<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "6c816d130a1fa47570c11907e72d84ae",
  "translation_date": "2026-01-05T23:03:23+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "br"
}
-->
# Módulo 05: Protocolo de Contexto de Modelo (MCP)

## Sumário

- [O que você vai aprender](../../../05-mcp)
- [O que é MCP?](../../../05-mcp)
- [Como o MCP funciona](../../../05-mcp)
- [O Módulo Agentic](../../../05-mcp)
- [Executando os exemplos](../../../05-mcp)
  - [Pré-requisitos](../../../05-mcp)
- [Início rápido](../../../05-mcp)
  - [Operações com arquivos (Stdio)](../../../05-mcp)
  - [Agente Supervisor](../../../05-mcp)
    - [Compreendendo a saída](../../../05-mcp)
    - [Estratégias de resposta](../../../05-mcp)
    - [Explicação sobre recursos do módulo Agentic](../../../05-mcp)
- [Conceitos-chave](../../../05-mcp)
- [Parabéns!](../../../05-mcp)
  - [Qual o próximo passo?](../../../05-mcp)

## O que você vai aprender

Você já construiu IA conversacional, dominou prompts, fundamentou respostas em documentos e criou agentes com ferramentas. Mas todas essas ferramentas foram construídas sob medida para sua aplicação específica. E se você pudesse dar à sua IA acesso a um ecossistema padronizado de ferramentas que qualquer um pode criar e compartilhar? Neste módulo, você aprenderá exatamente isso com o Protocolo de Contexto de Modelo (MCP) e o módulo agentic do LangChain4j. Primeiro mostramos um leitor de arquivos MCP simples e depois mostramos como ele se integra facilmente em fluxos de trabalho agentic avançados usando o padrão Agente Supervisor.

## O que é MCP?

O Protocolo de Contexto de Modelo (MCP) fornece exatamente isso – uma maneira padrão para aplicações de IA descobrirem e usarem ferramentas externas. Em vez de escrever integrações personalizadas para cada fonte de dados ou serviço, você se conecta a servidores MCP que expõem suas capacidades em um formato consistente. Seu agente de IA pode então descobrir e usar essas ferramentas automaticamente.

<img src="../../../translated_images/br/mcp-comparison.9129a881ecf10ff5.png" alt="MCP Comparison" width="800"/>

*Antes do MCP: Integrações complexas ponto a ponto. Depois do MCP: Um protocolo, possibilidades infinitas.*

O MCP resolve um problema fundamental no desenvolvimento de IA: toda integração é customizada. Quer acessar o GitHub? Código personalizado. Quer ler arquivos? Código personalizado. Quer consultar um banco de dados? Código personalizado. E nenhuma dessas integrações funciona com outras aplicações de IA.

O MCP padroniza isso. Um servidor MCP expõe ferramentas com descrições claras e esquemas. Qualquer cliente MCP pode conectar-se, descobrir ferramentas disponíveis e usá-las. Construa uma vez, use em qualquer lugar.

<img src="../../../translated_images/br/mcp-architecture.b3156d787a4ceac9.png" alt="MCP Architecture" width="800"/>

*Arquitetura do Protocolo de Contexto de Modelo - descoberta e execução padronizadas de ferramentas*

## Como o MCP funciona

**Arquitetura Cliente-Servidor**

O MCP usa um modelo cliente-servidor. Servidores fornecem ferramentas – leitura de arquivos, consultas a bancos de dados, chamadas de APIs. Clientes (sua aplicação de IA) conectam-se aos servidores e usam suas ferramentas.

Para usar MCP com LangChain4j, adicione esta dependência no Maven:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**Descoberta de Ferramentas**

Quando seu cliente conecta a um servidor MCP, ele pergunta “Quais ferramentas você tem?” O servidor responde com uma lista de ferramentas disponíveis, cada uma com descrições e esquemas dos parâmetros. Seu agente de IA pode então decidir quais ferramentas usar com base nas solicitações do usuário.

**Mecanismos de Transporte**

O MCP suporta diferentes mecanismos de transporte. Este módulo demonstra o transporte Stdio para processos locais:

<img src="../../../translated_images/br/transport-mechanisms.2791ba7ee93cf020.png" alt="Transport Mechanisms" width="800"/>

*Mecanismos de transporte MCP: HTTP para servidores remotos, Stdio para processos locais*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Para processos locais. Sua aplicação cria um servidor como subprocesso e se comunica via entrada/saída padrão. Útil para acesso ao sistema de arquivos ou ferramentas de linha de comando.

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

> **🤖 Experimente com [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) e pergunte:
> - "Como funciona o transporte Stdio e quando devo usá-lo em vez de HTTP?"
> - "Como o LangChain4j gerencia o ciclo de vida dos processos dos servidores MCP criados?"
> - "Quais são as implicações de segurança de dar acesso da IA ao sistema de arquivos?"

## O Módulo Agentic

Enquanto o MCP fornece ferramentas padronizadas, o **módulo agentic** do LangChain4j oferece uma forma declarativa de construir agentes que orquestram essas ferramentas. A anotação `@Agent` e o `AgenticServices` permitem definir o comportamento do agente por meio de interfaces ao invés de código imperativo.

Neste módulo, você vai explorar o padrão **Agente Supervisor** — uma abordagem avançada de IA agentic onde um agente "supervisor" decide dinamicamente quais subagentes invocar com base nas solicitações do usuário. Vamos combinar ambos os conceitos dando a um dos nossos subagentes capacidades de acesso a arquivos impulsionadas pelo MCP.

Para usar o módulo agentic, adicione esta dependência no Maven:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ Experimental:** O módulo `langchain4j-agentic` é **experimental** e sujeito a mudanças. A forma estável de construir assistentes de IA permanece sendo o `langchain4j-core` com ferramentas customizadas (Módulo 04).

## Executando os exemplos

### Pré-requisitos

- Java 21+, Maven 3.9+
- Node.js 16+ e npm (para servidores MCP)
- Variáveis de ambiente configuradas no arquivo `.env` (a partir do diretório raiz):
  - `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (mesmo que os Módulos 01-04)

> **Nota:** Se você ainda não configurou suas variáveis de ambiente, veja [Módulo 00 - Início Rápido](../00-quick-start/README.md) para instruções, ou copie `.env.example` para `.env` no diretório raiz e preencha seus valores.

## Início rápido

**Usando VS Code:** Simplesmente clique com o botão direito em qualquer arquivo de demo no Explorer e selecione **"Run Java"**, ou use as configurações de execução no painel Executar e Depurar (certifique-se de ter adicionado seu token ao arquivo `.env` antes).

**Usando Maven:** Alternativamente, você pode rodar pela linha de comando com os exemplos abaixo.

### Operações com arquivos (Stdio)

Isso demonstra ferramentas baseadas em subprocessos locais.

**✅ Nenhum pré-requisito necessário** - o servidor MCP é criado automaticamente.

**Usando os scripts de inicialização (recomendado):**

Os scripts de inicialização carregam automaticamente as variáveis de ambiente do arquivo `.env` no diretório raiz:

**Bash:**
```bash
cd 05-mcp
chmod +x start-stdio.sh
./start-stdio.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start-stdio.ps1
```

**Usando VS Code:** Clique com o botão direito em `StdioTransportDemo.java` e selecione **"Run Java"** (certifique-se que seu arquivo `.env` está configurado).

A aplicação cria um servidor MCP do sistema de arquivos automaticamente e lê um arquivo local. Observe como o gerenciamento do subprocesso é feito para você.

**Saída esperada:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### Agente Supervisor

O **padrão Agente Supervisor** é uma forma **flexível** de IA agentic. Um Supervisor usa um LLM para decidir autonomamente quais agentes invocar com base na solicitação do usuário. No próximo exemplo, combinamos acesso a arquivos impulsionado por MCP com um agente LLM para criar um fluxo de leitura de arquivos supervisionado → relatório.

Na demonstração, `FileAgent` lê um arquivo usando ferramentas MCP do sistema de arquivos, e `ReportAgent` gera um relatório estruturado com um resumo executivo (1 frase), 3 pontos principais e recomendações. O Supervisor orquestra esse fluxo automaticamente:

<img src="../../../translated_images/br/agentic.cf84dcda226374e3.png" alt="Agentic Module" width="800"/>

```
┌─────────────┐      ┌──────────────┐
│  FileAgent  │ ───▶ │ ReportAgent  │
│ (MCP tools) │      │  (pure LLM)  │
└─────────────┘      └──────────────┘
   outputKey:           outputKey:
  'fileContent'         'report'
```

Cada agente armazena sua saída no **Agentic Scope** (memória compartilhada), permitindo que agentes subsequentes acessem resultados anteriores. Isso demonstra como ferramentas MCP se integram perfeitamente em fluxos agentics — o Supervisor não precisa saber *como* os arquivos são lidos, apenas que o `FileAgent` pode fazer isso.

#### Executando a demo

Os scripts de inicialização carregam automaticamente as variáveis de ambiente do arquivo `.env` no diretório raiz:

**Bash:**
```bash
cd 05-mcp
chmod +x start-supervisor.sh
./start-supervisor.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start-supervisor.ps1
```

**Usando VS Code:** Clique com o botão direito em `SupervisorAgentDemo.java` e selecione **"Run Java"** (certifique-se que seu arquivo `.env` está configurado).

#### Como o Supervisor funciona

```java
// Etapa 1: FileAgent lê arquivos usando ferramentas MCP
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // Possui ferramentas MCP para operações de arquivos
        .build();

// Etapa 2: ReportAgent gera relatórios estruturados
ReportAgent reportAgent = AgenticServices.agentBuilder(ReportAgent.class)
        .chatModel(model)
        .build();

// Supervisor orquestra o fluxo de trabalho arquivo → relatório
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)
        .subAgents(fileAgent, reportAgent)
        .responseStrategy(SupervisorResponseStrategy.LAST)  // Retorna o relatório final
        .build();

// O Supervisor decide quais agentes invocar com base na solicitação
String response = supervisor.invoke("Read the file at /path/file.txt and generate a report");
```

#### Estratégias de resposta

Ao configurar um `SupervisorAgent`, você especifica como ele deve formular sua resposta final ao usuário depois que os subagentes completam suas tarefas. As estratégias disponíveis são:

| Estratégia | Descrição |
|------------|-----------|
| **LAST**   | O supervisor retorna a saída do último subagente ou ferramenta chamado. Isso é útil quando o agente final no fluxo é projetado especificamente para produzir a resposta final completa (ex: um "Agente de Resumo" em um pipeline de pesquisa). |
| **SUMMARY**| O supervisor usa seu próprio Modelo de Linguagem Interno (LLM) para sintetizar um resumo de toda a interação e de todas as saídas dos subagentes, retornando esse resumo como resposta final. Isso fornece uma resposta limpa e agregada ao usuário. |
| **SCORED** | O sistema usa um LLM interno para pontuar tanto a resposta LAST quanto o SUMMARY da interação em relação à solicitação original do usuário, retornando a saída com maior pontuação. |

Veja [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) para a implementação completa.

> **🤖 Experimente com [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) e pergunte:
> - "Como o Supervisor decide quais agentes invocar?"
> - "Qual a diferença entre os padrões Supervisor e Workflow Sequencial?"
> - "Como posso customizar o comportamento de planejamento do Supervisor?"

#### Compreendendo a saída

Quando você executar a demo, verá um passo a passo estruturado de como o Supervisor orquestra múltiplos agentes. Aqui está o que cada seção significa:

```
======================================================================
  FILE → REPORT WORKFLOW DEMO
======================================================================

This demo shows a clear 2-step workflow: read a file, then generate a report.
The Supervisor orchestrates the agents automatically based on the request.
```

**O cabeçalho** apresenta o conceito do fluxo de trabalho: pipeline focado de leitura de arquivos para geração de relatório.

```
--- WORKFLOW ---------------------------------------------------------
  ┌─────────────┐      ┌──────────────┐
  │  FileAgent  │ ───▶ │ ReportAgent  │
  │ (MCP tools) │      │  (pure LLM)  │
  └─────────────┘      └──────────────┘
   outputKey:           outputKey:
   'fileContent'        'report'

--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]   FileAgent   - Reads files via MCP → stores in 'fileContent'
  [REPORT] ReportAgent - Generates structured report → stores in 'report'
```

**Diagrama do fluxo de trabalho** mostra o fluxo de dados entre agentes. Cada agente tem um papel específico:
- **FileAgent** lê arquivos usando ferramentas MCP e armazena o conteúdo bruto em `fileContent`
- **ReportAgent** consome esse conteúdo e produz um relatório estruturado em `report`

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and generate a report on its contents"
```

**Solicitação do usuário** mostra a tarefa. O Supervisor interpreta isso e decide invocar FileAgent → ReportAgent.

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor decides which agents to invoke and passes data between them...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source, provider-agnostic Java framework for building LLM...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> ReportAgent (generating structured report)
  |
  |   Input: LangChain4j is an open-source, provider-agnostic Java framew...
  |
  |   Result: Executive Summary...
  +-- [OK] ReportAgent (generating structured report) completed
```

**Orquestração do Supervisor** mostra o fluxo em 2 etapas na prática:
1. **FileAgent** lê o arquivo via MCP e armazena o conteúdo
2. **ReportAgent** recebe o conteúdo e gera um relatório estruturado

O Supervisor tomou essas decisões **autonomamente** com base na solicitação do usuário.

```
--- FINAL RESPONSE ---------------------------------------------------
Executive Summary
...

Key Points
...

Recommendations
...

--- AGENTIC SCOPE (Data Flow) ----------------------------------------
  Each agent stores its output for downstream agents to consume:
  * fileContent: LangChain4j is an open-source, provider-agnostic Java framework...
  * report: Executive Summary...
```

#### Explicação sobre recursos do módulo Agentic

O exemplo demonstra vários recursos avançados do módulo agentic. Vamos olhar mais de perto o Agentic Scope e os Agent Listeners.

**Agentic Scope** mostra a memória compartilhada onde os agentes armazenaram seus resultados usando `@Agent(outputKey="...")`. Isso permite:
- Agentes posteriores acessarem saídas de agentes anteriores
- O Supervisor sintetizar uma resposta final
- Você inspecionar o que cada agente produziu

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String fileContent = scope.readState("fileContent");  // Dados brutos do arquivo do FileAgent
String report = scope.readState("report");            // Relatório estruturado do ReportAgent
```

**Agent Listeners** permitem monitorar e depurar a execução dos agentes. A saída passo a passo que você vê na demo vem de um AgentListener que é conectado a cada invocação de agente:
- **beforeAgentInvocation** - Chamado quando o Supervisor seleciona um agente, permitindo ver qual agente foi escolhido e por quê
- **afterAgentInvocation** - Chamado quando um agente termina, mostrando seu resultado
- **inheritedBySubagents** - Quando true, o listener monitora todos os agentes na hierarquia

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

Além do padrão Supervisor, o módulo `langchain4j-agentic` oferece vários padrões e recursos poderosos para workflows:

| Padrão             | Descrição                                  | Caso de uso                                  |
|--------------------|--------------------------------------------|---------------------------------------------|
| **Sequencial**     | Executa agentes em ordem, saída flui para o próximo | Pipelines: pesquisa → análise → relatório   |
| **Paralelo**       | Executa agentes simultaneamente              | Tarefas independentes: clima + notícias + ações |
| **Loop**           | Itera até que a condição seja atendida        | Avaliação de qualidade: refinar até pontuação ≥ 0,8 |
| **Condicional**    | Roteia com base em condições                   | Classificar → direcionar para agente especialista |
| **Human-in-the-Loop** | Adiciona pontos de checagem humana           | Fluxos de aprovação, revisão de conteúdo     |

## Conceitos-chave

Agora que você explorou MCP e o módulo agentic em ação, vamos resumir quando usar cada abordagem.

**MCP** é ideal quando você quer aproveitar ecossistemas de ferramentas existentes, construir ferramentas que múltiplas aplicações possam compartilhar, integrar serviços de terceiros com protocolos padrão ou trocar implementações de ferramentas sem mudar código.

**O Módulo Agentic** funciona melhor quando você quer definições declarativas de agentes com anotações `@Agent`, precisa de orquestração de workflow (sequencial, loop, paralelo), prefere design de agentes baseado em interfaces ao invés de código imperativo, ou está combinando múltiplos agentes que compartilham saídas via `outputKey`.

**O padrão Agente Supervisor** brilha quando o fluxo de trabalho não é previsível de antemão e você quer que o LLM decida, quando tem múltiplos agentes especializados que precisam de orquestração dinâmica, ao construir sistemas conversacionais que direcionam para diferentes capacidades, ou quando você quer o comportamento de agente mais flexível e adaptativo.
## Parabéns!

Você concluiu o curso LangChain4j para Iniciantes. Você aprendeu:

- Como construir IA conversacional com memória (Módulo 01)
- Padrões de engenharia de prompts para diferentes tarefas (Módulo 02)
- Fundamentar respostas em seus documentos com RAG (Módulo 03)
- Criar agentes básicos de IA (assistentes) com ferramentas personalizadas (Módulo 04)
- Integrar ferramentas padronizadas com os módulos MCP e Agentic do LangChain4j (Módulo 05)

### E agora?

Após concluir os módulos, explore o [Guia de Testes](../docs/TESTING.md) para ver conceitos de testes do LangChain4j em ação.

**Recursos Oficiais:**
- [Documentação LangChain4j](https://docs.langchain4j.dev/) - Guias completos e referência de API
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Código-fonte e exemplos
- [Tutoriais LangChain4j](https://docs.langchain4j.dev/tutorials/) - Tutoriais passo a passo para vários casos de uso

Obrigado por concluir este curso!

---

**Navegação:** [← Anterior: Módulo 04 - Ferramentas](../04-tools/README.md) | [Voltar ao Início](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, esteja ciente de que traduções automáticas podem conter erros ou imprecisões. O documento original em sua língua nativa deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se a tradução profissional feita por humanos. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações incorretas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->