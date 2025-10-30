**AI Agent** - System that uses AI to reason and act autonomously. [Module 04](../04-tools/README.md)

**Chain** - Sequence of operations where output feeds into the next step.

**Chunking** - Breaking documents into smaller pieces. Typical: 300-500 tokens with overlap. [Module 03](../03-rag/README.md)

**Context Window** - Maximum tokens a model can process. GPT-5: 400K tokens.

**Embeddings** - Numerical vectors representing text meaning. [Module 03](../03-rag/README.md)

**Function Calling** - Model generates structured requests to call external functions. [Module 04](../04-tools/README.md)

**Hallucination** - When models generate incorrect but plausible information.

**Prompt** - Text input to a language model. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Search by meaning using embeddings, not keywords. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: no memory. Stateful: maintains conversation history. [Module 01](../01-introduction/README.md)

**Tokens** - Basic text units models process. Affects costs and limits. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sequential tool execution where output informs next call. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Creates type-safe AI service interfaces using `@Tool` annotations. [Module 04](../04-tools/README.md)

**AzureOpenAiChatModel** - Connects to Azure OpenAI models. [Module 01](../01-introduction/README.md)

**AzureOpenAiEmbeddingModel** - Creates embeddings using Azure OpenAI. [Module 03](../03-rag/README.md)

**ChatLanguageModel** - Core interface for language models. [Module 00](../00-quick-start/README.md)

**ChatMemory** - Maintains conversation history. [Module 01](../01-introduction/README.md)

**ContentRetriever** - Finds relevant document chunks for RAG. [Module 03](../03-rag/README.md)

**DocumentSplitter** - Breaks documents into chunks. [Module 03](../03-rag/README.md)

**EmbeddingModel** - Converts text into numerical vectors. [Module 03](../03-rag/README.md)

**EmbeddingStore** - Stores and retrieves embeddings. [Module 03](../03-rag/README.md)

**MessageWindowChatMemory** - Maintains sliding window of recent messages. [Module 01](../01-introduction/README.md)

**OpenAiChatModel** - Connects to OpenAI-compatible endpoints. [Module 00](../00-quick-start/README.md)

**PromptTemplate** - Creates reusable prompts with `{{variable}}` placeholders. [Module 02](../02-prompt-engineering/README.md)

**TextSegment** - Text chunk with metadata. Used in RAG. [Module 03](../03-rag/README.md)

**ToolExecutionRequest** - Represents tool execution request. [Module 04](../04-tools/README.md)

**UserMessage / AiMessage / SystemMessage** - Conversation message types. [Module 01](../01-introduction/README.md)

## AI/ML Concepts

**Few-Shot Learning** - Providing examples in prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI models trained on vast text data.

**Reasoning Effort** - GPT-5 parameter controlling thinking depth. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Controls output randomness. Low=deterministic, high=creative.

**Vector Database** - Specialized database for embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Performing tasks without examples. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering

**Chain-of-Thought** - Step-by-step reasoning for better accuracy. [Module 02](../02-prompt-engineering/README.md)

**Constrained Output** - Enforcing specific format or structure. [Module 02](../02-prompt-engineering/README.md)

**High Eagerness** - GPT-5 pattern for thorough reasoning. [Module 02](../02-prompt-engineering/README.md)

**Low Eagerness** - GPT-5 pattern for quick answers. [Module 02](../02-prompt-engineering/README.md)

**Multi-Turn Conversation** - Maintaining context across exchanges. [Module 02](../02-prompt-engineering/README.md)

**Role-Based Prompting** - Setting model persona via system messages. [Module 02](../02-prompt-engineering/README.md)

**Self-Reflection** - Model evaluates and improves its output. [Module 02](../02-prompt-engineering/README.md)

**Structured Analysis** - Fixed evaluation framework. [Module 02](../02-prompt-engineering/README.md)

**Task Execution Pattern** - Plan → Execute → Summarize. [Module 02](../02-prompt-engineering/README.md)

## RAG (Retrieval-Augmented Generation)

**Document Processing Pipeline** - Load → chunk → embed → store. [Module 03](../03-rag/README.md)

**In-Memory Embedding Store** - Non-persistent storage for testing. [Module 03](../03-rag/README.md)

**RAG** - Combines retrieval with generation to ground responses. [Module 03](../03-rag/README.md)

**Similarity Score** - Measure (0-1) of semantic similarity. [Module 03](../03-rag/README.md)

**Source Reference** - Metadata about retrieved content. [Module 03](../03-rag/README.md)

## Agents and Tools

**@Tool Annotation** - Marks Java methods as AI-callable tools. [Module 04](../04-tools/README.md)

**ReAct Pattern** - Reason → Act → Observe → Repeat. [Module 04](../04-tools/README.md)

**Session Management** - Separate contexts for different users. [Module 04](../04-tools/README.md)

**Tool** - Function an AI agent can call. [Module 04](../04-tools/README.md)

**Tool Description** - Documentation of tool purpose and parameters. [Module 04](../04-tools/README.md)

## Model Context Protocol (MCP)

**Docker Transport** - MCP server in Docker container. [Module 05](../05-mcp/README.md)

**MCP** - Standard for connecting AI apps to external tools. [Module 05](../05-mcp/README.md)

**MCP Client** - Application that connects to MCP servers. [Module 05](../05-mcp/README.md)

**MCP Server** - Service exposing tools via MCP. [Module 05](../05-mcp/README.md)

**Server-Sent Events (SSE)** - Server-to-client streaming over HTTP.

**Stdio Transport** - Server as subprocess via stdin/stdout. [Module 05](../05-mcp/README.md)

**Streamable HTTP Transport** - HTTP with SSE for real-time communication. [Module 05](../05-mcp/README.md)

**Tool Discovery** - Client queries server for available tools. [Module 05](../05-mcp/README.md)

## Azure Services

**Azure AI Search** - Cloud search with vector capabilities. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Deploys Azure resources. [Module 01](../01-introduction/README.md)

**Azure OpenAI** - Microsoft's enterprise AI service. [Module 01](../01-introduction/README.md)

**Bicep** - Azure infrastructure-as-code language. [Module 01](../01-introduction/infra/README.md)

**Deployment Name** - Name for model deployment in Azure. [Module 01](../01-introduction/README.md)

**GPT-5** - Latest OpenAI model with reasoning control. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development

**Dev Container** - Containerized development environment.

**GitHub Models** - Free AI model playground. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testing with in-memory storage. [Testing Guide](TESTING.md)

**Integration Testing** - Testing with real infrastructure. [Testing Guide](TESTING.md)

**Maven** - Java build automation tool.

**Mockito** - Java mocking framework. [Testing Guide](TESTING.md)

**Spring Boot** - Java application framework. [Module 01](../01-introduction/README.md)

**Testcontainers** - Docker containers in tests. [Testing Guide](TESTING.md)
