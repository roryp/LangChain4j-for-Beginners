<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "13ec450c12cdd1a863baa2b778f27cd7",
  "translation_date": "2025-12-30T23:42:54+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "pt"
}
-->
# Módulo 04: Agentes de IA com Ferramentas

## Table of Contents

- [O que vai aprender](../../../04-tools)
- [Pré-requisitos](../../../04-tools)
- [Compreender Agentes de IA com Ferramentas](../../../04-tools)
- [Como funciona a chamada de ferramentas](../../../04-tools)
  - [Definições de Ferramentas](../../../04-tools)
  - [Tomada de Decisão](../../../04-tools)
  - [Execução](../../../04-tools)
  - [Geração de Resposta](../../../04-tools)
- [Encadeamento de Ferramentas](../../../04-tools)
- [Executar a Aplicação](../../../04-tools)
- [Usar a Aplicação](../../../04-tools)
  - [Experimente um uso simples de ferramentas](../../../04-tools)
  - [Teste o encadeamento de ferramentas](../../../04-tools)
  - [Ver o fluxo da conversa](../../../04-tools)
  - [Observe o raciocínio](../../../04-tools)
  - [Experimente diferentes solicitações](../../../04-tools)
- [Conceitos Principais](../../../04-tools)
  - [Padrão ReAct (Raciocinar e Agir)](../../../04-tools)
  - [As descrições das ferramentas importam](../../../04-tools)
  - [Gestão de Sessões](../../../04-tools)
  - [Tratamento de Erros](../../../04-tools)
- [Ferramentas Disponíveis](../../../04-tools)
- [Quando usar agentes baseados em ferramentas](../../../04-tools)
- [Próximos passos](../../../04-tools)

## O que vai aprender

Até agora, aprendeu a conversar com IA, estruturar prompts de forma eficaz e fundamentar respostas nos seus documentos. Mas ainda existe uma limitação fundamental: os modelos de linguagem só conseguem gerar texto. Não podem verificar o tempo, efetuar cálculos, consultar bases de dados ou interagir com sistemas externos.

As ferramentas alteram isso. Ao dar ao modelo acesso a funções que pode invocar, transforma-o de um gerador de texto num agente capaz de executar ações. O modelo decide quando precisa de uma ferramenta, que ferramenta usar e que parâmetros passar. O seu código executa a função e devolve o resultado. O modelo incorpora esse resultado na sua resposta.

## Pré-requisitos

- Conclusão do Módulo 01 (recursos Azure OpenAI implantados)
- Ficheiro `.env` na diretoria raiz com credenciais Azure (criado por `azd up` no Módulo 01)

> **Nota:** Se não completou o Módulo 01, siga primeiro as instruções de implantação aí.

## Compreender Agentes de IA com Ferramentas

> **📝 Nota:** O termo "agentes" neste módulo refere-se a assistentes de IA melhorados com capacidades de chamada de ferramentas. Isto é diferente dos padrões de **Agentic AI** (agentes autónomos com planeamento, memória e raciocínio em múltiplos passos) que abordaremos em [Module 05: MCP](../05-mcp/README.md).

Um agente de IA com ferramentas segue um padrão de raciocínio e ação (ReAct):

1. O utilizador faz uma pergunta
2. O agente raciocina sobre o que precisa de saber
3. O agente decide se precisa de uma ferramenta para responder
4. Se sim, o agente chama a ferramenta apropriada com os parâmetros corretos
5. A ferramenta executa e devolve dados
6. O agente incorpora o resultado e fornece a resposta final

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.pt.png" alt="Padrão ReAct" width="800"/>

*O padrão ReAct - como os agentes de IA alternam entre raciocínio e ação para resolver problemas*

Isto acontece automaticamente. Define as ferramentas e as suas descrições. O modelo trata da tomada de decisão sobre quando e como usá-las.

## Como funciona a chamada de ferramentas

**Definições de Ferramentas** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Define funções com descrições claras e especificações de parâmetros. O modelo vê estas descrições no seu system prompt e compreende o que cada ferramenta faz.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // A sua lógica de consulta do tempo
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// O Assistente é automaticamente configurado pelo Spring Boot com:
// - bean ChatModel
// - Todos os métodos @Tool das classes @Component
// - ChatMemoryProvider para gestão de sessões
```

> **🤖 Experimente com o Chat do [GitHub Copilot](https://github.com/features/copilot):** Abra [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) e pergunte:
> - "Como integraria uma API de tempo real como a OpenWeatherMap em vez de dados mock?"
> - "O que torna uma boa descrição de ferramenta que ajuda a IA a usá-la corretamente?"
> - "Como trato erros de API e limites de taxa nas implementações de ferramentas?"

**Tomada de Decisão**

Quando um utilizador pergunta "Qual é o tempo em Seattle?", o modelo reconhece que precisa da ferramenta de tempo. Gera uma chamada de função com o parâmetro location definido para "Seattle".

**Execução** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

O Spring Boot injeta automaticamente a interface declarativa `@AiService` com todas as ferramentas registadas, e o LangChain4j executa chamadas a ferramentas automaticamente.

> **🤖 Experimente com o Chat do [GitHub Copilot](https://github.com/features/copilot):** Abra [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) e pergunte:
> - "Como funciona o padrão ReAct e porque é eficaz para agentes de IA?"
> - "Como decide o agente que ferramenta usar e em que ordem?"
> - "O que acontece se a execução de uma ferramenta falhar - como devo tratar erros de forma robusta?"

**Geração de Resposta**

O modelo recebe os dados do tempo e formata-os numa resposta em linguagem natural para o utilizador.

### Por que usar Serviços de IA Declarativos?

Este módulo usa a integração do LangChain4j com Spring Boot através de interfaces declarativas `@AiService`:

- **Injeção automática do Spring Boot** - ChatModel e as ferramentas são injetados automaticamente
- **Padrão @MemoryId** - Gestão automática de memória baseada na sessão
- **Instância única** - Assistente criado uma vez e reutilizado para melhor desempenho
- **Execução com segurança de tipos** - Métodos Java invocados diretamente com conversão de tipos
- **Orquestração multi-turno** - Trata automaticamente o encadeamento de ferramentas
- **Zero código repetitivo** - Sem chamadas manuais AiServices.builder() ou HashMap de memória

Abordagens alternativas (manual `AiServices.builder()`) exigem mais código e perdem os benefícios da integração com o Spring Boot.

## Encadeamento de Ferramentas

**Encadeamento de Ferramentas** - A IA pode chamar várias ferramentas em sequência. Pergunte "Qual é o tempo em Seattle e devo levar um guarda-chuva?" e veja-a encadear `getCurrentWeather` com raciocínio sobre equipamento para chuva.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.pt.png" alt="Encadeamento de Ferramentas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Chamadas sequenciais de ferramentas - a saída de uma ferramenta alimenta a decisão seguinte*

**Falhas tratadas** - Peça o tempo numa cidade que não está nos dados mock. A ferramenta devolve uma mensagem de erro, e a IA explica que não consegue ajudar. As ferramentas falham de forma segura.

Isto acontece numa única interação de conversa. O agente orquestra múltiplas chamadas de ferramentas autonomamente.

## Executar a Aplicação

**Verificar a implantação:**

Certifique-se de que o ficheiro `.env` existe na diretoria raiz com credenciais Azure (criado durante o Módulo 01):
```bash
cat ../.env  # Deve mostrar AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Iniciar a aplicação:**

> **Nota:** Se já iniciou todas as aplicações usando `./start-all.sh` do Módulo 01, este módulo já está a correr na porta 8084. Pode saltar os comandos de arranque abaixo e ir diretamente para http://localhost:8084.

**Opção 1: Usar o Spring Boot Dashboard (Recomendado para utilizadores VS Code)**

O dev container inclui a extensão Spring Boot Dashboard, que fornece uma interface visual para gerir todas as aplicações Spring Boot. Pode encontrá-la na Activity Bar no lado esquerdo do VS Code (procure o ícone do Spring Boot).

A partir do Spring Boot Dashboard, pode:
- Ver todas as aplicações Spring Boot disponíveis no workspace
- Iniciar/parar aplicações com um único clique
- Ver logs da aplicação em tempo real
- Monitorizar o estado da aplicação

Basta clicar no botão de reprodução ao lado de "tools" para iniciar este módulo, ou iniciar todos os módulos de uma vez.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.pt.png" alt="Painel do Spring Boot" width="400"/>

**Opção 2: Usar scripts shell**

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

Ambos os scripts carregam automaticamente variáveis de ambiente a partir do ficheiro `.env` na raiz e irão construir os JARs se estes não existirem.

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

Abra http://localhost:8084 no seu navegador.

**Para parar:**

**Bash:**
```bash
./stop.sh  # Apenas este módulo
# Ou
cd .. && ./stop-all.sh  # Todos os módulos
```

**PowerShell:**
```powershell
.\stop.ps1  # Apenas este módulo
# Ou
cd ..; .\stop-all.ps1  # Todos os módulos
```

## Usar a Aplicação

A aplicação fornece uma interface web onde pode interagir com um agente de IA que tem acesso a ferramentas de tempo e de conversão de temperatura.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.pt.png" alt="Interface de Ferramentas do Agente de IA" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*A interface AI Agent Tools - exemplos rápidos e interface de chat para interagir com ferramentas*

**Experimente um uso simples de ferramentas**

Comece com um pedido simples: "Converter 100 graus Fahrenheit para Celsius". O agente reconhece que precisa da ferramenta de conversão de temperatura, chama-a com os parâmetros corretos e devolve o resultado. Repare como isto é natural — não especificou qual ferramenta usar nem como a chamar.

**Teste o encadeamento de ferramentas**

Agora experimente algo mais complexo: "Qual é o tempo em Seattle e converta para Fahrenheit?" Observe o agente a trabalhar por passos. Primeiro obtém o tempo (que devolve em Celsius), reconhece que precisa de converter para Fahrenheit, chama a ferramenta de conversão e combina ambos os resultados numa única resposta.

**Ver o fluxo da conversa**

A interface de chat mantém o histórico da conversa, permitindo interações multi-turno. Pode ver todas as perguntas e respostas anteriores, tornando fácil acompanhar a conversa e compreender como o agente constrói contexto ao longo de várias trocas.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.pt.png" alt="Conversa com Chamadas de Múltiplas Ferramentas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Conversa multi-turno mostrando conversões simples, pesquisas de tempo e encadeamento de ferramentas*

**Experimente diferentes solicitações**

Tente várias combinações:
- Pesquisas do tempo: "Qual é o tempo em Tóquio?"
- Conversões de temperatura: "Quanto é 25°C em Kelvin?"
- Consultas combinadas: "Verifica o tempo em Paris e diz-me se está acima de 20°C"

Repare como o agente interpreta linguagem natural e mapeia-a para chamadas de ferramentas apropriadas.

## Conceitos Principais

**Padrão ReAct (Raciocinar e Agir)**

O agente alterna entre raciocinar (decidir o que fazer) e agir (usar ferramentas). Este padrão permite resolução autónoma de problemas em vez de apenas responder a instruções.

**As descrições das ferramentas importam**

A qualidade das descrições das suas ferramentas afeta diretamente a capacidade do agente de as usar bem. Descrições claras e específicas ajudam o modelo a compreender quando e como chamar cada ferramenta.

**Gestão de Sessões**

A anotação `@MemoryId` permite gestão automática de memória baseada na sessão. Cada ID de sessão obtém a sua própria instância `ChatMemory` gerida pelo bean `ChatMemoryProvider`, eliminando a necessidade de rastreio manual da memória.

**Tratamento de Erros**

As ferramentas podem falhar - APIs fazem timeout, parâmetros podem ser inválidos, serviços externos ficam indisponíveis. Agentes de produção precisam de tratamento de erros para que o modelo possa explicar problemas ou tentar alternativas.

## Ferramentas Disponíveis

**Ferramentas de Tempo** (dados mock para demonstração):
- Obter o tempo atual para uma localização
- Obter previsão para vários dias

**Ferramentas de Conversão de Temperatura**:
- Celsius para Fahrenheit
- Fahrenheit para Celsius
- Celsius para Kelvin
- Kelvin para Celsius
- Fahrenheit para Kelvin
- Kelvin para Fahrenheit

Estes são exemplos simples, mas o padrão estende-se a qualquer função: consultas a bases de dados, chamadas a APIs, cálculos, operações de ficheiros ou comandos do sistema.

## Quando usar agentes baseados em ferramentas

**Use ferramentas quando:**
- A resposta requer dados em tempo real (tempo, preços de ações, inventário)
- Precisa efetuar cálculos para além de matemática simples
- Aceder a bases de dados ou APIs
- Executar ações (enviar emails, criar tickets, atualizar registos)
- Combinar múltiplas fontes de dados

**Não use ferramentas quando:**
- As perguntas podem ser respondidas com conhecimento geral
- A resposta é puramente conversacional
- A latência da ferramenta tornaria a experiência demasiado lenta

## Próximos passos

**Próximo Módulo:** [05-mcp - Model Context Protocol (MCP)](../05-mcp/README.md)

---

**Navegação:** [← Anterior: Módulo 03 - RAG](../03-rag/README.md) | [Voltar ao Início](../README.md) | [Seguinte: Módulo 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Isenção de responsabilidade**:
Este documento foi traduzido usando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos por garantir a precisão, tenha em atenção que traduções automáticas podem conter erros ou imprecisões. O documento original, no seu idioma original, deve ser considerado a fonte autoritativa. Para informação crítica, recomenda-se uma tradução profissional realizada por um humano. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações erradas decorrentes da utilização desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->