<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T23:30:40+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "pa"
}
-->
# LangChain4j ਸ਼ਬਦਾਵਲੀ

## ਸਮੱਗਰੀ ਦੀ ਸੂਚੀ

- [ਮੁੱਖ ਧਾਰਨਾਵਾਂ](../../../docs)
- [LangChain4j ਘਟਕ](../../../docs)
- [AI/ML ਧਾਰਨਾਵਾਂ](../../../docs)
- [ਪ੍ਰਾਂਪਟ ਇੰਜੀਨੀਅਰਿੰਗ](../../../docs)
- [RAG (ਰੀਟਰੀਵਲ-ਅੱਗਮੈਂਟਡ ਜਨਰੇਸ਼ਨ)](../../../docs)
- [ਏਜੰਟ ਅਤੇ ਸੰਦ](../../../docs)
- [ਮਾਡਲ ਸੰਦਰਭ ਪ੍ਰੋਟੋਕੋਲ (MCP)](../../../docs)
- [Azure ਸੇਵਾਵਾਂ](../../../docs)
- [ਟੈਸਟਿੰਗ ਅਤੇ ਵਿਕਾਸ](../../../docs)

ਕੋਰਸ ਵਿੱਚ ਵਰਤੇ ਜਾਣ ਵਾਲੇ ਸ਼ਬਦਾਂ ਅਤੇ ਧਾਰਨਾਵਾਂ ਲਈ ਤੇਜ਼ ਰੈਫਰੇਂਸ।

## ਮੁੱਖ ਧਾਰਨਾਵਾਂ

**AI Agent** - ਇੱਕ ਸਿਸਟਮ ਜੋ AI ਦੀ ਵਰਤੋਂ ਕਰਕੇ ਤਰਕ ਕਰਦਾ ਅਤੇ ਸੁਤੰਤਰ ਤਰੀਕੇ ਨਾਲ ਕਾਰਵਾਈ ਕਰਦਾ ਹੈ। [ਮੋਡੀਊਲ 04](../04-tools/README.md)

**Chain** - ਕਾਰਵਾਈਆਂ ਦੀ ਲੜੀ ਜਿੱਥੇ ਇੱਕ ਕਦਮ ਦਾ ਆਉਟਪੁੱਟ ਅਗਲੇ ਕਦਮ ਵਿੱਚ ਫੀਡ ਹੋਦਾ ਹੈ।

**Chunking** - ਦਸਤਾਵੇਜ਼ਾਂ ਨੂੰ ਛੋਟੇ ਹਿੱਸਿਆਂ ਵਿੱਚ ਵਾਂਝਣਾ। ਆਮ: 300-500 ਟੋਕਨ覆盖 (overlap) ਨਾਲ। [ਮੋਡੀਊਲ 03](../03-rag/README.md)

**Context Window** - ਸਭ ਤੋਂ ਵੱਧ ਟੋਕਨ ਜੋ ਮਾਡਲ ਪ੍ਰੋਸੈਸ ਕਰ ਸਕਦਾ ਹੈ। GPT-5: 400K ਟੋਕਨ।

**Embeddings** - ਟੈਕਸਟ ਦੇ ਅਰਥ ਦਾ ਨੰਬਰਾਤਮਕ ਵੈਕਟਰ ਪ੍ਰਤੀਨਿਧਿਤਾ। [ਮੋਡੀਊਲ 03](../03-rag/README.md)

**Function Calling** - ਮਾਡਲ ਬਾਹਰੀ ਫੰਕਸ਼ਨਾਂ ਨੂੰ ਕਾਲ ਕਰਨ ਲਈ ਸਟ੍ਰਕਚਰਡ অনੁਰੋਧ ਬਣਾਉਂਦਾ ਹੈ। [ਮੋਡੀਊਲ 04](../04-tools/README.md)

**Hallucination** - ਜਦੋਂ ਮਾਡਲ ਗਲਤ ਪਰ ਯਥਾਰਥਸੁਮਾ ਜਾਣਕਾਰੀ ਬਣਾਉਂਦੇ ਹਨ।

**Prompt** - ਇੱਕ ਭਾਸ਼ਾ ਮਾਡਲ ਨੂੰ ਦਿਵਿਆ ਜਾਣ ਵਾਲਾ ਟੈਕਸਟ ਇਨਪੁੱਟ। [ਮੋਡੀਊਲ 02](../02-prompt-engineering/README.md)

**Semantic Search** - ਐంబੈੱਡਿੰਗਜ਼ ਦੀ ਵਰਤੋਂ ਕਰਕੇ ਅਰਥ ਅਧਾਰਿਤ ਖੋਜ, ਕੀਵਰਡਾਂ ਨਹੀਂ। [ਮੋਡੀਊਲ 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ਕੋਈ ਮੈਮੋਰੀ ਨਹੀਂ। Stateful: ਗੱਲਬਾਤ ਇਤਿਹਾਸ ਸੰਭਾਲਦਾ ਹੈ। [ਮੋਡੀਊਲ 01](../01-introduction/README.md)

**Tokens** - ਮਾਡਲਾਂ ਦੁਆਰਾ ਪ੍ਰੋਸੈਸ ਕੀਤੇ ਜਾਣ ਵਾਲੇ ਆਧਾਰਭੂਤ ਟੈਕਸਟ ਯੂਨਿਟ। ਲਾਗਤਾਂ ਅਤੇ ਸੀਮਾਵਾਂ ਨੂੰ ਪ੍ਰਭਾਵਿਤ ਕਰਦੇ ਹਨ। [ਮੋਡੀਊਲ 01](../01-introduction/README.md)

**Tool Chaining** - ਕ੍ਰਮਵਾਰ ਟੂਲ ਏਗਜ਼ਿਕਿਊਸ਼ਨ ਜਿੱਥੇ ਇੱਕ ਦਾ ਆਉਟਪੁੱਟ ਅਗਲੇ ਕਾਲ ਨੂੰ ਜਾਣੂ ਕਰਦਾ ਹੈ। [ਮੋਡੀਊਲ 04](../04-tools/README.md)

## LangChain4j ਘਟਕ

**AiServices** - ਟਾਈਪ-ਸੇਫ AI ਸੇਵਾ ਇੰਟਰਫੇਸ ਬਣਾਉਂਦਾ ਹੈ।

**OpenAiOfficialChatModel** - OpenAI ਅਤੇ Azure OpenAI ਮਾਡਲਾਂ ਲਈ ਇਕਿਕ੍ਰਤ ਕਲਾਇਂਟ।

**OpenAiOfficialEmbeddingModel** - OpenAI Official ਕਲਾਇਂਟ ਦੀ ਵਰਤੋਂ ਕਰਕੇ embeddings ਬਣਾਉਂਦਾ ਹੈ (OpenAI ਅਤੇ Azure OpenAI ਦੋਹਾਂ ਨੂੰ ਸਮਰਥਿਤ)।

**ChatModel** - ਭਾਸ਼ਾ ਮਾਡਲਾਂ ਲਈ ਕੋਰ ਇੰਟਰਫੇਸ।

**ChatMemory** - ਗੱਲਬਾਤ ਇਤਿਹਾਸ ਨੂੰ ਸੰਭਾਲਦਾ ਹੈ।

**ContentRetriever** - RAG ਲਈ ਸਬੰਧਤ ਦਸਤਾਵੇਜ਼ ਚੰਕ ਲੱਭਦਾ ਹੈ।

**DocumentSplitter** - ਦਸਤਾਵੇਜ਼ਾਂ ਨੂੰ ਚੰਕਾਂ ਵਿੱਚ ਵੰਡਦਾ ਹੈ।

**EmbeddingModel** - ਟੈਕਸਟ ਨੂੰ ਨੰਬਰਾਤਮਕ ਵੈਕਟਰਾਂ ਵਿੱਚ ਬਦਲਦਾ ਹੈ।

**EmbeddingStore** - embeddings ਨੂੰ ਸੰਭਾਲਦਾ ਅਤੇ ਪ੍ਰਾਪਤ ਕਰਦਾ ਹੈ।

**MessageWindowChatMemory** - ਤਾਜ਼ਾ ਸੁਨੇਹਿਆਂ ਦੀ ਸਲਾਈਡਿੰਗ ਵਿੰਡੋ ਨੂੰ ਸੰਭਾਲਦਾ ਹੈ।

**PromptTemplate** - `{{variable}}` ਪਲੇਸਹੋਲਡਰਾਂ ਨਾਲ ਦੁਬਾਰਾ ਵਰਤ ਜੋਗ ਪ੍ਰਾਂਪਟ ਬਣਾਉਂਦਾ ਹੈ।

**TextSegment** - ਮੈਟਾਡੇਟਾ ਵਾਲਾ ਟੈਕਸਟ ਚੰਕ। RAG ਵਿੱਚ ਵਰਤਿਆ ਜਾਂਦਾ ਹੈ।

**ToolExecutionRequest** - ਟੂਲ ਏਗਜ਼ਿਕਿਊਸ਼ਨ ਬੇਨਤੀ ਦਾ ਪ੍ਰਤੀਨਿਧਿਤਾ।

**UserMessage / AiMessage / SystemMessage** - ਗੱਲਬਾਤ ਸੁਨੇਹਿਆਂ ਦੇ ਕਿਸਮਾਂ।

## AI/ML ਧਾਰਨਾਵਾਂ

**Few-Shot Learning** - ਪ੍ਰਾਂਪਟਾਂ ਵਿੱਚ ਉਦਾਹਰਣ ਦਿੱਤੀਆਂ ਜਾਣਾ। [ਮੋਡੀਊਲ 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - ਵਿਸ਼ਾਲ ਟੈਕਸਟ ਡੇਟਾ 'ਤੇ ਟ੍ਰੇਨ ਕੀਤੇ ਗਏ AI ਮਾਡਲ।

**Reasoning Effort** - ਸੋਚਣ ਦੀ ਗਹਿਰਾਈ ਨੂੰ ਨਿਯંત્રਿਤ ਕਰਨ ਵਾਲਾ GPT-5 ਪੈਰਾਮੀਟਰ। [ਮੋਡੀਊਲ 02](../02-prompt-engineering/README.md)

**Temperature** - ਆਉਟਪੁੱਟ ਦੀ ਬੇਰੁਕ਼ੀ ਨੂੰ ਨਿਯੰਤ੍ਰਿਤ ਕਰਦਾ ਹੈ। ਨਿਮ੍ਹ=ਨਿਰਧਾਰਿਤ, ਉੱਚ=ਰਚਨਾਤਮਕ।

**Vector Database** - embeddings ਲਈ ਵਿਸ਼ੇਸ਼ ਡੇਟਾਬੇਸ। [ਮੋਡੀਊਲ 03](../03-rag/README.md)

**Zero-Shot Learning** - ਉਦਾਹਰਣਾਂ ਬਿਨਾਂ ਟਾਸਕ ਕਰਨ ਦੀ ਸਮਰੱਥਾ। [ਮੋਡੀਊਲ 02](../02-prompt-engineering/README.md)

## ਪ੍ਰਾਂਪਟ ਇੰਜੀਨੀਅਰਿੰਗ - [ਮੋਡੀਊਲ 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - ਬਿਹਤਰ ਸਹੀਤਾ ਲਈ ਕਦਮ-ਦਰ-ਕਦਮ ਤਰਕ।

**Constrained Output** - ਖਾਸ ਫਾਰਮੈਟ ਜਾਂ ਢਾਂਚੇ ਦੀ ਪਾਲਣਾ ਕਰਵਾਉਣਾ।

**High Eagerness** - ਵਿਸਥਾਰ ਨਾਲ ਤਰਕ ਕਰਨ ਲਈ GPT-5 ਪੈਟਰਨ।

**Low Eagerness** - ਤੇਜ਼ ਜਵਾਬਾਂ ਲਈ GPT-5 ਪੈਟਰਨ।

**Multi-Turn Conversation** - ਵਟਾਂਦਰੇ ਦੌਰਾਨ ਸੰਦਰਭ ਨੂੰ ਬਣਾਈ ਰੱਖਣਾ।

**Role-Based Prompting** - ਸਿਸਟਮ ਸੁਨੇਹਿਆਂ ਰਾਹੀਂ ਮਾਡਲ ਪੈਰਸੋਨਾ ਸੈੱਟ ਕਰਨਾ।

**Self-Reflection** - ਮਾਡਲ ਆਪਣੀ ਆਉਟਪੁੱਟ ਦਾ ਮੁਲਾਂਕਣ ਕਰਦਾ ਅਤੇ ਸੁਧਾਰ ਕਰਦਾ ਹੈ।

**Structured Analysis** - ਨਿਸ਼ਚਿਤ ਮੁਲਾਂਕਣ ਫਰੇਮਵਰਕ।

**Task Execution Pattern** - ਯੋਜਨਾ → ਅਮਲ → ਸਾਰਾਂਸ਼।

## RAG (Retrieval-Augmented Generation) - [ਮੋਡੀਊਲ 03](../03-rag/README.md)

**Document Processing Pipeline** - ਲੋਡ → ਚੰਕ → ਐంబੈੱਡ → ਸਟੋਰ।

**In-Memory Embedding Store** - ਟੈਸਟਿੰਗ ਲਈ ਗੈਰ-ਸਥਾਈ ਸਟੋਰੇਜ਼।

**RAG** - ਜਵਾਬਾਂ ਨੂੰ ਭੂਮਿਕਾ ਦਿੰਦਿਆਂ retrieval ਨੂੰ generation ਨਾਲ ਜੋੜਦਾ ਹੈ।

**Similarity Score** - ਸੈਮੈਂਟਿਕ ਸਮਾਨਤਾ ਦੀ ਮਾਪ (0-1)।

**Source Reference** - ਪ੍ਰਾਪਤ ਕੀਤੇ ਸਮੱਗਰੀ ਬਾਰੇ ਮੈਟਾਡੇਟਾ।

## ਏਜੰਟ ਅਤੇ ਸੰਦ - [ਮੋਡੀਊਲ 04](../04-tools/README.md)

**@Tool Annotation** - Java ਮੈਥਡਾਂ ਨੂੰ AI-ਕਾਲੇਬਲ ਟੂਲਾਂ ਵਜੋਂ ਨਿਸ਼ਾਨ ਲਗਾਉਂਦਾ ਹੈ।

**ReAct Pattern** - ਤਰਕ → ਕਾਰਵਾਈ → ਨਿਰੀਖਣ → ਦੁਹਰਾਉ।

**Session Management** - ਵੱਖ-ਵੱਖ ਯੂਜ਼ਰਾਂ ਲਈ ਵੱਖਰੇ ਸੰਦਰਭ।

**Tool** - ਉਹ ਫੰਕਸ਼ਨ ਜੋ ਇੱਕ AI ਏਜੰਟ ਕਾਲ ਕਰ ਸਕਦਾ ਹੈ।

**Tool Description** - ਟੂਲ ਦੇ ਉਦਦੇਸ਼ ਅਤੇ ਪੈਰਾਮੀਟਰਾਂ ਦੀ ਡੌਕੂਮੇਨਟੇਸ਼ਨ।

## ਮਾਡਲ ਸੰਦਰਭ ਪ੍ਰੋਟੋਕੋਲ (MCP) - [ਮੋਡੀਊਲ 05](../05-mcp/README.md)

**MCP** - AI ਐਪਸ ਨੂੰ ਬਾਹਰੀ ਟੂਲਾਂ ਨਾਲ ਜੋੜਨ ਲਈ ਮਿਆਰੀਕਰਨ।

**MCP Client** - ਐਪਲੀਕੇਸ਼ਨ ਜੋ MCP ਸਰਵਰਾਂ ਨਾਲ ਜੁੜਦਾ ਹੈ।

**MCP Server** - ਸੇਵਾ ਜੋ MCP ਰਾਹੀਂ ਟੂਲਾਂ ਨੂੰ ਪ੍ਰਦਰਸ਼ਿਤ ਕਰਦੀ ਹੈ।

**Stdio Transport** - stdin/stdout ਰਾਹੀਂ subprocess ਵਜੋਂ ਸਰਵਰ।

**Tool Discovery** - ਕਲਾਇਂਟ ਉਪਲਬਧ ਟੂਲਾਂ ਲਈ ਸਰਵਰ ਨੂੰ ਕੁਇਰੀ ਕਰਦਾ ਹੈ।

## Azure ਸੇਵਾਵਾਂ - [ਮੋਡੀਊਲ 01](../01-introduction/README.md)

**Azure AI Search** - ਵੈਕਟਰ ਸਮਰੱਥਾਵਾਂ ਵਾਲੀ ਕਲਾਉਡ ਖੋਜ। [ਮੋਡੀਊਲ 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure ਸਰੋਤਾਂ ਨੂੰ ਡਿਪਲੋਇ/ਤੈਨਾਤ ਕਰਦਾ ਹੈ।

**Azure OpenAI** - Microsoft ਦੀ ਏਂਟਰਪ੍ਰਾਈਜ਼ AI ਸੇਵਾ।

**Bicep** - Azure ਇਨਫਰਾਸਟਰੱਕਚਰ-ਐਜ਼-ਕੋਡ ਭਾਸ਼ਾ। [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azure ਵਿੱਚ ਮਾਡਲ ਡਿਪਲੋਇਮੈਂਟ ਲਈ ਨਾਮ।

**GPT-5** - ਤਰਕ ਨਿਯੰਤਰਣ ਵਾਲਾ ਨਵਾਂ OpenAI ਮਾਡਲ। [ਮੋਡੀਊਲ 02](../02-prompt-engineering/README.md)

## ਟੈਸਟਿੰਗ ਅਤੇ ਵਿਕਾਸ - [ਟੈਸਟਿੰਗ ਗਾਈਡ](TESTING.md)

**Dev Container** - ਕੰਟੇਨਰ ਅਧਾਰਿਤ ਵਿਕਾਸ ਪਰਿਵੇਸ਼। [ਕੰਫਿਗਰੇਸ਼ਨ](../../../.devcontainer/devcontainer.json)

**GitHub Models** - ਮੁਫ਼ਤ AI ਮਾਡਲ ਪਲੇਅਗਰਾਉਂਡ। [ਮੋਡੀਊਲ 00](../00-quick-start/README.md)

**In-Memory Testing** - ਇਨ-ਮੇਮੋਰੀ ਸਟੋਰੇਜ਼ ਨਾਲ ਟੈਸਟਿੰਗ।

**Integration Testing** - ਅਸਲੀ ਇੰਫਰਾਸਟਰੱਕਚਰ ਨਾਲ ਟੈਸਟਿੰਗ।

**Maven** - Java ਬਿਲਡ ਆਟੋਮੇਸ਼ਨ ਟੂਲ।

**Mockito** - Java ਮੌਕਿੰਗ ਫਰੇਮਵਰਕ।

**Spring Boot** - Java ਐਪਲੀਕੇਸ਼ਨ ਫਰੇਮਵਰਕ। [ਮੋਡੀਊਲ 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
ਡਿਸਕਲੇਮਰ:
ਇਹ ਦਸਤਾਵੇਜ਼ ਏਆਈ ਅਨੁਵਾਦ ਸੇਵਾ [Co-op Translator](https://github.com/Azure/co-op-translator) ਦੀ ਵਰਤੋਂ ਕਰਕੇ ਅਨੁਵਾਦ ਕੀਤਾ ਗਿਆ ਹੈ। ਅਸੀਂ ਸਹੀਅਤ ਲਈ ਕੋਸ਼ਿਸ਼ ਕਰਦੇ ਹਾਂ, ਪਰ ਕਿਰਪਾ ਕਰਕੇ ਧਿਆਨ ਰੱਖੋ ਕਿ ਆਟੋਮੇਟਿਕ ਅਨੁਵਾਦਾਂ ਵਿੱਚ ਗਲਤੀਆਂ ਜਾਂ ਅਸਪਸ਼ਟਤਾਵਾਂ ਹੋ ਸਕਦੀਆਂ ਹਨ। ਮੂਲ ਦਸਤਾਵੇਜ਼ ਨੂੰ ਉਸ ਦੀ ਮੂਲ ਭਾਸ਼ਾ ਵਿੱਚ ਪ੍ਰਮਾਣਿਕ ਸਰੋਤ ਮੰਨਿਆ ਜਾਣਾ ਚਾਹੀਦਾ ਹੈ। ਆਵਸ਼ਯਕ ਜਾਣਕਾਰੀ ਲਈ ਪੇਸ਼ੇਵਰ ਮਨੁੱਖੀ ਅਨੁਵਾਦ ਦੀ ਸਿਫ਼ਾਰਿਸ਼ ਕੀਤੀ ਜਾਂਦੀ ਹੈ। ਇਸ ਅਨੁਵਾਦ ਦੇ ਇਸਤੇਮਾਲ ਕਾਰਨ ਪੈਦਾਅ ਹੋਣ ਵਾਲੀਆਂ ਕਿਸੇ ਵੀ ਗਲਤਫਹਮੀਆਂ ਜਾਂ ਭੁੱਲ-ਸਮਝ ਲਈ ਅਸੀਂ ਜ਼ਿੰਮੇਵਾਰ ਨਹੀਂ ਹਾਂ।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->