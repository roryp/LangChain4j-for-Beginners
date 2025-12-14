<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T19:55:00+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "mo"
}
-->
# LangChain4j 詞彙表

## 目錄

- [核心概念](../../../docs)
- [LangChain4j 組件](../../../docs)
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

**鏈** - 輸出作為下一步輸入的操作序列。

**分塊** - 將文件拆分成較小的部分。典型大小：300-500 個標記，帶重疊。[模組 03](../03-rag/README.md)

**上下文窗口** - 模型可處理的最大標記數。GPT-5：400K 標記。

**嵌入** - 表示文本含義的數值向量。[模組 03](../03-rag/README.md)

**函數調用** - 模型生成結構化請求以調用外部函數。[模組 04](../04-tools/README.md)

**幻覺** - 模型生成錯誤但看似合理的信息。

**提示** - 輸入給語言模型的文本。[模組 02](../02-prompt-engineering/README.md)

**語義搜索** - 使用嵌入按含義搜索，而非關鍵字。[模組 03](../03-rag/README.md)

**有狀態與無狀態** - 無狀態：無記憶。有狀態：維持對話歷史。[模組 01](../01-introduction/README.md)

**標記** - 模型處理的基本文本單位。影響成本和限制。[模組 01](../01-introduction/README.md)

**工具鏈接** - 按序執行工具，輸出用於下一次調用。[模組 04](../04-tools/README.md)

## LangChain4j 組件

**AiServices** - 創建類型安全的 AI 服務接口。

**OpenAiOfficialChatModel** - OpenAI 與 Azure OpenAI 模型的統一客戶端。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI 官方客戶端創建嵌入（支持 OpenAI 和 Azure OpenAI）。

**ChatModel** - 語言模型的核心接口。

**ChatMemory** - 維持對話歷史。

**ContentRetriever** - 為 RAG 查找相關文件塊。

**DocumentSplitter** - 將文件拆分成塊。

**EmbeddingModel** - 將文本轉換為數值向量。

**EmbeddingStore** - 存儲和檢索嵌入。

**MessageWindowChatMemory** - 維持最近消息的滑動窗口。

**PromptTemplate** - 使用 `{{variable}}` 佔位符創建可重用提示。

**TextSegment** - 帶元數據的文本塊。用於 RAG。

**ToolExecutionRequest** - 表示工具執行請求。

**UserMessage / AiMessage / SystemMessage** - 對話消息類型。

## AI/ML 概念

**少量示例學習** - 在提示中提供示例。[模組 02](../02-prompt-engineering/README.md)

**大型語言模型（LLM）** - 在大量文本數據上訓練的 AI 模型。

**推理努力** - GPT-5 控制思考深度的參數。[模組 02](../02-prompt-engineering/README.md)

**溫度** - 控制輸出隨機性。低=確定性，高=創造性。

**向量數據庫** - 專門用於嵌入的數據庫。[模組 03](../03-rag/README.md)

**零示例學習** - 無需示例即可執行任務。[模組 02](../02-prompt-engineering/README.md)

## 提示工程 - [模組 02](../02-prompt-engineering/README.md)

**思維鏈** - 逐步推理以提高準確性。

**受限輸出** - 強制特定格式或結構。

**高熱情** - GPT-5 用於徹底推理的模式。

**低熱情** - GPT-5 用於快速回答的模式。

**多輪對話** - 維持多次交流的上下文。

**基於角色的提示** - 通過系統消息設置模型角色。

**自我反思** - 模型評估並改進其輸出。

**結構化分析** - 固定的評估框架。

**任務執行模式** - 計劃 → 執行 → 總結。

## RAG（檢索增強生成） - [模組 03](../03-rag/README.md)

**文件處理管線** - 加載 → 分塊 → 嵌入 → 存儲。

**內存嵌入存儲** - 用於測試的非持久存儲。

**RAG** - 結合檢索與生成以提供依據的回答。

**相似度分數** - 語義相似度的度量（0-1）。

**來源參考** - 檢索內容的元數據。

## 代理與工具 - [模組 04](../04-tools/README.md)

**@Tool 註解** - 標記 Java 方法為 AI 可調用工具。

**ReAct 模式** - 推理 → 行動 → 觀察 → 重複。

**會話管理** - 為不同用戶分離上下文。

**工具** - AI 代理可調用的函數。

**工具描述** - 工具用途和參數的文檔。

## 模型上下文協議（MCP） - [模組 05](../05-mcp/README.md)

**Docker 傳輸** - MCP 伺服器運行於 Docker 容器中。

**MCP** - 連接 AI 應用與外部工具的標準。

**MCP 客戶端** - 連接 MCP 伺服器的應用。

**MCP 伺服器** - 通過 MCP 暴露工具的服務。

**伺服器發送事件（SSE）** - 伺服器通過 HTTP 向客戶端流式傳輸。

**Stdio 傳輸** - 伺服器作為子進程通過 stdin/stdout 通信。

**可流式 HTTP 傳輸** - 使用 SSE 的 HTTP 實時通信。

**工具發現** - 客戶端查詢伺服器可用工具。

## Azure 服務 - [模組 01](../01-introduction/README.md)

**Azure AI 搜索** - 具備向量功能的雲端搜索。[模組 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 部署 Azure 資源。

**Azure OpenAI** - 微軟的企業 AI 服務。

**Bicep** - Azure 基礎設施即代碼語言。[基礎設施指南](../01-introduction/infra/README.md)

**部署名稱** - Azure 中模型部署的名稱。

**GPT-5** - 最新的 OpenAI 模型，具備推理控制。[模組 02](../02-prompt-engineering/README.md)

## 測試與開發 - [測試指南](TESTING.md)

**開發容器** - 容器化開發環境。[配置](../../../.devcontainer/devcontainer.json)

**GitHub 模型** - 免費 AI 模型遊樂場。[模組 00](../00-quick-start/README.md)

**內存測試** - 使用內存存儲進行測試。

**集成測試** - 使用真實基礎設施進行測試。

**Maven** - Java 構建自動化工具。

**Mockito** - Java 模擬框架。

**Spring Boot** - Java 應用框架。[模組 01](../01-introduction/README.md)

**Testcontainers** - 測試中的 Docker 容器。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：  
本文件係使用人工智能翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們致力於確保準確性，但請注意，自動翻譯可能包含錯誤或不準確之處。原始文件之母語版本應視為權威來源。對於重要資訊，建議採用專業人工翻譯。我們不對因使用本翻譯而引起之任何誤解或誤釋承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->