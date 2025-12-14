<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:22:17+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sl"
}
-->
# LangChain4j Slovar

## Kazalo

- [Osnovni pojmi](../../../docs)
- [Komponente LangChain4j](../../../docs)
- [Pojmi AI/ML](../../../docs)
- [Inženiring pozivov](../../../docs)
- [RAG (generiranje z iskanjem)](../../../docs)
- [Agentje in orodja](../../../docs)
- [Protokol konteksta modela (MCP)](../../../docs)
- [Azure storitve](../../../docs)
- [Testiranje in razvoj](../../../docs)

Hiter pregled izrazov in pojmov, uporabljenih skozi tečaj.

## Osnovni pojmi

**AI agent** - Sistem, ki uporablja AI za samostojno razmišljanje in delovanje. [Modul 04](../04-tools/README.md)

**Veriga** - Zaporedje operacij, kjer izhod napaja naslednji korak.

**Razbijanje na kose** - Razdeljevanje dokumentov na manjše dele. Tipično: 300-500 tokenov z prekrivanjem. [Modul 03](../03-rag/README.md)

**Kontekstno okno** - Največje število tokenov, ki jih model lahko obdela. GPT-5: 400K tokenov.

**Vdelave** - Numerične vektorje, ki predstavljajo pomen besedila. [Modul 03](../03-rag/README.md)

**Klic funkcije** - Model generira strukturirane zahteve za klic zunanjih funkcij. [Modul 04](../04-tools/README.md)

**Halucinacija** - Ko modeli ustvarijo napačne, a verjetne informacije.

**Poziv** - Besedilni vhod v jezikovni model. [Modul 02](../02-prompt-engineering/README.md)

**Semantično iskanje** - Iskanje po pomenu z uporabo vdelav, ne ključnih besed. [Modul 03](../03-rag/README.md)

**Stanje brez stanja vs s stanjem** - Brez stanja: brez spomina. S stanjem: ohranja zgodovino pogovora. [Modul 01](../01-introduction/README.md)

**Tokeni** - Osnovne enote besedila, ki jih modeli obdelujejo. Vplivajo na stroške in omejitve. [Modul 01](../01-introduction/README.md)

**Verižna uporaba orodij** - Zaporedno izvajanje orodij, kjer izhod vpliva na naslednji klic. [Modul 04](../04-tools/README.md)

## Komponente LangChain4j

**AiServices** - Ustvarja tipno varne vmesnike za AI storitve.

**OpenAiOfficialChatModel** - Enoten odjemalec za modele OpenAI in Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Ustvarja vdelave z uporabo uradnega OpenAI odjemalca (podpira OpenAI in Azure OpenAI).

**ChatModel** - Osnovni vmesnik za jezikovne modele.

**ChatMemory** - Ohranja zgodovino pogovora.

**ContentRetriever** - Najde relevantne dele dokumentov za RAG.

**DocumentSplitter** - Razdeli dokumente na kose.

**EmbeddingModel** - Pretvori besedilo v numerične vektorje.

**EmbeddingStore** - Shrani in pridobi vdelave.

**MessageWindowChatMemory** - Ohranja drseče okno nedavnih sporočil.

**PromptTemplate** - Ustvari ponovno uporabne pozive z `{{variable}}` nadomestki.

**TextSegment** - Besedilni kos z metapodatki. Uporablja se v RAG.

**ToolExecutionRequest** - Predstavlja zahtevo za izvajanje orodja.

**UserMessage / AiMessage / SystemMessage** - Vrste sporočil v pogovoru.

## Pojmi AI/ML

**Učenje z nekaj primeri** - Zagotavljanje primerov v pozivih. [Modul 02](../02-prompt-engineering/README.md)

**Velik jezikovni model (LLM)** - AI modeli, usposobljeni na obsežnih besedilnih podatkih.

**Napor razmišljanja** - Parameter GPT-5, ki nadzoruje globino razmišljanja. [Modul 02](../02-prompt-engineering/README.md)

**Temperatura** - Nadzoruje naključnost izhoda. Nizka=deterministična, visoka=kreativna.

**Vektorska baza podatkov** - Specializirana baza za vdelave. [Modul 03](../03-rag/README.md)

**Učenje brez primerov** - Izvajanje nalog brez primerov. [Modul 02](../02-prompt-engineering/README.md)

## Inženiring pozivov - [Modul 02](../02-prompt-engineering/README.md)

**Veriga misli** - Korak za korakom razmišljanje za boljšo natančnost.

**Omejen izhod** - Uveljavljanje določenega formata ali strukture.

**Visoka vnema** - Vzorec GPT-5 za temeljito razmišljanje.

**Nizka vnema** - Vzorec GPT-5 za hitre odgovore.

**Večkratni pogovor** - Ohranjanje konteksta skozi izmenjave.

**Pozivanje na podlagi vlog** - Nastavitev osebnosti modela preko sistemskih sporočil.

**Samorefleksija** - Model ocenjuje in izboljšuje svoj izhod.

**Strukturirana analiza** - Fiksni okvir ocenjevanja.

**Vzorec izvajanja nalog** - Načrtuj → Izvedi → Povzemi.

## RAG (generiranje z iskanjem) - [Modul 03](../03-rag/README.md)

**Procesiranje dokumentov** - Naloži → razdeli → vdelaj → shrani.

**Vdelave v pomnilniku** - Neperzistentno shranjevanje za testiranje.

**RAG** - Združuje iskanje z generiranjem za utemeljitev odgovorov.

**Ocena podobnosti** - Merilo (0-1) semantične podobnosti.

**Referenca vira** - Metapodatki o pridobljeni vsebini.

## Agentje in orodja - [Modul 04](../04-tools/README.md)

**@Tool oznaka** - Označuje Java metode kot AI-klicna orodja.

**ReAct vzorec** - Razmišljaj → Ukrepaj → Opazuj → Ponovi.

**Upravljanje sej** - Ločeni konteksti za različne uporabnike.

**Orodje** - Funkcija, ki jo lahko AI agent pokliče.

**Opis orodja** - Dokumentacija namena in parametrov orodja.

## Protokol konteksta modela (MCP) - [Modul 05](../05-mcp/README.md)

**Docker transport** - MCP strežnik v Docker kontejnerju.

**MCP** - Standard za povezovanje AI aplikacij z zunanjimi orodji.

**MCP odjemalec** - Aplikacija, ki se povezuje z MCP strežniki.

**MCP strežnik** - Storitev, ki preko MCP izpostavlja orodja.

**Server-Sent Events (SSE)** - Pretakanje s strežnika na odjemalca preko HTTP.

**Stdio transport** - Strežnik kot podproces preko stdin/stdout.

**Pretakanje HTTP transporta** - HTTP s SSE za komunikacijo v realnem času.

**Odkritje orodij** - Odjemalec poizveduje strežnik o razpoložljivih orodjih.

## Azure storitve - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Iskanje v oblaku z vektorskimi zmogljivostmi. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Uporablja Azure vire.

**Azure OpenAI** - Microsoftova podjetniška AI storitev.

**Bicep** - Jezik za infrastrukturo kot kodo v Azure. [Vodnik za infrastrukturo](../01-introduction/infra/README.md)

**Ime nameščanja** - Ime za nameščanje modela v Azure.

**GPT-5** - Najnovejši OpenAI model z nadzorom razmišljanja. [Modul 02](../02-prompt-engineering/README.md)

## Testiranje in razvoj - [Vodnik za testiranje](TESTING.md)

**Dev Container** - Kontejnerizirano razvojno okolje. [Konfiguracija](../../../.devcontainer/devcontainer.json)

**GitHub modeli** - Brezplačno igrišče za AI modele. [Modul 00](../00-quick-start/README.md)

**Testiranje v pomnilniku** - Testiranje z uporabo pomnilniškega shranjevanja.

**Integracijsko testiranje** - Testiranje z resnično infrastrukturo.

**Maven** - Orodje za avtomatizacijo gradnje v Javi.

**Mockito** - Okvir za lažno testiranje v Javi.

**Spring Boot** - Okvir za Java aplikacije. [Modul 01](../01-introduction/README.md)

**Testcontainers** - Docker kontejnerji v testih.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Omejitev odgovornosti**:
Ta dokument je bil preveden z uporabo storitve za prevajanje z umetno inteligenco [Co-op Translator](https://github.com/Azure/co-op-translator). Čeprav si prizadevamo za natančnost, vas opozarjamo, da avtomatizirani prevodi lahko vsebujejo napake ali netočnosti. Izvirni dokument v njegovem izvirnem jeziku velja za avtoritativni vir. Za ključne informacije priporočamo strokovni človeški prevod. Za morebitna nesporazume ali napačne interpretacije, ki izhajajo iz uporabe tega prevoda, ne odgovarjamo.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->