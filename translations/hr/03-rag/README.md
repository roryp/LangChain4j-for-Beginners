<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-06T01:23:36+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "hr"
}
-->
# Modul 03: RAG (Retrieval-Augmented Generation)

## Sadržaj

- [Što ćete naučiti](../../../03-rag)
- [Preduvjeti](../../../03-rag)
- [Razumijevanje RAG-a](../../../03-rag)
- [Kako funkcionira](../../../03-rag)
  - [Obrada dokumenata](../../../03-rag)
  - [Stvaranje ugriza](../../../03-rag)
  - [Semantičko pretraživanje](../../../03-rag)
  - [Generiranje odgovora](../../../03-rag)
- [Pokretanje aplikacije](../../../03-rag)
- [Korištenje aplikacije](../../../03-rag)
  - [Otpremi dokument](../../../03-rag)
  - [Postavljanje pitanja](../../../03-rag)
  - [Provjera izvora](../../../03-rag)
  - [Eksperimentiranje s pitanjima](../../../03-rag)
- [Ključni pojmovi](../../../03-rag)
  - [Strategija razbijanja na dijelove](../../../03-rag)
  - [Ocjene sličnosti](../../../03-rag)
  - [Pohrana u memoriji](../../../03-rag)
  - [Upravljanje kontekstnim prozorom](../../../03-rag)
- [Kada je RAG važan](../../../03-rag)
- [Sljedeći koraci](../../../03-rag)

## Što ćete naučiti

U prethodnim modulima naučili ste kako voditi razgovore s AI-jem i kako učinkovito strukturirati svoje upite. Ali postoji temeljno ograničenje: jezični modeli znaju samo ono što su naučili tijekom treninga. Ne mogu odgovoriti na pitanja o pravilima vaše tvrtke, dokumentaciji vaših projekata ili bilo kojim informacijama na kojima nisu trenirani.

RAG (Retrieval-Augmented Generation) rješava ovaj problem. Umjesto da pokušavate model naučiti vaše informacije (što je skupo i nepraktično), dajete mu mogućnost pretraživanja vaših dokumenata. Kada netko postavi pitanje, sustav pronalazi relevantne informacije i uključuje ih u upit. Model zatim odgovara na temelju tog dohvaćenog konteksta.

Razmislite o RAG-u kao da modelu dajete referentnu knjižnicu. Kad postavite pitanje, sustav:
1. **Upit korisnika** - Postavljate pitanje  
2. **Ugriz (Embedding)** - Pretvara vaše pitanje u vektor  
3. **Pretraživanje vektora** - Pronalazi slične dijelove dokumenata  
4. **Sastavljanje konteksta** - Dodaje relevantne dijelove u upit  
5. **Odgovor** - LLM generira odgovor na temelju konteksta

Time se odgovori modela utemeljuju na vašim stvarnim podacima umjesto oslanjanja na njegovu trening znanje ili izmišljanje odgovora.

<img src="../../../translated_images/rag-architecture.ccb53b71a6ce407f.hr.png" alt="RAG Arhitektura" width="800"/>

*RAG radni tijek - od korisničkog upita do semantičkog pretraživanja do generiranja odgovora temeljenog na kontekstu*

## Preduvjeti

- Završeni Modul 01 (postavljeni Azure OpenAI resursi)  
- `.env` datoteka u korijenskom direktoriju s Azure vjerodajnicama (kreirana naredbom `azd up` u Modulu 01)

> **Napomena:** Ako niste završili Modul 01, prvo slijedite tamo navedene upute za postavljanje.

## Kako funkcionira

### Obrada dokumenata

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

Kad otpremite dokument, sustav ga razbija na dijelove – manje komade koji se udobno uklapaju u kontekstni prozor modela. Ti dijelovi se malo preklapaju kako se ne bi izgubio kontekst na granicama.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```
  
> **🤖 Isprobajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorite [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) i pitajte:  
> - "Kako LangChain4j razbija dokumente na dijelove i zašto je preklapanje važno?"  
> - "Koja je optimalna veličina dijelova za različite vrste dokumenata i zašto?"  
> - "Kako rukovati dokumentima na više jezika ili s posebnim formatiranjem?"

### Stvaranje ugriza

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Svaki dio se pretvara u numerički prikaz nazvan embedding – u osnovi matematički otisak koji hvata značenje teksta. Sličan tekst proizvodi slične embeddings.

```java
@Bean
public EmbeddingModel embeddingModel() {
    return OpenAiOfficialEmbeddingModel.builder()
        .baseUrl(azureOpenAiEndpoint)
        .apiKey(azureOpenAiKey)
        .modelName(azureEmbeddingDeploymentName)
        .build();
}

EmbeddingStore<TextSegment> embeddingStore = 
    new InMemoryEmbeddingStore<>();
```
  
<img src="../../../translated_images/vector-embeddings.2ef7bdddac79a327.hr.png" alt="Prostor vektorskih ugriza" width="800"/>

*Dokumenti prikazani kao vektori u embedding prostoru - slični sadržaji grupirani zajedno*

### Semantičko pretraživanje

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Kad postavite pitanje, vaše pitanje također postaje embedding. Sustav uspoređuje embedding vašeg pitanja sa svim embeddingima dijelova dokumenata. Pronalazi dijelove s najsličnijim značenjima – ne samo podudaranje ključnih riječi, već stvarnu semantičku sličnost.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```
  
> **🤖 Isprobajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorite [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) i pitajte:  
> - "Kako funkcionira pretraživanje po sličnosti s embeddingima i što određuje ocjenu?"  
> - "Koji prag sličnosti trebam koristiti i kako to utječe na rezultate?"  
> - "Kako riješiti situacije kad se ne pronađu relevantni dokumenti?"

### Generiranje odgovora

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Najrelevantniji dijelovi uključeni su u upit modelu. Model čita te specifične dijelove i daje odgovor na temelju tih informacija. Time se sprječava halucinacija – model može odgovarati samo na temelju onoga što mu je prikazano.

## Pokretanje aplikacije

**Provjera postavljanja:**

Provjerite postoji li `.env` datoteka u korijenskom direktoriju s Azure vjerodajnicama (kreirana tijekom Modula 01):  
```bash
cat ../.env  # Trebalo bi prikazati AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```
  
**Pokretanje aplikacije:**

> **Napomena:** Ako ste već pokrenuli sve aplikacije koristeći `./start-all.sh` iz Modula 01, ovaj modul već radi na portu 8081. Možete preskočiti naredbe za pokretanje u nastavku i odmah otvoriti http://localhost:8081.

**Opcija 1: Korištenje Spring Boot nadzorne ploče (preporučeno za korisnike VS Code-a)**

Dev container uključuje ekstenziju Spring Boot Dashboard, koja pruža vizualno sučelje za upravljanje svim Spring Boot aplikacijama. Možete ju pronaći na Activity Bar-u s lijeve strane u VS Code-u (potražite ikonu Spring Boota).

Iz Spring Boot Dashboarda možete:  
- Vidjeti sve dostupne Spring Boot aplikacije u radnom prostoru  
- Startati/zaustavljati aplikacije jednim klikom  
- Pratiti logove aplikacije u stvarnom vremenu  
- Nadzirati status aplikacije

Jednostavno kliknite na dugme za play pored "rag" da pokrenete ovaj modul, ili pokrenite sve module odjednom.

<img src="../../../translated_images/dashboard.fbe6e28bf4267ffe.hr.png" alt="Spring Boot Dashboard" width="400"/>

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
cd 03-rag
./start.sh
```
  
**PowerShell:**  
```powershell
cd 03-rag
.\start.ps1
```
  
Obje skripte automatski učitavaju varijable okruženja iz `.env` datoteke u korijenu i grade JAR datoteke ako ne postoje.

> **Napomena:** Ako želite sami ručno izgraditi sve module prije pokretanja:  
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
  
Otvorite http://localhost:8081 u pregledniku.

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

Aplikacija pruža web sučelje za otpremu dokumenata i postavljanje pitanja.

<a href="images/rag-homepage.png"><img src="../../../translated_images/rag-homepage.d90eb5ce1b3caa94.hr.png" alt="RAG aplikacijsko sučelje" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*RAG aplikacijsko sučelje - otpremite dokumente i postavljajte pitanja*

### Otpremi dokument

Započnite otpremanjem dokumenta – TXT datoteke najbolje funkcioniraju za testiranje. U ovom direktoriju nalazi se `sample-document.txt` koji sadrži informacije o značajkama LangChain4j-a, RAG implementaciji i najboljim praksama – savršen za testiranje sustava.

Sustav obrađuje vaš dokument, razbija ga na dijelove i stvara embeddings za svaki dio. To se događa automatski nakon što otpremite dokument.

### Postavljanje pitanja

Sada postavite specifična pitanja o sadržaju dokumenta. Pokušajte s nečim činjeničnim što je jasno navedeno u dokumentu. Sustav traži relevantne dijelove, uključuje ih u upit i generira odgovor.

### Provjera izvora

Primijetite da svaki odgovor uključuje izvore s ocjenama sličnosti. Te ocjene (0 do 1) pokazuju koliko je svaki dio bio relevantan za vaše pitanje. Više ocjene znače bolje podudaranje. To vam omogućuje da provjerite točnost odgovora u odnosu na izvorni materijal.

<a href="images/rag-query-results.png"><img src="../../../translated_images/rag-query-results.6d69fcec5397f355.hr.png" alt="RAG rezultati upita" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Rezultati upita pokazuju odgovor sa referencama izvora i ocjenama relevantnosti*

### Eksperimentiranje s pitanjima

Isprobajte različite vrste pitanja:  
- Specifične činjenice: "Koja je glavna tema?"  
- Usporedbe: "Koja je razlika između X i Y?"  
- Sažeci: "Sažmi ključne točke o Z"

Promatrajte kako se ocjene relevantnosti mijenjaju ovisno o tome koliko dobro vaše pitanje odgovara sadržaju dokumenta.

## Ključni pojmovi

### Strategija razbijanja na dijelove

Dokumenti se dijele na dijelove od 300 tokena s preklapanjem od 30 tokena. Ova ravnoteža osigurava da svaki dio ima dovoljno konteksta da bude smislen, a istovremeno ostaje dovoljno mali da se u upit može uključiti više dijelova.

### Ocjene sličnosti

Ocjene su u rasponu od 0 do 1:  
- 0.7-1.0: Izuzetno relevantno, točnu podudaranje  
- 0.5-0.7: Relevantno, dobar kontekst  
- Ispod 0.5: Filtrirano, previše različito

Sustav dohvaća samo dijelove iznad minimalnog praga da bi osigurao kvalitetu.

### Pohrana u memoriji

Ovaj modul koristi pohranu u memoriji radi jednostavnosti. Kada ponovno pokrenete aplikaciju, učitani dokumenti se gube. Produkcijski sustavi koriste trajne vektorske baze podataka poput Qdrant ili Azure AI Search.

### Upravljanje kontekstnim prozorom

Svaki model ima maksimalni kontekstni prozor. Ne možete uključiti svaki dijelovi velikog dokumenta. Sustav dohvaća najrelevantnijih N dijelova (zadano 5) da ostane unutar ograničenja, a istovremeno pruži dovoljan kontekst za točne odgovore.

## Kada je RAG važan

**Koristite RAG kada:**  
- Odgovarate na pitanja o zaštićenim dokumentima  
- Informacije se često mijenjaju (pravila, cijene, specifikacije)  
- Potrebna je točnost s pripisivanjem izvora  
- Sadržaj je prevelik za jedan upit  
- Trebate provjerljive, utemeljene odgovore

**Nemojte koristiti RAG kada:**  
- Pitanja zahtijevaju opće znanje koje model već ima  
- Potrebni su podaci u stvarnom vremenu (RAG radi na otpremnim dokumentima)  
- Sadržaj je dovoljno mali da se može izravno uključiti u upite

## Sljedeći koraci

**Sljedeći modul:** [04-tools - AI agenti s alatima](../04-tools/README.md)

---

**Navigacija:** [← Prethodno: Modul 02 - Prompt Engineering](../02-prompt-engineering/README.md) | [Natrag na početak](../README.md) | [Sljedeće: Modul 04 - Alati →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Odricanje od odgovornosti**:
Ovaj dokument je preveden korištenjem AI usluge prevođenja [Co-op Translator](https://github.com/Azure/co-op-translator). Iako nastojimo biti točni, imajte na umu da automatski prijevodi mogu sadržavati pogreške ili netočnosti. Izvorni dokument na izvornom jeziku treba smatrati službenim i autoritativnim izvorom. Za važne informacije preporučuje se profesionalni ljudski prijevod. Ne snosimo odgovornost za bilo kakve nesporazume ili kriva tumačenja koja proizlaze iz korištenja ovog prijevoda.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->