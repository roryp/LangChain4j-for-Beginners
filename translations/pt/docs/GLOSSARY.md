<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:03:13+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "pt"
}
-->
# Glossário LangChain4j

## Índice

- [Conceitos Principais](../../../docs)
- [Componentes LangChain4j](../../../docs)
- [Conceitos AI/ML](../../../docs)
- [Engenharia de Prompt](../../../docs)
- [RAG (Geração Aumentada por Recuperação)](../../../docs)
- [Agentes e Ferramentas](../../../docs)
- [Protocolo de Contexto do Modelo (MCP)](../../../docs)
- [Serviços Azure](../../../docs)
- [Testes e Desenvolvimento](../../../docs)

Referência rápida para termos e conceitos usados ao longo do curso.

## Conceitos Principais

**Agente AI** - Sistema que usa IA para raciocinar e agir autonomamente. [Módulo 04](../04-tools/README.md)

**Cadeia** - Sequência de operações onde a saída alimenta o passo seguinte.

**Fragmentação** - Dividir documentos em pedaços menores. Típico: 300-500 tokens com sobreposição. [Módulo 03](../03-rag/README.md)

**Janela de Contexto** - Máximo de tokens que um modelo pode processar. GPT-5: 400K tokens.

**Embeddings** - Vetores numéricos que representam o significado do texto. [Módulo 03](../03-rag/README.md)

**Chamada de Função** - Modelo gera pedidos estruturados para chamar funções externas. [Módulo 04](../04-tools/README.md)

**Alucinação** - Quando modelos geram informação incorreta mas plausível.

**Prompt** - Entrada de texto para um modelo de linguagem. [Módulo 02](../02-prompt-engineering/README.md)

**Pesquisa Semântica** - Pesquisa por significado usando embeddings, não palavras-chave. [Módulo 03](../03-rag/README.md)

**Com Estado vs Sem Estado** - Sem estado: sem memória. Com estado: mantém histórico da conversa. [Módulo 01](../01-introduction/README.md)

**Tokens** - Unidades básicas de texto que os modelos processam. Afeta custos e limites. [Módulo 01](../01-introduction/README.md)

**Encadeamento de Ferramentas** - Execução sequencial de ferramentas onde a saída informa a próxima chamada. [Módulo 04](../04-tools/README.md)

## Componentes LangChain4j

**AiServices** - Cria interfaces de serviço AI com tipagem segura.

**OpenAiOfficialChatModel** - Cliente unificado para modelos OpenAI e Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Cria embeddings usando cliente oficial OpenAI (suporta OpenAI e Azure OpenAI).

**ChatModel** - Interface principal para modelos de linguagem.

**ChatMemory** - Mantém histórico da conversa.

**ContentRetriever** - Encontra fragmentos de documentos relevantes para RAG.

**DocumentSplitter** - Divide documentos em fragmentos.

**EmbeddingModel** - Converte texto em vetores numéricos.

**EmbeddingStore** - Armazena e recupera embeddings.

**MessageWindowChatMemory** - Mantém janela deslizante das mensagens recentes.

**PromptTemplate** - Cria prompts reutilizáveis com espaços reservados `{{variable}}`.

**TextSegment** - Fragmento de texto com metadados. Usado em RAG.

**ToolExecutionRequest** - Representa pedido de execução de ferramenta.

**UserMessage / AiMessage / SystemMessage** - Tipos de mensagens na conversa.

## Conceitos AI/ML

**Aprendizagem Few-Shot** - Fornecer exemplos nos prompts. [Módulo 02](../02-prompt-engineering/README.md)

**Modelo de Linguagem Grande (LLM)** - Modelos AI treinados com vastos dados textuais.

**Esforço de Raciocínio** - Parâmetro GPT-5 que controla profundidade do pensamento. [Módulo 02](../02-prompt-engineering/README.md)

**Temperatura** - Controla aleatoriedade da saída. Baixa=determinístico, alta=criativo.

**Base de Dados Vetorial** - Base de dados especializada para embeddings. [Módulo 03](../03-rag/README.md)

**Aprendizagem Zero-Shot** - Realizar tarefas sem exemplos. [Módulo 02](../02-prompt-engineering/README.md)

## Engenharia de Prompt - [Módulo 02](../02-prompt-engineering/README.md)

**Cadeia de Pensamento** - Raciocínio passo a passo para maior precisão.

**Saída Constrangida** - Aplicar formato ou estrutura específica.

**Alta Disposição** - Padrão GPT-5 para raciocínio aprofundado.

**Baixa Disposição** - Padrão GPT-5 para respostas rápidas.

**Conversa Multi-Turno** - Manter contexto ao longo das trocas.

**Prompting Baseado em Papel** - Definir persona do modelo via mensagens do sistema.

**Autorreflexão** - Modelo avalia e melhora sua saída.

**Análise Estruturada** - Quadro fixo de avaliação.

**Padrão de Execução de Tarefa** - Planear → Executar → Resumir.

## RAG (Geração Aumentada por Recuperação) - [Módulo 03](../03-rag/README.md)

**Pipeline de Processamento de Documentos** - Carregar → fragmentar → embedar → armazenar.

**Armazenamento de Embeddings em Memória** - Armazenamento não persistente para testes.

**RAG** - Combina recuperação com geração para fundamentar respostas.

**Pontuação de Similaridade** - Medida (0-1) de similaridade semântica.

**Referência de Fonte** - Metadados sobre conteúdo recuperado.

## Agentes e Ferramentas - [Módulo 04](../04-tools/README.md)

**Anotação @Tool** - Marca métodos Java como ferramentas chamáveis por IA.

**Padrão ReAct** - Raciocinar → Agir → Observar → Repetir.

**Gestão de Sessão** - Contextos separados para diferentes utilizadores.

**Ferramenta** - Função que um agente AI pode chamar.

**Descrição da Ferramenta** - Documentação do propósito e parâmetros da ferramenta.

## Protocolo de Contexto do Modelo (MCP) - [Módulo 05](../05-mcp/README.md)

**Transporte Docker** - Servidor MCP em contentor Docker.

**MCP** - Standard para conectar apps AI a ferramentas externas.

**Cliente MCP** - Aplicação que conecta a servidores MCP.

**Servidor MCP** - Serviço que expõe ferramentas via MCP.

**Server-Sent Events (SSE)** - Streaming do servidor para cliente via HTTP.

**Transporte Stdio** - Servidor como subprocesso via stdin/stdout.

**Transporte HTTP Streamable** - HTTP com SSE para comunicação em tempo real.

**Descoberta de Ferramentas** - Cliente consulta servidor por ferramentas disponíveis.

## Serviços Azure - [Módulo 01](../01-introduction/README.md)

**Azure AI Search** - Pesquisa na cloud com capacidades vetoriais. [Módulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Desdobra recursos Azure.

**Azure OpenAI** - Serviço AI empresarial da Microsoft.

**Bicep** - Linguagem de infraestrutura como código Azure. [Guia de Infraestrutura](../01-introduction/infra/README.md)

**Nome de Deployment** - Nome para deployment de modelo no Azure.

**GPT-5** - Último modelo OpenAI com controlo de raciocínio. [Módulo 02](../02-prompt-engineering/README.md)

## Testes e Desenvolvimento - [Guia de Testes](TESTING.md)

**Dev Container** - Ambiente de desenvolvimento conteinerizado. [Configuração](../../../.devcontainer/devcontainer.json)

**Modelos GitHub** - Playground gratuito de modelos AI. [Módulo 00](../00-quick-start/README.md)

**Testes em Memória** - Testes com armazenamento em memória.

**Testes de Integração** - Testes com infraestrutura real.

**Maven** - Ferramenta de automação de build Java.

**Mockito** - Framework de mocking Java.

**Spring Boot** - Framework de aplicações Java. [Módulo 01](../01-introduction/README.md)

**Testcontainers** - Contentores Docker em testes.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:
Este documento foi traduzido utilizando o serviço de tradução por IA [Co-op Translator](https://github.com/Azure/co-op-translator). Embora nos esforcemos para garantir a precisão, por favor tenha em conta que traduções automáticas podem conter erros ou imprecisões. O documento original na sua língua nativa deve ser considerado a fonte autorizada. Para informações críticas, recomenda-se a tradução profissional humana. Não nos responsabilizamos por quaisquer mal-entendidos ou interpretações erradas decorrentes do uso desta tradução.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->