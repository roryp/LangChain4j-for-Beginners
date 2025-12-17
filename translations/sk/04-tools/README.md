<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "aa23f106e7f53270924c9dd39c629004",
  "translation_date": "2025-12-13T19:20:03+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "sk"
}
-->
# Modul 04: AI agenti s nástrojmi

## Obsah

- [Čo sa naučíte](../../../04-tools)
- [Predpoklady](../../../04-tools)
- [Pochopenie AI agentov s nástrojmi](../../../04-tools)
- [Ako funguje volanie nástrojov](../../../04-tools)
  - [Definície nástrojov](../../../04-tools)
  - [Rozhodovanie](../../../04-tools)
  - [Vykonanie](../../../04-tools)
  - [Generovanie odpovede](../../../04-tools)
- [Reťazenie nástrojov](../../../04-tools)
- [Spustenie aplikácie](../../../04-tools)
- [Používanie aplikácie](../../../04-tools)
  - [Vyskúšajte jednoduché použitie nástroja](../../../04-tools)
  - [Otestujte reťazenie nástrojov](../../../04-tools)
  - [Pozrite si tok konverzácie](../../../04-tools)
  - [Sledujte uvažovanie](../../../04-tools)
  - [Experimentujte s rôznymi požiadavkami](../../../04-tools)
- [Kľúčové koncepty](../../../04-tools)
  - [ReAct vzor (uvážanie a konanie)](../../../04-tools)
  - [Popisy nástrojov sú dôležité](../../../04-tools)
  - [Správa relácií](../../../04-tools)
  - [Riešenie chýb](../../../04-tools)
- [Dostupné nástroje](../../../04-tools)
- [Kedy používať agentov založených na nástrojoch](../../../04-tools)
- [Ďalšie kroky](../../../04-tools)

## Čo sa naučíte

Doteraz ste sa naučili viesť rozhovory s AI, efektívne štruktúrovať prompt a zakladať odpovede na vašich dokumentoch. Ale stále existuje základné obmedzenie: jazykové modely môžu generovať iba text. Nemôžu kontrolovať počasie, vykonávať výpočty, dotazovať sa do databáz ani komunikovať s externými systémami.

Nástroje to menia. Tým, že modelu dáte prístup k funkciám, ktoré môže volať, premeníte ho z generátora textu na agenta, ktorý môže konať. Model rozhoduje, kedy potrebuje nástroj, ktorý nástroj použiť a aké parametre odovzdať. Váš kód vykoná funkciu a vráti výsledok. Model tento výsledok zahrnie do svojej odpovede.

## Predpoklady

- Dokončený Modul 01 (nasadené Azure OpenAI zdroje)
- Súbor `.env` v koreňovom adresári s Azure povereniami (vytvorený pomocou `azd up` v Module 01)

> **Poznámka:** Ak ste Modul 01 nedokončili, najskôr postupujte podľa tam uvedených inštrukcií na nasadenie.

## Pochopenie AI agentov s nástrojmi

AI agent s nástrojmi nasleduje vzor uvažovania a konania (ReAct):

1. Používateľ položí otázku
2. Agent uvažuje, čo potrebuje vedieť
3. Agent rozhodne, či potrebuje nástroj na odpoveď
4. Ak áno, agent zavolá príslušný nástroj s vhodnými parametrami
5. Nástroj vykoná operáciu a vráti dáta
6. Agent zahrnie výsledok a poskytne konečnú odpoveď

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13ae5b0218d4e91befabc04e00f97539df14f93d1ad9b8516f.sk.png" alt="ReAct Pattern" width="800"/>

*ReAct vzor - ako AI agenti striedavo uvažujú a konajú, aby vyriešili problémy*

Toto sa deje automaticky. Definujete nástroje a ich popisy. Model sa stará o rozhodovanie, kedy a ako ich použiť.

## Ako funguje volanie nástrojov

**Definície nástrojov** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Definujete funkcie s jasnými popismi a špecifikáciami parametrov. Model vidí tieto popisy vo svojom systémovom prompte a rozumie, čo každý nástroj robí.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Vaša logika vyhľadávania počasia
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Asistent je automaticky prepojený pomocou Spring Boot s:
// - ChatModel bean
// - Všetky @Tool metódy z @Component tried
// - ChatMemoryProvider pre správu relácií
```

> **🤖 Vyskúšajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorte [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) a spýtajte sa:
> - "Ako by som integroval skutočné API počasia ako OpenWeatherMap namiesto simulovaných dát?"
> - "Čo robí dobrý popis nástroja, ktorý pomáha AI správne ho používať?"
> - "Ako riešim chyby API a limity volaní v implementáciách nástrojov?"

**Rozhodovanie**

Keď používateľ položí otázku "Aké je počasie v Seattli?", model rozpozná, že potrebuje nástroj počasia. Vygeneruje volanie funkcie s parametrom lokality nastaveným na "Seattle".

**Vykonanie** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot automaticky prepojí deklaratívne rozhranie `@AiService` so všetkými registrovanými nástrojmi a LangChain4j vykoná volania nástrojov automaticky.

> **🤖 Vyskúšajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorte [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) a spýtajte sa:
> - "Ako funguje ReAct vzor a prečo je efektívny pre AI agentov?"
> - "Ako agent rozhoduje, ktorý nástroj použiť a v akom poradí?"
> - "Čo sa stane, ak vykonanie nástroja zlyhá - ako robustne riešiť chyby?"

**Generovanie odpovede**

Model dostane dáta o počasí a naformátuje ich do prirodzenej odpovede pre používateľa.

### Prečo používať deklaratívne AI služby?

Tento modul používa integráciu LangChain4j so Spring Boot a deklaratívne rozhrania `@AiService`:

- **Automatické prepojenie Spring Boot** - ChatModel a nástroje sú automaticky injektované
- **Vzorec @MemoryId** - Automatická správa pamäte na základe relácie
- **Jediná inštancia** - Asistent vytvorený raz a znovu použitý pre lepší výkon
- **Typovo bezpečné vykonávanie** - Java metódy volané priamo s konverziou typov
- **Orchestrace viacerých krokov** - Automaticky spravuje reťazenie nástrojov
- **Žiadny boilerplate** - Žiadne manuálne volania AiServices.builder() alebo HashMap pamäte

Alternatívne prístupy (manuálne AiServices.builder()) vyžadujú viac kódu a neprinášajú výhody integrácie so Spring Boot.

## Reťazenie nástrojov

**Reťazenie nástrojov** - AI môže volať viacero nástrojov za sebou. Spýtajte sa "Aké je počasie v Seattli a mám si vziať dáždnik?" a sledujte, ako reťazí `getCurrentWeather` s uvažovaním o dažďovom vybavení.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b1d54117d54ba382c21c51176aaf3800084cae2e7dfc82508.sk.png" alt="Tool Chaining" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sekvenčné volania nástrojov - výstup jedného nástroja sa používa v ďalšom rozhodnutí*

**Elegantné zlyhania** - Požiadajte o počasie v meste, ktoré nie je v simulovaných dátach. Nástroj vráti chybové hlásenie a AI vysvetlí, že nemôže pomôcť. Nástroje zlyhávajú bezpečne.

Toto sa deje v jednom kole konverzácie. Agent autonómne orchestruje viacero volaní nástrojov.

## Spustenie aplikácie

**Overte nasadenie:**

Uistite sa, že súbor `.env` existuje v koreňovom adresári s Azure povereniami (vytvorený počas Modulu 01):
```bash
cat ../.env  # Malo by zobraziť AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Spustite aplikáciu:**

> **Poznámka:** Ak ste už spustili všetky aplikácie pomocou `./start-all.sh` z Modulu 01, tento modul už beží na porte 8084. Môžete preskočiť spúšťacie príkazy nižšie a ísť priamo na http://localhost:8084.

**Možnosť 1: Použitie Spring Boot Dashboard (odporúčané pre používateľov VS Code)**

Vývojársky kontajner obsahuje rozšírenie Spring Boot Dashboard, ktoré poskytuje vizuálne rozhranie na správu všetkých Spring Boot aplikácií. Nájdete ho v Activity Bar na ľavej strane VS Code (ikona Spring Boot).

Zo Spring Boot Dashboard môžete:
- Vidieť všetky dostupné Spring Boot aplikácie v pracovnom priestore
- Jedným kliknutím spustiť/zastaviť aplikácie
- Zobraziť logy aplikácií v reálnom čase
- Monitorovať stav aplikácií

Jednoducho kliknite na tlačidlo play vedľa "tools" pre spustenie tohto modulu, alebo spustite všetky moduly naraz.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30af495a594f5c0213fecdbdf5bd9fb543d3c5467565773974a.sk.png" alt="Spring Boot Dashboard" width="400"/>

**Možnosť 2: Použitie shell skriptov**

Spustite všetky webové aplikácie (moduly 01-04):

**Bash:**
```bash
cd ..  # Z koreňového adresára
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Z koreňového adresára
.\start-all.ps1
```

Alebo spustite len tento modul:

**Bash:**
```bash
cd 04-tools
./start.sh
```

**PowerShell:**
```powershell
cd 04-tools
.\start.ps1
```

Oba skripty automaticky načítajú premenné prostredia zo súboru `.env` v koreňovom adresári a zostavia JAR súbory, ak neexistujú.

> **Poznámka:** Ak chcete najskôr manuálne zostaviť všetky moduly pred spustením:
>
> **Bash:**
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
>
> **PowerShell:**
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

Otvorte http://localhost:8084 vo vašom prehliadači.

**Na zastavenie:**

**Bash:**
```bash
./stop.sh  # Len tento modul
# Alebo
cd .. && ./stop-all.sh  # Všetky moduly
```

**PowerShell:**
```powershell
.\stop.ps1  # Len tento modul
# Alebo
cd ..; .\stop-all.ps1  # Všetky moduly
```

## Používanie aplikácie

Aplikácia poskytuje webové rozhranie, kde môžete komunikovať s AI agentom, ktorý má prístup k nástrojom na počasie a prevod teplôt.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f96216024b45b493ca1cd84935d6856416ea7a383b42f280d648c.sk.png" alt="AI Agent Tools Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Rozhranie AI Agent Tools - rýchle príklady a chat na interakciu s nástrojmi*

**Vyskúšajte jednoduché použitie nástroja**

Začnite jednoduchou požiadavkou: "Preveď 100 stupňov Fahrenheita na Celziove." Agent rozpozná, že potrebuje nástroj na prevod teplôt, zavolá ho s vhodnými parametrami a vráti výsledok. Všimnite si, aké to pôsobí prirodzene - neurčili ste, ktorý nástroj použiť ani ako ho volať.

**Otestujte reťazenie nástrojov**

Teraz skúste niečo zložitejšie: "Aké je počasie v Seattli a preveď ho na Fahrenheit?" Sledujte, ako agent postupne rieši túto požiadavku. Najprv získa počasie (ktoré je v Celziových stupňoch), rozpozná potrebu prevodu na Fahrenheit, zavolá nástroj na prevod a skombinuje oba výsledky do jednej odpovede.

**Pozrite si tok konverzácie**

Chatové rozhranie uchováva históriu konverzácie, čo umožňuje viackolové interakcie. Môžete vidieť všetky predchádzajúce otázky a odpovede, čo uľahčuje sledovanie konverzácie a pochopenie, ako agent buduje kontext cez viaceré výmeny.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f596acc43e227bf70f3c0d6030ad91d84df81070abf08848608.sk.png" alt="Conversation with Multiple Tool Calls" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Viackolová konverzácia ukazujúca jednoduché prevody, vyhľadávanie počasia a reťazenie nástrojov*

**Experimentujte s rôznymi požiadavkami**

Vyskúšajte rôzne kombinácie:
- Vyhľadávanie počasia: "Aké je počasie v Tokiu?"
- Prevod teplôt: "Koľko je 25°C v Kelvinoch?"
- Kombinované otázky: "Skontroluj počasie v Paríži a povedz mi, či je nad 20°C"

Všimnite si, ako agent interpretuje prirodzený jazyk a mapuje ho na vhodné volania nástrojov.

## Kľúčové koncepty

**ReAct vzor (uvážanie a konanie)**

Agent strieda uvažovanie (rozhodovanie, čo robiť) a konanie (používanie nástrojov). Tento vzor umožňuje autonómne riešenie problémov namiesto len odpovedania na inštrukcie.

**Popisy nástrojov sú dôležité**

Kvalita vašich popisov nástrojov priamo ovplyvňuje, ako dobre ich agent používa. Jasné, špecifické popisy pomáhajú modelu pochopiť, kedy a ako volať každý nástroj.

**Správa relácií**

Anotácia `@MemoryId` umožňuje automatickú správu pamäte na základe relácie. Každé ID relácie dostane vlastnú inštanciu `ChatMemory` spravovanú beanom `ChatMemoryProvider`, čím odpadá potreba manuálneho sledovania pamäte.

**Riešenie chýb**

Nástroje môžu zlyhať - API môžu vypršať, parametre môžu byť neplatné, externé služby môžu byť nedostupné. Produkčné agenti potrebujú riešenie chýb, aby model mohol vysvetliť problémy alebo skúsiť alternatívy.

## Dostupné nástroje

**Nástroje počasia** (simulované dáta na demonštráciu):
- Získanie aktuálneho počasia pre lokalitu
- Získanie viacdňovej predpovede

**Nástroje na prevod teplôt**:
- Celzius na Fahrenheit
- Fahrenheit na Celzius
- Celzius na Kelvin
- Kelvin na Celzius
- Fahrenheit na Kelvin
- Kelvin na Fahrenheit

Sú to jednoduché príklady, ale vzor sa rozširuje na akúkoľvek funkciu: dotazy do databázy, volania API, výpočty, operácie so súbormi alebo systémové príkazy.

## Kedy používať agentov založených na nástrojoch

**Používajte nástroje, keď:**
- Odpoveď vyžaduje aktuálne dáta (počasie, ceny akcií, skladové zásoby)
- Potrebujete vykonať výpočty nad rámec základnej matematiky
- Pristupujete k databázam alebo API
- Konáte akcie (odosielanie emailov, vytváranie tiketov, aktualizácia záznamov)
- Kombinujete viacero zdrojov dát

**Nepoužívajte nástroje, keď:**
- Otázky sa dajú zodpovedať z všeobecných znalostí
- Odpoveď je čisto konverzačná
- Latencia nástrojov by spomalila používateľský zážitok

## Ďalšie kroky

**Ďalší modul:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigácia:** [← Predchádzajúci: Modul 03 - RAG](../03-rag/README.md) | [Späť na hlavnú stránku](../README.md) | [Ďalší: Modul 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Zrieknutie sa zodpovednosti**:
Tento dokument bol preložený pomocou AI prekladateľskej služby [Co-op Translator](https://github.com/Azure/co-op-translator). Aj keď sa snažíme o presnosť, prosím, majte na pamäti, že automatizované preklady môžu obsahovať chyby alebo nepresnosti. Pôvodný dokument v jeho rodnom jazyku by mal byť považovaný za autoritatívny zdroj. Pre kritické informácie sa odporúča profesionálny ľudský preklad. Nie sme zodpovední za akékoľvek nedorozumenia alebo nesprávne interpretácie vyplývajúce z použitia tohto prekladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->