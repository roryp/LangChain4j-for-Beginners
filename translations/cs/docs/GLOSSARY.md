<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:17:39+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "cs"
}
-->
# LangChain4j Slovník pojmů

## Obsah

- [Základní pojmy](../../../docs)
- [Komponenty LangChain4j](../../../docs)
- [Pojmy AI/ML](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenti a nástroje](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure služby](../../../docs)
- [Testování a vývoj](../../../docs)

Rychlá reference pojmů a konceptů používaných v celém kurzu.

## Základní pojmy

**AI Agent** - Systém, který používá AI k autonomnímu uvažování a jednání. [Modul 04](../04-tools/README.md)

**Chain** - Sekvence operací, kde výstup je vstupem do dalšího kroku.

**Chunking** - Rozdělení dokumentů na menší části. Typicky: 300-500 tokenů s překryvem. [Modul 03](../03-rag/README.md)

**Context Window** - Maximální počet tokenů, které model může zpracovat. GPT-5: 400K tokenů.

**Embeddings** - Číselné vektory reprezentující význam textu. [Modul 03](../03-rag/README.md)

**Function Calling** - Model generuje strukturované požadavky na volání externích funkcí. [Modul 04](../04-tools/README.md)

**Halucinace** - Když modely generují nesprávné, ale věrohodné informace.

**Prompt** - Textový vstup do jazykového modelu. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Vyhledávání podle významu pomocí embeddings, nikoli klíčových slov. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: bez paměti. Stateful: uchovává historii konverzace. [Modul 01](../01-introduction/README.md)

**Tokens** - Základní textové jednotky, které modely zpracovávají. Ovlivňuje náklady a limity. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Sekvenční spouštění nástrojů, kde výstup ovlivňuje další volání. [Modul 04](../04-tools/README.md)

## Komponenty LangChain4j

**AiServices** - Vytváří typově bezpečné rozhraní AI služeb.

**OpenAiOfficialChatModel** - Jednotný klient pro modely OpenAI a Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Vytváří embeddings pomocí oficiálního klienta OpenAI (podporuje OpenAI i Azure OpenAI).

**ChatModel** - Základní rozhraní pro jazykové modely.

**ChatMemory** - Uchovává historii konverzace.

**ContentRetriever** - Vyhledává relevantní části dokumentů pro RAG.

**DocumentSplitter** - Rozděluje dokumenty na části.

**EmbeddingModel** - Převádí text na číselné vektory.

**EmbeddingStore** - Ukládá a načítá embeddings.

**MessageWindowChatMemory** - Uchovává posuvné okno nedávných zpráv.

**PromptTemplate** - Vytváří znovupoužitelné prompty s `{{variable}}` zástupci.

**TextSegment** - Textový úsek s metadaty. Používá se v RAG.

**ToolExecutionRequest** - Reprezentuje požadavek na spuštění nástroje.

**UserMessage / AiMessage / SystemMessage** - Typy zpráv v konverzaci.

## Pojmy AI/ML

**Few-Shot Learning** - Poskytování příkladů v promptech. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI modely trénované na rozsáhlých textových datech.

**Reasoning Effort** - Parametr GPT-5 ovlivňující hloubku uvažování. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Řídí náhodnost výstupu. Nízká=deterministický, vysoká=tvůrčí.

**Vector Database** - Specializovaná databáze pro embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Provádění úkolů bez příkladů. [Modul 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Krok za krokem uvažování pro lepší přesnost.

**Constrained Output** - Vynucení specifického formátu nebo struktury.

**High Eagerness** - Vzor GPT-5 pro důkladné uvažování.

**Low Eagerness** - Vzor GPT-5 pro rychlé odpovědi.

**Multi-Turn Conversation** - Udržování kontextu přes více výměn.

**Role-Based Prompting** - Nastavení osobnosti modelu pomocí systémových zpráv.

**Self-Reflection** - Model hodnotí a zlepšuje svůj výstup.

**Structured Analysis** - Pevný hodnotící rámec.

**Task Execution Pattern** - Plánuj → Proveď → Shrň.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Načti → rozděl → vlož do embeddingů → ulož.

**In-Memory Embedding Store** - Nepersistentní úložiště pro testování.

**RAG** - Kombinuje vyhledávání s generováním pro podložené odpovědi.

**Similarity Score** - Míra (0-1) sémantické podobnosti.

**Source Reference** - Metadata o získaném obsahu.

## Agenti a nástroje - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Označuje Java metody jako AI-volatelné nástroje.

**ReAct Pattern** - Uvažuj → Jednej → Pozoruj → Opakuj.

**Session Management** - Oddělené kontexty pro různé uživatele.

**Tool** - Funkce, kterou může AI agent volat.

**Tool Description** - Dokumentace účelu a parametrů nástroje.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**Docker Transport** - MCP server v Docker kontejneru.

**MCP** - Standard pro připojení AI aplikací k externím nástrojům.

**MCP Client** - Aplikace připojující se k MCP serverům.

**MCP Server** - Služba zpřístupňující nástroje přes MCP.

**Server-Sent Events (SSE)** - Streamování ze serveru na klienta přes HTTP.

**Stdio Transport** - Server jako podproces přes stdin/stdout.

**Streamable HTTP Transport** - HTTP se SSE pro komunikaci v reálném čase.

**Tool Discovery** - Klient dotazuje server na dostupné nástroje.

## Azure služby - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Cloudové vyhledávání s vektorovými schopnostmi. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Nasazuje Azure zdroje.

**Azure OpenAI** - Podniková AI služba Microsoftu.

**Bicep** - Jazyk pro infrastrukturu jako kód v Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Název nasazení modelu v Azure.

**GPT-5** - Nejnovější model OpenAI s řízením uvažování. [Modul 02](../02-prompt-engineering/README.md)

## Testování a vývoj - [Testing Guide](TESTING.md)

**Dev Container** - Kontejnerizované vývojové prostředí. [Konfigurace](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Bezplatné AI modelové hřiště. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testování s úložištěm v paměti.

**Integration Testing** - Testování s reálnou infrastrukturou.

**Maven** - Nástroj pro automatizaci sestavení v Javě.

**Mockito** - Framework pro mockování v Javě.

**Spring Boot** - Java aplikační framework. [Modul 01](../01-introduction/README.md)

**Testcontainers** - Docker kontejnery v testech.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Prohlášení o vyloučení odpovědnosti**:  
Tento dokument byl přeložen pomocí AI překladatelské služby [Co-op Translator](https://github.com/Azure/co-op-translator). Přestože usilujeme o přesnost, mějte prosím na paměti, že automatické překlady mohou obsahovat chyby nebo nepřesnosti. Původní dokument v jeho mateřském jazyce by měl být považován za autoritativní zdroj. Pro důležité informace se doporučuje profesionální lidský překlad. Nejsme odpovědní za jakékoli nedorozumění nebo nesprávné výklady vyplývající z použití tohoto překladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->