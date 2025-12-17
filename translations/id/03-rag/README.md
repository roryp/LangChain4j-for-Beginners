<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f538a51cfd13147d40d84e936a0f485c",
  "translation_date": "2025-12-13T17:10:35+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "id"
}
-->
# Modul 03: RAG (Retrieval-Augmented Generation)

## Daftar Isi

- [Apa yang Akan Anda Pelajari](../../../03-rag)
- [Prasyarat](../../../03-rag)
- [Memahami RAG](../../../03-rag)
- [Cara Kerjanya](../../../03-rag)
  - [Pemrosesan Dokumen](../../../03-rag)
  - [Membuat Embedding](../../../03-rag)
  - [Pencarian Semantik](../../../03-rag)
  - [Generasi Jawaban](../../../03-rag)
- [Menjalankan Aplikasi](../../../03-rag)
- [Menggunakan Aplikasi](../../../03-rag)
  - [Unggah Dokumen](../../../03-rag)
  - [Ajukan Pertanyaan](../../../03-rag)
  - [Periksa Referensi Sumber](../../../03-rag)
  - [Bereksperimen dengan Pertanyaan](../../../03-rag)
- [Konsep Kunci](../../../03-rag)
  - [Strategi Chunking](../../../03-rag)
  - [Skor Kemiripan](../../../03-rag)
  - [Penyimpanan Dalam Memori](../../../03-rag)
  - [Manajemen Jendela Konteks](../../../03-rag)
- [Kapan RAG Penting](../../../03-rag)
- [Langkah Selanjutnya](../../../03-rag)

## Apa yang Akan Anda Pelajari

Dalam modul sebelumnya, Anda belajar bagaimana melakukan percakapan dengan AI dan menyusun prompt Anda secara efektif. Namun ada keterbatasan mendasar: model bahasa hanya tahu apa yang mereka pelajari selama pelatihan. Mereka tidak bisa menjawab pertanyaan tentang kebijakan perusahaan Anda, dokumentasi proyek Anda, atau informasi apa pun yang tidak mereka latih.

RAG (Retrieval-Augmented Generation) memecahkan masalah ini. Alih-alih mencoba mengajarkan model informasi Anda (yang mahal dan tidak praktis), Anda memberinya kemampuan untuk mencari melalui dokumen Anda. Ketika seseorang mengajukan pertanyaan, sistem menemukan informasi relevan dan menyertakannya dalam prompt. Model kemudian menjawab berdasarkan konteks yang diambil tersebut.

Pikirkan RAG sebagai memberikan model sebuah perpustakaan referensi. Ketika Anda mengajukan pertanyaan, sistem:

1. **Pertanyaan Pengguna** - Anda mengajukan pertanyaan  
2. **Embedding** - Mengubah pertanyaan Anda menjadi vektor  
3. **Pencarian Vektor** - Menemukan potongan dokumen yang mirip  
4. **Perakitan Konteks** - Menambahkan potongan relevan ke prompt  
5. **Respon** - LLM menghasilkan jawaban berdasarkan konteks  

Ini mendasari jawaban model pada data nyata Anda, bukan hanya mengandalkan pengetahuan pelatihan atau membuat jawaban.

<img src="../../../translated_images/rag-architecture.ccb53b71a6ce407fa8a6394c7a747eb9ad40f6334b4c217be0439d700f22bbcc.id.png" alt="RAG Architecture" width="800"/>

*Alur kerja RAG - dari pertanyaan pengguna ke pencarian semantik hingga generasi jawaban kontekstual*

## Prasyarat

- Modul 01 selesai (sumber daya Azure OpenAI sudah dideploy)  
- File `.env` di direktori root dengan kredensial Azure (dibuat oleh `azd up` di Modul 01)  

> **Catatan:** Jika Anda belum menyelesaikan Modul 01, ikuti instruksi deployment di sana terlebih dahulu.

## Cara Kerjanya

**Pemrosesan Dokumen** - [DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

Saat Anda mengunggah dokumen, sistem memecahnya menjadi potongan-potongan kecil — bagian yang lebih kecil yang muat dengan nyaman dalam jendela konteks model. Potongan-potongan ini sedikit tumpang tindih agar konteks di batas tidak hilang.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```
  
> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) dan tanyakan:  
> - "Bagaimana LangChain4j memecah dokumen menjadi potongan dan mengapa tumpang tindih penting?"  
> - "Berapa ukuran potongan optimal untuk berbagai jenis dokumen dan mengapa?"  
> - "Bagaimana menangani dokumen dalam berbagai bahasa atau dengan format khusus?"

**Membuat Embedding** - [LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Setiap potongan diubah menjadi representasi numerik yang disebut embedding — pada dasarnya sidik jari matematis yang menangkap makna teks. Teks yang mirip menghasilkan embedding yang mirip.

```java
@Bean
public EmbeddingModel embeddingModel() {
    return OpenAiOfficialEmbeddingModel.builder()
        .baseUrl(azureOpenAiEndpoint)
        .apiKey(azureOpenAiKey)
        .modelName(azureEmbeddingDeploymentName)
        .build();
}

EmbeddingStore<TextSegment> embeddingStore = 
    new InMemoryEmbeddingStore<>();
```
  
<img src="../../../translated_images/vector-embeddings.2ef7bdddac79a327ad9e3e46cde9a86f5eeefbeb3edccd387e33018c1671cecd.id.png" alt="Vector Embeddings Space" width="800"/>

*Dokumen direpresentasikan sebagai vektor di ruang embedding - konten serupa berkumpul bersama*

**Pencarian Semantik** - [RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Ketika Anda mengajukan pertanyaan, pertanyaan Anda juga menjadi embedding. Sistem membandingkan embedding pertanyaan Anda dengan semua embedding potongan dokumen. Sistem menemukan potongan dengan makna paling mirip — bukan hanya kata kunci yang cocok, tapi kemiripan semantik sebenarnya.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```
  
> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) dan tanyakan:  
> - "Bagaimana pencarian kemiripan bekerja dengan embedding dan apa yang menentukan skornya?"  
> - "Ambang kemiripan apa yang harus saya gunakan dan bagaimana pengaruhnya terhadap hasil?"  
> - "Bagaimana menangani kasus ketika tidak ada dokumen relevan yang ditemukan?"

**Generasi Jawaban** - [RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Potongan paling relevan disertakan dalam prompt ke model. Model membaca potongan tersebut dan menjawab pertanyaan Anda berdasarkan informasi itu. Ini mencegah halusinasi — model hanya bisa menjawab dari apa yang ada di depannya.

## Menjalankan Aplikasi

**Verifikasi deployment:**

Pastikan file `.env` ada di direktori root dengan kredensial Azure (dibuat selama Modul 01):  
```bash
cat ../.env  # Harus menampilkan AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```
  
**Mulai aplikasi:**

> **Catatan:** Jika Anda sudah menjalankan semua aplikasi menggunakan `./start-all.sh` dari Modul 01, modul ini sudah berjalan di port 8081. Anda bisa melewati perintah start di bawah dan langsung buka http://localhost:8081.

**Opsi 1: Menggunakan Spring Boot Dashboard (Direkomendasikan untuk pengguna VS Code)**

Dev container menyertakan ekstensi Spring Boot Dashboard, yang menyediakan antarmuka visual untuk mengelola semua aplikasi Spring Boot. Anda dapat menemukannya di Activity Bar di sisi kiri VS Code (cari ikon Spring Boot).

Dari Spring Boot Dashboard, Anda dapat:  
- Melihat semua aplikasi Spring Boot yang tersedia di workspace  
- Memulai/menghentikan aplikasi dengan satu klik  
- Melihat log aplikasi secara real-time  
- Memantau status aplikasi  

Cukup klik tombol play di sebelah "rag" untuk memulai modul ini, atau mulai semua modul sekaligus.

<img src="../../../translated_images/dashboard.fbe6e28bf4267ffe4f95a708ecd46e78f69fd46a562d2a766e73c98fe0f53922.id.png" alt="Spring Boot Dashboard" width="400"/>

**Opsi 2: Menggunakan skrip shell**

Mulai semua aplikasi web (modul 01-04):

**Bash:**  
```bash
cd ..  # Dari direktori root
./start-all.sh
```
  
**PowerShell:**  
```powershell
cd ..  # Dari direktori root
.\start-all.ps1
```
  
Atau mulai hanya modul ini:

**Bash:**  
```bash
cd 03-rag
./start.sh
```
  
**PowerShell:**  
```powershell
cd 03-rag
.\start.ps1
```
  
Kedua skrip secara otomatis memuat variabel lingkungan dari file `.env` root dan akan membangun JAR jika belum ada.

> **Catatan:** Jika Anda ingin membangun semua modul secara manual sebelum memulai:  
>  
> **Bash:**  
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
  
> **PowerShell:**  
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
  
Buka http://localhost:8081 di browser Anda.

**Untuk menghentikan:**

**Bash:**  
```bash
./stop.sh  # Hanya modul ini
# Atau
cd .. && ./stop-all.sh  # Semua modul
```
  
**PowerShell:**  
```powershell
.\stop.ps1  # Hanya modul ini
# Atau
cd ..; .\stop-all.ps1  # Semua modul
```
  
## Menggunakan Aplikasi

Aplikasi menyediakan antarmuka web untuk mengunggah dokumen dan mengajukan pertanyaan.

<a href="images/rag-homepage.png"><img src="../../../translated_images/rag-homepage.d90eb5ce1b3caa94987b4fa2923d3cb884a67987cf2f994ca53756c6586a93b1.id.png" alt="RAG Application Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Antarmuka aplikasi RAG - unggah dokumen dan ajukan pertanyaan*

**Unggah Dokumen**

Mulailah dengan mengunggah dokumen — file TXT paling cocok untuk pengujian. Sebuah `sample-document.txt` disediakan di direktori ini yang berisi informasi tentang fitur LangChain4j, implementasi RAG, dan praktik terbaik — sempurna untuk menguji sistem.

Sistem memproses dokumen Anda, memecahnya menjadi potongan, dan membuat embedding untuk setiap potongan. Ini terjadi secara otomatis saat Anda mengunggah.

**Ajukan Pertanyaan**

Sekarang ajukan pertanyaan spesifik tentang isi dokumen. Cobalah sesuatu yang faktual dan jelas dinyatakan dalam dokumen. Sistem mencari potongan relevan, menyertakannya dalam prompt, dan menghasilkan jawaban.

**Periksa Referensi Sumber**

Perhatikan setiap jawaban menyertakan referensi sumber dengan skor kemiripan. Skor ini (0 sampai 1) menunjukkan seberapa relevan setiap potongan dengan pertanyaan Anda. Skor lebih tinggi berarti kecocokan lebih baik. Ini memungkinkan Anda memverifikasi jawaban terhadap materi sumber.

<a href="images/rag-query-results.png"><img src="../../../translated_images/rag-query-results.6d69fcec5397f3558c788388bb395191616dad4c7c0417f1a68bd18590ad0a0e.id.png" alt="RAG Query Results" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Hasil kueri menampilkan jawaban dengan referensi sumber dan skor relevansi*

**Bereksperimen dengan Pertanyaan**

Coba berbagai jenis pertanyaan:  
- Fakta spesifik: "Apa topik utama?"  
- Perbandingan: "Apa perbedaan antara X dan Y?"  
- Ringkasan: "Ringkas poin-poin utama tentang Z"  

Perhatikan bagaimana skor relevansi berubah berdasarkan seberapa baik pertanyaan Anda cocok dengan isi dokumen.

## Konsep Kunci

**Strategi Chunking**

Dokumen dibagi menjadi potongan 300 token dengan tumpang tindih 30 token. Keseimbangan ini memastikan setiap potongan memiliki cukup konteks untuk bermakna sekaligus cukup kecil agar beberapa potongan bisa dimasukkan dalam satu prompt.

**Skor Kemiripan**

Skor berkisar dari 0 sampai 1:  
- 0.7-1.0: Sangat relevan, cocok tepat  
- 0.5-0.7: Relevan, konteks baik  
- Di bawah 0.5: Disaring, terlalu tidak mirip  

Sistem hanya mengambil potongan di atas ambang minimum untuk memastikan kualitas.

**Penyimpanan Dalam Memori**

Modul ini menggunakan penyimpanan dalam memori untuk kesederhanaan. Saat Anda memulai ulang aplikasi, dokumen yang diunggah hilang. Sistem produksi menggunakan database vektor persisten seperti Qdrant atau Azure AI Search.

**Manajemen Jendela Konteks**

Setiap model memiliki jendela konteks maksimum. Anda tidak bisa memasukkan semua potongan dari dokumen besar. Sistem mengambil N potongan paling relevan teratas (default 5) agar tetap dalam batas sambil menyediakan cukup konteks untuk jawaban akurat.

## Kapan RAG Penting

**Gunakan RAG ketika:**  
- Menjawab pertanyaan tentang dokumen milik pribadi  
- Informasi sering berubah (kebijakan, harga, spesifikasi)  
- Akurasi memerlukan atribusi sumber  
- Konten terlalu besar untuk dimasukkan dalam satu prompt  
- Anda butuh jawaban yang dapat diverifikasi dan berdasar

**Jangan gunakan RAG ketika:**  
- Pertanyaan memerlukan pengetahuan umum yang sudah dimiliki model  
- Data real-time diperlukan (RAG bekerja pada dokumen yang diunggah)  
- Konten cukup kecil untuk dimasukkan langsung dalam prompt

## Langkah Selanjutnya

**Modul Berikutnya:** [04-tools - AI Agents with Tools](../04-tools/README.md)

---

**Navigasi:** [← Sebelumnya: Modul 02 - Prompt Engineering](../02-prompt-engineering/README.md) | [Kembali ke Utama](../README.md) | [Berikutnya: Modul 04 - Tools →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan layanan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Meskipun kami berupaya untuk mencapai akurasi, harap diingat bahwa terjemahan otomatis mungkin mengandung kesalahan atau ketidakakuratan. Dokumen asli dalam bahasa aslinya harus dianggap sebagai sumber yang sahih. Untuk informasi penting, disarankan menggunakan terjemahan profesional oleh manusia. Kami tidak bertanggung jawab atas kesalahpahaman atau penafsiran yang keliru yang timbul dari penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->