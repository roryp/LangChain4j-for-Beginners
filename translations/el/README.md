<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "1dccdb1a8e2b8ed18e6dea22e823c608",
  "translation_date": "2025-12-19T08:34:40+00:00",
  "source_file": "README.md",
  "language_code": "el"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b506e9588f734989dd106ebd9f977b7f784941a28b88348f0d6.el.png" alt="LangChain4j" width="800"/>

### 🌐 Υποστήριξη Πολλών Γλωσσών

#### Υποστηρίζεται μέσω GitHub Action (Αυτοματοποιημένο & Πάντα Ενημερωμένο)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arabic](../ar/README.md) | [Bengali](../bn/README.md) | [Bulgarian](../bg/README.md) | [Burmese (Myanmar)](../my/README.md) | [Chinese (Simplified)](../zh/README.md) | [Chinese (Traditional, Hong Kong)](../hk/README.md) | [Chinese (Traditional, Macau)](../mo/README.md) | [Chinese (Traditional, Taiwan)](../tw/README.md) | [Croatian](../hr/README.md) | [Czech](../cs/README.md) | [Danish](../da/README.md) | [Dutch](../nl/README.md) | [Estonian](../et/README.md) | [Finnish](../fi/README.md) | [French](../fr/README.md) | [German](../de/README.md) | [Greek](./README.md) | [Hebrew](../he/README.md) | [Hindi](../hi/README.md) | [Hungarian](../hu/README.md) | [Indonesian](../id/README.md) | [Italian](../it/README.md) | [Japanese](../ja/README.md) | [Kannada](../kn/README.md) | [Korean](../ko/README.md) | [Lithuanian](../lt/README.md) | [Malay](../ms/README.md) | [Malayalam](../ml/README.md) | [Marathi](../mr/README.md) | [Nepali](../ne/README.md) | [Nigerian Pidgin](../pcm/README.md) | [Norwegian](../no/README.md) | [Persian (Farsi)](../fa/README.md) | [Polish](../pl/README.md) | [Portuguese (Brazil)](../br/README.md) | [Portuguese (Portugal)](../pt/README.md) | [Punjabi (Gurmukhi)](../pa/README.md) | [Romanian](../ro/README.md) | [Russian](../ru/README.md) | [Serbian (Cyrillic)](../sr/README.md) | [Slovak](../sk/README.md) | [Slovenian](../sl/README.md) | [Spanish](../es/README.md) | [Swahili](../sw/README.md) | [Swedish](../sv/README.md) | [Tagalog (Filipino)](../tl/README.md) | [Tamil](../ta/README.md) | [Telugu](../te/README.md) | [Thai](../th/README.md) | [Turkish](../tr/README.md) | [Ukrainian](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnamese](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j για Αρχάριους

Ένα μάθημα για την κατασκευή εφαρμογών AI με LangChain4j και Azure OpenAI GPT-5, από βασική συνομιλία έως πράκτορες AI.

**Νέος στο LangChain4j;** Δείτε το [Γλωσσάρι](docs/GLOSSARY.md) για ορισμούς βασικών όρων και εννοιών.

## Πίνακας Περιεχομένων

1. [Γρήγορη Εκκίνηση](00-quick-start/README.md) - Ξεκινήστε με το LangChain4j  
2. [Εισαγωγή](01-introduction/README.md) - Μάθετε τα βασικά του LangChain4j  
3. [Μηχανική Προτροπής](02-prompt-engineering/README.md) - Κατακτήστε τον αποτελεσματικό σχεδιασμό προτροπών  
4. [RAG (Ανάκτηση-Ενισχυμένη Γενιά)](03-rag/README.md) - Δημιουργήστε έξυπνα συστήματα βασισμένα στη γνώση  
5. [Εργαλεία](04-tools/README.md) - Ενσωματώστε εξωτερικά εργαλεία και APIs με πράκτορες AI  
6. [MCP (Πρωτόκολλο Πλαισίου Μοντέλου)](05-mcp/README.md) - Εργαστείτε με το Πρωτόκολλο Πλαισίου Μοντέλου  
---

## Μονοπάτι Μάθησης

> **Γρήγορη Εκκίνηση**

1. Κάντε fork αυτό το αποθετήριο στον λογαριασμό σας στο GitHub  
2. Κάντε κλικ στο **Code** → καρτέλα **Codespaces** → **...** → **Νέο με επιλογές...**  
3. Χρησιμοποιήστε τις προεπιλογές – αυτό θα επιλέξει το κοντέινερ ανάπτυξης που δημιουργήθηκε για αυτό το μάθημα  
4. Κάντε κλικ στο **Create codespace**  
5. Περιμένετε 5-10 λεπτά για να είναι έτοιμο το περιβάλλον  
6. Πηγαίνετε απευθείας στο [Γρήγορη Εκκίνηση](./00-quick-start/README.md) για να ξεκινήσετε!

> **Προτιμάτε να κάνετε κλωνοποίηση τοπικά;**  
>  
> Αυτό το αποθετήριο περιλαμβάνει πάνω από 50 μεταφράσεις γλωσσών που αυξάνουν σημαντικά το μέγεθος λήψης. Για κλωνοποίηση χωρίς μεταφράσεις, χρησιμοποιήστε sparse checkout:  
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> Αυτό σας δίνει όλα όσα χρειάζεστε για να ολοκληρώσετε το μάθημα με πολύ πιο γρήγορη λήψη.

Ξεκινήστε με το μάθημα [Γρήγορη Εκκίνηση](00-quick-start/README.md) και προχωρήστε σε κάθε ενότητα για να αναπτύξετε τις δεξιότητές σας βήμα-βήμα. Θα δοκιμάσετε βασικά παραδείγματα για να κατανοήσετε τα θεμελιώδη πριν προχωρήσετε στην ενότητα [Εισαγωγή](01-introduction/README.md) για πιο βαθιά εμβάθυνση με το GPT-5.

<img src="../../translated_images/learning-path.ac2da6720e77c3165960835627cef4c20eb2afb103be73a4f25b6d8fafbd738d.el.png" alt="Learning Path" width="800"/>

Μετά την ολοκλήρωση των ενοτήτων, εξερευνήστε τον [Οδηγό Δοκιμών](docs/TESTING.md) για να δείτε τις έννοιες δοκιμών του LangChain4j σε δράση.

> **Σημείωση:** Αυτή η εκπαίδευση χρησιμοποιεί τόσο τα GitHub Models όσο και το Azure OpenAI. Οι ενότητες [Γρήγορη Εκκίνηση](00-quick-start/README.md) και [MCP](05-mcp/README.md) χρησιμοποιούν τα GitHub Models (δεν απαιτείται συνδρομή Azure), ενώ οι ενότητες 1-4 χρησιμοποιούν το Azure OpenAI GPT-5.


## Μάθηση με το GitHub Copilot

Για να ξεκινήσετε γρήγορα την κωδικοποίηση, ανοίξτε αυτό το έργο σε ένα GitHub Codespace ή στο τοπικό σας IDE με το παρεχόμενο devcontainer. Το devcontainer που χρησιμοποιείται σε αυτό το μάθημα έρχεται προδιαμορφωμένο με το GitHub Copilot για προγραμματισμό AI σε ζευγάρι.

Κάθε παράδειγμα κώδικα περιλαμβάνει προτεινόμενες ερωτήσεις που μπορείτε να κάνετε στο GitHub Copilot για να εμβαθύνετε την κατανόησή σας. Αναζητήστε τις ενδείξεις 💡/🤖 σε:

- **Κεφαλίδες αρχείων Java** - Ερωτήσεις ειδικές για κάθε παράδειγμα  
- **README των ενοτήτων** - Ερωτήσεις εξερεύνησης μετά από παραδείγματα κώδικα  

**Πώς να το χρησιμοποιήσετε:** Ανοίξτε οποιοδήποτε αρχείο κώδικα και κάντε τις προτεινόμενες ερωτήσεις στο Copilot. Έχει πλήρες πλαίσιο του κώδικα και μπορεί να εξηγήσει, να επεκτείνει και να προτείνει εναλλακτικές.

Θέλετε να μάθετε περισσότερα; Δείτε το [Copilot για Προγραμματισμό AI σε Ζευγάρι](https://aka.ms/GitHubCopilotAI).


## Πρόσθετοι Πόροι

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j για Αρχάριους](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js για Αρχάριους](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Πράκτορες
[![AZD για Αρχάριους](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI για Αρχάριους](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP για Αρχάριους](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Πράκτορες AI για Αρχάριους](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Σειρά Γενετικής Τεχνητής Νοημοσύνης
[![Γενετική Τεχνητή Νοημοσύνη για Αρχάριους](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Γενετική Τεχνητή Νοημοσύνη (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Γενετική Τεχνητή Νοημοσύνη (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Γενετική Τεχνητή Νοημοσύνη (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### Βασική Μάθηση
[![Μηχανική Μάθηση για Αρχάριους](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Επιστήμη Δεδομένων για Αρχάριους](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![Τεχνητή Νοημοσύνη για Αρχάριους](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Κυβερνοασφάλεια για Αρχάριους](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Ανάπτυξη Ιστού για Αρχάριους](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT για Αρχάριους](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development for Beginners](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Σειρά Copilot
[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## Λήψη Βοήθειας

Αν κολλήσετε ή έχετε οποιεσδήποτε ερωτήσεις σχετικά με την κατασκευή εφαρμογών AI, συμμετάσχετε:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

Αν έχετε σχόλια για το προϊόν ή σφάλματα κατά την κατασκευή, επισκεφθείτε:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## Άδεια

Άδεια MIT - Δείτε το αρχείο [LICENSE](../../LICENSE) για λεπτομέρειες.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Αποποίηση ευθυνών**:  
Αυτό το έγγραφο έχει μεταφραστεί χρησιμοποιώντας την υπηρεσία αυτόματης μετάφρασης AI [Co-op Translator](https://github.com/Azure/co-op-translator). Παρόλο που επιδιώκουμε την ακρίβεια, παρακαλούμε να λάβετε υπόψη ότι οι αυτόματες μεταφράσεις ενδέχεται να περιέχουν λάθη ή ανακρίβειες. Το πρωτότυπο έγγραφο στη μητρική του γλώσσα πρέπει να θεωρείται η αυθεντική πηγή. Για κρίσιμες πληροφορίες, συνιστάται επαγγελματική ανθρώπινη μετάφραση. Δεν φέρουμε ευθύνη για τυχόν παρεξηγήσεις ή λανθασμένες ερμηνείες που προκύπτουν από τη χρήση αυτής της μετάφρασης.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->