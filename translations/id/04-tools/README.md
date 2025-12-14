<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "aa23f106e7f53270924c9dd39c629004",
  "translation_date": "2025-12-13T19:10:10+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "id"
}
-->
# Modul 04: Agen AI dengan Alat

## Daftar Isi

- [Apa yang Akan Anda Pelajari](../../../04-tools)
- [Prasyarat](../../../04-tools)
- [Memahami Agen AI dengan Alat](../../../04-tools)
- [Cara Kerja Pemanggilan Alat](../../../04-tools)
  - [Definisi Alat](../../../04-tools)
  - [Pengambilan Keputusan](../../../04-tools)
  - [Eksekusi](../../../04-tools)
  - [Pembuatan Respons](../../../04-tools)
- [Rangkaian Alat](../../../04-tools)
- [Jalankan Aplikasi](../../../04-tools)
- [Menggunakan Aplikasi](../../../04-tools)
  - [Coba Penggunaan Alat Sederhana](../../../04-tools)
  - [Uji Rangkaian Alat](../../../04-tools)
  - [Lihat Alur Percakapan](../../../04-tools)
  - [Amati Penalaran](../../../04-tools)
  - [Eksperimen dengan Permintaan Berbeda](../../../04-tools)
- [Konsep Kunci](../../../04-tools)
  - [Pola ReAct (Penalaran dan Bertindak)](../../../04-tools)
  - [Deskripsi Alat Penting](../../../04-tools)
  - [Manajemen Sesi](../../../04-tools)
  - [Penanganan Kesalahan](../../../04-tools)
- [Alat yang Tersedia](../../../04-tools)
- [Kapan Menggunakan Agen Berbasis Alat](../../../04-tools)
- [Langkah Selanjutnya](../../../04-tools)

## Apa yang Akan Anda Pelajari

Sejauh ini, Anda telah belajar bagaimana melakukan percakapan dengan AI, menyusun prompt secara efektif, dan mendasarkan respons pada dokumen Anda. Namun masih ada keterbatasan mendasar: model bahasa hanya dapat menghasilkan teks. Mereka tidak bisa memeriksa cuaca, melakukan perhitungan, mengquery basis data, atau berinteraksi dengan sistem eksternal.

Alat mengubah ini. Dengan memberikan model akses ke fungsi yang dapat dipanggil, Anda mengubahnya dari generator teks menjadi agen yang dapat mengambil tindakan. Model memutuskan kapan ia membutuhkan alat, alat mana yang digunakan, dan parameter apa yang diberikan. Kode Anda mengeksekusi fungsi dan mengembalikan hasilnya. Model menggabungkan hasil itu ke dalam responsnya.

## Prasyarat

- Menyelesaikan Modul 01 (sumber daya Azure OpenAI sudah dideploy)
- File `.env` di direktori root dengan kredensial Azure (dibuat oleh `azd up` di Modul 01)

> **Catatan:** Jika Anda belum menyelesaikan Modul 01, ikuti instruksi deployment di sana terlebih dahulu.

## Memahami Agen AI dengan Alat

Agen AI dengan alat mengikuti pola penalaran dan bertindak (ReAct):

1. Pengguna mengajukan pertanyaan
2. Agen menganalisis apa yang perlu diketahuinya
3. Agen memutuskan apakah perlu alat untuk menjawab
4. Jika ya, agen memanggil alat yang sesuai dengan parameter yang tepat
5. Alat mengeksekusi dan mengembalikan data
6. Agen menggabungkan hasil dan memberikan jawaban akhir

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13ae5b0218d4e91befabc04e00f97539df14f93d1ad9b8516f.id.png" alt="Pola ReAct" width="800"/>

*Pola ReAct - bagaimana agen AI bergantian antara penalaran dan bertindak untuk memecahkan masalah*

Ini terjadi secara otomatis. Anda mendefinisikan alat dan deskripsinya. Model menangani pengambilan keputusan tentang kapan dan bagaimana menggunakannya.

## Cara Kerja Pemanggilan Alat

**Definisi Alat** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Anda mendefinisikan fungsi dengan deskripsi yang jelas dan spesifikasi parameter. Model melihat deskripsi ini dalam prompt sistemnya dan memahami apa yang dilakukan setiap alat.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Logika pencarian cuaca Anda
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Asisten secara otomatis dihubungkan oleh Spring Boot dengan:
// - Bean ChatModel
// - Semua metode @Tool dari kelas @Component
// - ChatMemoryProvider untuk manajemen sesi
```

> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) dan tanyakan:
> - "Bagaimana saya mengintegrasikan API cuaca nyata seperti OpenWeatherMap daripada data tiruan?"
> - "Apa yang membuat deskripsi alat yang baik sehingga membantu AI menggunakannya dengan benar?"
> - "Bagaimana saya menangani kesalahan API dan batasan rate dalam implementasi alat?"

**Pengambilan Keputusan**

Ketika pengguna bertanya "Bagaimana cuaca di Seattle?", model mengenali bahwa ia membutuhkan alat cuaca. Ia menghasilkan panggilan fungsi dengan parameter lokasi diatur ke "Seattle".

**Eksekusi** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot secara otomatis menghubungkan interface deklaratif `@AiService` dengan semua alat yang terdaftar, dan LangChain4j mengeksekusi panggilan alat secara otomatis.

> **🤖 Coba dengan [GitHub Copilot](https://github.com/features/copilot) Chat:** Buka [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) dan tanyakan:
> - "Bagaimana pola ReAct bekerja dan mengapa efektif untuk agen AI?"
> - "Bagaimana agen memutuskan alat mana yang digunakan dan dalam urutan apa?"
> - "Apa yang terjadi jika eksekusi alat gagal - bagaimana saya harus menangani kesalahan dengan kuat?"

**Pembuatan Respons**

Model menerima data cuaca dan memformatnya menjadi respons bahasa alami untuk pengguna.

### Mengapa Menggunakan Layanan AI Deklaratif?

Modul ini menggunakan integrasi LangChain4j dengan Spring Boot melalui interface deklaratif `@AiService`:

- **Spring Boot auto-wiring** - ChatModel dan alat disuntikkan otomatis
- **Pola @MemoryId** - Manajemen memori berbasis sesi otomatis
- **Instance tunggal** - Asisten dibuat sekali dan digunakan ulang untuk performa lebih baik
- **Eksekusi tipe-aman** - Metode Java dipanggil langsung dengan konversi tipe
- **Orkestrasi multi-turn** - Menangani rangkaian alat secara otomatis
- **Tanpa boilerplate** - Tidak perlu panggilan manual AiServices.builder() atau HashMap memori

Pendekatan alternatif (manual `AiServices.builder()`) membutuhkan lebih banyak kode dan kehilangan manfaat integrasi Spring Boot.

## Rangkaian Alat

**Rangkaian Alat** - AI mungkin memanggil beberapa alat secara berurutan. Tanyakan "Bagaimana cuaca di Seattle dan apakah saya harus membawa payung?" dan lihat bagaimana ia merangkai `getCurrentWeather` dengan penalaran tentang perlengkapan hujan.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b1d54117d54ba382c21c51176aaf3800084cae2e7dfc82508.id.png" alt="Rangkaian Alat" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Panggilan alat berurutan - output satu alat menjadi input keputusan berikutnya*

**Kegagalan yang Anggun** - Minta cuaca di kota yang tidak ada dalam data tiruan. Alat mengembalikan pesan kesalahan, dan AI menjelaskan bahwa ia tidak bisa membantu. Alat gagal dengan aman.

Ini terjadi dalam satu putaran percakapan. Agen mengorkestrasi beberapa panggilan alat secara mandiri.

## Jalankan Aplikasi

**Verifikasi deployment:**

Pastikan file `.env` ada di direktori root dengan kredensial Azure (dibuat selama Modul 01):
```bash
cat ../.env  # Harus menampilkan AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Mulai aplikasi:**

> **Catatan:** Jika Anda sudah memulai semua aplikasi menggunakan `./start-all.sh` dari Modul 01, modul ini sudah berjalan di port 8084. Anda bisa melewati perintah start di bawah dan langsung ke http://localhost:8084.

**Opsi 1: Menggunakan Spring Boot Dashboard (Direkomendasikan untuk pengguna VS Code)**

Dev container menyertakan ekstensi Spring Boot Dashboard, yang menyediakan antarmuka visual untuk mengelola semua aplikasi Spring Boot. Anda dapat menemukannya di Activity Bar di sisi kiri VS Code (cari ikon Spring Boot).

Dari Spring Boot Dashboard, Anda dapat:
- Melihat semua aplikasi Spring Boot yang tersedia di workspace
- Memulai/menghentikan aplikasi dengan satu klik
- Melihat log aplikasi secara real-time
- Memantau status aplikasi

Cukup klik tombol play di sebelah "tools" untuk memulai modul ini, atau mulai semua modul sekaligus.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30af495a594f5c0213fecdbdf5bd9fb543d3c5467565773974a.id.png" alt="Spring Boot Dashboard" width="400"/>

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
cd 04-tools
./start.sh
```

**PowerShell:**
```powershell
cd 04-tools
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

Buka http://localhost:8084 di browser Anda.

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

Aplikasi menyediakan antarmuka web di mana Anda dapat berinteraksi dengan agen AI yang memiliki akses ke alat cuaca dan konversi suhu.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f96216024b45b493ca1cd84935d6856416ea7a383b42f280d648c.id.png" alt="Antarmuka Alat Agen AI" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Antarmuka Alat Agen AI - contoh cepat dan antarmuka chat untuk berinteraksi dengan alat*

**Coba Penggunaan Alat Sederhana**

Mulailah dengan permintaan sederhana: "Konversi 100 derajat Fahrenheit ke Celsius". Agen mengenali bahwa ia membutuhkan alat konversi suhu, memanggilnya dengan parameter yang tepat, dan mengembalikan hasilnya. Perhatikan betapa alami rasanya - Anda tidak perlu menentukan alat mana yang digunakan atau bagaimana memanggilnya.

**Uji Rangkaian Alat**

Sekarang coba sesuatu yang lebih kompleks: "Bagaimana cuaca di Seattle dan konversikan ke Fahrenheit?" Amati agen bekerja melalui langkah-langkah ini. Ia pertama mendapatkan cuaca (yang mengembalikan dalam Celsius), mengenali bahwa ia perlu mengonversi ke Fahrenheit, memanggil alat konversi, dan menggabungkan kedua hasil menjadi satu respons.

**Lihat Alur Percakapan**

Antarmuka chat menyimpan riwayat percakapan, memungkinkan Anda melakukan interaksi multi-putaran. Anda dapat melihat semua pertanyaan dan respons sebelumnya, memudahkan melacak percakapan dan memahami bagaimana agen membangun konteks selama beberapa pertukaran.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f596acc43e227bf70f3c0d6030ad91d84df81070abf08848608.id.png" alt="Percakapan dengan Beberapa Panggilan Alat" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Percakapan multi-putaran yang menunjukkan konversi sederhana, pencarian cuaca, dan rangkaian alat*

**Eksperimen dengan Permintaan Berbeda**

Coba berbagai kombinasi:
- Pencarian cuaca: "Bagaimana cuaca di Tokyo?"
- Konversi suhu: "Berapa 25°C dalam Kelvin?"
- Query gabungan: "Periksa cuaca di Paris dan beri tahu saya jika di atas 20°C"

Perhatikan bagaimana agen menginterpretasikan bahasa alami dan memetakan ke panggilan alat yang sesuai.

## Konsep Kunci

**Pola ReAct (Penalaran dan Bertindak)**

Agen bergantian antara penalaran (memutuskan apa yang harus dilakukan) dan bertindak (menggunakan alat). Pola ini memungkinkan pemecahan masalah secara otonom daripada hanya merespons instruksi.

**Deskripsi Alat Penting**

Kualitas deskripsi alat Anda secara langsung memengaruhi seberapa baik agen menggunakannya. Deskripsi yang jelas dan spesifik membantu model memahami kapan dan bagaimana memanggil setiap alat.

**Manajemen Sesi**

Anotasi `@MemoryId` memungkinkan manajemen memori berbasis sesi otomatis. Setiap ID sesi mendapatkan instance `ChatMemory` sendiri yang dikelola oleh bean `ChatMemoryProvider`, menghilangkan kebutuhan pelacakan memori manual.

**Penanganan Kesalahan**

Alat bisa gagal - API timeout, parameter mungkin tidak valid, layanan eksternal turun. Agen produksi membutuhkan penanganan kesalahan agar model dapat menjelaskan masalah atau mencoba alternatif.

## Alat yang Tersedia

**Alat Cuaca** (data tiruan untuk demonstrasi):
- Mendapatkan cuaca saat ini untuk lokasi
- Mendapatkan prakiraan multi-hari

**Alat Konversi Suhu**:
- Celsius ke Fahrenheit
- Fahrenheit ke Celsius
- Celsius ke Kelvin
- Kelvin ke Celsius
- Fahrenheit ke Kelvin
- Kelvin ke Fahrenheit

Ini adalah contoh sederhana, tetapi pola ini dapat diperluas ke fungsi apa pun: query basis data, panggilan API, perhitungan, operasi file, atau perintah sistem.

## Kapan Menggunakan Agen Berbasis Alat

**Gunakan alat ketika:**
- Menjawab membutuhkan data waktu nyata (cuaca, harga saham, inventaris)
- Anda perlu melakukan perhitungan lebih dari matematika sederhana
- Mengakses basis data atau API
- Mengambil tindakan (mengirim email, membuat tiket, memperbarui catatan)
- Menggabungkan beberapa sumber data

**Jangan gunakan alat ketika:**
- Pertanyaan dapat dijawab dari pengetahuan umum
- Respons hanya bersifat percakapan
- Latensi alat akan membuat pengalaman terlalu lambat

## Langkah Selanjutnya

**Modul Berikutnya:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navigasi:** [← Sebelumnya: Modul 03 - RAG](../03-rag/README.md) | [Kembali ke Utama](../README.md) | [Berikutnya: Modul 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:  
Dokumen ini telah diterjemahkan menggunakan layanan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Meskipun kami berusaha untuk akurasi, harap diingat bahwa terjemahan otomatis mungkin mengandung kesalahan atau ketidakakuratan. Dokumen asli dalam bahasa aslinya harus dianggap sebagai sumber yang sahih. Untuk informasi penting, disarankan menggunakan terjemahan profesional oleh manusia. Kami tidak bertanggung jawab atas kesalahpahaman atau salah tafsir yang timbul dari penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->