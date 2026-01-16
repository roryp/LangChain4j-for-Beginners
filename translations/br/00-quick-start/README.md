<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "22b5d7c8d7585325e38b37fd29eafe25",
  "translation_date": "2026-01-05T23:02:05+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "br"
}
-->
# Módulo 00: Início Rápido

## Índice

- [Introdução](../../../00-quick-start)
- [O que é LangChain4j?](../../../00-quick-start)
- [Dependências LangChain4j](../../../00-quick-start)
- [Pré-requisitos](../../../00-quick-start)
- [Configuração](../../../00-quick-start)
  - [1. Obtenha seu Token do GitHub](../../../00-quick-start)
  - [2. Configure seu Token](../../../00-quick-start)
- [Execute os Exemplos](../../../00-quick-start)
  - [1. Chat Básico](../../../00-quick-start)
  - [2. Padrões de Prompt](../../../00-quick-start)
  - [3. Chamada de Função](../../../00-quick-start)
  - [4. Perguntas e Respostas de Documentos (RAG)](../../../00-quick-start)
  - [5. IA Responsável](../../../00-quick-start)
- [O Que Cada Exemplo Mostra](../../../00-quick-start)
- [Próximos Passos](../../../00-quick-start)
- [Resolução de Problemas](../../../00-quick-start)

## Introdução

Este início rápido tem o objetivo de colocá-lo em funcionamento com LangChain4j o mais rápido possível. Ele cobre o básico absoluto de construir aplicações de IA com LangChain4j e GitHub Models. Nos próximos módulos, você usará Azure OpenAI com LangChain4j para construir aplicações mais avançadas.

## O que é LangChain4j?

LangChain4j é uma biblioteca Java que simplifica a construção de aplicações com IA. Em vez de lidar com clientes HTTP e parsing de JSON, você trabalha com APIs Java limpas.

A "chain" em LangChain refere-se a encadear múltiplos componentes – você pode encadear um prompt a um modelo, a um parser, ou encadear múltiplas chamadas de IA onde uma saída alimenta a próxima entrada. Este início rápido enfoca os fundamentos antes de explorar cadeias mais complexas.

<img src="../../../translated_images/br/langchain-concept.ad1fe6cf063515e1.webp" alt="Conceito de Encadeamento do LangChain4j" width="800"/>

*Componentes encadeados no LangChain4j - blocos de construção conectados para criar fluxos de trabalho de IA poderosos*

Usaremos três componentes principais:

**ChatLanguageModel** - A interface para interações com modelos de IA. Chame `model.chat("prompt")` e obtenha uma string de resposta. Usamos `OpenAiOfficialChatModel` que funciona com endpoints compatíveis com OpenAI, como GitHub Models.

**AiServices** - Cria interfaces de serviço de IA com tipagem segura. Defina métodos, anote-os com `@Tool`, e o LangChain4j gerencia a orquestração. A IA chama automaticamente seus métodos Java quando necessário.

**MessageWindowChatMemory** - Mantém o histórico da conversa. Sem isso, cada requisição é independente. Com ele, a IA lembra mensagens anteriores e mantém contexto por múltiplas interações.

<img src="../../../translated_images/br/architecture.eedc993a1c576839.webp" alt="Arquitetura LangChain4j" width="800"/>

*Arquitetura LangChain4j - componentes principais trabalhando juntos para potencializar suas aplicações de IA*

## Dependências LangChain4j

Este início rápido usa duas dependências Maven no [`pom.xml`](../../../00-quick-start/pom.xml):

```xml
<!-- Core LangChain4j library -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>

<!-- OpenAI integration (works with GitHub Models) -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

O módulo `langchain4j-open-ai-official` fornece a classe `OpenAiOfficialChatModel` que conecta a APIs compatíveis com OpenAI. GitHub Models usa o mesmo formato de API, então nenhum adaptador especial é necessário - apenas aponte a URL base para `https://models.github.ai/inference`.

## Pré-requisitos

**Usando o Container de Desenvolvimento?** Java e Maven já estão instalados. Você só precisa de um Token de Acesso Pessoal do GitHub.

**Desenvolvimento Local:**
- Java 21+, Maven 3.9+
- Token de Acesso Pessoal do GitHub (instruções abaixo)

> **Nota:** Este módulo usa `gpt-4.1-nano` do GitHub Models. Não modifique o nome do modelo no código - ele está configurado para funcionar com os modelos disponíveis no GitHub.

## Configuração

### 1. Obtenha seu Token do GitHub

1. Vá para [Configurações do GitHub → Tokens de Acesso Pessoal](https://github.com/settings/personal-access-tokens)
2. Clique em "Gerar novo token"
3. Defina um nome descritivo (ex.: "Demonstração LangChain4j")
4. Defina a expiração (7 dias recomendados)
5. Em "Permissões da conta", encontre "Models" e defina como "Somente leitura"
6. Clique em "Gerar token"
7. Copie e salve seu token - você não verá ele novamente

### 2. Configure seu Token

**Opção 1: Usando VS Code (Recomendado)**

Se estiver usando VS Code, adicione seu token ao arquivo `.env` na raiz do projeto:

Se o arquivo `.env` não existir, copie `.env.example` para `.env` ou crie um novo arquivo `.env` na raiz do projeto.

**Exemplo de arquivo `.env`:**
```bash
# Em /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

Então você pode simplesmente clicar com o botão direito em qualquer arquivo de demonstração (ex.: `BasicChatDemo.java`) no Explorer e selecionar **"Run Java"** ou usar as configurações de execução no painel Executar e Depurar.

**Opção 2: Usando Terminal**

Configure o token como variável de ambiente:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Execute os Exemplos

**Usando VS Code:** Basta clicar com o botão direito em qualquer arquivo de demonstração no Explorer e selecionar **"Run Java"**, ou usar as configurações de execução no painel Executar e Depurar (certifique-se de ter adicionado seu token ao arquivo `.env` antes).

**Usando Maven:** Alternativamente, você pode executar pela linha de comando:

### 1. Chat Básico

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Padrões de Prompt

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

Mostra prompt zero-shot, few-shot, chain-of-thought, e baseados em papéis.

### 3. Chamada de Função

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

A IA chama automaticamente seus métodos Java quando necessário.

### 4. Perguntas e Respostas de Documentos (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

Faça perguntas sobre o conteúdo em `document.txt`.

### 5. IA Responsável

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

Veja como filtros de segurança da IA bloqueiam conteúdo prejudicial.

## O Que Cada Exemplo Mostra

**Chat Básico** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Comece aqui para ver o LangChain4j na sua forma mais simples. Você criará um `OpenAiOfficialChatModel`, enviará um prompt com `.chat()`, e receberá uma resposta. Isso demonstra a base: como inicializar modelos com endpoints customizados e chaves de API. Uma vez que você entenda esse padrão, tudo o mais é construído a partir dele.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) e pergunte:
> - "Como eu mudaria dos GitHub Models para Azure OpenAI neste código?"
> - "Quais outros parâmetros posso configurar no OpenAiOfficialChatModel.builder()?"
> - "Como adiciono respostas em streaming em vez de esperar pela resposta completa?"

**Engenharia de Prompt** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Agora que você sabe como conversar com um modelo, vamos explorar o que você diz a ele. Esta demo usa a mesma configuração de modelo, mas mostra quatro padrões diferentes de prompting. Experimente prompts zero-shot para instruções diretas, few-shot que aprendem com exemplos, chain-of-thought que revelam passos de raciocínio, e prompts baseados em papéis que estabelecem contexto. Você verá como o mesmo modelo dá resultados dramaticamente diferentes dependendo de como você enquadra seu pedido.

```java
PromptTemplate template = PromptTemplate.from(
    "What's the best time to visit {{destination}} for {{activity}}?"
);

Prompt prompt = template.apply(Map.of(
    "destination", "Paris",
    "activity", "sightseeing"
));

String response = model.chat(prompt.text());
```

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) e pergunte:
> - "Qual é a diferença entre prompt zero-shot e few-shot, e quando devo usar cada um?"
> - "Como o parâmetro temperature afeta as respostas do modelo?"
> - "Quais são algumas técnicas para prevenir ataques de injeção de prompt em produção?"
> - "Como posso criar objetos PromptTemplate reutilizáveis para padrões comuns?"

**Integração de Ferramentas** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Aqui é onde o LangChain4j fica poderoso. Você usará `AiServices` para criar um assistente de IA que pode chamar seus métodos Java. Basta anotar métodos com `@Tool("descrição")` e o LangChain4j faz o resto - a IA decide automaticamente quando usar cada ferramenta baseado no que o usuário pede. Isso demonstra chamada de função, uma técnica chave para construir IA que pode agir, não apenas responder perguntas.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) e pergunte:
> - "Como funciona a anotação @Tool e o que o LangChain4j faz com ela por trás dos panos?"
> - "A IA pode chamar múltiplas ferramentas em sequência para resolver problemas complexos?"
> - "O que acontece se uma ferramenta lança uma exceção - como devo tratar erros?"
> - "Como eu integraria uma API real em vez deste exemplo de calculadora?"

**Perguntas e Respostas de Documentos (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Aqui você verá a base do RAG (recuperação aumentada por geração). Em vez de depender dos dados de treinamento do modelo, você carrega o conteúdo do [`document.txt`](../../../00-quick-start/document.txt) e o inclui no prompt. A IA responde com base no seu documento, não no conhecimento geral dela. Este é o primeiro passo para construir sistemas que trabalham com seus próprios dados.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Nota:** Esta abordagem simples carrega o documento inteiro no prompt. Para arquivos grandes (>10KB), você ultrapassará os limites de contexto. O Módulo 03 cobre segmentação e busca vetorial para sistemas RAG em produção.

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) e pergunte:
> - "Como o RAG previne alucinações da IA comparado ao uso dos dados de treinamento do modelo?"
> - "Qual a diferença entre esta abordagem simples e usar embeddings vetoriais para recuperação?"
> - "Como eu escalaria isso para lidar com múltiplos documentos ou bases de conhecimento maiores?"
> - "Quais são as melhores práticas para estruturar o prompt para garantir que a IA use somente o contexto fornecido?"

**IA Responsável** - [ResponsibleAIDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)

Construa segurança em IA com defesa em profundidade. Esta demo mostra duas camadas de proteção trabalhando juntas:

**Parte 1: LangChain4j Input Guardrails** - Bloqueia prompts perigosos antes de chegarem ao LLM. Crie guardrails customizados que verificam por palavras-chave ou padrões proibidos. Eles rodam no seu código, então são rápidos e gratuitos.

```java
class DangerousContentGuardrail implements InputGuardrail {
    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        String text = userMessage.singleText().toLowerCase();
        if (text.contains("explosives")) {
            return fatal("Blocked: contains prohibited keyword");
        }
        return success();
    }
}
```

**Parte 2: Filtros de Segurança do Provedor** - GitHub Models tem filtros embutidos que pegam o que seus guardrails podem perder. Você verá bloqueios rígidos (erros HTTP 400) para violações graves e recusas suaves onde a IA recusa educadamente.

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`ResponsibleAIDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java) e pergunte:
> - "O que é InputGuardrail e como eu crio o meu próprio?"
> - "Qual é a diferença entre bloqueio rígido e recusa suave?"
> - "Por que usar guardrails e filtros do provedor juntos?"

## Próximos Passos

**Próximo Módulo:** [01-introduction - Começando com LangChain4j e gpt-5 na Azure](../01-introduction/README.md)

---

**Navegação:** [← Voltar ao Início](../README.md) | [Próximo: Módulo 01 - Introdução →](../01-introduction/README.md)

---

## Resolução de Problemas

### Primeira Build Maven

**Problema:** `mvn clean compile` ou `mvn package` iniciais demoram muito (10-15 minutos)

**Causa:** Maven precisa baixar todas as dependências do projeto (Spring Boot, bibliotecas LangChain4j, SDKs Azure, etc.) na primeira build.

**Solução:** Isso é comportamento normal. Builds subsequentes serão muito mais rápidas pois as dependências ficam em cache localmente. O tempo de download depende da velocidade da sua rede.

### Sintaxe de Comando Maven no PowerShell

**Problema:** Comandos Maven falham com erro `Unknown lifecycle phase ".mainClass=..."`

**Causa:** PowerShell interpreta `=` como operador de atribuição de variável, quebrando a sintaxe de propriedade do Maven
**Solução**: Use o operador de parada de análise `--%` antes do comando Maven:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

O operador `--%` diz ao PowerShell para passar todos os argumentos restantes literalmente para o Maven sem interpretação.

### Exibição de Emojis no Windows PowerShell

**Problema**: Respostas da IA mostram caracteres ilegíveis (ex.: `????` ou `â??`) em vez de emojis no PowerShell

**Causa**: A codificação padrão do PowerShell não suporta emojis UTF-8

**Solução**: Execute este comando antes de executar aplicações Java:
```cmd
chcp 65001
```

Isso força a codificação UTF-8 no terminal. Alternativamente, use o Windows Terminal que possui melhor suporte a Unicode.

### Depuração de Chamadas de API

**Problema**: Erros de autenticação, limites de taxa ou respostas inesperadas do modelo de IA

**Solução**: Os exemplos incluem `.logRequests(true)` e `.logResponses(true)` para mostrar chamadas de API no console. Isso ajuda a diagnosticar erros de autenticação, limites de taxa ou respostas inesperadas. Remova essas flags em produção para reduzir o ruído do log.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:
Este documento foi traduzido usando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, por favor, esteja ciente de que traduções automáticas podem conter erros ou imprecisões. O documento original em seu idioma nativo deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se tradução profissional humana. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações incorretas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->