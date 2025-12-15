<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T19:57:51+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ko"
}
-->
# LangChain4j 용어집

## 목차

- [핵심 개념](../../../docs)
- [LangChain4j 구성 요소](../../../docs)
- [AI/ML 개념](../../../docs)
- [프롬프트 엔지니어링](../../../docs)
- [RAG (검색 증강 생성)](../../../docs)
- [에이전트 및 도구](../../../docs)
- [모델 컨텍스트 프로토콜 (MCP)](../../../docs)
- [Azure 서비스](../../../docs)
- [테스트 및 개발](../../../docs)

과정 전반에 걸쳐 사용되는 용어와 개념에 대한 빠른 참조입니다.

## 핵심 개념

**AI 에이전트** - AI를 사용하여 자율적으로 추론하고 행동하는 시스템. [모듈 04](../04-tools/README.md)

**체인** - 출력이 다음 단계로 입력되는 일련의 작업.

**청킹** - 문서를 더 작은 조각으로 나누기. 일반적으로 300-500 토큰, 중첩 포함. [모듈 03](../03-rag/README.md)

**컨텍스트 윈도우** - 모델이 처리할 수 있는 최대 토큰 수. GPT-5: 40만 토큰.

**임베딩** - 텍스트 의미를 나타내는 수치 벡터. [모듈 03](../03-rag/README.md)

**함수 호출** - 모델이 외부 함수를 호출하기 위한 구조화된 요청 생성. [모듈 04](../04-tools/README.md)

**환각** - 모델이 그럴듯하지만 잘못된 정보를 생성하는 현상.

**프롬프트** - 언어 모델에 입력하는 텍스트. [모듈 02](../02-prompt-engineering/README.md)

**의미 기반 검색** - 키워드가 아닌 임베딩을 사용한 의미 검색. [모듈 03](../03-rag/README.md)

**상태 저장 vs 상태 비저장** - 상태 비저장: 메모리 없음. 상태 저장: 대화 기록 유지. [모듈 01](../01-introduction/README.md)

**토큰** - 모델이 처리하는 기본 텍스트 단위. 비용과 한도에 영향. [모듈 01](../01-introduction/README.md)

**도구 체인** - 출력이 다음 호출에 영향을 주는 연속 도구 실행. [모듈 04](../04-tools/README.md)

## LangChain4j 구성 요소

**AiServices** - 타입 안전 AI 서비스 인터페이스 생성.

**OpenAiOfficialChatModel** - OpenAI 및 Azure OpenAI 모델을 위한 통합 클라이언트.

**OpenAiOfficialEmbeddingModel** - OpenAI 공식 클라이언트를 사용해 임베딩 생성 (OpenAI 및 Azure OpenAI 모두 지원).

**ChatModel** - 언어 모델의 핵심 인터페이스.

**ChatMemory** - 대화 기록 유지.

**ContentRetriever** - RAG용 관련 문서 청크 검색.

**DocumentSplitter** - 문서를 청크로 분할.

**EmbeddingModel** - 텍스트를 수치 벡터로 변환.

**EmbeddingStore** - 임베딩 저장 및 검색.

**MessageWindowChatMemory** - 최근 메시지의 슬라이딩 윈도우 유지.

**PromptTemplate** - `{{variable}}` 자리표시자가 있는 재사용 가능한 프롬프트 생성.

**TextSegment** - 메타데이터가 포함된 텍스트 청크. RAG에 사용.

**ToolExecutionRequest** - 도구 실행 요청 표현.

**UserMessage / AiMessage / SystemMessage** - 대화 메시지 유형.

## AI/ML 개념

**Few-Shot Learning** - 프롬프트에 예시 제공. [모듈 02](../02-prompt-engineering/README.md)

**대형 언어 모델 (LLM)** - 방대한 텍스트 데이터로 학습된 AI 모델.

**추론 노력** - GPT-5의 사고 깊이 제어 매개변수. [모듈 02](../02-prompt-engineering/README.md)

**온도** - 출력의 무작위성 제어. 낮음=결정적, 높음=창의적.

**벡터 데이터베이스** - 임베딩 전용 특수 데이터베이스. [모듈 03](../03-rag/README.md)

**Zero-Shot Learning** - 예시 없이 작업 수행. [모듈 02](../02-prompt-engineering/README.md)

## 프롬프트 엔지니어링 - [모듈 02](../02-prompt-engineering/README.md)

**사고의 사슬** - 더 나은 정확도를 위한 단계별 추론.

**제한된 출력** - 특정 형식 또는 구조 강제.

**높은 열의** - 철저한 추론을 위한 GPT-5 패턴.

**낮은 열의** - 빠른 답변을 위한 GPT-5 패턴.

**다중 턴 대화** - 교환 간 컨텍스트 유지.

**역할 기반 프롬프트** - 시스템 메시지를 통한 모델 페르소나 설정.

**자기 반성** - 모델이 자신의 출력을 평가하고 개선.

**구조화된 분석** - 고정된 평가 프레임워크.

**작업 실행 패턴** - 계획 → 실행 → 요약.

## RAG (검색 증강 생성) - [모듈 03](../03-rag/README.md)

**문서 처리 파이프라인** - 로드 → 청킹 → 임베딩 → 저장.

**인메모리 임베딩 저장소** - 테스트용 비영구 저장소.

**RAG** - 검색과 생성을 결합해 응답 근거 제공.

**유사도 점수** - 의미적 유사성의 척도 (0-1).

**출처 참조** - 검색된 콘텐츠에 대한 메타데이터.

## 에이전트 및 도구 - [모듈 04](../04-tools/README.md)

**@Tool 주석** - Java 메서드를 AI 호출 가능 도구로 표시.

**ReAct 패턴** - 추론 → 행동 → 관찰 → 반복.

**세션 관리** - 사용자별 별도 컨텍스트.

**도구** - AI 에이전트가 호출할 수 있는 함수.

**도구 설명** - 도구 목적 및 매개변수 문서화.

## 모델 컨텍스트 프로토콜 (MCP) - [모듈 05](../05-mcp/README.md)

**Docker 전송** - Docker 컨테이너 내 MCP 서버.

**MCP** - AI 앱과 외부 도구 연결 표준.

**MCP 클라이언트** - MCP 서버에 연결하는 애플리케이션.

**MCP 서버** - MCP를 통해 도구를 노출하는 서비스.

**서버 전송 이벤트 (SSE)** - HTTP를 통한 서버-클라이언트 스트리밍.

**Stdio 전송** - stdin/stdout을 통한 서브프로세스 서버.

**스트리밍 HTTP 전송** - 실시간 통신을 위한 SSE 포함 HTTP.

**도구 검색** - 클라이언트가 서버에 사용 가능한 도구 질의.

## Azure 서비스 - [모듈 01](../01-introduction/README.md)

**Azure AI 검색** - 벡터 기능을 갖춘 클라우드 검색. [모듈 03](../03-rag/README.md)

**Azure 개발자 CLI (azd)** - Azure 리소스 배포.

**Azure OpenAI** - 마이크로소프트의 엔터프라이즈 AI 서비스.

**Bicep** - Azure 인프라 코드 언어. [인프라 가이드](../01-introduction/infra/README.md)

**배포 이름** - Azure 내 모델 배포 이름.

**GPT-5** - 추론 제어가 가능한 최신 OpenAI 모델. [모듈 02](../02-prompt-engineering/README.md)

## 테스트 및 개발 - [테스트 가이드](TESTING.md)

**개발 컨테이너** - 컨테이너화된 개발 환경. [구성](../../../.devcontainer/devcontainer.json)

**GitHub 모델** - 무료 AI 모델 플레이그라운드. [모듈 00](../00-quick-start/README.md)

**인메모리 테스트** - 인메모리 저장소를 이용한 테스트.

**통합 테스트** - 실제 인프라를 이용한 테스트.

**Maven** - 자바 빌드 자동화 도구.

**Mockito** - 자바 모킹 프레임워크.

**Spring Boot** - 자바 애플리케이션 프레임워크. [모듈 01](../01-introduction/README.md)

**Testcontainers** - 테스트 내 Docker 컨테이너.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**면책 조항**:  
이 문서는 AI 번역 서비스 [Co-op Translator](https://github.com/Azure/co-op-translator)를 사용하여 번역되었습니다. 정확성을 위해 최선을 다하고 있으나, 자동 번역에는 오류나 부정확한 부분이 있을 수 있음을 유의하시기 바랍니다. 원문 문서가 권위 있는 출처로 간주되어야 합니다. 중요한 정보의 경우 전문적인 인간 번역을 권장합니다. 본 번역 사용으로 인한 오해나 잘못된 해석에 대해 당사는 책임을 지지 않습니다.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->