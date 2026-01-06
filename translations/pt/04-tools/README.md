<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-05T23:00:03+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "pt"
}
-->
# Módulo 04: Agentes de IA com Ferramentas

## Índice

- [O que vai aprender](../../../04-tools)
- [Pré-requisitos](../../../04-tools)
- [Compreendendo Agentes de IA com Ferramentas](../../../04-tools)
- [Como funciona a chamada a ferramentas](../../../04-tools)
  - [Definições de Ferramentas](../../../04-tools)
  - [Tomada de Decisão](../../../04-tools)
  - [Execução](../../../04-tools)
  - [Geração de Resposta](../../../04-tools)
- [Encadeamento de Ferramentas](../../../04-tools)
- [Executar a Aplicação](../../../04-tools)
- [Usar a Aplicação](../../../04-tools)
  - [Experimentar uso simples de ferramentas](../../../04-tools)
  - [Testar encadeamento de ferramentas](../../../04-tools)
  - [Ver fluxo da conversa](../../../04-tools)
  - [Experimentar diferentes pedidos](../../../04-tools)
- [Conceitos principais](../../../04-tools)
  - [Padrão ReAct (Raciocinar e Agir)](../../../04-tools)
  - [Descrições das Ferramentas são importantes](../../../04-tools)
  - [Gestão de Sessões](../../../04-tools)
  - [Tratamento de Erros](../../../04-tools)
- [Ferramentas disponíveis](../../../04-tools)
- [Quando usar agentes baseados em ferramentas](../../../04-tools)
- [Próximos passos](../../../04-tools)

## O que vai aprender

Até agora, aprendeu a ter conversas com IA, estruturar prompts de forma eficaz e fundamentar respostas nos seus documentos. Mas existe ainda uma limitação fundamental: os modelos de linguagem só conseguem gerar texto. Não podem consultar o tempo, fazer cálculos, consultar bases de dados ou interagir com sistemas externos.

As ferramentas mudam isto. Ao dar ao modelo acesso a funções que pode chamar, transforma-o de um gerador de texto num agente que pode tomar ações. O modelo decide quando precisa de uma ferramenta, qual ferramenta usar e que parâmetros passar. O seu código executa a função e retorna o resultado. O modelo incorpora esse resultado na sua resposta.

## Pré-requisitos

- Módulo 01 concluído (recursos Azure OpenAI implantados)
- Ficheiro `.env` na diretoria raiz com credenciais Azure (criado pelo `azd up` no Módulo 01)

> **Nota:** Se não completou o Módulo 01, siga primeiro as instruções de implantação aí indicadas.

## Compreendendo Agentes de IA com Ferramentas

> **📝 Nota:** O termo "agentes" neste módulo refere-se a assistentes IA aprimorados com capacidades de chamada a ferramentas. Isto é diferente dos padrões **Agentic AI** (agentes autónomos com planeamento, memória e raciocínio multi-etapa) que vamos abordar em [Módulo 05: MCP](../05-mcp/README.md).

Um agente de IA com ferramentas segue o padrão de raciocínio e ação (ReAct):

1. O utilizador faz uma pergunta
2. O agente raciocina sobre o que precisa de saber
3. O agente decide se precisa de uma ferramenta para responder
4. Se sim, o agente chama a ferramenta apropriada com os parâmetros certos
5. A ferramenta executa e devolve dados
6. O agente incorpora o resultado e fornece a resposta final

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13.pt.png" alt="Padrão ReAct" width="800"/>

*O padrão ReAct - como os agentes de IA alternam entre raciocinar e agir para resolver problemas*

Isto acontece automaticamente. Você define as ferramentas e as suas descrições. O modelo trata da tomada de decisão sobre quando e como usá-las.

## Como funciona a chamada a ferramentas

### Definições de Ferramentas

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Define funções com descrições claras e especificações de parâmetros. O modelo vê essas descrições no seu prompt de sistema e compreende o que cada ferramenta faz.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // A sua lógica de pesquisa meteorológica
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// O assistente é automaticamente ligado pelo Spring Boot com:
// - Bean ChatModel
// - Todos os métodos @Tool das classes @Component
// - ChatMemoryProvider para gestão de sessões
```

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) e pergunte:
> - "Como integraria uma API real do tempo como o OpenWeatherMap em vez de dados simulados?"
> - "O que faz uma boa descrição de ferramenta que ajude a IA a usá-la corretamente?"
> - "Como lidar com erros de API e limites de taxa nas implementações das ferramentas?"

### Tomada de Decisão

Quando um utilizador pergunta "Qual é o tempo em Seattle?", o modelo reconhece que precisa da ferramenta de tempo. Gera uma chamada de função com o parâmetro localização definido como "Seattle".

### Execução

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

O Spring Boot injeta automaticamente a interface declarativa `@AiService` com todas as ferramentas registadas, e o LangChain4j executa as chamadas de ferramentas automaticamente.

> **🤖 Experimente com o [GitHub Copilot](https://github.com/features/copilot) Chat:** Abra [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) e pergunte:
> - "Como funciona o padrão ReAct e porque é eficaz para agentes de IA?"
> - "Como o agente decide qual ferramenta usar e em que ordem?"
> - "O que acontece se uma execução de ferramenta falhar - como devo tratar erros de forma robusta?"

### Geração de Resposta

O modelo recebe os dados meteorológicos e formata-os numa resposta em linguagem natural para o utilizador.

### Porque usar Serviços de IA Declarativos?

Este módulo usa a integração LangChain4j com Spring Boot e interfaces declarativas `@AiService`:

- **Injeção automática no Spring Boot** – ChatModel e ferramentas injetados automaticamente
- **Padrão @MemoryId** – Gestão automática de memória baseada em sessões
- **Instância única** – Assistente criado uma vez e reutilizado para melhor desempenho
- **Execução tipo-segura** – Métodos Java chamados diretamente com conversão de tipos
- **Orquestração multi-turno** – Lida automaticamente com encadeamento de ferramentas
- **Zero boilerplate** – Sem chamadas manuais a AiServices.builder() ou HashMap de memória

Abordagens alternativas (builder manual AiServices.builder()) requerem mais código e perdem os benefícios da integração com Spring Boot.

## Encadeamento de Ferramentas

**Encadeamento de Ferramentas** – A IA pode chamar múltiplas ferramentas em sequência. Pergunte "Qual é o tempo em Seattle e devo levar um guarda-chuva?" e veja como encadeia `getCurrentWeather` com raciocínio sobre equipamentos para chuva.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b.pt.png" alt="Encadeamento de Ferramentas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Chamadas sequenciais a ferramentas - a saída de uma alimenta a decisão seguinte*

**Falhas Graciosas** – Peça o tempo numa cidade que não está nos dados simulados. A ferramenta retorna uma mensagem de erro e a IA explica que não consegue ajudar. As ferramentas falham de forma segura.

Isto acontece numa única interação de conversa. O agente orquestra múltiplas chamadas a ferramentas autonomamente.

## Executar a Aplicação

**Verifique a implantação:**

Garanta que o ficheiro `.env` existe na diretoria raiz com as credenciais Azure (criado no Módulo 01):
```bash
cat ../.env  # Deve mostrar AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Inicie a aplicação:**

> **Nota:** Se já iniciou todas as aplicações com `./start-all.sh` no Módulo 01, este módulo já está a correr na porta 8084. Pode pular os comandos de início abaixo e ir diretamente a http://localhost:8084.

**Opção 1: Usando o Painel Spring Boot (Recomendado para utilizadores VS Code)**

O contentor de desenvolvimento inclui a extensão Spring Boot Dashboard, que fornece uma interface visual para gerir todas as aplicações Spring Boot. Pode encontrá-la na Barra de Atividades no lado esquerdo do VS Code (procure o ícone Spring Boot).

No Painel Spring Boot pode:
- Ver todas as aplicações Spring Boot disponíveis no espaço de trabalho
- Iniciar/parar aplicações com um clique
- Ver logs da aplicação em tempo real
- Monitorizar o estado da aplicação

Basta clicar no botão play junto a "tools" para iniciar este módulo, ou iniciar todos os módulos de uma vez.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30a.pt.png" alt="Painel Spring Boot" width="400"/>

**Opção 2: Usando scripts shell**

Inicie todas as aplicações web (módulos 01-04):

**Bash:**
```bash
cd ..  # Do diretório raiz
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Do diretório raiz
.\start-all.ps1
```

Ou inicie só este módulo:

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

Ambos os scripts carregam automaticamente as variáveis de ambiente do ficheiro `.env` na raiz e constroem os JARs se não existirem.

> **Nota:** Se preferir construir manualmente todos os módulos antes de iniciar:
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

A aplicação fornece uma interface web onde pode interagir com um agente IA que tem acesso a ferramentas de tempo e conversão de temperatura.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f9621.pt.png" alt="Interface de Ferramentas do Agente IA" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Interface das Ferramentas do Agente IA - exemplos rápidos e interface de chat para interagir com ferramentas*

### Experimentar uso simples de ferramentas

Comece com um pedido simples: "Converter 100 graus Fahrenheit para Celsius". O agente reconhece que precisa da ferramenta de conversão de temperatura, chama-a com os parâmetros corretos e devolve o resultado. Note como isto é natural – não especificou qual ferramenta usar nem como chamá-la.

### Testar encadeamento de ferramentas

Agora experimente algo mais complexo: "Qual é o tempo em Seattle e converte para Fahrenheit?" Veja o agente resolver isto em passos. Primeiro obtém o tempo (que retorna em Celsius), reconhece que precisa converter para Fahrenheit, chama a ferramenta de conversão e combina os dois resultados numa única resposta.

### Ver fluxo da conversa

A interface de chat mantém o histórico da conversa, permitindo interações multi-turno. Pode ver todas as perguntas e respostas anteriores, tornando fácil acompanhar a conversa e perceber como o agente constrói contexto ao longo de várias trocas.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f59.pt.png" alt="Conversa com múltiplas chamadas a ferramentas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Conversa multi-turno mostrando conversões simples, consultas meteorológicas e encadeamento de ferramentas*

### Experimentar diferentes pedidos

Experimente várias combinações:
- Consultas meteorológicas: "Qual é o tempo em Tóquio?"
- Conversões de temperatura: "Quanto é 25°C em Kelvin?"
- Consultas combinadas: "Consulta o tempo em Paris e diz-me se está acima de 20°C"

Note como o agente interpreta linguagem natural e a mapeia para chamadas apropriadas a ferramentas.

## Conceitos principais

### Padrão ReAct (Raciocinar e Agir)

O agente alterna entre raciocinar (decidir o que fazer) e agir (usar ferramentas). Este padrão permite resolução autónoma de problemas em vez de apenas responder a instruções.

### Descrições das Ferramentas são importantes

A qualidade das descrições das suas ferramentas afeta diretamente a forma como o agente as usa. Descrições claras e específicas ajudam o modelo a entender quando e como chamar cada ferramenta.

### Gestão de Sessões

A anotação `@MemoryId` permite gestão automática de memória baseada em sessões. Cada ID de sessão obtém a sua própria instância `ChatMemory` gerida pelo bean `ChatMemoryProvider`, eliminando a necessidade de rastreio manual de memória.

### Tratamento de Erros

As ferramentas podem falhar – APIs expiram, parâmetros podem estar inválidos, serviços externos podem cair. Agentes de produção precisam de tratamento de erros para que o modelo possa explicar problemas ou tentar alternativas.

## Ferramentas disponíveis

**Ferramentas de Tempo** (dados simulados para demonstração):
- Obter o tempo atual para um local
- Obter previsão para múltiplos dias

**Ferramentas de Conversão de Temperatura**:
- Celsius para Fahrenheit
- Fahrenheit para Celsius
- Celsius para Kelvin
- Kelvin para Celsius
- Fahrenheit para Kelvin
- Kelvin para Fahrenheit

Estes são exemplos simples, mas o padrão estende-se a qualquer função: consultas a base de dados, chamadas API, cálculos, operações em ficheiros ou comandos de sistema.

## Quando usar agentes baseados em ferramentas

**Use ferramentas quando:**
- A resposta requer dados em tempo real (tempo, preços de ações, inventário)
- Precisa realizar cálculos para além de matemática simples
- Aceder a bases de dados ou APIs
- Tomar ações (enviar e-mails, criar tickets, atualizar registos)
- Combinar múltiplas fontes de dados

**Não use ferramentas quando:**
- As perguntas podem ser respondidas com conhecimento geral
- A resposta é puramente conversacional
- A latência das ferramentas tornaria a experiência muito lenta

## Próximos passos

**Próximo Módulo:** [05-mcp - Protocolo de Contexto de Modelo (MCP)](../05-mcp/README.md)

---

**Navegação:** [← Anterior: Módulo 03 - RAG](../03-rag/README.md) | [Voltar ao Índice](../README.md) | [Seguinte: Módulo 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos pela precisão, por favor tenha em consideração que traduções automáticas podem conter erros ou imprecisões. O documento original na sua língua nativa deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se tradução profissional humana. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações erradas decorrentes da utilização desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->