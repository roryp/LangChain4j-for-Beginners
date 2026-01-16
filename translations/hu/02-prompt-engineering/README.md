<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "8d787826cad7e92bf5cdbd116b1e6116",
  "translation_date": "2025-12-13T16:21:57+00:00",
  "source_file": "02-prompt-engineering/README.md",
  "language_code": "hu"
}
-->
# Modul 02: Prompt tervezés GPT-5-tel

## Tartalomjegyzék

- [Mit fogsz megtanulni](../../../02-prompt-engineering)
- [Előfeltételek](../../../02-prompt-engineering)
- [A prompt tervezés megértése](../../../02-prompt-engineering)
- [Hogyan használja ezt a LangChain4j](../../../02-prompt-engineering)
- [Az alapvető minták](../../../02-prompt-engineering)
- [Meglévő Azure erőforrások használata](../../../02-prompt-engineering)
- [Alkalmazás képernyőképek](../../../02-prompt-engineering)
- [A minták felfedezése](../../../02-prompt-engineering)
  - [Alacsony vs Magas lelkesedés](../../../02-prompt-engineering)
  - [Feladat végrehajtás (Eszköz bevezetők)](../../../02-prompt-engineering)
  - [Önreflektáló kód](../../../02-prompt-engineering)
  - [Strukturált elemzés](../../../02-prompt-engineering)
  - [Többfordulós csevegés](../../../02-prompt-engineering)
  - [Lépésről lépésre érvelés](../../../02-prompt-engineering)
  - [Korlátozott kimenet](../../../02-prompt-engineering)
- [Mit is tanulsz valójában](../../../02-prompt-engineering)
- [Következő lépések](../../../02-prompt-engineering)

## Mit fogsz megtanulni

Az előző modulban láttad, hogyan teszi lehetővé a memória a beszélgető AI-t, és használtad a GitHub Modelleket alapvető interakciókhoz. Most arra fókuszálunk, hogyan teszel fel kérdéseket – maguk a promptok – az Azure OpenAI GPT-5 használatával. A promptok felépítése drámaian befolyásolja a kapott válaszok minőségét.

A GPT-5-öt használjuk, mert bevezeti az érvelés vezérlését – megmondhatod a modellnek, mennyi gondolkodást végezzen a válaszadás előtt. Ez világosabbá teszi a különböző promptolási stratégiákat, és segít megérteni, mikor melyik megközelítést érdemes használni. Emellett az Azure kevesebb korlátozást alkalmaz a GPT-5-re, mint a GitHub Modellekre.

## Előfeltételek

- Az 01-es modul befejezése (Azure OpenAI erőforrások telepítve)
- `.env` fájl a gyökérkönyvtárban Azure hitelesítő adatokkal (az `azd up` által létrehozva az 01-es modulban)

> **Megjegyzés:** Ha még nem fejezted be az 01-es modult, először kövesd ott a telepítési utasításokat.

## A prompt tervezés megértése

A prompt tervezés arról szól, hogy olyan bemeneti szöveget alakíts ki, amely következetesen megadja a kívánt eredményeket. Nem csak kérdéseket feltenni – hanem úgy strukturálni a kéréseket, hogy a modell pontosan értse, mit akarsz és hogyan adja azt vissza.

Gondolj rá úgy, mint amikor utasítást adsz egy kollégának. A „Javítsd meg a hibát” homályos. A „Javítsd meg a null pointer exception-t a UserService.java 45. sorában null ellenőrzés hozzáadásával” konkrét. A nyelvi modellek ugyanígy működnek – a konkrétság és a struktúra számít.

## Hogyan használja ezt a LangChain4j

Ez a modul fejlett promptolási mintákat mutat be ugyanazon LangChain4j alapokra építve, mint az előző modulok, a prompt struktúrájára és az érvelés vezérlésére fókuszálva.

<img src="../../../translated_images/hu/langchain4j-flow.48e534666213010b.png" alt="LangChain4j Flow" width="800"/>

*Hogyan kapcsolja össze a LangChain4j a promptjaidat az Azure OpenAI GPT-5-tel*

**Függőségek** – A 02-es modul a `pom.xml`-ben definiált következő langchain4j függőségeket használja:  
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
  
**OpenAiOfficialChatModel konfiguráció** – [LangChainConfig.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/config/LangChainConfig.java)

A chat modellt manuálisan konfiguráljuk Spring bean-ként az OpenAI Official klienssel, amely támogatja az Azure OpenAI végpontokat. A kulcsfontosságú különbség az 01-es modulhoz képest az, hogyan strukturáljuk a `chatModel.chat()`-nek küldött promptokat, nem maga a modell beállítása.

**Rendszer- és felhasználói üzenetek** – [Gpt5PromptService.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/service/Gpt5PromptService.java)

A LangChain4j elkülöníti az üzenettípusokat az átláthatóság érdekében. A `SystemMessage` beállítja az AI viselkedését és kontextusát (például „Te egy kódellenőr vagy”), míg a `UserMessage` tartalmazza a tényleges kérést. Ez az elkülönítés lehetővé teszi, hogy az AI viselkedése következetes maradjon különböző felhasználói kérdések esetén.

```java
SystemMessage systemMsg = SystemMessage.from(
    "You are a helpful Java programming expert."
);

UserMessage userMsg = UserMessage.from(
    "Explain what a List is in Java"
);

String response = chatModel.chat(systemMsg, userMsg);
```
  
<img src="../../../translated_images/hu/message-types.93e0779798a17c9d.png" alt="Message Types Architecture" width="800"/>

*A SystemMessage tartós kontextust biztosít, míg a UserMessage-ek egyedi kéréseket tartalmaznak*

**MessageWindowChatMemory a többfordulós beszélgetéshez** – A többfordulós beszélgetési mintához újrahasználjuk a 01-es modulból a `MessageWindowChatMemory`-t. Minden munkamenet saját memóriapéldányt kap, amely egy `Map<String, ChatMemory>`-ben tárolódik, lehetővé téve több párhuzamos beszélgetést kontextuskeveredés nélkül.

**Prompt sablonok** – A valódi fókusz itt a prompt tervezés, nem új LangChain4j API-k. Minden minta (alacsony lelkesedés, magas lelkesedés, feladat végrehajtás stb.) ugyanazt a `chatModel.chat(prompt)` metódust használja, de gondosan strukturált prompt szövegekkel. Az XML tagek, utasítások és formázás mind a prompt szöveg részei, nem LangChain4j funkciók.

**Érvelés vezérlés** – A GPT-5 érvelési erőfeszítését prompt utasítások szabályozzák, mint például „maximum 2 érvelési lépés” vagy „alapos feltárás”. Ezek prompt tervezési technikák, nem LangChain4j konfigurációk. A könyvtár egyszerűen továbbítja a promptokat a modellnek.

A legfontosabb tanulság: a LangChain4j biztosítja az infrastruktúrát (modellkapcsolat a [LangChainConfig.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/config/LangChainConfig.java) segítségével, memória, üzenetkezelés a [Gpt5PromptService.java](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/service/Gpt5PromptService.java) által), míg ez a modul megtanítja, hogyan készíts hatékony promptokat ezen infrastruktúrán belül.

## Az alapvető minták

Nem minden problémához ugyanaz a megközelítés kell. Egyes kérdések gyors válaszokat igényelnek, mások mély gondolkodást. Egyesek látható érvelést, mások csak eredményeket. Ez a modul nyolc promptolási mintát fed le – mindegyik más helyzetekre optimalizálva. Mindegyikkel kísérletezni fogsz, hogy megtanuld, mikor melyik működik a legjobban.

<img src="../../../translated_images/hu/eight-patterns.fa1ebfdf16f71e9a.png" alt="Eight Prompting Patterns" width="800"/>

*A nyolc prompt tervezési minta áttekintése és felhasználási eseteik*

<img src="../../../translated_images/hu/reasoning-effort.db4a3ba5b8e392c1.png" alt="Reasoning Effort Comparison" width="800"/>

*Alacsony lelkesedés (gyors, közvetlen) vs Magas lelkesedés (alapos, feltáró) érvelési megközelítések*

**Alacsony lelkesedés (Gyors & fókuszált)** – Egyszerű kérdésekhez, ahol gyors, közvetlen válaszokat szeretnél. A modell minimális érvelést végez – maximum 2 lépés. Használd számításokhoz, lekérdezésekhez vagy egyenes kérdésekhez.

```java
String prompt = """
    <reasoning_effort>low</reasoning_effort>
    <instruction>maximum 2 reasoning steps</instruction>
    
    What is 15% of 200?
    """;

String response = chatModel.chat(prompt);
```
  
> 💡 **Fedezd fel a GitHub Copilot-tal:** Nyisd meg a [`Gpt5PromptService.java`](../../../02-prompt-engineering/src/main/java/com/example/langchain4j/prompts/service/Gpt5PromptService.java) fájlt, és kérdezd meg:  
> - „Mi a különbség az alacsony és magas lelkesedésű promptolási minták között?”  
> - „Hogyan segítik az XML tagek a promptokban az AI válaszának strukturálását?”  
> - „Mikor érdemes önreflektáló mintákat használni a közvetlen utasítás helyett?”

**Magas lelkesedés (Mély & alapos)** – Összetett problémákhoz, ahol átfogó elemzést szeretnél. A modell alaposan feltárja és részletes érvelést mutat. Használd rendszertervezéshez, architektúra döntésekhez vagy komplex kutatáshoz.

```java
String prompt = """
    <reasoning_effort>high</reasoning_effort>
    <instruction>explore thoroughly, show detailed reasoning</instruction>
    
    Design a caching strategy for a high-traffic REST API.
    """;

String response = chatModel.chat(prompt);
```
  
**Feladat végrehajtás (Lépésről lépésre haladás)** – Többlépcsős munkafolyamatokhoz. A modell előre megtervezi, narrálja az egyes lépéseket, majd összefoglal. Használd migrációkhoz, implementációkhoz vagy bármilyen többlépéses folyamathoz.

```java
String prompt = """
    <task>Create a REST endpoint for user registration</task>
    <preamble>Provide an upfront plan</preamble>
    <narration>Narrate each step as you work</narration>
    <summary>Summarize what was accomplished</summary>
    """;

String response = chatModel.chat(prompt);
```
  
A Chain-of-Thought promptolás kifejezetten kéri a modellt, hogy mutassa be érvelési folyamatát, javítva a pontosságot összetett feladatoknál. A lépésről lépésre bontás segíti az embereket és az AI-t is a logika megértésében.

> **🤖 Próbáld ki a [GitHub Copilot](https://github.com/features/copilot) Chat-tel:** Kérdezz erről a mintáról:  
> - „Hogyan adaptálnám a feladat végrehajtási mintát hosszú futású műveletekhez?”  
> - „Mik a legjobb gyakorlatok az eszköz bevezetők strukturálására éles alkalmazásokban?”  
> - „Hogyan lehet köztes előrehaladási frissítéseket rögzíteni és megjeleníteni egy UI-ban?”

<img src="../../../translated_images/hu/task-execution-pattern.9da3967750ab5c1e.png" alt="Task Execution Pattern" width="800"/>

*Tervezés → Végrehajtás → Összefoglalás munkafolyamat többlépéses feladatokhoz*

**Önreflektáló kód** – Termelési minőségű kód generálásához. A modell kódot generál, minőségi kritériumok alapján ellenőrzi, majd iteratívan javítja. Használd új funkciók vagy szolgáltatások építéséhez.

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
  
<img src="../../../translated_images/hu/self-reflection-cycle.6f71101ca0bd28cc.png" alt="Self-Reflection Cycle" width="800"/>

*Iteratív fejlesztési ciklus – generálás, értékelés, problémák azonosítása, javítás, ismétlés*

**Strukturált elemzés** – Következetes értékeléshez. A modell egy rögzített keretrendszer szerint vizsgálja a kódot (helyesség, gyakorlatok, teljesítmény, biztonság). Használd kódellenőrzésekhez vagy minőségértékelésekhez.

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
  
> **🤖 Próbáld ki a [GitHub Copilot](https://github.com/features/copilot) Chat-tel:** Kérdezz a strukturált elemzésről:  
> - „Hogyan testreszabhatom az elemzési keretrendszert különböző típusú kódellenőrzésekhez?”  
> - „Mi a legjobb módja a strukturált kimenet programozott feldolgozásának és végrehajtásának?”  
> - „Hogyan biztosítható a következetes súlyossági szintek alkalmazása különböző ellenőrzési munkamenetekben?”

<img src="../../../translated_images/hu/structured-analysis-pattern.0af3b690b60cf2d6.png" alt="Structured Analysis Pattern" width="800"/>

*Négy kategóriás keretrendszer következetes kódellenőrzésekhez súlyossági szintekkel*

**Többfordulós csevegés** – Kontextust igénylő beszélgetésekhez. A modell emlékszik az előző üzenetekre és épít rájuk. Használd interaktív segítségnyújtáshoz vagy összetett kérdés-válasz helyzetekhez.

```java
ChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

memory.add(UserMessage.from("What is Spring Boot?"));
AiMessage aiMessage1 = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage1);

memory.add(UserMessage.from("Show me an example"));
AiMessage aiMessage2 = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage2);
```
  
<img src="../../../translated_images/hu/context-memory.dff30ad9fa78832a.png" alt="Context Memory" width="800"/>

*Hogyan halmozódik fel a beszélgetési kontextus több fordulón át, amíg el nem éri a token limitet*

**Lépésről lépésre érvelés** – Látható logikát igénylő problémákhoz. A modell minden lépéshez explicit érvelést mutat. Használd matematikai feladatokhoz, logikai rejtvényekhez vagy amikor meg kell érteni a gondolkodási folyamatot.

```java
String prompt = """
    <instruction>Show your reasoning step-by-step</instruction>
    
    If a train travels 120 km in 2 hours, then stops for 30 minutes,
    then travels another 90 km in 1.5 hours, what is the average speed
    for the entire journey including the stop?
    """;

String response = chatModel.chat(prompt);
```
  
<img src="../../../translated_images/hu/step-by-step-pattern.a99ea4ca1c48578c.png" alt="Step-by-Step Pattern" width="800"/>

*Problémák explicit logikai lépésekre bontása*

**Korlátozott kimenet** – Olyan válaszokhoz, amelyeknek specifikus formátumkövetelményeik vannak. A modell szigorúan követi a formátum- és hosszúsági szabályokat. Használd összefoglalókhoz vagy amikor pontos kimeneti struktúrára van szükség.

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
  
<img src="../../../translated_images/hu/constrained-output-pattern.0ce39a682a6795c2.png" alt="Constrained Output Pattern" width="800"/>

*Specifikus formátum, hossz és struktúra követelmények érvényesítése*

## Meglévő Azure erőforrások használata

**Telepítés ellenőrzése:**

Győződj meg róla, hogy a `.env` fájl létezik a gyökérkönyvtárban Azure hitelesítő adatokkal (az 01-es modul során létrehozva):  
```bash
cat ../.env  # Meg kell jelenítenie az AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT értékeket
```
  
**Az alkalmazás indítása:**

> **Megjegyzés:** Ha már elindítottad az összes alkalmazást az 01-es modulból a `./start-all.sh` segítségével, ez a modul már fut a 8083-as porton. Kihagyhatod az alábbi indítási parancsokat, és közvetlenül a http://localhost:8083 oldalra léphetsz.

**1. lehetőség: Spring Boot Dashboard használata (ajánlott VS Code felhasználóknak)**

A fejlesztői konténer tartalmazza a Spring Boot Dashboard kiterjesztést, amely vizuális felületet biztosít az összes Spring Boot alkalmazás kezeléséhez. A VS Code bal oldali Activity Bar-jában találod (keresd a Spring Boot ikont).

A Spring Boot Dashboard segítségével:  
- Megtekintheted az összes elérhető Spring Boot alkalmazást a munkaterületen  
- Egy kattintással indíthatod/leállíthatod az alkalmazásokat  
- Valós időben nézheted az alkalmazás naplóit  
- Figyelheted az alkalmazás állapotát

Egyszerűen kattints a lejátszás gombra a „prompt-engineering” mellett, hogy elindítsd ezt a modult, vagy indítsd el egyszerre az összes modult.

<img src="../../../translated_images/hu/dashboard.da2c2130c904aaf0.png" alt="Spring Boot Dashboard" width="400"/>

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
  
Vagy indítsd el csak ezt a modult:

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
  
Mindkét szkript automatikusan betölti a környezeti változókat a gyökér `.env` fájlból, és lefordítja a JAR fájlokat, ha még nem léteznek.

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
  
Nyisd meg a http://localhost:8083 címet a böngésződben.

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
  
## Alkalmazás képernyőképek

<img src="../../../translated_images/hu/dashboard-home.5444dbda4bc1f79d.png" alt="Dashboard Home" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/>

*A fő műszerfal, amely az összes 8 prompt tervezési mintát mutatja jellemzőikkel és felhasználási eseteikkel*

## A minták felfedezése

A webes felület lehetővé teszi, hogy különböző promptolási stratégiákkal kísérletezz. Minden minta más problémákat old meg – próbáld ki őket, hogy lásd, mikor melyik megközelítés működik a legjobban.

### Alacsony vs Magas lelkesedés

Tegyél fel egy egyszerű kérdést, például „Mi 15%-a 200-nak?” Alacsony lelkesedéssel. Azonnali, közvetlen választ kapsz. Most kérdezz valami összetettet, például „Tervezzen egy gyorsítótárazási stratégiát egy nagy forgalmú API-hoz” Magas lelkesedéssel. Figyeld, ahogy a modell lassabban dolgozik és részletes érvelést ad. Ugyanaz a modell, ugyanaz a kérdésfelépítés – de a prompt megmondja, mennyi gondolkodást végezzen.

<img src="../../../translated_images/hu/low-eagerness-demo.898894591fb23aa0.png" alt="Low Eagerness Demo" width="800"/>
*Gyors számítás minimális érveléssel*

<img src="../../../translated_images/hu/high-eagerness-demo.4ac93e7786c5a376.png" alt="High Eagerness Demo" width="800"/>

*Átfogó gyorsítótárazási stratégia (2,8MB)*

### Feladatvégrehajtás (Eszköz bevezetők)

A többlépéses munkafolyamatok előnyösek az előzetes tervezés és a folyamat narrációja szempontjából. A modell vázolja, mit fog tenni, narrálja az egyes lépéseket, majd összefoglalja az eredményeket.

<img src="../../../translated_images/hu/tool-preambles-demo.3ca4881e417f2e28.png" alt="Task Execution Demo" width="800"/>

*REST végpont létrehozása lépésről lépésre narrációval (3,9MB)*

### Önkritikus kód

Próbáld ki a "Hozz létre egy e-mail érvényesítő szolgáltatást" kérést. Ahelyett, hogy csak kódot generálna és megállna, a modell generál, értékel minőségi kritériumok alapján, azonosítja a gyengeségeket, és javít. Láthatod, ahogy iterál, amíg a kód eléri a gyártási szintet.

<img src="../../../translated_images/hu/self-reflecting-code-demo.851ee05c988e743f.png" alt="Self-Reflecting Code Demo" width="800"/>

*Teljes e-mail érvényesítő szolgáltatás (5,2MB)*

### Strukturált elemzés

A kódáttekintésekhez következetes értékelési keretrendszerek szükségesek. A modell rögzített kategóriák (helyesség, gyakorlatok, teljesítmény, biztonság) és súlyossági szintek alapján elemzi a kódot.

<img src="../../../translated_images/hu/structured-analysis-demo.9ef892194cd23bc8.png" alt="Structured Analysis Demo" width="800"/>

*Keretrendszer-alapú kódáttekintés*

### Többfordulós csevegés

Kérdezd meg: "Mi az a Spring Boot?" majd azonnal kövesd a "Mutass egy példát" kérdéssel. A modell emlékszik az első kérdésedre, és kifejezetten egy Spring Boot példát ad. Memória nélkül a második kérdés túl általános lenne.

<img src="../../../translated_images/hu/multi-turn-chat-demo.0d2d9b9a86a12b4b.png" alt="Multi-Turn Chat Demo" width="800"/>

*Kontekstus megőrzése a kérdések között*

### Lépésről lépésre érvelés

Válassz egy matekfeladatot, és próbáld ki mind a Lépésről lépésre érveléssel, mind az Alacsony lelkesedéssel. Az alacsony lelkesedés csak a választ adja meg – gyors, de átláthatatlan. A lépésről lépésre megmutat minden számítást és döntést.

<img src="../../../translated_images/hu/step-by-step-reasoning-demo.12139513356faecd.png" alt="Step-by-Step Reasoning Demo" width="800"/>

*Matekfeladat explicit lépésekkel*

### Korlátozott kimenet

Ha specifikus formátumokra vagy szószámra van szükség, ez a minta szigorú betartást érvényesít. Próbálj meg egy összefoglalót generálni pontosan 100 szóban, felsorolásos formátumban.

<img src="../../../translated_images/hu/constrained-output-demo.567cc45b75da1633.png" alt="Constrained Output Demo" width="800"/>

*Gépi tanulás összefoglaló formátumvezérléssel*

## Amit Valójában Tanulsz

**Az érvelési erőfeszítés mindent megváltoztat**

A GPT-5 lehetővé teszi, hogy a promptjaidon keresztül szabályozd a számítási erőfeszítést. Az alacsony erőfeszítés gyors válaszokat jelent minimális feltárással. A magas erőfeszítés azt jelenti, hogy a modell időt szán a mély gondolkodásra. Megtanulod az erőfeszítést a feladat összetettségéhez igazítani – ne pazarold az időt egyszerű kérdésekre, de ne siess a bonyolult döntéseknél sem.

**A struktúra irányítja a viselkedést**

Észrevetted az XML címkéket a promptokban? Nem díszítésként vannak ott. A modellek megbízhatóbban követik a strukturált utasításokat, mint a szabad szöveget. Ha többlépéses folyamatokra vagy összetett logikára van szükség, a struktúra segít a modellnek nyomon követni, hol tart és mi következik.

<img src="../../../translated_images/hu/prompt-structure.a77763d63f4e2f89.png" alt="Prompt Structure" width="800"/>

*Egy jól strukturált prompt anatómiája világos szakaszokkal és XML-stílusú szervezéssel*

**Minőség önértékeléssel**

Az önreflektáló minták úgy működnek, hogy a minőségi kritériumokat explicit módon megadják. Ahelyett, hogy remélnéd, hogy a modell "jól csinálja", pontosan megmondod neki, mit jelent a "jól": helyes logika, hibakezelés, teljesítmény, biztonság. A modell így képes értékelni a saját kimenetét és javítani. Ez a kódgenerálást lottóból folyamatá változtatja.

**A kontextus véges**

A többfordulós beszélgetések úgy működnek, hogy minden kéréshez tartalmazzák az üzenettörténetet. De van egy határ – minden modellnek van maximális token száma. Ahogy a beszélgetések nőnek, stratégiákra lesz szükséged, hogy megőrizd a releváns kontextust anélkül, hogy elérnéd a plafont. Ez a modul megmutatja, hogyan működik a memória; később megtanulod, mikor foglalj össze, mikor felejts, és mikor hívj elő.

## Következő lépések

**Következő modul:** [03-rag - RAG (Retrieval-Augmented Generation)](../03-rag/README.md)

---

**Navigáció:** [← Előző: 01-es modul - Bevezetés](../01-introduction/README.md) | [Vissza a főoldalra](../README.md) | [Következő: 03-as modul - RAG →](../03-rag/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Jogi nyilatkozat**:
Ezt a dokumentumot az AI fordító szolgáltatás, a [Co-op Translator](https://github.com/Azure/co-op-translator) segítségével fordítottuk le. Bár a pontosságra törekszünk, kérjük, vegye figyelembe, hogy az automatikus fordítások hibákat vagy pontatlanságokat tartalmazhatnak. Az eredeti dokumentum az anyanyelvén tekintendő hiteles forrásnak. Fontos információk esetén professzionális emberi fordítást javaslunk. Nem vállalunk felelősséget a fordítás használatából eredő félreértésekért vagy félreértelmezésekért.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->