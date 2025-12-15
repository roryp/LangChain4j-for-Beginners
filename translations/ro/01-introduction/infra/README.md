<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:20:25+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "ro"
}
-->
# Infrastructura Azure pentru LangChain4j Început Rapid

## Cuprins

- [Prerechizite](../../../../01-introduction/infra)
- [Arhitectură](../../../../01-introduction/infra)
- [Resurse Create](../../../../01-introduction/infra)
- [Început Rapid](../../../../01-introduction/infra)
- [Configurare](../../../../01-introduction/infra)
- [Comenzi de Administrare](../../../../01-introduction/infra)
- [Optimizarea Costurilor](../../../../01-introduction/infra)
- [Monitorizare](../../../../01-introduction/infra)
- [Depanare](../../../../01-introduction/infra)
- [Actualizarea Infrastructurii](../../../../01-introduction/infra)
- [Curățare](../../../../01-introduction/infra)
- [Structura Fișierelor](../../../../01-introduction/infra)
- [Recomandări de Securitate](../../../../01-introduction/infra)
- [Resurse Suplimentare](../../../../01-introduction/infra)

Acest director conține infrastructura Azure ca cod (IaC) folosind Bicep și Azure Developer CLI (azd) pentru implementarea resurselor Azure OpenAI.

## Prerechizite

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (versiunea 2.50.0 sau mai recentă)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (versiunea 1.5.0 sau mai recentă)
- Un abonament Azure cu permisiuni pentru crearea resurselor

## Arhitectură

**Configurare Simplificată pentru Dezvoltare Locală** - Se implementează doar Azure OpenAI, toate aplicațiile rulează local.

Infrastructura implementează următoarele resurse Azure:

### Servicii AI
- **Azure OpenAI**: Servicii Cognitive cu două implementări de modele:
  - **gpt-5**: Model de completare chat (capacitate 20K TPM)
  - **text-embedding-3-small**: Model de embedding pentru RAG (capacitate 20K TPM)

### Dezvoltare Locală
Toate aplicațiile Spring Boot rulează local pe mașina ta:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Resurse Create

| Tip Resursă | Model Nume Resursă | Scop |
|--------------|----------------------|---------|
| Grup de Resurse | `rg-{environmentName}` | Conține toate resursele |
| Azure OpenAI | `aoai-{resourceToken}` | Găzduire model AI |

> **Notă:** `{resourceToken}` este un șir unic generat din ID-ul abonamentului, numele mediului și locație

## Început Rapid

### 1. Implementați Azure OpenAI

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

La solicitare:
- Selectați abonamentul Azure
- Alegeți o locație (recomandat: `eastus2` sau `swedencentral` pentru disponibilitate GPT-5)
- Confirmați numele mediului (implicit: `langchain4j-dev`)

Aceasta va crea:
- Resursa Azure OpenAI cu GPT-5 și text-embedding-3-small
- Detalii de conexiune afișate

### 2. Obțineți Detaliile de Conexiune

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Aceasta afișează:
- `AZURE_OPENAI_ENDPOINT`: URL-ul endpoint-ului Azure OpenAI
- `AZURE_OPENAI_KEY`: Cheia API pentru autentificare
- `AZURE_OPENAI_DEPLOYMENT`: Numele modelului chat (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Numele modelului embedding

### 3. Rulați Aplicațiile Local

Comanda `azd up` creează automat un fișier `.env` în directorul rădăcină cu toate variabilele de mediu necesare.

**Recomandat:** Porniți toate aplicațiile web:

**Bash:**
```bash
# Din directorul rădăcină
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Din directorul rădăcină
cd ../..
.\start-all.ps1
```

Sau porniți un singur modul:

**Bash:**
```bash
# Exemplu: Porniți doar modulul de introducere
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Exemplu: Porniți doar modulul de introducere
cd ../01-introduction
.\start.ps1
```

Ambele scripturi încarcă automat variabilele de mediu din fișierul `.env` creat de `azd up`.

## Configurare

### Personalizarea Implementărilor de Model

Pentru a schimba implementările modelelor, editați `infra/main.bicep` și modificați parametrul `openAiDeployments`:

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

Modele și versiuni disponibile: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Schimbarea Regiunilor Azure

Pentru a implementa într-o regiune diferită, editați `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Verificați disponibilitatea GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Pentru a actualiza infrastructura după modificarea fișierelor Bicep:

**Bash:**
```bash
# Reconstruiește șablonul ARM
az bicep build --file infra/main.bicep

# Previzualizează modificările
azd provision --preview

# Aplică modificările
azd provision
```

**PowerShell:**
```powershell
# Reconstruiește șablonul ARM
az bicep build --file infra/main.bicep

# Previzualizează modificările
azd provision --preview

# Aplică modificările
azd provision
```

## Curățare

Pentru a șterge toate resursele:

**Bash:**
```bash
# Șterge toate resursele
azd down

# Șterge totul inclusiv mediul de lucru
azd down --purge
```

**PowerShell:**
```powershell
# Șterge toate resursele
azd down

# Șterge totul inclusiv mediul de lucru
azd down --purge
```

**Atenție**: Aceasta va șterge definitiv toate resursele Azure.

## Structura Fișierelor

## Optimizarea Costurilor

### Dezvoltare/Testare
Pentru medii de dev/test puteți reduce costurile:
- Folosiți nivelul Standard (S0) pentru Azure OpenAI
- Setați o capacitate mai mică (10K TPM în loc de 20K) în `infra/core/ai/cognitiveservices.bicep`
- Ștergeți resursele când nu sunt folosite: `azd down`

### Producție
Pentru producție:
- Măriți capacitatea OpenAI în funcție de utilizare (50K+ TPM)
- Activați redundanța pe zone pentru disponibilitate mai mare
- Implementați monitorizare și alerte de cost

### Estimare Costuri
- Azure OpenAI: Plată per token (input + output)
- GPT-5: ~3-5$ per 1M tokeni (verificați prețurile curente)
- text-embedding-3-small: ~0.02$ per 1M tokeni

Calculator prețuri: https://azure.microsoft.com/pricing/calculator/

## Monitorizare

### Vizualizați Metricile Azure OpenAI

Accesați Portalul Azure → Resursa dvs. OpenAI → Metrici:
- Utilizare bazată pe tokeni
- Rata cererilor HTTP
- Timpul de răspuns
- Tokeni activi

## Depanare

### Problemă: Conflict nume subdomeniu Azure OpenAI

**Mesaj de eroare:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Cauză:**
Numele subdomeniului generat din abonament/mediu este deja folosit, posibil de o implementare anterioară care nu a fost complet ștearsă.

**Soluție:**
1. **Opțiunea 1 - Folosiți un nume de mediu diferit:**
   
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

2. **Opțiunea 2 - Implementare manuală prin Portalul Azure:**
   - Accesați Portalul Azure → Creați o resursă → Azure OpenAI
   - Alegeți un nume unic pentru resursa dvs.
   - Implementați următoarele modele:
     - **GPT-5**
     - **text-embedding-3-small** (pentru modulele RAG)
   - **Important:** Notați numele implementărilor - trebuie să corespundă configurației `.env`
   - După implementare, obțineți endpoint-ul și cheia API din "Chei și Endpoint"
   - Creați un fișier `.env` în rădăcina proiectului cu:
     
     **Exemplu fișier `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Ghid pentru denumirea implementărilor de modele:**
- Folosiți nume simple și consistente: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Numele implementărilor trebuie să corespundă exact cu ce configurați în `.env`
- Greșeală comună: Crearea modelului cu un nume, dar referirea unui alt nume în cod

### Problemă: GPT-5 nu este disponibil în regiunea selectată

**Soluție:**
- Alegeți o regiune cu acces GPT-5 (ex: eastus, swedencentral)
- Verificați disponibilitatea: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Problemă: Cota insuficientă pentru implementare

**Soluție:**
1. Solicitați creșterea cotei în Portalul Azure
2. Sau folosiți o capacitate mai mică în `main.bicep` (ex: capacity: 10)

### Problemă: "Resource not found" la rularea locală

**Soluție:**
1. Verificați implementarea: `azd env get-values`
2. Verificați dacă endpoint-ul și cheia sunt corecte
3. Asigurați-vă că grupul de resurse există în Portalul Azure

### Problemă: Autentificare eșuată

**Soluție:**
- Verificați dacă `AZURE_OPENAI_API_KEY` este setat corect
- Formatul cheii trebuie să fie un șir hexazecimal de 32 de caractere
- Obțineți o cheie nouă din Portalul Azure dacă este necesar

### Implementare Eșuată

**Problemă**: `azd provision` eșuează cu erori de cotă sau capacitate

**Soluție**: 
1. Încercați o regiune diferită - Consultați secțiunea [Schimbarea Regiunilor Azure](../../../../01-introduction/infra) pentru configurare
2. Verificați dacă abonamentul are cotă Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Aplicația Nu Se Conectează

**Problemă**: Aplicația Java afișează erori de conexiune

**Soluție**:
1. Verificați dacă variabilele de mediu sunt exportate:
   
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

2. Verificați dacă formatul endpoint-ului este corect (ar trebui să fie `https://xxx.openai.azure.com`)
3. Verificați dacă cheia API este cheia primară sau secundară din Portalul Azure

**Problemă**: 401 Unauthorized de la Azure OpenAI

**Soluție**:
1. Obțineți o cheie API nouă din Portalul Azure → Chei și Endpoint
2. Re-exportați variabila de mediu `AZURE_OPENAI_API_KEY`
3. Asigurați-vă că implementările modelelor sunt complete (verificați în Portalul Azure)

### Probleme de Performanță

**Problemă**: Timpuri de răspuns lente

**Soluție**:
1. Verificați utilizarea tokenilor OpenAI și limitările în metricile Portalului Azure
2. Măriți capacitatea TPM dacă atingeți limitele
3. Luați în considerare folosirea unui nivel mai mare de efort de raționament (low/medium/high)

## Actualizarea Infrastructurii

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

## Recomandări de Securitate

1. **Nu comiteți niciodată cheile API** - Folosiți variabile de mediu
2. **Folosiți fișiere .env local** - Adăugați `.env` în `.gitignore`
3. **Rotiți cheile regulat** - Generați chei noi în Portalul Azure
4. **Limitați accesul** - Folosiți Azure RBAC pentru controlul accesului la resurse
5. **Monitorizați utilizarea** - Configurați alerte de cost în Portalul Azure

## Resurse Suplimentare

- [Documentația Serviciului Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [Documentația Modelului GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Documentația Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Documentația Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Integrarea Oficială LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Suport

Pentru probleme:
1. Verificați [secțiunea de depanare](../../../../01-introduction/infra) de mai sus
2. Revizuiți starea serviciului Azure OpenAI în Portalul Azure
3. Deschideți un issue în repository

## Licență

Consultați fișierul [LICENSE](../../../../LICENSE) din rădăcină pentru detalii.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Declinare de responsabilitate**:  
Acest document a fost tradus folosind serviciul de traducere AI [Co-op Translator](https://github.com/Azure/co-op-translator). Deși ne străduim pentru acuratețe, vă rugăm să rețineți că traducerile automate pot conține erori sau inexactități. Documentul original în limba sa nativă trebuie considerat sursa autorizată. Pentru informații critice, se recomandă traducerea profesională realizată de un specialist uman. Nu ne asumăm răspunderea pentru eventualele neînțelegeri sau interpretări greșite rezultate din utilizarea acestei traduceri.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->