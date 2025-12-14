<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "69c7e2616c66df6cc296492fbfcad9ec",
  "translation_date": "2025-12-13T12:20:19+00:00",
  "source_file": "README.md",
  "language_code": "ja"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b506e9588f734989dd106ebd9f977b7f784941a28b88348f0d6.ja.png" alt="LangChain4j" width="800"/>

# LangChain4j 入門者向け

LangChain4j と Azure OpenAI GPT-5 を使った AI アプリケーション構築のコース。基本的なチャットから AI エージェントまで。

**LangChain4j は初めてですか？** 重要な用語や概念の定義については [用語集](docs/GLOSSARY.md) をご覧ください。

## 目次

1. [クイックスタート](00-quick-start/README.md) - LangChain4j の始め方
2. [イントロダクション](01-introduction/README.md) - LangChain4j の基本を学ぶ
3. [プロンプトエンジニアリング](02-prompt-engineering/README.md) - 効果的なプロンプト設計を習得
4. [RAG（検索強化生成）](03-rag/README.md) - インテリジェントな知識ベースシステムを構築
5. [ツール](04-tools/README.md) - AI エージェントと外部ツールや API を統合
6. [MCP（モデルコンテキストプロトコル）](05-mcp/README.md) - モデルコンテキストプロトコルを扱う
---

## 学習パス

[クイックスタート](00-quick-start/README.md) モジュールから始めて、各モジュールを順に進めてスキルを段階的に構築しましょう。基本的な例を試して基礎を理解した後、GPT-5 を使ったより深い内容の [イントロダクション](01-introduction/README.md) モジュールに進みます。

<img src="../../translated_images/learning-path.ac2da6720e77c3165960835627cef4c20eb2afb103be73a4f25b6d8fafbd738d.ja.png" alt="Learning Path" width="800"/>

モジュールを修了したら、[テストガイド](docs/TESTING.md) を参照して LangChain4j のテスト概念を実際に体験してください。

> **注意:** このトレーニングでは GitHub モデルと Azure OpenAI の両方を使用します。[クイックスタート](00-quick-start/README.md) と [MCP](05-mcp/README.md) モジュールは GitHub モデルを使用（Azure サブスクリプション不要）、モジュール 1～4 は Azure OpenAI GPT-5 を使用します。


## GitHub Copilot で学ぶ

すぐにコーディングを始めるには、このプロジェクトを GitHub Codespace または提供された devcontainer を使ってローカル IDE で開いてください。このコースで使う devcontainer は AI ペアプログラミング用の GitHub Copilot が事前設定されています。

各コード例には、GitHub Copilot に質問して理解を深めるための推奨質問が含まれています。💡/🤖 のプロンプトを探してください：

- **Java ファイルのヘッダー** - 各例に特化した質問
- **モジュールの README** - コード例の後の探求用プロンプト

**使い方:** 任意のコードファイルを開き、推奨質問を Copilot に尋ねてください。コードベースの全体コンテキストを把握しており、説明、拡張、代替案の提案が可能です。

もっと知りたいですか？[AI ペアプログラミング用 Copilot](https://aka.ms/GitHubCopilotAI) をご覧ください。


## 追加リソース

### LangChain
[![LangChain4j for Beginners](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js for Beginners](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / エージェント
[![AZD for Beginners](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI for Beginners](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP for Beginners](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Agents for Beginners](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### 生成AIシリーズ
[![Generative AI for Beginners](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generative AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generative AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generative AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### コア学習
[![ML for Beginners](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science for Beginners](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI for Beginners](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Cybersecurity for Beginners](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev for Beginners](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT for Beginners](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development for Beginners](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot シリーズ
[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)

## ヘルプを得るには

AI アプリ構築で行き詰まったり質問がある場合は、以下に参加してください：

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

製品のフィードバックや構築中のエラーについては、こちらへ：

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## ライセンス

MIT ライセンス - 詳細は [LICENSE](../../LICENSE) ファイルをご覧ください。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責事項**：  
本書類はAI翻訳サービス「Co-op Translator」（https://github.com/Azure/co-op-translator）を使用して翻訳されました。正確性の向上に努めておりますが、自動翻訳には誤りや不正確な部分が含まれる可能性があります。原文の言語によるオリジナル文書が正式な情報源とみなされるべきです。重要な情報については、専門の人間による翻訳を推奨します。本翻訳の利用により生じた誤解や誤訳について、当方は一切の責任を負いかねます。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->