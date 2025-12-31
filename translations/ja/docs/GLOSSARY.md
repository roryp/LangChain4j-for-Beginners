<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T22:03:17+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ja"
}
-->
# LangChain4j 用語集

## 目次

- [コア概念](../../../docs)
- [LangChain4j コンポーネント](../../../docs)
- [AI/ML の概念](../../../docs)
- [プロンプトエンジニアリング - [モジュール 02](../02-prompt-engineering/README.md)](#prompt-engineering---module-02)
- [RAG（検索強化生成）](../../../docs)
- [エージェントとツール](../../../docs)
- [Model Context Protocol (MCP) - [モジュール 05](../05-mcp/README.md)](#model-context-protocol-mcp---module-05)
- [Azure サービス - [モジュール 01](../01-introduction/README.md)](#azure-services---module-01)
- [テストと開発 - [テストガイド](TESTING.md)](#testing-and-development---testing-guide)

コース全体で使用される用語と概念のクイックリファレンス。

## コア概念

**AI Agent** - AIを使って推論し自律的に行動するシステム。 [モジュール 04](../04-tools/README.md)

**Chain** - 出力が次のステップに渡される一連の操作の流れ。

**Chunking** - ドキュメントをより小さな断片に分割すること。一般的には重複ありで300〜500トークン。 [モジュール 03](../03-rag/README.md)

**Context Window** - モデルが処理できる最大トークン数。GPT-5: 400K トークン。

**Embeddings** - テキストの意味を表す数値ベクトル。 [モジュール 03](../03-rag/README.md)

**Function Calling** - モデルが外部関数を呼び出すための構造化されたリクエストを生成すること。 [モジュール 04](../04-tools/README.md)

**Hallucination** - モデルが誤っているがもっともらしい情報を生成する現象。

**Prompt** - 言語モデルへのテキスト入力。 [モジュール 02](../02-prompt-engineering/README.md)

**Semantic Search** - キーワードではなく意味を使って検索する方法（埋め込みを使用）。 [モジュール 03](../03-rag/README.md)

**Stateful vs Stateless** - ステートレス: メモリなし。ステートフル: 会話履歴を保持。 [モジュール 01](../01-introduction/README.md)

**Tokens** - モデルが処理する基本的なテキスト単位。コストや制限に影響する。 [モジュール 01](../01-introduction/README.md)

**Tool Chaining** - 出力が次の呼び出しに情報を提供する順次のツール実行。 [モジュール 04](../04-tools/README.md)

## LangChain4j コンポーネント

**AiServices** - 型安全なAIサービスインターフェースを作成します。

**OpenAiOfficialChatModel** - OpenAI と Azure OpenAI のモデル向けの統合クライアント。

**OpenAiOfficialEmbeddingModel** - OpenAI Official クライアントを使って埋め込みを作成します（OpenAI と Azure OpenAI の両方をサポート）。

**ChatModel** - 言語モデルのコアインターフェース。

**ChatMemory** - 会話履歴を保持します。

**ContentRetriever** - RAG のために関連するドキュメントチャンクを検索します。

**DocumentSplitter** - ドキュメントをチャンクに分割します。

**EmbeddingModel** - テキストを数値ベクトルに変換します。

**EmbeddingStore** - 埋め込みを保存および取得します。

**MessageWindowChatMemory** - 最近のメッセージのスライディングウィンドウを保持します。

**PromptTemplate** - `{{variable}}` プレースホルダーを使った再利用可能なプロンプトを作成します。

**TextSegment** - メタデータを持つテキストチャンク。RAGで使用されます。

**ToolExecutionRequest** - ツール実行リクエストを表します。

**UserMessage / AiMessage / SystemMessage** - 会話メッセージの種類。

## AI/ML の概念

**Few-Shot Learning** - プロンプト内で例を提示する学習法。 [モジュール 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - 膨大なテキストデータで訓練されたAIモデル。

**Reasoning Effort** - 思考の深さを制御するGPT-5のパラメータ。 [モジュール 02](../02-prompt-engineering/README.md)

**Temperature** - 出力のランダム性を制御します。低=決定的、 高=創造的。

**Vector Database** - 埋め込み向けに特化したデータベース。 [モジュール 03](../03-rag/README.md)

**Zero-Shot Learning** - 例を使わずにタスクを実行すること。 [モジュール 02](../02-prompt-engineering/README.md)

## プロンプトエンジニアリング - [モジュール 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - より高い精度のための段階的な推論。

**Constrained Output** - 特定のフォーマットや構造を強制すること。

**High Eagerness** - 徹底的な推論を行うGPT-5のパターン。

**Low Eagerness** - 迅速な回答を行うGPT-5のパターン。

**Multi-Turn Conversation** - 交互のやり取りでコンテキストを維持すること。

**Role-Based Prompting** - システムメッセージを通じてモデルのペルソナを設定すること。

**Self-Reflection** - モデルが自らの出力を評価して改善すること。

**Structured Analysis** - 固定された評価フレームワーク。

**Task Execution Pattern** - 計画 → 実行 → 要約。

## RAG (Retrieval-Augmented Generation) - [モジュール 03](../03-rag/README.md)

**Document Processing Pipeline** - 読み込み → チャンク化 → 埋め込み → 保存。

**In-Memory Embedding Store** - テスト用の非永続的ストレージ。

**RAG** - 生成を検索で裏付けることで応答を根拠づける手法。

**Similarity Score** - セマンティック類似度の指標（0〜1）。

**Source Reference** - 取得したコンテンツに関するメタデータ。

## エージェントとツール - [モジュール 04](../04-tools/README.md)

**@Tool Annotation** - Java メソッドをAIから呼び出せるツールとしてマークします。

**ReAct Pattern** - 推論 → 行動 → 観察 → 繰り返し。

**Session Management** - 異なるユーザー向けにコンテキストを分離します。

**Tool** - AIエージェントが呼び出せる関数。

**Tool Description** - ツールの目的とパラメータのドキュメント。

## Model Context Protocol (MCP) - [モジュール 05](../05-mcp/README.md)

**MCP** - AIアプリを外部ツールに接続するための標準。

**MCP Client** - MCPサーバーに接続するアプリケーション。

**MCP Server** - MCP経由でツールを公開するサービス。

**Stdio Transport** - stdin/stdout経由のサブプロセスとしてのサーバ。

**Tool Discovery** - クライアントが利用可能なツールをサーバに問い合わせます。

## Azure サービス - [モジュール 01](../01-introduction/README.md)

**Azure AI Search** - ベクター機能を備えたクラウド検索。 [モジュール 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azureリソースをデプロイするツール。

**Azure OpenAI** - Microsoftのエンタープライズ向けAIサービス。

**Bicep** - Azureのインフラをコード化する言語。 [インフラガイド](../01-introduction/infra/README.md)

**Deployment Name** - Azureでのモデルデプロイメントの名前。

**GPT-5** - 推論制御を備えた最新のOpenAIモデル。 [モジュール 02](../02-prompt-engineering/README.md)

## テストと開発 - [テストガイド](TESTING.md)

**Dev Container** - コンテナ化された開発環境。 [構成](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 無料のAIモデルプレイグラウンド。 [モジュール 00](../00-quick-start/README.md)

**In-Memory Testing** - インメモリストレージを使ったテスト。

**Integration Testing** - 実際のインフラを使った統合テスト。

**Maven** - Javaのビルド自動化ツール。

**Mockito** - Javaのモッキングフレームワーク。

**Spring Boot** - Javaアプリケーションフレームワーク。 [モジュール 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免責事項：
この文書は AI 翻訳サービス「Co-op Translator」（https://github.com/Azure/co-op-translator）を使用して翻訳されました。正確性には注意を払っていますが、自動翻訳には誤りや不正確な表現が含まれる可能性があります。原文（母語で作成された文書）を正本と見なしてください。重要な情報については、専門の人間による翻訳を推奨します。本翻訳の利用により生じたいかなる誤解や誤訳についても責任を負いません。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->