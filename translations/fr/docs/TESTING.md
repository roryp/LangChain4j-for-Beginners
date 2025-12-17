<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "b975537560c404d5f254331832811e78",
  "translation_date": "2025-12-13T20:31:51+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "fr"
}
-->
# Tester les applications LangChain4j

## Table des matières

- [Démarrage rapide](../../../docs)
- [Ce que couvrent les tests](../../../docs)
- [Exécution des tests](../../../docs)
- [Exécution des tests dans VS Code](../../../docs)
- [Modèles de test](../../../docs)
- [Philosophie des tests](../../../docs)
- [Étapes suivantes](../../../docs)

Ce guide vous accompagne à travers les tests qui démontrent comment tester des applications IA sans nécessiter de clés API ni de services externes.

## Démarrage rapide

Exécutez tous les tests avec une seule commande :

**Bash :**
```bash
mvn test
```

**PowerShell :**
```powershell
mvn --% test
```

<img src="../../../translated_images/test-results.ea5c98d8f3642043bdfde5c208f8c0760a88a818f6846763583b614a5de37d52.fr.png" alt="Résultats de test réussis" width="800"/>

*Exécution réussie des tests montrant tous les tests passés sans échec*

## Ce que couvrent les tests

Ce cours se concentre sur les **tests unitaires** qui s'exécutent localement. Chaque test démontre un concept spécifique de LangChain4j isolément.

<img src="../../../translated_images/testing-pyramid.2dd1079a0481e53e4da944aec40169f37adf86fd932d3dfd56a4a86b37401ab9.fr.png" alt="Pyramide de test" width="800"/>

*Pyramide de test montrant l'équilibre entre tests unitaires (rapides, isolés), tests d'intégration (composants réels) et tests de bout en bout (système complet avec Docker). Cette formation couvre les tests unitaires.*

| Module | Tests | Focus | Fichiers clés |
|--------|-------|-------|---------------|
| **00 - Démarrage rapide** | 6 | Modèles de prompt et substitution de variables | `SimpleQuickStartTest.java` |
| **01 - Introduction** | 8 | Mémoire de conversation et chat avec état | `SimpleConversationTest.java` |
| **02 - Ingénierie des prompts** | 12 | Modèles GPT-5, niveaux d'empressement, sortie structurée | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | Ingestion de documents, embeddings, recherche de similarité | `DocumentServiceTest.java` |
| **04 - Outils** | 12 | Appel de fonctions et chaînage d'outils | `SimpleToolsTest.java` |
| **05 - MCP** | 15 | Protocole de contexte de modèle avec Docker | `SimpleMcpTest.java`, `McpDockerTransportTest.java` |

## Exécution des tests

**Exécuter tous les tests depuis la racine :**

**Bash :**
```bash
mvn test
```

**PowerShell :**
```powershell
mvn --% test
```

**Exécuter les tests pour un module spécifique :**

**Bash :**
```bash
cd 01-introduction && mvn test
# Ou depuis la racine
mvn test -pl 01-introduction
```

**PowerShell :**
```powershell
cd 01-introduction; mvn --% test
# Ou depuis la racine
mvn --% test -pl 01-introduction
```

**Exécuter une seule classe de test :**

**Bash :**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell :**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**Exécuter une méthode de test spécifique :**

**Bash :**
```bash
mvn test -Dtest=SimpleConversationTest#doitMaintenirLHistoriqueDeConversation
```

**PowerShell :**
```powershell
mvn --% test -Dtest=SimpleConversationTest#doitMaintenirLHistoriqueDeConversation
```

## Exécution des tests dans VS Code

Si vous utilisez Visual Studio Code, l'Explorateur de tests fournit une interface graphique pour exécuter et déboguer les tests.

<img src="../../../translated_images/vscode-testing.f02dd5917289dcedbacf98a3539218c1d0c700307ab77c031590ae63d0be59b6.fr.png" alt="Explorateur de tests VS Code" width="800"/>

*Explorateur de tests VS Code montrant l'arborescence des tests avec toutes les classes de test Java et les méthodes de test individuelles*

**Pour exécuter les tests dans VS Code :**

1. Ouvrez l'Explorateur de tests en cliquant sur l'icône du bécher dans la barre d'activité
2. Développez l'arborescence des tests pour voir tous les modules et classes de test
3. Cliquez sur le bouton lecture à côté de n'importe quel test pour l'exécuter individuellement
4. Cliquez sur "Run All Tests" pour exécuter toute la suite
5. Faites un clic droit sur un test et sélectionnez "Debug Test" pour définir des points d'arrêt et parcourir le code

L'Explorateur de tests affiche des coches vertes pour les tests réussis et fournit des messages d'échec détaillés lorsque des tests échouent.

## Modèles de test

<img src="../../../translated_images/testing-patterns.02581af1c9ef742460887004e9940994dba342ec726efc4ddb2b0215b56a1d89.fr.png" alt="Six modèles de test" width="800"/>

*Six modèles de test pour les applications LangChain4j : modèles de prompt, simulation de modèles, isolation de conversation, test d'outils, RAG en mémoire, et intégration Docker*

### Modèle 1 : Tester les modèles de prompt

Le modèle le plus simple teste les modèles de prompt sans appeler de modèle IA. Vous vérifiez que la substitution des variables fonctionne correctement et que les prompts sont formatés comme prévu.

<img src="../../../translated_images/prompt-template-testing.b902758ddccc8dee3dfceaa5fa903098bdb174674acc5009a2f3d60cb07eb4e9.fr.png" alt="Test de modèle de prompt" width="800"/>

*Test des modèles de prompt montrant le flux de substitution de variables : modèle avec espaces réservés → valeurs appliquées → sortie formatée vérifiée*

```java
@Test
@DisplayName("Should format prompt template with variables")
void testPromptTemplateFormatting() {
    PromptTemplate template = PromptTemplate.from(
        "Best time to visit {{destination}} for {{activity}}?"
    );
    
    Prompt prompt = template.apply(Map.of(
        "destination", "Paris",
        "activity", "sightseeing"
    ));
    
    assertThat(prompt.text()).isEqualTo("Best time to visit Paris for sightseeing?");
}
```

Ce test se trouve dans `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`.

**Exécutez-le :**

**Bash :**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#testFormatageDuModèleDePrompt
```

**PowerShell :**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#testFormatageDuModèleDePrompt
```

### Modèle 2 : Simulation des modèles de langage

Lors du test de la logique de conversation, utilisez Mockito pour créer des modèles factices qui retournent des réponses prédéterminées. Cela rend les tests rapides, gratuits et déterministes.

<img src="../../../translated_images/mock-vs-real.3b8b1f85bfe6845ee07556f9520ac45c7f776d0879434315756020cd8b1d5b73.fr.png" alt="Comparaison Mock vs API réelle" width="800"/>

*Comparaison montrant pourquoi les mocks sont préférés pour les tests : ils sont rapides, gratuits, déterministes et ne nécessitent pas de clés API*

```java
@ExtendWith(MockitoExtension.class)
class SimpleConversationTest {
    
    private ConversationService conversationService;
    
    @Mock
    private OpenAiOfficialChatModel mockChatModel;
    
    @BeforeEach
    void setUp() {
        ChatResponse mockResponse = ChatResponse.builder()
            .aiMessage(AiMessage.from("This is a test response"))
            .build();
        when(mockChatModel.chat(anyList())).thenReturn(mockResponse);
        
        conversationService = new ConversationService(mockChatModel);
    }
    
    @Test
    void shouldMaintainConversationHistory() {
        String conversationId = conversationService.startConversation();
        
        ChatResponse mockResponse1 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 1"))
            .build();
        ChatResponse mockResponse2 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 2"))
            .build();
        ChatResponse mockResponse3 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 3"))
            .build();
        
        when(mockChatModel.chat(anyList()))
            .thenReturn(mockResponse1)
            .thenReturn(mockResponse2)
            .thenReturn(mockResponse3);

        conversationService.chat(conversationId, "First message");
        conversationService.chat(conversationId, "Second message");
        conversationService.chat(conversationId, "Third message");

        List<ChatMessage> history = conversationService.getHistory(conversationId);
        assertThat(history).hasSize(6); // 3 messages utilisateur + 3 messages IA
    }
}
```

Ce modèle apparaît dans `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`. Le mock garantit un comportement cohérent pour vérifier que la gestion de la mémoire fonctionne correctement.

### Modèle 3 : Tester l'isolation des conversations

La mémoire de conversation doit garder les utilisateurs multiples séparés. Ce test vérifie que les conversations ne mélangent pas les contextes.

<img src="../../../translated_images/conversation-isolation.e00336cf8f7a3e3f860d81c37a97181c0e0a2ff996d38f314f06b35cc1e08ca3.fr.png" alt="Isolation de conversation" width="800"/>

*Test de l'isolation de conversation montrant des mémoires séparées pour différents utilisateurs afin d'éviter le mélange de contexte*

```java
@Test
void shouldIsolateConversationsByid() {
    String conv1 = conversationService.startConversation();
    String conv2 = conversationService.startConversation();
    
    ChatResponse mockResponse = ChatResponse.builder()
        .aiMessage(AiMessage.from("Response"))
        .build();
    when(mockChatModel.chat(anyList())).thenReturn(mockResponse);

    conversationService.chat(conv1, "Message for conversation 1");
    conversationService.chat(conv2, "Message for conversation 2");

    List<ChatMessage> history1 = conversationService.getHistory(conv1);
    List<ChatMessage> history2 = conversationService.getHistory(conv2);
    
    assertThat(history1).hasSize(2);
    assertThat(history2).hasSize(2);
}
```

Chaque conversation maintient son propre historique indépendant. Dans les systèmes de production, cette isolation est cruciale pour les applications multi-utilisateurs.

### Modèle 4 : Tester les outils indépendamment

Les outils sont des fonctions que l'IA peut appeler. Testez-les directement pour vous assurer qu'ils fonctionnent correctement indépendamment des décisions de l'IA.

<img src="../../../translated_images/tools-testing.3e1706817b0b3924e7e7cd41be8ba5ccb62f16962b85f53953d46cc6317d2972.fr.png" alt="Test des outils" width="800"/>

*Test des outils indépendamment montrant l'exécution d'outils simulés sans appels IA pour vérifier la logique métier*

```java
@Test
void shouldConvertCelsiusToFahrenheit() {
    TemperatureTool tempTool = new TemperatureTool();
    String result = tempTool.celsiusToFahrenheit(25.0);
    assertThat(result).containsPattern("77[.,]0°F");
}

@Test
void shouldDemonstrateToolChaining() {
    WeatherTool weatherTool = new WeatherTool();
    TemperatureTool tempTool = new TemperatureTool();

    String weatherResult = weatherTool.getCurrentWeather("Seattle");
    assertThat(weatherResult).containsPattern("\\d+°C");

    String conversionResult = tempTool.celsiusToFahrenheit(22.0);
    assertThat(conversionResult).containsPattern("71[.,]6°F");
}
```

Ces tests de `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` valident la logique des outils sans intervention IA. L'exemple de chaînage montre comment la sortie d'un outil alimente l'entrée d'un autre.

### Modèle 5 : Test RAG en mémoire

Les systèmes RAG nécessitent traditionnellement des bases de données vectorielles et des services d'embeddings. Le modèle en mémoire vous permet de tester toute la chaîne sans dépendances externes.

<img src="../../../translated_images/rag-testing.ee7541b1e23934b14fcda9103bba011864f1d0882b27a9886c3cf5b446522ce3.fr.png" alt="Test RAG en mémoire" width="800"/>

*Flux de test RAG en mémoire montrant l'analyse de documents, le stockage d'embeddings et la recherche de similarité sans nécessiter de base de données*

```java
@Test
void testProcessTextDocument() {
    String content = "This is a test document.\nIt has multiple lines.";
    InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    
    DocumentService.ProcessedDocument result = 
        documentService.processDocument(inputStream, "test.txt");

    assertNotNull(result);
    assertTrue(result.segments().size() > 0);
    assertEquals("test.txt", result.segments().get(0).metadata().getString("filename"));
}
```

Ce test de `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` crée un document en mémoire et vérifie le découpage et la gestion des métadonnées.

### Modèle 6 : Test d'intégration avec Docker

Certaines fonctionnalités nécessitent une infrastructure réelle. Le module MCP utilise Testcontainers pour lancer des conteneurs Docker pour les tests d'intégration. Ceux-ci valident que votre code fonctionne avec des services réels tout en maintenant l'isolation des tests.

<img src="../../../translated_images/mcp-testing.bb3b3e92e47acb4b3dfb12715b4e113655f16c11ba39c2ffabae557bc6c3734c.fr.png" alt="Test d'intégration MCP Docker" width="800"/>

*Test d'intégration MCP avec Testcontainers montrant le cycle de vie automatisé des conteneurs : démarrage, exécution des tests, arrêt et nettoyage*

Les tests dans `05-mcp/src/test/java/com/example/langchain4j/mcp/McpDockerTransportTest.java` nécessitent que Docker soit en fonctionnement.

**Exécutez-les :**

**Bash :**
```bash
cd 05-mcp && mvn test
```

**PowerShell :**
```powershell
cd 05-mcp; mvn --% test
```

## Philosophie des tests

Testez votre code, pas l'IA. Vos tests doivent valider le code que vous écrivez en vérifiant comment les prompts sont construits, comment la mémoire est gérée, et comment les outils s'exécutent. Les réponses de l'IA varient et ne doivent pas faire partie des assertions de test. Demandez-vous si votre modèle de prompt substitue correctement les variables, pas si l'IA donne la bonne réponse.

Utilisez des mocks pour les modèles de langage. Ce sont des dépendances externes lentes, coûteuses et non déterministes. La simulation rend les tests rapides en millisecondes au lieu de secondes, gratuits sans coûts API, et déterministes avec le même résultat à chaque fois.

Gardez les tests indépendants. Chaque test doit configurer ses propres données, ne pas dépendre d'autres tests, et nettoyer après lui-même. Les tests doivent réussir quel que soit l'ordre d'exécution.

Testez les cas limites au-delà du chemin heureux. Essayez des entrées vides, des entrées très grandes, des caractères spéciaux, des paramètres invalides, et des conditions aux limites. Ceux-ci révèlent souvent des bugs que l'usage normal ne montre pas.

Utilisez des noms descriptifs. Comparez `shouldMaintainConversationHistoryAcrossMultipleMessages()` avec `test1()`. Le premier vous dit exactement ce qui est testé, facilitant grandement le débogage des échecs.

## Étapes suivantes

Maintenant que vous comprenez les modèles de test, plongez plus profondément dans chaque module :

- **[00 - Démarrage rapide](../00-quick-start/README.md)** - Commencez par les bases des modèles de prompt
- **[01 - Introduction](../01-introduction/README.md)** - Apprenez la gestion de la mémoire de conversation
- **[02 - Ingénierie des prompts](../02-prompt-engineering/README.md)** - Maîtrisez les modèles de prompt GPT-5
- **[03 - RAG](../03-rag/README.md)** - Construisez des systèmes de génération augmentée par récupération
- **[04 - Outils](../04-tools/README.md)** - Implémentez l'appel de fonctions et les chaînes d'outils
- **[05 - MCP](../05-mcp/README.md)** - Intégrez le protocole de contexte de modèle avec Docker

Le README de chaque module fournit des explications détaillées des concepts testés ici.

---

**Navigation :** [← Retour au principal](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Avertissement** :  
Ce document a été traduit à l’aide du service de traduction automatique [Co-op Translator](https://github.com/Azure/co-op-translator). Bien que nous nous efforcions d’assurer l’exactitude, veuillez noter que les traductions automatiques peuvent contenir des erreurs ou des inexactitudes. Le document original dans sa langue d’origine doit être considéré comme la source faisant foi. Pour les informations critiques, une traduction professionnelle réalisée par un humain est recommandée. Nous déclinons toute responsabilité en cas de malentendus ou de mauvaises interprétations résultant de l’utilisation de cette traduction.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->