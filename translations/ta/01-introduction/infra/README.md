# LangChain4j தொடங்க Azure கட்டமைப்பு

## உள்ளடக்க அட்டவணை

- [முன் தேவைகள்](../../../../01-introduction/infra)
- [கட்டமைப்பு](../../../../01-introduction/infra)
- [உருவாக்கப்பட்ட வளங்கள்](../../../../01-introduction/infra)
- [விரைவு தொடக்கம்](../../../../01-introduction/infra)
- [கட்டமைப்பு](../../../../01-introduction/infra)
- [மேலாண்மை கட்டளைகள்](../../../../01-introduction/infra)
- [செலவு குறைத்தல்](../../../../01-introduction/infra)
- [கண்காணிப்பு](../../../../01-introduction/infra)
- [பிரச்சனை தீர்வு](../../../../01-introduction/infra)
- [கட்டமைப்பை புதுப்பித்தல்](../../../../01-introduction/infra)
- [சுத்தம் செய்தல்](../../../../01-introduction/infra)
- [கோப்பு அமைப்பு](../../../../01-introduction/infra)
- [பாதுகாப்பு பரிந்துரைகள்](../../../../01-introduction/infra)
- [கூடுதல் வளங்கள்](../../../../01-introduction/infra)

இந்த அடைவு Bicep மற்றும் Azure Developer CLI (azd) பயன்படுத்தி Azure OpenAI வளங்களை நிறுவ Azure கட்டமைப்பை குறியீடாக (IaC) கொண்டுள்ளது.

## முன் தேவைகள்

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (பதிப்பு 2.50.0 அல்லது அதற்கு மேல்)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (பதிப்பு 1.5.0 அல்லது அதற்கு மேல்)
- வளங்களை உருவாக்க அனுமதிகள் கொண்ட Azure சந்தா

## கட்டமைப்பு

**எளிமைப்படுத்தப்பட்ட உள்ளூர் மேம்பாட்டு அமைப்பு** - Azure OpenAI மட்டும் நிறுவி, அனைத்து செயலிகளும் உள்ளூரில் இயங்கும்.

கட்டமைப்பு பின்வரும் Azure வளங்களை நிறுவுகிறது:

### AI சேவைகள்
- **Azure OpenAI**: இரண்டு மாதிரி நிறுவல்களுடன் கூடிய அறிவாற்றல் சேவைகள்:
  - **gpt-5**: உரையாடல் முடித்தல் மாதிரி (20K TPM திறன்)
  - **text-embedding-3-small**: RAG க்கான எம்பெட்டிங் மாதிரி (20K TPM திறன்)

### உள்ளூர் மேம்பாடு
அனைத்து Spring Boot செயலிகள் உங்கள் கணினியில் உள்ளூரில் இயங்கும்:
- 01-introduction (போர்ட் 8080)
- 02-prompt-engineering (போர்ட் 8083)
- 03-rag (போர்ட் 8081)
- 04-tools (போர்ட் 8084)

## உருவாக்கப்பட்ட வளங்கள்

| வள வகை | வள பெயர் மாதிரி | நோக்கம் |
|--------------|----------------------|---------|
| வள குழு | `rg-{environmentName}` | அனைத்து வளங்களையும் கொண்டுள்ளது |
| Azure OpenAI | `aoai-{resourceToken}` | AI மாதிரி ஹோஸ்டிங் |

> **குறிப்பு:** `{resourceToken}` என்பது சந்தா ஐடி, சூழல் பெயர் மற்றும் இடம் ஆகியவற்றிலிருந்து உருவாக்கப்பட்ட தனித்துவமான சரம்

## விரைவு தொடக்கம்

### 1. Azure OpenAI நிறுவுதல்

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

கேட்கப்பட்டபோது:
- உங்கள் Azure சந்தாவை தேர்ந்தெடுக்கவும்
- இடத்தை தேர்ந்தெடுக்கவும் (பரிந்துரை: `eastus2` அல்லது `swedencentral` GPT-5 கிடைக்கும் இடம்)
- சூழல் பெயரை உறுதிப்படுத்தவும் (இயல்புநிலை: `langchain4j-dev`)

இதனால் உருவாகும்:
- GPT-5 மற்றும் text-embedding-3-small உடன் Azure OpenAI வளம்
- இணைப்பு விவரங்கள் வெளியீடு

### 2. இணைப்பு விவரங்களை பெறுதல்

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

இதன் மூலம் காண்பிக்கப்படும்:
- `AZURE_OPENAI_ENDPOINT`: உங்கள் Azure OpenAI முடிவுச்சுட்டி URL
- `AZURE_OPENAI_KEY`: அங்கீகாரத்திற்கான API விசை
- `AZURE_OPENAI_DEPLOYMENT`: உரையாடல் மாதிரி பெயர் (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: எம்பெட்டிங் மாதிரி பெயர்

### 3. செயலிகளை உள்ளூரில் இயக்குதல்

`azd up` கட்டளை தானாகவே அடிப்படை அடைவில் `.env` கோப்பை உருவாக்கி தேவையான அனைத்து சூழல் மாறிகளையும் சேர்க்கிறது.

**பரிந்துரை:** அனைத்து வலை செயலிகளையும் தொடங்கவும்:

**Bash:**
```bash
# ரூட் அடைவு கோப்பகத்திலிருந்து
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# ரூட் அடைவு கோப்பகத்திலிருந்து
cd ../..
.\start-all.ps1
```

அல்லது தனி தொகுதியை தொடங்கவும்:

**Bash:**
```bash
# உதாரணம்: அறிமுக மொடியூலை மட்டும் தொடங்கு
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# உதாரணம்: அறிமுக மொடியூலை மட்டும் தொடங்கு
cd ../01-introduction
.\start.ps1
```

இரு ஸ்கிரிப்ட்களும் `azd up` மூலம் உருவாக்கப்பட்ட அடிப்படை `.env` கோப்பிலிருந்து சூழல் மாறிகளை தானாக ஏற்றுகின்றன.

## கட்டமைப்பு

### மாதிரி நிறுவல்களை தனிப்பயனாக்குதல்

மாதிரி நிறுவல்களை மாற்ற `infra/main.bicep` கோப்பை திருத்தி `openAiDeployments` அளவுருவை மாற்றவும்:

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

கிடைக்கும் மாதிரிகள் மற்றும் பதிப்புகள்: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure பிராந்தியங்களை மாற்றுதல்

வேறு பிராந்தியத்தில் நிறுவ `infra/main.bicep` கோப்பை திருத்தவும்:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 கிடைக்கும் இடத்தை சரிபார்க்க: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep கோப்புகளில் மாற்றங்கள் செய்த பிறகு கட்டமைப்பை புதுப்பிக்க:

**Bash:**
```bash
# ARM வார்ப்புருவை மீண்டும் கட்டமைக்கவும்
az bicep build --file infra/main.bicep

# மாற்றங்களை முன்னோட்டமாக காண்க
azd provision --preview

# மாற்றங்களை பொருத்தவும்
azd provision
```

**PowerShell:**
```powershell
# ARM வார்ப்புருவை மீண்டும் கட்டமைக்கவும்
az bicep build --file infra/main.bicep

# மாற்றங்களை முன்னோட்டமாக காண்க
azd provision --preview

# மாற்றங்களை பொருத்தவும்
azd provision
```

## சுத்தம் செய்தல்

அனைத்து வளங்களையும் நீக்க:

**Bash:**
```bash
# அனைத்து வளங்களையும் நீக்கு
azd down

# சூழலை உட்பட அனைத்தையும் நீக்கு
azd down --purge
```

**PowerShell:**
```powershell
# அனைத்து வளங்களையும் நீக்கு
azd down

# சூழலை உட்பட அனைத்தையும் நீக்கு
azd down --purge
```

**எச்சரிக்கை**: இது அனைத்து Azure வளங்களையும் நிரந்தரமாக நீக்கும்.

## கோப்பு அமைப்பு

## செலவு குறைத்தல்

### மேம்பாடு/சோதனை
மேம்பாடு/சோதனை சூழல்களுக்கு செலவை குறைக்க:
- Azure OpenAI க்கான Standard tier (S0) பயன்படுத்தவும்
- `infra/core/ai/cognitiveservices.bicep` இல் திறனை குறைத்து (20K பதிலாக 10K TPM)
- பயன்படுத்தாத போது வளங்களை நீக்கவும்: `azd down`

### உற்பத்தி
உற்பத்திக்காக:
- பயன்பாட்டின் அடிப்படையில் OpenAI திறனை அதிகரிக்கவும் (50K+ TPM)
- அதிக கிடைக்கும் தன்மைக்காக மண்டல மீளமைப்பை இயக்கவும்
- சரியான கண்காணிப்பு மற்றும் செலவு எச்சரிக்கைகள் அமைக்கவும்

### செலவு மதிப்பீடு
- Azure OpenAI: டோக்கன் அடிப்படையில் கட்டணம் (உள்ளீடு + வெளியீடு)
- GPT-5: சுமார் $3-5 ஒரு மில்லியன் டோக்கனுக்கு (தற்போதைய விலை சரிபார்க்கவும்)
- text-embedding-3-small: சுமார் $0.02 ஒரு மில்லியன் டோக்கனுக்கு

விலை கணக்கீடு: https://azure.microsoft.com/pricing/calculator/

## கண்காணிப்பு

### Azure OpenAI அளவுகோல்களை பார்வையிடுதல்

Azure போர்டல் → உங்கள் OpenAI வளம் → அளவுகோல்கள்:
- டோக்கன் அடிப்படையிலான பயன்பாடு
- HTTP கோரிக்கை வீதம்
- பதிலளிக்கும் நேரம்
- செயலில் உள்ள டோக்கன்கள்

## பிரச்சனை தீர்வு

### பிரச்சனை: Azure OpenAI துணைமுக பெயர் மோதல்

**பிழை செய்தி:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**காரணம்:**
உங்கள் சந்தா/சூழல் மூலம் உருவாக்கப்பட்ட துணைமுக பெயர் ஏற்கனவே பயன்படுத்தப்பட்டிருக்கலாம், முன் நிறுவல் முழுமையாக நீக்கப்படாததனால்.

**தீர்வு:**
1. **விருப்பம் 1 - வேறு சூழல் பெயரை பயன்படுத்தவும்:**
   
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

2. **விருப்பம் 2 - Azure போர்டல் மூலம் கைமுறை நிறுவல்:**
   - Azure போர்டல் → வளம் உருவாக்கவும் → Azure OpenAI
   - உங்கள் வளத்திற்கு தனித்துவமான பெயரை தேர்ந்தெடுக்கவும்
   - பின்வரும் மாதிரிகளை நிறுவவும்:
     - **GPT-5**
     - **text-embedding-3-small** (RAG தொகுதிகளுக்கு)
   - **முக்கியம்:** உங்கள் `.env` கட்டமைப்புடன் பொருந்தும் மாதிரி பெயர்களை கவனிக்கவும்
   - நிறுவிய பிறகு "Keys and Endpoint" இல் இருந்து உங்கள் முடிவுச்சுட்டி மற்றும் API விசையை பெறவும்
   - திட்ட அடிப்படையில் `.env` கோப்பை உருவாக்கவும்:
     
     **உதாரண `.env` கோப்பு:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**மாதிரி நிறுவல் பெயரிடல் வழிகாட்டிகள்:**
- எளிமையான, ஒரே மாதிரியான பெயர்களை பயன்படுத்தவும்: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- நிறுவல் பெயர்கள் `.env` இல் உள்ளதை சரியாக பொருந்த வேண்டும்
- பொதுவான தவறு: ஒரே மாதிரிக்கு வேறு பெயர் கொடுத்து, குறியீட்டில் வேறு பெயரை குறிப்பிடுதல்

### பிரச்சனை: தேர்ந்தெடுக்கப்பட்ட பிராந்தியத்தில் GPT-5 கிடைக்கவில்லை

**தீர்வு:**
- GPT-5 கிடைக்கும் பிராந்தியத்தை தேர்ந்தெடுக்கவும் (எ.கா., eastus, swedencentral)
- கிடைக்கும் இடத்தை சரிபார்க்க: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### பிரச்சனை: நிறுவலுக்கு போதுமான குவோட்டா இல்லை

**தீர்வு:**
1. Azure போர்டலில் குவோட்டா அதிகரிப்பு கோரிக்கை செய்யவும்
2. அல்லது `main.bicep` இல் குறைந்த திறன் பயன்படுத்தவும் (எ.கா., திறன்: 10)

### பிரச்சனை: உள்ளூரில் இயக்கும்போது "வளம் காணப்படவில்லை"

**தீர்வு:**
1. நிறுவலை சரிபார்க்க: `azd env get-values`
2. முடிவுச்சுட்டி மற்றும் விசை சரியானதா என பார்க்கவும்
3. Azure போர்டலில் வள குழு உள்ளது என்பதை உறுதிப்படுத்தவும்

### பிரச்சனை: அங்கீகாரம் தோல்வி

**தீர்வு:**
- `AZURE_OPENAI_API_KEY` சரியாக அமைக்கப்பட்டுள்ளதா என சரிபார்க்கவும்
- விசை வடிவம் 32 எழுத்து ஹெக்ஸாடெசிமல் சரம் ஆக இருக்க வேண்டும்
- தேவையானால் Azure போர்டலில் இருந்து புதிய விசையை பெறவும்

### நிறுவல் தோல்வி

**பிரச்சனை**: `azd provision` குவோட்டா அல்லது திறன் பிழைகளுடன் தோல்வி

**தீர்வு**: 
1. வேறு பிராந்தியத்தை முயற்சிக்கவும் - பிராந்தியங்களை மாற்றும் வழிமுறைக்காக [Changing Azure Regions](../../../../01-introduction/infra) பகுதியை பார்க்கவும்
2. உங்கள் சந்தாவுக்கு Azure OpenAI குவோட்டா உள்ளது என்பதை சரிபார்க்கவும்:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### செயலி இணைக்கவில்லை

**பிரச்சனை**: ஜாவா செயலி இணைப்பு பிழைகள் காட்டுகிறது

**தீர்வு**:
1. சூழல் மாறிகள் ஏற்றப்பட்டுள்ளதா என சரிபார்க்கவும்:
   
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

2. முடிவுச்சுட்டி வடிவம் சரியானதா என பார்க்கவும் (`https://xxx.openai.azure.com` ஆக இருக்க வேண்டும்)
3. API விசை Azure போர்டலில் இருந்து முதன்மை அல்லது இரண்டாம் விசையாக இருக்க வேண்டும்

**பிரச்சனை**: Azure OpenAI இல் இருந்து 401 அனுமதி மறுக்கப்பட்டது

**தீர்வு**:
1. Azure போர்டல் → Keys and Endpoint இல் இருந்து புதிய API விசையை பெறவும்
2. `AZURE_OPENAI_API_KEY` சூழல் மாறியை மீண்டும் ஏற்றவும்
3. மாதிரி நிறுவல்கள் முழுமையாக உள்ளதா என Azure போர்டலில் சரிபார்க்கவும்

### செயல்திறன் பிரச்சனைகள்

**பிரச்சனை**: பதிலளிக்கும் நேரம் மெதுவாக உள்ளது

**தீர்வு**:
1. Azure போர்டல் அளவுகோல்களில் OpenAI டோக்கன் பயன்பாடு மற்றும் தடுப்பு நிலையை சரிபார்க்கவும்
2. நீங்கள் வரம்புகளை எட்டினால் TPM திறனை அதிகரிக்கவும்
3. அதிகமான காரணமளிக்கும் முயற்சி நிலையை (குறைந்த/நடுத்தர/உயர்) பயன்படுத்த பரிசீலிக்கவும்

## கட்டமைப்பை புதுப்பித்தல்

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

## பாதுகாப்பு பரிந்துரைகள்

1. **API விசைகளை ஒருபோதும் கமிட் செய்யாதீர்கள்** - சூழல் மாறிகளை பயன்படுத்தவும்
2. **உள்ளூரில் .env கோப்புகளை பயன்படுத்தவும்** - `.env` ஐ `.gitignore` இல் சேர்க்கவும்
3. **விசைகளை முறையாக மாற்றவும்** - Azure போர்டலில் புதிய விசைகளை உருவாக்கவும்
4. **அணுகலை கட்டுப்படுத்தவும்** - Azure RBAC மூலம் யார் வளங்களை அணுகலாம் என்பதை நிர்வகிக்கவும்
5. **பயன்பாட்டை கண்காணிக்கவும்** - Azure போர்டலில் செலவு எச்சரிக்கைகள் அமைக்கவும்

## கூடுதல் வளங்கள்

- [Azure OpenAI சேவை ஆவணம்](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 மாதிரி ஆவணம்](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI ஆவணம்](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep ஆவணம்](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI அதிகாரப்பூர்வ ஒருங்கிணைப்பு](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## ஆதரவு

பிரச்சனைகளுக்கு:
1. மேலே உள்ள [பிரச்சனை தீர்வு பகுதியை](../../../../01-introduction/infra) சரிபார்க்கவும்
2. Azure போர்டலில் Azure OpenAI சேவை நிலையை பரிசீலிக்கவும்
3. ரெப்போசிடரியில் ஒரு பிரச்சனை திறக்கவும்

## உரிமம்

விவரங்களுக்கு அடிப்படை [LICENSE](../../../../LICENSE) கோப்பை பார்க்கவும்.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**குறிப்பு**:  
இந்த ஆவணம் AI மொழிபெயர்ப்பு சேவை [Co-op Translator](https://github.com/Azure/co-op-translator) மூலம் மொழிபெயர்க்கப்பட்டுள்ளது. நாங்கள் துல்லியத்திற்காக முயற்சித்தாலும், தானியங்கி மொழிபெயர்ப்புகளில் பிழைகள் அல்லது தவறுகள் இருக்கக்கூடும் என்பதை தயவுசெய்து கவனிக்கவும். அசல் ஆவணம் அதன் சொந்த மொழியில் அதிகாரப்பூர்வ மூலமாக கருதப்பட வேண்டும். முக்கியமான தகவல்களுக்கு, தொழில்முறை மனித மொழிபெயர்ப்பை பரிந்துரைக்கிறோம். இந்த மொழிபெயர்ப்பின் பயன்பாட்டால் ஏற்படும் எந்தவொரு தவறான புரிதலுக்கும் அல்லது தவறான விளக்கங்களுக்கும் நாங்கள் பொறுப்பேற்கமாட்டோம்.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->