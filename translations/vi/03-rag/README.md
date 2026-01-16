<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-06T00:14:29+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "vi"
}
-->
# Module 03: RAG (Tạo Sinh Tăng Cường Truy Xuất)

## Mục Lục

- [Bạn Sẽ Học Gì](../../../03-rag)
- [Yêu Cầu Trước](../../../03-rag)
- [Hiểu Về RAG](../../../03-rag)
- [Nó Hoạt Động Như Thế Nào](../../../03-rag)
  - [Xử Lý Tài Liệu](../../../03-rag)
  - [Tạo Embeddings](../../../03-rag)
  - [Tìm Kiếm Ngữ Nghĩa](../../../03-rag)
  - [Tạo Câu Trả Lời](../../../03-rag)
- [Chạy Ứng Dụng](../../../03-rag)
- [Sử Dụng Ứng Dụng](../../../03-rag)
  - [Tải Lên Tài Liệu](../../../03-rag)
  - [Đặt Câu Hỏi](../../../03-rag)
  - [Kiểm Tra Tham Chiếu Nguồn](../../../03-rag)
  - [Thử Nghiệm Với Câu Hỏi](../../../03-rag)
- [Khái Niệm Chính](../../../03-rag)
  - [Chiến Lược Chia Đoạn](../../../03-rag)
  - [Điểm Tương Đồng](../../../03-rag)
  - [Lưu Trữ Trong Bộ Nhớ](../../../03-rag)
  - [Quản Lý Cửa Sổ Ngữ Cảnh](../../../03-rag)
- [Khi Nào RAG Quan Trọng](../../../03-rag)
- [Bước Tiếp Theo](../../../03-rag)

## Bạn Sẽ Học Gì

Trong các module trước, bạn đã học cách trò chuyện với AI và cấu trúc prompt hiệu quả. Nhưng có một hạn chế cơ bản: các mô hình ngôn ngữ chỉ biết những gì chúng học được trong quá trình huấn luyện. Chúng không thể trả lời các câu hỏi về chính sách công ty bạn, tài liệu dự án của bạn, hay bất kỳ thông tin nào mà chúng không được huấn luyện.

RAG (Tạo Sinh Tăng Cường Truy Xuất) giải quyết vấn đề này. Thay vì cố gắng dạy mô hình thông tin của bạn (việc này tốn kém và không thực tế), bạn cung cấp cho nó khả năng tìm kiếm trong tài liệu của bạn. Khi ai đó đặt câu hỏi, hệ thống sẽ tìm thông tin liên quan và đưa vào prompt. Mô hình sau đó trả lời dựa trên ngữ cảnh được truy xuất.

Hãy nghĩ về RAG như việc cung cấp cho mô hình một thư viện tham khảo. Khi bạn hỏi một câu hỏi, hệ thống sẽ:

1. **Truy Vấn Người Dùng** - Bạn đặt câu hỏi
2. **Embedding** - Chuyển câu hỏi của bạn thành vectơ
3. **Tìm Kiếm Vectơ** - Tìm các đoạn tài liệu tương tự
4. **Tổng Hợp Ngữ Cảnh** - Thêm các đoạn liên quan vào prompt
5. **Phản Hồi** - LLM tạo câu trả lời dựa trên ngữ cảnh

Điều này giúp câu trả lời của mô hình dựa trên dữ liệu thực tế của bạn thay vì dựa vào kiến thức huấn luyện hoặc bịa đặt câu trả lời.

<img src="../../../translated_images/vi/rag-architecture.ccb53b71a6ce407f.png" alt="Kiến Trúc RAG" width="800"/>

*Quy trình làm việc của RAG - từ truy vấn người dùng đến tìm kiếm ngữ nghĩa và tạo câu trả lời theo ngữ cảnh*

## Yêu Cầu Trước

- Hoàn thành Module 01 (đã triển khai tài nguyên Azure OpenAI)
- File `.env` trong thư mục gốc chứa thông tin xác thực Azure (được tạo bởi `azd up` trong Module 01)

> **Lưu ý:** Nếu bạn chưa hoàn thành Module 01, hãy làm theo hướng dẫn triển khai ở đó trước.

## Nó Hoạt Động Như Thế Nào

### Xử Lý Tài Liệu

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

Khi bạn tải lên một tài liệu, hệ thống sẽ chia nhỏ tài liệu thành các đoạn nhỏ - những mảnh nhỏ vừa đủ để mô hình có thể chứa trong cửa sổ ngữ cảnh. Những đoạn này chồng lấn nhẹ để bạn không bị mất ngữ cảnh ở ranh giới.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```

> **🤖 Thử với [GitHub Copilot](https://github.com/features/copilot) Chat:** Mở [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) và hỏi:
> - "LangChain4j chia tài liệu thành các đoạn như thế nào và tại sao chồng lấn lại quan trọng?"
> - "Kích thước đoạn tối ưu cho các loại tài liệu khác nhau là gì và tại sao?"
> - "Làm thế nào để xử lý tài liệu đa ngôn ngữ hoặc có định dạng đặc biệt?"

### Tạo Embeddings

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Mỗi đoạn được chuyển đổi thành một biểu diễn số gọi là embedding - về cơ bản là một dấu vân tay toán học thể hiện ý nghĩa của văn bản. Các văn bản giống nhau sẽ tạo ra embeddings tương tự.

```java
@Bean
public EmbeddingModel embeddingModel() {
    return OpenAiOfficialEmbeddingModel.builder()
        .baseUrl(azureOpenAiEndpoint)
        .apiKey(azureOpenAiKey)
        .modelName(azureEmbeddingDeploymentName)
        .build();
}

EmbeddingStore<TextSegment> embeddingStore = 
    new InMemoryEmbeddingStore<>();
```

<img src="../../../translated_images/vi/vector-embeddings.2ef7bdddac79a327.png" alt="Không gian Embeddings Vectơ" width="800"/>

*Tài liệu được biểu diễn dưới dạng các vectơ trong không gian embedding - các nội dung tương tự sẽ tụ lại gần nhau*

### Tìm Kiếm Ngữ Nghĩa

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Khi bạn đặt câu hỏi, câu hỏi của bạn cũng được chuyển thành embedding. Hệ thống so sánh embedding của câu hỏi với tất cả các đoạn tài liệu. Nó tìm những đoạn có ý nghĩa tương đồng nhất - không chỉ là từ khóa trùng, mà thực sự là sự tương đồng về ngữ nghĩa.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```

> **🤖 Thử với [GitHub Copilot](https://github.com/features/copilot) Chat:** Mở [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) và hỏi:
> - "Tìm kiếm tương đồng hoạt động như thế nào với embeddings và điểm số được xác định ra sao?"
> - "Ngưỡng tương đồng nên dùng là bao nhiêu và nó ảnh hưởng thế nào đến kết quả?"
> - "Làm sao xử lý khi không tìm thấy tài liệu liên quan?"

### Tạo Câu Trả Lời

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Những đoạn liên quan nhất được đưa vào prompt cho mô hình. Mô hình đọc các đoạn cụ thể đó và trả lời câu hỏi dựa trên thông tin này. Điều này ngăn ngừa việc tạo câu trả lời bịa đặt - mô hình chỉ trả lời dựa trên những gì có sẵn trước mặt nó.

## Chạy Ứng Dụng

**Xác minh triển khai:**

Đảm bảo file `.env` tồn tại trong thư mục gốc chứa thông tin xác thực Azure (được tạo trong Module 01):
```bash
cat ../.env  # Nên hiển thị AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Khởi động ứng dụng:**

> **Lưu ý:** Nếu bạn đã khởi động tất cả ứng dụng bằng `./start-all.sh` từ Module 01, module này đã chạy trên cổng 8081. Bạn có thể bỏ qua các lệnh khởi động dưới đây và truy cập trực tiếp http://localhost:8081.

**Tùy chọn 1: Sử dụng Spring Boot Dashboard (Khuyến nghị cho người dùng VS Code)**

Dev container bao gồm phần mở rộng Spring Boot Dashboard, cung cấp giao diện trực quan để quản lý tất cả ứng dụng Spring Boot. Bạn có thể tìm nó trên Thanh hoạt động bên trái của VS Code (nhìn biểu tượng Spring Boot).

Từ Spring Boot Dashboard, bạn có thể:
- Xem tất cả các ứng dụng Spring Boot trong workspace
- Bắt đầu/dừng ứng dụng chỉ với một nhấp chuột
- Xem nhật ký ứng dụng theo thời gian thực
- Giám sát trạng thái ứng dụng

Chỉ cần nhấp nút chạy bên cạnh "rag" để khởi động module này, hoặc khởi động tất cả các module cùng lúc.

<img src="../../../translated_images/vi/dashboard.fbe6e28bf4267ffe.png" alt="Spring Boot Dashboard" width="400"/>

**Tùy chọn 2: Sử dụng shell scripts**

Khởi động tất cả ứng dụng web (các module 01-04):

**Bash:**
```bash
cd ..  # Từ thư mục gốc
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Từ thư mục gốc
.\start-all.ps1
```

Hoặc chỉ khởi động module này:

**Bash:**
```bash
cd 03-rag
./start.sh
```

**PowerShell:**
```powershell
cd 03-rag
.\start.ps1
```

Cả hai script tự động tải biến môi trường từ file `.env` ở thư mục gốc và sẽ xây dựng JAR nếu chưa có.

> **Lưu ý:** Nếu bạn muốn tự xây dựng tất cả module trước khi chạy:
>
> **Bash:**
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
>
> **PowerShell:**
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

Mở http://localhost:8081 trong trình duyệt.

**Để dừng:**

**Bash:**
```bash
./stop.sh  # Chỉ module này
# Hoặc
cd .. && ./stop-all.sh  # Tất cả các module
```

**PowerShell:**
```powershell
.\stop.ps1  # Chỉ mô-đun này
# Hoặc
cd ..; .\stop-all.ps1  # Tất cả các mô-đun
```

## Sử Dụng Ứng Dụng

Ứng dụng cung cấp giao diện web để tải tài liệu lên và đặt câu hỏi.

<a href="images/rag-homepage.png"><img src="../../../translated_images/vi/rag-homepage.d90eb5ce1b3caa94.png" alt="Giao Diện Ứng Dụng RAG" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Giao diện ứng dụng RAG - tải tài liệu và đặt câu hỏi*

### Tải Lên Tài Liệu

Bắt đầu bằng việc tải lên một tài liệu - các file TXT hoạt động tốt nhất để thử nghiệm. Một file `sample-document.txt` được cung cấp trong thư mục này chứa thông tin về các tính năng LangChain4j, cách triển khai RAG, và các thực hành tốt nhất - rất phù hợp để thử nghiệm hệ thống.

Hệ thống xử lý tài liệu của bạn, chia thành các đoạn nhỏ, và tạo embeddings cho mỗi đoạn. Việc này diễn ra tự động khi bạn tải lên.

### Đặt Câu Hỏi

Bây giờ hãy đặt câu hỏi cụ thể về nội dung tài liệu. Thử hỏi một điều chắc chắn rõ ràng được nêu trong tài liệu. Hệ thống sẽ tìm các đoạn liên quan, đưa vào prompt, và tạo câu trả lời.

### Kiểm Tra Tham Chiếu Nguồn

Chú ý mỗi câu trả lời đều có tham chiếu nguồn kèm điểm tương đồng. Những điểm này (từ 0 đến 1) cho thấy mức độ liên quan của từng đoạn với câu hỏi của bạn. Điểm càng cao nghĩa là khớp càng tốt. Điều này cho phép bạn xác minh câu trả lời dựa trên tài liệu gốc.

<a href="images/rag-query-results.png"><img src="../../../translated_images/vi/rag-query-results.6d69fcec5397f355.png" alt="Kết Quả Truy Vấn RAG" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Kết quả truy vấn hiển thị câu trả lời kèm tham chiếu nguồn và điểm độ liên quan*

### Thử Nghiệm Với Câu Hỏi

Thử các loại câu hỏi khác nhau:
- Sự kiện cụ thể: "Chủ đề chính là gì?"
- So sánh: "Sự khác biệt giữa X và Y là gì?"
- Tóm tắt: "Tóm tắt các điểm chính về Z"

Quan sát cách điểm độ liên quan thay đổi dựa trên sự khớp giữa câu hỏi và nội dung tài liệu.

## Khái Niệm Chính

### Chiến Lược Chia Đoạn

Tài liệu được chia thành các đoạn 300 token với 30 token chồng lấn. Sự cân bằng này đảm bảo mỗi đoạn đủ ngữ cảnh để có ý nghĩa trong khi vẫn nhỏ để có thể bao gồm nhiều đoạn trong cùng một prompt.

### Điểm Tương Đồng

Điểm số dao động từ 0 đến 1:
- 0.7-1.0: Rất liên quan, khớp chính xác
- 0.5-0.7: Liên quan, có ngữ cảnh tốt
- Dưới 0.5: Bị lọc bỏ vì quá khác biệt

Hệ thống chỉ lấy các đoạn trên ngưỡng tối thiểu để đảm bảo chất lượng.

### Lưu Trữ Trong Bộ Nhớ

Module này sử dụng lưu trữ trong bộ nhớ để đơn giản. Khi bạn khởi động lại ứng dụng, các tài liệu đã tải lên sẽ bị mất. Hệ thống sản xuất thực tế sử dụng cơ sở dữ liệu vectơ bền vững như Qdrant hoặc Azure AI Search.

### Quản Lý Cửa Sổ Ngữ Cảnh

Mỗi mô hình có một cửa sổ ngữ cảnh tối đa. Bạn không thể đưa tất cả các đoạn từ một tài liệu lớn vào. Hệ thống lấy N đoạn liên quan nhất (mặc định 5) để giữ trong giới hạn đồng thời cung cấp đủ ngữ cảnh cho câu trả lời chính xác.

## Khi Nào RAG Quan Trọng

**Dùng RAG khi:**
- Trả lời câu hỏi về tài liệu độc quyền
- Thông tin thay đổi thường xuyên (chính sách, giá, thông số)
- Cần độ chính xác có nguồn tham khảo
- Nội dung quá lớn để đưa vào prompt đơn
- Bạn cần câu trả lời có thể kiểm chứng, có căn cứ

**Không dùng RAG khi:**
- Câu hỏi yêu cầu kiến thức chung mà mô hình đã có
- Cần dữ liệu thời gian thực (RAG hoạt động trên tài liệu đã tải lên)
- Nội dung đủ nhỏ để đưa trực tiếp vào prompt

## Bước Tiếp Theo

**Module tiếp theo:** [04-tools - AI Agents with Tools](../04-tools/README.md)

---

**Điều hướng:** [← Trước: Module 02 - Kỹ Thuật Prompt](../02-prompt-engineering/README.md) | [Quay lại Trang Chính](../README.md) | [Tiếp theo: Module 04 - Công Cụ →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Tuyên bố từ chối trách nhiệm**:
Tài liệu này đã được dịch bằng dịch vụ dịch thuật AI [Co-op Translator](https://github.com/Azure/co-op-translator). Mặc dù chúng tôi cố gắng đảm bảo độ chính xác, xin lưu ý rằng các bản dịch tự động có thể chứa lỗi hoặc không chính xác. Tài liệu gốc bằng ngôn ngữ gốc của nó nên được coi là nguồn chính xác và đáng tin cậy. Đối với các thông tin quan trọng, khuyến nghị sử dụng dịch vụ dịch thuật chuyên nghiệp bởi con người. Chúng tôi không chịu trách nhiệm về bất kỳ sự hiểu lầm hoặc giải thích sai nào phát sinh từ việc sử dụng bản dịch này.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->