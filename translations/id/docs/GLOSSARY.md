<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T03:04:14+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "id"
}
-->
# LangChain4j Glosarium

## Daftar Isi

- [Konsep Inti](../../../docs)
- [Komponen LangChain4j](../../../docs)
- [Konsep AI/ML](../../../docs)
- [Rekayasa Prompt](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agen dan Alat](../../../docs)
- [Protokol Konteks Model (MCP)](../../../docs)
- [Layanan Azure](../../../docs)
- [Pengujian dan Pengembangan](../../../docs)

Referensi cepat untuk istilah dan konsep yang digunakan sepanjang kursus.

## Konsep Inti

**AI Agent** - Sistem yang menggunakan AI untuk bernalar dan bertindak secara otonom. [Module 04](../04-tools/README.md)

**Chain** - Urutan operasi di mana keluaran memasok langkah berikutnya.

**Chunking** - Memecah dokumen menjadi potongan-potongan lebih kecil. Biasanya: 300-500 token dengan tumpang tindih. [Module 03](../03-rag/README.md)

**Context Window** - Maksimum token yang dapat diproses model. GPT-5: 400K token.

**Embeddings** - Vektor numerik yang mewakili makna teks. [Module 03](../03-rag/README.md)

**Function Calling** - Model menghasilkan permintaan terstruktur untuk memanggil fungsi eksternal. [Module 04](../04-tools/README.md)

**Hallucination** - Ketika model menghasilkan informasi yang salah tetapi tampak masuk akal.

**Prompt** - Masukan teks ke model bahasa. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Pencarian berdasarkan makna menggunakan embeddings, bukan kata kunci. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: tanpa memori. Stateful: mempertahankan riwayat percakapan. [Module 01](../01-introduction/README.md)

**Tokens** - Unit teks dasar yang diproses model. Mempengaruhi biaya dan batasan. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Eksekusi alat secara berurutan di mana keluaran memberi informasi pemanggilan berikutnya. [Module 04](../04-tools/README.md)

## Komponen LangChain4j

**AiServices** - Membuat antarmuka layanan AI yang type-safe.

**OpenAiOfficialChatModel** - Klien terpadu untuk model OpenAI dan Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Membuat embeddings menggunakan klien OpenAI Official (mendukung OpenAI dan Azure OpenAI).

**ChatModel** - Antarmuka inti untuk model bahasa.

**ChatMemory** - Memelihara riwayat percakapan.

**ContentRetriever** - Menemukan potongan dokumen relevan untuk RAG.

**DocumentSplitter** - Memecah dokumen menjadi potongan.

**EmbeddingModel** - Mengonversi teks menjadi vektor numerik.

**EmbeddingStore** - Menyimpan dan mengambil embeddings.

**MessageWindowChatMemory** - Mempertahankan jendela geser dari pesan terbaru.

**PromptTemplate** - Membuat prompt yang dapat digunakan ulang dengan placeholder `{{variable}}`.

**TextSegment** - Potongan teks dengan metadata. Digunakan dalam RAG.

**ToolExecutionRequest** - Mewakili permintaan eksekusi alat.

**UserMessage / AiMessage / SystemMessage** - Jenis pesan percakapan.

## Konsep AI/ML

**Few-Shot Learning** - Memberikan contoh dalam prompt. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Model AI yang dilatih pada data teks yang sangat besar.

**Reasoning Effort** - Parameter GPT-5 yang mengontrol kedalaman pemikiran. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Mengontrol randomness keluaran. Rendah=deterministik, tinggi=kreatif.

**Vector Database** - Basis data khusus untuk embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Melakukan tugas tanpa contoh. [Module 02](../02-prompt-engineering/README.md)

## Rekayasa Prompt - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Penalaran langkah-demi-langkah untuk akurasi lebih baik.

**Constrained Output** - Memaksakan format atau struktur tertentu.

**High Eagerness** - Pola GPT-5 untuk penalaran menyeluruh.

**Low Eagerness** - Pola GPT-5 untuk jawaban cepat.

**Multi-Turn Conversation** - Mempertahankan konteks di antara pertukaran.

**Role-Based Prompting** - Mengatur persona model melalui pesan sistem.

**Self-Reflection** - Model mengevaluasi dan memperbaiki keluarannya.

**Structured Analysis** - Kerangka evaluasi tetap.

**Task Execution Pattern** - Rencana → Eksekusi → Ringkasan.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Muat → pecah → embed → simpan.

**In-Memory Embedding Store** - Penyimpanan non-persisten untuk pengujian.

**RAG** - Menggabungkan pengambilan dengan generasi untuk memberi dasar pada respons.

**Similarity Score** - Ukuran (0-1) dari kesamaan semantik.

**Source Reference** - Metadata tentang konten yang diambil.

## Agen dan Alat - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Menandai metode Java sebagai alat yang dapat dipanggil AI.

**ReAct Pattern** - Bernalar → Bertindak → Mengamati → Ulangi.

**Session Management** - Konteks terpisah untuk pengguna berbeda.

**Tool** - Fungsi yang dapat dipanggil agen AI.

**Tool Description** - Dokumentasi tujuan dan parameter alat.

## Protokol Konteks Model (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - Standar untuk menghubungkan aplikasi AI ke alat eksternal.

**MCP Client** - Aplikasi yang terhubung ke server MCP.

**MCP Server** - Layanan yang mengekspos alat melalui MCP.

**Stdio Transport** - Server sebagai subprocess melalui stdin/stdout.

**Tool Discovery** - Klien menanyakan server untuk alat yang tersedia.

## Layanan Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Pencarian cloud dengan kemampuan vektor. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Mendeploy sumber daya Azure.

**Azure OpenAI** - Layanan AI enterprise dari Microsoft.

**Bicep** - Bahasa infrastructure-as-code untuk Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Nama untuk deployment model di Azure.

**GPT-5** - Model OpenAI terbaru dengan kontrol penalaran. [Module 02](../02-prompt-engineering/README.md)

## Pengujian dan Pengembangan - [Testing Guide](TESTING.md)

**Dev Container** - Lingkungan pengembangan terkontainerisasi. [Konfigurasi](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Playground model AI gratis. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Pengujian dengan penyimpanan in-memory.

**Integration Testing** - Pengujian dengan infrastruktur nyata.

**Maven** - Alat otomatisasi build Java.

**Mockito** - Kerangka kerja mocking untuk Java.

**Spring Boot** - Kerangka aplikasi Java. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Penafian:
Dokumen ini telah diterjemahkan menggunakan layanan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Meskipun kami berupaya mencapai akurasi, harap diperhatikan bahwa terjemahan otomatis mungkin mengandung kesalahan atau ketidakakuratan. Dokumen asli dalam bahasa aslinya harus dianggap sebagai sumber yang berwenang. Untuk informasi yang bersifat kritis, disarankan menggunakan jasa penerjemah profesional. Kami tidak bertanggung jawab atas kesalahpahaman atau salah tafsir yang timbul dari penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->