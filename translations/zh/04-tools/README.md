<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-05T21:55:20+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "zh"
}
-->
# 模块 04：带工具的 AI 代理

## 目录

- [你将学到什么](../../../04-tools)
- [先决条件](../../../04-tools)
- [理解带工具的 AI 代理](../../../04-tools)
- [工具调用的工作原理](../../../04-tools)
  - [工具定义](../../../04-tools)
  - [决策过程](../../../04-tools)
  - [执行](../../../04-tools)
  - [响应生成](../../../04-tools)
- [工具链](../../../04-tools)
- [运行应用程序](../../../04-tools)
- [使用应用程序](../../../04-tools)
  - [尝试简单的工具使用](../../../04-tools)
  - [测试工具链](../../../04-tools)
  - [查看对话流程](../../../04-tools)
  - [尝试不同的请求](../../../04-tools)
- [关键概念](../../../04-tools)
  - [ReAct 模式（推理与行动）](../../../04-tools)
  - [工具描述很重要](../../../04-tools)
  - [会话管理](../../../04-tools)
  - [错误处理](../../../04-tools)
- [可用工具](../../../04-tools)
- [何时使用基于工具的代理](../../../04-tools)
- [下一步](../../../04-tools)

## 你将学到什么

到目前为止，你已经学会了如何与 AI 进行对话，如何有效构造提示，并让回答基于你的文档。但仍存在一个根本限制：语言模型只能生成文本。它们无法查看天气、执行计算、查询数据库或与外部系统交互。

工具改变了这一点。通过让模型访问它可以调用的函数，你将它从文本生成器转变为可以采取行动的代理。模型决定何时需要工具、使用哪个工具以及传递什么参数。你的代码执行函数并返回结果，模型将该结果纳入其回答中。

## 先决条件

- 已完成模块 01（Azure OpenAI 资源已部署）
- 根目录下有包含 Azure 凭据的 `.env` 文件（由模块 01 中的 `azd up` 创建）

> **注意：** 如果尚未完成模块 01，请先按照该模块中的部署说明操作。

## 理解带工具的 AI 代理

> **📝 注意：** 本模块中的“代理”一词指的是增强了工具调用能力的 AI 助手。这与我们将在[模块 05：MCP](../05-mcp/README.md)介绍的**自主代理 AI**（具备规划、记忆和多步推理能力的自主代理）不同。

带工具的 AI 代理遵循推理与行动模式（ReAct）：

1. 用户提问
2. 代理推理所需信息
3. 代理决定是否需要工具来回答
4. 如果需要，调用合适的工具并传递正确参数
5. 工具执行并返回数据
6. 代理将结果纳入最终回答

<img src="../../../translated_images/zh/react-pattern.86aafd3796f3fd13.png" alt="ReAct Pattern" width="800"/>

*ReAct 模式 —— AI 代理如何在推理与行动之间交替解决问题*

此过程是自动完成的。你定义工具及其描述，模型负责何时以及如何使用它们的决策。

## 工具调用的工作原理

### 工具定义

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

你定义带有清晰描述和参数规格的函数。模型在其系统提示中看到这些描述，从而理解每个工具的功能。

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // 你的天气查询逻辑
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// 助手由 Spring Boot 自动配置：
// - ChatModel Bean
// - 来自 @Component 类的所有 @Tool 方法
// - 用于会话管理的 ChatMemoryProvider
```

> **🤖 试试 [GitHub Copilot](https://github.com/features/copilot) 聊天:** 打开 [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java)，提问：
> - “我如何集成真实天气 API（如 OpenWeatherMap）来替代模拟数据？”
> - “什么样的工具描述能帮助 AI 正确使用它？”
> - “如何处理工具实现中的 API 错误和速率限制？”

### 决策过程

当用户问“西雅图的天气怎么样？”时，模型识别出需要使用天气工具，并生成一个带有位置参数“Seattle”的函数调用。

### 执行

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot 自动为声明性的 `@AiService` 接口注入所有注册工具，LangChain4j 自动执行工具调用。

> **🤖 试试 [GitHub Copilot](https://github.com/features/copilot) 聊天：** 打开 [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)，提问：
> - “ReAct 模式如何工作？为什么它对 AI 代理有效？”
> - “代理如何决定使用哪个工具及调用顺序？”
> - “如果工具执行失败，会发生什么？如何健壮地处理错误？”

### 响应生成

模型接收天气数据，并将其格式化为自然语言回复给用户。

### 为什么使用声明式 AI 服务？

本模块使用 LangChain4j 的 Spring Boot 集成和声明式 `@AiService` 接口：

- **Spring Boot 自动注入** ——  自动注入 ChatModel 和工具
- **@MemoryId 模式** —— 自动基于会话的内存管理
- **单实例** —— 助手只创建一次，提升性能
- **类型安全执行** —— 直接调用 Java 方法并进行类型转换
- **多轮编排** —— 自动处理工具链调用
- **零样板代码** —— 无需手动调用 AiServices.builder() 或维护内存 HashMap

手动 `AiServices.builder()` 的方案需要更多代码，且无法享受 Spring Boot 集成的优势。

## 工具链

**工具链**——AI 可能会依次调用多个工具。比如问“西雅图的天气如何？我需要带伞吗？”它会先调用 `getCurrentWeather`，然后根据是否下雨判断是否需要雨具。

<a href="images/tool-chaining.png"><img src="../../../translated_images/zh/tool-chaining.3b25af01967d6f7b.png" alt="Tool Chaining" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*顺序调用工具 —— 一个工具的输出作为下一个决策的输入*

**优雅失败**——请求一个模拟数据中无的城市天气，工具返回错误消息，AI 解释无法提供帮助。工具安全失败。

这在一次对话轮次内完成。代理自主编排多次工具调用。

## 运行应用程序

**验证部署：**

确保根目录存在 `.env` 文件，包含 Azure 凭据（模块 01 部署时创建）：
```bash
cat ../.env  # 应显示 AZURE_OPENAI_ENDPOINT、API_KEY、DEPLOYMENT
```

**启动应用程序：**

> **注意：** 如果你已通过模块 01 中的 `./start-all.sh` 启动所有应用程序，本模块已运行在端口 8084。可跳过下列启动命令，直接访问 http://localhost:8084。

**选项 1：使用 Spring Boot Dashboard（推荐 VS Code 用户）**

开发容器包含 Spring Boot Dashboard 扩展，提供可视界面管理所有 Spring Boot 应用。在 VS Code 左侧活动栏中找到（点击 Spring Boot 图标）。

通过 Spring Boot Dashboard 你可以：
- 查看工作区内所有可用 Spring Boot 应用
- 一键启动/停止应用
- 实时查看应用日志
- 监控应用状态

点击 “tools” 旁的播放按钮启动本模块，或一次启动所有模块。

<img src="../../../translated_images/zh/dashboard.9b519b1a1bc1b30a.png" alt="Spring Boot Dashboard" width="400"/>

**选项 2：使用 Shell 脚本**

启动所有 Web 应用（模块 01-04）：

**Bash:**
```bash
cd ..  # 从根目录
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # 从根目录
.\start-all.ps1
```

或仅启动本模块：

**Bash:**
```bash
cd 04-tools
./start.sh
```

**PowerShell:**
```powershell
cd 04-tools
.\start.ps1
```

两个脚本会自动从根目录 `.env` 文件加载环境变量，若 JAR 包不存在会自动构建。

> **注意：** 如果你想手动构建所有模块然后启动：
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

在浏览器中打开 http://localhost:8084。

**停止应用程序：**

**Bash:**
```bash
./stop.sh  # 仅此模块
# 或
cd .. && ./stop-all.sh  # 所有模块
```

**PowerShell:**
```powershell
.\stop.ps1  # 仅此模块
# 或
cd ..; .\stop-all.ps1  # 所有模块
```


## 使用应用程序

该应用提供一个网页界面，让你与具有天气和温度转换工具的 AI 代理交互。

<a href="images/tools-homepage.png"><img src="../../../translated_images/zh/tools-homepage.4b4cd8b2717f9621.png" alt="AI Agent Tools Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI 代理工具界面 —— 快速示例和聊天界面供交互使用工具*

### 尝试简单的工具使用

从简单请求开始：“将 100 华氏度转换为摄氏度”。代理识别出需要温度转换工具，调用它并返回结果。感受其自然——你无需指定使用哪个工具或如何调用。

### 测试工具链

尝试更复杂的：“西雅图天气如何，并转换为华氏度？”观察代理分步骤处理：先获取天气（返回摄氏度），识别需要转换单位，调用转换工具，最后合并结果回答。

### 查看对话流程

聊天界面保留对话历史，支持多轮交互。可查看所有过往提问和回答，易于追踪对话和理解代理如何建立多轮上下文。

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/zh/tools-conversation-demo.89f2ce9676080f59.png" alt="Conversation with Multiple Tool Calls" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*多轮对话展示简单转换、天气查询和工具链调用*

### 尝试不同的请求

试试多样组合：
- 天气查询：“东京天气怎么样？”
- 温度转换：“25°C 等于多少开尔文？”
- 组合查询：“查询巴黎天气，告诉我是否超过20°C”

注意代理如何解析自然语言并映射到合适的工具调用。

## 关键概念

### ReAct 模式（推理与行动）

代理在推理（决定做什么）和行动（使用工具）间交替。此模式使其能自主解决问题，而不仅仅是响应指令。

### 工具描述很重要

工具描述的质量直接影响代理的使用效果。清晰、具体的描述帮助模型理解何时及如何调用该工具。

### 会话管理

`@MemoryId` 注解启用自动的基于会话的内存管理。每个会话 ID 拥有独立的 `ChatMemory` 实例，由 `ChatMemoryProvider` 管理，无需手动跟踪内存。

### 错误处理

工具可能失败——API 超时、参数无效、外部服务宕机。生产环境代理需要健壮的错误处理，使模型能够解释问题或尝试替代方案。

## 可用工具

**天气工具**（用于示范的模拟数据）：
- 获取指定地点当前天气
- 获取多日天气预报

**温度转换工具**：
- 摄氏度转华氏度
- 华氏度转摄氏度
- 摄氏度转开尔文
- 开尔文转摄氏度
- 华氏度转开尔文
- 开尔文转华氏度

这些只是简单示例，模式可扩展到任何函数：数据库查询、API 调用、计算、文件操作或系统命令。

## 何时使用基于工具的代理

**使用工具时机：**
- 需要实时数据（天气、股票价格、库存）
- 需要执行复杂计算
- 访问数据库或 API
- 进行操作（发送邮件、创建工单、更新记录）
- 结合多个数据源

**不适合使用工具的情况：**
- 问题可基于通用知识回答
- 纯对话交流
- 工具响应延迟影响体验

## 下一步

**下一模块：** [05-mcp - 模型上下文协议（MCP）](../05-mcp/README.md)

---

**导航：** [← 上一节：模块 03 - RAG](../03-rag/README.md) | [返回主页](../README.md) | [下一节：模块 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免责声明**：  
本文档是使用 AI 翻译服务 [Co-op Translator](https://github.com/Azure/co-op-translator) 翻译的。虽然我们努力确保翻译的准确性，但请注意，自动翻译可能包含错误或不准确之处。原文应视为权威版本。对于重要信息，建议采用专业人工翻译。我们不对因使用本翻译而产生的任何误解或曲解承担责任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->