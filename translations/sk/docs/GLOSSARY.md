<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:30:01+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sk"
}
-->
# LangChain4j Slovník pojmov

## Obsah

- [Základné pojmy](../../../docs)
- [Komponenty LangChain4j](../../../docs)
- [Pojmy AI/ML](../../../docs)
- [Bezpečnostné opatrenia](../../../docs)
- [Tvorba promptov](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenti a nástroje](../../../docs)
- [Agentický modul](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure služby](../../../docs)
- [Testovanie a vývoj](../../../docs)

Rýchla referencia pojmov a konceptov používaných počas kurzu.

## Základné pojmy

**AI agent** - Systém, ktorý používa AI na samostatné uvažovanie a konanie. [Modul 04](../04-tools/README.md)

**Reťazec (Chain)** - Sekvencia operácií, kde výstup je vstupom do nasledujúceho kroku.

**Rozdelenie na časti (Chunking)** - Rozbitie dokumentov na menšie časti. Typicky: 300-500 tokenov s prekrytím. [Modul 03](../03-rag/README.md)

**Kontextové okno (Context Window)** - Maximálny počet tokenov, ktoré model dokáže spracovať. GPT-5: 400K tokenov.

**Vektorizácie (Embeddings)** - Číselné vektory reprezentujúce význam textu. [Modul 03](../03-rag/README.md)

**Volanie funkcií (Function Calling)** - Model generuje štruktúrované požiadavky na volanie externých funkcií. [Modul 04](../04-tools/README.md)

**Halucinácia (Hallucination)** - Keď modely generujú nesprávne, no pravdepodobné informácie.

**Prompt** - Textový vstup pre jazykový model. [Modul 02](../02-prompt-engineering/README.md)

**Sémantické vyhľadávanie (Semantic Search)** - Vyhľadávanie podľa významu pomocou vektorizácií, nie kľúčových slov. [Modul 03](../03-rag/README.md)

**Stavový vs Bezstavový (Stateful vs Stateless)** - Bezstavový: bez pamäti. Stavový: udržiava históriu konverzácie. [Modul 01](../01-introduction/README.md)

**Tokeny** - Základné textové jednotky spracovávané modelmi. Ovplyvňujú náklady a limity. [Modul 01](../01-introduction/README.md)

**Reťazenie nástrojov (Tool Chaining)** - Sekvenčné vykonanie nástrojov, kde výstup ovplyvňuje volanie ďalšieho. [Modul 04](../04-tools/README.md)

## Komponenty LangChain4j

**AiServices** - Vytvára typovo bezpečné rozhrania pre AI služby.

**OpenAiOfficialChatModel** - Zjednotený klient pre OpenAI a Azure OpenAI modely.

**OpenAiOfficialEmbeddingModel** - Vytvára vektorizácie pomocou oficiálneho OpenAI klienta (podporuje OpenAI aj Azure OpenAI).

**ChatModel** - Hlavné rozhranie pre jazykové modely.

**ChatMemory** - Udržiava históriu konverzácie.

**ContentRetriever** - Nájde relevantné časti dokumentov pre RAG.

**DocumentSplitter** - Rozdeľuje dokumenty na časti.

**EmbeddingModel** - Konvertuje text na číselné vektory.

**EmbeddingStore** - Ukladá a vyhľadáva vektorizácie.

**MessageWindowChatMemory** - Udržiava pohyblivé okno posledných správ.

**PromptTemplate** - Vytvára opakovane použiteľné prompty s `{{variable}}` zástupcami.

**TextSegment** - Textová časť s metadátami. Používa sa v RAG.

**ToolExecutionRequest** - Reprezentuje požiadavku na vykonanie nástroja.

**UserMessage / AiMessage / SystemMessage** - Typy správ v konverzácii.

## Pojmy AI/ML

**Few-Shot Learning** - Poskytovanie príkladov v promptoch. [Modul 02](../02-prompt-engineering/README.md)

**Veľký jazykový model (LLM)** - AI modely trénované na obrovských textových dátach.

**Rozumovacie úsilie (Reasoning Effort)** - Parameter GPT-5 kontrolujúci hĺbku uvažovania. [Modul 02](../02-prompt-engineering/README.md)

**Teplota (Temperature)** - Riadi náhodnosť výstupu. Nízka=deterministické, vysoká=kreatívne.

**Vektorová databáza (Vector Database)** - Špecializovaná databáza pre vektorizácie. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Vykonávanie úloh bez príkladov. [Modul 02](../02-prompt-engineering/README.md)

## Bezpečnostné opatrenia - [Modul 00](../00-quick-start/README.md)

**Obrana v hĺbke (Defense in Depth)** - Viacvrstvový bezpečnostný prístup kombinujúci aplikáciou nastavené opatrenia s bezpečnostnými filtrami poskytovateľa.

**Tvrdý blok (Hard Block)** - Poskytovateľ vracia HTTP 400 chybu pri závažných porušeniach obsahu.

**InputGuardrail** - LangChain4j rozhranie na validáciu používateľského vstupu pred dosiahnutím LLM. Šetrí náklady a latenciu blokovaním škodlivých promptov včas.

**InputGuardrailResult** - Návratový typ pre validáciu guardrailu: `success()` alebo `fatal("dôvod")`.

**OutputGuardrail** - Rozhranie na validáciu AI odpovedí pred ich vrátením používateľom.

**Bezpečnostné filtre poskytovateľa (Provider Safety Filters)** - Vstavané filtre obsahu od AI poskytovateľov (napr. GitHub Models) zachytávajúce porušenia na úrovni API.

**Mierne odmietnutie (Soft Refusal)** - Model zdvorilo odmietne odpovedať bez vyvolania chyby.

## Tvorba promptov - [Modul 02](../02-prompt-engineering/README.md)

**Logické uvažovanie krok za krokom (Chain-of-Thought)** - Postupné uvažovanie pre lepšiu presnosť.

**Obmedzený výstup (Constrained Output)** - Vynucovanie konkrétneho formátu alebo štruktúry.

**Vysoká ochota (High Eagerness)** - GPT-5 vzorec pre dôkladné uvažovanie.

**Nízka ochota (Low Eagerness)** - GPT-5 vzorec pre rýchle odpovede.

**Viackolový rozhovor (Multi-Turn Conversation)** - Udržiavanie kontextu naprieč výmenami.

**Promptovanie podľa rolí (Role-Based Prompting)** - Nastavenie modelovej persony prostredníctvom systémových správ.

**Sebareflexia (Self-Reflection)** - Model hodnotí a zlepšuje svoj výstup.

**Štruktúrovaná analýza (Structured Analysis)** - Fixný hodnotiaci rámec.

**Vzorec vykonania úlohy (Task Execution Pattern)** - Plánuj → Vykonaj → Zhrň.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Pipeline spracovania dokumentov (Document Processing Pipeline)** - Načítať → rozbiť → vložiť do vektorov → uložiť.

**V pamäti uložená embedding store (In-Memory Embedding Store)** - Neptrvalé úložisko na testovanie.

**RAG** - Kombinuje vyhľadávanie s generovaním na zakladanie odpovedí.

**Skóre podobnosti (Similarity Score)** - Miera (0-1) sémantickej podobnosti.

**Zdrojový odkaz (Source Reference)** - Metadáta o získanom obsahu.

## Agenti a nástroje - [Modul 04](../04-tools/README.md)

**@Tool anotácia** - Označuje Java metódy ako nástroje volateľné AI.

**ReAct vzorec** - Uvažuj → Konaj → Pozoruj → Opakuj.

**Správa relácií (Session Management)** - Oddelené kontexty pre rôznych používateľov.

**Nástroj (Tool)** - Funkcia volaná AI agentom.

**Popis nástroja (Tool Description)** - Dokumentácia účelu a parametrov nástroja.

## Agentický modul - [Modul 05](../05-mcp/README.md)

**@Agent anotácia** - Označuje rozhrania ako AI agentov s deklaratívnym definovaním správania.

**Agent Listener** - Hook na sledovanie vykonávania agenta cez `beforeAgentInvocation()` a `afterAgentInvocation()`.

**Agentická oblasť (Agentic Scope)** - Zdieľaná pamäť, kam agenti ukladajú výstupy pomocou `outputKey` na spotrebu následnými agentmi.

**AgenticServices** - Továreň na vytváranie agentov pomocou `agentBuilder()` a `supervisorBuilder()`.

**Podmienený workflow (Conditional Workflow)** - Smerovanie podľa podmienok na rôznych špecialistických agentov.

**Človek v slučke (Human-in-the-Loop)** - Vzorec workflow pridávajúci ľudské kontrolné body na schválenie alebo revíziu obsahu.

**langchain4j-agentic** - Maven závislosť pre deklaratívnu tvorbu agentov (experimentálne).

**Cyklický workflow (Loop Workflow)** - Iteruje vykonávanie agenta, kým nie je splnená podmienka (napr. skóre kvality ≥ 0.8).

**outputKey** - Parameter anotácie agenta určujúci, kde sa výsledky ukladajú v agentickej oblasti.

**Paralelný workflow (Parallel Workflow)** - Súbežne spúšťa viacerých agentov na nezávislé úlohy.

**Stratégia odpovede (Response Strategy)** - Spôsob, akým supervízor formuluje konečnú odpoveď: LAST, SUMMARY alebo SCORED.

**Sekvenčný workflow (Sequential Workflow)** - Vykonáva agentov po poriadku, kde výstup plynie do nasledujúceho kroku.

**Vzorec supervízor agenta (Supervisor Agent Pattern)** - Pokročilý agentický vzorec, kde supervízor LLM dynamicky rozhoduje, ktorých podagentov vyvolať.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** - Maven závislosť pre integráciu MCP v LangChain4j.

**MCP** - Model Context Protocol: štandard pre prepájanie AI aplikácií s externými nástrojmi. Vybuduj raz, používaj všade.

**MCP klient** - Aplikácia pripájajúca sa na MCP servery na objavovanie a používanie nástrojov.

**MCP server** - Služba, ktorá vystavuje nástroje cez MCP s jasnými popismi a schémami parametrov.

**McpToolProvider** - Komponent LangChain4j, ktorý zabaluje MCP nástroje pre použitie v AI službách a agentoch.

**McpTransport** - Rozhranie pre MCP komunikáciu. Implementácie zahŕňajú Stdio a HTTP.

**Stdio Transport** - Lokálny transport cez stdin/stdout. Užitočný pre prístup k súborovému systému alebo príkazovým nástrojom.

**StdioMcpTransport** - LangChain4j implementácia, ktorá spúšťa MCP server ako podproces.

**Objavovanie nástrojov (Tool Discovery)** - Klient vyhľadáva na serveri dostupné nástroje s popismi a schémami.

## Azure služby - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Cloudové vyhľadávanie s vektorovou podporou. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Nástroj na nasadenie Azure zdrojov.

**Azure OpenAI** - Enterprise AI služba Microsoftu.

**Bicep** - Infrastruktúra ako kód pre Azure. [Sprievodca infraštruktúrou](../01-introduction/infra/README.md)

**Názov nasadenia (Deployment Name)** - Názov nasadenia modelu v Azure.

**GPT-5** - Najnovší model OpenAI s kontrolou rozumovania. [Modul 02](../02-prompt-engineering/README.md)

## Testovanie a vývoj - [Sprievodca testovaním](TESTING.md)

**Dev Container** - Kontajnerizované vývojové prostredie. [Konfigurácia](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Bezplatná AI modelová aréna. [Modul 00](../00-quick-start/README.md)

**Testovanie v pamäti (In-Memory Testing)** - Testovanie s úložiskom v pamäti.

**Integračné testovanie (Integration Testing)** - Testovanie s reálnou infraštruktúrou.

**Maven** - Java nástroj na automatizáciu buildov.

**Mockito** - Java framework na mockovanie.

**Spring Boot** - Java aplikačný framework. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vyhlásenie o zodpovednosti**:  
Tento dokument bol preložený pomocou AI prekladateľskej služby [Co-op Translator](https://github.com/Azure/co-op-translator). Aj keď sa snažíme o presnosť, majte, prosím, na pamäti, že automatizované preklady môžu obsahovať chyby alebo nepresnosti. Originálny dokument v jeho pôvodnom jazyku by mal byť považovaný za autoritatívny zdroj. Pre kritické informácie sa odporúča profesionálny ľudský preklad. Nie sme zodpovední za žiadne nepochopenia alebo nesprávne výklady vyplývajúce z použitia tohto prekladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->