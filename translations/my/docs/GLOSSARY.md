<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T06:12:37+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "my"
}
-->
# LangChain4j အဘိဓာန်

## Table of Contents

- [Core Concepts](../../../docs)
- [LangChain4j Components](../../../docs)
- [AI/ML Concepts](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agents and Tools](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Services](../../../docs)
- [Testing and Development](../../../docs)

သင်တန်းအတွင်း အသုံးပြုသော စကားလုံးများနှင့် အယူအဆများအတွက် အမြန်ညွှန်ပြချက်။

## Core Concepts

**AI Agent** - AI ကို သုံးပြီး ကိုယ်တိုင် အတွေးခေါ်ပြီး အလိုအလျောက် လှုပ်ရှားနိုင်သော စနစ်။ [မော်ဂျူး 04](../04-tools/README.md)

**Chain** - ထွက်ရှိလာသော အချက်ကို နောက်အဆင့်ထဲသို့ ထည့်သွင်း ဆက်လက်အသုံးပြုသော လုပ်ငန်းစဉ် အစဉ်လိုက်။

**Chunking** - စာရွက်စာတမ်းများကို အပိုင်းသေးသေးများသို့ ခွဲထုတ်ခြင်း။ ပျမ်းမျှ: overlap အပါအဝင် 300-500 tokens။ [မော်ဂျူး 03](../03-rag/README.md)

**Context Window** - မော်ဒယ်တစ်ခုက မြှောက်လုပ်နိုင်သည့် ထိပ်တန်း tokens အရေအတွက်။ GPT-5: 400K tokens။

**Embeddings** - စာသား၏ အဓိပ္ပါယ်ကို ကိုယ်စားပြုသော ကိန်းဂဏန်းဗက်တာများ။ [မော်ဂ်ူး 03](../03-rag/README.md)

**Function Calling** - မော်ဒယ်က အပြန်အလှန် ဖန်တီးပေးသည့် ဖော်မက်ထားသော တောင်းဆိုချက်များဖြင့် အပြင်ဖန်တ်ရှင်များကို ခေါ်ဆိုရန် ဖန်တီးသည်။ [မော်ဂျူး 04](../04-tools/README.md)

**Hallucination** - မော်ဒယ်များက မှားနေသော်လည်း သက်ဆိုင်နိုင်သည့် အချက်အလက်များကို ထုတ်ပေးခြင်း။

**Prompt** - ဘာသာစကားမော်ဒယ်ထံ ပေးသည့် စာသား အထည့်။ [မော်ဂျူး 02](../02-prompt-engineering/README.md)

**Semantic Search** - keywords မဟုတ်ပဲ embeddings အသုံးပြုကာ အဓိပ္ပါယ်အရ ရှာဖွေခြင်း။ [မော်ဂျူး 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: မှတ်ဉာဏ်မရှိ။ Stateful: စကားပြောသမိုင်းကို ထိန်းသိမ်းထားသည်။ [မော်ဂျူး 01](../01-introduction/README.md)

**Tokens** - မော်ဒယ်များက ပိုင်းခြားဆောင်ရွက်သည့် အခြေခံစာသားယူနစ်များ။ ကုန်ကျမှုနှင့် ကန့်သတ်ချက်များကို ထိခိုက်စေသည်။ [မော်ဂျူး 01](../01-introduction/README.md)

**Tool Chaining** - tools များကို အစဉ်လိုက် ဖော်ဆောင်ပြီး မိမိထွက်လာသည့် အချက်ဖြင့် နောက်တစ်ခေါ်ဆိုမှုကို အကြောင်းပြုခြင်း။ [မော်ဂျူး 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - type-safe AI ဝန်ဆောင်မှု အင်တာဖေ့စ်များ ဖန်တီးသည်။

**OpenAiOfficialChatModel** - OpenAI နှင့် Azure OpenAI မော်ဒယ်များအတွက် ညီအောင်ထည့်သွင်းထားသော client။
**OpenAiOfficialEmbeddingModel** - OpenAI Official client ကို အသုံးပြု၍ embeddings များ ဖန်တီးသည် (OpenAI နှင့် Azure OpenAI နှစ်ခုလုံးကို ပံ့ပိုး)။
**ChatModel** - ဘာသာစကားမော်ဒယ်များအတွက် အခြေခံ အင်တာဖေ့စ်။
**ChatMemory** - စကားပလှယ်အတိတ်ကို ထိန်းသိမ်းသည်။
**ContentRetriever** - RAG အတွက် သက်ဆိုင်ရာ စာရွက်အပိုင်းများကို ရှာဖွေသည်။
**DocumentSplitter** - စာရွက်များကို အပိုင်းအသေးများသို့ ခွဲထုတ်သည်။
**EmbeddingModel** - စာသားကို ကိန်းဂဏန်းဗက်တာများသို့ ပြောင်းပေးသည်။
**EmbeddingStore** - embeddings များကို သိမ်းဆည်းပြီး ရယူသည်။
**MessageWindowChatMemory** - နောက်ဆုံးပိုင်း စာတိုက် စာသားများ၏ လှိမ့်ပြာ ပြတင်းပေါက်ကို ထိန်းသိမ်းသည်။
**PromptTemplate** - `{{variable}}` placeholders များပါသော ပြန်လည်အသုံးပြုနိုင်သော prompts များ ဖန်တီးသည်။
**TextSegment** - မီတာဒေတာပါ သယ်ဆောင်သော စာသားပိုင်း။ RAG တွင် အသုံးပြုသည်။
**ToolExecutionRequest** - tool ဆောင်ရွက်မှု တောင်းဆိုချက်ကို ကိုယ်စားပြုသည်။
**UserMessage / AiMessage / SystemMessage** - စကားပြောသုံးသပ်ချက် မက်ဆေ့ခ်ျ အမျိုးအစားများ။

## AI/ML Concepts

**Few-Shot Learning** - prompts ထဲတွင် နမူနာများ ပေးခြင်း။ [မော်ဂျူး 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - အကြီးစား စာသားဒေတာများဖြင့် လေ့ကျင့်ထားသော AI မော်ဒယ်များ။
**Reasoning Effort** - အတွေးပုံဖော်မှု အနက်ကို ထိန်းချုပ်ရန် GPT-5 ၏ ပမာဏချက်။ [မော်ဂျူး 02](../02-prompt-engineering/README.md)
**Temperature** - ထွက်ရှိမှု၏ ရှုပ်ထွေးမှုနှင့် ရုံကျယ်မှုကို ထိန်းချုပ်သည်။ တန်ဖိုးနည်း=တိကျသေချာ၊ တန်ဖိုးမြင့်=ဖန်တီးမှုမြင့်။
**Vector Database** - embeddings များအတွက် အထူးသီးသန့် ဒေတာဘေ့စ်။ [မော်ဂျူး 03](../03-rag/README.md)
**Zero-Shot Learning** - နမူနာ မပေးဘဲ တာဝန်များကို တိုက်ရိုက်ဆောင်ရွက်ခြင်း။ [မော်ဂျူး 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [မော်ဂျူး 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - စဥ်ဆက်မပြတ် အတွေးခွဲခြင်းက တိကျမှန်ကန်မှုကို မြှင့်တင်ပေးသည်။
**Constrained Output** - သတ်မှတ်ထားသော ဖော်မတ် သို့မဟုတ် ဖွဲ့စည်းပုံကို ညှိနှိုင်းအရေးပေးခြင်း။
**High Eagerness** - သေချာလေ့လာစဉ်းစားမှုအတွက် GPT-5 ပုံစံ။
**Low Eagerness** - အလျင်အမြန်ဖြေကြားမှုအတွက် GPT-5 ပုံစံ။
**Multi-Turn Conversation** - ဆက်လက် အပြန်အလှန်အတွင်း စ.Context ကို ထိန်းသိမ်းခြင်း။
**Role-Based Prompting** - system messages မှတဆင့် မော်ဒယ်၏ ကိုယ်ရည်ကို သတ်မှတ်ခြင်း။
**Self-Reflection** - မော်ဒယ်က မိမိထုတ်ပေးသည့် အဖြေကို အကဲဖြတ်၍ တိုးတက်စေခြင်း။
**Structured Analysis** - အတည်ပြုထားသော အကဲဖြတ်မူနှင့် စနစ်။
**Task Execution Pattern** - ရည်မှန်းချက် → ဆောင်ရွက်ခြင်း → အကျဉ်းချုံး။

## RAG (Retrieval-Augmented Generation) - [မော်ဂျူး 03](../03-rag/README.md)

**Document Processing Pipeline** - Load → chunk → embed → store။
**In-Memory Embedding Store** - စမ်းသပ်မှုအတွက် မအာမခံသိမ်းဆည်းမှု (non-persistent)။
**RAG** - ရှာဖွေမှုနှင့် ထုတ်လုပ်မှုကို ပေါင်းစပ်၍ အဖြေများကို အခြေခံပေးသည်။
**Similarity Score** - အဓိပ္ပါယ် ဆင်တူမှု၏ တိုင်းတာချက် (0-1)။
**Source Reference** - ရှာဖွေပြီး ရလာသော အကြောင်းအရာအပေါ် အချက်အလက် မီတာဒေတာ။

## Agents and Tools - [မော်ဂျူး 04](../04-tools/README.md)

**@Tool Annotation** - Java မက်သက်များကို AI က ခေါ်နိုင်သော tools အဖြစ် အမှတ်အသားပေးသည်။
**ReAct Pattern** - Reason → Act → Observe → Repeat။
**Session Management** - အသုံးပြုသူ မည်မျှကို မဆို အကွာအဝေး ခွဲထားသော context များ ထိန်းသိမ်းခြင်း။
**Tool** - AI agent က ခေါ်နိုင်သည့် function တစ်ခု။
**Tool Description** - tool ၏ ရည်ရွယ်ချက်နှင့် ပါရာမီတာများ အကြောင်း အချက်အလက်။

## Model Context Protocol (MCP) - [မော်ဂျူး 05](../05-mcp/README.md)

**MCP** - AI အက်ပလီကေးရှင်းများကို အပြင်工具များနှင့် ချိတ်ဆက်ရန် စံတမ်း။
**MCP Client** - MCP server များနှင့် ချိတ်ဆက်သော အက်ပလီကေးရှင်း။
**MCP Server** - MCP ထောက်ပံ့သည့် စနစ်တစ်ရပ်၊ tools များကို ထုတ်ဖော်ပေးသည်။
**Stdio Transport** - stdin/stdout ဖြင့် subprocess အဖြစ် စာဝန်ဆောင်မှု ဖော်ဆောင်ခြင်း။
**Tool Discovery** - အသုံးပြုသူ client က server ဆီသို့ ရနိုင်သည့် tools များကို မေးမြန်းခြင်း။

## Azure Services - [မော်ဂျူး 01](../01-introduction/README.md)

**Azure AI Search** - vector အင်ဂျင်များပါသော ကလိင့် စတိုးရှင်းရှာဖွေမှု။ [မော်ဂျူး 03](../03-rag/README.md)
**Azure Developer CLI (azd)** - Azure အရင်းအမြစ်များကို deploy ပြုလုပ်ရန်။
**Azure OpenAI** - Microsoft ၏ စီးပွားရေးအဆင့် AI ဝန်ဆောင်မှု။
**Bicep** - Azure infrastructure-as-code ဘာသာစကား။ [Infrastructure Guide](../01-introduction/infra/README.md)
**Deployment Name** - Azure တွင် မော်ဒယ် deployment အတွက် ခေါင်းစဉ်။
**GPT-5** - ယခင်ကထက် ပို၍ အတွေးချောမွေ့မှုကို ထိန်းချုပ်နိုင်သည့် နောက်ဆုံး OpenAI မော်ဒယ်။ [မော်ဂျူး 02](../02-prompt-engineering/README.md)

## Testing and Development - [စမ်းသပ်ရေးလမ်းညွှန်](TESTING.md)

**Dev Container** - ကွန်တိနာအတွင်း ဖွံ့ဖြိုးရေးပတ်ဝန်းကျင်။ [Configuration](../../../.devcontainer/devcontainer.json)
**GitHub Models** - အခမဲ့ AI မော်ဒယ် ခေတ်စားစမ်းသပ်ရန် ဧကရာဇ်။ [မော်ဂျူး 00](../00-quick-start/README.md)
**In-Memory Testing** - In-memory သိုလှောင်မှု ဖြင့် စမ်းသပ်ခြင်း။
**Integration Testing** - တကယ့် အင်ဖရាសထြပ်ချ်ချ်နှင့် နှိုင်းယှဉ် စမ်းသပ်ခြင်း။
**Maven** - Java build automation ကိရိယာ။
**Mockito** - Java mocking ဖရိမ်ဝျာ့ခ်။
**Spring Boot** - Java အက်ပလီကေးရှင်း ဖရိမ်ဝျာ့ခ်။ [မော်ဂျူး 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
အသိပေးချက်:
ဤစာတမ်းကို AI ဘာသာပြန်ဝန်ဆောင်မှု [Co-op Translator] အသုံးပြု၍ ဘာသာပြန်ထားပါသည်။ ကျွန်ုပ်တို့သည် တိကျမှုအတွက် ကြိုးပမ်းသော်လည်း အလိုအလျောက် ဘာသာပြန်ချက်များတွင် အမှားများ သို့မဟုတ် တိကျမှုလျော့နည်းချက်များ ပါရှိနိုင်ကြောင်း လက်ခံထားရှိပါ။ မူလစာတမ်းကို မူလဘာသာဖြင့် ရှိသော မူရင်းကို အာဏာပိုင် အရင်းအမြစ်အဖြစ် ယူဆသင့်သည်။ အရေးကြီးသော သတင်းအချက်အလက်များအတွက် လူ့ပရော်ဖက်ရှင်နယ် ဘာသာပြန်ခြင်းကို အသင့်သုံးစွဲရန် အကြံပြုပါသည်။ ဤဘာသာပြန်ချက်ကို အသုံးပြုမှုပေါ်တွင် ဖြစ်ပေါ်လာနိုင်သည့် နားမလည်မှုများ သို့မဟုတ် မှားယွင်းဖော်ပြချက်များအတွက် ကျွန်ုပ်တို့သည် တာဝန်မယူပါ။
<!-- CO-OP TRANSLATOR DISCLAIMER END -->