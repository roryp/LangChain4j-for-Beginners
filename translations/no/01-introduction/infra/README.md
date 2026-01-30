# Azure-infrastruktur for LangChain4j Komme i gang

## Innholdsfortegnelse

- [Forutsetninger](../../../../01-introduction/infra)
- [Arkitektur](../../../../01-introduction/infra)
- [Opprettede ressurser](../../../../01-introduction/infra)
- [Rask start](../../../../01-introduction/infra)
- [Konfigurasjon](../../../../01-introduction/infra)
- [Administrasjonskommandoer](../../../../01-introduction/infra)
- [Kostnadsoptimalisering](../../../../01-introduction/infra)
- [Overvåking](../../../../01-introduction/infra)
- [Feilsøking](../../../../01-introduction/infra)
- [Oppdatering av infrastruktur](../../../../01-introduction/infra)
- [Opprydding](../../../../01-introduction/infra)
- [Filstruktur](../../../../01-introduction/infra)
- [Sikkerhetsanbefalinger](../../../../01-introduction/infra)
- [Ekstra ressurser](../../../../01-introduction/infra)

Denne katalogen inneholder Azure-infrastruktur som kode (IaC) ved bruk av Bicep og Azure Developer CLI (azd) for distribusjon av Azure OpenAI-ressurser.

## Forutsetninger

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (versjon 2.50.0 eller nyere)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (versjon 1.5.0 eller nyere)
- Et Azure-abonnement med tillatelser til å opprette ressurser

## Arkitektur

**Forenklet lokal utviklingsoppsett** - Distribuer kun Azure OpenAI, kjør alle apper lokalt.

Infrastrukturen distribuerer følgende Azure-ressurser:

### AI-tjenester
- **Azure OpenAI**: Cognitive Services med to modellutplasseringer:
  - **gpt-5**: Chat fullføringsmodell (20K TPM kapasitet)
  - **text-embedding-3-small**: Embedding-modell for RAG (20K TPM kapasitet)

### Lokal utvikling
Alle Spring Boot-applikasjoner kjører lokalt på din maskin:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Opprettede ressurser

| Ressurstype | Ressursnavnmønster | Formål |
|--------------|----------------------|---------|
| Ressursgruppe | `rg-{environmentName}` | Inneholder alle ressurser |
| Azure OpenAI | `aoai-{resourceToken}` | Vert for AI-modell |

> **Merk:** `{resourceToken}` er en unik streng generert fra abonnement-ID, miljønavn og lokasjon

## Rask start

### 1. Distribuer Azure OpenAI

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

Når du blir spurt:
- Velg ditt Azure-abonnement
- Velg en lokasjon (anbefalt: `eastus2` eller `swedencentral` for GPT-5 tilgjengelighet)
- Bekreft miljønavnet (standard: `langchain4j-dev`)

Dette vil opprette:
- Azure OpenAI-ressurs med GPT-5 og text-embedding-3-small
- Vise tilkoblingsdetaljer

### 2. Hent tilkoblingsdetaljer

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Dette viser:
- `AZURE_OPENAI_ENDPOINT`: Din Azure OpenAI endepunkt-URL
- `AZURE_OPENAI_KEY`: API-nøkkel for autentisering
- `AZURE_OPENAI_DEPLOYMENT`: Chat-modellnavn (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Embedding-modellnavn

### 3. Kjør applikasjoner lokalt

Kommandoen `azd up` oppretter automatisk en `.env`-fil i rotkatalogen med alle nødvendige miljøvariabler.

**Anbefalt:** Start alle webapplikasjoner:

**Bash:**
```bash
# Fra rotkatalogen
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Fra rotkatalogen
cd ../..
.\start-all.ps1
```

Eller start en enkelt modul:

**Bash:**
```bash
# Eksempel: Start bare introduksjonsmodulen
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Eksempel: Start bare introduksjonsmodulen
cd ../01-introduction
.\start.ps1
```

Begge skriptene laster automatisk miljøvariabler fra rotens `.env`-fil opprettet av `azd up`.

## Konfigurasjon

### Tilpasse modellutplasseringer

For å endre modellutplasseringer, rediger `infra/main.bicep` og modifiser parameteren `openAiDeployments`:

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

Tilgjengelige modeller og versjoner: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Endre Azure-regioner

For å distribuere i en annen region, rediger `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Sjekk GPT-5 tilgjengelighet: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

For å oppdatere infrastrukturen etter endringer i Bicep-filene:

**Bash:**
```bash
# Bygg ARM-malen på nytt
az bicep build --file infra/main.bicep

# Forhåndsvis endringer
azd provision --preview

# Bruk endringer
azd provision
```

**PowerShell:**
```powershell
# Bygg ARM-malen på nytt
az bicep build --file infra/main.bicep

# Forhåndsvis endringer
azd provision --preview

# Bruk endringer
azd provision
```

## Opprydding

For å slette alle ressurser:

**Bash:**
```bash
# Slett alle ressurser
azd down

# Slett alt inkludert miljøet
azd down --purge
```

**PowerShell:**
```powershell
# Slett alle ressurser
azd down

# Slett alt inkludert miljøet
azd down --purge
```

**Advarsel**: Dette vil permanent slette alle Azure-ressurser.

## Filstruktur

## Kostnadsoptimalisering

### Utvikling/testing
For dev/test-miljøer kan du redusere kostnader:
- Bruk Standard-nivå (S0) for Azure OpenAI
- Sett lavere kapasitet (10K TPM i stedet for 20K) i `infra/core/ai/cognitiveservices.bicep`
- Slett ressurser når de ikke er i bruk: `azd down`

### Produksjon
For produksjon:
- Øk OpenAI-kapasitet basert på bruk (50K+ TPM)
- Aktiver sone-redundans for høyere tilgjengelighet
- Implementer riktig overvåking og kostnadsvarsler

### Kostnadsestimering
- Azure OpenAI: Betal per token (input + output)
- GPT-5: ~$3-5 per 1M tokens (sjekk gjeldende priser)
- text-embedding-3-small: ~$0.02 per 1M tokens

Prisberegner: https://azure.microsoft.com/pricing/calculator/

## Overvåking

### Se Azure OpenAI-metrikker

Gå til Azure Portal → Din OpenAI-ressurs → Metrikker:
- Token-basert bruk
- HTTP-forespørselsrate
- Responstid
- Aktive tokens

## Feilsøking

### Problem: Navnekonflikt for Azure OpenAI subdomene

**Feilmelding:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Årsak:**
Subdomenenavnet generert fra abonnement/miljø er allerede i bruk, muligens fra en tidligere distribusjon som ikke ble fullstendig fjernet.

**Løsning:**
1. **Alternativ 1 - Bruk et annet miljønavn:**
   
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

2. **Alternativ 2 - Manuell distribusjon via Azure Portal:**
   - Gå til Azure Portal → Opprett en ressurs → Azure OpenAI
   - Velg et unikt navn for ressursen din
   - Distribuer følgende modeller:
     - **GPT-5**
     - **text-embedding-3-small** (for RAG-moduler)
   - **Viktig:** Noter distribusjonsnavnene dine - de må samsvare med `.env`-konfigurasjonen
   - Etter distribusjon, hent endepunkt og API-nøkkel fra "Keys and Endpoint"
   - Opprett en `.env`-fil i prosjektets rot med:
     
     **Eksempel på `.env`-fil:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Retningslinjer for modellutplassering-navn:**
- Bruk enkle, konsistente navn: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Distribusjonsnavn må samsvare nøyaktig med det du konfigurerer i `.env`
- Vanlig feil: Opprette modell med ett navn, men referere til et annet navn i koden

### Problem: GPT-5 ikke tilgjengelig i valgt region

**Løsning:**
- Velg en region med GPT-5 tilgang (f.eks. eastus, swedencentral)
- Sjekk tilgjengelighet: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Problem: Utilstrekkelig kvote for distribusjon

**Løsning:**
1. Be om kvoteøkning i Azure Portal
2. Eller bruk lavere kapasitet i `main.bicep` (f.eks. capacity: 10)

### Problem: "Resource not found" ved lokal kjøring

**Løsning:**
1. Verifiser distribusjon: `azd env get-values`
2. Sjekk at endepunkt og nøkkel er korrekte
3. Sørg for at ressursgruppen finnes i Azure Portal

### Problem: Autentisering feilet

**Løsning:**
- Verifiser at `AZURE_OPENAI_API_KEY` er satt korrekt
- Nøkkelformat skal være 32-tegns heksadesimal streng
- Hent ny nøkkel fra Azure Portal om nødvendig

### Distribusjon feiler

**Problem**: `azd provision` feiler med kvote- eller kapasitetsfeil

**Løsning**: 
1. Prøv en annen region - Se [Endre Azure-regioner](../../../../01-introduction/infra) for hvordan du konfigurerer regioner
2. Sjekk at abonnementet ditt har Azure OpenAI-kvote:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Applikasjon kobler ikke til

**Problem**: Java-applikasjon viser tilkoblingsfeil

**Løsning**:
1. Verifiser at miljøvariabler er eksportert:
   
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

2. Sjekk at endepunktformatet er korrekt (skal være `https://xxx.openai.azure.com`)
3. Verifiser at API-nøkkelen er primær- eller sekundærnøkkel fra Azure Portal

**Problem**: 401 Unauthorized fra Azure OpenAI

**Løsning**:
1. Skaff en ny API-nøkkel fra Azure Portal → Keys and Endpoint
2. Eksporter `AZURE_OPENAI_API_KEY` miljøvariabel på nytt
3. Sørg for at modellutplasseringene er fullført (sjekk Azure Portal)

### Ytelsesproblemer

**Problem**: Langsomme responstider

**Løsning**:
1. Sjekk OpenAI token-bruk og throttling i Azure Portal-metrikker
2. Øk TPM-kapasitet hvis du når grenser
3. Vurder å bruke høyere reasoning-effort nivå (lav/middels/høy)

## Oppdatering av infrastruktur

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

## Sikkerhetsanbefalinger

1. **Aldri sjekk inn API-nøkler i kode** - Bruk miljøvariabler
2. **Bruk .env-filer lokalt** - Legg til `.env` i `.gitignore`
3. **Roter nøkler regelmessig** - Generer nye nøkler i Azure Portal
4. **Begrens tilgang** - Bruk Azure RBAC for å kontrollere hvem som kan få tilgang til ressurser
5. **Overvåk bruk** - Sett opp kostnadsvarsler i Azure Portal

## Ekstra ressurser

- [Azure OpenAI Service Dokumentasjon](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 Modell Dokumentasjon](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI Dokumentasjon](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep Dokumentasjon](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI Offisiell Integrasjon](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Support

For problemer:
1. Sjekk [feilsøkingsseksjonen](../../../../01-introduction/infra) ovenfor
2. Gå gjennom Azure OpenAI tjenestehelse i Azure Portal
3. Åpne en sak i repository

## Lisens

Se rotmappen [LICENSE](../../../../LICENSE) for detaljer.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokumentet er oversatt ved hjelp av AI-oversettelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selv om vi streber etter nøyaktighet, vennligst vær oppmerksom på at automatiske oversettelser kan inneholde feil eller unøyaktigheter. Det opprinnelige dokumentet på originalspråket skal anses som den autoritative kilden. For kritisk informasjon anbefales profesjonell menneskelig oversettelse. Vi er ikke ansvarlige for eventuelle misforståelser eller feiltolkninger som oppstår ved bruk av denne oversettelsen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->