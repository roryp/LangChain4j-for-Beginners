# Azure infraštruktúra pre LangChain4j Začíname

## Obsah

- [Predpoklady](../../../../01-introduction/infra)
- [Architektúra](../../../../01-introduction/infra)
- [Vytvorené zdroje](../../../../01-introduction/infra)
- [Rýchly štart](../../../../01-introduction/infra)
- [Konfigurácia](../../../../01-introduction/infra)
- [Príkazy na správu](../../../../01-introduction/infra)
- [Optimalizácia nákladov](../../../../01-introduction/infra)
- [Monitorovanie](../../../../01-introduction/infra)
- [Riešenie problémov](../../../../01-introduction/infra)
- [Aktualizácia infraštruktúry](../../../../01-introduction/infra)
- [Vyčistenie](../../../../01-introduction/infra)
- [Štruktúra súborov](../../../../01-introduction/infra)
- [Odporúčania pre bezpečnosť](../../../../01-introduction/infra)
- [Ďalšie zdroje](../../../../01-introduction/infra)

Tento adresár obsahuje Azure infraštruktúru ako kód (IaC) pomocou Bicep a Azure Developer CLI (azd) na nasadenie Azure OpenAI zdrojov.

## Prerekvizity

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (verzia 2.50.0 alebo novšia)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (verzia 1.5.0 alebo novšia)
- Predplatné Azure s oprávneniami na vytváranie zdrojov

## Architektúra

**Zjednodušené lokálne vývojové nastavenie** - Nasadiť iba Azure OpenAI, všetky aplikácie spustiť lokálne.

Infraštruktúra nasadzuje nasledujúce Azure zdroje:

### AI služby
- **Azure OpenAI**: Kognitívne služby s dvoma nasadeniami modelov:
  - **gpt-5**: Model pre chatové dokončenie (kapacita 20K TPM)
  - **text-embedding-3-small**: Model pre vkladanie pre RAG (kapacita 20K TPM)

### Lokálny vývoj
Všetky Spring Boot aplikácie bežia lokálne na vašom počítači:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Vytvorené zdroje

| Typ zdroja | Vzor názvu zdroja | Účel |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | Obsahuje všetky zdroje |
| Azure OpenAI | `aoai-{resourceToken}` | Hostovanie AI modelu |

> **Poznámka:** `{resourceToken}` je jedinečný reťazec generovaný z ID predplatného, názvu prostredia a lokality

## Rýchly štart

### 1. Nasadiť Azure OpenAI

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

Keď budete vyzvaní:
- Vyberte svoje Azure predplatné
- Zvoľte lokalitu (odporúčané: `eastus2` alebo `swedencentral` pre dostupnosť GPT-5)
- Potvrďte názov prostredia (predvolené: `langchain4j-dev`)

Tým sa vytvorí:
- Azure OpenAI zdroj s GPT-5 a text-embedding-3-small
- Výstupné pripojovacie údaje

### 2. Získať pripojovacie údaje

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Zobrazí sa:
- `AZURE_OPENAI_ENDPOINT`: URL vášho Azure OpenAI endpointu
- `AZURE_OPENAI_KEY`: API kľúč pre autentifikáciu
- `AZURE_OPENAI_DEPLOYMENT`: Názov chat modelu (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Názov embedding modelu

### 3. Spustiť aplikácie lokálne

Príkaz `azd up` automaticky vytvorí `.env` súbor v koreňovom adresári so všetkými potrebnými premennými prostredia.

**Odporúčané:** Spustiť všetky webové aplikácie:

**Bash:**
```bash
# Z koreňového adresára
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Z koreňového adresára
cd ../..
.\start-all.ps1
```

Alebo spustiť jeden modul:

**Bash:**
```bash
# Príklad: Spustiť iba úvodný modul
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Príklad: Spustiť iba úvodný modul
cd ../01-introduction
.\start.ps1
```

Oba skripty automaticky načítajú premenné prostredia z koreňového `.env` súboru vytvoreného príkazom `azd up`.

## Konfigurácia

### Prispôsobenie nasadení modelov

Ak chcete zmeniť nasadenia modelov, upravte `infra/main.bicep` a modifikujte parameter `openAiDeployments`:

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

Dostupné modely a verzie: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Zmena Azure regiónov

Ak chcete nasadiť v inom regióne, upravte `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Skontrolujte dostupnosť GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Ak chcete aktualizovať infraštruktúru po zmenách v Bicep súboroch:

**Bash:**
```bash
# Znovu zostaviť ARM šablónu
az bicep build --file infra/main.bicep

# Náhľad zmien
azd provision --preview

# Použiť zmeny
azd provision
```

**PowerShell:**
```powershell
# Znovu zostaviť ARM šablónu
az bicep build --file infra/main.bicep

# Náhľad zmien
azd provision --preview

# Použiť zmeny
azd provision
```

## Vyčistenie

Ak chcete vymazať všetky zdroje:

**Bash:**
```bash
# Odstrániť všetky zdroje
azd down

# Odstrániť všetko vrátane prostredia
azd down --purge
```

**PowerShell:**
```powershell
# Odstrániť všetky zdroje
azd down

# Odstrániť všetko vrátane prostredia
azd down --purge
```

**Upozornenie**: Toto trvalo vymaže všetky Azure zdroje.

## Štruktúra súborov

## Optimalizácia nákladov

### Vývoj/Testovanie
Pre vývojové/testovacie prostredia môžete znížiť náklady:
- Použite štandardnú úroveň (S0) pre Azure OpenAI
- Nastavte nižšiu kapacitu (10K TPM namiesto 20K) v `infra/core/ai/cognitiveservices.bicep`
- Vymažte zdroje, keď ich nepoužívate: `azd down`

### Produkcia
Pre produkciu:
- Zvýšte kapacitu OpenAI podľa používania (50K+ TPM)
- Povoliť redundanciu zóny pre vyššiu dostupnosť
- Implementujte správne monitorovanie a upozornenia na náklady

### Odhad nákladov
- Azure OpenAI: Platba za token (vstup + výstup)
- GPT-5: cca 3-5 USD za 1M tokenov (skontrolujte aktuálne ceny)
- text-embedding-3-small: cca 0,02 USD za 1M tokenov

Kalkulačka cien: https://azure.microsoft.com/pricing/calculator/

## Monitorovanie

### Zobraziť metriky Azure OpenAI

Prejdite do Azure portálu → Váš OpenAI zdroj → Metriky:
- Využitie na základe tokenov
- Miera HTTP požiadaviek
- Čas odozvy
- Aktívne tokeny

## Riešenie problémov

### Problém: Konflikt názvu subdomény Azure OpenAI

**Chybové hlásenie:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Príčina:**
Názov subdomény generovaný z vášho predplatného/prostredia je už použitý, pravdepodobne z predchádzajúceho nasadenia, ktoré nebolo úplne odstránené.

**Riešenie:**
1. **Možnosť 1 - Použiť iný názov prostredia:**
   
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

2. **Možnosť 2 - Manuálne nasadenie cez Azure Portal:**
   - Prejdite do Azure portálu → Vytvoriť zdroj → Azure OpenAI
   - Zvoľte jedinečný názov pre váš zdroj
   - Nasadte nasledujúce modely:
     - **GPT-5**
     - **text-embedding-3-small** (pre RAG moduly)
   - **Dôležité:** Zapamätajte si názvy nasadení - musia zodpovedať konfigurácii v `.env`
   - Po nasadení získajte endpoint a API kľúč z "Kľúče a Endpoint"
   - Vytvorte `.env` súbor v koreňovom adresári projektu s:

     **Príklad `.env` súboru:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Názvoslovie nasadenia modelov:**
- Používajte jednoduché, konzistentné názvy: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Názvy nasadení musia presne zodpovedať tomu, čo konfigurujete v `.env`
- Bežná chyba: Vytvoriť model s jedným názvom, ale v kóde odkazovať na iný názov

### Problém: GPT-5 nie je dostupný v zvolenom regióne

**Riešenie:**
- Vyberte región s prístupom k GPT-5 (napr. eastus, swedencentral)
- Skontrolujte dostupnosť: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Problém: Nedostatok kvóty pre nasadenie

**Riešenie:**
1. Požiadajte o zvýšenie kvóty v Azure portáli
2. Alebo použite nižšiu kapacitu v `main.bicep` (napr. capacity: 10)

### Problém: "Resource not found" pri lokálnom spustení

**Riešenie:**
1. Overte nasadenie: `azd env get-values`
2. Skontrolujte, či endpoint a kľúč sú správne
3. Uistite sa, že resource group existuje v Azure portáli

### Problém: Autentifikácia zlyhala

**Riešenie:**
- Overte, či je `AZURE_OPENAI_API_KEY` správne nastavený
- Formát kľúča by mal byť 32-znakový hexadecimálny reťazec
- Ak treba, získajte nový kľúč z Azure portálu

### Nasadenie zlyháva

**Problém**: `azd provision` zlyháva s chybami kvóty alebo kapacity

**Riešenie**: 
1. Skúste iný región - pozrite sekciu [Zmena Azure regiónov](../../../../01-introduction/infra) pre nastavenie regiónov
2. Skontrolujte, či vaše predplatné má kvótu pre Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Aplikácia sa nepripája

**Problém**: Java aplikácia hlási chyby pripojenia

**Riešenie**:
1. Overte, či sú exportované premenné prostredia:
   
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

2. Skontrolujte správny formát endpointu (mal by byť `https://xxx.openai.azure.com`)
3. Overte, či API kľúč je primárny alebo sekundárny kľúč z Azure portálu

**Problém**: 401 Unauthorized z Azure OpenAI

**Riešenie**:
1. Získajte nový API kľúč z Azure portálu → Kľúče a Endpoint
2. Znovu exportujte premennú prostredia `AZURE_OPENAI_API_KEY`
3. Uistite sa, že nasadenia modelov sú dokončené (skontrolujte Azure portál)

### Výkonové problémy

**Problém**: Pomalé časy odozvy

**Riešenie**:
1. Skontrolujte využitie tokenov a obmedzenia v metrikách Azure portálu
2. Zvýšte kapacitu TPM, ak dosahujete limity
3. Zvážte použitie vyššej úrovne náročnosti na uvažovanie (nízka/stredná/vysoká)

## Aktualizácia infraštruktúry

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

## Odporúčania pre bezpečnosť

1. **Nikdy nezverejňujte API kľúče** - Používajte premenné prostredia
2. **Používajte .env súbory lokálne** - Pridajte `.env` do `.gitignore`
3. **Pravidelne rotujte kľúče** - Generujte nové kľúče v Azure portáli
4. **Obmedzte prístup** - Používajte Azure RBAC na kontrolu prístupu k zdrojom
5. **Monitorujte používanie** - Nastavte upozornenia na náklady v Azure portáli

## Ďalšie zdroje

- [Dokumentácia služby Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [Dokumentácia modelu GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Dokumentácia Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Dokumentácia Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Oficiálna integrácia LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Podpora

Pre problémy:
1. Skontrolujte [sekciu riešenia problémov](../../../../01-introduction/infra) vyššie
2. Skontrolujte stav služby Azure OpenAI v Azure portáli
3. Otvorte issue v repozitári

## Licencia

Podrobnosti nájdete v koreňovom súbore [LICENSE](../../../../LICENSE).

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Zrieknutie sa zodpovednosti**:
Tento dokument bol preložený pomocou AI prekladateľskej služby [Co-op Translator](https://github.com/Azure/co-op-translator). Aj keď sa snažíme o presnosť, prosím, majte na pamäti, že automatizované preklady môžu obsahovať chyby alebo nepresnosti. Originálny dokument v jeho pôvodnom jazyku by mal byť považovaný za autoritatívny zdroj. Pre kritické informácie sa odporúča profesionálny ľudský preklad. Nie sme zodpovední za akékoľvek nedorozumenia alebo nesprávne interpretácie vyplývajúce z použitia tohto prekladu.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->