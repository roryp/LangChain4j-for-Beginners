<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:07:53+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "tr"
}
-->
# LangChain4j Sözlüğü

## İçindekiler

- [Temel Kavramlar](../../../docs)
- [LangChain4j Bileşenleri](../../../docs)
- [AI/ML Kavramları](../../../docs)
- [Koruma Önlemleri](../../../docs)
- [Prompt Mühendisliği](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Ajanlar ve Araçlar](../../../docs)
- [Agentic Modülü](../../../docs)
- [Model Context Protokolü (MCP)](../../../docs)
- [Azure Hizmetleri](../../../docs)
- [Test ve Geliştirme](../../../docs)

Kurs boyunca kullanılan terimler ve kavramlar için hızlı başvuru.

## Temel Kavramlar

**AI Agent** - Yapay zekayı kullanarak özerk şekilde düşünen ve hareket eden sistem. [Module 04](../04-tools/README.md)

**Chain** - Çıktının bir sonraki adıma beslendiği işlem dizisi.

**Chunking** - Belgeleri daha küçük parçalara bölme işlemi. Tipik: 300-500 token ve örtüşmeler. [Module 03](../03-rag/README.md)

**Context Window** - Bir modelin işleyebileceği maksimum token sayısı. GPT-5: 400K token.

**Embeddings** - Metnin anlamını temsil eden sayısal vektörler. [Module 03](../03-rag/README.md)

**Function Calling** - Modelin dış işlevleri çağırmak için yapılandırılmış istekler üretmesi. [Module 04](../04-tools/README.md)

**Hallucination** - Modellerin yanlış ama inandırıcı bilgi üretmesi durumu.

**Prompt** - Dil modeline verilen metin girdisi. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Anahtar kelimeler yerine anlam ile arama yapma, embeddings kullanarak. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: belleksiz. Stateful: sohbet geçmişini tutar. [Module 01](../01-introduction/README.md)

**Tokens** - Modellerin işlediği temel metin birimleri. Maliyet ve sınırları etkiler. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Bir aracın çıktısının sonraki çağrıyı bilgilendirdiği ardışık araç yürütme. [Module 04](../04-tools/README.md)

## LangChain4j Bileşenleri

**AiServices** - Tür güvenli AI hizmet arayüzleri oluşturur.

**OpenAiOfficialChatModel** - OpenAI ve Azure OpenAI modelleri için birleşik istemci.

**OpenAiOfficialEmbeddingModel** - OpenAI Official istemci kullanarak embeddings oluşturur (hem OpenAI hem Azure OpenAI destekler).

**ChatModel** - Dil modelleri için temel arayüz.

**ChatMemory** - Sohbet geçmişini tutar.

**ContentRetriever** - RAG için ilgili belge parçalarını bulur.

**DocumentSplitter** - Belgeleri parçalara ayırır.

**EmbeddingModel** - Metni sayısal vektörlere dönüştürür.

**EmbeddingStore** - Embeddingleri depolar ve getirir.

**MessageWindowChatMemory** - Son mesajların kayan penceresini tutar.

**PromptTemplate** - `{{variable}}` yer tutucularıyla yeniden kullanılabilir promptlar oluşturur.

**TextSegment** - Meta veriye sahip metin parçası. RAG’de kullanılır.

**ToolExecutionRequest** - Araç yürütme isteğini temsil eder.

**UserMessage / AiMessage / SystemMessage** - Sohbet mesaj türleri.

## AI/ML Kavramları

**Few-Shot Learning** - Promptlarda örnekler verme. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Devasa metin verileriyle eğitilmiş yapay zeka modelleri.

**Reasoning Effort** - GPT-5’te düşünme derinliğini kontrol eden parametre. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Çıktının rastgeleliğini kontrol eder. Düşük=belirleyici, yüksek=yaratıcı.

**Vector Database** - Embeddingler için özelleşmiş veritabanı. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Örnek olmadan görev yapma. [Module 02](../02-prompt-engineering/README.md)

## Koruma Önlemleri - [Module 00](../00-quick-start/README.md)

**Defense in Depth** - Uygulama seviyesi koruma katmanları ile sağlayıcı güvenlik filtrelerini birleştiren çok katmanlı güvenlik yaklaşımı.

**Hard Block** - Sağlayıcının ciddi içerik ihlallerinde HTTP 400 hatası vermesi.

**InputGuardrail** - Kullanıcı girdisinin LLM’e ulaşmadan önce geçerliliğini denetleyen LangChain4j arayüzü. Zararlı istemleri erkenden engelleyerek maliyet ve gecikmeyi azaltır.

**InputGuardrailResult** - Koruma doğrulama dönüş tipi: `success()` ya da `fatal("neden")`.

**OutputGuardrail** - Yapay zeka yanıtlarını kullanıcıya dönmeden önce doğrulamak için arayüz.

**Provider Safety Filters** - API düzeyinde ihlalleri yakalayan AI sağlayıcılarından (ör. GitHub Modelleri) gelen yerleşik içerik filtreleri.

**Soft Refusal** - Model hata vermeden nazikçe yanıt vermeyi reddeder.

## Prompt Mühendisliği - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Daha iyi doğruluk için adım adım akıl yürütme.

**Constrained Output** - Belirli biçim veya yapı zorunluluğu.

**High Eagerness** - GPT-5’te detaylı düşünmek için kalıp.

**Low Eagerness** - GPT-5’te hızlı cevaplar için kalıp.

**Multi-Turn Conversation** - Değişimler boyunca bağlamı sürdürme.

**Role-Based Prompting** - Sistem mesajlarıyla model persona ayarlama.

**Self-Reflection** - Modelin çıktısını değerlendirmesi ve geliştirmesi.

**Structured Analysis** - Sabit değerlendirme çerçevesi.

**Task Execution Pattern** - Planla → Yürüt → Özetle.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Yükle → parça → göm → depola.

**In-Memory Embedding Store** - Test için kalıcı olmayan bellek içi depolama.

**RAG** - Yanıtları gerçek verilere dayandırmak için arama ile üretimi birleştirir.

**Similarity Score** - Anlamsal benzerlik ölçüsü (0-1).

**Source Reference** - Getirilen içeriğe ait meta veri.

## Ajanlar ve Araçlar - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Java yöntemlerini AI çağrılabilir araçlar olarak işaretler.

**ReAct Pattern** - Akıl yürüt → Hareket et → Gözlemle → Tekrarla.

**Session Management** - Farklı kullanıcılar için ayrı bağlamlar.

**Tool** - AI ajanının çağırabileceği işlev.

**Tool Description** - Araç amacı ve parametrelerinin dokümantasyonu.

## Agentic Modülü - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - Arayüzleri AI ajanları olarak ve bildirimsel davranış tanımıyla işaretler.

**Agent Listener** - `beforeAgentInvocation()` ve `afterAgentInvocation()` ile ajanın yürütülmesini izlemek için kanca.

**Agentic Scope** - Ajanların sonuçları downstream ajanlar için `outputKey` kullanarak depoladığı paylaşılan bellek.

**AgenticServices** - `agentBuilder()` ve `supervisorBuilder()` ile ajan oluşturma fabrikası.

**Conditional Workflow** - Koşullara göre farklı uzman ajana yönlendirme.

**Human-in-the-Loop** - Onay veya içerik incelemesi için insan kontrol noktaları ekleyen iş akışı kalıbı.

**langchain4j-agentic** - Bildirimsel ajan oluşturma için Maven bağımlılığı (deneysel).

**Loop Workflow** - Koşul sağlanana (örneğin kalite puanı ≥ 0.8) kadar ajan yürütmesini yineleme.

**outputKey** - Sonuçların Agentic Scope içinde nerede depolanacağını belirten ajan notasyonu parametresi.

**Parallel Workflow** - Bağımsız görevler için birden çok ajanı aynı anda çalıştırma.

**Response Strategy** - Süpervizörün nihai cevabı oluşturma biçimi: SON, ÖZET veya PUANLANMIŞ.

**Sequential Workflow** - Ajanları çıktıların sonraki adıma aktığı sırayla yürütme.

**Supervisor Agent Pattern** - Süpervizör LLM’nin dinamik olarak hangi alt ajanları çağıracağına karar verdiği gelişmiş agentic kalıbı.

## Model Context Protokolü (MCP) - [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - LangChain4j’de MCP entegrasyonu için Maven bağımlılığı.

**MCP** - Model Context Protocol: AI uygulamalarını dış araçlara bağlamak için standart. Bir kere oluştur, her yerde kullan.

**MCP Client** - MCP sunucularına bağlanarak araçları keşfeden ve kullanan uygulama.

**MCP Server** - Araçları net açıklamalar ve parametre şemaları ile MCP üzerinden sunan servis.

**McpToolProvider** - LangChain4j bileşeni olarak MCP araçlarını AI hizmetleri ve ajanlarda kullanıma sunar.

**McpTransport** - MCP iletişimi için arayüz. Uygulamaları Stdio ve HTTP içerir.

**Stdio Transport** - stdin/stdout üzerinden yerel süreç taşıması. Dosya sistemi erişimi veya komut satırı araçları için yararlı.

**StdioMcpTransport** - MCP sunucusunu alt süreç olarak başlatan LangChain4j uygulaması.

**Tool Discovery** - İstemcinin mevcut araçları açıklamalar ve şemalar ile sunucudan sorgulaması.

## Azure Hizmetleri - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Vektör özellikli bulut araması. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure kaynaklarını konuşlandırır.

**Azure OpenAI** - Microsoft’un kurumsal yapay zeka servisi.

**Bicep** - Azure altyapı kodu dili. [Altyapı Kılavuzu](../01-introduction/infra/README.md)

**Deployment Name** - Azure’daki model dağıtımı için isim.

**GPT-5** - Sebep-sonuç kontrolü sunan en yeni OpenAI modeli. [Module 02](../02-prompt-engineering/README.md)

## Test ve Geliştirme - [Test Kılavuzu](TESTING.md)

**Dev Container** - Konteynerli geliştirme ortamı. [Yapılandırma](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Ücretsiz AI model oyunu. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Bellek içi depolama ile test.

**Integration Testing** - Gerçek altyapı ile test.

**Maven** - Java için derleme otomasyon aracı.

**Mockito** - Java sahte nesne çatısı.

**Spring Boot** - Java uygulama çatısı. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Feragatname**:  
Bu belge, AI çeviri servisi [Co-op Translator](https://github.com/Azure/co-op-translator) kullanılarak çevrilmiştir. Doğruluk için çaba göstersek de, otomatik çevirilerin hatalar veya yanlışlıklar içerebileceğini lütfen unutmayın. Orijinal belge, ana dilindeki haliyle yetkili kaynak olarak kabul edilmelidir. Kritik bilgiler için profesyonel insan çevirisi önerilir. Bu çevirinin kullanımı sonucu ortaya çıkabilecek yanlış anlama veya yorumlama durumlarından sorumlu değiliz.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->