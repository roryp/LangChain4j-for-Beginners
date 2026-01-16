<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "22b5d7c8d7585325e38b37fd29eafe25",
  "translation_date": "2026-01-06T00:19:28+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "id"
}
-->
# Modul 00: Memulai dengan Cepat

## Daftar Isi

- [Pendahuluan](../../../00-quick-start)
- [Apa itu LangChain4j?](../../../00-quick-start)
- [Ketergantungan LangChain4j](../../../00-quick-start)
- [Prasyarat](../../../00-quick-start)
- [Pengaturan](../../../00-quick-start)
  - [1. Dapatkan Token GitHub Anda](../../../00-quick-start)
  - [2. Atur Token Anda](../../../00-quick-start)
- [Jalankan Contoh](../../../00-quick-start)
  - [1. Obrolan Dasar](../../../00-quick-start)
  - [2. Pola Prompt](../../../00-quick-start)
  - [3. Pemanggilan Fungsi](../../../00-quick-start)
  - [4. Tanya Jawab Dokumen (RAG)](../../../00-quick-start)
  - [5. AI yang Bertanggung Jawab](../../../00-quick-start)
- [Apa yang Ditunjukkan Setiap Contoh](../../../00-quick-start)
- [Langkah Berikutnya](../../../00-quick-start)
- [Pemecahan Masalah](../../../00-quick-start)

## Pendahuluan

Quickstart ini dimaksudkan untuk membuat Anda dapat menggunakan LangChain4j secepat mungkin. Ini mencakup dasar-dasar membangun aplikasi AI dengan LangChain4j dan Model GitHub. Pada modul berikutnya Anda akan menggunakan Azure OpenAI dengan LangChain4j untuk membangun aplikasi yang lebih canggih.

## Apa itu LangChain4j?

LangChain4j adalah perpustakaan Java yang menyederhanakan pembangunan aplikasi bertenaga AI. Alih-alih berurusan dengan klien HTTP dan parsing JSON, Anda bekerja dengan API Java yang bersih.

"Chain" dalam LangChain merujuk pada menggabungkan beberapa komponen bersama — Anda mungkin menghubungkan prompt ke model ke parser, atau menggabungkan beberapa panggilan AI di mana output satu menjadi input berikutnya. Quickstart ini fokus pada dasar-dasar sebelum menjelajahi rantai yang lebih kompleks.

<img src="../../../translated_images/id/langchain-concept.ad1fe6cf063515e1.png" alt="LangChain4j Chaining Concept" width="800"/>

*Menggabungkan komponen di LangChain4j - blok bangunan terhubung untuk membuat alur kerja AI yang kuat*

Kita akan menggunakan tiga komponen inti:

**ChatLanguageModel** - Antarmuka untuk interaksi dengan model AI. Panggil `model.chat("prompt")` dan dapatkan string balasan. Kita menggunakan `OpenAiOfficialChatModel` yang bekerja dengan endpoint kompatibel OpenAI seperti Model GitHub.

**AiServices** - Membuat antarmuka layanan AI yang tipe-aman. Definisikan metode, beri anotasi dengan `@Tool`, dan LangChain4j menangani orkestrasi. AI secara otomatis memanggil metode Java Anda saat dibutuhkan.

**MessageWindowChatMemory** - Mempertahankan riwayat percakapan. Tanpa ini, setiap permintaan terpisah. Dengan ini, AI mengingat pesan sebelumnya dan mempertahankan konteks selama beberapa putaran.

<img src="../../../translated_images/id/architecture.eedc993a1c576839.png" alt="LangChain4j Architecture" width="800"/>

*Arsitektur LangChain4j - komponen inti bekerja bersama untuk menggerakkan aplikasi AI Anda*

## Ketergantungan LangChain4j

Quickstart ini menggunakan dua ketergantungan Maven dalam [`pom.xml`](../../../00-quick-start/pom.xml):

```xml
<!-- Core LangChain4j library -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>

<!-- OpenAI integration (works with GitHub Models) -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

Modul `langchain4j-open-ai-official` menyediakan kelas `OpenAiOfficialChatModel` yang menghubungkan ke API kompatibel OpenAI. Model GitHub menggunakan format API yang sama, jadi tidak perlu adaptor khusus - cukup arahkan URL dasar ke `https://models.github.ai/inference`.

## Prasyarat

**Menggunakan Dev Container?** Java dan Maven sudah terpasang. Anda hanya perlu Token Akses Pribadi GitHub.

**Pengembangan Lokal:**
- Java 21+, Maven 3.9+
- Token Akses Pribadi GitHub (instruksi di bawah)

> **Catatan:** Modul ini menggunakan `gpt-4.1-nano` dari Model GitHub. Jangan mengubah nama model di kode — sudah dikonfigurasi untuk bekerja dengan model yang tersedia di GitHub.

## Pengaturan

### 1. Dapatkan Token GitHub Anda

1. Buka [Pengaturan GitHub → Token Akses Pribadi](https://github.com/settings/personal-access-tokens)
2. Klik "Generate new token"
3. Beri nama yang deskriptif (misal, "Demo LangChain4j")
4. Atur masa berlaku (7 hari disarankan)
5. Di bawah "Account permissions", cari "Models" dan atur ke "Read-only"
6. Klik "Generate token"
7. Salin dan simpan token Anda - Anda tidak akan melihatnya lagi

### 2. Atur Token Anda

**Opsi 1: Menggunakan VS Code (Disarankan)**

Jika menggunakan VS Code, tambahkan token Anda ke file `.env` di akar proyek:

Jika file `.env` tidak ada, salin `.env.example` ke `.env` atau buat file `.env` baru di akar proyek.

**Contoh file `.env`:**
```bash
# Di /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Kemudian Anda cukup klik kanan pada file demo apa pun (misal, `BasicChatDemo.java`) di Explorer dan pilih **"Run Java"** atau gunakan konfigurasi jalur dari panel Run and Debug.

**Opsi 2: Menggunakan Terminal**

Atur token sebagai variabel lingkungan:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Jalankan Contoh

**Menggunakan VS Code:** Klik kanan file demo apa pun di Explorer dan pilih **"Run Java"**, atau gunakan konfigurasi jalur di panel Run and Debug (pastikan Anda sudah menambahkan token ke file `.env` terlebih dahulu).

**Menggunakan Maven:** Alternatifnya, Anda bisa menjalankan dari baris perintah:

### 1. Obrolan Dasar

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Pola Prompt

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Menunjukkan zero-shot, few-shot, chain-of-thought, dan prompt berbasis peran.

### 3. Pemanggilan Fungsi

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI secara otomatis memanggil metode Java Anda saat diperlukan.

### 4. Tanya Jawab Dokumen (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Ajukan pertanyaan tentang konten di `document.txt`.

### 5. AI yang Bertanggung Jawab

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

Lihat bagaimana filter keamanan AI memblokir konten berbahaya.

## Apa yang Ditunjukkan Setiap Contoh

**Obrolan Dasar** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Mulailah di sini untuk melihat LangChain4j pada tingkat paling sederhana. Anda akan membuat `OpenAiOfficialChatModel`, mengirim prompt dengan `.chat()`, dan mendapatkan balasan. Ini menunjukkan dasar: bagaimana menginisialisasi model dengan endpoint dan kunci API khusus. Setelah Anda memahami pola ini, semuanya dibangun di atasnya.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) dan tanyakan:
> - "Bagaimana cara saya beralih dari Model GitHub ke Azure OpenAI di kode ini?"
> - "Parameter lain apa yang bisa saya konfigurasikan di OpenAiOfficialChatModel.builder()?"
> - "Bagaimana saya menambahkan respons streaming alih-alih menunggu respons lengkap?"

**Rekayasa Prompt** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Sekarang Anda tahu cara berbicara dengan model, mari kita jelajahi apa yang Anda katakan padanya. Demo ini menggunakan pengaturan model yang sama tetapi menampilkan empat pola prompt yang berbeda. Coba prompt zero-shot untuk instruksi langsung, prompt few-shot yang belajar dari contoh, prompt chain-of-thought yang mengungkap langkah penalaran, dan prompt berbasis peran yang mengatur konteks. Anda akan melihat bagaimana model yang sama memberi hasil sangat berbeda berdasarkan bagaimana Anda menyusun permintaan.

```java
PromptTemplate template = PromptTemplate.from(
    "What's the best time to visit {{destination}} for {{activity}}?"
);

Prompt prompt = template.apply(Map.of(
    "destination", "Paris",
    "activity", "sightseeing"
));

String response = model.chat(prompt.text());
```

> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) dan tanyakan:
> - "Apa perbedaan antara zero-shot dan few-shot prompt, dan kapan sebaiknya saya gunakan masing-masing?"
> - "Bagaimana parameter temperature memengaruhi respons model?"
> - "Teknik apa saja untuk mencegah serangan injeksi prompt di produksi?"
> - "Bagaimana saya membuat objek PromptTemplate yang dapat digunakan ulang untuk pola umum?"

**Integrasi Alat** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Di sinilah LangChain4j menjadi kuat. Anda akan menggunakan `AiServices` untuk membuat asisten AI yang dapat memanggil metode Java Anda. Cukup anotasi metode dengan `@Tool("deskripsi")` dan LangChain4j mengatur sisanya - AI secara otomatis memutuskan kapan menggunakan setiap alat berdasarkan apa yang diminta pengguna. Ini menunjukkan pemanggilan fungsi, teknik kunci untuk membangun AI yang dapat mengambil tindakan, tidak hanya menjawab pertanyaan.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) dan tanyakan:
> - "Bagaimana anotasi @Tool bekerja dan apa yang dilakukan LangChain4j dengan itu di belakang layar?"
> - "Bisakah AI memanggil beberapa alat berturut-turut untuk memecahkan masalah kompleks?"
> - "Apa yang terjadi jika alat melemparkan pengecualian — bagaimana saya harus menangani kesalahan?"
> - "Bagaimana saya mengintegrasikan API nyata daripada contoh kalkulator ini?"

**Tanya Jawab Dokumen (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Di sini Anda akan melihat dasar RAG (retrieval-augmented generation). Alih-alih mengandalkan data pelatihan model, Anda memuat konten dari [`document.txt`](../../../00-quick-start/document.txt) dan menyertakannya dalam prompt. AI menjawab berdasarkan dokumen Anda, bukan pengetahuan umum. Ini adalah langkah pertama menuju membangun sistem yang dapat bekerja dengan data Anda sendiri.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Catatan:** Pendekatan sederhana ini memuat seluruh dokumen ke dalam prompt. Untuk file besar (>10KB), Anda akan melewati batas konteks. Modul 03 membahas pemecahan dan pencarian vektor untuk sistem RAG produksi.

> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) dan tanyakan:
> - "Bagaimana RAG mencegah halusinasi AI dibandingkan menggunakan data pelatihan model?"
> - "Apa bedanya pendekatan sederhana ini dengan menggunakan embedding vektor untuk pengambilan?"
> - "Bagaimana saya memperbesar ini untuk menangani banyak dokumen atau basis pengetahuan yang lebih besar?"
> - "Apa praktik terbaik untuk menyusun prompt agar AI hanya menggunakan konteks yang diberikan?"

**AI yang Bertanggung Jawab** - [ResponsibleAIDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)

Bangun keamanan AI dengan pertahanan berlapis. Demo ini menunjukkan dua lapisan perlindungan bekerja bersama:

**Bagian 1: Input Guardrails LangChain4j** - Memblokir prompt berbahaya sebelum mencapai LLM. Buat guardrails khusus yang memeriksa kata kunci atau pola terlarang. Ini dijalankan dalam kode Anda, sehingga cepat dan gratis.

```java
class DangerousContentGuardrail implements InputGuardrail {
    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        String text = userMessage.singleText().toLowerCase();
        if (text.contains("explosives")) {
            return fatal("Blocked: contains prohibited keyword");
        }
        return success();
    }
}
```

**Bagian 2: Filter Keamanan Provider** - Model GitHub memiliki filter bawaan yang menangkap apa yang mungkin terlewatkan oleh guardrails Anda. Anda akan melihat blok keras (error HTTP 400) untuk pelanggaran serius dan penolakan lunak di mana AI dengan sopan menolak.

> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`ResponsibleAIDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java) dan tanyakan:
> - "Apa itu InputGuardrail dan bagaimana saya membuatnya?"
> - "Apa bedanya blok keras dengan penolakan lunak?"
> - "Mengapa menggunakan guardrails dan filter provider sekaligus?"

## Langkah Berikutnya

**Modul Berikutnya:** [01-introduction - Memulai dengan LangChain4j dan gpt-5 di Azure](../01-introduction/README.md)

---

**Navigasi:** [← Kembali ke Utama](../README.md) | [Berikutnya: Modul 01 - Pendahuluan →](../01-introduction/README.md)

---

## Pemecahan Masalah

### Build Maven Pertama Kali

**Masalah**: `mvn clean compile` atau `mvn package` pertama kali memakan waktu lama (10-15 menit)

**Penyebab**: Maven perlu mengunduh semua ketergantungan proyek (Spring Boot, pustaka LangChain4j, SDK Azure, dll.) pada build pertama.

**Solusi**: Ini perilaku normal. Build berikutnya akan jauh lebih cepat karena ketergantungan sudah tersimpan di lokal. Waktu unduh tergantung kecepatan jaringan Anda.

### Sintaks Perintah Maven di PowerShell

**Masalah**: Perintah Maven gagal dengan error `Unknown lifecycle phase ".mainClass=..."`

**Penyebab**: PowerShell mengartikan `=` sebagai operator penugasan variabel, yang merusak sintaks properti Maven.
**Solusi**: Gunakan operator stop-parsing `--%` sebelum perintah Maven:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Operator `--%` memberitahu PowerShell untuk meneruskan semua argumen yang tersisa secara literal ke Maven tanpa interpretasi.

### Tampilan Emoji Windows PowerShell

**Masalah**: Tanggapan AI menampilkan karakter sampah (misalnya, `????` atau `â??`) alih-alih emoji di PowerShell

**Penyebab**: Encoding default PowerShell tidak mendukung emoji UTF-8

**Solusi**: Jalankan perintah ini sebelum menjalankan aplikasi Java:
```cmd
chcp 65001
```

Ini memaksa encoding UTF-8 di terminal. Sebagai alternatif, gunakan Windows Terminal yang memiliki dukungan Unicode lebih baik.

### Debugging Panggilan API

**Masalah**: Kesalahan autentikasi, batas kecepatan, atau tanggapan tak terduga dari model AI

**Solusi**: Contoh menyertakan `.logRequests(true)` dan `.logResponses(true)` untuk menampilkan panggilan API di konsol. Ini membantu memecahkan masalah kesalahan autentikasi, batas kecepatan, atau tanggapan tak terduga. Hapus flag ini di produksi untuk mengurangi gangguan log.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan layanan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Meskipun kami berupaya untuk akurasi, harap diingat bahwa terjemahan otomatis mungkin mengandung kesalahan atau ketidakakuratan. Dokumen asli dalam bahasa aslinya harus dianggap sebagai sumber yang sahih. Untuk informasi penting, disarankan menggunakan terjemahan profesional oleh manusia. Kami tidak bertanggung jawab atas kesalahpahaman atau salah tafsir yang timbul dari penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->