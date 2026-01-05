<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T15:20:56+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "hr"
}
-->
# Modul 00: Brzi početak

## Sadržaj

- [Uvod](../../../00-quick-start)
- [Što je LangChain4j?](../../../00-quick-start)
- [Ovisnosti LangChain4j](../../../00-quick-start)
- [Preduvjeti](../../../00-quick-start)
- [Postavljanje](../../../00-quick-start)
  - [1. Nabavite svoj GitHub token](../../../00-quick-start)
  - [2. Postavite svoj token](../../../00-quick-start)
- [Pokrenite primjere](../../../00-quick-start)
  - [1. Osnovni chat](../../../00-quick-start)
  - [2. Uzorci prompta](../../../00-quick-start)
  - [3. Pozivanje funkcija](../../../00-quick-start)
  - [4. Pitanja i odgovori o dokumentu (RAG)](../../../00-quick-start)
- [Što svaki primjer prikazuje](../../../00-quick-start)
- [Sljedeći koraci](../../../00-quick-start)
- [Rješavanje problema](../../../00-quick-start)

## Uvod

Ovaj brzi početak je namijenjen da vas što brže uvede u rad s LangChain4j. Pokriva apsolutne osnove izgradnje AI aplikacija s LangChain4j i GitHub modelima. U sljedećim modulima koristit ćete Azure OpenAI s LangChain4j za izgradnju naprednijih aplikacija.

## Što je LangChain4j?

LangChain4j je Java biblioteka koja pojednostavljuje izgradnju AI-pokretanih aplikacija. Umjesto da se bavite HTTP klijentima i parsiranjem JSON-a, radite s čistim Java API-jima.

"Chain" u LangChain odnosi se na povezivanje više komponenti - možete povezati prompt s modelom, zatim s parserom, ili povezati više AI poziva gdje jedan izlaz služi kao ulaz za sljedeći. Ovaj brzi početak fokusira se na osnove prije nego što istražimo složenije lance.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1.hr.png" alt="LangChain4j Chaining Concept" width="800"/>

*Povezivanje komponenti u LangChain4j - gradivni blokovi se povezuju za stvaranje moćnih AI tijekova rada*

Koristit ćemo tri osnovne komponente:

**ChatLanguageModel** - sučelje za interakciju s AI modelima. Pozovite `model.chat("prompt")` i dobijete odgovor kao string. Koristimo `OpenAiOfficialChatModel` koji radi s OpenAI-kompatibilnim endpointima poput GitHub modela.

**AiServices** - stvara tip-sigurna AI sučelja za usluge. Definirajte metode, označite ih s `@Tool`, a LangChain4j upravlja orkestracijom. AI automatski poziva vaše Java metode kad je potrebno.

**MessageWindowChatMemory** - održava povijest razgovora. Bez toga, svaki zahtjev je neovisan. S njim, AI pamti prethodne poruke i održava kontekst kroz više okretaja.

<img src="../../../translated_images/architecture.eedc993a1c576839.hr.png" alt="LangChain4j Architecture" width="800"/>

*Arhitektura LangChain4j - osnovne komponente koje zajedno pokreću vaše AI aplikacije*

## Ovisnosti LangChain4j

Ovaj brzi početak koristi dvije Maven ovisnosti u [`pom.xml`](../../../00-quick-start/pom.xml):

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

Modul `langchain4j-open-ai-official` pruža klasu `OpenAiOfficialChatModel` koja se povezuje na OpenAI-kompatibilne API-je. GitHub modeli koriste isti API format, pa nije potreban poseban adapter - samo usmjerite osnovni URL na `https://models.github.ai/inference`.

## Preduvjeti

**Koristite li Dev Container?** Java i Maven su već instalirani. Trebate samo GitHub Personal Access Token.

**Lokalni razvoj:**
- Java 21+, Maven 3.9+
- GitHub Personal Access Token (upute dolje)

> **Napomena:** Ovaj modul koristi `gpt-4.1-nano` iz GitHub modela. Nemojte mijenjati ime modela u kodu - konfigurirano je za rad s dostupnim GitHub modelima.

## Postavljanje

### 1. Nabavite svoj GitHub token

1. Idite na [GitHub Settings → Personal Access Tokens](https://github.com/settings/personal-access-tokens)
2. Kliknite "Generate new token"
3. Postavite opisni naziv (npr. "LangChain4j Demo")
4. Postavite rok trajanja (preporučeno 7 dana)
5. Pod "Account permissions" pronađite "Models" i postavite na "Read-only"
6. Kliknite "Generate token"
7. Kopirajte i spremite token - više ga nećete vidjeti

### 2. Postavite svoj token

**Opcija 1: Korištenje VS Code (preporučeno)**

Ako koristite VS Code, dodajte svoj token u `.env` datoteku u korijenu projekta:

Ako `.env` datoteka ne postoji, kopirajte `.env.example` u `.env` ili kreirajte novu `.env` datoteku u korijenu projekta.

**Primjer `.env` datoteke:**
```bash
# U /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Zatim jednostavno desnim klikom na bilo koju demo datoteku (npr. `BasicChatDemo.java`) u Exploreru odaberite **"Run Java"** ili koristite konfiguracije za pokretanje iz Run and Debug panela.

**Opcija 2: Korištenje terminala**

Postavite token kao varijablu okoline:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Pokrenite primjere

**Korištenje VS Code:** Jednostavno desni klik na bilo koju demo datoteku u Exploreru i odaberite **"Run Java"**, ili koristite konfiguracije za pokretanje iz Run and Debug panela (prvo se pobrinite da ste dodali token u `.env` datoteku).

**Korištenje Mavena:** Alternativno, možete pokrenuti iz naredbenog retka:

### 1. Osnovni chat

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Uzorci prompta

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Prikazuje zero-shot, few-shot, chain-of-thought i role-based promptove.

### 3. Pozivanje funkcija

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI automatski poziva vaše Java metode kad je potrebno.

### 4. Pitanja i odgovori o dokumentu (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Postavljajte pitanja o sadržaju u `document.txt`.

## Što svaki primjer prikazuje

**Osnovni chat** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Počnite ovdje da vidite LangChain4j u najjednostavnijem obliku. Kreirat ćete `OpenAiOfficialChatModel`, poslati prompt s `.chat()`, i dobiti odgovor. Ovo pokazuje temelj: kako inicijalizirati modele s prilagođenim endpointima i API ključevima. Kad shvatite ovaj obrazac, sve ostalo se na njega nadograđuje.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Isprobajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorite [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) i pitajte:
> - "Kako bih prešao s GitHub modela na Azure OpenAI u ovom kodu?"
> - "Koje druge parametre mogu konfigurirati u OpenAiOfficialChatModel.builder()?"
> - "Kako dodati streaming odgovore umjesto čekanja na kompletan odgovor?"

**Prompt Engineering** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Sad kad znate kako razgovarati s modelom, istražimo što mu govorite. Ovaj demo koristi istu postavku modela, ali prikazuje četiri različita uzorka prompta. Isprobajte zero-shot promptove za izravne upute, few-shot promptove koji uče iz primjera, chain-of-thought promptove koji otkrivaju korake razmišljanja, i role-based promptove koji postavljaju kontekst. Vidjet ćete kako isti model daje drastično različite rezultate ovisno o načinu na koji oblikujete zahtjev.

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

> **🤖 Isprobajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorite [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) i pitajte:
> - "Koja je razlika između zero-shot i few-shot promptanja, i kada koristiti koji?"
> - "Kako parametar temperature utječe na odgovore modela?"
> - "Koje su tehnike za sprječavanje prompt injection napada u produkciji?"
> - "Kako mogu kreirati ponovno upotrebljive PromptTemplate objekte za uobičajene uzorke?"

**Integracija alata** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Ovdje LangChain4j postaje moćan. Koristit ćete `AiServices` za stvaranje AI asistenta koji može pozivati vaše Java metode. Samo označite metode s `@Tool("opis")` i LangChain4j se brine za ostalo - AI automatski odlučuje kada koristiti koji alat na temelju korisničkih zahtjeva. Ovo demonstrira pozivanje funkcija, ključnu tehniku za izgradnju AI-ja koji može poduzimati radnje, a ne samo odgovarati na pitanja.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Isprobajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorite [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) i pitajte:
> - "Kako radi @Tool anotacija i što LangChain4j radi s njom iza scene?"
> - "Može li AI pozvati više alata u nizu za rješavanje složenih problema?"
> - "Što se događa ako alat baci iznimku - kako trebam rukovati greškama?"
> - "Kako bih integrirao pravi API umjesto ovog primjera kalkulatora?"

**Pitanja i odgovori o dokumentu (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Ovdje vidite temelj RAG-a (retrieval-augmented generation). Umjesto da se oslanjate na podatke za treniranje modela, učitavate sadržaj iz [`document.txt`](../../../00-quick-start/document.txt) i uključujete ga u prompt. AI odgovara na temelju vašeg dokumenta, a ne općeg znanja. Ovo je prvi korak prema izgradnji sustava koji mogu raditi s vašim vlastitim podacima.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Napomena:** Ovaj jednostavan pristup učitava cijeli dokument u prompt. Za velike datoteke (>10KB) premašit ćete limite konteksta. Modul 03 pokriva dijeljenje na dijelove i vektorsko pretraživanje za produkcijske RAG sustave.

> **🤖 Isprobajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorite [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) i pitajte:
> - "Kako RAG sprječava AI halucinacije u usporedbi s korištenjem podataka za treniranje modela?"
> - "Koja je razlika između ovog jednostavnog pristupa i korištenja vektorskih ugradnji za dohvat?"
> - "Kako bih skalirao ovo za rad s više dokumenata ili većim bazama znanja?"
> - "Koje su najbolje prakse za strukturiranje prompta da AI koristi samo dani kontekst?"

## Rješavanje problema

Primjeri uključuju `.logRequests(true)` i `.logResponses(true)` za prikaz API poziva u konzoli. Ovo pomaže u rješavanju problema s autentifikacijom, ograničenjima brzine ili neočekivanim odgovorima. Uklonite ove zastavice u produkciji da smanjite buku u zapisima.

## Sljedeći koraci

**Sljedeći modul:** [01-introduction - Početak rada s LangChain4j i gpt-5 na Azureu](../01-introduction/README.md)

---

**Navigacija:** [← Natrag na glavni](../README.md) | [Sljedeće: Modul 01 - Uvod →](../01-introduction/README.md)

---

## Rješavanje problema

### Prvo Maven buildanje

**Problem:** Početni `mvn clean compile` ili `mvn package` traje dugo (10-15 minuta)

**Uzrok:** Maven mora preuzeti sve ovisnosti projekta (Spring Boot, LangChain4j biblioteke, Azure SDK-ove itd.) pri prvom buildanju.

**Rješenje:** Ovo je normalno ponašanje. Sljedeća buildanja bit će znatno brža jer se ovisnosti keširaju lokalno. Vrijeme preuzimanja ovisi o brzini vaše mreže.

### Sintaksa Maven naredbi u PowerShellu

**Problem:** Maven naredbe ne uspijevaju s greškom `Unknown lifecycle phase ".mainClass=..."`

**Uzrok:** PowerShell tumači `=` kao operator dodjele varijable, što kvari sintaksu Maven svojstava.

**Rješenje:** Koristite operator za zaustavljanje parsiranja `--%` prije Maven naredbe:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Operator `--%` govori PowerShellu da sve preostale argumente proslijedi doslovno Mavenu bez tumačenja.

### Prikaz emojija u Windows PowerShellu

**Problem:** AI odgovori prikazuju nečitljive znakove (npr. `????` ili `â??`) umjesto emojija u PowerShellu

**Uzrok:** Zadano kodiranje PowerShella ne podržava UTF-8 emojije

**Rješenje:** Pokrenite ovu naredbu prije pokretanja Java aplikacija:
```cmd
chcp 65001
```

Ovo forsira UTF-8 kodiranje u terminalu. Alternativno, koristite Windows Terminal koji ima bolju podršku za Unicode.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Odricanje od odgovornosti**:
Ovaj dokument je preveden korištenjem AI usluge za prevođenje [Co-op Translator](https://github.com/Azure/co-op-translator). Iako nastojimo postići točnost, imajte na umu da automatski prijevodi mogu sadržavati pogreške ili netočnosti. Izvorni dokument na izvornom jeziku treba smatrati autoritativnim izvorom. Za kritične informacije preporučuje se profesionalni ljudski prijevod. Ne snosimo odgovornost za bilo kakva nesporazuma ili pogrešna tumačenja koja proizlaze iz korištenja ovog prijevoda.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->