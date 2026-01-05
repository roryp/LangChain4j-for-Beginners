<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T23:44:58+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "pt"
}
-->
# Glossário LangChain4j

## Índice

- [Conceitos Principais](../../../docs)
- [Componentes do LangChain4j](../../../docs)
- [Conceitos de IA/ML](../../../docs)
- [Engenharia de Prompts](../../../docs)
- [RAG (Geração Aumentada por Recuperação)](../../../docs)
- [Agentes e Ferramentas](../../../docs)
- [Protocolo de Contexto de Modelo (MCP)](../../../docs)
- [Serviços Azure](../../../docs)
- [Testes e Desenvolvimento](../../../docs)

Referência rápida para termos e conceitos usados ao longo do curso.

## Conceitos Principais

**AI Agent** - Sistema que utiliza IA para raciocinar e agir de forma autónoma. [Módulo 04](../04-tools/README.md)

**Chain** - Sequência de operações onde a saída alimenta o passo seguinte.

**Chunking** - Dividir documentos em pedaços menores. Típico: 300-500 tokens com sobreposição. [Módulo 03](../03-rag/README.md)

**Context Window** - Máximo de tokens que um modelo pode processar. GPT-5: 400K tokens.

**Embeddings** - Vetores numéricos que representam o significado do texto. [Módulo 03](../03-rag/README.md)

**Function Calling** - O modelo gera pedidos estruturados para chamar funções externas. [Módulo 04](../04-tools/README.md)

**Hallucination** - Quando os modelos geram informação incorreta mas plausível.

**Prompt** - Texto de entrada para um modelo de linguagem. [Módulo 02](../02-prompt-engineering/README.md)

**Semantic Search** - Pesquisa por significado usando embeddings, não palavras-chave. [Módulo 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: sem memória. Stateful: mantém o historial da conversa. [Módulo 01](../01-introduction/README.md)

**Tokens** - Unidades básicas de texto que os modelos processam. Afeta custos e limites. [Módulo 01](../01-introduction/README.md)

**Tool Chaining** - Execução sequencial de ferramentas onde a saída informa a chamada seguinte. [Módulo 04](../04-tools/README.md)

## Componentes do LangChain4j

**AiServices** - Cria interfaces de serviço de IA com tipagem segura.

**OpenAiOfficialChatModel** - Cliente unificado para modelos OpenAI e Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Cria embeddings usando o cliente OpenAI Official (suporta tanto OpenAI como Azure OpenAI).

**ChatModel** - Interface central para modelos de linguagem.

**ChatMemory** - Mantém o historial da conversa.

**ContentRetriever** - Encontra pedaços de documento relevantes para RAG.

**DocumentSplitter** - Divide documentos em chunks.

**EmbeddingModel** - Converte texto em vetores numéricos.

**EmbeddingStore** - Armazena e recupera embeddings.

**MessageWindowChatMemory** - Mantém uma janela deslizante das mensagens recentes.

**PromptTemplate** - Cria prompts reutilizáveis com espaços reservados `{{variable}}`.

**TextSegment** - Segmento de texto com metadata. Usado em RAG.

**ToolExecutionRequest** - Representa um pedido de execução de ferramenta.

**UserMessage / AiMessage / SystemMessage** - Tipos de mensagem de conversa.

## Conceitos de IA/ML

**Few-Shot Learning** - Fornecer exemplos nos prompts. [Módulo 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - Modelos de IA treinados com grandes quantidades de texto.

**Reasoning Effort** - Parâmetro do GPT-5 que controla a profundidade do raciocínio. [Módulo 02](../02-prompt-engineering/README.md)

**Temperature** - Controla a aleatoriedade da saída. Baixa=determinístico, alta=criativo.

**Vector Database** - Base de dados especializada em embeddings. [Módulo 03](../03-rag/README.md)

**Zero-Shot Learning** - Realizar tarefas sem exemplos. [Módulo 02](../02-prompt-engineering/README.md)

## Engenharia de Prompts - [Módulo 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Raciocínio passo a passo para maior precisão.

**Constrained Output** - Imposição de formato ou estrutura específicos.

**Alta 'eagerness'** - Padrão do GPT-5 para raciocínio aprofundado.

**Baixa 'eagerness'** - Padrão do GPT-5 para respostas rápidas.

**Multi-Turn Conversation** - Manter contexto ao longo de várias trocas.

**Role-Based Prompting** - Definir persona do modelo via mensagens do sistema.

**Self-Reflection** - O modelo avalia e melhora a sua própria saída.

**Structured Analysis** - Estrutura fixa de avaliação.

**Task Execution Pattern** - Planear → Executar → Resumir.

## RAG (Geração Aumentada por Recuperação) - [Módulo 03](../03-rag/README.md)

**Document Processing Pipeline** - Carregar → chunking → gerar embeddings → armazenar.

**In-Memory Embedding Store** - Armazenamento não persistente para testes.

**RAG** - Combina recuperação com geração para ancorar respostas.

**Similarity Score** - Medida (0-1) de similaridade semântica.

**Source Reference** - Metadata sobre o conteúdo recuperado.

## Agentes e Ferramentas - [Módulo 04](../04-tools/README.md)

**@Tool Annotation** - Marca métodos Java como ferramentas invocáveis pela IA.

**ReAct Pattern** - Raciocinar → Agir → Observar → Repetir.

**Session Management** - Contextos separados para diferentes utilizadores.

**Tool** - Função que um agente de IA pode chamar.

**Tool Description** - Documentação do propósito da ferramenta e dos seus parâmetros.

## Protocolo de Contexto de Modelo (MCP) - [Módulo 05](../05-mcp/README.md)

**MCP** - Padrão para conectar aplicações de IA a ferramentas externas.

**MCP Client** - Aplicação que se liga a servidores MCP.

**MCP Server** - Serviço que expõe ferramentas via MCP.

**Stdio Transport** - Servidor como subprocesso via stdin/stdout.

**Tool Discovery** - O cliente consulta o servidor pelas ferramentas disponíveis.

## Serviços Azure - [Módulo 01](../01-introduction/README.md)

**Azure AI Search** - Serviço de pesquisa na nuvem com capacidades vetoriais. [Módulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Faz deploy de recursos Azure.

**Azure OpenAI** - Serviço de IA empresarial da Microsoft.

**Bicep** - Linguagem de infraestruturas como código da Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Nome para o deployment do modelo no Azure.

**GPT-5** - Último modelo da OpenAI com controlo de raciocínio. [Módulo 02](../02-prompt-engineering/README.md)

## Testes e Desenvolvimento - [Guia de Testes](TESTING.md)

**Dev Container** - Ambiente de desenvolvimento conteinerizado. [Configuração](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Playground gratuito de modelos de IA. [Módulo 00](../00-quick-start/README.md)

**In-Memory Testing** - Testes com armazenamento em memória.

**Integration Testing** - Testes com infraestrutura real.

**Maven** - Ferramenta de automatização de builds Java.

**Mockito** - Framework de mocking para Java.

**Spring Boot** - Framework de aplicações Java. [Módulo 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Aviso legal:
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos por garantir a precisão, tenha em atenção que as traduções automáticas podem conter erros ou imprecisões. O documento original no seu idioma nativo deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se a tradução por um tradutor profissional. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações incorretas decorrentes da utilização desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->