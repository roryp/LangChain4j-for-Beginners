<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c25ec1f10ef156c53e190cdf8b0711ab",
  "translation_date": "2025-12-13T17:56:43+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "fi"
}
-->
# Moduuli 05: Mallin kontekstiprotokolla (MCP)

## Sisällysluettelo

- [Mitä opit](../../../05-mcp)
- [MCP:n ymmärtäminen](../../../05-mcp)
- [Miten MCP toimii](../../../05-mcp)
  - [Palvelin-asiakasarkkitehtuuri](../../../05-mcp)
  - [Työkalujen löytäminen](../../../05-mcp)
  - [Siirtomekanismit](../../../05-mcp)
- [Esivaatimukset](../../../05-mcp)
- [Mitä tämä moduuli kattaa](../../../05-mcp)
- [Pika-aloitus](../../../05-mcp)
  - [Esimerkki 1: Etälaskin (Streamable HTTP)](../../../05-mcp)
  - [Esimerkki 2: Tiedostotoiminnot (Stdio)](../../../05-mcp)
  - [Esimerkki 3: Git-analyysi (Docker)](../../../05-mcp)
- [Keskeiset käsitteet](../../../05-mcp)
  - [Siirron valinta](../../../05-mcp)
  - [Työkalujen löytäminen](../../../05-mcp)
  - [Istunnon hallinta](../../../05-mcp)
  - [Monialustaiset näkökohdat](../../../05-mcp)
- [Milloin käyttää MCP:tä](../../../05-mcp)
- [MCP-ekosysteemi](../../../05-mcp)
- [Onnittelut!](../../../05-mcp)
  - [Mitä seuraavaksi?](../../../05-mcp)
- [Vianetsintä](../../../05-mcp)

## Mitä opit

Olet rakentanut keskustelevaa tekoälyä, hallinnut kehotteita, perustanut vastaukset dokumentteihin ja luonut agentteja työkaluilla. Mutta kaikki nämä työkalut olivat räätälöityjä juuri sinun sovellustasi varten. Entä jos voisit antaa tekoälyllesi pääsyn standardoituun työkaluekosysteemiin, jonka kuka tahansa voi luoda ja jakaa?

Model Context Protocol (MCP) tarjoaa juuri tämän – standardoidun tavan tekoälysovelluksille löytää ja käyttää ulkoisia työkaluja. Sen sijaan, että kirjoittaisit räätälöityjä integraatioita jokaiselle tietolähteelle tai palvelulle, yhdistyt MCP-palvelimiin, jotka tarjoavat ominaisuutensa yhtenäisessä muodossa. Tekoälyagenttisi voi sitten automaattisesti löytää ja käyttää näitä työkaluja.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5448d2fa21a61218777ceb8010ea0390dd43924b26df35f61.fi.png" alt="MCP Comparison" width="800"/>

*Ennen MCP:tä: Monimutkaiset pisteestä pisteeseen -integraatiot. MCP:n jälkeen: Yksi protokolla, loputtomat mahdollisuudet.*

## MCP:n ymmärtäminen

MCP ratkaisee perustavanlaatuisen ongelman tekoälyn kehityksessä: jokainen integraatio on räätälöity. Haluatko käyttää GitHubia? Räätälöity koodi. Haluatko lukea tiedostoja? Räätälöity koodi. Haluatko kysyä tietokantaa? Räätälöity koodi. Eikä mikään näistä integraatioista toimi muiden tekoälysovellusten kanssa.

MCP standardisoi tämän. MCP-palvelin tarjoaa työkalut selkeillä kuvauksilla ja skeemoilla. Mikä tahansa MCP-asiakas voi yhdistää, löytää saatavilla olevat työkalut ja käyttää niitä. Rakenna kerran, käytä kaikkialla.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9814b7cffade208d4b0d97203c22df8d8e5504d8238fa7065.fi.png" alt="MCP Architecture" width="800"/>

*Model Context Protocol -arkkitehtuuri – standardoitu työkalujen löytäminen ja suorittaminen*

## Miten MCP toimii

**Palvelin-asiakasarkkitehtuuri**

MCP käyttää asiakas-palvelin-mallia. Palvelimet tarjoavat työkaluja – tiedostojen lukemista, tietokantakyselyjä, API-kutsuja. Asiakkaat (tekoälysovelluksesi) yhdistävät palvelimiin ja käyttävät niiden työkaluja.

**Työkalujen löytäminen**

Kun asiakkaasi yhdistää MCP-palvelimeen, se kysyy "Mitä työkaluja sinulla on?" Palvelin vastaa luettelolla saatavilla olevista työkaluista, jokaisella kuvaukset ja parametrien skeemat. Tekoälyagenttisi voi sitten päättää, mitä työkaluja käyttää käyttäjän pyyntöjen perusteella.

**Siirtomekanismit**

MCP määrittelee kaksi siirtomekanismia: HTTP etäpalvelimille, Stdio paikallisille prosesseille (mukaan lukien Docker-kontit):

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020ed801b772b26ed69338e22739677aa017e0968f6538b09a2.fi.png" alt="Transport Mechanisms" width="800"/>

*MCP:n siirtomekanismit: HTTP etäpalvelimille, Stdio paikallisille prosesseille (mukaan lukien Docker-kontit)*

**Streamable HTTP** - [StreamableHttpDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java)

Etäpalvelimille. Sovelluksesi tekee HTTP-pyyntöjä jossain verkossa toimivalle palvelimelle. Käyttää Server-Sent Events -tekniikkaa reaaliaikaiseen viestintään.

```java
McpTransport httpTransport = new StreamableHttpMcpTransport.Builder()
    .url("http://localhost:3001/mcp")
    .timeout(Duration.ofSeconds(60))
    .logRequests(true)
    .logResponses(true)
    .build();
```

> **🤖 Kokeile [GitHub Copilot](https://github.com/features/copilot) Chatin kanssa:** Avaa [`StreamableHttpDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java) ja kysy:
> - "Miten MCP eroaa suorasta työkalun integraatiosta kuten Moduulissa 04?"
> - "Mitkä ovat MCP:n käytön edut työkalujen jakamisessa sovellusten välillä?"
> - "Miten käsittelen yhteysvirheitä tai aikakatkaisuja MCP-palvelimiin?"

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Paikallisille prosesseille. Sovelluksesi käynnistää palvelimen aliprosessina ja kommunikoi standardin sisään- ja ulostulon kautta. Hyödyllinen tiedostojärjestelmän käyttöön tai komentorivityökaluihin.

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@0.6.2",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 Kokeile [GitHub Copilot](https://github.com/features/copilot) Chatin kanssa:** Avaa [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) ja kysy:
> - "Miten Stdio-siirto toimii ja milloin sitä pitäisi käyttää HTTP:n sijaan?"
> - "Miten LangChain4j hallitsee MCP-palvelinprosessien elinkaaren?"
> - "Mitkä ovat turvallisuusnäkökohdat, kun annetaan tekoälylle pääsy tiedostojärjestelmään?"

**Docker (käyttää Stdioa)** - [GitRepositoryAnalyzer.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java)

Konttien palveluille. Käyttää stdio-siirtoa kommunikoidakseen Docker-kontin kanssa `docker run` -komennolla. Hyvä monimutkaisiin riippuvuuksiin tai eristettyihin ympäristöihin.

```java
McpTransport dockerTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        "docker", "run",
        "-e", "GITHUB_PERSONAL_ACCESS_TOKEN=" + System.getenv("GITHUB_TOKEN"),
        "-v", volumeMapping,
        "-i", "mcp/git"
    ))
    .logEvents(true)
    .build();
```

> **🤖 Kokeile [GitHub Copilot](https://github.com/features/copilot) Chatin kanssa:** Avaa [`GitRepositoryAnalyzer.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java) ja kysy:
> - "Miten Docker-siirto eristää MCP-palvelimet ja mitkä ovat hyödyt?"
> - "Miten konfiguroin volyymien liitokset datan jakamiseksi isännän ja MCP-konttien välillä?"
> - "Mitkä ovat parhaat käytännöt Docker-pohjaisten MCP-palvelinprosessien hallintaan tuotannossa?"

## Esimerkkien suorittaminen

### Esivaatimukset

- Java 21+, Maven 3.9+
- Node.js 16+ ja npm (MCP-palvelimille)
- **Docker Desktop** – Täytyy olla **KÄYTÖSSÄ** Esimerkkiä 3 varten (ei pelkästään asennettuna)
- GitHubin henkilökohtainen käyttöoikeustunnus konfiguroituna `.env`-tiedostossa (Moduulista 00)

> **Huom:** Jos et ole vielä asettanut GitHub-tunnustasi, katso [Moduuli 00 - Pika-aloitus](../00-quick-start/README.md) ohjeita.

> **⚠️ Docker-käyttäjille:** Ennen Esimerkkiä 3 varmista, että Docker Desktop on käynnissä komennolla `docker ps`. Jos näet yhteysvirheitä, käynnistä Docker Desktop ja odota noin 30 sekuntia alustuksen valmistumista.

## Pika-aloitus

**VS Code -käytössä:** Napsauta hiiren oikealla mitä tahansa demotiedostoa Explorerissa ja valitse **"Run Java"**, tai käytä Run and Debug -paneelin käynnistyskonfiguraatioita (muista lisätä tunnuksesi `.env`-tiedostoon ensin).

**Mavenilla:** Vaihtoehtoisesti voit ajaa komentoriviltä alla olevilla esimerkeillä.

**⚠️ Tärkeää:** Joillakin esimerkeillä on esivaatimuksia (kuten MCP-palvelimen käynnistys tai Docker-kuvien rakentaminen). Tarkista kunkin esimerkin vaatimukset ennen ajoa.

### Esimerkki 1: Etälaskin (Streamable HTTP)

Tämä demonstroi verkkoon perustuvaa työkalujen integraatiota.

**⚠️ Esivaatimus:** MCP-palvelin täytyy käynnistää ensin (katso Terminaali 1 alla).

**Terminaali 1 – Käynnistä MCP-palvelin:**

**Bash:**
```bash
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**PowerShell:**
```powershell
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**Terminaali 2 – Aja esimerkki:**

**VS Code:** Napsauta hiiren oikealla `StreamableHttpDemo.java` ja valitse **"Run Java"**.

**Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

Seuraa, kuinka agentti löytää saatavilla olevat työkalut ja käyttää laskinta yhteenlaskun suorittamiseen.

### Esimerkki 2: Tiedostotoiminnot (Stdio)

Tämä demonstroi paikallisia aliprosessipohjaisia työkaluja.

**✅ Ei esivaatimuksia** – MCP-palvelin käynnistyy automaattisesti.

**VS Code:** Napsauta hiiren oikealla `StdioTransportDemo.java` ja valitse **"Run Java"**.

**Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

Sovellus käynnistää tiedostojärjestelmän MCP-palvelimen automaattisesti ja lukee paikallisen tiedoston. Huomaa, miten aliprosessien hallinta hoidetaan puolestasi.

**Odotettu tulos:**
```
Assistant response: The content of the file is "Kaboom!".
```

### Esimerkki 3: Git-analyysi (Docker)

Tämä demonstroi konttien palvelimia.

**⚠️ Esivaatimukset:** 
1. **Docker Desktopin täytyy olla KÄYTÖSSÄ** (ei pelkästään asennettuna)
2. **Windows-käyttäjät:** WSL 2 -tila suositeltu (Docker Desktop Settings → General → "Use the WSL 2 based engine"). Hyper-V-tila vaatii manuaalisen tiedostojen jakamisen konfiguroinnin.
3. Docker-kuva täytyy rakentaa ensin (katso Terminaali 1 alla)

**Varmista, että Docker on käynnissä:**

**Bash:**
```bash
docker ps  # Pitäisi näyttää konttiluettelo, ei virhettä
```

**PowerShell:**
```powershell
docker ps  # Pitäisi näyttää konttiluettelo, ei virhettä
```

Jos näet virheen kuten "Cannot connect to Docker daemon" tai "The system cannot find the file specified", käynnistä Docker Desktop ja odota alustuksen valmistumista (~30 sekuntia).

**Vianetsintä:**
- Jos tekoäly raportoi tyhjän repositorion tai ei tiedostoja, volyymiliitos (`-v`) ei toimi.
- **Windows Hyper-V -käyttäjät:** Lisää projektihakemisto Docker Desktop Settings → Resources → File sharing -kohtaan ja käynnistä Docker Desktop uudelleen.
- **Suositeltu ratkaisu:** Vaihda WSL 2 -tilaan automaattista tiedostojen jakamista varten (Settings → General → ota käyttöön "Use the WSL 2 based engine").

**Terminaali 1 – Rakenna Docker-kuva:**

**Bash:**
```bash
cd servers/src/git
docker build -t mcp/git .
```

**PowerShell:**
```powershell
cd servers/src/git
docker build -t mcp/git .
```

**Terminaali 2 – Aja analyysi:**

**VS Code:** Napsauta hiiren oikealla `GitRepositoryAnalyzer.java` ja valitse **"Run Java"**.

**Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

Sovellus käynnistää Docker-kontin, liittää repositoriosi ja analysoi repositorion rakenteen ja sisällön tekoälyagentin kautta.

## Keskeiset käsitteet

**Siirron valinta**

Valitse sen mukaan, missä työkalusi sijaitsevat:
- Etäpalvelut → Streamable HTTP
- Paikallinen tiedostojärjestelmä → Stdio
- Monimutkaiset riippuvuudet → Docker

**Työkalujen löytäminen**

MCP-asiakkaat löytävät automaattisesti saatavilla olevat työkalut yhdistäessään. Tekoälyagenttisi näkee työkalujen kuvaukset ja päättää, mitä käyttää käyttäjän pyynnön perusteella.

**Istunnon hallinta**

Streamable HTTP -siirto ylläpitää istuntoja, mahdollistaen tilalliset vuorovaikutukset etäpalvelimien kanssa. Stdio- ja Docker-siirrot ovat tyypillisesti tilattomia.

**Monialustaiset näkökohdat**

Esimerkit käsittelevät automaattisesti alustan eroja (Windowsin ja Unix-komentojen erot, polkujen muunnokset Dockerille). Tämä on tärkeää tuotantokäyttöön eri ympäristöissä.

## Milloin käyttää MCP:tä

**Käytä MCP:tä, kun:**
- Haluat hyödyntää olemassa olevia työkaluekosysteemejä
- Rakennat työkaluja, joita useat sovellukset käyttävät
- Integroi kolmannen osapuolen palveluita standardiprotokollilla
- Tarvitset työkalujen toteutusten vaihtoa ilman koodimuutoksia

**Käytä räätälöityjä työkaluja (Moduuli 04), kun:**
- Rakennat sovelluskohtaisia toiminnallisuuksia
- Suorituskyky on kriittistä (MCP lisää ylikuormitusta)
- Työkalusi ovat yksinkertaisia eivätkä tule uudelleenkäytetyiksi
- Tarvitset täydellisen hallinnan suoritukseen

## MCP-ekosysteemi

Model Context Protocol on avoin standardi kasvavalla ekosysteemillä:

- Viralliset MCP-palvelimet yleisiin tehtäviin (tiedostojärjestelmä, Git, tietokannat)
- Yhteisön tuottamat palvelimet erilaisiin palveluihin
- Standardoidut työkalukuvaus- ja skeemat
- Yhteensopivuus eri kehysten kanssa (toimii minkä tahansa MCP-asiakkaan kanssa)

Tämä standardisointi tarkoittaa, että yhdelle tekoälysovellukselle rakennetut työkalut toimivat myös muiden kanssa, luoden jaetun kyvykkyysekosysteemin.

## Onnittelut!

Olet suorittanut LangChain4j aloittelijoille -kurssin. Olet oppinut:

- Kuinka rakentaa keskusteleva tekoäly muistilla (Moduuli 01)
- Kehotetekniikoita eri tehtäviin (Moduuli 02)
- Vastausten perustamisen dokumentteihin RAG:n avulla (Moduuli 03)
- Tekoälyagenttien luomisen räätälöidyillä työkaluilla (Moduuli 04)
- Standardoitujen työkalujen integroinnin MCP:n kautta (Moduuli 05)

Sinulla on nyt perusta tuotantotason tekoälysovellusten rakentamiseen. Oppimasi käsitteet pätevät riippumatta erityisistä kehyksistä tai malleista – ne ovat tekoälytekniikan perustavanlaatuisia malleja.

### Mitä seuraavaksi?

Moduulien suorittamisen jälkeen tutustu [Testausoppaaseen](../docs/TESTING.md) nähdäksesi LangChain4j:n testauskonseptit käytännössä.

**Viralliset resurssit:**
- [LangChain4j Dokumentaatio](https://docs.langchain4j.dev/) – Kattavat oppaat ja API-viite
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) – Lähdekoodi ja esimerkit
- [LangChain4j Opetusohjelmat](https://docs.langchain4j.dev/tutorials/) – Vaiheittaiset opetusohjelmat eri käyttötarkoituksiin

Kiitos, että suoristit tämän kurssin!

---

**Navigointi:** [← Edellinen: Moduuli 04 - Työkalut](../04-tools/README.md) | [Takaisin pääsivulle](../README.md)

---

## Vianetsintä

### PowerShellin Maven-komentojen syntaksi
**Ongelma**: Maven-komennot epäonnistuvat virheellä `Unknown lifecycle phase ".mainClass=..."`

**Syy**: PowerShell tulkitsee `=` muuttujan arvon asetusoperaattorina, mikä rikkoo Mavenin ominaisuuksien syntaksin

**Ratkaisu**: Käytä pysäytyksen jäsentämisoperaattoria `--%` ennen Maven-komentoa:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

`--%`-operaattori kertoo PowerShellille, että kaikki jäljellä olevat argumentit välitetään kirjaimellisesti Mavenille ilman tulkintaa.

### Docker-yhteysongelmat

**Ongelma**: Docker-komennot epäonnistuvat virheellä "Cannot connect to Docker daemon" tai "The system cannot find the file specified"

**Syy**: Docker Desktop ei ole käynnissä tai ei ole täysin alustautunut

**Ratkaisu**: 
1. Käynnistä Docker Desktop
2. Odota noin 30 sekuntia täyteen alustautumiseen
3. Tarkista komennolla `docker ps` (näyttää konttien listan, ei virhettä)
4. Suorita sitten esimerkkisi

### Windows Docker -volyymin liittäminen

**Ongelma**: Git-repositorion analysoija raportoi tyhjän repositorion tai ei tiedostoja

**Syy**: Volyymin liittäminen (`-v`) ei toimi tiedostojen jakamisasetusten vuoksi

**Ratkaisu**:
- **Suositeltu:** Vaihda WSL 2 -tilaan (Docker Desktop Settings → General → "Use the WSL 2 based engine")
- **Vaihtoehto (Hyper-V):** Lisää projektihakemisto Docker Desktop Settings → Resources → File sharing -kohtaan, ja käynnistä Docker Desktop uudelleen

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastuuvapauslauseke**:
Tämä asiakirja on käännetty käyttämällä tekoälypohjaista käännöspalvelua [Co-op Translator](https://github.com/Azure/co-op-translator). Vaikka pyrimme tarkkuuteen, otathan huomioon, että automaattikäännöksissä saattaa esiintyä virheitä tai epätarkkuuksia. Alkuperäinen asiakirja sen alkuperäiskielellä on virallinen lähde. Tärkeissä asioissa suositellaan ammattimaista ihmiskäännöstä. Emme ole vastuussa tämän käännöksen käytöstä aiheutuvista väärinymmärryksistä tai tulkinnoista.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->