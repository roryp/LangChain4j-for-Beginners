<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "6c816d130a1fa47570c11907e72d84ae",
  "translation_date": "2026-01-05T22:14:45+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "ja"
}
-->
# モジュール 05: モデルコンテキストプロトコル (MCP)

## 目次

- [学習内容](../../../05-mcp)
- [MCPとは？](../../../05-mcp)
- [MCPの仕組み](../../../05-mcp)
- [エージェンティックモジュール](../../../05-mcp)
- [例の実行](../../../05-mcp)
  - [前提条件](../../../05-mcp)
- [クイックスタート](../../../05-mcp)
  - [ファイル操作（Stdio）](../../../05-mcp)
  - [スーパーバイザーエージェント](../../../05-mcp)
    - [出力の理解](../../../05-mcp)
    - [応答戦略](../../../05-mcp)
    - [エージェンティックモジュールの機能説明](../../../05-mcp)
- [主要コンセプト](../../../05-mcp)
- [おめでとうございます！](../../../05-mcp)
  - [次に何をする？](../../../05-mcp)

## 学習内容

これまでに会話型AIを構築し、プロンプトをマスターし、文書を基に応答し、ツールを持つエージェントを作成してきました。しかしそれらのツールはすべてあなたの特定のアプリケーション用にカスタムビルドされたものでした。もし誰でも作成し共有できる標準化されたツールのエコシステムにAIがアクセスできるとしたらどうでしょう？本モジュールでは、Model Context Protocol (MCP) と LangChain4j のエージェンティックモジュールを使ってそれを実現する方法を学びます。まずシンプルなMCPファイルリーダーを紹介し、続いてSupervisor Agentパターンを使った高度なエージェンティックワークフローへの統合を示します。

## MCPとは？

Model Context Protocol (MCP) はまさにそうした標準化された方法をAIアプリケーションに提供します。各データソースやサービス用にカスタムの統合コードを書く代わりに、一貫した形式で機能を公開するMCPサーバーに接続します。あなたのAIエージェントはこのツール群を自動的に発見し利用できます。

<img src="../../../translated_images/ja/mcp-comparison.9129a881ecf10ff5.png" alt="MCP Comparison" width="800"/>

*MCP以前：複雑なポイントツーポイント連携。MCP以降：一つのプロトコルで無限の可能性。*

MCPはAI開発における根本的な問題を解決します：すべての統合がカスタムであること。GitHubにアクセスしたい？カスタムコード。ファイルを読みたい？カスタムコード。データベースに問合せたい？カスタムコード。そしてこれらの統合は他のAIアプリケーションと共有できません。

MCPはこれを標準化します。MCPサーバーは明確な説明とスキーマ付きでツールを公開し、任意のMCPクライアントが接続して利用可能なツールを発見し利用できるようになります。一度作ればどこでも使えるのです。

<img src="../../../translated_images/ja/mcp-architecture.b3156d787a4ceac9.png" alt="MCP Architecture" width="800"/>

*Model Context Protocolのアーキテクチャ - 標準化されたツール発見と実行*

## MCPの仕組み

**サーバークライアントアーキテクチャ**

MCPはクライアントサーバーモデルを使います。サーバーはファイル読み取り、データベースクエリ、API呼び出しなどツールを提供し、クライアント（あなたのAIアプリ）が接続してそのツールを使います。

LangChain4jでMCPを使うにはこのMaven依存を追加します：

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**ツール発見**

クライアントがMCPサーバーに接続すると「どんなツールがありますか？」と尋ねます。サーバーは利用できるツールを説明とパラメータスキーマ付きで返します。あなたのAIエージェントはユーザーの要求に基づいて使うツールを決定します。

**通信方式**

MCPは複数の通信方式をサポートします。本モジュールではローカルプロセス向けのStdio通信を例示します：

<img src="../../../translated_images/ja/transport-mechanisms.2791ba7ee93cf020.png" alt="Transport Mechanisms" width="800"/>

*MCP通信方式：リモートサーバ用HTTP、ローカルプロセス用Stdio*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

ローカルプロセス用です。アプリケーションがサーバーを子プロセスとして起動し、標準入出力を通じて通信します。ファイルシステムアクセスやCLIツールに便利です。

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

> **🤖 [GitHub Copilot](https://github.com/features/copilot) チャットで試そう:** [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)を開き、次のように質問してください：
> - 「Stdio通信はどう機能し、HTTPと比べていつ使うべきですか？」
> - 「LangChain4jは起動したMCPサーバープロセスのライフサイクルをどう管理していますか？」
> - 「AIにファイルシステムへのアクセスを許可することのセキュリティ上の影響は何ですか？」

## エージェンティックモジュール

MCPが標準化されたツールを提供する一方で、LangChain4jの**エージェンティックモジュール**はそれらのツールをオーケストレーションするエージェントを宣言的に構築する方法を提供します。`@Agent`アノテーションや`AgenticServices`により、命令的コードではなくインターフェースでエージェントの振る舞いを定義できます。

本モジュールでは、ユーザーの要求に基づきサブエージェントの呼び出しを動的に決定する高度なエージェンティックAIのアプローチ「Supervisor Agent」パターンを扱います。さらにサブエージェントの一つにMCPを使ったファイルアクセス機能を持たせて両者を組み合わせます。

エージェンティックモジュールを使うにはこのMaven依存を追加します：

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ 実験的:** `langchain4j-agentic`モジュールは**実験的**であり変更の可能性があります。安定したAIアシスタントの構築方法は引き続きカスタムツールによる`langchain4j-core`（モジュール04）です。

## 例の実行

### 前提条件

- Java 21以上、Maven 3.9以上
- Node.js 16以上とnpm（MCPサーバー用）
- ルートディレクトリの`.env`ファイルに環境変数設定済み：
  - `AZURE_OPENAI_ENDPOINT`、`AZURE_OPENAI_API_KEY`、`AZURE_OPENAI_DEPLOYMENT`（モジュール01～04と同様）

> **注意:** 環境変数をまだ設定していなければ、[モジュール00 - クイックスタート](../00-quick-start/README.md)を参照するか、ルートディレクトリで`.env.example`を`.env`にコピーし値を記入してください。

## クイックスタート

**VS Code使用時:** エクスプローラーで任意のデモファイルを右クリックし**「Run Java」**を選択するか、実行とデバッグパネルのランチ構成を使います（事前に`.env`にトークンを追加済みであること）。

**Maven使用時:** コマンドラインから下記例で実行しても構いません。

### ファイル操作（Stdio）

ローカルのサブプロセスベースのツールを示します。

**✅ 前提条件不要** - MCPサーバーは自動的に起動されます。

**スタートスクリプトの利用（推奨）:**

スタートスクリプトはルートの`.env`ファイルから環境変数を自動ロードします：

**Bash:**
```bash
cd 05-mcp
chmod +x start-stdio.sh
./start-stdio.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start-stdio.ps1
```

**VS Code使用:** `StdioTransportDemo.java`を右クリックし**「Run Java」**を選択（`.env`が設定済みであることを確認）。

アプリケーションはファイルシステムMCPサーバーを自動起動し、ローカルファイルを読み取ります。サブプロセスマネジメントが自動的に処理されていることに注目してください。

**期待される出力:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### スーパーバイザーエージェント

**Supervisor Agentパターン**は**柔軟な**エージェンティックAIの形態です。SupervisorはLLMを使いユーザー要求に基づいて自律的にどのエージェントを呼ぶか決定します。次の例では、MCPによるファイルアクセスとLLMエージェントを組み合わせ、ファイル読み取り→レポート生成の監督ワークフローを作っています。

デモでは`FileAgent`がMCPファイルシステムツールでファイルを読み、`ReportAgent`が1文の概要、3つの要点、推奨事項を含む構造化されたレポートを生成します。Supervisorがこのフローを自動的にオーケストレーションします：

<img src="../../../translated_images/ja/agentic.cf84dcda226374e3.png" alt="Agentic Module" width="800"/>

```
┌─────────────┐      ┌──────────────┐
│  FileAgent  │ ───▶ │ ReportAgent  │
│ (MCP tools) │      │  (pure LLM)  │
└─────────────┘      └──────────────┘
   outputKey:           outputKey:
  'fileContent'         'report'
```

各エージェントは**Agentic Scope**（共有メモリ）に結果を格納し、後続エージェントが前の結果にアクセス可能です。これによりMCPツールがエージェンティックワークフローにシームレスに統合されており、Supervisorは*ファイル読み取りの具体的な方法*を知らずとも`FileAgent`ができることを理解すれば良いのです。

#### デモの実行

スタートスクリプトはルートの`.env`ファイルから環境変数を自動ロードします：

**Bash:**
```bash
cd 05-mcp
chmod +x start-supervisor.sh
./start-supervisor.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start-supervisor.ps1
```

**VS Code使用:** `SupervisorAgentDemo.java`を右クリックして**「Run Java」**を選択（`.env`が設定済みであることを確認）。

#### Supervisorの動作

```java
// ステップ1: FileAgentはMCPツールを使用してファイルを読み取ります
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // ファイル操作のためのMCPツールを持っています
        .build();

// ステップ2: ReportAgentは構造化されたレポートを生成します
ReportAgent reportAgent = AgenticServices.agentBuilder(ReportAgent.class)
        .chatModel(model)
        .build();

// Supervisorはファイル → レポートのワークフローを調整します
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)
        .subAgents(fileAgent, reportAgent)
        .responseStrategy(SupervisorResponseStrategy.LAST)  // 最終レポートを返します
        .build();

// Supervisorはリクエストに基づいて呼び出すエージェントを決定します
String response = supervisor.invoke("Read the file at /path/file.txt and generate a report");
```

#### 応答戦略

`SupervisorAgent`を設定すると、サブエージェントの作業が完了した後に、どのように最終回答を組み立てるか指定します。利用可能な戦略は以下の通りです：

| 戦略 | 説明 |
|----------|-------------|
| **LAST** | 最後に呼ばれたサブエージェントまたはツールの出力を返します。ワークフローの最後のエージェントが完全な最終回答を生成するよう設計されている場合（例：研究パイプラインの「要約エージェント」）に有効です。 |
| **SUMMARY** | Supervisor自身の内部LLMを使い、対話全体とサブエージェントの出力を統合した要約を作成し、それを最終回答として返します。ユーザーにクリーンな集約回答を提供します。 |
| **SCORED** | 内部LLMを使用し、元のユーザー要求に対するLAST応答とSUMMARY応答のスコアを算出し、高スコアの出力を返します。 |

完全な実装は[SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java)を参照してください。

> **🤖 [GitHub Copilot](https://github.com/features/copilot) チャットで試そう:** [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java)を開き、次のように質問してください：
> - 「Supervisorはどのように呼び出すエージェントを決定しますか？」
> - 「SupervisorパターンとSequentialワークフローパターンの違いは何ですか？」
> - 「Supervisorの計画動作をどうカスタマイズできますか？」

#### 出力の理解

デモを実行すると、Supervisorが複数エージェントをどのようにオーケストレーションしているか構造化された解説付きで見ることができます。各セクションの意味は以下の通りです：

```
======================================================================
  FILE → REPORT WORKFLOW DEMO
======================================================================

This demo shows a clear 2-step workflow: read a file, then generate a report.
The Supervisor orchestrates the agents automatically based on the request.
```

**ヘッダー**はワークフロー概念を紹介し、ファイル読み取りからレポート生成までのフォーカスされたパイプラインを示します。

```
--- WORKFLOW ---------------------------------------------------------
  ┌─────────────┐      ┌──────────────┐
  │  FileAgent  │ ───▶ │ ReportAgent  │
  │ (MCP tools) │      │  (pure LLM)  │
  └─────────────┘      └──────────────┘
   outputKey:           outputKey:
   'fileContent'        'report'

--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]   FileAgent   - Reads files via MCP → stores in 'fileContent'
  [REPORT] ReportAgent - Generates structured report → stores in 'report'
```

**ワークフローダイアグラム**はエージェント間のデータフローを示します。それぞれの役割は：
- **FileAgent**がMCPツールでファイルを読み、`fileContent`にRaw内容を格納
- **ReportAgent**がその内容を受け取り、`report`に構造化レポートを生成

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and generate a report on its contents"
```

**ユーザー要求**はタスクを示します。Supervisorはこれを解析しFileAgent→ReportAgentの呼び出しを決定しました。

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor decides which agents to invoke and passes data between them...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source, provider-agnostic Java framework for building LLM...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> ReportAgent (generating structured report)
  |
  |   Input: LangChain4j is an open-source, provider-agnostic Java framew...
  |
  |   Result: Executive Summary...
  +-- [OK] ReportAgent (generating structured report) completed
```

**Supervisorのオーケストレーション**は2段階の処理を示します：
1. **FileAgent**がMCP経由でファイルを読み内容を格納
2. **ReportAgent**が内容を受けて構造化レポートを生成

これらの決定はユーザーの要求に基づき**自律的に**行われました。

```
--- FINAL RESPONSE ---------------------------------------------------
Executive Summary
...

Key Points
...

Recommendations
...

--- AGENTIC SCOPE (Data Flow) ----------------------------------------
  Each agent stores its output for downstream agents to consume:
  * fileContent: LangChain4j is an open-source, provider-agnostic Java framework...
  * report: Executive Summary...
```

#### エージェンティックモジュールの機能説明

この例はエージェンティックモジュールの高度な機能をいくつか示しています。Agentic ScopeとAgent Listenerを詳しく見てみましょう。

**Agentic Scope**はエージェントが`@Agent(outputKey="...")`で結果を格納する共有メモリです。これにより：
- 後続エージェントが先行エージェントの出力にアクセス可能
- Supervisorが最終回答を統合可能
- あなたが各エージェントの生成物を確認可能

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String fileContent = scope.readState("fileContent");  // FileAgentからの生ファイルデータ
String report = scope.readState("report");            // ReportAgentからの構造化レポート
```

**Agent Listener**はエージェントの実行監視とデバッグを可能にします。デモで見るステップごとの出力は各エージェント呼出しにフックするAgentListenerからのものです：
- **beforeAgentInvocation** - Supervisorがエージェントを選択した時に呼ばれ、どのエージェントを選び理由も見えます
- **afterAgentInvocation** - エージェント完了時に呼ばれ、その結果を表示
- **inheritedBySubagents** - trueの場合、階層内のすべてのエージェントを監視

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
        return true; // すべてのサブエージェントに伝播する
    }
};
```

Supervisorパターン以外にも、`langchain4j-agentic`モジュールは複数の強力なワークフローパターンと機能を提供します：

| パターン | 説明 | 利用例 |
|---------|-------------|----------|
| **Sequential** | エージェントを順番に実行し、出力を次に流す | パイプライン：研究→分析→レポート |
| **Parallel** | エージェントを同時実行 | 独立タスク：天気＋ニュース＋株価 |
| **Loop** | 条件満たすまで繰り返し | 品質スコアリング：スコア≥0.8まで改良 |
| **Conditional** | 条件に基づき経路選択 | 分類して専門エージェントへ振り分け |
| **Human-in-the-Loop** | 人によるチェックポイント追加 | 承認ワークフロー、コンテンツレビュー |

## 主要コンセプト

MCPとエージェンティックモジュールを実例で学んだ今、それぞれいつ使うべきかをまとめます。

**MCP**は既存のツールエコシステムを活用したい場合、複数アプリが共有するツールを作りたい場合、標準プロトコルでサードパーティサービスと統合したい場合、ツールの実装をコードを変えず差し替えたい場合に最適です。

**エージェンティックモジュール**は`@Agent`アノテーションによる宣言的エージェント定義を求める場合、逐次・ループ・並列など多様なワークフローオーケストレーションが必要な場合、インターフェースベース設計で命令的コードを避けたい場合、複数エージェントの出力を`outputKey`で共有したい場合に適しています。

**Supervisor Agentパターン**はワークフローが事前に決まらずLLMに動的判断させたい時、専門化した複数エージェントを動的にオーケストレーションしたい時、異なる能力へのルーティングを伴う対話システム構築時、最も柔軟で適応的なエージェント振る舞いを求める場合に輝きます。
## おめでとうございます！

LangChain4j for Beginners コースを修了しました。以下を学びました：

- メモリ付きの会話型AIの構築方法（モジュール01）
- さまざまなタスクに対応したプロンプトエンジニアリングパターン（モジュール02）
- RAGを用いたドキュメントに基づく応答のグラウンディング（モジュール03）
- カスタムツールを使った基本的なAIエージェント（アシスタント）の作成（モジュール04）
- LangChain4j MCPおよびAgenticモジュールを使った標準化ツールの統合（モジュール05）

### 次に何をする？

モジュールを修了したら、[Testing Guide](../docs/TESTING.md) を確認して、LangChain4jのテストコンセプトを実際に見てみましょう。

**公式リソース：**
- [LangChain4j Documentation](https://docs.langchain4j.dev/) - 包括的なガイドとAPIリファレンス
- [LangChain4j GitHub](https://github.com/langchain4j/langchain4j) - ソースコードと例
- [LangChain4j Tutorials](https://docs.langchain4j.dev/tutorials/) - さまざまなユースケースに対応したステップバイステップのチュートリアル

このコースを修了していただき、ありがとうございます！

---

**ナビゲーション：** [← 前へ：モジュール04 - ツール](../04-tools/README.md) | [メインに戻る](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責事項**：  
本書類はAI翻訳サービス「Co-op Translator」（https://github.com/Azure/co-op-translator）を使用して翻訳されています。正確さを期しておりますが、自動翻訳には誤りや不正確な部分が含まれる可能性があることをご承知おきください。原文の言語で記載された文書が正式な情報源とみなされます。重要な情報については、専門の人間による翻訳を推奨いたします。本翻訳の利用により生じたいかなる誤解や誤訳についても、当方は責任を負いかねます。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->