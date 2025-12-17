<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:28:24+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "lt"
}
-->
# Azure infrastruktūra LangChain4j pradžiai

## Turinys

- [Reikalavimai](../../../../01-introduction/infra)
- [Architektūra](../../../../01-introduction/infra)
- [Sukurti ištekliai](../../../../01-introduction/infra)
- [Greitas pradėjimas](../../../../01-introduction/infra)
- [Konfigūracija](../../../../01-introduction/infra)
- [Valdymo komandos](../../../../01-introduction/infra)
- [Sąnaudų optimizavimas](../../../../01-introduction/infra)
- [Stebėjimas](../../../../01-introduction/infra)
- [Trikčių šalinimas](../../../../01-introduction/infra)
- [Infrastruktūros atnaujinimas](../../../../01-introduction/infra)
- [Išvalymas](../../../../01-introduction/infra)
- [Failų struktūra](../../../../01-introduction/infra)
- [Saugumo rekomendacijos](../../../../01-introduction/infra)
- [Papildomi ištekliai](../../../../01-introduction/infra)

Šiame kataloge yra Azure infrastruktūra kaip kodas (IaC) naudojant Bicep ir Azure Developer CLI (azd) Azure OpenAI išteklių diegimui.

## Reikalavimai

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (2.50.0 arba naujesnė versija)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (1.5.0 arba naujesnė versija)
- Azure prenumerata su teisėmis kurti išteklius

## Architektūra

**Supaprastintas vietinio vystymo nustatymas** – diegiamas tik Azure OpenAI, visos programos paleidžiamos vietoje.

Infrastruktūra diegia šiuos Azure išteklius:

### DI paslaugos
- **Azure OpenAI**: Kognityvinės paslaugos su dviem modelių diegimais:
  - **gpt-5**: Pokalbių užbaigimo modelis (20K TPM talpa)
  - **text-embedding-3-small**: Įterpimo modelis RAG (20K TPM talpa)

### Vietinis vystymas
Visos Spring Boot programos veikia vietoje jūsų kompiuteryje:
- 01-introduction (prievadas 8080)
- 02-prompt-engineering (prievadas 8083)
- 03-rag (prievadas 8081)
- 04-tools (prievadas 8084)

## Sukurti ištekliai

| Išteklių tipas | Išteklių pavadinimo šablonas | Paskirtis |
|--------------|----------------------|---------|
| Išteklių grupė | `rg-{environmentName}` | Laiko visus išteklius |
| Azure OpenAI | `aoai-{resourceToken}` | DI modelių talpinimas |

> **Pastaba:** `{resourceToken}` yra unikalus eilutės identifikatorius, sukurtas iš prenumeratos ID, aplinkos pavadinimo ir vietos

## Greitas pradėjimas

### 1. Diegti Azure OpenAI

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

Kai bus paprašyta:
- Pasirinkite savo Azure prenumeratą
- Pasirinkite vietą (rekomenduojama: `eastus2` arba `swedencentral` dėl GPT-5 prieinamumo)
- Patvirtinkite aplinkos pavadinimą (numatytasis: `langchain4j-dev`)

Tai sukurs:
- Azure OpenAI išteklių su GPT-5 ir text-embedding-3-small
- Išves prisijungimo duomenis

### 2. Gauti prisijungimo duomenis

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Tai parodys:
- `AZURE_OPENAI_ENDPOINT`: Jūsų Azure OpenAI galinio taško URL
- `AZURE_OPENAI_KEY`: API raktas autentifikacijai
- `AZURE_OPENAI_DEPLOYMENT`: Pokalbių modelio pavadinimas (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Įterpimo modelio pavadinimas

### 3. Paleisti programas vietoje

Komanda `azd up` automatiškai sukuria `.env` failą šakniniame kataloge su visais reikalingais aplinkos kintamaisiais.

**Rekomenduojama:** Paleiskite visas žiniatinklio programas:

**Bash:**
```bash
# Iš šakninių katalogų
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Iš šakninių katalogų
cd ../..
.\start-all.ps1
```

Arba paleiskite vieną modulį:

**Bash:**
```bash
# Pavyzdys: Pradėkite tik įvadinį modulį
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Pavyzdys: Pradėkite tik įvadinį modulį
cd ../01-introduction
.\start.ps1
```

Abu scenarijai automatiškai įkelia aplinkos kintamuosius iš šakniniame kataloge sukurto `.env` failo, kurį sukūrė `azd up`.

## Konfigūracija

### Modelių diegimų pritaikymas

Norėdami pakeisti modelių diegimus, redaguokite `infra/main.bicep` ir modifikuokite parametrą `openAiDeployments`:

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

Galimi modeliai ir versijos: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure regionų keitimas

Norėdami diegti kitame regione, redaguokite `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Patikrinkite GPT-5 prieinamumą: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Norėdami atnaujinti infrastruktūrą po Bicep failų pakeitimų:

**Bash:**
```bash
# Perstatyti ARM šabloną
az bicep build --file infra/main.bicep

# Peržiūrėti pakeitimus
azd provision --preview

# Taikyti pakeitimus
azd provision
```

**PowerShell:**
```powershell
# Perstatyti ARM šabloną
az bicep build --file infra/main.bicep

# Peržiūrėti pakeitimus
azd provision --preview

# Taikyti pakeitimus
azd provision
```

## Išvalymas

Norėdami ištrinti visus išteklius:

**Bash:**
```bash
# Ištrinti visus išteklius
azd down

# Ištrinti viską, įskaitant aplinką
azd down --purge
```

**PowerShell:**
```powershell
# Ištrinti visus išteklius
azd down

# Ištrinti viską, įskaitant aplinką
azd down --purge
```

**Įspėjimas**: Tai visam laikui ištrins visus Azure išteklius.

## Failų struktūra

## Sąnaudų optimizavimas

### Vystymas / testavimas
Vystymo/testavimo aplinkoms galite sumažinti sąnaudas:
- Naudokite Standartinį lygį (S0) Azure OpenAI
- Nustatykite mažesnę talpą (10K TPM vietoje 20K) faile `infra/core/ai/cognitiveservices.bicep`
- Ištrinkite išteklius, kai nenaudojate: `azd down`

### Gamyba
Gamybai:
- Padidinkite OpenAI talpą pagal naudojimą (50K+ TPM)
- Įjunkite zonų atsarginumą didesniam prieinamumui
- Įgyvendinkite tinkamą stebėjimą ir sąnaudų įspėjimus

### Sąnaudų įvertinimas
- Azure OpenAI: mokestis už tokeną (įvestį + išvestį)
- GPT-5: apie 3-5 USD už 1M tokenų (patikrinkite dabartines kainas)
- text-embedding-3-small: apie 0,02 USD už 1M tokenų

Kainų skaičiuoklė: https://azure.microsoft.com/pricing/calculator/

## Stebėjimas

### Peržiūrėti Azure OpenAI metrikas

Eikite į Azure Portal → Jūsų OpenAI išteklius → Metrikos:
- Naudojimas pagal tokenus
- HTTP užklausų dažnis
- Atsakymo laikas
- Aktyvūs tokenai

## Trikčių šalinimas

### Problema: Azure OpenAI subdomeno pavadinimo konfliktas

**Klaidos pranešimas:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Priežastis:**
Subdomeno pavadinimas, sukurtas iš jūsų prenumeratos/aplinkos, jau naudojamas, galbūt dėl ankstesnio diegimo, kuris nebuvo visiškai pašalintas.

**Sprendimas:**
1. **1 variantas – naudokite kitą aplinkos pavadinimą:**
   
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

2. **2 variantas – rankinis diegimas per Azure Portal:**
   - Eikite į Azure Portal → Kurti išteklių → Azure OpenAI
   - Pasirinkite unikalų išteklių pavadinimą
   - Diegkite šiuos modelius:
     - **GPT-5**
     - **text-embedding-3-small** (RAG moduliams)
   - **Svarbu:** Užsirašykite diegimo pavadinimus – jie turi atitikti `.env` konfigūraciją
   - Po diegimo gaukite galinį tašką ir API raktą iš „Keys and Endpoint“
   - Sukurkite `.env` failą projekto šaknyje su:

     **Pavyzdinis `.env` failas:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Modelių diegimo pavadinimų gairės:**
- Naudokite paprastus, nuoseklius pavadinimus: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Diegimo pavadinimai turi tiksliai atitikti tai, ką nurodote `.env`
- Dažna klaida: modelio sukūrimas su vienu pavadinimu, bet kodo nuoroda į kitą pavadinimą

### Problema: GPT-5 neprieinamas pasirinktoje zonoje

**Sprendimas:**
- Pasirinkite regioną su GPT-5 prieiga (pvz., eastus, swedencentral)
- Patikrinkite prieinamumą: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Problema: Nepakanka kvotos diegimui

**Sprendimas:**
1. Prašykite kvotos padidinimo Azure Portal
2. Arba naudokite mažesnę talpą faile `main.bicep` (pvz., capacity: 10)

### Problema: „Resource not found“ paleidžiant vietoje

**Sprendimas:**
1. Patikrinkite diegimą: `azd env get-values`
2. Patikrinkite, ar galinis taškas ir raktas teisingi
3. Įsitikinkite, kad išteklių grupė egzistuoja Azure Portal

### Problema: Autentifikacija nepavyko

**Sprendimas:**
- Patikrinkite, ar `AZURE_OPENAI_API_KEY` nustatytas teisingai
- Raktas turi būti 32 simbolių šešioliktainis eilutė
- Jei reikia, gaukite naują raktą iš Azure Portal

### Diegimas nepavyksta

**Problema**: `azd provision` nepavyksta dėl kvotos ar talpos klaidų

**Sprendimas**: 
1. Išbandykite kitą regioną – žr. [Azure regionų keitimas](../../../../01-introduction/infra) skyrių, kaip konfigūruoti regionus
2. Patikrinkite, ar jūsų prenumerata turi Azure OpenAI kvotą:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Programa neprisijungia

**Problema**: Java programa rodo prisijungimo klaidas

**Sprendimas**:
1. Patikrinkite, ar aplinkos kintamieji eksportuoti:
   
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

2. Patikrinkite, ar galinio taško formatas teisingas (turi būti `https://xxx.openai.azure.com`)
3. Patikrinkite, ar API raktas yra pagrindinis arba antrinis raktas iš Azure Portal

**Problema**: 401 Unauthorized iš Azure OpenAI

**Sprendimas**:
1. Gaukite naują API raktą iš Azure Portal → Keys and Endpoint
2. Iš naujo eksportuokite `AZURE_OPENAI_API_KEY` aplinkos kintamąjį
3. Įsitikinkite, kad modelių diegimai baigti (patikrinkite Azure Portal)

### Veikimo problemos

**Problema**: Lėtas atsakymo laikas

**Sprendimas**:
1. Patikrinkite OpenAI tokenų naudojimą ir ribojimą Azure Portal metrikose
2. Padidinkite TPM talpą, jei pasiekiate ribas
3. Apsvarstykite galimybę naudoti aukštesnį mąstymo lygį (žemas/vidutinis/aukštas)

## Infrastruktūros atnaujinimas

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

## Saugumo rekomendacijos

1. **Niekada neįtraukite API raktų į versijų valdymą** – naudokite aplinkos kintamuosius
2. **Naudokite .env failus vietoje** – pridėkite `.env` į `.gitignore`
3. **Reguliariai keiskite raktus** – generuokite naujus raktus Azure Portal
4. **Ribokite prieigą** – naudokite Azure RBAC, kad kontroliuotumėte, kas gali pasiekti išteklius
5. **Stebėkite naudojimą** – nustatykite sąnaudų įspėjimus Azure Portal

## Papildomi ištekliai

- [Azure OpenAI paslaugos dokumentacija](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 modelio dokumentacija](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI dokumentacija](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep dokumentacija](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI oficiali integracija](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Pagalba

Dėl problemų:
1. Peržiūrėkite aukščiau esantį [trikčių šalinimo skyrių](../../../../01-introduction/infra)
2. Patikrinkite Azure OpenAI paslaugos būklę Azure Portal
3. Atidarykite problemą repozitorijoje

## Licencija

Daugiau informacijos rasite šakniniame [LICENSE](../../../../LICENSE) faile.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Atsakomybės apribojimas**:  
Šis dokumentas buvo išverstas naudojant dirbtinio intelekto vertimo paslaugą [Co-op Translator](https://github.com/Azure/co-op-translator). Nors stengiamės užtikrinti tikslumą, prašome atkreipti dėmesį, kad automatiniai vertimai gali turėti klaidų ar netikslumų. Originalus dokumentas gimtąja kalba turėtų būti laikomas autoritetingu šaltiniu. Svarbiai informacijai rekomenduojamas profesionalus žmogaus vertimas. Mes neatsakome už bet kokius nesusipratimus ar neteisingus aiškinimus, kylančius dėl šio vertimo naudojimo.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->