# LangChain4j के लिए Azure इन्फ्रास्ट्रक्चर शुरूआत

## सामग्री सूची

- [पूर्वापेक्षाएँ](../../../../01-introduction/infra)
- [आर्किटेक्चर](../../../../01-introduction/infra)
- [बनाए गए संसाधन](../../../../01-introduction/infra)
- [त्वरित शुरुआत](../../../../01-introduction/infra)
- [कॉन्फ़िगरेशन](../../../../01-introduction/infra)
- [प्रबंधन कमांड](../../../../01-introduction/infra)
- [लागत अनुकूलन](../../../../01-introduction/infra)
- [निगरानी](../../../../01-introduction/infra)
- [समस्या निवारण](../../../../01-introduction/infra)
- [इन्फ्रास्ट्रक्चर अपडेट करना](../../../../01-introduction/infra)
- [साफ़-सफाई](../../../../01-introduction/infra)
- [फ़ाइल संरचना](../../../../01-introduction/infra)
- [सुरक्षा सिफारिशें](../../../../01-introduction/infra)
- [अतिरिक्त संसाधन](../../../../01-introduction/infra)

यह निर्देशिका Azure OpenAI संसाधनों को तैनात करने के लिए Bicep और Azure Developer CLI (azd) का उपयोग करते हुए Azure इन्फ्रास्ट्रक्चर कोड (IaC) रखती है।

## पूर्वापेक्षाएँ

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (संस्करण 2.50.0 या बाद का)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (संस्करण 1.5.0 या बाद का)
- संसाधन बनाने के लिए अनुमतियों के साथ Azure सदस्यता

## आर्किटेक्चर

**सरलीकृत स्थानीय विकास सेटअप** - केवल Azure OpenAI तैनात करें, सभी ऐप्स स्थानीय रूप से चलाएं।

इन्फ्रास्ट्रक्चर निम्न Azure संसाधनों को तैनात करता है:

### AI सेवाएँ
- **Azure OpenAI**: दो मॉडल तैनाती के साथ कॉग्निटिव सेवाएँ:
  - **gpt-5**: चैट पूर्णता मॉडल (20K TPM क्षमता)
  - **text-embedding-3-small**: RAG के लिए एम्बेडिंग मॉडल (20K TPM क्षमता)

### स्थानीय विकास
सभी Spring Boot एप्लिकेशन आपके मशीन पर स्थानीय रूप से चलते हैं:
- 01-introduction (पोर्ट 8080)
- 02-prompt-engineering (पोर्ट 8083)
- 03-rag (पोर्ट 8081)
- 04-tools (पोर्ट 8084)

## बनाए गए संसाधन

| संसाधन प्रकार | संसाधन नाम पैटर्न | उद्देश्य |
|--------------|----------------------|---------|
| संसाधन समूह | `rg-{environmentName}` | सभी संसाधनों को समाहित करता है |
| Azure OpenAI | `aoai-{resourceToken}` | AI मॉडल होस्टिंग |

> **नोट:** `{resourceToken}` एक अद्वितीय स्ट्रिंग है जो सदस्यता ID, पर्यावरण नाम, और स्थान से उत्पन्न होती है

## त्वरित शुरुआत

### 1. Azure OpenAI तैनात करें

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

जब पूछा जाए:
- अपनी Azure सदस्यता चुनें
- एक स्थान चुनें (सिफारिश: GPT-5 उपलब्धता के लिए `eastus2` या `swedencentral`)
- पर्यावरण नाम की पुष्टि करें (डिफ़ॉल्ट: `langchain4j-dev`)

यह बनाएगा:
- GPT-5 और text-embedding-3-small के साथ Azure OpenAI संसाधन
- आउटपुट कनेक्शन विवरण

### 2. कनेक्शन विवरण प्राप्त करें

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

यह दिखाता है:
- `AZURE_OPENAI_ENDPOINT`: आपका Azure OpenAI एंडपॉइंट URL
- `AZURE_OPENAI_KEY`: प्रमाणीकरण के लिए API कुंजी
- `AZURE_OPENAI_DEPLOYMENT`: चैट मॉडल नाम (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: एम्बेडिंग मॉडल नाम

### 3. एप्लिकेशन स्थानीय रूप से चलाएं

`azd up` कमांड स्वचालित रूप से रूट निर्देशिका में सभी आवश्यक पर्यावरण चर के साथ `.env` फ़ाइल बनाता है।

**सिफारिश:** सभी वेब एप्लिकेशन शुरू करें:

**Bash:**
```bash
# रूट निर्देशिका से
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# रूट निर्देशिका से
cd ../..
.\start-all.ps1
```

या एकल मॉड्यूल शुरू करें:

**Bash:**
```bash
# उदाहरण: केवल परिचय मॉड्यूल शुरू करें
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# उदाहरण: केवल परिचय मॉड्यूल शुरू करें
cd ../01-introduction
.\start.ps1
```

दोनों स्क्रिप्ट स्वचालित रूप से `azd up` द्वारा बनाई गई रूट `.env` फ़ाइल से पर्यावरण चर लोड करते हैं।

## कॉन्फ़िगरेशन

### मॉडल तैनाती अनुकूलित करना

मॉडल तैनाती बदलने के लिए, `infra/main.bicep` संपादित करें और `openAiDeployments` पैरामीटर संशोधित करें:

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

उपलब्ध मॉडल और संस्करण: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure क्षेत्रों को बदलना

किसी अन्य क्षेत्र में तैनात करने के लिए, `infra/main.bicep` संपादित करें:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 उपलब्धता जांचें: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep फ़ाइलों में परिवर्तन के बाद इन्फ्रास्ट्रक्चर अपडेट करने के लिए:

**Bash:**
```bash
# ARM टेम्पलेट को पुनर्निर्मित करें
az bicep build --file infra/main.bicep

# परिवर्तनों का पूर्वावलोकन करें
azd provision --preview

# परिवर्तनों को लागू करें
azd provision
```

**PowerShell:**
```powershell
# ARM टेम्पलेट को पुनर्निर्मित करें
az bicep build --file infra/main.bicep

# परिवर्तनों का पूर्वावलोकन करें
azd provision --preview

# परिवर्तनों को लागू करें
azd provision
```

## साफ़-सफाई

सभी संसाधन हटाने के लिए:

**Bash:**
```bash
# सभी संसाधनों को हटाएं
azd down

# पर्यावरण सहित सब कुछ हटाएं
azd down --purge
```

**PowerShell:**
```powershell
# सभी संसाधनों को हटाएं
azd down

# पर्यावरण सहित सब कुछ हटाएं
azd down --purge
```

**चेतावनी**: यह सभी Azure संसाधनों को स्थायी रूप से हटा देगा।

## फ़ाइल संरचना

## लागत अनुकूलन

### विकास/परीक्षण
डेव/टेस्ट वातावरण के लिए, आप लागत कम कर सकते हैं:
- Azure OpenAI के लिए Standard tier (S0) का उपयोग करें
- `infra/core/ai/cognitiveservices.bicep` में क्षमता कम करें (20K के बजाय 10K TPM)
- उपयोग में न होने पर संसाधन हटाएं: `azd down`

### उत्पादन
उत्पादन के लिए:
- उपयोग के आधार पर OpenAI क्षमता बढ़ाएं (50K+ TPM)
- उच्च उपलब्धता के लिए ज़ोन पुनरावृत्ति सक्षम करें
- उचित निगरानी और लागत अलर्ट लागू करें

### लागत अनुमान
- Azure OpenAI: टोकन-प्रति-भुगतान (इनपुट + आउटपुट)
- GPT-5: लगभग $3-5 प्रति 1M टोकन (वर्तमान मूल्य जांचें)
- text-embedding-3-small: लगभग $0.02 प्रति 1M टोकन

मूल्य निर्धारण कैलकुलेटर: https://azure.microsoft.com/pricing/calculator/

## निगरानी

### Azure OpenAI मेट्रिक्स देखें

Azure पोर्टल → आपका OpenAI संसाधन → मेट्रिक्स:
- टोकन-आधारित उपयोग
- HTTP अनुरोध दर
- प्रतिक्रिया समय
- सक्रिय टोकन

## समस्या निवारण

### समस्या: Azure OpenAI सबडोमेन नाम संघर्ष

**त्रुटि संदेश:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**कारण:**
आपकी सदस्यता/पर्यावरण से उत्पन्न सबडोमेन नाम पहले से उपयोग में है, संभवतः पिछली तैनाती से जो पूरी तरह से हटाई नहीं गई।

**समाधान:**
1. **विकल्प 1 - अलग पर्यावरण नाम का उपयोग करें:**
   
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

2. **विकल्प 2 - Azure पोर्टल के माध्यम से मैनुअल तैनाती:**
   - Azure पोर्टल जाएं → एक संसाधन बनाएँ → Azure OpenAI
   - अपने संसाधन के लिए एक अद्वितीय नाम चुनें
   - निम्नलिखित मॉडल तैनात करें:
     - **GPT-5**
     - **text-embedding-3-small** (RAG मॉड्यूल के लिए)
   - **महत्वपूर्ण:** अपनी तैनाती के नाम नोट करें - इन्हें `.env` कॉन्फ़िगरेशन से मेल खाना चाहिए
   - तैनाती के बाद, "Keys and Endpoint" से अपना एंडपॉइंट और API कुंजी प्राप्त करें
   - प्रोजेक्ट रूट में `.env` फ़ाइल बनाएं जिसमें:

     **उदाहरण `.env` फ़ाइल:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**मॉडल तैनाती नामकरण दिशानिर्देश:**
- सरल, सुसंगत नामों का उपयोग करें: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- तैनाती के नाम बिल्कुल वही होने चाहिए जो आप `.env` में कॉन्फ़िगर करते हैं
- सामान्य गलती: एक नाम से मॉडल बनाना लेकिन कोड में अलग नाम संदर्भित करना

### समस्या: चयनित क्षेत्र में GPT-5 उपलब्ध नहीं है

**समाधान:**
- GPT-5 एक्सेस वाले क्षेत्र चुनें (जैसे, eastus, swedencentral)
- उपलब्धता जांचें: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### समस्या: तैनाती के लिए अपर्याप्त कोटा

**समाधान:**
1. Azure पोर्टल में कोटा वृद्धि का अनुरोध करें
2. या `main.bicep` में कम क्षमता का उपयोग करें (जैसे, क्षमता: 10)

### समस्या: स्थानीय रूप से चलाते समय "Resource not found"

**समाधान:**
1. तैनाती सत्यापित करें: `azd env get-values`
2. एंडपॉइंट और कुंजी सही हैं यह जांचें
3. सुनिश्चित करें कि Azure पोर्टल में संसाधन समूह मौजूद है

### समस्या: प्रमाणीकरण विफल

**समाधान:**
- `AZURE_OPENAI_API_KEY` सही सेट है यह जांचें
- कुंजी प्रारूप 32-अक्षर हेक्साडेसिमल स्ट्रिंग होना चाहिए
- आवश्यक होने पर Azure पोर्टल से नई कुंजी प्राप्त करें

### तैनाती विफल होती है

**समस्या**: `azd provision` कोटा या क्षमता त्रुटियों के साथ विफल होता है

**समाधान**: 
1. अलग क्षेत्र आज़माएं - क्षेत्र कॉन्फ़िगर करने के लिए [Azure क्षेत्रों को बदलना](../../../../01-introduction/infra) अनुभाग देखें
2. जांचें कि आपकी सदस्यता के पास Azure OpenAI कोटा है:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### एप्लिकेशन कनेक्ट नहीं हो रहा

**समस्या**: Java एप्लिकेशन कनेक्शन त्रुटियाँ दिखाता है

**समाधान**:
1. पर्यावरण चर निर्यात किए गए हैं यह सत्यापित करें:
   
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

2. एंडपॉइंट प्रारूप सही है यह जांचें (यह होना चाहिए `https://xxx.openai.azure.com`)
3. सुनिश्चित करें कि API कुंजी Azure पोर्टल से प्राथमिक या द्वितीयक कुंजी है

**समस्या**: Azure OpenAI से 401 Unauthorized

**समाधान**:
1. Azure पोर्टल → Keys and Endpoint से नई API कुंजी प्राप्त करें
2. `AZURE_OPENAI_API_KEY` पर्यावरण चर पुनः निर्यात करें
3. सुनिश्चित करें कि मॉडल तैनाती पूरी हो चुकी है (Azure पोर्टल जांचें)

### प्रदर्शन समस्याएँ

**समस्या**: धीमे प्रतिक्रिया समय

**समाधान**:
1. Azure पोर्टल मेट्रिक्स में OpenAI टोकन उपयोग और थ्रॉटलिंग जांचें
2. यदि सीमा पार हो रही है तो TPM क्षमता बढ़ाएं
3. उच्च reasoning-effort स्तर (low/medium/high) का उपयोग करने पर विचार करें

## इन्फ्रास्ट्रक्चर अपडेट करना

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

## सुरक्षा सिफारिशें

1. **कभी भी API कुंजी कमिट न करें** - पर्यावरण चर का उपयोग करें
2. **स्थानीय रूप से .env फ़ाइलें उपयोग करें** - `.env` को `.gitignore` में जोड़ें
3. **कुंजी नियमित रूप से घुमाएं** - Azure पोर्टल में नई कुंजी बनाएं
4. **पहुँच सीमित करें** - Azure RBAC का उपयोग करके संसाधनों तक पहुँच नियंत्रित करें
5. **उपयोग की निगरानी करें** - Azure पोर्टल में लागत अलर्ट सेट करें

## अतिरिक्त संसाधन

- [Azure OpenAI सेवा प्रलेखन](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 मॉडल प्रलेखन](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI प्रलेखन](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep प्रलेखन](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI आधिकारिक एकीकरण](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## समर्थन

समस्याओं के लिए:
1. ऊपर [समस्या निवारण अनुभाग](../../../../01-introduction/infra) देखें
2. Azure पोर्टल में Azure OpenAI सेवा स्वास्थ्य की समीक्षा करें
3. रिपॉजिटरी में एक मुद्दा खोलें

## लाइसेंस

विवरण के लिए रूट [LICENSE](../../../../LICENSE) फ़ाइल देखें।

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**अस्वीकरण**:  
यह दस्तावेज़ AI अनुवाद सेवा [Co-op Translator](https://github.com/Azure/co-op-translator) का उपयोग करके अनुवादित किया गया है। जबकि हम सटीकता के लिए प्रयासरत हैं, कृपया ध्यान दें कि स्वचालित अनुवादों में त्रुटियाँ या अशुद्धियाँ हो सकती हैं। मूल दस्तावेज़ अपनी मूल भाषा में ही अधिकारिक स्रोत माना जाना चाहिए। महत्वपूर्ण जानकारी के लिए, पेशेवर मानव अनुवाद की सलाह दी जाती है। इस अनुवाद के उपयोग से उत्पन्न किसी भी गलतफहमी या गलत व्याख्या के लिए हम जिम्मेदार नहीं हैं।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->