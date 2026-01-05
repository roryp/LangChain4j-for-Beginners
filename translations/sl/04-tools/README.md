<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-31T05:53:49+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "sl"
}
-->
# Modul 04: AI agenti z orodji

## Vsebina

- [Kaj se boste naučili](../../../04-tools)
- [Predpogoji](../../../04-tools)
- [Razumevanje AI agentov z orodji](../../../04-tools)
- [Kako deluje klicanje orodij](../../../04-tools)
  - [Določanje orodij](../../../04-tools)
  - [Sprejemanje odločitev](../../../04-tools)
  - [Izvajanje](../../../04-tools)
  - [Generiranje odgovora](../../../04-tools)
- [Verižna uporaba orodij](../../../04-tools)
- [Zagon aplikacije](../../../04-tools)
- [Uporaba aplikacije](../../../04-tools)
  - [Poskusite preprosto uporabo orodij](../../../04-tools)
  - [Preizkusite verižanje orodij](../../../04-tools)
  - [Oglejte si potek pogovora](../../../04-tools)
  - [Opazujte sklepanje](../../../04-tools)
  - [Igrajte se z raznimi zahtevami](../../../04-tools)
- [Ključni pojmi](../../../04-tools)
  - [Vzorec ReAct (Reasoning and Acting)](../../../04-tools)
  - [Opisi orodij so pomembni](../../../04-tools)
  - [Upravljanje sej](../../../04-tools)
  - [Ravnanje z napakami](../../../04-tools)
- [Razpoložljiva orodja](../../../04-tools)
- [Kdaj uporabljati agente, ki uporabljajo orodja](../../../04-tools)
- [Naslednji koraki](../../../04-tools)

## Kaj se boste naučili

Do zdaj ste se naučili, kako voditi pogovore z AI, kako učinkovito strukturirati pozive in kako utemeljiti odgovore na vaših dokumentih. Kljub temu pa obstaja temeljna omejitev: jezikovni modeli lahko ustvarjajo le besedilo. Ne morejo preveriti vremena, izvajati izračunov, poizvedovati v bazah podatkov ali komunicirati z zunanjimi sistemi.

Orodja to spremenijo. Z omogočanjem funkcij, ki jih lahko model pokliče, ga spremenite iz generatorja besedila v agenta, ki lahko izvaja dejanja. Model odloča, kdaj potrebuje orodje, katero orodje uporabiti in katere parametre posredovati. Vaša koda izvede funkcijo in vrne rezultat. Model vključi ta rezultat v svoj odgovor.

## Predpogoji

- Dokončan Modul 01 (Azure OpenAI viri nameščeni)
- Datoteka `.env` v korenski mapi z Azure poverilnicami (ustvarjena z `azd up` v Modul 01)

> **Note:** Če niste dokončali Modula 01, najprej sledite tamkajšnjim navodilom za nameščanje.

## Razumevanje AI agentov z orodji

> **📝 Note:** Pojem "agenti" v tem modulu se nanaša na AI asistente, izboljšane s sposobnostjo klicanja orodij. To se razlikuje od vzorcev **Agentic AI** (avtonomni agenti z načrtovanjem, spominom in večstopenjskim sklepanjem), ki jih bomo obravnavali v [Module 05: MCP](../05-mcp/README.md).

AI agent z orodji sledi vzorcu sklepanja in delovanja (ReAct):

1. Uporabnik postavi vprašanje
2. Agent razmišlja o tem, kaj mora vedeti
3. Agent se odloči, ali potrebuje orodje za odgovor
4. Če da, agent pokliče ustrezno orodje z pravimi parametri
5. Orodje izvede in vrne podatke
6. Agent vključi rezultat in poda končni odgovor

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.sl.png" alt="Vzorec ReAct" width="800"/>

*Vzorec ReAct - kako AI agenti izmenjujejo sklepanja in dejanja za reševanje problemov*

To se zgodi samodejno. Določite orodja in njihove opise. Model poskrbi za odločanje o tem, kdaj in kako jih uporabiti.

## Kako deluje klicanje orodij

**Določanje orodij** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Določite funkcije z jasnimi opisi in specifikacijami parametrov. Model vidi te opise v svojem sistemskem pozivu in razume, kaj vsako orodje počne.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Vaša logika iskanja vremena
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Pomočnik je s Spring Bootom samodejno povezan z:
// - ChatModel bean
// - Vse @Tool metode iz @Component razredov
// - ChatMemoryProvider za upravljanje sej
```

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) and ask:
> - "How would I integrate a real weather API like OpenWeatherMap instead of mock data?"
> - "What makes a good tool description that helps the AI use it correctly?"
> - "How do I handle API errors and rate limits in tool implementations?"

**Sprejemanje odločitev**

Ko uporabnik vpraša "Kako je vreme v Seattlu?", model prepozna, da potrebuje orodje za vreme. Generira klic funkcije z nastavljenim parametrom location na "Seattle".

**Izvajanje** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot avtomatsko poveže deklarativni vmesnik `@AiService` z vsemi registriranimi orodji, in LangChain4j avtomatično izvede klice orodij.

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) and ask:
> - "How does the ReAct pattern work and why is it effective for AI agents?"
> - "How does the agent decide which tool to use and in what order?"
> - "What happens if a tool execution fails - how should I handle errors robustly?"

**Generiranje odgovora**

Model prejme podatke o vremenu in jih oblikuje v naravni jezik, s katerim odgovori uporabniku.

### Zakaj uporabljati deklarativne AI storitve?

Ta modul uporablja integracijo LangChain4j s Spring Bootom in deklarativne `@AiService` vmesnike:

- **Spring Boot auto-wiring** - ChatModel in orodja so avtomatično vbrizgani
- **@MemoryId pattern** - Samodejno upravljanje spomina na osnovi seje
- **En primerek** - Asistent je ustvarjen enkrat in ponovno uporabljen za boljšo zmogljivost
- **Izvrševanje z varnimi tipi** - Java metode se kličejo neposredno s pretvorbo tipov
- **Orkestracija v več krogih** - Samodejno podpira verižanje orodij
- **Brez dodatnega kode** - Ni ročnih klicev AiServices.builder() ali HashMap za pomnilnik

Alternativni pristopi (ročni `AiServices.builder()`) zahtevajo več kode in zamujajo prednosti Spring Boot integracije.

## Verižna uporaba orodij

**Verižna uporaba orodij** - AI lahko zaporedoma pokliče več orodij. Vprašajte "Kako je vreme v Seattlu in ali naj vzamem dežnik?" in opazujte, kako veriženo pokliče `getCurrentWeather` ter razmišlja o opremi za dež.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.sl.png" alt="Verižna uporaba orodij" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Zaporedni klici orodij - izhod enega orodja vpliva na naslednjo odločitev*

**Elegantne napake** - Povprašajte za vreme v mestu, ki ni v vzorčnih podatkih. Orodje vrne sporočilo o napaki, AI pa pojasni, da ne more pomagati. Orodja varno odpovejo.

To se zgodi v eni potezi pogovora. Agent samodejno orkestrira več klicev orodij.

## Zagon aplikacije

**Preverite namestitev:**

Prepričajte se, da datoteka `.env` obstaja v korenski mapi z Azure poverilnicami (ustvarjena med Modulom 01):
```bash
cat ../.env  # Naj prikaže AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Zaženite aplikacijo:**

> **Note:** Če ste že zagnali vse aplikacije s `./start-all.sh` iz Modula 01, ta modul že teče na vratih 8084. Lahko preskočite ukaze za zagon spodaj in neposredno obiščete http://localhost:8084.

**Možnost 1: Uporaba Spring Boot nadzorne plošče (priporočeno za uporabnike VS Code)**

Razvojni kontejner vključuje razširitev Spring Boot Dashboard, ki nudi vizualni vmesnik za upravljanje vseh Spring Boot aplikacij. Najdete jo v Activity Bar na levi strani VS Code (poiščite ikono Spring Boot).

Iz Spring Boot Dashboard lahko:
- Vidite vse razpoložljive Spring Boot aplikacije v delovnem prostoru
- Zaženete/ustavite aplikacije z enim klikom
- V realnem času si ogledate dnevnike aplikacij
- Spremljate stanje aplikacij

Preprosto kliknite gumb za predvajanje poleg "tools", da zaženete ta modul, ali zaženite vse module naenkrat.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.sl.png" alt="Nadzorna plošča Spring Boot" width="400"/>

**Možnost 2: Uporaba skript v lupini**

Zaženite vse spletne aplikacije (moduli 01-04):

**Bash:**
```bash
cd ..  # Iz korenskega imenika
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Iz korenskega imenika
.\start-all.ps1
```

Ali zaženite samo ta modul:

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

Oba skripta samodejno naložita spremenljivke okolja iz korenske datoteke `.env` in bodo zgradila JAR-je, če ti ne obstajajo.

> **Note:** Če želite raje zgraditi vse module ročno, preden jih zaženete:
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

Odprite http://localhost:8084 v vašem brskalniku.

**Za ustavitev:**

**Bash:**
```bash
./stop.sh  # Samo ta modul
# Ali
cd .. && ./stop-all.sh  # Vsi moduli
```

**PowerShell:**
```powershell
.\stop.ps1  # Samo ta modul
# Ali
cd ..; .\stop-all.ps1  # Vsi moduli
```

## Uporaba aplikacije

Aplikacija ponuja spletni vmesnik, kjer lahko komunicirate z AI agentom, ki ima dostop do orodij za vreme in pretvorbo temperatur.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.sl.png" alt="Vmesnik orodij za AI agente" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Vmesnik AI Agent Tools - hitri primeri in klepetalni vmesnik za interakcijo z orodji*

**Poskusite preprosto uporabo orodij**

Začnite s preprosto zahtevo: "Pretvori 100 stopinj Fahrenheita v Celzija". Agent prepozna, da potrebuje orodje za pretvorbo temperature, ga pokliče s pravimi parametri in vrne rezultat. Opazite, kako naravno to deluje - niste določili, katero orodje uporabiti ali kako ga poklicati.

**Preizkusite verižanje orodij**

Zdaj poskusite nekaj bolj zapletenega: "Kako je vreme v Seattlu in pretvori v Fahrenheite?" Oglejte si, kako agent to izpelje korak za korakom. Najprej dobi vreme (ki vrne v Celziju), prepozna potrebo po pretvorbi v Fahrenheite, pokliče orodje za pretvorbo in združi oba rezultata v en odgovor.

**Oglejte si potek pogovora**

Klepetalni vmesnik ohranja zgodovino pogovora, kar omogoča večkrožno interakcijo. Vidite lahko vse prejšnje poizvedbe in odgovore, kar olajša sledenje pogovoru in razumevanje, kako agent gradi kontekst skozi več izmenjav.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.sl.png" alt="Pogovor z več klici orodij" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Večkrožni pogovor, ki prikazuje preproste pretvorbe, poizvedbe o vremenu in verižanje orodij*

**Igrajte se z raznimi zahtevami**

Preizkusite različne kombinacije:
- Poizvedbe o vremenu: "Kako je vreme v Tokiu?"
- Pretvorbe temperature: "Koliko je 25°C v Kelvin?"
- Združene poizvedbe: "Preveri vreme v Parizu in povej, ali je nad 20°C"

Opazite, kako agent interpretira naravni jezik in ga preslika v ustrezne klice orodij.

## Ključni pojmi

**Vzorec ReAct (Reasoning and Acting)**

Agent izmenjuje sklepanja (odločanje, kaj storiti) in dejanja (uporaba orodij). Ta vzorec omogoča avtonomno reševanje problemov namesto zgolj odgovarjanja na navodila.

**Opisi orodij so pomembni**

Kakovost opisov vaših orodij neposredno vpliva na to, kako dobro jih agent uporablja. Jasni, specifični opisi pomagajo modelu razumeti, kdaj in kako poklicati posamezno orodje.

**Upravljanje sej**

Oznaka `@MemoryId` omogoča samodejno upravljanje spomina na osnovi seje. Vsak ID seje dobi svojo instanco `ChatMemory`, ki jo upravlja bean `ChatMemoryProvider`, s čimer odpravimo potrebo po ročnem sledenju pomnilniku.

**Ravnanje z napakami**

Orodja lahko odpovedo - API-ji potečejo, parametri so lahko neveljavni, zunanji servisi so nedosegljivi. Proizvodni agenti potrebujejo obravnavo napak, da lahko model pojasni težave ali poskusi alternative.

## Razpoložljiva orodja

**Orodja za vreme** (vzorec podatkov za demonstracijo):
- Pridobi trenutno vreme za lokacijo
- Pridobi večdnevno napoved

**Orodja za pretvorbo temperature**:
- Celzij v Fahrenheit
- Fahrenheit v Celzij
- Celzij v Kelvin
- Kelvin v Celzij
- Fahrenheit v Kelvin
- Kelvin v Fahrenheit

To so preprosti primeri, a vzorec se razteza na katerokoli funkcijo: poizvedbe v bazo podatkov, klice API-jev, izračune, operacije z datotekami ali ukaze sistema.

## Kdaj uporabljati agente, ki uporabljajo orodja

**Uporabite orodja, ko:**
- Odgovor zahteva podatke v realnem času (vreme, cene delnic, zaloge)
- Potrebujete izvesti izračune, ki presegajo preprosto matematiko
- Dostopate do baz podatkov ali API-jev
- Izvajate dejanja (pošiljanje e-pošte, ustvarjanje kartic, posodabljanje zapisov)
- Združujete več virov podatkov

**Ne uporabljajte orodij, ko:**
- Vprašanja je mogoče odgovoriti iz splošnega znanja
- Odgovor je povsem pogovoren
- Latenca orodij bi izkušnjo naredila prepočasi

## Naslednji koraki

**Naslednji modul:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigacija:** [← Prejšnji: Modul 03 - RAG](../03-rag/README.md) | [Nazaj na glavno](../README.md) | [Naslednji: Modul 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Izjava o omejitvi odgovornosti**:
Ta dokument je bil preveden z uporabo storitve za prevajanje z umetno inteligenco [Co-op Translator](https://github.com/Azure/co-op-translator). Čeprav si prizadevamo za natančnost, vas prosimo, da upoštevate, da lahko avtomatski prevodi vsebujejo napake ali netočnosti. Izvirni dokument v njegovem izvirnem jeziku velja za avtoritativni vir. Za kritične informacije priporočamo strokovni človeški prevod. Ne odgovarjamo za kakršnekoli nesporazume ali napačne razlage, ki bi izhajale iz uporabe tega prevoda.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->