<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T08:28:20+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "kn"
}
-->
# LangChain4j ಶಬ್ದಕೋಶ

## ವಿಷಯ ಸೂಚಿ

- [ಮುಖ್ಯ ಕಲ್ಪನೆಗಳು](../../../docs)
- [LangChain4j ಘಟಕಗಳು](../../../docs)
- [AI/ML ಕಲ್ಪನೆಗಳು](../../../docs)
- [ಪ್ರಾಂಪ್‌ಟ್ ಎಂಜಿನಿಯರಿಂಗ್ - [ಮಾಡ್ಯೂಲ್ 02]](../../../docs)
- [RAG (Retrieval-Augmented Generation) - [ಮಾಡ್ಯೂಲ್ 03]](../../../docs)
- [ಏಜೆಂಟ್‌ಗಳು ಮತ್ತು ಉಪಕರಣಗಳು - [ಮಾಡ್ಯೂಲ್ 04]](../../../docs)
- [Model Context Protocol (MCP) - [ಮಾಡ್ಯೂಲ್ 05]](../../../docs)
- [Azure ಸೇವೆಗಳು - [ಮಾಡ್ಯೂಲ್ 01]](../../../docs)
- [ಪರೀಕ್ಷೆ ಮತ್ತು ಅಭಿವೃದ್ಧಿ - [ಪರೀಕ್ಷೆ ಮಾರ್ಗದರ್ಶಿ]](../../../docs)

ಕೋರ್ಸ್‌ನಲ್ಲಿ ಬಳಸಲಾಗುವ ಪದಗಳು ಮತ್ತು ಕಲ್ಪನೆಗಳ ತ್ವರಿತ ಉಲ್ಲೇಖ.

## ಮುಖ್ಯ ಕಲ್ಪನೆಗಳು

**AI Agent** - ಸ್ವಾಯತ್ತವಾಗಿ ಯೋಚಿಸಿ ಮತ್ತು ಕ್ರಮವಹಿಸಲು AI ಅನ್ನು ಬಳಸುವ ವ್ಯವಸ್ಥೆ. [ಮಾಡ್ಯೂಲ್ 04](../04-tools/README.md)

**Chain** - ಒಂದರ ಔಟ್ಪುಟ್ ಮುಂದಿನ ಹಂತಕ್ಕೆ ಒದಗಿಸಲಾದ ಕಾರ್ಯಗಳ ಕ್ರಮ.

**Chunking** - ದಾಖಲೆಗಳನ್ನು ಚಿಕ್ಕ ಭಾಗಗಳಾಗಿ ವಿಭಜಿಸುವುದು. ಸಾಮಾನ್ಯವಾಗಿ: 300-500 ಟೋಕನ್‌ಗಳು ಒವರ್ಲಾಪ್ ಜೊತೆಗೆ. [ಮಾಡ್ಯೂಲ್ 03](../03-rag/README.md)

**Context Window** - ಮಾದರಿ ಪ್ರಕ್ರಿಯೆ ಮಾಡಲು ಸಾಧ್ಯವಿರುವ ಗರಿಷ್ಠ ಟೋಕನ್‌ಗಳು. GPT-5: 400K ಟೋಕನ್‌ಗಳು.

**Embeddings** - ಪಠ್ಯದ ಅರ್ಥವನ್ನು ಪ್ರತಿನಿಧಿಸುವ ಸಂಖ್ಯಾತ್ಮಕ ವೆಕ್ಟರ್‌ಗಳು. [ಮಾಡ್ಯೂಲ್ 03](../03-rag/README.md)

**Function Calling** - ಹೊರಗಿನ ಫಂಕ್ಷನ್‌ಗಳನ್ನು ಕರೆಸುವ ಸಂಕೇತಿತ ವಿನಂತಿಗಳನ್ನು ಮಾದರಿ ರಚಿಸುತ್ತದೆ. [ಮಾಡ್ಯೂಲ್ 04](../04-tools/README.md)

**Hallucination** - ಮಾದರಿಗಳು ತಪ್ಪು ಆದರೆ ನಂಬಬಹುದಾದ ಮಾಹಿತಿಯನ್ನು ರಚಿಸುವ ಸಮಯ.

**Prompt** - ಭಾಷಾ ಮಾದರಿಗೆ ನೀಡುವ ಪಠ್ಯ ಇನ್ಪುಟ್. [ಮಾಡ್ಯೂಲ್ 02](../02-prompt-engineering/README.md)

**Semantic Search** - ಕೀವರ್ಡ್‌ಗಳ ಕೊರತೆಯಲ್ಲಿ embeddings ಬಳಸಿ ಅರ್ಥದ ಮೂಲಕ ಹುಡುಕಾಟ. [ಮಾಡ್ಯೂಲ್ 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ಮೆಮೊರಿ ಇಲ್ಲ. Stateful: ಸಂಭಾಷಣೆ ಇತಿಹಾಸವನ್ನು ಕಾಪಾಡುತ್ತದೆ. [ಮಾಡ್ಯೂಲ್ 01](../01-introduction/README.md)

**Tokens** - ಮಾದರಿಗಳು ಪ್ರಕ್ರಿಯೆ ಮಾಡುವ ಮೂಲ ಪಠ್ಯ ಘಟಕಗಳು. ವೆಚ್ಚ ಮತ್ತು ಮર્યಾದೆಗಳನ್ನು ಪ್ರಭಾವಿಸುತ್ತದೆ. [ಮಾಡ್ಯೂಲ್ 01](../01-introduction/README.md)

**Tool Chaining** - ಔಟ್ಪುಟ್ ಮುಂದಿನ ಕರೆಗೆ ಮಾಹಿತಿ ನೀಡುವ ಸರಣಿಯ ಉಪಕರಣ ಕಾರ್ಯನಿರ್ವಹಣೆ. [ಮಾಡ್ಯೂಲ್ 04](../04-tools/README.md)

## LangChain4j ಘಟಕಗಳು

**AiServices** - ಟೈಪ್-ಸೇಫ್ AI ಸೇವಾ ಇಂಟರ್ಫೇಸ್ಗಳನ್ನು ರಚಿಸುತ್ತದೆ.

**OpenAiOfficialChatModel** - OpenAI ಮತ್ತು Azure OpenAI ಮಾದರಿಗಳಿಗೆ ಏಕೀಕೃತ ಕ್ಲೈಯಂಟ್.

**OpenAiOfficialEmbeddingModel** - OpenAI Official ಕ್ಲೈಯಂಟ್ ಬಳಸಿ embeddings ರಚಿಸುತ್ತದೆ (OpenAI ಮತ್ತು Azure OpenAI ಎರಡನ್ನು ಬೆಂಬಲಿಸುತ್ತದೆ).

**ChatModel** - ಭಾಷಾ ಮಾದರಿಗಳためದ ಕೋರ್ ಇಂಟರ್ಫೇಸ್.

**ChatMemory** - ಸಂಭಾಷಣೆ ಇತಿಹಾಸವನ್ನು ಕಾಪಾಡುತ್ತದೆ.

**ContentRetriever** - RAG ಗೆ ಸಂಬಂಧಿಸಿದ ದಾಖಲೆ ಚಂಕ್‌ಗಳನ್ನು ಹುಡುಕುತ್ತದೆ.

**DocumentSplitter** - ದಾಖಲೆಗಳನ್ನು ಚುಂಕ್‌ಗಳಾಗಿ ವಿಭಜಿಸುತ್ತದೆ.

**EmbeddingModel** - ಪಠ್ಯವನ್ನು ಸಂಖ್ಯಾತ್ಮಕ ವೆಕ್ಟರ್‌ಗಳಲ್ಲಿ ಪರಿವರ್ತಿಸುತ್ತದೆ.

**EmbeddingStore** - embeddings ಅನ್ನು ಸಂಗ್ರಹಿಸುತ್ತದೆ ಮತ್ತು ಪುನಃ ಹಿಂತಿರುಗಿಸುತ್ತದೆ.

**MessageWindowChatMemory** - ಇತ್ತೀಚಿನ ಸಂದೇಶಗಳ ಸ್ಲೈಡಿಂಗ್ ವಿಂಡೋವನ್ನು ಕಾಪಾಡುತ್ತದೆ.

**PromptTemplate** - `{{variable}}` ಪ್ಲೇಸ್ಹೋಲ್ಡರ್‌ಗಳನ್ನು ಹೊಂದಿದ ಪುನರುಪಯೋಗಿಸಬಹುದಾದ ಪ್ರಾಂಪ್ಟ್‌ಗಳನ್ನು ರಚಿಸುತ್ತದೆ.

**TextSegment** - ಮೆಟಾ‌ಡೇಟಾ ಹೊಂದಿರುವ ಪಠ್ಯದ ಚುಂಕ್. RAG ನಲ್ಲಿ ಬಳಸಲಾಗುತ್ತದೆ.

**ToolExecutionRequest** - ಉಪಕರಣ ಕಾರ್ಯಾಚರಣೆಯ ವಿನಂತಿಯನ್ನು ಪ್ರತಿನಿಧಿಸುತ್ತದೆ.

**UserMessage / AiMessage / SystemMessage** - ಸಂಭಾಷಣಾ ಸಂದೇಶ ಪ್ರಕಾರಗಳು.

## AI/ML ಕಲ್ಪನೆಗಳು

**Few-Shot Learning** - ಪ್ರಾಂಪ್ಟ್‌ಗಳಲ್ಲಿ ಉದಾಹರಣೆಗಳನ್ನು ಒದಗಿಸುವುದು. [ಮಾಡ್ಯೂಲ್ 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - ವ್ಯಾಪಕ ಪಠ್ಯ ಡೇಟಾ ಮೇಲೆ ತರಬೇತಿಸಲಾದ AI ಮಾದರಿಗಳು.

**Reasoning Effort** - ಚಿಂತನೆ ಆಳತೆಯನ್ನು ನಿಯಂತ್ರಿಸುವ GPT-5 ಪರಿಮಾಣ. [ಮಾಡ್ಯೂಲ್ 02](../02-prompt-engineering/README.md)

**Temperature** - ಔಟ್ಪುಟ್‌ನ ಯಾದೃಚ್ಛಿಕತೆಯನ್ನು ನಿಯಂತ್ರಿಸುತ್ತದೆ. ಕಡಿಮೆ=ನಿರ್ಧಾರಾತ್ಮಕ, ಹೆಚ್ಚು=ಸೃಜನಾತ್ಮಕ.

**Vector Database** - embeddings ಗಾಗಿ વિશೇಷಿತ ಡೇಟಾಬೇಸ್. [ಮಾಡ್ಯೂಲ್ 03](../03-rag/README.md)

**Zero-Shot Learning** - ಉದಾಹರಣೆಗಳಿಲ್ಲದೆ ಕಾರ್ಯಗಳನ್ನು ನಡೆಸುವುದು. [ಮಾಡ್ಯೂಲ್ 02](../02-prompt-engineering/README.md)

## ಪ್ರಾಂಪ್‌ಟ್ ಎಂಜಿನಿಯರಿಂಗ್ - [മಾಡ್ಯೂಲ್ 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - ಉತ್ತಮ ನಿಖರತೆಗೆ ಹಂತಹಂತದ ತರ್ಕನಿರ್ಮಾಣ.

**Constrained Output** - ನಿರ್ದಿಷ್ಟ ಫಾರ್ಮ್ಯಾಟ್ ಅಥವಾ ರಚನೆಯನ್ನು ಜಾರಿಗೆ ಹಾಕುವುದು.

**High Eagerness** - ವಿಸ್ತೃತ ತರ್ಕನಿರ್ಮಾಣಕ್ಕೆ GPT-5 ಮಾದರಿ ಮಾದರಿ.

**Low Eagerness** - ವೇಗದ ಉತ್ತರಗಳಿಗೆ GPT-5 ಮಾದರಿ ಮಾದರಿ.

**Multi-Turn Conversation** - ವಿನಿಮಯಗಳಾದ್ಯಂತ ಪ್ರಾಸಂಗಿಕತೆಯನ್ನು ಕಾಪಾಡುವುದು.

**Role-Based Prompting** - ಸಿಸ್ಟಮ್ ಸಂದೇಶಗಳ ಮೂಲಕ ಮಾದರಿಯ ವೈಯಕ್ತಿಕತೆಯನ್ನು ಹೊಂದಿಸುವುದು.

**Self-Reflection** - ಮಾದರಿ ತನ್ನ ಔಟ್ಪುಟ್ ಅನ್ನು ತಾವು ವಿಮರ್ಶಿಸಿ ಸುಧಾರಿಸುತ್ತದೆ.

**Structured Analysis** - ನಿಶ್ಚಿತ ಮೌಲ್ಯಮಾಪನ ಚೌಕಟ್ಟಿನ ಬಳಕೆ.

**Task Execution Pattern** - ಯೋಜನೆ → ಕಾರ್ಯಗತಗೊಳಿಸಿ → ಸಾರಾಂಶ.

## RAG (Retrieval-Augmented Generation) - [ಮಾಡ್ಯೂಲ್ 03](../03-rag/README.md)

**Document Processing Pipeline** - ಲೋಡ್ → ಚುঙ্ক್ → ಎम्बೆಡ್ → ಸ್ಟೋರ್.

**In-Memory Embedding Store** - ಪರೀಕ್ಷೆಗಾಗಿ非-ಸ್ಥಿರ ಸಂಗ್ರಹಣಾ ಸ್ಥಳ.

**RAG** - ಪ್ರತಿಕ್ರಿಯೆಗಳನ್ನು ಭೂಮಿಕೆಯನ್ನಾಗಿಸಲು retrieval ಅನ್ನು generation ಜೊತೆಗೆ ಸಂಯೋಜಿಸುತ್ತದೆ.

**Similarity Score** - ಅರ್ಥಾತ್ಮಕ ಸಾದೃಶ್ಯದ 0-1 ಪ್ರಮಾಣಕ.

**Source Reference** - ಪಡೆದ ವಿಷಯದ ಬಗ್ಗೆ ಮೆಟಾ‌ಡೇಟಾ.

## ಏಜೆಂಟ್‌ಗಳು ಮತ್ತು ಉಪಕರಣಗಳು - [ಮಾಡ್ಯೂಲ್ 04](../04-tools/README.md)

**@Tool Annotation** - Java ವಿಧಾನಗಳನ್ನು AI-ಕಾಲ್‌ಮಾಡಬಹುದಾದ ಉಪಕರಣಗಳként ಗುರುತಿಸುತ್ತದೆ.

**ReAct Pattern** - ಯೋಚಿ → ಕ್ರಮವೈ→ ಗಮನಿಸಿ → ಪುನರಾವರ್ತನೆ.

**Session Management** - ವಿಭಿನ್ನ ಬಳಕೆದಾರರಿಗೆ ಪ್ರತ್ಯೇಕContexts ಕಾಪಾಡುವುದು.

**Tool** - AI ಏಜೆಂಟ್ ಕರೆಸಬಹುದಾದ ಫಂಕ್ಷನ್.

**Tool Description** - ಉಪಕರಣದ ಉದ್ದೇಶ ಮತ್ತು ಪರಿಮಾಣಗಳ ದಾಖಲಾತಿ.

## Model Context Protocol (MCP) - [ಮಾಡ್ಯೂಲ್ 05](../05-mcp/README.md)

**MCP** - AI ಅಪ್ಲಿಕೇಶನ್ಗಳನ್ನು ಹೊರಗಿನ ಉಪಕರಣಗಳಿಗೆ ಸಂಪರ್ಕಿಸುವ ಮಾನಕ.

**MCP Client** - MCP ಸರ್ವರ್‌ಗಳಿಗೆ ಸಂಪರ್ಕಿಸುವ ಅಪ್ಲಿಕೇಶನ್.

**MCP Server** - MCP ಮೂಲಕ ಉಪಕರಣಗಳನ್ನು ಹೊರಬರುತ್ತಿರುವ ಸೇವೆ.

**Stdio Transport** - stdin/stdout ಮೂಲಕ subprocess ಆಗಿ ಸರ್ವರ್.

**Tool Discovery** - ಲಭ್ಯವಿರುವ ಉಪಕರಣಗಳನ್ನು ತಿಳಿಯಲು ಕ್ಲೈಯಂಟ್ ಸರ್ವರ್‌ನ್ನು ಪ್ರಶ್ನಿಸುತ್ತದೆ.

## Azure ಸೇವೆಗಳು - [ಮಾಡ್ಯೂಲ್ 01](../01-introduction/README.md)

**Azure AI Search** - ವೆಕ್ಟರ್ ಸಾಮರ್ಥ್ಯಗಳೊಂದಿಗೆ ಕ್ಲೌಡ್ ಹುಡುಕಾಟ. [ಮಾಡ್ಯೂಲ್ 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure ಸಂಪನ್ಮೂಲಗಳನ್ನು ಡೆಪ್ಲಾಯ್ ಮಾಡುತ್ತದೆ.

**Azure OpenAI** - Microsoft‌ನ ಎಂಟರ್ಪ್ರೈಸ್ AI ಸೇವೆ.

**Bicep** - Azure infrastructure-as-code ಭಾಷೆ. [ಇನ್‌ಫ್ರಾಸ್ಟ್ರಕ್ಚರ್ ಮಾರ್ಗದರ್ಶಿ](../01-introduction/infra/README.md)

**Deployment Name** - Azure ನಲ್ಲಿ ಮಾದರಿ ನಿಯೋಜನೆಗೆ ಇರುವ ಹೆಸರು.

**GPT-5** - ತರ್ಕ ನಿಯಂತ್ರಣದೊಂದಿಗೆ ನವೀನ OpenAI ಮಾದರಿ. [ಮಾಡ್ಯೂಲ್ 02](../02-prompt-engineering/README.md)

## ಪರೀಕ್ಷೆ ಮತ್ತು ಅಭಿವೃದ್ಧಿ - [ಪರೀಕ್ಷೆ ಮಾರ್ಗದರ್ಶಿ](TESTING.md)

**Dev Container** - ಕಂಟೈನರೈಜ್ಡ್ ಅಭಿವೃದ್ಧಿ ಪರಿಸರ. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - ಉಚಿತ AI ಮಾದರಿ ಪ್ಲೇಗ್ರೌಂಡ್. [ಮಾಡ್ಯೂಲ್ 00](../00-quick-start/README.md)

**In-Memory Testing** - ಇನ್- ಮೆಮರಿ ಸಂಗ್ರಹಣೆಯೊಂದಿಗೆ ಪರೀಕ್ಷೆ.

**Integration Testing** - ವಾಸ್ತವಿಕ ಇನ್ಫ್ರಾಸ್ಟ್ರಕ್ಚರ್‍ನ್ನು ಬಳಸಿ ಪರೀಕ್ಷೆ.

**Maven** - Java ಬಿಲ್ಡ್ ಸ್ವಯಂಚಾಲಿತ ಸಾಧನ.

**Mockito** - Java ಮಾಕ್ ಮಾಡುವ ಫ್ರೇಮ್ವರ್ಕ್.

**Spring Boot** - Java ಅಪ್ಲಿಕೇಶನ್ ಫ್ರೇಮ್ವರ್ಕ್. [ಮಾಡ್ಯೂಲ್ 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
ಜವಾಬ್ದಾರಿ ನಿರಾಕರಣೆ:
ಈ ದಸ್ತಾವೇಜನ್ನು AI ಅನುವಾದ ಸೇವೆ [Co-op Translator](https://github.com/Azure/co-op-translator) ಬಳಸಿ ಅನುವಾದಿಸಲಾಗಿದೆ. ನಾವು ಶುದ್ಧತೆಯತ್ತ ಪ್ರಯತ್ನಿಸಿದರೂ, ಸ್ವಯಂಚಾಲಿತ ಅನುವಾದಗಳಲ್ಲಿ ದೋಷಗಳು ಅಥವಾ ಅಸಮರ್ಪಕತೆಗಳು ಇರಬಹುದೆಂಬದರ ಬಗ್ಗೆ ದಯವಿಟ್ಟು ತಿಳಿದುಕೊಳ್ಳಿ. ಮೂಲ ಭಾಷೆಯಲ್ಲಿರುವ ಮೂಲ ದಸ್ತಾವೇಜನ್ನು ಅಧಿಕೃತ ಮೂಲವಾಗಿಯೇ ಪರಿಗಣಿಸಬೇಕು. ಮುಖ್ಯ ಅಥವಾ ಗಂಭೀರ ಮಾಹಿತಿಗಾಗಿ ವೃತ್ತಿಪರ ಮಾನವ अनುವಾದವನ್ನು ಶಿಫಾರಸು ಮಾಡಲಾಗುತ್ತದೆ. ಈ ಅನುವಾದವನ್ನು ಬಳಸಿ ಉಂಟಾದ ಯಾವುದೇ ಅಸಮಜ್ಜಣೆಗಳು ಅಥವಾ ತಪ್ಪಾಗಿ ಅರ್ಥಮಾಡಿಕೊಳ್ಳುವಿಕೆಗೆ ನಾವು ಹೊಣೆಗಾರರಲ್ಲ.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->