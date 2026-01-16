<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c3e07ca58d0b8a3f47d3bf5728541e0a",
  "translation_date": "2025-12-13T13:44:20+00:00",
  "source_file": "01-introduction/README.md",
  "language_code": "fi"
}
-->
# Module 01: Aloittaminen LangChain4j:n kanssa

## Sisällysluettelo

- [Mitä opit](../../../01-introduction)
- [Esivaatimukset](../../../01-introduction)
- [Ymmärtäminen ydinkysymyksestä](../../../01-introduction)
- [Tokenien ymmärtäminen](../../../01-introduction)
- [Miten muisti toimii](../../../01-introduction)
- [Miten tämä käyttää LangChain4j:ta](../../../01-introduction)
- [Azure OpenAI -infrastruktuurin käyttöönotto](../../../01-introduction)
- [Sovelluksen ajaminen paikallisesti](../../../01-introduction)
- [Sovelluksen käyttäminen](../../../01-introduction)
  - [Stateless Chat (vasen paneeli)](../../../01-introduction)
  - [Stateful Chat (oikea paneeli)](../../../01-introduction)
- [Seuraavat askeleet](../../../01-introduction)

## Mitä opit

Jos suoritat pikakäynnistyksen, näit miten lähetetään kehotteita ja saadaan vastauksia. Se on perusta, mutta todelliset sovellukset tarvitsevat enemmän. Tässä moduulissa opit rakentamaan keskustelevaa tekoälyä, joka muistaa kontekstin ja ylläpitää tilaa – ero kertaluontoisen demon ja tuotantovalmiin sovelluksen välillä.

Käytämme tässä oppaassa Azure OpenAI:n GPT-5:ttä, koska sen kehittyneet päättelykyvyt tekevät eri mallien käyttäytymisestä selkeämpää. Kun lisäät muistin, näet eron selvästi. Tämä helpottaa ymmärtämään, mitä kukin komponentti tuo sovellukseesi.

Rakennat yhden sovelluksen, joka demonstroi molempia malleja:

**Stateless Chat** – Jokainen pyyntö on itsenäinen. Mallilla ei ole muistia aiemmista viesteistä. Tämä on malli, jota käytit pikakäynnistyksessä.

**Stateful Conversation** – Jokainen pyyntö sisältää keskusteluhistorian. Malli ylläpitää kontekstia useiden vuorojen ajan. Tätä tuotantosovellukset vaativat.

## Esivaatimukset

- Azure-tilaus, jossa on Azure OpenAI -käyttöoikeus
- Java 21, Maven 3.9+
- Azure CLI (https://learn.microsoft.com/en-us/cli/azure/install-azure-cli)
- Azure Developer CLI (azd) (https://learn.microsoft.com/en-us/azure/developer/azure-developer-cli/install-azd)

> **Huom:** Java, Maven, Azure CLI ja Azure Developer CLI (azd) ovat valmiiksi asennettuina mukana toimitetussa devcontainerissa.

> **Huom:** Tämä moduuli käyttää GPT-5:ttä Azure OpenAI:ssa. Käyttöönotto konfiguroidaan automaattisesti `azd up` -komennolla – älä muuta mallin nimeä koodissa.

## Ymmärtäminen ydinkysymyksestä

Kielimallit ovat tilattomia. Jokainen API-kutsu on itsenäinen. Jos lähetät "Nimeni on John" ja sitten kysyt "Mikä nimeni on?", mallilla ei ole aavistustakaan, että juuri esittelit itsesi. Se käsittelee jokaisen pyynnön kuin se olisi ensimmäinen keskustelusi koskaan.

Tämä toimii yksinkertaisissa kysymys-vastaus -tilanteissa, mutta on hyödytöntä todellisissa sovelluksissa. Asiakaspalvelubottien täytyy muistaa, mitä kerroit niille. Henkilökohtaisten avustajien täytyy ymmärtää konteksti. Mikä tahansa monivuoroinen keskustelu vaatii muistia.

<img src="../../../translated_images/fi/stateless-vs-stateful.cc4a4765e649c41a.png" alt="Stateless vs Stateful Conversations" width="800"/>

*Ero tilattomien (itsenäisten kutsujen) ja tilallisten (kontekstia ymmärtävien) keskustelujen välillä*

## Tokenien ymmärtäminen

Ennen kuin sukellat keskusteluihin, on tärkeää ymmärtää tokenit – tekstin perusyksiköt, joita kielimallit käsittelevät:

<img src="../../../translated_images/fi/token-explanation.c39760d8ec650181.png" alt="Token Explanation" width="800"/>

*Esimerkki siitä, miten teksti pilkotaan tokeneiksi – "I love AI!" muodostuu neljästä erillisestä käsittelyyksiköstä*

Tokenit ovat tapa, jolla tekoälymallit mittaavat ja käsittelevät tekstiä. Sanat, välimerkit ja jopa välilyönnit voivat olla tokeneita. Mallillasi on raja, kuinka monta tokenia se voi käsitellä kerralla (GPT-5:llä 400 000, josta enintään 272 000 syötteessä ja 128 000 tulosteessa). Tokenien ymmärtäminen auttaa hallitsemaan keskustelun pituutta ja kustannuksia.

## Miten muisti toimii

Keskustelumuisti ratkaisee tilattomuuden ongelman ylläpitämällä keskusteluhistoriaa. Ennen kuin lähetät pyynnön mallille, kehys lisää siihen relevantit aiemmat viestit. Kun kysyt "Mikä nimeni on?", järjestelmä lähettää koko keskusteluhistorian, jolloin malli näkee, että sanoit aiemmin "Nimeni on John."

LangChain4j tarjoaa muistiratkaisuja, jotka hoitavat tämän automaattisesti. Valitset, kuinka monta viestiä säilytetään, ja kehys hallinnoi konteksti-ikkunaa.

<img src="../../../translated_images/fi/memory-window.bbe67f597eadabb3.png" alt="Memory Window Concept" width="800"/>

*MessageWindowChatMemory ylläpitää liukuvaa ikkunaa viimeisimmistä viesteistä, pudottaen automaattisesti vanhoja*

## Miten tämä käyttää LangChain4j:ta

Tämä moduuli laajentaa pikakäynnistystä integroimalla Spring Bootin ja lisäämällä keskustelumuistin. Näin osat toimivat yhdessä:

**Riippuvuudet** – Lisää kaksi LangChain4j-kirjastoa:

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

**Chat-malli** – Konfiguroi Azure OpenAI Spring beaniksi ([LangChainConfig.java](../../../01-introduction/src/main/java/com/example/langchain4j/config/LangChainConfig.java)):

```java
@Bean
public OpenAiOfficialChatModel openAiOfficialChatModel() {
    return OpenAiOfficialChatModel.builder()
            .baseUrl(azureEndpoint)
            .apiKey(azureApiKey)
            .modelName(deploymentName)
            .timeout(Duration.ofMinutes(5))
            .maxRetries(3)
            .build();
}
```

Rakentaja lukee tunnistetiedot ympäristömuuttujista, jotka `azd up` asettaa. `baseUrl`-asetuksella määritetään Azure-päätepiste, jolloin OpenAI-asiakas toimii Azure OpenAI:n kanssa.

**Keskustelumuisti** – Seuraa keskusteluhistoriaa MessageWindowChatMemoryllä ([ConversationService.java](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java)):

```java
ChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

memory.add(UserMessage.from("My name is John"));
memory.add(AiMessage.from("Nice to meet you, John!"));

memory.add(UserMessage.from("What's my name?"));
AiMessage aiMessage = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage);
```

Luo muisti `withMaxMessages(10)` -asetuksella, joka säilyttää viimeiset 10 viestiä. Lisää käyttäjän ja tekoälyn viestit tyypitetyillä kääreillä: `UserMessage.from(text)` ja `AiMessage.from(text)`. Hae historia `memory.messages()` -metodilla ja lähetä se mallille. Palvelu tallentaa erilliset muistiesimerkit keskustelu-ID:n mukaan, mahdollistaen useiden käyttäjien samanaikaisen keskustelun.

> **🤖 Kokeile [GitHub Copilot](https://github.com/features/copilot) Chatin kanssa:** Avaa [`ConversationService.java`](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java) ja kysy:
> - "Miten MessageWindowChatMemory päättää, mitkä viestit pudottaa, kun ikkuna on täynnä?"
> - "Voinko toteuttaa oman muistivarastoinnin tietokantaa käyttäen muistin sijaan?"
> - "Miten lisäisin tiivistämisen vanhan keskusteluhistorian pakkaamiseksi?"

Stateless chat -päätepiste ohittaa muistin kokonaan – vain `chatModel.chat(prompt)` kuten pikakäynnistyksessä. Stateful-päätepiste lisää viestit muistiin, hakee historian ja liittää sen kontekstiksi jokaiseen pyyntöön. Sama mallikonfiguraatio, eri mallit.

## Azure OpenAI -infrastruktuurin käyttöönotto

**Bash:**
```bash
cd 01-introduction
azd up  # Valitse tilaus ja sijainti (eastus2 suositeltu)
```

**PowerShell:**
```powershell
cd 01-introduction
azd up  # Valitse tilaus ja sijainti (eastus2 suositeltu)
```

> **Huom:** Jos saat aikakatkaisun virheen (`RequestConflict: Cannot modify resource ... provisioning state is not terminal`), suorita `azd up` uudelleen. Azure-resurssit voivat olla vielä käyttöönotossa taustalla, ja uudelleenkäynnistys sallii käyttöönoton valmistumisen, kun resurssit saavuttavat lopullisen tilan.

Tämä suorittaa:
1. Azure OpenAI -resurssin käyttöönoton GPT-5 ja text-embedding-3-small -malleilla
2. Luo automaattisesti `.env`-tiedoston projektin juureen tunnistetiedoilla
3. Määrittää kaikki tarvittavat ympäristömuuttujat

**Onko käyttöönotossa ongelmia?** Katso [Infrastructure README](infra/README.md) yksityiskohtaisesta vianmäärityksestä, mukaan lukien aliverkkotunnuksen nimikonfliktit, manuaaliset Azure Portal -käyttöönotto-ohjeet ja mallin konfigurointiohjeet.

**Varmista, että käyttöönotto onnistui:**

**Bash:**
```bash
cat ../.env  # Tulisi näyttää AZURE_OPENAI_ENDPOINT, API_KEY, jne.
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Tulisi näyttää AZURE_OPENAI_ENDPOINT, API_KEY, jne.
```

> **Huom:** `azd up` -komento luo `.env`-tiedoston automaattisesti. Jos sinun täytyy päivittää sitä myöhemmin, voit joko muokata `.env`-tiedostoa manuaalisesti tai luoda sen uudelleen ajamalla:
>
> **Bash:**
> ```bash
> cd ..
> bash .azd-env.sh
> ```
>
> **PowerShell:**
> ```powershell
> cd ..
> .\.azd-env.ps1
> ```

## Sovelluksen ajaminen paikallisesti

**Varmista käyttöönotto:**

Varmista, että `.env`-tiedosto on juurihakemistossa Azure-tunnistetiedoilla:

**Bash:**
```bash
cat ../.env  # Tulisi näyttää AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Tulisi näyttää AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Käynnistä sovellukset:**

**Vaihtoehto 1: Spring Boot Dashboardin käyttäminen (suositeltu VS Code -käyttäjille)**

Dev container sisältää Spring Boot Dashboard -laajennuksen, joka tarjoaa visuaalisen käyttöliittymän kaikkien Spring Boot -sovellusten hallintaan. Löydät sen VS Coden vasemman reunan Activity Barista (etsi Spring Boot -kuvaketta).

Spring Boot Dashboardista voit:
- Näyttää kaikki työtilan Spring Boot -sovellukset
- Käynnistää/pysäyttää sovelluksia yhdellä napsautuksella
- Tarkastella sovelluslokeja reaaliajassa
- Valvoa sovellusten tilaa

Klikkaa vain toistopainiketta "introduction" käynnistääksesi tämän moduulin tai käynnistä kaikki moduulit kerralla.

<img src="../../../translated_images/fi/dashboard.69c7479aef09ff6b.png" alt="Spring Boot Dashboard" width="400"/>

**Vaihtoehto 2: Shell-skriptien käyttäminen**

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
cd 01-introduction
./start.sh
```

**PowerShell:**
```powershell
cd 01-introduction
.\start.ps1
```

Molemmat skriptit lataavat automaattisesti ympäristömuuttujat juuren `.env`-tiedostosta ja rakentavat JAR-tiedostot, jos niitä ei ole olemassa.

> **Huom:** Jos haluat rakentaa kaikki moduulit manuaalisesti ennen käynnistystä:
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

Avaa selaimessa http://localhost:8080.

**Pysäyttääksesi:**

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

Sovellus tarjoaa web-käyttöliittymän, jossa on kaksi chat-toteutusta rinnakkain.

<img src="../../../translated_images/fi/home-screen.121a03206ab910c0.png" alt="Application Home Screen" width="800"/>

*Hallintapaneeli, jossa on sekä Simple Chat (tilaton) että Conversational Chat (tilallinen) -vaihtoehdot*

### Stateless Chat (vasen paneeli)

Kokeile tätä ensin. Kysy "Nimeni on John" ja heti perään "Mikä nimeni on?" Malli ei muista, koska jokainen viesti on itsenäinen. Tämä havainnollistaa perusongelman kielimallien integroinnissa – ei keskustelukontekstia.

<img src="../../../translated_images/fi/simple-chat-stateless-demo.13aeb3978eab3234.png" alt="Stateless Chat Demo" width="800"/>

*Tekoäly ei muista nimeäsi edellisestä viestistä*

### Stateful Chat (oikea paneeli)

Kokeile samaa sekvenssiä täällä. Kysy "Nimeni on John" ja sitten "Mikä nimeni on?" Tällä kertaa se muistaa. Erona on MessageWindowChatMemory – se ylläpitää keskusteluhistoriaa ja liittää sen jokaiseen pyyntöön. Näin tuotantokeskustelutekoäly toimii.

<img src="../../../translated_images/fi/conversational-chat-stateful-demo.e5be9822eb23ff59.png" alt="Stateful Chat Demo" width="800"/>

*Tekoäly muistaa nimesi aiemmasta keskustelusta*

Molemmat paneelit käyttävät samaa GPT-5-mallia. Ainoa ero on muisti. Tämä tekee selväksi, mitä muisti tuo sovellukseesi ja miksi se on välttämätön todellisissa käyttötapauksissa.

## Seuraavat askeleet

**Seuraava moduuli:** [02-prompt-engineering - Kehoteinsinöörityö GPT-5:n kanssa](../02-prompt-engineering/README.md)

---

**Navigointi:** [← Edellinen: Module 00 - Pikakäynnistys](../00-quick-start/README.md) | [Takaisin pääsivulle](../README.md) | [Seuraava: Module 02 - Kehoteinsinöörityö →](../02-prompt-engineering/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastuuvapauslauseke**:
Tämä asiakirja on käännetty käyttämällä tekoälypohjaista käännöspalvelua [Co-op Translator](https://github.com/Azure/co-op-translator). Vaikka pyrimme tarkkuuteen, otathan huomioon, että automaattiset käännökset saattavat sisältää virheitä tai epätarkkuuksia. Alkuperäistä asiakirjaa sen alkuperäiskielellä tulee pitää virallisena lähteenä. Tärkeiden tietojen osalta suositellaan ammattimaista ihmiskäännöstä. Emme ole vastuussa tämän käännöksen käytöstä aiheutuvista väärinymmärryksistä tai tulkinnoista.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->