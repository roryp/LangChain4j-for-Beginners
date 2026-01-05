<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "d61ab6c245562094cc3dddecf08b50d3",
  "translation_date": "2025-12-30T23:04:43+00:00",
  "source_file": "README.md",
  "language_code": "ne"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b50.ne.png" alt="LangChain4j" width="800"/>

### 🌐 बहुभाषी समर्थन

#### GitHub Action मार्फत समर्थन (स्वचालित र सधैं अद्यावधिक)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[अरबी](../ar/README.md) | [बांग्ला](../bn/README.md) | [बुल्गेरियन](../bg/README.md) | [बर्मी (म्यानमार)](../my/README.md) | [चिनियाँ (सरलीकृत)](../zh/README.md) | [चिनियाँ (परम्परागत, हङकङ)](../hk/README.md) | [चिनियाँ (परम्परागत, मकाउ)](../mo/README.md) | [चिनियाँ (परम्परागत, ताइवान)](../tw/README.md) | [क्रोएशियाली](../hr/README.md) | [चेक](../cs/README.md) | [डेनिश](../da/README.md) | [डच](../nl/README.md) | [एस्टोनियन](../et/README.md) | [फिनिश](../fi/README.md) | [फ्रेन्च](../fr/README.md) | [जर्मन](../de/README.md) | [ग्रीक](../el/README.md) | [हिब्रू](../he/README.md) | [हिन्दी](../hi/README.md) | [हंगेरीयन](../hu/README.md) | [इन्डोनेशियाली](../id/README.md) | [इटालियन](../it/README.md) | [जापानी](../ja/README.md) | [कन्नड़](../kn/README.md) | [कोरियाली](../ko/README.md) | [लिथुआनियाली](../lt/README.md) | [मलय](../ms/README.md) | [मलयालम](../ml/README.md) | [मराठी](../mr/README.md) | [नेपाली](./README.md) | [नाइजीरियाली पिड्जिन](../pcm/README.md) | [नर्वेजियन](../no/README.md) | [फारसी (Farsi)](../fa/README.md) | [पोलिश](../pl/README.md) | [पोर्चुगिज (ब्राजिल)](../br/README.md) | [पोर्चुगिज (पुर्तगाल)](../pt/README.md) | [पंजाबी (गुरमुखी)](../pa/README.md) | [रोमानियन](../ro/README.md) | [रूसी](../ru/README.md) | [सर्बियन (सिरेलिक)](../sr/README.md) | [स्लोभाक](../sk/README.md) | [स्लोभेनियन](../sl/README.md) | [स्पेनिश](../es/README.md) | [स्वाहिली](../sw/README.md) | [स्विडिश](../sv/README.md) | [ट्यागालोग (फिलिपिनो)](../tl/README.md) | [तमिल](../ta/README.md) | [तेलुगु](../te/README.md) | [थाई](../th/README.md) | [टर्किश](../tr/README.md) | [युक्रेनी](../uk/README.md) | [उर्दू](../ur/README.md) | [भियतनामी](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j नयाँ प्रयोगकर्ताहरूका लागि

LangChain4j र Azure OpenAI GPT-5 प्रयोग गरी आधारभूत च्याटदेखि AI एजेन्टहरू सम्म AI अनुप्रयोगहरू निर्माण गर्ने कोर्स।

**LangChain4j नयाँ हुनुहुन्छ?** प्रमुख शब्द र अवधारणाहरूको परिभाषाका लागि [शब्दावली](docs/GLOSSARY.md) हेर्नुहोस्।

## Table of Contents

1. [छिटो सुरु](00-quick-start/README.md) - LangChain4j सँग आरम्भ गर्नुहोस्
2. [परिचय](01-introduction/README.md) - LangChain4j का आधारभूत कुरा सिक्नुहोस्
3. [प्रम्प्ट इन्जिनियरिङ](02-prompt-engineering/README.md) - प्रभावकारी प्रम्प्ट डिजाइनमा पारंगत हुनुहोस्
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - बुद्धिमानी ज्ञान-आधारित प्रणालीहरू निर्माण गर्नुहोस्
5. [उपकरणहरू](04-tools/README.md) - बाह्य उपकरणहरू र सरल सहायकहरू एकीकृत गर्नुहोस्
6. [MCP (Model Context Protocol)](05-mcp/README.md) - Model Context Protocol (MCP) र Agentic मोड्युलहरूसँग काम गर्नुहोस्
---

##  सिकाइ मार्ग

> **छिटो सुरु**

1. यो रिपोजिटरीलाई आफ्नो GitHub खातामा Fork गर्नुहोस्
2. क्लिक गर्नुहोस् **Code** → **Codespaces** ट्याब → **...** → **New with options...**
3. पूर्वनिर्धारितहरू प्रयोग गर्नुहोस् – यसले यस कोर्सका लागि सिर्जना गरिएको Development container चयन गर्नेछ
4. क्लिक गर्नुहोस् **Create codespace**
5. वातावरण तयार हुन 5-10 मिनेट पर्खनुहोस्
6. तुरुन्तै सुरु गर्न [छिटो सुरु](./00-quick-start/README.md) मा जानुहोस्!

> **स्थानीय रूपमा क्लोन गर्न रुचाउनुहुन्छ?**
>
> यो रिपोजिटरीमा 50+ भाषा अनुवादहरू समावेश छन् जसले डाउनलोड साइजलाई उल्लेखनीय बढाउँछ। अनुवादहरू बिना क्लोन गर्न, sparse checkout प्रयोग गर्नुहोस्:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> यसले तपाईंलाई कोर्स पूरा गर्न आवश्यक सबै कुरा धेरै छिटो डाउनलोडको साथ दिन्छ।

मोड्युलहरू पूरा गरेपछि, LangChain4j परीक्षण सिद्धान्तहरू कार्यमा हेर्न [परीक्षण मार्गदर्शिका](docs/TESTING.md) अन्वेषण गर्नुहोस्।

> **नोट:** यो तालिमले GitHub Models र Azure OpenAI दुवै प्रयोग गर्छ। [छिटो सुरु](00-quick-start/README.md) र [MCP](05-mcp/README.md) मोड्युलहरूले GitHub Models प्रयोग गर्छन् (Azure सदस्यता आवश्यक छैन), जबकि मोड्युल 1-4 ले Azure OpenAI GPT-5 प्रयोग गर्छन्।

## GitHub Copilot सँग सिक्ने

द्रुत रूपमा कोडिङ सुरु गर्न, यो प्रोजेक्ट GitHub Codespace वा उपलब्ध devcontainer सहित आफ्नै लोकल IDE मा खोल्नुहोस्। यस कोर्समा प्रयोग गरिएको devcontainer GitHub Copilot सँग AI जोडी प्रोग्रामिङका लागि पूर्व-कन्फिगर गरिएको आउँछ।

प्रत्येक कोड उदाहरणमा GitHub Copilot लाई सोध्न सकिने सुझाएको प्रश्नहरू समावेश छन् जसले तपाईंको बुझाइ बढाउँछ। 💡/🤖 संकेतहरू तल खोज्नुहोस्:

- **Java फाइल हेडरहरू** - प्रत्येक उदाहरणका लागि विशिष्ट प्रश्नहरू
- **Module READMEहरू** - कोड उदाहरणपछि अन्वेषणात्मक संकेतहरू

**कसरी प्रयोग गर्ने:** कुनै पनि कोड फाइल खोल्नुहोस् र Copilot लाई सुझाएका प्रश्नहरू सोध्नुहोस्। यसले कोडबेसको पूर्ण सन्दर्भ थाहा पाउँछ र स्पस्ट पार्न, विस्तार गर्न, र वैकल्पिक सुझाव दिन सक्छ।

थप जान्न चाहनुहुन्छ? हेर्नुहोस् [AI जोडी प्रोग्रामिङका लागि Copilot](https://aka.ms/GitHubCopilotAI).


## अतिरिक्त स्रोतहरू

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Agents
[![AZD नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Agents नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Generative AI श्रृंखला
[![Generative AI नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generative AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generative AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generative AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### मुख्य सिकाइ
[![ML नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![साइबरसुरक्षा नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development नयाँ प्रयोगकर्ताहरूका लागि](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot श्रृंखला
[![AI पेयर्ड प्रोग्रामिङका लागि Copilot](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![C#/.NET का लागि Copilot](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot साहसिक](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## मद्दत पाउनुहोस्

यदि तपाईं अड्किनुहुन्छ वा AI एपहरू निर्माण गर्दा कुनै प्रश्न भएमा, सामेल हुनुहोस्:

[![Azure AI Foundry डिस्कोर्ड](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

यदि तपाईंसँग उत्पादन सम्बन्धी प्रतिक्रिया वा निर्माण गर्दा त्रुटिहरू भएमा, भ्रमण गर्नुहोस्:

[![Azure AI Foundry विकासकर्ता फोरम](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## लाइसेन्स

MIT लाइसेन्स - विस्तृत विवरणका लागि [LICENSE](../../LICENSE) फाइल हेर्नुहोस्.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
अस्वीकरण:
यो दस्तावेज AI अनुवाद सेवा [Co-op Translator](https://github.com/Azure/co-op-translator) प्रयोग गरी अनुवाद गरिएको हो। हामी शुद्धताका लागि प्रयास गर्छौं, तर कृपया ध्यान दिनुहोस् कि स्वचालित अनुवादमा त्रुटि वा अशुद्धि हुन सक्छ। मूल दस्तावेज यसको मूल भाषामा नै अधिकारप्राप्त स्रोत मानिनेछ। महत्वपूर्ण जानकारीका लागि पेशेवर मानवीय अनुवाद सिफारिस गरिन्छ। यस अनुवादको प्रयोगबाट उत्पन्न कुनै पनि गलतफहमी वा गलत व्याख्याका लागि हामी जिम्मेवार हुनेछैनौं।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->