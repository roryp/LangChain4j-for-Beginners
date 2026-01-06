<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "22b5d7c8d7585325e38b37fd29eafe25",
  "translation_date": "2026-01-05T21:13:24+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "fr"
}
-->
# Module 00 : Démarrage Rapide

## Table des Matières

- [Introduction](../../../00-quick-start)
- [Qu'est-ce que LangChain4j ?](../../../00-quick-start)
- [Dépendances LangChain4j](../../../00-quick-start)
- [Prérequis](../../../00-quick-start)
- [Configuration](../../../00-quick-start)
  - [1. Obtenez votre token GitHub](../../../00-quick-start)
  - [2. Configurez votre token](../../../00-quick-start)
- [Exécuter les Exemples](../../../00-quick-start)
  - [1. Chat basique](../../../00-quick-start)
  - [2. Modèles de prompt](../../../00-quick-start)
  - [3. Appel de fonctions](../../../00-quick-start)
  - [4. Q&R sur document (RAG)](../../../00-quick-start)
  - [5. IA Responsable](../../../00-quick-start)
- [Ce que chaque exemple montre](../../../00-quick-start)
- [Étapes suivantes](../../../00-quick-start)
- [Dépannage](../../../00-quick-start)

## Introduction

Ce démarrage rapide est conçu pour vous permettre de commencer à utiliser LangChain4j le plus rapidement possible. Il couvre les bases absolues de la création d'applications IA avec LangChain4j et les modèles GitHub. Dans les modules suivants, vous utiliserez Azure OpenAI avec LangChain4j pour construire des applications plus avancées.

## Qu'est-ce que LangChain4j ?

LangChain4j est une bibliothèque Java qui facilite la création d'applications propulsées par l'IA. Plutôt que de gérer des clients HTTP et l'analyse JSON, vous travaillez avec des API Java propres.

La "chaîne" dans LangChain fait référence au chaînage de plusieurs composants - vous pouvez chaîner un prompt à un modèle puis à un parseur, ou chaîner plusieurs appels IA où une sortie alimente la prochaine entrée. Ce démarrage rapide se concentre sur les fondamentaux avant d'explorer des chaînes plus complexes.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1.fr.png" alt="Concept de Chaînage LangChain4j" width="800"/>

*Chaînage des composants dans LangChain4j - des blocs de construction connectés pour créer des workflows IA puissants*

Nous utiliserons trois composants principaux :

**ChatLanguageModel** - L'interface pour les interactions avec le modèle IA. Appelez `model.chat("prompt")` et obtenez une réponse sous forme de chaîne. Nous utilisons `OpenAiOfficialChatModel` qui fonctionne avec les endpoints compatibles OpenAI comme les modèles GitHub.

**AiServices** - Crée des interfaces de services IA typées. Définissez des méthodes, annotez-les avec `@Tool`, et LangChain4j gère l'orchestration. L'IA appelle automatiquement vos méthodes Java quand nécessaire.

**MessageWindowChatMemory** - Maintient l'historique de la conversation. Sans cela, chaque requête est indépendante. Avec, l'IA se souvient des messages précédents et conserve le contexte sur plusieurs échanges.

<img src="../../../translated_images/architecture.eedc993a1c576839.fr.png" alt="Architecture LangChain4j" width="800"/>

*Architecture LangChain4j - composants centraux collaborant pour alimenter vos applications IA*

## Dépendances LangChain4j

Ce démarrage rapide utilise deux dépendances Maven dans le [`pom.xml`](../../../00-quick-start/pom.xml) :

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

Le module `langchain4j-open-ai-official` fournit la classe `OpenAiOfficialChatModel` qui se connecte aux APIs compatibles OpenAI. Les modèles GitHub utilisent le même format d’API, donc aucun adaptateur spécial n’est nécessaire - il suffit de pointer l’URL de base vers `https://models.github.ai/inference`.

## Prérequis

**Utilisez-vous le conteneur de développement ?** Java et Maven sont déjà installés. Vous avez seulement besoin d'un jeton d'accès personnel GitHub.

**Développement local :**
- Java 21+, Maven 3.9+
- Jeton d'accès personnel GitHub (instructions ci-dessous)

> **Note :** Ce module utilise `gpt-4.1-nano` des modèles GitHub. Ne modifiez pas le nom du modèle dans le code - il est configuré pour fonctionner avec les modèles disponibles sur GitHub.

## Configuration

### 1. Obtenez votre token GitHub

1. Allez sur [Paramètres GitHub → Jetons d’accès personnel](https://github.com/settings/personal-access-tokens)
2. Cliquez sur « Générer un nouveau jeton »
3. Mettez un nom descriptif (ex. « Démo LangChain4j »)
4. Définissez une expiration (7 jours recommandé)
5. Sous « Permissions du compte », trouvez « Models » et définissez en « Lecture seule »
6. Cliquez sur « Générer le jeton »
7. Copiez et sauvegardez votre jeton – vous ne le verrez plus

### 2. Configurez votre token

**Option 1 : Utiliser VS Code (recommandé)**

Si vous utilisez VS Code, ajoutez votre token dans le fichier `.env` à la racine du projet :

Si le fichier `.env` n’existe pas, copiez `.env.example` vers `.env` ou créez un nouveau fichier `.env` à la racine du projet.

**Exemple de fichier `.env` :**
```bash
# Dans /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Vous pouvez ensuite simplement faire un clic droit sur n’importe quel fichier de démonstration (ex. `BasicChatDemo.java`) dans l’Explorateur et sélectionner **« Run Java »** ou utiliser les configurations de lancement dans le panneau Exécuter et Déboguer.

**Option 2 : Utiliser le terminal**

Définissez le token comme variable d’environnement :

**Bash :**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell :**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Exécuter les Exemples

**Avec VS Code :** Cliquez droit sur un fichier démo dans l’Explorateur et choisissez **« Run Java »**, ou utilisez les configurations de lancement dans le panneau Exécuter et Déboguer (assurez-vous d’avoir ajouté votre token dans `.env` avant).

**Avec Maven :** Vous pouvez aussi exécuter en ligne de commande :

### 1. Chat basique

**Bash :**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell :**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Modèles de prompt

**Bash :**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell :**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Montre les approches zero-shot, few-shot, chaîne de pensée, et prompt basé sur les rôles.

### 3. Appel de fonctions

**Bash :**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell :**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

L’IA appelle automatiquement vos méthodes Java quand nécessaire.

### 4. Q&R sur document (RAG)

**Bash :**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell :**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Posez des questions sur le contenu de `document.txt`.

### 5. IA Responsable

**Bash :**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

**PowerShell :**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

Voyez comment les filtres de sécurité IA bloquent les contenus nuisibles.

## Ce que chaque exemple montre

**Chat basique** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Commencez ici pour voir LangChain4j dans sa forme la plus simple. Vous créerez un `OpenAiOfficialChatModel`, enverrez un prompt avec `.chat()`, et recevrez une réponse. Cela montre les bases : comment initialiser les modèles avec des endpoints et clés API personnalisés. Une fois ce modèle compris, tout le reste se construit dessus.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Essayez avec [GitHub Copilot](https://github.com/features/copilot) Chat :** Ouvrez [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) et demandez :
> - « Comment passer de GitHub Models à Azure OpenAI dans ce code ? »
> - « Quels autres paramètres puis-je configurer dans OpenAiOfficialChatModel.builder() ? »
> - « Comment ajouter la diffusion en continu des réponses au lieu d’attendre la réponse complète ? »

**Conception de prompt** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Maintenant que vous savez comment parler à un modèle, explorons ce que vous lui dites. Cette démo utilise la même configuration de modèle mais montre quatre modèles de prompt différents. Essayez les prompts zero-shot pour des instructions directes, few-shot qui apprennent d’exemples, chaîne de pensée qui révèlent les étapes de raisonnement, et prompts basés sur un rôle qui définissent le contexte. Vous verrez comment un même modèle donne des résultats très différents selon la formulation de la demande.

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

> **🤖 Essayez avec [GitHub Copilot](https://github.com/features/copilot) Chat :** Ouvrez [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) et demandez :
> - « Quelle est la différence entre zero-shot et few-shot prompting, et quand utiliser chacun ? »
> - « Comment le paramètre température affecte-t-il les réponses du modèle ? »
> - « Quelles techniques existent pour prévenir les attaques par injection de prompt en production ? »
> - « Comment créer des objets PromptTemplate réutilisables pour des patterns courants ? »

**Intégration d’outils** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

C’est ici que LangChain4j devient puissant. Vous utiliserez `AiServices` pour créer un assistant IA capable d’appeler vos méthodes Java. Il suffit d’annoter les méthodes avec `@Tool("description")` et LangChain4j s’occupe du reste - l’IA décide automatiquement quand utiliser chaque outil selon les requêtes utilisateur. Ceci illustre l’appel de fonctions, une technique clé pour bâtir une IA capable d’agir, pas juste de répondre.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Essayez avec [GitHub Copilot](https://github.com/features/copilot) Chat :** Ouvrez [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) et demandez :
> - « Comment fonctionne l’annotation @Tool et que fait LangChain4j en coulisses avec celle-ci ? »
> - « L’IA peut-elle appeler plusieurs outils en séquence pour résoudre des problèmes complexes ? »
> - « Que se passe-t-il si un outil génère une exception - comment gérer les erreurs ? »
> - « Comment intégrer une vraie API à la place de cet exemple de calculatrice ? »

**Q&R sur document (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Ici, vous verrez les bases du RAG (retrieval-augmented generation). Au lieu de s’appuyer sur les données d’entraînement du modèle, vous chargez le contenu du fichier [`document.txt`](../../../00-quick-start/document.txt) et l’incluez dans le prompt. L’IA répond en se basant sur votre document, pas sur ses connaissances générales. C’est la première étape pour construire des systèmes capables de travailler avec vos propres données.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Note :** Cette approche simple charge tout le document dans le prompt. Pour de gros fichiers (>10 Ko), vous dépasserez les limites de contexte. Le module 03 couvre la segmentation et la recherche vectorielle pour les systèmes RAG en production.

> **🤖 Essayez avec [GitHub Copilot](https://github.com/features/copilot) Chat :** Ouvrez [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) et demandez :
> - « Comment le RAG empêche-t-il les hallucinations IA comparé à l’utilisation des données d’entraînement du modèle ? »
> - « Quelle est la différence entre cette approche simple et l’utilisation d’embeddings vectoriels pour la recherche ? »
> - « Comment scaler ceci pour gérer plusieurs documents ou bases de connaissances plus larges ? »
> - « Quelles sont les bonnes pratiques pour structurer le prompt afin que l’IA utilise uniquement le contexte fourni ? »

**IA Responsable** - [ResponsibleAIDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)

Construisez la sécurité IA avec une défense en profondeur. Cette démo montre deux couches de protection qui fonctionnent ensemble :

**Partie 1 : LangChain4j Input Guardrails** - Bloque les prompts dangereux avant qu’ils n’atteignent le LLM. Créez des garde-fous personnalisés qui vérifient les mots-clés ou motifs interdits. Ceux-ci s’exécutent dans votre code, donc ils sont rapides et gratuits.

```java
class DangerousContentGuardrail implements InputGuardrail {
    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        String text = userMessage.singleText().toLowerCase();
        if (text.contains("explosives")) {
            return fatal("Blocked: contains prohibited keyword");
        }
        return success();
    }
}
```

**Partie 2 : Filtres de sécurité du fournisseur** - Les modèles GitHub intègrent des filtres qui captent ce que vos garde-fous peuvent manquer. Vous verrez des blocages durs (erreurs HTTP 400) pour violations graves et des refus souples où l’IA décline poliment.

> **🤖 Essayez avec [GitHub Copilot](https://github.com/features/copilot) Chat :** Ouvrez [`ResponsibleAIDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java) et demandez :
> - « Qu’est-ce que InputGuardrail et comment en créer un personnalisé ? »
> - « Quelle est la différence entre un blocage dur et un refus souple ? »
> - « Pourquoi utiliser à la fois des garde-fous et des filtres fournisseurs ? »

## Étapes suivantes

**Module suivant :** [01-introduction - Premiers pas avec LangChain4j et gpt-5 sur Azure](../01-introduction/README.md)

---

**Navigation :** [← Retour au principal](../README.md) | [Suivant : Module 01 - Introduction →](../01-introduction/README.md)

---

## Dépannage

### Première compilation Maven

**Problème :** La commande initiale `mvn clean compile` ou `mvn package` prend beaucoup de temps (10-15 minutes)

**Cause :** Maven doit télécharger toutes les dépendances du projet (Spring Boot, bibliothèques LangChain4j, SDK Azure, etc.) lors de la première compilation.

**Solution :** C’est un comportement normal. Les compilations suivantes seront beaucoup plus rapides car les dépendances sont mises en cache localement. Le temps de téléchargement dépend de la vitesse de votre connexion.

### Syntaxe des commandes Maven sous PowerShell

**Problème :** Les commandes Maven échouent avec l’erreur `Unknown lifecycle phase ".mainClass=..."`

**Cause :** PowerShell interprète `=` comme opérateur d’affectation de variable, ce qui perturbe la syntaxe des propriétés Maven.
**Solution** : Utilisez l’opérateur d’arrêt d’interprétation `--%` avant la commande Maven :

**PowerShell :**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash :**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

L’opérateur `--%` indique à PowerShell de transmettre tous les arguments restants littéralement à Maven sans les interpréter.

### Affichage des emojis dans Windows PowerShell

**Problème** : Les réponses de l’IA affichent des caractères illisibles (ex. : `????` ou `â??`) au lieu des emojis dans PowerShell

**Cause** : L’encodage par défaut de PowerShell ne prend pas en charge les emojis UTF-8

**Solution** : Exécutez cette commande avant de lancer les applications Java :
```cmd
chcp 65001
```

Cela force l’encodage UTF-8 dans le terminal. Vous pouvez également utiliser Windows Terminal qui offre un meilleur support Unicode.

### Débogage des appels API

**Problème** : Erreurs d’authentification, limites de taux ou réponses inattendues du modèle d’IA

**Solution** : Les exemples incluent `.logRequests(true)` et `.logResponses(true)` pour afficher les appels API dans la console. Cela aide à résoudre les erreurs d’authentification, les limites de taux ou les réponses inattendues. Supprimez ces indicateurs en production pour réduire le bruit des journaux.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Avertissement** :  
Ce document a été traduit à l’aide du service de traduction automatique [Co-op Translator](https://github.com/Azure/co-op-translator). Bien que nous nous efforcions d’assurer l’exactitude, veuillez noter que les traductions automatiques peuvent contenir des erreurs ou des inexactitudes. Le document original dans sa langue d’origine doit être considéré comme la source officielle. Pour les informations critiques, une traduction professionnelle humaine est recommandée. Nous déclinons toute responsabilité en cas de malentendus ou d’interprétations erronées résultant de l’utilisation de cette traduction.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->