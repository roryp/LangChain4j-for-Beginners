<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "6c816d130a1fa47570c11907e72d84ae",
  "translation_date": "2026-01-06T00:38:12+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "sw"
}
-->
# Module 05: Itifaki ya Muktadha wa Mfano (MCP)

## Jedwali la Yaliyomo

- [Utajifunza Nini](../../../05-mcp)
- [MCP ni Nini?](../../../05-mcp)
- [Jinsi MCP Inavyofanya Kazi](../../../05-mcp)
- [Kipengele cha Agentic](../../../05-mcp)
- [Kukimbia Mifano](../../../05-mcp)
  - [Mahitaji ya Awali](../../../05-mcp)
- [Mwanzo wa Haraka](../../../05-mcp)
  - [Mifumo ya Faili (Stdio)](../../../05-mcp)
  - [Wakala Msimamizi](../../../05-mcp)
    - [Kufahamu Matokeo](../../../05-mcp)
    - [Mikakati ya Majibu](../../../05-mcp)
    - [Maelezo ya Sifa za Kipengele cha Agentic](../../../05-mcp)
- [Madhumuni Muhimu](../../../05-mcp)
- [Hongera!](../../../05-mcp)
  - [Nini Ifuatayo?](../../../05-mcp)

## Utajifunza Nini

Umejenga AI ya mazungumzo, umeweza kutumia maelekezo, umeweka majibu katika nyaraka, na umeunda mawakala wenye zana. Lakini zana zote hizo zilijengwa maalum kwa programu yako. Je, vipi kama ungeweza kumruhusu AI yako kutumia ekolojia ya zana zilizo na viwango vya kawaida ambavyo mtu yeyote anaweza kuunda na kushirikiana? Katika moduli hii, utajifunza jinsi ya kufanya hivyo kwa kutumia Itifaki ya Muktadha wa Mfano (MCP) na kipengele cha agentic cha LangChain4j. Kwanza tunaonyesha msomaji rahisi wa faili za MCP halafu tunaonyesha jinsi inavyojumuisha kwa urahisi katika taratibu za agentic za hali ya juu kwa kutumia mfano wa Wakala Msimamizi.

## MCP ni Nini?

Itifaki ya Muktadha wa Mfano (MCP) hutoa hasa hilo - njia ya kawaida kwa programu za AI kugundua na kutumia zana za nje. Badala ya kuandika mjumuisho maalum kwa kila chanzo cha data au huduma, unajiunga na seva za MCP zinazofichua uwezo wao kwa muundo unaolingana. Wakala wako wa AI basi anaweza kugundua na kutumia zana hizi moja kwa moja.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.sw.png" alt="MCP Comparison" width="800"/>

*Kabala ya MCP: Mizunguko ya moja kwa moja tata. Baada ya MCP: Protokoli moja, uwezekano usio na kikomo.*

MCP inatatua tatizo la msingi katika maendeleo ya AI: kila ujumuishaji ni maalum. Unataka kufikia GitHub? Msimbo maalum. Unataka kusoma faili? Msimbo maalum. Unataka kuuliza database? Msimbo maalum. Na hakuna ujumuishaji wowote kati ya haya unaofanya kazi na programu zingine za AI.

MCP huweka kiwango hiki. Seva ya MCP inaonyesha zana kwa maelezo wazi na skimu. Mteja yeyote wa MCP anaweza kuungana, kugundua zana zinazopatikana, na kuzitumia. Jenga mara moja, tumia kila mahali.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.sw.png" alt="MCP Architecture" width="800"/>

*Miundo ya Itifaki ya Muktadha wa Mfano - ugunduzi na utekelezaji wa zana unaolingana*

## Jinsi MCP Inavyofanya Kazi

**Muundo wa Seva-Mteja**

MCP hutumia mfano wa mteja-seva. Seva hutoa zana - kusoma faili, kuuliza database, kuita API. Wateja (programu yako ya AI) huungana na seva na kutumia zana zao.

Ili kutumia MCP na LangChain4j, ongeza utegemezi huu wa Maven:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**Ugunduzi wa Zana**

Unapo jiunga na seva ya MCP, huuliza "Una zana zipi?" Seva hutoa orodha ya zana zinazopatikana, kila moja ikiwa na maelezo na skimu za vigezo. Wakala wako wa AI anaweza kisha kuamua ni zana zipi za kutumia kulingana na maombi ya mtumiaji.

**Mbinu za Usafirishaji**

MCP inaunga mkono mbinu tofauti za usafirishaji. Moduli hii inaonyesha usafirishaji wa Stdio kwa michakato ya ndani:

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.sw.png" alt="Transport Mechanisms" width="800"/>

*Mbinu za usafirishaji MCP: HTTP kwa seva za mbali, Stdio kwa michakato ya ndani*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Kwa michakato ya ndani. Programu yako huanzisha seva kama mchakato mdogo na kuwasiliana kupitia pembejeo/utokeo wa kawaida. Inafaa kwa upatikanaji wa mfumo wa faili au zana za mstari wa amri.

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

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) na uulize:
> - "Usafirishaji wa Stdio unafanyaje kazi na ni lini inapaswa kutumika badala ya HTTP?"
> - "LangChain4j inasimamia vipi mzunguko wa maisha ya michakato ya seva za MCP?"
> - "Ni madhara gani ya kiusalama ya kumpa AI upatikanaji wa mfumo wa faili?"

## Kipengele cha Agentic

Wakati MCP hutoa zana zilizo fumwa, kipengele cha **agentic** cha LangChain4j hutoa njia ya kauli ili kujenga mawakala wanaoratibu zana hizo. Alama ya `@Agent` na `AgenticServices` hukuwezesha kufafanua tabia za wakala kupitia interface badala ya msimbo wa amri.

Katika moduli hii, utachunguza mfano wa **Wakala Msimamizi** — njia ya hali ya juu ya AI ya agentic ambapo wakala "msimamizi" huamua kwa nguvu ni mawakala gani ndogo wataitishwa kulingana na maombi ya mtumiaji. Tutachanganya dhana zote mbili kwa kumpa mojawapo ya mawakala wetu ndogo uwezo wa upatikanaji wa faili unaotegemea MCP.

Ili kutumia kipengele cha agentic, ongeza utegemezi huu wa Maven:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ Jaribio:** Moduli ya `langchain4j-agentic` ni **jaribio** na inaweza kubadilika. Njia thabiti ya kujenga misaada ya AI bado ni `langchain4j-core` na zana maalum (Moduli 04).

## Kukimbia Mifano

### Mahitaji ya Awali

- Java 21+, Maven 3.9+
- Node.js 16+ na npm (kwa seva za MCP)
- Mabadiliko ya mazingira yamewekwa katika faili `.env` (kutoka kwenye saraka ya mzizi):
  - `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (sawa na Moduli 01-04)

> **Kumbuka:** Ikiwa bado hujaunda mabadiliko yako ya mazingira, tazama [Moduli 00 - Mwanzo wa Haraka](../00-quick-start/README.md) kwa maagizo, au nakili `.env.example` hadi `.env` katika saraka ya mzizi na jaza maadili yako.

## Mwanzo wa Haraka

**Kutumia VS Code:** Bonyeza kitufe cha kulia kwenye faili yoyote ya maonyesho katika Explorer na uchague **"Run Java"**, au tumia usanidi wa kuanzisha kutoka kwenye paneli ya Run and Debug (hakikisha umeongeza token yako katika faili `.env` kwanza).

**Kutumia Maven:** Vinginevyo, unaweza kuendesha kutoka mstari wa amri na mifano ifuatayo.

### Mifumo ya Faili (Stdio)

Hii inaonyesha zana zinazotegemea michakato ya ndani.

**✅ Hakuna mahitaji ya awali yanayohitajika** - seva ya MCP huanzishwa moja kwa moja.

**Kutumia Skripti za Kuanza (Inapendekezwa):**

Skripti za kuanza huingiza moja kwa moja mabadiliko ya mazingira kutoka faili `.env` ya mzizi:

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

**Kutumia VS Code:** Bonyeza kitufe cha kulia kwenye `StdioTransportDemo.java` na uchague **"Run Java"** (hakikisha faili yako `.env` imewekwa).

Programu huanzisha seva ya mfumo wa faili ya MCP moja kwa moja na kusoma faili ya ndani. Angalia jinsi usimamizi wa mchakato mdogo unavyofanyika kwako.

**Matokeo Yanayotarajiwa:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Wakala Msimamizi

Mfano wa **Wakala Msimamizi** ni aina **inayobadilika** ya AI ya agentic. Msimamizi hutumia LLM kuamua kwa uhuru mawakala gani ndogo ya kuwaita kulingana na ombi la mtumiaji. Katika mfano unaofuata, tunachanganya upatikanaji wa faili unaotegemea MCP pamoja na wakala wa LLM kuunda mtiririko wa kazi wa kusoma faili → kuripoti.

Katika maonyesho, `FileAgent` husoma faili kwa kutumia zana za mfumo wa faili wa MCP, na `ReportAgent` huunda ripoti yenye muhtasari wa mkurugenzi (sentensi 1), pointi 3 muhimu, na mapendekezo. Msimamizi huoratibu mzunguko huu moja kwa moja:

<img src="../../../translated_images/agentic.cf84dcda226374e3.sw.png" alt="Agentic Module" width="800"/>

```
┌─────────────┐      ┌──────────────┐
│  FileAgent  │ ───▶ │ ReportAgent  │
│ (MCP tools) │      │  (pure LLM)  │
└─────────────┘      └──────────────┘
   outputKey:           outputKey:
  'fileContent'         'report'
```

Kila wakala huhifadhi matokeo yake katika **Eneo la Agentic** (kumbukumbu iliyoshirikiwa), ikiruhusu mawakala wa chini kufikia matokeo ya awali. Hii inaonyesha jinsi zana za MCP zinavyojumuishwa kwa urahisi katika miradi ya agentic — Msimamizi hawezi kujua *jinsi* faili zinavyosomwa, bali ni kwamba `FileAgent` anaweza kufanya hivyo.

#### Kukimbia Maonyesho

Skripti za kuanza huingiza moja kwa moja mabadiliko ya mazingira kutoka faili `.env` ya mzizi:

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

**Kutumia VS Code:** Bonyeza kitufe cha kulia kwenye `SupervisorAgentDemo.java` na uchague **"Run Java"** (hakikisha faili yako `.env` imewekwa).

#### Jinsi Msimamizi Anavyofanya Kazi

```java
// Hatua 1: FileAgent husoma faili kwa kutumia zana za MCP
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // Ina zana za MCP kwa shughuli za faili
        .build();

// Hatua 2: ReportAgent hutengeneza ripoti zilizopangwa
ReportAgent reportAgent = AgenticServices.agentBuilder(ReportAgent.class)
        .chatModel(model)
        .build();

// Msimamizi anaongoza mtiririko wa faili → ripoti
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)
        .subAgents(fileAgent, reportAgent)
        .responseStrategy(SupervisorResponseStrategy.LAST)  // Rudisha ripoti ya mwisho
        .build();

// Msimamizi huchagua mawakala watakaoitwa kulingana na ombi
String response = supervisor.invoke("Read the file at /path/file.txt and generate a report");
```

#### Mikakati ya Majibu

Unapo weka `SupervisorAgent`, unaeleza jinsi unavyopaswa kuunda jibu la mwisho kwa mtumiaji baada ya mawakala wadogo kumaliza kazi zao. Mikakati inayopatikana ni:

| Mkakati | Maelezo |
|----------|-------------|
| **LAST** | Msimamizi hurudisha matokeo ya wakala au zana ya mwisho iliyoitwa. Hii ni muhimu wakati wakala wa mwisho katika mlolongo amebuniwa mahsusi kutoa jibu kamili na la mwisho (mfano, "Wakala wa Muhtasari" katika mchakato wa utafiti). |
| **SUMMARY** | Msimamizi hutumia LLM yake ndani kuunda muhtasari wa mwingiliano mzima na matokeo yote ya mawakala wadogo, kisha hurudisha muhtasari huo kama jibu la mwisho. Hii hutoa jibu safi na linalojumuisha kwa mtumiaji. |
| **SCORED** | Mfumo hutumia LLM ya ndani kupima jibu la LAST na SUMMARY dhidi ya ombi la mtumiaji, kisha hurudisha matokeo yenye alama nzuri zaidi. |

Tazama [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) kwa utekelezaji kamili.

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) na uulize:
> - "Msimamizi huamua vipi mawakala gani ya waitishe?"
> - "Tofauti gani kati ya mifumo ya kazi ya Msimamizi na mtiririko wa mfululizo?"
> - "Nawezaje kubinafsisha tabia ya kupanga ya Msimamizi?"

#### Kufahamu Matokeo

Unapoendesha onyesho, utaona mtiririko uliopangwa wa jinsi Msimamizi anavyoratibu mawakala wengi. Hapa ni maana ya kila sehemu:

```
======================================================================
  FILE → REPORT WORKFLOW DEMO
======================================================================

This demo shows a clear 2-step workflow: read a file, then generate a report.
The Supervisor orchestrates the agents automatically based on the request.
```

**Kichwa** kinaanzisha dhana ya mtiririko wa kazi: mchakato ulioelekezwa kutoka kusoma faili hadi utengenezaji wa ripoti.

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

**Mchoro wa Mtiririko wa Kazi** unaonyesha mtiririko wa data kati ya mawakala. Kila wakala ana kazi maalum:
- **FileAgent** husoma faili kwa kutumia zana za MCP na kuhifadhi maudhui ghafi katika `fileContent`
- **ReportAgent** hutumia maudhui hayo na kuandaa ripoti yenye muundo katika `report`

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and generate a report on its contents"
```

**Ombi la Mtumiaji** linaonyesha kazi. Msimamizi huchambua hili na kuamua kuitisha FileAgent → ReportAgent.

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

**Uratibu wa Msimamizi** unaonyesha mtiririko wa hatua 2 ukiendeshwa:
1. **FileAgent** husoma faili kupitia MCP na kuhifadhi maudhui
2. **ReportAgent** hupokea maudhui na kuunda ripoti yenye muundo

Msimamizi alichukua maamuzi haya **kwa uhuru** kulingana na ombi la mtumiaji.

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

#### Maelezo ya Sifa za Kipengele cha Agentic

Mfano unaonyesha sifa kadhaa za hali ya juu za kipengele cha agentic. Tuchunguze Eneo la Agentic na Wasikilizaji wa Wakala.

**Eneo la Agentic** linaonyesha kumbukumbu iliyoshirikiwa ambapo mawakala huhifadhi matokeo yao kwa kutumia `@Agent(outputKey="...")`. Hii inaruhusu:
- Mawakala wa baadaye kufikia matokeo ya mawakala waliotangulia
- Msimamizi kuunganisha jibu la mwisho
- Wewe kukagua kilichozalishwa na kila wakala

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String fileContent = scope.readState("fileContent");  // Data ya faili ghafi kutoka FileAgent
String report = scope.readState("report");            // Ripoti iliyopangwa kutoka ReportAgent
```

**Wasikilizaji wa Wakala** huruhusu ufuatiliaji na utatuzi wa matatizo ya utekelezaji wa wakala. Matokeo unayoona hatua kwa hatua katika onyesho hutoka kwa AgentListener anayeshirikiana na kila uhiushaji wa wakala:
- **beforeAgentInvocation** - Huitwa wakati Msimamizi anapochagua wakala, ikikuonyesha wakala aliyekuliwa na kwanini
- **afterAgentInvocation** - Huitwa wakati wakala anapomaliza kazi, ikionyesha matokeo yake
- **inheritedBySubagents** - Ikiwa kweli, msikilizaji husimamia mawakala wote katika mlolongo

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
        return true; // Sambaza kwa wakala wote wa chini
    }
};
```

Zaidi ya mfano wa Msimamizi, moduli ya `langchain4j-agentic` hutoa mifano mingi ya miradi ya kazi na sifa:

| Mfano | Maelezo | Matumizi |
|---------|-------------|----------|
| **Sequential** | Tekeleza mawakala kwa mpangilio, matokeo yataenda kwa mwenzie | Mifereji: utafiti → uchambuzi → ripoti |
| **Parallel** | Endesha mawakala kwa wakati mmoja | Kazi huru: hali ya hewa + habari + hisa |
| **Loop** | Rudia hadi sharti litimizwe | Kupima ubora: boresha hadi alama ≥ 0.8 |
| **Conditional** | Elekeza kwa msingi wa masharti | Gawanya → peleka kwa wakala mtaalamu |
| **Human-in-the-Loop** | Ongeza hatua za kibinadamu | Mifumo ya idhini, uhakiki wa maudhui |

## Madhumuni Muhimu

Sasa umechunguza MCP na kipengele cha agentic kikifanya kazi, hebu muhtasari wakati ya kutumia kila mbinu.

**MCP** ni bora unapotaka kutumia ekolojia zilizopo za zana, kujenga zana ambazo programu nyingi zinaweza kushirikiana, kuhusisha huduma za mtu wa tatu kwa itifaki za kawaida, au kubadilisha utekelezaji wa zana bila kubadilisha msimbo.

**Kipengele cha Agentic** kinafanya kazi vizuri unapotaka ufafanuzi wa wakala kwa kauli na alama za `@Agent`, unahitaji uratibu wa mtiririko wa kazi (mfululizo, mzunguko, sambamba), unapendelea usanifu wa wakala kwa interface badala ya msimbo wa amri, au unapochanganya mawakala wengi yanayoshirikiana matokeo kupitia `outputKey`.

**Mfano wa Wakala Msimamizi** unaangaza wakati mtiririko wa kazi hauwezi kutabirika kabla na unataka LLM iamua, wakati una mawakala maalum wengi yanayohitaji uratibu wa mabadiliko, wakati unajenga mifumo ya mazungumzo inayopanga kwa uwezo tofauti, au unapotaka tabia ya wakala inayobadilika na yenye kibadiliko zaidi.
## Hongera!

Umehitimisha kozi ya LangChain4j kwa Waanzaji. Umesoma:

- Jinsi ya kujenga AI ya mazungumzo yenye kumbukumbu (Moduli 01)
- Mifumo ya uwekaji maagizo kwa kazi mbalimbali (Moduli 02)
- Kusawazisha majibu katika nyaraka zako kwa kutumia RAG (Moduli 03)
- Kuunda mawakala wa AI wa msingi (wasaidizi) kwa kutumia zana za desturi (Moduli 04)
- Kuunganisha zana zilizosanifiwa na MCP ya LangChain4j na moduli za Agentic (Moduli 05)

### Nini Kifuatacho?

Baada ya kumaliza moduli, chunguza [Mwongozo wa Kupima](../docs/TESTING.md) ili kuona dhana za kupima LangChain4j zikifanyakazi.

**Rasilimali Rasmi:**
- [Nyaraka za LangChain4j](https://docs.langchain4j.dev/) - Miongozo kamili na rejeleo la API
- [GitHub ya LangChain4j](https://github.com/langchain4j/langchain4j) - Chanzo cha msimbo na mifano
- [Mafunzo ya LangChain4j](https://docs.langchain4j.dev/tutorials/) - Mafunzo hatua kwa hatua kwa matumizi mbalimbali

Asante kwa kumaliza kozi hii!

---

**Urambazaji:** [← Iliyotangulia: Moduli 04 - Zana](../04-tools/README.md) | [Rudi Kwenye Kuu](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Onyo**:
Hati hii imetafsiriwa kwa kutumia huduma ya tafsiri ya AI [Co-op Translator](https://github.com/Azure/co-op-translator). Ingawa tunajitahidi kuwa sahihi, tafadhali fahamu kwamba tafsiri za kiotomatiki zinaweza kuwa na makosa au upungufu wa usahihi. Hati asili katika lugha yake ya asili inapaswa kuchukuliwa kama chanzo cha haki. Kwa taarifa muhimu, tafsiri ya kitaalamu kwa binadamu inashauriwa. Hatuna uwajibikaji kwa kutoelewana au tafsiri potofu zitokanazo na matumizi ya tafsiri hii.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->