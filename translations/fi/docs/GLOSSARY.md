<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:15:11+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "fi"
}
-->
# LangChain4j Sanasto

## Sisällysluettelo

- [Keskeiset käsitteet](../../../docs)
- [LangChain4j-komponentit](../../../docs)
- [AI/ML-käsitteet](../../../docs)
- [Suojamekanismit](../../../docs)
- [Prompt-suunnittelu](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agentit ja työkalut](../../../docs)
- [Agenttinen moduuli](../../../docs)
- [Mallin kontekstiprotokolla (MCP)](../../../docs)
- [Azure-palvelut](../../../docs)
- [Testaus ja kehitys](../../../docs)

Nopea hakemisto kurssilla käytetyille termeille ja käsitteille.

## Keskeiset käsitteet

**AI Agentti** - Järjestelmä, joka käyttää tekoälyä järkeilyyn ja itsenäiseen toimintaan. [Moduuli 04](../04-tools/README.md)

**Ketju** - Toimintojen sarja, jossa tulos syötetään seuraavaan vaiheeseen.

**Paloittelu** - Asiakirjojen jakaminen pienempiin osiin. Tyypillinen: 300–500 tokenia päällekkäisyydellä. [Moduuli 03](../03-rag/README.md)

**Kontekstin ikkuna** - Suurin tokenien määrä, jonka malli pystyy käsittelemään. GPT-5: 400 000 tokenia.

**Upotukset (Embeddings)** - Numeraalisia vektoreita, jotka kuvaavat tekstin merkitystä. [Moduuli 03](../03-rag/README.md)

**Funktiokutsu** - Malli tuottaa rakenteellisia pyyntöjä ulkoisten toimintojen kutsumiseen. [Moduuli 04](../04-tools/README.md)

**Hallusinaatio** - Kun mallit tuottavat virheellistä mutta uskottavaa tietoa.

**Prompt** - Tekstisyöte kielimallille. [Moduuli 02](../02-prompt-engineering/README.md)

**Semanttinen haku** - Hakua merkityksen perusteella upotuksia käyttäen, ei avainsanoilla. [Moduuli 03](../03-rag/README.md)

**Tila vs. tilaton** - Tilaton: ei muistia. Tila: säilyttää keskusteluhistorian. [Moduuli 01](../01-introduction/README.md)

**Tokenit** - Perusyksiköt, joita mallit käsittelevät. Vaikuttavat kustannuksiin ja rajoituksiin. [Moduuli 01](../01-introduction/README.md)

**Työkaluketjutus** - Työkalujen peräkkäinen suoritus, jossa tulos vaikuttaa seuraavaan kutsuun. [Moduuli 04](../04-tools/README.md)

## LangChain4j-komponentit

**AiServices** - Luo tyypiltään turvalliset tekoälypalvelurajapinnat.

**OpenAiOfficialChatModel** - Yhtenäinen asiakasrajapinta OpenAI:n ja Azure OpenAI:n malleille.

**OpenAiOfficialEmbeddingModel** - Luo upotuksia OpenAI Official -asiakkaalla (tukee sekä OpenAI:ta että Azure OpenAI:ta).

**ChatModel** - Ydinkäyttöliittymä kielimalleille.

**ChatMemory** - Säilyttää keskusteluhistorian.

**ContentRetriever** - Löytää relevantit asiakirjapalat RAG:ia varten.

**DocumentSplitter** - Jakaa asiakirjat paloihin.

**EmbeddingModel** - Muuntaa tekstin numeerisiksi vektoreiksi.

**EmbeddingStore** - Tallentaa ja noutaa upotuksia.

**MessageWindowChatMemory** - Säilyttää liukuvan ikkunan viimeisimmistä viesteistä.

**PromptTemplate** - Luo uudelleenkäytettäviä kehote-malleja `{{variable}}`-paikkamerkkeineen.

**TextSegment** - Tekstipala metatietoineen. Käytetään RAG:ssa.

**ToolExecutionRequest** - Edustaa työkalun suorituspyyntöä.

**UserMessage / AiMessage / SystemMessage** - Keskusteluviestien tyypit.

## AI/ML-käsitteet

**Few-Shot Learning** - Esimerkkien antaminen promteissa. [Moduuli 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Suuret kielimallit, jotka on koulutettu mittavalla tekstidatalla.

**Reasoning Effort** - GPT-5:n parametri, joka ohjaa ajattelun syvyyttä. [Moduuli 02](../02-prompt-engineering/README.md)

**Temperature** - Ohjaa tuloksen satunnaisuutta. Matala = deterministinen, korkea = luova.

**Vektoritietokanta** - Erikoistunut tietokanta upotuksia varten. [Moduuli 03](../03-rag/README.md)

**Zero-Shot Learning** - Suorittaa tehtäviä ilman esimerkkejä. [Moduuli 02](../02-prompt-engineering/README.md)

## Suojamekanismit - [Moduuli 00](../00-quick-start/README.md)

**Defense in Depth** - Monikerroksinen suojaus, joka yhdistää sovellustason suojarakenteet ja tarjoajan turvasuodattimet.

**Hard Block** - Tarjoaja antaa HTTP 400 -virheen vakavista sisältörikkomuksista.

**InputGuardrail** - LangChain4j-rajapinta käyttäjän syötteen tarkistukseen ennen kuin se saavuttaa LLM:n. Säästää kustannuksia ja latenssia estämällä haitalliset promptit varhaisessa vaiheessa.

**InputGuardrailResult** - Palautustyyppi suojamekanismin validoinnille: `success()` tai `fatal("peruste")`.

**OutputGuardrail** - Rajapinta tekoälyn vastausten validointiin ennen niiden palauttamista käyttäjälle.

**Provider Safety Filters** - Tekoälypalveluntarjoajien (esim. GitHub Models) sisäänrakennetut sisältösuodattimet, jotka havaitsevat rikkomukset API-tasolla.

**Soft Refusal** - Malli kieltäytyy kohteliaasti vastaamasta heittämättä virhettä.

## Prompt-suunnittelu - [Moduuli 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Askeltainen päättely parantaakseen tarkkuutta.

**Rajoitettu tuloste** - Tarkasti määritellyn muodon tai rakenteen pakottaminen.

**Korkea halukkuus** - GPT-5-kuvio perusteelliseen päättelyyn.

**Matala halukkuus** - GPT-5-kuvio nopeisiin vastauksiin.

**Monivaiheinen keskustelu** - Kontekstin ylläpito vaihtojen yli.

**Rooliperusteinen kehotus** - Mallipersonan asettaminen järjestelmäviestien avulla.

**Itsearviointi** - Malli arvioi ja parantaa omaa tulostaan.

**Rakenteellinen analyysi** - Kiinteä arviointikehys.

**Tehtävän suoritusmalli** - Suunnittele → Suorita → Tiivistä.

## RAG (Retrieval-Augmented Generation) - [Moduuli 03](../03-rag/README.md)

**Asiakirjaprosessointiputki** - Lataa → paloittelu → upotus → tallennus.

**Muistissa toimiva upotusvarasto** - Ei-pysyvä tallennus testiä varten.

**RAG** - Yhdistää hakemisen ja generoinnin vastauksien pohjaksi.

**Samanlaisuuspisteet** - Mitta (0–1) semanttisesta samankaltaisuudesta.

**Lähdeviite** - Metatiedot haetusta sisällöstä.

## Agentit ja työkalut - [Moduuli 04](../04-tools/README.md)

**@Tool-annotaatio** - Merkitsee Java-metodit tekoälyn kutsuttaviksi työkaluiksi.

**ReAct-kuvio** - Päättely → Toiminta → Havainnointi → Toisto.

**Istunnon hallinta** - Erilliset kontekstit eri käyttäjille.

**Työkalu** - Toiminto, jota AI-agentti voi kutsua.

**Työkalun kuvaus** - Dokumentaatio työkalun tarkoituksesta ja parametreista.

## Agenttinen moduuli - [Moduuli 05](../05-mcp/README.md)

**@Agent-annotaatio** - Merkitsee rajapinnat tekoälyagenteiksi deklaratiivisella käyttäytymismäärittelyllä.

**Agent Listener** - Koukku agentin suorituksen valvontaan `beforeAgentInvocation()` ja `afterAgentInvocation()` kautta.

**Agenttinen scope** - Jaettu muisti, johon agentit tallentavat tuloksia `outputKey`-avainsanalla muiden agenttien käytettäväksi.

**AgenticServices** - Tehdas agenttien luomiseen `agentBuilder()` ja `supervisorBuilder()` -menetelmillä.

**Ehdollinen työnkulku** - Reititys eri asiantuntija-agenttien välillä ehtojen mukaan.

**Ihmisen osallistuminen** - Työnkulku, jossa ihmiskäyttäjä tarkastaa ja hyväksyy sisällön vaiheittain.

**langchain4j-agentic** - Maven-riippuvuus deklaratiiviseen agenttien rakentamiseen (kokeellinen).

**Toistolohko** - Agentin suoritus toistetaan kunnes ehto täyttyy (esim. laatu ≥ 0,8).

**outputKey** - Agenttiannotaation parametri, joka määrittää tulosten tallennuspaikan Agentic Scopeen.

**Rinnakkainen työnkulku** - Useiden agenttien samanaikainen suoritus itsenäisiin tehtäviin.

**Vastausstrategia** - Tapa, jolla ohjaava agentti muodostaa lopullisen vastauksen: VIIMAINEN, YHTEENVETO tai PISTEYTETTY.

**Peräkkäinen työnkulku** - Agenttien suoritus järjestyksessä, tulosten kulkiessa seuraavaan vaiheeseen.

**Ohjaava agenttipatterni** - Edistynyt agenttikuva, jossa ohjaava LLM päättää dynaamisesti, mitä apu-agentteja kutsutaan.

## Mallin kontekstiprotokolla (MCP) - [Moduuli 05](../05-mcp/README.md)

**langchain4j-mcp** - Maven-riippuvuus MCP-integraatioon LangChain4j:ssa.

**MCP** - Model Context Protocol: standardi tekoälysovellusten liittämiseen ulkoisiin työkaluihin. Rakennetaan kerran, käytetään kaikkialla.

**MCP Client** - Sovellus, joka yhdistyy MCP-palvelimiin työkaluja löytääkseen ja käyttäen.

**MCP Server** - Palvelu, joka tarjoaa työkalut MCP:n kautta selkeästi kuvattuina ja parametritskeemoineen.

**McpToolProvider** - LangChain4j-komponentti, joka käärii MCP-työkalut tekoälypalveluiden ja agenttien käyttöön.

**McpTransport** - Rajapinta MCP-kommunikointiin. Toteutuksia ovat Stdio ja HTTP.

**Stdio Transport** - Paikallinen prosessikuljetus stdin/stdoutin kautta. Hyödyllinen tiedostojärjestelmän tai komentorivityökalujen käytössä.

**StdioMcpTransport** - LangChain4j:n toteutus, joka käynnistää MCP-palvelimen aliprosessina.

**Työkalujen löytäminen** - Asiakas kysyy palvelimelta saatavilla olevat työkalut kuvauksineen ja skeemoineen.

## Azure-palvelut - [Moduuli 01](../01-introduction/README.md)

**Azure AI Search** - Pilvipohjainen haku, jossa vektoriominaisuudet. [Moduuli 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure-resurssien käyttöönotto.

**Azure OpenAI** - Microsoftin yritystason tekoälypalvelu.

**Bicep** - Azure-infrastruktuurin koodikieli. [Infrastruktuuriohje](../01-introduction/infra/README.md)

**Deployment Name** - Mallin käyttöönoton nimi Azure:ssa.

**GPT-5** - Uusin OpenAI-malli päättelyohjauksella. [Moduuli 02](../02-prompt-engineering/README.md)

## Testaus ja kehitys - [Testausopas](TESTING.md)

**Dev Container** - Konttimuotoinen kehitysympäristö. [Konfiguraatio](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Ilmainen tekoälymallien leikkikenttä. [Moduuli 00](../00-quick-start/README.md)

**Muistissa testaus** - Testaus muistipohjaisella tallennuksella.

**Integraatiotestaus** - Testaus aidolla infrastruktuurilla.

**Maven** - Java-käännöstuki ja automatisointi.

**Mockito** - Java-mokkausevälineistö.

**Spring Boot** - Java-sovelluskehys. [Moduuli 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastuuvapauslauseke**:
Tämä asiakirja on käännetty tekoälykäännöspalvelulla [Co-op Translator](https://github.com/Azure/co-op-translator). Pyrimme tarkkuuteen, mutta ole hyvä ja huomioi, että automaattikäännöksissä saattaa esiintyä virheitä tai epätarkkuuksia. Alkuperäistä asiakirjaa omalla kielellään tulee pitää virallisena lähteenä. Tärkeissä tiedoissa suositellaan ammattilaisen tekemää ihmiskäännöstä. Emme ole vastuussa tämän käännöksen käytöstä aiheutuvista väärinymmärryksistä tai tulkinnoista.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->