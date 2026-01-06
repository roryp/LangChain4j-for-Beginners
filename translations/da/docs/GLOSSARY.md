<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:12:40+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "da"
}
-->
# LangChain4j Ordliste

## Indholdsfortegnelse

- [Kernebegreber](../../../docs)
- [LangChain4j Komponenter](../../../docs)
- [AI/ML Begreber](../../../docs)
- [Guardrails](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenter og Værktøjer](../../../docs)
- [Agentic Modul](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Tjenester](../../../docs)
- [Test og Udvikling](../../../docs)

Hurtig reference til termer og begreber brugt gennem hele kurset.

## Kernebegreber

**AI Agent** - System, der bruger AI til at ræsonnere og handle autonomt. [Module 04](../04-tools/README.md)

**Chain** - Sekvens af operationer hvor output bruges i næste trin.

**Chunking** - Opdeling af dokumenter i mindre stykker. Typisk: 300-500 tokens med overlap. [Module 03](../03-rag/README.md)

**Context Window** - Maksimalt antal tokens et model kan behandle. GPT-5: 400K tokens.

**Embeddings** - Numeriske vektorer der repræsenterer tekstens betydning. [Module 03](../03-rag/README.md)

**Function Calling** - Model genererer strukturerede forespørgsler for at kalde eksterne funktioner. [Module 04](../04-tools/README.md)

**Hallucination** - Når modeller genererer ukorrekte, men plausible informationer.

**Prompt** - Tekstinput til et sprogmodel. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Søgning efter mening ved hjælp af embeddings, ikke nøgleord. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ingen hukommelse. Stateful: opretholder samtalehistorik. [Module 01](../01-introduction/README.md)

**Tokens** - Grundlæggende tekst-enheder modeller behandler. Påvirker omkostninger og grænser. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sekventiel værktøjsudførelse hvor output informerer næste kald. [Module 04](../04-tools/README.md)

## LangChain4j Komponenter

**AiServices** - Opretter type-sikre AI tjeneste interface.

**OpenAiOfficialChatModel** - Unified klient for OpenAI og Azure OpenAI modeller.

**OpenAiOfficialEmbeddingModel** - Opretter embeddings med OpenAI Official klient (understøtter både OpenAI og Azure OpenAI).

**ChatModel** - Kerneinterface til sprogmodeller.

**ChatMemory** - Opretholder samtalehistorik.

**ContentRetriever** - Finder relevante dokumentstykker til RAG.

**DocumentSplitter** - Opdeler dokumenter i stykker.

**EmbeddingModel** - Konverterer tekst til numeriske vektorer.

**EmbeddingStore** - Gemmer og henter embeddings.

**MessageWindowChatMemory** - Opretholder et glidende vindue af nylige beskeder.

**PromptTemplate** - Opretter genanvendelige prompts med `{{variable}}` pladsholdere.

**TextSegment** - Tekststykke med metadata. Bruges i RAG.

**ToolExecutionRequest** - Repræsenterer anmodning om værktøjsudførelse.

**UserMessage / AiMessage / SystemMessage** - Samtale beskedtyper.

## AI/ML Begreber

**Few-Shot Learning** - At give eksempler i prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI modeller trænet på store tekstmængder.

**Reasoning Effort** - GPT-5 parameter der styrer tænkningsdybde. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Kontrollerer output randomness. Lav=deterministisk, høj= kreativ.

**Vector Database** - Specialiseret database for embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Udfør opgaver uden eksempler. [Module 02](../02-prompt-engineering/README.md)

## Guardrails - [Module 00](../00-quick-start/README.md)

**Defense in Depth** - Flersidet sikkerheds-tilgang som kombinerer applikations-niveau guardrails med leverandørens sikkerhedsfiltre.

**Hard Block** - Udbyder kaster HTTP 400 fejl ved alvorlige indholds-overtrædelser.

**InputGuardrail** - LangChain4j interface til validering af brugerinput før det når LLM. Sparrer omkostninger og latens ved at blokere skadelige prompts tidligt.

**InputGuardrailResult** - Return type for guardrail-validering: `success()` eller `fatal("reason")`.

**OutputGuardrail** - Interface til validering af AI-responser før de returneres til brugere.

**Provider Safety Filters** - Indbyggede indholdsfiltre fra AI-udbydere (f.eks. GitHub Models) som fanger overtrædelser på API-niveau.

**Soft Refusal** - Model afviser høfligt at svare uden at kaste fejl.

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Trinvis ræsonnering for bedre nøjagtighed.

**Constrained Output** - Håndhæver specifikt format eller struktur.

**High Eagerness** - GPT-5 mønster for grundig ræsonnering.

**Low Eagerness** - GPT-5 mønster for hurtige svar.

**Multi-Turn Conversation** - Opretholder kontekst på tværs af udvekslinger.

**Role-Based Prompting** - Indstiller model persona via systembeskeder.

**Self-Reflection** - Modellen evaluerer og forbedrer sit output.

**Structured Analysis** - Fast evalueringsramme.

**Task Execution Pattern** - Planlæg → Udfør → Opsummer.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Indlæs → opdel → indlejr → gem.

**In-Memory Embedding Store** - Ikke-persistent lager til test.

**RAG** - Kombinerer genfinding med generering for at forankre svar.

**Similarity Score** - Mål (0-1) af semantisk lighed.

**Source Reference** - Metadata om hentet indhold.

## Agenter og Værktøjer - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Marker Java metoder som AI-kaldbare værktøjer.

**ReAct Pattern** - Ræsonner → Handle → Observer → Gentag.

**Session Management** - Separate kontekster for forskellige brugere.

**Tool** - Funktion en AI agent kan kalde.

**Tool Description** - Dokumentation af værktøjets formål og parametre.

## Agentic Modul - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - Marker interfaces som AI agenter med deklarativ adfærdsdefinition.

**Agent Listener** - Hook for overvågning af agentudførelse via `beforeAgentInvocation()` og `afterAgentInvocation()`.

**Agentic Scope** - Delt hukommelse hvor agenter gemmer output ved brug af `outputKey` til efterfølgende agenter.

**AgenticServices** - Fabrik til oprettelse af agenter med `agentBuilder()` og `supervisorBuilder()`.

**Conditional Workflow** - Rute baseret på betingelser til forskellige specialagenturer.

**Human-in-the-Loop** - Workflow-mønster der tilføjer menneskelige kontrolpunkter til godkendelse eller indholds-gennemgang.

**langchain4j-agentic** - Maven-afhængighed til deklarativ agent-opbygning (eksperimentel).

**Loop Workflow** - Iterer agentudførelse indtil en betingelse opfyldes (fx kvalitetsscore ≥ 0.8).

**outputKey** - Agent-annotationsparameter der angiver hvor resultater gemmes i Agentic Scope.

**Parallel Workflow** - Kør flere agenter samtidigt til uafhængige opgaver.

**Response Strategy** - Hvordan supervisor formulerer endeligt svar: LAST, SUMMARY eller SCORED.

**Sequential Workflow** - Udfør agenter i rækkefølge hvor output flyder til næste trin.

**Supervisor Agent Pattern** - Avanceret agent-mønster hvor en supervisor LLM dynamisk beslutter hvilke sub-agenter der skal kaldes.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - Maven-afhængighed til MCP integration i LangChain4j.

**MCP** - Model Context Protocol: standard for at forbinde AI apps til eksterne værktøjer. Byg én gang, brug overalt.

**MCP Client** - Applikation der forbinder til MCP servere for at finde og bruge værktøjer.

**MCP Server** - Tjeneste der eksponerer værktøjer via MCP med klare beskrivelser og parameterskemaer.

**McpToolProvider** - LangChain4j komponent der pakker MCP værktøjer til brug i AI services og agenter.

**McpTransport** - Interface til MCP kommunikation. Implementeringer inkluderer Stdio og HTTP.

**Stdio Transport** - Lokal proces transport via stdin/stdout. Nyttig til filsystemadgang eller kommandolinjeværktøjer.

**StdioMcpTransport** - LangChain4j implementering der starter MCP server som underproces.

**Tool Discovery** - Client forespørger server om tilgængelige værktøjer med beskrivelser og skemaer.

## Azure Tjenester - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloud-søgning med vektor-kapabiliteter. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Udruller Azure ressourcer.

**Azure OpenAI** - Microsofts enterprise AI service.

**Bicep** - Azure infrastruktur-som-kode sprog. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Navn for modeludrulning i Azure.

**GPT-5** - Seneste OpenAI model med ræsonneringskontrol. [Module 02](../02-prompt-engineering/README.md)

## Test og Udvikling - [Testing Guide](TESTING.md)

**Dev Container** - Containeriseret udviklingsmiljø. [Konfiguration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Gratis AI model legeplads. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Test med in-memory lager.

**Integration Testing** - Test med rigtig infrastruktur.

**Maven** - Java byggeautomatiseringsværktøj.

**Mockito** - Java mocking framework.

**Spring Boot** - Java applikationsframework. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokument er blevet oversat ved hjælp af AI-oversættelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selvom vi bestræber os på nøjagtighed, skal du være opmærksom på, at automatiske oversættelser kan indeholde fejl eller unøjagtigheder. Det oprindelige dokument på dets oprindelige sprog bør betragtes som den autoritative kilde. For vigtig information anbefales professionel menneskelig oversættelse. Vi påtager os intet ansvar for misforståelser eller fejltolkninger, der måtte opstå ved brug af denne oversættelse.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->