<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f538a51cfd13147d40d84e936a0f485c",
  "translation_date": "2025-12-13T17:04:30+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "sv"
}
-->
# Modul 03: RAG (Retrieval-Augmented Generation)

## Innehållsförteckning

- [Vad du kommer att lära dig](../../../03-rag)
- [Förkunskaper](../../../03-rag)
- [Förstå RAG](../../../03-rag)
- [Hur det fungerar](../../../03-rag)
  - [Dokumentbearbetning](../../../03-rag)
  - [Skapa inbäddningar](../../../03-rag)
  - [Semantisk sökning](../../../03-rag)
  - [Svarsgenerering](../../../03-rag)
- [Kör applikationen](../../../03-rag)
- [Använda applikationen](../../../03-rag)
  - [Ladda upp ett dokument](../../../03-rag)
  - [Ställ frågor](../../../03-rag)
  - [Kontrollera källreferenser](../../../03-rag)
  - [Experimentera med frågor](../../../03-rag)
- [Nyckelbegrepp](../../../03-rag)
  - [Chunking-strategi](../../../03-rag)
  - [Likhetspoäng](../../../03-rag)
  - [Minneslagring](../../../03-rag)
  - [Hantera kontextfönster](../../../03-rag)
- [När RAG är viktigt](../../../03-rag)
- [Nästa steg](../../../03-rag)

## Vad du kommer att lära dig

I de tidigare modulerna lärde du dig hur man har konversationer med AI och strukturerar dina prompts effektivt. Men det finns en grundläggande begränsning: språkmodeller vet bara det de lärde sig under träningen. De kan inte svara på frågor om ditt företags policyer, din projektdokumentation eller någon information de inte tränades på.

RAG (Retrieval-Augmented Generation) löser detta problem. Istället för att försöka lära modellen din information (vilket är dyrt och opraktiskt), ger du den förmågan att söka igenom dina dokument. När någon ställer en fråga hittar systemet relevant information och inkluderar den i prompten. Modellen svarar sedan baserat på den hämtade kontexten.

Tänk på RAG som att ge modellen ett referensbibliotek. När du ställer en fråga gör systemet:

1. **Användarfråga** - Du ställer en fråga  
2. **Inbäddning** - Omvandlar din fråga till en vektor  
3. **Vektorsökning** - Hittar liknande dokumentbitar  
4. **Kontextsammansättning** - Lägger till relevanta bitar i prompten  
5. **Svar** - LLM genererar ett svar baserat på kontexten  

Detta förankrar modellens svar i din faktiska data istället för att förlita sig på dess träningskunskap eller hitta på svar.

<img src="../../../translated_images/rag-architecture.ccb53b71a6ce407fa8a6394c7a747eb9ad40f6334b4c217be0439d700f22bbcc.sv.png" alt="RAG Architecture" width="800"/>

*RAG arbetsflöde - från användarfråga till semantisk sökning till kontextuell svarsgenerering*

## Förkunskaper

- Avslutad Modul 01 (Azure OpenAI-resurser distribuerade)  
- `.env`-fil i rotkatalogen med Azure-uppgifter (skapad av `azd up` i Modul 01)  

> **Notera:** Om du inte har slutfört Modul 01, följ först distributionsinstruktionerna där.

## Hur det fungerar

**Dokumentbearbetning** - [DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

När du laddar upp ett dokument delar systemet upp det i bitar – mindre delar som passar bekvämt i modellens kontextfönster. Dessa bitar överlappar något så att du inte förlorar kontext vid gränserna.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```
  
> **🤖 Prova med [GitHub Copilot](https://github.com/features/copilot) Chat:** Öppna [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) och fråga:  
> - "Hur delar LangChain4j upp dokument i bitar och varför är överlappning viktigt?"  
> - "Vad är optimal bitstorlek för olika dokumenttyper och varför?"  
> - "Hur hanterar jag dokument på flera språk eller med specialformatering?"

**Skapa inbäddningar** - [LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Varje bit omvandlas till en numerisk representation kallad en inbäddning – i princip ett matematiskt fingeravtryck som fångar textens betydelse. Liknande text ger liknande inbäddningar.

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
  
<img src="../../../translated_images/vector-embeddings.2ef7bdddac79a327ad9e3e46cde9a86f5eeefbeb3edccd387e33018c1671cecd.sv.png" alt="Vector Embeddings Space" width="800"/>

*Dokument representerade som vektorer i inbäddningsutrymme – liknande innehåll klustras tillsammans*

**Semantisk sökning** - [RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

När du ställer en fråga blir även din fråga en inbäddning. Systemet jämför din frågas inbäddning med alla dokumentbitarnas inbäddningar. Det hittar de bitar med mest liknande betydelse – inte bara matchande nyckelord, utan faktisk semantisk likhet.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```
  
> **🤖 Prova med [GitHub Copilot](https://github.com/features/copilot) Chat:** Öppna [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) och fråga:  
> - "Hur fungerar likhetssökning med inbäddningar och vad avgör poängen?"  
> - "Vilken likhetströskel bör jag använda och hur påverkar den resultaten?"  
> - "Hur hanterar jag fall där inga relevanta dokument hittas?"

**Svarsgenerering** - [RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

De mest relevanta bitarna inkluderas i prompten till modellen. Modellen läser dessa specifika bitar och svarar på din fråga baserat på den informationen. Detta förhindrar hallucination – modellen kan bara svara utifrån det som finns framför den.

## Kör applikationen

**Verifiera distribution:**

Säkerställ att `.env`-filen finns i rotkatalogen med Azure-uppgifter (skapad under Modul 01):  
```bash
cat ../.env  # Bör visa AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```
  
**Starta applikationen:**

> **Notera:** Om du redan startat alla applikationer med `./start-all.sh` från Modul 01 körs denna modul redan på port 8081. Du kan hoppa över startkommandona nedan och gå direkt till http://localhost:8081.

**Alternativ 1: Använd Spring Boot Dashboard (Rekommenderas för VS Code-användare)**

Dev-containern inkluderar Spring Boot Dashboard-tillägget, som ger ett visuellt gränssnitt för att hantera alla Spring Boot-applikationer. Du hittar det i aktivitetsfältet till vänster i VS Code (leta efter Spring Boot-ikonen).

Från Spring Boot Dashboard kan du:  
- Se alla tillgängliga Spring Boot-applikationer i arbetsytan  
- Starta/stoppa applikationer med ett klick  
- Visa applikationsloggar i realtid  
- Övervaka applikationsstatus  

Klicka helt enkelt på play-knappen bredvid "rag" för att starta denna modul, eller starta alla moduler samtidigt.

<img src="../../../translated_images/dashboard.fbe6e28bf4267ffe4f95a708ecd46e78f69fd46a562d2a766e73c98fe0f53922.sv.png" alt="Spring Boot Dashboard" width="400"/>

**Alternativ 2: Använda shell-skript**

Starta alla webbapplikationer (moduler 01-04):

**Bash:**  
```bash
cd ..  # Från rotkatalogen
./start-all.sh
```
  
**PowerShell:**  
```powershell
cd ..  # Från rotkatalogen
.\start-all.ps1
```
  
Eller starta bara denna modul:

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
  
Båda skripten laddar automatiskt miljövariabler från rotens `.env`-fil och bygger JAR-filerna om de inte finns.

> **Notera:** Om du föredrar att bygga alla moduler manuellt innan start:  
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
  
Öppna http://localhost:8081 i din webbläsare.

**För att stoppa:**

**Bash:**  
```bash
./stop.sh  # Endast denna modul
# Eller
cd .. && ./stop-all.sh  # Alla moduler
```
  
**PowerShell:**  
```powershell
.\stop.ps1  # Endast denna modul
# Eller
cd ..; .\stop-all.ps1  # Alla moduler
```
  
## Använda applikationen

Applikationen erbjuder ett webbgränssnitt för dokumentuppladdning och frågeställning.

<a href="images/rag-homepage.png"><img src="../../../translated_images/rag-homepage.d90eb5ce1b3caa94987b4fa2923d3cb884a67987cf2f994ca53756c6586a93b1.sv.png" alt="RAG Application Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*RAG-applikationens gränssnitt – ladda upp dokument och ställ frågor*

**Ladda upp ett dokument**

Börja med att ladda upp ett dokument – TXT-filer fungerar bäst för testning. En `sample-document.txt` finns i denna katalog som innehåller information om LangChain4j-funktioner, RAG-implementering och bästa praxis – perfekt för att testa systemet.

Systemet bearbetar ditt dokument, delar upp det i bitar och skapar inbäddningar för varje bit. Detta sker automatiskt när du laddar upp.

**Ställ frågor**

Ställ nu specifika frågor om dokumentinnehållet. Prova något faktabaserat som tydligt anges i dokumentet. Systemet söker efter relevanta bitar, inkluderar dem i prompten och genererar ett svar.

**Kontrollera källreferenser**

Observera att varje svar inkluderar källreferenser med likhetspoäng. Dessa poäng (0 till 1) visar hur relevant varje bit var för din fråga. Högre poäng betyder bättre träffar. Detta låter dig verifiera svaret mot källmaterialet.

<a href="images/rag-query-results.png"><img src="../../../translated_images/rag-query-results.6d69fcec5397f3558c788388bb395191616dad4c7c0417f1a68bd18590ad0a0e.sv.png" alt="RAG Query Results" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Frågeresultat som visar svar med källreferenser och relevanspoäng*

**Experimentera med frågor**

Prova olika typer av frågor:  
- Specifika fakta: "Vad är huvudämnet?"  
- Jämförelser: "Vad är skillnaden mellan X och Y?"  
- Sammanfattningar: "Sammanfatta huvudpunkterna om Z"  

Se hur relevanspoängen ändras beroende på hur väl din fråga matchar dokumentinnehållet.

## Nyckelbegrepp

**Chunking-strategi**

Dokument delas upp i 300-token bitar med 30 tokens överlappning. Denna balans säkerställer att varje bit har tillräckligt med kontext för att vara meningsfull samtidigt som den är tillräckligt liten för att flera bitar ska få plats i en prompt.

**Likhetspoäng**

Poäng sträcker sig från 0 till 1:  
- 0.7-1.0: Mycket relevant, exakt träff  
- 0.5-0.7: Relevant, bra kontext  
- Under 0.5: Filtreras bort, för olik  

Systemet hämtar endast bitar över minimigränsen för att säkerställa kvalitet.

**Minneslagring**

Denna modul använder minneslagring för enkelhetens skull. När du startar om applikationen förloras uppladdade dokument. Produktionssystem använder persistenta vektordatabaser som Qdrant eller Azure AI Search.

**Hantera kontextfönster**

Varje modell har ett maximalt kontextfönster. Du kan inte inkludera alla bitar från ett stort dokument. Systemet hämtar de topp N mest relevanta bitarna (standard 5) för att hålla sig inom gränserna samtidigt som det ger tillräckligt med kontext för korrekta svar.

## När RAG är viktigt

**Använd RAG när:**  
- Du svarar på frågor om proprietära dokument  
- Information ändras ofta (policyer, priser, specifikationer)  
- Noggrannhet kräver källhänvisning  
- Innehållet är för stort för att rymmas i en enda prompt  
- Du behöver verifierbara, förankrade svar  

**Använd inte RAG när:**  
- Frågor kräver allmän kunskap som modellen redan har  
- Realtidsdata behövs (RAG fungerar på uppladdade dokument)  
- Innehållet är tillräckligt litet för att inkluderas direkt i prompts  

## Nästa steg

**Nästa modul:** [04-tools - AI Agents med verktyg](../04-tools/README.md)

---

**Navigering:** [← Föregående: Modul 02 - Prompt Engineering](../02-prompt-engineering/README.md) | [Tillbaka till huvudmenyn](../README.md) | [Nästa: Modul 04 - Verktyg →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Ansvarsfriskrivning**:
Detta dokument har översatts med hjälp av AI-översättningstjänsten [Co-op Translator](https://github.com/Azure/co-op-translator). Även om vi strävar efter noggrannhet, vänligen observera att automatiska översättningar kan innehålla fel eller brister. Det ursprungliga dokumentet på dess modersmål bör betraktas som den auktoritativa källan. För kritisk information rekommenderas professionell mänsklig översättning. Vi ansvarar inte för några missförstånd eller feltolkningar som uppstår till följd av användningen av denna översättning.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->