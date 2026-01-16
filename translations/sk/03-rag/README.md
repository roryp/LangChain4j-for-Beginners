<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-06T00:57:24+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "sk"
}
-->
# Modul 03: RAG (Retrieval-Augmented Generation)

## Obsah

- [Čo sa naučíte](../../../03-rag)
- [Predpoklady](../../../03-rag)
- [Pochopenie RAG](../../../03-rag)
- [Ako to funguje](../../../03-rag)
  - [Spracovanie dokumentu](../../../03-rag)
  - [Vytváranie embeddingov](../../../03-rag)
  - [Sémantické vyhľadávanie](../../../03-rag)
  - [Generovanie odpovedí](../../../03-rag)
- [Spustenie aplikácie](../../../03-rag)
- [Používanie aplikácie](../../../03-rag)
  - [Nahranie dokumentu](../../../03-rag)
  - [Klásť otázky](../../../03-rag)
  - [Skontrolovať zdroje referencií](../../../03-rag)
  - [Experimentovať s otázkami](../../../03-rag)
- [Kľúčové koncepty](../../../03-rag)
  - [Stratégia rozdelenia (chunking)](../../../03-rag)
  - [Skóre podobnosti](../../../03-rag)
  - [Ukladanie v pamäti (in-memory storage)](../../../03-rag)
  - [Správa kontextového okna](../../../03-rag)
- [Kedy je RAG dôležité](../../../03-rag)
- [Ďalšie kroky](../../../03-rag)

## Čo sa naučíte

V predchádzajúcich moduloch ste sa naučili, ako viesť rozhovory s AI a efektívne štruktúrovať vaše prompt-y. Avšak existuje základné obmedzenie: jazykové modely poznajú len to, čo sa naučili počas tréningu. Nemôžu odpovedať na otázky týkajúce sa politiky vašej spoločnosti, vašej projektovej dokumentácie alebo akejkoľvek informácie, na ktorú neboli trénované.

RAG (Retrieval-Augmented Generation) rieši tento problém. Namiesto toho, aby ste sa model snažili naučiť vaše informácie (čo je drahé a nepraktické), dáte mu schopnosť prehľadávať vaše dokumenty. Keď niekto položí otázku, systém nájde relevantné informácie a zahrnie ich do promptu. Model potom odpovie na základe tohto vyhľadaného kontextu.

Predstavte si RAG ako poskytnutie referenčnej knižnice modelu. Keď sa spýate na otázku, systém:

1. **Používateľská otázka** - Vy položíte otázku  
2. **Embedding** - Vaša otázka sa prevedie na vektor  
3. **Vektorové vyhľadávanie** - Nájdú sa podobné kusy dokumentu  
4. **Zostavenie kontextu** - Relevantné kusy sa pridajú do promptu  
5. **Odpoveď** - LLM vygeneruje odpoveď na základe kontextu

To zakladá odpovede modelu na vašich skutočných dátach namiesto spoliehania sa na vedomosti z tréningu alebo vymýšľanie odpovedí.

<img src="../../../translated_images/sk/rag-architecture.ccb53b71a6ce407f.png" alt="RAG Architektúra" width="800"/>

*RAG workflow - od používateľskej otázky cez sémantické vyhľadávanie až po generovanie odpovedí na základe kontextu*

## Predpoklady

- Dokončený Modul 01 (nasadené Azure OpenAI prostriedky)
- Súbor `.env` v koreňovom adresári so Azure povereniami (vytvorený pomocou `azd up` v Module 01)

> **Poznámka:** Ak ste Modul 01 ešte nedokončili, najprv postupujte podľa tam uvedených pokynov na nasadenie.

## Ako to funguje

### Spracovanie dokumentu

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

Keď nahrajete dokument, systém ho rozdelí na kúsky - menšie časti, ktoré pohodlne vojdú do kontextového okna modelu. Tieto kúsky sa mierne prekrývajú, aby sa neztratil kontext na hraniciach.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```

> **🤖 Vyskúšajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorte [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) a spýtajte sa:
> - "Ako LangChain4j rozdeľuje dokumenty na kúsky a prečo je prekrývanie dôležité?"
> - "Aká je optimálna veľkosť kúskov pre rôzne typy dokumentov a prečo?"
> - "Ako spracovať dokumenty v rôznych jazykoch alebo so špeciálnym formátovaním?"

### Vytváranie embeddingov

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Každý kúsok je prevedený na číselnú reprezentáciu nazývanú embedding - v podstate matematický odtlačok, ktorý zachytáva význam textu. Podobný text vytvára podobné embeddingy.

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

<img src="../../../translated_images/sk/vector-embeddings.2ef7bdddac79a327.png" alt="Priestor vektorových embeddingov" width="800"/>

*Dokumenty reprezentované ako vektory v embeddingovom priestore - podobný obsah sa zhlukuje*

### Sémantické vyhľadávanie

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Keď položíte otázku, vaša otázka sa tiež prevedie na embedding. Systém porovná embedding vašej otázky s embeddingmi všetkých kúskov dokumentu. Nájde kúsky, ktoré majú najpodobnejší význam - nielen zhody kľúčových slov, ale skutočnú sémantickú podobnosť.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```

> **🤖 Vyskúšajte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otvorte [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) a spýtajte sa:
> - "Ako funguje vyhľadávanie podobnosti s embeddingmi a čo určuje skóre?"
> - "Akú prahovú hodnotu podobnosti by som mal použiť a ako ovplyvňuje výsledky?"
> - "Ako riešiť prípady, keď sa nenájdu žiadne relevantné dokumenty?"

### Generovanie odpovedí

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Najrelevantnejšie kúsky sú zahrnuté do promptu modelu. Model si prečíta tieto konkrétne kúsky a odpovie na vašu otázku na základe týchto informácií. Tým sa predchádza halucináciám - model môže odpovedať len z toho, čo má pred sebou.

## Spustenie aplikácie

**Overenie nasadenia:**

Uistite sa, že v koreňovom adresári existuje súbor `.env` s Azure povereniami (vytvorené počas Modulu 01):
```bash
cat ../.env  # Mali by zobraziť AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Spustenie aplikácie:**

> **Poznámka:** Ak ste už spustili všetky aplikácie pomocou `./start-all.sh` z Modulu 01, tento modul už beží na porte 8081. Môžete preskočiť spúšťacie príkazy nižšie a rovno prejsť na http://localhost:8081.

**Možnosť 1: Použitie Spring Boot Dashboard (odporúčané pre používateľov VS Code)**

Vývojársky kontajner obsahuje rozšírenie Spring Boot Dashboard, ktoré poskytuje vizuálne rozhranie na správu všetkých Spring Boot aplikácií. Nájdete ho v Activity Bar na ľavej strane VS Code (ikona Spring Boot).

Zo Spring Boot Dashboard môžete:  
- Vidieť všetky dostupné Spring Boot aplikácie v pracovnom priestore  
- Jedným kliknutím spustiť/zastaviť aplikácie  
- Sledovať denniská aplikácií v reálnom čase  
- Monitorovať stav aplikácií

Jednoducho kliknite na tlačidlo pre spustenie vedľa "rag" pre štart tohto modulu, alebo spustite všetky moduly naraz.

<img src="../../../translated_images/sk/dashboard.fbe6e28bf4267ffe.png" alt="Spring Boot Dashboard" width="400"/>

**Možnosť 2: Použitie shell skriptov**

Spustite všetky webové aplikácie (moduly 01-04):

**Bash:**
```bash
cd ..  # Z koreňového adresára
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Z koreňového adresára
.\start-all.ps1
```

Alebo spustite len tento modul:

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

Oba skripty automaticky načítajú premenné prostredia zo súboru `.env` v koreňovom adresári a postavia JAR súbory, ak ešte neexistujú.

> **Poznámka:** Ak chcete manuálne zostaviť všetky moduly pred spustením:
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

Otvorte v prehliadači http://localhost:8081.

**Na zastavenie:**

**Bash:**
```bash
./stop.sh  # Len tento modul
# Alebo
cd .. && ./stop-all.sh  # Všetky moduly
```

**PowerShell:**
```powershell
.\stop.ps1  # Len tento modul
# Alebo
cd ..; .\stop-all.ps1  # Všetky moduly
```

## Používanie aplikácie

Aplikácia poskytuje webové rozhranie na nahrávanie dokumentov a kladenie otázok.

<a href="images/rag-homepage.png"><img src="../../../translated_images/sk/rag-homepage.d90eb5ce1b3caa94.png" alt="Rozhranie aplikácie RAG" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Rozhranie aplikácie RAG - nahrávajte dokumenty a pýtajte sa otázky*

### Nahranie dokumentu

Začnite nahraním dokumentu - pre testovanie fungujú najlepšie TXT súbory. V tomto adresári je k dispozícii `sample-document.txt`, ktorý obsahuje informácie o funkciách LangChain4j, implementácii RAG a najlepších praktikách - ideálne na testovanie systému.

Systém spracuje váš dokument, rozdelí ho na kúsky a pre každý kúsok vytvorí embeddingy. Toto sa deje automaticky po nahraní.

### Klásť otázky

Teraz položte konkrétne otázky týkajúce sa obsahu dokumentu. Skúste niečo faktické, čo je jasne uvedené v dokumente. Systém vyhľadá relevantné kúsky, zahrnie ich do promptu a vygeneruje odpoveď.

### Skontrolovať zdroje referencií

Všimnite si, že každá odpoveď obsahuje referencie na zdroje so skóre podobnosti. Tieto skóre (od 0 do 1) ukazujú, ako relevantný bol každý kúsok pre vašu otázku. Vyššie skóre znamená lepšiu zhoda. To vám umožňuje overiť odpoveď voči zdrojovému materiálu.

<a href="images/rag-query-results.png"><img src="../../../translated_images/sk/rag-query-results.6d69fcec5397f355.png" alt="Výsledky dopytu RAG" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Výsledky dopytu zobrazujú odpoveď so zdrojovými referenciami a hodnoteniami relevantnosti*

### Experimentovať s otázkami

Vyskúšajte rôzne druhy otázok:  
- Špecifické fakty: "Aká je hlavná téma?"  
- Porovnania: "Aký je rozdiel medzi X a Y?"  
- Zhrnutia: "Zhrňte kľúčové body o Z"

Pozorujte, ako sa skóre relevantnosti mení v závislosti od toho, ako dobre vaša otázka zodpovedá obsahu dokumentu.

## Kľúčové koncepty

### Stratégia rozdelenia (chunking)

Dokumenty sa rozdeľujú na kúsky po 300 tokenov s 30 tokenmi prekrývania. Tento pomer zaručuje, že každý kúsok má dostatok kontextu na to, aby bol zmysluplný, a pritom sú kúsky dostatočne malé na to, aby ich bolo možné zahrnúť viaceré do promptu.

### Skóre podobnosti

Skóre sa pohybujú v rozpätí od 0 do 1:  
- 0.7-1.0: Veľmi relevantné, presná zhoda  
- 0.5-0.7: Relevantné, dobrý kontext  
- Pod 0.5: Odfiltrované, príliš odlišné

Systém načítava iba kúsky nad minimálnou prahovou hodnotou, aby zaistil kvalitu.

### Ukladanie v pamäti (in-memory storage)

Tento modul používa na jednoduchosť ukladanie v pamäti. Po reštarte aplikácie sa nahrané dokumenty stratia. Produkčné systémy používajú trvalé vektorové databázy ako Qdrant alebo Azure AI Search.

### Správa kontextového okna

Každý model má maximálnu veľkosť kontextového okna. Nemôžete zahrnúť každý kúsok z veľkého dokumentu. Systém načíta najlepších N najrelevantnejších kúskov (predvolene 5), aby zostal v limitoch a zároveň poskytol dostatočný kontext pre presné odpovede.

## Kedy je RAG dôležité

**Použite RAG keď:**  
- Odpovedáte na otázky o interných dokumentoch  
- Informácie sa často menia (politiky, ceny, špecifikácie)  
- Presnosť vyžaduje uvedenie zdroja  
- Obsah je príliš veľký na jedno prompt  
- Potrebujete overiteľné, podložené odpovede

**Nepoužívajte RAG keď:**  
- Otázky vyžadujú všeobecné znalosti, ktoré model už má  
- Potrebujete dáta v reálnom čase (RAG funguje len na nahraných dokumentoch)  
- Obsah je malý a môže byť priamo zahrnutý v promptoch

## Ďalšie kroky

**Ďalší modul:** [04-tools - AI agenti s nástrojmi](../04-tools/README.md)

---

**Navigácia:** [← Predchádzajúci: Modul 02 - Prompt Engineering](../02-prompt-engineering/README.md) | [Späť na hlavnú stránku](../README.md) | [Ďalší: Modul 04 - Nástroje →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Upozornenie**:  
Tento dokument bol preložený pomocou AI prekladateľskej služby [Co-op Translator](https://github.com/Azure/co-op-translator). Hoci sa snažíme o presnosť, prosím berte na vedomie, že automatizované preklady môžu obsahovať chyby alebo nepresnosti. Pôvodný dokument v jeho rodnom jazyku by mal byť považovaný za autoritatívny zdroj. Pre dôležité informácie sa odporúča profesionálny ľudský preklad. Nie sme zodpovední za žiadne nedorozumenia alebo nesprávne výklady vyplývajúce z použitia tohto prekladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->