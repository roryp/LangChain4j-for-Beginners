param name string
param location string = resourceGroup().location
param tags object = {}
param kind string = 'OpenAI'
param sku string = 'S0'
param deployments array = []

resource cognitiveServices 'Microsoft.CognitiveServices/accounts@2024-10-01' = {
  name: name
  location: location
  tags: tags
  kind: kind
  sku: {
    name: sku
  }
  properties: {
    customSubDomainName: name
    publicNetworkAccess: 'Enabled'
    networkAcls: {
      defaultAction: 'Allow'
    }
  }
}

// First deployment - gpt-5
resource deployment1 'Microsoft.CognitiveServices/accounts/deployments@2024-10-01' = if (length(deployments) > 0) {
  parent: cognitiveServices
  name: deployments[0].name
  sku: deployments[0].sku
  properties: {
    model: deployments[0].model
  }
}

// Second deployment - text-embedding-3-small (depends on first)
resource deployment2 'Microsoft.CognitiveServices/accounts/deployments@2024-10-01' = if (length(deployments) > 1) {
  parent: cognitiveServices
  name: deployments[1].name
  sku: deployments[1].sku
  properties: {
    model: deployments[1].model
  }
  dependsOn: [
    deployment1
  ]
}

output id string = cognitiveServices.id
output name string = cognitiveServices.name
output endpoint string = cognitiveServices.properties.endpoint
output deploymentNames array = length(deployments) > 0 ? (length(deployments) > 1 ? [deployment1.name, deployment2.name] : [deployment1.name]) : []

#disable-next-line outputs-should-not-contain-secrets
output key string = length(deployments) > 1 ? (deployment2.id != '' ? cognitiveServices.listKeys().key1 : cognitiveServices.listKeys().key1) : (length(deployments) > 0 ? (deployment1.id != '' ? cognitiveServices.listKeys().key1 : cognitiveServices.listKeys().key1) : cognitiveServices.listKeys().key1)
