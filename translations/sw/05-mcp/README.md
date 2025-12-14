<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c25ec1f10ef156c53e190cdf8b0711ab",
  "translation_date": "2025-12-13T18:01:49+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "sw"
}
-->
# Moduli 05: Itifaki ya Muktadha wa Mfano (MCP)

## Jedwali la Yaliyomo

- [Utajifunza Nini](../../../05-mcp)
- [Kuelewa MCP](../../../05-mcp)
- [Jinsi MCP Inavyofanya Kazi](../../../05-mcp)
  - [Mimari ya Server-Client](../../../05-mcp)
  - [Ugunduzi wa Zana](../../../05-mcp)
  - [Mekanizimu za Usafirishaji](../../../05-mcp)
- [Mahitaji ya Awali](../../../05-mcp)
- [Moduli Hii Inashughulikia Nini](../../../05-mcp)
- [Anza Haraka](../../../05-mcp)
  - [Mfano 1: Kihesabu Mbali (Streamable HTTP)](../../../05-mcp)
  - [Mfano 2: Operesheni za Faili (Stdio)](../../../05-mcp)
  - [Mfano 3: Uchambuzi wa Git (Docker)](../../../05-mcp)
- [Mafundisho Muhimu](../../../05-mcp)
  - [Uchaguzi wa Usafirishaji](../../../05-mcp)
  - [Ugunduzi wa Zana](../../../05-mcp)
  - [Usimamizi wa Kikao](../../../05-mcp)
  - [Mazingira ya Mifumo Mbalimbali](../../../05-mcp)
- [Wakati wa Kutumia MCP](../../../05-mcp)
- [Ecosystem ya MCP](../../../05-mcp)
- [Hongera!](../../../05-mcp)
  - [Nini Kifuatacho?](../../../05-mcp)
- [Matatizo na Ufumbuzi](../../../05-mcp)

## Utajifunza Nini

Umejenga AI ya mazungumzo, umejifunza kutumia maelekezo, kuweka majibu msingi kwenye nyaraka, na kuunda mawakala wenye zana. Lakini zana zote hizo zilijengwa maalum kwa ajili ya programu yako. Je, ungeweza kutoa AI yako ufikiaji wa mfumo wa zana uliopangwa kwa viwango ambavyo mtu yeyote anaweza kuunda na kushiriki?

Itifaki ya Muktadha wa Mfano (MCP) hutoa hasa hilo - njia ya kawaida kwa programu za AI kugundua na kutumia zana za nje. Badala ya kuandika muunganisho maalum kwa kila chanzo cha data au huduma, unajiunga na seva za MCP zinazoweka uwezo wao kwa muundo unaoendana. Wakala wako wa AI anaweza kisha kugundua na kutumia zana hizi moja kwa moja.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5448d2fa21a61218777ceb8010ea0390dd43924b26df35f61.sw.png" alt="MCP Comparison" width="800"/>

*Kabla ya MCP: Muunganisho tata wa nukta kwa nukta. Baada ya MCP: Itifaki moja, uwezekano usio na kikomo.*

## Kuelewa MCP

MCP inatatua tatizo msingi katika maendeleo ya AI: kila muunganisho ni maalum. Unataka kufikia GitHub? Msimbo maalum. Unataka kusoma faili? Msimbo maalum. Unataka kuuliza hifadhidata? Msimbo maalum. Na hakuna muunganisho huu unaofanya kazi na programu nyingine za AI.

MCP huweka viwango hivi. Seva ya MCP inaonyesha zana na maelezo wazi na miundo. Mteja yeyote wa MCP anaweza kuungana, kugundua zana zinazopatikana, na kuzitumia. Jenga mara moja, tumia kila mahali.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9814b7cffade208d4b0d97203c22df8d8e5504d8238fa7065.sw.png" alt="MCP Architecture" width="800"/>

*Mimari ya Itifaki ya Muktadha wa Mfano - ugunduzi na utekelezaji wa zana ulio na viwango*

## Jinsi MCP Inavyofanya Kazi

**Mimari ya Server-Client**

MCP hutumia mfano wa mteja-seva. Seva hutoa zana - kusoma faili, kuuliza hifadhidata, kuita API. Wateja (programu yako ya AI) huungana na seva na kutumia zana zao.

**Ugunduzi wa Zana**

Wakati mteja wako anajiunga na seva ya MCP, huuliza "Una zana gani?" Seva hujibu na orodha ya zana zinazopatikana, kila moja ikiwa na maelezo na miundo ya vigezo. Wakala wako wa AI anaweza kisha kuamua zana gani za kutumia kulingana na maombi ya mtumiaji.

**Mekanizimu za Usafirishaji**

MCP hufafanua mekanizimu mbili za usafirishaji: HTTP kwa seva za mbali, Stdio kwa michakato ya ndani (ikiwa ni pamoja na kontena za Docker):

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020ed801b772b26ed69338e22739677aa017e0968f6538b09a2.sw.png" alt="Transport Mechanisms" width="800"/>

*Mekanizimu za usafirishaji za MCP: HTTP kwa seva za mbali, Stdio kwa michakato ya ndani (ikiwa ni pamoja na kontena za Docker)*

**Streamable HTTP** - [StreamableHttpDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java)

Kwa seva za mbali. Programu yako hufanya maombi ya HTTP kwa seva inayotumia mtandao. Inatumia Matukio Yanayotumwa na Seva kwa mawasiliano ya wakati halisi.

```java
McpTransport httpTransport = new StreamableHttpMcpTransport.Builder()
    .url("http://localhost:3001/mcp")
    .timeout(Duration.ofSeconds(60))
    .logRequests(true)
    .logResponses(true)
    .build();
```

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`StreamableHttpDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java) na uliza:
> - "MCP inatofautianaje na muunganisho wa zana moja kwa moja kama katika Moduli 04?"
> - "Faida za kutumia MCP kwa kushiriki zana kati ya programu ni zipi?"
> - "Nashughulikiaje kushindwa kuungana au muda wa kusubiri kwa seva za MCP?"

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Kwa michakato ya ndani. Programu yako huanzisha seva kama mchakato mdogo na kuwasiliana kupitia pembejeo/mazao ya kawaida. Inafaa kwa upatikanaji wa mfumo wa faili au zana za mstari wa amri.

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@0.6.2",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) na uliza:
> - "Usafirishaji wa Stdio hufanya kazi vipi na ni lini niitumie badala ya HTTP?"
> - "LangChain4j husimamia vipi mzunguko wa maisha ya michakato ya seva za MCP inayozaliwa?"
> - "Athari za usalama za kutoa AI ufikiaji wa mfumo wa faili ni zipi?"

**Docker (inatumia Stdio)** - [GitRepositoryAnalyzer.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java)

Kwa huduma zilizo ndani ya kontena. Inatumia usafirishaji wa stdio kuwasiliana na kontena la Docker kupitia `docker run`. Nzuri kwa utegemezi mgumu au mazingira yaliyotengwa.

```java
McpTransport dockerTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        "docker", "run",
        "-e", "GITHUB_PERSONAL_ACCESS_TOKEN=" + System.getenv("GITHUB_TOKEN"),
        "-v", volumeMapping,
        "-i", "mcp/git"
    ))
    .logEvents(true)
    .build();
```

> **🤖 Jaribu na [GitHub Copilot](https://github.com/features/copilot) Chat:** Fungua [`GitRepositoryAnalyzer.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java) na uliza:
> - "Usafirishaji wa Docker unawenganisha vipi seva za MCP na faida zake ni zipi?"
> - "Naboreshaje usanidi wa milima ya volumu kushiriki data kati ya mwenyeji na kontena za MCP?"
> - "Mazingira bora ya kusimamia mizunguko ya maisha ya seva za MCP zinazotumia Docker ni yapi?"

## Kuendesha Mifano

### Mahitaji ya Awali

- Java 21+, Maven 3.9+
- Node.js 16+ na npm (kwa seva za MCP)
- **Docker Desktop** - Lazima iwe **INAYOKIMIA** kwa Mfano 3 (si tu imewekwa)
- Tokeni ya Ufikiaji wa Kibinafsi ya GitHub imewekwa kwenye faili `.env` (kutoka Moduli 00)

> **Kumbuka:** Ikiwa bado hujaunda tokeni yako ya GitHub, angalia [Moduli 00 - Anza Haraka](../00-quick-start/README.md) kwa maelekezo.

> **⚠️ Watumiaji wa Docker:** Kabla ya kuendesha Mfano 3, hakikisha Docker Desktop inakimbia kwa `docker ps`. Ikiwa unaona makosa ya muunganisho, anzisha Docker Desktop na subiri takriban sekunde 30 kwa ajili ya kuanzishwa.

## Anza Haraka

**Kutumia VS Code:** Bonyeza kulia kwenye faili yoyote ya maonyesho kwenye Explorer na chagua **"Run Java"**, au tumia usanidi wa kuanzisha kutoka kwenye paneli ya Run and Debug (hakikisha umeongeza tokeni yako kwenye faili `.env` kwanza).

**Kutumia Maven:** Vinginevyo, unaweza kuendesha kutoka mstari wa amri kwa mifano ifuatayo.

**⚠️ Muhimu:** Mifano mingine ina mahitaji ya awali (kama kuanzisha seva ya MCP au kujenga picha za Docker). Angalia mahitaji ya kila mfano kabla ya kuendesha.

### Mfano 1: Kihesabu Mbali (Streamable HTTP)

Huu unaonyesha muunganisho wa zana kwa mtandao.

**⚠️ Hitaji la awali:** Lazima uanze seva ya MCP kwanza (angalia Terminal 1 hapa chini).

**Terminal 1 - Anzisha seva ya MCP:**

**Bash:**
```bash
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**PowerShell:**
```powershell
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**Terminal 2 - Endesha mfano:**

**Kutumia VS Code:** Bonyeza kulia `StreamableHttpDemo.java` na chagua **"Run Java"**.

**Kutumia Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

Tazama wakala agundua zana zinazopatikana, kisha tumia kihesabu kufanya ongezeko.

### Mfano 2: Operesheni za Faili (Stdio)

Huu unaonyesha zana za michakato ndogo za ndani.

**✅ Hakuna mahitaji ya awali** - seva ya MCP huanzishwa moja kwa moja.

**Kutumia VS Code:** Bonyeza kulia `StdioTransportDemo.java` na chagua **"Run Java"**.

**Kutumia Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

Programu huanzisha seva ya MCP ya mfumo wa faili moja kwa moja na kusoma faili ya ndani. Angalia jinsi usimamizi wa mchakato mdogo unavyofanywa kwa niaba yako.

**Matokeo yanayotarajiwa:**
```
Assistant response: The content of the file is "Kaboom!".
```

### Mfano 3: Uchambuzi wa Git (Docker)

Huu unaonyesha seva za zana zilizo ndani ya kontena.

**⚠️ Mahitaji ya awali:** 
1. **Docker Desktop lazima IWE INAYOKIMIA** (si tu imewekwa)
2. **Watumiaji wa Windows:** Mode ya WSL 2 inapendekezwa (Mipangilio ya Docker Desktop → General → "Use the WSL 2 based engine"). Mode ya Hyper-V inahitaji usanidi wa kushiriki faili kwa mkono.
3. Lazima ujenge picha ya Docker kwanza (angalia Terminal 1 hapa chini)

**Thibitisha Docker inakimbia:**

**Bash:**
```bash
docker ps  # Inapaswa kuonyesha orodha ya kontena, si kosa
```

**PowerShell:**
```powershell
docker ps  # Inapaswa kuonyesha orodha ya kontena, si kosa
```

Ikiwa unaona kosa kama "Haiwezi kuungana na daemon ya Docker" au "Mfumo hauwezi kupata faili iliyotajwa", anzisha Docker Desktop na subiri kuanzishwa (~sekunde 30).

**Matatizo na Ufumbuzi:**
- Ikiwa AI inaripoti hifadhidata tupu au hakuna faili, milima ya volumu (`-v`) haifanyi kazi.
- **Watumiaji wa Windows Hyper-V:** Ongeza saraka ya mradi kwenye Mipangilio ya Docker Desktop → Resources → File sharing, kisha anzisha tena Docker Desktop.
- **Suluhisho lililopendekezwa:** Badilisha hadi mode ya WSL 2 kwa kushiriki faili moja kwa moja (Mipangilio → General → wezesha "Use the WSL 2 based engine").

**Terminal 1 - Jenga picha ya Docker:**

**Bash:**
```bash
cd servers/src/git
docker build -t mcp/git .
```

**PowerShell:**
```powershell
cd servers/src/git
docker build -t mcp/git .
```

**Terminal 2 - Endesha mchambuzi:**

**Kutumia VS Code:** Bonyeza kulia `GitRepositoryAnalyzer.java` na chagua **"Run Java"**.

**Kutumia Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

Programu huanzisha kontena la Docker, linapandisha hifadhidata yako, na kuchambua muundo na maudhui ya hifadhidata kupitia wakala wa AI.

## Mafundisho Muhimu

**Uchaguzi wa Usafirishaji**

Chagua kulingana na mahali zana zako zipo:
- Huduma za mbali → Streamable HTTP
- Mfumo wa faili wa ndani → Stdio
- Utegemezi mgumu → Docker

**Ugunduzi wa Zana**

Wateja wa MCP hugundua zana zinazopatikana moja kwa moja wanapoungana. Wakala wako wa AI anaona maelezo ya zana na huamua ni zipi za kutumia kulingana na ombi la mtumiaji.

**Usimamizi wa Kikao**

Usafirishaji wa Streamable HTTP huendeleza vikao, kuruhusu mawasiliano yenye hali na seva za mbali. Usafirishaji wa Stdio na Docker kawaida hauhifadhi hali.

**Mazingira ya Mifumo Mbalimbali**

Mifano hushughulikia tofauti za mifumo moja kwa moja (tofauti za amri za Windows vs Unix, mabadiliko ya njia kwa Docker). Hii ni muhimu kwa utoaji wa uzalishaji katika mazingira tofauti.

## Wakati wa Kutumia MCP

**Tumia MCP wakati:**
- Unataka kutumia mifumo ya zana iliyopo
- Kujenga zana ambazo programu nyingi zitazitumia
- Kuunganisha huduma za mtu wa tatu kwa itifaki za kawaida
- Unahitaji kubadilisha utekelezaji wa zana bila mabadiliko ya msimbo

**Tumia zana maalum (Moduli 04) wakati:**
- Kujenga utendaji maalum wa programu
- Utendaji ni muhimu (MCP huongeza mzigo)
- Zana zako ni rahisi na hazitatumika tena
- Unahitaji udhibiti kamili juu ya utekelezaji

## Ecosystem ya MCP

Itifaki ya Muktadha wa Mfano ni kiwango wazi chenye mfumo unaokua:

- Seva rasmi za MCP kwa kazi za kawaida (mfumo wa faili, Git, hifadhidata)
- Seva zinazochangia jamii kwa huduma mbalimbali
- Maelezo na miundo ya zana iliyowekwa viwango
- Ulinganifu wa mifumo mingi (inayofanya kazi na mteja yeyote wa MCP)

Kiwango hiki kinamaanisha zana zilizojengwa kwa programu moja ya AI zinafanya kazi na nyingine, zikaunda mfumo wa pamoja wa uwezo.

## Hongera!

Umehitimisha kozi ya LangChain4j kwa Waanzilishi. Umejifunza:

- Jinsi ya kujenga AI ya mazungumzo yenye kumbukumbu (Moduli 01)
- Mifumo ya uhandisi wa maelekezo kwa kazi tofauti (Moduli 02)
- Kuweka majibu msingi kwenye nyaraka zako kwa RAG (Moduli 03)
- Kuunda mawakala wa AI wenye zana maalum (Moduli 04)
- Kuunganisha zana zilizo na viwango kupitia MCP (Moduli 05)

Sasa una msingi wa kujenga programu za AI za uzalishaji. Mafundisho uliyopata yanatumika bila kujali mifumo maalum au mifano - ni mifumo ya msingi katika uhandisi wa AI.

### Nini Kifuatacho?

Baada ya kumaliza moduli, chunguza [Mwongozo wa Upimaji](../docs/TESTING.md) kuona dhana za upimaji za LangChain4j zikitumika.

**Rasilimali Rasmi:**
- [Nyaraka za LangChain4j](https://docs.langchain4j.dev/) - Miongozo kamili na rejeleo la API
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Chanzo cha msimbo na mifano
- [Mafunzo ya LangChain4j](https://docs.langchain4j.dev/tutorials/) - Mafunzo hatua kwa hatua kwa matumizi mbalimbali

Asante kwa kumaliza kozi hii!

---

**Uvinjari:** [← Iliyotangulia: Moduli 04 - Zana](../04-tools/README.md) | [Rudi Kwenye Kuu](../README.md)

---

## Matatizo na Ufumbuzi

### Muundo wa Amri ya PowerShell Maven
**Tatizo**: Amri za Maven zinashindwa na kosa `Unknown lifecycle phase ".mainClass=..."`

**Sababu**: PowerShell huchukulia `=` kama kiendeshaji cha ugawaji wa mabadiliko, na kuvunja sintaksia ya mali ya Maven

**Suluhisho**: Tumia kiendeshaji cha kuacha-kuchambua `--%` kabla ya amri ya Maven:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

Kiendeshaji `--%` kinaambia PowerShell kupitisha hoja zote zilizobaki moja kwa moja kwa Maven bila tafsiri.

### Masuala ya Muunganisho wa Docker

**Tatizo**: Amri za Docker zinashindwa na "Cannot connect to Docker daemon" au "The system cannot find the file specified"

**Sababu**: Docker Desktop haifanyi kazi au haijaanza kikamilifu

**Suluhisho**: 
1. Anzisha Docker Desktop
2. Subiri takriban sekunde 30 kwa ajili ya kuanzishwa kikamilifu
3. Thibitisha kwa `docker ps` (inapaswa kuonyesha orodha ya kontena, si kosa)
4. Kisha endesha mfano wako

### Kuweka Kiasi cha Docker kwenye Windows

**Tatizo**: Mchambuzi wa hazina ya Git unaarifu hazina tupu au hakuna faili

**Sababu**: Kuweka kiasi (`-v`) hakifanyi kazi kutokana na usanidi wa kushiriki faili

**Suluhisho**:
- **Inayopendekezwa:** Badilisha hadi hali ya WSL 2 (Mipangilio ya Docker Desktop → General → "Use the WSL 2 based engine")
- **Mbadala (Hyper-V):** Ongeza saraka ya mradi kwenye Mipangilio ya Docker Desktop → Resources → File sharing, kisha anzisha tena Docker Desktop

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Kiarifa cha Kukataa**:
Hati hii imetafsiriwa kwa kutumia huduma ya tafsiri ya AI [Co-op Translator](https://github.com/Azure/co-op-translator). Ingawa tunajitahidi kwa usahihi, tafadhali fahamu kwamba tafsiri za kiotomatiki zinaweza kuwa na makosa au upungufu wa usahihi. Hati ya asili katika lugha yake ya asili inapaswa kuchukuliwa kama chanzo cha mamlaka. Kwa taarifa muhimu, tafsiri ya kitaalamu ya binadamu inapendekezwa. Hatubebei dhamana kwa kutoelewana au tafsiri potofu zinazotokana na matumizi ya tafsiri hii.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->