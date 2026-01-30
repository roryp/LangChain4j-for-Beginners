# Azure infrastruktúra a LangChain4j kezdéshez

## Tartalomjegyzék

- [Előfeltételek](../../../../01-introduction/infra)
- [Architektúra](../../../../01-introduction/infra)
- [Létrehozott erőforrások](../../../../01-introduction/infra)
- [Gyors kezdés](../../../../01-introduction/infra)
- [Konfiguráció](../../../../01-introduction/infra)
- [Kezelő parancsok](../../../../01-introduction/infra)
- [Költségoptimalizálás](../../../../01-introduction/infra)
- [Monitorozás](../../../../01-introduction/infra)
- [Hibaelhárítás](../../../../01-introduction/infra)
- [Infrastruktúra frissítése](../../../../01-introduction/infra)
- [Takarítás](../../../../01-introduction/infra)
- [Fájlstruktúra](../../../../01-introduction/infra)
- [Biztonsági ajánlások](../../../../01-introduction/infra)
- [További források](../../../../01-introduction/infra)

Ez a könyvtár tartalmazza az Azure infrastruktúrát kódként (IaC) Bicep és Azure Developer CLI (azd) használatával az Azure OpenAI erőforrások telepítéséhez.

## Előfeltételek

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (2.50.0 vagy újabb verzió)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (1.5.0 vagy újabb verzió)
- Egy Azure előfizetés, amely jogosultsággal rendelkezik erőforrások létrehozására

## Architektúra

**Egyszerűsített helyi fejlesztési környezet** – Csak az Azure OpenAI telepítése, az összes alkalmazás helyileg fut.

Az infrastruktúra a következő Azure erőforrásokat telepíti:

### AI szolgáltatások
- **Azure OpenAI**: Kognitív szolgáltatások két modell telepítéssel:
  - **gpt-5**: Chat befejező modell (20K TPM kapacitás)
  - **text-embedding-3-small**: Beágyazó modell RAG-hoz (20K TPM kapacitás)

### Helyi fejlesztés
Minden Spring Boot alkalmazás helyileg fut a gépeden:
- 01-introduction (8080-as port)
- 02-prompt-engineering (8083-as port)
- 03-rag (8081-es port)
- 04-tools (8084-es port)

## Létrehozott erőforrások

| Erőforrás típusa | Erőforrás név mintázat | Cél |
|--------------|----------------------|---------|
| Erőforráscsoport | `rg-{environmentName}` | Minden erőforrást tartalmaz |
| Azure OpenAI | `aoai-{resourceToken}` | AI modell hosztolás |

> **Megjegyzés:** A `{resourceToken}` egy egyedi karakterlánc, amely az előfizetés azonosítóból, a környezet nevéből és a helyszínből generálódik

## Gyors kezdés

### 1. Azure OpenAI telepítése

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

A kérésre:
- Válaszd ki az Azure előfizetésedet
- Válassz helyszínt (ajánlott: `eastus2` vagy `swedencentral` a GPT-5 elérhetőségéhez)
- Erősítsd meg a környezet nevét (alapértelmezett: `langchain4j-dev`)

Ez létrehozza:
- Azure OpenAI erőforrást GPT-5 és text-embedding-3-small modellekkel
- Kimeneti kapcsolati adatokat

### 2. Kapcsolati adatok lekérése

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Ez megjeleníti:
- `AZURE_OPENAI_ENDPOINT`: Az Azure OpenAI végpont URL-je
- `AZURE_OPENAI_KEY`: API kulcs azonosításhoz
- `AZURE_OPENAI_DEPLOYMENT`: Chat modell neve (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Beágyazó modell neve

### 3. Alkalmazások helyi futtatása

Az `azd up` parancs automatikusan létrehoz egy `.env` fájlt a gyökérkönyvtárban az összes szükséges környezeti változóval.

**Ajánlott:** Indítsd el az összes webalkalmazást:

**Bash:**
```bash
# A gyökérkönyvtárból
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# A gyökérkönyvtárból
cd ../..
.\start-all.ps1
```

Vagy indíts egyetlen modult:

**Bash:**
```bash
# Példa: Csak a bevezető modult indítsa el
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Példa: Csak a bevezető modult indítsa el
cd ../01-introduction
.\start.ps1
```

Mindkét script automatikusan betölti a környezeti változókat a gyökér `.env` fájlból, amelyet az `azd up` hozott létre.

## Konfiguráció

### Modell telepítések testreszabása

A modell telepítések módosításához szerkeszd az `infra/main.bicep` fájlt, és változtasd meg az `openAiDeployments` paramétert:

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

Elérhető modellek és verziók: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure régiók módosítása

Más régióban való telepítéshez szerkeszd az `infra/main.bicep` fájlt:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 elérhetőség ellenőrzése: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Az infrastruktúra frissítéséhez a Bicep fájlok módosítása után:

**Bash:**
```bash
# Az ARM sablon újraépítése
az bicep build --file infra/main.bicep

# Változások előnézete
azd provision --preview

# Változások alkalmazása
azd provision
```

**PowerShell:**
```powershell
# Az ARM sablon újraépítése
az bicep build --file infra/main.bicep

# Változások előnézete
azd provision --preview

# Változások alkalmazása
azd provision
```

## Takarítás

Az összes erőforrás törléséhez:

**Bash:**
```bash
# Minden erőforrás törlése
azd down

# Minden törlése, beleértve a környezetet is
azd down --purge
```

**PowerShell:**
```powershell
# Minden erőforrás törlése
azd down

# Minden törlése, beleértve a környezetet is
azd down --purge
```

**Figyelem**: Ez véglegesen törli az összes Azure erőforrást.

## Fájlstruktúra

## Költségoptimalizálás

### Fejlesztés/Tesztelés
Fejlesztési/teszt környezetekben csökkentheted a költségeket:
- Használj Standard szintet (S0) az Azure OpenAI-hoz
- Állíts alacsonyabb kapacitást (10K TPM a 20K helyett) az `infra/core/ai/cognitiveservices.bicep` fájlban
- Töröld az erőforrásokat, ha nem használod: `azd down`

### Éles környezet
Éles környezetben:
- Növeld az OpenAI kapacitást a használat alapján (50K+ TPM)
- Engedélyezd a zóna redundanciát a nagyobb rendelkezésre állásért
- Alkalmazz megfelelő monitorozást és költségriasztásokat

### Költségbecslés
- Azure OpenAI: fizetés tokenenként (bemenet + kimenet)
- GPT-5: kb. 3-5 USD 1 millió tokenenként (ellenőrizd az aktuális árakat)
- text-embedding-3-small: kb. 0,02 USD 1 millió tokenenként

Árkalkulátor: https://azure.microsoft.com/pricing/calculator/

## Monitorozás

### Azure OpenAI metrikák megtekintése

Lépj az Azure Portalra → Az OpenAI erőforrásod → Metrikák:
- Token alapú kihasználtság
- HTTP kérés sebesség
- Válaszidő
- Aktív tokenek

## Hibaelhárítás

### Probléma: Azure OpenAI aldomain névütközés

**Hibaüzenet:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Ok:**
Az előfizetésed/környezeted alapján generált aldomain név már használatban van, valószínűleg egy korábbi telepítésből, amely nem lett teljesen törölve.

**Megoldás:**
1. **1. lehetőség - Használj másik környezet nevet:**
   
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

2. **2. lehetőség - Kézi telepítés az Azure Portalon keresztül:**
   - Lépj az Azure Portalra → Erőforrás létrehozása → Azure OpenAI
   - Válassz egy egyedi nevet az erőforrásodnak
   - Telepítsd a következő modelleket:
     - **GPT-5**
     - **text-embedding-3-small** (a RAG modulokhoz)
   - **Fontos:** Jegyezd fel a telepítés neveit – ezeknek meg kell egyezniük a `.env` konfigurációval
   - A telepítés után szerezd be a végpontot és az API kulcsot a "Kulcsok és végpont" menüpontból
   - Hozz létre egy `.env` fájlt a projekt gyökerében a következővel:
     
     **Példa `.env` fájl:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Modell telepítési név irányelvek:**
- Használj egyszerű, következetes neveket: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- A telepítési nevek pontosan egyezzenek a `.env` fájlban beállítottakkal
- Gyakori hiba: a modell létrehozása egy névvel, de a kódban más név hivatkozása

### Probléma: GPT-5 nem elérhető a kiválasztott régióban

**Megoldás:**
- Válassz olyan régiót, ahol elérhető a GPT-5 (pl. eastus, swedencentral)
- Ellenőrizd az elérhetőséget: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Probléma: Nem elegendő kvóta a telepítéshez

**Megoldás:**
1. Kérj kvótaemelést az Azure Portalon
2. Vagy használj alacsonyabb kapacitást a `main.bicep` fájlban (pl. capacity: 10)

### Probléma: "Resource not found" helyi futtatáskor

**Megoldás:**
1. Ellenőrizd a telepítést: `azd env get-values`
2. Ellenőrizd, hogy a végpont és a kulcs helyes-e
3. Győződj meg róla, hogy az erőforráscsoport létezik az Azure Portalon

### Probléma: Hitelesítés sikertelen

**Megoldás:**
- Ellenőrizd, hogy az `AZURE_OPENAI_API_KEY` helyesen van-e beállítva
- A kulcs formátuma 32 karakteres hexadecimális legyen
- Ha szükséges, szerezz új kulcsot az Azure Portalról

### Telepítés sikertelen

**Probléma**: Az `azd provision` kvóta vagy kapacitás hibával leáll

**Megoldás**: 
1. Próbálj másik régiót – Lásd a [Azure régiók módosítása](../../../../01-introduction/infra) részt a régiók konfigurálásához
2. Ellenőrizd, hogy az előfizetésed rendelkezik Azure OpenAI kvótával:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Alkalmazás nem csatlakozik

**Probléma**: A Java alkalmazás kapcsolódási hibákat jelez

**Megoldás**:
1. Ellenőrizd, hogy a környezeti változók exportálva vannak:
   
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

2. Ellenőrizd, hogy a végpont formátuma helyes (pl. `https://xxx.openai.azure.com`)
3. Győződj meg róla, hogy az API kulcs az Azure Portal elsődleges vagy másodlagos kulcsa

**Probléma**: 401 Nem jogosult az Azure OpenAI-tól

**Megoldás**:
1. Szerezz új API kulcsot az Azure Portal → Kulcsok és végpont menüpontból
2. Exportáld újra az `AZURE_OPENAI_API_KEY` környezeti változót
3. Győződj meg róla, hogy a modell telepítések befejeződtek (ellenőrizd az Azure Portalon)

### Teljesítmény problémák

**Probléma**: Lassú válaszidők

**Megoldás**:
1. Ellenőrizd az OpenAI token használatot és a korlátozásokat az Azure Portal metrikákban
2. Növeld a TPM kapacitást, ha eléri a korlátokat
3. Fontold meg magasabb érvelési szint használatát (alacsony/közepes/magas)

## Infrastruktúra frissítése

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

## Biztonsági ajánlások

1. **Soha ne kötelezz el API kulcsokat** – Használj környezeti változókat
2. **Használj .env fájlokat helyileg** – Add hozzá a `.env`-t a `.gitignore`-hoz
3. **Rendszeresen cseréld a kulcsokat** – Generálj új kulcsokat az Azure Portalon
4. **Korlátozd a hozzáférést** – Használj Azure RBAC-ot az erőforrások elérésének szabályozására
5. **Monitorozd a használatot** – Állíts be költségriasztásokat az Azure Portalon

## További források

- [Azure OpenAI szolgáltatás dokumentáció](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 modell dokumentáció](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI dokumentáció](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep dokumentáció](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI hivatalos integráció](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Támogatás

Probléma esetén:
1. Ellenőrizd a [hibaelhárítási részt](../../../../01-introduction/infra) fent
2. Vizsgáld meg az Azure OpenAI szolgáltatás állapotát az Azure Portalon
3. Nyiss hibajegyet a tárolóban

## Licenc

Részletekért lásd a gyökérkönyvtárban található [LICENSE](../../../../LICENSE) fájlt.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Jogi nyilatkozat**:
Ezt a dokumentumot az AI fordító szolgáltatás, a [Co-op Translator](https://github.com/Azure/co-op-translator) segítségével fordítottuk le. Bár a pontosságra törekszünk, kérjük, vegye figyelembe, hogy az automatikus fordítások hibákat vagy pontatlanságokat tartalmazhatnak. Az eredeti dokumentum az anyanyelvén tekintendő hiteles forrásnak. Fontos információk esetén professzionális emberi fordítást javaslunk. Nem vállalunk felelősséget a fordítás használatából eredő félreértésekért vagy félreértelmezésekért.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->