<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T15:02:23+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "fi"
}
-->
# Module 00: Quick Start

## Table of Contents

- [Introduction](../../../00-quick-start)
- [What is LangChain4j?](../../../00-quick-start)
- [LangChain4j Dependencies](../../../00-quick-start)
- [Prerequisites](../../../00-quick-start)
- [Setup](../../../00-quick-start)
  - [1. Get Your GitHub Token](../../../00-quick-start)
  - [2. Set Your Token](../../../00-quick-start)
- [Run the Examples](../../../00-quick-start)
  - [1. Basic Chat](../../../00-quick-start)
  - [2. Prompt Patterns](../../../00-quick-start)
  - [3. Function Calling](../../../00-quick-start)
  - [4. Document Q&A (RAG)](../../../00-quick-start)
- [What Each Example Shows](../../../00-quick-start)
- [Next Steps](../../../00-quick-start)
- [Troubleshooting](../../../00-quick-start)

## Introduction

Tämä pikaopas on tarkoitettu auttamaan sinua pääsemään nopeasti alkuun LangChain4j:n kanssa. Se kattaa AI-sovellusten rakentamisen aivan perusteet LangChain4j:llä ja GitHub-malleilla. Seuraavissa moduuleissa käytät Azure OpenAI:ta LangChain4j:n kanssa rakentaaksesi kehittyneempiä sovelluksia.

## What is LangChain4j?

LangChain4j on Java-kirjasto, joka yksinkertaistaa tekoälypohjaisten sovellusten rakentamista. HTTP-asiakkaiden ja JSON-parsinnan sijaan työskentelet puhtaiden Java-rajapintojen kanssa.

LangChainin "ketju" viittaa useiden komponenttien ketjuttamiseen – voit ketjuttaa kehotteen malliin ja parseriin tai ketjuttaa useita tekoälykutsuja, joissa yhden tulos syötetään seuraavaan syötteeseen. Tämä pikaopas keskittyy perusteisiin ennen monimutkaisempien ketjujen tutkimista.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1e961a13c73d45cfa305fd03d81bd11e5d34d0e36ed28a44d.fi.png" alt="LangChain4j Chaining Concept" width="800"/>

*Komponenttien ketjuttaminen LangChain4j:ssä – rakennuspalikat yhdistyvät luodakseen tehokkaita tekoälytyönkulkuja*

Käytämme kolmea ydinkomponenttia:

**ChatLanguageModel** – Rajapinta tekoälymallin vuorovaikutuksille. Kutsu `model.chat("prompt")` ja saat vastausmerkkijonon. Käytämme `OpenAiOfficialChatModel`-luokkaa, joka toimii OpenAI-yhteensopivien päätepisteiden, kuten GitHub-mallien, kanssa.

**AiServices** – Luo tyyppiturvallisia tekoälypalvelujen rajapintoja. Määrittele metodit, merkitse ne `@Tool`-annotaatiolla, ja LangChain4j hoitaa orkestroinnin. Tekoäly kutsuu automaattisesti Java-metodejasi tarpeen mukaan.

**MessageWindowChatMemory** – Säilyttää keskusteluhistorian. Ilman tätä jokainen pyyntö on itsenäinen. Tämän kanssa tekoäly muistaa aiemmat viestit ja ylläpitää kontekstia useiden vuorojen ajan.

<img src="../../../translated_images/architecture.eedc993a1c57683951f20244f652fc7a9853f571eea835bc2b2828cf0dbf62d0.fi.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j-arkkitehtuuri – ydinkomponentit toimivat yhdessä tehostaakseen tekoälysovelluksiasi*

## LangChain4j Dependencies

Tämä pikaopas käyttää kahta Maven-riippuvuutta [`pom.xml`](../../../00-quick-start/pom.xml) -tiedostossa:

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

`langchain4j-open-ai-official`-moduuli tarjoaa `OpenAiOfficialChatModel`-luokan, joka yhdistää OpenAI-yhteensopiviin API:hin. GitHub-mallit käyttävät samaa API-muotoa, joten erillistä sovitinta ei tarvita – osoita vain perus-URL `https://models.github.ai/inference`-osoitteeseen.

## Prerequisites

**Käytätkö Dev Containeria?** Java ja Maven ovat jo asennettuina. Tarvitset vain GitHub Personal Access Tokenin.

**Paikallinen kehitys:**
- Java 21+, Maven 3.9+
- GitHub Personal Access Token (ohjeet alla)

> **Huom:** Tämä moduuli käyttää GitHub-malleista `gpt-4.1-nano`-mallia. Älä muuta mallin nimeä koodissa – se on konfiguroitu toimimaan GitHubin saatavilla olevien mallien kanssa.

## Setup

### 1. Get Your GitHub Token

1. Mene osoitteeseen [GitHub Settings → Personal Access Tokens](https://github.com/settings/personal-access-tokens)
2. Klikkaa "Generate new token"
3. Aseta kuvaava nimi (esim. "LangChain4j Demo")
4. Aseta vanhenemisaika (7 päivää suositeltu)
5. "Account permissions" -kohdassa etsi "Models" ja aseta se "Read-only"
6. Klikkaa "Generate token"
7. Kopioi ja tallenna token – et näe sitä uudelleen

### 2. Set Your Token

**Vaihtoehto 1: VS Code (suositeltu)**

Jos käytät VS Codea, lisää token projektin juureen `.env`-tiedostoon:

Jos `.env`-tiedostoa ei ole, kopioi `.env.example` tiedostoksi `.env` tai luo uusi `.env`-tiedosto projektin juureen.

**Esimerkki `.env`-tiedostosta:**
```bash
# Tiedostossa /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Sitten voit yksinkertaisesti klikata hiiren oikealla mitä tahansa demotiedostoa (esim. `BasicChatDemo.java`) Explorerissa ja valita **"Run Java"** tai käyttää käynnistyskonfiguraatioita Run and Debug -paneelista.

**Vaihtoehto 2: Komentorivi**

Aseta token ympäristömuuttujaksi:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Run the Examples

**VS Code:** Klikkaa hiiren oikealla mitä tahansa demotiedostoa Explorerissa ja valitse **"Run Java"**, tai käytä Run and Debug -paneelin käynnistyskonfiguraatioita (muista lisätä token `.env`-tiedostoon ensin).

**Maven:** Vaihtoehtoisesti voit ajaa komentoriviltä:

### 1. Basic Chat

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Prompt Patterns

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Näyttää zero-shot, few-shot, chain-of-thought ja roolipohjaiset kehotteet.

### 3. Function Calling

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

Tekoäly kutsuu automaattisesti Java-metodejasi tarpeen mukaan.

### 4. Document Q&A (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Kysy kysymyksiä `document.txt`-tiedoston sisällöstä.

## What Each Example Shows

**Basic Chat** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Aloita tästä nähdäksesi LangChain4j:n yksinkertaisimmillaan. Luot `OpenAiOfficialChatModel`-instanssin, lähetät kehotteen `.chat()`-metodilla ja saat vastauksen. Tämä näyttää perustan: miten alustaa mallit mukautetuilla päätepisteillä ja API-avaimilla. Kun ymmärrät tämän mallin, kaikki muu rakentuu sen päälle.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Kokeile [GitHub Copilot](https://github.com/features/copilot) Chatin kanssa:** Avaa [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) ja kysy:
> - "Miten vaihtaisin GitHub-malleista Azure OpenAI:hin tässä koodissa?"
> - "Mitä muita parametreja voin määrittää OpenAiOfficialChatModel.builder():ssa?"
> - "Miten lisään suoratoistovastaukset odottamisen sijaan?"

**Prompt Engineering** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Nyt kun tiedät miten puhua mallille, tutustutaan siihen, mitä sanot sille. Tämä demo käyttää samaa malliasetusta, mutta näyttää neljä erilaista kehotemallia. Kokeile zero-shot-kehotteita suoriin ohjeisiin, few-shot-kehotteita, jotka oppivat esimerkeistä, chain-of-thought-kehotteita, jotka paljastavat päättelyvaiheet, ja roolipohjaisia kehotteita, jotka asettavat kontekstin. Näet, miten sama malli antaa dramaattisesti erilaisia tuloksia sen mukaan, miten pyyntö muotoillaan.

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

> **🤖 Kokeile [GitHub Copilot](https://github.com/features/copilot) Chatin kanssa:** Avaa [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) ja kysy:
> - "Mikä on ero zero-shot- ja few-shot-kehotteiden välillä, ja milloin kumpaakin tulisi käyttää?"
> - "Miten lämpötila-parametri vaikuttaa mallin vastauksiin?"
> - "Mitkä ovat keinoja estää kehotteiden injektointihyökkäyksiä tuotannossa?"
> - "Miten luon uudelleenkäytettäviä PromptTemplate-objekteja yleisiin malleihin?"

**Tool Integration** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Tässä LangChain4j muuttuu tehokkaaksi. Käytät `AiServices`-luokkaa luodaksesi tekoälyavustajan, joka voi kutsua Java-metodejasi. Merkitse metodit `@Tool("kuvaus")`-annotaatiolla, ja LangChain4j hoitaa loput – tekoäly päättää automaattisesti, milloin käyttää kutakin työkalua käyttäjän kysymyksen perusteella. Tämä demonstroi funktiokutsuja, keskeistä tekniikkaa tekoälyn rakentamisessa, joka voi toimia, ei vain vastata kysymyksiin.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Kokeile [GitHub Copilot](https://github.com/features/copilot) Chatin kanssa:** Avaa [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) ja kysy:
> - "Miten @Tool-annotaatio toimii ja mitä LangChain4j tekee sen kanssa taustalla?"
> - "Voiko tekoäly kutsua useita työkaluja peräkkäin ratkaistakseen monimutkaisia ongelmia?"
> - "Mitä tapahtuu, jos työkalu heittää poikkeuksen – miten virheitä tulisi käsitellä?"
> - "Miten integroisin oikean API:n tämän laskin-esimerkin sijaan?"

**Document Q&A (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Tässä näet RAG:n (retrieval-augmented generation) perustan. Mallin koulutusdataan luottamisen sijaan lataat sisältöä tiedostosta [`document.txt`](../../../00-quick-start/document.txt) ja sisällytät sen kehotteeseen. Tekoäly vastaa dokumenttisi perusteella, ei yleisen tietämyksensä pohjalta. Tämä on ensimmäinen askel kohti järjestelmiä, jotka voivat työskennellä omien tietojesi kanssa.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Huom:** Tämä yksinkertainen lähestymistapa lataa koko dokumentin kehotteeseen. Suurilla tiedostoilla (>10KB) ylität kontekstirajat. Moduuli 03 käsittelee pilkkomista ja vektorihakua tuotantotason RAG-järjestelmiin.

> **🤖 Kokeile [GitHub Copilot](https://github.com/features/copilot) Chatin kanssa:** Avaa [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) ja kysy:
> - "Miten RAG estää tekoälyn harhoja verrattuna mallin koulutusdataan perustuvaan käyttöön?"
> - "Mikä on ero tämän yksinkertaisen lähestymistavan ja vektoriupotusten käytön välillä haussa?"
> - "Miten skaalaisin tämän käsittelemään useita dokumentteja tai suurempia tietokantoja?"
> - "Mitkä ovat parhaat käytännöt kehotteen rakenteen suhteen, jotta tekoäly käyttää vain annettua kontekstia?"

## Debugging

Esimerkeissä on `.logRequests(true)` ja `.logResponses(true)` näyttääksesi API-kutsut konsolissa. Tämä auttaa vianmäärityksessä, kuten todennusvirheissä, rajoituksissa tai odottamattomissa vastauksissa. Poista nämä liput tuotannossa vähentääksesi lokin määrää.

## Next Steps

**Seuraava moduuli:** [01-introduction - Getting Started with LangChain4j and gpt-5 on Azure](../01-introduction/README.md)

---

**Navigointi:** [← Takaisin pääsivulle](../README.md) | [Seuraava: Module 01 - Introduction →](../01-introduction/README.md)

---

## Troubleshooting

### First-Time Maven Build

**Ongelma:** Ensimmäinen `mvn clean compile` tai `mvn package` kestää kauan (10-15 minuuttia)

**Syy:** Mavenin täytyy ladata kaikki projektin riippuvuudet (Spring Boot, LangChain4j-kirjastot, Azure SDK:t jne.) ensimmäisellä kerralla.

**Ratkaisu:** Tämä on normaalia. Seuraavat kerrat ovat paljon nopeampia, koska riippuvuudet ovat välimuistissa paikallisesti. Latausaika riippuu verkkoyhteytesi nopeudesta.

### PowerShell Maven Command Syntax

**Ongelma:** Maven-komennot epäonnistuvat virheellä `Unknown lifecycle phase ".mainClass=..."`

**Syy:** PowerShell tulkitsee `=`-merkin muuttujan arvon asetteluksi, mikä rikkoo Mavenin ominaisuuksien syntaksin.

**Ratkaisu:** Käytä pysäytysparsintaoperaattoria `--%` ennen Maven-komentoa:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

`--%`-operaattori kertoo PowerShellille, että kaikki jäljellä olevat argumentit välitetään suoraan Mavenille ilman tulkintaa.

### Windows PowerShell Emoji Display

**Ongelma:** Tekoälyn vastaukset näyttävät roska-merkkejä (esim. `????` tai `â??`) emojeiden sijaan PowerShellissä

**Syy:** PowerShellin oletuskoodaus ei tue UTF-8-emojia

**Ratkaisu:** Suorita tämä komento ennen Java-sovellusten ajamista:
```cmd
chcp 65001
```

Tämä pakottaa UTF-8-koodauksen terminaalissa. Vaihtoehtoisesti käytä Windows Terminalia, joka tukee Unicodea paremmin.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastuuvapauslauseke**:  
Tämä asiakirja on käännetty käyttämällä tekoälypohjaista käännöspalvelua [Co-op Translator](https://github.com/Azure/co-op-translator). Vaikka pyrimme tarkkuuteen, huomioithan, että automaattikäännöksissä saattaa esiintyä virheitä tai epätarkkuuksia. Alkuperäistä asiakirjaa sen alkuperäiskielellä tulee pitää virallisena lähteenä. Tärkeissä asioissa suositellaan ammattimaista ihmiskäännöstä. Emme ole vastuussa tämän käännöksen käytöstä aiheutuvista väärinymmärryksistä tai tulkinnoista.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->