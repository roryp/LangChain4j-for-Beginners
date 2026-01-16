<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "7dffae166c7db7dc932a0e3d0217cbb7",
  "translation_date": "2026-01-16T10:42:54+00:00",
  "source_file": "README.md",
  "language_code": "fi"
}
-->
<img src="../../translated_images/fi/LangChain4j.90e1d693fcc71b50.webp" alt="LangChain4j" width="800"/>

### 🌐 Monikielinen tuki

#### Tuettu GitHub Actionin kautta (automaattinen ja aina ajan tasalla)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[arabia](../ar/README.md) | [bengali](../bn/README.md) | [bulgaria](../bg/README.md) | [burma (Myanmar)](../my/README.md) | [kiina (yksinkertaistettu)](../zh/README.md) | [kiina (perinteinen, Hong Kong)](../hk/README.md) | [kiina (perinteinen, Macao)](../mo/README.md) | [kiina (perinteinen, Taiwan)](../tw/README.md) | [kroatia](../hr/README.md) | [tšekki](../cs/README.md) | [tanska](../da/README.md) | [hollanti](../nl/README.md) | [viro](../et/README.md) | [suomi](./README.md) | [ranska](../fr/README.md) | [saksa](../de/README.md) | [kreikka](../el/README.md) | [heprea](../he/README.md) | [hindi](../hi/README.md) | [unkari](../hu/README.md) | [indonesia](../id/README.md) | [italia](../it/README.md) | [japani](../ja/README.md) | [kannada](../kn/README.md) | [korea](../ko/README.md) | [liettua](../lt/README.md) | [malaiji](../ms/README.md) | [malajalami](../ml/README.md) | [marathi](../mr/README.md) | [nepali](../ne/README.md) | [nigerian pidgin](../pcm/README.md) | [norja](../no/README.md) | [persia (farsi)](../fa/README.md) | [puola](../pl/README.md) | [portugali (Brasilia)](../br/README.md) | [portugali (Portugali)](../pt/README.md) | [punjabi (gurmukhi)](../pa/README.md) | [romania](../ro/README.md) | [venäjä](../ru/README.md) | [serbia (kyrillinen)](../sr/README.md) | [slovakki](../sk/README.md) | [sloveeni](../sl/README.md) | [espanja](../es/README.md) | [swahili](../sw/README.md) | [ruotsi](../sv/README.md) | [tagalog (filipino)](../tl/README.md) | [tamili](../ta/README.md) | [telugu](../te/README.md) | [thai](../th/README.md) | [turkki](../tr/README.md) | [ukraina](../uk/README.md) | [urdu](../ur/README.md) | [vietnam](../vi/README.md)

> **Haluatko mieluummin kloonata paikallisesti?**

> Tämä repository sisältää yli 50 kielen käännökset, mikä kasvattaa merkittävästi latauskokoa. Jos haluat kloonata ilman käännöksiä, käytä rajatun sisällön checkoutia:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> Saat kaiken tarvitsemasi kurssin suorittamiseen paljon nopeammalla latauksella.
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j aloittelijoille

Kurssi tekoälysovellusten rakentamisesta LangChain4j:llä ja Azure OpenAI GPT-5:llä, alkaen peruskeskusteluista tekoälyagentteihin.

**Uusi LangChain4j:ssä?** Tutustu [sanastoon](docs/GLOSSARY.md) avainten termien ja käsitteiden selityksille.

## Sisällysluettelo

1. [Nopea aloitus](00-quick-start/README.md) - Aloita LangChain4j:n kanssa
2. [Johdanto](01-introduction/README.md) - Opi LangChain4j:n perusteet
3. [Kehote-suunnittelu](02-prompt-engineering/README.md) - Hallitse tehokas kehote-suunnittelu
4. [RAG (Hakuavusteinen generaattori)](03-rag/README.md) - Rakenna älykkäitä tietopohjaisia järjestelmiä
5. [Työkalut](04-tools/README.md) - Integroi ulkoisia työkaluja ja yksinkertaisia avustajia
6. [MCP (Mallin kontekstiprotokolla)](05-mcp/README.md) - Työskentele Mallin kontekstiprotokollan (MCP) ja agenttimoduulien kanssa
---

## Oppimispolku

> **Nopea aloitus**

1. Tee haarukka tästä repositoriosta GitHub-tilillesi
2. Klikkaa **Code** → **Codespaces** -välilehti → **...** → **Uusi vaihtoehdoilla...**
3. Käytä oletuksia – tämä valitsee tälle kurssille tehdyn kehityskontin
4. Klikkaa **Luo codespace**
5. Odota 5-10 minuuttia ympäristön valmistumista
6. Mene suoraan [Nopea aloitus](./00-quick-start/README.md) -osioon aloittaaksesi!

Kun modulit on suoritettu, tutustu [Testausoppaaseen](docs/TESTING.md) nähdäksesi LangChain4j testauksen käsitteitä käytännössä.

> **Huom:** Tässä koulutuksessa käytetään sekä GitHub-malleja että Azure OpenAI:ta. [Nopea aloitus](00-quick-start/README.md) käyttää GitHub-malleja (ei tarvitse Azure-tilausta), kun moduulit 1-5 käyttävät Azure OpenAI:ta.


## Oppiminen GitHub Copilotin kanssa

Aloita koodaus nopeasti avaamalla tämä projekti GitHub Codespacessa tai paikallisessa IDE:ssä mukana toimitetulla devcontainerilla. Tämän kurssin devcontainer on esikonfiguroitu GitHub Copilotilla tekoälypare-ohjelmointiin.

Jokaisessa koodiesimerkissä on ehdotettuja kysymyksiä, joita voit kysyä GitHub Copilotilta syventääksesi ymmärrystäsi. Etsi 💡/🤖 kehotteet:

- **Java-tiedostojen otsikoissa** - Esimerkkiin liittyvät kysymykset
- **Moduulien README-tiedostoissa** - Koodiesimerkkien jälkeiset tutkimuskysymykset

**Käyttöohje:** Avaa mikä tahansa kooditiedosto ja kysy Copilotilta ehdotetut kysymykset. Se tuntee koko koodikannan ja voi selittää, laajentaa ja ehdottaa vaihtoehtoja.

Haluatko oppia lisää? Katso [Copilot tekoälypare-ohjelmointiin](https://aka.ms/GitHubCopilotAI).


## Lisäresurssit

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j aloittelijoille](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js aloittelijoille](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Agentit
[![AZD aloittelijoille](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI aloittelijoille](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP aloittelijoille](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Tekoälyagentit aloittelijoille](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Generatiivinen tekoäly -sarja
[![Generatiivinen tekoäly aloittelijoille](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generatiivinen tekoäly (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generatiivinen tekoäly (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generatiivinen tekoäly (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### Ydintuntemus
[![ML aloittelijoille](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data science aloittelijoille](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![Tekoäly aloittelijoille](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Kyberturvallisuus aloittelijoille](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web-kehitys aloittelijoille](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT aloittelijoille](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR-kehitys aloittelijoille](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot-sarja

[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## Apua saatavilla

Jos jumitut tai sinulla on kysyttävää tekoälysovellusten rakentamisesta, liity:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

Jos sinulla on palautetta tuotteesta tai virheitä rakentamisen aikana, käy:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## Lisenssi

MIT-lisenssi - katso yksityiskohdat [LICENSE](../../LICENSE) tiedostosta.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastuuvapauslauseke**:  
Tämä asiakirja on käännetty tekoälypohjaisella käännöspalvelulla [Co-op Translator](https://github.com/Azure/co-op-translator). Vaikka pyrimme tarkkuuteen, on hyvä tiedostaa, että automaattikäännöksissä voi esiintyä virheitä tai epätarkkuuksia. Alkuperäinen asiakirja sen omalla kielellä on aina virallinen lähde. Tärkeissä asioissa suositellaan ammattimaista ihmiskäännöstä. Emme ole vastuussa tämän käännöksen käytöstä aiheutuvista väärinymmärryksistä tai tulkinnoista.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->