<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-30T23:03:28+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "mr"
}
-->
# LangChain4j अनुप्रयोगांची चाचणी

## निर्देशिका

- [जलद प्रारंभ](../../../docs)
- [चाचण्या काय कव्हर करतात](../../../docs)
- [चाचण्या कशा चालवायच्या](../../../docs)
- [VS Code मध्ये चाचण्या चालवणे](../../../docs)
- [चाचणी नमुने](../../../docs)
- [चाचणी तत्त्वज्ञान](../../../docs)
- [पुढील पाउले](../../../docs)

हे मार्गदर्शक तुम्हाला अशा चाचण्यांमधून घेऊन जाते ज्याद्वारे API की किंवा बाह्य सेवा न वापरता AI अनुप्रयोग कसे चाचणी करायचे ते दाखवले आहे.

## Quick Start

एकाच कमांडने सर्व चाचण्या चालवा:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/mr/test-results.ea5c98d8f3642043.png" alt="यशस्वी चाचणी परिणाम" width="800"/>

*सर्व चाचण्या शून्य अपयशांसह यशस्वीपणे पार पडल्याचे परिणाम दाखवण्यात आले आहेत*

## चाचण्या काय कव्हर करतात

हा कोर्स स्थानिक पातळीवरील **युनिट चाचण्यांवर** लक्ष केंद्रित करतो. प्रत्येक चाचणी स्वतंत्रपणे एका विशिष्ट LangChain4j संकल्पनेचे प्रदर्शन करते.

<img src="../../../translated_images/mr/testing-pyramid.2dd1079a0481e53e.png" alt="चाचणी पिरॅमिड" width="800"/>

*चाचणी पिरॅमिड जेथे युनिट चाचण्या (वेगवान, स्वतंत्र), इंटिग्रेशन चाचण्या (खऱ्या घटकांसह), आणि end-to-end चाचण्या यांचा समतोल दाखवला आहे. हे प्रशिक्षण युनिट चाचणींवर केंद्रित आहे.*

| Module | Tests | Focus | Key Files |
|--------|-------|-------|-----------|
| **00 - Quick Start** | 6 | Prompt templates आणि व्हेरिएबल सब्स्टिट्यूशन | `SimpleQuickStartTest.java` |
| **01 - Introduction** | 8 | संभाषणाची स्मृती आणि स्थितीपूर्ण चॅट | `SimpleConversationTest.java` |
| **02 - Prompt Engineering** | 12 | GPT-5 पॅटर्न्स, उत्सुकता पातळ्या, संरचित आउटपुट | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | दस्तऐवज इन्गेस्टन, एम्बेडिंग्स, सिमिलॅरिटी सर्च | `DocumentServiceTest.java` |
| **04 - Tools** | 12 | फंक्शन कॉलिंग आणि टूल चेनिंग | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | Model Context Protocol सह Stdio ट्रान्सपोर्ट | `SimpleMcpTest.java` |

## चाचण्या कशा चालवायच्या

**रूटमधून सर्व चाचण्या चालवा:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**विशिष्ट मॉड्युलसाठी चाचण्या चालवा:**

**Bash:**
```bash
cd 01-introduction && mvn test
# किंवा रूटमधून
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# किंवा रूटमधून
mvn --% test -pl 01-introduction
```

**एखादी एकल टेस्ट क्लास चालवा:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**विशिष्ट टेस्ट पद्धत चालवा:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#संवादाचा इतिहास जतन करावा
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#संवाद इतिहास जतन करावा
```

## VS Code मध्ये चाचण्या चालवणे

तुम्ही Visual Studio Code वापरत असाल तर Test Explorer चा ग्राफिकल इंटरफेस चाचण्या चालवण्यासाठी आणि डिबग करण्यासाठी उपलब्ध आहे.

<img src="../../../translated_images/mr/vscode-testing.f02dd5917289dced.png" alt="VS Code Test Explorer" width="800"/>

*VS Code Test Explorer ज्यात सर्व Java टेस्ट क्लासेस आणि वेगवेगळ्या टेस्ट पद्धतींच्या झाडासारखा दृष्यरूप आहे*

**VS Code मध्ये चाचण्या चालवण्यासाठी:**

1. Activity Bar मधील बीकर आयकॉन क्लिक करून Test Explorer उघडा
2. सर्व मॉड्युल्स आणि टेस्ट क्लासेस पाहण्यासाठी टेस्ट ट्री विस्तृत करा
3. एखाद्या टेस्टच्या बाजूला असलेले प्ले बटण क्लिक करून ती स्वतंत्रपणे चालवा
4. संपूर्ण सूट चालवण्यासाठी "Run All Tests" क्लिक करा
5. कोणत्याही टेस्टवर उजवे-क्लिक करून "Debug Test" निवडा जेणेकरून ब्रेकपॉइंट सेट करून कोड स्टेप-बाय-स्टेप तपासता येईल

Test Explorer पास झालेल्या चाचण्यांसाठी हिरवे चेकमार्क दाखवते आणि चाचण्या अपयशी झाल्यास तपशीलवार अपयश संदेश देते.

## चाचणी नमुने

### नमुना 1: प्रॉम्प्ट टेम्पलेट्सची चाचणी

सर्वात सोपा नमुना AI मॉडेल कॉल न करता प्रॉम्प्ट टेम्पलेट्सची चाचणी करतो. तुम्ही पडणारे व्हेरिएबल योग्यरित्या बदलले जातात की नाही आणि प्रॉम्प्ट अपेक्षेप्रमाणे फॉरमॅट झाले आहेत का याची पडताळणी करता.

<img src="../../../translated_images/mr/prompt-template-testing.b902758ddccc8dee.png" alt="प्रॉम्प्ट टेम्पलेट चाचणी" width="800"/>

*प्रॉम्प्ट टेम्पलेट्सची चाचणी दर्शवते: टेम्पलेटमध्ये प्लेसहोल्डर्स → मूल्ये लागू केली जातात → फॉरमॅट केलेले आउटपुट सत्यापित केले जाते*

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

ही चाचणी `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java` मध्ये आहे.

**चला ती चालवू:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#प्रॉम्प्ट टेम्पलेट स्वरूपणाची चाचणी
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#प्रॉम्प्ट टेम्पलेट स्वरूपाची चाचणी
```

### नमुना 2: भाषा मॉडेल्सचे मॉकिंग

संभाषण लॉजिक चाचणी करताना, Mockito वापरून फेक मॉडेल तयार करा जे ठरवलेले प्रतिसाद परत करतात. यामुळे चाचण्या वेगवान, मोफत आणि निर्धारपूर्वक असतात.

<img src="../../../translated_images/mr/mock-vs-real.3b8b1f85bfe6845e.png" alt="मॉक विरुद्ध रिअल API तुलना" width="800"/>

*तुलना दर्शवते की चाचणीसाठी मॉक का प्राधान्य देतात: ते वेगवान, मोफत, निर्धारपूर्वक आहेत आणि API कीची आवश्यकता नाही*

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
        assertThat(history).hasSize(6); // 3 वापरकर्ते + 3 एआय संदेश
    }
}
```

हा नमुना `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java` मध्ये दिसतो. मॉक हे सतत वर्तन सुनिश्चित करते जेणेकरून तुम्ही स्मृती व्यवस्थापन योग्यरीत्या कार्य करते हे सत्यापित करू शकता.

### नमुना 3: संभाषण पृथक्करणाची चाचणी

संभाषणाची स्मृती वेगवेगळ्या वापरकर्त्यांसाठी वेगळी ठेवावी लागते. ही चाचणी तपासते की संभाषणांमध्ये संदर्भ मिसळत नाहीत.

<img src="../../../translated_images/mr/conversation-isolation.e00336cf8f7a3e3f.png" alt="संभाषण पृथक्करण" width="800"/>

*संभाषण पृथक्करणाची चाचणी दाखवते की वेगवेगळ्या वापरकर्त्यांसाठी स्वतंत्र स्मृती संच कसा वापरला जातो ज्यामुळे संदर्भ मिसळण्यापासून प्रतिबंध होतो*

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

प्रत्येक संभाषण आपला स्वतंत्र इतिहास राखते. उत्पादन प्रणालींमध्ये, हे बहु-उपयोगकर्ता अनुप्रयोगांसाठी अत्यावश्यक आहे.

### नमुना 4: साधने स्वतंत्रपणे चाचणी करणे

टूल्स हे AI कॉल करू शकतात असे फंक्शन्स असतात. AI च्या निर्णयापासून स्वतंत्रपणे त्यांची सरळ चाचणी करा जेणेकरून त्यांचे व्यवहारिक लॉजिक बरोबर आहे का ते सुनिश्चित करता येईल.

<img src="../../../translated_images/mr/tools-testing.3e1706817b0b3924.png" alt="साधने चाचणी" width="800"/>

*AI कॉल न करता मॉक टूल अंमलबजावणी दाखवून बिझनेस लॉजिकची पडताळणी करण्यासाठी साधने स्वतंत्रपणे चाचणी केली जातात*

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

ही चाचणी `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` मधील टूल लॉजिक AI सहभागी न करता सत्यापित करते. चेनिंगचे उदाहरण दाखवते की एका टूलचे आउटपुट दुसऱ्या टूलच्या इनपुटमध्ये कसे जातात.

### नमुना 5: इन-मेमरी RAG चाचणी

RAG प्रणालींपरंपरेने व्हेक्टर डेटाबेस आणि एम्बेडिंग सेवा आवश्यक असतात. इन-मेमरी पॅटर्न संपूर्ण पाइपलाइन बाह्य अवलंबित्वांशिवाय चाचणी करू देतो.

<img src="../../../translated_images/mr/rag-testing.ee7541b1e23934b1.png" alt="इन-मेमरी RAG चाचणी" width="800"/>

*दस्तऐवज पार्सिंग, एम्बेडिंग स्टोरेज, आणि सिमिलॅरिटी सर्चचे इन-मेमरी RAG वर्कफ्लो ज्यासाठी डेटाबेसची आवश्यकता नाही*

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

ही चाचणी `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` मध्ये मेमरीमध्ये दस्तऐवज तयार करते आणि चंकिंग व मेटाडेटा हँडलिंगची पडताळणी करते.

### नमुना 6: MCP इंटीग्रेशन चाचणी

MCP मॉड्युल Model Context Protocol इंटीग्रेशन stdio ट्रान्सपोर्ट वापरून चाचणी करते. या चाचण्यांद्वारे तुमचे अनुप्रयोग उपप्रक्रिया म्हणून MCP सर्व्हर स्पॉन आणि संवाद करू शकते का हे सत्यापित केले जाते.

`05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` मधील चाचण्या MCP क्लायंट वर्तनाची पडताळणी करतात.

**त्यांना चालवा:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## चाचणी तत्त्वज्ञान

तुमच्या कोडची चाचणी करा, AI ची नाही. तुमच्या चाचण्या तुम्ही लिहिलेल्या कोडला सत्यापित कराव्यात—प्रॉम्प्ट कसे बनत आहेत, स्मृती कशी व्यवस्थापित होते, आणि टूल्स कसे चालतात हे तपासून. AI प्रतिसाद बदलू शकतात आणि ते चाचणी पद्धतींमध्ये नसावेत. स्वत:ला विचारा की तुमचे प्रॉम्प्ट टेम्पलेट योग्यरित्या व्हेरिएबल्स बदलते का, AI योग्य उत्तर देते का नाही हे नाही.

भाषा मॉडेलसाठी मॉक वापरा. ते बाह्य अवलंबित्व असतात जे मंदी, महाग आणि अनिर्णायक असतात. मॉकिंगमुळे चाचण्या मोजक्याच मिलिसेकंदात होतात, मोफत राहतात कारण कोणतेही API खर्च नाही आणि निर्धारपूर्वक राहतात कारण प्रत्येकवेळी एकसारखेच परिणाम मिळतात.

चाचण्या स्वतंत्र ठेवा. प्रत्येक चाचणी आपले स्वतःचे डेटा सेट करावी, इतर चाचण्यांवर अवलंबून राहू नये, आणि स्वतः साफ करावी. चाचण्यांनी कार्यान्वयन क्रमानुसार निष्पन्न होऊनही यशस्वी होणे आवश्यक आहे.

सुखद मार्गाव्यतिरिक्त एज केसांची चाचणी करा. रिकामी इनपुट्स, खूप मोठी इनपुट्स, विशेष अक्षरे, अमान्य पॅरामीटर्स, आणि सीमेवरील स्थिती तपासा. हे अनेकदा अशा बग्स उजागर करतात जे सामान्य वापरात दिसत नाहीत.

वर्णनात्मक नावे वापरा. `shouldMaintainConversationHistoryAcrossMultipleMessages()` आणि `test1()` याची तुलना करा. पहिले नाव तुम्हाला नेमके काय चाचणी केली जात आहे हे सांगते, ज्यामुळे अपयशाचे डीबग करणे सोपे होते.

## पुढील पाउले

आता जेव्हा तुम्हाला चाचणी नमुने समजले आहेत, तर प्रत्येक मॉड्युलमध्ये अधिक खोलात जा:

- **[00 - Quick Start](../00-quick-start/README.md)** - प्रॉम्प्ट टेम्पलेटचे मूलभूत तत्त्वे सुरू करा
- **[01 - Introduction](../01-introduction/README.md)** - संभाषण स्मृती व्यवस्थापन शिका
- **[02 - Prompt Engineering](../02-prompt-engineering/README.md)** - GPT-5 प्रॉम्प्टिंग पॅटर्न्समध्ये प्रावीण्य मिळवा
- **[03 - RAG](../03-rag/README.md)** - रिट्रीव्हल-ऑगमेंटेड जनरेशन प्रणाली तयार करा
- **[04 - Tools](../04-tools/README.md)** - फंक्शन कॉलिंग आणि टूल चेन अंमलात आणा
- **[05 - MCP](../05-mcp/README.md)** - Model Context Protocol समाविष्ट करा

प्रत्येक मॉड्युलचा README येथे चाचणीत तपासल्या जाणाऱ्या संकल्पनांचे सविस्तर स्पष्टीकरण प्रदान करतो.

---

**नॅव्हिगेशन:** [← मुख्य पृष्ठावर परत जा](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
अस्वीकरण:
हा दस्तऐवज AI अनुवाद सेवा Co-op Translator (https://github.com/Azure/co-op-translator) वापरून अनुवादित केला गेला आहे. आम्ही अचूकतेसाठी प्रयत्न करतो, परंतु कृपया लक्षात घ्या की स्वयंचलित अनुवादांमध्ये चुका किंवा अचूकतेच्या त्रुटी असू शकतात. मूळ दस्तऐवज त्याच्या मूळ भाषेत अधिकृत स्रोत म्हणून मानला जावा. महत्त्वाच्या माहितीसाठी व्यावसायिक मानवी अनुवादाची शिफारस केली जाते. या अनुवादाच्या वापरामुळे उद्भवणाऱ्या कोणत्याही गैरसमजुती किंवा चुकीच्या अर्थ लावण्याबद्दल आम्ही जबाबदार नाही.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->