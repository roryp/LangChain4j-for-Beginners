# Glossário LangChain4j

## Índice

- [Conceitos Principais](../../../docs)
- [Componentes LangChain4j](../../../docs)
- [Conceitos de IA/ML](../../../docs)
- [Guardrails](../../../docs)
- [Engenharia de Prompt](../../../docs)
- [RAG (Geração Aumentada por Recuperação)](../../../docs)
- [Agentes e Ferramentas](../../../docs)
- [Módulo Agentic](../../../docs)
- [Protocolo de Contexto do Modelo (MCP)](../../../docs)
- [Serviços Azure](../../../docs)
- [Testes e Desenvolvimento](../../../docs)

Referência rápida para termos e conceitos usados ao longo do curso.

## Conceitos Principais

**Agente de IA** – Sistema que usa IA para raciocinar e agir autonomamente. [Módulo 04](../04-tools/README.md)

**Cadeia** – Sequência de operações onde a saída alimenta o passo seguinte.

**Chunking** – Divisão de documentos em pedaços menores. Típico: 300-500 tokens com sobreposição. [Módulo 03](../03-rag/README.md)

**Janela de Contexto** – Máximo de tokens que um modelo pode processar. GPT-5: 400K tokens.

**Embeddings** – Vetores numéricos que representam o significado do texto. [Módulo 03](../03-rag/README.md)

**Chamada de Função** – Modelo gera pedidos estruturados para chamar funções externas. [Módulo 04](../04-tools/README.md)

**Alucinação** – Quando modelos geram informação incorreta, mas plausível.

**Prompt** – Texto de entrada para um modelo de linguagem. [Módulo 02](../02-prompt-engineering/README.md)

**Pesquisa Semântica** – Pesquisa por significado usando embeddings, não palavras-chave. [Módulo 03](../03-rag/README.md)

**Com Estado vs Sem Estado** – Sem estado: sem memória. Com estado: mantém histórico de conversação. [Módulo 01](../01-introduction/README.md)

**Tokens** – Unidades básicas de texto que os modelos processam. Afetam custos e limites. [Módulo 01](../01-introduction/README.md)

**Encadeamento de Ferramentas** – Execução sequencial de ferramentas onde a saída informa a chamada seguinte. [Módulo 04](../04-tools/README.md)

## Componentes LangChain4j

**AiServices** – Cria interfaces de serviço AI com tipagem segura.

**OpenAiOfficialChatModel** – Cliente unificado para modelos OpenAI e Azure OpenAI.

**OpenAiOfficialEmbeddingModel** – Cria embeddings usando o cliente oficial OpenAI (suporta OpenAI e Azure OpenAI).

**ChatModel** – Interface principal para modelos de linguagem.

**ChatMemory** – Mantém histórico de conversação.

**ContentRetriever** – Encontra fragmentos de documentos relevantes para RAG.

**DocumentSplitter** – Divide documentos em fragmentos.

**EmbeddingModel** – Converte texto em vetores numéricos.

**EmbeddingStore** – Armazena e recupera embeddings.

**MessageWindowChatMemory** – Mantém janela deslizante das mensagens recentes.

**PromptTemplate** – Cria prompts reutilizáveis com espaços reservados `{{variável}}`.

**TextSegment** – Fragmento de texto com metadados. Usado em RAG.

**ToolExecutionRequest** – Representa pedido de execução de ferramenta.

**UserMessage / AiMessage / SystemMessage** – Tipos de mensagens na conversação.

## Conceitos IA/ML

**Aprendizagem Few-Shot** – Fornecer exemplos nos prompts. [Módulo 02](../02-prompt-engineering/README.md)

**Modelo de Linguagem Grande (LLM)** – Modelos de IA treinados com enormes quantidades de texto.

**Esforço de Raciocínio** – Parâmetro do GPT-5 que controla profundidade do pensamento. [Módulo 02](../02-prompt-engineering/README.md)

**Temperatura** – Controla aleatoriedade da saída. Baixa=determinístico, alta=criativo.

**Base de Dados Vetorial** – Base de dados especializada para embeddings. [Módulo 03](../03-rag/README.md)

**Aprendizagem Zero-Shot** – Realizar tarefas sem exemplos. [Módulo 02](../02-prompt-engineering/README.md)

## Guardrails - [Módulo 00](../00-quick-start/README.md)

**Defesa em Profundidade** – Abordagem de segurança multicamadas combinando guardrails a nível de aplicação com filtros de segurança do fornecedor.

**Bloqueio Rigoroso** – Fornecedor retorna erro HTTP 400 para violações graves de conteúdo.

**InputGuardrail** – Interface LangChain4j para validar entrada do utilizador antes de chegar ao LLM. Poupa custo e latência bloqueando prompts nocivos cedo.

**InputGuardrailResult** – Tipo de retorno para validação de guardrail: `success()` ou `fatal("razão")`.

**OutputGuardrail** – Interface para validar respostas de IA antes de devolver aos utilizadores.

**Filtros de Segurança do Fornecedor** – Filtros de conteúdo incorporados dos fornecedores de IA (ex.: GitHub Models) que detetam violações ao nível da API.

**Recusa Suave** – Modelo recusa educadamente responder sem gerar erro.

## Engenharia de Prompt - [Módulo 02](../02-prompt-engineering/README.md)

**Cadeia de Pensamento** – Raciocínio passo a passo para maior precisão.

**Saída Constrangida** – Aplicação de formato ou estrutura específicos.

**Alta Disposição** – Padrão GPT-5 para raciocínio detalhado.

**Baixa Disposição** – Padrão GPT-5 para respostas rápidas.

**Conversação Multi-Turno** – Manutenção de contexto ao longo de trocas.

**Prompting Baseado em Papel** – Definir a persona do modelo via mensagens do sistema.

**Autorreflexão** – Modelo avalia e melhora sua saída.

**Análise Estruturada** – Quadro fixo de avaliação.

**Padrão de Execução de Tarefas** – Planear → Executar → Resumir.

## RAG (Geração Aumentada por Recuperação) - [Módulo 03](../03-rag/README.md)

**Pipeline de Processamento de Documentos** – Carregar → fragmentar → embedar → armazenar.

**Armazenamento de Embeddings em Memória** – Armazenamento não persistente para testes.

**RAG** – Combina recuperação com geração para fundamentar respostas.

**Pontuação de Similaridade** – Medida (0-1) de similaridade semântica.

**Referência de Fonte** – Metadados sobre o conteúdo recuperado.

## Agentes e Ferramentas - [Módulo 04](../04-tools/README.md)

**Anotação @Tool** – Marca métodos Java como ferramentas chamáveis por IA.

**Padrão ReAct** – Raciocinar → Agir → Observar → Repetir.

**Gestão de Sessão** – Contextos separados para utilizadores distintos.

**Ferramenta** – Função que um agente de IA pode invocar.

**Descrição da Ferramenta** – Documentação do propósito e parâmetros da ferramenta.

## Módulo Agentic - [Módulo 05](../05-mcp/README.md)

**Anotação @Agent** – Marca interfaces como agentes de IA com definição declarativa de comportamento.

**Agent Listener** – Gancho para monitorizar execução do agente via `beforeAgentInvocation()` e `afterAgentInvocation()`.

**Agentic Scope** – Memória partilhada onde agentes armazenam resultados usando `outputKey` para consumo por agentes downstream.

**AgenticServices** – Fábrica para criação de agentes usando `agentBuilder()` e `supervisorBuilder()`.

**Fluxo Condicional** – Roteamento baseado em condições para agentes especialistas diferentes.

**Human-in-the-Loop** – Padrão de fluxo acrescentando pontos de controlo humanos para aprovação ou revisão de conteúdo.

**langchain4j-agentic** – Dependência Maven para construção declarativa de agentes (experimental).

**Fluxo de Loop** – Itera execução do agente até satisfazer condição (ex.: pontuação de qualidade ≥ 0.8).

**outputKey** – Parâmetro de anotação do agente especificando onde resultados são armazenados no Agentic Scope.

**Fluxo Paralelo** – Executa múltiplos agentes simultaneamente para tarefas independentes.

**Estratégia de Resposta** – Como o supervisor formula a resposta final: LAST, SUMMARY, ou SCORED.

**Fluxo Sequencial** – Executa agentes em ordem onde a saída flui para próxima etapa.

**Padrão de Agente Supervisor** – Padrão avançado agentic onde um LLM supervisor decide dinamicamente quais subagentes invocar.

## Protocolo de Contexto do Modelo (MCP) - [Módulo 05](../05-mcp/README.md)

**langchain4j-mcp** – Dependência Maven para integração MCP no LangChain4j.

**MCP** – Protocolo de Contexto do Modelo: padrão para conectar aplicações IA a ferramentas externas. Construir uma vez, usar em todo lado.

**Cliente MCP** – Aplicação que conecta a servidores MCP para descobrir e usar ferramentas.

**Servidor MCP** – Serviço que expõe ferramentas via MCP com descrições claras e esquemas de parâmetros.

**McpToolProvider** – Componente LangChain4j que encapsula ferramentas MCP para uso em serviços IA e agentes.

**McpTransport** – Interface para comunicação MCP. Implementações incluem Stdio e HTTP.

**Transporte Stdio** – Transporte local via stdin/stdout. Útil para acesso a sistema de ficheiros ou ferramentas de linha de comando.

**StdioMcpTransport** – Implementação LangChain4j que cria servidor MCP como subprocesso.

**Descoberta de Ferramentas** – Cliente questiona servidor sobre ferramentas disponíveis com descrições e esquemas.

## Serviços Azure - [Módulo 01](../01-introduction/README.md)

**Azure AI Search** – Pesquisa na nuvem com capacidades vetoriais. [Módulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** – Automatiza deploy de recursos Azure.

**Azure OpenAI** – Serviço IA empresarial da Microsoft.

**Bicep** – Linguagem de infraestrutura como código Azure. [Guia de Infraestrutura](../01-introduction/infra/README.md)

**Nome do Deploy** – Nome para deployment de modelo no Azure.

**GPT-5** – Último modelo OpenAI com controlo de raciocínio. [Módulo 02](../02-prompt-engineering/README.md)

## Testes e Desenvolvimento - [Guia de Testes](TESTING.md)

**Dev Container** – Ambiente de desenvolvimento conteinerizado. [Configuração](../../../.devcontainer/devcontainer.json)

**GitHub Models** – Playground gratuito de modelos IA. [Módulo 00](../00-quick-start/README.md)

**Testes em Memória** – Testes com armazenamento em memória.

**Testes de Integração** – Testes com infraestrutura real.

**Maven** – Ferramenta de automação de build Java.

**Mockito** – Framework Java para mocking.

**Spring Boot** – Framework Java para aplicações. [Módulo 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos pela precisão, esteja ciente de que traduções automáticas podem conter erros ou imprecisões. O documento original na sua língua nativa deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se a tradução profissional realizada por humanos. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações incorretas decorrentes da utilização desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->