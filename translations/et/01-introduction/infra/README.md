# Azure infrastruktuur LangChain4j alustamiseks

## Sisukord

- [Eeltingimused](../../../../01-introduction/infra)
- [Arhitektuur](../../../../01-introduction/infra)
- [Loodud ressursid](../../../../01-introduction/infra)
- [Kiire algus](../../../../01-introduction/infra)
- [Konfiguratsioon](../../../../01-introduction/infra)
- [Halduskäsud](../../../../01-introduction/infra)
- [Kulu optimeerimine](../../../../01-introduction/infra)
- [Jälgimine](../../../../01-introduction/infra)
- [Tõrkeotsing](../../../../01-introduction/infra)
- [Infrastruktuuri uuendamine](../../../../01-introduction/infra)
- [Puhastamine](../../../../01-introduction/infra)
- [Failistruktuur](../../../../01-introduction/infra)
- [Turvalisuse soovitused](../../../../01-introduction/infra)
- [Lisamaterjalid](../../../../01-introduction/infra)

See kataloog sisaldab Azure infrastruktuuri koodi (IaC) Bicep ja Azure Developer CLI (azd) abil Azure OpenAI ressursside juurutamiseks.

## Eeltingimused

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (versioon 2.50.0 või uuem)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (versioon 1.5.0 või uuem)
- Azure tellimus koos õigustega ressursside loomiseks

## Arhitektuur

**Lihtsustatud kohalik arenduskeskkond** – juuruta ainult Azure OpenAI, käivita kõik rakendused lokaalselt.

Infrastruktuur juurutab järgmised Azure ressursid:

### Tehisintellekti teenused
- **Azure OpenAI**: Kognitiivsed teenused kahe mudeli juurutusega:
  - **gpt-5**: Vestluse täitmise mudel (20K TPM maht)
  - **text-embedding-3-small**: Manustamise mudel RAG jaoks (20K TPM maht)

### Kohalik arendus
Kõik Spring Boot rakendused jooksevad lokaalselt sinu masinas:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Loodud ressursid

| Ressursi tüüp | Ressursi nime muster | Eesmärk |
|--------------|----------------------|---------|
| Ressursigrupi nimi | `rg-{environmentName}` | Sisaldab kõiki ressursse |
| Azure OpenAI | `aoai-{resourceToken}` | AI mudeli majutamine |

> **Märkus:** `{resourceToken}` on unikaalne string, mis genereeritakse tellimuse ID, keskkonna nime ja asukoha põhjal

## Kiire algus

### 1. Juuruta Azure OpenAI

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

Kui küsitakse:
- Vali oma Azure tellimus
- Vali asukoht (soovitatav: `eastus2` või `swedencentral` GPT-5 saadavuse jaoks)
- Kinnita keskkonna nimi (vaikimisi: `langchain4j-dev`)

See loob:
- Azure OpenAI ressursi koos GPT-5 ja text-embedding-3-small mudelitega
- Kuvab ühenduse üksikasjad

### 2. Hangi ühenduse üksikasjad

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

See kuvab:
- `AZURE_OPENAI_ENDPOINT`: sinu Azure OpenAI lõpp-punkti URL
- `AZURE_OPENAI_KEY`: API võti autentimiseks
- `AZURE_OPENAI_DEPLOYMENT`: Vestluse mudeli nimi (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Manustamise mudeli nimi

### 3. Käivita rakendused lokaalselt

Käsk `azd up` loob automaatselt juurkataloogi `.env` faili kõigi vajalike keskkonnamuutujatega.

**Soovitus:** Käivita kõik veebirakendused:

**Bash:**
```bash
# Juurekataloogist
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Juurekataloogist
cd ../..
.\start-all.ps1
```

Või käivita üksik moodul:

**Bash:**
```bash
# Näide: Käivita ainult sissejuhatuse moodul
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Näide: Käivita ainult sissejuhatuse moodul
cd ../01-introduction
.\start.ps1
```

Mõlemad skriptid laadivad automaatselt keskkonnamuutujad juurkataloogi `.env` failist, mille lõi `azd up`.

## Konfiguratsioon

### Mudelijuurutuste kohandamine

Mudelite juurutuste muutmiseks redigeeri faili `infra/main.bicep` ja muuda parameetrit `openAiDeployments`:

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

Saadaval olevad mudelid ja versioonid: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure piirkondade muutmine

Teises piirkonnas juurutamiseks redigeeri faili `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Kontrolli GPT-5 saadavust: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Infrastruktuuri uuendamiseks pärast Bicep failide muutmist:

**Bash:**
```bash
# Koosta ARM-mall uuesti
az bicep build --file infra/main.bicep

# Muudatuste eelvaade
azd provision --preview

# Rakenda muudatused
azd provision
```

**PowerShell:**
```powershell
# Koosta ARM-mall uuesti
az bicep build --file infra/main.bicep

# Muudatuste eelvaade
azd provision --preview

# Rakenda muudatused
azd provision
```

## Puhastamine

Kõigi ressursside kustutamiseks:

**Bash:**
```bash
# Kustuta kõik ressursid
azd down

# Kustuta kõik, kaasa arvatud keskkond
azd down --purge
```

**PowerShell:**
```powershell
# Kustuta kõik ressursid
azd down

# Kustuta kõik, kaasa arvatud keskkond
azd down --purge
```

**Hoiatus**: See kustutab kõik Azure ressursid jäädavalt.

## Failistruktuur

## Kulu optimeerimine

### Arendus/testimine
Arendus- ja testkeskkondades saad kulusid vähendada:
- Kasuta Azure OpenAI Standard taset (S0)
- Sea madalam maht (10K TPM asemel 20K) failis `infra/core/ai/cognitiveservices.bicep`
- Kustuta ressursid, kui neid ei kasutata: `azd down`

### Tootmine
Tootmiskeskkonnas:
- Suurenda OpenAI mahtu vastavalt kasutusele (50K+ TPM)
- Luba tsooni redundantsus suurema kättesaadavuse jaoks
- Rakenda korralik jälgimine ja kuluteavitused

### Kulu hinnang
- Azure OpenAI: tasu tokeni põhjal (sisend + väljund)
- GPT-5: umbes $3-5 miljoni tokeni kohta (kontrolli hetke hindu)
- text-embedding-3-small: umbes $0.02 miljoni tokeni kohta

Hinnakalkulaator: https://azure.microsoft.com/pricing/calculator/

## Jälgimine

### Vaata Azure OpenAI mõõdikuid

Mine Azure portaal → Sinu OpenAI ressurss → Mõõdikud:
- Tokeni põhine kasutus
- HTTP päringute sagedus
- Vastuse aeg
- Aktiivsed tokenid

## Tõrkeotsing

### Probleem: Azure OpenAI alamdomeeni nime konflikt

**Veateade:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Põhjus:**
Sinu tellimuse/keskkonna põhjal genereeritud alamdomeeni nimi on juba kasutusel, tõenäoliselt eelmisest juurutusest, mis ei olnud täielikult kustutatud.

**Lahendus:**
1. **Variant 1 - Kasuta teist keskkonna nime:**
   
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

2. **Variant 2 - Käsitsi juurutamine Azure portaalis:**
   - Mine Azure portaal → Loo ressurss → Azure OpenAI
   - Vali oma ressursile unikaalne nimi
   - Juuruta järgmised mudelid:
     - **GPT-5**
     - **text-embedding-3-small** (RAG moodulite jaoks)
   - **Oluline:** Pane tähele oma juurutuste nimesid – need peavad vastama `.env` konfiguratsioonile
   - Pärast juurutamist saa oma lõpp-punkt ja API võti "Võtmed ja lõpp-punkt" alt
   - Loo projekti juurkausta `.env` fail järgmise sisuga:
     
     **Näide `.env` failist:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Mudelijuurutuste nimede juhised:**
- Kasuta lihtsaid ja järjepidevaid nimesid: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Juurutuste nimed peavad täpselt vastama `.env` failis seadistatud nimedele
- Sageli tehakse viga, kui mudel luuakse ühe nimega, aga koodis viidatakse teisele nimele

### Probleem: GPT-5 pole valitud piirkonnas saadaval

**Lahendus:**
- Vali piirkond, kus on GPT-5 ligipääs (nt eastus, swedencentral)
- Kontrolli saadavust: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Probleem: Juurutamiseks pole piisavalt kvota

**Lahendus:**
1. Taotle kvota suurendamist Azure portaalis
2. Või kasuta madalamat mahtu failis `main.bicep` (nt capacity: 10)

### Probleem: "Resource not found" lokaalselt käivitades

**Lahendus:**
1. Kontrolli juurutust: `azd env get-values`
2. Kontrolli, et lõpp-punkt ja võti on õiged
3. Veendu, et ressursigrupp eksisteerib Azure portaalis

### Probleem: Autentimine ebaõnnestus

**Lahendus:**
- Kontrolli, et `AZURE_OPENAI_API_KEY` on õigesti seadistatud
- Võti peab olema 32-kohaline heksadesimaalne string
- Vajadusel saa uus võti Azure portaalist

### Juurutamine ebaõnnestub

**Probleem**: `azd provision` ebaõnnestub kvota või mahu vigadega

**Lahendus**: 
1. Proovi teist piirkonda – vaata [Azure piirkondade muutmine](../../../../01-introduction/infra) juhiseid
2. Kontrolli, kas su tellimusel on Azure OpenAI kvota:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Rakendus ei ühendu

**Probleem**: Java rakendus kuvab ühenduse vigu

**Lahendus**:
1. Kontrolli, et keskkonnamuutujad on eksporditud:
   
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

2. Kontrolli, et lõpp-punkti formaat on õige (peab olema `https://xxx.openai.azure.com`)
3. Veendu, et API võti on Azure portaali peamine või sekundaarne võti

**Probleem**: 401 Unauthorized Azure OpenAI poolt

**Lahendus**:
1. Saa uus API võti Azure portaalist → Võtmed ja lõpp-punkt
2. Ekspordi uuesti `AZURE_OPENAI_API_KEY` keskkonnamuutuja
3. Veendu, et mudelijuurutused on lõpetatud (kontrolli Azure portaali)

### Jõudlusprobleemid

**Probleem**: Aeglane vastuse aeg

**Lahendus**:
1. Kontrolli OpenAI tokeni kasutust ja piiranguid Azure portaali mõõdikutes
2. Suurenda TPM mahtu, kui jõuad piiridesse
3. Kaalu kõrgema mõtlemistaseme kasutamist (madal/keskmine/kõrge)

## Infrastruktuuri uuendamine

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

## Turvalisuse soovitused

1. **Ära kunagi salvesta API võtmeid versioonihaldusesse** – kasuta keskkonnamuutujaid
2. **Kasuta lokaalselt .env faile** – lisa `.env` `.gitignore` faili
3. **Võtmete regulaarne vahetamine** – loo uued võtmed Azure portaalis
4. **Piira ligipääsu** – kasuta Azure RBAC-i, et kontrollida, kes pääseb ressurssidele ligi
5. **Jälgi kasutust** – sea üles kuluteavitused Azure portaalis

## Lisamaterjalid

- [Azure OpenAI teenuse dokumentatsioon](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 mudeli dokumentatsioon](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI dokumentatsioon](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep dokumentatsioon](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI ametlik integratsioon](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Tugi

Probleemide korral:
1. Vaata ülaltoodud [tõrkeotsingu jaotist](../../../../01-introduction/infra)
2. Kontrolli Azure OpenAI teenuse tervist Azure portaalis
3. Ava probleemide arutelu hoidlas

## Litsents

Vaata juurkataloogis olevat [LICENSE](../../../../LICENSE) faili detailide jaoks.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastutusest loobumine**:
See dokument on tõlgitud kasutades tehisintellektil põhinevat tõlketeenust [Co-op Translator](https://github.com/Azure/co-op-translator). Kuigi püüame tagada täpsust, palun arvestage, et automaatsed tõlked võivad sisaldada vigu või ebatäpsusi. Originaaldokument selle emakeeles tuleks pidada autoriteetseks allikaks. Olulise teabe puhul soovitatakse kasutada professionaalset inimtõlget. Me ei vastuta selle tõlke kasutamisest tulenevate arusaamatuste või valesti mõistmiste eest.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->