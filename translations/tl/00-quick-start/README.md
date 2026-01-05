<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T15:09:27+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "tl"
}
-->
# Module 00: Quick Start

## Table of Contents

- [Introduction](../../../00-quick-start)
- [What is LangChain4j?](../../../00-quick-start)
- [LangChain4j Dependencies](../../../00-quick-start)
- [Prerequisites](../../../00-quick-start)
- [Setup](../../../00-quick-start)
  - [1. Get Your GitHub Token](../../../00-quick-start)
  - [2. Set Your Token](../../../00-quick-start)
- [Run the Examples](../../../00-quick-start)
  - [1. Basic Chat](../../../00-quick-start)
  - [2. Prompt Patterns](../../../00-quick-start)
  - [3. Function Calling](../../../00-quick-start)
  - [4. Document Q&A (RAG)](../../../00-quick-start)
- [What Each Example Shows](../../../00-quick-start)
- [Next Steps](../../../00-quick-start)
- [Troubleshooting](../../../00-quick-start)

## Introduction

Ang quickstart na ito ay nilayon upang mabilis kang makapagsimula gamit ang LangChain4j. Saklaw nito ang mga pinaka-pangunahing kaalaman sa paggawa ng mga AI application gamit ang LangChain4j at GitHub Models. Sa mga susunod na module gagamitin mo ang Azure OpenAI kasama ang LangChain4j upang makabuo ng mas advanced na mga application.

## What is LangChain4j?

Ang LangChain4j ay isang Java library na nagpapadali sa paggawa ng mga AI-powered na application. Sa halip na magtrabaho sa mga HTTP client at JSON parsing, gagamit ka ng malinis na Java APIs.

Ang "chain" sa LangChain ay tumutukoy sa pagsasama-sama ng maraming mga bahagi - maaari kang mag-chain ng prompt sa isang model papunta sa isang parser, o mag-chain ng maraming AI calls kung saan ang output ng isa ay input ng susunod. Ang quick start na ito ay nakatuon sa mga pundasyon bago tuklasin ang mas kumplikadong mga chain.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1.tl.png" alt="LangChain4j Chaining Concept" width="800"/>

*Pagsasama-sama ng mga bahagi sa LangChain4j - ang mga bloke ng gusali ay nag-uugnay upang makalikha ng makapangyarihang AI workflows*

Gagamit tayo ng tatlong pangunahing bahagi:

**ChatLanguageModel** - Ang interface para sa pakikipag-ugnayan sa AI model. Tawagin ang `model.chat("prompt")` at makakuha ng sagot na string. Ginagamit natin ang `OpenAiOfficialChatModel` na gumagana sa mga OpenAI-compatible na endpoint tulad ng GitHub Models.

**AiServices** - Lumilikha ng type-safe na AI service interfaces. Magdefine ng mga method, lagyan ng anotasyon gamit ang `@Tool`, at ang LangChain4j ang bahala sa orchestration. Awtomatikong tatawagin ng AI ang iyong mga Java method kapag kinakailangan.

**MessageWindowChatMemory** - Nagpapanatili ng kasaysayan ng pag-uusap. Kung wala ito, bawat request ay hiwalay. Sa paggamit nito, naaalala ng AI ang mga naunang mensahe at pinananatili ang konteksto sa maraming turn.

<img src="../../../translated_images/architecture.eedc993a1c576839.tl.png" alt="LangChain4j Architecture" width="800"/>

*Arkitektura ng LangChain4j - mga pangunahing bahagi na nagtutulungan upang paandarin ang iyong mga AI application*

## LangChain4j Dependencies

Ang quick start na ito ay gumagamit ng dalawang Maven dependencies sa [`pom.xml`](../../../00-quick-start/pom.xml):

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

Ang `langchain4j-open-ai-official` module ay nagbibigay ng `OpenAiOfficialChatModel` class na kumokonekta sa mga OpenAI-compatible na API. Ginagamit ng GitHub Models ang parehong format ng API, kaya hindi kailangan ng espesyal na adapter - ituro lang ang base URL sa `https://models.github.ai/inference`.

## Prerequisites

**Gumagamit ng Dev Container?** Nakainstall na ang Java at Maven. Kailangan mo lang ng GitHub Personal Access Token.

**Local Development:**
- Java 21+, Maven 3.9+
- GitHub Personal Access Token (mga tagubilin sa ibaba)

> **Tandaan:** Ginagamit ng module na ito ang `gpt-4.1-nano` mula sa GitHub Models. Huwag baguhin ang pangalan ng model sa code - naka-configure ito para gumana sa mga available na modelo ng GitHub.

## Setup

### 1. Get Your GitHub Token

1. Pumunta sa [GitHub Settings → Personal Access Tokens](https://github.com/settings/personal-access-tokens)
2. I-click ang "Generate new token"
3. Maglagay ng deskriptibong pangalan (hal., "LangChain4j Demo")
4. Itakda ang expiration (7 araw ang rekomendado)
5. Sa ilalim ng "Account permissions", hanapin ang "Models" at itakda sa "Read-only"
6. I-click ang "Generate token"
7. Kopyahin at i-save ang iyong token - hindi mo na ito makikita muli

### 2. Set Your Token

**Option 1: Using VS Code (Recommended)**

Kung gumagamit ka ng VS Code, idagdag ang iyong token sa `.env` file sa root ng proyekto:

Kung wala ang `.env` file, kopyahin ang `.env.example` papuntang `.env` o gumawa ng bagong `.env` file sa root ng proyekto.

**Halimbawa ng `.env` file:**
```bash
# Sa /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Pagkatapos ay maaari kang mag-right-click sa kahit anong demo file (hal., `BasicChatDemo.java`) sa Explorer at piliin ang **"Run Java"** o gamitin ang launch configurations mula sa Run and Debug panel.

**Option 2: Using Terminal**

Itakda ang token bilang environment variable:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Run the Examples

**Using VS Code:** Mag-right-click lang sa kahit anong demo file sa Explorer at piliin ang **"Run Java"**, o gamitin ang launch configurations mula sa Run and Debug panel (siguraduhing nailagay mo muna ang token sa `.env` file).

**Using Maven:** Bilang alternatibo, maaari kang magpatakbo mula sa command line:

### 1. Basic Chat

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Prompt Patterns

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Ipinapakita ang zero-shot, few-shot, chain-of-thought, at role-based prompting.

### 3. Function Calling

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

Awtomatikong tinatawagan ng AI ang iyong mga Java method kapag kinakailangan.

### 4. Document Q&A (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Magtanong tungkol sa nilalaman ng `document.txt`.

## What Each Example Shows

**Basic Chat** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Magsimula dito upang makita ang pinakapayak na gamit ng LangChain4j. Gagawa ka ng `OpenAiOfficialChatModel`, magpapadala ng prompt gamit ang `.chat()`, at makakakuha ng sagot. Ipinapakita nito ang pundasyon: paano i-initialize ang mga modelo gamit ang custom endpoints at API keys. Kapag naintindihan mo na ang pattern na ito, lahat ng iba pa ay nakabase dito.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Subukan gamit ang [GitHub Copilot](https://github.com/features/copilot) Chat:** Buksan ang [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) at itanong:
> - "Paano ko papalitan mula GitHub Models papuntang Azure OpenAI sa code na ito?"
> - "Anong iba pang mga parameter ang pwede kong i-configure sa OpenAiOfficialChatModel.builder()?"
> - "Paano ako magdadagdag ng streaming responses imbes na maghintay ng buong sagot?"

**Prompt Engineering** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Ngayon na alam mo na kung paano makipag-usap sa isang model, tuklasin natin kung ano ang sinasabi mo dito. Ginagamit ng demo na ito ang parehong setup ng model pero ipinapakita ang apat na iba't ibang pattern ng prompting. Subukan ang zero-shot prompts para sa direktang mga utos, few-shot prompts na natututo mula sa mga halimbawa, chain-of-thought prompts na nagpapakita ng mga hakbang ng pag-iisip, at role-based prompts na nagtatakda ng konteksto. Makikita mo kung paano nagbibigay ang parehong model ng napakaibang resulta depende sa paraan ng pag-frame ng iyong request.

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

> **🤖 Subukan gamit ang [GitHub Copilot](https://github.com/features/copilot) Chat:** Buksan ang [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) at itanong:
> - "Ano ang pagkakaiba ng zero-shot at few-shot prompting, at kailan ko dapat gamitin ang bawat isa?"
> - "Paano naaapektuhan ng temperature parameter ang mga sagot ng model?"
> - "Ano ang mga teknik para maiwasan ang prompt injection attacks sa production?"
> - "Paano ako gagawa ng reusable PromptTemplate objects para sa mga karaniwang pattern?"

**Tool Integration** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Dito nagiging makapangyarihan ang LangChain4j. Gagamitin mo ang `AiServices` upang gumawa ng AI assistant na kayang tumawag ng iyong mga Java method. Lagyan lang ng anotasyon ang mga method gamit ang `@Tool("description")` at ang LangChain4j ang bahala sa iba - awtomatikong pinipili ng AI kung kailan gagamitin ang bawat tool base sa tanong ng user. Ipinapakita nito ang function calling, isang mahalagang teknik para gumawa ng AI na kayang gumawa ng aksyon, hindi lang sumagot ng tanong.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Subukan gamit ang [GitHub Copilot](https://github.com/features/copilot) Chat:** Buksan ang [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) at itanong:
> - "Paano gumagana ang @Tool annotation at ano ang ginagawa ng LangChain4j dito sa likod?"
> - "Kaya ba ng AI na tumawag ng maraming tools sunod-sunod para lutasin ang mga komplikadong problema?"
> - "Ano ang mangyayari kung may tool na magtapon ng exception - paano ko dapat hawakan ang mga error?"
> - "Paano ko isasama ang totoong API imbes sa halimbawa ng calculator na ito?"

**Document Q&A (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Dito makikita mo ang pundasyon ng RAG (retrieval-augmented generation). Sa halip na umasa sa training data ng model, naglo-load ka ng nilalaman mula sa [`document.txt`](../../../00-quick-start/document.txt) at isinasama ito sa prompt. Sumagot ang AI base sa iyong dokumento, hindi sa pangkalahatang kaalaman nito. Ito ang unang hakbang patungo sa paggawa ng mga sistema na kayang gumana gamit ang sarili mong data.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Tandaan:** Ang simpleng paraan na ito ay niloload ang buong dokumento sa prompt. Para sa malalaking file (>10KB), lalampas ka sa context limits. Tatalakayin sa Module 03 ang chunking at vector search para sa production RAG systems.

> **🤖 Subukan gamit ang [GitHub Copilot](https://github.com/features/copilot) Chat:** Buksan ang [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) at itanong:
> - "Paano pinipigilan ng RAG ang AI hallucinations kumpara sa paggamit ng training data ng model?"
> - "Ano ang pagkakaiba ng simpleng paraan na ito at paggamit ng vector embeddings para sa retrieval?"
> - "Paano ko ito masusukat para hawakan ang maraming dokumento o mas malalaking knowledge bases?"
> - "Ano ang mga best practice sa pag-istruktura ng prompt para matiyak na gagamitin lang ng AI ang ibinigay na konteksto?"

## Debugging

Kasama sa mga halimbawa ang `.logRequests(true)` at `.logResponses(true)` upang ipakita ang mga API call sa console. Nakakatulong ito sa pag-troubleshoot ng mga authentication error, rate limits, o hindi inaasahang mga sagot. Alisin ang mga flag na ito sa production upang mabawasan ang log noise.

## Next Steps

**Next Module:** [01-introduction - Getting Started with LangChain4j and gpt-5 on Azure](../01-introduction/README.md)

---

**Navigation:** [← Back to Main](../README.md) | [Next: Module 01 - Introduction →](../01-introduction/README.md)

---

## Troubleshooting

### First-Time Maven Build

**Isyu**: Ang unang `mvn clean compile` o `mvn package` ay tumatagal ng matagal (10-15 minuto)

**Sanhi**: Kailangan i-download ng Maven lahat ng project dependencies (Spring Boot, LangChain4j libraries, Azure SDKs, atbp.) sa unang build.

**Solusyon**: Normal ito. Mas mabilis ang mga susunod na build dahil naka-cache na ang dependencies sa lokal. Depende ang oras ng pag-download sa bilis ng iyong network.

### PowerShell Maven Command Syntax

**Isyu**: Nabibigo ang Maven commands na may error na `Unknown lifecycle phase ".mainClass=..."`

**Sanhi**: Ini-interpret ng PowerShell ang `=` bilang operator ng variable assignment, kaya nasisira ang syntax ng Maven property

**Solusyon**: Gamitin ang stop-parsing operator na `--%` bago ang Maven command:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Sinasabi ng `--%` operator sa PowerShell na ipasa ang lahat ng natitirang argumento nang literal sa Maven nang walang interpretasyon.

### Windows PowerShell Emoji Display

**Isyu**: Nagpapakita ng mga kalat na karakter (hal., `????` o `â??`) sa halip na emojis ang mga sagot ng AI sa PowerShell

**Sanhi**: Hindi sinusuportahan ng default encoding ng PowerShell ang UTF-8 emojis

**Solusyon**: Patakbuhin ang command na ito bago mag-execute ng Java applications:
```cmd
chcp 65001
```

Pinipilit nito ang UTF-8 encoding sa terminal. Bilang alternatibo, gamitin ang Windows Terminal na may mas mahusay na suporta sa Unicode.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Paalala**:
Ang dokumentong ito ay isinalin gamit ang AI translation service na [Co-op Translator](https://github.com/Azure/co-op-translator). Bagamat nagsusumikap kami para sa katumpakan, pakatandaan na ang mga awtomatikong pagsasalin ay maaaring maglaman ng mga pagkakamali o di-tumpak na impormasyon. Ang orihinal na dokumento sa orihinal nitong wika ang dapat ituring na pangunahing sanggunian. Para sa mahahalagang impormasyon, inirerekomenda ang propesyonal na pagsasalin ng tao. Hindi kami mananagot sa anumang hindi pagkakaunawaan o maling interpretasyon na maaaring magmula sa paggamit ng pagsasaling ito.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->