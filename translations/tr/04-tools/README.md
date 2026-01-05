<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-31T00:36:19+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "tr"
}
-->
# Module 04: AI Agents with Tools

## Table of Contents

- [Ne Öğreneceksiniz](../../../04-tools)
- [Önkoşullar](../../../04-tools)
- [Araçlara Sahip AI Ajanlarını Anlamak](../../../04-tools)
- [Araç Çağrısı Nasıl Çalışır](../../../04-tools)
  - [Araç Tanımları](../../../04-tools)
  - [Karar Verme](../../../04-tools)
  - [Yürütme](../../../04-tools)
  - [Cevap Oluşturma](../../../04-tools)
- [Araç Zincirleme](../../../04-tools)
- [Uygulamayı Çalıştırma](../../../04-tools)
- [Uygulamayı Kullanma](../../../04-tools)
  - [Basit Araç Kullanımını Deneyin](../../../04-tools)
  - [Araç Zincirlemeyi Test Edin](../../../04-tools)
  - [Konuşma Akışını Görün](../../../04-tools)
  - [Muhakemeyi Gözlemleyin](../../../04-tools)
  - [Farklı İsteklerle Deneyler Yapın](../../../04-tools)
- [Temel Kavramlar](../../../04-tools)
  - [ReAct Deseni (Muhakeme ve Eylem)](../../../04-tools)
  - [Araç Açıklamaları Önemlidir](../../../04-tools)
  - [Oturum Yönetimi](../../../04-tools)
  - [Hata Yönetimi](../../../04-tools)
- [Mevcut Araçlar](../../../04-tools)
- [Araç Tabanlı Ajanları Ne Zaman Kullanmalısınız](../../../04-tools)
- [Sonraki Adımlar](../../../04-tools)

## What You'll Learn

Şu ana kadar AI ile nasıl konuşma yapılacağını, istemleri (prompts) nasıl etkili yapılandıracağınızı ve cevapları belgelerinize nasıl dayandıracağınızı öğrendiniz. Ancak hâlâ temel bir sınırlama var: dil modelleri yalnızca metin üretebilir. Hava durumunu kontrol edemezler, hesaplama yapamazlar, veritabanlarını sorgulayamazlar veya dış sistemlerle etkileşime giremezler.

Araçlar bunu değiştirir. Modele çağırabileceği fonksiyonlara erişim sağlayarak, onu bir metin üreticisinden eylem alabilen bir ajana dönüştürürsünüz. Model ne zaman bir araca ihtiyaç duyduğuna, hangi aracı kullanacağına ve hangi parametreleri ileteceğine karar verir. Kodunuz fonksiyonu yürütür ve sonucu döndürür. Model bu sonucu yanıtına dahil eder.

## Prerequisites

- Module 01 tamamlandı (Azure OpenAI kaynakları dağıtıldı)
- Kök dizinde Azure kimlik bilgilerini içeren `.env` dosyası (Module 01'de `azd up` tarafından oluşturuldu)

> **Not:** Module 01'i tamamlamadıysanız, önce oradaki dağıtım talimatlarını izleyin.

## Understanding AI Agents with Tools

> **📝 Not:** Bu modülde geçen "ajanlar" terimi, araç çağırma yetenekleriyle geliştirilmiş AI asistanlarını ifade eder. Bu, bizim [Module 05: MCP](../05-mcp/README.md) içinde ele alacağımız **Agentic AI** desenleri (planlama, hafıza ve çok adımlı muhakeme yapan otonom ajanlar) ile farklıdır.

Araçlara sahip bir AI ajanı muhakeme ve eylem desenini (ReAct) takip eder:

1. Kullanıcı bir soru sorar
2. Ajan ne bilmesi gerektiği hakkında muhakeme yapar
3. Ajan cevaplamak için bir araca ihtiyacı olup olmadığına karar verir
4. Eğer evet ise, ajan doğru parametrelerle uygun aracı çağırır
5. Araç yürütülür ve veri döner
6. Ajan sonucu dahil eder ve nihai cevabı verir

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.tr.png" alt="ReAct Deseni" width="800"/>

*ReAct deseni - AI ajanlarının problemleri çözmek için nasıl muhakeme ile eylem arasında geçiş yaptıkları*

Bu otomatik olarak gerçekleşir. Araçları ve açıklamalarını siz tanımlarsınız. Model, ne zaman ve nasıl kullanılacaklarına dair karar verme sürecini yönetir.

## How Tool Calling Works

**Tool Definitions** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Açık açıklamalar ve parametre spesifikasyonları ile fonksiyonlar tanımlarsınız. Model bu açıklamaları sistem isteminde görür ve her bir aracın ne yaptığını anlar.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Hava durumu sorgulama mantığınız
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Asistan Spring Boot tarafından otomatik olarak şunlarla yapılandırılır:
// - ChatModel bean
// - @Component sınıflarındaki tüm @Tool yöntemleri
// - Oturum yönetimi için ChatMemoryProvider
```

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) and ask:
> - "How would I integrate a real weather API like OpenWeatherMap instead of mock data?"
> - "What makes a good tool description that helps the AI use it correctly?"
> - "How do I handle API errors and rate limits in tool implementations?"

**Karar Verme**

Kullanıcı "Seattle'da hava nasıl?" diye sorduğunda, model hava aracına ihtiyaç duyduğunu fark eder. Lokasyon parametresi "Seattle" olarak ayarlanmış bir fonksiyon çağrısı üretir.

**Yürütme** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot, tüm kayıtlı araçlarla deklaratif `@AiService` arayüzünü otomatik olarak bağlar ve LangChain4j araç çağrılarını otomatik olarak yürütür.

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) and ask:
> - "How does the ReAct pattern work and why is it effective for AI agents?"
> - "How does the agent decide which tool to use and in what order?"
> - "What happens if a tool execution fails - how should I handle errors robustly?"

**Cevap Oluşturma**

Model hava verisini alır ve bunu kullanıcı için doğal dil yanıtına dönüştürür.

### Neden Deklaratif AI Servisleri Kullanılır?

Bu modül, LangChain4j'in Spring Boot entegrasyonunu deklaratif `@AiService` arayüzleri ile kullanır:

- **Spring Boot otomatik bağlama** - ChatModel ve araçlar otomatik olarak enjekte edilir
- **@MemoryId deseni** - Oturum bazlı hafıza yönetimi otomatik
- **Tek örnek** - Asistan bir kez oluşturulur ve daha iyi performans için yeniden kullanılır
- **Tip güvenli yürütme** - Java metodları doğrudan çağrılır ve tür dönüşümü yapılır
- **Çok turlu orkestrasyon** - Araç zincirlemeyi otomatik olarak yönetir
- **Sıfır gereksiz kod** - Manuel AiServices.builder() çağrılarına veya hafıza HashMap'lerine gerek yok

Alternatif yaklaşımlar (manuel `AiServices.builder()`) daha fazla kod gerektirir ve Spring Boot entegrasyon faydalarını kaçırır.

## Tool Chaining

**Araç Zincirleme** - AI birden fazla aracı sırayla çağırabilir. "Seattle'da hava nasıl ve şemsiye getirmeli miyim?" diye sorun ve modelin `getCurrentWeather` çağrısını yağmur ekipmanı hakkında muhakeme ile nasıl zincirlediğini izleyin.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.tr.png" alt="Araç Zincirleme" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Ardışık araç çağrıları - bir aracın çıktısı sonraki karara bilgi sağlar*

**Zarif Hatalar** - Mock veride olmayan bir şehir için hava isteyin. Araç bir hata mesajı döndürür ve AI yardım edemediğini açıklar. Araçlar güvenli şekilde başarısız olur.

Bu tek bir konuşma turunda gerçekleşir. Ajan çoklu araç çağrılarını otonom olarak düzenler.

## Run the Application

**Dağıtımı doğrulayın:**

Kök dizinde Azure kimlik bilgilerini içeren `.env` dosyasının bulunduğundan emin olun (Module 01 sırasında oluşturuldu):
```bash
cat ../.env  # AZURE_OPENAI_ENDPOINT, API_KEY ve DEPLOYMENT gösterilmeli
```

**Uygulamayı başlatın:**

> **Not:** Module 01'den `./start-all.sh` ile zaten tüm uygulamaları başlattıysanız, bu modül zaten 8084 numaralı portta çalışıyor. Aşağıdaki başlatma komutlarını atlayıp doğrudan http://localhost:8084 adresine gidebilirsiniz.

**Seçenek 1: Spring Boot Dashboard kullanma (VS Code kullanıcıları için önerilir)**

Geliştirme konteyneri, tüm Spring Boot uygulamalarını yönetmek için görsel bir arayüz sağlayan Spring Boot Dashboard uzantısını içerir. Bunu VS Code'un Sol Aktivite Çubuğunda bulabilirsiniz (Spring Boot simgesine bakın).

Spring Boot Dashboard'dan:
- Workspace içindeki tüm kullanılabilir Spring Boot uygulamalarını görebilirsiniz
- Uygulamaları tek tıklamayla başlat/durdurabilirsiniz
- Uygulama günlüklerini gerçek zamanlı görüntüleyebilirsiniz
- Uygulama durumunu izleyebilirsiniz

Bu modülü başlatmak için "tools"un yanındaki oynat düğmesine tıklayın veya tüm modülleri aynı anda başlatın.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.tr.png" alt="Spring Boot Dashboard" width="400"/>

**Seçenek 2: Shell betikleri kullanma**

Tüm web uygulamalarını başlatın (modüller 01-04):

**Bash:**
```bash
cd ..  # Kök dizininden
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Kök dizininden
.\start-all.ps1
```

Veya sadece bu modülü başlatın:

**Bash:**
```bash
cd 04-tools
./start.sh
```

**PowerShell:**
```powershell
cd 04-tools
.\start.ps1
```

Her iki betik de kök `.env` dosyasından ortam değişkenlerini otomatik yükler ve JAR'lar yoksa bunları oluşturur.

> **Not:** Başlatmadan önce tüm modülleri manuel olarak derlemeyi tercih ediyorsanız:
>
> **Bash:**
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
>
> **PowerShell:**
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

Tarayıcınızda http://localhost:8084 adresini açın.

**Durdurmak için:**

**Bash:**
```bash
./stop.sh  # Sadece bu modül
# Veya
cd .. && ./stop-all.sh  # Tüm modüller
```

**PowerShell:**
```powershell
.\stop.ps1  # Sadece bu modül
# Veya
cd ..; .\stop-all.ps1  # Tüm modüller
```

## Using the Application

Uygulama, hava ve sıcaklık dönüşümü araçlarına erişimi olan bir AI ajanı ile etkileşime girmenizi sağlayan bir web arayüzü sunar.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.tr.png" alt="AI Ajanı Araç Arayüzü" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI Ajanı Araç arayüzü - hızlı örnekler ve araçlarla etkileşim için sohbet arayüzü*

**Basit Araç Kullanımını Deneyin**

Basit bir istekle başlayın: "100 derece Fahrenheit'i Celsius'a çevir". Ajan hangi araca ihtiyaç duyduğunu algılar, doğru parametrelerle aracı çağırır ve sonucu döndürür. Bunun ne kadar doğal hissettirdiğine dikkat edin - hangi aracı kullanacağınızı veya nasıl çağıracağınızı belirtmediniz.

**Araç Zincirlemeyi Test Edin**

Şimdi daha karmaşık bir şey deneyin: "Seattle'da hava nasıl ve bunu Fahrenheit'e çevir?" Ajanın bunu adım adım nasıl çözdüğünü izleyin. Önce hava durumunu alır (Celsius döner), sonra Fahrenheit'e çevirmesi gerektiğini fark eder, dönüşüm aracını çağırır ve her iki sonucu birleştirerek yanıt verir.

**Konuşma Akışını Görün**

Sohbet arayüzü konuşma geçmişini korur, çok turlu etkileşimler yapmanızı sağlar. Tüm önceki sorgu ve yanıtları görebilir, konuşmayı takip edip ajanın birden fazla değiş tokuşta nasıl bağlam oluşturduğunu anlayabilirsiniz.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.tr.png" alt="Birden Çok Araç Çağrılı Konuşma" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Basit dönüşümler, hava sorguları ve araç zincirlemeyi gösteren çok turlu konuşma*

**Farklı İsteklerle Deneyler Yapın**

Çeşitli kombinasyonları deneyin:
- Hava sorguları: "Tokyo'da hava nasıl?"
- Sıcaklık dönüşümleri: "25°C kaç Kelvin?"
- Birleşik sorgular: "Paris'te havayı kontrol et ve 20°C üzerinde olup olmadığını söyle"

Ajanın doğal dili nasıl yorumladığına ve uygun araç çağrılarına nasıl eşlediğine dikkat edin.

## Key Concepts

**ReAct Deseni (Muhakeme ve Eylem)**

Ajan, muhakeme (ne yapılacağına karar verme) ile eylem (araçları kullanma) arasında geçiş yapar. Bu desen, sadece talimatlara yanıt vermek yerine otonom problem çözmeyi mümkün kılar.

**Araç Açıklamaları Önemlidir**

Araç açıklamalarınızın kalitesi, ajanın araçları ne kadar iyi kullandığını doğrudan etkiler. Açık ve spesifik açıklamalar modelin her aracı ne zaman ve nasıl çağıracağını anlamasına yardımcı olur.

**Oturum Yönetimi**

`@MemoryId` anotasyonu otomatik oturum bazlı hafıza yönetimini etkinleştirir. Her oturum kimliği, `ChatMemoryProvider` bean tarafından yönetilen kendi `ChatMemory` örneğini alır; bu da manuel hafıza takibine gerek bırakmaz.

**Hata Yönetimi**

Araçlar başarısız olabilir - API'ler zaman aşımına uğrayabilir, parametreler geçersiz olabilir, dış hizmetler devre dışı kalabilir. Üretim ajanlarının modelin sorunları açıklayabilmesi veya alternatifler denemesi için hata yönetimine ihtiyacı vardır.

## Available Tools

**Hava Araçları** (gösterim için mock veri):
- Bir konum için güncel hava durumunu alma
- Çok günlük tahmin alma

**Sıcaklık Dönüşüm Araçları**:
- Celsius'tan Fahrenheit'e
- Fahrenheit'tan Celsius'a
- Celsius'tan Kelvin'e
- Kelvin'den Celsius'a
- Fahrenheit'tan Kelvin'e
- Kelvin'den Fahrenheit'e

Bunlar basit örneklerdir, ancak desen herhangi bir fonksiyona genişletilebilir: veritabanı sorguları, API çağrıları, hesaplamalar, dosya işlemleri veya sistem komutları gibi.

## When to Use Tool-Based Agents

**Araçları kullanın when:**
- Cevap gerçek zamanlı veri gerektiriyorsa (hava, hisse senedi fiyatları, envanter)
- Basit matematiğin ötesinde hesaplamalar yapmanız gerekiyorsa
- Veritabanlarına veya API'lara erişim gerekiyorsa
- Eylem almanız gerekiyorsa (e-postalar gönderme, bilet oluşturma, kayıt güncelleme)
- Birden çok veri kaynağını birleştirmeniz gerekiyorsa

**Araç kullanmayın when:**
- Sorular genel bilgiyle cevaplanabiliyorsa
- Yanıt tamamen sohbet amaçlıysa
- Araç gecikmesi deneyimi çok yavaşlatacaksa

## Next Steps

**Sonraki Modül:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Gezinme:** [← Önceki: Module 03 - RAG](../03-rag/README.md) | [Ana Sayfaya Dön](../README.md) | [Sonraki: Module 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Feragatname**:
Bu belge, AI çeviri hizmeti [Co-op Translator](https://github.com/Azure/co-op-translator) kullanılarak çevrilmiştir. Doğruluk için çaba gösteriyor olsak da, otomatik çevirilerin hatalar veya yanlışlıklar içerebileceğini lütfen unutmayın. Orijinal belge, kendi dilinde yetkili kaynak olarak kabul edilmelidir. Kritik bilgiler için profesyonel insan çevirisi önerilir. Bu çevirinin kullanımından kaynaklanan herhangi bir yanlış anlama veya yanlış yorumlamadan sorumlu değiliz.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->