<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-30T21:51:24+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "tw"
}
-->
# 測試 LangChain4j 應用程式

## 目錄

- [快速開始](../../../docs)
- [測試涵蓋哪些內容](../../../docs)
- [執行測試](../../../docs)
- [在 VS Code 中執行測試](../../../docs)
- [測試範式](../../../docs)
- [測試理念](../../../docs)
- [下一步](../../../docs)

本指南將引導你了解示範如何在不需要 API 金鑰或外部服務的情況下測試 AI 應用程式的測試。

## 快速開始

用單一指令執行所有測試：

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/tw/test-results.ea5c98d8f3642043.png" alt="成功的測試結果" width="800"/>

*成功的測試執行，所有測試皆通過且沒有失敗*

## 測試涵蓋哪些內容

本指南著重於在本地執行的 **單元測試**。每個測試獨立示範 LangChain4j 的特定概念。

<img src="../../../translated_images/tw/testing-pyramid.2dd1079a0481e53e.png" alt="測試金字塔" width="800"/>

*測試金字塔顯示單元測試（快速、獨立）、整合測試（實體元件）與端對端測試之間的平衡。本訓練涵蓋單元測試。*

| 模組 | 測試數 | 焦點 | 主要檔案 |
|--------|-------|-------|-----------|
| **00 - 快速開始** | 6 | 提示範本與變數替換 | `SimpleQuickStartTest.java` |
| **01 - 介紹** | 8 | 對話記憶與有狀態的聊天 | `SimpleConversationTest.java` |
| **02 - 提示工程** | 12 | GPT-5 範式、積極程度、結構化輸出 | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | 文件導入、向量嵌入、相似度搜尋 | `DocumentServiceTest.java` |
| **04 - 工具** | 12 | 函式呼叫與工具串接 | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | 使用 stdio 傳輸的模型上下文協議 | `SimpleMcpTest.java` |

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
# 或從 root
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# 或從 root
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
mvn test -Dtest=SimpleConversationTest#應該保留對話記錄
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#應該保留對話記錄
```

## 在 VS Code 中執行測試

如果你使用 Visual Studio Code，Test Explorer 提供了用於執行與除錯測試的圖形介面。

<img src="../../../translated_images/tw/vscode-testing.f02dd5917289dced.png" alt="VS Code 測試總管" width="800"/>

*VS Code 的 Test Explorer 顯示包含所有 Java 測試類別與各個測試方法的測試樹*

**在 VS Code 中執行測試的方法：**

1. 點擊活動列中的燒杯圖示以開啟 Test Explorer
2. 展開測試樹以查看所有模組與測試類別
3. 點擊任一測試旁的播放按鈕以單獨執行
4. 點擊「Run All Tests」以執行整個測試套件
5. 右鍵點擊任一測試並選擇「Debug Test」以設定斷點並逐步執行程式碼

Test Explorer 對於通過的測試會顯示綠色勾號，而在測試失敗時會提供詳細的失敗訊息。

## 測試範式

### 範式 1：測試提示範本

最簡單的範式是在不呼叫任何 AI 模型的情況下測試提示範本。你要驗證變數替換是否正確，以及提示是否按照預期格式化。

<img src="../../../translated_images/tw/prompt-template-testing.b902758ddccc8dee.png" alt="提示範本測試" width="800"/>

*測試提示範本，顯示變數替換流程：帶有佔位符的範本 → 套用值 → 驗證格式化輸出*

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

這個測試位於 `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`。

**執行它：**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#測試提示範本格式化
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#測試提示範本格式化
```

### 範式 2：模擬語言模型

在測試對話邏輯時，使用 Mockito 建立假的模型以回傳預先設定的回應。這會讓測試快速、免費且具確定性。

<img src="../../../translated_images/tw/mock-vs-real.3b8b1f85bfe6845e.png" alt="模擬與真實 API 比較" width="800"/>

*比較說明為何在測試中偏好使用模擬：它們快速、免費、確定性高，且不需要 API 金鑰*

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
        assertThat(history).hasSize(6); // 3 則使用者訊息 + 3 則 AI 訊息
    }
}
```

這個範式出現在 `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`。模擬確保行為一致，因此你可以驗證記憶管理是否正確運作。

### 範式 3：測試對話隔離

對話記憶必須讓多個使用者彼此分離。此測試驗證對話不會混合上下文。

<img src="../../../translated_images/tw/conversation-isolation.e00336cf8f7a3e3f.png" alt="對話隔離" width="800"/>

*測試對話隔離，顯示不同使用者的獨立記憶儲存以避免上下文混淆*

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

每個對話都維持自己的獨立歷史。在生產系統中，這種隔離對於多使用者應用程式至關重要。

### 範式 4：獨立測試工具

工具是 AI 可以呼叫的函式。直接測試它們以確保其正確運作，而不受 AI 決策的影響。

<img src="../../../translated_images/tw/tools-testing.3e1706817b0b3924.png" alt="工具測試" width="800"/>

*獨立測試工具，顯示在不呼叫 AI 的情況下以模擬工具執行來驗證商業邏輯*

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

這些來自 `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` 的測試在沒有 AI 參與下驗證工具邏輯。串接範例顯示一個工具的輸出如何成為另一個工具的輸入。

### 範式 5：記憶體內 RAG 測試

傳統上 RAG 系統需要向量資料庫與嵌入服務。記憶體內的範式讓你在不依賴外部資源的情況下測試整個流程。

<img src="../../../translated_images/tw/rag-testing.ee7541b1e23934b1.png" alt="記憶體內 RAG 測試" width="800"/>

*記憶體內 RAG 測試流程，示範文件解析、嵌入儲存與相似度搜尋，無需資料庫*

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

這個測試位於 `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java`，在記憶體中建立文件並驗證切塊與 metadata 的處理。

### 範式 6：MCP 整合測試

MCP 模組使用 stdio 傳輸測試 Model Context Protocol 的整合。這些測試驗證你的應用程式能否以子程序的方式啟動並與 MCP 伺服器通訊。

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

測試你的程式碼，而不是 AI。本質上你的測試應該驗證你所撰寫的程式碼：檢查提示如何被構造、記憶如何被管理、工具如何被執行。AI 的回應會變動，不應該成為測試斷言的一部分。問你自己的是提示範本是否正確替換變數，而不是 AI 是否給出正確答案。

對語言模型使用模擬。它們是外部相依性，往往緩慢、昂貴且具非確定性。模擬讓測試快速（毫秒級而非秒級）、免費（無 API 成本），並在每次執行時產生相同結果。

保持測試獨立。每個測試應自行設定資料，不依賴其他測試，並在結束時清理。測試應在任何執行順序下皆能通過。

測試超出順利情境的邊界案例。嘗試空輸入、非常大的輸入、特殊字元、無效參數與邊界條件。這些情況常常會揭露正常使用下不會暴露的錯誤。

使用描述性名稱。比較 `shouldMaintainConversationHistoryAcrossMultipleMessages()` 與 `test1()`。前者明確說明正在測試的內容，使除錯失敗時容易許多。

## 下一步

既然你已了解測試範式，深入研究每個模組：

- **[00 - 快速開始](../00-quick-start/README.md)** - 從提示範本基礎開始
- **[01 - 介紹](../01-introduction/README.md)** - 學習對話記憶管理
- **[02 - 提示工程](../02-prompt-engineering/README.md)** - 精通 GPT-5 的提示範式
- **[03 - RAG](../03-rag/README.md)** - 建構檢索增強生成系統
- **[04 - 工具](../04-tools/README.md)** - 實作函式呼叫與工具串接
- **[05 - MCP](../05-mcp/README.md)** - 整合 Model Context Protocol

每個模組的 README 提供對此處測試概念的詳細說明。

---

**導覽：** [← 返回主頁](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：
本文件已使用 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們力求準確，但請注意，自動翻譯可能包含錯誤或不準確之處。原始語言的文件應視為具權威性的版本。對於重要或關鍵資訊，建議採用專業人工翻譯。我們不對因使用此翻譯而導致的任何誤解或誤譯承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->