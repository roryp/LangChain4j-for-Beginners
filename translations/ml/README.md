<img src="../../translated_images/ml/LangChain4j.90e1d693fcc71b50.webp" alt="LangChain4j" width="800"/>

### 🌐 ബഹുഭാഷാ പിന്തുണ

#### GitHub ആക്ഷൻ വഴി പിന്തുണ (സ്വയം പ്രവർത്തിക്കുന്നതും എല്ലായ്പ്പോഴും പുതുക്കലുള്ളതും)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arabic](../ar/README.md) | [Bengali](../bn/README.md) | [Bulgarian](../bg/README.md) | [Burmese (Myanmar)](../my/README.md) | [Chinese (Simplified)](../zh-CN/README.md) | [Chinese (Traditional, Hong Kong)](../zh-HK/README.md) | [Chinese (Traditional, Macau)](../zh-MO/README.md) | [Chinese (Traditional, Taiwan)](../zh-TW/README.md) | [Croatian](../hr/README.md) | [Czech](../cs/README.md) | [Danish](../da/README.md) | [Dutch](../nl/README.md) | [Estonian](../et/README.md) | [Finnish](../fi/README.md) | [French](../fr/README.md) | [German](../de/README.md) | [Greek](../el/README.md) | [Hebrew](../he/README.md) | [Hindi](../hi/README.md) | [Hungarian](../hu/README.md) | [Indonesian](../id/README.md) | [Italian](../it/README.md) | [Japanese](../ja/README.md) | [Kannada](../kn/README.md) | [Korean](../ko/README.md) | [Lithuanian](../lt/README.md) | [Malay](../ms/README.md) | [Malayalam](./README.md) | [Marathi](../mr/README.md) | [Nepali](../ne/README.md) | [Nigerian Pidgin](../pcm/README.md) | [Norwegian](../no/README.md) | [Persian (Farsi)](../fa/README.md) | [Polish](../pl/README.md) | [Portuguese (Brazil)](../pt-BR/README.md) | [Portuguese (Portugal)](../pt-PT/README.md) | [Punjabi (Gurmukhi)](../pa/README.md) | [Romanian](../ro/README.md) | [Russian](../ru/README.md) | [Serbian (Cyrillic)](../sr/README.md) | [Slovak](../sk/README.md) | [Slovenian](../sl/README.md) | [Spanish](../es/README.md) | [Swahili](../sw/README.md) | [Swedish](../sv/README.md) | [Tagalog (Filipino)](../tl/README.md) | [Tamil](../ta/README.md) | [Telugu](../te/README.md) | [Thai](../th/README.md) | [Turkish](../tr/README.md) | [Ukrainian](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnamese](../vi/README.md)

> **പ്രാദേശികമായി ക്ലോൺ ചെയ്യണമെന്ന് ആഗ്രഹിക്കുമോ?**

> ഈ റിപോസിറ്ററിയിൽ 50-ൽ പരമാവധി ഭാഷാ വിവർത്തനങ്ങൾ ഉൾക്കൊള്ളുന്നു, ഇത് ഡൗൺലോഡ് വലുപ്പം ഗണ്യമായി വർദ്ധിപ്പിക്കുന്നു. വിവർത്തനങ്ങൾ ഇല്ലാതെ ക്ലോൺ ചെയ്യാൻ sparse checkout ഉപയോഗിക്കാം:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> ഇത് ഈ കോഴ്‌സ് പൂർത്തിയാക്കാൻ ആവശ്യമായ എല്ലാ ഫയലുകളും വളരെ വേഗത്തിൽ ഡൗൺലോഡ് ചെയ്യാൻ സഹായിക്കും.
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j തുടക്കക്കാർക്കായി

LangChain4j ഉം Azure OpenAI GPT-5 ഉം ഉപയോഗിച്ച് അടിസ്ഥാന ചാറ്റിൽ നിന്ന് AI ഏജന്റുകളിലേക്കുള്ള AI അപ്ലിക്കേഷനുകൾ നിർമ്മിക്കുന്ന കോഴ്‌സ്.

**LangChain4j നെ പുതിയതായി പഠിക്കുകയാണ്?** പ്രധാന പദങ്ങളും ആശയങ്ങളും എന്നതിനായുള്ള [ഗ്ലോസറി](docs/GLOSSARY.md) പരിശോധിക്കൂ.

## ഉള്ളടക്ക പട്ടിക

1. [ക്വിക്ക് സ്റ്റാർട്ട്](00-quick-start/README.md) - LangChain4j ഉപയോഗിച്ച് തുടക്കം കുറിക്കുക
2. [പരിചയം](01-introduction/README.md) - LangChain4j ന്റെ അടിസ്ഥാനങ്ങൾ പഠിക്കുക
3. [പ്രോംപ്റ്റ് എഞ്ചിനീയറിംഗ്](02-prompt-engineering/README.md) - ഫലപ്രദമായ പ്രോംപ്റ്റ് രൂപകൽപ്പനയിൽ പ്രാവീണ്യം നേടുക
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - ബുദ്ധിമുട്ടുള്ള അറിവ് അടിസ്ഥാനമാക്കി സിസ്റ്റങ്ങൾ നിർമ്മിക്കുക
5. [ഉപകരണങ്ങൾ](04-tools/README.md) - ബാഹ്യ ഉപകരണങ്ങളും ലളിതമായ സഹായികളും സംയോജിപ്പിക്കുക
6. [MCP (Model Context Protocol)](05-mcp/README.md) - മോഡൽ കോണ്ടക്‌സ് പ്രോട്ടോകോൾ (MCP) ഉം ഏജന്റിക് മുട്യൂളുകളും പ്രവർത്തിപ്പിക്കുക
---

## പഠന പാത

> **ക്വിക്ക് സ്റ്റാർട്ട്**

1. ഈ റിപോസിറ്ററി നിങ്ങളുടെ GitHub അക്കൗണ്ടിലേക്ക് ഫോർക്കു ചെയ്യുക
2. **Code** → **Codespaces** ടാബ് → **...** → **New with options...** ക്ലിക്കുചെയ്യുക
3. ഡിഫോൾട്ട് ക്രമീകരണങ്ങൾ ഉപയോഗിക്കുക – ഇതാണു ഈ കോഴ്‌സിനായി സൃഷ്ടിച്ച വികസന കണ്ടെയ്‌നർ തിരഞ്ഞെടുക്കുന്നത്
4. **Create codespace** ക്ലിക്കുചെയ്യുക
5. പരിസ്ഥിതി തയ്യാറാകാൻ 5-10 മിനിറ്റ് കാത്തിരിക്കുക
6. തുടക്കം കുറിക്കാൻ നേരിട്ട് [Quick Start](./00-quick-start/README.md) സന്ദർശിക്കൂ!

മൊഡ്യൂളുകൾ പൂർത്തിയാക്കിയ ശേഷം, LangChain4j ടെസ്റ്റിംഗ് ആശയങ്ങൾ പ്രവർത്തനരീതിയിൽ കാണാൻ [Testing Guide](docs/TESTING.md) പരിശോധിക്കുക.

> **കുറിപ്പ്:** ഈ പരിശീലനം GitHub മോഡലുകളും Azure OpenAI ഉം ഉപയോഗിക്കുന്നു. [Quick Start](00-quick-start/README.md) മൊഡ്യൂൾ GitHub മോഡലുകൾ ഉപയോഗിക്കുന്നു (Azure സബ്സ്ക്രിപ്ഷൻ ആവശ്യമില്ല), മോഡ്യൂളുകൾ 1-5 Azure OpenAI ഉപയോഗിക്കുന്നു.


## GitHub Copilot ഉപയോഗിച്ച് പഠനം

വേഗത്തിൽ കോഡിംഗം ആരംഭിക്കാൻ, ഈ പ്രോജക്ട് GitHub Codespace-ൽ അല്ലെങ്കിൽ നൽകപ്പെട്ട devcontainer ഉപയോഗിച്ച് ലocale IDE-യിൽ തുറക്കുക. ഈ കോഴ്‌സിൽ ഉപയോഗിക്കുന്ന devcontainer GitHub Copilot ആകെ കൂട്ടുകാരന്റെ ഭാഗമായാണ് മുൻകൂട്ടി ക്രമീകരിച്ചിരിക്കുന്നത്.

ഓരോ കോഡ് ഉദാഹരണത്തിലും GitHub Copilot-നോട് ചോദിക്കാവുന്ന നിർദേശിച്ച ചോദ്യങ്ങൾ ഉൾക്കൊള്ളുന്നു, ഇത് നിങ്ങളുടെ ബോധ്യത്തെ കൂടുതൽ ശക്തിപ്പെടുത്തും. ചുവടെയുള്ളിടങ്ങളിൽ 💡/🤖 പ്രോമ്പ്റ്റുകൾ ശ്രദ്ധിക്കുക:

- **Java ഫയൽ ഹെഡറുകൾ** - ഓരോ ഉദാഹരണത്തിന് പ്രത്യേകം ചോദ്യങ്ങൾ
- **മൊഡ്യൂൾ README-കൾ** - കോഡ് ഉദാഹരണങ്ങൾക്കുശേഷം അന്വേഷണം ലേഖനങ്ങൾ

**ഉപയോഗวิി​ഥി:** ഏതെങ്കിലും കോഡ് ഫയൽ തുറന്ന് Copilot-നോട് നിർദേശിച്ച ചോദ്യങ്ങൾ ചോദിക്കുക. ഇത് കോഡ് ബേസിന്റെ പൂര്‍ണ്ണ സാന്ദർഭ്യം അറിയുന്നു, വിശദീകരിക്കാൻ, വികസിപ്പിക്കാൻ, സഹ മാർഗങ്ങൾ നിർദ്ദേശിക്കാനും കഴിയുന്നു.

കൂടുതൽ അറിയണമെന്ന് ആഗ്രഹിക്കുന്നുണ്ടോ? [AI കൂട്ടുകാരൻ കോപ്പൈലുട്ടിനായി](https://aka.ms/GitHubCopilotAI) സന്ദർശിക്കുക.


## അധിക വనക്കങ്ങൾ

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
 
### കോർ പഠനത്ത്
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

## സഹായം നേടുക

നിങ്ങൾക്ക് തടസ്സം നേരിടുകയാണെങ്കിൽ അല്ലെങ്കിൽ AI ആപ്പുകൾ നിർമ്മിക്കുന്നതിന് സംശയങ്ങളുണ്ടെങ്കിൽ, ചേരുക:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

നിർമിക്കുമ്പോൾ ഉത്പാദന പ്രതികരണങ്ങൾ അല്ലെങ്കിൽ പിശകുകൾ ഉണ്ടായാൽ സന്ദർശിക്കുക:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## ലൈസൻസ്

MIT ലൈസൻസ് - വിശദാംശങ്ങൾക്കായി [LICENSE](../../LICENSE) ഫയൽ കാണുക.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**അസ്വീകാരപ്രഖ്യാപനം**:  
ഈ ഡോക്യുമെന്റ് AI തർജ്ജമ സേവനമായ [Co-op Translator](https://github.com/Azure/co-op-translator) ഉപയോഗിച്ച് തർജ്ജമ ചെയ്തിരിക്കുന്നു. കൃത്യത ഉറപ്പിക്കാൻ ഞങ്ങൾ ശ്രമിക്കുന്നുവെങ്കിലും, സ്വയം പ്രവർത്തിക്കുന്ന തർജ്ജമയിൽ പിശകുകൾ അല്ലെങ്കിൽ തെറ്റുകൾ ഉണ്ടായിരിക്കാൻ സാധ്യതയുണ്ട് എന്നതിനായി ശ്രദ്ധിക്കണം. മൗലിക ഭാഷയിലെ യഥാർത്ഥ ഡോക്യുമെന്റ് ആണ് പ്രാമാണികമായ ഉറവിടം. ഗൗരവമായ വിവരങ്ങൾക്കായി വിദഗ്ധ മനുഷ്യൻ്റെ തർജ്ജമ പരിഗണിക്കണമെന്നും നിർദേശം നൽകുന്നു. ഈ തർജ്മവിനുപയോഗിച്ച് ഉണ്ടാകാവുന്ന quaisquer തെറ്റിദ്ധാരണകൾക്കും വ്യാഖ്യാനപ്പെടലുകൾക്കും ഞങ്ങൾ ഉത്തരവാദികൾ അല്ല.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->