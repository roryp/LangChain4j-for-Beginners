<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-05T23:04:47+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "br"
}
-->
# Módulo 04: Agentes de IA com Ferramentas

## Índice

- [O que você aprenderá](../../../04-tools)
- [Pré-requisitos](../../../04-tools)
- [Entendendo Agentes de IA com Ferramentas](../../../04-tools)
- [Como Funciona a Chamada de Ferramentas](../../../04-tools)
  - [Definições de Ferramentas](../../../04-tools)
  - [Tomada de Decisão](../../../04-tools)
  - [Execução](../../../04-tools)
  - [Geração de Resposta](../../../04-tools)
- [Encadeamento de Ferramentas](../../../04-tools)
- [Executar a Aplicação](../../../04-tools)
- [Usando a Aplicação](../../../04-tools)
  - [Experimente o Uso Simples de Ferramentas](../../../04-tools)
  - [Teste o Encadeamento de Ferramentas](../../../04-tools)
  - [Veja o Fluxo da Conversa](../../../04-tools)
  - [Experimente com Diferentes Solicitações](../../../04-tools)
- [Conceitos Principais](../../../04-tools)
  - [Padrão ReAct (Raciocínio e Ação)](../../../04-tools)
  - [Descrições de Ferramentas Importam](../../../04-tools)
  - [Gerenciamento de Sessão](../../../04-tools)
  - [Tratamento de Erros](../../../04-tools)
- [Ferramentas Disponíveis](../../../04-tools)
- [Quando Usar Agentes Baseados em Ferramentas](../../../04-tools)
- [Próximos Passos](../../../04-tools)

## O que você aprenderá

Até agora, você aprendeu como ter conversas com IA, estruturar prompts efetivamente e fundamentar respostas em seus documentos. Mas ainda existe uma limitação fundamental: modelos de linguagem só podem gerar texto. Eles não podem verificar o clima, realizar cálculos, consultar bancos de dados ou interagir com sistemas externos.

As ferramentas mudam isso. Ao dar ao modelo acesso a funções que ele pode chamar, você o transforma de um gerador de texto em um agente que pode tomar ações. O modelo decide quando precisa de uma ferramenta, qual usar e quais parâmetros passar. Seu código executa a função e retorna o resultado. O modelo incorpora esse resultado em sua resposta.

## Pré-requisitos

- Módulo 01 concluído (recursos Azure OpenAI implantados)
- Arquivo `.env` no diretório raiz com credenciais Azure (criado pelo `azd up` no Módulo 01)

> **Nota:** Se você não concluiu o Módulo 01, siga primeiro as instruções de implantação lá.

## Entendendo Agentes de IA com Ferramentas

> **📝 Nota:** O termo "agentes" neste módulo refere-se a assistentes de IA aprimorados com capacidades de chamada de ferramentas. Isso é diferente dos padrões **Agentic AI** (agentes autônomos com planejamento, memória e raciocínio em múltiplas etapas) que abordaremos em [Módulo 05: MCP](../05-mcp/README.md).

Um agente de IA com ferramentas segue um padrão de raciocínio e ação (ReAct):

1. Usuário faz uma pergunta  
2. O agente raciocina sobre o que precisa saber  
3. O agente decide se precisa de uma ferramenta para responder  
4. Se sim, o agente chama a ferramenta apropriada com os parâmetros certos  
5. A ferramenta executa e retorna dados  
6. O agente incorpora o resultado e fornece a resposta final  

<img src="../../../translated_images/pt-BR/react-pattern.86aafd3796f3fd13.webp" alt="Padrão ReAct" width="800"/>

*O padrão ReAct - como agentes de IA alternam entre raciocínio e ação para resolver problemas*

Isso acontece automaticamente. Você define as ferramentas e suas descrições. O modelo gerencia a tomada de decisão sobre quando e como usá-las.

## Como Funciona a Chamada de Ferramentas

### Definições de Ferramentas

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)  

Você define funções com descrições claras e especificações de parâmetros. O modelo vê essas descrições em seu prompt do sistema e entende o que cada ferramenta faz.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Sua lógica de consulta do tempo
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// O Assistente é automaticamente conectado pelo Spring Boot com:
// - Bean ChatModel
// - Todos os métodos @Tool de classes @Component
// - ChatMemoryProvider para gerenciamento de sessão
```
  
> **🤖 Experimente com [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) e pergunte:  
> - "Como eu integraria uma API real de clima como OpenWeatherMap ao invés dos dados simulados?"  
> - "O que faz uma boa descrição de ferramenta que ajuda a IA a usá-la corretamente?"  
> - "Como lido com erros de API e limites de taxa nas implementações das ferramentas?"

### Tomada de Decisão

Quando um usuário pergunta "Qual é o clima em Seattle?", o modelo reconhece que precisa da ferramenta de clima. Ele gera uma chamada de função com o parâmetro de localização definido para "Seattle".

### Execução

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)  

O Spring Boot injeta automaticamente a interface declarativa `@AiService` com todas as ferramentas registradas, e o LangChain4j executa as chamadas às ferramentas automaticamente.

> **🤖 Experimente com [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) e pergunte:  
> - "Como funciona o padrão ReAct e por que é eficaz para agentes de IA?"  
> - "Como o agente decide qual ferramenta usar e em que ordem?"  
> - "O que acontece se a execução de uma ferramenta falhar - como devo tratar erros de forma robusta?"

### Geração de Resposta

O modelo recebe os dados do clima e os formata em uma resposta em linguagem natural para o usuário.

### Por Que Usar Serviços Declarativos de IA?

Este módulo usa a integração do LangChain4j com Spring Boot via interfaces declarativas `@AiService`:

- **Injeção automática Spring Boot** - ChatModel e ferramentas são injetados automaticamente  
- **Padrão @MemoryId** - Gerenciamento automático de memória baseado em sessão  
- **Instância única** - Assistente criado uma vez e reutilizado para melhor desempenho  
- **Execução com tipos seguros** - Métodos Java chamados diretamente com conversão de tipo  
- **Orquestração multi-turno** - Lida automaticamente com encadeamento de ferramentas  
- **Zero boilerplate** - Sem chamadas manuais AiServices.builder() ou HashMap de memória  

Abordagens alternativas (manual `AiServices.builder()`) exigem mais código e perdem benefícios da integração Spring Boot.

## Encadeamento de Ferramentas

**Encadeamento de Ferramentas** - A IA pode chamar múltiplas ferramentas em sequência. Pergunte "Qual o clima em Seattle e devo levar um guarda-chuva?" e veja como encadeia `getCurrentWeather` com raciocínio sobre chuva.

<a href="images/tool-chaining.png"><img src="../../../translated_images/pt-BR/tool-chaining.3b25af01967d6f7b.webp" alt="Encadeamento de Ferramentas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Chamadas sequenciais de ferramentas - a saída de uma ferramenta alimenta a decisão da próxima*

**Falhas Elegantes** - Peça o clima de uma cidade que não está nos dados simulados. A ferramenta retorna uma mensagem de erro, e a IA explica que não pode ajudar. As ferramentas falham com segurança.

Isso acontece em uma única rodada de conversa. O agente orquestra múltiplas chamadas de ferramentas autonomamente.

## Executar a Aplicação

**Verifique a implantação:**

Certifique-se que o arquivo `.env` exista no diretório raiz com credenciais Azure (criado durante o Módulo 01):  
```bash
cat ../.env  # Deve mostrar AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```
  
**Inicie a aplicação:**

> **Nota:** Se você já iniciou todas as aplicações usando `./start-all.sh` do Módulo 01, este módulo já estará rodando na porta 8084. Você pode pular os comandos de inicialização abaixo e ir direto para http://localhost:8084.

**Opção 1: Usando o Spring Boot Dashboard (Recomendado para usuários VS Code)**

O container de desenvolvimento inclui a extensão Spring Boot Dashboard, que fornece uma interface visual para gerenciar todas as aplicações Spring Boot. Você pode encontrá-la na Barra de Atividades no lado esquerdo do VS Code (procure o ícone Spring Boot).

Pelo Spring Boot Dashboard você pode:  
- Ver todas as aplicações Spring Boot disponíveis na workspace  
- Iniciar/parar aplicações com um clique  
- Visualizar logs em tempo real  
- Monitorar status da aplicação  

Basta clicar no botão de play ao lado de "tools" para iniciar este módulo, ou iniciar todos os módulos de uma vez.

<img src="../../../translated_images/pt-BR/dashboard.9b519b1a1bc1b30a.webp" alt="Spring Boot Dashboard" width="400"/>

**Opção 2: Usando scripts shell**

Inicie todas as aplicações web (módulos 01-04):

**Bash:**  
```bash
cd ..  # A partir do diretório raiz
./start-all.sh
```
  
**PowerShell:**  
```powershell
cd ..  # A partir do diretório raiz
.\start-all.ps1
```
  
Ou inicie apenas este módulo:

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
  
Ambos os scripts carregam automaticamente as variáveis de ambiente do arquivo `.env` raiz e irão construir os JARs se eles não existirem.

> **Nota:** Se preferir construir todos os módulos manualmente antes de iniciar:  
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
  
Abra http://localhost:8084 em seu navegador.

**Para parar a aplicação:**

**Bash:**  
```bash
./stop.sh  # Este módulo somente
# Ou
cd .. && ./stop-all.sh  # Todos os módulos
```
  
**PowerShell:**  
```powershell
.\stop.ps1  # Somente este módulo
# Ou
cd ..; .\stop-all.ps1  # Todos os módulos
```
  
## Usando a Aplicação

A aplicação oferece uma interface web onde você pode interagir com um agente de IA que tem acesso a ferramentas de clima e conversão de temperatura.

<a href="images/tools-homepage.png"><img src="../../../translated_images/pt-BR/tools-homepage.4b4cd8b2717f9621.webp" alt="Interface de Ferramentas do Agente de IA" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Interface das Ferramentas do Agente de IA - exemplos rápidos e interface de chat para interação com ferramentas*

### Experimente o Uso Simples de Ferramentas

Comece com uma solicitação direta: "Converter 100 graus Fahrenheit para Celsius". O agente reconhece que precisa da ferramenta de conversão de temperatura, a chama com os parâmetros corretos e retorna o resultado. Perceba como isso é natural - você não especificou qual ferramenta usar nem como chamá-la.

### Teste o Encadeamento de Ferramentas

Agora tente algo mais complexo: "Qual o clima em Seattle e converta para Fahrenheit?" Veja o agente trabalhar isso passo a passo. Primeiro obtém o clima (que retorna em Celsius), reconhece que precisa converter para Fahrenheit, chama a ferramenta de conversão e combina ambos os resultados em uma única resposta.

### Veja o Fluxo da Conversa

A interface de chat mantém o histórico da conversa, permitindo interações multi-turno. Você pode ver todas as consultas e respostas anteriores, facilitando acompanhar a conversa e entender como o agente constrói o contexto em múltiplas trocas.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/pt-BR/tools-conversation-demo.89f2ce9676080f59.webp" alt="Conversa com Múltiplas Chamadas de Ferramentas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Conversa multi-turno mostrando conversões simples, consultas de clima e encadeamento de ferramentas*

### Experimente com Diferentes Solicitações

Teste várias combinações:  
- Consultas de clima: "Qual o clima em Tóquio?"  
- Conversões de temperatura: "Quanto é 25°C em Kelvin?"  
- Consultas combinadas: "Verifique o clima em Paris e me diga se está acima de 20°C"  

Observe como o agente interpreta linguagem natural e mapeia para chamadas adequadas das ferramentas.

## Conceitos Principais

### Padrão ReAct (Raciocínio e Ação)

O agente alterna entre raciocinar (decidir o que fazer) e agir (usar ferramentas). Esse padrão permite resolução autônoma de problemas, não apenas resposta a instruções.

### Descrições de Ferramentas Importam

A qualidade das descrições das suas ferramentas afeta diretamente como o agente as usa. Descrições claras e específicas ajudam o modelo a entender quando e como chamar cada ferramenta.

### Gerenciamento de Sessão

A anotação `@MemoryId` permite gerenciamento automático de memória baseado em sessão. Cada ID de sessão recebe sua própria instância `ChatMemory` gerenciada pelo bean `ChatMemoryProvider`, eliminando a necessidade de rastreamento manual de memória.

### Tratamento de Erros

As ferramentas podem falhar - APIs podem expirar, parâmetros podem estar inválidos, serviços externos podem parar. Agentes para produção precisam de tratamento de erros para que o modelo possa explicar problemas ou tentar alternativas.

## Ferramentas Disponíveis

**Ferramentas de Clima** (dados simulados para demonstração):  
- Obter o clima atual para uma localização  
- Obter previsão para múltiplos dias  

**Ferramentas de Conversão de Temperatura**:  
- Celsius para Fahrenheit  
- Fahrenheit para Celsius  
- Celsius para Kelvin  
- Kelvin para Celsius  
- Fahrenheit para Kelvin  
- Kelvin para Fahrenheit  

Estes são exemplos simples, mas o padrão se estende a qualquer função: consultas a bancos de dados, chamadas de API, cálculos, operações com arquivos ou comandos de sistema.

## Quando Usar Agentes Baseados em Ferramentas

**Use ferramentas quando:**  
- A resposta requer dados em tempo real (clima, preços de ações, inventário)  
- Precisa realizar cálculos além da matemática simples  
- Acesso a bancos de dados ou APIs  
- Executar ações (enviar e-mails, criar tickets, atualizar registros)  
- Combinar múltiplas fontes de dados  

**Não use ferramentas quando:**  
- As perguntas podem ser respondidas com conhecimento geral  
- A resposta é puramente conversacional  
- A latência da ferramenta tornaria a experiência muito lenta  

## Próximos Passos

**Próximo Módulo:** [05-mcp - Protocolo de Contexto de Modelo (MCP)](../05-mcp/README.md)

---

**Navegação:** [← Anterior: Módulo 03 - RAG](../03-rag/README.md) | [Voltar ao Início](../README.md) | [Próximo: Módulo 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:  
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, esteja ciente de que traduções automáticas podem conter erros ou imprecisões. O documento original em sua língua nativa deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se tradução profissional realizada por humanos. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações incorretas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->