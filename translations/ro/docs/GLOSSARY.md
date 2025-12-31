<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T04:53:12+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ro"
}
-->
# LangChain4j Glosar

## Cuprins

- [Concepte de bază](../../../docs)
- [Componente LangChain4j](../../../docs)
- [Concepte AI/ML](../../../docs)
- [Ingineria prompturilor](../../../docs)
- [RAG (Generare augmentată prin recuperare)](../../../docs)
- [Agenți și unelte](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Servicii Azure](../../../docs)
- [Testare și dezvoltare](../../../docs)

Referință rapidă pentru termeni și concepte utilizate pe tot parcursul cursului.

## Core Concepts

**AI Agent** - Sistem care folosește AI pentru a raționa și a acționa în mod autonom. [Modul 04](../04-tools/README.md)

**Chain** - Secvență de operațiuni în care ieșirea alimentează pasul următor.

**Chunking** - Împărțirea documentelor în bucăți mai mici. Tipic: 300-500 tokeni cu suprapunere. [Modul 03](../03-rag/README.md)

**Context Window** - Numărul maxim de tokeni pe care un model îi poate procesa. GPT-5: 400K tokeni.

**Embeddings** - Vectori numerici care reprezintă semnificația textului. [Modul 03](../03-rag/README.md)

**Function Calling** - Modelul generează cereri structurate pentru a apela funcții externe. [Modul 04](../04-tools/README.md)

**Hallucination** - Când modelele generează informații incorecte dar plauzibile.

**Prompt** - Textul introdus într-un model de limbaj. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Căutare după semnificație utilizând embeddings, nu cuvinte cheie. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Fără stare: fără memorie. Cu stare: păstrează istoricul conversațiilor. [Modul 01](../01-introduction/README.md)

**Tokens** - Unități de bază de text pe care modelele le procesează. Afectează costurile și limitele. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Execuția secvențială a uneltelor în care ieșirea informează următoarea apelare. [Modul 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Creează interfețe de servicii AI tip-safe.

**OpenAiOfficialChatModel** - Client unificat pentru modelele OpenAI și Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Creează embeddings folosind clientul OpenAI Official (suportă atât OpenAI cât și Azure OpenAI).

**ChatModel** - Interfața de bază pentru modelele de limbaj.

**ChatMemory** - Menține istoricul conversațiilor.

**ContentRetriever** - Găsește segmente de document relevante pentru RAG.

**DocumentSplitter** - Împarte documentele în segmente.

**EmbeddingModel** - Convertește textul în vectori numerici.

**EmbeddingStore** - Stochează și recuperează embeddings.

**MessageWindowChatMemory** - Menține o fereastră glisantă a mesajelor recente.

**PromptTemplate** - Creează prompturi reutilizabile cu placeholder-e `{{variable}}`.

**TextSegment** - Segment de text cu metadate. Folosit în RAG.

**ToolExecutionRequest** - Reprezintă o cerere de execuție a unei unelte.

**UserMessage / AiMessage / SystemMessage** - Tipuri de mesaje în conversație.

## AI/ML Concepts

**Few-Shot Learning** - Furnizarea de exemple în prompturi. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Modele AI antrenate pe cantități vaste de date text.

**Reasoning Effort** - Parametru GPT-5 care controlează profunzimea raționamentului. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Controlează aleatorietatea ieșirii. Scăzut=determinist, ridicat=creativ.

**Vector Database** - Bază de date specializată pentru embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Realizarea sarcinilor fără exemple. [Modul 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Raționament pas cu pas pentru o acuratețe mai bună.

**Constrained Output** - Impunerea unui format sau structură specifică.

**High Eagerness** - Tipar GPT-5 pentru raționament minuțios.

**Low Eagerness** - Tipar GPT-5 pentru răspunsuri rapide.

**Multi-Turn Conversation** - Menținerea contextului pe parcursul schimburilor.

**Role-Based Prompting** - Setarea unei persoane pentru model prin mesaje de sistem.

**Self-Reflection** - Modelul evaluează și îmbunătățește propria ieșire.

**Structured Analysis** - Cadrul fix de evaluare.

**Task Execution Pattern** - Plan → Execută → Rezumă.

## RAG (Generare augmentată prin recuperare) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Încărcare → împărțire în segmente → embedding → stocare.

**In-Memory Embedding Store** - Stocare nepersistentă pentru testare.

**RAG** - Combină recuperarea cu generarea pentru a ancora răspunsurile.

**Similarity Score** - Măsură (0-1) a similarității semantice.

**Source Reference** - Metadate despre conținutul recuperat.

## Agents and Tools - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Marchează metode Java ca unelte pe care AI le poate apela.

**ReAct Pattern** - Raționare → Acțiune → Observare → Repetare.

**Session Management** - Contexte separate pentru utilizatori diferiți.

**Tool** - Funcție pe care un agent AI o poate apela.

**Tool Description** - Documentație a scopului și parametrilor unei unelte.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**MCP** - Standard pentru conectarea aplicațiilor AI la unelte externe.

**MCP Client** - Aplicație care se conectează la servere MCP.

**MCP Server** - Serviciu care expune unelte prin MCP.

**Stdio Transport** - Server ca subproces prin stdin/stdout.

**Tool Discovery** - Clientul interoghează serverul pentru uneltele disponibile.

## Azure Services - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Căutare în cloud cu capabilități vectoriale. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Deplasează resurse Azure.

**Azure OpenAI** - Serviciul AI enterprise al Microsoft.

**Bicep** - Limbaj pentru infrastructură ca cod în Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Nume pentru implementarea modelului în Azure.

**GPT-5** - Cel mai recent model OpenAI cu control al raționamentului. [Modul 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Mediu de dezvoltare containerizat. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Playground gratuit pentru modele AI. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testare cu stocare în memorie.

**Integration Testing** - Testare cu infrastructură reală.

**Maven** - Unealtă de automatizare a build-ului Java.

**Mockito** - Framework Java pentru mocking.

**Spring Boot** - Framework pentru aplicații Java. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Declinare de responsabilitate:
Acest document a fost tradus folosind serviciul de traducere AI [Co-op Translator](https://github.com/Azure/co-op-translator). Deși ne străduim pentru acuratețe, vă rugăm să rețineți că traducerile automate pot conține erori sau inexactități. Documentul original, în limba sa nativă, trebuie considerat sursa autorizată. Pentru informații critice, se recomandă o traducere profesională realizată de un traducător uman. Nu ne asumăm răspunderea pentru eventuale neînțelegeri sau interpretări greșite care pot rezulta din utilizarea acestei traduceri.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->