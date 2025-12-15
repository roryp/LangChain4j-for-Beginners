<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T15:13:38+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "cs"
}
-->
# Modul 00: Rychlý start

## Obsah

- [Úvod](../../../00-quick-start)
- [Co je LangChain4j?](../../../00-quick-start)
- [Závislosti LangChain4j](../../../00-quick-start)
- [Požadavky](../../../00-quick-start)
- [Nastavení](../../../00-quick-start)
  - [1. Získejte svůj GitHub token](../../../00-quick-start)
  - [2. Nastavte svůj token](../../../00-quick-start)
- [Spuštění příkladů](../../../00-quick-start)
  - [1. Základní chat](../../../00-quick-start)
  - [2. Vzory promptů](../../../00-quick-start)
  - [3. Volání funkcí](../../../00-quick-start)
  - [4. Otázky a odpovědi k dokumentu (RAG)](../../../00-quick-start)
- [Co každý příklad ukazuje](../../../00-quick-start)
- [Další kroky](../../../00-quick-start)
- [Řešení problémů](../../../00-quick-start)

## Úvod

Tento rychlý start je určen k tomu, abyste co nejrychleji začali pracovat s LangChain4j. Pokrývá naprosté základy tvorby AI aplikací s LangChain4j a GitHub Models. V dalších modulech použijete Azure OpenAI s LangChain4j k vytváření pokročilejších aplikací.

## Co je LangChain4j?

LangChain4j je Java knihovna, která zjednodušuje tvorbu aplikací poháněných AI. Místo práce s HTTP klienty a parsováním JSON pracujete s čistými Java API.

„Chain“ v LangChain znamená řetězení více komponent – můžete například spojit prompt s modelem a parserem, nebo řetězit více AI volání, kde výstup jednoho je vstupem dalšího. Tento rychlý start se zaměřuje na základy před tím, než prozkoumáte složitější řetězce.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1e961a13c73d45cfa305fd03d81bd11e5d34d0e36ed28a44d.cs.png" alt="LangChain4j Chaining Concept" width="800"/>

*Řetězení komponent v LangChain4j – stavební bloky se spojují k vytvoření výkonných AI pracovních toků*

Použijeme tři základní komponenty:

**ChatLanguageModel** – rozhraní pro interakce s AI modelem. Zavoláte `model.chat("prompt")` a získáte odpověď jako řetězec. Používáme `OpenAiOfficialChatModel`, který funguje s OpenAI-kompatibilními endpointy jako GitHub Models.

**AiServices** – vytváří typově bezpečná rozhraní AI služeb. Definujete metody, označíte je anotací `@Tool` a LangChain4j se postará o orchestraci. AI automaticky volá vaše Java metody, když je to potřeba.

**MessageWindowChatMemory** – udržuje historii konverzace. Bez toho je každá žádost nezávislá. S tímto si AI pamatuje předchozí zprávy a udržuje kontext přes více kol.

<img src="../../../translated_images/architecture.eedc993a1c57683951f20244f652fc7a9853f571eea835bc2b2828cf0dbf62d0.cs.png" alt="LangChain4j Architecture" width="800"/>

*Architektura LangChain4j – základní komponenty spolupracují na pohánění vašich AI aplikací*

## Závislosti LangChain4j

Tento rychlý start používá dvě Maven závislosti v [`pom.xml`](../../../00-quick-start/pom.xml):

```xml
<!-- Core LangChain4j library -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>

<!-- OpenAI integration (works with GitHub Models) -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

Modul `langchain4j-open-ai-official` poskytuje třídu `OpenAiOfficialChatModel`, která se připojuje k OpenAI-kompatibilním API. GitHub Models používá stejný formát API, takže není potřeba žádný speciální adaptér – stačí nastavit základní URL na `https://models.github.ai/inference`.

## Požadavky

**Používáte Dev Container?** Java a Maven jsou již nainstalovány. Potřebujete pouze GitHub Personal Access Token.

**Lokální vývoj:**
- Java 21+, Maven 3.9+
- GitHub Personal Access Token (návod níže)

> **Poznámka:** Tento modul používá `gpt-4.1-nano` z GitHub Models. Neměňte název modelu v kódu – je nakonfigurován pro práci s dostupnými modely GitHubu.

## Nastavení

### 1. Získejte svůj GitHub token

1. Přejděte na [GitHub Nastavení → Personal Access Tokens](https://github.com/settings/personal-access-tokens)
2. Klikněte na „Generate new token“
3. Nastavte popisný název (např. „LangChain4j Demo“)
4. Nastavte expiraci (doporučeno 7 dní)
5. V sekci „Account permissions“ najděte „Models“ a nastavte na „Read-only“
6. Klikněte na „Generate token“
7. Zkopírujte a uložte token – už ho neuvidíte

### 2. Nastavte svůj token

**Možnost 1: Použití VS Code (doporučeno)**

Pokud používáte VS Code, přidejte svůj token do souboru `.env` v kořenovém adresáři projektu:

Pokud soubor `.env` neexistuje, zkopírujte `.env.example` do `.env` nebo vytvořte nový `.env` soubor v kořenovém adresáři.

**Příklad souboru `.env`:**
```bash
# V /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Pak můžete jednoduše kliknout pravým tlačítkem na jakýkoli demo soubor (např. `BasicChatDemo.java`) v Průzkumníku a vybrat **„Run Java“** nebo použít spouštěcí konfigurace z panelu Run and Debug.

**Možnost 2: Použití terminálu**

Nastavte token jako proměnnou prostředí:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Spuštění příkladů

**Použití VS Code:** Jednoduše klikněte pravým tlačítkem na jakýkoli demo soubor v Průzkumníku a vyberte **„Run Java“**, nebo použijte spouštěcí konfigurace z panelu Run and Debug (ujistěte se, že jste nejdříve přidali token do `.env`).

**Použití Maven:** Alternativně můžete spustit z příkazové řádky:

### 1. Základní chat

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Vzory promptů

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Ukazuje zero-shot, few-shot, chain-of-thought a role-based prompting.

### 3. Volání funkcí

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI automaticky volá vaše Java metody, když je to potřeba.

### 4. Otázky a odpovědi k dokumentu (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Ptejte se na obsah v `document.txt`.

## Co každý příklad ukazuje

**Základní chat** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Začněte zde, abyste viděli LangChain4j v jeho nejjednodušší podobě. Vytvoříte `OpenAiOfficialChatModel`, pošlete prompt pomocí `.chat()` a získáte odpověď. Ukazuje to základy: jak inicializovat modely s vlastními endpointy a API klíči. Jakmile tento vzor pochopíte, vše ostatní na něm staví.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Vyzkoušejte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otevřete [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) a zeptejte se:
> - „Jak přepnout z GitHub Models na Azure OpenAI v tomto kódu?“
> - „Jaké další parametry mohu konfigurovat v OpenAiOfficialChatModel.builder()?“
> - „Jak přidám streamování odpovědí místo čekání na kompletní odpověď?“

**Prompt Engineering** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Nyní, když víte, jak mluvit s modelem, pojďme prozkoumat, co mu říkáte. Toto demo používá stejnou konfiguraci modelu, ale ukazuje čtyři různé vzory promptů. Vyzkoušejte zero-shot prompt pro přímé instrukce, few-shot prompt, který se učí z příkladů, chain-of-thought prompt, který odhaluje kroky uvažování, a role-based prompt, který nastavuje kontext. Uvidíte, jak stejný model dává dramaticky odlišné výsledky podle toho, jak formulujete svou žádost.

```java
PromptTemplate template = PromptTemplate.from(
    "What's the best time to visit {{destination}} for {{activity}}?"
);

Prompt prompt = template.apply(Map.of(
    "destination", "Paris",
    "activity", "sightseeing"
));

String response = model.chat(prompt.text());
```

> **🤖 Vyzkoušejte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otevřete [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) a zeptejte se:
> - „Jaký je rozdíl mezi zero-shot a few-shot promptingem a kdy který použít?“
> - „Jak parametr temperature ovlivňuje odpovědi modelu?“
> - „Jaké jsou techniky pro prevenci prompt injection útoků v produkci?“
> - „Jak vytvořit znovupoužitelné objekty PromptTemplate pro běžné vzory?“

**Integrace nástrojů** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Tady LangChain4j získává sílu. Použijete `AiServices` k vytvoření AI asistenta, který může volat vaše Java metody. Stačí označit metody anotací `@Tool("popis")` a LangChain4j se postará o zbytek – AI automaticky rozhoduje, kdy který nástroj použít podle toho, co uživatel požaduje. Ukazuje to volání funkcí, klíčovou techniku pro tvorbu AI, která může vykonávat akce, nejen odpovídat na otázky.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Vyzkoušejte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otevřete [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) a zeptejte se:
> - „Jak funguje anotace @Tool a co s ní LangChain4j dělá na pozadí?“
> - „Může AI volat více nástrojů za sebou k řešení složitých problémů?“
> - „Co se stane, když nástroj vyhodí výjimku – jak mám řešit chyby?“
> - „Jak bych integroval skutečné API místo tohoto příkladu kalkulačky?“

**Otázky a odpovědi k dokumentu (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Zde uvidíte základy RAG (retrieval-augmented generation). Místo spoléhání se na tréninková data modelu načtete obsah z [`document.txt`](../../../00-quick-start/document.txt) a zahrnete ho do promptu. AI odpovídá na základě vašeho dokumentu, ne obecné znalosti. To je první krok k tvorbě systémů, které mohou pracovat s vašimi vlastními daty.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Poznámka:** Tento jednoduchý přístup načítá celý dokument do promptu. U velkých souborů (>10KB) překročíte limity kontextu. Modul 03 pokrývá dělení na části a vektorové vyhledávání pro produkční RAG systémy.

> **🤖 Vyzkoušejte s [GitHub Copilot](https://github.com/features/copilot) Chat:** Otevřete [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) a zeptejte se:
> - „Jak RAG zabraňuje halucinacím AI ve srovnání s použitím tréninkových dat modelu?“
> - „Jaký je rozdíl mezi tímto jednoduchým přístupem a použitím vektorových embeddingů pro vyhledávání?“
> - „Jak bych to škáloval pro více dokumentů nebo větší znalostní báze?“
> - „Jaké jsou nejlepší praktiky pro strukturování promptu, aby AI používala pouze poskytnutý kontext?“

## Ladění

Příklady obsahují `.logRequests(true)` a `.logResponses(true)`, aby zobrazily API volání v konzoli. To pomáhá řešit chyby autentizace, limity rychlosti nebo neočekávané odpovědi. V produkci tyto příznaky odstraňte, abyste snížili šum v logu.

## Další kroky

**Další modul:** [01-introduction - Začínáme s LangChain4j a gpt-5 na Azure](../01-introduction/README.md)

---

**Navigace:** [← Zpět na hlavní](../README.md) | [Další: Modul 01 - Úvod →](../01-introduction/README.md)

---

## Řešení problémů

### První sestavení Maven

**Problém:** První `mvn clean compile` nebo `mvn package` trvá dlouho (10-15 minut)

**Příčina:** Maven musí při prvním sestavení stáhnout všechny závislosti projektu (Spring Boot, LangChain4j knihovny, Azure SDK atd.).

**Řešení:** Toto je normální chování. Následující sestavení budou mnohem rychlejší, protože závislosti jsou uloženy v cache lokálně. Doba stahování závisí na rychlosti vaší sítě.

### Syntaxe Maven příkazů v PowerShell

**Problém:** Maven příkazy selhávají s chybou `Unknown lifecycle phase ".mainClass=..."`

**Příčina:** PowerShell interpretuje `=` jako operátor přiřazení proměnné, což narušuje syntaxi Maven vlastností.

**Řešení:** Použijte operátor zastavení parsování `--%` před Maven příkazem:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Operátor `--%` říká PowerShellu, aby všechny zbývající argumenty předal doslovně Maven bez interpretace.

### Zobrazení emoji ve Windows PowerShell

**Problém:** AI odpovědi zobrazují nesmyslné znaky (např. `????` nebo `â??`) místo emoji v PowerShellu

**Příčina:** Výchozí kódování PowerShellu nepodporuje UTF-8 emoji

**Řešení:** Spusťte tento příkaz před spuštěním Java aplikací:
```cmd
chcp 65001
```

Tím se v terminálu vynutí kódování UTF-8. Alternativně použijte Windows Terminal, který má lepší podporu Unicode.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Prohlášení o vyloučení odpovědnosti**:  
Tento dokument byl přeložen pomocí AI překladatelské služby [Co-op Translator](https://github.com/Azure/co-op-translator). Přestože usilujeme o přesnost, mějte prosím na paměti, že automatizované překlady mohou obsahovat chyby nebo nepřesnosti. Původní dokument v jeho mateřském jazyce by měl být považován za autoritativní zdroj. Pro kritické informace se doporučuje profesionální lidský překlad. Nejsme odpovědní za jakékoliv nedorozumění nebo nesprávné výklady vyplývající z použití tohoto překladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->