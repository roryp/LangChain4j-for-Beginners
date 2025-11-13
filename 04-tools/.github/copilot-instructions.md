# GitHub Copilot Instructions: Fix Module 04 Tools Implementation

## Overview
This module currently has a fundamentally broken implementation of LangChain4j tool calling. The tools themselves are correctly defined with `@Tool` annotations, but the `AgentService` completely bypasses LangChain4j's native tool-calling infrastructure and implements a fragile, manual orchestration system using text parsing and HTTP calls.

## Current Architecture (BROKEN)
```
User Query → AgentService.buildSystemPromptWithTools() (manual string)
    ↓
Chat Model (receives text prompt with tool descriptions)
    ↓
Model returns text: "TOOL_CALL: toolName(params)"
    ↓
AgentService.responseContainsToolCall() (regex parsing)
    ↓
AgentService.executeToolByName() (HTTP POST to localhost:8080/api/tools/...)
    ↓
ToolsController receives HTTP request
    ↓
ToolsController calls WeatherTool/TemperatureTool methods
    ↓
HTTP response back to AgentService
    ↓
AgentService.processToolCalls() (manual loop with max iterations)
    ↓
Final response
```

## Correct Architecture (TARGET)
```
User Query → AiServices (with tools registered via .tools() builder)
    ↓
Chat Model (receives function schemas automatically)
    ↓
Model returns function_call (structured JSON)
    ↓
LangChain4j framework executes Java method directly
    ↓
Result automatically fed back to model
    ↓
Final response (multi-turn handled automatically)
```

---

## Required Changes

### 1. **AgentService.java** - MAJOR REWRITE REQUIRED

#### DELETE the following methods entirely:
- `buildSystemPromptWithTools()` - No longer needed, LangChain4j generates tool schemas
- `responseContainsToolCall()` - No text parsing needed
- `processToolCalls()` - LangChain4j handles orchestration
- `executeToolByName()` - Direct Java method calls, no HTTP
- `parseParams()` - No manual parameter parsing needed

#### DELETE these fields:
- `private final String toolsBaseUrl;`
- `private final RestTemplate restTemplate;`

#### REMOVE constructor parameters:
- `@Value("${azure.ai.agent.tools.base-url}") String toolsBaseUrl`

#### REMOVE constructor initialization:
- `this.toolsBaseUrl = toolsBaseUrl;`
- `this.restTemplate = new RestTemplate();`

#### ADD new fields:
```java
private final WeatherTool weatherTool;
private final TemperatureTool temperatureTool;
```

#### ADD logging to constructor:
```java
public AgentService(
        @Value("${azure.openai.endpoint}") String endpoint,
        @Value("${azure.openai.api-key}") String apiKey,
        @Value("${azure.openai.deployment}") String deployment,
        WeatherTool weatherTool,
        TemperatureTool temperatureTool) {
    
    this.weatherTool = weatherTool;
    this.temperatureTool = temperatureTool;
    
    log.info("Initializing Agent Service with Azure OpenAI");
    log.info("Endpoint: {}", endpoint);
    log.info("Deployment: {}", deployment);
    
    this.chatModel = AzureOpenAiChatModel.builder()
        .endpoint(endpoint)
        .apiKey(apiKey)
        .deploymentName(deployment)
        .maxCompletionTokens(2000)
        .maxRetries(3)
        .logRequestsAndResponses(true)
        .build();
    
    log.info("Agent service initialized successfully");
}
```

#### COMPLETELY REWRITE executeTask method:
```java
public AgentResponse executeTask(AgentRequest request) {
    log.info("Executing agent task: {}", request.message());
    
    String sessionId = request.sessionId();
    if (sessionId == null) {
        sessionId = createAgentSession();
    }
    
    ChatMemory memory = sessionMemories.computeIfAbsent(
        sessionId,
        id -> MessageWindowChatMemory.withMaxMessages(20)
    );
    
    try {
        // Create AI Service with tool support
        interface Assistant {
            String chat(String userMessage);
        }
        
        Assistant assistant = AiServices.builder(Assistant.class)
            .chatLanguageModel(chatModel)
            .chatMemory(memory)
            .tools(weatherTool, temperatureTool)
            .build();
        
        // Execute - LangChain4j handles all tool orchestration automatically
        String answer = assistant.chat(request.message());
        
        log.info("Agent completed task successfully");
        
        return new AgentResponse(
            answer,
            sessionId,
            new ArrayList<>(), // Tool execution details tracked by LangChain4j logging
            "completed"
        );
        
    } catch (Exception e) {
        log.error("Agent task execution failed", e);
        return new AgentResponse(
            "I encountered an error: " + e.getMessage(),
            sessionId,
            new ArrayList<>(),
            "failed"
        );
    }
}
```

#### ADD new import:
```java
import dev.langchain4j.service.AiServices;
```

#### UPDATE executeTask method signature:
No changes to signature needed - stays the same.

#### OPTIONAL: Tool execution tracking
Tool execution tracking is automatically handled by LangChain4j. The `ToolExecutionInfo` tracking in the current code can be simplified or removed since the framework manages this internally. If you need to track tool calls for logging/monitoring, you can:

1. **Option A**: Keep the current DTO structure but leave `toolExecutions` empty in response
2. **Option B**: Implement a custom `ChatMemoryProvider` to extract tool execution details
3. **Option C**: Use LangChain4j's logging (already enabled with `logRequestsAndResponses(true)`)

For this beginner tutorial, Option A (empty list) is simplest:
```java
return new AgentResponse(
    answer,
    sessionId,
    new ArrayList<>(), // Tool execution tracking handled by framework
    "completed"
);
```

#### UPDATE getAvailableTools method (optional - can stay as-is for documentation):
No changes needed - this is just for listing tools, not executing them.

---

### 2. **ToolsController.java** - DELETE OR REPURPOSE

**Option A (Recommended): DELETE ENTIRELY**
- Tools are called directly as Java methods by LangChain4j
- HTTP endpoints serve no purpose in correct implementation
- Keeping them creates confusion about architecture

**Option B: Keep as Demo/Testing Endpoints**
If keeping, add prominent comment at top of file:
```java
/**
 * REST controller exposing tools as HTTP endpoints for TESTING ONLY.
 * 
 * NOTE: The AI agent does NOT use these endpoints. Tools are called directly
 * as Java methods by LangChain4j's AiServices framework.
 * 
 * These endpoints exist only for:
 * - Manual testing of individual tools via curl/Postman
 * - Demonstration of tool functionality
 * - External integrations (not agent-related)
 */
```

---

### 3. **application.yaml**

#### REMOVE this section:
```yaml
azure:
  ai:
    agent:
      tools:
        base-url: ${TOOLS_BASE_URL:http://localhost:8084}
```

This configuration is no longer needed since tools are called directly via Java methods, not HTTP endpoints.

---

### 4. **pom.xml**

#### VERIFY these dependencies exist (they already do):
```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-azure-open-ai</artifactId>
</dependency>
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId>
</dependency>
```

#### OPTIONAL: Remove if not used elsewhere:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId> <!-- Keep if ToolsController stays -->
</dependency>
```

If `ToolsController` is deleted, the web dependency is still needed for `AgentController` and `WebController`.

---

### 5. **README.md**

#### UPDATE "Execution" section (around line 93):
**Current (WRONG):**
```markdown
Your code intercepts the function call, executes the actual weather lookup (via API or database), and returns the result to the model.
```

**Corrected:**
```markdown
LangChain4j intercepts the function call, executes the Java method directly, and returns the result to the model automatically.
```

#### ADD a "Why LangChain4j AiServices?" section after "How Tool Calling Works":
```markdown
### Why Use AiServices with Tools?

LangChain4j's `AiServices` framework handles all the complexity of tool calling:

- **Automatic schema generation** - Tool descriptions are converted to function schemas
- **Type-safe execution** - Java methods called directly with type conversion
- **Multi-turn orchestration** - Handles tool chaining automatically
- **Error handling** - Propagates exceptions cleanly
- **Zero boilerplate** - No manual parsing, HTTP calls, or result formatting

The alternative (manual implementation) requires:
- Writing tool schemas as strings
- Parsing LLM responses with regex
- Manual parameter extraction and type conversion
- Implementing ReAct loop with iteration limits
- Error handling at each step

With `AiServices.builder().tools()`, all of this is automatic.
```

#### UPDATE code example to emphasize it matches the implementation:
```markdown
**Tool Definitions** - [WeatherTool.java](../src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java)

Define tools with `@Tool` annotations. LangChain4j automatically generates function schemas and handles execution:

```java
@Component
public class WeatherTool {
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        return weatherService.getWeather(location);
    }
}

// In your service:
interface Assistant {
    String chat(String userMessage);
}

Assistant assistant = AiServices.builder(Assistant.class)
    .chatLanguageModel(chatModel)
    .chatMemory(memory)
    .tools(weatherTool, temperatureTool)  // Register tool instances
    .build();

String response = assistant.chat("What's the weather in Seattle?");
// LangChain4j handles: function call detection, parameter extraction,
// method execution, result formatting, and response generation
```
```

---

### 6. **Add Integration Test** - NEW FILE

Create: `src/test/java/com/example/langchain4j/agents/service/AgentIntegrationTest.java`

```java
package com.example.langchain4j.agents.service;

import com.example.langchain4j.agents.model.dto.AgentRequest;
import com.example.langchain4j.agents.model.dto.AgentResponse;
import com.example.langchain4j.agents.tools.TemperatureTool;
import com.example.langchain4j.agents.tools.WeatherTool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for agent tool calling.
 * Tests the full flow: user message → tool execution → response.
 */
@SpringBootTest
@TestPropertySource(properties = {
    "azure.openai.endpoint=${AZURE_OPENAI_ENDPOINT}",
    "azure.openai.api-key=${AZURE_OPENAI_API_KEY}",
    "azure.openai.deployment=${AZURE_OPENAI_DEPLOYMENT}"
})
@DisplayName("Agent Integration Tests")
class AgentIntegrationTest {

    @Autowired
    private AgentService agentService;

    @Test
    @DisplayName("Should use weather tool when asked about weather")
    void shouldUseWeatherTool() {
        // given
        AgentRequest request = new AgentRequest(
            "What's the weather in Seattle?",
            null,
            false
        );

        // when
        AgentResponse response = agentService.executeTask(request);

        // then
        assertThat(response.status()).isEqualTo("completed");
        assertThat(response.answer())
            .isNotNull()
            .containsIgnoringCase("Seattle")
            .containsPattern("\\d+°C");
        assertThat(response.toolExecutions())
            .as("Should have called weather tool")
            .hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("Should use temperature conversion tool")
    void shouldUseTemperatureTool() {
        // given
        AgentRequest request = new AgentRequest(
            "Convert 25 Celsius to Fahrenheit",
            null,
            false
        );

        // when
        AgentResponse response = agentService.executeTask(request);

        // then
        assertThat(response.status()).isEqualTo("completed");
        assertThat(response.answer())
            .contains("77"); // 25°C = 77°F
        assertThat(response.toolExecutions())
            .hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("Should chain tools when needed")
    void shouldChainTools() {
        // given
        AgentRequest request = new AgentRequest(
            "What's the weather in Paris and convert the temperature to Fahrenheit?",
            null,
            false
        );

        // when
        AgentResponse response = agentService.executeTask(request);

        // then
        assertThat(response.status()).isEqualTo("completed");
        assertThat(response.answer())
            .containsIgnoringCase("Paris")
            .containsPattern("\\d+°F");
        assertThat(response.toolExecutions())
            .as("Should have called multiple tools")
            .hasSizeGreaterThanOrEqualTo(2);
    }
}
```

---

### 7. **SimpleToolsTest.java** - Minor Update

The existing tests are good but add a note explaining why they test tools directly:

```java
/**
 * Simple beginner-friendly tests demonstrating tool functionality.
 * 
 * These tests focus on:
 * 1. Individual tool method behavior (unit tests)
 * 2. Parameter handling and validation
 * 3. Conceptual tool chaining demonstration
 * 
 * Note: These are UNIT tests for the tool classes themselves.
 * For INTEGRATION tests of the agent using tools via LangChain4j,
 * see AgentIntegrationTest.java
 */
```

---

## Why This Matters

### Current Implementation Problems:
1. **Fragile** - Relies on LLM returning exact text format "TOOL_CALL: name(params)"
2. **Slow** - Every tool call requires 2 HTTP round-trips
3. **Complex** - 200+ lines of manual orchestration code
4. **Limited** - Fixed iteration count, no parallel tool calls
5. **Anti-pattern** - Fights the framework instead of using it
6. **Misleading** - Uses `@Tool` annotations but ignores them

### Benefits of Correct Implementation:
1. **Robust** - Uses structured function calling (JSON), not text parsing
2. **Fast** - Direct Java method calls, no HTTP overhead
3. **Simple** - ~30 lines of code vs 200+
4. **Flexible** - Framework handles multi-turn, parallel calls, retries
5. **Idiomatic** - Uses LangChain4j as designed
6. **Maintainable** - Standard pattern, easy to extend

---

## Testing After Changes

1. **Unit tests should still pass** - Tool classes unchanged
2. **Integration tests required** - Add `AgentIntegrationTest.java` 
3. **Manual testing**:
   ```bash
   ./start.sh
   # Visit http://localhost:8084
   # Try: "What's the weather in Tokyo?"
   # Try: "Convert 100F to Celsius"
   # Try: "What's the weather in Paris in Fahrenheit?"
   ```

4. **Check logs** - Should see LangChain4j function call traces, not HTTP POST logs

---

## Files Summary

| File | Action | Complexity |
|------|--------|------------|
| `AgentService.java` | **MAJOR REWRITE** | High - Delete 5 methods, rewrite `executeTask` |
| `ToolsController.java` | **DELETE or mark as demo** | Low |
| `application.properties` | **Remove 1 line** | Trivial |
| `README.md` | **Update 3 sections** | Medium |
| `AgentIntegrationTest.java` | **CREATE new file** | Medium |
| `SimpleToolsTest.java` | **Add comment** | Trivial |
| `WeatherTool.java` | **No changes** | - |
| `TemperatureTool.java` | **No changes** | - |
| `pom.xml` | **Verify only** | - |

---

## Additional Notes

- The `@Tool` and `@P` annotations in `WeatherTool.java` and `TemperatureTool.java` are already correct - no changes needed
- Tool classes themselves don't need any modifications
- `AgentController.java` can stay as-is (it just calls `AgentService.executeTask()`)
- Session management code (`createAgentSession()`, `sessionMemories`) is correct and should be preserved
- Chat memory handling (`MessageWindowChatMemory`) is correct
- The `simpleChat()` and `clearSession()` methods can stay as-is
- The `getAvailableTools()` method can stay as-is (it's just for documentation/listing)

**Important**: The `ToolExecutionInfo` DTO uses different field names than in the instructions:
- Current: `ToolExecutionInfo(String toolName, List<String> arguments, String status)`
- The third parameter is named `status` but represents the result/output
- This DTO can stay as-is; just pass an empty list to the AgentResponse

**Core issue**: The agent orchestration layer was implemented manually using HTTP calls instead of using LangChain4j's `AiServices.builder().tools()` pattern. The tools themselves are perfectly implemented with proper annotations.
