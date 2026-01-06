<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:21:34+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ms"
}
-->
# Kamus Istilah LangChain4j

## Senarai Kandungan

- [Konsep Teras](../../../docs)
- [Komponen LangChain4j](../../../docs)
- [Konsep AI/ML](../../../docs)
- [Guardrails](../../../docs)
- [Kejuruteraan Prompt](../../../docs)
- [RAG (Penjanaan Dengan Pengambilan)](../../../docs)
- [Ejen dan Alat](../../../docs)
- [Modul Agentic](../../../docs)
- [Protokol Konteks Model (MCP)](../../../docs)
- [Perkhidmatan Azure](../../../docs)
- [Ujian dan Pembangunan](../../../docs)

Rujukan pantas untuk terma dan konsep yang digunakan sepanjang kursus.

## Konsep Teras

**Ejen AI** - Sistem yang menggunakan AI untuk berfikir dan bertindak secara autonomi. [Modul 04](../04-tools/README.md)

**Rantai** - Urutan operasi di mana output menjadi input untuk langkah seterusnya.

**Chunking** - Memecah dokumen menjadi bahagian yang lebih kecil. Lazim: 300-500 token dengan lapisan. [Modul 03](../03-rag/README.md)

**Tetingkap Konteks** - Maksimum token yang boleh diproses oleh model. GPT-5: 400K token.

**Embedding** - Vektor numerik yang mewakili makna teks. [Modul 03](../03-rag/README.md)

**Panggilan Fungsi** - Model menjana permintaan berstruktur untuk memanggil fungsi luaran. [Modul 04](../04-tools/README.md)

**Halusinasi** - Apabila model menghasilkan maklumat yang salah tetapi kelihatan munasabah.

**Prompt** - Input teks kepada model bahasa. [Modul 02](../02-prompt-engineering/README.md)

**Carian Semantik** - Carian berdasarkan makna menggunakan embedding, bukan kata kunci. [Modul 03](../03-rag/README.md)

**Bersifat Stateful vs Stateless** - Stateless: tiada memori. Stateful: mengekalkan sejarah perbualan. [Modul 01](../01-introduction/README.md)

**Token** - Unit teks asas yang diproses oleh model. Mempengaruhi kos dan had. [Modul 01](../01-introduction/README.md)

**Rantai Alat** - Pelaksanaan berurutan alat di mana output memberi maklumat kepada panggilan seterusnya. [Modul 04](../04-tools/README.md)

## Komponen LangChain4j

**AiServices** - Membuat antaramuka perkhidmatan AI yang selamat jenis.

**OpenAiOfficialChatModel** - Klien bersatu untuk model OpenAI dan Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Membuat embedding menggunakan klien OpenAI Rasmi (menyokong OpenAI dan Azure OpenAI).

**ChatModel** - Antaramuka teras untuk model bahasa.

**ChatMemory** - Mengekalkan sejarah perbualan.

**ContentRetriever** - Mencari bahagian dokumen yang relevan untuk RAG.

**DocumentSplitter** - Memecah dokumen menjadi kepingan.

**EmbeddingModel** - Menukar teks kepada vektor numerik.

**EmbeddingStore** - Menyimpan dan mengambil embedding.

**MessageWindowChatMemory** - Mengekalkan tetingkap gelongsor mesej terkini.

**PromptTemplate** - Membuat prompt boleh guna semula dengan tempat letak `{{variable}}`.

**TextSegment** - Kepingan teks dengan metadata. Digunakan dalam RAG.

**ToolExecutionRequest** - Mewakili permintaan pelaksanaan alat.

**UserMessage / AiMessage / SystemMessage** - Jenis mesej perbualan.

## Konsep AI/ML

**Pembelajaran Few-Shot** - Memberi contoh dalam prompt. [Modul 02](../02-prompt-engineering/README.md)

**Model Bahasa Besar (LLM)** - Model AI yang dilatih pada data teks yang luas.

**Usaha Penalaran** - Parameter GPT-5 yang mengawal kedalaman pemikiran. [Modul 02](../02-prompt-engineering/README.md)

**Temperatur** - Mengawal random output. Rendah=deterministik, tinggi=kreatif.

**Pangkalan Data Vektor** - Pangkalan data khas untuk embedding. [Modul 03](../03-rag/README.md)

**Pembelajaran Zero-Shot** - Melakukan tugasan tanpa contoh. [Modul 02](../02-prompt-engineering/README.md)

## Guardrails - [Modul 00](../00-quick-start/README.md)

**Pertahanan Bertingkat** - Pendekatan keselamatan berlapis yang menggabungkan guardrails tahap aplikasi dengan penapis keselamatan penyedia.

**Halangan Keras** - Penyedia memberi ralat HTTP 400 untuk pelanggaran kandungan serius.

**InputGuardrail** - Antaramuka LangChain4j untuk mengesahkan input pengguna sebelum sampai ke LLM. Menjimatkan kos dan kelewatan dengan menghalang prompt berbahaya awal.

**InputGuardrailResult** - Jenis pulangan untuk pengesahan guardrail: `success()` atau `fatal("reason")`.

**OutputGuardrail** - Antaramuka untuk mengesahkan respons AI sebelum dikembalikan kepada pengguna.

**Penapis Keselamatan Penyedia** - Penapis kandungan terbina dalam dari penyedia AI (contoh, Model GitHub) yang mengesan pelanggaran pada tahap API.

**Penolakan Lembut** - Model menolak menjawab dengan sopan tanpa memberi ralat.

## Kejuruteraan Prompt - [Modul 02](../02-prompt-engineering/README.md)

**Rantai Pemikiran** - Penalaran langkah demi langkah untuk ketepatan lebih baik.

**Output Terhad** - Mewajibkan format atau struktur khusus.

**Keghairahan Tinggi** - Corak GPT-5 untuk penalaran mendalam.

**Keghairahan Rendah** - Corak GPT-5 untuk jawapan cepat.

**Perbualan Multi-Turn** - Mengekalkan konteks sepanjang pertukaran.

**Prompt Berdasarkan Peranan** - Menetapkan persona model melalui mesej sistem.

**Refleksi Diri** - Model menilai dan memperbaiki outputnya.

**Analisis Berstruktur** - Rangka kerja penilaian tetap.

**Corak Pelaksanaan Tugasan** - Rancang → Laksanakan → Rumuskan.

## RAG (Penjanaan Dengan Pengambilan) - [Modul 03](../03-rag/README.md)

**Rangkaian Pemprosesan Dokumen** - Muat → pecah → membuat embedding → simpan.

**Kedai Embedding Dalam Memori** - Penyimpanan tidak kekal untuk ujian.

**RAG** - Menggabungkan pengambilan dengan penjanaan untuk membumi respons.

**Skor Kesamaan** - Ukuran (0-1) kesamaan semantik.

**Rujukan Sumber** - Metadata mengenai kandungan yang diambil.

## Ejen dan Alat - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Menandakan kaedah Java sebagai alat yang boleh dipanggil AI.

**Corak ReAct** - Berfikir → Bertindak → Memerhati → Ulang.

**Pengurusan Sesi** - Konteks berasingan untuk pengguna berbeza.

**Alat** - Fungsi yang boleh dipanggil ejen AI.

**Penerangan Alat** - Dokumentasi tujuan dan parameter alat.

## Modul Agentic - [Modul 05](../05-mcp/README.md)

**@Agent Annotation** - Menandakan antaramuka sebagai ejen AI dengan definisi tingkah laku deklaratif.

**Pendengar Ejen** - Hook untuk memantau pelaksanaan ejen melalui `beforeAgentInvocation()` dan `afterAgentInvocation()`.

**Skop Agentic** - Memori bersama di mana ejen menyimpan output menggunakan `outputKey` untuk ejen seterusnya gunakan.

**AgenticServices** - Kilang untuk membuat ejen menggunakan `agentBuilder()` dan `supervisorBuilder()`.

**Aliran Kerja Bersyarat** - Laluan berdasarkan syarat ke ejen pakar berbeza.

**Manusia-dalam-Laluan** - Corak aliran kerja menambah titik pemeriksaan manusia untuk kelulusan atau semakan kandungan.

**langchain4j-agentic** - Kebergantungan Maven untuk binaan ejen deklaratif (eksperimen).

**Aliran Kerja Gelung** - Ulang pelaksanaan ejen sehingga syarat tercapai (contoh: skor kualiti ≥ 0.8).

**outputKey** - Parameter anotasi ejen yang menentukan tempat penyimpanan keputusan dalam Skop Agentic.

**Aliran Kerja Selari** - Jalankan beberapa ejen serentak untuk tugasan bebas.

**Strategi Respons** - Cara penyelia membentuk jawapan akhir: TERAKHIR, RUMUSAN, atau BERKEDUDUKAN.

**Aliran Kerja Berurutan** - Laksanakan ejen mengikut susunan di mana output mengalir ke langkah seterusnya.

**Corak Ejen Penyelia** - Corak agentic lanjutan di mana LLM penyelia menentukan secara dinamik ejen sub yang dipanggil.

## Protokol Konteks Model (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** - Kebergantungan Maven untuk integrasi MCP dalam LangChain4j.

**MCP** - Protokol Konteks Model: standard untuk menyambungkan aplikasi AI kepada alat luaran. Bina sekali, guna di mana-mana.

**Klien MCP** - Aplikasi yang menyambung ke pelayan MCP untuk mencari dan menggunakan alat.

**Pelayan MCP** - Perkhidmatan yang mendedahkan alat melalui MCP dengan penerangan jelas dan skema parameter.

**McpToolProvider** - Komponen LangChain4j yang membungkus alat MCP untuk digunakan dalam perkhidmatan AI dan ejen.

**McpTransport** - Antaramuka untuk komunikasi MCP. Implementasi termasuk Stdio dan HTTP.

**Pengangkutan Stdio** - Pengangkutan proses tempatan melalui stdin/stdout. Berguna untuk akses sistem fail atau alat baris perintah.

**StdioMcpTransport** - Implementasi LangChain4j yang memulakan pelayan MCP sebagai subprocess.

**Penemuan Alat** - Klien bertanya kepada pelayan mengenai alat tersedia dengan penerangan dan skema.

## Perkhidmatan Azure - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Carian awan dengan keupayaan vektor. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Menyebarkan sumber Azure.

**Azure OpenAI** - Perkhidmatan AI perusahaan Microsoft.

**Bicep** - Bahasa infrastruktur-sbg-kod Azure. [Panduan Infrastruktur](../01-introduction/infra/README.md)

**Nama Penyebaran** - Nama untuk penyebaran model di Azure.

**GPT-5** - Model OpenAI terkini dengan kawalan penalaran. [Modul 02](../02-prompt-engineering/README.md)

## Ujian dan Pembangunan - [Panduan Ujian](TESTING.md)

**Dev Container** - Persekitaran pembangunan berkontena. [Konfigurasi](../../../.devcontainer/devcontainer.json)

**Model GitHub** - Tapak ujian model AI percuma. [Modul 00](../00-quick-start/README.md)

**Ujian Dalam Memori** - Ujian dengan penyimpanan dalam memori.

**Ujian Integrasi** - Ujian dengan infrastruktur sebenar.

**Maven** - Alat automasi binaan Java.

**Mockito** - Rangka kerja pemalsuan Java.

**Spring Boot** - Rangka kerja aplikasi Java. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan perkhidmatan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Walaupun kami berusaha untuk ketepatan, sila ambil perhatian bahawa terjemahan automatik mungkin mengandungi kesilapan atau ketidaktepatan. Dokumen asal dalam bahasa asalnya hendaklah dianggap sumber yang sahih. Untuk maklumat penting, terjemahan profesional oleh manusia adalah disyorkan. Kami tidak bertanggungjawab atas sebarang salah faham atau salah tafsir yang timbul daripada penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->