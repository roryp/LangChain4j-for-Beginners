<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f89f4c106d110e4943c055dd1a2f1dff",
  "translation_date": "2025-12-31T00:33:46+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "tr"
}
-->
# Modül 05: Model Context Protocol (MCP)

## İçindekiler

- [Ne Öğreneceksiniz](../../../05-mcp)
- [MCP Nedir?](../../../05-mcp)
- [MCP Nasıl Çalışır](../../../05-mcp)
- [Agentic Modülü](../../../05-mcp)
- [Örnekleri Çalıştırma](../../../05-mcp)
  - [Önkoşullar](../../../05-mcp)
- [Hızlı Başlangıç](../../../05-mcp)
  - [Dosya İşlemleri (Stdio)](../../../05-mcp)
  - [Süpervizör Ajan](../../../05-mcp)
    - [Çıktıyı Anlamak](../../../05-mcp)
    - [Agentic Modül Özelliklerinin Açıklaması](../../../05-mcp)
- [Ana Kavramlar](../../../05-mcp)
- [Tebrikler!](../../../05-mcp)
  - [Sırada Ne Var?](../../../05-mcp)

## Ne Öğreneceksiniz

Konuşma tabanlı AI oluşturmayı, prompt ustalığını, cevapları belgelerde temellemeyi ve araçlara sahip ajanlar yaratmayı öğrendiniz. Ancak tüm bu araçlar özel uygulamanız için özel olarak oluşturulmuştu. Peki ya AI'nıza herkesin oluşturup paylaşabileceği standart bir araç ekosistemi erişimi verebilseydiniz? Bu modülde, Model Context Protocol (MCP) ve LangChain4j'in agentic modülü ile tam olarak bunu nasıl yapacağınızı öğreneceksiniz. Önce basit bir MCP dosya okuyucusunu göstereceğiz, ardından bunu Supervisor Agent desenini kullanarak gelişmiş agentic iş akışlarına nasıl kolayca entegre edebileceğimizi göstereceğiz.

## MCP Nedir?

Model Context Protocol (MCP) tam olarak bunu sağlar - AI uygulamalarının harici araçları keşfetmesi ve kullanması için standart bir yol. Her veri kaynağı veya servis için özel entegrasyon yazmak yerine, yeteneklerini tutarlı bir formatta açığa çıkaran MCP sunucularına bağlanırsınız. AI ajanınız sonra bu araçları otomatik olarak keşfedip kullanabilir.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.tr.png" alt="MCP Karşılaştırması" width="800"/>

*MCP Öncesi: Karmaşık nokta-noktaya entegrasyonlar. MCP Sonrası: Tek protokol, sonsuz imkanlar.*

MCP AI geliştirmede temel bir problemi çözer: her entegrasyon özeldir. GitHub'a erişmek mi istiyorsunuz? Özel kod. Dosya okumak mı istiyorsunuz? Özel kod. Bir veritabanı sorgulamak mı istiyorsunuz? Özel kod. Ve bu entegrasyonların hiçbiri diğer AI uygulamalarıyla çalışmaz.

MCP bunu standartlaştırır. Bir MCP sunucusu, net açıklamalar ve şemalarla araçları açığa çıkarır. Herhangi bir MCP istemcisi bağlanabilir, mevcut araçları keşfedebilir ve kullanabilir. Bir kere oluşturun, her yerde kullanın.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.tr.png" alt="MCP Mimarisi" width="800"/>

*Model Context Protocol mimarisi - standartlaştırılmış araç keşfi ve yürütme*

## MCP Nasıl Çalışır

**Sunucu-İstemci Mimarisi**

MCP bir istemci-sunucu modelini kullanır. Sunucular araçlar sağlar - dosya okuma, veritabanı sorgulama, API çağrıları. İstemciler (AI uygulamanız) sunuculara bağlanır ve onların araçlarını kullanır.

LangChain4j ile MCP kullanmak için bu Maven bağımlılığını ekleyin:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**Araç Keşfi**

İstemciniz bir MCP sunucusuna bağlandığında, "Hangi araçlara sahipsiniz?" diye sorar. Sunucu, her biri açıklamalar ve parametre şemaları içeren mevcut araçların bir listesini yanıtlar. AI ajanınız daha sonra kullanıcı isteklerine göre hangi araçların kullanılacağına karar verebilir.

**Taşıma Mekanizmaları**

MCP farklı taşıma mekanizmalarını destekler. Bu modül yerel süreçler için Stdio taşımayı gösterir:

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.tr.png" alt="Taşıma Mekanizmaları" width="800"/>

*MCP taşıma mekanizmaları: uzak sunucular için HTTP, yerel süreçler için Stdio*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Yerel süreçler için. Uygulamanız bir sunucuyu alt süreç olarak başlatır ve standart giriş/çıkış üzerinden iletişim kurar. Dosya sistemi erişimi veya komut satırı araçları için kullanışlıdır.

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@2025.12.18",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 [GitHub Copilot](https://github.com/features/copilot) Chat ile deneyin:** Açın [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) ve sorun:
> - "Stdio taşıma nasıl çalışır ve HTTP ile ne zaman kullanılmalı?"
> - "LangChain4j başlatılan MCP sunucu süreçlerinin yaşam döngüsünü nasıl yönetiyor?"
> - "AI'ya dosya sistemine erişim vermenin güvenlik sonuçları nelerdir?"

## Agentic Modülü

MCP standart araçlar sağlarken, LangChain4j'in **agentic modülü** bu araçları orkestre eden ajanlar oluşturmak için deklaratif bir yol sunar. `@Agent` açıklaması ve `AgenticServices` ile davranışı interface'ler aracılığıyla, zorlayıcı (imperative) kod yerine tanımlayabilirsiniz.

Bu modülde, kullanıcı isteğine göre hangi alt-ajanları çağıracağına dinamik olarak karar veren ileri düzey bir agentic AI yaklaşımı olan **Süpervizör Ajan** desenini inceleyeceksiniz. Bu iki kavramı, alt-ajanlarımızdan birine MCP destekli dosya erişimi yetenekleri vererek birleştireceğiz.

Agentic modülü kullanmak için bu Maven bağımlılığını ekleyin:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ Deneysel:** `langchain4j-agentic` modülü **deneysel**dir ve değişikliğe tabidir. AI asistanları oluşturmanın kararlı yolu, özel araçlarla `langchain4j-core` kullanmaya devam etmektir (Modül 04).

## Örnekleri Çalıştırma

### Önkoşullar

- Java 21+, Maven 3.9+
- MCP sunucuları için Node.js 16+ ve npm
- `.env` dosyasında kök dizinden yapılandırılmış ortam değişkenleri:
  - **StdioTransportDemo için:** `GITHUB_TOKEN` (GitHub Kişisel Erişim Token'ı)
  - **SupervisorAgentDemo için:** `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (Modüller 01-04 ile aynı)

> **Not:** Eğer henüz ortam değişkenlerinizi ayarlamadıysanız, talimatlar için [Module 00 - Quick Start](../00-quick-start/README.md) bölümüne bakın veya kök dizinde `.env.example` dosyasını `.env` olarak kopyalayın ve değerlerinizi doldurun.

## Hızlı Başlangıç

**VS Code Kullanımı:** Explorer'daki herhangi bir demo dosyasına sağ tıklayıp **"Run Java"** seçeneğini seçin veya Run and Debug panelindeki başlatma yapılandırmalarını kullanın (önce token'ınızı `.env` dosyasına eklediğinizden emin olun).

**Maven Kullanımı:** Alternatif olarak, aşağıdaki örneklerle komut satırından çalıştırabilirsiniz.

### Dosya İşlemleri (Stdio)

Bu, yerel alt süreç tabanlı araçları gösterir.

**✅ Önkoşul gerekmiyor** - MCP sunucusu otomatik olarak başlatılır.

**VS Code Kullanımı:** `StdioTransportDemo.java` dosyasına sağ tıklayıp **"Run Java"** seçin.

**Maven Kullanımı:**

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

Uygulama otomatik olarak bir dosya sistemi MCP sunucusu başlatır ve yerel bir dosyayı okur. Alt süreç yönetiminin sizin için nasıl ele alındığına dikkat edin.

**Beklenen çıktı:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Süpervizör Ajan

<img src="../../../translated_images/agentic.cf84dcda226374e3.tr.png" alt="Agentic Modülü" width="800"/>


**Süpervizör Ajan deseni**, deterministik iş akışlarından farklı olarak (sıralı, döngü, paralel) esnek bir agentic AI formudur. Bir Süpervizör, kullanıcının isteğine göre hangi ajanların çağrılacağına otonom şekilde karar vermek için bir LLM kullanır.

**Süpervizörü MCP ile Birleştirme:** Bu örnekte, `FileAgent`'e `toolProvider(mcpToolProvider)` aracılığıyla MCP dosya sistemi araçlarına erişim veriyoruz. Bir kullanıcı "bir dosyayı oku ve analiz et" dediğinde, Süpervizör isteği analiz eder ve bir yürütme planı oluşturur. Ardından isteği `FileAgent`'e yönlendirir; `FileAgent` içeriği almak için MCP'nin `read_file` aracını kullanır. Süpervizör bu içeriği yorumlama için `AnalysisAgent`'e iletir ve isteğe bağlı olarak sonuçları özetlemek için `SummaryAgent`'ı çağırır.

Bu, MCP araçlarının agentic iş akışlarına nasıl sorunsuz entegre olduğunu gösterir — Süpervizör dosyaların nasıl okunduğunu bilmek zorunda değil, sadece `FileAgent`'in bunu yapabildiğini bilir. Süpervizör farklı türdeki isteklere dinamik olarak uyum sağlar ve ya son ajanın yanıtını ya da tüm işlemlerin bir özetini döndürür.

**Başlatma Betiklerini Kullanma (Önerilen):**

Başlatma betikleri kök `.env` dosyasından ortam değişkenlerini otomatik olarak yükler:

**Bash:**
```bash
cd 05-mcp
chmod +x start.sh
./start.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start.ps1
```

**VS Code Kullanımı:** `SupervisorAgentDemo.java` dosyasına sağ tıklayıp **"Run Java"** seçin (`.env` dosyanızın yapılandırıldığından emin olun).

**Süpervizörün Çalışma Prensibi:**

```java
// Belirli yeteneklere sahip birden fazla ajan tanımla
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // Dosya işlemleri için MCP araçlarına sahiptir
        .build();

AnalysisAgent analysisAgent = AgenticServices.agentBuilder(AnalysisAgent.class)
        .chatModel(model)
        .build();

SummaryAgent summaryAgent = AgenticServices.agentBuilder(SummaryAgent.class)
        .chatModel(model)
        .build();

// Bu ajanları koordine eden bir Denetleyici oluştur
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)  // "planlayıcı" modeli
        .subAgents(fileAgent, analysisAgent, summaryAgent)
        .responseStrategy(SupervisorResponseStrategy.SUMMARY)
        .build();

// Denetleyici hangi ajanların çağrılacağına özerk olarak karar verir
// Sadece doğal dilde bir istek iletin - LLM yürütmeyi planlar
String response = supervisor.invoke("Read the file at /path/file.txt and analyze it");
```

Tam uygulama için [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) dosyasına bakın.

> **🤖 [GitHub Copilot](https://github.com/features/copilot) Chat ile deneyin:** Açın [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) ve sorun:
> - "Süpervizör hangi ajanları çağıracağına nasıl karar veriyor?"
> - "Süpervizör ile Sıralı iş akışı desenleri arasındaki fark nedir?"
> - "Süpervizörün planlama davranışını nasıl özelleştirebilirim?"

#### Çıktıyı Anlamak

Demo'yu çalıştırdığınızda, Süpervizörün birden fazla ajanın nasıl orkestre edildiğine dair yapılandırılmış bir yürütmeyi göreceksiniz. Her bölümün ne anlama geldiği:

```
======================================================================
  SUPERVISOR AGENT DEMO
======================================================================

This demo shows how a Supervisor Agent orchestrates multiple specialized agents.
The Supervisor uses an LLM to decide which agent to call based on the task.
```

**Başlık** demo'yu tanıtır ve temel kavramı açıklar: Süpervizör hangi ajanları çağıracağına karar vermek için sert kurallar yerine bir LLM kullanır.

```
--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]     FileAgent     - Reads files using MCP filesystem tools
  [ANALYZE]  AnalysisAgent - Analyzes content for structure, tone, and themes
  [SUMMARY]  SummaryAgent  - Creates concise summaries of content
```

**Mevcut Ajanlar** Süpervizörün seçebileceği üç uzmanlaşmış ajanı gösterir. Her ajanın belirli bir yeteneği vardır:
- **FileAgent** MCP araçlarını kullanarak dosyaları okuyabilir (harici yetenek)
- **AnalysisAgent** içeriği analiz eder (saf LLM yeteneği)
- **SummaryAgent** özetler oluşturur (saf LLM yeteneği)

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and analyze what it's about"
```

**Kullanıcı İsteği** ne istendiğini gösterir. Süpervizör bunu çözümlemeli ve hangi ajanları çağıracağına karar vermelidir.

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor will now decide which agents to invoke and in what order...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source Java library designed to simplify...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> AnalysisAgent (analyzing content)
  |
  |   Input: LangChain4j is an open-source Java library...
  |
  |   Result: Structure: The content is organized into clear paragraphs that int...
  +-- [OK] AnalysisAgent (analyzing content) completed
```

**Süpervizör Orkestrasyonu** sihrin olduğu yerdir. Şu adımlara dikkat edin:
1. Süpervizör **önce FileAgent'i seçti**, çünkü istek "dosyayı oku" ifadesini içeriyordu
2. FileAgent dosya içeriğini almak için MCP'nin `read_file` aracını kullandı
3. Süpervizör daha sonra **AnalysisAgent'i seçti** ve dosya içeriğini ona iletti
4. AnalysisAgent yapı, ton ve temaları analiz etti

Süpervizörün bu kararları kullanıcının isteğine dayanarak **otonom şekilde** verdiğine dikkat edin — sabitlenmiş bir iş akışı yok!

**Nihai Yanıt** Süpervizörün çağırdığı tüm ajanların çıktılarını birleştirerek sentezlediği cevaptır. Örnek, her ajanın kaydettiği özet ve analiz sonuçlarını gösteren agentic kapsamını döker.

```
--- FINAL RESPONSE ---------------------------------------------------
I read the contents of the file and analyzed its structure, tone, and key themes.
The file introduces LangChain4j as an open-source Java library for integrating
large language models...

--- AGENTIC SCOPE (Shared Memory) ------------------------------------
  Agents store their results in a shared scope for other agents to use:
  * summary: LangChain4j is an open-source Java library...
  * analysis: Structure: The content is organized into clear paragraphs that in...
```

### Agentic Modül Özelliklerinin Açıklaması

Örnek, agentic modülün birkaç gelişmiş özelliğini gösterir. Agentic Scope ve Agent Dinleyicilerini daha yakından inceleyelim.

**Agentic Scope** ajanların `@Agent(outputKey="...")` kullanarak sonuçlarını depoladığı paylaşılan belleği gösterir. Bu şunları sağlar:
- Sonraki ajanların önceki ajanların çıktısına erişebilmesi
- Süpervizörün nihai bir yanıt sentezlemesi
- Her ajanın ne ürettiğini inceleyebilmeniz

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String story = scope.readState("story");
List<AgentInvocation> history = scope.agentInvocations("analysisAgent");
```

**Agent Dinleyicileri** ajan yürütmesini izleme ve hata ayıklama imkanı sağlar. Demoda gördüğünüz adım adım çıktı, her ajan çağrısına bağlanan bir AgentListener'dan gelir:
- **beforeAgentInvocation** - Süpervizör bir ajan seçtiğinde çağrılır; hangi ajanın seçildiğini ve nedenini görmenizi sağlar
- **afterAgentInvocation** - Bir ajan tamamlandığında çağrılır; sonucunu gösterir
- **inheritedBySubagents** - True olduğunda, dinleyici hiyerarşideki tüm ajanları izler

```java
AgentListener monitor = new AgentListener() {
    private int step = 0;
    
    @Override
    public void beforeAgentInvocation(AgentRequest request) {
        step++;
        System.out.println("  +-- STEP " + step + ": " + request.agentName());
    }
    
    @Override
    public void afterAgentInvocation(AgentResponse response) {
        System.out.println("  +-- [OK] " + response.agentName() + " completed");
    }
    
    @Override
    public boolean inheritedBySubagents() {
        return true; // Tüm alt ajanlara ilet
    }
};
```

Süpervizör deseninin ötesinde, `langchain4j-agentic` modülü birkaç güçlü iş akışı deseni ve özellik sağlar:

| Pattern | Description | Use Case |
|---------|-------------|----------|
| **Sequential** | Execute agents in order, output flows to next | Pipelines: research → analyze → report |
| **Parallel** | Run agents simultaneously | Independent tasks: weather + news + stocks |
| **Loop** | Iterate until condition met | Quality scoring: refine until score ≥ 0.8 |
| **Conditional** | Route based on conditions | Classify → route to specialist agent |
| **Human-in-the-Loop** | Add human checkpoints | Approval workflows, content review |

## Ana Kavramlar

**MCP** mevcut araç ekosistemlerinden yararlanmak, birden çok uygulamanın paylaşabileceği araçlar oluşturmak, üçüncü taraf hizmetleri standart protokollerle entegre etmek veya araç uygulamalarını kodu değiştirmeden değiştirmek istediğinizde idealdir.

**Agentic Modül** `@Agent` açıklamalarıyla deklaratif ajan tanımları istediğinizde, iş akışı orkestrasyonuna (sıralı, döngü, paralel) ihtiyaç duyduğunuzda, interface tabanlı ajan tasarımını zorlayıcı koda tercih ettiğinizde veya birden çok ajanın `outputKey` aracılığıyla çıktı paylaştığı durumlarda en iyi şekilde çalışır.

**Süpervizör Ajan deseni** iş akışının önceden tahmin edilemediği ve LLM'nin karar vermesini istediğinizde, birden fazla uzmanlaşmış ajanın dinamik orkestrasyona ihtiyaç duyduğu durumlarda, farklı yeteneklere yönlendiren konuşma sistemleri oluştururken veya en esnek, uyumlu ajan davranışını istediğinizde öne çıkar.

## Tebrikler!

LangChain4j for Beginners kursunu tamamladınız. Şunları öğrendiniz:

- Belleğe sahip konuşma tabanlı AI nasıl inşa edilir (Modül 01)
- Farklı görevler için prompt mühendisliği desenleri (Modül 02)
- RAG ile cevapları belgelerinizde temelleme (Modül 03)
- Özel araçlarla temel AI ajanları (asistanlar) oluşturma (Modül 04)
- LangChain4j MCP ve Agentic modülleri ile standartlaştırılmış araçların entegrasyonu (Modül 05)

### Sonraki Adımlar?

Modülleri tamamladıktan sonra, LangChain4j test kavramlarının uygulamada nasıl çalıştığını görmek için [Test Kılavuzu](../docs/TESTING.md) bölümünü inceleyin.

**Resmi Kaynaklar:**
- [LangChain4j Documentation](https://docs.langchain4j.dev/) - Kapsamlı kılavuzlar ve API referansı
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Kaynak kodu ve örnekler
- [LangChain4j Tutorials](https://docs.langchain4j.dev/tutorials/) - Farklı kullanım senaryoları için adım adım öğreticiler

Kursu tamamladığınız için teşekkür ederiz!

---

**Gezinme:** [← Önceki: Modül 04 - Araçlar](../04-tools/README.md) | [Ana Sayfaya Dön](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Sorumluluk Reddi:
Bu belge, yapay zeka çeviri hizmeti [Co-op Translator](https://github.com/Azure/co-op-translator) kullanılarak çevrilmiştir. Doğruluğa özen göstermemize rağmen, otomatik çevirilerin hatalar veya yanlışlıklar içerebileceğini lütfen unutmayın. Orijinal belge, ana dilindeki hali yetkili kaynak olarak kabul edilmelidir. Kritik bilgiler için profesyonel insan çevirisi tavsiye edilir. Bu çevirinin kullanılmasından kaynaklanan herhangi bir yanlış anlama veya yanlış yorumdan sorumlu değiliz.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->