<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T07:53:59+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ja"
}
-->
# LangChain4j 用語集

## 目次

- [コアコンセプト](../../../docs)
- [LangChain4j コンポーネント](../../../docs)
- [AI/ML コンセプト](../../../docs)
- [ガードレール](../../../docs)
- [プロンプトエンジニアリング](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [エージェントとツール](../../../docs)
- [エージェンティックモジュール](../../../docs)
- [モデルコンテキストプロトコル (MCP)](../../../docs)
- [Azure サービス](../../../docs)
- [テストと開発](../../../docs)

コース全体で使用される用語と概念のクイックリファレンス。

## Core Concepts

**AI Agent** - AIを利用して自律的に推論し行動するシステム。[Module 04](../04-tools/README.md)

**Chain** - 出力が次のステップに入力される一連の処理。

**Chunking** - ドキュメントを小さな断片に分割すること。一般的に300～500トークンで重複あり。[Module 03](../03-rag/README.md)

**Context Window** - モデルが処理できる最大トークン数。GPT-5: 40万トークン。

**Embeddings** - テキストの意味を表す数値ベクトル。[Module 03](../03-rag/README.md)

**Function Calling** - モデルが外部関数を呼び出す構造化されたリクエストを生成する。[Module 04](../04-tools/README.md)

**Hallucination** - モデルが誤っているがもっともらしい情報を生成すること。

**Prompt** - 言語モデルへのテキスト入力。[Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - キーワードではなく意味による検索。Embeddingsを利用。[Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: メモリなし。Stateful: 会話履歴を保持。[Module 01](../01-introduction/README.md)

**Tokens** - モデルが処理する基本的なテキスト単位。コストや制限に影響。[Module 01](../01-introduction/README.md)

**Tool Chaining** - 複数ツールを順次実行し、出力を次の呼び出しに活用。[Module 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - 型安全なAIサービスインターフェースを作成。

**OpenAiOfficialChatModel** - OpenAIおよびAzure OpenAIモデルの統一クライアント。

**OpenAiOfficialEmbeddingModel** - OpenAI Officialクライアントを使い埋め込みを作成（OpenAIとAzure OpenAI両対応）。

**ChatModel** - 言語モデルのコアインターフェース。

**ChatMemory** - 会話履歴を保持。

**ContentRetriever** - RAG用に関連するドキュメントチャンクを検索。

**DocumentSplitter** - ドキュメントをチャンクに分割。

**EmbeddingModel** - テキストを数値ベクトルに変換。

**EmbeddingStore** - 埋め込みを保存および取得。

**MessageWindowChatMemory** - 最近のメッセージのスライディングウィンドウを保持。

**PromptTemplate** - `{{variable}}` プレースホルダーを使った再利用可能なプロンプト作成。

**TextSegment** - メタデータ付きテキストチャンク。RAGで使用。

**ToolExecutionRequest** - ツール実行リクエストを表現。

**UserMessage / AiMessage / SystemMessage** - 会話のメッセージタイプ。

## AI/ML Concepts

**Few-Shot Learning** - プロンプト内に例を示す学習。[Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - 大量のテキストデータで訓練されたAIモデル。

**Reasoning Effort** - GPT-5の思考深度制御パラメータ。[Module 02](../02-prompt-engineering/README.md)

**Temperature** - 出力のランダム性を制御。低いほど決定論的、高いほど創造的。

**Vector Database** - 埋め込みベクトル用の専門データベース。[Module 03](../03-rag/README.md)

**Zero-Shot Learning** - 例なしでタスクを実行する学習。[Module 02](../02-prompt-engineering/README.md)

## Guardrails - [Module 00](../00-quick-start/README.md)

**Defense in Depth** - アプリケーションレベルのガードレールとプロバイダーの安全フィルターを組み合わせた多層セキュリティ。

**Hard Block** - 重大な内容違反に対し、プロバイダーがHTTP 400エラーを返す。

**InputGuardrail** - ユーザー入力がLLMに届く前に検証するLangChain4jインターフェース。害悪なプロンプトを早期にブロックしてコストと遅延を削減。

**InputGuardrailResult** - ガードレール検証の戻り値タイプ：`success()` または `fatal("理由")`。

**OutputGuardrail** - AIの応答をユーザーに返す前に検証するインターフェース。

**Provider Safety Filters** - AIプロバイダー（例：GitHub Models）由来のAPIレベルの内蔵コンテンツフィルター。

**Soft Refusal** - エラーを返さず、丁寧に回答を拒否するモデルの応答。

## Prompt Engineering - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - 精度向上のための段階的推論。

**Constrained Output** - 特定のフォーマットや構造の強制。

**High Eagerness** - 徹底的な推論のためのGPT-5パターン。

**Low Eagerness** - 迅速な回答のためのGPT-5パターン。

**Multi-Turn Conversation** - 複数回のやりとりでコンテキストを維持。

**Role-Based Prompting** - システムメッセージを使いモデルのペルソナを設定。

**Self-Reflection** - モデルが自己評価し出力を改善。

**Structured Analysis** - 固定評価フレームワーク。

**Task Execution Pattern** - 計画 → 実行 → 要約。

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - 読み込み → チャンク分割 → 埋め込み → 保存。

**In-Memory Embedding Store** - テスト用の非永続的ストレージ。

**RAG** - 検索と生成を組み合わせて根拠のある応答を生成。

**Similarity Score** - 意味的な類似度の尺度（0-1）。

**Source Reference** - 取得したコンテンツのメタデータ。

## Agents and Tools - [Module 04](../04-tools/README.md)

**@Tool Annotation** - JavaのメソッドをAI呼び出し可能なツールとしてマーク。

**ReAct Pattern** - 推論 → 行動 → 観察 → 繰り返し。

**Session Management** - ユーザーごとに別々のコンテキストを管理。

**Tool** - AIエージェントが呼び出せる関数。

**Tool Description** - ツールの目的とパラメーターのドキュメント。

## Agentic Module - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - インターフェースをAIエージェントとしてマークし宣言的な振る舞い定義。

**Agent Listener** - `beforeAgentInvocation()` と `afterAgentInvocation()` によるエージェント実行監視用フック。

**Agentic Scope** - 複数のエージェントが `outputKey` を使って出力を保存し下流のエージェントが利用する共有メモリ。

**AgenticServices** - `agentBuilder()` と `supervisorBuilder()` を使ったエージェント作成ファクトリー。

**Conditional Workflow** - 条件に応じて異なる専門エージェントへルーティング。

**Human-in-the-Loop** - 承認または内容確認のための人間のチェックポイントを追加するワークフロー。

**langchain4j-agentic** - 宣言的エージェント構築用のMaven依存関係（実験的）。

**Loop Workflow** - 条件が満たされるまでエージェント実行を繰り返す（例：品質スコア≥0.8）。

**outputKey** - エージェントの注釈パラメーターで、結果をAgentic Scopeのどこに保存するか指定。

**Parallel Workflow** - 独立した複数のタスクを同時にエージェント実行。

**Response Strategy** - 監督エージェントが最終回答を作る方法：LAST、SUMMARY、SCORED。

**Sequential Workflow** - 出力が次のステップに流れる順番実行。

**Supervisor Agent Pattern** - 監督役LLMが動的にサブエージェントを選択実行する高度なエージェンティックパターン。

## Model Context Protocol (MCP) - [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - LangChain4jでのMCP統合用Maven依存関係。

**MCP** - Model Context Protocol：AIアプリを外部ツールに接続する標準。1度構築してどこでも使う。

**MCP Client** - MCPサーバーに接続してツールを検索・利用するアプリケーション。

**MCP Server** - MCP経由でツールを公開し、明確な説明とパラメータスキーマを提供。

**McpToolProvider** - MCPツールをLangChain4jのAIサービスやエージェントで使えるようにラップするコンポーネント。

**McpTransport** - MCP通信のインターフェース。実装例にStdioとHTTP。

**Stdio Transport** - stdin/stdout経由のローカルプロセストランスポート。ファイルアクセスやコマンドラインツール利用時に便利。

**StdioMcpTransport** - LangChain4jの実装で、MCPサーバーをサブプロセスとして起動。

**Tool Discovery** - クライアントが説明とスキーマ付きで利用可能ツールをサーバーに問い合わせ。

## Azure Services - [Module 01](../01-introduction/README.md)

**Azure AI Search** - ベクトル機能付きクラウド検索。[Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azureリソースをデプロイ。

**Azure OpenAI** - マイクロソフトの企業向けAIサービス。

**Bicep** - Azureのインフラコード言語。[Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Azureでのモデルデプロイの名前。

**GPT-5** - 推論制御機能を持つ最新のOpenAIモデル。[Module 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - コンテナ化された開発環境。[Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 無料のAIモデルプレイグラウンド。[Module 00](../00-quick-start/README.md)

**In-Memory Testing** - メモリ内ストレージを使ったテスト。

**Integration Testing** - 実際のインフラを使った統合テスト。

**Maven** - Javaのビルド自動化ツール。

**Mockito** - Javaモッキングフレームワーク。

**Spring Boot** - Javaアプリケーションフレームワーク。[Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責事項**：  
本書類はAI翻訳サービス[Co-op Translator](https://github.com/Azure/co-op-translator)を使用して翻訳されました。正確性には努めておりますが、自動翻訳には誤りや不正確な箇所が含まれる可能性があることをご了承ください。原文のオリジナル文書が正式な情報源とみなされます。重要な情報については、専門の人間による翻訳を推奨します。本翻訳の利用によって生じたいかなる誤解や誤訳についても、当方は一切の責任を負いかねます。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->