<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "d1499d1abf7e653e94fde35644629500",
  "translation_date": "2025-12-18T10:29:36+00:00",
  "source_file": "README.md",
  "language_code": "ta"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b506e9588f734989dd106ebd9f977b7f784941a28b88348f0d6.ta.png" alt="LangChain4j" width="800"/>

### 🌐 பன்மொழி ஆதரவு

#### GitHub செயல்பாட்டின் மூலம் ஆதரிக்கப்படுகிறது (தானாகவும் எப்போதும் புதுப்பிக்கப்படும்)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arabic](../ar/README.md) | [Bengali](../bn/README.md) | [Bulgarian](../bg/README.md) | [Burmese (Myanmar)](../my/README.md) | [Chinese (Simplified)](../zh/README.md) | [Chinese (Traditional, Hong Kong)](../hk/README.md) | [Chinese (Traditional, Macau)](../mo/README.md) | [Chinese (Traditional, Taiwan)](../tw/README.md) | [Croatian](../hr/README.md) | [Czech](../cs/README.md) | [Danish](../da/README.md) | [Dutch](../nl/README.md) | [Estonian](../et/README.md) | [Finnish](../fi/README.md) | [French](../fr/README.md) | [German](../de/README.md) | [Greek](../el/README.md) | [Hebrew](../he/README.md) | [Hindi](../hi/README.md) | [Hungarian](../hu/README.md) | [Indonesian](../id/README.md) | [Italian](../it/README.md) | [Japanese](../ja/README.md) | [Kannada](../kn/README.md) | [Korean](../ko/README.md) | [Lithuanian](../lt/README.md) | [Malay](../ms/README.md) | [Malayalam](../ml/README.md) | [Marathi](../mr/README.md) | [Nepali](../ne/README.md) | [Nigerian Pidgin](../pcm/README.md) | [Norwegian](../no/README.md) | [Persian (Farsi)](../fa/README.md) | [Polish](../pl/README.md) | [Portuguese (Brazil)](../br/README.md) | [Portuguese (Portugal)](../pt/README.md) | [Punjabi (Gurmukhi)](../pa/README.md) | [Romanian](../ro/README.md) | [Russian](../ru/README.md) | [Serbian (Cyrillic)](../sr/README.md) | [Slovak](../sk/README.md) | [Slovenian](../sl/README.md) | [Spanish](../es/README.md) | [Swahili](../sw/README.md) | [Swedish](../sv/README.md) | [Tagalog (Filipino)](../tl/README.md) | [Tamil](./README.md) | [Telugu](../te/README.md) | [Thai](../th/README.md) | [Turkish](../tr/README.md) | [Ukrainian](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnamese](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j தொடக்கத்திற்கானது

LangChain4j மற்றும் Azure OpenAI GPT-5 உடன் AI பயன்பாடுகளை உருவாக்கும் பாடநெறி, அடிப்படையான உரையாடலிலிருந்து AI முகவர்களுக்குள்.

**LangChain4j-க்கு புதியவரா?** முக்கிய சொற்கள் மற்றும் கருத்துக்களின் வரையறைகளுக்கு [அகராதி](docs/GLOSSARY.md) ஐப் பாருங்கள்.

## உள்ளடக்க அட்டவணை

1. [விரைவு தொடக்கம்](00-quick-start/README.md) - LangChain4j உடன் துவங்குங்கள்
2. [அறிமுகம்](01-introduction/README.md) - LangChain4j அடிப்படைகளை கற்றுக்கொள்ளுங்கள்
3. [உரையாடல் வடிவமைப்பு](02-prompt-engineering/README.md) - பயனுள்ள உரையாடல் வடிவமைப்பை கற்றுக்கொள்ளுங்கள்
4. [RAG (திரும்ப பெறுதல்-வளர்ச்சி உருவாக்கல்)](03-rag/README.md) - அறிவார்ந்த அறிவு அடிப்படையிலான அமைப்புகளை உருவாக்குங்கள்
5. [கருவிகள்](04-tools/README.md) - வெளிப்புற கருவிகள் மற்றும் API-களை AI முகவர்களுடன் ஒருங்கிணைக்கவும்
6. [MCP (மாதிரி சூழல் நெறிமுறை)](05-mcp/README.md) - மாதிரி சூழல் நெறிமுறையுடன் பணியாற்றுங்கள்
---

## கற்றல் பாதை

> **விரைவு தொடக்கம்**

1. இந்த சேமிப்பகத்தை உங்கள் GitHub கணக்குக்கு Fork செய்யவும்
2. **Code** → **Codespaces** தாவலை கிளிக் செய்யவும் → **...** → **New with options...** என்பதைத் தேர்ந்தெடுக்கவும்
3. இயல்புநிலைகளை பயன்படுத்தவும் – இது இந்த பாடநெறிக்கான உருவாக்கப்பட்ட மேம்பாட்டு கொண்டெய்னரைத் தேர்ந்தெடுக்கும்
4. **Create codespace** ஐ கிளிக் செய்யவும்
5. சூழல் தயாராக 5-10 நிமிடங்கள் காத்திருக்கவும்
6. துவங்க [விரைவு தொடக்கம்](./00-quick-start/README.md) க்கு நேரடியாக செல்லவும்!

> **உள்ளூரில் கிளோன் செய்ய விரும்புகிறீர்களா?**
>
> இந்த சேமிப்பகம் 50+ மொழி மொழிபெயர்ப்புகளை கொண்டுள்ளது, இது பதிவிறக்க அளவை பெரிதாக அதிகரிக்கிறது. மொழிபெயர்ப்புகள் இல்லாமல் கிளோன் செய்ய sparse checkout பயன்படுத்தவும்:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> இது பாடநெறியை முடிக்க தேவையான அனைத்தையும் மிக வேகமான பதிவிறக்கத்துடன் வழங்கும்.

[விரைவு தொடக்கம்](00-quick-start/README.md) மாடியூலைத் தொடங்கி ஒவ்வொரு மாடியூலும் படிப்படியாக உங்கள் திறன்களை மேம்படுத்துங்கள். அடிப்படைகளை புரிந்துகொள்ள அடிப்படை எடுத்துக்காட்டுகளை முயற்சி செய்யவும், பின்னர் GPT-5 உடன் ஆழமான கற்றலுக்காக [அறிமுகம்](01-introduction/README.md) மாடியூலை நோக்கி செல்லவும்.

<img src="../../translated_images/learning-path.ac2da6720e77c3165960835627cef4c20eb2afb103be73a4f25b6d8fafbd738d.ta.png" alt="Learning Path" width="800"/>

மாடியூல்கள் முடிந்தவுடன், LangChain4j சோதனை கருத்துக்களை செயல்பாட்டில் காண [சோதனை வழிகாட்டி](docs/TESTING.md) ஐ ஆராயவும்.

> **குறிப்பு:** இந்த பயிற்சி GitHub மாதிரிகள் மற்றும் Azure OpenAI இரண்டையும் பயன்படுத்துகிறது. [விரைவு தொடக்கம்](00-quick-start/README.md) மற்றும் [MCP](05-mcp/README.md) மாடியூல்கள் GitHub மாதிரிகளைப் பயன்படுத்துகின்றன (Azure சந்தா தேவையில்லை), மாடியூல்கள் 1-4 Azure OpenAI GPT-5 ஐப் பயன்படுத்துகின்றன.


## GitHub Copilot உடன் கற்றல்

விரைவாக குறியீடு எழுத துவங்க, இந்த திட்டத்தை GitHub Codespace அல்லது உங்கள் உள்ளூர் IDE-யில் devcontainer உடன் திறக்கவும். இந்த பாடநெறியில் பயன்படுத்தப்படும் devcontainer GitHub Copilot உடன் முன்கூட்டியே அமைக்கப்பட்டுள்ளது, இது AI இணைந்த நிரலாக்கத்திற்கானது.

ஒவ்வொரு குறியீடு எடுத்துக்காட்டிலும் GitHub Copilot-ஐ கேட்கக்கூடிய பரிந்துரைக்கப்பட்ட கேள்விகள் உள்ளன, இது உங்கள் புரிதலை ஆழப்படுத்த உதவும். 💡/🤖 குறியீடுகள் பின்வருமாறு உள்ளன:

- **Java கோப்பு தலைப்புகள்** - ஒவ்வொரு எடுத்துக்காட்டிற்கும் தனிப்பட்ட கேள்விகள்
- **மாடியூல் READMEகள்** - குறியீடு எடுத்துக்காட்டுகளுக்குப் பிறகு ஆராய்ச்சி கேள்விகள்

**எப்படி பயன்படுத்துவது:** எந்த குறியீடு கோப்பையும் திறந்து Copilot-க்கு பரிந்துரைக்கப்பட்ட கேள்விகளை கேளுங்கள். இது குறியீடு அடிப்படையின் முழு சூழலைப் புரிந்து, விளக்கவும், விரிவாக்கவும், மாற்று பரிந்துரைகளையும் வழங்க முடியும்.

மேலும் கற்றுக்கொள்ள விரும்புகிறீர்களா? [AI இணைந்த நிரலாக்கத்திற்கான Copilot](https://aka.ms/GitHubCopilotAI) ஐப் பாருங்கள்.


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
 
### முக்கிய கற்றல்
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

நீங்கள் சிக்கிக்கொண்டால் அல்லது AI செயலிகளை உருவாக்குவதில் ஏதேனும் கேள்விகள் இருந்தால், சேரவும்:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

உற்பத்தி கருத்து அல்லது பிழைகள் இருந்தால், பார்வையிடவும்:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## உரிமம்

MIT உரிமம் - விவரங்களுக்கு [LICENSE](../../LICENSE) கோப்பை பார்க்கவும்.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**குறிப்பு**:  
இந்த ஆவணம் AI மொழிபெயர்ப்பு சேவை [Co-op Translator](https://github.com/Azure/co-op-translator) மூலம் மொழிபெயர்க்கப்பட்டுள்ளது. நாங்கள் துல்லியத்திற்காக முயற்சித்தாலும், தானியங்கி மொழிபெயர்ப்புகளில் பிழைகள் அல்லது தவறுகள் இருக்கக்கூடும் என்பதை தயவுசெய்து கவனிக்கவும். அசல் ஆவணம் அதன் சொந்த மொழியில் அதிகாரப்பூர்வ மூலமாக கருதப்பட வேண்டும். முக்கியமான தகவல்களுக்கு, தொழில்முறை மனித மொழிபெயர்ப்பு பரிந்துரைக்கப்படுகிறது. இந்த மொழிபெயர்ப்பின் பயன்பாட்டால் ஏற்படும் எந்த தவறான புரிதல்கள் அல்லது தவறான விளக்கங்களுக்கும் நாங்கள் பொறுப்பேற்கமாட்டோம்.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->