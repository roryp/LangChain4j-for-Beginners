<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T22:17:53+00:00",
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
- [RAG (검색 보강 생성)](../../../docs)
- [에이전트 및 도구](../../../docs)
- [모델 컨텍스트 프로토콜 (MCP)](../../../docs)
- [Azure 서비스](../../../docs)
- [테스트 및 개발](../../../docs)

과정 전반에 걸쳐 사용되는 용어와 개념에 대한 빠른 참조입니다.

## Core Concepts

**AI Agent** - AI를 사용해 자율적으로 추론하고 행동하는 시스템입니다. [Module 04](../04-tools/README.md)

**Chain** - 출력이 다음 단계로 전달되는 일련의 연산입니다.

**Chunking** - 문서를 더 작은 조각으로 분할하는 것. 일반적으로 겹침과 함께 300-500 토큰입니다. [Module 03](../03-rag/README.md)

**Context Window** - 모델이 처리할 수 있는 최대 토큰 수입니다. GPT-5: 400K 토큰.

**Embeddings** - 텍스트 의미를 나타내는 수치 벡터입니다. [Module 03](../03-rag/README.md)

**Function Calling** - 모델이 외부 함수를 호출하기 위한 구조화된 요청을 생성하는 것. [Module 04](../04-tools/README.md)

**Hallucination** - 모델이 그럴듯하지만 잘못된 정보를 생성하는 경우입니다.

**Prompt** - 언어 모델에 대한 텍스트 입력입니다. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - 키워드가 아닌 의미를 사용해 임베딩으로 검색하는 것. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: 메모리가 없음. Stateful: 대화 기록을 유지함. [Module 01](../01-introduction/README.md)

**Tokens** - 모델이 처리하는 기본 텍스트 단위입니다. 비용과 한도에 영향을 줍니다. [Module 01](../01-introduction/README.md)

**Tool Chaining** - 출력이 다음 호출에 정보를 제공하는 순차적 도구 실행입니다. [Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - 타입 안전한 AI 서비스 인터페이스를 생성합니다.

**OpenAiOfficialChatModel** - OpenAI 및 Azure OpenAI 모델을 위한 통합 클라이언트입니다.

**OpenAiOfficialEmbeddingModel** - OpenAI Official 클라이언트를 사용하여 임베딩을 생성합니다( OpenAI 및 Azure OpenAI 모두 지원).

**ChatModel** - 언어 모델의 핵심 인터페이스입니다.

**ChatMemory** - 대화 기록을 유지합니다.

**ContentRetriever** - RAG를 위해 관련 문서 조각을 찾습니다.

**DocumentSplitter** - 문서를 조각으로 분할합니다.

**EmbeddingModel** - 텍스트를 수치 벡터로 변환합니다.

**EmbeddingStore** - 임베딩을 저장하고 검색합니다.

**MessageWindowChatMemory** - 최근 메시지의 슬라이딩 윈도를 유지합니다.

**PromptTemplate** - `{{variable}}` 자리표시자를 사용하는 재사용 가능한 프롬프트를 생성합니다.

**TextSegment** - 메타데이터가 포함된 텍스트 조각입니다. RAG에서 사용됩니다.

**ToolExecutionRequest** - 도구 실행 요청을 나타냅니다.

**UserMessage / AiMessage / SystemMessage** - 대화 메시지 유형입니다.

## AI/ML Concepts

**Few-Shot Learning** - 프롬프트에 예시를 제공하는 것. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - 방대한 텍스트 데이터를 학습한 AI 모델입니다.

**Reasoning Effort** - 사고의 깊이를 제어하는 GPT-5 매개변수입니다. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - 출력의 무작위성을 제어합니다. 낮음=결정적, 높음=창의적.

**Vector Database** - 임베딩을 위한 특화된 데이터베이스입니다. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - 예시 없이 작업을 수행하는 것. [Module 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - 더 나은 정확도를 위한 단계별 추론입니다.

**Constrained Output** - 특정 형식이나 구조를 강제하는 것.

**High Eagerness** - 철저한 추론을 위한 GPT-5 패턴입니다.

**Low Eagerness** - 빠른 답변을 위한 GPT-5 패턴입니다.

**Multi-Turn Conversation** - 교환 간에 컨텍스트를 유지하는 것.

**Role-Based Prompting** - 시스템 메시지를 통해 모델 페르소나를 설정하는 것.

**Self-Reflection** - 모델이 자신의 출력을 평가하고 개선하는 것.

**Structured Analysis** - 고정된 평가 프레임워크입니다.

**Task Execution Pattern** - 계획 → 실행 → 요약.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - 로드 → 청크화 → 임베딩 → 저장.

**In-Memory Embedding Store** - 테스트를 위한 비영구적 스토리지입니다.

**RAG** - 검색과 생성을 결합하여 응답을 근거에 기반하게 합니다.

**Similarity Score** - 의미적 유사성의 척도(0-1).

**Source Reference** - 검색된 콘텐츠에 대한 메타데이터입니다.

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - Java 메서드를 AI가 호출할 수 있는 도구로 표시합니다.

**ReAct Pattern** - 추론 → 행동 → 관찰 → 반복.

**Session Management** - 서로 다른 사용자를 위한 분리된 컨텍스트 관리입니다.

**Tool** - AI 에이전트가 호출할 수 있는 함수입니다.

**Tool Description** - 도구의 목적과 매개변수에 대한 문서입니다.

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - AI 앱을 외부 도구에 연결하기 위한 표준입니다.

**MCP Client** - MCP 서버에 연결하는 애플리케이션입니다.

**MCP Server** - 도구를 MCP를 통해 노출하는 서비스입니다.

**Stdio Transport** - stdin/stdout을 통해 서브프로세스로서 동작하는 서버입니다.

**Tool Discovery** - 사용 가능한 도구를 위해 클라이언트가 서버에 질의하는 것.

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - 벡터 기능을 갖춘 클라우드 검색입니다. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azure 리소스를 배포합니다.

**Azure OpenAI** - Microsoft의 엔터프라이즈 AI 서비스입니다.

**Bicep** - Azure 인프라스트럭처 코드 언어입니다. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azure에서 모델 배포 이름입니다.

**GPT-5** - 추론 제어 기능을 갖춘 최신 OpenAI 모델입니다. [Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - 컨테이너화된 개발 환경입니다. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 무료 AI 모델 실험 공간입니다. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - 인메모리 스토리지로 하는 테스트입니다.

**Integration Testing** - 실제 인프라와 함께하는 테스트입니다.

**Maven** - Java 빌드 자동화 도구입니다.

**Mockito** - Java 목킹 프레임워크입니다.

**Spring Boot** - Java 애플리케이션 프레임워크입니다. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**면책사항**:
이 문서는 AI 번역 서비스 [Co-op Translator](https://github.com/Azure/co-op-translator)를 사용하여 번역되었습니다. 정확성을 위해 노력하고 있으나 자동 번역은 오류나 부정확성을 포함할 수 있습니다. 원문(원어로 작성된 문서)을 권위 있는 출처로 간주해야 합니다. 중요한 정보의 경우 전문적인 인간 번역을 권장합니다. 이 번역의 사용으로 인해 발생한 오해 또는 잘못된 해석에 대해서는 당사가 책임을 지지 않습니다.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->