<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-31T00:40:58+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "tr"
}
-->
# LangChain4j Uygulamaları Test Etme

## İçindekiler

- [Hızlı Başlangıç](../../../docs)
- [Testlerin Kapsadığı Konular](../../../docs)
- [Testleri Çalıştırma](../../../docs)
- [VS Code'da Testleri Çalıştırma](../../../docs)
- [Test Desenleri](../../../docs)
- [Test Etme Felsefesi](../../../docs)
- [Sonraki Adımlar](../../../docs)

Bu rehber, API anahtarlarına veya harici servislere ihtiyaç duymadan yapay zeka uygulamalarını nasıl test edeceğinizi gösteren testlerin üzerinden adım adım geçer.

## Hızlı Başlangıç

Tüm testleri tek bir komutla çalıştırın:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/tr/test-results.ea5c98d8f3642043.png" alt="Başarılı Test Sonuçları" width="800"/>

*Tüm testlerin hatasız geçtiğini gösteren başarılı test çalıştırması*

## Testlerin Kapsadığı Konular

Bu eğitim, yerel olarak çalışan **birim testlerine** odaklanır. Her test, izole bir şekilde belirli bir LangChain4j kavramını gösterir.

<img src="../../../translated_images/tr/testing-pyramid.2dd1079a0481e53e.png" alt="Test Piramidi" width="800"/>

*Birim testleri (hızlı, izole), entegrasyon testleri (gerçek bileşenler) ve uçtan uca testler arasındaki dengeyi gösteren test piramidi. Bu eğitim birim testlerini kapsar.*

| Modül | Testler | Odak | Ana Dosyalar |
|--------|-------|-------|-----------|
| **00 - Hızlı Başlangıç** | 6 | İstem şablonları ve değişken yerine koyma | `SimpleQuickStartTest.java` |
| **01 - Giriş** | 8 | Konuşma belleği ve durumlu sohbet | `SimpleConversationTest.java` |
| **02 - İstem Mühendisliği** | 12 | GPT-5 kalıpları, istek seviyeleri, yapılandırılmış çıktı | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | Doküman alımı, embedding'ler, benzerlik araması | `DocumentServiceTest.java` |
| **04 - Araçlar** | 12 | Fonksiyon çağırma ve araç zincirleme | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | Stdio taşımacılığı ile Model Context Protocol | `SimpleMcpTest.java` |

## Testleri Çalıştırma

**Kök dizinden tüm testleri çalıştırın:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**Belirli bir modül için testleri çalıştırın:**

**Bash:**
```bash
cd 01-introduction && mvn test
# Veya root'tan
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# Veya root'tan
mvn --% test -pl 01-introduction
```

**Tek bir test sınıfını çalıştırın:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**Belirli bir test metodunu çalıştırın:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#sohbet geçmişi korunmalı mı
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#sohbet geçmişini korumalı
```

## VS Code'da Testleri Çalıştırma

Visual Studio Code kullanıyorsanız, Test Explorer testleri çalıştırmak ve hata ayıklamak için grafiksel bir arayüz sağlar.

<img src="../../../translated_images/tr/vscode-testing.f02dd5917289dced.png" alt="VS Code Test Gezgini" width="800"/>

*VS Code Test Explorer'ın, tüm Java test sınıfları ve bireysel test metodlarıyla test ağacını gösterdiği ekran görüntüsü*

**VS Code'da testleri çalıştırmak için:**

1. Aktivite Çubuğundaki beheron simgesine tıklayarak Test Explorer'ı açın
2. Tüm modülleri ve test sınıflarını görmek için test ağacını genişletin
3. Bireysel olarak çalıştırmak için herhangi bir testin yanındaki oynat düğmesine tıklayın
4. Tüm testleri çalıştırmak için "Run All Tests" düğmesine tıklayın
5. Herhangi bir teste sağ tıklayıp "Debug Test" seçeneğini seçerek breakpoint koyun ve kodu adım adım inceleyin

Test Explorer, geçen testler için yeşil onay işaretleri gösterir ve testler başarısız olduğunda ayrıntılı hata mesajları sağlar.

## Test Desenleri

### Desen 1: İstem Şablonlarını Test Etme

En basit desen, herhangi bir AI modeli çağırmadan istem şablonlarını test eder. Değişken yerine koymanın doğru çalıştığını ve istemlerin beklenen şekilde biçimlendiğini doğrularsınız.

<img src="../../../translated_images/tr/prompt-template-testing.b902758ddccc8dee.png" alt="İstem Şablonu Testi" width="800"/>

*Yer tutuculu şablon → değerlerin uygulanması → biçimlendirilmiş çıktının doğrulanması akışını gösteren istem şablonlarını test etme*

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

Bu test şu dosyada bulunur: `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`.

**Çalıştırın:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#test istem şablonu biçimlendirmesi
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#testİstemŞablonuBiçimlendirmesi
```

### Desen 2: Dil Modellerini Taklit Etme (Mocking)

Konuşma mantığını test ederken, önceden belirlenmiş yanıtlar döndüren Mockito gibi kütüphanelerle sahte modeller oluşturun. Bu, testleri hızlı, ücretsiz ve deterministik yapar.

<img src="../../../translated_images/tr/mock-vs-real.3b8b1f85bfe6845e.png" alt="Sahte ve Gerçek API Karşılaştırması" width="800"/>

*Test etmek için neden taklitlerin tercih edildiğini gösteren karşılaştırma: hızlıdırlar, ücretsizdirler, deterministiktirler ve API anahtarı gerektirmezler*

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
        assertThat(history).hasSize(6); // 3 kullanıcı + 3 yapay zeka mesajı
    }
}
```

Bu desen, `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java` içinde yer alır. Mock, bellek yönetiminin doğru çalıştığını doğrulayabilmeniz için tutarlı davranış sağlar.

### Desen 3: Konuşma İzolasyonunu Test Etme

Konuşma belleği birden fazla kullanıcıyı ayrı tutmalıdır. Bu test, konuşmaların bağlamları karıştırmadığını doğrular.

<img src="../../../translated_images/tr/conversation-isolation.e00336cf8f7a3e3f.png" alt="Sohbet İzolasyonu" width="800"/>

*Farklı kullanıcılar için bağlam karışmasını önlemek üzere ayrı bellek depoları gösteren konuşma izolasyonunu test etme*

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

Her konuşma kendi bağımsız geçmişini korur. Üretim sistemlerinde, bu izolasyon çok kullanıcılı uygulamalar için kritiktir.

### Desen 4: Araçları Bağımsız Olarak Test Etme

Araçlar, AI'nin çağırabileceği fonksiyonlardır. AI kararlarından bağımsız olarak doğru çalıştıklarından emin olmak için doğrudan test edin.

<img src="../../../translated_images/tr/tools-testing.3e1706817b0b3924.png" alt="Araç Testi" width="800"/>

*AI çağrısı olmadan sahte araç yürütmesini gösteren ve iş mantığını doğrulayan araçları bağımsız test etme*

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

Bu testler, `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` dosyasından alınmıştır ve AI katılımı olmadan araç mantığını doğrular. Zincirleme örneği, bir aracın çıktısının diğerinin girdisine nasıl beslendiğini gösterir.

### Desen 5: Bellek İçi RAG Testi

RAG sistemleri geleneksel olarak vektör veritabanları ve embedding servisleri gerektirir. Bellek içi desen, tüm boru hattını harici bağımlılıklar olmadan test etmenizi sağlar.

<img src="../../../translated_images/tr/rag-testing.ee7541b1e23934b1.png" alt="Bellek İçi RAG Testi" width="800"/>

*Bir veritabanı gerektirmeden doküman ayrıştırma, embedding depolama ve benzerlik araması gösteren bellek içi RAG test iş akışı*

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

Bu test, `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` dosyasından alınmıştır; bellekte bir doküman oluşturur ve parçalama ile metadata işleyişini doğrular.

### Desen 6: MCP Entegrasyon Testi

MCP modülü, stdio taşımasını kullanarak Model Context Protocol entegrasyonunu test eder. Bu testler, uygulamanızın MCP sunucularını alt süreç olarak başlatıp bunlarla iletişim kurabildiğini doğrular.

`05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` içindeki testler MCP istemci davranışını doğrular.

**Çalıştırın:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## Test Etme Felsefesi

Kodunuzu test edin, yapay zekayı değil. Testleriniz, istemlerin nasıl oluşturulduğunu, belleğin nasıl yönetildiğini ve araçların nasıl çalıştırıldığını kontrol ederek yazdığınız kodu doğrulamalıdır. AI yanıtları değişkendir ve test doğrulamalarının bir parçası olmamalıdır. Kendinize sorun: istem şablonunuz değişkenleri doğru şekilde yerine koyuyor mu, AI doğru cevabı veriyor mu değil.

Dil modelleri için mock kullanın. Bunlar dış bağımlılıklardır; yavaş, pahalı ve deterministik olmayan yapıdadır. Mock kullanmak testleri saniyeler yerine milisaniyelere indirir, maliyeti ortadan kaldırır ve her seferinde aynı sonucu verir.

Testleri bağımsız tutun. Her test kendi verisini kurmalı, diğer testlere güvenmemeli ve kendi temizliğini yapmalıdır. Testler yürütme sırasından bağımsız olarak geçmelidir.

İyi yol senaryosunun ötesindeki köşe durumlarını test edin. Boş girdileri, çok büyük girdileri, özel karakterleri, geçersiz parametreleri ve sınır koşullarını deneyin. Bunlar genellikle normal kullanımın ortaya çıkarmadığı hataları açığa çıkarır.

Açıklayıcı isimler kullanın. `shouldMaintainConversationHistoryAcrossMultipleMessages()` ile `test1()`'i karşılaştırın. Birincisi tam olarak ne test edildiğini söyler ve hata ayıklamayı çok daha kolay hale getirir.

## Sonraki Adımlar

Artık test desenlerini anladığınıza göre, her modüle daha derinlemesine dalın:

- **[00 - Hızlı Başlangıç](../00-quick-start/README.md)** - İstem şablonu temelleri ile başlayın
- **[01 - Giriş](../01-introduction/README.md)** - Konuşma bellek yönetimini öğrenin
- **[02 - İstem Mühendisliği](../02-prompt-engineering/README.md)** - GPT-5 istem kalıplarında ustalaşın
- **[03 - RAG](../03-rag/README.md)** - retrieval-augmented generation sistemleri kurun
- **[04 - Araçlar](../04-tools/README.md)** - Fonksiyon çağırma ve araç zincirlerini uygulayın
- **[05 - MCP](../05-mcp/README.md)** - Model Context Protocol ile entegrasyon yapın

Her modülün README dosyası burada test edilen kavramların ayrıntılı açıklamalarını sağlar.

---

**Gezinme:** [← Ana Sayfaya Geri](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Sorumluluk Reddi:
Bu belge, AI çeviri servisi [Co-op Translator](https://github.com/Azure/co-op-translator) kullanılarak çevrilmiştir. Doğruluk için çaba göstersek de, otomatik çevirilerin hata veya yanlışlıklar içerebileceğini lütfen unutmayın. Orijinal belge, kendi dilindeki metin olarak yetkili kaynak kabul edilmelidir. Kritik bilgiler için profesyonel bir insan çevirisi önerilir. Bu çevirinin kullanımı sonucunda ortaya çıkabilecek herhangi bir yanlış anlama veya yanlış yorumlamadan sorumlu değiliz.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->