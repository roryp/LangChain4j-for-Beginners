<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:07:11+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "el"
}
-->
# Λεξικό LangChain4j

## Περιεχόμενα

- [Βασικές Έννοιες](../../../docs)
- [Συστατικά LangChain4j](../../../docs)
- [Έννοιες AI/ML](../../../docs)
- [Μηχανική Προτροπής](../../../docs)
- [RAG (Ανάκτηση-Ενισχυμένη Γενιά)](../../../docs)
- [Πράκτορες και Εργαλεία](../../../docs)
- [Πρωτόκολλο Πλαισίου Μοντέλου (MCP)](../../../docs)
- [Υπηρεσίες Azure](../../../docs)
- [Δοκιμές και Ανάπτυξη](../../../docs)

Γρήγορη αναφορά για όρους και έννοιες που χρησιμοποιούνται σε όλο το μάθημα.

## Βασικές Έννοιες

**AI Agent** - Σύστημα που χρησιμοποιεί AI για να συλλογιστεί και να δράσει αυτόνομα. [Module 04](../04-tools/README.md)

**Chain** - Ακολουθία λειτουργιών όπου η έξοδος τροφοδοτεί το επόμενο βήμα.

**Chunking** - Διάσπαση εγγράφων σε μικρότερα κομμάτια. Τυπικά: 300-500 tokens με επικάλυψη. [Module 03](../03-rag/README.md)

**Context Window** - Μέγιστος αριθμός tokens που μπορεί να επεξεργαστεί ένα μοντέλο. GPT-5: 400K tokens.

**Embeddings** - Αριθμητικοί διανύσματα που αναπαριστούν το νόημα κειμένου. [Module 03](../03-rag/README.md)

**Function Calling** - Το μοντέλο δημιουργεί δομημένα αιτήματα για κλήση εξωτερικών συναρτήσεων. [Module 04](../04-tools/README.md)

**Hallucination** - Όταν τα μοντέλα παράγουν λανθασμένες αλλά πιθανές πληροφορίες.

**Prompt** - Κείμενο εισόδου σε ένα γλωσσικό μοντέλο. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Αναζήτηση με βάση το νόημα χρησιμοποιώντας embeddings, όχι λέξεις-κλειδιά. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: χωρίς μνήμη. Stateful: διατηρεί ιστορικό συνομιλίας. [Module 01](../01-introduction/README.md)

**Tokens** - Βασικές μονάδες κειμένου που επεξεργάζονται τα μοντέλα. Επηρεάζουν κόστη και όρια. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Αλληλουχία εκτέλεσης εργαλείων όπου η έξοδος ενημερώνει την επόμενη κλήση. [Module 04](../04-tools/README.md)

## Συστατικά LangChain4j

**AiServices** - Δημιουργεί τύπου ασφαλείς διεπαφές υπηρεσιών AI.

**OpenAiOfficialChatModel** - Ενοποιημένος πελάτης για μοντέλα OpenAI και Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Δημιουργεί embeddings χρησιμοποιώντας τον επίσημο πελάτη OpenAI (υποστηρίζει και OpenAI και Azure OpenAI).

**ChatModel** - Κύρια διεπαφή για γλωσσικά μοντέλα.

**ChatMemory** - Διατηρεί ιστορικό συνομιλίας.

**ContentRetriever** - Βρίσκει σχετικά κομμάτια εγγράφων για RAG.

**DocumentSplitter** - Διασπά έγγραφα σε κομμάτια.

**EmbeddingModel** - Μετατρέπει κείμενο σε αριθμητικά διανύσματα.

**EmbeddingStore** - Αποθηκεύει και ανακτά embeddings.

**MessageWindowChatMemory** - Διατηρεί παράθυρο ολισθαίνουσας μνήμης πρόσφατων μηνυμάτων.

**PromptTemplate** - Δημιουργεί επαναχρησιμοποιήσιμες προτροπές με `{{variable}}` θέσεις.

**TextSegment** - Κομμάτι κειμένου με μεταδεδομένα. Χρησιμοποιείται στο RAG.

**ToolExecutionRequest** - Αναπαριστά αίτημα εκτέλεσης εργαλείου.

**UserMessage / AiMessage / SystemMessage** - Τύποι μηνυμάτων συνομιλίας.

## Έννοιες AI/ML

**Few-Shot Learning** - Παροχή παραδειγμάτων στις προτροπές. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Μοντέλα AI εκπαιδευμένα σε τεράστια δεδομένα κειμένου.

**Reasoning Effort** - Παράμετρος GPT-5 που ελέγχει το βάθος σκέψης. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Ελέγχει την τυχαιότητα της εξόδου. Χαμηλό=ντετερμινιστικό, υψηλό=δημιουργικό.

**Vector Database** - Εξειδικευμένη βάση δεδομένων για embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Εκτέλεση εργασιών χωρίς παραδείγματα. [Module 02](../02-prompt-engineering/README.md)

## Μηχανική Προτροπής - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Βήμα-βήμα συλλογισμός για καλύτερη ακρίβεια.

**Constrained Output** - Επιβολή συγκεκριμένης μορφής ή δομής.

**High Eagerness** - Πρότυπο GPT-5 για διεξοδικό συλλογισμό.

**Low Eagerness** - Πρότυπο GPT-5 για γρήγορες απαντήσεις.

**Multi-Turn Conversation** - Διατήρηση πλαισίου σε πολλαπλές ανταλλαγές.

**Role-Based Prompting** - Ορισμός προσωπικότητας μοντέλου μέσω συστημικών μηνυμάτων.

**Self-Reflection** - Το μοντέλο αξιολογεί και βελτιώνει την έξοδό του.

**Structured Analysis** - Σταθερό πλαίσιο αξιολόγησης.

**Task Execution Pattern** - Σχεδιάζω → Εκτελώ → Περίληψη.

## RAG (Ανάκτηση-Ενισχυμένη Γενιά) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Φόρτωση → διάσπαση → ενσωμάτωση → αποθήκευση.

**In-Memory Embedding Store** - Μη μόνιμη αποθήκευση για δοκιμές.

**RAG** - Συνδυάζει ανάκτηση με γενιά για τεκμηρίωση απαντήσεων.

**Similarity Score** - Μέτρο (0-1) σημασιολογικής ομοιότητας.

**Source Reference** - Μεταδεδομένα για το ανακτηθέν περιεχόμενο.

## Πράκτορες και Εργαλεία - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Σημειώνει μεθόδους Java ως εργαλεία που καλούνται από AI.

**ReAct Pattern** - Συλλογίζομαι → Δρω → Παρατηρώ → Επαναλαμβάνω.

**Session Management** - Ξεχωριστά πλαίσια για διαφορετικούς χρήστες.

**Tool** - Συνάρτηση που μπορεί να καλέσει ένας πράκτορας AI.

**Tool Description** - Τεκμηρίωση σκοπού και παραμέτρων εργαλείου.

## Πρωτόκολλο Πλαισίου Μοντέλου (MCP) - [Module 05](../05-mcp/README.md)

**Docker Transport** - MCP server σε κοντέινερ Docker.

**MCP** - Πρότυπο για σύνδεση εφαρμογών AI με εξωτερικά εργαλεία.

**MCP Client** - Εφαρμογή που συνδέεται με MCP servers.

**MCP Server** - Υπηρεσία που εκθέτει εργαλεία μέσω MCP.

**Server-Sent Events (SSE)** - Ροή από server σε client μέσω HTTP.

**Stdio Transport** - Server ως υποδιαδικασία μέσω stdin/stdout.

**Streamable HTTP Transport** - HTTP με SSE για επικοινωνία σε πραγματικό χρόνο.

**Tool Discovery** - Ο πελάτης ρωτά τον server για διαθέσιμα εργαλεία.

## Υπηρεσίες Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Αναζήτηση στο cloud με δυνατότητες διανυσμάτων. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Αναπτύσσει πόρους Azure.

**Azure OpenAI** - Επιχειρησιακή υπηρεσία AI της Microsoft.

**Bicep** - Γλώσσα υποδομής ως κώδικα για Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Όνομα ανάπτυξης μοντέλου στο Azure.

**GPT-5** - Νεότερο μοντέλο OpenAI με έλεγχο συλλογισμού. [Module 02](../02-prompt-engineering/README.md)

## Δοκιμές και Ανάπτυξη - [Testing Guide](TESTING.md)

**Dev Container** - Περιβάλλον ανάπτυξης σε κοντέινερ. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Δωρεάν πλατφόρμα μοντέλων AI. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Δοκιμές με αποθήκευση στη μνήμη.

**Integration Testing** - Δοκιμές με πραγματική υποδομή.

**Maven** - Εργαλείο αυτοματοποίησης κατασκευής Java.

**Mockito** - Πλαίσιο mocking για Java.

**Spring Boot** - Πλαίσιο εφαρμογών Java. [Module 01](../01-introduction/README.md)

**Testcontainers** - Κοντέινερ Docker σε δοκιμές.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Αποποίηση ευθυνών**:  
Αυτό το έγγραφο έχει μεταφραστεί χρησιμοποιώντας την υπηρεσία αυτόματης μετάφρασης AI [Co-op Translator](https://github.com/Azure/co-op-translator). Παρόλο που επιδιώκουμε την ακρίβεια, παρακαλούμε να λάβετε υπόψη ότι οι αυτόματες μεταφράσεις ενδέχεται να περιέχουν λάθη ή ανακρίβειες. Το πρωτότυπο έγγραφο στη γλώσσα του θεωρείται η αυθεντική πηγή. Για κρίσιμες πληροφορίες, συνιστάται επαγγελματική ανθρώπινη μετάφραση. Δεν φέρουμε ευθύνη για τυχόν παρεξηγήσεις ή λανθασμένες ερμηνείες που προκύπτουν από τη χρήση αυτής της μετάφρασης.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->