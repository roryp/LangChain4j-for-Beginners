<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T21:56:57+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "br"
}
-->
# Infraestrutura Azure para LangChain4j Introdução

## Índice

- [Pré-requisitos](../../../../01-introduction/infra)
- [Arquitetura](../../../../01-introduction/infra)
- [Recursos Criados](../../../../01-introduction/infra)
- [Início Rápido](../../../../01-introduction/infra)
- [Configuração](../../../../01-introduction/infra)
- [Comandos de Gerenciamento](../../../../01-introduction/infra)
- [Otimização de Custos](../../../../01-introduction/infra)
- [Monitoramento](../../../../01-introduction/infra)
- [Solução de Problemas](../../../../01-introduction/infra)
- [Atualizando a Infraestrutura](../../../../01-introduction/infra)
- [Limpeza](../../../../01-introduction/infra)
- [Estrutura de Arquivos](../../../../01-introduction/infra)
- [Recomendações de Segurança](../../../../01-introduction/infra)
- [Recursos Adicionais](../../../../01-introduction/infra)

Este diretório contém a infraestrutura Azure como código (IaC) usando Bicep e Azure Developer CLI (azd) para implantar recursos Azure OpenAI.

## Pré-requisitos

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (versão 2.50.0 ou superior)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (versão 1.5.0 ou superior)
- Uma assinatura Azure com permissões para criar recursos

## Arquitetura

**Configuração Simplificada para Desenvolvimento Local** - Implante apenas Azure OpenAI, execute todos os apps localmente.

A infraestrutura implanta os seguintes recursos Azure:

### Serviços de IA
- **Azure OpenAI**: Serviços Cognitivos com duas implantações de modelo:
  - **gpt-5**: Modelo de conclusão de chat (capacidade 20K TPM)
  - **text-embedding-3-small**: Modelo de embedding para RAG (capacidade 20K TPM)

### Desenvolvimento Local
Todos os aplicativos Spring Boot rodam localmente na sua máquina:
- 01-introduction (porta 8080)
- 02-prompt-engineering (porta 8083)
- 03-rag (porta 8081)
- 04-tools (porta 8084)

## Recursos Criados

| Tipo de Recurso | Padrão do Nome do Recurso | Propósito |
|--------------|----------------------|---------|
| Grupo de Recursos | `rg-{environmentName}` | Contém todos os recursos |
| Azure OpenAI | `aoai-{resourceToken}` | Hospedagem do modelo de IA |

> **Nota:** `{resourceToken}` é uma string única gerada a partir do ID da assinatura, nome do ambiente e localização

## Início Rápido

### 1. Implantar Azure OpenAI

**Bash:**
```bash
cd 01-introduction
azd up
```

**PowerShell:**
```powershell
cd 01-introduction
azd up
```

Quando solicitado:
- Selecione sua assinatura Azure
- Escolha uma localização (recomendado: `eastus2` ou `swedencentral` para disponibilidade GPT-5)
- Confirme o nome do ambiente (padrão: `langchain4j-dev`)

Isso criará:
- Recurso Azure OpenAI com GPT-5 e text-embedding-3-small
- Detalhes de conexão de saída

### 2. Obter Detalhes de Conexão

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Isso exibe:
- `AZURE_OPENAI_ENDPOINT`: URL do endpoint Azure OpenAI
- `AZURE_OPENAI_KEY`: chave API para autenticação
- `AZURE_OPENAI_DEPLOYMENT`: nome do modelo de chat (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: nome do modelo de embedding

### 3. Executar Aplicações Localmente

O comando `azd up` cria automaticamente um arquivo `.env` no diretório raiz com todas as variáveis de ambiente necessárias.

**Recomendado:** Inicie todos os aplicativos web:

**Bash:**
```bash
# A partir do diretório raiz
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# A partir do diretório raiz
cd ../..
.\start-all.ps1
```

Ou inicie um único módulo:

**Bash:**
```bash
# Exemplo: Iniciar apenas o módulo de introdução
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Exemplo: Iniciar apenas o módulo de introdução
cd ../01-introduction
.\start.ps1
```

Ambos os scripts carregam automaticamente as variáveis de ambiente do arquivo `.env` raiz criado pelo `azd up`.

## Configuração

### Personalizando Implantações de Modelo

Para alterar implantações de modelo, edite `infra/main.bicep` e modifique o parâmetro `openAiDeployments`:

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

Modelos e versões disponíveis: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Alterando Regiões Azure

Para implantar em uma região diferente, edite `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Verifique a disponibilidade do GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Para atualizar a infraestrutura após alterações nos arquivos Bicep:

**Bash:**
```bash
# Reconstruir o template ARM
az bicep build --file infra/main.bicep

# Visualizar alterações
azd provision --preview

# Aplicar alterações
azd provision
```

**PowerShell:**
```powershell
# Reconstruir o modelo ARM
az bicep build --file infra/main.bicep

# Visualizar alterações
azd provision --preview

# Aplicar alterações
azd provision
```

## Limpeza

Para excluir todos os recursos:

**Bash:**
```bash
# Excluir todos os recursos
azd down

# Excluir tudo, incluindo o ambiente
azd down --purge
```

**PowerShell:**
```powershell
# Excluir todos os recursos
azd down

# Excluir tudo, incluindo o ambiente
azd down --purge
```

**Aviso**: Isso excluirá permanentemente todos os recursos Azure.

## Estrutura de Arquivos

## Otimização de Custos

### Desenvolvimento/Teste
Para ambientes de dev/teste, você pode reduzir custos:
- Use o nível Standard (S0) para Azure OpenAI
- Defina capacidade menor (10K TPM em vez de 20K) em `infra/core/ai/cognitiveservices.bicep`
- Exclua recursos quando não estiverem em uso: `azd down`

### Produção
Para produção:
- Aumente a capacidade OpenAI conforme uso (50K+ TPM)
- Habilite redundância de zona para maior disponibilidade
- Implemente monitoramento adequado e alertas de custo

### Estimativa de Custos
- Azure OpenAI: Pague por token (entrada + saída)
- GPT-5: ~$3-5 por 1M tokens (verifique preços atuais)
- text-embedding-3-small: ~$0.02 por 1M tokens

Calculadora de preços: https://azure.microsoft.com/pricing/calculator/

## Monitoramento

### Visualizar Métricas Azure OpenAI

Vá ao Portal Azure → Seu recurso OpenAI → Métricas:
- Utilização baseada em tokens
- Taxa de requisições HTTP
- Tempo para resposta
- Tokens ativos

## Solução de Problemas

### Problema: Conflito de nome de subdomínio Azure OpenAI

**Mensagem de Erro:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Causa:**
O nome do subdomínio gerado a partir da sua assinatura/ambiente já está em uso, possivelmente de uma implantação anterior que não foi totalmente removida.

**Solução:**
1. **Opção 1 - Use um nome de ambiente diferente:**
   
   **Bash:**
   ```bash
   azd env new my-unique-env-name
   azd up
   ```
   
   **PowerShell:**
   ```powershell
   azd env new my-unique-env-name
   azd up
   ```

2. **Opção 2 - Implantação manual via Portal Azure:**
   - Vá ao Portal Azure → Criar um recurso → Azure OpenAI
   - Escolha um nome único para seu recurso
   - Implemente os seguintes modelos:
     - **GPT-5**
     - **text-embedding-3-small** (para módulos RAG)
   - **Importante:** Anote os nomes das implantações - eles devem corresponder à configuração `.env`
   - Após a implantação, obtenha seu endpoint e chave API em "Chaves e Endpoint"
   - Crie um arquivo `.env` na raiz do projeto com:
     
     **Exemplo de arquivo `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Diretrizes para Nomeação de Implantação de Modelo:**
- Use nomes simples e consistentes: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Os nomes das implantações devem corresponder exatamente ao que você configura no `.env`
- Erro comum: Criar modelo com um nome e referenciar outro nome no código

### Problema: GPT-5 não disponível na região selecionada

**Solução:**
- Escolha uma região com acesso ao GPT-5 (ex: eastus, swedencentral)
- Verifique disponibilidade: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Problema: Cota insuficiente para implantação

**Solução:**
1. Solicite aumento de cota no Portal Azure
2. Ou use capacidade menor em `main.bicep` (ex: capacity: 10)

### Problema: "Recurso não encontrado" ao rodar localmente

**Solução:**
1. Verifique implantação: `azd env get-values`
2. Confira se endpoint e chave estão corretos
3. Certifique-se que o grupo de recursos existe no Portal Azure

### Problema: Falha na autenticação

**Solução:**
- Verifique se `AZURE_OPENAI_API_KEY` está configurado corretamente
- O formato da chave deve ser uma string hexadecimal de 32 caracteres
- Obtenha nova chave no Portal Azure se necessário

### Falha na Implantação

**Problema**: `azd provision` falha com erros de cota ou capacidade

**Solução**: 
1. Tente uma região diferente - Veja a seção [Alterando Regiões Azure](../../../../01-introduction/infra) para configurar regiões
2. Verifique se sua assinatura tem cota Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Aplicação Não Conecta

**Problema**: Aplicação Java mostra erros de conexão

**Solução**:
1. Verifique se as variáveis de ambiente estão exportadas:
   
   **Bash:**
   ```bash
   echo $AZURE_OPENAI_ENDPOINT
   echo $AZURE_OPENAI_API_KEY
   ```
   
   **PowerShell:**
   ```powershell
   Write-Host $env:AZURE_OPENAI_ENDPOINT
   Write-Host $env:AZURE_OPENAI_API_KEY
   ```

2. Confira se o formato do endpoint está correto (deve ser `https://xxx.openai.azure.com`)
3. Verifique se a chave API é a chave primária ou secundária do Portal Azure

**Problema**: 401 Não autorizado do Azure OpenAI

**Solução**:
1. Obtenha uma chave API nova no Portal Azure → Chaves e Endpoint
2. Reexportar a variável de ambiente `AZURE_OPENAI_API_KEY`
3. Certifique-se que as implantações de modelo estão completas (verifique no Portal Azure)

### Problemas de Desempenho

**Problema**: Tempos de resposta lentos

**Solução**:
1. Verifique uso de tokens OpenAI e limitação nas métricas do Portal Azure
2. Aumente a capacidade TPM se estiver atingindo limites
3. Considere usar um nível de esforço de raciocínio mais alto (baixo/médio/alto)

## Atualizando a Infraestrutura

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

## Recomendações de Segurança

1. **Nunca comite chaves API** - Use variáveis de ambiente
2. **Use arquivos .env localmente** - Adicione `.env` ao `.gitignore`
3. **Roteie chaves regularmente** - Gere novas chaves no Portal Azure
4. **Limite acesso** - Use Azure RBAC para controlar quem pode acessar recursos
5. **Monitore uso** - Configure alertas de custo no Portal Azure

## Recursos Adicionais

- [Documentação do Serviço Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [Documentação do Modelo GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Documentação Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Documentação Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Integração Oficial LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Suporte

Para problemas:
1. Verifique a [seção de solução de problemas](../../../../01-introduction/infra) acima
2. Revise o status do serviço Azure OpenAI no Portal Azure
3. Abra uma issue no repositório

## Licença

Veja o arquivo raiz [LICENSE](../../../../LICENSE) para detalhes.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:  
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, esteja ciente de que traduções automáticas podem conter erros ou imprecisões. O documento original em seu idioma nativo deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se tradução profissional realizada por humanos. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações incorretas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->