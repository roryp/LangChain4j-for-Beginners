# LangChain4j 术语表

## 目录

- [核心概念](../../../docs)
- [LangChain4j 组件](../../../docs)
- [AI/ML 概念](../../../docs)
- [护栏](../../../docs)
- [提示工程](../../../docs)
- [RAG（检索增强生成）](../../../docs)
- [代理和工具](../../../docs)
- [代理模块](../../../docs)
- [模型上下文协议 (MCP)](../../../docs)
- [Azure 服务](../../../docs)
- [测试与开发](../../../docs)

课程中使用术语和概念的快速参考。

## 核心概念

**AI Agent** - 使用 AI 进行推理和自主行动的系统。 [模块 04](../04-tools/README.md)

**Chain** - 一系列操作，输出作为下一步输入。

**Chunking** - 将文档拆分成更小的片段。典型：300-500 令牌，带重叠。 [模块 03](../03-rag/README.md)

**Context Window** - 模型可处理的最大令牌数。GPT-5：400K 令牌。

**Embeddings** - 表示文本含义的数值向量。 [模块 03](../03-rag/README.md)

**Function Calling** - 模型生成结构化请求以调用外部函数。 [模块 04](../04-tools/README.md)

**Hallucination** - 模型生成错误但似乎合理的信息。

**Prompt** - 语言模型的文本输入。 [模块 02](../02-prompt-engineering/README.md)

**Semantic Search** - 基于含义的搜索，使用嵌入而非关键词。 [模块 03](../03-rag/README.md)

**Stateful vs Stateless** - 无状态：无记忆。有状态：保持对话历史。 [模块 01](../01-introduction/README.md)

**Tokens** - 模型处理的基本文本单位。影响成本和限制。 [模块 01](../01-introduction/README.md)

**Tool Chaining** - 顺序执行工具，输出用于下一步调用。 [模块 04](../04-tools/README.md)

## LangChain4j 组件

**AiServices** - 创建类型安全的 AI 服务接口。

**OpenAiOfficialChatModel** - OpenAI 和 Azure OpenAI 模型的统一客户端。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI 官方客户端创建嵌入（支持 OpenAI 和 Azure OpenAI）。

**ChatModel** - 语言模型核心接口。

**ChatMemory** - 维护对话历史。

**ContentRetriever** - 为 RAG 查找相关文档片段。

**DocumentSplitter** - 将文档拆分成片段。

**EmbeddingModel** - 将文本转换为数值向量。

**EmbeddingStore** - 存储和检索嵌入。

**MessageWindowChatMemory** - 维护最近消息的滑动窗口。

**PromptTemplate** - 创建可重用提示，带有 `{{variable}}` 占位符。

**TextSegment** - 带元数据的文本片段。用于 RAG。

**ToolExecutionRequest** - 表示工具执行请求。

**UserMessage / AiMessage / SystemMessage** - 对话消息类型。

## AI/ML 概念

**Few-Shot Learning** - 在提示中提供示例。 [模块 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - 训练于大量文本数据的 AI 模型。

**Reasoning Effort** - GPT-5 控制推理深度的参数。 [模块 02](../02-prompt-engineering/README.md)

**Temperature** - 控制输出随机性。低=确定性，高=创造性。

**Vector Database** - 专门用于嵌入的数据库。 [模块 03](../03-rag/README.md)

**Zero-Shot Learning** - 无示例完成任务。 [模块 02](../02-prompt-engineering/README.md)

## 护栏 - [模块 00](../00-quick-start/README.md)

**Defense in Depth** - 多层安全策略，结合应用级护栏和供应商安全过滤器。

**Hard Block** - 供应商针对严重内容违规返回 HTTP 400 错误。

**InputGuardrail** - LangChain4j 验证用户输入接口，在到达 LLM 前拦截，节约成本和时延。

**InputGuardrailResult** - 护栏验证返回类型：`success()` 或 `fatal("reason")`。

**OutputGuardrail** - 验证 AI 响应接口，返回用户前检查内容。

**Provider Safety Filters** - AI 服务商内置内容过滤器（例如 GitHub Models），在 API 级别检测违规。

**Soft Refusal** - 模型礼貌拒绝回答且不抛出错误。

## 提示工程 - [模块 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - 逐步推理，提高准确性。

**Constrained Output** - 强制输出特定格式或结构。

**High Eagerness** - GPT-5 用于详尽推理的模式。

**Low Eagerness** - GPT-5 用于快速回答的模式。

**Multi-Turn Conversation** - 跨轮保持上下文。

**Role-Based Prompting** - 通过系统消息设置模型角色。

**Self-Reflection** - 模型自评并改进输出。

**Structured Analysis** - 固定评价框架。

**Task Execution Pattern** - 计划 → 执行 → 总结。

## RAG（检索增强生成） - [模块 03](../03-rag/README.md)

**Document Processing Pipeline** - 加载 → 拆分 → 嵌入 → 存储。

**In-Memory Embedding Store** - 非持久化存储，用于测试。

**RAG** - 结合检索和生成以提供有依据的回答。

**Similarity Score** - 语义相似度度量（0-1）。

**Source Reference** - 检索内容的元数据。

## 代理和工具 - [模块 04](../04-tools/README.md)

**@Tool 注解** - 标记 Java 方法为可 AI 调用的工具。

**ReAct Pattern** - 推理 → 行动 → 观察 → 重复。

**Session Management** - 为不同用户分离上下文。

**Tool** - AI 代理可调用的功能。

**Tool Description** - 工具目的及参数文档。

## 代理模块 - [模块 05](../05-mcp/README.md)

**@Agent 注解** - 标记接口为 AI 代理，支持声明式行为定义。

**Agent Listener** - 通过 `beforeAgentInvocation()` 和 `afterAgentInvocation()` 监控代理执行的钩子。

**Agentic Scope** - 代理共享内存，代理使用 `outputKey` 存储输出供后续代理消费。

**AgenticServices** - 使用 `agentBuilder()` 和 `supervisorBuilder()` 创建代理的工厂。

**Conditional Workflow** - 基于条件路由到不同专家代理。

**Human-in-the-Loop** - 添加人工检查点的工作流模式，用于审批或内容审核。

**langchain4j-agentic** - 声明式代理构建的 Maven 依赖（实验性）。

**Loop Workflow** - 迭代执行代理直到满足条件（如质量分≥0.8）。

**outputKey** - 代理注解参数，指定结果在 Agentic Scope 中的存储位置。

**Parallel Workflow** - 同时运行多个代理处理独立任务。

**Response Strategy** - 主管如何形成最终答案：LAST（最后一个）、SUMMARY（汇总）或 SCORED（评分）。

**Sequential Workflow** - 按顺序执行代理，输出流向下一步。

**Supervisor Agent Pattern** - 高级代理模式，主管 LLM 动态决定调用哪些子代理。

## 模型上下文协议 (MCP) - [模块 05](../05-mcp/README.md)

**langchain4j-mcp** - LangChain4j 中的 MCP 集成 Maven 依赖。

**MCP** - 模型上下文协议：连接 AI 应用到外部工具的标准。一次构建，到处使用。

**MCP Client** - 连接 MCP 服务器以发现和使用工具的应用。

**MCP Server** - 通过 MCP 暴露工具的服务，带有清晰的描述和参数模式。

**McpToolProvider** - LangChain4j 组件，包装 MCP 工具供 AI 服务和代理使用。

**McpTransport** - MCP 通信接口。实现包括 Stdio 和 HTTP。

**Stdio Transport** - 通过 stdin/stdout 的本地进程传输。适用于文件系统访问或命令行工具。

**StdioMcpTransport** - LangChain4j 实现，作为子进程启动 MCP 服务器。

**Tool Discovery** - 客户端查询服务器获取可用工具及其描述和模式。

## Azure 服务 - [模块 01](../01-introduction/README.md)

**Azure AI Search** - 具备向量能力的云搜索。 [模块 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 部署 Azure 资源。

**Azure OpenAI** - 微软的企业 AI 服务。

**Bicep** - Azure 基础设施即代码语言。 [基础设施指南](../01-introduction/infra/README.md)

**Deployment Name** - Azure 中模型部署的名称。

**GPT-5** - 最新的 OpenAI 模型，带推理控制。 [模块 02](../02-prompt-engineering/README.md)

## 测试与开发 - [测试指南](TESTING.md)

**Dev Container** - 容器化开发环境。 [配置](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 免费 AI 模型试验场。 [模块 00](../00-quick-start/README.md)

**In-Memory Testing** - 使用内存存储进行测试。

**Integration Testing** - 使用真实基础设施进行测试。

**Maven** - Java 构建自动化工具。

**Mockito** - Java 模拟框架。

**Spring Boot** - Java 应用框架。 [模块 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免责声明**：  
本文档使用AI翻译服务[Co-op Translator](https://github.com/Azure/co-op-translator)进行翻译。虽然我们努力追求准确性，但请注意自动翻译可能包含错误或不准确之处。原始文档的母语版本应被视为权威来源。对于关键信息，建议使用专业人工翻译。因使用此翻译而引起的任何误解或误释，我们概不负责。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->