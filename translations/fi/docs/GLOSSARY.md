<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:10:56+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "fi"
}
-->
# LangChain4j Sanasto

## Sisällysluettelo

- [Peruskäsitteet](../../../docs)
- [LangChain4j-komponentit](../../../docs)
- [AI/ML-käsitteet](../../../docs)
- [Prompt-suunnittelu](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agentit ja työkalut](../../../docs)
- [Mallin kontekstiprotokolla (MCP)](../../../docs)
- [Azure-palvelut](../../../docs)
- [Testaus ja kehitys](../../../docs)

Nopea viite kurssin aikana käytetyille termeille ja käsitteille.

## Peruskäsitteet

**AI Agentti** - Järjestelmä, joka käyttää tekoälyä päättelyyn ja itsenäiseen toimintaan. [Moduuli 04](../04-tools/README.md)

**Ketju** - Toimintojen sarja, jossa tulos syötetään seuraavaan vaiheeseen.

**Pilkkominen** - Asiakirjojen jakaminen pienempiin osiin. Tyypillisesti 300-500 tokenia päällekkäisyyksineen. [Moduuli 03](../03-rag/README.md)

**Kontekstin ikkuna** - Maksimimäärä tokeneita, jonka malli voi käsitellä. GPT-5: 400K tokenia.

**Upotukset** - Numeraaliset vektorit, jotka kuvaavat tekstin merkitystä. [Moduuli 03](../03-rag/README.md)

**Funktiokutsu** - Malli generoi rakenteellisia pyyntöjä ulkoisten funktioiden kutsumiseen. [Moduuli 04](../04-tools/README.md)

**Hallusinaatio** - Tilanne, jossa mallit tuottavat virheellistä mutta uskottavaa tietoa.

**Prompt** - Tekstisyöte kielimallille. [Moduuli 02](../02-prompt-engineering/README.md)

**Semanttinen haku** - Haku merkityksen perusteella upotuksia käyttäen, ei avainsanoilla. [Moduuli 03](../03-rag/README.md)

**Tilallinen vs tilaton** - Tilaton: ei muistia. Tilallinen: ylläpitää keskusteluhistoriaa. [Moduuli 01](../01-introduction/README.md)

**Tokenit** - Perusyksiköt, joita mallit käsittelevät. Vaikuttaa kustannuksiin ja rajoihin. [Moduuli 01](../01-introduction/README.md)

**Työkaluketjutus** - Työkalujen peräkkäinen suoritus, jossa tulos ohjaa seuraavaa kutsua. [Moduuli 04](../04-tools/README.md)

## LangChain4j-komponentit

**AiServices** - Luo tyyppiturvallisia tekoälypalvelurajapintoja.

**OpenAiOfficialChatModel** - Yhtenäinen asiakas OpenAI- ja Azure OpenAI -malleille.

**OpenAiOfficialEmbeddingModel** - Luo upotuksia OpenAI Official -asiakkaalla (tukee sekä OpenAI että Azure OpenAI).

**ChatModel** - Kielimallien ydintoteutus.

**ChatMemory** - Ylläpitää keskusteluhistoriaa.

**ContentRetriever** - Löytää relevantteja asiakirjan osia RAG:ia varten.

**DocumentSplitter** - Pilkkoo asiakirjat osiin.

**EmbeddingModel** - Muuntaa tekstin numeerisiksi vektoreiksi.

**EmbeddingStore** - Tallentaa ja hakee upotuksia.

**MessageWindowChatMemory** - Ylläpitää liukuvaa ikkunaa viimeisimmistä viesteistä.

**PromptTemplate** - Luo uudelleenkäytettäviä promptteja `{{variable}}`-paikkamerkeillä.

**TextSegment** - Tekstinpala metatiedoilla. Käytetään RAG:ssa.

**ToolExecutionRequest** - Edustaa työkalun suorituspyyntöä.

**UserMessage / AiMessage / SystemMessage** - Keskusteluviestien tyypit.

## AI/ML-käsitteet

**Few-Shot Learning** - Esimerkkien antaminen promptissa. [Moduuli 02](../02-prompt-engineering/README.md)

**Suuri kielimalli (LLM)** - Tekoälymalleja, jotka on koulutettu valtavalla tekstidatalla.

**Päättelyponnistus** - GPT-5:n parametri, joka ohjaa ajattelun syvyyttä. [Moduuli 02](../02-prompt-engineering/README.md)

**Lämpötila** - Ohjaa tuloksen satunnaisuutta. Matala=deterministinen, korkea=luova.

**Vektoritietokanta** - Erikoistunut tietokanta upotuksille. [Moduuli 03](../03-rag/README.md)

**Zero-Shot Learning** - Tehtävien suorittaminen ilman esimerkkejä. [Moduuli 02](../02-prompt-engineering/README.md)

## Prompt-suunnittelu - [Moduuli 02](../02-prompt-engineering/README.md)

**Ajatusketju** - Askeltainen päättely paremman tarkkuuden saavuttamiseksi.

**Rajoitettu tulos** - Tietyn muodon tai rakenteen pakottaminen.

**Korkea innokkuus** - GPT-5:n malli perusteelliselle päättelylle.

**Matala innokkuus** - GPT-5:n malli nopeille vastauksille.

**Monikierroksinen keskustelu** - Kontekstin ylläpito vaihdon aikana.

**Roolipohjainen prompttaus** - Mallin persoonan asettaminen järjestelmäviesteillä.

**Itsearviointi** - Malli arvioi ja parantaa omaa tuotostaan.

**Rakenteellinen analyysi** - Kiinteä arviointikehys.

**Tehtävän suoritusmalli** - Suunnittele → Suorita → Yhteenveto.

## RAG (Retrieval-Augmented Generation) - [Moduuli 03](../03-rag/README.md)

**Asiakirjan käsittelyputki** - Lataa → pilko → upota → tallenna.

**Muistissa oleva upotusvarasto** - Ei-pysyvä tallennus testaukseen.

**RAG** - Yhdistää haun ja generoinnin vastauksen perustaksi.

**Samanlaisuuspisteet** - Semanttisen samankaltaisuuden mitta (0-1).

**Lähdeviite** - Metatiedot haetusta sisällöstä.

## Agentit ja työkalut - [Moduuli 04](../04-tools/README.md)

**@Tool-annotaatio** - Merkitsee Java-metodit tekoälyn kutsuttaviksi työkaluiksi.

**ReAct-malli** - Päättely → Toiminta → Havainnointi → Toisto.

**Istunnon hallinta** - Eri käyttäjille erilliset kontekstit.

**Työkalu** - Funktio, jota AI-agentti voi kutsua.

**Työkalun kuvaus** - Dokumentaatio työkalun tarkoituksesta ja parametreista.

## Mallin kontekstiprotokolla (MCP) - [Moduuli 05](../05-mcp/README.md)

**Docker-siirto** - MCP-palvelin Docker-kontissa.

**MCP** - Standardi AI-sovellusten yhdistämiseen ulkoisiin työkaluihin.

**MCP-asiakas** - Sovellus, joka yhdistää MCP-palvelimiin.

**MCP-palvelin** - Palvelu, joka tarjoaa työkaluja MCP:n kautta.

**Server-Sent Events (SSE)** - Palvelimelta asiakkaalle HTTP:n yli striimaus.

**Stdio-siirto** - Palvelin aliohjelmana stdin/stdoutin kautta.

**Striimattava HTTP-siirto** - HTTP SSE:llä reaaliaikaiseen viestintään.

**Työkalujen löytäminen** - Asiakas kysyy palvelimelta saatavilla olevat työkalut.

## Azure-palvelut - [Moduuli 01](../01-introduction/README.md)

**Azure AI Search** - Pilvipohjainen haku vektoriominaisuuksilla. [Moduuli 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure-resurssien käyttöönotto.

**Azure OpenAI** - Microsoftin yritystason tekoälypalvelu.

**Bicep** - Azure-infrastruktuurin koodikieli. [Infrastruktuuriohje](../01-introduction/infra/README.md)

**Käyttöönoton nimi** - Mallin käyttöönoton nimi Azure:ssa.

**GPT-5** - Uusin OpenAI-malli päättelyohjauksella. [Moduuli 02](../02-prompt-engineering/README.md)

## Testaus ja kehitys - [Testausopas](TESTING.md)

**Dev Container** - Konttimuotoinen kehitysympäristö. [Konfiguraatio](../../../.devcontainer/devcontainer.json)

**GitHub-mallit** - Ilmainen tekoälymallien leikkikenttä. [Moduuli 00](../00-quick-start/README.md)

**Muistissa tapahtuva testaus** - Testaus muistipohjaisella tallennuksella.

**Integraatiotestaus** - Testaus oikealla infrastruktuurilla.

**Maven** - Java-rakennusautomaatio.

**Mockito** - Java-mokkikehys.

**Spring Boot** - Java-sovelluskehys. [Moduuli 01](../01-introduction/README.md)

**Testcontainers** - Docker-kontit testeissä.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastuuvapauslauseke**:  
Tämä asiakirja on käännetty käyttämällä tekoälypohjaista käännöspalvelua [Co-op Translator](https://github.com/Azure/co-op-translator). Pyrimme tarkkuuteen, mutta huomioithan, että automaattikäännöksissä saattaa esiintyä virheitä tai epätarkkuuksia. Alkuperäinen asiakirja sen alkuperäiskielellä on virallinen lähde. Tärkeissä asioissa suositellaan ammattimaista ihmiskäännöstä. Emme ole vastuussa tämän käännöksen käytöstä aiheutuvista väärinymmärryksistä tai tulkinnoista.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->