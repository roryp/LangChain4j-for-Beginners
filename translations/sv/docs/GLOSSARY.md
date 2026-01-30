# LangChain4j Ordlista

## Innehållsförteckning

- [Kärnbegrepp](../../../docs)
- [LangChain4j-komponenter](../../../docs)
- [AI/ML Begrepp](../../../docs)
- [Guardrails](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenter och Verktyg](../../../docs)
- [Agentic-modul](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure-tjänster](../../../docs)
- [Testning och Utveckling](../../../docs)

Snabb referens för termer och begrepp som används genom hela kursen.

## Kärnbegrepp

**AI Agent** - System som använder AI för att resonera och agera autonomt. [Modul 04](../04-tools/README.md)

**Chain** - Sekvens av operationer där utdata matas in i nästa steg.

**Chunking** - Att dela upp dokument i mindre delar. Typiskt: 300-500 tokens med överlappning. [Modul 03](../03-rag/README.md)

**Context Window** - Maximalt antal tokens en modell kan bearbeta. GPT-5: 400K tokens.

**Embeddings** - Numeriska vektorer som representerar textens mening. [Modul 03](../03-rag/README.md)

**Function Calling** - Modell genererar strukturerade anrop för att kalla externa funktioner. [Modul 04](../04-tools/README.md)

**Hallucination** - När modeller genererar felaktig men trovärdig information.

**Prompt** - Textinmatning till en språkmodell. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Sökning efter mening med hjälp av embeddings, inte nyckelord. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: inget minne. Stateful: upprätthåller konversationshistorik. [Modul 01](../01-introduction/README.md)

**Tokens** - Basenheter av text som modeller bearbetar. Påverkar kostnader och begränsningar. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Sekventiell verktygskörning där resultat från ett steg styr nästa anrop. [Modul 04](../04-tools/README.md)

## LangChain4j-komponenter

**AiServices** - Skapar typ-säkra AI-tjänstegränssnitt.

**OpenAiOfficialChatModel** - Enhetlig klient för OpenAI och Azure OpenAI-modeller.

**OpenAiOfficialEmbeddingModel** - Skapar embeddings med OpenAI Official-klient (stöder både OpenAI och Azure OpenAI).

**ChatModel** - Kärngränssnitt för språkmodeller.

**ChatMemory** - Underhåller konversationshistorik.

**ContentRetriever** - Hittar relevanta dokumentdelar för RAG.

**DocumentSplitter** - Delar upp dokument i chunkar.

**EmbeddingModel** - Omvandlar text till numeriska vektorer.

**EmbeddingStore** - Lagrar och hämtar embeddings.

**MessageWindowChatMemory** - Underhåller ett glidande fönster med senaste meddelanden.

**PromptTemplate** - Skapar återanvändbara prompts med `{{variable}}`-platshållare.

**TextSegment** - Textbit med metadata. Används i RAG.

**ToolExecutionRequest** - Representerar en begäran om verktygskörning.

**UserMessage / AiMessage / SystemMessage** - Konversationens meddelandetyper.

## AI/ML Begrepp

**Few-Shot Learning** - Ger exempel i prompts. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI-modeller tränade på enorma textmängder.

**Reasoning Effort** - GPT-5-parameter som styr resonemangets djup. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Styr output-variation. Låg=deterministisk, hög=kreativ.

**Vector Database** - Specialiserad databas för embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Utföra uppgifter utan exempel. [Modul 02](../02-prompt-engineering/README.md)

## Guardrails - [Modul 00](../00-quick-start/README.md)

**Defense in Depth** - Flerlagers säkerhetsstrategi som kombinerar applikationsnivå-guardrails med leverantörers säkerhetsfilter.

**Hard Block** - Leverantör ger HTTP 400-fel vid allvarliga innehållsöverträdelser.

**InputGuardrail** - LangChain4j-gränssnitt för validering av användarinmatning innan den når LLM. Sparar kostnad och latens genom att blockera skadliga prompts tidigt.

**InputGuardrailResult** - Returtyp för guardrail-validering: `success()` eller `fatal("anledning")`.

**OutputGuardrail** - Gränssnitt för att validera AI-svar innan de returneras till användare.

**Provider Safety Filters** - Inbyggda innehållsfilter från AI-leverantörer (t.ex. GitHub Models) som fångar överträdelser på API-nivå.

**Soft Refusal** - Modell vägrar artigt att svara utan att kasta fel.

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Stegvis resonemang för bättre noggrannhet.

**Constrained Output** - Tvinga specifikt format eller struktur.

**High Eagerness** - GPT-5-mönster för grundligt resonemang.

**Low Eagerness** - GPT-5-mönster för snabba svar.

**Multi-Turn Conversation** - Behålla kontext över flera utbyten.

**Role-Based Prompting** - Sätta modellpersona via systemmeddelanden.

**Self-Reflection** - Modell utvärderar och förbättrar sin output.

**Structured Analysis** - Fast utvärderingsramverk.

**Task Execution Pattern** - Planera → Utföra → Sammanfatta.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Ladda → dela → embedda → lagra.

**In-Memory Embedding Store** - Icke-persisten lagring för testning.

**RAG** - Kombinerar hämtning med generering för att grunda svar.

**Similarity Score** - Mått (0-1) på semantisk likhet.

**Source Reference** - Metadata om hämtat innehåll.

## Agenter och Verktyg - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Markerar Java-metoder som AI-anropbara verktyg.

**ReAct Pattern** - Resonera → Agera → Observera → Upprepa.

**Session Management** - Separata kontexter för olika användare.

**Tool** - Funktion som en AI-agent kan anropa.

**Tool Description** - Dokumentation om verktygets syfte och parametrar.

## Agentic-modul - [Modul 05](../05-mcp/README.md)

**@Agent Annotation** - Markerar gränssnitt som AI-agenter med deklarativ beteendedefinition.

**Agent Listener** - Krokar för att övervaka agentkörning via `beforeAgentInvocation()` och `afterAgentInvocation()`.

**Agentic Scope** - Delat minne där agenter lagrar resultat med `outputKey` för att downstream-agenter skall kunna använda.

**AgenticServices** - Fabrik för att skapa agenter med `agentBuilder()` och `supervisorBuilder()`.

**Conditional Workflow** - Rutter baserat på villkor till olika specialistagenter.

**Human-in-the-Loop** - Arbetsflödesmönster som lägger till mänskliga kontrollpunkter för godkännande eller innehållsgranskning.

**langchain4j-agentic** - Mavenberoende för deklarativ agentbyggnad (experimentell).

**Loop Workflow** - Upprepa agentkörning tills ett villkor uppfylls (t.ex. kvalitetspoäng ≥ 0.8).

**outputKey** - Agent-annoteringsparameter som specificerar var resultat lagras i Agentic Scope.

**Parallel Workflow** - Kör flera agenter samtidigt för oberoende uppgifter.

**Response Strategy** - Hur handledaren formulerar slutligt svar: LAST, SUMMARY eller SCORED.

**Sequential Workflow** - Kör agenter i ordning där utdata flyter till nästa steg.

**Supervisor Agent Pattern** - Avancerat agentiskt mönster där en handledande LLM dynamiskt avgör vilka subagenter som ska anropas.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** - Mavenberoende för MCP-integration i LangChain4j.

**MCP** - Model Context Protocol: standard för att koppla AI-appar till externa verktyg. Bygg en gång, använd överallt.

**MCP Client** - Applikation som ansluter till MCP-servrar för att upptäcka och använda verktyg.

**MCP Server** - Tjänst som exponerar verktyg via MCP med tydliga beskrivningar och parameterscheman.

**McpToolProvider** - LangChain4j-komponent som omsluter MCP-verktyg för användning i AI-tjänster och agenter.

**McpTransport** - Gränssnitt för MCP-kommunikation. Implementationer inkluderar Stdio och HTTP.

**Stdio Transport** - Lokal processöverföring via stdin/stdout. Användbart för filsystemåtkomst eller kommandoradsverktyg.

**StdioMcpTransport** - LangChain4j-implementation som startar MCP-server som underprocess.

**Tool Discovery** - Klient frågar server om tillgängliga verktyg med beskrivningar och scheman.

## Azure-tjänster - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Molnsökning med vektorstöd. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Distribuerar Azure-resurser.

**Azure OpenAI** - Microsofts företags-AI-tjänst.

**Bicep** - Azure infrastruktur-som-kod språk. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Namn för modellutplacering i Azure.

**GPT-5** - Senaste OpenAI-modellen med resonemangskontroll. [Modul 02](../02-prompt-engineering/README.md)

## Testning och Utveckling - [Testningsguide](TESTING.md)

**Dev Container** - Containeriserad utvecklingsmiljö. [Konfiguration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Gratis AI-modell lekplats. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testning med in-memory-lagring.

**Integration Testing** - Testning med verklig infrastruktur.

**Maven** - Java byggenhet.

**Mockito** - Java mock-ramverk.

**Spring Boot** - Java applikationsramverk. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfriskrivning**:
Detta dokument har översatts med hjälp av AI-översättningstjänsten [Co-op Translator](https://github.com/Azure/co-op-translator). Även om vi strävar efter noggrannhet, var vänlig observera att automatiska översättningar kan innehålla fel eller felaktigheter. Det ursprungliga dokumentet på dess modersmål ska betraktas som den auktoritativa källan. För kritisk information rekommenderas professionell mänsklig översättning. Vi ansvarar inte för några missförstånd eller feltolkningar som uppstår till följd av användningen av denna översättning.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->