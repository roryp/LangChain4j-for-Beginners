<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:15:14+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "tl"
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

Mabilisang sanggunian para sa mga termino at konsepto na ginagamit sa buong kurso.

## Core Concepts

**AI Agent** - Sistema na gumagamit ng AI upang mag-isip at kumilos nang autonomously. [Module 04](../04-tools/README.md)

**Chain** - Sunod-sunod na mga operasyon kung saan ang output ay pinapasok sa susunod na hakbang.

**Chunking** - Paghahati ng mga dokumento sa mas maliliit na bahagi. Karaniwan: 300-500 tokens na may overlap. [Module 03](../03-rag/README.md)

**Context Window** - Pinakamataas na bilang ng tokens na kayang iproseso ng modelo. GPT-5: 400K tokens.

**Embeddings** - Numerikal na mga vector na kumakatawan sa kahulugan ng teksto. [Module 03](../03-rag/README.md)

**Function Calling** - Ang modelo ay bumubuo ng istrukturadong mga kahilingan upang tawagan ang mga panlabas na function. [Module 04](../04-tools/README.md)

**Hallucination** - Kapag ang mga modelo ay bumubuo ng maling ngunit kapani-paniwalang impormasyon.

**Prompt** - Tekstong input sa isang language model. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Paghahanap ayon sa kahulugan gamit ang embeddings, hindi mga keyword. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: walang memorya. Stateful: nagpapanatili ng kasaysayan ng pag-uusap. [Module 01](../01-introduction/README.md)

**Tokens** - Pangunahing yunit ng teksto na pinoproseso ng mga modelo. Nakakaapekto sa gastos at limitasyon. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sunud-sunod na pagpapatakbo ng mga tool kung saan ang output ay nagbibigay-alam sa susunod na tawag. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Lumilikha ng type-safe na mga interface ng AI service.

**OpenAiOfficialChatModel** - Pinagsamang kliyente para sa OpenAI at Azure OpenAI na mga modelo.

**OpenAiOfficialEmbeddingModel** - Lumilikha ng embeddings gamit ang OpenAI Official client (sumusuporta sa parehong OpenAI at Azure OpenAI).

**ChatModel** - Pangunahing interface para sa mga language model.

**ChatMemory** - Nagpapanatili ng kasaysayan ng pag-uusap.

**ContentRetriever** - Naghahanap ng mga kaugnay na bahagi ng dokumento para sa RAG.

**DocumentSplitter** - Naghahati ng mga dokumento sa mga bahagi.

**EmbeddingModel** - Nagko-convert ng teksto sa mga numerikal na vector.

**EmbeddingStore** - Nag-iimbak at kumukuha ng mga embeddings.

**MessageWindowChatMemory** - Nagpapanatili ng sliding window ng mga kamakailang mensahe.

**PromptTemplate** - Lumilikha ng mga reusable na prompt na may `{{variable}}` na mga placeholder.

**TextSegment** - Bahagi ng teksto na may metadata. Ginagamit sa RAG.

**ToolExecutionRequest** - Kumakatawan sa kahilingan para sa pagpapatakbo ng tool.

**UserMessage / AiMessage / SystemMessage** - Mga uri ng mensahe sa pag-uusap.

## AI/ML Concepts

**Few-Shot Learning** - Pagbibigay ng mga halimbawa sa mga prompt. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Mga AI model na sinanay sa malawak na datos ng teksto.

**Reasoning Effort** - Parameter ng GPT-5 na kumokontrol sa lalim ng pag-iisip. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Kumokontrol sa randomness ng output. Mababa=deterministic, mataas=malikhain.

**Vector Database** - Espesyal na database para sa mga embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Pagsasagawa ng mga gawain nang walang mga halimbawa. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Hakbang-hakbang na pangangatwiran para sa mas mahusay na katumpakan.

**Constrained Output** - Pagpapatupad ng tiyak na format o istruktura.

**High Eagerness** - Pattern ng GPT-5 para sa masusing pangangatwiran.

**Low Eagerness** - Pattern ng GPT-5 para sa mabilisang sagot.

**Multi-Turn Conversation** - Pagpapanatili ng konteksto sa mga palitan.

**Role-Based Prompting** - Pagtatakda ng persona ng modelo sa pamamagitan ng mga system message.

**Self-Reflection** - Pagsusuri at pagpapabuti ng output ng modelo.

**Structured Analysis** - Nakapirming balangkas ng pagsusuri.

**Task Execution Pattern** - Plano → Isagawa → Buodin.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Load → chunk → embed → store.

**In-Memory Embedding Store** - Hindi permanenteng imbakan para sa pagsubok.

**RAG** - Pinagsasama ang retrieval at generation upang maging batayan ang mga sagot.

**Similarity Score** - Sukatan (0-1) ng semantikong pagkakatulad.

**Source Reference** - Metadata tungkol sa nakuha na nilalaman.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Nagmamarka ng mga Java method bilang AI-callable na mga tool.

**ReAct Pattern** - Reason → Act → Observe → Repeat.

**Session Management** - Hiwa-hiwalay na mga konteksto para sa iba't ibang user.

**Tool** - Function na maaaring tawagin ng AI agent.

**Tool Description** - Dokumentasyon ng layunin at mga parameter ng tool.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**Docker Transport** - MCP server sa loob ng Docker container.

**MCP** - Pamantayan para sa pagkonekta ng mga AI app sa mga panlabas na tool.

**MCP Client** - Aplikasyon na kumokonekta sa mga MCP server.

**MCP Server** - Serbisyo na nag-eexpose ng mga tool sa pamamagitan ng MCP.

**Server-Sent Events (SSE)** - Streaming mula server papunta sa client gamit ang HTTP.

**Stdio Transport** - Server bilang subprocess gamit ang stdin/stdout.

**Streamable HTTP Transport** - HTTP na may SSE para sa real-time na komunikasyon.

**Tool Discovery** - Pagtatanong ng client sa server para sa mga available na tool.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloud search na may kakayahan sa vector. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Nagde-deploy ng mga Azure resource.

**Azure OpenAI** - Enterprise AI service ng Microsoft.

**Bicep** - Wika para sa Azure infrastructure-as-code. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Pangalan para sa deployment ng modelo sa Azure.

**GPT-5** - Pinakabagong OpenAI model na may kontrol sa pangangatwiran. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Containerized na kapaligiran para sa development. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Libreng playground para sa AI model. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Pagsubok gamit ang in-memory na imbakan.

**Integration Testing** - Pagsubok gamit ang totoong imprastruktura.

**Maven** - Java build automation tool.

**Mockito** - Java mocking framework.

**Spring Boot** - Java application framework. [Module 01](../01-introduction/README.md)

**Testcontainers** - Docker containers sa mga pagsubok.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Paalala**:
Ang dokumentong ito ay isinalin gamit ang AI translation service na [Co-op Translator](https://github.com/Azure/co-op-translator). Bagamat nagsusumikap kami para sa katumpakan, pakatandaan na ang mga awtomatikong pagsasalin ay maaaring maglaman ng mga pagkakamali o di-tumpak na impormasyon. Ang orihinal na dokumento sa orihinal nitong wika ang dapat ituring na pangunahing sanggunian. Para sa mahahalagang impormasyon, inirerekomenda ang propesyonal na pagsasalin ng tao. Hindi kami mananagot sa anumang hindi pagkakaunawaan o maling interpretasyon na maaaring magmula sa paggamit ng pagsasaling ito.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->