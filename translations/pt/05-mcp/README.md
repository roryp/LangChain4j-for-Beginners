<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f89f4c106d110e4943c055dd1a2f1dff",
  "translation_date": "2025-12-30T23:39:47+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "pt"
}
-->
# Módulo 05: Model Context Protocol (MCP)

## Table of Contents

- [O que vai aprender](../../../05-mcp)
- [O que é o MCP?](../../../05-mcp)
- [Como o MCP funciona](../../../05-mcp)
- [O Módulo Agentic](../../../05-mcp)
- [Executar os Exemplos](../../../05-mcp)
  - [Pré-requisitos](../../../05-mcp)
- [Início Rápido](../../../05-mcp)
  - [Operações de Ficheiros (Stdio)](../../../05-mcp)
  - [Agente Supervisor](../../../05-mcp)
    - [Compreender a Saída](../../../05-mcp)
    - [Explicação das Funcionalidades do Módulo Agentic](../../../05-mcp)
- [Conceitos Chave](../../../05-mcp)
- [Parabéns!](../../../05-mcp)
  - [O que vem a seguir?](../../../05-mcp)

## O que vai aprender

Você já construiu IA conversacional, dominou prompts, fundamentou respostas em documentos e criou agentes com ferramentas. Mas todas essas ferramentas foram construídas à medida para a sua aplicação específica. E se pudesse dar à sua IA acesso a um ecossistema padronizado de ferramentas que qualquer pessoa pode criar e partilhar? Neste módulo, vai aprender exatamente isso com o Model Context Protocol (MCP) e o módulo agentic do LangChain4j. Primeiro demonstramos um simples leitor de ficheiros MCP e depois mostramos como ele se integra facilmente em fluxos de trabalho agentic avançados usando o padrão Supervisor Agent.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.pt.png" alt="Comparação MCP" width="800"/>

*Antes do MCP: Integrações ponto-a-ponto complexas. Depois do MCP: Um protocolo, possibilidades infinitas.*

O MCP resolve um problema fundamental no desenvolvimento de IA: cada integração é personalizada. Quer aceder ao GitHub? Código personalizado. Quer ler ficheiros? Código personalizado. Quer consultar uma base de dados? Código personalizado. E nenhuma dessas integrações funciona com outras aplicações de IA.

O MCP padroniza isto. Um servidor MCP expõe ferramentas com descrições claras e esquemas de parâmetros. Qualquer cliente MCP pode conectar-se, descobrir as ferramentas disponíveis e usá-las. Construa uma vez, use em todo o lado.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.pt.png" alt="Arquitetura MCP" width="800"/>

*Arquitetura do Model Context Protocol - descoberta e execução de ferramentas padronizadas*

## Como o MCP funciona

**Arquitetura Servidor-Cliente**

O MCP usa um modelo cliente-servidor. Os servidores fornecem ferramentas - ler ficheiros, consultar bases de dados, chamar APIs. Os clientes (a sua aplicação de IA) conectam-se aos servidores e usam as suas ferramentas.

Para usar o MCP com LangChain4j, adicione esta dependência Maven:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**Descoberta de Ferramentas**

Quando o seu cliente se conecta a um servidor MCP, pergunta "Que ferramentas tens?" O servidor responde com uma lista de ferramentas disponíveis, cada uma com descrições e esquemas de parâmetros. O seu agente de IA pode então decidir que ferramentas usar com base nos pedidos do utilizador.

**Mecanismos de Transporte**

O MCP suporta diferentes mecanismos de transporte. Este módulo demonstra o transporte Stdio para processos locais:

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.pt.png" alt="Mecanismos de Transporte" width="800"/>

*Mecanismos de transporte do MCP: HTTP para servidores remotos, Stdio para processos locais*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Para processos locais. A sua aplicação inicia um servidor como um subprocesso e comunica através da entrada/saída padrão. Útil para acesso ao sistema de ficheiros ou ferramentas de linha de comandos.

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@2025.12.18",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 Experimente com o Chat do [GitHub Copilot](https://github.com/features/copilot):** Abra [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) e pergunte:
> - "Como funciona o transporte Stdio e quando devo usá-lo em vez de HTTP?"
> - "Como é que o LangChain4j gere o ciclo de vida dos processos de servidor MCP iniciados como subprocessos?"
> - "Quais são as implicações de segurança de dar à IA acesso ao sistema de ficheiros?"

## O Módulo Agentic

Enquanto o MCP fornece ferramentas padronizadas, o módulo **agentic** do LangChain4j fornece uma forma declarativa de construir agentes que orquestram essas ferramentas. A anotação `@Agent` e o `AgenticServices` permitem definir o comportamento do agente através de interfaces em vez de código imperativo.

Neste módulo, vai explorar o padrão **Supervisor Agent** — uma abordagem agentic avançada onde um agente "supervisor" decide dinamicamente quais sub-agentes invocar com base nos pedidos dos utilizadores. Vamos combinar ambos os conceitos dando a um dos nossos sub-agentes capacidades de acesso a ficheiros potenciadas pelo MCP.

Para usar o módulo agentic, adicione esta dependência Maven:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ Experimental:** O módulo `langchain4j-agentic` é **experimental** e sujeito a alterações. A forma estável de construir assistentes de IA continua a ser o `langchain4j-core` com ferramentas personalizadas (Módulo 04).

## Executar os Exemplos

### Pré-requisitos

- Java 21+, Maven 3.9+
- Node.js 16+ e npm (para servidores MCP)
- Variáveis de ambiente configuradas no ficheiro `.env` (a partir do diretório raiz):
  - **Para StdioTransportDemo:** `GITHUB_TOKEN` (Token de Acesso Pessoal do GitHub)
  - **Para SupervisorAgentDemo:** `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (mesmo que nos Módulos 01-04)

> **Nota:** Se ainda não configurou as suas variáveis de ambiente, veja [Módulo 00 - Início Rápido](../00-quick-start/README.md) para instruções, ou copie `.env.example` para `.env` no diretório raiz e preencha os seus valores.

## Início Rápido

**Usando o VS Code:** Basta clicar com o botão direito em qualquer ficheiro de demonstração no Explorer e selecionar **"Run Java"**, ou usar as configurações de lançamento no painel Run and Debug (certifique-se de que adicionou o seu token ao ficheiro `.env` primeiro).

**Usando o Maven:** Alternativamente, pode executar a partir da linha de comando com os exemplos abaixo.

### Operações de Ficheiros (Stdio)

Isto demonstra ferramentas baseadas em subprocessos locais.

**✅ Nenhum pré-requisito necessário** - o servidor MCP é iniciado automaticamente.

**Usando o VS Code:** Clique com o botão direito em `StdioTransportDemo.java` e selecione **"Run Java"**.

**Usando o Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

A aplicação inicia automaticamente um servidor MCP de sistema de ficheiros e lê um ficheiro local. Repare como a gestão do subprocesso é tratada por si.

**Saída esperada:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Agente Supervisor

<img src="../../../translated_images/agentic.cf84dcda226374e3.pt.png" alt="Módulo Agentic" width="800"/>


O **padrão Supervisor Agent** é uma forma **flexível** de IA agentic. Ao contrário de workflows determinísticos (sequenciais, em loop, paralelos), um Supervisor usa um LLM para decidir autonomamente quais agentes invocar com base no pedido do utilizador.

**Combinar Supervisor com MCP:** Neste exemplo, damos ao `FileAgent` acesso às ferramentas de sistema de ficheiros do MCP através de `toolProvider(mcpToolProvider)`. Quando um utilizador pede para "ler e analisar um ficheiro", o Supervisor analisa o pedido e gera um plano de execução. Em seguida encaminha o pedido para o `FileAgent`, que usa a ferramenta `read_file` do MCP para recuperar o conteúdo. O Supervisor passa esse conteúdo ao `AnalysisAgent` para interpretação, e opcionalmente invoca o `SummaryAgent` para condensar os resultados.

Isto demonstra como as ferramentas MCP se integram sem esforço em fluxos de trabalho agentic — o Supervisor não precisa de saber *como* os ficheiros são lidos, apenas que o `FileAgent` consegue fazê-lo. O Supervisor adapta-se dinamicamente a diferentes tipos de pedidos e devolve ou a resposta do último agente ou um resumo de todas as operações.

**Usando os scripts de arranque (Recomendado):**

Os scripts de arranque carregam automaticamente as variáveis de ambiente do ficheiro `.env` na raiz:

**Bash:**
```bash
cd 05-mcp
chmod +x start.sh
./start.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start.ps1
```

**Usando o VS Code:** Clique com o botão direito em `SupervisorAgentDemo.java` e selecione **"Run Java"** (garanta que o seu ficheiro `.env` está configurado).

**Como o Supervisor funciona:**

```java
// Define múltiplos agentes com capacidades específicas
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // Possui ferramentas MCP para operações de ficheiros
        .build();

AnalysisAgent analysisAgent = AgenticServices.agentBuilder(AnalysisAgent.class)
        .chatModel(model)
        .build();

SummaryAgent summaryAgent = AgenticServices.agentBuilder(SummaryAgent.class)
        .chatModel(model)
        .build();

// Cria um Supervisor que orquestra estes agentes
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)  // O modelo "planner"
        .subAgents(fileAgent, analysisAgent, summaryAgent)
        .responseStrategy(SupervisorResponseStrategy.SUMMARY)
        .build();

// O Supervisor decide de forma autónoma quais agentes invocar
// Basta passar um pedido em linguagem natural - o LLM planeia a execução
String response = supervisor.invoke("Read the file at /path/file.txt and analyze it");
```

Veja [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) para a implementação completa.

> **🤖 Experimente com o Chat do [GitHub Copilot](https://github.com/features/copilot):** Abra [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) e pergunte:
> - "Como é que o Supervisor decide quais agentes invocar?"
> - "Qual é a diferença entre o padrão Supervisor e padrões de workflow sequenciais?"
> - "Como posso personalizar o comportamento de planeamento do Supervisor?"

#### Compreender a Saída

Quando executar a demo, verá um passo a passo estruturado de como o Supervisor orquestra múltiplos agentes. Eis o que cada secção significa:

```
======================================================================
  SUPERVISOR AGENT DEMO
======================================================================

This demo shows how a Supervisor Agent orchestrates multiple specialized agents.
The Supervisor uses an LLM to decide which agent to call based on the task.
```

**O cabeçalho** introduz a demo e explica o conceito principal: o Supervisor usa um LLM (não regras codificadas) para decidir que agentes chamar.

```
--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]     FileAgent     - Reads files using MCP filesystem tools
  [ANALYZE]  AnalysisAgent - Analyzes content for structure, tone, and themes
  [SUMMARY]  SummaryAgent  - Creates concise summaries of content
```

**Agentes Disponíveis** mostra os três agentes especializados que o Supervisor pode escolher. Cada agente tem uma capacidade específica:
- **FileAgent** pode ler ficheiros usando ferramentas MCP (capacidade externa)
- **AnalysisAgent** analisa conteúdo (capacidade puramente LLM)
- **SummaryAgent** cria resumos (capacidade puramente LLM)

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and analyze what it's about"
```

**Pedido do Utilizador** mostra o que foi pedido. O Supervisor tem de analisar isto e decidir quais agentes invocar.

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor will now decide which agents to invoke and in what order...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source Java library designed to simplify...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> AnalysisAgent (analyzing content)
  |
  |   Input: LangChain4j is an open-source Java library...
  |
  |   Result: Structure: The content is organized into clear paragraphs that int...
  +-- [OK] AnalysisAgent (analyzing content) completed
```

**Orquestração do Supervisor** é onde a magia acontece. Observe como:
1. O Supervisor **escolheu o FileAgent primeiro** porque o pedido mencionava "ler o ficheiro"
2. O FileAgent usou a ferramenta `read_file` do MCP para recuperar o conteúdo do ficheiro
3. O Supervisor depois **escolheu o AnalysisAgent** e passou-lhe o conteúdo do ficheiro
4. O AnalysisAgent analisou a estrutura, o tom e os temas

Repare que o Supervisor tomou estas decisões **autonomamente** com base no pedido do utilizador — sem workflow codificado!

**Resposta Final** é a resposta sintetizada do Supervisor, combinando as saídas de todos os agentes que invocou. O exemplo mostra o âmbito agentic (agentic scope) com o resumo e os resultados de análise armazenados por cada agente.

```
--- FINAL RESPONSE ---------------------------------------------------
I read the contents of the file and analyzed its structure, tone, and key themes.
The file introduces LangChain4j as an open-source Java library for integrating
large language models...

--- AGENTIC SCOPE (Shared Memory) ------------------------------------
  Agents store their results in a shared scope for other agents to use:
  * summary: LangChain4j is an open-source Java library...
  * analysis: Structure: The content is organized into clear paragraphs that in...
```

### Explicação das Funcionalidades do Módulo Agentic

O exemplo demonstra várias funcionalidades avançadas do módulo agentic. Vamos analisar mais de perto o Agentic Scope e os Agent Listeners.

**Agentic Scope** mostra a memória partilhada onde os agentes armazenaram os seus resultados usando `@Agent(outputKey="...")`. Isto permite:
- A agentes posteriores acederem às saídas de agentes anteriores
- O Supervisor sintetizar uma resposta final
- Inspecionar o que cada agente produziu

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String story = scope.readState("story");
List<AgentInvocation> history = scope.agentInvocations("analysisAgent");
```

**Agent Listeners** permitem monitorizar e depurar a execução dos agentes. A saída passo a passo que vê na demo vem de um AgentListener que se liga a cada invocação de agente:
- **beforeAgentInvocation** - Chamado quando o Supervisor seleciona um agente, permitindo ver qual agente foi escolhido e porquê
- **afterAgentInvocation** - Chamado quando um agente conclui, mostrando o seu resultado
- **inheritedBySubagents** - Quando verdadeiro, o listener monitoriza todos os agentes na hierarquia

```java
AgentListener monitor = new AgentListener() {
    private int step = 0;
    
    @Override
    public void beforeAgentInvocation(AgentRequest request) {
        step++;
        System.out.println("  +-- STEP " + step + ": " + request.agentName());
    }
    
    @Override
    public void afterAgentInvocation(AgentResponse response) {
        System.out.println("  +-- [OK] " + response.agentName() + " completed");
    }
    
    @Override
    public boolean inheritedBySubagents() {
        return true; // Propagar para todos os subagentes
    }
};
```

Para além do padrão Supervisor, o módulo `langchain4j-agentic` fornece vários padrões de workflow e funcionalidades poderosas:

| Pattern | Description | Use Case |
|---------|-------------|----------|
| **Sequential** | Execute agentes por ordem, a saída flui para o próximo | Pipelines: pesquisa → analisar → relatório |
| **Parallel** | Execute agentes em simultâneo | Tarefas independentes: tempo + notícias + ações |
| **Loop** | Iterar até que a condição seja cumprida | Avaliação de qualidade: refinar até que a pontuação ≥ 0.8 |
| **Conditional** | Encaminhar com base em condições | Classificar → encaminhar para agente especialista |
| **Human-in-the-Loop** | Adicionar pontos de verificação humanos | Workflows de aprovação, revisão de conteúdo |

## Conceitos Chave

**MCP** é ideal quando quer aproveitar ecossistemas de ferramentas existentes, construir ferramentas que múltiplas aplicações possam partilhar, integrar serviços de terceiros com protocolos standard, ou trocar implementações de ferramentas sem alterar o código.

**O Módulo Agentic** funciona melhor quando quer definições declarativas de agentes com anotações `@Agent`, precisa de orquestração de workflows (sequencial, loop, paralelo), prefere desenho de agentes baseado em interfaces em vez de código imperativo, ou está a combinar múltiplos agentes que partilham saídas via `outputKey`.

**O padrão Supervisor Agent** destaca-se quando o workflow não é previsível de antemão e quer que o LLM decida, quando tem múltiplos agentes especializados que precisam de orquestração dinâmica, quando constrói sistemas conversacionais que roteiam para capacidades diferentes, ou quando quer o comportamento de agente mais flexível e adaptativo.

## Parabéns!

Concluiu o curso LangChain4j for Beginners. Aprendeu:

- Como construir IA conversacional com memória (Módulo 01)
- Padrões de engenharia de prompts para diferentes tarefas (Módulo 02)
- Fundamentar respostas nos seus documentos com RAG (Módulo 03)
- Criar agentes de IA básicos (assistentes) com ferramentas personalizadas (Módulo 04)
- Integração de ferramentas padronizadas com os módulos LangChain4j MCP e Agentic (Módulo 05)

### O que vem a seguir?

Depois de concluir os módulos, explore o [Guia de Testes](../docs/TESTING.md) para ver os conceitos de testes do LangChain4j em ação.

**Recursos Oficiais:**
- [Documentação do LangChain4j](https://docs.langchain4j.dev/) - Guias abrangentes e referência da API
- [GitHub do LangChain4j](https://github.com/langchain4j/langchain4j) - Código-fonte e exemplos
- [Tutoriais do LangChain4j](https://docs.langchain4j.dev/tutorials/) - Tutoriais passo a passo para vários casos de uso

Obrigado por concluir este curso!

---

**Navegação:** [← Anterior: Módulo 04 - Ferramentas](../04-tools/README.md) | [Voltar ao Início](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Aviso legal:
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos pela precisão, por favor note que traduções automáticas podem conter erros ou imprecisões. O documento original na sua língua nativa deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se recorrer a uma tradução profissional humana. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações erradas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->