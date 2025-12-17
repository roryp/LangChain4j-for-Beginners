<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T21:43:28+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "de"
}
-->
# Azure-Infrastruktur für LangChain4j Erste Schritte

## Inhaltsverzeichnis

- [Voraussetzungen](../../../../01-introduction/infra)
- [Architektur](../../../../01-introduction/infra)
- [Erstellte Ressourcen](../../../../01-introduction/infra)
- [Schnellstart](../../../../01-introduction/infra)
- [Konfiguration](../../../../01-introduction/infra)
- [Verwaltungsbefehle](../../../../01-introduction/infra)
- [Kostenoptimierung](../../../../01-introduction/infra)
- [Überwachung](../../../../01-introduction/infra)
- [Fehlerbehebung](../../../../01-introduction/infra)
- [Infrastruktur aktualisieren](../../../../01-introduction/infra)
- [Aufräumen](../../../../01-introduction/infra)
- [Dateistruktur](../../../../01-introduction/infra)
- [Sicherheitsempfehlungen](../../../../01-introduction/infra)
- [Zusätzliche Ressourcen](../../../../01-introduction/infra)

Dieses Verzeichnis enthält die Azure-Infrastruktur als Code (IaC) mit Bicep und Azure Developer CLI (azd) zum Bereitstellen von Azure OpenAI-Ressourcen.

## Voraussetzungen

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (Version 2.50.0 oder höher)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (Version 1.5.0 oder höher)
- Ein Azure-Abonnement mit Berechtigungen zum Erstellen von Ressourcen

## Architektur

**Vereinfachte lokale Entwicklungsumgebung** – Nur Azure OpenAI bereitstellen, alle Apps lokal ausführen.

Die Infrastruktur stellt folgende Azure-Ressourcen bereit:

### KI-Dienste
- **Azure OpenAI**: Cognitive Services mit zwei Modellbereitstellungen:
  - **gpt-5**: Chat-Completion-Modell (20K TPM Kapazität)
  - **text-embedding-3-small**: Embedding-Modell für RAG (20K TPM Kapazität)

### Lokale Entwicklung
Alle Spring Boot-Anwendungen laufen lokal auf Ihrem Rechner:
- 01-introduction (Port 8080)
- 02-prompt-engineering (Port 8083)
- 03-rag (Port 8081)
- 04-tools (Port 8084)

## Erstellte Ressourcen

| Ressourcentyp | Ressourcenname-Muster | Zweck |
|--------------|----------------------|---------|
| Ressourcengruppe | `rg-{environmentName}` | Enthält alle Ressourcen |
| Azure OpenAI | `aoai-{resourceToken}` | Hosting der KI-Modelle |

> **Hinweis:** `{resourceToken}` ist ein eindeutiger String, der aus der Abonnement-ID, dem Umgebungsnamen und dem Standort generiert wird

## Schnellstart

### 1. Azure OpenAI bereitstellen

**Bash:**
```bash
cd 01-introduction
azd up
```

**PowerShell:**
```powershell
cd 01-introduction
azd up
```

Wenn Sie dazu aufgefordert werden:
- Wählen Sie Ihr Azure-Abonnement aus
- Wählen Sie einen Standort (empfohlen: `eastus2` oder `swedencentral` für GPT-5-Verfügbarkeit)
- Bestätigen Sie den Umgebungsnamen (Standard: `langchain4j-dev`)

Dies erstellt:
- Azure OpenAI-Ressource mit GPT-5 und text-embedding-3-small
- Ausgabe der Verbindungsdetails

### 2. Verbindungsdetails abrufen

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Dies zeigt an:
- `AZURE_OPENAI_ENDPOINT`: Ihre Azure OpenAI-Endpunkt-URL
- `AZURE_OPENAI_KEY`: API-Schlüssel zur Authentifizierung
- `AZURE_OPENAI_DEPLOYMENT`: Chat-Modellname (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Embedding-Modellname

### 3. Anwendungen lokal ausführen

Der Befehl `azd up` erstellt automatisch eine `.env`-Datei im Stammverzeichnis mit allen notwendigen Umgebungsvariablen.

**Empfohlen:** Starten Sie alle Webanwendungen:

**Bash:**
```bash
# Vom Stammverzeichnis
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Vom Stammverzeichnis aus
cd ../..
.\start-all.ps1
```

Oder starten Sie ein einzelnes Modul:

**Bash:**
```bash
# Beispiel: Starte nur das Einführungsmodul
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Beispiel: Starte nur das Einführungsmodul
cd ../01-introduction
.\start.ps1
```

Beide Skripte laden automatisch die Umgebungsvariablen aus der vom `azd up` erstellten `.env`-Datei im Stammverzeichnis.

## Konfiguration

### Modellbereitstellungen anpassen

Um Modellbereitstellungen zu ändern, bearbeiten Sie `infra/main.bicep` und passen Sie den Parameter `openAiDeployments` an:

```bicep
param openAiDeployments array = [
  {
    name: 'gpt-5'  // Model deployment name
    model: {
      format: 'OpenAI'
      name: 'gpt-5'
      version: '2025-08-07'  // Model version
    }
    sku: {
      name: 'Standard'
      capacity: 20  // TPM in thousands
    }
  }
  // Add more deployments...
]
```

Verfügbare Modelle und Versionen: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure-Regionen ändern

Um in einer anderen Region bereitzustellen, bearbeiten Sie `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Verfügbarkeit von GPT-5 prüfen: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Um die Infrastruktur nach Änderungen an den Bicep-Dateien zu aktualisieren:

**Bash:**
```bash
# ARM-Vorlage neu erstellen
az bicep build --file infra/main.bicep

# Änderungen vorschau
azd provision --preview

# Änderungen anwenden
azd provision
```

**PowerShell:**
```powershell
# ARM-Vorlage neu erstellen
az bicep build --file infra/main.bicep

# Änderungen vorschau
azd provision --preview

# Änderungen anwenden
azd provision
```

## Aufräumen

Um alle Ressourcen zu löschen:

**Bash:**
```bash
# Alle Ressourcen löschen
azd down

# Alles einschließlich der Umgebung löschen
azd down --purge
```

**PowerShell:**
```powershell
# Alle Ressourcen löschen
azd down

# Alles einschließlich der Umgebung löschen
azd down --purge
```

**Warnung**: Dies löscht alle Azure-Ressourcen dauerhaft.

## Dateistruktur

## Kostenoptimierung

### Entwicklung/Test
Für Entwicklungs-/Testumgebungen können Sie Kosten reduzieren:
- Verwenden Sie die Standardstufe (S0) für Azure OpenAI
- Setzen Sie eine niedrigere Kapazität (10K TPM statt 20K) in `infra/core/ai/cognitiveservices.bicep`
- Löschen Sie Ressourcen, wenn sie nicht verwendet werden: `azd down`

### Produktion
Für die Produktion:
- Erhöhen Sie die OpenAI-Kapazität basierend auf der Nutzung (50K+ TPM)
- Aktivieren Sie Zonenausfallsicherheit für höhere Verfügbarkeit
- Implementieren Sie ordnungsgemäße Überwachung und Kostenwarnungen

### Kostenschätzung
- Azure OpenAI: Bezahlung pro Token (Eingabe + Ausgabe)
- GPT-5: ca. 3-5 $ pro 1 Mio. Tokens (aktuellen Preis prüfen)
- text-embedding-3-small: ca. 0,02 $ pro 1 Mio. Tokens

Preisrechner: https://azure.microsoft.com/pricing/calculator/

## Überwachung

### Azure OpenAI-Metriken anzeigen

Gehen Sie im Azure-Portal zu Ihrer OpenAI-Ressource → Metriken:
- Token-basierte Auslastung
- HTTP-Anforderungsrate
- Antwortzeit
- Aktive Tokens

## Fehlerbehebung

### Problem: Azure OpenAI Subdomain-Name-Konflikt

**Fehlermeldung:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Ursache:**
Der aus Ihrem Abonnement/Umgebung generierte Subdomain-Name wird bereits verwendet, möglicherweise von einer vorherigen Bereitstellung, die nicht vollständig gelöscht wurde.

**Lösung:**
1. **Option 1 – Verwenden Sie einen anderen Umgebungsnamen:**
   
   **Bash:**
   ```bash
   azd env new my-unique-env-name
   azd up
   ```
   
   **PowerShell:**
   ```powershell
   azd env new my-unique-env-name
   azd up
   ```

2. **Option 2 – Manuelle Bereitstellung über Azure-Portal:**
   - Gehen Sie zum Azure-Portal → Ressource erstellen → Azure OpenAI
   - Wählen Sie einen eindeutigen Namen für Ihre Ressource
   - Stellen Sie folgende Modelle bereit:
     - **GPT-5**
     - **text-embedding-3-small** (für RAG-Module)
   - **Wichtig:** Notieren Sie Ihre Bereitstellungsnamen – sie müssen mit der `.env`-Konfiguration übereinstimmen
   - Nach der Bereitstellung erhalten Sie Ihren Endpunkt und API-Schlüssel unter „Schlüssel und Endpunkt“
   - Erstellen Sie eine `.env`-Datei im Projektstamm mit:
     
     **Beispiel `.env`-Datei:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Richtlinien zur Benennung von Modellbereitstellungen:**
- Verwenden Sie einfache, konsistente Namen: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Bereitstellungsnamen müssen genau mit der `.env`-Konfiguration übereinstimmen
- Häufiger Fehler: Modell mit einem Namen erstellen, aber im Code einen anderen Namen referenzieren

### Problem: GPT-5 in ausgewählter Region nicht verfügbar

**Lösung:**
- Wählen Sie eine Region mit GPT-5-Zugang (z. B. eastus, swedencentral)
- Verfügbarkeit prüfen: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Problem: Unzureichendes Kontingent für Bereitstellung

**Lösung:**
1. Beantragen Sie eine Kontingenterhöhung im Azure-Portal
2. Oder verwenden Sie eine niedrigere Kapazität in `main.bicep` (z. B. capacity: 10)

### Problem: „Resource not found“ bei lokalem Ausführen

**Lösung:**
1. Überprüfen Sie die Bereitstellung: `azd env get-values`
2. Prüfen Sie, ob Endpunkt und Schlüssel korrekt sind
3. Stellen Sie sicher, dass die Ressourcengruppe im Azure-Portal existiert

### Problem: Authentifizierung fehlgeschlagen

**Lösung:**
- Überprüfen Sie, ob `AZURE_OPENAI_API_KEY` korrekt gesetzt ist
- Der Schlüssel sollte eine 32-stellige hexadezimale Zeichenfolge sein
- Holen Sie bei Bedarf einen neuen Schlüssel im Azure-Portal

### Bereitstellung schlägt fehl

**Problem**: `azd provision` schlägt mit Kontingent- oder Kapazitätsfehlern fehl

**Lösung**: 
1. Versuchen Sie eine andere Region – siehe Abschnitt [Azure-Regionen ändern](../../../../01-introduction/infra) für Konfigurationshinweise
2. Prüfen Sie, ob Ihr Abonnement Azure OpenAI-Kontingent hat:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Anwendung verbindet sich nicht

**Problem**: Java-Anwendung zeigt Verbindungsfehler

**Lösung**:
1. Überprüfen Sie, ob Umgebungsvariablen exportiert sind:
   
   **Bash:**
   ```bash
   echo $AZURE_OPENAI_ENDPOINT
   echo $AZURE_OPENAI_API_KEY
   ```
   
   **PowerShell:**
   ```powershell
   Write-Host $env:AZURE_OPENAI_ENDPOINT
   Write-Host $env:AZURE_OPENAI_API_KEY
   ```

2. Prüfen Sie, ob das Endpunktformat korrekt ist (sollte `https://xxx.openai.azure.com` sein)
3. Vergewissern Sie sich, dass der API-Schlüssel der primäre oder sekundäre Schlüssel aus dem Azure-Portal ist

**Problem**: 401 Unauthorized von Azure OpenAI

**Lösung**:
1. Holen Sie einen neuen API-Schlüssel im Azure-Portal → Schlüssel und Endpunkt
2. Exportieren Sie die Umgebungsvariable `AZURE_OPENAI_API_KEY` neu
3. Stellen Sie sicher, dass die Modellbereitstellungen abgeschlossen sind (Azure-Portal prüfen)

### Leistungsprobleme

**Problem**: Langsame Antwortzeiten

**Lösung**:
1. Prüfen Sie OpenAI-Token-Nutzung und Drosselung in Azure-Portal-Metriken
2. Erhöhen Sie die TPM-Kapazität, wenn Limits erreicht werden
3. Erwägen Sie die Verwendung eines höheren Reasoning-Effort-Levels (niedrig/mittel/hoch)

## Infrastruktur aktualisieren

```
infra/
├── main.bicep                       # Main infrastructure definition
├── main.json                        # Compiled ARM template (auto-generated)
├── main.bicepparam                  # Parameter file
├── README.md                        # This file
└── core/
    └── ai/
        └── cognitiveservices.bicep  # Azure OpenAI module
```

## Sicherheitsempfehlungen

1. **API-Schlüssel niemals committen** – Verwenden Sie Umgebungsvariablen
2. **.env-Dateien lokal verwenden** – Fügen Sie `.env` zu `.gitignore` hinzu
3. **Schlüssel regelmäßig rotieren** – Neue Schlüssel im Azure-Portal generieren
4. **Zugriff beschränken** – Verwenden Sie Azure RBAC, um Zugriffsrechte zu steuern
5. **Nutzung überwachen** – Richten Sie Kostenwarnungen im Azure-Portal ein

## Zusätzliche Ressourcen

- [Azure OpenAI Service Dokumentation](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 Modell-Dokumentation](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI Dokumentation](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep Dokumentation](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI Offizielle Integration](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Support

Bei Problemen:
1. Prüfen Sie den [Fehlerbehebungsabschnitt](../../../../01-introduction/infra) oben
2. Überprüfen Sie den Dienststatus von Azure OpenAI im Azure-Portal
3. Öffnen Sie ein Issue im Repository

## Lizenz

Details finden Sie in der Root-[LICENSE](../../../../LICENSE)-Datei.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Haftungsausschluss**:  
Dieses Dokument wurde mit dem KI-Übersetzungsdienst [Co-op Translator](https://github.com/Azure/co-op-translator) übersetzt. Obwohl wir uns um Genauigkeit bemühen, beachten Sie bitte, dass automatisierte Übersetzungen Fehler oder Ungenauigkeiten enthalten können. Das Originaldokument in seiner Ursprungssprache gilt als maßgebliche Quelle. Für wichtige Informationen wird eine professionelle menschliche Übersetzung empfohlen. Wir übernehmen keine Haftung für Missverständnisse oder Fehlinterpretationen, die aus der Nutzung dieser Übersetzung entstehen.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->