<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-31T08:13:07+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "ml"
}
-->
# LangChain4j അപ്ലിക്കേഷനുകൾ ടെസ്റ്റിംഗ്

## ഉള്ളടക്ക പട്ടിക

- [ക്ഷിപ്രാരംഭം](../../../docs)
- [ടെസ്റ്റുകൾ എന്താണ് ഉൾക്കൊള്ളുന്നത്](../../../docs)
- [ടെസ്റ്റുകൾ എങ്ങനെ ഓടിപ്പിക്കുക](../../../docs)
- [VS Code ൽ ടെസ്റ്റുകൾ ഓടിക്കല്‍](../../../docs)
- [ടെസ്റ്റിംഗ് രീതികൾ](../../../docs)
- [ടെസ്റ്റിംഗ് തത്വശാഖ്യം](../../../docs)
- [അടുത്ത ნაბიჯങ്ങൾ](../../../docs)

ഈ ഗൈഡ് API കീകൾ അല്ലെങ്കിൽ ബാഹ്യ സേവനങ്ങൾ ആവശ്യമില്ലാതെ എഐ അപ്ലിക്കേഷനുകൾ എങ്ങനെ ടെസ്റ്റ് ചെയ്യാമെന്ന് തെളിയിക്കുന്ന ടെസ്റ്റുകൾ വഴി നിങ്ങളെ നയിക്കുന്നു.

## Quick Start

Run all tests with a single command:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/ml/test-results.ea5c98d8f3642043.png" alt="വിജയകരമായ ടെസ്റ്റ് ഫലങ്ങൾ" width="800"/>

*എല്ലാ ടെസ്റ്റുകളും വിഫലതകളില്ലാതെ വിജയകരമായി ഓടിവരുന്ന ടെസ്റ്റ് നിർവഹണത്തിന്റെ ദൃശ്യം*

## What the Tests Cover

This course focuses on **unit tests** that run locally. Each test demonstrates a specific LangChain4j concept in isolation.

<img src="../../../translated_images/ml/testing-pyramid.2dd1079a0481e53e.png" alt="ടെസ്റ്റിംഗ് പിരമിഡ്" width="800"/>

*യൂണിറ്റ് ടെസ്റ്റുകൾ (വേഗം, വേര്‍പെടുത്തിയ), ഇന്റഗ്രേഷൻ ടെസ്റ്റുകൾ (യഥാർത്ഥ ഘടകങ്ങൾ), end-to-end ടെസ്റ്റുകൾ എന്നിവയിലെ തുല്യഭാരം കാണിക്കുന്ന ടെസ്റ്റിംഗ് പിരമിഡ്. ഈ പരിശീലനം യൂണിറ്റ് ടെസ്റ്റിങ്ങിനാണ് കേന്ദ്രീകരിക്കുന്നത്.*

| Module | Tests | Focus | Key Files |
|--------|-------|-------|-----------|
| **00 - Quick Start** | 6 | Prompt templates and variable substitution | `SimpleQuickStartTest.java` |
| **01 - Introduction** | 8 | Conversation memory and stateful chat | `SimpleConversationTest.java` |
| **02 - Prompt Engineering** | 12 | GPT-5 patterns, eagerness levels, structured output | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | Document ingestion, embeddings, similarity search | `DocumentServiceTest.java` |
| **04 - Tools** | 12 | Function calling and tool chaining | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | Model Context Protocol with Stdio transport | `SimpleMcpTest.java` |

## Running the Tests

**Run all tests from root:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**Run tests for a specific module:**

**Bash:**
```bash
cd 01-introduction && mvn test
# അതവാ റൂട്ടിൽ നിന്ന്
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# അഥവാ റൂട്ട് മുതൽ
mvn --% test -pl 01-introduction
```

**Run a single test class:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**Run a specific test method:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#സംഭാഷണ ചരിത്രം നിലനിർത്തണോ
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#സംഭാഷണ ചരിത്രം നിലനിർത്തണം
```

## Running Tests in VS Code

If you're using Visual Studio Code, the Test Explorer provides a graphical interface for running and debugging tests.

<img src="../../../translated_images/ml/vscode-testing.f02dd5917289dced.png" alt="VS Code ടെസ്റ്റ് എക്‍സ്പ്ലോറർ" width="800"/>

*VS Code Test Explorer-ൽ എല്ലാ ജാവ ടെസ്റ്റ് ക്ലാസുകളും വ്യക്തിഗത ടെസ്റ്റ് മേതഡുകളും അടങ്ങിയ ടെസ്റ്റ് ട്രീ കാണിക്കുന്ന ദൃശ്യം*

**To run tests in VS Code:**

1. Open the Test Explorer by clicking the beaker icon in the Activity Bar
2. Expand the test tree to see all modules and test classes
3. Click the play button next to any test to run it individually
4. Click "Run All Tests" to execute the entire suite
5. Right-click any test and select "Debug Test" to set breakpoints and step through code

The Test Explorer shows green checkmarks for passing tests and provides detailed failure messages when tests fail.

## Testing Patterns

### Pattern 1: Testing Prompt Templates

The simplest pattern tests prompt templates without calling any AI model. You verify that variable substitution works correctly and prompts are formatted as expected.

<img src="../../../translated_images/ml/prompt-template-testing.b902758ddccc8dee.png" alt="പ്രോംപ്റ്റ് ടെംപ്ലേറ്റ് ടെസ്റ്റിംഗ്" width="800"/>

*വിവർത്തന സ്ഥലാധിഷ്ഠിതങ്ങളുള്ള ടെംപ്ലേറ്റ് → മൂല്യങ്ങൾ പ്രയോഗിച്ചു → ഫോര്മാറ്റുചെയ്‍ത ഔട്ട്‌പുട്ട് പരിശോധന ചെയ്‍തതെന്ന പ്രോംപ്റ്റ് ടെംപ്ലേറ്റ് ടെസ്റ്റിംഗിന്റെ പ്രവാഹം*

```java
@Test
@DisplayName("Should format prompt template with variables")
void testPromptTemplateFormatting() {
    PromptTemplate template = PromptTemplate.from(
        "Best time to visit {{destination}} for {{activity}}?"
    );
    
    Prompt prompt = template.apply(Map.of(
        "destination", "Paris",
        "activity", "sightseeing"
    ));
    
    assertThat(prompt.text()).isEqualTo("Best time to visit Paris for sightseeing?");
}
```

This test lives in `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`.

**Run it:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#ടെസ്റ്റ് പ്രോംപ്ലേറ്റ് പ്രോംപ്റ്റ് ഫോർമാറ്റിംഗ്
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#പ്രോംപ്റ്റ് ടെംപ്ലേറ്റ് ഫോർമാറ്റിംഗ് പരിശോധന
```

### Pattern 2: Mocking Language Models

When testing conversation logic, use Mockito to create fake models that return predetermined responses. This makes tests fast, free, and deterministic.

<img src="../../../translated_images/ml/mock-vs-real.3b8b1f85bfe6845e.png" alt="മോക്കുകൾ vs യഥാർത്ഥ API താരതമ്യം" width="800"/>

*മോക്കുകൾ ടെസ്റ്റിംഗിന് എന്തുകൊണ്ട് പ്രാധാന്യമുള്ളവയെന്നത് കാണിക്കുന്ന താരതമ്യം: വേഗം, സൗജന്യം, നിർണ്ണായകമായ ഫലങ്ങൾ, API കീകൾ不要*

```java
@ExtendWith(MockitoExtension.class)
class SimpleConversationTest {
    
    private ConversationService conversationService;
    
    @Mock
    private OpenAiOfficialChatModel mockChatModel;
    
    @BeforeEach
    void setUp() {
        ChatResponse mockResponse = ChatResponse.builder()
            .aiMessage(AiMessage.from("This is a test response"))
            .build();
        when(mockChatModel.chat(anyList())).thenReturn(mockResponse);
        
        conversationService = new ConversationService(mockChatModel);
    }
    
    @Test
    void shouldMaintainConversationHistory() {
        String conversationId = conversationService.startConversation();
        
        ChatResponse mockResponse1 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 1"))
            .build();
        ChatResponse mockResponse2 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 2"))
            .build();
        ChatResponse mockResponse3 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 3"))
            .build();
        
        when(mockChatModel.chat(anyList()))
            .thenReturn(mockResponse1)
            .thenReturn(mockResponse2)
            .thenReturn(mockResponse3);

        conversationService.chat(conversationId, "First message");
        conversationService.chat(conversationId, "Second message");
        conversationService.chat(conversationId, "Third message");

        List<ChatMessage> history = conversationService.getHistory(conversationId);
        assertThat(history).hasSize(6); // 3 ഉപയോക്താക്കൾ + 3 എഐ സന്ദേശങ്ങൾ
    }
}
```

This pattern appears in `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`. The mock ensures consistent behavior so you can verify memory management works correctly.

### Pattern 3: Testing Conversation Isolation

Conversation memory must keep multiple users separate. This test verifies that conversations don't mix contexts.

<img src="../../../translated_images/ml/conversation-isolation.e00336cf8f7a3e3f.png" alt="സംവാദ വ്യതിർത്തൃതി" width="800"/>

*വിവിധ ഉപഭോക്താക്കൾക്കുള്ള വേർതിരിച്ച മെമ്മറി സ്റ്റോറുകൾ സംവാദങ്ങളുടെ കോൺടെക്സ്‌ക്ട് മിശ്രിതമാകാതിരിക്കാൻ പരിശോധിക്കുന്ന ടെസ്റ്റിംഗ് ദൃശ്യം*

```java
@Test
void shouldIsolateConversationsByid() {
    String conv1 = conversationService.startConversation();
    String conv2 = conversationService.startConversation();
    
    ChatResponse mockResponse = ChatResponse.builder()
        .aiMessage(AiMessage.from("Response"))
        .build();
    when(mockChatModel.chat(anyList())).thenReturn(mockResponse);

    conversationService.chat(conv1, "Message for conversation 1");
    conversationService.chat(conv2, "Message for conversation 2");

    List<ChatMessage> history1 = conversationService.getHistory(conv1);
    List<ChatMessage> history2 = conversationService.getHistory(conv2);
    
    assertThat(history1).hasSize(2);
    assertThat(history2).hasSize(2);
}
```

Each conversation maintains its own independent history. In production systems, this isolation is critical for multi-user applications.

### Pattern 4: Testing Tools Independently

Tools are functions the AI can call. Test them directly to ensure they work correctly regardless of AI decisions.

<img src="../../../translated_images/ml/tools-testing.3e1706817b0b3924.png" alt="ഉപകരണങ്ങൾ സ്വതന്ത്രമായി ടെസ്റ്റ് ചെയ്യുക" width="800"/>

*AI കോളുകളോ തീരുമാനങ്ങളോ ഇല്ലാതെ മോക്ക് ടൂൾ നിർവഹണം കാണിക്കുന്ന ഉപകരണങ്ങൾ സ്വതന്ത്രമായി ടെസ്റ്റ് ചെയ്യുന്നതിന്റെ ദൃശ്യം: ബിസിനസ് ലജിക് പരിശോധിക്കുക*

```java
@Test
void shouldConvertCelsiusToFahrenheit() {
    TemperatureTool tempTool = new TemperatureTool();
    String result = tempTool.celsiusToFahrenheit(25.0);
    assertThat(result).containsPattern("77[.,]0°F");
}

@Test
void shouldDemonstrateToolChaining() {
    WeatherTool weatherTool = new WeatherTool();
    TemperatureTool tempTool = new TemperatureTool();

    String weatherResult = weatherTool.getCurrentWeather("Seattle");
    assertThat(weatherResult).containsPattern("\\d+°C");

    String conversionResult = tempTool.celsiusToFahrenheit(22.0);
    assertThat(conversionResult).containsPattern("71[.,]6°F");
}
```

These tests from `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` validate tool logic without AI involvement. The chaining example shows how one tool's output feeds into another's input.

### Pattern 5: In-Memory RAG Testing

RAG systems traditionally require vector databases and embedding services. The in-memory pattern lets you test the entire pipeline without external dependencies.

<img src="../../../translated_images/ml/rag-testing.ee7541b1e23934b1.png" alt="ഇൻ-മെമെറി RAG ടെസ്റ്റിംഗ്" width="800"/>

*ഡോക്ക്യുമെന്റ് പാർസിങ്, എമ്പഡ്ഡിംഗ് സംഭരണം, സമാനതാ തിരയൽ എന്നിവ ഡാറ്റാബേസ് ആവശ്യമില്ലാതെ കാണിക്കുന്ന ഇന്-മെമ്മറി RAG ടെസ്റ്റിംഗ് പ്രവാഹം*

```java
@Test
void testProcessTextDocument() {
    String content = "This is a test document.\nIt has multiple lines.";
    InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    
    DocumentService.ProcessedDocument result = 
        documentService.processDocument(inputStream, "test.txt");

    assertNotNull(result);
    assertTrue(result.segments().size() > 0);
    assertEquals("test.txt", result.segments().get(0).metadata().getString("filename"));
}
```

This test from `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` creates a document in memory and verifies chunking and metadata handling.

### Pattern 6: MCP Integration Testing

The MCP module tests the Model Context Protocol integration using stdio transport. These tests verify that your application can spawn and communicate with MCP servers as subprocesses.

The tests in `05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` validate MCP client behavior.

**Run them:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## Testing Philosophy

Test your code, not the AI. Your tests should validate the code you write by checking how prompts are constructed, how memory is managed, and how tools execute. AI responses vary and shouldn't be part of test assertions. Ask yourself whether your prompt template correctly substitutes variables, not whether the AI gives the right answer.

Use mocks for language models. They're external dependencies that are slow, expensive, and non-deterministic. Mocking makes tests fast with milliseconds instead of seconds, free with no API costs, and deterministic with the same result every time.

Keep tests independent. Each test should set up its own data, not rely on other tests, and clean up after itself. Tests should pass regardless of execution order.

Test edge cases beyond the happy path. Try empty inputs, very large inputs, special characters, invalid parameters, and boundary conditions. These often reveal bugs that normal usage doesn't expose.

Use descriptive names. Compare `shouldMaintainConversationHistoryAcrossMultipleMessages()` with `test1()`. The first tells you exactly what's being tested, making debugging failures much easier.

## Next Steps

Now that you understand the testing patterns, dive deeper into each module:

- **[00 - Quick Start](../00-quick-start/README.md)** - പ്രോംപ്റ്റ് ടെംപ്ലേറ്റ് അടിസ്ഥാനങ്ങളുമായി ആരംഭിക്കുക
- **[01 - Introduction](../01-introduction/README.md)** - സംവാദ മെമ്മറി മാനേജ്മെന്റ് പഠിക്കുക
- **[02 - Prompt Engineering](../02-prompt-engineering/README.md)** - GPT-5 പ്രോംപ്റ്റിംഗ് രീതികൾ കൈകഴിക്കുക
- **[03 - RAG](../03-rag/README.md)** - Retrieval-augmented generation സംവിധാനം നിർമ്മിക്കുക
- **[04 - Tools](../04-tools/README.md)** - ഫംഗ്ഷൻ കോല്ലിംഗ് এবং ടൂൾ ചെയിനുകൾ നടപ്പിലാക്കുക
- **[05 - MCP](../05-mcp/README.md)** - Model Context Protocol സംയോജിപ്പിക്കുക

Each module's README provides detailed explanations of the concepts tested here.

---

**Navigation:** [← പ്രധാന താളിലേക്ക് തിരികെ](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
അസ്വീകരണ കുറിപ്പ്:
ഈ രേഖ AI വിവർത്തന സേവനമായ [Co-op Translator](https://github.com/Azure/co-op-translator) ഉപയോഗിച്ച് പരിഭാഷപ്പെടുത്തിയതാണ്. ഞങ്ങൾ സൂക്ഷ്മതയ്ക്ക് ശ്രമിച്ചെങ്കിലും, ഓട്ടോമേറ്റഡ് വിവർത്തനങ്ങളില്‍ പിശകുകള്‍ അല്ലെങ്കില്‍ അശുദ്ധതകള്‍ ഉണ്ടാകാമെന്ന് ദയവായി മനസിലാക്കി കൊള്ളുക. മൂലഭാഷയില്‍ ഉള്ള രേഖയാണ് അതിന്റെ സർവസ്വീകരണമുള്ള പ്രാധാന്യമായ ഉറവിടം എന്ന് കരുതേണ്ടതാണ്. നിര്‍ണായകമായ വിവരങ്ങൾക്ക് വിദഗ്ധ മാനവ വിവർത്തനമാണ് ശിപാർശ ചെയ്യപ്പെടുന്നത്. ഈ വിവർത്തനം ഉപയോഗിച്ചതില്‍ നിന്നുണ്ടാകുന്ന ഏതെങ്കിലും തെറ്റിദ്ധാരണകള്‍ക്കും തെറ്റായ വ്യാഖ്യാനങ്ങള്‍ക്കും ഞങ്ങള്‍ ഉത്തരവാദികളല്ല.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->