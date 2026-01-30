# Bảng Thuật Ngữ LangChain4j

## Mục Lục

- [Khái Niệm Cốt lõi](../../../docs)
- [Thành phần LangChain4j](../../../docs)
- [Khái niệm AI/ML](../../../docs)
- [Bảo Đảm An Toàn](../../../docs)
- [Kỹ Thuật Thiết Kế Prompt](../../../docs)
- [RAG (Tạo Nội Dung Kèm Truy Xuất)](../../../docs)
- [Tác Nhân và Công Cụ](../../../docs)
- [Mô-đun Agentic](../../../docs)
- [Giao Thức Ngữ Cảnh Mô Hình (MCP)](../../../docs)
- [Dịch Vụ Azure](../../../docs)
- [Kiểm Thử và Phát Triển](../../../docs)

Tham khảo nhanh các thuật ngữ và khái niệm được sử dụng xuyên suốt khóa học.

## Khái Niệm Cốt lõi

**Tác Nhân AI** - Hệ thống sử dụng AI để suy luận và hành động một cách tự động. [Module 04](../04-tools/README.md)

**Chuỗi (Chain)** - Chuỗi các bước thao tác mà đầu ra của bước trước là đầu vào của bước tiếp theo.

**Chunking** - Phân tách tài liệu thành các phần nhỏ hơn. Thông thường: 300-500 token với phần trùng lặp. [Module 03](../03-rag/README.md)

**Cửa Sổ Ngữ Cảnh** - Số lượng token tối đa mà mô hình có thể xử lý. GPT-5: 400K token.

**Embedding** - Vector số đại diện cho ý nghĩa của văn bản. [Module 03](../03-rag/README.md)

**Gọi Hàm (Function Calling)** - Mô hình tạo yêu cầu có cấu trúc để gọi hàm ngoài. [Module 04](../04-tools/README.md)

**Hallucination** - Khi mô hình tạo ra thông tin sai nhưng có vẻ hợp lý.

**Prompt** - Văn bản đầu vào cho mô hình ngôn ngữ. [Module 02](../02-prompt-engineering/README.md)

**Tìm kiếm ngữ nghĩa** - Tìm kiếm dựa trên ý nghĩa sử dụng embeddings thay vì từ khóa. [Module 03](../03-rag/README.md)

**Trạng thái nhớ (Stateful) vs không nhớ (Stateless)** - Stateless: không lưu trữ lịch sử. Stateful: giữ lịch sử hội thoại. [Module 01](../01-introduction/README.md)

**Token** - Đơn vị văn bản cơ bản mà mô hình xử lý. Ảnh hưởng chi phí và giới hạn. [Module 01](../01-introduction/README.md)

**Chuỗi công cụ (Tool Chaining)** - Chuỗi gọi công cụ nơi đầu ra của công cụ này là đầu vào của công cụ kế tiếp. [Module 04](../04-tools/README.md)

## Thành phần LangChain4j

**AiServices** - Tạo giao diện dịch vụ AI kiểu an toàn.

**OpenAiOfficialChatModel** - Client hợp nhất cho các mô hình OpenAI và Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Tạo embeddings dùng client OpenAI Official (hỗ trợ cả OpenAI và Azure OpenAI).

**ChatModel** - Giao diện cốt lõi cho các mô hình ngôn ngữ.

**ChatMemory** - Duy trì lịch sử hội thoại.

**ContentRetriever** - Tìm các đoạn tài liệu liên quan cho RAG.

**DocumentSplitter** - Phân tách tài liệu thành các đoạn nhỏ.

**EmbeddingModel** - Chuyển văn bản thành vector số.

**EmbeddingStore** - Lưu trữ và truy xuất embeddings.

**MessageWindowChatMemory** - Duy trì cửa sổ trượt các tin nhắn gần đây.

**PromptTemplate** - Tạo prompt tái sử dụng với chỗ giữ chỗ `{{variable}}`.

**TextSegment** - Đoạn văn bản kèm siêu dữ liệu. Dùng trong RAG.

**ToolExecutionRequest** - Đại diện cho yêu cầu thực thi công cụ.

**UserMessage / AiMessage / SystemMessage** - Các loại tin nhắn trong hội thoại.

## Khái niệm AI/ML

**Học với ví dụ ít (Few-Shot Learning)** - Cung cấp ví dụ trong prompt. [Module 02](../02-prompt-engineering/README.md)

**Mô hình ngôn ngữ lớn (LLM)** - Mô hình AI được huấn luyện trên lượng lớn dữ liệu văn bản.

**Nỗ lực suy luận** - Tham số GPT-5 điều khiển độ sâu suy nghĩ. [Module 02](../02-prompt-engineering/README.md)

**Nhiệt độ** - Điều khiển độ ngẫu nhiên đầu ra. Thấp=định tính, cao=sáng tạo.

**Cơ sở dữ liệu vector** - Cơ sở dữ liệu chuyên biệt cho embeddings. [Module 03](../03-rag/README.md)

**Học không cần ví dụ (Zero-Shot Learning)** - Thực hiện tác vụ mà không cần ví dụ. [Module 02](../02-prompt-engineering/README.md)

## Bảo Đảm An Toàn - [Module 00](../00-quick-start/README.md)

**Phòng thủ theo chiều sâu** - Cách tiếp cận an ninh nhiều lớp kết hợp guardrails cấp ứng dụng với bộ lọc an toàn của nhà cung cấp.

**Khóa cứng (Hard Block)** - Nhà cung cấp trả lỗi HTTP 400 khi vi phạm nội dung nghiêm trọng.

**InputGuardrail** - Giao diện LangChain4j xác thực đầu vào người dùng trước khi vào LLM. Tiết kiệm chi phí và giảm độ trễ bằng cách chặn prompt độc hại từ sớm.

**InputGuardrailResult** - Kiểu trả về xác thực guardrail: `success()` hoặc `fatal("lý do")`.

**OutputGuardrail** - Giao diện để xác thực phản hồi AI trước khi trả lại người dùng.

**Bộ lọc an toàn nhà cung cấp** - Bộ lọc nội dung tích hợp sẵn của nhà cung cấp AI (ví dụ: GitHub Models) phát hiện vi phạm ở cấp API.

**Từ chối mềm** - Mô hình từ chối trả lời một cách lịch sự mà không báo lỗi.

## Kỹ Thuật Thiết Kế Prompt - [Module 02](../02-prompt-engineering/README.md)

**Chuỗi suy luận (Chain-of-Thought)** - Suy luận từng bước để tăng độ chính xác.

**Đầu ra có ràng buộc** - Ép định dạng hoặc cấu trúc cụ thể.

**Sự nhiệt tình cao** - Mẫu GPT-5 để suy luận kỹ lưỡng.

**Sự nhiệt tình thấp** - Mẫu GPT-5 để trả lời nhanh.

**Hội thoại nhiều lượt (Multi-Turn Conversation)** - Duy trì ngữ cảnh qua các vòng trao đổi.

**Prompt dựa trên vai trò** - Đặt nhân vật mô hình qua tin nhắn hệ thống.

**Tự đánh giá (Self-Reflection)** - Mô hình tự đánh giá và cải thiện đầu ra.

**Phân tích có cấu trúc** - Khung đánh giá cố định.

**Mẫu thực thi tác vụ** - Lập kế hoạch → Thực thi → Tóm tắt.

## RAG (Tạo Nội Dung Kèm Truy Xuất) - [Module 03](../03-rag/README.md)

**Chuỗi xử lý tài liệu** - Tải → phân đoạn → nhúng → lưu trữ.

**Kho nhúng trong bộ nhớ** - Kho lưu trữ không bền dùng để thử nghiệm.

**RAG** - Kết hợp truy xuất với tạo nội dung để làm cơ sở trả lời.

**Điểm tương đồng** - Đo lường (0-1) mức độ tương đồng ngữ nghĩa.

**Tham chiếu nguồn** - Siêu dữ liệu về nội dung truy xuất được.

## Tác Nhân và Công Cụ - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Đánh dấu phương thức Java là công cụ có thể gọi được bởi AI.

**Mẫu ReAct** - Suy nghĩ → Hành động → Quan sát → Lặp lại.

**Quản lý phiên** - Tách biệt ngữ cảnh cho các người dùng khác nhau.

**Công cụ** - Hàm mà tác nhân AI có thể gọi.

**Mô tả công cụ** - Tài liệu về mục đích và tham số công cụ.

## Mô-đun Agentic - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - Đánh dấu giao diện là tác nhân AI với định nghĩa hành vi khai báo.

**Agent Listener** - Hook theo dõi thực thi tác nhân qua `beforeAgentInvocation()` và `afterAgentInvocation()`.

**Phạm vi Agentic** - Bộ nhớ chia sẻ nơi các tác nhân lưu đầu ra dùng `outputKey` để các tác nhân sau tiêu thụ.

**AgenticServices** - Nhà máy tạo tác nhân dùng `agentBuilder()` và `supervisorBuilder()`.

**Luồng công việc điều kiện** - Chuyển hướng dựa trên điều kiện đến tác nhân chuyên môn khác nhau.

**Con người trong vòng lặp** - Mẫu luồng công việc thêm các điểm kiểm duyệt hoặc duyệt nội dung bởi con người.

**langchain4j-agentic** - Phụ thuộc Maven cho xây dựng tác nhân khai báo (thử nghiệm).

**Luồng công việc vòng lặp** - Lặp lại thực thi tác nhân đến khi thỏa điều kiện (ví dụ điểm chất lượng ≥ 0.8).

**outputKey** - Tham số annotation tác nhân chỉ định nơi lưu kết quả trong Phạm vi Agentic.

**Luồng công việc song song** - Chạy nhiều tác nhân đồng thời cho các tác vụ độc lập.

**Chiến lược phản hồi** - Cách giám sát viên định dạng câu trả lời cuối: LAST, SUMMARY hoặc SCORED.

**Luồng công việc tuần tự** - Thực thi các tác nhân theo thứ tự, đầu ra chảy tới bước sau.

**Mẫu tác nhân giám sát** - Mẫu agentic nâng cao, giám sát viên LLM quyết định động tác nhân phụ nào gọi.

## Giao Thức Ngữ Cảnh Mô Hình (MCP) - [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - Phụ thuộc Maven tích hợp MCP trong LangChain4j.

**MCP** - Giao thức Ngữ cảnh Mô hình: chuẩn kết nối ứng dụng AI với công cụ ngoài. Xây dựng một lần, dùng mọi nơi.

**Khách MCP** - Ứng dụng kết nối tới máy chủ MCP để khám phá và sử dụng công cụ.

**Máy chủ MCP** - Dịch vụ cung cấp công cụ qua MCP với mô tả rõ ràng và định nghĩa tham số.

**McpToolProvider** - Thành phần LangChain4j đóng gói công cụ MCP dùng trong dịch vụ và tác nhân AI.

**McpTransport** - Giao diện giao tiếp MCP. Các triển khai bao gồm Stdio và HTTP.

**Giao tiếp Stdio** - Giao tiếp qua stdin/stdout tiến trình cục bộ. Dùng để truy cập hệ thống tập tin hoặc công cụ dòng lệnh.

**StdioMcpTransport** - Triển khai LangChain4j khởi chạy máy chủ MCP dưới dạng tiến trình con.

**Khám phá công cụ** - Khách truy vấn máy chủ để lấy công cụ sẵn có với mô tả và định nghĩa.

## Dịch Vụ Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Tìm kiếm đám mây với khả năng vector hóa. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Triển khai tài nguyên Azure.

**Azure OpenAI** - Dịch vụ AI doanh nghiệp của Microsoft.

**Bicep** - Ngôn ngữ hạ tầng dưới dạng mã của Azure. [Hướng dẫn Hạ tầng](../01-introduction/infra/README.md)

**Tên triển khai** - Tên đặt cho triển khai mô hình trong Azure.

**GPT-5** - Mô hình OpenAI mới nhất với kiểm soát suy luận. [Module 02](../02-prompt-engineering/README.md)

## Kiểm Thử và Phát Triển - [Hướng dẫn Kiểm thử](TESTING.md)

**Dev Container** - Môi trường phát triển đóng gói container. [Cấu hình](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Mô hình AI miễn phí để thử nghiệm. [Module 00](../00-quick-start/README.md)

**Kiểm thử bộ nhớ trong** - Kiểm thử với lưu trữ trong bộ nhớ.

**Kiểm thử tích hợp** - Kiểm thử với hạ tầng thực tế.

**Maven** - Công cụ tự động xây dựng Java.

**Mockito** - Framework mô phỏng Java.

**Spring Boot** - Framework ứng dụng Java. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Tuyên bố từ chối trách nhiệm**:  
Tài liệu này đã được dịch bằng dịch vụ dịch thuật AI [Co-op Translator](https://github.com/Azure/co-op-translator). Mặc dù chúng tôi cố gắng đảm bảo tính chính xác, xin lưu ý rằng bản dịch tự động có thể chứa lỗi hoặc sai sót. Tài liệu gốc bằng ngôn ngữ bản địa nên được xem là nguồn thông tin chính xác và có thẩm quyền. Đối với các thông tin quan trọng, nên sử dụng dịch vụ dịch thuật chuyên nghiệp do con người thực hiện. Chúng tôi không chịu trách nhiệm về bất kỳ sự hiểu lầm hoặc giải thích sai nào phát sinh từ việc sử dụng bản dịch này.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->