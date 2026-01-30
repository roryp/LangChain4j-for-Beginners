# Glossário LangChain4j

## Índice

- [Conceitos Centrais](../../../docs)
- [Componentes LangChain4j](../../../docs)
- [Conceitos de IA/ML](../../../docs)
- [Guardrails](../../../docs)
- [Engenharia de Prompt](../../../docs)
- [RAG (Geração Aumentada por Recuperação)](../../../docs)
- [Agentes e Ferramentas](../../../docs)
- [Módulo Agentic](../../../docs)
- [Protocolo de Contexto de Modelo (MCP)](../../../docs)
- [Serviços Azure](../../../docs)
- [Testes e Desenvolvimento](../../../docs)

Referência rápida para termos e conceitos utilizados ao longo do curso.

## Conceitos Centrais

**Agente de IA** - Sistema que usa IA para raciocinar e agir autonomamente. [Módulo 04](../04-tools/README.md)

**Cadeia** - Sequência de operações onde a saída alimenta o próximo passo.

**Divisão em Pedaços (Chunking)** - Quebrar documentos em partes menores. Típico: 300-500 tokens com sobreposição. [Módulo 03](../03-rag/README.md)

**Janela de Contexto** - Máximo de tokens que um modelo pode processar. GPT-5: 400K tokens.

**Embeddings** - Vetores numéricos que representam o significado do texto. [Módulo 03](../03-rag/README.md)

**Chamada de Função** - Modelo gera solicitações estruturadas para chamar funções externas. [Módulo 04](../04-tools/README.md)

**Alucinação** - Quando modelos geram informações incorretas, porém plausíveis.

**Prompt** - Entrada de texto para um modelo de linguagem. [Módulo 02](../02-prompt-engineering/README.md)

**Busca Semântica** - Pesquisa por significado usando embeddings, não palavras-chave. [Módulo 03](../03-rag/README.md)

**Com Estado vs Sem Estado** - Sem estado: sem memória. Com estado: mantém histórico da conversa. [Módulo 01](../01-introduction/README.md)

**Tokens** - Unidades básicas de texto que os modelos processam. Afeta custos e limites. [Módulo 01](../01-introduction/README.md)

**Encadeamento de Ferramentas** - Execução sequencial de ferramentas onde a saída informa a próxima chamada. [Módulo 04](../04-tools/README.md)

## Componentes LangChain4j

**AiServices** - Cria interfaces de serviço de IA tipadas.

**OpenAiOfficialChatModel** - Cliente unificado para modelos OpenAI e Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Cria embeddings usando cliente oficial OpenAI (suporta OpenAI e Azure OpenAI).

**ChatModel** - Interface principal para modelos de linguagem.

**ChatMemory** - Mantém histórico da conversa.

**ContentRetriever** - Encontra pedaços de documentos relevantes para RAG.

**DocumentSplitter** - Divide documentos em pedaços.

**EmbeddingModel** - Converte texto em vetores numéricos.

**EmbeddingStore** - Armazena e recupera embeddings.

**MessageWindowChatMemory** - Mantém janela deslizante de mensagens recentes.

**PromptTemplate** - Cria prompts reutilizáveis com espaços reservados `{{variable}}`.

**TextSegment** - Pedaço de texto com metadados. Usado em RAG.

**ToolExecutionRequest** - Representa solicitação de execução de ferramenta.

**UserMessage / AiMessage / SystemMessage** - Tipos de mensagem de conversa.

## Conceitos de IA/ML

**Aprendizado Few-Shot** - Fornecendo exemplos nos prompts. [Módulo 02](../02-prompt-engineering/README.md)

**Modelo de Linguagem Grande (LLM)** - Modelos de IA treinados em vastos dados textuais.

**Esforço de Raciocínio** - Parâmetro GPT-5 que controla profundidade do pensamento. [Módulo 02](../02-prompt-engineering/README.md)

**Temperatura** - Controla aleatoriedade da saída. Baixa=determinístico, alta=criativo.

**Banco de Dados Vetorial** - Banco especializado para embeddings. [Módulo 03](../03-rag/README.md)

**Aprendizado Zero-Shot** - Realizar tarefas sem exemplos. [Módulo 02](../02-prompt-engineering/README.md)

## Guardrails - [Módulo 00](../00-quick-start/README.md)

**Defesa em Profundidade** - Abordagem de segurança multicamadas combinando guardrails de nível de aplicação com filtros de segurança do provedor.

**Bloqueio Rígido** - Provedor retorna erro HTTP 400 para violações graves de conteúdo.

**InputGuardrail** - Interface LangChain4j para validar entrada do usuário antes de chegar ao LLM. Economiza custo e latência bloqueando prompts prejudiciais cedo.

**InputGuardrailResult** - Tipo de retorno para validação de guardrail: `success()` ou `fatal("razão")`.

**OutputGuardrail** - Interface para validar respostas de IA antes de retornar aos usuários.

**Filtros de Segurança do Provedor** - Filtros de conteúdo incorporados dos provedores de IA (ex.: GitHub Models) que capturam violações no nível da API.

**Recusa Suave** - Modelo recusa educadamente responder sem gerar erro.

## Engenharia de Prompt - [Módulo 02](../02-prompt-engineering/README.md)

**Cadeia de Pensamento** - Raciocínio passo a passo para maior precisão.

**Saída Constrangida** - Aplicar formato ou estrutura específicos.

**Alta Disposição** - Padrão GPT-5 para raciocínio aprofundado.

**Baixa Disposição** - Padrão GPT-5 para respostas rápidas.

**Conversa de Várias Rodadas** - Manter contexto entre trocas.

**Prompt Baseado em Papel** - Definir persona do modelo via mensagens do sistema.

**Autorreflexão** - Modelo avalia e melhora sua saída.

**Análise Estruturada** - Quadro fixo de avaliação.

**Padrão de Execução de Tarefas** - Planejar → Executar → Resumir.

## RAG (Geração Aumentada por Recuperação) - [Módulo 03](../03-rag/README.md)

**Pipeline de Processamento de Documento** - Carregar → dividir → embedding → armazenar.

**Armazenamento de Embeddings em Memória** - Armazenamento não persistente para testes.

**RAG** - Combina recuperação com geração para fundamentar respostas.

**Pontuação de Similaridade** - Medida (0-1) de similaridade semântica.

**Referência de Fonte** - Metadados sobre conteúdo recuperado.

## Agentes e Ferramentas - [Módulo 04](../04-tools/README.md)

**Anotação @Tool** - Marca métodos Java como ferramentas acionáveis por IA.

**Padrão ReAct** - Raciocinar → Agir → Observar → Repetir.

**Gestão de Sessão** - Contextos separados para diferentes usuários.

**Ferramenta** - Função que um agente de IA pode chamar.

**Descrição da Ferramenta** - Documentação do propósito e parâmetros da ferramenta.

## Módulo Agentic - [Módulo 05](../05-mcp/README.md)

**Anotação @Agent** - Marca interfaces como agentes de IA com definição declarativa de comportamento.

**Listener do Agente** - Gancho para monitorar execução do agente via `beforeAgentInvocation()` e `afterAgentInvocation()`.

**Escopo Agentic** - Memória compartilhada onde agentes armazenam saídas usando `outputKey` para consumo por agentes subsequentes.

**AgenticServices** - Fábrica para criar agentes usando `agentBuilder()` e `supervisorBuilder()`.

**Fluxo Condicional** - Direcionamento baseado em condições para diferentes agentes especialistas.

**Humano no Loop** - Padrão de fluxo que adiciona checkpoints humanos para aprovação ou revisão de conteúdo.

**langchain4j-agentic** - Dependência Maven para construção declarativa de agentes (experimental).

**Fluxo de Loop** - Iterar execução de agente até que condição seja atendida (ex.: pontuação de qualidade ≥ 0.8).

**outputKey** - Parâmetro de anotação do agente que especifica onde resultados são armazenados no Escopo Agentic.

**Fluxo Paralelo** - Executar múltiplos agentes simultaneamente para tarefas independentes.

**Estratégia de Resposta** - Como o supervisor formula a resposta final: LAST, SUMMARY, ou SCORED.

**Fluxo Sequencial** - Executar agentes em ordem onde saída flui para o próximo passo.

**Padrão de Agente Supervisor** - Padrão agentic avançado onde um LLM supervisor decide dinamicamente quais subagentes invocar.

## Protocolo de Contexto de Modelo (MCP) - [Módulo 05](../05-mcp/README.md)

**langchain4j-mcp** - Dependência Maven para integração MCP no LangChain4j.

**MCP** - Protocolo de Contexto de Modelo: padrão para conectar apps de IA a ferramentas externas. Construa uma vez, use em qualquer lugar.

**Cliente MCP** - Aplicação que conecta-se a servidores MCP para descobrir e usar ferramentas.

**Servidor MCP** - Serviço que expõe ferramentas via MCP com descrições claras e esquemas de parâmetros.

**McpToolProvider** - Componente LangChain4j que encapsula ferramentas MCP para uso em serviços de IA e agentes.

**McpTransport** - Interface para comunicação MCP. Implementações incluem Stdio e HTTP.

**Transporte Stdio** - Transporte de processo local via stdin/stdout. Útil para acesso a sistema de arquivos ou ferramentas de linha de comando.

**StdioMcpTransport** - Implementação LangChain4j que inicia servidor MCP como subprocesso.

**Descoberta de Ferramentas** - Cliente consulta servidor por ferramentas disponíveis com descrições e esquemas.

## Serviços Azure - [Módulo 01](../01-introduction/README.md)

**Azure AI Search** - Busca na nuvem com capacidades vetoriais. [Módulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Realiza deploy de recursos Azure.

**Azure OpenAI** - Serviço empresarial de IA da Microsoft.

**Bicep** - Linguagem de infraestrutura como código para Azure. [Guia de Infraestrutura](../01-introduction/infra/README.md)

**Nome de Implantação** - Nome para implantação do modelo no Azure.

**GPT-5** - Modelo OpenAI mais recente com controle de raciocínio. [Módulo 02](../02-prompt-engineering/README.md)

## Testes e Desenvolvimento - [Guia de Testes](TESTING.md)

**Dev Container** - Ambiente de desenvolvimento conteinerizado. [Configuração](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Playground gratuito de modelos de IA. [Módulo 00](../00-quick-start/README.md)

**Testes em Memória** - Testes com armazenamento em memória.

**Testes de Integração** - Testes com infraestrutura real.

**Maven** - Ferramenta de automação de build Java.

**Mockito** - Framework para mocks em Java.

**Spring Boot** - Framework para aplicações Java. [Módulo 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, esteja ciente de que traduções automáticas podem conter erros ou imprecisões. O documento original em seu idioma nativo deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se tradução profissional feita por humanos. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações equivocadas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->