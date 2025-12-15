<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T14:53:47+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "pl"
}
-->
# Module 00: Szybki start

## Spis treści

- [Wprowadzenie](../../../00-quick-start)
- [Czym jest LangChain4j?](../../../00-quick-start)
- [Zależności LangChain4j](../../../00-quick-start)
- [Wymagania wstępne](../../../00-quick-start)
- [Konfiguracja](../../../00-quick-start)
  - [1. Uzyskaj swój token GitHub](../../../00-quick-start)
  - [2. Ustaw swój token](../../../00-quick-start)
- [Uruchom przykłady](../../../00-quick-start)
  - [1. Podstawowy czat](../../../00-quick-start)
  - [2. Wzorce promptów](../../../00-quick-start)
  - [3. Wywoływanie funkcji](../../../00-quick-start)
  - [4. Pytania i odpowiedzi na dokumentach (RAG)](../../../00-quick-start)
- [Co pokazuje każdy przykład](../../../00-quick-start)
- [Kolejne kroki](../../../00-quick-start)
- [Rozwiązywanie problemów](../../../00-quick-start)

## Wprowadzenie

Ten szybki start ma na celu jak najszybsze uruchomienie LangChain4j. Obejmuje absolutne podstawy budowania aplikacji AI z LangChain4j i modelami GitHub. W kolejnych modułach użyjesz Azure OpenAI z LangChain4j, aby tworzyć bardziej zaawansowane aplikacje.

## Czym jest LangChain4j?

LangChain4j to biblioteka Java, która upraszcza tworzenie aplikacji zasilanych AI. Zamiast zajmować się klientami HTTP i analizą JSON, pracujesz z czystymi interfejsami Java.

„Chain” w LangChain odnosi się do łączenia wielu komponentów – możesz połączyć prompt z modelem, a następnie z parserem, lub łączyć wiele wywołań AI, gdzie jedno wyjście zasila kolejne wejście. Ten szybki start skupia się na podstawach, zanim przejdziemy do bardziej złożonych łańcuchów.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1e961a13c73d45cfa305fd03d81bd11e5d34d0e36ed28a44d.pl.png" alt="LangChain4j Chaining Concept" width="800"/>

*Łączenie komponentów w LangChain4j – elementy budulcowe łączą się, tworząc potężne przepływy pracy AI*

Użyjemy trzech podstawowych komponentów:

**ChatLanguageModel** – interfejs do interakcji z modelem AI. Wywołaj `model.chat("prompt")` i otrzymaj odpowiedź w postaci tekstu. Używamy `OpenAiOfficialChatModel`, który działa z punktami końcowymi kompatybilnymi z OpenAI, takimi jak modele GitHub.

**AiServices** – tworzy typowo bezpieczne interfejsy usług AI. Definiujesz metody, oznaczasz je adnotacją `@Tool`, a LangChain4j zajmuje się orkiestracją. AI automatycznie wywołuje twoje metody Java, gdy jest to potrzebne.

**MessageWindowChatMemory** – utrzymuje historię rozmowy. Bez tego każde zapytanie jest niezależne. Z tym komponentem AI pamięta poprzednie wiadomości i utrzymuje kontekst przez wiele tur.

<img src="../../../translated_images/architecture.eedc993a1c57683951f20244f652fc7a9853f571eea835bc2b2828cf0dbf62d0.pl.png" alt="LangChain4j Architecture" width="800"/>

*Architektura LangChain4j – podstawowe komponenty współpracujące, aby zasilać twoje aplikacje AI*

## Zależności LangChain4j

Ten szybki start używa dwóch zależności Maven w [`pom.xml`](../../../00-quick-start/pom.xml):

```xml
<!-- Core LangChain4j library -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>

<!-- OpenAI integration (works with GitHub Models) -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

Moduł `langchain4j-open-ai-official` dostarcza klasę `OpenAiOfficialChatModel`, która łączy się z API kompatybilnym z OpenAI. Modele GitHub używają tego samego formatu API, więc nie jest potrzebny specjalny adapter – wystarczy wskazać bazowy URL na `https://models.github.ai/inference`.

## Wymagania wstępne

**Używasz kontenera deweloperskiego?** Java i Maven są już zainstalowane. Potrzebujesz tylko tokenu dostępu osobistego GitHub.

**Lokalny rozwój:**
- Java 21+, Maven 3.9+
- Token dostępu osobistego GitHub (instrukcje poniżej)

> **Uwaga:** Ten moduł używa `gpt-4.1-nano` z modeli GitHub. Nie zmieniaj nazwy modelu w kodzie – jest skonfigurowany do pracy z dostępnymi modelami GitHub.

## Konfiguracja

### 1. Uzyskaj swój token GitHub

1. Przejdź do [Ustawienia GitHub → Tokeny dostępu osobistego](https://github.com/settings/personal-access-tokens)
2. Kliknij „Generate new token”
3. Ustaw opisową nazwę (np. „LangChain4j Demo”)
4. Ustaw datę wygaśnięcia (zalecane 7 dni)
5. W sekcji „Uprawnienia konta” znajdź „Models” i ustaw na „Tylko do odczytu”
6. Kliknij „Generate token”
7. Skopiuj i zapisz token – nie zobaczysz go ponownie

### 2. Ustaw swój token

**Opcja 1: Użycie VS Code (zalecane)**

Jeśli używasz VS Code, dodaj swój token do pliku `.env` w katalogu głównym projektu:

Jeśli plik `.env` nie istnieje, skopiuj `.env.example` do `.env` lub utwórz nowy plik `.env` w katalogu głównym projektu.

**Przykładowy plik `.env`:**
```bash
# W /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Następnie możesz po prostu kliknąć prawym przyciskiem myszy dowolny plik demo (np. `BasicChatDemo.java`) w Eksploratorze i wybrać **„Run Java”** lub użyć konfiguracji uruchamiania z panelu Run and Debug.

**Opcja 2: Użycie terminala**

Ustaw token jako zmienną środowiskową:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Uruchom przykłady

**Używając VS Code:** Po prostu kliknij prawym przyciskiem myszy dowolny plik demo w Eksploratorze i wybierz **„Run Java”**, lub użyj konfiguracji uruchamiania z panelu Run and Debug (upewnij się, że najpierw dodałeś token do pliku `.env`).

**Używając Maven:** Alternatywnie możesz uruchomić z linii poleceń:

### 1. Podstawowy czat

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Wzorce promptów

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Pokazuje zero-shot, few-shot, chain-of-thought i role-based prompting.

### 3. Wywoływanie funkcji

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI automatycznie wywołuje twoje metody Java, gdy jest to potrzebne.

### 4. Pytania i odpowiedzi na dokumentach (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Zadaj pytania dotyczące zawartości `document.txt`.

## Co pokazuje każdy przykład

**Podstawowy czat** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Zacznij tutaj, aby zobaczyć LangChain4j w najprostszej formie. Stworzysz `OpenAiOfficialChatModel`, wyślesz prompt za pomocą `.chat()` i otrzymasz odpowiedź. To pokazuje fundamenty: jak inicjalizować modele z niestandardowymi punktami końcowymi i kluczami API. Gdy zrozumiesz ten wzorzec, wszystko inne na nim bazuje.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Wypróbuj z [GitHub Copilot](https://github.com/features/copilot) Chat:** Otwórz [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) i zapytaj:
> - „Jak przełączyć się z modeli GitHub na Azure OpenAI w tym kodzie?”
> - „Jakie inne parametry mogę konfigurować w OpenAiOfficialChatModel.builder()?”
> - „Jak dodać strumieniowe odpowiedzi zamiast czekać na pełną odpowiedź?”

**Inżynieria promptów** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Teraz, gdy wiesz, jak rozmawiać z modelem, zobaczmy, co do niego mówisz. To demo używa tego samego ustawienia modelu, ale pokazuje cztery różne wzorce promptów. Wypróbuj zero-shot dla bezpośrednich instrukcji, few-shot uczące się na przykładach, chain-of-thought ujawniające kroki rozumowania oraz role-based, które ustawiają kontekst. Zobaczysz, jak ten sam model daje dramatycznie różne wyniki w zależności od sposobu formułowania zapytania.

```java
PromptTemplate template = PromptTemplate.from(
    "What's the best time to visit {{destination}} for {{activity}}?"
);

Prompt prompt = template.apply(Map.of(
    "destination", "Paris",
    "activity", "sightseeing"
));

String response = model.chat(prompt.text());
```

> **🤖 Wypróbuj z [GitHub Copilot](https://github.com/features/copilot) Chat:** Otwórz [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) i zapytaj:
> - „Jaka jest różnica między zero-shot a few-shot prompting i kiedy używać każdego z nich?”
> - „Jak parametr temperature wpływa na odpowiedzi modelu?”
> - „Jakie są techniki zapobiegania atakom typu prompt injection w produkcji?”
> - „Jak tworzyć wielokrotnego użytku obiekty PromptTemplate dla popularnych wzorców?”

**Integracja narzędzi** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Tu LangChain4j staje się potężny. Użyjesz `AiServices`, aby stworzyć asystenta AI, który może wywoływać twoje metody Java. Wystarczy oznaczyć metody adnotacją `@Tool("opis")`, a LangChain4j zajmie się resztą – AI automatycznie decyduje, kiedy użyć którego narzędzia na podstawie pytań użytkownika. To pokazuje wywoływanie funkcji, kluczową technikę budowania AI, które może podejmować działania, a nie tylko odpowiadać na pytania.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Wypróbuj z [GitHub Copilot](https://github.com/features/copilot) Chat:** Otwórz [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) i zapytaj:
> - „Jak działa adnotacja @Tool i co LangChain4j robi z nią w tle?”
> - „Czy AI może wywołać wiele narzędzi po kolei, aby rozwiązać złożone problemy?”
> - „Co się stanie, jeśli narzędzie zgłosi wyjątek – jak obsługiwać błędy?”
> - „Jak zintegrować prawdziwe API zamiast tego przykładu kalkulatora?”

**Pytania i odpowiedzi na dokumentach (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Tu zobaczysz podstawy RAG (retrieval-augmented generation). Zamiast polegać na danych treningowych modelu, ładujesz zawartość z [`document.txt`](../../../00-quick-start/document.txt) i dołączasz ją do promptu. AI odpowiada na podstawie twojego dokumentu, a nie ogólnej wiedzy. To pierwszy krok do budowania systemów, które mogą pracować z twoimi własnymi danymi.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Uwaga:** To proste podejście ładuje cały dokument do promptu. Dla dużych plików (>10KB) przekroczysz limity kontekstu. Moduł 03 omawia dzielenie na fragmenty i wyszukiwanie wektorowe dla produkcyjnych systemów RAG.

> **🤖 Wypróbuj z [GitHub Copilot](https://github.com/features/copilot) Chat:** Otwórz [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) i zapytaj:
> - „Jak RAG zapobiega halucynacjom AI w porównaniu z używaniem danych treningowych modelu?”
> - „Jaka jest różnica między tym prostym podejściem a używaniem osadzeń wektorowych do wyszukiwania?”
> - „Jak skalować to, aby obsługiwać wiele dokumentów lub większe bazy wiedzy?”
> - „Jakie są najlepsze praktyki strukturyzowania promptu, aby AI używało tylko dostarczonego kontekstu?”

## Debugowanie

Przykłady zawierają `.logRequests(true)` i `.logResponses(true)`, aby pokazać wywołania API w konsoli. Pomaga to rozwiązywać problemy z uwierzytelnianiem, limitami lub nieoczekiwanymi odpowiedziami. Usuń te flagi w produkcji, aby zmniejszyć hałas w logach.

## Kolejne kroki

**Następny moduł:** [01-introduction - Rozpoczęcie pracy z LangChain4j i gpt-5 na Azure](../01-introduction/README.md)

---

**Nawigacja:** [← Wróć do głównego](../README.md) | [Dalej: Moduł 01 - Wprowadzenie →](../01-introduction/README.md)

---

## Rozwiązywanie problemów

### Pierwsze budowanie Maven

**Problem:** Początkowe `mvn clean compile` lub `mvn package` trwa długo (10-15 minut)

**Przyczyna:** Maven musi pobrać wszystkie zależności projektu (Spring Boot, biblioteki LangChain4j, SDK Azure itp.) przy pierwszym budowaniu.

**Rozwiązanie:** To normalne zachowanie. Kolejne budowania będą znacznie szybsze, ponieważ zależności są buforowane lokalnie. Czas pobierania zależy od prędkości twojej sieci.

### Składnia poleceń Maven w PowerShell

**Problem:** Polecenia Maven kończą się błędem `Unknown lifecycle phase ".mainClass=..."`

**Przyczyna:** PowerShell interpretuje `=` jako operator przypisania zmiennej, co psuje składnię właściwości Maven.

**Rozwiązanie:** Użyj operatora zatrzymania parsowania `--%` przed poleceniem Maven:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Operator `--%` mówi PowerShell, aby przekazał wszystkie pozostałe argumenty dosłownie do Mavena bez interpretacji.

### Wyświetlanie emoji w Windows PowerShell

**Problem:** Odpowiedzi AI pokazują nieczytelne znaki (np. `????` lub `â??`) zamiast emoji w PowerShell

**Przyczyna:** Domyślne kodowanie PowerShell nie obsługuje emoji UTF-8

**Rozwiązanie:** Uruchom to polecenie przed uruchomieniem aplikacji Java:
```cmd
chcp 65001
```

To wymusza kodowanie UTF-8 w terminalu. Alternatywnie użyj Windows Terminal, który ma lepsze wsparcie Unicode.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Zastrzeżenie**:  
Niniejszy dokument został przetłumaczony za pomocą usługi tłumaczenia AI [Co-op Translator](https://github.com/Azure/co-op-translator). Mimo że dokładamy starań, aby tłumaczenie było jak najbardziej precyzyjne, prosimy mieć na uwadze, że automatyczne tłumaczenia mogą zawierać błędy lub nieścisłości. Oryginalny dokument w języku źródłowym powinien być uznawany za źródło autorytatywne. W przypadku informacji krytycznych zalecane jest skorzystanie z profesjonalnego tłumaczenia wykonanego przez człowieka. Nie ponosimy odpowiedzialności za jakiekolwiek nieporozumienia lub błędne interpretacje wynikające z korzystania z tego tłumaczenia.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->