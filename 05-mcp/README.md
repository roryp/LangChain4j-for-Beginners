# Module 05: Model Context Protocol (MCP)

## Table of Contents

- [What You'll Learn](#what-youll-learn)
- [Understanding MCP](#understanding-mcp)
- [How MCP Works](#how-mcp-works)
  - [Server-Client Architecture](#server-client-architecture)
  - [Tool Discovery](#tool-discovery)
  - [Transport Mechanisms](#transport-mechanisms)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
  - [File Operations (Stdio)](#file-operations-stdio)
- [Key Concepts](#key-concepts)
  - [Transport Selection](#transport-selection)
  - [Tool Discovery](#tool-discovery-1)
  - [Session Management](#session-management)
  - [Cross-Platform Considerations](#cross-platform-considerations)
- [When to Use MCP](#when-to-use-mcp)
- [MCP Ecosystem](#mcp-ecosystem)
- [Congratulations!](#congratulations)
  - [What's Next?](#whats-next)
- [Troubleshooting](#troubleshooting)

## What You'll Learn

You've built conversational AI, mastered prompts, grounded responses in documents, and created agents with tools. But all those tools were custom-built for your specific application. What if you could give your AI access to a standardized ecosystem of tools that anyone can create and share?

The Model Context Protocol (MCP) provides exactly that - a standard way for AI applications to discover and use external tools. Instead of writing custom integrations for each data source or service, you connect to MCP servers that expose their capabilities in a consistent format. Your AI agent can then discover and use these tools automatically.

<img src="images/mcp-comparison.png" alt="MCP Comparison" width="800"/>

*Before MCP: Complex point-to-point integrations. After MCP: One protocol, endless possibilities.*

## Understanding MCP

MCP solves a fundamental problem in AI development: every integration is custom. Want to access GitHub? Custom code. Want to read files? Custom code. Want to query a database? Custom code. And none of these integrations work with other AI applications.

MCP standardizes this. An MCP server exposes tools with clear descriptions and schemas. Any MCP client can connect, discover available tools, and use them. Build once, use everywhere.

<img src="images/mcp-architecture.png" alt="MCP Architecture" width="800"/>

*Model Context Protocol architecture - standardized tool discovery and execution*

## How MCP Works

**Server-Client Architecture**

MCP uses a client-server model. Servers provide tools - reading files, querying databases, calling APIs. Clients (your AI application) connect to servers and use their tools.

**Tool Discovery**

When your client connects to an MCP server, it asks "What tools do you have?" The server responds with a list of available tools, each with descriptions and parameter schemas. Your AI agent can then decide which tools to use based on user requests.

**Transport Mechanisms**

MCP supports different transport mechanisms. This module demonstrates the Stdio transport for local processes:

<img src="images/transport-mechanisms.png" alt="Transport Mechanisms" width="800"/>

*MCP transport mechanisms: HTTP for remote servers, Stdio for local processes*

**Stdio** - [StdioTransportDemo.java](src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

For local processes. Your application spawns a server as a subprocess and communicates through standard input/output. Useful for filesystem access or command-line tools.

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

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`StdioTransportDemo.java`](src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) and ask:
> - "How does Stdio transport work and when should I use it vs HTTP?"
> - "How does LangChain4j manage the lifecycle of spawned MCP server processes?"
> - "What are the security implications of giving AI access to the file system?"

## Running the Examples

### Prerequisites

- Java 21+, Maven 3.9+
- Node.js 16+ and npm (for MCP servers)
- GitHub Personal Access Token configured in `.env` file (from Module 00)

> **Note:** If you haven't set up your GitHub token yet, see [Module 00 - Quick Start](../00-quick-start/README.md) for instructions.

## Quick Start

**Using VS Code:** Simply right-click on any demo file in the Explorer and select **"Run Java"**, or use the launch configurations from the Run and Debug panel (make sure you've added your token to the `.env` file first).

**Using Maven:** Alternatively, you can run from the command line with the examples below.

### File Operations (Stdio)

This demonstrates local subprocess-based tools.

**✅ No prerequisites needed** - the MCP server is spawned automatically.

**Using VS Code:** Right-click on `StdioTransportDemo.java` and select **"Run Java"**.

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

The application spawns a filesystem MCP server automatically and reads a local file. Notice how the subprocess management is handled for you.

**Expected output:**
```
Assistant response: The content of the file is "Kaboom!".
```

## Key Concepts

**Transport Selection**

Choose based on where your tools live:
- Remote services → Streamable HTTP
- Local file system → Stdio

**Tool Discovery**

MCP clients automatically discover available tools when connecting. Your AI agent sees tool descriptions and decides which ones to use based on the user's request.

**Session Management**

Streamable HTTP transport maintains sessions, allowing stateful interactions with remote servers. Stdio transport is typically stateless.

## When to Use MCP

**Use MCP when:**
- You want to leverage existing tool ecosystems
- Building tools that multiple applications will use
- Integrating third-party services with standard protocols
- You need to swap tool implementations without code changes

**Use custom tools (Module 04) when:**
- Building application-specific functionality
- Performance is critical (MCP adds overhead)
- Your tools are simple and won't be reused
- You need complete control over execution


## MCP Ecosystem

The Model Context Protocol is an open standard with a growing ecosystem:

- Official MCP servers for common tasks (filesystem, Git, databases)
- Community-contributed servers for various services
- Standardized tool descriptions and schemas
- Cross-framework compatibility (works with any MCP client)

This standardization means tools built for one AI application work with others, creating a shared ecosystem of capabilities.

## Congratulations!

You've completed the LangChain4j for Beginners course. You've learned:

- How to build conversational AI with memory (Module 01)
- Prompt engineering patterns for different tasks (Module 02)
- Grounding responses in your documents with RAG (Module 03)
- Creating AI agents with custom tools (Module 04)
- Integrating standardized tools through MCP (Module 05)

You now have the foundation to build production AI applications. The concepts you've learned apply regardless of specific frameworks or models - they're fundamental patterns in AI engineering.

### What's Next?

After completing the modules, explore the [Testing Guide](../docs/TESTING.md) to see LangChain4j testing concepts in action.

**Official Resources:**
- [LangChain4j Documentation](https://docs.langchain4j.dev/) - Comprehensive guides and API reference
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - Source code and examples
- [LangChain4j Tutorials](https://docs.langchain4j.dev/tutorials/) - Step-by-step tutorials for various use cases

Thank you for completing this course!

---

**Navigation:** [← Previous: Module 04 - Tools](../04-tools/README.md) | [Back to Main](../README.md)

---

## Troubleshooting

### PowerShell Maven Command Syntax

**Issue**: Maven commands fail with error `Unknown lifecycle phase ".mainClass=..."`

**Cause**: PowerShell interprets `=` as a variable assignment operator, breaking Maven property syntax

**Solution**: Use the stop-parsing operator `--%` before the Maven command:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

The `--%` operator tells PowerShell to pass all remaining arguments literally to Maven without interpretation.
