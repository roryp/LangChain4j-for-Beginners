<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-30T21:35:14+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "hk"
}
-->
# Module 04: AI Agents with Tools

## Table of Contents

- [你將會學到什麼](../../../04-tools)
- [先決條件](../../../04-tools)
- [了解具備工具的 AI 代理](../../../04-tools)
- [工具呼叫如何運作](../../../04-tools)
  - [工具定義](../../../04-tools)
  - [決策制定](../../../04-tools)
  - [執行](../../../04-tools)
  - [回應生成](../../../04-tools)
- [工具串聯](../../../04-tools)
- [執行應用程式](../../../04-tools)
- [使用應用程式](../../../04-tools)
  - [試試簡單的工具使用](../../../04-tools)
  - [測試工具串聯](../../../04-tools)
  - [查看對話流程](../../../04-tools)
  - [觀察推理過程](../../../04-tools)
  - [嘗試不同的請求](../../../04-tools)
- [關鍵概念](../../../04-tools)
  - [ReAct 模式（推理與行動）](../../../04-tools)
  - [工具描述很重要](../../../04-tools)
  - [會話管理](../../../04-tools)
  - [錯誤處理](../../../04-tools)
- [可用工具](../../../04-tools)
- [何時使用基於工具的代理](../../../04-tools)
- [下一步](../../../04-tools)

## What You'll Learn

到目前為止，你已經學會如何與 AI 對話、有效地構建 prompts，以及在你的文件中紮根回應。但仍然有一個根本限制：語言模型只能產生文字。它們無法查看天氣、執行計算、查詢資料庫或與外部系統互動。

工具改變了這一點。透過給模型可以呼叫的函式，你把它從一個文字生成器轉變為可以採取行動的代理。模型決定何時需要工具、使用哪個工具以及傳遞哪些參數。你的程式碼執行該函式並返回結果。模型將該結果納入其回應中。

## Prerequisites

- 已完成 Module 01（已部署 Azure OpenAI 資源）
- 專案根目錄有 `.env` 檔案且包含 Azure 憑證（由 Module 01 的 `azd up` 建立）

> **注意：** 如果你尚未完成 Module 01，請先按照那裡的部署說明操作。

## Understanding AI Agents with Tools

> **📝 注意：** 本模組中「代理（agents）」一詞指的是具備工具呼叫能力的 AI 助手。這與我們會在 [Module 05: MCP](../05-mcp/README.md) 中討論的 **Agentic AI** 模式（具有規劃、記憶和多步推理的自主代理）不同。

具備工具的 AI 代理遵循一個推理與行動的模式（ReAct）：

1. 使用者提出問題
2. 代理推理它需要知道什麼
3. 代理決定是否需要工具來回答
4. 若需要，代理以正確的參數呼叫適當的工具
5. 工具執行並返回資料
6. 代理整合結果並提供最終答案

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.hk.png" alt="ReAct 模式" width="800"/>

*ReAct 模式 - AI 代理如何在推理與行動之間交替來解決問題*

這個過程是自動發生的。你定義工具及其描述。模型會負責何時以及如何使用它們的決策。

## How Tool Calling Works

**工具定義** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

你定義帶有清晰描述和參數規格的函式。模型會在其 system prompt 中看到這些描述，並理解每個工具的功能。

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // 你嘅天氣查詢邏輯
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// 助理由 Spring Boot 自動注入以下項目：
// - ChatModel 的 bean
// - 來自 @Component 類別的所有 @Tool 方法
// - 用於會話管理的 ChatMemoryProvider
```

> **🤖 嘗試使用 [GitHub Copilot](https://github.com/features/copilot) Chat：** 打開 [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) 並詢問：
> - 「我如何整合像 OpenWeatherMap 這類的真實天氣 API，而不是使用模擬資料？」
> - 「有助於 AI 正確使用工具的良好工具描述包含哪些要素？」
> - 「我應該如何在工具實作中處理 API 錯誤和速率限制？」

**決策制定**

當使用者問「西雅圖的天氣如何？」時，模型會識別它需要天氣工具。它會生成一個函式呼叫，並將位置參數設為「Seattle」。

**執行** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot 會自動注入所有已註冊工具到宣告式的 `@AiService` 介面，LangChain4j 會自動執行工具呼叫。

> **🤖 嘗試使用 [GitHub Copilot](https://github.com/features/copilot) Chat：** 打開 [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) 並詢問：
> - 「ReAct 模式如何運作，為什麼它對 AI 代理有效？」
> - 「代理如何決定使用哪個工具以及使用的順序？」
> - 「如果工具執行失敗會發生什麼 - 我應該如何健全地處理錯誤？」

**回應生成**

模型接收天氣資料並將其格式化為自然語言回應給使用者。

### 為什麼使用宣告式 AI 服務？

本模組使用 LangChain4j 的 Spring Boot 整合與宣告式 `@AiService` 介面：

- **Spring Boot 自動注入** - ChatModel 和工具會自動注入
- **@MemoryId 模式** - 自動的基於會話的記憶管理
- **單一實例** - 助手只建立一次並重複使用以提升效能
- **型別安全的執行** - 直接呼叫 Java 方法並進行型別轉換
- **多回合協調** - 自動處理工具串聯
- **零樣板程式碼** - 不需要手動呼叫 AiServices.builder() 或使用記憶 HashMap

替代方法（手動 `AiServices.builder()`）需要更多程式碼，並會錯過 Spring Boot 整合的好處。

## Tool Chaining

**工具串聯** - AI 可能會依序呼叫多個工具。問「西雅圖的天氣如何，我應該帶雨傘嗎？」並觀察它如何將 `getCurrentWeather` 與關於雨具的推理串聯起來。

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.hk.png" alt="工具串聯" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*連續的工具呼叫 - 一個工具的輸出會成為下一個決策的輸入*

**優雅失敗** - 試著查詢不存在於模擬資料中的城市天氣。工具會返回錯誤訊息，而 AI 會解釋它無法提供協助。工具會安全地失敗。

這些都發生在單一對話回合中。代理會自動協調多個工具呼叫。

## Run the Application

**驗證部署：**

確保專案根目錄有 `.env` 檔案且包含 Azure 憑證（在 Module 01 建置期間建立）：
```bash
cat ../.env  # 應該顯示 AZURE_OPENAI_ENDPOINT、API_KEY、DEPLOYMENT
```

**啟動應用程式：**

> **注意：** 如果你已經在 Module 01 使用 `./start-all.sh` 啟動了所有應用程式，這個模組已經在 8084 埠上執行。你可以略過下方的啟動指令，直接前往 http://localhost:8084。

**選項 1：使用 Spring Boot Dashboard（建議 VS Code 使用者）**

開發容器已包含 Spring Boot Dashboard 擴充功能，提供一個可視化介面來管理所有 Spring Boot 應用程式。你可以在 VS Code 左側的活動列找到它（找 Spring Boot 圖示）。

從 Spring Boot Dashboard，你可以：
- 查看工作區中所有可用的 Spring Boot 應用程式
- 一鍵啟動/停止應用程式
- 即時檢視應用程式日誌
- 監控應用程式狀態

只需點擊 "tools" 旁的播放按鈕來啟動此模組，或一次啟動所有模組。

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.hk.png" alt="Spring Boot Dashboard" width="400"/>

**選項 2：使用 shell 腳本**

啟動所有 Web 應用程式（模組 01-04）：

**Bash:**
```bash
cd ..  # 從根目錄
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # 由根目錄
.\start-all.ps1
```

或僅啟動此模組：

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

這兩個腳本會自動從根目錄的 `.env` 檔案載入環境變數，並在 JAR 不存在時進行建置。

> **注意：** 如果你想在啟動前手動建置所有模組：
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

在瀏覽器中打開 http://localhost:8084。

**要停止：**

**Bash:**
```bash
./stop.sh  # 只限本模組
# 或
cd .. && ./stop-all.sh  # 所有模組
```

**PowerShell:**
```powershell
.\stop.ps1  # 只限此模組
# 或
cd ..; .\stop-all.ps1  # 所有模組
```

## Using the Application

該應用程式提供一個網頁介面，你可以在那裡與有權限使用天氣和溫度轉換工具的 AI 代理互動。

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.hk.png" alt="AI 代理工具介面" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI 代理工具介面 - 供互動的快速範例與聊天介面*

**試試簡單的工具使用**

從一個簡單請求開始：「將 100 華氏度轉換為攝氏」。代理會識別出它需要溫度轉換工具，使用正確的參數呼叫該工具，並返回結果。注意這感覺多麼自然 — 你不需要指定使用哪個工具或如何呼叫它。

**測試工具串聯**

現在試試更複雜的請求：「西雅圖的天氣如何並請幫我轉換成華氏？」觀察代理如何分步處理。它會先取得天氣（會返回攝氏），然後辨識出需要轉換成華氏，呼叫轉換工具，並將兩者結果組合成一個回應。

**查看對話流程**

聊天介面會保留對話歷史，讓你可以進行多回合互動。你可以看到所有先前的查詢與回應，方便追蹤對話並了解代理如何在多次交流中建立上下文。

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.hk.png" alt="多次工具呼叫的對話" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*多回合對話示範簡單轉換、天氣查詢和工具串聯*

**嘗試不同的請求**

試試各種組合：
- 天氣查詢：「東京的天氣如何？」
- 溫度轉換：「25°C 等於多少開爾文？」
- 組合查詢：「檢查巴黎的天氣並告訴我是否高於 20°C」

注意代理如何將自然語言映射到適當的工具呼叫。

## Key Concepts

**ReAct Pattern (Reasoning and Acting)**

代理在推理（決定要做什麼）和行動（使用工具）之間交替。這種模式使其能夠自主解決問題，而不僅僅是回應指令。

**工具描述很重要**

工具描述的品質直接影響代理使用工具的效果。清晰、具體的描述能幫助模型了解何時以及如何呼叫每個工具。

**會話管理**

`@MemoryId` 註解啟用自動的基於會話的記憶管理。每個會話 ID 都會有自己的 `ChatMemory` 實例，由 `ChatMemoryProvider` bean 管理，無需手動追蹤記憶。

**錯誤處理**

工具可能會失敗 — API 超時、參數可能無效、外部服務可能中斷。生產環境中的代理需要錯誤處理，以便模型可以解釋問題或嘗試替代方案。

## Available Tools

**天氣工具**（示範用的模擬資料）：
- 取得某地的即時天氣
- 取得多天預報

**溫度轉換工具**：
- 攝氏轉華氏
- 華氏轉攝氏
- 攝氏轉開爾文
- 開爾文轉攝氏
- 華氏轉開爾文
- 開爾文轉華氏

這些是簡單範例，但這個模式可以擴展到任何函式：資料庫查詢、API 呼叫、計算、檔案操作或系統指令。

## When to Use Tool-Based Agents

**使用工具的時候：**
- 回答需要即時資料（天氣、股價、庫存）
- 需要執行超出簡單數學的計算
- 訪問資料庫或 API
- 執行動作（發送郵件、建立工單、更新紀錄）
- 結合多個資料來源

**不要使用工具的時候：**
- 問題可以從一般知識回答
- 回應純屬對話性質
- 工具延遲會讓體驗變得過慢

## Next Steps

**下一模組：** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**導覽：** [← 上一章：Module 03 - RAG](../03-rag/README.md) | [返回主頁](../README.md) | [下一章：Module 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免責聲明：
本文件經由 AI 翻譯服務「Co-op Translator」(https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們力求準確，但請注意，自動翻譯可能包含錯誤或不準確之處。原始語言的文件應視為權威來源。對於重要資訊，建議採用專業人工翻譯。我們不對因使用此翻譯而引致的任何誤解或誤譯承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->