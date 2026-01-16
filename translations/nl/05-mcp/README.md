<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "6c816d130a1fa47570c11907e72d84ae",
  "translation_date": "2026-01-06T00:03:58+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "nl"
}
-->
# Module 05: Model Context Protocol (MCP)

## Inhoudsopgave

- [Wat je zult leren](../../../05-mcp)
- [Wat is MCP?](../../../05-mcp)
- [Hoe MCP werkt](../../../05-mcp)
- [De Agentic Module](../../../05-mcp)
- [De voorbeelden draaien](../../../05-mcp)
  - [Vereisten](../../../05-mcp)
- [Snel aan de slag](../../../05-mcp)
  - [Bestandsbewerkingen (Stdio)](../../../05-mcp)
  - [Supervisor Agent](../../../05-mcp)
    - [De output begrijpen](../../../05-mcp)
    - [Responsstrategieën](../../../05-mcp)
    - [Uitleg van Agentic Module functies](../../../05-mcp)
- [Belangrijke concepten](../../../05-mcp)
- [Gefeliciteerd!](../../../05-mcp)
  - [Wat nu?](../../../05-mcp)

## Wat je zult leren

Je hebt al gespreksgerichte AI gebouwd, prompts beheerst, antwoorden gebaseerd op documenten en agents gemaakt met tools. Maar al die tools waren op maat gemaakt voor jouw specifieke toepassing. Wat als je je AI toegang kunt geven tot een gestandaardiseerd ecosysteem van tools die iedereen kan creëren en delen? In deze module leer je precies dat met het Model Context Protocol (MCP) en LangChain4j's agentic module. We laten eerst een eenvoudige MCP-bestandlezer zien en daarna hoe deze makkelijk integreert in geavanceerde agentic workflows met behulp van het Supervisor Agent patroon.

## Wat is MCP?

Het Model Context Protocol (MCP) biedt precies dat - een standaardmanier voor AI-toepassingen om externe tools te ontdekken en te gebruiken. In plaats van voor elke databron of service aangepaste integraties te schrijven, verbind je met MCP-servers die hun mogelijkheden via een consistente indeling blootstellen. Je AI-agent kan dan automatisch deze tools ontdekken en gebruiken.

<img src="../../../translated_images/nl/mcp-comparison.9129a881ecf10ff5.png" alt="MCP Vergelijking" width="800"/>

*Voor MCP: Complexe punt-tot-punt integraties. Na MCP: Eén protocol, eindeloze mogelijkheden.*

MCP lost een fundamenteel probleem in AI-ontwikkeling op: elke integratie is maatwerk. Wil je toegang tot GitHub? Aangepaste code. Wil je bestanden lezen? Aangepaste code. Wil je een database bevragen? Aangepaste code. En geen van deze integraties werkt met andere AI-toepassingen.

MCP standaardiseert dit. Een MCP-server biedt tools aan met duidelijke beschrijvingen en schema’s. Elke MCP-client kan verbinden, beschikbare tools ontdekken en gebruiken. Eén keer bouwen, overal gebruiken.

<img src="../../../translated_images/nl/mcp-architecture.b3156d787a4ceac9.png" alt="MCP Architectuur" width="800"/>

*Model Context Protocol architectuur - gestandaardiseerde toolontdekking en uitvoering*

## Hoe MCP werkt

**Client-Server Architectuur**

MCP gebruikt een client-server model. Servers bieden tools - bestanden lezen, databases bevragen, APIs aanroepen. Clients (jouw AI-applicatie) verbinden met servers en gebruiken hun tools.

Om MCP met LangChain4j te gebruiken, voeg je deze Maven-afhankelijkheid toe:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**Tool Ontdekking**

Wanneer je client verbindt met een MCP-server, vraagt het "Welke tools heb je?" De server antwoordt met een lijst beschikbare tools, elk met beschrijvingen en parameter-schema’s. Je AI-agent kan dan beslissen welke tools te gebruiken op basis van gebruikersverzoeken.

**Transportmechanismen**

MCP ondersteunt verschillende transportmechanismen. Deze module demonstreert de Stdio transport voor lokale processen:

<img src="../../../translated_images/nl/transport-mechanisms.2791ba7ee93cf020.png" alt="Transportmechanismen" width="800"/>

*MCP transportmechanismen: HTTP voor externe servers, Stdio voor lokale processen*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Voor lokale processen. Je applicatie start een server als subprocess en communiceert via de standaard input/output. Handig voor toegang tot het bestandssysteem of command-line tools.

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

> **🤖 Probeer met [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) en vraag:
> - "Hoe werkt Stdio transport en wanneer gebruik ik dit in plaats van HTTP?"
> - "Hoe beheert LangChain4j de levenscyclus van opgestarte MCP-serverprocessen?"
> - "Wat zijn de beveiligingsimplicaties van AI toegang geven tot het bestandssysteem?"

## De Agentic Module

Hoewel MCP gestandaardiseerde tools biedt, levert LangChain4j's **agentic module** een declaratieve manier om agents te bouwen die deze tools orkestreren. De `@Agent` annotatie en `AgenticServices` laten je agentgedrag definiëren via interfaces in plaats van imperatieve code.

In deze module verken je het **Supervisor Agent** patroon — een geavanceerde agentic AI-benadering waarbij een "supervisor" agent dynamisch beslist welke sub-agents te activeren op basis van gebruikersverzoeken. We combineren beide concepten door één van onze sub-agents MCP-gestuurde bestands-toegang te geven.

Om de agentic module te gebruiken, voeg je deze Maven-afhankelijkheid toe:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ Experimenteel:** De `langchain4j-agentic` module is **experimenteel** en kan veranderen. De stabiele manier om AI-assistenten te bouwen blijft `langchain4j-core` met aangepaste tools (Module 04).

## De voorbeelden draaien

### Vereisten

- Java 21+, Maven 3.9+
- Node.js 16+ en npm (voor MCP-servers)
- Omgevingsvariabelen ingesteld in `.env` bestand (vanaf de root directory):
  - `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (zoals bij Modules 01-04)

> **Opmerking:** Als je je omgevingsvariabelen nog niet hebt ingesteld, zie [Module 00 - Quick Start](../00-quick-start/README.md) voor instructies, of kopieer `.env.example` naar `.env` in de root map en vul je waarden in.

## Snel aan de slag

**Met VS Code:** Klik met de rechtermuisknop op een demo-bestand in de Verkenner en selecteer **"Run Java"**, of gebruik de launch configuraties in het Run and Debug paneel (zorg dat je eerst je token in `.env` hebt gezet).

**Met Maven:** Je kunt ook vanaf de opdrachtregel de voorbeelden draaien met onderstaande commando’s.

### Bestandsbewerkingen (Stdio)

Dit demonstreert lokale subprocess-gebaseerde tools.

**✅ Geen vereisten nodig** - de MCP-server wordt automatisch gestart.

**Gebruik van Startscripts (aanbevolen):**

De start scripts laden automatisch omgevingsvariabelen uit de root `.env` bestand:

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

**Met VS Code:** Klik met de rechtermuisknop op `StdioTransportDemo.java` en selecteer **"Run Java"** (zorg dat je `.env` bestand correct is).

De applicatie start automatisch een filesystem MCP-server en leest een lokaal bestand. Let op hoe het subprocess beheer voor je wordt afgehandeld.

**Verwachte output:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Supervisor Agent

Het **Supervisor Agent patroon** is een **flexibele** vorm van agentic AI. Een Supervisor gebruikt een LLM om autonoom te beslissen welke agents te activeren op basis van het gebruikersverzoek. In het volgende voorbeeld combineren we MCP-gestuurde bestands-toegang met een LLM-agent om een workflow te creëren voor bestand lezen → rapport genereren.

In de demo leest `FileAgent` een bestand met MCP filesystem tools, en maakt `ReportAgent` een gestructureerd rapport met een executive summary (1 zin), 3 kernpunten en aanbevelingen. De Supervisor orkestreert deze flow automatisch:

<img src="../../../translated_images/nl/agentic.cf84dcda226374e3.png" alt="Agentic Module" width="800"/>

```
┌─────────────┐      ┌──────────────┐
│  FileAgent  │ ───▶ │ ReportAgent  │
│ (MCP tools) │      │  (pure LLM)  │
└─────────────┘      └──────────────┘
   outputKey:           outputKey:
  'fileContent'         'report'
```

Elke agent slaat zijn output op in de **Agentic Scope** (gedeeld geheugen), zodat downstream agents eerdere resultaten kunnen gebruiken. Dit toont hoe MCP-tools naadloos geïntegreerd worden in agentic workflows — de Supervisor hoeft niet te weten *hoe* bestanden worden gelezen, alleen dat `FileAgent` dat kan.

#### Demo draaien

De start scripts laden automatisch de omgevingsvariabelen uit de root `.env` bestand:

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

**Met VS Code:** Klik met de rechtermuisknop op `SupervisorAgentDemo.java` en selecteer **"Run Java"** (zorg dat je `.env` bestand is geconfigureerd).

#### Hoe de Supervisor werkt

```java
// Stap 1: FileAgent leest bestanden met behulp van MCP-tools
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // Heeft MCP-tools voor bestandsbewerkingen
        .build();

// Stap 2: ReportAgent genereert gestructureerde rapporten
ReportAgent reportAgent = AgenticServices.agentBuilder(ReportAgent.class)
        .chatModel(model)
        .build();

// Supervisor coördineert de bestand → rapport workflow
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)
        .subAgents(fileAgent, reportAgent)
        .responseStrategy(SupervisorResponseStrategy.LAST)  // Retourneer het definitieve rapport
        .build();

// De Supervisor beslist welke agenten worden aangeroepen op basis van het verzoek
String response = supervisor.invoke("Read the file at /path/file.txt and generate a report");
```

#### Responsstrategieën

Wanneer je een `SupervisorAgent` configureert, geef je aan hoe hij het definitieve antwoord aan de gebruiker formuleert nadat de sub-agents hun taken hebben afgerond. De beschikbare strategieën zijn:

| Strategie | Beschrijving |
|----------|-------------|
| **LAST** | De supervisor geeft de output terug van de laatst aangeroepen sub-agent of tool. Dit is handig wanneer de laatste agent in de workflow is ontworpen om het volledige, definitieve antwoord te produceren (bijv. een "Summary Agent" in een onderzoeksproces). |
| **SUMMARY** | De supervisor gebruikt zijn interne Language Model (LLM) om een samenvatting te maken van de gehele interactie en alle sub-agent outputs, en geeft die samenvatting terug als eindantwoord. Dit levert een overzichtelijk, geaggregeerd antwoord voor de gebruiker. |
| **SCORED** | Het systeem gebruikt een interne LLM om zowel het LAST antwoord als de SUMMARY van de interactie te scoren op basis van het oorspronkelijke gebruikersverzoek. Het resultaat met de hoogste score wordt teruggegeven. |

Zie [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) voor de volledige implementatie.

> **🤖 Probeer met [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) en vraag:
> - "Hoe bepaalt de Supervisor welke agents hij activeert?"
> - "Wat is het verschil tussen het Supervisor patroon en het Sequential workflow patroon?"
> - "Hoe kan ik het planningsgedrag van de Supervisor aanpassen?"

#### De output begrijpen

Wanneer je de demo draait, zie je een gestructureerd overzicht van hoe de Supervisor meerdere agents orkestreert. Dit betekent elk deel:

```
======================================================================
  FILE → REPORT WORKFLOW DEMO
======================================================================

This demo shows a clear 2-step workflow: read a file, then generate a report.
The Supervisor orchestrates the agents automatically based on the request.
```

**De koptekst** introduceert het workflowconcept: een gerichte pijplijn van bestand lezen tot rapport generatie.

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

**Workflow Diagram** toont de datastroom tussen agents. Elke agent heeft een specifieke rol:
- **FileAgent** leest bestanden met MCP-tools en slaat ruwe inhoud op in `fileContent`
- **ReportAgent** gebruikt die inhoud en maakt een gestructureerd rapport aan in `report`

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and generate a report on its contents"
```

**Gebruikersverzoek** toont de taak. De Supervisor verwerkt dit en besluit FileAgent → ReportAgent aan te roepen.

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

**Supervisor Orkestratie** toont de 2-staps flow in actie:
1. **FileAgent** leest het bestand via MCP en slaat de inhoud op
2. **ReportAgent** ontvangt de inhoud en genereert een gestructureerd rapport

De Supervisor nam deze beslissingen **autonoom** op basis van de gebruikersvraag.

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

#### Uitleg van Agentic Module functies

Het voorbeeld toont diverse geavanceerde functies van de agentic module. Laten we Agentic Scope en Agent Listeners nader bekijken.

**Agentic Scope** toont het gedeelde geheugen waar agents hun resultaten opslaan met `@Agent(outputKey="...")`. Dit maakt mogelijk:
- Latere agents krijgen toegang tot eerdere agent output
- De Supervisor kan een eindantwoord samenstellen
- Jij kunt zien wat elke agent produceerde

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String fileContent = scope.readState("fileContent");  // Ruwe bestandsgegevens van FileAgent
String report = scope.readState("report");            // Gestructureerd rapport van ReportAgent
```

**Agent Listeners** maken bewaking en debugging van agentuitvoering mogelijk. De stapsgewijze output in de demo komt van een AgentListener die op elke agentaanroep ingrijpt:
- **beforeAgentInvocation** - Wordt aangeroepen wanneer de Supervisor een agent selecteert, zodat je ziet welke agent gekozen is en waarom
- **afterAgentInvocation** - Wordt aangeroepen wanneer een agent klaar is, met het resultaat
- **inheritedBySubagents** - Wanneer waar, monitort de listener alle agents in de hiërarchie

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
        return true; // Propageren naar alle sub-agenten
    }
};
```

Naast het Supervisor patroon biedt de `langchain4j-agentic` module meerdere krachtige workflow patronen en functies:

| Patroon | Beschrijving | Gebruik |
|---------|-------------|----------|
| **Sequential** | Agents na elkaar uitvoeren, output gaat door naar de volgende | Pijplijnen: onderzoek → analyse → rapport |
| **Parallel** | Agents gelijktijdig uitvoeren | Onafhankelijke taken: weer + nieuws + aandelen |
| **Loop** | Herhalen tot een conditie voldoet | Kwaliteitsscore: verfijnen tot score ≥ 0,8 |
| **Conditional** | Routeren op basis van voorwaarden | Classificeren → doorsturen naar specialist |
| **Human-in-the-Loop** | Menselijke controles toevoegen | Goedkeuringsprocessen, inhoudscontrole |

## Belangrijke concepten

Nu je MCP en de agentic module in actie hebt gezien, volgt een samenvatting wanneer je welke aanpak gebruikt.

**MCP** is ideaal wanneer je gebruik wilt maken van bestaande tool-ecosystemen, tools bouwt die meerdere applicaties kunnen delen, derde partijen integreert met standaardprotocollen, of tool-implementaties wilt uitwisselen zonder code te wijzigen.

**De Agentic Module** werkt het beste als je declaratieve agentdefinities wilt met `@Agent` annotaties, workflow orkestratie nodig hebt (sequentieel, loop, parallel), interface-gebaseerde agentontwerpen prefereert boven imperatieve code, of meerdere agents combineert die output delen via `outputKey`.

**Het Supervisor Agent patroon** blinkt uit als de workflow niet van tevoren voorspelbaar is en je wilt dat de LLM beslist, als je meerdere gespecialiseerde agents hebt die dynamisch gecoördineerd worden, bij het bouwen van conversatiesystemen die naar verschillende functies routeren, of als je het meest flexibele, adaptieve agentgedrag wilt.
## Gefeliciteerd!

Je hebt de LangChain4j voor Beginners cursus voltooid. Je hebt geleerd:

- Hoe je conversatie-AI met geheugen bouwt (Module 01)
- Prompt engineering patronen voor verschillende taken (Module 02)
- Het onderbouwen van antwoorden met je documenten via RAG (Module 03)
- Basis AI-agents (assistenten) maken met aangepaste tools (Module 04)
- Gestandaardiseerde tools integreren met de LangChain4j MCP en Agentic modules (Module 05)

### Wat is de volgende stap?

Na het voltooien van de modules, verken de [Testing Guide](../docs/TESTING.md) om de LangChain4j testconcepten in actie te zien.

**Officiële middelen:**
- [LangChain4j Documentatie](https://docs.langchain4j.dev/) - Uitgebreide gidsen en API referentie
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Broncode en voorbeelden
- [LangChain4j Tutorials](https://docs.langchain4j.dev/tutorials/) - Stapsgewijze tutorials voor diverse toepassingen

Bedankt voor het voltooien van deze cursus!

---

**Navigatie:** [← Vorige: Module 04 - Tools](../04-tools/README.md) | [Terug naar hoofdmenu](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:
Dit document is vertaald met behulp van de AI-vertalingsdienst [Co-op Translator](https://github.com/Azure/co-op-translator). Hoewel we streven naar nauwkeurigheid, dient u er rekening mee te houden dat geautomatiseerde vertalingen fouten of onnauwkeurigheden kunnen bevatten. Het originele document in de oorspronkelijke taal wordt beschouwd als de gezaghebbende bron. Voor cruciale informatie wordt professionele menselijke vertaling aanbevolen. Wij zijn niet aansprakelijk voor eventuele misverstanden of verkeerde interpretaties die voortvloeien uit het gebruik van deze vertaling.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->