# LangChain4j Žodynas

## Turinys

- [Pagrindinės sąvokos](../../../docs)
- [LangChain4j komponentai](../../../docs)
- [AI/ML sąvokos](../../../docs)
- [Guardrails](../../../docs)
- [Promptų inžinerija](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agentai ir įrankiai](../../../docs)
- [Agentų modulis](../../../docs)
- [Modelio konteksto protokolas (MCP)](../../../docs)
- [Azure paslaugos](../../../docs)
- [Testavimas ir vystymas](../../../docs)

Greita nuoroda į terminus ir sąvokas, naudojamas visoje kursų medžiagoje.

## Pagrindinės sąvokos

**AI agentas** – Sistema, kuri naudoja DI sprendimams priimti ir autonomiškai veikti. [Modulis 04](../04-tools/README.md)

**Grandinė** – Operacijų seka, kurioje vienos išvestis perduodama kitam žingsniui.

**Dalijimas į gabalus** – Dokumentų suskaidymas į mažesnes dalis. Įprasta: 300–500 žetonų su persidengimu. [Modulis 03](../03-rag/README.md)

**Konteksto langas** – Maksimalus žetonų skaičius, kurį modelis gali apdoroti. GPT-5: 400 tūkst. žetonų.

**Įterpiniai (Embeddings)** – Skaitmeninės vektoriaus reprezentacijos, atspindinčios teksto prasmę. [Modulis 03](../03-rag/README.md)

**Funkcijų kvietimas** – Modelis generuoja struktūrizuotus užklausimus išorinėms funkcijoms kviesti. [Modulis 04](../04-tools/README.md)

**Halucinacija** – Kai modeliai generuoja netikslią, bet įtikinamą informaciją.

**Užklausos tekstas (Prompt)** – Tekstas, pateikiamas kalbos modeliui. [Modulis 02](../02-prompt-engineering/README.md)

**Semantinė paieška** – Paieška pagal reikšmę naudojant įterpinius, o ne pagal raktinius žodžius. [Modulis 03](../03-rag/README.md)

**Būsenos laikymas vs be būsenos** – Be būsenos: nėra atminties. Su būsena: išlaiko pokalbio istoriją. [Modulis 01](../01-introduction/README.md)

**Žetonai** – Pagrindiniai teksto vienetai, kuriuos modeliai apdoroja. Įtakoja kaštus ir ribas. [Modulis 01](../01-introduction/README.md)

**Įrankių grandinavimas** – Įrankių vykdymas seka, kurioje išvestis naudojama kitam kvietimui. [Modulis 04](../04-tools/README.md)

## LangChain4j komponentai

**AiServices** – Kuria tipui saugius DI paslaugų sąsajos įgyvendinimus.

**OpenAiOfficialChatModel** – Vieningas klientas OpenAI ir Azure OpenAI modeliams.

**OpenAiOfficialEmbeddingModel** – Kuria įterpinius naudojant oficialų OpenAI klientą (palaiko tiek OpenAI, tiek Azure OpenAI).

**ChatModel** – Pagrindinė kalbos modelių sąsaja.

**ChatMemory** – Išlaiko pokalbio istoriją.

**ContentRetriever** – Randa aktualius dokumentų gabalus RAG procesui.

**DocumentSplitter** – Suskaido dokumentus į gabalus.

**EmbeddingModel** – Konvertuoja tekstą į skaitmeninius vektorius.

**EmbeddingStore** – Laiko ir gauna įterpinius.

**MessageWindowChatMemory** – Išlaiko slystantį naujausių žinučių langą.

**PromptTemplate** – Kuria pakartotinai naudojamas užklausas su `{{kintamasis}}` vietos žymėmis.

**TextSegment** – Teksto gabalas su metaduomenimis. Naudojamas RAG.

**ToolExecutionRequest** – Atstovauja įrankio vykdymo užklausą.

**UserMessage / AiMessage / SystemMessage** – Pokalbio žinučių tipai.

## AI/ML sąvokos

**Few-Shot mokymasis** – Pavyzdžių pateikimas užklausoje. [Modulis 02](../02-prompt-engineering/README.md)

**Didelis kalbos modelis (LLM)** – DI modeliai, išmokyti dideliuose tekstų kiekiuose.

**Mąstymo pastangos** – GPT-5 parametras, kontroliuojantis mąstymo gilumą. [Modulis 02](../02-prompt-engineering/README.md)

**Temperatūra** – Valdo išvesties atsitiktinumą. Žema = deterministinis, aukšta = kūrybiškas.

**Vektorinė duomenų bazė** – Specializuota duomenų bazė įterpiniams. [Modulis 03](../03-rag/README.md)

**Zero-Shot mokymasis** – Užduočių atlikimas be pavyzdžių. [Modulis 02](../02-prompt-engineering/README.md)

## Guardrails - [Modulis 00](../00-quick-start/README.md)

**Gynyba sluoksniais** – Daugiapakopis saugumo metodas, jungiantis programos lygio ribojimus ir paslaugų tiekėjų saugumo filtrus.

**Griežtas blokavimas** – Paslaugų tiekėjas grąžina HTTP 400 klaidą už rimtus turinio pažeidimus.

**InputGuardrail** – LangChain4j sąsaja naudotojo įvesties validavimui prieš pasiekiant LLM. Taupo kaštus ir užkerta kelią kenksmingoms užklausoms anksti.

**InputGuardrailResult** – Guardrail validavimo grąžinimo tipas: `success()` arba `fatal("priežastis")`.

**OutputGuardrail** – Sąsaja DI atsakymų validavimui prieš grąžinant vartotojams.

**Paslaugų tiekėjų saugumo filtrai** – AI tiekėjų integruoti turinio filtrai (pvz., GitHub Modelle), kurie aptinka pažeidimus API lygyje.

**Švelnus atsisakymas** – Modelis mandagiai atsisako atsakyti be klaidos metimo.

## Promptų inžinerija - [Modulis 02](../02-prompt-engineering/README.md)

**Minties grandinė (Chain-of-Thought)** – Nuoseklus mąstymas geresniam tikslumui.

**Apribota išvestis** – Nustatyta konkreti išvesties forma arba struktūra.

**Didelis noras** – GPT-5 modelio šablonas kruopščiam mąstymui.

**Mažas noras** – GPT-5 modelio šablonas greitiems atsakymams.

**Daugkartinis pokalbis** – Konteksto išlaikymas pokalbių metu.

**Rolės pagrindu sukurtas užklausa** – Modelio asmenybės nustatymas per sistemos žinutes.

**Savianalizė** – Modelis vertina ir tobulina savo išvestį.

**Struktūruota analizė** – Fiksuota vertinimo sistema.

**Užduočių vykdymo šablonas** – Planuoti → Vykdyti → Apibendrinti.

## RAG (Retrieval-Augmented Generation) - [Modulis 03](../03-rag/README.md)

**Dokumentų apdorojimo grandinė** – Įkelti → suskaldyti → įterpti → saugoti.

**Laikinas įterpinių saugykla atmintyje** – Nepastovi saugykla testavimui.

**RAG** – Derina atgavimą ir generavimą atsakymams pagrįsti.

**Panašumo įvertinimas** – Semantinio panašumo matas (nuo 0 iki 1).

**Šaltinio nuoroda** – Metaduomenys apie rastą turinį.

## Agentai ir įrankiai - [Modulis 04](../04-tools/README.md)

**@Tool anotacija** – Žymi Java metodus kaip DI kviečiamus įrankius.

**ReAct modelis** – Mąstyti → Veikti → Stebėti → Kartoti.

**Sesijų valdymas** – Atskirti kontekstai skirtingiems vartotojams.

**Įrankis** – Funkcija, kurią gali kviesti DI agentas.

**Įrankio aprašymas** – Dokumentacija apie įrankio paskirtį ir parametrus.

## Agentų modulis - [Modulis 05](../05-mcp/README.md)

**@Agent anotacija** – Žymi sąsajas kaip DI agentus su deklaratyvia elgsenos apibrėžtimi.

**Agentų klausytojas (Listener)** – Priedėlis vykdymo stebėjimui per `beforeAgentInvocation()` ir `afterAgentInvocation()`.

**Agentinės apimties atmintis** – Bendrinė atmintis, kur agentai saugo išvestis naudodami `outputKey` tolimesniems agentams.

**AgenticServices** – Fabrikas agentams kurti naudojant `agentBuilder()` ir `supervisorBuilder()`.

**Sąlyginis darbo srautas** – Maršrutavimas pagal sąlygas į skirtingus specializuotus agentus.

**Žmogiškasis tarpininkavimas** – Darbo srauto modelis su žmogaus patvirtinimo arba turinio peržiūros etapais.

**langchain4j-agentic** – Maven priklausomybė deklaratyviam agentų kūrimui (eksperimentinė).

**Ciklinis darbo srautas** – Kartoti agentų vykdymą, kol tenkinama sąlyga (pvz., kokybės įvertinimas ≥ 0.8).

**outputKey** – Agentų anotacijos parametras, nurodantis, kur saugomi rezultatai agentinėje apimtyje.

**Lygiagretus darbo srautas** – Vienu metu vykdyti kelis agentus nepriklausomoms užduotims.

**Atsakymo strategija** – Kaip prižiūrėtojas (supervisorius) formuluoja galutinį atsakymą: LAST, SUMMARY arba SCORED.

**Sekos darbo srautas** – Agentų vykdymas iš eilės, kur išvestis perduodama kitam žingsniui.

**Prižiūrėtojo agento modelis** – Išplėstinė agentinė schema, kur prižiūrėtojo LLM dinamiškai sprendžia, kuriuos sub-agentus kviesti.

## Modelio konteksto protokolas (MCP) - [Modulis 05](../05-mcp/README.md)

**langchain4j-mcp** – Maven priklausomybė MCP integracijai LangChain4j.

**MCP** – Modelio konteksto protokolas: standartas AI programoms jungtis prie išorinių įrankių. Sukuri vieną kartą, naudok visur.

**MCP klientas** – Programa, jungiasi prie MCP serverių ieškoti ir naudoti įrankius.

**MCP serveris** – Paslauga, eksponuojanti įrankius per MCP su aiškiais aprašymais ir parametrų schemomis.

**McpToolProvider** – LangChain4j komponentas, apgaubiantis MCP įrankius DI paslaugoms ir agentams.

**McpTransport** – Sąsaja MCP komunikacijai. Implementacijos: Stdio ir HTTP.

**Stdio transportas** – Vietinė proceso transporto sąsaja per stdin/stdout. Naudinga failų sistemai arba komandų eilutės įrankiams.

**StdioMcpTransport** – LangChain4j įgyvendinimas, paleidžiantis MCP serverį kaip antrinį procesą.

**Įrankių paieška** – Klientas užklausia serverį dėl galimų įrankių aprašų ir schemų.

## Azure paslaugos - [Modulis 01](../01-introduction/README.md)

**Azure AI Search** – Debesų paieška su vektorinėmis galimybėmis. [Modulis 03](../03-rag/README.md)

**Azure Developer CLI (azd)** – Azure išteklių diegimas.

**Azure OpenAI** – „Microsoft“ įmonių DI paslauga.

**Bicep** – Azure infrastruktūros kaip kodo kalba. [Infrastruktūros vadovas](../01-introduction/infra/README.md)

**Diegimo pavadinimas** – Modelio diegimo pavadinimas Azure aplinkoje.

**GPT-5** – Naujausias OpenAI modelis su mąstymo valdymu. [Modulis 02](../02-prompt-engineering/README.md)

## Testavimas ir vystymas - [Testavimo vadovas](TESTING.md)

**Dev konteineris** – Konteinerizuota vystymo aplinka. [Konfigūracija](../../../.devcontainer/devcontainer.json)

**GitHub modeliai** – Nemokama DI modelių bandymo aplinka. [Modulis 00](../00-quick-start/README.md)

**Testavimas atmintyje** – Testavimas su laikina atminties saugykla.

**Integracinis testavimas** – Testavimas su realia infrastruktūra.

**Maven** – Java automatinio kompilavimo įrankis.

**Mockito** – Java imitavimo biblioteka.

**Spring Boot** – Java aplikacijų karkasas. [Modulis 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Atsakomybės apribojimas**:  
Šis dokumentas buvo išverstas naudojant dirbtinio intelekto vertimo paslaugą [Co-op Translator](https://github.com/Azure/co-op-translator). Nors siekiame tikslumo, prašome atkreipti dėmesį, kad automatiniai vertimai gali turėti klaidų ar netikslumų. Originalus dokumentas gimtąja kalba turi būti laikomas autoritetingu šaltiniu. Esant svarbiai informacijai, rekomenduojama kreiptis į profesionalius vertėjus. Mes neprisiimame atsakomybės už bet kokius nesusipratimus ar klaidingus aiškinimus, kilusius dėl šio vertimo naudojimo.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->