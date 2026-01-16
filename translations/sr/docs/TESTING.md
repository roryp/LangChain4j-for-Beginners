<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-31T05:29:04+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "sr"
}
-->
# Тестирање LangChain4j апликација

## Садржај

- [Брзо покретање](../../../docs)
- [Шта тестови покривају](../../../docs)
- [Покретање тестова](../../../docs)
- [Покретање тестова у VS Code](../../../docs)
- [Образци тестирања](../../../docs)
- [Филозофија тестирања](../../../docs)
- [Следећи кораци](../../../docs)

Овај водич води кроз тестове који демонстрирају како тестирати AI апликације без потребе за API кључевима или спољним сервисима.

## Брзо покретање

Покрените све тестове једном командом:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/sr/test-results.ea5c98d8f3642043.png" alt="Успешни резултати тестирања" width="800"/>

*Успешно извршавање тестова — сви тестови су прошли без неуспеха*

## Шта тестови покривају

Овај водич се фокусира на **јединичне тестове** који се изводе локално. Сваки тест демонстрира одређени LangChain4j концепт изоловано.

<img src="../../../translated_images/sr/testing-pyramid.2dd1079a0481e53e.png" alt="Пирамида тестирања" width="800"/>

*Пирамида тестирања која показује равнотежу између јединичних тестова (брзи, изоловани), интеграционих тестова (стварне компоненте) и крај-до-краја тестова. Ова обука покрива јединично тестирање.*

| Модул | Тестови | Фокус | Кључне датотеке |
|--------|-------|-------|-----------|
| **00 - Брзо покретање** | 6 | Шаблони упита и замена променљивих | `SimpleQuickStartTest.java` |
| **01 - Увод** | 8 | Памћење конверзације и состојни чат | `SimpleConversationTest.java` |
| **02 - Инжењеринг упита** | 12 | GPT-5 шаблони, нивои ентузијазма, структурисан излаз | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | Унос докумената, ембеддинзи, претрага по сличности | `DocumentServiceTest.java` |
| **04 - Алатке** | 12 | Позивање функција и повезивање алатки | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | Model Context Protocol са stdio транспортом | `SimpleMcpTest.java` |

## Покретање тестова

**Покрените све тестове из корена:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**Покрените тестове за одређени модул:**

**Bash:**
```bash
cd 01-introduction && mvn test
# Или из корена
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# Или из корена
mvn --% test -pl 01-introduction
```

**Покрените једну тест класу:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**Покрените одређен метод теста:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#требаСачуватиИсторијуРазговора
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#треба одржавати историју разговора
```

## Покретање тестова у VS Code

Ако користите Visual Studio Code, Test Explorer пружа графички интерфејс за покретање и дебаговање тестова.

<img src="../../../translated_images/sr/vscode-testing.f02dd5917289dced.png" alt="Test Explorer у VS Code" width="800"/>

*Test Explorer у VS Code који приказује стабло тестова са свим Java тест класама и појединачним тест методама*

**За покретање тестова у VS Code:**

1. Отворите Test Explorer кликом на иконицу еpruvete (beaker) у Activity Bar-у
2. Проширите стабло тестова да бисте видели све модуле и тест класе
3. Кликните на дугме за покретање поред било ког теста да бисте га покренули појединачно
4. Кликните "Run All Tests" да бисте извршили читав скуп
5. Десни клик на било који тест и изаберите "Debug Test" да поставите тачке прекида и корачате кроз код

Test Explorer приказује зелене ознаке за успешне тестове и пружа детаљне поруке о неуспеху када тестови падну.

## Образци тестирања

### Образац 1: Тестирање шаблона упита

Најједноставнији образац тестира шаблоне упита без позива било ког AI модела. Проверите да ли замена променљивих ради исправно и да ли су упити форматирани како се очекује.

<img src="../../../translated_images/sr/prompt-template-testing.b902758ddccc8dee.png" alt="Тестирање шаблона упита" width="800"/>

*Тестирање шаблона упита које показује ток замене променљивих: шаблон са плейсхолдерима → вредности примењене → форматирани излаз верификован*

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

Овај тест се налази у `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`.

**Покрените га:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#тест форматирања шаблона за упит
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#тест форматирања шаблона упита
```

### Образац 2: Моковање језичких модела

Када тестирате логику разговора, користите Mockito да креирате лажне моделе који враћају унапред одређене одговоре. Ово чини тестове брзим, бесплатним и детерминистичким.

<img src="../../../translated_images/sr/mock-vs-real.3b8b1f85bfe6845e.png" alt="Поређење мока и правог API-ја" width="800"/>

*Поређење које показује зашто су мoкови пожељни за тестирање: брзи су, бесплатни, детерминистички и не захтевају API кључеве*

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
        assertThat(history).hasSize(6); // 3 поруке корисника + 3 поруке вештачке интелигенције
    }
}
```

Овај образац се појављује у `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`. Мок обезбеђује доследно понашање тако да можете верификовати да управљање меморијом ради исправно.

### Образац 3: Тестирање изолације разговора

Меморија разговора мора држати кориснике одвојено. Овај тест верификује да се контексти разговора не мешају.

<img src="../../../translated_images/sr/conversation-isolation.e00336cf8f7a3e3f.png" alt="Изолација разговора" width="800"/>

*Тестирање изолације разговора које приказује одвојена складишта меморије за различите кориснике како би се спречило мешање контекста*

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

Свaki разговор одржава своју независну историју. У продукционм системима, ова изолација је критична за апликације са више корисника.

### Образац 4: Тестирање алатки независно

Алатке су функције које AI може позвати. Тестирајте их директно да бисте осигурали да раде исправно без обзира на одлуке AI-ја.

<img src="../../../translated_images/sr/tools-testing.3e1706817b0b3924.png" alt="Тестирање алатки" width="800"/>

*Тестирање алатки независно које показује извођење мок алатки без позива AI-ју како би се верификовала пословна логика*

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

Ови тестови из `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` верификују логику алатки без укључивања AI-ја. Пример повезивања показује како излаз једне алатке улази у улаз друге.

### Образац 5: RAG тестирање у меморији

RAG системи традиционално захтевају векторске базе података и сервисе за ембеддинге. Образац „у меморији“ вам омогућава да тестирате цео пипелине без спољних зависности.

<img src="../../../translated_images/sr/rag-testing.ee7541b1e23934b1.png" alt="RAG тестирање у меморији" width="800"/>

*Радни ток RAG тестирања у меморији који приказује парсирање докумената, складиште ембеддинга и претрагу по сличности без потребе за базом података*

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

Овај тест из `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` креира документ у меморији и верификује чонковање и руковање метаподацима.

### Образац 6: MCP интеграционо тестирање

MCP модул тестира интеграцију Model Context Protocol коришћењем stdio транспорта. Ови тестови верификују да ваша апликација може да покрене и комуницира са MCP серверима као подпроцесима.

Тестови у `05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` верификују понашање MCP клијента.

**Покрените их:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## Филозофија тестирања

Тестирајте ваш код, не AI. Ваша тестова треба да верификују код који пишете проверавањем како се упити конструишу, како се управља меморијом и како алатке извршавају. AI одговори варирају и не би требало да буду део тестних асерција. Питајте себе да ли ваш шаблон упита правилно замењује променљиве, а не да ли AI даје „прави“ одговор.

Користите мoкове за језичке моделе. Они су спољне зависности које су споре, скупе и недетерминистичке. Моковање чини тестове брзим — милисекунде уместо секунди, бесплатним без трошкова API-ја и детерминистичким са истим резултатом сваки пут.

Држите тестове независним. Сваки тест треба да постави своје податке, да се не ослања на друге тестове и да очисти за собом. Тестови треба да пролазе без обзира на редослед извршавања.

Тестирајте и рубне случајеве ван срећног пута. Испробајте празне уносе, веома велике уносе, специјалне карактере, неважеће параметре и граничне услове. Ово често открива багове које нормална употреба не показује.

Користите описне називе. Упоредите `shouldMaintainConversationHistoryAcrossMultipleMessages()` са `test1()`. Први вам тачно говори шта се тестира, што чини отклањање грешака много лакшим.

## Следећи кораци

Сада када разумете образце тестирања, проучите детаљније сваки модул:

- **[00 - Брзо покретање](../00-quick-start/README.md)** - Почните са основама шаблона упита
- **[01 - Увод](../01-introduction/README.md)** - Научите управљање меморијом разговора
- **[02 - Инжењеринг упита](../02-prompt-engineering/README.md)** - Усавршите GPT-5 обрасце упита
- **[03 - RAG](../03-rag/README.md)** - Изградите системе за retrieval-augmented generation
- **[04 - Алатке](../04-tools/README.md)** - Имплементирајте позиве функција и ланце алатки
- **[05 - MCP](../05-mcp/README.md)** - Интегришите Model Context Protocol

Сваки модулни README пружа детаљна објашњења концепата који су овде тестирани.

---

**Навигација:** [← Назад на главну](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Изјава о одрицању одговорности**:
Овај документ је преведен помоћу сервиса за превођење који користи вештачку интелигенцију — [Co-op Translator](https://github.com/Azure/co-op-translator). Иако тежимо тачности, имајте у виду да аутоматски преводи могу да садрже грешке или нетачности. Оригинални документ на матерњем језику треба сматрати меродавним извором. За критичне информације препоручује се професионални превод који обављају људски преводиоци. Не сносимо одговорност за било какве неспоразуме или погрешна тумачења која произилазе из употребе овог превода.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->