<img src="../../translated_images/ne/LangChain4j.90e1d693fcc71b50.webp" alt="LangChain4j" width="800"/>

### 🌐 बहुभाषी समर्थन

#### GitHub Action द्वारा समर्थित (स्वचालित र सधैं अपडेटमा)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arabic](../ar/README.md) | [Bengali](../bn/README.md) | [Bulgarian](../bg/README.md) | [Burmese (Myanmar)](../my/README.md) | [Chinese (Simplified)](../zh-CN/README.md) | [Chinese (Traditional, Hong Kong)](../zh-HK/README.md) | [Chinese (Traditional, Macau)](../zh-MO/README.md) | [Chinese (Traditional, Taiwan)](../zh-TW/README.md) | [Croatian](../hr/README.md) | [Czech](../cs/README.md) | [Danish](../da/README.md) | [Dutch](../nl/README.md) | [Estonian](../et/README.md) | [Finnish](../fi/README.md) | [French](../fr/README.md) | [German](../de/README.md) | [Greek](../el/README.md) | [Hebrew](../he/README.md) | [Hindi](../hi/README.md) | [Hungarian](../hu/README.md) | [Indonesian](../id/README.md) | [Italian](../it/README.md) | [Japanese](../ja/README.md) | [Kannada](../kn/README.md) | [Korean](../ko/README.md) | [Lithuanian](../lt/README.md) | [Malay](../ms/README.md) | [Malayalam](../ml/README.md) | [Marathi](../mr/README.md) | [Nepali](./README.md) | [Nigerian Pidgin](../pcm/README.md) | [Norwegian](../no/README.md) | [Persian (Farsi)](../fa/README.md) | [Polish](../pl/README.md) | [Portuguese (Brazil)](../pt-BR/README.md) | [Portuguese (Portugal)](../pt-PT/README.md) | [Punjabi (Gurmukhi)](../pa/README.md) | [Romanian](../ro/README.md) | [Russian](../ru/README.md) | [Serbian (Cyrillic)](../sr/README.md) | [Slovak](../sk/README.md) | [Slovenian](../sl/README.md) | [Spanish](../es/README.md) | [Swahili](../sw/README.md) | [Swedish](../sv/README.md) | [Tagalog (Filipino)](../tl/README.md) | [Tamil](../ta/README.md) | [Telugu](../te/README.md) | [Thai](../th/README.md) | [Turkish](../tr/README.md) | [Ukrainian](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnamese](../vi/README.md)

> **स्थानीय रूपमा क्लोन गर्न चाहनुहुन्छ?**

> यस रिपोजिटरीमा ५०+ भाषाका अनुवादहरू समावेश छन् जसले डाउनलोड साइजलाई निकै बढाउँछ। अनुवादहरू बिना क्लोन गर्न sparse checkout प्रयोग गर्नुहोस्:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> यसले तपाईंलाई कोर्स पूरा गर्न आवश्यक सबै कुरा छिटो डाउनलोडको साथ दिन्छ।
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j नयाँ सुरुवातकर्ताहरूका लागि

LangChain4j र Azure OpenAI GPT-5 सँग AI अनुप्रयोगहरू बनाउने कोर्स, आधारभूत च्याटदेखि AI एजेन्टहरू सम्म।

**LangChain4j नयाँ हुनुहुन्छ?** मुख्य शब्दहरू र अवधारणाहरूको परिभाषाका लागि [Glossary](docs/GLOSSARY.md) हेरिचाह गर्नुहोस्।

## सुची

1. [छिटो सुरुवात](00-quick-start/README.md) - LangChain4j सँग सुरु गर्नुहोस्
2. [परिचय](01-introduction/README.md) - LangChain4j का आधारहरू सिक्नुहोस्
3. [प्रम्प्ट इञ्जिनियरिङ](02-prompt-engineering/README.md) - प्रभावकारी प्रम्प्ट डिजाइनमा दक्ष हुनुहोस्
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - बौद्धिक ज्ञान-आधारित प्रणालीहरू बनाउनुहोस्
5. [उपकरणहरू](04-tools/README.md) - बाह्य उपकरणहरू र सरल सहायकहरू एकीकृत गर्नुहोस्
6. [MCP (Model Context Protocol)](05-mcp/README.md) - Model Context Protocol (MCP) र एजेन्टिक मोड्युलहरू सँग काम गर्नुहोस्
---

## सिकाइ मार्ग

> **छिटो सुरुवात**

1. यस रिपोजिटरीलाई तपाईंको GitHub खातामा Fork गर्नुहोस्
2. **Code** → **Codespaces** ट्याब → **...** → **New with options...** मा क्लिक गर्नुहोस्
3. पूर्वनिर्धारितहरू प्रयोग गर्नुहोस् – यसले यो कोर्सको लागि सिर्जना गरिएको Development container चयन गर्दछ
4. **Create codespace** मा क्लिक गर्नुहोस्
5. वातावरण तयार हुन ५-१० मिनेट पर्खनुहोस्
6. सुरु गर्न [छिटो सुरुवात](./00-quick-start/README.md) मा सिधै जानुहोस्!

मोड्युलहरू पूरा गरेपछि, LangChain4j परीक्षण अवधारणाहरूलाई क्रियामा हेर्न [Testing Guide](docs/TESTING.md) अन्वेषण गर्नुहोस्।

> **सूचना:** यो प्रशिक्षणले दुवै GitHub Models र Azure OpenAI प्रयोग गर्दछ। [छिटो सुरुवात](00-quick-start/README.md) मोड्युलले GitHub Models प्रयोग गर्छ (Azure सदस्यता आवश्यक छैन), जबकि मोड्युल १-५ ले Azure OpenAI प्रयोग गर्छ।

## GitHub Copilot सँग सिकाइ

छिटो कडिङ सुरु गर्न, यो प्रोजेक्टलाई GitHub Codespace वा तपाईंको स्थानीय IDE मा devcontainer सँग खोल्नुहोस्। यस कोर्समा प्रयोग गरिएको devcontainer GitHub Copilot sँग अगाडि नै कन्फिगर गरिएको छ जसले AI जोडी प्रोग्रामिङ समर्थन गर्छ।

प्रत्येक कोड उदाहरणले GitHub Copilot सँग सोध्न सकिने सुझाब प्रश्नहरू समावेश गर्दछ जसले तपाईंको बुझाइलाई गहिरो बनाउँछ। 💡/🤖 संकेतहरू हेर्नुहोस्:

- **Java फाइल हेडरहरू** - हरेक उदाहरणको लागि विशिष्ट प्रश्नहरू
- **मोड्युल READMEs** - कोड उदाहरणपछि अन्वेषणका लागि सुझावहरू

**कसरी प्रयोग गर्ने:** कुनै पनि कोड फाइल खोल्नुहोस् र Copilot लाई सुझाब प्रश्नहरू सोध्नुहोस्। यसलाई पूर्ण कोड आधारको सन्दर्भ थाहा छ र यसले व्याख्या, विस्तार, र विकल्पहरू सुझाव दिन सक्छ।

थप जान्न चाहनुहुन्छ? हेर्नुहोस् [AI जोडी प्रोग्रामिङका लागि Copilot](https://aka.ms/GitHubCopilotAI)।

## अतिरिक्त स्रोतहरू

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j for Beginners](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js for Beginners](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Agents
[![AZD for Beginners](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI for Beginners](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP for Beginners](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Agents for Beginners](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Generative AI Series
[![Generative AI for Beginners](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generative AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generative AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generative AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### Core Learning
[![ML for Beginners](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science for Beginners](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI for Beginners](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Cybersecurity for Beginners](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev for Beginners](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT for Beginners](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development for Beginners](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot Series
[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## सहयोग प्राप्त गर्ने

यदि तपाईं अड्किनुभयो वा AI अनुप्रयोगहरू निर्माण गर्दा कुनै प्रश्नहरू छन् भने, सहभागी हुनुहोस्:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

यदि तपाईंलाई उत्पादन प्रतिक्रियाहरू वा निर्माण गर्दा त्रुटिहरू छन् भने भ्रमण गर्नुहोस्:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## अनुमति

MIT लाइसेन्स - विवरणहरूका लागि [LICENSE](../../LICENSE) फाइल हेर्नुहोस्।

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**अस्वीकरण**:
यो दस्तावेज़ AI अनुवाद सेवा [Co-op Translator](https://github.com/Azure/co-op-translator) को प्रयोग गरी अनुवाद गरिएको हो। हामी सटीकताको प्रयास गर्दछौं भने पनि, कृपया जान्नुहोस् कि स्वचालित अनुवादहरूमा त्रुटिहरू वा अशुद्धिहरू हुन सक्छन्। मूल दस्तावेज़लाई यसको मातृ भाषामा अधिकारसम्पन्न स्रोतको रूपमा मान्नुपर्दछ। महत्वपूर्ण जानकारीको लागि, व्यावसायिक मानव अनुवाद सिफारिश गरिन्छ। यस अनुवादको प्रयोगबाट उत्पन्न कुनै पनि गलतफहमी वा गलत व्याख्याको लागि हामी जिम्मेवार छैनौं।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->