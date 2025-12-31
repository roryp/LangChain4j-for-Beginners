<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "1e85afe0b0ee47fc09b20442b0ee4ca5",
  "translation_date": "2025-12-23T10:24:02+00:00",
  "source_file": "README.md",
  "language_code": "ms"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b506e9588f734989dd106ebd9f977b7f784941a28b88348f0d6.ms.png" alt="LangChain4j" width="800"/>

### 🌐 Sokongan Pelbagai Bahasa

#### Disokong melalui GitHub Action (Automatik & Sentiasa Terkini)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arab](../ar/README.md) | [Benggali](../bn/README.md) | [Bahasa Bulgaria](../bg/README.md) | [Bahasa Burma (Myanmar)](../my/README.md) | [Cina (Dipermudahkan)](../zh/README.md) | [Cina (Tradisional, Hong Kong)](../hk/README.md) | [Cina (Tradisional, Macau)](../mo/README.md) | [Cina (Tradisional, Taiwan)](../tw/README.md) | [Bahasa Croatia](../hr/README.md) | [Bahasa Ceko](../cs/README.md) | [Bahasa Denmark](../da/README.md) | [Bahasa Belanda](../nl/README.md) | [Bahasa Estonia](../et/README.md) | [Bahasa Finland](../fi/README.md) | [Perancis](../fr/README.md) | [Jerman](../de/README.md) | [Yunani](../el/README.md) | [Ibrani](../he/README.md) | [Bahasa Hindi](../hi/README.md) | [Hungaria](../hu/README.md) | [Bahasa Indonesia](../id/README.md) | [Itali](../it/README.md) | [Jepun](../ja/README.md) | [Bahasa Kannada](../kn/README.md) | [Korea](../ko/README.md) | [Bahasa Lithuania](../lt/README.md) | [Melayu](./README.md) | [Bahasa Malayalam](../ml/README.md) | [Marathi](../mr/README.md) | [Nepali](../ne/README.md) | [Pidgin Nigeria](../pcm/README.md) | [Bahasa Norway](../no/README.md) | [Parsi (Farsi)](../fa/README.md) | [Poland](../pl/README.md) | [Portugis (Brazil)](../br/README.md) | [Portugis (Portugal)](../pt/README.md) | [Punjabi (Gurmukhi)](../pa/README.md) | [Romania](../ro/README.md) | [Rusia](../ru/README.md) | [Serbia (Sirilik)](../sr/README.md) | [Slovakia](../sk/README.md) | [Slovenia](../sl/README.md) | [Sepanyol](../es/README.md) | [Swahili](../sw/README.md) | [Swedia](../sv/README.md) | [Tagalog (Filipina)](../tl/README.md) | [Tamil](../ta/README.md) | [Telugu](../te/README.md) | [Thai](../th/README.md) | [Turki](../tr/README.md) | [Ukraine](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnam](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j untuk Pemula

Kursus untuk membina aplikasi AI dengan LangChain4j dan Azure OpenAI GPT-5, daripada sembang asas hingga ejen AI.

**Baru dengan LangChain4j?** Semak [Glosari](docs/GLOSSARY.md) untuk definisi istilah dan konsep utama.

## Isi Kandungan

1. [Permulaan Pantas](00-quick-start/README.md) - Mula dengan LangChain4j
2. [Pengenalan](01-introduction/README.md) - Pelajari asas-asas LangChain4j
3. [Kejuruteraan Prompt](02-prompt-engineering/README.md) - Kuasi reka bentuk prompt yang berkesan
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - Bina sistem berasaskan pengetahuan yang pintar
5. [Tools](04-tools/README.md) - Integrasikan alat dan API luaran dengan ejen AI
6. [MCP (Model Context Protocol)](05-mcp/README.md) - Bekerja dengan Protokol Konteks Model
---

##  Laluan Pembelajaran

> **Permulaan Pantas**

1. Fork repositori ini ke akaun GitHub anda
2. Klik **Code** → **Codespaces** tab → **...** → **New with options...**
3. Use the defaults – this will select the Development container created for this course
4. Klik **Create codespace**
5. Tunggu 5-10 minit untuk persekitaran sedia
6. Teruskan ke [Permulaan Pantas](./00-quick-start/README.md) untuk mula!

> **Lebih suka Mengklon Secara Tempatan?**
>
> Repositori ini mengandungi lebih 50 terjemahan bahasa yang meningkatkan saiz muat turun dengan ketara. Untuk mengklon tanpa terjemahan, gunakan sparse checkout:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> Ini memberi anda segala yang anda perlukan untuk menyelesaikan kursus dengan muat turun yang jauh lebih pantas.

Mula dengan modul [Permulaan Pantas](00-quick-start/README.md) dan maju melalui setiap modul untuk membina kemahiran anda langkah demi langkah. Anda akan mencuba contoh asas untuk memahami asas sebelum beralih ke modul [Pengenalan](01-introduction/README.md) untuk penerokaan lebih mendalam dengan GPT-5.

<img src="../../translated_images/learning-path.ac2da6720e77c3165960835627cef4c20eb2afb103be73a4f25b6d8fafbd738d.ms.png" alt="Laluan Pembelajaran" width="800"/>

Selepas menyelesaikan modul, terokai [Panduan Pengujian](docs/TESTING.md) untuk melihat konsep pengujian LangChain4j dalam tindakan.

> **Nota:** Latihan ini menggunakan kedua-dua GitHub Models dan Azure OpenAI. Modul [Permulaan Pantas](00-quick-start/README.md) dan [MCP](05-mcp/README.md) menggunakan GitHub Models (tiada langganan Azure diperlukan), manakala modul 1-4 menggunakan Azure OpenAI GPT-5.


## Pembelajaran dengan GitHub Copilot

Untuk mula menulis kod dengan cepat, buka projek ini dalam GitHub Codespace atau IDE tempatan anda dengan devcontainer yang disediakan. Devcontainer yang digunakan dalam kursus ini telah dipra-konfigurasikan dengan GitHub Copilot untuk pengaturcaraan berpasangan AI.

Setiap contoh kod termasuk soalan cadangan yang boleh anda tanyakan kepada GitHub Copilot untuk memperdalam pemahaman anda. Cari petunjuk 💡/🤖 dalam:

- **Java file headers** - Soalan spesifik untuk setiap contoh
- **Module READMEs** - Petunjuk eksplorasi selepas contoh kod

**Cara menggunakan:** Buka mana-mana fail kod dan tanyakan soalan yang dicadangkan kepada Copilot. Ia mempunyai konteks penuh kod dan boleh menerangkan, mengembangkan, dan mencadangkan alternatif.

Ingin belajar lebih lanjut? Lihat [Copilot untuk Pengaturcaraan Berpasangan AI](https://aka.ms/GitHubCopilotAI).


## Sumber Tambahan

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j untuk Pemula](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js untuk Pemula](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Agents
[![AZD untuk Pemula](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI untuk Pemula](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP untuk Pemula](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Ejen AI untuk Pemula](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Generative AI Series
[![AI Generatif untuk Pemula](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Generatif (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![AI Generatif (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![AI Generatif (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### Core Learning
[![ML untuk Pemula](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Sains Data untuk Pemula](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI untuk Pemula](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Keselamatan Siber untuk Pemula](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Pembangunan Web untuk Pemula](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT untuk Pemula](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)

[![Pembangunan XR untuk Pemula](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Siri Copilot
[![Copilot untuk Pengaturcaraan Berpasangan AI](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot untuk C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Pengembaraan Copilot](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## Mendapatkan Bantuan

Jika anda tersangkut atau mempunyai sebarang soalan mengenai membina aplikasi AI, sertai:

[![Discord Komuniti Azure AI Foundry](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

Jika anda mempunyai maklum balas mengenai produk atau menemui ralat semasa membina, lawati:

[![Forum Pembangun Azure AI Foundry](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## Lesen

Lesen MIT - Lihat fail [LICENSE](../../LICENSE) untuk butiran.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Penafian**:
Dokumen ini telah diterjemahkan menggunakan perkhidmatan terjemahan AI [Co-op Translator](https://github.com/Azure/co-op-translator). Walaupun kami berusaha untuk ketepatan, sila ambil maklum bahawa terjemahan automatik mungkin mengandungi ralat atau ketidaktepatan. Dokumen asal dalam bahasa asalnya hendaklah dianggap sebagai sumber rujukan yang muktamad. Bagi maklumat yang kritikal, penterjemahan profesional oleh penterjemah manusia adalah disyorkan. Kami tidak bertanggungjawab terhadap sebarang salah faham atau salah tafsir yang timbul daripada penggunaan terjemahan ini.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->