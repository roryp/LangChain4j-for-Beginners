# LangChain4j Azure 基礎架構入門指南

## 目錄

- [先決條件](../../../../01-introduction/infra)
- [架構](../../../../01-introduction/infra)
- [建立的資源](../../../../01-introduction/infra)
- [快速開始](../../../../01-introduction/infra)
- [設定](../../../../01-introduction/infra)
- [管理指令](../../../../01-introduction/infra)
- [成本優化](../../../../01-introduction/infra)
- [監控](../../../../01-introduction/infra)
- [疑難排解](../../../../01-introduction/infra)
- [更新基礎架構](../../../../01-introduction/infra)
- [清理](../../../../01-introduction/infra)
- [檔案結構](../../../../01-introduction/infra)
- [安全建議](../../../../01-introduction/infra)
- [其他資源](../../../../01-introduction/infra)

此目錄包含使用 Bicep 和 Azure Developer CLI (azd) 的 Azure 基礎架構即程式碼 (IaC)，用於部署 Azure OpenAI 資源。

## 先決條件

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli)（版本 2.50.0 或更新）
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd)（版本 1.5.0 或更新）
- 具有建立資源權限的 Azure 訂閱

## 架構

**簡化本地開發設定** - 僅部署 Azure OpenAI，所有應用程式在本機執行。

基礎架構部署以下 Azure 資源：

### AI 服務
- **Azure OpenAI**：認知服務，包含兩個模型部署：
  - **gpt-5**：聊天完成模型（20K TPM 容量）
  - **text-embedding-3-small**：用於 RAG 的嵌入模型（20K TPM 容量）

### 本地開發
所有 Spring Boot 應用程式在您的機器本地執行：
- 01-introduction（埠號 8080）
- 02-prompt-engineering（埠號 8083）
- 03-rag（埠號 8081）
- 04-tools（埠號 8084）

## 建立的資源

| 資源類型 | 資源名稱模式 | 用途 |
|--------------|----------------------|---------|
| 資源群組 | `rg-{environmentName}` | 包含所有資源 |
| Azure OpenAI | `aoai-{resourceToken}` | AI 模型託管 |

> **注意：** `{resourceToken}` 是由訂閱 ID、環境名稱和位置生成的唯一字串

## 快速開始

### 1. 部署 Azure OpenAI

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

當提示時：
- 選擇您的 Azure 訂閱
- 選擇地區（建議：`eastus2` 或 `swedencentral`，以支援 GPT-5）
- 確認環境名稱（預設：`langchain4j-dev`）

此操作將建立：
- 含 GPT-5 和 text-embedding-3-small 的 Azure OpenAI 資源
- 輸出連線詳細資訊

### 2. 取得連線詳細資訊

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

此指令會顯示：
- `AZURE_OPENAI_ENDPOINT`：您的 Azure OpenAI 端點 URL
- `AZURE_OPENAI_KEY`：用於驗證的 API 金鑰
- `AZURE_OPENAI_DEPLOYMENT`：聊天模型名稱（gpt-5）
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`：嵌入模型名稱

### 3. 本地執行應用程式

`azd up` 指令會自動在根目錄建立 `.env` 檔案，包含所有必要的環境變數。

**建議：** 啟動所有 Web 應用程式：

**Bash:**
```bash
# 從根目錄開始
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# 從根目錄開始
cd ../..
.\start-all.ps1
```

或啟動單一模組：

**Bash:**
```bash
# 例子：只啟動介紹模組
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# 例子：只啟動介紹模組
cd ../01-introduction
.\start.ps1
```

兩個腳本皆會自動從 `azd up` 建立的根目錄 `.env` 檔案載入環境變數。

## 設定

### 自訂模型部署

若要更改模型部署，請編輯 `infra/main.bicep` 並修改 `openAiDeployments` 參數：

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

可用模型與版本：https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### 更改 Azure 區域

若要在不同區域部署，請編輯 `infra/main.bicep`：

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

檢查 GPT-5 可用性：https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

在修改 Bicep 檔案後更新基礎架構：

**Bash:**
```bash
# 重建 ARM 模板
az bicep build --file infra/main.bicep

# 預覽更改
azd provision --preview

# 套用更改
azd provision
```

**PowerShell:**
```powershell
# 重建 ARM 模板
az bicep build --file infra/main.bicep

# 預覽更改
azd provision --preview

# 套用更改
azd provision
```

## 清理

刪除所有資源：

**Bash:**
```bash
# 刪除所有資源
azd down

# 刪除包括環境在內的所有內容
azd down --purge
```

**PowerShell:**
```powershell
# 刪除所有資源
azd down

# 刪除所有包括環境
azd down --purge
```

**警告**：此操作將永久刪除所有 Azure 資源。

## 檔案結構

## 成本優化

### 開發/測試
對於開發/測試環境，可降低成本：
- Azure OpenAI 使用標準層 (S0)
- 在 `infra/core/ai/cognitiveservices.bicep` 中設定較低容量（10K TPM 取代 20K）
- 不使用時刪除資源：`azd down`

### 生產環境
生產環境：
- 根據使用量增加 OpenAI 容量（50K+ TPM）
- 啟用區域冗餘以提高可用性
- 實施適當監控與成本警示

### 成本估算
- Azure OpenAI：按代幣數量付費（輸入 + 輸出）
- GPT-5：約每百萬代幣 3-5 美元（請查詢最新價格）
- text-embedding-3-small：約每百萬代幣 0.02 美元

價格計算器：https://azure.microsoft.com/pricing/calculator/

## 監控

### 查看 Azure OpenAI 指標

前往 Azure 入口網站 → 您的 OpenAI 資源 → 指標：
- 基於代幣的使用率
- HTTP 請求率
- 回應時間
- 活躍代幣數

## 疑難排解

### 問題：Azure OpenAI 子網域名稱衝突

**錯誤訊息：**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**原因：**
由您的訂閱/環境產生的子網域名稱已被使用，可能是先前部署未完全清除所致。

**解決方案：**
1. **方案一 - 使用不同的環境名稱：**
   
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

2. **方案二 - 透過 Azure 入口網站手動部署：**
   - 前往 Azure 入口網站 → 建立資源 → Azure OpenAI
   - 選擇唯一的資源名稱
   - 部署以下模型：
     - **GPT-5**
     - **text-embedding-3-small**（用於 RAG 模組）
   - **重要：** 記下您的部署名稱，必須與 `.env` 設定相符
   - 部署完成後，從「金鑰與端點」取得端點與 API 金鑰
   - 在專案根目錄建立 `.env` 檔案，內容如下：
     
     **範例 `.env` 檔案：**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**模型部署命名指南：**
- 使用簡單且一致的名稱：`gpt-5`、`gpt-4o`、`text-embedding-3-small`
- 部署名稱必須與 `.env` 中設定完全相符
- 常見錯誤：建立模型名稱與程式碼中引用名稱不一致

### 問題：選擇的區域無法使用 GPT-5

**解決方案：**
- 選擇支援 GPT-5 的區域（例如 eastus、swedencentral）
- 查詢可用性：https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### 問題：部署配額不足

**解決方案：**
1. 在 Azure 入口網站申請配額提升
2. 或在 `main.bicep` 中使用較低容量（例如 capacity: 10）

### 問題：本地執行時出現「找不到資源」

**解決方案：**
1. 驗證部署狀態：`azd env get-values`
2. 確認端點與金鑰正確
3. 確認資源群組在 Azure 入口網站存在

### 問題：驗證失敗

**解決方案：**
- 確認 `AZURE_OPENAI_API_KEY` 設定正確
- 金鑰格式應為 32 字元十六進位字串
- 如有需要，從 Azure 入口網站取得新金鑰

### 部署失敗

**問題**：`azd provision` 因配額或容量錯誤失敗

**解決方案**： 
1. 嘗試不同區域 - 請參閱 [更改 Azure 區域](../../../../01-introduction/infra) 章節設定區域
2. 確認您的訂閱有 Azure OpenAI 配額：
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### 應用程式無法連線

**問題**：Java 應用程式顯示連線錯誤

**解決方案**：
1. 確認環境變數已匯出：
   
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

2. 檢查端點格式是否正確（應為 `https://xxx.openai.azure.com`）
3. 確認 API 金鑰為 Azure 入口網站的主要或次要金鑰

**問題**：Azure OpenAI 回傳 401 未授權

**解決方案**：
1. 從 Azure 入口網站 → 金鑰與端點取得新 API 金鑰
2. 重新匯出 `AZURE_OPENAI_API_KEY` 環境變數
3. 確認模型部署完成（檢查 Azure 入口網站）

### 效能問題

**問題**：回應時間緩慢

**解決方案**：
1. 檢查 Azure 入口網站指標中的 OpenAI 代幣使用與限制
2. 若達到限制，增加 TPM 容量
3. 考慮使用較高的推理努力等級（低/中/高）

## 更新基礎架構

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

## 安全建議

1. **切勿提交 API 金鑰** - 使用環境變數
2. **本地使用 .env 檔案** - 將 `.env` 加入 `.gitignore`
3. **定期輪替金鑰** - 在 Azure 入口網站產生新金鑰
4. **限制存取權限** - 使用 Azure RBAC 控制資源存取
5. **監控使用情況** - 在 Azure 入口網站設定成本警示

## 其他資源

- [Azure OpenAI 服務文件](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 模型文件](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI 文件](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep 文件](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI 官方整合](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## 支援

如有問題：
1. 請先查看上方的 [疑難排解章節](../../../../01-introduction/infra)
2. 檢查 Azure 入口網站中的 Azure OpenAI 服務狀態
3. 在本專案倉庫開啟 issue

## 授權

詳見根目錄 [LICENSE](../../../../LICENSE) 檔案。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：  
本文件由 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們致力於確保準確性，但請注意自動翻譯可能包含錯誤或不準確之處。原始文件的母語版本應被視為權威來源。對於重要資訊，建議採用專業人工翻譯。我們不對因使用本翻譯而引起的任何誤解或誤釋承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->