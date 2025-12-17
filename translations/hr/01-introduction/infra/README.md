<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:24:04+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "hr"
}
-->
# Azure infrastruktura za LangChain4j Početak rada

## Sadržaj

- [Preduvjeti](../../../../01-introduction/infra)
- [Arhitektura](../../../../01-introduction/infra)
- [Kreirani resursi](../../../../01-introduction/infra)
- [Brzi početak](../../../../01-introduction/infra)
- [Konfiguracija](../../../../01-introduction/infra)
- [Upravljačke naredbe](../../../../01-introduction/infra)
- [Optimizacija troškova](../../../../01-introduction/infra)
- [Nadzor](../../../../01-introduction/infra)
- [Rješavanje problema](../../../../01-introduction/infra)
- [Ažuriranje infrastrukture](../../../../01-introduction/infra)
- [Čišćenje](../../../../01-introduction/infra)
- [Struktura datoteka](../../../../01-introduction/infra)
- [Preporuke za sigurnost](../../../../01-introduction/infra)
- [Dodatni resursi](../../../../01-introduction/infra)

Ovaj direktorij sadrži Azure infrastrukturu kao kod (IaC) koristeći Bicep i Azure Developer CLI (azd) za implementaciju Azure OpenAI resursa.

## Preduvjeti

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (verzija 2.50.0 ili novija)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (verzija 1.5.0 ili novija)
- Pretplata na Azure s dopuštenjima za kreiranje resursa

## Arhitektura

**Pojednostavljeni lokalni razvojni setup** - Implementirajte samo Azure OpenAI, pokrenite sve aplikacije lokalno.

Infrastruktura implementira sljedeće Azure resurse:

### AI usluge
- **Azure OpenAI**: Kognitivne usluge s dvije implementacije modela:
  - **gpt-5**: Model za chat dovršavanje (kapacitet 20K TPM)
  - **text-embedding-3-small**: Model za ugradnju za RAG (kapacitet 20K TPM)

### Lokalni razvoj
Sve Spring Boot aplikacije se pokreću lokalno na vašem računalu:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Kreirani resursi

| Tip resursa | Uzorak imena resursa | Svrha |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | Sadrži sve resurse |
| Azure OpenAI | `aoai-{resourceToken}` | Hosting AI modela |

> **Napomena:** `{resourceToken}` je jedinstveni niz generiran iz ID pretplate, imena okruženja i lokacije

## Brzi početak

### 1. Implementirajte Azure OpenAI

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

Kad se zatraži:
- Odaberite svoju Azure pretplatu
- Odaberite lokaciju (preporučeno: `eastus2` ili `swedencentral` za dostupnost GPT-5)
- Potvrdite ime okruženja (zadano: `langchain4j-dev`)

Ovo će kreirati:
- Azure OpenAI resurs s GPT-5 i text-embedding-3-small
- Izlazne detalje veze

### 2. Dohvatite detalje veze

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Ovo prikazuje:
- `AZURE_OPENAI_ENDPOINT`: URL vaše Azure OpenAI krajnje točke
- `AZURE_OPENAI_KEY`: API ključ za autentifikaciju
- `AZURE_OPENAI_DEPLOYMENT`: Ime chat modela (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Ime modela za ugradnju

### 3. Pokrenite aplikacije lokalno

Naredba `azd up` automatski kreira `.env` datoteku u korijenskom direktoriju sa svim potrebnim varijablama okruženja.

**Preporučeno:** Pokrenite sve web aplikacije:

**Bash:**
```bash
# Iz korijenskog direktorija
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Iz korijenskog direktorija
cd ../..
.\start-all.ps1
```

Ili pokrenite pojedinačni modul:

**Bash:**
```bash
# Primjer: Pokreni samo modul uvoda
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Primjer: Pokreni samo modul uvoda
cd ../01-introduction
.\start.ps1
```

Oba skripta automatski učitavaju varijable okruženja iz `.env` datoteke u korijenu koju je kreirao `azd up`.

## Konfiguracija

### Prilagodba implementacija modela

Za promjenu implementacija modela, uredite `infra/main.bicep` i izmijenite parametar `openAiDeployments`:

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

Dostupni modeli i verzije: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Promjena Azure regija

Za implementaciju u drugoj regiji, uredite `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Provjerite dostupnost GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Za ažuriranje infrastrukture nakon promjena u Bicep datotekama:

**Bash:**
```bash
# Ponovno izgradi ARM predložak
az bicep build --file infra/main.bicep

# Pregledaj promjene
azd provision --preview

# Primijeni promjene
azd provision
```

**PowerShell:**
```powershell
# Ponovno izgradi ARM predložak
az bicep build --file infra/main.bicep

# Pregledaj promjene
azd provision --preview

# Primijeni promjene
azd provision
```

## Čišćenje

Za brisanje svih resursa:

**Bash:**
```bash
# Izbriši sve resurse
azd down

# Izbriši sve uključujući okruženje
azd down --purge
```

**PowerShell:**
```powershell
# Izbriši sve resurse
azd down

# Izbriši sve uključujući okruženje
azd down --purge
```

**Upozorenje**: Ovo će trajno izbrisati sve Azure resurse.

## Struktura datoteka

## Optimizacija troškova

### Razvoj/Testiranje
Za razvojna/testna okruženja možete smanjiti troškove:
- Koristite Standardni sloj (S0) za Azure OpenAI
- Postavite manji kapacitet (10K TPM umjesto 20K) u `infra/core/ai/cognitiveservices.bicep`
- Izbrišite resurse kad nisu u upotrebi: `azd down`

### Produkcija
Za produkciju:
- Povećajte OpenAI kapacitet prema upotrebi (50K+ TPM)
- Omogućite zonalnu redundanciju za veću dostupnost
- Implementirajte odgovarajući nadzor i upozorenja o troškovima

### Procjena troškova
- Azure OpenAI: Plaćanje po tokenu (ulaz + izlaz)
- GPT-5: ~3-5 USD po 1M tokena (provjerite trenutne cijene)
- text-embedding-3-small: ~0,02 USD po 1M tokena

Kalkulator cijena: https://azure.microsoft.com/pricing/calculator/

## Nadzor

### Pregled Azure OpenAI metrika

Idite na Azure Portal → Vaš OpenAI resurs → Metrike:
- Korištenje po tokenima
- Stopa HTTP zahtjeva
- Vrijeme odgovora
- Aktivni tokeni

## Rješavanje problema

### Problem: Sukob imena poddomene Azure OpenAI

**Poruka o pogrešci:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Uzrok:**
Ime poddomene generirano iz vaše pretplate/okruženja već je u upotrebi, moguće od prethodne implementacije koja nije u potpunosti uklonjena.

**Rješenje:**
1. **Opcija 1 - Koristite drugo ime okruženja:**
   
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

2. **Opcija 2 - Ručna implementacija putem Azure Portala:**
   - Idite na Azure Portal → Kreirajte resurs → Azure OpenAI
   - Odaberite jedinstveno ime za vaš resurs
   - Implementirajte sljedeće modele:
     - **GPT-5**
     - **text-embedding-3-small** (za RAG module)
   - **Važno:** Zabilježite imena implementacija - moraju odgovarati konfiguraciji u `.env`
   - Nakon implementacije, dohvatite krajnju točku i API ključ iz "Keys and Endpoint"
   - Kreirajte `.env` datoteku u korijenu projekta sa:
     
     **Primjer `.env` datoteke:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Smjernice za imenovanje implementacija modela:**
- Koristite jednostavna, dosljedna imena: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Imena implementacija moraju točno odgovarati onome što konfigurirate u `.env`
- Česta pogreška: Kreiranje modela s jednim imenom, a referenciranje drugog u kodu

### Problem: GPT-5 nije dostupan u odabranoj regiji

**Rješenje:**
- Odaberite regiju s pristupom GPT-5 (npr. eastus, swedencentral)
- Provjerite dostupnost: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Problem: Nedovoljna kvota za implementaciju

**Rješenje:**
1. Zatražite povećanje kvote u Azure Portalu
2. Ili koristite manji kapacitet u `main.bicep` (npr. capacity: 10)

### Problem: "Resource not found" pri lokalnom pokretanju

**Rješenje:**
1. Provjerite implementaciju: `azd env get-values`
2. Provjerite jesu li endpoint i ključ ispravni
3. Provjerite postoji li resource group u Azure Portalu

### Problem: Neuspjela autentifikacija

**Rješenje:**
- Provjerite je li `AZURE_OPENAI_API_KEY` ispravno postavljen
- Format ključa treba biti 32-znamenkasti heksadecimalni niz
- Ako je potrebno, dohvatite novi ključ iz Azure Portala

### Neuspjela implementacija

**Problem**: `azd provision` ne uspijeva zbog kvote ili kapaciteta

**Rješenje**: 
1. Pokušajte drugu regiju - Pogledajte odjeljak [Promjena Azure regija](../../../../01-introduction/infra) za konfiguraciju regija
2. Provjerite ima li vaša pretplata kvotu za Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Aplikacija se ne povezuje

**Problem**: Java aplikacija prikazuje pogreške veze

**Rješenje**:
1. Provjerite jesu li varijable okruženja izvezene:
   
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

2. Provjerite je li format endpointa ispravan (trebao bi biti `https://xxx.openai.azure.com`)
3. Provjerite je li API ključ primarni ili sekundarni ključ iz Azure Portala

**Problem**: 401 Unauthorized iz Azure OpenAI

**Rješenje**:
1. Dohvatite novi API ključ iz Azure Portala → Keys and Endpoint
2. Ponovno izvezite varijablu okruženja `AZURE_OPENAI_API_KEY`
3. Provjerite jesu li implementacije modela dovršene (provjerite Azure Portal)

### Problemi s performansama

**Problem**: Spora vremena odziva

**Rješenje**:
1. Provjerite korištenje tokena i ograničenja u Azure Portal metrikama
2. Povećajte TPM kapacitet ako dosežete limite
3. Razmotrite korištenje višeg nivoa napora rezoniranja (low/medium/high)

## Ažuriranje infrastrukture

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

## Preporuke za sigurnost

1. **Nikada ne pohranjujte API ključeve u repozitorij** - Koristite varijable okruženja
2. **Koristite .env datoteke lokalno** - Dodajte `.env` u `.gitignore`
3. **Redovito rotirajte ključeve** - Generirajte nove ključeve u Azure Portalu
4. **Ograničite pristup** - Koristite Azure RBAC za kontrolu pristupa resursima
5. **Nadzor upotrebe** - Postavite upozorenja o troškovima u Azure Portalu

## Dodatni resursi

- [Dokumentacija Azure OpenAI usluge](https://learn.microsoft.com/azure/ai-services/openai/)
- [Dokumentacija GPT-5 modela](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Dokumentacija Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Dokumentacija Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI službena integracija](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Podrška

Za probleme:
1. Provjerite [odjeljak za rješavanje problema](../../../../01-introduction/infra) gore
2. Pregledajte stanje Azure OpenAI usluge u Azure Portalu
3. Otvorite issue u repozitoriju

## Licenca

Pogledajte glavnu [LICENSE](../../../../LICENSE) datoteku za detalje.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Odricanje od odgovornosti**:
Ovaj dokument preveden je pomoću AI usluge za prevođenje [Co-op Translator](https://github.com/Azure/co-op-translator). Iako nastojimo postići točnost, imajte na umu da automatski prijevodi mogu sadržavati pogreške ili netočnosti. Izvorni dokument na izvornom jeziku treba smatrati autoritativnim izvorom. Za kritične informacije preporučuje se profesionalni ljudski prijevod. Ne snosimo odgovornost za bilo kakva nesporazuma ili pogrešna tumačenja koja proizlaze iz korištenja ovog prijevoda.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->