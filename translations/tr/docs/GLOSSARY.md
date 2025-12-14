<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:06:20+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "tr"
}
-->
# LangChain4j Sözlüğü

## İçindekiler

- [Temel Kavramlar](../../../docs)
- [LangChain4j Bileşenleri](../../../docs)
- [AI/ML Kavramları](../../../docs)
- [Prompt Mühendisliği](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Ajanlar ve Araçlar](../../../docs)
- [Model Bağlam Protokolü (MCP)](../../../docs)
- [Azure Hizmetleri](../../../docs)
- [Test ve Geliştirme](../../../docs)

Kurs boyunca kullanılan terimler ve kavramlar için hızlı başvuru.

## Temel Kavramlar

**AI Agent** - AI kullanarak özerk şekilde düşünen ve hareket eden sistem. [Modül 04](../04-tools/README.md)

**Chain** - Çıktının bir sonraki adıma beslendiği işlem dizisi.

**Chunking** - Belgeleri daha küçük parçalara bölme. Tipik: 300-500 token, örtüşmeli. [Modül 03](../03-rag/README.md)

**Context Window** - Bir modelin işleyebileceği maksimum token sayısı. GPT-5: 400K token.

**Embeddings** - Metin anlamını temsil eden sayısal vektörler. [Modül 03](../03-rag/README.md)

**Function Calling** - Modelin dış fonksiyonları çağırmak için yapılandırılmış istekler üretmesi. [Modül 04](../04-tools/README.md)

**Hallucination** - Modellerin yanlış ama mantıklı görünen bilgi üretmesi.

**Prompt** - Dil modeline verilen metin girdisi. [Modül 02](../02-prompt-engineering/README.md)

**Semantic Search** - Anahtar kelime yerine anlam kullanarak arama. [Modül 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: hafıza yok. Stateful: konuşma geçmişini tutar. [Modül 01](../01-introduction/README.md)

**Tokens** - Modellerin işlediği temel metin birimleri. Maliyet ve sınırları etkiler. [Modül 01](../01-introduction/README.md)

**Tool Chaining** - Çıktının sonraki çağrıyı bilgilendirdiği ardışık araç çalıştırma. [Modül 04](../04-tools/README.md)

## LangChain4j Bileşenleri

**AiServices** - Tür güvenli AI servis arayüzleri oluşturur.

**OpenAiOfficialChatModel** - OpenAI ve Azure OpenAI modelleri için birleşik istemci.

**OpenAiOfficialEmbeddingModel** - OpenAI Official istemcisi kullanarak embedding oluşturur (hem OpenAI hem Azure OpenAI destekler).

**ChatModel** - Dil modelleri için temel arayüz.

**ChatMemory** - Konuşma geçmişini tutar.

**ContentRetriever** - RAG için ilgili belge parçalarını bulur.

**DocumentSplitter** - Belgeleri parçalara böler.

**EmbeddingModel** - Metni sayısal vektörlere dönüştürür.

**EmbeddingStore** - Embeddingleri depolar ve geri getirir.

**MessageWindowChatMemory** - Son mesajların kayan penceresini tutar.

**PromptTemplate** - `{{variable}}` yer tutucularıyla yeniden kullanılabilir promptlar oluşturur.

**TextSegment** - Meta verili metin parçası. RAG'de kullanılır.

**ToolExecutionRequest** - Araç çalıştırma isteğini temsil eder.

**UserMessage / AiMessage / SystemMessage** - Konuşma mesaj türleri.

## AI/ML Kavramları

**Few-Shot Learning** - Promptlarda örnekler sağlama. [Modül 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Büyük metin verisiyle eğitilmiş AI modelleri.

**Reasoning Effort** - GPT-5’te düşünme derinliğini kontrol eden parametre. [Modül 02](../02-prompt-engineering/README.md)

**Temperature** - Çıktı rastgeleliğini kontrol eder. Düşük=belirleyici, yüksek=yaratıcı.

**Vector Database** - Embeddingler için özel veritabanı. [Modül 03](../03-rag/README.md)

**Zero-Shot Learning** - Örnek olmadan görev yapma. [Modül 02](../02-prompt-engineering/README.md)

## Prompt Mühendisliği - [Modül 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Daha iyi doğruluk için adım adım akıl yürütme.

**Constrained Output** - Belirli format veya yapı zorunluluğu.

**High Eagerness** - GPT-5’te kapsamlı akıl yürütme deseni.

**Low Eagerness** - GPT-5’te hızlı cevap deseni.

**Multi-Turn Conversation** - Değişimler arasında bağlamı koruma.

**Role-Based Prompting** - Sistem mesajlarıyla model kişiliği belirleme.

**Self-Reflection** - Modelin çıktısını değerlendirmesi ve geliştirmesi.

**Structured Analysis** - Sabit değerlendirme çerçevesi.

**Task Execution Pattern** - Planla → Uygula → Özetle.

## RAG (Retrieval-Augmented Generation) - [Modül 03](../03-rag/README.md)

**Document Processing Pipeline** - Yükle → parçalara ayır → embed et → depola.

**In-Memory Embedding Store** - Test için kalıcı olmayan depolama.

**RAG** - Yanıtları temellendirmek için getirme ile üretimi birleştirir.

**Similarity Score** - Anlamsal benzerlik ölçüsü (0-1).

**Source Reference** - Getirilen içeriğin meta verisi.

## Ajanlar ve Araçlar - [Modül 04](../04-tools/README.md)

**@Tool Annotation** - Java metodlarını AI tarafından çağrılabilir araç olarak işaretler.

**ReAct Pattern** - Düşün → Hareket et → Gözlemle → Tekrarla.

**Session Management** - Farklı kullanıcılar için ayrı bağlamlar.

**Tool** - AI ajanının çağırabileceği fonksiyon.

**Tool Description** - Araç amacı ve parametrelerinin dokümantasyonu.

## Model Bağlam Protokolü (MCP) - [Modül 05](../05-mcp/README.md)

**Docker Transport** - Docker konteynerinde MCP sunucusu.

**MCP** - AI uygulamalarını dış araçlara bağlamak için standart.

**MCP Client** - MCP sunucularına bağlanan uygulama.

**MCP Server** - Araçları MCP üzerinden sunan servis.

**Server-Sent Events (SSE)** - HTTP üzerinden sunucudan istemciye akış.

**Stdio Transport** - Sunucunun stdin/stdout ile alt süreç olarak çalışması.

**Streamable HTTP Transport** - Gerçek zamanlı iletişim için SSE destekli HTTP.

**Tool Discovery** - İstemcinin sunucudan mevcut araçları sorgulaması.

## Azure Hizmetleri - [Modül 01](../01-introduction/README.md)

**Azure AI Search** - Vektör özellikli bulut araması. [Modül 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure kaynaklarını dağıtır.

**Azure OpenAI** - Microsoft’un kurumsal AI servisi.

**Bicep** - Azure altyapı kodlama dili. [Altyapı Kılavuzu](../01-introduction/infra/README.md)

**Deployment Name** - Azure’daki model dağıtımı için isim.

**GPT-5** - Düşünme kontrolü olan en yeni OpenAI modeli. [Modül 02](../02-prompt-engineering/README.md)

## Test ve Geliştirme - [Test Kılavuzu](TESTING.md)

**Dev Container** - Konteynerize geliştirme ortamı. [Yapılandırma](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Ücretsiz AI model oyun alanı. [Modül 00](../00-quick-start/README.md)

**In-Memory Testing** - Bellek içi depolama ile test.

**Integration Testing** - Gerçek altyapı ile test.

**Maven** - Java derleme otomasyon aracı.

**Mockito** - Java taklit (mock) çerçevesi.

**Spring Boot** - Java uygulama çatısı. [Modül 01](../01-introduction/README.md)

**Testcontainers** - Testlerde Docker konteynerleri.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Feragatname**:  
Bu belge, AI çeviri servisi [Co-op Translator](https://github.com/Azure/co-op-translator) kullanılarak çevrilmiştir. Doğruluk için çaba göstersek de, otomatik çevirilerin hatalar veya yanlışlıklar içerebileceğini lütfen unutmayın. Orijinal belge, kendi dilinde yetkili kaynak olarak kabul edilmelidir. Kritik bilgiler için profesyonel insan çevirisi önerilir. Bu çevirinin kullanımı sonucu oluşabilecek yanlış anlamalar veya yorum hatalarından sorumlu değiliz.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->