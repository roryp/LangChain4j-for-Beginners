<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T07:19:54+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "et"
}
-->
# LangChain4j sõnastik

## Sisukord

- [Põhilised kontseptsioonid](../../../docs)
- [LangChain4j komponendid](../../../docs)
- [AI/ML kontseptsioonid](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agentid ja tööriistad](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure teenused](../../../docs)
- [Testimine ja arendus](../../../docs)

Kiirviide kursuse vältel kasutatud terminitele ja kontseptsioonidele.

## Core Concepts

**AI Agent** - Süsteem, mis kasutab tehisintellekti iseseisvaks mõtlemiseks ja tegutsemiseks. [Module 04](../04-tools/README.md)

**Chain** - Järjestus toimingutest, kus väljund suunatakse järgmisesse sammu.

**Chunking** - Dokumentide jagamine väiksemateks osadeks. Tavaliselt: 300–500 tokenit koos kattuvusega. [Module 03](../03-rag/README.md)

**Context Window** - Maksimaalne tokenite hulk, mida mudel suudab töödelda. GPT-5: 400K tokenit.

**Embeddings** - Teksti tähendust esindavad numbrilised vektorid. [Module 03](../03-rag/README.md)

**Function Calling** - Mudel genereerib struktureeritud päringuid väliste funktsioonide kutsumiseks. [Module 04](../04-tools/README.md)

**Hallucination** - Situatsioon, kus mudel genereerib valesid, kuid usutavaid andmeid.

**Prompt** - Tekstiline sisend keelemudelile. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Otsing tähenduse alusel, kasutades embeddings'e, mitte märksõnu. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ei kasuta mälu. Stateful: säilitab vestluse ajaloo. [Module 01](../01-introduction/README.md)

**Tokens** - Teksti põhielemendid, mida mudel töötleb. Mõjutavad kulusid ja piiranguid. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Järjestikune tööriistade täitmine, kus väljund suunab järgmise kutsumise. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Loodab tüübiohutud AI-teenuste liideseid.

**OpenAiOfficialChatModel** - Ühtne klient OpenAI ja Azure OpenAI mudelite jaoks.

**OpenAiOfficialEmbeddingModel** - Loob embeddings'e, kasutades OpenAI Official klienti (toetab nii OpenAI kui Azure OpenAI).

**ChatModel** - Põhiline liides keelemudelite jaoks.

**ChatMemory** - Säilitab vestluse ajaloo.

**ContentRetriever** - Leiab RAG-iks asjakohased dokumendi tükid.

**DocumentSplitter** - Jagab dokumendid tükkideks.

**EmbeddingModel** - Konverteerib teksti numbrilisteks vektoriteks.

**EmbeddingStore** - Salvestab ja hangib embeddings'e.

**MessageWindowChatMemory** - Hooldab viimaste sõnumite libisevat akent.

**PromptTemplate** - Loob taaskasutatavaid prompt'e `{{variable}}` kohatäitjatega.

**TextSegment** - Tekstilõik metaandmetega. Kasutatakse RAG-is.

**ToolExecutionRequest** - Esindab tööriista täitmise päringut.

**UserMessage / AiMessage / SystemMessage** - Vestluse sõnumitüübid.

## AI/ML Concepts

**Few-Shot Learning** - Näidete esitamine promptides. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Suured keelemudelid, mis on treenitud mahuka tekstimaterjali peal.

**Reasoning Effort** - GPT-5 parameeter, mis kontrollib mõtlemise sügavust. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Kontrollib väljundi juhuslikkust. Madal=deterministlik, kõrge=loominguline.

**Vector Database** - Spetsialiseeritud andmebaas embeddings'ite jaoks. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Ülesannete sooritamine ilma näideteta. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Samm-sammuline mõtlemine parema täpsuse saavutamiseks.

**Constrained Output** - Konkreetse vormingu või struktuuri sundimine.

**High Eagerness** - GPT-5 muster põhjalikuks arutluseks.

**Low Eagerness** - GPT-5 muster kiirete vastuste jaoks.

**Multi-Turn Conversation** - Konteksti hoidmine mitme vahetuse vältel.

**Role-Based Prompting** - Mudeli isiku määramine süsteemisõnumite kaudu.

**Self-Reflection** - Mudel hindab ja parandab oma väljundit.

**Structured Analysis** - Kindel hindamisraamistik.

**Task Execution Pattern** - Plaan → Täida → Kokkuvõte.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Laadi → tükelda → embed'i loo → salvesta.

**In-Memory Embedding Store** - Mittepüsiv salvestus testimiseks.

**RAG** - Kombineerib taasesituse ja genereerimise, et vastuseid faktiliselt toetada.

**Similarity Score** - Mõõt (0-1) semantilise sarnasuse jaoks.

**Source Reference** - Metaandmed leitud sisu kohta.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Märgistab Java meetodid AI-kõnedele kutsutavate tööriistadena.

**ReAct Pattern** - Mõtle → Tegutse → Vaata → Korda.

**Session Management** - Eraldatud kontekstid erinevate kasutajate jaoks.

**Tool** - Funktsioon, mida AI-agent saab kutsuda.

**Tool Description** - Tööriista eesmärgi ja parameetrite dokumentatsioon.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - Standard AI-rakenduste ühendamiseks väliste tööriistadega.

**MCP Client** - Rakendus, mis ühendub MCP serveritega.

**MCP Server** - Teenus, mis eksponeerib tööriistu MCP kaudu.

**Stdio Transport** - Server alamprotsessina stdin/stdout kaudu.

**Tool Discovery** - Klient pärib serverilt saadaolevad tööriistad.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Pilveotsing vektorivõimalustega. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure ressursside juurutamisvahend.

**Azure OpenAI** - Microsofti ettevõtte tehisintellekti teenus.

**Bicep** - Azure infrastruktuuri-koodi keel. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Nimi mudeli juurutamiseks Azure'is.

**GPT-5** - OpenAI uusim mudel mõtlemise juhtimise võimalusega. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Konteineripõhine arenduskeskkond. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Tasuta AI mudelite katsekeskkond. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testimine mälupõhise salvestusega.

**Integration Testing** - Testimine reaalse infrastruktuuriga.

**Maven** - Java ehituse automatiseerimise tööriist.

**Mockito** - Java simulatsiooni raamistiku tööriist.

**Spring Boot** - Java rakenduste raamistik. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Vastutusest loobumine:
See dokument on tõlgitud tehisintellekti tõlketeenuse Co‑op Translator (https://github.com/Azure/co-op-translator) abil. Kuigi me püüame tagada täpsust, palun arvestage, et automatiseeritud tõlked võivad sisaldada vigu või ebatäpsusi. Originaaldokument selle emakeeles tuleks pidada autoriteetseks allikaks. Olulise teabe puhul soovitatakse kasutada professionaalset inimtõlget. Me ei vastuta selle tõlke kasutamisest tulenevate arusaamatuste ega valesti tõlgendamise eest.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->