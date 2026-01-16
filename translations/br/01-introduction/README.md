<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c3e07ca58d0b8a3f47d3bf5728541e0a",
  "translation_date": "2025-12-13T13:30:35+00:00",
  "source_file": "01-introduction/README.md",
  "language_code": "br"
}
-->
# Módulo 01: Começando com LangChain4j

## Índice

- [O que você vai aprender](../../../01-introduction)
- [Pré-requisitos](../../../01-introduction)
- [Entendendo o problema central](../../../01-introduction)
- [Entendendo tokens](../../../01-introduction)
- [Como a memória funciona](../../../01-introduction)
- [Como isso usa LangChain4j](../../../01-introduction)
- [Implantar infraestrutura Azure OpenAI](../../../01-introduction)
- [Executar a aplicação localmente](../../../01-introduction)
- [Usando a aplicação](../../../01-introduction)
  - [Chat sem estado (painel esquerdo)](../../../01-introduction)
  - [Chat com estado (painel direito)](../../../01-introduction)
- [Próximos passos](../../../01-introduction)

## O que você vai aprender

Se você completou o início rápido, viu como enviar prompts e obter respostas. Essa é a base, mas aplicações reais precisam de mais. Este módulo ensina como construir IA conversacional que lembra o contexto e mantém estado - a diferença entre uma demonstração pontual e uma aplicação pronta para produção.

Usaremos o GPT-5 do Azure OpenAI ao longo deste guia porque suas capacidades avançadas de raciocínio tornam o comportamento dos diferentes padrões mais evidente. Quando você adiciona memória, verá claramente a diferença. Isso facilita entender o que cada componente traz para sua aplicação.

Você construirá uma aplicação que demonstra ambos os padrões:

**Chat sem estado** - Cada requisição é independente. O modelo não tem memória das mensagens anteriores. Este é o padrão que você usou no início rápido.

**Conversa com estado** - Cada requisição inclui o histórico da conversa. O modelo mantém o contexto em múltiplas interações. É isso que aplicações de produção exigem.

## Pré-requisitos

- Assinatura Azure com acesso ao Azure OpenAI
- Java 21, Maven 3.9+
- Azure CLI (https://learn.microsoft.com/en-us/cli/azure/install-azure-cli)
- Azure Developer CLI (azd) (https://learn.microsoft.com/en-us/azure/developer/azure-developer-cli/install-azd)

> **Nota:** Java, Maven, Azure CLI e Azure Developer CLI (azd) já estão pré-instalados no devcontainer fornecido.

> **Nota:** Este módulo usa GPT-5 no Azure OpenAI. A implantação é configurada automaticamente via `azd up` - não modifique o nome do modelo no código.

## Entendendo o problema central

Modelos de linguagem são sem estado. Cada chamada de API é independente. Se você enviar "Meu nome é João" e depois perguntar "Qual é o meu nome?", o modelo não tem ideia de que você acabou de se apresentar. Ele trata cada requisição como se fosse a primeira conversa que você já teve.

Isso é aceitável para perguntas e respostas simples, mas inútil para aplicações reais. Bots de atendimento ao cliente precisam lembrar o que você disse. Assistentes pessoais precisam de contexto. Qualquer conversa com múltiplas interações requer memória.

<img src="../../../translated_images/br/stateless-vs-stateful.cc4a4765e649c41a.png" alt="Conversas sem estado vs com estado" width="800"/>

*A diferença entre conversas sem estado (chamadas independentes) e com estado (cientes do contexto)*

## Entendendo tokens

Antes de mergulhar nas conversas, é importante entender tokens - as unidades básicas de texto que os modelos de linguagem processam:

<img src="../../../translated_images/br/token-explanation.c39760d8ec650181.png" alt="Explicação de token" width="800"/>

*Exemplo de como o texto é dividido em tokens - "Eu amo IA!" vira 4 unidades separadas para processamento*

Tokens são como os modelos de IA medem e processam texto. Palavras, pontuação e até espaços podem ser tokens. Seu modelo tem um limite de quantos tokens pode processar de uma vez (400.000 para GPT-5, com até 272.000 tokens de entrada e 128.000 tokens de saída). Entender tokens ajuda a gerenciar o tamanho da conversa e os custos.

## Como a memória funciona

A memória do chat resolve o problema de ser sem estado mantendo o histórico da conversa. Antes de enviar sua requisição ao modelo, o framework antepõe mensagens anteriores relevantes. Quando você pergunta "Qual é o meu nome?", o sistema na verdade envia todo o histórico da conversa, permitindo que o modelo veja que você disse antes "Meu nome é João."

LangChain4j fornece implementações de memória que fazem isso automaticamente. Você escolhe quantas mensagens manter e o framework gerencia a janela de contexto.

<img src="../../../translated_images/br/memory-window.bbe67f597eadabb3.png" alt="Conceito de janela de memória" width="800"/>

*MessageWindowChatMemory mantém uma janela deslizante das mensagens recentes, descartando automaticamente as antigas*

## Como isso usa LangChain4j

Este módulo estende o início rápido integrando Spring Boot e adicionando memória de conversa. Veja como as peças se encaixam:

**Dependências** - Adicione duas bibliotecas LangChain4j:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

**Modelo de Chat** - Configure Azure OpenAI como um bean Spring ([LangChainConfig.java](../../../01-introduction/src/main/java/com/example/langchain4j/config/LangChainConfig.java)):

```java
@Bean
public OpenAiOfficialChatModel openAiOfficialChatModel() {
    return OpenAiOfficialChatModel.builder()
            .baseUrl(azureEndpoint)
            .apiKey(azureApiKey)
            .modelName(deploymentName)
            .timeout(Duration.ofMinutes(5))
            .maxRetries(3)
            .build();
}
```

O builder lê as credenciais das variáveis de ambiente definidas pelo `azd up`. Definir `baseUrl` para seu endpoint Azure faz o cliente OpenAI funcionar com Azure OpenAI.

**Memória da Conversa** - Acompanhe o histórico do chat com MessageWindowChatMemory ([ConversationService.java](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java)):

```java
ChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

memory.add(UserMessage.from("My name is John"));
memory.add(AiMessage.from("Nice to meet you, John!"));

memory.add(UserMessage.from("What's my name?"));
AiMessage aiMessage = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage);
```

Crie a memória com `withMaxMessages(10)` para manter as últimas 10 mensagens. Adicione mensagens do usuário e da IA com wrappers tipados: `UserMessage.from(text)` e `AiMessage.from(text)`. Recupere o histórico com `memory.messages()` e envie para o modelo. O serviço armazena instâncias de memória separadas por ID de conversa, permitindo múltiplos usuários conversarem simultaneamente.

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`ConversationService.java`](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java) e pergunte:
> - "Como o MessageWindowChatMemory decide quais mensagens descartar quando a janela está cheia?"
> - "Posso implementar armazenamento de memória personalizado usando um banco de dados em vez de memória em RAM?"
> - "Como eu adicionaria sumarização para comprimir o histórico antigo da conversa?"

O endpoint de chat sem estado ignora a memória completamente - apenas `chatModel.chat(prompt)` como no início rápido. O endpoint com estado adiciona mensagens à memória, recupera o histórico e inclui esse contexto em cada requisição. Mesma configuração de modelo, padrões diferentes.

## Implantar infraestrutura Azure OpenAI

**Bash:**
```bash
cd 01-introduction
azd up  # Selecione a assinatura e a localização (eastus2 recomendado)
```

**PowerShell:**
```powershell
cd 01-introduction
azd up  # Selecione a assinatura e a localização (eastus2 recomendado)
```

> **Nota:** Se você encontrar um erro de timeout (`RequestConflict: Cannot modify resource ... provisioning state is not terminal`), simplesmente execute `azd up` novamente. Os recursos do Azure podem ainda estar sendo provisionados em segundo plano, e tentar novamente permite que a implantação seja concluída assim que os recursos atingirem um estado terminal.

Isso irá:
1. Implantar recurso Azure OpenAI com modelos GPT-5 e text-embedding-3-small
2. Gerar automaticamente o arquivo `.env` na raiz do projeto com as credenciais
3. Configurar todas as variáveis de ambiente necessárias

**Está tendo problemas na implantação?** Veja o [README da infraestrutura](infra/README.md) para solução detalhada incluindo conflitos de nome de subdomínio, passos manuais no Portal Azure e orientações de configuração do modelo.

**Verifique se a implantação foi bem-sucedida:**

**Bash:**
```bash
cat ../.env  # Deve mostrar AZURE_OPENAI_ENDPOINT, API_KEY, etc.
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Deve mostrar AZURE_OPENAI_ENDPOINT, API_KEY, etc.
```

> **Nota:** O comando `azd up` gera automaticamente o arquivo `.env`. Se precisar atualizá-lo depois, você pode editar o arquivo `.env` manualmente ou regenerá-lo executando:
>
> **Bash:**
> ```bash
> cd ..
> bash .azd-env.sh
> ```
>
> **PowerShell:**
> ```powershell
> cd ..
> .\.azd-env.ps1
> ```

## Executar a aplicação localmente

**Verifique a implantação:**

Certifique-se de que o arquivo `.env` existe no diretório raiz com as credenciais Azure:

**Bash:**
```bash
cat ../.env  # Deve mostrar AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Deve mostrar AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Inicie as aplicações:**

**Opção 1: Usando o Spring Boot Dashboard (Recomendado para usuários VS Code)**

O dev container inclui a extensão Spring Boot Dashboard, que fornece uma interface visual para gerenciar todas as aplicações Spring Boot. Você pode encontrá-la na Barra de Atividades à esquerda do VS Code (procure o ícone do Spring Boot).

No Spring Boot Dashboard, você pode:
- Ver todas as aplicações Spring Boot disponíveis no workspace
- Iniciar/parar aplicações com um clique
- Visualizar logs da aplicação em tempo real
- Monitorar o status da aplicação

Basta clicar no botão de play ao lado de "introduction" para iniciar este módulo, ou iniciar todos os módulos de uma vez.

<img src="../../../translated_images/br/dashboard.69c7479aef09ff6b.png" alt="Spring Boot Dashboard" width="400"/>

**Opção 2: Usando scripts shell**

Inicie todas as aplicações web (módulos 01-04):

**Bash:**
```bash
cd ..  # A partir do diretório raiz
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # A partir do diretório raiz
.\start-all.ps1
```

Ou inicie apenas este módulo:

**Bash:**
```bash
cd 01-introduction
./start.sh
```

**PowerShell:**
```powershell
cd 01-introduction
.\start.ps1
```

Ambos os scripts carregam automaticamente as variáveis de ambiente do arquivo `.env` raiz e irão construir os JARs se eles não existirem.

> **Nota:** Se preferir construir todos os módulos manualmente antes de iniciar:
>
> **Bash:**
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
>
> **PowerShell:**
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

Abra http://localhost:8080 no seu navegador.

**Para parar:**

**Bash:**
```bash
./stop.sh  # Apenas este módulo
# Ou
cd .. && ./stop-all.sh  # Todos os módulos
```

**PowerShell:**
```powershell
.\stop.ps1  # Apenas este módulo
# Ou
cd ..; .\stop-all.ps1  # Todos os módulos
```

## Usando a aplicação

A aplicação fornece uma interface web com duas implementações de chat lado a lado.

<img src="../../../translated_images/br/home-screen.121a03206ab910c0.png" alt="Tela inicial da aplicação" width="800"/>

*Dashboard mostrando as opções de Chat Simples (sem estado) e Chat Conversacional (com estado)*

### Chat sem estado (painel esquerdo)

Experimente primeiro aqui. Pergunte "Meu nome é João" e logo em seguida "Qual é o meu nome?" O modelo não vai lembrar porque cada mensagem é independente. Isso demonstra o problema central da integração básica com modelos de linguagem - sem contexto de conversa.

<img src="../../../translated_images/br/simple-chat-stateless-demo.13aeb3978eab3234.png" alt="Demonstração de chat sem estado" width="800"/>

*A IA não lembra seu nome da mensagem anterior*

### Chat com estado (painel direito)

Agora tente a mesma sequência aqui. Pergunte "Meu nome é João" e depois "Qual é o meu nome?" Desta vez ela lembra. A diferença é o MessageWindowChatMemory - ele mantém o histórico da conversa e o inclui em cada requisição. É assim que a IA conversacional de produção funciona.

<img src="../../../translated_images/br/conversational-chat-stateful-demo.e5be9822eb23ff59.png" alt="Demonstração de chat com estado" width="800"/>

*A IA lembra seu nome de antes na conversa*

Ambos os painéis usam o mesmo modelo GPT-5. A única diferença é a memória. Isso deixa claro o que a memória traz para sua aplicação e por que é essencial para casos de uso reais.

## Próximos passos

**Próximo módulo:** [02-prompt-engineering - Engenharia de Prompt com GPT-5](../02-prompt-engineering/README.md)

---

**Navegação:** [← Anterior: Módulo 00 - Início Rápido](../00-quick-start/README.md) | [Voltar ao Início](../README.md) | [Próximo: Módulo 02 - Engenharia de Prompt →](../02-prompt-engineering/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:  
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, esteja ciente de que traduções automáticas podem conter erros ou imprecisões. O documento original em seu idioma nativo deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se tradução profissional humana. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações incorretas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->