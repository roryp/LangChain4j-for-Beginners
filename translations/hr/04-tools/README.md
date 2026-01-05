<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-31T05:39:15+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "hr"
}
-->
# Modul 04: AI agenti s alatima

## Sadržaj

- [Što ćete naučiti](../../../04-tools)
- [Preduvjeti](../../../04-tools)
- [Razumijevanje AI agenata s alatima](../../../04-tools)
- [Kako funkcionira pozivanje alata](../../../04-tools)
  - [Definicije alata](../../../04-tools)
  - [Donošenje odluka](../../../04-tools)
  - [Izvršenje](../../../04-tools)
  - [Generiranje odgovora](../../../04-tools)
- [Lančanje alata](../../../04-tools)
- [Pokrenite aplikaciju](../../../04-tools)
- [Korištenje aplikacije](../../../04-tools)
  - [Isprobajte jednostavnu upotrebu alata](../../../04-tools)
  - [Testirajte lančanje alata](../../../04-tools)
  - [Pogledajte tok razgovora](../../../04-tools)
  - [Promatrajte razmišljanje](../../../04-tools)
  - [Eksperimentirajte s različitim zahtjevima](../../../04-tools)
- [Ključni pojmovi](../../../04-tools)
  - [ReAct obrazac (razmišljanje i djelovanje)](../../../04-tools)
  - [Opisi alata su važni](../../../04-tools)
  - [Upravljanje sesijama](../../../04-tools)
  - [Rukovanje greškama](../../../04-tools)
- [Dostupni alati](../../../04-tools)
- [Kada koristiti agente temeljene na alatima](../../../04-tools)
- [Sljedeći koraci](../../../04-tools)

## Što ćete naučiti

Do sada ste naučili kako voditi razgovore s AI-jem, učinkovito strukturirati promptove i ukorijeniti odgovore u svoje dokumente. No postoji temeljno ograničenje: jezični modeli mogu generirati samo tekst. Ne mogu provjeriti vrijeme, napraviti izračune, upitati baze podataka ili komunicirati s vanjskim sustavima.

Alati to mijenjaju. Davanjem modelu pristupa funkcijama koje može pozivati, pretvarate ga iz generatora teksta u agenta koji može poduzimati radnje. Model odlučuje kada mu treba alat, koji alat koristiti i koje parametre proslijediti. Vaš kod izvršava funkciju i vraća rezultat. Model uključuje taj rezultat u svoj odgovor.

## Preduvjeti

- Završen Modul 01 (Azure OpenAI resursi postavljeni)
- `.env` datoteka u root direktoriju s Azure vjerodajnicama (kreirana naredbom `azd up` u Modulu 01)

> **Napomena:** Ako niste dovršili Modul 01, prvo slijedite upute za implementaciju tamo.

## Razumijevanje AI agenata s alatima

> **📝 Napomena:** Termin "agenti" u ovom modulu odnosi se na AI asistente proširene mogućnostima pozivanja alata. To se razlikuje od **Agentic AI** obrazaca (autonomni agenti s planiranjem, memorijom i višestupanjskim rasuđivanjem) koje ćemo obraditi u [Module 05: MCP](../05-mcp/README.md).

AI agent s alatima slijedi obrazac razmišljanja i djelovanja (ReAct):

1. Korisnik postavi pitanje
2. Agent razmišlja o tome što treba znati
3. Agent odluči treba li mu alat za odgovor
4. Ako da, agent pozove odgovarajući alat s ispravnim parametrima
5. Alat se izvrši i vraća podatke
6. Agent uključi rezultat i daje konačan odgovor

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.hr.png" alt="ReAct obrazac" width="800"/>

*ReAct obrazac - kako AI agenti izmjenjuju razmišljanje i djelovanje kako bi riješili probleme*

Ovo se događa automatski. Definirate alate i njihove opise. Model sam odlučuje kada i kako ih koristiti.

## Kako funkcionira pozivanje alata

**Definicije alata** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Definirate funkcije s jasnim opisima i specifikacijama parametara. Model vidi te opise u svom system promptu i razumije što svaki alat radi.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Vaša logika za dohvaćanje vremenske prognoze
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Assistant je automatski povezan od strane Spring Boota sa:
// - ChatModel bean
// - Sve @Tool metode iz @Component klasa
// - ChatMemoryProvider za upravljanje sesijama
```

> **🤖 Isprobajte s [GitHub Copilot] Chat:** Otvorite [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) i pitajte:
> - "Kako bih integrirao stvarni vremenski API poput OpenWeatherMap umjesto mock podataka?"
> - "Što čini dobar opis alata koji pomaže AI-ju da ga pravilno koristi?"
> - "Kako u implementaciji alata rukovati greškama API-ja i ograničenjima brzine (rate limits)?"

**Donošenje odluka**

Kada korisnik pita "Kakvo je vrijeme u Seattleu?", model prepoznaje da mu treba alat za vremensku prognozu. Generira poziv funkcije s parametrom lokacije postavljenim na "Seattle".

**Izvršenje** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot automatski injektira deklarativno `@AiService` sučelje sa svim registriranim alatima, a LangChain4j automatski izvršava pozive alata.

> **🤖 Isprobajte s [GitHub Copilot] Chat:** Otvorite [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) i pitajte:
> - "Kako funkcionira ReAct obrazac i zašto je učinkovit za AI agente?"
> - "Kako agent odlučuje koji alat koristiti i kojim redoslijedom?"
> - "Što se događa ako izvršenje alata ne uspije - kako bih trebao robusno rukovati pogreškama?"

**Generiranje odgovora**

Model prima podatke o vremenu i formatira ih u prirodni jezični odgovor za korisnika.

### Zašto koristiti deklarativne AI servise?

Ovaj modul koristi LangChain4j-ovu Spring Boot integraciju s deklarativnim `@AiService` sučeljima:

- **Spring Boot automatska injekcija** - ChatModel i alati automatski ubrizgani
- **@MemoryId obrazac** - Automatsko upravljanje memorijom temeljeno na sesiji
- **Jedna instanca** - Asistent se stvara jednom i ponovno koristi radi bolje izvedbe
- **Tip-sigurno izvršavanje** - Java metode se pozivaju izravno s konverzijom tipova
- **Orkestracija s više okretaja** - Automatski rukuje lančanjem alata
- **Bez boilerplatea** - Nema ručnih poziva AiServices.builder() ili mapiranja memorije u HashMap

Alternativni pristupi (ručni `AiServices.builder()`) zahtijevaju više koda i propuštaju prednosti Spring Boot integracije.

## Lančanje alata

**Lančanje alata** - AI može pozvati više alata jedan za drugim. Pitajte "Kakvo je vrijeme u Seattleu i trebam li ponijeti kišobran?" i promatrajte kako lanča `getCurrentWeather` s razmišljanjem o opremi za kišu.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.hr.png" alt="Lančanje alata" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sekvencijalni pozivi alata - izlaz jednog alata se koristi za sljedeću odluku*

**Rukovanje greškama** - Pitajte za vremensku prognozu u gradu koji nije u mock podacima. Alat vraća poruku o pogrešci, a AI objašnjava da ne može pomoći. Alati sigurnije ne uspijevaju.

Ovo se događa u jednom razgovoru. Agent autonomno orkestrira više poziva alata.

## Pokrenite aplikaciju

**Provjerite implementaciju:**

Osigurajte da `.env` datoteka postoji u root direktoriju s Azure vjerodajnicama (kreirana tijekom Modula 01):
```bash
cat ../.env  # Treba prikazati AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Pokrenite aplikaciju:**

> **Napomena:** Ako ste već pokrenuli sve aplikacije koristeći `./start-all.sh` iz Modula 01, ovaj modul već radi na portu 8084. Možete preskočiti naredbe za pokretanje u nastavku i izravno otići na http://localhost:8084.

**Opcija 1: Korištenje Spring Boot nadzorne ploče (preporučeno za korisnike VS Code-a)**

Dev container uključuje ekstenziju Spring Boot Dashboard, koja pruža vizualno sučelje za upravljanje svim Spring Boot aplikacijama. Možete je pronaći na Activity Baru s lijeve strane VS Code-a (potražite ikonu Spring Boot-a).

Iz Spring Boot nadzorne ploče možete:
- Vidjeti sve dostupne Spring Boot aplikacije u workspaceu
- Pokrenuti/zaustaviti aplikacije jednim klikom
- Pregledavati logove aplikacije u stvarnom vremenu
- Nadzirati status aplikacije

Jednostavno kliknite gumb za pokretanje pokraj 'tools' da pokrenete ovaj modul, ili pokrenite sve module odjednom.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.hr.png" alt="Spring Boot nadzorna ploča" width="400"/>

**Opcija 2: Korištenje shell skripti**

Pokrenite sve web aplikacije (moduli 01-04):

**Bash:**
```bash
cd ..  # Iz korijenskog direktorija
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Iz korijenskog direktorija
.\start-all.ps1
```

Ili pokrenite samo ovaj modul:

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

Obje skripte automatski učitavaju varijable okoline iz root `.env` datoteke i izgradit će JAR-ove ako ne postoje.

> **Napomena:** Ako radije želite ručno izgraditi sve module prije pokretanja:
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

Otvorite http://localhost:8084 u vašem pregledniku.

**Za zaustavljanje:**

**Bash:**
```bash
./stop.sh  # Samo ovaj modul
# Ili
cd .. && ./stop-all.sh  # Svi moduli
```

**PowerShell:**
```powershell
.\stop.ps1  # Samo ovaj modul
# Ili
cd ..; .\stop-all.ps1  # Svi moduli
```

## Korištenje aplikacije

Aplikacija pruža web sučelje gdje možete komunicirati s AI agentom koji ima pristup alatima za vremensku prognozu i pretvorbu temperature.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.hr.png" alt="Sučelje AI agenata s alatima" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sučelje AI agenata s alatima - brzi primjeri i chat sučelje za interakciju s alatima*

**Isprobajte jednostavnu upotrebu alata**

Započnite sa jednostavnim zahtjevom: "Pretvori 100 stupnjeva Fahrenheita u Celzijeve". Agent prepoznaje da mu treba alat za pretvorbu temperature, poziva ga s ispravnim parametrima i vraća rezultat. Primijetite koliko je prirodno to iskustvo - niste specificirali koji alat koristiti niti kako ga pozvati.

**Testirajte lančanje alata**

Sada isprobajte nešto složenije: "Kakvo je vrijeme u Seattleu i pretvori ga u Fahrenheite?" Promatrajte kako agent radi kroz korake. Prvo dobiva vremenske podatke (koji vraćaju Celzijuse), zatim prepoznaje da treba pretvoriti u Fahrenheite, poziva alat za pretvorbu i kombinira oba rezultata u jednom odgovoru.

**Pogledajte tok razgovora**

Chat sučelje održava povijest razgovora, omogućujući vam višekratne interakcije. Možete vidjeti sve prethodne upite i odgovore, što olakšava praćenje razgovora i razumijevanje kako agent gradi kontekst kroz više razmjena.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.hr.png" alt="Razgovor s više poziva alata" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Višekratni razgovor koji prikazuje jednostavne pretvorbe, pretrage vremena i lančanje alata*

**Eksperimentirajte s različitim zahtjevima**

Isprobajte različite kombinacije:
- Pretrage vremena: "Kakvo je vrijeme u Tokiju?"
- Pretvorbe temperature: "Što je 25°C u Kelvinima?"
- Kombinirani upiti: "Provjeri vrijeme u Parizu i reci mi je li iznad 20°C"

Primijetite kako agent interpretira prirodni jezik i preslikava ga na odgovarajuće pozive alata.

## Ključni pojmovi

**ReAct obrazac (razmišljanje i djelovanje)**

Agent izmjenjuje razmišljanje (odlučivanje što napraviti) i djelovanje (korištenje alata). Ovaj obrazac omogućuje autonomno rješavanje problema umjesto samo odgovaranja na upute.

**Opisi alata su važni**

Kvaliteta opisa vaših alata izravno utječe na to koliko dobro ih agent koristi. Jasni, specifični opisi pomažu modelu razumjeti kada i kako pozvati svaki alat.

**Upravljanje sesijama**

Annotacija `@MemoryId` omogućuje automatsko upravljanje memorijom temeljeno na sesiji. Svaki ID sesije dobije svoju instancu `ChatMemory` koju upravlja bean `ChatMemoryProvider`, čime se eliminira potreba za ručnim praćenjem memorije.

**Rukovanje greškama**

Alati mogu ne uspjeti - API-ji mogu istekne, parametri mogu biti neispravni, vanjski servisi mogu otkazati. Produkcijski agenti trebaju rukovanje greškama kako bi model mogao objasniti probleme ili pokušati alternative.

## Dostupni alati

**Alati za vremensku prognozu** (mock podaci za demonstraciju):
- Dohvati trenutno vrijeme za lokaciju
- Dohvati višednevnu prognozu

**Alati za pretvorbu temperature**:
- Celzijus u Fahrenheit
- Fahrenheit u Celzijus
- Celzijus u Kelvin
- Kelvin u Celzijus
- Fahrenheit u Kelvin
- Kelvin u Fahrenheit

Ovo su jednostavni primjeri, ali obrazac se može proširiti na bilo koju funkciju: upite baze podataka, API pozive, izračune, rad s datotekama ili sistemske naredbe.

## Kada koristiti agente temeljene na alatima

**Koristite alate kada:**
- Odgovor zahtijeva podatke u stvarnom vremenu (vrijeme, cijene dionica, inventar)
- Trebate obaviti izračune složenije od jednostavne matematike
- Pristupate bazama podataka ili API-jima
- Poduzimate radnje (slanje e-pošte, kreiranje ticket-a, ažuriranje zapisa)
- Kombinirate više izvora podataka

**Ne koristite alate kada:**
- Pitanja se mogu odgovoriti iz općeg znanja
- Odgovor je čisto konverzacijski
- Latencija alata učinila bi iskustvo presporo

## Sljedeći koraci

**Sljedeći modul:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigacija:** [← Prethodno: Module 03 - RAG](../03-rag/README.md) | [Povratak na početnu](../README.md) | [Sljedeće: Module 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Odricanje od odgovornosti**:
Ovaj dokument preveden je pomoću AI usluge za prijevod [Co-op Translator](https://github.com/Azure/co-op-translator). Iako nastojimo postići točnost, imajte na umu da automatski prijevodi mogu sadržavati pogreške ili netočnosti. Izvorni dokument na svom izvornom jeziku treba smatrati autoritativnim izvorom. Za kritične informacije preporučujemo profesionalni prijevod koji obavlja ljudski prevoditelj. Ne snosimo odgovornost za bilo kakve nesporazume ili pogrešna tumačenja koja proizlaze iz korištenja ovog prijevoda.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->