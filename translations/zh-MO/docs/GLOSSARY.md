# LangChain4j 詞彙表

## 目錄

- [核心概念](../../../docs)
- [LangChain4j 組件](../../../docs)
- [AI/ML 概念](../../../docs)
- [護欄](../../../docs)
- [提示工程](../../../docs)
- [RAG（檢索增強生成）](../../../docs)
- [代理人與工具](../../../docs)
- [代理模組](../../../docs)
- [模型上下文協定（MCP）](../../../docs)
- [Azure 服務](../../../docs)
- [測試與開發](../../../docs)

快速參考本課程中使用的術語和概念。

## 核心概念

**AI 代理人** - 使用 AI 進行推理與自主行動的系統。[模組 04](../04-tools/README.md)

**鏈** - 輸出作為下一步輸入的一連串操作。

**分片** - 將文件切分成較小段落。典型大小：300-500 個標記並且有重疊。[模組 03](../03-rag/README.md)

**上下文視窗** - 模型可處理的最大標記數。GPT-5：400K 標記。

**嵌入** - 表示文本意義的數值向量。[模組 03](../03-rag/README.md)

**函數調用** - 模型生成結構化請求以呼叫外部函數。[模組 04](../04-tools/README.md)

**幻覺** - 模型生成錯誤但看似合理的資訊。

**提示** - 輸入語言模型的文本。[模組 02](../02-prompt-engineering/README.md)

**語義搜索** - 使用嵌入按意義搜索，而非關鍵字。[模組 03](../03-rag/README.md)

**有狀態 vs 無狀態** - 無狀態：無記憶。有狀態：保存對話歷史。[模組 01](../01-introduction/README.md)

**標記** - 模型處理的基本文本單位。影響成本和限制。[模組 01](../01-introduction/README.md)

**工具鏈** - 連續呼叫工具，輸出作為下次呼叫的輸入。[模組 04](../04-tools/README.md)

## LangChain4j 組件

**AiServices** - 建立型別安全的 AI 服務接口。

**OpenAiOfficialChatModel** - OpenAI 與 Azure OpenAI 模型的統一客戶端。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI Official 客戶端建立嵌入（支援 OpenAI 與 Azure OpenAI）。

**ChatModel** - 語言模型核心接口。

**ChatMemory** - 維護對話歷史。

**ContentRetriever** - 查找相關文件分片以供 RAG 使用。

**DocumentSplitter** - 將文件拆分成分片。

**EmbeddingModel** - 將文本轉換為數值向量。

**EmbeddingStore** - 存儲及檢索嵌入。

**MessageWindowChatMemory** - 維護最近訊息的滑動視窗。

**PromptTemplate** - 使用 `{{variable}}` 變量創建可重用提示。

**TextSegment** - 帶元數據的文本分片。RAG 使用。

**ToolExecutionRequest** - 表示工具執行請求。

**UserMessage / AiMessage / SystemMessage** - 對話訊息類型。

## AI/ML 概念

**少量示例學習** - 在提示中提供範例。[模組 02](../02-prompt-engineering/README.md)

**大型語言模型 (LLM)** - 基於大量文本數據訓練的 AI 模型。

**推理深度** - GPT-5 參數控制思考深度。[模組 02](../02-prompt-engineering/README.md)

**溫度** - 控制輸出隨機性。低 = 決定性，高 = 創造性。

**向量資料庫** - 專門用於嵌入的資料庫。[模組 03](../03-rag/README.md)

**零樣本學習** - 無範例下執行任務。[模組 02](../02-prompt-engineering/README.md)

## 護欄 - [模組 00](../00-quick-start/README.md)

**多層防禦** - 結合應用層護欄與提供者安全過濾器的多重安全策略。

**硬封鎖** - 提供者對嚴重內容違規拋出 HTTP 400 錯誤。

**輸入護欄** - LangChain4j 介面，在提示進入 LLM 前驗證用戶輸入。提前阻擋有害提示，節省成本與延遲。

**輸入護欄結果** - 護欄驗證回傳型別：`success()` 或 `fatal("原因")`。

**輸出護欄** - 介面，在回傳用戶前驗證 AI 回應。

**提供者安全過濾器** - AI 服務商內建內容過濾器（例如 GitHub Models），API 層面攔截違規。

**軟拒絕** - 模型禮貌拒絕回答，不拋錯。

## 提示工程 - [模組 02](../02-prompt-engineering/README.md)

**連鎖思考** - 逐步推理提高準確度。

**受限輸出** - 強制特定格式或結構。

**高度積極** - GPT-5 模式，徹底推理。

**低度積極** - GPT-5 模式，快速回答。

**多輪對話** - 保持交流語境。

**角色引導** - 透過系統訊息設定模型角色。

**自我反思** - 模型評估並改進自身輸出。

**結構化分析** - 固定評估框架。

**任務執行模式** - 計畫 → 執行 → 總結。

## RAG（檢索增強生成） - [模組 03](../03-rag/README.md)

**文檔處理流水線** - 載入 → 分片 → 嵌入 → 儲存。

**記憶體內嵌入存儲** - 非持久存儲，用於測試。

**RAG** - 結合檢索與生成，為回應提供依據。

**相似度分數** - 語義相似度度量（0-1）。

**來源參考** - 檢索內容的元數據。

## 代理人與工具 - [模組 04](../04-tools/README.md)

**@Tool 註解** - 標記 Java 方法為 AI 可呼叫工具。

**ReAct 模式** - 推理 → 行動 → 觀察 → 重複。

**會話管理** - 為不同使用者分隔上下文。

**工具** - AI 代理人可呼叫的函數。

**工具說明** - 工具用途與參數文件。

## 代理模組 - [模組 05](../05-mcp/README.md)

**@Agent 註解** - 標記介面為 AI 代理人，宣告式定義行為。

**代理監聽器** - 透過 `beforeAgentInvocation()` 與 `afterAgentInvocation()` 監控代理執行的鉤子。

**代理範圍** - 代理人共享記憶體，使用 `outputKey` 儲存輸出，供下游代理使用。

**AgenticServices** - 創建代理的工廠，含 `agentBuilder()` 與 `supervisorBuilder()`。

**條件流程** - 根據條件路由至不同專家代理。

**人類介入流程** - 在流程中加入人類檢查點以取得批准或審核內容。

**langchain4j-agentic** - 用於宣告式代理構建的 Maven 依賴（實驗性質）。

**循環流程** - 迭代執行代理直到條件達成（例如品質分數 ≥ 0.8）。

**outputKey** - 代理註解參數，指定結果存放於代理範圍。

**並行流程** - 同時執行多個代理處理獨立任務。

**回應策略** - 監督者制定最終答案的方法：LAST、SUMMARY 或 SCORED。

**序列流程** - 依序執行代理，輸出流向下一步。

**監督者代理模式** - 高階代理模式，監督者 LLM 動態決定呼叫哪些子代理。

## 模型上下文協定（MCP） - [模組 05](../05-mcp/README.md)

**langchain4j-mcp** - LangChain4j MCP 整合的 Maven 依賴。

**MCP** - 模型上下文協定：連接 AI 應用與外部工具的標準。寫一次，用到處。

**MCP 客戶端** - 連接 MCP 伺服器，查找並使用工具的應用程式。

**MCP 伺服器** - 透過 MCP 開放工具，附帶說明與參數結構的服務。

**McpToolProvider** - LangChain4j 組件，封裝 MCP 工具以供 AI 服務與代理使用。

**McpTransport** - MCP 傳輸介面。實作包含 Stdio 與 HTTP。

**Stdio 傳輸** - 透過 stdin/stdout 的本地程序傳輸。適用於檔案系統存取或命令行工具。

**StdioMcpTransport** - LangChain4j 實作，啟動 MCP 伺服器為子程序。

**工具發現** - 客戶端查詢伺服器的可用工具及說明與結構。

## Azure 服務 - [模組 01](../01-introduction/README.md)

**Azure AI 搜尋** - 具備向量功能的雲端搜尋。[模組 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 部署 Azure 資源。

**Azure OpenAI** - 微軟的企業 AI 服務。

**Bicep** - Azure 基礎設施即代碼語言。[基礎架構指南](../01-introduction/infra/README.md)

**部署名稱** - Azure 中模型部署名稱。

**GPT-5** - 最新的 OpenAI 模型，具推理控制能力。[模組 02](../02-prompt-engineering/README.md)

## 測試與開發 - [測試指南](TESTING.md)

**開發容器** - 容器化開發環境。[配置](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 免費 AI 模型遊樂場。[模組 00](../00-quick-start/README.md)

**記憶體內測試** - 使用記憶體存儲的測試。

**集成測試** - 搭配真實基礎設施的測試。

**Maven** - Java 建構自動化工具。

**Mockito** - Java 模擬框架。

**Spring Boot** - Java 應用框架。[模組 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：
本文件由人工智能翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 翻譯而成。儘管我們力求準確，請注意自動翻譯可能包含錯誤或不準確之處。原始文件的原文版本應被視為權威來源。對於重要資訊，建議採用專業人工翻譯。我們不對因使用本翻譯而引起的任何誤解或錯誤詮釋承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->