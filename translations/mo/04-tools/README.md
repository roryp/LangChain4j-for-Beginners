<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-30T21:20:31+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "mo"
}
-->
# Module 04: 可使用工具的 AI 代理

## 目錄

- [你會學到什麼](../../../04-tools)
- [先決條件](../../../04-tools)
- [理解可使用工具的 AI 代理](../../../04-tools)
- [工具呼叫如何運作](../../../04-tools)
  - [工具定義](../../../04-tools)
  - [決策製定](../../../04-tools)
  - [執行](../../../04-tools)
  - [回應生成](../../../04-tools)
- [工具串聯](../../../04-tools)
- [執行應用程式](../../../04-tools)
- [使用應用程式](../../../04-tools)
  - [嘗試簡單的工具使用](../../../04-tools)
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

## 你會學到什麼

到目前為止，你已經學會如何與 AI 對話、有效地結構化提示，並在你的文件中紮根回應。但仍有一個根本限制：語言模型只能生成文字。它們無法檢查天氣、執行計算、查詢資料庫或與外部系統互動。

工具改變了這一點。透過讓模型能夠呼叫函數，你可以將它從文字生成器轉變為可以採取行動的代理。模型決定何時需要工具、使用哪個工具，以及要傳遞哪些參數。你的程式碼執行該函數並回傳結果。模型將該結果納入其回應中。

## 先決條件

- 已完成 Module 01（已部署 Azure OpenAI 資源）
- 根目錄中有包含 Azure 憑證的 `.env` 檔案（由 Module 01 的 `azd up` 建立）

> **注意：** 如果你尚未完成 Module 01，請先依照那裡的部署說明進行。

## 理解可使用工具的 AI 代理

> **📝 注意：** 本模組中所稱的「代理」是指具備工具呼叫功能的 AI 助手。這與我們在 [Module 05: MCP](../05-mcp/README.md) 中將介紹的 **Agentic AI** 範式（具備規劃、記憶和多步驟推理的自主代理）不同。

可使用工具的 AI 代理遵循一個推理與行動模式（ReAct）：

1. 使用者提出問題
2. 代理思考它需要知道的資訊
3. 代理決定是否需要工具來回答
4. 如果需要，代理以正確的參數呼叫適當的工具
5. 工具執行並回傳資料
6. 代理整合結果並提供最終答案

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.mo.png" alt="ReAct 模式" width="800"/>

*ReAct 模式 - AI 代理如何在推理與行動之間交替以解決問題*

這個流程是自動發生的。你定義工具及其描述。模型負責決定何時以及如何使用它們。

## 工具呼叫如何運作

**工具定義** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

你以清晰的描述和參數規格定義函數。模型在其系統提示中看到這些描述，並理解每個工具的功能。

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

// 助理會由 Spring Boot 自動注入下列項目：
// - ChatModel Bean
// - 來自 @Component 類別的所有 @Tool 方法
// - 用於會話管理的 ChatMemoryProvider
```

> **🤖 用 [GitHub Copilot](https://github.com/features/copilot) 聊天嘗試：** 打開 [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) 並問：
> - 「我該如何整合像 OpenWeatherMap 這樣的真實天氣 API，而不是使用模擬資料？」
> - 「什麼樣的工具描述能幫助 AI 正確使用它？」
> - 「我該如何在工具實作中處理 API 錯誤和速率限制？」

**決策製定**

當使用者問「西雅圖現在的天氣如何？」時，模型會識別它需要天氣工具。它會產生一個函數呼叫，並將位置參數設定為 "Seattle"。

**執行** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot 自動注入所有已註冊工具的宣告式 `@AiService` 介面，LangChain4j 會自動執行工具呼叫。

> **🤖 用 [GitHub Copilot](https://github.com/features/copilot) 聊天嘗試：** 打開 [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) 並問：
> - 「ReAct 模式如何運作，為什麼對 AI 代理有效？」
> - 「代理如何決定使用哪個工具以及以何種順序使用？」
> - 「如果工具執行失敗會發生什麼事——我該如何穩健地處理錯誤？」

**回應生成**

模型接收天氣資料並將其格式化為自然語言的回應提供給使用者。

### 為什麼使用宣告式 AI 服務？

本模組使用 LangChain4j 與 Spring Boot 的整合，採用宣告式的 `@AiService` 介面：

- **Spring Boot 自動注入** - ChatModel 和工具自動注入
- **@MemoryId 模式** - 自動的會話型記憶管理
- **單一實例** - 助手只建立一次並重複使用以提升效能
- **型別安全執行** - 直接以 Java 方法呼叫並進行型別轉換
- **多回合協調** - 自動處理工具串聯
- **零樣板碼** - 無需手動呼叫 AiServices.builder() 或管理記憶的 HashMap

替代方法（手動 `AiServices.builder()`）需要更多程式碼，且不能享有 Spring Boot 整合的好處。

## 工具串聯

**工具串聯** - AI 可能會依序呼叫多個工具。詢問「西雅圖的天氣如何，我應該帶傘嗎？」並觀察它如何串聯 `getCurrentWeather`，同時就雨具進行推理。

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.mo.png" alt="工具串聯" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*連續的工具呼叫 - 一個工具的輸出會成為下一個決策的輸入*

**優雅失敗處理** - 試著查詢不在模擬資料中的城市天氣。工具會回傳錯誤訊息，AI 會解釋它無法提供幫助。工具會安全地失敗。

這些都會在單一對話回合中發生。代理會自動協調多個工具呼叫。

## 執行應用程式

**驗證部署：**

確認根目錄中存在包含 Azure 憑證的 `.env` 檔案（在 Module 01 期間建立）：
```bash
cat ../.env  # 應該顯示 AZURE_OPENAI_ENDPOINT、API_KEY、DEPLOYMENT
```

**啟動應用程式：**

> **注意：** 如果你已經在 Module 01 使用 `./start-all.sh` 啟動所有應用程式，這個模組已在 8084 埠上執行。你可以跳過下列啟動命令，直接前往 http://localhost:8084。

**選項 1：使用 Spring Boot 儀表板（建議給 VS Code 使用者）**

開發容器包含 Spring Boot Dashboard 擴充套件，提供視覺化介面來管理所有 Spring Boot 應用程式。你可以在 VS Code 左側的活動列中找到它（尋找 Spring Boot 圖示）。

在 Spring Boot 儀表板中，你可以：
- 檢視工作區中所有可用的 Spring Boot 應用程式
- 一鍵啟動/停止應用程式
- 即時查看應用程式日誌
- 監控應用程式狀態

只要點擊 "tools" 旁的播放按鈕即可啟動此模組，或一次啟動所有模組。

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.mo.png" alt="Spring Boot 儀表板" width="400"/>

**選項 2：使用 shell 腳本**

啟動所有 Web 應用程式（模組 01-04）：

**Bash:**
```bash
cd ..  # 從根目錄
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # 從根目錄
.\start-all.ps1
```

或只啟動此模組：

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

這兩個腳本會自動從根目錄的 `.env` 檔案載入環境變數，並在 JAR 檔案不存在時建立它們。

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

在瀏覽器中開啟 http://localhost:8084 。

**停止：**

**Bash:**
```bash
./stop.sh  # 僅限此模組
# 或
cd .. && ./stop-all.sh  # 所有模組
```

**PowerShell:**
```powershell
.\stop.ps1  # 僅限此模組
# 或
cd ..; .\stop-all.ps1  # 所有模組
```

## 使用應用程式

此應用程式提供一個網頁介面，你可以在其中與具有天氣與溫度轉換工具的 AI 代理互動。

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.mo.png" alt="AI 代理工具介面" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*AI 代理工具介面 - 提供快速範例與用於與工具互動的聊天介面*

**嘗試簡單的工具使用**

從簡單的請求開始：「將 100 華氏度轉換為攝氏度」。代理會識別需要使用溫度轉換工具，並以正確的參數呼叫它，然後回傳結果。注意這感覺有多自然——你不需要指定要使用哪個工具或如何呼叫它。

**測試工具串聯**

現在試試更複雜的：「西雅圖的天氣如何，並把它轉換為華氏？」觀察代理如何分步執行。它會先取得天氣（回傳攝氏），接著判斷需要轉換為華氏，呼叫轉換工具，並將兩者結果合併為一個回應。

**查看對話流程**

聊天介面會保留對話歷史，允許你進行多回合互動。你可以查看所有先前的查詢和回應，方便追蹤對話並理解代理如何在多次交換中建立上下文。

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.mo.png" alt="包含多次工具呼叫的對話" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*多回合對話顯示簡單轉換、天氣查詢與工具串聯*

**嘗試不同的請求**

試試各種組合：
- 天氣查詢：「東京的天氣如何？」
- 溫度轉換：「25°C 等於多少開爾文？」
- 組合查詢：「檢查巴黎的天氣，告訴我是否高於 20°C」

注意代理如何將自然語言映射到適當的工具呼叫。

## 關鍵概念

**ReAct 模式（推理與行動）**

代理在推理（決定要做什麼）與行動（使用工具）之間交替。這種模式使其能夠自主解決問題，而不僅僅是回應指令。

**工具描述很重要**

工具描述的品質會直接影響代理使用工具的效果。清晰、具體的描述能幫助模型理解何時以及如何呼叫每個工具。

**會話管理**

`@MemoryId` 註解啟用自動的會話型記憶管理。每個會話 ID 都會有自己的 `ChatMemory` 實例，由 `ChatMemoryProvider` bean 管理，無需手動追蹤記憶。

**錯誤處理**

工具可能會失敗——API 超時、參數可能無效、外部服務宕機。生產環境的代理需要錯誤處理，以便模型可以解釋問題或嘗試替代方案。

## 可用工具

**天氣工具**（示範用模擬資料）：
- 取得某地的即時天氣
- 取得多日天氣預報

**溫度轉換工具**：
- 攝氏轉華氏
- 華氏轉攝氏
- 攝氏轉開爾文
- 開爾文轉攝氏
- 華氏轉開爾文
- 開爾文轉華氏

這些都是簡單範例，但此模式可擴展到任何功能：資料庫查詢、API 呼叫、計算、檔案操作或系統指令。

## 何時使用基於工具的代理

**在以下情況使用工具：**
- 回答需要即時資料（天氣、股價、庫存）
- 需要執行超出簡單數學的計算
- 存取資料庫或 API
- 採取行動（發送電子郵件、建立工單、更新記錄）
- 結合多個資料來源

**不使用工具的情況：**
- 問題可以從一般知識中回答
- 回應純屬對話性質
- 工具的延遲會讓體驗變得過慢

## 下一步

**下一模組：** [05-mcp - 模型上下文協議 (MCP)](../05-mcp/README.md)

---

**導覽：** [← 上一章：Module 03 - RAG](../03-rag/README.md) | [回到主目錄](../README.md) | [下一章：Module 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免責聲明：
本文件係使用 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。儘管我們力求準確，敬請注意，自動翻譯可能包含錯誤或不準確之處。原始文件之原文版本應被視為具權威性的來源。對於重要資訊，建議採用專業人工翻譯。我們不對因使用此翻譯而產生的任何誤解或曲解承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->