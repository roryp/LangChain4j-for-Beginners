<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T02:52:02+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "vi"
}
-->
# LangChain4j Bảng thuật ngữ

## Mục lục

- [Khái niệm cốt lõi](../../../docs)
- [Thành phần LangChain4j](../../../docs)
- [Khái niệm AI/ML](../../../docs)
- [Kỹ thuật thiết kế Prompt - [Mô-đun 02](../02-prompt-engineering/README.md)](#prompt-engineering---module-02)
- [RAG (Tạo nội dung có tăng cường truy xuất) - [Mô-đun 03](../03-rag/README.md)](#rag-retrieval-augmented-generation---module-03)
- [Agents và Tools - [Mô-đun 04](../04-tools/README.md)](#agents-and-tools---module-04)
- [Model Context Protocol (MCP) - [Mô-đun 05](../05-mcp/README.md)](#model-context-protocol-mcp---module-05)
- [Dịch vụ Azure - [Mô-đun 01](../01-introduction/README.md)](#azure-services---module-01)
- [Kiểm thử và Phát triển - [Hướng dẫn kiểm thử](TESTING.md)](#testing-and-development---testing-guide)

Tham khảo nhanh cho các thuật ngữ và khái niệm được sử dụng trong suốt khóa học.

## Khái niệm cốt lõi

**AI Agent** - Hệ thống sử dụng AI để suy luận và hành động một cách tự chủ. [Mô-đun 04](../04-tools/README.md)

**Chain** - Chuỗi các phép toán trong đó đầu ra được đưa vào bước tiếp theo.

**Chunking** - Chia tài liệu thành các phần nhỏ hơn. Thông thường: 300-500 token với chồng lấp. [Mô-đun 03](../03-rag/README.md)

**Context Window** - Số token tối đa mà một mô hình có thể xử lý. GPT-5: 400K token.

**Embeddings** - Vectơ số biểu diễn ý nghĩa văn bản. [Mô-đun 03](../03-rag/README.md)

**Function Calling** - Mô hình tạo các yêu cầu có cấu trúc để gọi hàm ngoài. [Mô-đun 04](../04-tools/README.md)

**Hallucination** - Khi mô hình sinh ra thông tin sai nhưng có vẻ hợp lý.

**Prompt** - Văn bản đầu vào cho mô hình ngôn ngữ. [Mô-đun 02](../02-prompt-engineering/README.md)

**Semantic Search** - Tìm kiếm theo ý nghĩa sử dụng embeddings, không phải từ khóa. [Mô-đun 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: không có bộ nhớ. Stateful: duy trì lịch sử cuộc hội thoại. [Mô-đun 01](../01-introduction/README.md)

**Tokens** - Đơn vị văn bản cơ bản mà các mô hình xử lý. Ảnh hưởng đến chi phí và giới hạn. [Mô-đun 01](../01-introduction/README.md)

**Tool Chaining** - Thực thi các công cụ theo trình tự trong đó đầu ra cung cấp thông tin cho lần gọi tiếp theo. [Mô-đun 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Tạo giao diện dịch vụ AI an toàn theo kiểu.

**OpenAiOfficialChatModel** - Client hợp nhất cho các mô hình OpenAI và Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Tạo embeddings sử dụng client OpenAI Official (hỗ trợ cả OpenAI và Azure OpenAI).

**ChatModel** - Giao diện lõi cho các mô hình ngôn ngữ.

**ChatMemory** - Duy trì lịch sử cuộc hội thoại.

**ContentRetriever** - Tìm các đoạn tài liệu liên quan cho RAG.

**DocumentSplitter** - Chia tài liệu thành các đoạn.

**EmbeddingModel** - Chuyển văn bản thành vectơ số.

**EmbeddingStore** - Lưu trữ và truy xuất embeddings.

**MessageWindowChatMemory** - Duy trì cửa sổ trượt của các tin nhắn gần nhất.

**PromptTemplate** - Tạo các prompt có thể tái sử dụng với `{{variable}}` placeholders.

**TextSegment** - Đoạn văn bản kèm metadata. Sử dụng trong RAG.

**ToolExecutionRequest** - Đại diện cho yêu cầu thực thi công cụ.

**UserMessage / AiMessage / SystemMessage** - Các loại tin nhắn trong cuộc hội thoại.

## Khái niệm AI/ML

**Few-Shot Learning** - Cung cấp ví dụ trong prompt. [Mô-đun 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Các mô hình AI được huấn luyện trên lượng lớn dữ liệu văn bản.

**Reasoning Effort** - Tham số của GPT-5 điều khiển độ sâu suy luận. [Mô-đun 02](../02-prompt-engineering/README.md)

**Temperature** - Điều khiển độ ngẫu nhiên của đầu ra. Thấp = quyết định, cao = sáng tạo.

**Vector Database** - Cơ sở dữ liệu chuyên dụng cho embeddings. [Mô-đun 03](../03-rag/README.md)

**Zero-Shot Learning** - Thực hiện nhiệm vụ mà không có ví dụ. [Mô-đun 02](../02-prompt-engineering/README.md)

## Kỹ thuật thiết kế Prompt - [Mô-đun 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Suy luận từng bước để đạt độ chính xác tốt hơn.

**Constrained Output** - Ép buộc định dạng hoặc cấu trúc cụ thể cho đầu ra.

**High Eagerness** - Mẫu GPT-5 cho suy luận kỹ lưỡng.

**Low Eagerness** - Mẫu GPT-5 cho câu trả lời nhanh.

**Multi-Turn Conversation** - Duy trì ngữ cảnh qua nhiều lượt trao đổi.

**Role-Based Prompting** - Đặt nhân cách mô hình thông qua tin nhắn hệ thống.

**Self-Reflection** - Mô hình đánh giá và cải thiện đầu ra của chính nó.

**Structured Analysis** - Khung đánh giá cố định.

**Task Execution Pattern** - Lập kế hoạch → Thực thi → Tóm tắt.

## RAG (Retrieval-Augmented Generation) - [Mô-đun 03](../03-rag/README.md)

**Document Processing Pipeline** - Tải → chia đoạn → nhúng → lưu trữ.

**In-Memory Embedding Store** - Lưu trữ không bền cho mục đích kiểm thử.

**RAG** - Kết hợp truy xuất với sinh để làm cơ sở cho các phản hồi.

**Similarity Score** - Thước đo (0-1) của sự tương đồng ngữ nghĩa.

**Source Reference** - Metadata về nội dung được truy xuất.

## Agents and Tools - [Mô-đun 04](../04-tools/README.md)

**@Tool Annotation** - Đánh dấu các phương thức Java là công cụ có thể được AI gọi.

**ReAct Pattern** - Lý luận → Hành động → Quan sát → Lặp lại.

**Session Management** - Tách ngữ cảnh cho các người dùng khác nhau.

**Tool** - Hàm mà một tác nhân AI có thể gọi.

**Tool Description** - Tài liệu về mục đích và tham số của công cụ.

## Model Context Protocol (MCP) - [Mô-đun 05](../05-mcp/README.md)

**MCP** - Tiêu chuẩn để kết nối ứng dụng AI với các công cụ bên ngoài.

**MCP Client** - Ứng dụng kết nối tới các server MCP.

**MCP Server** - Dịch vụ phơi bày các công cụ qua MCP.

**Stdio Transport** - Server chạy dưới dạng subprocess qua stdin/stdout.

**Tool Discovery** - Client truy vấn server để biết các công cụ có sẵn.

## Azure Services - [Mô-đun 01](../01-introduction/README.md)

**Azure AI Search** - Dịch vụ tìm kiếm đám mây với khả năng vectơ. [Mô-đun 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Công cụ triển khai tài nguyên Azure.

**Azure OpenAI** - Dịch vụ AI doanh nghiệp của Microsoft.

**Bicep** - Ngôn ngữ hạ tầng-dưới-dạng-mã cho Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Tên cho triển khai mô hình trong Azure.

**GPT-5** - Mô hình OpenAI mới nhất có điều khiển suy luận. [Mô-đun 02](../02-prompt-engineering/README.md)

## Kiểm thử và Phát triển - [Hướng dẫn kiểm thử](TESTING.md)

**Dev Container** - Môi trường phát triển đóng gói trong container. [Cấu hình](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Sân chơi mô hình AI miễn phí. [Mô-đun 00](../00-quick-start/README.md)

**In-Memory Testing** - Kiểm thử sử dụng lưu trữ trong bộ nhớ.

**Integration Testing** - Kiểm thử với hạ tầng thực tế.

**Maven** - Công cụ tự động hóa build cho Java.

**Mockito** - Framework giả lập (mocking) cho Java.

**Spring Boot** - Khung ứng dụng Java. [Mô-đun 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Miễn trừ trách nhiệm:
Tài liệu này đã được dịch bằng dịch vụ dịch thuật AI Co-op Translator (https://github.com/Azure/co-op-translator). Mặc dù chúng tôi cố gắng đảm bảo tính chính xác, xin lưu ý rằng bản dịch tự động có thể có lỗi hoặc sai sót. Tài liệu gốc bằng ngôn ngữ ban đầu nên được coi là nguồn chính thức. Đối với thông tin quan trọng, nên sử dụng bản dịch chuyên nghiệp do người dịch thực hiện. Chúng tôi không chịu trách nhiệm về bất kỳ hiểu lầm hoặc diễn giải sai nào phát sinh từ việc sử dụng bản dịch này.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->