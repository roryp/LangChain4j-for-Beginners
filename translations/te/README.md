<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "6a3bd54fc243ce3dc79d18848d2b5413",
  "translation_date": "2026-01-06T02:10:16+00:00",
  "source_file": "README.md",
  "language_code": "te"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b50.te.png" alt="LangChain4j" width="800"/>

### 🌐 బహుభాషా మద్దతు

#### GitHub యాక్షన్ ద్వారా మద్దతు (ఆటోమేటెడ్ & ఎల్లప్పుడూ తాజా)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arabic](../ar/README.md) | [Bengali](../bn/README.md) | [Bulgarian](../bg/README.md) | [Burmese (Myanmar)](../my/README.md) | [Chinese (Simplified)](../zh/README.md) | [Chinese (Traditional, Hong Kong)](../hk/README.md) | [Chinese (Traditional, Macau)](../mo/README.md) | [Chinese (Traditional, Taiwan)](../tw/README.md) | [Croatian](../hr/README.md) | [Czech](../cs/README.md) | [Danish](../da/README.md) | [Dutch](../nl/README.md) | [Estonian](../et/README.md) | [Finnish](../fi/README.md) | [French](../fr/README.md) | [German](../de/README.md) | [Greek](../el/README.md) | [Hebrew](../he/README.md) | [Hindi](../hi/README.md) | [Hungarian](../hu/README.md) | [Indonesian](../id/README.md) | [Italian](../it/README.md) | [Japanese](../ja/README.md) | [Kannada](../kn/README.md) | [Korean](../ko/README.md) | [Lithuanian](../lt/README.md) | [Malay](../ms/README.md) | [Malayalam](../ml/README.md) | [Marathi](../mr/README.md) | [Nepali](../ne/README.md) | [Nigerian Pidgin](../pcm/README.md) | [Norwegian](../no/README.md) | [Persian (Farsi)](../fa/README.md) | [Polish](../pl/README.md) | [Portuguese (Brazil)](../br/README.md) | [Portuguese (Portugal)](../pt/README.md) | [Punjabi (Gurmukhi)](../pa/README.md) | [Romanian](../ro/README.md) | [Russian](../ru/README.md) | [Serbian (Cyrillic)](../sr/README.md) | [Slovak](../sk/README.md) | [Slovenian](../sl/README.md) | [Spanish](../es/README.md) | [Swahili](../sw/README.md) | [Swedish](../sv/README.md) | [Tagalog (Filipino)](../tl/README.md) | [Tamil](../ta/README.md) | [Telugu](./README.md) | [Thai](../th/README.md) | [Turkish](../tr/README.md) | [Ukrainian](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnamese](../vi/README.md)

> **స్థానికంగా క్లోన్ చేయాలని ఇష్టపడుతున్నారా?**

> ఈ రిపోజిటరీ 50+ భాషా అనువాదాలను కలిగి ఉంది, ఇది డౌన్‌లోడ్ పరిమాణాన్ని ముఖ్యంగా పెంచుతుంది. అనువాదాలు లేకుండా క్లోన్ చేయడానికి, స్పార్స్ చెకౌట్ ఉపయోగించండి:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> ఇది మీకు కోర్సు పూర్తిచేయడానికి కావలసిన అన్ని విషయాలను ఎక్కువ వేగంగా డౌన్‌లోడ్‌తో ఇస్తుంది.
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j ప్రారంభికుల కోసం

LangChain4j మరియు Azure OpenAI GPT-5 తో AI అప్లికేషన్లు నిర్మించుకోవడానికి కోర్సు, ప్రాథమిక చాట్ నుండి AI ఏజెంట్స్ వరకు.

**LangChain4j కు కొత్తవారు?** ప్రధాన పదాలు మరియు కాన్సెప్ట్ల నిర్వచనాలకు [Glossary](docs/GLOSSARY.md) చూడండి.

## సూచిసూచి

1. [త్వరిత ప్రారంభం](00-quick-start/README.md) - LangChain4j తో ప్రారంభించండి
2. [ పరిచయం](01-introduction/README.md) - LangChain4j యొక్క ప్రాథమికాలను తెలుసుకోండి
3. [ప్రాంప్ట్ ఇంజినీరింగ్](02-prompt-engineering/README.md) - సమర్థవంతమైన ప్రాంప్ట్ డిజైన్ నేర్చుకోండి
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - తెలివైన జ్ఞాన ఆధారిత సిస్టమ్స్ నిర్మించండి
5. [పరికరాలు](04-tools/README.md) - బయటి పరికరాలు మరియు సింపుల్ అసిస్టెంట్లను ఇన్‌టీగ్రేట్ చేయండి
6. [MCP (మోడల్ కాంటెక్స్ట్ ప్రోటోకాల్)](05-mcp/README.md) - మోడల్ కాంటెక్స్ట్ ప్రోటోకాల్ (MCP) మరియు ఏజెంటిక్ మాడ్యూల్స్ తో పని చేయండి
---

##  అభ్యసన మార్గం

> **త్వరిత ప్రారంభం**

1. ఈ రిపాజిటరీని మీ GitHub ఖాతాకు Fork చేయండి
2. క్లిక్ చేయండి **Code** → **Codespaces** ట్యాబ్ → **...** → **New with options...**
3. డిఫాల్ట్స్ ఉపయోగించండి – ఇది ఈ కోర్సు కోసం సృష్టించిన డెవలప్‌మెంట్ కంటైనర్‌ని ఎంచుకుంటుంది
4. **Create codespace** క్లిక్ చేయండి
5. వాతావరణం సిద్ధంగా ఉండటానికి 5-10 నిమిషాలు వేచి ఉండండి
6. ప్రారంభించడానికి నేరుగా [Quick Start](./00-quick-start/README.md) వైపు పోయండి!

> **స్థానికంగా క్లోన్ చేయాలని ఇష్టపడుతున్నారా?**
>
> ఈ రిపోజిటరీలో 50+ భాషా అనువాదాలు ఉన్నాయి, ఇవి డౌన్లోడ్ పరిమాణాన్ని పెంచుతాయి. అనువాదాలు లేకుండా క్లోన్ చేయడానికి, స్పార్స్ చెకౌట్ ఉపయోగించండి:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> ఇది మీకు కోర్సు పూర్తిచేయడానికి కావలసిన దాన్ని తక్కువ సమయ వ్యవధిలో ఇస్తుంది.

మాడ్యూల్స్ పూర్తిచేసిన తర్వాత, LangChain4j టెస్టింగ్ కాన్సెప్ట్స్‌ను అనుభవించేందుకు [Testing Guide](docs/TESTING.md) ను అన్వేషించండి.

> **గమనిక:** ఈ శిక్షణలో GitHub Models మరియు Azure OpenAI రెండూ ఉపయోగించబడతాయి. [Quick Start](00-quick-start/README.md) మాడ్యూల్ GitHub Models ఉపయోగిస్తుంది (Azure సబ్‌స్క్రిప్షన్ అవసరం లేదు), అయితే మాడ్యూల్స్ 1-5 Azure OpenAI ఉపయోగిస్తాయి.


## GitHub Copilot తో నేర్చుకోండి

త్వరగా కోడింగ్ ప్రారంభించడానికి, ఈ ప్రాజెక్టును GitHub Codespace లో లేదా మీ స్థానిక IDE లో అందించిన devcontainer తో తెరిచి చూడండి. ఈ కోర్సులో ఉపయోగించిన devcontainer GitHub Copilot తో ముందే కాన్ఫిగర్ చేయబడింది, ఇది AI జత ప్రోగ్రామింగ్ కోసం.

ప్రతి కోడ్ ఉదాహరణలో మీరు GitHub Copilot ను అడగవచ్చని సూచించే ప్రశ్నలు ఉంటాయి, అవి మీ అవగాహనను పెంపొందిస్తాయి. ఈ 💡/🤖 సూచనలు కోసం చూడండి:

- **Java ఫైల్ హెడర్లు** - ప్రతి ఉదాహరణకి ప్రత్యేకమైన ప్రశ్నలు
- **మాడ్యూల్ READMEలు** - కోడ్ ఉదాహరణల తర్వాత అన్వేషణ సూచనలు

**ఎలా ఉపయోగించాలి:** ఏ కోడ్ ఫైల్ ను తెరచి Copilot కి సూచించిన ప్రశ్నలు అడగండి. ఇది పూర్తి కోడ్‌బేస్ యొక్క సందర్భాన్ని తెలుసుకుని వివరణ, నిర్మాణం మరియు ప్రత్యామ్నాయాలను సూచించగలదు.

మరింత తెలుసుకోవాలంటే [Copilot for AI Paired Programming](https://aka.ms/GitHubCopilotAI) చూడండి.


## అదనపు స్రోతసులు

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
 
### కోర్ లెర్నింగ్
[![ML for Beginners](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science for Beginners](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI for Beginners](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Cybersecurity for Beginners](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev for Beginners](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT for Beginners](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development for Beginners](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### కోపిలెట్ సిరీస్
[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## సహాయం పొందడం

మీరు ఏదైనా ఇబ్బంది పడితే లేదా AI యాప్స్ నిర్మాణం గురించి ఏవైనా ప్రశ్నలు ఉంటే, చేరుకోండి:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

ఉత్పత్తి అభిప్రాయం లేదా నిర్మాణంలో లోపాలు ఉంటే సందర్శించండి:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## లైసెన్స్

MIT లైసెన్స్ - వివరాల కోసం [LICENSE](../../LICENSE) ఫైలు చూడండి.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**అసత్య నిషేధం**:  
ఈ దస్తావేజ్‌ను AI అనువాద సేవ [Co-op Translator](https://github.com/Azure/co-op-translator) ఉపయోగించి అనువదించబడింది. మనం ఖచ్చితత్వం కోసం ప్రయత్నించినప్పటికీ, ఆటోమేటెడ్ అనువాదాల్లో పొరపాట్లు లేదా అపారదృశ్యాలు ఉండవచ్చు. స్వదేశీ భాషలోని మౌలిక దస్తావేజ్‌ను అధికారిక మూలంగా పరిగణించాలి. కీలక సమాచారం కోసం నిపుణుల మానవ అనువాదం సూచించబడుతుంది. ఈ అనువాదం వాడకం వల్ల వచ్చిన ఏవైనా అవగాహనలోపాలు లేదా తప్పుదోవలను మేము బాధ్యత స్వీకరించము.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->