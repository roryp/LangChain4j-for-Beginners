<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T23:57:36+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "br"
}
-->
# Glossário do LangChain4j

## Sumário

- [Conceitos Básicos](../../../docs)
- [Componentes do LangChain4j](../../../docs)
- [Conceitos de IA/ML](../../../docs)
- [Engenharia de Prompt - [Módulo 02]](../../../docs)
- [RAG (Geração Aumentada por Recuperação)](../../../docs)
- [Agentes e Ferramentas - [Módulo 04]](../../../docs)
- [Model Context Protocol (MCP) - [Módulo 05]](../../../docs)
- [Serviços Azure - [Módulo 01]](../../../docs)
- [Testes e Desenvolvimento - [Testing Guide]](../../../docs)

Referência rápida para termos e conceitos usados ao longo do curso.

## Conceitos Principais

**AI Agent** - Sistema que usa IA para raciocinar e agir de forma autônoma. [Módulo 04](../04-tools/README.md)

**Chain** - Sequência de operações onde a saída alimenta a próxima etapa.

**Chunking** - Quebra de documentos em partes menores. Típico: 300-500 tokens com sobreposição. [Módulo 03](../03-rag/README.md)

**Context Window** - Máximo de tokens que um modelo pode processar. GPT-5: 400K tokens.

**Embeddings** - Vetores numéricos que representam o significado do texto. [Módulo 03](../03-rag/README.md)

**Function Calling** - O modelo gera requisições estruturadas para chamar funções externas. [Módulo 04](../04-tools/README.md)

**Hallucination** - Quando modelos geram informações incorretas, porém plausíveis.

**Prompt** - Texto de entrada para um modelo de linguagem. [Módulo 02](../02-prompt-engineering/README.md)

**Semantic Search** - Busca por significado usando embeddings, não palavras-chave. [Módulo 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: sem memória. Stateful: mantém histórico da conversa. [Módulo 01](../01-introduction/README.md)

**Tokens** - Unidades básicas de texto que os modelos processam. Afeta custos e limites. [Módulo 01](../01-introduction/README.md)

**Tool Chaining** - Execução sequencial de ferramentas onde a saída informa a próxima chamada. [Módulo 04](../04-tools/README.md)

## Componentes do LangChain4j

**AiServices** - Cria interfaces de serviço de IA com tipagem segura.

**OpenAiOfficialChatModel** - Cliente unificado para modelos OpenAI e Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Cria embeddings usando o cliente oficial da OpenAI (suporta tanto OpenAI quanto Azure OpenAI).

**ChatModel** - Interface central para modelos de linguagem.

**ChatMemory** - Mantém o histórico de conversas.

**ContentRetriever** - Encontra trechos de documentos relevantes para RAG.

**DocumentSplitter** - Divide documentos em chunks.

**EmbeddingModel** - Converte texto em vetores numéricos.

**EmbeddingStore** - Armazena e recupera embeddings.

**MessageWindowChatMemory** - Mantém uma janela deslizante de mensagens recentes.

**PromptTemplate** - Cria prompts reutilizáveis com espaços reservados `{{variable}}`.

**TextSegment** - Trecho de texto com metadados. Usado em RAG.

**ToolExecutionRequest** - Representa uma requisição de execução de ferramenta.

**UserMessage / AiMessage / SystemMessage** - Tipos de mensagens de conversa.

## Conceitos de IA/ML

**Few-Shot Learning** - Fornecer exemplos nos prompts. [Módulo 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Modelos de IA treinados em vastos conjuntos de dados textuais.

**Reasoning Effort** - Parâmetro do GPT-5 que controla a profundidade do raciocínio. [Módulo 02](../02-prompt-engineering/README.md)

**Temperature** - Controla a aleatoriedade da saída. Baixa=determinístico, alta=criativo.

**Vector Database** - Banco de dados especializado para embeddings. [Módulo 03](../03-rag/README.md)

**Zero-Shot Learning** - Realizar tarefas sem exemplos. [Módulo 02](../02-prompt-engineering/README.md)

## Engenharia de Prompt - [Módulo 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Raciocínio passo a passo para melhor precisão.

**Constrained Output** - Aplicar um formato ou estrutura específica.

**High Eagerness** - Padrão do GPT-5 para raciocínio aprofundado.

**Low Eagerness** - Padrão do GPT-5 para respostas rápidas.

**Multi-Turn Conversation** - Manter contexto ao longo de trocas.

**Role-Based Prompting** - Definir a persona do modelo via mensagens de sistema.

**Self-Reflection** - Modelo avalia e melhora sua saída.

**Structured Analysis** - Estrutura fixa de avaliação.

**Task Execution Pattern** - Planejar → Executar → Resumir.

## RAG (Geração Aumentada por Recuperação) - [Módulo 03](../03-rag/README.md)

**Document Processing Pipeline** - Carregar → quebrar em chunks → gerar embeddings → armazenar.

**In-Memory Embedding Store** - Armazenamento não persistente para testes.

**RAG** - Combina recuperação com geração para fundamentar respostas.

**Similarity Score** - Medida (0-1) de similaridade semântica.

**Source Reference** - Metadados sobre o conteúdo recuperado.

## Agentes e Ferramentas - [Módulo 04](../04-tools/README.md)

**@Tool Annotation** - Marca métodos Java como ferramentas acionáveis por IA.

**ReAct Pattern** - Raciocinar → Agir → Observar → Repetir.

**Session Management** - Contextos separados para diferentes usuários.

**Tool** - Função que um agente de IA pode chamar.

**Tool Description** - Documentação do propósito da ferramenta e seus parâmetros.

## Model Context Protocol (MCP) - [Módulo 05](../05-mcp/README.md)

**MCP** - Padrão para conectar aplicações de IA a ferramentas externas.

**MCP Client** - Aplicação que se conecta a servidores MCP.

**MCP Server** - Serviço que expõe ferramentas via MCP.

**Stdio Transport** - Servidor como subprocesso via stdin/stdout.

**Tool Discovery** - Cliente consulta o servidor pelas ferramentas disponíveis.

## Serviços Azure - [Módulo 01](../01-introduction/README.md)

**Azure AI Search** - Busca em nuvem com capacidades de vetores. [Módulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Realiza deploy de recursos do Azure.

**Azure OpenAI** - Serviço de IA corporativo da Microsoft.

**Bicep** - Linguagem de infraestrutura como código do Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Nome para o deployment do modelo no Azure.

**GPT-5** - Modelo mais recente da OpenAI com controle de raciocínio. [Módulo 02](../02-prompt-engineering/README.md)

## Testes e Desenvolvimento - [Testing Guide](TESTING.md)

**Dev Container** - Ambiente de desenvolvimento em container. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Playground gratuito de modelos de IA. [Módulo 00](../00-quick-start/README.md)

**In-Memory Testing** - Testes com armazenamento em memória.

**Integration Testing** - Testes com infraestrutura real.

**Maven** - Ferramenta de automação de build Java.

**Mockito** - Framework de mocking para Java.

**Spring Boot** - Framework de aplicação Java. [Módulo 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Isenção de responsabilidade:**  
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, esteja ciente de que traduções automatizadas podem conter erros ou imprecisões. O documento original, no idioma de origem, deve ser considerado a fonte autoritativa. Para informações críticas, recomenda-se a tradução profissional realizada por um tradutor humano. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações equivocadas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->