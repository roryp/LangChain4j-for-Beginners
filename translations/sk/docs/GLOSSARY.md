<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:18:21+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "sk"
}
-->
# LangChain4j Slovník

## Obsah

- [Základné pojmy](../../../docs)
- [Komponenty LangChain4j](../../../docs)
- [Pojmy AI/ML](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenti a Nástroje](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure služby](../../../docs)
- [Testovanie a Vývoj](../../../docs)

Rýchla referencia pojmov a konceptov používaných v celom kurze.

## Základné pojmy

**AI Agent** - Systém, ktorý používa AI na uvažovanie a autonómne konanie. [Modul 04](../04-tools/README.md)

**Chain** - Sekvencia operácií, kde výstup je vstupom do ďalšieho kroku.

**Chunking** - Rozdelenie dokumentov na menšie časti. Typicky: 300-500 tokenov s prekrytím. [Modul 03](../03-rag/README.md)

**Context Window** - Maximálny počet tokenov, ktoré model dokáže spracovať. GPT-5: 400K tokenov.

**Embeddings** - Číselné vektory reprezentujúce význam textu. [Modul 03](../03-rag/README.md)

**Function Calling** - Model generuje štruktúrované požiadavky na volanie externých funkcií. [Modul 04](../04-tools/README.md)

**Hallucination** - Keď modely generujú nesprávne, ale pravdepodobné informácie.

**Prompt** - Textový vstup do jazykového modelu. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Vyhľadávanie podľa významu pomocou embeddings, nie kľúčových slov. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: bez pamäte. Stateful: uchováva históriu konverzácie. [Modul 01](../01-introduction/README.md)

**Tokens** - Základné textové jednotky, ktoré modely spracovávajú. Ovplyvňujú náklady a limity. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Sekvenčné vykonávanie nástrojov, kde výstup ovplyvňuje ďalšie volanie. [Modul 04](../04-tools/README.md)

## Komponenty LangChain4j

**AiServices** - Vytvára typovo bezpečné rozhrania AI služieb.

**OpenAiOfficialChatModel** - Zjednotený klient pre OpenAI a Azure OpenAI modely.

**OpenAiOfficialEmbeddingModel** - Vytvára embeddings pomocou OpenAI Official klienta (podporuje OpenAI aj Azure OpenAI).

**ChatModel** - Hlavné rozhranie pre jazykové modely.

**ChatMemory** - Uchováva históriu konverzácie.

**ContentRetriever** - Nájde relevantné časti dokumentov pre RAG.

**DocumentSplitter** - Rozdeľuje dokumenty na časti.

**EmbeddingModel** - Konvertuje text na číselné vektory.

**EmbeddingStore** - Ukladá a načítava embeddings.

**MessageWindowChatMemory** - Uchováva posuvné okno posledných správ.

**PromptTemplate** - Vytvára znovupoužiteľné prompty s `{{variable}}` zástupcami.

**TextSegment** - Textový úsek s metadátami. Používa sa v RAG.

**ToolExecutionRequest** - Reprezentuje požiadavku na vykonanie nástroja.

**UserMessage / AiMessage / SystemMessage** - Typy správ v konverzácii.

## Pojmy AI/ML

**Few-Shot Learning** - Poskytovanie príkladov v promptoch. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI modely trénované na obrovskom množstve textových dát.

**Reasoning Effort** - Parameter GPT-5 riadiaci hĺbku uvažovania. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Riadi náhodnosť výstupu. Nízka=deterministický, vysoká=tvorný.

**Vector Database** - Špecializovaná databáza pre embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Vykonávanie úloh bez príkladov. [Modul 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Postupné uvažovanie pre lepšiu presnosť.

**Constrained Output** - Vynútenie špecifického formátu alebo štruktúry.

**High Eagerness** - Vzor GPT-5 pre dôkladné uvažovanie.

**Low Eagerness** - Vzor GPT-5 pre rýchle odpovede.

**Multi-Turn Conversation** - Udržiavanie kontextu cez viaceré výmeny.

**Role-Based Prompting** - Nastavenie modelovej persony cez systémové správy.

**Self-Reflection** - Model hodnotí a zlepšuje svoj výstup.

**Structured Analysis** - Fixný hodnotiaci rámec.

**Task Execution Pattern** - Plánuj → Vykonaj → Zhrň.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Načítať → rozdeliť → vložiť do embeddingu → uložiť.

**In-Memory Embedding Store** - Nepersistentné úložisko pre testovanie.

**RAG** - Kombinuje vyhľadávanie s generovaním na zakotvenie odpovedí.

**Similarity Score** - Miera (0-1) sémantickej podobnosti.

**Source Reference** - Metadáta o získanom obsahu.

## Agenti a Nástroje - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Označuje Java metódy ako AI-volateľné nástroje.

**ReAct Pattern** - Uvažuj → Konaj → Pozoruj → Opakuj.

**Session Management** - Oddelené kontexty pre rôznych používateľov.

**Tool** - Funkcia, ktorú môže AI agent volať.

**Tool Description** - Dokumentácia účelu a parametrov nástroja.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**Docker Transport** - MCP server v Docker kontejnery.

**MCP** - Štandard pre prepojenie AI aplikácií s externými nástrojmi.

**MCP Client** - Aplikácia, ktorá sa pripája k MCP serverom.

**MCP Server** - Služba vystavujúca nástroje cez MCP.

**Server-Sent Events (SSE)** - Streaming zo servera na klienta cez HTTP.

**Stdio Transport** - Server ako podproces cez stdin/stdout.

**Streamable HTTP Transport** - HTTP so SSE pre komunikáciu v reálnom čase.

**Tool Discovery** - Klient zisťuje dostupné nástroje na serveri.

## Azure služby - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Cloudové vyhľadávanie s vektorovými možnosťami. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Nasadzuje Azure zdroje.

**Azure OpenAI** - Podniková AI služba Microsoftu.

**Bicep** - Jazyk infraštruktúry ako kódu pre Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Názov nasadenia modelu v Azure.

**GPT-5** - Najnovší OpenAI model s riadením uvažovania. [Modul 02](../02-prompt-engineering/README.md)

## Testovanie a Vývoj - [Testing Guide](TESTING.md)

**Dev Container** - Kontajnerizované vývojové prostredie. [Konfigurácia](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Bezplatné AI modelové ihrisko. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testovanie s úložiskom v pamäti.

**Integration Testing** - Testovanie s reálnou infraštruktúrou.

**Maven** - Nástroj na automatizáciu buildov v Jave.

**Mockito** - Java framework na mocking.

**Spring Boot** - Java aplikačný framework. [Modul 01](../01-introduction/README.md)

**Testcontainers** - Docker kontajnery v testoch.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Zrieknutie sa zodpovednosti**:
Tento dokument bol preložený pomocou AI prekladateľskej služby [Co-op Translator](https://github.com/Azure/co-op-translator). Aj keď sa snažíme o presnosť, prosím, majte na pamäti, že automatizované preklady môžu obsahovať chyby alebo nepresnosti. Originálny dokument v jeho pôvodnom jazyku by mal byť považovaný za autoritatívny zdroj. Pre kritické informácie sa odporúča profesionálny ľudský preklad. Nie sme zodpovední za akékoľvek nedorozumenia alebo nesprávne interpretácie vyplývajúce z použitia tohto prekladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->