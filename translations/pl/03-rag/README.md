<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-05T23:12:32+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "pl"
}
-->
# Moduł 03: RAG (Generowanie Wzbogacone o Wyszukiwanie)

## Spis treści

- [Czego się nauczysz](../../../03-rag)
- [Wymagania wstępne](../../../03-rag)
- [Zrozumienie RAG](../../../03-rag)
- [Jak to działa](../../../03-rag)
  - [Przetwarzanie dokumentu](../../../03-rag)
  - [Tworzenie osadzeń](../../../03-rag)
  - [Wyszukiwanie semantyczne](../../../03-rag)
  - [Generowanie odpowiedzi](../../../03-rag)
- [Uruchom aplikację](../../../03-rag)
- [Używanie aplikacji](../../../03-rag)
  - [Prześlij dokument](../../../03-rag)
  - [Zadaj pytania](../../../03-rag)
  - [Sprawdź odniesienia źródłowe](../../../03-rag)
  - [Eksperymentuj z pytaniami](../../../03-rag)
- [Kluczowe koncepcje](../../../03-rag)
  - [Strategia dzielenia na fragmenty](../../../03-rag)
  - [Wskaźniki podobieństwa](../../../03-rag)
  - [Przechowywanie w pamięci operacyjnej](../../../03-rag)
  - [Zarządzanie oknem kontekstu](../../../03-rag)
- [Kiedy RAG ma znaczenie](../../../03-rag)
- [Następne kroki](../../../03-rag)

## Czego się nauczysz

W poprzednich modułach nauczyłeś się, jak rozmawiać z AI i jak efektywnie strukturyzować swoje zapytania. Ale istnieje podstawowe ograniczenie: modele językowe znają tylko to, czego nauczyły się podczas treningu. Nie są w stanie odpowiedzieć na pytania dotyczące polityk Twojej firmy, dokumentacji projektu czy innych informacji, na których nie były trenowane.

RAG (Generowanie Wzbogacone o Wyszukiwanie) rozwiązuje ten problem. Zamiast próbować nauczyć model Twoich informacji (co jest kosztowne i niepraktyczne), dajesz mu możliwość przeszukania Twoich dokumentów. Gdy ktoś zada pytanie, system znajduje odpowiednie informacje i dołącza je do zapytania. Model odpowiada w oparciu o ten pobrany kontekst.

Pomyśl o RAG jak o dawaniu modelowi biblioteki odniesień. Gdy zadajesz pytanie, system:

1. **Zapytanie użytkownika** – Ty zadajesz pytanie  
2. **Osadzenie (Embedding)** – Zapytanie jest przekształcane na wektor  
3. **Wyszukiwanie wektorowe** – Znajduje podobne fragmenty dokumentów  
4. **Tworzenie kontekstu** – Dodaje odpowiednie fragmenty do zapytania  
5. **Odpowiedź** – LLM generuje odpowiedź na podstawie kontekstu  

Dzięki temu odpowiedzi modelu są oparte na Twoich rzeczywistych danych, a nie tylko na wiedzy z treningu czy wymyślone.

<img src="../../../translated_images/pl/rag-architecture.ccb53b71a6ce407f.png" alt="Architektura RAG" width="800"/>

*Przebieg działania RAG – od zapytania użytkownika do wyszukiwania semantycznego i generowania odpowiedzi z kontekstem*

## Wymagania wstępne

- Ukończony Moduł 01 (zdeployowane zasoby Azure OpenAI)  
- Plik `.env` w katalogu głównym z danymi uwierzytelniającymi Azure (utworzony przez `azd up` w Module 01)  

> **Uwaga:** Jeśli nie ukończyłeś Modułu 01, najpierw wykonaj tamte instrukcje dotyczące wdrożenia.

## Jak to działa

### Przetwarzanie dokumentu

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)

Gdy przesyłasz dokument, system dzieli go na fragmenty – mniejsze części, które mieszczą się w oknie kontekstu modelu. Fragmenty nieznacznie się nakładają, by nie tracić kontekstu na granicach.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```
  
> **🤖 Wypróbuj z [GitHub Copilot](https://github.com/features/copilot) Chat:** Otwórz [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) i zapytaj:  
> - "Jak LangChain4j dzieli dokumenty na fragmenty i dlaczego ważne jest nakładanie?"  
> - "Jaki jest optymalny rozmiar fragmentu dla różnych typów dokumentów i dlaczego?"  
> - "Jak obsługiwać dokumenty w wielu językach lub ze specjalnym formatowaniem?"

### Tworzenie osadzeń

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)

Każdy fragment jest przekształcany w reprezentację numeryczną zwaną osadzeniem – zasadniczo matematyczny odcisk palca, który uchwyca znaczenie tekstu. Podobne teksty generują podobne osadzenia.

```java
@Bean
public EmbeddingModel embeddingModel() {
    return OpenAiOfficialEmbeddingModel.builder()
        .baseUrl(azureOpenAiEndpoint)
        .apiKey(azureOpenAiKey)
        .modelName(azureEmbeddingDeploymentName)
        .build();
}

EmbeddingStore<TextSegment> embeddingStore = 
    new InMemoryEmbeddingStore<>();
```
  
<img src="../../../translated_images/pl/vector-embeddings.2ef7bdddac79a327.png" alt="Przestrzeń osadzeń wektorowych" width="800"/>

*Dokumenty reprezentowane jako wektory w przestrzeni osadzeń – podobne treści grupują się razem*

### Wyszukiwanie semantyczne

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Gdy zadajesz pytanie, ono również zostaje przekształcone w osadzenie. System porównuje osadzenie Twojego zapytania z osadzeniami wszystkich fragmentów dokumentów. Znajduje fragmenty o najbardziej zbliżonym znaczeniu – nie tylko dopasowanie słów kluczowych, lecz prawdziwe podobieństwo semantyczne.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```
  
> **🤖 Wypróbuj z [GitHub Copilot](https://github.com/features/copilot) Chat:** Otwórz [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) i zapytaj:  
> - "Jak działa wyszukiwanie podobieństwa z osadzeniami i co decyduje o wyniku?"  
> - "Jaki próg podobieństwa powinienem używać i jak wpływa to na wyniki?"  
> - "Jak obsłużyć sytuacje, gdy nie ma odpowiednich dokumentów?"

### Generowanie odpowiedzi

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)

Najbardziej odpowiednie fragmenty są dołączane do zapytania do modelu. Model czyta te konkretne fragmenty i odpowiada na pytanie na ich podstawie. Zapobiega to halucynacjom – model może odpowiedzieć tylko na podstawie tego, co ma przed sobą.

## Uruchom aplikację

**Zweryfikuj wdrożenie:**  

Upewnij się, że plik `.env` istnieje w katalogu głównym z danymi uwierzytelniającymi Azure (utworzony podczas Modułu 01):  
```bash
cat ../.env  # Powinno pokazywać AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```
  
**Uruchom aplikację:**  

> **Uwaga:** Jeśli już uruchomiłeś wszystkie aplikacje za pomocą `./start-all.sh` z Modułu 01, ten moduł już działa na porcie 8081. Możesz pominąć poniższe polecenia i przejść bezpośrednio na http://localhost:8081.

**Opcja 1: Użycie Spring Boot Dashboard (zalecane dla użytkowników VS Code)**  

Kontener developerski zawiera rozszerzenie Spring Boot Dashboard, które udostępnia interfejs wizualny do zarządzania wszystkimi aplikacjami Spring Boot. Znajdziesz je na pasku aktywności po lewej stronie VS Code (szukaj ikony Spring Boot).

Z poziomu Spring Boot Dashboard możesz:  
- Zobaczyć wszystkie dostępne aplikacje Spring Boot w przestrzeni roboczej  
- Uruchamiać/zatrzymywać aplikacje jednym kliknięciem  
- Podglądać logi aplikacji na bieżąco  
- Monitorować status aplikacji  

Po prostu kliknij przycisk "play" obok "rag", aby uruchomić ten moduł lub uruchom wszystkie moduły naraz.

<img src="../../../translated_images/pl/dashboard.fbe6e28bf4267ffe.png" alt="Spring Boot Dashboard" width="400"/>

**Opcja 2: Użycie skryptów shell**  

Uruchom wszystkie aplikacje webowe (moduły 01-04):

**Bash:**  
```bash
cd ..  # Z katalogu głównego
./start-all.sh
```
  
**PowerShell:**  
```powershell
cd ..  # Z katalogu głównego
.\start-all.ps1
```
  
Lub uruchom tylko ten moduł:

**Bash:**  
```bash
cd 03-rag
./start.sh
```
  
**PowerShell:**  
```powershell
cd 03-rag
.\start.ps1
```
  
Oba skrypty automatycznie ładują zmienne środowiskowe z pliku `.env` w katalogu głównym i zbudują JAR-y, jeśli nie istnieją.

> **Uwaga:** Jeśli wolisz najpierw ręcznie zbudować wszystkie moduły przed uruchomieniem:  
>  
> **Bash:**  
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
>  
> **PowerShell:**  
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
  
Otwórz http://localhost:8081 w przeglądarce.

**Aby zatrzymać:**  

**Bash:**  
```bash
./stop.sh  # Tylko ten moduł
# Lub
cd .. && ./stop-all.sh  # Wszystkie moduły
```
  
**PowerShell:**  
```powershell
.\stop.ps1  # Tylko ten moduł
# Lub
cd ..; .\stop-all.ps1  # Wszystkie moduły
```
  
## Używanie aplikacji

Aplikacja oferuje interfejs webowy do przesyłania dokumentów i zadawania pytań.

<a href="images/rag-homepage.png"><img src="../../../translated_images/pl/rag-homepage.d90eb5ce1b3caa94.png" alt="Interfejs aplikacji RAG" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Interfejs aplikacji RAG – prześlij dokument i zadaj pytania*

### Prześlij dokument

Zacznij od przesłania dokumentu – najlepiej testować na plikach TXT. W tym katalogu znajduje się `sample-document.txt` z informacjami o funkcjach LangChain4j, implementacji RAG i najlepszych praktykach – idealny do testowania systemu.

System przetwarza Twój dokument, dzieli go na fragmenty i tworzy osadzenia dla każdego z nich. Dzieje się to automatycznie po przesłaniu.

### Zadaj pytania

Teraz zadaj konkretne pytania dotyczące zawartości dokumentu. Spróbuj czegoś faktycznego, co jest jasno opisane w tekście. System wyszukuje odpowiednie fragmenty, dołącza je do zapytania i generuje odpowiedź.

### Sprawdź odniesienia źródłowe

Zauważ, że każda odpowiedź zawiera odniesienia źródłowe wraz ze wskaźnikami podobieństwa. Te wyniki (0 do 1) pokazują, jak bardzo dany fragment odpowiada Twojemu pytaniu. Wyższe wyniki oznaczają lepsze dopasowanie. Dzięki temu możesz zweryfikować odpowiedź względem oryginalnego materiału.

<a href="images/rag-query-results.png"><img src="../../../translated_images/pl/rag-query-results.6d69fcec5397f355.png" alt="Wyniki zapytania RAG" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Wyniki zapytania pokazujące odpowiedź wraz z odniesieniami źródłowymi i wskaźnikami trafności*

### Eksperymentuj z pytaniami

Wypróbuj różne typy pytań:  
- Konkretne fakty: "Jaki jest główny temat?"  
- Porównania: "Jaka jest różnica między X a Y?"  
- Podsumowania: "Podsumuj kluczowe punkty dotyczące Z"

Obserwuj, jak zmieniają się wskaźniki trafności w zależności od tego, jak dobrze Twoje pytanie pasuje do treści dokumentu.

## Kluczowe koncepcje

### Strategia dzielenia na fragmenty

Dokumenty dzielone są na fragmenty o długości 300 tokenów z 30-tokenowym nakładaniem. Ta równowaga zapewnia, że każdy fragment zawiera wystarczająco dużo kontekstu, by być znaczącym, a jednocześnie jest na tyle mały, by można było umieścić wiele fragmentów w jednym zapytaniu.

### Wskaźniki podobieństwa

Wyniki wahają się od 0 do 1:  
- 0.7-1.0: Bardzo trafne, dokładne dopasowanie  
- 0.5-0.7: Trafne, dobry kontekst  
- Poniżej 0.5: Odfiltrowane, zbyt różne  

System pobiera tylko fragmenty powyżej minimalnego progu, by zapewnić jakość.

### Przechowywanie w pamięci operacyjnej

Ten moduł używa przechowywania w pamięci operacyjnej dla uproszczenia. Po ponownym uruchomieniu aplikacji przesłane dokumenty są tracone. Systemy produkcyjne korzystają z trwałych baz danych wektorowych takich jak Qdrant czy Azure AI Search.

### Zarządzanie oknem kontekstu

Każdy model ma maksymalne okno kontekstowe. Nie możesz dołączyć wszystkich fragmentów dużego dokumentu. System pobiera N najbardziej trafnych fragmentów (domyślnie 5), by pozostać w limitach i zapewnić wystarczający kontekst dla precyzyjnych odpowiedzi.

## Kiedy RAG ma znaczenie

**Używaj RAG gdy:**  
- Odpowiadasz na pytania dotyczące własnych dokumentów  
- Informacje często się zmieniają (polityki, ceny, specyfikacje)  
- Dokładność wymaga podania źródła  
- Treść jest zbyt obszerna, by zmieścić się w jednym zapytaniu  
- Potrzebujesz weryfikowalnych, ugruntowanych odpowiedzi

**Nie używaj RAG gdy:**  
- Pytania dotyczą ogólnej wiedzy, którą model już posiada  
- Potrzebne są dane w czasie rzeczywistym (RAG działa na przesłanych dokumentach)  
- Treść jest na tyle mała, że można ją zawrzeć bezpośrednio w zapytaniu

## Następne kroki

**Następny moduł:** [04-tools - Agenci AI z narzędziami](../04-tools/README.md)

---

**Nawigacja:** [← Poprzedni: Moduł 02 - Inżynieria promptów](../02-prompt-engineering/README.md) | [Powrót do głównej](../README.md) | [Następny: Moduł 04 - Narzędzia →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Zastrzeżenie**:  
Niniejszy dokument został przetłumaczony za pomocą usługi tłumaczeń AI [Co-op Translator](https://github.com/Azure/co-op-translator). Chociaż dokładamy wszelkich starań, aby zapewnić poprawność, prosimy pamiętać, że tłumaczenia automatyczne mogą zawierać błędy lub niedokładności. Oryginalny dokument w jego języku źródłowym należy traktować jako źródło autorytatywne. W przypadku informacji krytycznych zalecane jest skorzystanie z profesjonalnego tłumaczenia wykonanego przez człowieka. Nie ponosimy odpowiedzialności za jakiekolwiek nieporozumienia lub błędne interpretacje wynikające z użycia tego tłumaczenia.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->