<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-30T20:22:17+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "ar"
}
-->
# اختبار تطبيقات LangChain4j

## جدول المحتويات

- [بدء سريع](../../../docs)
- [ما الذي تغطيه الاختبارات](../../../docs)
- [تشغيل الاختبارات](../../../docs)
- [تشغيل الاختبارات في VS Code](../../../docs)
- [أنماط الاختبار](../../../docs)
- [فلسفة الاختبار](../../../docs)
- [الخطوات التالية](../../../docs)

يرشدك هذا الدليل خلال الاختبارات التي توضح كيفية اختبار تطبيقات الذكاء الاصطناعي دون الحاجة إلى مفاتيح API أو خدمات خارجية.

## بدء سريع

شغّل جميع الاختبارات بأمر واحد:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/ar/test-results.ea5c98d8f3642043.png" alt="نتائج الاختبار الناجحة" width="800"/>

*تنفيذ الاختبارات بنجاح يظهر تمرير جميع الاختبارات بدون أي فشل*

## ما الذي تغطيه الاختبارات

تركز هذه الدورة على **اختبارات الوحدة** التي تعمل محليًا. يوضح كل اختبار مفهومًا محددًا في LangChain4j بشكل معزول.

<img src="../../../translated_images/ar/testing-pyramid.2dd1079a0481e53e.png" alt="هرم الاختبار" width="800"/>

*هرم الاختبار يوضح التوازن بين اختبارات الوحدة (سريعة ومعزولة)، اختبارات التكامل (مكونات حقيقية)، والاختبارات الشاملة. يغطي هذا التدريب اختبارات الوحدة.*

| Module | Tests | Focus | Key Files |
|--------|-------|-------|-----------|
| **00 - بدء سريع** | 6 | قوالب الموجه والاستبدال المتغيري | `SimpleQuickStartTest.java` |
| **01 - مقدمة** | 8 | ذاكرة المحادثة والدردشة ذات الحالة | `SimpleConversationTest.java` |
| **02 - هندسة الموجهات** | 12 | أنماط GPT-5 ومستويات الحماس والمخرجات المهيكلة | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | إدخال المستندات، التضمينات، البحث بالتشابه | `DocumentServiceTest.java` |
| **04 - الأدوات** | 12 | استدعاء الوظائف وتسلسل الأدوات | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | بروتوكول سياق النموذج مع نقل stdio | `SimpleMcpTest.java` |

## تشغيل الاختبارات

**شغّل جميع الاختبارات من الجذر:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**شغّل اختبارات وحدة معينة:**

**Bash:**
```bash
cd 01-introduction && mvn test
# أو من الجذر
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# أو من الجذر
mvn --% test -pl 01-introduction
```

**شغّل فصل اختبار واحد:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**شغّل طريقة اختبار محددة:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#ينبغي الحفاظ على سجل المحادثة
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#يجب الحفاظ على سجل المحادثة
```

## تشغيل الاختبارات في VS Code

إذا كنت تستخدم Visual Studio Code، يوفر مستكشف الاختبارات واجهة رسومية لتشغيل وتصحيح الاختبارات.

<img src="../../../translated_images/ar/vscode-testing.f02dd5917289dced.png" alt="مستكشف اختبارات VS Code" width="800"/>

*مستكشف اختبارات VS Code يعرض شجرة الاختبارات مع جميع فصول اختبار Java وطرق الاختبار الفردية*

**لتشغيل الاختبارات في VS Code:**

1. افتح مستكشف الاختبارات بالضغط على أيقونة المخبطة في شريط النشاط
2. وسّع شجرة الاختبارات لرؤية جميع الوحدات وفصول الاختبار
3. اضغط زر التشغيل بجانب أي اختبار لتشغيله بشكل فردي
4. اضغط "تشغيل جميع الاختبارات" لتنفيذ الحزمة كاملة
5. انقر بزر الماوس الأيمن على أي اختبار واختر "تصحيح الاختبار" لتعيين نقاط التوقف والتبع خطوة بخطوة عبر الكود

يعرض مستكشف الاختبارات علامات صح خضراء للاختبارات المارة ويوفر رسائل فشل مفصّلة عند فشل الاختبارات.

## أنماط الاختبار

### النمط 1: اختبار قوالب الموجه

أبسط نمط يختبر قوالب الموجه بدون استدعاء أي نموذج ذكاء اصطناعي. تتحقق من أن استبدال المتغيرات يعمل بشكل صحيح وأن القوالب مُنسقة كما هو متوقع.

<img src="../../../translated_images/ar/prompt-template-testing.b902758ddccc8dee.png" alt="اختبار قالب الموجه" width="800"/>

*اختبار قوالب الموجه يوضح تدفق استبدال المتغيرات: القالب مع نائبات → تطبيق القيم → التحقق من المخرجات المنسقة*

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

هذا الاختبار موجود في `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`.

**شغّله:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#اختبار تنسيق قالب المطالبة
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#اختبار تنسيق قالب المطالبة
```

### النمط 2: محاكاة نماذج اللغة

عند اختبار منطق المحادثة، استخدم Mockito لإنشاء نماذج وهمية تُعيد استجابات محددة مسبقًا. هذا يجعل الاختبارات سريعة ومجانية وحتمية.

<img src="../../../translated_images/ar/mock-vs-real.3b8b1f85bfe6845e.png" alt="مقارنة المحاكاة مقابل API الحقيقي" width="800"/>

*مقارنة توضح سبب تفضيل المحاكيات للاختبار: سريعة، مجانية، حتمية، ولا تتطلب مفاتيح API*

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
        assertThat(history).hasSize(6); // 3 رسائل من المستخدم + 3 رسائل من الذكاء الاصطناعي
    }
}
```

يظهر هذا النمط في `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`. تضمن المحاكاة سلوكًا متسقًا حتى تتمكن من التحقق من أن إدارة الذاكرة تعمل بشكل صحيح.

### النمط 3: اختبار عزل المحادثة

يجب أن تحافظ ذاكرة المحادثة على فصل المستخدمين المتعددين. يتحقق هذا الاختبار من أن المحادثات لا تخلط السياقات.

<img src="../../../translated_images/ar/conversation-isolation.e00336cf8f7a3e3f.png" alt="عزل المحادثة" width="800"/>

*اختبار عزل المحادثة يوضح مخازن ذاكرة منفصلة لمستخدمين مختلفين لمنع اختلاط السياق*

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

تحافظ كل محادثة على تاريخها المستقل. في أنظمة الإنتاج، هذا العزل حاسم لتطبيقات متعددة المستخدمين.

### النمط 4: اختبار الأدوات بشكل مستقل

الأدوات هي وظائف يمكن للذكاء الاصطناعي استدعاؤها. اختبرها مباشرة للتأكد من أنها تعمل بشكل صحيح بغض النظر عن قرارات الذكاء الاصطناعي.

<img src="../../../translated_images/ar/tools-testing.3e1706817b0b3924.png" alt="اختبار الأدوات" width="800"/>

*اختبار الأدوات بشكل مستقل يوضح تنفيذ أداة وهمية بدون استدعاءات الذكاء الاصطناعي للتحقق من منطق العمل*

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

تتحقق هذه الاختبارات من `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` من منطق الأدوات بدون تدخل الذكاء الاصطناعي. يُظهر مثال التسلسل كيف تُغذي مخرجات أداة واحدة مدخلات أخرى.

### النمط 5: اختبار RAG في الذاكرة

تقليديًا تتطلب أنظمة RAG قواعد بيانات متجهية وخدمات تضمين. يسمح نمط الذاكرة باختبار السلسلة بأكملها بدون تبعيات خارجية.

<img src="../../../translated_images/ar/rag-testing.ee7541b1e23934b1.png" alt="اختبار RAG في الذاكرة" width="800"/>

*سير عمل اختبار RAG في الذاكرة يوضح تحليل المستندات، تخزين التضمينات، وبحث التشابه دون الحاجة إلى قاعدة بيانات*

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

هذا الاختبار من `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` ينشئ مستندًا في الذاكرة ويتحقق من التقسيم ومعالجة البيانات الوصفية.

### النمط 6: اختبار تكامل MCP

تختبر وحدة MCP تكامل بروتوكول سياق النموذج باستخدام نقل stdio. تتحقق هذه الاختبارات من أن تطبيقك يمكنه تشغيل خوادم MCP كعمليات فرعية والتواصل معها.

تتحقق الاختبارات في `05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` من سلوك عميل MCP.

**شغّلها:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## فلسفة الاختبار

اختبر كودك، لا تختبر الذكاء الاصطناعي. يجب أن تتحقق اختباراتك من الكود الذي تكتبه عن طريق فحص كيفية بناء القوالب، وكيف تُدار الذاكرة، وكيف تنفذ الأدوات. تختلف استجابات الذكاء الاصطناعي ولا ينبغي أن تكون جزءًا من تأكيدات الاختبار. اسأل نفسك ما إذا كان قالب الموجه يبدّل المتغيرات بشكل صحيح، لا ما إذا كان الذكاء الاصطناعي يعطي الإجابة الصحيحة.

استخدم المحاكيات لنماذج اللغة. إنها تبعيات خارجية بطيئة ومكلفة وغير حتمية. تجعل المحاكاة الاختبارات سريعة بميلي ثانية بدلًا من ثوانٍ، مجانية بدون تكاليف API، وحتمية بنفس النتيجة في كل مرة.

حافظ على استقلالية الاختبارات. يجب أن يهيئ كل اختبار بياناته الخاصة، ألا يعتمد على اختبارات أخرى، وأن ينظف بعد نفسه. يجب أن تنجح الاختبارات بغض النظر عن ترتيب التنفيذ.

اختبر حالات الحافة إلى جانب المسار السعيد. جرّب المدخلات الفارغة، المدخلات الكبيرة جدًا، الأحرف الخاصة، المعلمات غير الصالحة، وظروف الحدود. غالبًا ما تكشف هذه الأخطاء التي لا يظهرها الاستخدام العادي.

استخدم أسماء وصفية. قارن `shouldMaintainConversationHistoryAcrossMultipleMessages()` مع `test1()`. الأول يخبرك تمامًا بما يتم اختباره، مما يجعل تصحيح الأخطاء أسهل بكثير.

## الخطوات التالية

الآن بعد أن فهمت أنماط الاختبار، تعمق في كل وحدة:

- **[00 - بدء سريع](../00-quick-start/README.md)** - ابدأ بأساسيات قوالب الموجهات
- **[01 - مقدمة](../01-introduction/README.md)** - تعلّم إدارة ذاكرة المحادثة
- **[02 - هندسة الموجهات](../02-prompt-engineering/README.md)** - اتقن أنماط التحفيز لـ GPT-5
- **[03 - RAG](../03-rag/README.md)** - ابنِ أنظمة التوليد المعززة بالاسترجاع
- **[04 - الأدوات](../04-tools/README.md)** - نفّذ استدعاء الدوال وسلاسل الأدوات
- **[05 - MCP](../05-mcp/README.md)** - ادمج بروتوكول سياق النموذج

توفر ملفات README لكل وحدة تفسيرات مفصّلة للمفاهيم التي تم اختبارها هنا.

---

**التنقل:** [← العودة إلى الرئيسية](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
إخلاء المسؤولية:
تمت ترجمة هذا المستند باستخدام خدمة الترجمة الآلية Co‑op Translator (https://github.com/Azure/co-op-translator). بينما نحرص على الدقة، يرجى العلم أن الترجمات الآلية قد تحتوي على أخطاء أو معلومات غير دقيقة. يجب اعتبار المستند الأصلي بلغته الأصلية المصدر المعتمد. للمعلومات الحساسة أو الحرجة، يُنصح بالاستعانة بترجمة بشرية محترفة. لا نتحمل أي مسؤولية عن أي سوء فهم أو تفسير خاطئ ينشأ عن استخدام هذه الترجمة.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->