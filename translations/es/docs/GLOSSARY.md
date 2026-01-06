<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T07:41:44+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "es"
}
-->
# Glosario de LangChain4j

## Tabla de Contenidos

- [Conceptos Clave](../../../docs)
- [Componentes de LangChain4j](../../../docs)
- [Conceptos de IA/ML](../../../docs)
- [Guardrails](../../../docs)
- [Ingeniería de Prompts](../../../docs)
- [RAG (Generación Aumentada por Recuperación)](../../../docs)
- [Agentes y Herramientas](../../../docs)
- [Módulo Agéntico](../../../docs)
- [Protocolo de Contexto de Modelo (MCP)](../../../docs)
- [Servicios de Azure](../../../docs)
- [Pruebas y Desarrollo](../../../docs)

Referencia rápida para términos y conceptos usados a lo largo del curso.

## Conceptos Clave

**Agente de IA** - Sistema que utiliza IA para razonar y actuar autónomamente. [Módulo 04](../04-tools/README.md)

**Cadena** - Secuencia de operaciones donde la salida alimenta el siguiente paso.

**Fragmentación** - Dividir documentos en partes más pequeñas. Típico: 300-500 tokens con solapamiento. [Módulo 03](../03-rag/README.md)

**Ventana de Contexto** - Máximo de tokens que un modelo puede procesar. GPT-5: 400K tokens.

**Incrustaciones** - Vectores numéricos que representan el significado del texto. [Módulo 03](../03-rag/README.md)

**Llamada a Funciones** - Modelo genera solicitudes estructuradas para llamar funciones externas. [Módulo 04](../04-tools/README.md)

**Alucinación** - Cuando los modelos generan información incorrecta pero plausible.

**Prompt** - Entrada de texto para un modelo de lenguaje. [Módulo 02](../02-prompt-engineering/README.md)

**Búsqueda Semántica** - Buscar por significado usando incrustaciones, no palabras clave. [Módulo 03](../03-rag/README.md)

**Con estado vs Sin estado** - Sin estado: sin memoria. Con estado: mantiene historial de conversación. [Módulo 01](../01-introduction/README.md)

**Tokens** - Unidades básicas de texto que los modelos procesan. Afecta costos y límites. [Módulo 01](../01-introduction/README.md)

**Encadenamiento de Herramientas** - Ejecución secuencial de herramientas donde la salida informa la siguiente llamada. [Módulo 04](../04-tools/README.md)

## Componentes de LangChain4j

**AiServices** - Crea interfaces de servicio AI con tipado seguro.

**OpenAiOfficialChatModel** - Cliente unificado para modelos OpenAI y Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Crea incrustaciones usando cliente oficial OpenAI (soporta OpenAI y Azure OpenAI).

**ChatModel** - Interfaz principal para modelos de lenguaje.

**ChatMemory** - Mantiene historial de conversación.

**ContentRetriever** - Encuentra fragmentos de documentos relevantes para RAG.

**DocumentSplitter** - Divide documentos en fragmentos.

**EmbeddingModel** - Convierte texto en vectores numéricos.

**EmbeddingStore** - Almacena y recupera incrustaciones.

**MessageWindowChatMemory** - Mantiene ventana deslizante de mensajes recientes.

**PromptTemplate** - Crea prompts reutilizables con marcadores `{{variable}}`.

**TextSegment** - Fragmento de texto con metadatos. Usado en RAG.

**ToolExecutionRequest** - Representa una solicitud de ejecución de herramienta.

**UserMessage / AiMessage / SystemMessage** - Tipos de mensajes en conversación.

## Conceptos de IA/ML

**Aprendizaje Few-Shot** - Proveer ejemplos en los prompts. [Módulo 02](../02-prompt-engineering/README.md)

**Modelo de Lenguaje Grande (LLM)** - Modelos de IA entrenados con grandes cantidades de texto.

**Esfuerzo de Razonamiento** - Parámetro de GPT-5 que controla la profundidad del pensamiento. [Módulo 02](../02-prompt-engineering/README.md)

**Temperatura** - Controla la aleatoriedad de la salida. Bajo=determinista, alto=creativo.

**Base de Datos Vectorial** - Base de datos especializada para incrustaciones. [Módulo 03](../03-rag/README.md)

**Aprendizaje Zero-Shot** - Realizar tareas sin ejemplos. [Módulo 02](../02-prompt-engineering/README.md)

## Guardrails - [Módulo 00](../00-quick-start/README.md)

**Defensa en Profundidad** - Enfoque de seguridad multicapa que combina guardrails a nivel de aplicación con filtros de seguridad del proveedor.

**Bloqueo Estricto** - El proveedor arroja error HTTP 400 para violaciones graves de contenido.

**InputGuardrail** - Interfaz de LangChain4j para validar entradas de usuario antes de que lleguen al LLM. Ahorra costos y latencia bloqueando prompts dañinos temprano.

**InputGuardrailResult** - Tipo de retorno para validación de guardrail: `success()` o `fatal("razón")`.

**OutputGuardrail** - Interfaz para validar respuestas de IA antes de devolverlas al usuario.

**Filtros de Seguridad del Proveedor** - Filtros de contenido incorporados de proveedores de IA (e.g., GitHub Models) que detectan violaciones a nivel de API.

**Rechazo Suave** - Modelo declina educadamente responder sin arrojar error.

## Ingeniería de Prompts - [Módulo 02](../02-prompt-engineering/README.md)

**Cadena de Pensamiento** - Razonamiento paso a paso para mayor precisión.

**Salida Restringida** - Imponer formato o estructura específica.

**Alta Disposición** - Patrón de GPT-5 para razonamiento exhaustivo.

**Baja Disposición** - Patrón de GPT-5 para respuestas rápidas.

**Conversación Multi-Turno** - Mantener contexto entre intercambios.

**Prompt basado en Roles** - Configurar personalidad del modelo vía mensajes del sistema.

**Autorreflexión** - Modelo evalúa y mejora su salida.

**Análisis Estructurado** - Marco de evaluación fijo.

**Patrón de Ejecución de Tarea** - Planificar → Ejecutar → Resumir.

## RAG (Generación Aumentada por Recuperación) - [Módulo 03](../03-rag/README.md)

**Pipeline de Procesamiento de Documentos** - Cargar → fragmentar → incrustar → almacenar.

**Almacenamiento en Memoria para Incrustaciones** - Almacenamiento no persistente para pruebas.

**RAG** - Combina recuperación con generación para fundamentar respuestas.

**Puntaje de Similitud** - Medida (0-1) de similitud semántica.

**Referencia de Fuente** - Metadatos sobre contenido recuperado.

## Agentes y Herramientas - [Módulo 04](../04-tools/README.md)

**Anotación @Tool** - Marca métodos Java como herramientas llamables por IA.

**Patrón ReAct** - Razonar → Actuar → Observar → Repetir.

**Gestión de Sesión** - Contextos separados para diferentes usuarios.

**Herramienta** - Función que un agente IA puede llamar.

**Descripción de Herramienta** - Documentación de propósito y parámetros.

## Módulo Agéntico - [Módulo 05](../05-mcp/README.md)

**Anotación @Agent** - Marca interfaces como agentes IA con definición declarativa de comportamiento.

**Escucha de Agente** - Gancho para monitoreo de ejecución mediante `beforeAgentInvocation()` y `afterAgentInvocation()`.

**Ámbito Agéntico** - Memoria compartida donde agentes almacenan salidas usando `outputKey` para que otros agentes las consuman.

**AgenticServices** - Fábrica para crear agentes usando `agentBuilder()` y `supervisorBuilder()`.

**Flujo de Trabajo Condicional** - Dirige basado en condiciones a diferentes agentes especialistas.

**Humano en el Bucle** - Patrón de flujo que añade puntos de control humano para aprobación o revisión de contenido.

**langchain4j-agentic** - Dependencia Maven para construcción declarativa de agentes (experimental).

**Flujo de Trabajo en Bucle** - Itera ejecución del agente hasta que se cumpla una condición (ej. puntaje de calidad ≥ 0.8).

**outputKey** - Parámetro de anotación de agente que especifica dónde se almacenan resultados en Ámbito Agéntico.

**Flujo de Trabajo Paralelo** - Ejecuta múltiples agentes simultáneamente para tareas independientes.

**Estrategia de Respuesta** - Cómo el supervisor formula la respuesta final: ÚLTIMO, RESUMEN o PUNTUADO.

**Flujo de Trabajo Secuencial** - Ejecuta agentes en orden donde la salida fluye al siguiente paso.

**Patrón de Agente Supervisor** - Patrón avanzado donde un supervisor LLM decide dinámicamente qué sub-agentes invocar.

## Protocolo de Contexto de Modelo (MCP) - [Módulo 05](../05-mcp/README.md)

**langchain4j-mcp** - Dependencia Maven para integración MCP en LangChain4j.

**MCP** - Protocolo de Contexto de Modelo: estándar para conectar apps de IA con herramientas externas. Construye una vez, usa en todas partes.

**Cliente MCP** - Aplicación que se conecta a servidores MCP para descubrir y usar herramientas.

**Servidor MCP** - Servicio que expone herramientas vía MCP con descripciones claras y esquemas de parámetros.

**McpToolProvider** - Componente de LangChain4j que envuelve herramientas MCP para uso en servicios IA y agentes.

**McpTransport** - Interfaz para comunicación MCP. Implementaciones incluyen Stdio y HTTP.

**Transporte Stdio** - Transporte local vía stdin/stdout. Útil para acceso a sistema de archivos o herramientas de línea de comandos.

**StdioMcpTransport** - Implementación en LangChain4j que lanza servidor MCP como subproceso.

**Descubrimiento de Herramientas** - Cliente consulta servidor por herramientas disponibles con descripciones y esquemas.

## Servicios de Azure - [Módulo 01](../01-introduction/README.md)

**Azure AI Search** - Búsqueda en la nube con capacidades vectoriales. [Módulo 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Despliega recursos de Azure.

**Azure OpenAI** - Servicio empresarial de IA de Microsoft.

**Bicep** - Lenguaje de infraestructura como código para Azure. [Guía de Infraestructura](../01-introduction/infra/README.md)

**Nombre del Despliegue** - Nombre para despliegue de modelo en Azure.

**GPT-5** - Último modelo de OpenAI con control de razonamiento. [Módulo 02](../02-prompt-engineering/README.md)

## Pruebas y Desarrollo - [Guía de Pruebas](TESTING.md)

**Dev Container** - Entorno de desarrollo conteinerizado. [Configuración](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Playground gratuito de modelos IA. [Módulo 00](../00-quick-start/README.md)

**Pruebas en Memoria** - Pruebas con almacenamiento en memoria.

**Pruebas de Integración** - Pruebas con infraestructura real.

**Maven** - Herramienta de automatización de construcción para Java.

**Mockito** - Framework de simulación para Java.

**Spring Boot** - Framework de aplicaciones Java. [Módulo 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso legal**:  
Este documento ha sido traducido utilizando el servicio de traducción automática [Co-op Translator](https://github.com/Azure/co-op-translator). Aunque nos esforzamos por la precisión, tenga en cuenta que las traducciones automáticas pueden contener errores o inexactitudes. El documento original en su idioma nativo debe considerarse la fuente autorizada. Para información crítica, se recomienda una traducción profesional realizada por un humano. No nos hacemos responsables por malentendidos o interpretaciones erróneas derivadas del uso de esta traducción.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->