<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T22:31:43+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "hi"
}
-->
# LangChain4j शब्दावली

## विषय सूची

- [मुख्य अवधारणाएँ](../../../docs)
- [LangChain4j घटक](../../../docs)
- [AI/ML अवधारणाएँ](../../../docs)
- [प्रॉम्प्ट इंजीनियरिंग](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [एजेंट्स और टूल्स](../../../docs)
- [मॉडल कॉन्टेक्स्ट प्रोटोकॉल (MCP)](../../../docs)
- [Azure सेवाएँ](../../../docs)
- [परीक्षण और विकास](../../../docs)

कोर्स में उपयोग किए गए शब्दों और अवधारणाओं के लिए त्वरित संदर्भ।

## मुख्य अवधारणाएँ

**AI Agent** - वह सिस्टम जो तर्क करने और स्वायत्त रूप से कार्य करने के लिए एआई का उपयोग करता है। [मॉड्यूल 04](../04-tools/README.md)

**Chain** - संचालन का क्रम जिसमें आउटपुट अगले चरण में फ़ीड होता है।

**Chunking** - दस्तावेज़ों को छोटे हिस्सों में बाँटना। सामान्य: 300-500 टोकन ओवरलैप के साथ। [मॉड्यूल 03](../03-rag/README.md)

**Context Window** - अधिकतम टोकन जो एक मॉडल प्रोसेस कर सकता है। GPT-5: 400K टोकन।

**Embeddings** - टेक्स्ट के अर्थ का प्रतिनिधित्व करने वाले संख्यात्मक वेक्टर। [मॉड्यूल 03](../03-rag/README.md)

**Function Calling** - मॉडल संरचित अनुरोध उत्पन्न करता है बाहरी फ़ंक्शन्स को कॉल करने के लिए। [मॉड्यूल 04](../04-tools/README.md)

**Hallucination** - जब मॉडल गलत लेकिन संभाव्य जानकारी उत्पन्न करते हैं।

**Prompt** - भाषा मॉडल के लिए टेक्स्ट इनपुट। [मॉड्यूल 02](../02-prompt-engineering/README.md)

**Semantic Search** - कीवर्ड के बजाय एम्बेडिंग्स के उपयोग से अर्थ के आधार पर खोज। [मॉड्यूल 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: कोई मेमोरी नहीं। Stateful: बातचीत का इतिहास बनाए रखता है। [मॉड्यूल 01](../01-introduction/README.md)

**Tokens** - मॉडल द्वारा प्रोसेस किए जाने वाले मूलभूत टेक्स्ट यूनिट। लागत और सीमाओं को प्रभावित करते हैं। [मॉड्यूल 01](../01-introduction/README.md)

**Tool Chaining** - क्रमिक टूल निष्पादन जहाँ आउटपुट अगले कॉल को सूचित करता है। [मॉड्यूल 04](../04-tools/README.md)

## LangChain4j घटक

**AiServices** - टाइप-सेफ AI सेवा इंटरफेस बनाता है।

**OpenAiOfficialChatModel** - OpenAI और Azure OpenAI मॉडल के लिए एकीकृत क्लाइंट।

**OpenAiOfficialEmbeddingModel** - OpenAI Official क्लाइंट का उपयोग करके एम्बेडिंग बनाता है (OpenAI और Azure OpenAI दोनों का समर्थन)।

**ChatModel** - भाषा मॉडलों के लिए कोर इंटरफ़ेस।

**ChatMemory** - बातचीत का इतिहास बनाए रखता है।

**ContentRetriever** - RAG के लिए प्रासंगिक दस्तावेज़ चंक्स ढूँढता है।

**DocumentSplitter** - दस्तावेज़ों को चंक्स में तोड़ता है।

**EmbeddingModel** - टेक्स्ट को संख्यात्मक वेक्टर में बदलता है।

**EmbeddingStore** - एम्बेडिंग्स को स्टोर और रिट्रीव करता है।

**MessageWindowChatMemory** - हालिया संदेशों की एक स्लाइडिंग विंडो बनाए रखता है।

**PromptTemplate** - पुन:प्रयोग किए जाने वाले प्रॉम्प्ट बनाता है `{{variable}}` प्लेसहोल्डर्स के साथ।

**TextSegment** - मेटाडेटा के साथ टेक्स्ट का टुकड़ा। RAG में उपयोग होता है।

**ToolExecutionRequest** - टूल निष्पादन अनुरोध का प्रतिनिधित्व करता है।

**UserMessage / AiMessage / SystemMessage** - बातचीत के संदेश प्रकार।

## AI/ML अवधारणाएँ

**Few-Shot Learning** - प्रॉम्प्ट में उदाहरण प्रदान करना। [मॉड्यूल 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - विशाल टेक्स्ट डेटा पर प्रशिक्षित एआई मॉडल।

**Reasoning Effort** - GPT-5 पैरामीटर जो सोच की गहराई को नियंत्रित करता है। [मॉड्यूल 02](../02-prompt-engineering/README.md)

**Temperature** - आउटपुट की रैंडमनेस को नियंत्रित करता है। Low=नियतात्मक, high=रचनात्मक।

**Vector Database** - एम्बेडिंग्स के लिए विशेषीकृत डेटाबेस। [मॉड्यूल 03](../03-rag/README.md)

**Zero-Shot Learning** - उदाहरणों के बिना कार्य करना। [मॉड्यूल 02](../02-prompt-engineering/README.md)

## प्रॉम्प्ट इंजीनियरिंग - [मॉड्यूल 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - बेहतर सटीकता के लिए चरण-दर-चरण तर्क।

**Constrained Output** - विशिष्ट फ़ॉर्मैट या संरचना लागू करना।

**High Eagerness** - गहन तर्क के लिए GPT-5 पैटर्न।

**Low Eagerness** - त्वरित उत्तरों के लिए GPT-5 पैटर्न।

**Multi-Turn Conversation** - विनिमयों के बीच संदर्भ बनाए रखना।

**Role-Based Prompting** - सिस्टम संदेशों के माध्यम से मॉडल पर्सोना सेट करना।

**Self-Reflection** - मॉडल अपने आउटपुट का मूल्यांकन करता है और सुधार करता है।

**Structured Analysis** - एक नियत मूल्यांकन ढांचा।

**Task Execution Pattern** - योजना → निष्पादन → सारांश।

## RAG (Retrieval-Augmented Generation) - [मॉड्यूल 03](../03-rag/README.md)

**Document Processing Pipeline** - लोड → चंक्स में बाँटना → एम्बेड → स्टोर।

**In-Memory Embedding Store** - परीक्षण के लिए गैर-स्थायी स्टोरेज।

**RAG** - रिट्रीवल को जेनरेशन के साथ मिलाकर उत्तरों को आधार प्रदान करता है।

**Similarity Score** - अर्थगत समानता का माप (0-1)।

**Source Reference** - पुनःप्राप्त सामग्री के बारे में मेटाडेटा।

## एजेंट्स और टूल्स - [मॉड्यूल 04](../04-tools/README.md)

**@Tool Annotation** - Java मेथड्स को एआई-कॉल करने योग्य टूल के रूप में चिह्नित करता है।

**ReAct Pattern** - तर्क → कार्य → अवलोकन → पुनरावृत्ति।

**Session Management** - विभिन्न उपयोगकर्ताओं के लिए अलग संदर्भ बनाए रखना।

**Tool** - ऐसी फ़ंक्शन जिसे एक एआई एजेंट कॉल कर सकता है।

**Tool Description** - टूल के उद्देश्य और पैरामीटर का दस्तावेज़ीकरण।

## मॉडल कॉन्टेक्स्ट प्रोटोकॉल (MCP) - [मॉड्यूल 05](../05-mcp/README.md)

**MCP** - एआई ऐप्स को बाहरी टूल्स से जोड़ने के लिए मानक।

**MCP Client** - वह एप्लिकेशन जो MCP सर्वरों से जुड़ता है।

**MCP Server** - सेवा जो MCP के माध्यम से टूल्स को एक्सपोज़ करती है।

**Stdio Transport** - सर्वर को subprocess के रूप में stdin/stdout के माध्यम से चलाना।

**Tool Discovery** - क्लाइंट उपलब्ध टूल्स के लिए सर्वर से प्रश्न करता है।

## Azure सेवाएँ - [मॉड्यूल 01](../01-introduction/README.md)

**Azure AI Search** - वेक्टर क्षमताओं के साथ क्लाउड खोज। [मॉड्यूल 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure संसाधनों को डिप्लॉय करता है।

**Azure OpenAI** - Microsoft की एंटरप्राइज़ एआई सेवा।

**Bicep** - Azure इंफ्रास्ट्रक्चर-ऐज़-कोड भाषा। [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azure में मॉडल डिप्लॉयमेंट के लिए नाम।

**GPT-5** - तर्क नियंत्रण के साथ नवीनतम OpenAI मॉडल। [मॉड्यूल 02](../02-prompt-engineering/README.md)

## परीक्षण और विकास - [परीक्षण मार्गदर्शिका](TESTING.md)

**Dev Container** - कंटेनरीकृत विकास वातावरण। [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - मुफ्त एआई मॉडल प्लेग्राउंड। [मॉड्यूल 00](../00-quick-start/README.md)

**In-Memory Testing** - इन-मेमोरी स्टोरेज के साथ परीक्षण।

**Integration Testing** - वास्तविक इंफ्रास्ट्रक्चर के साथ परीक्षण।

**Maven** - Java बिल्ड ऑटोमेशन टूल।

**Mockito** - Java मॉकिंग फ़्रेमवर्क।

**Spring Boot** - Java एप्लिकेशन फ्रेमवर्क। [मॉड्यूल 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
अस्वीकरण:
यह दस्तावेज़ एआई अनुवाद सेवा Co-op Translator (https://github.com/Azure/co-op-translator) का उपयोग करके अनुवादित किया गया है। जबकि हम सटीकता के लिए प्रयास करते हैं, कृपया ध्यान दें कि स्वचालित अनुवादों में त्रुटियाँ या असमानताएँ हो सकती हैं। मूल भाषा में उपलब्ध मूल दस्तावेज़ को अधिकृत स्रोत माना जाना चाहिए। महत्वपूर्ण जानकारी के लिए पेशेवर मानव अनुवाद की सिफारिश की जाती है। इस अनुवाद के उपयोग से उत्पन्न किसी भी गलतफहमी या गलत व्याख्या के लिए हम उत्तरदायी नहीं हैं।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->