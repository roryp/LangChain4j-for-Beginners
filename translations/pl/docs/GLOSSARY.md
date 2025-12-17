<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:05:31+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "pl"
}
-->
# Słownik LangChain4j

## Spis treści

- [Podstawowe pojęcia](../../../docs)
- [Komponenty LangChain4j](../../../docs)
- [Pojęcia AI/ML](../../../docs)
- [Inżynieria promptów](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenci i narzędzia](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Usługi Azure](../../../docs)
- [Testowanie i rozwój](../../../docs)

Szybkie odniesienie do terminów i pojęć używanych w całym kursie.

## Podstawowe pojęcia

**Agent AI** - System wykorzystujący AI do rozumowania i działania autonomicznego. [Moduł 04](../04-tools/README.md)

**Łańcuch** - Sekwencja operacji, gdzie wyjście jest wejściem do następnego kroku.

**Dzielenie na fragmenty (Chunking)** - Dzielenie dokumentów na mniejsze części. Typowo: 300-500 tokenów z nakładką. [Moduł 03](../03-rag/README.md)

**Okno kontekstu** - Maksymalna liczba tokenów, które model może przetworzyć. GPT-5: 400K tokenów.

**Wektory osadzeń (Embeddings)** - Numeryczne wektory reprezentujące znaczenie tekstu. [Moduł 03](../03-rag/README.md)

**Wywoływanie funkcji** - Model generuje ustrukturyzowane żądania wywołania funkcji zewnętrznych. [Moduł 04](../04-tools/README.md)

**Halucynacje** - Gdy modele generują nieprawidłowe, ale wiarygodne informacje.

**Prompt** - Tekst wejściowy do modelu językowego. [Moduł 02](../02-prompt-engineering/README.md)

**Wyszukiwanie semantyczne** - Wyszukiwanie według znaczenia za pomocą osadzeń, nie słów kluczowych. [Moduł 03](../03-rag/README.md)

**Stanowy vs bezstanowy** - Bezstanowy: brak pamięci. Stanowy: utrzymuje historię rozmowy. [Moduł 01](../01-introduction/README.md)

**Tokeny** - Podstawowe jednostki tekstu przetwarzane przez modele. Wpływają na koszty i limity. [Moduł 01](../01-introduction/README.md)

**Łańcuchowanie narzędzi** - Sekwencyjne wykonywanie narzędzi, gdzie wyjście informuje kolejne wywołanie. [Moduł 04](../04-tools/README.md)

## Komponenty LangChain4j

**AiServices** - Tworzy typowo-bezpieczne interfejsy usług AI.

**OpenAiOfficialChatModel** - Zunifikowany klient dla modeli OpenAI i Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Tworzy osadzenia używając oficjalnego klienta OpenAI (obsługuje OpenAI i Azure OpenAI).

**ChatModel** - Podstawowy interfejs modeli językowych.

**ChatMemory** - Utrzymuje historię rozmowy.

**ContentRetriever** - Znajduje odpowiednie fragmenty dokumentów dla RAG.

**DocumentSplitter** - Dzieli dokumenty na fragmenty.

**EmbeddingModel** - Konwertuje tekst na wektory numeryczne.

**EmbeddingStore** - Przechowuje i pobiera osadzenia.

**MessageWindowChatMemory** - Utrzymuje przesuwne okno ostatnich wiadomości.

**PromptTemplate** - Tworzy wielokrotnego użytku prompt z miejscami na `{{variable}}`.

**TextSegment** - Fragment tekstu z metadanymi. Używany w RAG.

**ToolExecutionRequest** - Reprezentuje żądanie wykonania narzędzia.

**UserMessage / AiMessage / SystemMessage** - Typy wiadomości w rozmowie.

## Pojęcia AI/ML

**Uczenie na kilku przykładach (Few-Shot Learning)** - Podawanie przykładów w promptach. [Moduł 02](../02-prompt-engineering/README.md)

**Duży model językowy (LLM)** - Modele AI trenowane na ogromnych zbiorach tekstu.

**Wysiłek rozumowania** - Parametr GPT-5 kontrolujący głębokość myślenia. [Moduł 02](../02-prompt-engineering/README.md)

**Temperatura** - Kontroluje losowość wyjścia. Niska=deterministyczne, wysoka=kreatywne.

**Baza danych wektorów** - Specjalistyczna baza dla osadzeń. [Moduł 03](../03-rag/README.md)

**Uczenie zero-shot** - Wykonywanie zadań bez przykładów. [Moduł 02](../02-prompt-engineering/README.md)

## Inżynieria promptów - [Moduł 02](../02-prompt-engineering/README.md)

**Łańcuch myślenia (Chain-of-Thought)** - Rozumowanie krok po kroku dla lepszej dokładności.

**Ograniczony output** - Wymuszanie określonego formatu lub struktury.

**Wysoka chęć (High Eagerness)** - Wzorzec GPT-5 dla dokładnego rozumowania.

**Niska chęć (Low Eagerness)** - Wzorzec GPT-5 dla szybkich odpowiedzi.

**Wielokrotna wymiana (Multi-Turn Conversation)** - Utrzymywanie kontekstu przez wymiany.

**Promptowanie oparte na roli** - Ustawianie persony modelu przez wiadomości systemowe.

**Auto-refleksja** - Model ocenia i poprawia swoje wyjście.

**Strukturalna analiza** - Stały schemat oceny.

**Wzorzec wykonania zadania** - Plan → Wykonaj → Podsumuj.

## RAG (Retrieval-Augmented Generation) - [Moduł 03](../03-rag/README.md)

**Pipeline przetwarzania dokumentów** - Ładowanie → dzielenie → osadzanie → przechowywanie.

**Pamięć osadzeń w pamięci (In-Memory Embedding Store)** - Niepermanentne przechowywanie do testów.

**RAG** - Łączy wyszukiwanie z generowaniem, by ugruntować odpowiedzi.

**Wskaźnik podobieństwa** - Miara (0-1) podobieństwa semantycznego.

**Referencja źródła** - Metadane o pobranej treści.

## Agenci i narzędzia - [Moduł 04](../04-tools/README.md)

**Adnotacja @Tool** - Oznacza metody Java jako narzędzia wywoływane przez AI.

**Wzorzec ReAct** - Rozumuj → Działaj → Obserwuj → Powtarzaj.

**Zarządzanie sesją** - Oddzielne konteksty dla różnych użytkowników.

**Narzędzie** - Funkcja, którą agent AI może wywołać.

**Opis narzędzia** - Dokumentacja celu i parametrów narzędzia.

## Model Context Protocol (MCP) - [Moduł 05](../05-mcp/README.md)

**Transport Docker** - Serwer MCP w kontenerze Docker.

**MCP** - Standard łączenia aplikacji AI z narzędziami zewnętrznymi.

**Klient MCP** - Aplikacja łącząca się z serwerami MCP.

**Serwer MCP** - Usługa udostępniająca narzędzia przez MCP.

**Server-Sent Events (SSE)** - Strumieniowanie z serwera do klienta przez HTTP.

**Transport Stdio** - Serwer jako podproces przez stdin/stdout.

**Streamowalny transport HTTP** - HTTP z SSE dla komunikacji w czasie rzeczywistym.

**Odkrywanie narzędzi** - Klient pyta serwer o dostępne narzędzia.

## Usługi Azure - [Moduł 01](../01-introduction/README.md)

**Azure AI Search** - Chmurowe wyszukiwanie z funkcjami wektorowymi. [Moduł 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Wdraża zasoby Azure.

**Azure OpenAI** - Usługa AI przedsiębiorstwa Microsoft.

**Bicep** - Język infrastruktury jako kod dla Azure. [Przewodnik infrastruktury](../01-introduction/infra/README.md)

**Nazwa wdrożenia** - Nazwa wdrożenia modelu w Azure.

**GPT-5** - Najnowszy model OpenAI z kontrolą rozumowania. [Moduł 02](../02-prompt-engineering/README.md)

## Testowanie i rozwój - [Przewodnik testowania](TESTING.md)

**Dev Container** - Konteneryzowane środowisko deweloperskie. [Konfiguracja](../../../.devcontainer/devcontainer.json)

**Modele GitHub** - Darmowy plac zabaw dla modeli AI. [Moduł 00](../00-quick-start/README.md)

**Testowanie w pamięci** - Testowanie z pamięcią w RAM.

**Testy integracyjne** - Testowanie z prawdziwą infrastrukturą.

**Maven** - Narzędzie do automatyzacji budowy Java.

**Mockito** - Framework do mockowania w Javie.

**Spring Boot** - Framework aplikacji Java. [Moduł 01](../01-introduction/README.md)

**Testcontainers** - Kontenery Docker w testach.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Zastrzeżenie**:  
Niniejszy dokument został przetłumaczony za pomocą usługi tłumaczenia AI [Co-op Translator](https://github.com/Azure/co-op-translator). Mimo że dokładamy starań, aby tłumaczenie było jak najbardziej precyzyjne, prosimy mieć na uwadze, że automatyczne tłumaczenia mogą zawierać błędy lub nieścisłości. Oryginalny dokument w języku źródłowym powinien być uznawany za źródło autorytatywne. W przypadku informacji krytycznych zalecane jest skorzystanie z profesjonalnego tłumaczenia wykonanego przez człowieka. Nie ponosimy odpowiedzialności za jakiekolwiek nieporozumienia lub błędne interpretacje wynikające z korzystania z tego tłumaczenia.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->