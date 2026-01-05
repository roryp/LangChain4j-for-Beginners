<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T01:48:54+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "no"
}
-->
# LangChain4j Ordliste

## Innholdsfortegnelse

- [Kjernebegreper](../../../docs)
- [LangChain4j-komponenter](../../../docs)
- [AI/ML-begreper](../../../docs)
- [Promptutforming](../../../docs)
- [RAG (henteforsterket generering)](../../../docs)
- [Agenter og verktøy](../../../docs)
- [Modellkontekstprotokoll (MCP)](../../../docs)
- [Azure-tjenester](../../../docs)
- [Testing og utvikling](../../../docs)

Rask referanse for begreper og konsepter brukt gjennom kurset.

## Kjernebegreper

**AI-agent** - System som bruker AI for å resonnere og handle autonomt. [Modul 04](../04-tools/README.md)

**Kjede** - Sekvens av operasjoner der output går videre til neste steg.

**Chunking** - Å dele dokumenter i mindre biter. Typisk: 300-500 tokens med overlapp. [Module 03](../03-rag/README.md)

**Kontekstvindu** - Maksimalt antall tokens en modell kan prosessere. GPT-5: 400K tokens.

**Embeddings** - Numeriske vektorer som representerer tekstens betydning. [Module 03](../03-rag/README.md)

**Funksjonskalling** - Modellen genererer strukturerte forespørsler for å kalle eksterne funksjoner. [Module 04](../04-tools/README.md)

**Hallusinasjon** - Når modeller genererer feil, men plausible, informasjon.

**Prompt** - Tekstinput til en språkmodell. [Module 02](../02-prompt-engineering/README.md)

**Semantisk søk** - Søk basert på mening ved bruk av embeddings, ikke nøkkelord. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: uten minne. Stateful: opprettholder samtalehistorikk. [Module 01](../01-introduction/README.md)

**Tokens** - Grunnleggende tekst-enheter modeller prosesserer. Påvirker kostnader og begrensninger. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sekvensiell verktøyutførelse der output informerer neste kall. [Module 04](../04-tools/README.md)

## LangChain4j-komponenter

**AiServices** - Oppretter typesikre AI-tjenestegrensesnitt.

**OpenAiOfficialChatModel** - Enhetlig klient for OpenAI og Azure OpenAI-modeller.

**OpenAiOfficialEmbeddingModel** - Lager embeddings ved bruk av OpenAI Official-klienten (støtter både OpenAI og Azure OpenAI).

**ChatModel** - Kjernegrensesnitt for språkmodeller.

**ChatMemory** - Opprettholder samtalehistorikk.

**ContentRetriever** - Finner relevante dokumentbiter for RAG.

**DocumentSplitter** - Deler dokumenter i biter.

**EmbeddingModel** - Konverterer tekst til numeriske vektorer.

**EmbeddingStore** - Lagrer og henter embeddings.

**MessageWindowChatMemory** - Opprettholder et glidende vindu med nylige meldinger.

**PromptTemplate** - Lager gjenbrukbare prompts med `{{variable}}`-plassholdere.

**TextSegment** - Tekstbit med metadata. Brukes i RAG.

**ToolExecutionRequest** - Representerer en forespørsel om verktøyutførelse.

**UserMessage / AiMessage / SystemMessage** - Typer samtalemeldinger.

## AI/ML-begreper

**Few-Shot Learning** - Gi eksempler i prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI-modeller trent på store mengder tekstdata.

**Reasoning Effort** - GPT-5-parameter som kontrollerer tenkedybde. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Kontrollerer tilfeldighet i output. Lav=deterministisk, høy=kreativ.

**Vector Database** - Spesialisert database for embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Utføre oppgaver uten eksempler. [Module 02](../02-prompt-engineering/README.md)

## Promptutforming - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Trinnvis resonnement for bedre nøyaktighet.

**Constrained Output** - Håndheving av spesifikt format eller struktur.

**High Eagerness** - GPT-5-mønster for grundig resonnement.

**Low Eagerness** - GPT-5-mønster for raske svar.

**Multi-Turn Conversation** - Opprettholde kontekst over flere utvekslinger.

**Role-Based Prompting** - Sette modellens persona via systemmeldinger.

**Self-Reflection** - Modellen evaluerer og forbedrer sitt output.

**Structured Analysis** - Fast evalueringsrammeverk.

**Task Execution Pattern** - Plan → Execute → Summarize.

## RAG (henteforsterket generering) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Last inn → del opp → embed → lagre.

**In-Memory Embedding Store** - Ikke-persistent lagring for testing.

**RAG** - Kombinerer innhenting med generering for å forankre svar.

**Similarity Score** - Måling (0-1) av semantisk likhet.

**Source Reference** - Metadata om hentet innhold.

## Agenter og verktøy - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Marker Java-metoder som AI-kallbare verktøy.

**ReAct Pattern** - Resonner → Handle → Observer → Gjenta.

**Session Management** - Adskilte kontekster for ulike brukere.

**Tool** - Funksjon et AI-agent kan kalle.

**Tool Description** - Dokumentasjon av verktøyets formål og parametre.

## Modellkontekstprotokoll (MCP) - [Modul 05](../05-mcp/README.md)

**MCP** - Standard for å koble AI-apper til eksterne verktøy.

**MCP Client** - Applikasjon som kobler til MCP-servere.

**MCP Server** - Tjeneste som eksponerer verktøy via MCP.

**Stdio Transport** - Server som underprosess via stdin/stdout.

**Tool Discovery** - Klient spør serveren om tilgjengelige verktøy.

## Azure-tjenester - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Skysøk med vektorfunksjonalitet. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Distribuerer Azure-ressurser.

**Azure OpenAI** - Microsofts bedrifts-AI-tjeneste.

**Bicep** - Azure infrastruktur-som-kode-språk. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Navn for modelldistribusjon i Azure.

**GPT-5** - Nyeste OpenAI-modell med kontroll over resonnering. [Module 02](../02-prompt-engineering/README.md)

## Testing og utvikling - [Testguide](TESTING.md)

**Dev Container** - Containerisert utviklingsmiljø. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Gratis AI-modell-lekeplass. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testing med minnelagring.

**Integration Testing** - Testing med reell infrastruktur.

**Maven** - Java verktøy for byggeautomatisering.

**Mockito** - Java-rammeverk for mocking.

**Spring Boot** - Java applikasjonsrammeverk. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokumentet er oversatt ved hjelp av AI-oversettelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selv om vi streber etter nøyaktighet, vær oppmerksom på at automatiske oversettelser kan inneholde feil eller unøyaktigheter. Det opprinnelige dokumentet på originalspråket bør anses som den autoritative kilden. For kritisk informasjon anbefales profesjonell menneskelig oversettelse. Vi er ikke ansvarlige for eventuelle misforståelser eller feiltolkninger som følge av bruk av denne oversettelsen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->