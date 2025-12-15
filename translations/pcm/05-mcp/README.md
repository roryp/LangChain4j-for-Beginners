<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c25ec1f10ef156c53e190cdf8b0711ab",
  "translation_date": "2025-12-13T18:13:40+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "pcm"
}
-->
# Module 05: Model Context Protocol (MCP)

## Table of Contents

- [Wetin You Go Learn](../../../05-mcp)
- [Understanding MCP](../../../05-mcp)
- [How MCP Dey Work](../../../05-mcp)
  - [Server-Client Architecture](../../../05-mcp)
  - [Tool Discovery](../../../05-mcp)
  - [Transport Mechanisms](../../../05-mcp)
- [Prerequisites](../../../05-mcp)
- [Wetin Dis Module Dey Cover](../../../05-mcp)
- [Quick Start](../../../05-mcp)
  - [Example 1: Remote Calculator (Streamable HTTP)](../../../05-mcp)
  - [Example 2: File Operations (Stdio)](../../../05-mcp)
  - [Example 3: Git Analysis (Docker)](../../../05-mcp)
- [Key Concepts](../../../05-mcp)
  - [Transport Selection](../../../05-mcp)
  - [Tool Discovery](../../../05-mcp)
  - [Session Management](../../../05-mcp)
  - [Cross-Platform Considerations](../../../05-mcp)
- [When to Use MCP](../../../05-mcp)
- [MCP Ecosystem](../../../05-mcp)
- [Congratulations!](../../../05-mcp)
  - [Wetin Next?](../../../05-mcp)
- [Troubleshooting](../../../05-mcp)

## What You'll Learn

You don build conversational AI, sabi prompts well, fit ground responses for documents, and create agents wey get tools. But all those tools na custom-build for your own application. Wetin if you fit give your AI access to one standardized ecosystem of tools wey anybody fit create and share?

The Model Context Protocol (MCP) na exactly dat - one standard way for AI applications to find and use external tools. Instead make you dey write custom integrations for every data source or service, you go connect to MCP servers wey dey show their capabilities for one consistent format. Your AI agent fit then find and use these tools automatically.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5448d2fa21a61218777ceb8010ea0390dd43924b26df35f61.pcm.png" alt="MCP Comparison" width="800"/>

*Before MCP: Complex point-to-point integrations. After MCP: One protocol, endless possibilities.*

## Understanding MCP

MCP dey solve one big problem for AI development: every integration na custom. You want access GitHub? Custom code. You want read files? Custom code. You want query database? Custom code. And none of these integrations dey work with other AI applications.

MCP dey standardize dis one. MCP server dey expose tools with clear descriptions and schemas. Any MCP client fit connect, find available tools, and use dem. Build once, use everywhere.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9814b7cffade208d4b0d97203c22df8d8e5504d8238fa7065.pcm.png" alt="MCP Architecture" width="800"/>

*Model Context Protocol architecture - standardized tool discovery and execution*

## How MCP Works

**Server-Client Architecture**

MCP dey use client-server model. Servers dey provide tools - reading files, querying databases, calling APIs. Clients (your AI application) dey connect to servers and use their tools.

**Tool Discovery**

When your client connect to MCP server, e go ask "Wetin tools you get?" The server go respond with list of available tools, each get descriptions and parameter schemas. Your AI agent fit then decide which tools to use based on user requests.

**Transport Mechanisms**

MCP define two transport mechanisms: HTTP for remote servers, Stdio for local processes (including Docker containers):

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020ed801b772b26ed69338e22739677aa017e0968f6538b09a2.pcm.png" alt="Transport Mechanisms" width="800"/>

*MCP transport mechanisms: HTTP for remote servers, Stdio for local processes (including Docker containers)*

**Streamable HTTP** - [StreamableHttpDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java)

For remote servers. Your application dey make HTTP requests to server wey dey run somewhere for network. E dey use Server-Sent Events for real-time communication.

```java
McpTransport httpTransport = new StreamableHttpMcpTransport.Builder()
    .url("http://localhost:3001/mcp")
    .timeout(Duration.ofSeconds(60))
    .logRequests(true)
    .logResponses(true)
    .build();
```

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`StreamableHttpDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StreamableHttpDemo.java) and ask:
> - "How MCP different from direct tool integration like for Module 04?"
> - "Wetin be the benefits of using MCP for tool sharing across applications?"
> - "How I go handle connection failures or timeouts to MCP servers?"

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

For local processes. Your application go spawn server as subprocess and communicate through standard input/output. E good for filesystem access or command-line tools.

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

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) and ask:
> - "How Stdio transport dey work and when I suppose use am vs HTTP?"
> - "How LangChain4j dey manage lifecycle of spawned MCP server processes?"
> - "Wetin be the security implications of giving AI access to file system?"

**Docker (uses Stdio)** - [GitRepositoryAnalyzer.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java)

For containerized services. E dey use stdio transport to communicate with Docker container via `docker run`. E good for complex dependencies or isolated environments.

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

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`GitRepositoryAnalyzer.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/GitRepositoryAnalyzer.java) and ask:
> - "How Docker transport dey isolate MCP servers and wetin be the benefits?"
> - "How I go configure volume mounts to share data between host and MCP containers?"
> - "Wetin be best practices for managing Docker-based MCP server lifecycles for production?"

## Running the Examples

### Prerequisites

- Java 21+, Maven 3.9+
- Node.js 16+ and npm (for MCP servers)
- **Docker Desktop** - Must dey **RUNNING** for Example 3 (no be only installed)
- GitHub Personal Access Token wey you don configure for `.env` file (from Module 00)

> **Note:** If you never set up your GitHub token yet, see [Module 00 - Quick Start](../00-quick-start/README.md) for instructions.

> **⚠️ Docker Users:** Before you run Example 3, make sure Docker Desktop dey run with `docker ps`. If you see connection errors, start Docker Desktop and wait about 30 seconds for e go initialize.

## Quick Start

**Using VS Code:** Just right-click any demo file for Explorer and select **"Run Java"**, or use the launch configurations from Run and Debug panel (make sure say you don add your token to `.env` file first).

**Using Maven:** Alternatively, you fit run from command line with the examples below.

**⚠️ Important:** Some examples get prerequisites (like to start MCP server or build Docker images). Check each example requirements before you run am.

### Example 1: Remote Calculator (Streamable HTTP)

Dis one dey show network-based tool integration.

**⚠️ Prerequisite:** You need start MCP server first (see Terminal 1 below).

**Terminal 1 - Start MCP server:**

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

**Terminal 2 - Run the example:**

**Using VS Code:** Right-click `StreamableHttpDemo.java` and select **"Run Java"**.

**Using Maven:**

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

Watch as agent go find available tools, then use calculator to do addition.

### Example 2: File Operations (Stdio)

Dis one dey show local subprocess-based tools.

**✅ No prerequisites needed** - MCP server go spawn automatically.

**Using VS Code:** Right-click `StdioTransportDemo.java` and select **"Run Java"**.

**Using Maven:**

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

The application go spawn filesystem MCP server automatically and read local file. Notice how subprocess management dey handled for you.

**Expected output:**
```
Assistant response: The content of the file is "Kaboom!".
```

### Example 3: Git Analysis (Docker)

Dis one dey show containerized tool servers.

**⚠️ Prerequisites:** 
1. **Docker Desktop must dey RUNNING** (no be only installed)
2. **Windows users:** WSL 2 mode dey recommended (Docker Desktop Settings → General → "Use the WSL 2 based engine"). Hyper-V mode need manual file sharing configuration.
3. You need build Docker image first (see Terminal 1 below)

**Verify Docker dey run:**

**Bash:**
```bash
docker ps  # E suppose show container list, no be error
```

**PowerShell:**
```powershell
docker ps  # E suppose show container list, no be error
```

If you see error like "Cannot connect to Docker daemon" or "The system cannot find the file specified", start Docker Desktop and wait make e initialize (~30 seconds).

**Troubleshooting:**
- If AI talk say repository empty or no files, volume mount (`-v`) no dey work.
- **Windows Hyper-V users:** Add project directory to Docker Desktop Settings → Resources → File sharing, then restart Docker Desktop.
- **Recommended solution:** Switch to WSL 2 mode for automatic file sharing (Settings → General → enable "Use the WSL 2 based engine").

**Terminal 1 - Build Docker image:**

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

**Terminal 2 - Run analyzer:**

**Using VS Code:** Right-click `GitRepositoryAnalyzer.java` and select **"Run Java"**.

**Using Maven:**

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

The application go launch Docker container, mount your repository, and analyze repository structure and contents through AI agent.

## Key Concepts

**Transport Selection**

Choose based on where your tools dey:
- Remote services → Streamable HTTP
- Local file system → Stdio
- Complex dependencies → Docker

**Tool Discovery**

MCP clients dey automatically find available tools when dem connect. Your AI agent go see tool descriptions and decide which ones to use based on user request.

**Session Management**

Streamable HTTP transport dey maintain sessions, allow stateful interactions with remote servers. Stdio and Docker transports usually no get state.

**Cross-Platform Considerations**

The examples dey handle platform differences automatically (Windows vs Unix command differences, path conversions for Docker). Dis one important for production deployments across different environments.

## When to Use MCP

**Use MCP when:**
- You want leverage existing tool ecosystems
- You dey build tools wey multiple applications go use
- You dey integrate third-party services with standard protocols
- You need swap tool implementations without code changes

**Use custom tools (Module 04) when:**
- You dey build application-specific functionality
- Performance dey critical (MCP fit add overhead)
- Your tools simple and no go dey reused
- You need full control over execution


## MCP Ecosystem

The Model Context Protocol na open standard wey get growing ecosystem:

- Official MCP servers for common tasks (filesystem, Git, databases)
- Community-contributed servers for different services
- Standardized tool descriptions and schemas
- Cross-framework compatibility (e dey work with any MCP client)

Dis standardization mean tools wey build for one AI application fit work with others, create shared ecosystem of capabilities.

## Congratulations!

You don finish LangChain4j for Beginners course. You don learn:

- How to build conversational AI with memory (Module 01)
- Prompt engineering patterns for different tasks (Module 02)
- Grounding responses for your documents with RAG (Module 03)
- Creating AI agents with custom tools (Module 04)
- Integrating standardized tools through MCP (Module 05)

You get foundation now to build production AI applications. The concepts wey you learn no depend on specific frameworks or models - na fundamental patterns for AI engineering.

### What's Next?

After you finish the modules, explore the [Testing Guide](../docs/TESTING.md) to see LangChain4j testing concepts for action.

**Official Resources:**
- [LangChain4j Documentation](https://docs.langchain4j.dev/) - Complete guides and API reference
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Source code and examples
- [LangChain4j Tutorials](https://docs.langchain4j.dev/tutorials/) - Step-by-step tutorials for different use cases

Thank you for completing this course!

---

**Navigation:** [← Previous: Module 04 - Tools](../04-tools/README.md) | [Back to Main](../README.md)

---

## Troubleshooting

### PowerShell Maven Command Syntax
**Issue**: Maven commands dey fail wit error `Unknown lifecycle phase ".mainClass=..."`

**Cause**: PowerShell dey interpret `=` as variable assignment operator, e dey break Maven property syntax

**Solution**: Use di stop-parsing operator `--%` before di Maven command:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StreamableHttpDemo
```

Di `--%` operator dey tell PowerShell to pass all di remaining arguments literally to Maven without interpretation.

### Docker Connection Issues

**Issue**: Docker commands dey fail wit "Cannot connect to Docker daemon" or "The system cannot find the file specified"

**Cause**: Docker Desktop no dey run or e never fully initialize

**Solution**: 
1. Start Docker Desktop
2. Wait ~30 seconds make e fully initialize
3. Verify wit `docker ps` (e suppose show container list, no be error)
4. Then run your example

### Windows Docker Volume Mounting

**Issue**: Git repository analyzer dey report empty repository or no files

**Cause**: Volume mount (`-v`) no dey work because file sharing configuration no correct

**Solution**:
- **Recommended:** Switch to WSL 2 mode (Docker Desktop Settings → General → "Use the WSL 2 based engine")
- **Alternative (Hyper-V):** Add project directory to Docker Desktop Settings → Resources → File sharing, then restart Docker Desktop

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:
Dis document don translate wit AI translation service [Co-op Translator](https://github.com/Azure/co-op-translator). Even though we dey try make am correct, abeg sabi say automated translation fit get some errors or wahala. Di original document wey e dey for im own language na di correct one. If na serious matter, e better make human professional translate am. We no go responsible for any misunderstanding or wrong meaning wey fit show because of dis translation.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->