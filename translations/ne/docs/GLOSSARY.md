<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T23:13:35+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ne"
}
-->
# LangChain4j शब्दावली

## सामग्री तालिका

- [मुख्य अवधारणाहरू](../../../docs)
- [LangChain4j कम्पोनेन्टहरू](../../../docs)
- [AI/ML अवधारणाहरू](../../../docs)
- [प्रॉम्प्ट इन्जिनियरिङ](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [एजेन्ट र टुलहरू](../../../docs)
- [मोडेल सन्दर्भ प्रोटोकल (MCP)](../../../docs)
- [Azure सेवाहरू](../../../docs)
- [परीक्षण र विकास](../../../docs)

कोर्सभर प्रयोग भएका शर्तहरू र अवधारणाहरूको छिटो सन्दर्भ।

## मुख्य अवधारणाहरू

**AI Agent** - AI प्रयोग गरेर स्वायत्त रूपमा तर्क गर्ने र कार्य गर्ने प्रणाली। [मोड्युल 04](../04-tools/README.md)

**Chain** - यस्तो अपरेशनहरूको अनुक्रम जहाँ आउटपुट अर्को चरणमा इनपुटको रूपमा जान्छ।

**Chunking** - दस्तावेजहरूलाई साना टुक्रामा विभाजन गर्ने प्रक्रिया। सामान्य: 300-500 टोकन ओभरल्यापसहित। [मोड्युल 03](../03-rag/README.md)

**Context Window** - मोडेलले प्रक्रिया गर्न सक्ने अधिकतम टोकन संख्या। GPT-5: 400K tokens।

**Embeddings** - पाठको अर्थ प्रतिनिधित्व गर्ने संख्यात्मक भेक्टरहरू। [मोड्युल 03](../03-rag/README.md)

**Function Calling** - मोडेलले बाह्य फङ्शनहरू कल गर्न संरचित अनुरोध उत्पन्न गर्छ। [मोड्युल 04](../04-tools/README.md)

**Hallucination** - जब मोडेलले गलत तर विश्वासयोग्य जानकारी उत्पन्न गर्छ।

**Prompt** - भाषा मोडेललाई दिइने पाठ इनपुट। [मोड्युल 02](../02-prompt-engineering/README.md)

**Semantic Search** - अर्थ प्रयोग गरेर embeddings मार्फत खोज, कुञ्जीशब्द होइन। [मोड्युल 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: कुनै मेमोरी हुँदैन। Stateful: वार्तालाप इतिहास कायम राख्छ। [मोड्युल 01](../01-introduction/README.md)

**Tokens** - मोडेलले प्रक्रिया गर्ने आधारभूत पाठ एकाइहरू। लागत र सीमाहरूमा असर गर्छ। [मोड्युल 01](../01-introduction/README.md)

**Tool Chaining** - टुलहरूको अनुक्रमिक कार्यान्वयन जहाँ आउटपुटले अर्को कललाई जानकारी दिन्छ। [मोड्युल 04](../04-tools/README.md)

## LangChain4j कम्पोनेन्टहरू

**AiServices** - प्रकार-सेफ AI सेवा इन्टरफेसहरू बनाउँछ।

**OpenAiOfficialChatModel** - OpenAI र Azure OpenAI मोडेलहरूका लागि एकीकृत क्लाइन्ट।

**OpenAiOfficialEmbeddingModel** - OpenAI Official क्लाइन्ट प्रयोग गरी embeddings बनाउँछ (OpenAI र Azure OpenAI दुवै समर्थन गर्छ)।

**ChatModel** - भाषा मोडेलहरूको लागि कोर इन्टरफेस।

**ChatMemory** - वार्तालाप इतिहास कायम राख्छ।

**ContentRetriever** - RAG का लागि सम्बन्धित दस्तावेजका टुक्राहरू फेला पार्छ।

**DocumentSplitter** - दस्तावेजहरूलाई टुक्रामा विभाजन गर्छ।

**EmbeddingModel** - पाठलाई संख्यात्मक भेक्टरमा रूपान्तरण गर्छ।

**EmbeddingStore** - embeddings भण्डारण र पुन:प्राप्त गर्छ।

**MessageWindowChatMemory** - भर्खरका सन्देशहरूको स्लाइडिङ विन्डो कायम राख्छ।

**PromptTemplate** - पुन:प्रयोगयोग्य प्रॉम्प्टहरू `{{variable}}` प्लेसहोल्डरहरूसँग सिर्जना गर्छ।

**TextSegment** - मेटाडाटा सहितको पाठ टुक्रा। RAG मा प्रयोग हुन्छ।

**ToolExecutionRequest** - टुल कार्यान्वयन अनुरोधलाई प्रतिनिधित्व गर्छ।

**UserMessage / AiMessage / SystemMessage** - वार्तालाप सन्देश प्रकारहरू।

## AI/ML अवधारणाहरू

**Few-Shot Learning** - प्रॉम्प्टहरूमा उदाहरणहरू प्रदान गर्ने। [मोड्युल 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - विशाल पाठ डाटामा तालिम गरिएको AI मोडेलहरू।

**Reasoning Effort** - सोचाईको गहिराइ नियन्त्रण गर्ने GPT-5 प्यारामिटर। [मोड्युल 02](../02-prompt-engineering/README.md)

**Temperature** - आउटपुटको र्यान्डमनेस नियन्त्रण गर्छ। Low=नियतात्मक, high=रचनात्मक।

**Vector Database** - embeddings हरूका लागि विशेषीकृत डाटाबेस। [मोड्युल 03](../03-rag/README.md)

**Zero-Shot Learning** - उदाहरणविना कार्यहरू प्रदर्शन गर्ने। [मोड्युल 02](../02-prompt-engineering/README.md)

## प्रॉम्प्ट इन्जिनियरिङ - [मोड्युल 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - राम्रो शुद्धताको लागि चरणबद्ध तर्क प्रक्रिया।

**Constrained Output** - निश्चित ढाँचा वा संरचना लागू गराउने।

**High Eagerness** - गहिरो तर्कका लागि GPT-5 ढाँचा।

**Low Eagerness** - छिटो उत्तरका लागि GPT-5 ढाँचा।

**Multi-Turn Conversation** - आदानप्रदानहरूमा सन्दर्भ कायम राख्ने।

**Role-Based Prompting** - सिस्टम सन्देशहरू मार्फत मोडेलको पात्र सेट गर्ने।

**Self-Reflection** - मोडेलले आफ्नो आउटपुट मूल्याङ्कन गरी सुधार गर्ने प्रक्रिया।

**Structured Analysis** - निश्चित मूल्याङ्कन ढाँचा।

**Task Execution Pattern** - योजना → कार्यान्वयन → सारांश।

## RAG (Retrieval-Augmented Generation) - [मोड्युल 03](../03-rag/README.md)

**Document Processing Pipeline** - Load → chunk → embed → store।

**In-Memory Embedding Store** - परीक्षणका लागि गैर-स्थायी (non-persistent) स्टोरेज।

**RAG** - retrieval र generation मिश्रण गरी प्रतिक्रियाहरूलाई आधारभूत बनाउँछ।

**Similarity Score** - सेमान्टिक समानताको मापन (0-1)।

**Source Reference** - प्राप्त सामग्रीबारे मेटाडाटा।

## एजेन्ट र टुलहरू - [मोड्युल 04](../04-tools/README.md)

**@Tool Annotation** - Java मेथडहरूलाई AI-कल गर्न मिल्ने टुलको रूपमा चिन्हित गर्छ।

**ReAct Pattern** - Reason → Act → Observe → Repeat।

**Session Management** - विभिन्न प्रयोगकर्ताहरूका लागि पृथक सन्दर्भ व्यवस्थापन।

**Tool** - AI एजेन्टले कल गर्न सक्ने फङ्शन।

**Tool Description** - टुलको उद्देश्य र प्यारामिटरहरूको दस्तावेजीकरण।

## मोडेल सन्दर्भ प्रोटोकल (MCP) - [मोड्युल 05](../05-mcp/README.md)

**MCP** - AI एपहरूलाई बाह्य टुलसँग जडान गर्ने मानक।

**MCP Client** - MCP सर्भरहरूमा जडान गर्ने एप्लिकेशन।

**MCP Server** - MCP मार्फत टुलहरू उपलब्ध गराउने सेवा।

**Stdio Transport** - stdin/stdout मार्फत सबप्रोसेसको रूपमा सर्भर चलाउने तरीका।

**Tool Discovery** - क्लाइन्टले उपलब्ध टुलहरूको लागि सर्भरलाई सोधपुछ गर्छ।

## Azure सेवाहरू - [मोड्युल 01](../01-introduction/README.md)

**Azure AI Search** - भेक्टर क्षमताहरू सहितको क्लाउड सर्च। [मोड्युल 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure स्रोतहरू डिप्लोय गर्छ।

**Azure OpenAI** - माइक्रोसफ्टको एन्त्रप्राइज AI सेवा।

**Bicep** - Azure इन्फ्रास्ट्रक्चर-एज़-कोड भाषा। [पूर्वाधार मार्गदर्शक](../01-introduction/infra/README.md)

**Deployment Name** - Azure मा मोडेल डिप्लोयमेन्टको नाम।

**GPT-5** - तर्क नियन्त्रणसहितको नवीनतम OpenAI मोडेल। [मोड्युल 02](../02-prompt-engineering/README.md)

## परीक्षण र विकास - [परीक्षण मार्गदर्शक](TESTING.md)

**Dev Container** - कन्टेनरयुक्त विकास वातावरण। [कन्फिगरेसन](../../../.devcontainer/devcontainer.json)

**GitHub Models** - निःशुल्क AI मोडेल प्लेग्राउन्ड। [मोड्युल 00](../00-quick-start/README.md)

**In-Memory Testing** - इन-मेमोरी स्टोरेजसहित परीक्षण।

**Integration Testing** - वास्तविक पूर्वाधारसँग परीक्षण।

**Maven** - Java बिल्ड स्वचालन उपकरण।

**Mockito** - Java मोकिंग फ्रेमवर्क।

**Spring Boot** - Java एप्लिकेशन फ्रेमवर्क। [मोड्युल 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
अस्वीकरण:
यो दस्तावेज AI अनुवाद सेवा [Co-op Translator](https://github.com/Azure/co-op-translator) प्रयोग गरी अनुवाद गरिएको हो। हामी सटीकता कायम राख्न प्रयास गर्छौं, तर कृपया ध्यान दिनुहोस् कि स्वचालित अनुवादमा त्रुटि वा अशुद्धता हुनसक्छ। मूल दस्तावेज यसको मूल भाषामा नै अधिकारिक स्रोत मानिनुपर्छ। महत्वपूर्ण जानकारीका लागि व्यावसायिक मानवीय अनुवादको सिफारिस गरिन्छ। यस अनुवादको प्रयोगबाट उत्पन्न कुनै पनि गलतसमझ वा गलत व्याख्याका लागि हामी जिम्मेवार छैनौं।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->