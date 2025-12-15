<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:00:19+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "tr"
}
-->
# LangChain4j için Azure Altyapısına Başlarken

## İçindekiler

- [Önkoşullar](../../../../01-introduction/infra)
- [Mimari](../../../../01-introduction/infra)
- [Oluşturulan Kaynaklar](../../../../01-introduction/infra)
- [Hızlı Başlangıç](../../../../01-introduction/infra)
- [Yapılandırma](../../../../01-introduction/infra)
- [Yönetim Komutları](../../../../01-introduction/infra)
- [Maliyet Optimizasyonu](../../../../01-introduction/infra)
- [İzleme](../../../../01-introduction/infra)
- [Sorun Giderme](../../../../01-introduction/infra)
- [Altyapıyı Güncelleme](../../../../01-introduction/infra)
- [Temizleme](../../../../01-introduction/infra)
- [Dosya Yapısı](../../../../01-introduction/infra)
- [Güvenlik Önerileri](../../../../01-introduction/infra)
- [Ek Kaynaklar](../../../../01-introduction/infra)

Bu dizin, Azure OpenAI kaynaklarını dağıtmak için Bicep ve Azure Developer CLI (azd) kullanarak Azure altyapısını kod olarak (IaC) içerir.

## Önkoşullar

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (sürüm 2.50.0 veya sonrası)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (sürüm 1.5.0 veya sonrası)
- Kaynak oluşturma izinlerine sahip bir Azure aboneliği

## Mimari

**Basitleştirilmiş Yerel Geliştirme Kurulumu** - Sadece Azure OpenAI dağıtılır, tüm uygulamalar yerel olarak çalıştırılır.

Altyapı aşağıdaki Azure kaynaklarını dağıtır:

### AI Hizmetleri
- **Azure OpenAI**: İki model dağıtımı ile Bilişsel Hizmetler:
  - **gpt-5**: Sohbet tamamlama modeli (20K TPM kapasitesi)
  - **text-embedding-3-small**: RAG için gömme modeli (20K TPM kapasitesi)

### Yerel Geliştirme
Tüm Spring Boot uygulamaları makinenizde yerel olarak çalışır:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Oluşturulan Kaynaklar

| Kaynak Türü | Kaynak Adı Deseni | Amaç |
|--------------|----------------------|---------|
| Kaynak Grubu | `rg-{environmentName}` | Tüm kaynakları içerir |
| Azure OpenAI | `aoai-{resourceToken}` | AI model barındırma |

> **Not:** `{resourceToken}`, abonelik kimliği, ortam adı ve konumdan oluşturulan benzersiz bir dizgedir

## Hızlı Başlangıç

### 1. Azure OpenAI Dağıtımı

**Bash:**
```bash
cd 01-introduction
azd up
```

**PowerShell:**
```powershell
cd 01-introduction
azd up
```

İstendiğinde:
- Azure aboneliğinizi seçin
- Bir konum seçin (önerilen: GPT-5 erişimi için `eastus2` veya `swedencentral`)
- Ortam adını onaylayın (varsayılan: `langchain4j-dev`)

Bu işlemler şunları oluşturur:
- GPT-5 ve text-embedding-3-small içeren Azure OpenAI kaynağı
- Bağlantı detaylarının çıktısı

### 2. Bağlantı Detaylarını Alın

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Şunları gösterir:
- `AZURE_OPENAI_ENDPOINT`: Azure OpenAI uç noktası URL'niz
- `AZURE_OPENAI_KEY`: Kimlik doğrulama için API anahtarı
- `AZURE_OPENAI_DEPLOYMENT`: Sohbet modeli adı (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Gömme modeli adı

### 3. Uygulamaları Yerel Olarak Çalıştırın

`azd up` komutu, kök dizinde gerekli tüm ortam değişkenleriyle `.env` dosyasını otomatik oluşturur.

**Önerilen:** Tüm web uygulamalarını başlatın:

**Bash:**
```bash
# Kök dizinden
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Kök dizinden
cd ../..
.\start-all.ps1
```

Ya da tek bir modülü başlatın:

**Bash:**
```bash
# Örnek: Sadece giriş modülünü başlat
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Örnek: Sadece giriş modülünü başlat
cd ../01-introduction
.\start.ps1
```

Her iki betik de `azd up` tarafından oluşturulan kök `.env` dosyasından ortam değişkenlerini otomatik yükler.

## Yapılandırma

### Model Dağıtımlarını Özelleştirme

Model dağıtımlarını değiştirmek için `infra/main.bicep` dosyasını düzenleyin ve `openAiDeployments` parametresini değiştirin:

```bicep
param openAiDeployments array = [
  {
    name: 'gpt-5'  // Model deployment name
    model: {
      format: 'OpenAI'
      name: 'gpt-5'
      version: '2025-08-07'  // Model version
    }
    sku: {
      name: 'Standard'
      capacity: 20  // TPM in thousands
    }
  }
  // Add more deployments...
]
```

Mevcut modeller ve sürümler: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure Bölgelerini Değiştirme

Farklı bir bölgede dağıtım yapmak için `infra/main.bicep` dosyasını düzenleyin:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 kullanılabilirliğini kontrol edin: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep dosyalarında değişiklik yaptıktan sonra altyapıyı güncellemek için:

**Bash:**
```bash
# ARM şablonunu yeniden oluştur
az bicep build --file infra/main.bicep

# Değişiklikleri önizle
azd provision --preview

# Değişiklikleri uygula
azd provision
```

**PowerShell:**
```powershell
# ARM şablonunu yeniden oluştur
az bicep build --file infra/main.bicep

# Değişiklikleri önizle
azd provision --preview

# Değişiklikleri uygula
azd provision
```

## Temizleme

Tüm kaynakları silmek için:

**Bash:**
```bash
# Tüm kaynakları sil
azd down

# Ortam dahil her şeyi sil
azd down --purge
```

**PowerShell:**
```powershell
# Tüm kaynakları sil
azd down

# Ortam dahil her şeyi sil
azd down --purge
```

**Uyarı**: Bu işlem tüm Azure kaynaklarını kalıcı olarak siler.

## Dosya Yapısı

## Maliyet Optimizasyonu

### Geliştirme/Test
Geliştirme/test ortamları için maliyetleri azaltabilirsiniz:
- Azure OpenAI için Standart katman (S0) kullanın
- `infra/core/ai/cognitiveservices.bicep` dosyasında kapasiteyi 20K yerine 10K TPM olarak ayarlayın
- Kullanılmadığında kaynakları silin: `azd down`

### Üretim
Üretim için:
- Kullanıma göre OpenAI kapasitesini artırın (50K+ TPM)
- Daha yüksek kullanılabilirlik için bölge yedekliliğini etkinleştirin
- Uygun izleme ve maliyet uyarıları uygulayın

### Maliyet Tahmini
- Azure OpenAI: Token başına ödeme (girdi + çıktı)
- GPT-5: 1M token başına yaklaşık 3-5 USD (güncel fiyatları kontrol edin)
- text-embedding-3-small: 1M token başına yaklaşık 0.02 USD

Fiyat hesaplayıcı: https://azure.microsoft.com/pricing/calculator/

## İzleme

### Azure OpenAI Metriklerini Görüntüleme

Azure Portal → OpenAI kaynağınız → Metrikler:
- Token Bazlı Kullanım
- HTTP İstek Oranı
- Yanıt Süresi
- Aktif Tokenlar

## Sorun Giderme

### Sorun: Azure OpenAI alt alan adı çakışması

**Hata Mesajı:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Neden:**
Abonelik/ortamınızdan oluşturulan alt alan adı zaten kullanımda, muhtemelen tam temizlenmemiş önceki bir dağıtımdan.

**Çözüm:**
1. **Seçenek 1 - Farklı bir ortam adı kullanın:**
   
   **Bash:**
   ```bash
   azd env new my-unique-env-name
   azd up
   ```
   
   **PowerShell:**
   ```powershell
   azd env new my-unique-env-name
   azd up
   ```

2. **Seçenek 2 - Azure Portal üzerinden manuel dağıtım:**
   - Azure Portal → Kaynak oluştur → Azure OpenAI
   - Kaynağınız için benzersiz bir ad seçin
   - Aşağıdaki modelleri dağıtın:
     - **GPT-5**
     - **text-embedding-3-small** (RAG modülleri için)
   - **Önemli:** Dağıtım adlarınızı not edin - `.env` yapılandırmasıyla eşleşmelidir
   - Dağıtımdan sonra "Anahtarlar ve Uç Nokta" bölümünden uç noktanızı ve API anahtarınızı alın
   - Proje kökünde aşağıdaki gibi bir `.env` dosyası oluşturun:
     
     **Örnek `.env` dosyası:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Model Dağıtım Adlandırma Kuralları:**
- Basit, tutarlı adlar kullanın: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Dağıtım adları `.env` dosyasındaki ile tam eşleşmelidir
- Yaygın hata: Modeli bir adla oluşturup kodda farklı adla referans vermek

### Sorun: Seçilen bölgede GPT-5 mevcut değil

**Çözüm:**
- GPT-5 erişimi olan bir bölge seçin (örneğin eastus, swedencentral)
- Kullanılabilirliği kontrol edin: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Sorun: Dağıtım için yetersiz kota

**Çözüm:**
1. Azure Portal'dan kota artışı talep edin
2. Ya da `main.bicep` dosyasında daha düşük kapasite kullanın (örneğin kapasite: 10)

### Sorun: Yerelde çalıştırırken "Kaynak bulunamadı"

**Çözüm:**
1. Dağıtımı doğrulayın: `azd env get-values`
2. Uç nokta ve anahtarın doğru olduğundan emin olun
3. Kaynak grubunun Azure Portal'da var olduğundan emin olun

### Sorun: Kimlik doğrulama başarısız

**Çözüm:**
- `AZURE_OPENAI_API_KEY` değişkeninin doğru ayarlandığını kontrol edin
- Anahtar formatı 32 karakterli onaltılık dize olmalıdır
- Gerekirse Azure Portal'dan yeni anahtar alın

### Dağıtım Başarısız Oluyor

**Sorun**: `azd provision` kota veya kapasite hataları ile başarısız oluyor

**Çözüm**: 
1. Farklı bir bölge deneyin - Bölge yapılandırması için [Azure Bölgelerini Değiştirme](../../../../01-introduction/infra) bölümüne bakın
2. Aboneliğinizin Azure OpenAI kotası olduğundan emin olun:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Uygulama Bağlanmıyor

**Sorun**: Java uygulaması bağlantı hataları gösteriyor

**Çözüm**:
1. Ortam değişkenlerinin dışa aktarıldığını doğrulayın:
   
   **Bash:**
   ```bash
   echo $AZURE_OPENAI_ENDPOINT
   echo $AZURE_OPENAI_API_KEY
   ```
   
   **PowerShell:**
   ```powershell
   Write-Host $env:AZURE_OPENAI_ENDPOINT
   Write-Host $env:AZURE_OPENAI_API_KEY
   ```

2. Uç nokta formatının doğru olduğundan emin olun (`https://xxx.openai.azure.com` olmalı)
3. API anahtarının Azure Portal'dan alınan birincil veya ikincil anahtar olduğundan emin olun

**Sorun**: Azure OpenAI'den 401 Yetkisiz hatası

**Çözüm**:
1. Azure Portal → Anahtarlar ve Uç Nokta bölümünden yeni bir API anahtarı alın
2. `AZURE_OPENAI_API_KEY` ortam değişkenini yeniden dışa aktarın
3. Model dağıtımlarının tamamlandığını doğrulayın (Azure Portal'da kontrol edin)

### Performans Sorunları

**Sorun**: Yavaş yanıt süreleri

**Çözüm**:
1. Azure Portal metriklerinde OpenAI token kullanımı ve kısıtlamaları kontrol edin
2. Limitlere ulaşıyorsanız TPM kapasitesini artırın
3. Daha yüksek mantık seviyesi (düşük/orta/yüksek) kullanmayı düşünün

## Altyapıyı Güncelleme

```
infra/
├── main.bicep                       # Main infrastructure definition
├── main.json                        # Compiled ARM template (auto-generated)
├── main.bicepparam                  # Parameter file
├── README.md                        # This file
└── core/
    └── ai/
        └── cognitiveservices.bicep  # Azure OpenAI module
```

## Güvenlik Önerileri

1. **API anahtarlarını asla commit etmeyin** - Ortam değişkenleri kullanın
2. **Yerelde .env dosyaları kullanın** - `.env` dosyasını `.gitignore`a ekleyin
3. **Anahtarları düzenli döndürün** - Azure Portal'dan yeni anahtarlar oluşturun
4. **Erişimi sınırlandırın** - Kaynaklara erişimi Azure RBAC ile kontrol edin
5. **Kullanımı izleyin** - Azure Portal'da maliyet uyarıları kurun

## Ek Kaynaklar

- [Azure OpenAI Hizmeti Belgeleri](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 Model Belgeleri](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI Belgeleri](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep Belgeleri](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI Resmi Entegrasyonu](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Destek

Sorunlar için:
1. Yukarıdaki [sorun giderme bölümü](../../../../01-introduction/infra) kontrol edin
2. Azure Portal'da Azure OpenAI hizmet durumu inceleyin
3. Depoda bir sorun açın

## Lisans

Detaylar için kök dizindeki [LICENSE](../../../../LICENSE) dosyasına bakınız.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Feragatname**:  
Bu belge, AI çeviri servisi [Co-op Translator](https://github.com/Azure/co-op-translator) kullanılarak çevrilmiştir. Doğruluk için çaba gösterilse de, otomatik çevirilerin hatalar veya yanlışlıklar içerebileceğini lütfen unutmayınız. Orijinal belge, kendi dilinde yetkili kaynak olarak kabul edilmelidir. Kritik bilgiler için profesyonel insan çevirisi önerilir. Bu çevirinin kullanımı sonucu oluşabilecek yanlış anlamalar veya yorum hatalarından sorumlu değiliz.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->