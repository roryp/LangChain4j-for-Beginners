<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T07:36:05+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "pcm"
}
-->
# LangChain4j Glosari

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

Quick referens for di terms an koncepts wey dem dey use for di whole course.

## Core Concepts

**AI Agent** - System wey dey use AI to reason an act on im own. [Module 04](../04-tools/README.md)

**Chain** - Sequence of operations wey one output dey feed into di next step.

**Chunking** - To break document dem into smaller pieces. Normal: 300-500 tokens wit overlap. [Module 03](../03-rag/README.md)

**Context Window** - Di maximum tokens wey model fit process. GPT-5: 400K tokens.

**Embeddings** - Numerical vector dem wey dey represent wetin text mean. [Module 03](../03-rag/README.md)

**Function Calling** - Di model go generate structured requests to call external functions. [Module 04](../04-tools/README.md)

**Hallucination** - When model dem dey produce wrong but e sound like correct information.

**Prompt** - Text input wey you give language model. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Search by meaning using embeddings, no be keywords. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: no memory. Stateful: e dey keep conversation history. [Module 01](../01-introduction/README.md)

**Tokens** - Di basic text units wey models dey process. E affect cost an limits. [Module 01](../01-introduction/README.md)

**Tool Chaining** - To run tools one after di other where output from one dey inform di next call. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Creates type-safe AI service interfaces.

**OpenAiOfficialChatModel** - Unified client for OpenAI and Azure OpenAI models.

**OpenAiOfficialEmbeddingModel** - Creates embeddings using OpenAI Official client (supports both OpenAI and Azure OpenAI).

**ChatModel** - Core interface for language models.

**ChatMemory** - Maintains conversation history.

**ContentRetriever** - Finds relevant document chunks for RAG.

**DocumentSplitter** - Breaks documents into chunks.

**EmbeddingModel** - Converts text into numerical vectors.

**EmbeddingStore** - Stores and retrieves embeddings.

**MessageWindowChatMemory** - Maintains sliding window of recent messages.

**PromptTemplate** - Creates reusable prompts wit `{{variable}}` placeholders.

**TextSegment** - Text chunk wit metadata. Dem dey use am for RAG.

**ToolExecutionRequest** - Represents tool execution request.

**UserMessage / AiMessage / SystemMessage** - Conversation message types.

## AI/ML Concepts

**Few-Shot Learning** - To give examples inside prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI models wey dem train for plenty text data.

**Reasoning Effort** - GPT-5 parameter wey dey control how deep e go think. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - E dey control how random di output go be. Low = deterministic, high = creative.

**Vector Database** - Special database for embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - To do tasks without examples. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Step-by-step reasoning for better accuracy.

**Constrained Output** - To enforce specific format or structure.

**High Eagerness** - GPT-5 pattern wey dey make thorough reasoning.

**Low Eagerness** - GPT-5 pattern wey dey give quick answers.

**Multi-Turn Conversation** - To keep context across multiple exchanges.

**Role-Based Prompting** - To set model persona via system messages.

**Self-Reflection** - Model go evaluate an improve im own output.

**Structured Analysis** - Fixed framework for evaluation.

**Task Execution Pattern** - Plan → Execute → Summarize.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Load → chunk → embed → store.

**In-Memory Embedding Store** - Non-persistent storage wey dem dey use for testing.

**RAG** - E combine retrieval with generation to make responses grounded.

**Similarity Score** - Measure (0-1) of semantic similarity.

**Source Reference** - Metadata about di content wey dem retrieve.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Marks Java methods as AI-callable tools.

**ReAct Pattern** - Reason → Act → Observe → Repeat.

**Session Management** - Separate contexts for different users.

**Tool** - Function wey AI agent fit call.

**Tool Description** - Documentation for wetin tool dey do and di parameters.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - Standard wey dem use to connect AI apps to external tools.

**MCP Client** - Application wey dey connect to MCP servers.

**MCP Server** - Service wey dey expose tools via MCP.

**Stdio Transport** - Server wey dey run as subprocess via stdin/stdout.

**Tool Discovery** - Client go query server to know which tools dey available.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloud search wey get vector capabilities. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Tool wey deploy Azure resources.

**Azure OpenAI** - Microsoft enterprise AI service.

**Bicep** - Azure infrastructure-as-code language. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Di name wey you dey give model deployment for Azure.

**GPT-5** - Di latest OpenAI model wey get reasoning control. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Containerized development environment. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Free AI model playground. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testing with in-memory storage.

**Integration Testing** - Testing wit real infrastructure.

**Maven** - Java build automation tool.

**Mockito** - Java mocking framework.

**Spring Boot** - Java application framework. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Disclaimah:
Dis dokument don translate with AI translation service (Co-op Translator: https://github.com/Azure/co-op-translator). Even though we dey try make everything correct, abeg sabi say automated translation fit get errors or wrong tins. The original dokument for im original language suppose be the main/official source. If na important information, make you use professional human translator. We no dey responsible for any misunderstanding or wrong interpretation wey fit come from using this translation.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->