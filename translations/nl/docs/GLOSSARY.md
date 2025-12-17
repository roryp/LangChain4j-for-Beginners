<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:11:39+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "nl"
}
-->
# LangChain4j Verklarende Woordenlijst

## Inhoudsopgave

- [Kernconcepten](../../../docs)
- [LangChain4j Componenten](../../../docs)
- [AI/ML Concepten](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenten en Tools](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Services](../../../docs)
- [Testen en Ontwikkeling](../../../docs)

Snelle referentie voor termen en concepten die door de hele cursus worden gebruikt.

## Kernconcepten

**AI Agent** - Systeem dat AI gebruikt om autonoom te redeneren en te handelen. [Module 04](../04-tools/README.md)

**Chain** - Reeks bewerkingen waarbij de output in de volgende stap wordt gebruikt.

**Chunking** - Documenten opdelen in kleinere stukken. Typisch: 300-500 tokens met overlap. [Module 03](../03-rag/README.md)

**Context Window** - Maximale tokens die een model kan verwerken. GPT-5: 400K tokens.

**Embeddings** - Numerieke vectoren die de betekenis van tekst representeren. [Module 03](../03-rag/README.md)

**Function Calling** - Model genereert gestructureerde verzoeken om externe functies aan te roepen. [Module 04](../04-tools/README.md)

**Hallucinatie** - Wanneer modellen onjuiste maar plausibele informatie genereren.

**Prompt** - Tekstinvoer voor een taalmodel. [Module 02](../02-prompt-engineering/README.md)

**Semantisch Zoeken** - Zoeken op betekenis met embeddings, niet op trefwoorden. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: geen geheugen. Stateful: onderhoudt gespreksgeschiedenis. [Module 01](../01-introduction/README.md)

**Tokens** - Basis tekstunits die modellen verwerken. Beïnvloedt kosten en limieten. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Opeenvolgende uitvoering van tools waarbij output de volgende aanroep informeert. [Module 04](../04-tools/README.md)

## LangChain4j Componenten

**AiServices** - Maakt type-veilige AI service interfaces.

**OpenAiOfficialChatModel** - Geünificeerde client voor OpenAI en Azure OpenAI modellen.

**OpenAiOfficialEmbeddingModel** - Maakt embeddings met OpenAI Official client (ondersteunt zowel OpenAI als Azure OpenAI).

**ChatModel** - Kerninterface voor taalmodellen.

**ChatMemory** - Onderhoudt gespreksgeschiedenis.

**ContentRetriever** - Vindt relevante documentstukken voor RAG.

**DocumentSplitter** - Splitst documenten in stukken.

**EmbeddingModel** - Zet tekst om in numerieke vectoren.

**EmbeddingStore** - Slaat embeddings op en haalt ze op.

**MessageWindowChatMemory** - Onderhoudt een schuivend venster van recente berichten.

**PromptTemplate** - Maakt herbruikbare prompts met `{{variable}}` placeholders.

**TextSegment** - Tekststuk met metadata. Gebruikt in RAG.

**ToolExecutionRequest** - Vertegenwoordigt een tool-uitvoeringsverzoek.

**UserMessage / AiMessage / SystemMessage** - Gesprek berichttypes.

## AI/ML Concepten

**Few-Shot Learning** - Voorzien van voorbeelden in prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI modellen getraind op enorme tekstdata.

**Reasoning Effort** - GPT-5 parameter die de diepte van het denken regelt. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Regelt de willekeurigheid van output. Laag=deterministisch, hoog=creatief.

**Vector Database** - Gespecialiseerde database voor embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Taken uitvoeren zonder voorbeelden. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Stapsgewijze redenering voor betere nauwkeurigheid.

**Constrained Output** - Afdwingen van een specifiek formaat of structuur.

**High Eagerness** - GPT-5 patroon voor grondige redenering.

**Low Eagerness** - GPT-5 patroon voor snelle antwoorden.

**Multi-Turn Conversation** - Context behouden over meerdere uitwisselingen.

**Role-Based Prompting** - Instellen van modelpersona via systeemberichten.

**Self-Reflection** - Model evalueert en verbetert zijn output.

**Structured Analysis** - Vaste evaluatiekader.

**Task Execution Pattern** - Plannen → Uitvoeren → Samenvatten.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Laden → opdelen → embedden → opslaan.

**In-Memory Embedding Store** - Niet-persistente opslag voor testen.

**RAG** - Combineert ophalen met genereren om antwoorden te onderbouwen.

**Similarity Score** - Maat (0-1) van semantische gelijkenis.

**Source Reference** - Metadata over opgehaalde inhoud.

## Agenten en Tools - [Module 04](../04-tools/README.md)

**@Tool Annotatie** - Markeert Java-methoden als AI-aanroepbare tools.

**ReAct Patroon** - Redeneren → Handelen → Observeren → Herhalen.

**Session Management** - Gescheiden contexten voor verschillende gebruikers.

**Tool** - Functie die een AI-agent kan aanroepen.

**Tool Beschrijving** - Documentatie van tooldoel en parameters.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**Docker Transport** - MCP server in Docker container.

**MCP** - Standaard voor het verbinden van AI-apps met externe tools.

**MCP Client** - Applicatie die verbinding maakt met MCP servers.

**MCP Server** - Dienst die tools via MCP aanbiedt.

**Server-Sent Events (SSE)** - Server-naar-client streaming via HTTP.

**Stdio Transport** - Server als subprocess via stdin/stdout.

**Streamable HTTP Transport** - HTTP met SSE voor realtime communicatie.

**Tool Discovery** - Client vraagt server om beschikbare tools.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloud-zoekdienst met vectormogelijkheden. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Zet Azure resources uit.

**Azure OpenAI** - Microsofts enterprise AI-dienst.

**Bicep** - Azure infrastructuur-als-code taal. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Naam voor modeluitrol in Azure.

**GPT-5** - Laatste OpenAI model met redeneersturing. [Module 02](../02-prompt-engineering/README.md)

## Testen en Ontwikkeling - [Testing Guide](TESTING.md)

**Dev Container** - Gecontaineriseerde ontwikkelomgeving. [Configuratie](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Gratis AI model speelveld. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testen met in-memory opslag.

**Integration Testing** - Testen met echte infrastructuur.

**Maven** - Java build automatiseringstool.

**Mockito** - Java mocking framework.

**Spring Boot** - Java applicatieframework. [Module 01](../01-introduction/README.md)

**Testcontainers** - Docker containers in tests.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:  
Dit document is vertaald met behulp van de AI-vertalingsdienst [Co-op Translator](https://github.com/Azure/co-op-translator). Hoewel we streven naar nauwkeurigheid, dient u er rekening mee te houden dat geautomatiseerde vertalingen fouten of onnauwkeurigheden kunnen bevatten. Het originele document in de oorspronkelijke taal moet als de gezaghebbende bron worden beschouwd. Voor cruciale informatie wordt professionele menselijke vertaling aanbevolen. Wij zijn niet aansprakelijk voor eventuele misverstanden of verkeerde interpretaties die voortvloeien uit het gebruik van deze vertaling.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->