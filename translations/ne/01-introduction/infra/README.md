<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T21:53:36+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "ne"
}
-->
# LangChain4j सुरु गर्नका लागि Azure पूर्वाधार

## सामग्री तालिका

- [पूर्वआवश्यकताहरू](../../../../01-introduction/infra)
- [वास्तुकला](../../../../01-introduction/infra)
- [स्रोतहरू सिर्जना गरियो](../../../../01-introduction/infra)
- [छिटो सुरु](../../../../01-introduction/infra)
- [कन्फिगरेसन](../../../../01-introduction/infra)
- [प्रबन्धन आदेशहरू](../../../../01-introduction/infra)
- [लागत अनुकूलन](../../../../01-introduction/infra)
- [अनुगमन](../../../../01-introduction/infra)
- [समस्या समाधान](../../../../01-introduction/infra)
- [पूर्वाधार अद्यावधिक गर्दै](../../../../01-introduction/infra)
- [सफा गर्ने](../../../../01-introduction/infra)
- [फाइल संरचना](../../../../01-introduction/infra)
- [सुरक्षा सिफारिसहरू](../../../../01-introduction/infra)
- [थप स्रोतहरू](../../../../01-introduction/infra)

यो निर्देशिका Azure OpenAI स्रोतहरू तैनाथ गर्न Bicep र Azure Developer CLI (azd) प्रयोग गरी Azure पूर्वाधारलाई कोड (IaC) को रूपमा समावेश गर्दछ।

## पूर्वआवश्यकताहरू

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (संस्करण 2.50.0 वा पछि)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (संस्करण 1.5.0 वा पछि)
- स्रोतहरू सिर्जना गर्न अनुमति भएको Azure सदस्यता

## वास्तुकला

**सरलीकृत स्थानीय विकास सेटअप** - Azure OpenAI मात्र तैनाथ गर्नुहोस्, सबै अनुप्रयोगहरू स्थानीय रूपमा चलाउनुहोस्।

पूर्वाधारले तलका Azure स्रोतहरू तैनाथ गर्दछ:

### AI सेवाहरू
- **Azure OpenAI**: दुई मोडेल तैनाथी सहित संज्ञानात्मक सेवाहरू:
  - **gpt-5**: च्याट पूर्णता मोडेल (20K TPM क्षमता)
  - **text-embedding-3-small**: RAG का लागि एम्बेडिङ मोडेल (20K TPM क्षमता)

### स्थानीय विकास
सबै Spring Boot अनुप्रयोगहरू तपाईंको मेसिनमा स्थानीय रूपमा चल्छन्:
- 01-introduction (पोर्ट 8080)
- 02-prompt-engineering (पोर्ट 8083)
- 03-rag (पोर्ट 8081)
- 04-tools (पोर्ट 8084)

## स्रोतहरू सिर्जना गरियो

| स्रोत प्रकार | स्रोत नाम ढाँचा | उद्देश्य |
|--------------|----------------|---------|
| स्रोत समूह | `rg-{environmentName}` | सबै स्रोतहरू समावेश गर्दछ |
| Azure OpenAI | `aoai-{resourceToken}` | AI मोडेल होस्टिङ |

> **टिप्पणी:** `{resourceToken}` सदस्यता ID, वातावरण नाम, र स्थानबाट उत्पन्न अद्वितीय स्ट्रिङ हो

## छिटो सुरु

### 1. Azure OpenAI तैनाथ गर्नुहोस्

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

जब सोधिन्छ:
- आफ्नो Azure सदस्यता चयन गर्नुहोस्
- स्थान चयन गर्नुहोस् (सिफारिस गरिएको: `eastus2` वा `swedencentral` GPT-5 उपलब्धताका लागि)
- वातावरण नाम पुष्टि गर्नुहोस् (पूर्वनिर्धारित: `langchain4j-dev`)

यसले सिर्जना गर्नेछ:
- GPT-5 र text-embedding-3-small सहित Azure OpenAI स्रोत
- कनेक्शन विवरण आउटपुट

### 2. कनेक्शन विवरण प्राप्त गर्नुहोस्

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

यसले देखाउँछ:
- `AZURE_OPENAI_ENDPOINT`: तपाईंको Azure OpenAI अन्त बिन्दु URL
- `AZURE_OPENAI_KEY`: प्रमाणीकरणको लागि API कुञ्जी
- `AZURE_OPENAI_DEPLOYMENT`: च्याट मोडेल नाम (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: एम्बेडिङ मोडेल नाम

### 3. अनुप्रयोगहरू स्थानीय रूपमा चलाउनुहोस्

`azd up` आदेशले स्वचालित रूपमा मूल निर्देशिकामा सबै आवश्यक वातावरण चरहरूसहित `.env` फाइल सिर्जना गर्दछ।

**सिफारिस गरिएको:** सबै वेब अनुप्रयोगहरू सुरु गर्नुहोस्:

**Bash:**
```bash
# मूल निर्देशिका बाट
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# मूल निर्देशिका बाट
cd ../..
.\start-all.ps1
```

वा एकल मोड्युल सुरु गर्नुहोस्:

**Bash:**
```bash
# उदाहरण: केवल परिचय मोड्युल सुरु गर्नुहोस्
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# उदाहरण: केवल परिचय मोड्युल सुरु गर्नुहोस्
cd ../01-introduction
.\start.ps1
```

दुवै स्क्रिप्टहरूले `azd up` द्वारा सिर्जना गरिएको मूल `.env` फाइलबाट वातावरण चरहरू स्वचालित रूपमा लोड गर्छन्।

## कन्फिगरेसन

### मोडेल तैनाथीहरू अनुकूलन

मोडेल तैनाथीहरू परिवर्तन गर्न, `infra/main.bicep` सम्पादन गरी `openAiDeployments` प्यारामिटर परिमार्जन गर्नुहोस्:

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

उपलब्ध मोडेलहरू र संस्करणहरू: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure क्षेत्रहरू परिवर्तन

अर्को क्षेत्रमा तैनाथ गर्न, `infra/main.bicep` सम्पादन गर्नुहोस्:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 उपलब्धता जाँच गर्नुहोस्: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep फाइलहरूमा परिवर्तन गरेपछि पूर्वाधार अद्यावधिक गर्न:

**Bash:**
```bash
# ARM टेम्प्लेट पुनर्निर्माण गर्नुहोस्
az bicep build --file infra/main.bicep

# परिवर्तनहरू पूर्वावलोकन गर्नुहोस्
azd provision --preview

# परिवर्तनहरू लागू गर्नुहोस्
azd provision
```

**PowerShell:**
```powershell
# ARM टेम्प्लेट पुनर्निर्माण गर्नुहोस्
az bicep build --file infra/main.bicep

# परिवर्तनहरू पूर्वावलोकन गर्नुहोस्
azd provision --preview

# परिवर्तनहरू लागू गर्नुहोस्
azd provision
```

## सफा गर्ने

सबै स्रोतहरू मेटाउन:

**Bash:**
```bash
# सबै स्रोतहरू मेटाउनुहोस्
azd down

# वातावरण सहित सबै कुरा मेटाउनुहोस्
azd down --purge
```

**PowerShell:**
```powershell
# सबै स्रोतहरू मेटाउनुहोस्
azd down

# वातावरण सहित सबै कुरा मेटाउनुहोस्
azd down --purge
```

**चेतावनी**: यसले सबै Azure स्रोतहरू स्थायी रूपमा मेटाउनेछ।

## फाइल संरचना

## लागत अनुकूलन

### विकास/परीक्षण
डेभ/टेस्ट वातावरणहरूका लागि लागत घटाउन सकिन्छ:
- Azure OpenAI को लागि Standard तह (S0) प्रयोग गर्नुहोस्
- `infra/core/ai/cognitiveservices.bicep` मा क्षमता कम गर्नुहोस् (20K को सट्टा 10K TPM)
- प्रयोगमा नभएको बेला स्रोतहरू मेट्नुहोस्: `azd down`

### उत्पादन
उत्पादनका लागि:
- प्रयोग अनुसार OpenAI क्षमता बढाउनुहोस् (50K+ TPM)
- उच्च उपलब्धताका लागि जोन पुनरावृत्ति सक्षम गर्नुहोस्
- उचित अनुगमन र लागत चेतावनी लागू गर्नुहोस्

### लागत अनुमान
- Azure OpenAI: टोकन-प्रति भुक्तानी (इनपुट + आउटपुट)
- GPT-5: लगभग $3-5 प्रति 1M टोकन (हालको मूल्य जाँच गर्नुहोस्)
- text-embedding-3-small: लगभग $0.02 प्रति 1M टोकन

मूल्य गणना उपकरण: https://azure.microsoft.com/pricing/calculator/

## अनुगमन

### Azure OpenAI मेट्रिक्स हेर्नुहोस्

Azure पोर्टल → तपाईंको OpenAI स्रोत → मेट्रिक्स:
- टोकन-आधारित उपयोग
- HTTP अनुरोध दर
- प्रतिक्रिया समय
- सक्रिय टोकनहरू

## समस्या समाधान

### समस्या: Azure OpenAI सबडोमेन नाम द्वन्द्व

**त्रुटि सन्देश:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**कारण:**
तपाईंको सदस्यता/वातावरणबाट उत्पन्न सबडोमेन नाम पहिले नै प्रयोगमा छ, सम्भवतः पहिलेको पूर्ण रूपमा मेटिएको तैनाथीबाट होइन।

**समाधान:**
1. **विकल्प 1 - फरक वातावरण नाम प्रयोग गर्नुहोस्:**
   
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

2. **विकल्प 2 - Azure पोर्टल मार्फत म्यानुअल तैनाथी:**
   - Azure पोर्टल जानुहोस् → स्रोत सिर्जना गर्नुहोस् → Azure OpenAI
   - तपाईंको स्रोतको लागि अद्वितीय नाम चयन गर्नुहोस्
   - तलका मोडेलहरू तैनाथ गर्नुहोस्:
     - **GPT-5**
     - **text-embedding-3-small** (RAG मोड्युलहरूका लागि)
   - **महत्त्वपूर्ण:** तपाईंको तैनाथी नामहरू नोट गर्नुहोस् - तिनीहरू `.env` कन्फिगरेसनसँग मेल खानुपर्छ
   - तैनाथी पछि, "Keys and Endpoint" बाट अन्त बिन्दु र API कुञ्जी प्राप्त गर्नुहोस्
   - परियोजना मूलमा `.env` फाइल सिर्जना गर्नुहोस्:
     
     **उदाहरण `.env` फाइल:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**मोडेल तैनाथी नामकरण दिशानिर्देशहरू:**
- सरल, सुसंगत नामहरू प्रयोग गर्नुहोस्: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- तैनाथी नामहरू ठीक त्यही हुनुपर्छ जुन तपाईंले `.env` मा कन्फिगर गर्नुभएको छ
- सामान्य गल्ती: मोडेल एक नामले सिर्जना गर्ने तर कोडमा फरक नाम सन्दर्भ गर्ने

### समस्या: चयन गरिएको क्षेत्रमा GPT-5 उपलब्ध छैन

**समाधान:**
- GPT-5 पहुँच भएको क्षेत्र चयन गर्नुहोस् (जस्तै, eastus, swedencentral)
- उपलब्धता जाँच गर्नुहोस्: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### समस्या: तैनाथीका लागि अपर्याप्त कोटा

**समाधान:**
1. Azure पोर्टलमा कोटा वृद्धि अनुरोध गर्नुहोस्
2. वा `main.bicep` मा कम क्षमता प्रयोग गर्नुहोस् (जस्तै, क्षमता: 10)

### समस्या: स्थानीय रूपमा चलाउँदा "स्रोत फेला परेन"

**समाधान:**
1. तैनाथी जाँच गर्नुहोस्: `azd env get-values`
2. अन्त बिन्दु र कुञ्जी सही छन् कि छैनन् जाँच गर्नुहोस्
3. Azure पोर्टलमा स्रोत समूह अवस्थित छ कि छैन सुनिश्चित गर्नुहोस्

### समस्या: प्रमाणीकरण असफल

**समाधान:**
- `AZURE_OPENAI_API_KEY` सही सेट गरिएको छ कि छैन जाँच गर्नुहोस्
- कुञ्जी ढाँचा 32-अक्षर हेक्साडेसिमल स्ट्रिङ हुनुपर्छ
- आवश्यक परे Azure पोर्टलबाट नयाँ कुञ्जी प्राप्त गर्नुहोस्

### तैनाथी असफल हुन्छ

**समस्या**: `azd provision` कोटा वा क्षमता त्रुटिहरूका साथ असफल हुन्छ

**समाधान**: 
1. फरक क्षेत्र प्रयास गर्नुहोस् - क्षेत्रहरू कन्फिगर गर्ने तरिका हेर्नुहोस् [Changing Azure Regions](../../../../01-introduction/infra) खण्ड
2. तपाईंको सदस्यतामा Azure OpenAI कोटा छ कि छैन जाँच गर्नुहोस्:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### अनुप्रयोग जडान हुँदैन

**समस्या**: Java अनुप्रयोगले जडान त्रुटिहरू देखाउँछ

**समाधान**:
1. वातावरण चरहरू निर्यात गरिएको छ कि छैन जाँच गर्नुहोस्:
   
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

2. अन्त बिन्दु ढाँचा सही छ कि छैन जाँच गर्नुहोस् (`https://xxx.openai.azure.com` हुनुपर्छ)
3. API कुञ्जी Azure पोर्टलबाट प्राथमिक वा द्वितीयक कुञ्जी हो कि छैन सुनिश्चित गर्नुहोस्

**समस्या**: Azure OpenAI बाट 401 Unauthorized

**समाधान**:
1. Azure पोर्टल → Keys and Endpoint बाट नयाँ API कुञ्जी प्राप्त गर्नुहोस्
2. `AZURE_OPENAI_API_KEY` वातावरण चर पुनः निर्यात गर्नुहोस्
3. मोडेल तैनाथीहरू पूरा भएका छन् कि छैनन् जाँच गर्नुहोस् (Azure पोर्टल हेर्नुहोस्)

### प्रदर्शन समस्या

**समस्या**: प्रतिक्रिया समय ढिलो छ

**समाधान**:
1. Azure पोर्टल मेट्रिक्समा OpenAI टोकन प्रयोग र थ्रोटलिङ जाँच गर्नुहोस्
2. यदि सीमा पुगेको छ भने TPM क्षमता बढाउनुहोस्
3. उच्च reasoning-effort स्तर (low/medium/high) प्रयोग गर्ने विचार गर्नुहोस्

## पूर्वाधार अद्यावधिक गर्दै

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

## सुरक्षा सिफारिसहरू

1. **कुनै पनि बेला API कुञ्जीहरू कमिट नगर्नुहोस्** - वातावरण चरहरू प्रयोग गर्नुहोस्
2. **स्थानीय रूपमा .env फाइलहरू प्रयोग गर्नुहोस्** - `.env` लाई `.gitignore` मा थप्नुहोस्
3. **कुञ्जीहरू नियमित रूपमा घुमाउनुहोस्** - Azure पोर्टलमा नयाँ कुञ्जीहरू सिर्जना गर्नुहोस्
4. **पहुँच सीमित गर्नुहोस्** - Azure RBAC प्रयोग गरी स्रोत पहुँच नियन्त्रण गर्नुहोस्
5. **प्रयोग अनुगमन गर्नुहोस्** - Azure पोर्टलमा लागत चेतावनी सेटअप गर्नुहोस्

## थप स्रोतहरू

- [Azure OpenAI सेवा कागजात](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 मोडेल कागजात](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI कागजात](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep कागजात](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI आधिकारिक एकीकरण](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## समर्थन

समस्याहरूका लागि:
1. माथिको [समस्या समाधान खण्ड](../../../../01-introduction/infra) जाँच गर्नुहोस्
2. Azure पोर्टलमा Azure OpenAI सेवा स्वास्थ्य समीक्षा गर्नुहोस्
3. रिपोजिटरीमा मुद्दा खोल्नुहोस्

## लाइसेन्स

विवरणका लागि मूल [LICENSE](../../../../LICENSE) फाइल हेर्नुहोस्।

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**अस्वीकरण**:
यो दस्तावेज AI अनुवाद सेवा [Co-op Translator](https://github.com/Azure/co-op-translator) प्रयोग गरी अनुवाद गरिएको हो। हामी शुद्धताका लागि प्रयासरत छौं, तर कृपया ध्यान दिनुहोस् कि स्वचालित अनुवादमा त्रुटि वा अशुद्धता हुन सक्छ। मूल दस्तावेज यसको मूल भाषामा आधिकारिक स्रोत मानिनु पर्छ। महत्वपूर्ण जानकारीका लागि व्यावसायिक मानव अनुवाद सिफारिस गरिन्छ। यस अनुवादको प्रयोगबाट उत्पन्न कुनै पनि गलतफहमी वा गलत व्याख्याका लागि हामी जिम्मेवार छैनौं।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->