# Azure-infrastructuur voor LangChain4j Aan de slag

## Inhoudsopgave

- [Vereisten](../../../../01-introduction/infra)
- [Architectuur](../../../../01-introduction/infra)
- [Gemaakte bronnen](../../../../01-introduction/infra)
- [Snel aan de slag](../../../../01-introduction/infra)
- [Configuratie](../../../../01-introduction/infra)
- [Beheeropdrachten](../../../../01-introduction/infra)
- [Kostenoptimalisatie](../../../../01-introduction/infra)
- [Monitoring](../../../../01-introduction/infra)
- [Probleemoplossing](../../../../01-introduction/infra)
- [Infrastructuur bijwerken](../../../../01-introduction/infra)
- [Opruimen](../../../../01-introduction/infra)
- [Bestandsstructuur](../../../../01-introduction/infra)
- [Beveiligingsaanbevelingen](../../../../01-introduction/infra)
- [Aanvullende bronnen](../../../../01-introduction/infra)

Deze map bevat de Azure-infrastructuur als code (IaC) met Bicep en Azure Developer CLI (azd) voor het implementeren van Azure OpenAI-resources.

## Vereisten

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (versie 2.50.0 of later)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (versie 1.5.0 of later)
- Een Azure-abonnement met rechten om resources te maken

## Architectuur

**Vereenvoudigde lokale ontwikkelopstelling** - Alleen Azure OpenAI implementeren, alle apps lokaal uitvoeren.

De infrastructuur implementeert de volgende Azure-resources:

### AI-diensten
- **Azure OpenAI**: Cognitive Services met twee modelimplementaties:
  - **gpt-5**: Chat completion model (20K TPM capaciteit)
  - **text-embedding-3-small**: Embedding model voor RAG (20K TPM capaciteit)

### Lokale ontwikkeling
Alle Spring Boot-applicaties draaien lokaal op je machine:
- 01-introduction (poort 8080)
- 02-prompt-engineering (poort 8083)
- 03-rag (poort 8081)
- 04-tools (poort 8084)

## Gemaakte bronnen

| Type resource | Naam patroon resource | Doel |
|--------------|----------------------|---------|
| Resourcegroep | `rg-{environmentName}` | Bevat alle resources |
| Azure OpenAI | `aoai-{resourceToken}` | Hosting AI-model |

> **Opmerking:** `{resourceToken}` is een unieke string gegenereerd uit abonnement-ID, omgevingsnaam en locatie

## Snel aan de slag

### 1. Azure OpenAI implementeren

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

Wanneer daarom wordt gevraagd:
- Selecteer je Azure-abonnement
- Kies een locatie (aanbevolen: `eastus2` of `swedencentral` voor GPT-5 beschikbaarheid)
- Bevestig de omgevingsnaam (standaard: `langchain4j-dev`)

Dit maakt aan:
- Azure OpenAI-resource met GPT-5 en text-embedding-3-small
- Uitvoer met verbindingsgegevens

### 2. Verbindingsgegevens ophalen

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Dit toont:
- `AZURE_OPENAI_ENDPOINT`: jouw Azure OpenAI eindpunt-URL
- `AZURE_OPENAI_KEY`: API-sleutel voor authenticatie
- `AZURE_OPENAI_DEPLOYMENT`: Chat modelnaam (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Embedding modelnaam

### 3. Applicaties lokaal uitvoeren

Het `azd up` commando maakt automatisch een `.env` bestand aan in de hoofdmap met alle benodigde omgevingsvariabelen.

**Aanbevolen:** Start alle webapplicaties:

**Bash:**
```bash
# Vanuit de hoofdmap
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Vanuit de hoofdmap
cd ../..
.\start-all.ps1
```

Of start een enkele module:

**Bash:**
```bash
# Voorbeeld: Start alleen de introductiemodule
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Voorbeeld: Start alleen de introductiemodule
cd ../01-introduction
.\start.ps1
```

Beide scripts laden automatisch omgevingsvariabelen uit het root `.env` bestand dat door `azd up` is aangemaakt.

## Configuratie

### Modelimplementaties aanpassen

Om modelimplementaties te wijzigen, bewerk `infra/main.bicep` en pas de parameter `openAiDeployments` aan:

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

Beschikbare modellen en versies: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure-regio's wijzigen

Om in een andere regio te implementeren, bewerk `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Controleer GPT-5 beschikbaarheid: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Om de infrastructuur bij te werken na wijzigingen in Bicep-bestanden:

**Bash:**
```bash
# Bouw de ARM-sjabloon opnieuw op
az bicep build --file infra/main.bicep

# Wijzigingen bekijken
azd provision --preview

# Wijzigingen toepassen
azd provision
```

**PowerShell:**
```powershell
# Bouw de ARM-sjabloon opnieuw op
az bicep build --file infra/main.bicep

# Wijzigingen bekijken
azd provision --preview

# Wijzigingen toepassen
azd provision
```

## Opruimen

Om alle resources te verwijderen:

**Bash:**
```bash
# Verwijder alle bronnen
azd down

# Verwijder alles inclusief de omgeving
azd down --purge
```

**PowerShell:**
```powershell
# Verwijder alle bronnen
azd down

# Verwijder alles inclusief de omgeving
azd down --purge
```

**Waarschuwing**: Dit verwijdert permanent alle Azure-resources.

## Bestandsstructuur

## Kostenoptimalisatie

### Ontwikkeling/Testen
Voor dev/test omgevingen kun je kosten verlagen:
- Gebruik Standard tier (S0) voor Azure OpenAI
- Stel lagere capaciteit in (10K TPM in plaats van 20K) in `infra/core/ai/cognitiveservices.bicep`
- Verwijder resources wanneer niet in gebruik: `azd down`

### Productie
Voor productie:
- Verhoog OpenAI capaciteit op basis van gebruik (50K+ TPM)
- Schakel zone-redundantie in voor hogere beschikbaarheid
- Implementeer goede monitoring en kostenwaarschuwingen

### Kostenraming
- Azure OpenAI: betalen per token (invoer + uitvoer)
- GPT-5: ~$3-5 per 1M tokens (controleer actuele prijzen)
- text-embedding-3-small: ~$0.02 per 1M tokens

Prijscalculator: https://azure.microsoft.com/pricing/calculator/

## Monitoring

### Azure OpenAI-metrics bekijken

Ga naar Azure Portal → jouw OpenAI-resource → Metrics:
- Token-gebaseerd gebruik
- HTTP-verzoekfrequentie
- Reactietijd
- Actieve tokens

## Probleemoplossing

### Probleem: Azure OpenAI subdomeinnaamconflict

**Foutmelding:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Oorzaak:**
De subdomeinnaam die is gegenereerd uit je abonnement/omgeving is al in gebruik, mogelijk van een eerdere implementatie die niet volledig is verwijderd.

**Oplossing:**
1. **Optie 1 - Gebruik een andere omgevingsnaam:**
   
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

2. **Optie 2 - Handmatige implementatie via Azure Portal:**
   - Ga naar Azure Portal → Maak een resource → Azure OpenAI
   - Kies een unieke naam voor je resource
   - Implementeer de volgende modellen:
     - **GPT-5**
     - **text-embedding-3-small** (voor RAG-modules)
   - **Belangrijk:** Noteer je implementatienamen - deze moeten overeenkomen met de `.env` configuratie
   - Na implementatie, haal je eindpunt en API-sleutel op via "Keys and Endpoint"
   - Maak een `.env` bestand aan in de projectroot met:
     
     **Voorbeeld `.env` bestand:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Richtlijnen voor modelimplementatienamen:**
- Gebruik eenvoudige, consistente namen: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Implementatienamen moeten exact overeenkomen met wat je in `.env` configureert
- Veelgemaakte fout: model aanmaken met één naam maar in code een andere naam gebruiken

### Probleem: GPT-5 niet beschikbaar in geselecteerde regio

**Oplossing:**
- Kies een regio met GPT-5 toegang (bijv. eastus, swedencentral)
- Controleer beschikbaarheid: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Probleem: Onvoldoende quotum voor implementatie

**Oplossing:**
1. Vraag quotumverhoging aan in Azure Portal
2. Of gebruik lagere capaciteit in `main.bicep` (bijv. capaciteit: 10)

### Probleem: "Resource niet gevonden" bij lokaal draaien

**Oplossing:**
1. Controleer implementatie: `azd env get-values`
2. Controleer of eindpunt en sleutel correct zijn
3. Zorg dat resourcegroep bestaat in Azure Portal

### Probleem: Authenticatie mislukt

**Oplossing:**
- Controleer of `AZURE_OPENAI_API_KEY` correct is ingesteld
- Sleutel moet een 32-teken hexadecimale string zijn
- Haal een nieuwe sleutel op in Azure Portal indien nodig

### Implementatie mislukt

**Probleem**: `azd provision` faalt met quotum- of capaciteitsfouten

**Oplossing**: 
1. Probeer een andere regio - zie sectie [Azure-regio's wijzigen](../../../../01-introduction/infra) voor configuratie
2. Controleer of je abonnement Azure OpenAI-quotum heeft:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Applicatie maakt geen verbinding

**Probleem**: Java-applicatie toont verbindingsfouten

**Oplossing**:
1. Controleer of omgevingsvariabelen zijn geëxporteerd:
   
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

2. Controleer of eindpuntformaat correct is (moet zijn `https://xxx.openai.azure.com`)
3. Controleer of API-sleutel de primaire of secundaire sleutel uit Azure Portal is

**Probleem**: 401 Unauthorized van Azure OpenAI

**Oplossing**:
1. Haal een nieuwe API-sleutel op via Azure Portal → Keys and Endpoint
2. Exporteer de `AZURE_OPENAI_API_KEY` omgevingsvariabele opnieuw
3. Zorg dat modelimplementaties voltooid zijn (controleer Azure Portal)

### Prestatieproblemen

**Probleem**: Trage responstijden

**Oplossing**:
1. Controleer OpenAI tokengebruik en throttling in Azure Portal metrics
2. Verhoog TPM-capaciteit als je limieten bereikt
3. Overweeg een hoger reasoning-effort niveau (laag/middel/hoog)

## Infrastructuur bijwerken

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

## Beveiligingsaanbevelingen

1. **Nooit API-sleutels committen** - Gebruik omgevingsvariabelen
2. **Gebruik .env-bestanden lokaal** - Voeg `.env` toe aan `.gitignore`
3. **Draai sleutels regelmatig** - Genereer nieuwe sleutels in Azure Portal
4. **Beperk toegang** - Gebruik Azure RBAC om toegang tot resources te beheren
5. **Monitor gebruik** - Stel kostenwaarschuwingen in Azure Portal in

## Aanvullende bronnen

- [Azure OpenAI Service Documentatie](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 Model Documentatie](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI Documentatie](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep Documentatie](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI Officiële Integratie](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Ondersteuning

Voor problemen:
1. Bekijk de [probleemoplossingssectie](../../../../01-introduction/infra) hierboven
2. Controleer de status van Azure OpenAI-service in Azure Portal
3. Open een issue in de repository

## Licentie

Zie het root [LICENSE](../../../../LICENSE) bestand voor details.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:  
Dit document is vertaald met behulp van de AI-vertalingsdienst [Co-op Translator](https://github.com/Azure/co-op-translator). Hoewel we streven naar nauwkeurigheid, dient u er rekening mee te houden dat geautomatiseerde vertalingen fouten of onnauwkeurigheden kunnen bevatten. Het originele document in de oorspronkelijke taal moet als de gezaghebbende bron worden beschouwd. Voor cruciale informatie wordt professionele menselijke vertaling aanbevolen. Wij zijn niet aansprakelijk voor eventuele misverstanden of verkeerde interpretaties die voortvloeien uit het gebruik van deze vertaling.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->