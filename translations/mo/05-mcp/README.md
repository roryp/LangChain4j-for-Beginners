<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f89f4c106d110e4943c055dd1a2f1dff",
  "translation_date": "2025-12-30T21:16:36+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "mo"
}
-->
# Module 05: 模型上下文協定 (MCP)

## Table of Contents

- [你會學到甚麼](../../../05-mcp)
- [甚麼是 MCP?](../../../05-mcp)
- [MCP 如何運作](../../../05-mcp)
- [代理模組](../../../05-mcp)
- [執行範例](../../../05-mcp)
  - [先決條件](../../../05-mcp)
- [快速開始](../../../05-mcp)
  - [檔案操作 (Stdio)](../../../05-mcp)
  - [監督代理](../../../05-mcp)
    - [了解輸出](../../../05-mcp)
    - [代理模組功能說明](../../../05-mcp)
- [關鍵概念](../../../05-mcp)
- [恭喜！](../../../05-mcp)
  - [接下來做甚麼？](../../../05-mcp)

## 你會學到甚麼

你已經建立了對話式 AI、精通提示工程、將回應與文件連結（grounding），並建立了具工具的代理。但所有那些工具都是為你的特定應用量身打造的。如果你能讓 AI 存取一個任何人都能建立與分享的標準化工具生態系統，那會如何？在本模組中，你將學習如何使用模型上下文協定（MCP）和 LangChain4j 的代理模組來達成這一點。我們先展示一個簡單的 MCP 檔案讀取器，然後示範它如何輕鬆整合進使用監督代理模式的進階代理工作流程。

## 甚麼是 MCP?

模型上下文協定（MCP）正是提供這樣的標準 — 為 AI 應用發現與使用外部工具提供一致的方式。你不再需要為每個資料來源或服務撰寫自訂整合；只要連接到以一致格式揭露其功能的 MCP 伺服器。你的 AI 代理便能自動發現並使用這些工具。

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.mo.png" alt="MCP 比較" width="800"/>

*在 MCP 之前：複雜的點對點整合。MCP 之後：一個協定，無限可能。*

MCP 解決了 AI 開發中的一個基本問題：每個整合都是自訂的。想要存取 GitHub？要寫自訂程式。想要讀取檔案？要寫自訂程式。想要查詢資料庫？要寫自訂程式。而這些整合通常無法與其他 AI 應用共用。

MCP 將此標準化。MCP 伺服器以清楚的描述和參數架構揭露工具。任何 MCP 用戶端都能連線、發現可用工具並使用它們。一次建置，到處使用。

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.mo.png" alt="MCP 架構" width="800"/>

*模型上下文協定架構 — 標準化的工具發現與執行*

## MCP 如何運作

**伺服器-用戶端架構**

MCP 採用用戶端-伺服器模型。伺服器提供工具 — 讀取檔案、查詢資料庫、呼叫 API。用戶端（你的 AI 應用）連線至伺服器並使用其工具。

要在 LangChain4j 中使用 MCP，請新增此 Maven 相依：

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**工具發現**

當你的用戶端連線到 MCP 伺服器時，它會詢問「你有哪些工具？」伺服器會回傳可用工具清單，每項都有描述和參數架構。你的 AI 代理便能根據使用者要求決定要使用哪些工具。

**傳輸機制**

MCP 支援不同的傳輸機制。本模組示範用於本機程序的 Stdio 傳輸：

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.mo.png" alt="傳輸機制" width="800"/>

*MCP 傳輸機制：遠端伺服器使用 HTTP，本機程序使用 Stdio*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

用於本機程序。你的應用會以子程序方式啟動一個伺服器，並透過標準輸入/輸出進行通訊。適用於檔案系統存取或命令列工具。

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

> **🤖 在 [GitHub Copilot](https://github.com/features/copilot) Chat 試試：** 開啟 [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) 並詢問：
> - 「Stdio 傳輸如何運作？何時應該使用它而非 HTTP？」
> - 「LangChain4j 如何管理啟動的 MCP 伺服器程序的生命週期？」
> - 「讓 AI 存取檔案系統會有甚麼安全性影響？」

## 代理模組

雖然 MCP 提供標準化的工具，LangChain4j 的 **代理模組** 則提供以宣告式方式建立協調這些工具的代理的方法。`@Agent` 註解與 `AgenticServices` 讓你透過介面而非命令式程式碼定義代理行為。

在本模組中，你將探索 **監督代理 (Supervisor Agent)** 模式 — 一種進階的代理式 AI 方法，讓「監督」代理可根據使用者請求動態決定要呼叫哪些子代理。我們會結合兩個概念，賦予其中一個子代理 MCP 支援的檔案存取功能。

要使用代理模組，請新增此 Maven 相依：

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ 實驗性功能：** `langchain4j-agentic` 模組為 **實驗性**，未來可能變動。穩定的建立 AI 助手方式仍是使用 `langchain4j-core` 並搭配自訂工具（模組 04）。

## 執行範例

### 先決條件

- Java 21+、Maven 3.9+
- Node.js 16+ 及 npm（用於 MCP 伺服器）
- 在根目錄的 `.env` 檔案中設定環境變數：
  - **For StdioTransportDemo:** `GITHUB_TOKEN` (GitHub Personal Access Token)
  - **For SupervisorAgentDemo:** `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT`（與模組 01-04 相同）

> **注意：** 如果你還沒有設定環境變數，請參閱 [Module 00 - Quick Start](../00-quick-start/README.md) 取得說明，或將 `.env.example` 複製為根目錄下的 `.env` 並填入你的值。

## 快速開始

**使用 VS Code：** 在 Explorer 中對任一示範檔案按右鍵並選擇 **"Run Java"**，或從「執行與除錯」面板使用啟動設定（請先確保已將你的 token 新增到 `.env` 檔案）。

**使用 Maven：** 或者，你也可以從命令列以下列範例執行。

### 檔案操作 (Stdio)

此示範展示基於本機子程序的工具。

**✅ 不需先決條件** — MCP 伺服器會自動被啟動。

**使用 VS Code：** 對 `StdioTransportDemo.java` 按右鍵並選擇 **"Run Java"**。

**使用 Maven：**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

應用會自動啟動一個檔案系統的 MCP 伺服器並讀取本機檔案。注意子程序管理如何為你處理好。

**預期輸出：**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### 監督代理

<img src="../../../translated_images/agentic.cf84dcda226374e3.mo.png" alt="代理模組" width="800"/>


**監督代理模式** 是一種 **彈性** 的代理式 AI 形式。不同於確定性的工作流程（順序、迴圈、並行），監督代理使用 LLM 自主決定要根據使用者的請求呼叫哪些代理。

**將監督與 MCP 結合：** 在此範例中，我們透過 `toolProvider(mcpToolProvider)` 給予 `FileAgent` MCP 檔案系統工具的存取權限。當使用者要求「讀取並分析一個檔案」時，監督者會分析請求並生成執行計畫。接著它會將請求導向 `FileAgent`，`FileAgent` 使用 MCP 的 `read_file` 工具來取得內容。監督者再將該內容傳給 `AnalysisAgent` 做解讀，並視情況呼叫 `SummaryAgent` 來摘要結果。

這展示了 MCP 工具如何無縫整合進代理工作流程 — 監督者不需要知道檔案如何被讀取，只需知道 `FileAgent` 能做到。監督者能動態適應不同類型的請求，並回傳最後一個代理的回應或所有操作的摘要。

**使用啟動腳本（建議）：**

啟動腳本會自動從根目錄的 `.env` 檔案載入環境變數：

**Bash:**
```bash
cd 05-mcp
chmod +x start.sh
./start.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start.ps1
```

**使用 VS Code：** 對 `SupervisorAgentDemo.java` 按右鍵並選擇 **"Run Java"**（確保你的 `.env` 檔案已正確設定）。

**監督者如何運作：**

```java
// 定義多個具備特定能力的代理人
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // 具有用於檔案操作的 MCP 工具
        .build();

AnalysisAgent analysisAgent = AgenticServices.agentBuilder(AnalysisAgent.class)
        .chatModel(model)
        .build();

SummaryAgent summaryAgent = AgenticServices.agentBuilder(SummaryAgent.class)
        .chatModel(model)
        .build();

// 建立一個協調這些代理人的監督者
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)  // 「planner」模型
        .subAgents(fileAgent, analysisAgent, summaryAgent)
        .responseStrategy(SupervisorResponseStrategy.SUMMARY)
        .build();

// 監督者會自主決定調用哪些代理人
// 只需傳入自然語言請求 - LLM 會規劃執行
String response = supervisor.invoke("Read the file at /path/file.txt and analyze it");
```

完整實作請參閱 [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java)。

> **🤖 在 [GitHub Copilot](https://github.com/features/copilot) Chat 試試：** 開啟 [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) 並詢問：
> - 「監督者如何決定要呼叫哪些代理？」
> - 「監督模式與順序式工作流程模式有何不同？」
> - 「我如何自訂監督者的規劃行為？」

#### 了解輸出

執行範例時，你會看到一個結構化的演練，說明監督者如何協調多個代理。以下說明每個區段的含義：

```
======================================================================
  SUPERVISOR AGENT DEMO
======================================================================

This demo shows how a Supervisor Agent orchestrates multiple specialized agents.
The Supervisor uses an LLM to decide which agent to call based on the task.
```

**標頭** 介紹了範例並說明核心概念：監督者使用 LLM（而非硬編碼規則）來決定要呼叫哪些代理。

```
--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]     FileAgent     - Reads files using MCP filesystem tools
  [ANALYZE]  AnalysisAgent - Analyzes content for structure, tone, and themes
  [SUMMARY]  SummaryAgent  - Creates concise summaries of content
```

**可用代理** 顯示監督者可以選擇的三個專責代理。每個代理有特定能力：
- **FileAgent** can read files using MCP tools (external capability)
- **AnalysisAgent** analyzes content (pure LLM capability)
- **SummaryAgent** creates summaries (pure LLM capability)

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and analyze what it's about"
```

**使用者請求** 顯示使用者的提問。監督者必須解析此請求並決定要呼叫哪些代理。

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor will now decide which agents to invoke and in what order...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source Java library designed to simplify...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> AnalysisAgent (analyzing content)
  |
  |   Input: LangChain4j is an open-source Java library...
  |
  |   Result: Structure: The content is organized into clear paragraphs that int...
  +-- [OK] AnalysisAgent (analyzing content) completed
```

**監督者協調** 是魔法發生的地方。請注意：
1. 監督者因為請求提到「讀取該檔案」，所以**先選擇 FileAgent**
2. FileAgent 使用 MCP 的 `read_file` 工具來擷取檔案內容
3. 監督者接著**選擇 AnalysisAgent** 並將檔案內容傳給它
4. AnalysisAgent 分析了結構、語氣和主題

注意監督者是根據使用者請求**自主**做出這些決策 — 並無硬編碼的工作流程！

**最終回應** 是監督者綜合多個代理輸出後的答案。範例會將代理範圍（agentic scope）傾印出來，顯示每個代理儲存的摘要與分析結果。

```
--- FINAL RESPONSE ---------------------------------------------------
I read the contents of the file and analyzed its structure, tone, and key themes.
The file introduces LangChain4j as an open-source Java library for integrating
large language models...

--- AGENTIC SCOPE (Shared Memory) ------------------------------------
  Agents store their results in a shared scope for other agents to use:
  * summary: LangChain4j is an open-source Java library...
  * analysis: Structure: The content is organized into clear paragraphs that in...
```

### 代理模組功能說明

範例示範了代理模組的幾項進階功能。我們來更仔細地看看 Agentic 範圍與代理監聽器。

**Agentic 範圍** 顯示代理使用 `@Agent(outputKey="...")` 儲存結果的共享記憶。這允許：
- 後續代理存取先前代理的輸出
- 監督者綜合出一個最終回應
- 你檢視每個代理產出的內容

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String story = scope.readState("story");
List<AgentInvocation> history = scope.agentInvocations("analysisAgent");
```

**代理監聽器 (Agent Listeners)** 可用於監控與除錯代理執行。你在範例中看到的逐步輸出來自於一個掛勾到每次代理呼叫的 AgentListener：
- **beforeAgentInvocation** - 當監督者選擇代理時呼叫，讓你看到被選擇的代理與原因
- **afterAgentInvocation** - 當代理完成時呼叫，顯示其結果
- **inheritedBySubagents** - 若為 true，該監聽器會監控階層中的所有代理

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
        return true; // 傳播到所有子代理
    }
};
```

除了監督者模式外，`langchain4j-agentic` 模組還提供數種強大的工作流程模式與功能：

| Pattern | Description | Use Case |
|---------|-------------|----------|
| **Sequential** | 順序執行代理，輸出流向下一個代理 | 管線：研究 → 分析 → 報告 |
| **Parallel** | 同時執行代理 | 獨立任務：天氣 + 新聞 + 股票 |
| **Loop** | 迭代直到滿足條件 | 品質評分：反覆精煉直到分數 ≥ 0.8 |
| **Conditional** | 根據條件路由 | 分類 → 導向專家代理 |
| **Human-in-the-Loop** | 新增人工檢查點 | 核准工作流程、內容審核 |

## 關鍵概念

**MCP** 適合在你想要利用既有工具生態系、建立多個應用可共享的工具、以標準協定整合第三方服務，或在不改變程式碼的情況下替換工具實作時使用。

**代理模組** 適合在你想以 `@Agent` 註解進行宣告式代理定義、需要工作流程協調（順序、迴圈、並行）、偏好以介面為基礎的代理設計而非命令式程式碼，或是要結合多個透過 `outputKey` 分享輸出的代理時使用。

**監督代理模式** 在工作流程難以事先預測且你希望由 LLM 決定時最為出色；當你有多個專責代理需要動態協調、建立會將請求導向不同能力的對話系統，或你想要最具彈性與適應性的代理行為時非常適合。

## 恭喜！

你已完成 LangChain4j for Beginners 課程。你已學會：

- 如何建立具記憶的對話式 AI（Module 01）
- 各式任務的提示工程模式（Module 02）
- 使用 RAG 將回應以文件為依據（Module 03）
- 使用自訂工具建立基本 AI 代理（助理）（Module 04）
- 整合標準化工具與 LangChain4j MCP 及 Agentic 模組 (模組 05)

### 接下來怎麼做？

完成各模組後，請參閱 [測試指南](../docs/TESTING.md) 以實際了解 LangChain4j 的測試概念。

**官方資源：**
- [LangChain4j 文件](https://docs.langchain4j.dev/) - 完整的指南與 API 參考
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - 原始碼與範例
- [LangChain4j 教學](https://docs.langchain4j.dev/tutorials/) - 針對各種使用情境的逐步教學

感謝你完成本課程！

---

**導覽：** [← 上一節：模組 04 - 工具](../04-tools/README.md) | [返回主頁](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免責聲明：
本文件為使用 AI 翻譯服務 Co-op Translator（https://github.com/Azure/co-op-translator）所翻譯。儘管我們力求準確，但請注意自動翻譯可能包含錯誤或不準確之處。原始文件之原文版本應視為具權威性的資料來源。對於關乎重要資訊，建議委託專業人工翻譯。對於因使用本翻譯而導致的任何誤解、曲解或其他後果，我們概不負責。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->