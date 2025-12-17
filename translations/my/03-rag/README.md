<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f538a51cfd13147d40d84e936a0f485c",
  "translation_date": "2025-12-13T17:21:50+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "my"
}
-->
# Module 03: RAG (Retrieval-Augmented Generation)

## Table of Contents

- [What You'll Learn](../../../03-rag)
- [Prerequisites](../../../03-rag)
- [Understanding RAG](../../../03-rag)
- [How It Works](../../../03-rag)
  - [Document Processing](../../../03-rag)
  - [Creating Embeddings](../../../03-rag)
  - [Semantic Search](../../../03-rag)
  - [Answer Generation](../../../03-rag)
- [Run the Application](../../../03-rag)
- [Using the Application](../../../03-rag)
  - [Upload a Document](../../../03-rag)
  - [Ask Questions](../../../03-rag)
  - [Check Source References](../../../03-rag)
  - [Experiment with Questions](../../../03-rag)
- [Key Concepts](../../../03-rag)
  - [Chunking Strategy](../../../03-rag)
  - [Similarity Scores](../../../03-rag)
  - [In-Memory Storage](../../../03-rag)
  - [Context Window Management](../../../03-rag)
- [When RAG Matters](../../../03-rag)
- [Next Steps](../../../03-rag)

## What You'll Learn

ယခင် module များတွင် AI နှင့် စကားပြောနည်းများနှင့် prompt များကို ထိရောက်စွာ ဖွဲ့စည်းနည်းကို သင်ယူခဲ့သည်။ သို့သော် အခြေခံကန့်သတ်ချက်တစ်ခုရှိသည်။ ဘာသာစကားမော်ဒယ်များသည် သင်ကြားမှုအတွင်း သင်ယူထားသည့် အချက်အလက်များကိုသာ သိရှိသည်။ သင်၏ကုမ္ပဏီ၏ မူဝါဒများ၊ စီမံကိန်းစာရွက်စာတမ်းများ သို့မဟုတ် မသင်ကြားထားသော အချက်အလက်များအကြောင်း မေးခွန်းများကို မဖြေဆိုနိုင်ပါ။

RAG (Retrieval-Augmented Generation) သည် ဤပြဿနာကို ဖြေရှင်းပေးသည်။ မော်ဒယ်ကို သင်၏အချက်အလက်များ သင်ကြားပေးရန် (ဈေးကြီးပြီး မလွယ်ကူသော) အစား၊ သင်၏စာရွက်စာတမ်းများကို ရှာဖွေစစ်ဆေးနိုင်စွမ်းကို ပေးသည်။ မေးခွန်းတစ်ခုမေးသောအခါ၊ စနစ်သည် သက်ဆိုင်ရာ အချက်အလက်များကို ရှာဖွေပြီး prompt ထဲသို့ ထည့်သွင်းပေးသည်။ မော်ဒယ်သည် ထို ရရှိထားသော context အပေါ် အခြေခံ၍ ဖြေကြားသည်။

RAG ကို မော်ဒယ်အား ကိုးကားစာကြည့်တိုက်တစ်ခု ပေးခြင်းဟု တွေးပါ။ မေးခွန်းမေးသောအခါ၊ စနစ်သည် -

1. **User Query** - သင်မေးခွန်းမေးသည်
2. **Embedding** - မေးခွန်းကို vector သို့ ပြောင်းသည်
3. **Vector Search** - ဆင်တူသော စာရွက်စာတမ်း အပိုင်းများ ရှာဖွေသည်
4. **Context Assembly** - သက်ဆိုင်ရာ အပိုင်းများကို prompt ထဲ ထည့်သည်
5. **Response** - LLM သည် context အပေါ် အခြေခံ၍ ဖြေကြားသည်

ဤနည်းဖြင့် မော်ဒယ်၏ ဖြေကြားချက်များကို သင်၏ အချက်အလက်များပေါ်တွင် အခြေခံစေပြီး သင်ကြားမှုသိပ္ပံပညာ သို့မဟုတ် မမှန်ကန်သော ဖြေကြားချက်များ မပေးစေပါ။

<img src="../../../translated_images/rag-architecture.ccb53b71a6ce407fa8a6394c7a747eb9ad40f6334b4c217be0439d700f22bbcc.my.png" alt="RAG Architecture" width="800"/>

*RAG workflow - user query မှ semantic search မှ context-based answer generation အထိ*

## Prerequisites

- Module 01 ပြီးစီးထားပြီး (Azure OpenAI အရင်းအမြစ်များ တပ်ဆင်ပြီး)
- Root directory တွင် `.env` ဖိုင်ရှိပြီး Azure အတည်ပြုချက်များပါရှိသည် (Module 01 တွင် `azd up` ဖြင့် ဖန်တီးထားသည်)

> **Note:** Module 01 မပြီးစီးသေးပါက အရင်ဆုံး အဲဒီမှာရှိသော တပ်ဆင်မှု လမ်းညွှန်ချက်များကို လိုက်နာပါ။

## How It Works

**Document Processing** - [DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

စာရွက်စာတမ်းတင်သွင်းသောအခါ၊ စနစ်သည် စာရွက်စာတမ်းကို chunks များသို့ ခွဲထုတ်သည် - မော်ဒယ်၏ context window ထဲသို့ သက်တောင့်သက်သာ ထည့်နိုင်သော အပိုင်းသေးသေးများဖြစ်သည်။ ဤ chunks များသည် အနည်းငယ် 겹치는နေရာများရှိပြီး အနားနားတွင် context ပျောက်ဆုံးခြင်း မဖြစ်စေရန် ဖြစ်သည်။

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) and ask:
> - "How does LangChain4j split documents into chunks and why is overlap important?"
> - "What's the optimal chunk size for different document types and why?"
> - "How do I handle documents in multiple languages or with special formatting?"

**Creating Embeddings** - [LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

chunk တစ်ခုချင်းစီကို embedding ဟုခေါ်သော နံပါတ်ပြသည့် ကိုယ်စားပြုမှုသို့ ပြောင်းလဲသည် - စာသား၏ အဓိပ္ပာယ်ကို ဖမ်းယူထားသည့် သင်္ချာဆိုင်ရာ လက်မှတ်တစ်ခုလို ဖြစ်သည်။ ဆင်တူသော စာသားများသည် ဆင်တူသော embedding များ ထုတ်ပေးသည်။

```java
@Bean
public EmbeddingModel embeddingModel() {
    return OpenAiOfficialEmbeddingModel.builder()
        .baseUrl(azureOpenAiEndpoint)
        .apiKey(azureOpenAiKey)
        .modelName(azureEmbeddingDeploymentName)
        .build();
}

EmbeddingStore<TextSegment> embeddingStore = 
    new InMemoryEmbeddingStore<>();
```

<img src="../../../translated_images/vector-embeddings.2ef7bdddac79a327ad9e3e46cde9a86f5eeefbeb3edccd387e33018c1671cecd.my.png" alt="Vector Embeddings Space" width="800"/>

*စာရွက်စာတမ်းများကို embedding အာကာသတွင် vector များအဖြစ် ကိုယ်စားပြုထားခြင်း - ဆင်တူသော အကြောင်းအရာများ သီးခြားစုစည်းထားသည်*

**Semantic Search** - [RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

မေးခွန်းမေးသောအခါ၊ မေးခွန်းကိုလည်း embedding သို့ ပြောင်းသည်။ စနစ်သည် မေးခွန်း embedding ကို စာရွက်စာတမ်း chunks များ၏ embedding များနှင့် နှိုင်းယှဉ်သည်။ အဓိပ္ပာယ်ဆင်တူမှု အများဆုံးရှိသော chunks များကို ရှာဖွေသည် - စကားလုံးကိုက်ညီမှုသာမက အမှန်တကယ် semantic ဆင်တူမှုဖြစ်သည်။

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) and ask:
> - "How does similarity search work with embeddings and what determines the score?"
> - "What similarity threshold should I use and how does it affect results?"
> - "How do I handle cases where no relevant documents are found?"

**Answer Generation** - [RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

အဓိပ္ပာယ်ဆင်တူမှု အများဆုံး chunks များကို မော်ဒယ်၏ prompt ထဲသို့ ထည့်သွင်းသည်။ မော်ဒယ်သည် ထို chunks များကို ဖတ်ပြီး မေးခွန်းကို အချက်အလက်အပေါ် အခြေခံ၍ ဖြေကြားသည်။ ဤနည်းဖြင့် hallucination ဖြစ်ခြင်းကို ကာကွယ်ပေးသည် - မော်ဒယ်သည် မျက်နှာမူထားသော အချက်အလက်မှသာ ဖြေဆိုနိုင်သည်။

## Run the Application

**Verify deployment:**

Root directory တွင် `.env` ဖိုင်ရှိပြီး Azure အတည်ပြုချက်များပါရှိသည်ကို သေချာစေပါ (Module 01 တွင် ဖန်တီးထားသည်) -
```bash
cat ../.env  # AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT ကိုပြသသင့်သည်။
```

**Start the application:**

> **Note:** Module 01 မှ `./start-all.sh` ဖြင့် အားလုံးကို စတင်ထားပြီးဖြစ်ပါက ဒီ module သည် port 8081 တွင် ရပ်တည်နေပါပြီ။ အောက်ပါ စတင်မှု command များကို ကျော်လွှားပြီး http://localhost:8081 သို့ တိုက်ရိုက်သွားနိုင်ပါသည်။

**Option 1: Using Spring Boot Dashboard (VS Code အသုံးပြုသူများအတွက် အကြံပြုသည်)**

dev container တွင် Spring Boot Dashboard extension ပါဝင်ပြီး၊ ဤ extension သည် Spring Boot application များအား visual interface ဖြင့် စီမံခန့်ခွဲရန် အဆင်ပြေစေသည်။ VS Code ၏ ဘယ်ဘက် Activity Bar တွင် Spring Boot icon ကို ရှာနိုင်သည်။

Spring Boot Dashboard မှာ -
- workspace တွင် ရှိသည့် Spring Boot application များအားလုံးကို ကြည့်ရှုနိုင်သည်
- တစ်ချက်နှိပ်ခြင်းဖြင့် application များ စတင်/ရပ်တန့်နိုင်သည်
- application log များကို real-time တွင် ကြည့်ရှုနိုင်သည်
- application အခြေအနေကို စောင့်ကြည့်နိုင်သည်

"rag" အနီးရှိ play button ကို နှိပ်၍ ဒီ module ကို စတင်ပါ၊ သို့မဟုတ် အားလုံးကို တပြိုင်နက် စတင်နိုင်သည်။

<img src="../../../translated_images/dashboard.fbe6e28bf4267ffe4f95a708ecd46e78f69fd46a562d2a766e73c98fe0f53922.my.png" alt="Spring Boot Dashboard" width="400"/>

**Option 2: Using shell scripts**

Web application များအားလုံး (module 01-04) ကို စတင်ရန် -

**Bash:**
```bash
cd ..  # မူလဖိုင်လမ်းကြောင်းမှ
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # မူလဖိုင်လမ်းကြောင်းမှ
.\start-all.ps1
```

သို့မဟုတ် ဒီ module ကိုသာ စတင်ရန် -

**Bash:**
```bash
cd 03-rag
./start.sh
```

**PowerShell:**
```powershell
cd 03-rag
.\start.ps1
```

နှစ်ခုလုံးသည် root `.env` ဖိုင်မှ environment variables များကို အလိုအလျောက် load လုပ်ပြီး JAR မရှိပါက build လုပ်ပေးပါသည်။

> **Note:** စတင်ရန်မတိုင်မီ module များအားလုံးကို ကိုယ်တိုင် build လုပ်လိုပါက -
>
> **Bash:**
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
>
> **PowerShell:**
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

http://localhost:8081 ကို browser တွင် ဖွင့်ပါ။

**ရပ်ရန်:**

**Bash:**
```bash
./stop.sh  # ဒီမော်ဂျူးသာ
# ဒါမှမဟုတ်
cd .. && ./stop-all.sh  # မော်ဂျူးအားလုံး
```

**PowerShell:**
```powershell
.\stop.ps1  # ဒီမော်ဂျူးသာ
# ဒါမှမဟုတ်
cd ..; .\stop-all.ps1  # မော်ဂျူးအားလုံး
```

## Using the Application

ဤ application သည် စာရွက်စာတမ်းတင်သွင်းခြင်းနှင့် မေးခွန်းမေးခြင်းအတွက် web interface ပေးသည်။

<a href="images/rag-homepage.png"><img src="../../../translated_images/rag-homepage.d90eb5ce1b3caa94987b4fa2923d3cb884a67987cf2f994ca53756c6586a93b1.my.png" alt="RAG Application Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*RAG application interface - စာရွက်စာတမ်းတင်ပြီး မေးခွန်းမေးနိုင်သည်*

**Upload a Document**

စာရွက်စာတမ်းတင်ခြင်းဖြင့် စတင်ပါ - စမ်းသပ်ရန် TXT ဖိုင်များ အကောင်းဆုံးဖြစ်သည်။ ဒီ directory တွင် `sample-document.txt` ဖိုင်တစ်ခု ပါရှိပြီး LangChain4j features, RAG implementation နှင့် အကောင်းဆုံး လေ့လာမှုများအကြောင်း ပါဝင်သည် - စနစ်ကို စမ်းသပ်ရန် သင့်တော်သည်။

စနစ်သည် သင်၏စာရွက်စာတမ်းကို ခွဲထုတ်ပြီး chunks များသို့ ခွဲခြားကာ chunk တစ်ခုချင်းစီအတွက် embedding များ ဖန်တီးသည်။ ဤလုပ်ငန်းစဉ်သည် စာရွက်စာတမ်းတင်သွင်းသည့်အခါ အလိုအလျောက် ဖြစ်ပေါ်သည်။

**Ask Questions**

ယခု စာရွက်စာတမ်းအကြောင်း အတိအကျ မေးခွန်းများ မေးပါ။ စာရွက်စာတမ်းတွင် ထင်ရှားစွာ ဖော်ပြထားသော အချက်အလက်များကို မေးကြည့်ပါ။ စနစ်သည် သက်ဆိုင်ရာ chunks များကို ရှာဖွေပြီး prompt ထဲ ထည့်သွင်းကာ ဖြေကြားချက် ထုတ်ပေးသည်။

**Check Source References**

ဖြေကြားချက်တိုင်းတွင် similarity score များပါသော အရင်းအမြစ် ကိုးကားချက်များ ပါဝင်သည်။ ဤ score များ (0 မှ 1 အထိ) သည် မေးခွန်းနှင့် chunk တစ်ခုချင်း၏ သက်ဆိုင်မှုကို ပြသသည်။ score မြင့်သည်ဆိုသည်မှာ ကိုက်ညီမှုကောင်းသည်။ ဤကဲ့သို့ဖြင့် ဖြေကြားချက်ကို အရင်းအမြစ်နှင့် နှိုင်းယှဉ်စစ်ဆေးနိုင်သည်။

<a href="images/rag-query-results.png"><img src="../../../translated_images/rag-query-results.6d69fcec5397f3558c788388bb395191616dad4c7c0417f1a68bd18590ad0a0e.my.png" alt="RAG Query Results" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Query ရလဒ်များ - ဖြေကြားချက်နှင့် အရင်းအမြစ် ကိုးကားချက်များ၊ သက်ဆိုင်မှု score များ*

**Experiment with Questions**

မေးခွန်းအမျိုးအစား မတူညီသော မေးခွန်းများ စမ်းသပ်ကြည့်ပါ -
- အတိအကျ အချက်အလက်များ: "အဓိက ခေါင်းစဉ်ကဘာလဲ?"
- နှိုင်းယှဉ်ချက်များ: "X နဲ့ Y ကြားက ကွာခြားချက်ကဘာလဲ?"
- အကျဉ်းချုပ်: "Z အကြောင်း အဓိက အချက်များကို အကျဉ်းချုပ်ပြပါ"

မေးခွန်းနှင့် စာရွက်စာတမ်းအကြောင်းအရာ၏ ကိုက်ညီမှုအပေါ် မူတည်၍ သက်ဆိုင်မှု score များ ပြောင်းလဲမှုကို ကြည့်ရှုပါ။

## Key Concepts

**Chunking Strategy**

စာရွက်စာတမ်းများကို ၃၀၀ token ပါသော chunks များသို့ ခွဲခြားပြီး ၃၀ token overlap ပါရှိသည်။ ဤညီမျှမှုသည် chunk တစ်ခုစီတွင် အဓိပ္ပာယ်ရှိသော context ရှိစေရန်နှင့် prompt တစ်ခုတွင် chunks များစွာ ထည့်သွင်းနိုင်ရန် အတွက် ဖြစ်သည်။

**Similarity Scores**

score များသည် 0 မှ 1 အထိဖြစ်သည် -
- 0.7-1.0: အလွန်သက်ဆိုင်မှုရှိပြီး တိကျသော ကိုက်ညီမှု
- 0.5-0.7: သက်ဆိုင်မှုရှိပြီး context ကောင်း
- 0.5 အောက်: ဖယ်ရှားထားပြီး မဆင်တူသော

စနစ်သည် အနည်းဆုံး threshold ထက် မြင့်သော chunks များကိုသာ ရှာဖွေသည်။

**In-Memory Storage**

ဤ module သည် ရိုးရှင်းမှုအတွက် in-memory storage ကို အသုံးပြုသည်။ application ကို ပြန်စတင်သောအခါ တင်သွင်းထားသော စာရွက်စာတမ်းများ ပျောက်ဆုံးသွားသည်။ ထုတ်လုပ်မှု စနစ်များတွင် Qdrant သို့မဟုတ် Azure AI Search ကဲ့သို့ persistent vector database များကို အသုံးပြုသည်။

**Context Window Management**

မော်ဒယ်တိုင်းတွင် အများဆုံး context window ရှိသည်။ စာရွက်စာတမ်းကြီးများမှ chunks အားလုံးကို ထည့်သွင်း၍ မရနိုင်ပါ။ စနစ်သည် အများဆုံး သက်ဆိုင်မှုရှိသော chunks N ခု (default 5) ကို ရွေးချယ်၍ အကန့်အသတ်အတွင်း ထည့်သွင်းကာ တိကျမှန်ကန်သော ဖြေကြားချက်များ ပေးနိုင်စေရန် ကြိုးစားသည်။

## When RAG Matters

**RAG ကို အသုံးပြုသင့်သောအခါ:**
- ကိုယ်ပိုင် စာရွက်စာတမ်းများအကြောင်း မေးခွန်းများ ဖြေဆိုရာတွင်
- အချက်အလက်များ မကြာခဏ ပြောင်းလဲသော (မူဝါဒများ၊ စျေးနှုန်းများ၊ အသေးစိတ်ဖော်ပြချက်များ)
- တိကျမှန်ကန်မှုအတွက် အရင်းအမြစ် ကိုးကားချက် လိုအပ်သောအခါ
- အကြောင်းအရာ ကြီးမား၍ prompt တစ်ခုတွင် ထည့်သွင်း၍ မရသောအခါ
- စစ်မှန်ပြီး အခြေခံထားသော ဖြေကြားချက်များ လိုအပ်သောအခါ

**RAG ကို မသုံးသင့်သောအခါ:**
- မော်ဒယ်တွင် ရှိပြီးသား အထွေထွေ သိပ္ပံပညာလိုအပ်သော မေးခွန်းများ
- အချိန်နှင့်တပြေးညီ အချက်အလက်လိုအပ်သောအခါ (RAG သည် တင်သွင်းထားသော စာရွက်စာတမ်းများပေါ်တွင် အလုပ်လုပ်သည်)
- အကြောင်းအရာ သေးငယ်၍ prompt ထဲတွင် တိုက်ရိုက် ထည့်သွင်းနိုင်သောအခါ

## Next Steps

**Next Module:** [04-tools - AI Agents with Tools](../04-tools/README.md)

---

**Navigation:** [← Previous: Module 02 - Prompt Engineering](../02-prompt-engineering/README.md) | [Back to Main](../README.md) | [Next: Module 04 - Tools →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**အကြောင်းကြားချက်**  
ဤစာတမ်းကို AI ဘာသာပြန်ဝန်ဆောင်မှု [Co-op Translator](https://github.com/Azure/co-op-translator) ဖြင့် ဘာသာပြန်ထားပါသည်။ ကျွန်ုပ်တို့သည် တိကျမှန်ကန်မှုအတွက် ကြိုးစားနေသော်လည်း၊ အလိုအလျောက် ဘာသာပြန်ခြင်းတွင် အမှားများ သို့မဟုတ် မှားယွင်းချက်များ ပါဝင်နိုင်ကြောင်း သတိပြုပါရန် မေတ္တာရပ်ခံအပ်ပါသည်။ မူရင်းစာတမ်းကို မိမိဘာသာစကားဖြင့်သာ တရားဝင်အချက်အလက်အဖြစ် သတ်မှတ်သင့်ပါသည်။ အရေးကြီးသော အချက်အလက်များအတွက် လူ့ဘာသာပြန်ပညာရှင်မှ ဘာသာပြန်ခြင်းကို အကြံပြုပါသည်။ ဤဘာသာပြန်ချက်ကို အသုံးပြုရာမှ ဖြစ်ပေါ်လာနိုင်သည့် နားလည်မှုမှားယွင်းမှုများအတွက် ကျွန်ုပ်တို့သည် တာဝန်မယူပါ။
<!-- CO-OP TRANSLATOR DISCLAIMER END -->