<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-05T22:06:06+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "hk"
}
-->
# Module 04: AI Agents with Tools

## 目錄

- [你將學到什麼](../../../04-tools)
- [前置條件](../../../04-tools)
- [理解具備工具的 AI 代理](../../../04-tools)
- [工具調用如何運作](../../../04-tools)
  - [工具定義](../../../04-tools)
  - [決策制定](../../../04-tools)
  - [執行](../../../04-tools)
  - [回應生成](../../../04-tools)
- [工具鏈結](../../../04-tools)
- [執行應用程式](../../../04-tools)
- [使用應用程式](../../../04-tools)
  - [嘗試簡單的工具使用](../../../04-tools)
  - [測試工具鏈結](../../../04-tools)
  - [查看對話流程](../../../04-tools)
  - [嘗試不同的請求](../../../04-tools)
- [關鍵概念](../../../04-tools)
  - [ReAct 模式（推理與執行）](../../../04-tools)
  - [工具描述很重要](../../../04-tools)
  - [會話管理](../../../04-tools)
  - [錯誤處理](../../../04-tools)
- [可用的工具](../../../04-tools)
- [何時使用基於工具的代理](../../../04-tools)
- [下一步](../../../04-tools)

## 你將學到什麼

到目前為止，你已學會如何與 AI 對話、有效地結構提示詞，並在文件中建立回應的依據。但仍存在一個基本限制：語言模型只能生成文本。它們無法查詢天氣、執行計算、查詢資料庫或與外部系統互動。

工具改變了這一點。通過讓模型能調用函數，你將它從文本生成器轉變為可執行動作的代理。模型決定什麼時候需要工具、使用哪個工具，以及傳遞什麼參數。你的程式碼執行該函數並返回結果。模型則將該結果整合到回應中。

## 前置條件

- 完成 Module 01（已部署 Azure OpenAI 資源）
- 根目錄有 `.env` 檔案，包含 Azure 憑證（由 Module 01 中的 `azd up` 建立）

> **注意：** 如果尚未完成 Module 01，請先依該模組的部署說明進行部署。

## 理解具備工具的 AI 代理

> **📝 注意：** 本模組中「代理」一詞指的是具備調用工具功能的 AI 助手。這與我們將在 [Module 05: MCP](../05-mcp/README.md) 探討的 **Agentic AI** 模式（具備規劃、記憶及多步推理的自主代理）不同。

具備工具的 AI 代理遵循一種推理與執行模式（ReAct）：

1. 使用者提出問題
2. 代理推理其所需資訊
3. 代理決定是否需要工具回答
4. 若需要，代理用正確參數呼叫適當工具
5. 工具執行並返回資料
6. 代理整合結果並提供最終答案

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.hk.png" alt="ReAct Pattern" width="800"/>

*ReAct 模式 — AI 代理如何在推理與執行間交替以解決問題*

這是自動進行的。你定義工具及其描述，模型負責決策何時及如何使用它們。

## 工具調用如何運作

### 工具定義

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

你定義具有清晰描述和參數規範的函數。模型在系統提示中看到這些描述，並理解每個工具的作用。

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // 你的天氣查詢邏輯
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// Assistant 由 Spring Boot 自動配置，包含：
// - ChatModel Bean
// - 所有來自 @Component 類別的 @Tool 方法
// - 用於會話管理的 ChatMemoryProvider
```

> **🤖 試試用 [GitHub Copilot](https://github.com/features/copilot) 聊天：** 打開 [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) 並問：
> - 「我如何整合像 OpenWeatherMap 的真實天氣 API，而非使用模擬資料？」
> - 「什麼是能幫助 AI 正確使用它的良好工具描述？」
> - 「我如何在工具實現中處理 API 錯誤與速率限制？」

### 決策制定

當使用者問「西雅圖的天氣如何？」模型識別需要呼叫天氣工具。它生成一個函式呼叫，設定位置參數為「Seattle」。

### 執行

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot 自動注入帶有所有已註冊工具的宣告式 `@AiService` 介面，LangChain4j 會自動執行工具調用。

> **🤖 試試用 [GitHub Copilot](https://github.com/features/copilot) 聊天：** 打開 [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) 並問：
> - 「ReAct 模式如何運作？為何對 AI 代理有效？」
> - 「代理如何決定使用哪個工具及其順序？」
> - 「如果工具執行失敗，該如何健全地處理錯誤？」

### 回應生成

模型收到天氣資料後，將其格式化為自然語言回應使用者。

### 為何使用宣告式 AI 服務？

本模組使用 LangChain4j 的 Spring Boot 整合和宣告式 `@AiService` 介面：

- **Spring Boot 自動注入** — ChatModel 與工具自動進行依賴注入
- **@MemoryId 模式** — 自動化基於會話的記憶管理
- **單一實例** — 助手創建一次後重複使用以提升效能
- **型別安全執行** — 直接呼叫 Java 方法並自動類型轉換
- **多輪協調** — 自動處理工具鏈結
- **零樣板碼** — 不需手動調用 AiServices.builder() 或記憶 HashMap

其他方法（手動呼叫 AiServices.builder()）需要更多代碼，且無法享有 Spring Boot 整合優勢。

## 工具鏈結

**工具鏈結** — AI 可能會連續調用多個工具。詢問「西雅圖的天氣如何？我應該帶傘嗎？」並觀察它對 `getCurrentWeather` 的調用以及關於雨具的推理。

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.hk.png" alt="Tool Chaining" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*連續的工具呼叫 — 一個工具的輸出為下一步決策提供依據*

**優雅失敗** — 詢問模擬資料中沒有的城市天氣，工具會返回錯誤訊息，AI 會解釋無法提供協助。工具以安全方式失敗。

這發生在單一對話輪次中。代理自主協調多個工具調用。

## 執行應用程式

**確認部署：**

確保根目錄存在 `.env` 檔案並包含 Azure 憑證（於 Module 01 中建立）：
```bash
cat ../.env  # 應顯示 AZURE_OPENAI_ENDPOINT、API_KEY、DEPLOYMENT
```

**啟動應用：**

> **注意：** 若你已使用 Module 01 的 `./start-all.sh` 啟動所有應用程式，本模組已在 8084 埠運行。你可以跳過以下啟動指令，直接訪問 http://localhost:8084。

**選項 1：使用 Spring Boot 儀表板（推薦 VS Code 用戶）**

開發容器包含 Spring Boot 儀表板外掛，提供管理所有 Spring Boot 應用的視覺介面。你可以在 VS Code 左側活動欄找到它（尋找 Spring Boot 圖示）。

從 Spring Boot 儀表板你可以：
- 查看工作區中所有可用的 Spring Boot 應用
- 一鍵啟動/停止應用
- 即時查看應用日誌
- 監控應用狀態

只需點擊「tools」旁的播放按鈕即可啟動本模組，或一次啟動所有模組。

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.hk.png" alt="Spring Boot Dashboard" width="400"/>

**選項 2：使用 shell 指令**

啟動所有網頁應用（模組 01-04）：

**Bash:**
```bash
cd ..  # 從根目錄出發
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # 從根目錄開始
.\start-all.ps1
```

或只啟動本模組：

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

兩個腳本會自動從根目錄 `.env` 載入環境變數，並於 JAR 不存在時自動構建。

> **注意：** 如果你希望先手動編譯所有模組再啟動：
>
> **Bash:**
> ```bash
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```
>
> **PowerShell:**
> ```powershell
> cd ..  # Go to root directory
> mvn clean package -DskipTests
> ```

在瀏覽器開啟 http://localhost:8084 。

**停止應用：**

**Bash:**
```bash
./stop.sh  # 只有此模組
# 或者
cd .. && ./stop-all.sh  # 所有模組
```

**PowerShell:**
```powershell
.\stop.ps1  # 只有這個模組
# 或者
cd ..; .\stop-all.ps1  # 所有模組
```

## 使用應用程式

此應用提供一個網頁界面，讓你可以與擁有天氣和溫度換算工具的 AI 代理互動。

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.hk.png" alt="AI Agent Tools Interface" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI 代理工具介面 — 快速示範和對話接口以便與工具互動*

### 嘗試簡單的工具使用

先試試簡單請求：「將 100 華氏度轉換成攝氏度」。代理識別出需要溫度換算工具，用正確參數呼叫，並回傳結果。注意這過程很自然——你沒指定用哪個工具或如何呼叫。

### 測試工具鏈結

現在試試更複雜一點：「西雅圖的天氣如何？請幫我轉換成華氏度」。看代理如何分步完成。它先取得天氣（回傳攝氏度），再識別需要轉換成華氏度，呼叫換算工具，並合併兩個結果成一個回答。

### 查看對話流程

聊天介面會保留對話歷史，讓你進行多輪互動。你可以看到所有之前的查詢和回應，輕鬆追蹤對話，理解代理如何在多次交流中建立上下文。

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.hk.png" alt="Conversation with Multiple Tool Calls" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*多輪對話展示簡單換算、天氣查詢與工具鏈結*

### 嘗試不同的請求

試試不同組合：
- 天氣查詢：「東京的天氣如何？」
- 溫度換算：「25°C 是幾開爾文？」
- 組合查詢：「查詢巴黎的天氣，並告訴我是否超過 20°C」

注意代理如何理解自然語言，並對應到適當的工具調用。

## 關鍵概念

### ReAct 模式（推理與執行）

代理在推理（決定下一步）和執行（使用工具）間交替。此模式讓代理能自主解決問題，而非僅僅回應指令。

### 工具描述很重要

你的工具描述品質直接影響代理使用效果。清晰、具體的描述有助模型理解何時及如何呼叫每個工具。

### 會話管理

`@MemoryId` 注解啟用自動化基於會話的記憶管理。每個會話 ID 獲得自己由 `ChatMemoryProvider` bean 管理的 `ChatMemory` 實例，免除手動追蹤記憶的麻煩。

### 錯誤處理

工具可能失敗——API 超時、參數無效、外部服務宕機。生產環境代理需有錯誤處理能力，讓模型能說明問題或嘗試其他方案。

## 可用的工具

**天氣工具**（示範的模擬資料）：
- 取得指定地點的即時天氣
- 取得多日天氣預報

**溫度換算工具**：
- 攝氏轉華氏
- 華氏轉攝氏
- 攝氏轉開爾文
- 開爾文轉攝氏
- 華氏轉開爾文
- 開爾文轉華氏

這些只是簡單範例，但模式可擴展到任何函數：資料庫查詢、API 調用、計算、檔案操作或系統命令。

## 何時使用基於工具的代理

**使用工具時機：**
- 需要即時資料（天氣、股價、庫存）
- 需要執行簡單數學以外的計算
- 查詢資料庫或 API
- 執行行動（發郵件、創建票據、更新紀錄）
- 結合多個資料來源

**不適合使用工具情況：**
- 問題可由通用知識回答
- 回應純粹是對話交流
- 工具延遲會嚴重影響體驗速度

## 下一步

**下一模組：** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**導覽：** [← 上一章：Module 03 - RAG](../03-rag/README.md) | [返回主頁](../README.md) | [下一章：Module 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：  
本文件由AI翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 所翻譯。雖然我們力求準確，但自動翻譯可能包含錯誤或不準確之處。原始文件的母語版本應被視為權威來源。對於重要資訊，建議使用專業人工翻譯。我們不對因使用本翻譯所引起的任何誤解或誤譯承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->