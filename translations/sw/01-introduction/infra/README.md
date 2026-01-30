# Miundombinu ya Azure kwa LangChain4j Kuanzia

## Jedwali la Yaliyomo

- [Mahitaji ya Awali](../../../../01-introduction/infra)
- [Mimaarufu](../../../../01-introduction/infra)
- [Rasilimali Zilizoundwa](../../../../01-introduction/infra)
- [Anza Haraka](../../../../01-introduction/infra)
- [Usanidi](../../../../01-introduction/infra)
- [Amri za Usimamizi](../../../../01-introduction/infra)
- [Uboreshaji wa Gharama](../../../../01-introduction/infra)
- [Ufuatiliaji](../../../../01-introduction/infra)
- [Utatuzi wa Matatizo](../../../../01-introduction/infra)
- [Kusasisha Miundombinu](../../../../01-introduction/infra)
- [Usafishaji](../../../../01-introduction/infra)
- [Muundo wa Faili](../../../../01-introduction/infra)
- [Mapendekezo ya Usalama](../../../../01-introduction/infra)
- [Rasilimali Zaidi](../../../../01-introduction/infra)

Hii saraka ina miundombinu ya Azure kama msimbo (IaC) kwa kutumia Bicep na Azure Developer CLI (azd) kwa ajili ya kuweka rasilimali za Azure OpenAI.

## Mahitaji ya Awali

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (toleo 2.50.0 au baadaye)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (toleo 1.5.0 au baadaye)
- Usajili wa Azure wenye ruhusa za kuunda rasilimali

## Mimaarufu

**Mpangilio Rahisi wa Maendeleo ya Ndani** - Weka Azure OpenAI tu, endesha programu zote kwa ndani.

Miundombinu inaweka rasilimali zifuatazo za Azure:

### Huduma za AI
- **Azure OpenAI**: Huduma za Kihisia zenye uanzishaji wa mifano miwili:
  - **gpt-5**: Mfano wa mazungumzo ya kukamilisha (uwezo wa 20K TPM)
  - **text-embedding-3-small**: Mfano wa uingizaji kwa RAG (uwezo wa 20K TPM)

### Maendeleo ya Ndani
Programu zote za Spring Boot zinaendeshwa kwa ndani kwenye mashine yako:
- 01-introduction (bandari 8080)
- 02-prompt-engineering (bandari 8083)
- 03-rag (bandari 8081)
- 04-tools (bandari 8084)

## Rasilimali Zilizoundwa

| Aina ya Rasilimali | Mfano wa Jina la Rasilimali | Kusudi |
|--------------|----------------------|---------|
| Kundi la Rasilimali | `rg-{environmentName}` | Inashikilia rasilimali zote |
| Azure OpenAI | `aoai-{resourceToken}` | Ukarabati wa mfano wa AI |

> **Kumbuka:** `{resourceToken}` ni mfuatano wa kipekee unaotengenezwa kutoka kwa ID ya usajili, jina la mazingira, na eneo

## Anza Haraka

### 1. Weka Azure OpenAI

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

Unapoulizwa:
- Chagua usajili wako wa Azure
- Chagua eneo (inapendekezwa: `eastus2` au `swedencentral` kwa upatikanaji wa GPT-5)
- Thibitisha jina la mazingira (chaguo-msingi: `langchain4j-dev`)

Hii itaunda:
- Rasilimali ya Azure OpenAI yenye GPT-5 na text-embedding-3-small
- Toa maelezo ya muunganisho

### 2. Pata Maelezo ya Muunganisho

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Hii inaonyesha:
- `AZURE_OPENAI_ENDPOINT`: URL ya mwisho wa Azure OpenAI yako
- `AZURE_OPENAI_KEY`: Kitufe cha API kwa uthibitishaji
- `AZURE_OPENAI_DEPLOYMENT`: Jina la mfano wa mazungumzo (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Jina la mfano wa uingizaji

### 3. Endesha Programu Ndani

Amri ya `azd up` huunda moja kwa moja faili `.env` katika saraka kuu yenye vigezo vyote vya mazingira vinavyohitajika.

**Inapendekezwa:** Anzisha programu zote za wavuti:

**Bash:**
```bash
# Kutoka kwenye saraka ya mzizi
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Kutoka kwenye saraka ya mzizi
cd ../..
.\start-all.ps1
```

Au anzisha moduli moja:

**Bash:**
```bash
# Mfano: Anza tu moduli ya utangulizi
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Mfano: Anza tu moduli ya utangulizi
cd ../01-introduction
.\start.ps1
```

Mafaili yote ya amri hujaza vigezo vya mazingira kutoka kwa faili `.env` iliyoundwa na `azd up`.

## Usanidi

### Kubadilisha Uanzishaji wa Mifano

Ili kubadilisha uanzishaji wa mifano, hariri `infra/main.bicep` na badilisha parameter `openAiDeployments`:

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

Mifano na matoleo inayopatikana: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Kubadilisha Maeneo ya Azure

Ili kuweka katika eneo tofauti, hariri `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Angalia upatikanaji wa GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Ili kusasisha miundombinu baada ya kufanya mabadiliko kwenye faili za Bicep:

**Bash:**
```bash
# Jenga upya kiolezo cha ARM
az bicep build --file infra/main.bicep

# Tazama mabadiliko
azd provision --preview

# Tekeleza mabadiliko
azd provision
```

**PowerShell:**
```powershell
# Jenga upya kiolezo cha ARM
az bicep build --file infra/main.bicep

# Tazama mabadiliko
azd provision --preview

# Tekeleza mabadiliko
azd provision
```

## Usafishaji

Ili kufuta rasilimali zote:

**Bash:**
```bash
# Futa rasilimali zote
azd down

# Futa kila kitu ikiwa ni pamoja na mazingira
azd down --purge
```

**PowerShell:**
```powershell
# Futa rasilimali zote
azd down

# Futa kila kitu ikiwa ni pamoja na mazingira
azd down --purge
```

**Onyo**: Hii itafuta rasilimali zote za Azure kwa kudumu.

## Muundo wa Faili

## Uboreshaji wa Gharama

### Maendeleo/Majaribio
Kwa mazingira ya maendeleo/majaribio, unaweza kupunguza gharama:
- Tumia kiwango cha Standard (S0) kwa Azure OpenAI
- Weka uwezo mdogo (10K TPM badala ya 20K) katika `infra/core/ai/cognitiveservices.bicep`
- Futa rasilimali wakati hazitumiki: `azd down`

### Uzalishaji
Kwa uzalishaji:
- Ongeza uwezo wa OpenAI kulingana na matumizi (50K+ TPM)
- Weka upya wa eneo kwa upatikanaji bora
- Tekeleza ufuatiliaji mzuri na arifa za gharama

### Makadirio ya Gharama
- Azure OpenAI: Lipa kwa tokeni (ingizo + toleo)
- GPT-5: ~$3-5 kwa milioni 1 ya tokeni (angalia bei ya sasa)
- text-embedding-3-small: ~$0.02 kwa milioni 1 ya tokeni

Kalkuleta ya bei: https://azure.microsoft.com/pricing/calculator/

## Ufuatiliaji

### Tazama Vipimo vya Azure OpenAI

Nenda Azure Portal → Rasilimali yako ya OpenAI → Vipimo:
- Matumizi ya Tokeni
- Kiwango cha Maombi ya HTTP
- Muda wa Kujibu
- Tokeni Zilizotumika

## Utatuzi wa Matatizo

### Tatizo: Mgongano wa jina la subdomain ya Azure OpenAI

**Ujumbe wa Hitilafu:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Sababu:**
Jina la subdomain lililotengenezwa kutoka kwa usajili/muhitaji tayari linatumika, labda kutoka kwa uanzishaji wa awali ambao haujakamilika kufutwa.

**Suluhisho:**
1. **Chaguo 1 - Tumia jina tofauti la mazingira:**
   
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

2. **Chaguo 2 - Uanzishaji wa mkono kupitia Azure Portal:**
   - Nenda Azure Portal → Unda rasilimali → Azure OpenAI
   - Chagua jina la kipekee kwa rasilimali yako
   - Weka mifano ifuatayo:
     - **GPT-5**
     - **text-embedding-3-small** (kwa moduli za RAG)
   - **Muhimu:** Kumbuka majina ya uanzishaji - lazima yaendane na usanidi wa `.env`
   - Baada ya uanzishaji, pata mwisho na kitufe cha API kutoka "Keys and Endpoint"
   - Unda faili `.env` katika saraka kuu na:
     
     **Mfano wa faili `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Miongozo ya Majina ya Uanzishaji wa Mfano:**
- Tumia majina rahisi, ya kawaida: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Majina ya uanzishaji lazima yaendane kabisa na yale unayoweka katika `.env`
- Makosa ya kawaida: Kuunda mfano kwa jina moja lakini kurejelea jina tofauti katika msimbo

### Tatizo: GPT-5 haipatikani katika eneo lililochaguliwa

**Suluhisho:**
- Chagua eneo lenye upatikanaji wa GPT-5 (mfano, eastus, swedencentral)
- Angalia upatikanaji: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Tatizo: Kiasi cha rasilimali hakitoshi kwa uanzishaji

**Suluhisho:**
1. Omba ongezeko la kiasi katika Azure Portal
2. Au tumia uwezo mdogo katika `main.bicep` (mfano, uwezo: 10)

### Tatizo: "Rasilimali haipatikani" unapoendesha kwa ndani

**Suluhisho:**
1. Thibitisha uanzishaji: `azd env get-values`
2. Angalia mwisho na kitufe ni sahihi
3. Hakikisha kundi la rasilimali lipo katika Azure Portal

### Tatizo: Uthibitishaji umeshindwa

**Suluhisho:**
- Thibitisha `AZURE_OPENAI_API_KEY` imewekwa kwa usahihi
- Muundo wa kitufe unapaswa kuwa mfuatano wa herufi 32 za hexadecimal
- Pata kitufe kipya kutoka Azure Portal ikiwa inahitajika

### Uanzishaji Umekataliwa

**Tatizo**: `azd provision` inakataa kwa sababu za kiasi au uwezo

**Suluhisho**: 
1. Jaribu eneo tofauti - Angalia sehemu ya [Kubadilisha Maeneo ya Azure](../../../../01-introduction/infra) kwa jinsi ya kusanidi maeneo
2. Hakikisha usajili wako una kiasi cha Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Programu Haijiunganishi

**Tatizo**: Programu ya Java inaonyesha makosa ya muunganisho

**Suluhisho**:
1. Thibitisha vigezo vya mazingira vimewekwa:
   
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

2. Angalia muundo wa mwisho ni sahihi (inapaswa kuwa `https://xxx.openai.azure.com`)
3. Thibitisha kitufe cha API ni kitufe cha msingi au cha sekondari kutoka Azure Portal

**Tatizo**: 401 Unauthorized kutoka Azure OpenAI

**Suluhisho**:
1. Pata kitufe kipya cha API kutoka Azure Portal → Keys and Endpoint
2. Re-export vigezo vya mazingira `AZURE_OPENAI_API_KEY`
3. Hakikisha uanzishaji wa mifano umekamilika (angalia Azure Portal)

### Matatizo ya Utendaji

**Tatizo**: Muda wa majibu ni polepole

**Suluhisho**:
1. Angalia matumizi ya tokeni na kupunguzwa kwa kasi katika vipimo vya Azure Portal
2. Ongeza uwezo wa TPM ikiwa unafikia mipaka
3. Fikiria kutumia kiwango cha juu cha jitihada za kufikiri (chini/kati/juu)

## Kusasisha Miundombinu

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

## Mapendekezo ya Usalama

1. **Usiweka funguo za API kwenye msimbo** - Tumia vigezo vya mazingira
2. **Tumia faili za .env kwa ndani** - Ongeza `.env` kwenye `.gitignore`
3. **Zungusha funguo mara kwa mara** - Tengeneza funguo mpya katika Azure Portal
4. **Punguza ufikiaji** - Tumia Azure RBAC kudhibiti nani anaweza kufikia rasilimali
5. **Fuatilia matumizi** - Weka arifa za gharama katika Azure Portal

## Rasilimali Zaidi

- [Nyaraka za Huduma ya Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [Nyaraka za Mfano wa GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Nyaraka za Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Nyaraka za Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Muunganisho Rasmi wa LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Msaada

Kwa matatizo:
1. Angalia [sehemu ya utatuzi wa matatizo](../../../../01-introduction/infra) hapo juu
2. Kagua afya ya huduma ya Azure OpenAI katika Azure Portal
3. Fungua tatizo katika hifadhidata

## Leseni

Tazama faili la [LICENSE](../../../../LICENSE) katika saraka kuu kwa maelezo.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Kiarifa cha Kukataa**:
Hati hii imetafsiriwa kwa kutumia huduma ya tafsiri ya AI [Co-op Translator](https://github.com/Azure/co-op-translator). Ingawa tunajitahidi kwa usahihi, tafadhali fahamu kuwa tafsiri za kiotomatiki zinaweza kuwa na makosa au upungufu wa usahihi. Hati ya asili katika lugha yake ya asili inapaswa kuchukuliwa kama chanzo cha mamlaka. Kwa taarifa muhimu, tafsiri ya kitaalamu ya binadamu inapendekezwa. Hatubebei dhamana kwa kutoelewana au tafsiri potofu zinazotokana na matumizi ya tafsiri hii.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->