<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c25ec1f10ef156c53e190cdf8b0711ab",
  "translation_date": "2025-12-13T18:00:32+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "ms"
}
-->
# Modul 05: Protokol Konteks Model (MCP)

## Jadual Kandungan

- [Apa yang Anda Akan Pelajari](../../../05-mcp)
- [Memahami MCP](../../../05-mcp)
- [Bagaimana MCP Berfungsi](../../../05-mcp)
  - [Seni Bina Pelayan-Pelanggan](../../../05-mcp)
  - [Penemuan Alat](../../../05-mcp)
  - [Mekanisme Pengangkutan](../../../05-mcp)
- [Prasyarat](../../../05-mcp)
- [Apa yang Modul Ini Liputi](../../../05-mcp)
- [Mula Pantas](../../../05-mcp)
  - [Contoh 1: Kalkulator Jauh (HTTP Boleh Alir)](../../../05-mcp)
  - [Contoh 2: Operasi Fail (Stdio)](../../../05-mcp)
  - [Contoh 3: Analisis Git (Docker)](../../../05-mcp)
- [Konsep Utama](../../../05-mcp)
  - [Pemilihan Pengangkutan](../../../05-mcp)
  - [Penemuan Alat](../../../05-mcp)
  - [Pengurusan Sesi](../../../05-mcp)
  - [Pertimbangan Rentas Platform](../../../05-mcp)
- [Bila Menggunakan MCP](../../../05-mcp)
- [Ekosistem MCP](../../../05-mcp)
- [Tahniah!](../../../05-mcp)
  - [Apa Seterusnya?](../../../05-mcp)
- [Penyelesaian Masalah](../../../05-mcp)

## Apa yang Anda Akan Pelajari

Anda telah membina AI perbualan, menguasai arahan, mengasaskan respons dalam dokumen, dan mencipta ejen dengan alat. Tetapi semua alat itu dibina khas untuk aplikasi anda. Bagaimana jika anda boleh memberikan AI anda akses kepada ekosistem alat standard yang sesiapa sahaja boleh cipta dan kongsi?

Protokol Konteks Model (MCP) menyediakan tepat itu - cara standard untuk aplikasi AI menemui dan menggunakan alat luaran. Daripada menulis integrasi khusus untuk setiap sumber data atau perkhidmatan, anda menyambung ke pelayan MCP yang mendedahkan keupayaan mereka dalam format yang konsisten. Ejen AI anda kemudian boleh menemui dan menggunakan alat ini secara automatik.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5448d2fa21a61218777ceb8010ea0390dd43924b26df35f61.ms.png" alt="Perbandingan MCP" width="800"/>

*Sebelum MCP: Integrasi titik-ke-titik yang kompleks. Selepas MCP: Satu protokol, kemungkinan tanpa had.*

## Memahami MCP

MCP menyelesaikan masalah asas dalam pembangunan AI: setiap integrasi adalah khusus. Mahu akses GitHub? Kod khusus. Mahu baca fail? Kod khusus. Mahu kueri pangkalan data? Kod khusus. Dan tiada satu pun integrasi ini berfungsi dengan aplikasi AI lain.

MCP menstandardkan ini. Pelayan MCP mendedahkan alat dengan penerangan dan skema yang jelas. Mana-mana klien MCP boleh menyambung, menemui alat yang tersedia, dan menggunakannya. Bina sekali, guna di mana-mana.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9814b7cffade208d4b0d97203c22df8d8e5504d8238fa7065.ms.png" alt="Seni Bina MCP" width="800"/>

*Seni bina Protokol Konteks Model - penemuan dan pelaksanaan alat yang distandardkan*

## Bagaimana MCP Berfungsi

**Seni Bina Pelayan-Pelanggan**

MCP menggunakan model pelayan-pelanggan. Pelayan menyediakan alat - membaca fail, kueri pangkalan data, panggil API. Klien (aplikasi AI anda) menyambung ke pelayan dan menggunakan alat mereka.

**Penemuan Alat**

Apabila klien anda menyambung ke pelayan MCP, ia bertanya "Alat apa yang anda ada?" Pelayan membalas dengan senarai alat yang tersedia, setiap satu dengan penerangan dan skema parameter. Ejen AI anda kemudian boleh memutuskan alat mana yang hendak digunakan berdasarkan permintaan pengguna.

**Mekanisme Pengangkutan**

MCP mentakrifkan dua mekanisme pengangkutan: HTTP untuk pelayan jauh, Stdio untuk proses tempatan (termasuk kontena Docker):

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020ed801b772b26ed69338e22739677aa017e0968f6538b09a2.ms.png" alt="Mekanisme Pengangkutan" width="800"/>

*Mekanisme pengangkutan MCP: HTTP untuk pelayan jauh, Stdio untuk proses tempatan (termasuk kontena Docker)*

**Streamable HTTP** - [StreamableHttpDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java)

Untuk pelayan jauh. Aplikasi anda membuat permintaan HTTP ke pelayan yang berjalan di mana-mana dalam rangkaian. Menggunakan Server-Sent Events untuk komunikasi masa nyata.

```java
McpTransport httpTransport = new StreamableHttpMcpTransport.Builder()
    .url("http://localhost:3001/mcp")
    .timeout(Duration.ofSeconds(60))
    .logRequests(true)
    .logResponses(true)
    .build();
```

> **🤖 Cuba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`StreamableHttpDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java) dan tanya:
> - "Bagaimana MCP berbeza daripada integrasi alat langsung seperti dalam Modul 04?"
> - "Apakah manfaat menggunakan MCP untuk perkongsian alat merentasi aplikasi?"
> - "Bagaimana saya mengendalikan kegagalan sambungan atau masa tamat ke pelayan MCP?"

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Untuk proses tempatan. Aplikasi anda memulakan pelayan sebagai proses anak dan berkomunikasi melalui input/output standard. Berguna untuk akses sistem fail atau alat baris perintah.

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@0.6.2",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 Cuba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) dan tanya:
> - "Bagaimana pengangkutan Stdio berfungsi dan bila saya harus menggunakannya berbanding HTTP?"
> - "Bagaimana LangChain4j mengurus kitar hayat proses pelayan MCP yang dimulakan?"
> - "Apakah implikasi keselamatan memberi AI akses ke sistem fail?"

**Docker (menggunakan Stdio)** - [GitRepositoryAnalyzer.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java)

Untuk perkhidmatan berkontena. Menggunakan pengangkutan stdio untuk berkomunikasi dengan kontena Docker melalui `docker run`. Sesuai untuk pergantungan kompleks atau persekitaran terasing.

```java
McpTransport dockerTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        "docker", "run",
        "-e", "GITHUB_PERSONAL_ACCESS_TOKEN=" + System.getenv("GITHUB_TOKEN"),
        "-v", volumeMapping,
        "-i", "mcp/git"
    ))
    .logEvents(true)
    .build();
```

> **🤖 Cuba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`GitRepositoryAnalyzer.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java) dan tanya:
> - "Bagaimana pengangkutan Docker mengasingkan pelayan MCP dan apakah manfaatnya?"
> - "Bagaimana saya mengkonfigurasi pemasangan volum untuk berkongsi data antara hos dan kontena MCP?"
> - "Apakah amalan terbaik untuk mengurus kitar hayat pelayan MCP berasaskan Docker dalam pengeluaran?"

## Menjalankan Contoh

### Prasyarat

- Java 21+, Maven 3.9+
- Node.js 16+ dan npm (untuk pelayan MCP)
- **Docker Desktop** - Mesti **BERJALAN** untuk Contoh 3 (bukan hanya dipasang)
- Token Akses Peribadi GitHub dikonfigurasi dalam fail `.env` (dari Modul 00)

> **Nota:** Jika anda belum menyediakan token GitHub anda, lihat [Modul 00 - Mula Pantas](../00-quick-start/README.md) untuk arahan.

> **⚠️ Pengguna Docker:** Sebelum menjalankan Contoh 3, sahkan Docker Desktop berjalan dengan `docker ps`. Jika anda melihat ralat sambungan, mulakan Docker Desktop dan tunggu ~30 saat untuk inisialisasi.

## Mula Pantas

**Menggunakan VS Code:** Klik kanan pada mana-mana fail demo dalam Penjelajah dan pilih **"Run Java"**, atau gunakan konfigurasi pelancaran dari panel Run and Debug (pastikan anda telah menambah token anda ke fail `.env` terlebih dahulu).

**Menggunakan Maven:** Sebagai alternatif, anda boleh jalankan dari baris arahan dengan contoh di bawah.

**⚠️ Penting:** Sesetengah contoh mempunyai prasyarat (seperti memulakan pelayan MCP atau membina imej Docker). Semak keperluan setiap contoh sebelum menjalankan.

### Contoh 1: Kalkulator Jauh (HTTP Boleh Alir)

Ini menunjukkan integrasi alat berasaskan rangkaian.

**⚠️ Prasyarat:** Anda perlu memulakan pelayan MCP terlebih dahulu (lihat Terminal 1 di bawah).

**Terminal 1 - Mulakan pelayan MCP:**

**Bash:**
```bash
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**PowerShell:**
```powershell
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**Terminal 2 - Jalankan contoh:**

**Menggunakan VS Code:** Klik kanan pada `StreamableHttpDemo.java` dan pilih **"Run Java"**.

**Menggunakan Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

Perhatikan ejen menemui alat yang tersedia, kemudian gunakan kalkulator untuk melakukan penambahan.

### Contoh 2: Operasi Fail (Stdio)

Ini menunjukkan alat berasaskan proses anak tempatan.

**✅ Tiada prasyarat diperlukan** - pelayan MCP dimulakan secara automatik.

**Menggunakan VS Code:** Klik kanan pada `StdioTransportDemo.java` dan pilih **"Run Java"**.

**Menggunakan Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

Aplikasi memulakan pelayan MCP sistem fail secara automatik dan membaca fail tempatan. Perhatikan bagaimana pengurusan proses anak dikendalikan untuk anda.

**Output yang dijangka:**
```
Assistant response: The content of the file is "Kaboom!".
```

### Contoh 3: Analisis Git (Docker)

Ini menunjukkan pelayan alat berkontena.

**⚠️ Prasyarat:** 
1. **Docker Desktop mesti BERJALAN** (bukan hanya dipasang)
2. **Pengguna Windows:** Mod WSL 2 disyorkan (Tetapan Docker Desktop → Umum → "Gunakan enjin berasaskan WSL 2"). Mod Hyper-V memerlukan konfigurasi perkongsian fail manual.
3. Anda perlu membina imej Docker terlebih dahulu (lihat Terminal 1 di bawah)

**Sahkan Docker berjalan:**

**Bash:**
```bash
docker ps  # Patut menunjukkan senarai bekas, bukan ralat
```

**PowerShell:**
```powershell
docker ps  # Patut menunjukkan senarai bekas, bukan ralat
```

Jika anda melihat ralat seperti "Tidak dapat sambung ke daemon Docker" atau "Sistem tidak dapat mencari fail yang ditentukan", mulakan Docker Desktop dan tunggu untuk inisialisasi (~30 saat).

**Penyelesaian Masalah:**
- Jika AI melaporkan repositori kosong atau tiada fail, pemasangan volum (`-v`) tidak berfungsi.
- **Pengguna Windows Hyper-V:** Tambah direktori projek ke Tetapan Docker Desktop → Sumber → Perkongsian Fail, kemudian mulakan semula Docker Desktop.
- **Penyelesaian disyorkan:** Tukar ke mod WSL 2 untuk perkongsian fail automatik (Tetapan → Umum → aktifkan "Gunakan enjin berasaskan WSL 2").

**Terminal 1 - Bina imej Docker:**

**Bash:**
```bash
cd servers/src/git
docker build -t mcp/git .
```

**PowerShell:**
```powershell
cd servers/src/git
docker build -t mcp/git .
```

**Terminal 2 - Jalankan penganalisis:**

**Menggunakan VS Code:** Klik kanan pada `GitRepositoryAnalyzer.java` dan pilih **"Run Java"**.

**Menggunakan Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

Aplikasi melancarkan kontena Docker, memasang repositori anda, dan menganalisis struktur serta kandungan repositori melalui ejen AI.

## Konsep Utama

**Pemilihan Pengangkutan**

Pilih berdasarkan lokasi alat anda:
- Perkhidmatan jauh → HTTP Boleh Alir
- Sistem fail tempatan → Stdio
- Pergantungan kompleks → Docker

**Penemuan Alat**

Klien MCP secara automatik menemui alat yang tersedia apabila menyambung. Ejen AI anda melihat penerangan alat dan memutuskan mana yang hendak digunakan berdasarkan permintaan pengguna.

**Pengurusan Sesi**

Pengangkutan HTTP Boleh Alir mengekalkan sesi, membenarkan interaksi berstatus dengan pelayan jauh. Pengangkutan Stdio dan Docker biasanya tanpa status.

**Pertimbangan Rentas Platform**

Contoh mengendalikan perbezaan platform secara automatik (perbezaan arahan Windows vs Unix, penukaran laluan untuk Docker). Ini penting untuk pengeluaran merentas persekitaran berbeza.

## Bila Menggunakan MCP

**Gunakan MCP apabila:**
- Anda mahu memanfaatkan ekosistem alat sedia ada
- Membina alat yang akan digunakan oleh pelbagai aplikasi
- Mengintegrasi perkhidmatan pihak ketiga dengan protokol standard
- Anda perlu menukar pelaksanaan alat tanpa perubahan kod

**Gunakan alat khusus (Modul 04) apabila:**
- Membina fungsi khusus aplikasi
- Prestasi kritikal (MCP menambah overhead)
- Alat anda mudah dan tidak akan digunakan semula
- Anda memerlukan kawalan penuh ke atas pelaksanaan


## Ekosistem MCP

Protokol Konteks Model adalah standard terbuka dengan ekosistem yang berkembang:

- Pelayan MCP rasmi untuk tugas biasa (sistem fail, Git, pangkalan data)
- Pelayan sumbangan komuniti untuk pelbagai perkhidmatan
- Penerangan dan skema alat yang distandardkan
- Keserasian rentas rangka kerja (berfungsi dengan mana-mana klien MCP)

Penyeragaman ini bermakna alat yang dibina untuk satu aplikasi AI berfungsi dengan yang lain, mewujudkan ekosistem keupayaan bersama.

## Tahniah!

Anda telah menamatkan kursus LangChain4j untuk Pemula. Anda telah belajar:

- Cara membina AI perbualan dengan memori (Modul 01)
- Corak kejuruteraan arahan untuk pelbagai tugas (Modul 02)
- Mengasaskan respons dalam dokumen anda dengan RAG (Modul 03)
- Mencipta ejen AI dengan alat khusus (Modul 04)
- Mengintegrasi alat standard melalui MCP (Modul 05)

Anda kini mempunyai asas untuk membina aplikasi AI produksi. Konsep yang anda pelajari terpakai tanpa mengira rangka kerja atau model tertentu - ia adalah corak asas dalam kejuruteraan AI.

### Apa Seterusnya?

Selepas menamatkan modul, terokai [Panduan Ujian](../docs/TESTING.md) untuk melihat konsep ujian LangChain4j dalam tindakan.

**Sumber Rasmi:**
- [Dokumentasi LangChain4j](https://docs.langchain4j.dev/) - Panduan komprehensif dan rujukan API
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Kod sumber dan contoh
- [Tutorial LangChain4j](https://docs.langchain4j.dev/tutorials/) - Tutorial langkah demi langkah untuk pelbagai kes penggunaan

Terima kasih kerana menamatkan kursus ini!

---

**Navigasi:** [← Sebelumnya: Modul 04 - Alat](../04-tools/README.md) | [Kembali ke Utama](../README.md)

---

## Penyelesaian Masalah

### Sintaks Perintah Maven PowerShell
**Isu**: Perintah Maven gagal dengan ralat `Unknown lifecycle phase ".mainClass=..."`

**Punca**: PowerShell mentafsir `=` sebagai operator penetapan pembolehubah, menyebabkan sintaks harta Maven rosak

**Penyelesaian**: Gunakan operator berhenti-memparsing `--%` sebelum perintah Maven:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

Operator `--%` memberitahu PowerShell untuk menghantar semua argumen yang tinggal secara literal kepada Maven tanpa tafsiran.

### Isu Sambungan Docker

**Isu**: Perintah Docker gagal dengan "Cannot connect to Docker daemon" atau "The system cannot find the file specified"

**Punca**: Docker Desktop tidak berjalan atau belum dimulakan sepenuhnya

**Penyelesaian**: 
1. Mulakan Docker Desktop
2. Tunggu ~30 saat untuk inisialisasi penuh
3. Sahkan dengan `docker ps` (sepatutnya menunjukkan senarai kontena, bukan ralat)
4. Kemudian jalankan contoh anda

### Pemasangan Volume Docker Windows

**Isu**: Penganalisis repositori Git melaporkan repositori kosong atau tiada fail

**Punca**: Pemasangan volume (`-v`) tidak berfungsi disebabkan konfigurasi perkongsian fail

**Penyelesaian**:
- **Disyorkan:** Tukar ke mod WSL 2 (Tetapan Docker Desktop → General → "Use the WSL 2 based engine")
- **Alternatif (Hyper-V):** Tambah direktori projek ke Tetapan Docker Desktop → Resources → File sharing, kemudian mulakan semula Docker Desktop

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan perkhidmatan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Walaupun kami berusaha untuk ketepatan, sila ambil maklum bahawa terjemahan automatik mungkin mengandungi kesilapan atau ketidaktepatan. Dokumen asal dalam bahasa asalnya harus dianggap sebagai sumber yang sahih. Untuk maklumat penting, terjemahan profesional oleh manusia adalah disyorkan. Kami tidak bertanggungjawab atas sebarang salah faham atau salah tafsir yang timbul daripada penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->