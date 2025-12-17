<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "b975537560c404d5f254331832811e78",
  "translation_date": "2025-12-13T20:56:23+00:00",
  "source_file": "docs/TESTING.md",
  "language_code": "pt"
}
-->
# Testar Aplicações LangChain4j

## Índice

- [Início Rápido](../../../docs)
- [O Que os Testes Abrangem](../../../docs)
- [Executar os Testes](../../../docs)
- [Executar Testes no VS Code](../../../docs)
- [Padrões de Teste](../../../docs)
- [Filosofia de Teste](../../../docs)
- [Próximos Passos](../../../docs)

Este guia orienta-o através dos testes que demonstram como testar aplicações de IA sem necessitar de chaves de API ou serviços externos.

## Início Rápido

Execute todos os testes com um único comando:

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

<img src="../../../translated_images/test-results.ea5c98d8f3642043bdfde5c208f8c0760a88a818f6846763583b614a5de37d52.pt.png" alt="Successful Test Results" width="800"/>

*Execução bem-sucedida dos testes mostrando todos os testes aprovados sem falhas*

## O Que os Testes Abrangem

Este curso foca-se em **testes unitários** que correm localmente. Cada teste demonstra um conceito específico do LangChain4j isoladamente.

<img src="../../../translated_images/testing-pyramid.2dd1079a0481e53e4da944aec40169f37adf86fd932d3dfd56a4a86b37401ab9.pt.png" alt="Testing Pyramid" width="800"/>

*Pirâmide de testes mostrando o equilíbrio entre testes unitários (rápidos, isolados), testes de integração (componentes reais) e testes end-to-end (sistema completo com Docker). Esta formação cobre testes unitários.*

| Módulo | Testes | Foco | Ficheiros Principais |
|--------|--------|-------|---------------------|
| **00 - Início Rápido** | 6 | Templates de prompt e substituição de variáveis | `SimpleQuickStartTest.java` |
| **01 - Introdução** | 8 | Memória de conversação e chat com estado | `SimpleConversationTest.java` |
| **02 - Engenharia de Prompt** | 12 | Padrões GPT-5, níveis de prontidão, saída estruturada | `SimpleGpt5PromptTest.java` |
| **03 - RAG** | 10 | Ingestão de documentos, embeddings, pesquisa por similaridade | `DocumentServiceTest.java` |
| **04 - Ferramentas** | 12 | Chamada de funções e encadeamento de ferramentas | `SimpleToolsTest.java` |
| **05 - MCP** | 15 | Protocolo de Contexto de Modelo com Docker | `SimpleMcpTest.java`, `McpDockerTransportTest.java` |

## Executar os Testes

**Executar todos os testes a partir da raiz:**

**Bash:**
```bash
mvn test
```

**PowerShell:**
```powershell
mvn --% test
```

**Executar testes para um módulo específico:**

**Bash:**
```bash
cd 01-introduction && mvn test
# Ou a partir da raiz
mvn test -pl 01-introduction
```

**PowerShell:**
```powershell
cd 01-introduction; mvn --% test
# Ou a partir da raiz
mvn --% test -pl 01-introduction
```

**Executar uma única classe de teste:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest
```

**Executar um método de teste específico:**

**Bash:**
```bash
mvn test -Dtest=SimpleConversationTest#deveManterHistoricoDeConversa
```

**PowerShell:**
```powershell
mvn --% test -Dtest=SimpleConversationTest#deveManterHistoricoDeConversa
```

## Executar Testes no VS Code

Se estiver a usar o Visual Studio Code, o Test Explorer fornece uma interface gráfica para executar e depurar testes.

<img src="../../../translated_images/vscode-testing.f02dd5917289dcedbacf98a3539218c1d0c700307ab77c031590ae63d0be59b6.pt.png" alt="VS Code Test Explorer" width="800"/>

*Test Explorer do VS Code mostrando a árvore de testes com todas as classes de teste Java e métodos de teste individuais*

**Para executar testes no VS Code:**

1. Abra o Test Explorer clicando no ícone do béquer na Barra de Atividades
2. Expanda a árvore de testes para ver todos os módulos e classes de teste
3. Clique no botão de play ao lado de qualquer teste para o executar individualmente
4. Clique em "Run All Tests" para executar toda a suíte
5. Clique com o botão direito em qualquer teste e selecione "Debug Test" para definir pontos de interrupção e avançar no código

O Test Explorer mostra marcas verdes para testes aprovados e fornece mensagens detalhadas de falha quando os testes falham.

## Padrões de Teste

<img src="../../../translated_images/testing-patterns.02581af1c9ef742460887004e9940994dba342ec726efc4ddb2b0215b56a1d89.pt.png" alt="Six Testing Patterns" width="800"/>

*Seis padrões de teste para aplicações LangChain4j: templates de prompt, simulação de modelos, isolamento de conversação, teste de ferramentas, RAG em memória e integração com Docker*

### Padrão 1: Testar Templates de Prompt

O padrão mais simples testa templates de prompt sem chamar qualquer modelo de IA. Verifica-se que a substituição de variáveis funciona corretamente e que os prompts são formatados conforme esperado.

<img src="../../../translated_images/prompt-template-testing.b902758ddccc8dee3dfceaa5fa903098bdb174674acc5009a2f3d60cb07eb4e9.pt.png" alt="Prompt Template Testing" width="800"/>

*Testar templates de prompt mostrando o fluxo de substituição de variáveis: template com espaços reservados → valores aplicados → saída formatada verificada*

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

Este teste encontra-se em `00-quick-start/src/test/java/com/example/langchain4j/quickstart/SimpleQuickStartTest.java`.

**Execute-o:**

**Bash:**
```bash
cd 00-quick-start && mvn test -Dtest=SimpleQuickStartTest#testarFormataçãoDoModeloDePrompt
```

**PowerShell:**
```powershell
cd 00-quick-start; mvn --% test -Dtest=SimpleQuickStartTest#testarFormataçãoDoModeloDePrompt
```

### Padrão 2: Simulação de Modelos de Linguagem

Ao testar a lógica de conversação, use Mockito para criar modelos falsos que retornam respostas pré-determinadas. Isto torna os testes rápidos, gratuitos e determinísticos.

<img src="../../../translated_images/mock-vs-real.3b8b1f85bfe6845ee07556f9520ac45c7f776d0879434315756020cd8b1d5b73.pt.png" alt="Mock vs Real API Comparison" width="800"/>

*Comparação mostrando porque os mocks são preferidos para testes: são rápidos, gratuitos, determinísticos e não requerem chaves de API*

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
        assertThat(history).hasSize(6); // 3 mensagens de utilizador + 3 mensagens de IA
    }
}
```

Este padrão aparece em `01-introduction/src/test/java/com/example/langchain4j/service/SimpleConversationTest.java`. O mock assegura comportamento consistente para que possa verificar se a gestão da memória funciona corretamente.

### Padrão 3: Testar Isolamento de Conversação

A memória da conversação deve manter múltiplos utilizadores separados. Este teste verifica que as conversas não misturam contextos.

<img src="../../../translated_images/conversation-isolation.e00336cf8f7a3e3f860d81c37a97181c0e0a2ff996d38f314f06b35cc1e08ca3.pt.png" alt="Conversation Isolation" width="800"/>

*Testar isolamento de conversação mostrando armazenamentos de memória separados para diferentes utilizadores para evitar mistura de contextos*

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

Cada conversação mantém o seu próprio histórico independente. Em sistemas de produção, este isolamento é crítico para aplicações multiutilizador.

### Padrão 4: Testar Ferramentas Independentemente

Ferramentas são funções que a IA pode chamar. Teste-as diretamente para garantir que funcionam corretamente independentemente das decisões da IA.

<img src="../../../translated_images/tools-testing.3e1706817b0b3924e7e7cd41be8ba5ccb62f16962b85f53953d46cc6317d2972.pt.png" alt="Tools Testing" width="800"/>

*Testar ferramentas independentemente mostrando execução simulada de ferramentas sem chamadas à IA para verificar a lógica de negócio*

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

Estes testes de `04-tools/src/test/java/com/example/langchain4j/agents/tools/SimpleToolsTest.java` validam a lógica das ferramentas sem envolvimento da IA. O exemplo de encadeamento mostra como a saída de uma ferramenta alimenta a entrada de outra.

### Padrão 5: Testar RAG em Memória

Sistemas RAG tradicionalmente requerem bases de dados vetoriais e serviços de embedding. O padrão em memória permite testar toda a pipeline sem dependências externas.

<img src="../../../translated_images/rag-testing.ee7541b1e23934b14fcda9103bba011864f1d0882b27a9886c3cf5b446522ce3.pt.png" alt="In-Memory RAG Testing" width="800"/>

*Fluxo de teste RAG em memória mostrando análise de documentos, armazenamento de embeddings e pesquisa por similaridade sem necessidade de base de dados*

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

Este teste de `03-rag/src/test/java/com/example/langchain4j/rag/service/DocumentServiceTest.java` cria um documento em memória e verifica a fragmentação e gestão de metadados.

### Padrão 6: Teste de Integração com Docker

Algumas funcionalidades precisam de infraestrutura real. O módulo MCP usa Testcontainers para levantar containers Docker para testes de integração. Estes validam que o seu código funciona com serviços reais mantendo o isolamento dos testes.

<img src="../../../translated_images/mcp-testing.bb3b3e92e47acb4b3dfb12715b4e113655f16c11ba39c2ffabae557bc6c3734c.pt.png" alt="MCP Docker Integration Testing" width="800"/>

*Teste de integração MCP com Testcontainers mostrando ciclo de vida automatizado do container: iniciar, executar teste, parar e limpar*

Os testes em `05-mcp/src/test/java/com/example/langchain4j/mcp/McpDockerTransportTest.java` requerem que o Docker esteja a correr.

**Execute-os:**

**Bash:**
```bash
cd 05-mcp && mvn test
```

**PowerShell:**
```powershell
cd 05-mcp; mvn --% test
```

## Filosofia de Teste

Teste o seu código, não a IA. Os seus testes devem validar o código que escreve verificando como os prompts são construídos, como a memória é gerida e como as ferramentas são executadas. As respostas da IA variam e não devem fazer parte das asserções dos testes. Pergunte-se se o seu template de prompt substitui corretamente as variáveis, não se a IA dá a resposta certa.

Use mocks para modelos de linguagem. São dependências externas que são lentas, caras e não determinísticas. A simulação torna os testes rápidos com milissegundos em vez de segundos, gratuitos sem custos de API e determinísticos com o mesmo resultado todas as vezes.

Mantenha os testes independentes. Cada teste deve configurar os seus próprios dados, não depender de outros testes e limpar-se a si próprio. Os testes devem passar independentemente da ordem de execução.

Teste casos limite além do caminho feliz. Experimente entradas vazias, entradas muito grandes, caracteres especiais, parâmetros inválidos e condições de fronteira. Estes frequentemente revelam bugs que o uso normal não expõe.

Use nomes descritivos. Compare `shouldMaintainConversationHistoryAcrossMultipleMessages()` com `test1()`. O primeiro diz-lhe exatamente o que está a ser testado, tornando a depuração de falhas muito mais fácil.

## Próximos Passos

Agora que compreende os padrões de teste, aprofunde-se em cada módulo:

- **[00 - Início Rápido](../00-quick-start/README.md)** - Comece com os fundamentos dos templates de prompt
- **[01 - Introdução](../01-introduction/README.md)** - Aprenda a gestão da memória de conversação
- **[02 - Engenharia de Prompt](../02-prompt-engineering/README.md)** - Domine os padrões de prompting GPT-5
- **[03 - RAG](../03-rag/README.md)** - Construa sistemas de geração aumentada por recuperação
- **[04 - Ferramentas](../04-tools/README.md)** - Implemente chamadas de função e encadeamento de ferramentas
- **[05 - MCP](../05-mcp/README.md)** - Integre o Protocolo de Contexto de Modelo com Docker

O README de cada módulo fornece explicações detalhadas dos conceitos testados aqui.

---

**Navegação:** [← Voltar ao Principal](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, por favor tenha em conta que traduções automáticas podem conter erros ou imprecisões. O documento original na sua língua nativa deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se a tradução profissional humana. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações erradas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->