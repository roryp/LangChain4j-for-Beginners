<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T19:16:09+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "fr"
}
-->
# Glossaire LangChain4j

## Table of Contents

- [Concepts de base](../../../docs)
- [Composants LangChain4j](../../../docs)
- [Concepts IA/ML](../../../docs)
- [Ingénierie des prompts](../../../docs)
- [RAG (Génération augmentée par récupération)](../../../docs)
- [Agents et outils](../../../docs)
- [Model Context Protocol (MCP)](../../../docs)
- [Services Azure](../../../docs)
- [Tests et développement](../../../docs)

Référence rapide des termes et concepts utilisés tout au long du cours.

## Concepts de base

**Agent IA** - Système qui utilise l'IA pour raisonner et agir de manière autonome. [Module 04](../04-tools/README.md)

**Chaîne** - Séquence d'opérations où la sortie alimente l'étape suivante.

**Chunking** - Découpage des documents en plus petites parties. Typique : 300-500 tokens avec chevauchement. [Module 03](../03-rag/README.md)

**Fenêtre de contexte** - Nombre maximal de tokens qu'un modèle peut traiter. GPT-5: 400K tokens.

**Embeddings** - Vecteurs numériques représentant le sens du texte. [Module 03](../03-rag/README.md)

**Appel de fonction** - Le modèle génère des requêtes structurées pour appeler des fonctions externes. [Module 04](../04-tools/README.md)

**Hallucination** - Lorsque les modèles génèrent des informations incorrectes mais plausibles.

**Prompt** - Entrée textuelle pour un modèle de langage. [Module 02](../02-prompt-engineering/README.md)

**Recherche sémantique** - Recherche par sens utilisant des embeddings, pas des mots-clés. [Module 03](../03-rag/README.md)

**Avec état vs Sans état** - Sans état : pas de mémoire. Avec état : conserve l'historique de la conversation. [Module 01](../01-introduction/README.md)

**Tokens** - Unités de texte de base que les modèles traitent. Affecte les coûts et les limites. [Module 01](../01-introduction/README.md)

**Chaînage d'outils** - Exécution séquentielle d'outils où la sortie informe l'appel suivant. [Module 04](../04-tools/README.md)

## Composants LangChain4j

**AiServices** - Crée des interfaces de services IA typées.

**OpenAiOfficialChatModel** - Client unifié pour les modèles OpenAI et Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Crée des embeddings en utilisant le client officiel OpenAI (prend en charge OpenAI et Azure OpenAI).

**ChatModel** - Interface principale pour les modèles de langage.

**ChatMemory** - Conserve l'historique des conversations.

**ContentRetriever** - Trouve les fragments de documents pertinents pour le RAG.

**DocumentSplitter** - Divise les documents en fragments.

**EmbeddingModel** - Convertit le texte en vecteurs numériques.

**EmbeddingStore** - Stocke et récupère les embeddings.

**MessageWindowChatMemory** - Maintient une fenêtre glissante des messages récents.

**PromptTemplate** - Crée des prompts réutilisables avec `{{variable}}` placeholders.

**TextSegment** - Fragment de texte avec métadonnées. Utilisé dans le RAG.

**ToolExecutionRequest** - Représente une requête d'exécution d'outil.

**UserMessage / AiMessage / SystemMessage** - Types de messages de conversation.

## Concepts IA/ML

**Few-Shot Learning** - Fournir des exemples dans les prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Modèles d'IA entraînés sur de vastes corpus textuels.

**Effort de raisonnement** - Paramètre GPT-5 contrôlant la profondeur de la réflexion. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - Contrôle l'aléa de la sortie. Low=déterministe, High=créatif.

**Base de données vectorielle** - Base de données spécialisée pour les embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Exécuter des tâches sans exemples. [Module 02](../02-prompt-engineering/README.md)

## Ingénierie des prompts - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Raisonnement étape par étape pour une meilleure précision.

**Constrained Output** - Imposition d'un format ou d'une structure spécifique.

**High Eagerness** - Schéma GPT-5 pour un raisonnement approfondi.

**Low Eagerness** - Schéma GPT-5 pour des réponses rapides.

**Conversation multi-tours** - Maintenir le contexte à travers les échanges.

**Role-Based Prompting** - Définir la persona du modèle via des messages système.

**Self-Reflection** - Le modèle évalue et améliore sa sortie.

**Structured Analysis** - Cadre d'évaluation fixe.

**Task Execution Pattern** - Plan → Exécuter → Résumer.

## RAG (Génération augmentée par récupération) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - Charger → découper → encoder → stocker.

**In-Memory Embedding Store** - Stockage non persistant pour les tests.

**RAG** - Combine la récupération avec la génération pour ancrer les réponses.

**Similarity Score** - Mesure (0-1) de la similarité sémantique.

**Source Reference** - Métadonnées sur le contenu récupéré.

## Agents et outils - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Marque les méthodes Java comme outils appelables par l'IA.

**ReAct Pattern** - Raisonner → Agir → Observer → Répéter.

**Session Management** - Contextes séparés pour différents utilisateurs.

**Tool** - Fonction qu'un agent IA peut appeler.

**Tool Description** - Documentation du but et des paramètres de l'outil.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - Standard pour connecter les applications d'IA à des outils externes.

**MCP Client** - Application qui se connecte aux serveurs MCP.

**MCP Server** - Service exposant des outils via MCP.

**Stdio Transport** - Serveur en tant que sous-processus via stdin/stdout.

**Tool Discovery** - Le client interroge le serveur pour les outils disponibles.

## Services Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Recherche cloud avec capacités vectorielles. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Déploie des ressources Azure.

**Azure OpenAI** - Service IA entreprise de Microsoft.

**Bicep** - Langage d'infrastructure en tant que code pour Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Nom pour le déploiement du modèle dans Azure.

**GPT-5** - Dernier modèle OpenAI avec contrôle du raisonnement. [Module 02](../02-prompt-engineering/README.md)

## Tests et développement - [Guide de test](TESTING.md)

**Dev Container** - Environnement de développement conteneurisé. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Terrain de jeu gratuit pour modèles d'IA. [Module 00](../00-quick-start/README.md)

**Tests en mémoire** - Tests avec stockage en mémoire.

**Tests d'intégration** - Tests avec une infrastructure réelle.

**Maven** - Outil d'automatisation de build Java.

**Mockito** - Framework de mock pour Java.

**Spring Boot** - Framework d'application Java. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Avertissement** :
Ce document a été traduit à l'aide du service de traduction automatique [Co-op Translator](https://github.com/Azure/co-op-translator). Bien que nous nous efforcions d'assurer l'exactitude, veuillez noter que les traductions automatiques peuvent comporter des erreurs ou des inexactitudes. Le document original dans sa langue d'origine doit être considéré comme la source faisant foi. Pour les informations critiques, il est recommandé de faire appel à un traducteur professionnel. Nous ne sommes pas responsables des malentendus ou des interprétations erronées résultant de l'utilisation de cette traduction.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->