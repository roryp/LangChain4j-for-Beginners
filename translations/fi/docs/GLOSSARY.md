<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T02:04:31+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "fi"
}
-->
# LangChain4j Sanasto

## Sisällysluettelo

- [Peruskäsitteet](../../../docs)
- [LangChain4j-komponentit](../../../docs)
- [AI/ML-käsitteet](../../../docs)
- [Prompt-tekniikka](../../../docs)
- [RAG (Hakuavusteinen generointi)](../../../docs)
- [Agentit ja työkalut](../../../docs)
- [Mallin kontekstiprotokolla (MCP)](../../../docs)
- [Azure-palvelut](../../../docs)
- [Testaus ja kehitys](../../../docs)

Pikaviite kurssin aikana käytetyistä termeistä ja käsitteistä.

## Peruskäsitteet

**AI Agent** - Järjestelmä, joka käyttää tekoälyä päättelyyn ja toimii itsenäisesti. [Moduuli 04](../04-tools/README.md)

**Chain** - Toimintojen sarja, jossa yhden vaiheen lähtö syötetään seuraavaan vaiheeseen.

**Chunking** - Asiakirjojen jakaminen pienempiin osiin. Tyypillisesti 300–500 tokenia päällekkäisyyden kanssa. [Moduuli 03](../03-rag/README.md)

**Context Window** - Maksimi tokenien määrä, jonka malli voi käsitellä. GPT-5: 400K tokenia.

**Embeddings** - Tekstin merkitystä kuvaavat numeeriset vektorit. [Moduuli 03](../03-rag/README.md)

**Function Calling** - Malli tuottaa jäsenneltyjä pyyntöjä ulkoisten funktioiden kutsumiseen. [Moduuli 04](../04-tools/README.md)

**Hallucination** - Tilanne, jossa mallit tuottavat virheellistä mutta uskottavalta vaikuttavaa tietoa.

**Prompt** - Tekstisyöte kielimallille. [Moduuli 02](../02-prompt-engineering/README.md)

**Semantic Search** - Haulla merkityksen perusteella käyttämällä embeddings-vektoreita, ei avainsanoja. [Moduuli 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ei muistia. Stateful: ylläpitää keskusteluhistoriaa. [Moduuli 01](../01-introduction/README.md)

**Tokens** - Perusyksiköt, joita mallit käsittelevät. Vaikuttaa kustannuksiin ja rajoihin. [Moduuli 01](../01-introduction/README.md)

**Tool Chaining** - Työkalujen peräkkäinen suoritus, jossa yhden työkalun tulos ohjaa seuraavaa kutsua. [Moduuli 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Luo tyyppiturvallisia AI-palveluiden rajapintoja.

**OpenAiOfficialChatModel** - Yhtenäinen asiakas OpenAI:n ja Azure OpenAI:n mallien käyttöön.

**OpenAiOfficialEmbeddingModel** - Luo embeddings-vektoreita käyttäen OpenAI Official -asiakasta (tukee sekä OpenAI:ta että Azure OpenAI:ta).

**ChatModel** - Keskeinen rajapinta kielimalleille.

**ChatMemory** - Ylläpitää keskusteluhistoriaa.

**ContentRetriever** - Löytää RAGia varten relevantteja dokumenttien lohkoja.

**DocumentSplitter** - Jakaa dokumentit lohkoihin.

**EmbeddingModel** - Muuntaa tekstiä numeerisiksi vektoreiksi.

**EmbeddingStore** - Tallentaa ja hakee embeddings-vektoreita.

**MessageWindowChatMemory** - Ylläpitää liukuvaa ikkunaa viimeisimmistä viesteistä.

**PromptTemplate** - Luo uudelleenkäytettäviä promptteja käyttäen `{{variable}}`-paikkamerkkejä.

**TextSegment** - Tekstilohko metadatalla. Käytetään RAGissa.

**ToolExecutionRequest** - Edustaa työkalun suorituspyyntöä.

**UserMessage / AiMessage / SystemMessage** - Keskusteluviestityyppejä.

## AI/ML Concepts

**Few-Shot Learning** - Esimerkkien antaminen promptteihin. [Moduuli 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Tekoälymalleja, jotka on koulutettu laajoilla tekstiaineistoilla.

**Reasoning Effort** - GPT-5:n parametri, joka ohjaa ajattelun syvyyttä. [Moduuli 02](../02-prompt-engineering/README.md)

**Temperature** - Ohjaa vastausten satunnaisuutta. Matala = deterministinen, korkea = luova.

**Vector Database** - Erikoistunut tietokanta embeddings-vektoreille. [Moduuli 03](../03-rag/README.md)

**Zero-Shot Learning** - Tehtävien suorittaminen ilman esimerkkejä. [Moduuli 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Moduuli 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Askel askeleelta tehtävä päättely paremman tarkkuuden saavuttamiseksi.

**Constrained Output** - Tietyn muodon tai rakenteen pakottaminen.

**High Eagerness** - GPT-5:n malli perusteelliselle päättelylle.

**Low Eagerness** - GPT-5:n malli nopeille vastauksille.

**Multi-Turn Conversation** - Kontekstin ylläpitäminen useiden vuorovaikutusten yli.

**Role-Based Prompting** - Mallin roolin määrittely järjestelmäviestien avulla.

**Self-Reflection** - Malli arvioi ja parantaa omaa tuotostaan.

**Structured Analysis** - Kiinteä arviointikehys.

**Task Execution Pattern** - Suunnittele → Suorita → Tiivistä.

## RAG (Retrieval-Augmented Generation) - [Moduuli 03](../03-rag/README.md)

**Document Processing Pipeline** - Lataa → pilko → upota → tallenna.

**In-Memory Embedding Store** - Ei-pysyvä tallennus testausta varten.

**RAG** - Yhdistää haun ja generoinnin vastauksien perustelemista varten.

**Similarity Score** - Semanttisen samankaltaisuuden mitta (0–1).

**Source Reference** - Metatiedot haetusta sisällöstä.

## Agents and Tools - [Moduuli 04](../04-tools/README.md)

**@Tool Annotation** - Merkitsee Java-metodit tekoälyn kutsuttaviksi työkaluiksi.

**ReAct Pattern** - Päättely → Toimi → Havainnoi → Toista.

**Session Management** - Eri käyttäjille erilliset kontekstit.

**Tool** - Funktio, jonka AI-agentti voi kutsua.

**Tool Description** - Työkalun tarkoitusta ja parametreja kuvaava dokumentaatio.

## Model Context Protocol (MCP) - [Moduuli 05](../05-mcp/README.md)

**MCP** - Standardi AI-sovellusten yhdistämiseen ulkoisiin työkaluihin.

**MCP Client** - Asiakasohjelma, joka yhdistyy MCP-palvelimiin.

**MCP Server** - Palvelu, joka tarjoaa työkaluja MCP:n kautta.

**Stdio Transport** - Palvelin aliohjelmana stdin/stdout-väylän kautta.

**Tool Discovery** - Asiakas kysyy palvelimelta saatavilla olevia työkaluja.

## Azure Services - [Moduuli 01](../01-introduction/README.md)

**Azure AI Search** - Pilvihaku vektoritoiminnoilla. [Moduuli 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Ottaa Azure-resurssit käyttöön.

**Azure OpenAI** - Microsoftin yritystason tekoälypalvelu.

**Bicep** - Azure-infrastruktuurin koodikieli. [Infrastruktuuriopas](../01-introduction/infra/README.md)

**Deployment Name** - Mallin käyttöönoton nimi Azure:ssa.

**GPT-5** - Uusin OpenAI-malli, jossa on päättelyn ohjaus. [Moduuli 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testausopas](TESTING.md)

**Dev Container** - Konttipohjainen kehitysympäristö. [Konfiguraatio](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Ilmainen AI-mallien testiympäristö. [Moduuli 00](../00-quick-start/README.md)

**In-Memory Testing** - Testaus muistissa tapahtuvalla tallennuksella.

**Integration Testing** - Integraatiotestaus oikealla infrastruktuurilla.

**Maven** - Java-rakennustyökalu.

**Mockito** - Java-mokkikehys.

**Spring Boot** - Java-sovelluskehys. [Moduuli 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Vastuuvapauslauseke:
Tämä asiakirja on käännetty käyttämällä tekoälypohjaista käännöspalvelua [Co-op Translator](https://github.com/Azure/co-op-translator). Vaikka pyrimme tarkkuuteen, huomioithan, että automaattiset käännökset voivat sisältää virheitä tai epätarkkuuksia. Alkuperäistä asiakirjaa sen alkuperäiskielellä tulee pitää määräävänä lähteenä. Kriittisen tiedon osalta suositellaan ammattimaista ihmiskäännöstä. Emme ole vastuussa mistään väärinymmärryksistä tai virheellisistä tulkinnoista, jotka johtuvat tämän käännöksen käytöstä.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->