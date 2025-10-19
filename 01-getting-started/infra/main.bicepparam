using './main.bicep'

param environmentName = readEnvironmentVariable('AZURE_ENV_NAME', 'langchain4j-dev')
param location = readEnvironmentVariable('AZURE_LOCATION', 'eastus')
param principalId = readEnvironmentVariable('AZURE_PRINCIPAL_ID', '')
