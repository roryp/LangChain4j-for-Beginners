<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c25ec1f10ef156c53e190cdf8b0711ab",
  "translation_date": "2025-12-13T17:34:33+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "fr"
}
-->
# Module 05 : Protocole de Contexte de Modèle (MCP)

## Table des matières

- [Ce que vous apprendrez](../../../05-mcp)
- [Comprendre MCP](../../../05-mcp)
- [Comment fonctionne MCP](../../../05-mcp)
  - [Architecture Serveur-Client](../../../05-mcp)
  - [Découverte des outils](../../../05-mcp)
  - [Mécanismes de transport](../../../05-mcp)
- [Prérequis](../../../05-mcp)
- [Ce que couvre ce module](../../../05-mcp)
- [Démarrage rapide](../../../05-mcp)
  - [Exemple 1 : Calculatrice distante (HTTP Streamable)](../../../05-mcp)
  - [Exemple 2 : Opérations sur fichiers (Stdio)](../../../05-mcp)
  - [Exemple 3 : Analyse Git (Docker)](../../../05-mcp)
- [Concepts clés](../../../05-mcp)
  - [Sélection du transport](../../../05-mcp)
  - [Découverte des outils](../../../05-mcp)
  - [Gestion des sessions](../../../05-mcp)
  - [Considérations multiplateformes](../../../05-mcp)
- [Quand utiliser MCP](../../../05-mcp)
- [Écosystème MCP](../../../05-mcp)
- [Félicitations !](../../../05-mcp)
  - [Et ensuite ?](../../../05-mcp)
- [Dépannage](../../../05-mcp)

## Ce que vous apprendrez

Vous avez construit une IA conversationnelle, maîtrisé les prompts, ancré les réponses dans des documents, et créé des agents avec des outils. Mais tous ces outils étaient conçus sur mesure pour votre application spécifique. Et si vous pouviez donner à votre IA accès à un écosystème standardisé d’outils que tout le monde peut créer et partager ?

Le Protocole de Contexte de Modèle (MCP) offre exactement cela - une manière standard pour les applications IA de découvrir et d’utiliser des outils externes. Au lieu d’écrire des intégrations personnalisées pour chaque source de données ou service, vous vous connectez à des serveurs MCP qui exposent leurs capacités dans un format cohérent. Votre agent IA peut alors découvrir et utiliser ces outils automatiquement.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5448d2fa21a61218777ceb8010ea0390dd43924b26df35f61.fr.png" alt="Comparaison MCP" width="800"/>

*Avant MCP : intégrations point à point complexes. Après MCP : un protocole, des possibilités infinies.*

## Comprendre MCP

MCP résout un problème fondamental dans le développement IA : chaque intégration est personnalisée. Vous voulez accéder à GitHub ? Code personnalisé. Vous voulez lire des fichiers ? Code personnalisé. Vous voulez interroger une base de données ? Code personnalisé. Et aucune de ces intégrations ne fonctionne avec d’autres applications IA.

MCP standardise cela. Un serveur MCP expose des outils avec des descriptions claires et des schémas. Tout client MCP peut se connecter, découvrir les outils disponibles, et les utiliser. Construisez une fois, utilisez partout.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9814b7cffade208d4b0d97203c22df8d8e5504d8238fa7065.fr.png" alt="Architecture MCP" width="800"/>

*Architecture du Protocole de Contexte de Modèle - découverte et exécution d’outils standardisées*

## Comment fonctionne MCP

**Architecture Serveur-Client**

MCP utilise un modèle client-serveur. Les serveurs fournissent des outils - lecture de fichiers, interrogation de bases de données, appels d’API. Les clients (votre application IA) se connectent aux serveurs et utilisent leurs outils.

**Découverte des outils**

Quand votre client se connecte à un serveur MCP, il demande « Quels outils avez-vous ? » Le serveur répond avec une liste d’outils disponibles, chacun avec des descriptions et des schémas de paramètres. Votre agent IA peut alors décider quels outils utiliser selon les demandes de l’utilisateur.

**Mécanismes de transport**

MCP définit deux mécanismes de transport : HTTP pour les serveurs distants, Stdio pour les processus locaux (y compris les conteneurs Docker) :

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020ed801b772b26ed69338e22739677aa017e0968f6538b09a2.fr.png" alt="Mécanismes de transport" width="800"/>

*Mécanismes de transport MCP : HTTP pour serveurs distants, Stdio pour processus locaux (y compris conteneurs Docker)*

**Streamable HTTP** - [StreamableHttpDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java)

Pour serveurs distants. Votre application fait des requêtes HTTP à un serveur quelque part sur le réseau. Utilise Server-Sent Events pour la communication en temps réel.

```java
McpTransport httpTransport = new StreamableHttpMcpTransport.Builder()
    .url("http://localhost:3001/mcp")
    .timeout(Duration.ofSeconds(60))
    .logRequests(true)
    .logResponses(true)
    .build();
```

> **🤖 Essayez avec [GitHub Copilot](https://github.com/features/copilot) Chat :** Ouvrez [`StreamableHttpDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java) et demandez :
> - « En quoi MCP diffère-t-il de l’intégration directe d’outils comme dans le Module 04 ? »
> - « Quels sont les avantages d’utiliser MCP pour le partage d’outils entre applications ? »
> - « Comment gérer les échecs de connexion ou les délais d’attente aux serveurs MCP ? »

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Pour processus locaux. Votre application lance un serveur en sous-processus et communique via l’entrée/sortie standard. Utile pour l’accès au système de fichiers ou les outils en ligne de commande.

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

> **🤖 Essayez avec [GitHub Copilot](https://github.com/features/copilot) Chat :** Ouvrez [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) et demandez :
> - « Comment fonctionne le transport Stdio et quand devrais-je l’utiliser plutôt que HTTP ? »
> - « Comment LangChain4j gère-t-il le cycle de vie des processus serveurs MCP lancés ? »
> - « Quelles sont les implications de sécurité à donner à l’IA l’accès au système de fichiers ? »

**Docker (utilise Stdio)** - [GitRepositoryAnalyzer.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java)

Pour services conteneurisés. Utilise le transport stdio pour communiquer avec un conteneur Docker via `docker run`. Utile pour des dépendances complexes ou des environnements isolés.

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

> **🤖 Essayez avec [GitHub Copilot](https://github.com/features/copilot) Chat :** Ouvrez [`GitRepositoryAnalyzer.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java) et demandez :
> - « Comment le transport Docker isole-t-il les serveurs MCP et quels en sont les avantages ? »
> - « Comment configurer les montages de volumes pour partager des données entre l’hôte et les conteneurs MCP ? »
> - « Quelles sont les bonnes pratiques pour gérer le cycle de vie des serveurs MCP basés sur Docker en production ? »

## Exécution des exemples

### Prérequis

- Java 21+, Maven 3.9+
- Node.js 16+ et npm (pour les serveurs MCP)
- **Docker Desktop** - Doit être **EN FONCTIONNEMENT** pour l’Exemple 3 (pas seulement installé)
- Jeton d’accès personnel GitHub configuré dans le fichier `.env` (depuis le Module 00)

> **Note :** Si vous n’avez pas encore configuré votre jeton GitHub, consultez [Module 00 - Démarrage rapide](../00-quick-start/README.md) pour les instructions.

> **⚠️ Utilisateurs Docker :** Avant d’exécuter l’Exemple 3, vérifiez que Docker Desktop fonctionne avec `docker ps`. Si vous voyez des erreurs de connexion, démarrez Docker Desktop et attendez environ 30 secondes pour l’initialisation.

## Démarrage rapide

**Avec VS Code :** Faites un clic droit sur n’importe quel fichier de démonstration dans l’Explorateur et sélectionnez **« Run Java »**, ou utilisez les configurations de lancement dans le panneau Exécuter et Déboguer (assurez-vous d’avoir ajouté votre jeton dans le fichier `.env` au préalable).

**Avec Maven :** Vous pouvez aussi lancer depuis la ligne de commande avec les exemples ci-dessous.

**⚠️ Important :** Certains exemples ont des prérequis (comme démarrer un serveur MCP ou construire des images Docker). Vérifiez les exigences de chaque exemple avant de lancer.

### Exemple 1 : Calculatrice distante (HTTP Streamable)

Cela démontre l’intégration d’outils via réseau.

**⚠️ Prérequis :** Vous devez d’abord démarrer le serveur MCP (voir Terminal 1 ci-dessous).

**Terminal 1 - Démarrer le serveur MCP :**

**Bash :**
```bash
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**PowerShell :**
```powershell
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**Terminal 2 - Exécuter l’exemple :**

**Avec VS Code :** Faites un clic droit sur `StreamableHttpDemo.java` et sélectionnez **« Run Java »**.

**Avec Maven :**

**Bash :**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**PowerShell :**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

Observez l’agent découvrir les outils disponibles, puis utiliser la calculatrice pour effectuer une addition.

### Exemple 2 : Opérations sur fichiers (Stdio)

Cela démontre des outils basés sur des sous-processus locaux.

**✅ Aucun prérequis nécessaire** - le serveur MCP est lancé automatiquement.

**Avec VS Code :** Faites un clic droit sur `StdioTransportDemo.java` et sélectionnez **« Run Java »**.

**Avec Maven :**

**Bash :**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**PowerShell :**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

L’application lance automatiquement un serveur MCP système de fichiers et lit un fichier local. Remarquez comment la gestion du sous-processus est prise en charge pour vous.

**Sortie attendue :**
```
Assistant response: The content of the file is "Kaboom!".
```

### Exemple 3 : Analyse Git (Docker)

Cela démontre des serveurs d’outils conteneurisés.

**⚠️ Prérequis :** 
1. **Docker Desktop doit être EN FONCTIONNEMENT** (pas seulement installé)
2. **Utilisateurs Windows :** mode WSL 2 recommandé (Paramètres Docker Desktop → Général → « Utiliser le moteur basé sur WSL 2 »). Le mode Hyper-V nécessite une configuration manuelle du partage de fichiers.
3. Vous devez construire l’image Docker d’abord (voir Terminal 1 ci-dessous)

**Vérifiez que Docker fonctionne :**

**Bash :**
```bash
docker ps  # Devrait afficher la liste des conteneurs, pas une erreur
```

**PowerShell :**
```powershell
docker ps  # Devrait afficher la liste des conteneurs, pas une erreur
```

Si vous voyez une erreur comme « Cannot connect to Docker daemon » ou « The system cannot find the file specified », démarrez Docker Desktop et attendez son initialisation (~30 secondes).

**Dépannage :**
- Si l’IA signale un dépôt vide ou aucun fichier, le montage de volume (`-v`) ne fonctionne pas.
- **Utilisateurs Windows Hyper-V :** Ajoutez le répertoire du projet dans Paramètres Docker Desktop → Ressources → Partage de fichiers, puis redémarrez Docker Desktop.
- **Solution recommandée :** Passez en mode WSL 2 pour un partage de fichiers automatique (Paramètres → Général → activer « Utiliser le moteur basé sur WSL 2 »).

**Terminal 1 - Construire l’image Docker :**

**Bash :**
```bash
cd servers/src/git
docker build -t mcp/git .
```

**PowerShell :**
```powershell
cd servers/src/git
docker build -t mcp/git .
```

**Terminal 2 - Lancer l’analyseur :**

**Avec VS Code :** Faites un clic droit sur `GitRepositoryAnalyzer.java` et sélectionnez **« Run Java »**.

**Avec Maven :**

**Bash :**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

**PowerShell :**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

L’application lance un conteneur Docker, monte votre dépôt, et analyse la structure et le contenu du dépôt via l’agent IA.

## Concepts clés

**Sélection du transport**

Choisissez selon l’emplacement de vos outils :
- Services distants → HTTP Streamable
- Système de fichiers local → Stdio
- Dépendances complexes → Docker

**Découverte des outils**

Les clients MCP découvrent automatiquement les outils disponibles lors de la connexion. Votre agent IA voit les descriptions des outils et décide lesquels utiliser selon la demande de l’utilisateur.

**Gestion des sessions**

Le transport HTTP Streamable maintient des sessions, permettant des interactions avec état avec les serveurs distants. Les transports Stdio et Docker sont généralement sans état.

**Considérations multiplateformes**

Les exemples gèrent automatiquement les différences de plateforme (différences de commandes Windows vs Unix, conversions de chemins pour Docker). Ceci est important pour les déploiements en production sur différents environnements.

## Quand utiliser MCP

**Utilisez MCP lorsque :**
- Vous voulez exploiter des écosystèmes d’outils existants
- Vous construisez des outils que plusieurs applications utiliseront
- Vous intégrez des services tiers avec des protocoles standard
- Vous devez pouvoir changer les implémentations d’outils sans modifier le code

**Utilisez des outils personnalisés (Module 04) lorsque :**
- Vous développez des fonctionnalités spécifiques à l’application
- La performance est critique (MCP ajoute une surcharge)
- Vos outils sont simples et ne seront pas réutilisés
- Vous avez besoin d’un contrôle complet sur l’exécution


## Écosystème MCP

Le Protocole de Contexte de Modèle est une norme ouverte avec un écosystème en croissance :

- Serveurs MCP officiels pour tâches courantes (système de fichiers, Git, bases de données)
- Serveurs communautaires pour divers services
- Descriptions et schémas d’outils standardisés
- Compatibilité inter-framework (fonctionne avec tout client MCP)

Cette standardisation signifie que les outils construits pour une application IA fonctionnent avec d’autres, créant un écosystème partagé de capacités.

## Félicitations !

Vous avez terminé le cours LangChain4j pour débutants. Vous avez appris :

- Comment construire une IA conversationnelle avec mémoire (Module 01)
- Les patterns d’ingénierie de prompt pour différentes tâches (Module 02)
- Ancrer les réponses dans vos documents avec RAG (Module 03)
- Créer des agents IA avec des outils personnalisés (Module 04)
- Intégrer des outils standardisés via MCP (Module 05)

Vous avez maintenant les bases pour construire des applications IA en production. Les concepts appris s’appliquent indépendamment des frameworks ou modèles spécifiques - ce sont des patterns fondamentaux en ingénierie IA.

### Et ensuite ?

Après avoir terminé les modules, explorez le [Guide de test](../docs/TESTING.md) pour voir les concepts de test LangChain4j en action.

**Ressources officielles :**
- [Documentation LangChain4j](https://docs.langchain4j.dev/) - Guides complets et référence API
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Code source et exemples
- [Tutoriels LangChain4j](https://docs.langchain4j.dev/tutorials/) - Tutoriels pas à pas pour divers cas d’usage

Merci d’avoir suivi ce cours !

---

**Navigation :** [← Précédent : Module 04 - Outils](../04-tools/README.md) | [Retour au principal](../README.md)

---

## Dépannage

### Syntaxe de commande Maven PowerShell
**Problème** : Les commandes Maven échouent avec l'erreur `Unknown lifecycle phase ".mainClass=..."`

**Cause** : PowerShell interprète `=` comme un opérateur d'affectation de variable, ce qui casse la syntaxe des propriétés Maven

**Solution** : Utilisez l'opérateur d'arrêt d'analyse `--%` avant la commande Maven :

**PowerShell :**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**Bash :**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

L'opérateur `--%` indique à PowerShell de passer tous les arguments restants littéralement à Maven sans interprétation.

### Problèmes de connexion Docker

**Problème** : Les commandes Docker échouent avec "Cannot connect to Docker daemon" ou "The system cannot find the file specified"

**Cause** : Docker Desktop n'est pas lancé ou pas complètement initialisé

**Solution** : 
1. Démarrez Docker Desktop
2. Attendez environ 30 secondes pour une initialisation complète
3. Vérifiez avec `docker ps` (devrait afficher la liste des conteneurs, pas une erreur)
4. Puis exécutez votre exemple

### Montage de volume Docker sous Windows

**Problème** : L'analyseur de dépôt Git signale un dépôt vide ou aucun fichier

**Cause** : Le montage de volume (`-v`) ne fonctionne pas à cause de la configuration du partage de fichiers

**Solution** :
- **Recommandé :** Passez en mode WSL 2 (Paramètres Docker Desktop → Général → "Use the WSL 2 based engine")
- **Alternative (Hyper-V) :** Ajoutez le répertoire du projet dans Paramètres Docker Desktop → Ressources → Partage de fichiers, puis redémarrez Docker Desktop

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Avertissement** :  
Ce document a été traduit à l’aide du service de traduction automatique [Co-op Translator](https://github.com/Azure/co-op-translator). Bien que nous nous efforcions d’assurer l’exactitude, veuillez noter que les traductions automatiques peuvent contenir des erreurs ou des inexactitudes. Le document original dans sa langue d’origine doit être considéré comme la source faisant foi. Pour les informations critiques, une traduction professionnelle réalisée par un humain est recommandée. Nous déclinons toute responsabilité en cas de malentendus ou de mauvaises interprétations résultant de l’utilisation de cette traduction.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->