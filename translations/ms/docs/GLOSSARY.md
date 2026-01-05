<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T03:18:02+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ms"
}
-->
# LangChain4j Glosari

## Jadual Kandungan

- [Konsep Teras](../../../docs)
- [Komponen LangChain4j](../../../docs)
- [Konsep AI/ML](../../../docs)
- [Kejuruteraan Prompt](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Ejen dan Alat](../../../docs)
- [Protokol Konteks Model (MCP)](../../../docs)
- [Perkhidmatan Azure](../../../docs)
- [Pengujian dan Pembangunan](../../../docs)

Rujukan ringkas untuk istilah dan konsep yang digunakan sepanjang kursus.

## Konsep Teras

**Ejen AI** - Sistem yang menggunakan AI untuk membuat penalaran dan bertindak secara autonomi. [Modul 04](../04-tools/README.md)

**Rantaian** - Urutan operasi di mana keluaran digunakan sebagai input untuk langkah seterusnya.

**Chunking** - Memecahkan dokumen kepada bahagian lebih kecil. Lazimnya: 300-500 token dengan pertindihan. [Modul 03](../03-rag/README.md)

**Tingkap Konteks** - Jumlah maksimum token yang model boleh proses. GPT-5: 400K token.

**Embeddings** - Vektor berangka yang mewakili maksud teks. [Modul 03](../03-rag/README.md)

**Function Calling** - Model menjana permintaan berstruktur untuk memanggil fungsi luaran. [Modul 04](../04-tools/README.md)

**Hallucination** - Apabila model menjana maklumat yang tidak betul tetapi nampak munasabah.

**Prompt** - Input teks kepada model bahasa. [Modul 02](../02-prompt-engineering/README.md)

**Carian Semantik** - Carian berdasarkan makna menggunakan embeddings, bukan kata kunci. [Modul 03](../03-rag/README.md)

**Berstatus vs Tanpa Status** - Tanpa Status: tiada memori. Berstatus: mengekalkan sejarah perbualan. [Modul 01](../01-introduction/README.md)

**Token** - Unit asas teks yang diproses model. Mempengaruhi kos dan had. [Modul 01](../01-introduction/README.md)

**Rantaian Alat** - Pelaksanaan alat secara berurutan di mana keluaran memberi maklumat kepada panggilan seterusnya. [Modul 04](../04-tools/README.md)

## Komponen LangChain4j

**AiServices** - Mewujudkan antara muka perkhidmatan AI yang type-safe.

**OpenAiOfficialChatModel** - Klien bersatu untuk model OpenAI dan Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Mewujudkan embeddings menggunakan klien OpenAI Official (menyokong OpenAI dan Azure OpenAI).

**ChatModel** - Antara muka teras untuk model bahasa.

**ChatMemory** - Mengekalkan sejarah perbualan.

**ContentRetriever** - Mencari petak dokumen yang relevan untuk RAG.

**DocumentSplitter** - Memecah dokumen kepada petak-petak.

**EmbeddingModel** - Menukarkan teks kepada vektor berangka.

**EmbeddingStore** - Menyimpan dan mengambil embeddings.

**MessageWindowChatMemory** - Mengekalkan tingkap gelincir mesej terkini.

**PromptTemplate** - Mewujudkan prompt boleh guna semula dengan pemegang tempat `{{variable}}`.

**TextSegment** - Petak teks dengan metadata. Digunakan dalam RAG.

**ToolExecutionRequest** - Mewakili permintaan pelaksanaan alat.

**UserMessage / AiMessage / SystemMessage** - Jenis mesej perbualan.

## Konsep AI/ML

**Pembelajaran Few-Shot** - Memberi contoh dalam prompt. [Modul 02](../02-prompt-engineering/README.md)

**Model Bahasa Besar (LLM)** - Model AI yang dilatih pada jumlah teks yang besar.

**Usaha Penalaran** - Parameter GPT-5 yang mengawal kedalaman pemikiran. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Mengawal kebarangkalian keluaran. Rendah = deterministik, tinggi = kreatif.

**Pangkalan Data Vektor** - Pangkalan data khusus untuk embeddings. [Modul 03](../03-rag/README.md)

**Pembelajaran Zero-Shot** - Melaksanakan tugas tanpa contoh. [Modul 02](../02-prompt-engineering/README.md)

## Kejuruteraan Prompt - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Penalaran langkah demi langkah untuk ketepatan yang lebih baik.

**Keluaran Terhad** - Menguatkuasakan format atau struktur tertentu.

**High Eagerness** - Corak GPT-5 untuk penalaran menyeluruh.

**Low Eagerness** - Corak GPT-5 untuk jawapan cepat.

**Perbualan Berbilang Pusingan** - Mengekalkan konteks merentasi pertukaran.

**Prompt Berasaskan Peranan** - Menetapkan persona model melalui mesej sistem.

**Refleksi Diri** - Model menilai dan memperbaiki keluaran sendiri.

**Analisis Berstruktur** - Rangka penilaian tetap.

**Corak Pelaksanaan Tugas** - Rancang → Laksanakan → Rumuskan.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Saluran Pemprosesan Dokumen** - Muat → pecah → embedding → simpan.

**In-Memory Embedding Store** - Stor bukan kekal untuk pengujian.

**RAG** - Menggabungkan pemulihan dengan penjanaan untuk menyandarkan respons.

**Skor Kesamaan** - Ukuran (0-1) bagi persamaan semantik.

**Rujukan Sumber** - Metadata tentang kandungan yang diambil.

## Ejen dan Alat - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Menandakan kaedah Java sebagai alat yang boleh dipanggil oleh AI.

**Corak ReAct** - Fikir → Bertindak → Perhatikan → Ulang.

**Pengurusan Sesi** - Konteks berasingan untuk pengguna yang berbeza.

**Tool** - Fungsi yang boleh dipanggil oleh ejen AI.

**Penerangan Alat** - Dokumentasi tujuan alat dan parameternya.

## Protokol Konteks Model (MCP) - [Modul 05](../05-mcp/README.md)

**MCP** - Standard untuk menyambungkan aplikasi AI kepada alat luaran.

**MCP Client** - Aplikasi yang menyambung kepada server MCP.

**MCP Server** - Perkhidmatan yang mendedahkan alat melalui MCP.

**Stdio Transport** - Server sebagai proses subprocess melalui stdin/stdout.

**Penemuan Alat** - Client bertanya kepada server untuk alat yang tersedia.

## Perkhidmatan Azure - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Carian awan dengan kebolehan vektor. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Mengurus penerapan sumber Azure.

**Azure OpenAI** - Perkhidmatan AI perusahaan Microsoft.

**Bicep** - Bahasa infrastruktur-sebagai-kod untuk Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Nama Deployment** - Nama untuk penerapan model di Azure.

**GPT-5** - Model terkini OpenAI dengan kawalan penalaran. [Modul 02](../02-prompt-engineering/README.md)

## Pengujian dan Pembangunan - [Panduan Ujian](TESTING.md)

**Dev Container** - Persekitaran pembangunan bercontainer. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Kawasan permainan model AI percuma. [Modul 00](../00-quick-start/README.md)

**Ujian Dalam Memori** - Pengujian dengan stor dalam memori.

**Ujian Integrasi** - Pengujian dengan infrastruktur sebenar.

**Maven** - Alat automasi binaan Java.

**Mockito** - Rangka kerja mocking Java.

**Spring Boot** - Rangka kerja aplikasi Java. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Penafian:
Dokumen ini telah diterjemahkan menggunakan perkhidmatan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Walaupun kami berusaha untuk ketepatan, sila ambil perhatian bahawa terjemahan automatik mungkin mengandungi ralat atau ketidakakuratan. Dokumen asal dalam bahasa asalnya hendaklah dianggap sebagai sumber yang sahih. Untuk maklumat penting, disarankan mendapatkan terjemahan profesional oleh penterjemah manusia. Kami tidak bertanggungjawab terhadap sebarang salah faham atau salah tafsiran yang timbul daripada penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->