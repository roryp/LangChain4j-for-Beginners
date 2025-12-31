<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-31T06:10:03+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "my"
}
-->
# Module 04: AI Agents with Tools

## Table of Contents

- [What You'll Learn](../../../04-tools)
- [Prerequisites](../../../04-tools)
- [Understanding AI Agents with Tools](../../../04-tools)
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
  - [Experiment with Different Requests](../../../04-tools)
- [Key Concepts](../../../04-tools)
  - [ReAct Pattern (Reasoning and Acting)](../../../04-tools)
  - [Tool Descriptions Matter](../../../04-tools)
  - [Session Management](../../../04-tools)
  - [Error Handling](../../../04-tools)
- [Available Tools](../../../04-tools)
- [When to Use Tool-Based Agents](../../../04-tools)
- [Next Steps](../../../04-tools)

## What You'll Learn

ယခုအချိန်ထိ သင် AI နှင့် စကားပြောနည်းများ၊ prompt များကို ထိရောက်စွာ ဖွဲ့စည်းနည်းများနှင့် သင်၏စာအုပ်များကို အရင်းအမြစ်အဖြစ် အသုံးပြု၍ ဖြေကြားချက်များကို အခြေခံပေးနည်းများကို သင်ယူပြီးပါပြီ။ သို့သော် အခြေခံ ကန့်သတ်ချက်တစ်ခု ရှိဆဲဖြစ်သည်။ ဘာဆိုသော် ယာယီမော်ဒယ်များသည် စာသားပဲ ထုတ်ပေးနိုင်ကြသည်။ မိုးလေဝသကို စစ်ထုတ်၍မရ၊ တွက်ချက်ချက်၊ ဒေတာဘေ့စ် မေးမြန်းမှုမပြုနိုင်၊ ဒါမှမဟုတ် ပြင်ပ စနစ်များနှင့် အပြန်အလှန်ဆက်သွယ်၍ မရနိုင်ပါ။

ကိရိယာများ (tools) သည် ဤကို ပြောင်းလဲစေသည်။ မော်ဒယ်အား function များကို ခေါ်ရန် ခွင့်ပြုခြင်းဖြင့်၊ ၎င်းအား စာသားထုတ်ပေးသူမှ လုပ်ဆောင်နိုင်သည့် အေးဂျင့်တစ်ခုအဖြစ် ပြောင်းလဲစေပါသည်။ မော်ဒယ်သည် ဘယ်အချိန်တွင် ကိရိယာတစ်ခုလိုအပ်သည်၊ ဘယ်ကိရိယာကို အသုံးပြုမည်၊ အားလုံး parameter များကို ၎င်းမှဆုံးဖြတ်သည်။ သင်၏ကုဒ်သည် function ကို အကောင်အထည်ဖော်ပြီး ရလဒ်ကို ပြန်ပေးသည်။ မော်ဒယ်က ထိုရလဒ်ကို ၎င်း၏ ဖြေကြားချက်ထဲသို့ ပေါင်းထည့်သည်။

## Prerequisites

- Completed Module 01 (Azure OpenAI resources deployed)
- `.env` file in root directory with Azure credentials (created by `azd up` in Module 01)

> **Note:** If you haven't completed Module 01, follow the deployment instructions there first.

## Understanding AI Agents with Tools

> **📝 Note:** The term "agents" in this module refers to AI assistants enhanced with tool-calling capabilities. This is different from the **Agentic AI** patterns (autonomous agents with planning, memory, and multi-step reasoning) that we'll cover in [Module 05: MCP](../05-mcp/README.md).

An AI agent with tools follows a reasoning and acting pattern (ReAct):

1. User asks a question
2. Agent reasons about what it needs to know
3. Agent decides if it needs a tool to answer
4. If yes, agent calls the appropriate tool with the right parameters
5. Tool executes and returns data
6. Agent incorporates the result and provides the final answer

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.my.png" alt="ReAct Pattern" width="800"/>

*The ReAct pattern - how AI agents alternate between reasoning and acting to solve problems*

This happens automatically. You define the tools and their descriptions. The model handles the decision-making about when and how to use them.

## How Tool Calling Works

**Tool Definitions** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

You define functions with clear descriptions and parameter specifications. The model sees these descriptions in its system prompt and understands what each tool does.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // သင့်ရဲ့ ရာသီဥတု ရှာဖွေရေး လုပ်ထုံးလုပ်နည်း
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Assistant ကို Spring Boot က အလိုအလျောက် ချိတ်ဆက်ပေးထားသည်:
// - ChatModel bean
// - @Component အတန်းများမှ @Tool ဖြင့် သတ်မှတ်ထားသော အားလုံးသော method များ
// - Session စီမံခန့်ခွဲရေးအတွက် ChatMemoryProvider
```

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) and ask:
> - "How would I integrate a real weather API like OpenWeatherMap instead of mock data?"
> - "What makes a good tool description that helps the AI use it correctly?"
> - "How do I handle API errors and rate limits in tool implementations?"

**Decision Making**

When a user asks "What's the weather in Seattle?", the model recognizes it needs the weather tool. It generates a function call with the location parameter set to "Seattle".

**Execution** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot auto-wires the declarative `@AiService` interface with all registered tools, and LangChain4j executes tool calls automatically.

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) and ask:
> - "How does the ReAct pattern work and why is it effective for AI agents?"
> - "How does the agent decide which tool to use and in what order?"
> - "What happens if a tool execution fails - how should I handle errors robustly?"

**Response Generation**

The model receives the weather data and formats it into a natural language response for the user.

### Why Use Declarative AI Services?

This module uses LangChain4j's Spring Boot integration with declarative `@AiService` interfaces:

- **Spring Boot auto-wiring** - ChatModel and tools automatically injected
- **@MemoryId pattern** - Automatic session-based memory management
- **Single instance** - Assistant created once and reused for better performance
- **Type-safe execution** - Java methods called directly with type conversion
- **Multi-turn orchestration** - Handles tool chaining automatically
- **Zero boilerplate** - No manual AiServices.builder() calls or memory HashMap

Alternative approaches (manual `AiServices.builder()`) require more code and miss Spring Boot integration benefits.

## Tool Chaining

**Tool Chaining** - The AI might call multiple tools in sequence. Ask "What's the weather in Seattle and should I bring an umbrella?" and watch it chain `getCurrentWeather` with reasoning about rain gear.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.my.png" alt="Tool Chaining" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sequential tool calls - one tool's output feeds into the next decision*

**Graceful Failures** - Ask for weather in a city that's not in the mock data. The tool returns an error message, and the AI explains it can't help. Tools fail safely.

This happens in a single conversation turn. The agent orchestrates multiple tool calls autonomously.

## Run the Application

**Verify deployment:**

Ensure the `.env` file exists in root directory with Azure credentials (created during Module 01):
```bash
cat ../.env  # AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT ကို ပြသသင့်သည်
```

**Start the application:**

> **Note:** If you already started all applications using `./start-all.sh` from Module 01, this module is already running on port 8084. You can skip the start commands below and go directly to http://localhost:8084.

**Option 1: Using Spring Boot Dashboard (Recommended for VS Code users)**

The dev container includes the Spring Boot Dashboard extension, which provides a visual interface to manage all Spring Boot applications. You can find it in the Activity Bar on the left side of VS Code (look for the Spring Boot icon).

From the Spring Boot Dashboard, you can:
- See all available Spring Boot applications in the workspace
- Start/stop applications with a single click
- View application logs in real-time
- Monitor application status

Simply click the play button next to "tools" to start this module, or start all modules at once.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.my.png" alt="Spring Boot Dashboard" width="400"/>

**Option 2: Using shell scripts**

Start all web applications (modules 01-04):

**Bash:**
```bash
cd ..  # အမြစ် ဒိုင်ရက်ထရီမှ
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # အမြစ် ဒိုင်ရေးထရီမှ
.\start-all.ps1
```

Or start just this module:

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

Both scripts automatically load environment variables from the root `.env` file and will build the JARs if they don't exist.

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

Open http://localhost:8084 in your browser.

**To stop:**

**Bash:**
```bash
./stop.sh  # ဤမော်ဂျူးသာ
# သို့မဟုတ်
cd .. && ./stop-all.sh  # မော်ဂျူးအားလုံး
```

**PowerShell:**
```powershell
.\stop.ps1  # ဤမော်ဂျူးသာ
# သို့မဟုတ်
cd ..; .\stop-all.ps1  # မော်ဂျူးများအားလုံး
```

## Using the Application

The application provides a web interface where you can interact with an AI agent that has access to weather and temperature conversion tools.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.my.png" alt="AI Agent Tools Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*The AI Agent Tools interface - quick examples and chat interface for interacting with tools*

**Try Simple Tool Usage**

Start with a straightforward request: "Convert 100 degrees Fahrenheit to Celsius". The agent recognizes it needs the temperature conversion tool, calls it with the right parameters, and returns the result. Notice how natural this feels - you didn't specify which tool to use or how to call it.

**Test Tool Chaining**

Now try something more complex: "What's the weather in Seattle and convert it to Fahrenheit?" Watch the agent work through this in steps. It first gets the weather (which returns Celsius), recognizes it needs to convert to Fahrenheit, calls the conversion tool, and combines both results into one response.

**See Conversation Flow**

The chat interface maintains conversation history, allowing you to have multi-turn interactions. You can see all previous queries and responses, making it easy to track the conversation and understand how the agent builds context over multiple exchanges.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.my.png" alt="Conversation with Multiple Tool Calls" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Multi-turn conversation showing simple conversions, weather lookups, and tool chaining*

**Experiment with Different Requests**

Try various combinations:
- Weather lookups: "What's the weather in Tokyo?"
- Temperature conversions: "What is 25°C in Kelvin?"
- Combined queries: "Check the weather in Paris and tell me if it's above 20°C"

Notice how the agent interprets natural language and maps it to appropriate tool calls.

## Key Concepts

**ReAct Pattern (Reasoning and Acting)**

The agent alternates between reasoning (deciding what to do) and acting (using tools). This pattern enables autonomous problem-solving rather than just responding to instructions.

**Tool Descriptions Matter**

The quality of your tool descriptions directly affects how well the agent uses them. Clear, specific descriptions help the model understand when and how to call each tool.

**Session Management**

The `@MemoryId` annotation enables automatic session-based memory management. Each session ID gets its own `ChatMemory` instance managed by the `ChatMemoryProvider` bean, eliminating the need for manual memory tracking.

**Error Handling**

Tools can fail - APIs timeout, parameters might be invalid, external services go down. Production agents need error handling so the model can explain problems or try alternatives.

## Available Tools

**Weather Tools** (mock data for demonstration):
- Get current weather for a location
- Get multi-day forecast

**Temperature Conversion Tools**:
- Celsius to Fahrenheit
- Fahrenheit to Celsius
- Celsius to Kelvin
- Kelvin to Celsius
- Fahrenheit to Kelvin
- Kelvin to Fahrenheit

These are simple examples, but the pattern extends to any function: database queries, API calls, calculations, file operations, or system commands.

## When to Use Tool-Based Agents

**Use tools when:**
- Answering requires real-time data (weather, stock prices, inventory)
- You need to perform calculations beyond simple math
- Accessing databases or APIs
- Taking actions (sending emails, creating tickets, updating records)
- Combining multiple data sources

**Don't use tools when:**
- Questions can be answered from general knowledge
- Response is purely conversational
- Tool latency would make the experience too slow

## Next Steps

**Next Module:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigation:** [← Previous: Module 03 - RAG](../03-rag/README.md) | [Back to Main](../README.md) | [Next: Module 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
ငြင်းဆိုချက်:
ဤစာတမ်းကို AI ဘာသာပြန်မှု ဝန်ဆောင်မှုဖြစ်သည့် [Co-op Translator](https://github.com/Azure/co-op-translator) ကို အသုံးပြု၍ ဘာသာပြန်ထားခြင်းဖြစ်သည်။ ကျွန်ုပ်တို့သည် တိကျစွာ ဘာသာပြန်ရန် ကြိုးပမ်းသော်လည်း အလိုအလျောက် ဘာသာပြန်ချက်များတွင် အမှားများ သို့မဟုတ် တိကျမှုလျော့နည်းချက်များ ပါဝင်နိုင်ကြောင်း သိရှိထားရန် မေတ္တာရပ်ခံအပ်ပါသည်။ မူရင်းစာတမ်းကို မိခင်ဘာသာစကားဖြင့် ရှိသည့်ပုံစံဖြင့် သတ်မှတ်ထားသည့် တရားဝင် အချက်အလက် အရင်းအမြစ်အဖြစ် ယူဆသင့်သည်။ အရေးကြီးသော သတင်းအချက်အလက်များအတွက်တော့ လူသား ပရော်ဖက်ရှင်နယ် ဘာသာပြန်အကို ဆောင်ရွက်ပေးရန် အကြံပြုပါသည်။ ဤဘာသာပြန်ချက်ကို အသုံးပြုမှုမှ ဖြစ်ပေါ်နိုင်သည့် နားလည်မှုမှားယွင်းခြင်းများ သို့မဟုတ် မမှန်ကန်သော အဓိပ္ပါယ်ဖော်ပြချက်များအတွက် ကျွန်ုပ်တို့၏ တာဝန်မထားကြောင်း မှတ်သားပါ။
<!-- CO-OP TRANSLATOR DISCLAIMER END -->