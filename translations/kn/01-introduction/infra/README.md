<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:34:05+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "kn"
}
-->
# LangChain4j ಪ್ರಾರಂಭಿಸಲು Azure ಮೂಲಸೌಕರ್ಯ

## ವಿಷಯಗಳ ಪಟ್ಟಿಯು

- [ಪೂರ್ವಾಪೇಕ್ಷೆಗಳು](../../../../01-introduction/infra)
- [ವಾಸ್ತುಶಿಲ್ಪ](../../../../01-introduction/infra)
- [ರಚಿಸಲಾದ ಸಂಪನ್ಮೂಲಗಳು](../../../../01-introduction/infra)
- [ತ್ವರಿತ ಪ್ರಾರಂಭ](../../../../01-introduction/infra)
- [ಕಾನ್ಫಿಗರೇಶನ್](../../../../01-introduction/infra)
- [ನಿರ್ವಹಣಾ ಆಜ್ಞೆಗಳು](../../../../01-introduction/infra)
- [ಖರ್ಚು ಆಪ್ಟಿಮೈಜೆಷನ್](../../../../01-introduction/infra)
- [ನಿಗಾ](../../../../01-introduction/infra)
- [ಸಮಸ್ಯೆ ಪರಿಹಾರ](../../../../01-introduction/infra)
- [ಮೂಲಸೌಕರ್ಯ ನವೀಕರಣ](../../../../01-introduction/infra)
- [ಶುದ್ಧೀಕರಣ](../../../../01-introduction/infra)
- [ಫೈಲ್ ರಚನೆ](../../../../01-introduction/infra)
- [ಭದ್ರತಾ ಶಿಫಾರಸುಗಳು](../../../../01-introduction/infra)
- [ಹೆಚ್ಚಿನ ಸಂಪನ್ಮೂಲಗಳು](../../../../01-introduction/infra)

ಈ ಡೈರೆಕ್ಟರಿ Bicep ಮತ್ತು Azure Developer CLI (azd) ಬಳಸಿ Azure OpenAI ಸಂಪನ್ಮೂಲಗಳನ್ನು ನಿಯೋಜಿಸಲು Azure ಮೂಲಸೌಕರ್ಯವನ್ನು ಕೋಡ್ (IaC) ರೂಪದಲ್ಲಿ ಹೊಂದಿದೆ.

## ಪೂರ್ವಾಪೇಕ್ಷೆಗಳು

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (ಆವೃತ್ತಿ 2.50.0 ಅಥವಾ ನಂತರದ)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (ಆವೃತ್ತಿ 1.5.0 ಅಥವಾ ನಂತರದ)
- ಸಂಪನ್ಮೂಲಗಳನ್ನು ರಚಿಸಲು ಅನುಮತಿಗಳೊಂದಿಗೆ Azure ಸಬ್ಸ್ಕ್ರಿಪ್ಷನ್

## ವಾಸ್ತುಶಿಲ್ಪ

**ಸರಳೀಕೃತ ಸ್ಥಳೀಯ ಅಭಿವೃದ್ಧಿ ಸೆಟ್‌ಅಪ್** - ಕೇವಲ Azure OpenAI ನಿಯೋಜಿಸಿ, ಎಲ್ಲಾ ಅಪ್ಲಿಕೇಶನ್‌ಗಳನ್ನು ಸ್ಥಳೀಯವಾಗಿ ಚಾಲನೆ ಮಾಡಿ.

ಮೂಲಸೌಕರ್ಯ ಕೆಳಗಿನ Azure ಸಂಪನ್ಮೂಲಗಳನ್ನು ನಿಯೋಜಿಸುತ್ತದೆ:

### AI ಸೇವೆಗಳು
- **Azure OpenAI**: ಎರಡು ಮಾದರಿ ನಿಯೋಜನೆಗಳೊಂದಿಗೆ ಕಾಗ್ನಿಟಿವ್ ಸೇವೆಗಳು:
  - **gpt-5**: ಚಾಟ್ ಪೂರ್ಣಗೊಳಿಸುವ ಮಾದರಿ (20K TPM ಸಾಮರ್ಥ್ಯ)
  - **text-embedding-3-small**: RAG ಗಾಗಿ ಎम्बೆಡ್ಡಿಂಗ್ ಮಾದರಿ (20K TPM ಸಾಮರ್ಥ್ಯ)

### ಸ್ಥಳೀಯ ಅಭಿವೃದ್ಧಿ
ಎಲ್ಲಾ Spring Boot ಅಪ್ಲಿಕೇಶನ್‌ಗಳು ನಿಮ್ಮ ಯಂತ್ರದಲ್ಲಿ ಸ್ಥಳೀಯವಾಗಿ ಚಾಲನೆ ಮಾಡುತ್ತವೆ:
- 01-introduction (ಪೋರ್ಟ್ 8080)
- 02-prompt-engineering (ಪೋರ್ಟ್ 8083)
- 03-rag (ಪೋರ್ಟ್ 8081)
- 04-tools (ಪೋರ್ಟ್ 8084)

## ರಚಿಸಲಾದ ಸಂಪನ್ಮೂಲಗಳು

| ಸಂಪನ್ಮೂಲ ಪ್ರಕಾರ | ಸಂಪನ್ಮೂಲ ಹೆಸರು ಮಾದರಿ | ಉದ್ದೇಶ |
|--------------|----------------------|---------|
| ಸಂಪನ್ಮೂಲ ಗುಂಪು | `rg-{environmentName}` | ಎಲ್ಲಾ ಸಂಪನ್ಮೂಲಗಳನ್ನು ಒಳಗೊಂಡಿದೆ |
| Azure OpenAI | `aoai-{resourceToken}` | AI ಮಾದರಿ ಹೋಸ್ಟಿಂಗ್ |

> **ಗಮನಿಸಿ:** `{resourceToken}` ಸಬ್ಸ್ಕ್ರಿಪ್ಷನ್ ID, ಪರಿಸರ ಹೆಸರು ಮತ್ತು ಸ್ಥಳದಿಂದ ಉತ್ಪನ್ನವಾದ ವಿಶಿಷ್ಟ ಸ್ಟ್ರಿಂಗ್

## ತ್ವರಿತ ಪ್ರಾರಂಭ

### 1. Azure OpenAI ನಿಯೋಜಿಸಿ

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

ಪ್ರಾಂಪ್ಟ್ ಆಗುವಾಗ:
- ನಿಮ್ಮ Azure ಸಬ್ಸ್ಕ್ರಿಪ್ಷನ್ ಆಯ್ಕೆಮಾಡಿ
- ಸ್ಥಳ ಆಯ್ಕೆಮಾಡಿ (ಶಿಫಾರಸು: `eastus2` ಅಥವಾ `swedencentral` GPT-5 ಲಭ್ಯತೆಗಾಗಿ)
- ಪರಿಸರ ಹೆಸರನ್ನು ದೃಢೀಕರಿಸಿ (ಡೀಫಾಲ್ಟ್: `langchain4j-dev`)

ಇದು ರಚಿಸುತ್ತದೆ:
- GPT-5 ಮತ್ತು text-embedding-3-small ಹೊಂದಿರುವ Azure OpenAI ಸಂಪನ್ಮೂಲ
- ಸಂಪರ್ಕ ವಿವರಗಳನ್ನು ಔಟ್‌ಪುಟ್ ಮಾಡುತ್ತದೆ

### 2. ಸಂಪರ್ಕ ವಿವರಗಳನ್ನು ಪಡೆಯಿರಿ

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

ಇದು ತೋರಿಸುತ್ತದೆ:
- `AZURE_OPENAI_ENDPOINT`: ನಿಮ್ಮ Azure OpenAI ಎಂಡ್‌ಪಾಯಿಂಟ್ URL
- `AZURE_OPENAI_KEY`: ಪ್ರಾಮಾಣೀಕರಣಕ್ಕಾಗಿ API ಕೀ
- `AZURE_OPENAI_DEPLOYMENT`: ಚಾಟ್ ಮಾದರಿ ಹೆಸರು (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: ಎम्बೆಡ್ಡಿಂಗ್ ಮಾದರಿ ಹೆಸರು

### 3. ಅಪ್ಲಿಕೇಶನ್‌ಗಳನ್ನು ಸ್ಥಳೀಯವಾಗಿ ಚಾಲನೆ ಮಾಡಿ

`azd up` ಆಜ್ಞೆ ಸ್ವಯಂಚಾಲಿತವಾಗಿ ಮೂಲ ಡೈರೆಕ್ಟರಿಯಲ್ಲಿ ಎಲ್ಲಾ ಅಗತ್ಯ ಪರಿಸರ ಚರಗಳನ್ನು ಹೊಂದಿರುವ `.env` ಫೈಲ್ ರಚಿಸುತ್ತದೆ.

**ಶಿಫಾರಸು:** ಎಲ್ಲಾ ವೆಬ್ ಅಪ್ಲಿಕೇಶನ್‌ಗಳನ್ನು ಪ್ರಾರಂಭಿಸಿ:

**Bash:**
```bash
# ರೂಟ್ ಡೈರೆಕ್ಟರಿಯಿಂದ
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# ರೂಟ್ ಡೈರೆಕ್ಟರಿಯಿಂದ
cd ../..
.\start-all.ps1
```

ಅಥವಾ ಒಂದು ಮಾತ್ರ ಮಾಯಾಜೋಡಣೆಯನ್ನು ಪ್ರಾರಂಭಿಸಿ:

**Bash:**
```bash
# ಉದಾಹರಣೆ: ಕೇವಲ ಪರಿಚಯ ಮಾಯಾಜಾಲವನ್ನು ಪ್ರಾರಂಭಿಸಿ
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# ಉದಾಹರಣೆ: ಕೇವಲ ಪರಿಚಯ ಮಾಯಾಜಾಲವನ್ನು ಪ್ರಾರಂಭಿಸಿ
cd ../01-introduction
.\start.ps1
```

ಎರಡೂ ಸ್ಕ್ರಿಪ್ಟ್‌ಗಳು `azd up` ಮೂಲಕ ರಚಿಸಲಾದ ಮೂಲ `.env` ಫೈಲ್‌ನಿಂದ ಪರಿಸರ ಚರಗಳನ್ನು ಸ್ವಯಂಚಾಲಿತವಾಗಿ ಲೋಡ್ ಮಾಡುತ್ತವೆ.

## ಕಾನ್ಫಿಗರೇಶನ್

### ಮಾದರಿ ನಿಯೋಜನೆಗಳನ್ನು ಕಸ್ಟಮೈಸ್ ಮಾಡುವುದು

ಮಾದರಿ ನಿಯೋಜನೆಗಳನ್ನು ಬದಲಾಯಿಸಲು, `infra/main.bicep` ಸಂಪಾದಿಸಿ ಮತ್ತು `openAiDeployments` ಪ್ಯಾರಾಮೀಟರ್ ಅನ್ನು ಬದಲಾಯಿಸಿ:

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

ಲಭ್ಯವಿರುವ ಮಾದರಿಗಳು ಮತ್ತು ಆವೃತ್ತಿಗಳು: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure ಪ್ರದೇಶಗಳನ್ನು ಬದಲಾಯಿಸುವುದು

ಬೇರೊಂದು ಪ್ರದೇಶದಲ್ಲಿ ನಿಯೋಜಿಸಲು, `infra/main.bicep` ಸಂಪಾದಿಸಿ:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 ಲಭ್ಯತೆ ಪರಿಶೀಲಿಸಿ: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep ಫೈಲ್‌ಗಳಲ್ಲಿ ಬದಲಾವಣೆ ಮಾಡಿದ ನಂತರ ಮೂಲಸೌಕರ್ಯವನ್ನು ನವೀಕರಿಸಲು:

**Bash:**
```bash
# ARM ಟೆಂಪ್ಲೇಟನ್ನು ಮರುನಿರ್ಮಿಸಿ
az bicep build --file infra/main.bicep

# ಬದಲಾವಣೆಗಳನ್ನು ಪೂರ್ವದೃಶ್ಯ ಮಾಡಿ
azd provision --preview

# ಬದಲಾವಣೆಗಳನ್ನು ಅನ್ವಯಿಸಿ
azd provision
```

**PowerShell:**
```powershell
# ARM ಟೆಂಪ್ಲೇಟನ್ನು ಮರುನಿರ್ಮಿಸಿ
az bicep build --file infra/main.bicep

# ಬದಲಾವಣೆಗಳನ್ನು ಪೂರ್ವದೃಶ್ಯ ಮಾಡಿ
azd provision --preview

# ಬದಲಾವಣೆಗಳನ್ನು ಅನ್ವಯಿಸಿ
azd provision
```

## ಶುದ್ಧೀಕರಣ

ಎಲ್ಲಾ ಸಂಪನ್ಮೂಲಗಳನ್ನು ಅಳಿಸಲು:

**Bash:**
```bash
# ಎಲ್ಲಾ ಸಂಪನ್ಮೂಲಗಳನ್ನು ಅಳಿಸಿ
azd down

# ಪರಿಸರವನ್ನು ಸೇರಿಸಿ ಎಲ್ಲವನ್ನೂ ಅಳಿಸಿ
azd down --purge
```

**PowerShell:**
```powershell
# ಎಲ್ಲಾ ಸಂಪನ್ಮೂಲಗಳನ್ನು ಅಳಿಸಿ
azd down

# ಪರಿಸರವನ್ನು ಸೇರಿಸಿ ಎಲ್ಲವನ್ನೂ ಅಳಿಸಿ
azd down --purge
```

**ಎಚ್ಚರಿಕೆ**: ಇದು ಎಲ್ಲಾ Azure ಸಂಪನ್ಮೂಲಗಳನ್ನು ಶಾಶ್ವತವಾಗಿ ಅಳಿಸುತ್ತದೆ.

## ಫೈಲ್ ರಚನೆ

## ಖರ್ಚು ಆಪ್ಟಿಮೈಜೆಷನ್

### ಅಭಿವೃದ್ಧಿ/ಪರೀಕ್ಷೆ
ಡಿವ್/ಟೆಸ್ಟ್ ಪರಿಸರಗಳಿಗೆ, ನೀವು ಖರ್ಚು ಕಡಿಮೆ ಮಾಡಬಹುದು:
- Azure OpenAI ಗೆ ಸ್ಟ್ಯಾಂಡರ್ಡ್ ಟಿಯರ್ (S0) ಬಳಸಿ
- `infra/core/ai/cognitiveservices.bicep` ನಲ್ಲಿ ಕಡಿಮೆ ಸಾಮರ್ಥ್ಯ (10K TPM ಬದಲಿಗೆ 20K TPM) ಹೊಂದಿಸಿ
- ಬಳಕೆ ಇಲ್ಲದಾಗ ಸಂಪನ್ಮೂಲಗಳನ್ನು ಅಳಿಸಿ: `azd down`

### ಉತ್ಪಾದನೆ
ಉತ್ಪಾದನೆಗಾಗಿ:
- ಬಳಕೆಯ ಆಧಾರದ ಮೇಲೆ OpenAI ಸಾಮರ್ಥ್ಯವನ್ನು ಹೆಚ್ಚಿಸಿ (50K+ TPM)
- ಹೆಚ್ಚಿನ ಲಭ್ಯತೆಗಾಗಿ ವಲಯ ಪುನರಾವರ್ತನೆ ಸಕ್ರಿಯಗೊಳಿಸಿ
- ಸರಿಯಾದ ನಿಗಾ ಮತ್ತು ಖರ್ಚು ಎಚ್ಚರಿಕೆಗಳನ್ನು ಜಾರಿಗೊಳಿಸಿ

### ಖರ್ಚು ಅಂದಾಜು
- Azure OpenAI: ಟೋಕನ್ ಪ್ರತಿ ಪಾವತಿ (ಇನ್‌ಪುಟ್ + ಔಟ್‌ಪುಟ್)
- GPT-5: ಪ್ರತಿ 1M ಟೋಕನ್ಗೆ ~$3-5 (ಪ್ರಸ್ತುತ ಬೆಲೆ ಪರಿಶೀಲಿಸಿ)
- text-embedding-3-small: ಪ್ರತಿ 1M ಟೋಕನ್ಗೆ ~$0.02

ಬೆಲೆ ಗಣಕ: https://azure.microsoft.com/pricing/calculator/

## ನಿಗಾ

### Azure OpenAI ಮೆಟ್ರಿಕ್ಸ್ ವೀಕ್ಷಣೆ

Azure ಪೋರ್ಟಲ್ → ನಿಮ್ಮ OpenAI ಸಂಪನ್ಮೂಲ → ಮೆಟ್ರಿಕ್ಸ್ ಗೆ ಹೋಗಿ:
- ಟೋಕನ್ ಆಧಾರಿತ ಬಳಕೆ
- HTTP ವಿನಂತಿ ದರ
- ಪ್ರತಿಕ್ರಿಯೆಗೆ ಸಮಯ
- ಸಕ್ರಿಯ ಟೋಕನ್ಗಳು

## ಸಮಸ್ಯೆ ಪರಿಹಾರ

### ಸಮಸ್ಯೆ: Azure OpenAI ಉಪಡೊಮೇನ್ ಹೆಸರು ಸಂಘರ್ಷ

**ದೋಷ ಸಂದೇಶ:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**ಕಾರಣ:**
ನಿಮ್ಮ ಸಬ್ಸ್ಕ್ರಿಪ್ಷನ್/ಪರಿಸರದಿಂದ ಉತ್ಪನ್ನವಾದ ಉಪಡೊಮೇನ್ ಹೆಸರು ಈಗಾಗಲೇ ಬಳಕೆಯಲ್ಲಿದೆ, ಬಹುಶಃ ಪೂರ್ವದ ನಿಯೋಜನೆಯಿಂದ ಸಂಪೂರ್ಣವಾಗಿ ಅಳಿಸಲಾಗಿಲ್ಲ.

**ಪರಿಹಾರ:**
1. **ಆಯ್ಕೆ 1 - ಬೇರೆ ಪರಿಸರ ಹೆಸರನ್ನು ಬಳಸಿ:**
   
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

2. **ಆಯ್ಕೆ 2 - Azure ಪೋರ್ಟಲ್ ಮೂಲಕ ಕೈಯಿಂದ ನಿಯೋಜನೆ:**
   - Azure ಪೋರ್ಟಲ್ → ಸಂಪನ್ಮೂಲ ರಚಿಸಿ → Azure OpenAI ಗೆ ಹೋಗಿ
   - ನಿಮ್ಮ ಸಂಪನ್ಮೂಲಕ್ಕೆ ವಿಶಿಷ್ಟ ಹೆಸರು ಆಯ್ಕೆಮಾಡಿ
   - ಕೆಳಗಿನ ಮಾದರಿಗಳನ್ನು ನಿಯೋಜಿಸಿ:
     - **GPT-5**
     - **text-embedding-3-small** (RAG ಮಾಯಾಜೋಡಣೆಗಳಿಗೆ)
   - **ಮುಖ್ಯ:** ನಿಮ್ಮ ನಿಯೋಜನೆ ಹೆಸರುಗಳನ್ನು ಗಮನಿಸಿ - ಅವು `.env` ಕಾನ್ಫಿಗರೇಶನ್‌ಗೆ ಹೊಂದಿಕೊಳ್ಳಬೇಕು
   - ನಿಯೋಜನೆಯ ನಂತರ, "ಕೀಗಳು ಮತ್ತು ಎಂಡ್‌ಪಾಯಿಂಟ್" ನಿಂದ ನಿಮ್ಮ ಎಂಡ್‌ಪಾಯಿಂಟ್ ಮತ್ತು API ಕೀ ಪಡೆಯಿರಿ
   - ಪ್ರಾಜೆಕ್ಟ್ ಮೂಲದಲ್ಲಿ `.env` ಫೈಲ್ ರಚಿಸಿ:
     
     **ಉದಾಹರಣೆಯ `.env` ಫೈಲ್:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**ಮಾದರಿ ನಿಯೋಜನೆ ಹೆಸರು ಮಾರ್ಗಸೂಚಿಗಳು:**
- ಸರಳ, ಸತತ ಹೆಸರುಗಳನ್ನು ಬಳಸಿ: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- ನಿಯೋಜನೆ ಹೆಸರುಗಳು `.env` ನಲ್ಲಿ ನೀವು ಕಾನ್ಫಿಗರ್ ಮಾಡಿದುದಕ್ಕೆ ಸರಿಹೊಂದಬೇಕು
- ಸಾಮಾನ್ಯ ತಪ್ಪು: ಒಂದೇ ಹೆಸರು ಬಳಸಿ ಮಾದರಿ ರಚಿಸಿ ಆದರೆ ಕೋಡ್‌ನಲ್ಲಿ ಬೇರೆ ಹೆಸರು ಉಲ್ಲೇಖಿಸುವುದು

### ಸಮಸ್ಯೆ: ಆಯ್ಕೆಮಾಡಿದ ಪ್ರದೇಶದಲ್ಲಿ GPT-5 ಲಭ್ಯವಿಲ್ಲ

**ಪರಿಹಾರ:**
- GPT-5 ಲಭ್ಯವಿರುವ ಪ್ರದೇಶವನ್ನು ಆಯ್ಕೆಮಾಡಿ (ಉದಾ: eastus, swedencentral)
- ಲಭ್ಯತೆ ಪರಿಶೀಲಿಸಿ: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### ಸಮಸ್ಯೆ: ನಿಯೋಜನೆಗೆ ಸಾಕಷ್ಟು ಕೊಟಾ ಇಲ್ಲ

**ಪರಿಹಾರ:**
1. Azure ಪೋರ್ಟಲ್‌ನಲ್ಲಿ ಕೊಟಾ ಹೆಚ್ಚಿಸುವಂತೆ ವಿನಂತಿಸಿ
2. ಅಥವಾ `main.bicep` ನಲ್ಲಿ ಕಡಿಮೆ ಸಾಮರ್ಥ್ಯ ಬಳಸಿ (ಉದಾ: capacity: 10)

### ಸಮಸ್ಯೆ: ಸ್ಥಳೀಯವಾಗಿ ಚಾಲನೆ ಮಾಡಿದಾಗ "ಸಂಪನ್ಮೂಲ ಕಂಡುಬಂದಿಲ್ಲ"

**ಪರಿಹಾರ:**
1. ನಿಯೋಜನೆ ಪರಿಶೀಲಿಸಿ: `azd env get-values`
2. ಎಂಡ್‌ಪಾಯಿಂಟ್ ಮತ್ತು ಕೀ ಸರಿಯಾಗಿದೆಯೇ ಎಂದು ಪರಿಶೀಲಿಸಿ
3. Azure ಪೋರ್ಟಲ್‌ನಲ್ಲಿ ಸಂಪನ್ಮೂಲ ಗುಂಪು ಇದ್ದೇ ಇದೆಯೇ ಎಂದು ಖಚಿತಪಡಿಸಿಕೊಳ್ಳಿ

### ಸಮಸ್ಯೆ: ಪ್ರಾಮಾಣೀಕರಣ ವಿಫಲವಾಗಿದೆ

**ಪರಿಹಾರ:**
- `AZURE_OPENAI_API_KEY` ಸರಿಯಾಗಿ ಸೆಟ್ ಆಗಿದೆಯೇ ಎಂದು ಪರಿಶೀಲಿಸಿ
- ಕೀ ಫಾರ್ಮ್ಯಾಟ್ 32 ಅಕ್ಷರದ ಹೆಕ್ಸಾಡೆಸಿಮಲ್ ಸ್ಟ್ರಿಂಗ್ ಆಗಿರಬೇಕು
- ಅಗತ್ಯವಿದ್ದರೆ Azure ಪೋರ್ಟಲ್‌ನಿಂದ ಹೊಸ ಕೀ ಪಡೆಯಿರಿ

### ನಿಯೋಜನೆ ವಿಫಲವಾಗಿದೆ

**ಸಮಸ್ಯೆ**: `azd provision` ಕೊಟಾ ಅಥವಾ ಸಾಮರ್ಥ್ಯ ದೋಷಗಳೊಂದಿಗೆ ವಿಫಲವಾಗಿದೆ

**ಪರಿಹಾರ**: 
1. ಬೇರೆ ಪ್ರದೇಶ ಪ್ರಯತ್ನಿಸಿ - [Azure ಪ್ರದೇಶ ಬದಲಾವಣೆ](../../../../01-introduction/infra) ವಿಭಾಗ ನೋಡಿ
2. ನಿಮ್ಮ ಸಬ್ಸ್ಕ್ರಿಪ್ಷನ್‌ಗೆ Azure OpenAI ಕೊಟಾ ಇದೆ ಎಂದು ಪರಿಶೀಲಿಸಿ:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### ಅಪ್ಲಿಕೇಶನ್ ಸಂಪರ್ಕ ಹೊಂದುತ್ತಿಲ್ಲ

**ಸಮಸ್ಯೆ**: ಜಾವಾ ಅಪ್ಲಿಕೇಶನ್ ಸಂಪರ್ಕ ದೋಷಗಳನ್ನು ತೋರಿಸುತ್ತದೆ

**ಪರಿಹಾರ**:
1. ಪರಿಸರ ಚರಗಳು ರಫ್ತುಗೊಂಡಿವೆ ಎಂದು ಪರಿಶೀಲಿಸಿ:
   
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

2. ಎಂಡ್‌ಪಾಯಿಂಟ್ ಫಾರ್ಮ್ಯಾಟ್ ಸರಿಯಾಗಿದೆ ಎಂದು ಖಚಿತಪಡಿಸಿಕೊಳ್ಳಿ (`https://xxx.openai.azure.com` ಆಗಿರಬೇಕು)
3. API ಕೀ Azure ಪೋರ್ಟಲ್‌ನಿಂದ ಪ್ರಾಥಮಿಕ ಅಥವಾ ದ್ವಿತೀಯ ಕೀ ಆಗಿರಬೇಕು ಎಂದು ಪರಿಶೀಲಿಸಿ

**ಸಮಸ್ಯೆ**: Azure OpenAI ನಿಂದ 401 ಅನಧಿಕೃತ

**ಪರಿಹಾರ**:
1. Azure ಪೋರ್ಟಲ್ → ಕೀಗಳು ಮತ್ತು ಎಂಡ್‌ಪಾಯಿಂಟ್ ನಿಂದ ಹೊಸ API ಕೀ ಪಡೆಯಿರಿ
2. `AZURE_OPENAI_API_KEY` ಪರಿಸರ ಚರವನ್ನು ಮರುರಫ್ತು ಮಾಡಿ
3. ಮಾದರಿ ನಿಯೋಜನೆಗಳು ಪೂರ್ಣಗೊಂಡಿವೆ ಎಂದು ಖಚಿತಪಡಿಸಿಕೊಳ್ಳಿ (Azure ಪೋರ್ಟಲ್ ಪರಿಶೀಲಿಸಿ)

### ಕಾರ್ಯಕ್ಷಮತೆ ಸಮಸ್ಯೆಗಳು

**ಸಮಸ್ಯೆ**: ಪ್ರತಿಕ್ರಿಯೆ ಸಮಯ ನಿಧಾನವಾಗಿದೆ

**ಪರಿಹಾರ**:
1. Azure ಪೋರ್ಟಲ್ ಮೆಟ್ರಿಕ್ಸ್‌ನಲ್ಲಿ OpenAI ಟೋಕನ್ ಬಳಕೆ ಮತ್ತು ಥ್ರಾಟ್ಲಿಂಗ್ ಪರಿಶೀಲಿಸಿ
2. ನೀವು ಮಿತಿಗಳನ್ನು ತಲುಪುತ್ತಿದ್ದರೆ TPM ಸಾಮರ್ಥ್ಯವನ್ನು ಹೆಚ್ಚಿಸಿ
3. ಹೆಚ್ಚಿನ ತರ್ಕ-ಪ್ರಯತ್ನ ಮಟ್ಟ (ಕಡಿಮೆ/ಮಧ್ಯಮ/ಹೆಚ್ಚು) ಬಳಕೆಯನ್ನು ಪರಿಗಣಿಸಿ

## ಮೂಲಸೌಕರ್ಯ ನವೀಕರಣ

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

## ಭದ್ರತಾ ಶಿಫಾರಸುಗಳು

1. **ಯಾವುದೇ API ಕೀಗಳನ್ನು ಕಮಿಟ್ ಮಾಡಬೇಡಿ** - ಪರಿಸರ ಚರಗಳನ್ನು ಬಳಸಿ
2. **ಸ್ಥಳೀಯವಾಗಿ .env ಫೈಲ್‌ಗಳನ್ನು ಬಳಸಿ** - `.env` ಅನ್ನು `.gitignore` ಗೆ ಸೇರಿಸಿ
3. **ಕೀಗಳನ್ನು ನಿಯಮಿತವಾಗಿ ರೋಟೇಟ್ ಮಾಡಿ** - Azure ಪೋರ್ಟಲ್‌ನಲ್ಲಿ ಹೊಸ ಕೀಗಳನ್ನು ರಚಿಸಿ
4. **ಪ್ರವೇಶವನ್ನು ಮಿತಿಗೊಳಿಸಿ** - Azure RBAC ಬಳಸಿ ಯಾರು ಸಂಪನ್ಮೂಲಗಳಿಗೆ ಪ್ರವೇಶ ಹೊಂದಬಹುದು ಎಂದು ನಿಯಂತ್ರಿಸಿ
5. **ಬಳಕೆಯನ್ನು ನಿಗಾ ವಹಿಸಿ** - Azure ಪೋರ್ಟಲ್‌ನಲ್ಲಿ ಖರ್ಚು ಎಚ್ಚರಿಕೆಗಳನ್ನು ಹೊಂದಿಸಿ

## ಹೆಚ್ಚುವರಿ ಸಂಪನ್ಮೂಲಗಳು

- [Azure OpenAI ಸೇವೆ ಡಾಕ್ಯುಮೆಂಟೇಶನ್](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 ಮಾದರಿ ಡಾಕ್ಯುಮೆಂಟೇಶನ್](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI ಡಾಕ್ಯುಮೆಂಟೇಶನ್](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep ಡಾಕ್ಯುಮೆಂಟೇಶನ್](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI ಅಧಿಕೃತ ಇಂಟಿಗ್ರೇಶನ್](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## ಬೆಂಬಲ

ಸಮಸ್ಯೆಗಳಿಗಾಗಿ:
1. ಮೇಲಿನ [ಸಮಸ್ಯೆ ಪರಿಹಾರ ವಿಭಾಗ](../../../../01-introduction/infra) ಪರಿಶೀಲಿಸಿ
2. Azure ಪೋರ್ಟಲ್‌ನಲ್ಲಿ Azure OpenAI ಸೇವೆಯ ಆರೋಗ್ಯ ಪರಿಶೀಲಿಸಿ
3. ರೆಪೊದಲ್ಲಿ ಸಮಸ್ಯೆ ತೆರೆಯಿರಿ

## ಪರವಾನಗಿ

ವಿವರಗಳಿಗೆ ಮೂಲ [LICENSE](../../../../LICENSE) ಫೈಲ್ ನೋಡಿ.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**ಅಸ್ವೀಕರಣ**:  
ಈ ದಸ್ತಾವೇಜು AI ಅನುವಾದ ಸೇವೆ [Co-op Translator](https://github.com/Azure/co-op-translator) ಬಳಸಿ ಅನುವಾದಿಸಲಾಗಿದೆ. ನಾವು ನಿಖರತೆಯಿಗಾಗಿ ಪ್ರಯತ್ನಿಸುತ್ತಿದ್ದರೂ, ಸ್ವಯಂಚಾಲಿತ ಅನುವಾದಗಳಲ್ಲಿ ತಪ್ಪುಗಳು ಅಥವಾ ಅಸತ್ಯತೆಗಳು ಇರಬಹುದು ಎಂದು ದಯವಿಟ್ಟು ಗಮನಿಸಿ. ಮೂಲ ಭಾಷೆಯಲ್ಲಿರುವ ಮೂಲ ದಸ್ತಾವೇಜನ್ನು ಅಧಿಕೃತ ಮೂಲವೆಂದು ಪರಿಗಣಿಸಬೇಕು. ಪ್ರಮುಖ ಮಾಹಿತಿಗಾಗಿ, ವೃತ್ತಿಪರ ಮಾನವ ಅನುವಾದವನ್ನು ಶಿಫಾರಸು ಮಾಡಲಾಗುತ್ತದೆ. ಈ ಅನುವಾದ ಬಳಕೆಯಿಂದ ಉಂಟಾಗುವ ಯಾವುದೇ ತಪ್ಪು ಅರ್ಥಮಾಡಿಕೊಳ್ಳುವಿಕೆ ಅಥವಾ ತಪ್ಪು ವಿವರಣೆಗಳಿಗೆ ನಾವು ಹೊಣೆಗಾರರಾಗುವುದಿಲ್ಲ.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->