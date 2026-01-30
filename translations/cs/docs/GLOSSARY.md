# Slovník LangChain4j

## Obsah

- [Základní pojmy](../../../docs)
- [Komponenty LangChain4j](../../../docs)
- [Pojmy AI/ML](../../../docs)
- [Bezpečnostní opatření](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agent a nástroje](../../../docs)
- [Agentní modul](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure služby](../../../docs)
- [Testování a vývoj](../../../docs)

Rychlá reference pojmů a konceptů používaných v kurzu.

## Základní pojmy

**AI Agent** - Systém, který využívá AI k autonomnímu uvažování a jednání. [Modul 04](../04-tools/README.md)

**Chain** - Sekvence operací, kde výstup je vstupem do dalšího kroku.

**Chunking** - Rozdělení dokumentů na menší části. Typicky 300–500 tokenů s překryvem. [Modul 03](../03-rag/README.md)

**Context Window** - Maximální počet tokenů, které model může zpracovat. GPT-5: 400K tokenů.

**Embeddings** - Číselné vektory reprezentující význam textu. [Modul 03](../03-rag/README.md)

**Function Calling** - Model generuje strukturované požadavky pro volání externích funkcí. [Modul 04](../04-tools/README.md)

**Halucinace** - Když modely generují nesprávné, ale věrohodné informace.

**Prompt** - Textový vstup do jazykového modelu. [Modul 02](../02-prompt-engineering/README.md)

**Sémantické vyhledávání** - Vyhledávání podle významu pomocí embeddingů, nikoliv klíčových slov. [Modul 03](../03-rag/README.md)

**Stavové vs nestavové** - Nestavové: bez paměti. Stavové: uchovává historii konverzace. [Modul 01](../01-introduction/README.md)

**Tokeny** - Základní textové jednotky zpracovávané modely. Ovlivňují náklady a limity. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Sekvenční spouštění nástrojů, kde výstup ovlivňuje další volání. [Modul 04](../04-tools/README.md)

## Komponenty LangChain4j

**AiServices** - Vytváří typově bezpečné rozhraní AI služeb.

**OpenAiOfficialChatModel** - Jednotný klient pro modely OpenAI a Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Vytváří embeddingy pomocí oficiálního klienta OpenAI (podporuje OpenAI i Azure OpenAI).

**ChatModel** - Hlavní rozhraní pro jazykové modely.

**ChatMemory** - Uchovává historii konverzace.

**ContentRetriever** - Najde relevantní části dokumentů pro RAG.

**DocumentSplitter** - Rozděluje dokumenty na části.

**EmbeddingModel** - Převádí text na číselné vektory.

**EmbeddingStore** - Ukládá a získává embeddingy.

**MessageWindowChatMemory** - Uchovává posuvné okno nedávných zpráv.

**PromptTemplate** - Vytváří znovupoužitelné prompt šablony s placeholdery `{{variable}}`.

**TextSegment** - Textový úsek s metadaty. Používá se v RAG.

**ToolExecutionRequest** - Reprezentuje požadavek na provedení nástroje.

**UserMessage / AiMessage / SystemMessage** - Typy konverzačních zpráv.

## Pojmy AI/ML

**Few-Shot Learning** - Poskytování příkladů v promtech. [Modul 02](../02-prompt-engineering/README.md)

**Velký jazykový model (LLM)** - AI modely trénované na rozsáhlých textových datech.

**Úsilí o uvažování** - Parametr GPT-5 řídící hloubku uvažování. [Modul 02](../02-prompt-engineering/README.md)

**Teplota** - Řídí náhodnost výstupu. Nízká=deterministický, vysoká=tvůrčí.

**Vektorová databáze** - Specializovaná databáze pro embeddingy. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Provádění úkolů bez příkladů. [Modul 02](../02-prompt-engineering/README.md)

## Bezpečnostní opatření - [Modul 00](../00-quick-start/README.md)

**Defense in Depth** - Víceúrovňový bezpečnostní přístup kombinující aplikační ochrany s bezpečnostními filtry poskytovatelů.

**Hard Block** - Poskytovatel vrací HTTP 400 chybu při vážném porušení obsahu.

**InputGuardrail** - Rozhraní LangChain4j pro validaci vstupů uživatelů před dosažením LLM. Šetří náklady a zpoždění blokováním škodlivých promptů včas.

**InputGuardrailResult** - Návratový typ validace guardrail: `success()` nebo `fatal("důvod")`.

**OutputGuardrail** - Rozhraní pro validaci AI odpovědí před jejich vrácením uživatelům.

**Bezpečnostní filtry poskytovatelů** - Vestavěné filtry obsahu od poskytovatelů AI (např. GitHub Models), které zachytávají porušení na úrovni API.

**Soft Refusal** - Model zdvořile odmítne odpovědět bez vyvolání chyby.

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Postupné uvažování pro lepší přesnost.

**Omezený výstup** - Vynucení specifického formátu nebo struktury.

**Vysoká ochota** - Vzor GPT-5 pro důkladné uvažování.

**Nízká ochota** - Vzor GPT-5 pro rychlé odpovědi.

**Vícenásobná konverzace** - Uchovávání kontextu přes výměny.

**Role-Based Prompting** - Nastavení persony modelu pomocí systémových zpráv.

**Seberozbor** - Model hodnotí a zlepšuje svůj výstup.

**Strukturovaná analýza** - Fixní vyhodnocovací rámec.

**Vzor provedení úkolu** - Plán → Provést → Shrnutí.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Procesní pipeline dokumentů** - Načíst → rozdělit → vložit do embeddingů → uložit.

**In-Memory Embedding Store** - Nepersistentní úložiště pro testování.

**RAG** - Kombinuje vyhledávání s generováním pro zakotvení odpovědí.

**Similarity Score** - Míra (0-1) sémantické podobnosti.

**Zdrojový odkaz** - Metadata o vyhledaném obsahu.

## Agent a nástroje - [Modul 04](../04-tools/README.md)

**@Tool Anotace** - Označuje Java metody jako nástroje volatelné AI.

**ReAct Vzor** - Uvažuj → Jednej → Pozoruj → Opakuj.

**Správa relací** - Oddělené kontexty pro různé uživatele.

**Tool** - Funkce, kterou AI agent může zavolat.

**Popis nástroje** - Dokumentace účelu a parametrů nástroje.

## Agentní modul - [Modul 05](../05-mcp/README.md)

**@Agent Anotace** - Označuje rozhraní jako AI agenti s deklarativní definicí chování.

**Agent Listener** - Hook pro sledování vykonávání agenta přes `beforeAgentInvocation()` a `afterAgentInvocation()`.

**Agentní rozsah** - Sdílená paměť, kde agenti ukládají výstupy s klíčem `outputKey` pro spotřebu downstream agentů.

**AgenticServices** - Továrna na vytváření agentů pomocí `agentBuilder()` a `supervisorBuilder()`.

**Podmíněný workflow** - Směrování podle podmínek k různým specialistickým agentům.

**Human-in-the-Loop** - Workflow vzor přidávající lidské kontrolní body pro schválení nebo revizi obsahu.

**langchain4j-agentic** - Maven závislost pro deklarativní stavbu agentů (experimentální).

**Smyčkový workflow** - Iteruje vykonávání agenta, dokud není splněna podmínka (např. skóre kvality ≥ 0.8).

**outputKey** - Parametr anotace agenta určující, kde jsou výsledky uloženy v Agentním rozsahu.

**Paralelní workflow** - Současné spuštění více agentů pro nezávislé úkoly.

**Strategie odpovědi** - Jak supervizor formuluje konečnou odpověď: POSLEDNÍ, SHRNUJÍCÍ nebo OHODNOCENÁ.

**Sekvenční workflow** - Spouštění agentů v pořadí, kde výstup plyne do dalšího kroku.

**Vzor supervizorského agenta** - Pokročilý agentní vzor, kde supervizor LLM dynamicky rozhoduje, které pod-agenty povolat.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** - Maven závislost pro integraci MCP v LangChain4j.

**MCP** - Model Context Protocol: standard pro připojování AI aplikací k externím nástrojům. Naprogramuj jednou, používej všude.

**MCP Client** - Aplikace, která se připojuje k MCP serverům k objevování a používání nástrojů.

**MCP Server** - Služba vystavující nástroje přes MCP s jasnými popisy a schématy parametrů.

**McpToolProvider** - Komponenta LangChain4j obalující MCP nástroje pro použití v AI službách a agentech.

**McpTransport** - Rozhraní pro MCP komunikaci. Implementace zahrnují Stdio a HTTP.

**Stdio Transport** - Lokální procesní transport přes stdin/stdout. Užívá se pro přístup k souborovému systému nebo příkazovým nástrojům.

**StdioMcpTransport** - Implementace LangChain4j spouštějící MCP server jako podproces.

**Objevování nástrojů** - Klient dotazuje server na dostupné nástroje s popisy a schématy.

## Azure služby - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Cloudové vyhledávání s vektorovými schopnostmi. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Nástroj pro nasazení zdrojů v Azure.

**Azure OpenAI** - Podniková AI služba Microsoftu.

**Bicep** - Jazyk pro infrastrukturu jako kód v Azure. [Průvodce infrastrukturou](../01-introduction/infra/README.md)

**Název nasazení** - Název pro nasazení modelu v Azure.

**GPT-5** - Nejnovější model OpenAI s řízením uvažování. [Modul 02](../02-prompt-engineering/README.md)

## Testování a vývoj - [Testovací průvodce](TESTING.md)

**Dev Container** - Kontejnerizované vývojové prostředí. [Konfigurace](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Bezplatné AI modelové hřiště. [Modul 00](../00-quick-start/README.md)

**Testování v paměti** - Testování s úložištěm v paměti.

**Integrační testování** - Testování s reálnou infrastrukturou.

**Maven** - Nástroj pro automatizaci sestavení v Javě.

**Mockito** - Framework pro mocking v Javě.

**Spring Boot** - Java aplikační framework. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Prohlášení o vyloučení odpovědnosti**:
Tento dokument byl přeložen pomocí AI překladatelské služby [Co-op Translator](https://github.com/Azure/co-op-translator). I když usilujeme o přesnost, mějte prosím na paměti, že automatické překlady mohou obsahovat chyby nebo nepřesnosti. Původní dokument v jeho mateřském jazyce by měl být považován za závazný zdroj. Pro kritické informace se doporučuje profesionální lidský překlad. Nejsme odpovědni za jakékoli nedorozumění nebo nesprávné interpretace vyplývající z použití tohoto překladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->