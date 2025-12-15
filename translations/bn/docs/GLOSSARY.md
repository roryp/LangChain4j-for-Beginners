<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T19:59:24+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "bn"
}
-->
# LangChain4j শব্দকোষ

## বিষয়বস্তু সূচি

- [মূল ধারণা](../../../docs)
- [LangChain4j উপাদানসমূহ](../../../docs)
- [AI/ML ধারণা](../../../docs)
- [প্রম্পট ইঞ্জিনিয়ারিং](../../../docs)
- [RAG (রিট্রিভাল-অগমেন্টেড জেনারেশন)](../../../docs)
- [এজেন্ট এবং টুলস](../../../docs)
- [মডেল কনটেক্সট প্রোটোকল (MCP)](../../../docs)
- [আজুর সার্ভিসেস](../../../docs)
- [পরীক্ষা এবং উন্নয়ন](../../../docs)

কোর্স জুড়ে ব্যবহৃত শব্দ এবং ধারণাগুলির দ্রুত রেফারেন্স।

## মূল ধারণা

**AI Agent** - এমন একটি সিস্টেম যা AI ব্যবহার করে স্বয়ংক্রিয়ভাবে যুক্তি করে এবং কাজ করে। [মডিউল 04](../04-tools/README.md)

**Chain** - অপারেশনগুলির একটি ক্রম যেখানে আউটপুট পরবর্তী ধাপে প্রবাহিত হয়।

**Chunking** - ডকুমেন্টগুলোকে ছোট ছোট অংশে ভাগ করা। সাধারণত: ৩০০-৫০০ টোকেন ওভারল্যাপ সহ। [মডিউল 03](../03-rag/README.md)

**Context Window** - সর্বোচ্চ টোকেন যা একটি মডেল প্রক্রিয়া করতে পারে। GPT-5: ৪০০কে টোকেন।

**Embeddings** - টেক্সটের অর্থকে প্রতিনিধিত্বকারী সংখ্যাসূচক ভেক্টর। [মডিউল 03](../03-rag/README.md)

**Function Calling** - মডেল বাইরের ফাংশন কল করার জন্য কাঠামোবদ্ধ অনুরোধ তৈরি করে। [মডিউল 04](../04-tools/README.md)

**Hallucination** - যখন মডেল ভুল কিন্তু বিশ্বাসযোগ্য তথ্য তৈরি করে।

**Prompt** - একটি ভাষা মডেলের জন্য টেক্সট ইনপুট। [মডিউল 02](../02-prompt-engineering/README.md)

**Semantic Search** - কীওয়ার্ড নয়, অর্থ ব্যবহার করে অনুসন্ধান। [মডিউল 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: কোনো মেমোরি নেই। Stateful: কথোপকথনের ইতিহাস সংরক্ষণ করে। [মডিউল 01](../01-introduction/README.md)

**Tokens** - মডেল প্রক্রিয়া করার মৌলিক টেক্সট ইউনিট। খরচ এবং সীমা প্রভাবিত করে। [মডিউল 01](../01-introduction/README.md)

**Tool Chaining** - টুলের ক্রমাগত কার্যকরী যেখানে আউটপুট পরবর্তী কলকে জানায়। [মডিউল 04](../04-tools/README.md)

## LangChain4j উপাদানসমূহ

**AiServices** - টাইপ-সেফ AI সার্ভিস ইন্টারফেস তৈরি করে।

**OpenAiOfficialChatModel** - OpenAI এবং Azure OpenAI মডেলের জন্য একক ক্লায়েন্ট।

**OpenAiOfficialEmbeddingModel** - OpenAI অফিসিয়াল ক্লায়েন্ট ব্যবহার করে এম্বেডিং তৈরি করে (OpenAI এবং Azure OpenAI উভয় সমর্থন করে)।

**ChatModel** - ভাষা মডেলের জন্য মূল ইন্টারফেস।

**ChatMemory** - কথোপকথনের ইতিহাস সংরক্ষণ করে।

**ContentRetriever** - RAG এর জন্য প্রাসঙ্গিক ডকুমেন্ট চাঙ্ক খুঁজে বের করে।

**DocumentSplitter** - ডকুমেন্টগুলোকে চাঙ্কে ভাগ করে।

**EmbeddingModel** - টেক্সটকে সংখ্যাসূচক ভেক্টরে রূপান্তর করে।

**EmbeddingStore** - এম্বেডিং সংরক্ষণ এবং পুনরুদ্ধার করে।

**MessageWindowChatMemory** - সাম্প্রতিক বার্তাগুলোর স্লাইডিং উইন্ডো সংরক্ষণ করে।

**PromptTemplate** - `{{variable}}` প্লেসহোল্ডার সহ পুনঃব্যবহারযোগ্য প্রম্পট তৈরি করে।

**TextSegment** - মেটাডেটাসহ টেক্সট চাঙ্ক। RAG এ ব্যবহৃত।

**ToolExecutionRequest** - টুল কার্যকর করার অনুরোধ উপস্থাপন করে।

**UserMessage / AiMessage / SystemMessage** - কথোপকথনের বার্তার ধরন।

## AI/ML ধারণা

**Few-Shot Learning** - প্রম্পটে উদাহরণ প্রদান। [মডিউল 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - বিশাল টেক্সট ডেটা দিয়ে প্রশিক্ষিত AI মডেল।

**Reasoning Effort** - GPT-5 প্যারামিটার যা চিন্তার গভীরতা নিয়ন্ত্রণ করে। [মডিউল 02](../02-prompt-engineering/README.md)

**Temperature** - আউটপুটের এলোমেলোতা নিয়ন্ত্রণ করে। কম=নির্ধারিত, বেশি=সৃজনশীল।

**Vector Database** - এম্বেডিংয়ের জন্য বিশেষায়িত ডাটাবেস। [মডিউল 03](../03-rag/README.md)

**Zero-Shot Learning** - উদাহরণ ছাড়াই কাজ সম্পাদন। [মডিউল 02](../02-prompt-engineering/README.md)

## প্রম্পট ইঞ্জিনিয়ারিং - [মডিউল 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - ধাপে ধাপে যুক্তি যা সঠিকতা বাড়ায়।

**Constrained Output** - নির্দিষ্ট ফরম্যাট বা কাঠামো বাধ্যতামূলক করা।

**High Eagerness** - GPT-5 এর প্যাটার্ন যা গভীর যুক্তি করে।

**Low Eagerness** - GPT-5 এর প্যাটার্ন যা দ্রুত উত্তর দেয়।

**Multi-Turn Conversation** - বিনিময় জুড়ে প্রসঙ্গ বজায় রাখা।

**Role-Based Prompting** - সিস্টেম মেসেজের মাধ্যমে মডেলের ব্যক্তিত্ব নির্ধারণ।

**Self-Reflection** - মডেল তার আউটপুট মূল্যায়ন এবং উন্নতি করে।

**Structured Analysis** - নির্দিষ্ট মূল্যায়ন কাঠামো।

**Task Execution Pattern** - পরিকল্পনা → কার্যকর → সারাংশ।

## RAG (রিট্রিভাল-অগমেন্টেড জেনারেশন) - [মডিউল 03](../03-rag/README.md)

**Document Processing Pipeline** - লোড → চাঙ্ক → এম্বেড → সংরক্ষণ।

**In-Memory Embedding Store** - পরীক্ষার জন্য অস্থায়ী সংরক্ষণ।

**RAG** - রিট্রিভাল এবং জেনারেশন একত্রিত করে উত্তর ভিত্তি করে।

**Similarity Score** - অর্থগত সাদৃশ্যের পরিমাপ (০-১)।

**Source Reference** - পুনরুদ্ধারকৃত বিষয়বস্তুর মেটাডেটা।

## এজেন্ট এবং টুলস - [মডিউল 04](../04-tools/README.md)

**@Tool Annotation** - জাভা মেথডকে AI-কলযোগ্য টুল হিসেবে চিহ্নিত করে।

**ReAct Pattern** - যুক্তি → কাজ → পর্যবেক্ষণ → পুনরাবৃত্তি।

**Session Management** - বিভিন্ন ব্যবহারকারীর জন্য পৃথক প্রসঙ্গ।

**Tool** - এমন একটি ফাংশন যা AI এজেন্ট কল করতে পারে।

**Tool Description** - টুলের উদ্দেশ্য এবং প্যারামিটারসমূহের ডকুমেন্টেশন।

## মডেল কনটেক্সট প্রোটোকল (MCP) - [মডিউল 05](../05-mcp/README.md)

**Docker Transport** - Docker কন্টেইনারে MCP সার্ভার।

**MCP** - AI অ্যাপ্লিকেশনকে বাইরের টুলের সাথে সংযুক্ত করার স্ট্যান্ডার্ড।

**MCP Client** - MCP সার্ভারের সাথে সংযোগকারী অ্যাপ্লিকেশন।

**MCP Server** - MCP এর মাধ্যমে টুলস এক্সপোজ করা সার্ভিস।

**Server-Sent Events (SSE)** - HTTP এর মাধ্যমে সার্ভার থেকে ক্লায়েন্টে স্ট্রিমিং।

**Stdio Transport** - stdin/stdout এর মাধ্যমে সাবপ্রসেস হিসেবে সার্ভার।

**Streamable HTTP Transport** - SSE সহ HTTP যা রিয়েল-টাইম যোগাযোগ করে।

**Tool Discovery** - ক্লায়েন্ট সার্ভার থেকে উপলব্ধ টুল সম্পর্কে তথ্য নেয়।

## আজুর সার্ভিসেস - [মডিউল 01](../01-introduction/README.md)

**Azure AI Search** - ভেক্টর সক্ষমতা সহ ক্লাউড অনুসন্ধান। [মডিউল 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - আজুর রিসোর্স ডিপ্লয় করে।

**Azure OpenAI** - মাইক্রোসফটের এন্টারপ্রাইজ AI সার্ভিস।

**Bicep** - আজুর ইনফ্রাস্ট্রাকচার-অ্যাস-কোড ভাষা। [ইনফ্রাস্ট্রাকচার গাইড](../01-introduction/infra/README.md)

**Deployment Name** - আজুরে মডেল ডিপ্লয়মেন্টের নাম।

**GPT-5** - সর্বশেষ OpenAI মডেল যা যুক্তি নিয়ন্ত্রণ করে। [মডিউল 02](../02-prompt-engineering/README.md)

## পরীক্ষা এবং উন্নয়ন - [পরীক্ষা গাইড](TESTING.md)

**Dev Container** - কন্টেইনারাইজড উন্নয়ন পরিবেশ। [কনফিগারেশন](../../../.devcontainer/devcontainer.json)

**GitHub Models** - বিনামূল্যের AI মডেল প্লেগ্রাউন্ড। [মডিউল ০০](../00-quick-start/README.md)

**In-Memory Testing** - ইন-মেমোরি স্টোরেজ দিয়ে পরীক্ষা।

**Integration Testing** - বাস্তব অবকাঠামোর সাথে পরীক্ষা।

**Maven** - জাভা বিল্ড অটোমেশন টুল।

**Mockito** - জাভা মকিং ফ্রেমওয়ার্ক।

**Spring Boot** - জাভা অ্যাপ্লিকেশন ফ্রেমওয়ার্ক। [মডিউল 01](../01-introduction/README.md)

**Testcontainers** - পরীক্ষায় Docker কন্টেইনার।

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**অস্বীকৃতি**:  
এই নথিটি AI অনুবাদ সেবা [Co-op Translator](https://github.com/Azure/co-op-translator) ব্যবহার করে অনূদিত হয়েছে। আমরা যথাসাধ্য সঠিকতার চেষ্টা করি, তবে স্বয়ংক্রিয় অনুবাদে ত্রুটি বা অসঙ্গতি থাকতে পারে। মূল নথিটি তার নিজস্ব ভাষায়ই কর্তৃত্বপূর্ণ উৎস হিসেবে বিবেচিত হওয়া উচিত। গুরুত্বপূর্ণ তথ্যের জন্য পেশাদার মানব অনুবাদ গ্রহণ করার পরামর্শ দেওয়া হয়। এই অনুবাদের ব্যবহারে সৃষ্ট কোনো ভুল বোঝাবুঝি বা ভুল ব্যাখ্যার জন্য আমরা দায়ী নই।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->