<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "d61ab6c245562094cc3dddecf08b50d3",
  "translation_date": "2025-12-30T22:35:24+00:00",
  "source_file": "README.md",
  "language_code": "bn"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b50.bn.png" alt="LangChain4j" width="800"/>

### 🌐 বহু-ভাষা সমর্থন

#### GitHub Action দ্বারা সমর্থিত (স্বয়ংক্রিয় ও সর্বদা আপ-টু-ডেট)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[আরবি](../ar/README.md) | [বাংলা](./README.md) | [বুলগেরিয়ান](../bg/README.md) | [বার্মিজ (মায়ানমার)](../my/README.md) | [চীনা (সরলীকৃত)](../zh/README.md) | [চীনা (প্রচলিত, হংকং)](../hk/README.md) | [চীনা (প্রচলিত, মাকাও)](../mo/README.md) | [চীনা (প্রচলিত, তাইওয়ান)](../tw/README.md) | [ক্রোয়েশিয়ান](../hr/README.md) | [চেক](../cs/README.md) | [ড্যানিশ](../da/README.md) | [ডাচ](../nl/README.md) | [এস্তোনিয়ান](../et/README.md) | [ফিনিশ](../fi/README.md) | [ফরাসি](../fr/README.md) | [জার্মান](../de/README.md) | [গ্রিক](../el/README.md) | [হিব্রু](../he/README.md) | [হিন্দি](../hi/README.md) | [হাঙ্গেরিয়ান](../hu/README.md) | [ইন্দোনেশিয়ান](../id/README.md) | [ইতালিয়ান](../it/README.md) | [জাপানি](../ja/README.md) | [কন্নড়](../kn/README.md) | [কোরিয়ান](../ko/README.md) | [লিথুয়ানিয়ান](../lt/README.md) | [মালয়](../ms/README.md) | [মালায়ালাম](../ml/README.md) | [মরাঠি](../mr/README.md) | [নেপালি](../ne/README.md) | [নাইজেরীয়ান পিডজি](../pcm/README.md) | [নরওয়েজিয়ান](../no/README.md) | [পারসিয়ান (ফার্সি)](../fa/README.md) | [পোলিশ](../pl/README.md) | [পর্তুগিজ (ব্রাজিল)](../br/README.md) | [পর্তুগিজ (পর্তুগাল)](../pt/README.md) | [পাঞ্জাবি (গুরুমুখী)](../pa/README.md) | [রোমানিয়ান](../ro/README.md) | [রাশিয়ান](../ru/README.md) | [সার্বীয় (সিরিলিক)](../sr/README.md) | [স্লোভাক](../sk/README.md) | [স্লোভেনিয়ান](../sl/README.md) | [স্প্যানিশ](../es/README.md) | [স্বাহিলি](../sw/README.md) | [সুইডিশ](../sv/README.md) | [তাগালগ (ফিলিপিনো)](../tl/README.md) | [তামিল](../ta/README.md) | [তেলুগু](../te/README.md) | [থাই](../th/README.md) | [তুর্কি](../tr/README.md) | [ইউক্রেনীয়](../uk/README.md) | [উর্দু](../ur/README.md) | [ভিয়েতনামি](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j নবীনদের জন্য

LangChain4j এবং Azure OpenAI GPT-5 ব্যবহার করে AI অ্যাপ্লিকেশন তৈরি করার জন্য একটি কোর্স, মৌলিক চ্যাট থেকে AI এজেন্ট পর্যন্ত।

**LangChain4j-এ নতুন?** কী টার্ম এবং ধারণার সংজ্ঞার জন্য [Glossary](docs/GLOSSARY.md) দেখুন।

## Table of Contents

1. [দ্রুত শুরু](00-quick-start/README.md) - LangChain4j দিয়ে শুরু করুন
2. [ভূমিকা](01-introduction/README.md) - LangChain4j এর মৌলিক বিষয়গুলো শিখুন
3. [প্রম্পট ইঞ্জিনিয়ারিং](02-prompt-engineering/README.md) - কার্যকর প্রম্পট ডিজাইন আয়ত্ত করুন
4. [RAG (রিট্রিভাল-অগমেন্টেড জেনারেশন)](03-rag/README.md) - বুদ্ধিমত্তাসম্পন্ন জ্ঞান-ভিত্তিক সিস্টেম তৈরি করুন
5. [Tools](04-tools/README.md) - বাহ্যিক টুল এবং সরল সহায়ক একীভূত করুন
6. [MCP (মডেল কনটেক্সট প্রটোকল)](05-mcp/README.md) - Model Context Protocol (MCP) এবং এজেন্টিক মডিউল নিয়ে কাজ করুন
---

##  শেখার পথ

> **দ্রুত শুরু**

1. এই রিপোজিটরিটি আপনার GitHub অ্যাকাউন্টে Fork করুন
2. ক্লিক করুন **Code** → **Codespaces** ট্যাব → **...** → **New with options...**
3. ডিফল্টগুলো ব্যবহার করুন – এটি এই কোর্সের জন্য তৈরি করা Development container সিলেক্ট করবে
4. ক্লিক করুন **Create codespace**
5. পরিবেশটি প্রস্তুত হতে 5-10 মিনিট অপেক্ষা করুন
6. শুরু করতে সরাসরি [দ্রুত শুরু](./00-quick-start/README.md) দেখুন!

> **লোকালি ক্লোন করা পছন্দ করেন?**
>
> এই রিপোজিটরিতে 50+ ভাষার অনুবাদ অন্তর্ভুক্ত আছে যা ডাউনলোড সাইজ উল্লেখযোগ্যভাবে বাড়ায়। অনুবাদ ছাড়াই ক্লোন করতে sparse checkout ব্যবহার করুন:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> এটি আপনাকে কোর্স সম্পন্ন করার জন্য যা কিছু দরকার তা সবই দেবে, এবং ডাউনলোড অনেক দ্রুত হবে।

মডিউলগুলো শেষ করার পরে, LangChain4j টেস্টিং ধারণাগুলো কার্যকরভাবে দেখতে [Testing Guide](docs/TESTING.md) অনুসন্ধান করুন।

> **দ্রষ্টব্য:** এই প্রশিক্ষণে GitHub Models এবং Azure OpenAI উভয়ই ব্যবহৃত হয়েছে। [দ্রুত শুরু](00-quick-start/README.md) এবং [MCP](05-mcp/README.md) মডিউলগুলো GitHub Models ব্যবহার করে (Azure সাবস্ক্রিপশন প্রয়োজন নেই), কাজী 1-4 মডিউলগুলো Azure OpenAI GPT-5 ব্যবহার করে।

## GitHub Copilot দিয়ে শেখা

দ্রুত কোডিং শুরু করতে, এই প্রজেক্টটি একটি GitHub Codespace বা আপনার লোকাল IDE তে দেওয়া devcontainer নিয়ে খুলুন। এই কোর্সে ব্যবহৃত devcontainer GitHub Copilot সহ AI পেয়ারড প্রোগ্রামিং-এর জন্য পূর্বনির্ধারিতভাবে কনফিগার করা আছে।

প্রতিটি কোড উদাহরণে এমন প্রশ্ন নির্দেশ করা আছে যা আপনি GitHub Copilot-কে জিজ্ঞাসা করে আপনার ধারণা আরও গভীর করতে পারেন। 💡/🤖 প্রম্পট গুলো দেখুন:

- **Java file headers** - প্রতিটি উদাহরণের জন্য নির্দিষ্ট প্রশ্ন
- **Module READMEs** - কোড উদাহরণগুলোর পরে অনুসন্ধানমূলক প্রম্পট

**ব্যবহার কীভাবে করবেন:** কোনো কোড ফাইল খুলুন এবং Copilot-কে নির্দেশিত প্রশ্নগুলো জিজ্ঞাসা করুন। এটি কোডবেসের পূর্ণ প্রসঙ্গ জানে এবং ব্যাখ্যা, সম্প্রসারণ, ও বিকল্প প্রস্তাব করতে পারে।

আরও জানতে চান? দেখুন [Copilot for AI Paired Programming](https://aka.ms/GitHubCopilotAI)।

## অতিরিক্ত সম্পদ

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j নবীনদের জন্য](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js নবীনদের জন্য](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Agents
[![AZD নবীনদের জন্য](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI নবীনদের জন্য](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP নবীনদের জন্য](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI এজেন্টস নবীনদের জন্য](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Generative AI Series
[![জেনারেটিভ AI নবীনদের জন্য](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![জেনারেটিভ AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![জেনারেটিভ AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![জেনারেটিভ AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### মূল শেখা
[![ML নবীনদের জন্য](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![ডাটা সায়েন্স নবীনদের জন্য](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI নবীনদের জন্য](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![সাইবারসিকিউরিটি নবীনদের জন্য](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![ওয়েব ডেভ নবীনদের জন্য](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT নবীনদের জন্য](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR ডেভেলপমেন্ট নবীনদের জন্য](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot সিরিজ
[![এআই যুগল প্রোগ্রামিং-এর জন্য কপাইলট](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![C#/.NET-এর জন্য কপাইলট](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![কপাইলট অ্যাডভেঞ্চার](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## সহায়তা

If you get stuck or have any questions about building AI apps, join:

[![Azure AI Foundry ডিসকর্ড](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

If you have product feedback or errors while building visit:

[![Azure AI Foundry ডেভেলপার ফোরাম](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## লাইসেন্স

MIT লাইসেন্স - বিস্তারিত জানার জন্য [LICENSE](../../LICENSE) ফাইল দেখুন।

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
দায়-অস্বীকার:
এই নথিটি এআই অনুবাদ সেবা Co-op Translator (https://github.com/Azure/co-op-translator) ব্যবহার করে অনুবাদ করা হয়েছে। আমরা যথাসম্ভব সঠিক অনুবাদের লক্ষ্যে কাজ করি, তবে অনুগ্রহ করে মনে রাখুন যে স্বয়ংক্রিয় অনুবাদে ত্রুটি বা অসংগততা থাকতে পারে। মূল নথিটি তার মাতৃভাষায়ই প্রামাণ্য উৎস হিসেবে গণ্য করা উচিৎ। গুরুত্বপূর্ণ তথ্যের ক্ষেত্রে পেশাদার মানব অনুবাদের পরামর্শ দেওয়া হয়। এই অনুবাদ ব্যবহারের ফলে উদ্ভূত কোনো ভুল বোঝাবুঝি বা ভুল ব্যাখ্যার জন্য আমরা দায়ী নই।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->