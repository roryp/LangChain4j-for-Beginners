<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:25:25+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "sl"
}
-->
# Azure infrastruktura za LangChain4j Začetek

## Kazalo

- [Pogoji](../../../../01-introduction/infra)
- [Arhitektura](../../../../01-introduction/infra)
- [Ustvarjeni viri](../../../../01-introduction/infra)
- [Hiter začetek](../../../../01-introduction/infra)
- [Konfiguracija](../../../../01-introduction/infra)
- [Ukazi za upravljanje](../../../../01-introduction/infra)
- [Optimizacija stroškov](../../../../01-introduction/infra)
- [Nadzor](../../../../01-introduction/infra)
- [Odpravljanje težav](../../../../01-introduction/infra)
- [Posodabljanje infrastrukture](../../../../01-introduction/infra)
- [Čiščenje](../../../../01-introduction/infra)
- [Struktura datotek](../../../../01-introduction/infra)
- [Varnostna priporočila](../../../../01-introduction/infra)
- [Dodatni viri](../../../../01-introduction/infra)

Ta imenik vsebuje Azure infrastrukturo kot kodo (IaC) z uporabo Bicep in Azure Developer CLI (azd) za nameščanje Azure OpenAI virov.

## Pogoji

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (različica 2.50.0 ali novejša)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (različica 1.5.0 ali novejša)
- Azure naročnina s pravicami za ustvarjanje virov

## Arhitektura

**Poenostavljena lokalna razvojna nastavitev** - Namestite samo Azure OpenAI, vse aplikacije zaženite lokalno.

Infrastruktura namesti naslednje Azure vire:

### AI storitve
- **Azure OpenAI**: Kognitivne storitve z dvema nameščenima modeloma:
  - **gpt-5**: Model za klepet (kapaciteta 20K TPM)
  - **text-embedding-3-small**: Model za vdelavo za RAG (kapaciteta 20K TPM)

### Lokalni razvoj
Vse Spring Boot aplikacije tečejo lokalno na vašem računalniku:
- 01-introduction (vrata 8080)
- 02-prompt-engineering (vrata 8083)
- 03-rag (vrata 8081)
- 04-tools (vrata 8084)

## Ustvarjeni viri

| Tip vira | Vzorec imena vira | Namen |
|--------------|----------------------|---------|
| Skupina virov | `rg-{environmentName}` | Vsebuje vse vire |
| Azure OpenAI | `aoai-{resourceToken}` | Gostovanje AI modela |

> **Opomba:** `{resourceToken}` je edinstvena niz, ustvarjen iz ID naročnine, imena okolja in lokacije

## Hiter začetek

### 1. Namestite Azure OpenAI

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

Ko vas sistem vpraša:
- Izberite svojo Azure naročnino
- Izberite lokacijo (priporočeno: `eastus2` ali `swedencentral` za razpoložljivost GPT-5)
- Potrdite ime okolja (privzeto: `langchain4j-dev`)

To bo ustvarilo:
- Azure OpenAI vir z GPT-5 in text-embedding-3-small
- Izpis podrobnosti povezave

### 2. Pridobite podrobnosti povezave

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

To prikaže:
- `AZURE_OPENAI_ENDPOINT`: URL vašega Azure OpenAI končne točke
- `AZURE_OPENAI_KEY`: API ključ za avtentikacijo
- `AZURE_OPENAI_DEPLOYMENT`: Ime modela za klepet (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Ime modela za vdelavo

### 3. Zaženite aplikacije lokalno

Ukaz `azd up` samodejno ustvari datoteko `.env` v korenskem imeniku z vsemi potrebnimi okoljskimi spremenljivkami.

**Priporočeno:** Zaženite vse spletne aplikacije:

**Bash:**
```bash
# Iz korenskega imenika
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Iz korenskega imenika
cd ../..
.\start-all.ps1
```

Ali zaženite posamezen modul:

**Bash:**
```bash
# Primer: Zaženi samo modul uvoda
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Primer: Zaženi samo modul uvoda
cd ../01-introduction
.\start.ps1
```

Oba skripta samodejno naložita okoljske spremenljivke iz korenske `.env` datoteke, ki jo ustvari `azd up`.

## Konfiguracija

### Prilagajanje nameščanja modelov

Za spremembo nameščanja modelov uredite `infra/main.bicep` in spremenite parameter `openAiDeployments`:

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

Razpoložljivi modeli in različice: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Sprememba Azure regij

Za nameščanje v drugi regiji uredite `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Preverite razpoložljivost GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Za posodobitev infrastrukture po spremembah Bicep datotek:

**Bash:**
```bash
# Znova zgradi ARM predlogo
az bicep build --file infra/main.bicep

# Predogled sprememb
azd provision --preview

# Uveljavi spremembe
azd provision
```

**PowerShell:**
```powershell
# Znova zgradi ARM predlogo
az bicep build --file infra/main.bicep

# Predogled sprememb
azd provision --preview

# Uveljavi spremembe
azd provision
```

## Čiščenje

Za brisanje vseh virov:

**Bash:**
```bash
# Izbriši vse vire
azd down

# Izbriši vse, vključno z okoljem
azd down --purge
```

**PowerShell:**
```powershell
# Izbriši vse vire
azd down

# Izbriši vse, vključno z okoljem
azd down --purge
```

**Opozorilo**: To bo trajno izbrisalo vse Azure vire.

## Struktura datotek

## Optimizacija stroškov

### Razvoj/Testiranje
Za razvojna/testna okolja lahko znižate stroške:
- Uporabite Standardni nivo (S0) za Azure OpenAI
- Nastavite nižjo kapaciteto (10K TPM namesto 20K) v `infra/core/ai/cognitiveservices.bicep`
- Izbrišite vire, ko niso v uporabi: `azd down`

### Produkcija
Za produkcijo:
- Povečajte kapaciteto OpenAI glede na uporabo (50K+ TPM)
- Omogočite redundanco con za večjo razpoložljivost
- Uvedite ustrezen nadzor in opozorila o stroških

### Ocena stroškov
- Azure OpenAI: Plačilo na token (vhodni + izhodni)
- GPT-5: približno 3-5 $ na 1M tokenov (preverite trenutno ceno)
- text-embedding-3-small: približno 0,02 $ na 1M tokenov

Kalkulator cen: https://azure.microsoft.com/pricing/calculator/

## Nadzor

### Ogled meritev Azure OpenAI

Pojdite v Azure Portal → Vaš OpenAI vir → Meritev:
- Uporaba na osnovi tokenov
- Hitrost HTTP zahtev
- Čas do odziva
- Aktivni tokeni

## Odpravljanje težav

### Težava: Konflikt imena poddomene Azure OpenAI

**Sporočilo o napaki:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Vzrok:**
Ime poddomene, ustvarjeno iz vaše naročnine/okolja, je že v uporabi, morda zaradi prejšnje namestitve, ki ni bila popolnoma odstranjena.

**Rešitev:**
1. **Možnost 1 - Uporabite drugo ime okolja:**
   
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

2. **Možnost 2 - Ročna namestitev preko Azure Portala:**
   - Pojdite v Azure Portal → Ustvari vir → Azure OpenAI
   - Izberite edinstveno ime za vaš vir
   - Namestite naslednje modele:
     - **GPT-5**
     - **text-embedding-3-small** (za RAG module)
   - **Pomembno:** Zabeležite imena nameščanj - morajo se ujemati s konfiguracijo `.env`
   - Po namestitvi pridobite končno točko in API ključ iz "Keys and Endpoint"
   - Ustvarite `.env` datoteko v korenu projekta z:
     
     **Primer `.env` datoteke:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Smernice za poimenovanje nameščanja modelov:**
- Uporabljajte preprosta, dosledna imena: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Imena nameščanj se morajo natančno ujemati s tistim, kar konfigurirate v `.env`
- Pogosta napaka: ustvarjanje modela z enim imenom, a sklicevanje na drugo ime v kodi

### Težava: GPT-5 ni na voljo v izbrani regiji

**Rešitev:**
- Izberite regijo z dostopom do GPT-5 (npr. eastus, swedencentral)
- Preverite razpoložljivost: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Težava: Nezadostna kvota za nameščanje

**Rešitev:**
1. Zahtevajte povečanje kvote v Azure Portalu
2. Ali uporabite nižjo kapaciteto v `main.bicep` (npr. kapaciteta: 10)

### Težava: "Resource not found" pri lokalnem zagonu

**Rešitev:**
1. Preverite nameščanje: `azd env get-values`
2. Preverite, da sta končna točka in ključ pravilna
3. Preverite, da skupina virov obstaja v Azure Portalu

### Težava: Avtentikacija ni uspela

**Rešitev:**
- Preverite, da je `AZURE_OPENAI_API_KEY` pravilno nastavljen
- Oblika ključa mora biti 32-mestna heksadecimalna nizka vrednost
- Po potrebi pridobite nov ključ iz Azure Portala

### Neuspeh nameščanja

**Težava**: `azd provision` ne uspe zaradi napak kvote ali kapacitete

**Rešitev**: 
1. Poskusite drugo regijo - glejte razdelek [Sprememba Azure regij](../../../../01-introduction/infra) za navodila o konfiguraciji regij
2. Preverite, ali ima vaša naročnina kvoto za Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Aplikacija se ne povezuje

**Težava**: Java aplikacija prikazuje napake povezave

**Rešitev**:
1. Preverite, da so okoljske spremenljivke izvožene:
   
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

2. Preverite, da je format končne točke pravilen (mora biti `https://xxx.openai.azure.com`)
3. Preverite, da je API ključ primarni ali sekundarni ključ iz Azure Portala

**Težava**: 401 Neavtorizirano iz Azure OpenAI

**Rešitev**:
1. Pridobite nov API ključ iz Azure Portala → Keys and Endpoint
2. Ponovno izvozite okoljsko spremenljivko `AZURE_OPENAI_API_KEY`
3. Preverite, da so nameščanja modelov dokončana (preverite Azure Portal)

### Težave s zmogljivostjo

**Težava**: Počasni odzivi

**Rešitev**:
1. Preverite uporabo tokenov in omejitve v merilih Azure Portala
2. Povečajte kapaciteto TPM, če dosežete omejitve
3. Razmislite o uporabi višje ravni napora razmišljanja (nizka/srednja/visoka)

## Posodabljanje infrastrukture

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

## Varnostna priporočila

1. **Nikoli ne shranjujte API ključev v repozitorij** - Uporabljajte okoljske spremenljivke
2. **Lokalno uporabljajte .env datoteke** - Dodajte `.env` v `.gitignore`
3. **Redno menjajte ključe** - Ustvarjajte nove ključe v Azure Portalu
4. **Omejite dostop** - Uporabljajte Azure RBAC za nadzor dostopa do virov
5. **Nadzorujte uporabo** - Nastavite opozorila o stroških v Azure Portalu

## Dodatni viri

- [Dokumentacija Azure OpenAI storitve](https://learn.microsoft.com/azure/ai-services/openai/)
- [Dokumentacija GPT-5 modela](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Dokumentacija Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Dokumentacija Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Uradna integracija LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Podpora

Za težave:
1. Preverite [razdelek za odpravljanje težav](../../../../01-introduction/infra) zgoraj
2. Preglejte stanje storitve Azure OpenAI v Azure Portalu
3. Odprite težavo v repozitoriju

## Licenca

Podrobnosti glejte v korenski datoteki [LICENSE](../../../../LICENSE).

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Omejitev odgovornosti**:
Ta dokument je bil preveden z uporabo storitve za prevajanje z umetno inteligenco [Co-op Translator](https://github.com/Azure/co-op-translator). Čeprav si prizadevamo za natančnost, vas opozarjamo, da avtomatizirani prevodi lahko vsebujejo napake ali netočnosti. Izvirni dokument v njegovem izvirnem jeziku velja za avtoritativni vir. Za ključne informacije priporočamo strokovni človeški prevod. Za morebitne nesporazume ali napačne interpretacije, ki izhajajo iz uporabe tega prevoda, ne odgovarjamo.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->