<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-06T00:02:32+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "nl"
}
-->
# Module 03: RAG (Retrieval-Augmented Generation)

## Table of Contents

- [What You'll Learn](../../../03-rag)
- [Prerequisites](../../../03-rag)
- [Understanding RAG](../../../03-rag)
- [How It Works](../../../03-rag)
  - [Document Processing](../../../03-rag)
  - [Creating Embeddings](../../../03-rag)
  - [Semantic Search](../../../03-rag)
  - [Answer Generation](../../../03-rag)
- [Run the Application](../../../03-rag)
- [Using the Application](../../../03-rag)
  - [Upload a Document](../../../03-rag)
  - [Ask Questions](../../../03-rag)
  - [Check Source References](../../../03-rag)
  - [Experiment with Questions](../../../03-rag)
- [Key Concepts](../../../03-rag)
  - [Chunking Strategy](../../../03-rag)
  - [Similarity Scores](../../../03-rag)
  - [In-Memory Storage](../../../03-rag)
  - [Context Window Management](../../../03-rag)
- [When RAG Matters](../../../03-rag)
- [Next Steps](../../../03-rag)

## What You'll Learn

In de vorige modules leerde je hoe je gesprekken voert met AI en je prompts effectief structuurt. Maar er is een fundamentele beperking: taalmodellen weten alleen wat ze tijdens de training geleerd hebben. Ze kunnen geen vragen beantwoorden over het beleid van jouw bedrijf, je projectdocumentatie, of informatie waarop ze niet getraind zijn.

RAG (Retrieval-Augmented Generation) lost dit probleem op. In plaats van te proberen het model jouw informatie te leren (wat duur en onpraktisch is), geef je het de mogelijkheid om door jouw documenten te zoeken. Wanneer iemand een vraag stelt, vindt het systeem relevante informatie en voegt deze toe aan de prompt. Het model beantwoordt de vraag vervolgens op basis van die opgehaalde context.

Zie RAG als het geven van een referentiebibliotheek aan het model. Wanneer je een vraag stelt, doet het systeem het volgende:

1. **User Query** - Jij stelt een vraag  
2. **Embedding** - Zet je vraag om in een vector  
3. **Vector Search** - Vindt vergelijkbare documentstukken  
4. **Context Assembly** - Voegt relevante stukken toe aan de prompt  
5. **Response** - LLM genereert een antwoord op basis van de context  

Dit maakt dat de antwoorden van het model gebaseerd zijn op jouw echte data in plaats van op zijn trainingskennis of verzonnen antwoorden.

<img src="../../../translated_images/nl/rag-architecture.ccb53b71a6ce407f.png" alt="RAG Architecture" width="800"/>

*RAG workflow - van gebruikersvraag naar semantische zoekopdracht tot contextueel antwoordgeneratie*

## Prerequisites

- Module 01 voltooid (Azure OpenAI resources gedeployed)  
- `.env` bestand in de hoofdmap met Azure-gegevens (aangemaakt met `azd up` in Module 01)  

> **Opmerking:** Als je Module 01 nog niet hebt afgerond, volg dan eerst de deploy-instructies daar.

## How It Works

### Document Processing

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

Wanneer je een document uploadt, splitst het systeem dit in stukken — kleinere delen die comfortabel in het contextvenster van het model passen. Deze stukken overlappen iets, zodat je geen context mist aan de randen.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```
  
> **🤖 Probeer met [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) en vraag:  
> - "Hoe splitst LangChain4j documenten in stukken en waarom is overlapping belangrijk?"  
> - "Wat is de optimale chunksize voor verschillende documenttypen en waarom?"  
> - "Hoe ga ik om met documenten in meerdere talen of met speciale opmaak?"

### Creating Embeddings

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Elk stuk wordt omgezet in een numerieke representatie, een embedding — eigenlijk een wiskundige vingerafdruk die de betekenis van de tekst vastlegt. Vergelijkbare teksten geven vergelijkbare embeddings.

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
  
<img src="../../../translated_images/nl/vector-embeddings.2ef7bdddac79a327.png" alt="Vector Embeddings Space" width="800"/>

*Documenten voorgesteld als vectoren in embedding-ruimte - vergelijkbare inhoud clustert samen*

### Semantic Search

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Wanneer je een vraag stelt, wordt die vraag ook omgezet in een embedding. Het systeem vergelijkt de embedding van jouw vraag met die van alle documentstukken. Het vindt de stukken met de meest vergelijkbare betekenis — niet alleen op overeenkomstige zoekwoorden, maar werkelijke semantische gelijkenis.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```
  
> **🤖 Probeer met [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) en vraag:  
> - "Hoe werkt gelijkeniszoek met embeddings en wat bepaalt de score?"  
> - "Welke gelijkenisdrempel moet ik gebruiken en hoe beïnvloedt dit de resultaten?"  
> - "Hoe ga ik om met situaties waarin geen relevante documenten worden gevonden?"

### Answer Generation

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

De meest relevante stukken worden bij de prompt gevoegd voor het model. Het model leest die specifieke stukken en beantwoordt jouw vraag op basis van die informatie. Dit voorkomt hallucinaties — het model kan alleen antwoorden vanuit wat het ziet.

## Run the Application

**Controleer deployment:**

Zorg dat het `.env` bestand bestaat in de hoofdmap met Azure-credentials (aangemaakt tijdens Module 01):  
```bash
cat ../.env  # Moet AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT tonen
```
  
**Start de applicatie:**

> **Opmerking:** Als je alle applicaties al gestart hebt met `./start-all.sh` uit Module 01, draait deze module al op poort 8081. Je kunt de startcommando's hieronder dan overslaan en direct naar http://localhost:8081 gaan.

**Optie 1: Gebruik Spring Boot Dashboard (aanbevolen voor VS Code gebruikers)**

De dev container bevat de Spring Boot Dashboard extensie, die een visuele interface biedt om alle Spring Boot applicaties te beheren. Je vindt het in de Activiteitenbalk links van VS Code (zoek het Spring Boot-icoon).

Via het Spring Boot Dashboard kun je:  
- Alle beschikbare Spring Boot applicaties in de workspace zien  
- Applicaties starten/stoppen met één klik  
- Applicatielogs realtime bekijken  
- Applicatiestatus monitoren  

Klik simpelweg op de play-knop naast "rag" om deze module te starten, of start alle modules tegelijk.

<img src="../../../translated_images/nl/dashboard.fbe6e28bf4267ffe.png" alt="Spring Boot Dashboard" width="400"/>

**Optie 2: gebruik shell scripts**

Start alle webapplicaties (modules 01-04):

**Bash:**  
```bash
cd ..  # Vanaf de hoofdmap
./start-all.sh
```
  
**PowerShell:**  
```powershell
cd ..  # Vanaf hoofddirectory
.\start-all.ps1
```
  
Of start alleen deze module:

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
  
Beide scripts laden automatisch de omgevingsvariabelen uit het `.env` bestand in de hoofddirectory en bouwen de JAR’s indien deze nog niet bestaan.

> **Opmerking:** Wil je liever alle modules handmatig bouwen voordat je start:  
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
  
Open http://localhost:8081 in je browser.

**Stoppen:**

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
  
## Using the Application

De applicatie biedt een webinterface om documenten te uploaden en vragen te stellen.

<a href="images/rag-homepage.png"><img src="../../../translated_images/nl/rag-homepage.d90eb5ce1b3caa94.png" alt="RAG Application Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*De RAG applicatie-interface - upload documenten en stel vragen*

### Upload a Document

Begin met het uploaden van een document — TXT-bestanden werken het beste voor testen. In deze directory staat een `sample-document.txt` die informatie bevat over LangChain4j features, RAG-implementatie en best practices — ideaal om het systeem te testen.

Het systeem verwerkt je document, splitst het in stukken en maakt embeddings aan voor elk stuk. Dit gebeurt automatisch bij uploaden.

### Ask Questions

Stel nu specifieke vragen over de inhoud van het document. Probeer iets feitelijks dat duidelijk in het document staat. Het systeem zoekt naar relevante stukken, voegt deze toe aan de prompt en genereert een antwoord.

### Check Source References

Opvallend is dat elk antwoord bronverwijzingen bevat met gelijkenisscores. Deze scores (van 0 tot 1) geven aan hoe relevant elk stuk was voor jouw vraag. Hogere scores betekenen betere matches. Zo kun je het antwoord verifieren met het bronmateriaal.

<a href="images/rag-query-results.png"><img src="../../../translated_images/nl/rag-query-results.6d69fcec5397f355.png" alt="RAG Query Results" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Zoekresultaten met antwoord, bronverwijzingen en relevantiescores*

### Experiment with Questions

Probeer verschillende soorten vragen:  
- Specifieke feiten: "Wat is het hoofdonderwerp?"  
- Vergelijkingen: "Wat is het verschil tussen X en Y?"  
- Samenvattingen: "Vat de belangrijkste punten over Z samen"

Let op hoe de relevantiescores veranderen afhankelijk van hoe goed je vraag aansluit bij de inhoud van het document.

## Key Concepts

### Chunking Strategy

Documenten worden opgesplitst in chunks van 300 tokens met 30 tokens overlap. Deze balans zorgt ervoor dat elk stuk genoeg context heeft om betekenisvol te zijn, terwijl het klein genoeg blijft om meerdere stukken in één prompt op te nemen.

### Similarity Scores

Scores variëren van 0 tot 1:  
- 0.7-1.0: Zeer relevant, exacte match  
- 0.5-0.7: Relevant, goede context  
- Lager dan 0.5: Gefilterd, te verschillend  

Het systeem haalt alleen stukken op die boven de minimumdrempel scoren om kwaliteit te waarborgen.

### In-Memory Storage

Deze module gebruikt in-memory opslag voor eenvoud. Bij herstart van de applicatie gaan geüploade documenten verloren. Productiesystemen gebruiken persistente vector databases zoals Qdrant of Azure AI Search.

### Context Window Management

Elk model heeft een maximale contextgrootte. Je kunt niet elk stuk van een groot document opnemen. Het systeem haalt de top N meest relevante stukken op (standaard 5) om binnen de limiet te blijven en toch voldoende context te bieden voor accurate antwoorden.

## When RAG Matters

**Gebruik RAG wanneer:**  
- Je vragen beantwoordt over eigen documenten  
- Informatie vaak verandert (beleid, prijzen, specificaties)  
- Nauwkeurigheid bronvermelding vereist  
- Inhoud te groot is voor één enkele prompt  
- Je verifieerbare, grondige antwoorden nodig hebt

**Gebruik RAG niet wanneer:**  
- Vragen algemene kennis betreffen die het model al weet  
- Je realtime data nodig hebt (RAG werkt op geüploade documenten)  
- Inhoud klein genoeg is om direct in prompts te zetten

## Next Steps

**Volgende module:** [04-tools - AI Agents with Tools](../04-tools/README.md)

---

**Navigatie:** [← Vorige: Module 02 - Prompt Engineering](../02-prompt-engineering/README.md) | [Terug naar Hoofdmenu](../README.md) | [Volgende: Module 04 - Tools →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:
Dit document is vertaald met behulp van de AI-vertalingsservice [Co-op Translator](https://github.com/Azure/co-op-translator). Hoewel we streven naar nauwkeurigheid, dient u er rekening mee te houden dat automatische vertalingen fouten of onnauwkeurigheden kunnen bevatten. Het oorspronkelijke document in de oorspronkelijke taal dient als gezaghebbende bron te worden beschouwd. Voor cruciale informatie wordt professionele menselijke vertaling aanbevolen. Wij zijn niet aansprakelijk voor eventuele misverstanden of verkeerde interpretaties voortvloeiend uit het gebruik van deze vertaling.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->