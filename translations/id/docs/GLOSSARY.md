<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:13:51+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "id"
}
-->
# Kamus LangChain4j

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

**AI Agent** - Sistem yang menggunakan AI untuk bernalar dan bertindak secara otonom. [Modul 04](../04-tools/README.md)

**Chain** - Urutan operasi di mana keluaran menjadi masukan untuk langkah berikutnya.

**Chunking** - Memecah dokumen menjadi bagian-bagian lebih kecil. Umum: 300-500 token dengan tumpang tindih. [Modul 03](../03-rag/README.md)

**Context Window** - Maksimum token yang dapat diproses model. GPT-5: 400K token.

**Embeddings** - Vektor numerik yang mewakili makna teks. [Modul 03](../03-rag/README.md)

**Function Calling** - Model menghasilkan permintaan terstruktur untuk memanggil fungsi eksternal. [Modul 04](../04-tools/README.md)

**Hallucination** - Ketika model menghasilkan informasi yang salah tetapi tampak masuk akal.

**Prompt** - Masukan teks ke model bahasa. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Pencarian berdasarkan makna menggunakan embeddings, bukan kata kunci. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: tanpa memori. Stateful: mempertahankan riwayat percakapan. [Modul 01](../01-introduction/README.md)

**Tokens** - Unit teks dasar yang diproses model. Mempengaruhi biaya dan batasan. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Eksekusi alat secara berurutan di mana keluaran memberi informasi panggilan berikutnya. [Modul 04](../04-tools/README.md)

## Komponen LangChain4j

**AiServices** - Membuat antarmuka layanan AI yang tipe-aman.

**OpenAiOfficialChatModel** - Klien terpadu untuk model OpenAI dan Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Membuat embeddings menggunakan klien OpenAI Official (mendukung OpenAI dan Azure OpenAI).

**ChatModel** - Antarmuka inti untuk model bahasa.

**ChatMemory** - Mempertahankan riwayat percakapan.

**ContentRetriever** - Menemukan potongan dokumen relevan untuk RAG.

**DocumentSplitter** - Memecah dokumen menjadi potongan-potongan.

**EmbeddingModel** - Mengubah teks menjadi vektor numerik.

**EmbeddingStore** - Menyimpan dan mengambil embeddings.

**MessageWindowChatMemory** - Mempertahankan jendela geser pesan terbaru.

**PromptTemplate** - Membuat prompt yang dapat digunakan ulang dengan placeholder `{{variable}}`.

**TextSegment** - Potongan teks dengan metadata. Digunakan dalam RAG.

**ToolExecutionRequest** - Mewakili permintaan eksekusi alat.

**UserMessage / AiMessage / SystemMessage** - Jenis pesan percakapan.

## Konsep AI/ML

**Few-Shot Learning** - Memberikan contoh dalam prompt. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Model AI yang dilatih pada data teks besar.

**Reasoning Effort** - Parameter GPT-5 yang mengontrol kedalaman pemikiran. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Mengontrol keacakan keluaran. Rendah=deterministik, tinggi=kreatif.

**Vector Database** - Basis data khusus untuk embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Melakukan tugas tanpa contoh. [Modul 02](../02-prompt-engineering/README.md)

## Rekayasa Prompt - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Penalaran langkah demi langkah untuk akurasi lebih baik.

**Constrained Output** - Memaksa format atau struktur tertentu.

**High Eagerness** - Pola GPT-5 untuk penalaran menyeluruh.

**Low Eagerness** - Pola GPT-5 untuk jawaban cepat.

**Multi-Turn Conversation** - Mempertahankan konteks antar pertukaran.

**Role-Based Prompting** - Menetapkan persona model melalui pesan sistem.

**Self-Reflection** - Model mengevaluasi dan memperbaiki keluarannya.

**Structured Analysis** - Kerangka evaluasi tetap.

**Task Execution Pattern** - Rencana → Eksekusi → Ringkasan.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Muat → pecah → embed → simpan.

**In-Memory Embedding Store** - Penyimpanan non-persisten untuk pengujian.

**RAG** - Menggabungkan pengambilan dengan generasi untuk mendasari respons.

**Similarity Score** - Ukuran (0-1) kesamaan semantik.

**Source Reference** - Metadata tentang konten yang diambil.

## Agen dan Alat - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Menandai metode Java sebagai alat yang dapat dipanggil AI.

**ReAct Pattern** - Bernalar → Bertindak → Mengamati → Ulangi.

**Session Management** - Konteks terpisah untuk pengguna berbeda.

**Tool** - Fungsi yang dapat dipanggil agen AI.

**Tool Description** - Dokumentasi tujuan dan parameter alat.

## Protokol Konteks Model (MCP) - [Modul 05](../05-mcp/README.md)

**Docker Transport** - Server MCP dalam kontainer Docker.

**MCP** - Standar untuk menghubungkan aplikasi AI ke alat eksternal.

**MCP Client** - Aplikasi yang terhubung ke server MCP.

**MCP Server** - Layanan yang mengekspos alat melalui MCP.

**Server-Sent Events (SSE)** - Streaming server ke klien melalui HTTP.

**Stdio Transport** - Server sebagai subprocess melalui stdin/stdout.

**Streamable HTTP Transport** - HTTP dengan SSE untuk komunikasi real-time.

**Tool Discovery** - Klien menanyakan server untuk alat yang tersedia.

## Layanan Azure - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Pencarian cloud dengan kemampuan vektor. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Mendeploy sumber daya Azure.

**Azure OpenAI** - Layanan AI perusahaan Microsoft.

**Bicep** - Bahasa infrastruktur sebagai kode Azure. [Panduan Infrastruktur](../01-introduction/infra/README.md)

**Deployment Name** - Nama untuk deployment model di Azure.

**GPT-5** - Model OpenAI terbaru dengan kontrol penalaran. [Modul 02](../02-prompt-engineering/README.md)

## Pengujian dan Pengembangan - [Panduan Pengujian](TESTING.md)

**Dev Container** - Lingkungan pengembangan terkontainerisasi. [Konfigurasi](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Playground model AI gratis. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Pengujian dengan penyimpanan dalam memori.

**Integration Testing** - Pengujian dengan infrastruktur nyata.

**Maven** - Alat otomatisasi build Java.

**Mockito** - Kerangka mocking Java.

**Spring Boot** - Kerangka aplikasi Java. [Modul 01](../01-introduction/README.md)

**Testcontainers** - Kontainer Docker dalam pengujian.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan layanan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Meskipun kami berusaha untuk akurasi, harap diingat bahwa terjemahan otomatis mungkin mengandung kesalahan atau ketidakakuratan. Dokumen asli dalam bahasa aslinya harus dianggap sebagai sumber yang sahih. Untuk informasi penting, disarankan menggunakan terjemahan profesional oleh manusia. Kami tidak bertanggung jawab atas kesalahpahaman atau penafsiran yang salah yang timbul dari penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->