<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-30T23:16:06+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "ne"
}
-->
# LangChain4j अनुप्रयोगहरूको परीक्षण

## सामग्री सूची

- [छिटो सुरु](../../../docs)
- [परीक्षणहरूले के समावेश गर्छन्](../../../docs)
- [परीक्षणहरू चलाउने](../../../docs)
- [VS Code मा परीक्षणहरू चलाउने](../../../docs)
- [परीक्षण ढाँचाहरू](../../../docs)
- [परीक्षण दर्शन](../../../docs)
- [अर्को कदमहरू](../../../docs)

यस मार्गदर्शिकाले API कुञ्जी वा बाह्य सेवाहरूको आवश्यकता बिना AI अनुप्रयोगहरू कसरी परीक्षण गर्ने भन्ने देखाउने परीक्षणहरू कसरी चलाउने भनेर मार्गनिर्देशन गर्दछ।

## छिटो सुरु

एकै आदेशबाट सबै परीक्षणहरू चलाउनुहोस्:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/ne/test-results.ea5c98d8f3642043.png" alt="सफल परीक्षण नतिजाहरू" width="800"/>

*सफल परीक्षण कार्यान्वयन देखाउँदै सबै परीक्षणहरू पास भएका र कुनै असफलता नभएको*

## परीक्षणहरूले के समावेश गर्छन्

यो पाठ्यक्रम स्थानीय रूपमा चल्ने **युनिट परीक्षणहरू**मा केन्द्रित छ। प्रत्येक परीक्षणले अलग्गै एउटा विशिष्ट LangChain4j अवधारणा प्रदर्शन गर्छ।

<img src="../../../translated_images/ne/testing-pyramid.2dd1079a0481e53e.png" alt="परीक्षण पिरामिड" width="800"/>

*परीक्षण पिरामिड जसले युनिट परीक्षणहरू (छिटो, पृथक), एकीकरण परीक्षणहरू (वास्तविक कम्पोनेन्टहरू), र अन्त्यदेखि-अन्त्य परीक्षणहरू बीचको सन्तुलन देखाउँछ। यो तालिमले युनिट परीक्षणलाई समेट्छ।*

| मोड्युल | परीक्षणहरू | फोकस | मुख्य फाइलहरू |
|--------|-------|-------|-----------|
| **00 - छिटो सुरु** | 6 | प्रॉम्प्ट टेम्पलेटहरू र भेरिएबल प्रतिस्थापन | `SimpleQuickStartTest.java` |
| **01 - परिचय** | 8 | संवाद स्मृति र स्थिति-आधारित च्याट | `SimpleConversationTest.java` |
| **02 - प्रॉम्प्ट इन्जिनियरिङ** | 12 | GPT-5 ढाँचाहरू, उत्साह स्तरहरू, संरचित आउटपुट | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | दस्तावेज़ इनगेसन, एमबेडिङहरू, समानता खोज | `DocumentServiceTest.java` |
| **04 - उपकरणहरू** | 12 | फङ्क्सन कलिङ र उपकरण चेनिङ | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | स्टडीयो ट्रान्सपोर्टसहित मोडल कन्टेक्स्ट प्रोटोकल | `SimpleMcpTest.java` |

## परीक्षणहरू चलाउने

**रूटबाट सबै परीक्षणहरू चलाउनुहोस्:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**विशेष मोड्युलका लागि परीक्षणहरू चलाउनुहोस्:**

**Bash:**
```bash
cd 01-introduction && mvn test
# वा रूटबाट
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# वा root बाट
mvn --% test -pl 01-introduction
```

**एकल टेस्ट क्लास चलाउनुहोस्:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**विशेष परीक्षण मेथड चलाउनुहोस्:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#बातचीतको इतिहास कायम राख्नुपर्छ
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#वार्तालाप इतिहास कायम राख्नुपर्छ
```

## VS Code मा परीक्षणहरू चलाउने

यदि तपाईं Visual Studio Code प्रयोग गर्दै हुनुहुन्छ भने, Test Explorer ले परीक्षणहरू चलाउन र डिबग गर्न ग्राफिकल इन्टरफेस प्रदान गर्छ।

<img src="../../../translated_images/ne/vscode-testing.f02dd5917289dced.png" alt="VS Code Test Explorer" width="800"/>

*VS Code Test Explorer ले परीक्षण रूख देखाउँछ जसमा सबै Java टेस्ट क्लासहरू र व्यक्तिगत टेस्ट मेथडहरू समावेश छन्*

**VS Code मा परीक्षणहरू चलाउन:**

1. Activity Bar मा रहेको बेक्कर आइकनमा क्लिक गरेर Test Explorer खोल्नुहोस्
2. सबै मोड्युल र टेस्ट क्लासहरू हेर्न परीक्षण रूखलाई विस्तार गर्नुहोस्।
3. कुनै पनि परीक्षणअगाडि रहेको प्ले बटनमा क्लिक गरेर त्यसलाई व्यक्तिगत रूपमा चलाउनुहोस्।
4. "Run All Tests" मा क्लिक गरेर सम्पूर्ण सूट चलाउनुहोस्
5. कुनै पनि परीक्षणमा राइट-क्लिक गरेर "Debug Test" चयन गर्नुहोस् र ब्रेकपोइन्ट सेट गरी कोड स्टेपमार्फत जाऔं।

Test Explorer ले पास भएका परीक्षणहरूका लागि हरियो चेकमार्क देखाउँछ र परीक्षण असफल हुँदा विस्तृत असफलता सन्देशहरू दिन्छ।

## परीक्षण ढाँचाहरू

### ढाँचा 1: प्रॉम्प्ट टेम्पलेट परीक्षण

सबैभन्दा सरल ढाँचाले कुनै AI मोडल कल नगरी प्रॉम्प्ट टेम्पलेटहरू परीक्षण गर्छ। तपाईंले पुष्टि गर्नुहुन्छ कि भेरिएबल प्रतिस्थापन ठीक छ र प्रॉम्प्टहरू अपेक्षित रूपमा स्वरूपित छन्।

<img src="../../../translated_images/ne/prompt-template-testing.b902758ddccc8dee.png" alt="प्रॉम्प्ट टेम्पलेट परीक्षण" width="800"/>

*प्रॉम्प्ट टेम्पलेट परीक्षण जसले भेरिएबल प्रतिस्थापन प्रवाह देखाउँछ: प्लेसहोल्डर भएका टेम्पलेट → मानहरू लागू → स्वरूपित आउटपुट प्रमाणित*

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

यो परीक्षण `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java` मा रहेको छ।

**यसलाई चलाउनुहोस्:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#परीक्षण प्रॉम्प्ट टेम्पलेट स्वरूपण
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#परीक्षण प्रॉम्प्ट टेम्पलेट स्वरूपण
```

### ढाँचा 2: भाषा मोडेलहरूको मोकिङ

संवाद लॉजिक परीक्षण गर्दा, Mockito प्रयोग गरेर पूर्वनिर्धारित प्रतिक्रियाहरू फर्काउने नक्कली मोडेलहरू बनाउनुहोस्। यसले परीक्षणहरू छिटो, निःशुल्क, र निर्धारक बनाउँछ।

<img src="../../../translated_images/ne/mock-vs-real.3b8b1f85bfe6845e.png" alt="मक बनाम वास्तविक API तुलना" width="800"/>

*कुनकारण मक्स परीक्षणका लागि प्राथमिक हुन्छन् भन्ने तुलना: ती छिटो, निःशुल्क, निर्धारक छन्, र कुनै API कुञ्जीको आवश्यकता पर्दैन*

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
        assertThat(history).hasSize(6); // 3 प्रयोगकर्ता + 3 AI सन्देशहरू
    }
}
```

यो ढाँचा `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java` मा देखिन्छ। मॉकले सुसंगत व्यवहार सुनिश्चित गर्छ ताकि तपाईं स्मृति व्यवस्थापन सही रूपमा काम गर्छ वा भनि पुष्टि गर्न सक्नुहुन्छ।

### ढाँचा 3: संवाद अलगाव परीक्षण

संवाद स्मृतिले धेरै प्रयोगकर्ताहरूलाई अलग्गै राख्नुपर्छ। यस परीक्षणले संवादहरूले सन्दर्भहरू मिस नगर्ने कुरा प्रमाणित गर्छ।

<img src="../../../translated_images/ne/conversation-isolation.e00336cf8f7a3e3f.png" alt="संवाद अलगाव" width="800"/>

*विभिन्न प्रयोगकर्ताहरूका लागि अलग्गा स्मृति स्टोर्स देखाउँदै संवाद अलगाव परीक्षण जसले सन्दर्भ मिश्रण रोक्छ*

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

प्रत्येक संवादले आफ्नो स्वातन्त्र्य इतिहास राख्छ। उत्पादन प्रणालीहरूमा, यो अलगाव बहु-प्रयोगकर्ता अनुप्रयोगहरूको लागि महत्त्वपूर्ण हुन्छ।

### ढाँचा 4: उपकरणहरू स्वतंत्र रूपमा परीक्षण गर्ने

उपकरणहरू ती फङ्क्सनहरू हुन् जुन AI ले कल गर्न सक्छ। AI निर्णयहरूबाट स्वतंत्र रूपमा तिनीहरू ठीकसँग काम गर्छन् कि छैन भनि सुनिश्चित गर्न सिधै परीक्षण गर्नुहोस्।

<img src="../../../translated_images/ne/tools-testing.3e1706817b0b3924.png" alt="उपकरण परीक्षण" width="800"/>

*AI कल बिना नक्कली उपकरण कार्यान्वयन देखाउँदै उपकरणहरू स्वतन्त्र रूपमा परीक्षण जसले बिजनेस लॉजिक प्रमाणित गर्छ*

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

यी परीक्षणहरू `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` बाट आएका हुन् र AI संलग्नता बिना उपकरण लॉजिकलाई मान्य गर्छन्। चेनिङ उदाहरणले देखाउँछ कि कसरी एक उपकरणको आउटपुट अर्कोको इनपुटमा जान्छ।

### ढाँचा 5: इन-मेमोरी RAG परीक्षण

RAG प्रणालीहरूले परम्परागत रूपमा भेक्टर डेटाबेस र एमबेडिङ सेवाहरू चाहिन्छ। इन-मेमोरी ढाँचाले बाह्य निर्भरताहरू बिना सम्पूर्ण पाइपलाइन परीक्षण गर्न दिन्छ।

<img src="../../../translated_images/ne/rag-testing.ee7541b1e23934b1.png" alt="इन-मेमोरी RAG परीक्षण" width="800"/>

*डाटाबेसको आवश्यकता बिना दस्तावेज पार्सिङ, एमबेडिङ भण्डारण, र समानता खोज देखाउँदै इन-मेमोरी RAG परीक्षण कार्यप्रवाह*

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

यो परीक्षण `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` बाट छ जसले एक दस्तावेज इन-मेमोरीमा सिर्जना गरी chunking र मेटाडाटा ह्यान्डलिङ प्रमाणित गर्छ।

### ढाँचा 6: MCP एकीकरण परीक्षण

MCP मोड्युलले stdio ट्रान्सपोर्ट प्रयोग गरेर Model Context Protocol एकीकरण परीक्षण गर्छ। यी परीक्षणहरूले तपाइँको एप्लिकेशनले MCP सर्भरहरू subprocess को रूपमा स्पन गरी सञ्चार गर्न सक्ने कुरा प्रमाणित गर्छ।

यी परीक्षणहरू `05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` मा MCP क्लाइєн्ट व्यवहारलाई मान्य गर्छन्।

**यीहरू चलाउनुहोस्:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## परीक्षण दर्शन

आफ्नो कोड परीक्षण गर्नुहोस्, AI लाई होइन। तपाईंका परीक्षणहरूले तपाईंले लेखेको कोडलाई प्रमाणित गर्नुपर्छ — प्रॉम्प्टहरू कसरी बनाइन्छ, स्मृति कसरी व्यवस्थापन हुन्छ, र उपकरणहरू कसरी कार्यान्वयन हुन्छन् भन्ने जाँच गरेर। AI प्रतिक्रियाहरू भिन्न हुन्छन् र तिनीहरूलाई परीक्षण दावीहरूको भाग बनाउन हुँदैन। आफैंसँग सोध्नुहोस् कि के तपाईंको प्रॉम्प्ट टेम्पलेटले भेरिएबलहरू सही रूपमा प्रतिस्थापन गरिरहेको छ, AI ले सही उत्तर दिन्छ कि दिएन भनी होइन।

भाषा मोडेलहरूको लागि मक्सहरू प्रयोग गर्नुहोस्। ती बाह्य निर्भरता हुन् जो ढिला, महँगो, र अनिर्णायक हुन्छन्। मोकिङले परीक्षणहरूलाई सेकेन्डको सट्टा मिलिसेकेन्डमा छिटो बनाउँछ, कुनै API लागत बिना निःशुल्क बनाउँछ, र हरेक पल्ट एउै नतिजा दिने गरी निर्धारक बनाउँछ।

परीक्षणहरू स्वतन्त्र राख्नुहोस्। प्रत्येक परीक्षणले आफ्नै डेटा सेटअप गर्नुपर्छ, अरू परीक्षणहरूमा निर्भर हुनुहुँदैन, र आफैं पछि सफा गर्नुपर्छ। परीक्षणहरूले निष्पादन क्रमको आधारमा नभई सधैं पास हुनुपर्छ।

सद्गति बाट अघिका किनारा मामिलाहरू पनि परीक्षण गर्नुहोस्। खाली इनपुटहरू, धेरै ठूला इनपुटहरू, विशेष कर्याचिह्नहरू, अवैध प्यारामिटरहरू, र सीमा अवस्थाहरू प्रयास गर्नुहोस्। यी अक्सर सामान्य प्रयोगले खोल्न नसक्ने बगहरू प्रकट गर्छन्।

वर्णनात्मक नामहरू प्रयोग गर्नुहोस्। `shouldMaintainConversationHistoryAcrossMultipleMessages()` लाई `test1()` सँग तुलना गर्नुहोस्। पहिलोले तपाइँलाई ठ्याक्कै के परीक्षण भइरहेको छ भन्ने बताउँछ, जसले फेल भएमा डिबगिङलाई धेरै सजिलो बनाउँछ।

## अर्को कदमहरू

अब जब तपाईंले परीक्षण ढाँचाहरू बुझ्नुभयो, प्रत्येक मोड्युलमा गहिराइमा जानुहोस्:

- **[00 - छिटो सुरु](../00-quick-start/README.md)** - प्रॉम्प्ट टेम्पलेटका आधारभूत कुराबाट सुरु गर्नुहोस्
- **[01 - परिचय](../01-introduction/README.md)** - संवाद स्मृति व्यवस्थापन सिक्नुहोस्
- **[02 - प्रॉम्प्ट इन्जिनियरिङ](../02-prompt-engineering/README.md)** - GPT-5 प्रॉम्प्टिङ ढाँचाहरूमा निपुण हुनुहोस्
- **[03 - RAG](../03-rag/README.md)** - रिट्राइवल-अग्मेन्टेड जेनेरेसन प्रणालीहरू बनाउनुहोस्
- **[04 - उपकरणहरू](../04-tools/README.md)** - फङ्क्सन कलिङ र उपकरण चेनहरू कार्यान्वयन गर्नुहोस्
- **[05 - MCP](../05-mcp/README.md)** - Model Context Protocol एकीकृत गर्नुहोस्

प्रत्येक मोड्युलको README मा यहाँ परीक्षण गरिएका अवधारणाहरूका विस्तृत व्याख्याहरू छन्।

---

**नेभिगेसन:** [← मुख्यमा फर्कनुहोस्](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
अस्वीकरण:
यस दस्तावेजलाई AI अनुवाद सेवा Co-op Translator (https://github.com/Azure/co-op-translator) प्रयोग गरेर अनुवाद गरिएको हो। यद्यपि हामी सटीकताको लागि प्रयास गर्छौं, कृपया ध्यान दिनुहोस् कि स्वचालित अनुवादमा त्रुटि वा अशुद्धता हुन सक्छ। मूल भाषामा रहेको दस्तावेजलाई अधिकृत स्रोत मान्नुपर्छ। महत्त्वपूर्ण जानकारीका लागि व्यावसायिक मानव अनुवाद सिफारिस गरिन्छ। यस अनुवादको प्रयोगबाट उत्पन्न कुनै पनि गलतफहमी वा गलत व्याख्याका लागि हामी उत्तरदायी छैनौं।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->