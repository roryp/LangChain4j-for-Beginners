<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "6c816d130a1fa47570c11907e72d84ae",
  "translation_date": "2026-01-06T00:51:28+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "cs"
}
-->
# Modul 05: Protokol modelového kontextu (MCP)

## Obsah

- [Co se naučíte](../../../05-mcp)
- [Co je MCP?](../../../05-mcp)
- [Jak MCP funguje](../../../05-mcp)
- [Agentní modul](../../../05-mcp)
- [Spuštění příkladů](../../../05-mcp)
  - [Požadavky](../../../05-mcp)
- [Rychlý start](../../../05-mcp)
  - [Operace se soubory (Stdio)](../../../05-mcp)
  - [Supervisor Agent](../../../05-mcp)
    - [Porozumění výstupu](../../../05-mcp)
    - [Strategie odpovědí](../../../05-mcp)
    - [Vysvětlení funkcí agentního modulu](../../../05-mcp)
- [Klíčové pojmy](../../../05-mcp)
- [Gratulujeme!](../../../05-mcp)
  - [Co dál?](../../../05-mcp)

## Co se naučíte

Vybudovali jste konverzační AI, ovládli promptování, zakotvili odpovědi v dokumentech a vytvořili agenty s nástroji. Ale všechny tyto nástroje byly vytvořeny na míru pro vaši konkrétní aplikaci. Co kdybyste mohli vaší AI poskytnout přístup ke standardizovanému ekosystému nástrojů, které může kdokoliv vytvářet a sdílet? V tomto modulu se naučíte přesně to pomocí Protokolu modelového kontextu (MCP) a agentního modulu LangChain4j. Nejprve ukážeme jednoduchý MCP čtečku souborů a potom, jak se snadno integruje do pokročilých agentních pracovních postupů pomocí vzoru Supervisor Agent.

## Co je MCP?

Protokol modelového kontextu (MCP) poskytuje právě to – standardní způsob, jak mohou AI aplikace objevovat a používat externí nástroje. Místo psaní vlastních integrací pro každý zdroj dat nebo službu se připojíte k MCP serverům, které své schopnosti vystavují v jednotném formátu. Vaše AI agent pak tyto nástroje může automaticky objevit a používat.

<img src="../../../translated_images/cs/mcp-comparison.9129a881ecf10ff5.png" alt="MCP Comparison" width="800"/>

*Před MCP: složité bodové integrace. Po MCP: jeden protokol, nekonečné možnosti.*

MCP řeší základní problém ve vývoji AI: každá integrace je na míru. Chcete přístup k GitHubu? Vlastní kód. Chcete číst soubory? Vlastní kód. Chcete dotazovat databázi? Vlastní kód. A žádná z těchto integrací nefunguje s jinými AI aplikacemi.

MCP toto standardizuje. MCP server vystavuje nástroje s jasnými popisy a schématy parametrů. Jakýkoliv MCP klient se může připojit, objevit dostupné nástroje a používat je. Jednou postavíte, všude použijete.

<img src="../../../translated_images/cs/mcp-architecture.b3156d787a4ceac9.png" alt="MCP Architecture" width="800"/>

*Architektura Protokolu modelového kontextu – standardizované objevování a spouštění nástrojů*

## Jak MCP funguje

**Architektura klient-server**

MCP používá model klient-server. Servery poskytují nástroje – čtení souborů, dotazování databází, volání API. Klienti (vaše AI aplikace) se k serverům připojují a používají jejich nástroje.

Pro použití MCP s LangChain4j přidejte tuto Maven závislost:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**Objevování nástrojů**

Když se váš klient připojí k MCP serveru, zeptá se: „Jaké nástroje máte?“ Server odpoví seznamem dostupných nástrojů, každý s popisy a schématy parametrů. Váš AI agent se pak rozhodne, které nástroje použije na základě požadavků uživatele.

**Přenosové mechanismy**

MCP podporuje různé přenosové mechanismy. Tento modul předvádí Stdio přenos pro lokální procesy:

<img src="../../../translated_images/cs/transport-mechanisms.2791ba7ee93cf020.png" alt="Transport Mechanisms" width="800"/>

*Přenosové mechanismy MCP: HTTP pro vzdálené servery, Stdio pro lokální procesy*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Pro lokální procesy. Vaše aplikace spustí server jako podproces a komunikuje přes standardní vstup/výstup. Vhodné pro přístup k souborovému systému nebo příkazovým řádkům.

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@2025.12.18",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 Vyzkoušejte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otevřete [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) a zeptejte se:
> - „Jak funguje Stdio přenos a kdy ho použít místo HTTP?“
> - „Jak LangChain4j spravuje životní cyklus spouštěných MCP serverových procesů?“
> - „Jaká jsou bezpečnostní rizika, když AI poskytujete přístup k souborovému systému?“

## Agentní modul

Zatímco MCP poskytuje standardizované nástroje, agentní modul LangChain4j poskytuje deklarativní způsob, jak sestavit agenty, kteří tyto nástroje orchestrují. Anotace `@Agent` a `AgenticServices` vám umožní definovat chování agenta přes rozhraní místo imperativního kódu.

V tomto modulu poznáte vzor **Supervisor Agent** — pokročilý agentní AI přístup, kdy „supervizor“ agent dynamicky rozhoduje, které pod-agenty vyvolat podle požadavku uživatele. Obě koncepce spojíme tak, že jednomu z našich pod-agentů dáme MCP-poháněné schopnosti přístupu k souborům.

Pro použití agentního modulu přidejte tuto Maven závislost:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ Experimentální:** Modul `langchain4j-agentic` je **experimentální** a může se měnit. Stabilní způsob tvorby AI asistentů je stále přes `langchain4j-core` s vlastními nástroji (Modul 04).

## Spuštění příkladů

### Požadavky

- Java 21+, Maven 3.9+
- Node.js 16+ a npm (pro MCP servery)
- Proměnné prostředí nakonfigurované v souboru `.env` (ze základního adresáře):
  - `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (stejné jako v Modulech 01-04)

> **Poznámka:** Pokud jste ještě nenakonfigurovali proměnné prostředí, podívejte se do [Modulu 00 - Rychlý start](../00-quick-start/README.md) pro instrukce nebo zkopírujte `.env.example` do `.env` v základním adresáři a doplňte své hodnoty.

## Rychlý start

**Použití VS Code:** Stačí pravým tlačítkem kliknout na libovolný demo soubor v Průzkumníku a vybrat **„Run Java“**, nebo použijte konfigurační profily z panelu Spustit a ladit (nejprve přidejte svůj token do `.env` souboru).

**Použití Maven:** Alternativně spusťte příklady z příkazové řádky podle níže uvedených instrukcí.

### Operace se soubory (Stdio)

Ukazuje nástroje založené na lokálně spuštěných podprocesech.

**✅ Není potřeba žádné nastavení** - MCP server se spustí automaticky.

**Použití startovacích skriptů (doporučeno):**

Startovací skripty automaticky načítají proměnné prostředí ze základního `.env` souboru:

**Bash:**
```bash
cd 05-mcp
chmod +x start-stdio.sh
./start-stdio.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start-stdio.ps1
```

**Použití VS Code:** Pravým tlačítkem klikněte na `StdioTransportDemo.java` a vyberte **„Run Java“** (ujistěte se, že váš `.env` je nakonfigurovaný).

Aplikace automaticky spustí MCP server pro souborový systém a přečte lokální soubor. Všimněte si, jak je správa podprocesu zařízena za vás.

**Očekávaný výstup:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Supervisor Agent

**Supervisor Agent pattern** je **flexibilní** forma agentní AI. Supervisor používá LLM, aby autonomně rozhodl, které agenty vyvolat podle požadavku uživatele. V příštím příkladu zkombinujeme MCP-poháněný přístup k souborům s LLM agentem, abychom vytvořili workflow čtení souboru → report s dozorem.

V demu `FileAgent` čte soubor pomocí MCP nástrojů pro souborový systém a `ReportAgent` generuje strukturovanou zprávu s výkonnou rekapitulací (1 věta), 3 klíčovými body a doporučeními. Supervisor automaticky orchestruje tento tok:

<img src="../../../translated_images/cs/agentic.cf84dcda226374e3.png" alt="Agentní modul" width="800"/>

```
┌─────────────┐      ┌──────────────┐
│  FileAgent  │ ───▶ │ ReportAgent  │
│ (MCP tools) │      │  (pure LLM)  │
└─────────────┘      └──────────────┘
   outputKey:           outputKey:
  'fileContent'         'report'
```

Každý agent ukládá svůj výstup do **Agentní scopy** (sdílené paměti), což umožňuje dalším agentům přístup k předchozím výsledkům. To ukazuje, jak se MCP nástroje hladce integrují do agentních pracovních postupů — Supervisor nemusí znát *jak* jsou soubory čteny, pouze že to `FileAgent` umí.

#### Spuštění demoverze

Startovací skripty automaticky načítají proměnné prostředí ze základního `.env` souboru:

**Bash:**
```bash
cd 05-mcp
chmod +x start-supervisor.sh
./start-supervisor.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start-supervisor.ps1
```

**Použití VS Code:** Pravým tlačítkem klikněte na `SupervisorAgentDemo.java` a vyberte **„Run Java“** (ujistěte se, že váš `.env` je nakonfigurovaný).

#### Jak Supervisor funguje

```java
// Krok 1: FileAgent čte soubory pomocí nástrojů MCP
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // Má nástroje MCP pro operace se soubory
        .build();

// Krok 2: ReportAgent generuje strukturované zprávy
ReportAgent reportAgent = AgenticServices.agentBuilder(ReportAgent.class)
        .chatModel(model)
        .build();

// Supervisor řídí pracovní postup soubor → zpráva
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)
        .subAgents(fileAgent, reportAgent)
        .responseStrategy(SupervisorResponseStrategy.LAST)  // Vrátit konečnou zprávu
        .build();

// Supervisor rozhoduje, které agenty vyvolat na základě požadavku
String response = supervisor.invoke("Read the file at /path/file.txt and generate a report");
```

#### Strategie odpovědí

Když nastavujete `SupervisorAgent`, určíte, jak by měl formulovat svou konečnou odpověď uživateli poté, co pod-agenti dokončí úkoly. Dostupné strategie jsou:

| Strategie | Popis |
|-----------|--------|
| **LAST** | Supervisor vrátí výstup posledního volaného pod-agenta nebo nástroje. Toto se hodí, když je poslední agent ve workflow určený k vytvoření kompletní finální odpovědi (např. „Agent shrnutí“ ve výzkumném procesu). |
| **SUMMARY** | Supervisor použije svůj interní jazykový model (LLM) k syntéze shrnutí celé interakce a všech výstupů pod-agentů a toto shrnutí vrátí jako finální odpověď. To poskytuje čistou a sjednocenou odpověď uživateli. |
| **SCORED** | Systém využívá interní LLM k ohodnocení jak poslední odpovědi, tak shrnutí interakce vůči původnímu požadavku uživatele a vrátí tu odpověď, která získá vyšší skóre. |

Kompletní implementaci najdete v [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java).

> **🤖 Vyzkoušejte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otevřete [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) a zeptejte se:
> - „Jak Supervisor rozhoduje, které agenty vyvolat?“
> - „Jaký je rozdíl mezi Supervizorovým a Sekvenčním vzorem workflow?“
> - „Jak mohu přizpůsobit plánovací chování Supervisora?“

#### Porozumění výstupu

Po spuštění demoverze uvidíte strukturovaný průchod tím, jak Supervisor orchestruje více agentů. Zde, co jednotlivé části znamenají:

```
======================================================================
  FILE → REPORT WORKFLOW DEMO
======================================================================

This demo shows a clear 2-step workflow: read a file, then generate a report.
The Supervisor orchestrates the agents automatically based on the request.
```

**Nadpis** představuje koncept workflow: zaměřený pipeline od čtení souboru ke generování reportu.

```
--- WORKFLOW ---------------------------------------------------------
  ┌─────────────┐      ┌──────────────┐
  │  FileAgent  │ ───▶ │ ReportAgent  │
  │ (MCP tools) │      │  (pure LLM)  │
  └─────────────┘      └──────────────┘
   outputKey:           outputKey:
   'fileContent'        'report'

--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]   FileAgent   - Reads files via MCP → stores in 'fileContent'
  [REPORT] ReportAgent - Generates structured report → stores in 'report'
```

**Diagram workflow** ukazuje tok dat mezi agenty. Každý agent má konkrétní roli:
- **FileAgent** čte soubory pomocí MCP nástrojů a ukládá syrový obsah do `fileContent`
- **ReportAgent** spotřebovává tento obsah a vytváří strukturovanou zprávu v `report`

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and generate a report on its contents"
```

**Uživatelský požadavek** ukazuje úkol. Supervisor jej analyzuje a rozhodne se vyvolat FileAgent → ReportAgent.

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor decides which agents to invoke and passes data between them...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source, provider-agnostic Java framework for building LLM...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> ReportAgent (generating structured report)
  |
  |   Input: LangChain4j is an open-source, provider-agnostic Java framew...
  |
  |   Result: Executive Summary...
  +-- [OK] ReportAgent (generating structured report) completed
```

**Supervisor Orchestrace** ukazuje dvoukrokový tok v praxi:
1. **FileAgent** načte soubor přes MCP a uloží obsah
2. **ReportAgent** obdrží obsah a generuje strukturovanou zprávu

Supervisor tato rozhodnutí udělal **autonomně** na základě požadavku uživatele.

```
--- FINAL RESPONSE ---------------------------------------------------
Executive Summary
...

Key Points
...

Recommendations
...

--- AGENTIC SCOPE (Data Flow) ----------------------------------------
  Each agent stores its output for downstream agents to consume:
  * fileContent: LangChain4j is an open-source, provider-agnostic Java framework...
  * report: Executive Summary...
```

#### Vysvětlení funkcí agentního modulu

Příklad ukazuje několik pokročilých funkcí agentního modulu. Podívejme se blíže na Agentní scopu a Agentní posluchače.

**Agentní Scope** ukazuje sdílenou paměť, kam agenti ukládají výsledky pomocí `@Agent(outputKey="...")`. To umožňuje:
- Pozdějším agentům přístup k výstupům agentů předchozích
- Supervisorovi syntetizovat konečnou odpověď
- Vám zkontrolovat, co který agent vytvořil

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String fileContent = scope.readState("fileContent");  // Surová data souboru od FileAgent
String report = scope.readState("report");            // Strukturovaná zpráva od ReportAgent
```

**Agentní posluchači** umožňují monitorování a ladění spuštění agentů. Krok za krokem výstup v demu pochází z AgentListeneru, který se připojuje ke každému volání agenta:
- **beforeAgentInvocation** – Volejte při výběru agenta Supervisorom, abyste viděli, který agent byl vybrán a proč
- **afterAgentInvocation** – Volejte po dokončení agenta, zobrazující jeho výsledek
- **inheritedBySubagents** – Když je true, posluchač sleduje všechny agenty v hierarchii

```java
AgentListener monitor = new AgentListener() {
    private int step = 0;
    
    @Override
    public void beforeAgentInvocation(AgentRequest request) {
        step++;
        System.out.println("  +-- STEP " + step + ": " + request.agentName());
    }
    
    @Override
    public void afterAgentInvocation(AgentResponse response) {
        System.out.println("  +-- [OK] " + response.agentName() + " completed");
    }
    
    @Override
    public boolean inheritedBySubagents() {
        return true; // Propagovat na všechny pod-agenty
    }
};
```

Kromě vzoru Supervisor poskytuje `langchain4j-agentic` modul několik silných vzorů a funkcí workflow:

| Vzor | Popis | Případ použití |
|-------|--------|----------------|
| **Sekvenční** | Spouští agenty postupně, výstup jde na další | Pipeline: výzkum → analýza → report |
| **Paralelní** | Spouští agenty současně | Nezávislé úkoly: počasí + zprávy + akcie |
| **Cyklus** | Iteruje, dokud není splněna podmínka | Hodnocení kvality: vylepšovat, dokud skóre ≥ 0.8 |
| **Podmíněný** | Směruje podle podmínek | Klasifikovat → směrovat k agentovi specialistovi |
| **Člověk v cyklu** | Přidává lidské kontrolní body | Schvalovací workflow, recenze obsahu |

## Klíčové pojmy

Nyní, když jste prozkoumali MCP a agentní modul v praxi, shrňme, kdy použít který přístup.

**MCP** je ideální, když chcete využít existující ekosystémy nástrojů, stavět nástroje, které mohou používat různé aplikace, integrovat služby třetích stran pomocí standardních protokolů, nebo měnit implementace nástrojů bez změny kódu.

**Agentní modul** je nejlepší, pokud chcete deklarativní definice agentů s anotacemi `@Agent`, potřebujete orchestraci workflow (sekvenční, cyklus, paralelní), preferujete návrh agentů založený na rozhraních místo imperativního kódu, nebo kombinujete více agentů, kteří sdílí výstupy přes `outputKey`.

**Vzor Supervisor Agent** vyniká, když workflow není předvídatelné předem a chcete, aby LLM rozhodovalo, když máte více specializovaných agentů, které vyžadují dynamickou orchestraci, při tvorbě konverzačních systémů směrujících různým schopnostem, nebo když chcete nejflexibilnější, adaptivní chování agenta.
## Gratulujeme!

Dokončili jste kurz LangChain4j pro začátečníky. Naučili jste se:

- Jak vytvářet konverzační AI s pamětí (Modul 01)
- Vzory prompt inženýrství pro různé úkoly (Modul 02)
- Zakládání odpovědí na vašich dokumentech pomocí RAG (Modul 03)
- Vytváření základních AI agentů (asistentů) s vlastními nástroji (Modul 04)
- Integrace standardizovaných nástrojů s LangChain4j MCP a Agentic moduly (Modul 05)

### Co dál?

Po dokončení modulů prozkoumejte [Testing Guide](../docs/TESTING.md), kde uvidíte koncepty testování LangChain4j v akci.

**Oficiální zdroje:**
- [Dokumentace LangChain4j](https://docs.langchain4j.dev/) - Komplexní návody a API reference
- [LangChain4j na GitHubu](https://github.com/langchain4j/langchain4j) - Zdrojový kód a příklady
- [LangChain4j Tutoriály](https://docs.langchain4j.dev/tutorials/) - Krok za krokem návody pro různé použití

Děkujeme, že jste dokončili tento kurz!

---

**Navigace:** [← Předchozí: Modul 04 - Nástroje](../04-tools/README.md) | [Zpět na hlavní stránku](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Prohlášení o vyloučení odpovědnosti**:  
Tento dokument byl přeložen pomocí AI překladatelské služby [Co-op Translator](https://github.com/Azure/co-op-translator). I když usilujeme o přesnost, mějte prosím na paměti, že automatizované překlady mohou obsahovat chyby nebo nepřesnosti. Původní dokument v jeho mateřském jazyce by měl být považován za autoritativní zdroj. Pro kritické informace se doporučuje profesionální lidský překlad. Nejsme odpovědní za jakékoliv nedorozumění nebo nesprávné výklady vyplývající z použití tohoto překladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->