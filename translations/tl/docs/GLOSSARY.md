<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T03:32:11+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "tl"
}
-->
# Glosaryo ng LangChain4j

## Talaan ng Nilalaman

- [Pangunahing Konsepto](../../../docs)
- [Mga Komponent ng LangChain4j](../../../docs)
- [Mga Konsepto ng AI/ML](../../../docs)
- [Prompt Engineering - [Modyul 02](../02-prompt-engineering/README.md)](#prompt-engineering---module-02)
- [RAG (Retrieval-Augmented Generation) - [Modyul 03](../03-rag/README.md)](#rag-retrieval-augmented-generation---module-03)
- [Mga Ahente at Tool - [Modyul 04](../04-tools/README.md)](#agents-and-tools---module-04)
- [Model Context Protocol (MCP) - [Modyul 05](../05-mcp/README.md)](#model-context-protocol-mcp---module-05)
- [Mga Serbisyo ng Azure - [Modyul 01](../01-introduction/README.md)](#azure-services---module-01)
- [Pagsubok at Pag-unlad - [Gabay sa Pagsubok](TESTING.md)](#testing-and-development---testing-guide)

Mabilisang sanggunian para sa mga termino at konsepto na ginagamit sa buong kurso.

## Core Concepts

**AI Agent** - Sistema na gumagamit ng AI para magpasiya at kumilos nang awtonomo. [Modyul 04](../04-tools/README.md)

**Chain** - Sunod-sunod na mga operasyon kung saan ang output ay ipinapasa sa susunod na hakbang.

**Chunking** - Paghahati-hati ng mga dokumento sa mas maliliit na bahagi. Karaniwan: 300-500 tokens na may overlap. [Modyul 03](../03-rag/README.md)

**Context Window** - Pinakamataas na bilang ng tokens na kayang i-process ng modelo. GPT-5: 400K tokens.

**Embeddings** - Mga numerikal na vector na kumakatawan sa kahulugan ng teksto. [Modyul 03](../03-rag/README.md)

**Function Calling** - Lumilikha ang modelo ng istrukturadong mga hiling para tumawag ng mga panlabas na function. [Modyul 04](../04-tools/README.md)

**Hallucination** - Kapag ang mga modelo ay gumagawa ng maling pero mukhang makatotohanang impormasyon.

**Prompt** - Tekstong input sa isang modelo ng wika. [Modyul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Paghahanap batay sa kahulugan gamit ang embeddings, hindi mga keyword. [Modyul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: walang memorya. Stateful: nagpapanatili ng kasaysayan ng pag-uusap. [Modyul 01](../01-introduction/README.md)

**Tokens** - Mga pangunahing yunit ng teksto na pinoproseso ng mga modelo. Nakakaapekto sa gastos at mga limitasyon. [Modyul 01](../01-introduction/README.md)

**Tool Chaining** - Sunud-sunod na pag-execute ng mga tool kung saan ang output ay humuhubog sa susunod na tawag. [Modyul 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Lumilikha ng type-safe na mga interface para sa AI service.

**OpenAiOfficialChatModel** - Pinag-isang client para sa OpenAI at Azure OpenAI na mga modelo.

**OpenAiOfficialEmbeddingModel** - Lumilikha ng embeddings gamit ang OpenAI Official client (sumusuporta sa parehong OpenAI at Azure OpenAI).

**ChatModel** - Pangunahing interface para sa mga language model.

**ChatMemory** - Nagpapanatili ng kasaysayan ng pag-uusap.

**ContentRetriever** - Naghahanap ng mga may-kinalamang chunk ng dokumento para sa RAG.

**DocumentSplitter** - Naghahati ng mga dokumento sa mga chunk.

**EmbeddingModel** - Nagko-convert ng teksto sa mga numerikal na vector.

**EmbeddingStore** - Nagtatago at kumukuha ng mga embeddings.

**MessageWindowChatMemory** - Nagpapanatili ng sliding window ng mga kamakailang mensahe.

**PromptTemplate** - Lumilikha ng mga reusable na prompt na may `{{variable}}` placeholders.

**TextSegment** - Chunk ng teksto na may metadata. Ginagamit sa RAG.

**ToolExecutionRequest** - Kumakatawan sa kahilingan ng pag-execute ng tool.

**UserMessage / AiMessage / SystemMessage** - Mga uri ng mensahe sa pag-uusap.

## AI/ML Concepts

**Few-Shot Learning** - Pagbibigay ng mga halimbawa sa mga prompt. [Modyul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Mga modelo ng AI na sinanay sa napakalawak na koleksyon ng teksto.

**Reasoning Effort** - Parameter ng GPT-5 na kumokontrol sa lalim ng pag-iisip. [Modyul 02](../02-prompt-engineering/README.md)

**Temperature** - Kumokontrol sa randomness ng output. Mababa=deterministiko, mataas=malikhain.

**Vector Database** - Espesyal na database para sa embeddings. [Modyul 03](../03-rag/README.md)

**Zero-Shot Learning** - Pagsasagawa ng mga gawain nang walang mga halimbawa. [Modyul 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modyul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Hakbang-hakbang na pag-iisip para sa mas mahusay na katumpakan.

**Constrained Output** - Pagpapatupad ng tiyak na format o istruktura.

**High Eagerness** - Pattern ng GPT-5 para sa mas masusing pag-iisip.

**Low Eagerness** - Pattern ng GPT-5 para sa mabilisang mga sagot.

**Multi-Turn Conversation** - Pagpapanatili ng konteksto sa mga magkakasunod na palitan.

**Role-Based Prompting** - Pag-set ng persona ng modelo sa pamamagitan ng mga mensahe ng sistema.

**Self-Reflection** - Ang modelo ay nagsusuri at pinapabuti ang sariling output.

**Structured Analysis** - Nakapirming balangkas ng ebalwasyon.

**Task Execution Pattern** - Planuhin → Isagawa → Ibuod.

## RAG (Retrieval-Augmented Generation) - [Modyul 03](../03-rag/README.md)

**Document Processing Pipeline** - I-load → hatiin → i-embed → i-imbak.

**In-Memory Embedding Store** - Hindi permanenteng imbakan para sa pagsubok.

**RAG** - Pinagsasama ang retrieval at generation upang gawing nakabatay sa pinagmulan ang mga tugon.

**Similarity Score** - Sukat (0-1) ng semantikong pagkakatulad.

**Source Reference** - Metadata tungkol sa nakuha na nilalaman.

## Agents and Tools - [Modyul 04](../04-tools/README.md)

**@Tool Annotation** - Nagmamarka ng mga Java method bilang mga tool na maaaring tawagin ng AI.

**ReAct Pattern** - Mag-isip → Kumilos → Obserbahan → Ulitin.

**Session Management** - Hiwa-hiwalay na mga konteksto para sa iba't ibang mga gumagamit.

**Tool** - Function na maaaring tawagin ng isang AI agent.

**Tool Description** - Dokumentasyon ng layunin at mga parameter ng tool.

## Model Context Protocol (MCP) - [Modyul 05](../05-mcp/README.md)

**MCP** - Pamantayan para sa pagkokonekta ng mga aplikasyon ng AI sa mga panlabas na tool.

**MCP Client** - Aplikasyon na kumokonekta sa mga MCP server.

**MCP Server** - Serbisyo na naglalantad ng mga tool sa pamamagitan ng MCP.

**Stdio Transport** - Server bilang subprocess gamit ang stdin/stdout.

**Tool Discovery** - Nagtatanong ang client sa server para sa mga magagamit na tool.

## Azure Services - [Modyul 01](../01-introduction/README.md)

**Azure AI Search** - Cloud search na may kakayahang vector. [Modyul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Nagde-deploy ng mga Azure resource.

**Azure OpenAI** - Enterprise AI service ng Microsoft.

**Bicep** - Wika para sa infrastructure-as-code ng Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Pangalan para sa deployment ng modelo sa Azure.

**GPT-5** - Pinakabagong modelo ng OpenAI na may kontrol sa pagre-reason. [Modyul 02](../02-prompt-engineering/README.md)

## Testing and Development - [Gabay sa Pagsubok](TESTING.md)

**Dev Container** - Containerized na kapaligiran para sa pag-develop. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Libreng playground para sa mga AI model. [Modyul 00](../00-quick-start/README.md)

**In-Memory Testing** - Pagsubok gamit ang in-memory na imbakan.

**Integration Testing** - Pagsubok gamit ang totoong imprastruktura.

**Maven** - Tool para sa automation ng build sa Java.

**Mockito** - Java mocking framework.

**Spring Boot** - Java application framework. [Modyul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Paunawa:
Ang dokumentong ito ay isinalin gamit ang serbisyong AI para sa pagsasalin na [Co-op Translator](https://github.com/Azure/co-op-translator). Bagaman sinisikap naming maging tumpak, pakitandaan na ang mga awtomatikong pagsasalin ay maaaring magkaroon ng mga pagkakamali o kakulangan sa katumpakan. Ang orihinal na dokumento sa orihinal nitong wika ang dapat ituring na pangunahing sanggunian. Para sa mahahalagang impormasyon, inirerekomenda ang propesyonal na pagsasalin ng tao. Hindi kami mananagot para sa anumang hindi pagkakaunawaan o maling interpretasyon na maaaring magmula sa paggamit ng pagsasaling ito.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->