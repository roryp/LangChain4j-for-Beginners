<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-05T23:59:16+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "fi"
}
-->
# Moduuli 04: AI-agentit työkaluilla

## Sisällysluettelo

- [Mitä opit](../../../04-tools)
- [Edellytykset](../../../04-tools)
- [AI-agenttien ymmärtäminen työkaluilla](../../../04-tools)
- [Miten työkalukutsut toimivat](../../../04-tools)
  - [Työkalumääritelmät](../../../04-tools)
  - [Päätöksenteko](../../../04-tools)
  - [Suoritus](../../../04-tools)
  - [Vastauksen generointi](../../../04-tools)
- [Työkaluketjutus](../../../04-tools)
- [Sovelluksen suorittaminen](../../../04-tools)
- [Sovelluksen käyttäminen](../../../04-tools)
  - [Kokeile yksinkertaista työkalun käyttöä](../../../04-tools)
  - [Testaa työkaluketjutusta](../../../04-tools)
  - [Katso keskustelun kulku](../../../04-tools)
  - [Kokeile erilaisia pyyntöjä](../../../04-tools)
- [Keskeiset käsitteet](../../../04-tools)
  - [ReAct-kuvio (päättely ja toiminta)](../../../04-tools)
  - [Työkalukuvausten merkitys](../../../04-tools)
  - [Istunnon hallinta](../../../04-tools)
  - [Virheenkäsittely](../../../04-tools)
- [Saatavilla olevat työkalut](../../../04-tools)
- [Milloin käyttää työkalupohjaisia agentteja](../../../04-tools)
- [Seuraavat askeleet](../../../04-tools)

## Mitä opit

Tähän asti olet oppinut käymään keskusteluja tekoälyn kanssa, rakentamaan tehokkaita kehotteita ja kytkemään vastaukset dokumentteihisi. Mutta on olemassa perustavanlaatuinen rajoitus: kielimallit pystyvät tuottamaan vain tekstiä. Ne eivät voi tarkistaa säätä, tehdä laskelmia, kysellä tietokannoista tai olla vuorovaikutuksessa ulkoisten järjestelmien kanssa.

Työkalut muuttavat tämän. Antamalla mallille pääsyn kutsuttaviin funktioihin, muunnat sen pelkästä tekstintuottajasta agentiksi, joka voi ottaa toimia. Malli päättää, milloin se tarvitsee työkalua, mitä työkalua käyttää ja mitä parametreja antaa. Koodisi suorittaa funktion ja palauttaa tuloksen. Malli sulauttaa tuloksen vastaukseensa.

## Edellytykset

- Moduuli 01 suoritettu (Azure OpenAI -resurssit otettu käyttöön)
- Juurikansiossa `.env`-tiedosto Azure-tunnuksilla (luotu `azd up` -komennolla moduulissa 01)

> **Huom:** Jos et ole suorittanut moduulia 01, noudata ensin siellä olevia käyttöönotto-ohjeita.

## AI-agenttien ymmärtäminen työkaluilla

> **📝 Huom:** Tässä moduulissa termi "agentit" tarkoittaa tekoälyavustajia, joilla on työkalukutsutoiminnallisuus. Tämä eroaa **Agentic AI** -mallikuvioista (autonomiset agentit, joilla on suunnittelu, muisti ja monivaiheinen päättely), joita käsittelemme [Moduulissa 05: MCP](../05-mcp/README.md).

Työkaluilla varustettu AI-agentti noudattaa päättelyn ja toiminnan kuviota (ReAct):

1. Käyttäjä esittää kysymyksen
2. Agentti pohtii, mitä sen pitää tietää
3. Agentti päättää, tarvitseekö se työkalua vastatakseen
4. Jos tarvitsee, agentti kutsuu sopivaa työkalua oikeilla parametreilla
5. Työkalu suorittaa toiminnon ja palauttaa tietoa
6. Agentti sisällyttää tuloksen vastaukseensa ja antaa lopullisen vastauksen

<img src="../../../translated_images/fi/react-pattern.86aafd3796f3fd13.png" alt="ReAct-kuvio" width="800"/>

*ReAct-kuvio – miten AI-agentit vuorottelevat päättelyn ja toiminnan välillä ongelmien ratkaisemiseksi*

Tämä tapahtuu automaattisesti. Määrittelet työkalut ja niiden kuvaukset. Malli hoitaa päätöksenteon siitä, milloin ja miten niitä käytetään.

## Miten työkalukutsut toimivat

### Työkalumääritelmät

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Määrittelet funktiot selkeillä kuvauksilla ja parametrimäärityksillä. Malli näkee nämä kuvaukset järjestelmäkehotteessaan ja ymmärtää, mitä kukin työkalu tekee.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Säähaun logiikkasi
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Avustaja on automaattisesti yhdistetty Spring Bootin kanssa:
// - ChatModel bean
// - Kaikki @Tool-metodit @Component-luokista
// - ChatMemoryProvider istunnon hallintaa varten
```

> **🤖 Kokeile [GitHub Copilot](https://github.com/features/copilot) Chatin kanssa:** Avaa [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) ja kysy:
> - "Miten integroisin oikean sää-API:n, kuten OpenWeatherMapin, mallin mock-datan sijaan?"
> - "Mikä tekee työkalukuvausta hyväksi ja auttaa AI:ta käyttämään sitä oikein?"
> - "Miten käsittelen API-virheitä ja rajapyyntirajoja työkalujen toteutuksissa?"

### Päätöksenteko

Kun käyttäjä kysyy "Mikä on sää Seattlessa?", malli tunnistaa tarvitsevansa sääkalu. Se muodostaa funktiokutsun sijaintiparametrilla "Seattle".

### Suoritus

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot liittää automaattisesti `@AiService`-rajapinnan kaikilla rekisteröidyillä työkaluilla, ja LangChain4j suorittaa työkalukutsut automaattisesti.

> **🤖 Kokeile [GitHub Copilot](https://github.com/features/copilot) Chatin kanssa:** Avaa [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) ja kysy:
> - "Miten ReAct-kuvio toimii ja miksi se on tehokas AI-agenteille?"
> - "Miten agentti päättää, mitä työkalua käyttää ja missä järjestyksessä?"
> - "Mitä tapahtuu, jos työkalun suoritus epäonnistuu – miten virheitä tulisi käsitellä luotettavasti?"

### Vastauksen generointi

Malli saa säädatan ja muotoilee siitä luonnollisen kielen vastauksen käyttäjälle.

### Miksi käyttää deklaratiivisia AI-palveluita?

Tässä moduulissa käytetään LangChain4j:n Spring Boot -integraatiota deklaratiivisilla `@AiService`-rajapinnoilla:

- **Spring Boot auto-wiring** – ChatModel ja työkalut injektoidaan automaattisesti
- **@MemoryId-kuvio** – Istuntokohtainen muistinhallinta automaattisesti
- **Yksi instanssi** – Avustaja luodaan kerran ja käytetään uudelleen parempaan suorituskykyyn
- **Tyyppiturvallinen suoritus** – Java-metodeita kutsutaan suoraan tyyppimuunnoksilla
- **Monivaiheinen orkestrointi** – Käsittelee työkaluketjut automaattisesti
- **Ei boilerplatea** – Ei manuaalisia AiServices.builder()-kutsuja tai muistinhallintaa HashMapilla

Vaihtoehtoiset lähestymistavat (manuaalinen `AiServices.builder()`) vaativat enemmän koodia ja eivät hyödynnä Spring Bootin integraation etuja.

## Työkaluketjutus

**Työkaluketjutus** – AI saattaa kutsua useita työkaluja peräkkäin. Kysy esimerkiksi "Mikä on sää Seattlessa ja pitäisikö ottaa sateenvarjo?" ja katso, miten se ketjuttaa `getCurrentWeather` -kutsun sateenvarjoa koskevan pohtimisen kanssa.

<a href="images/tool-chaining.png"><img src="../../../translated_images/fi/tool-chaining.3b25af01967d6f7b.png" alt="Työkaluketjutus" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Peräkkäiset työkalukutsut – yhden työkalun tuotos ohjaa seuraavaa päätöstä*

**Huolelliset virhetilanteet** – Kysy sää jostain kaupungista, jota mock-data ei kata. Työkalu palauttaa virheilmoituksen, ja AI selittää, ettei voi auttaa. Työkalut epäonnistuvat turvallisesti.

Tämä tapahtuu yhdessä keskustelun vuorossa. Agentti orkestroi useat työkalukutsut itsenäisesti.

## Sovelluksen suorittaminen

**Tarkista käyttöönotto:**

Varmista, että juurikansiossa on `.env`-tiedosto Azure-tunnuksilla (luotu moduulin 01 aikana):
```bash
cat ../.env  # Tulisi näyttää AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Käynnistä sovellus:**

> **Huom:** Jos olet jo käynnistänyt kaikki sovellukset `./start-all.sh` -skriptillä moduulissa 01, tämä moduuli toimii jo portissa 8084. Voit ohittaa käynnistyskomennot ja mennä suoraan osoitteeseen http://localhost:8084.

**Vaihtoehto 1: Spring Boot Dashboardin käyttö (suositeltu VS Code -käyttäjille)**

Kehityssäiliössä on Spring Boot Dashboard -laajennus, joka tarjoaa visuaalisen käyttöliittymän kaikkien Spring Boot -sovellusten hallintaan. Löydät sen VS Coden vasemmalta Activity Barista (etsi Spring Boot -kuvake).

Dashboardista voit:
- Näyttää kaikki käytettävissä olevat Spring Boot -sovellukset
- Käynnistää/pysäyttää sovellukset yhdellä napsautuksella
- Tarkastella sovelluslokeja reaaliajassa
- Valvoa sovellusten tilaa

Käynnistä moduuli napsauttamalla "tools"-kohdan vieressä olevaa soittonappia tai käynnistä kaikki moduulit kerralla.

<img src="../../../translated_images/fi/dashboard.9b519b1a1bc1b30a.png" alt="Spring Boot Dashboard" width="400"/>

**Vaihtoehto 2: Shell-skriptien käyttö**

Käynnistä kaikki web-sovellukset (moduulit 01-04):

**Bash:**
```bash
cd ..  # Juurihakemistosta
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Juurihakemistosta
.\start-all.ps1
```

Tai käynnistä vain tämä moduuli:

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

Molemmat skriptit lataavat automaattisesti ympäristömuuttujat juurikansion `.env`-tiedostosta ja rakentavat JAR-tiedostot, jos niitä ei ole vielä olemassa.

> **Huom:** Jos haluat koota moduulit manuaalisesti ennen käynnistystä:
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

Avaa http://localhost:8084 selaimessasi.

**Pysäyttäminen:**

**Bash:**
```bash
./stop.sh  # Vain tämä moduuli
# Tai
cd .. && ./stop-all.sh  # Kaikki moduulit
```

**PowerShell:**
```powershell
.\stop.ps1  # Vain tämä moduuli
# Tai
cd ..; .\stop-all.ps1  # Kaikki moduulit
```

## Sovelluksen käyttäminen

Sovellus tarjoaa web-käyttöliittymän, jossa voit olla vuorovaikutuksessa AI-agentin kanssa, jolla on pääsy sää- ja lämpötilamuunnostyökaluihin.

<a href="images/tools-homepage.png"><img src="../../../translated_images/fi/tools-homepage.4b4cd8b2717f9621.png" alt="AI-agenttien työkaluliittymä" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI-agenttien työkaluliittymä – nopeita esimerkkejä ja chat-käyttöliittymä työkalujen kanssa keskusteluun*

### Kokeile yksinkertaista työkalun käyttöä

Aloita suoraviivaisella pyynnöllä: "Muunna 100 Fahrenheit-astetta Celsius-asteiksi". Agentti tunnistaa tarvitsevansa lämpötilamuunnostyökalun, kutsuu sitä oikeilla parametreilla ja palauttaa tuloksen. Huomaa, kuinka luonnolliselta tämä tuntuu – et määritellyt, mitä työkalua käyttää tai miten kutsua sitä.

### Testaa työkaluketjutusta

Kokeile nyt monimutkaisempaa pyyntöä: "Mikä on sää Seattlessa ja muunna se Fahrenheit-asteiksi?" Katso, miten agentti toimii vaiheittain. Se ensin hakee sään (joka on celsius), tunnistaa tarvetta muuntaa farenheitiksi, kutsuu muunnostyökalun ja yhdistää molemmat tulokset yhdeksi vastaukseksi.

### Katso keskustelun kulku

Chat-käyttöliittymä säilyttää keskusteluhistorian, mikä mahdollistaa monivaiheiset vuorovaikutukset. Näet kaikki aiemmat kyselyt ja vastaukset, mikä helpottaa keskustelun seuraamista ja ymmärtämistä siitä, miten agentti rakentaa kontekstia useissa vaihdoissa.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/fi/tools-conversation-demo.89f2ce9676080f59.png" alt="Keskustelu, jossa useita työkalukutsuja" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Monivaiheinen keskustelu, jossa nähdään yksinkertaisia muunnoksia, säähaut ja työkaluketjutusta*

### Kokeile erilaisia pyyntöjä

Kokeile erilaisia yhdistelmiä:
- Säähaut: "Millainen sää on Tokiossa?"
- Lämpötilamuunnokset: "Paljonko on 25°C Kelvin-asteina?"
- Yhdistetyt kyselyt: "Tarkista sää Pariisissa ja kerro, onko yli 20°C"

Huomaa, miten agentti tulkitsee luonnollista kieltä ja muuntaa sen sopiviksi työkalukutsuiksi.

## Keskeiset käsitteet

### ReAct-kuvio (päättely ja toiminta)

Agentti vuorottelee päättelyn (päätös, mitä tehdä) ja toiminnan (työkalujen käyttö) välillä. Tämä kuvio mahdollistaa itsenäisen ongelmanratkaisun pelkän käskyihin vastaamisen sijaan.

### Työkalukuvausten merkitys

Työkalukuvaustesi laatu vaikuttaa suoraan siihen, kuinka hyvin agentti käyttää työkaluja. Selkeät, tarkat kuvaukset auttavat mallia ymmärtämään, milloin ja miten kutakin työkalua kutsutaan.

### Istunnon hallinta

`@MemoryId`-annotaatio mahdollistaa automaattisen istuntokohtaisen muistinhallinnan. Jokaisella istuntotunnuksella on oma `ChatMemory`-instanssi, jota `ChatMemoryProvider`-bean hallinnoi. Näin ei tarvitse seurata muistia manuaalisesti.

### Virheenkäsittely

Työkalut voivat epäonnistua – API:t voivat aikakatkaista, parametrit voivat olla virheellisiä, ulkoiset palvelut voivat mennä alas. Tuotantotason agenteissa tarvitaan virheenkäsittelyä, jotta malli voi selittää ongelmat tai kokeilla vaihtoehtoja.

## Saatavilla olevat työkalut

**Säätyökalut** (demonstratiivista mock-dataa):
- Hanki nykyinen sää sijainnille
- Hanki monipäiväinen sääennuste

**Lämpötilamuunnostyökalut**:
- Celsius → Fahrenheit
- Fahrenheit → Celsius
- Celsius → Kelvin
- Kelvin → Celsius
- Fahrenheit → Kelvin
- Kelvin → Fahrenheit

Nämä ovat yksinkertaisia esimerkkejä, mutta kuvio laajenee mihin tahansa funktioon: tietokantahaut, API-kutsut, laskutoimitukset, tiedostotoiminnot tai järjestelmäkomennot.

## Milloin käyttää työkalupohjaisia agentteja

**Käytä työkaluja, kun:**
- Vastauksen saaminen vaatii reaaliaikaista dataa (sää, osakekurssit, varastosaldot)
- Tarvitset laskelmia pelkän perusmatematiikan ulkopuolelta
- Pääsy tietokantoihin tai API:hin
- Toimien tekeminen (sähköpostien lähetys, tikettien luonti, tietueiden päivitys)
- Tietolähteiden yhdistäminen

**Älä käytä työkaluja, kun:**
- Kysymyksiin voi vastata yleisen tiedon pohjalta
- Vastaus on puhtaasti keskusteleva
- Työkalujen viive hidastaisi kokemusta liikaa

## Seuraavat askeleet

**Seuraava moduuli:** [05-mcp - Mallikonseptiprotokolla (MCP)](../05-mcp/README.md)

---

**Navigointi:** [← Edellinen: Moduuli 03 - RAG](../03-rag/README.md) | [Takaisin päävalikkoon](../README.md) | [Seuraava: Moduuli 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastuuvapauslauseke**:
Tämä asiakirja on käännetty käyttämällä tekoälypohjaista käännöspalvelua [Co-op Translator](https://github.com/Azure/co-op-translator). Pyrimme tarkkuuteen, mutta ota huomioon, että automaattiset käännökset voivat sisältää virheitä tai epätarkkuuksia. Alkuperäinen asiakirja sen alkuperäisellä kielellä on virallinen lähde. Tärkeiden tietojen osalta suositellaan ammattimaista ihmiskäännöstä. Emme ole vastuussa tämän käännöksen käytöstä aiheutuneista väärinkäsityksistä tai väärin tulkinnoista.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->