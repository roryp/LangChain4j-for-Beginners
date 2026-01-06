<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:16:24+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "nl"
}
-->
# LangChain4j Glossarium

## Inhoudsopgave

- [Kernconcepten](../../../docs)
- [LangChain4j Componenten](../../../docs)
- [AI/ML Concepten](../../../docs)
- [Guardrails](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenten en Hulpmiddelen](../../../docs)
- [Agentic Module](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Services](../../../docs)
- [Testen en Ontwikkeling](../../../docs)

Snelle referentie voor termen en concepten die door de hele cursus worden gebruikt.

## Kernconcepten

**AI Agent** - Systeem dat AI gebruikt om autonoom te redeneren en handelen. [Module 04](../04-tools/README.md)

**Chain** - Reeks van bewerkingen waarbij output wordt doorgegeven aan de volgende stap.

**Chunking** - Documenten opsplitsen in kleinere delen. Typisch: 300-500 tokens met overlap. [Module 03](../03-rag/README.md)

**Context Window** - Maximale tokens die een model kan verwerken. GPT-5: 400K tokens.

**Embeddings** - Numerieke vectoren die de betekenis van tekst vertegenwoordigen. [Module 03](../03-rag/README.md)

**Function Calling** - Model genereert gestructureerde verzoeken om externe functies aan te roepen. [Module 04](../04-tools/README.md)

**Hallucination** - Wanneer modellen onjuiste maar plausibele informatie genereren.

**Prompt** - Tekstinvoer voor een taalmodel. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Zoeken op betekenis met behulp van embeddings, niet op sleutelwoorden. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: geen geheugen. Stateful: behoudt gesprekshistorie. [Module 01](../01-introduction/README.md)

**Tokens** - Basis tekstunits die modellen verwerken. Beïnvloedt kosten en limieten. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Opeenvolgende uitvoering van tools waarbij output de volgende aanroep informeert. [Module 04](../04-tools/README.md)

## LangChain4j Componenten

**AiServices** - Maakt type-veilige AI service interfaces.

**OpenAiOfficialChatModel** - Geünificeerde client voor OpenAI en Azure OpenAI modellen.

**OpenAiOfficialEmbeddingModel** - Maakt embeddings met OpenAI Official client (ondersteunt zowel OpenAI als Azure OpenAI).

**ChatModel** - Kerninterface voor taalmodellen.

**ChatMemory** - Houdt gespreksgeschiedenis bij.

**ContentRetriever** - Vindt relevante documentstukken voor RAG.

**DocumentSplitter** - Splitst documenten in stukken.

**EmbeddingModel** - Zet tekst om in numerieke vectoren.

**EmbeddingStore** - Slaat embeddings op en haalt ze op.

**MessageWindowChatMemory** - Houdt een sliding window van recente berichten bij.

**PromptTemplate** - Maakt herbruikbare prompts met `{{variable}}` placeholders.

**TextSegment** - Tekststuk met metadata. Gebruikt in RAG.

**ToolExecutionRequest** - Vertegenwoordigt een tooluitvoeringsverzoek.

**UserMessage / AiMessage / SystemMessage** - Gesprek berichttypes.

## AI/ML Concepten

**Few-Shot Learning** - Voorzien van voorbeelden in prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI modellen getraind op enorme tekstdata.

**Reasoning Effort** - GPT-5 parameter die de diepgang van denken aanstuurt. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Regelt de willekeurigheid van output. Laag=deterministisch, hoog=creatief.

**Vector Database** - Gespecialiseerde database voor embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Taken uitvoeren zonder voorbeelden. [Module 02](../02-prompt-engineering/README.md)

## Guardrails - [Module 00](../00-quick-start/README.md)

**Defense in Depth** - Meervoudige beveiligingslaag die guardrails op applicatieniveau combineert met veiligheidfilters van providers.

**Hard Block** - Provider geeft HTTP 400 fout voor ernstige content overtredingen.

**InputGuardrail** - LangChain4j interface om gebruikersinvoer te valideren voordat deze het LLM bereikt. Bespaart kosten en latency door schadelijke prompts vroeg te blokkeren.

**InputGuardrailResult** - Retourtype voor guardrail validatie: `success()` of `fatal("reden")`.

**OutputGuardrail** - Interface om AI reacties te valideren voordat ze aan gebruikers worden geretourneerd.

**Provider Safety Filters** - Ingebouwde contentfilters van AI providers (bijv. GitHub Models) die overtredingen op API-niveau detecteren.

**Soft Refusal** - Model weigert beleefd te antwoorden zonder een fout te geven.

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Stapsgewijze redenering voor betere nauwkeurigheid.

**Constrained Output** - Handhaven van specifieke format of structuur.

**High Eagerness** - GPT-5 patroon voor grondige redenering.

**Low Eagerness** - GPT-5 patroon voor snelle antwoorden.

**Multi-Turn Conversation** - Context bewaren over uitwisselingen heen.

**Role-Based Prompting** - Modelpersoon instellen via systeemberichten.

**Self-Reflection** - Model evalueert en verbetert zijn output.

**Structured Analysis** - Vaste evaluatiekader.

**Task Execution Pattern** - Plan → Uitvoeren → Samenvatten.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Laden → chunking → embedden → opslaan.

**In-Memory Embedding Store** - Niet-persistente opslag voor testen.

**RAG** - Combineert ophalen met generatie om antwoorden te funderen.

**Similarity Score** - Maat (0-1) van semantische gelijkenis.

**Source Reference** - Metadata over opgehaalde inhoud.

## Agenten en Hulpmiddelen - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Markeert Java-methoden als AI-oproepbare hulpmiddelen.

**ReAct Pattern** - Redeneren → Handelen → Observeren → Herhalen.

**Session Management** - Gescheiden contexten voor verschillende gebruikers.

**Tool** - Functie die een AI-agent kan aanroepen.

**Tool Description** - Documentatie van doel en parameters van hulpmiddel.

## Agentic Module - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - Markeert interfaces als AI-agenten met declaratieve gedragsdefinitie.

**Agent Listener** - Hook voor het monitoren van agentuitvoering via `beforeAgentInvocation()` en `afterAgentInvocation()`.

**Agentic Scope** - Gedeeld geheugen waarin agenten outputs opslaan met `outputKey` voor downstream agents.

**AgenticServices** - Factory voor het maken van agenten via `agentBuilder()` en `supervisorBuilder()`.

**Conditional Workflow** - Routeer op basis van condities naar verschillende specialistische agenten.

**Human-in-the-Loop** - Workflowpatroon met menselijke checkpoints voor goedkeuring of inhoudscontrole.

**langchain4j-agentic** - Maven dependency voor declaratief agentbouwen (experimenteel).

**Loop Workflow** - Itereer agentuitvoering totdat een voorwaarde is voldaan (bijv. kwaliteitscore ≥ 0.8).

**outputKey** - Agent annotatieparameter die specificeert waar resultaten worden opgeslagen in Agentic Scope.

**Parallel Workflow** - Meerdere agenten gelijktijdig laten draaien voor onafhankelijke taken.

**Response Strategy** - Hoe supervisor het definitieve antwoord formuleert: LAST, SUMMARY, of SCORED.

**Sequential Workflow** - Agenten achtereenvolgens uitvoeren waarbij output naar volgende stap stroomt.

**Supervisor Agent Pattern** - Geavanceerd agentpatroon waarbij een supervisor LLM dynamisch bepaalt welke sub-agenten worden aangeroepen.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - Maven dependency voor MCP integratie in LangChain4j.

**MCP** - Model Context Protocol: standaard voor het koppelen van AI-apps aan externe tools. Één keer bouwen, overal gebruiken.

**MCP Client** - Applicatie die verbinding maakt met MCP-servers om tools te ontdekken en te gebruiken.

**MCP Server** - Dienst die tools via MCP aanbiedt met duidelijke beschrijvingen en parameterschema's.

**McpToolProvider** - LangChain4j component dat MCP tools verpakt voor gebruik in AI services en agenten.

**McpTransport** - Interface voor MCP-communicatie. Implementaties zijn o.a. Stdio en HTTP.

**Stdio Transport** - Lokale proces transport via stdin/stdout. Handig voor toegang tot bestandssysteem of commandline tools.

**StdioMcpTransport** - LangChain4j implementatie die MCP server als subprocess opstart.

**Tool Discovery** - Client vraagt server naar beschikbare tools met beschrijvingen en schema's.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloudzoekdienst met vector functionaliteit. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Zet Azure resources uit.

**Azure OpenAI** - Enterprise AI-service van Microsoft.

**Bicep** - Azure infrastructuur-als-code taal. [Infrastructuurhandleiding](../01-introduction/infra/README.md)

**Deployment Name** - Naam voor modeldeployment in Azure.

**GPT-5** - Nieuwste OpenAI-model met redeneervariabele. [Module 02](../02-prompt-engineering/README.md)

## Testen en Ontwikkeling - [Testing Guide](TESTING.md)

**Dev Container** - Gecontaineriseerde ontwikkelomgeving. [Configuratie](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Gratis AI-model speelplatform. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testen met in-memory opslag.

**Integration Testing** - Testen met echte infrastructuur.

**Maven** - Java build automation tool.

**Mockito** - Java mocking framework.

**Spring Boot** - Java applicatie framework. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vrijwaring**:
Dit document is vertaald met behulp van de AI-vertalingsservice [Co-op Translator](https://github.com/Azure/co-op-translator). Hoewel we streven naar nauwkeurigheid, dient u er rekening mee te houden dat geautomatiseerde vertalingen fouten of onnauwkeurigheden kunnen bevatten. Het originele document in de oorspronkelijke taal moet als de gezaghebbende bron worden beschouwd. Voor cruciale informatie wordt professionele menselijke vertaling aanbevolen. Wij zijn niet aansprakelijk voor eventuele misverstanden of verkeerde interpretaties die voortvloeien uit het gebruik van deze vertaling.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->