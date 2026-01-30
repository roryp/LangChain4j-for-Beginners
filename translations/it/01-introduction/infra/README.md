# Infrastruttura Azure per LangChain4j Guida Introduttiva

## Indice

- [Prerequisiti](../../../../01-introduction/infra)
- [Architettura](../../../../01-introduction/infra)
- [Risorse Create](../../../../01-introduction/infra)
- [Avvio Rapido](../../../../01-introduction/infra)
- [Configurazione](../../../../01-introduction/infra)
- [Comandi di Gestione](../../../../01-introduction/infra)
- [Ottimizzazione dei Costi](../../../../01-introduction/infra)
- [Monitoraggio](../../../../01-introduction/infra)
- [Risoluzione dei Problemi](../../../../01-introduction/infra)
- [Aggiornamento dell'Infrastruttura](../../../../01-introduction/infra)
- [Pulizia](../../../../01-introduction/infra)
- [Struttura dei File](../../../../01-introduction/infra)
- [Raccomandazioni di Sicurezza](../../../../01-introduction/infra)
- [Risorse Aggiuntive](../../../../01-introduction/infra)

Questa directory contiene l'infrastruttura Azure come codice (IaC) usando Bicep e Azure Developer CLI (azd) per il deployment delle risorse Azure OpenAI.

## Prerequisiti

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (versione 2.50.0 o successiva)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (versione 1.5.0 o successiva)
- Un abbonamento Azure con permessi per creare risorse

## Architettura

**Configurazione semplificata per sviluppo locale** - Deploy solo Azure OpenAI, esegui tutte le app localmente.

L'infrastruttura distribuisce le seguenti risorse Azure:

### Servizi AI
- **Azure OpenAI**: Servizi cognitivi con due deployment di modelli:
  - **gpt-5**: Modello di completamento chat (capacità 20K TPM)
  - **text-embedding-3-small**: Modello di embedding per RAG (capacità 20K TPM)

### Sviluppo Locale
Tutte le applicazioni Spring Boot vengono eseguite localmente sulla tua macchina:
- 01-introduction (porta 8080)
- 02-prompt-engineering (porta 8083)
- 03-rag (porta 8081)
- 04-tools (porta 8084)

## Risorse Create

| Tipo di Risorsa | Pattern Nome Risorsa | Scopo |
|--------------|----------------------|---------|
| Gruppo di Risorse | `rg-{environmentName}` | Contiene tutte le risorse |
| Azure OpenAI | `aoai-{resourceToken}` | Hosting modello AI |

> **Nota:** `{resourceToken}` è una stringa unica generata da ID abbonamento, nome ambiente e località

## Avvio Rapido

### 1. Deploy Azure OpenAI

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

Quando richiesto:
- Seleziona il tuo abbonamento Azure
- Scegli una località (consigliato: `eastus2` o `swedencentral` per disponibilità GPT-5)
- Conferma il nome dell'ambiente (default: `langchain4j-dev`)

Questo creerà:
- Risorsa Azure OpenAI con GPT-5 e text-embedding-3-small
- Output con dettagli di connessione

### 2. Ottieni Dettagli di Connessione

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Questo mostra:
- `AZURE_OPENAI_ENDPOINT`: URL endpoint Azure OpenAI
- `AZURE_OPENAI_KEY`: Chiave API per autenticazione
- `AZURE_OPENAI_DEPLOYMENT`: Nome modello chat (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Nome modello embedding

### 3. Esegui Applicazioni Localmente

Il comando `azd up` crea automaticamente un file `.env` nella directory radice con tutte le variabili d'ambiente necessarie.

**Consigliato:** Avvia tutte le applicazioni web:

**Bash:**
```bash
# Dalla directory radice
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Dalla directory radice
cd ../..
.\start-all.ps1
```

Oppure avvia un singolo modulo:

**Bash:**
```bash
# Esempio: Avvia solo il modulo di introduzione
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Esempio: Avvia solo il modulo di introduzione
cd ../01-introduction
.\start.ps1
```

Entrambi gli script caricano automaticamente le variabili d'ambiente dal file `.env` radice creato da `azd up`.

## Configurazione

### Personalizzazione dei Deployment Modello

Per cambiare i deployment dei modelli, modifica `infra/main.bicep` e modifica il parametro `openAiDeployments`:

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

Modelli e versioni disponibili: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Cambiare le Regioni Azure

Per distribuire in una regione diversa, modifica `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Controlla la disponibilità di GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Per aggiornare l'infrastruttura dopo aver modificato i file Bicep:

**Bash:**
```bash
# Ricostruisci il modello ARM
az bicep build --file infra/main.bicep

# Anteprima delle modifiche
azd provision --preview

# Applica le modifiche
azd provision
```

**PowerShell:**
```powershell
# Ricostruisci il modello ARM
az bicep build --file infra/main.bicep

# Anteprima delle modifiche
azd provision --preview

# Applica le modifiche
azd provision
```

## Pulizia

Per eliminare tutte le risorse:

**Bash:**
```bash
# Elimina tutte le risorse
azd down

# Elimina tutto incluso l'ambiente
azd down --purge
```

**PowerShell:**
```powershell
# Elimina tutte le risorse
azd down

# Elimina tutto incluso l'ambiente
azd down --purge
```

**Attenzione**: Questo eliminerà permanentemente tutte le risorse Azure.

## Struttura dei File

## Ottimizzazione dei Costi

### Sviluppo/Test
Per ambienti di sviluppo/test, puoi ridurre i costi:
- Usa il livello Standard (S0) per Azure OpenAI
- Imposta capacità inferiore (10K TPM invece di 20K) in `infra/core/ai/cognitiveservices.bicep`
- Elimina risorse quando non in uso: `azd down`

### Produzione
Per la produzione:
- Aumenta la capacità OpenAI in base all'uso (50K+ TPM)
- Abilita ridondanza di zona per maggiore disponibilità
- Implementa monitoraggio e avvisi di costo adeguati

### Stima dei Costi
- Azure OpenAI: Pagamento per token (input + output)
- GPT-5: circa $3-5 per 1M token (verifica prezzi attuali)
- text-embedding-3-small: circa $0.02 per 1M token

Calcolatore prezzi: https://azure.microsoft.com/pricing/calculator/

## Monitoraggio

### Visualizza Metriche Azure OpenAI

Vai su Azure Portal → La tua risorsa OpenAI → Metriche:
- Utilizzo basato su token
- Tasso di richieste HTTP
- Tempo di risposta
- Token attivi

## Risoluzione dei Problemi

### Problema: Conflitto nome sottodominio Azure OpenAI

**Messaggio di Errore:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Causa:**
Il nome del sottodominio generato dal tuo abbonamento/ambiente è già in uso, probabilmente da un deployment precedente non completamente rimosso.

**Soluzione:**
1. **Opzione 1 - Usa un nome ambiente diverso:**
   
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

2. **Opzione 2 - Deployment manuale tramite Azure Portal:**
   - Vai su Azure Portal → Crea una risorsa → Azure OpenAI
   - Scegli un nome unico per la tua risorsa
   - Distribuisci i seguenti modelli:
     - **GPT-5**
     - **text-embedding-3-small** (per moduli RAG)
   - **Importante:** Prendi nota dei nomi di deployment - devono corrispondere alla configurazione `.env`
   - Dopo il deployment, ottieni endpoint e chiave API da "Chiavi e Endpoint"
   - Crea un file `.env` nella radice del progetto con:
     
     **Esempio file `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Linee guida per la denominazione dei deployment modello:**
- Usa nomi semplici e coerenti: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- I nomi dei deployment devono corrispondere esattamente a quelli configurati in `.env`
- Errore comune: creare modello con un nome ma riferirsi a un nome diverso nel codice

### Problema: GPT-5 non disponibile nella regione selezionata

**Soluzione:**
- Scegli una regione con accesso a GPT-5 (es. eastus, swedencentral)
- Controlla disponibilità: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Problema: Quota insufficiente per il deployment

**Soluzione:**
1. Richiedi aumento quota in Azure Portal
2. Oppure usa capacità inferiore in `main.bicep` (es. capacity: 10)

### Problema: "Risorsa non trovata" eseguendo localmente

**Soluzione:**
1. Verifica deployment: `azd env get-values`
2. Controlla che endpoint e chiave siano corretti
3. Assicurati che il gruppo di risorse esista in Azure Portal

### Problema: Autenticazione fallita

**Soluzione:**
- Verifica che `AZURE_OPENAI_API_KEY` sia impostato correttamente
- Il formato della chiave deve essere una stringa esadecimale di 32 caratteri
- Ottieni una nuova chiave da Azure Portal se necessario

### Deployment Fallito

**Problema**: `azd provision` fallisce con errori di quota o capacità

**Soluzione**: 
1. Prova una regione diversa - Vedi sezione [Cambiare le Regioni Azure](../../../../01-introduction/infra) per configurare le regioni
2. Controlla che il tuo abbonamento abbia quota Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Applicazione Non Connessa

**Problema**: L'applicazione Java mostra errori di connessione

**Soluzione**:
1. Verifica che le variabili d'ambiente siano esportate:
   
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

2. Controlla che il formato dell'endpoint sia corretto (deve essere `https://xxx.openai.azure.com`)
3. Verifica che la chiave API sia la chiave primaria o secondaria da Azure Portal

**Problema**: 401 Non autorizzato da Azure OpenAI

**Soluzione**:
1. Ottieni una nuova chiave API da Azure Portal → Chiavi e Endpoint
2. Riesporta la variabile d'ambiente `AZURE_OPENAI_API_KEY`
3. Assicurati che i deployment modello siano completi (controlla Azure Portal)

### Problemi di Prestazioni

**Problema**: Tempi di risposta lenti

**Soluzione**:
1. Controlla l'uso dei token OpenAI e il throttling nelle metriche di Azure Portal
2. Aumenta la capacità TPM se stai raggiungendo i limiti
3. Considera di usare un livello di sforzo di ragionamento più alto (basso/medio/alto)

## Aggiornamento dell'Infrastruttura

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

## Raccomandazioni di Sicurezza

1. **Non commettere mai chiavi API** - Usa variabili d'ambiente
2. **Usa file .env localmente** - Aggiungi `.env` a `.gitignore`
3. **Ruota regolarmente le chiavi** - Genera nuove chiavi in Azure Portal
4. **Limita l'accesso** - Usa Azure RBAC per controllare chi può accedere alle risorse
5. **Monitora l'uso** - Imposta avvisi di costo in Azure Portal

## Risorse Aggiuntive

- [Documentazione Azure OpenAI Service](https://learn.microsoft.com/azure/ai-services/openai/)
- [Documentazione Modello GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Documentazione Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Documentazione Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Integrazione Ufficiale LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Supporto

Per problemi:
1. Controlla la [sezione risoluzione problemi](../../../../01-introduction/infra) sopra
2. Verifica lo stato del servizio Azure OpenAI in Azure Portal
3. Apri un issue nel repository

## Licenza

Vedi il file [LICENSE](../../../../LICENSE) nella radice per i dettagli.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:  
Questo documento è stato tradotto utilizzando il servizio di traduzione automatica [Co-op Translator](https://github.com/Azure/co-op-translator). Pur impegnandoci per garantire l’accuratezza, si prega di notare che le traduzioni automatiche possono contenere errori o imprecisioni. Il documento originale nella sua lingua nativa deve essere considerato la fonte autorevole. Per informazioni critiche, si raccomanda una traduzione professionale effettuata da un traduttore umano. Non ci assumiamo alcuna responsabilità per eventuali malintesi o interpretazioni errate derivanti dall’uso di questa traduzione.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->