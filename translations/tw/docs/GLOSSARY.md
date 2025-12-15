<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T19:56:20+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "tw"
}
-->
# LangChain4j 詞彙表

## 目錄

- [核心概念](../../../docs)
- [LangChain4j 元件](../../../docs)
- [AI/ML 概念](../../../docs)
- [提示工程](../../../docs)
- [RAG（檢索增強生成）](../../../docs)
- [代理與工具](../../../docs)
- [模型上下文協議（MCP）](../../../docs)
- [Azure 服務](../../../docs)
- [測試與開發](../../../docs)

課程中使用的術語和概念快速參考。

## 核心概念

**AI 代理** - 使用 AI 進行推理並自主行動的系統。[模組 04](../04-tools/README.md)

**鏈（Chain）** - 輸出作為下一步輸入的操作序列。

**分塊（Chunking）** - 將文件拆分成較小片段。典型大小：300-500 個標記，帶重疊。[模組 03](../03-rag/README.md)

**上下文視窗** - 模型可處理的最大標記數。GPT-5：400K 標記。

**嵌入（Embeddings）** - 表示文本意義的數值向量。[模組 03](../03-rag/README.md)

**函數調用** - 模型生成結構化請求以調用外部函數。[模組 04](../04-tools/README.md)

**幻覺（Hallucination）** - 模型生成錯誤但看似合理的信息。

**提示（Prompt）** - 輸入給語言模型的文本。[模組 02](../02-prompt-engineering/README.md)

**語義搜索** - 使用嵌入按意義搜索，而非關鍵字。[模組 03](../03-rag/README.md)

**有狀態與無狀態** - 無狀態：無記憶。有狀態：維持對話歷史。[模組 01](../01-introduction/README.md)

**標記（Tokens）** - 模型處理的基本文本單位。影響成本與限制。[模組 01](../01-introduction/README.md)

**工具鏈接** - 依序執行工具，輸出用於下一次調用。[模組 04](../04-tools/README.md)

## LangChain4j 元件

**AiServices** - 建立類型安全的 AI 服務介面。

**OpenAiOfficialChatModel** - OpenAI 與 Azure OpenAI 模型的統一客戶端。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI 官方客戶端建立嵌入（支援 OpenAI 與 Azure OpenAI）。

**ChatModel** - 語言模型的核心介面。

**ChatMemory** - 維持對話歷史。

**ContentRetriever** - 尋找 RAG 相關的文件片段。

**DocumentSplitter** - 將文件拆分成片段。

**EmbeddingModel** - 將文本轉換為數值向量。

**EmbeddingStore** - 儲存與檢索嵌入。

**MessageWindowChatMemory** - 維持最近訊息的滑動視窗。

**PromptTemplate** - 使用 `{{variable}}` 佔位符建立可重用提示。

**TextSegment** - 帶有元資料的文本片段。用於 RAG。

**ToolExecutionRequest** - 代表工具執行請求。

**UserMessage / AiMessage / SystemMessage** - 對話訊息類型。

## AI/ML 概念

**少量示例學習（Few-Shot Learning）** - 在提示中提供範例。[模組 02](../02-prompt-engineering/README.md)

**大型語言模型（LLM）** - 在大量文本資料上訓練的 AI 模型。

**推理努力（Reasoning Effort）** - GPT-5 控制思考深度的參數。[模組 02](../02-prompt-engineering/README.md)

**溫度（Temperature）** - 控制輸出隨機性。低＝確定性，高＝創造性。

**向量資料庫** - 專門用於嵌入的資料庫。[模組 03](../03-rag/README.md)

**零示例學習（Zero-Shot Learning）** - 無範例執行任務。[模組 02](../02-prompt-engineering/README.md)

## 提示工程 - [模組 02](../02-prompt-engineering/README.md)

**思維鏈（Chain-of-Thought）** - 逐步推理以提升準確度。

**受限輸出** - 強制特定格式或結構。

**高積極性** - GPT-5 用於徹底推理的模式。

**低積極性** - GPT-5 用於快速回答的模式。

**多輪對話** - 維持跨輪交換的上下文。

**角色基礎提示** - 透過系統訊息設定模型角色。

**自我反思** - 模型評估並改進其輸出。

**結構化分析** - 固定的評估框架。

**任務執行模式** - 計劃 → 執行 → 總結。

## RAG（檢索增強生成） - [模組 03](../03-rag/README.md)

**文件處理流程** - 載入 → 分塊 → 嵌入 → 儲存。

**記憶體內嵌入儲存** - 用於測試的非持久性儲存。

**RAG** - 結合檢索與生成以提供依據的回答。

**相似度分數** - 語義相似度的度量（0-1）。

**來源參考** - 檢索內容的元資料。

## 代理與工具 - [模組 04](../04-tools/README.md)

**@Tool 註解** - 標記 Java 方法為 AI 可調用工具。

**ReAct 模式** - 推理 → 行動 → 觀察 → 重複。

**會話管理** - 為不同用戶分離上下文。

**工具** - AI 代理可調用的功能。

**工具說明** - 工具用途與參數的文件。

## 模型上下文協議（MCP） - [模組 05](../05-mcp/README.md)

**Docker 傳輸** - MCP 伺服器運行於 Docker 容器中。

**MCP** - 連接 AI 應用與外部工具的標準。

**MCP 客戶端** - 連接 MCP 伺服器的應用程式。

**MCP 伺服器** - 透過 MCP 暴露工具的服務。

**伺服器發送事件（SSE）** - 伺服器透過 HTTP 向客戶端串流。

**Stdio 傳輸** - 伺服器作為子程序透過 stdin/stdout。

**可串流 HTTP 傳輸** - 使用 SSE 的 HTTP 實時通訊。

**工具發現** - 客戶端查詢伺服器可用工具。

## Azure 服務 - [模組 01](../01-introduction/README.md)

**Azure AI 搜尋** - 具向量功能的雲端搜尋。[模組 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 部署 Azure 資源。

**Azure OpenAI** - 微軟的企業 AI 服務。

**Bicep** - Azure 基礎架構即程式碼語言。[基礎架構指南](../01-introduction/infra/README.md)

**部署名稱** - Azure 中模型部署的名稱。

**GPT-5** - 最新 OpenAI 模型，具推理控制功能。[模組 02](../02-prompt-engineering/README.md)

## 測試與開發 - [測試指南](TESTING.md)

**開發容器** - 容器化開發環境。[設定](../../../.devcontainer/devcontainer.json)

**GitHub 模型** - 免費 AI 模型遊樂場。[模組 00](../00-quick-start/README.md)

**記憶體內測試** - 使用記憶體儲存進行測試。

**整合測試** - 使用真實基礎架構的測試。

**Maven** - Java 建置自動化工具。

**Mockito** - Java 模擬框架。

**Spring Boot** - Java 應用框架。[模組 01](../01-introduction/README.md)

**Testcontainers** - 測試中的 Docker 容器。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：  
本文件係使用 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們致力於確保翻譯的準確性，但請注意，自動翻譯可能包含錯誤或不準確之處。原始文件的母語版本應視為權威來源。對於重要資訊，建議採用專業人工翻譯。我們不對因使用本翻譯而產生的任何誤解或誤譯負責。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->