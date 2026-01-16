<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-06T01:35:24+00:00",
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

ယခင် module များတွင် AI နှင့် စကားပြောနည်းနှင့် prompt များကို ထိရောက်စွာ ဖွဲ့စည်းနည်းကို သင်ယူပြီးသားဖြစ်သည်။ သို့သော် ရိုးရိုးအတိုင်း တစ်ခုသော ကန့်သတ်ချက်ရှိသည်။ ဘာသာစကားလုပ်ဆောင်ချက်များသည် သင်ထားသည့် အတန်းသင်သင့်တင်ထားသည့် အချက်များကိုသာ သိရှိနိုင်ပြီး၊ သင်၏ကုမ္ပဏီ မူဝါဒများ၊ ပရောဂျက်စာရွက်စာတမ်းများ သို့မဟုတ် သင်မလေ့လာသေးသော အချက်အလက်များကို ဖြေဆို၍ မရနိုင်ပါ။

RAG (Retrieval-Augmented Generation) သည် ဤပြဿနာကို ဖြေရှင်းပေးသည်။ မော်ဒယ်ကို သင်၏ အချက်အလက်များ သင်ကြားကြမည့် တန်ဖိုးမြင့်သော်လည်း အသာခံရန်ခက်ခဲသော နည်းလမ်းကို မသုံးဘဲ၊ သင်၏စာရွက်စာတမ်းများအတွင်းမှ ရှာဖွေရန် စွမ်းဆောင်နိုင်စေသည်။ လူတစ်ယောက်မေးလျှင် ဆိုင်းစင် အချက်အလက်များကို ရှာဖွေပြီး prompt ထဲထည့်သည်။ မော်ဒယ်သည် လက်ခံထားသောအချက်အလက်အပေါ်မှ အဖြေကိုဖန်တီးပေးသည်။

RAG ကို မော်ဒယ်အား ကိုးကားစာကြည့်တိုက်တစ်ခု ပေးသည်ဟု ယူဆပါ။ မေးမြန်းသောအခါ -

1. **User Query** - သင်မေးသည်
2. **Embedding** - မေးခွန်းကို ဗက်တာ (vector) အဖြစ်ပြောင်းသည်
3. **Vector Search** - ဆင်တူသော စာရွက်ရာတို့ကို ရှာဖွေသည်
4. **Context Assembly** - ဆင်တူchunks များကို prompt ထဲသို့ ထည့်သည်
5. **Response** - LLM က ထို context အပေါ် အခြေခံပြီး အဖြေထုတ်ပြန်သည်

ဤနည်းစနစ်မှာ မော်ဒယ်၏ဖြေကြားချက်များကို သင်၏ရင်းမြစ်ဒေတာများပေါ်မှာ အခြေခံပေးပြီး training အတတ်ပညာသို့မဟုတ် ဖန်တီးထုတ်လုပ်ချက်မဟုတ်ကြောင်း သေချာစေသည်။

<img src="../../../translated_images/my/rag-architecture.ccb53b71a6ce407f.png" alt="RAG Architecture" width="800"/>

*RAG အလုပ်လုပ်ပုံ - အသုံးပြုသူမေးခွန်းမှ semantic search နှင့် context-based ဖြေချက်ထုတ်ပုံ*

## Prerequisites

- Module 01 ကိုပြီးမြောက်ထားပြီး (Azure OpenAI အရင်းအမြစ်များ တင်ဆောင်ပြီး)
- root directory တွင် Azure ကို credential ဖော်ပြထားသော `.env` ဖိုင်ရှိပြီး (Module 01 တွင် `azd up` ဖြင့် ဖန်တီးထားသည်)

> **Note:** Module 01 ကို မပြီးမြောက်ရသေးပါက၊ အဲဒီမှာပထမဦးစွာ deployment အညွှန်းများကို လိုက်နာပါ။

## How It Works

### Document Processing

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

စာရွက်တစ်ခု upload လုပ်သော အခါ၊ စနစ်သည် စာရွက်ကို chunk များသို့ ခွဲထုတ်သည် - မော်ဒယ်၏ context ပြတင်းပေါက်တွင်း ထည့်သွင်းနိုင်သော အရွယ်အစားသေးသေးသော အစိတ်အပိုင်းများဖြစ်သည်။ ထို chunks များသည် လျှော့နည်းစွာ ထပ်ပေါင်းထားသဖြင့် နယ်နိမိတ်တွင် context တွင်းအား လက်လွတ်ခြင်း မဖြစ်စေရန်ဖြစ်သည်။

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

### Creating Embeddings

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

chunk တစ်ခုချင်းစီကို embedding ဟုခေါ်သော နံပါတ်ထုတ်ပြန်ချက်တစ်ရပ်သို့ ပြောင်းလဲသည် - အဓိပ္ပါယ်ဖမ်းယူထားသည့် သင်္ချာကြိမ်စက်ခြယ်မှုတစ်ခုဖြစ်သည်။ ဆင်တူစာသားများအတွက် ဆင်တူ embedding များထွက်ရှိသည်။

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

<img src="../../../translated_images/my/vector-embeddings.2ef7bdddac79a327.png" alt="Vector Embeddings Space" width="800"/>

*စာရွက်များသည် embedding နေရာ၌ vector များဖြင့် ကိုယ်စားပြုထားပြီး ဆင်တူဖွဲ့စည်းမှုများ သဘောတူနေကြသည်*

### Semantic Search

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

မေးခွန်းမေးသောအခါ၊ မေးခွန်းကို embedding ဖြစ်သွားသည်။ စနစ်သည် မေးခွန်း embedding ကို စာရွက် chunks များ၏ embedding များနှင့် နှိုင်းယှဉ်သည်။ metric ဖြစ်သော semantic ဆင်တူမှုအတိုင်း အများဆုံး ဆင်တူသော chunks များ ရှာဖွေသည် - ကီးဝတ်အရာလုံးများကိုမဟုတ်ဘဲ အစစ်အမှန် အဓိပ္ပါယ်ဆင်တူမှုဖြစ်သည်။

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

### Answer Generation

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

အကြောင်းအရာအထိ relevance အမြင့်ဆုံး chunks များကို မော်ဒယ် prompt ထဲတွင် ထည့်သွင်းသည်။ မော်ဒယ်သည် ထို chunks များကို ဖတ်ပြီး မေးခွန်းကို ထိုအချက်အလက်အပေါ်မှ ကျွန်ုပ် များအဖြေဖန်တီးသည်။ ၎င်းသည် hallucination ဖြစ်ပွားမှုကို ကာကွယ်ပေးသည်။

## Run the Application

**deployment ကို စစ်ဆေးရန်:**

Module 01 မှာ ဖန်တီးထားသော root directory တွင် Azure credentials ပါသော `.env` ဖိုင် ရှိကြောင်း သိရှိပါစေ။
```bash
cat ../.env  # AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT ကို ပြရန် ဖြစ်သည်။
```

**Application စတင်ရန်:**

> **Note:** Module 01 တွင် `./start-all.sh` ဖြင့် application များအားလုံး စတင်ထားပြီးခဲ့ပါက၊ ယခု module သည် port 8081 တွင် ပြေးဆာနေပြီးဖြစ်သည်။ အောက်ပါ စတင်ဖို့ cmd များကို မလိုအပ်သလို https://localhost:8081 သို့ တိုက်ရိုက်သွားနိုင်ပါသည်။

**Option 1: Spring Boot Dashboard အသုံးပြုခြင်း (VS Code အသုံးပြုသူအတွက် အကြံပြုသည်)**

dev container တွင် Spring Boot Dashboard Extension ပါဝင်ပြီး VS Code ၏ ဘယ်ဘက် Activity Bar မှာ Spring Boot အိုင်ကွန်ကိုတွေ့ရမည်။

Dashboard မှ -
- workspace တွင်ရှိသည့် Spring Boot applications များ လုံးဝဖော်ပြပါမည်
- လက်နှိပ်ပြီး applications များ စတင်/ရပ်တန့်နိုင်မည်
- application log များကို အချိန်နဲ့တပြေးညီကြည့်ရှုနိုင်မည်
- application အခြေအနေကို ကြည့်ရှု၊ စောင့်ကြည့်နိုင်မည်

"rag" app အနီးက play button ကို နှိပ်၍ module ကို စတင်ပါ၊ ဒါမှမဟုတ် လုံးဝ module များအားလုံးကို တစ်ပြိုင်နက် စတင်ပါ။

<img src="../../../translated_images/my/dashboard.fbe6e28bf4267ffe.png" alt="Spring Boot Dashboard" width="400"/>

**Option 2: shell script များ အသုံးပြုခြင်း**

Web application များအားလုံး စတင်ရန် (module 01-04):

**Bash:**
```bash
cd ..  # မူလ ဒါရိုက်တာထဲမှ
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # အရာမူလဖိုင်ညွှန်းတည်နေရာမှ
.\start-all.ps1
```

မှတ်အညွှန်း - သို့မဟုတ် ယခု module ကို ဖန်တီးရန်:

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

script နှစ်ခုလုံးသည် root `.env` ဖိုင်မှ environment variables များကို အလိုအလျောက် load ပြီး JAR များ မရှိလျှင် build လုပ်ပါသည်။

> **Note:** မဖြစ်သေးသည့် modules များအား လက်ျာပိုင် build ပြုလုပ်ပြီးစဥ်:
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

http://localhost:8081 ကို browser မှာ ဖွင့်ပါ။

**ရပ်ရန်:**

**Bash:**
```bash
./stop.sh  # ဤမော်ဂျူးလ်သာ
# သို့မဟုတ်
cd .. && ./stop-all.sh  # မော်ဂျူးလ်များအားလုံး
```

**PowerShell:**
```powershell
.\stop.ps1  # ဤမော်ဂျူးလ်သာ
# သို့မဟုတ်
cd ..; .\stop-all.ps1  # မော်ဂျူးလ်အားလုံး
```

## Using the Application

application သည် စာရွက် upload နှင့် မေးခွန်းမေးခြင်းအတွက် web interface ပံ့ပိုးသည်။

<a href="images/rag-homepage.png"><img src="../../../translated_images/my/rag-homepage.d90eb5ce1b3caa94.png" alt="RAG Application Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*RAG application interface - စာရွက် upload လုပ်၍ မေးခွန်းမေးပါ*

### Upload a Document

စာရွက်တစ်ခုကို ထည့်ပါ - စမ်းသပ်ရန် အတွက် TXT ဖိုင်များ အသုံးပြုရလွယ်သည်။ ဤ directory တွင် LangChain4j features, RAG implementation နှင့် လုပ်ထုံးလုပ်နည်းများပါဝင်သည့် `sample-document.txt` ရှိသည်၊ စနစ် စမ်းသပ်ရန် ထူးခြားသောဖြစ်စေသည်။

စနစ်သည် စာရွက်ကို ခွဲထုတ်ပြီး chunks များ ဖန်တီးပြီး တစ်ချက်ချင်းစီ အတွက် embedding များ ဖန်တီးပေးမည်ဖြစ်သည်။ ၎င်းလုပ်ငန်းစဉ်သည် upload လုပ်တာနှင့်အလိုက် အလိုအလျောက် ဖြစ်ပေါ်သည်။

### Ask Questions

ယခု သင့်အား စာရွက်အကြောင်း ကျယ်ကျယ်ပြန့်ပြန့် မေးခွန်းများမေးနိုင်ပါသည်။ စာရွက်တွင် ဖော်ပြထားသော အချက်အလက်များ အခြေခံ၍ မေးမြန်းပါ။ စနစ်သည် ဆက်စပ်သော chunks များကို ရှာဖွေပြီး prompt ထဲ ထည့်ပြီး အဖြေထုတ်ပေးမည်ဖြစ်သည်။

### Check Source References

အဖြေတိုင်းတွင် ဆော့စွဲရာများနှင့် similarity score များပါဝင်သည်။ score များသည် 0 မှ 1 အတွင်း ရှိပြီး မေးခွန်းနှင့် ဆက်စပ်မှုကို ဖော်ပြသည်။ score မြင့်သည့် chunks များသည် ပိုမိုပေါက်ဖ်သော ဆက်စပ်မှုရှိကြောင်း သက်သေပြသည်။ ထို့ကြောင့် မူရင်း စာရွက်နှင့် နှိုင်းယှဉ်စစ်ဆေးနိုင်သည်။

<a href="images/rag-query-results.png"><img src="../../../translated_images/my/rag-query-results.6d69fcec5397f355.png" alt="RAG Query Results" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Query ရလဒ်များ - အဖြေ၊ ဆော့စွဲရာများနှင့် relevance score များ ဖော်ပြထားခြင်း*

### Experiment with Questions

မေးခွန်းအမျိုးအစားတစ်မျိုးစီ စမ်းသပ်ကြည့်ပါ -
- တိကျသော အချက်အလက်များ: "အဓိက အကြောင်းအရာ ဘာလဲ?"
- နှိုင်းယှဉ်မှု: "X နှင့် Y တွင် ကွာခြားချက် ဘာတွေရှိသလဲ?"
- ဆောင်းပါး အကျဉ်းချုပ်: "Z အကြောင်း အချက်အလက်များ အကျဉ်းချုပ် ပြပါ"

မေးခွန်းနှင့် စာရွက်အကြောင်းအရာ တိကျမှုအပေါ်မှ Relevance score များ ပြောင်းလဲသွားသည်ကို သတိပြုကြည့်ရှုပေးပါ။

## Key Concepts

### Chunking Strategy

စာရွက်များကို ၃၀၀ token ပါသော chunks အဖြစ် ခွဲခြားပြီး ၃၀ token overlap ပါရှိသည်။ ၎င်းသည် context ကြီးမားမှုနှင့် prompt ထဲတွင် နေရာလုံလောက်မှု နှစ်ခုလုံးကို ညှိနှိုင်းထားသော နည်းဖြစ်သည်။

### Similarity Scores

Score များသည် 0 မှ 1 အတွင်းဖြစ်သည် -
- 0.7-1.0: အလွန်ဆက်စပ်မှုရှိသော၊ တိကျသော ကိုက်ညီမှု
- 0.5-0.7: ဆက်စပ်မှုရှိသော၊ အေကာင္းမြင်သော context
- 0.5 မပြည့်သော: ဖြတ်ထုတ်ခြင်း၊ မဆင်တူသော

စနစ်သည် minimum threshold အထက်ရှိသော chunks များကိုသာ ပြန်လည်ထုတ်ယူသည်။

### In-Memory Storage

ဤ module သည် ရိုးရှင်းအတွက် in-memory သိုလှောင်မှုကို အသုံးပြုသည်။ application ကို ပြန်စတင်သောအခါ upload လုပ်ထားသော စာရွက်အားလုံး ပျောက်ကွယ်ပါမည်။ ထုတ်လုပ်မှုတွင် persistent vector database များ (ဥပမာ Qdrant သို့ Azure AI Search) ကို အသုံးပြုသည်။

### Context Window Management

မော်ဒယ်ပေါင်းစံာ်မှု context window ကန့်သတ်ချက်ရှိသည်။ ကြီးမားသော စာရွက်များမှ လုံးဝ chunk များကို မရနိုင်ပေ။ စနစ်သည် relevance အငွေ့အကြောင်းခံအမြင့်ဆုံး chunks (ပုံမှန်အားဖြင့် ၅ ခု) ကို ရွေးယူ စနစ်တကျ ထည့်သွင်းကာ တိကျမှန်ကန်သော ဖြေမှုများဘေးကင်းစေသည်။

## When RAG Matters

**RAG ကို သုံးစွဲသင့်သော အချိန်များမှာ**
- မူပိုင်စာရွက်များအတွက် စုံစမ်းမေးမြန်းရာ
- အချက်အလက်များ ကြာနိုင်လျှင် (မူဝါဒများ၊ စျေးနှုန်းများ၊ ဖော်ပြချက်များ)
- တိကျမှန်ကန်မှုအတွက် ရင်းမြစ်ကိုးကားမှု လိုအပ်ခြင်း
- အကြောင်းအရာ ကြီးမားခြင်းကြောင့် တစ်ခုတည်း prompt တွင် မထည့်နိုင်သောအခါ
- အတည်ပြုနိုင်သော ဖြေကြားချက်များ လိုအပ်သည်

**RAG ကို မသုံးသင့်သောအချိန်များမှာ**
- မော်ဒယ်မှာ ရှိပြီးသား သာမာန် အသိပညာလို မေးခွန်းများ
- အချိန်နဲ့အမျှ အချက်အလက်တင်ပြချက် လိုလားခြင်း (RAG သည် upload လုပ်ထားသော စာရွက် များအပေါ် အခြေခံသည်)
- အကြောင်းအရာ ကျဉ်းမြောင်းလွယ်၍ တိုက်ရိုက် prompt တွင် ထည့်နိုင်သောအခါ

## Next Steps

**Next Module:** [04-tools - AI Agents with Tools](../04-tools/README.md)

---

**Navigation:** [← Previous: Module 02 - Prompt Engineering](../02-prompt-engineering/README.md) | [Back to Main](../README.md) | [Next: Module 04 - Tools →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**သတိပေးချက်**  
ဤစာတမ်းကို AI ဘာသာပြန်ဝန်ဆောင်မှု [Co-op Translator](https://github.com/Azure/co-op-translator) ဖြင့် ဘာသာပြန်ထားပါသည်။ တိကျမှုကို ကြိုးစားရေ့ဆိုပေမယ့် အလိုအလျောက်ဘာသာပြန်ခြင်းမှာ အမှားများ သို့မဟုတ် တိကျမှုမရှိမှုများ ဖြစ်ပေါ်နိုင်ကြောင်း သတိပြုပါ။ မူရင်းစာတမ်းကို သူ့မိခင်ဘာသာဖြင့်သာ အာဏာပိုင်အရင်းအမြစ်အဖြစ် စဉ်းစားသင့်ပါသည်။ အရေးကြီးသော သတင်းအချက်အလက်များအတွက်တော့ ပရော်ဖက်ရှင်နယ် လူ့ဘာသာပြန်သူအား စီစစ်အသုံးပြုရန် အကြံပြုပါသည်။ ဤဘာသာပြန်ချက်ကိုအသုံးပြုခြင်းကြောင့် ဖြစ်ပေါ်လာနိုင်သည့် နားမလည်မှုများ သို့မဟုတ် မမှန်ကန်မှုများအတွက် ကျွန်ုပ်တို့သည် တာဝန်မခံပါ။
<!-- CO-OP TRANSLATOR DISCLAIMER END -->