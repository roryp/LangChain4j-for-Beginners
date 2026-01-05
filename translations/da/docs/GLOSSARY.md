<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T01:35:00+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "da"
}
-->
# LangChain4j Ordliste

## Indholdsfortegnelse

- [Kernebegreber](../../../docs)
- [LangChain4j-komponenter](../../../docs)
- [AI/ML-begreber](../../../docs)
- [Prompt-udformning](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenter og værktøjer](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure-tjenester](../../../docs)
- [Test og udvikling](../../../docs)

Hurtig reference for termer og begreber, der bruges i hele kurset.

## Kernebegreber

**AI-agent** - System der bruger AI til at ræsonnere og handle autonomt. [Module 04](../04-tools/README.md)

**Kæde** - Sekvens af operationer, hvor output bruges som input til næste trin.

**Chunking** - Opdeling af dokumenter i mindre stykker. Typisk: 300-500 tokens med overlap. [Module 03](../03-rag/README.md)

**Kontekstvindue** - Maksimalt antal tokens en model kan håndtere. GPT-5: 400K tokens.

**Embeddings** - Numeriske vektorer, der repræsenterer tekstens betydning. [Module 03](../03-rag/README.md)

**Function Calling** - Modellen genererer strukturerede forespørgsler for at kalde eksterne funktioner. [Module 04](../04-tools/README.md)

**Hallucination** - Når modeller genererer forkerte, men plausible oplysninger.

**Prompt** - Tekstinput til en sprogmodel. [Module 02](../02-prompt-engineering/README.md)

**Semantisk søgning** - Søgning efter betydning ved hjælp af embeddings, ikke nøgleord. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ingen hukommelse. Stateful: bevarer samtalehistorik. [Module 01](../01-introduction/README.md)

**Tokens** - Grundlæggende tekstenheder, som modeller behandler. Påvirker omkostninger og begrænsninger. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sekventiel udførelse af værktøjer, hvor output informerer næste kald. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Opretter typesikre AI-servicegrænseflader.

**OpenAiOfficialChatModel** - Forenet klient til OpenAI- og Azure OpenAI-modeller.

**OpenAiOfficialEmbeddingModel** - Opretter embeddings ved hjælp af OpenAI Official-clienten (understøtter både OpenAI og Azure OpenAI).

**ChatModel** - Kernegrænseflade for sprogmodeller.

**ChatMemory** - Vedligeholder samtalehistorik.

**ContentRetriever** - Finder relevante dokumentstykker til RAG.

**DocumentSplitter** - Splitter dokumenter i stykker.

**EmbeddingModel** - Konverterer tekst til numeriske vektorer.

**EmbeddingStore** - Gemmer og henter embeddings.

**MessageWindowChatMemory** - Vedligeholder et glidende vindue af nyere beskeder.

**PromptTemplate** - Opretter genanvendelige prompts med `{{variable}}`-pladsholdere.

**TextSegment** - Tekststykke med metadata. Bruges i RAG.

**ToolExecutionRequest** - Repræsenterer en anmodning om værktøjsudførelse.

**UserMessage / AiMessage / SystemMessage** - Samtalebesked-typer.

## AI/ML-begreber

**Few-Shot Learning** - At give eksempler i prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI-modeller trænet på enorme mængder tekstdata.

**Reasoning Effort** - GPT-5-parameter der styrer dybden af ræsonnement. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Styrer output-tilfældighed. Lav = deterministisk, høj = kreativ.

**Vector Database** - Specialiseret database til embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Udførelse af opgaver uden eksempler. [Module 02](../02-prompt-engineering/README.md)

## Prompt-udformning - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Trinvis ræsonnement for bedre præcision.

**Constrained Output** - Pålæggelse af specifikt format eller struktur.

**High Eagerness** - GPT-5-mønster for grundigt ræsonnement.

**Low Eagerness** - GPT-5-mønster for hurtige svar.

**Multi-Turn Conversation** - Bevaring af kontekst på tværs af udvekslinger.

**Role-Based Prompting** - Indstilling af modelpersona via systembeskeder.

**Self-Reflection** - Modellen evaluerer og forbedrer sit output.

**Structured Analysis** - Fast evalueringsramme.

**Task Execution Pattern** - Planlæg → Udfør → Opsummer.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Indlæs → opdel → indlejre → gem.

**In-Memory Embedding Store** - Ikke-permanent lager til test.

**RAG** - Kombinerer hentning med generering for at forankre svar.

**Similarity Score** - Mål (0-1) for semantisk lighed.

**Source Reference** - Metadata om hentet indhold.

## Agenter og værktøjer - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Marker Java-metoder som AI-opkaldelige værktøjer.

**ReAct Pattern** - Ræsonner → Handl → Observer → Gentag.

**Session Management** - Separate kontekster for forskellige brugere.

**Tool** - Et værktøj, som en AI-agent kan kalde.

**Tool Description** - Dokumentation af værktøjets formål og parametre.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - Standard for at forbinde AI-apps til eksterne værktøjer.

**MCP Client** - Applikation, der forbinder til MCP-servere.

**MCP Server** - Service, der eksponerer værktøjer via MCP.

**Stdio Transport** - Server som underproces via stdin/stdout.

**Tool Discovery** - Klienten spørger serveren om tilgængelige værktøjer.

## Azure-tjenester - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloud-søgning med vektorfunktionalitet. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Udruller Azure-ressourcer.

**Azure OpenAI** - Microsofts enterprise AI-tjeneste.

**Bicep** - Azure-infrastruktur-som-kode-sprog. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Navn for modeludrulning i Azure.

**GPT-5** - Seneste OpenAI-model med kontrol over ræsonnement. [Module 02](../02-prompt-engineering/README.md)

## Test og udvikling - [Testguide](TESTING.md)

**Dev Container** - Containeriseret udviklingsmiljø. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Gratis AI-model-legeplads. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testning med in-memory-lagring.

**Integration Testing** - Testning med reel infrastruktur.

**Maven** - Java build-automatiseringsværktøj.

**Mockito** - Java-mocking-ramme.

**Spring Boot** - Java applikationsramme. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Ansvarsfraskrivelse:
Dette dokument er blevet oversat ved hjælp af AI-oversættelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selvom vi stræber efter nøjagtighed, bedes du være opmærksom på, at automatiske oversættelser kan indeholde fejl eller unøjagtigheder. Det oprindelige dokument på dets originalsprog bør betragtes som den autoritative kilde. For kritisk information anbefales en professionel menneskelig oversættelse. Vi er ikke ansvarlige for eventuelle misforståelser eller fejltolkninger, der måtte opstå som følge af brugen af denne oversættelse.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->