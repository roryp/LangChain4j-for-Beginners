<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-06T01:47:56+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "lt"
}
-->
# Modulis 03: RAG (Retrieval-Augmented Generation)

## Turinys

- [Ko Išmoksite](../../../03-rag)
- [Reikalavimai](../../../03-rag)
- [RAG Suvokimas](../../../03-rag)
- [Kaip Tai Veikia](../../../03-rag)
  - [Dokumento Apdorojimas](../../../03-rag)
  - [Įterpinių Kūrimas](../../../03-rag)
  - [Semantinis Paieška](../../../03-rag)
  - [Atsakymo Generavimas](../../../03-rag)
- [Paleisti Programą](../../../03-rag)
- [Programos Naudojimas](../../../03-rag)
  - [Įkelti Dokumentą](../../../03-rag)
  - [Užduoti Klausimus](../../../03-rag)
  - [Patikrinti Šaltinių Nuorodas](../../../03-rag)
  - [Eksperimentuoti su Klausimais](../../../03-rag)
- [Pagrindinės Sąvokos](../../../03-rag)
  - [Dalijimosi Strategija](../../../03-rag)
  - [Panašumo Balai](../../../03-rag)
  - [Atminties Saugykla](../../../03-rag)
  - [Konteksto Langų Valdymas](../../../03-rag)
- [Kada RAG Yra Svarbus](../../../03-rag)
- [Kiti Veiksmai](../../../03-rag)

## Ko Išmoksite

Ankstesniuose moduliuose išmokote bendrauti su DI ir efektyviai struktūruoti savo užklausas. Tačiau yra esminė riba: kalbos modeliai žino tik tai, ką išmoko mokymo metu. Jie negali atsakyti į klausimus apie jūsų įmonės taisykles, projektų dokumentaciją ar bet kokią informaciją, kurios nebuvo mokyti.

RAG (Retrieval-Augmented Generation) išsprendžia šią problemą. Vietoj to, kad bandytumėte mokyti modelį savo informacijos (kas yra brangu ir nepraktiška), suteikiate jam galimybę ieškoti per jūsų dokumentus. Kai kas nors užduoda klausimą, sistema randa aktualią informaciją ir įtraukia ją į užklausą. Tada modelis atsako remdamasis tuo paimtu kontekstu.

Galvokite apie RAG kaip suteikiant modeliui nuorodų biblioteką. Kai užduodate klausimą, sistema:

1. **Vartotojo Užklausa** – Jūs užduodate klausimą
2. **Įterpimas** – Paverčia jūsų klausimą vektoriumi
3. **Vektorinė Paieška** – Randa panašius dokumentų fragmentus
4. **Konteksto Surinkimas** – Įtraukia aktualius fragmentus į užklausą
5. **Atsakymas** – LLM generuoja atsakymą remdamasis kontekstu

Tai pagrindžia modelio atsakymus jūsų tikrais duomenimis vietoje to, kad remtųsi mokymo žiniomis ar kurtų atsakymus iš oro.

<img src="../../../translated_images/lt/rag-architecture.ccb53b71a6ce407f.png" alt="RAG Architecture" width="800"/>

*RAG darbo eiga – nuo vartotojo užklausos iki semantinės paieškos ir kontekstinio atsakymo generavimo*

## Reikalavimai

- Atliktas Modulis 01 (įdiegti Azure OpenAI ištekliai)
- `.env` failas pagrindiniame kataloge su Azure kredencialais (sukurta su `azd up` Module 01 metu)

> **Pastaba:** Jei nesate atlikę Modulio 01, pirmiausia vykdykite ten pateiktas diegimo instrukcijas.

## Kaip Tai Veikia

### Dokumentų Apdorojimas

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

Įkėlę dokumentą, sistema jį padalija į fragmentus – mažesnius gabalus, kurie patogiai telpa modelio konteksto lange. Šie fragmentai šiek tiek persidengia, kad neprarastumėte konteksto ribose.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) pokalbiu:** Atidarykite [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) ir paklauskite:
> - "Kaip LangChain4j dalija dokumentus į fragmentus ir kodėl persidengimas svarbus?"
> - "Kokia yra optimali fragmentų dydžio riba skirtingų dokumentų tipams ir kodėl?"
> - "Kaip tvarkyti dokumentus keliomis kalbomis ar su specialiu formatavimu?"

### Įterpinių Kūrimas

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Kiekvienas fragmentas paverčiamas į skaitmeninę reprezentaciją, vadinamą įterpiniu – tai lyg matematinis pirštų atspaudas, apibūdinantis teksto prasmę. Panašus tekstas sukuria panašius įterpinius.

```java
@Bean
public EmbeddingModel embeddingModel() {
    return OpenAiOfficialEmbeddingModel.builder()
        .baseUrl(azureOpenAiEndpoint)
        .apiKey(azureOpenAiKey)
        .modelName(azureEmbeddingDeploymentName)
        .build();
}

EmbeddingStore<TextSegment> embeddingStore = 
    new InMemoryEmbeddingStore<>();
```

<img src="../../../translated_images/lt/vector-embeddings.2ef7bdddac79a327.png" alt="Vector Embeddings Space" width="800"/>

*Dokumentai pateikti kaip vektoriai įterpinių erdvėje – panašus turinys grupuojasi kartu*

### Semantinė Paieška

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Kai užduodate klausimą, jūsų klausimas taip pat paverčiamas įterpiniu. Sistema palygina jūsų klausimo įterpinį su visų dokumentų fragmentų įterpiniais. Randa fragmentus su pačia panašia reikšme – ne tik pagal raktinius žodžius, bet tikrą semantinį panašumą.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) pokalbiu:** Atidarykite [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) ir paklauskite:
> - "Kaip veikia panašumo paieška su įterpiniais ir kas lemia balą?"
> - "Kokį panašumo slenkstį turėčiau naudoti ir kaip tai veikia rezultatus?"
> - "Kaip elgtis, kai nėra rastų aktualių dokumentų?"

### Atsakymo Generavimas

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Svarbiausi fragmentai įtraukiami į modelio užklausą. Modelis perskaito tuos konkrečius fragmentus ir atsako į klausimą remdamasis ta informacija. Tai neleidžia modelio „halucinacijoms“ – jis gali atsakyti tik iš to, kas pateikta.

## Paleisti Programą

**Patikrinkite diegimą:**

Įsitikinkite, kad `.env` failas egzistuoja pagrindiniame kataloge su Azure kredencialais (sukurtas Modulio 01 metu):
```bash
cat ../.env  # Turėtų parodyti AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Paleiskite programą:**

> **Pastaba:** Jei jau paleidote visas programas naudojant `./start-all.sh` Modulyje 01, šis modulis jau veikia prievade 8081. Galite praleisti žemiau pateiktas paleidimo komandas ir tiesiog nueiti į http://localhost:8081.

**1 variantas: Naudojant Spring Boot Dashboard (rekomenduojama VS Code naudotojams)**

Dev konteineryje yra Spring Boot Dashboard plėtinys, kuris suteikia vizualią sąsają visoms Spring Boot programoms valdyti. Jį rasite kairėje VS Code veiklos juostoje (ieškokite Spring Boot piktogramos).

Iš Spring Boot Dashboard galite:
- Matyti visas prieinamas Spring Boot programas darbo aplinkoje
- Vienu spustelėjimu paleisti/stabdyti programas
- Realiai laiku žiūrėti programų logus
- Stebėti programų būseną

Tiesiog paspauskite paleidimo mygtuką šalia „rag“, kad paleistumėte šį modulį, arba paleiskite visus modulius vienu metu.

<img src="../../../translated_images/lt/dashboard.fbe6e28bf4267ffe.png" alt="Spring Boot Dashboard" width="400"/>

**2 variantas: Naudojant shell skriptus**

Paleisti visas web programas (moduliai 01-04):

**Bash:**
```bash
cd ..  # Iš šaknies direktorijos
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Iš šakninių katalogų
.\start-all.ps1
```

Arba paleiskite tik šį modulį:

**Bash:**
```bash
cd 03-rag
./start.sh
```

**PowerShell:**
```powershell
cd 03-rag
.\start.ps1
```

Abu skriptai automatiškai įkrauna aplinkos kintamuosius iš pagrindinio `.env` failo ir sukurs JAR failus, jei jų nėra.

> **Pastaba:** Jei norite rankiniu būdu surinkti visus modulius prieš paleidžiant:
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

Naršyklėje atidarykite http://localhost:8081.

**Norėdami sustabdyti:**

**Bash:**
```bash
./stop.sh  # Tik šis modulis
# Arba
cd .. && ./stop-all.sh  # Visi moduliai
```

**PowerShell:**
```powershell
.\stop.ps1  # Tik šis modulis
# Arba
cd ..; .\stop-all.ps1  # Visi moduliai
```

## Programos Naudojimas

Programa suteikia internetinę sąsają dokumentų įkėlimui ir klausimų uždavimui.

<a href="images/rag-homepage.png"><img src="../../../translated_images/lt/rag-homepage.d90eb5ce1b3caa94.png" alt="RAG Application Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*RAG programos sąsaja – įkelkite dokumentus ir užduokite klausimus*

### Įkelti Dokumentą

Pradėkite įkeldami dokumentą – TXT failai geriausiai tinka testavimui. Šiame kataloge yra pateiktas `sample-document.txt` su informacija apie LangChain4j funkcijas, RAG įgyvendinimą ir geriausias praktikas – puiku sistemai išbandyti.

Sistema apdoroja jūsų dokumentą, padalija į fragmentus ir sukuria įterpinius kiekvienam fragmentui. Tai vyksta automatiškai įkėlimo metu.

### Užduoti Klausimus

Dabar paklauskite konkrečių klausimų apie dokumentų turinį. Išbandykite faktinius klausimus, kurie aiškiai nurodyti dokumente. Sistema ieško aktualių fragmentų, įtraukia juos į užklausą ir generuoja atsakymą.

### Patikrinti Šaltinių Nuorodas

Atkreipkite dėmesį, kad kiekvienas atsakymas pateikia šaltinių nuorodas su panašumo balais. Šie balai (nuo 0 iki 1) rodo, kiek kiekvienas fragmentas buvo susijęs su jūsų klausimu. Aukštesni balai reiškia geresnius atitikimus. Tai leidžia jums patikrinti atsakymą pagal šaltinio medžiagą.

<a href="images/rag-query-results.png"><img src="../../../translated_images/lt/rag-query-results.6d69fcec5397f355.png" alt="RAG Query Results" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Užklausos rezultatai rodo atsakymą su šaltinių nuorodomis ir aktualumo balais*

### Eksperimentuoti su Klausimais

Išbandykite įvairių tipų klausimus:
- Konkretūs faktai: "Kokia pagrindinė tema?"
- Palyginimai: "Kuo skiriasi X nuo Y?"
- Santraukos: "Apibendrinkite pagrindines Z temas"

Stebėkite, kaip keičiasi panašumo balai priklausomai nuo to, kaip gerai jūsų klausimas atitinka dokumentų turinį.

## Pagrindinės Sąvokos

### Dalijimosi Strategija

Dokumentai dalijami į 300 simbolių fragmentus su 30 simbolių persidengimu. Toks balansas užtikrina, kad kiekviename fragmente yra pakankamai konteksto prasmingam turiniui, bet fragmentas išlieka pakankamai mažas, kad keli fragmentai tilptų į užklausą.

### Panašumo Balai

Balai svyruoja nuo 0 iki 1:
- 0.7-1.0: Labai aktualu, tikslus atitikimas
- 0.5-0.7: Aktualu, geras kontekstas
- Žemiau 0.5: Filtruojama, per tolimas neatitikimas

Sistema paima tik fragmentus, kurių balai aukštesni nei minimalus slenkstis, siekiant kokybės.

### Atminties Saugykla

Šis modulis naudoja atminties saugyklą dėl paprastumo. Paleidus programą iš naujo, įkelti dokumentai prarandami. Gamybinėse sistemose naudojamos nuolatinės vektorinių duomenų bazės, pvz., Qdrant ar Azure AI Search.

### Konteksto Langų Valdymas

Kiekvienas modelis turi maksimalų konteksto lango dydį. Jūs negalite įtraukti visų fragmentų iš didelio dokumento. Sistema pasiima N svarbiausių fragmentų (pagal nutylėjimą 5), kad liktų žemiau ribos ir būtų pakankamai konteksto tiksliai atsakyti.

## Kada RAG Yra Svarbus

**Naudokite RAG kai:**
- Atsakote į klausimus apie konfidencialius dokumentus
- Informacija dažnai kinta (politikos, kainos, specifikacijos)
- Reikia tikslumo su šaltinių priskyrimu
- Turinys per didelis vienai užklausai
- Reikia patikimų, pagrįstų atsakymų

**Nenaudokite RAG kai:**
- Klausimai apima bendrą modeliui jau žinomą informaciją
- Reikia realaus laiko duomenų (RAG veikia su įkeltais dokumentais)
- Turinys pakankamai mažas tiesiogiai įtraukti į užklausas

## Kiti Veiksmai

**Kitas modulis:** [04-tools - DI agentai su įrankiais](../04-tools/README.md)

---

**Navigacija:** [← Ankstesnis: Modulis 02 - Užuominų Kūrimas](../02-prompt-engineering/README.md) | [Grįžti į Pagrindinį](../README.md) | [Kitas: Modulis 04 - Įrankiai →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Atsakomybės apribojimas**:
Šis dokumentas buvo išverstas naudojant dirbtinio intelekto vertimo paslaugą [Co-op Translator](https://github.com/Azure/co-op-translator). Nors stengiamės užtikrinti tikslumą, prašome atkreipti dėmesį, kad automatizuoti vertimai gali turėti klaidų ar netikslumų. Originalus dokumentas, pateiktas gimtąja kalba, turėtų būti laikomas autoritetingu šaltiniu. Esant kritinei informacijai, rekomenduojamas profesionalus žmogaus vertimas. Mes neprisiimame atsakomybės už bet kokius nesusipratimus ar klaidingus supratimus, kylančius naudojant šį vertimą.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->