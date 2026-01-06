<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "22b5d7c8d7585325e38b37fd29eafe25",
  "translation_date": "2026-01-06T01:21:53+00:00",
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
  - [2. Obrasci prompta](../../../00-quick-start)
  - [3. Pozivanje funkcija](../../../00-quick-start)
  - [4. Pitanja i odgovori na dokumente (RAG)](../../../00-quick-start)
  - [5. Odgovorni AI](../../../00-quick-start)
- [Što svaki primjer prikazuje](../../../00-quick-start)
- [Sljedeći koraci](../../../00-quick-start)
- [Rješavanje problema](../../../00-quick-start)

## Uvod

Ovaj brzi početak služi da vas što brže uvede u rad s LangChain4j. Obuhvaća apsolutne osnove izgradnje AI aplikacija koristeći LangChain4j i GitHub modele. U narednim modulima koristit ćete Azure OpenAI s LangChain4j za izgradnju naprednijih aplikacija.

## Što je LangChain4j?

LangChain4j je Java biblioteka koja pojednostavljuje izradu aplikacija pogonjenih AI tehnologijom. Umjesto da se bavite HTTP klijentima i parsiranjem JSON-a, radite s čistim Java API-jima.

"Lanac" u LangChain odnosi se na povezivanje više komponenti – možete spojiti prompt s modelom pa s parserom, ili povezati više AI poziva gdje jedan izlaz ide kao ulaz sljedećem. Ovaj brzi početak se fokusira na osnove prije nego što krenete u složenije lance.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1.hr.png" alt="LangChain4j Chaining Concept" width="800"/>

*Povezivanje komponenti u LangChain4j - gradivni blokovi povezuju se kako bi stvorili moćne AI tokove rada*

Koristit ćemo tri osnovne komponente:

**ChatLanguageModel** - sučelje za interakciju s AI modelima. Pozivate `model.chat("prompt")` i dobivate odgovor kao string. Koristimo `OpenAiOfficialChatModel` koji radi s OpenAI-kompatibilnim krajnjim točkama poput GitHub modela.

**AiServices** - stvara tip-sigurna AI sučelja. Definirajte metode, označite ih s `@Tool`, a LangChain4j upravlja orkestracijom. AI automatski poziva vaše Java metode kad je potrebno.

**MessageWindowChatMemory** - održava povijest razgovora. Bez ovoga, svaki zahtjev je neovisan. S njim, AI pamti prethodne poruke i održava kontekst kroz više koraka.

<img src="../../../translated_images/architecture.eedc993a1c576839.hr.png" alt="LangChain4j Architecture" width="800"/>

*Arhitektura LangChain4j – osnovne komponente koje zajedno pokreću vaše AI aplikacije*

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

Modul `langchain4j-open-ai-official` pruža klasu `OpenAiOfficialChatModel` koja se povezuje s OpenAI-kompatibilnim API-jima. GitHub modeli koriste isti format API-ja, pa nije potreban poseban adapter – samo postavite bazni URL na `https://models.github.ai/inference`.

## Preduvjeti

**Koristite li Dev Container?** Java i Maven su već instalirani. Trebate samo GitHub Personal Access Token.

**Lokalni razvoj:**
- Java 21+, Maven 3.9+
- GitHub Personal Access Token (upute dolje)

> **Napomena:** Ovaj modul koristi `gpt-4.1-nano` iz GitHub modela. Nemojte mijenjati naziv modela u kodu – konfiguriran je za rad s dostupnim GitHub modelima.

## Postavljanje

### 1. Nabavite svoj GitHub token

1. Idite na [GitHub postavke → Personal Access Tokens](https://github.com/settings/personal-access-tokens)
2. Kliknite "Generate new token"
3. Postavite opisni naziv (npr., "LangChain4j Demo")
4. Postavite rok trajanja (preporučeno 7 dana)
5. Pod "Account permissions" pronađite "Models" i postavite na "Read-only"
6. Kliknite "Generate token"
7. Kopirajte i sačuvajte token – više ga nećete vidjeti

### 2. Postavite svoj token

**Opcija 1: Koristeći VS Code (Preporučeno)**

Ako koristite VS Code, dodajte svoj token u `.env` datoteku u korijenu projekta:

Ako datoteka `.env` ne postoji, kopirajte `.env.example` u `.env` ili napravite novu `.env` datoteku u korijenu projekta.

**Primjer `.env` datoteke:**
```bash
# U /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Zatim jednostavno desni klik na bilo koju demo datoteku (npr. `BasicChatDemo.java`) u Exploreru i odaberite **"Run Java"** ili koristite konfiguracije za pokretanje u Run and Debug panelu.

**Opcija 2: Korištenjem terminala**

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

**Koristeći VS Code:** Jednostavno desni klik na bilo koju demo datoteku u Exploreru i odaberite **"Run Java"**, ili koristite konfiguracije za pokretanje iz Run and Debug panela (prvo se pobrinite da ste dodali token u `.env` datoteku).

**Koristeći Maven:** Alternativno, možete pokrenuti iz naredbenog retka:

### 1. Osnovni chat

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Obrasci prompta

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Prikazuje zero-shot, few-shot, chain-of-thought i role-based promptanje.

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

### 4. Pitanja i odgovori na dokumente (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Postavljajte pitanja o sadržaju u `document.txt`.

### 5. Odgovorni AI

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

Pogledajte kako AI sigurnosni filteri blokiraju štetni sadržaj.

## Što svaki primjer prikazuje

**Osnovni chat** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Počnite ovdje da vidite LangChain4j u najjednostavnijem obliku. Kreirat ćete `OpenAiOfficialChatModel`, poslati prompt s `.chat()`, i dobiti odgovor. Ovo prikazuje temelj: kako inicijalizirati modele s prilagođenim krajnjim točkama i API ključevima. Kad razumijete ovaj obrazac, sve ostalo se nadograđuje na njega.

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
> - "Kako mogu dodati streaming odgovore umjesto čekanja cjelovitog odgovora?"

**Inženjering prompta** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Sad kad znate kako razgovarati s modelom, pogledajmo što mu kažete. Ovaj demo koristi istu postavku modela, ali prikazuje četiri različita obrasca promptanja. Isprobajte zero-shot promptove za direktne upute, few-shot promptove koji uče iz primjera, chain-of-thought promptove koji otkrivaju korake razmišljanja i role-based promptove koji postavljaju kontekst. Vidjet ćete kako isti model daje dramatično različite rezultate ovisno o načinu na koji postavite zahtjev.

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
> - "Koja je razlika između zero-shot i few-shot promptanja i kada koristiti koji?"
> - "Kako parametar temperature utječe na odgovore modela?"
> - "Koje tehnike postoje za sprječavanje prompt injection napada u produkciji?"
> - "Kako mogu kreirati ponovno upotrebljive PromptTemplate objekte za često korištene obrasce?"

**Integracija alata** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Ovdje LangChain4j postaje moćan. Koristite `AiServices` da kreirate AI asistenta koji može pozivati vaše Java metode. Samo označite metode s `@Tool("opis")`, a LangChain4j upravlja ostatkom – AI automatski odlučuje kada koristiti svaki alat prema upitima korisnika. Ovo demonstrira pozivanje funkcija, ključnu tehniku za izgradnju AI koji može izvršavati akcije, a ne samo odgovarati na pitanja.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Isprobajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorite [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) i pitajte:
> - "Kako radi @Tool anotacija i što LangChain4j radi s njom u pozadini?"
> - "Može li AI pozivati više alata u nizu za rješavanje složenih problema?"
> - "Što se događa ako alat baci iznimku – kako trebam rukovati greškama?"
> - "Kako bih integrirao pravi API umjesto ovog primjera kalkulatora?"

**Pitanja i odgovori na dokumente (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Ovdje vidite temelj RAG (retrieval-augmented generation). Umjesto da se oslanjate na podatke iz modelovog treninga, učitavate sadržaj iz [`document.txt`](../../../00-quick-start/document.txt) i uključujete ga u prompt. AI odgovara na temelju vašeg dokumenta, a ne općeg znanja. Ovo je prvi korak prema izgradnji sustava koji mogu raditi s vašim vlastitim podacima.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Napomena:** Ovaj jednostavan pristup učitava cijeli dokument u prompt. Za velike datoteke (>10KB) premašit ćete limit konteksta. Modul 03 obrađuje dijeljenje na segmente i vektorsku pretragu za produkcijske RAG sustave.

> **🤖 Isprobajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorite [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) i pitajte:
> - "Kako RAG sprječava AI halucinacije u usporedbi s korištenjem podataka iz treninga modela?"
> - "Koja je razlika između ovog jednostavnog pristupa i korištenja vektorskih uvlaka za dohvat?"
> - "Kako bih skalirao ovo za rukovanje više dokumenata ili većim bazama znanja?"
> - "Koje su najbolje prakse za strukturiranje prompta da AI koristi samo dani kontekst?"

**Odgovorni AI** - [ResponsibleAIDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)

Izgradite sigurnost AI-a s višeslojnim obrambenim mehanizmima. Ovaj demo prikazuje dva sloja zaštite koja rade zajedno:

**Dio 1: LangChain4j ulazna pravila (Input Guardrails)** – Blokiraju opasne promptove prije nego što dođu do LLM-a. Kreirajte vlastita pravila koja provjeravaju zabranjene ključne riječi ili obrasce. Ovo se izvodi u vašem kodu, pa je brzo i besplatno.

```java
class DangerousContentGuardrail implements InputGuardrail {
    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        String text = userMessage.singleText().toLowerCase();
        if (text.contains("explosives")) {
            return fatal("Blocked: contains prohibited keyword");
        }
        return success();
    }
}
```

**Dio 2: Filteri sigurnosti pružatelja usluga** – GitHub modeli imaju ugrađene filtre koji hvataju što vaša pravila mogu propustiti. Vidjet ćete čvrste blokade (HTTP 400 greške) za ozbiljne prekršaje i mekane odbijanja kada AI ljubazno odbije.

> **🤖 Isprobajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorite [`ResponsibleAIDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java) i pitajte:
> - "Što je InputGuardrail i kako napraviti svoje vlastito?"
> - "Koja je razlika između čvrste blokade i mekanog odbijanja?"
> - "Zašto koristiti istovremeno guardrailse i filtere pružatelja usluge?"

## Sljedeći koraci

**Sljedeći modul:** [01-introduction - Početak rada s LangChain4j i gpt-5 na Azure](../01-introduction/README.md)

---

**Navigacija:** [← Nazad na početak](../README.md) | [Dalje: Modul 01 - Uvod →](../01-introduction/README.md)

---

## Rješavanje problema

### Prvo Maven buildanje

**Problem:** Prvi `mvn clean compile` ili `mvn package` traje dugo (10-15 minuta)

**Uzrok:** Maven treba preuzeti sve ovisnosti projekta (Spring Boot, LangChain4j biblioteke, Azure SDK-e itd.) pri prvom buildanju.

**Rješenje:** Ovo je normalno. Sljedeća buildanja bit će znatno brža jer su ovisnosti keširane lokalno. Vrijeme preuzimanja ovisi o brzini vaše mreže.

### Sintaksa Maven naredbi u PowerShellu

**Problem:** Maven naredbe ne uspijevaju s greškom `Unknown lifecycle phase ".mainClass=..."`

**Uzrok:** PowerShell tumači `=` kao operator dodjele varijable, što razbija sintaksu Maven svojstava.
**Rješenje**: Koristite operator za zaustavljanje parsiranja `--%` prije Maven naredbe:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Operator `--%` govori PowerShell-u da sve preostale argumente doslovno proslijedi Mavenu bez interpretacije.

### Prikaz emotikona u Windows PowerShellu

**Problem**: AI odgovori prikazuju besmislene znakove (npr. `????` ili `â??`) umjesto emotikona u PowerShellu

**Uzrok**: Zadano kodiranje PowerShell-a ne podržava UTF-8 emotikone

**Rješenje**: Pokrenite ovu naredbu prije izvođenja Java aplikacija:
```cmd
chcp 65001
```

Ovo forsira UTF-8 kodiranje u terminalu. Alternativno, koristite Windows Terminal koji ima bolju podršku za Unicode.

### Otklanjanje pogrešaka poziva API-ja

**Problem**: Pogreške autentifikacije, ograničenja broja poziva ili neočekivani odgovori AI modela

**Rješenje**: Primjeri uključuju `.logRequests(true)` i `.logResponses(true)` za prikaz poziva API-ja u konzoli. To pomaže u otkrivanju pogrešaka autentifikacije, ograničenja broja poziva ili neočekivanih odgovora. Uklonite ove postavke u produkciji kako biste smanjili buku u zapisima.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Izjava o odricanju odgovornosti**:
Ovaj je dokument preveden pomoću AI usluge za prevođenje [Co-op Translator](https://github.com/Azure/co-op-translator). Iako nastojimo postići točnost, imajte na umu da automatski prijevodi mogu sadržavati pogreške ili netočnosti. Izvorni dokument na izvornom jeziku smatra se službenim i jedinim mjerodavnim izvorom. Za važne informacije preporučuje se profesionalni ljudski prijevod. Ne preuzimamo odgovornost za bilo kakve nesporazume ili pogrešna tumačenja koja proizlaze iz korištenja ovog prijevoda.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->