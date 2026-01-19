<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-05T23:52:35+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "no"
}
-->
# Modul 04: AI-agenter med verktøy

## Innholdsfortegnelse

- [Hva du vil lære](../../../04-tools)
- [Forutsetninger](../../../04-tools)
- [Forstå AI-agenter med verktøy](../../../04-tools)
- [Hvordan verktøyråpning fungerer](../../../04-tools)
  - [Verktøydefinisjoner](../../../04-tools)
  - [Beslutningstaking](../../../04-tools)
  - [Utførelse](../../../04-tools)
  - [Responsgenerering](../../../04-tools)
- [Kjedede verktøy](../../../04-tools)
- [Kjør applikasjonen](../../../04-tools)
- [Bruke applikasjonen](../../../04-tools)
  - [Prøv enkel verktøybruk](../../../04-tools)
  - [Test verktøykjedning](../../../04-tools)
  - [Se samtaleflyt](../../../04-tools)
  - [Eksperimenter med forskjellige forespørsler](../../../04-tools)
- [Nøkkelkoncepter](../../../04-tools)
  - [ReAct-mønsteret (Resonnering og Handling)](../../../04-tools)
  - [Verktøybeskrivelser betyr noe](../../../04-tools)
  - [Sesjonshåndtering](../../../04-tools)
  - [Feilhåndtering](../../../04-tools)
- [Tilgjengelige verktøy](../../../04-tools)
- [Når du skal bruke verktøybaserte agenter](../../../04-tools)
- [Neste steg](../../../04-tools)

## Hva du vil lære

Så langt har du lært hvordan du kan ha samtaler med AI, strukturere prompt effektivt og forankre svar i dokumentene dine. Men det finnes fortsatt en grunnleggende begrensning: språkmodeller kan bare generere tekst. De kan ikke sjekke vær, utføre beregninger, spørre databaser eller samhandle med eksterne systemer.

Verktøy endrer dette. Ved å gi modellen tilgang til funksjoner den kan kalle, forvandler du den fra en tekstgenerator til en agent som kan utføre handlinger. Modellen bestemmer når den trenger et verktøy, hvilket verktøy den skal bruke og hvilke parametere den skal sende. Koden din utfører funksjonen og returnerer resultatet. Modellen integrerer resultatet i sitt svar.

## Forutsetninger

- Fullført modul 01 (Azure OpenAI-ressurser distribuert)
- `.env`-fil i rotkatalogen med Azure-legitimasjon (opprettet av `azd up` i modul 01)

> **Merk:** Hvis du ikke har fullført modul 01, følg distribusjonsinstruksjonene der først.

## Forstå AI-agenter med verktøy

> **📝 Merk:** Begrepet "agenter" i denne modulen refererer til AI-assistenter utvidet med verktøyråpningsevner. Dette er forskjellig fra **Agentic AI**-mønstrene (autonome agenter med planlegging, hukommelse og flerstegs resonnement) som vi vil dekke i [Modul 05: MCP](../05-mcp/README.md).

En AI-agent med verktøy følger et resonerings- og handlemønster (ReAct):

1. Bruker stiller et spørsmål
2. Agent resonerer om hva den trenger å vite
3. Agent avgjør om den trenger et verktøy for å svare
4. Hvis ja, ringer agenten det passende verktøyet med riktige parametere
5. Verktøyet kjører og returnerer data
6. Agenten inkorporerer resultatet og gir endelig svar

<img src="../../../translated_images/no/react-pattern.86aafd3796f3fd13.webp" alt="ReAct Pattern" width="800"/>

*ReAct-mønsteret – hvordan AI-agenter veksler mellom resonnement og handling for å løse problemer*

Dette skjer automatisk. Du definerer verktøyene og deres beskrivelser. Modellen håndterer beslutningstaking om når og hvordan de skal brukes.

## Hvordan verktøyråpning fungerer

### Verktøydefinisjoner

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Du definerer funksjoner med klare beskrivelser og parameter-spesifikasjoner. Modellen ser disse beskrivelsene i sitt system-prompt og forstår hva hvert verktøy gjør.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Din væroppslagslogikk
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Assistenten er automatisk koblet av Spring Boot med:
// - ChatModel bean
// - Alle @Tool-metoder fra @Component-klasser
// - ChatMemoryProvider for sesjonsadministrasjon
```

> **🤖 Prøv med [GitHub Copilot](https://github.com/features/copilot) Chat:** Åpne [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) og spør:
> - "Hvordan kan jeg integrere en ekte vær-API som OpenWeatherMap i stedet for mock-data?"
> - "Hva kjennetegner en god verktøybeskrivelse som hjelper AI å bruke den riktig?"
> - "Hvordan håndterer jeg API-feil og rate limits i verktøyimplementasjoner?"

### Beslutningstaking

Når en bruker spør "Hvordan er været i Seattle?", gjenkjenner modellen at den trenger værverktøyet. Den genererer et funksjonskall med lokasjonsparameter satt til "Seattle".

### Utførelse

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot kobler automatisk sammen den deklarative `@AiService`-grensesnittet med alle registrerte verktøy, og LangChain4j utfører verktøykall automatisk.

> **🤖 Prøv med [GitHub Copilot](https://github.com/features/copilot) Chat:** Åpne [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) og spør:
> - "Hvordan fungerer ReAct-mønsteret og hvorfor er det effektivt for AI-agenter?"
> - "Hvordan avgjør agenten hvilket verktøy som skal brukes og i hvilken rekkefølge?"
> - "Hva skjer hvis verktøykjøring feiler – hvordan bør jeg håndtere feil robust?"

### Responsgenerering

Modellen mottar værdataene og formaterer det til et naturlig språk-svar til brukeren.

### Hvorfor bruke deklarative AI-tjenester?

Denne modulen bruker LangChain4js Spring Boot-integrasjon med deklarative `@AiService`-grensesnitt:

- **Spring Boot auto-wiring** – ChatModel og verktøy injiseres automatisk
- **@MemoryId-mønster** – Automatisk sesjonsbasert minnehåndtering
- **Enkelt instans** – Assistent opprettes én gang og gjenbrukes for bedre ytelse
- **Typesikker utførelse** – Java-metoder kalles direkte med typekonvertering
- **Multi-turn orkestrering** – Håndterer verktøykjedning automatisk
- **Null boilerplate** – Ingen manuelle AiServices.builder()-kall eller memory HashMap

Alternative tilnærminger (manuell `AiServices.builder()`) krever mer kode og går glipp av Spring Boot-integrasjonsfordelene.

## Kjedede verktøy

**Kjedede verktøy** – AI-en kan kalle flere verktøy i sekvens. Spør "Hvordan er været i Seattle og bør jeg ta med paraply?" og se hvordan den kjeder `getCurrentWeather` med resonnement om regnutstyr.

<a href="images/tool-chaining.png"><img src="../../../translated_images/no/tool-chaining.3b25af01967d6f7b.webp" alt="Tool Chaining" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sekvensielle verktøykall – ett verktøys utdata brukes i neste beslutning*

**Grasiøse feil** – Be om vær i en by som ikke finnes i mock-dataene. Verktøyet returnerer en feilmelding, og AI forklarer at den ikke kan hjelpe. Verktøy feiler trygt.

Dette skjer i en enkelt samtaleturn. Agenten orkestrerer flere verktøykall autonomt.

## Kjør applikasjonen

**Verifiser distribusjon:**

Sørg for at `.env`-filen finnes i rotkatalogen med Azure-legitimasjon (opprettet under modul 01):
```bash
cat ../.env  # Skal vise AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Start applikasjonen:**

> **Merk:** Hvis du allerede startet alle applikasjoner med `./start-all.sh` fra modul 01, kjører denne modulen allerede på port 8084. Du kan hoppe over startkommandoene nedenfor og gå direkte til http://localhost:8084.

**Alternativ 1: Bruke Spring Boot Dashboard (Anbefalt for VS Code-brukere)**

Dev-containeren inkluderer Spring Boot Dashboard-utvidelsen, som gir et visuelt grensesnitt for å administrere alle Spring Boot-applikasjoner. Du finner den i Activity Bar på venstre side i VS Code (se etter Spring Boot-ikonet).

Fra Spring Boot Dashboard kan du:
- Se alle tilgjengelige Spring Boot-applikasjoner i arbeidsområdet
- Starte/stoppe applikasjoner med ett klikk
- Se applikasjonslogger i sanntid
- Overvåke applikasjonsstatus

Klikk på avspillingsknappen ved siden av "tools" for å starte denne modulen, eller start alle moduler samtidig.

<img src="../../../translated_images/no/dashboard.9b519b1a1bc1b30a.webp" alt="Spring Boot Dashboard" width="400"/>

**Alternativ 2: Bruke shell-skript**

Start alle web-applikasjoner (moduler 01-04):

**Bash:**
```bash
cd ..  # Fra rotkatalogen
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Fra rotkatalogen
.\start-all.ps1
```

Eller start bare denne modulen:

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

Begge skriptene laster automatisk miljøvariabler fra rot `.env`-filen og bygger JAR-filer hvis de ikke finnes.

> **Merk:** Hvis du foretrekker å bygge alle moduler manuelt før start:
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

Åpne http://localhost:8084 i nettleseren din.

**For å stoppe:**

**Bash:**
```bash
./stop.sh  # Kun denne modulen
# Eller
cd .. && ./stop-all.sh  # Alle moduler
```

**PowerShell:**
```powershell
.\stop.ps1  # Kun denne modulen
# Eller
cd ..; .\stop-all.ps1  # Alle moduler
```

## Bruke applikasjonen

Applikasjonen tilbyr et webgrensesnitt hvor du kan samhandle med en AI-agent som har tilgang til vær- og temperaturkonverteringsverktøy.

<a href="images/tools-homepage.png"><img src="../../../translated_images/no/tools-homepage.4b4cd8b2717f9621.webp" alt="AI Agent Tools Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI Agent Tools-grensesnittet – raske eksempler og chattegrensesnitt for interaksjon med verktøy*

### Prøv enkel verktøybruk

Start med en enkel forespørsel: "Konverter 100 grader Fahrenheit til Celsius". Agenten gjenkjenner at den trenger temperaturkonverteringsverktøyet, kaller det med riktige parametere og returnerer resultatet. Legg merke til hvor naturlig det føles – du spesifiserte ikke hvilket verktøy som skulle brukes eller hvordan det skulle kalles.

### Test verktøykjedning

Prøv nå noe mer komplekst: "Hvordan er været i Seattle og konverter det til Fahrenheit?" Se hvordan agenten jobber gjennom dette steg for steg. Den henter først været (som returnerer Celsius), innser den må konvertere til Fahrenheit, kaller konverteringsverktøyet, og kombinerer begge resultatene til ett svar.

### Se samtaleflyt

Chattegrensesnittet lagrer samtalehistorikken, så du kan ha flerstegs interaksjoner. Du kan se alle tidligere spørsmål og svar, noe som gjør det enkelt å følge samtalen og forstå hvordan agenten bygger kontekst over flere utvekslinger.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/no/tools-conversation-demo.89f2ce9676080f59.webp" alt="Conversation with Multiple Tool Calls" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Flerstegs samtale som viser enkle konverteringer, væroppslag og verktøykjedning*

### Eksperimenter med forskjellige forespørsler

Prøv ulike kombinasjoner:
- Væropplag: "Hvordan er været i Tokyo?"
- Temperaturkonverteringer: "Hva er 25°C i Kelvin?"
- Kombinerte spørsmål: "Sjekk været i Paris og fortell meg om det er over 20°C"

Legg merke til hvordan agenten tolker naturlig språk og mapper det til passende verktøykall.

## Nøkkelkoncepter

### ReAct-mønsteret (Resonnering og Handling)

Agenten veksler mellom å resonnere (bestemme hva som skal gjøres) og å handle (bruke verktøy). Dette mønsteret gjør det mulig for autonom problemløsning i stedet for bare å svare på instruksjoner.

### Verktøybeskrivelser betyr noe

Kvaliteten på verktøybeskrivelsene dine påvirker direkte hvor godt agenten bruker dem. Klare, spesifikke beskrivelser hjelper modellen å forstå når og hvordan hvert verktøy skal kalles.

### Sesjonshåndtering

`@MemoryId`-annotasjonen muliggjør automatisk sesjonsbasert minnehåndtering. Hver sesjons-ID får sin egen `ChatMemory`-instans som håndteres av `ChatMemoryProvider`-bean, noe som eliminerer behovet for manuell minnesporing.

### Feilhåndtering

Verktøy kan feile – API-er kan tidsavbryte, parametere kan være ugyldige, eksterne tjenester kan være nede. Produktive agenter trenger feilhåndtering slik at modellen kan forklare problemer eller prøve alternativer.

## Tilgjengelige verktøy

**Værverktøy** (mock-data for demonstrasjon):
- Hent gjeldende vær for et sted
- Hent værmelding for flere dager

**Temperaturkonverteringsverktøy**:
- Celsius til Fahrenheit
- Fahrenheit til Celsius
- Celsius til Kelvin
- Kelvin til Celsius
- Fahrenheit til Kelvin
- Kelvin til Fahrenheit

Disse er enkle eksempler, men mønsteret kan utvides til hvilken som helst funksjon: databaseforespørsler, API-kall, beregninger, filoperasjoner eller systemkommandoer.

## Når du skal bruke verktøybaserte agenter

**Bruk verktøy når:**
- Svar krever sanntidsdata (vær, aksjekurser, lagerstatus)
- Du trenger å utføre beregninger utover enkel matematikk
- Tilgang til databaser eller API-er
- Utføre handlinger (sende e-post, opprette saker, oppdatere poster)
- Kombinere flere datakilder

**Ikke bruk verktøy når:**
- Spørsmål kan besvares med generell kunnskap
- Svaret er rent samtalebasert
- Verktøy-latens ville gjøre opplevelsen for treg

## Neste steg

**Neste modul:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigasjon:** [← Forrige: Modul 03 - RAG](../03-rag/README.md) | [Tilbake til hovedmeny](../README.md) | [Neste: Modul 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokumentet er oversatt ved hjelp av AI-oversettelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selv om vi streber etter nøyaktighet, vennligst vær oppmerksom på at automatiske oversettelser kan inneholde feil eller unøyaktigheter. Det opprinnelige dokumentet på originalspråket bør betraktes som den autoritative kilden. For viktig informasjon anbefales profesjonell menneskelig oversettelse. Vi er ikke ansvarlige for eventuelle misforståelser eller feiltolkninger som oppstår som følge av bruken av denne oversettelsen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->