# LangChain4j 術語表

## 目錄

- [核心概念](../../../docs)
- [LangChain4j 組件](../../../docs)
- [AI/ML 概念](../../../docs)
- [安全防護措施](../../../docs)
- [提示工程](../../../docs)
- [RAG（檢索增強生成）](../../../docs)
- [代理程式與工具](../../../docs)
- [Agentic 模組](../../../docs)
- [模型上下文協議 (MCP)](../../../docs)
- [Azure 服務](../../../docs)
- [測試與開發](../../../docs)

本課程使用詞彙與概念之快速參考。

## 核心概念

**AI 代理程式** - 使用 AI 進行推理與自主行動的系統。[模組 04](../04-tools/README.md)

**鏈** - 運算序列，輸出餵入下一步。

**分塊** - 將文件拆成較小片段。典型大小：300-500 令牌，含重疊。[模組 03](../03-rag/README.md)

**上下文視窗** - 模型可處理的最大令牌數。GPT-5：40 萬令牌。

**向量嵌入** - 代表文本意義的數值向量。[模組 03](../03-rag/README.md)

**函數呼叫** - 模型生成結構化請求調用外部函數。[模組 04](../04-tools/README.md)

**幻覺** - 模型產生錯誤但看似合理的資訊。

**提示** - 送入語言模型的文本輸入。[模組 02](../02-prompt-engineering/README.md)

**語義搜尋** - 透過意義（使用嵌入）而非關鍵字搜尋。[模組 03](../03-rag/README.md)

**有狀態與無狀態** - 無狀態：無記憶；有狀態：維持對話歷史。[模組 01](../01-introduction/README.md)

**令牌** - 模型處理的文字基本單位。影響成本與限制。[模組 01](../01-introduction/README.md)

**工具鏈** - 按序執行工具，輸出提供下一次呼叫依據。[模組 04](../04-tools/README.md)

## LangChain4j 組件

**AiServices** - 建立型別安全的 AI 服務介面。

**OpenAiOfficialChatModel** - 統一 OpenAI 與 Azure OpenAI 模型用戶端。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI Official 用戶端產生嵌入（支援 OpenAI 與 Azure OpenAI）。

**ChatModel** - 語言模型核心介面。

**ChatMemory** - 維護對話歷史。

**ContentRetriever** - 尋找 RAG 相關文件片段。

**DocumentSplitter** - 拆分文件為區塊。

**EmbeddingModel** - 將文本轉換成數值向量。

**EmbeddingStore** - 儲存與取用嵌入。

**MessageWindowChatMemory** - 維護近期訊息的滑動視窗。

**PromptTemplate** - 使用 `{{variable}}` 佔位符創造可重用提示。

**TextSegment** - 帶有元資料的文本片段。用於 RAG。

**ToolExecutionRequest** - 表示工具執行請求。

**UserMessage / AiMessage / SystemMessage** - 對話訊息類型。

## AI/ML 概念

**少量範例學習** - 在提示中提供範例。[模組 02](../02-prompt-engineering/README.md)

**大型語言模型 (LLM)** - 訓練於大量文本資料的 AI 模型。

**推理深度** - GPT-5 控制思考深度的參數。[模組 02](../02-prompt-engineering/README.md)

**溫度** - 控制輸出隨機度。低=確定性，高=創意。

**向量資料庫** - 專門用於嵌入的資料庫。[模組 03](../03-rag/README.md)

**零示例學習** - 無需範例即可執行任務。[模組 02](../02-prompt-engineering/README.md)

## 安全防護措施 - [模組 00](../00-quick-start/README.md)

**多層防禦** - 多層級安全策略，結合應用層防護與提供者安全過濾器。

**嚴重阻止** - 提供者因嚴重內容違規回 HTTP 400 錯誤。

**輸入防護** - LangChain4j 驗證用戶輸入介面。在 LLM 前攔截有害提示，節省成本與延遲。

**輸入防護結果** - 防護驗證回傳類型：`success()` 或 `fatal("原因")`。

**輸出防護** - 驗證 AI 回應介面，回傳給使用者前檢查。

**提供者安全過濾** - AI 提供者內建內容過濾器（例如 GitHub Models），於 API 層攔截違規。

**軟性拒絕** - 模型有禮貌地拒絕回答，無錯誤拋出。

## 提示工程 - [模組 02](../02-prompt-engineering/README.md)

**鏈式思考** - 逐步推理提升準確度。

**受限輸出** - 強制特定格式或結構。

**高度積極性** - GPT-5 用於深入推理的模式。

**低度積極性** - GPT-5 用於快速回答的模式。

**多輪對話** - 跨交換維持上下文。

**角色基礎提示** - 透過系統訊息設定模型角色。

**自我反思** - 模型自評並改善輸出。

**結構化分析** - 固定評估框架。

**任務執行模式** - 計畫 → 執行 → 彙總。

## RAG（檢索增強生成） - [模組 03](../03-rag/README.md)

**文件處理流程** - 載入 → 分塊 → 嵌入 → 儲存。

**記憶體內嵌入庫** - 用於測試的非持久儲存。

**RAG** - 結合檢索與生成以實證回應。

**相似度分數** - 語義相似度量表（0-1）。

**來源參考** - 取回內容的元資料。

## 代理程式與工具 - [模組 04](../04-tools/README.md)

**@Tool 註解** - 標記 Java 方法為 AI 可調用工具。

**ReAct 模式** - 推理 → 行動 → 觀察 → 重複。

**會話管理** - 不同用戶隔離上下文。

**工具** - AI 代理可呼叫的函數。

**工具說明** - 工具目的與參數文件。

## Agentic 模組 - [模組 05](../05-mcp/README.md)

**@Agent 註解** - 標記介面為具宣告式行為定義的 AI 代理。

**代理聆聽器** - 透過 `beforeAgentInvocation()` 與 `afterAgentInvocation()` 監視代理執行的掛鉤。

**Agentic 作用域** - 代理以 `outputKey` 儲存輸出以供後續代理使用的共享記憶。

**AgenticServices** - 使用 `agentBuilder()` 和 `supervisorBuilder()` 建立代理的工廠。

**條件式工作流程** - 根據條件導向不同專家代理。

**人類參與流程** - 增加人類檢查點，供審批或內容審核。

**langchain4j-agentic** - 用於宣告式代理構建的 Maven 依賴（實驗性）。

**迴圈工作流程** - 重復代理執行直到滿足條件（例如品質分數≥0.8）。

**outputKey** - 代理註解參數，指定結果於 Agentic 作用域的存放位置。

**平行工作流程** - 同時執行多代理處理獨立任務。

**回應策略** - 監督者決定最終回答方式：LAST、SUMMARY 或 SCORED。

**序列工作流程** - 按順序執行代理，輸出流向下一步。

**監督代理模式** - 高階 agentic 模式，由監督 LLM 動態決定調用哪些子代理。

## 模型上下文協議 (MCP) - [模組 05](../05-mcp/README.md)

**langchain4j-mcp** - LangChain4j 的 MCP 整合 Maven 依賴。

**MCP** - 模型上下文協議：連接 AI 應用與外部工具的標準。一次建置，多處使用。

**MCP 用戶端** - 連接 MCP 伺服器，發現與使用工具的應用程式。

**MCP 伺服器** - 透過 MCP 以明確描述與參數架構暴露工具的服務。

**McpToolProvider** - LangChain4j 組件，包裝 MCP 工具供 AI 服務與代理使用。

**McpTransport** - MCP 通訊介面。實作包含 Stdio 與 HTTP。

**Stdio 傳輸** - 透過 stdin/stdout 的本地程序傳輸。適用於檔案系統存取或命令列工具。

**StdioMcpTransport** - LangChain4j 實作，作為子程序啟動 MCP 伺服器。

**工具發現** - 用戶端詢問伺服器可用工具及其描述與架構。

## Azure 服務 - [模組 01](../01-introduction/README.md)

**Azure AI Search** - 具向量能力的雲端搜尋服務。[模組 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 部署 Azure 資源。

**Azure OpenAI** - 微軟企業 AI 服務。

**Bicep** - Azure 基礎架構即程式碼語言。[基礎架構指南](../01-introduction/infra/README.md)

**部署名稱** - Azure 模型部署名稱。

**GPT-5** - OpenAI 最新模型，具推理控制。[模組 02](../02-prompt-engineering/README.md)

## 測試與開發 - [測試指南](TESTING.md)

**開發容器** - 容器化的開發環境。[配置](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 免費 AI 模型試驗場。[模組 00](../00-quick-start/README.md)

**記憶體內測試** - 使用記憶體儲存進行測試。

**整合測試** - 使用真實基礎架構的測試。

**Maven** - Java 建構自動化工具。

**Mockito** - Java 模擬框架。

**Spring Boot** - Java 應用框架。[模組 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：
本文件係使用 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 翻譯而成。雖然我們致力於提高翻譯準確性，但請注意，自動翻譯可能包含錯誤或不準確之處。原始文件的母語版本應視為權威來源。對於重要資訊，建議採用專業人工翻譯。我們不對因使用本翻譯所產生的任何誤解或誤譯負責。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->