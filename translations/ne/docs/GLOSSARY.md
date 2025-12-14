<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:01:10+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ne"
}
-->
# LangChain4j शब्दावली

## सामग्री तालिका

- [मूल अवधारणाहरू](../../../docs)
- [LangChain4j कम्पोनेन्टहरू](../../../docs)
- [AI/ML अवधारणाहरू](../../../docs)
- [प्रॉम्प्ट इन्जिनियरिङ](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [एजेन्टहरू र उपकरणहरू](../../../docs)
- [मोडेल सन्दर्भ प्रोटोकल (MCP)](../../../docs)
- [एजुर सेवाहरू](../../../docs)
- [परीक्षण र विकास](../../../docs)

कोर्सभरि प्रयोग भएका शब्दहरू र अवधारणाहरूको छिटो सन्दर्भ।

## मूल अवधारणाहरू

**AI Agent** - AI प्रयोग गरेर स्वतन्त्र रूपमा तर्क गर्ने र कार्य गर्ने प्रणाली। [Module 04](../04-tools/README.md)

**Chain** - अपरेसनहरूको अनुक्रम जहाँ आउटपुट अर्को चरणमा जान्छ।

**Chunking** - कागजातहरूलाई साना टुक्रामा विभाजन गर्ने। सामान्य: ३००-५०० टोकनहरू ओभरलैपसहित। [Module 03](../03-rag/README.md)

**Context Window** - मोडेलले प्रक्रिया गर्न सक्ने अधिकतम टोकनहरू। GPT-5: ४००K टोकन।

**Embeddings** - पाठको अर्थ प्रतिनिधित्व गर्ने संख्यात्मक भेक्टरहरू। [Module 03](../03-rag/README.md)

**Function Calling** - मोडेलले बाह्य फंक्शनहरू कल गर्न संरचित अनुरोधहरू उत्पन्न गर्छ। [Module 04](../04-tools/README.md)

**Hallucination** - मोडेलहरूले गलत तर सम्भावित जानकारी उत्पन्न गर्दा।

**Prompt** - भाषा मोडेलमा टेक्स्ट इनपुट। [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - कीवर्ड होइन, अर्थ प्रयोग गरेर खोज। [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: कुनै मेमोरी छैन। Stateful: संवाद इतिहास राख्छ। [Module 01](../01-introduction/README.md)

**Tokens** - मोडेलहरूले प्रक्रिया गर्ने आधारभूत पाठ इकाइहरू। लागत र सीमाहरूमा प्रभाव पार्छ। [Module 01](../01-introduction/README.md)

**Tool Chaining** - उपकरणहरूको अनुक्रमिक कार्यान्वयन जहाँ आउटपुटले अर्को कललाई सूचित गर्छ। [Module 04](../04-tools/README.md)

## LangChain4j कम्पोनेन्टहरू

**AiServices** - प्रकार-सुरक्षित AI सेवा इन्टरफेसहरू सिर्जना गर्छ।

**OpenAiOfficialChatModel** - OpenAI र Azure OpenAI मोडेलहरूको एकीकृत क्लाइन्ट।

**OpenAiOfficialEmbeddingModel** - OpenAI Official क्लाइन्ट प्रयोग गरेर embeddings सिर्जना गर्छ (OpenAI र Azure OpenAI दुवै समर्थन गर्दछ)।

**ChatModel** - भाषा मोडेलहरूको मुख्य इन्टरफेस।

**ChatMemory** - संवाद इतिहास राख्छ।

**ContentRetriever** - RAG का लागि सान्दर्भिक कागजात टुक्राहरू फेला पार्छ।

**DocumentSplitter** - कागजातहरूलाई टुक्रामा विभाजन गर्छ।

**EmbeddingModel** - पाठलाई संख्यात्मक भेक्टरमा रूपान्तरण गर्छ।

**EmbeddingStore** - embeddings भण्डारण र पुनःप्राप्त गर्छ।

**MessageWindowChatMemory** - हालसालैका सन्देशहरूको स्लाइडिङ विन्डो राख्छ।

**PromptTemplate** - `{{variable}}` प्लेसहोल्डरहरूसहित पुन: प्रयोगयोग्य प्रॉम्प्टहरू सिर्जना गर्छ।

**TextSegment** - मेटाडेटासहितको पाठ टुक्रा। RAG मा प्रयोग हुन्छ।

**ToolExecutionRequest** - उपकरण कार्यान्वयन अनुरोध प्रतिनिधित्व गर्छ।

**UserMessage / AiMessage / SystemMessage** - संवाद सन्देश प्रकारहरू।

## AI/ML अवधारणाहरू

**Few-Shot Learning** - प्रॉम्प्टहरूमा उदाहरणहरू प्रदान गर्ने। [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - विशाल पाठ डाटामा तालिम प्राप्त AI मोडेलहरू।

**Reasoning Effort** - GPT-5 को तर्क गहिराइ नियन्त्रण गर्ने प्यारामिटर। [Module 02](../02-prompt-engineering/README.md)

**Temperature** - आउटपुटको अनियमितता नियन्त्रण गर्छ। कम=निश्चित, उच्च=रचनात्मक।

**Vector Database** - embeddings का लागि विशेष डेटाबेस। [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - उदाहरण बिना कार्यहरू प्रदर्शन गर्ने। [Module 02](../02-prompt-engineering/README.md)

## प्रॉम्प्ट इन्जिनियरिङ - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - राम्रो सटीकताका लागि चरण-द्वारा-चरण तर्क।

**Constrained Output** - विशेष ढाँचा वा संरचना लागू गर्ने।

**High Eagerness** - GPT-5 को गहिरो तर्कको ढाँचा।

**Low Eagerness** - GPT-5 को छिटो उत्तर दिने ढाँचा।

**Multi-Turn Conversation** - विनिमयहरूमा सन्दर्भ कायम राख्ने।

**Role-Based Prompting** - प्रणाली सन्देशहरू मार्फत मोडेल व्यक्तित्व सेट गर्ने।

**Self-Reflection** - मोडेलले आफ्नो आउटपुट मूल्याङ्कन र सुधार गर्छ।

**Structured Analysis** - निश्चित मूल्याङ्कन फ्रेमवर्क।

**Task Execution Pattern** - योजना → कार्यान्वयन → सारांश।

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - लोड → टुक्रा → एम्बेड → भण्डारण।

**In-Memory Embedding Store** - परीक्षणका लागि अस्थायी भण्डारण।

**RAG** - प्रतिक्रियाहरूलाई आधार दिन पुनःप्राप्ति र उत्पादन संयोजन।

**Similarity Score** - सेम्यान्टिक समानताको मापन (०-१)।

**Source Reference** - पुनःप्राप्त सामग्रीको मेटाडेटा।

## एजेन्टहरू र उपकरणहरू - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Java विधिहरूलाई AI-कलयोग्य उपकरणहरूका रूपमा चिन्हित गर्छ।

**ReAct Pattern** - तर्क → कार्य → अवलोकन → पुनरावृत्ति।

**Session Management** - फरक प्रयोगकर्ताहरूका लागि अलग सन्दर्भहरू।

**Tool** - AI एजेन्टले कल गर्न सक्ने फंक्शन।

**Tool Description** - उपकरणको उद्देश्य र प्यारामिटरहरूको दस्तावेजीकरण।

## मोडेल सन्दर्भ प्रोटोकल (MCP) - [Module 05](../05-mcp/README.md)

**Docker Transport** - Docker कन्टेनरमा MCP सर्भर।

**MCP** - AI एपहरूलाई बाह्य उपकरणहरूसँग जडान गर्ने मानक।

**MCP Client** - MCP सर्भरहरूसँग जडान गर्ने एप्लिकेसन।

**MCP Server** - MCP मार्फत उपकरणहरू प्रदान गर्ने सेवा।

**Server-Sent Events (SSE)** - HTTP मार्फत सर्भरदेखि क्लाइन्ट स्ट्रिमिङ।

**Stdio Transport** - stdin/stdout मार्फत सबप्रोसेसको रूपमा सर्भर।

**Streamable HTTP Transport** - वास्तविक-समय सञ्चारका लागि SSE सहित HTTP।

**Tool Discovery** - क्लाइन्टले उपलब्ध उपकरणहरूको लागि सर्भरलाई सोध्छ।

## एजुर सेवाहरू - [Module 01](../01-introduction/README.md)

**Azure AI Search** - भेक्टर क्षमतासहितको क्लाउड खोज। [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure स्रोतहरू तैनाथ गर्छ।

**Azure OpenAI** - Microsoft को उद्यम AI सेवा।

**Bicep** - Azure पूर्वाधार-कोड भाषा। [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azure मा मोडेल तैनाथीकरणको नाम।

**GPT-5** - reasoning नियन्त्रणसहितको नवीनतम OpenAI मोडेल। [Module 02](../02-prompt-engineering/README.md)

## परीक्षण र विकास - [Testing Guide](TESTING.md)

**Dev Container** - कन्टेनराइज्ड विकास वातावरण। [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - निःशुल्क AI मोडेल खेल मैदान। [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - इन-मेमोरी भण्डारणसँग परीक्षण।

**Integration Testing** - वास्तविक पूर्वाधारसँग परीक्षण।

**Maven** - Java निर्माण स्वचालन उपकरण।

**Mockito** - Java मोकिङ फ्रेमवर्क।

**Spring Boot** - Java एप्लिकेसन फ्रेमवर्क। [Module 01](../01-introduction/README.md)

**Testcontainers** - परीक्षणहरूमा Docker कन्टेनरहरू।

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**अस्वीकरण**:
यो दस्तावेज AI अनुवाद सेवा [Co-op Translator](https://github.com/Azure/co-op-translator) प्रयोग गरी अनुवाद गरिएको हो। हामी शुद्धताका लागि प्रयासरत छौं, तर कृपया ध्यान दिनुहोस् कि स्वचालित अनुवादमा त्रुटि वा अशुद्धता हुन सक्छ। मूल दस्तावेज यसको मूल भाषामा नै अधिकारिक स्रोत मानिनु पर्छ। महत्वपूर्ण जानकारीका लागि व्यावसायिक मानव अनुवाद सिफारिस गरिन्छ। यस अनुवादको प्रयोगबाट उत्पन्न कुनै पनि गलतफहमी वा गलत व्याख्याका लागि हामी जिम्मेवार छैनौं।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->