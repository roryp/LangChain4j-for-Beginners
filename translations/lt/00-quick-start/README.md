<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T15:27:03+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "lt"
}
-->
# Modulis 00: Greitas pradėjimas

## Turinys

- [Įvadas](../../../00-quick-start)
- [Kas yra LangChain4j?](../../../00-quick-start)
- [LangChain4j priklausomybės](../../../00-quick-start)
- [Reikalavimai](../../../00-quick-start)
- [Nustatymas](../../../00-quick-start)
  - [1. Gaukite savo GitHub žetoną](../../../00-quick-start)
  - [2. Nustatykite savo žetoną](../../../00-quick-start)
- [Paleiskite pavyzdžius](../../../00-quick-start)
  - [1. Pagrindinis pokalbis](../../../00-quick-start)
  - [2. Užklausų šablonai](../../../00-quick-start)
  - [3. Funkcijų kvietimas](../../../00-quick-start)
  - [4. Dokumentų klausimai ir atsakymai (RAG)](../../../00-quick-start)
- [Ką rodo kiekvienas pavyzdys](../../../00-quick-start)
- [Kiti žingsniai](../../../00-quick-start)
- [Trikčių šalinimas](../../../00-quick-start)

## Įvadas

Šis greitas pradėjimas skirtas kuo greičiau pradėti naudotis LangChain4j. Jame apžvelgiami absoliutūs pagrindai, kaip kurti DI programas su LangChain4j ir GitHub modeliais. Kitose moduliuose naudosite Azure OpenAI su LangChain4j, kad kurtumėte pažangesnes programas.

## Kas yra LangChain4j?

LangChain4j yra Java biblioteka, kuri supaprastina DI pagrįstų programų kūrimą. Vietoje to, kad dirbtumėte su HTTP klientais ir JSON analizavimu, jūs naudojate švarias Java API.

„Chain“ LangChain pavadinime reiškia kelių komponentų sujungimą – galite sujungti užklausą su modeliu ir su parseriu, arba sujungti kelis DI kvietimus, kai vieno išvestis tampa kito įvestimi. Šis greitas pradėjimas sutelktas į pagrindus prieš pereinant prie sudėtingesnių grandinių.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1.lt.png" alt="LangChain4j Chaining Concept" width="800"/>

*Komponentų sujungimas LangChain4j – statybiniai blokai jungiasi, kad sukurtų galingus DI darbo srautus*

Naudosime tris pagrindinius komponentus:

**ChatLanguageModel** – sąsaja DI modelio sąveikoms. Iškvieskite `model.chat("prompt")` ir gaukite atsakymo eilutę. Naudojame `OpenAiOfficialChatModel`, kuris veikia su OpenAI suderinamais galiniais taškais, tokiais kaip GitHub modeliai.

**AiServices** – sukuria tipui saugias DI paslaugų sąsajas. Apibrėžkite metodus, pažymėkite juos `@Tool`, o LangChain4j tvarko orkestraciją. DI automatiškai kviečia jūsų Java metodus, kai reikia.

**MessageWindowChatMemory** – palaiko pokalbio istoriją. Be to kiekvienas užklausimas yra nepriklausomas. Su juo DI prisimena ankstesnius pranešimus ir palaiko kontekstą per kelis pokalbio raundus.

<img src="../../../translated_images/architecture.eedc993a1c576839.lt.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j architektūra – pagrindiniai komponentai dirba kartu, kad maitintų jūsų DI programas*

## LangChain4j priklausomybės

Šis greitas pradėjimas naudoja dvi Maven priklausomybes [`pom.xml`](../../../00-quick-start/pom.xml):

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

`langchain4j-open-ai-official` modulis suteikia `OpenAiOfficialChatModel` klasę, kuri jungiasi prie OpenAI suderinamų API. GitHub modeliai naudoja tą patį API formatą, todėl nereikia specialaus adapterio – tiesiog nurodykite bazinį URL `https://models.github.ai/inference`.

## Reikalavimai

**Naudojate Dev Container?** Java ir Maven jau įdiegti. Jums reikia tik GitHub asmeninio prieigos žetono.

**Vietinė plėtra:**
- Java 21+, Maven 3.9+
- GitHub asmeninis prieigos žetonas (instrukcijos žemiau)

> **Pastaba:** Šis modulis naudoja `gpt-4.1-nano` iš GitHub modelių. Nekoreguokite modelio pavadinimo kode – jis sukonfigūruotas dirbti su GitHub prieinamais modeliais.

## Nustatymas

### 1. Gaukite savo GitHub žetoną

1. Eikite į [GitHub nustatymai → Asmeniniai prieigos žetonai](https://github.com/settings/personal-access-tokens)
2. Spustelėkite „Generate new token“
3. Nustatykite aprašomą pavadinimą (pvz., „LangChain4j Demo“)
4. Nustatykite galiojimo laiką (rekomenduojama 7 dienos)
5. Skiltyje „Account permissions“ raskite „Models“ ir nustatykite „Read-only“
6. Spustelėkite „Generate token“
7. Nukopijuokite ir išsaugokite žetoną – jo daugiau nematysite

### 2. Nustatykite savo žetoną

**1 variantas: Naudojant VS Code (rekomenduojama)**

Jei naudojate VS Code, pridėkite savo žetoną į `.env` failą projekto šaknyje:

Jei `.env` failas neegzistuoja, nukopijuokite `.env.example` į `.env` arba sukurkite naują `.env` failą projekto šaknyje.

**Pavyzdinis `.env` failas:**
```bash
# Faile /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Tada galite paprasčiausiai dešiniuoju pelės mygtuku spustelėti bet kurį demonstracinį failą (pvz., `BasicChatDemo.java`) Explorer lange ir pasirinkti **„Run Java“** arba naudoti paleidimo konfigūracijas Run and Debug skydelyje.

**2 variantas: Naudojant terminalą**

Nustatykite žetoną kaip aplinkos kintamąjį:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Paleiskite pavyzdžius

**Naudojant VS Code:** Tiesiog dešiniuoju pelės mygtuku spustelėkite bet kurį demonstracinį failą Explorer lange ir pasirinkite **„Run Java“**, arba naudokite paleidimo konfigūracijas Run and Debug skydelyje (įsitikinkite, kad pirmiausia pridėjote žetoną į `.env` failą).

**Naudojant Maven:** Taip pat galite paleisti iš komandinės eilutės:

### 1. Pagrindinis pokalbis

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Užklausų šablonai

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Rodo zero-shot, few-shot, chain-of-thought ir role-based užklausas.

### 3. Funkcijų kvietimas

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

DI automatiškai kviečia jūsų Java metodus, kai reikia.

### 4. Dokumentų klausimai ir atsakymai (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Užduokite klausimus apie `document.txt` turinį.

## Ką rodo kiekvienas pavyzdys

**Pagrindinis pokalbis** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Pradėkite čia, kad pamatytumėte LangChain4j paprastumą. Sukursite `OpenAiOfficialChatModel`, išsiųsite užklausą su `.chat()` ir gausite atsakymą. Tai demonstruoja pagrindus: kaip inicializuoti modelius su pasirinktiniais galiniais taškais ir API raktus. Kai suprasite šį modelį, visa kita bus paremta juo.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) Chat:** Atidarykite [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) ir paklauskite:
> - „Kaip šiame kode pereiti nuo GitHub modelių prie Azure OpenAI?“
> - „Kokius kitus parametrus galiu konfigūruoti OpenAiOfficialChatModel.builder()?“
> - „Kaip pridėti srautinį atsakymą vietoje laukimo pilno atsakymo?“

**Užklausų inžinerija** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Dabar, kai žinote, kaip kalbėtis su modeliu, pažvelkime, ką jam sakote. Ši demonstracija naudoja tą patį modelio nustatymą, bet rodo keturis skirtingus užklausų šablonus. Išbandykite zero-shot užklausas tiesioginėms instrukcijoms, few-shot užklausas, kurios mokosi iš pavyzdžių, chain-of-thought užklausas, kurios atskleidžia mąstymo žingsnius, ir role-based užklausas, kurios nustato kontekstą. Pamatysite, kaip tas pats modelis duoda labai skirtingus rezultatus, priklausomai nuo to, kaip formuluojate užklausą.

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

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) Chat:** Atidarykite [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) ir paklauskite:
> - „Kuo skiriasi zero-shot ir few-shot užklausos, ir kada naudoti kiekvieną?“
> - „Kaip temperatūros parametras veikia modelio atsakymus?“
> - „Kokios yra technikos, kad būtų išvengta užklausų injekcijos atakų gamyboje?“
> - „Kaip sukurti pakartotinai naudojamus PromptTemplate objektus dažnai naudojamiems šablonams?“

**Įrankių integracija** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Čia LangChain4j tampa galingas. Naudosite `AiServices`, kad sukurtumėte DI asistentą, kuris gali kviesti jūsų Java metodus. Tiesiog pažymėkite metodus `@Tool("aprašymas")`, o LangChain4j pasirūpina likusiu – DI automatiškai nusprendžia, kada naudoti kiekvieną įrankį pagal vartotojo užklausą. Tai demonstruoja funkcijų kvietimą, svarbią techniką kuriant DI, kuris gali imtis veiksmų, o ne tik atsakyti į klausimus.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) Chat:** Atidarykite [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) ir paklauskite:
> - „Kaip veikia @Tool anotacija ir ką LangChain4j daro su ja užkulisiuose?“
> - „Ar DI gali kviesti kelis įrankius iš eilės, kad išspręstų sudėtingas problemas?“
> - „Kas nutinka, jei įrankis meta išimtį – kaip turėčiau tvarkyti klaidas?“
> - „Kaip integruočiau tikrą API vietoje šio skaičiuotuvo pavyzdžio?“

**Dokumentų klausimai ir atsakymai (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Čia pamatysite RAG (retrieval-augmented generation) pagrindus. Vietoje to, kad pasikliautumėte modelio mokymo duomenimis, įkelsite turinį iš [`document.txt`](../../../00-quick-start/document.txt) ir įtrauksite jį į užklausą. DI atsako remdamasis jūsų dokumentu, o ne bendromis žiniomis. Tai pirmas žingsnis link sistemų, kurios gali dirbti su jūsų duomenimis.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Pastaba:** Šis paprastas metodas įkelia visą dokumentą į užklausą. Dideliems failams (>10KB) viršysite konteksto ribas. Modulis 03 apima dalijimą į dalis ir vektorinę paiešką gamybos RAG sistemoms.

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) Chat:** Atidarykite [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) ir paklauskite:
> - „Kaip RAG apsaugo nuo DI haliucinacijų, palyginti su modelio mokymo duomenimis?“
> - „Kuo skiriasi šis paprastas metodas nuo vektorinių įterpimų naudojimo paieškai?“
> - „Kaip išplėsti šią sistemą, kad apdorotų kelis dokumentus ar didesnes žinių bazes?“
> - „Kokios yra geriausios praktikos, kaip struktūruoti užklausą, kad DI naudotų tik pateiktą kontekstą?“

## Derinimas

Pavyzdžiuose yra `.logRequests(true)` ir `.logResponses(true)`, kad parodytų API kvietimus konsolėje. Tai padeda spręsti autentifikacijos klaidas, kvotų ribojimus ar netikėtus atsakymus. Pašalinkite šiuos žymenis gamyboje, kad sumažintumėte žurnalų triukšmą.

## Kiti žingsniai

**Kitas modulis:** [01-introduction - Pradžia su LangChain4j ir gpt-5 Azure](../01-introduction/README.md)

---

**Navigacija:** [← Atgal į pagrindinį](../README.md) | [Toliau: Modulis 01 - Įvadas →](../01-introduction/README.md)

---

## Trikčių šalinimas

### Pirmas Maven kūrimas

**Problema:** Pirmasis `mvn clean compile` arba `mvn package` užtrunka ilgai (10-15 minučių)

**Priežastis:** Maven pirmą kartą turi atsisiųsti visas projekto priklausomybes (Spring Boot, LangChain4j bibliotekas, Azure SDK ir kt.).

**Sprendimas:** Tai normalu. Vėlesni kūrimai bus daug greitesni, nes priklausomybės bus talpinamos vietoje. Atsisiuntimo laikas priklauso nuo jūsų tinklo greičio.

### PowerShell Maven komandos sintaksė

**Problema:** Maven komandos nepavyksta su klaida `Unknown lifecycle phase ".mainClass=..."`

**Priežastis:** PowerShell interpretuoja `=` kaip kintamojo priskyrimo operatorių, todėl Maven savybių sintaksė sulūžta.

**Sprendimas:** Naudokite sustabdymo analizės operatorių `--%` prieš Maven komandą:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Operatorius `--%` nurodo PowerShell perduoti visus likusius argumentus tiesiogiai Maven be interpretacijos.

### Windows PowerShell emocijų rodymas

**Problema:** DI atsakymai rodo šiukšles (pvz., `????` arba `â??`) vietoje emocijų PowerShell lange

**Priežastis:** PowerShell numatytoji koduotė nepalaiko UTF-8 emocijų

**Sprendimas:** Paleiskite šią komandą prieš vykdydami Java programas:
```cmd
chcp 65001
```

Tai priverčia terminalą naudoti UTF-8 koduotę. Alternatyviai naudokite Windows Terminal, kuris geriau palaiko Unicode.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Atsakomybės apribojimas**:
Šis dokumentas buvo išverstas naudojant dirbtinio intelekto vertimo paslaugą [Co-op Translator](https://github.com/Azure/co-op-translator). Nors siekiame tikslumo, prašome atkreipti dėmesį, kad automatiniai vertimai gali turėti klaidų ar netikslumų. Originalus dokumentas gimtąja kalba turėtų būti laikomas autoritetingu šaltiniu. Kritinei informacijai rekomenduojamas profesionalus žmogaus vertimas. Mes neatsakome už bet kokius nesusipratimus ar neteisingus aiškinimus, kilusius dėl šio vertimo naudojimo.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->