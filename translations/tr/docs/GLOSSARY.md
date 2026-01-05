<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T00:38:08+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "tr"
}
-->
# LangChain4j Sözlüğü

## İçindekiler

- [Temel Kavramlar](../../../docs)
- [LangChain4j Bileşenleri](../../../docs)
- [Yapay Zeka/ML Kavramları](../../../docs)
- [İstem Mühendisliği](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Ajanlar ve Araçlar](../../../docs)
- [Model Bağlam Protokolü (MCP)](../../../docs)
- [Azure Hizmetleri](../../../docs)
- [Test ve Geliştirme](../../../docs)

Kurs boyunca kullanılan terimler ve kavramlar için hızlı başvuru.

## Core Concepts

**AI Agent** - Yapay zekayı kullanarak özerk şekilde akıl yürüten ve hareket eden sistem. [Module 04](../04-tools/README.md)

**Chain** - Çıktının bir sonraki adıma girdi olduğu işlem dizisi.

**Chunking** - Belgeleri daha küçük parçalara ayırma. Tipik: 300-500 token, örtüşme ile. [Module 03](../03-rag/README.md)

**Context Window** - Bir modelin işleyebileceği maksimum token sayısı. GPT-5: 400K token.

**Embeddings** - Metnin anlamını temsil eden sayısal vektörler. [Module 03](../03-rag/README.md)

**Function Calling** - Modelin dış fonksiyonları çağırmak için yapılandırılmış istekler üretmesi. [Module 04](../04-tools/README.md)

**Hallucination** - Modellerin yanlış fakat inandırıcı bilgiler üretmesi.

**Prompt** - Bir dil modeline verilen metin girdisi. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Anahtar kelime yerine gömülü vektörleri kullanarak anlam üzerinden arama. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Durumsuz: hafıza yok. Durumlu: konuşma geçmişini tutar. [Module 01](../01-introduction/README.md)

**Tokens** - Modellerin işlediği temel metin birimleri. Maliyetleri ve sınırları etkiler. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Çıktının sonraki çağrıyı bilgilendirdiği ardışık araç yürütme. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Tip güvenli AI hizmet arayüzleri oluşturur.

**OpenAiOfficialChatModel** - OpenAI ve Azure OpenAI modelleri için birleşik istemci.

**OpenAiOfficialEmbeddingModel** - OpenAI Official istemci kullanarak gömüler oluşturur (hem OpenAI hem Azure OpenAI'i destekler).

**ChatModel** - Dil modelleri için temel arayüz.

**ChatMemory** - Konuşma geçmişini saklar.

**ContentRetriever** - RAG için ilgili belge parçalarını bulur.

**DocumentSplitter** - Belgeleri parçalara böler.

**EmbeddingModel** - Metni sayısal vektörlere dönüştürür.

**EmbeddingStore** - Gömüleri depolar ve alır.

**MessageWindowChatMemory** - Son mesajların kayan penceresini tutar.

**PromptTemplate** - `{{variable}}` yer tutucularıyla yeniden kullanılabilir istemler oluşturur.

**TextSegment** - Meta verili metin parçası. RAG'de kullanılır.

**ToolExecutionRequest** - Araç yürütme isteğini temsil eder.

**UserMessage / AiMessage / SystemMessage** - Konuşma mesajı türleri.

## AI/ML Concepts

**Few-Shot Learning** - İstemlerde örnekler sağlama. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Geniş metin verileri üzerinde eğitilmiş yapay zeka modelleri.

**Reasoning Effort** - Düşünme derinliğini kontrol eden GPT-5 parametresi. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Çıktı rastgeleliğini kontrol eder. Düşük=deterministik, yüksek=yaratıcı.

**Vector Database** - Gömüler için özelleşmiş veritabanı. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Örnek olmadan görevleri yerine getirme. [Module 02](../02-prompt-engineering/README.md)

## İstem Mühendisliği - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Daha iyi doğruluk için adım adım akıl yürütme.

**Constrained Output** - Belirli bir format veya yapı zorunluluğu.

**High Eagerness** - Detaylı akıl yürütme için GPT-5 deseni.

**Low Eagerness** - Hızlı cevaplar için GPT-5 deseni.

**Multi-Turn Conversation** - Turlar arasında bağlamı koruma.

**Role-Based Prompting** - Sistem mesajlarıyla model personası belirleme.

**Self-Reflection** - Modelin çıktısını değerlendirip iyileştirmesi.

**Structured Analysis** - Sabit değerlendirme çerçevesi.

**Task Execution Pattern** - Planla → Yürüt → Özetle.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Yükle → parçalara ayır → göm → depola.

**In-Memory Embedding Store** - Test için kalıcı olmayan depolama.

**RAG** - Getirme ile üretimi birleştirerek yanıtları sağlamlaştırır.

**Similarity Score** - Anlamsal benzerliğin (0-1) ölçüsü.

**Source Reference** - Getirilen içeriğe dair meta veriler.

## Ajanlar ve Araçlar - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Java yöntemlerini yapay zekâ tarafından çağrılabilir araçlar olarak işaretler.

**ReAct Pattern** - Akıl Yürüt → Hareket Et → Gözlemle → Tekrar et.

**Session Management** - Farklı kullanıcılar için ayrı bağlamlar.

**Tool** - Bir yapay zeka ajanının çağırabileceği işlev.

**Tool Description** - Araç amacı ve parametrelerinin belgelenmesi.

## Model Bağlam Protokolü (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - Yapay zeka uygulamalarını harici araçlara bağlamak için standart.

**MCP Client** - MCP sunucularına bağlanan uygulama.

**MCP Server** - Araçları MCP üzerinden sunan hizmet.

**Stdio Transport** - Sunucunun stdin/stdout üzerinden alt süreç olarak çalışması.

**Tool Discovery** - İstemcinin kullanılabilir araçlar için sunucuya sorgu göndermesi.

## Azure Hizmetleri - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Vektör özelliklerine sahip bulut araması. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure kaynaklarını dağıtır.

**Azure OpenAI** - Microsoft'un kurumsal yapay zeka hizmeti.

**Bicep** - Azure altyapı-olarak-kod dili. [Altyapı Kılavuzu](../01-introduction/infra/README.md)

**Deployment Name** - Azure'da model dağıtımı için ad.

**GPT-5** - Düşünme kontrolü olan en yeni OpenAI modeli. [Module 02](../02-prompt-engineering/README.md)

## Test ve Geliştirme - [Test Rehberi](TESTING.md)

**Dev Container** - Konteynerleştirilmiş geliştirme ortamı. [Yapılandırma](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Ücretsiz yapay zeka modeli deneme alanı. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Bellek içi depolamayla test.

**Integration Testing** - Gerçek altyapıyla test.

**Maven** - Java derleme otomasyon aracı.

**Mockito** - Java mocking çerçevesi.

**Spring Boot** - Java uygulama çerçevesi. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Feragatname**:
Bu belge, yapay zeka çeviri hizmeti [Co-op Translator](https://github.com/Azure/co-op-translator) kullanılarak çevrilmiştir. Doğruluğa özen göstersek de, otomatik çevirilerin hatalar veya yanlışlıklar içerebileceğini lütfen unutmayın. Orijinal belge, kaynak dilindeki hâli yetkili kaynak olarak kabul edilmelidir. Kritik bilgiler için profesyonel bir insan çevirisi önerilir. Bu çevirinin kullanımı sonucu ortaya çıkabilecek herhangi bir yanlış anlama veya yanlış yorumlamadan sorumlu değiliz.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->