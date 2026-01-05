<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T20:34:59+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "fa"
}
-->
# واژه‌نامه LangChain4j

## فهرست مطالب

- [مفاهیم اصلی](../../../docs)
- [اجزای LangChain4j](../../../docs)
- [مفاهیم AI/ML](../../../docs)
- [مهندسی پرامپت](../../../docs)
- [RAG (تولید تقویت‌شده با بازیابی)](../../../docs)
- [عامل‌ها و ابزارها](../../../docs)
- [پروتکل کانتکست مدل (MCP)](../../../docs)
- [خدمات Azure](../../../docs)
- [تست و توسعه](../../../docs)

مرجع سریع برای اصطلاحات و مفاهیمی که در سراسر دوره استفاده شده‌اند.

## Core Concepts

**AI Agent** - سیستمی که از هوش مصنوعی برای استنتاج و انجام اقدامات به‌طور خودکار استفاده می‌کند. [ماژول 04](../04-tools/README.md)

**Chain** - توالی عملیات که خروجی یک مرحله به مرحله بعدی داده می‌شود.

**Chunking** - شکستن سندها به قطعات کوچکتر. معمولاً: 300-500 توکن با همپوشانی. [ماژول 03](../03-rag/README.md)

**Context Window** - حداکثر توکنی که یک مدل می‌تواند پردازش کند. GPT-5: 400K توکن.

**Embeddings** - بردارهای عددی که مفهوم متن را نمایش می‌دهند. [ماژول 03](../03-rag/README.md)

**Function Calling** - مدلی که درخواست‌های ساختاریافته برای فراخوانی توابع خارجی تولید می‌کند. [ماژول 04](../04-tools/README.md)

**Hallucination** - زمانی که مدل‌ها اطلاعات نادرست اما محتمل تولید می‌کنند.

**Prompt** - ورودی متنی به یک مدل زبانی. [ماژول 02](../02-prompt-engineering/README.md)

**Semantic Search** - جستجو بر اساس معنی با استفاده از embeddings، نه کلمات کلیدی. [ماژول 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: بدون حافظه. Stateful: تاریخچه گفتگو را حفظ می‌کند. [ماژول 01](../01-introduction/README.md)

**Tokens** - واحدهای پایه متن که مدل‌ها پردازش می‌کنند. بر هزینه‌ها و محدودیت‌ها تأثیر می‌گذارد. [ماژول 01](../01-introduction/README.md)

**Tool Chaining** - اجرای متوالی ابزارها که خروجی یکی فراخوانی بعدی را اطلاع‌رسانی می‌کند. [ماژول 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Creates type-safe AI service interfaces.

**OpenAiOfficialChatModel** - Unified client for OpenAI and Azure OpenAI models.

**OpenAiOfficialEmbeddingModel** - Creates embeddings using OpenAI Official client (supports both OpenAI and Azure OpenAI).

**ChatModel** - Core interface for language models.

**ChatMemory** - Maintains conversation history.

**ContentRetriever** - Finds relevant document chunks for RAG.

**DocumentSplitter** - Breaks documents into chunks.

**EmbeddingModel** - Converts text into numerical vectors.

**EmbeddingStore** - Stores and retrieves embeddings.

**MessageWindowChatMemory** - Maintains sliding window of recent messages.

**PromptTemplate** - Creates reusable prompts with `{{variable}}` placeholders.

**TextSegment** - Text chunk with metadata. Used in RAG.

**ToolExecutionRequest** - Represents tool execution request.

**UserMessage / AiMessage / SystemMessage** - Conversation message types.

## AI/ML Concepts

**Few-Shot Learning** - ارائه مثال‌ها در پرامپت‌ها. [ماژول 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - مدل‌های هوش مصنوعی که روی مقادیر زیادی از داده‌های متنی آموزش دیده‌اند.

**Reasoning Effort** - پارامتری در GPT-5 که عمق تفکر را کنترل می‌کند. [ماژول 02](../02-prompt-engineering/README.md)

**Temperature** - کنترل تصادفی بودن خروجی. پایین = قطعی، بالا = خلاقانه.

**Vector Database** - پایگاه‌داده تخصصی برای embeddings. [ماژول 03](../03-rag/README.md)

**Zero-Shot Learning** - انجام وظایف بدون مثال‌ها. [ماژول 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [ماژول 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - استدلال گام‌به‌گام برای دقت بهتر.

**Constrained Output** - اِعمال فرمت یا ساختار خاص روی خروجی.

**High Eagerness** - الگوی GPT-5 برای استدلال جامع.

**Low Eagerness** - الگوی GPT-5 برای پاسخ‌های سریع.

**Multi-Turn Conversation** - حفظ زمینه در تبادلات متعدد.

**Role-Based Prompting** - تنظیم شخصیت مدل از طریق پیام‌های سیستمی.

**Self-Reflection** - مدل خروجی را ارزیابی و بهبود می‌بخشد.

**Structured Analysis** - چارچوب ارزیابی ثابت.

**Task Execution Pattern** - برنامه‌ریزی → اجرا → خلاصه‌سازی.

## RAG (Retrieval-Augmented Generation) - [ماژول 03](../03-rag/README.md)

**Document Processing Pipeline** - بارگذاری → تقسیم → تبدیل به بردار → ذخیره.

**In-Memory Embedding Store** - ذخیره‌سازی غیرپایدار برای تست.

**RAG** - ترکیب بازیابی با تولید برای پایه‌گذاری پاسخ‌ها.

**Similarity Score** - معیار (0-1) شباهت معنایی.

**Source Reference** - فراداده درباره محتوای بازیابی‌شده.

## Agents and Tools - [ماژول 04](../04-tools/README.md)

**@Tool Annotation** - Marks Java methods as AI-callable tools.

**ReAct Pattern** - استدلال → عمل → مشاهده → تکرار.

**Session Management** - زمینه‌های جداگانه برای کاربران مختلف.

**Tool** - تابعی که یک عامل هوش مصنوعی می‌تواند فراخوانی کند.

**Tool Description** - مستندسازی هدف ابزار و پارامترهای آن.

## Model Context Protocol (MCP) - [ماژول 05](../05-mcp/README.md)

**MCP** - استانداردی برای اتصال برنامه‌های هوش مصنوعی به ابزارهای خارجی.

**MCP Client** - برنامه‌ای که به سرورهای MCP متصل می‌شود.

**MCP Server** - سرویس ارائه‌دهنده ابزارها از طریق MCP.

**Stdio Transport** - سرور به‌عنوان زیرفرآیند از طریق stdin/stdout.

**Tool Discovery** - کلاینت ابزارهای موجود را از سرور پرس‌وجو می‌کند.

## Azure Services - [ماژول 01](../01-introduction/README.md)

**Azure AI Search** - جستجوی ابری با قابلیت‌های برداری. [ماژول 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - مستقرسازی منابع Azure.

**Azure OpenAI** - سرویس سازمانی هوش مصنوعی مایکروسافت.

**Bicep** - زبان Infrastructure-as-Code برای Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - نام استقرار مدل در Azure.

**GPT-5** - جدیدترین مدل OpenAI با کنترل استدلال. [ماژول 02](../02-prompt-engineering/README.md)

## Testing and Development - [راهنمای تست](TESTING.md)

**Dev Container** - محیط توسعه کانتینری. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - میدان بازی مدل‌های رایگان AI. [ماژول 00](../00-quick-start/README.md)

**In-Memory Testing** - تست با ذخیره‌سازی در حافظه.

**Integration Testing** - تست با زیرساخت واقعی.

**Maven** - ابزار اتوماسیون ساخت جاوا.

**Mockito** - فریم‌ورک شبیه‌سازی جاوا.

**Spring Boot** - فریم‌ورک برنامه‌نویسی جاوا. [ماژول 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
سلب مسئولیت:
این سند با استفاده از سرویس ترجمهٔ هوش مصنوعی Co‑op Translator (https://github.com/Azure/co-op-translator) ترجمه شده است. در حالی که ما در تلاش برای دقت هستیم، لطفاً توجه داشته باشید که ترجمه‌های خودکار ممکن است حاوی خطا یا نادرستی باشند. نسخهٔ اصلی سند به زبان اصلی‌اش باید به‌عنوان منبع معتبر و مرجع در نظر گرفته شود. برای اطلاعات حیاتی، استفاده از ترجمهٔ حرفه‌ای انسانی توصیه می‌شود. ما در قبال هرگونه سوءتفاهم یا تفسیر نادرستی که از استفاده از این ترجمه ناشی شود مسئولیتی نداریم.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->