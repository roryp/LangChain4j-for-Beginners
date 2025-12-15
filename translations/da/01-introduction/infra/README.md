<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:04:43+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "da"
}
-->
# Azure Infrastruktur for LangChain4j Kom godt i gang

## Indholdsfortegnelse

- [Forudsætninger](../../../../01-introduction/infra)
- [Arkitektur](../../../../01-introduction/infra)
- [Oprettede Ressourcer](../../../../01-introduction/infra)
- [Hurtig Start](../../../../01-introduction/infra)
- [Konfiguration](../../../../01-introduction/infra)
- [Administrationskommandoer](../../../../01-introduction/infra)
- [Omkostningsoptimering](../../../../01-introduction/infra)
- [Overvågning](../../../../01-introduction/infra)
- [Fejlfinding](../../../../01-introduction/infra)
- [Opdatering af Infrastruktur](../../../../01-introduction/infra)
- [Ryd Op](../../../../01-introduction/infra)
- [Filstruktur](../../../../01-introduction/infra)
- [Sikkerhedsanbefalinger](../../../../01-introduction/infra)
- [Yderligere Ressourcer](../../../../01-introduction/infra)

Denne mappe indeholder Azure-infrastruktur som kode (IaC) ved brug af Bicep og Azure Developer CLI (azd) til udrulning af Azure OpenAI-ressourcer.

## Forudsætninger

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (version 2.50.0 eller nyere)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (version 1.5.0 eller nyere)
- Et Azure-abonnement med tilladelser til at oprette ressourcer

## Arkitektur

**Forenklet lokal udviklingsopsætning** - Udrul kun Azure OpenAI, kør alle apps lokalt.

Infrastrukturen udruller følgende Azure-ressourcer:

### AI-tjenester
- **Azure OpenAI**: Cognitive Services med to modeludrulninger:
  - **gpt-5**: Chat completion-model (20K TPM kapacitet)
  - **text-embedding-3-small**: Embedding-model til RAG (20K TPM kapacitet)

### Lokal udvikling
Alle Spring Boot-applikationer kører lokalt på din maskine:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Oprettede Ressourcer

| Ressourcetype | Ressourcenavn Mønster | Formål |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | Indeholder alle ressourcer |
| Azure OpenAI | `aoai-{resourceToken}` | Hosting af AI-model |

> **Bemærk:** `{resourceToken}` er en unik streng genereret ud fra abonnement-ID, miljønavn og placering

## Hurtig Start

### 1. Udrul Azure OpenAI

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

Når du bliver bedt om det:
- Vælg dit Azure-abonnement
- Vælg en placering (anbefalet: `eastus2` eller `swedencentral` for GPT-5 tilgængelighed)
- Bekræft miljønavnet (standard: `langchain4j-dev`)

Dette vil oprette:
- Azure OpenAI-ressource med GPT-5 og text-embedding-3-small
- Udskrive forbindelsesdetaljer

### 2. Hent forbindelsesdetaljer

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Dette viser:
- `AZURE_OPENAI_ENDPOINT`: Din Azure OpenAI-endpoint-URL
- `AZURE_OPENAI_KEY`: API-nøgle til autentificering
- `AZURE_OPENAI_DEPLOYMENT`: Chat-modelnavn (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Embedding-modelnavn

### 3. Kør applikationer lokalt

Kommandoen `azd up` opretter automatisk en `.env`-fil i rodmappen med alle nødvendige miljøvariabler.

**Anbefalet:** Start alle webapplikationer:

**Bash:**
```bash
# Fra rodmappen
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Fra rodmappen
cd ../..
.\start-all.ps1
```

Eller start et enkelt modul:

**Bash:**
```bash
# Eksempel: Start kun introduktionsmodulet
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Eksempel: Start kun introduktionsmodulet
cd ../01-introduction
.\start.ps1
```

Begge scripts indlæser automatisk miljøvariabler fra rodens `.env`-fil oprettet af `azd up`.

## Konfiguration

### Tilpasning af modeludrulninger

For at ændre modeludrulninger, rediger `infra/main.bicep` og modificer parameteren `openAiDeployments`:

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

Tilgængelige modeller og versioner: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Ændring af Azure-regioner

For at udrulle i en anden region, rediger `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Tjek GPT-5 tilgængelighed: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

For at opdatere infrastrukturen efter ændringer i Bicep-filer:

**Bash:**
```bash
# Genopbyg ARM-skabelonen
az bicep build --file infra/main.bicep

# Forhåndsvis ændringer
azd provision --preview

# Anvend ændringer
azd provision
```

**PowerShell:**
```powershell
# Genopbyg ARM-skabelonen
az bicep build --file infra/main.bicep

# Forhåndsvis ændringer
azd provision --preview

# Anvend ændringer
azd provision
```

## Ryd Op

For at slette alle ressourcer:

**Bash:**
```bash
# Slet alle ressourcer
azd down

# Slet alt inklusive miljøet
azd down --purge
```

**PowerShell:**
```powershell
# Slet alle ressourcer
azd down

# Slet alt inklusive miljøet
azd down --purge
```

**Advarsel**: Dette vil permanent slette alle Azure-ressourcer.

## Filstruktur

## Omkostningsoptimering

### Udvikling/Test
For dev/test-miljøer kan du reducere omkostninger:
- Brug Standard tier (S0) for Azure OpenAI
- Sæt lavere kapacitet (10K TPM i stedet for 20K) i `infra/core/ai/cognitiveservices.bicep`
- Slet ressourcer når de ikke er i brug: `azd down`

### Produktion
For produktion:
- Forøg OpenAI kapacitet baseret på brug (50K+ TPM)
- Aktiver zoneredundans for højere tilgængelighed
- Implementer korrekt overvågning og omkostningsalarmer

### Omkostningsestimering
- Azure OpenAI: Betal pr. token (input + output)
- GPT-5: Ca. $3-5 pr. 1M tokens (tjek aktuel pris)
- text-embedding-3-small: Ca. $0.02 pr. 1M tokens

Prisberegner: https://azure.microsoft.com/pricing/calculator/

## Overvågning

### Se Azure OpenAI-metrikker

Gå til Azure Portal → Din OpenAI-ressource → Metrikker:
- Token-baseret udnyttelse
- HTTP-anmodningsrate
- Responstid
- Aktive tokens

## Fejlfinding

### Problem: Konflikt med Azure OpenAI subdomænenavn

**Fejlmeddelelse:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Årsag:**
Subdomænenavnet genereret ud fra dit abonnement/miljø er allerede i brug, muligvis fra en tidligere udrulning, der ikke blev fuldstændigt fjernet.

**Løsning:**
1. **Mulighed 1 - Brug et andet miljønavn:**
   
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

2. **Mulighed 2 - Manuel udrulning via Azure Portal:**
   - Gå til Azure Portal → Opret en ressource → Azure OpenAI
   - Vælg et unikt navn til din ressource
   - Udrul følgende modeller:
     - **GPT-5**
     - **text-embedding-3-small** (til RAG-moduler)
   - **Vigtigt:** Noter dine udrulningsnavne - de skal matche `.env`-konfigurationen
   - Efter udrulning, hent dit endpoint og API-nøgle fra "Keys and Endpoint"
   - Opret en `.env`-fil i projektets rod med:
     
     **Eksempel på `.env`-fil:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Retningslinjer for navngivning af modeludrulning:**
- Brug simple, konsistente navne: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Udrulningsnavne skal matche præcist det, du konfigurerer i `.env`
- Almindelig fejl: Oprette model med ét navn, men referere til et andet navn i koden

### Problem: GPT-5 ikke tilgængelig i valgt region

**Løsning:**
- Vælg en region med GPT-5 adgang (f.eks. eastus, swedencentral)
- Tjek tilgængelighed: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Problem: Utilstrækkelig kvote til udrulning

**Løsning:**
1. Anmod om kvoteforhøjelse i Azure Portal
2. Eller brug lavere kapacitet i `main.bicep` (f.eks. capacity: 10)

### Problem: "Resource not found" ved lokal kørsel

**Løsning:**
1. Bekræft udrulning: `azd env get-values`
2. Tjek at endpoint og nøgle er korrekte
3. Sørg for at resource group findes i Azure Portal

### Problem: Autentificering mislykkedes

**Løsning:**
- Bekræft at `AZURE_OPENAI_API_KEY` er korrekt sat
- Nøgleformat skal være en 32-tegns hexadecimalt streng
- Hent ny nøgle fra Azure Portal om nødvendigt

### Udrulning fejler

**Problem**: `azd provision` fejler med kvote- eller kapacitetsfejl

**Løsning**: 
1. Prøv en anden region - Se [Ændring af Azure-regioner](../../../../01-introduction/infra) sektionen for hvordan man konfigurerer regioner
2. Tjek at dit abonnement har Azure OpenAI-kvote:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Applikation forbinder ikke

**Problem**: Java-applikation viser forbindelsesfejl

**Løsning**:
1. Bekræft at miljøvariabler er eksporteret:
   
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

2. Tjek at endpoint-formatet er korrekt (skal være `https://xxx.openai.azure.com`)
3. Bekræft at API-nøglen er den primære eller sekundære nøgle fra Azure Portal

**Problem**: 401 Unauthorized fra Azure OpenAI

**Løsning**:
1. Hent en ny API-nøgle fra Azure Portal → Keys and Endpoint
2. Eksporter `AZURE_OPENAI_API_KEY` miljøvariablen igen
3. Sørg for at modeludrulninger er fuldførte (tjek Azure Portal)

### Ydeevneproblemer

**Problem**: Langsomme svartider

**Løsning**:
1. Tjek OpenAI token-brug og throttling i Azure Portal metrikker
2. Forøg TPM kapacitet hvis du rammer grænser
3. Overvej at bruge et højere reasoning-effort niveau (lav/mellem/høj)

## Opdatering af Infrastruktur

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

## Sikkerhedsanbefalinger

1. **Aldrig commit API-nøgler** - Brug miljøvariabler
2. **Brug .env-filer lokalt** - Tilføj `.env` til `.gitignore`
3. **Roter nøgler regelmæssigt** - Generer nye nøgler i Azure Portal
4. **Begræns adgang** - Brug Azure RBAC til at styre hvem der kan tilgå ressourcer
5. **Overvåg brug** - Opsæt omkostningsalarmer i Azure Portal

## Yderligere Ressourcer

- [Azure OpenAI Service Dokumentation](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 Model Dokumentation](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI Dokumentation](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep Dokumentation](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI Officiel Integration](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Support

For problemer:
1. Tjek [fejlfinding sektionen](../../../../01-introduction/infra) ovenfor
2. Gennemgå Azure OpenAI service status i Azure Portal
3. Opret en issue i repository

## Licens

Se rodmappen [LICENSE](../../../../LICENSE) fil for detaljer.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokument er blevet oversat ved hjælp af AI-oversættelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selvom vi bestræber os på nøjagtighed, bedes du være opmærksom på, at automatiserede oversættelser kan indeholde fejl eller unøjagtigheder. Det oprindelige dokument på dets modersmål bør betragtes som den autoritative kilde. For kritisk information anbefales professionel menneskelig oversættelse. Vi påtager os intet ansvar for misforståelser eller fejltolkninger, der opstår som følge af brugen af denne oversættelse.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->