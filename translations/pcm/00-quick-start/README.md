<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "22b5d7c8d7585325e38b37fd29eafe25",
  "translation_date": "2026-01-06T02:05:18+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "pcm"
}
-->
# Module 00: Quick Start

## Table of Contents

- [Introduction](../../../00-quick-start)
- [Wetín be LangChain4j?](../../../00-quick-start)
- [LangChain4j Dependencies](../../../00-quick-start)
- [Wetin You Go Need](../../../00-quick-start)
- [Setup](../../../00-quick-start)
  - [1. Get Your GitHub Token](../../../00-quick-start)
  - [2. Set Your Token](../../../00-quick-start)
- [Run the Examples](../../../00-quick-start)
  - [1. Basic Chat](../../../00-quick-start)
  - [2. Prompt Patterns](../../../00-quick-start)
  - [3. Function Calling](../../../00-quick-start)
  - [4. Document Q&A (RAG)](../../../00-quick-start)
  - [5. Responsible AI](../../../00-quick-start)
- [Wetin Each Example Dey Show](../../../00-quick-start)
- [Next Steps](../../../00-quick-start)
- [Troubleshooting](../../../00-quick-start)

## Introduction

Dis quickstart wan make you start dey run LangChain4j as quick as e fit. E cover the basic tins for how to build AI applications with LangChain4j and GitHub Models. For next modules, you go use Azure OpenAI with LangChain4j build better applications.

## Wetín be LangChain4j?

LangChain4j na Java library wey dey make am easy to build AI-powered apps. Instead make you dey use HTTP clients and dey parse JSON, you go dey use clean Java APIs.

The "chain" for LangChain mean say you go dey join different components - fit be say you go connect prompt go model go parser, or multiple AI calls wey one output go feed the next input. Dis quick start go first show you the basics before you go do more complex chains.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1.pcm.png" alt="LangChain4j Chaining Concept" width="800"/>

*Chaining components for LangChain4j - blocks dey join to build strong AI workflows*

We go use three main components:

**ChatLanguageModel** - Na the interface wey you dey talk to AI models. You call `model.chat("prompt")` to get answer. We dey use `OpenAiOfficialChatModel` wey fit work with OpenAI-compatible endpoints like GitHub Models.

**AiServices** - E dey create type-safe AI service interfaces. You define methods, mark dem with `@Tool`, LangChain4j go manage the arrangement. The AI go call your Java methods automatically when e need am.

**MessageWindowChatMemory** - E dey keep the story for the conversation. Without am, each request stand alone. With am, AI go remember previous messages and keep context through multiple turns.

<img src="../../../translated_images/architecture.eedc993a1c576839.pcm.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j architecture - core components dey work together to power your AI apps*

## LangChain4j Dependencies

Dis quick start dey use two Maven dependencies for the [`pom.xml`](../../../00-quick-start/pom.xml):

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

The `langchain4j-open-ai-official` module get `OpenAiOfficialChatModel` class wey connect to OpenAI-compatible APIs. GitHub Models dey use the same API format, so you no need special adapter - just set the base URL to `https://models.github.ai/inference`.

## Wetin You Go Need

**You dey use Dev Container?** Java and Maven don pre-install finish. You only need GitHub Personal Access Token.

**Local Development:**
- Java 21+, Maven 3.9+
- GitHub Personal Access Token (instructions below)

> **Note:** Dis module dey use `gpt-4.1-nano` from GitHub Models. No change the model name inside code - e don configure to work with GitHub models wey dey available.

## Setup

### 1. Get Your GitHub Token

1. Go [GitHub Settings → Personal Access Tokens](https://github.com/settings/personal-access-tokens)
2. Click "Generate new token"
3. Give am name wey make sense (example, "LangChain4j Demo")
4. Set how long e go expire (7 days dey recommended)
5. Under "Account permissions", find "Models" and set am to "Read-only"
6. Click "Generate token"
7. Copy am well and keep am safe - you no go see am again

### 2. Set Your Token

**Option 1: Use VS Code (Na the best way)**

If na VS Code you dey use, add your token for `.env` file for the project root:

If `.env` no dey, copy `.env.example` go `.env` or create new `.env` file for project root.

**Example `.env` file:**
```bash
# For /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

After dat, you fit right-click any demo file (like `BasicChatDemo.java`) for Explorer and select **"Run Java"** or start am from the Run and Debug panel configurations.

**Option 2: Using Terminal**

Set the token as environment variable:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## Run the Examples

**Use VS Code:** Just right-click any demo file for Explorer make you select **"Run Java"**, or use the Run and Debug panel (but make sure say your token don dey for `.env` file first).

**Use Maven:** Or you fit run from command line:

### 1. Basic Chat

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. Prompt Patterns

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

E show zero-shot, few-shot, chain-of-thought, and role-based prompting.

### 3. Function Calling

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI dey call your Java methods automatically when e need am.

### 4. Document Q&A (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

You fit ask questions about wetin dey inside `document.txt`.

### 5. Responsible AI

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

See how AI safety filters dey block harmful content.

## Wetin Each Example Dey Show

**Basic Chat** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

Start here to see LangChain4j for the simplest form. You go create `OpenAiOfficialChatModel`, send prompt with `.chat()`, and get answer back. Dis one show how e start: how to start models with your own endpoints and API keys. Once you understand dis pattern, everything else go easier.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) and ask:
> - "How I go switch from GitHub Models to Azure OpenAI for dis code?"
> - "Wetin be the other parameters wey I fit set for OpenAiOfficialChatModel.builder()?"
> - "How I go add streaming responses instead make I wait the full response before I get am?"

**Prompt Engineering** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

Now wey you sabi how to talk to model, make we see wetin you go talk to am. Dis demo dey use the same model setup but show four different prompting styles. Try zero-shot prompts for direct instructions, few-shot prompts wey learn from examples, chain-of-thought prompts wey dey show reasoning steps, and role-based prompts wey set context. You go see how one model fit give plenty different answers based on how you ask.

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

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) and ask:
> - "Wetin be difference between zero-shot and few-shot prompting, and when I supposed use each one?"
> - "How the temperature parameter dey affect the model responses?"
> - "Wetin be some ways to stop prompt injection attacks for production?"
> - "How I fit create reusable PromptTemplate objects for common patterns?"

**Tool Integration** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

Dis na where LangChain4j strong pass. You go use `AiServices` make AI assistant wey fit call your Java methods. You just mark methods with `@Tool("description")` and LangChain4j go take care of the rest - AI go decide automatically when to use which tool based on wetin user ask. Dis one na function calling, key method to build AI wey fit take actions, no just answer questions.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) and ask:
> - "How @Tool annotation dey work and wetin LangChain4j dey do with am behind the scenes?"
> - "Fit the AI call many tools one after another to solve wahala wey complex?"
> - "Wetin happen if tool throw exception - how I suppose handle errors?"
> - "How I fit connect real API instead of dis calculator example?"

**Document Q&A (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

Here you go see the base for RAG (retrieval-augmented generation). No just rely on model training data. You go load content from [`document.txt`](../../../00-quick-start/document.txt) and add am to prompt. AI go answer based on your document, no na general knowledge. Dis na first step to build systems wey fit work with your own data.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **Note:** Dis simple way dey load the whole document inside prompt. For big files (>10KB), e go pass context limits. Module 03 go teach chunking and vector search for production RAG systems.

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) and ask:
> - "How RAG dey stop AI hallucinations unlike the model training data?"
> - "Wetin be difference between dis simple method and using vector embeddings for retrieval?"
> - "How I fit scale dis to handle plenty documents or bigger knowledge bases?"
> - "Wetin be best ways to arrange prompt so AI go use only the provided context?"

**Responsible AI** - [ResponsibleAIDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)

Build AI safety with strong protection inside. Dis demo show two layers of protection wey dey work together:

**Part 1: LangChain4j Input Guardrails** - E block dangerous prompts before dem reach LLM. Create your own guardrails to check for banned keywords or patterns. Dem dey run for your code, so dem fast and no cost money.

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

**Part 2: Provider Safety Filters** - GitHub Models get built-in filters wey catch wetin your guardrails fit miss. You go see hard blocks (HTTP 400 errors) for serious bad tori and soft refusals where AI dey politely decline.

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`ResponsibleAIDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java) and ask:
> - "Wetin be InputGuardrail and how I fit create mine?"
> - "Wetin be difference between hard block and soft refusal?"
> - "Why e good to use both guardrails and provider filters together?"

## Next Steps

**Next Module:** [01-introduction - Getting Started with LangChain4j and gpt-5 on Azure](../01-introduction/README.md)

---

**Navigation:** [← Back to Main](../README.md) | [Next: Module 01 - Introduction →](../01-introduction/README.md)

---

## Troubleshooting

### First-Time Maven Build

**Issue**: First time you run `mvn clean compile` or `mvn package` e fit take long (10-15 minutes)

**Cause**: Maven need download all project dependencies (Spring Boot, LangChain4j libs, Azure SDKs, and others) for the first time e build.

**Solution**: Na normal. After first time, build go quick pass because dependencies go dey cache for your machine. The download time depend on your internet speed.

### PowerShell Maven Command Syntax

**Issue**: Maven commands dey fail with error `Unknown lifecycle phase ".mainClass=..."`

**Cause**: PowerShell dey treat `=` like assignment operator, so e go spoil Maven property syntax.
**Solution**: Use di stop-parsing operator `--%` before di Maven command:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

Di `--%` operator mean say PowerShell go pass all di remaining arguments as dem be go Maven without to interpret am.

### Windows PowerShell Emoji Display

**Issue**: AI responses dey show waste characters (e.g., `????` or `â??`) instead of emojis for PowerShell

**Cause**: PowerShell default encoding no dey support UTF-8 emojis

**Solution**: Run dis command before you run Java applications:
```cmd
chcp 65001
```

Dis one go force UTF-8 encoding for di terminal. Or else, make you use Windows Terminal wey get beta Unicode support.

### Debugging API Calls

**Issue**: Authentication errors, rate limits, or unexpected responses from di AI model

**Solution**: Di examples get `.logRequests(true)` and `.logResponses(true)` to show API calls for console. Dis one dey help troubleshoot authentication errors, rate limits, or unexpected responses. Remove dis flags for production to reduce log noise.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Disclaimer**:  
Dis document don translate wit AI translation service wey dem call [Co-op Translator](https://github.com/Azure/co-op-translator). Even tho we dey try make everything correct, abeg sabi say automated translation fit get some mistakes or wrong tins. Di original document wey na im own language na beta place to check for correct tori. If na important info, e good make professional human translate am. We no go take any wahala if person no understand or interpret dis translation well.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->