<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T14:29:29+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "de"
}
-->
# Modul 00: Schnellstart

## Inhaltsverzeichnis

- [Einführung](../../../00-quick-start)
- [Was ist LangChain4j?](../../../00-quick-start)
- [LangChain4j Abhängigkeiten](../../../00-quick-start)
- [Voraussetzungen](../../../00-quick-start)
- [Einrichtung](../../../00-quick-start)
  - [1. Holen Sie sich Ihr GitHub-Token](../../../00-quick-start)
  - [2. Setzen Sie Ihr Token](../../../00-quick-start)
- [Beispiele ausführen](../../../00-quick-start)
  - [1. Basis-Chat](../../../00-quick-start)
  - [2. Prompt-Muster](../../../00-quick-start)
  - [3. Funktionsaufruf](../../../00-quick-start)
  - [4. Dokument Q&A (RAG)](../../../00-quick-start)
- [Was jedes Beispiel zeigt](../../../00-quick-start)
- [Nächste Schritte](../../../00-quick-start)
- [Fehlerbehebung](../../../00-quick-start)

## Einführung

Dieser Schnellstart soll Ihnen helfen, LangChain4j so schnell wie möglich zum Laufen zu bringen. Er behandelt die absoluten Grundlagen zum Erstellen von KI-Anwendungen mit LangChain4j und GitHub Models. In den nächsten Modulen verwenden Sie Azure OpenAI mit LangChain4j, um fortgeschrittenere Anwendungen zu erstellen.

## Was ist LangChain4j?

LangChain4j ist eine Java-Bibliothek, die das Erstellen KI-gestützter Anwendungen vereinfacht. Anstatt sich mit HTTP-Clients und JSON-Parsing zu beschäftigen, arbeiten Sie mit sauberen Java-APIs.

Die „Chain“ in LangChain bezieht sich auf das Verketten mehrerer Komponenten – Sie können eine Eingabeaufforderung an ein Modell an einen Parser ketten oder mehrere KI-Aufrufe verketten, bei denen eine Ausgabe in die nächste Eingabe fließt. Dieser Schnellstart konzentriert sich auf die Grundlagen, bevor komplexere Ketten erkundet werden.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1e961a13c73d45cfa305fd03d81bd11e5d34d0e36ed28a44d.de.png" alt="LangChain4j Chaining Concept" width="800"/>

*Verkettung von Komponenten in LangChain4j – Bausteine verbinden sich, um leistungsstarke KI-Workflows zu erstellen*

Wir verwenden drei Kernkomponenten:

**ChatLanguageModel** – Die Schnittstelle für KI-Modell-Interaktionen. Rufen Sie `model.chat("prompt")` auf und erhalten Sie eine Antwort als String. Wir verwenden `OpenAiOfficialChatModel`, das mit OpenAI-kompatiblen Endpunkten wie GitHub Models funktioniert.

**AiServices** – Erstellt typsichere KI-Service-Schnittstellen. Definieren Sie Methoden, annotieren Sie sie mit `@Tool` und LangChain4j übernimmt die Orchestrierung. Die KI ruft Ihre Java-Methoden automatisch auf, wenn nötig.

**MessageWindowChatMemory** – Pflegt den Gesprächsverlauf. Ohne dies ist jede Anfrage unabhängig. Mit ihm erinnert sich die KI an vorherige Nachrichten und behält den Kontext über mehrere Runden.

<img src="../../../translated_images/architecture.eedc993a1c57683951f20244f652fc7a9853f571eea835bc2b2828cf0dbf62d0.de.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j-Architektur – Kernkomponenten arbeiten zusammen, um Ihre KI-Anwendungen zu betreiben*

## LangChain4j Abhängigkeiten

Dieser Schnellstart verwendet zwei Maven-Abhängigkeiten in der [`pom.xml`](../../../00-quick-start/pom.xml):

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

Das Modul `langchain4j-open-ai-official` stellt die Klasse `OpenAiOfficialChatModel` bereit, die sich mit OpenAI-kompatiblen APIs verbindet. GitHub Models verwendet dasselbe API-Format, daher ist kein spezieller Adapter erforderlich – weisen Sie einfach die Basis-URL auf `https://models.github.ai/inference` zu.

## Voraussetzungen

**Verwenden Sie den Dev Container?** Java und Maven sind bereits installiert. Sie benötigen nur ein GitHub Personal Access Token.

**Lokale Entwicklung:**
- Java 21+, Maven 3.9+
- GitHub Personal Access Token (Anleitung unten)

> **Hinweis:** Dieses Modul verwendet `gpt-4.1-nano` von GitHub Models. Ändern Sie den Modellnamen im Code nicht – er ist so konfiguriert, dass er mit den verfügbaren GitHub-Modellen funktioniert.

## Einrichtung

### 1. Holen Sie sich Ihr GitHub-Token

1. Gehen Sie zu [GitHub Einstellungen → Personal Access Tokens](https://github.com/settings/personal-access-tokens)
2. Klicken Sie auf „Generate new token“
3. Geben Sie einen beschreibenden Namen ein (z. B. „LangChain4j Demo“)
4. Legen Sie die Ablaufzeit fest (7 Tage empfohlen)
5. Unter „Account permissions“ suchen Sie „Models“ und setzen es auf „Read-only“
6. Klicken Sie auf „Generate token“
7. Kopieren und speichern Sie Ihr Token – Sie sehen es nicht wieder

### 2. Setzen Sie Ihr Token

**Option 1: Verwendung von VS Code (empfohlen)**

Wenn Sie VS Code verwenden, fügen Sie Ihr Token in die `.env`-Datei im Projektstammverzeichnis ein:

Falls die `.env`-Datei nicht existiert, kopieren Sie `.env.example` nach `.env` oder erstellen Sie eine neue `.env`-Datei im Projektstamm.

**Beispiel `.env`-Datei:**
```bash
# In /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Dann können Sie einfach mit der rechten Maustaste auf eine beliebige Demo-Datei (z. B. `BasicChatDemo.java`) im Explorer klicken und **„Run Java“** auswählen oder die Startkonfigurationen im Run and Debug-Panel verwenden.

**Option 2: Verwendung des Terminals**

Setzen Sie das Token als Umgebungsvariable:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Beispiele ausführen

**Mit VS Code:** Klicken Sie einfach mit der rechten Maustaste auf eine Demo-Datei im Explorer und wählen Sie **„Run Java“** oder verwenden Sie die Startkonfigurationen im Run and Debug-Panel (stellen Sie sicher, dass Sie Ihr Token zuerst in die `.env`-Datei eingefügt haben).

**Mit Maven:** Alternativ können Sie es über die Kommandozeile ausführen:

### 1. Basis-Chat

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Prompt-Muster

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Zeigt Zero-Shot, Few-Shot, Chain-of-Thought und rollenbasierte Eingabeaufforderungen.

### 3. Funktionsaufruf

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

Die KI ruft Ihre Java-Methoden automatisch auf, wenn nötig.

### 4. Dokument Q&A (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Stellen Sie Fragen zum Inhalt in `document.txt`.

## Was jedes Beispiel zeigt

**Basis-Chat** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Starten Sie hier, um LangChain4j in seiner einfachsten Form zu sehen. Sie erstellen ein `OpenAiOfficialChatModel`, senden eine Eingabeaufforderung mit `.chat()` und erhalten eine Antwort zurück. Dies zeigt die Grundlage: wie man Modelle mit benutzerdefinierten Endpunkten und API-Schlüsseln initialisiert. Sobald Sie dieses Muster verstanden haben, baut alles andere darauf auf.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Probieren Sie es mit [GitHub Copilot](https://github.com/features/copilot) Chat:** Öffnen Sie [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) und fragen Sie:
> - „Wie wechsle ich in diesem Code von GitHub Models zu Azure OpenAI?“
> - „Welche anderen Parameter kann ich in OpenAiOfficialChatModel.builder() konfigurieren?“
> - „Wie füge ich Streaming-Antworten hinzu, anstatt auf die vollständige Antwort zu warten?“

**Prompt Engineering** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Jetzt, da Sie wissen, wie man mit einem Modell spricht, schauen wir uns an, was Sie ihm sagen. Diese Demo verwendet dieselbe Modellkonfiguration, zeigt aber vier verschiedene Eingabeaufforderungsmuster. Probieren Sie Zero-Shot-Prompts für direkte Anweisungen, Few-Shot-Prompts, die aus Beispielen lernen, Chain-of-Thought-Prompts, die Denkprozesse offenlegen, und rollenbasierte Prompts, die Kontext setzen. Sie werden sehen, wie dasselbe Modell dramatisch unterschiedliche Ergebnisse liefert, je nachdem, wie Sie Ihre Anfrage formulieren.

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

> **🤖 Probieren Sie es mit [GitHub Copilot](https://github.com/features/copilot) Chat:** Öffnen Sie [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) und fragen Sie:
> - „Was ist der Unterschied zwischen Zero-Shot- und Few-Shot-Prompting und wann sollte ich welches verwenden?“
> - „Wie beeinflusst der Temperatur-Parameter die Antworten des Modells?“
> - „Welche Techniken gibt es, um Prompt-Injection-Angriffe in der Produktion zu verhindern?“
> - „Wie kann ich wiederverwendbare PromptTemplate-Objekte für gängige Muster erstellen?“

**Tool-Integration** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Hier wird LangChain4j mächtig. Sie verwenden `AiServices`, um einen KI-Assistenten zu erstellen, der Ihre Java-Methoden aufrufen kann. Annotieren Sie einfach Methoden mit `@Tool("Beschreibung")` und LangChain4j übernimmt den Rest – die KI entscheidet automatisch, wann welches Tool basierend auf der Benutzeranfrage verwendet wird. Dies demonstriert Funktionsaufrufe, eine Schlüsseltechnik zum Erstellen von KI, die Aktionen ausführen kann, nicht nur Fragen beantwortet.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Probieren Sie es mit [GitHub Copilot](https://github.com/features/copilot) Chat:** Öffnen Sie [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) und fragen Sie:
> - „Wie funktioniert die @Tool-Annotation und was macht LangChain4j damit hinter den Kulissen?“
> - „Kann die KI mehrere Tools nacheinander aufrufen, um komplexe Probleme zu lösen?“
> - „Was passiert, wenn ein Tool eine Ausnahme wirft – wie sollte ich Fehler behandeln?“
> - „Wie würde ich eine echte API anstelle dieses Taschenrechner-Beispiels integrieren?“

**Dokument Q&A (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Hier sehen Sie die Grundlage von RAG (retrieval-augmented generation). Anstatt sich auf die Trainingsdaten des Modells zu verlassen, laden Sie Inhalte aus [`document.txt`](../../../00-quick-start/document.txt) und fügen sie in die Eingabeaufforderung ein. Die KI antwortet basierend auf Ihrem Dokument, nicht auf ihrem allgemeinen Wissen. Dies ist der erste Schritt zum Aufbau von Systemen, die mit Ihren eigenen Daten arbeiten können.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Hinweis:** Dieser einfache Ansatz lädt das gesamte Dokument in die Eingabeaufforderung. Bei großen Dateien (>10KB) überschreiten Sie die Kontextgrenzen. Modul 03 behandelt Chunking und Vektorsuche für produktive RAG-Systeme.

> **🤖 Probieren Sie es mit [GitHub Copilot](https://github.com/features/copilot) Chat:** Öffnen Sie [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) und fragen Sie:
> - „Wie verhindert RAG KI-Halluzinationen im Vergleich zur Verwendung der Trainingsdaten des Modells?“
> - „Was ist der Unterschied zwischen diesem einfachen Ansatz und der Verwendung von Vektor-Embeddings für die Suche?“
> - „Wie würde ich das skalieren, um mehrere Dokumente oder größere Wissensdatenbanken zu handhaben?“
> - „Was sind Best Practices für die Strukturierung der Eingabeaufforderung, damit die KI nur den bereitgestellten Kontext verwendet?“

## Fehlerbehebung

Die Beispiele enthalten `.logRequests(true)` und `.logResponses(true)`, um API-Aufrufe in der Konsole anzuzeigen. Dies hilft bei der Fehlersuche bei Authentifizierungsfehlern, Ratenbegrenzungen oder unerwarteten Antworten. Entfernen Sie diese Flags in der Produktion, um Log-Rauschen zu reduzieren.

## Nächste Schritte

**Nächstes Modul:** [01-introduction - Erste Schritte mit LangChain4j und gpt-5 auf Azure](../01-introduction/README.md)

---

**Navigation:** [← Zurück zur Hauptseite](../README.md) | [Weiter: Modul 01 - Einführung →](../01-introduction/README.md)

---

## Fehlerbehebung

### Erster Maven-Build

**Problem:** Erster `mvn clean compile` oder `mvn package` dauert lange (10-15 Minuten)

**Ursache:** Maven muss beim ersten Build alle Projektabhängigkeiten (Spring Boot, LangChain4j-Bibliotheken, Azure SDKs usw.) herunterladen.

**Lösung:** Dies ist normales Verhalten. Nachfolgende Builds sind viel schneller, da Abhängigkeiten lokal zwischengespeichert werden. Die Downloadzeit hängt von Ihrer Netzwerkgeschwindigkeit ab.

### PowerShell Maven-Befehls-Syntax

**Problem:** Maven-Befehle schlagen fehl mit Fehler `Unknown lifecycle phase ".mainClass=..."`

**Ursache:** PowerShell interpretiert `=` als Zuweisungsoperator und bricht die Maven-Property-Syntax.

**Lösung:** Verwenden Sie den Stop-Parsing-Operator `--%` vor dem Maven-Befehl:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Der Operator `--%` weist PowerShell an, alle verbleibenden Argumente wörtlich an Maven weiterzugeben, ohne sie zu interpretieren.

### Windows PowerShell Emoji-Anzeige

**Problem:** KI-Antworten zeigen in PowerShell Müllzeichen (z. B. `????` oder `â??`) statt Emojis an

**Ursache:** Die Standardcodierung von PowerShell unterstützt keine UTF-8-Emojis

**Lösung:** Führen Sie diesen Befehl vor dem Ausführen von Java-Anwendungen aus:
```cmd
chcp 65001
```

Dies erzwingt UTF-8-Codierung im Terminal. Alternativ verwenden Sie Windows Terminal, das eine bessere Unicode-Unterstützung bietet.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Haftungsausschluss**:  
Dieses Dokument wurde mit dem KI-Übersetzungsdienst [Co-op Translator](https://github.com/Azure/co-op-translator) übersetzt. Obwohl wir uns um Genauigkeit bemühen, beachten Sie bitte, dass automatisierte Übersetzungen Fehler oder Ungenauigkeiten enthalten können. Das Originaldokument in seiner Ursprungssprache ist als maßgebliche Quelle zu betrachten. Für wichtige Informationen wird eine professionelle menschliche Übersetzung empfohlen. Wir übernehmen keine Haftung für Missverständnisse oder Fehlinterpretationen, die aus der Nutzung dieser Übersetzung entstehen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->