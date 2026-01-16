<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-05T23:18:11+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "tr"
}
-->
# Modül 03: RAG (Retrieval-Augmented Generation)

## İçindekiler

- [Neler Öğreneceksiniz](../../../03-rag)
- [Ön Koşullar](../../../03-rag)
- [RAG'i Anlamak](../../../03-rag)
- [Nasıl Çalışır](../../../03-rag)
  - [Belge İşleme](../../../03-rag)
  - [Embedding Oluşturma](../../../03-rag)
  - [Anlamsal Arama](../../../03-rag)
  - [Cevap Üretimi](../../../03-rag)
- [Uygulamayı Çalıştırma](../../../03-rag)
- [Uygulamayı Kullanma](../../../03-rag)
  - [Belge Yükleme](../../../03-rag)
  - [Soru Sorma](../../../03-rag)
  - [Kaynak Referanslarını Kontrol Etme](../../../03-rag)
  - [Sorularla Deney Yapma](../../../03-rag)
- [Temel Kavramlar](../../../03-rag)
  - [Parçalama Stratejisi](../../../03-rag)
  - [Benzerlik Puanları](../../../03-rag)
  - [Bellek İçi Depolama](../../../03-rag)
  - [Bağlam Penceresi Yönetimi](../../../03-rag)
- [RAG'in Önemi Ne Zaman Artar](../../../03-rag)
- [Sonraki Adımlar](../../../03-rag)

## Neler Öğreneceksiniz

Önceki modüllerde, AI ile nasıl sohbet edileceğini ve istemlerinizi etkili şekilde nasıl yapılandıracağınızı öğrendiniz. Ancak temel bir kısıtlama vardır: dil modelleri yalnızca eğitim sırasında öğrenilenlerle sınırlıdır. Şirket politikalarınız, proje dokümantasyonunuz veya eğitilmediği herhangi bir bilgi hakkında soruları yanıtlayamazlar.

RAG (Retrieval-Augmented Generation) bu sorunu çözer. Modeli bilgilerinize öğretmeye çalışmak (ki bu maliyetli ve pratik olmayan bir yöntemdir) yerine, modele belgeleriniz arasında arama yapabilme yeteneği verirsiniz. Bir soru sorulduğunda, sistem ilgili bilgileri bulur ve isteme ekler. Model ardından o alınan bağlam temelinde yanıt verir.

RAG'yi modele bir referans kütüphanesi sağlamak olarak düşünün. Bir soru sorduğunuzda, sistem:

1. **Kullanıcı Sorgusu** - Bir soru sorarsınız
2. **Embedding** - Sorunuzu vektöre dönüştürür
3. **Vektör Araması** - Benzer belge parçalarını bulur
4. **Bağlam Oluşturma** - İlgili parçaları isteme ekler
5. **Yanıt** - LLM bağlam temelinde cevap üretir

Bu, modelin yanıtlarını eğitim bilgisinden veya uydurmalardan ziyade gerçek verilerinize dayandırır.

<img src="../../../translated_images/tr/rag-architecture.ccb53b71a6ce407f.png" alt="RAG Architecture" width="800"/>

*RAG iş akışı - kullanıcı sorgusundan anlamsal arama ve bağlamsal yanıt üretimine*

## Ön Koşullar

- Modül 01 tamamlandı (Azure OpenAI kaynakları dağıtıldı)
- Kök dizinde Azure kimlik bilgilerini içeren `.env` dosyası mevcut (Modül 01’de `azd up` komutuyla oluşturuldu)

> **Not:** Modül 01’i tamamlamadıysanız, önce oradaki dağıtım talimatlarını izleyin.

## Nasıl Çalışır

### Belge İşleme

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

Bir belge yüklediğinizde, sistem onu dil modelinin bağlam penceresine rahatlıkla sığacak daha küçük parçalara böler. Bu parçalar, sınırlarında bağlam kaybını önlemek için biraz üst üste biner.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```

> **🤖 [GitHub Copilot](https://github.com/features/copilot) Chat ile deneyin:** [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) dosyasını açın ve sorun:
> - "LangChain4j dokümanları parçalara nasıl böler ve üst üste binme neden önemlidir?"
> - "Farklı belge türleri için ideal parça boyutu nedir ve neden?"
> - "Birden fazla dilde olan veya özel biçimlendirmeye sahip belgelerle nasıl başa çıkarım?"

### Embedding Oluşturma

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Her parça, embedding denilen sayısal bir temsile dönüştürülür — metnin anlamını yakalayan matematiksel bir parmak izi gibi. Benzer metinler benzer embedding’ler üretir.

```java
@Bean
public EmbeddingModel embeddingModel() {
    return OpenAiOfficialEmbeddingModel.builder()
        .baseUrl(azureOpenAiEndpoint)
        .apiKey(azureOpenAiKey)
        .modelName(azureEmbeddingDeploymentName)
        .build();
}

EmbeddingStore<TextSegment> embeddingStore = 
    new InMemoryEmbeddingStore<>();
```

<img src="../../../translated_images/tr/vector-embeddings.2ef7bdddac79a327.png" alt="Vector Embeddings Space" width="800"/>

*Embedding uzayında vektörlerle temsil edilen belgeler - benzer içerikler kümelenir*

### Anlamsal Arama

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Bir soru sorduğunuzda, soru da embedding olarak dönüştürülür. Sistem, sorunuzun embedding’ini tüm belge parçalarının embedding’leriyle karşılaştırır. En benzer anlamlara sahip parçaları bulur — sadece anahtar kelime eşleşmesi değil, gerçek anlamsal benzerlik.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```

> **🤖 [GitHub Copilot](https://github.com/features/copilot) Chat ile deneyin:** [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) dosyasını açın ve sorun:
> - "Benzerlik araması embedding’lerle nasıl çalışır ve skoru ne belirler?"
> - "Hangi benzerlik eşik değerini kullanmalıyım ve sonuçları nasıl etkiler?"
> - "İlgili belge bulunamadığında nasıl davranılır?"

### Cevap Üretimi

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

En uygun parçalar modelin istemine dahil edilir. Model bu spesifik parçaları okur ve sorunuza bu bilgi temelinde yanıt verir. Bu, halüsinasyonu (uydurmayı) önler — model sadece önünde olan bilgiden cevap verir.

## Uygulamayı Çalıştırma

**Dağıtımı doğrulayın:**

Azure kimlik bilgilerini içeren `.env` dosyasının kök dizinde olduğundan emin olun (Modül 01’de oluşturuldu):
```bash
cat ../.env  # AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT göstermeli
```

**Uygulamayı başlatın:**

> **Not:** Modül 01’de `./start-all.sh` komutuyla tüm uygulamaları zaten başlattıysanız, bu modül port 8081 üzerinde zaten çalışmaktadır. Aşağıdaki başlatma komutlarını atlayabilir ve doğrudan http://localhost:8081 adresine gidebilirsiniz.

**Seçenek 1: Spring Boot Dashboard kullanma (VS Code kullanıcıları için önerilir)**

Geliştirme konteyneri, tüm Spring Boot uygulamalarını görsel olarak yönetmenizi sağlayan Spring Boot Dashboard uzantısını içerir. VS Code’un sol tarafındaki Aktivite Çubuğunda (Spring Boot simgesine bakın) bulunabilir.

Spring Boot Dashboard’dan:
- Çalışma alanındaki tüm kullanılabilir Spring Boot uygulamalarını görebilirsiniz
- Tek tıklamayla uygulamaları başlatıp durdurabilirsiniz
- Uygulama günlüklerini gerçek zamanlı izleyebilirsiniz
- Uygulama durumunu takip edebilirsiniz

Sadece “rag” modülünün yanındaki oynat tuşuna tıklayın veya tüm modülleri birden başlatın.

<img src="../../../translated_images/tr/dashboard.fbe6e28bf4267ffe.png" alt="Spring Boot Dashboard" width="400"/>

**Seçenek 2: Shell scriptleri kullanma**

Tüm web uygulamalarını (modüller 01-04) başlatın:

**Bash:**
```bash
cd ..  # Kök dizinden
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Kök dizinden
.\start-all.ps1
```

Ya da sadece bu modülü başlatın:

**Bash:**
```bash
cd 03-rag
./start.sh
```

**PowerShell:**
```powershell
cd 03-rag
.\start.ps1
```

Her iki script de kök `.env` dosyasından otomatik olarak ortam değişkenlerini yükler ve JAR dosyaları yoksa oluşturur.

> **Not:** Başlatmadan önce tüm modülleri manuel olarak derlemek isterseniz:
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

Tarayıcınızda http://localhost:8081 adresini açın.

**Durdurmak için:**

**Bash:**
```bash
./stop.sh  # Yalnızca bu modül
# Veya
cd .. && ./stop-all.sh  # Tüm modüller
```

**PowerShell:**
```powershell
.\stop.ps1  # Sadece bu modül
# Veya
cd ..; .\stop-all.ps1  # Tüm modüller
```

## Uygulamayı Kullanma

Uygulama, belge yükleme ve soru sorma için web arayüzü sağlar.

<a href="images/rag-homepage.png"><img src="../../../translated_images/tr/rag-homepage.d90eb5ce1b3caa94.png" alt="RAG Application Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*RAG uygulama arayüzü - belgeleri yükleyin ve sorular sorun*

### Belge Yükleme

Başlangıç olarak belge yükleyin - test için TXT dosyaları en iyisidir. Bu dizinde LangChain4j özellikleri, RAG uygulaması ve en iyi uygulamalar hakkında bilgiler içeren bir `sample-document.txt` dosyası sağlanmıştır — sistem testi için mükemmel.

Sistem belgenizi işler, parçalara böler ve her parça için embedding oluşturur. Bu işlem belgeyi yüklediğinizde otomatik olur.

### Soru Sorma

Şimdi belge içeriği hakkında spesifik sorular sorun. Belgede açıkça belirtilmiş gerçek bilgiler deneyin. Sistem ilgili parçaları arar, isteme ekler ve bir cevap üretir.

### Kaynak Referanslarını Kontrol Etme

Her cevabın kaynak referansları ve benzerlik puanları içerdiğini fark edeceksiniz. Bu puanlar (0 ile 1 arasında) her parçanın sorunuzla ne kadar ilgili olduğunun göstergesidir. Yüksek puanlar daha iyi eşleşme demektir. Bu, cevabı kaynak materyalle doğrulamanızı sağlar.

<a href="images/rag-query-results.png"><img src="../../../translated_images/tr/rag-query-results.6d69fcec5397f355.png" alt="RAG Query Results" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sorgu sonuçları - cevap ve kaynak referansları ile alaka puanları gösterimi*

### Sorularla Deney Yapma

Farklı tipte sorular sorun:
- Özel gerçekler: "Ana konu nedir?"
- Karşılaştırmalar: "X ile Y arasındaki fark nedir?"
- Özetler: "Z hakkında temel noktaları özetle"

Sorunuz belgede ana içerikle ne kadar iyi eşleşiyorsa benzerlik puanlarının nasıl değiştiğine dikkat edin.

## Temel Kavramlar

### Parçalama Stratejisi

Belgeler, 30 token üst üste binme ile 300 token parçalar halinde bölünür. Bu denge, her parçanın anlamlı bağlam içermesini sağlar ve aynı zamanda bir istemde birden fazla parça dahil edilebilecek kadar küçük olmasını sağlar.

### Benzerlik Puanları

Puan aralığı 0 ile 1 arasındadır:
- 0.7-1.0: Yüksek derecede ilgili, tam eşleşme
- 0.5-0.7: İlgili, iyi bağlam
- 0.5’in altında: Eleme, çok alakasız

Sistem, kaliteyi sağlamak için minimum eşik değerinin üzerindeki parçaları getirir.

### Bellek İçi Depolama

Bu modül basitlik için bellekte depolama kullanır. Uygulamayı yeniden başlattığınızda yüklediğiniz belgeler kaybolur. Üretim sistemleri Qdrant veya Azure AI Search gibi kalıcı vektör veritabanları kullanır.

### Bağlam Penceresi Yönetimi

Her modelin maksimum bağlam penceresi vardır. Büyük bir belgedeki tüm parçaları dahil edemezsiniz. Sistem, en alakalı N parçayı (varsayılan 5) getirir ve sınırlar içinde kalırken doğru cevap için yeterli bağlamı sağlar.

## RAG'in Önemi Ne Zaman Artar

**RAG’i kullanın:**
- Özel belgelerle ilgili soruları yanıtlamak gerektiğinde
- Bilginin sık sık değiştiği durumlarda (politikalar, fiyatlar, spesifikasyonlar)
- Doğruluk için kaynak gösterimi gerektiğinde
- İçerik tek bir isteme sığmayacak kadar büyükse
- Doğrulanabilir, temellendirilmiş yanıtlar elde etmek istediğinizde

**RAG’i kullanmayın:**
- Sorular modelin zaten bildiği genel bilgilerle alakalıysa
- Gerçek zamanlı verilere ihtiyaç varsa (RAG yüklenen belgelere dayanır)
- İçerik doğrudan isteme sığacak kadar küçükse

## Sonraki Adımlar

**Sonraki Modül:** [04-tools - Araçlarla AI Ajanları](../04-tools/README.md)

---

**Gezinme:** [← Önceki: Modül 02 - Prompt Mühendisliği](../02-prompt-engineering/README.md) | [Ana Sayfaya Dön](../README.md) | [Sonraki: Modül 04 - Araçlar →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Feragatname**:  
Bu belge, yapay zeka çeviri servisi [Co-op Translator](https://github.com/Azure/co-op-translator) kullanılarak çevrilmiştir. Doğruluk için çaba gösterilse de, otomatik çevirilerde hatalar veya yanlışlıklar bulunabilir. Orijinal belge, kendi diliyle yetkili kaynak olarak kabul edilmelidir. Kritik bilgiler için profesyonel insan çevirisi önerilir. Bu çevirinin kullanımından kaynaklanan herhangi bir yanlış anlama veya yorumlamadan dolayı sorumluluk kabul edilmemektedir.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->