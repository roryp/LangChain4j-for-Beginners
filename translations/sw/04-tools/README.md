<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-06T00:39:59+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "sw"
}
-->
# Moduli 04: Maajenti wa AI Wenye Vifaa

## Jedwali la Yaliyomo

- [Utajifunza Nini](../../../04-tools)
- [Mahitaji](../../../04-tools)
- [Kuelewa Maajenti wa AI Wenye Vifaa](../../../04-tools)
- [Jinsi Kuita Vifaa Kunavyofanya Kazi](../../../04-tools)
  - [Maelezo ya Vifaa](../../../04-tools)
  - [Uamuzi](../../../04-tools)
  - [Utekelezaji](../../../04-tools)
  - [Uundaji wa Majibu](../../../04-tools)
- [Kuingiza Vifaa Mfululizo](../../../04-tools)
- [Endesha Programu](../../../04-tools)
- [Kutumia Programu](../../../04-tools)
  - [Jaribu Matumizi Rahisi ya Vifaa](../../../04-tools)
  - [Jaribu Kuingiza Vifaa Mfululizo](../../../04-tools)
  - [Tazama Mtiririko wa Mazungumzo](../../../04-tools)
  - [Fanya majaribio na Omakala Tofauti](../../../04-tools)
- [Mafunzo Muhimu](../../../04-tools)
  - [Mfumo wa ReAct (Kufikiri na Kutenda)](../../../04-tools)
  - [Maelezo ya Vifaa ni Muhimu](../../../04-tools)
  - [Usimamizi wa Kikao](../../../04-tools)
  - [Matatizo na Kukabiliana Nao](../../../04-tools)
- [Vifaa Vilivyopatikana](../../../04-tools)
- [Wakati wa Kutumia Maajenti Wenye Vifaa](../../../04-tools)
- [Hatua Zifuatazo](../../../04-tools)

## What You'll Learn

Hadi sasa, umejifunza jinsi ya kuzungumza na AI, kupanga maelekezo kwa ufanisi, na kuimarisha majibu yako kwa nyaraka zako. Lakini bado kuna ukomo wa msingi: mifano ya lugha inaweza kutoa maandishi tu. Haiwezi kuangalia hali ya hewa, kufanya mahesabu, kuuliza katika hifadhidata, au kuingiliana na mifumo ya nje.

Vifaa vinabadilisha hili. Kwa kumpa mfano uwezo wa kutumia kazi anazoweza kuita, unabadilisha mfano kutoka kuwa mtengenezaji wa maandishi kuwa ajenti anayeweza kuchukua hatua. Mfano huamua linapohitajika kifaa, ni kifaa gani kutumia, na ni parameta gani kupitishwa. Kifungu chako hutekeleza kazi hiyo na kurudisha matokeo. Mfano huunganisha matokeo hayo kwenye jibu lake.

## Prerequisites

- Umemaliza Moduli 01 (Rasilimali za Azure OpenAI zimewekewa)
- Faili `.env` katika saraka kuu lenye taarifa za Azure (limeundwa na `azd up` katika Moduli 01)

> **Kumbuka:** Ikiwa bado hujakamilisha Moduli 01, fuata maelekezo ya utekelezaji hapo kwanza.

## Understanding AI Agents with Tools

> **📝 Kumbuka:** Neno "maajenti" kwenye moduli hii linarejelea wasaidizi wa AI walioimarishwa kwa uwezo wa kuita vifaa. Hii ni tofauti na mifumo ya **Agentic AI** (maajenti huru wenye upangaji, kumbukumbu, na kufikiri kwa hatua nyingi) tutakazojifunza katika [Moduli 05: MCP](../05-mcp/README.md).

Ajenti wa AI mwenye vifaa hufuata mfano wa kufikiri na kutenda (ReAct):

1. Mtumiaji anauliza swali
2. Ajenti hufikiri kile anachohitaji kujua
3. Ajenti huamua kama anahitaji kifaa kujibu
4. Ikiwa ndiyo, ajenti huita kifaa kinachofaa na parameta sahihi
5. Kifaa hutekeleza na kurudisha data
6. Ajenti huingiza matokeo na kutoa jibu la mwisho

<img src="../../../translated_images/sw/react-pattern.86aafd3796f3fd13.png" alt="Mfumo wa ReAct" width="800"/>

*Mfumo wa ReAct - jinsi maajenti wa AI hubadilishana kati ya kufikiri na kutenda kutatua matatizo*

Hii hufanyika moja kwa moja. Wewe unaelekeza vifaa na maelezo yao. Mfano hushughulikia maamuzi kuhusu lini na jinsi ya kuvitumia.

## How Tool Calling Works

### Tool Definitions

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Unaeleza kazi na maelezo wazi na vipimo vya parameta. Mfano unaona maelezo haya katika kichocheo cha mfumo wake na kuelewa kazi ya kila kifaa.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Mantiki yako ya kutafuta hali ya hewa
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Msaidizi huunganishwa moja kwa moja na Spring Boot na:
// - Kijani cha ChatModel
// - Mbinu zote za @Tool kutoka kwa madarasa ya @Component
// - ChatMemoryProvider kwa usimamizi wa kikao
```

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) na uliza:
> - "Ningeingiza aje API halisi ya hali ya hewa kama OpenWeatherMap badala ya data bandia?"
> - "Nini kinachofanya maelezo ya kifaa kuwa mazuri na kusaidia AI kuitumia vizuri?"
> - "Ninawezaje kushughulikia makosa ya API na mipaka ya viwango katika utekelezaji wa vifaa?"

### Decision Making

Mtumiaji anapouliza "Hali ya hewa ikoje Seattle?", mfano hutambua kwamba anahitaji kifaa cha hali ya hewa. Hutoa wito wa kazi na parameta ya eneo imewekwa kuwa "Seattle".

### Execution

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot huhusisha kiotomatiki interface ya kwa undani `@AiService` na vifaa vyote vilivyojiandikisha, na LangChain4j hufanya wito wa vifaa moja kwa moja.

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) na uliza:
> - "Mfumo wa ReAct hufanya kazi vipi na kwa nini ni madhubuti kwa maajenti wa AI?"
> - "Ajenti huamua aje kifaa gani kutumia na kwa mpangilio gani?"
> - "Nini hutokea ikiwa utekelezaji wa kifaa unashindwa - nawezaje kushughulikia makosa kwa usahihi?"

### Response Generation

Mfano hupokea data za hali ya hewa na kuzipanga kuwa jibu la lugha ya kawaida kwa mtumiaji.

### Why Use Declarative AI Services?

Moduli hii inatumia LangChain4j kuunganishwa na Spring Boot na interfaces za `@AiService` kwa undani:

- **Spring Boot kuunganisha kiotomatiki** - ChatModel na vifaa huingizwa moja kwa moja
- **Mfumo wa @MemoryId** - Usimamizi wa kumbukumbu wa kikao kiotomatiki
- **Kimoja tu cha mfano** - Msaidizi huundwa mara moja na kutumika tena kwa ufanisi bora
- **Utekelezaji salama kwa aina** - Njia za Java huitwa moja kwa moja na uongozaji wa aina
- **Uendeshaji wa hatua nyingi** - Hushughulikia kuingiza vifaa mfululizo moja kwa moja
- **Hakuna coding ya ziada** - Hakuna wito wa mkono wa AiServices.builder() au HashMap ya kumbukumbu

Njia mbadala (mkono `AiServices.builder()`) zinahitaji zaidi ya kode na hazina faida za kuunganishwa na Spring Boot.

## Tool Chaining

**Kuingiza Vifaa Mfululizo** - AI inaweza kuita vifaa vingi mfululizo. Uliza "Hali ya hewa ikoje Seattle na je, niipige jeusi?" na uone inavyounganisha `getCurrentWeather` na kufikiri juu ya mavazi ya mvua.

<a href="images/tool-chaining.png"><img src="../../../translated_images/sw/tool-chaining.3b25af01967d6f7b.png" alt="Kuingiza Vifaa Mfululizo" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Wito mfululizo wa vifaa - matokeo ya kifaa kimoja huungwa mkono kwa uamuzi unaofuata*

**Kushindwa Butu** - Uliza hali ya hewa katika mji usio katika data bandia. Kifaa hurudisha ujumbe wa kosa, na AI huweza kueleza hawezi kusaidia. Vifaa hushindwa kwa usalama.

Hii hufanyika kwa zamu moja ya mazungumzo. Ajenti huendesha wito wa vifaa vingi kiotomatiki.

## Run the Application

**Thibitisha uwekaji:**

Hakikisha faili `.env` ipo katika saraka kuu lenye taarifa za Azure (limeundwa wakati wa Moduli 01):
```bash
cat ../.env  # Inapaswa kuonyesha AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Anza programu:**

> **Kumbuka:** Ikiwa tayari umeanzisha programu zote kwa kutumia `./start-all.sh` kutoka Moduli 01, moduli hii tayari inaendeshwa kwenye bandari 8084. Unaweza ruka amri za kuanzisha hapa chini na kwenda moja kwa moja http://localhost:8084.

**Chaguo 1: Kutumia Dashibodi ya Spring Boot (Inapendekezwa kwa watumiaji wa VS Code)**

Konteina la maendeleo lina nyongeza ya Dashibodi ya Spring Boot, inayotoa kiolesura cha kuona na kusimamia programu zote za Spring Boot. Unaweza kuiweka kwenye Mkahawa wa Shughuli upande wa kushoto wa VS Code (tafuta ikoni ya Spring Boot).

Kutoka Dashibodi ya Spring Boot, unaweza:
- Kuona programu zote za Spring Boot zinazopatikana kwenye eneo la kazi
- Kuanza/kusimamisha programu kwa kubofya kifungo kimoja
- Kutazama kumbukumbu za programu kwa wakati halisi
- Kufuatilia hali ya programu

Bonyeza tu kitufe cha kuanzisha kando na "tools" kuanzisha moduli hii, au anzisha moduli zote mara moja.

<img src="../../../translated_images/sw/dashboard.9b519b1a1bc1b30a.png" alt="Dashibodi ya Spring Boot" width="400"/>

**Chaguo 2: Kutumia skiripti za shell**

Anzisha programu zote za wavuti (moduli 01-04):

**Bash:**
```bash
cd ..  # Kutoka kwa saraka ya mzizi
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Kutoka kwenye saraka ya mzizi
.\start-all.ps1
```

Au anzisha moduli hii pekee:

**Bash:**
```bash
cd 04-tools
./start.sh
```

**PowerShell:**
```powershell
cd 04-tools
.\start.ps1
```

Skiripti zote hujaza kiotomatiki mabadiliko ya mazingira kutoka faili `.env` ya saraka kuu na zitaunda JAR ikiwa hazipo.

> **Kumbuka:** Ikiwa unataka kujenga moduli zote moja kwa moja kabla ya kuanzisha:
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

Fungua http://localhost:8084 kwenye kivinjari chako.

**Kusitisha:**

**Bash:**
```bash
./stop.sh  # Hii moduli tu
# Au
cd .. && ./stop-all.sh  # Moduli zote
```

**PowerShell:**
```powershell
.\stop.ps1  # Moduli hii tu
# Au
cd ..; .\stop-all.ps1  # Moduli zote
```

## Using the Application

Programu hutoa kiolesura cha wavuti ambapo unaweza kuingiliana na ajenti wa AI mwenye upatikanaji wa vifaa vya hali ya hewa na kubadilisha joto.

<a href="images/tools-homepage.png"><img src="../../../translated_images/sw/tools-homepage.4b4cd8b2717f9621.png" alt="Kiolesura cha Vifaa vya Maajenti wa AI" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Kiolesura cha Vifaa vya Maajenti wa AI - mifano ya haraka na mazungumzo ya kuingiliana na vifaa*

### Try Simple Tool Usage

Anza na ombi rahisi: "Badilisha 100 digrii Fahrenheit kuwa Celsius". Ajenti anatambua anahitaji kifaa cha kubadilisha joto, anakiita kwa parameta sahihi, na kurudisha matokeo. Tambua jinsi hii inavyoonekana ya asili – hukufafanua kifaa gani kutumia wala jinsi ya kukiita.

### Test Tool Chaining

Sasa jaribu jambo tata zaidi: "Hali ya hewa ikoje Seattle na ibadilishe kuwa Fahrenheit?" Tazama ajenti anavyofanya hatua kwa hatua. Anaanza kwa kupata hali ya hewa (inayorudisha Celsius), anatambua anahitaji kubadilisha kuwa Fahrenheit, anaita kifaa cha kubadilisha joto, na kuunganisha matokeo yote katika jibu moja.

### See Conversation Flow

Kiolesura cha mazungumzo kinafuatilia historia ya mazungumzo, kikiruhusu maingiliano ya hatua nyingi. Unaweza kuona maswali yote yaliyopita na majibu, kuifanya iwe rahisi kufuatilia mazungumzo na kuelewa jinsi ajenti anavyojenga muktadha kwa mabadilishano mengi.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/sw/tools-conversation-demo.89f2ce9676080f59.png" alt="Mazungumzo na Wito wa Vifaa Vyenye Mbalimbali" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Mazungumzo yenye hatua nyingi yakiwa na uboreshaji rahisi wa kubadilisha joto, utaftaji wa hali ya hewa, na kuingiza vifaa mfululizo*

### Experiment with Different Requests

Jaribu mchanganyiko mbalimbali:
- Tafuta hali ya hewa: "Hali ya hewa ikoje Tokyo?"
- Kubadilisha joto: "25°C ni kiasi gani katika Kelvin?"
- Maswali yaliyounganishwa: "Angalia hali ya hewa Paris na niambie kama ni zaidi ya 20°C"

Tazama jinsi ajenti anavyotafsiri lugha ya asili na kuiweka kwenye wito sahihi wa kifaa.

## Key Concepts

### ReAct Pattern (Reasoning and Acting)

Ajenti hubadilishana kati ya kufikiri (kuamua cha kufanya) na kutenda (kutumia vifaa). Mfumo huu unawawezesha kutatua matatizo kwa uhuru badala ya kujibu maagizo tu.

### Tool Descriptions Matter

Ubora wa maelezo ya vifaa vyako unaathiri moja kwa moja jinsi ajenti anavyovikitumia. Maelezo ya wazi na yanayobainisha husaidia mfano kuelewa lini na jinsi ya kuita kifaa.

### Session Management

Aina ya `@MemoryId` inaruhusu usimamizi wa kumbukumbu wa kikao kiotomatiki. Kila ID ya kikao hupata mfano wake wa `ChatMemory` unaosimamiwa na bean ya `ChatMemoryProvider`, hivyo hakuna hitaji la kufuatilia kumbukumbu kwa mkono.

### Error Handling

Vifaa vinaweza kushindwa - API zinaweza kucheleweshwa, parameta zinaweza kuwa batili, huduma za nje zikashindwa. Maajenti ya uzalishaji yanahitaji usimamizi wa makosa ili mfano aweze kueleza matatizo au kujaribu mbadala.

## Available Tools

**Vifaa vya Hali ya Hewa** (data bandia kwa maonyesho):
- Pata hali ya hewa ya sasa kwa eneo
- Pata utabiri wa siku nyingi

**Vifaa vya Kubadilisha Joto**:
- Celsius kwenda Fahrenheit
- Fahrenheit kwenda Celsius
- Celsius kwenda Kelvin
- Kelvin kwenda Celsius
- Fahrenheit kwenda Kelvin
- Kelvin kwenda Fahrenheit

Haya ni mifano rahisi, lakini mfumo huongezeka kwa kazi yoyote: kuuliza hifadhidata, kuitisha API, mahesabu, operesheni za faili, au amri za mfumo.

## When to Use Tool-Based Agents

**Tumia vifaa pale:**
- Kujibu inahitaji data za wakati halisi (hali ya hewa, bei za hisa, hesabu za bidhaa)
- Unahitaji kufanya mahesabu zaidi ya hisabati rahisi
- Kufikia hifadhidata au API
- Kuchukua hatua (kutuma barua pepe, kuunda tiketi, kusasisha rekodi)
- Kuunganisha vyanzo vingi vya data

**Usitumiwe vifaa pale:**
- Maswali yanajibiwa kwa maarifa ya jumla
- Jibu ni mazungumzo tu
- Kufanya hivyo kunaweza kuchelewesha uzoefu wa mtumiaji

## Next Steps

**Moduli Ifuatayo:** [05-mcp - Itifaki ya Muktadha wa Mfano (MCP)](../05-mcp/README.md)

---

**Uavigeshaji:** [← Iliyopita: Moduli 03 - RAG](../03-rag/README.md) | [Rudi Kwenye Kuu](../README.md) | [Ifuatayo: Moduli 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Kasi:**  
Hati hii imefasiriwa kwa kutumia huduma ya tafsiri ya AI [Co-op Translator](https://github.com/Azure/co-op-translator). Ingawa tunajitahidi kwa usahihi, tafadhali fahamu kwamba tafsiri za moja kwa moja zinaweza kuwa na makosa au upotoshaji. Hati ya asili kwa lugha yake ya asili inapaswa kuchukuliwa kama chanzo cha mamlaka. Kwa taarifa muhimu sana, tafsiri ya kitaalamu kwa mtu inashauriwa. Hatupo hatarini kwa maelewano mabaya au tafsiri potofu zinazotokea kutokana na matumizi ya tafsiri hii.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->