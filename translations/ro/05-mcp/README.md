<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f89f4c106d110e4943c055dd1a2f1dff",
  "translation_date": "2025-12-31T04:47:55+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "ro"
}
-->
# Modulul 05: Model Context Protocol (MCP)

## Cuprins

- [Ce vei învăța](../../../05-mcp)
- [Ce este MCP?](../../../05-mcp)
- [Cum funcționează MCP](../../../05-mcp)
- [Modulul agențial](../../../05-mcp)
- [Rularea exemplelor](../../../05-mcp)
  - [Precondiții](../../../05-mcp)
- [Pornire rapidă](../../../05-mcp)
  - [Operațiuni pe fișiere (Stdio)](../../../05-mcp)
  - [Agentul Supervisor](../../../05-mcp)
    - [Înțelegerea rezultatului](../../../05-mcp)
    - [Explicație a caracteristicilor modulului agențial](../../../05-mcp)
- [Concepte cheie](../../../05-mcp)
- [Felicitări!](../../../05-mcp)
  - [Ce urmează?](../../../05-mcp)

## Ce vei învăța

Ai construit AI conversațional, ai stăpânit prompturile, ai fundamentat răspunsurile în documente și ai creat agenți cu instrumente. Dar toate acele instrumente au fost construite personalizat pentru aplicația ta specifică. Ce ai face dacă ai putea oferi AI-ului tău acces la un ecosistem standardizat de instrumente pe care oricine le poate crea și partaja? În acest modul vei învăța exact asta cu Model Context Protocol (MCP) și modulul agentic al LangChain4j. Mai întâi prezentăm un cititor de fișiere MCP simplu și apoi arătăm cum se integrează ușor în fluxuri de lucru agentice avansate folosind patternul Supervisor Agent.

## Ce este MCP?

Model Context Protocol (MCP) oferă exact asta - un mod standard pentru aplicațiile AI de a descoperi și utiliza instrumente externe. În loc să scrii integrări personalizate pentru fiecare sursă de date sau serviciu, te conectezi la servere MCP care își expun capabilitățile într-un format consecvent. Agentul tău AI poate apoi descoperi și folosi automat aceste instrumente.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.ro.png" alt="Comparație MCP" width="800"/>

*Înainte de MCP: integrări complexe punct-la-punct. După MCP: un protocol, posibilități nesfârșite.*

MCP rezolvă o problemă fundamentală în dezvoltarea AI: fiecare integrare este personalizată. Vrei acces la GitHub? Cod personalizat. Vrei să citești fișiere? Cod personalizat. Vrei să interoghezi o bază de date? Cod personalizat. Și niciuna dintre aceste integrări nu funcționează cu alte aplicații AI.

MCP standardizează acest lucru. Un server MCP expune instrumente cu descrieri clare și scheme de parametri. Orice client MCP se poate conecta, descoperi instrumentele disponibile și le poate folosi. Construiește o dată, folosește oriunde.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.ro.png" alt="Arhitectura MCP" width="800"/>

*Arhitectura Model Context Protocol - descoperire și execuție standardizate a instrumentelor*

## Cum funcționează MCP

**Arhitectură Server-Client**

MCP folosește un model client-server. Serverele furnizează instrumente - citirea fișierelor, interogarea bazelor de date, apelarea API-urilor. Clienții (aplicația ta AI) se conectează la servere și folosesc instrumentele lor.

Pentru a folosi MCP cu LangChain4j, adaugă această dependență Maven:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**Descoperirea instrumentelor**

Când clientul tău se conectează la un server MCP, întreabă "Ce instrumente aveți?" Serverul răspunde cu o listă de instrumente disponibile, fiecare cu descrieri și scheme de parametri. Agentul tău AI poate apoi decide ce instrumente să folosească pe baza cererilor utilizatorului.

**Mecanisme de transport**

MCP suportă diferite mecanisme de transport. Acest modul demonstrează transportul Stdio pentru procese locale:

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.ro.png" alt="Mecanisme de transport" width="800"/>

*Mecanisme de transport MCP: HTTP pentru servere la distanță, Stdio pentru procese locale*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Pentru procese locale. Aplicația ta pornește un server ca subprocess și comunică prin intrare/ieșire standard. Util pentru acces la sistemul de fișiere sau instrumente din linia de comandă.

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

> **🤖 Încearcă cu [GitHub Copilot](https://github.com/features/copilot) Chat:** Deschide [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) și întreabă:
> - "Cum funcționează transportul Stdio și când ar trebui să îl folosesc în locul HTTP?"
> - "Cum gestionează LangChain4j ciclul de viață al proceselor server MCP pornite ca subprocess?"
> - "Care sunt implicațiile de securitate ale acordării AI-ului acces la sistemul de fișiere?"

## Modulul agențial

În timp ce MCP furnizează instrumente standardizate, modulul **agențial** al LangChain4j oferă o modalitate declarativă de a construi agenți care orchestrează acele instrumente. Anotarea `@Agent` și `AgenticServices` îți permit să definești comportamentul agenților prin interfețe în loc de cod imperativ.

În acest modul, vei explora patternul **Supervisor Agent** — o abordare agentică avansată în care un agent „supervizor” decide dinamic ce sub-agenti să invoce pe baza cerințelor utilizatorului. Vom combina ambele concepte oferind unuia dintre sub-agenti capabilități de acces la fișiere alimentate de MCP.

Pentru a folosi modulul agențial, adaugă această dependență Maven:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ Experimental:** modulul `langchain4j-agentic` este **experimental** și poate suferi modificări. Modul stabil de a construi asistenți AI rămâne `langchain4j-core` cu instrumente personalizate (Modulul 04).

## Rularea exemplelor

### Precondiții

- Java 21+, Maven 3.9+
- Node.js 16+ și npm (pentru serverele MCP)
- Variabile de mediu configurate în fișierul `.env` (din directorul rădăcină):
  - **Pentru StdioTransportDemo:** `GITHUB_TOKEN` (GitHub Personal Access Token)
  - **Pentru SupervisorAgentDemo:** `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (aceleași ca în Modulele 01-04)

> **Notă:** Dacă nu ți-ai configurat încă variabilele de mediu, vezi [Module 00 - Quick Start](../00-quick-start/README.md) pentru instrucțiuni, sau copiază `.env.example` în `.env` în directorul rădăcină și completează valorile tale.

## Pornire rapidă

**Folosind VS Code:** Fă click dreapta pe orice fișier demo în Explorer și selectează **"Run Java"**, sau folosește configurațiile de lansare din panoul Run and Debug (asigură-te că ai adăugat mai întâi tokenul în fișierul `.env`).

**Folosind Maven:** Alternativ, poți rula din linia de comandă cu exemplele de mai jos.

### Operațiuni pe fișiere (Stdio)

Aceasta demonstrează instrumente bazate pe subprocessuri locale.

**✅ Fără precondiții necesare** - serverul MCP este pornit automat.

**Folosind VS Code:** Fă click dreapta pe `StdioTransportDemo.java` și selectează **"Run Java"**.

**Folosind Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

Aplicația pornește automat un server MCP pentru sistemul de fișiere și citește un fișier local. Observă cum gestionarea subprocessurilor este făcută pentru tine.

**Ieșire așteptată:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Agentul Supervisor

<img src="../../../translated_images/agentic.cf84dcda226374e3.ro.png" alt="Modul agentic" width="800"/>


Patternul **Supervisor Agent** este o formă **flexibilă** de AI agențial. Spre deosebire de fluxurile de lucru deterministe (secuențiale, buclă, paralele), un Supervisor folosește un LLM pentru a decide în mod autonom ce agenți să invoce pe baza cererii utilizatorului.

**Combinarea Supervisor cu MCP:** În acest exemplu, îi oferim `FileAgent` acces la instrumentele sistemului de fișiere MCP prin `toolProvider(mcpToolProvider)`. Când un utilizator cere să „citească și să analizeze un fișier”, Supervisor analizează cererea și generează un plan de execuție. Apoi direcționează cererea către `FileAgent`, care folosește instrumentul MCP `read_file` pentru a recupera conținutul. Supervisor transmite acel conținut către `AnalysisAgent` pentru interpretare și, opțional, invocă `SummaryAgent` pentru a rezuma rezultatele.

Acest lucru demonstrează cum instrumentele MCP se integrează perfect în fluxurile de lucru agențiale — Supervisor nu trebuie să știe *cum* sunt citite fișierele, doar că `FileAgent` le poate citi. Supervisor se adaptează dinamic la diferite tipuri de cereri și returnează fie răspunsul ultimului agent, fie un rezumat al tuturor operațiunilor.

**Folosind scripturile de pornire (Recomandat):**

Scripturile de pornire încarcă automat variabilele de mediu din fișierul `.env` din rădăcină:

**Bash:**
```bash
cd 05-mcp
chmod +x start.sh
./start.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start.ps1
```

**Folosind VS Code:** Fă click dreapta pe `SupervisorAgentDemo.java` și selectează **"Run Java"** (asigură-te că fișierul `.env` este configurat).

**Cum funcționează Supervisor:**

```java
// Defineți mai mulți agenți cu capacități specifice
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // Dispune de instrumente MCP pentru operațiuni cu fișiere
        .build();

AnalysisAgent analysisAgent = AgenticServices.agentBuilder(AnalysisAgent.class)
        .chatModel(model)
        .build();

SummaryAgent summaryAgent = AgenticServices.agentBuilder(SummaryAgent.class)
        .chatModel(model)
        .build();

// Creați un Supraveghetor care orchestrează acești agenți
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)  // Modelul "planner"
        .subAgents(fileAgent, analysisAgent, summaryAgent)
        .responseStrategy(SupervisorResponseStrategy.SUMMARY)
        .build();

// Supraveghetorul decide în mod autonom ce agenți să invoce
// Pur și simplu transmiteți o cerere în limbaj natural - LLM-ul planifică execuția
String response = supervisor.invoke("Read the file at /path/file.txt and analyze it");
```

Vezi [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) pentru implementarea completă.

> **🤖 Încearcă cu [GitHub Copilot](https://github.com/features/copilot) Chat:** Deschide [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) și întreabă:
> - "Cum decide Supervisor ce agenți să invoce?"
> - "Care este diferența dintre Supervisor și patternurile de workflow Secvențial?"
> - "Cum pot personaliza comportamentul de planificare al Supervisor-ului?"

#### Înțelegerea rezultatului

Când rulezi demo-ul, vei vedea o prezentare structurată a modului în care Supervisor orchestrează mai mulți agenți. Iată ce înseamnă fiecare secțiune:

```
======================================================================
  SUPERVISOR AGENT DEMO
======================================================================

This demo shows how a Supervisor Agent orchestrates multiple specialized agents.
The Supervisor uses an LLM to decide which agent to call based on the task.
```

**Antetul** introduce demo-ul și explică conceptul central: Supervisor folosește un LLM (nu reguli hardcodate) pentru a decide ce agenți să apeleze.

```
--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]     FileAgent     - Reads files using MCP filesystem tools
  [ANALYZE]  AnalysisAgent - Analyzes content for structure, tone, and themes
  [SUMMARY]  SummaryAgent  - Creates concise summaries of content
```

**Agenți disponibili** arată cei trei agenți specializați dintre care Supervisor poate alege. Fiecare agent are o capacitate specifică:
- **FileAgent** poate citi fișiere folosind instrumente MCP (capabilitate externă)
- **AnalysisAgent** analizează conținutul (capabilitate pur LLM)
- **SummaryAgent** creează rezumate (capabilitate pur LLM)

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and analyze what it's about"
```

**Cererea utilizatorului** arată ceea ce s-a cerut. Supervisor trebuie să interpreteze asta și să decidă ce agenți să invoce.

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor will now decide which agents to invoke and in what order...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source Java library designed to simplify...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> AnalysisAgent (analyzing content)
  |
  |   Input: LangChain4j is an open-source Java library...
  |
  |   Result: Structure: The content is organized into clear paragraphs that int...
  +-- [OK] AnalysisAgent (analyzing content) completed
```

**Orchestrarea Supervisor-ului** este locul unde are loc magia. Urmărește cum:
1. Supervisor **a ales mai întâi FileAgent** deoarece cererea menționa „citește fișierul”
2. FileAgent a folosit instrumentul MCP `read_file` pentru a prelua conținutul fișierului
3. Supervisor apoi **a ales AnalysisAgent** și i-a transmis conținutul fișierului
4. AnalysisAgent a analizat structura, tonul și temele

Observă că Supervisor a luat aceste decizii **în mod autonom** pe baza cererii utilizatorului — fără workflow hardcodat!

**Răspunsul final** este răspunsul sintetizat al Supervisor-ului, combinând output-urile tuturor agenților pe care i-a invocat. Exemplul afișează scope-ul agențial arătând rezumatul și rezultatele analizei stocate de fiecare agent.

```
--- FINAL RESPONSE ---------------------------------------------------
I read the contents of the file and analyzed its structure, tone, and key themes.
The file introduces LangChain4j as an open-source Java library for integrating
large language models...

--- AGENTIC SCOPE (Shared Memory) ------------------------------------
  Agents store their results in a shared scope for other agents to use:
  * summary: LangChain4j is an open-source Java library...
  * analysis: Structure: The content is organized into clear paragraphs that in...
```

### Explicație a caracteristicilor modulului agențial

Exemplul demonstrează mai multe caracteristici avansate ale modulului agențial. Să aruncăm o privire mai atentă la Agentic Scope și la Agent Listeners.

**Agentic Scope** afișează memoria partajată unde agenții și-au stocat rezultatele folosind `@Agent(outputKey="...")`. Aceasta permite:
- Agenților care vin ulterior să acceseze output-urile agenților anteriori
- Supervisor-ului să sintetizeze un răspuns final
- Ție să inspectezi ce a produs fiecare agent

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String story = scope.readState("story");
List<AgentInvocation> history = scope.agentInvocations("analysisAgent");
```

**Agent Listeners** permit monitorizarea și depanarea execuției agenților. Ieșirea pas cu pas pe care o vezi în demo provine de la un AgentListener care se conectează la fiecare invocare de agent:
- **beforeAgentInvocation** - Apelat când Supervisor selectează un agent, permițându-ți să vezi care agent a fost ales și de ce
- **afterAgentInvocation** - Apelat când un agent se încheie, arătând rezultatul său
- **inheritedBySubagents** - Când este true, listener-ul monitorizează toți agenții din ierarhie

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
        return true; // Propagați către toți subagenții
    }
};
```

Dincolo de patternul Supervisor, modulul `langchain4j-agentic` oferă mai multe patternuri și caracteristici puternice de workflow:

| Pattern | Descriere | Caz de utilizare |
|---------|-------------|----------|
| **Sequential** | Execută agenții în ordine, output-ul curge către următorul | Pipeline-uri: cercetare → analiză → raport |
| **Parallel** | Rulează agenții simultan | Sarcini independente: vreme + știri + acțiuni |
| **Loop** | Iterează până se îndeplinește o condiție | Scorare calitate: rafinează până când scor ≥ 0.8 |
| **Conditional** | Direcționează pe baza condițiilor | Clasificare → direcționare către agent specialist |
| **Human-in-the-Loop** | Adaugă puncte de control umane | Fluxuri de aprobare, revizuire conținut |

## Concepte cheie

**MCP** este ideal când vrei să valorifici ecosisteme existente de instrumente, să construiești instrumente pe care mai multe aplicații le pot partaja, să integrezi servicii terțe cu protocoale standard sau să schimbi implementările instrumentelor fără a modifica codul.

**Modulul agențial** funcționează cel mai bine când vrei definiții declarative ale agenților cu anotări `@Agent`, ai nevoie de orchestrare a fluxului de lucru (secuențial, buclă, paralel), preferi designul bazat pe interfețe în loc de cod imperativ sau combini mai mulți agenți care partajează output-uri prin `outputKey`.

**Patternul Supervisor Agent** strălucește când workflow-ul nu este predictibil dinainte și vrei ca LLM-ul să decidă, când ai mai mulți agenți specializați care necesită orchestrare dinamică, când construiești sisteme conversaționale care direcționează către diferite capabilități sau când vrei cel mai flexibil și adaptabil comportament al agenților.

## Felicitări!

Ai finalizat cursul LangChain4j pentru Începători. Ai învățat:

- Cum să construiești AI conversațional cu memorie (Modulul 01)
- Modele de inginerie a prompturilor pentru diferite sarcini (Modulul 02)
- Fundamentarea răspunsurilor în documentele tale cu RAG (Modulul 03)
- Crearea agenților AI de bază (asistenți) cu instrumente personalizate (Modulul 04)
- Integrarea uneltelor standardizate cu modulele LangChain4j MCP și Agentic (Modul 05)

### Ce urmează?

După ce ați finalizat modulele, explorați [Ghidul de testare](../docs/TESTING.md) pentru a vedea conceptele de testare LangChain4j în acțiune.

**Resurse oficiale:**
- [Documentația LangChain4j](https://docs.langchain4j.dev/) - Ghiduri cuprinzătoare și referință API
- [LangChain4j pe GitHub](https://github.com/langchain4j/langchain4j) - Cod sursă și exemple
- [Tutoriale LangChain4j](https://docs.langchain4j.dev/tutorials/) - Tutoriale pas cu pas pentru diverse cazuri de utilizare

Mulțumim că ați finalizat acest curs!

---

**Navigare:** [← Anterior: Modul 04 - Unelte](../04-tools/README.md) | [Înapoi la pagina principală](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Declinare de responsabilitate:
Acest document a fost tradus folosind serviciul de traducere AI Co-op Translator (https://github.com/Azure/co-op-translator). Deși ne străduim pentru acuratețe, vă rugăm să rețineți că traducerile automate pot conține erori sau inexactități. Documentul original, în limba sa nativă, trebuie considerat sursa autoritară. Pentru informații critice, se recomandă o traducere profesională realizată de un traducător uman. Nu ne asumăm nicio răspundere pentru eventualele neînțelegeri sau interpretări greșite rezultate din utilizarea acestei traduceri.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->