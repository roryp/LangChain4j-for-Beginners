<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:20:16+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "id"
}
-->
# Glosarium LangChain4j

## Daftar Isi

- [Konsep Inti](../../../docs)
- [Komponen LangChain4j](../../../docs)
- [Konsep AI/ML](../../../docs)
- [Guardrails](../../../docs)
- [Rekayasa Prompt](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agen dan Alat](../../../docs)
- [Modul Agentic](../../../docs)
- [Protokol Konteks Model (MCP)](../../../docs)
- [Layanan Azure](../../../docs)
- [Pengujian dan Pengembangan](../../../docs)

Referensi cepat untuk istilah dan konsep yang digunakan sepanjang kursus.

## Konsep Inti

**Agen AI** - Sistem yang menggunakan AI untuk bernalar dan bertindak secara otonom. [Modul 04](../04-tools/README.md)

**Rantai** - Urutan operasi di mana keluaran menjadi masukan untuk langkah berikutnya.

**Pecahan (Chunking)** - Memecah dokumen menjadi bagian-bagian lebih kecil. Umum: 300-500 token dengan tumpang tindih. [Modul 03](../03-rag/README.md)

**Jendela Konteks** - Maksimum token yang dapat diproses model. GPT-5: 400 ribu token.

**Embedding** - Vektor numerik yang merepresentasikan makna teks. [Modul 03](../03-rag/README.md)

**Pemanggilan Fungsi** - Model menghasilkan permintaan terstruktur untuk memanggil fungsi eksternal. [Modul 04](../04-tools/README.md)

**Halusinasi** - Ketika model menghasilkan informasi yang salah namun tampak masuk akal.

**Prompt** - Masukan teks ke model bahasa. [Modul 02](../02-prompt-engineering/README.md)

**Pencarian Semantik** - Pencarian berdasarkan makna menggunakan embedding, bukan kata kunci. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: tanpa memori. Stateful: mempertahankan riwayat percakapan. [Modul 01](../01-introduction/README.md)

**Token** - Unit dasar teks yang diproses model. Mempengaruhi biaya dan batasan. [Modul 01](../01-introduction/README.md)

**Pengurutan Alat (Tool Chaining)** - Eksekusi alat secara berurutan di mana keluaran memberi informasi pemanggilan berikutnya. [Modul 04](../04-tools/README.md)

## Komponen LangChain4j

**AiServices** - Membuat antarmuka layanan AI yang tipe aman.

**OpenAiOfficialChatModel** - Klien terpadu untuk model OpenAI dan Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Membuat embedding menggunakan klien resmi OpenAI (mendukung OpenAI dan Azure OpenAI).

**ChatModel** - Antarmuka inti untuk model bahasa.

**ChatMemory** - Memelihara riwayat percakapan.

**ContentRetriever** - Mencari potongan dokumen relevan untuk RAG.

**DocumentSplitter** - Memecah dokumen menjadi potongan.

**EmbeddingModel** - Mengubah teks menjadi vektor numerik.

**EmbeddingStore** - Menyimpan dan mengambil embedding.

**MessageWindowChatMemory** - Memelihara jendela geser dari pesan terbaru.

**PromptTemplate** - Membuat prompt yang dapat digunakan ulang dengan placeholder `{{variable}}`.

**TextSegment** - Potongan teks dengan metadata. Digunakan dalam RAG.

**ToolExecutionRequest** - Mewakili permintaan eksekusi alat.

**UserMessage / AiMessage / SystemMessage** - Jenis pesan percakapan.

## Konsep AI/ML

**Few-Shot Learning** - Memberikan contoh dalam prompt. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Model AI yang dilatih dengan data teks besar.

**Usaha Penalaran (Reasoning Effort)** - Parameter GPT-5 yang mengontrol kedalaman pemikiran. [Modul 02](../02-prompt-engineering/README.md)

**Temperatur** - Mengontrol tingkat keacakan keluaran. Rendah=deterministik, tinggi=kreatif.

**Database Vektor** - Basis data khusus untuk embedding. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Melakukan tugas tanpa contoh. [Modul 02](../02-prompt-engineering/README.md)

## Guardrails - [Modul 00](../00-quick-start/README.md)

**Pertahanan Berlapis (Defense in Depth)** - Pendekatan keamanan multi-lapisan yang menggabungkan guardrails tingkat aplikasi dengan filter keamanan penyedia.

**Blok Keras (Hard Block)** - Penyedia mengeluarkan error HTTP 400 untuk pelanggaran konten berat.

**InputGuardrail** - Antarmuka LangChain4j untuk memvalidasi masukan pengguna sebelum mencapai LLM. Menghemat biaya dan latensi dengan memblokir prompt berbahaya lebih awal.

**InputGuardrailResult** - Tipe hasil untuk validasi guardrail: `success()` atau `fatal("reason")`.

**OutputGuardrail** - Antarmuka untuk memvalidasi respon AI sebelum dikembalikan ke pengguna.

**Filter Keamanan Penyedia** - Filter konten bawaan dari penyedia AI (misal, GitHub Models) yang menangkap pelanggaran di tingkat API.

**Penolakan Halus (Soft Refusal)** - Model menolak menjawab dengan sopan tanpa melempar kesalahan.

## Rekayasa Prompt - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Penalaran langkah demi langkah untuk akurasi lebih baik.

**Output Terbatas (Constrained Output)** - Memaksakan format atau struktur tertentu.

**Keinginan Tinggi (High Eagerness)** - Pola GPT-5 untuk penalaran mendalam.

**Keinginan Rendah (Low Eagerness)** - Pola GPT-5 untuk jawaban cepat.

**Percakapan Multi-Turn** - Mempertahankan konteks antar pertukaran.

**Prompt Berbasis Peran** - Mengatur persona model melalui pesan sistem.

**Refleksi Diri (Self-Reflection)** - Model mengevaluasi dan meningkatkan keluarannya.

**Analisis Terstruktur** - Kerangka evaluasi tetap.

**Pola Eksekusi Tugas** - Rencana → Eksekusi → Ringkasan.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Proses Dokumen** - Muat → potong → embed → simpan.

**Penyimpanan Embedding In-Memory** - Penyimpanan non-persistent untuk pengujian.

**RAG** - Menggabungkan pencarian dengan generasi untuk memberi dasar pada respon.

**Skor Kesamaan** - Ukuran (0-1) dari kemiripan semantik.

**Referensi Sumber** - Metadata tentang konten yang diambil.

## Agen dan Alat - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Menandai metode Java sebagai alat yang dapat dipanggil AI.

**Pola ReAct** - Bernalar → Bertindak → Mengamati → Ulangi.

**Manajemen Sesi** - Konteks terpisah untuk pengguna berbeda.

**Alat (Tool)** - Fungsi yang dapat dipanggil agen AI.

**Deskripsi Alat** - Dokumentasi tujuan dan parameter alat.

## Modul Agentic - [Modul 05](../05-mcp/README.md)

**@Agent Annotation** - Menandai antarmuka sebagai agen AI dengan definisi perilaku deklaratif.

**Agent Listener** - Hook untuk memantau eksekusi agen via `beforeAgentInvocation()` dan `afterAgentInvocation()`.

**Agentic Scope** - Memori bersama di mana agen menyimpan keluaran menggunakan `outputKey` untuk digunakan agen berikutnya.

**AgenticServices** - Pabrik untuk membuat agen menggunakan `agentBuilder()` dan `supervisorBuilder()`.

**Alur Kerja Bersyarat** - Rute berdasarkan kondisi ke agen spesialis berbeda.

**Human-in-the-Loop** - Pola alur kerja yang menambahkan titik pemeriksaan manusia untuk persetujuan atau peninjauan konten.

**langchain4j-agentic** - Ketergantungan Maven untuk pembuatan agen deklaratif (eksperimental).

**Alur Kerja Berulang** - Mengulangi eksekusi agen sampai kondisi terpenuhi (misal, skor kualitas ≥ 0,8).

**outputKey** - Parameter anotasi agen yang menentukan di mana hasil disimpan dalam Agentic Scope.

**Alur Kerja Paralel** - Menjalankan beberapa agen secara bersamaan untuk tugas independen.

**Strategi Respon** - Cara supervisor membentuk jawaban akhir: LAST, SUMMARY, atau SCORED.

**Alur Kerja Berurutan** - Menjalankan agen secara berurutan dimana keluaran mengalir ke langkah berikutnya.

**Pola Agen Supervisor** - Pola agentic tingkat lanjut di mana LLM supervisor secara dinamis memutuskan sub-agen mana yang dipanggil.

## Protokol Konteks Model (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** - Ketergantungan Maven untuk integrasi MCP di LangChain4j.

**MCP** - Model Context Protocol: standar untuk menghubungkan aplikasi AI ke alat eksternal. Bangun sekali, gunakan di mana saja.

**Klien MCP** - Aplikasi yang terhubung ke server MCP untuk menemukan dan menggunakan alat.

**Server MCP** - Layanan yang membuka alat melalui MCP dengan deskripsi dan skema parameter yang jelas.

**McpToolProvider** - Komponen LangChain4j yang membungkus alat MCP untuk digunakan di layanan dan agen AI.

**McpTransport** - Antarmuka untuk komunikasi MCP. Implementasi termasuk Stdio dan HTTP.

**Transport Stdio** - Transport proses lokal via stdin/stdout. Berguna untuk akses sistem berkas atau alat baris perintah.

**StdioMcpTransport** - Implementasi LangChain4j yang menjalankan server MCP sebagai proses anak.

**Penemuan Alat** - Klien menanyakan server untuk alat tersedia dengan deskripsi dan skema.

## Layanan Azure - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Pencarian cloud dengan kemampuan vektor. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Mendeploy sumber daya Azure.

**Azure OpenAI** - Layanan AI perusahaan Microsoft.

**Bicep** - Bahasa infrastructure-as-code Azure. [Panduan Infrastruktur](../01-introduction/infra/README.md)

**Nama Deployment** - Nama untuk deployment model di Azure.

**GPT-5** - Model OpenAI terbaru dengan kontrol penalaran. [Modul 02](../02-prompt-engineering/README.md)

## Pengujian dan Pengembangan - [Panduan Pengujian](TESTING.md)

**Dev Container** - Lingkungan pengembangan yang dikontainerisasi. [Konfigurasi](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Taman bermain model AI gratis. [Modul 00](../00-quick-start/README.md)

**Pengujian In-Memory** - Pengujian dengan penyimpanan in-memory.

**Pengujian Integrasi** - Pengujian dengan infrastruktur nyata.

**Maven** - Alat otomasi build Java.

**Mockito** - Kerangka mocking Java.

**Spring Boot** - Kerangka aplikasi Java. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan layanan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Meskipun kami berusaha untuk memberikan terjemahan yang akurat, harap diperhatikan bahwa terjemahan otomatis mungkin mengandung kesalahan atau ketidakakuratan. Dokumen asli dalam bahasa aslinya harus dianggap sebagai sumber yang paling sahih. Untuk informasi yang penting, disarankan menggunakan jasa terjemahan profesional oleh manusia. Kami tidak bertanggung jawab atas kesalahpahaman atau salah tafsir yang timbul dari penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->