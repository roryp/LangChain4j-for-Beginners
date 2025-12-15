<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c3e07ca58d0b8a3f47d3bf5728541e0a",
  "translation_date": "2025-12-13T13:50:37+00:00",
  "source_file": "01-introduction/README.md",
  "language_code": "id"
}
-->
# Modul 01: Memulai dengan LangChain4j

## Daftar Isi

- [Apa yang Akan Anda Pelajari](../../../01-introduction)
- [Prasyarat](../../../01-introduction)
- [Memahami Masalah Inti](../../../01-introduction)
- [Memahami Token](../../../01-introduction)
- [Cara Kerja Memori](../../../01-introduction)
- [Bagaimana Ini Menggunakan LangChain4j](../../../01-introduction)
- [Menerapkan Infrastruktur Azure OpenAI](../../../01-introduction)
- [Menjalankan Aplikasi Secara Lokal](../../../01-introduction)
- [Menggunakan Aplikasi](../../../01-introduction)
  - [Chat Tanpa Status (Panel Kiri)](../../../01-introduction)
  - [Chat Dengan Status (Panel Kanan)](../../../01-introduction)
- [Langkah Selanjutnya](../../../01-introduction)

## Apa yang Akan Anda Pelajari

Jika Anda telah menyelesaikan quick start, Anda sudah melihat cara mengirim prompt dan mendapatkan respons. Itu adalah dasar, tetapi aplikasi nyata membutuhkan lebih. Modul ini mengajarkan Anda cara membangun AI percakapan yang mengingat konteks dan mempertahankan status - perbedaan antara demo sekali jalan dan aplikasi siap produksi.

Kami akan menggunakan GPT-5 dari Azure OpenAI sepanjang panduan ini karena kemampuan penalarannya yang canggih membuat perilaku pola yang berbeda menjadi lebih jelas. Saat Anda menambahkan memori, Anda akan melihat perbedaannya dengan jelas. Ini memudahkan untuk memahami apa yang dibawa setiap komponen ke aplikasi Anda.

Anda akan membangun satu aplikasi yang menunjukkan kedua pola:

**Chat Tanpa Status** - Setiap permintaan bersifat independen. Model tidak memiliki memori dari pesan sebelumnya. Ini adalah pola yang Anda gunakan di quick start.

**Percakapan Dengan Status** - Setiap permintaan menyertakan riwayat percakapan. Model mempertahankan konteks di beberapa putaran. Ini yang dibutuhkan aplikasi produksi.

## Prasyarat

- Langganan Azure dengan akses Azure OpenAI
- Java 21, Maven 3.9+
- Azure CLI (https://learn.microsoft.com/en-us/cli/azure/install-azure-cli)
- Azure Developer CLI (azd) (https://learn.microsoft.com/en-us/azure/developer/azure-developer-cli/install-azd)

> **Catatan:** Java, Maven, Azure CLI dan Azure Developer CLI (azd) sudah terpasang di devcontainer yang disediakan.

> **Catatan:** Modul ini menggunakan GPT-5 di Azure OpenAI. Deployment dikonfigurasi otomatis melalui `azd up` - jangan ubah nama model di kode.

## Memahami Masalah Inti

Model bahasa bersifat tanpa status. Setiap panggilan API bersifat independen. Jika Anda mengirim "Nama saya John" lalu bertanya "Siapa nama saya?", model tidak tahu Anda baru saja memperkenalkan diri. Model memperlakukan setiap permintaan seolah itu adalah percakapan pertama Anda.

Ini baik untuk tanya jawab sederhana tapi tidak berguna untuk aplikasi nyata. Bot layanan pelanggan perlu mengingat apa yang Anda katakan. Asisten pribadi perlu konteks. Setiap percakapan multi-putaran membutuhkan memori.

<img src="../../../translated_images/stateless-vs-stateful.cc4a4765e649c41a9edc2ee46cc997aae9055c2d7b4d37e785f5747879a405d8.id.png" alt="Percakapan Tanpa Status vs Dengan Status" width="800"/>

*Perbedaan antara percakapan tanpa status (panggilan independen) dan dengan status (sadar konteks)*

## Memahami Token

Sebelum masuk ke percakapan, penting untuk memahami token - unit dasar teks yang diproses model bahasa:

<img src="../../../translated_images/token-explanation.c39760d8ec6501817a47717a2caf355f251dbfa38ee244b425c89c207068fab8.id.png" alt="Penjelasan Token" width="800"/>

*Contoh bagaimana teks dipecah menjadi token - "I love AI!" menjadi 4 unit pemrosesan terpisah*

Token adalah cara model AI mengukur dan memproses teks. Kata, tanda baca, dan bahkan spasi bisa menjadi token. Model Anda memiliki batas berapa banyak token yang bisa diproses sekaligus (400.000 untuk GPT-5, dengan hingga 272.000 token input dan 128.000 token output). Memahami token membantu Anda mengelola panjang percakapan dan biaya.

## Cara Kerja Memori

Memori chat menyelesaikan masalah tanpa status dengan mempertahankan riwayat percakapan. Sebelum mengirim permintaan ke model, framework menambahkan pesan sebelumnya yang relevan. Saat Anda bertanya "Siapa nama saya?", sistem sebenarnya mengirim seluruh riwayat percakapan, memungkinkan model melihat Anda sebelumnya mengatakan "Nama saya John."

LangChain4j menyediakan implementasi memori yang menangani ini secara otomatis. Anda memilih berapa banyak pesan yang disimpan dan framework mengelola jendela konteks.

<img src="../../../translated_images/memory-window.bbe67f597eadabb3cce8f622a1239f2aa5e3a22a2d35a7baddb5ec6e5f12d78a.id.png" alt="Konsep Jendela Memori" width="800"/>

*MessageWindowChatMemory mempertahankan jendela geser pesan terbaru, secara otomatis menghapus pesan lama*

## Bagaimana Ini Menggunakan LangChain4j

Modul ini memperluas quick start dengan mengintegrasikan Spring Boot dan menambahkan memori percakapan. Berikut cara bagian-bagiannya terhubung:

**Dependencies** - Tambahkan dua pustaka LangChain4j:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

**Model Chat** - Konfigurasikan Azure OpenAI sebagai bean Spring ([LangChainConfig.java](../../../01-introduction/src/main/java/com/example/langchain4j/config/LangChainConfig.java)):

```java
@Bean
public OpenAiOfficialChatModel openAiOfficialChatModel() {
    return OpenAiOfficialChatModel.builder()
            .baseUrl(azureEndpoint)
            .apiKey(azureApiKey)
            .modelName(deploymentName)
            .timeout(Duration.ofMinutes(5))
            .maxRetries(3)
            .build();
}
```

Builder membaca kredensial dari variabel lingkungan yang diatur oleh `azd up`. Menetapkan `baseUrl` ke endpoint Azure Anda membuat klien OpenAI bekerja dengan Azure OpenAI.

**Memori Percakapan** - Melacak riwayat chat dengan MessageWindowChatMemory ([ConversationService.java](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java)):

```java
ChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

memory.add(UserMessage.from("My name is John"));
memory.add(AiMessage.from("Nice to meet you, John!"));

memory.add(UserMessage.from("What's my name?"));
AiMessage aiMessage = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage);
```

Buat memori dengan `withMaxMessages(10)` untuk menyimpan 10 pesan terakhir. Tambahkan pesan pengguna dan AI dengan pembungkus bertipe: `UserMessage.from(text)` dan `AiMessage.from(text)`. Ambil riwayat dengan `memory.messages()` dan kirim ke model. Service menyimpan instance memori terpisah per ID percakapan, memungkinkan banyak pengguna chat secara bersamaan.

> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`ConversationService.java`](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java) dan tanyakan:
> - "Bagaimana MessageWindowChatMemory memutuskan pesan mana yang dihapus saat jendela penuh?"
> - "Bisakah saya mengimplementasikan penyimpanan memori kustom menggunakan database daripada in-memory?"
> - "Bagaimana saya menambahkan ringkasan untuk mengompres riwayat percakapan lama?"

Endpoint chat tanpa status melewati memori sepenuhnya - hanya `chatModel.chat(prompt)` seperti quick start. Endpoint dengan status menambahkan pesan ke memori, mengambil riwayat, dan menyertakan konteks itu dengan setiap permintaan. Konfigurasi model sama, pola berbeda.

## Menerapkan Infrastruktur Azure OpenAI

**Bash:**
```bash
cd 01-introduction
azd up  # Pilih langganan dan lokasi (eastus2 direkomendasikan)
```

**PowerShell:**
```powershell
cd 01-introduction
azd up  # Pilih langganan dan lokasi (eastus2 direkomendasikan)
```

> **Catatan:** Jika Anda mengalami error timeout (`RequestConflict: Cannot modify resource ... provisioning state is not terminal`), cukup jalankan `azd up` lagi. Sumber daya Azure mungkin masih dalam proses penyediaan di latar belakang, dan mencoba ulang memungkinkan deployment selesai setelah sumber daya mencapai status terminal.

Ini akan:
1. Menerapkan sumber daya Azure OpenAI dengan model GPT-5 dan text-embedding-3-small
2. Secara otomatis menghasilkan file `.env` di root proyek dengan kredensial
3. Mengatur semua variabel lingkungan yang diperlukan

**Mengalami masalah deployment?** Lihat [README Infrastruktur](infra/README.md) untuk pemecahan masalah detail termasuk konflik nama subdomain, langkah manual deployment di Azure Portal, dan panduan konfigurasi model.

**Verifikasi deployment berhasil:**

**Bash:**
```bash
cat ../.env  # Harus menampilkan AZURE_OPENAI_ENDPOINT, API_KEY, dll.
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Harus menampilkan AZURE_OPENAI_ENDPOINT, API_KEY, dll.
```

> **Catatan:** Perintah `azd up` secara otomatis menghasilkan file `.env`. Jika Anda perlu memperbaruinya nanti, Anda bisa mengedit file `.env` secara manual atau menghasilkan ulang dengan menjalankan:
>
> **Bash:**
> ```bash
> cd ..
> bash .azd-env.sh
> ```
>
> **PowerShell:**
> ```powershell
> cd ..
> .\.azd-env.ps1
> ```

## Menjalankan Aplikasi Secara Lokal

**Verifikasi deployment:**

Pastikan file `.env` ada di direktori root dengan kredensial Azure:

**Bash:**
```bash
cat ../.env  # Harus menampilkan AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Harus menampilkan AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Mulai aplikasi:**

**Opsi 1: Menggunakan Spring Boot Dashboard (Direkomendasikan untuk pengguna VS Code)**

Dev container menyertakan ekstensi Spring Boot Dashboard, yang menyediakan antarmuka visual untuk mengelola semua aplikasi Spring Boot. Anda dapat menemukannya di Activity Bar di sisi kiri VS Code (cari ikon Spring Boot).

Dari Spring Boot Dashboard, Anda dapat:
- Melihat semua aplikasi Spring Boot yang tersedia di workspace
- Memulai/menghentikan aplikasi dengan satu klik
- Melihat log aplikasi secara real-time
- Memantau status aplikasi

Cukup klik tombol play di sebelah "introduction" untuk memulai modul ini, atau mulai semua modul sekaligus.

<img src="../../../translated_images/dashboard.69c7479aef09ff6bfac595fccc531dfc0cf87d02f3222492c0b1a324538275bd.id.png" alt="Spring Boot Dashboard" width="400"/>

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
cd 01-introduction
./start.sh
```

**PowerShell:**
```powershell
cd 01-introduction
.\start.ps1
```

Kedua skrip secara otomatis memuat variabel lingkungan dari file `.env` root dan akan membangun JAR jika belum ada.

> **Catatan:** Jika Anda lebih suka membangun semua modul secara manual sebelum memulai:
>
> **Bash:**
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
>
> **PowerShell:**
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

Buka http://localhost:8080 di browser Anda.

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

Aplikasi menyediakan antarmuka web dengan dua implementasi chat berdampingan.

<img src="../../../translated_images/home-screen.121a03206ab910c0e3a995fa310d86090166e80121096dcc23e89549548426ea.id.png" alt="Layar Beranda Aplikasi" width="800"/>

*Dashboard menampilkan opsi Simple Chat (tanpa status) dan Conversational Chat (dengan status)*

### Chat Tanpa Status (Panel Kiri)

Coba ini dulu. Tanyakan "Nama saya John" lalu segera tanya "Siapa nama saya?" Model tidak akan mengingat karena setiap pesan bersifat independen. Ini menunjukkan masalah inti integrasi model bahasa dasar - tidak ada konteks percakapan.

<img src="../../../translated_images/simple-chat-stateless-demo.13aeb3978eab32345806f17ba5438891e8b8295576f9857fe7fcb81376437fe2.id.png" alt="Demo Chat Tanpa Status" width="800"/>

*AI tidak mengingat nama Anda dari pesan sebelumnya*

### Chat Dengan Status (Panel Kanan)

Sekarang coba urutan yang sama di sini. Tanyakan "Nama saya John" lalu "Siapa nama saya?" Kali ini model mengingat. Perbedaannya adalah MessageWindowChatMemory - ia mempertahankan riwayat percakapan dan menyertakannya dengan setiap permintaan. Inilah cara AI percakapan produksi bekerja.

<img src="../../../translated_images/conversational-chat-stateful-demo.e5be9822eb23ff59f255427e2422b6a60bd331f1546cb28a4662571a3915e88f.id.png" alt="Demo Chat Dengan Status" width="800"/>

*AI mengingat nama Anda dari percakapan sebelumnya*

Kedua panel menggunakan model GPT-5 yang sama. Satu-satunya perbedaan adalah memori. Ini membuat jelas apa yang dibawa memori ke aplikasi Anda dan mengapa itu penting untuk kasus penggunaan nyata.

## Langkah Selanjutnya

**Modul Berikutnya:** [02-prompt-engineering - Rekayasa Prompt dengan GPT-5](../02-prompt-engineering/README.md)

---

**Navigasi:** [← Sebelumnya: Modul 00 - Quick Start](../00-quick-start/README.md) | [Kembali ke Utama](../README.md) | [Berikutnya: Modul 02 - Prompt Engineering →](../02-prompt-engineering/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan layanan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Meskipun kami berusaha untuk akurasi, harap diingat bahwa terjemahan otomatis mungkin mengandung kesalahan atau ketidakakuratan. Dokumen asli dalam bahasa aslinya harus dianggap sebagai sumber yang sahih. Untuk informasi penting, disarankan menggunakan terjemahan profesional oleh manusia. Kami tidak bertanggung jawab atas kesalahpahaman atau salah tafsir yang timbul dari penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->