<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-05T23:09:36+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "it"
}
-->
# Modulo 04: Agenti IA con Strumenti

## Indice

- [Cosa Imparerai](../../../04-tools)
- [Prerequisiti](../../../04-tools)
- [Comprendere gli Agenti IA con Strumenti](../../../04-tools)
- [Come Funziona la Chiamata agli Strumenti](../../../04-tools)
  - [Definizioni degli Strumenti](../../../04-tools)
  - [Processo Decisionale](../../../04-tools)
  - [Esecuzione](../../../04-tools)
  - [Generazione della Risposta](../../../04-tools)
- [Concatenamento degli Strumenti](../../../04-tools)
- [Esegui l’Applicazione](../../../04-tools)
- [Utilizzo dell’Applicazione](../../../04-tools)
  - [Prova l’Uso Semplice degli Strumenti](../../../04-tools)
  - [Testa il Concatenamento degli Strumenti](../../../04-tools)
  - [Visualizza il Flusso della Conversazione](../../../04-tools)
  - [Sperimenta con Diverse Richieste](../../../04-tools)
- [Concetti Chiave](../../../04-tools)
  - [Pattern ReAct (Ragionare e Agire)](../../../04-tools)
  - [Le Descrizioni degli Strumenti Contano](../../../04-tools)
  - [Gestione della Sessione](../../../04-tools)
  - [Gestione degli Errori](../../../04-tools)
- [Strumenti Disponibili](../../../04-tools)
- [Quando Usare Agenti Basati su Strumenti](../../../04-tools)
- [Passi Successivi](../../../04-tools)

## Cosa Imparerai

Finora, hai imparato come conversare con l’IA, strutturare efficacemente i prompt e fondare le risposte nei tuoi documenti. Ma c’è ancora un limite fondamentale: i modelli linguistici possono solo generare testo. Non possono controllare il meteo, eseguire calcoli, interrogare database o interagire con sistemi esterni.

Gli strumenti cambiano questo. Dando al modello accesso a funzioni che può chiamare, lo trasformi da generatore di testo a agente che può compiere azioni. Il modello decide quando ha bisogno di uno strumento, quale strumento usare e quali parametri passare. Il tuo codice esegue la funzione e restituisce il risultato. Il modello incorpora quel risultato nella sua risposta.

## Prerequisiti

- Completato il Modulo 01 (risorse Azure OpenAI distribuite)
- File `.env` nella directory radice con credenziali Azure (creato da `azd up` nel Modulo 01)

> **Nota:** Se non hai completato il Modulo 01, segui prima le istruzioni di distribuzione lì.

## Comprendere gli Agenti IA con Strumenti

> **📝 Nota:** Il termine "agenti" in questo modulo si riferisce ad assistenti IA potenziati con capacità di chiamata agli strumenti. Questo è diverso dai pattern di **Agentic AI** (agenti autonomi con pianificazione, memoria e ragionamento multi-step) che tratteremo in [Modulo 05: MCP](../05-mcp/README.md).

Un agente IA con strumenti segue un pattern di ragionamento e azione (ReAct):

1. L’utente fa una domanda
2. L’agente riflette su cosa deve sapere
3. L’agente decide se ha bisogno di uno strumento per rispondere
4. Se sì, l’agente chiama lo strumento appropriato con i parametri giusti
5. Lo strumento esegue e restituisce i dati
6. L’agente incorpora il risultato e fornisce la risposta finale

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.it.png" alt="Pattern ReAct" width="800"/>

*Il pattern ReAct - come gli agenti IA alternano ragionamento e azione per risolvere problemi*

Questo accade automaticamente. Definisci gli strumenti e le loro descrizioni. Il modello gestisce il processo decisionale su quando e come usarli.

## Come Funziona la Chiamata agli Strumenti

### Definizioni degli Strumenti

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Definisci funzioni con descrizioni chiare e specifiche dei parametri. Il modello vede queste descrizioni nel suo prompt di sistema e capisce cosa fa ciascuno strumento.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // La tua logica di ricerca del meteo
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Assistant è automaticamente configurato da Spring Boot con:
// - Bean ChatModel
// - Tutti i metodi @Tool dalle classi @Component
// - ChatMemoryProvider per la gestione della sessione
```

> **🤖 Prova con [GitHub Copilot](https://github.com/features/copilot) Chat:** Apri [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) e chiedi:
> - "Come integrerei una vera API meteo come OpenWeatherMap invece dei dati mock?"
> - "Cosa rende una buona descrizione dello strumento che aiuta l’IA a usarlo correttamente?"
> - "Come gestisco errori API e limiti di richiesta nelle implementazioni degli strumenti?"

### Processo Decisionale

Quando un utente chiede "Com’è il tempo a Seattle?", il modello riconosce che ha bisogno dello strumento meteo. Genera una chiamata di funzione con il parametro location impostato su "Seattle".

### Esecuzione

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot auto-configura l’interfaccia dichiarativa `@AiService` con tutti gli strumenti registrati, e LangChain4j esegue automaticamente le chiamate agli strumenti.

> **🤖 Prova con [GitHub Copilot](https://github.com/features/copilot) Chat:** Apri [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) e chiedi:
> - "Come funziona il pattern ReAct e perché è efficace per gli agenti IA?"
> - "Come decide l’agente quale strumento usare e in che ordine?"
> - "Cosa succede se l’esecuzione dello strumento fallisce - come gestire robustamente gli errori?"

### Generazione della Risposta

Il modello riceve i dati meteo e li formatta in una risposta in linguaggio naturale per l’utente.

### Perché Usare Servizi IA Dichiarativi?

Questo modulo usa l’integrazione Spring Boot di LangChain4j con interfacce dichiarative `@AiService`:

- **Auto-configurazione Spring Boot** - ChatModel e strumenti iniettati automaticamente
- **Pattern @MemoryId** - Gestione automatica della memoria basata su sessioni
- **Istanza singola** - Assistente creato una sola volta e riutilizzato per migliori prestazioni
- **Esecuzione type-safe** - Metodi Java chiamati direttamente con conversione dei tipi
- **Orchestrazione multi-turn** - Gestisce automaticamente il concatenamento degli strumenti
- **Zero boilerplate** - Nessuna chiamata manuale ad AiServices.builder() o mappa memoria HashMap

Approcci alternativi (manuale `AiServices.builder()`) richiedono più codice e perdono i benefici dell’integrazione con Spring Boot.

## Concatenamento degli Strumenti

**Concatenamento degli Strumenti** - L’IA potrebbe chiamare più strumenti in sequenza. Chiedi "Com’è il tempo a Seattle e devo portare un ombrello?" e guarda come concatena `getCurrentWeather` con un ragionamento sull’equipaggiamento per la pioggia.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.it.png" alt="Concatenamento degli Strumenti" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Chiamate sequenziali agli strumenti - l’output di uno alimenta la decisione successiva*

**Fallimenti Eleganti** - Chiedi il meteo per una città che non è nei dati mock. Lo strumento restituisce un messaggio di errore e l’IA spiega che non può aiutare. Gli strumenti falliscono in sicurezza.

Questo avviene in un unico turno di conversazione. L’agente orchestra autonomamente più chiamate agli strumenti.

## Esegui l’Applicazione

**Verifica il deployment:**

Assicurati che il file `.env` esista nella directory radice con le credenziali Azure (creato durante il Modulo 01):
```bash
cat ../.env  # Dovrebbe mostrare AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Avvia l’applicazione:**

> **Nota:** Se hai già avviato tutte le applicazioni usando `./start-all.sh` dal Modulo 01, questo modulo è già in esecuzione sulla porta 8084. Puoi saltare i comandi di avvio qui sotto e andare direttamente su http://localhost:8084.

**Opzione 1: Usare Spring Boot Dashboard (consigliata per utenti VS Code)**

Il container di sviluppo include l’estensione Spring Boot Dashboard, che offre un’interfaccia visiva per gestire tutte le applicazioni Spring Boot. Lo trovi nella barra attività a sinistra di VS Code (icona Spring Boot).

Dal Spring Boot Dashboard puoi:
- Vedere tutte le applicazioni Spring Boot disponibili nello workspace
- Avviare/fermare le applicazioni con un click
- Visualizzare i log dell’app in tempo reale
- Monitorare lo stato delle applicazioni

Clicca semplicemente il pulsante play accanto a "tools" per avviare questo modulo, o avvia tutti i moduli insieme.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.it.png" alt="Spring Boot Dashboard" width="400"/>

**Opzione 2: Usare script shell**

Avvia tutte le applicazioni web (moduli 01-04):

**Bash:**
```bash
cd ..  # Dalla directory principale
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Dalla directory radice
.\start-all.ps1
```

Oppure avvia solo questo modulo:

**Bash:**
```bash
cd 04-tools
./start.sh
```

**PowerShell:**
```powershell
cd 04-tools
.\start.ps1
```

Entrambi gli script caricano automaticamente le variabili d’ambiente dal file `.env` della radice e compileranno i JAR se non esistenti.

> **Nota:** Se preferisci compilare tutti i moduli manualmente prima dell’avvio:
>
> **Bash:**
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
>
> **PowerShell:**
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

Apri http://localhost:8084 nel browser.

**Per fermare:**

**Bash:**
```bash
./stop.sh  # Solo questo modulo
# O
cd .. && ./stop-all.sh  # Tutti i moduli
```

**PowerShell:**
```powershell
.\stop.ps1  # Solo questo modulo
# O
cd ..; .\stop-all.ps1  # Tutti i moduli
```

## Utilizzo dell’Applicazione

L’applicazione offre un’interfaccia web dove puoi interagire con un agente IA che ha accesso a strumenti meteo e di conversione temperatura.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.it.png" alt="Interfaccia Agente IA con Strumenti" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Interfaccia degli Strumenti Agente IA - esempi rapidi e chat per interagire con gli strumenti*

### Prova l’Uso Semplice degli Strumenti

Inizia con una richiesta semplice: "Converti 100 gradi Fahrenheit in Celsius". L’agente riconosce che serve lo strumento di conversione temperatura, lo chiama con i parametri corretti e restituisce il risultato. Nota quanto è naturale: non hai specificato quale strumento usare né come chiamarlo.

### Testa il Concatenamento degli Strumenti

Ora prova qualcosa di più complesso: "Com’è il tempo a Seattle e converti la temperatura in Fahrenheit?" Guarda l’agente procedere a passi. Prima ottiene il meteo (in Celsius), riconosce che serve la conversione in Fahrenheit, chiama lo strumento di conversione e combina entrambi i risultati in una singola risposta.

### Visualizza il Flusso della Conversazione

L’interfaccia chat mantiene la cronologia della conversazione, permettendoti interazioni multi-turno. Puoi vedere tutte le domande e risposte precedenti, facilitando il tracciamento e la comprensione di come l’agente costruisce il contesto su più scambi.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.it.png" alt="Conversazione con Multiple Chiamate a Strumenti" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Conversazione multi-turno che mostra conversioni semplici, ricerche meteo e concatenamento strumenti*

### Sperimenta con Diverse Richieste

Prova varie combinazioni:
- Ricerche meteo: "Com’è il tempo a Tokyo?"
- Conversioni di temperatura: "Quanto sono 25°C in Kelvin?"
- Query combinate: "Controlla il meteo a Parigi e dimmi se è sopra i 20°C"

Nota come l’agente interpreta il linguaggio naturale e lo mappa a chiamate agli strumenti appropriate.

## Concetti Chiave

### Pattern ReAct (Ragionare e Agire)

L’agente alterna ragionamento (decidere cosa fare) e azione (usare strumenti). Questo pattern abilita la risoluzione autonoma dei problemi anziché una semplice risposta a istruzioni.

### Le Descrizioni degli Strumenti Contano

La qualità delle descrizioni dei tuoi strumenti influisce direttamente su come bene l’agente li usa. Descrizioni chiare e specifiche aiutano il modello a capire quando e come chiamare ogni strumento.

### Gestione della Sessione

L’annotazione `@MemoryId` abilita la gestione automatica della memoria basata su sessioni. Ogni ID di sessione ottiene una propria istanza di `ChatMemory` gestita dal bean `ChatMemoryProvider`, eliminando la necessità di tracciare la memoria manualmente.

### Gestione degli Errori

Gli strumenti possono fallire - API timeout, parametri invalidi, servizi esterni non disponibili. Gli agenti di produzione necessitano di gestione degli errori affinché il modello possa spiegare i problemi o provare alternative.

## Strumenti Disponibili

**Strumenti Meteo** (dati mock per dimostrazione):
- Ottieni il meteo corrente per una località
- Ottieni previsioni multi-giorno

**Strumenti di Conversione Temperatura**:
- Celsius a Fahrenheit
- Fahrenheit a Celsius
- Celsius a Kelvin
- Kelvin a Celsius
- Fahrenheit a Kelvin
- Kelvin a Fahrenheit

Questi sono esempi semplici, ma il pattern si estende a qualsiasi funzione: query database, chiamate API, calcoli, operazioni su file o comandi di sistema.

## Quando Usare Agenti Basati su Strumenti

**Usa strumenti quando:**
- Per rispondere servono dati in tempo reale (meteo, prezzi azioni, inventario)
- Devi fare calcoli oltre la semplice matematica
- Accedi a database o API
- Compi azioni (inviare email, creare ticket, aggiornare record)
- Combini molteplici fonti dati

**Non usare strumenti quando:**
- Le domande si possono rispondere con conoscenza generale
- La risposta è puramente conversazionale
- La latenza dello strumento renderebbe l’esperienza troppo lenta

## Passi Successivi

**Prossimo Modulo:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigazione:** [← Precedente: Modulo 03 - RAG](../03-rag/README.md) | [Torna alla Home](../README.md) | [Successivo: Modulo 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:  
Questo documento è stato tradotto utilizzando il servizio di traduzione automatica [Co-op Translator](https://github.com/Azure/co-op-translator). Pur impegnandoci a garantire l’accuratezza, si prega di considerare che le traduzioni automatiche possono contenere errori o imprecisioni. Il documento originale nella sua lingua nativa deve essere considerato la fonte autorizzata. Per informazioni criticali, si raccomanda una traduzione professionale umana. Non ci assumiamo responsabilità per eventuali fraintendimenti o interpretazioni errate derivanti dall’uso di questa traduzione.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->