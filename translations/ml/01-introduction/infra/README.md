# LangChain4j ആരംഭിക്കുന്നതിന് Azure ഇൻഫ്രാസ്ട്രക്ചർ

## ഉള്ളടക്ക പട്ടിക

- [ആവശ്യമായ മുൻകൂട്ടി](../../../../01-introduction/infra)
- [ആർക്കിടെക്ചർ](../../../../01-introduction/infra)
- [സൃഷ്ടിച്ച റിസോഴ്‌സുകൾ](../../../../01-introduction/infra)
- [വേഗത്തിലുള്ള ആരംഭം](../../../../01-introduction/infra)
- [കോൺഫിഗറേഷൻ](../../../../01-introduction/infra)
- [മാനേജ്മെന്റ് കമാൻഡുകൾ](../../../../01-introduction/infra)
- [ചെലവ് മെച്ചപ്പെടുത്തൽ](../../../../01-introduction/infra)
- [മോണിറ്ററിംഗ്](../../../../01-introduction/infra)
- [പ്രശ്നപരിഹാരം](../../../../01-introduction/infra)
- [ഇൻഫ്രാസ്ട്രക്ചർ അപ്ഡേറ്റ് ചെയ്യൽ](../../../../01-introduction/infra)
- [ശുചീകരണം](../../../../01-introduction/infra)
- [ഫയൽ ഘടന](../../../../01-introduction/infra)
- [സുരക്ഷാ ശിപാർശകൾ](../../../../01-introduction/infra)
- [കൂടുതൽ റിസോഴ്‌സുകൾ](../../../../01-introduction/infra)

ഈ ഡയറക്ടറിയിൽ Bicep, Azure Developer CLI (azd) ഉപയോഗിച്ച് Azure OpenAI റിസോഴ്‌സുകൾ വിന്യസിക്കുന്നതിനുള്ള Azure ഇൻഫ്രാസ്ട്രക്ചർ കോഡ് (IaC) ഉൾപ്പെടുന്നു.

## ആവശ്യമായ മുൻകൂട്ടി

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (പതിപ്പ് 2.50.0 അല്ലെങ്കിൽ അതിനുശേഷം)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (പതിപ്പ് 1.5.0 അല്ലെങ്കിൽ അതിനുശേഷം)
- റിസോഴ്‌സുകൾ സൃഷ്ടിക്കാൻ അനുമതിയുള്ള Azure സബ്സ്ക്രിപ്ഷൻ

## ആർക്കിടെക്ചർ

**സിംപ്ലിഫൈഡ് ലോക്കൽ ഡെവലപ്പ്മെന്റ് സെറ്റപ്പ്** - Azure OpenAI മാത്രം വിന്യസിക്കുക, എല്ലാ ആപ്പുകളും ലോക്കലായി പ്രവർത്തിക്കുക.

ഇൻഫ്രാസ്ട്രക്ചർ താഴെപ്പറയുന്ന Azure റിസോഴ്‌സുകൾ വിന്യസിക്കുന്നു:

### AI സേവനങ്ങൾ
- **Azure OpenAI**: രണ്ട് മോഡൽ വിന്യസനങ്ങളുള്ള കോഗ്നിറ്റീവ് സർവീസസ്:
  - **gpt-5**: ചാറ്റ് പൂർത്തീകരണ മോഡൽ (20K TPM ശേഷി)
  - **text-embedding-3-small**: RAG-ക്കായി എംബെഡ്ഡിംഗ് മോഡൽ (20K TPM ശേഷി)

### ലോക്കൽ ഡെവലപ്പ്മെന്റ്
എല്ലാ Spring Boot ആപ്പുകളും നിങ്ങളുടെ മെഷീനിൽ ലോക്കലായി പ്രവർത്തിക്കുന്നു:
- 01-introduction (പോർട്ട് 8080)
- 02-prompt-engineering (പോർട്ട് 8083)
- 03-rag (പോർട്ട് 8081)
- 04-tools (പോർട്ട് 8084)

## സൃഷ്ടിച്ച റിസോഴ്‌സുകൾ

| റിസോഴ്‌സ് തരം | റിസോഴ്‌സ് നാമ മാതൃക | ഉദ്ദേശ്യം |
|--------------|----------------------|---------|
| റിസോഴ്‌സ് ഗ്രൂപ്പ് | `rg-{environmentName}` | എല്ലാ റിസോഴ്‌സുകളും ഉൾക്കൊള്ളുന്നു |
| Azure OpenAI | `aoai-{resourceToken}` | AI മോഡൽ ഹോസ്റ്റിംഗ് |

> **കുറിപ്പ്:** `{resourceToken}` സബ്സ്ക്രിപ്ഷൻ ഐഡി, എൻവയോൺമെന്റ് നാമം, ലൊക്കേഷൻ എന്നിവയിൽ നിന്നുള്ള ഒരു വ്യത്യസ്തമായ സ്ട്രിംഗ് ആണ്

## വേഗത്തിലുള്ള ആരംഭം

### 1. Azure OpenAI വിന്യസിക്കുക

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

പ്രോംപ്റ്റ് വന്നപ്പോൾ:
- നിങ്ങളുടെ Azure സബ്സ്ക്രിപ്ഷൻ തിരഞ്ഞെടുക്കുക
- ഒരു ലൊക്കേഷൻ തിരഞ്ഞെടുക്കുക (ശുപാർശ: `eastus2` അല്ലെങ്കിൽ `swedencentral` GPT-5 ലഭ്യതക്കായി)
- എൻവയോൺമെന്റ് നാമം സ്ഥിരീകരിക്കുക (ഡിഫോൾട്ട്: `langchain4j-dev`)

ഇത് സൃഷ്ടിക്കും:
- GPT-5, text-embedding-3-small ഉള്ള Azure OpenAI റിസോഴ്‌സ്
- കണക്ഷൻ വിശദാംശങ്ങൾ ഔട്ട്പുട്ട് ചെയ്യുക

### 2. കണക്ഷൻ വിശദാംശങ്ങൾ നേടുക

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

ഇത് പ്രദർശിപ്പിക്കും:
- `AZURE_OPENAI_ENDPOINT`: നിങ്ങളുടെ Azure OpenAI എന്റ്പോയിന്റ് URL
- `AZURE_OPENAI_KEY`: ഓതന്റിക്കേഷൻ API കീ
- `AZURE_OPENAI_DEPLOYMENT`: ചാറ്റ് മോഡൽ നാമം (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: എംബെഡ്ഡിംഗ് മോഡൽ നാമം

### 3. ആപ്പുകൾ ലോക്കലായി പ്രവർത്തിപ്പിക്കുക

`azd up` കമാൻഡ് റൂട്ട് ഡയറക്ടറിയിൽ എല്ലാ ആവശ്യമായ എൻവയോൺമെന്റ് വേരിയബിളുകളോടുകൂടിയ `.env` ഫയൽ സ്വയം സൃഷ്ടിക്കും.

**ശുപാർശ:** എല്ലാ വെബ് ആപ്പുകളും ആരംഭിക്കുക:

**Bash:**
```bash
# റൂട്ട് ഡയറക്ടറിയിൽ നിന്ന്
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# റൂട്ട് ഡയറക്ടറിയിൽ നിന്ന്
cd ../..
.\start-all.ps1
```

അല്ലെങ്കിൽ ഒരു മാത്രം മോഡ്യൂൾ ആരംഭിക്കുക:

**Bash:**
```bash
# ഉദാഹരണം: പരിചയ模块 മാത്രം ആരംഭിക്കുക
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# ഉദാഹരണം: പരിചയ模块 മാത്രം ആരംഭിക്കുക
cd ../01-introduction
.\start.ps1
```

രണ്ടു സ്ക്രിപ്റ്റുകളും `azd up` സൃഷ്ടിച്ച റൂട്ട് `.env` ഫയലിൽ നിന്നുള്ള എൻവയോൺമെന്റ് വേരിയബിളുകൾ സ്വയം ലോഡ് ചെയ്യും.

## കോൺഫിഗറേഷൻ

### മോഡൽ വിന്യസനങ്ങൾ ഇഷ്ടാനുസൃതമാക്കൽ

മോഡൽ വിന്യസനങ്ങൾ മാറ്റാൻ, `infra/main.bicep` എഡിറ്റ് ചെയ്ത് `openAiDeployments` പാരാമീറ്റർ മാറ്റുക:

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

ലഭ്യമായ മോഡലുകളും പതിപ്പുകളും: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure മേഖലകൾ മാറ്റുക

മറ്റൊരു മേഖലയിലായി വിന്യസിക്കാൻ, `infra/main.bicep` എഡിറ്റ് ചെയ്യുക:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 ലഭ്യത പരിശോധിക്കുക: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep ഫയലുകളിൽ മാറ്റങ്ങൾ ചെയ്ത ശേഷം ഇൻഫ്രാസ്ട്രക്ചർ അപ്ഡേറ്റ് ചെയ്യാൻ:

**Bash:**
```bash
# ARM ടെംപ്ലേറ്റ് പുനർനിർമ്മിക്കുക
az bicep build --file infra/main.bicep

# മാറ്റങ്ങൾ മുൻകൂർദർശനം
azd provision --preview

# മാറ്റങ്ങൾ പ്രയോഗിക്കുക
azd provision
```

**PowerShell:**
```powershell
# ARM ടെംപ്ലേറ്റ് പുനർനിർമ്മിക്കുക
az bicep build --file infra/main.bicep

# മാറ്റങ്ങൾ മുൻകൂർദർശനം
azd provision --preview

# മാറ്റങ്ങൾ പ്രയോഗിക്കുക
azd provision
```

## ശുചീകരണം

എല്ലാ റിസോഴ്‌സുകളും ഇല്ലാതാക്കാൻ:

**Bash:**
```bash
# എല്ലാ വിഭവങ്ങളും ഇല്ലാതാക്കുക
azd down

# പരിസ്ഥിതിയും ഉൾപ്പെടെ എല്ലാം ഇല്ലാതാക്കുക
azd down --purge
```

**PowerShell:**
```powershell
# എല്ലാ വിഭവങ്ങളും ഇല്ലാതാക്കുക
azd down

# പരിസ്ഥിതിയും ഉൾപ്പെടെ എല്ലാം ഇല്ലാതാക്കുക
azd down --purge
```

**മുന്നറിയിപ്പ്**: ഇത് എല്ലാ Azure റിസോഴ്‌സുകളും സ്ഥിരമായി ഇല്ലാതാക്കും.

## ഫയൽ ഘടന

## ചെലവ് മെച്ചപ്പെടുത്തൽ

### ഡെവലപ്പ്മെന്റ്/ടെസ്റ്റിംഗ്
ഡെവ്/ടെസ്റ്റ് എൻവയോൺമെന്റുകൾക്കായി ചെലവ് കുറയ്ക്കാൻ:
- Azure OpenAI-യ്ക്ക് സ്റ്റാൻഡേർഡ് ടിയർ (S0) ഉപയോഗിക്കുക
- `infra/core/ai/cognitiveservices.bicep`-ൽ ശേഷി കുറയ്ക്കുക (20K പകരം 10K TPM)
- ഉപയോഗിക്കാത്തപ്പോൾ റിസോഴ്‌സുകൾ ഇല്ലാതാക്കുക: `azd down`

### പ്രൊഡക്ഷൻ
പ്രൊഡക്ഷനായി:
- ഉപയോഗം അനുസരിച്ച് OpenAI ശേഷി വർദ്ധിപ്പിക്കുക (50K+ TPM)
- ഉയർന്ന ലഭ്യതക്കായി സോൺ റിഡണ്ടൻസി സജ്ജമാക്കുക
- ശരിയായ മോണിറ്ററിംഗ്, ചെലവ് അലർട്ടുകൾ നടപ്പിലാക്കുക

### ചെലവ് കണക്കുകൂട്ടൽ
- Azure OpenAI: ടോക്കൺ അടിസ്ഥാനത്തിൽ പണം (ഇൻപുട്ട് + ഔട്ട്പുട്ട്)
- GPT-5: ഏകദേശം $3-5 1M ടോക്കണിന് (നിലവിലെ വില പരിശോധിക്കുക)
- text-embedding-3-small: ഏകദേശം $0.02 1M ടോക്കണിന്

വില കാൽക്കുലേറ്റർ: https://azure.microsoft.com/pricing/calculator/

## മോണിറ്ററിംഗ്

### Azure OpenAI മെട്രിക്‌സ് കാണുക

Azure പോർട്ടലിൽ → നിങ്ങളുടെ OpenAI റിസോഴ്‌സ് → മെട്രിക്‌സ്:
- ടോക്കൺ അടിസ്ഥാന ഉപയോഗം
- HTTP അഭ്യർത്ഥന നിരക്ക്
- പ്രതികരണ സമയം
- സജീവ ടോക്കണുകൾ

## പ്രശ്നപരിഹാരം

### പ്രശ്നം: Azure OpenAI സബ്‌ഡൊമെയ്ൻ നാമം സംഘർഷം

**പിശക് സന്ദേശം:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**കാരണം:**
നിങ്ങളുടെ സബ്സ്ക്രിപ്ഷൻ/എൻവയോൺമെന്റ് നിന്നുള്ള സബ്‌ഡൊമെയ്ൻ നാമം ഇതിനകം ഉപയോഗത്തിലാണ്, മുമ്പത്തെ ഒരു വിന്യസനത്തിൽ നിന്ന് പൂർണ്ണമായി നീക്കം ചെയ്യാത്തതാകാം.

**പരിഹാരം:**
1. **ഓപ്ഷൻ 1 - വ്യത്യസ്ത എൻവയോൺമെന്റ് നാമം ഉപയോഗിക്കുക:**
   
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

2. **ഓപ്ഷൻ 2 - Azure പോർട്ടൽ വഴി മാനുവൽ വിന്യസനം:**
   - Azure പോർട്ടലിൽ → Create a resource → Azure OpenAI
   - നിങ്ങളുടെ റിസോഴ്‌സിന് വ്യത്യസ്തമായ ഒരു നാമം തിരഞ്ഞെടുക്കുക
   - താഴെപ്പറയുന്ന മോഡലുകൾ വിന്യസിക്കുക:
     - **GPT-5**
     - **text-embedding-3-small** (RAG മോഡ്യൂളുകൾക്കായി)
   - **പ്രധാനമാണ്:** നിങ്ങളുടെ വിന്യസന നാമങ്ങൾ `.env` കോൺഫിഗറേഷനുമായി പൊരുത്തപ്പെടണം
   - വിന്യസനത്തിന് ശേഷം "Keys and Endpoint" ൽ നിന്നുള്ള എന്റ്പോയിന്റും API കീയും നേടുക
   - പ്രോജക്ട് റൂട്ടിൽ `.env` ഫയൽ സൃഷ്ടിക്കുക:
     
     **ഉദാഹരണ `.env` ഫയൽ:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**മോഡൽ വിന്യസന നാമ നിർദ്ദേശങ്ങൾ:**
- ലളിതവും സ്ഥിരതയുള്ള നാമങ്ങൾ ഉപയോഗിക്കുക: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- വിന്യസന നാമങ്ങൾ `.env`-ൽ കോൺഫിഗർ ചെയ്തതുമായി കൃത്യമായി പൊരുത്തപ്പെടണം
- സാധാരണ പിശക്: ഒരു നാമത്തിൽ മോഡൽ സൃഷ്ടിച്ച് കോഡിൽ വ്യത്യസ്ത നാമം റഫറൻസ് ചെയ്യുക

### പ്രശ്നം: തിരഞ്ഞെടുക്കപ്പെട്ട മേഖലയിലെ GPT-5 ലഭ്യമല്ല

**പരിഹാരം:**
- GPT-5 ലഭ്യമാകുന്ന മേഖല തിരഞ്ഞെടുക്കുക (ഉദാ: eastus, swedencentral)
- ലഭ്യത പരിശോധിക്കുക: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### പ്രശ്നം: വിന്യസനത്തിന് മതിയായ ക്വോട്ട ഇല്ല

**പരിഹാരം:**
1. Azure പോർട്ടലിൽ ക്വോട്ട വർദ്ധിപ്പിക്കാൻ അപേക്ഷിക്കുക
2. അല്ലെങ്കിൽ `main.bicep`-ൽ കുറഞ്ഞ ശേഷി ഉപയോഗിക്കുക (ഉദാ: capacity: 10)

### പ്രശ്നം: ലോക്കലായി പ്രവർത്തിക്കുമ്പോൾ "Resource not found"

**പരിഹാരം:**
1. വിന്യസനം പരിശോധിക്കുക: `azd env get-values`
2. എന്റ്പോയിന്റും കീയും ശരിയാണെന്ന് ഉറപ്പാക്കുക
3. Azure പോർട്ടലിൽ റിസോഴ്‌സ് ഗ്രൂപ്പ് നിലവിലുണ്ടെന്ന് ഉറപ്പാക്കുക

### പ്രശ്നം: ഓതന്റിക്കേഷൻ പരാജയം

**പരിഹാരം:**
- `AZURE_OPENAI_API_KEY` ശരിയായി സജ്ജമാക്കിയിട്ടുണ്ടെന്ന് പരിശോധിക്കുക
- കീ ഫോർമാറ്റ് 32-അക്ഷര ഹെക്സാഡെസിമൽ സ്ട്രിംഗ് ആയിരിക്കണം
- ആവശ്യമെങ്കിൽ Azure പോർട്ടലിൽ നിന്ന് പുതിയ കീ നേടുക

### വിന്യസനം പരാജയപ്പെടുന്നു

**പ്രശ്നം**: `azd provision` ക്വോട്ട അല്ലെങ്കിൽ ശേഷി പിശകുകളോടെ പരാജയപ്പെടുന്നു

**പരിഹാരം**: 
1. വ്യത്യസ്ത മേഖല പരീക്ഷിക്കുക - [Changing Azure Regions](../../../../01-introduction/infra) വിഭാഗം കാണുക
2. നിങ്ങളുടെ സബ്സ്ക്രിപ്ഷനിൽ Azure OpenAI ക്വോട്ട ഉണ്ടെന്ന് പരിശോധിക്കുക:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### ആപ്പ് കണക്ട് ചെയ്യുന്നില്ല

**പ്രശ്നം**: ജാവ ആപ്പ് കണക്ഷൻ പിശകുകൾ കാണിക്കുന്നു

**പരിഹാരം**:
1. എൻവയോൺമെന്റ് വേരിയബിളുകൾ എക്സ്പോർട്ട് ചെയ്തിട്ടുണ്ടെന്ന് ഉറപ്പാക്കുക:
   
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

2. എന്റ്പോയിന്റ് ഫോർമാറ്റ് ശരിയാണെന്ന് പരിശോധിക്കുക (`https://xxx.openai.azure.com` ആയിരിക്കണം)
3. API കീ Azure പോർട്ടലിൽ നിന്നുള്ള പ്രൈമറി അല്ലെങ്കിൽ സെക്കൻഡറി കീ ആണെന്ന് ഉറപ്പാക്കുക

**പ്രശ്നം**: Azure OpenAI-യിൽ നിന്ന് 401 Unauthorized

**പരിഹാരം**:
1. Azure പോർട്ടലിൽ → Keys and Endpoint → പുതിയ API കീ നേടുക
2. `AZURE_OPENAI_API_KEY` എൻവയോൺമെന്റ് വേരിയബിൾ വീണ്ടും എക്സ്പോർട്ട് ചെയ്യുക
3. മോഡൽ വിന്യസനങ്ങൾ പൂർത്തിയായിട്ടുണ്ടെന്ന് ഉറപ്പാക്കുക (Azure പോർട്ടൽ പരിശോധിക്കുക)

### പ്രകടന പ്രശ്നങ്ങൾ

**പ്രശ്നം**: പ്രതികരണ സമയം മന്ദഗതിയിലാണ്

**പരിഹാരം**:
1. Azure പോർട്ടലിലെ മെട്രിക്‌സിൽ OpenAI ടോക്കൺ ഉപയോഗവും ത്രോട്ട്ലിംഗും പരിശോധിക്കുക
2. പരിധി തൊട്ടാൽ TPM ശേഷി വർദ്ധിപ്പിക്കുക
3. ഉയർന്ന റീസണിംഗ്-എഫോർട്ട് ലെവൽ (low/medium/high) ഉപയോഗിക്കാൻ പരിഗണിക്കുക

## ഇൻഫ്രാസ്ട്രക്ചർ അപ്ഡേറ്റ് ചെയ്യൽ

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

## സുരക്ഷാ ശിപാർശകൾ

1. **API കീകൾ ഒരിക്കലും കോഡ് റിപോസിറ്ററിയിൽ കമ്മിറ്റ് ചെയ്യരുത്** - എൻവയോൺമെന്റ് വേരിയബിളുകൾ ഉപയോഗിക്കുക
2. **ലോക്കലായി .env ഫയലുകൾ ഉപയോഗിക്കുക** - `.env`-നെ `.gitignore`-ലേക്ക് ചേർക്കുക
3. **കീകൾ സ്ഥിരമായി റോട്ടേറ്റ് ചെയ്യുക** - Azure പോർട്ടലിൽ പുതിയ കീകൾ സൃഷ്ടിക്കുക
4. **പ്രവേശനം നിയന്ത്രിക്കുക** - Azure RBAC ഉപയോഗിച്ച് റിസോഴ്‌സുകൾക്ക് ആക്‌സസ് നിയന്ത്രിക്കുക
5. **ഉപയോഗം മോണിറ്റർ ചെയ്യുക** - Azure പോർട്ടലിൽ ചെലവ് അലർട്ടുകൾ സജ്ജമാക്കുക

## കൂടുതൽ റിസോഴ്‌സുകൾ

- [Azure OpenAI സർവീസ് ഡോക്യുമെന്റേഷൻ](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 മോഡൽ ഡോക്യുമെന്റേഷൻ](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI ഡോക്യുമെന്റേഷൻ](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep ഡോക്യുമെന്റേഷൻ](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI ഔദ്യോഗിക ഇന്റഗ്രേഷൻ](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## പിന്തുണ

പ്രശ്നങ്ങൾക്കായി:
1. മുകളിൽ [പ്രശ്നപരിഹാരം](../../../../01-introduction/infra) വിഭാഗം പരിശോധിക്കുക
2. Azure പോർട്ടലിൽ Azure OpenAI സർവീസ് ഹെൽത്ത് പരിശോധിക്കുക
3. റിപോസിറ്ററിയിൽ ഒരു ഇഷ്യൂ തുറക്കുക

## ലൈസൻസ്

വിവരങ്ങൾക്ക് റൂട്ട് [LICENSE](../../../../LICENSE) ഫയൽ കാണുക.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**അസൂയാപത്രം**:  
ഈ രേഖ AI വിവർത്തന സേവനം [Co-op Translator](https://github.com/Azure/co-op-translator) ഉപയോഗിച്ച് വിവർത്തനം ചെയ്തതാണ്. നാം കൃത്യതയ്ക്ക് ശ്രമിച്ചിട്ടുണ്ടെങ്കിലും, സ്വയം പ്രവർത്തിക്കുന്ന വിവർത്തനങ്ങളിൽ പിശകുകൾ അല്ലെങ്കിൽ തെറ്റുകൾ ഉണ്ടാകാമെന്ന് ദയവായി ശ്രദ്ധിക്കുക. അതിന്റെ മാതൃഭാഷയിലുള്ള യഥാർത്ഥ രേഖ അധികാരപരമായ ഉറവിടമായി കണക്കാക്കപ്പെടണം. നിർണായക വിവരങ്ങൾക്ക്, പ്രൊഫഷണൽ മനുഷ്യ വിവർത്തനം ശുപാർശ ചെയ്യപ്പെടുന്നു. ഈ വിവർത്തനം ഉപയോഗിക്കുന്നതിൽ നിന്നുണ്ടാകുന്ന ഏതെങ്കിലും തെറ്റിദ്ധാരണകൾക്കോ തെറ്റായ വ്യാഖ്യാനങ്ങൾക്കോ ഞങ്ങൾ ഉത്തരവാദികളല്ല.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->