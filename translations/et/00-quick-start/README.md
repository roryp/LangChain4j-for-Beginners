<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "22b5d7c8d7585325e38b37fd29eafe25",
  "translation_date": "2026-01-06T01:58:42+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "et"
}
-->
# Moodul 00: Kiirkäivitus

## Sisukord

- [Tutvustus](../../../00-quick-start)
- [Mis on LangChain4j?](../../../00-quick-start)
- [LangChain4j Sõltuvused](../../../00-quick-start)
- [Eeltingimused](../../../00-quick-start)
- [Seadistamine](../../../00-quick-start)
  - [1. Hangi oma GitHubi token](../../../00-quick-start)
  - [2. Sea oma token](../../../00-quick-start)
- [Näidete käivitamine](../../../00-quick-start)
  - [1. Lihtne vestlus](../../../00-quick-start)
  - [2. Päringu mustrid](../../../00-quick-start)
  - [3. Funktsioonikõned](../../../00-quick-start)
  - [4. Dokumendi küsimused ja vastused (RAG)](../../../00-quick-start)
  - [5. Vastutustundlik tehisintellekt](../../../00-quick-start)
- [Mida iga näide näitab](../../../00-quick-start)
- [Järgmised sammud](../../../00-quick-start)
- [Tõrkeotsing](../../../00-quick-start)

## Tutvustus

See kiirkäivitus on mõeldud selleks, et aidata sul LangChain4j-ga võimalikult kiiresti tööle saada. See käsitleb tehisintellektirakenduste loomise põhitõdesid LangChain4j ja GitHubi mudelitega. Järgmistes moodulites kasutad Azure OpenAI-d LangChain4j-ga, et luua keerukamaid rakendusi.

## Mis on LangChain4j?

LangChain4j on Java teek, mis lihtsustab tehisintellekti jõuliste rakenduste loomist. Selle asemel, et tegeleda HTTP klientide ja JSONi parseritega, kasutad puhtaid Java API-sid.

"Chain" LangChaini nimes viitab mitme komponendi ühendamisele - võid ketti ühendada päringu mudelile ja seejärel parserile või kokku viia mitu AI kutset, kus ühe väljund on järgmise sisend. See kiirkäivitus keskendub alustõdedele, enne kui uurida keerukamaid ahelaid.

<img src="../../../translated_images/et/langchain-concept.ad1fe6cf063515e1.png" alt="LangChain4j ketistamise kontseptsioon" width="800"/>

*Kettide loomine LangChain4j-s – ehituskivid ühenduvad võimsateks AI töövoogudeks*

Kasutame kolme põhikomponenti:

**ChatLanguageModel** – liides AI mudelitega suhtlemiseks. Kutsu `model.chat("prompt")` ja saa vastuse string. Me kasutame `OpenAiOfficialChatModel`i, mis töötab OpenAI-ga ühilduvate lõpp-punktidega nagu GitHubi mudelid.

**AiServices** – loob tüübiturvalised AI teenuste liidesed. Defineeri meetodid, märgista need `@Tool` annotatsiooniga ning LangChain4j korraldab nende käivitamise automaatselt. AI kutsub sinu Java meetodeid vajadusel ise.

**MessageWindowChatMemory** – hoiab vestluse ajalugu. Ilma selleta on iga päring iseseisev. Sellega mäletab AI varasemaid sõnumeid ja hoiab konteksti mitme käigu vältel.

<img src="../../../translated_images/et/architecture.eedc993a1c576839.png" alt="LangChain4j arhitektuur" width="800"/>

*LangChain4j arhitektuur – põhikomponendid töötamas koos, et jõustada sinu AI rakendusi*

## LangChain4j Sõltuvused

See kiirkäivitus kasutab kahte Maven'i sõltuvust [`pom.xml`](../../../00-quick-start/pom.xml)-s:

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

`langchain4j-open-ai-official` moodul pakub `OpenAiOfficialChatModel` klassi, mis ühendub OpenAI-ga ühilduvate API-dega. GitHubi mudelid kasutavad sama API formaati, nii et eraldi adapterit ei ole vaja - lihtsalt suuna baas-URL aadressile `https://models.github.ai/inference`.

## Eeltingimused

**Kas kasutad Dev-konteinerit?** Java ja Maven on juba paigaldatud. Sul on vaja ainult GitHubi isikliku ligipääsu tokenit.

**Kohalik arendus:**
- Java 21+, Maven 3.9+
- GitHubi isiklik ligipääsu token (juhised allpool)

> **Märkus:** See moodul kasutab GitHubi mudelit `gpt-4.1-nano`. Ära muuda koodis mudeli nime - see on seadistatud töötama GitHubi kättesaadavate mudelitega.

## Seadistamine

### 1. Hangi oma GitHubi token

1. Mine [GitHub Seaded → Isiklikud ligipääsu tokenid](https://github.com/settings/personal-access-tokens)
2. Klõpsa "Generate new token"
3. Sea kirjeldav nimi (nt "LangChain4j Demo")
4. Sea aegumistähtaeg (7 päeva soovitatav)
5. Konto õiguste alt leia "Models" ja vali "Ainult lugemine"
6. Klõpsa "Generate token"
7. Kopeeri ja salvesta oma token – sa ei näe seda enam uuesti

### 2. Sea oma token

**Variant 1: Kasutades VS Code'i (soovitatav)**

Kui kasutad VS Code'i, lisa oma token projekti juurkataloogis asuvasse `.env` faili:

Kui `.env` faili ei ole, kopeeri `.env.example` fail nimega `.env` või loo uus `.env` fail projekti juurkataloogi.

**Näide `.env` failist:**
```bash
# Asukohas /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Seejärel võid lihtsalt hiire parema nupuga klõpsata mõnel demo failil (nt `BasicChatDemo.java`) Exploreris ja valida **"Run Java"** või kasutada käivitamise konfiguratsioone Run and Debug paneelist.

**Variant 2: Kasutades terminali**

Sea token keskkonnamuutujana:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Näidete käivitamine

**VS Code kasutajad:** Paremklõpsa demofailil Exploreris ja vali **"Run Java"** või kasuta Run and Debug paneeli konfiguratsioone (veendu, et token on lisatud `.env` faili enne).

**Maveni kasutajad:** Võid ka käsurealt käivitada:

### 1. Lihtne vestlus

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Päringu mustrid

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Näitab nullkivist, väheste näidete, mõttekäigu ja rollipõhist päringut.

### 3. Funktsioonikõned

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI kutsub vajadusel automaatselt sinu Java meetodeid.

### 4. Dokumendi küsimused ja vastused (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Esita küsimusi faili `document.txt` sisu kohta.

### 5. Vastutustundlik tehisintellekt

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

Vaata, kuidas AI turvafiltrid blokeerivad kahjulikku sisu.

## Mida iga näide näitab

**Lihtne vestlus** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Alusta siit, et näha LangChain4j kõige lihtsamat kasutust. Sa lood `OpenAiOfficialChatModel` objekti, saadad päringu `.chat()` meetodiga ja saad vastuse tagasi. See demonstreerib aluseid: kuidas initsialiseerida mudeleid kohandatud lõpp-punktide ja API võtmega. Kui see muster on selge, saavad kõik edasised toimingud sellest lähtuda.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Proovi [GitHub Copiloti](https://github.com/features/copilot) vestlusega:** Ava [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) ja küsi:
> - "Kuidas ma vahetaksin selles koodis GitHubi mudelid Azure OpenAI vastu?"
> - "Milliseid teisi parameetreid saan ma seadistada OpenAiOfficialChatModel.builder() meetodis?"
> - "Kuidas lisada voogesituse vastuseid täieliku vastuse ootamise asemel?"

**Päringute inseneritöö** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Nüüd, kui tead, kuidas mudeliga suhelda, vaatleme, mida sa talle ütled. See demo kasutab sama mudeli seadistust, aga näitab nelja päringu mustrit. Proovi nullkivist päringuid otsete juhisteks, väheste näidetega päringuid õpiks näidete põhjal, mõttekäigu ahelaid, et näidata mõtlemisprotsessi samme, ja rollipõhiseid päringuid konteksti seadmiseks. Näed, kuidas sama mudel annab oluliselt erinevaid tulemusi sõltuvalt, kuidas sa päringut vormistad.

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

> **🤖 Proovi [GitHub Copiloti](https://github.com/features/copilot) vestlusega:** Ava [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) ja küsi:
> - "Mis vahe on nullkivist ja väheste näidetega päringutel ning millal kumbagi kasutada?"
> - "Kuidas mõjutab temperatuuri parameeter mudeli vastuseid?"
> - "Millised on tehnikad päringute sisestusrünnakute vältimiseks tootmises?"
> - "Kuidas luua taaskasutatavaid PromptTemplate objekte tavaliste mustrite jaoks?"

**Tööriistade integreerimine** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Siin muutub LangChain4j võimsaks. Sa kasutad `AiServices` AI assistendi loomiseks, mis saab kutsuda sinu Java meetodeid. Lihtsalt märgista meetodid `@Tool("kirjeldus")` annotatsiooniga ja LangChain4j korraldab ülejäänu - AI otsustab automaatselt, millal iga tööriista kasutada vastavalt kasutaja päringule. See demonstreerib funktsioonikõnesid, mis on võtmetehnika AI ehitamisel, mis lisaks vastamisele suudab ka toiminguid teha.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Proovi [GitHub Copiloti](https://github.com/features/copilot) vestlusega:** Ava [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) ja küsi:
> - "Kuidas töötab @Tool annotatsioon ja mida LangChain4j selle taga teeb?"
> - "Kas AI saab järjestikku kutsuda mitut tööriista keeruliste probleemide lahendamiseks?"
> - "Mis juhtub, kui tööriist viskab erandi - kuidas ma peaksin vigu käsitlema?"
> - "Kuidas integreerida päris API selle kalkulaatori näite asemel?"

**Dokumendi küsimused ja vastused (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Siin näed RAG (taastumis-põhine genereerimine) alustalasid. Selle asemel, et tugineda mudeli treeningandmetele, laed faili [`document.txt`](../../../00-quick-start/document.txt) sisu ja lisad selle päringusse. AI vastab vastavalt sinu dokumendile, mitte oma üldisele teadmistepagasile. See on esimene samm süsteemide loomisel, mis saavad töötada sinu enda andmetega.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Märkus:** See lihtne meetod laadib kogu dokumendi päringusse. Suuremate failide (>10KB) puhul ületad konteksti piire. Moodul 03 käsitleb tükkideks lõikamist ja vektoripõhist otsingut tootmiseks sobivate RAG süsteemide jaoks.

> **🤖 Proovi [GitHub Copiloti](https://github.com/features/copilot) vestlusega:** Ava [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) ja küsi:
> - "Kuidas RAG takistab AI hallutsinatsioone võrreldes mudeli treeningandmete kasutamisega?"
> - "Mis vahe on selle lihtsa meetodi ja vektorite manuste kasutamise vahel päringul?"
> - "Kuidas skaleerida seda mitme dokumendi või suuremate teadmiste-põhjade käsitlemiseks?"
> - "Millised on parimad praktikad päringu struktureerimisel, et AI kasutaks ainult pakutud konteksti?"

**Vastutustundlik tehisintellekt** - [ResponsibleAIDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)

Ehita AI turvalisus mitmetasandilise kaitsega. See demo näitab kahte kaitsekihte, mis töötavad koos:

**Osa 1: LangChain4j sisendi kaitseliinid** – Blokeerivad ohtlikud päringud enne, kui need LLMi jõuavad. Loo kohandatud kaitseliinid, mis kontrollivad keelatud märksõnu või mustreid. Need töötavad sinu koodis, nii et need on kiired ja tasuta.

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

**Osa 2: Teenusepakkuja turvafiltrid** – GitHubi mudelitel on sisseehitatud filtrid, mis tabavad seda, mida sinu kaitseliinid võivad jätta vahele. Näed rasket blokeerimist (HTTP 400 vead) tõsiste rikkumiste korral ja peenet hoidumist, kus AI viisakalt keeldub.

> **🤖 Proovi [GitHub Copiloti](https://github.com/features/copilot) vestlusega:** Ava [`ResponsibleAIDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java) ja küsi:
> - "Mis on InputGuardrail ja kuidas ma saan oma luua?"
> - "Mis vahe on raskel blokeerimisel ja pehmel keeldumisel?"
> - "Miks kasutada korraga nii kaitseliine kui ka teenusepakkuja filtreid?"

## Järgmised sammud

**Järgmine moodul:** [01-introduction - LangChain4j ja gpt-5 alustamine Azure peal](../01-introduction/README.md)

---

**Navigeerimine:** [← Tagasi peamise juurde](../README.md) | [Järgmine: Moodul 01 - Tutvustus →](../01-introduction/README.md)

---

## Tõrkeotsing

### Esimene Maven'i ehitus

**Probleem:** Algus `mvn clean compile` või `mvn package` võtab kaua aega (10-15 minutit)

**Põhjus:** Maven peab esimesel ehitusel alla laadima kõik projekti sõltuvused (Spring Boot, LangChain4j teegid, Azure SDKd jne).

**Lahendus:** See on tavapärane olek. Järgmised ehitused on palju kiirem, sest sõltuvused on kohapeal vahemälus. Allalaadimise aeg sõltub sinu võrgu kiirusest.

### PowerShell Maven käsu süntaks

**Probleem:** Maven käsud ebaõnnestuvad veaga `Unknown lifecycle phase ".mainClass=..."`

**Põhjus:** PowerShell tõlgendab `=` kui muutujale väärtuse omistamist, mis rikub Maven'i omaduste süntaksit
**Lahendus**: Kasuta stop-parsing operaatorit `--%` enne Maveni käsku:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Operatsioonimärk `--%` käsib PowerShellil edastada kõik järgnevad argumendid täpselt Mavenile tõlgendamata.

### Windows PowerShelli emotikonide kuvamine

**Probleem**: AI vastustes kuvatakse PowerShellis emotikonide asemel rämpsmärke (nt `????` või `â??`)

**Põhjus**: PowerShelli vaikimisi kodeering ei toeta UTF-8 emotikone

**Lahendus**: Käivita see käsk enne Java rakenduste käivitamist:
```cmd
chcp 65001
```

See sunnib terminali kasutama UTF-8 kodeeringut. Alternatiivina kasuta Windows Terminali, mis toetab paremini Unicode’i.

### API kõnede silumine

**Probleem**: Autentimiserandid, piirmäärad või ootamatud vastused AI mudelilt

**Lahendus**: Näidetes on kasutusel `.logRequests(true)` ja `.logResponses(true)`, et kuvada API kõned konsoolis. See aitab tõrkeotsingul autentimiserandite, piirmäärade või ootamatute vastuste puhul. Eemalda need lippud tootmiskeskkonnas, et vähendada logimist.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastutusest loobumine**:  
See dokument on tõlgitud kasutades tehisintellekti tõlketeenust [Co-op Translator](https://github.com/Azure/co-op-translator). Kuigi püüame tagada täpsust, palun arvestage, et automatiseeritud tõlked võivad sisaldada vigu või ebatäpsusi. Originaaldokument selle emakeeles on pidada autoriteetseks allikaks. Olulise info puhul soovitatakse kasutada professionaalset inimtõlget. Me ei vastuta ühegi arusaamatuse või valesti tõlgendamise eest, mis võib tuleneda selle tõlke kasutamisest.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->