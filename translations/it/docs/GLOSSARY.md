# Glossario LangChain4j

## Indice

- [Concetti di Base](../../../docs)
- [Componenti LangChain4j](../../../docs)
- [Concetti AI/ML](../../../docs)
- [Guardrails](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenti e Strumenti](../../../docs)
- [Modulo Agentic](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Servizi Azure](../../../docs)
- [Testing e Sviluppo](../../../docs)

Riferimento rapido per termini e concetti utilizzati in tutto il corso.

## Core Concepts

**Agente AI** - Sistema che utilizza l'AI per ragionare e agire in modo autonomo. [Modulo 04](../04-tools/README.md)

**Catena (Chain)** - Sequenza di operazioni dove l'output alimenta il passo successivo.

**Chunking** - Suddividere documenti in pezzi più piccoli. Tipico: 300-500 token con sovrapposizione. [Modulo 03](../03-rag/README.md)

**Finestra di contesto (Context Window)** - Numero massimo di token che un modello può elaborare. GPT-5: 400K token.

**Embedding** - Vettori numerici che rappresentano il significato del testo. [Modulo 03](../03-rag/README.md)

**Function Calling** - Il modello genera richieste strutturate per chiamare funzioni esterne. [Modulo 04](../04-tools/README.md)

**Allucinazione (Hallucination)** - Quando i modelli generano informazioni errate ma plausibili.

**Prompt** - Input testuale per un modello linguistico. [Modulo 02](../02-prompt-engineering/README.md)

**Ricerca Semantica** - Ricerca basata sul significato usando embedding, non parole chiave. [Modulo 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: senza memoria. Stateful: mantiene la storia della conversazione. [Modulo 01](../01-introduction/README.md)

**Token** - Unità di testo base che i modelli elaborano. Influisce su costi e limiti. [Modulo 01](../01-introduction/README.md)

**Tool Chaining** - Esecuzione sequenziale di strumenti dove l'output influenza la chiamata successiva. [Modulo 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Crea interfacce di servizi AI tipizzate in modo sicuro.

**OpenAiOfficialChatModel** - Client unificato per modelli OpenAI e Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Crea embedding usando il client ufficiale OpenAI (supporta sia OpenAI che Azure OpenAI).

**ChatModel** - Interfaccia core per modelli linguistici.

**ChatMemory** - Mantiene la cronologia della conversazione.

**ContentRetriever** - Trova chunk documentali rilevanti per RAG.

**DocumentSplitter** - Spezza documenti in chunk.

**EmbeddingModel** - Converte testo in vettori numerici.

**EmbeddingStore** - Memorizza e recupera embedding.

**MessageWindowChatMemory** - Mantiene una finestra scorrevole dei messaggi recenti.

**PromptTemplate** - Crea prompt riutilizzabili con segnaposto `{{variable}}`.

**TextSegment** - Chunk di testo con metadata. Usato in RAG.

**ToolExecutionRequest** - Rappresenta la richiesta di esecuzione di uno strumento.

**UserMessage / AiMessage / SystemMessage** - Tipi di messaggi di conversazione.

## AI/ML Concepts

**Few-Shot Learning** - Fornire esempi nei prompt. [Modulo 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Modelli AI addestrati su vasti dati testuali.

**Reasoning Effort** - Parametro GPT-5 che controlla la profondità del ragionamento. [Modulo 02](../02-prompt-engineering/README.md)

**Temperature** - Controlla la casualità dell'output. Basso=deterministico, alto=creativo.

**Vector Database** - Database specializzato per embedding. [Modulo 03](../03-rag/README.md)

**Zero-Shot Learning** - Eseguire compiti senza esempi. [Modulo 02](../02-prompt-engineering/README.md)

## Guardrails - [Modulo 00](../00-quick-start/README.md)

**Defense in Depth** - Approccio di sicurezza multilivello che combina guardrails a livello di applicazione con filtri di sicurezza del provider.

**Hard Block** - Il provider restituisce errore HTTP 400 per gravi violazioni di contenuto.

**InputGuardrail** - Interfaccia LangChain4j per validare l'input utente prima che raggiunga l’LLM. Risparmia costi e latenza bloccando prompt dannosi in anticipo.

**InputGuardrailResult** - Tipo di ritorno per la validazione guardrail: `success()` oppure `fatal("reason")`.

**OutputGuardrail** - Interfaccia per validare risposte AI prima di restituirle all'utente.

**Provider Safety Filters** - Filtri integrati dei provider AI (es. GitHub Models) che intercettano violazioni a livello API.

**Soft Refusal** - Il modello rifiuta gentilmente di rispondere senza generare errore.

## Prompt Engineering - [Modulo 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Ragionamento passo-passo per maggiore accuratezza.

**Constrained Output** - Applicare formato o struttura specifica.

**High Eagerness** - Pattern GPT-5 per ragionamento approfondito.

**Low Eagerness** - Pattern GPT-5 per risposte rapide.

**Multi-Turn Conversation** - Mantenere il contesto tra scambi.

**Role-Based Prompting** - Impostare la persona del modello tramite messaggi di sistema.

**Self-Reflection** - Il modello valuta e migliora il proprio output.

**Structured Analysis** - Framework di valutazione fisso.

**Task Execution Pattern** - Pianifica → Esegui → Riassumi.

## RAG (Retrieval-Augmented Generation) - [Modulo 03](../03-rag/README.md)

**Document Processing Pipeline** - Carica → chunk → embeddi → archivia.

**In-Memory Embedding Store** - Archiviazione non persistente per test.

**RAG** - Combina retrieval con generazione per ancorare le risposte.

**Similarity Score** - Misura (0-1) della somiglianza semantica.

**Source Reference** - Metadata sul contenuto recuperato.

## Agents and Tools - [Modulo 04](../04-tools/README.md)

**Annotazione @Tool** - Marca metodi Java come strumenti chiamabili dall’AI.

**Pattern ReAct** - Ragiona → Agisci → Osserva → Ripeti.

**Gestione Sessione** - Contesti separati per utenti diversi.

**Strumento (Tool)** - Funzione che un agente AI può chiamare.

**Descrizione Strumento** - Documentazione dello scopo e parametri dello strumento.

## Agentic Module - [Modulo 05](../05-mcp/README.md)

**Annotazione @Agent** - Marca interfacce come agenti AI con definizione dichiarativa del comportamento.

**Agent Listener** - Hook per monitorare l’esecuzione tramite `beforeAgentInvocation()` e `afterAgentInvocation()`.

**Agentic Scope** - Memoria condivisa dove gli agenti memorizzano output usando `outputKey` per consumo da agenti downstream.

**AgenticServices** - Fabbrica per creare agenti usando `agentBuilder()` e `supervisorBuilder()`.

**Conditional Workflow** - Instradamento basato su condizioni verso agenti specialisti diversi.

**Human-in-the-Loop** - Pattern di workflow che aggiunge checkpoint umani per approvazione o revisione contenuti.

**langchain4j-agentic** - Dipendenza Maven per costruzione dichiarativa di agenti (sperimentale).

**Loop Workflow** - Itera l’esecuzione dell’agente finché non si verifica una condizione (es. punteggio qualità ≥ 0.8).

**outputKey** - Parametro annotazione agente che specifica dove sono memorizzati i risultati in Agentic Scope.

**Parallel Workflow** - Esecuzione simultanea di più agenti per compiti indipendenti.

**Response Strategy** - Come il supervisore formula la risposta finale: LAST, SUMMARY o SCORED.

**Sequential Workflow** - Esegui agenti in ordine dove l’output scorre al passo successivo.

**Supervisor Agent Pattern** - Pattern agentic avanzato in cui un LLM supervisore decide dinamicamente quali sub-agenti invocare.

## Model Context Protocol (MCP) - [Modulo 05](../05-mcp/README.md)

**langchain4j-mcp** - Dipendenza Maven per integrazione MCP in LangChain4j.

**MCP** - Model Context Protocol: standard per collegare app AI a strumenti esterni. Costruisci una volta, usa ovunque.

**MCP Client** - Applicazione che si connette ai server MCP per scoprire e usare strumenti.

**MCP Server** - Servizio che espone strumenti via MCP con descrizioni chiare e schemi parametri.

**McpToolProvider** - Componente LangChain4j che avvolge gli strumenti MCP per uso in servizi AI e agenti.

**McpTransport** - Interfaccia per comunicazione MCP. Implementazioni includono Stdio e HTTP.

**Stdio Transport** - Trasporto processo locale via stdin/stdout. Utile per accesso filesystem o strumenti da linea di comando.

**StdioMcpTransport** - Implementazione LangChain4j che avvia MCP server come processo secondario.

**Tool Discovery** - Il client interroga il server per strumenti disponibili con descrizioni e schemi.

## Azure Services - [Modulo 01](../01-introduction/README.md)

**Azure AI Search** - Ricerca cloud con capacità vettoriali. [Modulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Deploy risorse Azure.

**Azure OpenAI** - Servizio AI enterprise di Microsoft.

**Bicep** - Linguaggio infrastruttura-as-code Azure. [Guida infrastruttura](../01-introduction/infra/README.md)

**Nome Deployment** - Nome per il deployment modello in Azure.

**GPT-5** - Ultimo modello OpenAI con controllo del ragionamento. [Modulo 02](../02-prompt-engineering/README.md)

## Testing and Development - [Guida Testing](TESTING.md)

**Dev Container** - Ambiente di sviluppo containerizzato. [Configurazione](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Playground gratuito per modelli AI. [Modulo 00](../00-quick-start/README.md)

**In-Memory Testing** - Test con archiviazione in memoria.

**Integration Testing** - Test con infrastruttura reale.

**Maven** - Strumento di automazione build Java.

**Mockito** - Framework Java per mocking.

**Spring Boot** - Framework applicativo Java. [Modulo 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:
Questo documento è stato tradotto utilizzando il servizio di traduzione automatica [Co-op Translator](https://github.com/Azure/co-op-translator). Sebbene ci impegniamo per garantire accuratezza, si prega di considerare che le traduzioni automatiche possono contenere errori o imprecisioni. Il documento originale nella sua lingua originaria deve essere considerato la fonte autorevole. Per informazioni critiche, si raccomanda una traduzione professionale effettuata da un umano. Non siamo responsabili per eventuali incomprensioni o interpretazioni errate derivanti dall'uso di questa traduzione.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->