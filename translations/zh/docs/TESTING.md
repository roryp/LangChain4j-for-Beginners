<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "ed93b3c14d58734ac10162967da958c1",
  "translation_date": "2025-12-30T21:09:41+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "zh"
}
-->
# 测试 LangChain4j 应用

## 目录

- [快速开始](../../../docs)
- [测试涵盖内容](../../../docs)
- [运行测试](../../../docs)
- [在 VS Code 中运行测试](../../../docs)
- [测试模式](../../../docs)
- [测试哲学](../../../docs)
- [下一步](../../../docs)

本指南将引导您了解演示如何在不需要 API 密钥或外部服务的情况下测试 AI 应用的测试。

## Quick Start

使用单个命令运行所有测试：

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/zh/test-results.ea5c98d8f3642043.png" alt="成功的测试结果" width="800"/>

*成功的测试执行，显示所有测试通过且无失败*

## 测试涵盖内容

本课程侧重于在本地运行的**单元测试**。每个测试演示一个独立的 LangChain4j 概念。

<img src="../../../translated_images/zh/testing-pyramid.2dd1079a0481e53e.png" alt="测试金字塔" width="800"/>

*测试金字塔显示单元测试（快速、独立）、集成测试（真实组件）和端到端测试之间的平衡。本培训涵盖单元测试。*

| Module | Tests | Focus | Key Files |
|--------|-------|-------|-----------|
| **00 - Quick Start** | 6 | Prompt templates and variable substitution | `SimpleQuickStartTest.java` |
| **01 - Introduction** | 8 | Conversation memory and stateful chat | `SimpleConversationTest.java` |
| **02 - Prompt Engineering** | 12 | GPT-5 patterns, eagerness levels, structured output | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | Document ingestion, embeddings, similarity search | `DocumentServiceTest.java` |
| **04 - Tools** | 12 | Function calling and tool chaining | `SimpleToolsTest.java` |
| **05 - MCP** | 8 | Model Context Protocol with Stdio transport | `SimpleMcpTest.java` |

## 运行测试

**从根目录运行所有测试：**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**运行特定模块的测试：**

**Bash:**
```bash
cd 01-introduction && mvn test
# 或者从根目录
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# 或者从根开始
mvn --% test -pl 01-introduction
```

**运行单个测试类：**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**运行特定的测试方法：**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#应该维护对话历史记录
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#应维护对话历史记录
```

## 在 VS Code 中运行测试

如果您使用 Visual Studio Code，测试资源管理器提供了一个用于运行和调试测试的图形界面。

<img src="../../../translated_images/zh/vscode-testing.f02dd5917289dced.png" alt="VS Code 测试资源管理器" width="800"/>

*VS Code 测试资源管理器显示包含所有 Java 测试类和各个测试方法的测试树*

**在 VS Code 中运行测试：**

1. 单击活动栏中的试管图标以打开测试资源管理器
2. 展开测试树以查看所有模块和测试类
3. 单击任何测试旁边的播放按钮以单独运行该测试
4. 单击“Run All Tests”以执行整个测试套件
5. 右键单击任何测试并选择“Debug Test”以设置断点并逐步调试代码

测试资源管理器对通过的测试显示绿色勾号，并在测试失败时提供详细的失败信息。

## 测试模式

### 模式 1：测试提示模板

最简单的模式是在不调用任何 AI 模型的情况下测试提示模板。您可以验证变量替换是否正常工作以及提示是否按预期格式化。

<img src="../../../translated_images/zh/prompt-template-testing.b902758ddccc8dee.png" alt="提示模板测试" width="800"/>

*测试提示模板，显示变量替换流程：带占位符的模板 → 应用的值 → 验证格式化输出*

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

此测试位于 `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`。

**运行它：**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#测试提示模板格式化
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#测试提示模板格式化
```

### 模式 2：模拟语言模型

在测试会话逻辑时，使用 Mockito 创建返回预设响应的假模型。这样测试就会快速、免费且确定性强。

<img src="../../../translated_images/zh/mock-vs-real.3b8b1f85bfe6845e.png" alt="模拟与真实 API 对比" width="800"/>

*对比说明为什么在测试中更偏好使用模拟：它们快速、免费、确定性强且不需要 API 密钥*

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
        assertThat(history).hasSize(6); // 3 条用户消息 + 3 条 AI 消息
    }
}
```

该模式出现在 `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`。模拟确保行为一致，因此您可以验证记忆管理是否正常工作。

### 模式 3：测试会话隔离

会话记忆必须将多个用户区分开。此测试验证会话不会混合上下文。

<img src="../../../translated_images/zh/conversation-isolation.e00336cf8f7a3e3f.png" alt="会话隔离" width="800"/>

*测试会话隔离，显示不同用户的独立记忆存储以防止上下文混淆*

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

每个会话都维护其独立的历史记录。在生产系统中，这种隔离对于多用户应用至关重要。

### 模式 4：独立测试工具

工具是 AI 可以调用的函数。直接测试它们以确保无论 AI 的决策如何，它们都能正确工作。

<img src="../../../translated_images/zh/tools-testing.3e1706817b0b3924.png" alt="工具测试" width="800"/>

*独立测试工具，显示在不调用 AI 的情况下模拟工具执行以验证业务逻辑*

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

这些来自 `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` 的测试在没有 AI 参与的情况下验证工具逻辑。链式示例展示了一个工具的输出如何成为另一个工具的输入。

### 模式 5：内存中 RAG 测试

RAG 系统传统上需要向量数据库和嵌入服务。内存中模式让您在无需外部依赖的情况下测试整个管道。

<img src="../../../translated_images/zh/rag-testing.ee7541b1e23934b1.png" alt="内存中 RAG 测试" width="800"/>

*内存中 RAG 测试工作流，展示文档解析、嵌入存储和相似度搜索，而无需数据库*

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

此测试来自 `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java`，在内存中创建文档并验证分块和元数据处理。

### 模式 6：MCP 集成测试

MCP 模块使用 stdio 传输测试模型上下文协议的集成。这些测试验证您的应用是否能够生成并与作为子进程的 MCP 服务器通信。

`05-mcp/src/test/java/com/example/langchain4j/mcp/SimpleMcpTest.java` 中的测试验证 MCP 客户端行为。

**运行它们：**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## 测试哲学

测试您的代码，而不是 AI。您的测试应通过检查提示如何构建、记忆如何管理以及工具如何执行来验证您编写的代码。AI 的响应会变化，不应成为测试断言的一部分。问你自己的是：提示模板是否正确替换了变量，而不是 AI 是否给出正确答案。

对语言模型使用模拟。它们是外部依赖，速度慢、费用高且不确定。模拟使测试快速（毫秒级而不是秒级）、免费且确定性高，每次得到相同结果。

保持测试独立。每个测试都应设置自己的数据，不依赖其他测试，并在完成后清理。测试应在任何执行顺序下都能通过。

测试超出正常路径的边界情况。尝试空输入、非常大的输入、特殊字符、无效参数和边界条件。这些通常会暴露正常使用下不会显现的错误。

使用描述性名称。将 shouldMaintainConversationHistoryAcrossMultipleMessages() 与 test1() 相比，第一个会明确地告诉您正在测试的内容，使调试失败更容易。

## 下一步

现在您已经了解了测试模式，请深入研究每个模块：

- **[00 - 快速开始](../00-quick-start/README.md)** - 从提示模板基础开始
- **[01 - 介绍](../01-introduction/README.md)** - 学习会话记忆管理
- **[02 - 提示工程](../02-prompt-engineering/README.md)** - 掌握 GPT-5 提示模式
- **[03 - RAG](../03-rag/README.md)** - 构建检索增强生成系统
- **[04 - 工具](../04-tools/README.md)** - 实现函数调用和工具链
- **[05 - MCP](../05-mcp/README.md)** - 集成模型上下文协议

每个模块的 README 提供了对此处测试概念的详细解释。

---

**导航：** [← 返回主页面](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
免责声明：
本文件使用 AI 翻译服务 Co-op Translator (https://github.com/Azure/co-op-translator) 进行翻译。虽然我们力求准确，但请注意，自动翻译可能包含错误或不准确之处。原始语言的文档应被视为权威来源。对于重要信息，建议采用专业人工翻译。我们不对因使用本翻译而产生的任何误解、曲解或由此引起的后果承担责任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->