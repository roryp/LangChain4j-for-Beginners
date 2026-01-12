<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T07:51:10+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "hk"
}
-->
# LangChain4j 詞彙表

## 目錄

- [核心概念](../../../docs)
- [LangChain4j 元件](../../../docs)
- [AI/ML 概念](../../../docs)
- [護欄](../../../docs)
- [提示工程](../../../docs)
- [RAG（檢索增強生成）](../../../docs)
- [代理人與工具](../../../docs)
- [代理模組](../../../docs)
- [模型上下文協議（MCP）](../../../docs)
- [Azure 服務](../../../docs)
- [測試與開發](../../../docs)

快速參考課程中使用的術語和概念。

## 核心概念

**AI Agent** - 使用 AI 自主推理和行動的系統。[Module 04](../04-tools/README.md)

**Chain** - 輸出作為下一步輸入的一連串操作。

**Chunking** - 將文件拆分成較小片段。典型為 300-500 代幣並帶重疊。[Module 03](../03-rag/README.md)

**Context Window** - 模型能處理的最大代幣數。GPT-5：400K 代幣。

**Embeddings** - 表示文本意義的數值向量。[Module 03](../03-rag/README.md)

**Function Calling** - 模型生成結構化請求以調用外部函數。[Module 04](../04-tools/README.md)

**Hallucination** - 模型生成錯誤但看似合理的資訊。

**Prompt** - 輸入給語言模型的文本。[Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - 利用 embeddings 按語義搜尋，而非關鍵字。[Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless：無記憶。Stateful：維持對話歷史。[Module 01](../01-introduction/README.md)

**Tokens** - 模型處理的基本文本單位。影響成本和限制。[Module 01](../01-introduction/README.md)

**Tool Chaining** - 工具按序執行，輸出用於下一次調用。[Module 04](../04-tools/README.md)

## LangChain4j 元件

**AiServices** - 建立類型安全的 AI 服務介面。

**OpenAiOfficialChatModel** - OpenAI 與 Azure OpenAI 模型的統一客戶端。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI Official 客戶端創建 embeddings（支援 OpenAI 和 Azure OpenAI）。

**ChatModel** - 語言模型核心介面。

**ChatMemory** - 維護對話歷史。

**ContentRetriever** - 尋找與 RAG 相關的文件片段。

**DocumentSplitter** - 將文件拆分成多個片段。

**EmbeddingModel** - 將文本轉換為數值向量。

**EmbeddingStore** - 儲存並檢索 embeddings。

**MessageWindowChatMemory** - 維持最近消息的滑動視窗。

**PromptTemplate** - 使用 `{{variable}}` 佔位符建立可重用提示。

**TextSegment** - 附帶元資料的文本片段，用於 RAG。

**ToolExecutionRequest** - 表示工具執行請求。

**UserMessage / AiMessage / SystemMessage** - 對話訊息類型。

## AI/ML 概念

**Few-Shot Learning** - 在提示中提供範例。[Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - 以龐大文本資料訓練的 AI 模型。

**Reasoning Effort** - GPT-5 控制思考深度的參數。[Module 02](../02-prompt-engineering/README.md)

**Temperature** - 控制輸出隨機性。低值=決定性，高值=創造性。

**Vector Database** - 專門用於 embeddings 的資料庫。[Module 03](../03-rag/README.md)

**Zero-Shot Learning** - 不依賴範例執行任務。[Module 02](../02-prompt-engineering/README.md)

## 護欄 - [Module 00](../00-quick-start/README.md)

**Defense in Depth** - 多層安全策略，結合應用層護欄與供應商安全過濾器。

**Hard Block** - 嚴重內容違規時，供應商回傳 HTTP 400 錯誤。

**InputGuardrail** - LangChain4j 介面，在提示語進入 LLM 前驗證使用者輸入。提前阻擋有害提示以節省成本和延遲。

**InputGuardrailResult** - 護欄驗證的返回類型：`success()` 或 `fatal("原因")`。

**OutputGuardrail** - 驗證 AI 回應是否符合規範後再回傳使用者的介面。

**Provider Safety Filters** - AI 供應商現成的內容過濾器 (例如 GitHub Models)，在 API 層面攔截違規。

**Soft Refusal** - 模型禮貌拒絕回答，無錯誤拋出。

## 提示工程 - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - 逐步推理以提高準確度。

**Constrained Output** - 強制特定格式或結構。

**High Eagerness** - GPT-5 用於深入推理的模式。

**Low Eagerness** - GPT-5 用於快速回應的模式。

**Multi-Turn Conversation** - 維持多輪對話上下文。

**Role-Based Prompting** - 透過系統訊息設定模型角色。

**Self-Reflection** - 模型自我評估並改進輸出。

**Structured Analysis** - 固定評估框架。

**Task Execution Pattern** - 計劃 → 執行 → 總結。

## RAG（檢索增強生成）- [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - 載入 → 拆分 → 嵌入 → 存儲。

**In-Memory Embedding Store** - 非持久性存儲，用於測試。

**RAG** - 結合檢索與生成，為回答提供依據。

**Similarity Score** - 語義相似度量（0-1）。

**Source Reference** - 取得內容的元資料。

## 代理人與工具 - [Module 04](../04-tools/README.md)

**@Tool Annotation** - 標記 Java 方法為 AI 可調用工具。

**ReAct Pattern** - 推理 → 行動 → 觀察 → 重複。

**Session Management** - 不同用戶維持獨立上下文。

**Tool** - AI 代理人可調用的函數。

**Tool Description** - 工具用途與參數說明文檔。

## 代理模組 - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - 以宣告式方式標記介面為 AI 代理人。

**Agent Listener** - 監控代理執行的鉤子，包含 `beforeAgentInvocation()` 與 `afterAgentInvocation()`。

**Agentic Scope** - 代理人的共享記憶空間，使用 `outputKey` 儲存輸出供下游代理人使用。

**AgenticServices** - 創建代理人的工廠，包含 `agentBuilder()` 與 `supervisorBuilder()`。

**Conditional Workflow** - 根據條件路由至不同專家代理人。

**Human-in-the-Loop** - 加入人工審核點的流程模式。

**langchain4j-agentic** - 用於宣告式代理構建的 Maven 依賴（實驗性）。

**Loop Workflow** - 持續迭代代理執行直到滿足條件（如品質分數≥0.8）。

**outputKey** - 代理標註參數，指定結果於 Agentic Scope 中的存放位置。

**Parallel Workflow** - 同時執行多個獨立任務的代理人。

**Response Strategy** - 主管代理人產生最終答案的策略：LAST、SUMMARY 或 SCORED。

**Sequential Workflow** - 依序執行代理人，輸出流向下一步。

**Supervisor Agent Pattern** - 進階代理模式，主管 LLM 動態決定要啟用哪些子代理人。

## 模型上下文協議（MCP）- [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - LangChain4j 中的 MCP 整合 Maven 依賴。

**MCP** - 模型上下文協議：連接 AI 應用和外部工具的標準。一次構建，到處使用。

**MCP Client** - 連接 MCP 伺服器，發現並使用工具的應用。

**MCP Server** - 透過 MCP 暴露工具的服務，提供清晰描述與參數結構。

**McpToolProvider** - LangChain4j 元件，封裝 MCP 工具供 AI 服務和代理使用。

**McpTransport** - MCP 通訊介面。實作包括 Stdio 和 HTTP。

**Stdio Transport** - 透過 stdin/stdout 的本地程序通訊。適用於檔案系統存取或命令列工具。

**StdioMcpTransport** - LangChain4j 實作，執行 MCP 伺服器作為子進程。

**Tool Discovery** - 客戶端查詢伺服器以取得可用工具及其描述和結構。

## Azure 服務 - [Module 01](../01-introduction/README.md)

**Azure AI Search** - 具向量能力的雲端搜尋。[Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 部署 Azure 資源工具。

**Azure OpenAI** - 微軟企業級 AI 服務。

**Bicep** - Azure 基礎設施即程式碼語言。[Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azure 中模型部署的名稱。

**GPT-5** - 具推理控制功能的最新 OpenAI 模型。[Module 02](../02-prompt-engineering/README.md)

## 測試與開發 - [測試指南](TESTING.md)

**Dev Container** - 容器化開發環境。[Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 免費的 AI 模型試驗場。[Module 00](../00-quick-start/README.md)

**In-Memory Testing** - 使用記憶體存儲的測試。

**Integration Testing** - 使用真實基礎設施的測試。

**Maven** - Java 建置自動化工具。

**Mockito** - Java 偽造框架。

**Spring Boot** - Java 應用框架。[Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：
本文件由 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們力求準確，但請注意，自動翻譯可能包含錯誤或不準確之處。原始語言文件應被視為權威來源。對於重要資訊，建議採用專業人工翻譯。我們不承擔因使用本翻譯而引起的任何誤解或誤釋的責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->