<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:13:48+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "no"
}
-->
# LangChain4j Ordliste

## Innholdsfortegnelse

- [Kjernebegreper](../../../docs)
- [LangChain4j-komponenter](../../../docs)
- [AI/ML-begreper](../../../docs)
- [Guardrails](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenter og verktøy](../../../docs)
- [Agentmodul](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure-tjenester](../../../docs)
- [Testing og utvikling](../../../docs)

Rask referanse for termer og konsepter brukt gjennom hele kurset.

## Kjernebegreper

**AI-agent** – System som bruker AI til å resonnere og handle autonomt. [Modul 04](../04-tools/README.md)

**Kjede** – Sekvens av operasjoner hvor output mates inn i neste steg.

**Chunking** – Å dele dokumenter i mindre biter. Typisk: 300-500 token med overlapp. [Modul 03](../03-rag/README.md)

**Kontekstvindu** – Maksimalt antall token en modell kan prosessere. GPT-5: 400K token.

**Inbeddings** – Numeriske vektorer som representerer tekstens mening. [Modul 03](../03-rag/README.md)

**Funksjonskall** – Modell genererer strukturerte forespørsler for å kalle eksterne funksjoner. [Modul 04](../04-tools/README.md)

**Hallusinasjon** – Når modeller genererer feil men troverdig informasjon.

**Prompt** – Tekstinnputt til en språkmodell. [Modul 02](../02-prompt-engineering/README.md)

**Semantisk søk** – Søking etter mening ved bruk av embeddings, ikke søkeord. [Modul 03](../03-rag/README.md)

**Tilstandsfull vs tilstands­løs** – Tilstands­løs: ingen hukommelse. Tilstandsfull: opprettholder samtalehistorikk. [Modul 01](../01-introduction/README.md)

**Token** – Grunnenheter tekst modeller prosesserer. Påvirker kostnader og begrensninger. [Modul 01](../01-introduction/README.md)

**Verktøykjedning** – Sekvensiell verktøyutførelse hvor output informerer neste kall. [Modul 04](../04-tools/README.md)

## LangChain4j-komponenter

**AiServices** – Lager typesikre AI-tjenestegrensesnitt.

**OpenAiOfficialChatModel** – Enhetlig klient for OpenAI og Azure OpenAI-modeller.

**OpenAiOfficialEmbeddingModel** – Lager embeddings med OpenAI Official-klient (støtter både OpenAI og Azure OpenAI).

**ChatModel** – Kjernegrensesnitt for språkmodeller.

**ChatMemory** – Opprettholder samtalehistorikk.

**ContentRetriever** – Finner relevante dokumentbiter for RAG.

**DocumentSplitter** – Deler dokumenter i biter.

**EmbeddingModel** – Konverterer tekst til numeriske vektorer.

**EmbeddingStore** – Lagrer og henter embeddings.

**MessageWindowChatMemory** – Opprettholder et glidende vindu med nylige meldinger.

**PromptTemplate** – Lager gjenbrukbare prompts med `{{variable}}`-plassholdere.

**TextSegment** – Tekstbit med metadata. Brukes i RAG.

**ToolExecutionRequest** – Representerer verktøyutførelsesforespørsel.

**UserMessage / AiMessage / SystemMessage** – Samtaletypemeldinger.

## AI/ML-begreper

**Few-Shot Learning** – Å gi eksempler i prompts. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** – AI-modeller trent på enorme tekstmengder.

**Reasoning Effort** – GPT-5 parameter som kontrollerer resonnementets dybde. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** – Kontrollerer grad av tilfeldig variasjon i output. Lav=deterministisk, høy=kreativ.

**Vektordatabases** – Spesialisert database for embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** – Utfør oppgaver uten eksempler. [Modul 02](../02-prompt-engineering/README.md)

## Guardrails - [Modul 00](../00-quick-start/README.md)

**Defense in Depth** – Flere lag sikkerhets-tilnærming som kombinerer applikasjonsnivå-guardrails med leverandørsikkerhetsfiltre.

**Hard Block** – Leverandør kaster HTTP 400-feil ved alvorlige innholdsbrudd.

**InputGuardrail** – LangChain4j-grensesnitt for å validere brukerinput før den når LLM. Spar kostnad og ventetid ved å blokkere skadelige prompts tidlig.

**InputGuardrailResult** – Returtype for guardrail-validering: `success()` eller `fatal("årsak")`.

**OutputGuardrail** – Grensesnitt for å validere AI-responser før de returneres til brukere.

**Provider Safety Filters** – Innebygde innholdsfiltre fra AI-leverandører (f.eks. GitHub Models) som fanger brudd på API-nivå.

**Soft Refusal** – Modell avslår høflig å svare uten å kaste feil.

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** – Trinnvis resonnement for bedre nøyaktighet.

**Constrained Output** – Pålegger spesifikk format eller struktur.

**High Eagerness** – GPT-5-mønster for grundig resonnement.

**Low Eagerness** – GPT-5-mønster for raske svar.

**Multi-Turn Conversation** – Opprettholder kontekst på tvers av utvekslinger.

**Role-Based Prompting** – Setter modellpersona via systemmeldinger.

**Self-Reflection** – Modell vurderer og forbedrer eget output.

**Structured Analysis** – Fast evalueringsrammeverk.

**Task Execution Pattern** – Planlegg → Utfør → Oppsummer.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Dokumentbehandlingspipelinen** – Last inn → del i biter → embed → lagre.

**In-Memory Embedding Store** – Ikke-persistent lagring for testing.

**RAG** – Kombinerer henting med generering for å forankre svar.

**Similarity Score** – Måling (0-1) av semantisk likhet.

**Source Reference** – Metadata om hentet innhold.

## Agenter og verktøy - [Modul 04](../04-tools/README.md)

**@Tool Annotation** – Marker Java-metoder som AI-anropbare verktøy.

**ReAct Pattern** – Resonner → Handle → Observer → Gjenta.

**Session Management** – Separate kontekster for ulike brukere.

**Tool** – Funksjon en AI-agent kan kalle.

**Tool Description** – Dokumentasjon av verktøyformål og parametre.

## Agentmodul - [Modul 05](../05-mcp/README.md)

**@Agent Annotation** – Marker grensesnitt som AI-agenter med deklarativ atferdsdefinisjon.

**Agent Listener** – Hook for overvåking av agentutførelse via `beforeAgentInvocation()` og `afterAgentInvocation()`.

**Agentic Scope** – Delt minne hvor agenter lagrer output med `outputKey` for at påfølgende agenter skal konsumere.

**AgenticServices** – Fabrikk for å lage agenter med `agentBuilder()` og `supervisorBuilder()`.

**Conditional Workflow** – Rute basert på betingelser til forskjellige spesialistagenter.

**Human-in-the-Loop** – Arbeidsflytmønster som legger til menneskelige sjekkpunkter for godkjenning eller innholds­gjennomgang.

**langchain4j-agentic** – Maven-avhengighet for deklarativ agentbygging (eksperimentell).

**Loop Workflow** – Iterer agentutførelse til en betingelse er møtt (f.eks. kvalitetspoeng ≥ 0.8).

**outputKey** – Agentannotasjonsparameter som spesifiserer hvor resultater lagres i Agentic Scope.

**Parallel Workflow** – Kjør flere agenter samtidig for uavhengige oppgaver.

**Response Strategy** – Hvordan supervisor formar endelig svar: SISTE, SAMMENDRAG, eller SCORET.

**Sequential Workflow** – Utfør agenter i rekkefølge hvor output flyter til neste steg.

**Supervisor Agent Pattern** – Avansert agentmønster hvor en supervisor-LLM dynamisk bestemmer hvilke sub-agenter som skal kalles.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** – Maven-avhengighet for MCP-integrasjon i LangChain4j.

**MCP** – Model Context Protocol: standard for å koble AI-apper til eksterne verktøy. Bygg én gang, bruk overalt.

**MCP Client** – Applikasjon som kobler til MCP-servere for å oppdage og bruke verktøy.

**MCP Server** – Tjeneste som eksponerer verktøy via MCP med klare beskrivelser og parameterskjemaer.

**McpToolProvider** – LangChain4j-komponent som pakker MCP-verktøy for bruk i AI-tjenester og agenter.

**McpTransport** – Grensesnitt for MCP-kommunikasjon. Implementasjoner inkluderer Stdio og HTTP.

**Stdio Transport** – Lokal prosess-transport via stdin/stdout. Nyttig for filsystemtilgang eller kommandolinjeverktøy.

**StdioMcpTransport** – LangChain4j-implementasjon som starter MCP-server som underprosess.

**Tool Discovery** – Klient spør server om tilgjengelige verktøy med beskrivelser og skjemaer.

## Azure-tjenester - [Modul 01](../01-introduction/README.md)

**Azure AI Search** – Skybasert søk med vektorfunksjoner. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** – Distribuerer Azure-ressurser.

**Azure OpenAI** – Microsofts bedrifts-AI-tjeneste.

**Bicep** – Azure-infrastruktur-som-kode-språk. [Infrastrukturguide](../01-introduction/infra/README.md)

**Deployment Name** – Navn for modelldistribusjon i Azure.

**GPT-5** – Nyeste OpenAI-modell med styring av resonnement. [Modul 02](../02-prompt-engineering/README.md)

## Testing og utvikling - [Testing Guide](TESTING.md)

**Dev Container** – Containerisert utviklingsmiljø. [Konfigurasjon](../../../.devcontainer/devcontainer.json)

**GitHub Models** – Gratis AI-modell-lekeplass. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** – Testing med in-memory lagring.

**Integration Testing** – Testing med ekte infrastruktur.

**Maven** – Java-byggeverktøy.

**Mockito** – Java mocking-rammeverk.

**Spring Boot** – Java applikasjonsrammeverk. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokumentet er oversatt ved hjelp av AI-oversettelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selv om vi streber etter nøyaktighet, vennligst vær oppmerksom på at automatiske oversettelser kan inneholde feil eller unøyaktigheter. Det opprinnelige dokumentet på dets opprinnelige språk skal anses som den autoritative kilden. For kritisk informasjon anbefales profesjonell menneskelig oversettelse. Vi er ikke ansvarlige for eventuelle misforståelser eller feiltolkninger som oppstår som følge av bruk av denne oversettelsen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->