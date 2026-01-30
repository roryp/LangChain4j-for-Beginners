# Module 00: 快速開始

## 目錄

- [簡介](../../../00-quick-start)
- [什麼是 LangChain4j？](../../../00-quick-start)
- [LangChain4j 依賴項](../../../00-quick-start)
- [前置條件](../../../00-quick-start)
- [設置](../../../00-quick-start)
  - [1. 取得你的 GitHub 令牌](../../../00-quick-start)
  - [2. 設置你的令牌](../../../00-quick-start)
- [執行範例](../../../00-quick-start)
  - [1. 基本聊天](../../../00-quick-start)
  - [2. 提示模式](../../../00-quick-start)
  - [3. 函數調用](../../../00-quick-start)
  - [4. 文件問答 (RAG)](../../../00-quick-start)
  - [5. 負責任的 AI](../../../00-quick-start)
- [每個範例展示什麼](../../../00-quick-start)
- [下一步](../../../00-quick-start)
- [故障排除](../../../00-quick-start)

## 簡介

此快速入門旨在讓你盡快開始使用 LangChain4j。涵蓋使用 LangChain4j 和 GitHub 模型構建 AI 應用程序的基本內容。在後續模組中，你將使用 Azure OpenAI 和 LangChain4j 構建更高級應用。

## 什麼是 LangChain4j？

LangChain4j 是一個 Java 庫，簡化構建 AI 驅動應用程序。你無需處理 HTTP 客戶端和 JSON 解析，只需使用乾淨的 Java API。

LangChain 中的「鏈」指的是串接多個組件——你可以鏈接提示到模型，再到解析器，或串接多個 AI 調用，使一個輸出成為下一個輸入。此快速入門專注於基礎，之後會探討更複雜的鏈。

<img src="../../../translated_images/zh-HK/langchain-concept.ad1fe6cf063515e1.webp" alt="LangChain4j Chaining Concept" width="800"/>

*LangChain4j 中的組件鏈結——積木相連建構強大的 AI 工作流程*

我們將使用三個核心組件：

**ChatLanguageModel** - AI 模型交互界面。呼叫 `model.chat("prompt")` 得到回覆字串。我們使用 `OpenAiOfficialChatModel`，可連接 OpenAI 兼容端點，如 GitHub 模型。

**AiServices** - 創建類型安全的 AI 服務介面。定義方法並用 `@Tool` 標註，LangChain4j 負責協調。AI 在需要時會自動呼叫你的 Java 方法。

**MessageWindowChatMemory** - 維持對話歷史。沒有它，每次請求相互獨立。有了它，AI 會記住之前訊息，保持多輪對話上下文。

<img src="../../../translated_images/zh-HK/architecture.eedc993a1c576839.webp" alt="LangChain4j Architecture" width="800"/>

*LangChain4j 架構 - 核心組件協同工作推動你的 AI 應用*

## LangChain4j 依賴項

此快速入門使用 [`pom.xml`](../../../00-quick-start/pom.xml) 內的兩個 Maven 依賴：

```xml
<!-- Core LangChain4j library -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>

<!-- OpenAI integration (works with GitHub Models) -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

`langchain4j-open-ai-official` 模組提供了 `OpenAiOfficialChatModel` 類，與 OpenAI 兼容 API 連接。GitHub 模型使用相同 API 格式，無需特殊適配器——只需將基本 URL 指向 `https://models.github.ai/inference`。

## 前置條件

**使用開發容器？** Java 和 Maven 已安裝。你只需一個 GitHub 個人存取令牌。

**本地開發：**
- Java 21 以上，Maven 3.9 以上
- GitHub 個人存取令牌（下方有說明）

> **注意：** 本模組使用 GitHub 模型的 `gpt-4.1-nano`。請勿修改程式碼中的模型名稱——它已配置為適用於 GitHub 可用模型。

## 設置

### 1. 取得你的 GitHub 令牌

1. 前往 [GitHub 設定 → 個人存取令牌](https://github.com/settings/personal-access-tokens)
2. 點擊「產生新令牌」
3. 設定描述名稱（例如：「LangChain4j Demo」）
4. 設定到期時間（建議 7 天）
5. 在「帳戶權限」中找到「Models」設定為「唯讀」
6. 點擊「產生令牌」
7. 複製並保存你的令牌——你將不會再次看到它

### 2. 設置你的令牌

**選項一：使用 VS Code（推薦）**

若使用 VS Code，將令牌加入專案根目錄 `.env` 檔案：

如 `.env` 檔不存在，可將 `.env.example` 複製為 `.env`，或直接在根目錄新建 `.env`。

**範例 `.env` 檔案：**
```bash
# 在 /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

然後只需在檔案總管中右鍵點擊任一範例檔（如 `BasicChatDemo.java`），選擇 **「執行 Java」**，或使用執行與偵錯面板的啟動設定。

**選項二：使用終端機**

設定令牌為環境變數：

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## 執行範例

**使用 VS Code：** 只需在檔案總管中右鍵點擊任何範例檔，選擇 **「執行 Java」**，或使用執行與偵錯面板的啟動設定（前提是你已先加令牌到 `.env`）。

**使用 Maven：** 或者你也可以從指令行執行：

### 1. 基本聊天

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. 提示模式

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

展示零次示例、少次示例、思路鏈和角色提示。

### 3. 函數調用

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

AI 在需要時會自動呼叫你的 Java 方法。

### 4. 文件問答 (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

針對 `document.txt` 內容提問。

### 5. 負責任的 AI

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

展示 AI 安全篩選如何阻擋有害內容。

## 每個範例展示什麼

**基本聊天** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

從這裡開始，了解 LangChain4j 的最簡用法。你會建立 `OpenAiOfficialChatModel`，使用 `.chat()` 傳送提示並獲得回應。展示如何初始化模型並設置自訂端點與 API 金鑰。一旦理解此模式，其他內容都基於此。

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) 聊天測試：** 打開 [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)，問：
> - 「如何將此程式碼中從 GitHub 模型切換到 Azure OpenAI？」
> - 「OpenAiOfficialChatModel.builder() 可以配置哪些其他參數？」
> - 「如何新增串流回應而非等待完整回應？」

**提示工程** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

了解如何與模型對話後，探究如何向其發送內容。此範例使用相同模型設定，演示四種提示模式。嘗試零次示例提示（直接指令）、少次示例提示（示例學習）、思路鏈提示（展現推理步驟）、角色提示（設定背景）。你將看到同一模型如何因表達不同，導致迥異結果。

```java
PromptTemplate template = PromptTemplate.from(
    "What's the best time to visit {{destination}} for {{activity}}?"
);

Prompt prompt = template.apply(Map.of(
    "destination", "Paris",
    "activity", "sightseeing"
));

String response = model.chat(prompt.text());
```

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) 聊天測試：** 打開 [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)，問：
> - 「零次示例和少次示例提示有何差異？何時該用哪一種？」
> - 「溫度參數如何影響模型的回復？」
> - 「實務上有哪些防止提示注入攻擊的技巧？」
> - 「如何為常用模式建立可重用的 PromptTemplate 物件？」

**工具整合** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

LangChain4j 強大的地方在這裡。你會使用 `AiServices` 創建可呼叫 Java 方法的 AI 助理。只需在方法上用 `@Tool("描述")` 標註，LangChain4j 負責其餘協調——AI 會根據用戶要求自動決定何時使用何工具。展示函數調用，打造能執行操作而非僅回答問題的 AI。

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) 聊天測試：** 打開 [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)，問：
> - 「@Tool 標註如何運作？LangChain4j 背後怎麼處理？」
> - 「AI 可以依序呼叫多個工具來解決複雜問題嗎？」
> - 「如果工具拋出例外，如何處理錯誤？」
> - 「如何整合真實 API，取代這個計算器範例？」

**文件問答 (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

展示 RAG（檢索增強生成）的基礎。你不依賴模型訓練資料，而是載入[`document.txt`](../../../00-quick-start/document.txt)內容並加入提示中。AI 根據文件回應，而非一般知識。是打造可使用自有資料系統的第一步。

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **注意：** 此簡單作法將整份文件加到提示中。若文件過大（＞10KB），會超出上下文限制。模組 03 將介紹拆塊和向量檢索，適用於生產級 RAG 系統。

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) 聊天測試：** 打開 [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)，問：
> - 「RAG 如何防止 AI 產生幻覺，相較只用模型訓練資料？」
> - 「這種簡單方法跟用向量嵌入檢索有何不同？」
> - 「如何擴充以處理多文件或更大知識庫？」
> - 「組織提示結構有何最佳實務，確保 AI 僅使用提供的上下文？」

**負責任的 AI** - [ResponsibleAIDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)

建立多層防護的 AI 安全。此範例展示兩層保護協同運作：

**第一部分：LangChain4j 輸入防護** - 在送達 LLM 前封鎖危險提示。打造自訂防護門檻，檢查禁用關鍵詞或模式。此段在你的程式碼執行，快速且免費。

```java
class DangerousContentGuardrail implements InputGuardrail {
    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        String text = userMessage.singleText().toLowerCase();
        if (text.contains("explosives")) {
            return fatal("Blocked: contains prohibited keyword");
        }
        return success();
    }
}
```

**第二部分：提供者安全篩選** - GitHub 模型內建過濾，補足防護門檻可能遺漏部分。看到嚴重違規時的硬阻擋（HTTP 400 錯誤）和 AI 禮貌拒絕的軟拒絕。

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) 聊天測試：** 打開 [`ResponsibleAIDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)，問：
> - 「什麼是 InputGuardrail？怎麼創建自訂防護門檻？」
> - 「硬阻擋和軟拒絕有何差異？」
> - 「為什麼要同時使用防護門檻和提供者過濾？」

## 下一步

**下一模組：** [01-introduction - LangChain4j 與 Azure 上的 gpt-5 入門](../01-introduction/README.md)

---

**導覽：** [← 返回主頁](../README.md) | [下一章：Module 01 - 介紹 →](../01-introduction/README.md)

---

## 故障排除

### 首次 Maven 建置

**問題：** 初次執行 `mvn clean compile` 或 `mvn package` 時花很久（10-15 分鐘）

**原因：** Maven 需首次下載全部專案依賴（Spring Boot、LangChain4j 函式庫、Azure SDK 等）

**解決辦法：** 這是正常現象。後續建置會快許多，因依賴已緩存。下載速度視網路而定。

### PowerShell Maven 指令語法

**問題：** Maven 命令執行失敗，錯誤訊息 `Unknown lifecycle phase ".mainClass=..."`

**原因：** PowerShell 將 `=` 解讀為變數賦值運算符，導致 Maven 屬性語法錯誤
**解決方案**：在 Maven 命令前使用停止解析運算子 `--%`：

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

`--%` 運算子告訴 PowerShell 將所有剩餘參數原封不動地傳遞給 Maven，不做任何解釋。

### Windows PowerShell 表情符號顯示

**問題**：AI 回應在 PowerShell 中顯示亂碼（例如 `????` 或 `â??`）而不是表情符號

**原因**：PowerShell 預設編碼不支援 UTF-8 表情符號

**解決方案**：在執行 Java 應用程式前執行此命令：
```cmd
chcp 65001
```

這會強制終端機使用 UTF-8 編碼。或者，使用 Windows Terminal，它對 Unicode 有更佳的支援。

### 調試 API 呼叫

**問題**：AI 模型出現身份驗證錯誤、速率限制或意外回應

**解決方案**：範例中包含 `.logRequests(true)` 和 `.logResponses(true)`，可在主控台顯示 API 呼叫，幫助排查身份驗證錯誤、速率限制或意外回應。正式運行時請移除這些標誌以減少日誌干擾。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：  
本文件由 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們努力確保準確性，但請注意，自動翻譯可能包含錯誤或不準確之處。原始文件的原文版本應被視為權威資料。對於重要資訊，建議採用專業人工翻譯。我們對因使用本翻譯而引起的任何誤解或曲解概不負責。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->