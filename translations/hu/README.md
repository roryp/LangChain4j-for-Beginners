<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "1e85afe0b0ee47fc09b20442b0ee4ca5",
  "translation_date": "2025-12-23T10:35:15+00:00",
  "source_file": "README.md",
  "language_code": "hu"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b506e9588f734989dd106ebd9f977b7f784941a28b88348f0d6.hu.png" alt="LangChain4j" width="800"/>

### 🌐 Többnyelvű támogatás

#### GitHub Action által támogatott (automatizált és mindig naprakész)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arab](../ar/README.md) | [Bengáli](../bn/README.md) | [Bolgár](../bg/README.md) | [Burmai (Mianmar)](../my/README.md) | [Kínai (egyszerűsített)](../zh/README.md) | [Kínai (hagyományos, Hongkong)](../hk/README.md) | [Kínai (hagyományos, Makaó)](../mo/README.md) | [Kínai (hagyományos, Tajvan)](../tw/README.md) | [Horvát](../hr/README.md) | [Cseh](../cs/README.md) | [Dán](../da/README.md) | [Holland](../nl/README.md) | [Észt](../et/README.md) | [Finn](../fi/README.md) | [Francia](../fr/README.md) | [Német](../de/README.md) | [Görög](../el/README.md) | [Héber](../he/README.md) | [Hindi](../hi/README.md) | [Magyar](./README.md) | [Indonéz](../id/README.md) | [Olasz](../it/README.md) | [Japán](../ja/README.md) | [Kannada](../kn/README.md) | [Koreai](../ko/README.md) | [Litván](../lt/README.md) | [Maláj](../ms/README.md) | [Malayalam](../ml/README.md) | [Maráthi](../mr/README.md) | [Nepáli](../ne/README.md) | [Nigériai Pidgin](../pcm/README.md) | [Norvég](../no/README.md) | [Perzsa (fárszi)](../fa/README.md) | [Lengyel](../pl/README.md) | [Portugál (Brazília)](../br/README.md) | [Portugál (Portugália)](../pt/README.md) | [Pandzsábi (Gurmukhi)](../pa/README.md) | [Román](../ro/README.md) | [Orosz](../ru/README.md) | [Szerb (cirill)](../sr/README.md) | [Szlovák](../sk/README.md) | [Szlovén](../sl/README.md) | [Spanyol](../es/README.md) | [Szuahéli](../sw/README.md) | [Svéd](../sv/README.md) | [Tagalog (filippínó)](../tl/README.md) | [Tamil](../ta/README.md) | [Telugu](../te/README.md) | [Thai](../th/README.md) | [Török](../tr/README.md) | [Ukrán](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnami](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j kezdőknek

Tanfolyam AI-alkalmazások építéséhez LangChain4j-vel és Azure OpenAI GPT-5-tel, az alapvető csevegéstől az AI-ügynökökig.

**Új vagy a LangChain4j-ben?** Nézd meg a [Fogalomtárat](docs/GLOSSARY.md) a kulcsfogalmak és kifejezések meghatározásához.

## Table of Contents

1. [Gyors kezdés](00-quick-start/README.md) - Kezdés a LangChain4j-vel
2. [Bevezetés](01-introduction/README.md) - Ismerd meg a LangChain4j alapjait
3. [Prompt tervezés](02-prompt-engineering/README.md) - Sajátítsd el a hatékony prompttervezést
4. [RAG (Lekérdezéssel kiegészített generálás)](03-rag/README.md) - Építs intelligens, tudásalapú rendszereket
5. [Eszközök](04-tools/README.md) - Integráld a külső eszközöket és API-kat AI-ügynökökkel
6. [MCP (Model Context Protocol)](05-mcp/README.md) - Dolgozz a Model Context Protokollal
---

##  Tanulási út

> **Gyors kezdés**

1. Forkold ezt a tárhelyet a GitHub fiókodba
2. Kattints **Code** → **Codespaces** fülre → **...** → **New with options...**
3. Használd az alapértelmezetteket – ez kiválasztja a tanfolyamhoz létrehozott Development konténert
4. Kattints **Create codespace**
5. Várj 5-10 percet, amíg a környezet készen lesz
6. Ugorj közvetlenül a [Gyors kezdés](./00-quick-start/README.md)-hez, hogy elkezdhesd!

> **Szeretnéd helyben klónozni?**
>
> Ez a tárhely 50+ nyelvi fordítást tartalmaz, ami jelentősen megnöveli a letöltési méretet. Ha fordítások nélküli klónozást szeretnél, használj sparse checkout:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> Ez mindent megad, amire szükséged van a tanfolyam elvégzéséhez, és jóval gyorsabb letöltést biztosít.

Kezdd a [Gyors kezdés](00-quick-start/README.md) modullal és haladj modulról modulra, hogy lépésről lépésre fejleszd képességeidet. Alapvető példákat próbálsz ki az alapok megértéséhez, mielőtt az [Bevezetés](01-introduction/README.md) modulra lépnél a GPT-5 mélyebb megismeréséhez.

<img src="../../translated_images/learning-path.ac2da6720e77c3165960835627cef4c20eb2afb103be73a4f25b6d8fafbd738d.hu.png" alt="Tanulási útvonal" width="800"/>

A modulok befejezése után nézd meg a [Tesztelési útmutatót](docs/TESTING.md), hogy lásd a LangChain4j tesztelési koncepcióit a gyakorlatban.

> **Megjegyzés:** Ez a képzés mind GitHub Modelleket, mind Azure OpenAI-t használ. A [Gyors kezdés](00-quick-start/README.md) és a [MCP](05-mcp/README.md) modulok GitHub Modelleket használnak (nem szükséges Azure-előfizetés), míg az 1-4. modulok az Azure OpenAI GPT-5-öt használják.


## Tanulás a GitHub Copilottal

Gyors kódolás elkezdéséhez nyisd meg ezt a projektet egy GitHub Codespace-ben vagy a helyi IDE-dben a mellékelt devcontainerrel. A tanfolyamban használt devcontainer előre konfigurált GitHub Copilottal az AI páros programozáshoz.

Minden kódpélda tartalmaz javasolt kérdéseket, amelyeket feltehetsz a GitHub Copilotnak a mélyebb megértés érdekében. Keresd a 💡/🤖 jelzéseket a következőkben:

- **Java fájlfejek** - Az egyes példákhoz kapcsolódó kérdések
- **Modul README-k** - Feltáró kérdések a kódpéldák után

**Hogyan használd:** Nyiss meg bármely kódfájlt és tedd fel a Copilotnak a javasolt kérdéseket. Teljes kontextusa van a kódbázisnak, és képes magyarázni, kiterjeszteni és alternatívákat javasolni.

Szeretnél többet megtudni? Nézd meg a [Copilot az AI páros programozáshoz](https://aka.ms/GitHubCopilotAI).


## További források

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j kezdőknek](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js kezdőknek](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Ügynökök
[![AZD kezdőknek](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI kezdőknek](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP kezdőknek](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI-ügynökök kezdőknek](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Generatív AI sorozat
[![Generatív AI kezdőknek](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generatív AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generatív AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generatív AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### Alapok
[![Gépi tanulás kezdőknek](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Adattudomány kezdőknek](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![Mesterséges intelligencia kezdőknek](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Kiberbiztonság kezdőknek](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Webfejlesztés kezdőknek](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT kezdőknek](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR-fejlesztés kezdőknek](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot sorozat
[![Copilot az AI páros programozáshoz](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot C#/.NET-hez](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot kaland](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## Segítség

Ha elakadnál, vagy kérdésed van az AI-alkalmazások fejlesztésével kapcsolatban, csatlakozz:

[![Azure AI Foundry Discord közösség](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

Ha termék-visszajelzésed van vagy hibába ütközöl a fejlesztés során, látogass el ide:

[![Azure AI Foundry fejlesztői fórum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## Licenc

MIT-licenc - Lásd a [LICENSE](../../LICENSE) fájlt a részletekért.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Felelősségkizárás:
Ezt a dokumentumot az AI fordítási szolgáltatás, a Co-op Translator (https://github.com/Azure/co-op-translator) használatával fordítottuk le. Bár igyekszünk pontosak lenni, kérjük, vegye figyelembe, hogy az automatikus fordítások hibákat vagy pontatlanságokat tartalmazhatnak. Az eredeti dokumentumot annak eredeti nyelvén kell tekinteni a hiteles forrásnak. Kritikus információk esetén professzionális, emberi fordítást javasolunk. Nem vállalunk felelősséget az ebből a fordításból eredő félreértésekért vagy téves értelmezésekért.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->