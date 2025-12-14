<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "69c7e2616c66df6cc296492fbfcad9ec",
  "translation_date": "2025-12-13T12:55:47+00:00",
  "source_file": "README.md",
  "language_code": "ml"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b506e9588f734989dd106ebd9f977b7f784941a28b88348f0d6.ml.png" alt="LangChain4j" width="800"/>

# LangChain4j ആരംഭിക്കുന്നവർക്കായി

LangChain4j ഉം Azure OpenAI GPT-5 ഉം ഉപയോഗിച്ച് അടിസ്ഥാന ചാറ്റിൽ നിന്ന് AI ഏജന്റുകളിലേക്കുള്ള AI ആപ്ലിക്കേഷനുകൾ നിർമ്മിക്കുന്ന കോഴ്‌സ്.

**LangChain4j-യിൽ പുതിയവരാണോ?** പ്രധാന പദങ്ങളും ആശയങ്ങളും നിർവചിക്കുന്നതിന് [Glossary](docs/GLOSSARY.md) കാണുക.

## ഉള്ളടക്ക പട്ടിക

1. [Quick Start](00-quick-start/README.md) - LangChain4j ഉപയോഗിച്ച് ആരംഭിക്കുക
2. [Introduction](01-introduction/README.md) - LangChain4j-യുടെ അടിസ്ഥാനങ്ങൾ പഠിക്കുക
3. [Prompt Engineering](02-prompt-engineering/README.md) - ഫലപ്രദമായ പ്രോംപ്റ്റ് രൂപകൽപ്പനയിൽ നിപുണത നേടുക
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - ബുദ്ധിമുട്ടുള്ള അറിവ് അടിസ്ഥാനമാക്കിയുള്ള സിസ്റ്റങ്ങൾ നിർമ്മിക്കുക
5. [Tools](04-tools/README.md) - AI ഏജന്റുകളുമായി ബാഹ്യ ഉപകരണങ്ങളും API-കളും സംയോജിപ്പിക്കുക
6. [MCP (Model Context Protocol)](05-mcp/README.md) - മോഡൽ കോൺടെക്സ്റ്റ് പ്രോട്ടോക്കോളുമായി പ്രവർത്തിക്കുക
---

## പഠന പാത

[Quick Start](00-quick-start/README.md) മോഡ്യൂളിൽ നിന്ന് ആരംഭിച്ച് ഓരോ മോഡ്യൂളും ക്രമമായി പഠിച്ച് നിങ്ങളുടെ കഴിവുകൾ ഘട്ടം ഘട്ടമായി വികസിപ്പിക്കുക. അടിസ്ഥാനങ്ങൾ മനസ്സിലാക്കാൻ ലളിതമായ ഉദാഹരണങ്ങൾ പരീക്ഷിച്ച് ശേഷം GPT-5 ഉപയോഗിച്ച് കൂടുതൽ ആഴത്തിലുള്ള പഠനത്തിനായി [Introduction](01-introduction/README.md) മോഡ്യൂളിലേക്ക് നീങ്ങുക.

<img src="../../translated_images/learning-path.ac2da6720e77c3165960835627cef4c20eb2afb103be73a4f25b6d8fafbd738d.ml.png" alt="Learning Path" width="800"/>

മോഡ്യൂളുകൾ പൂർത്തിയാക്കിയ ശേഷം LangChain4j ടെസ്റ്റിംഗ് ആശയങ്ങൾ പ്രയോഗത്തിൽ കാണാൻ [Testing Guide](docs/TESTING.md) പരിശോധിക്കുക.

> **കുറിപ്പ്:** ഈ പരിശീലനം GitHub മോഡലുകളും Azure OpenAI-യും രണ്ടും ഉപയോഗിക്കുന്നു. [Quick Start](00-quick-start/README.md) ഉം [MCP](05-mcp/README.md) ഉം GitHub മോഡലുകൾ ഉപയോഗിക്കുന്നു (Azure സബ്സ്ക്രിപ്ഷൻ ആവശ്യമില്ല), എന്നാൽ 1-4 മോഡ്യൂളുകൾ Azure OpenAI GPT-5 ഉപയോഗിക്കുന്നു.


## GitHub Copilot-ഉം കൂടെ പഠനം

വേഗത്തിൽ കോഡിംഗ് ആരംഭിക്കാൻ, ഈ പ്രോജക്ട് GitHub Codespace-ൽ അല്ലെങ്കിൽ നൽകിയ devcontainer ഉപയോഗിച്ച് നിങ്ങളുടെ ലോക്കൽ IDE-യിൽ തുറക്കുക. ഈ കോഴ്‌സിൽ ഉപയോഗിക്കുന്ന devcontainer GitHub Copilot AI കൂട്ടായ്മ പ്രോഗ്രാമിംഗിനായി മുൻകൂട്ടി ക്രമീകരിച്ചിരിക്കുന്നു.

ഓരോ കോഡ് ഉദാഹരണത്തിലും GitHub Copilot-നോട് ചോദിക്കാവുന്ന നിർദ്ദേശിച്ച ചോദ്യങ്ങൾ ഉൾപ്പെടുത്തിയിട്ടുണ്ട്, ഇത് നിങ്ങളുടെ മനസ്സിലാക്കൽ കൂടുതൽ ആഴത്തിൽ ആക്കാൻ സഹായിക്കും. 💡/🤖 പ്രോംപ്റ്റുകൾ താഴെ കാണാം:

- **Java ഫയൽ ഹെഡറുകൾ** - ഓരോ ഉദാഹരണത്തിനും പ്രത്യേക ചോദ്യങ്ങൾ
- **മോഡ്യൂൾ README-കൾ** - കോഡ് ഉദാഹരണങ്ങൾക്കുശേഷം അന്വേഷിക്കുന്ന പ്രോംപ്റ്റുകൾ

**ഉപയോഗിക്കുന്ന വിധം:** ഏതെങ്കിലും കോഡ് ഫയൽ തുറന്ന് Copilot-നോട് നിർദ്ദേശിച്ച ചോദ്യങ്ങൾ ചോദിക്കുക. കോഡ് ബേസ് പൂർണ്ണമായും മനസ്സിലാക്കുന്ന Copilot വിശദീകരിക്കാനും, വിപുലീകരിക്കാനും, പകരം നിർദ്ദേശിക്കാനും കഴിയും.

കൂടുതൽ അറിയാൻ [Copilot for AI Paired Programming](https://aka.ms/GitHubCopilotAI) കാണുക.


## അധിക സ്രോതസ്സുകൾ

### LangChain
[![LangChain4j for Beginners](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js for Beginners](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / ഏജന്റുകൾ
[![AZD for Beginners](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI for Beginners](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP for Beginners](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Agents for Beginners](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### ജനറേറ്റീവ് AI സീരീസ്
[![Generative AI for Beginners](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generative AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generative AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generative AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### കോർ പഠനം
[![ML for Beginners](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science for Beginners](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI for Beginners](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Cybersecurity for Beginners](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev for Beginners](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT for Beginners](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development for Beginners](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot സീരീസ്
[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)

## സഹായം നേടുക

AI ആപ്ലിക്കേഷനുകൾ നിർമ്മിക്കുന്നതിൽ നിങ്ങൾക്ക് തടസ്സം നേരിടുകയോ ചോദ്യങ്ങളുണ്ടായിരിക്കുകയോ ചെയ്താൽ, ചേരുക:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

ഉൽപ്പന്ന പ്രതികരണങ്ങൾക്കോ പിഴവുകൾക്കോ വേണ്ടി സന്ദർശിക്കുക:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## ലൈസൻസ്

MIT ലൈസൻസ് - വിശദാംശങ്ങൾക്ക് [LICENSE](../../LICENSE) ഫയൽ കാണുക.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**അസൂയാ**:  
ഈ രേഖ AI വിവർത്തന സേവനം [Co-op Translator](https://github.com/Azure/co-op-translator) ഉപയോഗിച്ച് വിവർത്തനം ചെയ്തതാണ്. നാം കൃത്യതയ്ക്ക് ശ്രമിച്ചെങ്കിലും, സ്വയം പ്രവർത്തിക്കുന്ന വിവർത്തനങ്ങളിൽ പിശകുകൾ അല്ലെങ്കിൽ തെറ്റുകൾ ഉണ്ടാകാമെന്ന് ദയവായി ശ്രദ്ധിക്കുക. അതിന്റെ മാതൃഭാഷയിലുള്ള യഥാർത്ഥ രേഖ അധികാരപരമായ ഉറവിടമായി കണക്കാക്കപ്പെടണം. നിർണായക വിവരങ്ങൾക്ക്, പ്രൊഫഷണൽ മനുഷ്യ വിവർത്തനം ശുപാർശ ചെയ്യപ്പെടുന്നു. ഈ വിവർത്തനത്തിന്റെ ഉപയോഗത്തിൽ നിന്നുണ്ടാകുന്ന ഏതെങ്കിലും തെറ്റിദ്ധാരണകൾക്കോ തെറ്റായ വ്യാഖ്യാനങ്ങൾക്കോ ഞങ്ങൾ ഉത്തരവാദികളല്ല.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->