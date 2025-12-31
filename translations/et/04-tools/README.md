<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-31T07:16:45+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "et"
}
-->
# Moodul 04: AI-agendid tööriistadega

## Sisukord

- [Mida õpid](../../../04-tools)
- [Eeltingimused](../../../04-tools)
- [AI-agentide mõistmine tööriistadega](../../../04-tools)
- [Kuidas tööriistade kutsumine töötab](../../../04-tools)
  - [Tööriistade määratlused](../../../04-tools)
  - [Otsuste tegemine](../../../04-tools)
  - [Täideviimine](../../../04-tools)
  - [Vastuse genereerimine](../../../04-tools)
- [Tööriistade ahelad](../../../04-tools)
- [Rakenduse käivitamine](../../../04-tools)
- [Rakenduse kasutamine](../../../04-tools)
  - [Proovi lihtsat tööriista kasutust](../../../04-tools)
  - [Testi tööriistade ahelamist](../../../04-tools)
  - [Vaata vestluse kulgu](../../../04-tools)
  - [Jälgi põhjendamist](../../../04-tools)
  - [Katseta erinevaid päringuid](../../../04-tools)
- [Põhimõisted](../../../04-tools)
  - [ReAct mudel (Mõtlemine ja tegutsemine)](../../../04-tools)
  - [Tööriistakirjeldused loevad](../../../04-tools)
  - [Seansihaldus](../../../04-tools)
  - [Vea käitlemine](../../../04-tools)
- [Saadaval olevad tööriistad](../../../04-tools)
- [Millal kasutada tööriistapõhiseid agente](../../../04-tools)
- [Järgmised sammud](../../../04-tools)

## Mida õpid

Siiani oled õppinud, kuidas AI-ga vestelda, kuidas efektiivselt prompte struktureerida ja kuidas vastuseid oma dokumentidesse toetada. Kuid on üks põhimõtteline piirang: keelemudelid suudavad ainult teksti genereerida. Nad ei saa kontrollida ilma, teha kalkulatsioone, pärida andmebaase ega suhelda välistes süsteemidega.

Tööriistad muudavad seda. Kui annad mudelile juurdepääsu funktsioonidele, mida ta saab kutsuda, muutub see tekstigeneraatorist agentiks, kes suudab tegevusi ette võtta. Mudel otsustab, millal tal on vaja tööriista, millist tööriista kasutada ja milliseid parameetreid edastada. Sinu kood täidab funktsiooni ja tagastab tulemuse. Mudel lisab selle tulemuse oma vastusesse.

## Eeltingimused

- Läbitud Moodul 01 (Azure OpenAI ressursid juurutatud)
- `.env` fail juurkataloogis koos Azure'i volitustega (loodud `azd up` abil Moodulis 01)

> **Märkus:** Kui sa pole Moodulit 01 lõpetanud, järgi esmalt seal toodud juurutusjuhiseid.

## AI-agentide mõistmine tööriistadega

> **📝 Märkus:** Selles moodulis tähendab termin "agendid" AI-assistedente, millel on tööriistade kutsumise võimekus. See erineb **Agentic AI** mustritest (autonoomsed agendid planeerimise, mäluga ja mitmeastmelise põhjendamisega), mida käsitleme [Moodul 05: MCP](../05-mcp/README.md).

AI-agent, kellel on tööriistad, järgib mõtlemise ja tegutsemise mustrit (ReAct):

1. Kasutaja esitab küsimuse
2. Agent mõtleb, mida tal on vaja teada
3. Agent otsustab, kas vastamiseks on vaja tööriista
4. Kui jah, kutsub agent sobiva tööriista õige parameetriga
5. Tööriist täidab ja tagastab andmed
6. Agent lisab tulemuse ja annab lõpliku vastuse

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.et.png" alt="ReAct-muster" width="800"/>

*ReAct-muster – kuidas AI-agendid vaheldumisi mõtlevad ja tegutsevad, et lahendada probleeme*

See toimub automaatselt. Sina määratled tööriistad ja nende kirjeldused. Mudel hoolitseb otsustusprotsessi eest, millal ja kuidas neid kasutada.

## Kuidas tööriistade kutsumine töötab

**Tööriistade määratlused** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Sa määratled funktsioonid selgete kirjelduste ja parameetri-spetsifikatsioonidega. Mudel näeb neid kirjeldusi oma süsteemiprompts ja mõistab, mida iga tööriist teeb.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Teie ilmapäringu loogika
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Abiline on Spring Booti poolt automaatselt konfigureeritud järgmistega:
// - ChatModel bean
// - Kõik @Tool meetodid @Component klassidest
// - ChatMemoryProvider seansi haldamiseks
```

> **🤖 Proovi [GitHub Copilot](https://github.com/features/copilot) Chatiga:** Ava [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) ja küsi:
> - "Kuidas integreerida tegelikku ilmaapi-d nagu OpenWeatherMap asemel näidandmete kasutamist?"
> - "Mis teeb hea tööriistakirjelduse, mis aitab AI-l seda õigesti kasutada?"
> - "Kuidas ma peaksin tööriistaimplementatsioonides API-vigu ja päringute limiite käsitlema?"

**Otsuste tegemine**

Kui kasutaja küsib "Mis on ilm Seattles?", märkab mudel, et tal on vaja WeatherTool'i. Ta genereerib funktsiooni kutsumise koos location parameetriga "Seattle".

**Täideviimine** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot ühendab deklaratiivse `@AiService` liidese automaatselt kõigi registreeritud tööriistadega ning LangChain4j täidab tööriistade kutsed automaatselt.

> **🤖 Proovi [GitHub Copilot](https://github.com/features/copilot) Chatiga:** Ava [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) ja küsi:
> - "Kuidas ReAct-muster töötab ja miks see on AI-agentide jaoks tõhus?"
> - "Kuidas agent otsustab, millist tööriista kasutada ja mis järjekorras?"
> - "Mis juhtub, kui tööriista täideviimine nurjub – kuidas vigu robustselt käsitleda?"

**Vastuse genereerimine**

Mudel saab ilmainfo ja vormindab selle loomulikku keelde vastuseks kasutajale.

### Miks kasutada deklaratiivseid AI-teenuseid?

See moodul kasutab LangChain4j Spring Boot integreerimist deklaratiivsete `@AiService` liidestega:

- **Spring Boot auto-wiring** - ChatModel ja tööriistad süstivad ennast automaatselt
- **@MemoryId muster** - Automaatne seansipõhine mäluhaldus
- **Üksik eksemplar** - Assistent luuakse kord ja taaskasutatakse parema jõudluse jaoks
- **Tüübitundlik täideviimine** - Java meetodid kutsutakse otse koos tüüpkonversiooniga
- **Mitme-pöörde orkestreerimine** - Haldab tööriistade ahelaid automaatselt
- **Null boilerplate** - Pole vaja käsitsi AiServices.builder() kutseid ega mäluhashide haldamist

Alternatiivsed lähenemised (manuaalne `AiServices.builder()`) nõuavad rohkem koodi ja jäävad ilma Spring Boot integreerimise eelistest.

## Tööriistade ahelad

**Tööriistade ahelad** - AI võib kutsuda mitut tööriista järjestikku. Küsi "Mis on ilm Seattles ja kas mul peaks olema vihmavari?" ja vaata, kuidas ta seob `getCurrentWeather` ning põhjendab vihmavarju vajaolekut.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.et.png" alt="Tool Chaining" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Järjestikused tööriistakutsed – ühe tööriista väljund mõjutab järgmist otsust*

**Sujuvalt hallatavad tõrked** - Küsi ilma kohta linnas, mis ei ole näidandmete hulgas. Tööriist tagastab veateate ja AI selgitab, et ta ei saa aidata. Tööriistad ebaõnnestuvad turvaliselt.

See toimub ühe vestlusvahe korral. Agent orkestreerib mitu tööriistakutset autonoomselt.

## Rakenduse käivitamine

**Juurutuse kontrollimine:**

Veendu, et `.env` fail on juurkataloogis Azure'i volitustega (loodud Moodulis 01):
```bash
cat ../.env  # Tuleb kuvada AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Käivita rakendus:**

> **Märkus:** Kui sa juba käivitasid kõik rakendused kasutades `./start-all.sh` Moodulist 01, töötab see moodul juba pordil 8084. Sa võid allolevad käivituskäsud vahele jätta ja minna otse aadressile http://localhost:8084.

**Valik 1: Spring Boot Dashboardi kasutamine (soovitatav VS Code kasutajatele)**

Dev konteiner sisaldab Spring Boot Dashboard laiendust, mis pakub visuaalset liidest kõigi Spring Boot rakenduste haldamiseks. Leiad selle VS Code'i tegevusribalt vasakul (otsi Spring Boot ikooni).

Spring Boot Dashboardist saad:
- Näha kõiki tööruumi Spring Boot rakendusi
- Käivitada/peatada rakendusi ühe klikiga
- Vaadata rakenduste logisid reaalajas
- Jälgida rakenduste olekut

Lihtsalt klõpsa "tools" kõrval mängimisnupul, et käivitada see moodul või käivita korraga kõik moodulid.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.et.png" alt="Spring Booti juhtpaneel" width="400"/>

**Valik 2: Shell-skriptide kasutamine**

Käivita kõik veebirakendused (moodulid 01-04):

**Bash:**
```bash
cd ..  # juurkataloogist
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Juurekataloogist
.\start-all.ps1
```

Või käivita ainult see moodul:

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

Mõlemad skriptid laadivad automaatselt keskkonnamuutujad juurdepääsufailist `.env` ja ehitavad JAR-id, kui neid pole olemas.

> **Märkus:** Kui eelistad enne käivitamist kõik moodulid käsitsi ehitada:
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

Ava brauseris aadress http://localhost:8084.

**Peatamine:**

**Bash:**
```bash
./stop.sh  # Ainult see moodul
# Või
cd .. && ./stop-all.sh  # Kõik moodulid
```

**PowerShell:**
```powershell
.\stop.ps1  # Ainult see moodul
# Või
cd ..; .\stop-all.ps1  # Kõik moodulid
```

## Rakenduse kasutamine

Rakendus pakub veebiliidest, kus saad suhelda AI-agentiga, millel on juurdepääs ilma- ja temperatuuri konverteerimise tööriistadele.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.et.png" alt="AI-agendi tööriistaliides" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI-agendi tööriistaliides – kiirnäited ja vestlusliides tööriistadega suhtlemiseks*

**Proovi lihtsat tööriista kasutust**

Alusta lihtsa päringuga: "Konverteeri 100 kraadi Fahrenheiti Celsiuseks". Agent tuvastab, et tal on vaja temperatuuri konverteerimise tööriista, kutsub selle õige parameetriga ja tagastab tulemuse. Märka, kui loomulik see on – sa ei pidanud täpsustama, millist tööriista kasutada ega kuidas seda kutsuda.

**Testi tööriistade ahelamist**

Proovi nüüd midagi keerukamat: "Mis on ilm Seattles ja konverteeri see Fahrenheiti?" Vaata, kuidas agent töötab samm-sammult: esmalt hangib ilma (tagastab Celsiuse), märkab vajadust konverteerida Fahrenheiti, kutsub konverteerimistööriista ja ühendab mõlemad tulemused üheks vastuseks.

**Vaata vestluse kulgu**

Vestlusliides hoiab vestluse ajalugu, võimaldades mitme-pöörde suhtlust. Sa näed kõiki varasemaid päringuid ja vastuseid, mis teeb lihtsaks vestluse jälgimise ja selle mõistmise, kuidas agent konteksti ehitab mitme vahetuse jooksul.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.et.png" alt="Vestlus mitme tööriistakutsuga" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Mitme-pöörde vestlus, mis näitab lihtsaid konversioone, ilmaotsinguid ja tööriistade ahelamist*

**Katseta erinevate päringutega**

Proovi erinevaid kombinatsioone:
- Ilmaotsingud: "Mis on ilm Tokyos?"
- Temperatuuri konversioonid: "Mis on 25°C kelvinites?"
- Kombineeritud päringud: "Kontrolli ilma Pariisis ja ütle, kas see on üle 20°C"

Märka, kuidas agent tõlgendab loomulikku keelt ja kaardistab selle sobivatele tööriistakutsudele.

## Põhimõisted

**ReAct mudel (Mõtlemine ja tegutsemine)**

Agent vaheldab mõtlemist (otsustab, mida teha) ja tegutsemist (kasutab tööriistu). See muster võimaldab autonoomset probleemilahendust, mitte ainult instruktsioonidele vastamist.

**Tööriistakirjeldused loevad**

Sinu tööriistakirjelduste kvaliteet mõjutab otseselt seda, kui hästi agent neid kasutab. Selged ja konkreetsed kirjeldused aitavad mudelil mõista, millal ja kuidas iga tööriista kutsuda.

**Seansihaldus**

`@MemoryId` annotatsioon võimaldab automaatset seansipõhist mäluhaldust. Iga seansi ID jaoks luuakse oma `ChatMemory` eksemplar, mida haldab `ChatMemoryProvider` bean, elimineerides vajaduse manuaalse mälukorralduse järele.

**Vea käitlemine**

Tööriistad võivad ebaõnnestuda – API-d aeguvad, parameetrid võivad olla vigased, välist teenust ei pruugi olla saadaval. Tootmisagentidel on vaja vea käitlemist, et mudel saaks seletada probleeme või proovida alternatiive.

## Saadaval olevad tööriistad

**Ilmatööriistad** (näidandmed demonstreerimiseks):
- Aktuaalse ilma päring asukoha järgi
- Mitmepäevane prognoos

**Temperatuuri konverteerimise tööriistad**:
- Celsiuse -> Fahrenheiti
- Fahrenheiti -> Celsiuse
- Celsiuse -> Kelvini
- Kelvini -> Celsiuse
- Fahrenheiti -> Kelvini
- Kelvini -> Fahrenheiti

Need on lihtsad näited, kuid muster laiendub mis tahes funktsioonile: andmebaasi päringud, API-kutsed, kalkulatsioonid, failitoimingud või süsteemikäsklused.

## Millal kasutada tööriistapõhiseid agente

**Kasuta tööriistu, kui:**
- Vastamiseks on vaja reaalajas andmeid (ilm, aktsiahinnad, laoseis)
- Tuleb teha kalkulatsioone, mis ületavad lihtsat matemaatikat
- Vajatakse ligipääsu andmebaasidele või API-dele
- Sooritatakse toiminguid (sähvata e-kiri, loo pilet, uuenda kirjeid)
- Kombineeritakse mitu andmeallikat

**Ära kasuta tööriistu, kui:**
- Küsimustele saab vastata üldteadmiste põhjal
- Vastus on puhtalt vestluslik
- Tööriista latentsus muudaks kasutuskogemuse liiga aeglaseks

## Järgmised sammud

**Järgmine moodul:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigeerimine:** [← Eelmine: Moodul 03 - RAG](../03-rag/README.md) | [Tagasi peamenuusse](../README.md) | [Järgmine: Moodul 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Vastutusest loobumine:
See dokument on tõlgitud tehisintellektil põhineva tõlketeenuse [Co-op Translator](https://github.com/Azure/co-op-translator) abil. Kuigi püüame tagada täpsust, palume arvestada, et automatiseeritud tõlked võivad sisaldada vigu või ebatäpsusi. Originaaldokumenti selle emakeeles tuleks pidada autoriteetseks allikaks. Olulise teabe korral soovitatakse kasutada professionaalset inimtõlget. Me ei vastuta selle tõlke kasutamisest tulenevate arusaamatuste ega valesti tõlgenduste eest.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->