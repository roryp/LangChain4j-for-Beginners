<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T19:57:02+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ja"
}
-->
# LangChain4j 用語集

## 目次

- [コアコンセプト](../../../docs)
- [LangChain4j コンポーネント](../../../docs)
- [AI/ML コンセプト](../../../docs)
- [プロンプトエンジニアリング](../../../docs)
- [RAG（検索強化生成）](../../../docs)
- [エージェントとツール](../../../docs)
- [モデルコンテキストプロトコル（MCP）](../../../docs)
- [Azure サービス](../../../docs)
- [テストと開発](../../../docs)

コース全体で使用される用語と概念のクイックリファレンス。

## コアコンセプト

**AIエージェント** - AIを使って自律的に推論し行動するシステム。[モジュール 04](../04-tools/README.md)

**チェーン** - 出力が次のステップに入力される一連の操作。

**チャンク化** - ドキュメントを小さな部分に分割すること。典型的には300～500トークンで重複あり。[モジュール 03](../03-rag/README.md)

**コンテキストウィンドウ** - モデルが処理可能な最大トークン数。GPT-5: 40万トークン。

**埋め込み（Embeddings）** - テキストの意味を表す数値ベクトル。[モジュール 03](../03-rag/README.md)

**関数呼び出し** - モデルが外部関数を呼び出すための構造化リクエストを生成すること。[モジュール 04](../04-tools/README.md)

**幻覚（Hallucination）** - モデルが誤ったがもっともらしい情報を生成すること。

**プロンプト** - 言語モデルへのテキスト入力。[モジュール 02](../02-prompt-engineering/README.md)

**意味検索（Semantic Search）** - キーワードではなく埋め込みを使った意味による検索。[モジュール 03](../03-rag/README.md)

**ステートフル vs ステートレス** - ステートレス：メモリなし。ステートフル：会話履歴を保持。[モジュール 01](../01-introduction/README.md)

**トークン** - モデルが処理する基本的なテキスト単位。コストや制限に影響。[モジュール 01](../01-introduction/README.md)

**ツールチェイニング** - 出力が次の呼び出しに影響する連続的なツール実行。[モジュール 04](../04-tools/README.md)

## LangChain4j コンポーネント

**AiServices** - 型安全なAIサービスインターフェースを作成。

**OpenAiOfficialChatModel** - OpenAIおよびAzure OpenAIモデルの統一クライアント。

**OpenAiOfficialEmbeddingModel** - OpenAI Officialクライアントを使って埋め込みを作成（OpenAIとAzure OpenAI両対応）。

**ChatModel** - 言語モデルのコアインターフェース。

**ChatMemory** - 会話履歴を保持。

**ContentRetriever** - RAG用に関連するドキュメントチャンクを検索。

**DocumentSplitter** - ドキュメントをチャンクに分割。

**EmbeddingModel** - テキストを数値ベクトルに変換。

**EmbeddingStore** - 埋め込みの保存と取得。

**MessageWindowChatMemory** - 最近のメッセージのスライディングウィンドウを保持。

**PromptTemplate** - `{{variable}}` プレースホルダーを使った再利用可能なプロンプトを作成。

**TextSegment** - メタデータ付きテキストチャンク。RAGで使用。

**ToolExecutionRequest** - ツール実行リクエストを表す。

**UserMessage / AiMessage / SystemMessage** - 会話メッセージの種類。

## AI/ML コンセプト

**Few-Shot Learning** - プロンプトに例を提供する学習方法。[モジュール 02](../02-prompt-engineering/README.md)

**大規模言語モデル（LLM）** - 大量のテキストデータで訓練されたAIモデル。

**推論努力（Reasoning Effort）** - GPT-5の思考の深さを制御するパラメータ。[モジュール 02](../02-prompt-engineering/README.md)

**温度（Temperature）** - 出力のランダム性を制御。低いほど決定的、高いほど創造的。

**ベクトルデータベース** - 埋め込み用の特殊なデータベース。[モジュール 03](../03-rag/README.md)

**ゼロショット学習（Zero-Shot Learning）** - 例なしでタスクを実行すること。[モジュール 02](../02-prompt-engineering/README.md)

## プロンプトエンジニアリング - [モジュール 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - 精度向上のための段階的推論。

**制約付き出力** - 特定の形式や構造を強制。

**高い熱意（High Eagerness）** - GPT-5の徹底的な推論パターン。

**低い熱意（Low Eagerness）** - GPT-5の迅速な回答パターン。

**マルチターン会話** - 複数回のやり取りでコンテキストを維持。

**役割ベースのプロンプト** - システムメッセージでモデルのペルソナを設定。

**自己反省** - モデルが自身の出力を評価し改善。

**構造化分析** - 固定された評価フレームワーク。

**タスク実行パターン** - 計画 → 実行 → 要約。

## RAG（検索強化生成） - [モジュール 03](../03-rag/README.md)

**ドキュメント処理パイプライン** - ロード → チャンク化 → 埋め込み → 保存。

**インメモリ埋め込みストア** - テスト用の非永続的ストレージ。

**RAG** - 検索と生成を組み合わせて応答を根拠づける。

**類似度スコア** - 意味的類似度の尺度（0～1）。

**ソース参照** - 取得したコンテンツのメタデータ。

## エージェントとツール - [モジュール 04](../04-tools/README.md)

**@Tool アノテーション** - JavaメソッドをAI呼び出し可能なツールとしてマーク。

**ReAct パターン** - 推論 → 行動 → 観察 → 繰り返し。

**セッション管理** - ユーザーごとに異なるコンテキストを分離。

**ツール** - AIエージェントが呼び出せる関数。

**ツール説明** - ツールの目的とパラメータのドキュメント。

## モデルコンテキストプロトコル（MCP） - [モジュール 05](../05-mcp/README.md)

**Docker トランスポート** - Dockerコンテナ内のMCPサーバー。

**MCP** - AIアプリと外部ツールを接続する標準。

**MCP クライアント** - MCPサーバーに接続するアプリケーション。

**MCP サーバー** - MCP経由でツールを公開するサービス。

**サーバー送信イベント（SSE）** - HTTP経由のサーバーからクライアントへのストリーミング。

**Stdio トランスポート** - stdin/stdoutを使ったサブプロセスとしてのサーバー。

**ストリーム可能HTTPトランスポート** - SSEを使ったリアルタイム通信のHTTP。

**ツール検出** - クライアントが利用可能なツールをサーバーに問い合わせ。

## Azure サービス - [モジュール 01](../01-introduction/README.md)

**Azure AI Search** - ベクトル機能付きクラウド検索。[モジュール 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Azureリソースのデプロイ。

**Azure OpenAI** - マイクロソフトのエンタープライズAIサービス。

**Bicep** - Azureのインフラコード言語。[インフラガイド](../01-introduction/infra/README.md)

**デプロイメント名** - Azureでのモデルデプロイ名。

**GPT-5** - 推論制御機能を持つ最新のOpenAIモデル。[モジュール 02](../02-prompt-engineering/README.md)

## テストと開発 - [テストガイド](TESTING.md)

**Dev Container** - コンテナ化された開発環境。[設定](../../../.devcontainer/devcontainer.json)

**GitHub Models** - 無料のAIモデルプレイグラウンド。[モジュール 00](../00-quick-start/README.md)

**インメモリテスト** - インメモリストレージを使ったテスト。

**統合テスト** - 実際のインフラを使ったテスト。

**Maven** - Javaのビルド自動化ツール。

**Mockito** - Javaのモッキングフレームワーク。

**Spring Boot** - Javaアプリケーションフレームワーク。[モジュール 01](../01-introduction/README.md)

**Testcontainers** - テスト内でのDockerコンテナ。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責事項**：  
本書類はAI翻訳サービス「Co-op Translator」（https://github.com/Azure/co-op-translator）を使用して翻訳されました。正確性を期しておりますが、自動翻訳には誤りや不正確な部分が含まれる可能性があります。原文の言語によるオリジナル文書が正式な情報源とみなされるべきです。重要な情報については、専門の人間による翻訳を推奨します。本翻訳の利用により生じた誤解や誤訳について、当方は一切の責任を負いかねます。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->