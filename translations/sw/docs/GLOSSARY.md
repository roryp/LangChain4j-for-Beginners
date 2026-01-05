<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T03:47:48+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sw"
}
-->
# LangChain4j Kamusi ya Maneno

## Yaliyomo

- [Misingi](../../../docs)
- [Vipengele vya LangChain4j](../../../docs)
- [Dhana za AI/ML](../../../docs)
- [Uhandisi wa Prompt](../../../docs)
- [RAG (Uundaji Ulioimarishwa kwa Urejeshaji)](../../../docs)
- [Wakala na Zana](../../../docs)
- [Itifaki ya Muktadha wa Mfano (MCP)](../../../docs)
- [Huduma za Azure](../../../docs)
- [Ujaribu na Maendeleo](../../../docs)

Rejea ya haraka kwa istilahi na dhana zinazotumika katika kozi.

## Misingi

**AI Agent** - Mfumo unaotumia AI kufikiri na kutenda kwa uhuru. [Moduli 04](../04-tools/README.md)

**Chain** - Mfululizo wa operesheni ambapo pato hutumika kama ingizo kwa hatua inayofuata.

**Chunking** - Kugawanya nyaraka katika vipande vidogo. Kawaida: tokens 300-500 zikiwa na sehemu zinazofanana (overlap). [Moduli 03](../03-rag/README.md)

**Context Window** - Idadi kubwa ya tokens ambazo modeli inaweza kushughulikia. GPT-5: 400K tokens.

**Embeddings** - Vektor za nambari zinazoelezea maana ya maandishi. [Moduli 03](../03-rag/README.md)

**Function Calling** - Modeli inazalisha maombi yaliyopangwa ili kuita kazi za nje. [Moduli 04](../04-tools/README.md)

**Hallucination** - Wakati modeli zinazalisha taarifa zisizo sahihi lakini zinazoonekana kuaminika.

**Prompt** - Ingizo la maandishi kwa modeli ya lugha. [Moduli 02](../02-prompt-engineering/README.md)

**Semantic Search** - Utafutaji kwa maana kwa kutumia embeddings, si maneno muhimu. [Moduli 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: hakuna kumbukumbu. Stateful: huhifadhi historia ya mazungumzo. [Moduli 01](../01-introduction/README.md)

**Tokens** - Vitengo vya msingi vya maandishi vinavyosindikwa na modeli. Vinaathiri gharama na mipaka. [Moduli 01](../01-introduction/README.md)

**Tool Chaining** - Utekelezaji wa zana kwa mfululizo ambapo pato linaelekeza mwito unaofuata. [Moduli 04](../04-tools/README.md)

## Vipengele vya LangChain4j

**AiServices** - Inaunda interfaces za huduma za AI zenye usalama wa aina.

**OpenAiOfficialChatModel** - Mteja mmoja kwa ajili ya modeli za OpenAI na Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Inaunda embeddings kwa kutumia mteja rasmi wa OpenAI (unaunga mkono OpenAI na Azure OpenAI).

**ChatModel** - Kiolesura kuu kwa modeli za lugha.

**ChatMemory** - Huhifadhi historia ya mazungumzo.

**ContentRetriever** - Hutafuta vipande vya nyaraka vinavyofaa kwa RAG.

**DocumentSplitter** - Inagawa nyaraka kuwa vipande.

**EmbeddingModel** - Inageuza maandishi kuwa vektor za nambari.

**EmbeddingStore** - Inahifadhi na kurejesha embeddings.

**MessageWindowChatMemory** - Inadumisha dirisha linaloendelea la ujumbe wa hivi karibuni.

**PromptTemplate** - Inaunda prompts zinazoweza kutumiwa tena zenye nafasi za `{{variable}}`.

**TextSegment** - Kipande cha maandishi chenye metadata. Inatumiwa katika RAG.

**ToolExecutionRequest** - Inawakilisha ombi la utekelezaji wa zana.

**UserMessage / AiMessage / SystemMessage** - Aina za ujumbe wa mazungumzo.

## Dhana za AI/ML

**Few-Shot Learning** - Kutoa mifano ndani ya prompts. [Moduli 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Modeli za AI zilizofunzwa kwa data kubwa ya maandishi.

**Reasoning Effort** - Parameta ya GPT-5 inayodhibiti kina cha fikra. [Moduli 02](../02-prompt-engineering/README.md)

**Temperature** - Inadhibiti nasibu ya matokeo. Chini = utabiri, juu = ubunifu.

**Vector Database** - Hifadhidata maalum kwa embeddings. [Moduli 03](../03-rag/README.md)

**Zero-Shot Learning** - Kufanya kazi bila mifano. [Moduli 02](../02-prompt-engineering/README.md)

## Uhandisi wa Prompt - [Moduli 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Ufikra wa hatua kwa hatua kwa usahihi bora.

**Constrained Output** - Kulazimisha muundo au fomati maalum.

**High Eagerness** - Mtindo wa GPT-5 kwa fikra za kina.

**Low Eagerness** - Mtindo wa GPT-5 kwa majibu ya haraka.

**Multi-Turn Conversation** - Kuhifadhi muktadha katika mabadilishano.

**Role-Based Prompting** - Kuweka persona ya modeli kupitia ujumbe wa mfumo.

**Self-Reflection** - Modeli inajitathmini na kuboresha pato lake.

**Structured Analysis** - Mfumo wa tathmini uliopangwa.

**Task Execution Pattern** - Panga → Tekeleza → Muhtasari.

## RAG (Retrieval-Augmented Generation) - [Moduli 03](../03-rag/README.md)

**Document Processing Pipeline** - Pakua → gawanya → weka embeddings → hifadhi.

**In-Memory Embedding Store** - Hifadhi isiyodumu kwa ajili ya upimaji.

**RAG** - Inachanganya urejeshaji na uundaji ili kuimarisha majibu.

**Similarity Score** - Kipimo (0-1) cha ulinganifu wa maana.

**Source Reference** - Metadata kuhusu maudhui yaliyopatikana.

## Wakala na Zana - [Moduli 04](../04-tools/README.md)

**@Tool Annotation** - Inaashiria njia za Java kama zana zinazoweza kuitwa na AI.

**ReAct Pattern** - Fikiria → Tenda → Angalia → Rudia.

**Session Management** - Muktadha tofauti kwa watumiaji tofauti.

**Tool** - Kazi ambayo wakala wa AI anaweza kuita.

**Tool Description** - Nyaraka za kusudi na vigezo vya zana.

## Itifaki ya Muktadha wa Mfano (MCP) - [Moduli 05](../05-mcp/README.md)

**MCP** - Kiwango cha kuunganisha programu za AI na zana za nje.

**MCP Client** - Programu inayounganisha na seva za MCP.

**MCP Server** - Huduma inayofungua zana kupitia MCP.

**Stdio Transport** - Seva kama mchakato msaidizi kupitia stdin/stdout.

**Tool Discovery** - Mteja huita seva kuulizia zana zilizopatikana.

## Huduma za Azure - [Moduli 01](../01-introduction/README.md)

**Azure AI Search** - Utafutaji wa wingu unaounga mkono uwezo wa vector. [Moduli 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Inatekeleza rasilimali za Azure.

**Azure OpenAI** - Huduma ya AI ya shirika ya Microsoft.

**Bicep** - Azure infrastructure-as-code language. [Mwongozo wa Miundombinu](../01-introduction/infra/README.md)

**Deployment Name** - Jina la utekelezaji wa modeli katika Azure.

**GPT-5** - Modeli ya hivi karibuni ya OpenAI yenye udhibiti wa fikra. [Moduli 02](../02-prompt-engineering/README.md)

## Ujaribu na Maendeleo - [Mwongozo wa Majaribio](TESTING.md)

**Dev Container** - Mazingira ya maendeleo yaliyo ndani ya container. [Usanidi](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Uwanja wa majaribio wa modeli za AI bure. [Moduli 00](../00-quick-start/README.md)

**In-Memory Testing** - Majaribio kwa kutumia hifadhi ya kumbukumbu.

**Integration Testing** - Majaribio kwa kutumia miundombinu halisi.

**Maven** - Zana ya kuendesha kujenga (build) ya Java.

**Mockito** - Mfumo wa kuiga (mock) kwa Java.

**Spring Boot** - Mfumo wa programu za Java. [Moduli 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Taarifa ya kutokuwa na dhamana:
Nyaraka hii imetafsiriwa kwa kutumia huduma ya utafsiri ya AI [Co-op Translator](https://github.com/Azure/co-op-translator). Ingawa tunajitahidi kufikia usahihi, tafadhali fahamu kwamba tafsiri za kiotomatiki zinaweza kuwa na makosa au ukosefu wa usahihi. Nyaraka ya asili katika lugha yake inapaswa kuchukuliwa kuwa chanzo cha kuaminika. Kwa taarifa muhimu, inashauriwa kutumia utafsiri wa kitaalamu uliofanywa na mtafsiri wa binadamu. Hatuwajibiki kwa kutoelewana au tafsiri zisizo sahihi zinazotokana na matumizi ya tafsiri hii.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->