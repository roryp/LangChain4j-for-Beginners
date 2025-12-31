<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T06:43:35+00:00",
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

Greita nuoroda į terminus ir sąvokas, naudojamas visoje kursų medžiagoje.

## Pagrindinės sąvokos

**DI agentas** - Sistema, kuri naudoja DI, kad samprotautų ir veiksmų savarankiškai. [Modulis 04](../04-tools/README.md)

**Grandinė** - Operacijų seka, kurioje išvestis perduodama į kitą žingsnį.

**Skaidymas į gabalus** - Dokumentų suskaidymas į mažesnes dalis. Tipiška: 300–500 žetonų su persidengimu. [Modulis 03](../03-rag/README.md)

**Konteksto langas** - Maksimalus žetonų skaičius, kurį modelis gali apdoroti. GPT-5: 400K žetonų.

**Embeddingai** - Skaitmeniniai vektoriai, reprezentuojantys teksto prasmę. [Modulis 03](../03-rag/README.md)

**Funkcijų iškvietimas** - Modelis generuoja struktūruotus prašymus iškviesti išorines funkcijas. [Modulis 04](../04-tools/README.md)

**Halucinacija** - Kai modeliai generuoja neteisingą, bet įtikinamą informaciją.

**Užklausa (Prompt)** - Tekstas, pateikiamas kalbos modeliui. [Modulis 02](../02-prompt-engineering/README.md)

**Semantinė paieška** - Paieška pagal prasmes naudojant embeddingus, o ne raktinius žodžius. [Modulis 03](../03-rag/README.md)

**Be būsenos vs. su būsena** - Be būsenos: be atminties. Su būsena: saugo pokalbio istoriją. [Modulis 01](../01-introduction/README.md)

**Žetonai** - Pagrindiniai tekstiniai vienetai, kuriuos modeliai apdoroja. Veikia į kainas ir ribas. [Modulis 01](../01-introduction/README.md)

**Įrankų grandinimas** - Sekanti įrankių vykdymo tvarka, kur išvestis formuoja kitą kvietimą. [Modulis 04](../04-tools/README.md)

## LangChain4j komponentai

**AiServices** - Kuria tipui saugias DI paslaugų sąsajas.

**OpenAiOfficialChatModel** - Suvienytas klientas OpenAI ir Azure OpenAI modeliams.

**OpenAiOfficialEmbeddingModel** - Generuoja embeddingus naudojant OpenAI Official klientą (palaiko tiek OpenAI, tiek Azure OpenAI).

**ChatModel** - Pagrindinė sąsaja kalbos modeliams.

**ChatMemory** - Laiko pokalbio istoriją.

**ContentRetriever** - Randa aktualius dokumentų gabalus RAG procesui.

**DocumentSplitter** - Suskaido dokumentus į gabalus.

**EmbeddingModel** - Paverčia tekstą į skaitmeninius vektorius.

**EmbeddingStore** - Saugo ir gauna embeddingus.

**MessageWindowChatMemory** - Laiko slenkantį nesenų žinučių langą.

**PromptTemplate** - Kuria pakartotinai naudojamas užklausas su `{{variable}}` vietos rezervuarais.

**TextSegment** - Teksto gabalas su metaduomenimis. Naudojama RAG.

**ToolExecutionRequest** - Reprezentuoja įrankio vykdymo užklausą.

**UserMessage / AiMessage / SystemMessage** - Pokalbio žinučių tipai.

## AI/ML sąvokos

**Few-Shot Learning** - Pavyzdžių pateikimas užklausose. [Modulis 02](../02-prompt-engineering/README.md)

**Didelis kalbos modelis (LLM)** - DI modeliai, apmokyti dideliais teksto duomenimis.

**Samprotautinio pastangumo parametras (Reasoning Effort)** - GPT-5 parametras, kontroliuojantis mąstymo gilumą. [Modulis 02](../02-prompt-engineering/README.md)

**Temperatūra** - Kontroliuoja išvesties atsitiktinumą. Maža = deterministinis, didelė = kūrybiškas.

**Vektorinė duomenų bazė** - Specializuota duomenų bazė embeddingams. [Modulis 03](../03-rag/README.md)

**Zero-Shot Learning** - Užduočių atlikimas be pateiktų pavyzdžių. [Modulis 02](../02-prompt-engineering/README.md)

## Promptų inžinerija - [Modulis 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Žingsnis po žingsnio samprotavimas geresniam tikslumui.

**Apribota išvestis** - Priverstinai užtikrinti konkretų formatą ar struktūrą.

**Didelis atkaklumas (High Eagerness)** - GPT-5 modelio raštas išsamiam samprotavimui.

**Mažas atkaklumas (Low Eagerness)** - GPT-5 raštas greitiems atsakymams.

**Daugelio apsikeitimų pokalbis** - Konteksto palaikymas per keitimąsi žinutėmis.

**Veiklos vaidmens užklausimas** - Modelio personos nustatymas per sistemos žinutes.

**Saviglauda** - Modelis įvertina ir tobulina savo išvestį.

**Struktūruota analizė** - Fiksuota vertinimo sistema.

**Užduoties vykdymo modelis** - Planas → Vykdymas → Santrauka.

## RAG (Retrieval-Augmented Generation) - [Modulis 03](../03-rag/README.md)

**Dokumentų apdorojimo vamzdis** - Įkelti → suskaidyti → sudaryti embeddingus → saugoti.

**Atmintyje veikiantis embeddingų saugykla** - Nenuolatinė saugykla testavimui.

**RAG** - Kombinuoja paiešką su generavimu, kad atsakymai būtų pagrįsti šaltiniais.

**Panašumo balas** - Semantinio panašumo matas (0–1).

**Šaltinio nuoroda** - Metaduomenys apie gautą turinį.

## Agentai ir įrankiai - [Modulis 04](../04-tools/README.md)

**@Tool anotacija** - Pažymi Java metodus kaip DI iškviečiamus įrankius.

**ReAct modelis** - Samprotauk → Veik → Stebėk → Kartok.

**Sesijų valdymas** - Atskiri kontekstai skirtingiems vartotojams.

**Įrankis** - Funkcija, kurią DI agentas gali iškviesti.

**Įrankio aprašymas** - Dokumentacija apie įrankio paskirtį ir parametrus.

## Modelio konteksto protokolas (MCP) - [Modulis 05](../05-mcp/README.md)

**MCP** - Standartas, jungiantis DI programas su išoriniais įrankiais.

**MCP klientas** - Programa, kuri jungiasi prie MCP serverių.

**MCP serveris** - Tarnyba, teikianti įrankius per MCP.

**Stdio transportas** - Serveris kaip subprocess per stdin/stdout.

**Įrankių atradimas** - Klientas užklausia serverio apie turimus įrankius.

## Azure paslaugos - [Modulis 01](../01-introduction/README.md)

**Azure AI Search** - Debesų paieška su vektorinėmis galimybėmis. [Modulis 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Diegia Azure išteklius.

**Azure OpenAI** - Microsoft verslo DI paslauga.

**Bicep** - Azure infrastruktūros kaip kodo kalba. [Infrastructure Guide](../01-introduction/infra/README.md)

**Diegimo pavadinimas** - Modelio diegimo pavadinimas Azure aplinkoje.

**GPT-5** - Naujausias OpenAI modelis su samprotavimo kontrole. [Modulis 02](../02-prompt-engineering/README.md)

## Testavimas ir vystymas - [Testavimo vadovas](TESTING.md)

**Dev Container** - Kontainerizuota vystymo aplinka. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Nemokama DI modelių žaidimų aikštelė. [Modulis 00](../00-quick-start/README.md)

**Testavimas atmintyje** - Testavimas naudojant atminties saugyklą.

**Integracinis testavimas** - Testavimas su tikra infrastruktūra.

**Maven** - „Java“ statybos automatizavimo įrankis.

**Mockito** - „Java“ imitavimo biblioteka.

**Spring Boot** - „Java“ aplikacijų karkasas. [Modulis 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Atsakomybės apribojimas:
Šis dokumentas buvo išverstas naudojant dirbtinio intelekto vertimo paslaugą Co-op Translator (https://github.com/Azure/co-op-translator). Nors siekiame tikslumo, atkreipkite dėmesį, kad automatiniai vertimai gali turėti klaidų arba netikslumų. Originalus dokumentas jo gimtąja kalba turėtų būti laikomas autoritetingu šaltiniu. Svarbios informacijos atveju rekomenduojamas profesionalus, žmogaus atliktas vertimas. Mes neprisiimame atsakomybės už jokius nesusipratimus ar neteisingas interpretacijas, kilusias dėl šio vertimo naudojimo.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->