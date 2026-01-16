<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c3e07ca58d0b8a3f47d3bf5728541e0a",
  "translation_date": "2025-12-13T14:17:58+00:00",
  "source_file": "01-introduction/README.md",
  "language_code": "et"
}
-->
# Moodul 01: LangChain4j-ga alustamine

## Sisukord

- [Mida sa õpid](../../../01-introduction)
- [Eeltingimused](../../../01-introduction)
- [Põhiprobleemi mõistmine](../../../01-introduction)
- [Tokenite mõistmine](../../../01-introduction)
- [Kuidas mälu töötab](../../../01-introduction)
- [Kuidas see kasutab LangChain4j](../../../01-introduction)
- [Azure OpenAI infrastruktuuri juurutamine](../../../01-introduction)
- [Rakenduse lokaalne käivitamine](../../../01-introduction)
- [Rakenduse kasutamine](../../../01-introduction)
  - [Olemitu vestlus (vasak paneel)](../../../01-introduction)
  - [Olemitu vestlus (parem paneel)](../../../01-introduction)
- [Järgmised sammud](../../../01-introduction)

## Mida sa õpid

Kui sa lõpetasid kiire alguse, nägid, kuidas saata päringuid ja saada vastuseid. See on alus, kuid tõelised rakendused vajavad rohkem. See moodul õpetab sind ehitama vestluslikku tehisintellekti, mis mäletab konteksti ja hoiab olekut – see on vahe ühekorra demo ja tootmiskõlbuliku rakenduse vahel.

Selles juhendis kasutame kogu aeg Azure OpenAI GPT-5 mudelit, sest selle arenenud mõtlemisvõime muudab erinevate mustrite käitumise selgemaks. Kui lisad mälu, näed erinevust selgelt. See teeb lihtsamaks mõista, mida iga komponent sinu rakendusele lisab.

Sa ehitad ühe rakenduse, mis demonstreerib mõlemat mustrit:

**Olemitu vestlus** – Iga päring on iseseisev. Mudelil puudub mälu varasemate sõnumite kohta. See on mustrit, mida kasutasid kiire alguse puhul.

**Olekuline vestlus** – Iga päring sisaldab vestluse ajalugu. Mudel hoiab konteksti mitme vahetuse jooksul. Seda nõuavad tootmisrakendused.

## Eeltingimused

- Azure tellimus koos Azure OpenAI ligipääsuga
- Java 21, Maven 3.9+
- Azure CLI (https://learn.microsoft.com/en-us/cli/azure/install-azure-cli)
- Azure Developer CLI (azd) (https://learn.microsoft.com/en-us/azure/developer/azure-developer-cli/install-azd)

> **Märkus:** Java, Maven, Azure CLI ja Azure Developer CLI (azd) on eelinstallitud antud devcontaineris.

> **Märkus:** See moodul kasutab GPT-5 Azure OpenAI peal. Juurutamine on automaatselt seadistatud `azd up` abil – ära muuda mudeli nime koodis.

## Põhiprobleemi mõistmine

Keelemudelid on olemitud. Iga API kõne on iseseisev. Kui sa saadad "Minu nimi on John" ja siis küsid "Mis mu nimi on?", siis mudelil pole aimu, et sa just end tutvustasid. Ta käsitleb iga päringut nagu see oleks sinu esimene vestlus.

See sobib lihtsate küsimuste ja vastuste jaoks, kuid on kasutu tõeliste rakenduste jaoks. Klienditeeninduse botid peavad mäletama, mida sa neile ütlesid. Isiklikud assistendid vajavad konteksti. Iga mitme vahetusega vestlus nõuab mälu.

<img src="../../../translated_images/et/stateless-vs-stateful.cc4a4765e649c41a.png" alt="Olemitu vs olekuline vestlus" width="800"/>

*Vahe olemitu (iseseisvad kõned) ja olekulise (kontekstiteadliku) vestluse vahel*

## Tokenite mõistmine

Enne vestlustesse sukeldumist on oluline mõista tokeneid – teksti põhiühikuid, mida keelemudelid töötlevad:

<img src="../../../translated_images/et/token-explanation.c39760d8ec650181.png" alt="Tokeni selgitus" width="800"/>

*Näide, kuidas tekst jaguneb tokeniteks – "I love AI!" muutub 4 eraldiseisvaks töötlemisüksuseks*

Tokenid on see, kuidas tehisintellekt mõõdab ja töötleb teksti. Sõnad, kirjavahemärgid ja isegi tühikud võivad olla tokenid. Sinu mudelil on piirang, kui palju tokeneid ta korraga töödelda suudab (GPT-5 puhul 400 000, millest kuni 272 000 sisendtokenit ja 128 000 väljundtokenit). Tokenite mõistmine aitab sul hallata vestluse pikkust ja kulusid.

## Kuidas mälu töötab

Vestluse mälu lahendab olemituse probleemi, hoides vestluse ajalugu. Enne päringu saatmist mudelile lisab raamistik eelnevad asjakohased sõnumid ette. Kui sa küsid "Mis mu nimi on?", saadab süsteem tegelikult kogu vestluse ajaloo, võimaldades mudelil näha, et sa ütlesid varem "Minu nimi on John."

LangChain4j pakub mälu teostusi, mis seda automaatselt haldavad. Sa valid, mitu sõnumit säilitada, ja raamistik haldab kontekstiakent.

<img src="../../../translated_images/et/memory-window.bbe67f597eadabb3.png" alt="Mälu akna kontseptsioon" width="800"/>

*MessageWindowChatMemory hoiab libisevat akent viimastest sõnumitest, automaatselt eemaldades vanu*

## Kuidas see kasutab LangChain4j

See moodul laiendab kiiret algust, integreerides Spring Booti ja lisades vestluse mälu. Siin on, kuidas osad kokku sobivad:

**Sõltuvused** – Lisa kaks LangChain4j teeki:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

**Vestlusmudel** – Konfigureeri Azure OpenAI Spring bean-ina ([LangChainConfig.java](../../../01-introduction/src/main/java/com/example/langchain4j/config/LangChainConfig.java)):

```java
@Bean
public OpenAiOfficialChatModel openAiOfficialChatModel() {
    return OpenAiOfficialChatModel.builder()
            .baseUrl(azureEndpoint)
            .apiKey(azureApiKey)
            .modelName(deploymentName)
            .timeout(Duration.ofMinutes(5))
            .maxRetries(3)
            .build();
}
```

Builder loeb mandaadid keskkonnamuutujatest, mis on seatud `azd up` poolt. `baseUrl` seadmine sinu Azure lõpp-punktile paneb OpenAI kliendi töötama Azure OpenAI-ga.

**Vestluse mälu** – Jälgi vestluse ajalugu MessageWindowChatMemory abil ([ConversationService.java](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java)):

```java
ChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

memory.add(UserMessage.from("My name is John"));
memory.add(AiMessage.from("Nice to meet you, John!"));

memory.add(UserMessage.from("What's my name?"));
AiMessage aiMessage = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage);
```

Loo mälu `withMaxMessages(10)` abil, et hoida viimased 10 sõnumit. Lisa kasutaja ja AI sõnumid tüübitud wrapperitega: `UserMessage.from(text)` ja `AiMessage.from(text)`. Ajaloo saamiseks kasuta `memory.messages()` ja saada see mudelile. Teenus hoiab eraldi mälu iga vestluse ID kohta, võimaldades mitmel kasutajal samaaegselt vestelda.

> **🤖 Proovi koos [GitHub Copilot](https://github.com/features/copilot) Chatiga:** Ava [`ConversationService.java`](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java) ja küsi:
> - "Kuidas MessageWindowChatMemory otsustab, milliseid sõnumeid akna täitumisel eemaldada?"
> - "Kas ma saan rakendada kohandatud mälu salvestust andmebaasi abil, mitte mälus?"
> - "Kuidas lisada vanade vestluste kokkuvõtte tegemist?"

Olemitu vestluse lõpp-punkt jätab mälu täielikult vahele – lihtsalt `chatModel.chat(prompt)` nagu kiire alguse puhul. Olekuline lõpp-punkt lisab sõnumid mällu, hangib ajaloo ja lisab selle konteksti iga päringu juurde. Sama mudeli konfiguratsioon, erinevad mustrid.

## Azure OpenAI infrastruktuuri juurutamine

**Bash:**
```bash
cd 01-introduction
azd up  # Valige tellimus ja asukoht (soovitatav on eastus2)
```

**PowerShell:**
```powershell
cd 01-introduction
azd up  # Valige tellimus ja asukoht (soovitatav on eastus2)
```

> **Märkus:** Kui tekib ajapiirangu viga (`RequestConflict: Cannot modify resource ... provisioning state is not terminal`), käivita lihtsalt uuesti `azd up`. Azure ressursid võivad taustal veel juurutamisel olla ja korduskatse lubab juurutusel lõpule jõuda, kui ressursid jõuavad lõppseisundisse.

See teeb järgmist:
1. Juurutab Azure OpenAI ressursi koos GPT-5 ja text-embedding-3-small mudelitega
2. Genereerib automaatselt `.env` faili projekti juurkausta mandaadiga
3. Seadistab kõik vajalikud keskkonnamuutujad

**Probleemid juurutamisega?** Vaata [Infrastruktuuri README-d](infra/README.md) üksikasjaliku tõrkeotsingu jaoks, sealhulgas alamdomeeni nime konfliktid, käsitsi Azure Portali juurutamise sammud ja mudeli konfiguratsiooni juhised.

**Kontrolli, kas juurutamine õnnestus:**

**Bash:**
```bash
cat ../.env  # Peaks näitama AZURE_OPENAI_ENDPOINT, API_KEY jne.
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Peaks näitama AZURE_OPENAI_ENDPOINT, API_KEY jne.
```

> **Märkus:** `azd up` käsk genereerib automaatselt `.env` faili. Kui vajad hiljem selle uuendamist, võid kas muuta `.env` faili käsitsi või genereerida selle uuesti, käivitades:
>
> **Bash:**
> ```bash
> cd ..
> bash .azd-env.sh
> ```
>
> **PowerShell:**
> ```powershell
> cd ..
> .\.azd-env.ps1
> ```

## Rakenduse lokaalne käivitamine

**Kontrolli juurutust:**

Veendu, et `.env` fail on juurkaustas koos Azure mandaadiga:

**Bash:**
```bash
cat ../.env  # Peaks näitama AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Peaks näitama AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Käivita rakendused:**

**Variant 1: Spring Boot Dashboardi kasutamine (soovitatav VS Code kasutajatele)**

Dev container sisaldab Spring Boot Dashboard laiendust, mis pakub visuaalset liidest kõigi Spring Boot rakenduste haldamiseks. Leiad selle VS Code vasakpoolse tegevusriba Spring Boot ikooni alt.

Spring Boot Dashboardist saad:
- Näha kõiki tööruumis olevaid Spring Boot rakendusi
- Käivitada/peatada rakendusi ühe klikiga
- Vaadata rakenduse logisid reaalajas
- Jälgida rakenduse olekut

Lihtsalt klõpsa "introduction" kõrval olevale mängunupule, et käivitada see moodul, või käivita korraga kõik moodulid.

<img src="../../../translated_images/et/dashboard.69c7479aef09ff6b.png" alt="Spring Boot Dashboard" width="400"/>

**Variant 2: Shell skriptide kasutamine**

Käivita kõik veebirakendused (moodulid 01-04):

**Bash:**
```bash
cd ..  # Juurekataloogist
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Juurekataloogist
.\start-all.ps1
```

Või käivita ainult see moodul:

**Bash:**
```bash
cd 01-introduction
./start.sh
```

**PowerShell:**
```powershell
cd 01-introduction
.\start.ps1
```

Mõlemad skriptid laadivad automaatselt keskkonnamuutujad juurkaustas olevast `.env` failist ja ehitavad JAR-failid, kui neid veel pole.

> **Märkus:** Kui soovid enne käivitamist kõik moodulid käsitsi ehitada:
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

Ava oma brauseris http://localhost:8080.

**Peatamiseks:**

**Bash:**
```bash
./stop.sh  # Ainult see moodul
# Või
cd .. && ./stop-all.sh  # Kõik moodulid
```

**PowerShell:**
```powershell
.\stop.ps1  # Ainult see moodul
# Või
cd ..; .\stop-all.ps1  # Kõik moodulid
```

## Rakenduse kasutamine

Rakendus pakub veebiliidest kahe kõne rakendusega kõrvuti.

<img src="../../../translated_images/et/home-screen.121a03206ab910c0.png" alt="Rakenduse avaleht" width="800"/>

*Armatuurlaud, mis näitab nii lihtsat vestlust (olemitu) kui ka vestluslikku vestlust (olekuline)*

### Olemitu vestlus (vasak paneel)

Proovi esmalt seda. Küsi "Minu nimi on John" ja siis kohe "Mis mu nimi on?" Mudel ei mäleta, sest iga sõnum on iseseisev. See demonstreerib põhiprobleemi lihtsa keelemudeli integratsiooniga – puudub vestluse kontekst.

<img src="../../../translated_images/et/simple-chat-stateless-demo.13aeb3978eab3234.png" alt="Olemitu vestluse demo" width="800"/>

*Tehisintellekt ei mäleta sinu nime eelmisest sõnumist*

### Olekuline vestlus (parem paneel)

Nüüd proovi sama järjestust siin. Küsi "Minu nimi on John" ja siis "Mis mu nimi on?" Seekord mäletab. Vahe on MessageWindowChatMemory – see hoiab vestluse ajalugu ja lisab selle iga päringu juurde. Nii töötab tootmises vestluslik tehisintellekt.

<img src="../../../translated_images/et/conversational-chat-stateful-demo.e5be9822eb23ff59.png" alt="Olekuline vestluse demo" width="800"/>

*Tehisintellekt mäletab sinu nime varasemast vestlusest*

Mõlemad paneelid kasutavad sama GPT-5 mudelit. Ainuke erinevus on mälu. See teeb selgeks, mida mälu sinu rakendusele lisab ja miks see on tõeliste kasutusjuhtude jaoks hädavajalik.

## Järgmised sammud

**Järgmine moodul:** [02-prompt-engineering - Prompt Engineering GPT-5-ga](../02-prompt-engineering/README.md)

---

**Navigeerimine:** [← Eelmine: Moodul 00 - Kiire algus](../00-quick-start/README.md) | [Tagasi avalehele](../README.md) | [Järgmine: Moodul 02 - Prompt Engineering →](../02-prompt-engineering/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastutusest loobumine**:
See dokument on tõlgitud kasutades tehisintellektil põhinevat tõlketeenust [Co-op Translator](https://github.com/Azure/co-op-translator). Kuigi püüame tagada täpsust, palun arvestage, et automaatsed tõlked võivad sisaldada vigu või ebatäpsusi. Originaaldokument selle emakeeles tuleks pidada autoriteetseks allikaks. Olulise teabe puhul soovitatakse kasutada professionaalset inimtõlget. Me ei vastuta selle tõlke kasutamisest tulenevate arusaamatuste või valesti mõistmiste eest.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->