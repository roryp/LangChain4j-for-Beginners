<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-30T21:25:20+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "mo"
}
-->
# 測試 LangChain4j 應用程式

## 目錄

- [快速入門](../../../docs)
- [測試涵蓋範圍](../../../docs)
- [執行測試](../../../docs)
- [在 VS Code 中執行測試](../../../docs)
- [測試模式](../../../docs)
- [測試理念](../../../docs)
- [後續步驟](../../../docs)

本指南帶您了解示範如何在不需要 API 金鑰或外部服務的情況下測試 AI 應用程式的測試。

## 快速入門

用一個指令執行所有測試：

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/mo/test-results.ea5c98d8f3642043.png" alt="成功的測試結果" width="800"/>

*成功執行測試，所有測試均通過且沒有失敗*

## 測試涵蓋範圍

本課程著重於在本機執行的**單元測試**。每個測試都會在隔離環境中示範特定的 LangChain4j 概念。

<img src="../../../translated_images/mo/testing-pyramid.2dd1079a0481e53e.png" alt="測試金字塔" width="800"/>

*測試金字塔顯示單元測試（快速、隔離）、整合測試（實際元件）與端到端測試之間的平衡。本課程涵蓋單元測試。*

| 模組 | 測試數量 | 重點 | 主要檔案 |
|--------|-------|-------|-----------|
| **00 - 快速入門** | 6 | 提示模板與變數替換 | `SimpleQuickStartTest.java` |
| **01 - 介紹** | 8 | 對話記憶與有狀態聊天 | `SimpleConversationTest.java` |
| **02 - 提示工程** | 12 | GPT-5 模式、積極程度、結構化輸出 | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | 文件攝取、嵌入、相似度搜索 | `DocumentServiceTest.java` |
| **04 - 工具** | 12 | 函數呼叫與工具串接 | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | 使用 Stdio 傳輸的模型上下文協定 | `SimpleMcpTest.java` |

## 執行測試

**從專案根目錄執行所有測試：**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**執行特定模組的測試：**

**Bash:**
```bash
cd 01-introduction && mvn test
# 或者由 root
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# 或由 root
mvn --% test -pl 01-introduction
```

**執行單一測試類別：**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**執行特定測試方法：**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#應該維持對話記錄
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#是否應該維持對話紀錄
```

## 在 VS Code 中執行測試

如果您使用 Visual Studio Code，Test Explorer 提供了圖形介面來執行與除錯測試。

<img src="../../../translated_images/mo/vscode-testing.f02dd5917289dced.png" alt="VS Code 測試總管" width="800"/>

*VS Code 測試總管顯示測試樹，包含所有 Java 測試類別與個別測試方法*

**在 VS Code 中執行測試：**

1. 點擊活動列上的試管圖示以開啟測試總管
2. 展開測試樹以查看所有模組與測試類別
3. 點擊任一測試旁的播放按鈕以單獨執行該測試
4. 點擊「Run All Tests」以執行整個測試套件
5. 右鍵點擊任一測試並選擇「Debug Test」以設置斷點並單步除錯程式碼

測試總管會顯示通過測試的綠色勾勾，並在測試失敗時提供詳細的失敗訊息。

## 測試模式

### 模式 1：測試提示模板

最簡單的模式是在不呼叫任何 AI 模型的情況下測試提示模板。您可以驗證變數替換是否正確，並且提示是否按預期格式化。

<img src="../../../translated_images/mo/prompt-template-testing.b902758ddccc8dee.png" alt="提示模板測試" width="800"/>

*測試提示模板顯示變數替換流程：帶有佔位符的模板 → 套用值 → 驗證格式化輸出*

```java
@Test
@DisplayName("Should format prompt template with variables")
void testPromptTemplateFormatting() {
    PromptTemplate template = PromptTemplate.from(
        "Best time to visit {{destination}} for {{activity}}?"
    );
    
    Prompt prompt = template.apply(Map.of(
        "destination", "Paris",
        "activity", "sightseeing"
    ));
    
    assertThat(prompt.text()).isEqualTo("Best time to visit Paris for sightseeing?");
}
```

此測試存在於 `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`。

**執行它：**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#測試提示範本格式化
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#測試提示範本格式化
```

### 模式 2：模擬語言模型

在測試對話邏輯時，使用 Mockito 建立會回傳預先定義回應的假模型。這樣測試就會快速、免費且具有決定性。

<img src="../../../translated_images/mo/mock-vs-real.3b8b1f85bfe6845e.png" alt="模擬與實際 API 比較" width="800"/>

*比較顯示為何在測試中優先使用模擬：它們快速、免費、具有決定性，且不需要 API 金鑰*

```java
@ExtendWith(MockitoExtension.class)
class SimpleConversationTest {
    
    private ConversationService conversationService;
    
    @Mock
    private OpenAiOfficialChatModel mockChatModel;
    
    @BeforeEach
    void setUp() {
        ChatResponse mockResponse = ChatResponse.builder()
            .aiMessage(AiMessage.from("This is a test response"))
            .build();
        when(mockChatModel.chat(anyList())).thenReturn(mockResponse);
        
        conversationService = new ConversationService(mockChatModel);
    }
    
    @Test
    void shouldMaintainConversationHistory() {
        String conversationId = conversationService.startConversation();
        
        ChatResponse mockResponse1 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 1"))
            .build();
        ChatResponse mockResponse2 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 2"))
            .build();
        ChatResponse mockResponse3 = ChatResponse.builder()
            .aiMessage(AiMessage.from("Response 3"))
            .build();
        
        when(mockChatModel.chat(anyList()))
            .thenReturn(mockResponse1)
            .thenReturn(mockResponse2)
            .thenReturn(mockResponse3);

        conversationService.chat(conversationId, "First message");
        conversationService.chat(conversationId, "Second message");
        conversationService.chat(conversationId, "Third message");

        List<ChatMessage> history = conversationService.getHistory(conversationId);
        assertThat(history).hasSize(6); // 3 個使用者訊息 + 3 個 AI 訊息
    }
}
```

此模式出現在 `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`。模擬確保行為一致，讓您可以驗證記憶管理是否正確運作。

### 模式 3：測試對話隔離

對話記憶必須將多個使用者分開。此測試驗證對話不會互相混淆上下文。

<img src="../../../translated_images/mo/conversation-isolation.e00336cf8f7a3e3f.png" alt="對話隔離" width="800"/>

*測試對話隔離顯示為不同使用者維護獨立的記憶儲存以防止上下文混用*

```java
@Test
void shouldIsolateConversationsByid() {
    String conv1 = conversationService.startConversation();
    String conv2 = conversationService.startConversation();
    
    ChatResponse mockResponse = ChatResponse.builder()
        .aiMessage(AiMessage.from("Response"))
        .build();
    when(mockChatModel.chat(anyList())).thenReturn(mockResponse);

    conversationService.chat(conv1, "Message for conversation 1");
    conversationService.chat(conv2, "Message for conversation 2");

    List<ChatMessage> history1 = conversationService.getHistory(conv1);
    List<ChatMessage> history2 = conversationService.getHistory(conv2);
    
    assertThat(history1).hasSize(2);
    assertThat(history2).hasSize(2);
}
```

每個對話都維持其獨立的歷史記錄。在生產系統中，這種隔離對於多使用者應用程式至關重要。

### 模式 4：獨立測試工具

工具是 AI 可以呼叫的函數。直接測試它們以確保它們在不受 AI 決策影響下正常運作。

<img src="../../../translated_images/mo/tools-testing.3e1706817b0b3924.png" alt="工具測試" width="800"/>

*獨立測試工具示範在不呼叫 AI 的情況下模擬工具執行以驗證商業邏輯*

```java
@Test
void shouldConvertCelsiusToFahrenheit() {
    TemperatureTool tempTool = new TemperatureTool();
    String result = tempTool.celsiusToFahrenheit(25.0);
    assertThat(result).containsPattern("77[.,]0°F");
}

@Test
void shouldDemonstrateToolChaining() {
    WeatherTool weatherTool = new WeatherTool();
    TemperatureTool tempTool = new TemperatureTool();

    String weatherResult = weatherTool.getCurrentWeather("Seattle");
    assertThat(weatherResult).containsPattern("\\d+°C");

    String conversionResult = tempTool.celsiusToFahrenheit(22.0);
    assertThat(conversionResult).containsPattern("71[.,]6°F");
}
```

這些測試位於 `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java`，驗證工具邏輯在沒有 AI 參與下的正確性。連鎖範例顯示一個工具的輸出如何作為另一個工具的輸入。

### 模式 5：記憶體內 RAG 測試

RAG 系統通常需要向量資料庫和嵌入服務。記憶體內模式讓您在不依賴外部資源的情況下測試整個流程。

<img src="../../../translated_images/mo/rag-testing.ee7541b1e23934b1.png" alt="記憶體內 RAG 測試" width="800"/>

*記憶體內 RAG 測試工作流程顯示文件解析、嵌入儲存與相似度搜索，而不需要資料庫*

```java
@Test
void testProcessTextDocument() {
    String content = "This is a test document.\nIt has multiple lines.";
    InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    
    DocumentService.ProcessedDocument result = 
        documentService.processDocument(inputStream, "test.txt");

    assertNotNull(result);
    assertTrue(result.segments().size() > 0);
    assertEquals("test.txt", result.segments().get(0).metadata().getString("filename"));
}
```

此測試來自 `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java`，在記憶體中建立文件並驗證切片與元資料處理。

### 模式 6：MCP 整合測試

MCP 模組測試使用 stdio 傳輸的 Model Context Protocol 整合。這些測試驗證您的應用程式能否以子程序方式啟動並與 MCP 伺服器通訊。

`05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` 中的測試驗證 MCP 客戶端行為。

**執行它們：**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## 測試理念

測試您的程式碼，而不是 AI。您的測試應該透過檢查提示如何構建、記憶如何管理以及工具如何執行來驗證您所撰寫的程式碼。AI 回應可能會變化，不應成為測試斷言的一部分。問自己的是提示模板是否正確替換變數，而不是 AI 是否給出正確答案。

對語言模型使用模擬物。它們是外部依賴，速度慢、花費高且不具決定性。模擬能讓測試從秒級減少到毫秒級，無需 API 成本，且每次結果相同。

保持測試獨立。每個測試都應設置自己的資料，不依賴其他測試，並在完成後清理。測試應在任何執行順序下都能通過。

測試超出理想情況的邊界情形。嘗試空輸入、非常大的輸入、特殊字元、無效參數和邊界條件。這些通常能揭露正常使用下不會出現的錯誤。

使用具描述性的名稱。將 `shouldMaintainConversationHistoryAcrossMultipleMessages()` 與 `test1()` 比較。前者能明確告訴您正在測試什麼，讓除錯失敗更容易。

## 後續步驟

既然您已了解測試模式，請深入各個模組：

- **[00 - 快速入門](../00-quick-start/README.md)** - 從提示模板基礎開始
- **[01 - 介紹](../01-introduction/README.md)** - 學習對話記憶管理
- **[02 - 提示工程](../02-prompt-engineering/README.md)** - 精通 GPT-5 提示模式
- **[03 - RAG](../03-rag/README.md)** - 建構檢索增強生成系統
- **[04 - 工具](../04-tools/README.md)** - 實作函數呼叫與工具串連
- **[05 - MCP](../05-mcp/README.md)** - 整合 Model Context Protocol

每個模組的 README 提供了此處測試概念的詳細說明。

---

**導覽：** [← 返回主頁](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免責聲明：
本文件由 AI 翻譯服務 Co-op Translator (https://github.com/Azure/co-op-translator) 進行翻譯。儘管我們力求準確，請注意自動翻譯可能包含錯誤或不準確之處。應以原始語言的文件版本為準。對於重要或關鍵資訊，建議採用專業人工翻譯。對於因使用本翻譯而引致的任何誤解或曲解，我們概不負責。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->