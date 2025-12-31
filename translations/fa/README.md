<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "1e85afe0b0ee47fc09b20442b0ee4ca5",
  "translation_date": "2025-12-23T08:27:20+00:00",
  "source_file": "README.md",
  "language_code": "fa"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b506e9588f734989dd106ebd9f977b7f784941a28b88348f0d6.fa.png" alt="LangChain4j" width="800"/>

### 🌐 پشتیبانی چندزبانه

#### پشتیبانی‌شده از طریق GitHub Action (خودکار و همیشه به‌روز)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[عربی](../ar/README.md) | [بنگالی](../bn/README.md) | [بلغاری](../bg/README.md) | [برمه‌ای (میانمار)](../my/README.md) | [چینی (ساده‌شده)](../zh/README.md) | [چینی (سنتی، هنگ کنگ)](../hk/README.md) | [چینی (سنتی، ماکائو)](../mo/README.md) | [چینی (سنتی، تایوان)](../tw/README.md) | [کرواتی](../hr/README.md) | [چکی](../cs/README.md) | [دانمارکی](../da/README.md) | [هلندی](../nl/README.md) | [استونیایی](../et/README.md) | [فنلاندی](../fi/README.md) | [فرانسوی](../fr/README.md) | [آلمانی](../de/README.md) | [یونانی](../el/README.md) | [عبری](../he/README.md) | [هندی](../hi/README.md) | [مجارستانی](../hu/README.md) | [اندونزیایی](../id/README.md) | [ایتالیایی](../it/README.md) | [ژاپنی](../ja/README.md) | [کانادا](../kn/README.md) | [کره‌ای](../ko/README.md) | [لیتوانیایی](../lt/README.md) | [مالایی](../ms/README.md) | [مالایالام](../ml/README.md) | [مراتی](../mr/README.md) | [نپالی](../ne/README.md) | [پیدژ نیجریه‌ای](../pcm/README.md) | [نروژی](../no/README.md) | [فارسی (Farsi)](./README.md) | [لهستانی](../pl/README.md) | [پرتغالی (برزیل)](../br/README.md) | [پرتغالی (پرتغال)](../pt/README.md) | [پنجابی (گورمخی)](../pa/README.md) | [رومانیایی](../ro/README.md) | [روسی](../ru/README.md) | [صربی (سیریلیک)](../sr/README.md) | [اسلواکیایی](../sk/README.md) | [اسلوونیایی](../sl/README.md) | [اسپانیایی](../es/README.md) | [سواحلی](../sw/README.md) | [سوئدی](../sv/README.md) | [تاگالوگ (فیلیپینی)](../tl/README.md) | [تامیل](../ta/README.md) | [تلوگو](../te/README.md) | [تایلندی](../th/README.md) | [ترکی](../tr/README.md) | [اوکراینی](../uk/README.md) | [اردو](../ur/README.md) | [ویتنامی](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j برای مبتدیان

یک دوره برای ساخت برنامه‌های هوش مصنوعی با LangChain4j و Azure OpenAI GPT-5، از چت پایه تا عامل‌های هوش مصنوعی.

**با LangChain4j تازه‌کار هستید؟** به [واژه‌نامه](docs/GLOSSARY.md) برای تعریف اصطلاحات و مفاهیم کلیدی مراجعه کنید.

## فهرست مطالب

1. [Quick Start](00-quick-start/README.md) - شروع به کار با LangChain4j
2. [Introduction](01-introduction/README.md) - یادگیری مبانی LangChain4j
3. [Prompt Engineering](02-prompt-engineering/README.md) - تسلط بر طراحی مؤثر پرامپت
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - ساخت سیستم‌های هوشمند مبتنی بر دانش
5. [Tools](04-tools/README.md) - ادغام ابزارها و APIهای خارجی با عامل‌های هوش مصنوعی
6. [MCP (Model Context Protocol)](05-mcp/README.md) - کار با پروتکل زمینه مدل
---

##  مسیر یادگیری

> **شروع سریع**

1. این مخزن را به حساب GitHub خود فورک کنید
2. روی **Code** → تب **Codespaces** → **...** → **New with options...** کلیک کنید
3. از تنظیمات پیش‌فرض استفاده کنید – این کار کانتینر توسعه‌ای را که برای این دوره ایجاد شده انتخاب می‌کند
4. روی **Create codespace** کلیک کنید
5. ۵–۱۰ دقیقه منتظر بمانید تا محیط آماده شود
6. مستقیماً به [شروع سریع](./00-quick-start/README.md) بروید تا شروع کنید!

> **ترجیح می‌دهید محلی کلون کنید؟**
>
> این مخزن شامل بیش از ۵۰ ترجمه زبان است که اندازه دانلود را به‌طور قابل‌توجهی افزایش می‌دهد. برای کلون بدون ترجمه‌ها، از sparse checkout استفاده کنید:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> این به شما همه چیز لازم برای تکمیل دوره را با دانلودی بسیار سریع‌تر می‌دهد.

با ماژول [شروع سریع](00-quick-start/README.md) شروع کنید و در هر ماژول پیش بروید تا مهارت‌های خود را گام‌به‌گام بسازید. شما مثال‌های پایه‌ای را امتحان خواهید کرد تا مبانی را بفهمید قبل از اینکه به ماژول [مقدمه](01-introduction/README.md) برای بررسی عمیق‌تر با GPT-5 بروید.

<img src="../../translated_images/learning-path.ac2da6720e77c3165960835627cef4c20eb2afb103be73a4f25b6d8fafbd738d.fa.png" alt="مسیر یادگیری" width="800"/>

پس از تکمیل ماژول‌ها، به [راهنمای تست](docs/TESTING.md) مراجعه کنید تا مفاهیم تست LangChain4j را در عمل ببینید.

> **توجه:** این آموزش هم از GitHub Models و هم از Azure OpenAI استفاده می‌کند. ماژول‌های [شروع سریع](00-quick-start/README.md) و [MCP](05-mcp/README.md) از GitHub Models استفاده می‌کنند (نیاز به اشتراک Azure نیست)، در حالی که ماژول‌های ۱–۴ از Azure OpenAI GPT-5 استفاده می‌کنند.


## یادگیری با GitHub Copilot

برای شروع سریع کدنویسی، این پروژه را در یک GitHub Codespace یا IDE محلی خود با devcontainer ارائه‌شده باز کنید. devcontainer استفاده‌شده در این دوره از پیش با GitHub Copilot برای برنامه‌نویسی جفت‌شده با هوش مصنوعی پیکربندی شده است.

هر مثال کد شامل سوالات پیشنهادی است که می‌توانید از GitHub Copilot بپرسید تا درک خود را عمیق‌تر کنید. به دنبال نشانک‌های 💡/🤖 در:

- **هدرهای فایل Java** - سوالات خاص برای هر مثال
- **README‌های ماژول** - پیشنهادهای اکتشافی پس از مثال‌های کد

**نحوه استفاده:** هر فایل کد را باز کنید و سوالات پیشنهادی را از Copilot بپرسید. Copilot دارای درک کامل از کدبیس است و می‌تواند توضیح دهد، گسترش دهد و گزینه‌های جایگزین پیشنهاد کند.

می‌خواهید بیشتر بدانید؟ به [Copilot برای برنامه‌نویسی جفت‌شده با هوش مصنوعی](https://aka.ms/GitHubCopilotAI) مراجعه کنید.


## منابع اضافی

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j برای مبتدیان](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js برای مبتدیان](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / عامل‌ها
[![AZD برای مبتدیان](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI برای مبتدیان](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP برای مبتدیان](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![عامل‌های هوش مصنوعی برای مبتدیان](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### سری هوش مولد
[![هوش مولد برای مبتدیان](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![هوش مولد (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![هوش مولد (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![هوش مولد (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### یادگیری پایه‌ای
[![یادگیری ماشین برای مبتدیان](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![علم داده برای مبتدیان](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![هوش مصنوعی برای مبتدیان](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![امنیت سایبری برای مبتدیان](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![توسعه وب برای مبتدیان](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![اینترنت اشیاء برای مبتدیان](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)

[![توسعه XR برای مبتدیان](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### مجموعه Copilot
[![Copilot برای برنامه‌نویسی جفتی با هوش مصنوعی](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot برای C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![ماجراجویی Copilot](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## دریافت کمک

اگر گیر کردید یا سوالی در مورد ساخت برنامه‌های هوش مصنوعی دارید، بپیوندید:

[![Discord انجمن Azure AI Foundry](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

اگر بازخوردی دربارهٔ محصول دارید یا هنگام ساخت با خطا مواجه شدید، مراجعه کنید:

[![انجمن توسعه‌دهندگان Azure AI Foundry](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## مجوز

مجوز MIT - برای جزئیات به فایل [LICENSE](../../LICENSE) مراجعه کنید.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
سلب مسئولیت:
این سند با استفاده از سرویس ترجمه‌ی هوش مصنوعی [Co‑op Translator](https://github.com/Azure/co-op-translator) ترجمه شده است. در حالی که ما در تلاش برای دقت هستیم، لطفاً توجه داشته باشید که ترجمه‌های خودکار ممکن است حاوی خطاها یا نادرستی‌هایی باشند. نسخهٔ اصلی سند به زبان مبداء باید به‌عنوان منبع معتبر در نظر گرفته شود. برای اطلاعات حساس یا حیاتی، ترجمهٔ حرفه‌ای انسانی توصیه می‌شود. ما در قبال هرگونه سوءتفاهم یا تفسیر نادرست ناشی از استفاده از این ترجمه مسئولیتی نداریم.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->