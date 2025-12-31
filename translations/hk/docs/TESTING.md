<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-30T21:39:30+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "hk"
}
-->
# 測試 LangChain4j 應用程式

## 目錄

- [快速開始](../../../docs)
- [測試涵蓋內容](../../../docs)
- [運行測試](../../../docs)
- [在 VS Code 中運行測試](../../../docs)
- [測試模式](../../../docs)
- [測試理念](../../../docs)
- [下一步](../../../docs)

本指南引導你了解示範如何在不需要 API 金鑰或外部服務的情況下測試 AI 應用程式的測試。

## 快速開始

用一條指令運行所有測試：

**Bash:**
```bash
mvn test
```

**PowerShell：**
```powershell
mvn --% test
```

<img src="../../../translated_images/test-results.ea5c98d8f3642043.hk.png" alt="成功的測試結果" width="800"/>

*成功執行測試，所有測試通過，沒有失敗*

## 測試涵蓋內容

本課程專注於在本地運行的 **單元測試**。每個測試都獨立展示一個特定的 LangChain4j 概念。

<img src="../../../translated_images/testing-pyramid.2dd1079a0481e53e.hk.png" alt="測試金字塔" width="800"/>

*測試金字塔顯示單元測試（快速、隔離）、整合測試（真實元件）和端到端測試之間的平衡。本課程涵蓋單元測試。*

| 模組 | 測試數 | 重點 | 主要檔案 |
|--------|-------|-------|-----------|
| **00 - 快速開始** | 6 | 提示模板與變數替換 | `SimpleQuickStartTest.java` |
| **01 - 介紹** | 8 | 會話記憶與有狀態聊天 | `SimpleConversationTest.java` |
| **02 - 提示工程** | 12 | GPT-5 範式、積極程度、結構化輸出 | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | 文件擷取、嵌入、相似度搜尋 | `DocumentServiceTest.java` |
| **04 - 工具** | 12 | 函數呼叫與工具串接 | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | 使用 stdio 傳輸的模型上下文協議 | `SimpleMcpTest.java` |

## 運行測試

**從專案根目錄運行所有測試：**

**Bash:**
```bash
mvn test
```

**PowerShell：**
```powershell
mvn --% test
```

**運行特定模組的測試：**

**Bash:**
```bash
cd 01-introduction && mvn test
# 或者由 root
mvn test -pl 01-introduction
```

**PowerShell：**
```powershell
cd 01-introduction; mvn --% test
# 或者由 root
mvn --% test -pl 01-introduction
```

**運行單一測試類別：**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell：**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**運行特定測試方法：**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#應該保留對話紀錄
```

**PowerShell：**
```powershell
mvn --% test -Dtest=SimpleConversationTest#應該保留對話紀錄
```

## 在 VS Code 中運行測試

如果你使用 Visual Studio Code，Test Explorer 提供圖形化介面來運行與偵錯測試。

<img src="../../../translated_images/vscode-testing.f02dd5917289dced.hk.png" alt="VS Code 測試總覽" width="800"/>

*VS Code 測試總覽顯示包含所有 Java 測試類別和各別測試方法的測試樹*

**在 VS Code 中運行測試：**

1. 在活動列中點選燒杯圖示以開啟測試總覽
2. 展開測試樹以查看所有模組與測試類別
3. 點選任何測試旁邊的播放按鈕以單獨執行該測試
4. 點選「Run All Tests」以執行整個測試套件
5. 右鍵點選任一測試並選擇「Debug Test」以設置斷點並逐步檢視程式碼

測試總覽會以綠色勾勾顯示通過的測試，並在測試失敗時提供詳細的失敗訊息。

## 測試模式

### 模式 1：測試提示模板

最簡單的模式是在不呼叫任何 AI 模型的情況下測試提示模板。你要驗證變數替換是否正確，以及提示的格式是否如預期。

<img src="../../../translated_images/prompt-template-testing.b902758ddccc8dee.hk.png" alt="提示模板測試" width="800"/>

*提示模板測試，展示變數替換流程：帶有佔位符的範本 → 套用值 → 驗證格式化輸出*

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

此測試位於 `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`。

**運行它：**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#測試提示範本格式化
```

**PowerShell：**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#測試提示範本格式化
```

### 模式 2：模擬語言模型

在測試會話邏輯時，使用 Mockito 建立會回傳預先定義回應的假模型。這讓測試快速、免費且具決定性。

<img src="../../../translated_images/mock-vs-real.3b8b1f85bfe6845e.hk.png" alt="模擬與真實 API 比較" width="800"/>

*比較圖示說明為何在測試中偏好使用 mock：它們快速、免費、具決定性，且不需要 API 金鑰*

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

這個模式出現在 `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`。mock 能確保一致的行為，讓你能驗證記憶管理是否正確。

### 模式 3：測試會話隔離

會話記憶必須將多位使用者分開保持。此測試驗證會話不會混合上下文。

<img src="../../../translated_images/conversation-isolation.e00336cf8f7a3e3f.hk.png" alt="會話隔離" width="800"/>

*測試會話隔離，顯示為不同使用者保留獨立的記憶庫以防止上下文混用*

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

每個會話都維護自己的獨立歷史紀錄。在生產系統中，這種隔離對於多使用者應用程式至關重要。

### 模式 4：獨立測試工具

工具是 AI 可以呼叫的函數。直接測試它們以確保無論 AI 的決策如何，工具邏輯都能正確運作。

<img src="../../../translated_images/tools-testing.3e1706817b0b3924.hk.png" alt="工具測試" width="800"/>

*獨立測試工具，示範在不呼叫 AI 的情況下以 mock 執行工具來驗證商業邏輯*

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

這些測試來自 `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java`，驗證在沒有 AI 參與下的工具邏輯。串接範例展示一個工具的輸出如何成為另一個工具的輸入。

### 模式 5：記憶體內 RAG 測試

RAG 系統傳統上需要向量資料庫與嵌入服務。記憶體內模式讓你在不依賴外部的情況下測試整個流程。

<img src="../../../translated_images/rag-testing.ee7541b1e23934b1.hk.png" alt="記憶體內 RAG 測試" width="800"/>

*記憶體內 RAG 測試工作流程，示範文件解析、嵌入儲存與相似度搜尋，而不需要資料庫*

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

此測試來自 `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java`，在記憶體中建立文件並驗證分塊與 metadata 處理。

### 模式 6：MCP 整合測試

MCP 模組使用 stdio 傳輸測試模型上下文協議的整合。這些測試驗證你的應用程式能夠以子程序的形式啟動並與 MCP 伺服器通訊。

`05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` 的測試驗證 MCP 用戶端行為。

**運行它們：**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell：**
```powershell
cd 05-mcp; mvn --% test
```

## 測試理念

測試你的程式碼，而不是 AI。你的測試應該透過檢查提示如何構建、記憶如何管理以及工具如何執行來驗證你所撰寫的程式碼。AI 的回應會有變化，不應該成為測試斷言的一部分。問你自己的是：你的提示模板是否正確地替換變數，而不是 AI 是否給出正確答案。

對語言模型使用 mock。它們是外部相依性，既慢、昂貴，又不具決定性。使用 mock 可以讓測試從數秒縮短到毫秒，無需 API 成本，並保證每次得到相同結果。

保持測試獨立。每個測試都應該自行建立所需資料，不依賴其他測試，並在結束時自行清理。測試應該無論執行順序為何都能通過。

測試快樂路徑之外的邊界情況。嘗試空輸入、非常大的輸入、特殊字元、無效參數與邊界條件。這些情況常常揭露正常使用下不會顯現的錯誤。

使用具描述性的名稱。比較 `shouldMaintainConversationHistoryAcrossMultipleMessages()` 與 `test1()`。前者能清楚告訴你正在測試的內容，當失敗時也更容易進行除錯。

## 下一步

現在你已了解測試模式，深入探索各模組：

- **[00 - 快速開始](../00-quick-start/README.md)** - 從提示模板基礎開始
- **[01 - 介紹](../01-introduction/README.md)** - 學習會話記憶管理
- **[02 - 提示工程](../02-prompt-engineering/README.md)** - 精通 GPT-5 提示範式
- **[03 - RAG](../03-rag/README.md)** - 建構檢索增強生成系統
- **[04 - 工具](../04-tools/README.md)** - 實作函數呼叫與工具串接
- **[05 - MCP](../05-mcp/README.md)** - 整合模型上下文協議

每個模組的 README 提供本文件中測試概念的詳細說明。

---

**導覽：** [← 返回主頁](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免責聲明：
本文件已使用 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們致力確保準確性，但請注意，自動翻譯可能包含錯誤或不準確之處。原始語言的文件應被視為具權威性的版本。若涉及重要資訊，建議採用專業人工翻譯。我們對因使用本翻譯而引致的任何誤解或錯誤詮釋概不負責。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->