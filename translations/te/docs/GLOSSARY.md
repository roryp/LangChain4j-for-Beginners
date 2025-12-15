<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:27:47+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "te"
}
-->
# LangChain4j గ్లోసరీ

## Table of Contents

- [Core Concepts](../../../docs)
- [LangChain4j Components](../../../docs)
- [AI/ML Concepts](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agents and Tools](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Services](../../../docs)
- [Testing and Development](../../../docs)

కోర్సు అంతటా ఉపయోగించే పదాలు మరియు భావనలకు త్వరిత సూచిక.

## Core Concepts

**AI Agent** - AIని ఉపయోగించి స్వతంత్రంగా తర్కం చేసి చర్యలు తీసుకునే వ్యవస్థ. [Module 04](../04-tools/README.md)

**Chain** - అవుట్పుట్ తదుపరి దశకు ఫీడ్ అయ్యే ఆపరేషన్ల శ్రేణి.

**Chunking** - డాక్యుమెంట్లను చిన్న భాగాలుగా విభజించడం. సాధారణంగా: 300-500 టోకెన్లు ఓవర్‌ల్యాప్‌తో. [Module 03](../03-rag/README.md)

**Context Window** - ఒక మోడల్ ప్రాసెస్ చేయగల గరిష్ట టోకెన్లు. GPT-5: 400K టోకెన్లు.

**Embeddings** - టెక్స్ట్ అర్థాన్ని సూచించే సంఖ్యాత్మక వెక్టర్లు. [Module 03](../03-rag/README.md)

**Function Calling** - మోడల్ బాహ్య ఫంక్షన్లను పిలవడానికి నిర్మిత అభ్యర్థనలు సృష్టిస్తుంది. [Module 04](../04-tools/README.md)

**Hallucination** - మోడల్స్ తప్పు అయినా సాద్యమైన సమాచారాన్ని ఉత్పత్తి చేయడం.

**Prompt** - భాషా మోడల్‌కు టెక్స్ట్ ఇన్‌పుట్. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - కీవర్డ్స్ కాకుండా అర్థం ఆధారంగా శోధన embeddings ఉపయోగించి. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: జ్ఞాపకం లేదు. Stateful: సంభాషణ చరిత్రను నిర్వహిస్తుంది. [Module 01](../01-introduction/README.md)

**Tokens** - మోడల్స్ ప్రాసెస్ చేసే ప్రాథమిక టెక్స్ట్ యూనిట్లు. ఖర్చులు మరియు పరిమితులపై ప్రభావం. [Module 01](../01-introduction/README.md)

**Tool Chaining** - అవుట్పుట్ తదుపరి పిలుపుకు సమాచారం అందించే వరుస టూల్ అమలు. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - టైప్-సేఫ్ AI సర్వీస్ ఇంటర్‌ఫేస్‌లను సృష్టిస్తుంది.

**OpenAiOfficialChatModel** - OpenAI మరియు Azure OpenAI మోడల్స్ కోసం ఏకీకృత క్లయింట్.

**OpenAiOfficialEmbeddingModel** - OpenAI అధికారిక క్లయింట్ ఉపయోగించి embeddings సృష్టిస్తుంది (OpenAI మరియు Azure OpenAI రెండింటినీ మద్దతు ఇస్తుంది).

**ChatModel** - భాషా మోడల్స్ కోసం కోర్ ఇంటర్‌ఫేస్.

**ChatMemory** - సంభాషణ చరిత్రను నిర్వహిస్తుంది.

**ContentRetriever** - RAG కోసం సంబంధిత డాక్యుమెంట్ చంక్‌లను కనుగొంటుంది.

**DocumentSplitter** - డాక్యుమెంట్లను చంక్‌లుగా విభజిస్తుంది.

**EmbeddingModel** - టెక్స్ట్‌ను సంఖ్యాత్మక వెక్టర్లుగా మార్చుతుంది.

**EmbeddingStore** - embeddings నిల్వ చేస్తుంది మరియు తిరిగి పొందుతుంది.

**MessageWindowChatMemory** - తాజా సందేశాల స్లైడింగ్ విండోను నిర్వహిస్తుంది.

**PromptTemplate** - `{{variable}}` ప్లేస్‌హోల్డర్లతో పునర్వినియోగపరచదగిన ప్రాంప్ట్‌లను సృష్టిస్తుంది.

**TextSegment** - మెటాడేటాతో కూడిన టెక్స్ట్ చంక్. RAGలో ఉపయోగిస్తారు.

**ToolExecutionRequest** - టూల్ అమలుకు అభ్యర్థనను సూచిస్తుంది.

**UserMessage / AiMessage / SystemMessage** - సంభాషణ సందేశ రకాలు.

## AI/ML Concepts

**Few-Shot Learning** - ప్రాంప్ట్‌లలో ఉదాహరణలు ఇవ్వడం. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - విస్తృత టెక్స్ట్ డేటాపై శిక్షణ పొందిన AI మోడల్స్.

**Reasoning Effort** - ఆలోచనా లోతును నియంత్రించే GPT-5 పారామీటర్. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - అవుట్పుట్ యాదృచ్ఛికతను నియంత్రిస్తుంది. తక్కువ=నిర్ణాయక, ఎక్కువ=సృజనాత్మక.

**Vector Database** - embeddings కోసం ప్రత్యేక డేటాబేస్. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - ఉదాహరణలు లేకుండా పనులు చేయడం. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - మెరుగైన ఖచ్చితత్వం కోసం దశల వారీ తర్కం.

**Constrained Output** - నిర్దిష్ట ఫార్మాట్ లేదా నిర్మాణాన్ని అమలు చేయడం.

**High Eagerness** - సమగ్ర తర్కం కోసం GPT-5 నమూనా.

**Low Eagerness** - త్వరిత సమాధానాల కోసం GPT-5 నమూనా.

**Multi-Turn Conversation** - మార్పిడులలో సందర్భాన్ని నిర్వహించడం.

**Role-Based Prompting** - సిస్టమ్ సందేశాల ద్వారా మోడల్ వ్యక్తిత్వాన్ని సెట్ చేయడం.

**Self-Reflection** - మోడల్ తన అవుట్పుట్‌ను మూల్యాంకనం చేసి మెరుగుపరుస్తుంది.

**Structured Analysis** - స్థిరమైన మూల్యాంకన ఫ్రేమ్‌వర్క్.

**Task Execution Pattern** - ప్రణాళిక → అమలు → సారాంశం.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - లోడ్ → చంక్ → ఎంబెడ్ → నిల్వ.

**In-Memory Embedding Store** - పరీక్షల కోసం అస్థిర నిల్వ.

**RAG** - ప్రతిస్పందనలను స్థిరపరచడానికి రిట్రీవల్ మరియు జనరేషన్ కలిపి.

**Similarity Score** - సారూప్యత కొలత (0-1).

**Source Reference** - రిట్రీవ్ చేసిన కంటెంట్ గురించి మెటాడేటా.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - జావా పద్ధతులను AI-కాల్ చేయదగిన టూల్స్‌గా గుర్తిస్తుంది.

**ReAct Pattern** - తర్కం → చర్య → పరిశీలన → పునరావృతం.

**Session Management** - వేర్వేరు వినియోగదారుల కోసం వేర్వేరు సందర్భాలు.

**Tool** - AI ఏజెంట్ పిలవగల ఫంక్షన్.

**Tool Description** - టూల్ ఉద్దేశ్యం మరియు పారామీటర్ల డాక్యుమెంటేషన్.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**Docker Transport** - Docker కంటైనర్‌లో MCP సర్వర్.

**MCP** - AI యాప్స్‌ను బాహ్య టూల్స్‌కు కనెక్ట్ చేయడానికి ప్రమాణం.

**MCP Client** - MCP సర్వర్లకు కనెక్ట్ అయ్యే అప్లికేషన్.

**MCP Server** - MCP ద్వారా టూల్స్‌ను అందించే సర్వీస్.

**Server-Sent Events (SSE)** - HTTP ద్వారా సర్వర్-టు-క్లయింట్ స్ట్రీమింగ్.

**Stdio Transport** - stdin/stdout ద్వారా సబ్‌ప్రాసెస్‌గా సర్వర్.

**Streamable HTTP Transport** - రియల్-టైమ్ కమ్యూనికేషన్ కోసం SSEతో HTTP.

**Tool Discovery** - క్లయింట్ అందుబాటులో ఉన్న టూల్స్ కోసం సర్వర్‌ను ప్రశ్నిస్తుంది.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - వెక్టర్ సామర్థ్యాలతో క్లౌడ్ శోధన. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure వనరులను డిప్లాయ్ చేస్తుంది.

**Azure OpenAI** - మైక్రోసాఫ్ట్ యొక్క ఎంటర్ప్రైజ్ AI సర్వీస్.

**Bicep** - Azure ఇన్‌ఫ్రాస్ట్రక్చర్-అస్-కోడ్ భాష. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azureలో మోడల్ డిప్లాయ్‌మెంట్‌కు పేరు.

**GPT-5** - reasoning నియంత్రణతో తాజా OpenAI మోడల్. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - కంటైనర్ చేయబడిన అభివృద్ధి వాతావరణం. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - ఉచిత AI మోడల్ ప్లేగ్రౌండ్. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - ఇన్-మెమరీ నిల్వతో పరీక్ష.

**Integration Testing** - వాస్తవ ఇన్‌ఫ్రాస్ట్రక్చర్‌తో పరీక్ష.

**Maven** - జావా బిల్డ్ ఆటోమేషన్ టూల్.

**Mockito** - జావా మాకింగ్ ఫ్రేమ్‌వర్క్.

**Spring Boot** - జావా అప్లికేషన్ ఫ్రేమ్‌వర్క్. [Module 01](../01-introduction/README.md)

**Testcontainers** - పరీక్షలలో Docker కంటైనర్లు.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**అస్పష్టత**:  
ఈ పత్రాన్ని AI అనువాద సేవ [Co-op Translator](https://github.com/Azure/co-op-translator) ఉపయోగించి అనువదించబడింది. మేము ఖచ్చితత్వానికి ప్రయత్నించినప్పటికీ, ఆటోమేటెడ్ అనువాదాల్లో పొరపాట్లు లేదా తప్పిదాలు ఉండవచ్చు. అసలు పత్రం దాని స్వదేశీ భాషలో ఉన్నది అధికారిక మూలంగా పరిగణించాలి. ముఖ్యమైన సమాచారానికి, ప్రొఫెషనల్ మానవ అనువాదం సిఫార్సు చేయబడుతుంది. ఈ అనువాదం వాడకంలో ఏర్పడిన ఏవైనా అపార్థాలు లేదా తప్పుదారుల కోసం మేము బాధ్యత వహించము.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->