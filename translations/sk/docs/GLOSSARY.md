<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T04:38:40+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sk"
}
-->
# LangChain4j Glosár

## Obsah

- [Jadrá koncepty](../../../docs)
- [Komponenty LangChain4j](../../../docs)
- [AI/ML koncepty](../../../docs)
- [Návrh promptov - [Module 02](../02-prompt-engineering/README.md)](#prompt-engineering---module-02)
- [RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)](#rag-retrieval-augmented-generation---module-03)
- [Agenti a nástroje - [Module 04](../04-tools/README.md)](#agents-and-tools---module-04)
- [Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)](#model-context-protocol-mcp---module-05)
- [Služby Azure - [Module 01](../01-introduction/README.md)](#azure-services---module-01)
- [Testovanie a vývoj - [Testing Guide](TESTING.md)](#testing-and-development---testing-guide)

Rýchla referencia pre pojmy a koncepty používané v priebehu kurzu.

## Core Concepts

**AI Agent** - Systém, ktorý využíva umelú inteligenciu na uvažovanie a autonómne konanie. [Module 04](../04-tools/README.md)

**Chain** - Sekvencia operácií, kde výstup slúži ako vstup do ďalšieho kroku.

**Chunking** - Rozdelenie dokumentov na menšie časti. Typické: 300-500 tokenov s prekrytím. [Module 03](../03-rag/README.md)

**Context Window** - Maximálny počet tokenov, ktoré model môže spracovať. GPT-5: 400K tokenov.

**Embeddings** - Číselné vektory reprezentujúce význam textu. [Module 03](../03-rag/README.md)

**Function Calling** - Model generuje štruktúrované požiadavky na volanie externých funkcií. [Module 04](../04-tools/README.md)

**Hallucination** - Keď modely generujú nesprávne, ale dôveryhodne vyzerajúce informácie.

**Prompt** - Textový vstup do jazykového modelu. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Vyhľadávanie podľa významu pomocou embeddingov, nie kľúčových slov. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Bezstavový: bez pamäte. Stavový: uchováva históriu konverzácie. [Module 01](../01-introduction/README.md)

**Tokens** - Základné textové jednotky, ktoré modely spracovávajú. Ovplyvňujú náklady a limity. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sekvenčné vykonávanie nástrojov, kde výstup informuje o ďalšom volaní. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Vytvára typovo bezpečné rozhrania AI služieb.

**OpenAiOfficialChatModel** - Zjednotený klient pre modely OpenAI a Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Vytvára embeddingy pomocou OpenAI Official klienta (podporuje OpenAI aj Azure OpenAI).

**ChatModel** - Jadro rozhrania pre jazykové modely.

**ChatMemory** - Uchováva históriu konverzácie.

**ContentRetriever** - Nájde relevantné časti dokumentov pre RAG.

**DocumentSplitter** - Rozdeľuje dokumenty na kúsky.

**EmbeddingModel** - Konvertuje text na číselné vektory.

**EmbeddingStore** - Ukladá a vyhľadáva embeddingy.

**MessageWindowChatMemory** - Udržuje posuvné okno posledných správ.

**PromptTemplate** - Vytvára znovupoužiteľné prompty s `{{variable}}` zástupcami.

**TextSegment** - Textový úsek s metadátami. Používa sa v RAG.

**ToolExecutionRequest** - Reprezentuje požiadavku na vykonanie nástroja.

**UserMessage / AiMessage / SystemMessage** - Typy správ v konverzácii.

## AI/ML Concepts

**Few-Shot Learning** - Poskytovanie príkladov v promptoch. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Veľké jazykové modely trénované na rozsiahlych textových dátach.

**Reasoning Effort** - Miera rozumovania — parameter GPT-5, ktorý ovláda hĺbku uvažovania. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Ovláda náhodnosť výstupu. Nízka = deterministické, vysoká = kreatívne.

**Vector Database** - Špecializovaná databáza pre embeddingy. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Vykonávanie úloh bez príkladov. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Postupné uvažovanie krok za krokom pre lepšiu presnosť.

**Constrained Output** - Vynútenie konkrétneho formátu alebo štruktúry výstupu.

**High Eagerness** - Vzor GPT-5 pre dôkladné uvažovanie.

**Low Eagerness** - Vzor GPT-5 pre rýchle odpovede.

**Multi-Turn Conversation** - Udržiavanie kontextu cez viaceré výmeny.

**Role-Based Prompting** - Nastavenie osobnosti modelu prostredníctvom systémových správ.

**Self-Reflection** - Model hodnotí a zlepšuje svoj výstup.

**Structured Analysis** - Pevný hodnotiaci rámec.

**Task Execution Pattern** - Naplánuj → Vykonaj → Zhrň.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Načítať → rozdeliť → embedovať → uložiť.

**In-Memory Embedding Store** - Nepersistentné úložisko pre testovanie.

**RAG** - Kombinuje vyhľadávanie s generovaním, aby zakorenil odpovede.

**Similarity Score** - Miera (0-1) sémantickej podobnosti.

**Source Reference** - Metadáta o získanom obsahu.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Označuje Java metódy ako volateľné nástrojmi AI.

**ReAct Pattern** - Premýšľaj → Konaj → Pozoruj → Opakuj.

**Session Management** - Oddelené kontexty pre rôznych používateľov.

**Tool** - Funkcia, ktorú môže AI agent zavolať.

**Tool Description** - Dokumentácia účelu nástroja a jeho parametrov.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - Štandard pre pripájanie AI aplikácií k externým nástrojom.

**MCP Client** - Aplikácia, ktorá sa pripája k MCP serverom.

**MCP Server** - Služba vystavujúca nástroje cez MCP.

**Stdio Transport** - Server ako podproces cez stdin/stdout.

**Tool Discovery** - Klient dotazuje server na dostupné nástroje.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Cloudové vyhľadávanie s vektorovými možnosťami. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Nasadzuje Azure zdroje.

**Azure OpenAI** - Podniková AI služba Microsoftu.

**Bicep** - Jazyk infraštruktúry ako kódu pre Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Názov nasadenia modelu v Azure.

**GPT-5** - Najnovší model OpenAI s kontrolou rozumovania. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Kontajnerizované vývojové prostredie. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Bezplatné prostredie pre AI modely. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testovanie s in-memory úložiskom.

**Integration Testing** - Testovanie s reálnou infraštruktúrou.

**Maven** - Nástroj na automatizáciu buildov v Jave.

**Mockito** - Framework na mockovanie v Jave.

**Spring Boot** - Java aplikačný framework. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Vylúčenie zodpovednosti:
Tento dokument bol preložený pomocou AI prekladateľskej služby [Co-op Translator](https://github.com/Azure/co-op-translator). Aj keď sa snažíme o presnosť, berte prosím na vedomie, že automatické preklady môžu obsahovať chyby alebo nepresnosti. Pôvodný dokument v jeho pôvodnom jazyku by sa mal považovať za autoritatívny zdroj. Pre kritické informácie odporúčame profesionálny ľudský preklad. Nezodpovedáme za žiadne nedorozumenia ani nesprávne interpretácie vzniknuté použitím tohto prekladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->