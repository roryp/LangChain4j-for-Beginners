<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-06T00:44:27+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "hu"
}
-->
# Modul 03: RAG (Retrieval-Augmented Generation)

## Tartalomjegyzék

- [Mit fogsz megtanulni](../../../03-rag)
- [Előfeltételek](../../../03-rag)
- [A RAG megértése](../../../03-rag)
- [Hogyan működik](../../../03-rag)
  - [Dokumentumfeldolgozás](../../../03-rag)
  - [Beágyazások létrehozása](../../../03-rag)
  - [Szemantikus keresés](../../../03-rag)
  - [Válaszgenerálás](../../../03-rag)
- [Az alkalmazás indítása](../../../03-rag)
- [Az alkalmazás használata](../../../03-rag)
  - [Dokumentum feltöltése](../../../03-rag)
  - [Kérdések feltevése](../../../03-rag)
  - [Forrás hivatkozások ellenőrzése](../../../03-rag)
  - [Kísérletezés kérdésekkel](../../../03-rag)
- [Kulcsfogalmak](../../../03-rag)
  - [Darabolási stratégia](../../../03-rag)
  - [Hasonlósági pontszámok](../../../03-rag)
  - [Memóriabeli tárolás](../../../03-rag)
  - [Context ablak kezelése](../../../03-rag)
- [Mikor számít a RAG](../../../03-rag)
- [Következő lépések](../../../03-rag)

## Mit fogsz megtanulni

Az előző modulokban megtanultad, hogyan folytass párbeszédet AI-val és hogyan strukturáld hatékonyan a promptjaidat. De van egy alapvető korlát: a nyelvi modellek csak azt tudják, amit a tanítás során megtanultak. Nem tudnak válaszolni az adott cég politikájával, projekt dokumentációjával vagy bármilyen olyan információval kapcsolatos kérdésre, amin nem tanultak.

A RAG (Retrieval-Augmented Generation) oldja meg ezt a problémát. Ahelyett, hogy megpróbálnád megtanítani a modellnek az információidat (ami költséges és nem praktikus), lehetőséget adsz neki, hogy keresgéljen a dokumentumaid között. Amikor valaki kérdez, a rendszer megtalálja a releváns információt és beilleszti azt a promptba. A modell így az előhívott kontextus alapján válaszol.

Gondolj a RAG-ra úgy, mint egy hivatkozási könyvtár létrehozására a modell számára. Amikor kérdezel, a rendszer:

1. **Felhasználói kérdés** – felteszel egy kérdést  
2. **Beágyazás** – a kérdés vektorrá alakul  
3. **Vektor keresés** – hasonló dokumentumdarabokat keres  
4. **Kontextus összeállítás** – releváns darabokat ad hozzá a prompthoz  
5. **Válaszadás** – az LLM a kontextus alapján választ generál  

Ez a modell válaszait a valódi adataidra alapozza, nem a tanult tudására vagy kitalált válaszokra.

<img src="../../../translated_images/hu/rag-architecture.ccb53b71a6ce407f.png" alt="RAG Architecture" width="800"/>

*RAG munkafolyamat – a felhasználói kérdéstől a szemantikus keresésen át a kontextuális válaszgenerálásig*

## Előfeltételek

- Befejezett 01-es modul (Azure OpenAI erőforrások telepítve)  
- `.env` fájl a gyökérkönyvtárban az Azure hitelesítő adatokkal (a `azd up` parancs létrehozta az 01-es modulban)

> **Megjegyzés:** Ha még nem fejezted be az 01-es modult, először az ottani telepítési útmutatót kövesd.


## Hogyan működik

### Dokumentumfeldolgozás

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

Amikor feltöltesz egy dokumentumot, a rendszer darabokra tördel – kisebb egységekre, amelyek kényelmesen beleférnek a modell kontextusablakába. Ezek a darabok kismértékben átfedik egymást, hogy a határoknál ne veszítsd el a kontextust.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```

> **🤖 Próbáld ki a [GitHub Copilot](https://github.com/features/copilot) Chattel:** Nyisd meg a [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) fájlt és kérdezd meg:  
> - "Hogyan darabolja a LangChain4j a dokumentumokat és miért fontos az átfedés?"  
> - "Mi az optimális darabméret különböző dokumentumtípusokhoz és miért?"  
> - "Hogyan kezeljem a többnyelvű vagy speciális formázású dokumentumokat?"

### Beágyazások létrehozása

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Minden darabot egy numerikus reprezentációvá alakít át a rendszer, amit beágyazásnak hívunk – gyakorlatilag egy matematikai ujjlenyomat, ami megragadja a szöveg jelentését. Hasonló szöveg hasonló beágyazásokat eredményez.

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

<img src="../../../translated_images/hu/vector-embeddings.2ef7bdddac79a327.png" alt="Vector Embeddings Space" width="800"/>

*Dokumentumok vetítése vektorokként a beágyazási térben – hasonló tartalom csoportosul*

### Szemantikus keresés

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Amikor kérdezel, a kérdésed is beágyazássá alakul. A rendszer összehasonlítja a kérdésed beágyazását a dokumentumdarabok beágyazásaival. Megtalálja a tartalmilag leginkább hasonló darabokat – nem csak kulcsszó-egyezés szerint, hanem valódi szemantikai hasonlóság alapján.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```

> **🤖 Próbáld ki a [GitHub Copilot](https://github.com/features/copilot) Chattel:** Nyisd meg a [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) fájlt és kérdezd meg:  
> - "Hogyan működik a hasonlóság keresés beágyazásokkal és mi határozza meg a pontszámot?"  
> - "Milyen hasonlósági küszöböt használjak és ez hogyan befolyásolja az eredményeket?"  
> - "Hogyan kezeljem az eseteket, amikor nem talál releváns dokumentumot?"

### Válaszgenerálás

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

A leginkább releváns darabokat belefoglalja a promptba a modell. A modell elolvassa ezeket, és az alapján válaszol. Ez megakadályozza a „hallucinációt” – a modell csak abból tud válaszolni, ami a rendelkezésére áll.

## Az alkalmazás indítása

**Telepítés ellenőrzése:**

Győződj meg róla, hogy a `.env` fájl megvan a gyökérkönyvtárban, az Azure hitelesítő adatokkal (az 01-es modul alatt jött létre):
```bash
cat ../.env  # Meg kell jeleníteni az AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT értékeket
```

**Indítsd el az alkalmazást:**

> **Megjegyzés:** Ha már az összes alkalmazást elindítottad a `./start-all.sh` segítségével az 01-es modulból, ez a modul már fut a 8081-es porton. A lentiek helyett közvetlenül a http://localhost:8081 oldalra léphetsz.

**1. lehetőség: Spring Boot Dashboard használata (VS Code felhasználóknak ajánlott)**

A fejlesztői konténer tartalmazza a Spring Boot Dashboard kiegészítőt, amely vizuális felületet biztosít az összes Spring Boot alkalmazás kezeléséhez. A VS Code bal oldali Activity Bar részén (a Spring Boot ikonon) találod.

A Spring Boot Dashboard-on keresztül:  
- Meglátod az összes elérhető Spring Boot alkalmazást a munkaterületen  
- Egy kattintással indíthatod/leállíthatod őket  
- Valós időben nézheted az alkalmazás logjait  
- Monitorozhatod az alkalmazás állapotát  

Egyszerűen kattints a play gombra a "rag" modul mellett, vagy indíts el egyszerre minden modult.

<img src="../../../translated_images/hu/dashboard.fbe6e28bf4267ffe.png" alt="Spring Boot Dashboard" width="400"/>

**2. lehetőség: Shell szkriptek használata**

Indítsd el az összes webalkalmazást (01-04 modulok):

**Bash:**
```bash
cd ..  # A gyökérkönyvtárból
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # A gyökérkönyvtárból
.\start-all.ps1
```

Vagy csak ezt a modult indítsd el:

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

Mindkét szkript automatikusan betölti a környezeti változókat a gyökér `.env` fájlból és ha a JAR fájlok nem léteznek, le is fordítja őket.

> **Megjegyzés:** Ha inkább manuálisan szeretnéd lefordítani az összes modult indítás előtt:  
>  
> **Bash:**  
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
  
> **PowerShell:**  
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
  
Nyisd meg böngészőben a http://localhost:8081 címet.

**Leállításhoz:**

**Bash:**  
```bash
./stop.sh  # Csak ez a modul
# Vagy
cd .. && ./stop-all.sh  # Minden modul
```
  
**PowerShell:**  
```powershell
.\stop.ps1  # Csak ez a modul
# Vagy
cd ..; .\stop-all.ps1  # Minden modul
```


## Az alkalmazás használata

Az alkalmazás webes felületet biztosít dokumentum feltöltéshez és kérdezéshez.

<a href="images/rag-homepage.png"><img src="../../../translated_images/hu/rag-homepage.d90eb5ce1b3caa94.png" alt="RAG Application Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*A RAG alkalmazás felülete – tölts fel dokumentumokat és tegyél fel kérdéseket*

### Dokumentum feltöltése

Kezdésként tölts fel egy dokumentumot – a TXT fájlok jól működnek teszteléshez. A jelen könyvtárban egy `sample-document.txt` található, amely LangChain4j funkciókat, a RAG megvalósítást és bevált gyakorlatokat tartalmaz – ideális a rendszer kipróbálásához.

A rendszer feldolgozza a dokumentumot, darabokra tördel és minden darabhoz létrehozza a beágyazásokat. Ez automatikusan megtörténik feltöltéskor.

### Kérdések feltevése

Ezután tegyél fel specifikus kérdéseket a dokumentum tartalmáról. Próbálj meg tényeken alapuló, egyértelműen a dokumentumban megadott kérdéseket. A rendszer megkeresi a releváns darabokat, belefoglalja a promptba és választ generál.

### Forrás hivatkozások ellenőrzése

Minden válasz tartalmazza a forrás hivatkozásokat hasonlósági pontszámokkal együtt. Ezek a pontszámok (0 és 1 között) azt mutatják, mennyire releváns volt az adott darab a kérdésedhez. A magasabb pontszám jobb egyezést jelent. Így ellenőrizheted a választ az eredeti forrásanyaggal.

<a href="images/rag-query-results.png"><img src="../../../translated_images/hu/rag-query-results.6d69fcec5397f355.png" alt="RAG Query Results" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Kérdezési eredmények válasszal, forrás hivatkozásokkal és relevancia pontszámokkal*

### Kísérletezés kérdésekkel

Próbálj ki különböző típusú kérdéseket:  
- Konkrét tények: „Mi a fő téma?”  
- Összehasonlítás: „Mi a különbség X és Y között?”  
- Összefoglalók: „Foglald össze a Z kulcspontjait”

Figyeld meg, hogyan változnak a relevancia pontszámok annak függvényében, hogy mennyire jól illeszkedik a kérdésed a dokumentumtartalomhoz.

## Kulcsfogalmak

### Darabolási stratégia

A dokumentumokat 300 tokenes darabokra osztjuk, 30 tokenes átfedéssel. Ez az arány biztosítja, hogy minden darab elég kontextust tartalmazzon, ugyanakkor elég kicsi maradjon ahhoz, hogy több darabot lehessen a promptba foglalni.

### Hasonlósági pontszámok

A pontszámok 0 és 1 között mozognak:  
- 0,7-1,0: Nagyon releváns, pontos egyezés  
- 0,5-0,7: Releváns, jó kontextus  
- 0,5 alatt: Kiszelektált, túl eltérő  

A rendszer csak az adott minimum küszöbnél magasabb pontszámú darabokat hozza vissza a minőség érdekében.

### Memóriabeli tárolás

Ez a modul egyszerűség kedvéért memóriabeli tárolást használ. Az alkalmazás újraindításakor a feltöltött dokumentumok elvesznek. Éles rendszerek tartós vektoralapú adatbázisokat használnak, mint például Qdrant vagy Azure AI Search.

### Context ablak kezelése

Minden modellnek van maximális kontextusablak mérete. Nem tudod minden darabot belefoglalni egy nagy dokumentumból. A rendszer a legrelevánsabb N darabot (alapértelmezett: 5) választja ki, hogy a korlátok között maradva elegendő kontextust adjon a pontos válaszokhoz.

## Mikor számít a RAG

**Használd a RAG-ot, amikor:**  
- Saját dokumentumokról kérdeznek  
- Információk gyakran változnak (szabályzatok, árak, specifikációk)  
- Pontosságot igényel a forrásmegjelölés  
- A tartalom túl nagy egyetlen promptba  
- Ellenőrizhető, megalapozott válaszokra van szükség

**Ne használd a RAG-ot, amikor:**  
- Általános tudásra van szükség, amit a modell már ismer  
- Valós idejű adat kell (a RAG feltöltött dokumentumokon működik)  
- A tartalom elég kicsi, hogy közvetlenül a promptban legyen

## Következő lépések

**Következő modul:** [04-tools - AI ágens eszközökkel](../04-tools/README.md)

---

**Navigáció:** [← Előző: Modul 02 - Prompt tervezés](../02-prompt-engineering/README.md) | [Vissza a főoldalra](../README.md) | [Következő: Modul 04 - Eszközök →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Felmondás**:
Ezt a dokumentumot az AI fordítószolgáltatás, a [Co-op Translator](https://github.com/Azure/co-op-translator) segítségével fordítottuk le. Bár a pontosságra törekszünk, kérjük, vegye figyelembe, hogy az automatikus fordítások hibákat vagy pontatlanságokat tartalmazhatnak. Az eredeti dokumentum az anyanyelvén tekintendő hiteles forrásnak. Kritikus információk esetén szakmai, emberi fordítást javasolunk. Nem vállalunk felelősséget az ebből a fordításból eredő félreértésekért vagy félreértelmezésekért.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->