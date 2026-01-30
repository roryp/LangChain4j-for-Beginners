<img src="../../translated_images/ta/LangChain4j.90e1d693fcc71b50.webp" alt="LangChain4j" width="800"/>

### 🌐 பல்மொழி ஆதரவு

#### GitHub செயல்பாட்டின் மூலம் ஆதரிக்கப்பட்டது (தானாகவும் எப்போதும் புதுப்பிக்கப்படும்)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arabic](../ar/README.md) | [Bengali](../bn/README.md) | [Bulgarian](../bg/README.md) | [Burmese (Myanmar)](../my/README.md) | [Chinese (Simplified)](../zh-CN/README.md) | [Chinese (Traditional, Hong Kong)](../zh-HK/README.md) | [Chinese (Traditional, Macau)](../zh-MO/README.md) | [Chinese (Traditional, Taiwan)](../zh-TW/README.md) | [Croatian](../hr/README.md) | [Czech](../cs/README.md) | [Danish](../da/README.md) | [Dutch](../nl/README.md) | [Estonian](../et/README.md) | [Finnish](../fi/README.md) | [French](../fr/README.md) | [German](../de/README.md) | [Greek](../el/README.md) | [Hebrew](../he/README.md) | [Hindi](../hi/README.md) | [Hungarian](../hu/README.md) | [Indonesian](../id/README.md) | [Italian](../it/README.md) | [Japanese](../ja/README.md) | [Kannada](../kn/README.md) | [Korean](../ko/README.md) | [Lithuanian](../lt/README.md) | [Malay](../ms/README.md) | [Malayalam](../ml/README.md) | [Marathi](../mr/README.md) | [Nepali](../ne/README.md) | [Nigerian Pidgin](../pcm/README.md) | [Norwegian](../no/README.md) | [Persian (Farsi)](../fa/README.md) | [Polish](../pl/README.md) | [Portuguese (Brazil)](../pt-BR/README.md) | [Portuguese (Portugal)](../pt-PT/README.md) | [Punjabi (Gurmukhi)](../pa/README.md) | [Romanian](../ro/README.md) | [Russian](../ru/README.md) | [Serbian (Cyrillic)](../sr/README.md) | [Slovak](../sk/README.md) | [Slovenian](../sl/README.md) | [Spanish](../es/README.md) | [Swahili](../sw/README.md) | [Swedish](../sv/README.md) | [Tagalog (Filipino)](../tl/README.md) | [Tamil](./README.md) | [Telugu](../te/README.md) | [Thai](../th/README.md) | [Turkish](../tr/README.md) | [Ukrainian](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnamese](../vi/README.md)

> **உள்ளூர் க்ளோன் செய்ய விரும்புகிறீர்களா?**

> இந்த உள்கட்டமைப்பு 50+ மொழி மொழிபெயர்ப்புகளை உள்ளடக்குகிறது, இது பதிவிறக்கம் அளவை பெரிதும் அதிகரிக்கிறது. மொழிபெயர்ப்புகளின்றி க்ளோன் செய்ய, sparse checkout ஐ பயன்படுத்தவும்:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> இதன்மூலம் பாடநெறியை முடிக்க உங்களுக்கு தேவையான எல்லாவற்றையும் மிக வேகமாக பதிவிறக்கம் செய்யலாம்.
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# ஆரம்பக்கற்காரர்களுக்கான LangChain4j

LangChain4j மற்றும் Azure OpenAI GPT-5 உடன் AI பயன்பாடுகளை உருவாக்க ஒரு பாடநெறி, அடிப்படை உரையாடலிலிருந்து AI முகவரிகள் வரை.

**LangChain4j புதியவரா?** முக்கிய வார்த்தைகள் மற்றும் கருத்துக்களின் விளக்கங்களுக்கு [உரையாடல் அகராதியை](docs/GLOSSARY.md) காணவும்.

## зміст

1. [விரைவில் தொடங்கு](00-quick-start/README.md) - LangChain4j உடன் தொடங்குங்கள்
2. [அறிமுகம்](01-introduction/README.md) - LangChain4j அடிப்படைகளை அறியவும்
3. [பிரேரணை வடிவமைப்பு](02-prompt-engineering/README.md) - பயனுள்ள பிரேரணை வடிவமைப்பை கற்றுக்கொள்ளவும்
4. [RAG (திருப்பம்-அழிக்கப்பட்ட துவாரம்)](03-rag/README.md) - நுண்ணறிவு அறிவுத்தளம் அமைக்கவும்
5. [கருவிகள்](04-tools/README.md) - வெளி கருவிகள் மற்றும் எளிய உதவியாளர்களை ஒருங்கிணைக்கவும்
6. [MCP (மாதிரி சூழல்றிதல் நடைமுறை)](05-mcp/README.md) - மாதிரி சூழல்றிதல் (Model Context Protocol) மற்றும் முகவரிகளில் பணியாற்றவும்
---

## கற்றல் பாதை

> **விரைவில் தொடங்கு**

1. இந்த உள்கட்டமைப்பை உங்கள் GitHub கணக்கிற்கு Fork செய்யவும்
2. **Code** → **Codespaces** தாவலை கிளிக் செய்யவும் → **...** → **புதிய தேர்வுகளுடன்...** என்பதை தேர்ந்தெடுக்கவும்
3. இயல்புகளை பயன்படுத்தவும் – இது இந்த பாடநெறிக்கு உருவாக்கப்பட்ட அபிவிருத்தி контейнерைத் தேர்வுசெய்கிறது
4. **Codespace உருவாக்கு** ஐ கிளிக் செய்யவும்
5. சூழல் தயார் ஆக 5-10 நிமிடங்கள் காத்திருக்கவும்
6. தொடங்க [விரைவில் தொடங்கு](./00-quick-start/README.md) சென்று நேரடியாக தொடங்கவும்!

பாட தொகுதியை முடித்த பிறகு, LangChain4j சோதனை கருத்துக்களை பார்க்க [சோதனை வழிகாட்டியை](docs/TESTING.md) ஆராயவும்.

> **குறிப்பு:** இந்த பயிற்சி GitHub மாதிரிகளையும் Azure OpenAI-யையும் இரண்டையும் பயன்படுத்துகிறது. [விரைவு தொடக்கம்](00-quick-start/README.md) GitHub மாதிரிகளைப் பயன்படுத்துகிறது (Azure சந்தா தேவையில்லை), ஆனால் பாட தொகுதி 1-5 Azure OpenAI-யை பயன்படுத்துகிறது.


## GitHub Copilot உடன் கற்றல்

விரைவில் குறியீடு எழுத தொடங்க, இந்த திட்டத்தை GitHub Codespace-ல் அல்லது உள்ளூர் IDE-யில் devcontainer உடன் திறக்கவும். இந்த பாடநெறியில் பயன்படுத்தப்படும் devcontainer GitHub Copilot உடன் முன்னர் கட்டமைக்கப்பட்டுள்ளது, இது AI இணைக்கப்பட்ட நிரல் எழுத்துக்கானது.

ஒவ்வொரு குறியீட்டு உதாரணத்திலும் GitHub Copilot ஐ கேட்கக்கூடிய பரிந்துரைக்கப்பட்ட கேள்விகள் உள்ளன, இதனுடன் உங்கள் புரிதலை மேம்படுத்தலாம். கீழ்க்காணும் இடங்களில் 💡/🤖 அங்கீகாரம் உள்ள கேள்விகளை தேடவும்:

- **ஜாவா கோப்பு தலைப்புகள்** - ஒவ்வொரு உதாரணத்திற்கு தனிப்பட்ட கேள்விகள்
- **பாட தொகுப்புகளின் READMEகள்** - குறியீட்டுக்குப் பிறகு ஆராய்ச்சி கேள்விகள்

**எப்படி பயன்படுத்துவது:** எந்த குறியீடு கோப்பையும் திறந்து பரிந்துரைக்கப்பட்ட கேள்விகளை Copilot இக்கு கேளுங்கள். இது குறியீட்டு அடிப்படையை முழுமையாகப் புரிந்து கொண்டு விளக்கவும், விரிவாக்கவும், மாற்ற முறைகளைக் கூறவும் முடியும்.

மேலும் கற்றுக்கொள்ள விரும்புகிறீர்களா? [AI இணைக்கப்பட்ட நிரல் எழுத்திற்கான Copilot](https://aka.ms/GitHubCopilotAI) ஐ காணவும்.


## கூடுதல் வளங்கள்

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j for Beginners](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js for Beginners](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / முகவர்கள்
[![AZD for Beginners](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI for Beginners](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP for Beginners](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Agents for Beginners](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### உருவாக்கும் AI தொடர்
[![Generative AI for Beginners](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generative AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generative AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generative AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### கோர் கற்றல்
[![ML for Beginners](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science for Beginners](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI for Beginners](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Cybersecurity for Beginners](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev for Beginners](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT for Beginners](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development for Beginners](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### கோபைலட் தொடர்
[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## உதவி பெறுதல்

AI செயலிகளை உருவாக்கும்போது நீங்கள் சிக்கல் அடைவீர்களோ அல்லது ஏதேனும் கேள்விகள் வந்தால், சேரவும்:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

உற்பத்தி கருத்துக்களோ அல்லது பிழைகள் பற்றிப் பேச விரும்பினால் பார்வையிடுங்கள்:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## உரிமம்

MIT உரிமம் - விவரங்களுக்கு [LICENSE](../../LICENSE) கோப்பை பார்வையிடுங்கள்.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**குறிப்பு**:  
இந்தக் கோப்புச் செய்தி AI மொழிபெயர்ப்பு சேவை [Co-op Translator](https://github.com/Azure/co-op-translator) மூலம் மொழிமாற்றம் செய்யப்பட்டுள்ளதாம். நாம் துல்லியத்திற்காக முயலினாலும், தானாக செய்யப்படும் மொழிபெயர்ப்புகளில் பிழைகள் அல்லது தவறுகள் இருக்கலாம் என்பதை கவனத்தில் கொள்ளவும். இயல்புநிலையான மொழியில் உள்ள முதன்மை ஆவணம் தான் அதிகாரபூர்வமான மூலமாக கருதப்பட வேண்டும். மிகவும் முக்கியமான தகவல்களுக்கு, தொழில்முறை மனித மொழிபெயர்ப்பை பரிந்துரைக்கிறோம். இந்த மொழிபெயர்ப்பினால் ஏற்படக்கூடிய புரிதல் தவறுகள் அல்லது தவறான அர்த்தமூட்டல்கள் குறித்து எங்களுக்கு பொறுப்பு இல்லை.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->