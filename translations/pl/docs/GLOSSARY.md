# Słownik LangChain4j

## Spis treści

- [Podstawowe pojęcia](../../../docs)
- [Komponenty LangChain4j](../../../docs)
- [Pojęcia AI/ML](../../../docs)
- [Guardrails](../../../docs)
- [Inżynieria promptów](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenci i narzędzia](../../../docs)
- [Moduł agentowy](../../../docs)
- [Protokół kontekstu modelu (MCP)](../../../docs)
- [Usługi Azure](../../../docs)
- [Testowanie i rozwój](../../../docs)

Szybkie odniesienie do terminów i pojęć używanych w całym kursie.

## Podstawowe pojęcia

**Agent AI** - System, który używa sztucznej inteligencji do samodzielnego rozumowania i działania. [Moduł 04](../04-tools/README.md)

**Łańcuch (Chain)** - Sekwencja operacji, gdzie wyjście jest wejściem do następnego kroku.

**Chunking** - Dzielenie dokumentów na mniejsze kawałki. Typowo: 300-500 tokenów z nakładką. [Moduł 03](../03-rag/README.md)

**Okno kontekstu** - Maksymalna liczba tokenów, które model może przetworzyć. GPT-5: 400 tys. tokenów.

**Embeddingi** - Numeryczne wektory reprezentujące znaczenie tekstu. [Moduł 03](../03-rag/README.md)

**Wywoływanie funkcji** - Model generuje ustrukturyzowane żądania do wywołania zewnętrznych funkcji. [Moduł 04](../04-tools/README.md)

**Halucynacje** - Gdy modele generują nieprawidłowe, lecz wiarygodne informacje.

**Prompt** - Tekst wejściowy do modelu językowego. [Moduł 02](../02-prompt-engineering/README.md)

**Wyszukiwanie semantyczne** - Wyszukiwanie według znaczenia przy użyciu embeddingów, nie słów kluczowych. [Moduł 03](../03-rag/README.md)

**Stanowy vs bezstanowy** - Bezstanowy: bez pamięci. Stanowy: utrzymuje historię rozmowy. [Moduł 01](../01-introduction/README.md)

**Tokeny** - Podstawowe jednostki tekstu przetwarzane przez modele. Wpływa na koszty i limity. [Moduł 01](../01-introduction/README.md)

**Łańcuchowanie narzędzi** - Sekwencyjne wykonywanie narzędzi, gdzie wynik informuje kolejne wywołanie. [Moduł 04](../04-tools/README.md)

## Komponenty LangChain4j

**AiServices** - Tworzy typowo bezpieczne interfejsy usług AI.

**OpenAiOfficialChatModel** - Zunifikowany klient dla modeli OpenAI i Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Tworzy embeddingi używając oficjalnego klienta OpenAI (obsługuje OpenAI oraz Azure OpenAI).

**ChatModel** - Podstawowy interfejs dla modeli językowych.

**ChatMemory** - Utrzymuje historię rozmów.

**ContentRetriever** - Znajduje odpowiednie fragmenty dokumentów do RAG.

**DocumentSplitter** - Dzieli dokumenty na fragmenty.

**EmbeddingModel** - Konwertuje tekst na wektory liczbowe.

**EmbeddingStore** - Przechowuje i pobiera embeddingi.

**MessageWindowChatMemory** - Utrzymuje przesuwne okno ostatnich wiadomości.

**PromptTemplate** - Tworzy wielokrotnego użytku prompt z `{{variable}}` jako miejscami na zmienne.

**TextSegment** - Fragment tekstu ze metadanymi. Używane w RAG.

**ToolExecutionRequest** - Reprezentuje żądanie wykonania narzędzia.

**UserMessage / AiMessage / SystemMessage** - Typy wiadomości w rozmowie.

## Pojęcia AI/ML

**Few-Shot Learning** - Podawanie przykładów w promptach. [Moduł 02](../02-prompt-engineering/README.md)

**Duży model językowy (LLM)** - Modele AI trenowane na ogromnych zbiorach tekstów.

**Wysiłek rozumowania** - Parametr GPT-5 kontrolujący głębokość myślenia. [Moduł 02](../02-prompt-engineering/README.md)

**Temperatura** - Kontroluje losowość outputu. Niska=deterministyczna, wysoka=kreacyjna.

**Baza wektorowa** - Specjalistyczna baza dla embeddingów. [Moduł 03](../03-rag/README.md)

**Zero-Shot Learning** - Wykonywanie zadań bez przykładów. [Moduł 02](../02-prompt-engineering/README.md)

## Guardrails - [Moduł 00](../00-quick-start/README.md)

**Obrona w głębii (Defense in Depth)** - Wielowarstwowe podejście do bezpieczeństwa łączące zabezpieczenia na poziomie aplikacji z filtrami bezpieczeństwa dostawcy.

**Hard Block** - Dostawca zwraca błąd HTTP 400 w przypadku poważnych naruszeń treści.

**InputGuardrail** - Interfejs LangChain4j do walidacji danych wejściowych użytkownika przed dotarciem do LLM. Oszczędza koszty i czas blokując szkodliwe prompt-y na wczesnym etapie.

**InputGuardrailResult** - Typ zwracany przy walidacji guardrail: `success()` lub `fatal("powód")`.

**OutputGuardrail** - Interfejs do walidacji odpowiedzi AI przed zwróceniem użytkownikom.

**Filtry bezpieczeństwa dostawcy** - Wbudowane filtry treści od dostawców AI (np. GitHub Models) wychwytujące naruszenia na poziomie API.

**Miękka odmowa (Soft Refusal)** - Model grzecznie odmawia odpowiedzi bez zwracania błędu.

## Inżynieria promptów - [Moduł 02](../02-prompt-engineering/README.md)

**Łańcuch myślenia (Chain-of-Thought)** - Rozumowanie krok po kroku dla lepszej precyzji.

**Ograniczony output** - Wymuszanie konkretnego formatu lub struktury.

**Wysoka gotowość (High Eagerness)** - Wzorzec GPT-5 dla gruntownego rozumowania.

**Niska gotowość (Low Eagerness)** - Wzorzec GPT-5 dla szybkich odpowiedzi.

**Wielokrotna rozmowa** - Utrzymywanie kontekstu w trakcie wymiany.

**Prompt oparty na roli** - Ustawianie persony modelu przez wiadomości systemowe.

**Auto-ocena (Self-Reflection)** - Model ocenia i poprawia swoje odpowiedzi.

**Strukturalna analiza** - Stały schemat oceny.

**Wzorzec wykonania zadania** - Plan → Wykonaj → Podsumuj.

## RAG (Retrieval-Augmented Generation) - [Moduł 03](../03-rag/README.md)

**Proces przetwarzania dokumentów** - Wczytaj → podziel → zamieść → przechowaj.

**Pamięciowe przechowywanie embeddingów** - Nieutrwalające przechowywanie do testów.

**RAG** - Łączy wyszukiwanie z generowaniem, aby ugruntować odpowiedzi.

**Wskaźnik podobieństwa** - Miara (0-1) podobieństwa semantycznego.

**Źródło odniesienia** - Metadane o pobranej treści.

## Agenci i narzędzia - [Moduł 04](../04-tools/README.md)

**Adnotacja @Tool** - Oznacza metody Java jako narzędzia wywoływane przez AI.

**Wzorzec ReAct** - Rozumuj → Działaj → Obserwuj → Powtarzaj.

**Zarządzanie sesjami** - Oddzielne konteksty dla różnych użytkowników.

**Narzędzie (Tool)** - Funkcja, którą agent AI może wywołać.

**Opis narzędzia** - Dokumentacja celu i parametrów narzędzia.

## Moduł agentowy - [Moduł 05](../05-mcp/README.md)

**Adnotacja @Agent** - Oznacza interfejsy jako agentów AI z deklaratywną definicją zachowania.

**Listener agenta** - Hak do monitorowania wykonania agenta przez `beforeAgentInvocation()` i `afterAgentInvocation()`.

**Zakres agentowy** - Wspólna pamięć, gdzie agenci przechowują wyniki za pomocą `outputKey` dla agentów połączonych dalej.

**AgenticServices** - Fabryka do tworzenia agentów używając `agentBuilder()` i `supervisorBuilder()`.

**Warunkowy workflow** - Kierowanie na różne specjalistyczne agenty w oparciu o warunki.

**Człowiek w pętli** - Wzorzec workflow dodający punkty kontrolne dla zatwierdzeń lub przeglądu treści przez człowieka.

**langchain4j-agentic** - Zależność Maven do deklaratywnego budowania agentów (eksperymentalne).

**Pętla workflow** - Iteracyjne wykonywanie agenta aż do spełnienia warunku (np. ocena jakości ≥ 0.8).

**outputKey** - Parametr adnotacji agenta określający gdzie zapisywane są wyniki w zakresie agentowym.

**Równoległy workflow** - Uruchamianie wielu agentów jednocześnie do niezależnych zadań.

**Strategia odpowiedzi** - Jak supervisor formułuje ostateczną odpowiedź: LAST, SUMMARY lub SCORED.

**Sekwencyjny workflow** - Wykonanie agentów po kolei, gdzie wyjście płynie do następnego kroku.

**Wzorzec agenta nadzorcy** - Zaawansowany wzorzec, gdzie supervisor LLM dynamicznie decyduje, których pod-agentów wywołać.

## Protokół kontekstu modelu (MCP) - [Moduł 05](../05-mcp/README.md)

**langchain4j-mcp** - Zależność Maven do integracji MCP w LangChain4j.

**MCP** - Model Context Protocol: standard łączenia aplikacji AI z zewnętrznymi narzędziami. Raz zbuduj, używaj wszędzie.

**MCP Client** - Aplikacja łącząca się z serwerami MCP w celu odkrywania i używania narzędzi.

**MCP Server** - Usługa udostępniająca narzędzia przez MCP z jasnym opisem i schematami parametrów.

**McpToolProvider** - Komponent LangChain4j opakowujący narzędzia MCP do użycia w usługach i agentach AI.

**McpTransport** - Interfejs komunikacji MCP. Implementacje to Stdio i HTTP.

**Transport Stdio** - Lokalny transport procesowy przez stdin/stdout. Przydatny dla dostępu do systemu plików lub narzędzi wiersza poleceń.

**StdioMcpTransport** - Implementacja LangChain4j uruchamiająca serwer MCP jako podproces.

**Odkrywanie narzędzi** - Klient zapytuje serwer o dostępne narzędzia wraz z opisami i schematami.

## Usługi Azure - [Moduł 01](../01-introduction/README.md)

**Azure AI Search** - Chmurowe wyszukiwanie z możliwością wektorową. [Moduł 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Narzędzie do wdrażania zasobów Azure.

**Azure OpenAI** - Usługa AI przedsiębiorstwa Microsoft.

**Bicep** - Język infrastruktury jako kod dla Azure. [Przewodnik po infrastrukturze](../01-introduction/infra/README.md)

**Nazwa wdrożenia** - Nazwa dla wdrożenia modelu w Azure.

**GPT-5** - Najnowszy model OpenAI z kontrolą rozumowania. [Moduł 02](../02-prompt-engineering/README.md)

## Testowanie i rozwój - [Przewodnik testowania](TESTING.md)

**Dev Container** - Konteneryzowane środowisko deweloperskie. [Konfiguracja](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Darmowe pole do eksperymentów z modelami AI. [Moduł 00](../00-quick-start/README.md)

**Testy w pamięci** - Testowanie ze składowaniem w pamięci.

**Testy integracyjne** - Testowanie z rzeczywistą infrastrukturą.

**Maven** - Narzędzie do automatyzacji budowy w Javie.

**Mockito** - Framework do tworzenia obiektów zastępczych w Javie.

**Spring Boot** - Framework aplikacji Java. [Moduł 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Zastrzeżenie**:  
Ten dokument został przetłumaczony za pomocą usługi tłumaczenia AI [Co-op Translator](https://github.com/Azure/co-op-translator). Choć dbamy o dokładność, prosimy mieć na uwadze, że automatyczne tłumaczenia mogą zawierać błędy lub nieścisłości. Oryginalny dokument w języku źródłowym należy uważać za źródło autorytatywne. W przypadku informacji krytycznych zalecane jest skorzystanie z profesjonalnego tłumaczenia wykonanego przez człowieka. Nie ponosimy odpowiedzialności za wszelkie nieporozumienia lub błędne interpretacje wynikające z użycia niniejszego tłumaczenia.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->