<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c25ec1f10ef156c53e190cdf8b0711ab",
  "translation_date": "2025-12-13T17:49:48+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "br"
}
-->
# Módulo 05: Protocolo de Contexto de Modelo (MCP)

## Índice

- [O que você vai aprender](../../../05-mcp)
- [Entendendo o MCP](../../../05-mcp)
- [Como o MCP funciona](../../../05-mcp)
  - [Arquitetura Cliente-Servidor](../../../05-mcp)
  - [Descoberta de Ferramentas](../../../05-mcp)
  - [Mecanismos de Transporte](../../../05-mcp)
- [Pré-requisitos](../../../05-mcp)
- [O que este módulo cobre](../../../05-mcp)
- [Início Rápido](../../../05-mcp)
  - [Exemplo 1: Calculadora Remota (HTTP Streamable)](../../../05-mcp)
  - [Exemplo 2: Operações de Arquivo (Stdio)](../../../05-mcp)
  - [Exemplo 3: Análise Git (Docker)](../../../05-mcp)
- [Conceitos-chave](../../../05-mcp)
  - [Seleção de Transporte](../../../05-mcp)
  - [Descoberta de Ferramentas](../../../05-mcp)
  - [Gerenciamento de Sessão](../../../05-mcp)
  - [Considerações Multiplataforma](../../../05-mcp)
- [Quando usar o MCP](../../../05-mcp)
- [Ecossistema MCP](../../../05-mcp)
- [Parabéns!](../../../05-mcp)
  - [E o que vem a seguir?](../../../05-mcp)
- [Solução de Problemas](../../../05-mcp)

## O que você vai aprender

Você já construiu IA conversacional, dominou prompts, fundamentou respostas em documentos e criou agentes com ferramentas. Mas todas essas ferramentas foram feitas sob medida para sua aplicação específica. E se você pudesse dar à sua IA acesso a um ecossistema padronizado de ferramentas que qualquer pessoa pode criar e compartilhar?

O Protocolo de Contexto de Modelo (MCP) oferece exatamente isso - uma forma padrão para aplicações de IA descobrirem e usarem ferramentas externas. Em vez de escrever integrações personalizadas para cada fonte de dados ou serviço, você se conecta a servidores MCP que expõem suas capacidades em um formato consistente. Seu agente de IA pode então descobrir e usar essas ferramentas automaticamente.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5448d2fa21a61218777ceb8010ea0390dd43924b26df35f61.br.png" alt="Comparação MCP" width="800"/>

*Antes do MCP: Integrações ponto a ponto complexas. Depois do MCP: Um protocolo, possibilidades infinitas.*

## Entendendo o MCP

O MCP resolve um problema fundamental no desenvolvimento de IA: toda integração é personalizada. Quer acessar o GitHub? Código personalizado. Quer ler arquivos? Código personalizado. Quer consultar um banco de dados? Código personalizado. E nenhuma dessas integrações funciona com outras aplicações de IA.

O MCP padroniza isso. Um servidor MCP expõe ferramentas com descrições claras e esquemas. Qualquer cliente MCP pode se conectar, descobrir as ferramentas disponíveis e usá-las. Construa uma vez, use em qualquer lugar.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9814b7cffade208d4b0d97203c22df8d8e5504d8238fa7065.br.png" alt="Arquitetura MCP" width="800"/>

*Arquitetura do Protocolo de Contexto de Modelo - descoberta e execução padronizadas de ferramentas*

## Como o MCP funciona

**Arquitetura Cliente-Servidor**

O MCP usa um modelo cliente-servidor. Servidores fornecem ferramentas - leitura de arquivos, consultas a bancos de dados, chamadas de APIs. Clientes (sua aplicação de IA) se conectam aos servidores e usam suas ferramentas.

**Descoberta de Ferramentas**

Quando seu cliente se conecta a um servidor MCP, ele pergunta "Quais ferramentas você tem?" O servidor responde com uma lista de ferramentas disponíveis, cada uma com descrições e esquemas de parâmetros. Seu agente de IA pode então decidir quais ferramentas usar com base nas solicitações do usuário.

**Mecanismos de Transporte**

O MCP define dois mecanismos de transporte: HTTP para servidores remotos, Stdio para processos locais (incluindo containers Docker):

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020ed801b772b26ed69338e22739677aa017e0968f6538b09a2.br.png" alt="Mecanismos de Transporte" width="800"/>

*Mecanismos de transporte MCP: HTTP para servidores remotos, Stdio para processos locais (incluindo containers Docker)*

**Streamable HTTP** - [StreamableHttpDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java)

Para servidores remotos. Sua aplicação faz requisições HTTP a um servidor rodando em algum lugar na rede. Usa Server-Sent Events para comunicação em tempo real.

```java
McpTransport httpTransport = new StreamableHttpMcpTransport.Builder()
    .url("http://localhost:3001/mcp")
    .timeout(Duration.ofSeconds(60))
    .logRequests(true)
    .logResponses(true)
    .build();
```

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`StreamableHttpDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java) e pergunte:
> - "Como o MCP difere da integração direta de ferramentas como no Módulo 04?"
> - "Quais são os benefícios de usar MCP para compartilhamento de ferramentas entre aplicações?"
> - "Como lidar com falhas de conexão ou timeouts em servidores MCP?"

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

Para processos locais. Sua aplicação cria um servidor como subprocesso e se comunica via entrada/saída padrão. Útil para acesso ao sistema de arquivos ou ferramentas de linha de comando.

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@0.6.2",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) e pergunte:
> - "Como funciona o transporte Stdio e quando devo usá-lo em vez de HTTP?"
> - "Como o LangChain4j gerencia o ciclo de vida dos processos de servidores MCP criados?"
> - "Quais são as implicações de segurança ao dar acesso da IA ao sistema de arquivos?"

**Docker (usa Stdio)** - [GitRepositoryAnalyzer.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java)

Para serviços conteinerizados. Usa transporte stdio para comunicar com um container Docker via `docker run`. Bom para dependências complexas ou ambientes isolados.

```java
McpTransport dockerTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        "docker", "run",
        "-e", "GITHUB_PERSONAL_ACCESS_TOKEN=" + System.getenv("GITHUB_TOKEN"),
        "-v", volumeMapping,
        "-i", "mcp/git"
    ))
    .logEvents(true)
    .build();
```

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`GitRepositoryAnalyzer.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java) e pergunte:
> - "Como o transporte Docker isola servidores MCP e quais são os benefícios?"
> - "Como configurar montagens de volume para compartilhar dados entre host e containers MCP?"
> - "Quais são as melhores práticas para gerenciar ciclos de vida de servidores MCP baseados em Docker em produção?"

## Executando os Exemplos

### Pré-requisitos

- Java 21+, Maven 3.9+
- Node.js 16+ e npm (para servidores MCP)
- **Docker Desktop** - Deve estar **EXECUTANDO** para o Exemplo 3 (não apenas instalado)
- Token de Acesso Pessoal do GitHub configurado no arquivo `.env` (do Módulo 00)

> **Nota:** Se você ainda não configurou seu token do GitHub, veja [Módulo 00 - Início Rápido](../00-quick-start/README.md) para instruções.

> **⚠️ Usuários Docker:** Antes de rodar o Exemplo 3, verifique se o Docker Desktop está rodando com `docker ps`. Se aparecerem erros de conexão, inicie o Docker Desktop e aguarde ~30 segundos para inicialização.

## Início Rápido

**Usando VS Code:** Basta clicar com o botão direito em qualquer arquivo de demonstração no Explorer e selecionar **"Run Java"**, ou usar as configurações de execução no painel Run and Debug (certifique-se de ter adicionado seu token no arquivo `.env` primeiro).

**Usando Maven:** Alternativamente, você pode rodar pela linha de comando com os exemplos abaixo.

**⚠️ Importante:** Alguns exemplos têm pré-requisitos (como iniciar um servidor MCP ou construir imagens Docker). Verifique os requisitos de cada exemplo antes de executar.

### Exemplo 1: Calculadora Remota (HTTP Streamable)

Demonstra integração de ferramentas baseada em rede.

**⚠️ Pré-requisito:** Você precisa iniciar o servidor MCP primeiro (veja o Terminal 1 abaixo).

**Terminal 1 - Inicie o servidor MCP:**

**Bash:**
```bash
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**PowerShell:**
```powershell
git clone https://github.com/modelcontextprotocol/servers.git
cd servers/src/everything
npm install
node dist/streamableHttp.js
```

**Terminal 2 - Execute o exemplo:**

**Usando VS Code:** Clique com o botão direito em `StreamableHttpDemo.java` e selecione **"Run Java"**.

**Usando Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

Observe o agente descobrir as ferramentas disponíveis, depois use a calculadora para realizar uma adição.

### Exemplo 2: Operações de Arquivo (Stdio)

Demonstra ferramentas baseadas em subprocessos locais.

**✅ Sem pré-requisitos** - o servidor MCP é criado automaticamente.

**Usando VS Code:** Clique com o botão direito em `StdioTransportDemo.java` e selecione **"Run Java"**.

**Usando Maven:**

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

A aplicação cria automaticamente um servidor MCP de sistema de arquivos e lê um arquivo local. Note como o gerenciamento do subprocesso é feito para você.

**Saída esperada:**
```
Assistant response: The content of the file is "Kaboom!".
```

### Exemplo 3: Análise Git (Docker)

Demonstra servidores de ferramentas conteinerizados.

**⚠️ Pré-requisitos:** 
1. **Docker Desktop deve estar EXECUTANDO** (não apenas instalado)
2. **Usuários Windows:** modo WSL 2 recomendado (Configurações Docker Desktop → Geral → "Use the WSL 2 based engine"). Modo Hyper-V requer configuração manual de compartilhamento de arquivos.
3. Você precisa construir a imagem Docker primeiro (veja o Terminal 1 abaixo)

**Verifique se o Docker está rodando:**

**Bash:**
```bash
docker ps  # Deve mostrar a lista de contêineres, não um erro
```

**PowerShell:**
```powershell
docker ps  # Deve mostrar a lista de contêineres, não um erro
```

Se aparecer um erro como "Cannot connect to Docker daemon" ou "The system cannot find the file specified", inicie o Docker Desktop e aguarde a inicialização (~30 segundos).

**Solução de problemas:**
- Se a IA reportar repositório vazio ou sem arquivos, a montagem do volume (`-v`) não está funcionando.
- **Usuários Windows Hyper-V:** Adicione o diretório do projeto em Configurações Docker Desktop → Recursos → Compartilhamento de arquivos, depois reinicie o Docker Desktop.
- **Solução recomendada:** Mude para o modo WSL 2 para compartilhamento automático de arquivos (Configurações → Geral → habilitar "Use the WSL 2 based engine").

**Terminal 1 - Construa a imagem Docker:**

**Bash:**
```bash
cd servers/src/git
docker build -t mcp/git .
```

**PowerShell:**
```powershell
cd servers/src/git
docker build -t mcp/git .
```

**Terminal 2 - Execute o analisador:**

**Usando VS Code:** Clique com o botão direito em `GitRepositoryAnalyzer.java` e selecione **"Run Java"**.

**Usando Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.GitRepositoryAnalyzer
```

A aplicação inicia um container Docker, monta seu repositório e analisa a estrutura e conteúdo do repositório através do agente de IA.

## Conceitos-chave

**Seleção de Transporte**

Escolha com base em onde suas ferramentas estão:
- Serviços remotos → HTTP Streamable
- Sistema de arquivos local → Stdio
- Dependências complexas → Docker

**Descoberta de Ferramentas**

Clientes MCP descobrem automaticamente as ferramentas disponíveis ao se conectar. Seu agente de IA vê as descrições das ferramentas e decide quais usar com base no pedido do usuário.

**Gerenciamento de Sessão**

O transporte HTTP Streamable mantém sessões, permitindo interações com estado com servidores remotos. Transportes Stdio e Docker são tipicamente sem estado.

**Considerações Multiplataforma**

Os exemplos lidam automaticamente com diferenças de plataforma (diferenças de comandos Windows vs Unix, conversões de caminho para Docker). Isso é importante para implantações em produção em diferentes ambientes.

## Quando usar o MCP

**Use MCP quando:**
- Quiser aproveitar ecossistemas de ferramentas existentes
- Construir ferramentas que múltiplas aplicações usarão
- Integrar serviços de terceiros com protocolos padrão
- Precisar trocar implementações de ferramentas sem mudar código

**Use ferramentas personalizadas (Módulo 04) quando:**
- Construir funcionalidades específicas da aplicação
- Performance for crítica (MCP adiciona overhead)
- Suas ferramentas forem simples e não serão reutilizadas
- Precisar de controle total sobre a execução


## Ecossistema MCP

O Protocolo de Contexto de Modelo é um padrão aberto com um ecossistema crescente:

- Servidores MCP oficiais para tarefas comuns (sistema de arquivos, Git, bancos de dados)
- Servidores contribuídos pela comunidade para vários serviços
- Descrições e esquemas padronizados de ferramentas
- Compatibilidade entre frameworks (funciona com qualquer cliente MCP)

Essa padronização significa que ferramentas feitas para uma aplicação de IA funcionam com outras, criando um ecossistema compartilhado de capacidades.

## Parabéns!

Você completou o curso LangChain4j para Iniciantes. Você aprendeu:

- Como construir IA conversacional com memória (Módulo 01)
- Padrões de engenharia de prompt para diferentes tarefas (Módulo 02)
- Fundamentar respostas em seus documentos com RAG (Módulo 03)
- Criar agentes de IA com ferramentas personalizadas (Módulo 04)
- Integrar ferramentas padronizadas via MCP (Módulo 05)

Agora você tem a base para construir aplicações de IA em produção. Os conceitos que aprendeu se aplicam independentemente de frameworks ou modelos específicos - são padrões fundamentais em engenharia de IA.

### E o que vem a seguir?

Após completar os módulos, explore o [Guia de Testes](../docs/TESTING.md) para ver conceitos de testes LangChain4j em ação.

**Recursos Oficiais:**
- [Documentação LangChain4j](https://docs.langchain4j.dev/) - Guias completos e referência de API
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Código-fonte e exemplos
- [Tutoriais LangChain4j](https://docs.langchain4j.dev/tutorials/) - Tutoriais passo a passo para vários casos de uso

Obrigado por completar este curso!

---

**Navegação:** [← Anterior: Módulo 04 - Ferramentas](../04-tools/README.md) | [Voltar ao Início](../README.md)

---

## Solução de Problemas

### Sintaxe do comando Maven no PowerShell
**Problema**: Comandos Maven falham com o erro `Unknown lifecycle phase ".mainClass=..."`

**Causa**: PowerShell interpreta `=` como operador de atribuição de variável, quebrando a sintaxe da propriedade Maven

**Solução**: Use o operador de parada de análise `--%` antes do comando Maven:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

O operador `--%` instrui o PowerShell a passar todos os argumentos restantes literalmente para o Maven sem interpretação.

### Problemas de Conexão com Docker

**Problema**: Comandos Docker falham com "Cannot connect to Docker daemon" ou "The system cannot find the file specified"

**Causa**: Docker Desktop não está em execução ou não está totalmente inicializado

**Solução**: 
1. Inicie o Docker Desktop
2. Aguarde ~30 segundos para a inicialização completa
3. Verifique com `docker ps` (deve mostrar a lista de containers, não um erro)
4. Então execute seu exemplo

### Montagem de Volume Docker no Windows

**Problema**: Analisador de repositório Git reporta repositório vazio ou sem arquivos

**Causa**: Montagem de volume (`-v`) não funciona devido à configuração de compartilhamento de arquivos

**Solução**:
- **Recomendado:** Mude para o modo WSL 2 (Configurações do Docker Desktop → Geral → "Use the WSL 2 based engine")
- **Alternativa (Hyper-V):** Adicione o diretório do projeto em Configurações do Docker Desktop → Recursos → Compartilhamento de arquivos, depois reinicie o Docker Desktop

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:  
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, esteja ciente de que traduções automáticas podem conter erros ou imprecisões. O documento original em seu idioma nativo deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se tradução profissional humana. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações incorretas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->