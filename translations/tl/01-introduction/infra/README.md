<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:15:32+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "tl"
}
-->
# Azure Infrastructure para sa LangChain4j Pagsisimula

## Talaan ng Nilalaman

- [Mga Kinakailangan](../../../../01-introduction/infra)
- [Arkitektura](../../../../01-introduction/infra)
- [Mga Nilikhang Resources](../../../../01-introduction/infra)
- [Mabilis na Pagsisimula](../../../../01-introduction/infra)
- [Konfigurasyon](../../../../01-introduction/infra)
- [Mga Utos sa Pamamahala](../../../../01-introduction/infra)
- [Pag-optimize ng Gastos](../../../../01-introduction/infra)
- [Pagmamanman](../../../../01-introduction/infra)
- [Pag-aayos ng Problema](../../../../01-introduction/infra)
- [Pag-update ng Infrastructure](../../../../01-introduction/infra)
- [Paglilinis](../../../../01-introduction/infra)
- [Istruktura ng File](../../../../01-introduction/infra)
- [Mga Rekomendasyon sa Seguridad](../../../../01-introduction/infra)
- [Karagdagang Mga Resources](../../../../01-introduction/infra)

Ang direktoryong ito ay naglalaman ng Azure infrastructure bilang code (IaC) gamit ang Bicep at Azure Developer CLI (azd) para sa pag-deploy ng Azure OpenAI resources.

## Mga Kinakailangan

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (bersyon 2.50.0 o mas bago)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (bersyon 1.5.0 o mas bago)
- Isang Azure subscription na may pahintulot na gumawa ng mga resources

## Arkitektura

**Pinasimpleng Setup para sa Lokal na Pag-develop** - I-deploy lamang ang Azure OpenAI, patakbuhin ang lahat ng apps nang lokal.

Ang infrastructure ay nagde-deploy ng mga sumusunod na Azure resources:

### AI Services
- **Azure OpenAI**: Cognitive Services na may dalawang deployment ng modelo:
  - **gpt-5**: Chat completion model (20K TPM kapasidad)
  - **text-embedding-3-small**: Embedding model para sa RAG (20K TPM kapasidad)

### Lokal na Pag-develop
Lahat ng Spring Boot applications ay tumatakbo nang lokal sa iyong makina:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Mga Nilikhang Resources

| Uri ng Resource | Pattern ng Pangalan ng Resource | Layunin |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | Naglalaman ng lahat ng resources |
| Azure OpenAI | `aoai-{resourceToken}` | Pagho-host ng AI model |

> **Tandaan:** Ang `{resourceToken}` ay isang natatanging string na ginawa mula sa subscription ID, pangalan ng environment, at lokasyon

## Mabilis na Pagsisimula

### 1. I-deploy ang Azure OpenAI

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

Kapag na-prompt:
- Piliin ang iyong Azure subscription
- Pumili ng lokasyon (inirerekomenda: `eastus2` o `swedencentral` para sa availability ng GPT-5)
- Kumpirmahin ang pangalan ng environment (default: `langchain4j-dev`)

Ito ay lilikha ng:
- Azure OpenAI resource na may GPT-5 at text-embedding-3-small
- Output ng mga detalye ng koneksyon

### 2. Kunin ang Mga Detalye ng Koneksyon

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Ipinapakita nito:
- `AZURE_OPENAI_ENDPOINT`: Ang iyong Azure OpenAI endpoint URL
- `AZURE_OPENAI_KEY`: API key para sa authentication
- `AZURE_OPENAI_DEPLOYMENT`: Pangalan ng chat model (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Pangalan ng embedding model

### 3. Patakbuhin ang Mga Aplikasyon nang Lokal

Ang utos na `azd up` ay awtomatikong lumilikha ng `.env` file sa root directory na may lahat ng kinakailangang environment variables.

**Inirerekomenda:** Simulan ang lahat ng web applications:

**Bash:**
```bash
# Mula sa ugat na direktoryo
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Mula sa ugat na direktoryo
cd ../..
.\start-all.ps1
```

O simulan ang isang module lamang:

**Bash:**
```bash
# Halimbawa: Simulan lamang ang module ng pagpapakilala
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Halimbawa: Simulan lamang ang module ng pagpapakilala
cd ../01-introduction
.\start.ps1
```

Parehong mga script ay awtomatikong naglo-load ng environment variables mula sa root `.env` file na nilikha ng `azd up`.

## Konfigurasyon

### Pag-customize ng Model Deployments

Para baguhin ang model deployments, i-edit ang `infra/main.bicep` at baguhin ang `openAiDeployments` parameter:

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

Mga available na modelo at bersyon: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Pagbabago ng Azure Regions

Para mag-deploy sa ibang rehiyon, i-edit ang `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Suriin ang availability ng GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Para i-update ang infrastructure pagkatapos gumawa ng mga pagbabago sa Bicep files:

**Bash:**
```bash
# Muling buuin ang ARM template
az bicep build --file infra/main.bicep

# Tingnan ang mga pagbabago
azd provision --preview

# Ipatupad ang mga pagbabago
azd provision
```

**PowerShell:**
```powershell
# Muling buuin ang ARM template
az bicep build --file infra/main.bicep

# Tingnan ang mga pagbabago
azd provision --preview

# Ipatupad ang mga pagbabago
azd provision
```

## Paglilinis

Para tanggalin lahat ng resources:

**Bash:**
```bash
# Burahin ang lahat ng mga resources
azd down

# Burahin ang lahat kasama na ang kapaligiran
azd down --purge
```

**PowerShell:**
```powershell
# Burahin ang lahat ng mga resources
azd down

# Burahin ang lahat kasama na ang kapaligiran
azd down --purge
```

**Babala**: Ito ay permanenteng magtatanggal ng lahat ng Azure resources.

## Istruktura ng File

## Pag-optimize ng Gastos

### Pag-develop/Pagsusuri
Para sa dev/test environments, maaari mong bawasan ang gastos:
- Gumamit ng Standard tier (S0) para sa Azure OpenAI
- Itakda ang mas mababang kapasidad (10K TPM sa halip na 20K) sa `infra/core/ai/cognitiveservices.bicep`
- Tanggalin ang mga resources kapag hindi ginagamit: `azd down`

### Produksyon
Para sa produksyon:
- Taasan ang OpenAI kapasidad base sa paggamit (50K+ TPM)
- Paganahin ang zone redundancy para sa mas mataas na availability
- Magpatupad ng tamang pagmamanman at mga alerto sa gastos

### Pagtataya ng Gastos
- Azure OpenAI: Bayad kada token (input + output)
- GPT-5: ~$3-5 kada 1M tokens (suriin ang kasalukuyang presyo)
- text-embedding-3-small: ~$0.02 kada 1M tokens

Calculator ng presyo: https://azure.microsoft.com/pricing/calculator/

## Pagmamanman

### Tingnan ang Azure OpenAI Metrics

Pumunta sa Azure Portal → Iyong OpenAI resource → Metrics:
- Token-Based Utilization
- HTTP Request Rate
- Time To Response
- Active Tokens

## Pag-aayos ng Problema

### Isyu: Konflikto sa pangalan ng Azure OpenAI subdomain

**Mensahe ng Error:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Sanhi:**
Ang pangalan ng subdomain na ginawa mula sa iyong subscription/environment ay ginagamit na, maaaring mula sa nakaraang deployment na hindi ganap na natanggal.

**Solusyon:**
1. **Opsyon 1 - Gumamit ng ibang pangalan ng environment:**
   
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

2. **Opsyon 2 - Manwal na deployment sa Azure Portal:**
   - Pumunta sa Azure Portal → Gumawa ng resource → Azure OpenAI
   - Pumili ng natatanging pangalan para sa iyong resource
   - I-deploy ang mga sumusunod na modelo:
     - **GPT-5**
     - **text-embedding-3-small** (para sa mga RAG modules)
   - **Mahalaga:** Tandaan ang iyong mga deployment names - dapat tugma sa `.env` configuration
   - Pagkatapos ng deployment, kunin ang iyong endpoint at API key mula sa "Keys and Endpoint"
   - Gumawa ng `.env` file sa root ng proyekto na may:
     
     **Halimbawa ng `.env` file:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Mga Patnubay sa Pangalan ng Model Deployment:**
- Gumamit ng simple, pare-parehong mga pangalan: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Dapat eksaktong tumugma ang deployment names sa iyong `.env` configuration
- Karaniwang pagkakamali: Gumawa ng modelo gamit ang isang pangalan ngunit tumutukoy ng ibang pangalan sa code

### Isyu: Hindi available ang GPT-5 sa napiling rehiyon

**Solusyon:**
- Pumili ng rehiyon na may access sa GPT-5 (hal. eastus, swedencentral)
- Suriin ang availability: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Isyu: Hindi sapat ang quota para sa deployment

**Solusyon:**
1. Humiling ng pagtaas ng quota sa Azure Portal
2. O gumamit ng mas mababang kapasidad sa `main.bicep` (hal. capacity: 10)

### Isyu: "Resource not found" kapag tumatakbo nang lokal

**Solusyon:**
1. Suriin ang deployment: `azd env get-values`
2. Tiyaking tama ang endpoint at key
3. Siguraduhing umiiral ang resource group sa Azure Portal

### Isyu: Nabigong Authentication

**Solusyon:**
- Tiyaking tama ang `AZURE_OPENAI_API_KEY` na nakaset
- Ang format ng key ay dapat 32-character hexadecimal string
- Kumuha ng bagong key mula sa Azure Portal kung kinakailangan

### Nabigong Deployment

**Isyu**: Nabigo ang `azd provision` dahil sa quota o capacity errors

**Solusyon**: 
1. Subukan ang ibang rehiyon - Tingnan ang seksyong [Pagbabago ng Azure Regions](../../../../01-introduction/infra) para sa kung paano i-configure ang mga rehiyon
2. Suriin kung may Azure OpenAI quota ang iyong subscription:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Hindi Nakakonekta ang Aplikasyon

**Isyu**: Nagpapakita ng connection errors ang Java application

**Solusyon**:
1. Tiyaking na-export ang environment variables:
   
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

2. Suriin kung tama ang format ng endpoint (dapat ay `https://xxx.openai.azure.com`)
3. Tiyaking ang API key ay ang primary o secondary key mula sa Azure Portal

**Isyu**: 401 Unauthorized mula sa Azure OpenAI

**Solusyon**:
1. Kumuha ng bagong API key mula sa Azure Portal → Keys and Endpoint
2. Muling i-export ang `AZURE_OPENAI_API_KEY` environment variable
3. Siguraduhing kumpleto ang model deployments (suriin sa Azure Portal)

### Mga Isyu sa Performance

**Isyu**: Mabagal ang mga tugon

**Solusyon**:
1. Suriin ang paggamit ng OpenAI token at throttling sa Azure Portal metrics
2. Taasan ang TPM capacity kung naabot mo ang mga limitasyon
3. Isaalang-alang ang paggamit ng mas mataas na antas ng reasoning-effort (mababa/gitna/mataas)

## Pag-update ng Infrastructure

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

## Mga Rekomendasyon sa Seguridad

1. **Huwag kailanman i-commit ang API keys** - Gumamit ng environment variables
2. **Gumamit ng .env files nang lokal** - Idagdag ang `.env` sa `.gitignore`
3. **Regular na i-rotate ang mga keys** - Gumawa ng bagong keys sa Azure Portal
4. **Limitahan ang access** - Gumamit ng Azure RBAC para kontrolin kung sino ang makaka-access sa resources
5. **Subaybayan ang paggamit** - Mag-set up ng cost alerts sa Azure Portal

## Karagdagang Mga Resources

- [Azure OpenAI Service Documentation](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 Model Documentation](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI Documentation](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep Documentation](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI Official Integration](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Suporta

Para sa mga isyu:
1. Suriin ang [seksyon ng pag-aayos ng problema](../../../../01-introduction/infra) sa itaas
2. Tingnan ang kalusugan ng Azure OpenAI service sa Azure Portal
3. Magbukas ng isyu sa repository

## Lisensya

Tingnan ang root [LICENSE](../../../../LICENSE) file para sa mga detalye.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Paalala**:
Ang dokumentong ito ay isinalin gamit ang AI translation service na [Co-op Translator](https://github.com/Azure/co-op-translator). Bagamat nagsusumikap kami para sa katumpakan, pakatandaan na ang mga awtomatikong pagsasalin ay maaaring maglaman ng mga pagkakamali o di-tumpak na impormasyon. Ang orihinal na dokumento sa orihinal nitong wika ang dapat ituring na pangunahing sanggunian. Para sa mahahalagang impormasyon, inirerekomenda ang propesyonal na pagsasalin ng tao. Hindi kami mananagot sa anumang hindi pagkakaunawaan o maling interpretasyon na maaaring magmula sa paggamit ng pagsasaling ito.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->