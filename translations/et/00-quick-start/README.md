<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T15:29:42+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "et"
}
-->
# Moodul 00: Kiire algus

## Sisukord

- [Sissejuhatus](../../../00-quick-start)
- [Mis on LangChain4j?](../../../00-quick-start)
- [LangChain4j sõltuvused](../../../00-quick-start)
- [Eeltingimused](../../../00-quick-start)
- [Seadistamine](../../../00-quick-start)
  - [1. Hangi oma GitHubi token](../../../00-quick-start)
  - [2. Sea oma token](../../../00-quick-start)
- [Näidete käivitamine](../../../00-quick-start)
  - [1. Põhiline vestlus](../../../00-quick-start)
  - [2. Päringu mustrid](../../../00-quick-start)
  - [3. Funktsiooni kutsumine](../../../00-quick-start)
  - [4. Dokumendi küsimused ja vastused (RAG)](../../../00-quick-start)
- [Mida iga näide näitab](../../../00-quick-start)
- [Järgmised sammud](../../../00-quick-start)
- [Tõrkeotsing](../../../00-quick-start)

## Sissejuhatus

See kiire algus on mõeldud selleks, et saaksite LangChain4j-ga võimalikult kiiresti tööle hakata. See käsitleb AI rakenduste loomise absoluutseid põhialuseid LangChain4j ja GitHubi mudelitega. Järgmistes moodulites kasutate Azure OpenAI-d koos LangChain4j-ga keerukamate rakenduste loomiseks.

## Mis on LangChain4j?

LangChain4j on Java teek, mis lihtsustab AI-põhiste rakenduste loomist. Selle asemel, et tegeleda HTTP klientide ja JSON-i parsimisega, töötate puhaste Java API-dega.

LangChaini "chain" viitab mitme komponendi ühendamisele - võite ühendada päringu mudeliga, mudeli parseriga või ühendada mitu AI kõnet, kus ühe väljund läheb järgmise sisendiks. See kiire algus keskendub põhialustele enne keerukamate ahelate uurimist.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1e961a13c73d45cfa305fd03d81bd11e5d34d0e36ed28a44d.et.png" alt="LangChain4j Chaining Concept" width="800"/>

*Komponentide ühendamine LangChain4j-s – ehitusplokid ühenduvad võimsate AI töövoogude loomiseks*

Kasutame kolme põhikomponenti:

**ChatLanguageModel** – liides AI mudelitega suhtlemiseks. Kutsu `model.chat("prompt")` ja saa vastuseks string. Kasutame `OpenAiOfficialChatModel`-i, mis töötab OpenAI-ga ühilduvate lõpp-punktidega nagu GitHubi mudelid.

**AiServices** – loob tüübiturvalised AI teenuste liidesed. Määra meetodid, märgista need `@Tool`-iga ja LangChain4j haldab orkestreerimist. AI kutsub automaatselt sinu Java meetodeid, kui vaja.

**MessageWindowChatMemory** – hoiab vestluse ajalugu. Ilma selleta on iga päring iseseisev. Sellega mäletab AI eelnevaid sõnumeid ja hoiab konteksti mitme vooru jooksul.

<img src="../../../translated_images/architecture.eedc993a1c57683951f20244f652fc7a9853f571eea835bc2b2828cf0dbf62d0.et.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j arhitektuur – põhikomponendid töötavad koos, et toita sinu AI rakendusi*

## LangChain4j sõltuvused

See kiire algus kasutab kahte Maven sõltuvust [`pom.xml`](../../../00-quick-start/pom.xml) failis:

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

`langchain4j-open-ai-official` moodul pakub `OpenAiOfficialChatModel` klassi, mis ühendub OpenAI-ga ühilduvate API-dega. GitHubi mudelid kasutavad sama API formaati, seega pole vaja erilist adapterit – lihtsalt suuna baas-URL `https://models.github.ai/inference` peale.

## Eeltingimused

**Kas kasutad Dev Containerit?** Java ja Maven on juba paigaldatud. Sul on vaja ainult GitHubi isikliku juurdepääsu tokenit.

**Kohalik arendus:**
- Java 21+, Maven 3.9+
- GitHubi isiklik juurdepääsu token (juhised allpool)

> **Märkus:** See moodul kasutab GitHubi mudelit `gpt-4.1-nano`. Ära muuda mudeli nime koodis – see on seadistatud töötama GitHubi saadaval olevate mudelitega.

## Seadistamine

### 1. Hangi oma GitHubi token

1. Mine [GitHubi seadistustesse → Isiklikud juurdepääsu tokenid](https://github.com/settings/personal-access-tokens)
2. Klõpsa "Generate new token"
3. Sea kirjeldav nimi (nt "LangChain4j Demo")
4. Sea aegumistähtaeg (soovitatavalt 7 päeva)
5. "Account permissions" alt leia "Models" ja sea "Read-only"
6. Klõpsa "Generate token"
7. Kopeeri ja salvesta token – seda ei näe enam uuesti

### 2. Sea oma token

**Variant 1: Kasutades VS Code'i (Soovitatav)**

Kui kasutad VS Code'i, lisa oma token projekti juurkausta `.env` faili:

Kui `.env` faili pole, kopeeri `.env.example` fail `.env`-ks või loo uus `.env` fail projekti juurkausta.

**Näide `.env` failist:**
```bash
# Failis /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Seejärel saad lihtsalt paremklõpsata mõnel demo failil (nt `BasicChatDemo.java`) Exploreris ja valida **"Run Java"** või kasutada käivituskonfiguratsioone Run and Debug paneelil.

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

**VS Code kasutamisel:** Paremklõpsa lihtsalt mõnel demo failil Exploreris ja vali **"Run Java"** või kasuta Run and Debug paneeli käivituskonfiguratsioone (veendu, et oled tokeni lisanud `.env` faili).

**Maveni kasutamisel:** Võid ka käsurealt käivitada:

### 1. Põhiline vestlus

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

Näitab zero-shot, few-shot, chain-of-thought ja rollipõhiseid päringuid.

### 3. Funktsiooni kutsumine

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI kutsub automaatselt sinu Java meetodeid, kui vaja.

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

## Mida iga näide näitab

**Põhiline vestlus** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Alusta siit, et näha LangChain4j lihtsaimat kasutust. Lood `OpenAiOfficialChatModel`-i, saadad päringu `.chat()` meetodiga ja saad vastuse. See demonstreerib alust: kuidas initsialiseerida mudeleid kohandatud lõpp-punktide ja API võtmetega. Kui see muster on selge, ehitatakse kõik muu selle peale.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Proovi koos [GitHub Copilot](https://github.com/features/copilot) Chatiga:** Ava [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) ja küsi:
> - "Kuidas ma vahetaksin selles koodis GitHubi mudelid Azure OpenAI vastu?"
> - "Milliseid teisi parameetreid saan seadistada OpenAiOfficialChatModel.builder() sees?"
> - "Kuidas lisada voogedastuse vastuseid, mitte oodata täielikku vastust?"

**Päringu inseneritöö** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Nüüd, kui tead, kuidas mudeliga rääkida, uurime, mida sa talle ütled. See demo kasutab sama mudeli seadistust, kuid näitab nelja erinevat päringu mustrit. Proovi zero-shot päringuid otseste juhiste jaoks, few-shot päringuid, mis õpivad näidete põhjal, chain-of-thought päringuid, mis paljastavad mõtlemisprotsessi samme, ja rollipõhiseid päringuid, mis seavad konteksti. Näed, kuidas sama mudel annab väga erinevaid tulemusi sõltuvalt sellest, kuidas sa oma päringu vormistad.

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

> **🤖 Proovi koos [GitHub Copilot](https://github.com/features/copilot) Chatiga:** Ava [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) ja küsi:
> - "Mis vahe on zero-shot ja few-shot päringutel ning millal kumbagi kasutada?"
> - "Kuidas mõjutab temperatuuri parameeter mudeli vastuseid?"
> - "Millised on tehnikad päringu süstimise rünnakute vältimiseks tootmises?"
> - "Kuidas luua taaskasutatavaid PromptTemplate objekte tavaliste mustrite jaoks?"

**Tööriistade integratsioon** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Siin muutub LangChain4j võimsaks. Kasutad `AiServices`-i, et luua AI assistent, mis saab kutsuda sinu Java meetodeid. Lihtsalt märgista meetodid `@Tool("kirjeldus")`-ga ja LangChain4j haldab ülejäänu – AI otsustab automaatselt, millal kasutada iga tööriista vastavalt kasutaja päringule. See demonstreerib funktsiooni kutsumist, mis on võtmetehnika AI loomiseks, mis suudab tegutseda, mitte ainult vastata küsimustele.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Proovi koos [GitHub Copilot](https://github.com/features/copilot) Chatiga:** Ava [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) ja küsi:
> - "Kuidas @Tool annotatsioon töötab ja mida LangChain4j selle taga teeb?"
> - "Kas AI saab järjestikku kutsuda mitut tööriista keerukate probleemide lahendamiseks?"
> - "Mis juhtub, kui tööriist viskab erandi – kuidas peaksin vigadega toime tulema?"
> - "Kuidas integreerida päris API selle kalkulaatori näite asemel?"

**Dokumendi küsimused ja vastused (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Siin näed RAG (otsingupõhine genereerimine) alustalasid. Selle asemel, et tugineda mudeli treeningandmetele, laed sisu failist [`document.txt`](../../../00-quick-start/document.txt) ja lisad selle päringusse. AI vastab sinu dokumendi põhjal, mitte üldise teadmise põhjal. See on esimene samm süsteemide loomisel, mis töötavad sinu enda andmetega.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Märkus:** See lihtne lähenemine laeb kogu dokumendi päringusse. Suurte failide (>10KB) puhul ületad konteksti piirid. Moodul 03 käsitleb tükkideks jagamist ja vektorotsingut tootmis-RAG süsteemide jaoks.

> **🤖 Proovi koos [GitHub Copilot](https://github.com/features/copilot) Chatiga:** Ava [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) ja küsi:
> - "Kuidas RAG takistab AI hallutsinatsioone võrreldes mudeli treeningandmete kasutamisega?"
> - "Mis vahe on selle lihtsa lähenemise ja vektorite manuste kasutamise vahel otsinguks?"
> - "Kuidas skaleerida see mitme dokumendi või suurema teadmistebaasi jaoks?"
> - "Millised on parimad tavad päringu struktureerimiseks, et AI kasutaks ainult antud konteksti?"

## Silumine

Näited sisaldavad `.logRequests(true)` ja `.logResponses(true)`, et näidata API kõnesid konsoolis. See aitab tõrkeotsingul autentimisvigade, kiirusepiirangute või ootamatute vastuste korral. Tootmises eemalda need lipud, et vähendada logimüra.

## Järgmised sammud

**Järgmine moodul:** [01-introduction - LangChain4j ja gpt-5 kasutuselevõtt Azure'is](../01-introduction/README.md)

---

**Navigeerimine:** [← Tagasi avalehele](../README.md) | [Järgmine: Moodul 01 - Sissejuhatus →](../01-introduction/README.md)

---

## Tõrkeotsing

### Esimene Maven ehitus

**Probleem:** Esimene `mvn clean compile` või `mvn package` võtab kaua aega (10-15 minutit)

**Põhjus:** Maven peab esimesel ehitusel alla laadima kõik projekti sõltuvused (Spring Boot, LangChain4j teegid, Azure SDK-d jne).

**Lahendus:** See on normaalne käitumine. Järgmised ehitused on palju kiirem, sest sõltuvused on lokaalselt vahemälus. Allalaadimise aeg sõltub sinu võrgu kiirusest.

### PowerShelli Maven käsu süntaks

**Probleem:** Maven käsud ebaõnnestuvad veaga `Unknown lifecycle phase ".mainClass=..."`

**Põhjus:** PowerShell tõlgendab `=` kui muutujale väärtuse määramist, mis rikub Maven'i omaduste süntaksit.

**Lahendus:** Kasuta käsu ees stop-parsing operaatorit `--%`:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

`--%` operaator ütleb PowerShellile, et edastada kõik ülejäänud argumendid täpselt Mavenile ilma tõlgendamiseta.

### Windows PowerShelli emotikonide kuvamine

**Probleem:** AI vastustes kuvatakse PowerShellis rämpsmärgid (nt `????` või `â??`) emotikonide asemel

**Põhjus:** PowerShelli vaikimisi kodeering ei toeta UTF-8 emotikone

**Lahendus:** Käivita see käsk enne Java rakenduste käivitamist:
```cmd
chcp 65001
```

See sunnib terminali kasutama UTF-8 kodeeringut. Alternatiivina kasuta Windows Terminali, mis toetab paremini Unicode'i.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastutusest loobumine**:
See dokument on tõlgitud kasutades tehisintellekti tõlketeenust [Co-op Translator](https://github.com/Azure/co-op-translator). Kuigi püüame tagada täpsust, palun arvestage, et automaatsed tõlked võivad sisaldada vigu või ebatäpsusi. Originaaldokument selle emakeeles tuleks pidada autoriteetseks allikaks. Olulise teabe puhul soovitatakse kasutada professionaalset inimtõlget. Me ei vastuta selle tõlke kasutamisest tulenevate arusaamatuste või valesti mõistmiste eest.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->