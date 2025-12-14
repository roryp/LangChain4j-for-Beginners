<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "b975537560c404d5f254331832811e78",
  "translation_date": "2025-12-13T21:05:52+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "sv"
}
-->
# Testa LangChain4j-applikationer

## Innehållsförteckning

- [Snabbstart](../../../docs)
- [Vad testerna täcker](../../../docs)
- [Köra testerna](../../../docs)
- [Köra tester i VS Code](../../../docs)
- [Testmönster](../../../docs)
- [Testfilosofi](../../../docs)
- [Nästa steg](../../../docs)

Denna guide leder dig genom tester som visar hur man testar AI-applikationer utan att kräva API-nycklar eller externa tjänster.

## Snabbstart

Kör alla tester med ett enda kommando:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/test-results.ea5c98d8f3642043bdfde5c208f8c0760a88a818f6846763583b614a5de37d52.sv.png" alt="Successful Test Results" width="800"/>

*Lyckad testkörning som visar att alla tester passerar utan fel*

## Vad testerna täcker

Denna kurs fokuserar på **enhetstester** som körs lokalt. Varje test demonstrerar ett specifikt LangChain4j-koncept isolerat.

<img src="../../../translated_images/testing-pyramid.2dd1079a0481e53e4da944aec40169f37adf86fd932d3dfd56a4a86b37401ab9.sv.png" alt="Testing Pyramid" width="800"/>

*Testpyramid som visar balansen mellan enhetstester (snabba, isolerade), integrationstester (riktiga komponenter) och end-to-end-tester (fullt system med Docker). Denna utbildning täcker enhetstestning.*

| Modul | Tester | Fokus | Viktiga filer |
|--------|-------|-------|-----------|
| **00 - Snabbstart** | 6 | Promptmallar och variabelsubstitution | `SimpleQuickStartTest.java` |
| **01 - Introduktion** | 8 | Konversationsminne och tillståndsbaserad chatt | `SimpleConversationTest.java` |
| **02 - Prompt Engineering** | 12 | GPT-5-mönster, iver-nivåer, strukturerad output | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | Dokumentinmatning, inbäddningar, likhetssökning | `DocumentServiceTest.java` |
| **04 - Verktyg** | 12 | Funktionsanrop och verktygskedjor | `SimpleToolsTest.java` |
| **05 - MCP** | 15 | Model Context Protocol med Docker | `SimpleMcpTest.java`, `McpDockerTransportTest.java` |

## Köra testerna

**Kör alla tester från root:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**Kör tester för en specifik modul:**

**Bash:**
```bash
cd 01-introduction && mvn test
# Eller från rot
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# Eller från rot
mvn --% test -pl 01-introduction
```

**Kör en enskild testklass:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**Kör en specifik testmetod:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#börBehållaSamtalshistorik
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#börBehållaSamtalshistorik
```

## Köra tester i VS Code

Om du använder Visual Studio Code erbjuder Test Explorer ett grafiskt gränssnitt för att köra och felsöka tester.

<img src="../../../translated_images/vscode-testing.f02dd5917289dcedbacf98a3539218c1d0c700307ab77c031590ae63d0be59b6.sv.png" alt="VS Code Test Explorer" width="800"/>

*VS Code Test Explorer som visar testträdet med alla Java-testklasser och individuella testmetoder*

**För att köra tester i VS Code:**

1. Öppna Test Explorer genom att klicka på bägare-ikonen i aktivitetsfältet
2. Expandera testträdet för att se alla moduler och testklasser
3. Klicka på play-knappen bredvid ett test för att köra det individuellt
4. Klicka på "Run All Tests" för att köra hela sviten
5. Högerklicka på ett test och välj "Debug Test" för att sätta brytpunkter och stega igenom koden

Test Explorer visar gröna bockar för passerade tester och ger detaljerade felmeddelanden när tester misslyckas.

## Testmönster

<img src="../../../translated_images/testing-patterns.02581af1c9ef742460887004e9940994dba342ec726efc4ddb2b0215b56a1d89.sv.png" alt="Six Testing Patterns" width="800"/>

*Sex testmönster för LangChain4j-applikationer: promptmallar, mockning av modeller, konversationsisolering, verktygstestning, in-memory RAG och Docker-integration*

### Mönster 1: Testa promptmallar

Det enklaste mönstret testar promptmallar utan att anropa någon AI-modell. Du verifierar att variabelsubstitution fungerar korrekt och att prompts formateras som förväntat.

<img src="../../../translated_images/prompt-template-testing.b902758ddccc8dee3dfceaa5fa903098bdb174674acc5009a2f3d60cb07eb4e9.sv.png" alt="Prompt Template Testing" width="800"/>

*Test av promptmallar som visar flödet för variabelsubstitution: mall med platshållare → värden applicerade → formaterad output verifierad*

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

Detta test finns i `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`.

**Kör det:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#testPromptTemplateFormatering
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#testPromptTemplateFormatering
```

### Mönster 2: Mocka språkmodeller

När du testar konversationslogik, använd Mockito för att skapa falska modeller som returnerar förutbestämda svar. Detta gör tester snabba, gratis och deterministiska.

<img src="../../../translated_images/mock-vs-real.3b8b1f85bfe6845ee07556f9520ac45c7f776d0879434315756020cd8b1d5b73.sv.png" alt="Mock vs Real API Comparison" width="800"/>

*Jämförelse som visar varför mocks föredras för testning: de är snabba, gratis, deterministiska och kräver inga API-nycklar*

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
        assertThat(history).hasSize(6); // 3 användare + 3 AI-meddelanden
    }
}
```

Detta mönster finns i `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`. Mocken säkerställer konsekvent beteende så att du kan verifiera att minneshantering fungerar korrekt.

### Mönster 3: Testa konversationsisolering

Konversationsminnet måste hålla flera användare separerade. Detta test verifierar att konversationer inte blandar samman kontexter.

<img src="../../../translated_images/conversation-isolation.e00336cf8f7a3e3f860d81c37a97181c0e0a2ff996d38f314f06b35cc1e08ca3.sv.png" alt="Conversation Isolation" width="800"/>

*Test av konversationsisolering som visar separata minneslagringar för olika användare för att förhindra kontextblandning*

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

Varje konversation behåller sin egen oberoende historik. I produktionssystem är denna isolering kritisk för multi-användarapplikationer.

### Mönster 4: Testa verktyg oberoende

Verktyg är funktioner som AI kan anropa. Testa dem direkt för att säkerställa att de fungerar korrekt oavsett AI-beslut.

<img src="../../../translated_images/tools-testing.3e1706817b0b3924e7e7cd41be8ba5ccb62f16962b85f53953d46cc6317d2972.sv.png" alt="Tools Testing" width="800"/>

*Test av verktyg oberoende som visar mock-körning av verktyg utan AI-anrop för att verifiera affärslogik*

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

Dessa tester från `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` validerar verktygslogik utan AI-inblandning. Kedjeexemplet visar hur en verktygs output matas in i en annan.

### Mönster 5: In-memory RAG-testning

RAG-system kräver traditionellt vektordatabaser och inbäddningstjänster. In-memory-mönstret låter dig testa hela pipelinen utan externa beroenden.

<img src="../../../translated_images/rag-testing.ee7541b1e23934b14fcda9103bba011864f1d0882b27a9886c3cf5b446522ce3.sv.png" alt="In-Memory RAG Testing" width="800"/>

*In-memory RAG-testflöde som visar dokumentanalys, inbäddningslagring och likhetssökning utan krav på databas*

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

Detta test från `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` skapar ett dokument i minnet och verifierar chunkning och metadatahantering.

### Mönster 6: Integrationstestning med Docker

Vissa funktioner behöver riktig infrastruktur. MCP-modulen använder Testcontainers för att starta Docker-containrar för integrationstester. Dessa validerar att din kod fungerar med faktiska tjänster samtidigt som testisolering bibehålls.

<img src="../../../translated_images/mcp-testing.bb3b3e92e47acb4b3dfb12715b4e113655f16c11ba39c2ffabae557bc6c3734c.sv.png" alt="MCP Docker Integration Testing" width="800"/>

*MCP integrationstestning med Testcontainers som visar automatiserad containerlivscykel: start, testkörning, stopp och städning*

Tester i `05-mcp/src/test/java/com/example/langchain4j/mcp/McpDockerTransportTest.java` kräver att Docker körs.

**Kör dem:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## Testfilosofi

Testa din kod, inte AI:n. Dina tester ska validera den kod du skriver genom att kontrollera hur prompts konstrueras, hur minnet hanteras och hur verktyg körs. AI-svar varierar och bör inte ingå i testpåståenden. Fråga dig om din promptmall korrekt ersätter variabler, inte om AI ger rätt svar.

Använd mocks för språkmodeller. De är externa beroenden som är långsamma, dyra och icke-deterministiska. Mockning gör tester snabba med millisekunder istället för sekunder, gratis utan API-kostnader och deterministiska med samma resultat varje gång.

Håll tester oberoende. Varje test ska sätta upp sina egna data, inte förlita sig på andra tester och städa upp efter sig. Tester ska passera oavsett körordning.

Testa kantfall utöver den lyckade vägen. Prova tomma indata, mycket stora indata, specialtecken, ogiltiga parametrar och gränsvärden. Dessa avslöjar ofta buggar som normal användning inte visar.

Använd beskrivande namn. Jämför `shouldMaintainConversationHistoryAcrossMultipleMessages()` med `test1()`. Det första berättar exakt vad som testas, vilket gör felsökning av fel mycket enklare.

## Nästa steg

Nu när du förstår testmönstren, fördjupa dig i varje modul:

- **[00 - Snabbstart](../00-quick-start/README.md)** - Börja med grunderna i promptmallar
- **[01 - Introduktion](../01-introduction/README.md)** - Lär dig hantera konversationsminne
- **[02 - Prompt Engineering](../02-prompt-engineering/README.md)** - Bemästra GPT-5 promptmönster
- **[03 - RAG](../03-rag/README.md)** - Bygg system för retrieval-augmented generation
- **[04 - Verktyg](../04-tools/README.md)** - Implementera funktionsanrop och verktygskedjor
- **[05 - MCP](../05-mcp/README.md)** - Integrera Model Context Protocol med Docker

Varje moduls README ger detaljerade förklaringar av de koncept som testas här.

---

**Navigering:** [← Tillbaka till huvudmenyn](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfriskrivning**:
Detta dokument har översatts med hjälp av AI-översättningstjänsten [Co-op Translator](https://github.com/Azure/co-op-translator). Även om vi strävar efter noggrannhet, vänligen var medveten om att automatiska översättningar kan innehålla fel eller brister. Det ursprungliga dokumentet på dess modersmål bör betraktas som den auktoritativa källan. För kritisk information rekommenderas professionell mänsklig översättning. Vi ansvarar inte för några missförstånd eller feltolkningar som uppstår till följd av användningen av denna översättning.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->