<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "aa23f106e7f53270924c9dd39c629004",
  "translation_date": "2025-12-13T18:28:57+00:00",
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
  - [决策制定](../../../04-tools)
  - [执行](../../../04-tools)
  - [响应生成](../../../04-tools)
- [工具链](../../../04-tools)
- [运行应用程序](../../../04-tools)
- [使用应用程序](../../../04-tools)
  - [尝试简单的工具使用](../../../04-tools)
  - [测试工具链](../../../04-tools)
  - [查看对话流程](../../../04-tools)
  - [观察推理过程](../../../04-tools)
  - [尝试不同的请求](../../../04-tools)
- [关键概念](../../../04-tools)
  - [ReAct 模式（推理与行动）](../../../04-tools)
  - [工具描述的重要性](../../../04-tools)
  - [会话管理](../../../04-tools)
  - [错误处理](../../../04-tools)
- [可用工具](../../../04-tools)
- [何时使用基于工具的代理](../../../04-tools)
- [下一步](../../../04-tools)

## 你将学到什么

到目前为止，你已经学会了如何与 AI 进行对话，如何有效构建提示，并将回答基于你的文档。但仍存在一个根本限制：语言模型只能生成文本。它们无法查询天气、执行计算、查询数据库或与外部系统交互。

工具改变了这一点。通过赋予模型调用函数的能力，你将它从文本生成器转变为可以采取行动的代理。模型决定何时需要工具，使用哪个工具，以及传递什么参数。你的代码执行函数并返回结果。模型将该结果整合到其响应中。

## 先决条件

- 完成模块 01（已部署 Azure OpenAI 资源）
- 根目录下有包含 Azure 凭据的 `.env` 文件（由模块 01 中的 `azd up` 创建）

> **注意：** 如果你还没有完成模块 01，请先按照那里的部署说明操作。

## 理解带工具的 AI 代理

带工具的 AI 代理遵循推理与行动模式（ReAct）：

1. 用户提出问题
2. 代理推理它需要知道什么
3. 代理决定是否需要工具来回答
4. 如果需要，代理调用合适的工具并传入正确参数
5. 工具执行并返回数据
6. 代理整合结果并给出最终答案

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13ae5b0218d4e91befabc04e00f97539df14f93d1ad9b8516f.zh.png" alt="ReAct Pattern" width="800"/>

*ReAct 模式——AI 代理如何在推理与行动之间交替以解决问题*

这一过程是自动进行的。你定义工具及其描述，模型负责决定何时以及如何使用它们。

## 工具调用的工作原理

**工具定义** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

你定义带有清晰描述和参数规范的函数。模型在其系统提示中看到这些描述，理解每个工具的功能。

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
// - ChatModel bean
// - 来自 @Component 类的所有 @Tool 方法
// - 用于会话管理的 ChatMemoryProvider
```

> **🤖 试试用 [GitHub Copilot](https://github.com/features/copilot) 聊天：** 打开 [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) 并提问：
> - “我如何集成像 OpenWeatherMap 这样的真实天气 API，而不是模拟数据？”
> - “什么样的工具描述能帮助 AI 正确使用它？”
> - “我如何在工具实现中处理 API 错误和速率限制？”

**决策制定**

当用户问“西雅图的天气怎么样？”时，模型识别出需要天气工具。它生成一个函数调用，位置参数设为“Seattle”。

**执行** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot 自动装配声明式的 `@AiService` 接口及所有注册的工具，LangChain4j 自动执行工具调用。

> **🤖 试试用 [GitHub Copilot](https://github.com/features/copilot) 聊天：** 打开 [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) 并提问：
> - “ReAct 模式如何工作，为什么对 AI 代理有效？”
> - “代理如何决定使用哪个工具以及调用顺序？”
> - “如果工具执行失败，会发生什么——我该如何健壮地处理错误？”

**响应生成**

模型接收天气数据并将其格式化为自然语言响应给用户。

### 为什么使用声明式 AI 服务？

本模块使用 LangChain4j 的 Spring Boot 集成和声明式 `@AiService` 接口：

- **Spring Boot 自动装配** - ChatModel 和工具自动注入
- **@MemoryId 模式** - 自动基于会话的内存管理
- **单实例** - 助手只创建一次并复用以提升性能
- **类型安全执行** - 直接调用 Java 方法并进行类型转换
- **多轮编排** - 自动处理工具链调用
- **零样板代码** - 无需手动调用 AiServices.builder() 或管理内存 HashMap

手动使用 `AiServices.builder()` 需要更多代码且缺少 Spring Boot 集成优势。

## 工具链

**工具链** - AI 可能会顺序调用多个工具。问“西雅图的天气怎么样，我需要带伞吗？”并观察它如何将 `getCurrentWeather` 与关于雨具的推理串联起来。

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b1d54117d54ba382c21c51176aaf3800084cae2e7dfc82508.zh.png" alt="Tool Chaining" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*顺序工具调用——一个工具的输出作为下一个决策的输入*

**优雅失败** - 询问模拟数据中不存在的城市天气。工具返回错误信息，AI 解释无法提供帮助。工具安全失败。

这在单次对话轮次内完成。代理自主编排多次工具调用。

## 运行应用程序

**验证部署：**

确保根目录存在包含 Azure 凭据的 `.env` 文件（模块 01 创建）：
```bash
cat ../.env  # 应显示 AZURE_OPENAI_ENDPOINT、API_KEY、DEPLOYMENT
```

**启动应用程序：**

> **注意：** 如果你已经使用模块 01 中的 `./start-all.sh` 启动了所有应用，本模块已在端口 8084 运行。你可以跳过下面的启动命令，直接访问 http://localhost:8084。

**选项 1：使用 Spring Boot 仪表盘（推荐 VS Code 用户）**

开发容器包含 Spring Boot 仪表盘扩展，提供可视化界面管理所有 Spring Boot 应用。你可以在 VS Code 左侧活动栏找到（寻找 Spring Boot 图标）。

通过 Spring Boot 仪表盘，你可以：
- 查看工作区内所有可用的 Spring Boot 应用
- 一键启动/停止应用
- 实时查看应用日志
- 监控应用状态

只需点击“tools”旁的播放按钮启动本模块，或一次启动所有模块。

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30af495a594f5c0213fecdbdf5bd9fb543d3c5467565773974a.zh.png" alt="Spring Boot Dashboard" width="400"/>

**选项 2：使用 shell 脚本**

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

两个脚本都会自动从根目录 `.env` 文件加载环境变量，且如果 JAR 文件不存在会自动构建。

> **注意：** 如果你想先手动构建所有模块再启动：
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

**停止应用：**

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

该应用提供网页界面，你可以与拥有天气和温度转换工具的 AI 代理交互。

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f96216024b45b493ca1cd84935d6856416ea7a383b42f280d648c.zh.png" alt="AI Agent Tools Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI 代理工具界面——快速示例和聊天界面，用于与工具交互*

**尝试简单的工具使用**

从简单请求开始：“将 100 华氏度转换为摄氏度”。代理识别需要温度转换工具，调用它并传入正确参数，返回结果。注意这感觉多么自然——你没有指定用哪个工具或如何调用。

**测试工具链**

现在试试更复杂的：“西雅图的天气怎么样，并转换成华氏度？”观察代理分步骤处理。它先获取天气（返回摄氏度），识别需要转换成华氏度，调用转换工具，并将两个结果合并成一个响应。

**查看对话流程**

聊天界面保持对话历史，支持多轮交互。你可以看到所有之前的查询和回答，方便跟踪对话并理解代理如何在多轮交流中构建上下文。

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f596acc43e227bf70f3c0d6030ad91d84df81070abf08848608.zh.png" alt="Conversation with Multiple Tool Calls" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*多轮对话展示简单转换、天气查询和工具链调用*

**尝试不同的请求**

试试各种组合：
- 天气查询：“东京的天气怎么样？”
- 温度转换：“25°C 是多少开尔文？”
- 组合查询：“查一下巴黎的天气，告诉我是否高于 20°C”

注意代理如何理解自然语言并映射到合适的工具调用。

## 关键概念

**ReAct 模式（推理与行动）**

代理在推理（决定做什么）和行动（使用工具）之间交替。这种模式使其能够自主解决问题，而不仅仅是响应指令。

**工具描述的重要性**

工具描述的质量直接影响代理使用工具的效果。清晰、具体的描述帮助模型理解何时以及如何调用每个工具。

**会话管理**

`@MemoryId` 注解启用自动基于会话的内存管理。每个会话 ID 都有自己的 `ChatMemory` 实例，由 `ChatMemoryProvider` bean 管理，无需手动跟踪内存。

**错误处理**

工具可能失败——API 超时、参数无效、外部服务宕机。生产环境代理需要错误处理，使模型能解释问题或尝试替代方案。

## 可用工具

**天气工具**（演示用模拟数据）：
- 获取某地当前天气
- 获取多日天气预报

**温度转换工具**：
- 摄氏度转华氏度
- 华氏度转摄氏度
- 摄氏度转开尔文
- 开尔文转摄氏度
- 华氏度转开尔文
- 开尔文转华氏度

这些是简单示例，但该模式可扩展到任何函数：数据库查询、API 调用、计算、文件操作或系统命令。

## 何时使用基于工具的代理

**使用工具的场景：**
- 回答需要实时数据（天气、股价、库存）
- 需要执行复杂计算
- 访问数据库或 API
- 执行动作（发送邮件、创建工单、更新记录）
- 结合多个数据源

**不适合使用工具的场景：**
- 问题可用通用知识回答
- 纯对话式响应
- 工具延迟会导致体验过慢

## 下一步

**下一个模块：** [05-mcp - 模型上下文协议 (MCP)](../05-mcp/README.md)

---

**导航：** [← 上一节：模块 03 - RAG](../03-rag/README.md) | [返回主页](../README.md) | [下一节：模块 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免责声明**：  
本文件由人工智能翻译服务 [Co-op Translator](https://github.com/Azure/co-op-translator) 翻译而成。虽然我们力求准确，但请注意自动翻译可能存在错误或不准确之处。原始文件的母语版本应被视为权威来源。对于重要信息，建议使用专业人工翻译。我们不对因使用本翻译而产生的任何误解或误释承担责任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->