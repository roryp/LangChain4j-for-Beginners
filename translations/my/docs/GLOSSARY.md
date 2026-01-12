<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:38:46+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "my"
}
-->
# LangChain4j အဓိပ္ပာယ်ဖွင့်ချက်စာရင်း

## အကြောင်းအရာ စာရင်း

- [အခြေခံအယူအဆ](../../../docs)
- [LangChain4j အစိတ်အပိုင်းများ](../../../docs)
- [AI/ML အယူအဆများ](../../../docs)
- [အကာအကွယ်များ](../../../docs)
- [Prompt အင်ဂျင်နီယာ](../../../docs)
- [RAG (နှုတ်ယူ၍ အတိုးဖြစ်ထွန်းမှု)](../../../docs)
- [အေးဂျင့်များနှင့် ကိရိယာများ](../../../docs)
- [Agentic အပိုင်း](../../../docs)
- [မော်ဒယ် Context ပရိုတိုကော့ (MCP)](../../../docs)
- [Azure ဝန်ဆောင်မှုများ](../../../docs)
- [စမ်းသပ်မှုနှင့် ဖွံ့ဖြိုးတိုးတက်မှု](../../../docs)

သင်တန်းအတွင်း၌ အသုံးပြုသော ပစ္စည်းများနှင့် အယူအဆများအတွက် အမြန်ရယူရရှိနိုင်ရန်။

## အခြေခံအယူအဆ

**AI Agent** - AI ကို အသုံးပြုပြီး ကိစ္စများကို သဘောထားပြုလုပ်ခြင်းနှင့် ကိုယ်ပိုင် ရွေးချယ် ဆောင်ရွက်နိုင်သော စနစ်။ [Module 04](../04-tools/README.md)

**Chain** - ရလဒ်ကို ဆက်တိုက် အဆင့်သို့ ပို့ပြီး ဆောင်ရွက်မှု စဉ်ကွက်။

**Chunking** - စာရွက်စာတမ်းများကို အသေးစား အပိုင်းများခွဲခြားခြင်း။ ပုံမှန်အားဖြင့် ၃၀၀-၅၀၀ token အတွင်း overlap ပါဝင်စေနိုင်သည်။ [Module 03](../03-rag/README.md)

**Context Window** - မော်ဒယ်တစ်ခုက အမြင့်ဆုံး ခွဲထုတ် ခွင့်ပြုသော token အရေအတွက်။ GPT-5: ၄၀၀K tokens။

**Embeddings** - စာသား အဓိပ္ပါယ်ကို ဖော်ပြသော ဂဏန်း ဗက်တာများ။ [Module 03](../03-rag/README.md)

**Function Calling** - မော်ဒယ်မှ အပြင်လုပ်ဆောင်ချက်များကို ခေါ်ရန် ဖွဲ့စည်းထားသော တောင်းဆိုချက်များဖန်တီးခြင်း။ [Module 04](../04-tools/README.md)

**Hallucination** - မော်ဒယ်များမှ မှားယွင်းသော်လည်း ဖြစ်နိုင်သော သတင်းအချက်အလက်များ ဖြန့်ဝေခြင်း။

**Prompt** - ဘာသာစကား မော်ဒယ်အတွက် စာသား တည်းဖြတ်ပေးခြင်း။ [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** -  keyword မဟုတ်ဘဲ အဓိပ္ပါယ်အခြေပြု ရှာဖွေမှု။ [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: မှတ်ဉာဏ်မပါ။ Stateful: စကားပြော အတွင်းခံ မှတ်တမ်း ထားရှိသည်။ [Module 01](../01-introduction/README.md)

**Tokens** - မော်ဒယ်များ ရေးသား နှုတ်ယူသော အခြေခံ စာသား အပိုင်းများ။ ကုန်ကျစရိတ်နှင့် ကန့်သတ်ချက်များကို သက်ရောက်စေသည်။ [Module 01](../01-introduction/README.md)

**Tool Chaining** - ကိရိယာများကို ဆက်တိုက် အသုံးပြုခြင်း၊ ရလဒ်က တာဝန်ထမ်းဆောင်မှု နောက်တစ်ခုထံ ပို့သည်။ [Module 04](../04-tools/README.md)

## LangChain4j အစိတ်အပိုင်းများ

**AiServices** - အမျိုးအစားလုံခြုံသော AI ဝန်ဆောင်မှု ရှေ့နောက်မျက်နှာပြင် ဖန်တီးသည်။

**OpenAiOfficialChatModel** - OpenAI နှင့် Azure OpenAI မော်ဒယ်များအတွက် ပေါင်းစပ် ဧည့်သည်။

**OpenAiOfficialEmbeddingModel** - OpenAI အတည်ပြု ဧည့်သည်ကို အသုံးပြုပြီး embeddings ဖန်တီးသည် (OpenAI နှင့် Azure OpenAI နှစ်မျိုးလုံး။

**ChatModel** - ဘာသာစကား မော်ဒယ်အတွက် အဓိက အင်တာဖေ့စ်။

**ChatMemory** - စကားပြော အတွင်းခံမှတ်တမ်း ထိန်းသိမ်းသည်။

**ContentRetriever** - RAG အတွက် သက်ဆိုင်သော စာရွက်များကို ရှာဖွေသည်။

**DocumentSplitter** - စာရွက်စာတမ်းများကို ခွဲခြားသည်။

**EmbeddingModel** - စာသားကို ဂဏန်းဗက်တာများသို့ ပြောင်းလဲသည်။

**EmbeddingStore** - embeddings များ သိမ်းဆည်းပြီး ရယူသည်။

**MessageWindowChatMemory** - လတ်တလော စကားသံများ ဆွဲထည့်ထားသော ဖြန့်ချိမှု။

**PromptTemplate** - `{{variable}}` နေရာဒြပ်များပါ သည့် ပြန်လည် အသုံးပြုနိုင်သော prompts ဖန်တီးသည်။

**TextSegment** - မှတ်တမ်းအချက်အလက်ပါ သည့် စာသား အပိုင်း။ RAG အတွက် အသုံးပြုသည်။

**ToolExecutionRequest** - ကိရိယာ အကောင်အထည်ဖော် တောင်းဆိုချက် ကိုယ်စားပြုသည်။

**UserMessage / AiMessage / SystemMessage** - စကားပြောသဘောထား မက်ဆေ့ခ်ျ အမျိုးအစားများ။

## AI/ML အယူအဆများ

**Few-Shot Learning** - prompts တွင် နမူနာများ ကို ပေးဆောင်ခြင်း။ [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - စာသား များတား၍ သင်ကြားထားသော ကောင်းမွန်သော AI မော်ဒယ်များ။

**Reasoning Effort** - GPT-5 ၏ စဉ်းစား သဘောထား အတွင်း မဲပေးသည်။ [Module 02](../02-prompt-engineering/README.md)

**Temperature** - ထွက်ရှိသော အဖြေ၏ စိတ်ခံစားမှု ရောနှောမှု ထိန်းချုပ်မှု။ နိမ့် = သက်ဆိုင်မှု၊ မြင့် = ဖန်တီး \

**Vector Database** - embeddings အတွက် အထူးပြုဒေတာဘေ့စ်။ [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - နမူနာ မပါဘဲ တာဝန်များ ဆောင်ရွက်ခြင်း။ [Module 02](../02-prompt-engineering/README.md)

## အကာအကွယ်များ - [Module 00](../00-quick-start/README.md)

**Defense in Depth** - အတန်းပေါင်းစုံသော လုံခြုံရေးနည်းလမ်း၊ application-level guardrails နဲ့ provider safety filters ကို ပေါင်းစပ်ထားသည်။

**Hard Block** - အကြီးအကျယ် ကန့်သတ်ချက်များအတွက် provider မှ HTTP 400 အမှားတစ်ခု ထုတ်ပေးသည်။

**InputGuardrail** - LangChain4j ကိုယ်စားလှယ်, LLM ခရီး၏ user input ကို စုံစမ်းစစ်ဆေးရန်။ ပထမဦးဆုံး ပုံမှန် prompt များကို တားမြစ်ခြင်းဖြင့် စရိတ်နှင့် ကြာမြင့်ချိန် သက်သာသည်။

**InputGuardrailResult** - guardrail စစ်ဆေးမှုအတွက် ပြန်လည်ပို့သည့် အမျိုးအစား။ `success()` သို့မဟုတ် `fatal("reason")`။

**OutputGuardrail** - AI မှ တုံ့ပြန်မှုများကို ယူနာ မသို့ ပြန်ပို့မီ စစ်ဆေးရန် အသုံးပြု အင်တာဖေ့စ်။

**Provider Safety Filters** - AI service ပေးသူများ ပေးသော ဖိုင်တာများ (ဥပမာအနေဖြင့် GitHub Models) သည် API အဆင့်တွင် ဆိုးကျိုးများကို ဖမ်းဆီးသည်။

**Soft Refusal** - မော်ဒယ်မှ အမှားမဖြစ်ပဲ မဖြေဆိုချင်ခြင်းကို သနားစေသော ရုပ်ရှင်ပြခဲ့သည်။

## Prompt အင်ဂျင်နီယာ - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - တစ်ဆင့်စီ သဘောထားခြင်း ဖြင့် တိကျမှုမြင့်မားစေသည်။

**Constrained Output** - သတ်မှတ်ပုံစံ သို့မဟုတ် ဖွဲ့စည်းမှု တစ်ခုကို အတင်းအကြပ် ထိန်းသိမ်းသည်။

**High Eagerness** - GPT-5 ၏ ပြည့်စုံကြိုးစားမှု ပုံစံ။

**Low Eagerness** - GPT-5 ၏ အမြန်ဖြေချင်မှု ပုံစံ။

**Multi-Turn Conversation** - စကားပြော ဆက်ခံမှုများအတွက် context ထိန်းသိမ်းခြင်း။

**Role-Based Prompting** - စနစ်မက်ဆေ့ခ်ျများဖြင့် မော်ဒယ် သဘောထား ကို သတ်မှတ်ခြင်း။

**Self-Reflection** - မော်ဒယ် မှ ထွက်ရှိချက် ကို လက်ခံ သုံးသပ်ကာ တိုးတက်အောင် ပြုလုပ်ခြင်း။

**Structured Analysis** - သတ်မှတ်ထားသော အကဲဖြတ် အခြေခံမျက်နှာ။

**Task Execution Pattern** - အစီအစဉ် → ဆောင်ရွက် → အကျဉ်းချုပ်။

## RAG (နှုတ်ယူ၍ အတိုးဖြစ်ထွန်းမှု) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - တင်သွင်း → ခွဲခြား → embed → သိမ်းဆည်း။

**In-Memory Embedding Store** - စမ်းသပ်မှုအတွက် မသိမ်းဆည်းအားဖြင့် သိုလှောင်မှု။

**RAG** - ရှာဖွေမှုနှင့် ဖန်တီးမှု ကိုပေါင်းစပ်၍ တုံ့ပြန်ချက် များကို အခြေခံထားသည်။

**Similarity Score** - semantic အတူတူမှု ရာခိုင်နှုန်း (0-1)။

**Source Reference** - ရယူခဲ့သော အကြောင်းအရာ အချက်အလက်။

## အေးဂျင့်များနှင့် ကိရိယာများ - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Java နည်းနည်းကျ AI ခေါ်ဆိုနိုင်သော ကိရိယာများ အဖြစ် သတ်မှတ်သည်။

**ReAct Pattern** - ထင်ချက် → လုပ်ဆောင်မှု → သတိထား → ထပ်ခါတလဲလဲ။

**Session Management** - အသုံးပြုသူ သီးခြား context များစီမံခြင်း။

**Tool** - AI အေးဂျင့် တစ်ခုခေါ်ဆိုနိုင်သော လုပ်ဆောင်ချက်။

**Tool Description** - ကိရိယာ ရည်ရွယ်ချက် နှင့် ပါရာမီတာများ၏ စာရွက်အဖေါ်။

## Agentic အပိုင်း - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - AI အေးဂျင့်များအဖြစ် interface များ သတ်မှတ်ပြီး ဘာသာရပ်အခြေခံ အပြုအမှု အဓိပ္ပါယ် ဖော်ပြသည်။

**Agent Listener** - agent ဆောင်ရွက်မှုကို `beforeAgentInvocation()` နှင့် `afterAgentInvocation()` ဖြင့် ကြည့်ရှုးခြင်း။

**Agentic Scope** - agents များ တိုးတက်မှု အထွက်များကို `outputKey` သတ်မှတ် ခြင်းဖြင့် မွေးမြူထားသော အသိဉာဏ် အရေအတွက်၌ မျှဝေထားသည်။

**AgenticServices** - `agentBuilder()` နှင့် `supervisorBuilder()` ဖြင့် agent များ ဖန်တီးရာ စက်ရုံ။

**Conditional Workflow** - အခြေအနေများအရ အထူးပြု agent များသို့ လမ်းကြောင်း ချထားသည်။

**Human-in-the-Loop** - အတည်ပြုသို့မဟုတ် အကြောင်းအရာ ပြန်လည်သုံးသပ်မှုများအတွက် လူ့စစ်ဆေးမှု ပြောင်းလဲမှု pattern။

**langchain4j-agentic** - မူလတန်းအနေဖြင့် agent ဖန်တီးမှုအတွက် Maven အပေါ်တွင်ထားသော dependency (စမ်းသပ်ဂုဏ်)。

**Loop Workflow** - အခြေအနေတစ်ခုများပြီ ဆို အထိ agent ဆောင်ရွက်မှု ပြန်လည်တိုးတက်ခြင်း (ဥပမာ။ အရည်အသွေး စကင် ≥ 0.8)။

**outputKey** - Agent annotation တွင် သတ်မှတ်ထားသော Agentic Scope အတွင်း ဖော်ပြချက် သိမ်းဆည်းရာ နေရာ။

**Parallel Workflow** - လွတ်လပ်သော တာဝန်များအတွက် အေးဂျင့်များ တပြိုင်နက် ပြုလုပ်ရန်။

**Response Strategy** - supervisor မှ နောက်ဆုံး ဖြေကြားမှု ပုံစံ: LAST, SUMMARY, သို့မဟုတ် SCORED။

**Sequential Workflow** - ရလဒ်က တဆင့်တက် ဆောင်ရွက်မှု အဆင့်သို့ ဆက်လက် ပေးပို့ခြင်း။

**Supervisor Agent Pattern** - စွမ်းဆောင်ရည်မြင့် agentic ပုံစံဖြင့် supervisor LLM သည် ဘယ် sub-agent များကို ခေါ်မည်ကို ဆိုင်းငံ့ဆုံးဖြတ်သည်။

## မော်ဒယ် Context ပရိုတိုကော့ (MCP) - [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - LangChain4j တွင် MCP ပေါင်းစည်းမှု အတွက် Maven dependency။

**MCP** - Model Context Protocol: AI app များကို အပြင်ကိရိယာများနှင့် ချိတ်ဆက်ရန် စံနှုန်း။ တစ်ကြိမ်ဖန်တီးပြီး အားလုံးတွင် အသုံးပြုမည်။

**MCP Client** - MCP server များနှင့် ချိတ်ဆက်၍ ကိရိယာများ ရှာဖွေနိုင်သော အပလီ케ေးရှင်း။

**MCP Server** - MCP မှတဆင့် ကိရိယာများကို သတ်မှတ်ချက်ရှင်းလင်းပြီး ပံ့ပိုးသော ဝန်ဆောင်မှု။

**McpToolProvider** - AI ဝန်ဆောင်မှုများနှင့် agent များတွင် အသုံးပြုရန် MCP ကိရိယာများကို၀န်ဆောင်မှုပေးသော LangChain4j အစိတ်အပိုင်း။

**McpTransport** - MCP ဆက်သွယ်မှုအတွက် အင်တာဖေ့စ်။ အကောင်အထည်ဖော်မှုများတွင် Stdio နှင့် HTTP ပါဝင်သည်။

**Stdio Transport** - stdin/stdout နည်းဖြင့် ဒေသတွင်းလုပ်ငန်းဖြင့် သယ်ယူပို့ဆောင်မှု။ စနစ်ဖိုင် သို့မဟုတ် command-line ကိရိယာများတွင် အသုံးဝင်သည်။

**StdioMcpTransport** - MCP server ကို subprocess အဖြစ် စတင်အသက်သွင်းသည့် LangChain4j အကောင်အထည်ဖော်မှု။

**Tool Discovery** - client မှ server ကို အသုံးပြုနိုင်သည့် ကိရိယာများအတွက် ဖေါ်ပြချက်များနှင့် schema များအား မေးမြန်းခြင်း။

## Azure ဝန်ဆောင်မှုများ - [Module 01](../01-introduction/README.md)

**Azure AI Search** - ဗက်တာစွမ်းဆောင်ရည်ပါ ရှာဖွေမှု ဝန်ဆောင်မှု။ [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure ရင်းမြစ်များ ကို ထည့်သွင်းတည်ဆောက်ပေးသည်။

**Azure OpenAI** - Microsoft ၏ စက်ရုပ်သုတတ် ဝန်ဆောင်မှု။

**Bicep** - Azure အခြေခံ အင်ဖရာဖွဲ့စည်းမှု သတင်းအချက်အလက် ေရးသားခြင်း ဘာသာစကား။ [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azure တွင် မော်ဒယ် တပ်ဆင်ခြင်း အမည်။

**GPT-5** - reasoning ထိန်းချုပ်မှု ပါသော နောက်ဆုံးပေါ် OpenAI မော်ဒယ်။ [Module 02](../02-prompt-engineering/README.md)

## စမ်းသပ်မှုနှင့် ဖွံ့ဖြိုးတိုးတက်မှု - [စမ်းသပ်မှုလမ်းညွှန်ချက်](TESTING.md)

**Dev Container** - ကွန်တိနာ ကျဆုံး ဖွံ့ဖြိုးရေး ပတ်ဝန်းကျင်။ [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - အခမဲ့ AI မော်ဒယ် စမ်းသပ်ရေး ကစားခန်း။ [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - memory အတွင်းသိုလှောင်မှုဖြင့် စမ်းသပ်ခြင်း။

**Integration Testing** - အမှန်တကယ် အင်ဖရာများနှင့် စမ်းသပ်မှု။

**Maven** - Java အပ်ဒိတ်လုပ်ငန်း လုပ်ဆောင်ခြင်း ကိရိယာ။

**Mockito** - Java ပြုလုပ်ထားသော mocking framework။

**Spring Boot** - Java အပလီကေးရှင်း ဖွံ့ဖြိုးတိုးတက်ရေး ဖရိမ်းဝပ်။ [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**မသလောက်အကြောင်းကြားချက်**  
ဤစာရွက်စာတမ်းကို AI ဘာသာပြန်ဝန်ဆောင်မှု [Co-op Translator](https://github.com/Azure/co-op-translator) ကို အသုံးပြု၍ ဘာသာပြန်ထားပါသည်။ ကျွန်ုပ်တို့သည် တိကျမှန်ကန်မှုအတွက် ကြိုးစားနေသော်လည်း၊ အလိုအလျောက်ဘာသာပြန်ချက်များတွင် အမှားများ သို့မဟုတ် မမှန်ကန်မှုများ ပါဝင်နိုင်ကြောင်း သတိပြုရန် လိုအပ်ပါသည်။ မူလစာရွက်စာတမ်းကို သူ၏ မူရင်းဘာသာစကားဖြင့်သာ သင့်တော်သော ထောက်ခံစံနှုန်းအဖြစ် သတ်မှတ်သင့်သည်။ အရေးကြီးသော အချက်အလက်များအတွက် လူမြောက်ပညာရှင် ပရော်ဖက်ရှင်နယ် ဘာသာပြန်ခြင်းကို စဉ်းစားရန် အကြံပြုပါသည်။ ဤဘာသာပြန်ချက်ကို အသုံးပြုခြင်းမှ ဖြစ်ပေါ်လာသော နားလည်မှုမှားယွင်းမှုများ သို့မဟုတ် အဓိပ္ပါယ်မွမ်းမံမှုများအတွက် ကျွန်ုပ်တို့ အာမခံမပေးပါ။
<!-- CO-OP TRANSLATOR DISCLAIMER END -->