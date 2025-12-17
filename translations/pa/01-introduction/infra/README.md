<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T21:54:14+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "pa"
}
-->
# LangChain4j ਲਈ Azure ਇੰਫਰਾਸਟਰੱਕਚਰ ਸ਼ੁਰੂਆਤ

## ਸਮੱਗਰੀ ਸੂਚੀ

- [ਪੂਰਵ-ਸ਼ਰਤਾਂ](../../../../01-introduction/infra)
- [ਵਿਧਾਨ-ਰਚਨਾ](../../../../01-introduction/infra)
- [ਬਣਾਏ ਗਏ ਸਰੋਤ](../../../../01-introduction/infra)
- [ਤੁਰੰਤ ਸ਼ੁਰੂਆਤ](../../../../01-introduction/infra)
- [ਸੰਰਚਨਾ](../../../../01-introduction/infra)
- [ਪ੍ਰਬੰਧਨ ਕਮਾਂਡਾਂ](../../../../01-introduction/infra)
- [ਲਾਗਤ ਅਨੁਕੂਲਤਾ](../../../../01-introduction/infra)
- [ਨਿਗਰਾਨੀ](../../../../01-introduction/infra)
- [ਸਮੱਸਿਆ ਨਿਵਾਰਣ](../../../../01-introduction/infra)
- [ਇੰਫਰਾਸਟਰੱਕਚਰ ਅੱਪਡੇਟ ਕਰਨਾ](../../../../01-introduction/infra)
- [ਸਾਫ਼-ਸਫਾਈ](../../../../01-introduction/infra)
- [ਫਾਈਲ ਸੰਰਚਨਾ](../../../../01-introduction/infra)
- [ਸੁਰੱਖਿਆ ਸਿਫਾਰਸ਼ਾਂ](../../../../01-introduction/infra)
- [ਵਾਧੂ ਸਰੋਤ](../../../../01-introduction/infra)

ਇਹ ਡਾਇਰੈਕਟਰੀ Bicep ਅਤੇ Azure Developer CLI (azd) ਦੀ ਵਰਤੋਂ ਕਰਕੇ Azure OpenAI ਸਰੋਤਾਂ ਨੂੰ ਤਾਇਨਾਤ ਕਰਨ ਲਈ ਕੋਡ (IaC) ਵਜੋਂ Azure ਇੰਫਰਾਸਟਰੱਕਚਰ ਰੱਖਦੀ ਹੈ।

## ਪੂਰਵ-ਸ਼ਰਤਾਂ

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (ਸੰਸਕਰਣ 2.50.0 ਜਾਂ ਇਸ ਤੋਂ ਬਾਅਦ)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (ਸੰਸਕਰਣ 1.5.0 ਜਾਂ ਇਸ ਤੋਂ ਬਾਅਦ)
- ਸਰੋਤ ਬਣਾਉਣ ਲਈ ਅਧਿਕਾਰਾਂ ਵਾਲੀ Azure ਸਬਸਕ੍ਰਿਪਸ਼ਨ

## ਵਿਧਾਨ-ਰਚਨਾ

**ਸਰਲ ਸਥਾਨਕ ਵਿਕਾਸ ਸੈਟਅੱਪ** - ਸਿਰਫ Azure OpenAI ਤਾਇਨਾਤ ਕਰੋ, ਸਾਰੇ ਐਪਸ ਸਥਾਨਕ ਤੌਰ 'ਤੇ ਚਲਾਓ।

ਇੰਫਰਾਸਟਰੱਕਚਰ ਹੇਠ ਲਿਖੇ Azure ਸਰੋਤ ਤਾਇਨਾਤ ਕਰਦਾ ਹੈ:

### AI ਸੇਵਾਵਾਂ
- **Azure OpenAI**: ਦੋ ਮਾਡਲ ਤਾਇਨਾਤੀਆਂ ਨਾਲ ਕੋਗਨਿਟਿਵ ਸੇਵਾਵਾਂ:
  - **gpt-5**: ਚੈਟ ਪੂਰਨਤਾ ਮਾਡਲ (20K TPM ਸਮਰੱਥਾ)
  - **text-embedding-3-small**: RAG ਲਈ ਐਂਬੈਡਿੰਗ ਮਾਡਲ (20K TPM ਸਮਰੱਥਾ)

### ਸਥਾਨਕ ਵਿਕਾਸ
ਸਾਰੇ Spring Boot ਐਪਲੀਕੇਸ਼ਨ ਤੁਹਾਡੇ ਮਸ਼ੀਨ 'ਤੇ ਸਥਾਨਕ ਤੌਰ 'ਤੇ ਚਲਦੇ ਹਨ:
- 01-introduction (ਪੋਰਟ 8080)
- 02-prompt-engineering (ਪੋਰਟ 8083)
- 03-rag (ਪੋਰਟ 8081)
- 04-tools (ਪੋਰਟ 8084)

## ਬਣਾਏ ਗਏ ਸਰੋਤ

| ਸਰੋਤ ਕਿਸਮ | ਸਰੋਤ ਨਾਮ ਪੈਟਰਨ | ਉਦੇਸ਼ |
|--------------|----------------------|---------|
| ਸਰੋਤ ਗਰੁੱਪ | `rg-{environmentName}` | ਸਾਰੇ ਸਰੋਤਾਂ ਨੂੰ ਰੱਖਦਾ ਹੈ |
| Azure OpenAI | `aoai-{resourceToken}` | AI ਮਾਡਲ ਹੋਸਟਿੰਗ |

> **ਨੋਟ:** `{resourceToken}` ਇੱਕ ਵਿਲੱਖਣ ਸਤਰ ਹੈ ਜੋ ਸਬਸਕ੍ਰਿਪਸ਼ਨ ID, ਵਾਤਾਵਰਣ ਨਾਮ ਅਤੇ ਸਥਾਨ ਤੋਂ ਬਣਾਇਆ ਜਾਂਦਾ ਹੈ

## ਤੁਰੰਤ ਸ਼ੁਰੂਆਤ

### 1. Azure OpenAI ਤਾਇਨਾਤ ਕਰੋ

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

ਜਦੋਂ ਪੁੱਛਿਆ ਜਾਵੇ:
- ਆਪਣੀ Azure ਸਬਸਕ੍ਰਿਪਸ਼ਨ ਚੁਣੋ
- ਇੱਕ ਸਥਾਨ ਚੁਣੋ (ਸਿਫਾਰਸ਼ੀ: `eastus2` ਜਾਂ `swedencentral` GPT-5 ਉਪਲਬਧਤਾ ਲਈ)
- ਵਾਤਾਵਰਣ ਨਾਮ ਦੀ ਪੁਸ਼ਟੀ ਕਰੋ (ਡਿਫਾਲਟ: `langchain4j-dev`)

ਇਸ ਨਾਲ ਬਣੇਗਾ:
- GPT-5 ਅਤੇ text-embedding-3-small ਨਾਲ Azure OpenAI ਸਰੋਤ
- ਕਨੈਕਸ਼ਨ ਵੇਰਵੇ ਆਉਟਪੁੱਟ

### 2. ਕਨੈਕਸ਼ਨ ਵੇਰਵੇ ਪ੍ਰਾਪਤ ਕਰੋ

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

ਇਹ ਦਿਖਾਉਂਦਾ ਹੈ:
- `AZURE_OPENAI_ENDPOINT`: ਤੁਹਾਡਾ Azure OpenAI ਐਂਡਪੌਇੰਟ URL
- `AZURE_OPENAI_KEY`: ਪ੍ਰਮਾਣਿਕਤਾ ਲਈ API ਕੁੰਜੀ
- `AZURE_OPENAI_DEPLOYMENT`: ਚੈਟ ਮਾਡਲ ਨਾਮ (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: ਐਂਬੈਡਿੰਗ ਮਾਡਲ ਨਾਮ

### 3. ਐਪਲੀਕੇਸ਼ਨ ਸਥਾਨਕ ਤੌਰ 'ਤੇ ਚਲਾਓ

`azd up` ਕਮਾਂਡ ਆਪਣੇ ਆਪ ਰੂਟ ਡਾਇਰੈਕਟਰੀ ਵਿੱਚ ਸਾਰੇ ਜ਼ਰੂਰੀ ਵਾਤਾਵਰਣ ਚਲਾਂ ਵਾਲੇ `.env` ਫਾਈਲ ਬਣਾਉਂਦਾ ਹੈ।

**ਸਿਫਾਰਸ਼ੀ:** ਸਾਰੇ ਵੈੱਬ ਐਪਲੀਕੇਸ਼ਨ ਸ਼ੁਰੂ ਕਰੋ:

**Bash:**
```bash
# ਰੂਟ ਡਾਇਰੈਕਟਰੀ ਤੋਂ
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# ਰੂਟ ਡਾਇਰੈਕਟਰੀ ਤੋਂ
cd ../..
.\start-all.ps1
```

ਜਾਂ ਇੱਕ ਮੋਡੀਊਲ ਸ਼ੁਰੂ ਕਰੋ:

**Bash:**
```bash
# ਉਦਾਹਰਨ: ਸਿਰਫ਼ ਪਰਿਚਯ ਮੋਡੀਊਲ ਸ਼ੁਰੂ ਕਰੋ
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# ਉਦਾਹਰਨ: ਸਿਰਫ਼ ਪਰਿਚਯ ਮੋਡੀਊਲ ਸ਼ੁਰੂ ਕਰੋ
cd ../01-introduction
.\start.ps1
```

ਦੋਹਾਂ ਸਕ੍ਰਿਪਟਾਂ `azd up` ਦੁਆਰਾ ਬਣਾਈ ਗਈ ਰੂਟ `.env` ਫਾਈਲ ਤੋਂ ਵਾਤਾਵਰਣ ਚਲਾਂ ਵਾਲੇ ਲੋਡ ਕਰਦੀਆਂ ਹਨ।

## ਸੰਰਚਨਾ

### ਮਾਡਲ ਤਾਇਨਾਤੀਆਂ ਨੂੰ ਕਸਟਮਾਈਜ਼ ਕਰਨਾ

ਮਾਡਲ ਤਾਇਨਾਤੀਆਂ ਬਦਲਣ ਲਈ, `infra/main.bicep` ਨੂੰ ਸੋਧੋ ਅਤੇ `openAiDeployments` ਪੈਰਾਮੀਟਰ ਨੂੰ ਬਦਲੋ:

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

ਉਪਲਬਧ ਮਾਡਲ ਅਤੇ ਸੰਸਕਰਣ: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure ਖੇਤਰ ਬਦਲਣਾ

ਕਿਸੇ ਹੋਰ ਖੇਤਰ ਵਿੱਚ ਤਾਇਨਾਤ ਕਰਨ ਲਈ, `infra/main.bicep` ਸੋਧੋ:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 ਉਪਲਬਧਤਾ ਦੇਖੋ: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep ਫਾਈਲਾਂ ਵਿੱਚ ਬਦਲਾਅ ਕਰਨ ਤੋਂ ਬਾਅਦ ਇੰਫਰਾਸਟਰੱਕਚਰ ਅੱਪਡੇਟ ਕਰਨ ਲਈ:

**Bash:**
```bash
# ARM ਟੈਮਪਲੇਟ ਨੂੰ ਮੁੜ ਬਣਾਓ
az bicep build --file infra/main.bicep

# ਬਦਲਾਵਾਂ ਦਾ ਪ੍ਰੀਵਿਊ ਕਰੋ
azd provision --preview

# ਬਦਲਾਵਾਂ ਲਾਗੂ ਕਰੋ
azd provision
```

**PowerShell:**
```powershell
# ARM ਟੈਮਪਲੇਟ ਨੂੰ ਮੁੜ ਬਣਾਓ
az bicep build --file infra/main.bicep

# ਬਦਲਾਵਾਂ ਦਾ ਪ੍ਰੀਵਿਊ ਕਰੋ
azd provision --preview

# ਬਦਲਾਵਾਂ ਲਾਗੂ ਕਰੋ
azd provision
```

## ਸਾਫ਼-ਸਫਾਈ

ਸਾਰੇ ਸਰੋਤ ਮਿਟਾਉਣ ਲਈ:

**Bash:**
```bash
# ਸਾਰੇ ਸਰੋਤ ਮਿਟਾਓ
azd down

# ਵਾਤਾਵਰਣ ਸਮੇਤ ਸਭ ਕੁਝ ਮਿਟਾਓ
azd down --purge
```

**PowerShell:**
```powershell
# ਸਾਰੇ ਸਰੋਤ ਮਿਟਾਓ
azd down

# ਵਾਤਾਵਰਣ ਸਮੇਤ ਸਭ ਕੁਝ ਮਿਟਾਓ
azd down --purge
```

**ਚੇਤਾਵਨੀ**: ਇਹ ਸਾਰੇ Azure ਸਰੋਤ ਸਥਾਈ ਤੌਰ 'ਤੇ ਮਿਟਾ ਦੇਵੇਗਾ।

## ਫਾਈਲ ਸੰਰਚਨਾ

## ਲਾਗਤ ਅਨੁਕੂਲਤਾ

### ਵਿਕਾਸ/ਟੈਸਟਿੰਗ
ਡਿਵ/ਟੈਸਟ ਵਾਤਾਵਰਣਾਂ ਲਈ, ਤੁਸੀਂ ਲਾਗਤ ਘਟਾ ਸਕਦੇ ਹੋ:
- Azure OpenAI ਲਈ ਸਟੈਂਡਰਡ ਟੀਅਰ (S0) ਵਰਤੋਂ
- `infra/core/ai/cognitiveservices.bicep` ਵਿੱਚ ਘੱਟ ਸਮਰੱਥਾ (10K TPM ਬਜਾਏ 20K) ਸੈੱਟ ਕਰੋ
- ਜਦੋਂ ਵਰਤੋਂ ਨਾ ਹੋਵੇ ਤਾਂ ਸਰੋਤ ਮਿਟਾਓ: `azd down`

### ਉਤਪਾਦਨ
ਉਤਪਾਦਨ ਲਈ:
- ਵਰਤੋਂ ਅਨੁਸਾਰ OpenAI ਸਮਰੱਥਾ ਵਧਾਓ (50K+ TPM)
- ਵੱਧ ਉਪਲਬਧਤਾ ਲਈ ਜ਼ੋਨ ਰਿਡੰਡੈਂਸੀ ਚਾਲੂ ਕਰੋ
- ਢੰਗ ਨਾਲ ਨਿਗਰਾਨੀ ਅਤੇ ਲਾਗਤ ਅਲਰਟ ਲਾਗੂ ਕਰੋ

### ਲਾਗਤ ਅੰਦਾਜ਼ਾ
- Azure OpenAI: ਪ੍ਰਤੀ ਟੋਕਨ ਭੁਗਤਾਨ (ਇਨਪੁੱਟ + ਆਉਟਪੁੱਟ)
- GPT-5: ਲਗਭਗ $3-5 ਪ੍ਰਤੀ 1M ਟੋਕਨ (ਮੌਜੂਦਾ ਕੀਮਤ ਦੇਖੋ)
- text-embedding-3-small: ਲਗਭਗ $0.02 ਪ੍ਰਤੀ 1M ਟੋਕਨ

ਕੀਮਤ ਕੈਲਕੂਲੇਟਰ: https://azure.microsoft.com/pricing/calculator/

## ਨਿਗਰਾਨੀ

### Azure OpenAI ਮੈਟ੍ਰਿਕਸ ਵੇਖੋ

Azure ਪੋਰਟਲ → ਤੁਹਾਡਾ OpenAI ਸਰੋਤ → ਮੈਟ੍ਰਿਕਸ:
- ਟੋਕਨ-ਆਧਾਰਿਤ ਵਰਤੋਂ
- HTTP ਬੇਨਤੀ ਦਰ
- ਜਵਾਬ ਦੇਣ ਦਾ ਸਮਾਂ
- ਸਰਗਰਮ ਟੋਕਨ

## ਸਮੱਸਿਆ ਨਿਵਾਰਣ

### ਸਮੱਸਿਆ: Azure OpenAI ਸਬਡੋਮੇਨ ਨਾਮ ਟਕਰਾਅ

**ਗਲਤੀ ਸੁਨੇਹਾ:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**ਕਾਰਨ:**
ਤੁਹਾਡੇ ਸਬਸਕ੍ਰਿਪਸ਼ਨ/ਵਾਤਾਵਰਣ ਤੋਂ ਬਣਿਆ ਸਬਡੋਮੇਨ ਨਾਮ ਪਹਿਲਾਂ ਹੀ ਵਰਤਿਆ ਜਾ ਰਿਹਾ ਹੈ, ਸੰਭਵ ਹੈ ਕਿ ਪਿਛਲੇ ਤਾਇਨਾਤ ਤੋਂ ਜੋ ਪੂਰੀ ਤਰ੍ਹਾਂ ਸਾਫ਼ ਨਹੀਂ ਹੋਇਆ।

**ਹੱਲ:**
1. **ਵਿਕਲਪ 1 - ਵੱਖਰਾ ਵਾਤਾਵਰਣ ਨਾਮ ਵਰਤੋਂ:**
   
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

2. **ਵਿਕਲਪ 2 - Azure ਪੋਰਟਲ ਰਾਹੀਂ ਮੈਨੂਅਲ ਤਾਇਨਾਤ:**
   - Azure ਪੋਰਟਲ → ਸਰੋਤ ਬਣਾਓ → Azure OpenAI
   - ਆਪਣੇ ਸਰੋਤ ਲਈ ਵਿਲੱਖਣ ਨਾਮ ਚੁਣੋ
   - ਹੇਠ ਲਿਖੇ ਮਾਡਲ ਤਾਇਨਾਤ ਕਰੋ:
     - **GPT-5**
     - **text-embedding-3-small** (RAG ਮੋਡੀਊਲ ਲਈ)
   - **ਮਹੱਤਵਪੂਰਨ:** ਆਪਣੇ ਤਾਇਨਾਤ ਨਾਮ ਨੋਟ ਕਰੋ - ਇਹ `.env` ਸੰਰਚਨਾ ਨਾਲ ਮੇਲ ਖਾਣੇ ਚਾਹੀਦੇ ਹਨ
   - ਤਾਇਨਾਤ ਤੋਂ ਬਾਅਦ, "Keys and Endpoint" ਤੋਂ ਆਪਣਾ ਐਂਡਪੌਇੰਟ ਅਤੇ API ਕੁੰਜੀ ਪ੍ਰਾਪਤ ਕਰੋ
   - ਪ੍ਰੋਜੈਕਟ ਰੂਟ ਵਿੱਚ `.env` ਫਾਈਲ ਬਣਾਓ ਜਿਸ ਵਿੱਚ:

     **ਉਦਾਹਰਨ `.env` ਫਾਈਲ:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**ਮਾਡਲ ਤਾਇਨਾਤ ਨਾਮਕਰਨ ਨਿਯਮ:**
- ਸਧਾਰਣ, ਸਥਿਰ ਨਾਮ ਵਰਤੋਂ: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- ਤਾਇਨਾਤ ਨਾਮ `.env` ਵਿੱਚ ਜੋ ਤੁਸੀਂ ਸੰਰਚਿਤ ਕਰਦੇ ਹੋ ਉਸ ਨਾਲ ਬਿਲਕੁਲ ਮੇਲ ਖਾਣੇ ਚਾਹੀਦੇ ਹਨ
- ਆਮ ਗਲਤੀ: ਇੱਕ ਨਾਮ ਨਾਲ ਮਾਡਲ ਬਣਾਉਣਾ ਪਰ ਕੋਡ ਵਿੱਚ ਵੱਖਰਾ ਨਾਮ ਦਰਸਾਉਣਾ

### ਸਮੱਸਿਆ: ਚੁਣੇ ਖੇਤਰ ਵਿੱਚ GPT-5 ਉਪਲਬਧ ਨਹੀਂ

**ਹੱਲ:**
- GPT-5 ਪਹੁੰਚ ਵਾਲੇ ਖੇਤਰ ਚੁਣੋ (ਜਿਵੇਂ eastus, swedencentral)
- ਉਪਲਬਧਤਾ ਦੇਖੋ: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### ਸਮੱਸਿਆ: ਤਾਇਨਾਤ ਲਈ ਕਵੋਟਾ ਅਪਰਯਾਪਤ

**ਹੱਲ:**
1. Azure ਪੋਰਟਲ ਵਿੱਚ ਕਵੋਟਾ ਵਾਧਾ ਮੰਗੋ
2. ਜਾਂ `main.bicep` ਵਿੱਚ ਘੱਟ ਸਮਰੱਥਾ ਵਰਤੋਂ (ਜਿਵੇਂ ਸਮਰੱਥਾ: 10)

### ਸਮੱਸਿਆ: ਸਥਾਨਕ ਚਲਾਉਂਦੇ ਸਮੇਂ "Resource not found"

**ਹੱਲ:**
1. ਤਾਇਨਾਤ ਦੀ ਜਾਂਚ ਕਰੋ: `azd env get-values`
2. ਐਂਡਪੌਇੰਟ ਅਤੇ ਕੁੰਜੀ ਸਹੀ ਹਨ ਜਾਂ ਨਹੀਂ ਵੇਖੋ
3. ਯਕੀਨੀ ਬਣਾਓ ਕਿ ਸਰੋਤ ਗਰੁੱਪ Azure ਪੋਰਟਲ ਵਿੱਚ ਮੌਜੂਦ ਹੈ

### ਸਮੱਸਿਆ: ਪ੍ਰਮਾਣਿਕਤਾ ਅਸਫਲ

**ਹੱਲ:**
- `AZURE_OPENAI_API_KEY` ਸਹੀ ਤੌਰ 'ਤੇ ਸੈੱਟ ਹੈ ਜਾਂ ਨਹੀਂ ਜਾਂਚੋ
- ਕੁੰਜੀ ਫਾਰਮੈਟ 32-ਅੱਖਰੀ ਹੇਕਸਾਡੇਸੀਮਲ ਸਤਰ ਹੋਣਾ ਚਾਹੀਦਾ ਹੈ
- ਜੇ ਲੋੜ ਹੋਵੇ ਤਾਂ Azure ਪੋਰਟਲ ਤੋਂ ਨਵੀਂ ਕੁੰਜੀ ਪ੍ਰਾਪਤ ਕਰੋ

### ਤਾਇਨਾਤ ਅਸਫਲ

**ਸਮੱਸਿਆ**: `azd provision` ਕਵੋਟਾ ਜਾਂ ਸਮਰੱਥਾ ਗਲਤੀਆਂ ਨਾਲ ਅਸਫਲ

**ਹੱਲ**: 
1. ਵੱਖਰਾ ਖੇਤਰ ਅਜ਼ਮਾਓ - ਖੇਤਰ ਸੰਰਚਨਾ ਲਈ [Changing Azure Regions](../../../../01-introduction/infra) ਹਿੱਸਾ ਵੇਖੋ
2. ਯਕੀਨੀ ਬਣਾਓ ਕਿ ਤੁਹਾਡੇ ਸਬਸਕ੍ਰਿਪਸ਼ਨ ਕੋਲ Azure OpenAI ਕਵੋਟਾ ਹੈ:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### ਐਪਲੀਕੇਸ਼ਨ ਕਨੈਕਟ ਨਹੀਂ ਹੋ ਰਿਹਾ

**ਸਮੱਸਿਆ**: ਜਾਵਾ ਐਪਲੀਕੇਸ਼ਨ ਕਨੈਕਸ਼ਨ ਗਲਤੀਆਂ ਦਿਖਾ ਰਿਹਾ ਹੈ

**ਹੱਲ**:
1. ਵਾਤਾਵਰਣ ਚਲਾਂ ਵਾਲੇ ਨਿਰਯਾਤ ਹੋਏ ਹਨ ਜਾਂ ਨਹੀਂ ਜਾਂਚੋ:
   
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

2. ਐਂਡਪੌਇੰਟ ਫਾਰਮੈਟ ਸਹੀ ਹੈ ਜਾਂ ਨਹੀਂ (ਇਹ `https://xxx.openai.azure.com` ਹੋਣਾ ਚਾਹੀਦਾ ਹੈ)
3. ਯਕੀਨੀ ਬਣਾਓ ਕਿ API ਕੁੰਜੀ Azure ਪੋਰਟਲ ਤੋਂ ਪ੍ਰਾਇਮਰੀ ਜਾਂ ਸੈਕੰਡਰੀ ਕੁੰਜੀ ਹੈ

**ਸਮੱਸਿਆ**: Azure OpenAI ਤੋਂ 401 Unauthorized

**ਹੱਲ**:
1. Azure ਪੋਰਟਲ → Keys and Endpoint ਤੋਂ ਨਵੀਂ API ਕੁੰਜੀ ਪ੍ਰਾਪਤ ਕਰੋ
2. `AZURE_OPENAI_API_KEY` ਵਾਤਾਵਰਣ ਚਲਾਂ ਵਾਲੇ ਨੂੰ ਦੁਬਾਰਾ ਨਿਰਯਾਤ ਕਰੋ
3. ਯਕੀਨੀ ਬਣਾਓ ਕਿ ਮਾਡਲ ਤਾਇਨਾਤੀਆਂ ਪੂਰੀਆਂ ਹੋ ਚੁੱਕੀਆਂ ਹਨ (Azure ਪੋਰਟਲ ਵਿੱਚ ਜਾਂਚੋ)

### ਪ੍ਰਦਰਸ਼ਨ ਸਮੱਸਿਆਵਾਂ

**ਸਮੱਸਿਆ**: ਜਵਾਬ ਦੇਣ ਵਿੱਚ ਧੀਰਜ

**ਹੱਲ**:
1. Azure ਪੋਰਟਲ ਮੈਟ੍ਰਿਕਸ ਵਿੱਚ OpenAI ਟੋਕਨ ਵਰਤੋਂ ਅਤੇ ਥਰੋਟਲਿੰਗ ਦੀ ਜਾਂਚ ਕਰੋ
2. ਜੇ ਸੀਮਾਵਾਂ ਪਾਰ ਹੋ ਰਹੀਆਂ ਹਨ ਤਾਂ TPM ਸਮਰੱਥਾ ਵਧਾਓ
3. ਵੱਧ ਸੋਚ-ਵਿਚਾਰ ਪੱਧਰ (ਘੱਟ/ਮੱਧ/ਉੱਚ) ਵਰਤਣ ਬਾਰੇ ਸੋਚੋ

## ਇੰਫਰਾਸਟਰੱਕਚਰ ਅੱਪਡੇਟ ਕਰਨਾ

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

## ਸੁਰੱਖਿਆ ਸਿਫਾਰਸ਼ਾਂ

1. **ਕਦੇ ਵੀ API ਕੁੰਜੀਆਂ ਕਮਿਟ ਨਾ ਕਰੋ** - ਵਾਤਾਵਰਣ ਚਲਾਂ ਵਾਲੇ ਵਰਤੋਂ
2. **ਸਥਾਨਕ ਤੌਰ 'ਤੇ .env ਫਾਈਲਾਂ ਵਰਤੋਂ** - `.env` ਨੂੰ `.gitignore` ਵਿੱਚ ਸ਼ਾਮਲ ਕਰੋ
3. **ਕੁੰਜੀਆਂ ਨਿਯਮਤ ਤੌਰ 'ਤੇ ਘੁਮਾਓ** - Azure ਪੋਰਟਲ ਵਿੱਚ ਨਵੀਆਂ ਕੁੰਜੀਆਂ ਬਣਾਓ
4. **ਪਹੁੰਚ ਸੀਮਿਤ ਕਰੋ** - Azure RBAC ਨਾਲ ਕੰਟਰੋਲ ਕਰੋ ਕਿ ਕੌਣ ਸਰੋਤਾਂ ਤੱਕ ਪਹੁੰਚ ਸਕਦਾ ਹੈ
5. **ਵਰਤੋਂ ਦੀ ਨਿਗਰਾਨੀ ਕਰੋ** - Azure ਪੋਰਟਲ ਵਿੱਚ ਲਾਗਤ ਅਲਰਟ ਸੈੱਟ ਕਰੋ

## ਵਾਧੂ ਸਰੋਤ

- [Azure OpenAI ਸੇਵਾ ਦਸਤਾਵੇਜ਼](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 ਮਾਡਲ ਦਸਤਾਵੇਜ਼](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI ਦਸਤਾਵੇਜ਼](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep ਦਸਤਾਵੇਜ਼](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI ਅਧਿਕਾਰਤ ਇੰਟੀਗ੍ਰੇਸ਼ਨ](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## ਸਹਾਇਤਾ

ਸਮੱਸਿਆਵਾਂ ਲਈ:
1. ਉੱਪਰ ਦਿੱਤੇ [ਸਮੱਸਿਆ ਨਿਵਾਰਣ ਹਿੱਸਾ](../../../../01-introduction/infra) ਨੂੰ ਵੇਖੋ
2. Azure ਪੋਰਟਲ ਵਿੱਚ Azure OpenAI ਸੇਵਾ ਸਿਹਤ ਦੀ ਸਮੀਖਿਆ ਕਰੋ
3. ਰਿਪੋਜ਼ਟਰੀ ਵਿੱਚ ਇੱਕ ਇਸ਼ੂ ਖੋਲ੍ਹੋ

## ਲਾਇਸੈਂਸ

ਵੇਰਵੇ ਲਈ ਰੂਟ [LICENSE](../../../../LICENSE) ਫਾਈਲ ਵੇਖੋ।

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**ਅਸਵੀਕਾਰੋਪੱਤਰ**:  
ਇਹ ਦਸਤਾਵੇਜ਼ AI ਅਨੁਵਾਦ ਸੇਵਾ [Co-op Translator](https://github.com/Azure/co-op-translator) ਦੀ ਵਰਤੋਂ ਕਰਕੇ ਅਨੁਵਾਦ ਕੀਤਾ ਗਿਆ ਹੈ। ਜਦੋਂ ਕਿ ਅਸੀਂ ਸਹੀਤਾ ਲਈ ਕੋਸ਼ਿਸ਼ ਕਰਦੇ ਹਾਂ, ਕਿਰਪਾ ਕਰਕੇ ਧਿਆਨ ਵਿੱਚ ਰੱਖੋ ਕਿ ਸਵੈਚਾਲਿਤ ਅਨੁਵਾਦਾਂ ਵਿੱਚ ਗਲਤੀਆਂ ਜਾਂ ਅਸਮਰਥਤਾਵਾਂ ਹੋ ਸਕਦੀਆਂ ਹਨ। ਮੂਲ ਦਸਤਾਵੇਜ਼ ਆਪਣੀ ਮੂਲ ਭਾਸ਼ਾ ਵਿੱਚ ਪ੍ਰਮਾਣਿਕ ਸਰੋਤ ਮੰਨਿਆ ਜਾਣਾ ਚਾਹੀਦਾ ਹੈ। ਮਹੱਤਵਪੂਰਨ ਜਾਣਕਾਰੀ ਲਈ, ਪੇਸ਼ੇਵਰ ਮਨੁੱਖੀ ਅਨੁਵਾਦ ਦੀ ਸਿਫਾਰਸ਼ ਕੀਤੀ ਜਾਂਦੀ ਹੈ। ਅਸੀਂ ਇਸ ਅਨੁਵਾਦ ਦੀ ਵਰਤੋਂ ਤੋਂ ਉਤਪੰਨ ਕਿਸੇ ਵੀ ਗਲਤਫਹਿਮੀ ਜਾਂ ਗਲਤ ਵਿਆਖਿਆ ਲਈ ਜ਼ਿੰਮੇਵਾਰ ਨਹੀਂ ਹਾਂ।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->