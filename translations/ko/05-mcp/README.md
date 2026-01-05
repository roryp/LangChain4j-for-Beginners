<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f89f4c106d110e4943c055dd1a2f1dff",
  "translation_date": "2025-12-30T22:12:50+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "ko"
}
-->
# 모듈 05: 모델 컨텍스트 프로토콜 (MCP)

## 목차

- [무엇을 배우게 될까요](../../../05-mcp)
- [MCP란 무엇인가요?](../../../05-mcp)
- [MCP 작동 방식](../../../05-mcp)
- [에이전틱 모듈](../../../05-mcp)
- [예제 실행하기](../../../05-mcp)
  - [전제 조건](../../../05-mcp)
- [빠른 시작](../../../05-mcp)
  - [파일 작업 (Stdio)](../../../05-mcp)
  - [슈퍼바이저 에이전트](../../../05-mcp)
    - [출력 이해하기](../../../05-mcp)
    - [에이전틱 모듈 기능 설명](../../../05-mcp)
- [핵심 개념](../../../05-mcp)
- [축하합니다!](../../../05-mcp)
  - [다음 단계는?](../../../05-mcp)

## 무엇을 배우게 될까요

이제 대화형 AI를 구축하고, 프롬프트를 마스터하고, 문서에 근거한 응답을 생성하며, 도구가 포함된 에이전트를 만들었습니다. 하지만 지금까지 만든 도구들은 모두 특정 애플리케이션을 위해 맞춤 제작된 것이었습니다. 만약 AI에게 누구나 만들고 공유할 수 있는 표준화된 도구 생태계에 접근할 수 있게 한다면 어떨까요? 이 모듈에서는 Model Context Protocol(MCP)과 LangChain4j의 에이전틱 모듈을 사용하여 바로 그것을 수행하는 방법을 배웁니다. 먼저 간단한 MCP 파일 리더를 소개한 후, Supervisor Agent 패턴을 사용하여 고급 에이전틱 워크플로우에 어떻게 쉽게 통합되는지 보여줍니다.

## MCP란 무엇인가요?

Model Context Protocol(MCP)은 정확히 그런 것을 제공합니다 — AI 애플리케이션이 외부 도구를 발견하고 사용할 수 있는 표준화된 방법입니다. 각 데이터 소스나 서비스마다 맞춤 통합을 작성하는 대신, 일관된 형식으로 기능을 노출하는 MCP 서버에 연결합니다. 그러면 AI 에이전트는 이러한 도구를 자동으로 발견하고 사용할 수 있습니다.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.ko.png" alt="MCP 비교" width="800"/>

*이전 MCP: 복잡한 포인트-투-포인트 통합. 이후 MCP: 하나의 프로토콜, 무한한 가능성.*

MCP는 AI 개발의 근본적인 문제를 해결합니다: 모든 통합이 맞춤형이라는 점입니다. GitHub에 액세스하고 싶나요? 맞춤 코드가 필요합니다. 파일을 읽고 싶나요? 맞춤 코드가 필요합니다. 데이터베이스를 쿼리하고 싶나요? 맞춤 코드가 필요합니다. 그리고 이러한 통합들은 다른 AI 애플리케이션과 호환되지 않습니다.

MCP는 이를 표준화합니다. MCP 서버는 명확한 설명과 스키마를 가진 도구들을 노출합니다. 어떤 MCP 클라이언트든 연결하여 사용 가능한 도구를 발견하고 사용할 수 있습니다. 한 번 빌드하면 어디서든 사용하세요.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.ko.png" alt="MCP 아키텍처" width="800"/>

*모델 컨텍스트 프로토콜 아키텍처 - 표준화된 도구 발견 및 실행*

## MCP 작동 방식

**서버-클라이언트 아키텍처**

MCP는 클라이언트-서버 모델을 사용합니다. 서버는 파일 읽기, 데이터베이스 쿼리, API 호출과 같은 도구를 제공합니다. 클라이언트(당신의 AI 애플리케이션)는 서버에 연결하여 그들의 도구를 사용합니다.

LangChain4j에서 MCP를 사용하려면 다음 Maven 의존성을 추가하세요:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**도구 발견**

클라이언트가 MCP 서버에 연결하면 "어떤 도구가 있나요?"라고 묻습니다. 서버는 설명과 파라미터 스키마를 포함한 사용 가능한 도구 목록으로 응답합니다. 그러면 AI 에이전트는 사용자 요청에 따라 어떤 도구를 사용할지 결정할 수 있습니다.

**전송 메커니즘**

MCP는 다양한 전송 메커니즘을 지원합니다. 이 모듈은 로컬 프로세스를 위한 Stdio 전송을 시연합니다:

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.ko.png" alt="전송 메커니즘" width="800"/>

*MCP 전송 메커니즘: 원격 서버용 HTTP, 로컬 프로세스용 Stdio*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

로컬 프로세스용입니다. 애플리케이션이 서버를 서브프로세스로 생성하고 표준 입력/출력을 통해 통신합니다. 파일 시스템 접근이나 커맨드라인 도구에 유용합니다.

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

> **🤖 GitHub Copilot Chat으로 시도해보세요:** [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)를 열고 다음을 물어보세요:
> - "Stdio 전송은 어떻게 작동하며 HTTP와 비교했을 때 언제 사용해야 하나요?"
> - "LangChain4j는 생성된 MCP 서버 프로세스의 수명 주기를 어떻게 관리하나요?"
> - "AI에게 파일 시스템 접근 권한을 주는 것의 보안적 함의는 무엇인가요?"

## 에이전틱 모듈

MCP가 표준화된 도구를 제공하는 반면, LangChain4j의 **에이전틱 모듈**은 이러한 도구들을 오케스트레이션하는 에이전트를 선언적으로 구축할 수 있는 방법을 제공합니다. `@Agent` 어노테이션과 `AgenticServices`를 통해 명령형 코드 대신 인터페이스를 통해 에이전트 동작을 정의할 수 있습니다.

이 모듈에서는 사용자 요청에 따라 어떤 서브에이전트를 호출할지 동적으로 결정하는 고급 에이전틱 AI 접근 방식인 **Supervisor Agent** 패턴을 살펴봅니다. 우리는 MCP 기반 파일 접근 기능을 서브에이전트 중 하나에 제공하여 두 개념을 결합합니다.

에이전틱 모듈을 사용하려면 다음 Maven 의존성을 추가하세요:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ 실험적:** `langchain4j-agentic` 모듈은 **실험적**이며 변경될 수 있습니다. 안정적인 AI 어시스턴트 구축 방식은 여전히 `langchain4j-core`와 커스텀 도구(모듈 04)입니다.

## 예제 실행하기

### 전제 조건

- Java 21+, Maven 3.9+
- Node.js 16+ 및 npm (MCP 서버용)
- 루트 디렉터리의 `.env` 파일에 환경 변수 구성:
  - **StdioTransportDemo용:** `GITHUB_TOKEN` (GitHub 개인 액세스 토큰)
  - **SupervisorAgentDemo용:** `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (모듈 01-04와 동일)

> **참고:** 아직 환경 변수를 설정하지 않았다면, 지침은 [Module 00 - Quick Start](../00-quick-start/README.md)를 참조하거나 루트 디렉터리에서 `.env.example`를 `.env`로 복사한 후 값을 채우세요.

## 빠른 시작

**VS Code 사용:** 탐색기에서 아무 데모 파일을 마우스 오른쪽 버튼으로 클릭하고 **"Run Java"**를 선택하거나, 실행 및 디버그 패널의 실행 구성(먼저 `.env` 파일에 토큰을 추가했는지 확인)을 사용하세요.

**Maven 사용:** 또는 아래 예제를 사용하여 명령줄에서 실행할 수 있습니다.

### 파일 작업 (Stdio)

이 예제는 로컬 서브프로세스 기반 도구를 보여줍니다.

**✅ 전제 조건 불필요** - MCP 서버는 자동으로 생성됩니다.

**VS Code 사용:** `StdioTransportDemo.java`를 마우스 오른쪽 버튼으로 클릭하고 **"Run Java"**를 선택하세요.

**Maven 사용:**

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

애플리케이션은 파일 시스템 MCP 서버를 자동으로 생성하고 로컬 파일을 읽습니다. 서브프로세스 관리는 자동으로 처리되는 것을 확인하세요.

**예상 출력:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### 슈퍼바이저 에이전트

<img src="../../../translated_images/agentic.cf84dcda226374e3.ko.png" alt="에이전틱 모듈" width="800"/>

**슈퍼바이저 에이전트 패턴**은 **유연한** 형태의 에이전틱 AI입니다. 결정론적 워크플로우(순차, 루프, 병렬)와 달리, 슈퍼바이저는 LLM을 사용하여 사용자 요청에 따라 어떤 에이전트를 호출할지 자율적으로 결정합니다.

**슈퍼바이저와 MCP 결합:** 이 예제에서는 `FileAgent`에 `toolProvider(mcpToolProvider)`를 통해 MCP 파일 시스템 도구 접근 권한을 제공합니다. 사용자가 "파일을 읽고 분석해 달라"고 요청하면 슈퍼바이저는 요청을 분석하고 실행 계획을 생성합니다. 그런 다음 요청을 `FileAgent`로 라우팅하고, `FileAgent`는 MCP의 `read_file` 도구를 사용해 내용을 가져옵니다. 슈퍼바이저는 그 내용을 `AnalysisAgent`로 전달하여 해석을 수행하고, 필요하면 `SummaryAgent`를 호출해 결과를 요약합니다.

이는 MCP 도구가 에이전틱 워크플로우에 원활하게 통합되는 방식을 보여줍니다 — 슈퍼바이저는 파일이 어떻게 읽히는지 알 필요가 없고, 단지 `FileAgent`가 그 작업을 수행할 수 있다는 것만 알면 됩니다. 슈퍼바이저는 다양한 유형의 요청에 동적으로 적응하며 마지막 에이전트의 응답이나 모든 작업의 요약을 반환합니다.

**시작 스크립트 사용(권장):**

시작 스크립트는 루트 `.env` 파일에서 환경 변수를 자동으로 로드합니다:

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

**VS Code 사용:** `SupervisorAgentDemo.java`를 마우스 오른쪽 버튼으로 클릭하고 **"Run Java"**를 선택하세요( `.env` 파일이 구성되어 있는지 확인).

**슈퍼바이저 작동 방식:**

```java
// 특정 기능을 가진 여러 에이전트를 정의
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // 파일 작업용 MCP 도구가 있음
        .build();

AnalysisAgent analysisAgent = AgenticServices.agentBuilder(AnalysisAgent.class)
        .chatModel(model)
        .build();

SummaryAgent summaryAgent = AgenticServices.agentBuilder(SummaryAgent.class)
        .chatModel(model)
        .build();

// 이 에이전트들을 조율하는 Supervisor 생성
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)  // "planner" 모델
        .subAgents(fileAgent, analysisAgent, summaryAgent)
        .responseStrategy(SupervisorResponseStrategy.SUMMARY)
        .build();

// Supervisor가 어떤 에이전트를 호출할지 자율적으로 결정함
// 자연어 요청만 전달하면 LLM이 실행을 계획함
String response = supervisor.invoke("Read the file at /path/file.txt and analyze it");
```

전체 구현은 [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java)를 참조하세요.

> **🤖 GitHub Copilot Chat으로 시도해보세요:** [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java)를 열고 다음을 물어보세요:
> - "슈퍼바이저는 어떤 에이전트를 호출할지 어떻게 결정하나요?"
> - "슈퍼바이저 패턴과 순차적 워크플로우 패턴의 차이는 무엇인가요?"
> - "슈퍼바이저의 계획(planning) 동작을 어떻게 맞춤화할 수 있나요?"

#### 출력 이해하기

데모를 실행하면 슈퍼바이저가 여러 에이전트를 어떻게 오케스트레이션하는지 구조화된 워크스루를 볼 수 있습니다. 각 섹션이 의미하는 바는 다음과 같습니다:

```
======================================================================
  SUPERVISOR AGENT DEMO
======================================================================

This demo shows how a Supervisor Agent orchestrates multiple specialized agents.
The Supervisor uses an LLM to decide which agent to call based on the task.
```

**헤더**는 데모를 소개하고 핵심 개념을 설명합니다: 슈퍼바이저는 하드코딩된 규칙이 아닌 LLM을 사용하여 호출할 에이전트를 결정합니다.

```
--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]     FileAgent     - Reads files using MCP filesystem tools
  [ANALYZE]  AnalysisAgent - Analyzes content for structure, tone, and themes
  [SUMMARY]  SummaryAgent  - Creates concise summaries of content
```

**사용 가능한 에이전트**는 슈퍼바이저가 선택할 수 있는 세 가지 전문 에이전트를 보여줍니다. 각 에이전트는 특정 기능을 가집니다:
- **FileAgent**는 MCP 도구를 사용해 파일을 읽을 수 있습니다 (외부 기능)
- **AnalysisAgent**는 내용을 분석합니다 (순수 LLM 기능)
- **SummaryAgent**는 요약을 생성합니다 (순수 LLM 기능)

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and analyze what it's about"
```

**사용자 요청**은 무엇이 요청되었는지를 보여줍니다. 슈퍼바이저는 이를 파싱하고 호출할 에이전트를 결정해야 합니다.

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

**슈퍼바이저 오케스트레이션**은 마법이 일어나는 곳입니다. 다음을 주목하세요:
1. 요청에 "파일을 읽다"가 언급되었기 때문에 슈퍼바이저는 **먼저 FileAgent를 선택했습니다**
2. FileAgent는 MCP의 `read_file` 도구를 사용하여 파일 내용을 검색했습니다
3. 그 다음 슈퍼바이저는 **AnalysisAgent를 선택**하고 파일 내용을 전달했습니다
4. AnalysisAgent는 구조, 어조 및 주제를 분석했습니다

슈퍼바이저는 사용자 요청을 바탕으로 이러한 결정을 **자율적으로** 내렸다는 점을 주목하세요 — 하드코딩된 워크플로우가 아닙니다!

**최종 응답**은 슈퍼바이저가 호출한 모든 에이전트의 출력물을 결합한 합성된 답변입니다. 예제는 각 에이전트가 저장한 요약 및 분석 결과를 보여주는 에이전틱 스코프를 덤프합니다.

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

### 에이전틱 모듈 기능 설명

예제는 에이전틱 모듈의 여러 고급 기능을 보여줍니다. Agentic Scope와 Agent Listeners를 자세히 살펴보겠습니다.

**에이전틱 스코프**는 에이전트들이 `@Agent(outputKey="...")`를 사용하여 결과를 저장한 공유 메모리를 보여줍니다. 이를 통해:
- 이후 에이전트가 이전 에이전트의 출력을 액세스할 수 있습니다
- 슈퍼바이저가 최종 응답을 합성할 수 있습니다
- 각 에이전트가 생성한 내용을 검사할 수 있습니다

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String story = scope.readState("story");
List<AgentInvocation> history = scope.agentInvocations("analysisAgent");
```

**에이전트 리스너**는 에이전트 실행을 모니터링하고 디버깅하는 기능을 제공합니다. 데모에서 보는 단계별 출력은 각 에이전트 호출에 훅을 거는 AgentListener에서 나옵니다:
- **beforeAgentInvocation** - 슈퍼바이저가 에이전트를 선택할 때 호출되어 어떤 에이전트가 왜 선택되었는지 볼 수 있게 합니다
- **afterAgentInvocation** - 에이전트가 완료되었을 때 호출되어 그 결과를 보여줍니다
- **inheritedBySubagents** - true일 경우, 리스너는 계층의 모든 에이전트를 모니터링합니다

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
        return true; // 모든 하위 에이전트에게 전파
    }
};
```

슈퍼바이저 패턴 외에도 `langchain4j-agentic` 모듈은 여러 강력한 워크플로우 패턴과 기능을 제공합니다:

| 패턴 | 설명 | 사용 사례 |
|---------|-------------|----------|
| **Sequential** | 에이전트를 순서대로 실행하고 출력이 다음으로 흐름 | 파이프라인: 조사 → 분석 → 보고서 |
| **Parallel** | 에이전트를 동시에 실행 | 독립 작업: 날씨 + 뉴스 + 주식 |
| **Loop** | 조건이 만족될 때까지 반복 | 품질 점수: 점수 ≥ 0.8이 될 때까지 개선 |
| **Conditional** | 조건에 따라 라우팅 | 분류 → 전문 에이전트로 라우팅 |
| **Human-in-the-Loop** | 인간 검토 지점 추가 | 승인 워크플로우, 콘텐츠 검토 |

## 핵심 개념

**MCP**는 기존 도구 생태계를 활용하려 할 때, 여러 애플리케이션이 공유할 도구를 구축하려 할 때, 표준 프로토콜로 서드파티 서비스를 통합하려 할 때, 또는 코드를 변경하지 않고 도구 구현을 교체하려 할 때 이상적입니다.

**에이전틱 모듈**은 `@Agent` 어노테이션으로 선언적 에이전트 정의가 필요할 때, 워크플로우 오케스트레이션(순차, 루프, 병렬)이 필요할 때, 명령형 코드보다 인터페이스 기반 에이전트 설계를 선호할 때, 또는 `outputKey`를 통해 출력을 공유하는 여러 에이전트를 결합할 때 가장 적합합니다.

**슈퍼바이저 에이전트 패턴**은 워크플로우를 사전에 예측할 수 없고 LLM이 결정을 내리길 원할 때, 여러 전문 에이전트를 동적으로 오케스트레이션해야 할 때, 다양한 기능으로 라우팅하는 대화형 시스템을 구축할 때, 또는 가장 유연하고 적응 가능한 에이전트 동작을 원할 때 빛을 발합니다.

## 축하합니다!

LangChain4j for Beginners 과정을 완료했습니다. 여러분은 다음을 배웠습니다:

- 메모리를 가진 대화형 AI 구축 방법 (모듈 01)
- 다양한 작업을 위한 프롬프트 엔지니어링 패턴 (모듈 02)
- RAG로 문서에 근거한 응답 생성 방법 (모듈 03)
- 커스텀 도구로 기본 AI 에이전트(어시스턴트) 생성 방법 (모듈 04)
- LangChain4j MCP 및 Agentic 모듈과 표준화된 도구 통합 (Module 05)

### 다음 단계는?

모듈을 완료한 후, LangChain4j의 테스트 개념이 실제로 어떻게 적용되는지 보려면 [테스트 가이드](../docs/TESTING.md)를 살펴보세요.

**공식 자료:**
- [LangChain4j 문서](https://docs.langchain4j.dev/) - 종합 가이드 및 API 참조
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - 소스 코드 및 예제
- [LangChain4j 튜토리얼](https://docs.langchain4j.dev/tutorials/) - 다양한 사용 사례에 대한 단계별 튜토리얼

이 강좌를 완료해 주셔서 감사합니다!

---

**탐색:** [← 이전: Module 04 - Tools](../04-tools/README.md) | [메인으로 돌아가기](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
면책사항:
이 문서는 AI 번역 서비스 Co-op Translator (https://github.com/Azure/co-op-translator)를 사용하여 번역되었습니다. 정확성을 위해 최선을 다하고 있으나 자동 번역에는 오류나 부정확성이 포함될 수 있음을 양지해 주시기 바랍니다. 원문(원어) 문서를 권위 있는 출처로 간주해야 합니다. 중요한 정보의 경우 전문적인 인간 번역을 권장합니다. 본 번역의 사용으로 인해 발생한 오해나 잘못된 해석에 대해서는 당사가 책임을 지지 않습니다.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->