<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T14:59:58+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "da"
}
-->
# Module 00: Hurtig start

## Indholdsfortegnelse

- [Introduktion](../../../00-quick-start)
- [Hvad er LangChain4j?](../../../00-quick-start)
- [LangChain4j-afhængigheder](../../../00-quick-start)
- [Forudsætninger](../../../00-quick-start)
- [Opsætning](../../../00-quick-start)
  - [1. Få dit GitHub-token](../../../00-quick-start)
  - [2. Indstil dit token](../../../00-quick-start)
- [Kør eksemplerne](../../../00-quick-start)
  - [1. Grundlæggende chat](../../../00-quick-start)
  - [2. Prompt-mønstre](../../../00-quick-start)
  - [3. Funktionskald](../../../00-quick-start)
  - [4. Dokument Q&A (RAG)](../../../00-quick-start)
- [Hvad hvert eksempel viser](../../../00-quick-start)
- [Næste skridt](../../../00-quick-start)
- [Fejlfinding](../../../00-quick-start)

## Introduktion

Denne hurtigstart er designet til at få dig i gang med LangChain4j så hurtigt som muligt. Den dækker det absolutte grundlag for at bygge AI-applikationer med LangChain4j og GitHub Models. I de næste moduler vil du bruge Azure OpenAI med LangChain4j til at bygge mere avancerede applikationer.

## Hvad er LangChain4j?

LangChain4j er et Java-bibliotek, der forenkler opbygningen af AI-drevne applikationer. I stedet for at håndtere HTTP-klienter og JSON-parsing arbejder du med rene Java-API'er.

"Chain" i LangChain refererer til at kæde flere komponenter sammen – du kan kæde en prompt til en model til en parser, eller kæde flere AI-kald sammen, hvor output fra det ene bliver input til det næste. Denne hurtigstart fokuserer på det grundlæggende, før vi udforsker mere komplekse kæder.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1.da.png" alt="LangChain4j Chaining Concept" width="800"/>

*Kædning af komponenter i LangChain4j – byggeklodser forbinder for at skabe kraftfulde AI-arbejdsgange*

Vi bruger tre kernekomponenter:

**ChatLanguageModel** – Interfacet til AI-modelinteraktioner. Kald `model.chat("prompt")` og få en svarstreng. Vi bruger `OpenAiOfficialChatModel`, som fungerer med OpenAI-kompatible endpoints som GitHub Models.

**AiServices** – Opretter typesikre AI-serviceinterfaces. Definer metoder, annoter dem med `@Tool`, og LangChain4j håndterer orkestreringen. AI'en kalder automatisk dine Java-metoder, når det er nødvendigt.

**MessageWindowChatMemory** – Vedligeholder samtalehistorik. Uden dette er hver forespørgsel uafhængig. Med det husker AI tidligere beskeder og bevarer kontekst over flere runder.

<img src="../../../translated_images/architecture.eedc993a1c576839.da.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j-arkitektur – kernekomponenter arbejder sammen for at drive dine AI-applikationer*

## LangChain4j-afhængigheder

Denne hurtigstart bruger to Maven-afhængigheder i [`pom.xml`](../../../00-quick-start/pom.xml):

```xml
<!-- Core LangChain4j library -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>

<!-- OpenAI integration (works with GitHub Models) -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

Modulet `langchain4j-open-ai-official` leverer klassen `OpenAiOfficialChatModel`, som forbinder til OpenAI-kompatible API'er. GitHub Models bruger samme API-format, så der er ikke behov for en særlig adapter – bare peg base-URL'en til `https://models.github.ai/inference`.

## Forudsætninger

**Bruger du Dev Container?** Java og Maven er allerede installeret. Du skal kun bruge et GitHub Personal Access Token.

**Lokal udvikling:**
- Java 21+, Maven 3.9+
- GitHub Personal Access Token (instruktioner nedenfor)

> **Bemærk:** Dette modul bruger `gpt-4.1-nano` fra GitHub Models. Ændr ikke modelnavnet i koden – det er konfigureret til at fungere med GitHubs tilgængelige modeller.

## Opsætning

### 1. Få dit GitHub-token

1. Gå til [GitHub Settings → Personal Access Tokens](https://github.com/settings/personal-access-tokens)
2. Klik på "Generate new token"
3. Sæt et beskrivende navn (f.eks. "LangChain4j Demo")
4. Sæt udløbstid (7 dage anbefales)
5. Under "Account permissions", find "Models" og sæt til "Read-only"
6. Klik på "Generate token"
7. Kopiér og gem dit token – du får det ikke vist igen

### 2. Indstil dit token

**Mulighed 1: Brug VS Code (anbefalet)**

Hvis du bruger VS Code, tilføj dit token til `.env`-filen i projektets rodmappe:

Hvis `.env`-filen ikke findes, kopier `.env.example` til `.env` eller opret en ny `.env`-fil i projektets rodmappe.

**Eksempel på `.env`-fil:**
```bash
# I /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Så kan du blot højreklikke på en hvilken som helst demo-fil (f.eks. `BasicChatDemo.java`) i Explorer og vælge **"Run Java"** eller bruge launch-konfigurationerne fra Run and Debug-panelet.

**Mulighed 2: Brug terminal**

Sæt token som en miljøvariabel:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Kør eksemplerne

**Bruger du VS Code:** Højreklik blot på en demo-fil i Explorer og vælg **"Run Java"**, eller brug launch-konfigurationerne fra Run and Debug-panelet (sørg for at have tilføjet dit token til `.env`-filen først).

**Bruger du Maven:** Alternativt kan du køre fra kommandolinjen:

### 1. Grundlæggende chat

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Prompt-mønstre

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Viser zero-shot, few-shot, chain-of-thought og rollebaseret prompting.

### 3. Funktionskald

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI kalder automatisk dine Java-metoder, når det er nødvendigt.

### 4. Dokument Q&A (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Stil spørgsmål om indholdet i `document.txt`.

## Hvad hvert eksempel viser

**Grundlæggende chat** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Start her for at se LangChain4j i sin enkleste form. Du opretter en `OpenAiOfficialChatModel`, sender en prompt med `.chat()`, og får et svar tilbage. Dette demonstrerer fundamentet: hvordan man initialiserer modeller med brugerdefinerede endpoints og API-nøgler. Når du forstår dette mønster, bygger alt andet videre på det.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Prøv med [GitHub Copilot](https://github.com/features/copilot) Chat:** Åbn [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) og spørg:
> - "Hvordan skifter jeg fra GitHub Models til Azure OpenAI i denne kode?"
> - "Hvilke andre parametre kan jeg konfigurere i OpenAiOfficialChatModel.builder()?"
> - "Hvordan tilføjer jeg streaming-svar i stedet for at vente på det komplette svar?"

**Prompt Engineering** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Nu hvor du ved, hvordan du taler til en model, lad os udforske, hvad du siger til den. Denne demo bruger samme modelopsætning, men viser fire forskellige prompt-mønstre. Prøv zero-shot prompts for direkte instruktioner, few-shot prompts der lærer fra eksempler, chain-of-thought prompts der afslører ræsonneringstrin, og rollebaserede prompts der sætter kontekst. Du vil se, hvordan den samme model giver dramatisk forskellige resultater baseret på, hvordan du formulerer din anmodning.

```java
PromptTemplate template = PromptTemplate.from(
    "What's the best time to visit {{destination}} for {{activity}}?"
);

Prompt prompt = template.apply(Map.of(
    "destination", "Paris",
    "activity", "sightseeing"
));

String response = model.chat(prompt.text());
```

> **🤖 Prøv med [GitHub Copilot](https://github.com/features/copilot) Chat:** Åbn [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) og spørg:
> - "Hvad er forskellen mellem zero-shot og few-shot prompting, og hvornår skal jeg bruge hver?"
> - "Hvordan påvirker temperatur-parameteren modellens svar?"
> - "Hvilke teknikker findes der for at forhindre prompt injection-angreb i produktion?"
> - "Hvordan kan jeg oprette genanvendelige PromptTemplate-objekter til almindelige mønstre?"

**Tool Integration** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Her bliver LangChain4j kraftfuld. Du bruger `AiServices` til at skabe en AI-assistent, der kan kalde dine Java-metoder. Bare annoter metoder med `@Tool("beskrivelse")`, og LangChain4j håndterer resten – AI'en beslutter automatisk, hvornår den skal bruge hvert værktøj baseret på, hvad brugeren spørger om. Dette demonstrerer funktionskald, en nøglemetode til at bygge AI, der kan handle, ikke bare svare på spørgsmål.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Prøv med [GitHub Copilot](https://github.com/features/copilot) Chat:** Åbn [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) og spørg:
> - "Hvordan fungerer @Tool-annotationen, og hvad gør LangChain4j med den bag scenen?"
> - "Kan AI kalde flere værktøjer i rækkefølge for at løse komplekse problemer?"
> - "Hvad sker der, hvis et værktøj kaster en undtagelse – hvordan håndterer jeg fejl?"
> - "Hvordan integrerer jeg en rigtig API i stedet for dette regneeksempel?"

**Dokument Q&A (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Her ser du fundamentet for RAG (retrieval-augmented generation). I stedet for at stole på modellens træningsdata, indlæser du indhold fra [`document.txt`](../../../00-quick-start/document.txt) og inkluderer det i prompten. AI svarer baseret på dit dokument, ikke sin generelle viden. Dette er det første skridt mod at bygge systemer, der kan arbejde med dine egne data.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Bemærk:** Denne simple tilgang indlæser hele dokumentet i prompten. For store filer (>10KB) overskrider du kontekstgrænser. Modul 03 dækker chunking og vektorsøgning til produktions-RAG-systemer.

> **🤖 Prøv med [GitHub Copilot](https://github.com/features/copilot) Chat:** Åbn [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) og spørg:
> - "Hvordan forhindrer RAG AI-hallucinationer sammenlignet med at bruge modellens træningsdata?"
> - "Hvad er forskellen mellem denne simple tilgang og brug af vektorindlejringer til opslag?"
> - "Hvordan skalerer jeg dette til at håndtere flere dokumenter eller større vidensbaser?"
> - "Hvad er bedste praksis for at strukturere prompten, så AI kun bruger den givne kontekst?"

## Fejlfinding

Eksemplerne inkluderer `.logRequests(true)` og `.logResponses(true)` for at vise API-kald i konsollen. Dette hjælper med at fejlfinde autentificeringsfejl, ratebegrænsninger eller uventede svar. Fjern disse flag i produktion for at reducere logstøj.

## Næste skridt

**Næste modul:** [01-introduction - Kom godt i gang med LangChain4j og gpt-5 på Azure](../01-introduction/README.md)

---

**Navigation:** [← Tilbage til hoved](../README.md) | [Næste: Module 01 - Introduktion →](../01-introduction/README.md)

---

## Fejlfinding

### Første Maven-build

**Problem:** Første `mvn clean compile` eller `mvn package` tager lang tid (10-15 minutter)

**Årsag:** Maven skal downloade alle projektets afhængigheder (Spring Boot, LangChain4j-biblioteker, Azure SDK'er osv.) ved første build.

**Løsning:** Dette er normal opførsel. Efterfølgende builds bliver meget hurtigere, da afhængigheder caches lokalt. Download-tiden afhænger af din netværkshastighed.

### PowerShell Maven-kommando-syntaks

**Problem:** Maven-kommandoer fejler med fejl `Unknown lifecycle phase ".mainClass=..."`

**Årsag:** PowerShell fortolker `=` som en variabeltildelingsoperator, hvilket bryder Maven-egenskabssyntaksen.

**Løsning:** Brug stop-parsing-operatoren `--%` før Maven-kommandoen:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

`--%`-operatoren fortæller PowerShell at sende alle resterende argumenter bogstaveligt til Maven uden fortolkning.

### Windows PowerShell Emoji-visning

**Problem:** AI-svar viser skraldetegn (f.eks. `????` eller `â??`) i stedet for emojis i PowerShell

**Årsag:** PowerShells standardkodning understøtter ikke UTF-8-emojis

**Løsning:** Kør denne kommando før du kører Java-applikationer:
```cmd
chcp 65001
```

Dette tvinger UTF-8-kodning i terminalen. Alternativt kan du bruge Windows Terminal, som har bedre Unicode-understøttelse.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokument er blevet oversat ved hjælp af AI-oversættelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selvom vi bestræber os på nøjagtighed, bedes du være opmærksom på, at automatiserede oversættelser kan indeholde fejl eller unøjagtigheder. Det oprindelige dokument på dets modersmål bør betragtes som den autoritative kilde. For kritisk information anbefales professionel menneskelig oversættelse. Vi påtager os intet ansvar for misforståelser eller fejltolkninger, der opstår som følge af brugen af denne oversættelse.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->