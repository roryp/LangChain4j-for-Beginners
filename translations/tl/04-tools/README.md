<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-31T03:29:32+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "tl"
}
-->
# Module 04: Mga Ahente ng AI na may Mga Tool

## Table of Contents

- [Ano ang Matututuhan Mo](../../../04-tools)
- [Mga Kinakailangan](../../../04-tools)
- [Pag-unawa sa Mga Ahente ng AI na may Mga Tool](../../../04-tools)
- [Paano Gumagana ang Pagtawag ng Tool](../../../04-tools)
  - [Mga Depinisyon ng Tool](../../../04-tools)
  - [Pagpapasya](../../../04-tools)
  - [Pagsasagawa](../../../04-tools)
  - [Pagbuo ng Tugon](../../../04-tools)
- [Pagkakadena ng Mga Tool](../../../04-tools)
- [Patakbuhin ang Aplikasyon](../../../04-tools)
- [Paggamit ng Aplikasyon](../../../04-tools)
  - [Subukan ang Simpleng Paggamit ng Tool](../../../04-tools)
  - [Subukan ang Pagkakadena ng Tool](../../../04-tools)
  - [Tingnan ang Daloy ng Pag-uusap](../../../04-tools)
  - [Obserbahan ang Pangangatwiran](../../../04-tools)
  - [Mag-eksperimento sa Iba't Ibang Kahilingan](../../../04-tools)
- [Mga Pangunahing Konsepto](../../../04-tools)
  - [ReAct Pattern (Pangangatwiran at Pagkilos)](../../../04-tools)
  - [Mahalaga ang Mga Paglalarawan ng Tool](../../../04-tools)
  - [Pamamahala ng Session](../../../04-tools)
  - [Paghawak ng Mga Error](../../../04-tools)
- [Mga Magagamit na Tool](../../../04-tools)
- [Kailan Gamitin ang Mga Ahenteng Batay sa Tool](../../../04-tools)
- [Mga Susunod na Hakbang](../../../04-tools)

## What You'll Learn

Sa ngayon, natutunan mo kung paano magkaroon ng mga pag-uusap sa AI, istrukturahin nang epektibo ang mga prompt, at i-angkla ang mga tugon sa iyong mga dokumento. Ngunit may isang pangunahing limitasyon: ang mga language model ay makakalikha lamang ng teksto. Hindi nila kayang tumingin ng lagay ng panahon, magsagawa ng kalkulasyon, mag-query ng mga database, o makipag-ugnayan sa mga panlabas na sistema.

Binabago ito ng mga tool. Sa pamamagitan ng pagbibigay sa modelo ng access sa mga function na maaari nitong tawagan, binabago mo ito mula sa isang text generator tungo sa isang ahente na maaaring gumawa ng mga aksyon. Ang modelo ang nagpapasya kung kailan nito kailangan ng tool, aling tool ang gagamitin, at anong mga parameter ang ipapasa. Ipinapatupad ng iyong code ang function at ibinabalik ang resulta. Isinasama ng modelo ang resulta na iyon sa kanyang tugon.

## Prerequisites

- Nakumpleto ang Module 01 (na-deploy ang mga Azure OpenAI resources)
- `.env` na file sa root directory na may mga kredensyal ng Azure (ginawa ng `azd up` sa Module 01)

> **Tandaan:** Kung hindi mo pa nakumpleto ang Module 01, sundin muna ang mga tagubilin sa pag-deploy doon.

## Understanding AI Agents with Tools

> **📝 Tandaan:** Ang terminong "agents" sa module na ito ay tumutukoy sa mga AI assistant na pinalakas ng kakayahang tumawag ng mga tool. Iba ito sa mga pattern ng **Agentic AI** (mga autonomous na ahente na may pagpaplano, memorya, at multi-step reasoning) na tatalakayin natin sa [Module 05: MCP](../05-mcp/README.md).

Ang isang AI agent na may mga tool ay sumusunod sa isang pattern ng pangangatwiran at pagkilos (ReAct):

1. Nagtatanong ang user
2. Nagpapakahulugan ang agent tungkol sa kung ano ang kailangan nitong malaman
3. Nagpapasya ang agent kung kailangan nito ng tool para sumagot
4. Kung oo, tinatawagan ng agent ang angkop na tool na may tamang mga parameter
5. Isinasagawa ng tool at ibinabalik ang data
6. Isinasama ng agent ang resulta at nagbibigay ng pangwakas na sagot

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.tl.png" alt="Pattern ng ReAct" width="800"/>

*Ang ReAct pattern - kung paano nag-iikot ang mga AI agent sa pagitan ng pangangatwiran at pagkilos upang lutasin ang mga problema*

Nangyayari ito nang awtomatiko. Ikaw ang magde-define ng mga tool at ang kanilang mga paglalarawan. Ang modelo ang humahawak ng pagdedesisyon tungkol sa kung kailan at paano ito gagamitin.

## How Tool Calling Works

**Tool Definitions** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Nagde-define ka ng mga function na may malinaw na mga paglalarawan at espesipikasyon ng mga parameter. Nakikita ng modelo ang mga paglalarawang ito sa kanyang system prompt at naiintindihan kung ano ang ginagawa ng bawat tool.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Ang iyong lohika sa paghahanap ng panahon
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Awtomatikong kinokonekta ng Spring Boot ang Assistant gamit ang:
// - bean ng ChatModel
// - Lahat ng @Tool na mga metodo mula sa mga @Component na klase
// - ChatMemoryProvider para sa pamamahala ng sesyon
```

> **🤖 Subukan gamit ang [GitHub Copilot](https://github.com/features/copilot) Chat:** Buksan ang [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) at itanong:
> - "Paano ko i-iintegrate ang totoong weather API tulad ng OpenWeatherMap sa halip na mock data?"
> - "Ano ang gumagawa ng isang magandang paglalarawan ng tool na tumutulong sa AI na magamit ito nang tama?"
> - "Paano ko hinaharap ang mga API error at rate limits sa mga implementasyon ng tool?"

**Decision Making**

Kapag nagtanong ang user ng "What's the weather in Seattle?", nakikilala ng modelo na kailangan nito ang weather tool. Gumagawa ito ng function call na may parameter na location na nakaset sa "Seattle".

**Execution** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Awtomatikong ia-wire ng Spring Boot ang deklaratibong `@AiService` interface sa lahat ng naka-rehistrong tool, at awtomatikong ipapatupad ng LangChain4j ang mga tawag sa tool.

> **🤖 Subukan gamit ang [GitHub Copilot](https://github.com/features/copilot) Chat:** Buksan ang [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) at itanong:
> - "Paano gumagana ang ReAct pattern at bakit ito epektibo para sa mga AI agent?"
> - "Paano nagde-decide ang agent kung aling tool ang gagamitin at sa anong pagkakasunod-sunod?"
> - "Ano ang nangyayari kung pumalpak ang pagsasagawa ng tool - paano ako dapat mag-handle ng mga error nang robust?"

**Response Generation**

Tinatanggap ng modelo ang datos ng panahon at inaayos ito sa isang natural na tugon para sa user.

### Bakit Gumamit ng Declarative AI Services?

Ang module na ito ay gumagamit ng LangChain4j's Spring Boot integration na may deklaratibong `@AiService` interfaces:

- **Spring Boot auto-wiring** - Awtomatikong nai-inject ang ChatModel at mga tool
- **@MemoryId pattern** - Awtomatikong session-based na pamamahala ng memorya
- **Isang instance lang** - Ginagawa ang assistant isang beses at nire-reuse para sa mas mahusay na performance
- **Type-safe execution** - Direktang tinatawagan ang mga Java method na may type conversion
- **Multi-turn orchestration** - Awtomatikong humahawak ng pagkakadena ng mga tool
- **Zero boilerplate** - Walang manwal na AiServices.builder() calls o memory HashMap

Ang mga alternatibong paraan (manwal na `AiServices.builder()`) ay nangangailangan ng mas maraming code at nawawala ang mga benepisyo ng Spring Boot integration.

## Tool Chaining

**Pagkakadena ng Mga Tool** - Maaaring tumawag ang AI ng maramihang mga tool nang sunud-sunod. Itanong ang "What's the weather in Seattle and should I bring an umbrella?" at panoorin itong i-chain ang `getCurrentWeather` habang nagpapakahulugan tungkol sa rain gear.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.tl.png" alt="Pagkakadena ng Mga Tool" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Sunud-sunod na tawag sa tool - ang output ng isang tool ay pinapakain sa susunod na desisyon*

**Graceful Failures** - Magtanong para sa panahon sa isang lungsod na wala sa mock data. Nagbabalik ang tool ng error message, at ipapaliwanag ng AI na hindi nito matutulungan ang user. Ang mga tool ay pumapalya nang ligtas.

Nangyayari ito sa isang solong conversation turn. Ang agent ang nag-o-orchestrate ng maramihang tawag sa tool nang autonomously.

## Run the Application

**Suriin ang deployment:**

Tiyaking umiiral ang `.env` file sa root directory na may mga kredensyal ng Azure (ginawa noong Module 01):
```bash
cat ../.env  # Dapat ipakita ang AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Simulan ang aplikasyon:**

> **Tandaan:** Kung sinuportahan mo na ang lahat ng aplikasyon gamit ang `./start-all.sh` mula sa Module 01, tumatakbo na ang module na ito sa port 8084. Maaari mong laktawan ang mga start command sa ibaba at direktang pumunta sa http://localhost:8084.

**Option 1: Paggamit ng Spring Boot Dashboard (Inirerekomenda para sa mga gumagamit ng VS Code)**

Kasama sa dev container ang Spring Boot Dashboard extension, na nagbibigay ng visual na interface para pamahalaan ang lahat ng Spring Boot application. Makikita mo ito sa Activity Bar sa kaliwang bahagi ng VS Code (hanapin ang icon ng Spring Boot).

Mula sa Spring Boot Dashboard, maaari mong:
- Makita ang lahat ng magagamit na Spring Boot application sa workspace
- Simulan/hintuin ang mga application sa isang click
- Tingnan ang mga log ng application nang real-time
- I-monitor ang status ng application

I-click lang ang play button sa tabi ng "tools" para simulan ang module na ito, o simulan lahat ng module nang sabay-sabay.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.tl.png" alt="Dashboard ng Spring Boot" width="400"/>

**Option 2: Paggamit ng shell scripts**

Simulan ang lahat ng web application (modules 01-04):

**Bash:**
```bash
cd ..  # Mula sa root na direktoryo
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Mula sa direktoryo ng ugat
.\start-all.ps1
```

O simulan lamang ang module na ito:

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

Parehong awtomatikong naglo-load ang mga script ng environment variables mula sa root `.env` file at ibe-build ang mga JAR kung wala pa ang mga ito.

> **Tandaan:** Kung mas gusto mong i-build nang mano-mano ang lahat ng module bago simulan:
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

Buksan ang http://localhost:8084 sa iyong browser.

**Para itigil:**

**Bash:**
```bash
./stop.sh  # Ang module na ito lamang
# O
cd .. && ./stop-all.sh  # Lahat ng mga module
```

**PowerShell:**
```powershell
.\stop.ps1  # Para sa modyul na ito lamang
# O
cd ..; .\stop-all.ps1  # Lahat ng modyul
```

## Using the Application

Nagbibigay ang aplikasyon ng web interface kung saan maaari kang makipag-ugnayan sa isang AI agent na may access sa mga tool para sa weather at temperature conversion.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.tl.png" alt="Interface ng Mga Tool ng Ahente ng AI" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Ang AI Agent Tools interface - mabilisang mga halimbawa at chat interface para makipag-ugnayan sa mga tool*

**Subukan ang Simpleng Paggamit ng Tool**

Magsimula sa isang madaling kahilingan: "Convert 100 degrees Fahrenheit to Celsius". Nakikita ng agent na kailangan nito ang temperature conversion tool, tinatawagan ito nang may tamang mga parameter, at ibinabalik ang resulta. Pansinin kung gaano ito kasimple - hindi mo tinukoy kung aling tool ang gagamitin o paano ito tatawagin.

**Subukan ang Pagkakadena ng Tool**

Ngayon subukan ang mas kumplikado: "What's the weather in Seattle and convert it to Fahrenheit?" Panoorin ang agent na magtrabaho sa mga hakbang na ito. Una nitong kukunin ang weather (na nagbabalik ng Celsius), mapapansin na kailangan nitong mag-convert sa Fahrenheit, tatawagin ang conversion tool, at pagsasamahin ang parehong resulta sa isang tugon.

**Tingnan ang Daloy ng Pag-uusap**

Pinananatili ng chat interface ang kasaysayan ng pag-uusap, na nagpapahintulot sa iyo ng multi-turn interactions. Makikita mo ang lahat ng naunang query at tugon, na nagpapadali na subaybayan ang pag-uusap at maintindihan kung paano binubuo ng agent ang konteksto sa maraming palitan.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.tl.png" alt="Pag-uusap na may Maramihang Tawag sa Tool" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Multi-turn na pag-uusap na nagpapakita ng simpleng conversions, weather lookups, at pagkakadena ng tool*

**Mag-eksperimento sa Iba't Ibang Kahilingan**

Subukan ang iba't ibang kombinasyon:
- Weather lookups: "What's the weather in Tokyo?"
- Temperature conversions: "What is 25°C in Kelvin?"
- Pinagsamang query: "Check the weather in Paris and tell me if it's above 20°C"

Pansinin kung paano ini-interpret ng agent ang natural na wika at ine-map ito sa angkop na tawag sa tool.

## Key Concepts

**ReAct Pattern (Pangangatwiran at Pagkilos)**

Nag-iikot ang agent sa pagitan ng pangangatwiran (pagpapasya kung ano ang gagawin) at pagkilos (paggamit ng mga tool). Pinapagana ng pattern na ito ang autonomous na paglutas ng problema sa halip na simpleng pagsagot sa mga instruksyon.

**Mahalaga ang Mga Paglalarawan ng Tool**

Direkta nakakaapekto sa kalidad ng paggamit ng agent ang kalidad ng mga paglalarawan ng iyong tool. Ang malinaw at tiyak na mga paglalarawan ay tumutulong sa modelo na maintindihan kung kailan at paano tatawagin ang bawat tool.

**Pamamahala ng Session**

Pinapagana ng `@MemoryId` annotation ang awtomatikong session-based na pamamahala ng memorya. Bawat session ID ay nakakakuha ng sariling `ChatMemory` instance na pinamamahalaan ng `ChatMemoryProvider` bean, inaalis ang pangangailangan para sa manwal na pag-track ng memorya.

**Paghawak ng Mga Error**

Maaaring pumalya ang mga tool - nag-timeout ang mga API, maaaring invalid ang mga parameter, bumaba ang mga panlabas na serbisyo. Kailangan ng production agents ng paghahandle ng error upang maipaliwanag ng modelo ang mga problema o subukan ang mga alternatibo.

## Available Tools

**Weather Tools** (mock data para sa demo):
- Kumuha ng kasalukuyang panahon para sa isang lokasyon
- Kumuha ng multi-day forecast

**Temperature Conversion Tools**:
- Celsius to Fahrenheit
- Fahrenheit to Celsius
- Celsius to Kelvin
- Kelvin to Celsius
- Fahrenheit to Kelvin
- Kelvin to Fahrenheit

Ito ay mga simpleng halimbawa, ngunit ang pattern ay umaabot sa anumang function: mga query sa database, API calls, kalkulasyon, file operations, o system commands.

## When to Use Tool-Based Agents

**Gamitin ang mga tool kapag:**
- Nangangailangan ng real-time na data ang sagot (panahon, presyo ng stock, inventory)
- Kailangan mong magsagawa ng kalkulasyon na lampas sa simpleng math
- Nag-a-access ng mga database o APIs
- Gumagawa ng mga aksyon (pagsesend ng email, paglikha ng ticket, pag-update ng mga record)
- Pagsasama-sama ng maramihang pinagmumulan ng data

**Huwag gumamit ng mga tool kapag:**
- Ang mga tanong ay masasagot mula sa pangkalahatang kaalaman
- Ang tugon ay purong pag-uusap lamang
- Ang latency ng tool ay gagawing masyadong mabagal ang karanasan

## Next Steps

**Susunod na Module:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigation:** [← Nakaraang: Module 03 - RAG](../03-rag/README.md) | [Balik sa Main](../README.md) | [Susunod: Module 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Paunawa:
Isinalin ang dokumentong ito gamit ang AI translation service na [Co-op Translator](https://github.com/Azure/co-op-translator). Bagaman nagsusumikap kami para sa katumpakan, pakitandaan na ang mga awtomatikong pagsasalin ay maaaring maglaman ng mga pagkakamali o hindi pagkakatumpakan. Ang orihinal na dokumento sa orihinal nitong wika ang dapat ituring na pinagmumulan ng awtoridad. Para sa mga impormasyong kritikal, inirerekomenda ang propesyonal na pagsasalin ng tao. Hindi kami mananagot sa anumang hindi pagkakaunawaan o maling interpretasyon na magmumula sa paggamit ng pagsasaling ito.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->