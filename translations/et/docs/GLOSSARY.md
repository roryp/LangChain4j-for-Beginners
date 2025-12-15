<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:26:28+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "et"
}
-->
# LangChain4j Sõnastik

## Sisukord

- [Põhikontseptsioonid](../../../docs)
- [LangChain4j Komponendid](../../../docs)
- [AI/ML Kontseptsioonid](../../../docs)
- [Promptide Inseneritöö](../../../docs)
- [RAG (Taasesitusega Täiendatud Generatsioon)](../../../docs)
- [Agentid ja Tööriistad](../../../docs)
- [Mudeli Konteksti Protokoll (MCP)](../../../docs)
- [Azure Teenused](../../../docs)
- [Testimine ja Arendus](../../../docs)

Kiire viide kursuse jooksul kasutatud terminitele ja kontseptsioonidele.

## Põhikontseptsioonid

**AI Agent** - Süsteem, mis kasutab tehisintellekti autonoomseks mõtlemiseks ja tegutsemiseks. [Moodul 04](../04-tools/README.md)

**Chain** - Operatsioonide jada, kus väljund suunatakse järgmisse sammu.

**Chunking** - Dokumentide lõhkumine väiksemateks osadeks. Tavaliselt: 300-500 tokenit koos kattuvusega. [Moodul 03](../03-rag/README.md)

**Context Window** - Maksimaalne tokenite arv, mida mudel suudab töödelda. GPT-5: 400K tokenit.

**Embeddings** - Teksti tähendust esindavad numbrilised vektorid. [Moodul 03](../03-rag/README.md)

**Function Calling** - Mudel genereerib struktureeritud päringuid väliste funktsioonide kutsumiseks. [Moodul 04](../04-tools/README.md)

**Hallucination** - Kui mudelid genereerivad valesid, kuid usutavaid andmeid.

**Prompt** - Tekstisisend keelemudelile. [Moodul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Otsing tähenduse järgi, kasutades embeddings'e, mitte märksõnu. [Moodul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ilma mäluta. Stateful: säilitab vestluse ajaloo. [Moodul 01](../01-introduction/README.md)

**Tokens** - Põhitekstielemendid, mida mudelid töötlevad. Mõjutab kulusid ja piiranguid. [Moodul 01](../01-introduction/README.md)

**Tool Chaining** - Tööriistade järjestikune käivitamine, kus väljund juhib järgmist kutsumist. [Moodul 04](../04-tools/README.md)

## LangChain4j Komponendid

**AiServices** - Loob tüübikindlad AI teenuste liidesed.

**OpenAiOfficialChatModel** - Ühtne klient OpenAI ja Azure OpenAI mudelitele.

**OpenAiOfficialEmbeddingModel** - Loob embeddings'e OpenAI ametliku kliendi abil (toetab nii OpenAI kui Azure OpenAI).

**ChatModel** - Keelemudelite põhiliides.

**ChatMemory** - Säilitab vestluse ajaloo.

**ContentRetriever** - Leiab RAG jaoks asjakohased dokumenditükid.

**DocumentSplitter** - Lõhustab dokumendid tükkideks.

**EmbeddingModel** - Muudab teksti numbrilisteks vektoriteks.

**EmbeddingStore** - Salvestab ja hangib embeddings'e.

**MessageWindowChatMemory** - Säilitab viimaste sõnumite liikuva akna.

**PromptTemplate** - Loob taaskasutatavaid prompt'e koos `{{variable}}` kohatäidetega.

**TextSegment** - Tekstitükk metainfoga. Kasutatakse RAG-is.

**ToolExecutionRequest** - Esindab tööriista täitmise päringut.

**UserMessage / AiMessage / SystemMessage** - Vestluse sõnumitüübid.

## AI/ML Kontseptsioonid

**Few-Shot Learning** - Näidete andmine promptides. [Moodul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Suured keelemudelid, mis on treenitud tohutul hulgal tekstil.

**Reasoning Effort** - GPT-5 parameeter, mis kontrollib mõtlemise sügavust. [Moodul 02](../02-prompt-engineering/README.md)

**Temperature** - Kontrollib väljundi juhuslikkust. Madal=deterministlik, kõrge=loominguline.

**Vector Database** - Spetsialiseeritud andmebaas embeddings'ite jaoks. [Moodul 03](../03-rag/README.md)

**Zero-Shot Learning** - Ülesannete täitmine ilma näideteta. [Moodul 02](../02-prompt-engineering/README.md)

## Promptide Inseneritöö - [Moodul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Samm-sammuline mõtlemine parema täpsuse saavutamiseks.

**Constrained Output** - Spetsiifilise vormingu või struktuuri nõudmine.

**High Eagerness** - GPT-5 muster põhjalikuks mõtlemiseks.

**Low Eagerness** - GPT-5 muster kiirete vastuste jaoks.

**Multi-Turn Conversation** - Konteksti säilitamine mitme vahetuse jooksul.

**Role-Based Prompting** - Mudeli isiku seadistamine süsteemisõnumite kaudu.

**Self-Reflection** - Mudel hindab ja parandab oma väljundit.

**Structured Analysis** - Fikseeritud hindamisraamistik.

**Task Execution Pattern** - Plaan → Täida → Kokkuvõte.

## RAG (Taasesitusega Täiendatud Generatsioon) - [Moodul 03](../03-rag/README.md)

**Document Processing Pipeline** - Laadi → lõhuta → embed'i → salvesta.

**In-Memory Embedding Store** - Mittepüsiv salvestus testimiseks.

**RAG** - Ühendab taasesituse ja generatsiooni, et vastused oleksid põhjendatud.

**Similarity Score** - Semantilise sarnasuse mõõt (0-1).

**Source Reference** - Metaandmed hangitud sisu kohta.

## Agentid ja Tööriistad - [Moodul 04](../04-tools/README.md)

**@Tool Annotation** - Märgistab Java meetodid AI-kõlbulike tööriistadena.

**ReAct Pattern** - Mõtle → Tegutse → Vaata → Korda.

**Session Management** - Eraldi kontekstid erinevatele kasutajatele.

**Tool** - Funktsioon, mida AI agent saab kutsuda.

**Tool Description** - Tööriista eesmärgi ja parameetrite dokumentatsioon.

## Mudeli Konteksti Protokoll (MCP) - [Moodul 05](../05-mcp/README.md)

**Docker Transport** - MCP server Docker konteineris.

**MCP** - Standard AI rakenduste ühendamiseks väliste tööriistadega.

**MCP Client** - Rakendus, mis ühendub MCP serveritega.

**MCP Server** - Teenus, mis eksponeerib tööriistu MCP kaudu.

**Server-Sent Events (SSE)** - Serveri ja kliendi vaheline voogedastus HTTP kaudu.

**Stdio Transport** - Server alamprotsessina stdin/stdout kaudu.

**Streamable HTTP Transport** - HTTP koos SSE-ga reaalajas suhtluseks.

**Tool Discovery** - Klient pärib serverilt saadaolevaid tööriistu.

## Azure Teenused - [Moodul 01](../01-introduction/README.md)

**Azure AI Search** - Pilvepõhine otsing vektorivõimalustega. [Moodul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure ressursside juurutamine.

**Azure OpenAI** - Microsofti ettevõtte AI teenus.

**Bicep** - Azure infrastruktuuri koodikeel. [Infrastruktuuri juhend](../01-introduction/infra/README.md)

**Deployment Name** - Mudeli juurutamise nimi Azure'is.

**GPT-5** - Viimane OpenAI mudel mõtlemise juhtimisega. [Moodul 02](../02-prompt-engineering/README.md)

## Testimine ja Arendus - [Testimise juhend](TESTING.md)

**Dev Container** - Konteineripõhine arenduskeskkond. [Konfiguratsioon](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Tasuta AI mudelite mänguväljak. [Moodul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testimine mälusalvestusega.

**Integration Testing** - Testimine reaalse infrastruktuuriga.

**Maven** - Java ehitustööriist.

**Mockito** - Java imiteerimisraamistik.

**Spring Boot** - Java rakenduste raamistik. [Moodul 01](../01-introduction/README.md)

**Testcontainers** - Docker konteinerid testides.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastutusest loobumine**:
See dokument on tõlgitud kasutades tehisintellekti tõlketeenust [Co-op Translator](https://github.com/Azure/co-op-translator). Kuigi püüame tagada täpsust, palun arvestage, et automaatsed tõlked võivad sisaldada vigu või ebatäpsusi. Originaaldokument selle emakeeles tuleks pidada autoriteetseks allikaks. Olulise teabe puhul soovitatakse kasutada professionaalset inimtõlget. Me ei vastuta selle tõlke kasutamisest tulenevate arusaamatuste või valesti mõistmiste eest.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->