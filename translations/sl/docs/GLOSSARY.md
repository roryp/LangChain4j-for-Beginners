<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T05:56:09+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sl"
}
-->
# LangChain4j Slovar

## Vsebina

- [Osnovni pojmi](../../../docs)
- [Komponente LangChain4j](../../../docs)
- [AI/ML koncepti](../../../docs)
- [Oblikovanje pozivov](../../../docs)
- [RAG (generiranje, podprto z iskanjem)](../../../docs)
- [Agentii orodja](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Storitev Azure](../../../docs)
- [Testiranje in razvoj](../../../docs)

Hiter pregled izrazov in konceptov, uporabljenih v tečaju.

## Core Concepts

**AI Agent** - Sistem, ki uporablja AI za sklepanje in avtonomno delovanje. [Modul 04](../04-tools/README.md)

**Chain** - Zaporedje operacij, kjer izhod napaja naslednji korak.

**Chunking** - Razbijanje dokumentov na manjše dele. Tipično: 300–500 tokenov z prekrivanjem. [Modul 03](../03-rag/README.md)

**Context Window** - Največje število tokenov, ki jih model lahko obdela. GPT-5: 400K tokenov.

**Embeddings** - Numerični vektorji, ki predstavljajo pomen besedila. [Modul 03](../03-rag/README.md)

**Function Calling** - Model generira strukturirane zahteve za klic zunanjih funkcij. [Modul 04](../04-tools/README.md)

**Hallucination** - Ko modeli generirajo napačne, a verjetne informacije.

**Prompt** - Besedilni vnos v jezikovni model. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Iskanje po pomenu z uporabo embeddingov, ne po ključnih besedah. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Brezstanje: brez pomnilnika. Stanje: ohranja zgodovino pogovora. [Modul 01](../01-introduction/README.md)

**Tokens** - Osnovne enote besedila, ki jih modeli obdelujejo. Vplivajo na stroške in omejitve. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Zaporedno izvajanje orodij, kjer izhod informira naslednji klic. [Modul 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Ustvari tipno-varne vmesnike za AI storitve.

**OpenAiOfficialChatModel** - Združeni odjemalec za OpenAI in Azure OpenAI modele.

**OpenAiOfficialEmbeddingModel** - Ustvari embeddinge z uporabo OpenAI Official odjemalca (podpira tako OpenAI kot Azure OpenAI).

**ChatModel** - Osnovni vmesnik za jezikovne modele.

**ChatMemory** - Ohranja zgodovino pogovora.

**ContentRetriever** - Najde relevantne kose dokumentov za RAG.

**DocumentSplitter** - Razdeli dokumente na kose.

**EmbeddingModel** - Pretvara besedilo v numerične vektorje.

**EmbeddingStore** - Shranjuje in pridobiva embeddinge.

**MessageWindowChatMemory** - Ohranja pomični okno nedavnih sporočil.

**PromptTemplate** - Ustvari ponovno uporabne pozive z `{{variable}}` mestniki.

**TextSegment** - Kos besedila z metapodatki. Uporablja se v RAG.

**ToolExecutionRequest** - Predstavlja zahtevo za izvedbo orodja.

**UserMessage / AiMessage / SystemMessage** - Vrste sporočil v pogovoru.

## AI/ML Concepts

**Few-Shot Learning** - Vključevanje primerov v promptih. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI modeli, trenirani na obsežnih besedilnih podatkih.

**Reasoning Effort** - Parameter GPT-5, ki nadzoruje globino razmišljanja. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Nadzoruje naključnost izhodov. Nizka=determinističen, visoka=kreativen.

**Vector Database** - Specializirana baza podatkov za embeddinge. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Opravljanje nalog brez primerov. [Modul 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Korak-za-korakom razmišljanje za boljšo natančnost.

**Constrained Output** - Uveljavljanje posebne oblike ali strukture izhoda.

**High Eagerness** - Vzorec GPT-5 za temeljito razmišljanje.

**Low Eagerness** - Vzorec GPT-5 za hitre odgovore.

**Multi-Turn Conversation** - Ohranjanje konteksta skozi več izmenjav.

**Role-Based Prompting** - Nastavitev osebnosti modela preko sistemskih sporočil.

**Self-Reflection** - Model ovrednoti in izboljša svoj izhod.

**Structured Analysis** - Fiksiran okvir za ocenjevanje.

**Task Execution Pattern** - Načrt → Izvedi → Povzetek.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Naloži → razdeli → embedira → shrani.

**In-Memory Embedding Store** - Nepersistentno shranjevanje za testiranje.

**RAG** - Kombinira pridobivanje z generiranjem za utemeljitev odgovorov.

**Similarity Score** - Merilo (0–1) semantične podobnosti.

**Source Reference** - Metapodatki o pridobljeni vsebini.

## Agents and Tools - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Oznaki Java metode kot AI-klicna orodja.

**ReAct Pattern** - Razmisli → Ukrepi → Opazuj → Ponovi.

**Session Management** - Ločeni konteksti za različne uporabnike.

**Tool** - Funkcija, ki jo AI agent lahko pokliče.

**Tool Description** - Dokumentacija namena orodja in parametrov.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**MCP** - Standard za povezovanje AI aplikacij z zunanjimi orodji.

**MCP Client** - Aplikacija, ki se poveže na MCP strežnike.

**MCP Server** - Storitev, ki preko MCP izpostavlja orodja.

**Stdio Transport** - Strežnik kot podproces preko stdin/stdout.

**Tool Discovery** - Odjemalec poizveduje strežnik za razpoložljiva orodja.

## Azure Services - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Iskanje v oblaku z vektorskimi zmogljivostmi. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Uporablja se za nameščanje Azure virov.

**Azure OpenAI** - Microsoftova enterprise AI storitev.

**Bicep** - Jezik za infrastrukturo kot koda za Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Ime namestitve modela v Azure.

**GPT-5** - Najnovejši OpenAI model z nadzorom razmišljanja. [Modul 02](../02-prompt-engineering/README.md)

## Testing and Development - [Vodnik za testiranje](TESTING.md)

**Dev Container** - Kontejnerizirano razvojno okolje. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Brezplačno igrišče za AI modele. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testiranje s pomnilniškim shranjevanjem.

**Integration Testing** - Testiranje z dejansko infrastrukturo.

**Maven** - Orodje za avtomatizacijo gradnje Java projektov.

**Mockito** - Okvir za izdelavo lažnih objektov v Javi.

**Spring Boot** - Java aplikacijski okvir. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Izjava o omejitvi odgovornosti:
Ta dokument je bil preveden z uporabo storitve za prevajanje z umetno inteligenco Co-op Translator (https://github.com/Azure/co-op-translator). Čeprav si prizadevamo za natančnost, upoštevajte, da lahko samodejni prevodi vsebujejo napake ali netočnosti. Izvirni dokument v izvirnem jeziku velja za avtoritativni vir. Za kritične informacije priporočamo strokovni človeški prevod. Za morebitne nesporazume ali napačne razlage, ki izhajajo iz uporabe tega prevoda, ne prevzemamo odgovornosti.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->