<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "6c816d130a1fa47570c11907e72d84ae",
  "translation_date": "2026-01-06T00:32:04+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "tl"
}
-->
# Module 05: Model Context Protocol (MCP)

## Table of Contents

- [What You'll Learn](../../../05-mcp)
- [What is MCP?](../../../05-mcp)
- [How MCP Works](../../../05-mcp)
- [The Agentic Module](../../../05-mcp)
- [Running the Examples](../../../05-mcp)
  - [Prerequisites](../../../05-mcp)
- [Quick Start](../../../05-mcp)
  - [File Operations (Stdio)](../../../05-mcp)
  - [Supervisor Agent](../../../05-mcp)
    - [Understanding the Output](../../../05-mcp)
    - [Response Strategies](../../../05-mcp)
    - [Explanation of Agentic Module Features](../../../05-mcp)
- [Key Concepts](../../../05-mcp)
- [Congratulations!](../../../05-mcp)
  - [What's Next?](../../../05-mcp)

## What You'll Learn

Nakabuo ka na ng conversational AI, na-master ang mga prompt, na-ground ang mga sagot sa mga dokumento, at nakagawa ng mga agent na may mga tools. Pero lahat ng iyon ay custom-built para sa iyong partikular na aplikasyon. Paano kung maaari mong bigyan ang iyong AI ng access sa isang standardized na ekosistema ng mga tool na maaaring likhain at ibahagi ng kahit sino? Sa module na ito, matututuhan mo kung paano gawin iyon gamit ang Model Context Protocol (MCP) at ang agentic module ng LangChain4j. Una naming ipapakita ang isang simpleng MCP file reader at pagkatapos ay ipapakita kung paano ito madaling isinasama sa mga advanced na agentic workflow gamit ang Supervisor Agent pattern.

## What is MCP?

Ang Model Context Protocol (MCP) ay nagbibigay eksaktong iyon — isang pamantayang paraan para sa mga AI application na matuklasan at gamitin ang mga external na tool. Sa halip na magsulat ng mga custom integration para sa bawat data source o serbisyo, kumokonekta ka sa mga MCP server na nagpapakita ng kanilang mga kakayahan sa isang consistent na format. Maaari nang awtomatikong matuklasan at magamit ng iyong AI agent ang mga tool na ito.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.tl.png" alt="MCP Comparison" width="800"/>

*Bago ang MCP: Kumplikadong point-to-point integrations. Pagkatapos ng MCP: Isang protocol, walang katapusang posibilidad.*

Nilulutas ng MCP ang isang pundamental na problema sa pag-develop ng AI: bawat integration ay custom. Gusto mong ma-access ang GitHub? Custom na code. Gusto mong magbasa ng mga file? Custom na code. Gusto mong mag-query ng database? Custom na code. At wala sa mga integrasyong ito ang gumagana sa ibang AI application.

Pinastandardize ito ng MCP. Isang MCP server ay nagpapakita ng mga tool na may malinaw na paglalarawan at schema. Anumang MCP client ay maaaring kumonekta, matuklasan ang mga available na tool, at gamitin ito. Build once, use everywhere.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.tl.png" alt="MCP Architecture" width="800"/>

*Model Context Protocol architecture - standardized tool discovery and execution*

## How MCP Works

**Server-Client Architecture**

Gumagamit ang MCP ng client-server model. Ang mga server ay nagbibigay ng mga tool — pagbabasa ng mga file, pag-query ng mga database, pagtawag ng mga API. Ang mga client (ang iyong AI application) ay kumokonekta sa mga server at ginagamit ang kanilang mga tool.

Para magamit ang MCP sa LangChain4j, idagdag ang Maven dependency na ito:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**Tool Discovery**

Kapag kumonekta ang iyong client sa isang MCP server, tinatanong nito, "Anong mga tool ang meron kayo?" Sumasagot ang server ng listahan ng mga available na tool, bawat isa ay may mga paglalarawan at schemas ng mga parameter. Maaari nang magdesisyon ang iyong AI agent kung alin ang gagamitin base sa mga kahilingan ng user.

**Transport Mechanisms**

Sinuportahan ng MCP ang iba't ibang mekanismo ng transportasyon. Ipinapakita ng module na ito ang Stdio transport para sa mga local na proseso:

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.tl.png" alt="Transport Mechanisms" width="800"/>

*MCP transport mechanisms: HTTP para sa mga remote server, Stdio para sa mga lokal na proseso*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Para sa mga lokal na proseso. Ang iyong aplikasyon ay nagsisimula ng isang server bilang isang subprocess at nakikipag-ugnayan gamit ang standard input/output. Kapaki-pakinabang para sa pag-access ng filesystem o mga command-line tool.

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

> **🤖 Subukan gamit ang [GitHub Copilot](https://github.com/features/copilot) Chat:** Buksan ang [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) at itanong:
> - "Paano gumagana ang Stdio transport at kailan ko ito dapat gamitin kumpara sa HTTP?"
> - "Paano pinamamahalaan ng LangChain4j ang lifecycle ng mga spawned MCP server process?"
> - "Ano ang mga security implication ng pagbibigay ng AI access sa file system?"

## The Agentic Module

Habang nagbibigay ang MCP ng standardized na mga tool, ang **agentic module** ng LangChain4j ay nagbibigay ng declarative na paraan para bumuo ng mga agent na nag-o-orchestrate ng mga tool na iyon. Ang annotation na `@Agent` at `AgenticServices` ay nagpapahintulot sa iyo na tukuyin ang kilos ng agent sa pamamagitan ng mga interface sa halip na imperative na code.

Sa module na ito, susuriin mo ang **Supervisor Agent** pattern — isang advanced na agentic AI approach kung saan ang isang "supervisor" agent ay dynamic na nagdedesisyon kung aling mga sub-agent ang dapat tawagin base sa kahilingan ng user. Pagsasamahin natin ang parehong konsepto sa pagbibigay ng isa sa mga sub-agent namin ng MCP-powered na kakayahan sa pag-access ng mga file.

Para magamit ang agentic module, idagdag ang Maven dependency na ito:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ Experimental:** Ang `langchain4j-agentic` module ay **experimental** at maaaring mabago. Ang stable na paraan upang bumuo ng AI assistant ay `langchain4j-core` gamit ang custom tools (Module 04).

## Running the Examples

### Prerequisites

- Java 21+, Maven 3.9+
- Node.js 16+ at npm (para sa mga MCP server)
- Mga environment variable na na-configure sa `.env` file (mula sa root directory):
  - `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (pareho sa Modules 01-04)

> **Tandaan:** Kung hindi mo pa naisaset up ang iyong mga environment variable, tingnan ang [Module 00 - Quick Start](../00-quick-start/README.md) para sa mga tagubilin, o kopyahin ang `.env.example` papuntang `.env` sa root directory at punan ang iyong mga halaga.

## Quick Start

**Paggamit ng VS Code:** I-right-click lang ang anumang demo file sa Explorer at piliin ang **"Run Java"**, o gamitin ang mga launch configuration mula sa Run and Debug panel (siguraduhing nailagay mo muna ang iyong token sa `.env` file).

**Paggamit ng Maven:** Bilang alternatibo, maaari mong patakbuhin ito mula sa command line gamit ang mga halimbawa sa ibaba.

### File Operations (Stdio)

Ipinapakita nito ang mga lokal na subprocess-based na tool.

**✅ Walang kinakailangang prerequisites** - ang MCP server ay awtomatikong na-spawn.

**Paggamit ng Start Scripts (Inirerekomenda):**

Ang start scripts ay awtomatikong naglo-load ng environment variables mula sa root `.env` file:

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

**Paggamit ng VS Code:** I-right-click ang `StdioTransportDemo.java` at piliin ang **"Run Java"** (siguraduhing na-configure ang iyong `.env` file).

Ang aplikasyon ay awtomatikong nagsisimula ng isang filesystem MCP server at nagbabasa ng lokal na file. Pansinin kung paano hawak ng subprocess management ang proseso para sa iyo.

**Inaaasahang output:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Supervisor Agent

Ang **Supervisor Agent pattern** ay isang **flexible** na anyo ng agentic AI. Gumagamit ang Supervisor ng LLM upang autonomously magdesisyon kung alin sa mga agent ang dapat tawagin base sa kahilingan ng user. Sa susunod na halimbawa, pagsasamahin natin ang MCP-powered file access sa isang LLM agent upang gumawa ng supervised file read → report workflow.

Sa demo, nagbabasa ng file gamit ang MCP filesystem tools ang `FileAgent`, at gumagawa ng structured report na may executive summary (1 pangungusap), 3 pangunahing punto, at mga rekomendasyon ang `ReportAgent`. Ina-o-orchestrate ito ng Supervisor nang awtomatiko:

<img src="../../../translated_images/agentic.cf84dcda226374e3.tl.png" alt="Agentic Module" width="800"/>

```
┌─────────────┐      ┌──────────────┐
│  FileAgent  │ ───▶ │ ReportAgent  │
│ (MCP tools) │      │  (pure LLM)  │
└─────────────┘      └──────────────┘
   outputKey:           outputKey:
  'fileContent'         'report'
```

Itinatabi ng bawat agent ang output nito sa **Agentic Scope** (shared memory), na nagpapahintulot sa mga downstream agent na ma-access ang mga naunang resulta. Ipinapakita nito kung paano seamless na nai-integrate ang mga tool ng MCP sa agentic workflows — hindi kailangan ng Supervisor na malaman *paano* binabasa ang mga file, ang mahalaga ay kaya itong gawin ng `FileAgent`.

#### Papatakbuhin ang Demo

Ang start scripts ay awtomatikong naglo-load ng environment variables mula sa root `.env` file:

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

**Paggamit ng VS Code:** I-right-click ang `SupervisorAgentDemo.java` at piliin ang **"Run Java"** (siguraduhing na-configure ang iyong `.env` file).

#### Paano Gumagana ang Supervisor

```java
// Hakbang 1: Binabasa ng FileAgent ang mga file gamit ang MCP tools
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // May MCP tools para sa mga operasyon ng file
        .build();

// Hakbang 2: Gumagawa ang ReportAgent ng mga istrukturadong ulat
ReportAgent reportAgent = AgenticServices.agentBuilder(ReportAgent.class)
        .chatModel(model)
        .build();

// Pinamamahalaan ng Supervisor ang daloy ng file → ulat
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)
        .subAgents(fileAgent, reportAgent)
        .responseStrategy(SupervisorResponseStrategy.LAST)  // Ibalik ang panghuling ulat
        .build();

// Ang Supervisor ang nagdedesisyon kung aling mga agent ang tatawagin batay sa kahilingan
String response = supervisor.invoke("Read the file at /path/file.txt and generate a report");
```

#### Mga Response Strategy

Kapag nag-configure ka ng `SupervisorAgent`, tinutukoy mo kung paano ito dapat bumuo ng panghuling sagot para sa user matapos gawin ng mga sub-agent ang kanilang mga task. Narito ang mga available na strategy:

| Strategy | Description |
|----------|-------------|
| **LAST** | Ibinabalik ng supervisor ang output ng huling sub-agent o tool na tinawag. Useful ito kapag ang panghuling agent sa workflow ay espesyal na idinisenyo upang makabuo ng kompletong sagot (halimbawa, isang "Summary Agent" sa isang research pipeline). |
| **SUMMARY** | Ginagamit ng supervisor ang sarili nitong internal Language Model (LLM) upang pagsamahin ang isang buod ng buong interaksyon at lahat ng output ng sub-agent, pagkatapos ay ibabalik ang buod bilang panghuling sagot. Nagbibigay ito ng malinis, pinagsama-samang sagot para sa user. |
| **SCORED** | Ginagamit ng sistema ang internal na LLM para i-score ang parehong LAST na tugon at SUMMARY ng interaksyon laban sa orihinal na kahilingan ng user, at ibabalik ang output na may mas mataas na marka. |

Tingnan ang [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) para sa kumpletong implementasyon.

> **🤖 Subukan gamit ang [GitHub Copilot](https://github.com/features/copilot) Chat:** Buksan ang [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) at itanong:
> - "Paano nagdedesisyon ang Supervisor kung aling mga agent ang tatawagin?"
> - "Ano ang kaibahan ng Supervisor at Sequential workflow patterns?"
> - "Paano ko ma-customize ang planning behavior ng Supervisor?"

#### Pag-unawa sa Output

Kapag pinatakbo mo ang demo, makikita mo ang istrakturadong walkthrough kung paano ina-o-orchestrate ng Supervisor ang maraming mga agent. Narito ang ibig sabihin ng bawat bahagi:

```
======================================================================
  FILE → REPORT WORKFLOW DEMO
======================================================================

This demo shows a clear 2-step workflow: read a file, then generate a report.
The Supervisor orchestrates the agents automatically based on the request.
```

**Ang header** ay nagpapakilala ng konsepto ng workflow: isang nakatutok na pipeline mula sa pagbabasa ng file hanggang sa paggawa ng ulat.

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

**Workflow Diagram** ay nagpapakita ng daloy ng datos sa pagitan ng mga agent. Bawat agent ay may partikular na tungkulin:
- **FileAgent** nagbabasa ng mga file gamit ang MCP tools at itinatabi ang raw content sa `fileContent`
- **ReportAgent** kumokonsumo ng content na iyon at gumagawa ng structured report sa `report`

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and generate a report on its contents"
```

**User Request** nagpapakita ng gawain. Ipinaparse ito ng Supervisor at nagdedesisyon na tawagin ang FileAgent → ReportAgent.

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

**Supervisor Orchestration** nagpapakita ng 2-step flow na aktibo:
1. **FileAgent** nagbabasa ng file sa pamamagitan ng MCP at itinatabi ang content
2. **ReportAgent** tumatanggap ng content at gumagawa ng structured report

Ginawa ng Supervisor ang mga desisyong ito **autonomously** base sa kahilingan ng user.

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

#### Paliwanag ng Mga Katangian ng Agentic Module

Ipinapakita ng halimbawa ang ilang advanced na feature ng agentic module. Tingnan natin nang mas malapitan ang Agentic Scope at Agent Listeners.

**Agentic Scope** ay nagpapakita ng shared memory kung saan inilalagay ng mga agent ang kanilang resulta gamit ang `@Agent(outputKey="...")`. Pinapahintulutan nito na:
- Ma-access ng mga susunod na agent ang mga output ng naunang agent
- Makagawa ang Supervisor ng synthesized na panghuling sagot
- Masuri mo kung ano ang nilikha ng bawat agent

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String fileContent = scope.readState("fileContent");  // Hilaw na datos ng file mula sa FileAgent
String report = scope.readState("report");            // Nakaayos na ulat mula sa ReportAgent
```

**Agent Listeners** ay nagpapahintulot sa pagbabantay at pag-debug ng pagpapatupad ng agent. Ang step-by-step na output na nakikita mo sa demo ay nanggagaling sa isang AgentListener na nakasaksak sa bawat pagtawag ng agent:
- **beforeAgentInvocation** - Tinatawag kapag pinili ng Supervisor ang isang agent, na nagpapakita kung aling agent ang napili at bakit
- **afterAgentInvocation** - Tinatawag kapag natapos ang isang agent, na nagpapakita ng resulta nito
- **inheritedBySubagents** - Kapag true, sinusubaybayan ng listener ang lahat ng agent sa hierarchy

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
        return true; // Ipalaganap sa lahat ng mga sub-ahente
    }
};
```

Higit pa sa Supervisor pattern, nagbibigay ang `langchain4j-agentic` module ng iba't ibang malalakas na workflow patterns at feature:

| Pattern | Description | Use Case |
|---------|-------------|----------|
| **Sequential** | Isagawa ang mga agent nang sunod-sunod, ang output ay dumadaloy sa susunod | Mga pipeline: research → analyze → report |
| **Parallel** | Patakbuhin ang mga agent nang sabay-sabay | Mga independiyenteng gawain: weather + news + stocks |
| **Loop** | Ulitin hanggang maabot ang kondisyon | Quality scoring: palihan hanggang score ≥ 0.8 |
| **Conditional** | I-route base sa mga kondisyon | I-classify → i-route sa specialist agent |
| **Human-in-the-Loop** | Magdagdag ng mga human checkpoint | Approval workflows, content review |

## Key Concepts

Ngayon na nasuri mo na ang MCP at ang agentic module sa aksyon, buuin natin kung kailan gagamitin ang bawat approach.

**MCP** ay mainam kapag gusto mong gamitin ang umiiral nang mga ekosistema ng tool, gumawa ng mga tool na pwedeng i-share ng maraming aplikasyon, mag-integrate ng third-party services gamit ang mga standard protocol, o palitan ang implementasyon ng tool nang hindi kailangang baguhin ang code.

**Ang Agentic Module** ay pinakamainam kapag gusto mo ng declarative na pagtukoy ng agent gamit ang `@Agent` annotation, kailangan mo ng workflow orchestration (sequential, loop, parallel), mas gusto mong gumamit ng interface-based na disenyo kaysa sa imperative code, o kapag pinagsasama mo ang maraming agent na nagbabahagi ng outputs gamit ang `outputKey`.

**Ang Supervisor Agent pattern** ay kapansin-pansin kapag hindi predictable ang workflow nang maaga at gusto mong ang LLM ang magdesisyon, kapag maraming specialized agent na kailangan ng dynamic orchestration, kapag bumubuo ng conversational system na nagra-route sa iba't ibang kakayahan, o kapag gusto mo ng pinaka-flexible at adaptive na kilos ng agent.
## Congratulations!

Naitapos mo na ang LangChain4j para sa mga Baguhan na kurso. Natutunan mo ang:

- Paano bumuo ng conversational AI na may memorya (Module 01)
- Mga pattern sa prompt engineering para sa iba't ibang gawain (Module 02)
- Pag-ground ng mga sagot sa iyong mga dokumento gamit ang RAG (Module 03)
- Paglikha ng mga basic AI agents (mga katulong) gamit ang pasadyang mga tool (Module 04)
- Pagsasama ng standardized na mga tool gamit ang LangChain4j MCP at Agentic modules (Module 05)

### Ano ang Susunod?

Pagkatapos makumpleto ang mga module, tuklasin ang [Testing Guide](../docs/TESTING.md) upang makita ang mga konsepto ng testing ng LangChain4j sa aksyon.

**Opisyal na Mga Mapagkukunan:**
- [LangChain4j Documentation](https://docs.langchain4j.dev/) - Komprehensibong mga gabay at API reference
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Source code at mga halimbawa
- [LangChain4j Tutorials](https://docs.langchain4j.dev/tutorials/) - Mga sunud-sunod na tutorial para sa iba't ibang mga gamit

Salamat sa pagtapos ng kursong ito!

---

**Navigation:** [← Previous: Module 04 - Tools](../04-tools/README.md) | [Back to Main](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Pahayag ng Pagsagot**:
Ang dokumentong ito ay isinalin gamit ang AI translation service na [Co-op Translator](https://github.com/Azure/co-op-translator). Bagamat aming pinagsusumikapan ang katumpakan, mangyaring maging maingat na ang mga awtomatikong pagsasalin ay maaaring maglaman ng mga pagkakamali o di-tumpak na impormasyon. Ang orihinal na dokumento sa kanyang orihinal na wika ang dapat ituring na pinakapinagkakatiwalaang sanggunian. Para sa mahahalagang impormasyon, inirerekomenda ang propesyonal na pagsasalin ng tao. Hindi kami mananagot sa anumang hindi pagkakaunawaan o maling interpretasyon na nagmula sa paggamit ng pagsasaling ito.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->