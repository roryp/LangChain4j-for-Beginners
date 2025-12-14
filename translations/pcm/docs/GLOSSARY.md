<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:27:05+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "pcm"
}
-->
# LangChain4j Glossary

## Table of Contents

- [Core Concepts](../../../docs)
- [LangChain4j Components](../../../docs)
- [AI/ML Concepts](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agents and Tools](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Services](../../../docs)
- [Testing and Development](../../../docs)

Quick reference for terms and concepts wey dem dey use for di whole course.

## Core Concepts

**AI Agent** - System wey dey use AI to reason and act on im own. [Module 04](../04-tools/README.md)

**Chain** - Sequence of operations wey output dey feed into di next step.

**Chunking** - Breaking documents into smaller pieces. Typical: 300-500 tokens with overlap. [Module 03](../03-rag/README.md)

**Context Window** - Maximum tokens wey model fit process. GPT-5: 400K tokens.

**Embeddings** - Numerical vectors wey represent text meaning. [Module 03](../03-rag/README.md)

**Function Calling** - Model dey generate structured requests to call external functions. [Module 04](../04-tools/README.md)

**Hallucination** - When models dey generate wrong but plausible information.

**Prompt** - Text input wey you give language model. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Search by meaning using embeddings, no be keywords. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: no memory. Stateful: dey keep conversation history. [Module 01](../01-introduction/README.md)

**Tokens** - Basic text units wey models dey process. E dey affect costs and limits. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sequential tool execution wey output dey inform next call. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - E dey create type-safe AI service interfaces.

**OpenAiOfficialChatModel** - Unified client for OpenAI and Azure OpenAI models.

**OpenAiOfficialEmbeddingModel** - E dey create embeddings using OpenAI Official client (e support both OpenAI and Azure OpenAI).

**ChatModel** - Core interface for language models.

**ChatMemory** - E dey keep conversation history.

**ContentRetriever** - E dey find relevant document chunks for RAG.

**DocumentSplitter** - E dey break documents into chunks.

**EmbeddingModel** - E dey convert text into numerical vectors.

**EmbeddingStore** - E dey store and retrieve embeddings.

**MessageWindowChatMemory** - E dey keep sliding window of recent messages.

**PromptTemplate** - E dey create reusable prompts with `{{variable}}` placeholders.

**TextSegment** - Text chunk wey get metadata. E dey use for RAG.

**ToolExecutionRequest** - E represent tool execution request.

**UserMessage / AiMessage / SystemMessage** - Conversation message types.

## AI/ML Concepts

**Few-Shot Learning** - E mean say you dey provide examples inside prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI models wey dem train on plenty text data.

**Reasoning Effort** - GPT-5 parameter wey dey control how deep e go reason. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - E dey control how random output go be. Low=deterministic, high=creative.

**Vector Database** - Special database for embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - E mean say you fit do task without examples. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Step-by-step reasoning for better accuracy.

**Constrained Output** - E mean say you dey enforce specific format or structure.

**High Eagerness** - GPT-5 pattern wey dey for thorough reasoning.

**Low Eagerness** - GPT-5 pattern wey dey for quick answers.

**Multi-Turn Conversation** - E mean say you dey maintain context across exchanges.

**Role-Based Prompting** - E mean say you dey set model persona through system messages.

**Self-Reflection** - Model dey evaluate and improve im own output.

**Structured Analysis** - Fixed evaluation framework.

**Task Execution Pattern** - Plan → Execute → Summarize.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Load → chunk → embed → store.

**In-Memory Embedding Store** - Non-persistent storage wey dem dey use for testing.

**RAG** - E combine retrieval with generation to ground responses.

**Similarity Score** - Measure (0-1) of semantic similarity.

**Source Reference** - Metadata about retrieved content.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - E mark Java methods as AI-callable tools.

**ReAct Pattern** - Reason → Act → Observe → Repeat.

**Session Management** - E mean say different contexts dey for different users.

**Tool** - Function wey AI agent fit call.

**Tool Description** - Documentation of tool purpose and parameters.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**Docker Transport** - MCP server wey dey inside Docker container.

**MCP** - Standard wey dey connect AI apps to external tools.

**MCP Client** - Application wey dey connect to MCP servers.

**MCP Server** - Service wey dey expose tools via MCP.

**Server-Sent Events (SSE)** - Server-to-client streaming over HTTP.

**Stdio Transport** - Server wey dey as subprocess via stdin/stdout.

**Streamable HTTP Transport** - HTTP with SSE for real-time communication.

**Tool Discovery** - Client dey query server for available tools.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloud search wey get vector capabilities. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - E dey deploy Azure resources.

**Azure OpenAI** - Microsoft enterprise AI service.

**Bicep** - Azure infrastructure-as-code language. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Name wey you go give model deployment for Azure.

**GPT-5** - Latest OpenAI model wey get reasoning control. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Containerized development environment. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Free AI model playground. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testing wey dey use in-memory storage.

**Integration Testing** - Testing wey dey use real infrastructure.

**Maven** - Java build automation tool.

**Mockito** - Java mocking framework.

**Spring Boot** - Java application framework. [Module 01](../01-introduction/README.md)

**Testcontainers** - Docker containers wey dem dey use for tests.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:
Dis document na AI translation service [Co-op Translator](https://github.com/Azure/co-op-translator) wey translate am. Even though we dey try make am correct, abeg sabi say automated translation fit get some mistakes or no too correct. The original document wey e dey for im own language na im be the correct one. If na serious matter, e better make person wey sabi human translation do am. We no go responsible for any wahala or wrong understanding wey fit happen because of this translation.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->