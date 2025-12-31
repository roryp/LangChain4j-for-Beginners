<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T20:54:02+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ur"
}
-->
# LangChain4j لغت

## مواد کی فہرست

- [بنیادی تصورات](../../../docs)
- [LangChain4j اجزاء](../../../docs)
- [AI/ML تصورات](../../../docs)
- [پرامپٹ انجینئرنگ - [ماڈیول 02](../02-prompt-engineering/README.md)](#prompt-engineering---module-02)
- [RAG (Retrieval-Augmented Generation) - [ماڈیول 03](../03-rag/README.md)](#rag-retrieval-augmented-generation---module-03)
- [ایجنٹس اور ٹولز - [ماڈیول 04](../04-tools/README.md)](#agents-and-tools---module-04)
- [ماڈل کانٹیکسٹ پروٹوکول (MCP) - [ماڈیول 05](../05-mcp/README.md)](#model-context-protocol-mcp---module-05)
- [Azure خدمات - [ماڈیول 01](../01-introduction/README.md)](#azure-services---module-01)
- [ٹیسٹنگ اور ڈیولپمنٹ - [رہنمائے ٹیسٹنگ](TESTING.md)](#testing-and-development---testing-guide)

کورس میں استعمال ہونے والی اصطلاحات اور تصورات کے لیے جلدی حوالہ۔

## بنیادی تصورات

**AI ایجنٹ** - ایسا نظام جو خودمختاری کے ساتھ استدلال اور کارروائی کے لیے AI کا استعمال کرتا ہے۔ [ماڈیول 04](../04-tools/README.md)

**Chain** - آپریشنز کا ایک سلسلہ جس میں ایک مرحلے کا آؤٹ پٹ اگلے مرحلے میں داخل کیا جاتا ہے۔

**Chunking** - دستاویزات کو چھوٹے حصوں میں تقسیم کرنا۔ عام طور پر: اوور لیپ کے ساتھ 300-500 ٹوکن۔ [ماڈیول 03](../03-rag/README.md)

**Context Window** - زیادہ سے زیادہ ٹوکن جو کوئی ماڈل پروسیس کر سکتا ہے۔ GPT-5: 400K ٹوکن۔

**Embeddings** - متن کے معنی کی نمائندگی کرنے والے عددی ویکٹرز۔ [ماڈیول 03](../03-rag/README.md)

**Function Calling** - ماڈل بیرونی فنکشنز کو کال کرنے کے لیے ساختی درخواستیں بناتا ہے۔ [ماڈیول 04](../04-tools/README.md)

**Hallucination** - جب ماڈلز غلط مگر ممکنہ معلومات پیدا کرتے ہیں۔

**Prompt** - زبان کے ماڈل کو دیا جانے والا متن۔ [ماڈیول 02](../02-prompt-engineering/README.md)

**Semantic Search** - معنی کی بنیاد پر تلاش کرنا، ایمبیڈنگز استعمال کرتے ہوئے، کی ورڈز نہیں۔ [ماڈیول 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: کوئی میموری نہیں۔ Stateful: گفتگو کی تاریخ برقرار رکھتا ہے۔ [ماڈیول 01](../01-introduction/README.md)

**Tokens** - بنیادی متن یونٹس جو ماڈلز پروسیس کرتے ہیں۔ قیمتوں اور حدود پر اثر انداز ہوتے ہیں۔ [ماڈیول 01](../01-introduction/README.md)

**Tool Chaining** - تسلسل وار ٹولز کی تکمیل جہاں ایک آؤٹ پٹ اگلی کال کو مطلع کرتی ہے۔ [ماڈیول 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - ٹائپ-سیف AI سروس انٹرفیسز بناتا ہے۔

**OpenAiOfficialChatModel** - OpenAI اور Azure OpenAI ماڈلز کے لیے متحدہ کلائنٹ۔

**OpenAiOfficialEmbeddingModel** - OpenAI Official کلائنٹ استعمال کرتے ہوئے ایمبیڈنگز بناتا ہے (دونوں OpenAI اور Azure OpenAI کو سپورٹ کرتا ہے)۔

**ChatModel** - زبان کے ماڈلز کے لیے مرکزی انٹرفیس۔

**ChatMemory** - گفتگو کی تاریخ برقرار رکھتا ہے۔

**ContentRetriever** - RAG کے لیے متعلقہ دستاویزی چنکس تلاش کرتا ہے۔

**DocumentSplitter** - دستاویزات کو چنکس میں تقسیم کرتا ہے۔

**EmbeddingModel** - متن کو عددی ویکٹرز میں تبدیل کرتا ہے۔

**EmbeddingStore** - ایمبیڈنگز کو ذخیرہ اور بازیافت کرتا ہے۔

**MessageWindowChatMemory** - تازہ پیغامات کی سلائیڈنگ ونڈو برقرار رکھتا ہے۔

**PromptTemplate** - دوبارہ قابل استعمال پرامپٹس بناتا ہے جن میں `{{variable}}` پلیس ہولڈرز ہوتے ہیں۔

**TextSegment** - میٹا ڈیٹا کے ساتھ متن کا چنک۔ RAG میں استعمال ہوتا ہے۔

**ToolExecutionRequest** - ٹول کے نفاذ کی درخواست کی نمائندگی کرتا ہے۔

**UserMessage / AiMessage / SystemMessage** - گفتگو کے پیغام کی اقسام۔

## AI/ML تصورات

**Few-Shot Learning** - پرامپٹس میں مثالیں فراہم کرنا۔ [ماڈیول 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - وسیع متن ڈیٹا پر تربیت یافتہ AI ماڈلز۔

**Reasoning Effort** - GPT-5 پیرامیٹر جو سوچ کی گہرائی کنٹرول کرتا ہے۔ [ماڈیول 02](../02-prompt-engineering/README.md)

**Temperature** - آؤٹ پٹ کی بے ترتیبیت کو کنٹرول کرتا ہے۔ کم=متعین، زیادہ=تخلیقی۔

**Vector Database** - ایمبیڈنگز کے لیے مخصوص ڈیٹا بیس۔ [ماڈیول 03](../03-rag/README.md)

**Zero-Shot Learning** - بغیر مثالوں کے کام انجام دینا۔ [ماڈیول 02](../02-prompt-engineering/README.md)

## پرامپٹ انجینئرنگ - [ماڈیول 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - بہتر درستگی کے لیے قدم بہ قدم استدلال۔

**Constrained Output** - مخصوص فارمیٹ یا ڈھانچے کو نافذ کرنا۔

**High Eagerness** - GPT-5 کا نمونہ جو جامع استدلال کے لیے ہے۔

**Low Eagerness** - GPT-5 کا نمونہ جو تیز جوابات کے لیے ہے۔

**Multi-Turn Conversation** - تبادلے کے دوران کانٹیکسٹ برقرار رکھنا۔

**Role-Based Prompting** - سسٹم پیغامات کے ذریعے ماڈل کا کردار مقرر کرنا۔

**Self-Reflection** - ماڈل اپنے آؤٹ پٹ کا جائزہ لے کر اسے بہتر بناتا ہے۔

**Structured Analysis** - ایک مقررہ تشخیصی فریم ورک۔

**Task Execution Pattern** - منصوبہ → انجام دیں → خلاصہ۔

## RAG (Retrieval-Augmented Generation) - [ماڈیول 03](../03-rag/README.md)

**Document Processing Pipeline** - لوڈ → چنک کریں → ایمبیڈ کریں → اسٹور کریں۔

**In-Memory Embedding Store** - ٹیسٹنگ کے لیے غیر مستقل اسٹوریج۔

**RAG** - بازیافت کو جنریشن کے ساتھ ملا کر جوابات کو حقیقت پر مبنی بناتا ہے۔

**Similarity Score** - معنوی مشابہت کی پیمائش (0-1)۔

**Source Reference** - بازیافت شدہ مواد کے بارے میں میٹاڈیٹا۔

## ایجنٹس اور ٹولز - [ماڈیول 04](../04-tools/README.md)

**@Tool Annotation** - Java میتھڈز کو AI-قابلِ کال ٹولز کے طور پر نشان زد کرتا ہے۔

**ReAct Pattern** - استدلال → عمل → مشاہدہ → دہرائیں۔

**Session Management** - مختلف صارفین کے لیے الگ کانٹیکسٹس۔

**Tool** - وہ فنکشن جسے AI ایجنٹ کال کر سکتا ہے۔

**Tool Description** - ٹول کے مقصد اور پیرامیٹرز کی دستاویزات۔

## ماڈل کانٹیکسٹ پروٹوکول (MCP) - [ماڈیول 05](../05-mcp/README.md)

**MCP** - AI ایپس کو بیرونی ٹولز سے جوڑنے کے لیے ایک اسٹینڈرڈ۔

**MCP Client** - وہ ایپلیکیشن جو MCP سرورز سے جڑتی ہے۔

**MCP Server** - وہ سروس جو MCP کے ذریعے ٹولز کو ایکسپوز کرتی ہے۔

**Stdio Transport** - سرور بطور subprocess stdin/stdout کے ذریعے۔

**Tool Discovery** - کلائنٹ دستیاب ٹولز کے بارے میں سرور سے پوچھتا ہے۔

## Azure خدمات - [ماڈیول 01](../01-introduction/README.md)

**Azure AI Search** - ویکٹر صلاحیتوں کے ساتھ کلاؤڈ سرچ۔ [ماڈیول 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure وسائل کو ڈیپلائے کرتا ہے۔

**Azure OpenAI** - مائیکروسافٹ کی ادارہ جاتی AI سروس۔

**Bicep** - Azure infrastructure-as-code زبان۔ [رہنمائے انفراسٹرکچر](../01-introduction/infra/README.md)

**Deployment Name** - Azure میں ماڈل کی ڈپلائمنٹ کے لیے نام۔

**GPT-5** - تازہ ترین OpenAI ماڈل جس میں استدلال کنٹرول ہے۔ [ماڈیول 02](../02-prompt-engineering/README.md)

## ٹیسٹنگ اور ڈیولپمنٹ - [ٹیسٹنگ گائیڈ](TESTING.md)

**Dev Container** - کنٹینرائزڈ ڈویلپمنٹ ماحول۔ [ترتیب](../../../.devcontainer/devcontainer.json)

**GitHub Models** - مفت AI ماڈل پلے گراؤنڈ۔ [ماڈیول 00](../00-quick-start/README.md)

**In-Memory Testing** - میموری بیسڈ اسٹوریج کے ساتھ ٹیسٹنگ۔

**Integration Testing** - حقیقی انفراسٹرکچر کے ساتھ ٹیسٹنگ۔

**Maven** - Java بلڈ آٹومیشن ٹول۔

**Mockito** - Java مِکنگ فریم ورک۔

**Spring Boot** - Java اپلیکیشن فریم ورک۔ [ماڈیول 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
ذمہ داری سے دستبرداری:
یہ دستاویز AI ترجمہ سروس Co-op Translator (https://github.com/Azure/co-op-translator) کے ذریعے ترجمہ کی گئی ہے۔ اگرچہ ہم درستگی کی کوشش کرتے ہیں، براہِ کرم نوٹ کریں کہ خودکار ترجموں میں غلطیاں یا عدمِ مطابقت ہو سکتی ہیں۔ اصل دستاویز کو اس کی مادری زبان میں معتبر ماخذ سمجھا جانا چاہیے۔ اہم معلومات کے لیے پیشہ ور انسانی ترجمہ تجویز کیا جاتا ہے۔ ہم اس ترجمے کے استعمال سے پیدا ہونے والی کسی بھی غلط فہمی یا غلط تعبیر کے لیے ذمہ دار نہیں ہیں۔
<!-- CO-OP TRANSLATOR DISCLAIMER END -->