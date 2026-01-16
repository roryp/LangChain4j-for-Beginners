<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "8d787826cad7e92bf5cdbd116b1e6116",
  "translation_date": "2025-12-13T16:15:00+00:00",
  "source_file": "02-prompt-engineering/README.md",
  "language_code": "nl"
}
-->
# Module 02: Prompt Engineering met GPT-5

## Inhoudsopgave

- [Wat Je Zal Leren](../../../02-prompt-engineering)
- [Vereisten](../../../02-prompt-engineering)
- [Begrijpen van Prompt Engineering](../../../02-prompt-engineering)
- [Hoe Dit LangChain4j Gebruikt](../../../02-prompt-engineering)
- [De Kernpatronen](../../../02-prompt-engineering)
- [Bestaande Azure Resources Gebruiken](../../../02-prompt-engineering)
- [Applicatie Screenshots](../../../02-prompt-engineering)
- [De Patronen Verkennen](../../../02-prompt-engineering)
  - [Lage vs Hoge Bereidheid](../../../02-prompt-engineering)
  - [Taakuitvoering (Tool Preambles)](../../../02-prompt-engineering)
  - [Zelfreflecterende Code](../../../02-prompt-engineering)
  - [Gestructureerde Analyse](../../../02-prompt-engineering)
  - [Multi-Turn Chat](../../../02-prompt-engineering)
  - [Stap-voor-Stap Redeneren](../../../02-prompt-engineering)
  - [Beperkte Output](../../../02-prompt-engineering)
- [Wat Je Echt Leert](../../../02-prompt-engineering)
- [Volgende Stappen](../../../02-prompt-engineering)

## Wat Je Zal Leren

In de vorige module zag je hoe geheugen conversatie-AI mogelijk maakt en gebruikte je GitHub Models voor basisinteracties. Nu richten we ons op hoe je vragen stelt - de prompts zelf - met Azure OpenAI's GPT-5. De manier waarop je je prompts structureert, beïnvloedt drastisch de kwaliteit van de antwoorden die je krijgt.

We gebruiken GPT-5 omdat het redeneervermogen introduceert - je kunt het model vertellen hoeveel het moet nadenken voordat het antwoordt. Dit maakt verschillende promptingstrategieën duidelijker en helpt je begrijpen wanneer je welke aanpak moet gebruiken. We profiteren ook van Azure's minder strenge snelheidslimieten voor GPT-5 vergeleken met GitHub Models.

## Vereisten

- Module 01 voltooid (Azure OpenAI resources gedeployed)
- `.env` bestand in de hoofdmap met Azure-gegevens (gemaakt door `azd up` in Module 01)

> **Opmerking:** Als je Module 01 nog niet hebt voltooid, volg dan eerst de deploymentinstructies daar.

## Begrijpen van Prompt Engineering

Prompt engineering gaat over het ontwerpen van invoertekst die consequent de resultaten oplevert die je nodig hebt. Het gaat niet alleen om het stellen van vragen - het gaat om het structureren van verzoeken zodat het model precies begrijpt wat je wilt en hoe het dat moet leveren.

Denk eraan als het geven van instructies aan een collega. "Los de bug op" is vaag. "Los de null pointer exception op in UserService.java regel 45 door een null-check toe te voegen" is specifiek. Taalmodellen werken hetzelfde - specificiteit en structuur zijn belangrijk.

## Hoe Dit LangChain4j Gebruikt

Deze module demonstreert geavanceerde promptingpatronen met dezelfde LangChain4j basis als eerdere modules, met focus op promptstructuur en redeneervermogen.

<img src="../../../translated_images/nl/langchain4j-flow.48e534666213010b.png" alt="LangChain4j Flow" width="800"/>

*Hoe LangChain4j je prompts verbindt met Azure OpenAI GPT-5*

**Afhankelijkheden** - Module 02 gebruikt de volgende langchain4j afhankelijkheden gedefinieerd in `pom.xml`:
```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

**OpenAiOfficialChatModel Configuratie** - [LangChainConfig.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/config/LangChainConfig.java)

Het chatmodel is handmatig geconfigureerd als een Spring bean met de OpenAI Official client, die Azure OpenAI endpoints ondersteunt. Het belangrijkste verschil met Module 01 is hoe we de prompts structureren die naar `chatModel.chat()` worden gestuurd, niet de modelconfiguratie zelf.

**Systeem- en Gebruikersberichten** - [Gpt5PromptService.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/service/Gpt5PromptService.java)

LangChain4j scheidt berichttypes voor duidelijkheid. `SystemMessage` stelt het gedrag en de context van de AI in (zoals "Je bent een code reviewer"), terwijl `UserMessage` het daadwerkelijke verzoek bevat. Deze scheiding laat je consistent AI-gedrag behouden over verschillende gebruikersvragen.

```java
SystemMessage systemMsg = SystemMessage.from(
    "You are a helpful Java programming expert."
);

UserMessage userMsg = UserMessage.from(
    "Explain what a List is in Java"
);

String response = chatModel.chat(systemMsg, userMsg);
```

<img src="../../../translated_images/nl/message-types.93e0779798a17c9d.png" alt="Message Types Architecture" width="800"/>

*SystemMessage biedt blijvende context terwijl UserMessages individuele verzoeken bevatten*

**MessageWindowChatMemory voor Multi-Turn** - Voor het multi-turn conversatiepatroon hergebruiken we `MessageWindowChatMemory` uit Module 01. Elke sessie krijgt een eigen geheugeninstantie opgeslagen in een `Map<String, ChatMemory>`, waardoor meerdere gelijktijdige gesprekken zonder contextvermenging mogelijk zijn.

**Prompt Templates** - De echte focus hier is prompt engineering, niet nieuwe LangChain4j API's. Elk patroon (lage bereidheid, hoge bereidheid, taakuitvoering, enz.) gebruikt dezelfde `chatModel.chat(prompt)` methode maar met zorgvuldig gestructureerde promptstrings. De XML-tags, instructies en opmaak maken allemaal deel uit van de prompttekst, niet van LangChain4j-functies.

**Redeneervermogen Controle** - Het redeneervermogen van GPT-5 wordt gestuurd via promptinstructies zoals "maximaal 2 redeneerstappen" of "grondig verkennen". Dit zijn prompt engineering technieken, geen LangChain4j configuraties. De bibliotheek levert simpelweg je prompts aan het model.

De belangrijkste conclusie: LangChain4j biedt de infrastructuur (modelverbinding via [LangChainConfig.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/config/LangChainConfig.java), geheugen, berichtafhandeling via [Gpt5PromptService.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/service/Gpt5PromptService.java)), terwijl deze module je leert hoe je effectieve prompts maakt binnen die infrastructuur.

## De Kernpatronen

Niet alle problemen vragen om dezelfde aanpak. Sommige vragen hebben snelle antwoorden nodig, andere diep nadenken. Sommige hebben zichtbare redenering nodig, andere alleen resultaten. Deze module behandelt acht promptingpatronen - elk geoptimaliseerd voor verschillende scenario's. Je gaat met ze allemaal experimenteren om te leren wanneer welke aanpak het beste werkt.

<img src="../../../translated_images/nl/eight-patterns.fa1ebfdf16f71e9a.png" alt="Eight Prompting Patterns" width="800"/>

*Overzicht van de acht prompt engineering patronen en hun gebruikssituaties*

<img src="../../../translated_images/nl/reasoning-effort.db4a3ba5b8e392c1.png" alt="Reasoning Effort Comparison" width="800"/>

*Lage bereidheid (snel, direct) vs Hoge bereidheid (grondig, verkennend) redeneerbenaderingen*

**Lage Bereidheid (Snel & Gefocust)** - Voor eenvoudige vragen waarbij je snelle, directe antwoorden wilt. Het model doet minimale redenering - maximaal 2 stappen. Gebruik dit voor berekeningen, opzoeken of eenvoudige vragen.

```java
String prompt = """
    <reasoning_effort>low</reasoning_effort>
    <instruction>maximum 2 reasoning steps</instruction>
    
    What is 15% of 200?
    """;

String response = chatModel.chat(prompt);
```

> 💡 **Verken met GitHub Copilot:** Open [`Gpt5PromptService.java`](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/service/Gpt5PromptService.java) en vraag:
> - "Wat is het verschil tussen lage en hoge bereidheid promptingpatronen?"
> - "Hoe helpen de XML-tags in prompts om het antwoord van de AI te structureren?"
> - "Wanneer moet ik zelfreflectiepatronen gebruiken versus directe instructies?"

**Hoge Bereidheid (Diep & Grondig)** - Voor complexe problemen waarbij je een uitgebreide analyse wilt. Het model verkent grondig en toont gedetailleerde redenering. Gebruik dit voor systeemontwerp, architectuurbeslissingen of complex onderzoek.

```java
String prompt = """
    <reasoning_effort>high</reasoning_effort>
    <instruction>explore thoroughly, show detailed reasoning</instruction>
    
    Design a caching strategy for a high-traffic REST API.
    """;

String response = chatModel.chat(prompt);
```

**Taakuitvoering (Stap-voor-Stap Voortgang)** - Voor workflows met meerdere stappen. Het model geeft een plan vooraf, vertelt elke stap terwijl het werkt, en geeft daarna een samenvatting. Gebruik dit voor migraties, implementaties of elk proces met meerdere stappen.

```java
String prompt = """
    <task>Create a REST endpoint for user registration</task>
    <preamble>Provide an upfront plan</preamble>
    <narration>Narrate each step as you work</narration>
    <summary>Summarize what was accomplished</summary>
    """;

String response = chatModel.chat(prompt);
```

Chain-of-Thought prompting vraagt het model expliciet om zijn redeneerproces te tonen, wat de nauwkeurigheid bij complexe taken verbetert. De stap-voor-stap uitsplitsing helpt zowel mensen als AI om de logica te begrijpen.

> **🤖 Probeer met [GitHub Copilot](https://github.com/features/copilot) Chat:** Vraag over dit patroon:
> - "Hoe zou ik het taakuitvoeringspatroon aanpassen voor langlopende operaties?"
> - "Wat zijn best practices voor het structureren van tool preambles in productieapplicaties?"
> - "Hoe kan ik tussentijdse voortgangsupdates vastleggen en weergeven in een UI?"

<img src="../../../translated_images/nl/task-execution-pattern.9da3967750ab5c1e.png" alt="Task Execution Pattern" width="800"/>

*Plan → Uitvoeren → Samenvatten workflow voor taken met meerdere stappen*

**Zelfreflecterende Code** - Voor het genereren van code van productiekwaliteit. Het model genereert code, controleert die aan kwaliteitscriteria en verbetert iteratief. Gebruik dit bij het bouwen van nieuwe features of services.

```java
String prompt = """
    <task>Create an email validation service</task>
    <quality_criteria>
    - Correct logic and error handling
    - Best practices (clean code, proper naming)
    - Performance optimization
    - Security considerations
    </quality_criteria>
    <instruction>Generate code, evaluate against criteria, improve iteratively</instruction>
    """;

String response = chatModel.chat(prompt);
```

<img src="../../../translated_images/nl/self-reflection-cycle.6f71101ca0bd28cc.png" alt="Self-Reflection Cycle" width="800"/>

*Iteratieve verbeterlus - genereren, evalueren, problemen identificeren, verbeteren, herhalen*

**Gestructureerde Analyse** - Voor consistente evaluatie. Het model beoordeelt code met een vast kader (correctheid, praktijken, prestaties, beveiliging). Gebruik dit voor code reviews of kwaliteitsbeoordelingen.

```java
String prompt = """
    <code>
    public List getUsers() {
        return database.query("SELECT * FROM users");
    }
    </code>
    
    <framework>
    Evaluate using these categories:
    1. Correctness - Logic and functionality
    2. Best Practices - Code quality
    3. Performance - Efficiency concerns
    4. Security - Vulnerabilities
    </framework>
    """;

String response = chatModel.chat(prompt);
```

> **🤖 Probeer met [GitHub Copilot](https://github.com/features/copilot) Chat:** Vraag over gestructureerde analyse:
> - "Hoe kan ik het analyseframework aanpassen voor verschillende soorten code reviews?"
> - "Wat is de beste manier om gestructureerde output programmatisch te parsen en te gebruiken?"
> - "Hoe zorg ik voor consistente ernstniveaus over verschillende review sessies?"

<img src="../../../translated_images/nl/structured-analysis-pattern.0af3b690b60cf2d6.png" alt="Structured Analysis Pattern" width="800"/>

*Vier-categorie kader voor consistente code reviews met ernstniveaus*

**Multi-Turn Chat** - Voor gesprekken die context nodig hebben. Het model onthoudt eerdere berichten en bouwt daarop voort. Gebruik dit voor interactieve hulpsessies of complexe Q&A.

```java
ChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

memory.add(UserMessage.from("What is Spring Boot?"));
AiMessage aiMessage1 = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage1);

memory.add(UserMessage.from("Show me an example"));
AiMessage aiMessage2 = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage2);
```

<img src="../../../translated_images/nl/context-memory.dff30ad9fa78832a.png" alt="Context Memory" width="800"/>

*Hoe gesprekcontext zich opbouwt over meerdere beurten tot de tokenlimiet is bereikt*

**Stap-voor-Stap Redeneren** - Voor problemen die zichtbare logica vereisen. Het model toont expliciete redenering voor elke stap. Gebruik dit voor wiskundige problemen, logische puzzels of wanneer je het denkproces wilt begrijpen.

```java
String prompt = """
    <instruction>Show your reasoning step-by-step</instruction>
    
    If a train travels 120 km in 2 hours, then stops for 30 minutes,
    then travels another 90 km in 1.5 hours, what is the average speed
    for the entire journey including the stop?
    """;

String response = chatModel.chat(prompt);
```

<img src="../../../translated_images/nl/step-by-step-pattern.a99ea4ca1c48578c.png" alt="Step-by-Step Pattern" width="800"/>

*Problemen opdelen in expliciete logische stappen*

**Beperkte Output** - Voor antwoorden met specifieke formaatvereisten. Het model volgt strikt format- en lengtevoorschriften. Gebruik dit voor samenvattingen of wanneer je precieze outputstructuur nodig hebt.

```java
String prompt = """
    <constraints>
    - Exactly 100 words
    - Bullet point format
    - Technical terms only
    </constraints>
    
    Summarize the key concepts of machine learning.
    """;

String response = chatModel.chat(prompt);
```

<img src="../../../translated_images/nl/constrained-output-pattern.0ce39a682a6795c2.png" alt="Constrained Output Pattern" width="800"/>

*Handhaven van specifieke format-, lengte- en structuurvereisten*

## Bestaande Azure Resources Gebruiken

**Controleer deployment:**

Zorg dat het `.env` bestand bestaat in de hoofdmap met Azure-gegevens (gemaakt tijdens Module 01):
```bash
cat ../.env  # Moet AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT tonen
```

**Start de applicatie:**

> **Opmerking:** Als je alle applicaties al gestart hebt met `./start-all.sh` uit Module 01, draait deze module al op poort 8083. Je kunt de startcommando's hieronder overslaan en direct naar http://localhost:8083 gaan.

**Optie 1: Gebruik Spring Boot Dashboard (Aanbevolen voor VS Code gebruikers)**

De dev container bevat de Spring Boot Dashboard extensie, die een visuele interface biedt om alle Spring Boot applicaties te beheren. Je vindt het in de Activiteitenbalk aan de linkerkant van VS Code (zoek naar het Spring Boot icoon).

Vanuit het Spring Boot Dashboard kun je:
- Alle beschikbare Spring Boot applicaties in de workspace zien
- Applicaties starten/stoppen met één klik
- Applicatielogs realtime bekijken
- Applicatiestatus monitoren

Klik simpelweg op de play-knop naast "prompt-engineering" om deze module te starten, of start alle modules tegelijk.

<img src="../../../translated_images/nl/dashboard.da2c2130c904aaf0.png" alt="Spring Boot Dashboard" width="400"/>

**Optie 2: Gebruik shell scripts**

Start alle webapplicaties (modules 01-04):

**Bash:**
```bash
cd ..  # Vanuit de hoofdmap
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Vanuit de hoofdmap
.\start-all.ps1
```

Of start alleen deze module:

**Bash:**
```bash
cd 02-prompt-engineering
./start.sh
```

**PowerShell:**
```powershell
cd 02-prompt-engineering
.\start.ps1
```

Beide scripts laden automatisch omgevingsvariabelen uit het `.env` bestand in de hoofdmap en bouwen de JARs als die nog niet bestaan.

> **Opmerking:** Als je alle modules handmatig wilt bouwen voordat je start:
>
> **Bash:**
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
>
> **PowerShell:**
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

Open http://localhost:8083 in je browser.

**Om te stoppen:**

**Bash:**
```bash
./stop.sh  # Alleen deze module
# Of
cd .. && ./stop-all.sh  # Alle modules
```

**PowerShell:**
```powershell
.\stop.ps1  # Alleen deze module
# Of
cd ..; .\stop-all.ps1  # Alle modules
```

## Applicatie Screenshots

<img src="../../../translated_images/nl/dashboard-home.5444dbda4bc1f79d.png" alt="Dashboard Home" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/>

*Het hoofd dashboard toont alle 8 prompt engineering patronen met hun kenmerken en gebruikssituaties*

## De Patronen Verkennen

De webinterface laat je experimenteren met verschillende promptingstrategieën. Elk patroon lost andere problemen op - probeer ze om te zien wanneer welke aanpak het beste werkt.

### Lage vs Hoge Bereidheid

Stel een eenvoudige vraag zoals "Wat is 15% van 200?" met Lage Bereidheid. Je krijgt een direct, snel antwoord. Stel nu iets complexers zoals "Ontwerp een cachingstrategie voor een API met veel verkeer" met Hoge Bereidheid. Kijk hoe het model vertraagt en gedetailleerde redenering geeft. Zelfde model, zelfde vraagstructuur - maar de prompt vertelt hoeveel het moet nadenken.

<img src="../../../translated_images/nl/low-eagerness-demo.898894591fb23aa0.png" alt="Low Eagerness Demo" width="800"/>
*Snelle berekening met minimale redenering*

<img src="../../../translated_images/nl/high-eagerness-demo.4ac93e7786c5a376.png" alt="High Eagerness Demo" width="800"/>

*Uitgebreide cachingstrategie (2.8MB)*

### Taakuitvoering (Tool Preambles)

Multi-stap workflows profiteren van vooraf plannen en voortgangsnarratie. Het model schetst wat het zal doen, vertelt elke stap, en vat dan de resultaten samen.

<img src="../../../translated_images/nl/tool-preambles-demo.3ca4881e417f2e28.png" alt="Task Execution Demo" width="800"/>

*Een REST-endpoint maken met stapsgewijze narratie (3.9MB)*

### Zelfreflecterende Code

Probeer "Maak een e-mailvalidatieservice". In plaats van alleen code te genereren en te stoppen, genereert het model, evalueert aan de hand van kwaliteitscriteria, identificeert zwaktes en verbetert. Je ziet het itereren totdat de code aan productienormen voldoet.

<img src="../../../translated_images/nl/self-reflecting-code-demo.851ee05c988e743f.png" alt="Self-Reflecting Code Demo" width="800"/>

*Volledige e-mailvalidatieservice (5.2MB)*

### Gestructureerde Analyse

Code reviews hebben consistente evaluatiekaders nodig. Het model analyseert code met vaste categorieën (correctheid, praktijken, prestaties, beveiliging) met ernstniveaus.

<img src="../../../translated_images/nl/structured-analysis-demo.9ef892194cd23bc8.png" alt="Structured Analysis Demo" width="800"/>

*Framework-gebaseerde code review*

### Multi-Turn Chat

Vraag "Wat is Spring Boot?" en volg direct op met "Laat me een voorbeeld zien". Het model onthoudt je eerste vraag en geeft je specifiek een Spring Boot voorbeeld. Zonder geheugen zou die tweede vraag te vaag zijn.

<img src="../../../translated_images/nl/multi-turn-chat-demo.0d2d9b9a86a12b4b.png" alt="Multi-Turn Chat Demo" width="800"/>

*Contextbehoud over vragen heen*

### Stapsgewijze Redenering

Kies een wiskundeprobleem en probeer het met zowel Stapsgewijze Redenering als Lage Begeerte. Lage begeerte geeft je alleen het antwoord - snel maar ondoorzichtig. Stapsgewijs toont elke berekening en beslissing.

<img src="../../../translated_images/nl/step-by-step-reasoning-demo.12139513356faecd.png" alt="Step-by-Step Reasoning Demo" width="800"/>

*Wiskundeprobleem met expliciete stappen*

### Beperkte Output

Wanneer je specifieke formaten of woordenaantallen nodig hebt, dwingt dit patroon strikte naleving af. Probeer een samenvatting te genereren met precies 100 woorden in opsomming.

<img src="../../../translated_images/nl/constrained-output-demo.567cc45b75da1633.png" alt="Constrained Output Demo" width="800"/>

*Machine learning samenvatting met formatcontrole*

## Wat Je Echt Leert

**Redeneerinspanning Verandert Alles**

GPT-5 laat je de rekeninspanning regelen via je prompts. Lage inspanning betekent snelle antwoorden met minimale verkenning. Hoge inspanning betekent dat het model de tijd neemt om diep na te denken. Je leert inspanning af te stemmen op taakcomplexiteit - verspil geen tijd aan simpele vragen, maar haast je ook niet bij complexe beslissingen.

**Structuur Stuurt Gedrag**

Merk je de XML-tags in de prompts op? Ze zijn niet decoratief. Modellen volgen gestructureerde instructies betrouwbaarder dan vrije tekst. Wanneer je multi-stap processen of complexe logica nodig hebt, helpt structuur het model bijhouden waar het is en wat volgt.

<img src="../../../translated_images/nl/prompt-structure.a77763d63f4e2f89.png" alt="Prompt Structure" width="800"/>

*Anatomie van een goed gestructureerde prompt met duidelijke secties en XML-stijl organisatie*

**Kwaliteit Door Zelfevaluatie**

De zelfreflecterende patronen werken door kwaliteitscriteria expliciet te maken. In plaats van te hopen dat het model "het goed doet", vertel je precies wat "goed" betekent: correcte logica, foutafhandeling, prestaties, beveiliging. Het model kan dan zijn eigen output evalueren en verbeteren. Dit verandert codegeneratie van een loterij in een proces.

**Context Is Beperkt**

Multi-turn gesprekken werken door berichtgeschiedenis mee te sturen bij elk verzoek. Maar er is een limiet - elk model heeft een maximum aantal tokens. Naarmate gesprekken groeien, heb je strategieën nodig om relevante context te behouden zonder die limiet te bereiken. Deze module laat zien hoe geheugen werkt; later leer je wanneer samenvatten, wanneer vergeten, en wanneer ophalen.

## Volgende Stappen

**Volgende Module:** [03-rag - RAG (Retrieval-Augmented Generation)](../03-rag/README.md)

---

**Navigatie:** [← Vorige: Module 01 - Introductie](../01-introduction/README.md) | [Terug naar Hoofdmenu](../README.md) | [Volgende: Module 03 - RAG →](../03-rag/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:  
Dit document is vertaald met behulp van de AI-vertalingsdienst [Co-op Translator](https://github.com/Azure/co-op-translator). Hoewel we streven naar nauwkeurigheid, dient u er rekening mee te houden dat geautomatiseerde vertalingen fouten of onnauwkeurigheden kunnen bevatten. Het originele document in de oorspronkelijke taal moet als de gezaghebbende bron worden beschouwd. Voor cruciale informatie wordt professionele menselijke vertaling aanbevolen. Wij zijn niet aansprakelijk voor eventuele misverstanden of verkeerde interpretaties die voortvloeien uit het gebruik van deze vertaling.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->