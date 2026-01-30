# Module 05: 模型上下文協議 (MCP)

## 目錄

- [你將學到什麼](../../../05-mcp)
- [什麼是 MCP？](../../../05-mcp)
- [MCP 如何運作](../../../05-mcp)
- [Agentic 模組](../../../05-mcp)
- [執行範例](../../../05-mcp)
  - [先決條件](../../../05-mcp)
- [快速開始](../../../05-mcp)
  - [檔案操作 (Stdio)](../../../05-mcp)
  - [主管代理](../../../05-mcp)
    - [了解輸出](../../../05-mcp)
    - [回應策略](../../../05-mcp)
    - [Agentic 模組功能說明](../../../05-mcp)
- [關鍵概念](../../../05-mcp)
- [恭喜！](../../../05-mcp)
  - [接下來呢？](../../../05-mcp)

## 你將學到什麼

你已經建立了對話式 AI，精通提示語，並能夠根據文件產生對應回答，也創建了帶有工具的代理。但這些工具都是為你的特定應用專門製作。假如你可以讓你的 AI 存取一個任何人都能創建並分享的標準化工具生態系統，該怎麼辦？在本模組中，你將學會如何透過模型上下文協議 (MCP) 與 LangChain4j 的 agentic 模組來實現這一點。我們將首先展示一個簡單的 MCP 檔案閱讀器，接著展示如何輕鬆整合進階的代理工作流程，採用主管代理模式 (Supervisor Agent pattern)。

## 什麼是 MCP？

模型上下文協議 (MCP) 正是為此提供了解決方案——一種 AI 應用可發現並使用外部工具的標準方式。不用為每個資料來源或服務編寫專用整合，你只需連接到以一致格式公開能力的 MCP 服務器。你的 AI 代理即可自動發現並使用這些工具。

<img src="../../../translated_images/zh-TW/mcp-comparison.9129a881ecf10ff5.webp" alt="MCP Comparison" width="800"/>

*MCP 之前：複雜的點對點整合。MCP 之後：一個協議，無限可能。*

MCP 解決了 AI 開發中的根本問題：每項整合都是自訂的。想訪問 GitHub？自訂程式碼。想讀取檔案？自訂程式碼。想查詢資料庫？自訂程式碼。這些整合都無法相容於其他 AI 應用。

MCP 將此標準化。MCP 服務器公開帶有清晰描述和結構的工具。任何 MCP 用戶端都能連接、發現可用工具並使用。一次建構，到處使用。

<img src="../../../translated_images/zh-TW/mcp-architecture.b3156d787a4ceac9.webp" alt="MCP Architecture" width="800"/>

*模型上下文協議架構——標準化工具發現與執行*

## MCP 如何運作

**伺服器-用戶端架構**

MCP 採用用戶端-伺服器模型。伺服器提供工具——讀取檔案、查詢資料庫、呼叫 API。用戶端（你的 AI 應用）連接伺服器並使用其工具。

要在 LangChain4j 使用 MCP，加入以下 Maven 相依：

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**工具發現**

當用戶端連接 MCP 伺服器時，會詢問「你有哪些工具？」伺服器回傳其可用工具清單，每個工具附帶描述與參數結構。你的 AI 代理能根據使用者請求決定使用哪些工具。

**傳輸機制**

MCP 支援不同的傳輸機制。本模組示範本地程序的 Stdio 傳輸：

<img src="../../../translated_images/zh-TW/transport-mechanisms.2791ba7ee93cf020.webp" alt="Transport Mechanisms" width="800"/>

*MCP 傳輸機制：遠端伺服器使用 HTTP，本地程序使用 Stdio*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

用於本地程序。你的程式會啟動一個子程序作為伺服器，透過標準輸入/輸出通訊。適合檔案系統存取或命令列工具。

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

> **🤖 嘗試使用 [GitHub Copilot](https://github.com/features/copilot) 聊天：** 開啟 [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) 並提問：
> - 「Stdio 傳輸是如何運作的？什麼時候該用它而非 HTTP？」
> - 「LangChain4j 怎麼管理啟動 MCP 伺服器程序的生命週期？」
> - 「讓 AI 存取檔案系統會有哪些安全性考量？」

## Agentic 模組

雖然 MCP 提供標準化工具，LangChain4j 的 **agentic 模組** 提供一種宣告式方式來構建協調這些工具的代理。`@Agent` 註解和 `AgenticServices` 能讓你透過介面定義代理行為，而非命令式程式碼。

本模組將探討 **主管代理** 模式——一種進階的 agentic AI 方式，主管代理根據使用者請求動態決定調用哪些子代理。我們結合這兩個概念，讓其中一個子代理擁有 MCP 提供的檔案存取功能。

要使用 agentic 模組，加入以下 Maven 相依：

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ 實驗性功能：** `langchain4j-agentic` 模組屬於 **實驗性**，未來可能變動。穩定的 AI 助理建構方式仍為 `langchain4j-core` 加自訂工具（模組 04）。

## 執行範例

### 先決條件

- Java 21 以上，Maven 3.9 以上
- Node.js 16 以上與 npm（用於 MCP 伺服器）
- 根目錄 `.env` 檔案內配置好環境變數：
  - `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT`（與模組 01-04 相同）

> **注意：** 若尚未設定環境變數，請參閱 [模組 00 - 快速開始](../00-quick-start/README.md)，或在根目錄將 `.env.example` 複製為 `.env` 並填入你的值。

## 快速開始

**使用 VS Code：** 直接在檔案總管對任一示範檔案點右鍵選擇 **「執行 Java」**，或從執行與偵錯面板使用啟動配置（請先在 `.env` 加入你的 Token）。

**使用 Maven：** 也可從命令列使用以下範例執行。

### 檔案操作 (Stdio)

這示範基於本地子程序的工具。

**✅ 無需先決條件** — MCP 伺服器會自動啟動。

**使用啟動腳本（推薦）：**

啟動腳本會自動載入根目錄 `.env` 文件的環境變數：

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

**使用 VS Code：** 右鍵 `StdioTransportDemo.java`，選擇 **「執行 Java」**（請確保 `.env` 已設定）。

應用程式會自動啟動一個檔案系統 MCP 伺服器並讀取本地檔案。你會看到子程序管理自動完成。

**預期輸出：**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### 主管代理

**主管代理模式** 是一種 **彈性** 的 agentic AI。主管代理使用大型語言模型 (LLM) 自主決定基於使用者請求調用哪些代理。下一個範例將結合 MCP 的檔案存取功能與 LLM 代理，創造一個監督式的「讀檔→報告」工作流程。

示範中，`FileAgent` 使用 MCP 檔案系統工具讀取檔案，`ReportAgent` 生成結構化報告，包括一段執行摘要（一句話）、三個重點與建議。主管代理自動協調這整個流程：

<img src="../../../translated_images/zh-TW/agentic.cf84dcda226374e3.webp" alt="Agentic Module" width="800"/>

```
┌─────────────┐      ┌──────────────┐
│  FileAgent  │ ───▶ │ ReportAgent  │
│ (MCP tools) │      │  (pure LLM)  │
└─────────────┘      └──────────────┘
   outputKey:           outputKey:
  'fileContent'         'report'
```

每個代理將輸出存於 **Agentic 範圍** (Agentic Scope，共享記憶空間)，下游代理可存取先前結果。這演示了 MCP 工具如何無縫整合進 agentic 工作流程——主管無需知道檔案是 *如何* 讀取，只需知道 `FileAgent` 能做到。

#### 執行範例

啟動腳本會自動載入根目錄 `.env` 文件的環境變數：

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

**使用 VS Code：** 右鍵 `SupervisorAgentDemo.java`，選擇 **「執行 Java」**（請確保 `.env` 已設定）。

#### 主管代理如何運作

```java
// 第一步：FileAgent 使用 MCP 工具讀取檔案
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // 具有用於檔案操作的 MCP 工具
        .build();

// 第二步：ReportAgent 生成結構化報告
ReportAgent reportAgent = AgenticServices.agentBuilder(ReportAgent.class)
        .chatModel(model)
        .build();

// 監督者協調檔案 → 報告的工作流程
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)
        .subAgents(fileAgent, reportAgent)
        .responseStrategy(SupervisorResponseStrategy.LAST)  // 返回最終報告
        .build();

// 監督者根據請求決定調用哪些代理人
String response = supervisor.invoke("Read the file at /path/file.txt and generate a report");
```

#### 回應策略

當你配置 `SupervisorAgent` 時，要指定在子代理執行完畢後，主管如何形成對使用者的最終回答。可用策略有：

| 策略       | 說明                                                         |
|------------|--------------------------------------------------------------|
| **LAST**   | 主管回傳最後呼叫的子代理或工具輸出。適用於工作流程最後代理專門產出完整最終答案（如研究流程中的「摘要代理」）。 |
| **SUMMARY**| 主管使用內部大型語言模型彙整整個互動與所有子代理輸出摘要，回傳此綜合摘要作為最終回應。提供乾淨且整合的回答給使用者。 |
| **SCORED** | 系統使用內部大型語言模型評分最後回應與整體摘要相較於原始使用者請求，回傳得分較高的輸出。 |

完整實作請見 [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java)。

> **🤖 嘗試使用 [GitHub Copilot](https://github.com/features/copilot) 聊天：** 打開 [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java)，並問：
> - 「主管如何決定呼叫哪些代理？」
> - 「主管代理與序列化工作流程模式有何差異？」
> - 「我如何自訂主管的規劃行為？」

#### 了解輸出

執行範例時，你會看到主管如何協調多個代理的結構化流程。以下是每部分的意義：

```
======================================================================
  FILE → REPORT WORKFLOW DEMO
======================================================================

This demo shows a clear 2-step workflow: read a file, then generate a report.
The Supervisor orchestrates the agents automatically based on the request.
```

**標題**介紹工作流程概念：專注於從讀檔到報告產生的流程。

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

**工作流程圖**展示代理間的資料流。每個代理有明確角色：
- **FileAgent** 使用 MCP 工具讀取檔案，將原始內容存入 `fileContent`
- **ReportAgent** 吃進內容，輸出結構化報告於 `report`

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and generate a report on its contents"
```

**使用者請求**展示任務。主管解析後決定先呼叫 FileAgent，再呼叫 ReportAgent。

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

**主管協調**展示兩步驟流程運作：
1. **FileAgent** 透過 MCP 讀檔並存內容
2. **ReportAgent** 取得內容產生結構化報告

主管根據使用者請求**自主**做出這些決策。

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

此範例展示 agentic 模組多項進階功能，我們特別關注 Agentic 範圍與代理監聽器。

**Agentic 範圍** 展示共享記憶，代理使用 `@Agent(outputKey="...")` 儲存結果。這允許：
- 後續代理存取先前代理輸出
- 主管綜合形成最終回應
- 你檢視各代理所產生內容

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String fileContent = scope.readState("fileContent");  // 來自 FileAgent 的原始檔案資料
String report = scope.readState("report");            // 來自 ReportAgent 的結構化報告
```

**代理監聽器** 可用於監控和除錯代理執行。範例中你看到的逐步輸出，來自掛載在每次代理呼叫的 AgentListener：
- **beforeAgentInvocation** - 當主管選擇代理時調用，可觀察決定了哪個代理及原因
- **afterAgentInvocation** - 代理完成時調用，顯示結果
- **inheritedBySubagents** - 設為 true 時，監聽器將監控整個代理階層

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

除了主管模式外，`langchain4j-agentic` 模組還提供多種強大工作流程模式與功能：

| 模式           | 說明                           | 使用情境                       |
|----------------|--------------------------------|--------------------------------|
| **Sequential** | 按順序執行代理，輸出傳給下一個 | 管道作業：研究→分析→報告       |
| **Parallel**   | 同時並行執行代理               | 獨立任務：天氣 + 新聞 + 股市    |
| **Loop**       | 迭代直到符合條件              | 品質評分：重複直到分數≥0.8     |
| **Conditional**| 根據條件路由                  | 分類→導向專家代理              |
| **Human-in-the-Loop** | 加入人工核查               | 審核工作流程、內容審查          |

## 關鍵概念

既然你已探索 MCP 與 agentic 模組的實際應用，讓我們總結何時使用哪種方式。

**MCP** 適用於想利用現有工具生態、構建可被多個應用共享的工具、與第三方服務採用標準協議整合，或想在不改程式碼的情況下替換工具實作。

**Agentic 模組** 適用於想用 `@Agent` 註解宣告代理、需要工作流程協調（序列、迴圈、並行）、偏好介面導向代理設計而非命令式，或結合多代理共享 `outputKey` 輸出。

**主管代理模式** 適用於流程不可預先完全規劃，需要 LLM 決策，擁有多個專精代理需動態協調，構建可路由不同能力的對話系統，或追求最靈活、最具適應性的代理行為。
## 恭喜！

您已完成 LangChain4j 初學者課程。您已學會：

- 如何構建帶有記憶的會話式 AI（模組 01）
- 不同任務的提示工程模式（模組 02）
- 使用 RAG 將回應依據您的文件（模組 03）
- 使用自訂工具創建基本的 AI 代理（助理）（模組 04）
- 使用 LangChain4j MCP 和 Agentic 模組整合標準化工具（模組 05）

### 接下來呢？

完成模組後，請探索 [測試指南](../docs/TESTING.md) 以了解 LangChain4j 測試概念的實作。

**官方資源：**
- [LangChain4j 文件](https://docs.langchain4j.dev/) - 全面指南與 API 參考
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - 原始碼與範例
- [LangChain4j 教學](https://docs.langchain4j.dev/tutorials/) - 各種使用案例的逐步教學

感謝您完成本課程！

---

**導航：** [← 上一章：模組 04 - 工具](../04-tools/README.md) | [回主頁](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：  
本文件使用 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們致力於提供準確的翻譯，但自動翻譯可能包含錯誤或不精確之處。請以原始語言版本的文件為權威來源。對於重要資訊，建議採用專業人工翻譯。我們不對因使用此翻譯而產生的任何誤解或錯誤解讀負責。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->