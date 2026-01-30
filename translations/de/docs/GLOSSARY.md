# LangChain4j Glossar

## Inhaltsverzeichnis

- [Kernkonzepte](../../../docs)
- [LangChain4j Komponenten](../../../docs)
- [AI/ML Konzepte](../../../docs)
- [Guardrails](../../../docs)
- [Prompt Engineering](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenten und Werkzeuge](../../../docs)
- [Agentisches Modul](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Azure Dienste](../../../docs)
- [Testen und Entwicklung](../../../docs)

Schnellreferenz für Begriffe und Konzepte, die im Kurs verwendet werden.

## Kernkonzepte

**AI-Agent** – System, das KI nutzt, um autonom zu denken und zu handeln. [Modul 04](../04-tools/README.md)

**Chain** – Abfolge von Operationen, bei der die Ausgabe in den nächsten Schritt eingeht.

**Chunking** – Aufteilen von Dokumenten in kleinere Stücke. Typisch: 300-500 Tokens mit Überlappung. [Modul 03](../03-rag/README.md)

**Kontextfenster** – Maximale Tokens, die ein Modell verarbeiten kann. GPT-5: 400K Tokens.

**Embeddings** – Numerische Vektoren, die die Bedeutung von Text repräsentieren. [Modul 03](../03-rag/README.md)

**Funktionsaufruf** – Modell generiert strukturierte Anfragen zum Aufrufen externer Funktionen. [Modul 04](../04-tools/README.md)

**Halluzination** – Wenn Modelle falsche, aber plausible Informationen erzeugen.

**Prompt** – Texteingabe an ein Sprachmodell. [Modul 02](../02-prompt-engineering/README.md)

**Semantische Suche** – Suche nach Bedeutung mithilfe von Embeddings, nicht Schlagwörtern. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** – Stateless: kein Speicher. Stateful: hält Gesprächsverlauf fest. [Modul 01](../01-introduction/README.md)

**Tokens** – Grundlegende Texteinheiten, die Modelle verarbeiten. Beeinflussen Kosten und Limits. [Modul 01](../01-introduction/README.md)

**Tool Chaining** – Sequenzielle Werkzeuga Ausführung, wobei die Ausgabe den nächsten Aufruf informiert. [Modul 04](../04-tools/README.md)

## LangChain4j Komponenten

**AiServices** – Erzeugt typsichere KI-Service-Schnittstellen.

**OpenAiOfficialChatModel** – Einheitlicher Client für OpenAI- und Azure OpenAI-Modelle.

**OpenAiOfficialEmbeddingModel** – Erstellt Embeddings mit OpenAI Official Client (unterstützt OpenAI und Azure OpenAI).

**ChatModel** – Kernschnittstelle für Sprachmodelle.

**ChatMemory** – Speichert den Gesprächsverlauf.

**ContentRetriever** – Findet relevante Dokumentstücke für RAG.

**DocumentSplitter** – Teilt Dokumente in Stücke.

**EmbeddingModel** – Wandelt Text in numerische Vektoren um.

**EmbeddingStore** – Speichert und ruft Embeddings ab.

**MessageWindowChatMemory** – Verwaltet gleitendes Fenster der letzten Nachrichten.

**PromptTemplate** – Erstellt wiederverwendbare Prompts mit `{{variable}}` Platzhaltern.

**TextSegment** – Textstück mit Metadaten. Wird in RAG verwendet.

**ToolExecutionRequest** – Repräsentiert Werkzeugausführungsanfrage.

**UserMessage / AiMessage / SystemMessage** – Gesprächsnachrichttypen.

## AI/ML Konzepte

**Few-Shot Learning** – Beispiele in Prompts bereitstellen. [Modul 02](../02-prompt-engineering/README.md)

**Großes Sprachmodell (LLM)** – KI-Modelle, trainiert an umfangreichen Textdaten.

**Reasoning Effort** – GPT-5 Parameter zur Steuerung der Denktiefe. [Modul 02](../02-prompt-engineering/README.md)

**Temperatur** – Steuert Zufälligkeit der Ausgabe. Niedrig=deterministisch, hoch=kreativ.

**Vektor-Datenbank** – Spezialisierte Datenbank für Embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** – Durchführung von Aufgaben ohne Beispiele. [Modul 02](../02-prompt-engineering/README.md)

## Guardrails - [Modul 00](../00-quick-start/README.md)

**Defense in Depth** – Mehrschichtiger Sicherheitsansatz, der auf Anwendungsebene Guardrails mit Safety-Filtern der Anbieter kombiniert.

**Hard Block** – Anbieter wirft HTTP 400 Fehler bei schweren Inhaltsverstößen.

**InputGuardrail** – LangChain4j-Schnittstelle zur Validierung von Benutzereingaben bevor sie das LLM erreichen. Spart Kosten und Latenz durch frühes Blockieren schädlicher Prompts.

**InputGuardrailResult** – Rückgabetyp für Guardrail-Validierung: `success()` oder `fatal("reason")`.

**OutputGuardrail** – Schnittstelle zur Validierung von KI-Antworten vor der Rückgabe an Nutzer.

**Provider Safety Filters** – Eingebaute Inhaltsfilter von KI-Anbietern (z. B. GitHub Models), die Verstöße auf API-Ebene abfangen.

**Soft Refusal** – Modell lehnt höflich ab zu antworten, ohne Fehler zu werfen.

## Prompt Engineering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** – Schritt-für-Schritt-Denken für bessere Genauigkeit.

**Eingeschränkte Ausgabe** – Erzwingung eines bestimmten Formats oder einer Struktur.

**Hohe Eiferigkeit** – GPT-5 Muster für gründliches Denken.

**Niedrige Eiferigkeit** – GPT-5 Muster für schnelle Antworten.

**Mehrfach-Durchlauf-Konversation** – Kontext über mehrere Austausch hinweg beibehalten.

**Rollenbasiertes Prompting** – Setzt Modellpersönlichkeit via Systemnachrichten.

**Selbstreflexion** – Modell bewertet und verbessert seine Ausgabe.

**Strukturierte Analyse** – Festes Bewertungs-Framework.

**Aufgaben-Ausführungsmuster** – Planen → Ausführen → Zusammenfassen.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Dokumentenbearbeitungspipeline** – Laden → Stückeln → Einbetten → Speichern.

**In-Memory Embedding Store** – Nicht-persistenter Speicher für Tests.

**RAG** – Kombiniert Retrieval mit Generierung zur fundierten Antwort.

**Ähnlichkeitswert** – Maß (0-1) für semantische Ähnlichkeit.

**Quellenangabe** – Metadaten über abgerufene Inhalte.

## Agenten und Werkzeuge - [Modul 04](../04-tools/README.md)

**@Tool Annotation** – Kennzeichnet Java-Methoden als KI-aufrufbare Werkzeuge.

**ReAct Muster** – Überlegen → Handeln → Beobachten → Wiederholen.

**Sitzungsverwaltung** – Getrennte Kontexte für verschiedene Benutzer.

**Tool** – Funktion, die ein KI-Agent aufrufen kann.

**Tool-Beschreibung** – Dokumentation des Werkzeugzwecks und der Parameter.

## Agentisches Modul - [Modul 05](../05-mcp/README.md)

**@Agent Annotation** – Kennzeichnet Schnittstellen als KI-Agenten mit deklarativer Verhaltensdefinition.

**Agent Listener** – Hook zur Überwachung der Agentenausführung via `beforeAgentInvocation()` und `afterAgentInvocation()`.

**Agentic Scope** – Gemeinsamer Speicher, in dem Agenten Ausgaben mit `outputKey` für nachfolgende Agenten ablegen.

**AgenticServices** – Fabrik zum Erstellen von Agenten mit `agentBuilder()` und `supervisorBuilder()`.

**Bedingter Workflow** – Routing basierend auf Bedingungen zu verschiedenen Spezialisten-Agenten.

**Human-in-the-Loop** – Workflow-Muster mit menschlichen Kontrollpunkten für Freigabe oder Inhaltsprüfung.

**langchain4j-agentic** – Maven-Abhängigkeit für deklaratives Agenten-Bauen (experimentell).

**Loop Workflow** – Agentenausführung iterieren, bis Bedingung erfüllt ist (z. B. Qualitätsbewertung ≥ 0.8).

**outputKey** – Agenten-Annotation Parameter, der angibt, wo Ergebnisse im Agentic Scope gespeichert werden.

**Paralleler Workflow** – Mehrere Agenten gleichzeitig für unabhängige Aufgaben ausführen.

**Response Strategy** – Wie der Supervisor die endgültige Antwort formuliert: LAST, SUMMARY oder SCORED.

**Sequenzieller Workflow** – Agenten nacheinander ausführen, wobei die Ausgabe in den nächsten Schritt fließt.

**Supervisor Agent Pattern** – Fortgeschrittenes agentisches Muster, bei dem ein Supervisor-LLM dynamisch entscheidet, welche Sub-Agenten aufzurufen sind.

## Model Context Protocol (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** – Maven-Abhängigkeit für MCP-Integration in LangChain4j.

**MCP** – Model Context Protocol: Standard für die Verbindung von KI-Apps zu externen Werkzeugen. Einmal bauen, überall nutzen.

**MCP Client** – Anwendung, die sich mit MCP-Servern verbindet, um Werkzeuge zu entdecken und zu verwenden.

**MCP Server** – Dienst, der Werkzeuge via MCP mit klaren Beschreibungen und Parameterschemata bereitstellt.

**McpToolProvider** – LangChain4j-Komponente, die MCP-Werkzeuge für KI-Services und Agenten kapselt.

**McpTransport** – Schnittstelle für MCP-Kommunikation. Implementierungen umfassen Stdio und HTTP.

**Stdio Transport** – Lokaler Prozess-Transport via stdin/stdout. Nützlich für Dateisystemzugriff oder Kommandozeilenwerkzeuge.

**StdioMcpTransport** – LangChain4j-Implementierung, die MCP-Server als Subprozess startet.

**Werkzeugentdeckung** – Client fragt Server nach verfügbaren Werkzeugen mit Beschreibungen und Schemata ab.

## Azure Dienste - [Modul 01](../01-introduction/README.md)

**Azure AI Search** – Cloud-Suche mit Vektor-Funktionalität. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** – Stellt Azure-Ressourcen bereit.

**Azure OpenAI** – Microsofts Enterprise-KI-Dienst.

**Bicep** – Azure Infrastruktur-als-Code-Sprache. [Infrastrukturleitfaden](../01-introduction/infra/README.md)

**Deployment-Name** – Name für Modellbereitstellung in Azure.

**GPT-5** – Neuestes OpenAI-Modell mit Steuerung des Denkens. [Modul 02](../02-prompt-engineering/README.md)

## Testen und Entwicklung - [Testanleitung](TESTING.md)

**Dev Container** – Containerisierte Entwicklungsumgebung. [Konfiguration](../../../.devcontainer/devcontainer.json)

**GitHub Modelle** – Kostenloser KI-Modell-Spielplatz. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** – Testen mit In-Memory-Speicher.

**Integration Testing** – Testen mit echter Infrastruktur.

**Maven** – Java Build-Automatisierungswerkzeug.

**Mockito** – Java-Mocking-Framework.

**Spring Boot** – Java-Anwendungsframework. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Haftungsausschluss**:  
Dieses Dokument wurde mit dem KI-Übersetzungsdienst [Co-op Translator](https://github.com/Azure/co-op-translator) übersetzt. Obwohl wir auf Genauigkeit achten, kann es bei automatischen Übersetzungen zu Fehlern oder Ungenauigkeiten kommen. Das Originaldokument in seiner Originalsprache gilt als maßgebliche Quelle. Für wichtige Informationen wird eine professionelle menschliche Übersetzung empfohlen. Wir übernehmen keine Haftung für Missverständnisse oder Fehlinterpretationen, die durch die Nutzung dieser Übersetzung entstehen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->