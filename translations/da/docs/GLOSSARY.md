<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:09:30+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "da"
}
-->
# LangChain4j Ordliste

## Indholdsfortegnelse

- [Kernebegreber](../../../docs)
- [LangChain4j Komponenter](../../../docs)
- [AI/ML Begreber](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenter og Værktøjer](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Tjenester](../../../docs)
- [Test og Udvikling](../../../docs)

Hurtig reference for termer og begreber brugt gennem hele kurset.

## Kernebegreber

**AI Agent** - System der bruger AI til at ræsonnere og handle autonomt. [Module 04](../04-tools/README.md)

**Chain** - Sekvens af operationer hvor output føres videre til næste trin.

**Chunking** - Opdeling af dokumenter i mindre stykker. Typisk: 300-500 tokens med overlap. [Module 03](../03-rag/README.md)

**Context Window** - Maksimalt antal tokens en model kan behandle. GPT-5: 400K tokens.

**Embeddings** - Numeriske vektorer der repræsenterer teksts betydning. [Module 03](../03-rag/README.md)

**Function Calling** - Model genererer strukturerede forespørgsler for at kalde eksterne funktioner. [Module 04](../04-tools/README.md)

**Hallucination** - Når modeller genererer ukorrekt men plausibel information.

**Prompt** - Tekstinput til en sprogmodel. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Søgning efter mening ved brug af embeddings, ikke nøgleord. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ingen hukommelse. Stateful: bevarer samtalehistorik. [Module 01](../01-introduction/README.md)

**Tokens** - Grundlæggende tekst-enheder som modeller behandler. Påvirker omkostninger og grænser. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sekventiel udførelse af værktøjer hvor output informerer næste kald. [Module 04](../04-tools/README.md)

## LangChain4j Komponenter

**AiServices** - Opretter typesikre AI service interfaces.

**OpenAiOfficialChatModel** - Unified klient til OpenAI og Azure OpenAI modeller.

**OpenAiOfficialEmbeddingModel** - Opretter embeddings ved brug af OpenAI Official klient (understøtter både OpenAI og Azure OpenAI).

**ChatModel** - Kerneinterface for sprogmodeller.

**ChatMemory** - Bevarer samtalehistorik.

**ContentRetriever** - Finder relevante dokumentstykker til RAG.

**DocumentSplitter** - Opdeler dokumenter i stykker.

**EmbeddingModel** - Konverterer tekst til numeriske vektorer.

**EmbeddingStore** - Gemmer og henter embeddings.

**MessageWindowChatMemory** - Bevarer et glidende vindue af nylige beskeder.

**PromptTemplate** - Opretter genanvendelige prompts med `{{variable}}` pladsholdere.

**TextSegment** - Tekststykke med metadata. Bruges i RAG.

**ToolExecutionRequest** - Repræsenterer værktøjsudførelsesanmodning.

**UserMessage / AiMessage / SystemMessage** - Samtale beskedtyper.

## AI/ML Begreber

**Few-Shot Learning** - At give eksempler i prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI modeller trænet på enorme mængder tekstdata.

**Reasoning Effort** - GPT-5 parameter der styrer tænkningsdybde. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Styrer output tilfældighed. Lav=deterministisk, høj=kreativ.

**Vector Database** - Specialiseret database til embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Udførelse af opgaver uden eksempler. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Trinvis ræsonnering for bedre nøjagtighed.

**Constrained Output** - Håndhævelse af specifikt format eller struktur.

**High Eagerness** - GPT-5 mønster for grundig ræsonnering.

**Low Eagerness** - GPT-5 mønster for hurtige svar.

**Multi-Turn Conversation** - Bevare kontekst på tværs af udvekslinger.

**Role-Based Prompting** - Sæt modelpersona via systembeskeder.

**Self-Reflection** - Model evaluerer og forbedrer sit output.

**Structured Analysis** - Fast evalueringsramme.

**Task Execution Pattern** - Planlæg → Udfør → Opsummer.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Indlæs → opdel → embed → gem.

**In-Memory Embedding Store** - Ikke-permanent lager til test.

**RAG** - Kombinerer hentning med generering for at forankre svar.

**Similarity Score** - Mål (0-1) for semantisk lighed.

**Source Reference** - Metadata om hentet indhold.

## Agenter og Værktøjer - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Marker Java metoder som AI-kaldbare værktøjer.

**ReAct Pattern** - Ræsonner → Handl → Observer → Gentag.

**Session Management** - Separate kontekster for forskellige brugere.

**Tool** - Funktion en AI agent kan kalde.

**Tool Description** - Dokumentation af værktøjets formål og parametre.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**Docker Transport** - MCP server i Docker container.

**MCP** - Standard for at forbinde AI apps til eksterne værktøjer.

**MCP Client** - Applikation der forbinder til MCP servere.

**MCP Server** - Service der eksponerer værktøjer via MCP.

**Server-Sent Events (SSE)** - Server-til-klient streaming over HTTP.

**Stdio Transport** - Server som subprocess via stdin/stdout.

**Streamable HTTP Transport** - HTTP med SSE til realtidskommunikation.

**Tool Discovery** - Client forespørger server om tilgængelige værktøjer.

## Azure Tjenester - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloud-søgning med vektor kapabiliteter. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Udruller Azure ressourcer.

**Azure OpenAI** - Microsofts enterprise AI service.

**Bicep** - Azure infrastruktur-som-kode sprog. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Navn for modeludrulning i Azure.

**GPT-5** - Seneste OpenAI model med ræsonneringskontrol. [Module 02](../02-prompt-engineering/README.md)

## Test og Udvikling - [Testing Guide](TESTING.md)

**Dev Container** - Containeriseret udviklingsmiljø. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Gratis AI model legeplads. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Test med in-memory lager.

**Integration Testing** - Test med rigtig infrastruktur.

**Maven** - Java build automatiseringsværktøj.

**Mockito** - Java mocking framework.

**Spring Boot** - Java applikationsframework. [Module 01](../01-introduction/README.md)

**Testcontainers** - Docker containere i tests.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokument er blevet oversat ved hjælp af AI-oversættelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selvom vi bestræber os på nøjagtighed, bedes du være opmærksom på, at automatiserede oversættelser kan indeholde fejl eller unøjagtigheder. Det oprindelige dokument på dets modersmål bør betragtes som den autoritative kilde. For kritisk information anbefales professionel menneskelig oversættelse. Vi påtager os intet ansvar for misforståelser eller fejltolkninger, der opstår som følge af brugen af denne oversættelse.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->