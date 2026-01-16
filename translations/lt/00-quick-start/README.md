<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "22b5d7c8d7585325e38b37fd29eafe25",
  "translation_date": "2026-01-06T01:47:05+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "lt"
}
-->
# Modulis 00: Greitas pradžios vadovas

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
  - [5. Atsakingas AI](../../../00-quick-start)
- [Ką rodo kiekvienas pavyzdys](../../../00-quick-start)
- [Tolimesni žingsniai](../../../00-quick-start)
- [Trikčių šalinimas](../../../00-quick-start)

## Įvadas

Šis greitojo pradžios vadovas skirtas kuo greičiau pradėti darbą su LangChain4j. Jame aprašomos pagrindinės AI programų kūrimo su LangChain4j ir GitHub modeliais sąvokos. Kitose moduliuose naudosite Azure OpenAI su LangChain4j, kad kurtumėte pažangesnes programas.

## Kas yra LangChain4j?

LangChain4j yra Java biblioteka, kuri supaprastina AI pagrindu sukurtų programų kūrimą. Vietoje to, kad tvarkytumėte HTTP klientus ir JSON analizę, dirbate su švariais Java API.

Terminas „chain“ LangChain požiūriu reiškia kelių komponentų sujungimą – galite susieti užklausą su modeliu ir su parseriu arba kelis AI kvietimus tarpusavyje, kur vieno rezultatas yra kito įvestis. Šis greitas pradžios vadovas susitelkia į pagrindus prieš pereinant prie sudėtingesnių grandinių.

<img src="../../../translated_images/lt/langchain-concept.ad1fe6cf063515e1.png" alt="LangChain4j Chaining Concept" width="800"/>

*Komponentų sujungimas LangChain4j – blokai jungiasi, kad sukurtų galingus AI darbo srautus*

Naudosime tris pagrindinius komponentus:

**ChatLanguageModel** – Sąsaja AI modelių sąveikai. Iškvieskite `model.chat("prompt")` ir gaukite atsakymą tekstu. Naudojame `OpenAiOfficialChatModel`, kuris veikia su OpenAI suderinamais API kaip GitHub Modeliai.

**AiServices** – Sukuria tipams saugias AI paslaugų sąsajas. Apibrėžkite metodus, pažymėkite juos `@Tool` ir LangChain4j tvarko orkestravimą. AI automatiškai kviečia jūsų Java metodus, kai reikia.

**MessageWindowChatMemory** – Laiko pokalbio istoriją. Be jos kiekvienas užklausimas yra nepriklausomas. Su ja AI prisimena ankstesnius pranešimus ir išlaiko kontekstą per kelis pakeitimus.

<img src="../../../translated_images/lt/architecture.eedc993a1c576839.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j architektūra – pagrindiniai komponentai veikia drauge, kad palaikytų jūsų AI programas*

## LangChain4j priklausomybės

Šis greitas pradžios vadovas naudoja dvi Maven priklausomybes [`pom.xml`](../../../00-quick-start/pom.xml):

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

`langchain4j-open-ai-official` modulis suteikia `OpenAiOfficialChatModel` klasę, kuri jungiasi prie OpenAI suderinamų API. GitHub Modeliai naudoja tą patį API formatą, todėl nereikia specialaus adapterio – tereikia nukreipti bazinį URL į `https://models.github.ai/inference`.

## Reikalavimai

**Naudojate Dev konteinerį?** Java ir Maven jau įdiegti. Jums reikia tik GitHub Asmeninio prieigos rakto.

**Vietinė plėtra:**
- Java 21+, Maven 3.9+
- GitHub Asmeninis prieigos raktas (žemiau pateiktos instrukcijos)

> **Pastaba:** Šiame modulyje naudojamas `gpt-4.1-nano` iš GitHub modelių. Nekoreguokite modelio pavadinimo kode – jis sukonfigūruotas veikti su GitHub turimais modeliais.

## Nustatymas

### 1. Gaukite savo GitHub žetoną

1. Eikite į [GitHub nustatymai → Asmeniniai prieigos raktai](https://github.com/settings/personal-access-tokens)
2. Spauskite „Generate new token“
3. Nustatykite aprašomą pavadinimą (pvz., "LangChain4j Demonstracija")
4. Nustatykite galiojimo laiką (rekomenduojama 7 dienos)
5. Skiltyje „Account permissions“ raskite „Models“ ir nustatykite į „Read-only“
6. Spauskite „Generate token“
7. Nukopijuokite ir išsaugokite savo žetoną – jo daugiau nebematysite

### 2. Nustatykite savo žetoną

**1 variantas: Naudojant VS Code (rekomenduojama)**

Jei naudojate VS Code, pridėkite žetoną į `.env` failą projekto šakniniame kataloge:

Jei `.env` failo nėra, nukopijuokite `.env.example` į `.env` arba sukurkite naują `.env` failą projekto šaknyje.

**Pavyzdinis `.env` failas:**
```bash
# Faile /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Tada tiesiog dešiniuoju pelės klavišu spustelėkite bet kurį demonstracinį failą (pvz. `BasicChatDemo.java`) Eksploratoriuje ir pasirinkite **"Run Java"** arba naudokite paleidimo konfigūracijas Run and Debug skiltyje.

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

**Naudojant VS Code:** Tiesiog dešiniuoju pelės klavišu spustelėkite bet kurį demonstracinį failą Eksploratoriuje ir pasirinkite **"Run Java"**, arba naudokite paleidimo konfigūracijas Run and Debug panelėje (įsitikinkite, kad pridėjote savo žetoną į `.env` failą).

**Naudojant Maven:** Alternatyviai galite paleisti iš komandų eilutės:

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

Rodo nulinio pavyzdžio, kelių pavyzdžių, grandinės mąstymo ir vaidmenimis paremtų užklausų pavyzdžius.

### 3. Funkcijų kvietimas

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI automatiškai kviečia jūsų Java metodus, kai reikia.

### 4. Dokumentų klausimai ir atsakymai (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Užduokite klausimus apie turinį faile `document.txt`.

### 5. Atsakingas AI

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

Pažiūrėkite, kaip AI saugumo filtrai blokuoja kenksmingą turinį.

## Ką rodo kiekvienas pavyzdys

**Pagrindinis pokalbis** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Pradėkite čia, kad pamatytumėte LangChain4j paprastumą. Sukursite `OpenAiOfficialChatModel`, išsiųsite užklausą su `.chat()` ir gausite atsakymą. Tai pademonstruoja pagrindus: kaip inicializuoti modelius su nestandartiniais galiniais taškais ir API raktais. Supratę šį modelį, viskas kita remiasi juo.

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
> - „Kaip šiuo kodu pereiti nuo GitHub modelių prie Azure OpenAI?“
> - „Kokius kitus parametrus galiu konfigūruoti OpenAiOfficialChatModel.builder()?“
> - „Kaip pridėti srautinį atsakymų gavimą vietoje laukimo, kol bus visas atsakymas?“

**Užklausų inžinerija** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Dabar, kai žinote, kaip bendrauti su modeliu, pažiūrėkime, ką jam sakote. Ši demonstracija naudoja tą patį modelio nustatymą, bet rodo keturis skirtingus užklausų šablonus. Išbandykite nulinio pavyzdžio užklausas tiesioms instrukcijoms, kelių pavyzdžių užklausas mokymuisi iš pavyzdžių, grandinės-mąstymo užklausas, kurios atskleidžia samprotavimo žingsnius, ir vaidmenimis paremtas užklausas, kurios nustato kontekstą. Pamatysite, kaip tas pats modelis duoda visiškai skirtingus rezultatus priklausomai nuo užklausos formavimo.

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
> - „Kuo skiriasi nulinio pavyzdžio ir kelių pavyzdžių užklausos, ir kada naudoti kurį?“
> - „Kaip temperatūros parametras veikia modelio atsakymus?“
> - „Kokios yra technikos, kad būtų išvengta užklausų injekcijos atakų gamyboje?“
> - „Kaip sukurti pakartotinai naudojamus PromptTemplate objektus bendriems šablonams?“

**Įrankių integracija** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Čia LangChain4j tampa galingas. Naudosite `AiServices` sukurti AI asistentą, kuris gali kviesti jūsų Java metodus. Tiesiog pažymėkite metodus `@Tool("aprašymas")` ir LangChain4j pasirūpina likusiu – AI automatiškai nusprendžia, kada naudoti kiekvieną įrankį, atsižvelgdamas į vartotojo užklausą. Tai demonstruoja funkcijų kvietimą, svarbią AI kūrimo techniką, leidžiančią AI imtis veiksmų, o ne tik atsakyti į klausimus.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) Chat:** Atidarykite [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) ir paklauskite:
> - „Kaip veikia @Tool anotacija ir ką LangChain4j su ja daro užkulisiuose?“
> - „Ar AI gali iš eilės naudoti kelis įrankius sprendžiant sudėtingas problemas?“
> - „Kas nutinka jei įrankis meta klaidą – kaip reikia tvarkyti klaidas?“
> - „Kaip integruočiau tikrą API vietoje šio skaičiuotuvo pavyzdžio?“

**Dokumentų klausimai ir atsakymai (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Čia pamatysite RAG (retrieval-augmented generation) pagrindus. Vietoje modelio mokymo duomenų naudojimo jūs įkelsite turinį iš [`document.txt`](../../../00-quick-start/document.txt) ir įtrauksite jį į užklausą. AI atsako remdamasis jūsų dokumentu, o ne bendromis žiniomis. Tai pirmas žingsnis link sistemų, kurios gali dirbti su jūsų pačių duomenimis.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Pastaba:** Šis paprastas metodas įkelia visą dokumentą į užklausą. Didesni failai (>10KB) viršys konteksto ribas. Modulis 03 apima dalijimą į dalis ir vektorinį paiešką gamybos RAG sistemoms.

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) Chat:** Atidarykite [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) ir paklauskite:
> - „Kaip RAG apsaugo nuo AI haliucinacijų, palyginti su modelio mokymo duomenimis?“
> - „Kuo skiriasi šis paprastas metodas nuo vektorinės įterpties naudojimo paieškai?“
> - „Kaip plečiau sistemą, kad ji apdorotų kelis dokumentus ar didesnes žinių bazes?“
> - „Kokios geriausios praktikos užklausos struktūrizavimui, kad AI naudotų tik pateiktą kontekstą?“

**Atsakingas AI** - [ResponsibleAIDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)

Kurkite AI saugumą daugiasluoksniu principu. Ši demonstracija rodo dvi apsaugos sluoksnius, veikiančius kartu:

**1 dalis: LangChain4j įvesties saugos taisyklės** – Blokuoja pavojingas užklausas prieš joms pasiekiant LLM. Kurkite savo saugos taisykles, kurios patikrina draudžiamus raktinius žodžius ar šablonus. Jos veikia jūsų kode, todėl yra greitos ir nemokamos.

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

**2 dalis: Tiekėjo saugos filtrai** – GitHub Modeliai turi įmontuotus filtrus, kurie pagavo tai, ko jūsų taisyklės galėjo nepastebėti. Matysite griežtus blokavimus (HTTP 400 klaidos) rimtiems pažeidimams ir minkštus atsisakymus, kai AI mandagiai nepriima užklausos.

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) Chat:** Atidarykite [`ResponsibleAIDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java) ir paklauskite:
> - „Kas yra InputGuardrail ir kaip sukurti savo?“
> - „Kuo skiriasi griežtas blokavimas ir minkštas atsisakymas?“
> - „Kodėl naudoti tiek saugos taisykles, tiek tiekėjo filtrus kartu?“

## Tolimesni žingsniai

**Kitas modulis:** [01-introduction - Pradžia su LangChain4j ir gpt-5 Azure aplinkoje](../01-introduction/README.md)

---

**Naršymas:** [← Atgal į pagrindinį](../README.md) | [Toliau: Modulis 01 - Įvadas →](../01-introduction/README.md)

---

## Trikčių šalinimas

### Pirmasis Maven kūrimas

**Problema:** Pradinė `mvn clean compile` arba `mvn package` komanda užtrunka ilgai (10-15 minučių)

**Priežastis:** Maven pirmą kartą turi atsisiųsti visas projekto priklausomybes (Spring Boot, LangChain4j bibliotekas, Azure SDK ir kt.).

**Sprendimas:** Tai normalu. Vėlesni kūrimai bus daug greitesni, nes priklausomybės bus talpinamos vietoje. Atsisiuntimo laikas priklauso nuo jūsų tinklo spartumo.

### PowerShell Maven komandų sintaksės problema

**Problema:** Maven komandos sukelia klaidą `Unknown lifecycle phase ".mainClass=..."`

**Priežastis:** PowerShell interpretuoja `=` kaip kintamojo priskyrimo operatorių, kuris laužo Maven savybių sintaksę
**Sprendimas**: Naudokite stop-parsing operatorių `--%` prieš Maven komandą:

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

**Problema**: AI atsakymai rodo šiukšlines simbolių eilutes (pvz., `????` arba `â??`) vietoje emocijų PowerShell

**Priežastis**: PowerShell numatytasis kodavimas nepalaiko UTF-8 emocijų

**Sprendimas**: Paleiskite šią komandą prieš vykdant Java programas:
```cmd
chcp 65001
```

Tai priverčia terminalą naudoti UTF-8 kodavimą. Arba naudokite Windows Terminal, kuris geriau palaiko Unicode.

### API kvietimų derinimas

**Problema**: Autentifikacijos klaidos, kvotų apribojimai ar netikėti AI modelio atsakymai

**Sprendimas**: Pavyzdžiuose yra `.logRequests(true)` ir `.logResponses(true)`, kurie rodo API kvietimus konsolėje. Tai padeda išspręsti autentifikacijos klaidas, kvotų apribojimus ar netikėtus atsakymus. Šiuos ženklus pašalinkite produkcijoje, kad sumažintumėte žurnalų triukšmą.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Atsakomybės atsisakymas**:
Šis dokumentas buvo išverstas naudojant dirbtinio intelekto vertimo paslaugą [Co-op Translator](https://github.com/Azure/co-op-translator). Nors stengiamės užtikrinti tikslumą, prašome atkreipti dėmesį, kad automatizuotuose vertimuose gali būti klaidų ar netikslumų. Originalus dokumentas gimtąja kalba turėtų būti laikomas autoritetingu šaltiniu. Svarbiai informacijai rekomenduojamas profesionalus žmogiškasis vertimas. Mes neatsakome už bet kokius nesusipratimus ar neteisingus interpretavimus, kylantčius dėl šio vertimo naudojimo.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->