<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-06T00:47:32+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "hu"
}
-->
# 04-es modul: AI-ügynökök eszközökkel

## Tartalomjegyzék

- [Mit fogsz megtanulni](../../../04-tools)
- [Előfeltételek](../../../04-tools)
- [AI-ügynökök eszközökkel megértése](../../../04-tools)
- [Hogyan működik az eszközhívás](../../../04-tools)
  - [Eszközdefiníciók](../../../04-tools)
  - [Döntéshozatal](../../../04-tools)
  - [Végrehajtás](../../../04-tools)
  - [Válaszgenerálás](../../../04-tools)
- [Eszközláncolás](../../../04-tools)
- [Az alkalmazás futtatása](../../../04-tools)
- [Az alkalmazás használata](../../../04-tools)
  - [Egyszerű eszközhasználat kipróbálása](../../../04-tools)
  - [Eszközláncolás tesztelése](../../../04-tools)
  - [A beszélgetés folyamatának megtekintése](../../../04-tools)
  - [Különböző kérések kipróbálása](../../../04-tools)
- [Kulcsfogalmak](../../../04-tools)
  - [ReAct minta (Érvelés és cselekvés)](../../../04-tools)
  - [Az eszközleírások számítanak](../../../04-tools)
  - [Munkamenet-kezelés](../../../04-tools)
  - [Hibakezelés](../../../04-tools)
- [Elérhető eszközök](../../../04-tools)
- [Mikor használjunk eszközalapú ügynököket](../../../04-tools)
- [Következő lépések](../../../04-tools)

## Mit fogsz megtanulni

Eddig megtanultad, hogyan folytass beszélgetéseket az AI-val, hogyan strukturáld hatékonyan a promptokat, és hogyan alapozd válaszaidat saját dokumentumaidra. De még mindig van egy alapvető korlát: a nyelvi modellek csak szöveget tudnak generálni. Nem tudnak időjárást ellenőrizni, számításokat végezni, adatbázisokat lekérdezni vagy külső rendszerekkel kommunikálni.

Az eszközök megváltoztatják ezt. Ha hozzáférést adsz a modellnek olyan függvényekhez, amelyeket meghívhat, a szöveggenerátorból olyan ügynökké alakul át, amely képes cselekedni. A modell dönt arról, mikor van szüksége eszközre, melyik eszközt használja, és milyen paramétereket ad át. A kódod végrehajtja a hívást és visszaadja az eredményt. A modell beépíti az eredményt a válaszába.

## Előfeltételek

- Az 01-es modul teljesítve (Azure OpenAI erőforrások telepítve)
- `.env` fájl a gyökérkönyvtárban Azure hitelesítő adatokkal (az `azd up` parancs hozza létre az 01-es modul során)

> **Megjegyzés:** Ha még nem végezted el az 01-es modult, először kövesd ott a telepítési útmutatót.

## AI-ügynökök eszközökkel megértése

> **📝 Megjegyzés:** Ebben a modulban az „ügynökök” olyan AI-asszisztenseket jelentenek, amelyeket eszközhívási képességekkel bővítettek ki. Ez eltér az **Agentic AI** mintáktól (autonóm ügynökök tervezéssel, memóriával és többlépéses érveléssel), amelyekkel a [05-mcp modulban](../05-mcp/README.md) foglalkozunk.

Egy eszközökkel rendelkező AI-ügynök a ReAct (Reasoning and Acting) mintát követi:

1. A felhasználó kérdez valamit
2. Az ügynök végiggondolja, mit kell tudnia
3. Az ügynök eldönti, szüksége van-e eszközre a válaszadáshoz
4. Ha igen, az ügynök meghívja a megfelelő eszközt a helyes paraméterekkel
5. Az eszköz végrehajtja a hívást és visszaadja az adatokat
6. Az ügynök beépíti az eredményt és megadja a végső választ

<img src="../../../translated_images/hu/react-pattern.86aafd3796f3fd13.png" alt="ReAct minta" width="800"/>

*A ReAct minta - hogyan váltakoznak az AI-ügynökök az érvelés és cselekvés között a problémamegoldáshoz*

Ez automatikusan történik. Te definiálod az eszközöket és azok leírását. A modell pedig gondoskodik a döntéshozatalról, hogy mikor és hogyan használja őket.

## Hogyan működik az eszközhívás

### Eszközdefiníciók

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Függvényeket definiálsz világos leírásokkal és paraméter specifikációkkal. A modell látja ezeket a leírásokat a rendszerpromptban, és érti, hogy az egyes eszközök mit csinálnak.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Az időjárás lekérdező logikád
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Az asszisztens automatikusan összekapcsolódik a Spring Boot által:
// - ChatModel bean
// - Minden @Tool metódus az @Component osztályokból
// - ChatMemoryProvider a munkamenet kezeléséhez
```

> **🤖 Próbáld ki [GitHub Copilot](https://github.com/features/copilot) chaten:** Nyisd meg a [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) fájlt, és kérdezd:
> - "Hogyan integrálnék egy valós időjárás API-t, például az OpenWeatherMap-et a mock adatok helyett?"
> - "Mi tesz egy jó eszközleírást, ami segíti az AI-t, hogy helyesen használja?"
> - "Hogyan kezelem az API hibákat és az elérések korlátait az eszközimplementációkban?"

### Döntéshozatal

Ha a felhasználó megkérdezi: „Milyen az idő Seattle-ben?”, a modell felismeri, hogy szüksége van az időjárás eszközre. Funkcióhívást generál, ahol a helyszín paraméter „Seattle”.

### Végrehajtás

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

A Spring Boot automatikusan beköti az deklaratív `@AiService` interfészeket az összes regisztrált eszközzel, és a LangChain4j automatikusan végrehajtja az eszközhívásokat.

> **🤖 Próbáld ki [GitHub Copilot](https://github.com/features/copilot) chaten:** Nyisd meg az [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) fájlt, és kérdezd:
> - "Hogyan működik a ReAct minta és miért hatékony az AI-ügynököknél?"
> - "Hogyan dönt az ügynök, melyik eszközt használja és milyen sorrendben?"
> - "Mi történik, ha egy eszköz végrehajtása meghiúsul - hogyan kezeljem robosztusan a hibákat?"

### Válaszgenerálás

A modell megkapja az időjárás adatokat és természetes nyelvű válasz formátumban adja vissza a felhasználónak.

### Miért használjunk deklaratív AI szolgáltatásokat?

Ez a modul a LangChain4j Spring Boot integrációját használja deklaratív `@AiService` interfészekkel:

- **Spring Boot automatikus bekötés** - ChatModel és eszközök automatikusan injektálva
- **@MemoryId minta** - Automatikus munkamenet alapú memória kezelése
- **Egyszeri példány** - Az asszisztens egyszer jön létre, jobb teljesítményért újrafelhasználva
- **Típusbiztos végrehajtás** - A Java metódusok közvetlen hívása típuskonverzióval
- **Többlépéses irányítás** - Automatikusan kezeli az eszközláncolást
- **Zero boilerplate** - Nincs szükség manuális AiServices.builder() hívásokra vagy memória HashMap-re

Alternatív megközelítés (kézi `AiServices.builder()`) több kódot igényel, és hiányoznak a Spring Boot integráció előnyei.

## Eszközláncolás

**Eszközláncolás** – Lehet, hogy az AI több eszközt hív egymás után. Kérdezd meg: „Milyen az idő Seattle-ben és vigyek-e esernyőt?” és figyeld, hogyan láncolja össze a `getCurrentWeather` hívást az eső elleni felszerelésről való mérlegeléssel.

<a href="images/tool-chaining.png"><img src="../../../translated_images/hu/tool-chaining.3b25af01967d6f7b.png" alt="Eszközláncolás" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sorozatos eszközhívások – egy eszköz kimenete a következő döntés bemenete*

**Problematikus hibakezelés** – Kérdezz rá egy város időjárására, amely nincs a mock adatok között. Az eszköz hibát jelez vissza, és az AI elmagyarázza, hogy nem tud segíteni. Az eszközök biztonságosan hibáznak.

Ez egyetlen beszélgetési fordulóban történik. Az ügynök önállóan irányítja a több eszközhívást.

## Az alkalmazás futtatása

**Telepítés ellenőrzése:**

Győződj meg róla, hogy a gyökér `.env` fájl létezik Azure hitelesítő adatokkal (az 01-es modul alatt jön létre):
```bash
cat ../.env  # Meg kellene jeleníteni az AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT értékeket
```

**Az alkalmazás indítása:**

> **Megjegyzés:** Ha már elindítottad az összes alkalmazást az 01-es modulból a `./start-all.sh` segítségével, ez a modul már fut a 8084-es porton. Kihagyhatod az alábbi indító parancsokat és közvetlenül mehetsz a http://localhost:8084 oldalra.

**1. lehetőség: Spring Boot Dashboard használata (ajánlott VS Code felhasználóknak)**

A fejlesztői konténer tartalmazza a Spring Boot Dashboard kiterjesztést, ami vizuális felületet ad az összes Spring Boot alkalmazás kezeléséhez. Megtalálod a VS Code bal oldali Activity Bar-on (nézd meg a Spring Boot ikont).

A Spring Boot Dashboard segítségével:
- Megnézheted az összes elérhető Spring Boot alkalmazást a munkaterületen
- Egy kattintással indíthatsz/leállíthatsz alkalmazásokat
- Valós idejű naplókat böngészhetsz
- Figyelheted az alkalmazás állapotát

Csak kattints a „tools” modul melletti lejátszás gombra, vagy indítsd el az összes modult egyszerre.

<img src="../../../translated_images/hu/dashboard.9b519b1a1bc1b30a.png" alt="Spring Boot Dashboard" width="400"/>

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

Vagy indítsd csak ezt a modult:

**Bash:**
```bash
cd 04-tools
./start.sh
```

**PowerShell:**
```powershell
cd 04-tools
.\start.ps1
```

Mindkét szkript automatikusan betölti a környezeti változókat a gyökér `.env` fájlból és fordítja a JAR-okat, ha még nem léteznek.

> **Megjegyzés:** Ha manuálisan szeretnéd mindegyik modult buildelni az indítás előtt:
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

Nyisd meg a http://localhost:8084 címet a böngésződben.

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

Az alkalmazás webes felületet biztosít, ahol egy AI-ügynökkel kommunikálhatsz, amely hozzáfér időjárás és hőmérséklet-átváltó eszközökhöz.

<a href="images/tools-homepage.png"><img src="../../../translated_images/hu/tools-homepage.4b4cd8b2717f9621.png" alt="AI Agent Tools Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI Agent Tools felület – gyors példák és chat felület az eszközökkel való interakcióhoz*

### Egyszerű eszközhasználat kipróbálása

Kezdj egy egyszerű kéréssel: „Alakítsd át a 100 Fahrenheit fokot Celsiusra”. Az ügynök felismeri, hogy a hőmérséklet-átalakító eszközre van szüksége, meghívja azt a helyes paraméterekkel és visszaadja az eredményt. Figyeld meg, milyen természetes ezt használni – nem kellett megmondani, melyik eszközt használja vagy hogyan hívja meg.

### Eszközláncolás tesztelése

Próbálj ki most egy összetettebb kérést: „Milyen az idő Seattle-ben és alakítsd át Fahrenheitbe?” Nézd, hogyan dolgozik lépésenként. Először lekéri az időjárást (ami Celsiusban van), felismeri, hogy át kell váltani Fahrenheitre, meghívja a konverziós eszközt, majd egyesíti az eredményeket egy válaszba.

### A beszélgetés folyamatának megtekintése

A chat felület megőrzi a beszélgetés előzményeit, így többlépéses interakciókat folytathatsz. Látod az előző lekérdezéseket és válaszokat, könnyű követni a párbeszédet és megérteni, hogyan építi az ügynök a kontextust több cserén keresztül.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/hu/tools-conversation-demo.89f2ce9676080f59.png" alt="Többlépcsős beszélgetés több eszközhívással" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Többlépcsős beszélgetés egyszerű átalakításokkal, időjárás lekérdezéssel és eszközláncolással*

### Különböző kérések kipróbálása

Próbálj ki különféle kombinációkat:
- Időjárás lekérdezések: „Milyen az idő Tokióban?”
- Hőmérséklet átváltások: „Mennyi 25°C Kelvinben?”
- Összetett kérdések: „Nézd meg Párizs időjárását, és mondd meg, hogy 20°C felett van-e”

Figyeld meg, az ügynök hogyan értelmezi a természetes nyelvet és hogyan térképezi le a megfelelő eszközhívásokra.

## Kulcsfogalmak

### ReAct minta (Érvelés és cselekvés)

Az ügynök váltakozik az érvelés (döntés mit kell tenni) és a cselekvés (eszközök használata) között. Ez a minta lehetővé teszi az autonóm problémamegoldást a puszta utasításokra való reagálás helyett.

### Az eszközleírások számítanak

Az eszközleírások minősége közvetlenül meghatározza, milyen jól használja az ügynök azokat. Világos, konkrét leírások segítik a modellt felismerni, mikor és hogyan hívjon meg egy eszközt.

### Munkamenet-kezelés

Az `@MemoryId` annotáció lehetővé teszi az automatikus munkamenet alapú memória kezelést. Minden munkamenet azonosítóhoz külön `ChatMemory` példány tartozik, amit a `ChatMemoryProvider` bean kezel, így nincs szükség manuális memória követésre.

### Hibakezelés

Az eszközök hibázhatnak – időszakos API hibák, érvénytelen paraméterek, külső szolgáltatások leállása. A termelésben használatos ügynököknek hibakezelésre van szükségük, hogy a modell meg tudja magyarázni a problémákat vagy alternatívákat próbáljon.

## Elérhető eszközök

**Időjárás eszközök** (demóhoz mock adatokkal):
- Aktuális időjárás lekérdezése hely szerint
- Többnapos előrejelzés lekérése

**Hőmérséklet átalakító eszközök**:
- Celsius → Fahrenheit
- Fahrenheit → Celsius
- Celsius → Kelvin
- Kelvin → Celsius
- Fahrenheit → Kelvin
- Kelvin → Fahrenheit

Ezek egyszerű példák, de a minta bármilyen funkcióra kiterjeszthető: adatbázis lekérdezések, API hívások, számítások, fájlkezelés vagy rendszerműveletek.

## Mikor használjunk eszközalapú ügynököket

**Használj eszközöket, ha:**
- Valós idejű adatokra van szükség (időjárás, részvényárfolyam, leltár)
- Számításokat kell végezni a sima matekon túl
- Adatbázisokhoz vagy API-khoz akarsz hozzáférni
- Cselekvéseket kell végrehajtani (e-mail küldés, jegyek létrehozása, adatok frissítése)
- Több adatforrást kombinálsz

**Ne használj eszközöket, ha:**
- A kérdés általános ismeretből megválaszolható
- A válasz tisztán beszélgetős jellegű
- Az eszköz késleltetése túl lassúvá tenné a használatot

## Következő lépések

**Következő modul:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigáció:** [← Előző: 03-as modul - RAG](../03-rag/README.md) | [Vissza a főoldalra](../README.md) | [Következő: 05-ös modul - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Jogi nyilatkozat**:  
Ezt a dokumentumot az [Co-op Translator](https://github.com/Azure/co-op-translator) AI fordító szolgáltatás segítségével fordítottuk. Bár törekszünk a pontosságra, kérjük, vegye figyelembe, hogy az automatikus fordítások hibákat vagy pontatlanságokat tartalmazhatnak. Az eredeti dokumentum az anyanyelvén tekintendő hiteles forrásnak. Kritikus információk esetén szakmai emberi fordítást javaslunk. Nem vállalunk felelősséget semmilyen félreértésért vagy félreértelmezésért, amely a fordítás használatából ered.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->