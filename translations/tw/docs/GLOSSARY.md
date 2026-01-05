<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T21:49:10+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "tw"
}
-->
# LangChain4j 詞彙表

## Table of Contents

- [核心概念](../../../docs)
- [LangChain4j 元件](../../../docs)
- [AI/ML 概念](../../../docs)
- [Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)](#prompt-engineering---module-02)
- [RAG (檢索增強生成)](../../../docs)
- [Agents and Tools - [Module 04](../04-tools/README.md)](#agents-and-tools---module-04)
- [Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)](#model-context-protocol-mcp---module-05)
- [Azure 服務 - [Module 01](../01-introduction/README.md)](#azure-services---module-01)
- [測試與開發 - [Testing Guide](TESTING.md)](#testing-and-development---testing-guide)

本課程中使用的術語與概念快速參考。

## Core Concepts

**AI Agent** - 使用 AI 進行推理並自主行動的系統。 [Module 04](../04-tools/README.md)

**Chain** - 一連串的操作，輸出作為下一步的輸入。

**Chunking** - 將文件切分成較小的片段。典型大小：300–500 個 token，並有重疊。 [Module 03](../03-rag/README.md)

**Context Window** - 模型可處理的最大 token 數量。GPT-5：400K token。

**Embeddings** - 代表文字意義的數值向量。 [Module 03](../03-rag/README.md)

**Function Calling** - 模型產生結構化請求以呼叫外部函式。 [Module 04](../04-tools/README.md)

**Hallucination** - 模型生成不正確但看似合理的資訊。

**Prompt** - 輸入給語言模型的文字。 [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - 使用 embeddings 依語意搜尋，而非關鍵字。 [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - 無狀態：沒有記憶。有狀態：維持對話歷史。 [Module 01](../01-introduction/README.md)

**Tokens** - 模型處理的基本文字單位。影響成本與限制。 [Module 01](../01-introduction/README.md)

**Tool Chaining** - 工具的序列化執行，輸出用於下一次呼叫。 [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - 建立型別安全的 AI 服務介面。

**OpenAiOfficialChatModel** - 統一的 OpenAI 與 Azure OpenAI 模型用戶端。

**OpenAiOfficialEmbeddingModel** - 使用 OpenAI Official 用戶端建立 embeddings（支援 OpenAI 與 Azure OpenAI）。

**ChatModel** - 語言模型的核心介面。

**ChatMemory** - 維持對話歷史。

**ContentRetriever** - 為 RAG 找出相關的文件片段。

**DocumentSplitter** - 將文件拆分成片段。

**EmbeddingModel** - 將文字轉換為數值向量。

**EmbeddingStore** - 儲存與檢索 embeddings。

**MessageWindowChatMemory** - 維持最近訊息的滑動視窗記憶。

**PromptTemplate** - 使用 `{{variable}}` 佔位符建立可重用的 prompt。

**TextSegment** - 帶有 metadata 的文字片段。用於 RAG。

**ToolExecutionRequest** - 代表工具執行請求。

**UserMessage / AiMessage / SystemMessage** - 對話訊息類型。

## AI/ML Concepts

**Few-Shot Learning** - 在 prompt 中提供示例。 [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - 在大量文字資料上訓練的 AI 模型。

**Reasoning Effort** - GPT-5 控制思考深度的參數。 [Module 02](../02-prompt-engineering/README.md)

**Temperature** - 控制輸出隨機性。低＝較確定性，高＝較有創意。

**Vector Database** - 專門用來儲存 embeddings 的資料庫。 [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - 在沒有示例的情況下執行任務。 [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - 逐步推理以提升準確性。

**Constrained Output** - 強制輸出為特定格式或結構。

**High Eagerness** - GPT-5 用於深入推理的模式。

**Low Eagerness** - GPT-5 用於快速回應的模式。

**Multi-Turn Conversation** - 在多次交換中維持上下文。

**Role-Based Prompting** - 透過 system 訊息設定模型角色或人設。

**Self-Reflection** - 模型自我評估並改進其輸出。

**Structured Analysis** - 固定的評估框架。

**Task Execution Pattern** - 計畫 → 執行 → 摘要。

## RAG (檢索增強生成) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - 載入 → 切片 → 產生 embeddings → 儲存。

**In-Memory Embedding Store** - 用於測試的非持久性儲存。

**RAG** - 結合檢索與生成以為回應提供依據。

**Similarity Score** - 量測語意相似度的指標（0–1）。

**Source Reference** - 有關擷取內容的來源 metadata。

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - 標記 Java 方法為可由 AI 呼叫的工具。

**ReAct Pattern** - 推理 → 行動 → 觀察 → 重複。

**Session Management** - 為不同使用者維持獨立的上下文。

**Tool** - AI agent 可以呼叫的函式。

**Tool Description** - 針對工具目的與參數的文件說明。

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - 連接 AI 應用程式與外部工具的標準。

**MCP Client** - 連接至 MCP 伺服器的應用程式。

**MCP Server** - 透過 MCP 揭露工具的服務。

**Stdio Transport** - 將伺服器以子程序方式透過 stdin/stdout 運行。

**Tool Discovery** - 用戶端查詢伺服器以得知可用工具。

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - 具向量功能的雲端搜尋服務。 [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - 用於部署 Azure 資源的工具。

**Azure OpenAI** - 微軟的企業級 AI 服務。

**Bicep** - Azure 的基礎設施即程式碼語言。 [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - 在 Azure 中為模型部署命名的名稱。

**GPT-5** - 具備推理控制的最新 OpenAI 模型。 [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - 容器化的開發環境。 [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 免費的 AI 模型試驗場。 [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - 使用記憶體儲存進行測試。

**Integration Testing** - 使用真實基礎設施進行測試。

**Maven** - Java 的建置自動化工具。

**Mockito** - Java 的模擬框架。

**Spring Boot** - Java 應用框架。 [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：
本文件係使用 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們力求準確，但請注意，自動翻譯可能包含錯誤或不準確之處。原始文件之母語版本應視為具有權威性的資料來源。對於重要資訊，建議採用專業人工翻譯。我們不對因使用本翻譯而導致的任何誤解或誤譯承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->