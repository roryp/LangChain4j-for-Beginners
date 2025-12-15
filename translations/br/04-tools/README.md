<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "aa23f106e7f53270924c9dd39c629004",
  "translation_date": "2025-12-13T18:49:05+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "br"
}
-->
# Módulo 04: Agentes de IA com Ferramentas

## Índice

- [O que você vai aprender](../../../04-tools)
- [Pré-requisitos](../../../04-tools)
- [Entendendo Agentes de IA com Ferramentas](../../../04-tools)
- [Como funciona a chamada de ferramentas](../../../04-tools)
  - [Definições de Ferramentas](../../../04-tools)
  - [Tomada de Decisão](../../../04-tools)
  - [Execução](../../../04-tools)
  - [Geração de Resposta](../../../04-tools)
- [Encadeamento de Ferramentas](../../../04-tools)
- [Executar a Aplicação](../../../04-tools)
- [Usando a Aplicação](../../../04-tools)
  - [Experimente o uso simples de ferramentas](../../../04-tools)
  - [Teste o encadeamento de ferramentas](../../../04-tools)
  - [Veja o fluxo da conversa](../../../04-tools)
  - [Observe o raciocínio](../../../04-tools)
  - [Experimente diferentes solicitações](../../../04-tools)
- [Conceitos-chave](../../../04-tools)
  - [Padrão ReAct (Raciocínio e Ação)](../../../04-tools)
  - [Descrições de Ferramentas Importam](../../../04-tools)
  - [Gerenciamento de Sessão](../../../04-tools)
  - [Tratamento de Erros](../../../04-tools)
- [Ferramentas Disponíveis](../../../04-tools)
- [Quando Usar Agentes Baseados em Ferramentas](../../../04-tools)
- [Próximos Passos](../../../04-tools)

## O que você vai aprender

Até agora, você aprendeu como ter conversas com IA, estruturar prompts de forma eficaz e fundamentar respostas em seus documentos. Mas ainda há uma limitação fundamental: modelos de linguagem só podem gerar texto. Eles não podem verificar o clima, realizar cálculos, consultar bancos de dados ou interagir com sistemas externos.

Ferramentas mudam isso. Ao dar ao modelo acesso a funções que ele pode chamar, você o transforma de um gerador de texto em um agente que pode tomar ações. O modelo decide quando precisa de uma ferramenta, qual ferramenta usar e quais parâmetros passar. Seu código executa a função e retorna o resultado. O modelo incorpora esse resultado em sua resposta.

## Pré-requisitos

- Módulo 01 concluído (recursos Azure OpenAI implantados)
- Arquivo `.env` no diretório raiz com credenciais Azure (criado pelo `azd up` no Módulo 01)

> **Nota:** Se você não concluiu o Módulo 01, siga as instruções de implantação lá primeiro.

## Entendendo Agentes de IA com Ferramentas

Um agente de IA com ferramentas segue um padrão de raciocínio e ação (ReAct):

1. Usuário faz uma pergunta
2. Agente raciocina sobre o que precisa saber
3. Agente decide se precisa de uma ferramenta para responder
4. Se sim, agente chama a ferramenta apropriada com os parâmetros corretos
5. Ferramenta executa e retorna dados
6. Agente incorpora o resultado e fornece a resposta final

<img src="../../../translated_images/react-pattern.86aafd3796f3fd13ae5b0218d4e91befabc04e00f97539df14f93d1ad9b8516f.br.png" alt="Padrão ReAct" width="800"/>

*O padrão ReAct - como agentes de IA alternam entre raciocínio e ação para resolver problemas*

Isso acontece automaticamente. Você define as ferramentas e suas descrições. O modelo lida com a tomada de decisão sobre quando e como usá-las.

## Como funciona a chamada de ferramentas

**Definições de Ferramentas** - [WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Você define funções com descrições claras e especificações de parâmetros. O modelo vê essas descrições em seu prompt de sistema e entende o que cada ferramenta faz.

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

// O assistente é automaticamente configurado pelo Spring Boot com:
// - Bean ChatModel
// - Todos os métodos @Tool das classes @Component
// - ChatMemoryProvider para gerenciamento de sessão
```

> **🤖 Experimente com o Chat do [GitHub Copilot](https://github.com/features/copilot):** Abra [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) e pergunte:
> - "Como eu integraria uma API real de clima como OpenWeatherMap em vez de dados simulados?"
> - "O que faz uma boa descrição de ferramenta que ajuda a IA a usá-la corretamente?"
> - "Como eu trato erros de API e limites de taxa nas implementações de ferramentas?"

**Tomada de Decisão**

Quando um usuário pergunta "Como está o tempo em Seattle?", o modelo reconhece que precisa da ferramenta de clima. Ele gera uma chamada de função com o parâmetro de localização definido como "Seattle".

**Execução** - [AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

O Spring Boot injeta automaticamente a interface declarativa `@AiService` com todas as ferramentas registradas, e o LangChain4j executa as chamadas de ferramentas automaticamente.

> **🤖 Experimente com o Chat do [GitHub Copilot](https://github.com/features/copilot):** Abra [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) e pergunte:
> - "Como funciona o padrão ReAct e por que ele é eficaz para agentes de IA?"
> - "Como o agente decide qual ferramenta usar e em que ordem?"
> - "O que acontece se a execução de uma ferramenta falhar - como devo tratar erros de forma robusta?"

**Geração de Resposta**

O modelo recebe os dados do clima e os formata em uma resposta em linguagem natural para o usuário.

### Por que usar serviços de IA declarativos?

Este módulo usa a integração do LangChain4j com Spring Boot e interfaces declarativas `@AiService`:

- **Injeção automática do Spring Boot** - ChatModel e ferramentas injetados automaticamente
- **Padrão @MemoryId** - Gerenciamento automático de memória baseado em sessão
- **Instância única** - Assistente criado uma vez e reutilizado para melhor desempenho
- **Execução com segurança de tipos** - Métodos Java chamados diretamente com conversão de tipos
- **Orquestração multi-turno** - Lida com encadeamento de ferramentas automaticamente
- **Zero boilerplate** - Sem chamadas manuais AiServices.builder() ou HashMap de memória

Abordagens alternativas (manual `AiServices.builder()`) exigem mais código e perdem os benefícios da integração com Spring Boot.

## Encadeamento de Ferramentas

**Encadeamento de Ferramentas** - A IA pode chamar múltiplas ferramentas em sequência. Pergunte "Como está o tempo em Seattle e devo levar um guarda-chuva?" e veja como ela encadeia `getCurrentWeather` com raciocínio sobre equipamentos para chuva.

<a href="images/tool-chaining.png"><img src="../../../translated_images/tool-chaining.3b25af01967d6f7b1d54117d54ba382c21c51176aaf3800084cae2e7dfc82508.br.png" alt="Encadeamento de Ferramentas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Chamadas sequenciais de ferramentas - a saída de uma ferramenta alimenta a próxima decisão*

**Falhas Graciosas** - Peça o clima de uma cidade que não está nos dados simulados. A ferramenta retorna uma mensagem de erro, e a IA explica que não pode ajudar. As ferramentas falham de forma segura.

Isso acontece em uma única interação da conversa. O agente orquestra múltiplas chamadas de ferramentas autonomamente.

## Executar a Aplicação

**Verifique a implantação:**

Certifique-se de que o arquivo `.env` existe no diretório raiz com as credenciais Azure (criado durante o Módulo 01):
```bash
cat ../.env  # Deve mostrar AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Inicie a aplicação:**

> **Nota:** Se você já iniciou todas as aplicações usando `./start-all.sh` do Módulo 01, este módulo já está rodando na porta 8084. Você pode pular os comandos de início abaixo e ir direto para http://localhost:8084.

**Opção 1: Usando o Spring Boot Dashboard (Recomendado para usuários do VS Code)**

O container de desenvolvimento inclui a extensão Spring Boot Dashboard, que fornece uma interface visual para gerenciar todas as aplicações Spring Boot. Você pode encontrá-la na Barra de Atividades à esquerda do VS Code (procure o ícone do Spring Boot).

No Spring Boot Dashboard, você pode:
- Ver todas as aplicações Spring Boot disponíveis no workspace
- Iniciar/parar aplicações com um clique
- Visualizar logs da aplicação em tempo real
- Monitorar o status da aplicação

Basta clicar no botão de play ao lado de "tools" para iniciar este módulo, ou iniciar todos os módulos de uma vez.

<img src="../../../translated_images/dashboard.9b519b1a1bc1b30af495a594f5c0213fecdbdf5bd9fb543d3c5467565773974a.br.png" alt="Spring Boot Dashboard" width="400"/>

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

## Usando a Aplicação

A aplicação fornece uma interface web onde você pode interagir com um agente de IA que tem acesso a ferramentas de clima e conversão de temperatura.

<a href="images/tools-homepage.png"><img src="../../../translated_images/tools-homepage.4b4cd8b2717f96216024b45b493ca1cd84935d6856416ea7a383b42f280d648c.br.png" alt="Interface de Ferramentas do Agente de IA" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Interface das Ferramentas do Agente de IA - exemplos rápidos e interface de chat para interagir com ferramentas*

**Experimente o uso simples de ferramentas**

Comece com uma solicitação direta: "Converta 100 graus Fahrenheit para Celsius". O agente reconhece que precisa da ferramenta de conversão de temperatura, a chama com os parâmetros corretos e retorna o resultado. Note como isso parece natural - você não especificou qual ferramenta usar nem como chamá-la.

**Teste o encadeamento de ferramentas**

Agora tente algo mais complexo: "Como está o tempo em Seattle e converta para Fahrenheit?" Veja o agente trabalhar isso em etapas. Primeiro obtém o clima (que retorna em Celsius), reconhece que precisa converter para Fahrenheit, chama a ferramenta de conversão e combina ambos os resultados em uma resposta.

**Veja o fluxo da conversa**

A interface de chat mantém o histórico da conversa, permitindo interações multi-turno. Você pode ver todas as consultas e respostas anteriores, facilitando acompanhar a conversa e entender como o agente constrói o contexto ao longo de múltiplas trocas.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/tools-conversation-demo.89f2ce9676080f596acc43e227bf70f3c0d6030ad91d84df81070abf08848608.br.png" alt="Conversa com Múltiplas Chamadas de Ferramentas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Conversa multi-turno mostrando conversões simples, consultas de clima e encadeamento de ferramentas*

**Experimente diferentes solicitações**

Tente várias combinações:
- Consultas de clima: "Como está o tempo em Tóquio?"
- Conversões de temperatura: "Quanto é 25°C em Kelvin?"
- Consultas combinadas: "Verifique o clima em Paris e me diga se está acima de 20°C"

Note como o agente interpreta a linguagem natural e a mapeia para chamadas apropriadas de ferramentas.

## Conceitos-chave

**Padrão ReAct (Raciocínio e Ação)**

O agente alterna entre raciocinar (decidir o que fazer) e agir (usar ferramentas). Esse padrão permite resolução autônoma de problemas em vez de apenas responder a instruções.

**Descrições de Ferramentas Importam**

A qualidade das descrições das suas ferramentas afeta diretamente o quão bem o agente as usa. Descrições claras e específicas ajudam o modelo a entender quando e como chamar cada ferramenta.

**Gerenciamento de Sessão**

A anotação `@MemoryId` habilita o gerenciamento automático de memória baseado em sessão. Cada ID de sessão recebe sua própria instância de `ChatMemory` gerenciada pelo bean `ChatMemoryProvider`, eliminando a necessidade de rastreamento manual de memória.

**Tratamento de Erros**

Ferramentas podem falhar - APIs podem expirar, parâmetros podem ser inválidos, serviços externos podem ficar indisponíveis. Agentes de produção precisam de tratamento de erros para que o modelo possa explicar problemas ou tentar alternativas.

## Ferramentas Disponíveis

**Ferramentas de Clima** (dados simulados para demonstração):
- Obter clima atual para uma localização
- Obter previsão para vários dias

**Ferramentas de Conversão de Temperatura**:
- Celsius para Fahrenheit
- Fahrenheit para Celsius
- Celsius para Kelvin
- Kelvin para Celsius
- Fahrenheit para Kelvin
- Kelvin para Fahrenheit

Estes são exemplos simples, mas o padrão se estende a qualquer função: consultas a banco de dados, chamadas de API, cálculos, operações de arquivo ou comandos de sistema.

## Quando Usar Agentes Baseados em Ferramentas

**Use ferramentas quando:**
- Responder requer dados em tempo real (clima, preços de ações, inventário)
- Você precisa realizar cálculos além da matemática simples
- Acessar bancos de dados ou APIs
- Tomar ações (enviar e-mails, criar tickets, atualizar registros)
- Combinar múltiplas fontes de dados

**Não use ferramentas quando:**
- Perguntas podem ser respondidas com conhecimento geral
- A resposta é puramente conversacional
- A latência da ferramenta tornaria a experiência muito lenta

## Próximos Passos

**Próximo Módulo:** [05-mcp - Protocolo de Contexto de Modelo (MCP)](../05-mcp/README.md)

---

**Navegação:** [← Anterior: Módulo 03 - RAG](../03-rag/README.md) | [Voltar ao Início](../README.md) | [Próximo: Módulo 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:  
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, esteja ciente de que traduções automáticas podem conter erros ou imprecisões. O documento original em seu idioma nativo deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se tradução profissional humana. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações incorretas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->