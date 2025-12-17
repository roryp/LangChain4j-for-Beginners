<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T14:35:42+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "zh"
}
-->
# Module 00: 快速开始

## 目录

- [介绍](../../../00-quick-start)
- [什么是 LangChain4j？](../../../00-quick-start)
- [LangChain4j 依赖](../../../00-quick-start)
- [先决条件](../../../00-quick-start)
- [设置](../../../00-quick-start)
  - [1. 获取你的 GitHub 令牌](../../../00-quick-start)
  - [2. 设置你的令牌](../../../00-quick-start)
- [运行示例](../../../00-quick-start)
  - [1. 基础聊天](../../../00-quick-start)
  - [2. 提示模式](../../../00-quick-start)
  - [3. 函数调用](../../../00-quick-start)
  - [4. 文档问答（RAG）](../../../00-quick-start)
- [每个示例展示内容](../../../00-quick-start)
- [下一步](../../../00-quick-start)
- [故障排除](../../../00-quick-start)

## 介绍

本快速入门旨在帮助你尽快使用 LangChain4j 启动运行。它涵盖了使用 LangChain4j 和 GitHub 模型构建 AI 应用的绝对基础。在后续模块中，你将使用 Azure OpenAI 和 LangChain4j 构建更高级的应用。

## 什么是 LangChain4j？

LangChain4j 是一个简化构建 AI 驱动应用的 Java 库。你无需处理 HTTP 客户端和 JSON 解析，而是使用简洁的 Java API。

LangChain 中的“链”指的是将多个组件串联起来——你可能将提示链到模型，再链到解析器，或者将多个 AI 调用串联起来，一个输出作为下一个输入。本快速入门专注于基础知识，之后会探索更复杂的链。

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1e961a13c73d45cfa305fd03d81bd11e5d34d0e36ed28a44d.zh.png" alt="LangChain4j Chaining Concept" width="800"/>

*LangChain4j 中的组件链——构建块连接以创建强大的 AI 工作流*

我们将使用三个核心组件：

**ChatLanguageModel** - AI 模型交互接口。调用 `model.chat("prompt")` 并获得响应字符串。我们使用 `OpenAiOfficialChatModel`，它支持 OpenAI 兼容的端点，如 GitHub 模型。

**AiServices** - 创建类型安全的 AI 服务接口。定义方法，使用 `@Tool` 注解，LangChain4j 负责协调。AI 会在需要时自动调用你的 Java 方法。

**MessageWindowChatMemory** - 维护对话历史。没有它，每个请求都是独立的。有了它，AI 会记住之前的消息，并在多轮对话中保持上下文。

<img src="../../../translated_images/architecture.eedc993a1c57683951f20244f652fc7a9853f571eea835bc2b2828cf0dbf62d0.zh.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j 架构——核心组件协同工作，驱动你的 AI 应用*

## LangChain4j 依赖

本快速入门在 [`pom.xml`](../../../00-quick-start/pom.xml) 中使用了两个 Maven 依赖：

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

`langchain4j-open-ai-official` 模块提供了连接 OpenAI 兼容 API 的 `OpenAiOfficialChatModel` 类。GitHub 模型使用相同的 API 格式，因此无需特殊适配器——只需将基础 URL 指向 `https://models.github.ai/inference`。

## 先决条件

**使用开发容器？** Java 和 Maven 已预装。你只需一个 GitHub 个人访问令牌。

**本地开发：**
- Java 21+，Maven 3.9+
- GitHub 个人访问令牌（见下方说明）

> **注意：** 本模块使用 GitHub 模型中的 `gpt-4.1-nano`。请勿修改代码中的模型名称——它已配置为与 GitHub 可用模型兼容。

## 设置

### 1. 获取你的 GitHub 令牌

1. 访问 [GitHub 设置 → 个人访问令牌](https://github.com/settings/personal-access-tokens)
2. 点击“生成新令牌”
3. 设置描述性名称（例如“LangChain4j 演示”）
4. 设置过期时间（建议 7 天）
5. 在“账户权限”中找到“Models”，设置为“只读”
6. 点击“生成令牌”
7. 复制并保存令牌——之后无法再次查看

### 2. 设置你的令牌

**选项 1：使用 VS Code（推荐）**

如果你使用 VS Code，将令牌添加到项目根目录的 `.env` 文件：

如果 `.env` 文件不存在，复制 `.env.example` 为 `.env`，或在项目根目录新建 `.env` 文件。

**示例 `.env` 文件：**
```bash
# 在 /workspaces/LangChain4j-for-Beginners/.env 中
GITHUB_TOKEN=your_token_here
```

然后你可以在资源管理器中右键点击任意演示文件（如 `BasicChatDemo.java`），选择 **“运行 Java”**，或使用运行和调试面板中的启动配置。

**选项 2：使用终端**

将令牌设置为环境变量：

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## 运行示例

**使用 VS Code：** 只需在资源管理器中右键点击任意演示文件，选择 **“运行 Java”**，或使用运行和调试面板中的启动配置（确保已先将令牌添加到 `.env` 文件）。

**使用 Maven：** 你也可以从命令行运行：

### 1. 基础聊天

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. 提示模式

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

展示零样本、少样本、链式思维和基于角色的提示。

### 3. 函数调用

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI 会在需要时自动调用你的 Java 方法。

### 4. 文档问答（RAG）

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

针对 `document.txt` 中的内容提问。

## 每个示例展示内容

**基础聊天** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

从这里开始，了解 LangChain4j 的最简单用法。你将创建一个 `OpenAiOfficialChatModel`，用 `.chat()` 发送提示，并获得响应。这展示了基础：如何用自定义端点和 API 密钥初始化模型。理解此模式后，其他内容都基于此构建。

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) 聊天尝试：** 打开 [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)，并提问：
> - “如何在代码中将 GitHub 模型切换到 Azure OpenAI？”
> - “OpenAiOfficialChatModel.builder() 还能配置哪些参数？”
> - “如何添加流式响应，而不是等待完整响应？”

**提示工程** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

既然你知道如何与模型对话，我们来探索你对它说什么。此演示使用相同模型设置，但展示了四种不同的提示模式。尝试零样本提示以直接指令，少样本提示通过示例学习，链式思维提示展示推理步骤，基于角色的提示设定上下文。你将看到相同模型根据提示方式产生截然不同的结果。

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

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) 聊天尝试：** 打开 [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)，并提问：
> - “零样本和少样本提示有什么区别，何时使用？”
> - “温度参数如何影响模型响应？”
> - “有哪些技术可以防止生产环境中的提示注入攻击？”
> - “如何为常用模式创建可复用的 PromptTemplate 对象？”

**工具集成** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

这里是 LangChain4j 的强大之处。你将使用 `AiServices` 创建一个 AI 助手，可以调用你的 Java 方法。只需用 `@Tool("描述")` 注解方法，LangChain4j 会处理其余部分——AI 会根据用户请求自动决定何时使用哪个工具。这展示了函数调用，这是构建能执行操作而不仅仅回答问题的 AI 的关键技术。

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) 聊天尝试：** 打开 [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)，并提问：
> - “@Tool 注解如何工作，LangChain4j 在背后做了什么？”
> - “AI 能否顺序调用多个工具解决复杂问题？”
> - “如果工具抛出异常，应该如何处理错误？”
> - “如何集成真实 API，而不是这个计算器示例？”

**文档问答（RAG）** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

这里你将看到 RAG（检索增强生成）的基础。你不是依赖模型的训练数据，而是加载 [`document.txt`](../../../00-quick-start/document.txt) 中的内容并将其包含在提示中。AI 根据你的文档回答，而非其通用知识。这是构建能使用你自己数据的系统的第一步。

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **注意：** 这种简单方法将整个文档加载到提示中。对于大文件（>10KB），会超出上下文限制。模块 03 涵盖了分块和向量搜索，用于生产级 RAG 系统。

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) 聊天尝试：** 打开 [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)，并提问：
> - “RAG 如何防止 AI 幻觉，相较于使用模型训练数据？”
> - “这种简单方法和使用向量嵌入检索有什么区别？”
> - “如何扩展以处理多个文档或更大的知识库？”
> - “如何构建提示以确保 AI 仅使用提供的上下文？”

## 调试

示例中包含 `.logRequests(true)` 和 `.logResponses(true)`，在控制台显示 API 调用。这有助于排查身份验证错误、速率限制或意外响应。生产环境中请移除这些标志以减少日志噪音。

## 下一步

**下一个模块：** [01-introduction - 使用 LangChain4j 和 Azure 上的 gpt-5 入门](../01-introduction/README.md)

---

**导航：** [← 返回主页面](../README.md) | [下一个：模块 01 - 介绍 →](../01-introduction/README.md)

---

## 故障排除

### 第一次 Maven 构建

**问题：** 初次执行 `mvn clean compile` 或 `mvn package` 需要很长时间（10-15 分钟）

**原因：** Maven 需要首次下载所有项目依赖（Spring Boot、LangChain4j 库、Azure SDK 等）。

**解决方案：** 这是正常现象。后续构建会快得多，因为依赖已缓存。下载时间取决于你的网络速度。

### PowerShell Maven 命令语法

**问题：** Maven 命令失败，报错 `Unknown lifecycle phase ".mainClass=..."`

**原因：** PowerShell 将 `=` 解释为变量赋值操作符，破坏了 Maven 属性语法。

**解决方案：** 在 Maven 命令前使用停止解析操作符 `--%`：

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

`--%` 操作符告诉 PowerShell 将后续所有参数原样传递给 Maven，不做解析。

### Windows PowerShell 表情符号显示

**问题：** AI 响应在 PowerShell 中显示乱码（如 `????` 或 `â??`）而非表情符号

**原因：** PowerShell 默认编码不支持 UTF-8 表情符号

**解决方案：** 在执行 Java 应用前运行此命令：
```cmd
chcp 65001
```

这会强制终端使用 UTF-8 编码。或者，使用支持更好 Unicode 的 Windows Terminal。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免责声明**：  
本文件由 AI 翻译服务 [Co-op Translator](https://github.com/Azure/co-op-translator) 翻译而成。尽管我们力求准确，但请注意自动翻译可能存在错误或不准确之处。原始文件的母语版本应被视为权威来源。对于重要信息，建议使用专业人工翻译。因使用本翻译而产生的任何误解或误释，我们概不负责。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->