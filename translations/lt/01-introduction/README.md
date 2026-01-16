<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c3e07ca58d0b8a3f47d3bf5728541e0a",
  "translation_date": "2025-12-13T14:14:31+00:00",
  "source_file": "01-introduction/README.md",
  "language_code": "lt"
}
-->
# Modulis 01: Pradžia su LangChain4j

## Turinys

- [Ką Išmoksite](../../../01-introduction)
- [Reikalavimai](../../../01-introduction)
- [Pagrindinės Problemos Supratimas](../../../01-introduction)
- [Tokenų Supratimas](../../../01-introduction)
- [Kaip Veikia Atmintis](../../../01-introduction)
- [Kaip Tai Naudoja LangChain4j](../../../01-introduction)
- [Azure OpenAI Infrastruktūros Diegimas](../../../01-introduction)
- [Programos Paleidimas Vietoje](../../../01-introduction)
- [Programos Naudojimas](../../../01-introduction)
  - [Bevaldis Pokalbis (Kairysis Skydelis)](../../../01-introduction)
  - [Valdingas Pokalbis (Dešinysis Skydelis)](../../../01-introduction)
- [Kiti Žingsniai](../../../01-introduction)

## Ką Išmoksite

Jei baigėte greitą pradžią, matėte, kaip siųsti užklausas ir gauti atsakymus. Tai yra pagrindas, bet tikroms programoms reikia daugiau. Šis modulis moko, kaip kurti pokalbių AI, kuris prisimena kontekstą ir palaiko būseną – skirtumas tarp vienkartinės demonstracijos ir gamybai paruoštos programos.

Viso šio vadovo metu naudosime Azure OpenAI GPT-5, nes jo pažangios samprotavimo galimybės aiškiau parodo skirtingų modelių elgesį. Pridėjus atmintį, skirtumas tampa akivaizdus. Tai palengvina suprasti, ką kiekviena dalis suteikia jūsų programai.

Sukursite vieną programą, kuri demonstruoja abu modelius:

**Bevaldis Pokalbis** – Kiekviena užklausa yra nepriklausoma. Modelis neprisimena ankstesnių žinučių. Tai modelis, kurį naudojote greitoje pradžioje.

**Valdingas Pokalbis** – Kiekviena užklausa apima pokalbio istoriją. Modelis palaiko kontekstą per kelis pokalbio raundus. Tai būtina gamybos programoms.

## Reikalavimai

- Azure prenumerata su Azure OpenAI prieiga
- Java 21, Maven 3.9+
- Azure CLI (https://learn.microsoft.com/en-us/cli/azure/install-azure-cli)
- Azure Developer CLI (azd) (https://learn.microsoft.com/en-us/azure/developer/azure-developer-cli/install-azd)

> **Pastaba:** Java, Maven, Azure CLI ir Azure Developer CLI (azd) yra iš anksto įdiegti pateiktame devcontainer.

> **Pastaba:** Šis modulis naudoja GPT-5 Azure OpenAI. Diegimas konfigūruojamas automatiškai per `azd up` – nekeiskite modelio pavadinimo kode.

## Pagrindinės Problemos Supratimas

Kalbos modeliai yra bevaldiški. Kiekvienas API kvietimas yra nepriklausomas. Jei pasakote „Mano vardas John“ ir tada klausi „Koks mano vardas?“, modelis neturi jokios informacijos, kad ką tik prisistatėte. Jis traktuoja kiekvieną užklausą tarsi tai būtų pirmas jūsų pokalbis.

Tai tinka paprastiems klausimams ir atsakymams, bet yra nenaudinga tikroms programoms. Klientų aptarnavimo botai turi prisiminti, ką jiems pasakėte. Asmeniniai asistentai reikalauja konteksto. Bet koks daugkartinis pokalbis reikalauja atminties.

<img src="../../../translated_images/lt/stateless-vs-stateful.cc4a4765e649c41a.png" alt="Bevaldis ir Valdingas Pokalbiai" width="800"/>

*Skirtumas tarp bevaldiškų (nepriklausomų kvietimų) ir valdingų (kontekstą atpažįstančių) pokalbių*

## Tokenų Supratimas

Prieš pradedant pokalbius svarbu suprasti tokenus – pagrindinius teksto vienetus, kuriuos apdoroja kalbos modeliai:

<img src="../../../translated_images/lt/token-explanation.c39760d8ec650181.png" alt="Tokenų Paaiškinimas" width="800"/>

*Pavyzdys, kaip tekstas suskaidomas į tokenus – „I love AI!“ tampa 4 atskiromis apdorojimo vienetais*

Tokenai yra būdas, kaip AI modeliai matuoja ir apdoroja tekstą. Žodžiai, skyryba ir net tarpai gali būti tokenai. Jūsų modelis turi ribą, kiek tokenų gali apdoroti vienu metu (400 000 GPT-5, su iki 272 000 įvesties tokenų ir 128 000 išvesties tokenų). Tokenų supratimas padeda valdyti pokalbio ilgį ir kaštus.

## Kaip Veikia Atmintis

Pokalbių atmintis sprendžia bevaldiškumo problemą palaikydama pokalbio istoriją. Prieš siunčiant užklausą modeliui, sistema prideda svarbias ankstesnes žinutes. Kai klausi „Koks mano vardas?“, sistema iš tikrųjų siunčia visą pokalbio istoriją, leidžiančią modeliui matyti, kad anksčiau pasakėte „Mano vardas John.“

LangChain4j suteikia atminties įgyvendinimus, kurie tai valdo automatiškai. Jūs pasirenkate, kiek žinučių išlaikyti, o sistema valdo konteksto langą.

<img src="../../../translated_images/lt/memory-window.bbe67f597eadabb3.png" alt="Atminties Langas" width="800"/>

*MessageWindowChatMemory palaiko slenkantį langą su naujausiomis žinutėmis, automatiškai pašalindama senas*

## Kaip Tai Naudoja LangChain4j

Šis modulis plečia greitą pradžią integruodamas Spring Boot ir pridėdamas pokalbių atmintį. Štai kaip dalys dera:

**Priklausomybės** – Pridėkite dvi LangChain4j bibliotekas:

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

**Pokalbių Modelis** – Konfigūruokite Azure OpenAI kaip Spring bean ([LangChainConfig.java](../../../01-introduction/src/main/java/com/example/langchain4j/config/LangChainConfig.java)):

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

Konstruktorius skaito kredencialus iš aplinkos kintamųjų, nustatytų `azd up`. Nustatant `baseUrl` į jūsų Azure galinį tašką, OpenAI klientas veikia su Azure OpenAI.

**Pokalbių Atmintis** – Sekite pokalbio istoriją su MessageWindowChatMemory ([ConversationService.java](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java)):

```java
ChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

memory.add(UserMessage.from("My name is John"));
memory.add(AiMessage.from("Nice to meet you, John!"));

memory.add(UserMessage.from("What's my name?"));
AiMessage aiMessage = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage);
```

Sukurkite atmintį su `withMaxMessages(10)`, kad išlaikytumėte paskutines 10 žinučių. Pridėkite vartotojo ir AI žinutes su tipizuotais įvyniojimais: `UserMessage.from(text)` ir `AiMessage.from(text)`. Gaukite istoriją su `memory.messages()` ir siųskite modeliui. Servisas saugo atskiras atminties instancijas pagal pokalbio ID, leidžiant keliems vartotojams bendrauti vienu metu.

> **🤖 Išbandykite su [GitHub Copilot](https://github.com/features/copilot) Chat:** Atidarykite [`ConversationService.java`](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java) ir paklauskite:
> - „Kaip MessageWindowChatMemory nusprendžia, kurias žinutes pašalinti, kai langas pilnas?“
> - „Ar galiu įgyvendinti pasirinktą atminties saugojimą naudojant duomenų bazę vietoje atminties?“
> - „Kaip pridėčiau santrauką, kad suspaustų seną pokalbio istoriją?“

Bevaldis pokalbių galinis taškas visiškai praleidžia atmintį – tiesiog `chatModel.chat(prompt)`, kaip greitoje pradžioje. Valdingas galinis taškas prideda žinutes į atmintį, gauna istoriją ir įtraukia tą kontekstą kiekvienai užklausai. Tas pats modelio konfigūravimas, skirtingi modeliai.

## Azure OpenAI Infrastruktūros Diegimas

**Bash:**
```bash
cd 01-introduction
azd up  # Pasirinkite prenumeratą ir vietą (rekomenduojama eastus2)
```

**PowerShell:**
```powershell
cd 01-introduction
azd up  # Pasirinkite prenumeratą ir vietą (rekomenduojama eastus2)
```

> **Pastaba:** Jei susiduriate su laiko viršijimo klaida (`RequestConflict: Cannot modify resource ... provisioning state is not terminal`), tiesiog paleiskite `azd up` dar kartą. Azure ištekliai gali dar būti diegiami fone, o pakartotinis bandymas leidžia diegimui užbaigti, kai ištekliai pasiekia galutinę būseną.

Tai atliks:
1. Diegs Azure OpenAI išteklius su GPT-5 ir text-embedding-3-small modeliais
2. Automatiškai sugeneruos `.env` failą projekto šaknyje su kredencialais
3. Nustatys visus reikalingus aplinkos kintamuosius

**Turite diegimo problemų?** Peržiūrėkite [Infrastruktūros README](infra/README.md) su išsamia trikčių šalinimo informacija, įskaitant subdomenų pavadinimų konfliktus, rankinius Azure Portal diegimo žingsnius ir modelio konfigūracijos rekomendacijas.

**Patikrinkite, ar diegimas pavyko:**

**Bash:**
```bash
cat ../.env  # Turėtų rodyti AZURE_OPENAI_ENDPOINT, API_KEY ir kt.
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Turėtų rodyti AZURE_OPENAI_ENDPOINT, API_KEY ir kt.
```

> **Pastaba:** Komanda `azd up` automatiškai generuoja `.env` failą. Jei vėliau reikia jį atnaujinti, galite redaguoti `.env` failą rankiniu būdu arba sugeneruoti iš naujo paleisdami:
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

## Programos Paleidimas Vietoje

**Patikrinkite diegimą:**

Įsitikinkite, kad `.env` failas yra šakniniame kataloge su Azure kredencialais:

**Bash:**
```bash
cat ../.env  # Turėtų rodyti AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Turėtų rodyti AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Paleiskite programas:**

**1 variantas: Naudojant Spring Boot Dashboard (Rekomenduojama VS Code naudotojams)**

Dev konteineryje yra Spring Boot Dashboard plėtinys, kuris suteikia vizualią sąsają valdyti visas Spring Boot programas. Jį rasite veiklos juostoje kairėje VS Code pusėje (ieškokite Spring Boot ikonos).

Iš Spring Boot Dashboard galite:
- Matyti visas prieinamas Spring Boot programas darbo aplinkoje
- Vienu paspaudimu paleisti/stabdyti programas
- Realizuoti programų žurnalų peržiūrą
- Stebėti programų būseną

Tiesiog spustelėkite paleidimo mygtuką šalia „introduction“, kad pradėtumėte šį modulį, arba paleiskite visus modulius vienu metu.

<img src="../../../translated_images/lt/dashboard.69c7479aef09ff6b.png" alt="Spring Boot Dashboard" width="400"/>

**2 variantas: Naudojant shell skriptus**

Paleiskite visas žiniatinklio programas (moduliai 01-04):

**Bash:**
```bash
cd ..  # Iš šakninių katalogų
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Iš šakninių katalogų
.\start-all.ps1
```

Arba paleiskite tik šį modulį:

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

Abu skriptai automatiškai įkelia aplinkos kintamuosius iš šakniniame kataloge esančio `.env` failo ir sukurs JAR failus, jei jų nėra.

> **Pastaba:** Jei norite rankiniu būdu sukompiliuoti visus modulius prieš paleidimą:
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

Atidarykite http://localhost:8080 savo naršyklėje.

**Norėdami sustabdyti:**

**Bash:**
```bash
./stop.sh  # Tik šis modulis
# Arba
cd .. && ./stop-all.sh  # Visi moduliai
```

**PowerShell:**
```powershell
.\stop.ps1  # Tik šis modulis
# Arba
cd ..; .\stop-all.ps1  # Visi moduliai
```

## Programos Naudojimas

Programa suteikia žiniatinklio sąsają su dviem pokalbių įgyvendinimais šalia vienas kito.

<img src="../../../translated_images/lt/home-screen.121a03206ab910c0.png" alt="Programos Pradžios Ekranas" width="800"/>

*Valdymo skydelis, rodantis tiek Paprastą Pokalbį (bevaldis), tiek Pokalbių Pokalbį (valdingas)*

### Bevaldis Pokalbis (Kairysis Skydelis)

Išbandykite pirmiausia. Paklauskite „Mano vardas John“ ir iš karto po to „Koks mano vardas?“ Modelis neprisimins, nes kiekviena žinutė yra nepriklausoma. Tai demonstruoja pagrindinę problemą su paprasta kalbos modelio integracija – nėra pokalbio konteksto.

<img src="../../../translated_images/lt/simple-chat-stateless-demo.13aeb3978eab3234.png" alt="Bevaldis Pokalbio Demonstracija" width="800"/>

*AI neprisimena jūsų vardo iš ankstesnės žinutės*

### Valdingas Pokalbis (Dešinysis Skydelis)

Dabar išbandykite tą patį seką čia. Paklauskite „Mano vardas John“ ir tada „Koks mano vardas?“ Šį kartą jis prisimena. Skirtumas yra MessageWindowChatMemory – jis palaiko pokalbio istoriją ir įtraukia ją į kiekvieną užklausą. Taip veikia gamybos pokalbių AI.

<img src="../../../translated_images/lt/conversational-chat-stateful-demo.e5be9822eb23ff59.png" alt="Valdingas Pokalbio Demonstracija" width="800"/>

*AI prisimena jūsų vardą iš ankstesnio pokalbio*

Abu skydeliai naudoja tą patį GPT-5 modelį. Vienintelis skirtumas yra atmintis. Tai aiškiai parodo, ką atmintis suteikia jūsų programai ir kodėl ji būtina tikriems naudojimo atvejams.

## Kiti Žingsniai

**Kitas Modulis:** [02-prompt-engineering - Užklausų Kūrimas su GPT-5](../02-prompt-engineering/README.md)

---

**Navigacija:** [← Ankstesnis: Modulis 00 - Greita Pradžia](../00-quick-start/README.md) | [Atgal į Pagrindinį](../README.md) | [Kitas: Modulis 02 - Užklausų Kūrimas →](../02-prompt-engineering/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Atsakomybės apribojimas**:  
Šis dokumentas buvo išverstas naudojant dirbtinio intelekto vertimo paslaugą [Co-op Translator](https://github.com/Azure/co-op-translator). Nors stengiamės užtikrinti tikslumą, prašome atkreipti dėmesį, kad automatiniai vertimai gali turėti klaidų ar netikslumų. Originalus dokumentas jo gimtąja kalba turėtų būti laikomas autoritetingu šaltiniu. Svarbiai informacijai rekomenduojamas profesionalus žmogaus vertimas. Mes neatsakome už bet kokius nesusipratimus ar neteisingus aiškinimus, kylančius dėl šio vertimo naudojimo.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->