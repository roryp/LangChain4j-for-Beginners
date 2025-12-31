<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T21:37:16+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "hk"
}
-->
# LangChain4j 詞彙表

## 目錄

- [核心概念](../../../docs)
- [LangChain4j 組件](../../../docs)
- [AI/ML 概念](../../../docs)
- [提示工程](../../../docs)
- [RAG (檢索增強生成)](../../../docs)
- [代理人與工具](../../../docs)
- [模型上下文協議 (MCP)](../../../docs)
- [Azure 服務](../../../docs)
- [測試與開發](../../../docs)

課程中使用術語與概念的快速參考。

## Core Concepts

**AI Agent** - 使用 AI 進行推理並能自主採取行動的系統。 [模組 04](../04-tools/README.md)

**Chain** - 一連串的操作，輸出會作為下一步的輸入。

**Chunking** - 將文件拆分為較小的片段。典型大小：300-500 tokens，帶重疊。 [模組 03](../03-rag/README.md)

**Context Window** - 模型能處理的最大 token 數量。GPT-5：400K tokens。

**Embeddings** - 表示文字語意的數值向量。 [模組 03](../03-rag/README.md)

**Function Calling** - 模型生成結構化請求以調用外部函數。 [模組 04](../04-tools/README.md)

**Hallucination** - 模型生成不正確但看似合理的資訊。

**Prompt** - 提供給語言模型的文字輸入。 [模組 02](../02-prompt-engineering/README.md)

**Semantic Search** - 使用嵌入向量按語意進行搜尋，而非關鍵字。 [模組 03](../03-rag/README.md)

**Stateful vs Stateless** - 無狀態：沒有記憶。有狀態：維持對話歷史。 [模組 01](../01-introduction/README.md)

**Tokens** - 模型處理的基本文字單位。影響成本與限制。 [模組 01](../01-introduction/README.md)

**Tool Chaining** - 工具的順序執行，輸出會用於下一次呼叫。 [模組 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - 建立型別安全的 AI 服務介面。

**OpenAiOfficialChatModel** - 統一的客戶端，用於 OpenAI 與 Azure OpenAI 模型。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI Official 客戶端建立嵌入向量（支援 OpenAI 與 Azure OpenAI）。

**ChatModel** - 語言模型的核心介面。

**ChatMemory** - 維護對話歷史。

**ContentRetriever** - 為 RAG 找出相關的文件片段。

**DocumentSplitter** - 將文件拆分成片段。

**EmbeddingModel** - 將文字轉換為數值向量。

**EmbeddingStore** - 儲存與檢索嵌入向量。

**MessageWindowChatMemory** - 維持最近訊息的滑動視窗。

**PromptTemplate** - 使用 `{{variable}}` 佔位符建立可重用的提示。

**TextSegment** - 含有 Metadata 的文字片段。用於 RAG。

**ToolExecutionRequest** - 代表工具執行請求。

**UserMessage / AiMessage / SystemMessage** - 對話訊息類型。

## AI/ML Concepts

**Few-Shot Learning** - 在提示中提供範例。 [模組 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - 在大量文本資料上訓練的 AI 模型。

**Reasoning Effort** - 控制 GPT-5 思考深度的參數。 [模組 02](../02-prompt-engineering/README.md)

**Temperature** - 控制輸出隨機性的參數。低＝確定性，高＝創意。

**Vector Database** - 專門用於嵌入向量的資料庫。 [模組 03](../03-rag/README.md)

**Zero-Shot Learning** - 在沒範例的情況下執行任務。 [模組 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [模組 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - 逐步推理以提升準確性。

**Constrained Output** - 強制輸出符合特定格式或結構。

**High Eagerness** - GPT-5 用於深入推理的模式。

**Low Eagerness** - GPT-5 用於快速回答的模式。

**Multi-Turn Conversation** - 在多輪互動中維持上下文。

**Role-Based Prompting** - 透過系統訊息設定模型角色或人格。

**Self-Reflection** - 模型自我評估並改善其輸出。

**Structured Analysis** - 固定的評估框架。

**Task Execution Pattern** - 計劃 → 執行 → 彙總。

## RAG (Retrieval-Augmented Generation) - [模組 03](../03-rag/README.md)

**Document Processing Pipeline** - 載入 → 切割 → 嵌入 → 儲存。

**In-Memory Embedding Store** - 用於測試的非永久性儲存。

**RAG** - 將檢索與生成結合以使回應有依據。

**Similarity Score** - 介於 0 到 1 的語意相似度量。

**Source Reference** - 關於檢索內容的來源 Metadata。

## Agents and Tools - [模組 04](../04-tools/README.md)

**@Tool Annotation** - 標記 Java 方法為可由 AI 呼叫的工具。

**ReAct Pattern** - 推理 → 行動 → 觀察 → 重複。

**Session Management** - 為不同使用者分隔的上下文管理。

**Tool** - AI 代理可以呼叫的函式。

**Tool Description** - 工具用途與參數的文件說明。

## Model Context Protocol (MCP) - [模組 05](../05-mcp/README.md)

**MCP** - 將 AI 應用程式連接到外部工具的標準。

**MCP Client** - 連接到 MCP 伺服器的應用程式。

**MCP Server** - 透過 MCP 暴露工具的服務。

**Stdio Transport** - 將伺服器作為子程序透過 stdin/stdout 運行。

**Tool Discovery** - 用戶端查詢伺服器可用工具的機制。

## Azure Services - [模組 01](../01-introduction/README.md)

**Azure AI Search** - 具有向量功能的雲端搜尋服務。 [模組 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 用於部署 Azure 資源的工具。

**Azure OpenAI** - 微軟的企業級 AI 服務。

**Bicep** - 用於 Azure 的基礎設施即程式碼語言。 [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - 在 Azure 中模型部署的名稱。

**GPT-5** - 最新的 OpenAI 模型，具有推理控制功能。 [模組 02](../02-prompt-engineering/README.md)

## Testing and Development - [測試指南](TESTING.md)

**Dev Container** - 容器化的開發環境。 [設定](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 免費的 AI 模型試驗場。 [模組 00](../00-quick-start/README.md)

**In-Memory Testing** - 使用記憶體儲存進行測試。

**Integration Testing** - 使用實際基礎設施的測試。

**Maven** - Java 的建構自動化工具。

**Mockito** - Java 的模擬框架。

**Spring Boot** - Java 應用程式框架。 [模組 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免責聲明：
本文件由 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們力求準確，但請注意，自動翻譯可能包含錯誤或不準確之處。原文應視為具權威性的版本。對於重要資訊，建議採用專業人工翻譯。我們不會就因使用本翻譯而導致的任何誤解或曲解承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->