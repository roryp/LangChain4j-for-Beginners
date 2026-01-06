<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:23:02+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "tl"
}
-->
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

Mabilis na sanggunian para sa mga termino at konsepto na ginagamit sa buong kurso.

## Core Concepts

**AI Agent** - Sistema na gumagamit ng AI para mag-isip at kumilos ng autonomously. [Module 04](../04-tools/README.md)

**Chain** - Sunod-sunod na mga operasyon kung saan ang output ay pinapasok sa susunod na hakbang.

**Chunking** - Paghahati-hati ng mga dokumento sa mas maliliit na bahagi. Karaniwan: 300-500 tokens na may overlap. [Module 03](../03-rag/README.md)

**Context Window** - Pinakamalaking bilang ng tokens na kayang iproseso ng isang modelo. GPT-5: 400K tokens.

**Embeddings** - Numerikal na mga vector na kumakatawan sa kahulugan ng teksto. [Module 03](../03-rag/README.md)

**Function Calling** - Ang modelo ay bumubuo ng estrukturadong mga kahilingan para tumawag sa mga panlabas na function. [Module 04](../04-tools/README.md)

**Hallucination** - Kapag ang mga modelo ay bumubuo ng maling ngunit kapani-paniwalang impormasyon.

**Prompt** - Tekstong input sa isang language model. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Paghahanap ayon sa kahulugan gamit ang embeddings, hindi keywords. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: walang memorya. Stateful: nagtatalaga ng kasaysayan ng pag-uusap. [Module 01](../01-introduction/README.md)

**Tokens** - Mga pangunahing yunit ng teksto na pinoproseso ng mga modelo. Nakakaapekto sa gastos at mga limitasyon. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sunud-sunod na pagpapatakbo ng mga tool kung saan ang output ay nag-iinform sa susunod na tawag. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Lumilikha ng type-safe na mga interface para sa AI services.

**OpenAiOfficialChatModel** - Pinag-isang client para sa mga modelo ng OpenAI at Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Lumilikha ng embeddings gamit ang OpenAI Official client (sumusuporta sa parehong OpenAI at Azure OpenAI).

**ChatModel** - Pangunahing interface para sa mga language model.

**ChatMemory** - Nangangalaga ng kasaysayan ng pag-uusap.

**ContentRetriever** - Naghahanap ng mga may kaugnayang bahagi ng dokumento para sa RAG.

**DocumentSplitter** - Nagbubukas ng mga dokumento sa mga chunk.

**EmbeddingModel** - Nagko-convert ng teksto sa numerikal na mga vector.

**EmbeddingStore** - Nagtatabi at kumukuha ng mga embeddings.

**MessageWindowChatMemory** - Nangangalaga ng sliding window ng mga kamakailang mensahe.

**PromptTemplate** - Lumilikha ng mga reusable na prompt na may `{{variable}}` na placeholders.

**TextSegment** - Bahagi ng teksto na may metadata. Ginagamit sa RAG.

**ToolExecutionRequest** - Nagrerepresenta ng kahilingan para sa pagpapatakbo ng tool.

**UserMessage / AiMessage / SystemMessage** - Mga uri ng mensahe sa pag-uusap.

## AI/ML Concepts

**Few-Shot Learning** - Nagbibigay ng mga halimbawa sa mga prompt. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Mga modelo ng AI na sinanay gamit ang napakalaking bilang ng datos ng teksto.

**Reasoning Effort** - Parameter ng GPT-5 na kumokontrol sa lalim ng pag-iisip. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Kumokontrol sa pagiging random ng output. Mababa=deteriministiko, mataas=malikhain.

**Vector Database** - Espesyalisadong database para sa embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Pagsasagawa ng mga gawain nang walang mga halimbawa. [Module 02](../02-prompt-engineering/README.md)

## Guardrails - [Module 00](../00-quick-start/README.md)

**Defense in Depth** - Multi-layer na diskarte sa seguridad na pinagsasama ang application-level guardrails at mga safety filter ng provider.

**Hard Block** - Ang provider ay bumabalik ng HTTP 400 error para sa malubhang paglabag sa nilalaman.

**InputGuardrail** - Interface ng LangChain4j para i-validate ang input ng user bago pa ito maipasa sa LLM. Nakakatipid ito sa gastos at latency sa pamamagitan ng pag-block ng mga mapanganib na prompt nang maaga.

**InputGuardrailResult** - Uri ng return value para sa guardrail validation: `success()` o `fatal("dahilan")`.

**OutputGuardrail** - Interface para i-validate ang mga sagot ng AI bago ibalik sa mga user.

**Provider Safety Filters** - Mga built-in na content filter mula sa mga AI provider (e.g., GitHub Models) na humuhuli ng mga paglabag sa API level.

**Soft Refusal** - Maingat na pagtanggi ng modelo na sagutin nang hindi nagbato ng error.

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Hakbang-hakbang na pangangatwiran para sa mas mahusay na katumpakan.

**Constrained Output** - Pagpapatupad ng partikular na format o estruktura.

**High Eagerness** - Pattern ng GPT-5 para sa masusing pangangatwiran.

**Low Eagerness** - Pattern ng GPT-5 para sa mabilisang sagot.

**Multi-Turn Conversation** - Pangangalaga sa konteksto sa buong palitan ng usapan.

**Role-Based Prompting** - Pagsasaayos ng persona ng modelo sa pamamagitan ng mga system message.

**Self-Reflection** - Pagsusuri at pagpapabuti ng output ng modelo.

**Structured Analysis** - Nakatakdang balangkas para sa pagsusuri.

**Task Execution Pattern** - Plano → Isagawa → Buodin.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - I-load → hatiin → i-embed → itabi.

**In-Memory Embedding Store** - Hindi-persistenteng imbakan para sa pagsusuri.

**RAG** - Pinagsasama ang retrieval at generation upang magkaroon ng basehan ang mga sagot.

**Similarity Score** - Sukat (0-1) ng pangsemantikong pagkakatulad.

**Source Reference** - Metadata tungkol sa nakuha na nilalaman.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Nagmamarka sa mga Java method bilang mga AI-callable na tool.

**ReAct Pattern** - Mag-isip → Kumilos → Obserbahan → Uulitin.

**Session Management** - Hiwa-hiwalay na mga konteksto para sa iba't ibang user.

**Tool** - Function na maaaring tawagin ng AI agent.

**Tool Description** - Dokumentasyon ng layunin at mga parameter ng tool.

## Agentic Module - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - Nagmamarka ng mga interface bilang AI agents na may deklaratibong depinisyon ng pag-uugali.

**Agent Listener** - Hook para sa pagmamanman ng pagpapatupad ng agent sa pamamagitan ng `beforeAgentInvocation()` at `afterAgentInvocation()`.

**Agentic Scope** - Pinagsasaluhang memorya kung saan iniimbak ng mga agent ang mga output gamit ang `outputKey` para magamit ng mga downstream agent.

**AgenticServices** - Pabrika para sa paglikha ng mga agent gamit ang `agentBuilder()` at `supervisorBuilder()`.

**Conditional Workflow** - Rutin batay sa mga kondisyon patungo sa iba't ibang espesyalistang agent.

**Human-in-the-Loop** - Workflow pattern na naglalagay ng mga human checkpoint para sa pag-apruba o pagsusuri ng nilalaman.

**langchain4j-agentic** - Maven dependency para sa deklaratibong paggawa ng agent (eksperimento).

**Loop Workflow** - Uulitin ang pagpapatupad ng agent hanggang matugunan ang isang kondisyon (hal., quality score ≥ 0.8).

**outputKey** - Parameter ng agent annotation na nagsasaad kung saan itatabi ang mga resulta sa Agentic Scope.

**Parallel Workflow** - Patakbuhin ang maraming agent nang sabay-sabay para sa mga independiyenteng gawain.

**Response Strategy** - Paano bumuo ang supervisor ng huling sagot: LAST, SUMMARY, o SCORED.

**Sequential Workflow** - Isakatuparan ang mga agent nang sunud-sunod kung saan ang output ay dumadaloy sa susunod na hakbang.

**Supervisor Agent Pattern** - Advanced na pattern ng agentic kung saan ang supervisor LLM ang dinamiko na nagpasiya kung aling mga sub-agent ang tatawagin.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - Maven dependency para sa integrasyon ng MCP sa LangChain4j.

**MCP** - Model Context Protocol: pamantayan para sa pagkonekta ng AI apps sa mga panlabas na tool. Isang beses lang gamitin, saanman.

**MCP Client** - Application na nakakonekta sa MCP server upang tuklasin at gamitin ang mga tool.

**MCP Server** - Serbisyo na naglalantad ng mga tool sa pamamagitan ng MCP na may malinaw na paglalarawan at schema ng mga parameter.

**McpToolProvider** - Bahagi ng LangChain4j na bumabalot sa MCP tools para magamit sa AI services at agents.

**McpTransport** - Interface para sa komunikasyon ng MCP. Kasama sa implementasyon ang Stdio at HTTP.

**Stdio Transport** - Lokal na proseso na transportasyon gamit ang stdin/stdout. Kapaki-pakinabang para sa pag-access ng filesystem o command-line tools.

**StdioMcpTransport** - Implementasyon ng LangChain4j na naglalabas ng MCP server bilang subprocess.

**Tool Discovery** - Kinukuha ng client mula sa server ang mga magagamit na tool kasama ang mga paglalarawan at schema.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloud search na may kakayahan sa vector. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Nagde-deploy ng mga Azure resource.

**Azure OpenAI** - Enterprise AI service ng Microsoft.

**Bicep** - Wika para sa infrastructure-as-code sa Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Pangalan para sa deployment ng modelo sa Azure.

**GPT-5** - Pinakabagong modelo ng OpenAI na may kontrol sa reasoning. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Containerized na kapaligiran para sa pag-develop. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Libreng AI model playground. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Pagsusuri gamit ang in-memory na imbakan.

**Integration Testing** - Pagsusuri gamit ang totoong imprastraktura.

**Maven** - Tool para sa automation ng Java build.

**Mockito** - Framework para sa mocking sa Java.

**Spring Boot** - Java application framework. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Paunawa**:
Ang dokumentong ito ay isinalin gamit ang AI translation service na [Co-op Translator](https://github.com/Azure/co-op-translator). Bagamat aming pinagsisikapan ang katumpakan, mangyaring tandaan na ang mga awtomatikong pagsasalin ay maaaring maglaman ng mga pagkakamali o maling interpretasyon. Ang orihinal na dokumento sa orihinal nitong wika ang dapat ituring na pangunahing sanggunian. Para sa mahahalagang impormasyon, inirerekomenda ang propesyonal na pagsasalin ng tao. Hindi kami mananagot sa anumang hindi pagkakaunawaan o maling interpretasyon na dulot ng paggamit ng pagsasaling ito.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->