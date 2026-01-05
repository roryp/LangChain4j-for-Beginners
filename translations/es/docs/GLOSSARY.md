<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-30T19:31:32+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "es"
}
-->
# Glosario de LangChain4j

## Tabla de contenidos

- [Conceptos clave](../../../docs)
- [Componentes de LangChain4j](../../../docs)
- [Conceptos de IA/ML](../../../docs)
- [Ingeniería de prompts](../../../docs)
- [RAG (Generación aumentada con recuperación)](../../../docs)
- [Agentes y Herramientas](../../../docs)
- [Protocolo de Contexto de Modelo (MCP)](../../../docs)
- [Servicios de Azure](../../../docs)
- [Pruebas y Desarrollo](../../../docs)

Referencia rápida de términos y conceptos utilizados a lo largo del curso.

## Conceptos clave

**Agente de IA** - Sistema que utiliza IA para razonar y actuar de forma autónoma. [Módulo 04](../04-tools/README.md)

**Cadena** - Secuencia de operaciones donde la salida alimenta el siguiente paso.

**Fragmentación** - Dividir documentos en piezas más pequeñas. Típico: 300-500 tokens con solapamiento. [Módulo 03](../03-rag/README.md)

**Ventana de contexto** - Máximo de tokens que un modelo puede procesar. GPT-5: 400K tokens.

**Embeddings** - Vectores numéricos que representan el significado del texto. [Módulo 03](../03-rag/README.md)

**Llamado de funciones** - El modelo genera solicitudes estructuradas para llamar funciones externas. [Módulo 04](../04-tools/README.md)

**Alucinación** - Cuando los modelos generan información incorrecta pero plausible.

**Prompt** - Entrada de texto a un modelo de lenguaje. [Módulo 02](../02-prompt-engineering/README.md)

**Búsqueda semántica** - Búsqueda por significado usando embeddings, no palabras clave. [Módulo 03](../03-rag/README.md)

**Con estado vs Sin estado** - Sin estado: sin memoria. Con estado: mantiene historial de conversación. [Módulo 01](../01-introduction/README.md)

**Tokens** - Unidades básicas de texto que los modelos procesan. Afecta costos y límites. [Módulo 01](../01-introduction/README.md)

**Encadenamiento de herramientas** - Ejecución secuencial de herramientas donde la salida informa la siguiente llamada. [Módulo 04](../04-tools/README.md)

## Componentes de LangChain4j

**AiServices** - Crea interfaces de servicio de IA con tipado seguro.

**OpenAiOfficialChatModel** - Cliente unificado para modelos de OpenAI y Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Crea embeddings usando el cliente oficial de OpenAI (soporta tanto OpenAI como Azure OpenAI).

**ChatModel** - Interfaz central para modelos de lenguaje.

**ChatMemory** - Mantiene el historial de conversación.

**ContentRetriever** - Encuentra fragmentos de documentos relevantes para RAG.

**DocumentSplitter** - Divide documentos en fragmentos.

**EmbeddingModel** - Convierte texto en vectores numéricos.

**EmbeddingStore** - Almacena y recupera embeddings.

**MessageWindowChatMemory** - Mantiene una ventana deslizante de mensajes recientes.

**PromptTemplate** - Crea prompts reutilizables con `{{variable}}` placeholders.

**TextSegment** - Fragmento de texto con metadatos. Usado en RAG.

**ToolExecutionRequest** - Representa una solicitud de ejecución de herramienta.

**UserMessage / AiMessage / SystemMessage** - Tipos de mensajes de conversación.

## Conceptos de IA/ML

**Aprendizaje Few-Shot** - Proporcionar ejemplos en los prompts. [Módulo 02](../02-prompt-engineering/README.md)

**Modelo de lenguaje grande (LLM)** - Modelos de IA entrenados en grandes cantidades de texto.

**Esfuerzo de razonamiento** - Parámetro de GPT-5 que controla la profundidad del pensamiento. [Módulo 02](../02-prompt-engineering/README.md)

**Temperatura** - Controla la aleatoriedad de la salida. Bajo=determinista, alto=creativo.

**Base de datos vectorial** - Base de datos especializada para embeddings. [Módulo 03](../03-rag/README.md)

**Aprendizaje Zero-Shot** - Realizar tareas sin ejemplos. [Módulo 02](../02-prompt-engineering/README.md)

## Ingeniería de prompts - [Módulo 02](../02-prompt-engineering/README.md)

**Cadena de pensamiento** - Razonamiento paso a paso para mayor precisión.

**Salida restringida** - Imponer un formato o estructura específicos.

**Alta disposición** - Patrón de GPT-5 para razonamiento exhaustivo.

**Baja disposición** - Patrón de GPT-5 para respuestas rápidas.

**Conversación de varios turnos** - Mantener el contexto a lo largo de los intercambios.

**Prompts basados en roles** - Configurar la persona del modelo mediante mensajes del sistema.

**Autorreflexión** - El modelo evalúa y mejora su salida.

**Análisis estructurado** - Marco de evaluación fijo.

**Patrón de ejecución de tareas** - Plan → Ejecutar → Resumir.

## RAG (Generación aumentada con recuperación) - [Módulo 03](../03-rag/README.md)

**Pipeline de procesamiento de documentos** - Cargar → fragmentar → incrustar → almacenar.

**Almacenamiento de embeddings en memoria** - Almacenamiento no persistente para pruebas.

**RAG** - Combina recuperación con generación para fundamentar las respuestas.

**Puntuación de similitud** - Medida (0-1) de similitud semántica.

**Referencia de fuente** - Metadatos sobre el contenido recuperado.

## Agentes y Herramientas - [Módulo 04](../04-tools/README.md)

**Anotación @Tool** - Marca métodos Java como herramientas invocables por IA.

**Patrón ReAct** - Razonar → Actuar → Observar → Repetir.

**Gestión de sesiones** - Contextos separados para diferentes usuarios.

**Herramienta** - Función que un agente de IA puede llamar.

**Descripción de la herramienta** - Documentación del propósito y los parámetros de la herramienta.

## Protocolo de Contexto de Modelo (MCP) - [Módulo 05](../05-mcp/README.md)

**MCP** - Estándar para conectar aplicaciones de IA con herramientas externas.

**Cliente MCP** - Aplicación que se conecta a servidores MCP.

**Servidor MCP** - Servicio que expone herramientas vía MCP.

**Transporte Stdio** - Servidor como subproceso vía stdin/stdout.

**Descubrimiento de herramientas** - El cliente consulta al servidor por las herramientas disponibles.

## Servicios de Azure - [Módulo 01](../01-introduction/README.md)

**Azure AI Search** - Búsqueda en la nube con capacidades vectoriales. [Módulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Despliega recursos de Azure.

**Azure OpenAI** - Servicio de IA empresarial de Microsoft.

**Bicep** - Lenguaje de infraestructura como código para Azure. [Guía de infraestructura](../01-introduction/infra/README.md)

**Nombre de despliegue** - Nombre para el despliegue del modelo en Azure.

**GPT-5** - Último modelo de OpenAI con control de razonamiento. [Módulo 02](../02-prompt-engineering/README.md)

## Pruebas y Desarrollo - [Guía de pruebas](TESTING.md)

**Dev Container** - Entorno de desarrollo en contenedor. [Configuración](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Entorno gratuito para modelos de IA. [Módulo 00](../00-quick-start/README.md)

**Pruebas en memoria** - Pruebas con almacenamiento en memoria.

**Pruebas de integración** - Pruebas con infraestructura real.

**Maven** - Herramienta de automatización de compilación para Java.

**Mockito** - Framework de mocking para Java.

**Spring Boot** - Framework de aplicaciones Java. [Módulo 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Descargo de responsabilidad**:
Este documento ha sido traducido mediante el servicio de traducción automática [Co-op Translator](https://github.com/Azure/co-op-translator). Aunque nos esforzamos por lograr exactitud, tenga en cuenta que las traducciones automáticas pueden contener errores o inexactitudes. El documento original en su idioma nativo debe considerarse la fuente autorizada. Para información crítica, se recomienda una traducción humana profesional. No nos hacemos responsables de ningún malentendido o interpretación errónea que surja del uso de esta traducción.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->