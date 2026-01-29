<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-05T21:53:18+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "zh"
}
-->
# 模块 03：RAG（检索增强生成）

## 目录

- [你将学到什么](../../../03-rag)
- [先决条件](../../../03-rag)
- [理解 RAG](../../../03-rag)
- [它是如何工作的](../../../03-rag)
  - [文档处理](../../../03-rag)
  - [创建嵌入](../../../03-rag)
  - [语义搜索](../../../03-rag)
  - [答案生成](../../../03-rag)
- [运行应用程序](../../../03-rag)
- [使用应用程序](../../../03-rag)
  - [上传文档](../../../03-rag)
  - [提问](../../../03-rag)
  - [检查来源引用](../../../03-rag)
  - [尝试不同问题](../../../03-rag)
- [关键概念](../../../03-rag)
  - [分块策略](../../../03-rag)
  - [相似度分数](../../../03-rag)
  - [内存存储](../../../03-rag)
  - [上下文窗口管理](../../../03-rag)
- [RAG 何时重要](../../../03-rag)
- [下一步](../../../03-rag)

## 你将学到什么

在之前的模块中，你学会了如何与 AI 进行对话并有效构建提示。但有一个根本限制：语言模型只知道它们训练期间学到的内容。它们无法回答有关你公司政策、项目文档或任何未经过训练信息的问题。

RAG（检索增强生成）解决了这个问题。它不是试图教模型你的信息（这既昂贵又不切实际），而是赋予它搜索你文档的能力。当有人提问时，系统会找到相关信息并将其包含在提示中。模型随后基于检索到的上下文回答问题。

把 RAG 看作是给模型一个参考图书馆。当你提问时，系统：

1. **用户查询** — 你提出一个问题
2. **嵌入** — 将你的问题转换为向量
3. **向量搜索** — 找到相似的文档块
4. **上下文组装** — 将相关块添加到提示中
5. **响应** — LLM 基于上下文生成答案

这样模型的回答就基于你的实际数据，而非依赖其训练知识或凭空编造答案。

<img src="../../../translated_images/zh-CN/rag-architecture.ccb53b71a6ce407f.webp" alt="RAG Architecture" width="800"/>

*RAG 流程 — 从用户查询到语义搜索再到上下文答案生成*

## 先决条件

- 完成模块 01（部署 Azure OpenAI 资源）
- 根目录有 `.env` 文件，包含 Azure 认证信息（由模块 01 中的 `azd up` 创建）

> **注意：** 如果你还未完成模块 01，请先按照那里的部署说明操作。

## 它是如何工作的

### 文档处理

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

当你上传文档时，系统会将其分割成小块——适合模型上下文窗口大小的小片段。这些块稍有重叠，避免界限处丢失上下文。

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```

> **🤖 试试使用 [GitHub Copilot](https://github.com/features/copilot) 聊天：** 打开 [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)，并询问：
> - “LangChain4j 如何将文档拆分成块，为什么重叠很重要？”
> - “不同文档类型的最佳块大小是多少，为什么？”
> - “如何处理多语言或带特殊格式的文档？”

### 创建嵌入

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

每个文档块被转换成一种叫作嵌入的数值表达——本质上是一种捕捉文本含义的数学指纹。内容相似的文本会生成相似的嵌入。

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

<img src="../../../translated_images/zh-CN/vector-embeddings.2ef7bdddac79a327.webp" alt="Vector Embeddings Space" width="800"/>

*文档在嵌入空间中的向量表示——相似内容聚集成簇*

### 语义搜索

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

当你提问时，问题也被转为嵌入。系统将你的问题嵌入与所有文档块的嵌入进行比较。找到含义最相近的块——不仅是关键词匹配，而是真正的语义相似。

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```

> **🤖 试试使用 [GitHub Copilot](https://github.com/features/copilot) 聊天：** 打开 [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)，并询问：
> - “基于嵌入的相似度搜索如何工作？分数由什么决定？”
> - “我应该使用什么相似度阈值？它如何影响结果？”
> - “如果没找到相关文档，我该怎么处理？”

### 答案生成

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

最相关的文档块会被加入到模型的提示中。模型阅读这些具体块，并基于这些信息回答问题。这样能防止“幻觉”——模型只能回答手头的信息。

## 运行应用程序

**验证部署：**

确保根目录存在 `.env` 文件，包含 Azure 认证信息（在模块 01 创建）：
```bash
cat ../.env  # 应该显示 AZURE_OPENAI_ENDPOINT、API_KEY、DEPLOYMENT
```

**启动应用程序：**

> **注意：** 如果你已使用模块 01 中的 `./start-all.sh` 启动所有应用程序，本模块已经运行在端口 8081。你可以跳过下面的启动命令，直接访问 http://localhost:8081。

**选项 1：使用 Spring Boot Dashboard（推荐 VS Code 用户）**

开发容器包含 Spring Boot Dashboard 扩展，提供可视化界面管理所有 Spring Boot 应用。你可以在 VS Code 左侧活动栏找到它（查找 Spring Boot 图标）。

通过 Spring Boot Dashboard，你可以：
- 查看工作区中的所有 Spring Boot 应用
- 一键启动/停止应用
- 实时查看应用日志
- 监控应用状态

只需点击“rag”旁的播放按钮启动此模块，或同时启动所有模块。

<img src="../../../translated_images/zh-CN/dashboard.fbe6e28bf4267ffe.webp" alt="Spring Boot Dashboard" width="400"/>

**选项 2：使用 shell 脚本**

启动所有 Web 应用（模块 01-04）：

**Bash:**
```bash
cd ..  # 从根目录
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # 从根目录开始
.\start-all.ps1
```

或者只启动此模块：

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

这两种脚本会自动从根目录 `.env` 文件加载环境变量，且如果 JAR 不存在，会自动构建。

> **注意：** 如果你想在启动前手动构建所有模块：
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

在浏览器打开 http://localhost:8081 。

**停止命令：**

**Bash:**
```bash
./stop.sh  # 仅此模块
# 或者
cd .. && ./stop-all.sh  # 所有模块
```

**PowerShell:**
```powershell
.\stop.ps1  # 仅此模块
# 或
cd ..; .\stop-all.ps1  # 所有模块
```

## 使用应用程序

该应用提供文档上传和提问的 Web 界面。

<a href="images/rag-homepage.png"><img src="../../../translated_images/zh-CN/rag-homepage.d90eb5ce1b3caa94.webp" alt="RAG Application Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*RAG 应用界面 — 上传文档并提问*

### 上传文档

首先上传文档 — 测试时 TXT 文件效果最佳。本目录下提供了一个 `sample-document.txt`，包含有关 LangChain4j 功能、RAG 实现和最佳实践的信息，非常适合系统测试。

系统会处理文档，将其分块，并为每块创建嵌入。上传时自动完成。

### 提问

现在针对文档内容提出具体问题。尽量问文档中明确陈述的事实。系统会搜索相关块、将它们包含在提示中，并生成答案。

### 检查来源引用

注意每个答案都包含带有相似度分数的来源引用。分数（0 到 1）表示该块与问题的相关程度。分数越高意味着匹配越好。这样你可以核对答案与来源材料。

<a href="images/rag-query-results.png"><img src="../../../translated_images/zh-CN/rag-query-results.6d69fcec5397f355.webp" alt="RAG Query Results" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*查询结果显示答案，附带来源引用和相关度分数*

### 尝试不同问题

试试不同类型的问题：
- 具体事实：“主要主题是什么？”
- 比较：“X 和 Y 有什么区别？”
- 总结：“总结关于 Z 的关键点”

观察相关度分数如何随着问题与文档内容匹配程度变化。

## 关键概念

### 分块策略

文档被拆分为 300 令牌的块，每块重叠 30 令牌。这样的平衡确保每个块有足够上下文有意义，同时够小以便提示中包含多个块。

### 相似度分数

分数范围 0 到 1：
- 0.7-1.0：高度相关，准确匹配
- 0.5-0.7：相关，具备良好上下文
- 低于 0.5：被过滤，差异太大

系统只检索超过最低阈值的块以保证质量。

### 内存存储

此模块为简单起见使用内存存储。重启应用后上传的文档会丢失。生产系统使用持久化向量数据库如 Qdrant 或 Azure AI Search。

### 上下文窗口管理

每个模型都有最大上下文窗口。无法在提示中包含大文档的每个块。系统检索最相关的前 N 个块（默认 5 个），既保证上下文充足又不会超限。

## RAG 何时重要

**适用 RAG 的情况：**
- 回答专有文档相关问题
- 信息经常变更（政策、价格、规格）
- 需要来源归属的准确性
- 内容太大，无法放入单个提示
- 需要可验证、扎实的回答

**不适用 RAG 的情况：**
- 问题需要模型已知的一般知识
- 需要实时数据（RAG 针对上传文档）
- 内容足够小，可以直接包含在提示中

## 下一步

**下一模块：** [04-tools - 带工具的 AI 代理](../04-tools/README.md)

---

**导航：** [← 上一页：模块 02 - 提示工程](../02-prompt-engineering/README.md) | [返回主页](../README.md) | [下一页：模块 04 - 工具 →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免责声明**：  
本文档由人工智能翻译服务[Co-op Translator](https://github.com/Azure/co-op-translator)翻译完成。虽然我们力求准确，但请注意自动翻译可能存在错误或不准确之处。原始文档的原文版本应被视为权威来源。对于重要信息，建议使用专业人工翻译。对于因使用本翻译而产生的任何误解或误释，我们概不负责。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->