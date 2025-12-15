<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:23:16+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "my"
}
-->
# LangChain4j အဓိပ္ပာယ်ဖွင့်

## အကြောင်းအရာဇယား

- [အခြေခံအယူအဆများ](../../../docs)
- [LangChain4j အစိတ်အပိုင်းများ](../../../docs)
- [AI/ML အယူအဆများ](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agents and Tools](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Services](../../../docs)
- [စမ်းသပ်ခြင်းနှင့် ဖွံ့ဖြိုးတိုးတက်မှု](../../../docs)

သင်တန်းတစ်ခုလုံးတွင် အသုံးပြုသော စကားလုံးများနှင့် အယူအဆများအတွက် အမြန်ရယူနိုင်သော ကိုးကားစာရင်း။

## အခြေခံအယူအဆများ

**AI Agent** - AI ကို အသုံးပြု၍ ကိုယ်ပိုင်အတွေးအခေါ်နှင့် လုပ်ဆောင်ချက်များ ပြုလုပ်နိုင်သော စနစ်။ [Module 04](../04-tools/README.md)

**Chain** - အဆင့်ဆင့် လုပ်ဆောင်ချက်များ စဉ်ဆက်မပြတ်ဖြစ်ပြီး အရလဒ်သည် နောက်တစ်ဆင့်အတွက် အချက်အလက်ဖြစ်သည်။

**Chunking** - စာရွက်စာတမ်းများကို အပိုင်းသေးသေးများသို့ ခွဲခြားခြင်း။ ပုံမှန်အားဖြင့် ၃၀၀-၅၀၀ token များနှင့် အတူတူဖြစ်သည်။ [Module 03](../03-rag/README.md)

**Context Window** - မော်ဒယ်တစ်ခုက အများဆုံး လက်ခံနိုင်သော token အရေအတွက်။ GPT-5: ၄၀၀,၀၀၀ token။

**Embeddings** - စာသား၏ အဓိပ္ပာယ်ကို ကိုယ်စားပြုသော နံပါတ်ဗက်တာများ။ [Module 03](../03-rag/README.md)

**Function Calling** - မော်ဒယ်က ပြင်ပ function များကို ခေါ်ရန် ဖွဲ့စည်းထားသော တောင်းဆိုမှုများ ထုတ်ပေးခြင်း။ [Module 04](../04-tools/README.md)

**Hallucination** - မော်ဒယ်များမှ မှားယွင်းသော်လည်း ယုံကြည်စိတ်ချရသော အချက်အလက်များ ထုတ်ပေးခြင်း။

**Prompt** - ဘာသာစကားမော်ဒယ်ထံ ပေးပို့သော စာသားအချက်အလက်။ [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - စကားလုံးအစား အဓိပ္ပာယ်အရ ရှာဖွေခြင်း။ [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: မှတ်ဉာဏ်မရှိ။ Stateful: စကားပြောဆိုမှုမှတ်တမ်းကို ထိန်းသိမ်းထားသည်။ [Module 01](../01-introduction/README.md)

**Tokens** - မော်ဒယ်များက လုပ်ဆောင်ရာတွင် အသုံးပြုသော အခြေခံစာသားယူနစ်များ။ ကုန်ကျစရိတ်နှင့် ကန့်သတ်ချက်များကို သက်ရောက်စေသည်။ [Module 01](../01-introduction/README.md)

**Tool Chaining** - တစ်ခုချင်းစီ၏ အထွက်အရလဒ်သည် နောက်တစ်ခုခေါ်ဆိုမှုအတွက် အချက်အလက်ဖြစ်သော ကိရိယာများ စဉ်ဆက်မပြတ် အသုံးပြုခြင်း။ [Module 04](../04-tools/README.md)

## LangChain4j အစိတ်အပိုင်းများ

**AiServices** - အမျိုးအစားလုံခြုံသော AI ဝန်ဆောင်မှု အင်တာဖေ့စ်များ ဖန်တီးသည်။

**OpenAiOfficialChatModel** - OpenAI နှင့် Azure OpenAI မော်ဒယ်များအတွက် ညီညွတ်သော client။

**OpenAiOfficialEmbeddingModel** - OpenAI Official client ကို အသုံးပြု၍ embedding များ ဖန်တီးသည် (OpenAI နှင့် Azure OpenAI နှစ်မျိုးလုံးကို ထောက်ပံ့သည်)။

**ChatModel** - ဘာသာစကားမော်ဒယ်များအတွက် အဓိက အင်တာဖေ့စ်။

**ChatMemory** - စကားပြောဆိုမှုမှတ်တမ်း ထိန်းသိမ်းသည်။

**ContentRetriever** - RAG အတွက် သင့်တော်သော စာရွက်စာတမ်း အပိုင်းများ ရှာဖွေသည်။

**DocumentSplitter** - စာရွက်စာတမ်းများကို အပိုင်းသေးသေးများ ခွဲခြားသည်။

**EmbeddingModel** - စာသားကို နံပါတ်ဗက်တာများသို့ ပြောင်းလဲသည်။

**EmbeddingStore** - embedding များကို သိမ်းဆည်းပြီး ရယူသည်။

**MessageWindowChatMemory** - နောက်ဆုံးရ စကားပြောဆိုမှုများ၏ sliding window ကို ထိန်းသိမ်းသည်။

**PromptTemplate** - `{{variable}}` placeholder များပါရှိသော ပြန်လည်အသုံးပြုနိုင်သော prompt များ ဖန်တီးသည်။

**TextSegment** - metadata ပါရှိသော စာသားအပိုင်း။ RAG တွင် အသုံးပြုသည်။

**ToolExecutionRequest** - ကိရိယာ လုပ်ဆောင်မှု တောင်းဆိုမှုကို ကိုယ်စားပြုသည်။

**UserMessage / AiMessage / SystemMessage** - စကားပြောဆိုမှု မက်ဆေ့ခ်ျ အမျိုးအစားများ။

## AI/ML အယူအဆများ

**Few-Shot Learning** - prompt များတွင် ဥပမာများ ပေးခြင်း။ [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - စာသားဒေတာ အများအပြားဖြင့် လေ့ကျင့်ထားသော AI မော်ဒယ်များ။

**Reasoning Effort** - GPT-5 ၏ စဉ်းစားမှု အနက်ကို ထိန်းချုပ်သော ပါရာမီတာ။ [Module 02](../02-prompt-engineering/README.md)

**Temperature** - ထွက်ရှိမှု၏ မတည်ငြိမ်မှုကို ထိန်းချုပ်သည်။ နိမ့်သည်=တိကျမှု၊ မြင့်သည်=ဖန်တီးမှု။

**Vector Database** - embedding များအတွက် အထူးပြု ဒေတာဘေ့စ်။ [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - ဥပမာမရှိဘဲ လုပ်ဆောင်ခြင်း။ [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - တစ်ဆင့်ချင်းစီ စဉ်းစားခြင်းဖြင့် တိကျမှု မြှင့်တင်ခြင်း။

**Constrained Output** - သတ်မှတ်ထားသော ပုံစံ သို့မဟုတ် ဖွဲ့စည်းမှုကို အတင်းအကျပ်လိုက်နာခြင်း။

**High Eagerness** - GPT-5 ၏ စဉ်းစားမှု အနက်မြင့် ပုံစံ။

**Low Eagerness** - GPT-5 ၏ အမြန်ဖြေကြားမှု ပုံစံ။

**Multi-Turn Conversation** - အပြန်အလှန် ဆက်သွယ်မှုများတွင် context ထိန်းသိမ်းခြင်း။

**Role-Based Prompting** - စနစ်မက်ဆေ့ခ်ျများမှတဆင့် မော်ဒယ်၏ persona သတ်မှတ်ခြင်း။

**Self-Reflection** - မော်ဒယ်သည် မိမိထုတ်လွှင့်မှုကို သုံးသပ်ပြီး တိုးတက်စေခြင်း။

**Structured Analysis** - သတ်မှတ်ထားသော အကဲဖြတ်မှု ဖွဲ့စည်းမှု။

**Task Execution Pattern** - စီမံချက် → လုပ်ဆောင် → အကျဉ်းချုပ်။

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - ဖတ်ယူ → ခွဲခြား → embedding → သိမ်းဆည်း။

**In-Memory Embedding Store** - စမ်းသပ်မှုအတွက် မတည်ငြိမ်သော သိမ်းဆည်းမှု။

**RAG** - ရှာဖွေမှုနှင့် ဖန်တီးမှု ပေါင်းစပ်၍ တုံ့ပြန်ချက်များကို အခြေခံပေးသည်။

**Similarity Score** - semantic ဆင်တူမှု၏ တိုင်းတာချက် (0-1)။

**Source Reference** - ရှာဖွေတွေ့ရှိထားသော အကြောင်းအရာ metadata။

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Java method များကို AI ခေါ်ဆိုနိုင်သော ကိရိယာအဖြစ် သတ်မှတ်သည်။

**ReAct Pattern** - စဉ်းစား → လုပ်ဆောင် → ကြည့်ရှု → ထပ်မံလုပ်ဆောင်။

**Session Management** - အသုံးပြုသူအလိုက် context များ ခွဲခြား ထိန်းသိမ်းခြင်း။

**Tool** - AI agent က ခေါ်ဆိုနိုင်သော function။

**Tool Description** - ကိရိယာရည်ရွယ်ချက်နှင့် ပါရာမီတာများ အကြောင်း အကျဉ်းချုပ်။

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**Docker Transport** - Docker container အတွင်း MCP server။

**MCP** - AI app များကို ပြင်ပကိရိယာများနှင့် ချိတ်ဆက်ရန် စံနမူနာ။

**MCP Client** - MCP server များနှင့် ချိတ်ဆက်သော အပလီကေးရှင်း။

**MCP Server** - MCP ဖြင့် ကိရိယာများကို ဖော်ပြသော ဝန်ဆောင်မှု။

**Server-Sent Events (SSE)** - HTTP မှတဆင့် server မှ client သို့ စီးဆင်းမှု။

**Stdio Transport** - stdin/stdout ဖြင့် subprocess အဖြစ် server။

**Streamable HTTP Transport** - SSE ပါရှိသော HTTP ဖြင့် အချိန်နှင့်တပြေးညီ ဆက်သွယ်မှု။

**Tool Discovery** - client က server ကို ရနိုင်သော ကိရိယာများ မေးမြန်းခြင်း။

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - vector စွမ်းရည်ပါရှိသော cloud ရှာဖွေရေး။ [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure အရင်းအမြစ်များ တပ်ဆင်ခြင်း။

**Azure OpenAI** - Microsoft ၏ စီးပွားရေး AI ဝန်ဆောင်မှု။

**Bicep** - Azure အခြေခံအဆောက်အအုံ ကုဒ်ရေးသားခြင်း ဘာသာစကား။ [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azure တွင် မော်ဒယ် တပ်ဆင်မှုအမည်။

**GPT-5** - reasoning ထိန်းချုပ်မှုပါရှိသည့် နောက်ဆုံး OpenAI မော်ဒယ်။ [Module 02](../02-prompt-engineering/README.md)

## စမ်းသပ်ခြင်းနှင့် ဖွံ့ဖြိုးတိုးတက်မှု - [Testing Guide](TESTING.md)

**Dev Container** - container အတွင်း ဖွံ့ဖြိုးတိုးတက်ရေး ပတ်ဝန်းကျင်။ [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - အခမဲ့ AI မော်ဒယ် ကစားကွင်း။ [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - in-memory သိမ်းဆည်းမှုဖြင့် စမ်းသပ်ခြင်း။

**Integration Testing** - အမှန်တကယ် အဆောက်အအုံဖြင့် စမ်းသပ်ခြင်း။

**Maven** - Java build automation ကိရိယာ။

**Mockito** - Java mocking framework။

**Spring Boot** - Java အပလီကေးရှင်း ဖွံ့ဖြိုးရေး framework။ [Module 01](../01-introduction/README.md)

**Testcontainers** - စမ်းသပ်မှုများတွင် Docker container များ။

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**အကြောင်းကြားချက်**  
ဤစာတမ်းကို AI ဘာသာပြန်ဝန်ဆောင်မှု [Co-op Translator](https://github.com/Azure/co-op-translator) ဖြင့် ဘာသာပြန်ထားပါသည်။ ကျွန်ုပ်တို့သည် တိကျမှန်ကန်မှုအတွက် ကြိုးစားသော်လည်း အလိုအလျောက် ဘာသာပြန်ခြင်းတွင် အမှားများ သို့မဟုတ် မှားယွင်းချက်များ ပါဝင်နိုင်ကြောင်း သတိပြုပါရန် မေတ္တာရပ်ခံအပ်ပါသည်။ မူရင်းစာတမ်းကို မိမိဘာသာစကားဖြင့်သာ တရားဝင်အရင်းအမြစ်အဖြစ် သတ်မှတ်သင့်ပါသည်။ အရေးကြီးသော အချက်အလက်များအတွက် လူ့ဘာသာပြန်သူမှ တာဝန်ယူ၍ ဘာသာပြန်ခြင်းကို အကြံပြုပါသည်။ ဤဘာသာပြန်ချက်ကို အသုံးပြုမှုကြောင့် ဖြစ်ပေါ်လာနိုင်သည့် နားလည်မှုမှားယွင်းမှုများ သို့မဟုတ် မှားဖတ်မှုများအတွက် ကျွန်ုပ်တို့သည် တာဝန်မယူပါ။
<!-- CO-OP TRANSLATOR DISCLAIMER END -->