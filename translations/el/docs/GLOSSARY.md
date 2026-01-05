<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T00:53:08+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "el"
}
-->
# Γλωσσάριο LangChain4j

## Πίνακας Περιεχομένων

- [Βασικές Έννοιες](../../../docs)
- [Συστατικά του LangChain4j](../../../docs)
- [Έννοιες AI/ML](../../../docs)
- [Μηχανική προτροπών](../../../docs)
- [RAG (Ανάκτηση-Ενισχυμένη Γενιά)](../../../docs)
- [Πράκτορες και Εργαλεία](../../../docs)
- [Πρωτόκολλο Συμφραζόμενου Μοντέλου (MCP)](../../../docs)
- [Υπηρεσίες Azure](../../../docs)
- [Δοκιμές και Ανάπτυξη](../../../docs)

Γρήγορη αναφορά για όρους και έννοιες που χρησιμοποιούνται σε όλο το μάθημα.

## Βασικές Έννοιες

**AI Agent** - Σύστημα που χρησιμοποιεί τεχνητή νοημοσύνη για να συλλογίζεται και να ενεργεί αυτόνομα. [Μονάδα 04](../04-tools/README.md)

**Chain** - Ακολουθία λειτουργιών όπου η έξοδος τροφοδοτεί το επόμενο βήμα.

**Chunking** - Διάσπαση εγγράφων σε μικρότερα κομμάτια. Τυπικά: 300-500 tokens με overlap. [Μονάδα 03](../03-rag/README.md)

**Context Window** - Το μέγιστο πλήθος token που ένα μοντέλο μπορεί να επεξεργαστεί. GPT-5: 400K tokens.

**Embeddings** - Αριθμητικοί διανύσματα που αντιπροσωπεύουν το νόημα κειμένου. [Μονάδα 03](../03-rag/README.md)

**Function Calling** - Το μοντέλο δημιουργεί δομημένα αιτήματα για να καλέσει εξωτερικές συναρτήσεις. [Μονάδα 04](../04-tools/README.md)

**Hallucination** - Όταν τα μοντέλα παράγουν λανθασμένες αλλά πιθανοφανείς πληροφορίες.

**Prompt** - Κείμενο εισόδου σε ένα γλωσσικό μοντέλο. [Μονάδα 02](../02-prompt-engineering/README.md)

**Semantic Search** - Αναζήτηση με βάση το νόημα χρησιμοποιώντας embeddings, όχι λέξεις-κλειδιά. [Μονάδα 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: χωρίς μνήμη. Stateful: διατηρεί ιστορικό συνομιλίας. [Μονάδα 01](../01-introduction/README.md)

**Tokens** - Βασικές μονάδες κειμένου που επεξεργάζονται τα μοντέλα. Επηρεάζει κόστη και όρια. [Μονάδα 01](../01-introduction/README.md)

**Tool Chaining** - Διαδοχική εκτέλεση εργαλείων όπου η έξοδος ενημερώνει την επόμενη κλήση. [Μονάδα 04](../04-tools/README.md)

## Συστατικά του LangChain4j

**AiServices** - Δημιουργεί type-safe interfaces για υπηρεσίες AI.

**OpenAiOfficialChatModel** - Ενοποιημένος πελάτης για μοντέλα OpenAI και Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Δημιουργεί embeddings χρησιμοποιώντας τον επίσημο πελάτη OpenAI (υποστηρίζει τόσο OpenAI όσο και Azure OpenAI).

**ChatModel** - Κεντρικό interface για γλωσσικά μοντέλα.

**ChatMemory** - Διατηρεί ιστορικό συνομιλίας.

**ContentRetriever** - Βρίσκει σχετικά κομμάτια εγγράφων για RAG.

**DocumentSplitter** - Διαιρεί έγγραφα σε κομμάτια.

**EmbeddingModel** - Μετατρέπει κείμενο σε αριθμητικά διανύσματα.

**EmbeddingStore** - Αποθηκεύει και ανακτά embeddings.

**MessageWindowChatMemory** - Διατηρεί παράθυρο ολίσθησης πρόσφατων μηνυμάτων.

**PromptTemplate** - Δημιουργεί επαναχρησιμοποιήσιμες προτροπές με `{{variable}}` placeholders.

**TextSegment** - Κομμάτι κειμένου με μεταδεδομένα. Χρησιμοποιείται στο RAG.

**ToolExecutionRequest** - Αναπαριστά αίτημα εκτέλεσης εργαλείου.

**UserMessage / AiMessage / SystemMessage** - Τύποι μηνυμάτων συνομιλίας.

## Έννοιες AI/ML

**Few-Shot Learning** - Παροχή παραδειγμάτων μέσα στις προτροπές. [Μονάδα 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Μοντέλα τεχνητής νοημοσύνης εκπαιδευμένα σε τεράστια σύνολα κειμένου.

**Reasoning Effort** - Παράμετρος GPT-5 που ελέγχει το βάθος της σκέψης. [Μονάδα 02](../02-prompt-engineering/README.md)

**Temperature** - Ελέγχει την τυχαιότητα της εξόδου. Χαμηλό=ντετερμινιστικό, υψηλό=δημιουργικό.

**Vector Database** - Εξειδικευμένη βάση δεδομένων για embeddings. [Μονάδα 03](../03-rag/README.md)

**Zero-Shot Learning** - Εκτέλεση εργασιών χωρίς παραδείγματα. [Μονάδα 02](../02-prompt-engineering/README.md)

## Μηχανική προτροπών - [Μονάδα 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Βήμα-βήμα συλλογισμός για καλύτερη ακρίβεια.

**Constrained Output** - Επιβολή συγκεκριμένης μορφής ή δομής.

**High Eagerness** - Πρότυπο GPT-5 για διεξοδικό συλλογισμό.

**Low Eagerness** - Πρότυπο GPT-5 για γρήγορες απαντήσεις.

**Multi-Turn Conversation** - Διατήρηση συμφραζομένων κατά τη διάρκεια ανταλλαγών.

**Role-Based Prompting** - Ρύθμιση προσωπείου μοντέλου μέσω system messages.

**Self-Reflection** - Το μοντέλο αξιολογεί και βελτιώνει την έξοδό του.

**Structured Analysis** - Σταθερό πλαίσιο αξιολόγησης.

**Task Execution Pattern** - Σχεδιάζω → Εκτελώ → Συνοψίζω.

## RAG (Retrieval-Augmented Generation) - [Μονάδα 03](../03-rag/README.md)

**Document Processing Pipeline** - Φόρτωση → διάσπαση σε κομμάτια → embedding → αποθήκευση.

**In-Memory Embedding Store** - Μη-επίμονη αποθήκευση για δοκιμές.

**RAG** - Συνδυάζει ανάκτηση με γενιά για να τεκμηριώνει τις απαντήσεις.

**Similarity Score** - Μέτρο (0-1) της σημασιολογικής ομοιότητας.

**Source Reference** - Μεταδεδομένα σχετικά με το ανακτηθέν περιεχόμενο.

## Πράκτορες και Εργαλεία - [Μονάδα 04](../04-tools/README.md)

**@Tool Annotation** - Σηματοδοτεί μεθόδους Java ως callable εργαλεία από AI.

**ReAct Pattern** - Συλλογισμός → Δράση → Παρατήρηση → Επανάληψη.

**Session Management** - Διαχωρισμένα συμφραζόμενα για διαφορετικούς χρήστες.

**Tool** - Συνάρτηση που μπορεί να καλέσει ένας AI πράκτορας.

**Tool Description** - Τεκμηρίωση του σκοπού και των παραμέτρων του εργαλείου.

## Πρωτόκολλο Συμφραζόμενου Μοντέλου (MCP) - [Μονάδα 05](../05-mcp/README.md)

**MCP** - Πρότυπο για σύνδεση εφαρμογών AI με εξωτερικά εργαλεία.

**MCP Client** - Εφαρμογή που συνδέεται με MCP servers.

**MCP Server** - Υπηρεσία που εκθέτει εργαλεία μέσω MCP.

**Stdio Transport** - Server ως subprocess μέσω stdin/stdout.

**Tool Discovery** - Ο client ρωτάει τον server για διαθέσιμα εργαλεία.

## Υπηρεσίες Azure - [Μονάδα 01](../01-introduction/README.md)

**Azure AI Search** - Cloud αναζήτηση με δυνατότητες vector. [Μονάδα 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Αναπτύσσει πόρους Azure.

**Azure OpenAI** - Επιχειρησιακή υπηρεσία AI της Microsoft.

**Bicep** - Γλώσσα infrastructure-as-code για Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Όνομα για το deployment μοντέλου στο Azure.

**GPT-5** - Το πιο πρόσφατο μοντέλο OpenAI με έλεγχο συλλογιστικής. [Μονάδα 02](../02-prompt-engineering/README.md)

## Δοκιμές και Ανάπτυξη - [Testing Guide](TESTING.md)

**Dev Container** - Περιβάλλον ανάπτυξης σε container. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Δωρεάν playground για AI μοντέλα. [Μονάδα 00](../00-quick-start/README.md)

**In-Memory Testing** - Δοκιμές με in-memory αποθήκευση.

**Integration Testing** - Δοκιμές με πραγματική υποδομή.

**Maven** - Εργαλείο αυτοματοποίησης build για Java.

**Mockito** - Framework για mocking σε Java.

**Spring Boot** - Πλαίσιο εφαρμογών Java. [Μονάδα 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Αποποίηση ευθύνης:
Αυτό το έγγραφο έχει μεταφραστεί χρησιμοποιώντας την υπηρεσία αυτόματης μετάφρασης με τεχνητή νοημοσύνη Co‑op Translator (https://github.com/Azure/co-op-translator). Παρά τις προσπάθειές μας για ακρίβεια, παρακαλούμε να λάβετε υπόψη ότι οι αυτοματοποιημένες μεταφράσεις ενδέχεται να περιέχουν σφάλματα ή ανακρίβειες. Το πρωτότυπο έγγραφο στην αρχική του γλώσσα πρέπει να θεωρείται η αυθεντική πηγή. Για κρίσιμες πληροφορίες, συνιστάται η χρήση επαγγελματικής μετάφρασης από επαγγελματία μεταφραστή. Δεν φέρουμε ευθύνη για τυχόν παρεξηγήσεις ή λανθασμένες ερμηνείες που προκύπτουν από τη χρήση αυτής της μετάφρασης.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->