<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:04:46+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "it"
}
-->
# Glossario LangChain4j

## Indice

- [Concetti Fondamentali](../../../docs)
- [Componenti LangChain4j](../../../docs)
- [Concetti AI/ML](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenti e Strumenti](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Servizi Azure](../../../docs)
- [Testing e Sviluppo](../../../docs)

Riferimento rapido per termini e concetti usati durante il corso.

## Concetti Fondamentali

**AI Agent** - Sistema che usa l'AI per ragionare e agire autonomamente. [Modulo 04](../04-tools/README.md)

**Chain** - Sequenza di operazioni dove l'output alimenta il passo successivo.

**Chunking** - Suddividere documenti in pezzi più piccoli. Tipico: 300-500 token con sovrapposizione. [Modulo 03](../03-rag/README.md)

**Context Window** - Massimo numero di token che un modello può processare. GPT-5: 400K token.

**Embeddings** - Vettori numerici che rappresentano il significato del testo. [Modulo 03](../03-rag/README.md)

**Function Calling** - Il modello genera richieste strutturate per chiamare funzioni esterne. [Modulo 04](../04-tools/README.md)

**Hallucination** - Quando i modelli generano informazioni errate ma plausibili.

**Prompt** - Testo in input a un modello linguistico. [Modulo 02](../02-prompt-engineering/README.md)

**Semantic Search** - Ricerca per significato usando embeddings, non parole chiave. [Modulo 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: senza memoria. Stateful: mantiene la cronologia della conversazione. [Modulo 01](../01-introduction/README.md)

**Tokens** - Unità base di testo che i modelli processano. Influisce su costi e limiti. [Modulo 01](../01-introduction/README.md)

**Tool Chaining** - Esecuzione sequenziale di strumenti dove l'output informa la chiamata successiva. [Modulo 04](../04-tools/README.md)

## Componenti LangChain4j

**AiServices** - Crea interfacce di servizio AI type-safe.

**OpenAiOfficialChatModel** - Client unificato per modelli OpenAI e Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Crea embeddings usando il client ufficiale OpenAI (supporta sia OpenAI che Azure OpenAI).

**ChatModel** - Interfaccia principale per modelli linguistici.

**ChatMemory** - Mantiene la cronologia della conversazione.

**ContentRetriever** - Trova chunk di documenti rilevanti per RAG.

**DocumentSplitter** - Suddivide documenti in chunk.

**EmbeddingModel** - Converte testo in vettori numerici.

**EmbeddingStore** - Memorizza e recupera embeddings.

**MessageWindowChatMemory** - Mantiene una finestra scorrevole dei messaggi recenti.

**PromptTemplate** - Crea prompt riutilizzabili con segnaposto `{{variable}}`.

**TextSegment** - Chunk di testo con metadati. Usato in RAG.

**ToolExecutionRequest** - Rappresenta una richiesta di esecuzione di uno strumento.

**UserMessage / AiMessage / SystemMessage** - Tipi di messaggi di conversazione.

## Concetti AI/ML

**Few-Shot Learning** - Fornire esempi nei prompt. [Modulo 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Modelli AI addestrati su grandi quantità di testo.

**Reasoning Effort** - Parametro GPT-5 che controlla la profondità del ragionamento. [Modulo 02](../02-prompt-engineering/README.md)

**Temperature** - Controlla la casualità dell'output. Basso=deterministico, alto=creativo.

**Vector Database** - Database specializzato per embeddings. [Modulo 03](../03-rag/README.md)

**Zero-Shot Learning** - Eseguire compiti senza esempi. [Modulo 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modulo 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Ragionamento passo-passo per maggiore accuratezza.

**Constrained Output** - Forzare un formato o struttura specifica.

**High Eagerness** - Pattern GPT-5 per ragionamento approfondito.

**Low Eagerness** - Pattern GPT-5 per risposte rapide.

**Multi-Turn Conversation** - Mantenere il contesto attraverso gli scambi.

**Role-Based Prompting** - Impostare la persona del modello tramite messaggi di sistema.

**Self-Reflection** - Il modello valuta e migliora il proprio output.

**Structured Analysis** - Quadro di valutazione fisso.

**Task Execution Pattern** - Pianifica → Esegui → Riassumi.

## RAG (Retrieval-Augmented Generation) - [Modulo 03](../03-rag/README.md)

**Document Processing Pipeline** - Carica → chunk → embedding → memorizza.

**In-Memory Embedding Store** - Memoria non persistente per test.

**RAG** - Combina recupero e generazione per fondare le risposte.

**Similarity Score** - Misura (0-1) di similarità semantica.

**Source Reference** - Metadati sul contenuto recuperato.

## Agenti e Strumenti - [Modulo 04](../04-tools/README.md)

**@Tool Annotation** - Marca metodi Java come strumenti chiamabili dall'AI.

**ReAct Pattern** - Ragiona → Agisci → Osserva → Ripeti.

**Session Management** - Contesti separati per utenti diversi.

**Tool** - Funzione che un agente AI può chiamare.

**Tool Description** - Documentazione dello scopo e parametri dello strumento.

## Model Context Protocol (MCP) - [Modulo 05](../05-mcp/README.md)

**Docker Transport** - Server MCP in contenitore Docker.

**MCP** - Standard per connettere app AI a strumenti esterni.

**MCP Client** - Applicazione che si connette a server MCP.

**MCP Server** - Servizio che espone strumenti tramite MCP.

**Server-Sent Events (SSE)** - Streaming server-to-client via HTTP.

**Stdio Transport** - Server come sottoprocesso via stdin/stdout.

**Streamable HTTP Transport** - HTTP con SSE per comunicazione in tempo reale.

**Tool Discovery** - Il client interroga il server per strumenti disponibili.

## Servizi Azure - [Modulo 01](../01-introduction/README.md)

**Azure AI Search** - Ricerca cloud con capacità vettoriali. [Modulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Distribuisce risorse Azure.

**Azure OpenAI** - Servizio AI enterprise di Microsoft.

**Bicep** - Linguaggio infrastructure-as-code per Azure. [Guida all'infrastruttura](../01-introduction/infra/README.md)

**Deployment Name** - Nome per il deployment del modello in Azure.

**GPT-5** - Ultimo modello OpenAI con controllo del ragionamento. [Modulo 02](../02-prompt-engineering/README.md)

## Testing e Sviluppo - [Guida al Testing](TESTING.md)

**Dev Container** - Ambiente di sviluppo containerizzato. [Configurazione](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Playground gratuito per modelli AI. [Modulo 00](../00-quick-start/README.md)

**In-Memory Testing** - Test con storage in memoria.

**Integration Testing** - Test con infrastruttura reale.

**Maven** - Strumento di automazione build Java.

**Mockito** - Framework di mocking per Java.

**Spring Boot** - Framework applicativo Java. [Modulo 01](../01-introduction/README.md)

**Testcontainers** - Contenitori Docker nei test.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:  
Questo documento è stato tradotto utilizzando il servizio di traduzione automatica [Co-op Translator](https://github.com/Azure/co-op-translator). Pur impegnandoci per garantire l’accuratezza, si prega di notare che le traduzioni automatiche possono contenere errori o imprecisioni. Il documento originale nella sua lingua nativa deve essere considerato la fonte autorevole. Per informazioni critiche, si raccomanda una traduzione professionale effettuata da un umano. Non ci assumiamo alcuna responsabilità per eventuali malintesi o interpretazioni errate derivanti dall’uso di questa traduzione.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->