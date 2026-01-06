<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "6c816d130a1fa47570c11907e72d84ae",
  "translation_date": "2026-01-06T00:21:17+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "id"
}
-->
# Modul 05: Model Context Protocol (MCP)

## Daftar Isi

- [Apa yang Akan Anda Pelajari](../../../05-mcp)
- [Apa itu MCP?](../../../05-mcp)
- [Bagaimana MCP Bekerja](../../../05-mcp)
- [Modul Agentic](../../../05-mcp)
- [Men jalankan Contoh](../../../05-mcp)
  - [Persyaratan Awal](../../../05-mcp)
- [Memulai dengan Cepat](../../../05-mcp)
  - [Operasi Berkas (Stdio)](../../../05-mcp)
  - [Agen Supervisor](../../../05-mcp)
    - [Memahami Output](../../../05-mcp)
    - [Strategi Respons](../../../05-mcp)
    - [Penjelasan Fitur Modul Agentic](../../../05-mcp)
- [Konsep Kunci](../../../05-mcp)
- [Selamat!](../../../05-mcp)
  - [Apa Berikutnya?](../../../05-mcp)

## Apa yang Akan Anda Pelajari

Anda telah membangun AI percakapan, menguasai prompt, memantapkan respons dalam dokumen, dan membuat agen dengan alat. Tetapi semua alat tersebut dibuat khusus untuk aplikasi spesifik Anda. Bagaimana jika Anda dapat memberi AI Anda akses ke ekosistem alat standar yang dapat dibuat dan dibagikan siapa saja? Dalam modul ini, Anda akan belajar cara melakukan ini dengan Model Context Protocol (MCP) dan modul agentic LangChain4j. Kami pertama-tama menampilkan pembaca file MCP sederhana dan kemudian menunjukkan bagaimana itu mudah diintegrasikan ke dalam alur kerja agentic lanjutan menggunakan pola Agen Supervisor.

## Apa itu MCP?

Model Context Protocol (MCP) memberikan tepat itu — cara standar bagi aplikasi AI untuk menemukan dan menggunakan alat eksternal. Alih-alih menulis integrasi khusus untuk setiap sumber data atau layanan, Anda terhubung ke server MCP yang memamerkan kemampuannya dalam format yang konsisten. Agen AI Anda kemudian dapat menemukan dan menggunakan alat-alat tersebut secara otomatis.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.id.png" alt="Perbandingan MCP" width="800"/>

*Sebelum MCP: Integrasi kompleks point-to-point. Setelah MCP: Satu protokol, kemungkinan tak terbatas.*

MCP memecahkan masalah mendasar dalam pengembangan AI: setiap integrasi bersifat khusus. Ingin mengakses GitHub? Kode khusus. Ingin membaca file? Kode khusus. Ingin kueri database? Kode khusus. Dan tidak ada integrasi ini yang bekerja dengan aplikasi AI lainnya.

MCP menstandarisasi ini. Server MCP memamerkan alat dengan deskripsi jelas dan skema parameter. Klien MCP mana pun dapat terhubung, menemukan alat yang tersedia, dan menggunakannya. Bangun sekali, gunakan di mana-mana.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.id.png" alt="Arsitektur MCP" width="800"/>

*Arsitektur Model Context Protocol — penemuan dan eksekusi alat yang distandarisasi*

## Bagaimana MCP Bekerja

**Arsitektur Server-Klien**

MCP menggunakan model klien-server. Server menyediakan alat — membaca file, kueri database, memanggil API. Klien (aplikasi AI Anda) terhubung ke server dan menggunakan alatnya.

Untuk menggunakan MCP dengan LangChain4j, tambahkan dependensi Maven ini:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**Penemuan Alat**

Saat klien Anda terhubung ke server MCP, ia bertanya "Alat apa yang kamu miliki?" Server merespons dengan daftar alat yang tersedia, masing-masing dengan deskripsi dan skema parameter. Agen AI Anda kemudian dapat memutuskan alat mana yang digunakan berdasarkan permintaan pengguna.

**Mekanisme Transportasi**

MCP mendukung berbagai mekanisme transportasi. Modul ini mendemonstrasikan transport Stdio untuk proses lokal:

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.id.png" alt="Mekanisme Transportasi" width="800"/>

*Mekanisme transport MCP: HTTP untuk server jarak jauh, Stdio untuk proses lokal*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Untuk proses lokal. Aplikasi Anda menjalankan server sebagai subprocess dan berkomunikasi melalui input/output standar. Berguna untuk akses sistem berkas atau alat baris perintah.

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@2025.12.18",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) dan tanyakan:
> - "Bagaimana cara kerja transportasi Stdio dan kapan saya harus menggunakannya dibanding HTTP?"
> - "Bagaimana LangChain4j mengelola siklus hidup proses server MCP yang dijalankan?"
> - "Apa implikasi keamanan memberikan AI akses ke sistem berkas?"

## Modul Agentic

Sementara MCP menyediakan alat standar, modul **agentic** LangChain4j menyediakan cara deklaratif untuk membangun agen yang mengoordinasikan alat-alat tersebut. Anotasi `@Agent` dan `AgenticServices` memungkinkan Anda mendefinisikan perilaku agen melalui antarmuka daripada kode imperatif.

Dalam modul ini, Anda akan mengeksplorasi pola **Agen Supervisor** — pendekatan AI agentic lanjutan dimana agen "supervisor" memutuskan secara dinamis sub-agen mana yang harus dipanggil berdasarkan permintaan pengguna. Kita akan menggabungkan kedua konsep ini dengan memberi salah satu sub-agen kita kemampuan akses file bertenaga MCP.

Untuk menggunakan modul agentic, tambahkan dependensi Maven ini:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ Eksperimental:** Modul `langchain4j-agentic` adalah **eksperimental** dan dapat berubah. Cara stabil membangun asisten AI tetap `langchain4j-core` dengan alat khusus (Modul 04).

## Menjalankan Contoh

### Persyaratan Awal

- Java 21+, Maven 3.9+
- Node.js 16+ dan npm (untuk server MCP)
- Variabel lingkungan dikonfigurasi di file `.env` (dari direktori akar):
  - `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (sama dengan Modul 01-04)

> **Catatan:** Jika Anda belum menyiapkan variabel lingkungan, lihat [Modul 00 - Memulai dengan Cepat](../00-quick-start/README.md) untuk petunjuk, atau salin `.env.example` ke `.env` di direktori akar dan isi nilainya.

## Memulai dengan Cepat

**Menggunakan VS Code:** Klik kanan pada file demo mana pun di Explorer dan pilih **"Jalankan Java"**, atau gunakan konfigurasi peluncuran dari panel Jalankan dan Debug (pastikan token Anda sudah ditambahkan ke file `.env` terlebih dahulu).

**Menggunakan Maven:** Alternatifnya, Anda dapat menjalankan dari baris perintah dengan contoh berikut.

### Operasi Berkas (Stdio)

Ini mendemonstrasikan alat berbasis subprocess lokal.

**✅ Tidak perlu persyaratan awal** - server MCP dijalankan otomatis.

**Menggunakan Skrip Start (Direkomendasikan):**

Skrip start otomatis memuat variabel lingkungan dari file `.env` di akar:

**Bash:**
```bash
cd 05-mcp
chmod +x start-stdio.sh
./start-stdio.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start-stdio.ps1
```

**Menggunakan VS Code:** Klik kanan pada `StdioTransportDemo.java` dan pilih **"Jalankan Java"** (pastikan file `.env` Anda sudah dikonfigurasi).

Aplikasi secara otomatis menjalankan server MCP filesystem dan membaca file lokal. Perhatikan bagaimana pengelolaan subprocess dilakukan untuk Anda.

**Output yang diharapkan:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Agen Supervisor

Pola **Agen Supervisor** adalah bentuk AI agentic yang **fleksibel**. Seorang Supervisor menggunakan LLM untuk secara otomatis memutuskan agen mana yang harus dipanggil berdasarkan permintaan pengguna. Dalam contoh berikut, kita menggabungkan akses file bertenaga MCP dengan agen LLM untuk membuat alur kerja baca file → laporan yang diawasi.

Dalam demo, `FileAgent` membaca file menggunakan alat filesystem MCP, dan `ReportAgent` menghasilkan laporan terstruktur dengan ringkasan eksekutif (1 kalimat), 3 poin utama, dan rekomendasi. Supervisor mengatur alur ini secara otomatis:

<img src="../../../translated_images/agentic.cf84dcda226374e3.id.png" alt="Modul Agentic" width="800"/>

```
┌─────────────┐      ┌──────────────┐
│  FileAgent  │ ───▶ │ ReportAgent  │
│ (MCP tools) │      │  (pure LLM)  │
└─────────────┘      └──────────────┘
   outputKey:           outputKey:
  'fileContent'         'report'
```

Setiap agen menyimpan outputnya dalam **Agentic Scope** (memori bersama), memungkinkan agen berikutnya mengakses hasil sebelumnya. Ini menunjukkan bagaimana alat MCP terintegrasi mulus ke dalam alur kerja agentic — Supervisor tidak perlu tahu *bagaimana* file dibaca, hanya bahwa `FileAgent` bisa melakukannya.

#### Menjalankan Demo

Skrip start otomatis memuat variabel lingkungan dari file `.env` di akar:

**Bash:**
```bash
cd 05-mcp
chmod +x start-supervisor.sh
./start-supervisor.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start-supervisor.ps1
```

**Menggunakan VS Code:** Klik kanan pada `SupervisorAgentDemo.java` dan pilih **"Jalankan Java"** (pastikan file `.env` sudah dikonfigurasi).

#### Cara Kerja Supervisor

```java
// Langkah 1: FileAgent membaca file menggunakan alat MCP
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // Memiliki alat MCP untuk operasi file
        .build();

// Langkah 2: ReportAgent menghasilkan laporan terstruktur
ReportAgent reportAgent = AgenticServices.agentBuilder(ReportAgent.class)
        .chatModel(model)
        .build();

// Supervisor mengatur alur kerja file → laporan
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)
        .subAgents(fileAgent, reportAgent)
        .responseStrategy(SupervisorResponseStrategy.LAST)  // Mengembalikan laporan akhir
        .build();

// Supervisor memutuskan agen mana yang akan dipanggil berdasarkan permintaan
String response = supervisor.invoke("Read the file at /path/file.txt and generate a report");
```

#### Strategi Respons

Saat Anda mengonfigurasi `SupervisorAgent`, Anda menentukan bagaimana ia harus merumuskan jawaban akhir kepada pengguna setelah sub-agen menyelesaikan tugas mereka. Strategi yang tersedia adalah:

| Strategi | Deskripsi |
|----------|-------------|
| **LAST** | Supervisor mengembalikan output dari sub-agen atau alat terakhir yang dipanggil. Ini berguna ketika agen terakhir dalam alur kerja dirancang khusus untuk menghasilkan jawaban lengkap akhir (misal, "Agen Ringkasan" dalam pipeline riset). |
| **SUMMARY** | Supervisor menggunakan Model Bahasa (LLM) internalnya untuk menyintesis ringkasan seluruh interaksi dan semua output sub-agen, lalu mengembalikan ringkasan tersebut sebagai respons akhir. Ini memberikan jawaban yang bersih dan teragregasi kepada pengguna. |
| **SCORED** | Sistem menggunakan LLM internal untuk memberi skor pada respons LAST dan SUMMARY terhadap permintaan asli pengguna, mengembalikan output yang mendapat skor lebih tinggi. |

Lihat [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) untuk implementasi lengkapnya.

> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) dan tanyakan:
> - "Bagaimana Supervisor memutuskan agen mana yang dipanggil?"
> - "Apa perbedaan pola Supervisor dan pola alur kerja Sequential?"
> - "Bagaimana cara saya menyesuaikan perilaku perencanaan Supervisor?"

#### Memahami Output

Saat Anda menjalankan demo, Anda akan melihat penjelasan terstruktur tentang bagaimana Supervisor mengoordinasikan beberapa agen. Ini arti setiap bagian:

```
======================================================================
  FILE → REPORT WORKFLOW DEMO
======================================================================

This demo shows a clear 2-step workflow: read a file, then generate a report.
The Supervisor orchestrates the agents automatically based on the request.
```

**Judul** memperkenalkan konsep alur kerja: pipeline terfokus dari pembacaan file ke pembuatan laporan.

```
--- WORKFLOW ---------------------------------------------------------
  ┌─────────────┐      ┌──────────────┐
  │  FileAgent  │ ───▶ │ ReportAgent  │
  │ (MCP tools) │      │  (pure LLM)  │
  └─────────────┘      └──────────────┘
   outputKey:           outputKey:
   'fileContent'        'report'

--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]   FileAgent   - Reads files via MCP → stores in 'fileContent'
  [REPORT] ReportAgent - Generates structured report → stores in 'report'
```

**Diagram Alur Kerja** menunjukkan alur data antar agen. Setiap agen memiliki peran spesifik:
- **FileAgent** membaca file menggunakan alat MCP dan menyimpan konten mentah di `fileContent`
- **ReportAgent** menggunakan konten tersebut dan menghasilkan laporan terstruktur di `report`

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and generate a report on its contents"
```

**Permintaan Pengguna** menunjukkan tugas. Supervisor mem-parsing ini dan memutuskan memanggil FileAgent → ReportAgent.

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor decides which agents to invoke and passes data between them...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source, provider-agnostic Java framework for building LLM...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> ReportAgent (generating structured report)
  |
  |   Input: LangChain4j is an open-source, provider-agnostic Java framew...
  |
  |   Result: Executive Summary...
  +-- [OK] ReportAgent (generating structured report) completed
```

**Orkestrasi Supervisor** menunjukkan alur 2 langkah yang berjalan:
1. **FileAgent** membaca file via MCP dan menyimpan konten
2. **ReportAgent** menerima konten dan membuat laporan terstruktur

Supervisor membuat keputusan ini **secara otonom** berdasarkan permintaan pengguna.

```
--- FINAL RESPONSE ---------------------------------------------------
Executive Summary
...

Key Points
...

Recommendations
...

--- AGENTIC SCOPE (Data Flow) ----------------------------------------
  Each agent stores its output for downstream agents to consume:
  * fileContent: LangChain4j is an open-source, provider-agnostic Java framework...
  * report: Executive Summary...
```

#### Penjelasan Fitur Modul Agentic

Contoh ini menunjukkan beberapa fitur maju modul agentic. Mari perhatikan Agentic Scope dan Agent Listeners.

**Agentic Scope** menunjukkan memori bersama tempat agen menyimpan hasilnya menggunakan `@Agent(outputKey="...")`. Ini memungkinkan:
- Agen berikutnya mengakses output agen sebelumnya
- Supervisor menyintesis respons akhir
- Anda memeriksa apa yang dihasilkan setiap agen

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String fileContent = scope.readState("fileContent");  // Data file mentah dari FileAgent
String report = scope.readState("report");            // Laporan terstruktur dari ReportAgent
```

**Agent Listeners** memungkinkan pemantauan dan debugging eksekusi agen. Output langkah-demi-langkah yang Anda lihat dalam demo berasal dari AgentListener yang terhubung ke setiap pemanggilan agen:
- **beforeAgentInvocation** – Dipanggil saat Supervisor memilih agen, memungkinkan Anda melihat agen mana yang dipilih dan alasannya
- **afterAgentInvocation** – Dipanggil saat agen selesai, menampilkan hasilnya
- **inheritedBySubagents** – Jika true, listener memantau semua agen dalam hierarki

```java
AgentListener monitor = new AgentListener() {
    private int step = 0;
    
    @Override
    public void beforeAgentInvocation(AgentRequest request) {
        step++;
        System.out.println("  +-- STEP " + step + ": " + request.agentName());
    }
    
    @Override
    public void afterAgentInvocation(AgentResponse response) {
        System.out.println("  +-- [OK] " + response.agentName() + " completed");
    }
    
    @Override
    public boolean inheritedBySubagents() {
        return true; // Sebarkan ke semua sub-agen
    }
};
```

Selain pola Supervisor, modul `langchain4j-agentic` menyediakan beberapa pola dan fitur alur kerja yang kuat:

| Pola | Deskripsi | Kasus Penggunaan |
|---------|-------------|----------|
| **Sequential** | Menjalankan agen berurutan, output mengalir ke agen berikutnya | Pipeline: riset → analisis → laporan |
| **Parallel** | Menjalankan agen secara simultan | Tugas independen: cuaca + berita + saham |
| **Loop** | Mengulangi sampai kondisi terpenuhi | Penilaian kualitas: perbaiki sampai skor ≥ 0,8 |
| **Conditional** | Mengarahkan berdasarkan kondisi | Klasifikasi → rute ke agen spesialis |
| **Human-in-the-Loop** | Menambahkan checkpoint manusia | Alur kerja persetujuan, peninjauan konten |

## Konsep Kunci

Setelah Anda menjelajahi MCP dan modul agentic secara langsung, mari kita ringkas kapan menggunakan masing-masing pendekatan.

**MCP** ideal saat Anda ingin memanfaatkan ekosistem alat yang sudah ada, membangun alat yang dapat digunakan oleh banyak aplikasi, mengintegrasikan layanan pihak ketiga dengan protokol standar, atau mengganti implementasi alat tanpa mengubah kode.

**Modul Agentic** terbaik digunakan jika Anda ingin definisi agen deklaratif dengan anotasi `@Agent`, memerlukan orkestrasi alur kerja (sekuensial, loop, paralel), lebih suka desain agen berbasis antarmuka daripada kode imperatif, atau menggabungkan beberapa agen yang berbagi output melalui `outputKey`.

**Pola Agen Supervisor** menonjol ketika alur kerja tidak dapat diprediksi sebelumnya dan Anda ingin LLM yang memutuskan, ketika Anda memiliki banyak agen khusus yang memerlukan orkestrasi dinamis, saat membangun sistem percakapan yang mengarahkan ke berbagai kapabilitas, atau ketika Anda ingin perilaku agen yang paling fleksibel dan adaptif.
## Selamat!

Anda telah menyelesaikan kursus LangChain4j untuk Pemula. Anda telah mempelajari:

- Cara membangun AI percakapan dengan memori (Modul 01)
- Pola rekayasa prompt untuk berbagai tugas (Modul 02)
- Membumikan respons dalam dokumen Anda dengan RAG (Modul 03)
- Membuat agen AI dasar (asisten) dengan alat khusus (Modul 04)
- Mengintegrasikan alat standar dengan modul LangChain4j MCP dan Agentic (Modul 05)

### Apa Selanjutnya?

Setelah menyelesaikan modul, jelajahi [Panduan Pengujian](../docs/TESTING.md) untuk melihat konsep pengujian LangChain4j dalam praktik.

**Sumber Resmi:**
- [Dokumentasi LangChain4j](https://docs.langchain4j.dev/) - Panduan lengkap dan referensi API
- [GitHub LangChain4j](https://github.com/langchain4j/langchain4j) - Kode sumber dan contoh
- [Tutorial LangChain4j](https://docs.langchain4j.dev/tutorials/) - Tutorial langkah demi langkah untuk berbagai kasus penggunaan

Terima kasih telah menyelesaikan kursus ini!

---

**Navigasi:** [← Sebelumnya: Modul 04 - Alat](../04-tools/README.md) | [Kembali ke Utama](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan layanan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Meskipun kami berupaya untuk akurasi, harap diketahui bahwa terjemahan otomatis mungkin mengandung kesalahan atau ketidakakuratan. Dokumen asli dalam bahasa aslinya harus dianggap sebagai sumber yang berwenang. Untuk informasi penting, disarankan menggunakan terjemahan profesional oleh manusia. Kami tidak bertanggung jawab atas kesalahpahaman atau salah tafsir yang timbul dari penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->