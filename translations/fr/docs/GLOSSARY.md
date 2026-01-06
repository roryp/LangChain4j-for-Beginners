<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T07:40:44+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "fr"
}
-->
# Glossaire LangChain4j

## Table des matières

- [Concepts fondamentaux](../../../docs)
- [Composants LangChain4j](../../../docs)
- [Concepts AI/ML](../../../docs)
- [Guardrails](../../../docs)
- [Ingénierie de prompt](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agents et outils](../../../docs)
- [Module Agentique](../../../docs)
- [Protocole de contexte modèle (MCP)](../../../docs)
- [Services Azure](../../../docs)
- [Tests et développement](../../../docs)

Référence rapide pour les termes et concepts utilisés tout au long du cours.

## Concepts fondamentaux

**Agent IA** - Système qui utilise l'IA pour raisonner et agir de manière autonome. [Module 04](../04-tools/README.md)

**Chaîne** - Séquence d'opérations où la sortie alimente l'étape suivante.

**Fragmentation** - Découpage des documents en morceaux plus petits. Typique : 300-500 tokens avec chevauchement. [Module 03](../03-rag/README.md)

**Fenêtre de contexte** - Nombre maximal de tokens qu'un modèle peut traiter. GPT-5 : 400K tokens.

**Incrustations** - Vecteurs numériques représentant la signification du texte. [Module 03](../03-rag/README.md)

**Appel de fonction** - Le modèle génère des requêtes structurées pour appeler des fonctions externes. [Module 04](../04-tools/README.md)

**Hallucination** - Lorsque les modèles génèrent des informations incorrectes mais plausibles.

**Prompt** - Texte d'entrée pour un modèle de langage. [Module 02](../02-prompt-engineering/README.md)

**Recherche sémantique** - Recherche par signification utilisant des incrustations, pas des mots-clés. [Module 03](../03-rag/README.md)

**Avec état vs Sans état** - Sans état : pas de mémoire. Avec état : conserve l’historique de la conversation. [Module 01](../01-introduction/README.md)

**Tokens** - Unités textuelles de base que les modèles traitent. Impactent les coûts et limites. [Module 01](../01-introduction/README.md)

**Chaînage d’outils** - Exécution séquentielle des outils où la sortie informe l’appel suivant. [Module 04](../04-tools/README.md)

## Composants LangChain4j

**AiServices** - Crée des interfaces de services IA typées.

**OpenAiOfficialChatModel** - Client unifié pour les modèles OpenAI et Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Crée des incrustations avec le client officiel OpenAI (supporte OpenAI et Azure OpenAI).

**ChatModel** - Interface centrale pour les modèles de langage.

**ChatMemory** - Conserve l’historique des conversations.

**ContentRetriever** - Trouve les fragments de documents pertinents pour le RAG.

**DocumentSplitter** - Découpe les documents en fragments.

**EmbeddingModel** - Convertit le texte en vecteurs numériques.

**EmbeddingStore** - Stocke et récupère les incrustations.

**MessageWindowChatMemory** - Conserve une fenêtre glissante des messages récents.

**PromptTemplate** - Crée des prompts réutilisables avec des placeholders `{{variable}}`.

**TextSegment** - Fragment de texte avec métadonnées. Utilisé dans le RAG.

**ToolExecutionRequest** - Représente une requête d’exécution d’outil.

**UserMessage / AiMessage / SystemMessage** - Types de messages de conversation.

## Concepts AI/ML

**Few-Shot Learning** - Fournir des exemples dans les prompts. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Modèles IA entraînés sur d’énormes volumes de texte.

**Effort de Raisonnement** - Paramètre GPT-5 contrôlant la profondeur de réflexion. [Module 02](../02-prompt-engineering/README.md)

**Température** - Contrôle l’aléa de la sortie. Faible = déterministe, élevé = créatif.

**Base de données vectorielle** - Base de données spécialisée pour les incrustations. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - Réalisation de tâches sans exemples. [Module 02](../02-prompt-engineering/README.md)

## Guardrails - [Module 00](../00-quick-start/README.md)

**Défense en profondeur** - Approche de sécurité à couches multiples combinant les guardrails au niveau applicatif et les filtres de sécurité du fournisseur.

**Blocage dur** - Le fournisseur renvoie une erreur HTTP 400 pour les violations de contenu graves.

**InputGuardrail** - Interface LangChain4j pour valider les entrées utilisateur avant qu’elles n’atteignent le LLM. Économise coûts et latence en bloquant tôt les prompts nuisibles.

**InputGuardrailResult** - Type de retour de validation : `success()` ou `fatal("raison")`.

**OutputGuardrail** - Interface pour valider les réponses IA avant de les retourner aux utilisateurs.

**Filtres de sécurité du fournisseur** - Filtres intégrés des fournisseurs IA (ex. GitHub Models) qui détectent les violations au niveau API.

**Refus doux** - Le modèle décline poliment de répondre sans générer d’erreur.

## Ingénierie de prompt - [Module 02](../02-prompt-engineering/README.md)

**Chaîne de pensée** - Raisonnement étape par étape pour plus de précision.

**Sortie contrainte** - Imposition d’un format ou d’une structure spécifique.

**Forte motivation** - Modèle GPT-5 pour raisonnement approfondi.

**Faible motivation** - Modèle GPT-5 pour réponses rapides.

**Conversation multi-tours** - Maintien du contexte lors des échanges.

**Prompting basé sur rôle** - Définir la personnalité du modèle via des messages système.

**Auto-réflexion** - Le modèle évalue et améliore sa sortie.

**Analyse structurée** - Cadre d’évaluation fixe.

**Patron d’exécution de tâche** - Planifier → Exécuter → Résumer.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Pipeline de traitement documentaire** - Charger → fragmenter → incruster → stocker.

**Stockage d’incrustation en mémoire** - Stockage non persistant pour tests.

**RAG** - Combine la recherche avec la génération pour ancrer les réponses.

**Score de similarité** - Mesure (0-1) de similarité sémantique.

**Référence source** - Métadonnées sur le contenu récupéré.

## Agents et outils - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Marque les méthodes Java comme outils appelables par l'IA.

**Patron ReAct** - Raisonner → Agir → Observer → Répéter.

**Gestion de sessions** - Contextes séparés pour différents utilisateurs.

**Outil** - Fonction qu’un agent IA peut appeler.

**Description d’outil** - Documentation du but et des paramètres de l’outil.

## Module Agentique - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - Marque les interfaces comme agents IA avec définition comportementale déclarative.

**Agent Listener** - Crochets pour surveiller l’exécution de l’agent via `beforeAgentInvocation()` et `afterAgentInvocation()`.

**Scope Agentique** - Mémoire partagée où les agents stockent les sorties avec `outputKey` pour consommation par d’autres agents.

**AgenticServices** - Usine pour créer des agents via `agentBuilder()` et `supervisorBuilder()`.

**Workflow conditionnel** - Routage selon conditions vers différents agents spécialistes.

**Human-in-the-Loop** - Patron workflow ajoutant des points de contrôle humains pour approbation ou revue de contenu.

**langchain4j-agentic** - Dépendance Maven pour construction déclarative d’agents (expérimental).

**Workflow en boucle** - Itération de l’exécution d’agent jusqu’à ce qu’une condition soit remplie (ex. score qualité ≥ 0,8).

**outputKey** - Paramètre d’annotation agent spécifiant où stocker les résultats dans le Scope Agentique.

**Workflow parallèle** - Exécution simultanée de plusieurs agents pour tâches indépendantes.

**Stratégie de réponse** - Manière dont le superviseur formule la réponse finale : DERNIER, RÉSUMÉ, ou NOTÉ.

**Workflow séquentiel** - Exécution ordonnée d’agents où la sortie alimente l’étape suivante.

**Patron Agent superviseur** - Patron agentique avancé où un LLM superviseur décide dynamiquement quels sous-agents invoquer.

## Protocole de contexte modèle (MCP) - [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - Dépendance Maven pour intégration MCP dans LangChain4j.

**MCP** - Model Context Protocol : standard pour connecter les applications IA aux outils externes. Construire une fois, utiliser partout.

**Client MCP** - Application se connectant aux serveurs MCP pour découvrir et utiliser les outils.

**Serveur MCP** - Service exposant des outils via MCP avec descriptions claires et schémas de paramètres.

**McpToolProvider** - Composant LangChain4j qui enveloppe les outils MCP pour usage dans services IA et agents.

**McpTransport** - Interface pour communication MCP. Implémentations incluent Stdio et HTTP.

**Transport Stdio** - Transport local via stdin/stdout. Utile pour accès système de fichiers ou outils en ligne de commande.

**StdioMcpTransport** - Implémentation LangChain4j lançant le serveur MCP en sous-processus.

**Découverte d’outils** - Le client interroge le serveur sur les outils disponibles avec descriptions et schémas.

## Services Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - Recherche cloud avec capacités vectorielles. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Déploie les ressources Azure.

**Azure OpenAI** - Service IA d’entreprise de Microsoft.

**Bicep** - Langage d’infrastructure-as-code Azure. [Guide infrastructure](../01-introduction/infra/README.md)

**Nom de déploiement** - Nom pour le déploiement de modèle dans Azure.

**GPT-5** - Dernier modèle OpenAI avec contrôle du raisonnement. [Module 02](../02-prompt-engineering/README.md)

## Tests et développement - [Guide de tests](TESTING.md)

**Dev Container** - Environnement de développement containerisé. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Terrain de jeu gratuit pour modèles IA. [Module 00](../00-quick-start/README.md)

**Tests en mémoire** - Tests avec stockage en mémoire.

**Tests d’intégration** - Tests avec infrastructure réelle.

**Maven** - Outil d’automatisation de build Java.

**Mockito** - Framework Java de mock.

**Spring Boot** - Framework applicatif Java. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Avertissement** :  
Ce document a été traduit à l’aide du service de traduction automatique [Co-op Translator](https://github.com/Azure/co-op-translator). Bien que nous nous efforçons d’assurer l’exactitude, veuillez noter que les traductions automatiques peuvent contenir des erreurs ou des inexactitudes. Le document original dans sa langue d’origine doit être considéré comme la source faisant autorité. Pour des informations critiques, il est recommandé de faire appel à une traduction professionnelle humaine. Nous déclinons toute responsabilité en cas de malentendus ou d’interprétations erronées résultant de l’utilisation de cette traduction.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->