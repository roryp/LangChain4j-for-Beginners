# LangChain4j Szószedet

## Tartalomjegyzék

- [Alapfogalmak](../../../docs)
- [LangChain4j Összetevők](../../../docs)
- [AI/ML Fogalmak](../../../docs)
- [Védőkorlátok](../../../docs)
- [Prompt Tervezés](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Ügynökök és Eszközök](../../../docs)
- [Agentikus Modul](../../../docs)
- [Modell Kontextus Protokoll (MCP)](../../../docs)
- [Azure Szolgáltatások](../../../docs)
- [Tesztelés és Fejlesztés](../../../docs)

Gyors hivatkozás a tanfolyamban használt kifejezésekre és fogalmakra.

## Alapfogalmak

**AI Ügynök** - Olyan rendszer, amely mesterséges intelligenciát használ önálló gondolkodásra és cselekvésre. [Modul 04](../04-tools/README.md)

**Lánc** - Műveletsorozat, ahol az eredmény a következő lépés bemenete.

**Darabolás** - Dokumentumok kisebb részekre bontása. Jellemzően: 300-500 token átfedéssel. [Modul 03](../03-rag/README.md)

**Kontextus Ablak** - Maximális tokenek száma, amit egy modell kezelni tud. GPT-5: 400K token.

**Beágyazások** - Szöveg jelentését numerikus vektorokkal ábrázoló alakzatok. [Modul 03](../03-rag/README.md)

**Függvényhívás** - A modell strukturált kéréseket generál külső függvények meghívására. [Modul 04](../04-tools/README.md)

**Hallucináció** - Amikor a modellek helytelen, de hihető információt állítanak elő.

**Prompt** - Szöveges bemenet nyelvi modellhez. [Modul 02](../02-prompt-engineering/README.md)

**Szemantikus Keresés** - Jelentés alapú keresés beágyazások segítségével, nem kulcsszavak alapján. [Modul 03](../03-rag/README.md)

**Állapotfüggő vs Állapotmentes** - Állapotmentes: nincs memória. Állapotfüggő: megőrzi a beszélgetés előzményeit. [Modul 01](../01-introduction/README.md)

**Tokenek** - Alapszövegegységek, amelyeket a modellek feldolgoznak. Befolyásolják a költségeket és korlátokat. [Modul 01](../01-introduction/README.md)

**Eszközláncolás** - Olyan eszközök egymás utáni használata, ahol az előző kimenete a következő hívását tájékoztatja. [Modul 04](../04-tools/README.md)

## LangChain4j Összetevők

**AiServices** - Típusszafety AI szolgáltatás interfészek létrehozása.

**OpenAiOfficialChatModel** - Egyesített kliens OpenAI és Azure OpenAI modellekhez.

**OpenAiOfficialEmbeddingModel** - Beágyazások készítése OpenAI Official klienssel (OpenAI és Azure OpenAI támogatás).

**ChatModel** - Alapértelmezett interfész nyelvi modellekhez.

**ChatMemory** - Megőrzi a beszélgetés előzményeit.

**ContentRetriever** - Megtalálja a releváns dokumentumdarabokat RAG-hez.

**DocumentSplitter** - Dokumentumok részekre bontása.

**EmbeddingModel** - Átalakítja a szöveget numerikus vektorokká.

**EmbeddingStore** - Beágyazások tárolása és lekérése.

**MessageWindowChatMemory** - Csúszóablakot tart fenn a legutóbbi üzenetekből.

**PromptTemplate** - Újrahasználható promptokat hoz létre `{{változó}}` helyőrzőkkel.

**TextSegment** - Metadátummal ellátott szövegrész. RAG-ben használatos.

**ToolExecutionRequest** - Eszköz végrehajtási kérés reprezentálása.

**UserMessage / AiMessage / SystemMessage** - Beszélgetési üzenettípusok.

## AI/ML Fogalmak

**Kevéspéldás Tanulás** - Példák megadása a promptokban. [Modul 02](../02-prompt-engineering/README.md)

**Nagy Nyelvi Modell (LLM)** - Nagy mennyiségű szövegen tanított AI modellek.

**Gondolkodási Erőfeszítés** - GPT-5 paraméter a gondolkodás mélységének szabályozására. [Modul 02](../02-prompt-engineering/README.md)

**Hőmérséklet** - Az eredmény véletlenszerűségének szabályozása. Alacsony=determinált, magas=alkotó.

**Vektor Adatbázis** - Beágyazásokhoz specializált adatbázis. [Modul 03](../03-rag/README.md)

**Nullapéldás Tanulás** - Feladatok példák nélküli végrehajtása. [Modul 02](../02-prompt-engineering/README.md)

## Védőkorlátok - [Modul 00](../00-quick-start/README.md)

**Rétegzett Védelem** - Többrétegű biztonsági megközelítés, amely alkalmazásszintű védőkorlátokat kombinál szolgáltató biztonsági szűrőkkel.

**Kemény Tiltás** - Szolgáltató HTTP 400-es hibát dob súlyos tartalmi szabálysértés esetén.

**InputGuardrail** - LangChain4j interfész a felhasználói bemenet ellenőrzésére még mielőtt az LLM-hez kerülne. Költség- és késleltetés-megtakarítást jelent, mert korán blokkolja a káros promptokat.

**InputGuardrailResult** - Védőkorlát érvényesítés visszatérési típusa: `success()` vagy `fatal("ok")`.

**OutputGuardrail** - Interfész az AI válaszok ellenőrzésére mielőtt a felhasználóknak visszaadnák.

**Szolgáltató Biztonsági Szűrők** - Beépített tartalomszűrők AI szolgáltatóktól (pl. GitHub Models), melyek API szinten fogják el a szabálysértéseket.

**Lágy Elutasítás** - A modell udvariasan megtagadja a válaszadást anélkül, hogy hibát dobna.

## Prompt Tervezés - [Modul 02](../02-prompt-engineering/README.md)

**Gondolatmenet Lánc** - Lépésenkénti gondolkodás a nagyobb pontosságért.

**Korlátozott Kimenet** - Meghatározott formátum vagy struktúra kikényszerítése.

**Nagy Lelkesedés** - GPT-5-minta alapos gondolkodáshoz.

**Alacsony Lelkesedés** - GPT-5-minta gyors válaszokhoz.

**Többkörös Beszélgetés** - Kontextus megtartása az egymást követő váltások között.

**Szerepalapú Prompt** - Modell szerepének beállítása rendszerüzeneteken keresztül.

**Önreflexió** - A modell kiértékeli és javítja saját kimenetét.

**Strukturált Elemzés** - Rögzített kiértékelési keretrendszer.

**Feladat-végrehajtási Minta** - Tervezés → Végrehajtás → Összefoglalás.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Dokumentumfeldolgozó Vonal** - Betöltés → darabolás → beágyazás → tárolás.

**Memóriabeli Beágyazás Tároló** - Nem tartós tároló teszteléshez.

**RAG** - Lekérés és generálás kombinálása a válaszok megalapozására.

**Hasonlósági Pontszám** - 0-1 közötti mérőszám a szemantikus hasonlóságra.

**Forrás Hivatkozás** - Lekért tartalomról szóló metadátum.

## Ügynökök és Eszközök - [Modul 04](../04-tools/README.md)

**@Tool Megjegyzés** - Java metódusokat jelöl AI-hívható eszközként.

**ReAct Minta** - Gondolkodás → Cselekvés → Megfigyelés → Ismétlés.

**Munkamenet Kezelés** - Különböző felhasználókhoz külön kontextusok.

**Eszköz** - Funkció, amit egy AI ügynök hívhat.

**Eszköz Leírása** - Dokumentáció az eszköz céljáról és paramétereiről.

## Agentikus Modul - [Modul 05](../05-mcp/README.md)

**@Agent Megjegyzés** - Interfészek jelölése AI ügynökként deklaratív viselkedésdefinícióval.

**Agent Hallgatózó** - Horgony az ügynök végrehajtásának figyelésére `beforeAgentInvocation()` és `afterAgentInvocation()` metódusokkal.

**Agentikus Hatókör** - Megosztott memória, ahol az ügynökök eltárolják eredményeiket az `outputKey` segítségével a további ügynökök számára.

**AgentikusSzolgáltatások** - Ügynökök létrehozó gyára az `agentBuilder()` és `supervisorBuilder()` segítségével.

**Feltételes Munkafolyamat** - Feltételek alapján különböző szakértői ügynökökhöz irányítás.

**Ember a Hurokban** - Munkafolyamat-minta emberi ellenőrzési pontokkal jóváhagyáshoz vagy tartalmi felülvizsgálathoz.

**langchain4j-agentic** - Maven függőség deklaratív ügynökkészítéshez (kísérleti).

**Ciklus Munkafolyamat** - Ügynök végrehajtása ismétlődően, amíg egy feltétel teljesül (pl. minőségi pontszám ≥ 0,8).

**outputKey** - Ügynök megjegyzés paramétere, amely megadja, hová tárolódnak az eredmények az Agentikus Hatókörben.

**Párhuzamos Munkafolyamat** - Több ügynök egyidejű futtatása független feladatokhoz.

**Válaszstratégia** - Hogyan fogalmazza meg a felügyelő a végső választ: UTOLSÓ, ÖSSZEFOGLALÓ vagy PONTSZÁMOZOTT.

**Szekvenciális Munkafolyamat** - Ügynökök sorrendben történő végrehajtása, ahol a kimenet a következő lépést táplálja.

**Felügyelő Ügynök Minta** - Fejlett agentikus minta, ahol egy felügyelő LLM dinamikusan dönti el, mely al-ügynököket hívja meg.

## Modell Kontextus Protokoll (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** - Maven függőség MCP integrációhoz LangChain4j-ben.

**MCP** - Modell Kontextus Protokoll: szabvány AI alkalmazások külső eszközökhöz való csatlakoztatásához. Egyszer építsd, mindenhol használd.

**MCP Ügyfél** - Alkalmazás, amely csatlakozik MCP szerverekhez eszközök felfedezéséhez és használatához.

**MCP Szerver** - Szolgáltatás, amely MCP-n keresztül nyújt eszközöket egyértelmű leírásokkal és paraméter sémákkal.

**McpToolProvider** - LangChain4j összetevő, amely MCP eszközöket csomagol AI szolgáltatásokhoz és ügynökökhöz.

**McpTransport** - MCP kommunikáció interfész. Implementációk pl. Stdio és HTTP.

**Stdio Átvitel** - Lokális folyamat átvitel stdin/stdout-on keresztül. Hasznos fájlrendszerhez vagy parancssori eszközökhöz.

**StdioMcpTransport** - LangChain4j implementáció, amely MCP szervert alfolyamatként indítja.

**Eszköz Felfedezés** - Az ügyfél lekérdezi a szervert az elérhető eszközökről leírásokkal és sémákkal.

## Azure Szolgáltatások - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Felhő alapú keresés vektorteszt-támogatással. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure erőforrások telepítésére.

**Azure OpenAI** - A Microsoft vállalati AI szolgáltatása.

**Bicep** - Azure infrastruktúra-kód nyelv. [Infrastruktúra Útmutató](../01-introduction/infra/README.md)

**Telepítés Neve** - Modell telepítésének neve Azure-ban.

**GPT-5** - Legújabb OpenAI modell gondolkodásvezérléssel. [Modul 02](../02-prompt-engineering/README.md)

## Tesztelés és Fejlesztés - [Tesztelési Útmutató](TESTING.md)

**Dev Konténer** - Konténerizált fejlesztési környezet. [Konfiguráció](../../../.devcontainer/devcontainer.json)

**GitHub Modellek** - Ingyenes AI modell játszótér. [Modul 00](../00-quick-start/README.md)

**Memória Tesztelés** - Tesztelés memóriaalapú tárolóval.

**Integrációs Tesztelés** - Valódi infrastruktúrával történő tesztelés.

**Maven** - Java build automatizációs eszköz.

**Mockito** - Java tesztelési (mocking) könyvtár.

**Spring Boot** - Java alkalmazás keretrendszer. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Jogi nyilatkozat**:
Ezt a dokumentumot az AI fordítási szolgáltatás, a [Co-op Translator](https://github.com/Azure/co-op-translator) segítségével fordítottuk le. Bár a pontosságra törekszünk, kérjük, vegye figyelembe, hogy az automatikus fordítások hibákat vagy pontatlanságokat tartalmazhatnak. Az eredeti dokumentum, az anyanyelvén, tekintendő hiteles forrásnak. Fontos információk esetén szakmai, emberi fordítást javaslunk. Nem vállalunk felelősséget az esetleges félreértésekért vagy félreértelmezésekért, amelyek ebből a fordításból adódhatnak.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->