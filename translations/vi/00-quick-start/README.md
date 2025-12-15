<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T15:06:05+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "vi"
}
-->
# Module 00: Bắt Đầu Nhanh

## Mục Lục

- [Giới thiệu](../../../00-quick-start)
- [LangChain4j là gì?](../../../00-quick-start)
- [Phụ thuộc của LangChain4j](../../../00-quick-start)
- [Yêu cầu trước](../../../00-quick-start)
- [Cài đặt](../../../00-quick-start)
  - [1. Lấy Token GitHub của bạn](../../../00-quick-start)
  - [2. Đặt Token của bạn](../../../00-quick-start)
- [Chạy các ví dụ](../../../00-quick-start)
  - [1. Chat Cơ Bản](../../../00-quick-start)
  - [2. Mẫu Prompt](../../../00-quick-start)
  - [3. Gọi Hàm](../../../00-quick-start)
  - [4. Hỏi Đáp Tài Liệu (RAG)](../../../00-quick-start)
- [Mỗi ví dụ thể hiện điều gì](../../../00-quick-start)
- [Bước tiếp theo](../../../00-quick-start)
- [Khắc phục sự cố](../../../00-quick-start)

## Giới thiệu

Bắt đầu nhanh này nhằm giúp bạn khởi động và chạy với LangChain4j nhanh nhất có thể. Nó bao gồm những kiến thức cơ bản nhất về xây dựng ứng dụng AI với LangChain4j và GitHub Models. Trong các module tiếp theo, bạn sẽ sử dụng Azure OpenAI với LangChain4j để xây dựng các ứng dụng nâng cao hơn.

## LangChain4j là gì?

LangChain4j là một thư viện Java giúp đơn giản hóa việc xây dựng các ứng dụng AI. Thay vì phải xử lý các client HTTP và phân tích JSON, bạn làm việc với các API Java sạch sẽ.

"Chain" trong LangChain ám chỉ việc nối chuỗi nhiều thành phần lại với nhau - bạn có thể nối một prompt với một mô hình rồi tới một bộ phân tích, hoặc nối nhiều lần gọi AI với nhau, đầu ra của lần này làm đầu vào cho lần tiếp theo. Bắt đầu nhanh này tập trung vào các nguyên tắc cơ bản trước khi khám phá các chuỗi phức tạp hơn.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1e961a13c73d45cfa305fd03d81bd11e5d34d0e36ed28a44d.vi.png" alt="Khái niệm Nối Chuỗi LangChain4j" width="800"/>

*Nối các thành phần trong LangChain4j - các khối xây dựng kết nối để tạo ra các quy trình AI mạnh mẽ*

Chúng ta sẽ sử dụng ba thành phần cốt lõi:

**ChatLanguageModel** - Giao diện cho các tương tác với mô hình AI. Gọi `model.chat("prompt")` và nhận về một chuỗi phản hồi. Chúng ta dùng `OpenAiOfficialChatModel` hoạt động với các endpoint tương thích OpenAI như GitHub Models.

**AiServices** - Tạo các giao diện dịch vụ AI an toàn kiểu. Định nghĩa các phương thức, chú thích chúng với `@Tool`, và LangChain4j sẽ xử lý việc phối hợp. AI tự động gọi các phương thức Java của bạn khi cần.

**MessageWindowChatMemory** - Duy trì lịch sử hội thoại. Nếu không có nó, mỗi yêu cầu là độc lập. Với nó, AI nhớ các tin nhắn trước và duy trì ngữ cảnh qua nhiều lượt.

<img src="../../../translated_images/architecture.eedc993a1c57683951f20244f652fc7a9853f571eea835bc2b2828cf0dbf62d0.vi.png" alt="Kiến trúc LangChain4j" width="800"/>

*Kiến trúc LangChain4j - các thành phần cốt lõi làm việc cùng nhau để cung cấp sức mạnh cho ứng dụng AI của bạn*

## Phụ thuộc của LangChain4j

Bắt đầu nhanh này sử dụng hai phụ thuộc Maven trong [`pom.xml`](../../../00-quick-start/pom.xml):

```xml
<!-- Core LangChain4j library -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>

<!-- OpenAI integration (works with GitHub Models) -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

Module `langchain4j-open-ai-official` cung cấp lớp `OpenAiOfficialChatModel` kết nối với các API tương thích OpenAI. GitHub Models sử dụng cùng định dạng API, nên không cần adapter đặc biệt - chỉ cần trỏ URL cơ sở tới `https://models.github.ai/inference`.

## Yêu cầu trước

**Sử dụng Dev Container?** Java và Maven đã được cài sẵn. Bạn chỉ cần một GitHub Personal Access Token.

**Phát triển cục bộ:**
- Java 21+, Maven 3.9+
- GitHub Personal Access Token (hướng dẫn bên dưới)

> **Lưu ý:** Module này sử dụng `gpt-4.1-nano` từ GitHub Models. Không thay đổi tên mô hình trong mã - nó được cấu hình để hoạt động với các mô hình có sẵn của GitHub.

## Cài đặt

### 1. Lấy Token GitHub của bạn

1. Truy cập [GitHub Settings → Personal Access Tokens](https://github.com/settings/personal-access-tokens)
2. Nhấn "Generate new token"
3. Đặt tên mô tả (ví dụ: "LangChain4j Demo")
4. Đặt thời hạn (khuyến nghị 7 ngày)
5. Dưới "Account permissions", tìm "Models" và đặt thành "Read-only"
6. Nhấn "Generate token"
7. Sao chép và lưu token của bạn - bạn sẽ không thấy lại nó nữa

### 2. Đặt Token của bạn

**Tùy chọn 1: Sử dụng VS Code (Khuyến nghị)**

Nếu bạn dùng VS Code, thêm token vào file `.env` trong thư mục gốc dự án:

Nếu file `.env` không tồn tại, sao chép `.env.example` thành `.env` hoặc tạo file `.env` mới trong thư mục gốc.

**Ví dụ file `.env`:**
```bash
# Trong /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Sau đó bạn chỉ cần nhấp chuột phải vào bất kỳ file demo nào (ví dụ `BasicChatDemo.java`) trong Explorer và chọn **"Run Java"** hoặc dùng cấu hình khởi chạy từ bảng Run and Debug.

**Tùy chọn 2: Sử dụng Terminal**

Đặt token dưới dạng biến môi trường:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Chạy các ví dụ

**Dùng VS Code:** Chỉ cần nhấp chuột phải vào bất kỳ file demo nào trong Explorer và chọn **"Run Java"**, hoặc dùng cấu hình khởi chạy từ bảng Run and Debug (đảm bảo bạn đã thêm token vào file `.env` trước).

**Dùng Maven:** Ngoài ra, bạn có thể chạy từ dòng lệnh:

### 1. Chat Cơ Bản

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Mẫu Prompt

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Hiển thị zero-shot, few-shot, chain-of-thought, và role-based prompting.

### 3. Gọi Hàm

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI tự động gọi các phương thức Java của bạn khi cần.

### 4. Hỏi Đáp Tài Liệu (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Hỏi các câu về nội dung trong `document.txt`.

## Mỗi ví dụ thể hiện điều gì

**Chat Cơ Bản** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Bắt đầu từ đây để thấy LangChain4j đơn giản nhất. Bạn sẽ tạo một `OpenAiOfficialChatModel`, gửi prompt với `.chat()`, và nhận phản hồi. Điều này minh họa nền tảng: cách khởi tạo mô hình với endpoint và khóa API tùy chỉnh. Khi bạn hiểu mẫu này, mọi thứ khác sẽ xây dựng dựa trên nó.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Thử với [GitHub Copilot](https://github.com/features/copilot) Chat:** Mở [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) và hỏi:
> - "Làm sao để chuyển từ GitHub Models sang Azure OpenAI trong mã này?"
> - "Có những tham số nào khác tôi có thể cấu hình trong OpenAiOfficialChatModel.builder()?"
> - "Làm sao để thêm phản hồi streaming thay vì chờ phản hồi hoàn chỉnh?"

**Kỹ thuật Prompt** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Bây giờ bạn đã biết cách nói chuyện với mô hình, hãy khám phá cách bạn nói với nó. Demo này dùng cùng cấu hình mô hình nhưng thể hiện bốn mẫu prompt khác nhau. Thử zero-shot cho chỉ dẫn trực tiếp, few-shot học từ ví dụ, chain-of-thought tiết lộ các bước suy luận, và role-based thiết lập ngữ cảnh. Bạn sẽ thấy cùng một mô hình cho kết quả rất khác dựa trên cách bạn đặt câu hỏi.

```java
PromptTemplate template = PromptTemplate.from(
    "What's the best time to visit {{destination}} for {{activity}}?"
);

Prompt prompt = template.apply(Map.of(
    "destination", "Paris",
    "activity", "sightseeing"
));

String response = model.chat(prompt.text());
```

> **🤖 Thử với [GitHub Copilot](https://github.com/features/copilot) Chat:** Mở [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) và hỏi:
> - "Sự khác biệt giữa zero-shot và few-shot prompting là gì, và khi nào nên dùng mỗi loại?"
> - "Tham số temperature ảnh hưởng thế nào đến phản hồi của mô hình?"
> - "Có những kỹ thuật nào để ngăn chặn tấn công prompt injection trong môi trường sản xuất?"
> - "Làm sao để tạo các đối tượng PromptTemplate tái sử dụng cho các mẫu phổ biến?"

**Tích hợp Công cụ** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Đây là nơi LangChain4j trở nên mạnh mẽ. Bạn sẽ dùng `AiServices` để tạo trợ lý AI có thể gọi các phương thức Java của bạn. Chỉ cần chú thích phương thức với `@Tool("mô tả")` và LangChain4j sẽ xử lý phần còn lại - AI tự động quyết định khi nào dùng công cụ dựa trên yêu cầu người dùng. Điều này minh họa gọi hàm, kỹ thuật then chốt để xây dựng AI có thể thực hiện hành động, không chỉ trả lời câu hỏi.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Thử với [GitHub Copilot](https://github.com/features/copilot) Chat:** Mở [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) và hỏi:
> - "Chú thích @Tool hoạt động thế nào và LangChain4j làm gì với nó phía sau?"
> - "AI có thể gọi nhiều công cụ theo chuỗi để giải quyết vấn đề phức tạp không?"
> - "Nếu một công cụ ném ra ngoại lệ thì sao - tôi nên xử lý lỗi thế nào?"
> - "Làm sao để tích hợp API thực thay vì ví dụ máy tính này?"

**Hỏi Đáp Tài Liệu (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Ở đây bạn sẽ thấy nền tảng của RAG (retrieval-augmented generation). Thay vì dựa vào dữ liệu huấn luyện của mô hình, bạn tải nội dung từ [`document.txt`](../../../00-quick-start/document.txt) và đưa vào prompt. AI trả lời dựa trên tài liệu của bạn, không phải kiến thức chung. Đây là bước đầu để xây dựng hệ thống làm việc với dữ liệu riêng của bạn.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Lưu ý:** Cách đơn giản này tải toàn bộ tài liệu vào prompt. Với file lớn (>10KB), bạn sẽ vượt quá giới hạn ngữ cảnh. Module 03 sẽ đề cập đến chia nhỏ và tìm kiếm vector cho hệ thống RAG sản xuất.

> **🤖 Thử với [GitHub Copilot](https://github.com/features/copilot) Chat:** Mở [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) và hỏi:
> - "RAG ngăn chặn ảo giác AI thế nào so với dùng dữ liệu huấn luyện của mô hình?"
> - "Sự khác biệt giữa cách đơn giản này và dùng vector embeddings để truy xuất là gì?"
> - "Làm sao để mở rộng xử lý nhiều tài liệu hoặc cơ sở tri thức lớn hơn?"
> - "Các thực hành tốt nhất để cấu trúc prompt nhằm đảm bảo AI chỉ dùng ngữ cảnh được cung cấp là gì?"

## Gỡ lỗi

Các ví dụ bao gồm `.logRequests(true)` và `.logResponses(true)` để hiển thị các cuộc gọi API trên console. Điều này giúp khắc phục lỗi xác thực, giới hạn tốc độ, hoặc phản hồi không mong muốn. Hãy bỏ các cờ này trong môi trường sản xuất để giảm tiếng ồn log.

## Bước tiếp theo

**Module tiếp theo:** [01-introduction - Bắt đầu với LangChain4j và gpt-5 trên Azure](../01-introduction/README.md)

---

**Điều hướng:** [← Quay lại Chính](../README.md) | [Tiếp: Module 01 - Giới thiệu →](../01-introduction/README.md)

---

## Khắc phục sự cố

### Lần đầu build Maven

**Vấn đề**: Lệnh `mvn clean compile` hoặc `mvn package` ban đầu mất nhiều thời gian (10-15 phút)

**Nguyên nhân**: Maven cần tải tất cả phụ thuộc dự án (Spring Boot, thư viện LangChain4j, SDK Azure, v.v.) trong lần build đầu tiên.

**Giải pháp**: Đây là hành vi bình thường. Các lần build sau sẽ nhanh hơn nhiều vì phụ thuộc được lưu trong bộ nhớ cache cục bộ. Thời gian tải phụ thuộc vào tốc độ mạng của bạn.

### Cú pháp lệnh Maven trên PowerShell

**Vấn đề**: Lệnh Maven báo lỗi `Unknown lifecycle phase ".mainClass=..."`

**Nguyên nhân**: PowerShell hiểu `=` là toán tử gán biến, làm hỏng cú pháp thuộc tính Maven

**Giải pháp**: Dùng toán tử dừng phân tích `--%` trước lệnh Maven:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Toán tử `--%` bảo PowerShell truyền tất cả đối số còn lại nguyên văn cho Maven mà không giải thích.

### Hiển thị emoji trên Windows PowerShell

**Vấn đề**: Phản hồi AI hiển thị ký tự rác (ví dụ `????` hoặc `â??`) thay vì emoji trong PowerShell

**Nguyên nhân**: Mã hóa mặc định của PowerShell không hỗ trợ emoji UTF-8

**Giải pháp**: Chạy lệnh này trước khi chạy ứng dụng Java:
```cmd
chcp 65001
```

Điều này ép mã hóa UTF-8 trong terminal. Ngoài ra, bạn có thể dùng Windows Terminal có hỗ trợ Unicode tốt hơn.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Tuyên bố từ chối trách nhiệm**:  
Tài liệu này đã được dịch bằng dịch vụ dịch thuật AI [Co-op Translator](https://github.com/Azure/co-op-translator). Mặc dù chúng tôi cố gắng đảm bảo độ chính xác, xin lưu ý rằng bản dịch tự động có thể chứa lỗi hoặc không chính xác. Tài liệu gốc bằng ngôn ngữ gốc của nó nên được coi là nguồn chính xác và đáng tin cậy. Đối với thông tin quan trọng, nên sử dụng dịch vụ dịch thuật chuyên nghiệp do con người thực hiện. Chúng tôi không chịu trách nhiệm về bất kỳ sự hiểu lầm hoặc giải thích sai nào phát sinh từ việc sử dụng bản dịch này.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->