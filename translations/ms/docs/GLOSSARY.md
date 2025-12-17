<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:14:30+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ms"
}
-->
# Kamus LangChain4j

## Jadual Kandungan

- [Konsep Teras](../../../docs)
- [Komponen LangChain4j](../../../docs)
- [Konsep AI/ML](../../../docs)
- [Kejuruteraan Prompt](../../../docs)
- [RAG (Penjanaan Dipertingkatkan Pengambilan)](../../../docs)
- [Ejen dan Alat](../../../docs)
- [Protokol Konteks Model (MCP)](../../../docs)
- [Perkhidmatan Azure](../../../docs)
- [Ujian dan Pembangunan](../../../docs)

Rujukan pantas untuk istilah dan konsep yang digunakan sepanjang kursus.

## Konsep Teras

**Ejen AI** - Sistem yang menggunakan AI untuk berfikir dan bertindak secara autonomi. [Modul 04](../04-tools/README.md)

**Rantaian** - Urutan operasi di mana output menjadi input untuk langkah seterusnya.

**Pecahan** - Memecahkan dokumen kepada bahagian yang lebih kecil. Lazim: 300-500 token dengan pertindihan. [Modul 03](../03-rag/README.md)

**Tetingkap Konteks** - Maksimum token yang boleh diproses oleh model. GPT-5: 400K token.

**Embedding** - Vektor berangka yang mewakili makna teks. [Modul 03](../03-rag/README.md)

**Panggilan Fungsi** - Model menghasilkan permintaan berstruktur untuk memanggil fungsi luaran. [Modul 04](../04-tools/README.md)

**Halusinasi** - Apabila model menghasilkan maklumat yang salah tetapi kelihatan munasabah.

**Prompt** - Input teks kepada model bahasa. [Modul 02](../02-prompt-engineering/README.md)

**Carian Semantik** - Carian berdasarkan makna menggunakan embedding, bukan kata kunci. [Modul 03](../03-rag/README.md)

**Berkeadaan vs Tanpa Keadaan** - Tanpa keadaan: tiada memori. Berkeadaan: mengekalkan sejarah perbualan. [Modul 01](../01-introduction/README.md)

**Token** - Unit asas teks yang diproses model. Mempengaruhi kos dan had. [Modul 01](../01-introduction/README.md)

**Rantaian Alat** - Pelaksanaan alat secara berurutan di mana output memaklumkan panggilan seterusnya. [Modul 04](../04-tools/README.md)

## Komponen LangChain4j

**AiServices** - Mewujudkan antara muka perkhidmatan AI yang selamat jenis.

**OpenAiOfficialChatModel** - Klien bersatu untuk model OpenAI dan Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Mewujudkan embedding menggunakan klien Rasmi OpenAI (menyokong OpenAI dan Azure OpenAI).

**ChatModel** - Antara muka teras untuk model bahasa.

**ChatMemory** - Mengekalkan sejarah perbualan.

**ContentRetriever** - Mencari bahagian dokumen yang relevan untuk RAG.

**DocumentSplitter** - Memecahkan dokumen kepada bahagian.

**EmbeddingModel** - Menukar teks kepada vektor berangka.

**EmbeddingStore** - Menyimpan dan mengambil embedding.

**MessageWindowChatMemory** - Mengekalkan tetingkap gelangsar mesej terkini.

**PromptTemplate** - Mewujudkan prompt boleh guna semula dengan tempat letak `{{variable}}`.

**TextSegment** - Bahagian teks dengan metadata. Digunakan dalam RAG.

**ToolExecutionRequest** - Mewakili permintaan pelaksanaan alat.

**UserMessage / AiMessage / SystemMessage** - Jenis mesej perbualan.

## Konsep AI/ML

**Pembelajaran Few-Shot** - Memberi contoh dalam prompt. [Modul 02](../02-prompt-engineering/README.md)

**Model Bahasa Besar (LLM)** - Model AI yang dilatih pada data teks yang banyak.

**Usaha Penalaran** - Parameter GPT-5 yang mengawal kedalaman pemikiran. [Modul 02](../02-prompt-engineering/README.md)

**Suhu** - Mengawal kebarangkalian output. Rendah=deterministik, tinggi=kreatif.

**Pangkalan Data Vektor** - Pangkalan data khusus untuk embedding. [Modul 03](../03-rag/README.md)

**Pembelajaran Zero-Shot** - Melakukan tugas tanpa contoh. [Modul 02](../02-prompt-engineering/README.md)

## Kejuruteraan Prompt - [Modul 02](../02-prompt-engineering/README.md)

**Rantaian Pemikiran** - Penalaran langkah demi langkah untuk ketepatan lebih baik.

**Output Terhad** - Memaksa format atau struktur tertentu.

**Keghairahan Tinggi** - Corak GPT-5 untuk penalaran menyeluruh.

**Keghairahan Rendah** - Corak GPT-5 untuk jawapan cepat.

**Perbualan Berbilang Giliran** - Mengekalkan konteks merentasi pertukaran.

**Prompt Berdasarkan Peranan** - Menetapkan persona model melalui mesej sistem.

**Refleksi Diri** - Model menilai dan memperbaiki outputnya.

**Analisis Berstruktur** - Rangka kerja penilaian tetap.

**Corak Pelaksanaan Tugas** - Rancang → Laksanakan → Rumuskan.

## RAG (Penjanaan Dipertingkatkan Pengambilan) - [Modul 03](../03-rag/README.md)

**Saluran Pemprosesan Dokumen** - Muat → pecah → embed → simpan.

**Penyimpanan Embedding Dalam Memori** - Penyimpanan tidak kekal untuk ujian.

**RAG** - Menggabungkan pengambilan dengan penjanaan untuk mengukuhkan jawapan.

**Skor Kesamaan** - Ukuran (0-1) kesamaan semantik.

**Rujukan Sumber** - Metadata tentang kandungan yang diambil.

## Ejen dan Alat - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Menandakan kaedah Java sebagai alat boleh dipanggil AI.

**Corak ReAct** - Berfikir → Bertindak → Memerhati → Ulang.

**Pengurusan Sesi** - Konteks berasingan untuk pengguna berbeza.

**Alat** - Fungsi yang boleh dipanggil oleh ejen AI.

**Penerangan Alat** - Dokumentasi tujuan dan parameter alat.

## Protokol Konteks Model (MCP) - [Modul 05](../05-mcp/README.md)

**Pengangkutan Docker** - Pelayan MCP dalam bekas Docker.

**MCP** - Standard untuk menyambungkan aplikasi AI ke alat luaran.

**Klien MCP** - Aplikasi yang menyambung ke pelayan MCP.

**Pelayan MCP** - Perkhidmatan yang mendedahkan alat melalui MCP.

**Server-Sent Events (SSE)** - Penstriman dari pelayan ke klien melalui HTTP.

**Pengangkutan Stdio** - Pelayan sebagai proses anak melalui stdin/stdout.

**Pengangkutan HTTP Boleh Alir** - HTTP dengan SSE untuk komunikasi masa nyata.

**Penemuan Alat** - Klien bertanya pelayan untuk alat yang tersedia.

## Perkhidmatan Azure - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Carian awan dengan keupayaan vektor. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Menggulung sumber Azure.

**Azure OpenAI** - Perkhidmatan AI perusahaan Microsoft.

**Bicep** - Bahasa infrastruktur sebagai kod Azure. [Panduan Infrastruktur](../01-introduction/infra/README.md)

**Nama Penggolekan** - Nama untuk penggolekan model di Azure.

**GPT-5** - Model OpenAI terkini dengan kawalan penalaran. [Modul 02](../02-prompt-engineering/README.md)

## Ujian dan Pembangunan - [Panduan Ujian](TESTING.md)

**Bekas Pembangunan** - Persekitaran pembangunan berbekas. [Konfigurasi](../../../.devcontainer/devcontainer.json)

**Model GitHub** - Tempat bermain model AI percuma. [Modul 00](../00-quick-start/README.md)

**Ujian Dalam Memori** - Ujian dengan penyimpanan dalam memori.

**Ujian Integrasi** - Ujian dengan infrastruktur sebenar.

**Maven** - Alat automasi binaan Java.

**Mockito** - Rangka kerja mocking Java.

**Spring Boot** - Rangka kerja aplikasi Java. [Modul 01](../01-introduction/README.md)

**Testcontainers** - Bekas Docker dalam ujian.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan perkhidmatan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Walaupun kami berusaha untuk ketepatan, sila ambil maklum bahawa terjemahan automatik mungkin mengandungi kesilapan atau ketidaktepatan. Dokumen asal dalam bahasa asalnya harus dianggap sebagai sumber yang sahih. Untuk maklumat penting, terjemahan profesional oleh manusia adalah disyorkan. Kami tidak bertanggungjawab atas sebarang salah faham atau salah tafsir yang timbul daripada penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->