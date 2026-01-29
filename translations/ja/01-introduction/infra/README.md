# LangChain4jのAzureインフラストラクチャ入門

## 目次

- [前提条件](../../../../01-introduction/infra)
- [アーキテクチャ](../../../../01-introduction/infra)
- [作成されるリソース](../../../../01-introduction/infra)
- [クイックスタート](../../../../01-introduction/infra)
- [設定](../../../../01-introduction/infra)
- [管理コマンド](../../../../01-introduction/infra)
- [コスト最適化](../../../../01-introduction/infra)
- [監視](../../../../01-introduction/infra)
- [トラブルシューティング](../../../../01-introduction/infra)
- [インフラストラクチャの更新](../../../../01-introduction/infra)
- [クリーンアップ](../../../../01-introduction/infra)
- [ファイル構成](../../../../01-introduction/infra)
- [セキュリティ推奨事項](../../../../01-introduction/infra)
- [追加リソース](../../../../01-introduction/infra)

このディレクトリには、BicepとAzure Developer CLI (azd) を使用したAzureインフラストラクチャコード（IaC）が含まれており、Azure OpenAIリソースのデプロイに使用されます。

## 前提条件

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli)（バージョン2.50.0以降）
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd)（バージョン1.5.0以降）
- リソース作成権限を持つAzureサブスクリプション

## アーキテクチャ

**簡易ローカル開発セットアップ** - Azure OpenAIのみをデプロイし、すべてのアプリをローカルで実行。

インフラストラクチャは以下のAzureリソースをデプロイします：

### AIサービス
- **Azure OpenAI**：2つのモデルデプロイを持つ認知サービス：
  - **gpt-5**：チャット補完モデル（20K TPM容量）
  - **text-embedding-3-small**：RAG用の埋め込みモデル（20K TPM容量）

### ローカル開発
すべてのSpring Bootアプリケーションはローカルマシンで実行されます：
- 01-introduction（ポート8080）
- 02-prompt-engineering（ポート8083）
- 03-rag（ポート8081）
- 04-tools（ポート8084）

## 作成されるリソース

| リソースタイプ | リソース名パターン | 用途 |
|--------------|----------------------|---------|
| リソースグループ | `rg-{environmentName}` | すべてのリソースを含む |
| Azure OpenAI | `aoai-{resourceToken}` | AIモデルホスティング |

> **注意:** `{resourceToken}` はサブスクリプションID、環境名、ロケーションから生成される一意の文字列です

## クイックスタート

### 1. Azure OpenAIをデプロイ

**Bash:**
```bash
cd 01-introduction
azd up
```

**PowerShell:**
```powershell
cd 01-introduction
azd up
```

プロンプトが表示されたら：
- Azureサブスクリプションを選択
- ロケーションを選択（推奨：GPT-5利用可能な`eastus2`または`swedencentral`）
- 環境名を確認（デフォルト：`langchain4j-dev`）

これにより以下が作成されます：
- GPT-5およびtext-embedding-3-smallを含むAzure OpenAIリソース
- 接続情報の出力

### 2. 接続情報を取得

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

表示される内容：
- `AZURE_OPENAI_ENDPOINT`：Azure OpenAIのエンドポイントURL
- `AZURE_OPENAI_KEY`：認証用APIキー
- `AZURE_OPENAI_DEPLOYMENT`：チャットモデル名（gpt-5）
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`：埋め込みモデル名

### 3. アプリケーションをローカルで実行

`azd up`コマンドはルートディレクトリに必要な環境変数を含む`.env`ファイルを自動作成します。

**推奨:** すべてのWebアプリケーションを起動：

**Bash:**
```bash
# ルートディレクトリから
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# ルートディレクトリから
cd ../..
.\start-all.ps1
```

または単一モジュールを起動：

**Bash:**
```bash
# 例：イントロダクションモジュールだけを開始する
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# 例：イントロダクションモジュールだけを開始する
cd ../01-introduction
.\start.ps1
```

両方のスクリプトは`azd up`で作成されたルートの`.env`ファイルから環境変数を自動的に読み込みます。

## 設定

### モデルデプロイのカスタマイズ

モデルデプロイを変更するには、`infra/main.bicep`を編集し、`openAiDeployments`パラメータを修正します：

```bicep
param openAiDeployments array = [
  {
    name: 'gpt-5'  // Model deployment name
    model: {
      format: 'OpenAI'
      name: 'gpt-5'
      version: '2025-08-07'  // Model version
    }
    sku: {
      name: 'Standard'
      capacity: 20  // TPM in thousands
    }
  }
  // Add more deployments...
]
```

利用可能なモデルとバージョン：https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azureリージョンの変更

別のリージョンにデプロイするには、`infra/main.bicep`を編集します：

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5の利用可能地域を確認：https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicepファイルを変更後にインフラを更新するには：

**Bash:**
```bash
# ARM テンプレートを再構築する
az bicep build --file infra/main.bicep

# 変更をプレビューする
azd provision --preview

# 変更を適用する
azd provision
```

**PowerShell:**
```powershell
# ARM テンプレートを再構築する
az bicep build --file infra/main.bicep

# 変更をプレビューする
azd provision --preview

# 変更を適用する
azd provision
```

## クリーンアップ

すべてのリソースを削除するには：

**Bash:**
```bash
# すべてのリソースを削除する
azd down

# 環境を含むすべてを削除する
azd down --purge
```

**PowerShell:**
```powershell
# すべてのリソースを削除する
azd down

# 環境を含むすべてを削除する
azd down --purge
```

**警告**：これによりすべてのAzureリソースが完全に削除されます。

## ファイル構成

## コスト最適化

### 開発/テスト環境
開発・テスト環境ではコストを削減可能：
- Azure OpenAIはStandardティア（S0）を使用
- `infra/core/ai/cognitiveservices.bicep`で容量を低く設定（20Kの代わりに10K TPM）
- 使用しないときはリソースを削除：`azd down`

### 本番環境
本番環境では：
- 使用量に応じてOpenAI容量を増加（50K以上のTPM）
- 高可用性のためゾーン冗長性を有効化
- 適切な監視とコストアラートを実装

### コスト見積もり
- Azure OpenAI：トークン単位の課金（入力＋出力）
- GPT-5：約3～5ドル／100万トークン（最新価格を確認）
- text-embedding-3-small：約0.02ドル／100万トークン

価格計算ツール：https://azure.microsoft.com/pricing/calculator/

## 監視

### Azure OpenAIのメトリクス確認

Azureポータル → OpenAIリソース → メトリクス：
- トークンベースの利用率
- HTTPリクエストレート
- 応答時間
- アクティブトークン数

## トラブルシューティング

### 問題：Azure OpenAIサブドメイン名の競合

**エラーメッセージ：**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**原因：**
サブスクリプションや環境から生成されたサブドメイン名が既に使用中で、以前のデプロイが完全に削除されていない可能性があります。

**解決策：**
1. **オプション1 - 別の環境名を使用：**
   
   **Bash:**
   ```bash
   azd env new my-unique-env-name
   azd up
   ```
   
   **PowerShell:**
   ```powershell
   azd env new my-unique-env-name
   azd up
   ```

2. **オプション2 - Azureポータルから手動デプロイ：**
   - Azureポータル → リソースの作成 → Azure OpenAI
   - リソースに一意の名前を付ける
   - 以下のモデルをデプロイ：
     - **GPT-5**
     - **text-embedding-3-small**（RAGモジュール用）
   - **重要:** デプロイ名は`.env`設定と一致させること
   - デプロイ後、「キーとエンドポイント」からエンドポイントとAPIキーを取得
   - プロジェクトルートに以下のような`.env`ファイルを作成：
     
     **例 `.env` ファイル：**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**モデルデプロイ命名ガイドライン：**
- シンプルで一貫した名前を使用：`gpt-5`、`gpt-4o`、`text-embedding-3-small`
- デプロイ名は`.env`で設定したものと完全に一致させる
- よくあるミス：モデルをある名前で作成し、コードで別名を参照すること

### 問題：選択したリージョンでGPT-5が利用できない

**解決策：**
- GPT-5が利用可能なリージョンを選択（例：eastus、swedencentral）
- 利用可能地域を確認：https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### 問題：デプロイのクォータ不足

**解決策：**
1. Azureポータルでクォータ増加を申請
2. または`main.bicep`で容量を低く設定（例：capacity: 10）

### 問題：ローカル実行時に「リソースが見つかりません」

**解決策：**
1. デプロイ状況を確認：`azd env get-values`
2. エンドポイントとキーが正しいか確認
3. Azureポータルでリソースグループが存在するか確認

### 問題：認証失敗

**解決策：**
- `AZURE_OPENAI_API_KEY`が正しく設定されているか確認
- キーは32文字の16進数文字列である必要あり
- 必要に応じてAzureポータルから新しいキーを取得

### デプロイ失敗

**問題**：`azd provision`がクォータや容量エラーで失敗

**解決策**： 
1. 別のリージョンを試す - [Azureリージョンの変更](../../../../01-introduction/infra)を参照
2. サブスクリプションにAzure OpenAIクォータがあるか確認：
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### アプリケーションが接続しない

**問題**：Javaアプリケーションで接続エラーが発生

**解決策**：
1. 環境変数がエクスポートされているか確認：
   
   **Bash:**
   ```bash
   echo $AZURE_OPENAI_ENDPOINT
   echo $AZURE_OPENAI_API_KEY
   ```
   
   **PowerShell:**
   ```powershell
   Write-Host $env:AZURE_OPENAI_ENDPOINT
   Write-Host $env:AZURE_OPENAI_API_KEY
   ```

2. エンドポイントの形式が正しいか確認（`https://xxx.openai.azure.com`であること）
3. APIキーがAzureポータルのプライマリまたはセカンダリキーであることを確認

**問題**：Azure OpenAIから401 Unauthorizedが返る

**解決策**：
1. Azureポータル → キーとエンドポイントから新しいAPIキーを取得
2. `AZURE_OPENAI_API_KEY`環境変数を再エクスポート
3. モデルデプロイが完了しているか確認（Azureポータルで確認）

### パフォーマンス問題

**問題**：応答が遅い

**解決策**：
1. AzureポータルのメトリクスでOpenAIのトークン使用量とスロットリングを確認
2. 制限に達している場合はTPM容量を増加
3. より高い推論努力レベル（低/中/高）を検討

## インフラストラクチャの更新

```
infra/
├── main.bicep                       # Main infrastructure definition
├── main.json                        # Compiled ARM template (auto-generated)
├── main.bicepparam                  # Parameter file
├── README.md                        # This file
└── core/
    └── ai/
        └── cognitiveservices.bicep  # Azure OpenAI module
```

## セキュリティ推奨事項

1. **APIキーをコミットしないこと** - 環境変数を使用
2. **ローカルでは.envファイルを使用** - `.env`を`.gitignore`に追加
3. **キーは定期的にローテーション** - Azureポータルで新しいキーを生成
4. **アクセス制限** - Azure RBACでリソースアクセスを制御
5. **使用状況の監視** - Azureポータルでコストアラートを設定

## 追加リソース

- [Azure OpenAIサービスドキュメント](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5モデルドキュメント](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLIドキュメント](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicepドキュメント](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI公式統合](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## サポート

問題がある場合：
1. 上記の[トラブルシューティングセクション](../../../../01-introduction/infra)を確認
2. AzureポータルでAzure OpenAIサービスの状態を確認
3. リポジトリでIssueを開く

## ライセンス

詳細はルートの[LICENSE](../../../../LICENSE)ファイルを参照してください。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責事項**：  
本書類はAI翻訳サービス「Co-op Translator」（https://github.com/Azure/co-op-translator）を使用して翻訳されました。正確性を期しておりますが、自動翻訳には誤りや不正確な部分が含まれる可能性があります。原文の言語による文書が正式な情報源とみなされるべきです。重要な情報については、専門の人間による翻訳を推奨します。本翻訳の利用により生じた誤解や誤訳について、当方は一切の責任を負いかねます。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->