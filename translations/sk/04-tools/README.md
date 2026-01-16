<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-06T01:00:13+00:00",
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
  - [Pozrite si priebeh konverzácie](../../../04-tools)
  - [Experimentujte s rôznymi požiadavkami](../../../04-tools)
- [Kľúčové koncepty](../../../04-tools)
  - [ReAct vzor (uvažovanie a konanie)](../../../04-tools)
  - [Význam popisov nástrojov](../../../04-tools)
  - [Správa relácií](../../../04-tools)
  - [Spracovanie chýb](../../../04-tools)
- [Dostupné nástroje](../../../04-tools)
- [Kedy používať agentov založených na nástrojoch](../../../04-tools)
- [Ďalšie kroky](../../../04-tools)

## Čo sa naučíte

Doteraz ste sa naučili viesť rozhovory s AI, efektívne štruktúrovať výzvy a zakladať odpovede na vašich dokumentoch. Ale stále existuje základné obmedzenie: jazykové modely dokážu generovať iba text. Nemôžu overiť počasie, vykonávať výpočty, dotazovať databázy ani komunikovať s externými systémami.

Nástroje to menia. Tým, že modelu poskytnete prístup k funkciám, ktoré môže volať, premeníte ho z generátora textu na agenta, ktorý dokáže konať. Model rozhoduje, kedy potrebuje nástroj, ktorý nástroj použiť a aké parametre mu odovzdať. Váš kód vykoná funkciu a vráti výsledok. Model tento výsledok začlení do svojej odpovede.

## Predpoklady

- Dokončený modul 01 (nasadené Azure OpenAI zdroje)
- Súbor `.env` v koreňovom adresári s prihlasovacími údajmi Azure (vytvorený pomocou `azd up` v module 01)

> **Poznámka:** Ak ste modul 01 nedokončili, najprv dodržte inštrukcie pre nasadenie tam.

## Pochopenie AI agentov s nástrojmi

> **📝 Poznámka:** Termín "agenti" v tomto module označuje AI asistentov rozšírených o schopnosti volania nástrojov. Toto sa líši od vzorov **Agentic AI** (autonómni agenti s plánovaním, pamäťou a viacstupňovým uvažovaním), ktoré pokryjeme v [Module 05: MCP](../05-mcp/README.md).

AI agent s nástrojmi nasleduje vzor uvažovania a konania (ReAct):

1. Používateľ položí otázku
2. Agent uvažuje o tom, čo potrebuje vedieť
3. Agent rozhodne, či potrebuje nástroj na odpoveď
4. Ak áno, agent zavolá príslušný nástroj s vhodnými parametrami
5. Nástroj vykoná úlohu a vráti údaje
6. Agent začlení výsledok a poskytne finálnu odpoveď

<img src="../../../translated_images/sk/react-pattern.86aafd3796f3fd13.png" alt="ReAct Pattern" width="800"/>

*ReAct vzor - ako AI agenti striedajú uvažovanie a konanie na riešenie problémov*

Toto sa deje automaticky. Definujete nástroje a ich popis. Model sa stará o rozhodovanie, kedy a ako ich používať.

## Ako funguje volanie nástrojov

### Definície nástrojov

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Definujete funkcie s jasnými popismi a špecifikáciou parametrov. Model vidí tieto popisy vo svojom systémovom promptu a rozumie, čo každý nástroj robí.

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

// Asistent je automaticky prepojený cez Spring Boot s:
// - ChatModel bean
// - Všetky metódy @Tool z tried @Component
// - ChatMemoryProvider pre správu relácie
```

> **🤖 Vyskúšajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorte [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) a spýtajte sa:
> - "Ako by som integroval skutočné API počasia ako OpenWeatherMap namiesto simulovaných údajov?"
> - "Čo robí dobrý popis nástroja, ktorý pomáha AI správne ho používať?"
> - "Ako riešiť chyby API a limity rýchlosti v implementáciách nástrojov?"

### Rozhodovanie

Keď používateľ položí otázku "Aké je počasie v Seattli?", model rozpozná, že potrebuje nástroj počasia. Vygeneruje volanie funkcie s parametrom lokácie nastaveným na "Seattle".

### Vykonanie

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot automaticky prepája deklaratívne rozhranie `@AiService` so všetkými registrovanými nástrojmi a LangChain4j volania nástrojov vykonáva automaticky.

> **🤖 Vyskúšajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorte [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) a spýtajte sa:
> - "Ako funguje ReAct vzor a prečo je efektívny pre AI agentov?"
> - "Ako agent rozhoduje, ktorý nástroj použiť a v akom poradí?"
> - "Čo sa stane, keď vykonanie nástroja zlyhá – ako robustne spracovať chyby?"

### Generovanie odpovede

Model prijme údaje o počasí a naformátuje ich do prirodzenej odpovede pre používateľa.

### Prečo používať deklaratívne AI služby?

Tento modul používa LangChain4j integráciu so Spring Boot a deklaratívnymi rozhraniami `@AiService`:

- **Automatické prepájanie v Spring Boot** – ChatModel a nástroje sú automaticky vkladané
- **Vzorec @MemoryId** – automatická správa pamäte založená na reláciách
- **Jedna inštancia** – asistent vytvorený raz a znovu použitý pre lepší výkon
- **Typovo bezpečné vykonávanie** – Java metódy volané priamo s konverziou typov
- **Viacstupňová orchestrácia** – automatické riadenie reťazenia nástrojov
- **Žiadna zbytočná boilerplate** – žiadne manuálne volania AiServices.builder() alebo spravovanie hash mapy pamäte

Alternatívne prístupy (manuálne AiServices.builder()) vyžadujú viac kódu a postrádajú výhody integrácie so Spring Boot.

## Reťazenie nástrojov

**Reťazenie nástrojov** – AI môže volať viacero nástrojov v sekvencii. Opýtajte sa „Aké je počasie v Seattli a mám si vziať dáždnik?“ a sledujte ako spojí volania `getCurrentWeather` s uvažovaním o dažďovej výbave.

<a href="images/tool-chaining.png"><img src="../../../translated_images/sk/tool-chaining.3b25af01967d6f7b.png" alt="Tool Chaining" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sekvenčné volania nástrojov - výstup jedného nástroja slúži ako vstup pre ďalšie rozhodnutie*

**Láskavé zlyhania** – Spýtajte sa na počasie v meste, ktoré nie je v simulovaných dátach. Nástroj vráti chybové hlásenie a AI vysvetlí, že nemôže pomôcť. Nástroje zlyhávajú bezpečne.

To sa deje počas jedného kola konverzácie. Agent autonómne orchestruje viaceré volania nástrojov.

## Spustenie aplikácie

**Overte nasadenie:**

Uistite sa, že súbor `.env` existuje v koreňovom adresári s prihlasovacími údajmi Azure (vytvorený počas modulu 01):
```bash
cat ../.env  # Malo by zobraziť AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Spustite aplikáciu:**

> **Poznámka:** Ak ste už spustili všetky aplikácie pomocou `./start-all.sh` z modulu 01, tento modul už beží na porte 8084. Môžete vynechať nižšie uvedené príkazy na spustenie a prejsť priamo na http://localhost:8084.

**Možnosť 1: Použitie Spring Boot Dashboard (odporúčané pre používateľov VS Code)**

Vývojový kontajner obsahuje rozšírenie Spring Boot Dashboard, ktoré poskytuje vizuálne rozhranie na správu všetkých Spring Boot aplikácií. Nájdete ho v Activity Bar na ľavej strane VS Code (ikona Spring Boot).

Zo Spring Boot Dashboard môžete:
- Vidieť všetky dostupné Spring Boot aplikácie vo workspace
- Jedným kliknutím spustiť alebo zastaviť aplikácie
- Zobraziť logy aplikácie v reálnom čase
- Monitorovať stav aplikácie

Stačí kliknúť na tlačidlo pre spustenie vedľa „tools“ pre spustenie tohto modulu alebo spustiť všetky moduly naraz.

<img src="../../../translated_images/sk/dashboard.9b519b1a1bc1b30a.png" alt="Spring Boot Dashboard" width="400"/>

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

Oba skripty automaticky načítajú premenné prostredia zo súboru `.env` v koreňovom adresári a zostavia JARy, ak neexistujú.

> **Poznámka:** Ak chcete pred spustením manuálne zostaviť všetky moduly:
>
> **Bash:**
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

> **PowerShell:**
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

Otvorte v prehliadači http://localhost:8084.

**Na zastavenie:**

**Bash:**
```bash
./stop.sh  # Tento modul iba
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

<a href="images/tools-homepage.png"><img src="../../../translated_images/sk/tools-homepage.4b4cd8b2717f9621.png" alt="AI Agent Tools Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Rozhranie AI Agent Tools - rýchle príklady a chatové rozhranie na interakciu s nástrojmi*

### Vyskúšajte jednoduché použitie nástroja

Začnite priamočiarym dotazom: „Preveď 100 stupňov Fahrenheita na Celziovu“. Agent rozpozná, že potrebuje nástroj na prevod teplôt, zavolá ho s správnymi parametrami a vráti výsledok. Všimnite si, ako prirodzené to pôsobí – nešpecifikovali ste, ktorý nástroj použiť ani ako ho volať.

### Otestujte reťazenie nástrojov

Teraz vyskúšajte zložitejšiu požiadavku: „Aké je počasie v Seattli a preveď ho na Fahrenheit?“ Sledujte ako agent postupne rieši úlohu. Najprv získa počasie (ktoré vracia Celziovu), rozpozná potrebu prevodu na Fahrenheit, zavolá prevodný nástroj a spojí oba výsledky do jednej odpovede.

### Pozrite si priebeh konverzácie

Chatové rozhranie uchováva históriu konverzácie, čo umožňuje viackolové interakcie. Vidíte všetky predchádzajúce otázky a odpovede, čo uľahčuje sledovanie kontextu a pochopenie, ako agent buduje kontext cez viaceré výmeny.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/sk/tools-conversation-demo.89f2ce9676080f59.png" alt="Conversation with Multiple Tool Calls" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Viackolová konverzácia ukazujúca jednoduché prevody, vyhľadávanie počasia a reťazenie nástrojov*

### Experimentujte s rôznymi požiadavkami

Vyskúšajte rôzne kombinácie:
- Vyhľadávanie počasia: „Aké je počasie v Tokiu?“
- Prevod teplôt: „Koľko je 25 °C v Kelvinoch?“
- Kombinované dotazy: „Skontroluj počasie v Paríži a povedz mi, či je nad 20 °C“

Všimnite si, ako agent interpretuje prirodzený jazyk a mapuje ho na vhodné volania nástrojov.

## Kľúčové koncepty

### ReAct vzor (uvažovanie a konanie)

Agent strieda uvažovanie (rozhodovanie, čo robiť) a konanie (použitie nástrojov). Tento vzor umožňuje autonómne riešenie problémov namiesto len reagovania na pokyny.

### Význam popisov nástrojov

Kvalita popisov vašich nástrojov priamo ovplyvňuje, ako dobre ich agent používa. Jasné, špecifické popisy pomáhajú modelu pochopiť, kedy a ako zavolať každý nástroj.

### Správa relácií

Anotácia `@MemoryId` umožňuje automatickú správu pamäte založenú na relácii. Každé ID relácie dostane vlastnú inštanciu `ChatMemory` spravovanú beanom `ChatMemoryProvider`, čo eliminuje potrebu manuálneho sledovania pamäte.

### Spracovanie chýb

Nástroje môžu zlyhať – API môže vypršať, parametre môžu byť neplatné, externé služby prestanú fungovať. Produkčné agenti potrebujú spracovanie chýb, aby model mohol vysvetliť problémy alebo skúsiť alternatívy.

## Dostupné nástroje

**Nástroje na počasie** (simulované údaje na demonštráciu):
- Získanie aktuálneho počasia pre lokalitu
- Získanie viacdňovej predpovede

**Nástroje na prevod teplôt**:
- Celzius na Fahrenheit
- Fahrenheit na Celzius
- Celzius na Kelvin
- Kelvin na Celzius
- Fahrenheit na Kelvin
- Kelvin na Fahrenheit

Sú to jednoduché príklady, ale vzor sa rozširuje na ľubovoľnú funkciu: databázové dotazy, API volania, výpočty, operácie so súbormi alebo systémové príkazy.

## Kedy používať agentov založených na nástrojoch

**Používajte nástroje, keď:**
- Odpovede vyžadujú aktuálne údaje v reálnom čase (počasie, ceny akcií, skladové zásoby)
- Potrebujete vykonávať výpočty nad rámec základnej matematiky
- Pristupujete k databázam alebo API
- Konáte akcie (odosielanie emailov, vytváranie tiketov, aktualizácia záznamov)
- Kombinujete viacero zdrojov dát

**Nepoužívajte nástroje, keď:**
- Otázky sa dajú zodpovedať z všeobecných znalostí
- Odpoveď je čisto konverzačná
- Latencia nástroja by spomalila používateľský zážitok príliš do dĺžky

## Ďalšie kroky

**Ďalší modul:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigácia:** [← Predchádzajúci: Modul 03 - RAG](../03-rag/README.md) | [Späť na hlavnú stránku](../README.md) | [Ďalší: Modul 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Upozornenie**:  
Tento dokument bol preložený pomocou služby automatického prekladu AI [Co-op Translator](https://github.com/Azure/co-op-translator). Hoci sa snažíme o presnosť, prosím, berte na vedomie, že automatické preklady môžu obsahovať chyby alebo nepresnosti. Originálny dokument v jeho pôvodnom jazyku by mal byť považovaný za autoritatívny zdroj. Pre dôležité informácie sa odporúča profesionálny ľudský preklad. Nenesieme zodpovednosť za akékoľvek nepochopenia alebo nesprávne interpretácie vyplývajúce z použitia tohto prekladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->