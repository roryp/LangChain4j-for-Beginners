# LangChain4j Glossary

## Table of Contents

- [Core Concepts](../../../docs)
- [LangChain4j Components](../../../docs)
- [AI/ML Concepts](../../../docs)
- [Guardrails](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agents and Tools](../../../docs)
- [Agentic Module](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Services](../../../docs)
- [Testing and Development](../../../docs)

Quick reference for terms and concepts wey dem dey use throughout di course.

## Core Concepts

**AI Agent** - System wey use AI to reason and dey act on im own. [Module 04](../04-tools/README.md)

**Chain** - Sequence of operations wey output dey enter next step.

**Chunking** - To break documents into smaller pieces dem. Normal: 300-500 tokens wit overlap. [Module 03](../03-rag/README.md)

**Context Window** - Maximum tokens wey model fit process. GPT-5: 400K tokens.

**Embeddings** - Number vectors wey represent text meaning. [Module 03](../03-rag/README.md)

**Function Calling** - Model dey generate structured requests to call external functions. [Module 04](../04-tools/README.md)

**Hallucination** - When model dem go generate wrong but believable info.

**Prompt** - Text input wey you give one language model. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Search by meaning use embeddings, no be keywords. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: no memory. Stateful: e dey keep conversation history. [Module 01](../01-introduction/README.md)

**Tokens** - Basic text unit wey model dem dey process. E dey affect cost and limits. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sequence wey tools de run where the output dey guide the next call. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - E dey create type-safe AI service interfaces.

**OpenAiOfficialChatModel** - Unified client for OpenAI and Azure OpenAI models.

**OpenAiOfficialEmbeddingModel** - E dey create embeddings use OpenAI Official client (e fit support both OpenAI and Azure OpenAI).

**ChatModel** - Core interface for language models.

**ChatMemory** - E dey keep conversation history.

**ContentRetriever** - E dey find relevant document chunks for RAG.

**DocumentSplitter** - E dey break documents into chunks.

**EmbeddingModel** - E dey convert text to number vectors.

**EmbeddingStore** - For store and retrieve embeddings.

**MessageWindowChatMemory** - E dey keep sliding window of recent messages.

**PromptTemplate** - E dey create reusable prompts wit `{{variable}}` placeholders.

**TextSegment** - Text chunk wey get metadata. E dey used in RAG.

**ToolExecutionRequest** - E represent tool execution request.

**UserMessage / AiMessage / SystemMessage** - Different conversation message types.

## AI/ML Concepts

**Few-Shot Learning** - To give examples in prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI models wey dem train on plenty text data.

**Reasoning Effort** - GPT-5 parameter wey dey control how deep e go reason. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - E dey control how random the output go be. Low=deterministic, high=creative.

**Vector Database** - Special database wey dem dey use for embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - To perform task without example. [Module 02](../02-prompt-engineering/README.md)

## Guardrails - [Module 00](../00-quick-start/README.md)

**Defense in Depth** - Multi-layer security approach wey combine application-level guardrails wit provider safety filters.

**Hard Block** - Provider go throw HTTP 400 error if content violation serious.

**InputGuardrail** - LangChain4j interface wey dey validate user input before e enter LLM. E dey save cost and latency by blocking harmful prompts early.

**InputGuardrailResult** - Return type for guardrail validation: `success()` or `fatal("reason")`.

**OutputGuardrail** - Interface to validate AI responses before e return to users.

**Provider Safety Filters** - Content filters wey AI providers like GitHub Models build inside wey dey catch violation for API level.

**Soft Refusal** - Model go polite decline to answer without throw error.

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Step-by-step reasoning to get better accuracy.

**Constrained Output** - To enforce specific format or structure.

**High Eagerness** - GPT-5 pattern for thorough reasoning.

**Low Eagerness** - GPT-5 pattern for quick answers.

**Multi-Turn Conversation** - E dey maintain context across exchanges.

**Role-Based Prompting** - To set model persona wit system messages.

**Self-Reflection** - Model go evaluate and improve im output.

**Structured Analysis** - Fixed evaluation framework.

**Task Execution Pattern** - Plan → Execute → Summarize.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Load → chunk → embed → store.

**In-Memory Embedding Store** - Non-persistent storage wey dem dey use for testing.

**RAG** - E dey combine retrieval wit generation to ground responses.

**Similarity Score** - Measure (0-1) of semantic similarity.

**Source Reference** - Metadata about retrieved content.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - E dey mark Java methods as AI-callable tools.

**ReAct Pattern** - Reason → Act → Observe → Repeat.

**Session Management** - Separate contexts for different users.

**Tool** - Function wey AI agent fit call.

**Tool Description** - Documentation of tool purpose and parameters.

## Agentic Module - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - E mark interfaces as AI agents wit declarative behavior definition.

**Agent Listener** - Hook to monitor agent execution via `beforeAgentInvocation()` and `afterAgentInvocation()`.

**Agentic Scope** - Shared memory wey agents dey store outputs wit `outputKey` so downstream agents fit use am.

**AgenticServices** - Factory wey dey create agents use `agentBuilder()` and `supervisorBuilder()`.

**Conditional Workflow** - Route wey depend on condition go different specialist agents.

**Human-in-the-Loop** - Workflow pattern wey add human checkpoints for approval or content review.

**langchain4j-agentic** - Maven dependency for declarative agent building (e still dey experimental).

**Loop Workflow** - E dey run agent execution again and again until condition meet (e.g., quality score ≥ 0.8).

**outputKey** - Agent annotation parameter wey tell where results dey store for Agentic Scope.

**Parallel Workflow** - Run plenty agents at once for independent tasks.

**Response Strategy** - How supervisor dey form final answer: LAST, SUMMARY, or SCORED.

**Sequential Workflow** - Run agents one after another where output flow enter the next step.

**Supervisor Agent Pattern** - Advanced agentic pattern wey supervisor LLM fit dynamically decide which sub-agents to invoke.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - Maven dependency for MCP integration for LangChain4j.

**MCP** - Model Context Protocol: standard wey dey connect AI apps to external tools. Build once, use everywhere.

**MCP Client** - Application wey dey connect to MCP servers to find and use tools.

**MCP Server** - Service wey expose tools via MCP wit clear descriptions and parameter schemas.

**McpToolProvider** - LangChain4j component wey dey wrap MCP tools for use in AI services and agents.

**McpTransport** - Interface for MCP communication. Implementations include Stdio and HTTP.

**Stdio Transport** - Local process transport wey use stdin/stdout. E good for filesystem access or command-line tools.

**StdioMcpTransport** - LangChain4j implementation wey go spawn MCP server as subprocess.

**Tool Discovery** - Client dey ask server for tools wey dey available wit descriptions and schemas.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloud search wey get vector capabilities. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - E dey deploy Azure resources.

**Azure OpenAI** - Microsoft enterprise AI service.

**Bicep** - Azure infrastructure-as-code language. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Name for model deployment for Azure.

**GPT-5** - Latest OpenAI model with reasoning control. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Containerized development environment. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Free AI model playground. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testing wit in-memory storage.

**Integration Testing** - Testing wit real infrastructure.

**Maven** - Java build automation tool.

**Mockito** - Java mocking framework.

**Spring Boot** - Java application framework. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:
Dis document dem don translate am wit AI translation service wey dem dey call [Co-op Translator](https://github.com/Azure/co-op-translator). Even though we try make sure say e correct well, abeg make you sabi say automated translations fit get some mistakes or wrong parts. Di original document wey e dey for e own language na im be di real correct one. If na serious matter, e good make human professional translate am. We nor go take any blame if person misunderstand or misinterpret from dis translation.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->