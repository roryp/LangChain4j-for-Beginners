# Module 00: Quick Start

## Table of Contents

- [What is LangChain4j?](#what-is-langchain4j)
- [LangChain4j Dependencies](#langchain4j-dependencies)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
  - [1. Get Your GitHub Token](#1-get-your-github-token)
  - [2. Set Your Token](#2-set-your-token)
- [Run the Examples](#run-the-examples)
  - [1. Basic Chat](#1-basic-chat)
  - [2. Prompt Patterns](#2-prompt-patterns)
  - [3. Function Calling](#3-function-calling)
  - [4. Document Q&A (RAG)](#4-document-qa-rag)
- [What Each Example Shows](#what-each-example-shows)
- [Next Steps](#next-steps)

## What is LangChain4j?

LangChain4j is a Java library that simplifies building AI-powered applications. Instead of dealing with HTTP clients and JSON parsing, you work with clean Java APIs. 

The "chain" in LangChain refers to chaining together multiple components - you might chain a prompt to a model to a parser, or chain multiple AI calls together where one output feeds into the next input. This quick start focuses on the fundamentals before exploring more complex chains.

<img src="images/langchain-concept.png" alt="LangChain4j Chaining Concept" width="800"/>

*Chaining components in LangChain4j - building blocks connect to create powerful AI workflows*

We'll use three core components:

**ChatLanguageModel** - The interface for AI model interactions. Call `model.chat("prompt")` and get a response string. We use `OpenAiChatModel` which works with OpenAI-compatible endpoints like GitHub Models.

**AiServices** - Creates type-safe AI service interfaces. Define methods, annotate them with `@Tool`, and LangChain4j handles the orchestration. The AI automatically calls your Java methods when needed.

**MessageWindowChatMemory** - Maintains conversation history. Without this, each request is independent. With it, the AI remembers previous messages and maintains context across multiple turns.

<img src="images/architecture.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j architecture - core components working together to power your AI applications*

## LangChain4j Dependencies

This quick start uses two Maven dependencies in the [`pom.xml`](pom.xml#L24-L36):

```xml
<!-- Core LangChain4j library -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId>
    <version>${langchain4j.version}</version> <!-- Version defined in root pom.xml -->
</dependency>

<!-- OpenAI integration (works with GitHub Models) -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai</artifactId>
    <version>${langchain4j.version}</version> <!-- Version defined in root pom.xml -->
</dependency>
```

The `langchain4j-open-ai` module provides the `OpenAiChatModel` class that connects to OpenAI-compatible APIs. GitHub Models uses the same API format, so no special adapter is needed - just point the base URL to `https://models.github.ai/inference`.

## Prerequisites

**Using the Dev Container?** Java and Maven are already installed. You only need a GitHub Personal Access Token.

**Local Development:**
- Java 21+, Maven 3.9+
- GitHub Personal Access Token (instructions below)

## Setup

### 1. Get Your GitHub Token

1. Go to [GitHub Settings → Personal Access Tokens](https://github.com/settings/personal-access-tokens)
2. Click "Generate new token"
3. Set a descriptive name (e.g., "LangChain4j Demo")
4. Set expiration (7 days recommended)
5. Under "Account permissions", find "Models" and set to "Read-only"
6. Click "Generate token"
7. Copy and save your token - you won't see it again

### 2. Set Your Token

```bash
export GITHUB_TOKEN="your_token_here"  # macOS/Linux
$env:GITHUB_TOKEN="your_token_here"    # Windows PowerShell
```

## Run the Examples

### 1. Basic Chat

```bash
mvn exec:java -Dexec.mainClass="com.example.langchain4j.quickstart.BasicChatDemo"
```

### 2. Prompt Patterns

```bash
mvn exec:java -Dexec.mainClass="com.example.langchain4j.quickstart.PromptEngineeringDemo"
```

Shows zero-shot, few-shot, chain-of-thought, and role-based prompting.

### 3. Function Calling

```bash
mvn exec:java -Dexec.mainClass="com.example.langchain4j.quickstart.ToolIntegrationDemo"
```

AI automatically calls your Java methods when needed.

### 4. Document Q&A (RAG)

```bash
mvn exec:java -Dexec.mainClass="com.example.langchain4j.quickstart.SimpleReaderDemo"
```

Ask questions about content in `document.txt`.

## What Each Example Shows

**[BasicChatDemo.java](src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java#L19-L47)** - Start here to see LangChain4j at its simplest. You'll create an `OpenAiChatModel`, send a prompt with `.chat()`, and get back a response. This demonstrates the foundation: how to initialize models with custom endpoints and API keys. Once you understand this pattern, everything else builds on it.

**[PromptEngineeringDemo.java](src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java#L19-L50)** - Now that you know how to talk to a model, let's explore what you say to it. This demo uses the same model setup but shows four different prompting patterns. Try zero-shot prompts for direct instructions, few-shot prompts that learn from examples, chain-of-thought prompts that reveal reasoning steps, and role-based prompts that set context. You'll see how the same model gives dramatically different results based on how you frame your request.

**[ToolIntegrationDemo.java](src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java#L21-L68)** - This is where LangChain4j gets powerful. You'll use `AiServices` to create an AI assistant that can call your Java methods. Just annotate methods with `@Tool("description")` and LangChain4j handles the rest - the AI automatically decides when to use each tool based on what the user asks. This demonstrates function calling, a key technique for building AI that can take actions, not just answer questions.

**[SimpleReaderDemo.java](src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java#L23-L81)** - Here you'll see the foundation of RAG (retrieval-augmented generation). Instead of relying on the model's training data, you load content from [`document.txt`](document.txt) and include it in the prompt. The AI answers based on your document, not its general knowledge. This is the first step toward building systems that can work with your own data.

## Next Steps

Ready for more? The [Introduction module](../01-introduction/README.md) covers conversation memory, architecture patterns, and production considerations using Azure OpenAI's GPT-5.

---

**Navigation:** [← Back to Main](../README.md) | [Next: Module 01 - Introduction →](../01-introduction/README.md)