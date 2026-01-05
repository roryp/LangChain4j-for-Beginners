<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "d61ab6c245562094cc3dddecf08b50d3",
  "translation_date": "2025-12-30T22:09:29+00:00",
  "source_file": "README.md",
  "language_code": "ko"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b50.ko.png" alt="LangChain4j" width="800"/>

### 🌐 다국어 지원

#### GitHub Action으로 지원 (자동화 및 항상 최신)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[아랍어](../ar/README.md) | [벵골어](../bn/README.md) | [불가리아어](../bg/README.md) | [버마어(미얀마)](../my/README.md) | [중국어(간체)](../zh/README.md) | [중국어(번체, 홍콩)](../hk/README.md) | [중국어(번체, 마카오)](../mo/README.md) | [중국어(번체, 대만)](../tw/README.md) | [크로아티아어](../hr/README.md) | [체코어](../cs/README.md) | [덴마크어](../da/README.md) | [네덜란드어](../nl/README.md) | [에스토니아어](../et/README.md) | [핀란드어](../fi/README.md) | [프랑스어](../fr/README.md) | [독일어](../de/README.md) | [그리스어](../el/README.md) | [히브리어](../he/README.md) | [힌디어](../hi/README.md) | [헝가리어](../hu/README.md) | [인도네시아어](../id/README.md) | [이탈리아어](../it/README.md) | [일본어](../ja/README.md) | [칸나다어](../kn/README.md) | [한국어](./README.md) | [리투아니아어](../lt/README.md) | [말레이어](../ms/README.md) | [말라얄람어](../ml/README.md) | [마라티어](../mr/README.md) | [네팔어](../ne/README.md) | [나이지리아 피진](../pcm/README.md) | [노르웨이어](../no/README.md) | [페르시아어(파르시)](../fa/README.md) | [폴란드어](../pl/README.md) | [포르투갈어(브라질)](../br/README.md) | [포르투갈어(포르투갈)](../pt/README.md) | [펀자브어(구르무키)](../pa/README.md) | [루마니아어](../ro/README.md) | [러시아어](../ru/README.md) | [세르비아어(키릴)](../sr/README.md) | [슬로바키아어](../sk/README.md) | [슬로베니아어](../sl/README.md) | [스페인어](../es/README.md) | [스와힐리어](../sw/README.md) | [스웨덴어](../sv/README.md) | [타갈로그어(필리핀)](../tl/README.md) | [타밀어](../ta/README.md) | [텔루구어](../te/README.md) | [태국어](../th/README.md) | [터키어](../tr/README.md) | [우크라이나어](../uk/README.md) | [우르두어](../ur/README.md) | [베트남어](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# 초보자를 위한 LangChain4j

LangChain4j 및 Azure OpenAI GPT-5로 챗 기반부터 AI 에이전트까지 AI 애플리케이션을 구축하는 과정입니다.

**LangChain4j가 처음이신가요?** 주요 용어와 개념 정의는 [Glossary](docs/GLOSSARY.md)를 확인하세요.

## 목차

1. [빠른 시작](00-quick-start/README.md) - LangChain4j 시작하기
2. [소개](01-introduction/README.md) - LangChain4j의 기본 개념 배우기
3. [프롬프트 엔지니어링](02-prompt-engineering/README.md) - 효과적인 프롬프트 설계 마스터하기
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - 지능형 지식 기반 시스템 구축하기
5. [도구](04-tools/README.md) - 외부 도구 통합 및 간단한 어시스턴트 만들기
6. [MCP (Model Context Protocol)](05-mcp/README.md) - 모델 컨텍스트 프로토콜(MCP) 및 에이전트 모듈 작업하기
---

##  학습 경로

> **빠른 시작**

1. 이 리포지토리를 GitHub 계정에 포크하세요
2. **Code** → **Codespaces** 탭 → **...** → **New with options...**을 클릭하세요
3. 기본값을 사용하세요 – 그러면 이 과정용으로 생성된 Development 컨테이너가 선택됩니다
4. **Create codespace**를 클릭하세요
5. 환경이 준비될 때까지 5~10분 기다리세요
6. 바로 [빠른 시작](./00-quick-start/README.md)으로 이동하여 시작하세요!

> **로컬로 클론하시겠습니까?**
>
> 이 리포지토리에는 50개 이상의 언어 번역이 포함되어 있어 다운로드 크기가 크게 증가합니다. 번역 없이 클론하려면 sparse checkout을 사용하세요:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> 이렇게 하면 훨씬 빠른 다운로드로 이 과정을 완료하는 데 필요한 모든 것을 얻을 수 있습니다.

모듈을 완료한 후에는 LangChain4j 테스트 개념을 실전으로 확인하려면 [테스트 가이드](docs/TESTING.md)를 살펴보세요.

> **참고:** 이 교육은 GitHub Models와 Azure OpenAI를 모두 사용합니다. [빠른 시작](00-quick-start/README.md) 및 [MCP](05-mcp/README.md) 모듈은 GitHub Models를 사용합니다(구독 필요 없음), 반면 모듈 1-4는 Azure OpenAI GPT-5를 사용합니다.


## GitHub Copilot로 학습하기

빠르게 코딩을 시작하려면 이 프로젝트를 GitHub Codespace에서 열거나 제공된 devcontainer로 로컬 IDE에서 여세요. 이 과정에 사용된 devcontainer는 AI 페어 프로그래밍용 GitHub Copilot이 미리 구성되어 있습니다.

각 코드 예제에는 GitHub Copilot에게 물어볼 수 있는 권장 질문이 포함되어 있어 이해를 심화할 수 있습니다. 다음에서 💡/🤖 프롬프트를 찾으세요:

- **Java 파일 헤더** - 각 예제에 대한 구체적인 질문
- **모듈 README** - 코드 예제 뒤의 탐색용 프롬프트

**사용 방법:** 임의의 코드 파일을 열고 Copilot에게 권장 질문을 해보세요. Copilot은 코드베이스의 전체 문맥을 알고 있으며 설명, 확장 및 대안을 제시할 수 있습니다.

더 알아보고 싶으신가요? [Copilot for AI Paired Programming](https://aka.ms/GitHubCopilotAI)를 확인하세요.


## 추가 자료

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j 초보자용](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js 초보자용](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / 에이전트
[![AZD 초보자용](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI 초보자용](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP 초보자용](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI 에이전트 초보자용](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### 생성형 AI 시리즈
[![생성형 AI 초보자용](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![생성형 AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![생성형 AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![생성형 AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### 핵심 학습
[![ML 초보자용](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![데이터 과학 초보자용](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI 초보자용](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![사이버보안 초보자용](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![웹 개발 초보자용](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT 초보자용](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR 개발 초보자용](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot 시리즈
[![AI 페어 프로그래밍을 위한 Copilot](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![C#/.NET용 Copilot](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot 어드벤처](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## 도움 받기

AI 앱을 구축하는 동안 막히거나 질문이 있으면 참여하세요:

[![Azure AI Foundry 디스코드](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

제품 피드백이나 빌드 중 오류가 있는 경우 방문하세요:

[![Azure AI Foundry 개발자 포럼](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## 라이선스

MIT License - See [LICENSE](../../LICENSE) file for details.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
면책 조항:
이 문서는 AI 번역 서비스 [Co-op Translator](https://github.com/Azure/co-op-translator)를 사용하여 번역되었습니다. 정확성을 위해 노력하고 있지만 자동 번역에는 오류나 부정확성이 포함될 수 있음을 알려드립니다. 원문(원본)은 해당 언어의 권위 있는 자료로 간주되어야 합니다. 중요한 정보의 경우 전문적인 인간 번역을 권장합니다. 본 번역의 사용으로 인해 발생하는 오해나 오해로 인한 결과에 대해 당사는 책임을 지지 않습니다.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->