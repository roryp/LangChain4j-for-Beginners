<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T04:22:21+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "cs"
}
-->
# Glosář LangChain4j

## Obsah

- [Základní pojmy](../../../docs)
- [Komponenty LangChain4j](../../../docs)
- [Pojmy AI/ML](../../../docs)
- [Tvorba promptů](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenti a nástroje](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Služby Azure](../../../docs)
- [Testování a vývoj](../../../docs)

Rychlý přehled termínů a konceptů používaných v celém kurzu.

## Základní pojmy

**AI Agent** - Systém, který využívá umělou inteligenci k odvozování a autonomnímu jednání. [Modul 04](../04-tools/README.md)

**Chain** - Sekvence operací, kde výstup slouží jako vstup pro další krok.

**Chunking** - Rozdělování dokumentů na menší části. Obvyklé: 300-500 tokenů s překryvem. [Modul 03](../03-rag/README.md)

**Context Window** - Maximální počet tokenů, které model může zpracovat. GPT-5: 400K tokenů.

**Embeddings** - Číselné vektory reprezentující význam textu. [Modul 03](../03-rag/README.md)

**Function Calling** - Model generuje strukturované požadavky pro volání externích funkcí. [Modul 04](../04-tools/README.md)

**Hallucination** - Když modely generují nesprávné, ale věrohodně znějící informace.

**Prompt** - Textový vstup pro jazykový model. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Vyhledávání podle významu pomocí embeddings, nikoli klíčových slov. [Modul 03](../03-rag/README.md)

**Stavové vs bezstavové** - Bezstavové: bez paměti. Stavové: uchovává historii konverzace. [Modul 01](../01-introduction/README.md)

**Tokens** - Základní jednotky textu, které modely zpracovávají. Ovlivňují náklady a limity. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Sekvenční vykonávání nástrojů, kde výstup ovlivňuje další volání. [Modul 04](../04-tools/README.md)

## Komponenty LangChain4j

**AiServices** - Vytváří typově bezpečné rozhraní AI služeb.

**OpenAiOfficialChatModel** - Jednotný klient pro modely OpenAI a Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Vytváří embeddings pomocí oficiálního klienta OpenAI (podporuje jak OpenAI, tak Azure OpenAI).

**ChatModel** - Základní rozhraní pro jazykové modely.

**ChatMemory** - Udržuje historii konverzace.

**ContentRetriever** - Vyhledává relevantní části dokumentů pro RAG.

**DocumentSplitter** - Rozděluje dokumenty na části.

**EmbeddingModel** - Převádí text na číselné vektory.

**EmbeddingStore** - Ukládá a načítá embeddings.

**MessageWindowChatMemory** - Udržuje posuvné okno nedávných zpráv.

**PromptTemplate** - Vytváří znovupoužitelné prompty s `{{variable}}` zástupnými poli.

**TextSegment** - Textová část s metadaty. Používá se v RAG.

**ToolExecutionRequest** - Reprezentuje požadavek na vykonání nástroje.

**UserMessage / AiMessage / SystemMessage** - Typy zpráv v konverzaci.

## Pojmy AI/ML

**Few-Shot Learning** - Poskytování příkladů v promptech. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI modely trénované na obrovském množství textových dat.

**Reasoning Effort** - Parametr GPT-5 řídící hloubku uvažování. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Řídí náhodnost výstupu. Nízké = deterministické, vysoké = kreativní.

**Vector Database** - Specializovaná databáze pro embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Provádění úkolů bez příkladů. [Modul 02](../02-prompt-engineering/README.md)

## Tvorba promptů - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Krok-za-krokem uvažování pro vyšší přesnost.

**Constrained Output** - Vynucení specifického formátu nebo struktury.

**High Eagerness** - Vzor GPT-5 pro důkladné uvažování.

**Low Eagerness** - Vzor GPT-5 pro rychlé odpovědi.

**Multi-Turn Conversation** - Udržování kontextu napříč výměnami.

**Role-Based Prompting** - Nastavení persony modelu pomocí systémových zpráv.

**Self-Reflection** - Model hodnotí a vylepšuje svůj výstup.

**Structured Analysis** - Pevný rámec pro hodnocení.

**Task Execution Pattern** - Plán → Provedení → Shrnutí.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Načíst → rozdělit do částí → vytvořit embeddings → uložit.

**In-Memory Embedding Store** - Neperzistentní úložiště pro testování.

**RAG** - Kombinuje vyhledávání s generováním, aby byly odpovědi podložené zdroji.

**Similarity Score** - Míra (0-1) sémantické podobnosti.

**Source Reference** - Metadata o získaném obsahu.

## Agenti a nástroje - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Označuje metody v Javě jako nástroje volatelné AI.

**ReAct Pattern** - Zvažuj → Jednej → Pozoruj → Opakuj.

**Session Management** - Oddělené kontexty pro různé uživatele.

**Tool** - Funkce, kterou může AI agent zavolat.

**Tool Description** - Dokumentace účelu nástroje a jeho parametrů.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**MCP** - Standard pro připojování AI aplikací k externím nástrojům.

**MCP Client** - Aplikace, která se připojuje k MCP serverům.

**MCP Server** - Služba, která zpřístupňuje nástroje přes MCP.

**Stdio Transport** - Server jako podproces komunikující přes stdin/stdout.

**Tool Discovery** - Klient dotazuje server na dostupné nástroje.

## Služby Azure - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Cloudové vyhledávání s vektorovými schopnostmi. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Nasazuje Azure zdroje.

**Azure OpenAI** - Podniková AI služba Microsoftu.

**Bicep** - Jazyk infrastruktury jako kódu pro Azure. [Průvodce infrastrukturou](../01-introduction/infra/README.md)

**Deployment Name** - Název nasazení modelu v Azure.

**GPT-5** - Nejnovější model OpenAI s řízením uvažování. [Modul 02](../02-prompt-engineering/README.md)

## Testování a vývoj - [Průvodce testováním](TESTING.md)

**Dev Container** - Kontejnerizované vývojové prostředí. [Konfigurace](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Bezplatné hřiště pro AI modely. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testování s in-memory úložištěm.

**Integration Testing** - Testování s reálnou infrastrukturou.

**Maven** - Nástroj pro automatizaci sestavení Java projektů.

**Mockito** - Mockovací framework pro Javu.

**Spring Boot** - Framework pro Java aplikace. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Prohlášení o vyloučení odpovědnosti:
Tento dokument byl přeložen pomocí AI překladatelské služby Co‑op Translator (https://github.com/Azure/co-op-translator). Přestože usilujeme o přesnost, mějte prosím na paměti, že automatické překlady mohou obsahovat chyby nebo nepřesnosti. Původní dokument v jeho originálním jazyce by měl být považován za autoritativní zdroj. Pro zásadní informace se doporučuje profesionální lidský překlad. Nejsme odpovědni za žádná nedorozumění nebo chybné výklady vyplývající z použití tohoto překladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->