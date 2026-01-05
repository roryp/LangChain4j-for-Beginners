<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "d61ab6c245562094cc3dddecf08b50d3",
  "translation_date": "2025-12-31T06:00:08+00:00",
  "source_file": "README.md",
  "language_code": "my"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b50.my.png" alt="LangChain4j" width="800"/>

### 🌐 ဘာသာစကားအမျိုးမျိုး ထောက်ပံ့မှု

#### GitHub Action ဖြင့် ထောက်ပံ့သည် (အလိုအလျောက်နှင့် အမြဲနောက်ဆုံးထား)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arabic](../ar/README.md) | [Bengali](../bn/README.md) | [Bulgarian](../bg/README.md) | [Burmese (Myanmar)](./README.md) | [Chinese (Simplified)](../zh/README.md) | [Chinese (Traditional, Hong Kong)](../hk/README.md) | [Chinese (Traditional, Macau)](../mo/README.md) | [Chinese (Traditional, Taiwan)](../tw/README.md) | [Croatian](../hr/README.md) | [Czech](../cs/README.md) | [Danish](../da/README.md) | [Dutch](../nl/README.md) | [Estonian](../et/README.md) | [Finnish](../fi/README.md) | [French](../fr/README.md) | [German](../de/README.md) | [Greek](../el/README.md) | [Hebrew](../he/README.md) | [Hindi](../hi/README.md) | [Hungarian](../hu/README.md) | [Indonesian](../id/README.md) | [Italian](../it/README.md) | [Japanese](../ja/README.md) | [Kannada](../kn/README.md) | [Korean](../ko/README.md) | [Lithuanian](../lt/README.md) | [Malay](../ms/README.md) | [Malayalam](../ml/README.md) | [Marathi](../mr/README.md) | [Nepali](../ne/README.md) | [Nigerian Pidgin](../pcm/README.md) | [Norwegian](../no/README.md) | [Persian (Farsi)](../fa/README.md) | [Polish](../pl/README.md) | [Portuguese (Brazil)](../br/README.md) | [Portuguese (Portugal)](../pt/README.md) | [Punjabi (Gurmukhi)](../pa/README.md) | [Romanian](../ro/README.md) | [Russian](../ru/README.md) | [Serbian (Cyrillic)](../sr/README.md) | [Slovak](../sk/README.md) | [Slovenian](../sl/README.md) | [Spanish](../es/README.md) | [Swahili](../sw/README.md) | [Swedish](../sv/README.md) | [Tagalog (Filipino)](../tl/README.md) | [Tamil](../ta/README.md) | [Telugu](../te/README.md) | [Thai](../th/README.md) | [Turkish](../tr/README.md) | [Ukrainian](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnamese](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j အစပြုသူများအတွက်

LangChain4j နှင့် Azure OpenAI GPT-5 ကို အသုံးပြု၍ အခြေခံ chat မှ AI agent များထိ AI အက်ပလီကေးရှင်းများ တည်ဆောက်ရန် သင်တန်း။

**LangChain4j အတွက် အသစ်လား?** အဓိကအသုံးအနှုန်းများနှင့် အယူအဆများ၏ အဓိပ္ပာယ်များကို ကြည့်ရန် [Glossary](docs/GLOSSARY.md) ကို စစ်ဆေးပါ။

## Table of Contents

1. [Quick Start](00-quick-start/README.md) - LangChain4j နှင့် စတင်ရန်
2. [Introduction](01-introduction/README.md) - LangChain4j ၏ မူလ အခြေခံများကို သင်ယူပါ
3. [Prompt Engineering](02-prompt-engineering/README.md) - ထိရောက်သော prompt ဒီဇိုင်း ကျွမ်းကျင်ပါ
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - ပညာအခြေပြု စနစ်များ တည်ဆောက်ပါ
5. [Tools](04-tools/README.md) - ပြင်ပကိရိယာများနှင့် ရိုးရှင်းသော အကူအညီပေးသူများကို ပေါင်းစည်းပါ
6. [MCP (Model Context Protocol)](05-mcp/README.md) - Model Context Protocol (MCP) နှင့် Agentic မော်ဂျူးများအလုပ်လုပ်နည်းကို သင်ယူပါ
---

##  သင်ယူလမ်းကြောင်း

> **အလျင်အမြန် စတင်ခြင်း**

1. ဤ repository ကို သင့် GitHub အကောင့်သို့ Fork လုပ်ပါ
2. Click **Code** → **Codespaces** tab → **...** → **New with options...**
3. Use the defaults – this will select the Development container created for this course
4. Click **Create codespace**
5. ပတ်ဝန်းကျင် ပြင်ဆင်ပြီး အသင့်ဖြစ်ရန် မိနစ် 5-10 ခန့် စောင့်ပါ
6. တိုက်ရိုက် စတင်ရန် [Quick Start](./00-quick-start/README.md) သို့ သွားပါ!

> **ဒေသတွင်း Clone လုပ်ချင်ပါသလား?**
>
> ဒီ repository သည် ဘာသာပြန် 50 ကျော် ပါဝင်သောကြောင့် download အရွယ်အစားကို အလွန် တိုးစေပါသည်။ ဘာသာပြန်များမပါဘဲ clone လုပ်ချင်ပါက sparse checkout ကို အသုံးပြုပါ:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> ၎င်းက သင်တန်းကို ပြီးမြောက်ရန် လိုအပ်သည့် အရာအားလုံးကို ပေးပြီး download ကို များစွာ မြန်ဆန်စေပါသည်။

မော်ဂျူးများကို ပြီးမြောက်ပြီးနောက် LangChain4j စမ်းသပ်မှုဆိုင်ရာ သဘောတရားများကို လက်တွေ့ကြည့်ရှုရန် [Testing Guide](docs/TESTING.md) ကို စူးစမ်းပါ။

> **မှတ်ချက်:** ဤသင်တန်းတွင် GitHub Models နှင့် Azure OpenAI တို့နှစ်ခုစလုံးကို အသုံးပြုသည်။ [Quick Start](00-quick-start/README.md) နှင့် [MCP](05-mcp/README.md) မော်ဂျူးများတွင် GitHub Models ကို အသုံးပြုသည် (Azure subscription မလိုအပ်ပါ)၊ မော်ဂျူး 1-4 များတွင် Azure OpenAI GPT-5 ကို အသုံးပြုသည်။

## GitHub Copilot ဖြင့် သင်ယူခြင်း

ကိုးကားချက်အတိုင်း ကုဒ်ရေးကို မြန်မြန်စတင်ရန် ဒီ project ကို GitHub Codespace သို့မဟုတ် ပေးထားသော devcontainer ဖြင့် သင့် ဒေသဆိုင်ရာ IDE တွင် ဖွင့်ပါ။ ဤသင်တန်းတွင် အသုံးပြုသော devcontainer သည် AI အတွက် GitHub Copilot ဖြင့် ကြိုတင် ဖွဲ့စည်းထားပြီး စုံစမ်းအတူရေးသားနိုင်အောင် ပြင်ဆင်ထားပါသည်။

ကုဒ်ဥပမာတစ်ခုချင်းစီတွင် GitHub Copilot ထံ မေးနိုင်သော အကြံပြုမေးခွန်းများ ပါရှိသည်၊ ၎င်းများက သင့်နားလည်မှုကို နက်ရှိုင်းစေပါလိမ့်မည်။ 💡/🤖 အဆင့်ပြချက်များကို အောက်ပါနေရာများတွင် ကြည့်ရှုပါ။

- **Java file headers** - ဥပမာတိုင်းနှင့် သက်ဆိုင်သော မေးခွန်းများ
- **Module READMEs** - ကုဒ်ဥပမာများပြီးနောက် ရှာဖွေစူးစမ်းရန် အဆိုပြုချက်များ

**အသုံးပြုပုံ:** မည်သည့်ကုဒ်ဖိုင်ကိုမဆို ဖွင့်၍ Copilot သို့ အဆိုပြုထားသော မေးခွန်းများကို မေးပါ။ ၎င်းတွင် ကုဒ်base ၏ အပြည့်အစုံ ထဲက ပတ်ဝန်းကျင်ရှိပြီး ရှင်းလင်းပြသ၊ တိုးချဲ့၊ နှင့် အခြားရွေးချယ်စရာများကို တင်ပြနိုင်ပါသည်။

ပိုမိုသိလိုပါသလား? [Copilot for AI Paired Programming](https://aka.ms/GitHubCopilotAI) ကို ကြည့်ပါ။

## ထပ်ဆောင်း အရင်းအမြစ်များ

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j အစပြုသူများအတွက်](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js အစပြုသူများအတွက်](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Agents
[![AZD အစပြုသူများအတွက်](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI အစပြုသူများအတွက်](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP အစပြုသူများအတွက်](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Agents အစပြုသူများအတွက်](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Generative AI Series
[![Generative AI အစပြုသူများအတွက်](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generative AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generative AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generative AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### အခြေခံ သင်ယူမှု
[![ML အစပြုသူများအတွက်](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science အစပြုသူများအတွက်](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI အစပြုသူများအတွက်](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Cybersecurity အစပြုသူများအတွက်](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev အစပြုသူများအတွက်](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT အစပြုသူများအတွက်](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development အစပြုသူများအတွက်](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot Series
[![AI တွဲဖက် ပရိုဂရမ်းမင်းအတွက် Copilot](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![C#/.NET အတွက် Copilot](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot စွန့်စားခန်း](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## အကူအညီရယူခြင်း

AI အက်ပ်များ တည်ဆောက်ရာတွင် အခက်အခဲ ရှိပါက သို့မဟုတ် မေးခွန်းများရှိပါက အောက်ဖော်ပြပါတွင် ဝင်ပါ။

[![Azure AI Foundry Discord အသိုင်းအဝိုင်း](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

ထုတ်ကုန်အကြံပြန်ချက်များ သို့မဟုတ် တည်ဆောက်စဉ်တွင် ဖြစ်ပေါ်သော အမှားများရှိပါက အောက်ဖော်ပြပါကို သွားကြည့်ပါ။

[![Azure AI Foundry Developer ဖိုရမ်](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## လိုင်စင်

MIT License - အသေးစိတ်အချက်အလက်များအတွက် [LICENSE](../../LICENSE) ဖိုင်ကို ကြည့်ပါ။

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
သတိပေးချက်:
ဤစာတမ်းကို AI ဘာသာပြန်ဝန်ဆောင်မှု [Co-op Translator](https://github.com/Azure/co-op-translator) ဖြင့် ဘာသာပြန်ထားပါသည်။ ကျွန်ုပ်တို့သည် တိကျမှန်ကန်စေရန် ကြိုးပမ်းပါသော်လည်း အလိုအလျောက် ဘာသာပြန်ချက်များတွင် အမှားများ သို့မဟုတ် မှားယွင်းချက်များ ပါဝင်ကြနိုင်ပါသည်။ မူလစာတမ်းကို မူလဘာသာဖြင့်သာ တရားဝင် အရင်းအမြစ်အဖြစ် သတ်မှတ်စဉ်းစားသင့်သည်။ အရေးကြီးသော အချက်အလက်များအတွက် ပရော်ဖက်ရှင်နယ် လူ့ဘာသာပြန်ခြင်းကို အကြံပြုပါသည်။ ဤဘာသာပြန်ချက်ကို အသုံးပြုမှုကြောင့် ဖြစ်ပေါ်နိုင်သည့် နားလည်မှုပြဿနာများ သို့မဟုတ် မှားယွင်းဖော်ပြချက်များအတွက် ကျွန်ုပ်တို့သည် တာဝန်မယူပါ။
<!-- CO-OP TRANSLATOR DISCLAIMER END -->