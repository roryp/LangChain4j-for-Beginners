<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "7dffae166c7db7dc932a0e3d0217cbb7",
  "translation_date": "2026-01-16T08:31:44+00:00",
  "source_file": "README.md",
  "language_code": "ur"
}
-->
<img src="../../translated_images/ur/LangChain4j.90e1d693fcc71b50.webp" alt="LangChain4j" width="800"/>

### 🌐 کثیراللسانی سپورٹ

#### گٹ ہب ایکشن کے ذریعے سپورٹ شدہ (خودکار اور ہمیشہ اپ ٹو ڈیٹ)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[عربی](../ar/README.md) | [بنگالی](../bn/README.md) | [بلغاریائی](../bg/README.md) | [برمی (میانمار)](../my/README.md) | [چینی (سمپلified)](../zh/README.md) | [چینی (روایتی، ہانگ کانگ)](../hk/README.md) | [چینی (روایتی، مکاو)](../mo/README.md) | [چینی (روایتی، تائیوان)](../tw/README.md) | [کروشیا ئی](../hr/README.md) | [چیک](../cs/README.md) | [ڈینش](../da/README.md) | [ڈچ](../nl/README.md) | [ایسٹونین](../et/README.md) | [فنش](../fi/README.md) | [فرانسیسی](../fr/README.md) | [جرمن](../de/README.md) | [یونانی](../el/README.md) | [عبرانی](../he/README.md) | [ہندی](../hi/README.md) | [ہنگریائی](../hu/README.md) | [انڈونیشیائی](../id/README.md) | [اطالوی](../it/README.md) | [جاپانی](../ja/README.md) | [کنڑ](../kn/README.md) | [کوریائی](../ko/README.md) | [لتھوانین](../lt/README.md) | [مالے](../ms/README.md) | [مالایالم](../ml/README.md) | [مراٹھی](../mr/README.md) | [نیپالی](../ne/README.md) | [نائجیریائی پڈجن](../pcm/README.md) | [ناروے](../no/README.md) | [فارسی (عربی)](../fa/README.md) | [پولش](../pl/README.md) | [پرتگالی (برازیل)](../br/README.md) | [پرتگالی (پرتگال)](../pt/README.md) | [پنجابی (گرمکھی)](../pa/README.md) | [رومانیائی](../ro/README.md) | [روسی](../ru/README.md) | [سربی (سریلیک)](../sr/README.md) | [سلوواکی](../sk/README.md) | [سلووینیائی](../sl/README.md) | [سپینی](../es/README.md) | [سواحلی](../sw/README.md) | [سویڈش](../sv/README.md) | [ٹاگالوگ (فلیپائنی)](../tl/README.md) | [تمل](../ta/README.md) | [تلگو](../te/README.md) | [تھائی](../th/README.md) | [ترکش](../tr/README.md) | [یوکرینی](../uk/README.md) | [اردو](./README.md) | [ویتنامی](../vi/README.md)

> **اگر مقامی طور پر کلون کرنا پسند کریں؟**

> اس ریپوزٹری میں 50+ زبانوں کے تراجم شامل ہیں جو ڈاؤن لوڈ کے سائز کو بہت زیادہ بڑھاتے ہیں۔ بغیر تراجم کے کلون کرنے کے لیے sparse checkout استعمال کریں:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> اس سے آپ کو وہ سب کچھ ملے گا جو آپ کو کورس مکمل کرنے کے لیے چاہیے، لیکن ڈاؤن لوڈ بہت تیز ہوگا۔
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# ابتدائیوں کے لیے LangChain4j

LangChain4j اور Azure OpenAI GPT-5 کے ساتھ AI ایپلیکیشنز بنانے کا کورس، بنیادی چیٹ سے لے کر AI ایجنٹس تک۔

**LangChain4j میں نئے ہیں؟** اہم اصطلاحات اور تصورات کی تعریف کے لیے [گلاسری](docs/GLOSSARY.md) دیکھیں۔

## فہرستِ مضامین

1. [فوری آغاز](00-quick-start/README.md) - LangChain4j کے ساتھ شروع کریں
2. [تعریف](01-introduction/README.md) - LangChain4j کے بنیادی اصول سیکھیں
3. [پرامپٹ انجینئرنگ](02-prompt-engineering/README.md) - مؤثر پرامپٹ ڈیزائن میں مہارت حاصل کریں
4. [رٰیگ (ریٹریول-آگمینٹڈ جنریشن)](03-rag/README.md) - ذہین معلوماتی نظام بنائیں
5. [اوزار](04-tools/README.md) - بیرونی اوزار اور آسان معاونات شامل کریں
6. [ایم سی پی (ماڈل کانٹیکسٹ پروٹوکول)](05-mcp/README.md) - ماڈل کانٹیکسٹ پروٹوکول (MCP) اور ایجنٹک ماڈیولز کے ساتھ کام کریں
---

## تعلیمی راہ

> **فوری آغاز**

1. اس ریپوزٹری کو اپنے گٹ ہب اکاؤنٹ میں فورک کریں
2. **Code** پر کلک کریں → **Codespaces** ٹیب → **...** → **New with options...** منتخب کریں
3. ڈیفالٹ منتخب کریں – یہ کورس کے لیے بنایا گیا Development container منتخب کرے گا
4. **Create codespace** پر کلک کریں
5. ماحول تیار ہونے کے لیے 5-10 منٹ انتظار کریں
6. شروع کرنے کے لیے سیدھے [فوری آغاز](./00-quick-start/README.md) پر جائیں!

ماڈیولز مکمل کرنے کے بعد، LangChain4j کے ٹیسٹنگ تصورات کو عملی طور پر دیکھنے کے لیے [ٹیسٹنگ گائیڈ](docs/TESTING.md) دریافت کریں۔

> **نوٹ:** یہ تربیت گٹ ہب ماڈلز اور Azure OpenAI دونوں استعمال کرتی ہے۔ [فوری آغاز](00-quick-start/README.md) ماڈیول GitHub ماڈلز استعمال کرتا ہے (کوئی Azure سبسکرپشن نہیں چاہیے)، جبکہ ماڈیولز 1-5 Azure OpenAI استعمال کرتے ہیں۔

## GitHub کوپائلٹ کے ساتھ سیکھنا

جلد کوڈنگ شروع کرنے کے لیے، اس پروجیکٹ کو GitHub Codespace یا اپنے مقامی IDE میں فراہم کردہ devcontainer کے ساتھ کھولیں۔ اس کورس میں استعمال ہونے والا devcontainer GitHub Copilot کے ساتھ پہلے سے مرتب ہوتا ہے، جو AI جوڑواں پروگرامنگ کے لیے ہے۔

ہر کوڈ کی مثال میں تجویز کردہ سوالات شامل ہیں جو آپ GitHub Copilot سے پوچھ سکتے ہیں تاکہ اپنی سمجھ کو گہرا کریں۔ 💡/🤖 اشارے تلاش کریں:

- **جاوا فائل ہیڈرز** - ہر مثال سے متعلق مخصوص سوالات
- **ماڈیول README** - کوڈ مثالوں کے بعد دریافت کے اشارے

**استعمال کا طریقہ:** کوئی بھی کوڈ فائل کھولیں اور تجویز کردہ سوالات Copilot سے پوچھیں۔ اسے پوری کوڈبیس کا مکمل سیاق و سباق معلوم ہے اور یہ وضاحت، توسیع، اور متبادل تجاویز دے سکتا ہے۔

مزید جاننا چاہتے ہیں؟ [AI جوڑواں پروگرامنگ کے لیے کوپائلٹ](https://aka.ms/GitHubCopilotAI) دیکھیں۔

## اضافی وسائل

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
 
### کور لرننگ
[![مشین لرننگ برائے ابتدائی](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![ڈیٹا سائنس برائے ابتدائی](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI برائے ابتدائی](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![سائبرسیکیورٹی برائے ابتدائی](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![ویب ڈیولپمنٹ برائے ابتدائی](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT برائے ابتدائی](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR ڈیولپمنٹ برائے ابتدائی](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### کوپائلٹ سیریز
[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## مدد حاصل کرنا

اگر آپ پھنس جائیں یا AI ایپس بنانے کے بارے میں کوئی سوالات ہوں، تو شامل ہوں:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

اگر آپ کے پاس پروڈکٹ فیڈبیک ہے یا تعمیر کے دوران غلطیاں ہوں تو وزٹ کریں:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## لائسنس

MIT لائسنس - مزید تفصیلات کے لیے [LICENSE](../../LICENSE) فائل دیکھیں۔

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**دستخطی دستبرداری**:  
یہ دستاویز اے آئی ترجمہ سروس [Co-op Translator](https://github.com/Azure/co-op-translator) کا استعمال کرتے ہوئے ترجمہ کی گئی ہے۔ اگرچہ ہم درستگی کے لیے کوشاں ہیں، براہ کرم اس بات سے آگاہ رہیں کہ خودکار ترجمے میں غلطیاں یا بے ضابطہ معلومات ہو سکتی ہیں۔ اصل دستاویز اپنی مادری زبان میں ہی معتبر ذریعہ سمجھی جانی چاہیے۔ اہم معلومات کے لیے پیشہ ور انسانی ترجمہ تجویز کیا جاتا ہے۔ اس ترجمے کے استعمال سے ہونے والی کسی بھی غلط فہمی یا بدفہمی کی ذمہ داری ہم پر نہیں ہوگی۔
<!-- CO-OP TRANSLATOR DISCLAIMER END -->