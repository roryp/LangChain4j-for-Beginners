<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T07:52:24+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "te"
}
-->
# LangChain4j పదజాలం

## విషయ సూచిక

- [కోర్ కాన్సెప్ట్‌లు](../../../docs)
- [LangChain4j భాగాలు](../../../docs)
- [AI/ML కాన్సెప్ట్‌లు](../../../docs)
- [ప్రాంప్ట్ ఇంజినీరింగ్ - [మాడ్యూల్ 02](../02-prompt-engineering/README.md)](#prompt-engineering---module-02)
- [RAG (రిట్రీవల్-ఆగ్మెంటెడ్ జనరేషన్) - [మాడ్యూల్ 03](../03-rag/README.md)](#rag-retrieval-augmented-generation---module-03)
- [ఏజెంట్స్ మరియు టూల్స్ - [మాడ్యూల్ 04](../04-tools/README.md)](#agents-and-tools---module-04)
- [మోడల్ కాంటెక్స్ ప్రోటోకాల్ (MCP) - [మాడ్యూల్ 05](../05-mcp/README.md)](#model-context-protocol-mcp---module-05)
- [Azure సేవలు - [మాడ్యూల్ 01](../01-introduction/README.md)](#azure-services---module-01)
- [పరీక్షలు మరియు అభివృద్ధి - [పరీక్ష మార్గదర్శి](TESTING.md)](#testing-and-development---testing-guide)

కోর্স్లో ఉపయోగించే పదాలు మరియు కాన్సెప్ట్‌లకు త్వరిత సూచిక.

## Core Concepts

**AI Agent** - AIని ఉపయోగించి తార్కికంగా నిర్ణయాలు తీసుకొని స్వతంత్రంగా చర్యలు తీసుకునే సిస్టమ్. [మాడ్యూల్ 04](../04-tools/README.md)

**Chain** - అవుట్పుట్ తదుపరి దశకు ఫీడ్ అయ్యే ఆపరేషన్ల క్రమం.

**Chunking** - డాక్యుమెంట్లను చిన్న భాగాలుగా విభజించడం. సాధారణంగా: 300-500 టోకెన్లు ఓవర్క్లాప్‌తో. [మాడ్యూల్ 03](../03-rag/README.md)

**Context Window** - మోడల్ ప్రాసెస్ చేయగల గరిష్ట టోకెన్లు. GPT-5: 400K టోకెన్లు.

**Embeddings** - టెక్స్ట్ అర్థాన్ని సూచించే సంఖ్యాపరమైన వెక్టర్లు. [మాడ్యూల్ 03](../03-rag/README.md)

**Function Calling** - మోడల్ బాహ్య ఫంక్షన్లను పిలవడానికి ఒక నిర్మాణీకృత అభ్యర్థనలను సృష్టిస్తుంది. [మాడ్యూల్ 04](../04-tools/README.md)

**Hallucination** - మోడల్స్ తప్పు అయినప్పటికీ బహుముఖ్యంగా చూపే సమాచారం సృష్టించినప్పుడు.

**Prompt** - లాంగ్వేజ్ మోడల్‌కు ఇన్‌పుట్‌గా ఇచ్చే టెక్స్ట్. [మాడ్యూల్ 02](../02-prompt-engineering/README.md)

**Semantic Search** - కీవర్డ్స్ కాకుండా embeddings ద్వారా అర్థం ఆధారంగా శోధించడం. [మాడ్యూల్ 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: మెమరీ లేదు. Stateful: సంభాషణ చరిత్రని నిలుపుకొంటుంది. [మాడ్యూల్ 01](../01-introduction/README.md)

**Tokens** - మోడల్స్ ప్రాసెస్ చేసే ప్రాథమిక టెక్స్ట్ యూనిట్లు. ఖర్చులు మరియు పరిమితులపై ప్రభావం చూపుతాయి. [మాడ్యూల్ 01](../01-introduction/README.md)

**Tool Chaining** - ఒకటి తర్వాత ఒకటి టూల్స్ కార్యకలాపం, అక్కడ అవుట్పుట్ తదుపరి కాల్‌ను సమాచారమిస్తుంది. [మాడ్యూల్ 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - టైప్-సేఫ్ AI సేవా ఇంటర్ఫేసులను సృష్టిస్తుంది.

**OpenAiOfficialChatModel** - OpenAI మరియు Azure OpenAI మోడల్స్ కోసం ఐక్యక్లయింట్.

**OpenAiOfficialEmbeddingModel** - OpenAI Official క్లయింట్ ఉపయోగించి embeddings సృష్టిస్తుంది (OpenAI మరియు Azure OpenAI రెండింటినీ మద్దతుగా ఉంచుతుంది).

**ChatModel** - లాంగ్వేజ్ మోడల్స్‌కు ప్రధాన ఇంటర్ఫేస్.

**ChatMemory** - సంభాషణ చరిత్రని నిర్వహిస్తుంది.

**ContentRetriever** - RAG కోసం సంబంధిత డాక్యుమెంట్ చంక్‌లను కనుగొంటుంది.

**DocumentSplitter** - డాక్యుమెంట్లను చంక్‌లుగా విభజిస్తుంది.

**EmbeddingModel** - టెక్స్ట్‌ని సంఖ్యాపరమైన వెక్టర్లుగా మార్చుతుంది.

**EmbeddingStore** - embeddings ని నిలుపు చేసి తిరిగి తెస్తుంది.

**MessageWindowChatMemory** - తాజా సందేశాల స్లైడింగ్ విండోను నిర్వహిస్తుంది.

**PromptTemplate** - `{{variable}}` ప్లేస్‌హోల్డర్‌లతో పునర్వినియోగయోగ్య ప్రాంప్ట్‌లు సృష్టిస్తుంది.

**TextSegment** - మెటాడేటాతో కూడిన టెక్స్ట్ చంక్. RAGలో ఉపయోగించబడుతుంది.

**ToolExecutionRequest** - టూల్ అమలు అభ్యర్థనను సూచిస్తుంది.

**UserMessage / AiMessage / SystemMessage** - సంభాషణ సందేశ రకాలవి.

## AI/ML Concepts

**Few-Shot Learning** - ప్రాంప్ట్‌లలో ఉదాహరణలు ఇవ్వడం. [మాడ్యూల్ 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - విస్తృత టెక్స్ట్ డేటా పై ట్రైన్ చేయబడిన AI మోడల్స్.

**Reasoning Effort** - ఆలోచనా గమనాన్ని నియంత్రించే GPT-5 పరామెటర్. [మాడ్యూల్ 02](../02-prompt-engineering/README.md)

**Temperature** - అవుట్పుట్ రాండమ్నెస్‌ను నియంత్రిస్తుంది. తక్కువ = నిర్ణీతమైన, ఎక్కువ = సృజనాత్మక.

**Vector Database** - embeddings కోసం ప్రత్యేకీకృత డేటాబేస్. [మాడ్యూల్ 03](../03-rag/README.md)

**Zero-Shot Learning** - ఉదాహరణల లేకుండానే పనులు చేయడం. [మాడ్యూల్ 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [మాడ్యూల్ 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - మెరుగైన ఖచ్చితత్వానికి దశల వారీగా తర్కం.

**Constrained Output** - నిర్దిష్ట ఫార్మాట్ లేదా నిర్మాణాన్ని అమలు చేయించడం.

**High Eagerness** - గہرైన తర్కానికి GPT-5 నమూనా.

**Low Eagerness** - త్వరిత జవాబుల కోసం GPT-5 నమూనా.

**Multi-Turn Conversation** - మార్పుల ద్వారా_context ను నిర్వహించడం.

**Role-Based Prompting** - సిస్టం సందేశాల ద్వారా మోడల్ వ్యక్తిత్వాన్ని సెట్ చేయడం.

**Self-Reflection** - మోడల్ తన అవుట్పుట్‌ను మూల్యాంకనం చేసి మెరుగుపరుచుకుంటుంది.

**Structured Analysis** - స్థిరమైన మూల్యాంకన ఫ్రేమ్‌వర్క్.

**Task Execution Pattern** - ప్లాన్ → అమలు → సారాంశం.

## RAG (రిట్రీవల్-ఆగ్మెంటెడ్ జనరేషన్) - [మాడ్యూల్ 03](../03-rag/README.md)

**Document Processing Pipeline** - లోడ్ → చంక్ → ఎంబెడ్ → స్టోర్.

**In-Memory Embedding Store** - పరీక్షల కోసం غیر-స్థిరమైన నిల్వ.

**RAG** - రిట్రీవల్‌ను జనరేషన్‌తో కలిపి ప్రతిస్పందనలను గ్రౌండ్ చేయడం.

**Similarity Score** - సేమాంటిక్ సమానత్వం యొక్క కొలమానం (0-1).

**Source Reference** - రిట్రీవ్ చేయబడిన కంటెంట్ గురించి మెటాడేటా.

## Agents and Tools - [మాడ్యూల్ 04](../04-tools/README.md)

**@Tool Annotation** - Java మethodenను AI-కాల్ చేయదగిన టూల్స్‌గా సూచిస్తుంది.

**ReAct Pattern** - తర్కం → చర్య → పరిశీలన → పునరావృతం.

**Session Management** - వివిధ వినియోగదారుల కోసం వేర్వేరు కాన్టెక్స్ట్స్.

**Tool** - AI ఏజెంట్ పిలవగల ఫంక్షన్.

**Tool Description** - టూల్ యొక్క లక్ష్యం మరియు పరామితుల డాక్యుమెంటేషన్.

## Model Context Protocol (MCP) - [మాడ్యూల్ 05](../05-mcp/README.md)

**MCP** - AI యాప్స్‌ను బాహ్య టూల్స్‌కు కనెక్ట్ చేయడానికి స్టాండర్డ్.

**MCP Client** - MCP సర్వర్స్‌కు కనెక్ట్ అయ్యే అప్లికేషన్.

**MCP Server** - MCP ద్వారా టూల్స్‌ను ఎక్స్‌పోజ్ చేసే సర్వీస్.

**Stdio Transport** - stdin/stdout ద్వారా subprocessగా సర్వర్ పనిచేస్తుంది.

**Tool Discovery** - వినియోగదారు అందుబాటులో ఉన్న టూల్స్ కోసం సర్వర్‌ను క్వెరీ చేస్తుంది.

## Azure Services - [మాడ్యూల్ 01](../01-introduction/README.md)

**Azure AI Search** - వెక్టర్ సామర్థ్యాలున్న క్లౌడ్ శోధన. [మాడ్యూల్ 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure రిసోర్సులను మోపడానికి ఉపయోగించే టూల్.

**Azure OpenAI** - Microsoft's ఎంటర్ప్రైజ్ AI సేవ.

**Bicep** - Azure ఇన్‌ఫ్రాస్ట్రక్చర్-అజ్-కోడ్ భాష. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azureలో మోడల్ డిప్లాయ్‌మెంట్‌కు ఇచ్చే పేరు.

**GPT-5** - తర్క నియంత్రణతో ఉన్న తాజా OpenAI మోడల్. [మాడ్యూల్ 02](../02-prompt-engineering/README.md)

## Testing and Development - [పరీక్ష మార్గదర్శి](TESTING.md)

**Dev Container** - కంటైనరైజ్డ్ డెవలప్‌మెంట్ వాతావరణం. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - ఉచిత AI మోడల్ ప్లేగ్రౌండ్. [మాడ్యూల్ 00](../00-quick-start/README.md)

**In-Memory Testing** - ఇన్-మెమరీ నిల్వతో పరీక్షలు.

**Integration Testing** - నిజమైన ఇన్‌ఫ్రాస్ట్రక్చర్‌తో పరీక్షలు.

**Maven** - Java బిల్డ్ ఆటోమేషన్ టూల్.

**Mockito** - Java మాకింగ్ ఫ్రేమ్‌వర్క్.

**Spring Boot** - Java అప్లికేషన్ ఫ్రేమ్‌వర్క్. [మాడ్యూల్ 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
బాధ్యత మినహాయింపు:
ఈ పత్రాన్ని AI అనువాద సేవ [Co-op Translator](https://github.com/Azure/co-op-translator) ఉపయోగించి అనువదించబడింది. మేము ఖచ్చితత్వానికి శ్రద్ధ తీసుకున్నప్పటికీ, ఆటోమేటెడ్ అనువాదాల్లో పొరపాట్లు లేదా అస్పష్టతలు ఉండవచ్చు అని దయచేసి గమనించండి. మూల భాషలోని అసలు పత్రాన్ని అధికారిక మూలంగా పరిగణించాలి. కీలకమైన సమాచారానికి వృత్తిపరమైన మానవ అనువాదాన్ని సిఫార్సు చేస్తాం. ఈ అనువాదం ఉపయోగించడంవల్ల ఏర్పడిన ఏవైనా అవగాహనా లోపాలు లేదా తప్పుగా అర్థం చేసుకోవడాలకు మేము బాధ్యులు కాదు.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->