<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:37:12+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sl"
}
-->
# LangChain4j Slovarček

## Vsebina

- [Osnovni pojmi](../../../docs)
- [Komponente LangChain4j](../../../docs)
- [Pojmi AI/ML](../../../docs)
- [Varovalke](../../../docs)
- [Inženiring pozivov](../../../docs)
- [RAG (generiranje z nadgradnjo z iskanjem)](../../../docs)
- [Agent in orodja](../../../docs)
- [Agentni modul](../../../docs)
- [Protokol konteksta modela (MCP)](../../../docs)
- [Azure storitve](../../../docs)
- [Testiranje in razvoj](../../../docs)

Hiter pregled pojmov in konceptov, uporabljenih v celotnem tečaju.

## Osnovni pojmi

**AI agent** - Sistem, ki uporablja umetno inteligenco za samostojno razmišljanje in ukrepanje. [Modul 04](../04-tools/README.md)

**Veriga** - Zaporedje operacij, kjer izhod vpliva na naslednji korak.

**Razbijanje na kose (Chunking)** - Razdeljevanje dokumentov na manjše dele. Običajno: 300–500 tokenov z prekrivanjem. [Modul 03](../03-rag/README.md)

**Okno konteksta** - Največje število tokenov, ki jih model lahko obdela. GPT-5: 400 tisoč tokenov.

**Vdelave (Embeddings)** - Numerične vektorje, ki predstavljajo pomen besedila. [Modul 03](../03-rag/README.md)

**Klic funkcije** - Model generira strukturirane zahteve za klic zunanjih funkcij. [Modul 04](../04-tools/README.md)

**Halucinacija** - Ko modeli ustvarijo netočne, a verjetne informacije.

**Poziv (Prompt)** - Besedilni vnos za jezikovni model. [Modul 02](../02-prompt-engineering/README.md)

**Semantično iskanje** - Iskanje po pomenu z uporabo vdelav, ne ključnih besed. [Modul 03](../03-rag/README.md)

**Stanje z ohranitvijo / brez ohranitve stanja (Stateful vs Stateless)** - Brez ohranitve: brez spomina. Z ohranitvijo: ohranja zgodovino pogovora. [Modul 01](../01-introduction/README.md)

**Tokeni** - Osnovne enote besedila, ki jih modeli obdelujejo. Vplivajo na stroške in omejitve. [Modul 01](../01-introduction/README.md)

**Verižna uporaba orodij (Tool Chaining)** - Zaporedno izvajanje orodij, kjer izhod obvešča naslednji klic. [Modul 04](../04-tools/README.md)

## Komponente LangChain4j

**AiServices** - Ustvarja tipno varne vmesnike za AI storitve.

**OpenAiOfficialChatModel** - Enoten odjemalec za OpenAI in Azure OpenAI modele.

**OpenAiOfficialEmbeddingModel** - Ustvarja vdelave z uporabo uradnega OpenAI odjemalca (podpira tako OpenAI kot Azure OpenAI).

**ChatModel** - Glavni vmesnik za jezikovne modele.

**ChatMemory** - Ohranja zgodovino pogovora.

**ContentRetriever** - Najde relevantne dele dokumentov za RAG.

**DocumentSplitter** - Razdeli dokumente na kose.

**EmbeddingModel** - Pretvori besedilo v numerične vektorje.

**EmbeddingStore** - Shrani in pridobi vdelave.

**MessageWindowChatMemory** - Ohranja drsno okno zadnjih sporočil.

**PromptTemplate** - Ustvari ponovno uporabne pozive z zastavico `{{variable}}`.

**TextSegment** - Kos besedila z metapodatki. Uporablja se v RAG.

**ToolExecutionRequest** - Predstavlja zahtevo za izvajanje orodja.

**UserMessage / AiMessage / SystemMessage** - Tipi sporočil v pogovoru.

## Pojmi AI/ML

**Few-Shot Learning** - Podajanje primerov v pozivih. [Modul 02](../02-prompt-engineering/README.md)

**Velik jezikovni model (LLM)** - AI modeli, usposobljeni na velikanskih količinah besedilnih podatkov.

**Napor razmišljanja (Reasoning Effort)** - Parameter GPT-5, ki nadzoruje globino razmišljanja. [Modul 02](../02-prompt-engineering/README.md)

**Temperatura** - Nadzira naključnost izhoda. Nizka=deterministična, visoka=kreativna.

**Vektorska baza podatkov** - Specializirana baza podatkov za vdelave. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Izvajanje nalog brez primerov. [Modul 02](../02-prompt-engineering/README.md)

## Varovalke - [Modul 00](../00-quick-start/README.md)

**Obramba v globino** - Večplastni varnostni pristop, ki združuje varovalke na nivoju aplikacije s filtri ponudnikov.

**Trdi blok** - Ponudnik vrže HTTP 400 napako pri resnih kršitvah vsebine.

**InputGuardrail** - Vmesnik LangChain4j za preverjanje uporabniškega vnosa pred posredovanjem LLM-u. Prihrani stroške in zamude z zgodnjim blokiranjem škodljivih pozivov.

**InputGuardrailResult** - Vrnjena vrednost preverjanja varovalk: `success()` ali `fatal("reason")`.

**OutputGuardrail** - Vmesnik za preverjanje odzivov AI pred vračanjem uporabnikom.

**Provider Safety Filters** - Vgrajeni filtri vsebine pri AI ponudnikih (npr. GitHub modeli), ki zaznavajo kršitve na ravni API-ja.

**Mehka zavrnitev** - Model vljudno zavrne odgovor brez napake.

## Inženiring pozivov - [Modul 02](../02-prompt-engineering/README.md)

**Veriga misli (Chain-of-Thought)** - Korak po koraku razmišljanje za boljšo natančnost.

**Omejen izhod** - Zagotovitev določenega formata ali strukture.

**Visoka angažiranost** - Vzorec GPT-5 za temeljito razmišljanje.

**Nizka angažiranost** - Vzorec GPT-5 za hitre odgovore.

**Večkratni pogovor (Multi-Turn Conversation)** - Ohranjanje konteksta med izmenjavami.

**Pozivanje glede na vlogo** - Nastavljanje osebnosti modela preko sistemskih sporočil.

**Samorefleksija** - Model oceni in izboljša svoj izhod.

**Strukturirana analiza** - Fiksni okvir za ocenjevanje.

**Vzorec izvajanja nalog** - Načrtuj → Izvrši → Povzemi.

## RAG (generiranje z nadgradnjo z iskanjem) - [Modul 03](../03-rag/README.md)

**Predelovalna veriga dokumentov** - Nalaganje → razbijanje → vdelava → shranjevanje.

**Shramba vdelav v pomnilniku** - Neperzistentna shramba za testiranje.

**RAG** - Kombinira iskanje z generiranjem za utemeljitev odgovorov.

**Ocena podobnosti** - Merilo (0–1) semantične podobnosti.

**Sklic na vir** - Metapodatki o pridobljeni vsebini.

## Agent in orodja - [Modul 04](../04-tools/README.md)

**@Tool oznaka** - Označi Java metode kot orodja, ki jih lahko kliče AI.

**ReAct vzorec** - Razmisliti → Ukrepati → Opazovati → Ponoviti.

**Upravljanje sej** - Ločeni konteksti za različne uporabnike.

**Orodje** - Funkcija, ki jo lahko kliče AI agent.

**Opis orodja** - Dokumentacija namena orodja in njegovih parametrov.

## Agentni modul - [Modul 05](../05-mcp/README.md)

**@Agent oznaka** - Označi vmesnike kot AI agente z deklarativno definicijo vedenja.

**Agent Listener** - Priključek za spremljanje izvajanja agenta preko `beforeAgentInvocation()` in `afterAgentInvocation()`.

**Agentni obseg (Agentic Scope)** - Skupni pomnilnik, kjer agenti shranjujejo izhode z uporabo `outputKey` za nadaljnjo uporabo drugih agentov.

**AgenticServices** - Tovarna za ustvarjanje agentov z `agentBuilder()` in `supervisorBuilder()`.

**Pogojni potek dela** - Usmerjanje glede na pogoje k različnim specializiranim agentom.

**Človek v zanki (Human-in-the-Loop)** - Vzorec poteka dodajanja človeških kontrolnih točk za odobritev ali pregled vsebine.

**langchain4j-agentic** - Maven odvisnost za deklarativno gradnjo agentov (eksperimentalno).

**Zankasti potek dela (Loop Workflow)** - Ponavljanje izvajanja agenta, dokler pogoj ni izpolnjen (npr. ocena kakovosti ≥ 0,8).

**outputKey** - Parameter oznake agenta, ki določa, kam se rezultati shranjujejo v agentnem obsegu.

**Vzporedni potek dela** - Hkratno izvajanje več agentov za neodvisne naloge.

**Strategija odgovora** - Kako nadzornik oblikuje končni odgovor: ZADNJI, POVZEMEK ali OCENA.

**Zaporedni potek dela** - Izvajanje agentov v zaporedju, kjer izhod teče v naslednji korak.

**Vzorec nadzornega agenta (Supervisor Agent Pattern)** - Napreden agentni vzorec, kjer nadzornik LLM dinamično odloča, katere podagente povabi.

## Protokol konteksta modela (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** - Maven odvisnost za integracijo MCP v LangChain4j.

**MCP** - Protokol konteksta modela: standard za povezovanje AI aplikacij z zunanjimi orodji. Zgradi enkrat, uporabi povsod.

**MCP odjemalec** - Aplikacija, ki se povezuje s MCP strežniki za odkrivanje in uporabo orodij.

**MCP strežnik** - Storitev, ki razkriva orodja preko MCP z jasnimi opisi in shemami parametrov.

**McpToolProvider** - Komponenta LangChain4j, ki ovije MCP orodja za uporabo v AI storitvah in agentih.

**McpTransport** - Vmesnik za komunikacijo MCP. Implementacije vključujejo Stdio in HTTP.

**Stdio Transport** - Lokalna procesna povezava preko stdin/stdout. Uporabno za dostop do datotečnega sistema ali ukazne vrstice.

**StdioMcpTransport** - Implementacija LangChain4j, ki požene MCP strežnik kot podproces.

**Odkritje orodij** - Odjemalec poizveduje strežnik o razpoložljivih orodjih z opisi in shemami.

## Azure storitve - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Oblačno iskanje z vektorskimi zmožnostmi. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Uporablja se za nameščanje Azure virov.

**Azure OpenAI** - Microsoftova podjetniška AI storitev.

**Bicep** - Azure jezik za infrastrukturo kot kodo. [Vodnik za infrastrukturo](../01-introduction/infra/README.md)

**Ime nameščanja** - Ime za nameščanje modela v Azure.

**GPT-5** - Najnovejši OpenAI model z nadzorom razmišljanja. [Modul 02](../02-prompt-engineering/README.md)

## Testiranje in razvoj - [Vodnik za testiranje](TESTING.md)

**Dev Container** - Razvojno okolje v vsebniku. [Konfiguracija](../../../.devcontainer/devcontainer.json)

**GitHub modeli** - Brezplačno igrišče za AI modele. [Modul 00](../00-quick-start/README.md)

**Testiranje v pomnilniku** - Testiranje z uporabo pomnilniške shrambnike.

**Integracijsko testiranje** - Testiranje s pravo infrastrukturo.

**Maven** - Java orodje za avtomatizacijo gradnje.

**Mockito** - Java ogrodje za simulacijo.

**Spring Boot** - Java ogrodje za aplikacije. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Omejitev odgovornosti**:
Ta dokument je bil preveden z uporabo AI prevajalske storitve [Co-op Translator](https://github.com/Azure/co-op-translator). Čeprav si prizadevamo za natančnost, vas opozarjamo, da lahko avtomatizirani prevodi vsebujejo napake ali netočnosti. Izvirni dokument v njegovem izvorno jeziku naj se smatra za avtoritativni vir. Za pomembne informacije priporočamo strokovni človeški prevod. Za morebitna nesporazume ali napačne interpretacije, ki izhajajo iz uporabe tega prevoda, ne odgovarjamo.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->