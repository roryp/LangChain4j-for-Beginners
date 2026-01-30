# Azure-infrastruktur för LangChain4j Kom igång

## Innehållsförteckning

- [Förutsättningar](../../../../01-introduction/infra)
- [Arkitektur](../../../../01-introduction/infra)
- [Skapade resurser](../../../../01-introduction/infra)
- [Snabbstart](../../../../01-introduction/infra)
- [Konfiguration](../../../../01-introduction/infra)
- [Hantera kommandon](../../../../01-introduction/infra)
- [Kostnadsoptimering](../../../../01-introduction/infra)
- [Övervakning](../../../../01-introduction/infra)
- [Felsökning](../../../../01-introduction/infra)
- [Uppdatera infrastruktur](../../../../01-introduction/infra)
- [Rensa upp](../../../../01-introduction/infra)
- [Filstruktur](../../../../01-introduction/infra)
- [Säkerhetsrekommendationer](../../../../01-introduction/infra)
- [Ytterligare resurser](../../../../01-introduction/infra)

Denna katalog innehåller Azure-infrastruktur som kod (IaC) med Bicep och Azure Developer CLI (azd) för att distribuera Azure OpenAI-resurser.

## Förutsättningar

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (version 2.50.0 eller senare)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (version 1.5.0 eller senare)
- Ett Azure-abonnemang med behörighet att skapa resurser

## Arkitektur

**Förenklad lokal utvecklingsmiljö** – Distribuera endast Azure OpenAI, kör alla appar lokalt.

Infrastrukturen distribuerar följande Azure-resurser:

### AI-tjänster
- **Azure OpenAI**: Cognitive Services med två modellutplaceringar:
  - **gpt-5**: Chattkompletteringsmodell (kapacitet 20K TPM)
  - **text-embedding-3-small**: Inbäddningsmodell för RAG (kapacitet 20K TPM)

### Lokal utveckling
Alla Spring Boot-applikationer körs lokalt på din dator:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Skapade resurser

| Resurstyp | Resursnamnsmönster | Syfte |
|--------------|----------------------|---------|
| Resursgrupp | `rg-{environmentName}` | Innehåller alla resurser |
| Azure OpenAI | `aoai-{resourceToken}` | AI-modellhosting |

> **Notera:** `{resourceToken}` är en unik sträng genererad från prenumerations-ID, miljönamn och plats

## Snabbstart

### 1. Distribuera Azure OpenAI

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

När du uppmanas:
- Välj ditt Azure-abonnemang
- Välj en plats (rekommenderat: `eastus2` eller `swedencentral` för GPT-5 tillgänglighet)
- Bekräfta miljönamnet (standard: `langchain4j-dev`)

Detta skapar:
- Azure OpenAI-resurs med GPT-5 och text-embedding-3-small
- Utdata med anslutningsdetaljer

### 2. Hämta anslutningsdetaljer

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Detta visar:
- `AZURE_OPENAI_ENDPOINT`: Din Azure OpenAI-endpoint-URL
- `AZURE_OPENAI_KEY`: API-nyckel för autentisering
- `AZURE_OPENAI_DEPLOYMENT`: Chattmodellnamn (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Inbäddningsmodellnamn

### 3. Kör applikationer lokalt

Kommandot `azd up` skapar automatiskt en `.env`-fil i rotkatalogen med alla nödvändiga miljövariabler.

**Rekommenderat:** Starta alla webbapplikationer:

**Bash:**
```bash
# Från rotkatalogen
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Från rotkatalogen
cd ../..
.\start-all.ps1
```

Eller starta en enskild modul:

**Bash:**
```bash
# Exempel: Starta bara introduktionsmodulen
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Exempel: Starta bara introduktionsmodulen
cd ../01-introduction
.\start.ps1
```

Båda skripten laddar automatiskt miljövariabler från rotens `.env`-fil som skapats av `azd up`.

## Konfiguration

### Anpassa modellutplaceringar

För att ändra modellutplaceringar, redigera `infra/main.bicep` och ändra parametern `openAiDeployments`:

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

Tillgängliga modeller och versioner: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Byta Azure-regioner

För att distribuera i en annan region, redigera `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Kontrollera GPT-5 tillgänglighet: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

För att uppdatera infrastrukturen efter ändringar i Bicep-filer:

**Bash:**
```bash
# Bygg om ARM-mallen
az bicep build --file infra/main.bicep

# Förhandsgranska ändringar
azd provision --preview

# Tillämpa ändringar
azd provision
```

**PowerShell:**
```powershell
# Bygg om ARM-mallen
az bicep build --file infra/main.bicep

# Förhandsgranska ändringar
azd provision --preview

# Tillämpa ändringar
azd provision
```

## Rensa upp

För att ta bort alla resurser:

**Bash:**
```bash
# Radera alla resurser
azd down

# Radera allt inklusive miljön
azd down --purge
```

**PowerShell:**
```powershell
# Radera alla resurser
azd down

# Radera allt inklusive miljön
azd down --purge
```

**Varning**: Detta kommer permanent att ta bort alla Azure-resurser.

## Filstruktur

## Kostnadsoptimering

### Utveckling/Test
För utvecklings-/testmiljöer kan du minska kostnader:
- Använd Standardnivå (S0) för Azure OpenAI
- Sätt lägre kapacitet (10K TPM istället för 20K) i `infra/core/ai/cognitiveservices.bicep`
- Ta bort resurser när de inte används: `azd down`

### Produktion
För produktion:
- Öka OpenAI-kapaciteten baserat på användning (50K+ TPM)
- Aktivera zonredundans för högre tillgänglighet
- Implementera korrekt övervakning och kostnadsvarningar

### Kostnadsuppskattning
- Azure OpenAI: Betala per token (inmatning + utmatning)
- GPT-5: ~3-5 USD per 1M tokens (kontrollera aktuell prissättning)
- text-embedding-3-small: ~0,02 USD per 1M tokens

Prisräknare: https://azure.microsoft.com/pricing/calculator/

## Övervakning

### Visa Azure OpenAI-metriker

Gå till Azure Portal → Din OpenAI-resurs → Metriker:
- Tokenbaserad användning
- HTTP-förfrågningsfrekvens
- Svarstid
- Aktiva tokens

## Felsökning

### Problem: Namnkonflikt för Azure OpenAI-underdomän

**Felmeddelande:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Orsak:**
Underdomännamnet som genererats från din prenumeration/miljö används redan, troligen från en tidigare distribution som inte rensats helt.

**Lösning:**
1. **Alternativ 1 - Använd ett annat miljönamn:**
   
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

2. **Alternativ 2 - Manuell distribution via Azure Portal:**
   - Gå till Azure Portal → Skapa en resurs → Azure OpenAI
   - Välj ett unikt namn för din resurs
   - Distribuera följande modeller:
     - **GPT-5**
     - **text-embedding-3-small** (för RAG-moduler)
   - **Viktigt:** Notera dina utplaceringsnamn – de måste matcha `.env`-konfigurationen
   - Efter distribution, hämta din endpoint och API-nyckel från "Keys and Endpoint"
   - Skapa en `.env`-fil i projektets rot med:
     
     **Exempel på `.env`-fil:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Riktlinjer för namngivning av modellutplaceringar:**
- Använd enkla, konsekventa namn: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Utplaceringsnamn måste exakt matcha vad du konfigurerar i `.env`
- Vanligt misstag: Skapa modell med ett namn men referera till ett annat i koden

### Problem: GPT-5 inte tillgänglig i vald region

**Lösning:**
- Välj en region med GPT-5-åtkomst (t.ex. eastus, swedencentral)
- Kontrollera tillgänglighet: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Problem: Otillräcklig kvot för distribution

**Lösning:**
1. Begär kvöthöjning i Azure Portal
2. Eller använd lägre kapacitet i `main.bicep` (t.ex. capacity: 10)

### Problem: "Resource not found" vid lokal körning

**Lösning:**
1. Verifiera distribution: `azd env get-values`
2. Kontrollera att endpoint och nyckel är korrekta
3. Säkerställ att resursgruppen finns i Azure Portal

### Problem: Autentisering misslyckades

**Lösning:**
- Kontrollera att `AZURE_OPENAI_API_KEY` är korrekt satt
- Nyckelformat ska vara en 32-teckens hexadecimalssträng
- Hämta ny nyckel från Azure Portal vid behov

### Distribution misslyckas

**Problem**: `azd provision` misslyckas med kvot- eller kapacitetsfel

**Lösning**: 
1. Prova en annan region – Se avsnittet [Byta Azure-regioner](../../../../01-introduction/infra) för hur man konfigurerar regioner
2. Kontrollera att din prenumeration har Azure OpenAI-kvot:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Applikation ansluter inte

**Problem**: Java-applikationen visar anslutningsfel

**Lösning**:
1. Verifiera att miljövariabler är exporterade:
   
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

2. Kontrollera att endpoint-formatet är korrekt (bör vara `https://xxx.openai.azure.com`)
3. Verifiera att API-nyckeln är primär eller sekundär nyckel från Azure Portal

**Problem**: 401 Unauthorized från Azure OpenAI

**Lösning**:
1. Hämta en ny API-nyckel från Azure Portal → Keys and Endpoint
2. Exportera om miljövariabeln `AZURE_OPENAI_API_KEY`
3. Säkerställ att modellutplaceringar är kompletta (kontrollera Azure Portal)

### Prestandaproblem

**Problem**: Långsamma svarstider

**Lösning**:
1. Kontrollera OpenAI-tokenanvändning och begränsningar i Azure Portal-metriker
2. Öka TPM-kapaciteten om du når gränser
3. Överväg att använda högre nivå av resonemang (låg/medel/hög)

## Uppdatera infrastruktur

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

## Säkerhetsrekommendationer

1. **Lämna aldrig in API-nycklar i koden** – Använd miljövariabler
2. **Använd .env-filer lokalt** – Lägg till `.env` i `.gitignore`
3. **Rotera nycklar regelbundet** – Generera nya nycklar i Azure Portal
4. **Begränsa åtkomst** – Använd Azure RBAC för att kontrollera vem som kan nå resurser
5. **Övervaka användning** – Sätt upp kostnadsvarningar i Azure Portal

## Ytterligare resurser

- [Azure OpenAI Service Dokumentation](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 Modell Dokumentation](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI Dokumentation](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep Dokumentation](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI Officiell Integration](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Support

För problem:
1. Kontrollera [felsökningsavsnittet](../../../../01-introduction/infra) ovan
2. Granska Azure OpenAI-tjänstens hälsa i Azure Portal
3. Öppna ett ärende i repositoryn

## Licens

Se rotens [LICENSE](../../../../LICENSE)-fil för detaljer.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfriskrivning**:
Detta dokument har översatts med hjälp av AI-översättningstjänsten [Co-op Translator](https://github.com/Azure/co-op-translator). Även om vi strävar efter noggrannhet, vänligen var medveten om att automatiska översättningar kan innehålla fel eller brister. Det ursprungliga dokumentet på dess modersmål bör betraktas som den auktoritativa källan. För kritisk information rekommenderas professionell mänsklig översättning. Vi ansvarar inte för några missförstånd eller feltolkningar som uppstår till följd av användningen av denna översättning.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->