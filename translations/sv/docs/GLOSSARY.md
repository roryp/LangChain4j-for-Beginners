<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T01:20:45+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sv"
}
-->
# LangChain4j Ordlista

## Innehåll

- [Kärnkoncept](../../../docs)
- [LangChain4j-komponenter](../../../docs)
- [AI/ML-begrepp](../../../docs)
- [Promptteknik](../../../docs)
- [RAG (sökförstärkt generering)](../../../docs)
- [Agenter och verktyg](../../../docs)
- [Modellkontextprotokoll (MCP)](../../../docs)
- [Azure-tjänster](../../../docs)
- [Testning och utveckling](../../../docs)

Snabbreferens för termer och begrepp som används genom kursen.

## Kärnkoncept

**AI Agent** - System som använder AI för att resonera och agera autonomt. [Modul 04](../04-tools/README.md)

**Chain** - Sekvens av operationer där utdata matas in i nästa steg.

**Chunking** - Att dela dokument i mindre bitar. Typiskt: 300–500 tokens med överlappning. [Modul 03](../03-rag/README.md)

**Context Window** - Maximalt antal tokens en modell kan bearbeta. GPT-5: 400K tokens.

**Embeddings** - Numeriska vektorer som representerar textens betydelse. [Modul 03](../03-rag/README.md)

**Function Calling** - Modellen genererar strukturerade förfrågningar för att anropa externa funktioner. [Modul 04](../04-tools/README.md)

**Hallucination** - När modeller genererar felaktig men plausibel information.

**Prompt** - Textinmatning till en språkmodell. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Sökning baserat på betydelse med hjälp av embeddings, inte nyckelord. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ingen minne. Stateful: behåller konversationshistorik. [Modul 01](../01-introduction/README.md)

**Tokens** - Grundläggande textenheter som modeller bearbetar. Påverkar kostnader och begränsningar. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Sekventiell verktygsexekvering där utdata informerar nästa anrop. [Modul 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Skapar typ-säkra AI-tjänstegränssnitt.

**OpenAiOfficialChatModel** - Enhetlig klient för OpenAI och Azure OpenAI-modeller.

**OpenAiOfficialEmbeddingModel** - Skapar embeddings med OpenAI Official-klienten (stöder både OpenAI och Azure OpenAI).

**ChatModel** - Kärninterface för språkmodeller.

**ChatMemory** - Behåller konversationshistorik.

**ContentRetriever** - Hittar relevanta dokumentbitar för RAG.

**DocumentSplitter** - Delar dokument i chunkar.

**EmbeddingModel** - Konverterar text till numeriska vektorer.

**EmbeddingStore** - Lagrar och hämtar embeddings.

**MessageWindowChatMemory** - Behåller ett glidande fönster av senaste meddelanden.

**PromptTemplate** - Skapar återanvändbara prompts med `{{variable}}`-platshållare.

**TextSegment** - Textbit med metadata. Används i RAG.

**ToolExecutionRequest** - Representerar en begäran om verktygsexekvering.

**UserMessage / AiMessage / SystemMessage** - Olika typer av konversationsmeddelanden.

## AI/ML Concepts

**Few-Shot Learning** - Att ge exempel i prompts. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI-modeller tränade på stora textmängder.

**Reasoning Effort** - GPT-5-parameter som styr tänkandets djup. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Kontrollerar utdataens slumpmässighet. Låg=deterministisk, hög=kreativ.

**Vector Database** - Specialiserad databas för embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Utföra uppgifter utan exempel. [Modul 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Stegvis resonemang för bättre noggrannhet.

**Constrained Output** - Tvinga fram ett specifikt format eller struktur.

**High Eagerness** - GPT-5-mönster för grundligt resonemang.

**Low Eagerness** - GPT-5-mönster för snabba svar.

**Multi-Turn Conversation** - Bibehålla kontext över flera utbyten.

**Role-Based Prompting** - Sätta modellens persona via systemmeddelanden.

**Self-Reflection** - Modellen utvärderar och förbättrar sitt eget resultat.

**Structured Analysis** - Fast utvärderingsramverk.

**Task Execution Pattern** - Planera → Utföra → Sammanfatta.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Ladda → dela i chunkar → embedda → lagra.

**In-Memory Embedding Store** - Icke-persistent lagring för testning.

**RAG** - Kombinerar hämtning med generering för att förankra svar.

**Similarity Score** - Mått (0–1) på semantisk likhet.

**Source Reference** - Metadata om hämtat innehåll.

## Agents and Tools - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Markerar Java-metoder som AI-anropbara verktyg.

**ReAct Pattern** - Resonera → Agera → Observera → Upprepa.

**Session Management** - Separata kontexter för olika användare.

**Tool** - Funktion som en AI-agent kan anropa.

**Tool Description** - Dokumentation av verktygets syfte och parametrar.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**MCP** - Standard för att koppla AI-appar till externa verktyg.

**MCP Client** - Applikation som ansluter till MCP-servrar.

**MCP Server** - Tjänst som exponerar verktyg via MCP.

**Stdio Transport** - Server som subprocess via stdin/stdout.

**Tool Discovery** - Klienten frågar servern efter tillgängliga verktyg.

## Azure Services - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Molnsökning med vektorfunktioner. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Distribuerar Azure-resurser.

**Azure OpenAI** - Microsofts företags-AI-tjänst.

**Bicep** - Azure-infrastruktur-som-kod-språk. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Namn för modelldistribution i Azure.

**GPT-5** - Senaste OpenAI-modellen med styrning av resonemang. [Modul 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Containeriserad utvecklingsmiljö. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Gratis AI-modelllekplats. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testning med in-memory-lagring.

**Integration Testing** - Testning med verklig infrastruktur.

**Maven** - Java-byggautomationsverktyg.

**Mockito** - Java-ramverk för mocking.

**Spring Boot** - Java-applikationsramverk. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Ansvarsfriskrivning:
Detta dokument har översatts med hjälp av AI-översättningstjänsten Co-op Translator (https://github.com/Azure/co-op-translator). Även om vi strävar efter noggrannhet, var medveten om att automatiska översättningar kan innehålla fel eller brister. Det ursprungliga dokumentet på dess modersmål bör betraktas som den auktoritativa källan. För kritisk information rekommenderas professionell mänsklig översättning. Vi ansvarar inte för eventuella missförstånd eller feltolkningar som uppstår till följd av användningen av denna översättning.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->