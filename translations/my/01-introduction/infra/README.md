<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:26:09+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "my"
}
-->
# LangChain4j အတွက် Azure အခြေခံအဆောက်အအုံ စတင်အသုံးပြုခြင်း

## အကြောင်းအရာ စာရင်း

- [လိုအပ်ချက်များ](../../../../01-introduction/infra)
- [ဖွဲ့စည်းပုံ](../../../../01-introduction/infra)
- [ဖန်တီးထားသော အရင်းအမြစ်များ](../../../../01-introduction/infra)
- [အမြန်စတင်ခြင်း](../../../../01-introduction/infra)
- [ဖွဲ့စည်းမှု](../../../../01-introduction/infra)
- [စီမံခန့်ခွဲမှု အမိန့်များ](../../../../01-introduction/infra)
- [ကုန်ကျစရိတ် ထိရောက်စွာ စီမံခြင်း](../../../../01-introduction/infra)
- [စောင့်ကြည့်မှု](../../../../01-introduction/infra)
- [ပြဿနာဖြေရှင်းခြင်း](../../../../01-introduction/infra)
- [အဆောက်အအုံ အပ်ဒိတ်လုပ်ခြင်း](../../../../01-introduction/infra)
- [သန့်ရှင်းရေး](../../../../01-introduction/infra)
- [ဖိုင် ဖွဲ့စည်းမှု](../../../../01-introduction/infra)
- [လုံခြုံရေး အကြံပြုချက်များ](../../../../01-introduction/infra)
- [အပိုအရင်းအမြစ်များ](../../../../01-introduction/infra)

ဤဖိုလ်ဒါတွင် Bicep နှင့် Azure Developer CLI (azd) ကို အသုံးပြု၍ Azure OpenAI အရင်းအမြစ်များကို တပ်ဆင်ရန် Azure အခြေခံအဆောက်အအုံကို ကုဒ်အဖြစ် ထည့်သွင်းထားသည်။

## လိုအပ်ချက်များ

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (ဗားရှင်း 2.50.0 သို့မဟုတ်နောက်ပိုင်း)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (ဗားရှင်း 1.5.0 သို့မဟုတ်နောက်ပိုင်း)
- အရင်းအမြစ်ဖန်တီးခွင့်ရှိသော Azure subscription တစ်ခု

## ဖွဲ့စည်းပုံ

**ရိုးရှင်းသော ဒေသတွင်း ဖွံ့ဖြိုးရေး စနစ်** - Azure OpenAI ကိုသာ တပ်ဆင်ပြီး အက်ပ်များအားလုံးကို ဒေသတွင်းတွင် ပြေးဆွဲသည်။

အဆောက်အအုံသည် အောက်ပါ Azure အရင်းအမြစ်များကို တပ်ဆင်သည်-

### AI ဝန်ဆောင်မှုများ
- **Azure OpenAI**: မော်ဒယ်နှစ်ခုဖြင့် Cognitive Services:
  - **gpt-5**: စကားပြောပြီးဆုံးမှု မော်ဒယ် (20K TPM စွမ်းဆောင်ရည်)
  - **text-embedding-3-small**: RAG အတွက် embedding မော်ဒယ် (20K TPM စွမ်းဆောင်ရည်)

### ဒေသတွင်း ဖွံ့ဖြိုးရေး
Spring Boot အက်ပ်များအားလုံးကို သင်၏ကွန်ပျူတာတွင် ဒေသတွင်းပြေးဆွဲသည်-
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## ဖန်တီးထားသော အရင်းအမြစ်များ

| အရင်းအမြစ် အမျိုးအစား | အရင်းအမြစ် အမည် ပုံစံ | ရည်ရွယ်ချက် |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | အရင်းအမြစ်အားလုံး ပါဝင်သည် |
| Azure OpenAI | `aoai-{resourceToken}` | AI မော်ဒယ် တည်ဆောက်ခြင်း |

> **မှတ်ချက်:** `{resourceToken}` သည် subscription ID, environment name နှင့် location မှ ထုတ်လုပ်ထားသော ထူးခြားသော စာကြောင်းဖြစ်သည်

## အမြန်စတင်ခြင်း

### 1. Azure OpenAI တပ်ဆင်ခြင်း

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

တောင်းဆိုသောအခါ-
- သင့် Azure subscription ကို ရွေးချယ်ပါ
- တည်နေရာကို ရွေးချယ်ပါ (အကြံပြုချက်- `eastus2` သို့မဟုတ် `swedencentral` GPT-5 ရရှိနိုင်မှုအတွက်)
- ပတ်ဝန်းကျင်အမည်ကို အတည်ပြုပါ (ပုံမှန်- `langchain4j-dev`)

ဤသည်ဖြင့် ဖန်တီးမည်-
- GPT-5 နှင့် text-embedding-3-small ပါရှိသော Azure OpenAI အရင်းအမြစ်
- ချိတ်ဆက်မှု အသေးစိတ် ထုတ်ပေးမည်

### 2. ချိတ်ဆက်မှု အသေးစိတ် ရယူခြင်း

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

ဤသည်မှာ ပြသသည်-
- `AZURE_OPENAI_ENDPOINT`: သင့် Azure OpenAI endpoint URL
- `AZURE_OPENAI_KEY`: အတည်ပြုရန် API key
- `AZURE_OPENAI_DEPLOYMENT`: စကားပြောမော်ဒယ်အမည် (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: embedding မော်ဒယ်အမည်

### 3. အက်ပ်များကို ဒေသတွင်းတွင် ပြေးဆွဲခြင်း

`azd up` အမိန့်သည် အလိုအလျောက် root ဖိုလ်ဒါတွင် `.env` ဖိုင်ကို ဖန်တီးပြီး လိုအပ်သော ပတ်ဝန်းကျင် အပြောင်းအလဲများအားလုံး ထည့်သွင်းပေးသည်။

**အကြံပြုချက်:** ဝက်ဘ်အက်ပ်များအားလုံး စတင်ပါ-

**Bash:**
```bash
# မူလဖိုင်လမ်းကြောင်းမှ
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# မူလဖိုင်လမ်းကြောင်းမှ
cd ../..
.\start-all.ps1
```

သို့မဟုတ် တစ်ခုတည်းသော မော်ဂျူးကို စတင်ပါ-

**Bash:**
```bash
# ဥပမာ - မိတ်ဆက် module ကိုသာ စတင်ပါ။
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# ဥပမာ - မိတ်ဆက် module ကိုသာ စတင်ပါ။
cd ../01-introduction
.\start.ps1
```

နှစ်ခုစလုံးသည် `azd up` မှ ဖန်တီးထားသော root `.env` ဖိုင်မှ ပတ်ဝန်းကျင် အပြောင်းအလဲများကို အလိုအလျောက် load လုပ်ပေးသည်။

## ဖွဲ့စည်းမှု

### မော်ဒယ် တပ်ဆင်မှုများကို စိတ်ကြိုက်ပြင်ဆင်ခြင်း

မော်ဒယ် တပ်ဆင်မှုများကို ပြောင်းလဲရန် `infra/main.bicep` ကို တည်းဖြတ်ပြီး `openAiDeployments` ပါရာမီတာကို ပြင်ဆင်ပါ-

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

ရရှိနိုင်သော မော်ဒယ်များနှင့် ဗားရှင်းများ: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure ဒေသများ ပြောင်းလဲခြင်း

အခြားဒေသတွင် တပ်ဆင်ရန် `infra/main.bicep` ကို တည်းဖြတ်ပါ-

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 ရရှိနိုင်မှု စစ်ဆေးရန်: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep ဖိုင်များ ပြင်ဆင်ပြီးနောက် အဆောက်အအုံကို အပ်ဒိတ်လုပ်ရန်-

**Bash:**
```bash
# ARM စံနမူနာကို ပြန်လည်တည်ဆောက်ပါ
az bicep build --file infra/main.bicep

# ပြောင်းလဲမှုများကို ကြိုတင်ကြည့်ရှုပါ
azd provision --preview

# ပြောင်းလဲမှုများကို အကောင်အထည်ဖော်ပါ
azd provision
```

**PowerShell:**
```powershell
# ARM စံနမူနာကို ပြန်လည်တည်ဆောက်ပါ
az bicep build --file infra/main.bicep

# ပြောင်းလဲမှုများကို ကြိုတင်ကြည့်ရှုပါ
azd provision --preview

# ပြောင်းလဲမှုများကို အကောင်အထည်ဖော်ပါ
azd provision
```

## သန့်ရှင်းရေး

အရင်းအမြစ်အားလုံး ဖျက်ရန်-

**Bash:**
```bash
# အရင်းအမြစ်အားလုံးကို ဖျက်ပါ
azd down

# ပတ်ဝန်းကျင်အပါအဝင် အားလုံးကို ဖျက်ပါ
azd down --purge
```

**PowerShell:**
```powershell
# အရင်းအမြစ်အားလုံးကို ဖျက်ပါ
azd down

# ပတ်ဝန်းကျင်အပါအဝင် အားလုံးကို ဖျက်ပါ
azd down --purge
```

**သတိပေးချက်**: ဤသည်သည် Azure အရင်းအမြစ်အားလုံးကို အမြဲတမ်း ဖျက်ပစ်မည်ဖြစ်သည်။

## ဖိုင် ဖွဲ့စည်းမှု

## ကုန်ကျစရိတ် ထိရောက်စွာ စီမံခြင်း

### ဖွံ့ဖြိုးရေး/စမ်းသပ်မှု
ဖွံ့ဖြိုးရေး/စမ်းသပ်မှု ပတ်ဝန်းကျင်များအတွက် ကုန်ကျစရိတ် လျော့ချနိုင်သည်-
- Azure OpenAI အတွက် Standard tier (S0) ကို အသုံးပြုပါ
- `infra/core/ai/cognitiveservices.bicep` တွင် စွမ်းဆောင်ရည်ကို 20K မှ 10K TPM သို့ လျော့ချပါ
- မသုံးသောအခါ အရင်းအမြစ်များကို ဖျက်ပစ်ပါ- `azd down`

### ထုတ်လုပ်မှု
ထုတ်လုပ်မှုအတွက်-
- အသုံးပြုမှုအပေါ် မူတည်၍ OpenAI စွမ်းဆောင်ရည် တိုးမြှင့်ပါ (50K+ TPM)
- မြင့်မားသော ရရှိနိုင်မှုအတွက် zone redundancy ကို ဖွင့်ပါ
- သင့်တော်သော စောင့်ကြည့်မှုနှင့် ကုန်ကျစရိတ် သတိပေးချက်များ ထည့်သွင်းပါ

### ကုန်ကျစရိတ် ခန့်မှန်းခြေ
- Azure OpenAI: token အလိုက် ပေးဆောင်မှု (input + output)
- GPT-5: 1M token အတွက် ~$3-5 (လက်ရှိ စျေးနှုန်း စစ်ဆေးပါ)
- text-embedding-3-small: 1M token အတွက် ~$0.02

စျေးနှုန်းတွက်ချက်ကိရိယာ: https://azure.microsoft.com/pricing/calculator/

## စောင့်ကြည့်မှု

### Azure OpenAI မီထရစ်များ ကြည့်ရှုခြင်း

Azure Portal → သင့် OpenAI အရင်းအမြစ် → Metrics:
- Token-Based Utilization
- HTTP Request Rate
- Time To Response
- Active Tokens

## ပြဿနာဖြေရှင်းခြင်း

### ပြဿနာ: Azure OpenAI subdomain အမည် တူညီမှု

**အမှားစာသား:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**အကြောင်းရင်း:**
သင့် subscription/ပတ်ဝန်းကျင်မှ ထုတ်လုပ်သော subdomain အမည်သည် ယခင်တပ်ဆင်မှုမှ မပြည့်စုံစွာ ဖျက်ပစ်ထားခြင်းကြောင့် အသုံးပြုနေပြီးဖြစ်နိုင်သည်။

**ဖြေရှင်းနည်း:**
1. **ရွေးချယ်မှု ၁ - အခြားပတ်ဝန်းကျင်အမည် အသုံးပြုခြင်း:**
   
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

2. **ရွေးချယ်မှု ၂ - Azure Portal မှ လက်ဖြင့် တပ်ဆင်ခြင်း:**
   - Azure Portal → Create a resource → Azure OpenAI သို့ သွားပါ
   - သင့်အရင်းအမြစ်အတွက် ထူးခြားသော အမည် ရွေးချယ်ပါ
   - အောက်ပါ မော်ဒယ်များ တပ်ဆင်ပါ-
     - **GPT-5**
     - **text-embedding-3-small** (RAG မော်ဂျူးများအတွက်)
   - **အရေးကြီး:** သင့် deployment အမည်များကို `.env` ဖိုင်နှင့် ကိုက်ညီစေရန် မှတ်သားပါ
   - တပ်ဆင်ပြီးနောက် "Keys and Endpoint" မှ သင့် endpoint နှင့် API key ရယူပါ
   - ပရောဂျက် root တွင် `.env` ဖိုင် ဖန်တီးပါ-
     
     **ဥပမာ `.env` ဖိုင်:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**မော်ဒယ် တပ်ဆင်မှု အမည် သတ်မှတ်ချက်များ:**
- ရိုးရှင်းပြီး တူညီသော အမည်များ အသုံးပြုပါ- `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- deployment အမည်များသည် `.env` တွင် သတ်မှတ်ထားသည့်အတိုင်း တိတိကျကျ ကိုက်ညီရမည်
- အထူးသတိ- မော်ဒယ်တစ်ခုကို အမည်တစ်ခုဖြင့် ဖန်တီးပြီး ကုဒ်တွင် အခြားအမည်ကို ရည်ညွှန်းခြင်း

### ပြဿနာ: ရွေးချယ်ထားသော ဒေသတွင် GPT-5 မရရှိနိုင်ခြင်း

**ဖြေရှင်းနည်း:**
- GPT-5 ရရှိနိုင်သော ဒေသကို ရွေးချယ်ပါ (ဥပမာ- eastus, swedencentral)
- ရရှိနိုင်မှု စစ်ဆေးရန်: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### ပြဿနာ: တပ်ဆင်မှုအတွက် ကွိုတာ မလုံလောက်ခြင်း

**ဖြေရှင်းနည်း:**
1. Azure Portal တွင် ကွိုတာ တိုးမြှင့်ရန် တောင်းဆိုပါ
2. ဒါမှမဟုတ် `main.bicep` တွင် စွမ်းဆောင်ရည်ကို နည်းပါးစွာ သတ်မှတ်ပါ (ဥပမာ- capacity: 10)

### ပြဿနာ: ဒေသတွင်းတွင် ပြေးဆွဲစဉ် "Resource not found" ဖြစ်ခြင်း

**ဖြေရှင်းနည်း:**
1. တပ်ဆင်မှုကို စစ်ဆေးပါ- `azd env get-values`
2. endpoint နှင့် key မှန်ကန်မှု စစ်ဆေးပါ
3. Azure Portal တွင် resource group ရှိမှု အတည်ပြုပါ

### ပြဿနာ: အတည်ပြုမှု မအောင်မြင်ခြင်း

**ဖြေရှင်းနည်း:**
- `AZURE_OPENAI_API_KEY` မှန်ကန်စွာ သတ်မှတ်ထားမှု စစ်ဆေးပါ
- key ပုံစံသည် 32-အက္ခရာ hexadecimal string ဖြစ်ရမည်
- လိုအပ်ပါက Azure Portal မှ အသစ် key ရယူပါ

### တပ်ဆင်မှု မအောင်မြင်ခြင်း

**ပြဿနာ**: `azd provision` ကွိုတာ သို့မဟုတ် စွမ်းဆောင်ရည် အမှားများဖြင့် မအောင်မြင်ခြင်း

**ဖြေရှင်းနည်း**: 
1. အခြားဒေသကို စမ်းသပ်ပါ - ဒေသများ ပြောင်းလဲခြင်းအပိုင်းကို ကြည့်ပါ
2. သင့် subscription တွင် Azure OpenAI ကွိုတာ ရှိမှု စစ်ဆေးပါ-
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### အက်ပ် မချိတ်ဆက်နိုင်ခြင်း

**ပြဿနာ**: Java အက်ပ်တွင် ချိတ်ဆက်မှု အမှားများ ပြသခြင်း

**ဖြေရှင်းနည်း**:
1. ပတ်ဝန်းကျင် အပြောင်းအလဲများ ထုတ်ပေးထားမှု စစ်ဆေးပါ-
   
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

2. endpoint ပုံစံ မှန်ကန်မှု စစ်ဆေးပါ (https://xxx.openai.azure.com ဖြစ်ရမည်)
3. API key သည် Azure Portal မှ ရရှိသော primary သို့မဟုတ် secondary key ဖြစ်ရမည်

**ပြဿနာ**: Azure OpenAI မှ 401 Unauthorized

**ဖြေရှင်းနည်း**:
1. Azure Portal → Keys and Endpoint မှ အသစ် API key ရယူပါ
2. `AZURE_OPENAI_API_KEY` ပတ်ဝန်းကျင် အပြောင်းအလဲကို ထပ်မံ ထုတ်ပေးပါ
3. မော်ဒယ် တပ်ဆင်မှု ပြီးစီးမှုကို Azure Portal တွင် စစ်ဆေးပါ

### စွမ်းဆောင်ရည် ပြဿနာများ

**ပြဿနာ**: တုံ့ပြန်ချိန် နှေးကွေးခြင်း

**ဖြေရှင်းနည်း**:
1. Azure Portal metrics တွင် OpenAI token အသုံးပြုမှုနှင့် throttling စစ်ဆေးပါ
2. သတ်မှတ်ထားသော TPM စွမ်းဆောင်ရည်ကို တိုးမြှင့်ပါ (ကန့်သတ်ချက်များ ရောက်ရှိနေပါက)
3. reasoning-effort အဆင့် မြင့်မားသော (low/medium/high) ကို စဉ်းစားပါ

## အဆောက်အအုံ အပ်ဒိတ်လုပ်ခြင်း

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

## လုံခြုံရေး အကြံပြုချက်များ

1. **API key များကို မတင်သွင်းပါနှင့်** - ပတ်ဝန်းကျင် အပြောင်းအလဲများ အသုံးပြုပါ
2. **ဒေသတွင်းတွင် .env ဖိုင်များ အသုံးပြုပါ** - `.env` ကို `.gitignore` ထဲ ထည့်ပါ
3. **key များကို ပုံမှန် လှည့်ပြောင်းပါ** - Azure Portal တွင် အသစ် key များ ဖန်တီးပါ
4. **ဝင်ရောက်ခွင့်ကို ကန့်သတ်ပါ** - Azure RBAC ဖြင့် အရင်းအမြစ် ဝင်ရောက်ခွင့် ထိန်းချုပ်ပါ
5. **အသုံးပြုမှုကို စောင့်ကြည့်ပါ** - Azure Portal တွင် ကုန်ကျစရိတ် သတိပေးချက်များ သတ်မှတ်ပါ

## အပိုအရင်းအမြစ်များ

- [Azure OpenAI ဝန်ဆောင်မှု စာတမ်းများ](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 မော်ဒယ် စာတမ်းများ](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI စာတမ်းများ](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep စာတမ်းများ](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI တရားဝင် ပေါင်းစည်းမှု](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## အထောက်အပံ့

ပြဿနာများအတွက်-
1. အထက်ပါ [ပြဿနာဖြေရှင်းခြင်း အပိုင်း](../../../../01-introduction/infra) ကို စစ်ဆေးပါ
2. Azure Portal တွင် Azure OpenAI ဝန်ဆောင်မှု ကျန်းမာရေးကို ကြည့်ရှုပါ
3. repository တွင် issue တစ်ခု ဖွင့်ပါ

## လိုင်စင်

အသေးစိတ်အတွက် root [LICENSE](../../../../LICENSE) ဖိုင်ကို ကြည့်ပါ။

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**အကြောင်းကြားချက်**  
ဤစာတမ်းကို AI ဘာသာပြန်ဝန်ဆောင်မှု [Co-op Translator](https://github.com/Azure/co-op-translator) ဖြင့် ဘာသာပြန်ထားပါသည်။ ကျွန်ုပ်တို့သည် တိကျမှန်ကန်မှုအတွက် ကြိုးစားနေသော်လည်း၊ အလိုအလျောက် ဘာသာပြန်မှုများတွင် အမှားများ သို့မဟုတ် မှားယွင်းချက်များ ပါဝင်နိုင်ကြောင်း သတိပြုပါရန် မေတ္တာရပ်ခံအပ်ပါသည်။ မူရင်းစာတမ်းကို မိမိဘာသာစကားဖြင့်သာ တရားဝင်အရင်းအမြစ်အဖြစ် သတ်မှတ်သင့်ပါသည်။ အရေးကြီးသော အချက်အလက်များအတွက် လူ့ဘာသာပြန်သူမှ တာဝန်ယူ၍ ဘာသာပြန်ခြင်းကို အကြံပြုပါသည်။ ဤဘာသာပြန်ချက်ကို အသုံးပြုမှုကြောင့် ဖြစ်ပေါ်လာနိုင်သည့် နားလည်မှုမှားယွင်းမှုများအတွက် ကျွန်ုပ်တို့ တာဝန်မယူပါ။
<!-- CO-OP TRANSLATOR DISCLAIMER END -->