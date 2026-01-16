<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-31T02:07:50+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "fi"
}
-->
# LangChain4j-sovellusten testaus

## Sisällysluettelo

- [Nopea aloitus](../../../docs)
- [Mitä testit kattavat](../../../docs)
- [Testien suorittaminen](../../../docs)
- [Testien suorittaminen VS Codessa](../../../docs)
- [Testausmallit](../../../docs)
- [Testausfilosofia](../../../docs)
- [Seuraavat askeleet](../../../docs)

Tämä opas opastaa läpi testit, jotka osoittavat, miten testata tekoälysovelluksia ilman API-avaimia tai ulkopuolisia palveluja.

## Nopea aloitus

Suorita kaikki testit yhdellä komennolla:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/fi/test-results.ea5c98d8f3642043.png" alt="Onnistuneet testitulokset" width="800"/>

*Onnistunut testisuoritus: kaikki testit läpäisevät ilman virheitä*

## Mitä testit kattavat

Tämä kurssi keskittyy paikallisesti suoritettaviin **yksikkötestauksiin**. Jokainen testi esittelee erillisen LangChain4j-käsitteen itsenäisesti.

<img src="../../../translated_images/fi/testing-pyramid.2dd1079a0481e53e.png" alt="Testauskolmio" width="800"/>

*Testauskolmio, joka kuvastaa tasapainoa yksikkötestien (nopeat, eristetyt), integraatiotestien (todelliset komponentit) ja loppukäyttäjätestien välillä. Tämä opetus kattaa yksikkötestauksen.*

| Moduuli | Testit | Painopiste | Keskeiset tiedostot |
|--------|-------|-------|-----------|
| **00 - Nopea aloitus** | 6 | Prompt-mallit ja muuttujien korvaaminen | `SimpleQuickStartTest.java` |
| **01 - Johdanto** | 8 | Keskustelun muisti ja tilallinen keskustelu | `SimpleConversationTest.java` |
| **02 - Kehotesuunnittelu** | 12 | GPT-5-mallit, innokkuustasot, jäsennelty tulostus | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | Dokumenttien tuonti, upotukset, samankaltaisuushaku | `DocumentServiceTest.java` |
| **04 - Työkalut** | 12 | Funktiokutsut ja työkaluketjuttaminen | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | Model Context Protocol stdio-siirrolla | `SimpleMcpTest.java` |

## Testien suorittaminen

**Suorita kaikki testit juurihakemistosta:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**Suorita testit tietylle moduulille:**

**Bash:**
```bash
cd 01-introduction && mvn test
# Tai root-käyttäjänä
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# Tai root-käyttäjänä
mvn --% test -pl 01-introduction
```

**Suorita yksittäinen testiluokka:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**Suorita tietty testimetodi:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#pitäisikö säilyttää keskusteluhistoria
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#pitäisi säilyttää keskusteluhistoria
```

## Testien suorittaminen VS Codessa

Jos käytät Visual Studio Codea, Test Explorer tarjoaa graafisen käyttöliittymän testien suorittamiseen ja virheenkorjaukseen.

<img src="../../../translated_images/fi/vscode-testing.f02dd5917289dced.png" alt="VS Code Test Explorer" width="800"/>

*VS Code Test Explorer näyttää testipuun kaikilla Java-testiluokilla ja yksittäisillä testimetodeilla*

**Testien suorittaminen VS Codessa:**

1. Avaa Test Explorer napsauttamalla koeputki-ikonia Activity Barissa
2. Laajenna testipuuta nähdäksesi kaikki moduulit ja testiluokat
3. Napsauta testin vieressä olevaa toisto-painiketta suorittaaksesi sen yksitellen
4. Napsauta "Run All Tests" suorittaaksesi koko testisarjan
5. Napsauta hiiren oikealla mitä tahansa testiä ja valitse "Debug Test" asettaaksesi breakpointteja ja ajaaksesi koodia vaiheittain

Test Explorer näyttää vihreät valintamerkit läpäistyistä testeistä ja tarjoaa yksityiskohtaiset virheilmoitukset, kun testit epäonnistuvat.

## Testausmallit

### Malli 1: Kehotepohjien testaaminen

Yksinkertaisin malli testaa kehotepohjia kutsumatta mitään tekoälymallia. Varmistat, että muuttujien korvaaminen toimii oikein ja kehotteet muotoillaan odotetulla tavalla.

<img src="../../../translated_images/fi/prompt-template-testing.b902758ddccc8dee.png" alt="Kehotepohjien testaus" width="800"/>

*Kehotepohjien testaaminen, joka näyttää muuttujien korvausketjun: malli paikkamerkkeineen → arvot käytetty → muotoiltu tulos varmennettu*

```java
@Test
@DisplayName("Should format prompt template with variables")
void testPromptTemplateFormatting() {
    PromptTemplate template = PromptTemplate.from(
        "Best time to visit {{destination}} for {{activity}}?"
    );
    
    Prompt prompt = template.apply(Map.of(
        "destination", "Paris",
        "activity", "sightseeing"
    ));
    
    assertThat(prompt.text()).isEqualTo("Best time to visit Paris for sightseeing?");
}
```

Tämä testi sijaitsee tiedostossa `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`.

**Suorita se:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#kehotepohjan muotoilun testi
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#testi kehotemallin muotoilu
```

### Malli 2: Kielimallien mockkaaminen

Keskustelulogiikkaa testatessa käytä Mockitoa luodaksesi väärennettyjä malleja, jotka palauttavat ennalta määrätyt vastaukset. Tämä tekee testeistä nopeita, ilmaisia ja deterministisiä.

<img src="../../../translated_images/fi/mock-vs-real.3b8b1f85bfe6845e.png" alt="Mock vs Real API -vertailu" width="800"/>

*Vertailu, joka selittää miksi mockkeja suositaan testeissä: ne ovat nopeita, ilmaisia, deterministisiä eivätkä vaadi API-avaimia*

```java
@ExtendWith(MockitoExtension.class)
class SimpleConversationTest {
    
    private ConversationService conversationService;
    
    @Mock
    private OpenAiOfficialChatModel mockChatModel;
    
    @BeforeEach
    void setUp() {
        ChatResponse mockResponse = ChatResponse.builder()
            .aiMessage(AiMessage.from("This is a test response"))
            .build();
        when(mockChatModel.chat(anyList())).thenReturn(mockResponse);
        
        conversationService = new ConversationService(mockChatModel);
    }
    
    @Test
    void shouldMaintainConversationHistory() {
        String conversationId = conversationService.startConversation();
        
        ChatResponse mockResponse1 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 1"))
            .build();
        ChatResponse mockResponse2 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 2"))
            .build();
        ChatResponse mockResponse3 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 3"))
            .build();
        
        when(mockChatModel.chat(anyList()))
            .thenReturn(mockResponse1)
            .thenReturn(mockResponse2)
            .thenReturn(mockResponse3);

        conversationService.chat(conversationId, "First message");
        conversationService.chat(conversationId, "Second message");
        conversationService.chat(conversationId, "Third message");

        List<ChatMessage> history = conversationService.getHistory(conversationId);
        assertThat(history).hasSize(6); // 3 käyttäjäviestiä + 3 tekoälyviestiä
    }
}
```

Tämä malli esiintyy tiedostossa `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`. Mockki varmistaa johdonmukaisen käyttäytymisen, jotta voit tarkistaa, että muistin hallinta toimii oikein.

### Malli 3: Keskustelujen eristyksen testaaminen

Keskustelumuistin on pidettävä useiden käyttäjien kontekstit erillisinä. Tämä testi varmistaa, ettei keskustelujen konteksti sekoitu.

<img src="../../../translated_images/fi/conversation-isolation.e00336cf8f7a3e3f.png" alt="Keskustelujen eristys" width="800"/>

*Keskustelueristyksen testaus, joka näyttää erilliset muistivarastot eri käyttäjille kontekstin sekoittumisen estämiseksi*

```java
@Test
void shouldIsolateConversationsByid() {
    String conv1 = conversationService.startConversation();
    String conv2 = conversationService.startConversation();
    
    ChatResponse mockResponse = ChatResponse.builder()
        .aiMessage(AiMessage.from("Response"))
        .build();
    when(mockChatModel.chat(anyList())).thenReturn(mockResponse);

    conversationService.chat(conv1, "Message for conversation 1");
    conversationService.chat(conv2, "Message for conversation 2");

    List<ChatMessage> history1 = conversationService.getHistory(conv1);
    List<ChatMessage> history2 = conversationService.getHistory(conv2);
    
    assertThat(history1).hasSize(2);
    assertThat(history2).hasSize(2);
}
```

Jokainen keskustelu ylläpitää omaa itsenäistä historiaansa. Tuotantojärjestelmissä tämä eristys on kriittinen monikäyttäjäisissä sovelluksissa.

### Malli 4: Työkalujen testaaminen itsenäisesti

Työkalut ovat funktioita, joita tekoäly voi kutsua. Testaa niitä suoraan varmistaaksesi, että ne toimivat oikein riippumatta tekoälyn päätöksistä.

<img src="../../../translated_images/fi/tools-testing.3e1706817b0b3924.png" alt="Työkalujen testaus" width="800"/>

*Työkalujen itsenäinen testaus, joka näyttää mock-työkalun suorituksen ilman tekoälykutsuja liiketoimintalogiikan varmistamiseksi*

```java
@Test
void shouldConvertCelsiusToFahrenheit() {
    TemperatureTool tempTool = new TemperatureTool();
    String result = tempTool.celsiusToFahrenheit(25.0);
    assertThat(result).containsPattern("77[.,]0°F");
}

@Test
void shouldDemonstrateToolChaining() {
    WeatherTool weatherTool = new WeatherTool();
    TemperatureTool tempTool = new TemperatureTool();

    String weatherResult = weatherTool.getCurrentWeather("Seattle");
    assertThat(weatherResult).containsPattern("\\d+°C");

    String conversionResult = tempTool.celsiusToFahrenheit(22.0);
    assertThat(conversionResult).containsPattern("71[.,]6°F");
}
```

Nämä testit tiedostosta `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` validoivat työkalulogiiikan ilman tekoälyä. Ketjutusesimerkki osoittaa, miten yhden työkalun tulos syötetään toisen syötteeksi.

### Malli 5: Muistissa tapahtuva RAG-testaus

RAG-järjestelmät tarvitsevat perinteisesti vektorikannat ja upotuspalvelut. Muistissa tapahtuva malli sallii koko putken testaamisen ilman ulkopuolisia riippuvuuksia.

<img src="../../../translated_images/fi/rag-testing.ee7541b1e23934b1.png" alt="Muistissa tapahtuva RAG-testaus" width="800"/>

*Muistissa tapahtuvan RAG-testauksen työnkulku, joka näyttää dokumentin jäsennyksen, upotusten tallennuksen ja samankaltaisuushaun ilman tietokantaa*

```java
@Test
void testProcessTextDocument() {
    String content = "This is a test document.\nIt has multiple lines.";
    InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    
    DocumentService.ProcessedDocument result = 
        documentService.processDocument(inputStream, "test.txt");

    assertNotNull(result);
    assertTrue(result.segments().size() > 0);
    assertEquals("test.txt", result.segments().get(0).metadata().getString("filename"));
}
```

Tämä testi tiedostosta `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` luo dokumentin muistiin ja tarkistaa pilkkomisen ja metatietojen käsittelyn.

### Malli 6: MCP-integraatiotestaus

MCP-moduuli testaa Model Context Protocol -integraatiota käyttäen stdio-siirtokerrointa. Nämä testit varmistavat, että sovelluksesi pystyy käynnistämään ja kommunikoimaan MCP-palvelimien kanssa aliprosesseina.

Tiedoston `05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` testit validoivat MCP-asiakkaan käyttäytymistä.

**Suorita ne:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## Testausfilosofia

Testaa koodiasi, älä tekoälyä. Testiesi tulisi validoida kirjoittamasi koodin toiminta tarkistamalla, miten kehotteet muodostetaan, miten muistia hallitaan ja miten työkalut suoritetaan. Tekoälyn vastaukset vaihtelevat eivätkä niiden pitäisi olla osa testien väittämiä. Kysy itseltäsi, korvaako kehotepohjasi muuttujat oikein, älä sitä, antaaako tekoäly oikeaa vastausta.

Käytä mockkeja kielimalleille. Ne ovat ulkoisia riippuvuuksia, jotka ovat hitaita, kalliita ja epä-deterministisiä. Mockkaus tekee testeistä nopeita (millisekunteja verrattuna sekunteihin), ilmaisia ilman API-kustannuksia ja deterministisiä, jolloin tulos on sama joka kerta.

Pidä testit riippumattomina. Jokaisen testin tulisi luoda omat tietonsa, ei luottaa muihin testeihin, ja siivota jälkensä. Testien tulisi läpäistä järjestyksestä riippumatta.

Testaa reunatapauksia onnellisen polun lisäksi. Kokeile tyhjiä syötteitä, erittäin suuria syötteitä, erikoismerkkejä, virheellisiä parametreja ja rajatapauksia. Nämä paljastavat usein bugeja, joita normaali käyttö ei paljasta.

Käytä kuvailevia nimiä. Vertaa `shouldMaintainConversationHistoryAcrossMultipleMessages()` ja `test1()`. Ensimmäinen kertoo täsmälleen, mitä testataan, mikä helpottaa vikojen selvittämistä huomattavasti.

## Seuraavat askeleet

Kun ymmärrät testausmallit, sukella syvemmälle jokaiseen moduuliin:

- **[00 - Nopea aloitus](../00-quick-start/README.md)** - Aloita kehotepohjien perusteista
- **[01 - Johdanto](../01-introduction/README.md)** - Opi keskustelumuistin hallinta
- **[02 - Kehotesuunnittelu](../02-prompt-engineering/README.md)** - Hallitse GPT-5-kehotemallit
- **[03 - RAG](../03-rag/README.md)** - Rakenna retrieval-augmented generation -järjestelmiä
- **[04 - Työkalut](../04-tools/README.md)** - Toteuta funktiokutsut ja työkaluketjut
- **[05 - MCP](../05-mcp/README.md)** - Integroi Model Context Protocol

Jokaisen moduulin README tarjoaa yksityiskohtaiset selitykset täällä testatuista käsitteistä.

---

**Navigointi:** [← Takaisin pääsivulle](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Vastuuvapauslauseke:
Tämä asiakirja on käännetty tekoälykäännöspalvelulla [Co-op Translator](https://github.com/Azure/co-op-translator). Vaikka pyrimme tarkkuuteen, huomioithan, että automaattikäännöksissä saattaa esiintyä virheitä tai epätarkkuuksia. Alkuperäistä asiakirjaa sen alkuperäiskielellä tulee pitää määräävänä lähteenä. Tärkeää tietoa varten suositellaan ammattimaista ihmiskäännöstä. Emme vastaa mahdollisista väärinkäsityksistä tai virhetulkinnoista, jotka johtuvat tämän käännöksen käytöstä.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->