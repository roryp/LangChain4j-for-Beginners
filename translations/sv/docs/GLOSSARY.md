<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:08:45+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sv"
}
-->
# LangChain4j Ordlista

## Innehållsförteckning

- [Kärnkoncept](../../../docs)
- [LangChain4j-komponenter](../../../docs)
- [AI/ML-koncept](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenter och Verktyg](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure-tjänster](../../../docs)
- [Testning och Utveckling](../../../docs)

Snabb referens för termer och koncept som används genom hela kursen.

## Kärnkoncept

**AI Agent** - System som använder AI för att resonera och agera autonomt. [Modul 04](../04-tools/README.md)

**Chain** - Sekvens av operationer där utdata matas in i nästa steg.

**Chunking** - Delar upp dokument i mindre bitar. Typiskt: 300-500 tokens med överlappning. [Modul 03](../03-rag/README.md)

**Context Window** - Maximalt antal tokens en modell kan bearbeta. GPT-5: 400K tokens.

**Embeddings** - Numeriska vektorer som representerar textens betydelse. [Modul 03](../03-rag/README.md)

**Function Calling** - Modell genererar strukturerade förfrågningar för att anropa externa funktioner. [Modul 04](../04-tools/README.md)

**Hallucination** - När modeller genererar felaktig men trovärdig information.

**Prompt** - Textinmatning till en språkmodell. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Sökning baserad på betydelse med embeddings, inte nyckelord. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: inget minne. Stateful: behåller konversationshistorik. [Modul 01](../01-introduction/README.md)

**Tokens** - Grundläggande textenheter som modeller bearbetar. Påverkar kostnader och begränsningar. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Sekventiell verktygsexekvering där utdata informerar nästa anrop. [Modul 04](../04-tools/README.md)

## LangChain4j-komponenter

**AiServices** - Skapar typ-säkra AI-tjänstegränssnitt.

**OpenAiOfficialChatModel** - Enhetlig klient för OpenAI och Azure OpenAI-modeller.

**OpenAiOfficialEmbeddingModel** - Skapar embeddings med OpenAI Official-klienten (stöder både OpenAI och Azure OpenAI).

**ChatModel** - Kärngränssnitt för språkmodeller.

**ChatMemory** - Behåller konversationshistorik.

**ContentRetriever** - Hittar relevanta dokumentbitar för RAG.

**DocumentSplitter** - Delar upp dokument i bitar.

**EmbeddingModel** - Omvandlar text till numeriska vektorer.

**EmbeddingStore** - Lagrar och hämtar embeddings.

**MessageWindowChatMemory** - Behåller ett glidande fönster av senaste meddelanden.

**PromptTemplate** - Skapar återanvändbara prompts med `{{variable}}`-platshållare.

**TextSegment** - Textbit med metadata. Används i RAG.

**ToolExecutionRequest** - Representerar verktygsexekveringsförfrågan.

**UserMessage / AiMessage / SystemMessage** - Konversationsmeddelandetyper.

## AI/ML-koncept

**Few-Shot Learning** - Tillhandahåller exempel i prompts. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI-modeller tränade på enorma textmängder.

**Reasoning Effort** - GPT-5-parameter som styr tänkandets djup. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Styr utdata slumpmässighet. Låg=deterministisk, hög=kreativ.

**Vector Database** - Specialiserad databas för embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Utför uppgifter utan exempel. [Modul 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Steg-för-steg-resonemang för bättre noggrannhet.

**Constrained Output** - Tvingar specifikt format eller struktur.

**High Eagerness** - GPT-5-mönster för grundligt resonemang.

**Low Eagerness** - GPT-5-mönster för snabba svar.

**Multi-Turn Conversation** - Behåller kontext över utbyten.

**Role-Based Prompting** - Sätter modellpersona via systemmeddelanden.

**Self-Reflection** - Modell utvärderar och förbättrar sin utdata.

**Structured Analysis** - Fast utvärderingsramverk.

**Task Execution Pattern** - Planera → Utför → Sammanfatta.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Ladda → dela → embedda → lagra.

**In-Memory Embedding Store** - Icke-persistent lagring för testning.

**RAG** - Kombinerar hämtning med generering för att förankra svar.

**Similarity Score** - Mått (0-1) på semantisk likhet.

**Source Reference** - Metadata om hämtat innehåll.

## Agenter och Verktyg - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Markerar Java-metoder som AI-anropbara verktyg.

**ReAct Pattern** - Resonera → Agera → Observera → Upprepa.

**Session Management** - Separata kontexter för olika användare.

**Tool** - Funktion som en AI-agent kan anropa.

**Tool Description** - Dokumentation av verktygets syfte och parametrar.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**Docker Transport** - MCP-server i Docker-container.

**MCP** - Standard för att koppla AI-appar till externa verktyg.

**MCP Client** - Applikation som ansluter till MCP-servrar.

**MCP Server** - Tjänst som exponerar verktyg via MCP.

**Server-Sent Events (SSE)** - Server-till-klient streaming över HTTP.

**Stdio Transport** - Server som subprocess via stdin/stdout.

**Streamable HTTP Transport** - HTTP med SSE för realtidskommunikation.

**Tool Discovery** - Klient frågar server om tillgängliga verktyg.

## Azure-tjänster - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Molnsökning med vektor-funktioner. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Distribuerar Azure-resurser.

**Azure OpenAI** - Microsofts företags-AI-tjänst.

**Bicep** - Azure infrastruktur-som-kod språk. [Infrastrukturguide](../01-introduction/infra/README.md)

**Deployment Name** - Namn för modellutplacering i Azure.

**GPT-5** - Senaste OpenAI-modellen med styrning av resonemang. [Modul 02](../02-prompt-engineering/README.md)

## Testning och Utveckling - [Testningsguide](TESTING.md)

**Dev Container** - Containeriserad utvecklingsmiljö. [Konfiguration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Gratis AI-modell-lekplats. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testning med minneslagring.

**Integration Testing** - Testning med riktig infrastruktur.

**Maven** - Java-byggautomatiseringsverktyg.

**Mockito** - Java-ramverk för mockning.

**Spring Boot** - Java-applikationsramverk. [Modul 01](../01-introduction/README.md)

**Testcontainers** - Docker-containrar i tester.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfriskrivning**:
Detta dokument har översatts med hjälp av AI-översättningstjänsten [Co-op Translator](https://github.com/Azure/co-op-translator). Även om vi strävar efter noggrannhet, vänligen var medveten om att automatiska översättningar kan innehålla fel eller brister. Det ursprungliga dokumentet på dess modersmål bör betraktas som den auktoritativa källan. För kritisk information rekommenderas professionell mänsklig översättning. Vi ansvarar inte för några missförstånd eller feltolkningar som uppstår till följd av användningen av denna översättning.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->