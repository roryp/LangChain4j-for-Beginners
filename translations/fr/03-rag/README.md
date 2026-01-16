<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "81d087662fb3dd7b7124bce1a9c9ec86",
  "translation_date": "2026-01-05T21:14:57+00:00",
  "source_file": "03-rag/README.md",
  "language_code": "fr"
}
-->
# Module 03 : RAG (Génération Augmentée par Recherche)

## Table des matières

- [Ce que vous apprendrez](../../../03-rag)
- [Prérequis](../../../03-rag)
- [Comprendre le RAG](../../../03-rag)
- [Comment cela fonctionne](../../../03-rag)
  - [Traitement des documents](../../../03-rag)
  - [Création d'embeddings](../../../03-rag)
  - [Recherche sémantique](../../../03-rag)
  - [Génération de réponse](../../../03-rag)
- [Lancer l'application](../../../03-rag)
- [Utilisation de l'application](../../../03-rag)
  - [Télécharger un document](../../../03-rag)
  - [Poser des questions](../../../03-rag)
  - [Vérifier les références sources](../../../03-rag)
  - [Expérimenter avec des questions](../../../03-rag)
- [Concepts clés](../../../03-rag)
  - [Stratégie de découpage](../../../03-rag)
  - [Scores de similarité](../../../03-rag)
  - [Stockage en mémoire](../../../03-rag)
  - [Gestion de la fenêtre de contexte](../../../03-rag)
- [Quand le RAG est important](../../../03-rag)
- [Étapes suivantes](../../../03-rag)

## Ce que vous apprendrez

Dans les modules précédents, vous avez appris comment converser avec l’IA et structurer efficacement vos invites. Mais il existe une limitation fondamentale : les modèles de langage ne connaissent que ce qu’ils ont appris lors de leur entraînement. Ils ne peuvent pas répondre aux questions concernant les politiques de votre entreprise, votre documentation de projet, ou toute information sur laquelle ils n’ont pas été entraînés.

Le RAG (Génération Augmentée par Recherche) résout ce problème. Au lieu d’essayer d’enseigner vos informations au modèle (ce qui est coûteux et peu pratique), vous lui donnez la capacité de rechercher dans vos documents. Lorsqu'une question est posée, le système trouve les informations pertinentes et les inclut dans l’invite. Le modèle répond ensuite en se basant sur ce contexte récupéré.

Pensez au RAG comme donnant au modèle une bibliothèque de référence. Quand vous posez une question, le système :

1. **Requête utilisateur** - Vous posez une question  
2. **Embedding** - Convertit votre question en vecteur  
3. **Recherche vectorielle** - Trouve des morceaux de documents similaires  
4. **Assemblage du contexte** - Ajoute les morceaux pertinents à l’invite  
5. **Réponse** - Le LLM génère une réponse basée sur le contexte  

Cela ancre les réponses du modèle dans vos données réelles au lieu de dépendre de sa connaissance d’entraînement ou d’inventer des réponses.

<img src="../../../translated_images/fr/rag-architecture.ccb53b71a6ce407f.webp" alt="Architecture du RAG" width="800"/>

*Flux de travail RAG - de la requête utilisateur à la recherche sémantique jusqu’à la génération de réponse contextuelle*

## Prérequis

- Module 01 terminé (ressources Azure OpenAI déployées)  
- Fichier `.env` dans le répertoire racine avec les identifiants Azure (créé par `azd up` dans le Module 01)

> **Note :** Si vous n'avez pas terminé le Module 01, suivez d'abord les instructions de déploiement de ce module.

## Comment cela fonctionne

### Traitement des documents

[DocumentService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java)  

Lorsque vous téléchargez un document, le système le découpe en morceaux – des parties plus petites qui tiennent confortablement dans la fenêtre de contexte du modèle. Ces morceaux se chevauchent légèrement pour ne pas perdre le contexte aux frontières.

```java
Document document = FileSystemDocumentLoader.loadDocument("sample-document.txt");

DocumentSplitter splitter = DocumentSplitters
    .recursive(300, 30, new OpenAiTokenizer());

List<TextSegment> segments = splitter.split(document);
```
  
> **🤖 Essayez avec [GitHub Copilot](https://github.com/features/copilot) Chat :** Ouvrez [`DocumentService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/DocumentService.java) et demandez :  
> - « Comment LangChain4j découpe-t-il les documents en morceaux et pourquoi le chevauchement est-il important ? »  
> - « Quelle est la taille optimale des morceaux pour différents types de documents et pourquoi ? »  
> - « Comment gérer les documents multilingues ou à formatage spécial ? »

### Création d'embeddings

[LangChainRagConfig.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/config/LangChainRagConfig.java)  

Chaque morceau est converti en une représentation numérique appelée embedding – essentiellement une empreinte mathématique qui capture le sens du texte. Les textes similaires produisent des embeddings similaires.

```java
@Bean
public EmbeddingModel embeddingModel() {
    return OpenAiOfficialEmbeddingModel.builder()
        .baseUrl(azureOpenAiEndpoint)
        .apiKey(azureOpenAiKey)
        .modelName(azureEmbeddingDeploymentName)
        .build();
}

EmbeddingStore<TextSegment> embeddingStore = 
    new InMemoryEmbeddingStore<>();
```
  
<img src="../../../translated_images/fr/vector-embeddings.2ef7bdddac79a327.webp" alt="Espace des embeddings vectoriels" width="800"/>

*Documents représentés comme vecteurs dans l’espace des embeddings – contenu similaire se regroupe*

### Recherche sémantique

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)  

Quand vous posez une question, celle-ci est aussi convertie en embedding. Le système compare l’embedding de votre question avec tous les embeddings des morceaux de documents. Il trouve les morceaux aux significations les plus proches – pas seulement l’appui sur des mots-clés, mais une similarité sémantique réelle.

```java
Embedding queryEmbedding = embeddingModel.embed(question).content();

List<EmbeddingMatch<TextSegment>> matches = 
    embeddingStore.findRelevant(queryEmbedding, 5, 0.7);

for (EmbeddingMatch<TextSegment> match : matches) {
    String relevantText = match.embedded().text();
    double score = match.score();
}
```
  
> **🤖 Essayez avec [GitHub Copilot](https://github.com/features/copilot) Chat :** Ouvrez [`RagService.java`](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java) et demandez :  
> - « Comment fonctionne la recherche par similarité avec les embeddings et qu'est-ce qui détermine le score ? »  
> - « Quel seuil de similarité dois-je utiliser et comment cela affecte-t-il les résultats ? »  
> - « Comment gérer les cas où aucun document pertinent n’est trouvé ? »

### Génération de réponse

[RagService.java](../../../03-rag/src/main/java/com/example/langchain4j/rag/service/RagService.java)  

Les morceaux les plus pertinents sont inclus dans l’invite envoyée au modèle. Le modèle lit ces morceaux spécifiques et répond en fonction de ces informations. Cela empêche les hallucinations – le modèle ne peut répondre que sur ce qui lui est fourni.

## Lancer l'application

**Vérifier le déploiement :**  

Assurez-vous que le fichier `.env` existe dans le répertoire racine avec les identifiants Azure (créé pendant le Module 01) :  
```bash
cat ../.env  # Devrait afficher AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```
  
**Démarrer l'application :**

> **Note :** Si vous avez déjà lancé toutes les applications avec `./start-all.sh` du Module 01, ce module est déjà en cours d’exécution sur le port 8081. Vous pouvez ignorer les commandes de démarrage ci-dessous et aller directement sur http://localhost:8081.

**Option 1 : Utiliser Spring Boot Dashboard (recommandé pour les utilisateurs VS Code)**  

Le conteneur de développement inclut l’extension Spring Boot Dashboard, qui offre une interface visuelle pour gérer toutes les applications Spring Boot. Vous pouvez la trouver dans la barre d’activités à gauche de VS Code (cherchez l’icône Spring Boot).

Depuis Spring Boot Dashboard, vous pouvez :  
- Voir toutes les applications Spring Boot disponibles dans l’espace de travail  
- Démarrer/arrêter les applications en un clic  
- Visualiser les logs des applications en temps réel  
- Surveiller l’état des applications  

Cliquez simplement sur le bouton play à côté de « rag » pour démarrer ce module, ou démarrez tous les modules en une fois.

<img src="../../../translated_images/fr/dashboard.fbe6e28bf4267ffe.webp" alt="Spring Boot Dashboard" width="400"/>

**Option 2 : Utiliser les scripts shell**

Démarrez toutes les applications web (modules 01-04) :

**Bash :**  
```bash
cd ..  # Depuis le répertoire racine
./start-all.sh
```
  
**PowerShell :**  
```powershell
cd ..  # Depuis le répertoire racine
.\start-all.ps1
```
  
Ou démarrez uniquement ce module :

**Bash :**  
```bash
cd 03-rag
./start.sh
```
  
**PowerShell :**  
```powershell
cd 03-rag
.\start.ps1
```
  
Les deux scripts chargent automatiquement les variables d’environnement depuis le fichier `.env` racine et construisent les fichiers JAR s’ils n’existent pas.

> **Note :** Si vous préférez construire tous les modules manuellement avant de démarrer :  
>  
> **Bash :**  
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
  
> **PowerShell :**  
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
  
Ouvrez http://localhost:8081 dans votre navigateur.

**Pour arrêter :**

**Bash :**  
```bash
./stop.sh  # Ce module seulement
# Ou
cd .. && ./stop-all.sh  # Tous les modules
```
  
**PowerShell :**  
```powershell
.\stop.ps1  # Ce module uniquement
# Ou
cd ..; .\stop-all.ps1  # Tous les modules
```
  

## Utilisation de l'application

L’application propose une interface web pour télécharger des documents et poser des questions.

<a href="images/rag-homepage.png"><img src="../../../translated_images/fr/rag-homepage.d90eb5ce1b3caa94.webp" alt="Interface de l'application RAG" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Interface de l’application RAG - téléchargez des documents et posez des questions*

### Télécharger un document

Commencez par télécharger un document – les fichiers TXT conviennent mieux pour les tests. Un `sample-document.txt` est fourni dans ce répertoire contenant des informations sur les fonctionnalités de LangChain4j, l’implémentation RAG, et les bonnes pratiques – parfait pour tester le système.

Le système traite votre document, le découpe en morceaux, et crée des embeddings pour chaque morceau. Cela se fait automatiquement lors du téléchargement.

### Poser des questions

Posez ensuite des questions spécifiques sur le contenu du document. Essayez quelque chose de factuel clairement indiqué dans le document. Le système recherche les morceaux pertinents, les inclut dans l’invite, et génère une réponse.

### Vérifier les références sources

Chaque réponse inclut des références sources avec des scores de similarité. Ces scores (de 0 à 1) montrent à quel point chaque morceau est pertinent par rapport à votre question. Les scores plus élevés indiquent une meilleure correspondance. Cela vous permet de vérifier la réponse à partir du matériau source.

<a href="images/rag-query-results.png"><img src="../../../translated_images/fr/rag-query-results.6d69fcec5397f355.webp" alt="Résultats de requête RAG" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Résultats de requête affichant la réponse avec les références sources et les scores de pertinence*

### Expérimenter avec des questions

Essayez différents types de questions :  
- Faits spécifiques : « Quel est le sujet principal ? »  
- Comparaisons : « Quelle est la différence entre X et Y ? »  
- Résumés : « Résumez les points clés à propos de Z »

Observez comment les scores de pertinence changent selon la correspondance de votre question avec le contenu du document.

## Concepts clés

### Stratégie de découpage

Les documents sont divisés en morceaux de 300 tokens avec 30 tokens de chevauchement. Cet équilibre garantit que chaque morceau a assez de contexte pour être significatif tout en restant assez petit pour inclure plusieurs morceaux dans une invite.

### Scores de similarité

Les scores vont de 0 à 1 :  
- 0.7-1.0 : Très pertinent, correspondance exacte  
- 0.5-0.7 : Pertinent, bon contexte  
- En dessous de 0.5 : Filtré, trop dissemblable  

Le système ne récupère que les morceaux au-dessus du seuil minimum pour garantir la qualité.

### Stockage en mémoire

Ce module utilise un stockage en mémoire pour la simplicité. Lorsque vous redémarrez l’application, les documents téléchargés sont perdus. Les systèmes en production utilisent des bases de données vectorielles persistantes comme Qdrant ou Azure AI Search.

### Gestion de la fenêtre de contexte

Chaque modèle a une fenêtre de contexte maximale. Vous ne pouvez pas inclure tous les morceaux d’un grand document. Le système récupère les N morceaux les plus pertinents (par défaut 5) pour rester dans les limites tout en fournissant assez de contexte pour des réponses précises.

## Quand le RAG est important

**Utilisez le RAG quand :**  
- Vous répondez à des questions sur des documents propriétaires  
- Les informations changent fréquemment (politiques, prix, spécifications)  
- La précision nécessite une attribution des sources  
- Le contenu est trop volumineux pour tenir dans une seule invite  
- Vous avez besoin de réponses vérifiables et fondées  

**N'utilisez pas le RAG quand :**  
- Les questions nécessitent des connaissances générales que le modèle possède déjà  
- Les données en temps réel sont nécessaires (le RAG fonctionne sur des documents téléchargés)  
- Le contenu est assez petit pour être inclus directement dans les invites  

## Étapes suivantes

**Module suivant :** [04-tools - Agents IA avec outils](../04-tools/README.md)

---

**Navigation :** [← Précédent : Module 02 - Ingénierie des invites](../02-prompt-engineering/README.md) | [Retour au début](../README.md) | [Suivant : Module 04 - Outils →](../04-tools/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Avertissement** :  
Ce document a été traduit à l’aide du service de traduction automatique [Co-op Translator](https://github.com/Azure/co-op-translator). Bien que nous nous efforcions d’assurer l’exactitude, veuillez noter que les traductions automatiques peuvent contenir des erreurs ou des inexactitudes. Le document original dans sa langue d’origine doit être considéré comme la source faisant foi. Pour les informations critiques, il est recommandé de recourir à une traduction professionnelle réalisée par un humain. Nous déclinons toute responsabilité en cas de malentendus ou d’interprétations erronées résultant de l’utilisation de cette traduction.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->