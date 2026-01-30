# LangChain4j 용어집

## 목차

- [핵심 개념](../../../docs)
- [LangChain4j 구성 요소](../../../docs)
- [AI/ML 개념](../../../docs)
- [가드레일](../../../docs)
- [프롬프트 엔지니어링](../../../docs)
- [RAG (검색 강화 생성)](../../../docs)
- [에이전트와 도구](../../../docs)
- [에이전틱 모듈](../../../docs)
- [모델 컨텍스트 프로토콜 (MCP)](../../../docs)
- [Azure 서비스](../../../docs)
- [테스트 및 개발](../../../docs)

코스 전반에서 사용되는 용어 및 개념에 대한 빠른 참조입니다.

## 핵심 개념

**AI 에이전트** - AI를 사용해 자율적으로 추론하고 행동하는 시스템. [모듈 04](../04-tools/README.md)

**체인** - 출력이 다음 단계의 입력이 되는 연속된 작업 순서.

**청킹(Chunking)** - 문서를 더 작은 조각으로 나누는 작업. 일반적 크기: 300-500 토큰, 중첩 포함. [모듈 03](../03-rag/README.md)

**컨텍스트 윈도우** - 모델이 처리할 수 있는 최대 토큰 수. GPT-5: 40만 토큰.

**임베딩(Embeddings)** - 텍스트 의미를 나타내는 수치 벡터. [모듈 03](../03-rag/README.md)

**함수 호출(Function Calling)** - 모델이 외부 함수를 호출하기 위한 구조화된 요청 생성. [모듈 04](../04-tools/README.md)

**환각(Hallucination)** - 모델이 틀렸지만 그럴듯한 정보를 생성하는 현상.

**프롬프트(Prompt)** - 언어 모델에 대한 텍스트 입력. [모듈 02](../02-prompt-engineering/README.md)

**시맨틱 검색** - 임베딩을 사용해 의미 기반으로 검색, 키워드가 아님. [모듈 03](../03-rag/README.md)

**상태 기반 vs 비상태 기반** - 비상태: 메모리 없음. 상태 기반: 대화 기록 유지. [모듈 01](../01-introduction/README.md)

**토큰(Tokens)** - 모델이 처리하는 기본 텍스트 단위. 비용과 한도에 영향. [모듈 01](../01-introduction/README.md)

**도구 체인** - 출력이 다음 호출의 입력이 되는 연속 도구 실행. [모듈 04](../04-tools/README.md)

## LangChain4j 구성 요소

**AiServices** - 타입 안전 AI 서비스 인터페이스 생성.

**OpenAiOfficialChatModel** - OpenAI 및 Azure OpenAI 모델을 위한 통합 클라이언트.

**OpenAiOfficialEmbeddingModel** - OpenAI 공식 클라이언트를 사용해 임베딩 생성 (OpenAI와 Azure OpenAI 모두 지원).

**ChatModel** - 언어 모델의 핵심 인터페이스.

**ChatMemory** - 대화 기록 유지.

**ContentRetriever** - RAG에 적합한 문서 조각 검색.

**DocumentSplitter** - 문서를 청크로 분할.

**EmbeddingModel** - 텍스트를 수치 벡터로 변환.

**EmbeddingStore** - 임베딩 저장 및 검색.

**MessageWindowChatMemory** - 최근 메시지의 슬라이딩 윈도우 유지.

**PromptTemplate** - `{{variable}}` 플레이스홀더가 포함된 재사용 가능한 프롬프트 생성.

**TextSegment** - 메타데이터가 포함된 텍스트 청크. RAG에 사용.

**ToolExecutionRequest** - 도구 실행 요청 표현.

**UserMessage / AiMessage / SystemMessage** - 대화 메시지 유형.

## AI/ML 개념

**Few-Shot 학습** - 프롬프트에 예시 제공. [모듈 02](../02-prompt-engineering/README.md)

**대형 언어 모델(LLM)** - 방대한 텍스트 데이터로 학습된 AI 모델.

**추론 노력(Reasoning Effort)** - GPT-5가 사고 깊이를 제어하는 파라미터. [모듈 02](../02-prompt-engineering/README.md)

**온도(Temperature)** - 출력의 무작위성 제어. 낮으면 결정적, 높으면 창의적.

**벡터 데이터베이스** - 임베딩 전용 특수 데이터베이스. [모듈 03](../03-rag/README.md)

**제로샷 학습(Zero-Shot Learning)** - 예시 없이 작업 수행. [모듈 02](../02-prompt-engineering/README.md)

## 가드레일 - [모듈 00](../00-quick-start/README.md)

**방어 심층 전략** - 애플리케이션 수준 가드레일과 공급자 안전 필터를 결합한 다층 보안 접근법.

**하드 블록(Hard Block)** - 심각한 내용 위반 시 공급자가 HTTP 400 오류 발생.

**InputGuardrail** - 사용자 입력이 LLM에 도달하기 전에 검증하는 LangChain4j 인터페이스. 유해한 프롬프트를 조기에 차단하여 비용과 지연 감소.

**InputGuardrailResult** - 가드레일 검증의 반환 유형: `success()` 또는 `fatal("reason")`.

**OutputGuardrail** - 사용자에게 반환되기 전 AI 응답 검증 인터페이스.

**공급자 안전 필터(Provider Safety Filters)** - API 수준에서 위반 사항을 감지하는 AI 공급자의 내장 콘텐츠 필터 (예: GitHub Models).

**소프트 거부(Soft Refusal)** - 모델이 오류 없이 정중하게 답변을 거절.

## 프롬프트 엔지니어링 - [모듈 02](../02-prompt-engineering/README.md)

**사고 연쇄(Chain-of-Thought)** - 단계별 추론으로 정확도 향상.

**제한된 출력(Constrained Output)** - 특정 형식이나 구조 강제.

**높은 적극성(High Eagerness)** - 철저한 추론을 위한 GPT-5 패턴.

**낮은 적극성(Low Eagerness)** - 빠른 답변을 위한 GPT-5 패턴.

**복수 턴 대화(Multi-Turn Conversation)** - 교환 간 문맥 유지.

**역할 기반 프롬프트(Role-Based Prompting)** - 시스템 메시지를 통해 모델 페르소나 설정.

**자기 반성(Self-Reflection)** - 모델이 자신의 출력을 평가하고 개선.

**구조화된 분석(Structured Analysis)** - 고정된 평가 프레임워크.

**작업 실행 패턴(Task Execution Pattern)** - 계획 → 실행 → 요약.

## RAG (검색 강화 생성) - [모듈 03](../03-rag/README.md)

**문서 처리 파이프라인** - 로드 → 청크 분할 → 임베딩 → 저장.

**메모리 내 임베딩 저장소** - 테스트용 비영구 저장.

**RAG** - 검색과 생성을 결합해 응답의 근거 확보.

**유사도 점수(Similarity Score)** - 의미 유사도를 0에서 1 사이로 측정.

**출처 참조(Source Reference)** - 검색된 콘텐츠 메타데이터.

## 에이전트와 도구 - [모듈 04](../04-tools/README.md)

**@Tool 주석** - 자바 메서드를 AI 호출 가능한 도구로 표시.

**ReAct 패턴** - 추론 → 행동 → 관찰 → 반복.

**세션 관리** - 사용자별 분리된 컨텍스트.

**도구(Tool)** - AI 에이전트가 호출할 수 있는 기능.

**도구 설명(Tool Description)** - 도구 목적과 매개변수 문서화.

## 에이전틱 모듈 - [모듈 05](../05-mcp/README.md)

**@Agent 주석** - 선언적 행동 정의와 함께 인터페이스를 AI 에이전트로 표시.

**에이전트 리스너** - `beforeAgentInvocation()` 및 `afterAgentInvocation()`을 통한 에이전트 실행 모니터링 훅.

**에이전틱 스코프** - `outputKey`를 사용해 에이전트가 출력을 저장하여 하위 에이전트가 사용할 수 있도록 공유 메모리.

**AgenticServices** - `agentBuilder()`와 `supervisorBuilder()`를 사용해 에이전트 생성하는 팩토리.

**조건부 워크플로우** - 조건에 따라 다른 전문 에이전트로 라우팅.

**휴먼 인 더 루프(Human-in-the-Loop)** - 승인 또는 콘텐츠 검토를 위한 인간 체크포인트 추가 워크플로우 패턴.

**langchain4j-agentic** - 선언적 에이전트 빌딩을 위한 Maven 의존성 (실험적).

**반복 워크플로우(Loop Workflow)** - 특정 조건(예: 품질 점수 ≥ 0.8)이 충족될 때까지 에이전트 실행 반복.

**outputKey** - 에이전트 주석 매개변수로 결과가 에이전틱 스코프 내 저장될 위치 지정.

**병렬 워크플로우(Parallel Workflow)** - 독립 작업 처리를 위해 여러 에이전트를 동시에 실행.

**응답 전략(Response Strategy)** - 감독자가 최종 답변을 구성하는 방식: LAST, SUMMARY, 또는 SCORED.

**순차 워크플로우(Sequential Workflow)** - 출력이 다음 단계로 흐르는 순서대로 에이전트 실행.

**감독 에이전트 패턴(Supervisor Agent Pattern)** - 감독 LLM이 동적으로 하위 에이전트를 호출할 에이전트를 결정하는 고급 에이전틱 패턴.

## 모델 컨텍스트 프로토콜 (MCP) - [모듈 05](../05-mcp/README.md)

**langchain4j-mcp** - LangChain4j의 MCP 통합용 Maven 의존성.

**MCP** - 모델 컨텍스트 프로토콜: AI 앱과 외부 도구를 연결하는 표준. 한 번 구축해 어디서나 사용.

**MCP 클라이언트** - 도구를 발견하고 사용하기 위해 MCP 서버에 연결하는 애플리케이션.

**MCP 서버** - 명확한 설명과 매개변수 스키마를 갖춘 MCP를 통해 도구를 노출하는 서비스.

**McpToolProvider** - MCP 도구를 래핑해 AI 서비스와 에이전트에서 사용할 수 있도록 하는 LangChain4j 구성요소.

**McpTransport** - MCP 통신용 인터페이스. 구현체에는 Stdio 및 HTTP가 포함됨.

**Stdio 전송** - stdin/stdout을 통해 로컬 프로세스 전송. 파일 시스템 접근이나 명령줄 도구에 유용.

**StdioMcpTransport** - MCP 서버를 서브프로세스로 실행하는 LangChain4j 구현체.

**도구 검색** - 클라이언트가 설명과 스키마를 포함한 사용 가능한 도구를 서버에 질의.

## Azure 서비스 - [모듈 01](../01-introduction/README.md)

**Azure AI 검색** - 벡터 기능을 갖춘 클라우드 검색. [모듈 03](../03-rag/README.md)

**Azure 개발자 CLI (azd)** - Azure 리소스 배포 도구.

**Azure OpenAI** - 마이크로소프트의 엔터프라이즈 AI 서비스.

**Bicep** - Azure 인프라 코드 언어. [인프라 가이드](../01-introduction/infra/README.md)

**배포 이름** - Azure에서 모델 배포 이름.

**GPT-5** - 추론 제어 기능을 갖춘 최신 OpenAI 모델. [모듈 02](../02-prompt-engineering/README.md)

## 테스트 및 개발 - [테스트 가이드](TESTING.md)

**개발 컨테이너** - 컨테이너화된 개발 환경. [설정](../../../.devcontainer/devcontainer.json)

**GitHub 모델** - 무료 AI 모델 실험 공간. [모듈 00](../00-quick-start/README.md)

**메모리 내 테스트** - 메모리 내 저장소를 사용한 테스트.

**통합 테스트** - 실제 인프라를 사용하는 테스트.

**Maven** - 자바 빌드 자동화 도구.

**Mockito** - 자바 모킹 프레임워크.

**Spring Boot** - 자바 애플리케이션 프레임워크. [모듈 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**면책 조항**:  
이 문서는 AI 번역 서비스 [Co-op Translator](https://github.com/Azure/co-op-translator)를 사용하여 번역되었습니다. 정확성을 위해 노력하고 있으나, 자동 번역에는 오류나 부정확성이 있을 수 있음을 알려드립니다. 원문 문서가 권위 있는 출처로 간주되어야 합니다. 중요한 정보의 경우 전문 인력에 의한 번역을 권장합니다. 본 번역 사용으로 발생하는 오해나 잘못된 해석에 대해 당사는 책임을 지지 않습니다.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->