<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T21:22:49+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "mo"
}
-->
# LangChain4j 詞彙表

## 目錄

- [核心概念](../../../docs)
- [LangChain4j 元件](../../../docs)
- [AI/ML 概念](../../../docs)
- [提示工程](../../../docs)
- [RAG (檢索增強生成)](../../../docs)
- [代理與工具](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure 服務](../../../docs)
- [測試與開發](../../../docs)

本課程中使用之術語與概念快速參考。

## 核心概念

**AI Agent** - 使用 AI 進行推理並自主執行動作的系統。 [模組 04](../04-tools/README.md)

**Chain** - 一連串的操作，輸出作為下一步的輸入。

**Chunking** - 將文件拆成較小的片段。典型：300–500 代幣並含重疊。 [模組 03](../03-rag/README.md)

**Context Window** - 模型可處理的最大代幣數。GPT-5：400K 代幣。

**Embeddings** - 表示文字意義的數值向量。 [模組 03](../03-rag/README.md)

**Function Calling** - 模型產生結構化的請求以呼叫外部函式。 [模組 04](../04-tools/README.md)

**Hallucination** - 模型產生不正確但看似合理的資訊。

**Prompt** - 輸入給語言模型的文字。 [模組 02](../02-prompt-engineering/README.md)

**Semantic Search** - 使用 embeddings 以語意搜尋，而非關鍵字。 [模組 03](../03-rag/README.md)

**Stateful vs Stateless** - 無狀態：沒有記憶。有狀態：維護會話歷史。 [模組 01](../01-introduction/README.md)

**Tokens** - 模型處理的基本文字單位。影響成本與限制。 [模組 01](../01-introduction/README.md)

**Tool Chaining** - 工具的順序執行，其輸出用於下一次呼叫。 [模組 04](../04-tools/README.md)

## LangChain4j 元件

**AiServices** - 建立型別安全的 AI 服務介面。

**OpenAiOfficialChatModel** - 統一的 OpenAI 與 Azure OpenAI 模型客戶端。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI Official 客戶端建立 embeddings（支援 OpenAI 與 Azure OpenAI）。

**ChatModel** - 語言模型的核心介面。

**ChatMemory** - 維護會話歷史。

**ContentRetriever** - 為 RAG 找出相關的文件片段。

**DocumentSplitter** - 將文件拆成片段。

**EmbeddingModel** - 將文字轉換為數值向量。

**EmbeddingStore** - 儲存與擷取 embeddings。

**MessageWindowChatMemory** - 維持最近訊息的滑動視窗。

**PromptTemplate** - 使用 `{{variable}}` 佔位符建立可重用的提示。

**TextSegment** - 帶有 metadata 的文字片段。用於 RAG。

**ToolExecutionRequest** - 表示工具執行請求。

**UserMessage / AiMessage / SystemMessage** - 會話訊息類型。

## AI/ML 概念

**Few-Shot Learning** - 在提示中提供範例。 [模組 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - 在大量文本資料上訓練的 AI 模型。

**Reasoning Effort** - 控制思考深度的 GPT-5 參數。 [模組 02](../02-prompt-engineering/README.md)

**Temperature** - 控制輸出隨機性。低＝確定性，高＝具創意。

**Vector Database** - 專門儲存 embeddings 的資料庫。 [模組 03](../03-rag/README.md)

**Zero-Shot Learning** - 在沒有範例情況下執行任務。 [模組 02](../02-prompt-engineering/README.md)

## 提示工程 - [模組 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - 逐步推理以提升準確性。

**Constrained Output** - 強制特定格式或結構的輸出。

**High Eagerness** - GPT-5 用於徹底推理的模式。

**Low Eagerness** - GPT-5 用於快速回答的模式。

**Multi-Turn Conversation** - 在多次互動中維持上下文。

**Role-Based Prompting** - 透過系統訊息設定模型角色。

**Self-Reflection** - 模型評估並改善自己的輸出。

**Structured Analysis** - 固定的評估框架。

**Task Execution Pattern** - 計畫 → 執行 → 摘要。

## RAG (檢索增強生成) - [模組 03](../03-rag/README.md)

**Document Processing Pipeline** - 載入 → 切分 → 建立 embedding → 儲存。

**In-Memory Embedding Store** - 用於測試的非永久性儲存。

**RAG** - 結合檢索與生成以使回答有依據。

**Similarity Score** - 量度語意相似度的指標（0–1）。

**Source Reference** - 關於擷取內容的來源 metadata。

## 代理與工具 - [模組 04](../04-tools/README.md)

**@Tool Annotation** - 將 Java 方法標記為可由 AI 呼叫的工具。

**ReAct Pattern** - 推理 → 行動 → 觀察 → 重複。

**Session Management** - 為不同使用者分離情境。

**Tool** - AI 代理可以呼叫的函式。

**Tool Description** - 闡述工具目的與參數的文件說明。

## Model Context Protocol (MCP) - [模組 05](../05-mcp/README.md)

**MCP** - 連接 AI 應用程式到外部工具的標準。

**MCP Client** - 連接到 MCP 伺服器的應用程式。

**MCP Server** - 以 MCP 暴露工具的服務。

**Stdio Transport** - 透過 stdin/stdout 將伺服器當作子程序執行。

**Tool Discovery** - 用戶端查詢伺服器可用工具的機制。

## Azure 服務 - [模組 01](../01-introduction/README.md)

**Azure AI Search** - 具向量能力的雲端搜尋。 [模組 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 部署 Azure 資源的工具。

**Azure OpenAI** - Microsoft 的企業級 AI 服務。

**Bicep** - Azure 的基礎設施即程式語言。 [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - 在 Azure 中模型部署的名稱。

**GPT-5** - 最新的 OpenAI 模型，具備推理控制。 [模組 02](../02-prompt-engineering/README.md)

## 測試與開發 - [測試指南](TESTING.md)

**Dev Container** - 容器化的開發環境。 [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 免費的 AI 模型試驗平台。 [模組 00](../00-quick-start/README.md)

**In-Memory Testing** - 使用記憶體內儲存進行的測試。

**Integration Testing** - 使用真實基礎設施的測試。

**Maven** - Java 的建置自動化工具。

**Mockito** - Java 的模擬框架。

**Spring Boot** - Java 應用框架。 [模組 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免責聲明：
本文件已使用 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。儘管我們力求準確，但請注意自動翻譯可能包含錯誤或不準確之處。原始語言的文件應視為具權威性的來源。對於重要資訊，建議採用專業人工翻譯。我們對於因使用此翻譯而引致的任何誤解或錯誤詮釋概不負責。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->