<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f89f4c106d110e4943c055dd1a2f1dff",
  "translation_date": "2025-12-30T21:02:52+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "zh"
}
-->
# 模块 05：模型上下文协议 (MCP)

## 目录

- [你将学到什么](../../../05-mcp)
- [什么是 MCP？](../../../05-mcp)
- [MCP 的工作原理](../../../05-mcp)
- [Agentic 模块](../../../05-mcp)
- [运行示例](../../../05-mcp)
  - [先决条件](../../../05-mcp)
- [快速开始](../../../05-mcp)
  - [文件操作（Stdio）](../../../05-mcp)
  - [监督代理](../../../05-mcp)
    - [理解输出](../../../05-mcp)
    - [Agentic 模块功能说明](../../../05-mcp)
- [关键概念](../../../05-mcp)
- [恭喜！](../../../05-mcp)
  - [接下来做什么？](../../../05-mcp)

## 你将学到什么

你已经构建了会话式 AI，掌握了 prompt，能让响应基于文档，并创建了带有工具的代理。但所有这些工具都是为你的特定应用定制构建的。如果你能让你的 AI 访问一个任何人都可以创建和共享的标准化工具生态系统，会怎样？在本模块中，你将学习如何使用模型上下文协议（MCP）和 LangChain4j 的 agentic 模块来实现这一点。我们首先展示一个简单的 MCP 文件读取器，然后演示它如何轻松集成到使用 Supervisor Agent 模式的高级 agentic 工作流中。

## 什么是 MCP？

模型上下文协议（MCP）正是为此而生 —— 为 AI 应用发现和使用外部工具提供了一种标准方式。不再需要为每个数据源或服务编写定制集成；你可以连接到以一致格式公开其功能的 MCP 服务器。你的 AI 代理随后可以自动发现并使用这些工具。

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.zh.png" alt="MCP 对比" width="800"/>

*在 MCP 之前：复杂的点对点集成。在 MCP 之后：一个协议，无限可能。*

MCP 解决了 AI 开发中的一个根本问题：每个集成都各不相同。想访问 GitHub？自定义代码。想读取文件？自定义代码。想查询数据库？自定义代码。而且这些集成通常无法与其他 AI 应用互通。

MCP 将这一切标准化。MCP 服务器以清晰的描述和参数 schema 暴露工具。任何 MCP 客户端都可以连接、发现可用工具并使用它们。一次构建，到处使用。

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.zh.png" alt="MCP 架构" width="800"/>

*模型上下文协议架构 - 标准化的工具发现与执行*

## MCP 的工作原理

**服务器-客户端 架构**

MCP 使用客户端-服务器模型。服务器提供工具 —— 读取文件、查询数据库、调用 API。客户端（你的 AI 应用）连接到服务器并使用其工具。

要在 LangChain4j 中使用 MCP，请添加以下 Maven 依赖：

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**工具发现**

当你的客户端连接到 MCP 服务器时，它会询问“你有哪些工具？”服务器会返回可用工具列表，每个工具带有描述和参数 schema。你的 AI 代理可以根据用户请求决定使用哪些工具。

**传输机制**

MCP 支持不同的传输机制。本模块演示用于本地进程的 Stdio 传输：

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.zh.png" alt="传输机制" width="800"/>

*MCP 传输机制：远程服务器使用 HTTP，本地进程使用 Stdio*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

用于本地进程。你的应用作为子进程启动一个服务器，并通过标准输入/输出进行通信。适用于文件系统访问或命令行工具。

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@2025.12.18",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 通过 [GitHub Copilot](https://github.com/features/copilot) 聊天试试：** 打开 [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) 并询问：
> - “Stdio 传输如何工作？我什么时候应使用它而不是 HTTP？”
> - “LangChain4j 如何管理已启动的 MCP 服务器进程的生命周期？”
> - “赋予 AI 访问文件系统的权限有哪些安全含义？”

## Agentic 模块

虽然 MCP 提供了标准化的工具，但 LangChain4j 的 **agentic 模块** 提供了一种声明式方式来构建协调这些工具的代理。`@Agent` 注释和 `AgenticServices` 让你通过接口而不是命令式代码来定义代理行为。

在本模块中，你将探索 **监督代理（Supervisor Agent）** 模式 —— 一种高级的 agentic AI 方法，其中“监督者”代理根据用户请求动态决定调用哪些子代理。我们将结合这两个概念，为我们的一个子代理提供基于 MCP 的文件访问能力。

要使用 agentic 模块，请添加以下 Maven 依赖：

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ 实验性：** `langchain4j-agentic` 模块为 **实验性**，可能会发生变化。构建 AI 助手的稳定方式仍然是使用 `langchain4j-core` 并结合自定义工具（模块 04）。

## 运行示例

### 先决条件

- Java 21+、Maven 3.9+
- Node.js 16+ 和 npm（用于 MCP 服务器）
- 在根目录的 `.env` 文件中配置环境变量：
  - **用于 StdioTransportDemo：** `GITHUB_TOKEN`（GitHub 个人访问令牌）
  - **用于 SupervisorAgentDemo：** `AZURE_OPENAI_ENDPOINT`、`AZURE_OPENAI_API_KEY`、`AZURE_OPENAI_DEPLOYMENT`（与模块 01-04 相同）

> **注意：** 如果你尚未设置环境变量，请参见 [Module 00 - Quick Start](../00-quick-start/README.md) 获取说明，或者将根目录下的 `.env.example` 复制为 `.env` 并填写你的值。

## 快速开始

**使用 VS Code：** 在资源管理器中右键单击任何演示文件并选择 **“运行 Java”**，或者使用“运行和调试”面板中的启动配置（请先确保已在 `.env` 文件中添加了你的令牌）。

**使用 Maven：** 或者，你也可以使用下面的示例从命令行运行。

### 文件操作（Stdio）

此示例演示基于本地子进程的工具。

**✅ 无需任何先决条件** —— MCP 服务器会自动生成。

**使用 VS Code：** 右键单击 `StdioTransportDemo.java` 并选择 **“运行 Java”**。

**使用 Maven：**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

应用会自动生成一个文件系统 MCP 服务器并读取本地文件。注意子进程管理是如何为你处理的。

**预期输出：**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### 监督代理

<img src="../../../translated_images/agentic.cf84dcda226374e3.zh.png" alt="Agentic 模块" width="800"/>


**监督代理模式** 是一种 **灵活** 的 agentic AI 形式。不同于确定性的工作流（顺序、循环、并行），监督者使用 LLM 根据用户请求自主决定调用哪些代理。

**结合监督者与 MCP：** 在此示例中，我们通过 `toolProvider(mcpToolProvider)` 为 `FileAgent` 提供 MCP 文件系统工具访问。当用户要求“读取并分析一个文件”时，监督者会分析请求并生成执行计划。然后它将请求路由到 `FileAgent`，`FileAgent` 使用 MCP 的 `read_file` 工具检索内容。监督者将该内容传递给 `AnalysisAgent` 进行解读，并可选择调用 `SummaryAgent` 来凝练结果。

这展示了 MCP 工具如何无缝集成到 agentic 工作流中 —— 监督者不需要知道文件是如何被读取的，只需知道 `FileAgent` 可以完成此操作。监督者可以根据不同类型的请求动态调整，并返回最后一个代理的响应或所有操作的摘要。

**使用启动脚本（推荐）：**

启动脚本会自动从根目录的 `.env` 文件加载环境变量：

**Bash:**
```bash
cd 05-mcp
chmod +x start.sh
./start.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start.ps1
```

**使用 VS Code：** 右键单击 `SupervisorAgentDemo.java` 并选择 **“运行 Java”**（确保已配置你的 `.env` 文件）。

**监督者如何工作：**

```java
// 定义多个具有特定能力的代理
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // 具有用于文件操作的 MCP 工具
        .build();

AnalysisAgent analysisAgent = AgenticServices.agentBuilder(AnalysisAgent.class)
        .chatModel(model)
        .build();

SummaryAgent summaryAgent = AgenticServices.agentBuilder(SummaryAgent.class)
        .chatModel(model)
        .build();

// 创建一个负责协调这些代理的监督者
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)  // “planner” 模型
        .subAgents(fileAgent, analysisAgent, summaryAgent)
        .responseStrategy(SupervisorResponseStrategy.SUMMARY)
        .build();

// 监督者会自主决定调用哪些代理
// 只需传入自然语言请求 - LLM 会规划执行
String response = supervisor.invoke("Read the file at /path/file.txt and analyze it");
```

详见 [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) 获取完整实现。

> **🤖 通过 [GitHub Copilot](https://github.com/features/copilot) 聊天试试：** 打开 [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) 并询问：
> - “监督者如何决定调用哪些代理？”
> - “监督者与顺序工作流模式有什么区别？”
> - “如何自定义监督者的规划行为？”

#### 理解输出

当你运行演示时，你会看到监督者如何协调多个代理的结构化演练。以下是每个部分的含义：

```
======================================================================
  SUPERVISOR AGENT DEMO
======================================================================

This demo shows how a Supervisor Agent orchestrates multiple specialized agents.
The Supervisor uses an LLM to decide which agent to call based on the task.
```

**标题** 介绍了演示并解释了核心概念：监督者使用 LLM（而非硬编码规则）来决定调用哪些代理。

```
--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]     FileAgent     - Reads files using MCP filesystem tools
  [ANALYZE]  AnalysisAgent - Analyzes content for structure, tone, and themes
  [SUMMARY]  SummaryAgent  - Creates concise summaries of content
```

**可用代理** 显示监督者可以选择的三个专门代理。每个代理具有特定能力：
- **FileAgent** 可以使用 MCP 工具读取文件（外部能力）
- **AnalysisAgent** 分析内容（纯 LLM 能力）
- **SummaryAgent** 生成摘要（纯 LLM 能力）

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and analyze what it's about"
```

**用户请求** 显示了所提出的内容。监督者必须解析该请求并决定要调用哪些代理。

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor will now decide which agents to invoke and in what order...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source Java library designed to simplify...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> AnalysisAgent (analyzing content)
  |
  |   Input: LangChain4j is an open-source Java library...
  |
  |   Result: Structure: The content is organized into clear paragraphs that int...
  +-- [OK] AnalysisAgent (analyzing content) completed
```

**监督者编排** 是魔法发生的地方。观看以下过程：
1. 监督者**首先选择了 FileAgent**，因为请求中提到了“读取文件”
2. FileAgent 使用 MCP 的 `read_file` 工具检索文件内容
3. 随后监督者**选择了 AnalysisAgent** 并将文件内容传给它
4. AnalysisAgent 分析了结构、语气和主题

注意监督者是基于用户请求**自主**做出这些决策的 —— 没有硬编码的工作流！

**最终响应** 是监督者综合所有被调用代理输出后的答案。示例会转储 agentic 作用域，显示每个代理存储的摘要和分析结果。

```
--- FINAL RESPONSE ---------------------------------------------------
I read the contents of the file and analyzed its structure, tone, and key themes.
The file introduces LangChain4j as an open-source Java library for integrating
large language models...

--- AGENTIC SCOPE (Shared Memory) ------------------------------------
  Agents store their results in a shared scope for other agents to use:
  * summary: LangChain4j is an open-source Java library...
  * analysis: Structure: The content is organized into clear paragraphs that in...
```

### Agentic 模块功能说明

该示例演示了 agentic 模块的若干高级功能。让我们更仔细地看一下 Agentic Scope 和 Agent Listeners。

**Agentic Scope** 显示代理使用 `@Agent(outputKey="...")` 存储其结果的共享内存。这允许：
- 后续代理访问之前代理的输出
- 监督者综合生成最终响应
- 你检查每个代理产生的内容

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String story = scope.readState("story");
List<AgentInvocation> history = scope.agentInvocations("analysisAgent");
```

**Agent Listeners** 可用于监控和调试代理执行。演示中你看到的逐步输出来自一个在每次代理调用时挂钩的 AgentListener：
- **beforeAgentInvocation** - 当监督者选择一个代理时调用，让你看到被选择的代理以及原因
- **afterAgentInvocation** - 当代理完成时调用，显示其结果
- **inheritedBySubagents** - 若为 true，该监听器会监控层级中的所有代理

```java
AgentListener monitor = new AgentListener() {
    private int step = 0;
    
    @Override
    public void beforeAgentInvocation(AgentRequest request) {
        step++;
        System.out.println("  +-- STEP " + step + ": " + request.agentName());
    }
    
    @Override
    public void afterAgentInvocation(AgentResponse response) {
        System.out.println("  +-- [OK] " + response.agentName() + " completed");
    }
    
    @Override
    public boolean inheritedBySubagents() {
        return true; // 传播到所有子代理
    }
};
```

除了监督者模式外，`langchain4j-agentic` 模块还提供了若干强大的工作流模式和功能：

| 模式 | 描述 | 使用场景 |
|---------|-------------|----------|
| **Sequential** | 按顺序执行代理，输出流向下一个 | 管道：研究 → 分析 → 报告 |
| **Parallel** | 同时运行代理 | 独立任务：天气 + 新闻 + 股票 |
| **Loop** | 迭代直到满足条件 | 质量评分：反复优化直到分数 ≥ 0.8 |
| **Conditional** | 基于条件进行路由 | 分类 → 路由到专门代理 |
| **Human-in-the-Loop** | 添加人工检查点 | 审批工作流、内容审查 |

## 关键概念

**MCP** 适用于你想利用现有工具生态系统、构建可以被多个应用共享的工具、以标准协议集成第三方服务，或在不更改代码的情况下替换工具实现的场景。

**Agentic 模块** 最适合在你希望使用 `@Agent` 注释进行声明式代理定义、需要工作流编排（顺序、循环、并行）、偏好基于接口的代理设计而非命令式代码、或将多个代理结合并通过 `outputKey` 共享输出时使用。

**监督代理模式** 在工作流无法事先预测且你希望由 LLM 做出决策时大放异彩；当你有多个专门化代理需要动态编排、构建将请求路由到不同能力的对话系统，或希望获得最灵活、最自适应的代理行为时，该模式尤为适合。

## 恭喜！

你已完成 LangChain4j 初学者课程。你已学会：

- 如何构建带有记忆的会话式 AI（模块 01）
- 不同任务的提示工程模式（模块 02）
- 使用 RAG 将响应锚定到你的文档（模块 03）
- 使用自定义工具创建基本 AI 代理（助手）（模块 04）
- 将标准化工具与 LangChain4j MCP 和 Agentic 模块集成 (模块 05)

### 下一步？

完成这些模块后，请查看 [测试指南](../docs/TESTING.md) 以了解 LangChain4j 测试概念的实际应用。

**官方资源：**
- [LangChain4j 文档](https://docs.langchain4j.dev/) - 全面指南和 API 参考
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - 源代码和示例
- [LangChain4j 教程](https://docs.langchain4j.dev/tutorials/) - 面向各种用例的逐步教程

感谢您完成本课程！

---

**导航：** [← 上一章：模块 04 - 工具](../04-tools/README.md) | [返回主目录](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免责声明：
本文档已使用人工智能翻译服务 Co-op Translator（https://github.com/Azure/co-op-translator）进行翻译。我们尽力确保准确性，但请注意，自动翻译可能包含错误或不准确之处。原文（其原始语言版本）应被视为权威来源。对于重要信息，建议使用专业人工翻译。因使用本翻译而产生的任何误解或曲解，我们不承担责任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->