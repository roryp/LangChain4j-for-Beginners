# Azure infrastruktura pro LangChain4j Začínáme

## Obsah

- [Požadavky](../../../../01-introduction/infra)
- [Architektura](../../../../01-introduction/infra)
- [Vytvořené zdroje](../../../../01-introduction/infra)
- [Rychlý start](../../../../01-introduction/infra)
- [Konfigurace](../../../../01-introduction/infra)
- [Příkazy pro správu](../../../../01-introduction/infra)
- [Optimalizace nákladů](../../../../01-introduction/infra)
- [Monitorování](../../../../01-introduction/infra)
- [Řešení problémů](../../../../01-introduction/infra)
- [Aktualizace infrastruktury](../../../../01-introduction/infra)
- [Vyčištění](../../../../01-introduction/infra)
- [Struktura souborů](../../../../01-introduction/infra)
- [Doporučení pro zabezpečení](../../../../01-introduction/infra)
- [Další zdroje](../../../../01-introduction/infra)

Tento adresář obsahuje Azure infrastrukturu jako kód (IaC) pomocí Bicep a Azure Developer CLI (azd) pro nasazení Azure OpenAI zdrojů.

## Požadavky

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (verze 2.50.0 nebo novější)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (verze 1.5.0 nebo novější)
- Azure předplatné s oprávněními pro vytváření zdrojů

## Architektura

**Zjednodušené lokální vývojové nastavení** – Nasadí pouze Azure OpenAI, všechny aplikace běží lokálně.

Infrastruktura nasazuje následující Azure zdroje:

### AI služby
- **Azure OpenAI**: Kognitivní služby se dvěma nasazeními modelů:
  - **gpt-5**: Model pro chatové dokončování (kapacita 20K TPM)
  - **text-embedding-3-small**: Model pro vkládání pro RAG (kapacita 20K TPM)

### Lokální vývoj
Všechny Spring Boot aplikace běží lokálně na vašem počítači:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Vytvořené zdroje

| Typ zdroje | Vzor názvu zdroje | Účel |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | Obsahuje všechny zdroje |
| Azure OpenAI | `aoai-{resourceToken}` | Hostování AI modelu |

> **Poznámka:** `{resourceToken}` je unikátní řetězec generovaný z ID předplatného, názvu prostředí a lokace

## Rychlý start

### 1. Nasazení Azure OpenAI

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

Po výzvě:
- Vyberte své Azure předplatné
- Zvolte lokaci (doporučeno: `eastus2` nebo `swedencentral` pro dostupnost GPT-5)
- Potvrďte název prostředí (výchozí: `langchain4j-dev`)

Tím se vytvoří:
- Azure OpenAI zdroj s GPT-5 a text-embedding-3-small
- Výstupní připojovací údaje

### 2. Získání připojovacích údajů

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Zobrazí se:
- `AZURE_OPENAI_ENDPOINT`: URL vašeho Azure OpenAI endpointu
- `AZURE_OPENAI_KEY`: API klíč pro autentizaci
- `AZURE_OPENAI_DEPLOYMENT`: Název chat modelu (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Název embedding modelu

### 3. Spuštění aplikací lokálně

Příkaz `azd up` automaticky vytvoří `.env` soubor v kořenovém adresáři se všemi potřebnými proměnnými prostředí.

**Doporučeno:** Spusťte všechny webové aplikace:

**Bash:**
```bash
# Ze základního adresáře
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Ze kořenového adresáře
cd ../..
.\start-all.ps1
```

Nebo spusťte jeden modul:

**Bash:**
```bash
# Příklad: Spustit pouze úvodní modul
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Příklad: Spustit pouze úvodní modul
cd ../01-introduction
.\start.ps1
```

Oba skripty automaticky načtou proměnné prostředí z kořenového `.env` souboru vytvořeného příkazem `azd up`.

## Konfigurace

### Přizpůsobení nasazení modelů

Pro změnu nasazení modelů upravte `infra/main.bicep` a modifikujte parametr `openAiDeployments`:

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

Dostupné modely a verze: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Změna Azure regionů

Pro nasazení v jiném regionu upravte `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Zkontrolujte dostupnost GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Pro aktualizaci infrastruktury po změnách v Bicep souborech:

**Bash:**
```bash
# Přestavět ARM šablonu
az bicep build --file infra/main.bicep

# Náhled změn
azd provision --preview

# Použít změny
azd provision
```

**PowerShell:**
```powershell
# Přestavět ARM šablonu
az bicep build --file infra/main.bicep

# Náhled změn
azd provision --preview

# Použít změny
azd provision
```

## Vyčištění

Pro smazání všech zdrojů:

**Bash:**
```bash
# Odstraňte všechny zdroje
azd down

# Odstraňte vše včetně prostředí
azd down --purge
```

**PowerShell:**
```powershell
# Odstraňte všechny zdroje
azd down

# Odstraňte vše včetně prostředí
azd down --purge
```

**Varování**: Toto trvale smaže všechny Azure zdroje.

## Struktura souborů

## Optimalizace nákladů

### Vývoj/Testování
Pro vývojová/testovací prostředí můžete snížit náklady:
- Použijte Standardní úroveň (S0) pro Azure OpenAI
- Nastavte nižší kapacitu (10K TPM místo 20K) v `infra/core/ai/cognitiveservices.bicep`
- Smažte zdroje, když nejsou používány: `azd down`

### Produkce
Pro produkci:
- Zvyšte kapacitu OpenAI podle využití (50K+ TPM)
- Povolit zónovou redundanci pro vyšší dostupnost
- Implementujte správné monitorování a upozornění na náklady

### Odhad nákladů
- Azure OpenAI: Platba za token (vstup + výstup)
- GPT-5: cca 3-5 USD za 1M tokenů (zkontrolujte aktuální ceny)
- text-embedding-3-small: cca 0,02 USD za 1M tokenů

Kalkulačka cen: https://azure.microsoft.com/pricing/calculator/

## Monitorování

### Zobrazení metrik Azure OpenAI

Přejděte do Azure Portálu → Váš OpenAI zdroj → Metriky:
- Využití na základě tokenů
- Míra HTTP požadavků
- Doba odezvy
- Aktivní tokeny

## Řešení problémů

### Problém: Konflikt názvu subdomény Azure OpenAI

**Chybová zpráva:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Příčina:**
Název subdomény generovaný z vašeho předplatného/prostředí je již používán, pravděpodobně z předchozího nasazení, které nebylo úplně odstraněno.

**Řešení:**
1. **Možnost 1 - Použijte jiný název prostředí:**
   
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

2. **Možnost 2 - Manuální nasazení přes Azure Portal:**
   - Přejděte do Azure Portálu → Vytvořit zdroj → Azure OpenAI
   - Zvolte unikátní název pro váš zdroj
   - Nasadte následující modely:
     - **GPT-5**
     - **text-embedding-3-small** (pro RAG moduly)
   - **Důležité:** Poznamenejte si názvy nasazení - musí odpovídat konfiguraci v `.env`
   - Po nasazení získejte endpoint a API klíč v "Klíče a endpoint"
   - Vytvořte `.env` soubor v kořenovém adresáři s:

     **Příklad `.env` souboru:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Pravidla pojmenování nasazení modelů:**
- Používejte jednoduché, konzistentní názvy: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Názvy nasazení musí přesně odpovídat tomu, co nastavíte v `.env`
- Častá chyba: Vytvoření modelu s jedním názvem, ale odkazování na jiný v kódu

### Problém: GPT-5 není dostupný ve vybraném regionu

**Řešení:**
- Vyberte region s přístupem k GPT-5 (např. eastus, swedencentral)
- Zkontrolujte dostupnost: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Problém: Nedostatečná kvóta pro nasazení

**Řešení:**
1. Požádejte o zvýšení kvóty v Azure Portálu
2. Nebo použijte nižší kapacitu v `main.bicep` (např. capacity: 10)

### Problém: "Resource not found" při lokálním spuštění

**Řešení:**
1. Ověřte nasazení: `azd env get-values`
2. Zkontrolujte správnost endpointu a klíče
3. Ujistěte se, že Resource Group existuje v Azure Portálu

### Problém: Autentizace selhala

**Řešení:**
- Ověřte, že `AZURE_OPENAI_API_KEY` je správně nastaven
- Formát klíče by měl být 32 znaků hexadecimální řetězec
- Získejte nový klíč z Azure Portálu, pokud je potřeba

### Nasazení selhává

**Problém**: `azd provision` selhává s chybami kvóty nebo kapacity

**Řešení**: 
1. Zkuste jiný region – viz sekce [Změna Azure regionů](../../../../01-introduction/infra) pro konfiguraci regionů
2. Zkontrolujte, zda máte kvótu Azure OpenAI:

   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Aplikace se nepřipojuje

**Problém**: Java aplikace hlásí chyby připojení

**Řešení**:
1. Ověřte export proměnných prostředí:
   
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

2. Zkontrolujte správný formát endpointu (mělo by být `https://xxx.openai.azure.com`)
3. Ověřte, že API klíč je primární nebo sekundární klíč z Azure Portálu

**Problém**: 401 Unauthorized z Azure OpenAI

**Řešení**:
1. Získejte nový API klíč z Azure Portálu → Klíče a endpoint
2. Znovu exportujte proměnnou prostředí `AZURE_OPENAI_API_KEY`
3. Ujistěte se, že nasazení modelů je kompletní (zkontrolujte v Azure Portálu)

### Výkonové problémy

**Problém**: Pomalé doby odezvy

**Řešení**:
1. Zkontrolujte využití tokenů a omezení v metrikách Azure Portálu
2. Zvyšte kapacitu TPM, pokud dosahujete limitů
3. Zvažte použití vyšší úrovně náročnosti na uvažování (nízká/střední/vysoká)

## Aktualizace infrastruktury

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

## Doporučení pro zabezpečení

1. **Nikdy neukládejte API klíče do repozitáře** – Používejte proměnné prostředí
2. **Používejte `.env` soubory lokálně** – Přidejte `.env` do `.gitignore`
3. **Pravidelně rotujte klíče** – Generujte nové klíče v Azure Portálu
4. **Omezte přístup** – Použijte Azure RBAC pro kontrolu přístupu ke zdrojům
5. **Monitorujte využití** – Nastavte upozornění na náklady v Azure Portálu

## Další zdroje

- [Dokumentace Azure OpenAI služby](https://learn.microsoft.com/azure/ai-services/openai/)
- [Dokumentace modelu GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Dokumentace Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Dokumentace Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Oficiální integrace LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Podpora

Pro problémy:
1. Zkontrolujte [sekci řešení problémů](../../../../01-introduction/infra) výše
2. Prohlédněte si stav služby Azure OpenAI v Azure Portálu
3. Otevřete issue v repozitáři

## Licence

Podrobnosti naleznete v kořenovém souboru [LICENSE](../../../../LICENSE).

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Prohlášení o vyloučení odpovědnosti**:  
Tento dokument byl přeložen pomocí AI překladatelské služby [Co-op Translator](https://github.com/Azure/co-op-translator). Přestože usilujeme o přesnost, mějte prosím na paměti, že automatizované překlady mohou obsahovat chyby nebo nepřesnosti. Původní dokument v jeho mateřském jazyce by měl být považován za autoritativní zdroj. Pro kritické informace se doporučuje profesionální lidský překlad. Nejsme odpovědní za jakékoliv nedorozumění nebo nesprávné výklady vyplývající z použití tohoto překladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->