<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-31T01:23:09+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "sv"
}
-->
# Testning av LangChain4j-applikationer

## Innehållsförteckning

- [Snabbstart](../../../docs)
- [Vad testerna täcker](../../../docs)
- [Köra testerna](../../../docs)
- [Köra tester i VS Code](../../../docs)
- [Testmönster](../../../docs)
- [Testfilosofi](../../../docs)
- [Nästa steg](../../../docs)

Denna guide leder dig genom testerna som visar hur du testar AI-applikationer utan att kräva API-nycklar eller externa tjänster.

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

<img src="../../../translated_images/sv/test-results.ea5c98d8f3642043.webp" alt="Lyckade testresultat" width="800"/>

*Lyckad testkörning som visar att alla tester passerar utan fel*

## Vad testerna täcker

Denna kurs fokuserar på **enhetstester** som körs lokalt. Varje test demonstrerar ett specifikt LangChain4j-koncept i isolering.

<img src="../../../translated_images/sv/testing-pyramid.2dd1079a0481e53e.webp" alt="Testpyramid" width="800"/>

*Testpyramid som visar balansen mellan enhetstester (snabba, isolerade), integrationstester (riktiga komponenter) och end-to-end-tester. Denna utbildning täcker enhetstester.*

| Modul | Tester | Fokus | Viktiga filer |
|--------|-------|-------|-----------|
| **00 - Snabbstart** | 6 | Promptmallar och variabelsubstitution | `SimpleQuickStartTest.java` |
| **01 - Introduktion** | 8 | Konversationsminne och tillståndsbaserad chatt | `SimpleConversationTest.java` |
| **02 - Promptutformning** | 12 | GPT-5-mönster, beredskapsnivåer, strukturerad utdata | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | Dokumentimport, embeddingar, likhetssökning | `DocumentServiceTest.java` |
| **04 - Verktyg** | 12 | Funktionsanrop och verktygskedjning | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | Model Context Protocol med stdio-transport | `SimpleMcpTest.java` |

## Köra testerna

**Kör alla tester från projektets rot:**

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
# Eller från root
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# Eller från root
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
mvn test -Dtest=SimpleConversationTest#bör bibehålla konversationshistorik
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#bör behålla samtalshistorik
```

## Köra tester i VS Code

Om du använder Visual Studio Code ger Test Explorer ett grafiskt gränssnitt för att köra och felsöka tester.

<img src="../../../translated_images/sv/vscode-testing.f02dd5917289dced.webp" alt="VS Code Testutforskare" width="800"/>

*VS Code Testutforskare som visar testträdet med alla Java-testklasser och enskilda testmetoder*

**För att köra tester i VS Code:**

1. Öppna Test Explorer genom att klicka på kolvikonen i aktivitetsfältet
2. Expandera testträdet för att se alla moduler och testklasser
3. Klicka på play-knappen bredvid ett test för att köra det individuellt
4. Klicka på "Run All Tests" för att köra hela testsviten
5. Högerklicka på ett test och välj "Debug Test" för att sätta brytpunkter och stega genom koden

Test Explorer visar gröna bockar för godkända tester och ger detaljerade felmeddelanden när tester misslyckas.

## Testmönster

### Mönster 1: Testa promptmallar

Det enklaste mönstret testar promptmallar utan att anropa någon AI-modell. Du verifierar att variabelsubstitution fungerar korrekt och att prompts formateras som förväntat.

<img src="../../../translated_images/sv/prompt-template-testing.b902758ddccc8dee.webp" alt="Test av promptmallar" width="800"/>

*Test av promptmallar som visar flödet för variabelsubstitution: mall med platshållare → värden appliceras → formaterad utdata verifieras*

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
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#testa formateringen av promptmallen
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#test av formatering av promptmallen
```

### Mönster 2: Mockning av språkmodeller

När du testar konversationslogik, använd Mockito för att skapa falska modeller som returnerar förutbestämda svar. Det gör testerna snabba, kostnadsfria och deterministiska.

<img src="../../../translated_images/sv/mock-vs-real.3b8b1f85bfe6845e.webp" alt="Mock vs verklig API-jämförelse" width="800"/>

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

Detta mönster finns i `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`. Mocken säkerställer konsekvent beteende så att du kan verifiera att minneshanteringen fungerar korrekt.

### Mönster 3: Testa konversationsisolering

Konversationsminnet måste hålla flera användare separerade. Detta test verifierar att konversationer inte blandar samman kontexter.

<img src="../../../translated_images/sv/conversation-isolation.e00336cf8f7a3e3f.webp" alt="Konversationsisolering" width="800"/>

*Test av konversationsisolering som visar separata minneslager för olika användare för att förhindra att kontext blandas*

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

Varje konversation upprätthåller sin egen oberoende historik. I produktionssystem är denna isolering avgörande för fleranvändarapplikationer.

### Mönster 4: Testa verktyg oberoende

Verktyg är funktioner som AI kan anropa. Testa dem direkt för att säkerställa att de fungerar korrekt oberoende av AI-beslut.

<img src="../../../translated_images/sv/tools-testing.3e1706817b0b3924.webp" alt="Test av verktyg" width="800"/>

*Test av verktyg oberoende som visar mock-exekvering av verktyg utan AI-anrop för att verifiera affärslogik*

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

Dessa tester från `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` validerar verktygslogik utan AI-inblandning. Kedjeexemplet visar hur en verktygsutgång matar in i nästa verktygs indata.

### Mönster 5: In-memory RAG-testning

RAG-system kräver traditionellt vektordatabaser och embeddingtjänster. In-memory-mönstret låter dig testa hela pipelinen utan externa beroenden.

<img src="../../../translated_images/sv/rag-testing.ee7541b1e23934b1.webp" alt="In-memory RAG-testning" width="800"/>

*In-memory RAG-testarbetsflöde som visar dokumentparsing, lagring av embeddingar och likhetssökning utan att kräva en databas*

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

### Mönster 6: MCP-integrations-testning

MCP-modulen testar integrationen av Model Context Protocol med stdio-transport. Dessa tester verifierar att din applikation kan starta och kommunicera med MCP-servrar som underprocesser.

Tester i `05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` validerar MCP-klientens beteende.

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

Testa din kod, inte AI:n. Dina tester bör validera den kod du skriver genom att kontrollera hur prompts konstrueras, hur minnet hanteras och hur verktyg exekveras. AI-svar varierar och bör inte ingå i testassertioner. Fråga dig om din promptmall korrekt substituerar variabler, inte om AI:n ger rätt svar.

Använd mocks för språkmodeller. De är externa beroenden som är långsamma, dyra och icke-deterministiska. Mockning gör testerna snabba (millisekunder istället för sekunder), gratis utan API-kostnader och deterministiska med samma resultat varje gång.

Håll tester oberoende. Varje test bör sätta upp sina egna data, inte förlita sig på andra tester, och städa upp efter sig. Tester bör passera oavsett körningsordning.

Testa hörnfall utöver lyckoscenariot. Prova tomma indata, mycket stora indata, specialtecken, ogiltiga parametrar och gränsvärden. Dessa avslöjar ofta buggar som normal användning inte exponerar.

Använd beskrivande namn. Jämför `shouldMaintainConversationHistoryAcrossMultipleMessages()` med `test1()`. Det första talar om exakt vad som testas, vilket gör felsökning av fel mycket enklare.

## Nästa steg

Nu när du förstår testmönstren, fördjupa dig i varje modul:

- **[00 - Snabbstart](../00-quick-start/README.md)** - Börja med grunderna i promptmallar
- **[01 - Introduktion](../01-introduction/README.md)** - Lär dig hantering av konversationsminne
- **[02 - Promptutformning](../02-prompt-engineering/README.md)** - Bemästra GPT-5-promptmönster
- **[03 - RAG](../03-rag/README.md)** - Bygg retrieval-augmented generation-system
- **[04 - Verktyg](../04-tools/README.md)** - Implementera funktionsanrop och verktygskedjor
- **[05 - MCP](../05-mcp/README.md)** - Integrera Model Context Protocol

Varje moduls README ger detaljerade förklaringar av de koncept som testas här.

---

**Navigation:** [← Tillbaka till huvudsidan](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Ansvarsfriskrivning:
Detta dokument har översatts med hjälp av AI-översättningstjänsten Co-op Translator (https://github.com/Azure/co-op-translator). Vi strävar efter noggrannhet, men var medveten om att automatiska översättningar kan innehålla fel eller brister. Det ursprungliga dokumentet på dess originalspråk bör betraktas som den auktoritativa källan. För viktig information rekommenderas professionell mänsklig översättning. Vi ansvarar inte för eventuella missförstånd eller feltolkningar som uppstår genom användning av denna översättning.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->