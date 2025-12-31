<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T07:00:49+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ta"
}
-->
# LangChain4j அகராதி

## Table of Contents

- [மூலக் கருத்துகள்](../../../docs)
- [LangChain4j கூறுகள்](../../../docs)
- [AI/ML கருத்துகள்](../../../docs)
- [ப்ராம்ட் பொறியியல்](../../../docs)
- [RAG (தகவல்-உதவியுடன் உருவாக்குதல்)](../../../docs)
- [ஏஜெண்டுகள் மற்றும் கருவிகள்](../../../docs)
- [மாடல் சூழ்நிலை நெறிமுறை (MCP)](../../../docs)
- [Azure சேவைகள்](../../../docs)
- [சோதனை மற்றும் மேம்பாடு](../../../docs)

Quick reference for terms and concepts used throughout the course.

## மூலக் கருத்துகள்

**AI Agent** - AI-யைப் பயன்படுத்தி சிந்தித்து சுயமாக செயல்படும் முறைமை. [Module 04](../04-tools/README.md)

**Chain** - ஒரு படிகளில் நடைபெறும் செயல்முறை, இதில் ஒரு படையின் வெளியீடு அடுத்த படைக்கு உள்ளீடாக பயன்படுத்தப்படுகிறது.

**Chunking** - ஆவணங்களை சிறிய துண்டுகளாகப் பிரிப்பது. வழக்கமாக: 300-500 tokens ஓவர்லேப்புடன். [Module 03](../03-rag/README.md)

**Context Window** - மாடல் ஒரே நேரத்தில் செயலாக்கக்கூடிய அதிகபட்ச token-கள். GPT-5: 400K tokens.

**Embeddings** - உரையின் பொருளை பிரதிநிதித்துவப்படுத்தும் எண்சொற்ப் வெக்டர்கள். [Module 03](../03-rag/README.md)

**Function Calling** - வெளிப்புற செயல்பாடுகளை அழைக்க மாடல் கட்டமைக்கப்பட்ட கோரிக்கைகளை உருவாக்குகிறது. [Module 04](../04-tools/README.md)

**Hallucination** - மாடல்கள் தவறான ஆனால் நம்பத்தக்கமான தகவல்களை உருவாக்கும் நிலை.

**Prompt** - மொழி மாடலுக்கு கொடுத்த உரை உள்ளீடு. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - எம்பெடிங்களை பயன்படுத்தி பொருள் அடிப்படையிலான தேடல், கீல்வார்த்தைகளைக் காணாமல். [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: நினைவிடம் இல்லை. Stateful: உரையாடல் வரலாற்றை பராமரிக்கிறது. [Module 01](../01-introduction/README.md)

**Tokens** - மாடல்கள் செயலாக்கும் அடிப்படை உரை அலகுகள். செலவுகள் மற்றும் எல்லைகளுக்கு பாதிப்பு. [Module 01](../01-introduction/README.md)

**Tool Chaining** - ஒரு கருவியின் வெளியீடு அடுத்தக் கொள்கைக்கு தகவலளிக்கும் முறையில் தொடர்ச்சியாக கருவிகளை செயல்படுத்துதல். [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - வகை-பாதுகாப்பான AI சேவை இடைமுகங்களை உருவாக்குகிறது.

**OpenAiOfficialChatModel** - OpenAI மற்றும் Azure OpenAI மாடல்களுக்கான ஒருங்கிணைந்த கிளையண்ட்.

**OpenAiOfficialEmbeddingModel** - OpenAI Official கிளையண்டைப் பயன்படுத்தி எம்பெடிங்களை உருவாக்குகிறது (OpenAI மற்றும் Azure OpenAI இரண்டையும் ஆதரிக்கிறது).

**ChatModel** - மொழி மாடல்களுக்கான மைய இடைமுகம்.

**ChatMemory** - உரையாடல் வரலாற்றை பராமரிக்கிறது.

**ContentRetriever** - RAG-க்காக தொடர்புடைய ஆவண துண்டுகளை கண்டுபிடிக்கிறது.

**DocumentSplitter** - ஆவணங்களை துண்டுகளாகப் பிரிக்கிறது.

**EmbeddingModel** - உரையை எண்சொற்ப் வெக்டர்களாக மாற்றுகிறது.

**EmbeddingStore** - எம்பெடிங்களை சேமித்து மீட்டெடுக்கும் இடம்.

**MessageWindowChatMemory** - சமீபத்திய செய்திகளின் ஸ்லைடிங் விண்டோவை பராமரிக்கிறது.

**PromptTemplate** - `{{variable}}` வகையான இடம்தவறுகள் உள்ள மீண்டும் பயன்படுத்தக்கூடிய ப்ராம்ட்களை உருவாக்குகிறது.

**TextSegment** - மெட்டாடேட்டாவுடன் கூடிய உரை துண்டு. RAG-இல் பயன்படுத்தப்படுகிறது.

**ToolExecutionRequest** - கருவி இயக்க கோரிக்கையை பிரதிநிதித்துவப்படுத்துகிறது.

**UserMessage / AiMessage / SystemMessage** - உரையாடல் செய்தி வகைகள்.

## AI/ML Concepts

**Few-Shot Learning** - ப்ராம்ட்களில் எடுத்துக்காட்டு கொடுத்து கற்றல். [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - பெரும் உரை தரவில் பயிற்சி பெற்ற AI மாடல்கள்.

**Reasoning Effort** - சிந்தனை ஆழத்தை கட்டுப்படுத்தும் GPT-5 அளவுரு. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - வெளியீட்டின் சீரற்ற தன்மையை கட்டுப்படுத்துகிறது. குறைவு = தீர்மானமானது, அதிகம் = படைக்குழுவானது.

**Vector Database** - எம்பெடிங்களுக்கு சிறப்பு செய்யப்பட்ட தரவுத்தளம். [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - எடுத்துக்காட்டுகள் இல்லாமல் பணிகளை செய்வது. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - சிறந்த துல்லியத்திற்காக படி படியாகச் சிந்தனை.

**Constrained Output** - குறிப்பிட்ட வடிவம் அல்லது அமைப்பை கடைப்பிடிக்கலை கட்டாயப்படுத்துதல்.

**High Eagerness** - விரிவான சிந்தனைக்கான GPT-5 முறை.

**Low Eagerness** - விரைவு பதில்களுக்கான GPT-5 முறை.

**Multi-Turn Conversation** - பரிமாற்றங்கள் வழியாக சூழ்நிலையை பராமரித்தல்.

**Role-Based Prompting** - சிஸ்டம் செய்திகளின் மூலம் மாடலின் პერსோனா (persona) அமைத்தல்.

**Self-Reflection** - மாடல் தனது வெளியீட்டை மதிப்பாய்வு செய்து மேம்படுத்துகிறது.

**Structured Analysis** - நிலையான மதிப்பீடு கட்டமைப்பு.

**Task Execution Pattern** - திட்டம் → செயல்படுத்து → சுருக்கம்.

## RAG (தகவல்-உதவியுடன் உருவாக்குதல்) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - ஏற்றுதல் → துண்டாக்கல் → எம்பெடிங் → சேமிப்பு.

**In-Memory Embedding Store** - சோதனைக்காக நிலையானது அல்லாத (non-persistent) சேமிப்பு.

**RAG** - பதில்களை நிலைநிறுத்த வரலாற்றில் மீட்பு மற்றும் உருவாக்கத்தை இணைக்கிறது.

**Similarity Score** - பொருள் சார்ந்த ஒத்திசைவை அளக்கும் அளவுகோல் (0-1).

**Source Reference** - மீட்கப்பட்ட உள்ளடக்கத்தைப் பற்றிய மெட்டாடேட்டா.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Java முறைமைகளை AI மூலம் அழைக்கக்கூடிய கருவிகளாக குறிக்கிறது.

**ReAct Pattern** - Reason → Act → Observe → Repeat.

**Session Management** - வெவ்வேறு பயனர்களுக்கான தனித்த சூழ்நிலைகளை பராமரித்தல்.

**Tool** - AI ஏஜெண்ட் அழைக்கும் செயல்பாடு.

**Tool Description** - கருவியின் நோக்கம் மற்றும் அளவுருக்களின் ஆவணம்.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - AI செயலிகள் மற்றும் வெளிப்புற கருவிகளை இணைக்குவதற்கான தரநிலை.

**MCP Client** - MCP சர்வர்களுடன் இணையும் செயலி.

**MCP Server** - MCP வழியாக கருவிகளை வெளிப்படுத்தும் சேவை.

**Stdio Transport** - stdin/stdout வழியாக துணை செயலியாக (subprocess) செயல்படும் சர்வர்.

**Tool Discovery** - கிளையன்ட் சர்வரைச் கேட்டு கிடைக்கும் கருவிகளை கண்டறிதல்.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - வெக்டர் திறன்களுடன் கூடிய மேகத்தில் நடைபெறும் தேடல். [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure வளங்களை நிலைநிறுத்த உதவும் கருவி.

**Azure OpenAI** - மைக்ரோசாப்டின் நிறுவனதர AI சேவை.

**Bicep** - Azure உட்கட்டமைப்பை குறியீடாக வரையிடும் மொழி. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azure-இல் மாடல் டெபிளாய்மென்டிற்கு பயன்படுத்தப்படும் பெயர்.

**GPT-5** - சிந்தனை கட்டுப்பாட்டுடன் கூடிய புதிய OpenAI மாடல். [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [சோதனை வழிகாட்டி](TESTING.md)

**Dev Container** - கொண்டெய்னரைப் பயன்படுத்திய மேம்பாட்டுத் சூழல். [கட்டமைப்பு](../../../.devcontainer/devcontainer.json)

**GitHub Models** - இலவச AI மாடல் விளையாட்டு மையம். [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - நினைவக சேமிப்புடன் சோதனைச் செய்முறை.

**Integration Testing** - உண்மையான அமைப்புகளுடன் ஒருங்கிணைந்து சோதனை செய்தல்.

**Maven** - Java கட்டுமான தானியக்க கருவி.

**Mockito** - Java-க்கு арналған நகல் உருவாக்கும் கட்டமைப்பு.

**Spring Boot** - Java செயலி கட்டமைப்பு. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
மறுப்பு:
இந்த ஆவணம் AI மொழிபெயர்ப்பு சேவை [Co-op Translator](https://github.com/Azure/co-op-translator) மூலம் மொழிபெயர்க்கப்பட்டுள்ளது. நாங்கள் துல்லியத்திற்கு முயற்சித்தாலும், தானாகச் செய்யப்பட்ட மொழிபெயர்ப்புகளில் பிழைகள் அல்லது துல்லியமின்மைகள் இருக்கலாம் என்பதை தயவுசெய்து கவனித்துக் கொள்ளவும். அசல் ஆவணம் அதன் சொந்த மொழியிலே அதிகாரபூர்வ மூலமாக கருதப்பட வேண்டும். முக்கியமான தகவல்களுக்கு, அனுபவமிக்க மனித மொழிபெயர்ப்பாளரை பயன்படுத்த பரிந்துரைக்கப்படுகிறது. இந்த மொழிபெயர்ப்பின் பயன்பாட்டினால் ஏற்படும் எந்தவொரு தவறான புரிதல்களுக்கும் அல்லது தவறான விளக்கங்களுக்கும் நாங்கள் பொறுப்பேற்கமாட்டோம்.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->