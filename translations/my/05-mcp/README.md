<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "6c816d130a1fa47570c11907e72d84ae",
  "translation_date": "2026-01-06T01:36:17+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "my"
}
-->
# မော်ဂျူး ၀၅: မော်ဒယ် ကွန်တက် ပရိုတိုကော (MCP)

## အကြောင်းအရာရိုး

- [သင် သင်ယူမည့်အချက်များ](../../../05-mcp)
- [MCP ဆိုတာဘာလဲ?](../../../05-mcp)
- [MCP က ဘယ်လို အလုပ်လုပ်သလဲ?](../../../05-mcp)
- [Agentic Module](../../../05-mcp)
- [နမူနာများကို ပြေးဆွဲခြင်း](../../../05-mcp)
  - [လိုအပ်ချက်များ](../../../05-mcp)
- [အမြန် စတင်မှု](../../../05-mcp)
  - [ဖိုင် လုပ်ဆောင်မှုများ (Stdio)](../../../05-mcp)
  - [Supervisor Agent](../../../05-mcp)
    - [အထွက် ထုံးတမ်းကို နားလည်ခြင်း](../../../05-mcp)
    - [တုံ့ပြန်မှု မျှော်လင့်ချက်များ](../../../05-mcp)
    - [Agentic Module အင်္ဂါရပ်များ ရှင်းလင်းချက်](../../../05-mcp)
- [အဓိက အယူအဆများ](../../../05-mcp)
- [ကြိုဆိုပါတယ်!](../../../05-mcp)
  - [နောက်တစ်ဆင့်?](../../../05-mcp)

## သင် သင်ယူမည့်အချက်များ

သင်သည် စကားပြော AI တစ်ခုကို တည်ဆောက်ပြီး၊ prompt များကို ကျွမ်းကျင်စွာ အသုံးပြုနိုင်ရန်၊ စာရွက်စာတမ်းများအတွင်း ရှိသော တုံ့ပြန်ချက်များကို အခြေခံနိုင်ချိန်၊ နှင့် ကိရိယာများပါ အသုံးပြုနိုင်သော agent များကိုဖန်တီးနိုင်ခဲ့ပါပြီ။ သို့သော် အဲဒီ ကိရိယာများအားလုံးကို သင်၏ အထူး application အတွက် အထူးပြုလုပ်ထားခြင်းဖြစ်သည်။ သင်၏ AI ကို ဘယ်သူမဆို ဖန်တီးနိုင်ပြီးမျှဝေပါက စံချိန်မီ ကိရိယာစနစ်သို့ ရောက်ရှိနိုင်စေမည်ဆိုပါက၊ ဘယ်လိုလုပ်နိုင်မလဲ? ဤ မော်ဂျူးတွင် သင်သည် Model Context Protocol (MCP) နှင့် LangChain4j ၏ agentic module ကို အသုံးပြု၍ အဲဒီ အရာကို မည်သို့လုပ်နိုင်သည်ကို သင် လေ့လာမည်ဖြစ်သည်။ အများအပြား မော်ဂျူးများမှ MCP ဖိုင်ဖတ်သူ တစ်ခုကို မကြာခဏ ပြသပြီး နောက်ပိုင်းမှာ Supervisor Agent စနစ်ကို အသုံးပြုပြီး မြင့်မားသော agentic workflow များနှင့် အလွယ်တကူ ပေါင်းစည်းနိုင်မှုကို ပြသသည်။

## MCP ဆိုတာဘာလဲ?

Model Context Protocol (MCP) သည် ထိုပုံစံကို ပေးသည် - AI application များအတွက် ပြင်ပ ကိရိယာများကို ရှာဖွေပြီး အသုံးပြုနိုင်ရန် စံတော်ချိန်ဖြစ်သည်။ တစ်ခုချင်းစီ data source သို့မဟုတ် ဝန်ဆောင်မှုအတွက် စိတ်တိုင်းကျ ပေါင်းစည်းမှုများရေးသားရန် မလိုတော့ပဲ MCP servers များနှင့် ချိတ်ဆက်ခြင်းဖြင့် ၎င်းတို့၏ တတ်နိုင်စွမ်းများကို သိသာထင်ရှားသော ပုံစံဖြင့် ဖော်ပြထားသည်။ သင်၏ AI agent မှ ထိုကိရိယာများကို အလိုအလျောက် ရှာဖွေပြီး အသုံးပြုနိုင်သည်။

<img src="../../../translated_images/my/mcp-comparison.9129a881ecf10ff5.png" alt="MCP ပြိုင်ဆိုင်မှု" width="800"/>

*MCP မတနေ့မှီ: ရိုးရှင်းမှုမရှိသော တိုက်တိုက်ဆိုင်ဆိုင် ပေါင်းစည်းမှုများ။ MCP ထွက်ရှိပြီးနောက်: စံချိန်တစ်ခု၊ အဆုံးမဲ့ အလားအလာများ။*

MCP သည် AI ဖွံ့ဖြိုးရေးတွင် မူလ ပြဿနာတစ်ရပ်ကို ဖြေရှင်းသည်။ အပြည့်အဝ ပေါင်းစည်းချက်တိုင်းကို စိတ်တိုင်းကျရေးသားရတယ်။ GitHub ကို ဝင်ရောက်ချင်လား? စိတ်တိုင်းကျ နှိပ်ပေးထားသော ကုဒ်။ ဖိုင်ဖတ်ချင်လား? စိတ်တိုင်းကျကုဒ်။ ဒေတာဘေ့စ်ကို မေးချင်လား? စိတ်တိုင်းကျကုဒ်။ ၎င်း ပေါင်းစည်းမှုများ ထိုအခြား AI application များနှင့် ကိုက်ညီမှု မရှိဘူး။

MCP သည် ၎င်းတို့အား စံချိန်တင်ပေးသည်။ MCP server တစ်ခုသည် ကိရိယာများကို ဖော်ပြသော ဖော်ပြချက်နှင့် စံစနစ်များဖြင့် ပြပေးသည်။ MCP client များ မည်သည့်လူမျိုးမဆို ချိတ်ဆက်ပြီး အသုံးပြုနိုင်ပြီး အဲဒီကိရိယာများအား ရှာဖွေနိုင်သည်။ တစ်ကြိမ် ဆောက်ပြီး နေရာတိုင်းအသုံးပြုမည်။

<img src="../../../translated_images/my/mcp-architecture.b3156d787a4ceac9.png" alt="MCP ဖွဲ့စည်းပုံ" width="800"/>

*Model Context Protocol ဖွဲ့စည်းပုံ - စံပြ ကိရိယာ ရှာဖွေရေးနှင့် အကောင်အထည် ဖော်ဆောင်ခြင်း*

## MCP က ဘယ်လို အလုပ်လုပ်သလဲ?

**Server-Client ဖွဲ့စည်းပုံ**

MCP သည် client-server မော်ဒယ်ကို အသုံးပြုသည်။ Server များမှာ ကိရိယာများ - ဖိုင်ဖတ်ခြင်း၊ ဒေတာဘေ့စ်မေးခြင်း၊ API များခေါ်ဆိုခြင်းတို့ကို ပေးသည်။ Client များ (သင်၏ AI application) သည် server များနှင့် ချိတ်ဆက်ပြီး ၎င်းတို့၏ ကိရိယာများကို အသုံးပြုသည်။

LangChain4j နှင့် MCP ကို အသုံးပြုရန်၊ Maven dependency ကို ထည့်သွင်းပါ။

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**ကိရိယာ ရှာဖွေရေး**

သင်၏ client သည် MCP server တစ်ခုနှင့် ချိတ်ဆက်သောအခါ “မည်သည့် ကိရိယာများရှိပါသလဲ?” ဟု မေးသည်။ Server သည် ရနိုင်သော ကိရိယာများ စာရင်းတစ်ခုအား ဖော်ပြပြီး၊ ဖော်ပြချက်များနှင့် parameter schema များပါတော့သည်။ သင်၏ AI agent သည် user တောင်းဆိုမှုအလိုက် သုံးမည့် ကိရိယာများကို ဆုံးဖြတ်နိုင်သည်။

**ယာဉ်ပို့ စနစ်များ**

MCP သည် ကွဲပြားသော ယာဉ်ပို့ စနစ်များကို ထောက်ခံသည်။ ဤမော်ဂျူးတွင် Stdio transport ကို ပြသထားပြီး ဒေသတွင်းလုပ်ငန်းများအတွက် အသုံးပြုသည်။

<img src="../../../translated_images/my/transport-mechanisms.2791ba7ee93cf020.png" alt="ယာဉ်ပို့ စနစ်များ" width="800"/>

*MCP ယာဉ်ပို့စနစ်များ: မည်သည့် HTTP ကို remote server များအတွက်, Stdio ကို ဒေသတွင်းလုပ်ငန်းများအတွက်*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

ဒေသတွင်းလုပ်ငန်းများ အတွက်။ သင့် application မှ subprocess အနေနှင့် server တစ်ခုကို ဖန်တီးပြီး၊ standard input/output မှတဆင့် ဆက်သွယ်သည်။ ဖိုင်စနစ် ဝင်ရောက်မှု သို့မဟုတ် command-line ကိရိယာများအတွက် အသုံးဝင်သည်။

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@2025.12.18",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 [GitHub Copilot](https://github.com/features/copilot) Chat ဖြင့် စမ်းသပ်ကြည့်ပါ:** [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) ဖိုင်ကို ဖွင့်ပြီး မေးမြန်းပါ-
> - "Stdio transport က ဘယ်လို အလုပ်လုပ်သလဲ၊ HTTP နဲ့ မတူဘူး ဘယ်အချိန်မှာ သုံးသင့်လဲ?"
> - "LangChain4j သည် MCP server process များကို ဘယ်လို စီမံထိန်းချုပ်သလဲ?"
> - "AI ကို file system ဝင်ခွင့်ပေးခြင်း၏ လုံခြုံရေး အခြေအနေများ ဘာတွေလဲ?"

## Agentic Module

MCP သည် စံချိန်တင်ထားသော ကိရိယာများပေးသော်လည်း LangChain4j ၏ **agentic module** သည် ၎င်းကိရိယာများကို စပ်ဆင်နိုင်သော agent များ ဖန်တီးရန် ကြေညာပုံစံနည်းလမ်းကို ပေးသည်။ `@Agent` အညွှန်းချက်နှင့် `AgenticServices` မှတဆင့် agent အပြုအမူကို interface များအား အသုံးပြု၍ သတ်မှတ်နိုင်သည်။

ဤမော်ဂျူးတွင် သင် စူးစမ်းမည်မှာ **Supervisor Agent** စနစ် ဖြစ်ပြီး၊ မြင့်မားသော agentic AI အမျိုးအစားဖြစ်သည်။ "supervisor" agent သည် user တောင်းဆိုမှုအပေါ် မူတည်၍ sub-agent များကို dynamic အခြေအနေဖြင့် ခေါ်ယူသည်။ ကျွန်ုပ်တို့သည် MCP ဖြင့် စေ့စပ်ထားသော file access အား သုံးသော sub-agent တစ်ခုနှင့်ပေါင်းပြီး ဒီနှစ်ခုကို ပေါင်းစပ်ပြသမည်။

agentic module ကို အသုံးပြုရန် Maven dependency ထည့်သွင်းပါ-

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ စမ်းသပ်ရေးအဆင့်:** `langchain4j-agentic` module သည် **စမ်းသပ်ရေးအဆင့်** ဖြစ်ပြီး မပြောင်းလဲနိုင်သော အရာဖြစ်ပါသည်။ AI အကူအညီများ ဆောက်ရန် ယုံကြည်စိတ်ချရသော နည်းလမ်းမှာ `langchain4j-core` ကို Module 04 မှာ သုံးသပ်ထားသော အမျိုးအစားဖြစ်သည်။

## နမူနာများကို ပြေးဆွဲခြင်း

### လိုအပ်ချက်များ

- Java 21+, Maven 3.9+
- Node.js 16+ နှင့် npm (MCP server များအတွက်)
- `.env` ဖိုင်တွင် environment variable များ သတ်မှတ်ထားရန် (root directory မှ):
  - `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (Module 01-04 အတူ)

> **မှတ်ချက်:** သင်၏ environment variable များအား မသတ်မှတ်ရသေးပါက၊ [Module 00 - Quick Start](../00-quick-start/README.md) လမ်းညွှန်ချက်များကို ကြည့်ပါ၊ ဒါမှမဟုတ် `.env.example` ကို root directory တွင် `.env` အဖြစ် ကူးပြောင်းပြီး သင့် အချက်အလက်များဖြည့်ပါ။

## အမြန် စတင်မှု

**VS Code အသုံးပြုခြင်း:** Explorer တွင် နမူနာဖိုင်တစ်ခု ဆိုင်ရာကို right-click ပြုလုပ်၍ **"Run Java"** ကို ရွေးပါ၊ ဒါမှမဟုတ် Run and Debug panel ရှိ launch configuration များကို အသုံးပြုပါ (ပထမဦးဆုံး `.env` ဖိုင်တွင် သင့် token ထည့်ပြီးဖြစ်သင့်သည်)။

**Maven အသုံးပြုခြင်း:** ဒါမှမဟုတ် command line မှ ရော်ထုတ်ထားသော နမူနာများကို အောက်မှာ ပြထားသည်အတိုင်း ပြေးဆွဲနိုင်သည်။

### ဖိုင် လုပ်ဆောင်မှုများ (Stdio)

ဤမှာ ဒေသတွင်း subprocess-အခြေခံ ကိရိယာများကို ပြသသည်။

**✅ လိုအပ်ချက်မရှိ** - MCP server သည် အလိုအလျောက် spawn လုပ်သည်။

**စတင် script များအသုံးပြုခြင်း (推奨):**

စတင် script များသည် root `.env` ဖိုင်မှ environment variable များကို အလိုအလျောက် load လုပ်သည်-

**Bash:**
```bash
cd 05-mcp
chmod +x start-stdio.sh
./start-stdio.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start-stdio.ps1
```

**VS Code အသုံးပြုခြင်း:** `StdioTransportDemo.java` ကို right-click ပြုလုပ်ပြီး **"Run Java"** ကို ရွေးပါ (.env ဖိုင် သတ်မှတ်ထားပြီးဖြစ်ရမည်)။

Application သည် ဖိုင်စနစ် MCP server ကို အလိုအလျောက် spawn လုပ်ပြီး ဒေသတွင်းဖိုင်ကို ဖတ်သည်။ subprocess စီမံခန့်ခွဲမှုကို သင့်အတွက် စီမံဦးစီးသည်ကို တွေ့မြင်ရမည်။

**မျှော်မှန်းထားသော အထွက်:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Supervisor Agent

**Supervisor Agent pattern** သည် **တတ်ကြွ** သော agentic AI ပုံစံဖြစ်သည်။ Supervisor သည် LLM တစ်ခု အသုံးပြု၍ user ၏ တောင်းဆိုချက်အပေါ် မူတည်၍ ဘယ် agent များကို ခေါ်ဆောင်မည်ကို ကိုယ်တိုင် ဆုံးဖြတ်သည်။ နောက်ထပ်နမူနာတွင် MCP-powered ဖိုင် access နှင့် LLM agent ကို ပေါင်းစပ်၍ supervised file read → report workflow တစ်ခု ဖန်တီးသွားမည်ဖြစ်သည်။

Demo တွင် `FileAgent` သည် MCP ဖိုင်စနစ် ကိရိယာများ အသုံးပြုပြီး ဖိုင်ဖတ်သည်၊ `ReportAgent` သည် အကွပ်အတည်းနှင့် အကြောင်းပြချက်ပါ ပြုစုထားသော စီစဥ်ချက်တစ်ခုကို ေမြ။ Supervisor သည် ဒီလည်ပတ်မှုကို အလိုအလျောက် စီမံခန့်ခွဲသည်။

<img src="../../../translated_images/my/agentic.cf84dcda226374e3.png" alt="Agentic Module" width="800"/>

```
┌─────────────┐      ┌──────────────┐
│  FileAgent  │ ───▶ │ ReportAgent  │
│ (MCP tools) │      │  (pure LLM)  │
└─────────────┘      └──────────────┘
   outputKey:           outputKey:
  'fileContent'         'report'
```

Agent တစ်ခုချင်းစီသည် **Agentic Scope** (မျှဝေသော မှတ်ဉာဏ်) ထဲတွင် output များကို သိမ်းဆည်းသည်၊ ဤသည်သည် အောက်လွှတ် agents များကို အရင်ဆုံး ရလဒ်များရရှိမှုအတွက် access ပေးသည်။ MCP ကိရိယာများကို agentic workflow များထဲသို့ မည်သို့ စပ်ဆင်ထားသည်ကို ပြသသည် - Supervisor သည် ဖိုင်ဘယ်လို ဖတ်သည်ကို မသိပေမည့် `FileAgent` သည် ဖတ်နိုင်သည်ကိုသာ သိရှိရန် လိုသည်။

#### Demo ပြေးဆွဲခြင်း

စတင် script များသည် root `.env` ဖိုင်မှ environment variable များကို အလိုအလျောက် load လုပ်သည်-

**Bash:**
```bash
cd 05-mcp
chmod +x start-supervisor.sh
./start-supervisor.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start-supervisor.ps1
```

**VS Code အသုံးပြုခြင်း:** `SupervisorAgentDemo.java` ကို right-click ပြုလုပ်ပြီး **"Run Java"** ကို ရွေးပါ (.env ဖိုင် သတ်မှတ်ထားပြီးဖြစ်ရမည်)။

#### Supervisor သည် ဘယ်လိုအလုပ်လုပ်သလဲ

```java
// အဆင့် ၁: FileAgent သည် MCP ကိရိယာများကို အသုံးပြုပြီး ဖိုင်များအား ဖတ်ရှုသည်
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // ဖိုင်ဆိုင်ရာ လုပ်ဆောင်ချက်များအတွက် MCP ကိရိယာများရှိသည်
        .build();

// အဆင့် ၂: ReportAgent သည် ဖွဲ့စည်းထားသောအစီရင်ခံစာများ ထုတ်ပေးသည်
ReportAgent reportAgent = AgenticServices.agentBuilder(ReportAgent.class)
        .chatModel(model)
        .build();

// Supervisor သည် ဖိုင် → အစီရင်ခံစာ လုပ်ငန်းစဉ်ကို စီမံခန့်ခွဲသည်
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)
        .subAgents(fileAgent, reportAgent)
        .responseStrategy(SupervisorResponseStrategy.LAST)  // နောက်ဆုံး အစီရင်ခံစာကို ပြန်ပေးပို့သည်
        .build();

// Supervisor သည် တောင်းဆိုချက်အပေါ် မူတည်၍ ကိုယ်စားလှယ်များကို ဖိတ်ခေါ်ရန်ဆုံးဖြတ်သည်
String response = supervisor.invoke("Read the file at /path/file.txt and generate a report");
```

#### တုံ့ပြန်မှု မျှော်လင့်ချက်များ

`SupervisorAgent` ကို သတ်မှတ်သောအခါ၊ sub-agent များ အလုပ်ပြီးစီးပြီးနောက် user ထံ ပေးပို့မည့် နောက်ဆုံး ဖြေရာပုံစံကို ဖြည့်စွက်ပေးသည်။ ရရှိနိုင်သော မျှော်လင့်ချက်များမှာ -

| မျှော်လင့်ချက် | ရှင်းလင်းချက် |
|----------------|----------------|
| **LAST** | Supervisor သည် နောက်ဆုံးခေါ်ယူသော sub-agent သို့မဟုတ် tool ၏ output ကို ပြန်ပေးသည်။ ၎င်းကို workflow ၏ နောက်ဆုံးသော agent သည် ထိပ်ဆုံးဖြေရှင်းချက် တစ်ခုထုတ်ပေးရန် သတ်မှတ်ထားသော အခါ အသုံးဝင်သည် (ဥပမာ- ရှာဖွေရေး pipeline တွင် "Summary Agent")။ |
| **SUMMARY** | Supervisor သည် ၎င်း၏ ကိုယ်ပိုင် အတွင်းပိုင်း Language Model (LLM) ကို သုံးကာ interaction ရဲ့ အနှစ်ချုပ်နှင့် sub-agent output များအား စုပေါင်းပြီး အောက်ဆုံး ဖြေကို ပြန်ပေးသည်။ ၎င်းသည် သန့်ရှင်းသော ပေါင်းစပ်ထားသော ဖြေရှင်းချက်တစ်ခုကို user ထံ ပေးသည်။ |
| **SCORED** | စနစ်သည် အတွင်းပိုင်း LLM ကို သုံးကာ LAST တုံ့ပြန်ချက်နှင့် SUMMARY တုံ့ပြန်ချက်ကို user ၏ မူလ တောင်းဆိုမှုနှင့် နှိုင်းယှဉ်ပြီး အရမှတ် ပိုမြင့်သော output ကို ပြန်ပေးသည်။ |

ပြည့်စုံသော အကောင်အထည်ဖော်မှုအတွက် [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) ကို ကြည့်ပါ။

> **🤖 [GitHub Copilot](https://github.com/features/copilot) Chat ဖြင့် စမ်းသပ်ကြည့်ပါ:** [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) ဖိုင်ကို ဖွင့်ပြီး မေးမြန်းပါ-
> - "Supervisor သည် မည်သို့ sub-agent များကို ခေါ်ယူမည်ကို ဆုံးဖြတ်သလဲ?"
> - "Supervisor နှင့် Sequential workflow pattern များအကြား မည်သို့ကွာခြားသည်နည်း?"
> - "Supervisor ၏ လုပ်ငန်းစီမံခန့်ခွဲမှုကို မည်သို့ ကိုယ်ပိုင် ပြုလုပ်နိုင်သနည်း?"

#### အထွက် ထုံးတမ်းကို နားလည်ခြင်း

Demo ကို ပြေးဆိုင်းသောအခါ Supervisor သည် agents များစွာကို မည်သို့ ဆောင်ရွက်သည်ကို ရှင်းလင်းပြသသော လမ်းညွှန်မှုတစ်ခုကို တွေ့မြင်မည်ဖြစ်သည်။ အပိုင်းတိုင်းသည် ဘာကို ဆိုလိုသနည်း။

```
======================================================================
  FILE → REPORT WORKFLOW DEMO
======================================================================

This demo shows a clear 2-step workflow: read a file, then generate a report.
The Supervisor orchestrates the agents automatically based on the request.
```

**ခေါင်းစဉ်** သည် workflow အယူအဆကို မိတ်ဆက်သည် - ဖိုင်ဖတ်ခြင်းမှ အစပြီး အစီရင်ခံစာ ထုတ်ပေးခြင်းဖြစ်သည်။

```
--- WORKFLOW ---------------------------------------------------------
  ┌─────────────┐      ┌──────────────┐
  │  FileAgent  │ ───▶ │ ReportAgent  │
  │ (MCP tools) │      │  (pure LLM)  │
  └─────────────┘      └──────────────┘
   outputKey:           outputKey:
   'fileContent'        'report'

--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]   FileAgent   - Reads files via MCP → stores in 'fileContent'
  [REPORT] ReportAgent - Generates structured report → stores in 'report'
```

**Workflow Diagram** သည် agent များအကြား ဒေတာမှုပေါင်းစည်းမှုကို ပြသသည်။ ရှိသည့် agent တစ်ခုချင်းစီ၏ တာဝန်များမှာ:
- **FileAgent** သည် MCP ကိရိယာများဖြင့် ဖိုင်များဖတ်ပြီး ရိုးရှင်းသော အကြောင်းအရာကို `fileContent` တွင် သိမ်းဆည်းသည်။
- **ReportAgent** သည် ထိုအကြောင်းအရာကို လက်ခံပြီး `report` တွင် ဖော်ပြချက် တစ်ရပ်ထုတ်ပေးသည်။

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and generate a report on its contents"
```

**User တောင်းဆိုချက်** သည် အလုပ်အကိုင်ကို ပြသသည်။ Supervisor သည် ၎င်းကို ခွဲခြမ်းပြီး `FileAgent` → `ReportAgent` သို့ ခေါ်ယူရန် ဆုံးဖြတ်သည်။

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor decides which agents to invoke and passes data between them...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source, provider-agnostic Java framework for building LLM...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> ReportAgent (generating structured report)
  |
  |   Input: LangChain4j is an open-source, provider-agnostic Java framew...
  |
  |   Result: Executive Summary...
  +-- [OK] ReportAgent (generating structured report) completed
```

**Supervisor ဆောင်ရွက်မှု** သည် ထို ၂ ကျိုးသက်ရောက်မှုရှိသော လည်ပတ်မှုကို ပြသသည်-
1. **FileAgent** သည် MCP မှ ဖိုင်ကို ဖတ်ပြီး အကြောင်းအရာ သိမ်းဆည်းသည်
2. **ReportAgent** သည် ထိုအကြောင်းအရာကို လက်ခံပြီး ဖော်ပြချက် တစ်ခုပြုလုပ်သည်

Supervisor သည် user တောင်းဆိုချက်အပေါ် အလိုအလျောက် ဆုံးဖြတ်ချက်များ ချသည်။

```
--- FINAL RESPONSE ---------------------------------------------------
Executive Summary
...

Key Points
...

Recommendations
...

--- AGENTIC SCOPE (Data Flow) ----------------------------------------
  Each agent stores its output for downstream agents to consume:
  * fileContent: LangChain4j is an open-source, provider-agnostic Java framework...
  * report: Executive Summary...
```

#### Agentic Module အင်္ဂါရပ်များ ရှင်းလင်းချက်

ဥပမာသည် agentic module ၏ မြင့်မားသော အင်္ဂါရပ်တချို့ကို ပြသသည်။ Agentic Scope နှင့် Agent Listeners ကို နီးကပ်စွာ ကြည့်ရှုကြမည်။

**Agentic Scope** သည် `@Agent(outputKey="...")` ဖြင့် agent များ ၎င်းတို့၏ ရလဒ်များကို သိမ်းဆည်းသော မျှဝေ memory ဖြစ်သည်။ ၎င်းသည်-
- နောက်ဆုံးထွက် agent များကို ယခင် agent များ၏ output များကို access ပြုလုပ်ခွင့် ပေးသည်
- Supervisor သည် နောက်ဆုံး အဖြေ ဖန်တီးနိုင်သည်
- သင့်အား agent တစ်ခုချင်း၏ ထုတ်ပေးချက်များကို ကြည့်ရှုနိုင်သည်

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String fileContent = scope.readState("fileContent");  // FileAgent မှ အ(raw) ဖိုင်ဒေတာ
String report = scope.readState("report");            // ReportAgent မှ ဖောင်တည်သွင်းထားသောအစီရင်ခံစာ
```

**Agent Listeners** သည် agent အလုပ်လုပ်မှုများအား စောင့်ကြည့်ခြင်းနှင့် အမှားရှာဖွေမှုများ အတွက် အသုံးပြုသည်။ demo တွင် တွေ့ရှိသော တစ်ဆင့်ချင်း output များမှာ AgentListener တစ်ခုမှ agent ဖုန်းခေါ်မှုတိုင်းကို ချိတ်ဆက်ပေးသောကြောင့် ဖြစ်သည်-
- **beforeAgentInvocation** - Supervisor သည် agent တစ်ခု ရွေးချယ်သောအချိန် ခေါ်ဆိုသည်၊ ဘာကြောင့် ကို ရွေးခဲ့သည်ကို ဖော်ပြသည်
- **afterAgentInvocation** - agent တစ်ခု အလုပ်ပြီးဆုံးတဲ့ အချိန် ခေါ်ဆိုသည်နောက်ဆုံး ရလဒ်ကို ပြသည်
- **inheritedBySubagents** - true ဖြစ်သောအခါ listener သည် hierarchy တွင် မည်သည့် agent မဆို စောင့်ကြည့်သည်

```java
AgentListener monitor = new AgentListener() {
    private int step = 0;
    
    @Override
    public void beforeAgentInvocation(AgentRequest request) {
        step++;
        System.out.println("  +-- STEP " + step + ": " + request.agentName());
    }
    
    @Override
    public void afterAgentInvocation(AgentResponse response) {
        System.out.println("  +-- [OK] " + response.agentName() + " completed");
    }
    
    @Override
    public boolean inheritedBySubagents() {
        return true; // အောက်လွှာအေးဂျင့်များအားလုံးသို့ ပျံ့နှံ့ပါ။
    }
};
```

Supervisor pattern ထက်ပို၍ `langchain4j-agentic` module သည် workflow များအတွက် အောက်ပါ ကြီးမားသောနည်းလမ်းများနှင့် အင်္ဂါရပ်များ ထောက်ပံ့သည်-

| ပုံစံ | ရှင်းလင်းချက် | အသုံးပြုမှုအာရုံ |
|--------|---------------|------------------|
| **Sequential** | Agent များကို အစဉ်လိုက် ဆောင်ရွက်သည်၊ output သည် နောက်တစ်ခုသို့ ဆက်လက်ဖြတ်သန်းသည် | Pipeline များ: ရှာဖွေခြင်း → စိစစ်ခြင်း → အစီရင်ခံချက် |
| **Parallel** | Agent များကို တပြိုင်နက်ပြေးဆွဲသည် | လွတ်လပ်သော အလုပ်များ: ရာသီဥတု + သတင်း + စတော့ရှယ်ယာ |
| **Loop** | အခြေအနေဖြင့် အဆက်မပျက် လည်ပတ်မှု | အရည်အသွေး အကဲဖြတ်မှု: အမှတ် ≥ 0.8 ရရှိသည်အထိ ထပ်ဆည်း |
| **Conditional** | အခြေအနေများအပေါ် မူတည်ပြီး လမ်းကြောင်း သတ်မှတ်ခြင်း | ဖွဲ့စည်းခြင်း → ကျွမ်းကျင်သူ agent သို့ လမ်းညွှန် |
| **Human-in-the-Loop** | လူသား စစ်ဆေးမှုများ ထည့်သွင်းခြင်း | ခွင့်ပြုလုပ်ထိန်းချုပ်မှု workflow များ၊ အကြောင်းအရာ စစ်ဆေးမှု |

## အဓိက အယူအဆများ

ယခု သင်သည် MCP နှင့် agentic module ကို လက်တွေ့ အသုံးပြု၍ လေ့လာပြီးဖြစ်သဖြင့်၊ အသုံးပြုရန် အခြေအနေကို အောက်ပါအတိုင်း အကျဉ်းချုပ် ကြည့်လိုက်မည်။

**MCP** သည် ယခင်ရှိပြီးသား ကိရိယာ ပတ်ဝန်းကျင်များကို အသုံးချလိုသည့်အခါ၊ ပလက်ဖောင်းများစွာမှ မျှဝေသုံးနိုင်သော ကိရိယာများ ဖန်တီးလိုသည့်အခါ၊ စံချိန် protocol များဖြင့် third-party ဝန်ဆောင်မှုများ ပေါင်းစပ်လိုသည့်အခါ၊ သို့မဟုတ် ကုဒ် ပြောင်းလဲခြင်းမလိုဘဲ ကိရိယာ အကောင်အထည်ဖော်မှုများ ပြောင်းလဲလိုပါက အထူးသင့်လျော်သည်။

**Agentic Module** သည် `@Agent` annotation များနှင့် အသေးစိတ် agent သတ်မှတ်ချက်များကို ကြေညာပုံစံဖြင့်ရေးဆွဲလိုည့်သောအခါ၊ workflow orchestration (sequential, loop, parallel) လိုအပ်သောအခါ၊ imperative ကုဒ်ထက် interface အခြေပြု agent ဖန်တီးမှုပိုကြိုက်နှစ်သက်သောအခါ၊ outputKey ဖြင့် output မျှဝေသည့် agent အများစုကို ပေါင်းစပ်လိုသည့်အခါ အသုံးဝင်သည်။

**Supervisor Agent pattern** သည် workflow များကို မခန့်မှန်းနိုင်သေးသောအခါ၊ multiple specialized agents များကို dynamic အခြေအနေဖြင့် စီမံခြင်း လိုသည့်အခါ၊ စကားပြောစနစ်များ သည် capability မျိုးစုံသို့ လူနဲ့အတူ ချိတ်ဆက်ရန်လိုပါက သို့မဟုတ် အလွန်တုန့်ပြန်နိုင်သော၊ မျိုးစုံ ပေါင်းစပ်နိုင်သော agent အပြုအမူ လိုချင်သည့်အခါ အထူးထူးခြားပါသည်။
## ဂုဏ်ယူပါတယ်!

သင်သည် LangChain4j for Beginners သင်ခန်းစာကို ပြီးမြောက်စွာ လေ့လာပြီးပါပြီ။ သင်ယူသင့်ကြောင်းများမှာ -

- စိတ်အမှတ်အသား ပါရှိသော စကားပြော AI ကို တည်ဆောက်နည်း (Module 01)
- အလုပ်အကြောင်းအမျိုးမျိုးအတွက် Prompt engineering ပုံစံများ (Module 02)
- သင့်စာရွက်စာတမ်းများအတွင်း အချက်အလက်ကို အခြေခံ၍ RAG ဖြင့် တုံ့ပြန်ချက်များထုတ်ပေးနည်း (Module 03)
- စိတ်ကြိုက်ကိရိယာများဖြင့် မူလ AI ဂျင်နီယပ်များ (အကူအညီပေးသူများ) ကို ဖန်တီးနည်း (Module 04)
- LangChain4j MCP နှင့် Agentic modules ဖြင့် စံနှုန်းတကျ ကိရိယာများကို ပေါင်းစည်းအသုံးချနည်း (Module 05)

### နောက်တစ်ဆင့်?

Modules များ ပြီးမြောက်ပြီးနောက်၊ LangChain4j စမ်းသပ်မှု အယူအဆများကို ကြည့်ရှုရန် [Testing Guide](../docs/TESTING.md) ကို စူးစမ်းလေ့လာပါ။

**တရားဝင် အရင်းအမြစ်များ:**
- [LangChain4j Documentation](https://docs.langchain4j.dev/) - အပြည့်အစုံ လမ်းညွန်များနှင့် API ကိုးကားချက်များ
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - အရင်းအမြစ်ကုဒ်နှင့် ဥပမာများ
- [LangChain4j Tutorials](https://docs.langchain4j.dev/tutorials/) - အမျိုးမျိုးသော အသုံးပြုမှုများအတွက် အဆင့်ဆင့် သင်ခန်းစာများ

ဒီ သင်ခန်းစာကို ပြီးမြောက်ပြီးပါကြောင်း ကျေးဇူးတင်ပါသည်!

---

**သွားရောက်ရန်:** [← ယခင်: Module 04 - Tools](../04-tools/README.md) | [နောက်သို့ ပြန်သွားရန်](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**ဆောင်းပါးမှတ်ချက်**  
ဤစာရွက်စက်ကို AI ဘာသာပြန်ခြင်း ဝန်ဆောင်မှု [Co-op Translator](https://github.com/Azure/co-op-translator) ဖြင့် ဘာသာပြန်ထားပါသည်။ ကျွန်ုပ်တို့သည် တိကျမှန်ကန်မှုအတွက် ကြိုးပမ်းအားထုတ်သော်လည်း စက်ရုပ်ဘာသာပြန်ခြင်းများတွင် အမှားများ သို့မဟုတ် မှန်ကန်မှုလွဲမှားမှုများ ဖြစ်ပေါ်နိုင်ကြောင်း သတိပြုပါရန် မေတ္တာရပ်ခံပါသည်။ မူရင်းစာရွက်စက်၏ မိခင်ဘာသာဖြင့် ရေးသားထားသော အကြောင်းအရာကို အတည်ပြုရရှိသည့် အရင်းအမြစ်အဖြစ် ဖော်ထုတ် အပါအဝင် စဉ်းစားသင့်ပါသည်။ အရေးကြီးသည့် အချက်အလက်များအတွက် လူ့ဘာသာပြန်သူ တတ်မြောက်သူအား ပြုလုပ်ခြင်းကို တိုက်တွန်းပါသည်။ ဤဘာသာပြန်ချက် အသုံးပြုမှုကြောင့် ဖြစ်ပေါ်လာနိုင်သည့် နားလည်မှုမှားယွင်းခြင်းများအတွက် ကျွန်ုပ်တို့ တာဝန်မယူပါ။
<!-- CO-OP TRANSLATOR DISCLAIMER END -->