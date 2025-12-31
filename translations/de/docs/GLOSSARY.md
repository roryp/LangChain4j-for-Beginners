<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T19:47:38+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "de"
}
-->
# LangChain4j Glossar

## Inhaltsverzeichnis

- [Kernkonzepte](../../../docs)
- [LangChain4j-Komponenten](../../../docs)
- [KI/ML-Konzepte](../../../docs)
- [Prompt-Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenten und Tools](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure-Dienste](../../../docs)
- [Testen und Entwicklung](../../../docs)

Kurze Referenz für Begriffe und Konzepte, die im Kurs verwendet werden.

## Kernkonzepte

**AI Agent** - System, das KI verwendet, um eigenständig zu schlussfolgern und zu handeln. [Modul 04](../04-tools/README.md)

**Chain** - Folge von Operationen, bei der die Ausgabe in den nächsten Schritt einfließt.

**Chunking** - Aufteilen von Dokumenten in kleinere Stücke. Typisch: 300-500 Tokens mit Überlappung. [Modul 03](../03-rag/README.md)

**Context Window** - Maximale Anzahl an Tokens, die ein Modell verarbeiten kann. GPT-5: 400K Tokens.

**Embeddings** - Numerische Vektoren, die die Bedeutung von Text repräsentieren. [Modul 03](../03-rag/README.md)

**Function Calling** - Das Modell erzeugt strukturierte Anfragen zum Aufruf externer Funktionen. [Modul 04](../04-tools/README.md)

**Hallucination** - Wenn Modelle inkorrekte, aber plausibel wirkende Informationen erzeugen.

**Prompt** - Texteingabe an ein Sprachmodell. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Suche nach Bedeutung unter Verwendung von Embeddings, nicht nach Schlüsselwörtern. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: kein Gedächtnis. Stateful: behält Gesprächshistorie bei. [Modul 01](../01-introduction/README.md)

**Tokens** - Grundlegende Texteinheiten, die Modelle verarbeiten. Beeinflussen Kosten und Limits. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Sequenzielle Ausführung von Tools, bei der die Ausgabe den nächsten Aufruf informiert. [Modul 04](../04-tools/README.md)

## LangChain4j-Komponenten

**AiServices** - Erstellt typsichere AI-Service-Schnittstellen.

**OpenAiOfficialChatModel** - Vereinheitlichter Client für OpenAI- und Azure OpenAI-Modelle.

**OpenAiOfficialEmbeddingModel** - Erstellt Embeddings mithilfe des OpenAI Official Clients (unterstützt sowohl OpenAI als auch Azure OpenAI).

**ChatModel** - Kernschnittstelle für Sprachmodelle.

**ChatMemory** - Verwaltet die Gesprächshistorie.

**ContentRetriever** - Findet relevante Dokumentabschnitte für RAG.

**DocumentSplitter** - Teilt Dokumente in Abschnitte.

**EmbeddingModel** - Wandelt Text in numerische Vektoren um.

**EmbeddingStore** - Speichert und ruft Embeddings ab.

**MessageWindowChatMemory** - Verwaltet ein gleitendes Fenster der letzten Nachrichten.

**PromptTemplate** - Erstellt wiederverwendbare Prompts mit `{{variable}}` Platzhaltern.

**TextSegment** - Textabschnitt mit Metadaten. Wird in RAG verwendet.

**ToolExecutionRequest** - Repräsentiert eine Anfrage zur Tool-Ausführung.

**UserMessage / AiMessage / SystemMessage** - Nachrichtentypen in Konversationen.

## KI/ML-Konzepte

**Few-Shot Learning** - Bereitstellung von Beispielen in Prompts. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - KI-Modelle, die auf umfangreichen Textdaten trainiert sind.

**Reasoning Effort** - GPT-5-Parameter, der die Tiefe des Denkens steuert. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Steuert die Zufälligkeit der Ausgabe. Niedrig=deterministisch, hoch=kreativ.

**Vector Database** - Spezialisierte Datenbank für Embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Ausführen von Aufgaben ohne Beispiele. [Modul 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Schritt-für-Schritt-Denkweise für höhere Genauigkeit.

**Constrained Output** - Erzwingen eines spezifischen Formats oder einer bestimmten Struktur.

**High Eagerness** - GPT-5-Muster für gründliches Denken.

**Low Eagerness** - GPT-5-Muster für schnelle Antworten.

**Multi-Turn Conversation** - Kontext über mehrere Austausche hinweg behalten.

**Role-Based Prompting** - Festlegen einer Modellpersona über Systemnachrichten.

**Self-Reflection** - Das Modell bewertet und verbessert seine Ausgabe.

**Structured Analysis** - Fester Bewertungsrahmen.

**Task Execution Pattern** - Plan → Ausführen → Zusammenfassen.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Laden → aufteilen → einbetten → speichern.

**In-Memory Embedding Store** - Nicht-persistenter Speicher für Tests.

**RAG** - Kombiniert Retrieval mit Generierung, um Antworten zu untermauern.

**Similarity Score** - Maß (0-1) der semantischen Ähnlichkeit.

**Source Reference** - Metadaten über abgerufene Inhalte.

## Agenten und Tools - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Markiert Java-Methoden als von KI aufrufbare Tools.

**ReAct Pattern** - Überlegen → Handeln → Beobachten → Wiederholen.

**Session Management** - Getrennte Kontexte für verschiedene Benutzer.

**Tool** - Funktion, die ein KI-Agent aufrufen kann.

**Tool Description** - Dokumentation des Zwecks und der Parameter eines Tools.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**MCP** - Standard zur Verbindung von KI-Anwendungen mit externen Tools.

**MCP Client** - Anwendung, die sich mit MCP-Servern verbindet.

**MCP Server** - Dienst, der Tools über MCP bereitstellt.

**Stdio Transport** - Server als Subprozess über stdin/stdout.

**Tool Discovery** - Client fragt den Server nach verfügbaren Tools.

## Azure-Dienste - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Cloud-Suche mit Vektor-Funktionen. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Stellt Azure-Ressourcen bereit.

**Azure OpenAI** - Microsofts Enterprise-KI-Dienst.

**Bicep** - Azure Infrastructure-as-Code-Sprache. [Infrastrukturleitfaden](../01-introduction/infra/README.md)

**Deployment Name** - Name für die Bereitstellung eines Modells in Azure.

**GPT-5** - Aktuellstes OpenAI-Modell mit Steuerung des Reasoning. [Modul 02](../02-prompt-engineering/README.md)

## Testen und Entwicklung - [Testanleitung](TESTING.md)

**Dev Container** - Containerisierte Entwicklungsumgebung. [Konfiguration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Kostenlose KI-Modell-Sandbox. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Tests mit In-Memory-Speicher.

**Integration Testing** - Tests mit realer Infrastruktur.

**Maven** - Build-Automatisierungstool für Java.

**Mockito** - Java Mocking-Framework.

**Spring Boot** - Java-Anwendungsframework. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Haftungsausschluss**:
Dieses Dokument wurde mithilfe des KI-Übersetzungsdienstes [Co-op Translator](https://github.com/Azure/co-op-translator) übersetzt. Obwohl wir uns um Genauigkeit bemühen, beachten Sie bitte, dass automatisierte Übersetzungen Fehler oder Ungenauigkeiten enthalten können. Das Originaldokument in seiner Ausgangssprache ist als maßgebliche Quelle zu betrachten. Bei kritischen Informationen wird eine professionelle menschliche Übersetzung empfohlen. Wir übernehmen keine Haftung für Missverständnisse oder Fehlinterpretationen, die aus der Nutzung dieser Übersetzung entstehen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->