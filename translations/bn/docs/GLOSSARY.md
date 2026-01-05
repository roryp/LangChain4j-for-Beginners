<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T22:44:50+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "bn"
}
-->
# LangChain4j অভিধান

## বিষয়সূচি

- [মূল ধারণা](../../../docs)
- [LangChain4j উপাদানসমূহ](../../../docs)
- [AI/ML ধারণা](../../../docs)
- [প্রম্পট ইঞ্জিনিয়ারিং](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [এজেন্ট এবং টুলস](../../../docs)
- [মডেল কনটেক্সট প্রোটোকল (MCP)](../../../docs)
- [Azure সার্ভিসসমূহ](../../../docs)
- [টেস্টিং এবং ডেভেলপমেন্ট](../../../docs)

কোর্স জুড়ে ব্যবহৃত টার্ম এবং ধারণাগুলির দ্রুত রেফারেন্স।

## মূল ধারণা

**AI Agent** - এমন একটি সিস্টেম যা স্বয়ংক্রিয়ভাবে যুক্তি করে এবং কাজ করে AI ব্যবহার করে। [মডিউল 04](../04-tools/README.md)

**Chain** - অপারেশনগুলোর একটি ধারাবাহিকতা যেখানে আউটপুট পরের ধাপে প্রবাহিত হয়।

**Chunking** - ডকুমেন্টগুলোকে ছোট টুকরো করে ভাগ করা। সাধারণত: 300-500 টোকেন ওভারল্যাপসহ। [মডিউল 03](../03-rag/README.md)

**Context Window** - সর্বাধিক টোকেন যা একটি মডেল প্রসেস করতে পারে। GPT-5: 400K টোকেন।

**Embeddings** - টেক্সটের অর্থকে উপস্থাপন করা সংখ্যাসংকেত ভেক্টর। [মডিউল 03](../03-rag/README.md)

**Function Calling** - মডেল বাইরের ফাংশন কল করার জন্য কাঠামোবদ্ধ অনুরোধ তৈরি করে। [মডিউল 04](../04-tools/README.md)

**Hallucination** - মডেল যখন ভুল কিন্তু বিশ্বাসযোগ্য তথ্য তৈরি করে।

**Prompt** - ভাষা মডেলের ইনপুট টেক্সট। [মডিউল 02](../02-prompt-engineering/README.md)

**Semantic Search** - কীওয়ার্ড নয়, এমবেডিং ব্যবহার করে অর্থের ভিত্তিতে সার্চ। [মডিউল 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: কোনো স্মৃতি নেই। Stateful: কথোপকথনের ইতিহাস বজায় রাখে। [মডিউল 01](../01-introduction/README.md)

**Tokens** - মডেলগুলি যে মৌলিক টেক্সট ইউনিটগুলো প্রসেস করে। খরচ এবং সীমার উপরে প্রভাব ফেলে। [মডিউল 01](../01-introduction/README.md)

**Tool Chaining** - ধারাবাহিকভাবে টুল执行 করা যেখানে আউটপুট পরবর্তী কলকে জানায়। [মডিউল 04](../04-tools/README.md)

## LangChain4j উপাদানসমূহ

**AiServices** - টাইপ-সেফ AI সার্ভিস ইন্টারফেস তৈরি করে।

**OpenAiOfficialChatModel** - OpenAI এবং Azure OpenAI মডেলগুলির জন্য একক ক্লায়েন্ট।

**OpenAiOfficialEmbeddingModel** - OpenAI Official ক্লায়েন্ট ব্যবহার করে এমবেডিং তৈরি করে (OpenAI এবং Azure OpenAI উভয় সমর্থন করে)।

**ChatModel** - ভাষা মডেলগুলির জন্য মূল ইন্টারফেস।

**ChatMemory** - কথোপকথনের ইতিহাস বজায় রাখে।

**ContentRetriever** - RAG-এর জন্য প্রাসঙ্গিক ডকুমেন্ট চাংকগুলো খুঁজে বের করে।

**DocumentSplitter** - ডকুমেন্টগুলোকে টুকরো করে।

**EmbeddingModel** - টেক্সটকে সংখ্যাসূচক ভেক্টরে রূপান্তর করে।

**EmbeddingStore** - এমবেডিং সংরক্ষণ এবং পুনরুদ্ধার করে।

**MessageWindowChatMemory** - সাম্প্রতিক মেসেজগুলোর স্লাইডিং উইন্ডো বজায় রাখে।

**PromptTemplate** - `{{variable}}` প্লেসহোল্ডার সহ পুনঃব্যবহারযোগ্য প্রম্পট তৈরি করে।

**TextSegment** - মেটাডেটাসহ টেক্সট চাংক। RAG-এ ব্যবহৃত।

**ToolExecutionRequest** - টুল এক্সিকিউশনের অনুরোধ প্রতিনিধিত্ব করে।

**UserMessage / AiMessage / SystemMessage** - কথোপকথনের মেসেজ টাইপসমূহ।

## AI/ML ধারণা

**Few-Shot Learning** - প্রম্পটে উদাহরণ প্রদান করা। [মডিউল 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - বিস্তৃত টেক্সট ডেটায় ট্রেন করা AI মডেল।

**Reasoning Effort** - চিন্তার গভীরতা নিয়ন্ত্রণ করার GPT-5 প্যারামিটার। [মডিউল 02](../02-prompt-engineering/README.md)

**Temperature** - আউটপুটের র‍্যান্ডমনেস নিয়ন্ত্রণ করে। কম=নির্ধারিত, বেশি=সৃষ্টিশীল।

**Vector Database** - এমবেডিংগুলোর জন্য বিশেষায়িত ডাটাবেস। [মডিউল 03](../03-rag/README.md)

**Zero-Shot Learning** - উদাহরণ ছাড়া কাজ করা। [মডিউল 02](../02-prompt-engineering/README.md)

## প্রম্পট ইঞ্জিনিয়ারিং - [মডিউল 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - ভাল নির্ভুলতার জন্য ধাপে ধাপে যুক্তি।

**Constrained Output** - নির্দিষ্ট ফর্ম্যাট বা কাঠামো বাধ্যতামূলক করা।

**High Eagerness** - ব্যাপক যুক্তির জন্য GPT-5 প্যাটার্ন।

**Low Eagerness** - দ্রুত উত্তর দেওয়ার জন্য GPT-5 প্যাটার্ন।

**Multi-Turn Conversation** - বিনিময়ের মাধ্যমে কনটেক্সট বজায় রাখা।

**Role-Based Prompting** - সিস্টেম মেসেজের মাধ্যমে মডেলের ব্যক্তিত্ব নির্ধারণ করা।

**Self-Reflection** - মডেল তার আউটপুট মূল্যায়ন করে এবং উন্নত করে।

**Structured Analysis** - নির্দিষ্ট মূল্যায়ন কাঠামো।

**Task Execution Pattern** - পরিকল্পনা → কার্যকর → সংক্ষিপ্তসার।

## RAG (Retrieval-Augmented Generation) - [মডিউল 03](../03-rag/README.md)

**Document Processing Pipeline** - লোাড় → চাংক → এমবেড → স্টোর।

**In-Memory Embedding Store** - টেস্টিংয়ের জন্য অস্থায়ী (non-persistent) স্টোরেজ।

**RAG** - রিট্রিভালকে জেনারেশনের সাথে মিলিয়ে উত্তরকে গ্রাউন্ড করে।

**Similarity Score** - অর্থগত সাদৃশ্য পরিমাপ (0-1)।

**Source Reference** - পুনরুদ্ধৃত কনটেন্ট সম্পর্কে মেটাডেটা।

## এজেন্ট এবং টুলস - [মডিউল 04](../04-tools/README.md)

**@Tool Annotation** - Java মেথডগুলোকে AI-কলে সক্ষম টুল হিসেবে চিহ্নিত করে।

**ReAct Pattern** - যুক্তি → ক্রিয়া → পর্যবেক্ষণ → পুনরাবৃত্তি।

**Session Management** - বিভিন্ন ইউজারের জন্য আলাদা প্রসঙ্গ বজায় রাখা।

**Tool** - এমন একটি ফাংশন যা একটি AI এজেন্ট কল করতে পারে।

**Tool Description** - টুলের উদ্দেশ্য এবং প্যারামিটারগুলোর ডকুমেন্টেশন।

## মডেল কনটেক্সট প্রোটোকল (MCP) - [মডিউল 05](../05-mcp/README.md)

**MCP** - AI অ্যাপগুলোকে বাইরের টুলগুলোর সঙ্গে সংযোগ করার স্ট্যান্ডার্ড।

**MCP Client** - MCP সার্ভারের সাথে কানেক্ট করে এমন অ্যাপ্লিকেশন।

**MCP Server** - MCP এর মাধ্যমে টুলসমূহ এক্সপোজ করে এমন সার্ভিস।

**Stdio Transport** - সার্ভারকে subprocess হিসেবে stdin/stdout এর মাধ্যমে চালানো।

**Tool Discovery** - ক্লায়েন্ট উপলব্ধ টুলগুলোর জন্য সার্ভারকে ক্যোয়ারি করে।

## Azure সার্ভিসসমূহ - [মডিউল 01](../01-introduction/README.md)

**Azure AI Search** - ভেক্টর সক্ষমতা সহ ক্লাউড সার্চ। [মডিউল 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure রিসোর্স ডেপ্লয় করে।

**Azure OpenAI** - Microsoft-এর এন্টারপ্রাইজ AI সার্ভিস।

**Bicep** - Azure ইন্ফ্রাস্ট্রাকচার-অ্যাজ-কোড ভাষা। [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azure-এ মডেল ডেপ্লয়মেন্টের নাম।

**GPT-5** - রিজনিং কন্ট্রোল সহ সর্বশেষ OpenAI মডেল। [মডিউল 02](../02-prompt-engineering/README.md)

## টেস্টিং এবং ডেভেলপমেন্ট - [Testing Guide](TESTING.md)

**Dev Container** - কন্টেইনারভিত্তিক ডেভেলপমেন্ট পরিবেশ। [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - বিনামূল্যের AI মডেল প্লেগ্রাউন্ড। [মডিউল 00](../00-quick-start/README.md)

**In-Memory Testing** - ইন-মেমরি স্টোরেজ দিয়ে টেস্টিং।

**Integration Testing** - বাস্তব ইনফ্রাস্ট্রাকচারের সঙ্গে টেস্টিং।

**Maven** - Java বিল্ড অটোমেশন টুল।

**Mockito** - Java মকিং ফ্রেমওয়ার্ক।

**Spring Boot** - Java অ্যাপ্লিকেশন ফ্রেমওয়ার্ক। [মডিউল 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
অস্বীকৃতি:
এই নথিটি AI অনুবাদ পরিষেবা Co-op Translator (https://github.com/Azure/co-op-translator) ব্যবহার করে অনুবাদ করা হয়েছে। আমরা যতটা সম্ভব সঠিকতার চেষ্টা করি, তবুও দয়া করে মনে রাখুন যে স্বয়ংক্রিয় অনুবাদে ত্রুটি বা অসামঞ্জস্যতা থাকতে পারে। মূল নথিটিকে তার মৌলিক ভাষাতেই কর্তৃত্বসম্পন্ন উৎস হিসেবে গণ্য করা উচিত। জরুরি বা গুরুত্বপূর্ণ তথ্যের জন্য পেশাদার মানব অনুবাদ করা উচিৎ। এই অনুবাদের ব্যবহারে উদ্ভূত যেকোনো ভুলবোঝাবুঝি বা ভ্রান্ত ব্যাখ্যার জন্য আমরা দায়ী নই।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->