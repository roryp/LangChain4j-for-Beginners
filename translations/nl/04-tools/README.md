<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-31T02:18:02+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "nl"
}
-->
# Module 04: AI-agenten met tools

## Table of Contents

- [Wat je zult leren](../../../04-tools)
- [Vereisten](../../../04-tools)
- [AI-agenten met tools begrijpen](../../../04-tools)
- [Hoe het oproepen van tools werkt](../../../04-tools)
  - [Tooldefinities](../../../04-tools)
  - [Besluitvorming](../../../04-tools)
  - [Uitvoering](../../../04-tools)
  - [Antwoordgeneratie](../../../04-tools)
- [Koppeling van tools](../../../04-tools)
- [De applicatie uitvoeren](../../../04-tools)
- [De applicatie gebruiken](../../../04-tools)
  - [Probeer eenvoudig toolgebruik](../../../04-tools)
  - [Test toolkoppeling](../../../04-tools)
  - [Bekijk het gespreksverloop](../../../04-tools)
  - [Bekijk het redeneren](../../../04-tools)
  - [Experimenteer met verschillende verzoeken](../../../04-tools)
- [Belangrijke concepten](../../../04-tools)
  - [ReAct-patroon (Redeneren en Handelen)](../../../04-tools)
  - [Toolbeschrijvingen zijn belangrijk](../../../04-tools)
  - [Sessiebeheer](../../../04-tools)
  - [Foutafhandeling](../../../04-tools)
- [Beschikbare tools](../../../04-tools)
- [Wanneer je tool-gebaseerde agenten moet gebruiken](../../../04-tools)
- [Volgende stappen](../../../04-tools)

## Wat je zult leren

Tot nu toe heb je geleerd hoe je gesprekken met AI voert, prompts effectief structureert en antwoorden baseert op je documenten. Maar er blijft een fundamentele beperking: taalmodellen kunnen alleen tekst genereren. Ze kunnen het weer niet opzoeken, geen berekeningen uitvoeren, geen databases raadplegen of met externe systemen communiceren.

Tools veranderen dit. Door het model toegang te geven tot functies die het kan aanroepen, verander je het van een tekstgenerator in een agent die acties kan ondernemen. Het model beslist wanneer het een tool nodig heeft, welke tool te gebruiken en welke parameters mee te geven. Jouw code voert de functie uit en geeft het resultaat terug. Het model verwerkt dat resultaat in zijn antwoord.

## Vereisten

- Voltooid Module 01 (Azure OpenAI-resources gedeployed)
- `.env` bestand in de rootmap met Azure-gegevens (aangemaakt door `azd up` in Module 01)

> **Opmerking:** Als je Module 01 nog niet hebt afgerond, volg dan eerst de daar gegeven deployment-instructies.

## AI-agenten met tools begrijpen

> **📝 Opmerking:** De term "agenten" in deze module verwijst naar AI-assistenten die zijn uitgebreid met tool-oproepmogelijkheden. Dit is anders dan de **Agentic AI**-patronen (autonome agenten met planning, geheugen en meerstaps-redenering) die we behandelen in [Module 05: MCP](../05-mcp/README.md).

Een AI-agent met tools volgt een redeneren-en-handelen patroon (ReAct):

1. Gebruiker stelt een vraag
2. Agent redeneert over wat hij moet weten
3. Agent beslist of hij een tool nodig heeft om te antwoorden
4. Als dat zo is, roept de agent de juiste tool aan met de juiste parameters
5. De tool voert uit en geeft gegevens terug
6. Agent verwerkt het resultaat en geeft het uiteindelijke antwoord

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.nl.png" alt="ReAct-patroon" width="800"/>

*Het ReAct-patroon - hoe AI-agenten afwisselen tussen redeneren en handelen om problemen op te lossen*

Dit gebeurt automatisch. Jij definieert de tools en hun beschrijvingen. Het model verzorgt de besluitvorming over wanneer en hoe ze te gebruiken.

## Hoe het oproepen van tools werkt

**Tooldefinities** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Je definieert functies met duidelijke beschrijvingen en parameter-specificaties. Het model ziet deze beschrijvingen in zijn system prompt en begrijpt wat elke tool doet.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Uw logica voor het opzoeken van het weer
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Assistant wordt automatisch door Spring Boot gekoppeld met:
// - ChatModel bean
// - Alle @Tool-methoden van @Component-klassen
// - ChatMemoryProvider voor sessiebeheer
```

> **🤖 Probeer met [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) en vraag:
> - "Hoe zou ik een echte weer-API zoals OpenWeatherMap integreren in plaats van mockdata?"
> - "Wat maakt een goede toolbeschrijving die het AI-model helpt de tool correct te gebruiken?"
> - "Hoe ga ik om met API-fouten en rate limits in toolimplementaties?"

**Besluitvorming**

Wanneer een gebruiker vraagt "What's the weather in Seattle?", herkent het model dat het de weertool nodig heeft. Het genereert een functie-oproep met de locatieparameter ingesteld op "Seattle".

**Uitvoering** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot wiret automatisch de declaratieve `@AiService`-interface met alle geregistreerde tools, en LangChain4j voert tool-oproepen automatisch uit.

> **🤖 Probeer met [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) en vraag:
> - "Hoe werkt het ReAct-patroon en waarom is het effectief voor AI-agenten?"
> - "Hoe beslist de agent welke tool te gebruiken en in welke volgorde?"
> - "Wat gebeurt er als een tooluitvoering faalt - hoe moet ik fouten robuust afhandelen?"

**Antwoordgeneratie**

Het model ontvangt de weergegevens en formatteert deze tot een natuurlijk taalantwoord voor de gebruiker.

### Waarom declaratieve AI-services gebruiken?

Deze module gebruikt LangChain4j's Spring Boot-integratie met declaratieve `@AiService`-interfaces:

- **Spring Boot auto-wiring** - ChatModel en tools worden automatisch geïnjecteerd
- **@MemoryId-patroon** - Automatisch sessie-gebaseerd geheugenbeheer
- **Enkele instantie** - Assistant wordt eenmaal aangemaakt en hergebruikt voor betere prestaties
- **Type-veilige uitvoering** - Java-methoden worden direct aangeroepen met typeconversie
- **Multi-turn orkestratie** - Handelt tool-koppeling automatisch af
- **Zero boilerplate** - Geen handmatige AiServices.builder() aanroepen of geheugen-HashMap

Alternatieve benaderingen (handmatige `AiServices.builder()`) vereisen meer code en missen de Spring Boot-integratievoordelen.

## Koppeling van tools

**Tool chaining** - De AI kan meerdere tools achtereenvolgens aanroepen. Vraag "What's the weather in Seattle and should I bring an umbrella?" en zie hoe het `getCurrentWeather` ketent met redenering over regenkleding.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.nl.png" alt="Koppeling van tools" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sequentiële tool-oproepen - de output van de ene tool voedt de volgende beslissing*

**Vriendelijke fouten** - Vraag om het weer in een stad die niet in de mockdata staat. De tool geeft een foutmelding terug en de AI legt uit dat hij niet kan helpen. Tools falen veilig.

Dit gebeurt in één conversatieronde. De agent orkestreert meerdere tool-oproepen autonoom.

## De applicatie uitvoeren

**Controleer deployment:**

Zorg ervoor dat het `.env` bestand in de rootmap bestaat met Azure-gegevens (aangemaakt tijdens Module 01):
```bash
cat ../.env  # Moet AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT tonen
```

**Start de applicatie:**

> **Opmerking:** Als je alle applicaties al hebt gestart met `./start-all.sh` uit Module 01, draait deze module al op poort 8084. Je kunt de startcommando's hieronder overslaan en direct naar http://localhost:8084 gaan.

**Optie 1: Gebruik van Spring Boot Dashboard (Aanbevolen voor VS Code-gebruikers)**

De dev-container bevat de Spring Boot Dashboard-extensie, die een visuele interface biedt om alle Spring Boot-applicaties te beheren. Je vindt deze in de Activity Bar aan de linkerkant van VS Code (zoek naar het Spring Boot-pictogram).

Vanaf het Spring Boot Dashboard kun je:
- Alle beschikbare Spring Boot-applicaties in de workspace zien
- Applicaties starten/stoppen met één klik
- Applicatielogs in realtime bekijken
- Applicatiestatus monitoren

Klik eenvoudig op de afspeelknop naast "tools" om deze module te starten, of start alle modules tegelijk.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.nl.png" alt="Spring Boot-dashboard" width="400"/>

**Optie 2: Gebruik van shell-scripts**

Start alle webapplicaties (modules 01-04):

**Bash:**
```bash
cd ..  # Vanaf de rootmap
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Vanaf de hoofdmap
.\start-all.ps1
```

Of start alleen deze module:

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

Beide scripts laden automatisch omgevingsvariabelen uit het root `.env` bestand en zullen de JARs bouwen als ze nog niet bestaan.

> **Opmerking:** Als je de voorkeur geeft aan het handmatig bouwen van alle modules voordat je start:
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

Open http://localhost:8084 in je browser.

**Om te stoppen:**

**Bash:**
```bash
./stop.sh  # Alleen deze module
# Of
cd .. && ./stop-all.sh  # Alle modules
```

**PowerShell:**
```powershell
.\stop.ps1  # Alleen deze module
# Of
cd ..; .\stop-all.ps1  # Alle modules
```

## De applicatie gebruiken

De applicatie biedt een webinterface waarmee je kunt communiceren met een AI-agent die toegang heeft tot weer- en temperatuurconversietools.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.nl.png" alt="AI Agent Tools Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*De AI Agent Tools-interface - snelle voorbeelden en chatinterface om met tools te communiceren*

**Probeer eenvoudig toolgebruik**

Begin met een eenvoudige vraag: "Convert 100 degrees Fahrenheit to Celsius". De agent herkent dat hij de temperatuurconversietool nodig heeft, roept deze aan met de juiste parameters en geeft het resultaat terug. Merk op hoe natuurlijk dit aanvoelt - je specificeerde niet welke tool te gebruiken of hoe deze aan te roepen.

**Test toolkoppeling**

Probeer nu iets complexers: "What's the weather in Seattle and convert it to Fahrenheit?" Kijk hoe de agent dit stap voor stap afhandelt. Hij haalt eerst het weer op (dat Celsius teruggeeft), herkent dat hij moet omrekenen naar Fahrenheit, roept de conversietool aan en combineert beide resultaten in één antwoord.

**Bekijk het gespreksverloop**

De chatinterface bewaart de gesprekshistorie, zodat je meerturn-interacties kunt voeren. Je kunt alle eerdere vragen en antwoorden zien, waardoor het gemakkelijk is het gesprek te volgen en te begrijpen hoe de agent context opbouwt over meerdere uitwisselingen.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.nl.png" alt="Gesprek met meerdere tool-oproepen" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Meerturn-gesprek met eenvoudige conversies, weeropzoekingen en toolkoppeling*

**Experimenteer met verschillende verzoeken**

Probeer verschillende combinaties:
- Weeropzoekingen: "What's the weather in Tokyo?"
- Temperatuurconversies: "What is 25°C in Kelvin?"
- Gecombineerde vragen: "Check the weather in Paris and tell me if it's above 20°C"

Merk op hoe de agent natuurlijke taal interpreteert en deze mappt naar geschikte tool-oproepen.

## Belangrijke concepten

**ReAct-patroon (Redeneren en Handelen)**

De agent wisselt af tussen redeneren (beslissen wat te doen) en handelen (tools gebruiken). Dit patroon maakt autonoom probleemoplossen mogelijk in plaats van alleen reageren op instructies.

**Toolbeschrijvingen zijn belangrijk**

De kwaliteit van je toolbeschrijvingen beïnvloedt direct hoe goed de agent ze gebruikt. Duidelijke, specifieke beschrijvingen helpen het model te begrijpen wanneer en hoe elke tool aan te roepen.

**Sessiebeheer**

De `@MemoryId`-annotatie maakt automatisch sessie-gebaseerd geheugenbeheer mogelijk. Elke sessie-ID krijgt zijn eigen `ChatMemory`-instantie die wordt beheerd door de `ChatMemoryProvider` bean, waardoor handmatige geheugenregistratie overbodig wordt.

**Foutafhandeling**

Tools kunnen falen - API's timen uit, parameters kunnen ongeldig zijn, externe services kunnen uitvallen. Productie-agenten hebben foutafhandeling nodig zodat het model problemen kan uitleggen of alternatieven kan proberen.

## Beschikbare tools

**Weertools** (mockdata voor demonstratie):
- Huidig weer opvragen voor een locatie
- Meerdaagse weersvoorspelling opvragen

**Temperatuurconversietools**:
- Celsius naar Fahrenheit
- Fahrenheit naar Celsius
- Celsius naar Kelvin
- Kelvin naar Celsius
- Fahrenheit naar Kelvin
- Kelvin naar Fahrenheit

Dit zijn eenvoudige voorbeelden, maar het patroon breidt zich uit naar elke functie: databasequeries, API-aanroepen, berekeningen, bestandbewerkingen of systeemcommando's.

## Wanneer je tool-gebaseerde agenten moet gebruiken

**Gebruik tools wanneer:**
- Antwoorden realtime gegevens vereisen (weer, aandelenkoersen, voorraad)
- Je berekeningen moet uitvoeren die verder gaan dan eenvoudige wiskunde
- Toegang tot databases of API's nodig is
- Acties ondernomen moeten worden (e-mails verzenden, tickets aanmaken, records bijwerken)
- Meerdere gegevensbronnen gecombineerd moeten worden

**Gebruik geen tools wanneer:**
- Vragen beantwoord kunnen worden uit algemene kennis
- Het antwoord puur conversatiegericht is
- Tool-latentie de ervaring te traag zou maken

## Volgende stappen

**Volgende module:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigatie:** [← Vorige: Module 03 - RAG](../03-rag/README.md) | [Terug naar hoofdmap](../README.md) | [Volgende: Module 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Disclaimer:
Dit document is vertaald met behulp van de AI-vertalingsservice [Co-op Translator](https://github.com/Azure/co-op-translator). Hoewel we naar nauwkeurigheid streven, dient u er rekening mee te houden dat geautomatiseerde vertalingen fouten of onjuistheden kunnen bevatten. Het oorspronkelijke document in de originele taal moet als de gezaghebbende bron worden beschouwd. Voor kritieke informatie wordt professionele menselijke vertaling aanbevolen. Wij zijn niet aansprakelijk voor eventuele misverstanden of onjuiste interpretaties die voortvloeien uit het gebruik van deze vertaling.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->