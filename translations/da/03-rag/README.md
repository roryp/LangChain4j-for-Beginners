<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-05T23:45:19+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "da"
}
-->
# Modul 03: RAG (Retrieval-Augmented Generation)

## Indholdsfortegnelse

- [Hvad du vil lære](../../../03-rag)
- [Forudsætninger](../../../03-rag)
- [Forståelse af RAG](../../../03-rag)
- [Sådan fungerer det](../../../03-rag)
  - [Dokumentbehandling](../../../03-rag)
  - [Oprettelse af embeddings](../../../03-rag)
  - [Semantisk søgning](../../../03-rag)
  - [Svar generering](../../../03-rag)
- [Kør applikationen](../../../03-rag)
- [Brug af applikationen](../../../03-rag)
  - [Upload et dokument](../../../03-rag)
  - [Stil spørgsmål](../../../03-rag)
  - [Tjek kildehenvisninger](../../../03-rag)
  - [Eksperimentér med spørgsmål](../../../03-rag)
- [Nøglebegreber](../../../03-rag)
  - [Chunking strategi](../../../03-rag)
  - [Lighedsscores](../../../03-rag)
  - [In-memory lagring](../../../03-rag)
  - [Håndtering af kontekstvindue](../../../03-rag)
- [Hvornår RAG er relevant](../../../03-rag)
- [Næste skridt](../../../03-rag)

## Hvad du vil lære

I de tidligere moduler har du lært, hvordan man fører samtaler med AI og strukturerer dine prompts effektivt. Men der er en grundlæggende begrænsning: sprogmodeller ved kun det, de lærte under træningen. De kan ikke besvare spørgsmål om din virksomheds politikker, din projekt-dokumentation eller nogen information, de ikke blev trænet i.

RAG (Retrieval-Augmented Generation) løser dette problem. I stedet for at forsøge at lære modellen din information (hvilket er dyrt og upraktisk), giver du den mulighed for at søge i dine dokumenter. Når nogen stiller et spørgsmål, finder systemet relevant information og inkluderer det i prompten. Modellen svarer derefter baseret på den hentede kontekst.

Tænk på RAG som at give modellen et referenceløb. Når du stiller et spørgsmål, gør systemet:

1. **Brugerforespørgsel** - Du stiller et spørgsmål  
2. **Embedding** - Konverterer dit spørgsmål til en vektor  
3. **Vektorsøgning** - Finder lignende dokument-chunks  
4. **Kontekstsammenstilling** - Tilføjer relevante chunks til prompten  
5. **Svar** - LLM genererer et svar baseret på konteksten  

Dette forankrer modellens svar i dine faktiske data i stedet for at stole på træningsviden eller at finde på svar.

<img src="../../../translated_images/da/rag-architecture.ccb53b71a6ce407f.png" alt="RAG Architecture" width="800"/>

*RAG arbejdsproces - fra brugerforespørgsel til semantisk søgning til kontekstuel svar-generering*

## Forudsætninger

- Gennemført Modul 01 (Azure OpenAI-ressourcer deployeret)  
- `.env` fil i rodmappen med Azure legitimationsoplysninger (oprettet af `azd up` i Modul 01)  

> **Note:** Hvis du ikke har gennemført Modul 01, følg først deployeringsvejledningen der.  

## Sådan fungerer det

### Dokumentbehandling

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

Når du uploader et dokument, deler systemet det op i chunks – mindre stykker, der passer komfortabelt i modellens kontekstvindue. Disse chunks overlapper lidt, så du ikke mister kontekst ved grænserne.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```
  
> **🤖 Prøv med [GitHub Copilot](https://github.com/features/copilot) Chat:** Åbn [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) og spørg:  
> - "Hvordan splitter LangChain4j dokumenter op i chunks, og hvorfor er overlap vigtigt?"  
> - "Hvad er den optimale chunk-størrelse for forskellige dokumenttyper, og hvorfor?"  
> - "Hvordan håndterer jeg dokumenter på flere sprog eller med særlig formatering?"  

### Oprettelse af embeddings

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Hver chunk konverteres til en numerisk repræsentation kaldet en embedding – i praksis et matematisk fingeraftryk, der fanger tekstens betydning. Lignende tekst giver lignende embeddings.

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
  
<img src="../../../translated_images/da/vector-embeddings.2ef7bdddac79a327.png" alt="Vector Embeddings Space" width="800"/>

*Dokumenter repræsenteret som vektorer i embeddings-rum – lignende indhold grupperes*

### Semantisk søgning

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Når du stiller et spørgsmål, bliver dit spørgsmål også til en embedding. Systemet sammenligner dit spørgsmål embedding med alle dokument-chunks embeddings. Det finder de chunks, der har den mest lignende betydning – ikke bare matchende nøgleord, men reel semantisk lighed.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```
  
> **🤖 Prøv med [GitHub Copilot](https://github.com/features/copilot) Chat:** Åbn [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) og spørg:  
> - "Hvordan virker lighedssøgning med embeddings, og hvad bestemmer scoren?"  
> - "Hvilken lighedsterskel bør jeg bruge, og hvordan påvirker det resultater?"  
> - "Hvordan håndterer jeg tilfælde, hvor der ikke findes relevante dokumenter?"  

### Svar generering

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

De mest relevante chunks inkluderes i prompten til modellen. Modellen læser disse specifikke chunks og svarer på dit spørgsmål baseret på den information. Dette forhindrer hallucination – modellen kan kun svare ud fra det, der er foran den.

## Kør applikationen

**Bekræft deployment:**

Sørg for at `.env` filen eksisterer i rodmappen med Azure-legitimationsoplysninger (oprettet under Modul 01):  
```bash
cat ../.env  # Skal vise AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```
  
**Start applikationen:**

> **Note:** Hvis du allerede har startet alle applikationer med `./start-all.sh` fra Modul 01, kører dette modul allerede på port 8081. Du kan springe startkommandoerne over nedenfor og gå direkte til http://localhost:8081.

**Valgmulighed 1: Brug Spring Boot Dashboard (anbefalet til VS Code-brugere)**

Dev containeren inkluderer Spring Boot Dashboard-udvidelsen, som giver en visuel grænseflade til at styre alle Spring Boot-applikationer. Du finder den i aktivitetslinjen til venstre i VS Code (se efter Spring Boot-ikonet).

Fra Spring Boot Dashboard kan du:  
- Se alle tilgængelige Spring Boot-applikationer i workspace  
- Starte/stoppe applikationer med et enkelt klik  
- Se applikationslogfiler i realtid  
- Overvåge applikationens status  

Klik blot på play-knappen ved siden af "rag" for at starte dette modul, eller start alle moduler på en gang.

<img src="../../../translated_images/da/dashboard.fbe6e28bf4267ffe.png" alt="Spring Boot Dashboard" width="400"/>

**Valgmulighed 2: Brug shell-scripts**

Start alle webapplikationer (moduler 01-04):

**Bash:**  
```bash
cd ..  # Fra roddirectory
./start-all.sh
```
  
**PowerShell:**  
```powershell
cd ..  # Fra rodkatalog
.\start-all.ps1
```
  
Eller start kun dette modul:

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
  
Begge scripts læser automatisk miljøvariabler fra rodfilen `.env` og bygger JAR-filerne, hvis de ikke findes.

> **Note:** Hvis du foretrækker at bygge alle moduler manuelt inden start:
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
  
Åbn http://localhost:8081 i din browser.

**For at stoppe:**

**Bash:**  
```bash
./stop.sh  # Kun denne modul
# Eller
cd .. && ./stop-all.sh  # Alle moduler
```
  
**PowerShell:**  
```powershell
.\stop.ps1  # Kun denne modul
# Eller
cd ..; .\stop-all.ps1  # Alle moduler
```


## Brug af applikationen

Applikationen tilbyder en webgrænseflade til dokumentupload og spørgsmål.

<a href="images/rag-homepage.png"><img src="../../../translated_images/da/rag-homepage.d90eb5ce1b3caa94.png" alt="RAG Application Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*RAG applikationsgrænseflade – upload dokumenter og stil spørgsmål*

### Upload et dokument

Start med at uploade et dokument – TXT-filer fungerer bedst til test. En `sample-document.txt` er tilgængelig i denne mappe, som indeholder information om LangChain4j-funktioner, RAG-implementering og best practices – perfekt til at teste systemet.

Systemet behandler dit dokument, opdeler det i chunks og opretter embeddings for hver chunk. Dette sker automatisk ved upload.

### Stil spørgsmål

Stil nu specifikke spørgsmål om dokumentets indhold. Prøv noget faktuelt, som tydeligt står i dokumentet. Systemet søger efter relevante chunks, inkluderer dem i prompten og genererer et svar.

### Tjek kildehenvisninger

Bemærk, at hvert svar indeholder kildehenvisninger med lighedsscores. Disse scores (0 til 1) viser, hvor relevant hver chunk var for dit spørgsmål. Højere scorer betyder bedre match. Det giver dig mulighed for at verificere svaret mod kildematerialet.

<a href="images/rag-query-results.png"><img src="../../../translated_images/da/rag-query-results.6d69fcec5397f355.png" alt="RAG Query Results" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Spørgeresultater der viser svar med kildehenvisninger og relevansscores*

### Eksperimentér med spørgsmål

Prøv forskellige typer spørgsmål:  
- Specifikke fakta: "Hvad er hovedemnet?"  
- Sammenligninger: "Hvad er forskellen mellem X og Y?"  
- Opsummeringer: "Opsummer nøglepunkterne om Z"  

Se hvordan relevansscorerne ændrer sig baseret på, hvor godt dit spørgsmål matcher dokumentindholdet.

## Nøglebegreber

### Chunking strategi

Dokumenter deles op i 300-token chunks med 30 tokens overlap. Denne balance sikrer, at hver chunk har nok kontekst til at være meningsfuld, samtidig med at den er lille nok til at inkludere flere chunks i en prompt.

### Lighedsscores

Scores varierer fra 0 til 1:  
- 0.7-1.0: Meget relevant, præcist match  
- 0.5-0.7: Relevant, god kontekst  
- Under 0.5: Filtreret fra, for forskellig  

Systemet henter kun chunks over minimumstersklen for at sikre kvalitet.

### In-memory lagring

Dette modul bruger in-memory lagring for enkelhedens skyld. Når du genstarter applikationen, mistes uploadede dokumenter. Produktionssystemer bruger peristente vektordatabaser som Qdrant eller Azure AI Search.

### Håndtering af kontekstvindue

Hver model har et maksimalt kontekstvindue. Du kan ikke inkludere alle chunks fra et stort dokument. Systemet henter de top N mest relevante chunks (standard 5) for at holde sig inden for grænserne, samtidig med at der gives nok kontekst til præcise svar.

## Hvornår RAG er relevant

**Brug RAG når:**  
- Du skal svare på spørgsmål om proprietære dokumenter  
- Information ændrer sig ofte (politikker, priser, specifikationer)  
- Nøjagtighed kræver kildehenvisning  
- Indholdet er for stort til at passe i én enkelt prompt  
- Du har brug for verificerbare, forankrede svar  

**Brug ikke RAG når:**  
- Spørgsmål kræver generel viden, som modellen allerede har  
- Real-time data er nødvendig (RAG arbejder på uploadede dokumenter)  
- Indholdet er lille nok til direkte inklusion i prompts  

## Næste skridt

**Næste modul:** [04-tools - AI Agenter med værktøjer](../04-tools/README.md)

---

**Navigation:** [← Forrige: Modul 02 - Prompt Engineering](../02-prompt-engineering/README.md) | [Tilbage til Hoved](../README.md) | [Næste: Modul 04 - Værktøjer →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfraskrivelse**:
Dette dokument er blevet oversat ved hjælp af AI-oversættelsestjenesten [Co-op Translator](https://github.com/Azure/co-op-translator). Selvom vi bestræber os på nøjagtighed, bedes du være opmærksom på, at automatiserede oversættelser kan indeholde fejl eller unøjagtigheder. Det originale dokument på dets oprindelige sprog bør betragtes som den autoritative kilde. For kritiske oplysninger anbefales professionel menneskelig oversættelse. Vi påtager os intet ansvar for eventuelle misforståelser eller fejltolkninger som følge af brugen af denne oversættelse.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->