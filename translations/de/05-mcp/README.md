<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c25ec1f10ef156c53e190cdf8b0711ab",
  "translation_date": "2025-12-13T17:36:03+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "de"
}
-->
# Modul 05: Model Context Protocol (MCP)

## Inhaltsverzeichnis

- [Was Sie lernen werden](../../../05-mcp)
- [Verständnis von MCP](../../../05-mcp)
- [Wie MCP funktioniert](../../../05-mcp)
  - [Server-Client-Architektur](../../../05-mcp)
  - [Werkzeugerkennung](../../../05-mcp)
  - [Transportmechanismen](../../../05-mcp)
- [Voraussetzungen](../../../05-mcp)
- [Was dieses Modul abdeckt](../../../05-mcp)
- [Schnellstart](../../../05-mcp)
  - [Beispiel 1: Remote-Rechner (Streamable HTTP)](../../../05-mcp)
  - [Beispiel 2: Dateioperationen (Stdio)](../../../05-mcp)
  - [Beispiel 3: Git-Analyse (Docker)](../../../05-mcp)
- [Schlüsselkonzepte](../../../05-mcp)
  - [Transportauswahl](../../../05-mcp)
  - [Werkzeugerkennung](../../../05-mcp)
  - [Sitzungsverwaltung](../../../05-mcp)
  - [Plattformübergreifende Überlegungen](../../../05-mcp)
- [Wann MCP verwenden](../../../05-mcp)
- [MCP-Ökosystem](../../../05-mcp)
- [Herzlichen Glückwunsch!](../../../05-mcp)
  - [Was kommt als Nächstes?](../../../05-mcp)
- [Fehlerbehebung](../../../05-mcp)

## Was Sie lernen werden

Sie haben konversationelle KI gebaut, Prompts gemeistert, Antworten in Dokumenten verankert und Agenten mit Werkzeugen erstellt. Aber all diese Werkzeuge waren speziell für Ihre Anwendung maßgeschneidert. Was wäre, wenn Sie Ihrer KI Zugang zu einem standardisierten Ökosystem von Werkzeugen geben könnten, die jeder erstellen und teilen kann?

Das Model Context Protocol (MCP) bietet genau das – eine standardisierte Möglichkeit für KI-Anwendungen, externe Werkzeuge zu entdecken und zu nutzen. Anstatt für jede Datenquelle oder jeden Dienst individuelle Integrationen zu schreiben, verbinden Sie sich mit MCP-Servern, die ihre Fähigkeiten in einem konsistenten Format bereitstellen. Ihr KI-Agent kann diese Werkzeuge dann automatisch entdecken und verwenden.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5448d2fa21a61218777ceb8010ea0390dd43924b26df35f61.de.png" alt="MCP Vergleich" width="800"/>

*Vor MCP: Komplexe Punkt-zu-Punkt-Integrationen. Nach MCP: Ein Protokoll, endlose Möglichkeiten.*

## Verständnis von MCP

MCP löst ein grundlegendes Problem in der KI-Entwicklung: Jede Integration ist individuell. Möchten Sie auf GitHub zugreifen? Individueller Code. Möchten Sie Dateien lesen? Individueller Code. Möchten Sie eine Datenbank abfragen? Individueller Code. Und keine dieser Integrationen funktioniert mit anderen KI-Anwendungen.

MCP standardisiert dies. Ein MCP-Server stellt Werkzeuge mit klaren Beschreibungen und Schemata bereit. Jeder MCP-Client kann sich verbinden, verfügbare Werkzeuge entdecken und sie nutzen. Einmal bauen, überall verwenden.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9814b7cffade208d4b0d97203c22df8d8e5504d8238fa7065.de.png" alt="MCP Architektur" width="800"/>

*Model Context Protocol Architektur – standardisierte Werkzeugerkennung und Ausführung*

## Wie MCP funktioniert

**Server-Client-Architektur**

MCP verwendet ein Client-Server-Modell. Server bieten Werkzeuge an – Dateien lesen, Datenbanken abfragen, APIs aufrufen. Clients (Ihre KI-Anwendung) verbinden sich mit Servern und nutzen deren Werkzeuge.

**Werkzeugerkennung**

Wenn Ihr Client sich mit einem MCP-Server verbindet, fragt er: „Welche Werkzeuge hast du?“ Der Server antwortet mit einer Liste verfügbarer Werkzeuge, jeweils mit Beschreibungen und Parameterschemata. Ihr KI-Agent kann dann entscheiden, welche Werkzeuge basierend auf Benutzeranfragen verwendet werden.

**Transportmechanismen**

MCP definiert zwei Transportmechanismen: HTTP für entfernte Server, Stdio für lokale Prozesse (einschließlich Docker-Container):

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020ed801b772b26ed69338e22739677aa017e0968f6538b09a2.de.png" alt="Transportmechanismen" width="800"/>

*MCP-Transportmechanismen: HTTP für entfernte Server, Stdio für lokale Prozesse (einschließlich Docker-Container)*

**Streamable HTTP** – [StreamableHttpDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java)

Für entfernte Server. Ihre Anwendung sendet HTTP-Anfragen an einen Server, der irgendwo im Netzwerk läuft. Verwendet Server-Sent Events für Echtzeitkommunikation.

```java
McpTransport httpTransport = new StreamableHttpMcpTransport.Builder()
    .url("http://localhost:3001/mcp")
    .timeout(Duration.ofSeconds(60))
    .logRequests(true)
    .logResponses(true)
    .build();
```

> **🤖 Probieren Sie es mit [GitHub Copilot](https://github.com/features/copilot) Chat:** Öffnen Sie [`StreamableHttpDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java) und fragen Sie:
> - „Wie unterscheidet sich MCP von direkter Werkzeugintegration wie in Modul 04?“
> - „Was sind die Vorteile der Verwendung von MCP für das Teilen von Werkzeugen zwischen Anwendungen?“
> - „Wie gehe ich mit Verbindungsfehlern oder Timeouts zu MCP-Servern um?“

**Stdio** – [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Für lokale Prozesse. Ihre Anwendung startet einen Server als Unterprozess und kommuniziert über Standard-Ein-/Ausgabe. Nützlich für Dateisystemzugriff oder Kommandozeilenwerkzeuge.

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@0.6.2",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 Probieren Sie es mit [GitHub Copilot](https://github.com/features/copilot) Chat:** Öffnen Sie [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) und fragen Sie:
> - „Wie funktioniert der Stdio-Transport und wann sollte ich ihn statt HTTP verwenden?“
> - „Wie verwaltet LangChain4j den Lebenszyklus von gestarteten MCP-Serverprozessen?“
> - „Was sind die Sicherheitsaspekte, wenn KI Zugriff auf das Dateisystem erhält?“

**Docker (verwendet Stdio)** – [GitRepositoryAnalyzer.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java)

Für containerisierte Dienste. Verwendet Stdio-Transport zur Kommunikation mit einem Docker-Container via `docker run`. Gut für komplexe Abhängigkeiten oder isolierte Umgebungen.

```java
McpTransport dockerTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        "docker", "run",
        "-e", "GITHUB_PERSONAL_ACCESS_TOKEN=" + System.getenv("GITHUB_TOKEN"),
        "-v", volumeMapping,
        "-i", "mcp/git"
    ))
    .logEvents(true)
    .build();
```

> **🤖 Probieren Sie es mit [GitHub Copilot](https://github.com/features/copilot) Chat:** Öffnen Sie [`GitRepositoryAnalyzer.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java) und fragen Sie:
> - „Wie isoliert der Docker-Transport MCP-Server und was sind die Vorteile?“
> - „Wie konfiguriere ich Volume-Mounts, um Daten zwischen Host und MCP-Containern zu teilen?“
> - „Was sind Best Practices für das Management von Docker-basierten MCP-Server-Lebenszyklen in der Produktion?“

## Ausführen der Beispiele

### Voraussetzungen

- Java 21+, Maven 3.9+
- Node.js 16+ und npm (für MCP-Server)
- **Docker Desktop** – Muss für Beispiel 3 **LÄUFT** (nicht nur installiert sein)
- GitHub Personal Access Token in `.env` Datei konfiguriert (aus Modul 00)

> **Hinweis:** Falls Sie Ihren GitHub-Token noch nicht eingerichtet haben, siehe [Modul 00 - Schnellstart](../00-quick-start/README.md) für Anweisungen.

> **⚠️ Docker-Nutzer:** Vor dem Ausführen von Beispiel 3 prüfen Sie mit `docker ps`, ob Docker Desktop läuft. Bei Verbindungsfehlern starten Sie Docker Desktop und warten ca. 30 Sekunden auf die Initialisierung.

## Schnellstart

**Mit VS Code:** Klicken Sie einfach mit der rechten Maustaste auf eine Demo-Datei im Explorer und wählen Sie **„Run Java“**, oder verwenden Sie die Startkonfigurationen im Run and Debug-Panel (stellen Sie sicher, dass Sie Ihren Token zuerst in die `.env` Datei eingefügt haben).

**Mit Maven:** Alternativ können Sie die Beispiele auch über die Kommandozeile ausführen.

**⚠️ Wichtig:** Einige Beispiele haben Voraussetzungen (wie das Starten eines MCP-Servers oder das Bauen von Docker-Images). Prüfen Sie die Anforderungen jedes Beispiels vor dem Ausführen.

### Beispiel 1: Remote-Rechner (Streamable HTTP)

Dies demonstriert netzwerkbasierte Werkzeugintegration.

**⚠️ Voraussetzung:** Sie müssen zuerst den MCP-Server starten (siehe Terminal 1 unten).

**Terminal 1 – Starten Sie den MCP-Server:**

**Bash:**
```bash
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**PowerShell:**
```powershell
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**Terminal 2 – Führen Sie das Beispiel aus:**

**Mit VS Code:** Rechtsklick auf `StreamableHttpDemo.java` und **„Run Java“** wählen.

**Mit Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

Beobachten Sie, wie der Agent verfügbare Werkzeuge entdeckt und dann den Rechner für eine Addition verwendet.

### Beispiel 2: Dateioperationen (Stdio)

Dies demonstriert lokal basierte Unterprozess-Werkzeuge.

**✅ Keine Voraussetzungen nötig** – der MCP-Server wird automatisch gestartet.

**Mit VS Code:** Rechtsklick auf `StdioTransportDemo.java` und **„Run Java“** wählen.

**Mit Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

Die Anwendung startet automatisch einen Dateisystem-MCP-Server und liest eine lokale Datei. Beachten Sie, wie die Verwaltung des Unterprozesses für Sie übernommen wird.

**Erwartete Ausgabe:**
```
Assistant response: The content of the file is "Kaboom!".
```

### Beispiel 3: Git-Analyse (Docker)

Dies demonstriert containerisierte Werkzeugserver.

**⚠️ Voraussetzungen:**  
1. **Docker Desktop muss LÄUFT** (nicht nur installiert)  
2. **Windows-Nutzer:** WSL 2 Modus empfohlen (Docker Desktop Einstellungen → Allgemein → „Use the WSL 2 based engine“). Hyper-V Modus erfordert manuelle Dateifreigabe-Konfiguration.  
3. Sie müssen das Docker-Image zuerst bauen (siehe Terminal 1 unten)

**Prüfen Sie, ob Docker läuft:**

**Bash:**
```bash
docker ps  # Sollte die Containerliste anzeigen, keinen Fehler
```

**PowerShell:**
```powershell
docker ps  # Sollte die Containerliste anzeigen, keinen Fehler
```

Wenn Sie einen Fehler wie „Cannot connect to Docker daemon“ oder „The system cannot find the file specified“ sehen, starten Sie Docker Desktop und warten Sie auf die Initialisierung (~30 Sekunden).

**Fehlerbehebung:**  
- Wenn die KI ein leeres Repository oder keine Dateien meldet, funktioniert das Volume-Mount (`-v`) nicht.  
- **Windows Hyper-V Nutzer:** Fügen Sie das Projektverzeichnis zu Docker Desktop Einstellungen → Ressourcen → Dateifreigabe hinzu und starten Sie Docker Desktop neu.  
- **Empfohlene Lösung:** Wechseln Sie in den WSL 2 Modus für automatische Dateifreigabe (Einstellungen → Allgemein → „Use the WSL 2 based engine“ aktivieren).

**Terminal 1 – Bauen Sie das Docker-Image:**

**Bash:**
```bash
cd servers/src/git
docker build -t mcp/git .
```

**PowerShell:**
```powershell
cd servers/src/git
docker build -t mcp/git .
```

**Terminal 2 – Führen Sie den Analyzer aus:**

**Mit VS Code:** Rechtsklick auf `GitRepositoryAnalyzer.java` und **„Run Java“** wählen.

**Mit Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

Die Anwendung startet einen Docker-Container, mountet Ihr Repository und analysiert die Repository-Struktur und Inhalte über den KI-Agenten.

## Schlüsselkonzepte

**Transportauswahl**

Wählen Sie basierend darauf, wo Ihre Werkzeuge leben:  
- Entfernte Dienste → Streamable HTTP  
- Lokales Dateisystem → Stdio  
- Komplexe Abhängigkeiten → Docker

**Werkzeugerkennung**

MCP-Clients entdecken automatisch verfügbare Werkzeuge beim Verbinden. Ihr KI-Agent sieht Werkzeugbeschreibungen und entscheidet, welche er basierend auf der Benutzeranfrage verwendet.

**Sitzungsverwaltung**

Streamable HTTP Transport hält Sitzungen aufrecht, was zustandsbehaftete Interaktionen mit entfernten Servern ermöglicht. Stdio- und Docker-Transporte sind typischerweise zustandslos.

**Plattformübergreifende Überlegungen**

Die Beispiele behandeln Plattformunterschiede automatisch (Windows vs. Unix-Befehlsunterschiede, Pfadkonvertierungen für Docker). Das ist wichtig für produktive Einsätze in verschiedenen Umgebungen.

## Wann MCP verwenden

**Verwenden Sie MCP, wenn:**  
- Sie bestehende Werkzeug-Ökosysteme nutzen wollen  
- Werkzeuge bauen, die von mehreren Anwendungen verwendet werden  
- Drittanbieterdienste mit Standardprotokollen integrieren  
- Werkzeugimplementierungen ohne Codeänderungen austauschen möchten

**Verwenden Sie benutzerdefinierte Werkzeuge (Modul 04), wenn:**  
- Sie anwendungsspezifische Funktionalität bauen  
- Leistung kritisch ist (MCP verursacht Overhead)  
- Ihre Werkzeuge einfach sind und nicht wiederverwendet werden  
- Sie vollständige Kontrolle über die Ausführung benötigen

## MCP-Ökosystem

Das Model Context Protocol ist ein offener Standard mit wachsendem Ökosystem:

- Offizielle MCP-Server für gängige Aufgaben (Dateisystem, Git, Datenbanken)  
- Community-beitragsbasierte Server für verschiedene Dienste  
- Standardisierte Werkzeugbeschreibungen und Schemata  
- Plattformübergreifende Kompatibilität (funktioniert mit jedem MCP-Client)

Diese Standardisierung bedeutet, dass Werkzeuge, die für eine KI-Anwendung gebaut wurden, auch mit anderen funktionieren und so ein gemeinsames Ökosystem von Fähigkeiten schaffen.

## Herzlichen Glückwunsch!

Sie haben den LangChain4j für Anfänger Kurs abgeschlossen. Sie haben gelernt:

- Wie man konversationelle KI mit Gedächtnis baut (Modul 01)  
- Prompt-Engineering-Muster für verschiedene Aufgaben (Modul 02)  
- Antworten in Ihren Dokumenten mit RAG verankern (Modul 03)  
- KI-Agenten mit benutzerdefinierten Werkzeugen erstellen (Modul 04)  
- Standardisierte Werkzeuge über MCP integrieren (Modul 05)

Sie haben nun die Grundlage, um produktive KI-Anwendungen zu bauen. Die Konzepte, die Sie gelernt haben, gelten unabhängig von spezifischen Frameworks oder Modellen – sie sind grundlegende Muster im KI-Engineering.

### Was kommt als Nächstes?

Nach Abschluss der Module erkunden Sie den [Testing Guide](../docs/TESTING.md), um LangChain4j-Testkonzepte in Aktion zu sehen.

**Offizielle Ressourcen:**  
- [LangChain4j Dokumentation](https://docs.langchain4j.dev/) – Umfassende Anleitungen und API-Referenz  
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) – Quellcode und Beispiele  
- [LangChain4j Tutorials](https://docs.langchain4j.dev/tutorials/) – Schritt-für-Schritt-Tutorials für verschiedene Anwendungsfälle

Vielen Dank, dass Sie diesen Kurs abgeschlossen haben!

---

**Navigation:** [← Vorheriges: Modul 04 - Werkzeuge](../04-tools/README.md) | [Zurück zur Übersicht](../README.md)

---

## Fehlerbehebung

### PowerShell Maven-Befehlsyntax
**Problem**: Maven-Befehle schlagen fehl mit dem Fehler `Unknown lifecycle phase ".mainClass=..."`

**Ursache**: PowerShell interpretiert `=` als Zuweisungsoperator, wodurch die Maven-Property-Syntax unterbrochen wird

**Lösung**: Verwenden Sie den Stop-Parsing-Operator `--%` vor dem Maven-Befehl:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

Der Operator `--%` weist PowerShell an, alle verbleibenden Argumente wörtlich an Maven weiterzugeben, ohne sie zu interpretieren.

### Docker-Verbindungsprobleme

**Problem**: Docker-Befehle schlagen fehl mit "Cannot connect to Docker daemon" oder "The system cannot find the file specified"

**Ursache**: Docker Desktop läuft nicht oder ist nicht vollständig initialisiert

**Lösung**: 
1. Starten Sie Docker Desktop
2. Warten Sie ca. 30 Sekunden auf die vollständige Initialisierung
3. Überprüfen Sie mit `docker ps` (sollte eine Containerliste anzeigen, keinen Fehler)
4. Führen Sie dann Ihr Beispiel aus

### Windows Docker Volume Mounting

**Problem**: Git-Repository-Analyzer meldet leeres Repository oder keine Dateien

**Ursache**: Volume-Mount (`-v`) funktioniert nicht wegen Dateifreigabe-Konfiguration

**Lösung**:
- **Empfohlen:** Wechseln Sie in den WSL 2 Modus (Docker Desktop Einstellungen → Allgemein → "Use the WSL 2 based engine")
- **Alternative (Hyper-V):** Fügen Sie das Projektverzeichnis zu Docker Desktop Einstellungen → Ressourcen → Dateifreigabe hinzu und starten Sie Docker Desktop neu

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Haftungsausschluss**:  
Dieses Dokument wurde mit dem KI-Übersetzungsdienst [Co-op Translator](https://github.com/Azure/co-op-translator) übersetzt. Obwohl wir uns um Genauigkeit bemühen, beachten Sie bitte, dass automatisierte Übersetzungen Fehler oder Ungenauigkeiten enthalten können. Das Originaldokument in seiner Ursprungssprache ist als maßgebliche Quelle zu betrachten. Für wichtige Informationen wird eine professionelle menschliche Übersetzung empfohlen. Wir übernehmen keine Haftung für Missverständnisse oder Fehlinterpretationen, die aus der Nutzung dieser Übersetzung entstehen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->