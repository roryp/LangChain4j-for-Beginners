<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:07:26+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "fi"
}
-->
# Azure-infrastruktuuri LangChain4j:n Aloittaminen

## Sisällysluettelo

- [Esivaatimukset](../../../../01-introduction/infra)
- [Arkkitehtuuri](../../../../01-introduction/infra)
- [Luodut resurssit](../../../../01-introduction/infra)
- [Pika-aloitus](../../../../01-introduction/infra)
- [Konfigurointi](../../../../01-introduction/infra)
- [Hallintakomennot](../../../../01-introduction/infra)
- [Kustannusten optimointi](../../../../01-introduction/infra)
- [Valvonta](../../../../01-introduction/infra)
- [Vianmääritys](../../../../01-introduction/infra)
- [Infrastruktuurin päivittäminen](../../../../01-introduction/infra)
- [Siivous](../../../../01-introduction/infra)
- [Tiedostorakenne](../../../../01-introduction/infra)
- [Turvasuositukset](../../../../01-introduction/infra)
- [Lisäresurssit](../../../../01-introduction/infra)

Tämä hakemisto sisältää Azure-infrastruktuurin koodina (IaC) käyttäen Bicep:iä ja Azure Developer CLI:tä (azd) Azure OpenAI -resurssien käyttöönottoa varten.

## Esivaatimukset

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (versio 2.50.0 tai uudempi)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (versio 1.5.0 tai uudempi)
- Azure-tilaus, jolla on oikeudet luoda resursseja

## Arkkitehtuuri

**Yksinkertaistettu paikallinen kehitysympäristö** - Ota käyttöön vain Azure OpenAI, aja kaikki sovellukset paikallisesti.

Infrastruktuuri ottaa käyttöön seuraavat Azure-resurssit:

### AI-palvelut
- **Azure OpenAI**: Kognitiiviset palvelut kahdella mallin käyttöönotolla:
  - **gpt-5**: Chat-vastausmalli (20K TPM kapasiteetti)
  - **text-embedding-3-small**: Upotemalli RAG:lle (20K TPM kapasiteetti)

### Paikallinen kehitys
Kaikki Spring Boot -sovellukset ajetaan paikallisesti koneellasi:
- 01-introduction (portti 8080)
- 02-prompt-engineering (portti 8083)
- 03-rag (portti 8081)
- 04-tools (portti 8084)

## Luodut resurssit

| Resurssityyppi | Resurssin nimen kaava | Tarkoitus |
|--------------|----------------------|---------|
| Resurssiryhmä | `rg-{environmentName}` | Sisältää kaikki resurssit |
| Azure OpenAI | `aoai-{resourceToken}` | AI-mallin isännöinti |

> **Huom:** `{resourceToken}` on uniikki merkkijono, joka muodostetaan tilaus-ID:stä, ympäristön nimestä ja sijainnista

## Pika-aloitus

### 1. Ota Azure OpenAI käyttöön

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

Kun sinulta kysytään:
- Valitse Azure-tilauksesi
- Valitse sijainti (suositus: `eastus2` tai `swedencentral` GPT-5:n saatavuuden vuoksi)
- Vahvista ympäristön nimi (oletus: `langchain4j-dev`)

Tämä luo:
- Azure OpenAI -resurssin GPT-5:llä ja text-embedding-3-small:lla
- Tulostaa yhteystiedot

### 2. Hanki yhteystiedot

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Tämä näyttää:
- `AZURE_OPENAI_ENDPOINT`: Azure OpenAI -päätepisteesi URL
- `AZURE_OPENAI_KEY`: API-avain autentikointiin
- `AZURE_OPENAI_DEPLOYMENT`: Chat-mallin nimi (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Upotemallin nimi

### 3. Aja sovellukset paikallisesti

`azd up` -komento luo automaattisesti `.env`-tiedoston juurihakemistoon kaikilla tarvittavilla ympäristömuuttujilla.

**Suositus:** Käynnistä kaikki web-sovellukset:

**Bash:**
```bash
# Juurihakemistosta
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Juurikansiosta
cd ../..
.\start-all.ps1
```

Tai käynnistä yksittäinen moduuli:

**Bash:**
```bash
# Esimerkki: Käynnistä vain johdantomoduuli
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Esimerkki: Käynnistä vain johdantomoduuli
cd ../01-introduction
.\start.ps1
```

Molemmat skriptit lataavat automaattisesti ympäristömuuttujat juurihakemistossa olevasta `.env`-tiedostosta, jonka `azd up` loi.

## Konfigurointi

### Mallien käyttöönoton mukauttaminen

Muuta mallien käyttöönottoja muokkaamalla `infra/main.bicep`-tiedostoa ja muokkaa `openAiDeployments`-parametria:

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

Saatavilla olevat mallit ja versiot: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure-alueen vaihtaminen

Ota käyttöön eri alueella muokkaamalla `infra/main.bicep`-tiedostoa:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Tarkista GPT-5:n saatavuus: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Päivitä infrastruktuuri Bicep-tiedostojen muutosten jälkeen:

**Bash:**
```bash
# Rakenna ARM-malli uudelleen
az bicep build --file infra/main.bicep

# Esikatsele muutokset
azd provision --preview

# Käytä muutokset
azd provision
```

**PowerShell:**
```powershell
# Rakenna ARM-malli uudelleen
az bicep build --file infra/main.bicep

# Esikatsele muutokset
azd provision --preview

# Käytä muutokset
azd provision
```

## Siivous

Poista kaikki resurssit:

**Bash:**
```bash
# Poista kaikki resurssit
azd down

# Poista kaikki mukaan lukien ympäristö
azd down --purge
```

**PowerShell:**
```powershell
# Poista kaikki resurssit
azd down

# Poista kaikki mukaan lukien ympäristö
azd down --purge
```

**Varoitus**: Tämä poistaa kaikki Azure-resurssit pysyvästi.

## Tiedostorakenne

## Kustannusten optimointi

### Kehitys/Testaus
Kehitys- ja testausympäristöissä voit vähentää kustannuksia:
- Käytä Standard-tasoa (S0) Azure OpenAI:ssa
- Aseta pienempi kapasiteetti (10K TPM 20K sijaan) tiedostossa `infra/core/ai/cognitiveservices.bicep`
- Poista resurssit käytön ulkopuolella: `azd down`

### Tuotanto
Tuotantoon:
- Lisää OpenAI-kapasiteettia käytön mukaan (50K+ TPM)
- Ota käyttöön vyöhyketason redundanssi paremman saatavuuden takaamiseksi
- Toteuta asianmukainen valvonta ja kustannusvaroitukset

### Kustannusarvio
- Azure OpenAI: Maksu per token (syöte + tuloste)
- GPT-5: noin 3-5 dollaria per miljoona tokenia (tarkista ajantasainen hinnoittelu)
- text-embedding-3-small: noin 0,02 dollaria per miljoona tokenia

Hinnoittelulaskuri: https://azure.microsoft.com/pricing/calculator/

## Valvonta

### Katso Azure OpenAI -mittarit

Siirry Azure-portaaliin → OpenAI-resurssisi → Mittarit:
- Token-pohjainen käyttö
- HTTP-pyyntöjen määrä
- Vastausaika
- Aktiiviset tokenit

## Vianmääritys

### Ongelma: Azure OpenAI -aliverkkotunnuksen nimi on jo käytössä

**Virheilmoitus:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Syy:**
Aliverkkotunnuksen nimi, joka muodostetaan tilauksestasi/ympäristöstäsi, on jo käytössä, mahdollisesti aiemmasta käyttöönotosta, jota ei ole täysin poistettu.

**Ratkaisu:**
1. **Vaihtoehto 1 - Käytä eri ympäristön nimeä:**
   
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

2. **Vaihtoehto 2 - Manuaalinen käyttöönotto Azure-portaalin kautta:**
   - Mene Azure-portaaliin → Luo resurssi → Azure OpenAI
   - Valitse resurssillesi uniikki nimi
   - Ota käyttöön seuraavat mallit:
     - **GPT-5**
     - **text-embedding-3-small** (RAG-moduuleille)
   - **Tärkeää:** Muista käyttöönoton nimet - niiden on vastattava `.env`-konfiguraatiota
   - Käyttöönoton jälkeen hae päätepiste ja API-avain "Keys and Endpoint" -osiosta
   - Luo projektin juureen `.env`-tiedosto, jossa on:
     
     **Esimerkki `.env`-tiedostosta:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Mallin käyttöönoton nimeämisohjeet:**
- Käytä yksinkertaisia, johdonmukaisia nimiä: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Käyttöönoton nimien on täsmättävä täsmälleen `.env`-asetuksiin
- Yleinen virhe: Mallin luominen yhdellä nimellä, mutta viittaus eri nimeen koodissa

### Ongelma: GPT-5 ei ole saatavilla valitussa alueessa

**Ratkaisu:**
- Valitse alue, jolla on GPT-5:n käyttöoikeus (esim. eastus, swedencentral)
- Tarkista saatavuus: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Ongelma: Riittämätön käyttökiintiö käyttöönotolle

**Ratkaisu:**
1. Pyydä kiintiön korotusta Azure-portaalissa
2. Tai käytä pienempää kapasiteettia `main.bicep`-tiedostossa (esim. capacity: 10)

### Ongelma: "Resource not found" paikallisesti ajettaessa

**Ratkaisu:**
1. Tarkista käyttöönotto: `azd env get-values`
2. Varmista, että päätepiste ja avain ovat oikein
3. Varmista, että resurssiryhmä on olemassa Azure-portaalissa

### Ongelma: Autentikointi epäonnistui

**Ratkaisu:**
- Varmista, että `AZURE_OPENAI_API_KEY` on asetettu oikein
- Avainmuodon tulee olla 32-merkkinen heksadesimaalinen merkkijono
- Hanki uusi avain Azure-portaalista tarvittaessa

### Käyttöönotto epäonnistuu

**Ongelma**: `azd provision` epäonnistuu kiintiö- tai kapasiteettivirheillä

**Ratkaisu**: 
1. Kokeile eri aluetta - Katso [Azure-alueiden vaihtaminen](../../../../01-introduction/infra) -osio alueiden konfiguroinnista
2. Tarkista, että tilauksellasi on Azure OpenAI -kiintiö:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Sovellus ei yhdistä

**Ongelma**: Java-sovellus näyttää yhteysvirheitä

**Ratkaisu**:
1. Varmista, että ympäristömuuttujat on viety:
   
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

2. Tarkista, että päätepisteen muoto on oikea (pitäisi olla `https://xxx.openai.azure.com`)
3. Varmista, että API-avain on Azure-portaalin ensisijainen tai toissijainen avain

**Ongelma**: 401 Unauthorized Azure OpenAI:lta

**Ratkaisu**:
1. Hanki uusi API-avain Azure-portaalista → Keys and Endpoint
2. Vie uudelleen `AZURE_OPENAI_API_KEY` -ympäristömuuttuja
3. Varmista, että mallien käyttöönotot ovat valmiit (tarkista Azure-portaalista)

### Suorituskykyongelmat

**Ongelma**: Hitaat vastausajat

**Ratkaisu**:
1. Tarkista OpenAI-tokenien käyttö ja rajoitukset Azure-portaalin mittareista
2. Lisää TPM-kapasiteettia, jos saavut rajoihin
3. Harkitse korkeamman päättelytason käyttöä (matala/keskitaso/korkea)

## Infrastruktuurin päivittäminen

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

## Turvasuositukset

1. **Älä koskaan tallenna API-avaimia versionhallintaan** - Käytä ympäristömuuttujia
2. **Käytä .env-tiedostoja paikallisesti** - Lisää `.env` `.gitignore`-tiedostoon
3. **Kierrätä avaimia säännöllisesti** - Luo uusia avaimia Azure-portaalissa
4. **Rajoita pääsyä** - Käytä Azure RBAC:ia resurssien käyttöoikeuksien hallintaan
5. **Valvo käyttöä** - Aseta kustannusvaroitukset Azure-portaalissa

## Lisäresurssit

- [Azure OpenAI -palvelun dokumentaatio](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 mallin dokumentaatio](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI:n dokumentaatio](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep-dokumentaatio](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI virallinen integraatio](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Tuki

Ongelmatilanteissa:
1. Tarkista yllä oleva [vianmäärityksen osio](../../../../01-introduction/infra)
2. Tarkista Azure OpenAI -palvelun tila Azure-portaalissa
3. Avaa issue repositoriossa

## Lisenssi

Katso juurihakemistosta [LICENSE](../../../../LICENSE) tiedosto lisätietoja varten.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Vastuuvapauslauseke**:
Tämä asiakirja on käännetty käyttämällä tekoälypohjaista käännöspalvelua [Co-op Translator](https://github.com/Azure/co-op-translator). Vaikka pyrimme tarkkuuteen, otathan huomioon, että automaattikäännöksissä saattaa esiintyä virheitä tai epätarkkuuksia. Alkuperäinen asiakirja sen alkuperäiskielellä on virallinen lähde. Tärkeissä asioissa suositellaan ammattimaista ihmiskäännöstä. Emme ole vastuussa tämän käännöksen käytöstä aiheutuvista väärinymmärryksistä tai tulkinnoista.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->