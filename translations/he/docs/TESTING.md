<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-31T02:39:33+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "he"
}
-->
# בדיקת יישומי LangChain4j

## תוכן העניינים

- [התחלה מהירה](../../../docs)
- [מה הבדיקות כוללות](../../../docs)
- [הרצת הבדיקות](../../../docs)
- [הפעלת בדיקות ב־VS Code](../../../docs)
- [דפוסי בדיקה](../../../docs)
- [פילוסופיית בדיקה](../../../docs)
- [שלבים הבאים](../../../docs)

מדריך זה מלווה אותך דרך הבדיקות שמדגימות כיצד לבדוק יישומי AI ללא צורך במפתחות API או שירותים חיצוניים.

## התחלה מהירה

הרץ את כל הבדיקות בפקודה אחת:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/he/test-results.ea5c98d8f3642043.png" alt="תוצאות בדיקה מוצלחות" width="800"/>

*הפעלת בדיקות מוצלחת שמציגה שכל הבדיקות עברו ללא כישלונות*

## מה הבדיקות כוללות

קורס זה מתמקד ב־**בדיקות יחידה** שרצות באופן מקומי. כל בדיקה מדגימה קונספט ספציפי של LangChain4j באופן מבודד.

<img src="../../../translated_images/he/testing-pyramid.2dd1079a0481e53e.png" alt="פירמידת בדיקות" width="800"/>

*פירמידת בדיקות המראה את האיזון בין בדיקות יחידה (מהירות, מבודדות), בדיקות אינטגרציה (רכיבים אמיתיים), ובדיקות מקצה לקצה. הכשרה זו מכסה בדיקות יחידה.*

| מודול | בדיקות | מיקוד | קבצים עיקריים |
|--------|-------|-------|-----------|
| **00 - התחלה מהירה** | 6 | תבניות פרומפט והחלפת משתנים | `SimpleQuickStartTest.java` |
| **01 - מבוא** | 8 | זיכרון שיחה וצ'אט מצבתי | `SimpleConversationTest.java` |
| **02 - הנדסת פרומפטים** | 12 | דפוסי GPT-5, רמות יוזמה, פלט מובנה | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | הכנסת מסמכים, הטמעות, חיפוש דמיון | `DocumentServiceTest.java` |
| **04 - כלים** | 12 | קריאת פונקציות וקישור כלים | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | פרוטוקול Model Context עם תקשורת stdio | `SimpleMcpTest.java` |

## הרצת הבדיקות

**הרץ את כל הבדיקות מהמשתמש הראשי של הפרויקט:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**הרץ בדיקות למודול ספציפי:**

**Bash:**
```bash
cd 01-introduction && mvn test
# או מהשורש
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# או מהשורש
mvn --% test -pl 01-introduction
```

**הרץ מחלקת בדיקה בודדת:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**הרץ שיטת בדיקה ספציפית:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#יש לשמור היסטוריית שיחה
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#יש לשמור היסטוריית שיחות
```

## הפעלת בדיקות ב־VS Code

אם אתה משתמש ב‑Visual Studio Code, ה‑Test Explorer מספק ממשק גרפי להרצה וניפוי שגיאות של בדיקות.

<img src="../../../translated_images/he/vscode-testing.f02dd5917289dced.png" alt="סייר הבדיקות של VS Code" width="800"/>

*סייר הבדיקות של VS Code המציג את עץ הבדיקות עם כל מחלקות הבדיקות ב‑Java ושיטות הבדיקה הפרטניות*

**להרצת בדיקות ב־VS Code:**

1. פתח את ה‑Test Explorer על‑ידי לחיצה על האייקון של הבקבוקון בסרגל הפעילויות
2. הרחב את עץ הבדיקות כדי לראות את כל המודולים ומחלקות הבדיקה
3. לחץ על כפתור ההפעלה ליד כל בדיקה כדי להריץ אותה בנפרד
4. לחץ על "Run All Tests" כדי להפעיל את כל הסוויטה
5. לחץ קליק ימני על כל בדיקה ובחר "Debug Test" כדי להגדיר נקודות עצירה ולצעוד דרך הקוד

סייר הבדיקות מציג סימני בדיקה ירוקים עבור בדיקות שעוברות ומספק הודעות כישלון מפורטות כאשר בדיקות נכשלות.

## דפוסי בדיקה

### דפוס 1: בדיקת תבניות פרומפט

הדפוס הפשוט ביותר בודק תבניות פרומפט מבלי לקרוא למודל AI כלשהו. אתה מאמת שהחלפת המשתנים פועלת נכון ושהפרומפטים מעוצבים כפי שהצפוי.

<img src="../../../translated_images/he/prompt-template-testing.b902758ddccc8dee.png" alt="בדיקת תבניות פרומפט" width="800"/>

*בדיקת תבניות פרומפט המציגה את זרימת החלפת המשתנים: תבנית עם תאי אחסון → החלת ערכים → אימות הפלט המעוצב*

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

בדיקה זו נמצאת ב־`00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`.

**הפעל אותה:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#בדיקת עיצוב תבנית הפרומפט
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#בדיקת עיצוב תבנית ההנחיה
```

### דפוס 2: הדמיית מודלים לשוניים

כאשר בודקים לוגיקת שיחה, השתמש ב‑Mockito ליצירת מודלים מדומיינים שמחזירים תגובות קבועות מראש. זה הופך את הבדיקות למהירות, חינמיות ודטרמיניסטיות.

<img src="../../../translated_images/he/mock-vs-real.3b8b1f85bfe6845e.png" alt="השוואה בין הדמיה ל‑API אמיתי" width="800"/>

*השוואה המדגימה מדוע עדיפים מוקים בבדיקה: הם מהירים, חינמיים, דטרמיניסטיים, ולא זקוקים למפתחות API*

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
        assertThat(history).hasSize(6); // 3 הודעות של משתמש + 3 הודעות של בינה מלאכותית
    }
}
```

דפוס זה מופיע ב־`01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`. המוק מבטיח התנהגות עקבית כך שתוכל לאמת שמניפולצית הזיכרון עובדת כראוי.

### דפוס 3: בדיקת בידוד שיחה

זיכרון השיחה חייב לשמור משתמשים מרובים מופרדים. בדיקה זו מאמתת שהשיחות אינן מערבבות הקשרים.

<img src="../../../translated_images/he/conversation-isolation.e00336cf8f7a3e3f.png" alt="בידוד שיחה" width="800"/>

*בדיקת בידוד שיחה המציגה חנויות זיכרון נפרדות למשתמשים שונים למניעת ערבוב הקשר*

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

כל שיחה שומרת על ההיסטוריה העצמאית שלה. במערכות פרודקשן, בידוד זה קריטי עבור יישומים מרובי משתמשים.

### דפוס 4: בדיקת כלים בנפרד

כלים הם פונקציות שה‑AI יכול לקרוא להן. בדוק אותם ישירות כדי לוודא שהם פועלים נכון ללא תלות בהחלטות ה‑AI.

<img src="../../../translated_images/he/tools-testing.3e1706817b0b3924.png" alt="בדיקת כלים" width="800"/>

*בדיקת כלים בנפרד המציגה הרצת כלים מדומיינים ללא קריאות AI כדי לאמת לוגיקת עסקית*

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

בדיקות אלה מ־`04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` מאמתות את לוגיקת הכלים ללא מעורבות AI. דוגמת השרשור מראה כיצד פלט של כלי אחד מוזן כקלט לכלי אחר.

### דפוס 5: בדיקות RAG בזיכרון

מערכות RAG בדרך‑כלל דורשות מאגרי וקטורים ושירותי הטמעות. דפוס הזיכרון מאפשר לך לבדוק את כל הפייפליין ללא תלות חיצונית.

<img src="../../../translated_images/he/rag-testing.ee7541b1e23934b1.png" alt="בדיקת RAG בזיכרון" width="800"/>

*סביבת בדיקת RAG בזיכרון המציגה ניתוח מסמכים, אחסון הטמעות וחיפוש דמיון ללא צורך בבסיס נתונים*

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

בדיקה זו מתוך `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` יוצרת מסמך בזיכרון ומאמתת חלוקה לחתיכות וטיפול במטא־דאטה.

### דפוס 6: בדיקות אינטגרציה של MCP

מודול ה‑MCP בודק את אינטגרציית Model Context Protocol באמצעות תקשורת stdio. בדיקות אלה מאמתות שהיישום שלך יכול ליצור תהליכים משנה של שרתי MCP ולתקשר איתם.

הבדיקות ב־`05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` מאמתות את התנהגות לקוח ה‑MCP.

**הרץ אותן:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## פילוסופיית בדיקה

בדוק את הקוד שלך, לא את ה‑AI. הבדיקות שלך צריכות לאמת את הקוד שאתה כותב על‑ידי בדיקה כיצד הפרומפטים בנויים, כיצד הזיכרון מנוהל, וכיצד הכלים מתבצעים. תגובות ה‑AI משתנות ולא צריכות להיכלל בהנחות הבדיקה. שאל את עצמך האם תבנית הפרומפט מחליפה משתנים נכון, ולא האם ה‑AI נותן את התשובה הנכונה.

השתמש במוקים עבור מודלים לשוניים. הם תלות חיצונית שהיא איטית, יקרה ולא דטרמיניסטית. שימוש במוקים הופך את הבדיקות למהירות (מילישניות במקום שניות), חינמיות (ללא עלויות API), ודטרמיניסטיות (אותו תוצאה בכל הרצה).

שמור על בדיקות עצמאיות. כל בדיקה צריכה להגדיר את הנתונים שלה, לא להסתמך על בדיקות אחרות, ולנקות אחרי עצמה. בדיקות צריכות לעבור ללא תלות בסדר ההרצה.

בדוק מקרי קצה מעבר לדרך הטובה. נסה קלטים ריקים, קלטים גדולים מאוד, תווים מיוחדים, פרמטרים לא חוקיים ותנאי גבול. אלה לעיתים מגלים באגים ששימוש רגיל לא חושף.

השתמש בשמות תיאוריים. השווה בין `shouldMaintainConversationHistoryAcrossMultipleMessages()` ל־`test1()`. הראשון אומר לך בדיוק מה נבדק, מה שמקל מאוד על איתור תקלות כשבדיקות נכשלות.

## שלבים הבאים

עכשיו כשאתה מבין את דפוסי הבדיקה, העמק בכל מודול:

- **[00 - התחלה מהירה](../00-quick-start/README.md)** - התחילו עם יסודות תבניות הפרומפט
- **[01 - מבוא](../01-introduction/README.md)** - למדו ניהול זיכרון שיחה
- **[02 - הנדסת פרומפטים](../02-prompt-engineering/README.md)** - שלטו בדפוסי פרומפט ל‑GPT-5
- **[03 - RAG](../03-rag/README.md)** - בנו מערכות Retrieval‑Augmented Generation
- **[04 - כלים](../04-tools/README.md)** - מימוש קריאת פונקציות ושרשרויות כלים
- **[05 - MCP](../05-mcp/README.md)** - שלבו את Model Context Protocol

קובץ ה‑README של כל מודול מספק הסברים מפורטים על הקונספטים הנבדקים כאן.

---

**Navigation:** [← חזרה לדף הראשי](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
הצהרת אחריות:
מסמך זה תורגם באמצעות שירות תרגום מבוסס בינה מלאכותית Co-op Translator (https://github.com/Azure/co-op-translator). למרות שאנו שואפים לדייק, יש לשים לב שתרגומים אוטומטיים עלולים להכיל שגיאות או אי־דיוקים. יש להתייחס למסמך המקורי בשפתו כמקור הסמכות. למידע קריטי מומלץ תרגום מקצועי על ידי מתרגם אנושי. איננו נושאים באחריות לכל אי־הבנה או פרשנות מוטעית הנובעת מהשימוש בתרגום זה.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->