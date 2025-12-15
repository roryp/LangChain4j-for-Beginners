<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:15:59+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sw"
}
-->
# Kamusi ya LangChain4j

## Jedwali la Yaliyomo

- [Madhumuni ya Msingi](../../../docs)
- [Vipengele vya LangChain4j](../../../docs)
- [Madhumuni ya AI/ML](../../../docs)
- [Uhandisi wa Prompt](../../../docs)
- [RAG (Uzalishaji Ulioboreshwa kwa Urejeshaji)](../../../docs)
- [Wakala na Vifaa](../../../docs)
- [Itifaki ya Muktadha wa Mfano (MCP)](../../../docs)
- [Huduma za Azure](../../../docs)
- [Upimaji na Maendeleo](../../../docs)

Marejeleo ya haraka kwa maneno na dhana zinazotumika katika kozi nzima.

## Madhumuni ya Msingi

**AI Agent** - Mfumo unaotumia AI kufikiri na kutenda kwa uhuru. [Module 04](../04-tools/README.md)

**Chain** - Mfululizo wa operesheni ambapo matokeo huingiza hatua inayofuata.

**Chunking** - Kugawanya nyaraka katika vipande vidogo. Kawaida: tokeni 300-500 zenye mchanganyiko. [Module 03](../03-rag/README.md)

**Context Window** - Tokeni nyingi zaidi ambazo mfano unaweza kushughulikia. GPT-5: tokeni 400K.

**Embeddings** - Vektori za nambari zinazoonyesha maana ya maandishi. [Module 03](../03-rag/README.md)

**Function Calling** - Mfano hutengeneza maombi yaliyopangwa kwa ajili ya kuita kazi za nje. [Module 04](../04-tools/README.md)

**Hallucination** - Wakati mifano hutengeneza taarifa zisizo sahihi lakini zinazoonekana kuwa za kweli.

**Prompt** - Ingizo la maandishi kwa mfano wa lugha. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Utafutaji kwa maana kwa kutumia embeddings, si maneno muhimu. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: haina kumbukumbu. Stateful: inahifadhi historia ya mazungumzo. [Module 01](../01-introduction/README.md)

**Tokens** - Vitengo vya msingi vya maandishi ambavyo mifano hushughulikia. Huthiri gharama na mipaka. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Utekelezaji wa zana kwa mfululizo ambapo matokeo huathiri wito lijalo. [Module 04](../04-tools/README.md)

## Vipengele vya LangChain4j

**AiServices** - Huunda interfaces za huduma za AI zenye usalama wa aina.

**OpenAiOfficialChatModel** - Mteja mmoja kwa mfano wa OpenAI na Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Huunda embeddings kwa kutumia mteja rasmi wa OpenAI (huunga mkono OpenAI na Azure OpenAI).

**ChatModel** - Interface kuu kwa mifano ya lugha.

**ChatMemory** - Huhifadhi historia ya mazungumzo.

**ContentRetriever** - Hupata vipande vya nyaraka vinavyohusiana kwa RAG.

**DocumentSplitter** - Hugawanya nyaraka katika vipande.

**EmbeddingModel** - Hubadilisha maandishi kuwa vektori za nambari.

**EmbeddingStore** - Huhifadhi na kurejesha embeddings.

**MessageWindowChatMemory** - Huhifadhi dirisha linalosogezwa la ujumbe wa hivi karibuni.

**PromptTemplate** - Huunda prompts zinazoweza kutumika tena zenye sehemu za `{{variable}}`.

**TextSegment** - Kipande cha maandishi chenye metadata. Kinatumika katika RAG.

**ToolExecutionRequest** - Huonyesha ombi la utekelezaji wa zana.

**UserMessage / AiMessage / SystemMessage** - Aina za ujumbe wa mazungumzo.

## Madhumuni ya AI/ML

**Few-Shot Learning** - Kutoa mifano katika prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Mifano ya AI iliyofunzwa kwa data kubwa ya maandishi.

**Reasoning Effort** - Kigezo cha GPT-5 kinachodhibiti kina cha kufikiri. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Hudhibiti nasibu ya matokeo. Chini=hakika, juu=ubunifu.

**Vector Database** - Hifadhidata maalum kwa embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Kutekeleza kazi bila mifano. [Module 02](../02-prompt-engineering/README.md)

## Uhandisi wa Prompt - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Kufikiri hatua kwa hatua kwa usahihi bora.

**Constrained Output** - Kulazimisha muundo au mfumo maalum.

**High Eagerness** - Mifumo ya GPT-5 kwa kufikiri kwa kina.

**Low Eagerness** - Mifumo ya GPT-5 kwa majibu ya haraka.

**Multi-Turn Conversation** - Kuhifadhi muktadha katika mabadilishano.

**Role-Based Prompting** - Kuweka tabia ya mfano kupitia ujumbe wa mfumo.

**Self-Reflection** - Mfano hujipima na kuboresha matokeo yake.

**Structured Analysis** - Mfumo thabiti wa tathmini.

**Task Execution Pattern** - Panga → Tekeleza → Fupisha.

## RAG (Uzalishaji Ulioboreshwa kwa Urejeshaji) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Pakua → gawanya → weka embedding → hifadhi.

**In-Memory Embedding Store** - Hifadhi isiyo ya kudumu kwa ajili ya upimaji.

**RAG** - Kuunganisha urejeshaji na uzalishaji ili kuimarisha majibu.

**Similarity Score** - Kipimo (0-1) cha ufananishi wa maana.

**Source Reference** - Metadata kuhusu yaliyorejeshwa.

## Wakala na Vifaa - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Inaweka alama kwa njia za Java kama zana zinazoweza kuitwa na AI.

**ReAct Pattern** - Fikiri → Tenda → Angalia → Rudia.

**Session Management** - Muktadha tofauti kwa watumiaji tofauti.

**Tool** - Kazi ambayo wakala wa AI anaweza kuita.

**Tool Description** - Nyaraka za kusudi na vigezo vya zana.

## Itifaki ya Muktadha wa Mfano (MCP) - [Module 05](../05-mcp/README.md)

**Docker Transport** - Seva ya MCP ndani ya kontena la Docker.

**MCP** - Kiwango cha kuunganisha programu za AI na zana za nje.

**MCP Client** - Programu inayounganisha na seva za MCP.

**MCP Server** - Huduma inayotoa zana kupitia MCP.

**Server-Sent Events (SSE)** - Upelekaji wa data kutoka seva hadi mteja kwa HTTP.

**Stdio Transport** - Seva kama mchakato mdogo kupitia stdin/stdout.

**Streamable HTTP Transport** - HTTP yenye SSE kwa mawasiliano ya wakati halisi.

**Tool Discovery** - Mteja huuliza seva kuhusu zana zinazopatikana.

## Huduma za Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Utafutaji wa wingu wenye uwezo wa vektori. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Hupeleka rasilimali za Azure.

**Azure OpenAI** - Huduma ya AI ya biashara ya Microsoft.

**Bicep** - Lugha ya Azure ya miundombinu kama msimbo. [Mwongozo wa Miundombinu](../01-introduction/infra/README.md)

**Deployment Name** - Jina la uenezaji wa mfano katika Azure.

**GPT-5** - Mfano wa hivi karibuni wa OpenAI wenye udhibiti wa kufikiri. [Module 02](../02-prompt-engineering/README.md)

## Upimaji na Maendeleo - [Mwongozo wa Upimaji](TESTING.md)

**Dev Container** - Mazingira ya maendeleo yaliyokontenedwa. [Usanidi](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Uwanja wa bure wa mfano wa AI. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Upimaji kwa kutumia hifadhi ya kumbukumbu.

**Integration Testing** - Upimaji kwa kutumia miundombinu halisi.

**Maven** - Zana ya ujenzi wa Java.

**Mockito** - Mfumo wa kuiga wa Java.

**Spring Boot** - Mfumo wa programu za Java. [Module 01](../01-introduction/README.md)

**Testcontainers** - Kontena za Docker katika majaribio.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Kiarifu cha Kukataa**:
Hati hii imetafsiriwa kwa kutumia huduma ya tafsiri ya AI [Co-op Translator](https://github.com/Azure/co-op-translator). Ingawa tunajitahidi kwa usahihi, tafadhali fahamu kuwa tafsiri za kiotomatiki zinaweza kuwa na makosa au upungufu wa usahihi. Hati ya asili katika lugha yake ya asili inapaswa kuchukuliwa kama chanzo cha mamlaka. Kwa taarifa muhimu, tafsiri ya kitaalamu ya binadamu inapendekezwa. Hatubebei dhamana kwa kutoelewana au tafsiri potofu zinazotokana na matumizi ya tafsiri hii.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->