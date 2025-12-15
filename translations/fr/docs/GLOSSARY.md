<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T19:49:15+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "fr"
}
-->
# Glossaire LangChain4j

## Table des matières

- [Concepts de base](../../../docs)
- [Composants LangChain4j](../../../docs)
- [Concepts IA/ML](../../../docs)
- [Ingénierie des prompts](../../../docs)
- [RAG (Génération augmentée par récupération)](../../../docs)
- [Agents et outils](../../../docs)
- [Protocole de contexte de modèle (MCP)](../../../docs)
- [Services Azure](../../../docs)
- [Tests et développement](../../../docs)

Référence rapide des termes et concepts utilisés tout au long du cours.

## Concepts de base

**Agent IA** - Système qui utilise l'IA pour raisonner et agir de manière autonome. [Module 04](../04-tools/README.md)

**Chaîne** - Séquence d'opérations où la sortie alimente l'étape suivante.

**Découpage (Chunking)** - Diviser les documents en morceaux plus petits. Typique : 300-500 tokens avec chevauchement. [Module 03](../03-rag/README.md)

**Fenêtre de contexte** - Nombre maximal de tokens qu'un modèle peut traiter. GPT-5 : 400K tokens.

**Embeddings** - Vecteurs numériques représentant le sens du texte. [Module 03](../03-rag/README.md)

**Appel de fonction** - Le modèle génère des requêtes structurées pour appeler des fonctions externes. [Module 04](../04-tools/README.md)

**Hallucination** - Lorsque les modèles génèrent des informations incorrectes mais plausibles.

**Prompt** - Texte d'entrée pour un modèle de langage. [Module 02](../02-prompt-engineering/README.md)

**Recherche sémantique** - Recherche par sens utilisant des embeddings, pas des mots-clés. [Module 03](../03-rag/README.md)

**Avec état vs sans état** - Sans état : pas de mémoire. Avec état : conserve l'historique de la conversation. [Module 01](../01-introduction/README.md)

**Tokens** - Unités de texte de base que les modèles traitent. Impacte les coûts et limites. [Module 01](../01-introduction/README.md)

**Chaînage d'outils** - Exécution séquentielle d'outils où la sortie informe l'appel suivant. [Module 04](../04-tools/README.md)

## Composants LangChain4j

**AiServices** - Crée des interfaces de service IA typées.

**OpenAiOfficialChatModel** - Client unifié pour les modèles OpenAI et Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Crée des embeddings en utilisant le client officiel OpenAI (supporte OpenAI et Azure OpenAI).

**ChatModel** - Interface principale pour les modèles de langage.

**ChatMemory** - Conserve l'historique de la conversation.

**ContentRetriever** - Trouve les morceaux de documents pertinents pour RAG.

**DocumentSplitter** - Divise les documents en morceaux.

**EmbeddingModel** - Convertit le texte en vecteurs numériques.

**EmbeddingStore** - Stocke et récupère les embeddings.

**MessageWindowChatMemory** - Maintient une fenêtre glissante des messages récents.

**PromptTemplate** - Crée des prompts réutilisables avec des espaces réservés `{{variable}}`.

**TextSegment** - Morceau de texte avec métadonnées. Utilisé dans RAG.

**ToolExecutionRequest** - Représente une requête d'exécution d'outil.

**UserMessage / AiMessage / SystemMessage** - Types de messages de conversation.

## Concepts IA/ML

**Apprentissage par quelques exemples (Few-Shot Learning)** - Fournir des exemples dans les prompts. [Module 02](../02-prompt-engineering/README.md)

**Grand modèle de langage (LLM)** - Modèles IA entraînés sur d'immenses données textuelles.

**Effort de raisonnement** - Paramètre GPT-5 contrôlant la profondeur de la réflexion. [Module 02](../02-prompt-engineering/README.md)

**Température** - Contrôle l'aléa de la sortie. Faible = déterministe, élevé = créatif.

**Base de données vectorielle** - Base spécialisée pour les embeddings. [Module 03](../03-rag/README.md)

**Apprentissage sans exemple (Zero-Shot Learning)** - Réaliser des tâches sans exemples. [Module 02](../02-prompt-engineering/README.md)

## Ingénierie des prompts - [Module 02](../02-prompt-engineering/README.md)

**Chaîne de pensée (Chain-of-Thought)** - Raisonnement étape par étape pour une meilleure précision.

**Sortie contrainte** - Imposer un format ou une structure spécifique.

**Forte motivation (High Eagerness)** - Modèle GPT-5 pour un raisonnement approfondi.

**Faible motivation (Low Eagerness)** - Modèle GPT-5 pour des réponses rapides.

**Conversation multi-tours** - Maintenir le contexte à travers les échanges.

**Prompting basé sur le rôle** - Définir la persona du modèle via des messages système.

**Auto-réflexion** - Le modèle évalue et améliore sa sortie.

**Analyse structurée** - Cadre d'évaluation fixe.

**Modèle d'exécution de tâche** - Planifier → Exécuter → Résumer.

## RAG (Génération augmentée par récupération) - [Module 03](../03-rag/README.md)

**Pipeline de traitement de documents** - Charger → découper → intégrer → stocker.

**Stockage d'embeddings en mémoire** - Stockage non persistant pour les tests.

**RAG** - Combine récupération et génération pour ancrer les réponses.

**Score de similarité** - Mesure (0-1) de similarité sémantique.

**Référence source** - Métadonnées sur le contenu récupéré.

## Agents et outils - [Module 04](../04-tools/README.md)

**Annotation @Tool** - Marque les méthodes Java comme outils appelables par l'IA.

**Modèle ReAct** - Raisonner → Agir → Observer → Répéter.

**Gestion de session** - Contextes séparés pour différents utilisateurs.

**Outil** - Fonction qu'un agent IA peut appeler.

**Description d'outil** - Documentation de l'objectif et des paramètres de l'outil.

## Protocole de contexte de modèle (MCP) - [Module 05](../05-mcp/README.md)

**Transport Docker** - Serveur MCP dans un conteneur Docker.

**MCP** - Standard pour connecter les applications IA à des outils externes.

**Client MCP** - Application qui se connecte aux serveurs MCP.

**Serveur MCP** - Service exposant des outils via MCP.

**Événements envoyés par le serveur (SSE)** - Streaming serveur vers client via HTTP.

**Transport Stdio** - Serveur en sous-processus via stdin/stdout.

**Transport HTTP streamable** - HTTP avec SSE pour communication en temps réel.

**Découverte d'outils** - Le client interroge le serveur pour les outils disponibles.

## Services Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Recherche cloud avec capacités vectorielles. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Déploie des ressources Azure.

**Azure OpenAI** - Service IA d'entreprise de Microsoft.

**Bicep** - Langage d'infrastructure-as-code Azure. [Guide Infrastructure](../01-introduction/infra/README.md)

**Nom de déploiement** - Nom pour le déploiement de modèle dans Azure.

**GPT-5** - Dernier modèle OpenAI avec contrôle du raisonnement. [Module 02](../02-prompt-engineering/README.md)

## Tests et développement - [Guide de test](TESTING.md)

**Conteneur Dev** - Environnement de développement conteneurisé. [Configuration](../../../.devcontainer/devcontainer.json)

**Modèles GitHub** - Terrain de jeu gratuit pour modèles IA. [Module 00](../00-quick-start/README.md)

**Tests en mémoire** - Tests avec stockage en mémoire.

**Tests d'intégration** - Tests avec infrastructure réelle.

**Maven** - Outil d'automatisation de build Java.

**Mockito** - Framework de moquage Java.

**Spring Boot** - Framework d'application Java. [Module 01](../01-introduction/README.md)

**Testcontainers** - Conteneurs Docker dans les tests.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Avertissement** :  
Ce document a été traduit à l’aide du service de traduction automatique [Co-op Translator](https://github.com/Azure/co-op-translator). Bien que nous nous efforcions d’assurer l’exactitude, veuillez noter que les traductions automatiques peuvent contenir des erreurs ou des inexactitudes. Le document original dans sa langue d’origine doit être considéré comme la source faisant foi. Pour les informations critiques, une traduction professionnelle réalisée par un humain est recommandée. Nous déclinons toute responsabilité en cas de malentendus ou de mauvaises interprétations résultant de l’utilisation de cette traduction.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->