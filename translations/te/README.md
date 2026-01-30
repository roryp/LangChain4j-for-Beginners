<img src="../../translated_images/te/LangChain4j.90e1d693fcc71b50.webp" alt="LangChain4j" width="800"/>

### 🌐 బహుభాషా మద్దతు

#### GitHub చర్య ద్వారా మద్దతు (ఆటోమేటెడ్ & ఎప్పుడూ నవీకరించబడును)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arabic](../ar/README.md) | [Bengali](../bn/README.md) | [Bulgarian](../bg/README.md) | [Burmese (Myanmar)](../my/README.md) | [Chinese (Simplified)](../zh-CN/README.md) | [Chinese (Traditional, Hong Kong)](../zh-HK/README.md) | [Chinese (Traditional, Macau)](../zh-MO/README.md) | [Chinese (Traditional, Taiwan)](../zh-TW/README.md) | [Croatian](../hr/README.md) | [Czech](../cs/README.md) | [Danish](../da/README.md) | [Dutch](../nl/README.md) | [Estonian](../et/README.md) | [Finnish](../fi/README.md) | [French](../fr/README.md) | [German](../de/README.md) | [Greek](../el/README.md) | [Hebrew](../he/README.md) | [Hindi](../hi/README.md) | [Hungarian](../hu/README.md) | [Indonesian](../id/README.md) | [Italian](../it/README.md) | [Japanese](../ja/README.md) | [Kannada](../kn/README.md) | [Korean](../ko/README.md) | [Lithuanian](../lt/README.md) | [Malay](../ms/README.md) | [Malayalam](../ml/README.md) | [Marathi](../mr/README.md) | [Nepali](../ne/README.md) | [Nigerian Pidgin](../pcm/README.md) | [Norwegian](../no/README.md) | [Persian (Farsi)](../fa/README.md) | [Polish](../pl/README.md) | [Portuguese (Brazil)](../pt-BR/README.md) | [Portuguese (Portugal)](../pt-PT/README.md) | [Punjabi (Gurmukhi)](../pa/README.md) | [Romanian](../ro/README.md) | [Russian](../ru/README.md) | [Serbian (Cyrillic)](../sr/README.md) | [Slovak](../sk/README.md) | [Slovenian](../sl/README.md) | [Spanish](../es/README.md) | [Swahili](../sw/README.md) | [Swedish](../sv/README.md) | [Tagalog (Filipino)](../tl/README.md) | [Tamil](../ta/README.md) | [Telugu](./README.md) | [Thai](../th/README.md) | [Turkish](../tr/README.md) | [Ukrainian](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnamese](../vi/README.md)

> **స్థానికంగా క్లోన్ చేయాలనుకుంటున్నారా?**

> ఈ రిపోజిటరీలో 50+ భాషా అనువాదాలు ఉన్నాయి, అవి డౌన్లోడ్ పరిమాణాన్ని గణనీయంగా పెంచుతాయి. అనువాదాలు లేకుండా క్లోన్ చేయడానికి, స్పార్స్ చెకౌట్ ఉపయోగించండి:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> ఇది కోర్సును పూర్తి చేయడానికి అవసరమైన ప్రతిదాన్ని మీకు ఇస్తుంది మరియు మరింత వేగవంతమైన డౌన్లోడ్ ఉంటుంది.
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j ప్రారంభికులకు

LangChain4j మరియు Azure OpenAI GPT-5 తో AI అప్లికేషన్లను డిజైన్ చేయడానికి కోర్సు, ప్రాథమిక చాట్ నుండి AI ఏజెంట్స్ వరకు.

**LangChain4j కొత్తవై ఉంటే?** కీలక పదాలు మరియు భావాలను నిర్వచించడానికి [Glossary](docs/GLOSSARY.md) చూడండి.

## విషయసూచీ

1. [త్వరిత ప్రారంభం](00-quick-start/README.md) - LangChain4j తో ప్రారంభించండి
2. [పరిచయం](01-introduction/README.md) - LangChain4j యొక్క మూలాలను తెలుసుకోండి
3. [ప్రాంప్ట్ ఇంజనీరింగ్](02-prompt-engineering/README.md) - సమర్థవంతమైన ప్రాంప్ట్ రూపకల్పన నేర్చుకోండి
4. [RAG (రెట్రీవల్-ఆగ్మెంటెడ్ జనరేషన్)](03-rag/README.md) - తెలివైన జ్ఞాన ఆధారిత వ్యవస్థలను రూపొందించండి
5. [సాధనాలు](04-tools/README.md) - బయటి సాధనాలు మరియు సాదాసీదా సాయాలను అనుసంధానం చేయండి
6. [MCP (మోడల్ కాంటెక్స్ట్ ప్రోటోకాల్)](05-mcp/README.md) - మోడల్ కాంటెక్స్ట్ ప్రోటోకాల్ (MCP) మరియు ఏజెంటిక్ మాడ్యూల్స్ తో పని చేయండి
---

##  అభ్యసన మార్గం

> **త్వరిత ప్రారంభం**

1. ఈ రిపోజిటరీని మీ GitHub ఖాతాకు Fork చేయండి
2. **Code** → **Codespaces** ట్యాబ్ → **...** → **New with options...** క్లిక్ చేయండి
3. డిఫాల్ట్స్ ఉపయోగించండి – ఇది ఈ కోర్సు కోసం సృష్టించిన డెవలప్‌మెంట్ కాన్టైనర్‌ను ఎంచుకుంటుంది
4. **Create codespace** క్లిక్ చేయండి
5. పరిసరాల సిద్ధం అయ్యే వరకు 5-10 నిమిషాలు ఎదురు చూడండి
6. ప్రారంభించడానికి నేరుగా [త్వరిత ప్రారంభం](./00-quick-start/README.md) కి వెళ్లండి!

మాడ్యూల్స్ పూర్తి చేసిన తర్వాత, LangChain4j టెస్టింగ్ కాన్సెప్ట్లను ప్రాక్టీస్ చేయడానికి [Testing Guide](docs/TESTING.md) ను అన్వేషించండి.

> **గమనిక:** ఈ శిక్షణ GitHub మోడల్స్ మరియు Azure OpenAI రెండింటిని ఉపయోగిస్తుంది. [త్వరిత ప్రారంభం](00-quick-start/README.md) మాడ్యూల్ GitHub మోడల్స్ ను ఉపయోగిస్తుంది (Azure సబ్స్క్రిప్షన్ అవసరం లేదు), అయితే 1-5 మాడ్యూల్స్ Azure OpenAI ఉపయోగిస్తాయి.


## GitHub Copilot తో నేర్చుకునేవిధానం

త్వరగా కోడింగ్ ప్రారంభించడానికి, ఈ ప్రాజెక్టును GitHub Codespace లో లేదా మీ స్థానిక IDE లో ఇచ్చిన devcontainer తో ఓపెన్ చేయండి. ఈ కోర్సులో ఉపయోగిస్తున్న devcontainer GitHub Copilot తో ముందుగానే కాన్ఫిగర్ చేయబడింది, ఇది AI జోడింపుతో కూడిన ప్రోగ్రామింగ్ సులభతరం చేస్తుంది.

ప్రతి కోడ్ ఉదాహరణ GitHub Copilot ను అడగడానికి సూచించిన ప్రశ్నలను కలిగి ఉంది, ఇవి మీ అర్థాన్ని విస్తరించేందుకు ఉపయోగపడతాయి. 💡/🤖 సూచనలు కోసం देखें:

- **Java ఫైల్ హెడ్డర్లు** - ప్రతి ఉదాహరణకి ప్రత్యేకమైన ప్రశ్నలు
- **మాడ్యూల్ READMEలు** - కోడ్ ఉదాహరణల తర్వాత అన్వేషణ కోసం సూచనలు

**వినియోగించటానికి:** ఎటువంటి కోడ్ ఫైల్ ను తెరిచి Copilot కు సూచించిన ప్రశ్నలు అడగండి. ఇది కోడ్‌బేస్ యొక్క పూర్తి_Context కలిగి ఉంది మరియు వివరణ, విస్తరణ మరియు ప్రత్యామ్నాయాలను సూచించగలదు.

మరింత తెలుసుకోవాలనుకుంటున్నారా? [AI జంట ప్రోగ్రామింగ్ కోసం Copilot](https://aka.ms/GitHubCopilotAI) చూడండి.


## అదనపు వనరులు

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j for Beginners](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js for Beginners](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / ఎడ్జ్ / MCP / ఏజెంట్స్
[![AZD for Beginners](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI for Beginners](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP for Beginners](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Agents for Beginners](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### జనరేటివ్ AI సిరీస్
[![Generative AI for Beginners](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generative AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generative AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generative AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### మూల సిద్దాంతాలు
[![ML for Beginners](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science for Beginners](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI for Beginners](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Cybersecurity for Beginners](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev for Beginners](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT for Beginners](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development for Beginners](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot సిరీస్
[![AI జతకట్టి ప్రోగ్రామింగ్ కోసం Copilot](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![C#/.NET కోసం Copilot](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot సాహసాన్ని](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## సహాయం పొందడం

మీరు సడలిపోయితే లేదా AI యాప్‌ల నిర్మాణం గురించి ఏవైనా ప్రశ్నలు ఉంటే, చేరండి:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

మీకు ఉత్పత్తి అభిప్రాయం లేదా తయారీ సమయంలో తప్పిదాలు ఉంటే సందర్శించండి:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## లైసెన్స్

MIT లైసెన్స్ - వివరాలకు [LICENSE](../../LICENSE) ఫైల్‌ను చూడండి.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**వ్యాఖ్యానము**:  
ఈ దస్తావేజు AI అనువాద సేవ [Co-op Translator](https://github.com/Azure/co-op-translator) ఉపయోగించి అనువదించబడింది. మేము సరైన అనువాదానికి కృషి చేస్తున్నప్పటికీ, ఆటోమేటెడ్ అనువాదాలలో లోపాలు లేదా అసత్యతలు ఉండవచ్చు. మూల భాషలో ఉన్న ఒరిజినల్ దస్తావేజునే అధికారిక మూలంగా పరిగణించాలి. కీలక సమాచారానికి, ప్రొఫెషనల్ మానవ అనువాదం సూచించబడుతుంది. ఈ అనువాదం వలన వచ్చే ఏవైనా తప్పుచూసుకోవడములు లేదా తప్పుదిద్దలేని అర్ధాలు ఎదురవుతున్న దానికి మేము బాధ్యత వహించము.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->