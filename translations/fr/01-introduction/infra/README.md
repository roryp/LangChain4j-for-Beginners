# Infrastructure Azure pour LangChain4j Démarrage

## Table des matières

- [Prérequis](../../../../01-introduction/infra)
- [Architecture](../../../../01-introduction/infra)
- [Ressources créées](../../../../01-introduction/infra)
- [Démarrage rapide](../../../../01-introduction/infra)
- [Configuration](../../../../01-introduction/infra)
- [Commandes de gestion](../../../../01-introduction/infra)
- [Optimisation des coûts](../../../../01-introduction/infra)
- [Surveillance](../../../../01-introduction/infra)
- [Dépannage](../../../../01-introduction/infra)
- [Mise à jour de l'infrastructure](../../../../01-introduction/infra)
- [Nettoyage](../../../../01-introduction/infra)
- [Structure des fichiers](../../../../01-introduction/infra)
- [Recommandations de sécurité](../../../../01-introduction/infra)
- [Ressources supplémentaires](../../../../01-introduction/infra)

Ce répertoire contient l'infrastructure Azure en tant que code (IaC) utilisant Bicep et Azure Developer CLI (azd) pour déployer les ressources Azure OpenAI.

## Prérequis

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (version 2.50.0 ou ultérieure)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (version 1.5.0 ou ultérieure)
- Un abonnement Azure avec les permissions pour créer des ressources

## Architecture

**Configuration simplifiée pour le développement local** - Déployer uniquement Azure OpenAI, exécuter toutes les applications localement.

L'infrastructure déploie les ressources Azure suivantes :

### Services IA
- **Azure OpenAI** : Services cognitifs avec deux déploiements de modèles :
  - **gpt-5** : Modèle de complétion de chat (capacité 20K TPM)
  - **text-embedding-3-small** : Modèle d'encodage pour RAG (capacité 20K TPM)

### Développement local
Toutes les applications Spring Boot s'exécutent localement sur votre machine :
- 01-introduction (port 8080)
- 02-prompt-engineering (port 8083)
- 03-rag (port 8081)
- 04-tools (port 8084)

## Ressources créées

| Type de ressource | Modèle de nom de ressource | But |
|--------------|----------------------|---------|
| Groupe de ressources | `rg-{environmentName}` | Contient toutes les ressources |
| Azure OpenAI | `aoai-{resourceToken}` | Hébergement du modèle IA |

> **Note :** `{resourceToken}` est une chaîne unique générée à partir de l'ID d'abonnement, du nom d'environnement et de la localisation

## Démarrage rapide

### 1. Déployer Azure OpenAI

**Bash :**
```bash
cd 01-introduction
azd up
```

**PowerShell :**
```powershell
cd 01-introduction
azd up
```

Lorsqu'on vous le demande :
- Sélectionnez votre abonnement Azure
- Choisissez une localisation (recommandé : `eastus2` ou `swedencentral` pour la disponibilité de GPT-5)
- Confirmez le nom de l'environnement (par défaut : `langchain4j-dev`)

Cela créera :
- La ressource Azure OpenAI avec GPT-5 et text-embedding-3-small
- Les détails de connexion en sortie

### 2. Obtenir les détails de connexion

**Bash :**
```bash
azd env get-values
```

**PowerShell :**
```powershell
azd env get-values
```

Cela affiche :
- `AZURE_OPENAI_ENDPOINT` : URL de votre point de terminaison Azure OpenAI
- `AZURE_OPENAI_KEY` : Clé API pour l'authentification
- `AZURE_OPENAI_DEPLOYMENT` : Nom du modèle de chat (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT` : Nom du modèle d'encodage

### 3. Exécuter les applications localement

La commande `azd up` crée automatiquement un fichier `.env` à la racine avec toutes les variables d'environnement nécessaires.

**Recommandé :** Démarrer toutes les applications web :

**Bash :**
```bash
# Depuis le répertoire racine
cd ../..
./start-all.sh
```

**PowerShell :**
```powershell
# Depuis le répertoire racine
cd ../..
.\start-all.ps1
```

Ou démarrer un seul module :

**Bash :**
```bash
# Exemple : Démarrer uniquement le module d'introduction
cd ../01-introduction
./start.sh
```

**PowerShell :**
```powershell
# Exemple : Démarrer uniquement le module d'introduction
cd ../01-introduction
.\start.ps1
```

Les deux scripts chargent automatiquement les variables d'environnement depuis le fichier `.env` racine créé par `azd up`.

## Configuration

### Personnalisation des déploiements de modèles

Pour modifier les déploiements de modèles, éditez `infra/main.bicep` et modifiez le paramètre `openAiDeployments` :

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

Modèles et versions disponibles : https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Changer les régions Azure

Pour déployer dans une autre région, éditez `infra/main.bicep` :

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Vérifiez la disponibilité de GPT-5 : https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Pour mettre à jour l'infrastructure après modification des fichiers Bicep :

**Bash :**
```bash
# Reconstruire le modèle ARM
az bicep build --file infra/main.bicep

# Aperçu des modifications
azd provision --preview

# Appliquer les modifications
azd provision
```

**PowerShell :**
```powershell
# Reconstruire le modèle ARM
az bicep build --file infra/main.bicep

# Aperçu des modifications
azd provision --preview

# Appliquer les modifications
azd provision
```

## Nettoyage

Pour supprimer toutes les ressources :

**Bash :**
```bash
# Supprimer toutes les ressources
azd down

# Tout supprimer y compris l'environnement
azd down --purge
```

**PowerShell :**
```powershell
# Supprimer toutes les ressources
azd down

# Tout supprimer y compris l'environnement
azd down --purge
```

**Attention** : Cela supprimera définitivement toutes les ressources Azure.

## Structure des fichiers

## Optimisation des coûts

### Développement/Test
Pour les environnements dev/test, vous pouvez réduire les coûts :
- Utiliser le niveau Standard (S0) pour Azure OpenAI
- Définir une capacité plus faible (10K TPM au lieu de 20K) dans `infra/core/ai/cognitiveservices.bicep`
- Supprimer les ressources lorsqu'elles ne sont pas utilisées : `azd down`

### Production
Pour la production :
- Augmenter la capacité OpenAI selon l'utilisation (50K+ TPM)
- Activer la redondance de zone pour une meilleure disponibilité
- Mettre en place une surveillance et des alertes de coûts appropriées

### Estimation des coûts
- Azure OpenAI : Paiement par token (entrée + sortie)
- GPT-5 : environ 3-5 $ par million de tokens (vérifiez les tarifs actuels)
- text-embedding-3-small : environ 0,02 $ par million de tokens

Calculateur de prix : https://azure.microsoft.com/pricing/calculator/

## Surveillance

### Voir les métriques Azure OpenAI

Allez dans le portail Azure → Votre ressource OpenAI → Métriques :
- Utilisation basée sur les tokens
- Taux de requêtes HTTP
- Temps de réponse
- Tokens actifs

## Dépannage

### Problème : Conflit de nom de sous-domaine Azure OpenAI

**Message d'erreur :**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Cause :**
Le nom de sous-domaine généré à partir de votre abonnement/environnement est déjà utilisé, probablement par un déploiement précédent non complètement supprimé.

**Solution :**
1. **Option 1 - Utiliser un nom d'environnement différent :**
   
   **Bash :**
   ```bash
   azd env new my-unique-env-name
   azd up
   ```
   
   **PowerShell :**
   ```powershell
   azd env new my-unique-env-name
   azd up
   ```

2. **Option 2 - Déploiement manuel via le portail Azure :**
   - Allez dans le portail Azure → Créer une ressource → Azure OpenAI
   - Choisissez un nom unique pour votre ressource
   - Déployez les modèles suivants :
     - **GPT-5**
     - **text-embedding-3-small** (pour les modules RAG)
   - **Important :** Notez vos noms de déploiement - ils doivent correspondre à la configuration `.env`
   - Après déploiement, récupérez votre point de terminaison et clé API dans "Clés et point de terminaison"
   - Créez un fichier `.env` à la racine du projet avec :
     
     **Exemple de fichier `.env` :**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Directives de nommage des déploiements de modèles :**
- Utilisez des noms simples et cohérents : `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Les noms de déploiement doivent correspondre exactement à ce que vous configurez dans `.env`
- Erreur courante : créer un modèle avec un nom mais référencer un autre dans le code

### Problème : GPT-5 non disponible dans la région sélectionnée

**Solution :**
- Choisissez une région avec accès à GPT-5 (ex. eastus, swedencentral)
- Vérifiez la disponibilité : https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Problème : Quota insuffisant pour le déploiement

**Solution :**
1. Demandez une augmentation de quota dans le portail Azure
2. Ou utilisez une capacité plus faible dans `main.bicep` (ex. capacité : 10)

### Problème : "Ressource non trouvée" lors de l'exécution locale

**Solution :**
1. Vérifiez le déploiement : `azd env get-values`
2. Vérifiez que le point de terminaison et la clé sont corrects
3. Assurez-vous que le groupe de ressources existe dans le portail Azure

### Problème : Échec d'authentification

**Solution :**
- Vérifiez que `AZURE_OPENAI_API_KEY` est correctement défini
- Le format de la clé doit être une chaîne hexadécimale de 32 caractères
- Obtenez une nouvelle clé depuis le portail Azure si nécessaire

### Échec du déploiement

**Problème** : `azd provision` échoue avec des erreurs de quota ou de capacité

**Solution** : 
1. Essayez une autre région - Voir la section [Changer les régions Azure](../../../../01-introduction/infra) pour configurer les régions
2. Vérifiez que votre abonnement dispose du quota Azure OpenAI :
   
   **Bash :**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell :**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Application ne se connecte pas

**Problème** : L'application Java affiche des erreurs de connexion

**Solution** :
1. Vérifiez que les variables d'environnement sont exportées :
   
   **Bash :**
   ```bash
   echo $AZURE_OPENAI_ENDPOINT
   echo $AZURE_OPENAI_API_KEY
   ```
   
   **PowerShell :**
   ```powershell
   Write-Host $env:AZURE_OPENAI_ENDPOINT
   Write-Host $env:AZURE_OPENAI_API_KEY
   ```

2. Vérifiez que le format du point de terminaison est correct (doit être `https://xxx.openai.azure.com`)
3. Vérifiez que la clé API est la clé primaire ou secondaire du portail Azure

**Problème** : 401 Non autorisé de la part d'Azure OpenAI

**Solution** :
1. Obtenez une nouvelle clé API depuis le portail Azure → Clés et point de terminaison
2. Réexportez la variable d'environnement `AZURE_OPENAI_API_KEY`
3. Assurez-vous que les déploiements de modèles sont complets (vérifiez dans le portail Azure)

### Problèmes de performance

**Problème** : Temps de réponse lent

**Solution** :
1. Vérifiez l'utilisation des tokens OpenAI et la limitation dans les métriques du portail Azure
2. Augmentez la capacité TPM si vous atteignez les limites
3. Envisagez d'utiliser un niveau d'effort de raisonnement plus élevé (faible/moyen/élevé)

## Mise à jour de l'infrastructure

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

## Recommandations de sécurité

1. **Ne jamais commettre les clés API** - Utilisez des variables d'environnement
2. **Utilisez des fichiers .env localement** - Ajoutez `.env` à `.gitignore`
3. **Faites tourner les clés régulièrement** - Générez de nouvelles clés dans le portail Azure
4. **Limitez l'accès** - Utilisez Azure RBAC pour contrôler qui peut accéder aux ressources
5. **Surveillez l'utilisation** - Configurez des alertes de coûts dans le portail Azure

## Ressources supplémentaires

- [Documentation du service Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [Documentation du modèle GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Documentation Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Documentation Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Intégration officielle LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Support

Pour les problèmes :
1. Consultez la [section dépannage](../../../../01-introduction/infra) ci-dessus
2. Vérifiez la santé du service Azure OpenAI dans le portail Azure
3. Ouvrez un ticket dans le dépôt

## Licence

Voir le fichier [LICENSE](../../../../LICENSE) à la racine pour plus de détails.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Avertissement** :  
Ce document a été traduit à l’aide du service de traduction automatique [Co-op Translator](https://github.com/Azure/co-op-translator). Bien que nous nous efforcions d’assurer l’exactitude, veuillez noter que les traductions automatiques peuvent contenir des erreurs ou des inexactitudes. Le document original dans sa langue d’origine doit être considéré comme la source faisant foi. Pour les informations critiques, une traduction professionnelle réalisée par un humain est recommandée. Nous déclinons toute responsabilité en cas de malentendus ou de mauvaises interprétations résultant de l’utilisation de cette traduction.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->