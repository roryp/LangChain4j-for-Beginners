<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:10:14+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "no"
}
-->
# LangChain4j Ordliste

## Innholdsfortegnelse

- [Kjernebegreper](../../../docs)
- [LangChain4j-komponenter](../../../docs)
- [AI/ML-begreper](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenter og verktøy](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure-tjenester](../../../docs)
- [Testing og utvikling](../../../docs)

Rask referanse for termer og begreper brukt gjennom hele kurset.

## Kjernebegreper

**AI-agent** - System som bruker AI for å resonnere og handle autonomt. [Modul 04](../04-tools/README.md)

**Kjede** - Sekvens av operasjoner der output mates inn i neste steg.

**Chunking** - Å dele dokumenter i mindre biter. Typisk: 300-500 tokens med overlapp. [Modul 03](../03-rag/README.md)

**Kontekstvindu** - Maksimalt antall tokens en modell kan prosessere. GPT-5: 400K tokens.

**Inbeddinger** - Numeriske vektorer som representerer tekstens mening. [Modul 03](../03-rag/README.md)

**Funksjonskall** - Modell genererer strukturerte forespørsler for å kalle eksterne funksjoner. [Modul 04](../04-tools/README.md)

**Hallusinasjon** - Når modeller genererer feilaktig, men plausibel informasjon.

**Prompt** - Tekstinput til en språkmodell. [Modul 02](../02-prompt-engineering/README.md)

**Semantisk søk** - Søking basert på mening ved bruk av inbeddinger, ikke nøkkelord. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ingen hukommelse. Stateful: opprettholder samtalehistorikk. [Modul 01](../01-introduction/README.md)

**Tokens** - Grunnleggende tekst-enheter som modeller prosesserer. Påvirker kostnader og begrensninger. [Modul 01](../01-introduction/README.md)

**Verktøykjedning** - Sekvensiell verktøyutførelse der output informerer neste kall. [Modul 04](../04-tools/README.md)

## LangChain4j-komponenter

**AiServices** - Lager typesikre AI-tjenestegrensesnitt.

**OpenAiOfficialChatModel** - Enhetlig klient for OpenAI og Azure OpenAI-modeller.

**OpenAiOfficialEmbeddingModel** - Lager inbeddinger ved bruk av OpenAI Official-klient (støtter både OpenAI og Azure OpenAI).

**ChatModel** - Kjernegrensesnitt for språkmodeller.

**ChatMemory** - Opprettholder samtalehistorikk.

**ContentRetriever** - Finner relevante dokumentbiter for RAG.

**DocumentSplitter** - Deler dokumenter i biter.

**EmbeddingModel** - Konverterer tekst til numeriske vektorer.

**EmbeddingStore** - Lagrer og henter inbeddinger.

**MessageWindowChatMemory** - Opprettholder et glidende vindu av nylige meldinger.

**PromptTemplate** - Lager gjenbrukbare prompts med `{{variable}}`-plassholdere.

**TextSegment** - Tekstbit med metadata. Brukes i RAG.

**ToolExecutionRequest** - Representerer forespørsel om verktøyutførelse.

**UserMessage / AiMessage / SystemMessage** - Samtalemeldingstyper.

## AI/ML-begreper

**Few-Shot Learning** - Å gi eksempler i prompts. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI-modeller trent på store mengder tekstdata.

**Reasoning Effort** - GPT-5-parameter som styrer tenkedybde. [Modul 02](../02-prompt-engineering/README.md)

**Temperatur** - Styrer output-tilfeldighet. Lav=deterministisk, høy=kreativ.

**Vektordatabaser** - Spesialiserte databaser for inbeddinger. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Utføre oppgaver uten eksempler. [Modul 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Trinnvis resonnement for bedre nøyaktighet.

**Begrenset output** - Påtvinge spesifikt format eller struktur.

**Høy iver** - GPT-5-mønster for grundig resonnement.

**Lav iver** - GPT-5-mønster for raske svar.

**Multi-Turn Conversation** - Opprettholde kontekst over flere utvekslinger.

**Rollebassert prompting** - Sette modellens persona via systemmeldinger.

**Selvrefleksjon** - Modell evaluerer og forbedrer sin output.

**Strukturert analyse** - Fast evalueringsrammeverk.

**Oppgaveutførelsesmønster** - Planlegg → Utfør → Oppsummer.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Dokumentbehandlingspipeline** - Last inn → del opp → embed → lagre.

**In-Memory Embedding Store** - Ikke-permanent lagring for testing.

**RAG** - Kombinerer gjenfinning med generering for å forankre svar.

**Likhetsscore** - Mål (0-1) på semantisk likhet.

**Kildereferanse** - Metadata om hentet innhold.

## Agenter og verktøy - [Modul 04](../04-tools/README.md)

**@Tool-annotasjon** - Marker Java-metoder som AI-kallbare verktøy.

**ReAct-mønster** - Resonner → Handle → Observer → Gjenta.

**Sesjonshåndtering** - Separate kontekster for ulike brukere.

**Verktøy** - Funksjon en AI-agent kan kalle.

**Verktøybeskrivelse** - Dokumentasjon av verktøyets formål og parametere.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**Docker Transport** - MCP-server i Docker-container.

**MCP** - Standard for å koble AI-apper til eksterne verktøy.

**MCP-klient** - Applikasjon som kobler til MCP-servere.

**MCP-server** - Tjeneste som eksponerer verktøy via MCP.

**Server-Sent Events (SSE)** - Server-til-klient streaming over HTTP.

**Stdio Transport** - Server som underprosess via stdin/stdout.

**Streamable HTTP Transport** - HTTP med SSE for sanntidskommunikasjon.

**Verktøyoppdagelse** - Klient spør server om tilgjengelige verktøy.

## Azure-tjenester - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Skybasert søk med vektorfunksjonalitet. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Distribuerer Azure-ressurser.

**Azure OpenAI** - Microsofts AI-tjeneste for bedrifter.

**Bicep** - Azure infrastruktur-som-kode språk. [Infrastrukturguide](../01-introduction/infra/README.md)

**Distribusjonsnavn** - Navn for modellutplassering i Azure.

**GPT-5** - Nyeste OpenAI-modell med styring av resonnement. [Modul 02](../02-prompt-engineering/README.md)

## Testing og utvikling - [Testing Guide](TESTING.md)

**Dev Container** - Containerisert utviklingsmiljø. [Konfigurasjon](../../../.devcontainer/devcontainer.json)

**GitHub-modeller** - Gratis AI-modell-lekeplass. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testing med in-memory lagring.

**Integrasjonstesting** - Testing med ekte infrastruktur.

**Maven** - Java byggautomatiseringsverktøy.

**Mockito** - Java mocking-rammeverk.

**Spring Boot** - Java applikasjonsrammeverk. [Modul 01](../01-introduction/README.md)

**Testcontainers** - Docker-containere i tester.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokumentet er oversatt ved hjelp av AI-oversettelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selv om vi streber etter nøyaktighet, vennligst vær oppmerksom på at automatiske oversettelser kan inneholde feil eller unøyaktigheter. Det opprinnelige dokumentet på originalspråket skal anses som den autoritative kilden. For kritisk informasjon anbefales profesjonell menneskelig oversettelse. Vi er ikke ansvarlige for eventuelle misforståelser eller feiltolkninger som oppstår ved bruk av denne oversettelsen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->