<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T00:24:07+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "pl"
}
-->
# Słownik LangChain4j

## Spis treści

- [Core Concepts](../../../docs)
- [LangChain4j Components](../../../docs)
- [AI/ML Concepts](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agents and Tools](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Services](../../../docs)
- [Testing and Development](../../../docs)

Szybkie odniesienie dotyczące terminów i pojęć używanych w całym kursie.

## Core Concepts

**AI Agent** - System, który wykorzystuje AI do rozumowania i działania autonomicznego. [Module 04](../04-tools/README.md)

**Chain** - Sekwencja operacji, w której wyjście jest przekazywane do następnego kroku.

**Chunking** - Dzielenie dokumentów na mniejsze części. Typowo: 300-500 tokenów z nakładaniem. [Module 03](../03-rag/README.md)

**Context Window** - Maksymalna liczba tokenów, które model może przetworzyć. GPT-5: 400K tokenów.

**Embeddings** - Wektory numeryczne reprezentujące znaczenie tekstu. [Module 03](../03-rag/README.md)

**Function Calling** - Model generuje ustrukturyzowane żądania do wywołania zewnętrznych funkcji. [Module 04](../04-tools/README.md)

**Hallucination** - Gdy modele generują nieprawidłowe, lecz wiarygodnie wyglądające informacje.

**Prompt** - Tekst wejściowy dla modelu językowego. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - Wyszukiwanie według znaczenia z użyciem osadzeń, zamiast słów kluczowych. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Bezstanowy: brak pamięci. Stanowy: utrzymuje historię konwersacji. [Module 01](../01-introduction/README.md)

**Tokens** - Podstawowe jednostki tekstu, które modele przetwarzają. Wpływają na koszty i limity. [Module 01](../01-introduction/README.md)

**Tool Chaining** - Sekwencyjne wykonywanie narzędzi, gdzie wynik wpływa na kolejne wywołanie. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Tworzy typowane interfejsy usług AI.

**OpenAiOfficialChatModel** - Zunifikowany klient dla modeli OpenAI i Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Tworzy embeddingi przy użyciu oficjalnego klienta OpenAI (obsługuje zarówno OpenAI, jak i Azure OpenAI).

**ChatModel** - Główny interfejs dla modeli językowych.

**ChatMemory** - Utrzymuje historię konwersacji.

**ContentRetriever** - Znajduje odpowiednie fragmenty dokumentów do RAG.

**DocumentSplitter** - Dzieli dokumenty na fragmenty.

**EmbeddingModel** - Konwertuje tekst na wektory numeryczne.

**EmbeddingStore** - Przechowuje i pobiera embeddingi.

**MessageWindowChatMemory** - Utrzymuje przesuwające się okno ostatnich wiadomości.

**PromptTemplate** - Tworzy wielokrotnego użytku prompty z symbolami zastępczymi `{{variable}}`.

**TextSegment** - Fragment tekstu z metadanymi. Używany w RAG.

**ToolExecutionRequest** - Reprezentuje żądanie wykonania narzędzia.

**UserMessage / AiMessage / SystemMessage** - Typy wiadomości w rozmowie.

## AI/ML Concepts

**Few-Shot Learning** - Udostępnianie przykładów w promptach. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Duże modele językowe trenowane na ogromnych zbiorach tekstu.

**Reasoning Effort** - Parametr GPT-5 kontrolujący głębokość rozumowania. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Kontroluje losowość odpowiedzi. Niska=deterministyczne, wysoka=kreatywne.

**Vector Database** - Specjalizowana baza danych dla embeddingów. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Wykonywanie zadań bez przykładów. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Rozumowanie krok po kroku dla lepszej dokładności.

**Constrained Output** - Wymuszanie określonego formatu lub struktury.

**High Eagerness** - Wzorzec GPT-5 dla gruntownego rozumowania.

**Low Eagerness** - Wzorzec GPT-5 dla szybkich odpowiedzi.

**Multi-Turn Conversation** - Utrzymanie kontekstu w kolejnych wymianach.

**Role-Based Prompting** - Ustawianie persony modelu poprzez wiadomości systemowe.

**Self-Reflection** - Model ocenia i poprawia swoje wyjście.

**Structured Analysis** - Stałe ramy oceny.

**Task Execution Pattern** - Zaplanuj → Wykonaj → Podsumuj.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Załaduj → podziel → osadź → zapisz.

**In-Memory Embedding Store** - Pamięciowy magazyn embeddingów do testów (przechowywanie nietrwałe).

**RAG** - Łączy pobieranie z generowaniem, aby ugruntować odpowiedzi.

**Similarity Score** - Miara (0-1) semantycznego podobieństwa.

**Source Reference** - Metadane dotyczące pobranej treści.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Oznacza metody Javy jako narzędzia dostępne dla AI.

**ReAct Pattern** - Rozumuj → Działaj → Obserwuj → Powtórz.

**Session Management** - Oddzielne konteksty dla różnych użytkowników.

**Tool** - Funkcja, którą agent AI może wywołać.

**Tool Description** - Dokumentacja celu narzędzia i jego parametrów.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - Standard łączenia aplikacji AI z zewnętrznymi narzędziami.

**MCP Client** - Aplikacja łącząca się z serwerami MCP.

**MCP Server** - Usługa udostępniająca narzędzia przez MCP.

**Stdio Transport** - Serwer jako podproces przez stdin/stdout.

**Tool Discovery** - Klient pyta serwer o dostępne narzędzia.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Wyszukiwanie w chmurze z funkcjami wektorowymi. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Wdraża zasoby Azure.

**Azure OpenAI** - Korporacyjna usługa AI Microsoftu.

**Bicep** - Język infrastruktury jako kod dla Azure. [Przewodnik po infrastrukturze](../01-introduction/infra/README.md)

**Deployment Name** - Nazwa wdrożenia modelu w Azure.

**GPT-5** - Najnowszy model OpenAI z kontrolą rozumowania. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Przewodnik testowania](TESTING.md)

**Dev Container** - Środowisko programistyczne w kontenerze. [Konfiguracja](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Darmowe środowisko modeli AI. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - Testowanie z użyciem pamięci ulotnej.

**Integration Testing** - Testowanie z prawdziwą infrastrukturą.

**Maven** - Narzędzie automatyzacji budowania dla Javy.

**Mockito** - Framework do mockowania w Javie.

**Spring Boot** - Framework aplikacji Java. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Zastrzeżenie:
Niniejszy dokument został przetłumaczony przy użyciu usługi tłumaczenia AI Co-op Translator (https://github.com/Azure/co-op-translator). Chociaż dokładamy starań, aby tłumaczenia były jak najbardziej dokładne, prosimy pamiętać, że tłumaczenia automatyczne mogą zawierać błędy lub nieścisłości. Oryginalny dokument w języku źródłowym powinien być traktowany jako dokument wiążący. W przypadku informacji krytycznych zalecane jest skorzystanie z profesjonalnego tłumaczenia wykonanego przez człowieka. Nie ponosimy odpowiedzialności za jakiekolwiek nieporozumienia lub błędne interpretacje wynikające z użycia tego tłumaczenia.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->