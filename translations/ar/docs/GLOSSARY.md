<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T20:20:13+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ar"
}
-->
# مسرد LangChain4j

## جدول المحتويات

- [المفاهيم الأساسية](../../../docs)
- [مكونات LangChain4j](../../../docs)
- [مفاهيم AI/ML](../../../docs)
- [هندسة المطالبات - [الوحدة 02](../02-prompt-engineering/README.md)](#prompt-engineering---module-02)
- [RAG (التوليد المعزز بالاسترجاع) - [الوحدة 03](../03-rag/README.md)](#rag-retrieval-augmented-generation---module-03)
- [الوكلاء والأدوات - [الوحدة 04](../04-tools/README.md)](#agents-and-tools---module-04)
- [بروتوكول سياق النموذج (MCP) - [الوحدة 05](../05-mcp/README.md)](#model-context-protocol-mcp---module-05)
- [خدمات Azure - [الوحدة 01](../01-introduction/README.md)](#azure-services---module-01)
- [الاختبار والتطوير - [دليل الاختبار](TESTING.md)](#testing-and-development---testing-guide)

مرجع سريع للمصطلحات والمفاهيم المستخدمة في جميع أنحاء الدورة.

## Core Concepts

**AI Agent** - نظام يستخدم الذكاء الاصطناعي للاستدلال والتصرف بشكل مستقل. [الوحدة 04](../04-tools/README.md)

**Chain** - سلسلة من العمليات حيث تغذي المخرجات الخطوة التالية.

**Chunking** - تقسيم المستندات إلى أجزاء أصغر. المعتاد: 300-500 توكن مع تداخل. [الوحدة 03](../03-rag/README.md)

**Context Window** - الحد الأقصى لعدد التوكنات التي يمكن للنموذج معالجتها. GPT-5: 400K توكن.

**Embeddings** - متجهات رقمية تمثل معنى النص. [الوحدة 03](../03-rag/README.md)

**Function Calling** - النموذج يولد طلبات مُهيكلة لاستدعاء دوال خارجية. [الوحدة 04](../04-tools/README.md)

**Hallucination** - عندما تنتج النماذج معلومات غير صحيحة لكنها تبدو معقولة.

**Prompt** - نص الإدخال لنموذج اللغة. [الوحدة 02](../02-prompt-engineering/README.md)

**Semantic Search** - البحث حسب المعنى باستخدام المتجهات، وليس الكلمات المفتاحية. [الوحدة 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: لا ذاكرة. Stateful: يحتفظ بتاريخ المحادثة. [الوحدة 01](../01-introduction/README.md)

**Tokens** - وحدات النص الأساسية التي تعالجها النماذج. تؤثر على التكاليف والقيود. [الوحدة 01](../01-introduction/README.md)

**Tool Chaining** - تنفيذ أدوات متسلسل حيث تُعلم المخرجات الاستدعاء التالي. [الوحدة 04](../04-tools/README.md)

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

**Few-Shot Learning** - توفير أمثلة داخل المطالبات. [الوحدة 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - نماذج ذكاء اصطناعي مدربة على كميات ضخمة من النصوص.

**Reasoning Effort** - معامل GPT-5 الذي يتحكم في عمق التفكير. [الوحدة 02](../02-prompt-engineering/README.md)

**Temperature** - يتحكم في عشوائية المخرجات. منخفض = حتمي، مرتفع = إبداعي.

**Vector Database** - قاعدة بيانات متخصصة للمتجهات. [الوحدة 03](../03-rag/README.md)

**Zero-Shot Learning** - أداء مهام بدون أمثلة. [الوحدة 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [الوحدة 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - التفكير خطوة بخطوة لتحسين الدقة.

**Constrained Output** - فرض تنسيق أو بنية محددة.

**High Eagerness** - نمط GPT-5 للتفكير المتعمق والشامل.

**Low Eagerness** - نمط GPT-5 للإجابات السريعة.

**Multi-Turn Conversation** - الحفاظ على السياق عبر تبادلات متعددة.

**Role-Based Prompting** - ضبط شخصية النموذج عبر رسائل النظام.

**Self-Reflection** - النموذج يقيم ويحسن مخرجاته.

**Structured Analysis** - إطار تقييم ثابت.

**Task Execution Pattern** - التخطيط → التنفيذ → التلخيص.

## RAG (Retrieval-Augmented Generation) - [الوحدة 03](../03-rag/README.md)

**Document Processing Pipeline** - التحميل → التقسيم → التضمين → التخزين.

**In-Memory Embedding Store** - تخزين غير دائم للاختبار.

**RAG** - يجمع بين الاسترجاع والتوليد لإسناد الإجابات.

**Similarity Score** - مقياس (0-1) للتشابه الدلالي.

**Source Reference** - بيانات وصفية حول المحتوى المسترجع.

## Agents and Tools - [الوحدة 04](../04-tools/README.md)

**@Tool Annotation** - يعلّم طرق Java لتكون أدوات قابلة للاستدعاء من الذكاء الاصطناعي.

**ReAct Pattern** - Reason → Act → Observe → Repeat.

**Session Management** - سياقات منفصلة لمستخدمين مختلفين.

**Tool** - دالة يمكن للوكيل الذكي استدعاؤها.

**Tool Description** - توثيق غرض الأداة ومعاملاتها.

## Model Context Protocol (MCP) - [الوحدة 05](../05-mcp/README.md)

**MCP** - معيار لربط تطبيقات الذكاء الاصطناعي بالأدوات الخارجية.

**MCP Client** - تطبيق يتصل بخوادم MCP.

**MCP Server** - خدمة تعرض الأدوات عبر MCP.

**Stdio Transport** - الخادم كعملية فرعية عبر stdin/stdout.

**Tool Discovery** - العميل يستعلم الخادم عن الأدوات المتاحة.

## Azure Services - [الوحدة 01](../01-introduction/README.md)

**Azure AI Search** - بحث سحابي مع قدرات متجهية. [الوحدة 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - ينشر موارد Azure.

**Azure OpenAI** - خدمة مايكروسوفت للمؤسسات في مجال الذكاء الاصطناعي.

**Bicep** - لغة البنية التحتية كرمز في Azure. [دليل البنية التحتية](../01-introduction/infra/README.md)

**Deployment Name** - اسم نشر النموذج في Azure.

**GPT-5** - أحدث نموذج OpenAI مع تحكم في الاستدلال. [الوحدة 02](../02-prompt-engineering/README.md)

## Testing and Development - [دليل الاختبار](TESTING.md)

**Dev Container** - بيئة تطوير معزولة بالحاويات. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - ملعب مجاني لنماذج الذكاء الاصطناعي. [الوحدة 00](../00-quick-start/README.md)

**In-Memory Testing** - اختبار باستخدام تخزين في الذاكرة.

**Integration Testing** - اختبار باستخدام البنية التحتية الحقيقية.

**Maven** - أداة أتمتة بناء Java.

**Mockito** - إطار عمل محاكاة لـ Java.

**Spring Boot** - إطار تطبيقات Java. [الوحدة 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
إخلاء المسؤولية:
تمت ترجمة هذا المستند باستخدام خدمة الترجمة الآلية Co-op Translator (https://github.com/Azure/co-op-translator). على الرغم من أننا نسعى إلى الدقة، يُرجى العلم أن الترجمات الآلية قد تحتوي على أخطاء أو عدم دقة. يجب اعتبار المستند الأصلي بلغته الأصلية هو المصدر المعتمد. للمعلومات الحرجة، يُنصح باللجوء إلى ترجمة بشرية محترفة. لسنا مسؤولين عن أي سوء فهم أو تفسيرات خاطئة تنشأ عن استخدام هذه الترجمة.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->