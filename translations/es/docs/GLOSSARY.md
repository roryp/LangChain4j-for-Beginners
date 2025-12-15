<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T19:49:55+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "es"
}
-->
# Glosario de LangChain4j

## Tabla de Contenidos

- [Conceptos Básicos](../../../docs)
- [Componentes de LangChain4j](../../../docs)
- [Conceptos de IA/ML](../../../docs)
- [Ingeniería de Prompts](../../../docs)
- [RAG (Generación Aumentada por Recuperación)](../../../docs)
- [Agentes y Herramientas](../../../docs)
- [Protocolo de Contexto de Modelo (MCP)](../../../docs)
- [Servicios de Azure](../../../docs)
- [Pruebas y Desarrollo](../../../docs)

Referencia rápida para términos y conceptos usados a lo largo del curso.

## Conceptos Básicos

**Agente de IA** - Sistema que usa IA para razonar y actuar de forma autónoma. [Módulo 04](../04-tools/README.md)

**Cadena** - Secuencia de operaciones donde la salida alimenta el siguiente paso.

**Fragmentación** - Dividir documentos en piezas más pequeñas. Típico: 300-500 tokens con solapamiento. [Módulo 03](../03-rag/README.md)

**Ventana de Contexto** - Máximo de tokens que un modelo puede procesar. GPT-5: 400K tokens.

**Embeddings** - Vectores numéricos que representan el significado del texto. [Módulo 03](../03-rag/README.md)

**Llamada a Función** - El modelo genera solicitudes estructuradas para llamar funciones externas. [Módulo 04](../04-tools/README.md)

**Alucinación** - Cuando los modelos generan información incorrecta pero plausible.

**Prompt** - Entrada de texto para un modelo de lenguaje. [Módulo 02](../02-prompt-engineering/README.md)

**Búsqueda Semántica** - Búsqueda por significado usando embeddings, no palabras clave. [Módulo 03](../03-rag/README.md)

**Con Estado vs Sin Estado** - Sin estado: sin memoria. Con estado: mantiene historial de conversación. [Módulo 01](../01-introduction/README.md)

**Tokens** - Unidades básicas de texto que los modelos procesan. Afecta costos y límites. [Módulo 01](../01-introduction/README.md)

**Encadenamiento de Herramientas** - Ejecución secuencial de herramientas donde la salida informa la siguiente llamada. [Módulo 04](../04-tools/README.md)

## Componentes de LangChain4j

**AiServices** - Crea interfaces de servicio de IA con tipado seguro.

**OpenAiOfficialChatModel** - Cliente unificado para modelos OpenAI y Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Crea embeddings usando cliente oficial de OpenAI (soporta OpenAI y Azure OpenAI).

**ChatModel** - Interfaz principal para modelos de lenguaje.

**ChatMemory** - Mantiene el historial de conversación.

**ContentRetriever** - Encuentra fragmentos de documentos relevantes para RAG.

**DocumentSplitter** - Divide documentos en fragmentos.

**EmbeddingModel** - Convierte texto en vectores numéricos.

**EmbeddingStore** - Almacena y recupera embeddings.

**MessageWindowChatMemory** - Mantiene una ventana deslizante de mensajes recientes.

**PromptTemplate** - Crea prompts reutilizables con marcadores `{{variable}}`.

**TextSegment** - Fragmento de texto con metadatos. Usado en RAG.

**ToolExecutionRequest** - Representa una solicitud de ejecución de herramienta.

**UserMessage / AiMessage / SystemMessage** - Tipos de mensajes en la conversación.

## Conceptos de IA/ML

**Aprendizaje con Pocos Ejemplos** - Proveer ejemplos en los prompts. [Módulo 02](../02-prompt-engineering/README.md)

**Modelo de Lenguaje Grande (LLM)** - Modelos de IA entrenados con grandes cantidades de texto.

**Esfuerzo de Razonamiento** - Parámetro de GPT-5 que controla la profundidad del pensamiento. [Módulo 02](../02-prompt-engineering/README.md)

**Temperatura** - Controla la aleatoriedad de la salida. Bajo=determinista, alto=creativo.

**Base de Datos Vectorial** - Base de datos especializada para embeddings. [Módulo 03](../03-rag/README.md)

**Aprendizaje Sin Ejemplos** - Realizar tareas sin ejemplos. [Módulo 02](../02-prompt-engineering/README.md)

## Ingeniería de Prompts - [Módulo 02](../02-prompt-engineering/README.md)

**Cadena de Pensamiento** - Razonamiento paso a paso para mayor precisión.

**Salida Restringida** - Imponer formato o estructura específica.

**Alta Disposición** - Patrón GPT-5 para razonamiento exhaustivo.

**Baja Disposición** - Patrón GPT-5 para respuestas rápidas.

**Conversación Multi-Turno** - Mantener contexto a través de intercambios.

**Prompt Basado en Roles** - Definir la persona del modelo mediante mensajes del sistema.

**Autorreflexión** - El modelo evalúa y mejora su salida.

**Análisis Estructurado** - Marco fijo de evaluación.

**Patrón de Ejecución de Tareas** - Planificar → Ejecutar → Resumir.

## RAG (Generación Aumentada por Recuperación) - [Módulo 03](../03-rag/README.md)

**Pipeline de Procesamiento de Documentos** - Cargar → fragmentar → embed → almacenar.

**Almacenamiento de Embeddings en Memoria** - Almacenamiento no persistente para pruebas.

**RAG** - Combina recuperación con generación para fundamentar respuestas.

**Puntaje de Similitud** - Medida (0-1) de similitud semántica.

**Referencia de Fuente** - Metadatos sobre contenido recuperado.

## Agentes y Herramientas - [Módulo 04](../04-tools/README.md)

**Anotación @Tool** - Marca métodos Java como herramientas llamables por IA.

**Patrón ReAct** - Razonar → Actuar → Observar → Repetir.

**Gestión de Sesiones** - Contextos separados para diferentes usuarios.

**Herramienta** - Función que un agente de IA puede llamar.

**Descripción de Herramienta** - Documentación del propósito y parámetros de la herramienta.

## Protocolo de Contexto de Modelo (MCP) - [Módulo 05](../05-mcp/README.md)

**Transporte Docker** - Servidor MCP en contenedor Docker.

**MCP** - Estándar para conectar apps de IA con herramientas externas.

**Cliente MCP** - Aplicación que se conecta a servidores MCP.

**Servidor MCP** - Servicio que expone herramientas vía MCP.

**Eventos Enviados por el Servidor (SSE)** - Streaming servidor-cliente sobre HTTP.

**Transporte Stdio** - Servidor como subproceso vía stdin/stdout.

**Transporte HTTP Transmisible** - HTTP con SSE para comunicación en tiempo real.

**Descubrimiento de Herramientas** - Cliente consulta al servidor por herramientas disponibles.

## Servicios de Azure - [Módulo 01](../01-introduction/README.md)

**Azure AI Search** - Búsqueda en la nube con capacidades vectoriales. [Módulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Despliega recursos de Azure.

**Azure OpenAI** - Servicio empresarial de IA de Microsoft.

**Bicep** - Lenguaje de infraestructura como código para Azure. [Guía de Infraestructura](../01-introduction/infra/README.md)

**Nombre de Despliegue** - Nombre para despliegue de modelo en Azure.

**GPT-5** - Último modelo de OpenAI con control de razonamiento. [Módulo 02](../02-prompt-engineering/README.md)

## Pruebas y Desarrollo - [Guía de Pruebas](TESTING.md)

**Contenedor de Desarrollo** - Entorno de desarrollo en contenedor. [Configuración](../../../.devcontainer/devcontainer.json)

**Modelos GitHub** - Playground gratuito de modelos de IA. [Módulo 00](../00-quick-start/README.md)

**Pruebas en Memoria** - Pruebas con almacenamiento en memoria.

**Pruebas de Integración** - Pruebas con infraestructura real.

**Maven** - Herramienta de automatización de compilación para Java.

**Mockito** - Framework de simulación para Java.

**Spring Boot** - Framework de aplicaciones Java. [Módulo 01](../01-introduction/README.md)

**Testcontainers** - Contenedores Docker en pruebas.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso legal**:
Este documento ha sido traducido utilizando el servicio de traducción automática [Co-op Translator](https://github.com/Azure/co-op-translator). Aunque nos esforzamos por la precisión, tenga en cuenta que las traducciones automáticas pueden contener errores o inexactitudes. El documento original en su idioma nativo debe considerarse la fuente autorizada. Para información crítica, se recomienda una traducción profesional realizada por humanos. No nos hacemos responsables de malentendidos o interpretaciones erróneas derivadas del uso de esta traducción.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->