<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T00:09:38+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "it"
}
-->
# Glossario LangChain4j

## Indice

- [Concetti principali](../../../docs)
- [Componenti di LangChain4j](../../../docs)
- [Concetti AI/ML](../../../docs)
- [Ingegneria dei Prompt](../../../docs)
- [RAG (Generazione aumentata dal recupero)](../../../docs)
- [Agenti e Strumenti](../../../docs)
- [Protocollo del contesto del modello (MCP)](../../../docs)
- [Servizi Azure](../../../docs)
- [Testing e Sviluppo](../../../docs)

Riferimento rapido per termini e concetti usati durante il corso.

## Concetti principali

**AI Agent** - Sistema che utilizza l'IA per ragionare e agire in modo autonomo. [Modulo 04](../04-tools/README.md)

**Catena** - Sequenza di operazioni in cui l'output alimenta il passaggio successivo.

**Segmentazione** - Suddivisione dei documenti in parti più piccole. Tipico: 300-500 token con sovrapposizione. [Modulo 03](../03-rag/README.md)

**Finestra di contesto** - Numero massimo di token che un modello può elaborare. GPT-5: 400K token.

**Embeddings** - Vettori numerici che rappresentano il significato del testo. [Modulo 03](../03-rag/README.md)

**Function Calling** - Il modello genera richieste strutturate per chiamare funzioni esterne. [Modulo 04](../04-tools/README.md)

**Allucinazione** - Quando i modelli generano informazioni errate ma plausibili.

**Prompt** - Testo in input a un modello linguistico. [Modulo 02](../02-prompt-engineering/README.md)

**Ricerca semantica** - Ricerca basata sul significato usando embeddings, non parole chiave. [Modulo 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: senza memoria. Stateful: mantiene la cronologia della conversazione. [Modulo 01](../01-introduction/README.md)

**Tokens** - Unità di testo di base che i modelli elaborano. Influisce su costi e limiti. [Modulo 01](../01-introduction/README.md)

**Catena di strumenti** - Esecuzione sequenziale di strumenti in cui l'output influenza la chiamata successiva. [Modulo 04](../04-tools/README.md)

## Componenti di LangChain4j

**AiServices** - Crea interfacce di servizi AI tipizzate.

**OpenAiOfficialChatModel** - Client unificato per modelli OpenAI e Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Crea embeddings usando il client ufficiale OpenAI (supporta sia OpenAI che Azure OpenAI).

**ChatModel** - Interfaccia principale per i modelli linguistici.

**ChatMemory** - Mantiene la cronologia della conversazione.

**ContentRetriever** - Trova frammenti di documento rilevanti per RAG.

**DocumentSplitter** - Suddivide i documenti in chunk.

**EmbeddingModel** - Converte il testo in vettori numerici.

**EmbeddingStore** - Memorizza e recupera gli embeddings.

**MessageWindowChatMemory** - Mantiene una finestra scorrevole dei messaggi recenti.

**PromptTemplate** - Crea prompt riutilizzabili con segnaposto `{{variable}}`.

**TextSegment** - Frammento di testo con metadata. Usato in RAG.

**ToolExecutionRequest** - Rappresenta una richiesta di esecuzione di uno strumento.

**UserMessage / AiMessage / SystemMessage** - Tipi di messaggi nella conversazione.

## Concetti AI/ML

**Few-Shot Learning** - Fornire esempi nei prompt. [Modulo 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Modelli di IA addestrati su vaste quantità di testo.

**Reasoning Effort** - Parametro di GPT-5 che controlla la profondità del ragionamento. [Modulo 02](../02-prompt-engineering/README.md)

**Temperatura** - Controlla la casualità dell'output. Bassa=deterministica, alta=creativa.

**Database vettoriale** - Database specializzato per embeddings. [Modulo 03](../03-rag/README.md)

**Zero-Shot Learning** - Eseguire compiti senza esempi. [Modulo 02](../02-prompt-engineering/README.md)

## Ingegneria dei Prompt - [Modulo 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Ragionamento passo-passo per una maggiore accuratezza.

**Output vincolato** - Applicare un formato o una struttura specifica.

**Alta Eagerness** - Pattern di GPT-5 per un ragionamento approfondito.

**Bassa Eagerness** - Pattern di GPT-5 per risposte rapide.

**Conversazione multi-turno** - Mantenere il contesto tra gli scambi.

**Prompting basato sui ruoli** - Impostare la persona del modello tramite messaggi di sistema.

**Auto-riflessione** - Il modello valuta e migliora il proprio output.

**Analisi strutturata** - Framework di valutazione fisso.

**Pattern di esecuzione dei compiti** - Pianifica → Esegui → Riassumi.

## RAG (Generazione aumentata dal recupero) - [Modulo 03](../03-rag/README.md)

**Pipeline di elaborazione dei documenti** - Carica → suddividi in chunk → crea embedding → memorizza.

**In-Memory Embedding Store** - Archiviazione non persistente per i test.

**RAG** - Combina il recupero con la generazione per ancorare le risposte.

**Punteggio di similarità** - Misura (0-1) della similarità semantica.

**Riferimento alla fonte** - Metadati sul contenuto recuperato.

## Agenti e Strumenti - [Modulo 04](../04-tools/README.md)

**@Tool Annotation** - Contrassegna metodi Java come strumenti chiamabili dall'IA.

**ReAct Pattern** - Ragiona → Agisci → Osserva → Ripeti.

**Session Management** - Contesti separati per utenti diversi.

**Tool** - Funzione che un agente AI può chiamare.

**Tool Description** - Documentazione dello scopo e dei parametri dello strumento.

## Protocollo del contesto del modello (MCP) - [Modulo 05](../05-mcp/README.md)

**MCP** - Standard per collegare le app AI a strumenti esterni.

**MCP Client** - Applicazione che si connette ai server MCP.

**MCP Server** - Servizio che espone strumenti tramite MCP.

**Stdio Transport** - Server come sottoprocesso tramite stdin/stdout.

**Tool Discovery** - Il client interroga il server per gli strumenti disponibili.

## Servizi Azure - [Modulo 01](../01-introduction/README.md)

**Azure AI Search** - Ricerca cloud con funzionalità vettoriali. [Modulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Distribuisce risorse Azure.

**Azure OpenAI** - Servizio AI enterprise di Microsoft.

**Bicep** - Linguaggio infrastructure-as-code per Azure. [Guida all'infrastruttura](../01-introduction/infra/README.md)

**Deployment Name** - Nome per il deployment del modello in Azure.

**GPT-5** - Ultimo modello OpenAI con controllo del ragionamento. [Modulo 02](../02-prompt-engineering/README.md)

## Testing e Sviluppo - [Guida ai test](TESTING.md)

**Dev Container** - Ambiente di sviluppo containerizzato. [Configurazione](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Playground per modelli AI gratuito. [Modulo 00](../00-quick-start/README.md)

**In-Memory Testing** - Test con storage in memoria.

**Integration Testing** - Test con infrastruttura reale.

**Maven** - Strumento di automazione della build per Java.

**Mockito** - Framework Java per il mocking.

**Spring Boot** - Framework applicativo Java. [Modulo 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Avvertenza:
Questo documento è stato tradotto utilizzando il servizio di traduzione automatica basato su IA Co-op Translator (https://github.com/Azure/co-op-translator). Pur impegnandoci per garantire accuratezza, si prega di notare che le traduzioni automatiche possono contenere errori o imprecisioni. Il documento originale nella sua lingua deve essere considerato la fonte autorevole. Per informazioni critiche si consiglia una traduzione professionale effettuata da un traduttore umano. Non siamo responsabili per eventuali fraintendimenti o interpretazioni errate derivanti dall'uso di questa traduzione.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->