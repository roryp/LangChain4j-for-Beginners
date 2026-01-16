<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-05T22:15:39+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "ja"
}
-->
# モジュール 04: ツールを使った AI エージェント

## 目次

- [学習内容](../../../04-tools)
- [前提条件](../../../04-tools)
- [ツールを使った AI エージェントの理解](../../../04-tools)
- [ツール呼び出しの仕組み](../../../04-tools)
  - [ツール定義](../../../04-tools)
  - [意思決定](../../../04-tools)
  - [実行](../../../04-tools)
  - [応答生成](../../../04-tools)
- [ツール連鎖](../../../04-tools)
- [アプリケーションの実行](../../../04-tools)
- [アプリケーションの使用](../../../04-tools)
  - [シンプルなツール使用の試行](../../../04-tools)
  - [ツール連鎖のテスト](../../../04-tools)
  - [会話の流れを見る](../../../04-tools)
  - [さまざまなリクエストで実験](../../../04-tools)
- [重要な概念](../../../04-tools)
  - [ReAct パターン（推論と行動）](../../../04-tools)
  - [ツール説明の重要性](../../../04-tools)
  - [セッション管理](../../../04-tools)
  - [エラー処理](../../../04-tools)
- [利用可能なツール](../../../04-tools)
- [ツールベースのエージェントを使うべき時](../../../04-tools)
- [次のステップ](../../../04-tools)

## 学習内容

これまでに、AIとの会話方法、プロンプトの効果的な構成、および応答をドキュメントに基づかせる方法を学びました。しかし、まだ根本的な制約があります：言語モデルはテキストを生成するだけで、天気を確認したり計算を行ったり、データベースを照会したり外部システムとやりとりしたりできません。

ツールがこの制約を変えます。モデルに呼び出せる関数へのアクセス権を与えることで、テキスト生成モデルから行動を取れるエージェントへと変わります。モデルはツールが必要な時、そのツールの選択、渡すパラメータを決定します。コードが関数を実行し結果を返し、モデルはその結果を応答に組み込みます。

## 前提条件

- モジュール 01 完了（Azure OpenAI リソースのデプロイ済み）
- ルートディレクトリに Azure 認証情報を含む `.env` ファイル（モジュール 01 の `azd up` で作成）

> **注意：** モジュール 01 を完了していない場合は、まずそちらのデプロイ手順を実行してください。

## ツールを使った AI エージェントの理解

> **📝 注意：** このモジュールでの「エージェント」という用語は、ツール呼び出し能力を備えた AI アシスタントを指します。これは、[モジュール 05: MCP](../05-mcp/README.md) で扱う **Agentic AI** パターン（計画、記憶、多段階推論を持つ自律型エージェント）とは異なります。

ツールを持つ AI エージェントは推論と行動のパターン（ReAct）を辿ります：

1. ユーザーが質問する  
2. エージェントが何を知るべきか推論する  
3. 答えるためにツールが必要か判断する  
4. 必要な場合、適切なパラメータでツールを呼び出す  
5. ツールが実行されデータを返す  
6. エージェントが結果を取り込み最終回答を提示する

<img src="../../../translated_images/ja/react-pattern.86aafd3796f3fd13.png" alt="ReAct Pattern" width="800"/>

*ReActパターン - AIエージェントが問題解決のために推論と行動を交互に行う様子*

これは自動的に行われます。あなたはツールとその説明を定義し、モデルはどのツールをいつ使うかの意思決定を処理します。

## ツール呼び出しの仕組み

### ツール定義

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

関数を明確な説明とパラメータ仕様で定義します。モデルはシステムプロンプトでこれらの説明を見て、各ツールが何をするか理解します。

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // あなたの天気検索ロジック
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// アシスタントはSpring Bootによって自動的に次のように接続されます:
// - ChatModelビーンズ
// - @Componentクラスのすべての@Toolメソッド
// - セッション管理のためのChatMemoryProvider
```

> **🤖 [GitHub Copilot](https://github.com/features/copilot) チャットで試す：** [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) を開き、以下を質問してみましょう：
> - 「モックデータの代わりに OpenWeatherMap のような実際の天気APIを統合するにはどうすればいい？」
> - 「AIが正しく使うために良いツール説明とはどんなもの？」
> - 「ツール実装でのAPIエラーやレートリミットはどう扱う？」

### 意思決定

ユーザーが「シアトルの天気は？」と尋ねると、モデルは天気ツールが必要なことを認識します。ロケーションパラメータに "Seattle" をセットした関数呼び出しを生成します。

### 実行

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot は宣言的な `@AiService` インターフェースを全ツールと自動結合し、LangChain4j がツール呼び出しを自動実行します。

> **🤖 [GitHub Copilot](https://github.com/features/copilot) チャットで試す：** [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) を開き、以下を質問してみましょう：
> - 「ReActパターンはどのように機能し、なぜAIエージェントに効果的なのか？」
> - 「エージェントはどのツールを使い、どの順序で使うかどうやって決める？」
> - 「ツール実行が失敗したらどうなる？エラーを堅牢に扱う方法は？」

### 応答生成

モデルは天気データを受け取り、ユーザー向けに自然言語の回答として整形します。

### なぜ宣言的 AI サービスを使うのか？

このモジュールでは LangChain4j の Spring Boot 統合を用いた宣言的 `@AiService` インターフェースを使用しています：

- **Spring Boot の自動結合** - ChatModel とツールが自動注入される  
- **@MemoryId パターン** - セッションベースのメモリ管理を自動化  
- **シングルインスタンス** - アシスタントを一度作って再利用しパフォーマンス向上  
- **型安全な実行** - Java メソッドを型変換付きで直接呼び出し  
- **マルチターンオーケストレーション** - ツール連鎖を自動処理  
- **ボイラープレート不要** - 手動の AiServices.builder() 呼び出しやメモリ HashMap は不要

手動の `AiServices.builder()` を使う代替策はコードが増え、Spring Boot 統合の利点を享受できません。

## ツール連鎖

**ツール連鎖** - AI は複数のツールを連続で呼び出すことがあります。「シアトルの天気は？傘は必要？」と聞くと、`getCurrentWeather` を呼び、その結果を基に雨具の要否を推論します。

<a href="images/tool-chaining.png"><img src="../../../translated_images/ja/tool-chaining.3b25af01967d6f7b.png" alt="ツール連鎖" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*連続したツール呼び出し - ひとつのツールの出力が次の判断の入力に繋がる*

**グレースフルフェイル** - モックデータにない都市の天気を聞くと、ツールはエラーメッセージを返し、AIは助けられない旨を説明します。ツールは安全に失敗します。

この処理は単一の会話ターン内で起こり、エージェントが複数ツール呼び出しを自動的に調整します。

## アプリケーションの実行

**デプロイの検証：**

ルートディレクトリに Azure 認証情報を含む `.env` ファイルが存在することを確認（モジュール 01 実行時に作成）：  
```bash
cat ../.env  # AZURE_OPENAI_ENDPOINT、API_KEY、DEPLOYMENTを表示する必要があります
```
  
**アプリケーションの起動：**

> **注意：** モジュール 01 から `./start-all.sh` で全アプリケーション起動済みの場合、このモジュールはすでにポート 8084 で動作中です。以下の起動コマンドはスキップし、直接 http://localhost:8084 にアクセスしてください。

**オプション 1：Spring Boot ダッシュボードの利用（VS Codeユーザー推奨）**

開発コンテナには Spring Boot ダッシュボード拡張機能が含まれ、すべての Spring Boot アプリを視覚的に管理できます。VS Code の左側アクティビティバーから（Spring Boot アイコンを探してください）アクセス可能。

Spring Boot ダッシュボードからは：
- ワークスペース内のすべての Spring Boot アプリを一覧表示  
- ワンクリックで起動/停止  
- リアルタイムログ閲覧  
- アプリケーションのステータス監視

「tools」隣の再生ボタンをクリックすればこのモジュール起動、または全モジュール一括起動もできます。

<img src="../../../translated_images/ja/dashboard.9b519b1a1bc1b30a.png" alt="Spring Boot Dashboard" width="400"/>

**オプション 2：シェルスクリプト使用**

すべてのウェブアプリ（モジュール01-04）を起動：

**Bash:**  
```bash
cd ..  # ルートディレクトリから
./start-all.sh
```
  
**PowerShell:**  
```powershell
cd ..  # ルートディレクトリから
.\start-all.ps1
```
  
またはこのモジュールのみ起動：

**Bash:**  
```bash
cd 04-tools
./start.sh
```
  
**PowerShell:**  
```powershell
cd 04-tools
.\start.ps1
```
  
どちらのスクリプトも `.env` ファイルから環境変数を自動読み込みし、JARがなければビルドします。

> **注意：** 先にすべてのモジュールを手動ビルドしてから起動したい場合：
>
> **Bash:**  
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

> **PowerShell:**  
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
  
ブラウザで http://localhost:8084 を開きます。

**停止するには：**

**Bash:**  
```bash
./stop.sh  # このモジュールのみ
# または
cd .. && ./stop-all.sh  # すべてのモジュール
```
  
**PowerShell:**  
```powershell
.\stop.ps1  # このモジュールのみ
# または
cd ..; .\stop-all.ps1  # すべてのモジュール
```


## アプリケーションの使用

このアプリは、天気や温度変換ツールへアクセスできる AI エージェントと対話するためのウェブインターフェースを提供します。

<a href="images/tools-homepage.png"><img src="../../../translated_images/ja/tools-homepage.4b4cd8b2717f9621.png" alt="AI エージェントツール インターフェース" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI エージェントツール インターフェース - ツールと対話するためのクイックサンプルとチャットUI*

### シンプルなツール使用の試行

まずは単純なリクエストを試してください：「100度華氏を摂氏に変換して」。エージェントは温度変換ツールが必要と認識し、正しいパラメータで呼び出し結果を返します。どのツールを使うか、呼び出し方法を指定していないのに自然な流れだったことに気づくでしょう。

### ツール連鎖のテスト

次に複合的な例：「シアトルの天気は？華氏に変換して」。エージェントが段階的に解決する様子を見てください。まず天気（摂氏）を取得し、次に華氏変換ツール呼び出し、両結果をまとめて返します。

### 会話の流れを見る

チャットインターフェースは会話履歴を保持し、マルチターン対話を可能にします。以前の問い合わせや応答をすべて見られるので、会話の追跡や複数回の交換でどのように文脈を構築しているか理解しやすいです。

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/ja/tools-conversation-demo.89f2ce9676080f59.png" alt="複数ツール呼び出しを含む会話" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*シンプルな変換、天気照会、ツール連鎖を含むマルチターン会話*

### さまざまなリクエストで実験

次のような組み合わせを試してください：  
- 天気照会：「東京の天気は？」  
- 温度変換：「25℃はケルビンで？」  
- 複合クエリ：「パリの天気を調べて、20℃以上か教えて」

エージェントが自然言語をどう解釈し適切なツール呼び出しにマッピングするかを実感できます。

## 重要な概念

### ReAct パターン（推論と行動）

エージェントは推論（何をすべきか決める）と行動（ツールを使う）を交互に行います。このパターンが単なる指示応答以上の自律的問題解決を可能にします。

### ツール説明の重要性

ツール説明の品質がエージェントの利用精度に直結します。明確かつ具体的な説明が、モデルに正しいツール呼び出しのタイミングや方法を理解させます。

### セッション管理

`@MemoryId` アノテーションによりセッションベースのメモリ管理が自動化されます。各セッションIDは `ChatMemory` インスタンスを持ち、`ChatMemoryProvider` ビーンが管理。手動でメモリ追跡を行う必要がありません。

### エラー処理

ツールは失敗することがあります—APIタイムアウト、無効パラメータ、外部サービス停止など。実稼働エージェントはエラー処理を行い、モデルが問題を説明したり代替手段を試せるようにします。

## 利用可能なツール

**天気関連ツール**（デモのためのモックデータ）：  
- 指定場所の現在の天気取得  
- 複数日間の天気予報取得  

**温度変換ツール**：  
- 摂氏 ⇔ 華氏  
- 摂氏 ⇔ ケルビン  
- 華氏 ⇔ ケルビン

これは簡単な例ですが、パターンはデータベース照会、API呼び出し、計算、ファイル操作、システムコマンドなど任意の関数に拡張できます。

## ツールベースのエージェントを使うべき時

**ツール利用が適している場合：**  
- リアルタイムデータが必要な回答（天気、株価、在庫）  
- 単純な数学以上の計算が必要な場合  
- データベースや API へのアクセス時  
- メール送信、チケット作成、記録更新などのアクション実行時  
- 複数データソースの組み合わせ時  

**ツールを使わないほうがいい場合：**  
- 一般知識で回答可能な質問の場合  
- 応答が純粋に会話的な内容のみの場合  
- ツール呼び出しの遅延が体験を悪くする場合  

## 次のステップ

**次のモジュール：** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**ナビゲーション：** [← 前へ: モジュール 03 - RAG](../03-rag/README.md) | [メインに戻る](../README.md) | [次へ: モジュール 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責事項**：  
本書類はAI翻訳サービス[Co-op Translator](https://github.com/Azure/co-op-translator)を使用して翻訳されています。正確性を目指しておりますが、自動翻訳には誤りや不正確な部分が含まれる可能性があることをご了承ください。原文はあくまでも公式な情報源とみなしてください。重要な情報に関しては、専門の人間による翻訳を推奨します。本翻訳の使用により生じる誤解や解釈の相違について、当方は一切の責任を負いかねます。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->