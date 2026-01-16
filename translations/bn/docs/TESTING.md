<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-30T22:46:48+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "bn"
}
-->
# LangChain4j অ্যাপ্লিকেশনগুলির টেস্টিং

## বিষয়সূচি

- [দ্রুত শুরু](../../../docs)
- [টেস্টগুলো কী কভার করে](../../../docs)
- [টেস্ট চালানো](../../../docs)
- [VS Code-এ টেস্ট চালানো](../../../docs)
- [টেস্টিং প্যাটার্নসমূহ](../../../docs)
- [টেস্টিং দর্শন](../../../docs)
- [পরবর্তী ধাপসমূহ](../../../docs)

এই গাইডটি আপনাকে এমন টেস্টগুলো দেখাবে যেগুলো API কী বা বাহ্যিক সার্ভিস ছাড়াই AI অ্যাপ্লিকেশনগুলো টেস্ট করার উপায় প্রদর্শন করে।

## দ্রুত শুরু

একটি কমান্ডে সব টেস্ট চালান:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/bn/test-results.ea5c98d8f3642043.png" alt="সফল টেস্ট ফলাফল" width="800"/>

*শূন্য ব্যর্থতার সাথে সমস্ত টেস্ট পাস হওয়ার সফল টেস্ট কার্যকরির চিত্র*

## টেস্টগুলো কী কভার করে

এই কোর্সটি লোকালভাবে চলা **ইউনিট টেস্ট**-গুলোর ওপর কেন্দ্রীভূত। প্রতিটি টেস্ট একটি নির্দিষ্ট LangChain4j ধারণা আলাদা করে প্রদর্শন করে।

<img src="../../../translated_images/bn/testing-pyramid.2dd1079a0481e53e.png" alt="টেস্টিং পিরামিড" width="800"/>

*টেস্টিং পিরামিডটি ইউনিট টেস্ট (দ্রুত, আলাদা), ইন্টিগ্রেশন টেস্ট (বাস্তব উপাদান), এবং এন্ড-টু-এন্ড টেস্টের মধ্যে ভারসাম্য দেখায়। এই প্রশিক্ষণটি ইউনিট টেস্টিং কভার করে।*

| মডিউল | টেস্ট | ফোকাস | কী ফাইলগুলি |
|--------|-------|-------|-----------|
| **00 - Quick Start** | 6 | প্রম্পট টেমপ্লেট এবং ভ্যারিয়েবল সাবস্টিটিউশন | `SimpleQuickStartTest.java` |
| **01 - Introduction** | 8 | কনভারসেশন মেমরি এবং স্টেটফুল চ্যাট | `SimpleConversationTest.java` |
| **02 - Prompt Engineering** | 12 | GPT-5 প্যাটার্ন, eagerness স্তর, স্ট্রাকচার্ড আউটপুট | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | ডকুমেন্ট ইনজেশন, এমবেডিং, সিমিলারিটি সার্চ | `DocumentServiceTest.java` |
| **04 - Tools** | 12 | ফাংশন কলিং এবং টুল চেইনিং | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | স্টডিঅ’ ট্রান্সপোর্ট সহ Model Context Protocol | `SimpleMcpTest.java` |

## টেস্ট চালানো

**রুট থেকে সব টেস্ট চালান:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**নির্দিষ্ট মডিউলের টেস্ট চালান:**

**Bash:**
```bash
cd 01-introduction && mvn test
# অথবা রুট থেকে
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# অথবা রুট থেকে
mvn --% test -pl 01-introduction
```

**একটি নির্দিষ্ট টেস্ট ক্লাস চালান:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**নির্দিষ্ট টেস্ট মেথড চালান:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#কথোপকথনের ইতিহাস বজায় রাখা উচিত
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#কথোপকথনের ইতিহাস বজায় রাখা উচিত
```

## VS Code-এ টেস্ট চলানো

আপনি যদি Visual Studio Code ব্যবহার করে থাকেন, Test Explorer টেস্টগুলো চালানো ও ডিবাগ করার একটি গ্রাফিকাল ইন্টারফেস প্রদান করে।

<img src="../../../translated_images/bn/vscode-testing.f02dd5917289dced.png" alt="VS Code টেস্ট এক্সপ্লোরার" width="800"/>

*VS Code Test Explorer-এ টেস্ট ট্রি দেখা যাচ্ছে যেখানে সমস্ত Java টেস্ট ক্লাস এবং পৃথক টেস্ট মেথডগুলো রয়েছে*

**VS Code-এ টেস্ট চালানোর জন্য:**

1. Activity Bar-এ বীকার আইকনে ক্লিক করে Test Explorer খুলুন
2. সমস্ত মডিউল এবং টেস্ট ক্লাস দেখতে টেস্ট ট্রি এক্সপ্যান্ড করুন
3. কোনো টেস্টের পাশে থাকা প্লে বাটনে ক্লিক করে সেটি আলাদাভাবে চালান
4. "Run All Tests" ক্লিকে পুরো সুইটটি এক্সিকিউট করুন
5. কোনো টেস্টে রাইট-ক্লিক করে "Debug Test" নির্বাচন করুন ব্রেকপয়েন্ট সেট করে কোড স্টেপিং করতে

Test Explorer পাস করা টেস্টগুলোর জন্য সবুজ চেকমার্ক দেখায় এবং টেস্ট ফেল করলে বিস্তারিত ফেলিয়ার মেসেজ দেয়।

## টেস্টিং প্যাটার্নসমূহ

### প্যাটার্ন 1: প্রম্পট টেমপ্লেট টেস্ট করা

সবচেয়ে সহজ প্যাটার্নটি যে প্রম্পট টেমপ্লেটগুলোকে AI মডেল কল না করে টেস্ট করে। আপনি যাচাই করবেন যে ভ্যারিয়েবল সাবস্টিটিউশন সঠিকভাবে কাজ করছে এবং প্রম্পটগুলি প্রত্যাশিতভাবে ফরম্যাট হয়েছে।

<img src="../../../translated_images/bn/prompt-template-testing.b902758ddccc8dee.png" alt="প্রম্পট টেমপ্লেট টেস্টিং" width="800"/>

*প্রম্পট টেমপ্লেট টেস্টিং দেখাচ্ছে ভ্যারিয়েবল সাবস্টিটিউশন ফ্লো: প্লেসহোল্ডারসহ টেমপ্লেট → মান প্রয়োগ → ফরম্যাট করা আউটপুট যাচাই করা*

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

এই টেস্টটি থাকে `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`-এ।

**এটি চালান:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#টেস্ট প্রম্পট টেমপ্লেট ফরম্যাটিং
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#প্রম্পট টেমপ্লেট বিন্যাস পরীক্ষা
```

### প্যাটার্ন 2: ল্যাঙ্গুয়েজ মডেল মক করা

কনভারসেশন লজিক টেস্ট করার সময়, নির্ধারিত রেসপন্স রিটার্ন করা জেনুইন মডেলগুলোর বদলে Mockito ব্যবহার করে ফেক মডেল তৈরি করুন। এতে টেস্টগুলো দ্রুত, বিনামূল্য এবং ডিটারমিনিস্টিক হয়।

<img src="../../../translated_images/bn/mock-vs-real.3b8b1f85bfe6845e.png" alt="মক বনাম বাস্তব API তুলনা" width="800"/>

*তুলনা দেখায় কেন মকগুলো টেস্টিংয়ের জন্য প্রেফার করা হয়: এগুলো দ্রুত, বিনামূল্য, ডিটারমিনিস্টিক এবং কোনো API কী দরকার হয় না*

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
        assertThat(history).hasSize(6); // 3 ব্যবহারকারী + 3 এআই বার্তা
    }
}
```

এই প্যাটার্নটি প্রদর্শিত হয় `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`-এ। মকটি ধারাবাহিক আচরণ নিশ্চিত করে যাতে আপনি মেমরি ম্যানেজমেন্ট সঠিকভাবে কাজ করে কিনা যাচাই করতে পারেন।

### প্যাটার্ন 3: কনভারসেশন আইসোলেশন টেস্ট করা

কনভারসেশন মেমরিটি একাধিক ব্যবহারকারীর আলাদা থাকা নিশ্চিত করতে হবে। এই টেস্ট নিশ্চিত করে যে কনভারসেশনগুলো কনটেক্সট মিশ্রিত করে না।

<img src="../../../translated_images/bn/conversation-isolation.e00336cf8f7a3e3f.png" alt="কনভারসেশন আইসোলেশন" width="800"/>

*কনভারসেশন আইসোলেশন টেস্টিং দেখায় আলাদা ব্যবহারকারীদের জন্য আলাদা মেমরি স্টোর যাতে কনটেক্সট মিক্স হওয়া রোধ করা যায়*

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

প্রতিটি কনভারসেশন তার নিজের স্বাধীন ইতিহাস বজায় রাখে। প্রোডাকশনে, এই আইসোলেশন মাল্টি-ইউজার অ্যাপ্লিকেশনের জন্য অত্যন্ত গুরুত্বপূর্ণ।

### প্যাটার্ন 4: টুলগুলো স্বাধীনভাবে টেস্ট করা

টুলগুলো হলো AI যে ফাংশনগুলো কল করতে পারে। AI সিদ্ধান্তের বাইরে এগুলো সরাসরি টেস্ট করুন যাতে নিশ্চিত হওয়া যায় সেগুলো সঠিকভাবে কাজ করে।

<img src="../../../translated_images/bn/tools-testing.3e1706817b0b3924.png" alt="টুল টেস্টিং" width="800"/>

*টুলগুলো স্বাধীনভাবে টেস্ট করা দেখায় মক টুল এক্সিকিউশন AI কল ছাড়াই ব্যবসায়িক লজিক যাচাই করার জন্য*

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

এই টেস্টগুলো `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java`-এ থেকে এসেছে এবং AI অংশ ছাড়াই টুল লজিক যাচাই করে। চেইনিং উদাহরণটি দেখায় কীভাবে এক টুলের আউটপুট আরেকটির ইনপুটে যায়।

### প্যাটার্ন 5: ইন-মেমরি RAG টেস্টিং

RAG সিস্টেমগুলো সাধারণত ভেক্টর ডাটাবেস এবং এমবেডিং সার্ভিস প্রয়োজন করে। ইন-মেমরি প্যাটার্ন আপনাকে সম্পূর্ণ পাইপলাইনটি বাহ্যিক নির্ভরতা ছাড়াই টেস্ট করতে দেয়।

<img src="../../../translated_images/bn/rag-testing.ee7541b1e23934b1.png" alt="ইন-মেমরি RAG টেস্টিং" width="800"/>

*ইন-মেমরি RAG টেস্টিং ওয়ার্কফ্লো দেখায় ডকুমেন্ট পারসিং, এমবেডিং স্টোরেজ, এবং সিমিলারিটি সার্চ ডাটাবেস ছাড়াই*

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

এই টেস্টটি `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java`-এ একটি ডকুমেন্ট ইন-মেমরিতে তৈরি করে এবং চাঙ্কিং ও মেটাডেটা হ্যান্ডলিং যাচাই করে।

### প্যাটার্ন 6: MCP ইন্টিগ্রেশন টেস্টিং

MCP মডিউলটি stdio ট্রান্সপোর্ট ব্যবহার করে Model Context Protocol ইন্টিগ্রেশন টেস্ট করে। এই টেস্টগুলো নিশ্চিত করে যে আপনার অ্যাপ্লিকেশন সাবপ্রসেস হিসেবে MCP সার্ভার স্পন ও কমিউনিকেট করতে পারে।

`05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java`-এর টেস্টগুলো MCP ক্লায়েন্ট আচরণ যাচাই করে।

**এগুলো চালান:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## টেস্টিং দর্শন

আপনার কোড টেস্ট করুন, AI নয়। আপনার টেস্টগুলো অবশ্যই আপনার লেখা কোডকে যাচাই করবে—কীভাবে প্রম্পট নির্মিত হয়, কিভাবে মেমরি ব্যবস্থাপনা করা হয়, এবং কিভাবে টুলগুলো এক্সিকিউট করে—এই বিষয়গুলো পরীক্ষা করে। AI রেসপন্স ভ্যারিয়েবল এবং সেগুলোকে টেস্ট অ্যাসারশন হিসেবে রাখা উচিৎ নয়। নিজের কাছে জিজ্ঞাসা করুন যে আপনার প্রম্পট টেমপ্লেট সঠিকভাবে ভ্যারিয়েবল সাবস্টিটিউশন করছে কিনা, না কি AI সঠিক উত্তর দিচ্ছে কিনা।

ল্যাঙ্গুয়েজ মডেলগুলোর জন্য মক ব্যবহার করুন। এগুলো বাহ্যিক নির্ভরতা যা ধীর, ব্যয়বহুল, এবং অ-ডিটারমিনিস্টিক। মকিং টেস্টগুলোকে মিলিসেকেন্ডে দ্রুত, কোনো API খরচ ছাড়াই বিনামূল্য, এবং প্রতিবার একই ফলাফল দেয় এমন করে তোলে।

টেস্টগুলো স্বাধীন রাখুন। প্রতিটি টেস্ট নিজস্ব ডেটা সেটআপ করবে, অন্য টেস্টের ওপর নির্ভর করবে না, এবং নিজেই ক্লিনআপ করবে। টেস্টগুলো নির্বাহের অর্ডার যাই হোক পাস হওয়া উচিত।

হ্যাপি পাথ ছাড়াও এজ কেস টেস্ট করুন। খালি ইনপুট, খুব বড় ইনপুট, বিশেষ ক্যারেক্টার, অবৈধ প্যারামিটার, এবং বাউন্ডারি কন্ডিশন চেষ্টা করুন। এইগুলো প্রায়ই এমন বাগ উন্মোচন করে যা সাধারণ ব্যবহারে দেখা যায় না।

বর্ণনামূলক নাম ব্যবহার করুন। `shouldMaintainConversationHistoryAcrossMultipleMessages()`-এর সাথে `test1()` তুলনা করুন। প্রথমটি আপনাকে ঠিক কী পরীক্ষা করা হচ্ছে তা বলে, ফলে ব্যর্থতা ডিবাগ করা অনেক সহজ হয়।

## পরবর্তী ধাপসমূহ

এখন যখন আপনি টেস্টিং প্যাটার্নগুলো বুঝে গেছেন, প্রতিটি মডিউলে গভীরভাবে যান:

- **[00 - Quick Start](../00-quick-start/README.md)** - প্রম্পট টেমপ্লেটের বেসিক দিয়ে শুরু করুন
- **[01 - Introduction](../01-introduction/README.md)** - কনভারসেশন মেমরি ম্যানেজমেন্ট শিখুন
- **[02 - Prompt Engineering](../02-prompt-engineering/README.md)** - GPT-5 প্রম্পটিং প্যাটার্নগুলিতে দক্ষ হয়ে উঠুন
- **[03 - RAG](../03-rag/README.md)** - রিট্রিভাল-অগমেন্টেড জেনারেশন সিস্টেম গঠন করুন
- **[04 - Tools](../04-tools/README.md)** - ফাংশন কলিং এবং টুল চেইন বাস্তবায়ন করুন
- **[05 - MCP](../05-mcp/README.md)** - Model Context Protocol ইন্টিগ্রেট করুন

প্রতিটি মডিউলের README এখানে টেস্ট করা ধারণাগুলোর বিস্তারিত ব্যাখ্যা প্রদান করে।

---

**নেভিগেশন:** [← মূল পৃষ্ঠায় ফিরে](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
অস্বীকৃতি:

এই নথিটি AI অনুবাদ সেবা Co-op Translator (https://github.com/Azure/co-op-translator) ব্যবহার করে অনুবাদ করা হয়েছে। আমরা যথাসাধ্য সঠিকতা নিশ্চিত করার চেষ্টা করি; তবুও অনুগ্রহ করে জানুন যে স্বয়ংক্রিয় অনুবাদে ত্রুটি বা ভুল থাকতে পারে। মূল নথিটি তার মাতৃভাষায়ই কর্তৃত্বশীল উৎস হিসেবে বিবেচনা করা উচিত। গুরুত্বপূর্ণ তথ্যের জন্য পেশাদার মানব অনুবাদ গ্রহণ করার পরামর্শ দেওয়া হয়। এই অনুবাদের ব্যবহার থেকে উদ্ভূত কোনো ভুলবোঝাবুঝি বা ভ্রান্ত ব্যাখ্যার জন্য আমরা দায়ী নই।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->