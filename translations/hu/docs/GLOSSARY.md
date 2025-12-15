<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:16:47+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "hu"
}
-->
# LangChain4j Szószedet

## Tartalomjegyzék

- [Alapfogalmak](../../../docs)
- [LangChain4j komponensek](../../../docs)
- [AI/ML fogalmak](../../../docs)
- [Prompt tervezés](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Ügynökök és eszközök](../../../docs)
- [Modell Kontextus Protokoll (MCP)](../../../docs)
- [Azure szolgáltatások](../../../docs)
- [Tesztelés és fejlesztés](../../../docs)

Gyors hivatkozás a kurzus során használt kifejezésekhez és fogalmakhoz.

## Alapfogalmak

**AI Ügynök** - Olyan rendszer, amely AI-t használ önálló gondolkodásra és cselekvésre. [Modul 04](../04-tools/README.md)

**Lánc** - Műveletsorozat, ahol az eredmény a következő lépés bemenete.

**Darabolás** - Dokumentumok kisebb részekre bontása. Tipikus: 300-500 token átfedéssel. [Modul 03](../03-rag/README.md)

**Kontextus Ablak** - Maximális tokenek száma, amit a modell feldolgozni tud. GPT-5: 400K token.

**Beágyazások** - Numerikus vektorok, amelyek a szöveg jelentését reprezentálják. [Modul 03](../03-rag/README.md)

**Függvényhívás** - A modell strukturált kéréseket generál külső függvények hívására. [Modul 04](../04-tools/README.md)

**Hallucináció** - Amikor a modellek hibás, de hihető információt generálnak.

**Prompt** - Szöveges bemenet a nyelvi modellhez. [Modul 02](../02-prompt-engineering/README.md)

**Szemantikus keresés** - Keresés jelentés alapján, beágyazásokat használva, nem kulcsszavakat. [Modul 03](../03-rag/README.md)

**Állapotfüggő vs Állapotfüggetlen** - Állapotfüggetlen: nincs memória. Állapotfüggő: megőrzi a beszélgetés előzményeit. [Modul 01](../01-introduction/README.md)

**Tokenek** - Alapvető szövegegységek, amelyeket a modellek feldolgoznak. Befolyásolják a költségeket és korlátokat. [Modul 01](../01-introduction/README.md)

**Eszközláncolás** - Eszközök egymás utáni végrehajtása, ahol az eredmény a következő hívást tájékoztatja. [Modul 04](../04-tools/README.md)

## LangChain4j komponensek

**AiServices** - Típusbiztos AI szolgáltatás interfészek létrehozása.

**OpenAiOfficialChatModel** - Egységes kliens az OpenAI és Azure OpenAI modellekhez.

**OpenAiOfficialEmbeddingModel** - Beágyazások létrehozása az OpenAI Official klienssel (támogatja az OpenAI és Azure OpenAI-t is).

**ChatModel** - Alap interfész nyelvi modellekhez.

**ChatMemory** - Megőrzi a beszélgetés előzményeit.

**ContentRetriever** - Megtalálja a releváns dokumentumdarabokat RAG-hez.

**DocumentSplitter** - Dokumentumokat darabol részekre.

**EmbeddingModel** - Szöveget numerikus vektorokká alakít.

**EmbeddingStore** - Beágyazások tárolása és lekérése.

**MessageWindowChatMemory** - Csúszó ablakot tart fenn a legutóbbi üzenetekből.

**PromptTemplate** - Újrahasználható promptokat hoz létre `{{variable}}` helyőrzőkkel.

**TextSegment** - Szövegrész metadata-val. RAG-ben használatos.

**ToolExecutionRequest** - Eszköz végrehajtási kérést reprezentál.

**UserMessage / AiMessage / SystemMessage** - Beszélgetési üzenettípusok.

## AI/ML fogalmak

**Few-Shot Learning** - Példák megadása a promptokban. [Modul 02](../02-prompt-engineering/README.md)

**Nagy Nyelvi Modell (LLM)** - Nagy mennyiségű szöveges adaton tanított AI modellek.

**Gondolkodási Erőfeszítés** - GPT-5 paraméter, amely a gondolkodás mélységét szabályozza. [Modul 02](../02-prompt-engineering/README.md)

**Hőmérséklet** - Az output véletlenszerűségét szabályozza. Alacsony=determinista, magas=alkotó.

**Vektor Adatbázis** - Beágyazásokhoz specializált adatbázis. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Feladatok végrehajtása példák nélkül. [Modul 02](../02-prompt-engineering/README.md)

## Prompt tervezés - [Modul 02](../02-prompt-engineering/README.md)

**Gondolatmenet Lánc** - Lépésenkénti érvelés a jobb pontosságért.

**Korlátozott Kimenet** - Meghatározott formátum vagy szerkezet kikényszerítése.

**Magas Lelkesedés** - GPT-5 minta alapos érveléshez.

**Alacsony Lelkesedés** - GPT-5 minta gyors válaszokhoz.

**Többfordulós Beszélgetés** - Kontextus megőrzése a váltások között.

**Szerepalapú Promptolás** - Modell személyiségének beállítása rendszerüzenetekkel.

**Önreflexió** - A modell értékeli és javítja a kimenetét.

**Strukturált Elemzés** - Rögzített értékelési keretrendszer.

**Feladatvégrehajtási Minta** - Tervezés → Végrehajtás → Összefoglalás.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Dokumentumfeldolgozó Folyamat** - Betöltés → darabolás → beágyazás → tárolás.

**Memóriabeli Beágyazás Tároló** - Nem tartós tároló teszteléshez.

**RAG** - Lekérdezés és generálás kombinálása a válaszok megalapozásához.

**Hasonlósági Pontszám** - Mérték (0-1) a szemantikus hasonlóságra.

**Forrás Hivatkozás** - Metaadat a lekért tartalomról.

## Ügynökök és eszközök - [Modul 04](../04-tools/README.md)

**@Tool Annotáció** - Java metódusokat jelöl AI-hívható eszközként.

**ReAct Minta** - Érvelés → Cselekvés → Megfigyelés → Ismétlés.

**Munkamenet Kezelés** - Külön kontextusok különböző felhasználók számára.

**Eszköz** - Olyan függvény, amelyet egy AI ügynök hívhat.

**Eszköz Leírás** - Dokumentáció az eszköz céljáról és paramétereiről.

## Modell Kontextus Protokoll (MCP) - [Modul 05](../05-mcp/README.md)

**Docker Átvitel** - MCP szerver Docker konténerben.

**MCP** - Szabvány AI alkalmazások külső eszközökhöz való kapcsolódásához.

**MCP Kliens** - Alkalmazás, amely MCP szerverekhez csatlakozik.

**MCP Szerver** - Szolgáltatás, amely eszközöket tesz elérhetővé MCP-n keresztül.

**Szerver Által Küldött Események (SSE)** - Szerver-kliens adatfolyam HTTP-n keresztül.

**Stdio Átvitel** - Szerver alfolyamatként stdin/stdout-on keresztül.

**Streamelhető HTTP Átvitel** - HTTP SSE-vel valós idejű kommunikációhoz.

**Eszköz Felderítés** - Kliens lekérdezi a szervert az elérhető eszközökről.

## Azure szolgáltatások - [Modul 01](../01-introduction/README.md)

**Azure AI Keresés** - Felhőalapú keresés vektoros képességekkel. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure erőforrások telepítése.

**Azure OpenAI** - A Microsoft vállalati AI szolgáltatása.

**Bicep** - Azure infrastruktúra-kód nyelv. [Infrastruktúra Útmutató](../01-introduction/infra/README.md)

**Telepítés Neve** - Modell telepítésének neve Azure-ban.

**GPT-5** - Legújabb OpenAI modell érvelésvezérléssel. [Modul 02](../02-prompt-engineering/README.md)

## Tesztelés és fejlesztés - [Tesztelési Útmutató](TESTING.md)

**Fejlesztői Konténer** - Konténerizált fejlesztői környezet. [Konfiguráció](../../../.devcontainer/devcontainer.json)

**GitHub Modellek** - Ingyenes AI modell játszótér. [Modul 00](../00-quick-start/README.md)

**Memóriabeli Tesztelés** - Tesztelés memóriabeli tárolóval.

**Integrációs Tesztelés** - Tesztelés valós infrastruktúrával.

**Maven** - Java build automatizáló eszköz.

**Mockito** - Java mock keretrendszer.

**Spring Boot** - Java alkalmazás keretrendszer. [Modul 01](../01-introduction/README.md)

**Testcontainers** - Docker konténerek tesztekben.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Jogi nyilatkozat**:
Ezt a dokumentumot az AI fordító szolgáltatás, a [Co-op Translator](https://github.com/Azure/co-op-translator) segítségével fordítottuk le. Bár a pontosságra törekszünk, kérjük, vegye figyelembe, hogy az automatikus fordítások hibákat vagy pontatlanságokat tartalmazhatnak. Az eredeti dokumentum az anyanyelvén tekintendő hiteles forrásnak. Fontos információk esetén professzionális emberi fordítást javaslunk. Nem vállalunk felelősséget a fordítás használatából eredő félreértésekért vagy félreértelmezésekért.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->