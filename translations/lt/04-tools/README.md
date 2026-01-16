<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-06T01:50:34+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "lt"
}
-->
# Modulis 04: DI agentai su įrankiais

## Turinys

- [Ko išmoksite](../../../04-tools)
- [Reikalavimai](../../../04-tools)
- [DI agentų su įrankiais supratimas](../../../04-tools)
- [Kaip veikia įrankių kvietimas](../../../04-tools)
  - [Įrankių apibrėžimai](../../../04-tools)
  - [Sprendimų priėmimas](../../../04-tools)
  - [Vykdymas](../../../04-tools)
  - [Atsakymo generavimas](../../../04-tools)
- [Įrankių grandinimas](../../../04-tools)
- [Paleisti programą](../../../04-tools)
- [Programos naudojimas](../../../04-tools)
  - [Išbandykite paprastą įrankių naudojimą](../../../04-tools)
  - [Išbandykite įrankių grandinimą](../../../04-tools)
  - [Peržiūrėkite pokalbio eigą](../../../04-tools)
  - [Eksperimentuokite su skirtingais prašymais](../../../04-tools)
- [Pagrindinės sąvokos](../../../04-tools)
  - [ReAct modelis (samprotavimas ir veikimas)](../../../04-tools)
  - [Įrankių aprašymai yra svarbūs](../../../04-tools)
  - [Sesijų valdymas](../../../04-tools)
  - [Klaidų tvarkymas](../../../04-tools)
- [Turimi įrankiai](../../../04-tools)
- [Kada naudoti agentus su įrankiais](../../../04-tools)
- [Tolimesni žingsniai](../../../04-tools)

## Ko išmoksite

Iki šiol jūs išmokote bendrauti su DI, efektyviai struktūruoti užklausas ir pagrįsti atsakymus savo dokumentuose. Tačiau yra esminė riba: kalbos modeliai gali tik generuoti tekstą. Jie negali patikrinti oro sąlygų, atlikti skaičiavimų, užklausti duomenų bazių ar sąveikauti su išorinėmis sistemomis.

Įrankiai tai keičia. Pateikdami modeliui funkcijų, kurias jis gali kviesti, paverčiate jį iš teksto generatoriaus į agentą, kuris gali imtis veiksmų. Modelis nusprendžia, kada jam reikia įrankio, kurį įrankį naudoti ir kokius parametrus perduoti. Jūsų kodas vykdo funkciją ir grąžina rezultatą. Modelis įtraukia tą rezultatą į savo atsakymą.

## Reikalavimai

- Baigtas Modulis 01 (įdiegti Azure OpenAI resursai)
- `.env` failas pagrindiniame kataloge su Azure kredencialais (sukurtas vykdant `azd up` Modulyje 01)

> **Pastaba:** Jei nepabaigėte Modulio 01, pirmiausia laikykitės ten pateiktų diegimo instrukcijų.

## DI agentų su įrankiais supratimas

> **📝 Pastaba:** Šiame modulyje terminas „agentai“ reiškia DI asistentus, papildytus galimybe kviesti įrankius. Tai skiriasi nuo **Agentinio DI** modelių (autonomi agentai su planavimu, atmintimi ir daugiasluoksniu samprotavimu), kuriuos aptarsime [Modulyje 05: MCP](../05-mcp/README.md).

DI agentas su įrankiais naudoja samprotavimo ir veikimo modelį (ReAct):

1. Vartotojas užduoda klausimą  
2. Agentas mąsto, ką reikia sužinoti  
3. Agentas nusprendžia, ar jam reikia įrankio atsakymui  
4. Jei taip, agentas kviečia tinkamą įrankį su teisingais parametrais  
5. Įrankis vykdo ir grąžina duomenis  
6. Agentas įtraukia rezultatą ir pateikia galutinį atsakymą  

<img src="../../../translated_images/lt/react-pattern.86aafd3796f3fd13.png" alt="ReAct modelis" width="800"/>

*ReAct modelis – kaip DI agentai keičiasi tarp samprotavimo ir veikimo sprendžiant problemas*

Tai vyksta automatiškai. Jūs aprašote įrankius ir jų aprašymus. Modelis sprendžia, kada ir kaip juos naudoti.

## Kaip veikia įrankių kvietimas

### Įrankių apibrėžimai

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Jūs apibrėžiate funkcijas su aiškiais aprašymais ir parametrų specifikacijomis. Modelis mato tuos aprašymus sistemos užklausoje ir supranta, ką kiekvienas įrankis daro.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Jūsų oro sąlygų paieškos logika
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Asistentas automatiškai sujungtas per Spring Boot su:
// - ChatModel sluoksniu
// - Visais @Tool metodais iš @Component klasės
// - ChatMemoryProvider sesijų valdymui
```

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) Chat:** Atidarykite [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) ir paklauskite:
> - „Kaip integruoti tikrą orų API, pvz., OpenWeatherMap, vietoje duomenų pavyzdžių?“
> - „Kas sudaro gerą įrankio aprašymą, padedantį DI tinkamai jį naudoti?“
> - „Kaip tvarkyti API klaidas ir kvotų ribojimus įrankių įgyvendinime?“

### Sprendimų priėmimas

Kai vartotojas klausia „Koks oras Sietle?“, modelis supranta, kad reikia naudoti orų įrankį. Jis generuoja funkcijos kvietimą su parametru vieta: „Seattle“.

### Vykdymas

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot automatiškai sujungia deklaratyvų `@AiService` sąsają su visais registruotais įrankiais, o LangChain4j vykdo įrankių kvietimus automatiškai.

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) Chat:** Atidarykite [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) ir paklauskite:
> - „Kaip veikia ReAct modelis ir kodėl jis efektyvus DI agentams?“
> - „Kaip agentas nusprendžia, kurį įrankį naudoti ir kokia tvarka?“
> - „Kas nutinka, jei įrankio vykdymas nepavyksta – kaip tvarkingai tvarkyti klaidas?“

### Atsakymo generavimas

Modelis gauna orų duomenis ir suformuoja juos natūralios kalbos atsakymu vartotojui.

### Kodėl naudoti deklaratyvius DI servisus?

Šiame modulyje naudojama LangChain4j Spring Boot integracija su deklaratyvia `@AiService` sąsaja:

- **Spring Boot automatinis sujungimas** – ChatModel ir įrankiai įkeliami automatiškai  
- **@MemoryId modelis** – Automatinis atminties valdymas pagal sesiją  
- **Vienas egzempliorius** – Asistentas sukuriamas vieną kartą ir pakartotinai naudojamas dėl geresnio našumo  
- **Tipų saugus vykdymas** – Java metodai kviečiami tiesiogiai su tipų konversija  
- **Kelių žingsnių koordinavimas** – Įrankių grandinimas veikia automatiškai  
- **Nulinis boilerplate** – Nereikia rankinių AiServices.builder() kvietimų ar atminties HashMap  

Alternatyvūs būdai (rankinis `AiServices.builder()`) reikalauja daugiau kodo ir praranda Spring Boot integracijos privalumus.

## Įrankių grandinimas

**Įrankių grandinimas** – DI gali kviesti kelis įrankius paeiliui. Užduokite klausimą „Koks oras Sietle ir ar turėčiau pasiimti skėčio?“ ir stebėkite, kaip jis sujungia `getCurrentWeather` ir samprotauja apie lietaus reikmenis.

<a href="images/tool-chaining.png"><img src="../../../translated_images/lt/tool-chaining.3b25af01967d6f7b.png" alt="Įrankių grandinimas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sekos įrankių kvietimai – vieno įrankio išvestis tampa kito sprendimo pagrindu*

**Graceful Failures** – Paklauskite apie orą mieste, kurio nėra duomenų pavyzdyje. Įrankis grąžina klaidos pranešimą, o DI paaiškina, kad negali padėti. Įrankiai gedimus tvarko saugiai.

Tai vyksta viename pokalbio žingsnyje. Agentas savarankiškai koordinuoja kelis įrankių kvietimus.

## Paleisti programą

**Patikrinkite diegimą:**

Įsitikinkite, kad pagrindiniame kataloge yra `.env` failas su Azure kredencialais (sukurtas Modulyje 01):
```bash
cat ../.env  # Turėtų rodyti AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Paleiskite programą:**

> **Pastaba:** Jei jau paleidote visas programas naudodami `./start-all.sh` Modulyje 01, šis modulis jau veikia 8084 porte. Galite praleisti paleidimo komandas ir eiti tiesiai į http://localhost:8084.

**1 variantas: Naudojant Spring Boot Dashboard (rekomenduojama VS Code vartotojams)**

Kūrimo konteineryje yra Spring Boot Dashboard plėtinys, suteikiantis vizualią sąsają valdyti visas Spring Boot programas. Jį rasite VS Code kairėje Activity Bar (ieškokite Spring Boot ikonos).

Per Spring Boot Dashboard galite:
- Matyti visas darbo aplinkoje esančias Spring Boot programas  
- Vienu mygtuku paleisti/stabdyti programas  
- Stebėti programų žurnalus realiu laiku  
- Stebėti programų būseną  

Tiesiog spustelėkite paleidimo mygtuką prie „tools“, kad pradėtumėte šį modulį, arba paleiskite visus modulius iš karto.

<img src="../../../translated_images/lt/dashboard.9b519b1a1bc1b30a.png" alt="Spring Boot Dashboard" width="400"/>

**2 variantas: Naudojant komandinės eilutės scenarijus**

Paleiskite visas internetines programas (moduliai 01-04):

**Bash:**
```bash
cd ..  # Iš šakninių katalogų
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
cd 04-tools
./start.sh
```

**PowerShell:**
```powershell
cd 04-tools
.\start.ps1
```

Abi scenarijai automatiškai įkelia aplinkos kintamuosius iš pagrindinio `.env` failo ir sukurs JAR bylas, jei jų dar nėra.

> **Pastaba:** Jei norite visas programas sukompiliuoti rankiniu būdu prieš paleidimą:
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

Atidarykite http://localhost:8084 naršyklėje.

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

## Programos naudojimas

Programa suteikia žiniatinklio sąsają, kur galite bendrauti su DI agentu, turinčiu prieigą prie orų ir temperatūros konvertavimo įrankių.

<a href="images/tools-homepage.png"><img src="../../../translated_images/lt/tools-homepage.4b4cd8b2717f9621.png" alt="DI agentų įrankių sąsaja" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*DI agentų įrankių sąsaja – greiti pavyzdžiai ir pokalbio sąsaja interakcijai su įrankiais*

### Išbandykite paprastą įrankių naudojimą

Pradėkite nuo paprasto prašymo: „Konvertuokite 100 laipsnių Farenheito į Celsijų“. Agentas supranta, kad reikia temperatūros konversijos įrankio, kviečia jį su tinkamais parametrais ir pateikia rezultatą. Pastebėkite, kaip natūraliai tai vyksta – jūs nenurodėte, kurį įrankį naudoti ar kaip jį kviesti.

### Išbandykite įrankių grandinimą

Dabar pabandykite kažką sudėtingesnio: „Koks oras Sietle ir konvertuokite jį į Farenheitą?“ Stebėkite, kaip agentas dirba etapais. Jis pirmiausia gauna orą (kuri pateikiama Celsijais), supranta, kad reikia konversijos į Farenheitą, kviečia konversijos įrankį ir sujungia abu rezultatus į vieną atsakymą.

### Peržiūrėkite pokalbio eigą

Pokalbio sąsaja palaiko pokalbio istoriją, leidžianti turėti kelių žingsnių bendravimą. Galite matyti visus ankstesnius klausimus ir atsakymus, todėl lengviau sekti pokalbį ir suprasti, kaip agentas kuria kontekstą per kelis mainus.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/lt/tools-conversation-demo.89f2ce9676080f59.png" alt="Pokalbis su keliais įrankių kvietimais" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Daugiakartinis pokalbis, rodantis paprastus konvertavimus, orų užklausas ir įrankių grandinimą*

### Eksperimentuokite su skirtingais prašymais

Išbandykite įvairius derinius:  
- Orų užklausos: „Koks oras Tokijuje?“  
- Temperatūros konversijos: „Kiek yra 25 °C Kelvinuose?“  
- Kombinuotos užklausos: „Patikrink orą Paryžiuje ir pasakyk, ar jis viršija 20 °C“  

Pastebėkite, kaip agentas interpretuoja natūralią kalbą ir suveda ją į tinkamus įrankių kvietimus.

## Pagrindinės sąvokos

### ReAct modelis (samprotavimas ir veikimas)

Agentas keičiasi tarp samprotavimo (sprendžia, ką daryti) ir veikimo (naudoja įrankius). Šis modelis leidžia autonomiškai spręsti problemas, o ne tik vykdyti nurodymus.

### Įrankių aprašymai yra svarbūs

Jūsų įrankių aprašymų kokybė tiesiogiai lemia, kaip gerai agentas juos naudoja. Aiškūs, konkretūs aprašymai padeda modeliui suprasti, kada ir kaip kviesti kiekvieną įrankį.

### Sesijų valdymas

`@MemoryId` anotacija leidžia automatiškai valdyti atmintį pagal sesiją. Kiekvienam sesijos ID sukuriama atskira `ChatMemory` instancija, valdoma `ChatMemoryProvider` komponento, todėl nereikia rankiniu būdu stebėti atminties.

### Klaidų tvarkymas

Įrankiai gali sugesti – API užtrunka, parametrai netinkami, išorinės paslaugos neprieinamos. Gamybos agentams reikia klaidų tvarkymo, kad modelis galėtų paaiškinti problemas arba bandyti alternatyvas.

## Turimi įrankiai

**Oro sąlygų įrankiai** (duomenų pavyzdžiai demonstravimui):  
- Gauti dabartines oro sąlygas vietovėje  
- Gauti kelių dienų orų prognozę  

**Temperatūros konversijos įrankiai:**  
- Celsijus į Farenheitą  
- Farenheitas į Celsijų  
- Celsijus į Kelvino laipsnius  
- Kelvino laipsniai į Celsijų  
- Farenheitas į Kelvino laipsnius  
- Kelvino laipsniai į Farenheitą  

Tai paprasti pavyzdžiai, tačiau modelį galima pritaikyti bet kokiai funkcijai: duomenų užklausoms, API kvietimams, skaičiavimams, failų operacijoms ar sisteminiams komandų vykdymams.

## Kada naudoti agentus su įrankiais

**Naudokite įrankius, kai:**  
- Atsakymas reikalauja realaus laiko duomenų (oras, akcijų kainos, inventorius)  
- Reikia atlikti skaičiavimus sudėtingesnius nei paprasti matematiniai  
- Reikia prieigos prie duomenų bazių ar API  
- Reikia atlikti veiksmus (siųsti el. laiškus, kurti užklausas, atnaujinti įrašus)  
- Reikia sujungti kelis duomenų šaltinius  

**Nenaudokite įrankių, kai:**  
- Klausimai gali būti atsakyti iš bendrų žinių  
- Atsakymas yra tik pokalbio forma  
- Įrankių vėlavimas padarytų patirtį per lėtą  

## Tolimesni žingsniai

**Kitas modulis:** [05-mcp – Modelio konteksto protokolas (MCP)](../05-mcp/README.md)

---

**Navigacija:** [← Ankstesnis: Modulis 03 – RAG](../03-rag/README.md) | [Atgal į pradžią](../README.md) | [Toliau: Modulis 05 – MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Atsakomybės apribojimas**:  
Šis dokumentas buvo išverstas naudojant dirbtinio intelekto vertimo paslaugą [Co-op Translator](https://github.com/Azure/co-op-translator). Nors siekiame tikslumo, atkreipkite dėmesį, kad automatiniai vertimai gali turėti klaidų ar netikslumų. Pirminis dokumentas jo gimtąja kalba turėtų būti laikomas autoritetingu šaltiniu. Kritinės informacijos atveju rekomenduojamas profesionalus žmogiškasis vertimas. Mes neatsakome už bet kokius nesusipratimus ar neteisingus interpretavimus, kilusius naudojant šį vertimą.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->