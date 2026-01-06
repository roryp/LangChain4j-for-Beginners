<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "22b5d7c8d7585325e38b37fd29eafe25",
  "translation_date": "2026-01-06T00:25:01+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "ms"
}
-->
# Modul 00: Mula Cepat

## Jadual Kandungan

- [Pengenalan](../../../00-quick-start)
- [Apakah LangChain4j?](../../../00-quick-start)
- [Kebergantungan LangChain4j](../../../00-quick-start)
- [Prasyarat](../../../00-quick-start)
- [Persediaan](../../../00-quick-start)
  - [1. Dapatkan Token GitHub Anda](../../../00-quick-start)
  - [2. Tetapkan Token Anda](../../../00-quick-start)
- [Jalankan Contoh](../../../00-quick-start)
  - [1. Sembang Asas](../../../00-quick-start)
  - [2. Corak Arahan](../../../00-quick-start)
  - [3. Panggilan Fungsi](../../../00-quick-start)
  - [4. Soal Jawab Dokumen (RAG)](../../../00-quick-start)
  - [5. AI Bertanggungjawab](../../../00-quick-start)
- [Apa yang Ditunjukkan Setiap Contoh](../../../00-quick-start)
- [Langkah Seterusnya](../../../00-quick-start)
- [Penyelesaian Masalah](../../../00-quick-start)

## Pengenalan

Mula cepat ini bertujuan untuk menjadikan anda dapat menggunakan LangChain4j dengan cepat. Ia merangkumi asas membina aplikasi AI dengan LangChain4j dan Model GitHub. Dalam modul seterusnya, anda akan menggunakan Azure OpenAI dengan LangChain4j untuk membina aplikasi yang lebih maju.

## Apakah LangChain4j?

LangChain4j adalah perpustakaan Java yang memudahkan pembinaan aplikasi berkuasa AI. Daripada bergelut dengan klien HTTP dan parsing JSON, anda bekerja dengan API Java yang kemas.

"Rantai" dalam LangChain merujuk kepada menyambungkan beberapa komponen - anda mungkin menyambungkan arahan ke model ke parser, atau menyambungkan beberapa panggilan AI di mana satu output menjadi input seterusnya. Mula cepat ini memfokuskan kepada asas sebelum meneroka rantai yang lebih kompleks.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1.ms.png" alt="Konsep Rantaian LangChain4j" width="800"/>

*Menyambung komponen dalam LangChain4j - blok binaan menghubungkan untuk mencipta aliran kerja AI yang berkuasa*

Kami akan menggunakan tiga komponen teras:

**ChatLanguageModel** - Antara muka untuk interaksi model AI. Panggil `model.chat("prompt")` dan dapatkan rentetan respons. Kami menggunakan `OpenAiOfficialChatModel` yang berfungsi dengan titik akhir yang serasi OpenAI seperti Model GitHub.

**AiServices** - Membina antara muka perkhidmatan AI yang jenis selamat. Definisikan kaedah, beri anotasi dengan `@Tool`, dan LangChain4j menguruskan orkestrasi. AI secara automatik memanggil kaedah Java anda apabila diperlukan.

**MessageWindowChatMemory** - Menyimpan sejarah perbualan. Tanpanya, setiap permintaan bebas. Dengan ini, AI mengingati mesej lepas dan mengekalkan konteks merentas beberapa pusing.

<img src="../../../translated_images/architecture.eedc993a1c576839.ms.png" alt="Seni Bina LangChain4j" width="800"/>

*Seni bina LangChain4j - komponen teras bekerjasama untuk menggerakkan aplikasi AI anda*

## Kebergantungan LangChain4j

Mula cepat ini menggunakan dua kebergantungan Maven dalam [`pom.xml`](../../../00-quick-start/pom.xml):

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

Modul `langchain4j-open-ai-official` menyediakan kelas `OpenAiOfficialChatModel` yang menghubungkan kepada API serasi OpenAI. Model GitHub menggunakan format API yang sama, jadi tiada penyesuai khas diperlukan - cuma tunjuk URL asas ke `https://models.github.ai/inference`.

## Prasyarat

**Menggunakan Dev Container?** Java dan Maven sudah dipasang. Anda hanya perlukan Token Akses Peribadi GitHub.

**Pembangunan Tempatan:**
- Java 21+, Maven 3.9+
- Token Akses Peribadi GitHub (arahan di bawah)

> **Nota:** Modul ini menggunakan `gpt-4.1-nano` dari Model GitHub. Jangan ubah nama model dalam kod - ia disediakan untuk berfungsi dengan model yang tersedia di GitHub.

## Persediaan

### 1. Dapatkan Token GitHub Anda

1. Pergi ke [Tetapan GitHub → Token Akses Peribadi](https://github.com/settings/personal-access-tokens)
2. Klik "Hasilkan token baru"
3. Tetapkan nama yang menerangkan (contohnya, "Demo LangChain4j")
4. Tetapkan tamat tempoh (7 hari disyorkan)
5. Di bawah "Kebenaran Akaun", cari "Model" dan tetapkan kepada "Baca sahaja"
6. Klik "Hasilkan token"
7. Salin dan simpan token anda - anda tidak akan melihatnya lagi

### 2. Tetapkan Token Anda

**Pilihan 1: Menggunakan VS Code (Disyorkan)**

Jika anda menggunakan VS Code, tambah token anda ke fail `.env` dalam akar projek:

Jika fail `.env` tidak wujud, salin `.env.example` ke `.env` atau buat fail `.env` baru dalam akar projek.

**Contoh fail `.env`:**
```bash
# Dalam /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Kemudian anda boleh klik kanan di mana-mana fail demo (contohnya, `BasicChatDemo.java`) dalam Penjelajah dan pilih **"Run Java"** atau guna konfigurasi pelancaran dari panel Jalankan dan Debug.

**Pilihan 2: Menggunakan Terminal**

Tetapkan token sebagai pembolehubah persekitaran:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Jalankan Contoh

**Menggunakan VS Code:** Klik kanan saja pada mana-mana fail demo dalam Penjelajah dan pilih **"Run Java"**, atau gunakan konfigurasi pelancaran dari panel Jalankan dan Debug (pastikan anda sudah tambah token ke fail `.env` dahulu).

**Menggunakan Maven:** Sebagai alternatif, anda boleh jalankan dari baris arahan:

### 1. Sembang Asas

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Corak Arahan

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Menunjukkan zero-shot, few-shot, chain-of-thought, dan arahan berasaskan peranan.

### 3. Panggilan Fungsi

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI secara automatik memanggil kaedah Java anda apabila diperlukan.

### 4. Soal Jawab Dokumen (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Tanya soalan tentang kandungan dalam `document.txt`.

### 5. AI Bertanggungjawab

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

Lihat bagaimana penapis keselamatan AI menghalang kandungan berbahaya.

## Apa yang Ditunjukkan Setiap Contoh

**Sembang Asas** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Mulakan di sini untuk melihat LangChain4j dalam bentuk paling asas. Anda akan buat `OpenAiOfficialChatModel`, hantar arahan dengan `.chat()`, dan terima respons. Ini menunjukkan asas: cara memulakan model dengan titik akhir dan kunci API tersuai. Setelah anda faham corak ini, segala-galanya dibina daripadanya.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Cuba dengan [GitHub Copilot](https://github.com/features/copilot) Sembang:** Buka [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) dan tanya:
> - "Bagaimana saya tukar dari Model GitHub ke Azure OpenAI dalam kod ini?"
> - "Apakah parameter lain yang boleh saya set dalam OpenAiOfficialChatModel.builder()?"
> - "Bagaimana saya tambah respons penstriman dan bukannya tunggu respons lengkap?"

**Kejuruteraan Arahan** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Sekarang anda tahu cara bercakap dengan model, mari kita lihat apa yang anda beritahu model itu. Demo ini menggunakan tetapan model yang sama tetapi menunjukkan empat corak arahan berbeza. Cuba zero-shot untuk arahan terus, few-shot yang belajar dari contoh, chain-of-thought yang jelaskan langkah berfikir, dan arahan berasaskan peranan yang tetapkan konteks. Anda akan lihat bagaimana model sama memberi hasil yang berbeza secara dramatik berdasarkan cara anda bina permintaan.

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

> **🤖 Cuba dengan [GitHub Copilot](https://github.com/features/copilot) Sembang:** Buka [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) dan tanya:
> - "Apa beza zero-shot dan few-shot prompting, dan bila patut guna masing-masing?"
> - "Bagaimana parameter suhu mempengaruhi respons model?"
> - "Apa teknik untuk cegah serangan suntikan arahan di produksi?"
> - "Bagaimana saya cipta objek PromptTemplate boleh guna semula untuk corak biasa?"

**Integrasi Alat** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Di sinilah LangChain4j menjadi berkuasa. Anda akan gunakan `AiServices` untuk cipta pembantu AI yang boleh panggil kaedah Java anda. Hanya beri anotasi kaedah dengan `@Tool("penerangan")` dan LangChain4j urus selebihnya - AI secara automatik putuskan bila guna setiap alat berdasarkan apa yang pengguna minta. Ini menunjukkan panggilan fungsi, teknik utama untuk bina AI yang boleh ambil tindakan, bukan hanya jawab soalan.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Cuba dengan [GitHub Copilot](https://github.com/features/copilot) Sembang:** Buka [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) dan tanya:
> - "Bagaimana anotasi @Tool berfungsi dan apa yang LangChain4j lakukan dengannya?"
> - "Bolehkah AI panggil beberapa alat secara berurutan untuk selesaikan masalah kompleks?"
> - "Apa jadi jika alat lempar pengecualian - bagaimana saya urus ralat?"
> - "Bagaimana saya integrasi API sebenar dan bukan contoh kalkulator ini?"

**Soal Jawab Dokumen (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Di sini anda akan lihat asas RAG (penjanaan yang diperkaya pengambilan). Daripada bergantung pada data latihan model, anda muatkan kandungan dari [`document.txt`](../../../00-quick-start/document.txt) dan sertakan dalam arahan. AI menjawab berdasarkan dokumen anda, bukan pengetahuan amnya. Ini langkah pertama ke arah bina sistem yang dapat gunakan data anda sendiri.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Nota:** Pendekatan mudah ini memuatkan keseluruhan dokumen ke arahan. Untuk fail besar (>10KB), anda akan melebihi had konteks. Modul 03 membahas pengpecahan dan pencarian vektor untuk sistem RAG produksi.

> **🤖 Cuba dengan [GitHub Copilot](https://github.com/features/copilot) Sembang:** Buka [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) dan tanya:
> - "Bagaimana RAG cegah halusinasi AI berbanding guna data latihan model?"
> - "Apa beza pendekatan mudah ini dengan guna embeddings vektor untuk pengambilan?"
> - "Bagaimana saya skalakan ini untuk tangani pelbagai dokumen atau pangkalan pengetahuan lebih besar?"
> - "Apa amalan terbaik untuk struktur arahan supaya AI hanya guna konteks disediakan?"

**AI Bertanggungjawab** - [ResponsibleAIDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)

Bina keselamatan AI dengan pertahanan berlapis. Demo ini tunjuk dua lapisan perlindungan yang bekerjasama:

**Bahagian 1: LangChain4j Input Guardrails** - Halang arahan berbahaya sebelum sampai ke LLM. Cipta guardrails tersuai yang periksa kata kunci atau corak terlarang. Ini dijalankan dalam kod anda, jadi pantas dan tanpa kos.

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

**Bahagian 2: Penapis Keselamatan Penyedia** - Model GitHub ada penapis terbina yang tangkap apa guardrails mungkin terlepas. Anda akan lihat sekatan keras (ralat HTTP 400) untuk pelanggaran serius dan penolakan lembut di mana AI dengan sopan menolak.

> **🤖 Cuba dengan [GitHub Copilot](https://github.com/features/copilot) Sembang:** Buka [`ResponsibleAIDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java) dan tanya:
> - "Apa itu InputGuardrail dan bagaimana saya cipta guardrail saya sendiri?"
> - "Apa beza sekatan keras dan penolakan lembut?"
> - "Kenapa guna kedua-dua guardrails dan penapis penyedia bersama-sama?"

## Langkah Seterusnya

**Modul Seterusnya:** [01-pengenalan - Memulakan dengan LangChain4j dan gpt-5 di Azure](../01-introduction/README.md)

---

**Navigasi:** [← Kembali ke Utama](../README.md) | [Seterusnya: Modul 01 - Pengenalan →](../01-introduction/README.md)

---

## Penyelesaian Masalah

### Pembinaan Maven Kali Pertama

**Masalah**: `mvn clean compile` atau `mvn package` pertama mengambil masa lama (10-15 minit)

**Punca**: Maven perlu muat turun semua kebergantungan projek (Spring Boot, perpustakaan LangChain4j, SDK Azure, dan lain) pada pembinaan pertama.

**Penyelesaian**: Ini adalah tingkah laku biasa. Pembinaan seterusnya akan jauh lebih cepat kerana kebergantungan disimpan dalam cache tempatan. Masa muat turun bergantung pada kelajuan rangkaian anda.

### Sintaks Arahan Maven PowerShell

**Masalah**: Arahan Maven gagal dengan ralat `Unknown lifecycle phase ".mainClass=..."`

**Punca**: PowerShell mentafsir `=` sebagai operator penetapan pembolehubah, menyebabkan ralat sintaks harta Maven.
**Penyelesaian**: Gunakan operator berhenti-menganalisa `--%` sebelum arahan Maven:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Operator `--%` memberitahu PowerShell untuk menghantar semua argumen yang tinggal secara literal kepada Maven tanpa tafsiran.

### Paparan Emoji Windows PowerShell

**Isu**: Respons AI menunjukkan aksara sampah (contoh, `????` atau `â??`) menggantikan emoji dalam PowerShell

**Sebab**: Pengekodan lalai PowerShell tidak menyokong emoji UTF-8

**Penyelesaian**: Jalankan arahan ini sebelum melaksanakan aplikasi Java:
```cmd
chcp 65001
```

Ini memaksa pengekodan UTF-8 dalam terminal. Sebagai alternatif, gunakan Windows Terminal yang mempunyai sokongan Unicode lebih baik.

### Menyahpepijat Panggilan API

**Isu**: Ralat pengesahan, had kadar, atau respons tidak dijangka daripada model AI

**Penyelesaian**: Contoh merangkumi `.logRequests(true)` dan `.logResponses(true)` untuk menunjukkan panggilan API dalam konsol. Ini membantu menyelesaikan masalah ralat pengesahan, had kadar, atau respons tidak dijangka. Keluarkan penanda ini dalam produksi untuk mengurangkan bunyian log.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan perkhidmatan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Walaupun kami berusaha untuk ketepatan, sila ambil maklum bahawa terjemahan automatik mungkin mengandungi kesilapan atau ketidaktepatan. Dokumen asal dalam bahasa asalnya hendaklah dianggap sebagai sumber yang sahih. Untuk maklumat kritikal, terjemahan profesional oleh manusia adalah disyorkan. Kami tidak bertanggungjawab atas sebarang salah faham atau salah tafsir yang timbul daripada penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->