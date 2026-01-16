<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "6c816d130a1fa47570c11907e72d84ae",
  "translation_date": "2026-01-05T22:04:27+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "hk"
}
-->
# Module 05: 模型上下文協議 (MCP)

## 目錄

- [你將學到什麼](../../../05-mcp)
- [什麼是 MCP？](../../../05-mcp)
- [MCP 如何運作](../../../05-mcp)
- [Agentic 模組](../../../05-mcp)
- [執行範例](../../../05-mcp)
  - [先決條件](../../../05-mcp)
- [快速開始](../../../05-mcp)
  - [檔案操作（Stdio）](../../../05-mcp)
  - [監督代理](../../../05-mcp)
    - [理解輸出](../../../05-mcp)
    - [回應策略](../../../05-mcp)
    - [Agentic 模組功能說明](../../../05-mcp)
- [關鍵概念](../../../05-mcp)
- [恭喜！](../../../05-mcp)
  - [接下來做什麼？](../../../05-mcp)

## 你將學到什麼

你已經建立了會話式 AI，掌握了提示語、讓回應有依據的文件支持，並且創建了具備工具的代理。但所有這些工具都是為你特定應用定制的。如果你能讓你的 AI 訪問一個標準化的工具生態系統，任何人都能創建和共享，那會怎麼樣？在本模組中，你將學習如何使用模型上下文協議（MCP）和 LangChain4j 的 agentic 模組做到這一點。我們首先展示一個簡單的 MCP 檔案閱讀器，接著展示如何輕鬆整合到使用監督代理模式的高級 agentic 工作流程中。

## 什麼是 MCP？

模型上下文協議 (MCP) 就是為此提供標準方式的工具 — 一種標準方法讓 AI 應用發現並使用外部工具。你不必為每個資料來源或服務寫自定義整合，而是連接到 MCP 伺服器，這些伺服器以一致格式暴露其能力。你的 AI 代理就能自動發現並使用這些工具。

<img src="../../../translated_images/hk/mcp-comparison.9129a881ecf10ff5.png" alt="MCP Comparison" width="800"/>

*MCP 之前：複雜的點對點整合。MCP 之後：一個協議，無限可能。*

MCP 解決了 AI 開發中的一個根本問題：每個整合都是定制的。想訪問 GitHub？自訂程式碼。想讀取檔案？自訂程式碼。想查詢資料庫？自訂程式碼。這些整合都無法與其他 AI 應用通用。

MCP 將這一切標準化。MCP 伺服器以清楚的描述和模式暴露工具。任何 MCP 用戶端都能連接、發現可用工具並使用它們。一次建立，到處使用。

<img src="../../../translated_images/hk/mcp-architecture.b3156d787a4ceac9.png" alt="MCP Architecture" width="800"/>

*模型上下文協議架構 — 標準化工具發現與執行*

## MCP 如何運作

**伺服器-客戶端架構**

MCP 採用客戶端-伺服器模型。伺服器提供工具 — 讀檔、查詢資料庫、呼叫 API。客戶端（你的 AI 應用）連接伺服器並使用其工具。

要在 LangChain4j 中使用 MCP，添加此 Maven 依賴：

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**工具發現**

當你的用戶端連接 MCP 伺服器時，它會詢問「你有哪些工具？」伺服器回應可用工具清單，每個帶有描述和參數模式。你的 AI 代理根據使用者請求決定使用哪種工具。

**傳輸機制**

MCP 支援不同的傳輸機制。本模組演示本地程序的 Stdio 傳輸：

<img src="../../../translated_images/hk/transport-mechanisms.2791ba7ee93cf020.png" alt="Transport Mechanisms" width="800"/>

*MCP 傳輸機制：遠端伺服器使用 HTTP，本地程序使用 Stdio*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

用於本地程序。你的應用啟動一個子程序作為伺服器，通過標準輸入/輸出通訊。適合檔案系統存取或命令行工具。

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

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) Chat 嘗試：** 開啟 [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) 並問：
> - 「Stdio 傳輸如何工作？什麼時候該用它而不是 HTTP？」
> - 「LangChain4j 如何管理啟動的 MCP 伺服器程序生命週期？」
> - 「給 AI 訪問檔案系統有哪些安全考量？」

## Agentic 模組

MCP 提供標準化工具，LangChain4j 的 **agentic 模組** 則提供宣告式方式來構建協調這些工具的代理。`@Agent` 註解和 `AgenticServices` 讓你透過介面定義代理行為，而非命令式程式碼。

在本模組中，你將探索 **監督代理** (Supervisor Agent) 模式 — 一種進階的 agentic AI 方式，由一個「監督」代理根據使用者請求動態決定調用哪些子代理。我們將結合兩者概念，讓一個子代理擁有 MCP 支援的檔案存取能力。

要使用 agentic 模組，添加此 Maven 依賴：

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ 實驗性功能：** `langchain4j-agentic` 模組為**實驗性**，可能變動。建構 AI 助手的穩定方式仍是使用帶有自定義工具的 `langchain4j-core`（模組04）。

## 執行範例

### 先決條件

- Java 21+、Maven 3.9+
- Node.js 16+ 和 npm（用於 MCP 伺服器）
- 根目錄 `.env` 配置環境變數：
  - `AZURE_OPENAI_ENDPOINT`、`AZURE_OPENAI_API_KEY`、`AZURE_OPENAI_DEPLOYMENT`（同模組01-04）

> **注意：** 若未設定環境變數，請參閱 [模組00 - 快速開始](../00-quick-start/README.md) 指引，或將根目錄的 `.env.example` 複製為 `.env` 並填入值。

## 快速開始

**使用 VS Code：** 在探索器中對任何示範檔案右鍵，選擇 **「Run Java」**，或使用執行和偵錯面板的啟動設定（確保已將憑證填入 `.env`）。

**使用 Maven：** 也可從命令列執行以下範例。

### 檔案操作（Stdio）

示範本地子程序工具。

**✅ 不需其他依賴** — MCP 伺服器會自動啟動。

**使用啟動腳本（推薦）：**

啟動腳本會自動從根目錄 `.env` 載入環境變數：

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

**使用 VS Code:** 右鍵 `StdioTransportDemo.java` 並選擇 **「Run Java」**（確保 `.env` 設定完成）。

應用將自動啟動檔案系統 MCP 伺服器並讀取本地檔案。注意子程序管理由系統自動處理。

**預期輸出：**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### 監督代理

**監督代理模式** 是一種 **彈性** 的 agentic AI 形式。監督者使用大型語言模型 (LLM) 根據使用者請求自主決定調用哪些代理。在下一範例中，我們結合 MCP 支援的檔案存取與 LLM 代理，創造一個監督的檔案讀取 → 報告工作流程。

範例中 `FileAgent` 使用 MCP 檔案系統工具讀檔，`ReportAgent` 生成帶有執行摘要（1 句話）、3 個重點條目和建議的結構化報告。監督者自動協調流程：

<img src="../../../translated_images/hk/agentic.cf84dcda226374e3.png" alt="Agentic Module" width="800"/>

```
┌─────────────┐      ┌──────────────┐
│  FileAgent  │ ───▶ │ ReportAgent  │
│ (MCP tools) │      │  (pure LLM)  │
└─────────────┘      └──────────────┘
   outputKey:           outputKey:
  'fileContent'         'report'
```

每個代理將輸出存入 **Agentic Scope**（共享記憶體），允許下游代理存取先前結果。此示範表示 MCP 工具如何無縫整合進 agentic 工作流程 — 監督者不必知道文件如何被讀取，只需要知道 `FileAgent` 可做到。

#### 執行示範

啟動腳本會自動從根 `.env` 載入環境變數：

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

**使用 VS Code:** 右鍵 `SupervisorAgentDemo.java` 並選擇 **「Run Java」**（確保 `.env` 已設定）。

#### 監督者如何工作

```java
// 第一步：FileAgent 使用 MCP 工具讀取文件
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // 擁有用於文件操作的 MCP 工具
        .build();

// 第二步：ReportAgent 生成結構化報告
ReportAgent reportAgent = AgenticServices.agentBuilder(ReportAgent.class)
        .chatModel(model)
        .build();

// 監督者協調文件到報告的工作流程
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)
        .subAgents(fileAgent, reportAgent)
        .responseStrategy(SupervisorResponseStrategy.LAST)  // 返回最終報告
        .build();

// 監督者根據請求決定調用哪些代理
String response = supervisor.invoke("Read the file at /path/file.txt and generate a report");
```

#### 回應策略

設定 `SupervisorAgent` 時，你指定了它在子代理完成任務後如何形成對使用者的最終回答。可用策略包括：

| 策略 | 說明 |
|------|------|
| **LAST** | 監督者回傳最後一個子代理或工具的輸出。當工作流程中最後一個代理專門用於產生完整最終答案時很有用（例如研究管線中的「摘要代理」）。 |
| **SUMMARY** | 監督者使用內部大型語言模型（LLM）綜合整個互動及所有子代理輸出後的摘要，並以此摘要作為最終回應。提供使用者乾淨、整合過的答案。 |
| **SCORED** | 系統使用內部 LLM 對最後回應與整體摘要根據使用者原始請求打分，回傳得分較高的回應。 |

完整實現請見 [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java)。

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) Chat 嘗試：** 開啟 [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) 並問：
> - 「監督者如何決定調用哪些代理？」
> - 「監督與順序工作流程模式有何差異？」
> - 「如何自訂監督者的規劃行為？」

#### 理解輸出

執行示範時，你會看到監督者如何協調多個代理的結構化流程。以下是各部分說明：

```
======================================================================
  FILE → REPORT WORKFLOW DEMO
======================================================================

This demo shows a clear 2-step workflow: read a file, then generate a report.
The Supervisor orchestrates the agents automatically based on the request.
```

**標題**介紹工作流程概念：從讀檔到報告的專注管線。

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

**工作流程圖**顯示代理間的資料流。每個代理有特定角色：
- **FileAgent** 使用 MCP 工具讀檔，將原始內容存至 `fileContent`
- **ReportAgent** 消耗內容並生成結構化報告存於 `report`

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and generate a report on its contents"
```

**使用者請求**呈現任務目標。監督者解析後決定順序調用 FileAgent→ReportAgent。

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

**監督者協調**展示兩步驟流程：
1. **FileAgent** 透過 MCP 讀檔並儲存內容
2. **ReportAgent** 接收內容，生成結構化報告

監督者根據使用者請求**自主**做出決策。

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

#### Agentic 模組功能說明

範例展示了 agentic 模組的多種進階功能。來深入看看 Agentic Scope 和 Agent 監聽器。

**Agentic Scope** 是共享記憶體，代理用 `@Agent(outputKey="...")` 將結果存入。允許：
- 後續代理存取之前輸出
- 監督者綜合生成最終答案
- 你檢查各代理產出內容

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String fileContent = scope.readState("fileContent");  // FileAgent 的原始檔案數據
String report = scope.readState("report");            // ReportAgent 的結構化報告
```

**Agent 監聽器** 使監控與除錯代理執行成為可能。示範中的逐步輸出由一個監聽器產生，隨代理調用掛鉤：
- **beforeAgentInvocation** - 監督者選擇代理時呼叫，顯示選擇代理與理由
- **afterAgentInvocation** - 代理完成時呼叫，顯示執行結果
- **inheritedBySubagents** - 設為 true 時監控階層中所有代理

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
        return true; // 傳播至所有子代理
    }
};
```

除監督者模式外，`langchain4j-agentic` 模組還提供多種強大工作流程模式及功能：

| 模式 | 說明 | 使用案例 |
|------|------|---------|
| **Sequential（順序）** | 依序執行代理，輸出流向下一個 | 管線：研究 → 分析 → 報告 |
| **Parallel（平行）** | 同時執行多代理 | 獨立任務：天氣 + 新聞 + 股市 |
| **Loop（迴圈）** | 重複直到條件達成 | 品質評分：優化直到分數 ≥ 0.8 |
| **Conditional（條件）** | 根據條件路由 | 分類 → 導向專家代理 |
| **Human-in-the-Loop（人類參與）** | 加入人工檢查點 | 核可工作流程、內容審核 |

## 關鍵概念

探索 MCP 與 agentic 模組後，總結何時使用各種方式。

**MCP** 適合想利用已建生態系統工具、打造可被多應用共用的工具、標準化整合第三方服務，或想無需改程式碼就更換工具實作時。

**Agentic 模組** 適合想用宣告式 `@Agent` 註解定義代理、需要工作流程協調（順序、迴圈、平行）、偏好介面設計勝過命令式程式碼，或組合多代理共享輸出時。

**監督代理模式** 優勢在工作流程難以預先規劃，由 LLM 動態決定時，有多個專門代理需動態協調時，以及構建會話系統需根據能力路由或想要最靈活、適應性最高代理行為時。
## 恭喜！

你已完成 LangChain4j 初學者課程。你已學會：

- 如何構建具備記憶的會話式 AI（模組 01）
- 不同任務的提示工程模式（模組 02）
- 使用 RAG 根據你的文件構建回應依據（模組 03）
- 使用自訂工具創建基本的 AI 代理（助理）（模組 04）
- 使用 LangChain4j MCP 和 Agentic 模組整合標準化工具（模組 05）

### 下一步？

完成模組後，探索 [測試指南](../docs/TESTING.md) 以查看 LangChain4j 測試概念的實際應用。

**官方資源：**
- [LangChain4j 文件](https://docs.langchain4j.dev/) - 全面指南與 API 參考
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - 原始碼與範例
- [LangChain4j 教學](https://docs.langchain4j.dev/tutorials/) - 各種使用案例的逐步教學

感謝你完成本課程！

---

**導覽：** [← 上一頁：模組 04 - 工具](../04-tools/README.md) | [返回主頁](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：
本文件已使用 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。儘管我們努力確保準確性，但請注意，自動翻譯可能包含錯誤或不準確之處。原文件之母語版本應視為權威來源。對於關鍵資訊，建議使用專業人工翻譯。我們不對因使用本翻譯而引起的任何誤解或誤譯承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->