<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:13:07+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "vi"
}
-->
# Thuật ngữ LangChain4j

## Mục lục

- [Khái niệm cốt lõi](../../../docs)
- [Thành phần LangChain4j](../../../docs)
- [Khái niệm AI/ML](../../../docs)
- [Kỹ thuật Prompt](../../../docs)
- [RAG (Tạo nội dung tăng cường truy xuất)](../../../docs)
- [Tác nhân và Công cụ](../../../docs)
- [Giao thức Ngữ cảnh Mô hình (MCP)](../../../docs)
- [Dịch vụ Azure](../../../docs)
- [Kiểm thử và Phát triển](../../../docs)

Tham khảo nhanh các thuật ngữ và khái niệm được sử dụng trong toàn khóa học.

## Khái niệm cốt lõi

**Tác nhân AI** - Hệ thống sử dụng AI để suy luận và hành động tự chủ. [Module 04](../04-tools/README.md)

**Chuỗi** - Chuỗi các thao tác trong đó đầu ra được đưa vào bước tiếp theo.

**Phân đoạn** - Chia tài liệu thành các phần nhỏ hơn. Thông thường: 300-500 token có chồng lấn. [Module 03](../03-rag/README.md)

**Cửa sổ ngữ cảnh** - Số token tối đa mà mô hình có thể xử lý. GPT-5: 400K token.

**Biểu diễn nhúng** - Vector số biểu diễn ý nghĩa văn bản. [Module 03](../03-rag/README.md)

**Gọi hàm** - Mô hình tạo yêu cầu có cấu trúc để gọi hàm bên ngoài. [Module 04](../04-tools/README.md)

**Ảo giác** - Khi mô hình tạo ra thông tin sai nhưng có vẻ hợp lý.

**Prompt** - Văn bản đầu vào cho mô hình ngôn ngữ. [Module 02](../02-prompt-engineering/README.md)

**Tìm kiếm ngữ nghĩa** - Tìm kiếm theo ý nghĩa sử dụng biểu diễn nhúng, không phải từ khóa. [Module 03](../03-rag/README.md)

**Trạng thái có nhớ vs không nhớ** - Không nhớ: không lưu lịch sử. Có nhớ: duy trì lịch sử hội thoại. [Module 01](../01-introduction/README.md)

**Token** - Đơn vị văn bản cơ bản mà mô hình xử lý. Ảnh hưởng chi phí và giới hạn. [Module 01](../01-introduction/README.md)

**Chuỗi công cụ** - Thực thi công cụ theo trình tự, đầu ra cung cấp thông tin cho lần gọi tiếp theo. [Module 04](../04-tools/README.md)

## Thành phần LangChain4j

**AiServices** - Tạo giao diện dịch vụ AI an toàn kiểu.

**OpenAiOfficialChatModel** - Khách hàng thống nhất cho các mô hình OpenAI và Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Tạo biểu diễn nhúng sử dụng khách OpenAI Official (hỗ trợ cả OpenAI và Azure OpenAI).

**ChatModel** - Giao diện cốt lõi cho các mô hình ngôn ngữ.

**ChatMemory** - Duy trì lịch sử hội thoại.

**ContentRetriever** - Tìm các đoạn tài liệu liên quan cho RAG.

**DocumentSplitter** - Chia tài liệu thành các đoạn nhỏ.

**EmbeddingModel** - Chuyển văn bản thành vector số.

**EmbeddingStore** - Lưu trữ và truy xuất biểu diễn nhúng.

**MessageWindowChatMemory** - Duy trì cửa sổ trượt các tin nhắn gần đây.

**PromptTemplate** - Tạo prompt tái sử dụng với các chỗ giữ chỗ `{{variable}}`.

**TextSegment** - Đoạn văn bản kèm siêu dữ liệu. Dùng trong RAG.

**ToolExecutionRequest** - Đại diện cho yêu cầu thực thi công cụ.

**UserMessage / AiMessage / SystemMessage** - Các loại tin nhắn trong hội thoại.

## Khái niệm AI/ML

**Học ít ví dụ** - Cung cấp ví dụ trong prompt. [Module 02](../02-prompt-engineering/README.md)

**Mô hình ngôn ngữ lớn (LLM)** - Mô hình AI được huấn luyện trên lượng lớn dữ liệu văn bản.

**Nỗ lực suy luận** - Tham số GPT-5 điều khiển độ sâu suy nghĩ. [Module 02](../02-prompt-engineering/README.md)

**Nhiệt độ** - Điều khiển độ ngẫu nhiên của đầu ra. Thấp = xác định, cao = sáng tạo.

**Cơ sở dữ liệu vector** - Cơ sở dữ liệu chuyên biệt cho biểu diễn nhúng. [Module 03](../03-rag/README.md)

**Học không ví dụ** - Thực hiện tác vụ mà không cần ví dụ. [Module 02](../02-prompt-engineering/README.md)

## Kỹ thuật Prompt - [Module 02](../02-prompt-engineering/README.md)

**Chuỗi suy nghĩ** - Suy luận từng bước để tăng độ chính xác.

**Đầu ra có ràng buộc** - Ép định dạng hoặc cấu trúc cụ thể.

**Nhiệt tình cao** - Mẫu GPT-5 cho suy luận kỹ lưỡng.

**Nhiệt tình thấp** - Mẫu GPT-5 cho câu trả lời nhanh.

**Hội thoại đa lượt** - Duy trì ngữ cảnh qua các lượt trao đổi.

**Prompt theo vai trò** - Đặt persona mô hình qua tin nhắn hệ thống.

**Tự phản ánh** - Mô hình tự đánh giá và cải thiện đầu ra.

**Phân tích có cấu trúc** - Khung đánh giá cố định.

**Mẫu thực thi tác vụ** - Lập kế hoạch → Thực thi → Tóm tắt.

## RAG (Tạo nội dung tăng cường truy xuất) - [Module 03](../03-rag/README.md)

**Quy trình xử lý tài liệu** - Tải → phân đoạn → nhúng → lưu trữ.

**Kho nhúng trong bộ nhớ** - Lưu trữ không bền cho kiểm thử.

**RAG** - Kết hợp truy xuất với tạo nội dung để căn cứ câu trả lời.

**Điểm tương đồng** - Thước đo (0-1) độ tương đồng ngữ nghĩa.

**Tham chiếu nguồn** - Siêu dữ liệu về nội dung được truy xuất.

## Tác nhân và Công cụ - [Module 04](../04-tools/README.md)

**Chú thích @Tool** - Đánh dấu phương thức Java là công cụ có thể gọi AI.

**Mẫu ReAct** - Suy luận → Hành động → Quan sát → Lặp lại.

**Quản lý phiên** - Ngữ cảnh riêng biệt cho từng người dùng.

**Công cụ** - Hàm mà tác nhân AI có thể gọi.

**Mô tả công cụ** - Tài liệu về mục đích và tham số công cụ.

## Giao thức Ngữ cảnh Mô hình (MCP) - [Module 05](../05-mcp/README.md)

**Giao thức Docker** - Máy chủ MCP trong container Docker.

**MCP** - Chuẩn kết nối ứng dụng AI với công cụ bên ngoài.

**Khách MCP** - Ứng dụng kết nối tới máy chủ MCP.

**Máy chủ MCP** - Dịch vụ cung cấp công cụ qua MCP.

**Sự kiện gửi từ máy chủ (SSE)** - Truyền dữ liệu từ máy chủ đến khách qua HTTP.

**Giao thức Stdio** - Máy chủ chạy dưới dạng tiến trình con qua stdin/stdout.

**Giao thức HTTP có thể truyền luồng** - HTTP với SSE cho giao tiếp thời gian thực.

**Khám phá công cụ** - Khách truy vấn máy chủ để biết công cụ có sẵn.

## Dịch vụ Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Tìm kiếm đám mây với khả năng vector. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Triển khai tài nguyên Azure.

**Azure OpenAI** - Dịch vụ AI doanh nghiệp của Microsoft.

**Bicep** - Ngôn ngữ hạ tầng dưới dạng mã của Azure. [Hướng dẫn hạ tầng](../01-introduction/infra/README.md)

**Tên triển khai** - Tên cho việc triển khai mô hình trên Azure.

**GPT-5** - Mô hình OpenAI mới nhất với điều khiển suy luận. [Module 02](../02-prompt-engineering/README.md)

## Kiểm thử và Phát triển - [Hướng dẫn kiểm thử](TESTING.md)

**Dev Container** - Môi trường phát triển đóng gói trong container. [Cấu hình](../../../.devcontainer/devcontainer.json)

**Mô hình GitHub** - Sân chơi mô hình AI miễn phí. [Module 00](../00-quick-start/README.md)

**Kiểm thử trong bộ nhớ** - Kiểm thử với lưu trữ trong bộ nhớ.

**Kiểm thử tích hợp** - Kiểm thử với hạ tầng thực tế.

**Maven** - Công cụ tự động hóa xây dựng Java.

**Mockito** - Framework giả lập trong Java.

**Spring Boot** - Framework ứng dụng Java. [Module 01](../01-introduction/README.md)

**Testcontainers** - Container Docker trong kiểm thử.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Tuyên bố từ chối trách nhiệm**:  
Tài liệu này đã được dịch bằng dịch vụ dịch thuật AI [Co-op Translator](https://github.com/Azure/co-op-translator). Mặc dù chúng tôi cố gắng đảm bảo độ chính xác, xin lưu ý rằng bản dịch tự động có thể chứa lỗi hoặc không chính xác. Tài liệu gốc bằng ngôn ngữ gốc của nó nên được coi là nguồn chính xác và đáng tin cậy. Đối với thông tin quan trọng, nên sử dụng dịch vụ dịch thuật chuyên nghiệp do con người thực hiện. Chúng tôi không chịu trách nhiệm về bất kỳ sự hiểu lầm hoặc giải thích sai nào phát sinh từ việc sử dụng bản dịch này.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->