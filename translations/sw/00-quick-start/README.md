<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T15:11:04+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "sw"
}
-->
# Module 00: Kuanzia Haraka

## Jedwali la Maudhui

- [Utangulizi](../../../00-quick-start)
- [LangChain4j ni Nini?](../../../00-quick-start)
- [Mategemeo ya LangChain4j](../../../00-quick-start)
- [Mahitaji ya Awali](../../../00-quick-start)
- [Usanidi](../../../00-quick-start)
  - [1. Pata Tokeni Yako ya GitHub](../../../00-quick-start)
  - [2. Weka Tokeni Yako](../../../00-quick-start)
- [Endesha Mifano](../../../00-quick-start)
  - [1. Mazungumzo ya Msingi](../../../00-quick-start)
  - [2. Mifumo ya Maagizo](../../../00-quick-start)
  - [3. Kupiga Simu ya Kazi](../../../00-quick-start)
  - [4. Maswali na Majibu ya Nyaraka (RAG)](../../../00-quick-start)
- [Kila Mfano Unaonyesha Nini](../../../00-quick-start)
- [Hatua Zifuatazo](../../../00-quick-start)
- [Kutatua Matatizo](../../../00-quick-start)

## Utangulizi

Kuanzia haraka hii inakusudiwa kukupeleka kuanza na LangChain4j haraka iwezekanavyo. Inashughulikia misingi ya ujenzi wa programu za AI kwa kutumia LangChain4j na Modeli za GitHub. Katika moduli zinazofuata utatumia Azure OpenAI na LangChain4j kujenga programu za hali ya juu zaidi.

## LangChain4j ni Nini?

LangChain4j ni maktaba ya Java inayorahisisha ujenzi wa programu zinazotumia AI. Badala ya kushughulikia wateja wa HTTP na uchambuzi wa JSON, unafanya kazi na API safi za Java.

"Neno 'chain' katika LangChain linahusu kuunganisha vipengele vingi pamoja - unaweza kuunganisha maagizo kwa mfano, kisha kwa mchambuzi, au kuunganisha simu nyingi za AI ambapo matokeo ya moja huingia kama ingizo la nyingine. Kuanzia haraka hii inalenga misingi kabla ya kuchunguza minyororo tata zaidi.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1e961a13c73d45cfa305fd03d81bd11e5d34d0e36ed28a44d.sw.png" alt="LangChain4j Chaining Concept" width="800"/>

*Kuunganisha vipengele katika LangChain4j - vipande vya msingi vinavyoungana kuunda mtiririko wa kazi wenye nguvu wa AI*

Tutatumia vipengele vitatu vikuu:

**ChatLanguageModel** - Kiolesura cha mawasiliano na mfano wa AI. Piga simu `model.chat("prompt")` na upate jibu la maandishi. Tunatumia `OpenAiOfficialChatModel` ambayo hufanya kazi na vituo vinavyolingana na OpenAI kama Modeli za GitHub.

**AiServices** - Huunda violesura vya huduma za AI vinavyohakikisha usalama wa aina. Eleza njia, ziweke alama na `@Tool`, na LangChain4j hushughulikia upangaji. AI hupiga simu kwa njia zako za Java moja kwa moja inapohitajika.

**MessageWindowChatMemory** - Huhifadhi historia ya mazungumzo. Bila hii, kila ombi ni huru. Ukiwa nayo, AI hukumbuka ujumbe wa awali na huendeleza muktadha katika mizunguko mingi.

<img src="../../../translated_images/architecture.eedc993a1c57683951f20244f652fc7a9853f571eea835bc2b2828cf0dbf62d0.sw.png" alt="LangChain4j Architecture" width="800"/>

*Miundo ya LangChain4j - vipengele vikuu vinavyofanya kazi pamoja kuendesha programu zako za AI*

## Mategemeo ya LangChain4j

Kuanzia haraka hii inatumia utegemezi wa Maven wawili katika [`pom.xml`](../../../00-quick-start/pom.xml):

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

Moduli `langchain4j-open-ai-official` hutoa darasa la `OpenAiOfficialChatModel` linalounganisha na API zinazolingana na OpenAI. Modeli za GitHub hutumia muundo huo huo wa API, hivyo hakuna hitaji la kiambatisho maalum - tuelekeze URL msingi kwa `https://models.github.ai/inference`.

## Mahitaji ya Awali

**Unatumia Dev Container?** Java na Maven tayari vimesakinishwa. Unahitaji tu Tokeni ya Ufikiaji wa Binafsi ya GitHub.

**Maendeleo ya Kwenye Kifaa Chako:**
- Java 21+, Maven 3.9+
- Tokeni ya Ufikiaji wa Binafsi ya GitHub (maelekezo chini)

> **Kumbuka:** Moduli hii inatumia `gpt-4.1-nano` kutoka Modeli za GitHub. Usibadilishe jina la mfano katika msimbo - limewekwa kufanya kazi na modeli zinazopatikana za GitHub.

## Usanidi

### 1. Pata Tokeni Yako ya GitHub

1. Nenda [Mipangilio ya GitHub → Tokeni za Ufikiaji wa Binafsi](https://github.com/settings/personal-access-tokens)
2. Bonyeza "Generate new token"
3. Weka jina linaloelezea (mfano, "LangChain4j Demo")
4. Weka muda wa kumalizika (siku 7 zinapendekezwa)
5. Chini ya "Account permissions", tafuta "Models" na weka "Read-only"
6. Bonyeza "Generate token"
7. Nakili na hifadhi tokeni yako - hautaiona tena

### 2. Weka Tokeni Yako

**Chaguo 1: Kutumia VS Code (Inapendekezwa)**

Ikiwa unatumia VS Code, ongeza tokeni yako kwenye faili `.env` kwenye mzizi wa mradi:

Kama faili `.env` haipo, nakili `.env.example` hadi `.env` au tengeneza faili mpya `.env` kwenye mzizi wa mradi.

**Mfano wa faili `.env`:**
```bash
# Katika /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Kisha unaweza bonyeza kulia kwenye faili lolote la maonyesho (mfano, `BasicChatDemo.java`) katika Explorer na chagua **"Run Java"** au tumia usanidi wa kuanzisha kutoka kwenye paneli ya Run and Debug.

**Chaguo 2: Kutumia Terminal**

Weka tokeni kama mabadiliko ya mazingira:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Endesha Mifano

**Kutumia VS Code:** Bonyeza kulia kwenye faili lolote la maonyesho katika Explorer na chagua **"Run Java"**, au tumia usanidi wa kuanzisha kutoka kwenye paneli ya Run and Debug (hakikisha umeongeza tokeni yako kwenye faili `.env` kwanza).

**Kutumia Maven:** Vinginevyo, unaweza kuendesha kutoka mstari wa amri:

### 1. Mazungumzo ya Msingi

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Mifumo ya Maagizo

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Inaonyesha zero-shot, few-shot, chain-of-thought, na maagizo ya kulingana na nafasi.

### 3. Kupiga Simu ya Kazi

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI hupiga simu kwa njia zako za Java moja kwa moja inapohitajika.

### 4. Maswali na Majibu ya Nyaraka (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Uliza maswali kuhusu yaliyomo katika `document.txt`.

## Kila Mfano Unaonyesha Nini

**Mazungumzo ya Msingi** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Anza hapa kuona LangChain4j kwa urahisi wake. Utaunda `OpenAiOfficialChatModel`, tuma agizo kwa `.chat()`, na upate jibu. Hii inaonyesha msingi: jinsi ya kuanzisha modeli na vituo maalum na funguo za API. Ukielewa mfano huu, kila kitu kingine kinajengwa juu yake.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) na uliza:
> - "Ningebadilisha vipi kutoka Modeli za GitHub hadi Azure OpenAI katika msimbo huu?"
> - "Ni vigezo gani vingine ninavyoweza kusanidi katika OpenAiOfficialChatModel.builder()?"
> - "Ningeeongezaje majibu ya mtiririko badala ya kusubiri jibu kamili?"

**Uhandisi wa Maagizo** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Sasa unajua jinsi ya kuzungumza na mfano, tuchunguze unachomwambia. Demo hii inatumia usanidi ule ule wa mfano lakini inaonyesha mifumo minne tofauti ya maagizo. Jaribu maagizo ya zero-shot kwa maelekezo ya moja kwa moja, few-shot yanayojifunza kutoka mifano, chain-of-thought inayofichua hatua za hoja, na maagizo ya kulingana na nafasi yanayoweka muktadha. Utaona jinsi mfano ule ule unavyotoa matokeo tofauti sana kulingana na jinsi unavyowasilisha ombi lako.

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

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) na uliza:
> - "Tofauti kati ya zero-shot na few-shot ni nini, na ni lini ni sahihi kutumia kila moja?"
> - "Jinsi gani kipengele cha joto kinaathiri majibu ya mfano?"
> - "Ni mbinu gani za kuzuia mashambulizi ya sindano ya maagizo katika uzalishaji?"
> - "Ningetengenezaje vitu vya PromptTemplate vinavyoweza kutumika tena kwa mifumo ya kawaida?"

**Uunganishaji wa Zana** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Hapa ndipo LangChain4j inakuwa yenye nguvu. Utatumia `AiServices` kuunda msaidizi wa AI anayeweza kupiga simu kwa njia zako za Java. Weka tu alama za `@Tool("maelezo")` kwenye njia na LangChain4j hushughulikia mengine - AI huamua moja kwa moja lini kutumia kila zana kulingana na kile mtumiaji anachoomba. Hii inaonyesha kupiga simu ya kazi, mbinu muhimu ya kujenga AI inayoweza kuchukua hatua, siyo tu kujibu maswali.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) na uliza:
> - "Alama ya @Tool inafanya kazi vipi na LangChain4j hufanya nini nyuma ya pazia?"
> - "Je, AI inaweza kupiga simu zana nyingi mfululizo kutatua matatizo magumu?"
> - "Nini kinatokea kama zana inatoa hitilafu - ni jinsi gani nishughulikie makosa?"
> - "Ningeelekezaje API halisi badala ya mfano huu wa kalkuleta?"

**Maswali na Majibu ya Nyaraka (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Hapa utaona msingi wa RAG (uzalishaji unaoimarishwa na utafutaji). Badala ya kutegemea data ya mafunzo ya mfano, unapakia yaliyomo kutoka [`document.txt`](../../../00-quick-start/document.txt) na kuingiza kwenye agizo. AI hujibu kulingana na nyaraka zako, si maarifa yake ya jumla. Hii ni hatua ya kwanza kuelekea kujenga mifumo inayoweza kufanya kazi na data yako mwenyewe.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Kumbuka:** Njia hii rahisi inapakia nyaraka nzima ndani ya agizo. Kwa faili kubwa (>10KB), utazidi mipaka ya muktadha. Moduli 03 inashughulikia kugawanya na utafutaji wa vector kwa mifumo ya RAG ya uzalishaji.

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) na uliza:
> - "RAG inazuia vipi mawazo potofu ya AI ikilinganishwa na kutumia data ya mafunzo ya mfano?"
> - "Tofauti kati ya njia hii rahisi na kutumia vector embeddings kwa utafutaji ni ipi?"
> - "Ningeboreshaje hii kushughulikia nyaraka nyingi au misingi mikubwa ya maarifa?"
> - "Ni mbinu gani bora za kuunda agizo ili kuhakikisha AI inatumia muktadha uliotolewa tu?"

## Utatuzi wa Hitilafu

Mifano ina `.logRequests(true)` na `.logResponses(true)` kuonyesha simu za API kwenye koni. Hii husaidia kutatua makosa ya uthibitishaji, mipaka ya kiwango, au majibu yasiyotarajiwa. Ondoa bendera hizi katika uzalishaji kupunguza kelele za kumbukumbu.

## Hatua Zifuatazo

**Moduli Ifuatayo:** [01-introduction - Kuanzia na LangChain4j na gpt-5 kwenye Azure](../01-introduction/README.md)

---

**Uelekezaji:** [← Rudi Kwenye Kuu](../README.md) | [Ifuatayo: Module 01 - Utangulizi →](../01-introduction/README.md)

---

## Kutatua Matatizo

### Ujenzi wa Kwanza wa Maven

**Tatizo**: `mvn clean compile` au `mvn package` ya kwanza huchukua muda mrefu (dakika 10-15)

**Sababu**: Maven inahitaji kupakua utegemezi wote wa mradi (Spring Boot, maktaba za LangChain4j, SDK za Azure, n.k.) katika ujenzi wa kwanza.

**Suluhisho**: Hali hii ni ya kawaida. Ujenzi unaofuata utakuwa wa haraka zaidi kwa sababu utegemezi utahifadhiwa ndani. Muda wa kupakua unategemea kasi ya mtandao wako.

### Muundo wa Amri za Maven katika PowerShell

**Tatizo**: Amri za Maven zinashindwa na kosa `Unknown lifecycle phase ".mainClass=..."`

**Sababu**: PowerShell huchukulia `=` kama kiashiria cha ugawaji wa mabadiliko, na kuvunja muundo wa mali za Maven

**Suluhisho**: Tumia kiashiria cha kuacha uchambuzi `--%` kabla ya amri ya Maven:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Kiashiria `--%` kinaambia PowerShell kupitisha hoja zote zilizobaki kwa Maven moja kwa moja bila uchambuzi.

### Onyesho la Emoji katika Windows PowerShell

**Tatizo**: Majibu ya AI yanaonyesha herufi zisizoeleweka (mfano, `????` au `â??`) badala ya emoji katika PowerShell

**Sababu**: Usimbaji wa PowerShell wa chaguo-msingi hauungi mkono emoji za UTF-8

**Suluhisho**: Endesha amri hii kabla ya kuendesha programu za Java:
```cmd
chcp 65001
```

Hii inalazimisha usimbaji wa UTF-8 kwenye terminal. Vinginevyo, tumia Windows Terminal ambayo ina msaada bora wa Unicode.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Kiarifu cha Kutotegemea**:
Hati hii imetafsiriwa kwa kutumia huduma ya tafsiri ya AI [Co-op Translator](https://github.com/Azure/co-op-translator). Ingawa tunajitahidi kwa usahihi, tafadhali fahamu kwamba tafsiri za kiotomatiki zinaweza kuwa na makosa au upungufu wa usahihi. Hati ya asili katika lugha yake ya asili inapaswa kuchukuliwa kama chanzo cha mamlaka. Kwa taarifa muhimu, tafsiri ya kitaalamu ya binadamu inapendekezwa. Hatubebei dhamana kwa kutoelewana au tafsiri potofu zinazotokana na matumizi ya tafsiri hii.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->