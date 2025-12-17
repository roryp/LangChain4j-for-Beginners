<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T19:50:36+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "de"
}
-->
# LangChain4j Glossar

## Inhaltsverzeichnis

- [Kernkonzepte](../../../docs)
- [LangChain4j Komponenten](../../../docs)
- [KI/ML Konzepte](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenten und Werkzeuge](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Dienste](../../../docs)
- [Testen und Entwicklung](../../../docs)

Schnellreferenz für Begriffe und Konzepte, die im gesamten Kurs verwendet werden.

## Kernkonzepte

**KI-Agent** – System, das KI nutzt, um autonom zu denken und zu handeln. [Modul 04](../04-tools/README.md)

**Kette** – Abfolge von Operationen, bei der die Ausgabe in den nächsten Schritt fließt.

**Chunking** – Aufteilen von Dokumenten in kleinere Stücke. Typisch: 300-500 Tokens mit Überlappung. [Modul 03](../03-rag/README.md)

**Kontextfenster** – Maximale Anzahl Tokens, die ein Modell verarbeiten kann. GPT-5: 400K Tokens.

**Embeddings** – Numerische Vektoren, die die Bedeutung von Text repräsentieren. [Modul 03](../03-rag/README.md)

**Funktionsaufruf** – Modell generiert strukturierte Anfragen zum Aufrufen externer Funktionen. [Modul 04](../04-tools/README.md)

**Halluzination** – Wenn Modelle falsche, aber plausible Informationen erzeugen.

**Prompt** – Texteingabe an ein Sprachmodell. [Modul 02](../02-prompt-engineering/README.md)

**Semantische Suche** – Suche nach Bedeutung mittels Embeddings, nicht Schlüsselwörtern. [Modul 03](../03-rag/README.md)

**Zustandsbehaftet vs. Zustandslos** – Zustandslos: kein Gedächtnis. Zustandsbehaftet: pflegt Gesprächshistorie. [Modul 01](../01-introduction/README.md)

**Tokens** – Grundlegende Textelemente, die Modelle verarbeiten. Beeinflussen Kosten und Limits. [Modul 01](../01-introduction/README.md)

**Werkzeugverkettung** – Sequenzielle Ausführung von Werkzeugen, bei der Ausgabe den nächsten Aufruf informiert. [Modul 04](../04-tools/README.md)

## LangChain4j Komponenten

**AiServices** – Erstellt typsichere KI-Service-Schnittstellen.

**OpenAiOfficialChatModel** – Einheitlicher Client für OpenAI und Azure OpenAI Modelle.

**OpenAiOfficialEmbeddingModel** – Erstellt Embeddings mit OpenAI Official Client (unterstützt OpenAI und Azure OpenAI).

**ChatModel** – Kernschnittstelle für Sprachmodelle.

**ChatMemory** – Pflegt Gesprächshistorie.

**ContentRetriever** – Findet relevante Dokumentenabschnitte für RAG.

**DocumentSplitter** – Teilt Dokumente in Abschnitte.

**EmbeddingModel** – Wandelt Text in numerische Vektoren um.

**EmbeddingStore** – Speichert und ruft Embeddings ab.

**MessageWindowChatMemory** – Pflegt ein gleitendes Fenster der letzten Nachrichten.

**PromptTemplate** – Erstellt wiederverwendbare Prompts mit `{{variable}}` Platzhaltern.

**TextSegment** – Textabschnitt mit Metadaten. Verwendet in RAG.

**ToolExecutionRequest** – Repräsentiert Werkzeugausführungsanfrage.

**UserMessage / AiMessage / SystemMessage** – Nachrichtentypen im Gespräch.

## KI/ML Konzepte

**Few-Shot Learning** – Beispiele in Prompts bereitstellen. [Modul 02](../02-prompt-engineering/README.md)

**Großes Sprachmodell (LLM)** – KI-Modelle, trainiert auf umfangreichen Textdaten.

**Reasoning Effort** – GPT-5 Parameter zur Steuerung der Denktiefe. [Modul 02](../02-prompt-engineering/README.md)

**Temperatur** – Steuert Zufälligkeit der Ausgabe. Niedrig=deterministisch, hoch=kreativ.

**Vektor-Datenbank** – Spezialisierte Datenbank für Embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** – Aufgaben ohne Beispiele ausführen. [Modul 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** – Schrittweises Denken für bessere Genauigkeit.

**Eingeschränkte Ausgabe** – Erzwingt bestimmtes Format oder Struktur.

**Hohe Eifer** – GPT-5 Muster für gründliches Denken.

**Niedriger Eifer** – GPT-5 Muster für schnelle Antworten.

**Mehrfach-Runden-Gespräch** – Kontext über mehrere Austausche erhalten.

**Rollenbasiertes Prompting** – Modellpersona über Systemnachrichten festlegen.

**Selbstreflexion** – Modell bewertet und verbessert seine Ausgabe.

**Strukturierte Analyse** – Fester Bewertungsrahmen.

**Aufgabenausführungsmuster** – Planen → Ausführen → Zusammenfassen.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Dokumentenverarbeitungspipeline** – Laden → aufteilen → einbetten → speichern.

**In-Memory Embedding Store** – Nicht-persistenter Speicher für Tests.

**RAG** – Kombiniert Abruf mit Generierung zur fundierten Antwort.

**Ähnlichkeitsscore** – Maß (0-1) für semantische Ähnlichkeit.

**Quellenreferenz** – Metadaten über abgerufene Inhalte.

## Agenten und Werkzeuge - [Modul 04](../04-tools/README.md)

**@Tool Annotation** – Markiert Java-Methoden als KI-aufrufbare Werkzeuge.

**ReAct Muster** – Denken → Handeln → Beobachten → Wiederholen.

**Sitzungsverwaltung** – Getrennte Kontexte für verschiedene Nutzer.

**Werkzeug** – Funktion, die ein KI-Agent aufrufen kann.

**Werkzeugbeschreibung** – Dokumentation von Zweck und Parametern.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**Docker Transport** – MCP-Server im Docker-Container.

**MCP** – Standard zur Verbindung von KI-Apps mit externen Werkzeugen.

**MCP Client** – Anwendung, die sich mit MCP-Servern verbindet.

**MCP Server** – Dienst, der Werkzeuge über MCP bereitstellt.

**Server-Sent Events (SSE)** – Server-zu-Client Streaming über HTTP.

**Stdio Transport** – Server als Unterprozess via stdin/stdout.

**Streamable HTTP Transport** – HTTP mit SSE für Echtzeitkommunikation.

**Werkzeugerkennung** – Client fragt Server nach verfügbaren Werkzeugen ab.

## Azure Dienste - [Modul 01](../01-introduction/README.md)

**Azure AI Search** – Cloud-Suche mit Vektor-Funktionalität. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** – Stellt Azure-Ressourcen bereit.

**Azure OpenAI** – Microsofts Enterprise-KI-Dienst.

**Bicep** – Azure Infrastructure-as-Code Sprache. [Infrastrukturleitfaden](../01-introduction/infra/README.md)

**Bereitstellungsname** – Name für Modellbereitstellung in Azure.

**GPT-5** – Neuestes OpenAI-Modell mit Steuerung der Denkweise. [Modul 02](../02-prompt-engineering/README.md)

## Testen und Entwicklung - [Testanleitung](TESTING.md)

**Dev Container** – Containerisierte Entwicklungsumgebung. [Konfiguration](../../../.devcontainer/devcontainer.json)

**GitHub Modelle** – Kostenloser KI-Modell-Spielplatz. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** – Testen mit In-Memory-Speicher.

**Integrationstests** – Testen mit echter Infrastruktur.

**Maven** – Java Build-Automatisierungstool.

**Mockito** – Java Mocking-Framework.

**Spring Boot** – Java-Anwendungsframework. [Modul 01](../01-introduction/README.md)

**Testcontainers** – Docker-Container in Tests.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Haftungsausschluss**:  
Dieses Dokument wurde mit dem KI-Übersetzungsdienst [Co-op Translator](https://github.com/Azure/co-op-translator) übersetzt. Obwohl wir uns um Genauigkeit bemühen, beachten Sie bitte, dass automatisierte Übersetzungen Fehler oder Ungenauigkeiten enthalten können. Das Originaldokument in seiner Ursprungssprache ist als maßgebliche Quelle zu betrachten. Für wichtige Informationen wird eine professionelle menschliche Übersetzung empfohlen. Wir übernehmen keine Haftung für Missverständnisse oder Fehlinterpretationen, die aus der Nutzung dieser Übersetzung entstehen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->