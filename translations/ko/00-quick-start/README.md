<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T14:42:05+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "ko"
}
-->
# Module 00: 빠른 시작

## 목차

- [소개](../../../00-quick-start)
- [LangChain4j란?](../../../00-quick-start)
- [LangChain4j 의존성](../../../00-quick-start)
- [사전 준비 사항](../../../00-quick-start)
- [설정](../../../00-quick-start)
  - [1. GitHub 토큰 받기](../../../00-quick-start)
  - [2. 토큰 설정하기](../../../00-quick-start)
- [예제 실행하기](../../../00-quick-start)
  - [1. 기본 채팅](../../../00-quick-start)
  - [2. 프롬프트 패턴](../../../00-quick-start)
  - [3. 함수 호출](../../../00-quick-start)
  - [4. 문서 Q&A (RAG)](../../../00-quick-start)
- [각 예제가 보여주는 것](../../../00-quick-start)
- [다음 단계](../../../00-quick-start)
- [문제 해결](../../../00-quick-start)

## 소개

이 빠른 시작 가이드는 LangChain4j를 최대한 빠르게 시작할 수 있도록 설계되었습니다. LangChain4j와 GitHub 모델을 사용하여 AI 애플리케이션을 구축하는 기본 사항을 다룹니다. 다음 모듈에서는 Azure OpenAI와 LangChain4j를 사용하여 더 고급 애플리케이션을 구축할 것입니다.

## LangChain4j란?

LangChain4j는 AI 기반 애플리케이션 구축을 단순화하는 자바 라이브러리입니다. HTTP 클라이언트와 JSON 파싱을 직접 다루는 대신, 깔끔한 자바 API로 작업할 수 있습니다.

LangChain의 "체인"은 여러 구성 요소를 연결하는 것을 의미합니다 - 프롬프트를 모델에 연결하고, 모델을 파서에 연결하거나, 여러 AI 호출을 연결하여 한 출력이 다음 입력으로 이어지게 할 수 있습니다. 이 빠른 시작은 더 복잡한 체인을 탐구하기 전에 기본 개념에 집중합니다.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1.ko.png" alt="LangChain4j Chaining Concept" width="800"/>

*LangChain4j에서 구성 요소 체인 연결 - 빌딩 블록들이 연결되어 강력한 AI 워크플로우를 만듭니다*

세 가지 핵심 구성 요소를 사용합니다:

**ChatLanguageModel** - AI 모델과 상호작용하는 인터페이스입니다. `model.chat("prompt")`를 호출하면 응답 문자열을 받습니다. OpenAI 호환 엔드포인트(예: GitHub 모델)와 작동하는 `OpenAiOfficialChatModel`을 사용합니다.

**AiServices** - 타입 안전 AI 서비스 인터페이스를 생성합니다. 메서드를 정의하고 `@Tool`로 주석을 달면 LangChain4j가 오케스트레이션을 처리합니다. AI가 필요할 때 자동으로 자바 메서드를 호출합니다.

**MessageWindowChatMemory** - 대화 기록을 유지합니다. 이것이 없으면 각 요청은 독립적입니다. 이것이 있으면 AI가 이전 메시지를 기억하고 여러 턴에 걸쳐 문맥을 유지합니다.

<img src="../../../translated_images/architecture.eedc993a1c576839.ko.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j 아키텍처 - 핵심 구성 요소들이 함께 작동하여 AI 애플리케이션을 구동합니다*

## LangChain4j 의존성

이 빠른 시작은 [`pom.xml`](../../../00-quick-start/pom.xml)에서 두 개의 Maven 의존성을 사용합니다:

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

`langchain4j-open-ai-official` 모듈은 OpenAI 호환 API에 연결하는 `OpenAiOfficialChatModel` 클래스를 제공합니다. GitHub 모델은 동일한 API 형식을 사용하므로 별도의 어댑터가 필요 없으며, 기본 URL을 `https://models.github.ai/inference`로 지정하기만 하면 됩니다.

## 사전 준비 사항

**Dev Container 사용 중인가요?** Java와 Maven이 이미 설치되어 있습니다. GitHub 개인 액세스 토큰만 필요합니다.

**로컬 개발:**
- Java 21 이상, Maven 3.9 이상
- GitHub 개인 액세스 토큰 (아래 지침 참조)

> **참고:** 이 모듈은 GitHub 모델의 `gpt-4.1-nano`를 사용합니다. 코드 내 모델 이름을 변경하지 마세요 - GitHub에서 제공하는 모델과 작동하도록 구성되어 있습니다.

## 설정

### 1. GitHub 토큰 받기

1. [GitHub 설정 → 개인 액세스 토큰](https://github.com/settings/personal-access-tokens)으로 이동
2. "새 토큰 생성" 클릭
3. 설명 이름 설정 (예: "LangChain4j Demo")
4. 만료 기간 설정 (7일 권장)
5. "계정 권한"에서 "Models"를 찾아 "읽기 전용"으로 설정
6. "토큰 생성" 클릭
7. 토큰을 복사하여 저장 - 다시 볼 수 없습니다

### 2. 토큰 설정하기

**옵션 1: VS Code 사용 (권장)**

VS Code를 사용하는 경우 프로젝트 루트의 `.env` 파일에 토큰을 추가하세요:

`.env` 파일이 없으면 `.env.example`을 `.env`로 복사하거나 새 `.env` 파일을 만드세요.

**예시 `.env` 파일:**
```bash
# /workspaces/LangChain4j-for-Beginners/.env 안에 있습니다
GITHUB_TOKEN=your_token_here
```

그런 다음 탐색기에서 임의의 데모 파일(예: `BasicChatDemo.java`)을 마우스 오른쪽 버튼으로 클릭하고 **"Run Java"**를 선택하거나 실행 및 디버그 패널의 실행 구성을 사용할 수 있습니다.

**옵션 2: 터미널 사용**

환경 변수로 토큰을 설정하세요:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## 예제 실행하기

**VS Code 사용 시:** 탐색기에서 임의의 데모 파일을 마우스 오른쪽 버튼으로 클릭하고 **"Run Java"**를 선택하거나 실행 및 디버그 패널의 실행 구성을 사용하세요 (먼저 `.env` 파일에 토큰을 추가했는지 확인).

**Maven 사용 시:** 또는 명령줄에서 실행할 수 있습니다:

### 1. 기본 채팅

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. 프롬프트 패턴

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

제로샷, 퓨샷, 체인 오브 생각, 역할 기반 프롬프트를 보여줍니다.

### 3. 함수 호출

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

필요할 때 AI가 자동으로 자바 메서드를 호출합니다.

### 4. 문서 Q&A (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

`document.txt`의 내용에 대해 질문하세요.

## 각 예제가 보여주는 것

**기본 채팅** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

LangChain4j의 가장 기본적인 사용법을 여기서 시작하세요. `OpenAiOfficialChatModel`을 생성하고 `.chat()`으로 프롬프트를 보내 응답을 받습니다. 이 예제는 사용자 지정 엔드포인트와 API 키로 모델을 초기화하는 기본을 보여줍니다. 이 패턴을 이해하면 나머지 모든 것이 이 위에 구축됩니다.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 [GitHub Copilot](https://github.com/features/copilot) 채팅으로 시도해보세요:** [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)를 열고 다음을 물어보세요:
> - "이 코드에서 GitHub 모델에서 Azure OpenAI로 어떻게 전환하나요?"
> - "OpenAiOfficialChatModel.builder()에서 설정할 수 있는 다른 매개변수는 무엇인가요?"
> - "완전한 응답을 기다리지 않고 스트리밍 응답을 추가하려면 어떻게 하나요?"

**프롬프트 엔지니어링** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

모델과 대화하는 방법을 알았으니, 이제 무엇을 말하는지 탐구해봅시다. 이 데모는 동일한 모델 설정을 사용하지만 네 가지 다른 프롬프트 패턴을 보여줍니다. 직접 지시하는 제로샷 프롬프트, 예제로 학습하는 퓨샷 프롬프트, 추론 단계를 보여주는 체인 오브 생각 프롬프트, 문맥을 설정하는 역할 기반 프롬프트를 시도해보세요. 같은 모델이라도 요청하는 방식에 따라 결과가 크게 달라지는 것을 볼 수 있습니다.

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

> **🤖 [GitHub Copilot](https://github.com/features/copilot) 채팅으로 시도해보세요:** [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)를 열고 다음을 물어보세요:
> - "제로샷과 퓨샷 프롬프트의 차이점은 무엇이며, 각각 언제 사용해야 하나요?"
> - "온도(temperature) 매개변수가 모델 응답에 어떤 영향을 미치나요?"
> - "프로덕션에서 프롬프트 인젝션 공격을 방지하는 기술은 무엇이 있나요?"
> - "공통 패턴에 재사용 가능한 PromptTemplate 객체를 어떻게 만들 수 있나요?"

**도구 통합** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

여기서 LangChain4j의 강력함이 드러납니다. `AiServices`를 사용해 자바 메서드를 호출할 수 있는 AI 어시스턴트를 만듭니다. 메서드에 `@Tool("설명")` 주석을 달기만 하면 LangChain4j가 나머지를 처리합니다 - AI가 사용자가 요청하는 내용에 따라 언제 어떤 도구를 사용할지 자동으로 결정합니다. 이는 함수 호출을 시연하는 예제로, AI가 단순히 질문에 답하는 것을 넘어 행동을 취할 수 있게 하는 핵심 기술입니다.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 [GitHub Copilot](https://github.com/features/copilot) 채팅으로 시도해보세요:** [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)를 열고 다음을 물어보세요:
> - "@Tool 주석은 어떻게 작동하며 LangChain4j는 내부적으로 무엇을 하나요?"
> - "AI가 여러 도구를 순차적으로 호출해 복잡한 문제를 해결할 수 있나요?"
> - "도구가 예외를 던지면 어떻게 처리해야 하나요?"
> - "이 계산기 예제 대신 실제 API를 통합하려면 어떻게 해야 하나요?"

**문서 Q&A (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

여기서는 RAG(검색 증강 생성)의 기초를 볼 수 있습니다. 모델의 훈련 데이터에 의존하는 대신 [`document.txt`](../../../00-quick-start/document.txt)에서 내용을 불러와 프롬프트에 포함시킵니다. AI는 일반 지식이 아니라 문서 기반으로 답변합니다. 이는 자체 데이터를 활용하는 시스템을 구축하는 첫걸음입니다.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **참고:** 이 간단한 방법은 전체 문서를 프롬프트에 로드합니다. 큰 파일(10KB 이상)은 컨텍스트 한도를 초과할 수 있습니다. 모듈 03에서는 프로덕션 RAG 시스템을 위한 청크 분할과 벡터 검색을 다룹니다.

> **🤖 [GitHub Copilot](https://github.com/features/copilot) 채팅으로 시도해보세요:** [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)를 열고 다음을 물어보세요:
> - "RAG가 모델 훈련 데이터 사용과 비교해 AI 환각을 어떻게 방지하나요?"
> - "이 간단한 방법과 벡터 임베딩을 이용한 검색의 차이점은 무엇인가요?"
> - "여러 문서나 더 큰 지식 베이스를 처리하려면 어떻게 확장하나요?"
> - "AI가 제공된 문맥만 사용하도록 프롬프트를 구조화하는 모범 사례는 무엇인가요?"

## 디버깅

예제에는 `.logRequests(true)`와 `.logResponses(true)`가 포함되어 있어 콘솔에 API 호출을 표시합니다. 인증 오류, 속도 제한, 예상치 못한 응답 문제를 해결하는 데 도움이 됩니다. 프로덕션에서는 로그 소음을 줄이기 위해 이 플래그를 제거하세요.

## 다음 단계

**다음 모듈:** [01-introduction - LangChain4j와 Azure에서 gpt-5 시작하기](../01-introduction/README.md)

---

**탐색:** [← 메인으로 돌아가기](../README.md) | [다음: Module 01 - 소개 →](../01-introduction/README.md)

---

## 문제 해결

### 처음 Maven 빌드 시

**문제:** 초기 `mvn clean compile` 또는 `mvn package` 실행이 오래 걸림 (10-15분)

**원인:** Maven이 첫 빌드 시 모든 프로젝트 의존성(Spring Boot, LangChain4j 라이브러리, Azure SDK 등)을 다운로드해야 함

**해결:** 정상적인 동작입니다. 이후 빌드는 로컬에 캐시된 의존성 덕분에 훨씬 빠릅니다. 다운로드 시간은 네트워크 속도에 따라 다릅니다.

### PowerShell Maven 명령 구문

**문제:** Maven 명령이 `Unknown lifecycle phase ".mainClass=..."` 오류로 실패

**원인:** PowerShell이 `=`를 변수 할당 연산자로 해석하여 Maven 속성 구문이 깨짐

**해결:** Maven 명령 앞에 중지 구문 연산자 `--%`를 사용하세요:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

`--%` 연산자는 PowerShell에 나머지 인수를 해석하지 않고 Maven에 문자 그대로 전달하도록 지시합니다.

### Windows PowerShell 이모지 표시 문제

**문제:** PowerShell에서 AI 응답에 이모지 대신 `????` 또는 `â??` 같은 깨진 문자 표시

**원인:** PowerShell 기본 인코딩이 UTF-8 이모지를 지원하지 않음

**해결:** Java 애플리케이션 실행 전에 다음 명령을 실행하세요:
```cmd
chcp 65001
```

이 명령은 터미널에서 UTF-8 인코딩을 강제합니다. 또는 유니코드 지원이 더 좋은 Windows Terminal을 사용하세요.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**면책 조항**:  
이 문서는 AI 번역 서비스 [Co-op Translator](https://github.com/Azure/co-op-translator)를 사용하여 번역되었습니다. 정확성을 위해 최선을 다하고 있으나, 자동 번역에는 오류나 부정확한 부분이 있을 수 있음을 유의하시기 바랍니다. 원문 문서가 권위 있는 출처로 간주되어야 합니다. 중요한 정보의 경우 전문적인 인간 번역을 권장합니다. 본 번역 사용으로 인해 발생하는 오해나 잘못된 해석에 대해 당사는 책임을 지지 않습니다.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->