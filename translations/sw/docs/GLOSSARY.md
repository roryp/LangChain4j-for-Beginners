<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:24:56+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sw"
}
-->
# Kamusi ya LangChain4j

## Jedwali la Yaliyomo

- [Madhumuni ya Msingi](../../../docs)
- [Vipengele vya LangChain4j](../../../docs)
- [Madhumuni ya AI/ML](../../../docs)
- [Mikakati ya Ulinzi](../../../docs)
- [Uhandisi wa Prompt](../../../docs)
- [RAG (Uundaji unaoendeshwa na Urejeshaji)](../../../docs)
- [Wakala na Vifaa](../../../docs)
- [Moduli ya Agentic](../../../docs)
- [Itifaki ya Muktadha wa Mfano (MCP)](../../../docs)
- [Huduma za Azure](../../../docs)
- [Upimaji na Maendeleo](../../../docs)

Rejea ya haraka kwa maneno na dhana zinazotumiwa katika kozi nzima.

## Madhumuni ya Msingi

**AI Agent** - Mfumo unaotumia AI kufikiria na kutenda kwa uhuru. [Moduli 04](../04-tools/README.md)

**Chain** - Mfuatano wa operesheni ambapo matokeo huchangia hatua inayofuata.

**Chunking** - Kugawanya hati katika vipande vidogo. Kawaida: tokeni 300-500 zenye kurudia. [Moduli 03](../03-rag/README.md)

**Context Window** - Idadi kubwa ya tokeni mfano unaweza kusindika. GPT-5: tokeni 400K.

**Embeddings** - Vektor za nambari zinazoonyesha maana ya maandishi. [Moduli 03](../03-rag/README.md)

**Function Calling** - Mfano huzaa maombi yaliyo sanifu ya kuita kazi za nje. [Moduli 04](../04-tools/README.md)

**Hallucination** - Wakati mifano huzaa taarifa zisizo sahihi lakini zinazoonekana kweli.

**Prompt** - Mwingiliano wa maandishi kwa mfano wa lugha. [Moduli 02](../02-prompt-engineering/README.md)

**Semantic Search** - Utafutaji kwa maana ukitumia embeddings, si maneno makuu. [Moduli 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: hakuna kumbukumbu. Stateful: huhifadhi historia ya mazungumzo. [Moduli 01](../01-introduction/README.md)

**Tokens** - Sehemu za msingi za maandishi zinazosindikwa na mifano. Huhusiana na gharama na vizingiti. [Moduli 01](../01-introduction/README.md)

**Tool Chaining** - Utekelezaji wa zana kwa mfuatano ambapo matokeo hutumika katika wito lijalo. [Moduli 04](../04-tools/README.md)

## Vipengele vya LangChain4j

**AiServices** - Huunda interfaces salama za aina kwa huduma za AI.

**OpenAiOfficialChatModel** - Mteja muunganiko wa mifano ya OpenAI na Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Huunda embeddings kwa kutumia mteja wa OpenAI Rasmi (yanaunga mkono OpenAI na Azure OpenAI).

**ChatModel** - Interface kuu kwa mifano ya lugha.

**ChatMemory** - Huhifadhi historia ya mazungumzo.

**ContentRetriever** - Huita vipande vya hati vinavyofaa kwa RAG.

**DocumentSplitter** - Huvunja hati katika vipande.

**EmbeddingModel** - Hubadilisha maandishi kuwa vektor za nambari.

**EmbeddingStore** - Huhifadhi na kurejesha embeddings.

**MessageWindowChatMemory** - Huhifadhi dirisha linaloelea la ujumbe wa hivi karibuni.

**PromptTemplate** - Hutiwa prompts zinazoweza kutumika tena zikiwa na sehemu za `{{variable}}`.

**TextSegment** - Kipande cha maandishi chenye metadata. Kinatumika katika RAG.

**ToolExecutionRequest** - Huashiria ombi la kutekeleza zana.

**UserMessage / AiMessage / SystemMessage** - Aina za ujumbe wa mazungumzo.

## Madhumuni ya AI/ML

**Few-Shot Learning** - Kutoa mifano katika prompts. [Moduli 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Mifano ya AI iliyofunzwa kwa kiasi kikubwa cha data za maandishi.

**Reasoning Effort** - Kigezo cha GPT-5 kinachosimamia kina cha fikra. [Moduli 02](../02-prompt-engineering/README.md)

**Temperature** - Huidhinisha nasibu ya matokeo. Chini=hakika, juu=ubunifu.

**Vector Database** - Hifadhidata maalum kwa embeddings. [Moduli 03](../03-rag/README.md)

**Zero-Shot Learning** - Kutenda kazi bila mifano. [Moduli 02](../02-prompt-engineering/README.md)

## Mikakati ya Ulinzi - [Moduli 00](../00-quick-start/README.md)

**Defense in Depth** - Njia ya usalama ya tabaka nyingi ikipangia miongozo ya usalama ya kiwango cha programu na vichujio vya usalama vya mtoaji.

**Hard Block** - Mtoa huduma hutupa hitilafu ya HTTP 400 kwa ukiukaji mkubwa wa maudhui.

**InputGuardrail** - Interface ya LangChain4j kwa kuhakiki ingizo la mtumiaji kabla halijafikia LLM. Hutoa akiba kwa gharama na ucheleweshaji kwa kuzuia prompts hatari mapema.

**InputGuardrailResult** - Aina ya kurudisha kwa uthibitisho wa mlinzi: `success()` au `fatal("reason")`.

**OutputGuardrail** - Interface ya kuhakiki majibu ya AI kabla ya kuyarejesha kwa watumiaji.

**Provider Safety Filters** - Vichujio vya maudhui vilivyosanifiwa na watoa huduma wa AI (mfano, GitHub Models) vinavyoshika makosa katika ngazi ya API.

**Soft Refusal** - Mfano hukataa kwa heshima kujawabu bila kutoa hitilafu.

## Uhandisi wa Prompt - [Moduli 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Maana ya baada ya hatua kwa usahihi bora.

**Constrained Output** - Kufikia muundo maalum au muundo.

**High Eagerness** - Mabadiliko ya GPT-5 kwa fikra za kina.

**Low Eagerness** - Mabadiliko ya GPT-5 kwa majibu ya haraka.

**Multi-Turn Conversation** - Kuhifadhi muktadha katika mazungumzo.

**Role-Based Prompting** - Kuweka hadhi ya mfano kupitia ujumbe wa mfumo.

**Self-Reflection** - Mfano hujitathmini na kuboresha matokeo yake.

**Structured Analysis** - Mfumo thabiti wa tathmini.

**Task Execution Pattern** - Panga → Tekeleza → Fupisha.

## RAG (Uundaji unaoendeshwa na Urejeshaji) - [Moduli 03](../03-rag/README.md)

**Document Processing Pipeline** - Pakua → vagawanya → ingiza → hifadhi.

**In-Memory Embedding Store** - Hifadhi isiyo ya kudumu kwa ajili ya upimaji.

**RAG** - Huunganisha urejeshaji na uundaji ili kutoa majibu yenye msingi.

**Similarity Score** - Kipimo (0-1) cha ulinganifu wa maana.

**Source Reference** - Metadata kuhusu maudhui yaliyorejeshwa.

## Wakala na Vifaa - [Moduli 04](../04-tools/README.md)

**@Tool Annotation** - Inaonyesha mbinu za Java kama zana zinazoweza kuitwa na AI.

**ReAct Pattern** - Fikiria → Tenda → Chunguza → Rudia.

**Session Management** - Muktadha tofauti kwa watumiaji tofauti.

**Tool** - Kazi ambacho wakala wa AI anaweza kuitisha.

**Tool Description** - Nyaraka za madhumuni na vigezo vya zana.

## Moduli ya Agentic - [Moduli 05](../05-mcp/README.md)

**@Agent Annotation** - Inaonyesha interfaces kama wakala wa AI kwa ufafanuzi wa tabia kwa njia ya maelezo.

**Agent Listener** - Hook kwa kufuatilia utekelezaji wa wakala kupitia `beforeAgentInvocation()` na `afterAgentInvocation()`.

**Agentic Scope** - Kumbukumbu ya pamoja ambapo mawakala huhifadhi matokeo kwa kutumia `outputKey` kwa mawakala wa mbeleni.

**AgenticServices** - Kiwanda cha kuunda mawakala kwa kutumia `agentBuilder()` na `supervisorBuilder()`.

**Conditional Workflow** - Kuelekeza kulingana na masharti kwa mawakala maalum tofauti.

**Human-in-the-Loop** - Mfano wa mchakato unaoingiza hatua za kibinadamu kwa idhini au ukaguzi wa maudhui.

**langchain4j-agentic** - Tegemezi la Maven kwa ujenzi wa wakala kwa njia ya maelezo (jaribio).

**Loop Workflow** - Rudia utekelezaji wa wakala hadi sharti litimizwe (k.m., alama ya ubora ≥ 0.8).

**outputKey** - Kipengele cha maelezo ya wakala kinachoonyesha mahali ambapo matokeo huhifadhiwa katika Agentic Scope.

**Parallel Workflow** - Endesha mawakala wengi kwa wakati mmoja kwa kazi huru.

**Response Strategy** - Jinsi msimamizi anavyobuni jibu la mwisho: LAST, SUMMARY, au SCORED.

**Sequential Workflow** - Tekeleza mawakala kwa mpangilio ambapo matokeo huingia hatua inayofuata.

**Supervisor Agent Pattern** - Mfano wa wakala wa hali ya juu ambapo msimamizi LLM huchagua kwa nguvu mawakala ndogo wa kuitisha.

## Itifaki ya Muktadha wa Mfano (MCP) - [Moduli 05](../05-mcp/README.md)

**langchain4j-mcp** - Tegemezi la Maven kwa ujumuishaji wa MCP katika LangChain4j.

**MCP** - Itifaki ya Muktadha wa Mfano: kiwango cha kuunganisha programu za AI na zana za nje. Jenga mara moja, tumia kila mahali.

**MCP Client** - Programu inayounganisha na seva za MCP kugundua na kutumia zana.

**MCP Server** - Huduma inayotoa zana kupitia MCP kwa maelezo wazi na skimu za vigezo.

**McpToolProvider** - Kipengele cha LangChain4j kinachozunguka zana za MCP kwa matumizi katika huduma za AI na mawakala.

**McpTransport** - Interface kwa mawasiliano ya MCP. Utekelezaji ni pamoja na Stdio na HTTP.

**Stdio Transport** - Usafirishaji wa mchakato wa ndani kwa stdin/stdout. Inafaa kwa upatikanaji wa mfumo wa faili au zana za mstari wa agizo.

**StdioMcpTransport** - Utekelezaji wa LangChain4j unaozindua seva ya MCP kama mchakato mdogo.

**Tool Discovery** - Mteja huuliza seva kwa zana zinazopatikana zikiwa na maelezo na skimu.

## Huduma za Azure - [Moduli 01](../01-introduction/README.md)

**Azure AI Search** - Utafutaji wa wingu wenye uwezo wa vector. [Moduli 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Inatoa rasilimali za Azure.

**Azure OpenAI** - Huduma ya AI ya biashara ya Microsoft.

**Bicep** - Lugha ya Azure ya miundombinu kama msimbo. [Mwongozo wa Miundombinu](../01-introduction/infra/README.md)

**Deployment Name** - Jina la utekelezaji wa mfano katika Azure.

**GPT-5** - Mfano wa hivi karibuni wa OpenAI wenye udhibiti wa fikra. [Moduli 02](../02-prompt-engineering/README.md)

## Upimaji na Maendeleo - [Mwongozo wa Upimaji](TESTING.md)

**Dev Container** - Mazingira ya maendeleo yaliyokontena. [Marekebisho](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Sehemu ya bure ya majaribio ya modeli ya AI. [Moduli 00](../00-quick-start/README.md)

**In-Memory Testing** - Upimaji kwa hifadhi ya ndani.

**Integration Testing** - Upimaji kwa miundombinu halisi.

**Maven** - Zana ya otomatiki ya ujenzi wa Java.

**Mockito** - Fremu ya kuiga katika Java.

**Spring Boot** - Fremu ya programu ya Java. [Moduli 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Maelezo ya Kukataa**:
Hati hii imetafsiriwa kwa kutumia huduma ya utafsiri wa AI [Co-op Translator](https://github.com/Azure/co-op-translator). Ingawa tunajitahidi kufanikisha usahihi, tafadhali fahamu kwamba tafsiri za kiotomatiki zinaweza kuwa na makosa au upungufu wa usahihi. Hati ya awali katika lugha yake ya asili inapaswa kuzingatiwa kama chanzo cha mamlaka. Kwa taarifa muhimu, tafsiri ya kitaalamu ya binadamu inapendekezwa. Hatubeba dhamana kwa kueleweka vibaya au tafsiri zisizo sahihi zinazotokana na matumizi ya tafsiri hii.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->