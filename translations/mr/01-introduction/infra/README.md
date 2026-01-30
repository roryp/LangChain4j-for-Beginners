# LangChain4j साठी Azure इन्फ्रास्ट्रक्चर सुरू करणे

## अनुक्रमणिका

- [पूर्वअट](../../../../01-introduction/infra)
- [आर्किटेक्चर](../../../../01-introduction/infra)
- [निर्मित संसाधने](../../../../01-introduction/infra)
- [त्वरित प्रारंभ](../../../../01-introduction/infra)
- [कॉन्फिगरेशन](../../../../01-introduction/infra)
- [व्यवस्थापन आदेश](../../../../01-introduction/infra)
- [खर्च ऑप्टिमायझेशन](../../../../01-introduction/infra)
- [मॉनिटरिंग](../../../../01-introduction/infra)
- [समस्या निवारण](../../../../01-introduction/infra)
- [इन्फ्रास्ट्रक्चर अद्यतनित करणे](../../../../01-introduction/infra)
- [स्वच्छता](../../../../01-introduction/infra)
- [फाइल संरचना](../../../../01-introduction/infra)
- [सुरक्षा शिफारसी](../../../../01-introduction/infra)
- [अतिरिक्त संसाधने](../../../../01-introduction/infra)

हा निर्देशिका Bicep आणि Azure Developer CLI (azd) वापरून Azure OpenAI संसाधने तैनात करण्यासाठी Azure इन्फ्रास्ट्रक्चर कोड (IaC) समाविष्ट करते.

## पूर्वअट

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (आवृत्ती 2.50.0 किंवा नंतरची)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (आवृत्ती 1.5.0 किंवा नंतरची)
- संसाधने तयार करण्यासाठी परवानग्या असलेली Azure सदस्यता

## आर्किटेक्चर

**सोपे स्थानिक विकास सेटअप** - फक्त Azure OpenAI तैनात करा, सर्व अॅप्स स्थानिकपणे चालवा.

इन्फ्रास्ट्रक्चर खालील Azure संसाधने तैनात करते:

### AI सेवा
- **Azure OpenAI**: दोन मॉडेल तैनातींसह कॉग्निटिव्ह सेवा:
  - **gpt-5**: चॅट पूर्णता मॉडेल (20K TPM क्षमता)
  - **text-embedding-3-small**: RAG साठी एम्बेडिंग मॉडेल (20K TPM क्षमता)

### स्थानिक विकास
सर्व Spring Boot अॅप्लिकेशन्स तुमच्या मशीनवर स्थानिकपणे चालतात:
- 01-introduction (पोर्ट 8080)
- 02-prompt-engineering (पोर्ट 8083)
- 03-rag (पोर्ट 8081)
- 04-tools (पोर्ट 8084)

## निर्मित संसाधने

| संसाधन प्रकार | संसाधन नाव नमुना | उद्देश |
|--------------|----------------------|---------|
| संसाधन गट | `rg-{environmentName}` | सर्व संसाधने समाविष्ट करतो |
| Azure OpenAI | `aoai-{resourceToken}` | AI मॉडेल होस्टिंग |

> **टीप:** `{resourceToken}` हा सदस्यता आयडी, पर्यावरण नाव आणि स्थान यावरून तयार केलेला अद्वितीय स्ट्रिंग आहे

## त्वरित प्रारंभ

### 1. Azure OpenAI तैनात करा

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

प्रॉम्प्ट आल्यावर:
- तुमची Azure सदस्यता निवडा
- स्थान निवडा (शिफारस: `eastus2` किंवा `swedencentral` GPT-5 उपलब्धतेसाठी)
- पर्यावरण नाव पुष्टी करा (डिफॉल्ट: `langchain4j-dev`)

यामुळे तयार होईल:
- GPT-5 आणि text-embedding-3-small सह Azure OpenAI संसाधन
- आउटपुट कनेक्शन तपशील

### 2. कनेक्शन तपशील मिळवा

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

हे दर्शवते:
- `AZURE_OPENAI_ENDPOINT`: तुमचा Azure OpenAI एंडपॉइंट URL
- `AZURE_OPENAI_KEY`: प्रमाणीकरणासाठी API की
- `AZURE_OPENAI_DEPLOYMENT`: चॅट मॉडेल नाव (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: एम्बेडिंग मॉडेल नाव

### 3. अॅप्लिकेशन्स स्थानिकपणे चालवा

`azd up` कमांड आपोआप मूळ निर्देशिकेत सर्व आवश्यक पर्यावरण चलांसह `.env` फाइल तयार करते.

**शिफारस:** सर्व वेब अॅप्लिकेशन्स सुरू करा:

**Bash:**
```bash
# मूळ निर्देशिकेतून
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# मूळ निर्देशिकेतून
cd ../..
.\start-all.ps1
```

किंवा एकल मॉड्यूल सुरू करा:

**Bash:**
```bash
# उदाहरण: फक्त परिचय मॉड्यूल सुरू करा
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# उदाहरण: फक्त परिचय मॉड्यूल सुरू करा
cd ../01-introduction
.\start.ps1
```

दोन्ही स्क्रिप्ट्स `azd up` ने तयार केलेल्या मूळ `.env` फाइलमधून पर्यावरण चल आपोआप लोड करतात.

## कॉन्फिगरेशन

### मॉडेल तैनाती सानुकूलित करणे

मॉडेल तैनाती बदलण्यासाठी, `infra/main.bicep` संपादित करा आणि `openAiDeployments` पॅरामीटर बदला:

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

उपलब्ध मॉडेल्स आणि आवृत्त्या: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure प्रदेश बदलणे

वेगळ्या प्रदेशात तैनात करण्यासाठी, `infra/main.bicep` संपादित करा:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 उपलब्धता तपासा: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep फाइल्समध्ये बदल केल्यानंतर इन्फ्रास्ट्रक्चर अद्यतनित करण्यासाठी:

**Bash:**
```bash
# ARM टेम्पलेट पुन्हा तयार करा
az bicep build --file infra/main.bicep

# बदलांचे पूर्वावलोकन करा
azd provision --preview

# बदल लागू करा
azd provision
```

**PowerShell:**
```powershell
# ARM टेम्पलेट पुन्हा तयार करा
az bicep build --file infra/main.bicep

# बदलांचे पूर्वावलोकन करा
azd provision --preview

# बदल लागू करा
azd provision
```

## स्वच्छता

सर्व संसाधने हटवण्यासाठी:

**Bash:**
```bash
# सर्व संसाधने हटवा
azd down

# पर्यावरणासह सर्व काही हटवा
azd down --purge
```

**PowerShell:**
```powershell
# सर्व संसाधने हटवा
azd down

# पर्यावरणासह सर्व काही हटवा
azd down --purge
```

**इशारा**: यामुळे सर्व Azure संसाधने कायमस्वरूपी हटवली जातील.

## फाइल संरचना

## खर्च ऑप्टिमायझेशन

### विकास/चाचणी
विकास/चाचणी पर्यावरणासाठी, खर्च कमी करण्यासाठी:
- Azure OpenAI साठी Standard tier (S0) वापरा
- `infra/core/ai/cognitiveservices.bicep` मध्ये क्षमता कमी करा (20K ऐवजी 10K TPM)
- वापरात नसताना संसाधने हटवा: `azd down`

### उत्पादन
उत्पादनासाठी:
- वापरानुसार OpenAI क्षमता वाढवा (50K+ TPM)
- उच्च उपलब्धतेसाठी झोन पुनरावृत्ती सक्षम करा
- योग्य मॉनिटरिंग आणि खर्च सूचना अंमलात आणा

### खर्च अंदाज
- Azure OpenAI: टोकन-प्रति-देय (इनपुट + आउटपुट)
- GPT-5: सुमारे $3-5 प्रति 1M टोकन्स (सध्याचे दर तपासा)
- text-embedding-3-small: सुमारे $0.02 प्रति 1M टोकन्स

किंमत कॅल्क्युलेटर: https://azure.microsoft.com/pricing/calculator/

## मॉनिटरिंग

### Azure OpenAI मेट्रिक्स पहा

Azure पोर्टल → तुमचे OpenAI संसाधन → मेट्रिक्स:
- टोकन-आधारित वापर
- HTTP विनंती दर
- प्रतिसाद वेळ
- सक्रिय टोकन्स

## समस्या निवारण

### समस्या: Azure OpenAI सबडोमेन नाव संघर्ष

**त्रुटी संदेश:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**कारण:**
तुमच्या सदस्यता/पर्यावरणातून तयार झालेलं सबडोमेन नाव आधीच वापरात आहे, कदाचित पूर्वीच्या तैनातीमुळे जी पूर्णपणे हटवलेली नाही.

**उपाय:**
1. **पर्याय 1 - वेगळं पर्यावरण नाव वापरा:**
   
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

2. **पर्याय 2 - Azure पोर्टलद्वारे मॅन्युअल तैनाती:**
   - Azure पोर्टल → Create a resource → Azure OpenAI येथे जा
   - तुमच्या संसाधनासाठी अद्वितीय नाव निवडा
   - खालील मॉडेल्स तैनात करा:
     - **GPT-5**
     - **text-embedding-3-small** (RAG मॉड्यूलसाठी)
   - **महत्त्वाचे:** तुमच्या तैनाती नावे `.env` कॉन्फिगरेशनशी जुळली पाहिजेत
   - तैनाती नंतर, "Keys and Endpoint" मधून तुमचा एंडपॉइंट आणि API की मिळवा
   - प्रोजेक्ट रूटमध्ये `.env` फाइल तयार करा:
     
     **उदाहरण `.env` फाइल:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**मॉडेल तैनाती नाव नियम:**
- सोपे, सुसंगत नावे वापरा: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- तैनाती नावे `.env` मध्ये जशी कॉन्फिगर केली आहेत तशीच असावीत
- सामान्य चूक: एका नावाने मॉडेल तयार करणे पण कोडमध्ये वेगळं नाव संदर्भित करणे

### समस्या: निवडलेल्या प्रदेशात GPT-5 उपलब्ध नाही

**उपाय:**
- GPT-5 प्रवेश असलेल्या प्रदेशाची निवड करा (उदा. eastus, swedencentral)
- उपलब्धता तपासा: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### समस्या: तैनातीसाठी अपुरी कोटा

**उपाय:**
1. Azure पोर्टलमध्ये कोटा वाढीची विनंती करा
2. किंवा `main.bicep` मध्ये कमी क्षमता वापरा (उदा. क्षमता: 10)

### समस्या: स्थानिकपणे चालवताना "Resource not found"

**उपाय:**
1. तैनाती तपासा: `azd env get-values`
2. एंडपॉइंट आणि की बरोबर आहेत का तपासा
3. Azure पोर्टलमध्ये संसाधन गट अस्तित्वात आहे का ते पाहा

### समस्या: प्रमाणीकरण अयशस्वी

**उपाय:**
- `AZURE_OPENAI_API_KEY` योग्यरित्या सेट आहे का तपासा
- की फॉरमॅट 32-अक्षरी हेक्साडेसिमल स्ट्रिंग असावा
- आवश्यक असल्यास Azure पोर्टलमधून नवीन की मिळवा

### तैनाती अयशस्वी

**समस्या**: `azd provision` कोटा किंवा क्षमता त्रुटींसह अयशस्वी

**उपाय**: 
1. वेगळ्या प्रदेशाचा प्रयत्न करा - प्रदेश कसे कॉन्फिगर करायचे ते [Changing Azure Regions](../../../../01-introduction/infra) विभागात पहा
2. तुमच्या सदस्यतेकडे Azure OpenAI कोटा आहे का तपासा:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### अॅप्लिकेशन कनेक्ट होत नाही

**समस्या**: Java अॅप्लिकेशन कनेक्शन त्रुटी दाखवते

**उपाय**:
1. पर्यावरण चल निर्यात झाले आहेत का तपासा:
   
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

2. एंडपॉइंट फॉरमॅट बरोबर आहे का तपासा (`https://xxx.openai.azure.com` असावे)
3. API की Azure पोर्टलमधील प्राथमिक किंवा द्वितीयक की आहे का तपासा

**समस्या**: Azure OpenAI कडून 401 Unauthorized

**उपाय**:
1. Azure पोर्टल → Keys and Endpoint मधून नवीन API की मिळवा
2. `AZURE_OPENAI_API_KEY` पर्यावरण चल पुन्हा निर्यात करा
3. मॉडेल तैनाती पूर्ण झाली आहे का ते तपासा (Azure पोर्टलमध्ये पहा)

### कार्यक्षमता समस्या

**समस्या**: प्रतिसाद वेळ मंद आहे

**उपाय**:
1. Azure पोर्टल मेट्रिक्समध्ये OpenAI टोकन वापर आणि थ्रॉटलिंग तपासा
2. TPM क्षमता वाढवा जर मर्यादा गाठत असाल तर
3. उच्च reasoning-effort स्तर वापरण्याचा विचार करा (कमी/मध्यम/उच्च)

## इन्फ्रास्ट्रक्चर अद्यतनित करणे

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

## सुरक्षा शिफारसी

1. **कधीही API की कमिट करू नका** - पर्यावरण चल वापरा
2. **स्थानिकपणे .env फाइल वापरा** - `.env` ला `.gitignore` मध्ये जोडा
3. **की नियमितपणे फिरवा** - Azure पोर्टलमध्ये नवीन की तयार करा
4. **प्रवेश मर्यादित करा** - Azure RBAC वापरून कोणाला संसाधनांवर प्रवेश आहे ते नियंत्रित करा
5. **वापर मॉनिटर करा** - Azure पोर्टलमध्ये खर्च सूचना सेट करा

## अतिरिक्त संसाधने

- [Azure OpenAI सेवा दस्तऐवज](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 मॉडेल दस्तऐवज](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI दस्तऐवज](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep दस्तऐवज](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI अधिकृत एकत्रीकरण](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## समर्थन

समस्या असल्यास:
1. वरील [समस्या निवारण विभाग](../../../../01-introduction/infra) तपासा
2. Azure पोर्टलमध्ये Azure OpenAI सेवा आरोग्य तपासा
3. रिपॉझिटरीमध्ये एक समस्या उघडा

## परवाना

तपशीलांसाठी मूळ [LICENSE](../../../../LICENSE) फाइल पहा.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**अस्वीकरण**:
हा दस्तऐवज AI अनुवाद सेवा [Co-op Translator](https://github.com/Azure/co-op-translator) वापरून अनुवादित केला आहे. आम्ही अचूकतेसाठी प्रयत्नशील असलो तरी, कृपया लक्षात घ्या की स्वयंचलित अनुवादांमध्ये चुका किंवा अचूकतेची कमतरता असू शकते. मूळ दस्तऐवज त्याच्या स्थानिक भाषेत अधिकृत स्रोत मानला जावा. महत्त्वाच्या माहितीसाठी व्यावसायिक मानवी अनुवाद शिफारसीय आहे. या अनुवादाच्या वापरामुळे उद्भवलेल्या कोणत्याही गैरसमजुती किंवा चुकीच्या अर्थलागी आम्ही जबाबदार नाही.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->