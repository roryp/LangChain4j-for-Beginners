<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "22b5d7c8d7585325e38b37fd29eafe25",
  "translation_date": "2026-01-05T22:08:14+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "tw"
}
-->
# Module 00: 快速開始

## 目錄

- [介紹](../../../00-quick-start)
- [什麼是 LangChain4j？](../../../00-quick-start)
- [LangChain4j 依賴](../../../00-quick-start)
- [先決條件](../../../00-quick-start)
- [設定](../../../00-quick-start)
  - [1. 取得您的 GitHub 令牌](../../../00-quick-start)
  - [2. 設定您的令牌](../../../00-quick-start)
- [執行範例](../../../00-quick-start)
  - [1. 基本聊天](../../../00-quick-start)
  - [2. 提示模式](../../../00-quick-start)
  - [3. 函數呼叫](../../../00-quick-start)
  - [4. 文件問答 (RAG)](../../../00-quick-start)
  - [5. 負責任的 AI](../../../00-quick-start)
- [每個範例說明](../../../00-quick-start)
- [下一步](../../../00-quick-start)
- [疑難排解](../../../00-quick-start)

## 介紹

此快速開始旨在讓您盡快使用 LangChain4j 並運行起來。涵蓋了使用 LangChain4j 與 GitHub Models 建立 AI 應用的基本概念。在後續模組中，您將使用 Azure OpenAI 與 LangChain4j 建構更進階的應用程式。

## 什麼是 LangChain4j？

LangChain4j 是一個 Java 函式庫，簡化了建立 AI 驅動應用的過程。您不再需要處理 HTTP 用戶端或 JSON 解析，而能使用乾淨的 Java API。

LangChain 中的「chain」指的是將多個元件串接起來——您可以將提示串接給模型再串接給解析器，或是將多個 AI 呼叫相互串連，一個輸出作為下一個輸入。本快速開始專注於基礎，稍後將探索更複雜的鏈結。

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1.tw.png" alt="LangChain4j Chaining Concept" width="800"/>

*LangChain4j 中的元件鏈結方式 - 組建強大 AI 工作流程的積木*

我們將使用三個核心元件：

**ChatLanguageModel** — AI 模型互動的介面。呼叫 `model.chat("prompt")` 並取得回應字串。我們使用 `OpenAiOfficialChatModel`，該模型可與 OpenAI 兼容的端點（如 GitHub Models）一起使用。

**AiServices** — 建立類型安全的 AI 服務介面。定義方法並使用 `@Tool` 標註，LangChain4j 會負責協調。AI 在需要時自動呼叫您的 Java 方法。

**MessageWindowChatMemory** — 維護對話歷史。沒有這個，每次請求都是獨立的；有了它，AI 會記得之前的訊息並保持多輪對話上下文。

<img src="../../../translated_images/architecture.eedc993a1c576839.tw.png" alt="LangChain4j Architecture" width="800"/>

*LangChain4j 架構 - 核心元件協同合作為您的 AI 應用提供動力*

## LangChain4j 依賴

此快速開始在 [`pom.xml`](../../../00-quick-start/pom.xml) 使用兩個 Maven 依賴：

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

`langchain4j-open-ai-official` 模組提供了 `OpenAiOfficialChatModel` 類別，可連接到與 OpenAI 兼容的 API。GitHub Models 使用相同的 API 格式，因此不需特殊轉接器 — 只需將基底 URL 指向 `https://models.github.ai/inference` 即可。

## 先決條件

**使用 Dev Container？** Java 與 Maven 已安裝。您只需要一個 GitHub 個人訪問令牌。

**本地開發：**
- Java 21+，Maven 3.9+
- GitHub 個人訪問令牌（以下說明）

> **注意：** 此模組使用 GitHub Models 的 `gpt-4.1-nano`。請勿修改程式碼中的模型名稱 — 已配置為支援 GitHub 可用模型。

## 設定

### 1. 取得您的 GitHub 令牌

1. 前往 [GitHub 設定 → 個人訪問令牌](https://github.com/settings/personal-access-tokens)
2. 點擊「Generate new token」生成新令牌
3. 設定描述名稱（例如「LangChain4j 演示」）
4. 設定過期時間（建議 7 天）
5. 在「帳戶權限」中找到「Models」並設定為「僅讀」
6. 點擊「Generate token」
7. 複製並保存令牌 — 之後將無法再次看到

### 2. 設定您的令牌

**選項 1：使用 VS Code（推薦）**

若您使用 VS Code，將令牌加入專案根目錄的 `.env` 檔案：

若不存在 `.env`，請複製 `.env.example` 為 `.env`，或自行建立 `.env` 檔案。

**範例 `.env` 檔案：**
```bash
# 在 /workspaces/LangChain4j-for-Beginners/.env 中
GITHUB_TOKEN=your_token_here
```

之後，您可以在檔案總管中對任何範例檔（如 `BasicChatDemo.java`）按右鍵並選擇 **「Run Java」**，或從執行與除錯面板使用啟動設定。

**選項 2：使用終端機**

將令牌設定為環境變數：

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## 執行範例

**使用 VS Code：** 直接在檔案總管中對任何範例檔按右鍵並選擇 **「Run Java」**，或從執行與除錯面板使用啟動設定（請先確定令牌已加入 `.env`）。

**使用 Maven：** 也可以從命令列執行：

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

展示零次示範、少次示範、思維鏈、以及角色基礎提示。

### 3. 函數呼叫

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

當需要時，AI 會自動呼叫您的 Java 方法。

### 4. 文件問答 (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

針對 `document.txt` 中的內容提出問題。

### 5. 負責任的 AI

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ResponsibleAIDemo
```

查看 AI 安全過濾器如何阻擋有害內容。

## 每個範例說明

**基本聊天** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

從這裡開始，了解 LangChain4j 最簡單的用法。您會建立一個 `OpenAiOfficialChatModel`，用 `.chat()` 傳送提示並取得回應。展示如何使用自訂端點與 API 金鑰初始化模型的基礎。理解此模式後，其他功能都能建立在此之上。

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) Chat 試試看：** 打開 [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) 並問：
> - 「我如何在此程式碼中從 GitHub Models 切換到 Azure OpenAI？」
> - 「OpenAiOfficialChatModel.builder() 還能配置哪些參數？」
> - 「如何新增串流回應，而不必等待完整回應？」

**提示工程** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

現在您知道如何與模型溝通，讓我們探索提示的撰寫方式。此範例使用相同的模型設置，但展示四種不同的提示模式。嘗試零次示範直接指令、少次示範透過範例學習、思維鏈揭露推理步驟，以及角色基礎設定上下文。您將看到相同模型根據提示方式，產生截然不同的結果。

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

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) Chat 試試看：** 打開 [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) 並問：
> - 「零次示範和少次示範提示有何不同？何時該使用哪一種？」
> - 「溫度參數如何影響模型回應？」
> - 「有哪些技術可防止生產環境中提示注入攻擊？」
> - 「如何建立可重用的 PromptTemplate 物件以應用常見模式？」

**工具整合** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

這使 LangChain4j 變得強大。您將使用 `AiServices` 建立一個 AI 助手，可呼叫您的 Java 方法。只需用 `@Tool("描述")` 標註方法，LangChain4j 會自動協調—AI 根據用戶詢問，自動決定何時使用每個工具。這展示了函數呼叫，是構建能採取行動而非僅回答問題的 AI 的關鍵技術。

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) Chat 試試看：** 打開 [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) 並問：
> - 「@Tool 標註是如何工作的？LangChain4j 背後做了什麼？」
> - 「AI 能否連續呼叫多個工具來解決複雜問題？」
> - 「工具發生例外時該如何處理錯誤？」
> - 「如何整合真實 API 來替代此計算機範例？」

**文件問答 (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

此處展示 RAG（檢索增強生成）的基礎。您不用完全依賴模型訓練資料，而是將 [`document.txt`](../../../00-quick-start/document.txt) 的內容加載並放入提示中。AI 根據您的文件回應，而非其一般知識。這是打造能操作自己數據系統的第一步。

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **注意：** 此簡易方法將整份文件載入提示。若檔案過大（>10KB），會超過上下文限制。模組 03 將介紹分塊與向量搜尋用於生產級 RAG 系統。

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) Chat 試試看：** 打開 [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) 並問：
> - 「RAG 如何比使用模型訓練資料更防止 AI 產生幻覺？」
> - 「此簡單方法與使用向量嵌入進行檢索有何差異？」
> - 「如何擴展此系統以處理多份文件或更大型知識庫？」
> - 「提示結構有何最佳實踐，確保 AI 僅使用提供的上下文？」

**負責任的 AI** - [ResponsibleAIDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java)

建構多層防禦的 AI 安全。此範例展示兩層保護：

**第一部分：LangChain4j 輸入護欄** — 在送入大型語言模型前阻擋危險提示。建立自訂護欄以檢查禁用關鍵字或模式。這些在您的程式碼中執行，速度快且免費。

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

**第二部分：供應商安全過濾器** — GitHub Models 內建過濾器捕捉護欄可能漏掉的內容。您會看到嚴重違規的硬性阻擋（HTTP 400 錯誤）和 AI 禮貌拒絕的軟性拒絕。

> **🤖 使用 [GitHub Copilot](https://github.com/features/copilot) Chat 試試看：** 打開 [`ResponsibleAIDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ResponsibleAIDemo.java) 並問：
> - 「什麼是 InputGuardrail？如何自己建立？」
> - 「硬性阻擋與軟性拒絕有何不同？」
> - 「為什麼同時使用護欄與供應商過濾器？」

## 下一步

**下一模組：** [01-introduction - 使用 LangChain4j 與 Azure 上的 gpt-5 入門](../01-introduction/README.md)

---

**導覽：** [← 返回主頁](../README.md) | [下一頁：Module 01 - 介紹 →](../01-introduction/README.md)

---

## 疑難排解

### 初次 Maven 建置

**問題：** 初次執行 `mvn clean compile` 或 `mvn package` 時需時很長（約 10-15 分鐘）

**原因：** Maven 需要在第一次建置時下載所有專案依賴（Spring Boot、LangChain4j 函式庫、Azure SDK 等）。

**解決方案：** 這屬正常行為，依賴套件後續會快取在本機。下載速度取決於您的網路速度。

### PowerShell Maven 指令語法

**問題：** Maven 指令失敗並顯示錯誤 `Unknown lifecycle phase ".mainClass=..."`

**原因：** PowerShell 將 `=` 解析為變數賦值運算符，破壞 Maven 屬性語法。
**解決方案**：在 Maven 命令前使用停止解析運算子 `--%`：

**PowerShell：**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash：**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

`--%` 運算子告訴 PowerShell 將所有剩餘的參數直接傳遞給 Maven，不進行任何解析。

### Windows PowerShell 表情符號顯示

**問題**：AI 回應在 PowerShell 中顯示為亂碼字元（例如 `????` 或 `â??`），而非表情符號

**原因**：PowerShell 預設編碼不支援 UTF-8 表情符號

**解決方案**：在執行 Java 應用程式前執行此命令：
```cmd
chcp 65001
```

這會強制終端機使用 UTF-8 編碼。或者，也可以使用支援更好 Unicode 的 Windows Terminal。

### 偵錯 API 調用

**問題**：AI 模型出現身份驗證錯誤、速率限制，或非預期的回應

**解決方案**：範例中包含 `.logRequests(true)` 和 `.logResponses(true)` 以在主控台顯示 API 調用內容，有助於排查身份驗證錯誤、速率限制或非預期的回應。正式環境中移除這些標記以減少日誌雜訊。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：  
本文件係使用 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。儘管我們力求準確，但請注意，自動翻譯結果可能包含錯誤或不準確之處。原始文件的母語版本應視為權威來源。對於重要資訊，建議採用專業人工翻譯。我們對因使用本翻譯而產生的任何誤解或曲解不承擔任何責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->