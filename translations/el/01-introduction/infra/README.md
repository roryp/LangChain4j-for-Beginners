<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:00:57+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "el"
}
-->
# Υποδομή Azure για το LangChain4j Ξεκινώντας

## Πίνακας Περιεχομένων

- [Προαπαιτούμενα](../../../../01-introduction/infra)
- [Αρχιτεκτονική](../../../../01-introduction/infra)
- [Δημιουργημένοι Πόροι](../../../../01-introduction/infra)
- [Γρήγορη Εκκίνηση](../../../../01-introduction/infra)
- [Διαμόρφωση](../../../../01-introduction/infra)
- [Εντολές Διαχείρισης](../../../../01-introduction/infra)
- [Βελτιστοποίηση Κόστους](../../../../01-introduction/infra)
- [Παρακολούθηση](../../../../01-introduction/infra)
- [Αντιμετώπιση Προβλημάτων](../../../../01-introduction/infra)
- [Ενημέρωση Υποδομής](../../../../01-introduction/infra)
- [Καθαρισμός](../../../../01-introduction/infra)
- [Δομή Αρχείων](../../../../01-introduction/infra)
- [Συστάσεις Ασφαλείας](../../../../01-introduction/infra)
- [Επιπλέον Πόροι](../../../../01-introduction/infra)

Αυτός ο φάκελος περιέχει την υποδομή Azure ως κώδικα (IaC) χρησιμοποιώντας Bicep και Azure Developer CLI (azd) για την ανάπτυξη πόρων Azure OpenAI.

## Προαπαιτούμενα

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (έκδοση 2.50.0 ή νεότερη)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (έκδοση 1.5.0 ή νεότερη)
- Συνδρομή Azure με δικαιώματα δημιουργίας πόρων

## Αρχιτεκτονική

**Απλοποιημένη Τοπική Ρύθμιση Ανάπτυξης** - Αναπτύξτε μόνο Azure OpenAI, εκτελέστε όλες τις εφαρμογές τοπικά.

Η υποδομή αναπτύσσει τους ακόλουθους πόρους Azure:

### Υπηρεσίες Τεχνητής Νοημοσύνης
- **Azure OpenAI**: Γνωστικές Υπηρεσίες με δύο αναπτύξεις μοντέλων:
  - **gpt-5**: Μοντέλο συνομιλίας (20K TPM χωρητικότητα)
  - **text-embedding-3-small**: Μοντέλο ενσωμάτωσης για RAG (20K TPM χωρητικότητα)

### Τοπική Ανάπτυξη
Όλες οι εφαρμογές Spring Boot εκτελούνται τοπικά στον υπολογιστή σας:
- 01-introduction (θύρα 8080)
- 02-prompt-engineering (θύρα 8083)
- 03-rag (θύρα 8081)
- 04-tools (θύρα 8084)

## Δημιουργημένοι Πόροι

| Τύπος Πόρου | Πρότυπο Ονόματος Πόρου | Σκοπός |
|--------------|----------------------|---------|
| Ομάδα Πόρων | `rg-{environmentName}` | Περιέχει όλους τους πόρους |
| Azure OpenAI | `aoai-{resourceToken}` | Φιλοξενία μοντέλου AI |

> **Σημείωση:** Το `{resourceToken}` είναι μια μοναδική συμβολοσειρά που δημιουργείται από το ID συνδρομής, το όνομα περιβάλλοντος και την τοποθεσία

## Γρήγορη Εκκίνηση

### 1. Αναπτύξτε Azure OpenAI

**Bash:**
```bash
cd 01-introduction
azd up
```

**PowerShell:**
```powershell
cd 01-introduction
azd up
```

Όταν σας ζητηθεί:
- Επιλέξτε τη συνδρομή Azure σας
- Επιλέξτε μια τοποθεσία (συνιστάται: `eastus2` ή `swedencentral` για διαθεσιμότητα GPT-5)
- Επιβεβαιώστε το όνομα περιβάλλοντος (προεπιλογή: `langchain4j-dev`)

Αυτό θα δημιουργήσει:
- Πόρο Azure OpenAI με GPT-5 και text-embedding-3-small
- Εξαγωγή λεπτομερειών σύνδεσης

### 2. Λάβετε Λεπτομέρειες Σύνδεσης

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Αυτό εμφανίζει:
- `AZURE_OPENAI_ENDPOINT`: Το URL του τελικού σημείου Azure OpenAI σας
- `AZURE_OPENAI_KEY`: Κλειδί API για αυθεντικοποίηση
- `AZURE_OPENAI_DEPLOYMENT`: Όνομα μοντέλου συνομιλίας (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Όνομα μοντέλου ενσωμάτωσης

### 3. Εκτελέστε Εφαρμογές Τοπικά

Η εντολή `azd up` δημιουργεί αυτόματα ένα αρχείο `.env` στον ριζικό φάκελο με όλες τις απαραίτητες μεταβλητές περιβάλλοντος.

**Συνιστάται:** Ξεκινήστε όλες τις web εφαρμογές:

**Bash:**
```bash
# Από τον ριζικό κατάλογο
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Από τον ριζικό κατάλογο
cd ../..
.\start-all.ps1
```

Ή ξεκινήστε ένα μόνο module:

**Bash:**
```bash
# Παράδειγμα: Ξεκινήστε μόνο το εισαγωγικό μοντέλο
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Παράδειγμα: Ξεκινήστε μόνο το εισαγωγικό μοντέλο
cd ../01-introduction
.\start.ps1
```

Και τα δύο σενάρια φορτώνουν αυτόματα τις μεταβλητές περιβάλλοντος από το αρχείο `.env` που δημιουργήθηκε από το `azd up`.

## Διαμόρφωση

### Προσαρμογή Αναπτύξεων Μοντέλων

Για να αλλάξετε τις αναπτύξεις μοντέλων, επεξεργαστείτε το `infra/main.bicep` και τροποποιήστε την παράμετρο `openAiDeployments`:

```bicep
param openAiDeployments array = [
  {
    name: 'gpt-5'  // Model deployment name
    model: {
      format: 'OpenAI'
      name: 'gpt-5'
      version: '2025-08-07'  // Model version
    }
    sku: {
      name: 'Standard'
      capacity: 20  // TPM in thousands
    }
  }
  // Add more deployments...
]
```

Διαθέσιμα μοντέλα και εκδόσεις: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Αλλαγή Περιοχών Azure

Για ανάπτυξη σε διαφορετική περιοχή, επεξεργαστείτε το `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Ελέγξτε τη διαθεσιμότητα GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Για να ενημερώσετε την υποδομή μετά από αλλαγές στα αρχεία Bicep:

**Bash:**
```bash
# Ανακατασκευή του προτύπου ARM
az bicep build --file infra/main.bicep

# Προεπισκόπηση αλλαγών
azd provision --preview

# Εφαρμογή αλλαγών
azd provision
```

**PowerShell:**
```powershell
# Ανακατασκευή του προτύπου ARM
az bicep build --file infra/main.bicep

# Προεπισκόπηση αλλαγών
azd provision --preview

# Εφαρμογή αλλαγών
azd provision
```

## Καθαρισμός

Για να διαγράψετε όλους τους πόρους:

**Bash:**
```bash
# Διαγραφή όλων των πόρων
azd down

# Διαγραφή όλων συμπεριλαμβανομένου του περιβάλλοντος
azd down --purge
```

**PowerShell:**
```powershell
# Διαγραφή όλων των πόρων
azd down

# Διαγραφή όλων συμπεριλαμβανομένου του περιβάλλοντος
azd down --purge
```

**Προειδοποίηση**: Αυτό θα διαγράψει οριστικά όλους τους πόρους Azure.

## Δομή Αρχείων

## Βελτιστοποίηση Κόστους

### Ανάπτυξη/Δοκιμές
Για περιβάλλοντα ανάπτυξης/δοκιμών, μπορείτε να μειώσετε το κόστος:
- Χρησιμοποιήστε το επίπεδο Standard (S0) για Azure OpenAI
- Ορίστε χαμηλότερη χωρητικότητα (10K TPM αντί για 20K) στο `infra/core/ai/cognitiveservices.bicep`
- Διαγράψτε πόρους όταν δεν χρησιμοποιούνται: `azd down`

### Παραγωγή
Για παραγωγή:
- Αυξήστε τη χωρητικότητα OpenAI ανάλογα με τη χρήση (50K+ TPM)
- Ενεργοποιήστε την πλεονασμό ζώνης για υψηλότερη διαθεσιμότητα
- Εφαρμόστε σωστή παρακολούθηση και ειδοποιήσεις κόστους

### Εκτίμηση Κόστους
- Azure OpenAI: Πληρωμή ανά token (είσοδος + έξοδος)
- GPT-5: περίπου $3-5 ανά 1M tokens (ελέγξτε την τρέχουσα τιμολόγηση)
- text-embedding-3-small: περίπου $0.02 ανά 1M tokens

Υπολογιστής τιμής: https://azure.microsoft.com/pricing/calculator/

## Παρακολούθηση

### Προβολή Μετρικών Azure OpenAI

Μεταβείτε στο Azure Portal → Τον πόρο OpenAI σας → Μετρικές:
- Χρήση βάσει token
- Ρυθμός αιτήσεων HTTP
- Χρόνος απόκρισης
- Ενεργά tokens

## Αντιμετώπιση Προβλημάτων

### Πρόβλημα: Σύγκρουση ονόματος υποτομέα Azure OpenAI

**Μήνυμα Σφάλματος:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Αιτία:**
Το όνομα υποτομέα που δημιουργήθηκε από τη συνδρομή/περιβάλλον σας χρησιμοποιείται ήδη, πιθανώς από προηγούμενη ανάπτυξη που δεν καθαρίστηκε πλήρως.

**Λύση:**
1. **Επιλογή 1 - Χρησιμοποιήστε διαφορετικό όνομα περιβάλλοντος:**
   
   **Bash:**
   ```bash
   azd env new my-unique-env-name
   azd up
   ```
   
   **PowerShell:**
   ```powershell
   azd env new my-unique-env-name
   azd up
   ```

2. **Επιλογή 2 - Χειροκίνητη ανάπτυξη μέσω Azure Portal:**
   - Μεταβείτε στο Azure Portal → Δημιουργία πόρου → Azure OpenAI
   - Επιλέξτε μοναδικό όνομα για τον πόρο σας
   - Αναπτύξτε τα ακόλουθα μοντέλα:
     - **GPT-5**
     - **text-embedding-3-small** (για τα modules RAG)
   - **Σημαντικό:** Σημειώστε τα ονόματα ανάπτυξης - πρέπει να ταιριάζουν με τη διαμόρφωση `.env`
   - Μετά την ανάπτυξη, λάβετε το τελικό σημείο και το κλειδί API από "Keys and Endpoint"
   - Δημιουργήστε ένα αρχείο `.env` στη ρίζα του έργου με:
     
     **Παράδειγμα αρχείου `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Οδηγίες Ονοματοδοσίας Ανάπτυξης Μοντέλων:**
- Χρησιμοποιήστε απλά, συνεπή ονόματα: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Τα ονόματα ανάπτυξης πρέπει να ταιριάζουν ακριβώς με αυτά που ρυθμίζετε στο `.env`
- Συνηθισμένο λάθος: Δημιουργία μοντέλου με ένα όνομα αλλά αναφορά διαφορετικού ονόματος στον κώδικα

### Πρόβλημα: Το GPT-5 δεν είναι διαθέσιμο στην επιλεγμένη περιοχή

**Λύση:**
- Επιλέξτε περιοχή με πρόσβαση σε GPT-5 (π.χ., eastus, swedencentral)
- Ελέγξτε τη διαθεσιμότητα: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Πρόβλημα: Ανεπαρκές όριο για ανάπτυξη

**Λύση:**
1. Ζητήστε αύξηση ορίου στο Azure Portal
2. Ή χρησιμοποιήστε χαμηλότερη χωρητικότητα στο `main.bicep` (π.χ., capacity: 10)

### Πρόβλημα: "Resource not found" κατά τοπική εκτέλεση

**Λύση:**
1. Επαληθεύστε την ανάπτυξη: `azd env get-values`
2. Ελέγξτε αν το τελικό σημείο και το κλειδί είναι σωστά
3. Βεβαιωθείτε ότι η ομάδα πόρων υπάρχει στο Azure Portal

### Πρόβλημα: Αποτυχία αυθεντικοποίησης

**Λύση:**
- Επαληθεύστε ότι το `AZURE_OPENAI_API_KEY` έχει οριστεί σωστά
- Το κλειδί πρέπει να είναι 32-ψήφια δεκαεξαδική συμβολοσειρά
- Λάβετε νέο κλειδί από το Azure Portal αν χρειάζεται

### Αποτυχία Ανάπτυξης

**Πρόβλημα**: Η εντολή `azd provision` αποτυγχάνει με σφάλματα ορίου ή χωρητικότητας

**Λύση**: 
1. Δοκιμάστε διαφορετική περιοχή - Δείτε την ενότητα [Αλλαγή Περιοχών Azure](../../../../01-introduction/infra) για οδηγίες
2. Ελέγξτε αν η συνδρομή σας έχει όριο Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Η Εφαρμογή Δεν Συνδέεται

**Πρόβλημα**: Η εφαρμογή Java εμφανίζει σφάλματα σύνδεσης

**Λύση**:
1. Επαληθεύστε ότι οι μεταβλητές περιβάλλοντος έχουν εξαχθεί:
   
   **Bash:**
   ```bash
   echo $AZURE_OPENAI_ENDPOINT
   echo $AZURE_OPENAI_API_KEY
   ```
   
   **PowerShell:**
   ```powershell
   Write-Host $env:AZURE_OPENAI_ENDPOINT
   Write-Host $env:AZURE_OPENAI_API_KEY
   ```

2. Ελέγξτε ότι η μορφή του τελικού σημείου είναι σωστή (πρέπει να είναι `https://xxx.openai.azure.com`)
3. Επαληθεύστε ότι το κλειδί API είναι το πρωτεύον ή δευτερεύον κλειδί από το Azure Portal

**Πρόβλημα**: 401 Unauthorized από Azure OpenAI

**Λύση**:
1. Λάβετε νέο κλειδί API από Azure Portal → Keys and Endpoint
2. Εξάγετε ξανά τη μεταβλητή περιβάλλοντος `AZURE_OPENAI_API_KEY`
3. Βεβαιωθείτε ότι οι αναπτύξεις μοντέλων έχουν ολοκληρωθεί (ελέγξτε το Azure Portal)

### Προβλήματα Απόδοσης

**Πρόβλημα**: Αργοί χρόνοι απόκρισης

**Λύση**:
1. Ελέγξτε τη χρήση token και το throttling στις μετρικές του Azure Portal
2. Αυξήστε τη χωρητικότητα TPM αν φτάνετε τα όρια
3. Σκεφτείτε να χρησιμοποιήσετε υψηλότερο επίπεδο προσπάθειας συλλογισμού (χαμηλό/μεσαίο/υψηλό)

## Ενημέρωση Υποδομής

```
infra/
├── main.bicep                       # Main infrastructure definition
├── main.json                        # Compiled ARM template (auto-generated)
├── main.bicepparam                  # Parameter file
├── README.md                        # This file
└── core/
    └── ai/
        └── cognitiveservices.bicep  # Azure OpenAI module
```

## Συστάσεις Ασφαλείας

1. **Ποτέ μην δεσμεύετε κλειδιά API** - Χρησιμοποιήστε μεταβλητές περιβάλλοντος
2. **Χρησιμοποιήστε αρχεία .env τοπικά** - Προσθέστε το `.env` στο `.gitignore`
3. **Ανανεώνετε τα κλειδιά τακτικά** - Δημιουργήστε νέα κλειδιά στο Azure Portal
4. **Περιορίστε την πρόσβαση** - Χρησιμοποιήστε Azure RBAC για έλεγχο πρόσβασης
5. **Παρακολουθείτε τη χρήση** - Ρυθμίστε ειδοποιήσεις κόστους στο Azure Portal

## Επιπλέον Πόροι

- [Τεκμηρίωση Υπηρεσίας Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [Τεκμηρίωση Μοντέλου GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Τεκμηρίωση Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Τεκμηρίωση Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Επίσημη Ενσωμάτωση LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Υποστήριξη

Για προβλήματα:
1. Ελέγξτε την [ενότητα αντιμετώπισης προβλημάτων](../../../../01-introduction/infra) παραπάνω
2. Εξετάστε την κατάσταση υπηρεσίας Azure OpenAI στο Azure Portal
3. Ανοίξτε ένα θέμα στο αποθετήριο

## Άδεια

Δείτε το αρχείο [LICENSE](../../../../LICENSE) στη ρίζα για λεπτομέρειες.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Αποποίηση ευθυνών**:  
Αυτό το έγγραφο έχει μεταφραστεί χρησιμοποιώντας την υπηρεσία αυτόματης μετάφρασης AI [Co-op Translator](https://github.com/Azure/co-op-translator). Παρόλο που επιδιώκουμε την ακρίβεια, παρακαλούμε να λάβετε υπόψη ότι οι αυτόματες μεταφράσεις ενδέχεται να περιέχουν λάθη ή ανακρίβειες. Το πρωτότυπο έγγραφο στη μητρική του γλώσσα πρέπει να θεωρείται η αυθεντική πηγή. Για κρίσιμες πληροφορίες, συνιστάται επαγγελματική ανθρώπινη μετάφραση. Δεν φέρουμε ευθύνη για τυχόν παρεξηγήσεις ή λανθασμένες ερμηνείες που προκύπτουν από τη χρήση αυτής της μετάφρασης.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->