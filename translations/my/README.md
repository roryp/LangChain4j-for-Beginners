<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "69c7e2616c66df6cc296492fbfcad9ec",
  "translation_date": "2025-12-13T12:49:54+00:00",
  "source_file": "README.md",
  "language_code": "my"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b506e9588f734989dd106ebd9f977b7f784941a28b88348f0d6.my.png" alt="LangChain4j" width="800"/>

# LangChain4j အတွက် စတင်သင်ယူသူများ

LangChain4j နှင့် Azure OpenAI GPT-5 ကို အသုံးပြု၍ အခြေခံ စကားပြောမှ စ၍ AI ကိုယ်စားလှယ်များထိ AI အပလီကေးရှင်းများ တည်ဆောက်ခြင်းအတွက် သင်တန်း။

**LangChain4j အသစ်လား?** အဓိပ္ပါယ်များနှင့် အယူအဆများအတွက် [Glossary](docs/GLOSSARY.md) ကို ကြည့်ပါ။

## အကြောင်းအရာ စာရင်း

1. [အမြန်စတင်ခြင်း](00-quick-start/README.md) - LangChain4j ဖြင့် စတင်ပါ
2. [နိဒါန်း](01-introduction/README.md) - LangChain4j ၏ အခြေခံအချက်များကို သင်ယူပါ
3. [Prompt Engineering](02-prompt-engineering/README.md) - ထိရောက်သော prompt ဒီဇိုင်း ကျွမ်းကျင်ပါ
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - ဉာဏ်ရည်မြင့် သိပ္ပံပညာအခြေခံ စနစ်များ တည်ဆောက်ပါ
5. [ကိရိယာများ](04-tools/README.md) - AI ကိုယ်စားလှယ်များနှင့် ပြင်ပကိရိယာများနှင့် API များ ပေါင်းစပ်ပါ
6. [MCP (Model Context Protocol)](05-mcp/README.md) - Model Context Protocol နှင့် အလုပ်လုပ်ပါ
---

## သင်ယူမှု လမ်းကြောင်း

[အမြန်စတင်ခြင်း](00-quick-start/README.md) မော်ဂျူးဖြင့် စတင်ပြီး တစ်ခုချင်းစီ မော်ဂျူးများကို အဆင့်လိုက် တိုးတက်စွာ သင်ယူပါ။ အခြေခံ ဥပမာများကို ကြိုးစားပြီး အခြေခံအချက်များကို နားလည်ပါက [နိဒါန်း](01-introduction/README.md) မော်ဂျူးသို့ ရောက်ရှိပြီး GPT-5 နှင့် ပိုမိုနက်ရှိုင်းစွာ လေ့လာနိုင်ပါသည်။

<img src="../../translated_images/learning-path.ac2da6720e77c3165960835627cef4c20eb2afb103be73a4f25b6d8fafbd738d.my.png" alt="Learning Path" width="800"/>

မော်ဂျူးများ ပြီးဆုံးပြီးနောက် [စမ်းသပ်ခြင်း လမ်းညွှန်](docs/TESTING.md) ကို လေ့လာပြီး LangChain4j စမ်းသပ်မှု အယူအဆများကို လက်တွေ့ကြည့်ရှုပါ။

> **မှတ်ချက်။** ဤသင်တန်းတွင် GitHub Models နှင့် Azure OpenAI နှစ်ခုလုံးကို အသုံးပြုသည်။ [အမြန်စတင်ခြင်း](00-quick-start/README.md) နှင့် [MCP](05-mcp/README.md) မော်ဂျူးများတွင် GitHub Models ကို အသုံးပြုသည် (Azure subscription မလိုအပ်ပါ)၊ မော်ဂျူး ၁ မှ ၄ အထိတွင် Azure OpenAI GPT-5 ကို အသုံးပြုသည်။

## GitHub Copilot ဖြင့် သင်ယူခြင်း

အမြန်ဆုံး ကုဒ်ရေးရန် GitHub Codespace သို့မဟုတ် သင့်ဒေသခံ IDE တွင် ဒီပရောဂျက်ကို ဖွင့်ပါ။ ဤသင်တန်းတွင် အသုံးပြုသော devcontainer တွင် AI အတွက် GitHub Copilot ကို ကြိုတင်ပြင်ဆင်ထားသည်။

ကုဒ်ဥပမာတိုင်းတွင် GitHub Copilot ကို မေးမြန်းနိုင်သော မေးခွန်းများ ပါဝင်သည်။ 💡/🤖 အမှတ်အသားများကို အောက်ပါနေရာများတွင် ရှာဖွေပါ-

- **Java ဖိုင်ခေါင်းစီးများ** - ဥပမာတစ်ခုချင်းစီအတွက် မေးခွန်းများ
- **မော်ဂျူး README များ** - ကုဒ်ဥပမာများပြီးနောက် စူးစမ်းမေးမြန်းရန်

**အသုံးပြုနည်း။** မည်သည့်ကုဒ်ဖိုင်မဆို ဖွင့်ပြီး Copilot ကို အဆိုပါမေးခွန်းများ မေးပါ။ Copilot သည် ကုဒ်အခြေခံကို အပြည့်အဝ နားလည်ပြီး ရှင်းပြ၊ တိုးချဲ့၊ နှင့် အခြားရွေးချယ်စရာများကို အကြံပြုနိုင်သည်။

ပိုမိုသိရှိလိုပါသလား? [AI အတွက် Copilot အတူတကွ ပရိုဂရမ်ရေးခြင်း](https://aka.ms/GitHubCopilotAI) ကို ကြည့်ပါ။

## အပိုဆောင်း အရင်းအမြစ်များ

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

### အခြေခံ သင်ယူမှု
[![ML for Beginners](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science for Beginners](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI for Beginners](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Cybersecurity for Beginners](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev for Beginners](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT for Beginners](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development for Beginners](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---

### Copilot စီးရီး
[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)

## အကူအညီရယူခြင်း

AI အပလီကေးရှင်းများ တည်ဆောက်ရာတွင် အခက်အခဲရှိပါက သို့မဟုတ် မေးခွန်းများရှိပါက:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

ထုတ်ကုန်တုံ့ပြန်ချက် သို့မဟုတ် အမှားများရှိပါက:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## လိုင်စင်

MIT လိုင်စင် - အသေးစိတ်အတွက် [LICENSE](../../LICENSE) ဖိုင်ကို ကြည့်ပါ။

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**အကြောင်းကြားချက်**  
ဤစာတမ်းကို AI ဘာသာပြန်ဝန်ဆောင်မှု [Co-op Translator](https://github.com/Azure/co-op-translator) ဖြင့် ဘာသာပြန်ထားပါသည်။ ကျွန်ုပ်တို့သည် တိကျမှန်ကန်မှုအတွက် ကြိုးစားသော်လည်း အလိုအလျောက် ဘာသာပြန်မှုများတွင် အမှားများ သို့မဟုတ် မှားယွင်းချက်များ ပါဝင်နိုင်ကြောင်း သတိပြုပါရန် မေတ္တာရပ်ခံအပ်ပါသည်။ မူရင်းစာတမ်းကို မိမိဘာသာစကားဖြင့်သာ တရားဝင်အရင်းအမြစ်အဖြစ် သတ်မှတ်စဉ်းစားသင့်ပါသည်။ အရေးကြီးသော အချက်အလက်များအတွက် လူ့ပညာရှင်များ၏ ပရော်ဖက်ရှင်နယ် ဘာသာပြန်မှုကို အကြံပြုပါသည်။ ဤဘာသာပြန်မှုကို အသုံးပြုရာမှ ဖြစ်ပေါ်လာနိုင်သည့် နားလည်မှုမှားယွင်းမှုများ သို့မဟုတ် မှားဖတ်မှုများအတွက် ကျွန်ုပ်တို့သည် တာဝန်မယူပါ။
<!-- CO-OP TRANSLATOR DISCLAIMER END -->