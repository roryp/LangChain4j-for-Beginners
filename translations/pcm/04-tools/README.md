<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-31T07:33:51+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "pcm"
}
-->
# Module 04: AI Agents wit Tools

## Table of Contents

- [Wetin You Go Learn](../../../04-tools)
- [Wetin You Need](../../../04-tools)
- [Understanding AI Agents wit Tools](../../../04-tools)
- [How Tool Calling Works](../../../04-tools)
  - [Tool Definitions](../../../04-tools)
  - [Decision Making](../../../04-tools)
  - [Execution](../../../04-tools)
  - [Response Generation](../../../04-tools)
- [Tool Chaining](../../../04-tools)
- [Run the Application](../../../04-tools)
- [Using the Application](../../../04-tools)
  - [Try Simple Tool Usage](../../../04-tools)
  - [Test Tool Chaining](../../../04-tools)
  - [See Conversation Flow](../../../04-tools)
  - [Observe the Reasoning](../../../04-tools)
  - [Experiment wit Different Requests](../../../04-tools)
- [Key Concepts](../../../04-tools)
  - [ReAct Pattern (Reasoning and Acting)](../../../04-tools)
  - [Tool Descriptions Matter](../../../04-tools)
  - [Session Management](../../../04-tools)
  - [Error Handling](../../../04-tools)
- [Available Tools](../../../04-tools)
- [When to Use Tool-Based Agents](../../../04-tools)
- [Next Steps](../../../04-tools)

## What You'll Learn

So far, you don learn how to reason with AI, structure prompts well, and make responses follow your documents. But one gbege remain: language models only sabi generate text. Dem no fit check weather, do calculations, query databases, or interact wit outside systems.

Tools fix dis. If you give the model access to functions wey e fit call, e go change from text generator to agent wey fit take action. The model go decide when e need tool, which tool to use, and which parameters to give. Your code go run the function and return the result. The model go carry that result enter im answer.

## Prerequisites

- Don finish Module 01 (Azure OpenAI resources don deploy)
- `.env` file for root directory with Azure credentials (wey `azd up` create for Module 01)

> **Note:** If you never finish Module 01, follow the deployment instructions for that module first.

## Understanding AI Agents with Tools

> **📝 Note:** When we dey tok "agents" for this module, e mean AI assistants wey don get tool-calling ability. E different from the **Agentic AI** patterns (autonomous agents wey get planning, memory, and multi-step reasoning) wey we go cover for [Module 05: MCP](../05-mcp/README.md).

AI agent wey get tools dey follow reasoning and acting pattern (ReAct):

1. User ask question
2. Agent reason about wetin e need know
3. Agent decide if e need tool to answer
4. If yes, agent go call the right tool with correct parameters
5. Tool go run and return data
6. Agent go mix the result and give the final answer

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.pcm.png" alt="ReAct Patern" width="800"/>

*The ReAct pattern - how AI agents alternate between reasoning and acting to solve problems*

Dis one dey happen automatic. You go define the tools and how you describe dem. The model dey handle decision-making about when and how to use dem.

## How Tool Calling Works

**Tool Definitions** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

You go define functions wit clear descriptions and parameter specs. The model go see those descriptions inside im system prompt and understand wetin each tool dey do.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Di logic wey dey find weather
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Spring Boot don automatically hook up di assistant wit:
// - ChatModel bean
// - All @Tool methods wey dey from @Component classes
// - ChatMemoryProvider wey dey manage session
```

> **🤖 Try wit [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) and ask:
> - "How would I integrate a real weather API like OpenWeatherMap instead of mock data?"
> - "What makes a good tool description that helps the AI use it correctly?"
> - "How do I handle API errors and rate limits in tool implementations?"

**Decision Making**

If user ask "What's the weather in Seattle?", the model go sabi say e need the weather tool. E go generate a function call with the location parameter set to "Seattle".

**Execution** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot go auto-wire the declarative `@AiService` interface wit all registered tools, and LangChain4j go run the tool calls automatically.

> **🤖 Try wit [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) and ask:
> - "How does the ReAct pattern work and why is it effective for AI agents?"
> - "How does the agent decide which tool to use and in what order?"
> - "What happens if a tool execution fails - how should I handle errors robustly?"

**Response Generation**

The model go receive the weather data and format am into natural language answer for the user.

### Why Use Declarative AI Services?

This module dey use LangChain4j's Spring Boot integration wit declarative `@AiService` interfaces:

- **Spring Boot auto-wiring** - ChatModel and tools dey inject automatically
- **@MemoryId pattern** - Automatic session-based memory management
- **Single instance** - Assistant create once and reuse for better performance
- **Type-safe execution** - Java methods dey call directly wit type conversion
- **Multi-turn orchestration** - Dey handle tool chaining automatically
- **Zero boilerplate** - No manual AiServices.builder() calls or memory HashMap

Other ways (manual `AiServices.builder()`) go need more code and dem no get Spring Boot integration benefits.

## Tool Chaining

**Tool Chaining** - The AI fit call many tools one after another. Ask "What's the weather in Seattle and should I bring an umbrella?" and watch am chain `getCurrentWeather` and reason about rain gear.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.pcm.png" alt="Tool Chaining" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sequential tool calls - one tool's output feeds into the next decision*

**Graceful Failures** - Ask for weather for city wey no dey the mock data. The tool go return error message, and the AI go explain say e no fit help. Tools dey fail safely.

This one dey happen inside single conversation turn. The agent dey orchestrate multiple tool calls by itself.

## Run the Application

**Verify deployment:**

Make sure the `.env` file dey for root directory with Azure credentials (wey create during Module 01):
```bash
cat ../.env  # E suppose show AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Start the application:**

> **Note:** If you don already start all applications using `./start-all.sh` from Module 01, this module don dey run for port 8084. You fit skip the start commands below and open http://localhost:8084 straight.

**Option 1: Using Spring Boot Dashboard (Recommended for VS Code users)**

The dev container get Spring Boot Dashboard extension, wey give visual interface to manage all Spring Boot apps. You go see am for the Activity Bar for left side of VS Code (look for the Spring Boot icon).

From the Spring Boot Dashboard, you fit:
- See all available Spring Boot applications for the workspace
- Start/stop applications with one click
- View application logs in real-time
- Monitor application status

Just click the play button next to "tools" to start this module, or start all modules at once.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.pcm.png" alt="Spring Boot Dashboard" width="400"/>

**Option 2: Using shell scripts**

Start all web applications (modules 01-04):

**Bash:**
```bash
cd ..  # From di root directory
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # From di root directory
.\start-all.ps1
```

Or start only this module:

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

Both scripts go automatically load environment variables from the root `.env` file and dem go build the JARs if dem no exist.

> **Note:** If you prefer to build all modules manually before starting:
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

Open http://localhost:8084 for your browser.

**To stop:**

**Bash:**
```bash
./stop.sh  # Na only dis module
# Abi
cd .. && ./stop-all.sh  # All di modules
```

**PowerShell:**
```powershell
.\stop.ps1  # Dis module only
# Or
cd ..; .\stop-all.ps1  # All di modules
```

## Using the Application

The application get web interface where you fit interact wit AI agent wey get access to weather and temperature conversion tools.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.pcm.png" alt="AI Agent Tools Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*The AI Agent Tools interface - quick examples and chat interface for interacting with tools*

**Try Simple Tool Usage**

Start wit easy request: "Convert 100 degrees Fahrenheit to Celsius". The agent go know say e need temperature conversion tool, call am wit correct parameters, and return the result. See how natural e feel - you no talk which tool to use or how to call am.

**Test Tool Chaining**

Now try somtin wey more complex: "What's the weather in Seattle and convert it to Fahrenheit?" Watch the agent do am step by step. E first get the weather (wey return Celsius), then e sabi say e need convert to Fahrenheit, call the conversion tool, and combine both results into one response.

**See Conversation Flow**

The chat interface dey keep conversation history, so you fit do multi-turn interactions. You go fit see previous queries and responses, which make am easy to follow how the agent dey build context over many exchanges.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.pcm.png" alt="Conversation wit Multiple Tool Calls" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Multi-turn conversation showing simple conversions, weather lookups, and tool chaining*

**Experiment wit Different Requests**

Try different combos:
- Weather lookups: "What's the weather in Tokyo?"
- Temperature conversions: "What is 25°C in Kelvin?"
- Combined queries: "Check the weather in Paris and tell me if it's above 20°C"

Observe how the agent interpret natural language and map am to the correct tool calls.

## Key Concepts

**ReAct Pattern (Reasoning and Acting)**

The agent dey switch between reasoning (deciding wetin to do) and acting (using tools). This pattern allow autonomous problem-solving instead of just following instructions.

**Tool Descriptions Matter**

How you describe your tools go affect how well the agent fit use dem. Clear and specific descriptions help the model know when and how to call each tool.

**Session Management**

The `@MemoryId` annotation enable automatic session-based memory management. Each session ID get im own `ChatMemory` instance wey `ChatMemoryProvider` bean manage, so you no need to track memory manually.

**Error Handling**

Tools fit fail - APIs fit timeout, parameters fit wrong, external services fit down. For production, agents need proper error handling so the model fit explain problems or try other options.

## Available Tools

**Weather Tools** (mock data for demo):
- Get current weather for a location
- Get multi-day forecast

**Temperature Conversion Tools**:
- Celsius to Fahrenheit
- Fahrenheit to Celsius
- Celsius to Kelvin
- Kelvin to Celsius
- Fahrenheit to Kelvin
- Kelvin to Fahrenheit

Dem simple examples, but the pattern fit extend to any function: database queries, API calls, calculations, file operations, or system commands.

## When to Use Tool-Based Agents

**Use tools when:**
- Answer need real-time data (weather, stock prices, inventory)
- You need to do calculations wey pass simple math
- You need access to databases or APIs
- You wan take actions (send emails, create tickets, update records)
- You wan combine many data sources

**Don't use tools when:**
- Questions fit answer from general knowledge
- Response na just conversation
- Tool latency go make experience too slow

## Next Steps

**Next Module:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigation:** [← Previous: Module 03 - RAG](../03-rag/README.md) | [Back to Main](../README.md) | [Next: Module 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Disclaimer:
Dis dokument don translate by AI translation service Co-op Translator (https://github.com/Azure/co-op-translator). We dey try make am accurate, but abeg sabi say automatic translations fit get mistakes or wrong tin. Di original dokument for e original language na di correct source. For important mata, make person wey sabi human translator do am. We no dey responsible if anybody misunderstand or misinterpret anything wey come from dis translation.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->