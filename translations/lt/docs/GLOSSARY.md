<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:24:55+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "lt"
}
-->
# LangChain4j Žodynas

## Turinys

- [Pagrindinės sąvokos](../../../docs)
- [LangChain4j komponentai](../../../docs)
- [AI/ML sąvokos](../../../docs)
- [Promptų inžinerija](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agentai ir įrankiai](../../../docs)
- [Modelio konteksto protokolas (MCP)](../../../docs)
- [Azure paslaugos](../../../docs)
- [Testavimas ir vystymas](../../../docs)

Greita terminų ir sąvokų, naudojamų viso kurso metu, nuoroda.

## Pagrindinės sąvokos

**AI agentas** – Sistema, kuri naudoja DI savarankiškam mąstymui ir veiksmams. [Modulis 04](../04-tools/README.md)

**Grandinė** – Operacijų seka, kurioje išvestis perduodama kitam žingsniui.

**Dalijimas į dalis** – Dokumentų skaidymas į mažesnes dalis. Tipiška: 300-500 žetonų su persidengimu. [Modulis 03](../03-rag/README.md)

**Konteksto langas** – Maksimalus žetonų skaičius, kurį modelis gali apdoroti. GPT-5: 400K žetonų.

**Įterpimai** – Skaitmeniniai vektoriai, atspindintys teksto prasmę. [Modulis 03](../03-rag/README.md)

**Funkcijų kvietimas** – Modelis generuoja struktūruotus užklausimus išoriniams funkcijų kvietimams. [Modulis 04](../04-tools/README.md)

**Halucinacija** – Kai modeliai generuoja neteisingą, bet įtikinamą informaciją.

**Promptas** – Teksto įvestis kalbos modeliui. [Modulis 02](../02-prompt-engineering/README.md)

**Semantinis paieška** – Paieška pagal prasmę naudojant įterpimus, o ne raktinius žodžius. [Modulis 03](../03-rag/README.md)

**Būsenos palaikymas vs be būsenos** – Be būsenos: be atminties. Su būsena: palaiko pokalbio istoriją. [Modulis 01](../01-introduction/README.md)

**Žetonai** – Pagrindiniai teksto vienetai, kuriuos apdoroja modeliai. Įtakoja kainas ir ribas. [Modulis 01](../01-introduction/README.md)

**Įrankių grandinavimas** – Sekantis įrankių vykdymas, kurio išvestis informuoja kitą kvietimą. [Modulis 04](../04-tools/README.md)

## LangChain4j komponentai

**AiServices** – Kuria tipui saugius DI paslaugų sąsajas.

**OpenAiOfficialChatModel** – Vieningas klientas OpenAI ir Azure OpenAI modeliams.

**OpenAiOfficialEmbeddingModel** – Kuria įterpimus naudojant OpenAI Official klientą (palaiko tiek OpenAI, tiek Azure OpenAI).

**ChatModel** – Pagrindinė kalbos modelių sąsaja.

**ChatMemory** – Palaiko pokalbio istoriją.

**ContentRetriever** – Randa aktualias dokumentų dalis RAG.

**DocumentSplitter** – Skaido dokumentus į dalis.

**EmbeddingModel** – Paverčia tekstą į skaitmeninius vektorius.

**EmbeddingStore** – Saugo ir atkuria įterpimus.

**MessageWindowChatMemory** – Palaiko slenkantį langą su naujausiomis žinutėmis.

**PromptTemplate** – Kuria pakartotinai naudojamus promptus su `{{variable}}` vietos žymėmis.

**TextSegment** – Teksto dalis su metaduomenimis. Naudojama RAG.

**ToolExecutionRequest** – Atstovauja įrankio vykdymo užklausą.

**UserMessage / AiMessage / SystemMessage** – Pokalbio žinučių tipai.

## AI/ML sąvokos

**Few-Shot mokymasis** – Pateikiami pavyzdžiai promptuose. [Modulis 02](../02-prompt-engineering/README.md)

**Didelis kalbos modelis (LLM)** – DI modeliai, apmokyti dideliais teksto duomenimis.

**Mąstymo pastangos** – GPT-5 parametras, kontroliuojantis mąstymo gilumą. [Modulis 02](../02-prompt-engineering/README.md)

**Temperatūra** – Kontroliuoja išvesties atsitiktinumą. Žema = deterministinė, aukšta = kūrybiška.

**Vektorinė duomenų bazė** – Specializuota duomenų bazė įterpimams. [Modulis 03](../03-rag/README.md)

**Zero-Shot mokymasis** – Užduočių atlikimas be pavyzdžių. [Modulis 02](../02-prompt-engineering/README.md)

## Promptų inžinerija - [Modulis 02](../02-prompt-engineering/README.md)

**Grandinės mąstymas** – Žingsnis po žingsnio mąstymas geresniam tikslumui.

**Apribota išvestis** – Nustatytas konkretus formatas ar struktūra.

**Didelis entuziazmas** – GPT-5 modelio šablonas kruopščiam mąstymui.

**Mažas entuziazmas** – GPT-5 modelio šablonas greitiems atsakymams.

**Daugiakartis pokalbis** – Konteksto palaikymas per keitimąsi žinutėmis.

**Rolės pagrindu kuriamas promptas** – Modelio asmenybės nustatymas per sistemos žinutes.

**Savi-refleksija** – Modelis vertina ir tobulina savo išvestį.

**Struktūruota analizė** – Fiksuota vertinimo sistema.

**Užduoties vykdymo šablonas** – Planuoti → Vykdyti → Apibendrinti.

## RAG (Retrieval-Augmented Generation) - [Modulis 03](../03-rag/README.md)

**Dokumentų apdorojimo kanalas** – Įkelti → suskaidyti → įterpti → saugoti.

**Atminties įterpimų saugykla** – Nepastovi saugykla testavimui.

**RAG** – Derina paiešką su generavimu, kad pagrįstų atsakymus.

**Panašumo balas** – Semantinio panašumo matas (0-1).

**Šaltinio nuoroda** – Metaduomenys apie rastą turinį.

## Agentai ir įrankiai - [Modulis 04](../04-tools/README.md)

**@Tool anotacija** – Pažymi Java metodus kaip DI kviečiamus įrankius.

**ReAct šablonas** – Mąstyti → Veikti → Stebėti → Kartoti.

**Sesijos valdymas** – Atskirti kontekstai skirtingiems vartotojams.

**Įrankis** – Funkcija, kurią gali kviesti DI agentas.

**Įrankio aprašymas** – Dokumentacija apie įrankio paskirtį ir parametrus.

## Modelio konteksto protokolas (MCP) - [Modulis 05](../05-mcp/README.md)

**Docker transportas** – MCP serveris Docker konteineryje.

**MCP** – Standartas DI programoms jungtis prie išorinių įrankių.

**MCP klientas** – Programa, jungiasi prie MCP serverių.

**MCP serveris** – Paslauga, teikianti įrankius per MCP.

**Serverio siunčiami įvykiai (SSE)** – Serverio ir kliento srautas per HTTP.

**Stdio transportas** – Serveris kaip subprocess per stdin/stdout.

**Srautinio HTTP transportas** – HTTP su SSE realaus laiko komunikacijai.

**Įrankių atradimas** – Klientas užklausia serverį apie prieinamus įrankius.

## Azure paslaugos - [Modulis 01](../01-introduction/README.md)

**Azure AI Search** – Debesų paieška su vektorinėmis galimybėmis. [Modulis 03](../03-rag/README.md)

**Azure Developer CLI (azd)** – Diegia Azure išteklius.

**Azure OpenAI** – Microsoft įmonių DI paslauga.

**Bicep** – Azure infrastruktūros kaip kodo kalba. [Infrastruktūros vadovas](../01-introduction/infra/README.md)

**Diegimo pavadinimas** – Modelio diegimo pavadinimas Azure.

**GPT-5** – Naujausias OpenAI modelis su mąstymo valdymu. [Modulis 02](../02-prompt-engineering/README.md)

## Testavimas ir vystymas - [Testavimo vadovas](TESTING.md)

**Dev konteineris** – Konteinerizuota vystymo aplinka. [Konfigūracija](../../../.devcontainer/devcontainer.json)

**GitHub modeliai** – Nemokama DI modelių žaidimų aikštelė. [Modulis 00](../00-quick-start/README.md)

**Atminties testavimas** – Testavimas su atminties saugykla.

**Integracinis testavimas** – Testavimas su realia infrastruktūra.

**Maven** – Java kūrimo automatizavimo įrankis.

**Mockito** – Java imitavimo sistema.

**Spring Boot** – Java programų karkasas. [Modulis 01](../01-introduction/README.md)

**Testcontainers** – Docker konteineriai testuose.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Atsakomybės apribojimas**:  
Šis dokumentas buvo išverstas naudojant dirbtinio intelekto vertimo paslaugą [Co-op Translator](https://github.com/Azure/co-op-translator). Nors stengiamės užtikrinti tikslumą, prašome atkreipti dėmesį, kad automatiniai vertimai gali turėti klaidų ar netikslumų. Originalus dokumentas gimtąja kalba turėtų būti laikomas autoritetingu šaltiniu. Svarbiai informacijai rekomenduojama naudoti profesionalų žmogaus vertimą. Mes neatsakome už bet kokius nesusipratimus ar neteisingus aiškinimus, kylančius dėl šio vertimo naudojimo.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->