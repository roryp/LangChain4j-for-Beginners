<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T21:07:47+00:00",
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
- [RAG (检索增强生成)](../../../docs)
- [Agent 与工具](../../../docs)
- [模型上下文协议 (MCP)](../../../docs)
- [Azure 服务](../../../docs)
- [测试与开发](../../../docs)

本课程中使用的术语和概念速查。

## 核心概念

**AI 代理** - 使用 AI 进行推理并自主行动的系统。 [模块 04](../04-tools/README.md)

**链** - 一系列操作，输出作为下一步的输入。

**分块** - 将文档拆分为更小的片段。典型：300-500 令牌并带重叠。 [模块 03](../03-rag/README.md)

**上下文窗口** - 模型可以处理的最大令牌数。GPT-5：400K 令牌。

**嵌入向量** - 表示文本含义的数值向量。 [模块 03](../03-rag/README.md)

**函数调用** - 模型生成结构化请求以调用外部函数。 [模块 04](../04-tools/README.md)

**幻觉** - 模型生成不正确但看似合理的信息时的现象。

**提示** - 传递给语言模型的文本输入。 [模块 02](../02-prompt-engineering/README.md)

**语义搜索** - 使用嵌入按含义而非关键字进行搜索。 [模块 03](../03-rag/README.md)

**有状态 vs 无状态** - 无状态：没有记忆。有状态：维护对话历史。 [模块 01](../01-introduction/README.md)

**令牌** - 模型处理的基本文本单元。影响成本和限制。 [模块 01](../01-introduction/README.md)

**工具链** - 顺序执行工具，输出用于指导下一次调用。 [模块 04](../04-tools/README.md)

## LangChain4j 组件

**AiServices** - 创建类型安全的 AI 服务接口。

**OpenAiOfficialChatModel** - 用于 OpenAI 和 Azure OpenAI 模型的统一客户端。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI Official 客户端创建嵌入（支持 OpenAI 和 Azure OpenAI）。

**ChatModel** - 语言模型的核心接口。

**ChatMemory** - 维护对话历史。

**ContentRetriever** - 为 RAG 查找相关文档片段。

**DocumentSplitter** - 将文档拆分为片段。

**EmbeddingModel** - 将文本转换为数值向量。

**EmbeddingStore** - 存储并检索嵌入。

**MessageWindowChatMemory** - 维护最近消息的滑动窗口。

**PromptTemplate** - 使用 `{{variable}}` 占位符创建可重用提示。

**TextSegment** - 带元数据的文本片段。用于 RAG。

**ToolExecutionRequest** - 表示工具执行请求。

**UserMessage / AiMessage / SystemMessage** - 对话消息类型。

## AI/ML 概念

**少样本学习** - 在提示中提供示例。 [模块 02](../02-prompt-engineering/README.md)

**大型语言模型 (LLM)** - 在大量文本数据上训练的 AI 模型。

**推理强度** - 控制思考深度的 GPT-5 参数。 [模块 02](../02-prompt-engineering/README.md)

**温度** - 控制输出随机性。低=确定性，高=富有创造性。

**向量数据库** - 专门用于嵌入的数据库。 [模块 03](../03-rag/README.md)

**零样本学习** - 在没有示例的情况下执行任务。 [模块 02](../02-prompt-engineering/README.md)

## 提示工程 - [模块 02](../02-prompt-engineering/README.md)

**Chain-of-Thought（连锁思维）** - 逐步推理以提高准确性。

**受限输出** - 强制特定格式或结构。

**高参与度** - GPT-5 用于深入推理的模式。

**低参与度** - GPT-5 用于快速回答的模式。

**多轮对话** - 在多次交流中维护上下文。

**基于角色的提示** - 通过系统消息设置模型人格。

**自我反思** - 模型评估并改进其输出。

**结构化分析** - 固定的评估框架。

**任务执行模式** - 计划 → 执行 → 总结。

## RAG (检索增强生成) - [模块 03](../03-rag/README.md)

**文档处理管道** - 加载 → 分块 → 嵌入 → 存储。

**内存内嵌入存储** - 用于测试的非持久性存储。

**RAG** - 将检索与生成结合以使响应有据可查。

**相似度得分** - 语义相似度的度量（0-1）。

**来源引用** - 检索内容的元数据。

## Agent 与工具 - [模块 04](../04-tools/README.md)

**@Tool 注解** - 将 Java 方法标记为可由 AI 调用的工具。

**ReAct 模式** - 推理 → 行动 → 观察 → 重复。

**会话管理** - 为不同用户分离上下文。

**工具** - AI 代理可以调用的函数。

**工具描述** - 关于工具用途和参数的文档。

## 模型上下文协议 (MCP) - [模块 05](../05-mcp/README.md)

**MCP** - 将 AI 应用连接到外部工具的标准。

**MCP Client** - 连接到 MCP 服务器的应用程序。

**MCP Server** - 通过 MCP 暴露工具的服务。

**Stdio 传输** - 通过 stdin/stdout 作为子进程的服务器。

**工具发现** - 客户端查询服务器以获取可用工具。

## Azure 服务 - [模块 01](../01-introduction/README.md)

**Azure AI Search** - 具有向量功能的云搜索。 [模块 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 部署 Azure 资源的工具。

**Azure OpenAI** - Microsoft 的企业级 AI 服务。

**Bicep** - Azure 基础设施即代码语言。 [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - 在 Azure 中模型部署的名称。

**GPT-5** - 具有推理控制的最新 OpenAI 模型。 [模块 02](../02-prompt-engineering/README.md)

## 测试与开发 - [测试指南](TESTING.md)

**开发容器** - 容器化的开发环境。 [配置](../../../.devcontainer/devcontainer.json)

**GitHub 模型** - 免费的 AI 模型试验场。 [模块 00](../00-quick-start/README.md)

**内存内测试** - 使用内存存储进行测试。

**集成测试** - 使用真实基础设施的测试。

**Maven** - Java 构建自动化工具。

**Mockito** - Java 模拟框架。

**Spring Boot** - Java 应用框架。 [模块 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免责声明：

本文件通过 AI 翻译服务 Co-op Translator (https://github.com/Azure/co-op-translator) 翻译。虽然我们力求准确，但请注意自动翻译可能包含错误或不准确之处。原始语言版本的文档应被视为权威来源。对于重要信息，建议采用专业人工翻译。对于因使用本翻译而产生的任何误解或误释，我们概不负责。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->