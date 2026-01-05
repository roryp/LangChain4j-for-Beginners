<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f89f4c106d110e4943c055dd1a2f1dff",
  "translation_date": "2025-12-31T05:50:45+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "sl"
}
-->
# Modul 05: Protokol konteksta modela (MCP)

## Table of Contents

- [Kaj se boste naučili](../../../05-mcp)
- [Kaj je MCP?](../../../05-mcp)
- [Kako MCP deluje](../../../05-mcp)
- [Agentni modul](../../../05-mcp)
- [Zagon primerov](../../../05-mcp)
  - [Predpogoji](../../../05-mcp)
- [Hiter začetek](../../../05-mcp)
  - [Operacije z datotekami (Stdio)](../../../05-mcp)
  - [Nadzorni agent](../../../05-mcp)
    - [Razumevanje izhoda](../../../05-mcp)
    - [Pojasnilo funkcij agentnega modula](../../../05-mcp)
- [Ključni pojmi](../../../05-mcp)
- [Čestitamo!](../../../05-mcp)
  - [Kaj sledi?](../../../05-mcp)

## Kaj se boste naučili

Zgradili ste pogovorno AI, obvladali pozive, osnovali odgovore v dokumentih in ustvarili agente z orodji. Vsa ta orodja pa so bila po meri izdelana za vašo specifično aplikacijo. Kaj pa, če bi vaši AI dali dostop do standardiziranega ekosistema orodij, ki ga lahko kdorkoli ustvari in deli? V tem modulu se boste naučili prav to z Model Context Protocol (MCP) in agentnim modulom LangChain4j. Najprej prikažemo preprost MCP bralnik datotek in nato pokažemo, kako se zlahka integrira v napredne agentne delovne tokove z uporabo vzorca Supervisor Agent.

## Kaj je MCP?

Model Context Protocol (MCP) nudi prav to - standardiziran način, da AI aplikacije odkrijejo in uporabljajo zunanja orodja. Namesto pisanja prilagojenih integracij za vsak vir podatkov ali storitev se povežete s MCP strežniki, ki svoje zmogljivosti izpostavijo v dosledni obliki. Vaš AI agent lahko nato samodejno odkrije in uporabi ta orodja.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.sl.png" alt="Primerjava MCP" width="800"/>

*Pred MCP: zapletene točkovne integracije. Po MCP: eden protokol, neskončne možnosti.*

MCP rešuje temeljni problem pri razvoju AI: vsaka integracija je po meri. Želite dostop do GitHub-a? Prilagojena koda. Želite brati datoteke? Prilagojena koda. Želite poizvedovati bazo podatkov? Prilagojena koda. In nobena od teh integracij ne deluje z drugimi AI aplikacijami.

MCP to standardizira. MCP strežnik izpostavi orodja z jasnimi opisi in shemami parametrov. Vsak MCP odjemalec se lahko poveže, odkrije razpoložljiva orodja in jih uporabi. Zgradi enkrat, uporabi povsod.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.sl.png" alt="Arhitektura MCP" width="800"/>

*Arhitektura Model Context Protocol - standardizirano odkrivanje in izvajanje orodij*

## Kako MCP deluje

**Arhitektura strežnik-odjemalec**

MCP uporablja model strežnik-odjemalec. Strežniki zagotavljajo orodja - branje datotek, poizvedovanje baz, klicanje API-jev. Odjemalci (vaša AI aplikacija) se povežejo s strežniki in uporabljajo njihova orodja.

Za uporabo MCP z LangChain4j dodajte to Maven odvisnost:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**Odkritje orodij**

Ko se vaš odjemalec poveže z MCP strežnikom, vpraša "Katera orodja imate?" Strežnik odgovori s seznamom razpoložljivih orodij, vsako z opisi in shemami parametrov. Vaš AI agent se nato odloči, katera orodja uporabiti glede na uporabniške zahteve.

**Transportni mehanizmi**

MCP podpira različne transportne mehanizme. Ta modul prikazuje Stdio transport za lokalne procese:

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.sl.png" alt="Mehanizmi transporta" width="800"/>

*MCP transportni mehanizmi: HTTP za oddaljene strežnike, Stdio za lokalne procese*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Za lokalne procese. Vaša aplikacija zažene strežnik kot podproces in komunicira preko standardnega vnosa/izhoda. Uporabno za dostop do datotečnega sistema ali orodij ukazne vrstice.

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

> **🤖 Preizkusi z [GitHub Copilot](https://github.com/features/copilot) Chat:** Odpri [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) in vprašaj:
> - "Kako deluje Stdio transport in kdaj naj ga uporabim v primerjavi z HTTP?"
> - "Kako LangChain4j upravlja življenjski cikel zaženih MCP strežniških procesov?"
> - "Kakšne so varnostne posledice dajanja AI dostopa do datotečnega sistema?"

## Agentni modul

Medtem ko MCP zagotavlja standardizirana orodja, agentni modul LangChain4j ponuja deklarativen način gradnje agentov, ki orkestrirajo ta orodja. Anotacija `@Agent` in `AgenticServices` vam omogočata, da določite vedenje agenta preko vmesnikov namesto imperativne kode.

V tem modulu boste raziskali vzorec **Nadzorni agent (Supervisor Agent)** — napredni agentni AI pristop, kjer "nadzornik" dinamično odloča, katere podagente bo poklical glede na uporabnikovo zahtevo. Združili bomo oba koncepta tako, da bomo enemu izmed naših podagentov dali MCP-podprt dostop do datotečnega sistema.

Za uporabo agentnega modula dodajte to Maven odvisnost:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ Eksperimentalno:** modul `langchain4j-agentic` je **eksperimentalni** in se lahko spremeni. Stabilen način gradnje AI asistentov ostaja `langchain4j-core` s prilagojenimi orodji (Modul 04).

## Zagon primerov

### Predpogoji

- Java 21+, Maven 3.9+
- Node.js 16+ in npm (za MCP strežnike)
- Spremenljivke okolja konfigurirane v datoteki `.env` (iz korenskega imenika):
  - **Za StdioTransportDemo:** `GITHUB_TOKEN` (GitHub Personal Access Token)
  - **Za SupervisorAgentDemo:** `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (enako kot v Modulih 01-04)

> **Opomba:** Če še niste nastavili svojih okoljskih spremenljivk, poglejte [Module 00 - Quick Start](../00-quick-start/README.md) za navodila, ali kopirajte `.env.example` v `.env` v korenskem imeniku in izpolnite svoje vrednosti.

## Hiter začetek

**Uporaba VS Code:** Enostavno kliknite z desnim gumbom na katero koli demo datoteko v Explorerju in izberite **"Run Java"**, ali uporabite zagonske konfiguracije iz pogleda Run and Debug (prepričajte se, da ste najprej dodali svoj token v datoteko `.env`).

**Uporaba Mavena:** Alternativno lahko zaženete iz ukazne vrstice z zgledi spodaj.

### Operacije z datotekami (Stdio)

To prikazuje orodja, ki temeljijo na lokalnih podprocesih.

**✅ Ni potrebnih predpogojev** - MCP strežnik se zažene samodejno.

**Uporaba VS Code:** Kliknite z desnim gumbom na `StdioTransportDemo.java` in izberite **"Run Java"**.

**Uporaba Mavena:**

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

Aplikacija samodejno zažene MCP strežnik za datotečni sistem in prebere lokalno datoteko. Opazite, kako je upravljanje podprocesov urejeno za vas.

**Pričakovan izhod:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Nadzorni agent

<img src="../../../translated_images/agentic.cf84dcda226374e3.sl.png" alt="Agentni modul" width="800"/>


Vzorec **Nadzorni agent** je **prilagodljiva** oblika agentnega AI. V nasprotju z determinističnimi delovnimi tokovi (zaporedno, zanko, vzporedno) nadzornik uporablja LLM, da avtonomno odloči, katere agente poklicati glede na uporabnikovo zahtevo.

**Združevanje nadzornika z MCP:** V tem primeru damo `FileAgent` dostop do MCP orodij datotečnega sistema preko `toolProvider(mcpToolProvider)`. Ko uporabnik zaprosi, naj "prebere in analizira datoteko", nadzornik analizira zahtevo in ustvari načrt izvajanja. Nato usmeri zahtevo k `FileAgent`, ki uporabi MCP orodje `read_file` za pridobitev vsebine. Nadzornik to vsebino posreduje `AnalysisAgent` za interpretacijo in po potrebi pokliče `SummaryAgent`, da povzame rezultate.

To prikazuje, kako se MCP orodja brezhibno vključijo v agentne delovne tokove — nadzornik ne potrebuje vedeti *kako* se datoteke berejo, le da `FileAgent` to zna. Nadzornik se dinamično prilagaja različnim vrstam zahtev in vrne bodisi odziv zadnjega agenta ali povzetek vseh operacij.

**Uporaba zagonskih skript (priporočeno):**

Zagonski skripti samodejno naložijo okoljske spremenljivke iz korenske datoteke `.env`:

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

**Uporaba VS Code:** Kliknite z desnim gumbom na `SupervisorAgentDemo.java` in izberite **"Run Java"** (poskrbite, da je vaša datoteka `.env` konfigurirana).

**Kako deluje nadzornik:**

```java
// Določite več agentov z določenimi zmožnostmi
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // Ima MCP orodja za delo z datotekami
        .build();

AnalysisAgent analysisAgent = AgenticServices.agentBuilder(AnalysisAgent.class)
        .chatModel(model)
        .build();

SummaryAgent summaryAgent = AgenticServices.agentBuilder(SummaryAgent.class)
        .chatModel(model)
        .build();

// Ustvarite nadzornika, ki orkestrira te agente
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)  // Model "planner"
        .subAgents(fileAgent, analysisAgent, summaryAgent)
        .responseStrategy(SupervisorResponseStrategy.SUMMARY)
        .build();

// Nadzornik samostojno odloča, katere agente bo sprožil
// Samo posredujte zahtevo v naravnem jeziku - LLM načrtuje izvedbo
String response = supervisor.invoke("Read the file at /path/file.txt and analyze it");
```

Poglejte [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) za popolno implementacijo.

> **🤖 Preizkusi z [GitHub Copilot](https://github.com/features/copilot) Chat:** Odpri [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) in vprašaj:
> - "Kako nadzornik odloči, katere agente poklicati?"
> - "Kakšna je razlika med vzorcem Supervisor in zaporednim (Sequential) delovnim tokom?"
> - "Kako lahko prilagodim načrtovalno vedenje nadzornika?"

#### Razumevanje izhoda

Ko zaženete demo, boste videli strukturiran pregled, kako nadzornik orkestrira več agentov. Tukaj je, kaj pomeni vsak del:

```
======================================================================
  SUPERVISOR AGENT DEMO
======================================================================

This demo shows how a Supervisor Agent orchestrates multiple specialized agents.
The Supervisor uses an LLM to decide which agent to call based on the task.
```

**Glava** predstavlja demo in pojasnjuje osnovni koncept: nadzornik uporablja LLM (ne trdo kodirana pravila), da odloči, katere agente poklicati.

```
--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]     FileAgent     - Reads files using MCP filesystem tools
  [ANALYZE]  AnalysisAgent - Analyzes content for structure, tone, and themes
  [SUMMARY]  SummaryAgent  - Creates concise summaries of content
```

**Razpoložljivi agenti** prikazujejo tri specializirane agente, ki jih lahko nadzornik izbere. Vsak agent ima specifično zmožnost:
- **FileAgent** zna brati datoteke z uporabo MCP orodij (zunanja zmožnost)
- **AnalysisAgent** analizira vsebino (čista LLM zmožnost)
- **SummaryAgent** ustvarja povzetke (čista LLM zmožnost)

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and analyze what it's about"
```

**Uporabniška zahteva** prikazuje, kaj je bilo zahtevano. Nadzornik mora to razčleniti in se odločiti, katere agente poklicati.

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

**Orkestracija nadzornika** je mesto, kjer se zgodi čarovnija. Oglejte si, kako:
1. Nadzornik je **najprej izbral FileAgent**, ker je bila v zahtevi omenjeno "preberi datoteko"
2. FileAgent je uporabil MCP-ovo orodje `read_file`, da pridobi vsebino datoteke
3. Nadzornik je nato **izbral AnalysisAgent** in mu poslal vsebino datoteke
4. AnalysisAgent je analiziral strukturo, ton in teme

Opazite, da je nadzornik te odločitve sprejel **avtonomno** glede na uporabnikovo zahtevo — brez trdo kodiranega delovnega toka!

**Končni odziv** je sintetiziran odgovor nadzornika, ki združuje izhode vseh agentov, ki jih je poklical. Primer izpiše agentno področje (agentic scope), ki prikazuje povzetek in analizo rezultatov, shranjenih pri vsakem agentu.

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

### Pojasnilo funkcij agentnega modula

Primer prikazuje več naprednih funkcij agentnega modula. Poglejmo si Agentic Scope in Agent Listeners bolj podrobno.

**Agentic Scope** prikazuje deljeno pomnilniško območje, kamor so agenti shranili svoje rezultate z uporabo `@Agent(outputKey="...")`. To omogoča:
- Kasnejšim agentom dostop do izhodov prej izvedenih agentov
- Nadzorniku sintetiziranje končnega odgovora
- Vam pregled, kaj je vsak agent ustvaril

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String story = scope.readState("story");
List<AgentInvocation> history = scope.agentInvocations("analysisAgent");
```

**Agent Listeners** omogočajo spremljanje in razhroščevanje izvajanja agentov. Korak-po-korak izhod, ki ga vidite v demu, prihaja iz AgentListener-ja, ki se pritrdi na vsako klicanje agenta:
- **beforeAgentInvocation** - Pokliče se, ko nadzornik izbere agenta, kar vam omogoči videti, kateri agent je bil izbran in zakaj
- **afterAgentInvocation** - Pokliče se, ko se agent zaključi, prikazuje njegov rezultat
- **inheritedBySubagents** - Če je true, poslušalec spremlja vse agente v hierarhiji

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
        return true; // Posreduj vsem podagentom
    }
};
```

Poleg vzorca Nadzornika, modul `langchain4j-agentic` ponuja več zmogljivih vzorcev delovnih tokov in funkcij:

| Pattern | Description | Use Case |
|---------|-------------|----------|
| **Sequential** | Execute agents in order, output flows to next | Pipelines: research → analyze → report |
| **Parallel** | Run agents simultaneously | Independent tasks: weather + news + stocks |
| **Loop** | Iterate until condition met | Quality scoring: refine until score ≥ 0.8 |
| **Conditional** | Route based on conditions | Classify → route to specialist agent |
| **Human-in-the-Loop** | Add human checkpoints | Approval workflows, content review |

## Ključni pojmi

**MCP** je idealen, ko želite izkoristiti obstoječe ekosisteme orodij, graditi orodja, ki jih lahko deli več aplikacij, integrirati storitve tretjih oseb s standardnimi protokoli ali zamenjati implementacije orodij brez spreminjanja kode.

**Agentni modul** najbolje deluje, ko želite deklarativne definicije agentov z anotacijami `@Agent`, potrebujete orkestracijo delovnih tokov (zaporedno, zanka, vzporedno), raje obliko agentov na osnovi vmesnikov namesto imperativne kode, ali združujete več agentov, ki si delijo izhode preko `outputKey`.

**Vzorec nadzornega agenta** izstopa, ko delovnega toka ni mogoče vnaprej natančno napovedati in želite, da LLM odloča, ko imate več specializiranih agentov, ki potrebujejo dinamično orkestracijo, pri gradnji pogovornih sistemov, ki usmerjajo k različnim zmožnostim, ali ko želite najbolj prilagodljivo, adaptivno vedenje agenta.

## Čestitamo!

Dokončali ste LangChain4j za začetnike. Naučili ste se:

- Kako graditi pogovno AI s pomnilnikom (Modul 01)
- Vzorce za inženiring pozivov za različna opravila (Modul 02)
- Utemeljitev odgovorov v vaših dokumentih z RAG (Modul 03)
- Ustvarjanje osnovnih AI agentov (asistentov) s prilagojenimi orodji (Modul 04)
- Integracija standardiziranih orodij z LangChain4j MCP in Agentic moduli (Modul 05)

### Kaj sledi?

Po zaključku modulov si oglejte [Vodnik za testiranje](../docs/TESTING.md), da vidite koncepte testiranja LangChain4j v praksi.

**Uradni viri:**
- [Dokumentacija LangChain4j](https://docs.langchain4j.dev/) - Celoviti vodiči in API-referenca
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Izvorna koda in primeri
- [Vadnice LangChain4j](https://docs.langchain4j.dev/tutorials/) - Korak-po-koraku vadnice za različne primere uporabe

Hvala, ker ste zaključili ta tečaj!

---

**Navigacija:** [← Prejšnji: Modul 04 - Orodja](../04-tools/README.md) | [Nazaj na glavno](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Izjava o omejitvi odgovornosti:
Ta dokument je bil preveden z uporabo storitve prevajanja z umetno inteligenco [Co-op Translator](https://github.com/Azure/co-op-translator). Čeprav si prizadevamo za natančnost, upoštevajte, da lahko avtomatizirani prevodi vsebujejo napake ali netočnosti. Izvirni dokument v izvirnem jeziku naj se šteje za avtoritativni vir. Za kritične informacije priporočamo strokovni prevod, opravljen s strani človeka. Ne odgovarjamo za morebitne nesporazume ali napačne razlage, ki izhajajo iz uporabe tega prevoda.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->