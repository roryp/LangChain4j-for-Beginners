<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T19:54:15+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "zh"
}
-->
# LangChain4j 术语表

## 目录

- [核心概念](../../../docs)
- [LangChain4j 组件](../../../docs)
- [AI/ML 概念](../../../docs)
- [提示工程](../../../docs)
- [RAG（检索增强生成）](../../../docs)
- [代理和工具](../../../docs)
- [模型上下文协议（MCP）](../../../docs)
- [Azure 服务](../../../docs)
- [测试与开发](../../../docs)

课程中使用的术语和概念快速参考。

## 核心概念

**AI Agent** - 使用 AI 进行推理和自主行动的系统。 [模块 04](../04-tools/README.md)

**Chain** - 一系列操作，输出作为下一步的输入。

**Chunking** - 将文档拆分成更小的片段。典型大小：300-500 令牌，带重叠。 [模块 03](../03-rag/README.md)

**Context Window** - 模型可处理的最大令牌数。GPT-5：400K 令牌。

**Embeddings** - 表示文本含义的数值向量。 [模块 03](../03-rag/README.md)

**Function Calling** - 模型生成结构化请求以调用外部函数。 [模块 04](../04-tools/README.md)

**Hallucination** - 模型生成错误但看似合理的信息。

**Prompt** - 语言模型的文本输入。 [模块 02](../02-prompt-engineering/README.md)

**Semantic Search** - 使用嵌入向量按含义搜索，而非关键词。 [模块 03](../03-rag/README.md)

**Stateful vs Stateless** - 无状态：无记忆。有状态：维护对话历史。 [模块 01](../01-introduction/README.md)

**Tokens** - 模型处理的基本文本单位。影响成本和限制。 [模块 01](../01-introduction/README.md)

**Tool Chaining** - 顺序执行工具，输出用于下一次调用。 [模块 04](../04-tools/README.md)

## LangChain4j 组件

**AiServices** - 创建类型安全的 AI 服务接口。

**OpenAiOfficialChatModel** - OpenAI 和 Azure OpenAI 模型的统一客户端。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI 官方客户端创建嵌入（支持 OpenAI 和 Azure OpenAI）。

**ChatModel** - 语言模型的核心接口。

**ChatMemory** - 维护对话历史。

**ContentRetriever** - 查找与 RAG 相关的文档片段。

**DocumentSplitter** - 将文档拆分成片段。

**EmbeddingModel** - 将文本转换为数值向量。

**EmbeddingStore** - 存储和检索嵌入。

**MessageWindowChatMemory** - 维护最近消息的滑动窗口。

**PromptTemplate** - 使用 `{{variable}}` 占位符创建可重用提示。

**TextSegment** - 带元数据的文本片段。用于 RAG。

**ToolExecutionRequest** - 表示工具执行请求。

**UserMessage / AiMessage / SystemMessage** - 对话消息类型。

## AI/ML 概念

**Few-Shot Learning** - 在提示中提供示例。 [模块 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - 基于大量文本数据训练的 AI 模型。

**Reasoning Effort** - GPT-5 控制思考深度的参数。 [模块 02](../02-prompt-engineering/README.md)

**Temperature** - 控制输出随机性。低=确定性，高=创造性。

**Vector Database** - 专门用于嵌入的数据库。 [模块 03](../03-rag/README.md)

**Zero-Shot Learning** - 无需示例执行任务。 [模块 02](../02-prompt-engineering/README.md)

## 提示工程 - [模块 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - 逐步推理以提高准确性。

**Constrained Output** - 强制特定格式或结构。

**High Eagerness** - GPT-5 用于彻底推理的模式。

**Low Eagerness** - GPT-5 用于快速回答的模式。

**Multi-Turn Conversation** - 跨轮次维护上下文。

**Role-Based Prompting** - 通过系统消息设置模型角色。

**Self-Reflection** - 模型自我评估并改进输出。

**Structured Analysis** - 固定的评估框架。

**Task Execution Pattern** - 计划 → 执行 → 总结。

## RAG（检索增强生成）- [模块 03](../03-rag/README.md)

**Document Processing Pipeline** - 加载 → 拆分 → 嵌入 → 存储。

**In-Memory Embedding Store** - 用于测试的非持久化存储。

**RAG** - 结合检索与生成以提供有依据的回答。

**Similarity Score** - 语义相似度的度量（0-1）。

**Source Reference** - 检索内容的元数据。

## 代理和工具 - [模块 04](../04-tools/README.md)

**@Tool Annotation** - 标记 Java 方法为 AI 可调用工具。

**ReAct Pattern** - 推理 → 行动 → 观察 → 重复。

**Session Management** - 为不同用户分离上下文。

**Tool** - AI 代理可调用的功能。

**Tool Description** - 工具用途和参数的文档说明。

## 模型上下文协议（MCP）- [模块 05](../05-mcp/README.md)

**Docker Transport** - 运行在 Docker 容器中的 MCP 服务器。

**MCP** - 连接 AI 应用与外部工具的标准。

**MCP Client** - 连接 MCP 服务器的应用。

**MCP Server** - 通过 MCP 暴露工具的服务。

**Server-Sent Events (SSE)** - 服务器通过 HTTP 向客户端推送流。

**Stdio Transport** - 通过 stdin/stdout 作为子进程的服务器。

**Streamable HTTP Transport** - 支持 SSE 的 HTTP 实时通信。

**Tool Discovery** - 客户端查询服务器可用工具。

## Azure 服务 - [模块 01](../01-introduction/README.md)

**Azure AI Search** - 具备向量功能的云搜索。 [模块 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 部署 Azure 资源。

**Azure OpenAI** - 微软的企业 AI 服务。

**Bicep** - Azure 基础设施即代码语言。 [基础设施指南](../01-introduction/infra/README.md)

**Deployment Name** - Azure 中模型部署的名称。

**GPT-5** - 具备推理控制的最新 OpenAI 模型。 [模块 02](../02-prompt-engineering/README.md)

## 测试与开发 - [测试指南](TESTING.md)

**Dev Container** - 容器化开发环境。 [配置](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 免费的 AI 模型游乐场。 [模块 00](../00-quick-start/README.md)

**In-Memory Testing** - 使用内存存储进行测试。

**Integration Testing** - 使用真实基础设施进行测试。

**Maven** - Java 构建自动化工具。

**Mockito** - Java 模拟框架。

**Spring Boot** - Java 应用框架。 [模块 01](../01-introduction/README.md)

**Testcontainers** - 测试中的 Docker 容器。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免责声明**：  
本文件由人工智能翻译服务 [Co-op Translator](https://github.com/Azure/co-op-translator) 翻译而成。尽管我们力求准确，但请注意，自动翻译可能包含错误或不准确之处。原始文件的母语版本应被视为权威来源。对于重要信息，建议使用专业人工翻译。因使用本翻译而产生的任何误解或误释，我们概不负责。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->