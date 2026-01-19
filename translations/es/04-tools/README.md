<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "844788938b26242f3cc54ce0d0951bea",
  "translation_date": "2026-01-05T21:22:47+00:00",
  "source_file": "04-tools/README.md",
  "language_code": "es"
}
-->
# Módulo 04: Agentes de IA con Herramientas

## Tabla de Contenidos

- [Qué Aprenderás](../../../04-tools)
- [Requisitos Previos](../../../04-tools)
- [Comprendiendo los Agentes de IA con Herramientas](../../../04-tools)
- [Cómo Funciona la Llamada a las Herramientas](../../../04-tools)
  - [Definiciones de Herramientas](../../../04-tools)
  - [Toma de Decisiones](../../../04-tools)
  - [Ejecución](../../../04-tools)
  - [Generación de Respuestas](../../../04-tools)
- [Encadenamiento de Herramientas](../../../04-tools)
- [Ejecutar la Aplicación](../../../04-tools)
- [Usando la Aplicación](../../../04-tools)
  - [Probar Uso Simple de Herramientas](../../../04-tools)
  - [Probar Encadenamiento de Herramientas](../../../04-tools)
  - [Ver Flujo de Conversación](../../../04-tools)
  - [Experimentar con Diferentes Solicitudes](../../../04-tools)
- [Conceptos Clave](../../../04-tools)
  - [Patrón ReAct (Razonamiento y Acción)](../../../04-tools)
  - [Importancia de las Descripciones de Herramientas](../../../04-tools)
  - [Gestión de Sesiones](../../../04-tools)
  - [Manejo de Errores](../../../04-tools)
- [Herramientas Disponibles](../../../04-tools)
- [Cuándo Usar Agentes Basados en Herramientas](../../../04-tools)
- [Próximos Pasos](../../../04-tools)

## Qué Aprenderás

Hasta ahora, has aprendido a mantener conversaciones con IA, estructurar prompts efectivamente y fundamentar respuestas en tus documentos. Pero aún existe una limitación fundamental: los modelos de lenguaje solo pueden generar texto. No pueden consultar el clima, realizar cálculos, consultar bases de datos o interactuar con sistemas externos.

Las herramientas cambian esto. Al darle al modelo acceso a funciones que puede llamar, lo transformas de un generador de texto en un agente que puede tomar acciones. El modelo decide cuándo necesita una herramienta, cuál usar y qué parámetros pasar. Tu código ejecuta la función y devuelve el resultado. El modelo incorpora ese resultado en su respuesta.

## Requisitos Previos

- Completar el Módulo 01 (recursos de Azure OpenAI desplegados)
- Archivo `.env` en el directorio raíz con credenciales de Azure (creado por `azd up` en el Módulo 01)

> **Nota:** Si no has completado el Módulo 01, sigue primero las instrucciones de despliegue allí.

## Comprendiendo los Agentes de IA con Herramientas

> **📝 Nota:** El término "agentes" en este módulo se refiere a asistentes de IA mejorados con capacidades de llamada a herramientas. Esto es diferente de los patrones de **Agentic AI** (agentes autónomos con planificación, memoria y razonamiento multi-paso) que cubriremos en [Módulo 05: MCP](../05-mcp/README.md).

Un agente de IA con herramientas sigue un patrón de razonamiento y acción (ReAct):

1. El usuario hace una pregunta
2. El agente razona sobre lo que necesita saber
3. El agente decide si necesita una herramienta para responder
4. Si sí, el agente llama a la herramienta adecuada con los parámetros correctos
5. La herramienta ejecuta y devuelve datos
6. El agente incorpora el resultado y proporciona la respuesta final

<img src="../../../translated_images/es/react-pattern.86aafd3796f3fd13.webp" alt="Patrón ReAct" width="800"/>

*El patrón ReAct - cómo los agentes de IA alternan entre razonar y actuar para resolver problemas*

Esto sucede automáticamente. Defines las herramientas y sus descripciones. El modelo maneja la toma de decisiones sobre cuándo y cómo usarlas.

## Cómo Funciona la Llamada a las Herramientas

### Definiciones de Herramientas

[WeatherTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) | [TemperatureTool.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/TemperatureTool.java)

Defines funciones con descripciones claras y especificaciones de parámetros. El modelo ve estas descripciones en su prompt del sistema y entiende qué hace cada herramienta.

```java
@Component
public class WeatherTool {
    
    @Tool("Get the current weather for a location")
    public String getCurrentWeather(@P("Location name") String location) {
        // Tu lógica de búsqueda del clima
        return "Weather in " + location + ": 22°C, cloudy";
    }
}

@AiService
public interface Assistant {
    String chat(@MemoryId String sessionId, @UserMessage String message);
}

// El asistente está automáticamente conectado por Spring Boot con:
// - Bean de ChatModel
// - Todos los métodos @Tool de clases @Component
// - ChatMemoryProvider para la gestión de sesiones
```

> **🤖 Prueba con [GitHub Copilot](https://github.com/features/copilot) Chat:** Abre [`WeatherTool.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/tools/WeatherTool.java) y pregunta:
> - "¿Cómo integraría una API real de clima como OpenWeatherMap en lugar de datos simulados?"
> - "¿Qué hace que una descripción de herramienta sea buena para que la IA la use correctamente?"
> - "¿Cómo manejo errores de API y límites de peticiones en implementaciones de herramientas?"

### Toma de Decisiones

Cuando un usuario pregunta "¿Cuál es el clima en Seattle?", el modelo reconoce que necesita la herramienta de clima. Genera una llamada a función con el parámetro de ubicación configurado a "Seattle".

### Ejecución

[AgentService.java](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java)

Spring Boot inyecta automáticamente la interfaz declarativa `@AiService` con todas las herramientas registradas, y LangChain4j ejecuta las llamadas a las herramientas automáticamente.

> **🤖 Prueba con [GitHub Copilot](https://github.com/features/copilot) Chat:** Abre [`AgentService.java`](../../../04-tools/src/main/java/com/example/langchain4j/agents/service/AgentService.java) y pregunta:
> - "¿Cómo funciona el patrón ReAct y por qué es efectivo para agentes de IA?"
> - "¿Cómo decide el agente qué herramienta usar y en qué orden?"
> - "¿Qué pasa si la ejecución de una herramienta falla? ¿Cómo debo manejar errores robustamente?"

### Generación de Respuestas

El modelo recibe los datos del clima y los formatea en una respuesta en lenguaje natural para el usuario.

### ¿Por Qué Usar Servicios AI Declarativos?

Este módulo usa la integración de LangChain4j con Spring Boot y las interfaces declarativas `@AiService`:

- **Inyección automática de Spring Boot** - ChatModel y herramientas inyectados automáticamente
- **Patrón @MemoryId** - Gestión automática de memoria basada en sesiones
- **Instancia única** - Asistente creado una vez y reutilizado para mejor rendimiento
- **Ejecución con seguridad de tipos** - Métodos Java llamados directamente con conversión de tipos
- **Orquestación multi-turno** - Maneja encadenamiento de herramientas automáticamente
- **Cero boilerplate** - Sin llamadas manuales a AiServices.builder() ni HashMap de memoria

Enfoques alternativos (con AiServices.builder() manual) requieren más código y no aprovechan los beneficios de integración con Spring Boot.

## Encadenamiento de Herramientas

**Encadenamiento de Herramientas** - El agente de IA puede llamar a múltiples herramientas en secuencia. Pregunta "¿Cuál es el clima en Seattle y debería llevar un paraguas?" y mira cómo encadena `getCurrentWeather` con razonamiento sobre llevar paraguas.

<a href="images/tool-chaining.png"><img src="../../../translated_images/es/tool-chaining.3b25af01967d6f7b.webp" alt="Encadenamiento de Herramientas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Llamadas secuenciales a herramientas - la salida de una herramienta alimenta la siguiente decisión*

**Fallos Gráciles** - Pregunta por el clima en una ciudad que no está en los datos simulados. La herramienta devuelve un mensaje de error y la IA explica que no puede ayudar. Las herramientas fallan de forma segura.

Esto ocurre en un solo turno de conversación. El agente orquesta múltiples llamadas a herramientas de forma autónoma.

## Ejecutar la Aplicación

**Verificar despliegue:**

Asegúrate de que el archivo `.env` exista en el directorio raíz con credenciales de Azure (creado durante el Módulo 01):
```bash
cat ../.env  # Debe mostrar AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Iniciar la aplicación:**

> **Nota:** Si ya iniciaste todas las aplicaciones usando `./start-all.sh` del Módulo 01, este módulo ya está ejecutándose en el puerto 8084. Puedes omitir los comandos de inicio a continuación e ir directamente a http://localhost:8084.

**Opción 1: Usar Spring Boot Dashboard (Recomendado para usuarios de VS Code)**

El contenedor de desarrollo incluye la extensión Spring Boot Dashboard, que proporciona una interfaz visual para gestionar todas las aplicaciones Spring Boot. La puedes encontrar en la Barra de Actividades a la izquierda de VS Code (busca el icono de Spring Boot).

Desde el Spring Boot Dashboard, puedes:
- Ver todas las aplicaciones Spring Boot disponibles en el espacio de trabajo
- Iniciar/detener aplicaciones con un solo clic
- Ver logs de aplicaciones en tiempo real
- Monitorizar el estado de la aplicación

Simplemente haz clic en el botón de reproducción junto a "tools" para iniciar este módulo, o inicia todos los módulos a la vez.

<img src="../../../translated_images/es/dashboard.9b519b1a1bc1b30a.webp" alt="Spring Boot Dashboard" width="400"/>

**Opción 2: Usar scripts de shell**

Inicia todas las aplicaciones web (módulos 01-04):

**Bash:**
```bash
cd ..  # Desde el directorio raíz
./start-all.sh
```

**PowerShell:**
```powershell
cd ..  # Desde el directorio raíz
.\start-all.ps1
```

O inicia solo este módulo:

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

Ambos scripts cargan automáticamente variables de entorno desde el archivo `.env` raíz y construirán los JAR si no existen.

> **Nota:** Si prefieres construir todos los módulos manualmente antes de iniciar:
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

Abre http://localhost:8084 en tu navegador.

**Para detener:**

**Bash:**
```bash
./stop.sh  # Solo este módulo
# O
cd .. && ./stop-all.sh  # Todos los módulos
```

**PowerShell:**
```powershell
.\stop.ps1  # Solo este módulo
# O
cd ..; .\stop-all.ps1  # Todos los módulos
```

## Usando la Aplicación

La aplicación ofrece una interfaz web donde puedes interactuar con un agente de IA que tiene acceso a herramientas de clima y conversión de temperatura.

<a href="images/tools-homepage.png"><img src="../../../translated_images/es/tools-homepage.4b4cd8b2717f9621.webp" alt="Interfaz de Herramientas del Agente de IA" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*La interfaz de Herramientas del Agente de IA - ejemplos rápidos y chat para interactuar con herramientas*

### Probar Uso Simple de Herramientas

Comienza con una solicitud simple: "Convierte 100 grados Fahrenheit a Celsius". El agente reconoce que necesita la herramienta de conversión de temperaturas, la llama con los parámetros correctos y devuelve el resultado. Nota lo natural que se siente: no especificaste qué herramienta usar ni cómo llamarla.

### Probar Encadenamiento de Herramientas

Ahora prueba algo más complejo: "¿Cuál es el clima en Seattle y conviértelo a Fahrenheit?" Observa cómo el agente trabaja esto en pasos. Primero obtiene el clima (que devuelve Celsius), reconoce que necesita convertir a Fahrenheit, llama a la herramienta de conversión y combina ambos resultados en una respuesta.

### Ver Flujo de Conversación

La interfaz de chat mantiene el historial de la conversación, permitiendo interacciones multi-turno. Puedes ver todas las consultas y respuestas previas, facilitando el seguimiento y entendimiento de cómo el agente construye contexto a lo largo de múltiples intercambios.

<a href="images/tools-conversation-demo.png"><img src="../../../translated_images/es/tools-conversation-demo.89f2ce9676080f59.webp" alt="Conversación con Múltiples Llamadas a Herramientas" width="800" style="border: 1px solid #ddd; box-shadow: 0 2px 8px rgba(0,0,0,0.1);"/></a>

*Conversación multi-turno mostrando conversiones simples, consultas de clima y encadenamiento de herramientas*

### Experimentar con Diferentes Solicitudes

Prueba diversas combinaciones:
- Consultas de clima: "¿Cuál es el clima en Tokio?"
- Conversiones de temperatura: "¿Cuánto es 25°C en Kelvin?"
- Consultas combinadas: "Revisa el clima en París y dime si está por encima de 20°C"

Observa cómo el agente interpreta lenguaje natural y lo mapea a llamadas apropiadas a herramientas.

## Conceptos Clave

### Patrón ReAct (Razonamiento y Acción)

El agente alterna entre razonar (decidir qué hacer) y actuar (usar herramientas). Este patrón permite resolver problemas de forma autónoma en lugar de solo responder instrucciones.

### Importancia de las Descripciones de Herramientas

La calidad de las descripciones de tus herramientas afecta directamente cómo el agente las usa. Descripciones claras y específicas ayudan al modelo a entender cuándo y cómo llamar cada herramienta.

### Gestión de Sesiones

La anotación `@MemoryId` habilita la gestión automática de memoria basada en sesiones. Cada ID de sesión tiene su propia instancia de `ChatMemory` gestionada por el bean `ChatMemoryProvider`, eliminando la necesidad de seguimiento manual de memoria.

### Manejo de Errores

Las herramientas pueden fallar: las APIs pueden agotarse, los parámetros pueden ser inválidos, servicios externos pueden estar caídos. Los agentes en producción necesitan manejo de errores para que el modelo pueda explicar problemas o intentar alternativas.

## Herramientas Disponibles

**Herramientas de Clima** (datos simulados para demostración):
- Obtener el clima actual para una ubicación
- Obtener pronóstico de varios días

**Herramientas de Conversión de Temperatura**:
- Celsius a Fahrenheit
- Fahrenheit a Celsius
- Celsius a Kelvin
- Kelvin a Celsius
- Fahrenheit a Kelvin
- Kelvin a Fahrenheit

Estos son ejemplos simples, pero el patrón se extiende a cualquier función: consultas a bases de datos, llamadas a APIs, cálculos, operaciones de archivos o comandos de sistema.

## Cuándo Usar Agentes Basados en Herramientas

**Usa herramientas cuando:**
- Responder requiere datos en tiempo real (clima, precios de acciones, inventario)
- Necesitas hacer cálculos más allá de matemáticas simples
- Acceder a bases de datos o APIs
- Tomar acciones (enviar correos, crear tickets, actualizar registros)
- Combinar múltiples fuentes de datos

**No uses herramientas cuando:**
- Las preguntas pueden responderse con conocimiento general
- La respuesta es puramente conversacional
- La latencia de la herramienta haría la experiencia demasiado lenta

## Próximos Pasos

**Próximo Módulo:** [05-mcp - Protocolo de Contexto de Modelo (MCP)](../05-mcp/README.md)

---

**Navegación:** [← Anterior: Módulo 03 - RAG](../03-rag/README.md) | [Volver al Inicio](../README.md) | [Siguiente: Módulo 05 - MCP →](../05-mcp/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Descargo de responsabilidad**:  
Este documento ha sido traducido utilizando el servicio de traducción automática [Co-op Translator](https://github.com/Azure/co-op-translator). Aunque nos esforzamos por mantener la precisión, tenga en cuenta que las traducciones automáticas pueden contener errores o inexactitudes. El documento original en su idioma nativo debe considerarse la fuente autorizada. Para información crítica, se recomienda una traducción profesional humana. No nos hacemos responsables de ningún malentendido o interpretación errónea derivada del uso de esta traducción.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->