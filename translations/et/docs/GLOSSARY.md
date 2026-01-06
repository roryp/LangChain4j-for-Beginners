<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:44:13+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "et"
}
-->
# LangChain4j Sõnastik

## Sisukord

- [Põhikontseptsioonid](../../../docs)
- [LangChain4j Komponendid](../../../docs)
- [AI/ML Kontseptsioonid](../../../docs)
- [Turvameetmed](../../../docs)
- [Promptide Kujundamine](../../../docs)
- [RAG (Taasesitusele Lisatud Generatsioon)](../../../docs)
- [Agentide ja Tööriistad](../../../docs)
- [Agentne Moodul](../../../docs)
- [Mudelikonteksti Protokoll (MCP)](../../../docs)
- [Azure Teenused](../../../docs)
- [Testimine ja Arendus](../../../docs)

Kiire viide kursuse jooksul kasutatud terminitele ja kontseptsioonidele.

## Põhikontseptsioonid

**AI Agent** – Süsteem, mis kasutab tehisintellekti autonoomseks mõtlemiseks ja tegutsemiseks. [Moodul 04](../04-tools/README.md)

**Chain** – Tegevuste jada, kus väljund söötub järgmisesse etappi.

**Chunking** – Dokumentide jagamine väiksemateks osadeks. Tüüpiline: 300-500 tokenit koos kattuvusega. [Moodul 03](../03-rag/README.md)

**Context Window** – Maksimaalne tokenite arv, mida mudel suudab töödelda. GPT-5: 400K tokenit.

**Embeddings** – Teksti tähendust esindavad arvvektorid. [Moodul 03](../03-rag/README.md)

**Function Calling** – Mudel genereerib struktureeritud päringuid väliste funktsioonide kutsumiseks. [Moodul 04](../04-tools/README.md)

**Hallucination** – Kui mudelid genereerivad valesid, kuid veenvaid andmeid.

**Prompt** – Tekstisisend keelemudelile. [Moodul 02](../02-prompt-engineering/README.md)

**Semantic Search** – Otsing tähenduse alusel, kasutades embeddings’e, mitte märksõnu. [Moodul 03](../03-rag/README.md)

**Stateful vs Stateless** – Stateless: ilma mäluta. Stateful: säilitab vestluse ajaloo. [Moodul 01](../01-introduction/README.md)

**Tokens** – Mudeli töödeldavad teksti põhiüksused. Mõjutavad kulusid ja piire. [Moodul 01](../01-introduction/README.md)

**Tool Chaining** – Tööriistade järjestikune kasutamine, kus väljund suunab järgmist käsku. [Moodul 04](../04-tools/README.md)

## LangChain4j Komponendid

**AiServices** – Loob tüübikindlad AI-teenuste liidesed.

**OpenAiOfficialChatModel** – Ühtne klient OpenAI ja Azure OpenAI mudelitele.

**OpenAiOfficialEmbeddingModel** – Loob embeddings’e OpenAI ametliku kliendi abil (toetab nii OpenAI kui Azure OpenAI).

**ChatModel** – Keelemudelite põhiliides.

**ChatMemory** – Säilitab vestluse ajalugu.

**ContentRetriever** – Leiab RAG jaoks asjakohast dokumentide osa.

**DocumentSplitter** – Jagab dokumendid osadeks.

**EmbeddingModel** – Muudab teksti arvulisteks vektoriteks.

**EmbeddingStore** – Salvestab ja hangib embeddings’e.

**MessageWindowChatMemory** – Säilitab liikuva akna viimastest sõnumitest.

**PromptTemplate** – Loob korduvkasutatavaid prompt'e koos `{{muutuja}}` asendajatega.

**TextSegment** – Tekstiosa koos metaandmetega. Kasutatakse RAG-is.

**ToolExecutionRequest** – Esindab tööriista käivitamise päringut.

**UserMessage / AiMessage / SystemMessage** – Vestluse sõnumitüübid.

## AI/ML Kontseptsioonid

**Few-Shot Learning** – Näidete lisamine promptidesse. [Moodul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** – Suured keelemudelid, mis on treenitud mahuka teksti peal.

**Reasoning Effort** – GPT-5 parameeter, mis kontrollib mõtlemise sügavust. [Moodul 02](../02-prompt-engineering/README.md)

**Temperature** – Juhtib väljundi juhuslikkust. Madal=deterministlik, kõrge=loov.

**Vector Database** – Spetsialiseerunud andmebaas embeddings’ite jaoks. [Moodul 03](../03-rag/README.md)

**Zero-Shot Learning** – Ülesannete täitmine ilma näideteta. [Moodul 02](../02-prompt-engineering/README.md)

## Turvameetmed - [Moodul 00](../00-quick-start/README.md)

**Defense in Depth** – Mitmekihiline turvalahendus, mis ühendab rakendustasemel turvapiirded teenusepakkuja turvafiltritega.

**Hard Block** – Pakkuja viskab API tasemel HTTP 400 vea tõsise sisu rikkumise puhul.

**InputGuardrail** – LangChain4j liides kasutaja sisendi valideerimiseks enne LLM-i jõudmist. Säästab kulusid ja latentsust, blokeerides kahjulikud prompt’id varakult.

**InputGuardrailResult** – Tagastustüüp turvapiirde valideerimisel: `success()` või `fatal("põhjus")`.

**OutputGuardrail** – Liides AI vastuste valideerimiseks enne kasutajale tagastamist.

**Provider Safety Filters** – AI-teenusepakkuja sisseehitatud sisufiltrid (nt GitHub Models), mis püüavad rikkumisi API tasemel kinni.

**Soft Refusal** – Mudel keelduvast viisakalt vastamast, ilma veateadet viskamata.

## Promptide Kujundamine - [Moodul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** – Sammhaaval järelduste tegemine täpsema tulemuse saavutamiseks.

**Constrained Output** – Spetsiifilise formaadi või struktuuri tagamine.

**High Eagerness** – GPT-5 mustriks põhjalikuks mõtlemiseks.

**Low Eagerness** – GPT-5 mustriks kiirete vastuste andmiseks.

**Multi-Turn Conversation** – Konteksti säilitamine mitme vahetusega.

**Role-Based Prompting** – Mudeli persona määramine süsteemisõnumite abil.

**Self-Reflection** – Mudel hindab ja täiustab enda väljundit.

**Structured Analysis** – Fikseeritud hindamise raamistik.

**Task Execution Pattern** – Plaan → Täida → Võta kokku.

## RAG (Taasesitusele Lisatud Generatsioon) - [Moodul 03](../03-rag/README.md)

**Document Processing Pipeline** – Laadimine → tükeldamine → embed’imine → salvestamine.

**In-Memory Embedding Store** – Mittepüsiv salvestus testimiseks.

**RAG** – Ühendab taasesituse generatsiooniga, et vastused oleksid hästi põhjendatud.

**Similarity Score** – Semantilise sarnasuse mõõdik (0-1).

**Source Reference** – Metaandmed hangitud sisu kohta.

## Agentide ja Tööriistad - [Moodul 04](../04-tools/README.md)

**@Tool Annotation** – Märgib Java meetodid AI-kõlbulikeks tööriistadeks.

**ReAct Pattern** – Mõtle → Tegutse → Jälgi → Korda.

**Session Management** – Eraldi kontekstid erinevatele kasutajatele.

**Tool** – Funktsioon, mida AI agent saab kutsuda.

**Tool Description** – Tööriista eesmärgi ja parameetrite dokumentatsioon.

## Agentne Moodul - [Moodul 05](../05-mcp/README.md)

**@Agent Annotation** – Märgib liidesed AI agentidena, deklareerides käitumise.

**Agent Listener** – Kinnitus agentide täitmise jälgimiseks `beforeAgentInvocation()` ja `afterAgentInvocation()` kaudu.

**Agentic Scope** – Jagatud mälu, kuhu agent salvestab väljundid `outputKey` abil järgnevate agentide tarbimiseks.

**AgenticServices** – Agentide loomise tehas, kasutades `agentBuilder()` ja `supervisorBuilder()`.

**Conditional Workflow** – Tingimustel põhinev marsruut spetsialistagentidele.

**Human-in-the-Loop** – Töövoo muster inimkäe lisamiseks heakskiiduks või sisu ülevaatuseks.

**langchain4j-agentic** – Maven sõltuvus deklaratiivseks agentide loomiseks (katsejärgus).

**Loop Workflow** – Agentide täitmise kordamine kuni tingimuse täitumiseni (nt kvaliteediskoor ≥ 0.8).

**outputKey** – Agendi annotatsiooni parameeter, mis määrab, kuhu tulemused Agentic Scope’is salvestatakse.

**Parallel Workflow** – Mitme agendi samaaegne jooksutamine sõltumatute ülesannete jaoks.

**Response Strategy** – Kuidas juhendaja koostab lõpliku vastuse: LAST, SUMMARY või SCORED.

**Sequential Workflow** – Agentide järjestikune täitmine, kus väljund voolab järgmisesse etappi.

**Supervisor Agent Pattern** – Täiustatud agentne muster, kus juhendaja LLM otsustab dünaamiliselt, milliseid alamagente kutsuda.

## Mudelikonteksti Protokoll (MCP) - [Moodul 05](../05-mcp/README.md)

**langchain4j-mcp** – Maven sõltuvus MCP integratsiooniks LangChain4j-s.

**MCP** – Model Context Protocol: standard AI rakenduste ühendamiseks väliste tööriistadega. Ehita üks kord, kasuta igal pool.

**MCP Client** – Rakendus, mis ühendub MCP serveritega tööriistade leidmiseks ja kasutamiseks.

**MCP Server** – Teenus, mis avaldab tööriistu MCP kaudu koos selgete kirjelduste ja parameetrite skeemidega.

**McpToolProvider** – LangChain4j komponent, mis kapseldab MCP tööriistad AI teenuste ja agentide jaoks.

**McpTransport** – Liides MCP kommunikatsiooniks. Implementatsioonideks on Stdio ja HTTP.

**Stdio Transport** – Kohalik protsessi transport stdin/stdout kaudu. Kasulik failisüsteemi või käsurea tööriistade jaoks.

**StdioMcpTransport** – LangChain4j implementatsioon, mis käivitab MCP serveri alamprotsessina.

**Tool Discovery** – Klient pärib serverilt olemasolevad tööriistad koos kirjelduste ja skeemidega.

## Azure Teenused - [Moodul 01](../01-introduction/README.md)

**Azure AI Search** – Pilvepõhine otsing vektorivõimekusega. [Moodul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** – Azure ressursside juurutamine.

**Azure OpenAI** – Microsofti ettevõtte AI teenus.

**Bicep** – Azure infrastruktuuri-koodi keel. [Infrastruktuuri juhend](../01-introduction/infra/README.md)

**Deployment Name** – Mudeli juurutamise nimi Azure's.

**GPT-5** – Viimane OpenAI mudel mõtlemise juhtimisega. [Moodul 02](../02-prompt-engineering/README.md)

## Testimine ja Arendus - [Testimiskäsiraamat](TESTING.md)

**Dev Container** – Konteineripõhine arenduskeskkond. [Konfiguratsioon](../../../.devcontainer/devcontainer.json)

**GitHub Models** – Tasuta AI mudelite mänguväljak. [Moodul 00](../00-quick-start/README.md)

**In-Memory Testing** – Testimine mälusalvestusega.

**Integration Testing** – Testimine reaalse infrastruktuuriga.

**Maven** – Java ehitamise automatiseerimise tööriist.

**Mockito** – Java jäljendusraamistik.

**Spring Boot** – Java rakenduste raamistik. [Moodul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Apleyus**:  
See dokument on tõlgitud kasutades tehisintellektil põhinevat tõlketeenust [Co-op Translator](https://github.com/Azure/co-op-translator). Kuigi püüame täpsust, palun arvestage, et automaatsed tõlked võivad sisaldada vigu või ebatäpsusi. Algne dokument selle emakeeles tuleks pidada autoriteetseks allikaks. Olulise teabe puhul soovitatakse kasutada professionaalset inimtõlget. Me ei vastuta ühegi arusaamatuse ega valesti tõlgendamise eest, mis võivad tekkida selle tõlke kasutamisel.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->