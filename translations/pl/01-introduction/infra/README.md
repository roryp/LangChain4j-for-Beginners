<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T21:59:37+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "pl"
}
-->
# Infrastruktura Azure dla LangChain4j - Pierwsze kroki

## Spis treści

- [Wymagania wstępne](../../../../01-introduction/infra)
- [Architektura](../../../../01-introduction/infra)
- [Utworzone zasoby](../../../../01-introduction/infra)
- [Szybki start](../../../../01-introduction/infra)
- [Konfiguracja](../../../../01-introduction/infra)
- [Polecenia zarządzania](../../../../01-introduction/infra)
- [Optymalizacja kosztów](../../../../01-introduction/infra)
- [Monitorowanie](../../../../01-introduction/infra)
- [Rozwiązywanie problemów](../../../../01-introduction/infra)
- [Aktualizacja infrastruktury](../../../../01-introduction/infra)
- [Sprzątanie](../../../../01-introduction/infra)
- [Struktura plików](../../../../01-introduction/infra)
- [Zalecenia dotyczące bezpieczeństwa](../../../../01-introduction/infra)
- [Dodatkowe zasoby](../../../../01-introduction/infra)

Ten katalog zawiera infrastrukturę Azure jako kod (IaC) wykorzystującą Bicep i Azure Developer CLI (azd) do wdrażania zasobów Azure OpenAI.

## Wymagania wstępne

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (wersja 2.50.0 lub nowsza)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (wersja 1.5.0 lub nowsza)
- Subskrypcja Azure z uprawnieniami do tworzenia zasobów

## Architektura

**Uproszczone lokalne środowisko deweloperskie** - wdrażaj tylko Azure OpenAI, uruchamiaj wszystkie aplikacje lokalnie.

Infrastruktura wdraża następujące zasoby Azure:

### Usługi AI
- **Azure OpenAI**: Usługi kognitywne z dwoma wdrożeniami modeli:
  - **gpt-5**: model do uzupełniania czatu (pojemność 20K TPM)
  - **text-embedding-3-small**: model osadzania dla RAG (pojemność 20K TPM)

### Lokalny rozwój
Wszystkie aplikacje Spring Boot działają lokalnie na twoim komputerze:
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Utworzone zasoby

| Typ zasobu | Wzór nazwy zasobu | Przeznaczenie |
|--------------|----------------------|---------|
| Grupa zasobów | `rg-{environmentName}` | Zawiera wszystkie zasoby |
| Azure OpenAI | `aoai-{resourceToken}` | Hosting modelu AI |

> **Uwaga:** `{resourceToken}` to unikalny ciąg generowany na podstawie ID subskrypcji, nazwy środowiska i lokalizacji

## Szybki start

### 1. Wdróż Azure OpenAI

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

Po wyświetleniu monitu:
- Wybierz swoją subskrypcję Azure
- Wybierz lokalizację (zalecane: `eastus2` lub `swedencentral` dla dostępności GPT-5)
- Potwierdź nazwę środowiska (domyślnie: `langchain4j-dev`)

To utworzy:
- zasób Azure OpenAI z GPT-5 i text-embedding-3-small
- wyświetli szczegóły połączenia

### 2. Pobierz szczegóły połączenia

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Wyświetli to:
- `AZURE_OPENAI_ENDPOINT`: URL punktu końcowego Azure OpenAI
- `AZURE_OPENAI_KEY`: klucz API do uwierzytelniania
- `AZURE_OPENAI_DEPLOYMENT`: nazwa modelu czatu (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: nazwa modelu osadzania

### 3. Uruchom aplikacje lokalnie

Polecenie `azd up` automatycznie tworzy plik `.env` w katalogu głównym ze wszystkimi niezbędnymi zmiennymi środowiskowymi.

**Zalecane:** Uruchom wszystkie aplikacje webowe:

**Bash:**
```bash
# Z katalogu głównego
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Z katalogu głównego
cd ../..
.\start-all.ps1
```

Lub uruchom pojedynczy moduł:

**Bash:**
```bash
# Przykład: Uruchom tylko moduł wprowadzenia
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Przykład: Uruchom tylko moduł wprowadzający
cd ../01-introduction
.\start.ps1
```

Oba skrypty automatycznie ładują zmienne środowiskowe z pliku `.env` w katalogu głównym utworzonego przez `azd up`.

## Konfiguracja

### Dostosowywanie wdrożeń modeli

Aby zmienić wdrożenia modeli, edytuj `infra/main.bicep` i zmodyfikuj parametr `openAiDeployments`:

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

Dostępne modele i wersje: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Zmiana regionów Azure

Aby wdrożyć w innym regionie, edytuj `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Sprawdź dostępność GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Aby zaktualizować infrastrukturę po zmianach w plikach Bicep:

**Bash:**
```bash
# Odbuduj szablon ARM
az bicep build --file infra/main.bicep

# Podgląd zmian
azd provision --preview

# Zastosuj zmiany
azd provision
```

**PowerShell:**
```powershell
# Odbuduj szablon ARM
az bicep build --file infra/main.bicep

# Podgląd zmian
azd provision --preview

# Zastosuj zmiany
azd provision
```

## Sprzątanie

Aby usunąć wszystkie zasoby:

**Bash:**
```bash
# Usuń wszystkie zasoby
azd down

# Usuń wszystko, łącznie ze środowiskiem
azd down --purge
```

**PowerShell:**
```powershell
# Usuń wszystkie zasoby
azd down

# Usuń wszystko, łącznie ze środowiskiem
azd down --purge
```

**Ostrzeżenie**: To trwale usunie wszystkie zasoby Azure.

## Struktura plików

## Optymalizacja kosztów

### Środowisko deweloperskie/testowe
Dla środowisk dev/test możesz obniżyć koszty:
- Użyj warstwy Standard (S0) dla Azure OpenAI
- Ustaw niższą pojemność (10K TPM zamiast 20K) w `infra/core/ai/cognitiveservices.bicep`
- Usuwaj zasoby, gdy nie są używane: `azd down`

### Produkcja
Dla produkcji:
- Zwiększ pojemność OpenAI w zależności od użycia (50K+ TPM)
- Włącz redundancję strefową dla wyższej dostępności
- Wdróż odpowiedni monitoring i alerty kosztowe

### Szacowanie kosztów
- Azure OpenAI: Płatność za token (wejściowy + wyjściowy)
- GPT-5: około 3-5 USD za 1M tokenów (sprawdź aktualne ceny)
- text-embedding-3-small: około 0,02 USD za 1M tokenów

Kalkulator cen: https://azure.microsoft.com/pricing/calculator/

## Monitorowanie

### Przegląd metryk Azure OpenAI

Przejdź do Azure Portal → Twój zasób OpenAI → Metryki:
- Wykorzystanie tokenów
- Liczba żądań HTTP
- Czas odpowiedzi
- Aktywne tokeny

## Rozwiązywanie problemów

### Problem: Konflikt nazwy subdomeny Azure OpenAI

**Komunikat o błędzie:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Przyczyna:**
Nazwa subdomeny wygenerowana z twojej subskrypcji/środowiska jest już używana, prawdopodobnie z poprzedniego wdrożenia, które nie zostało całkowicie usunięte.

**Rozwiązanie:**
1. **Opcja 1 - Użyj innej nazwy środowiska:**
   
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

2. **Opcja 2 - Ręczne wdrożenie przez Azure Portal:**
   - Przejdź do Azure Portal → Utwórz zasób → Azure OpenAI
   - Wybierz unikalną nazwę zasobu
   - Wdróż następujące modele:
     - **GPT-5**
     - **text-embedding-3-small** (dla modułów RAG)
   - **Ważne:** Zanotuj nazwy wdrożeń - muszą odpowiadać konfiguracji `.env`
   - Po wdrożeniu pobierz punkt końcowy i klucz API z "Klucze i punkt końcowy"
   - Utwórz plik `.env` w katalogu głównym projektu z:

     **Przykładowy plik `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Zasady nazewnictwa wdrożeń modeli:**
- Używaj prostych, spójnych nazw: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Nazwy wdrożeń muszą dokładnie odpowiadać tym w `.env`
- Częsty błąd: tworzenie modelu pod jedną nazwą, a odwoływanie się do innej w kodzie

### Problem: GPT-5 niedostępny w wybranym regionie

**Rozwiązanie:**
- Wybierz region z dostępem do GPT-5 (np. eastus, swedencentral)
- Sprawdź dostępność: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Problem: Niewystarczający limit dla wdrożenia

**Rozwiązanie:**
1. Poproś o zwiększenie limitu w Azure Portal
2. Lub użyj niższej pojemności w `main.bicep` (np. capacity: 10)

### Problem: "Resource not found" podczas uruchamiania lokalnego

**Rozwiązanie:**
1. Sprawdź wdrożenie: `azd env get-values`
2. Zweryfikuj poprawność punktu końcowego i klucza
3. Upewnij się, że grupa zasobów istnieje w Azure Portal

### Problem: Niepowodzenie uwierzytelniania

**Rozwiązanie:**
- Sprawdź, czy `AZURE_OPENAI_API_KEY` jest poprawnie ustawiony
- Klucz powinien mieć format 32-znakowego ciągu szesnastkowego
- W razie potrzeby pobierz nowy klucz z Azure Portal

### Nieudane wdrożenie

**Problem**: `azd provision` kończy się błędami limitów lub pojemności

**Rozwiązanie**: 
1. Spróbuj innego regionu - zobacz sekcję [Zmiana regionów Azure](../../../../01-introduction/infra)
2. Sprawdź, czy twoja subskrypcja ma limit Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Aplikacja nie łączy się

**Problem**: Aplikacja Java zgłasza błędy połączenia

**Rozwiązanie**:
1. Zweryfikuj, czy zmienne środowiskowe są wyeksportowane:
   
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

2. Sprawdź, czy format punktu końcowego jest poprawny (powinien być `https://xxx.openai.azure.com`)
3. Zweryfikuj, czy klucz API to klucz podstawowy lub zapasowy z Azure Portal

**Problem**: 401 Unauthorized z Azure OpenAI

**Rozwiązanie**:
1. Pobierz nowy klucz API z Azure Portal → Klucze i punkt końcowy
2. Ponownie wyeksportuj zmienną środowiskową `AZURE_OPENAI_API_KEY`
3. Upewnij się, że wdrożenia modeli są kompletne (sprawdź w Azure Portal)

### Problemy z wydajnością

**Problem**: Wolne czasy odpowiedzi

**Rozwiązanie**:
1. Sprawdź użycie tokenów i ograniczenia w metrykach Azure Portal
2. Zwiększ pojemność TPM, jeśli osiągasz limity
3. Rozważ użycie wyższego poziomu wysiłku rozumowania (niski/średni/wysoki)

## Aktualizacja infrastruktury

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

## Zalecenia dotyczące bezpieczeństwa

1. **Nigdy nie commituj kluczy API** - używaj zmiennych środowiskowych
2. **Używaj plików .env lokalnie** - dodaj `.env` do `.gitignore`
3. **Regularnie rotuj klucze** - generuj nowe klucze w Azure Portal
4. **Ogranicz dostęp** - używaj Azure RBAC do kontroli dostępu do zasobów
5. **Monitoruj użycie** - ustaw alerty kosztowe w Azure Portal

## Dodatkowe zasoby

- [Dokumentacja usługi Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [Dokumentacja modelu GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Dokumentacja Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Dokumentacja Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Oficjalna integracja LangChain4j z OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Wsparcie

W przypadku problemów:
1. Sprawdź powyższą [sekcję rozwiązywania problemów](../../../../01-introduction/infra)
2. Przejrzyj stan usługi Azure OpenAI w Azure Portal
3. Otwórz zgłoszenie w repozytorium

## Licencja

Szczegóły w pliku [LICENSE](../../../../LICENSE) w katalogu głównym.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Zastrzeżenie**:  
Niniejszy dokument został przetłumaczony za pomocą usługi tłumaczenia AI [Co-op Translator](https://github.com/Azure/co-op-translator). Mimo że dokładamy starań, aby tłumaczenie było jak najbardziej precyzyjne, prosimy mieć na uwadze, że automatyczne tłumaczenia mogą zawierać błędy lub nieścisłości. Oryginalny dokument w języku źródłowym powinien być uznawany za źródło autorytatywne. W przypadku informacji krytycznych zalecane jest skorzystanie z profesjonalnego tłumaczenia wykonanego przez człowieka. Nie ponosimy odpowiedzialności za jakiekolwiek nieporozumienia lub błędne interpretacje wynikające z korzystania z tego tłumaczenia.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->