<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T04:04:24+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "hu"
}
-->
# LangChain4j Szószedet

## Tartalomjegyzék

- [Alapfogalmak](../../../docs)
- [LangChain4j összetevők](../../../docs)
- [AI/ML fogalmak](../../../docs)
- [Prompt tervezés](../../../docs)
- [RAG (visszakereséssel kiegészített generálás)](../../../docs)
- [Ügynökök és eszközök](../../../docs)
- [Modellkontextus-protokoll (MCP)](../../../docs)
- [Azure szolgáltatások](../../../docs)
- [Tesztelés és fejlesztés](../../../docs)

Gyors hivatkozás a kurzus során használt kifejezésekhez és fogalmakhoz.

## Alapfogalmak

**AI ügynök** - Rendszer, amely mesterséges intelligenciát használ önálló következtetésre és cselekvésre. [4. modul](../04-tools/README.md)

**Lánc** - Műveletsor, ahol az egyik kimenet a következő lépés bemenetévé válik.

**Darabolás** - Dokumentumok kisebb darabokra bontása. Tipikus: 300–500 token átfedéssel. [3. modul](../03-rag/README.md)

**Kontextusablak** - A modell által egyszerre feldolgozható maximális tokenek száma. GPT-5: 400K token.

**Beágyazások** - Számértékekből álló vektorok, amelyek a szöveg jelentését reprezentálják. [3. modul](../03-rag/README.md)

**Függvényhívás** - A modell strukturált kérést generál külső függvények meghívásához. [4. modul](../04-tools/README.md)

**Hallucináció** - Amikor a modellek hibás, de hihető információt állítanak elő.

**Prompt** - Szöveges bemenet egy nyelvi modellhez. [2. modul](../02-prompt-engineering/README.md)

**Szemantikus keresés** - Jelentés alapján történő keresés beágyazások használatával, nem kulcsszavak alapján. [3. modul](../03-rag/README.md)

**Állapotfüggő vs állapotmentes** - Állapotmentes: nincs memória. Állapotfüggő: megőrzi a beszélgetés előzményeit. [1. modul](../01-introduction/README.md)

**Tokenek** - Az alapvető szövegegységek, amelyeket a modellek feldolgoznak. Befolyásolják a költségeket és korlátokat. [1. modul](../01-introduction/README.md)

**Eszközláncolás** - Olyan eszközök sorozatos végrehajtása, ahol az egyik eredménye tájékoztatja a következő hívást. [4. modul](../04-tools/README.md)

## LangChain4j összetevők

**AiServices** - Típusbiztos AI szolgáltatás interfészek létrehozása.

**OpenAiOfficialChatModel** - Egységes kliens az OpenAI és az Azure OpenAI modellekhez.

**OpenAiOfficialEmbeddingModel** - Beágyazások készítése az OpenAI Official klienssel (támogatja az OpenAI és az Azure OpenAI szolgáltatásokat).

**ChatModel** - A nyelvi modellek alapvető interfésze.

**ChatMemory** - Megőrzi a beszélgetés előzményeit.

**ContentRetriever** - Megtalálja a releváns dokumentumdarabokat RAG-hez.

**DocumentSplitter** - Dokumentumokat darabol szegmensekre.

**EmbeddingModel** - Szöveget alakít át numerikus vektorokká.

**EmbeddingStore** - Beágyazások tárolása és előhívása.

**MessageWindowChatMemory** - A legutóbbi üzenetek csúszó ablakát tartja fenn.

**PromptTemplate** - Újrahasználható promptokat hoz létre `{{variable}}` helyőrzőkkel.

**TextSegment** - Szövegszegmens metaadatokkal. RAG-ben használatos.

**ToolExecutionRequest** - Eszközvégrehajtási kérelmet reprezentál.

**UserMessage / AiMessage / SystemMessage** - Beszélgetés üzenettípusai.

## AI/ML fogalmak

**Few-Shot tanulás** - Példák megadása a promptokban. [2. modul](../02-prompt-engineering/README.md)

**Nagy nyelvi modell (LLM)** - Nagy mennyiségű szövegből tanult AI modellek.

**Gondolkodási erőfeszítés** - GPT-5 paraméter, amely a gondolkodás mélységét szabályozza. [2. modul](../02-prompt-engineering/README.md)

**Hőmérséklet (Temperature)** - A kimenet véletlenszerűségét szabályozza. Alacsony=detereminisztikus, magas=kreatív.

**Vektor adatbázis** - Beágyazások számára optimalizált adatbázis. [3. modul](../03-rag/README.md)

**Zero-Shot tanulás** - Feladatok végrehajtása példák nélkül. [2. modul](../02-prompt-engineering/README.md)

## Prompt tervezés - [2. modul](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Lépésenkénti érvelés a jobb pontosság érdekében.

**Korlátozott kimenet** - Meghatározott formátum vagy szerkezet kikényszerítése.

**Magas hév** - GPT-5 minta alapos érveléshez.

**Alacsony hév** - GPT-5 minta gyors válaszokhoz.

**Többfordulós beszélgetés** - A kontextus fenntartása az üzenetváltások során.

**Szerepalapú promptolás** - A modell személyiségének beállítása rendszerüzenetekkel.

**Önreflexió** - A modell értékeli és javítja a kimenetét.

**Strukturált elemzés** - Rögzített értékelési keretrendszer.

**Feladatvégrehajtási minta** - Tervezés → Végrehajtás → Összefoglalás.

## RAG (visszakereséssel kiegészített generálás) - [3. modul](../03-rag/README.md)

**Dokumentumfeldolgozási folyamat** - Betöltés → darabolás → beágyazás → tárolás.

**Memóriabeli beágyazás-tár** - Nem tartós tárolás teszteléshez.

**RAG** - A visszakeresést és a generálást kombinálja a válaszok megalapozásához.

**Hasonlósági pontszám** - A szemantikai hasonlóság mértéke (0–1).

**Forrás hivatkozás** - Metaadat a visszakeresett tartalomról.

## Ügynökök és eszközök - [4. modul](../04-tools/README.md)

**@Tool annotáció** - Java metódusokat jelöl AI által hívható eszközként.

**ReAct minta** - Érvelés → Cselekvés → Megfigyelés → Ismétlés.

**Munkamenet-kezelés** - Különálló kontextusok különböző felhasználók számára.

**Eszköz** - Funkció, amelyet egy AI ügynök meghívhat.

**Eszköz leírása** - Az eszköz céljának és paramétereinek dokumentációja.

## Modellkontextus-protokoll (MCP) - [5. modul](../05-mcp/README.md)

**MCP** - Szabvány az AI alkalmazások külső eszközökhöz való csatlakoztatásához.

**MCP kliens** - Alkalmazás, amely MCP szerverekhez csatlakozik.

**MCP szerver** - Szolgáltatás, amely eszközöket tesz elérhetővé MCP-n keresztül.

**Stdio szállítás** - Szerver alfolyamatként stdin/stdout felületen.

**Eszközfelderítés** - A kliens lekérdezi a szervert az elérhető eszközökről.

## Azure szolgáltatások - [1. modul](../01-introduction/README.md)

**Azure AI Search** - Felhőalapú keresés vektoros képességekkel. [3. modul](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure erőforrások telepítése.

**Azure OpenAI** - A Microsoft vállalati AI szolgáltatása.

**Bicep** - Azure infrastruktúra-kód nyelv. [Infrastruktúra útmutató](../01-introduction/infra/README.md)

**Telepítési név** - A modell telepítésének neve az Azure-ban.

**GPT-5** - A legújabb OpenAI modell érvelésvezérléssel. [2. modul](../02-prompt-engineering/README.md)

## Tesztelés és fejlesztés - [Tesztelési útmutató](TESTING.md)

**Fejlesztői konténer** - Konténerizált fejlesztési környezet. [Konfiguráció](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Ingyenes AI modell játszótér. [0. modul](../00-quick-start/README.md)

**Memóriabeli tesztelés** - Tesztelés memóriabeli tárolással.

**Integrációs tesztelés** - Tesztelés valódi infrastruktúrával.

**Maven** - Java build automatizálási eszköz.

**Mockito** - Java mocking keretrendszer.

**Spring Boot** - Java alkalmazáskeretrendszer. [1. modul](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Felelősségkizárás:
Ezt a dokumentumot az AI fordító szolgáltatás, a Co-op Translator (https://github.com/Azure/co-op-translator) segítségével fordítottuk. Bár törekszünk a pontosságra, kérjük, vegye figyelembe, hogy az automatizált fordítások hibákat vagy pontatlanságokat tartalmazhatnak. Az eredeti dokumentum az anyanyelvén tekintendő hiteles forrásnak. Kritikus fontosságú információk esetén szakmai, emberi fordítást javaslunk. Nem vállalunk felelősséget a fordítás használatából eredő esetleges félreértésekért vagy félreértelmezésekért.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->