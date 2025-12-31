<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T02:20:25+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "nl"
}
-->
# LangChain4j Woordenlijst

## Inhoudsopgave

- [Kernbegrippen](../../../docs)
- [LangChain4j-componenten](../../../docs)
- [AI/ML-concepten](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agents en Tools](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure-diensten](../../../docs)
- [Testen en ontwikkeling](../../../docs)

Snelle referentie voor termen en concepten die in de hele cursus worden gebruikt.

## Core Concepts

**AI Agent** - Systeem dat AI gebruikt om autonoom te redeneren en te handelen. [Module 04](../04-tools/README.md)

**Chain** - Reeks bewerkingen waarbij de uitvoer in de volgende stap wordt gevoed.

**Chunking** - Het opsplitsen van documenten in kleinere stukken. Typisch: 300-500 tokens met overlap. [Module 03](../03-rag/README.md)

**Context Window** - Maximale aantal tokens dat een model kan verwerken. GPT-5: 400K tokens.

**Embeddings** - Numerieke vectoren die de betekenis van tekst weergeven. [Module 03](../03-rag/README.md)

**Function Calling** - Het model genereert gestructureerde verzoeken om externe functies aan te roepen. [Module 04](../04-tools/README.md)

**Hallucination** - Wanneer modellen onjuiste maar plausibele informatie genereren.

**Prompt** - Tekstinvoer aan een taalmodel. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Zoeken op basis van betekenis met embeddings, niet op sleutelwoorden. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: geen geheugen. Stateful: onderhoudt conversatiegeschiedenis. [Module 01](../01-introduction/README.md)

**Tokens** - Basiseenheden van tekst die door modellen worden verwerkt. Beïnvloedt kosten en limieten. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sequentiële uitvoering van tools waarbij de uitvoer de volgende aanroep informeert. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Maakt type-veilige AI-serviceinterfaces aan.

**OpenAiOfficialChatModel** - Geünificeerde client voor OpenAI- en Azure OpenAI-modellen.

**OpenAiOfficialEmbeddingModel** - Maakt embeddings met behulp van de OpenAI Official-client (ondersteunt zowel OpenAI als Azure OpenAI).

**ChatModel** - Kerninterface voor taalmodellen.

**ChatMemory** - Onderhoudt conversatiegeschiedenis.

**ContentRetriever** - Vindt relevante documentstukken voor RAG.

**DocumentSplitter** - Splitst documenten in stukken.

**EmbeddingModel** - Zet tekst om in numerieke vectoren.

**EmbeddingStore** - Slaat embeddings op en haalt ze op.

**MessageWindowChatMemory** - Onderhoudt een schuivend venster van recente berichten.

**PromptTemplate** - Maakt herbruikbare prompts met `{{variable}}`-plaatsaanduidingen.

**TextSegment** - Tekstfragment met metadata. Gebruikt in RAG.

**ToolExecutionRequest** - Vertegenwoordigt een verzoek tot uitvoering van een tool.

**UserMessage / AiMessage / SystemMessage** - Typen conversatieberichten.

## AI/ML Concepts

**Few-Shot Learning** - Voorzien van voorbeelden in prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI-modellen getraind op enorme hoeveelheden tekstdata.

**Reasoning Effort** - GPT-5-parameter die de diepgang van het denken regelt. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Regelt de willekeurigheid van de uitvoer. Laag=deterministisch, hoog=creatief.

**Vector Database** - Gespecialiseerde database voor embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Uitvoeren van taken zonder voorbeelden. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Stap-voor-stap redenering voor betere nauwkeurigheid.

**Constrained Output** - Het afdwingen van een specifiek formaat of structuur.

**High Eagerness** - GPT-5-patroon voor grondige redenering.

**Low Eagerness** - GPT-5-patroon voor snelle antwoorden.

**Multi-Turn Conversation** - Context behouden over meerdere uitwisselingen.

**Role-Based Prompting** - Het instellen van het persona van het model via system-berichten.

**Self-Reflection** - Het model evalueert en verbetert zijn uitvoer.

**Structured Analysis** - Vaste evaluatiekaders.

**Task Execution Pattern** - Plannen → Uitvoeren → Samenvatten.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Laden → chunken → embedden → opslaan.

**In-Memory Embedding Store** - Niet-persistente opslag voor testen.

**RAG** - Combineert ophalen met generatie om antwoorden te funderen.

**Similarity Score** - Maat (0-1) voor semantische gelijkenis.

**Source Reference** - Metadata over opgehaalde inhoud.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Markeert Java-methoden als door AI aanroepbare tools.

**ReAct Pattern** - Redeneer → Handel → Observeer → Herhaal.

**Session Management** - Gescheiden contexten voor verschillende gebruikers.

**Tool** - Functie die een AI-agent kan aanroepen.

**Tool Description** - Documentatie van het doel van een tool en de parameters.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - Standaard voor het verbinden van AI-apps met externe tools.

**MCP Client** - Applicatie die verbindt met MCP-servers.

**MCP Server** - Service die tools via MCP blootstelt.

**Stdio Transport** - Server als subprocess via stdin/stdout.

**Tool Discovery** - Client vraagt de server welke tools beschikbaar zijn.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloud-zoekdienst met vectormogelijkheden. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Deployt Azure-resources.

**Azure OpenAI** - Microsoft's enterprise AI-dienst.

**Bicep** - Azure infrastructure-as-code-taal. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Naam voor modeldeployment in Azure.

**GPT-5** - Nieuwste OpenAI-model met regeling voor redenering. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Gepersonaliseerde ontwikkelomgeving in een container. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Gratis AI-model speelomgeving. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testen met in-memory opslag.

**Integration Testing** - Testen met echte infrastructuur.

**Maven** - Java build-automatiseringstool.

**Mockito** - Java mocking-framework.

**Spring Boot** - Java applicatieframework. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Disclaimer:
Dit document is vertaald met behulp van de AI-vertalingsdienst [Co-op Translator](https://github.com/Azure/co-op-translator). Hoewel we streven naar nauwkeurigheid, kunnen geautomatiseerde vertalingen fouten of onnauwkeurigheden bevatten. Het oorspronkelijke document in de oorspronkelijke taal moet als de gezaghebbende bron worden beschouwd. Voor cruciale informatie wordt een professionele menselijke vertaling aanbevolen. Wij zijn niet aansprakelijk voor eventuele misverstanden of verkeerde interpretaties die voortvloeien uit het gebruik van deze vertaling.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->