<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:32:31+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "te"
}
-->
# LangChain4j కోసం Azure మౌలిక సదుపాయాలు ప్రారంభించడం

## విషయ సూచిక

- [ముందస్తు అవసరాలు](../../../../01-introduction/infra)
- [వాస్తవిక నిర్మాణం](../../../../01-introduction/infra)
- [సృష్టించబడిన వనరులు](../../../../01-introduction/infra)
- [త్వరిత ప్రారంభం](../../../../01-introduction/infra)
- [కాన్ఫిగరేషన్](../../../../01-introduction/infra)
- [నిర్వహణ ఆదేశాలు](../../../../01-introduction/infra)
- [ఖర్చు ఆప్టిమైజేషన్](../../../../01-introduction/infra)
- [మానిటరింగ్](../../../../01-introduction/infra)
- [సమస్య పరిష్కారం](../../../../01-introduction/infra)
- [మౌలిక సదుపాయాల నవీకరణ](../../../../01-introduction/infra)
- [శుభ్రపరిచే విధానం](../../../../01-introduction/infra)
- [ఫైల్ నిర్మాణం](../../../../01-introduction/infra)
- [భద్రతా సిఫార్సులు](../../../../01-introduction/infra)
- [అదనపు వనరులు](../../../../01-introduction/infra)

ఈ డైరెక్టరీ Bicep మరియు Azure Developer CLI (azd) ఉపయోగించి Azure OpenAI వనరులను అమర్చడానికి Azure మౌలిక సదుపాయాలను కోడ్ (IaC) రూపంలో కలిగి ఉంది.

## ముందస్తు అవసరాలు

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (సంస్కరణ 2.50.0 లేదా తరువాత)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (సంస్కరణ 1.5.0 లేదా తరువాత)
- వనరులు సృష్టించడానికి అనుమతులు ఉన్న Azure సబ్‌స్క్రిప్షన్

## వాస్తవిక నిర్మాణం

**సరళీకృత స్థానిక అభివృద్ధి సెటప్** - Azure OpenAI మాత్రమే అమర్చండి, అన్ని యాప్స్ స్థానికంగా నడపండి.

ఈ మౌలిక సదుపాయాలు క్రింది Azure వనరులను అమర్చుతాయి:

### AI సేవలు
- **Azure OpenAI**: రెండు మోడల్ అమరికలతో కాగ్నిటివ్ సర్వీసులు:
  - **gpt-5**: చాట్ కంప్లీషన్ మోడల్ (20K TPM సామర్థ్యం)
  - **text-embedding-3-small**: RAG కోసం ఎంబెడ్డింగ్ మోడల్ (20K TPM సామర్థ్యం)

### స్థానిక అభివృద్ధి
అన్ని Spring Boot అప్లికేషన్లు మీ యంత్రంలో స్థానికంగా నడుస్తాయి:
- 01-introduction (పోర్ట్ 8080)
- 02-prompt-engineering (పోర్ట్ 8083)
- 03-rag (పోర్ట్ 8081)
- 04-tools (పోర్ట్ 8084)

## సృష్టించబడిన వనరులు

| వనరు రకం | వనరు పేరు నమూనా | ప్రయోజనం |
|--------------|----------------------|---------|
| వనరు గ్రూప్ | `rg-{environmentName}` | అన్ని వనరులను కలిగి ఉంటుంది |
| Azure OpenAI | `aoai-{resourceToken}` | AI మోడల్ హోస్టింగ్ |

> **గమనిక:** `{resourceToken}` అనేది సబ్‌స్క్రిప్షన్ ID, వాతావరణ పేరు, మరియు ప్రాంతం నుండి ఉత్పన్నమయ్యే ప్రత్యేక స్ట్రింగ్

## త్వరిత ప్రారంభం

### 1. Azure OpenAI అమర్చండి

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

ప్రాంప్ట్ వచ్చినప్పుడు:
- మీ Azure సబ్‌స్క్రిప్షన్ ఎంచుకోండి
- ఒక ప్రాంతం ఎంచుకోండి (సిఫార్సు: `eastus2` లేదా `swedencentral` GPT-5 అందుబాటుకు)
- వాతావరణ పేరు నిర్ధారించండి (డిఫాల్ట్: `langchain4j-dev`)

ఇది సృష్టిస్తుంది:
- GPT-5 మరియు text-embedding-3-small తో Azure OpenAI వనరు
- కనెక్షన్ వివరాలు అవుట్‌పుట్

### 2. కనెక్షన్ వివరాలు పొందండి

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

ఇది చూపిస్తుంది:
- `AZURE_OPENAI_ENDPOINT`: మీ Azure OpenAI ఎండ్‌పాయింట్ URL
- `AZURE_OPENAI_KEY`: ప్రామాణీకరణ కోసం API కీ
- `AZURE_OPENAI_DEPLOYMENT`: చాట్ మోడల్ పేరు (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: ఎంబెడ్డింగ్ మోడల్ పేరు

### 3. అప్లికేషన్లు స్థానికంగా నడపండి

`azd up` ఆదేశం రూట్ డైరెక్టరీలో అవసరమైన అన్ని వాతావరణ వేరియబుల్స్‌తో `.env` ఫైల్‌ను ఆటోమేటిక్‌గా సృష్టిస్తుంది.

**సిఫార్సు:** అన్ని వెబ్ అప్లికేషన్లను ప్రారంభించండి:

**Bash:**
```bash
# రూట్ డైరెక్టరీ నుండి
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# రూట్ డైరెక్టరీ నుండి
cd ../..
.\start-all.ps1
```

లేదా ఒకే మాడ్యూల్‌ను ప్రారంభించండి:

**Bash:**
```bash
# ఉదాహరణ: కేవలం పరిచయ మాడ్యూల్‌ను ప్రారంభించండి
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# ఉదాహరణ: కేవలం పరిచయ మాడ్యూల్‌ను ప్రారంభించండి
cd ../01-introduction
.\start.ps1
```

రెండు స్క్రిప్టులు కూడా `azd up` ద్వారా సృష్టించబడిన రూట్ `.env` ఫైల్ నుండి వాతావరణ వేరియబుల్స్‌ను ఆటోమేటిక్‌గా లోడ్ చేస్తాయి.

## కాన్ఫిగరేషన్

### మోడల్ అమరికలను అనుకూలీకరించడం

మోడల్ అమరికలను మార్చడానికి, `infra/main.bicep` ను సవరించి `openAiDeployments` పారామీటర్‌ను మార్చండి:

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

లభ్యమయ్యే మోడల్స్ మరియు సంస్కరణలు: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure ప్రాంతాలను మార్చడం

వేరే ప్రాంతంలో అమర్చడానికి, `infra/main.bicep` ను సవరించండి:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 అందుబాటును తనిఖీ చేయండి: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep ఫైళ్లలో మార్పులు చేసిన తర్వాత మౌలిక సదుపాయాలను నవీకరించడానికి:

**Bash:**
```bash
# ARM టెంప్లేట్‌ను మళ్లీ నిర్మించండి
az bicep build --file infra/main.bicep

# మార్పులను ముందుగా చూడండి
azd provision --preview

# మార్పులను వర్తింపజేయండి
azd provision
```

**PowerShell:**
```powershell
# ARM టెంప్లేట్‌ను మళ్లీ నిర్మించండి
az bicep build --file infra/main.bicep

# మార్పులను ముందుగా చూడండి
azd provision --preview

# మార్పులను వర్తించండి
azd provision
```

## శుభ్రపరిచే విధానం

అన్ని వనరులను తొలగించడానికి:

**Bash:**
```bash
# అన్ని వనరులను తొలగించండి
azd down

# వాతావరణం సహా అన్నింటినీ తొలగించండి
azd down --purge
```

**PowerShell:**
```powershell
# అన్ని వనరులను తొలగించండి
azd down

# వాతావరణం సహా అన్నింటినీ తొలగించండి
azd down --purge
```

**హెచ్చరిక**: ఇది అన్ని Azure వనరులను శాశ్వతంగా తొలగిస్తుంది.

## ఫైల్ నిర్మాణం

## ఖర్చు ఆప్టిమైజేషన్

### అభివృద్ధి/పరీక్ష

డెవ్/టెస్ట్ వాతావరణాల కోసం, మీరు ఖర్చులను తగ్గించవచ్చు:
- Azure OpenAI కోసం స్టాండర్డ్ టియర్ (S0) ఉపయోగించండి
- `infra/core/ai/cognitiveservices.bicep` లో సామర్థ్యాన్ని తక్కువగా (10K TPM బదులు 20K) సెట్ చేయండి
- ఉపయోగంలో లేనప్పుడు వనరులను తొలగించండి: `azd down`

### ఉత్పత్తి

ఉత్పత్తి కోసం:
- వాడుక ఆధారంగా OpenAI సామర్థ్యాన్ని పెంచండి (50K+ TPM)
- ఎక్కువ అందుబాటుకు జోన్ రెడండెన్సీని ఎనేబుల్ చేయండి
- సరైన మానిటరింగ్ మరియు ఖర్చు అలర్ట్స్ అమలు చేయండి

### ఖర్చు అంచనా

- Azure OpenAI: టోకెన్ ప్రాతిపదికన చెల్లింపు (ఇన్‌పుట్ + అవుట్‌పుట్)
- GPT-5: సుమారు $3-5 ప్రతి 1 మిలియన్ టోకెన్స్ (ప్రస్తుత ధరలను తనిఖీ చేయండి)
- text-embedding-3-small: సుమారు $0.02 ప్రతి 1 మిలియన్ టోకెన్స్

ధరల క్యాల్క్యులేటర్: https://azure.microsoft.com/pricing/calculator/

## మానిటరింగ్

### Azure OpenAI మెట్రిక్స్ చూడండి

Azure పోర్టల్ → మీ OpenAI వనరు → మెట్రిక్స్:
- టోకెన్ ఆధారిత వినియోగం
- HTTP అభ్యర్థన రేటు
- సమాధాన సమయం
- సక్రియ టోకెన్స్

## సమస్య పరిష్కారం

### సమస్య: Azure OpenAI సబ్‌డొమైన్ పేరు ఘర్షణ

**లోపం సందేశం:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**కారణం:**
మీ సబ్‌స్క్రిప్షన్/వాతావరణం నుండి ఉత్పన్నమైన సబ్‌డొమైన్ పేరు ఇప్పటికే ఉపయోగంలో ఉంది, ఇది పూర్తిగా తొలగించని పూర్వపు అమరిక నుండి ఉండవచ్చు.

**పరిష్కారం:**
1. **ఎంపిక 1 - వేరే వాతావరణ పేరు ఉపయోగించండి:**
   
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

2. **ఎంపిక 2 - Azure పోర్టల్ ద్వారా మాన్యువల్ అమరిక:**
   - Azure పోర్టల్ → వనరు సృష్టించండి → Azure OpenAI
   - మీ వనరుకు ప్రత్యేకమైన పేరు ఎంచుకోండి
   - క్రింది మోడల్స్‌ను అమర్చండి:
     - **GPT-5**
     - **text-embedding-3-small** (RAG మాడ్యూల్స్ కోసం)
   - **ముఖ్యమైనది:** మీ అమరిక పేర్లు `.env` కాన్ఫిగరేషన్‌కు సరిపోవాలి
   - అమరిక తర్వాత, "Keys and Endpoint" నుండి మీ ఎండ్‌పాయింట్ మరియు API కీ పొందండి
   - ప్రాజెక్ట్ రూట్‌లో `.env` ఫైల్ సృష్టించండి:
     
     **ఉదాహరణ `.env` ఫైల్:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**మోడల్ అమరిక పేర్ల మార్గదర్శకాలు:**
- సులభమైన, సुसंगతమైన పేర్లు ఉపయోగించండి: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- అమరిక పేర్లు `.env` లో మీరు కాన్ఫిగర్ చేసినదానికి ఖచ్చితంగా సరిపోవాలి
- సాధారణ తప్పు: ఒక పేరుతో మోడల్ సృష్టించి, కోడ్‌లో వేరే పేరును సూచించడం

### సమస్య: ఎంచుకున్న ప్రాంతంలో GPT-5 అందుబాటులో లేదు

**పరిష్కారం:**
- GPT-5 యాక్సెస్ ఉన్న ప్రాంతాన్ని ఎంచుకోండి (ఉదా: eastus, swedencentral)
- అందుబాటును తనిఖీ చేయండి: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### సమస్య: అమరికకు తగిన క్వోటా లేదు

**పరిష్కారం:**
1. Azure పోర్టల్‌లో క్వోటా పెంపు అభ్యర్థించండి
2. లేదా `main.bicep` లో తక్కువ సామర్థ్యం ఉపయోగించండి (ఉదా: capacity: 10)

### సమస్య: స్థానికంగా నడిపేటప్పుడు "వనరు కనుగొనబడలేదు"

**పరిష్కారం:**
1. అమరికను ధృవీకరించండి: `azd env get-values`
2. ఎండ్‌పాయింట్ మరియు కీ సరైనవో తనిఖీ చేయండి
3. Azure పోర్టల్‌లో వనరు గ్రూప్ ఉన్నదో చూసుకోండి

### సమస్య: ప్రామాణీకరణ విఫలం

**పరిష్కారం:**
- `AZURE_OPENAI_API_KEY` సరిగ్గా సెట్ అయ్యిందో తనిఖీ చేయండి
- కీ ఫార్మాట్ 32 అక్షరాల హెక్సాడెసిమల్ స్ట్రింగ్ అయి ఉండాలి
- అవసరమైతే Azure పోర్టల్ నుండి కొత్త కీ పొందండి

### అమరిక విఫలం

**సమస్య**: `azd provision` క్వోటా లేదా సామర్థ్య లోపాలతో విఫలమవుతుంది

**పరిష్కారం**: 
1. వేరే ప్రాంతాన్ని ప్రయత్నించండి - ప్రాంతాలను ఎలా కాన్ఫిగర్ చేయాలో [Changing Azure Regions](../../../../01-introduction/infra) విభాగం చూడండి
2. మీ సబ్‌స్క్రిప్షన్ Azure OpenAI క్వోటా కలిగి ఉందో తనిఖీ చేయండి:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### అప్లికేషన్ కనెక్ట్ అవ్వడం లేదు

**సమస్య**: జావా అప్లికేషన్ కనెక్షన్ లోపాలను చూపిస్తుంది

**పరిష్కారం**:
1. వాతావరణ వేరియబుల్స్ ఎగుమతి అయ్యాయో తనిఖీ చేయండి:
   
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

2. ఎండ్‌పాయింట్ ఫార్మాట్ సరైనదో చూసుకోండి (`https://xxx.openai.azure.com` అయి ఉండాలి)
3. API కీ Azure పోర్టల్ నుండి ప్రాథమిక లేదా ద్వితీయ కీ అయి ఉండాలి

**సమస్య**: Azure OpenAI నుండి 401 Unauthorized

**పరిష్కారం**:
1. Azure పోర్టల్ → Keys and Endpoint నుండి తాజా API కీ పొందండి
2. `AZURE_OPENAI_API_KEY` వాతావరణ వేరియబుల్‌ను మళ్లీ ఎగుమతి చేయండి
3. మోడల్ అమరికలు పూర్తయినవో తనిఖీ చేయండి (Azure పోర్టల్ చూడండి)

### పనితీరు సమస్యలు

**సమస్య**: స్పందన సమయాలు నెమ్మదిగా ఉన్నాయి

**పరిష్కారం**:
1. Azure పోర్టల్ మెట్రిక్స్‌లో OpenAI టోకెన్ వినియోగం మరియు థ్రాట్లింగ్ తనిఖీ చేయండి
2. మీరు పరిమితులను తాకుతున్నట్లయితే TPM సామర్థ్యాన్ని పెంచండి
3. ఎక్కువ రీజనింగ్-ఎఫర్ట్ స్థాయిని (తక్కువ/మధ్య/ఎక్కువ) ఉపయోగించండి

## మౌలిక సదుపాయాల నవీకరణ

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

## భద్రతా సిఫార్సులు

1. **API కీలు ఎప్పుడూ కమిట్ చేయవద్దు** - వాతావరణ వేరియబుల్స్ ఉపయోగించండి
2. **స్థానికంగా .env ఫైళ్లను ఉపయోగించండి** - `.env` ను `.gitignore` లో చేర్చండి
3. **కీలు తరచుగా మార్చండి** - Azure పోర్టల్‌లో కొత్త కీలు సృష్టించండి
4. **ప్రవేశాన్ని పరిమితం చేయండి** - Azure RBAC ఉపయోగించి వనరులకు యాక్సెస్ నియంత్రించండి
5. **వినియోగాన్ని మానిటర్ చేయండి** - Azure పోర్టల్‌లో ఖర్చు అలర్ట్స్ సెట్ చేయండి

## అదనపు వనరులు

- [Azure OpenAI సేవ డాక్యుమెంటేషన్](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 మోడల్ డాక్యుమెంటేషన్](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI డాక్యుమెంటేషన్](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep డాక్యుమెంటేషన్](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI అధికారిక ఇంటిగ్రేషన్](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## మద్దతు

సమస్యల కోసం:
1. పై [సమస్య పరిష్కారం](../../../../01-introduction/infra) విభాగాన్ని తనిఖీ చేయండి
2. Azure పోర్టల్‌లో Azure OpenAI సేవ ఆరోగ్యాన్ని సమీక్షించండి
3. రిపోజిటరీలో ఒక ఇష్యూ తెరవండి

## లైసెన్స్

వివరాలకు రూట్ [LICENSE](../../../../LICENSE) ఫైల్ చూడండి.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**అస్పష్టత**:  
ఈ పత్రాన్ని AI అనువాద సేవ [Co-op Translator](https://github.com/Azure/co-op-translator) ఉపయోగించి అనువదించబడింది. మేము ఖచ్చితత్వానికి ప్రయత్నించినప్పటికీ, ఆటోమేటెడ్ అనువాదాల్లో పొరపాట్లు లేదా తప్పిదాలు ఉండవచ్చు. మూల పత్రం దాని స్వదేశీ భాషలోనే అధికారిక మూలంగా పరిగణించాలి. ముఖ్యమైన సమాచారానికి, ప్రొఫెషనల్ మానవ అనువాదం సిఫార్సు చేయబడుతుంది. ఈ అనువాదం వాడకం వల్ల కలిగే ఏవైనా అపార్థాలు లేదా తప్పుదారితీసే అర్థాలు కోసం మేము బాధ్యత వహించము.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->