<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "d61ab6c245562094cc3dddecf08b50d3",
  "translation_date": "2025-12-30T20:42:24+00:00",
  "source_file": "README.md",
  "language_code": "ur"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b50.ur.png" alt="LangChain4j" width="800"/>

### 🌐 متعدد زبانوں کی معاونت

#### GitHub Action کے ذریعے حمایت یافتہ (خودکار اور ہمیشہ تازہ ترین)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[عربی](../ar/README.md) | [بنگالی](../bn/README.md) | [بلغاریائی](../bg/README.md) | [برمی (میانمار)](../my/README.md) | [چینی (سادہ)](../zh/README.md) | [چینی (روایتی، ہانگ کانگ)](../hk/README.md) | [چینی (روایتی، مکاو)](../mo/README.md) | [چینی (روایتی، تائیوان)](../tw/README.md) | [کروشیا](../hr/README.md) | [چیک](../cs/README.md) | [ڈینش](../da/README.md) | [ڈچ](../nl/README.md) | [اسٹونین](../et/README.md) | [فننش](../fi/README.md) | [فرانسیسی](../fr/README.md) | [جرمن](../de/README.md) | [یونانی](../el/README.md) | [عبرانی](../he/README.md) | [ہندی](../hi/README.md) | [ہنگیری](../hu/README.md) | [انڈونیشیائی](../id/README.md) | [اطالوی](../it/README.md) | [جاپانی](../ja/README.md) | [کنڑا](../kn/README.md) | [کوریائی](../ko/README.md) | [لتھوانیائی](../lt/README.md) | [ملائزی](../ms/README.md) | [مالایالم](../ml/README.md) | [مراٹھی](../mr/README.md) | [نیپالی](../ne/README.md) | [نائجیریائی پڈگن](../pcm/README.md) | [نارویجی](../no/README.md) | [فارسی (Farsi)](../fa/README.md) | [پولش](../pl/README.md) | [پرتگالی (برازیل)](../br/README.md) | [پرتگالی (پرتگال)](../pt/README.md) | [پنجابی (گُرمکھی)](../pa/README.md) | [رومانیائی](../ro/README.md) | [روسی](../ru/README.md) | [سربیائی (سیریلیک)](../sr/README.md) | [سلوواک](../sk/README.md) | [سلووینیائی](../sl/README.md) | [ہسپانوی](../es/README.md) | [سواحلی](../sw/README.md) | [سویڈش](../sv/README.md) | [تاگالوگ (فلپائنی)](../tl/README.md) | [تمل](../ta/README.md) | [تیلگو](../te/README.md) | [تھائی](../th/README.md) | [ترکی](../tr/README.md) | [یوکرائنی](../uk/README.md) | [اردو](./README.md) | [ویتنامی](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j برائے مبتدی

LangChain4j اور Azure OpenAI GPT-5 کے ساتھ AI ایپلیکیشنز بنانے کے لیے ایک کورس، بنیادی چیٹ سے لے کر AI ایجنٹس تک۔

**LangChain4j میں نئے ہیں؟** کلیدی اصطلاحات اور تصورات کی تعریف کے لیے [گلاسری](docs/GLOSSARY.md) دیکھیں۔

## فہرستِ مضامین

1. [Quick Start](00-quick-start/README.md) - LangChain4j کے ساتھ شروعات کریں
2. [تعارف](01-introduction/README.md) - LangChain4j کے بنیادی اصول سیکھیں
3. [پرومپٹ انجینئرنگ](02-prompt-engineering/README.md) - موثر پرومپٹ ڈیزائن میں مہارت حاصل کریں
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - ذہین علم پر مبنی نظام بنائیں
5. [ٹولز](04-tools/README.md) - بیرونی ٹولز اور سادہ معاونین کو ضم کریں
6. [MCP (Model Context Protocol)](05-mcp/README.md) - Model Context Protocol (MCP) اور Agentic ماڈیولز کے ساتھ کام کریں
---

##  سیکھنے کا راستہ

> **فوری آغاز**

1. اس مخزن کو اپنے GitHub اکاؤنٹ میں فورک کریں
2. کلک کریں **Code** → **Codespaces** ٹیب → **...** → **New with options...**
3. ڈیفالٹس استعمال کریں – اس سے اس کورس کے لیے بنائے گئے ڈویلپمنٹ کنٹینر کا انتخاب ہو جائے گا
4. **Create codespace** پر کلک کریں
5. ماحول تیار ہونے تک 5-10 منٹ انتظار کریں
6. شروع کرنے کے لیے براہِ راست [فوری آغاز](./00-quick-start/README.md) پر جائیں!

> **کیا آپ لوکل طور پر کلون کرنا پسند کریں گے؟**
>
> اس ریپوزیٹری میں 50+ زبانوں کی تراجم شامل ہیں جو ڈاؤن لوڈ سائز کو نمایاں طور پر بڑھاتی ہیں۔ تراجم کے بغیر کلون کرنے کے لیے sparse checkout استعمال کریں:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```

> یہ آپ کو کورس مکمل کرنے کے لیے درکار تمام چیزیں فراہم کرتا ہے اور ڈاؤن لوڈ بہت تیز ہو جاتا ہے۔

ماڈیولز مکمل کرنے کے بعد، LangChain4j کے ٹیسٹنگ تصورات کو عملی طور پر دیکھنے کے لیے [ٹیسٹنگ گائیڈ](docs/TESTING.md) کا مطالعہ کریں۔

> **نوٹ:** یہ تربیت GitHub Models اور Azure OpenAI دونوں استعمال کرتی ہے۔ [فوری آغاز](00-quick-start/README.md) اور [MCP](05-mcp/README.md) ماڈیولز GitHub Models استعمال کرتے ہیں (Azure سبسکرپشن کی ضرورت نہیں)، جبکہ ماڈیولز 1-4 Azure OpenAI GPT-5 استعمال کرتے ہیں۔


## GitHub Copilot کے ساتھ سیکھنا

تیزی سے کوڈنگ شروع کرنے کے لیے، اس پروجیکٹ کو GitHub Codespace میں یا اپنے مقامی IDE میں فراہم کردہ devcontainer کے ساتھ کھولیں۔ اس کورس میں استعمال ہونے والا devcontainer GitHub Copilot کے ساتھ AI پئیرڈ پروگرامنگ کے لیے پہلے ہی ترتیب دیا گیا آتا ہے۔

ہر کوڈ مثال میں تجویز کردہ سوالات شامل ہیں جو آپ GitHub Copilot سے پوچھ سکتے ہیں تاکہ اپنی سمجھ کو گہرا کریں۔ 💡/🤖 پرامپٹس تلاش کریں:

- **Java file headers** - ہر مثال سے متعلق مخصوص سوالات
- **Module READMEs** - کوڈ مثالوں کے بعد تلاش کے لیے پرامپٹس

**استعمال کا طریقہ:** کوئی بھی کوڈ فائل کھولیں اور تجویز کردہ سوالات Copilot سے پوچھیں۔ اسے کوڈ بیس کا مکمل سیاق و سباق معلوم ہوتا ہے اور یہ وضاحت، توسیع، اور متبادل تجاویز پیش کر سکتا ہے۔

مزید جاننا چاہتے ہیں؟ [Copilot برائے AI جوڑی پروگرامنگ](https://aka.ms/GitHubCopilotAI) دیکھیں。


## اضافی وسائل

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![ابتدائیوں کے لیے LangChain4j](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![ابتدائیوں کے لیے LangChain.js](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Agents
[![AZD برائے ابتدائی](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI برائے ابتدائی](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP برائے ابتدائی](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI ایجنٹس برائے ابتدائی](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### جنریٹو AI سیریز
[![جنریٹو AI برائے ابتدائی](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![جنریٹو AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![جنریٹو AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![جنریٹو AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### بنیادی مطالعہ
[![مشین لرننگ برائے مبتدی](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![ڈیٹا سائنس برائے مبتدی](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI برائے مبتدی](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![سائبرسیکیوریٹی برائے مبتدی](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![ویب ڈیولپمنٹ برائے مبتدی](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT برائے مبتدی](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR ڈویلپمنٹ برائے مبتدی](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### کوپائلٹ سیریز
[![Copilot برائے AI جوڑی پروگرامنگ](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot برائے C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot مہم](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## مدد حاصل کریں

اگر آپ پھنس جائیں یا AI ایپس بنانے کے بارے میں کوئی سوال ہو تو شامل ہوں:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

اگر آپ کے پاس پروڈکٹ کے بارے میں تاثرات ہوں یا تعمیر کے دوران کوئی خرابی پیش آئے تو ملاحظہ کریں:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## لائسنس

MIT لائسنس - تفصیلات کے لیے [LICENSE](../../LICENSE) فائل دیکھیں.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
ردِ ذمہ داری:
یہ دستاویز AI ترجمہ سروس [Co-op Translator](https://github.com/Azure/co-op-translator) کے ذریعے ترجمہ کی گئی ہے۔ اگرچہ ہم درستگی کی پوری کوشش کرتے ہیں، براہِ کرم نوٹ کریں کہ خودکار تراجم میں غلطیاں یا عدمِ درستی ہو سکتی ہیں۔ اصل دستاویز اپنی مادری زبان میں ہی معتبر ماخذ سمجھی جانی چاہیے۔ اہم معلومات کے لیے پیشہ ورانہ انسانی ترجمہ تجویز کیا جاتا ہے۔ اس ترجمے کے استعمال سے پیدا ہونے والی کسی بھی غلط فہمی یا غلط تعبیر کے لیے ہم ذمہ دار نہیں ہیں۔
<!-- CO-OP TRANSLATOR DISCLAIMER END -->