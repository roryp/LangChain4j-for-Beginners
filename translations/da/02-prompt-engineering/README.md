<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "8d787826cad7e92bf5cdbd116b1e6116",
  "translation_date": "2025-12-13T16:11:50+00:00",
  "source_file": "02-prompt-engineering/README.md",
  "language_code": "da"
}
-->
# Modul 02: Prompt Engineering med GPT-5

## Indholdsfortegnelse

- [Hvad du vil lære](../../../02-prompt-engineering)
- [Forudsætninger](../../../02-prompt-engineering)
- [Forståelse af Prompt Engineering](../../../02-prompt-engineering)
- [Hvordan dette bruger LangChain4j](../../../02-prompt-engineering)
- [Kerne-mønstrene](../../../02-prompt-engineering)
- [Brug af eksisterende Azure-ressourcer](../../../02-prompt-engineering)
- [Applikationsskærmbilleder](../../../02-prompt-engineering)
- [Udforskning af mønstrene](../../../02-prompt-engineering)
  - [Lav vs Høj Iver](../../../02-prompt-engineering)
  - [Opgaveudførelse (Tool Preambles)](../../../02-prompt-engineering)
  - [Selvreflekterende kode](../../../02-prompt-engineering)
  - [Struktureret analyse](../../../02-prompt-engineering)
  - [Multi-Turn Chat](../../../02-prompt-engineering)
  - [Trin-for-trin ræsonnering](../../../02-prompt-engineering)
  - [Begrænset output](../../../02-prompt-engineering)
- [Hvad du virkelig lærer](../../../02-prompt-engineering)
- [Næste skridt](../../../02-prompt-engineering)

## Hvad du vil lære

I det forrige modul så du, hvordan hukommelse muliggør konversationel AI og brugte GitHub Models til grundlæggende interaktioner. Nu vil vi fokusere på, hvordan du stiller spørgsmål – selve prompts – ved brug af Azure OpenAI's GPT-5. Den måde, du strukturerer dine prompts på, påvirker dramatisk kvaliteten af de svar, du får.

Vi bruger GPT-5, fordi det introducerer kontrol over ræsonnering – du kan fortælle modellen, hvor meget den skal tænke, før den svarer. Det gør forskellige prompting-strategier mere tydelige og hjælper dig med at forstå, hvornår du skal bruge hver tilgang. Vi får også fordel af Azures færre ratebegrænsninger for GPT-5 sammenlignet med GitHub Models.

## Forudsætninger

- Fuldført Modul 01 (Azure OpenAI-ressourcer implementeret)
- `.env`-fil i rodmappen med Azure-legitimationsoplysninger (oprettet af `azd up` i Modul 01)

> **Bemærk:** Hvis du ikke har fuldført Modul 01, følg først implementeringsinstruktionerne der.

## Forståelse af Prompt Engineering

Prompt engineering handler om at designe inputtekst, der konsekvent giver dig de resultater, du har brug for. Det handler ikke kun om at stille spørgsmål – det handler om at strukturere forespørgsler, så modellen præcist forstår, hvad du vil have, og hvordan det skal leveres.

Tænk på det som at give instruktioner til en kollega. "Fix fejlen" er vagt. "Fix null pointer exception i UserService.java linje 45 ved at tilføje en null-check" er specifikt. Sprogmodeller fungerer på samme måde – specificitet og struktur betyder noget.

## Hvordan dette bruger LangChain4j

Dette modul demonstrerer avancerede prompting-mønstre ved brug af samme LangChain4j fundament som i tidligere moduler, med fokus på promptstruktur og ræsonneringskontrol.

<img src="../../../translated_images/da/langchain4j-flow.48e534666213010b.png" alt="LangChain4j Flow" width="800"/>

*Hvordan LangChain4j forbinder dine prompts til Azure OpenAI GPT-5*

**Afhængigheder** – Modul 02 bruger følgende langchain4j-afhængigheder defineret i `pom.xml`:
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

**OpenAiOfficialChatModel Konfiguration** – [LangChainConfig.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/config/LangChainConfig.java)

Chatmodellen konfigureres manuelt som en Spring bean ved brug af OpenAI Official klienten, som understøtter Azure OpenAI endpoints. Den væsentlige forskel fra Modul 01 er, hvordan vi strukturerer de prompts, der sendes til `chatModel.chat()`, ikke selve modelopsætningen.

**System- og Brugermeddelelser** – [Gpt5PromptService.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/service/Gpt5PromptService.java)

LangChain4j adskiller meddelelsestyper for klarhed. `SystemMessage` sætter AI’ens adfærd og kontekst (som "Du er en kodeanmelder"), mens `UserMessage` indeholder den faktiske forespørgsel. Denne adskillelse lader dig opretholde konsistent AI-adfærd på tværs af forskellige brugerforespørgsler.

```java
SystemMessage systemMsg = SystemMessage.from(
    "You are a helpful Java programming expert."
);

UserMessage userMsg = UserMessage.from(
    "Explain what a List is in Java"
);

String response = chatModel.chat(systemMsg, userMsg);
```

<img src="../../../translated_images/da/message-types.93e0779798a17c9d.png" alt="Message Types Architecture" width="800"/>

*SystemMessage giver vedvarende kontekst, mens UserMessages indeholder individuelle forespørgsler*

**MessageWindowChatMemory til Multi-Turn** – Til multi-turn samtalemønsteret genbruger vi `MessageWindowChatMemory` fra Modul 01. Hver session får sin egen hukommelsesinstans gemt i et `Map<String, ChatMemory>`, hvilket tillader flere samtidige samtaler uden kontekstblanding.

**Promptskabeloner** – Det egentlige fokus her er prompt engineering, ikke nye LangChain4j API’er. Hvert mønster (lav iver, høj iver, opgaveudførelse osv.) bruger samme `chatModel.chat(prompt)` metode, men med omhyggeligt strukturerede promptstrenge. XML-tags, instruktioner og formatering er alle en del af promptteksten, ikke LangChain4j-funktioner.

**Ræsonneringskontrol** – GPT-5’s ræsonneringsindsats styres gennem promptinstruktioner som "maksimalt 2 ræsonneringstrin" eller "undersøg grundigt". Dette er prompt engineering-teknikker, ikke LangChain4j-konfigurationer. Biblioteket leverer blot dine prompts til modellen.

Det vigtigste: LangChain4j leverer infrastrukturen (modelforbindelse via [LangChainConfig.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/config/LangChainConfig.java), hukommelse, meddelelseshåndtering via [Gpt5PromptService.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/service/Gpt5PromptService.java)), mens dette modul lærer dig, hvordan du udformer effektive prompts inden for denne infrastruktur.

## Kerne-mønstrene

Ikke alle problemer kræver samme tilgang. Nogle spørgsmål har brug for hurtige svar, andre dyb tænkning. Nogle har brug for synlig ræsonnering, andre kun resultater. Dette modul dækker otte prompting-mønstre – hver optimeret til forskellige scenarier. Du vil eksperimentere med dem alle for at lære, hvornår hver tilgang fungerer bedst.

<img src="../../../translated_images/da/eight-patterns.fa1ebfdf16f71e9a.png" alt="Eight Prompting Patterns" width="800"/>

*Oversigt over de otte prompt engineering-mønstre og deres anvendelsestilfælde*

<img src="../../../translated_images/da/reasoning-effort.db4a3ba5b8e392c1.png" alt="Reasoning Effort Comparison" width="800"/>

*Lav iver (hurtig, direkte) vs Høj iver (grundig, udforskende) ræsonneringsmetoder*

**Lav Iver (Hurtig & Fokuseret)** – Til simple spørgsmål, hvor du ønsker hurtige, direkte svar. Modellen laver minimal ræsonnering – maksimalt 2 trin. Brug dette til beregninger, opslag eller ligetil spørgsmål.

```java
String prompt = """
    <reasoning_effort>low</reasoning_effort>
    <instruction>maximum 2 reasoning steps</instruction>
    
    What is 15% of 200?
    """;

String response = chatModel.chat(prompt);
```

> 💡 **Udforsk med GitHub Copilot:** Åbn [`Gpt5PromptService.java`](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/service/Gpt5PromptService.java) og spørg:
> - "Hvad er forskellen mellem lav iver og høj iver prompting-mønstre?"
> - "Hvordan hjælper XML-tags i prompts med at strukturere AI’ens svar?"
> - "Hvornår skal jeg bruge selvrefleksionsmønstre vs direkte instruktion?"

**Høj Iver (Dyb & Grundig)** – Til komplekse problemer, hvor du ønsker omfattende analyse. Modellen undersøger grundigt og viser detaljeret ræsonnering. Brug dette til systemdesign, arkitekturvalg eller kompleks forskning.

```java
String prompt = """
    <reasoning_effort>high</reasoning_effort>
    <instruction>explore thoroughly, show detailed reasoning</instruction>
    
    Design a caching strategy for a high-traffic REST API.
    """;

String response = chatModel.chat(prompt);
```

**Opgaveudførelse (Trin-for-trin fremgang)** – Til arbejdsprocesser med flere trin. Modellen giver en plan på forhånd, fortæller om hvert trin undervejs og giver derefter et resumé. Brug dette til migrationer, implementeringer eller enhver flertrinsproces.

```java
String prompt = """
    <task>Create a REST endpoint for user registration</task>
    <preamble>Provide an upfront plan</preamble>
    <narration>Narrate each step as you work</narration>
    <summary>Summarize what was accomplished</summary>
    """;

String response = chatModel.chat(prompt);
```

Chain-of-Thought prompting beder eksplicit modellen om at vise sin ræsonneringsproces, hvilket forbedrer nøjagtigheden for komplekse opgaver. Trin-for-trin opdelingen hjælper både mennesker og AI med at forstå logikken.

> **🤖 Prøv med [GitHub Copilot](https://github.com/features/copilot) Chat:** Spørg om dette mønster:
> - "Hvordan tilpasser jeg opgaveudførelsesmønstret til langvarige operationer?"
> - "Hvad er bedste praksis for at strukturere tool preambles i produktionsapplikationer?"
> - "Hvordan kan jeg fange og vise mellemliggende fremdriftsopdateringer i en UI?"

<img src="../../../translated_images/da/task-execution-pattern.9da3967750ab5c1e.png" alt="Task Execution Pattern" width="800"/>

*Plan → Udfør → Opsummer arbejdsproces til flertrinsopgaver*

**Selvreflekterende kode** – Til generering af produktionsklar kode. Modellen genererer kode, tjekker den mod kvalitetskriterier og forbedrer den iterativt. Brug dette, når du bygger nye funktioner eller services.

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

<img src="../../../translated_images/da/self-reflection-cycle.6f71101ca0bd28cc.png" alt="Self-Reflection Cycle" width="800"/>

*Iterativ forbedringscyklus – generer, evaluer, identificer problemer, forbedr, gentag*

**Struktureret analyse** – Til konsistent evaluering. Modellen gennemgår kode ved hjælp af en fast ramme (korrekthed, praksis, ydeevne, sikkerhed). Brug dette til kodegennemgange eller kvalitetsvurderinger.

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

> **🤖 Prøv med [GitHub Copilot](https://github.com/features/copilot) Chat:** Spørg om struktureret analyse:
> - "Hvordan kan jeg tilpasse analyse-rammen til forskellige typer kodegennemgange?"
> - "Hvad er den bedste måde at parse og handle på struktureret output programmatisk?"
> - "Hvordan sikrer jeg konsistente alvorlighedsniveauer på tværs af forskellige gennemgangssessioner?"

<img src="../../../translated_images/da/structured-analysis-pattern.0af3b690b60cf2d6.png" alt="Structured Analysis Pattern" width="800"/>

*Fire-kategori rammeværk til konsistente kodegennemgange med alvorlighedsniveauer*

**Multi-Turn Chat** – Til samtaler, der kræver kontekst. Modellen husker tidligere beskeder og bygger videre på dem. Brug dette til interaktive hjælpesessioner eller komplekse Q&A.

```java
ChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

memory.add(UserMessage.from("What is Spring Boot?"));
AiMessage aiMessage1 = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage1);

memory.add(UserMessage.from("Show me an example"));
AiMessage aiMessage2 = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage2);
```

<img src="../../../translated_images/da/context-memory.dff30ad9fa78832a.png" alt="Context Memory" width="800"/>

*Hvordan samtalekontekst akkumuleres over flere omgange indtil token-grænsen nås*

**Trin-for-trin ræsonnering** – Til problemer, der kræver synlig logik. Modellen viser eksplicit ræsonnering for hvert trin. Brug dette til matematikopgaver, logikpuslespil eller når du skal forstå tænkeprocessen.

```java
String prompt = """
    <instruction>Show your reasoning step-by-step</instruction>
    
    If a train travels 120 km in 2 hours, then stops for 30 minutes,
    then travels another 90 km in 1.5 hours, what is the average speed
    for the entire journey including the stop?
    """;

String response = chatModel.chat(prompt);
```

<img src="../../../translated_images/da/step-by-step-pattern.a99ea4ca1c48578c.png" alt="Step-by-Step Pattern" width="800"/>

*Nedbrydning af problemer i eksplicitte logiske trin*

**Begrænset output** – Til svar med specifikke formatkrav. Modellen følger strengt format- og længderegler. Brug dette til resuméer eller når du har brug for præcis outputstruktur.

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

<img src="../../../translated_images/da/constrained-output-pattern.0ce39a682a6795c2.png" alt="Constrained Output Pattern" width="800"/>

*Håndhævelse af specifikke format-, længde- og strukturkrav*

## Brug af eksisterende Azure-ressourcer

**Bekræft implementering:**

Sørg for, at `.env`-filen findes i rodmappen med Azure-legitimationsoplysninger (oprettet under Modul 01):
```bash
cat ../.env  # Skal vise AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Start applikationen:**

> **Bemærk:** Hvis du allerede har startet alle applikationer med `./start-all.sh` fra Modul 01, kører dette modul allerede på port 8083. Du kan springe startkommandoerne over nedenfor og gå direkte til http://localhost:8083.

**Mulighed 1: Brug af Spring Boot Dashboard (anbefalet til VS Code-brugere)**

Dev-containeren inkluderer Spring Boot Dashboard-udvidelsen, som giver en visuel grænseflade til at administrere alle Spring Boot-applikationer. Du finder den i aktivitetsbjælken til venstre i VS Code (se efter Spring Boot-ikonet).

Fra Spring Boot Dashboard kan du:
- Se alle tilgængelige Spring Boot-applikationer i arbejdsområdet
- Starte/stoppe applikationer med et enkelt klik
- Se applikationslogs i realtid
- Overvåge applikationsstatus

Klik blot på play-knappen ved siden af "prompt-engineering" for at starte dette modul, eller start alle moduler på én gang.

<img src="../../../translated_images/da/dashboard.da2c2130c904aaf0.png" alt="Spring Boot Dashboard" width="400"/>

**Mulighed 2: Brug af shell-scripts**

Start alle webapplikationer (moduler 01-04):

**Bash:**
```bash
cd ..  # Fra roddirectory
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Fra roddirectory
.\start-all.ps1
```

Eller start kun dette modul:

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

Begge scripts indlæser automatisk miljøvariabler fra rodens `.env`-fil og bygger JAR-filerne, hvis de ikke findes.

> **Bemærk:** Hvis du foretrækker at bygge alle moduler manuelt før start:
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

Åbn http://localhost:8083 i din browser.

**For at stoppe:**

**Bash:**
```bash
./stop.sh  # Kun denne modul
# Eller
cd .. && ./stop-all.sh  # Alle moduler
```

**PowerShell:**
```powershell
.\stop.ps1  # Kun denne modul
# Eller
cd ..; .\stop-all.ps1  # Alle moduler
```

## Applikationsskærmbilleder

<img src="../../../translated_images/da/dashboard-home.5444dbda4bc1f79d.png" alt="Dashboard Home" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/>

*Hoveddashboard, der viser alle 8 prompt engineering-mønstre med deres karakteristika og anvendelsestilfælde*

## Udforskning af mønstrene

Webgrænsefladen lader dig eksperimentere med forskellige prompting-strategier. Hvert mønster løser forskellige problemer – prøv dem for at se, hvornår hver tilgang fungerer bedst.

### Lav vs Høj Iver

Stil et simpelt spørgsmål som "Hvad er 15% af 200?" med Lav Iver. Du får et øjeblikkeligt, direkte svar. Stil nu noget komplekst som "Design en caching-strategi for en højtrafikeret API" med Høj Iver. Se, hvordan modellen sænker tempoet og giver detaljeret ræsonnering. Samme model, samme spørgsmålstruktur – men prompten fortæller, hvor meget den skal tænke.

<img src="../../../translated_images/da/low-eagerness-demo.898894591fb23aa0.png" alt="Low Eagerness Demo" width="800"/>
*Hurtig beregning med minimal ræsonnering*

<img src="../../../translated_images/da/high-eagerness-demo.4ac93e7786c5a376.png" alt="High Eagerness Demo" width="800"/>

*Omfattende caching-strategi (2.8MB)*

### Opgaveudførelse (Værktøjsintroduktioner)

Workflow med flere trin drager fordel af forudgående planlægning og løbende fortælling. Modellen skitserer, hvad den vil gøre, fortæller om hvert trin og opsummerer derefter resultaterne.

<img src="../../../translated_images/da/tool-preambles-demo.3ca4881e417f2e28.png" alt="Task Execution Demo" width="800"/>

*Oprettelse af en REST-endpoint med trin-for-trin fortælling (3.9MB)*

### Selvreflekterende kode

Prøv "Opret en e-mail valideringstjeneste". I stedet for bare at generere kode og stoppe, genererer modellen, evaluerer mod kvalitetskriterier, identificerer svagheder og forbedrer. Du vil se den iterere, indtil koden opfylder produktionsstandarder.

<img src="../../../translated_images/da/self-reflecting-code-demo.851ee05c988e743f.png" alt="Self-Reflecting Code Demo" width="800"/>

*Færdig e-mail valideringstjeneste (5.2MB)*

### Struktureret analyse

Kodegennemgange kræver konsistente evalueringsrammer. Modellen analyserer kode ved hjælp af faste kategorier (korrekthed, praksis, ydeevne, sikkerhed) med alvorlighedsniveauer.

<img src="../../../translated_images/da/structured-analysis-demo.9ef892194cd23bc8.png" alt="Structured Analysis Demo" width="800"/>

*Framework-baseret kodegennemgang*

### Multi-turn chat

Spørg "Hvad er Spring Boot?" og følg straks op med "Vis mig et eksempel". Modellen husker dit første spørgsmål og giver dig et specifikt Spring Boot-eksempel. Uden hukommelse ville det andet spørgsmål være for vagt.

<img src="../../../translated_images/da/multi-turn-chat-demo.0d2d9b9a86a12b4b.png" alt="Multi-Turn Chat Demo" width="800"/>

*Bevarelse af kontekst på tværs af spørgsmål*

### Trin-for-trin ræsonnering

Vælg et matematikproblem og prøv det med både Trin-for-trin ræsonnering og Lav iver. Lav iver giver dig bare svaret - hurtigt men uigennemsigtigt. Trin-for-trin viser dig hver beregning og beslutning.

<img src="../../../translated_images/da/step-by-step-reasoning-demo.12139513356faecd.png" alt="Step-by-Step Reasoning Demo" width="800"/>

*Matematikproblem med eksplicitte trin*

### Begrænset output

Når du har brug for specifikke formater eller ordantal, håndhæver dette mønster streng overholdelse. Prøv at generere et resumé med præcis 100 ord i punktform.

<img src="../../../translated_images/da/constrained-output-demo.567cc45b75da1633.png" alt="Constrained Output Demo" width="800"/>

*Maskinlæringsresumé med formatkontrol*

## Hvad du virkelig lærer

**Ræsonneringsindsats ændrer alt**

GPT-5 lader dig styre beregningsindsatsen gennem dine prompts. Lav indsats betyder hurtige svar med minimal udforskning. Høj indsats betyder, at modellen tager sig tid til at tænke dybt. Du lærer at matche indsats med opgavens kompleksitet - spild ikke tid på simple spørgsmål, men skynd dig heller ikke med komplekse beslutninger.

**Struktur styrer adfærd**

Læg mærke til XML-tags i prompts? De er ikke dekorative. Modeller følger strukturerede instruktioner mere pålideligt end frit tekst. Når du har brug for flertrinsprocesser eller kompleks logik, hjælper struktur modellen med at holde styr på, hvor den er, og hvad der kommer næste.

<img src="../../../translated_images/da/prompt-structure.a77763d63f4e2f89.png" alt="Prompt Structure" width="800"/>

*Anatomi af en velstruktureret prompt med klare sektioner og XML-stil organisering*

**Kvalitet gennem selvevaluering**

De selvreflekterende mønstre fungerer ved at gøre kvalitetskriterier eksplicitte. I stedet for at håbe, at modellen "gør det rigtigt", fortæller du den præcis, hvad "rigtigt" betyder: korrekt logik, fejlhåndtering, ydeevne, sikkerhed. Modellen kan så evaluere sit eget output og forbedre sig. Det forvandler kodegenerering fra et lotteri til en proces.

**Kontekst er begrænset**

Multi-turn samtaler fungerer ved at inkludere beskedhistorik med hver anmodning. Men der er en grænse - hver model har et maksimalt tokenantal. Efterhånden som samtaler vokser, får du brug for strategier til at bevare relevant kontekst uden at ramme loftet. Dette modul viser dig, hvordan hukommelse fungerer; senere lærer du, hvornår du skal opsummere, hvornår du skal glemme, og hvornår du skal hente.

## Næste skridt

**Næste modul:** [03-rag - RAG (Retrieval-Augmented Generation)](../03-rag/README.md)

---

**Navigation:** [← Forrige: Modul 01 - Introduktion](../01-introduction/README.md) | [Tilbage til hovedmenu](../README.md) | [Næste: Modul 03 - RAG →](../03-rag/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokument er blevet oversat ved hjælp af AI-oversættelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selvom vi bestræber os på nøjagtighed, bedes du være opmærksom på, at automatiserede oversættelser kan indeholde fejl eller unøjagtigheder. Det oprindelige dokument på dets modersmål bør betragtes som den autoritative kilde. For kritisk information anbefales professionel menneskelig oversættelse. Vi påtager os intet ansvar for misforståelser eller fejltolkninger, der opstår som følge af brugen af denne oversættelse.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->