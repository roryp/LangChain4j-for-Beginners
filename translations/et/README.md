<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "69c7e2616c66df6cc296492fbfcad9ec",
  "translation_date": "2025-12-13T12:53:06+00:00",
  "source_file": "README.md",
  "language_code": "et"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b506e9588f734989dd106ebd9f977b7f784941a28b88348f0d6.et.png" alt="LangChain4j" width="800"/>

# LangChain4j algajatele

Kursus AI-rakenduste loomiseks LangChain4j ja Azure OpenAI GPT-5 abil, alates lihtsast vestlusest kuni AI-agentideni.

**Uus LangChain4j-s?** Vaadake [Sõnastikku](docs/GLOSSARY.md), et saada ülevaade võtmeterminitest ja kontseptsioonidest.

## Sisukord

1. [Kiire algus](00-quick-start/README.md) - Alustage LangChain4j-ga
2. [Sissejuhatus](01-introduction/README.md) - Õppige LangChain4j põhialuseid
3. [Promptide loomine](02-prompt-engineering/README.md) - Valmistage ette tõhusaid prompt'e
4. [RAG (otsingupõhine genereerimine)](03-rag/README.md) - Looge nutikaid teadmistepõhiseid süsteeme
5. [Tööriistad](04-tools/README.md) - Integreerige väliseid tööriistu ja API-sid AI-agentidega
6. [MCP (mudeli konteksti protokoll)](05-mcp/README.md) - Töötage mudeli konteksti protokolliga
---

## Õppeteekond

Alustage [Kiire algus](00-quick-start/README.md) moodulist ja liikuge samm-sammult läbi kõikide moodulite, et oma oskusi järk-järgult arendada. Proovite lihtsaid näiteid, et mõista põhialuseid, enne kui liigute sügavamale [Sissejuhatus](01-introduction/README.md) moodulisse GPT-5-ga.

<img src="../../translated_images/learning-path.ac2da6720e77c3165960835627cef4c20eb2afb103be73a4f25b6d8fafbd738d.et.png" alt="Learning Path" width="800"/>

Pärast moodulite läbimist uurige [Testimise juhendit](docs/TESTING.md), et näha LangChain4j testimise kontseptsioone praktikas.

> **Märkus:** See koolitus kasutab nii GitHubi mudeleid kui ka Azure OpenAI-d. [Kiire algus](00-quick-start/README.md) ja [MCP](05-mcp/README.md) moodulid kasutavad GitHubi mudeleid (Azure tellimus ei ole vajalik), samas kui moodulid 1-4 kasutavad Azure OpenAI GPT-5.

## Õppimine GitHub Copilotiga

Kiireks kodeerimise alustamiseks avage see projekt GitHub Codespace'is või oma kohalikus IDE-s koos antud devcontaineriga. Selle kursuse devcontainer on eelkonfigureeritud GitHub Copilotiga AI paarisprogrammeerimiseks.

Igas koodinäites on soovitatud küsimused, mida saate GitHub Copilotile esitada, et oma arusaamist süvendada. Otsige 💡/🤖 vihjeid:

- **Java failide päistes** - iga näite spetsiifilised küsimused
- **Moodulite README-d** - uurimisküsimused pärast koodinäiteid

**Kasutusjuhend:** Avage ükskõik milline koodifail ja esitage Copilotile soovitatud küsimused. Tal on täielik ülevaade koodibaasist ning ta suudab selgitada, laiendada ja pakkuda alternatiive.

Tahate rohkem teada? Vaadake [Copilot AI paarisprogrammeerimiseks](https://aka.ms/GitHubCopilotAI).

## Täiendavad ressursid

### LangChain
[![LangChain4j algajatele](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js algajatele](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Agendid
[![AZD algajatele](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI algajatele](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP algajatele](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI agentid algajatele](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---

### Generatiivse AI sari
[![Generatiivne AI algajatele](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generatiivne AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generatiivne AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generatiivne AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---

### Põhiõpe
[![ML algajatele](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Andmeteadus algajatele](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI algajatele](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Küberjulgeolek algajatele](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Veebiarendus algajatele](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT algajatele](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR arendus algajatele](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---

### Copiloti sari
[![Copilot AI paarisprogrammeerimiseks](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot C#/.NET jaoks](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copiloti seiklus](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)

## Abi saamine

Kui jääte hätta või teil on küsimusi AI-rakenduste loomise kohta, liituge:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

Kui teil on toote tagasisidet või ehitamisel vigu, külastage:

[![Azure AI Foundry arendajate foorum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## Litsents

MIT litsents - vaadake üksikasju failist [LICENSE](../../LICENSE).

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastutusest loobumine**:
See dokument on tõlgitud kasutades tehisintellektil põhinevat tõlketeenust [Co-op Translator](https://github.com/Azure/co-op-translator). Kuigi püüame tagada täpsust, palun arvestage, et automaatsed tõlked võivad sisaldada vigu või ebatäpsusi. Originaaldokument selle emakeeles tuleks pidada autoriteetseks allikaks. Olulise teabe puhul soovitatakse kasutada professionaalset inimtõlget. Me ei vastuta selle tõlke kasutamisest tulenevate arusaamatuste või valesti mõistmiste eest.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->