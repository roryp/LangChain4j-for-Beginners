<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-31T04:26:00+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "cs"
}
-->
# Testování aplikací LangChain4j

## Obsah

- [Rychlý start](../../../docs)
- [Co testy pokrývají](../../../docs)
- [Spouštění testů](../../../docs)
- [Spouštění testů ve VS Code](../../../docs)
- [Vzorové postupy testování](../../../docs)
- [Filozofie testování](../../../docs)
- [Další kroky](../../../docs)

Tento průvodce vás provede testy, které ukazují, jak testovat AI aplikace, aniž byste potřebovali API klíče nebo externí služby.

## Rychlý start

Spusťte všechny testy jediným příkazem:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/cs/test-results.ea5c98d8f3642043.png" alt="Úspěšné výsledky testů" width="800"/>

*Úspěšné spuštění testů ukazující, že všechny testy proběhly bez chyb*

## Co testy pokrývají

Tento kurz se zaměřuje na **jednotkové testy**, které běží lokálně. Každý test demonstruje konkrétní koncept LangChain4j izolovaně.

<img src="../../../translated_images/cs/testing-pyramid.2dd1079a0481e53e.png" alt="Testovací pyramida" width="800"/>

*Testovací pyramida ukazující vyváženost mezi jednotkovými testy (rychlé, izolované), integračními testy (skutečné komponenty) a end-to-end testy. Tento kurz pokrývá jednotkové testování.*

| Modul | Testy | Zaměření | Hlavní soubory |
|--------|-------|-------|-----------|
| **00 - Rychlý start** | 6 | Šablony promptů a nahrazování proměnných | `SimpleQuickStartTest.java` |
| **01 - Úvod** | 8 | Paměť konverzace a stavový chat | `SimpleConversationTest.java` |
| **02 - Návrh promptů** | 12 | Vzory GPT-5, úrovně ochoty, strukturovaný výstup | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | Import dokumentů, embeddings, vyhledávání podle podobnosti | `DocumentServiceTest.java` |
| **04 - Nástroje** | 12 | Volání funkcí a řetězení nástrojů | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | Model Context Protocol s přenosem přes stdio | `SimpleMcpTest.java` |

## Spouštění testů

**Spusťte všechny testy z kořenového adresáře:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**Spusťte testy pro konkrétní modul:**

**Bash:**
```bash
cd 01-introduction && mvn test
# Nebo z kořenového adresáře
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# Nebo z kořenového adresáře
mvn --% test -pl 01-introduction
```

**Spusťte jednu testovací třídu:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**Spusťte konkrétní testovací metodu:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#mělo by uchovat historii konverzace
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#mělo by uchovávat historii konverzace
```

## Spouštění testů ve VS Code

Pokud používáte Visual Studio Code, Test Explorer poskytuje grafické rozhraní pro spouštění a ladění testů.

<img src="../../../translated_images/cs/vscode-testing.f02dd5917289dced.png" alt="Prohlížeč testů VS Code" width="800"/>

*Prohlížeč testů VS Code ukazující strom testů se všemi Java testovacími třídami a jednotlivými testovacími metodami*

**Pro spuštění testů ve VS Code:**

1. Otevřete Test Explorer kliknutím na ikonu zkumavky v postranním panelu aktivit
2. Rozbalte strom testů, abyste viděli všechny moduly a testovací třídy
3. Klikněte na tlačítko přehrát vedle libovolného testu pro jeho spuštění jednotlivě
4. Klikněte na "Run All Tests" pro spuštění celé sady
5. Klikněte pravým tlačítkem na jakýkoliv test a vyberte "Debug Test" pro nastavení breakpointů a krokování kódem

Test Explorer zobrazuje zelené fajfky pro úspěšné testy a poskytuje podrobné chybové zprávy při selháních.

## Vzorové postupy testování

### Vzor 1: Testování šablon promptů

Nejjednodušší vzor testuje šablony promptů bez volání jakéhokoli AI modelu. Ověříte, že nahrazování proměnných funguje správně a prompt je naformátován podle očekávání.

<img src="../../../translated_images/cs/prompt-template-testing.b902758ddccc8dee.png" alt="Testování šablon promptů" width="800"/>

*Testování šablon promptů ukazující tok nahrazování proměnných: šablona s placeholdery → aplikované hodnoty → ověřený naformátovaný výstup*

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

Tento test se nachází v `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`.

**Spusťte ho:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#testFormátováníŠablonyVýzvy
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#testování formátování šablony promptu
```

### Vzor 2: Mockování jazykových modelů

Při testování logiky konverzace použijte Mockito k vytvoření falešných modelů, které vracejí předem určené odpovědi. Díky tomu jsou testy rychlé, zdarma a deterministické.

<img src="../../../translated_images/cs/mock-vs-real.3b8b1f85bfe6845e.png" alt="Porovnání mock vs reálné API" width="800"/>

*Porovnání ukazující, proč jsou mocky preferovány pro testování: jsou rychlé, zdarma, deterministické a nevyžadují API klíče*

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
        assertThat(history).hasSize(6); // 3 uživatelské zprávy + 3 zprávy AI
    }
}
```

Tento vzor se objevuje v `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`. Mock zajišťuje konzistentní chování, takže můžete ověřit, že správa paměti funguje správně.

### Vzor 3: Testování izolace konverzací

Paměť konverzace musí udržovat oddělené uživatele. Tento test ověřuje, že se kontexty nekombinují.

<img src="../../../translated_images/cs/conversation-isolation.e00336cf8f7a3e3f.png" alt="Izolace konverzací" width="800"/>

*Testování izolace konverzací ukazující oddělené úložiště paměti pro různé uživatele, aby se zabránilo míchání kontextu*

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

Každá konverzace si udržuje vlastní nezávislou historii. V produkčních systémech je tato izolace kritická pro víceuživatelské aplikace.

### Vzor 4: Testování nástrojů nezávisle

Nástroje jsou funkce, které může AI volat. Testujte je přímo, abyste zajistili, že fungují správně nezávisle na rozhodnutích AI.

<img src="../../../translated_images/cs/tools-testing.3e1706817b0b3924.png" alt="Testování nástrojů" width="800"/>

*Testování nástrojů nezávisle ukazující spuštění mock nástroje bez volání AI pro ověření business logiky*

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

Tyto testy z `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` ověřují logiku nástrojů bez zapojení AI. Příklad řetězení ukazuje, jak výstup jednoho nástroje vstupuje do dalšího.

### Vzor 5: Testování RAG v paměti

Systémy RAG tradičně vyžadují vektorové databáze a embedding služby. Vzor v paměti vám umožní otestovat celý pipeline bez externích závislostí.

<img src="../../../translated_images/cs/rag-testing.ee7541b1e23934b1.png" alt="Testování RAG v paměti" width="800"/>

*Workflow testování RAG v paměti ukazující parsování dokumentů, ukládání embeddingů a vyhledávání podle podobnosti bez nutnosti databáze*

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

Tento test z `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` vytvoří dokument v paměti a ověří dělení na části a zpracování metadat.

### Vzor 6: Integrační testování MCP

Modul MCP testuje integraci Model Context Protocol pomocí stdio transportu. Tyto testy ověřují, že vaše aplikace může spawnout a komunikovat se servery MCP jako podprocesy.

Testy v `05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` ověřují chování MCP klienta.

**Spusťte je:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## Filozofie testování

Testujte svůj kód, ne AI. Vaše testy by měly validovat kód, který píšete, tím, že kontrolují, jak jsou prompt šablony konstruovány, jak je spravována paměť a jak nástroje fungují. Odpovědi AI se mění a neměly by být součástí testovacích asercí. Zeptejte se sami sebe, zda vaše šablona promptu správně nahrazuje proměnné, ne zda AI dává „správnou“ odpověď.

Používejte mocky pro jazykové modely. Jsou to externí závislosti, které jsou pomalé, drahé a nedeterministické. Mockování dělá testy rychlými v milisekundách místo sekund, bez nákladů na API a deterministickými se stejným výsledkem pokaždé.

Udržujte testy nezávislé. Každý test by měl nastavit vlastní data, neměl by spoléhat na jiné testy a měl by se po sobě uklidit. Testy by měly projít bez ohledu na pořadí spuštění.

Testujte hraniční případy mimo šťastnou cestu. Zkuste prázdné vstupy, velmi velké vstupy, speciální znaky, neplatné parametry a hraniční podmínky. Tyto případy často odhalí chyby, které běžné používání neukáže.

Používejte popisná jména. Porovnejte `shouldMaintainConversationHistoryAcrossMultipleMessages()` s `test1()`. První přesně říká, co se testuje, což výrazně usnadňuje hledání chyb.

## Další kroky

Nyní, když rozumíte vzorům testování, ponořte se hlouběji do každého modulu:

- **[00 - Rychlý start](../00-quick-start/README.md)** - Začněte se základy šablon promptů
- **[01 - Úvod](../01-introduction/README.md)** - Naučte se správu paměti konverzací
- **[02 - Návrh promptů](../02-prompt-engineering/README.md)** - Ovládněte vzory promptování pro GPT-5
- **[03 - RAG](../03-rag/README.md)** - Vytvořte systémy retrieval-augmented generation
- **[04 - Nástroje](../04-tools/README.md)** - Implementujte volání funkcí a řetězce nástrojů
- **[05 - MCP](../05-mcp/README.md)** - Integrujte Model Context Protocol

Každé README modulu poskytuje podrobné vysvětlení konceptů testovaných zde.

---

**Navigace:** [← Zpět na hlavní](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Prohlášení o vyloučení odpovědnosti**:
Tento dokument byl přeložen pomocí služby pro překlad s využitím umělé inteligence [Co-op Translator](https://github.com/Azure/co-op-translator). I když usilujeme o přesnost, vezměte prosím na vědomí, že automatické překlady mohou obsahovat chyby nebo nepřesnosti. Originální dokument v jeho mateřském jazyce by měl být považován za rozhodující zdroj. Pro kritické informace se doporučuje využít profesionální lidský překlad. Nezodpovídáme za jakákoli nedorozumění nebo chybné výklady vyplývající z použití tohoto překladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->