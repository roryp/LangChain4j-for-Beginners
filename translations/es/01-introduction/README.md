<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "c3e07ca58d0b8a3f47d3bf5728541e0a",
  "translation_date": "2025-12-13T13:01:25+00:00",
  "source_file": "01-introduction/README.md",
  "language_code": "es"
}
-->
# Módulo 01: Comenzando con LangChain4j

## Tabla de Contenidos

- [Lo que aprenderás](../../../01-introduction)
- [Requisitos previos](../../../01-introduction)
- [Entendiendo el problema central](../../../01-introduction)
- [Entendiendo los tokens](../../../01-introduction)
- [Cómo funciona la memoria](../../../01-introduction)
- [Cómo usa esto LangChain4j](../../../01-introduction)
- [Desplegar infraestructura Azure OpenAI](../../../01-introduction)
- [Ejecutar la aplicación localmente](../../../01-introduction)
- [Usando la aplicación](../../../01-introduction)
  - [Chat sin estado (panel izquierdo)](../../../01-introduction)
  - [Chat con estado (panel derecho)](../../../01-introduction)
- [Próximos pasos](../../../01-introduction)

## Lo que aprenderás

Si completaste el inicio rápido, viste cómo enviar prompts y obtener respuestas. Esa es la base, pero las aplicaciones reales necesitan más. Este módulo te enseña cómo construir IA conversacional que recuerda el contexto y mantiene el estado: la diferencia entre una demo puntual y una aplicación lista para producción.

Usaremos GPT-5 de Azure OpenAI a lo largo de esta guía porque sus capacidades avanzadas de razonamiento hacen que el comportamiento de diferentes patrones sea más evidente. Cuando agregas memoria, verás claramente la diferencia. Esto facilita entender qué aporta cada componente a tu aplicación.

Construirás una aplicación que demuestra ambos patrones:

**Chat sin estado** - Cada solicitud es independiente. El modelo no recuerda mensajes previos. Este es el patrón que usaste en el inicio rápido.

**Conversación con estado** - Cada solicitud incluye el historial de la conversación. El modelo mantiene el contexto a través de múltiples turnos. Esto es lo que requieren las aplicaciones en producción.

## Requisitos previos

- Suscripción de Azure con acceso a Azure OpenAI
- Java 21, Maven 3.9+
- Azure CLI (https://learn.microsoft.com/en-us/cli/azure/install-azure-cli)
- Azure Developer CLI (azd) (https://learn.microsoft.com/en-us/azure/developer/azure-developer-cli/install-azd)

> **Nota:** Java, Maven, Azure CLI y Azure Developer CLI (azd) están preinstalados en el devcontainer proporcionado.

> **Nota:** Este módulo usa GPT-5 en Azure OpenAI. El despliegue se configura automáticamente vía `azd up` - no modifiques el nombre del modelo en el código.

## Entendiendo el problema central

Los modelos de lenguaje son sin estado. Cada llamada a la API es independiente. Si envías "Mi nombre es John" y luego preguntas "¿Cuál es mi nombre?", el modelo no tiene idea de que acabas de presentarte. Trata cada solicitud como si fuera la primera conversación que tienes.

Esto está bien para preguntas y respuestas simples, pero es inútil para aplicaciones reales. Los bots de servicio al cliente necesitan recordar lo que les dijiste. Los asistentes personales necesitan contexto. Cualquier conversación de múltiples turnos requiere memoria.

<img src="../../../translated_images/stateless-vs-stateful.cc4a4765e649c41a.es.png" alt="Conversaciones sin estado vs con estado" width="800"/>

*La diferencia entre conversaciones sin estado (llamadas independientes) y con estado (consciente del contexto)*

## Entendiendo los tokens

Antes de sumergirte en las conversaciones, es importante entender los tokens: las unidades básicas de texto que procesan los modelos de lenguaje:

<img src="../../../translated_images/token-explanation.c39760d8ec650181.es.png" alt="Explicación de tokens" width="800"/>

*Ejemplo de cómo el texto se divide en tokens - "I love AI!" se convierte en 4 unidades separadas para procesar*

Los tokens son cómo los modelos de IA miden y procesan texto. Palabras, puntuación e incluso espacios pueden ser tokens. Tu modelo tiene un límite de cuántos tokens puede procesar a la vez (400,000 para GPT-5, con hasta 272,000 tokens de entrada y 128,000 de salida). Entender los tokens te ayuda a manejar la longitud de la conversación y los costos.

## Cómo funciona la memoria

La memoria de chat resuelve el problema de ser sin estado manteniendo el historial de la conversación. Antes de enviar tu solicitud al modelo, el framework antepone mensajes previos relevantes. Cuando preguntas "¿Cuál es mi nombre?", el sistema en realidad envía todo el historial de la conversación, permitiendo que el modelo vea que previamente dijiste "Mi nombre es John."

LangChain4j provee implementaciones de memoria que manejan esto automáticamente. Tú eliges cuántos mensajes conservar y el framework gestiona la ventana de contexto.

<img src="../../../translated_images/memory-window.bbe67f597eadabb3.es.png" alt="Concepto de ventana de memoria" width="800"/>

*MessageWindowChatMemory mantiene una ventana deslizante de mensajes recientes, descartando automáticamente los antiguos*

## Cómo usa esto LangChain4j

Este módulo extiende el inicio rápido integrando Spring Boot y agregando memoria de conversación. Así es como encajan las piezas:

**Dependencias** - Añade dos librerías de LangChain4j:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

**Modelo de chat** - Configura Azure OpenAI como un bean de Spring ([LangChainConfig.java](../../../01-introduction/src/main/java/com/example/langchain4j/config/LangChainConfig.java)):

```java
@Bean
public OpenAiOfficialChatModel openAiOfficialChatModel() {
    return OpenAiOfficialChatModel.builder()
            .baseUrl(azureEndpoint)
            .apiKey(azureApiKey)
            .modelName(deploymentName)
            .timeout(Duration.ofMinutes(5))
            .maxRetries(3)
            .build();
}
```

El builder lee las credenciales de variables de entorno configuradas por `azd up`. Establecer `baseUrl` a tu endpoint de Azure hace que el cliente OpenAI funcione con Azure OpenAI.

**Memoria de conversación** - Rastrea el historial de chat con MessageWindowChatMemory ([ConversationService.java](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java)):

```java
ChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

memory.add(UserMessage.from("My name is John"));
memory.add(AiMessage.from("Nice to meet you, John!"));

memory.add(UserMessage.from("What's my name?"));
AiMessage aiMessage = chatModel.chat(memory.messages()).aiMessage();
memory.add(aiMessage);
```

Crea la memoria con `withMaxMessages(10)` para conservar los últimos 10 mensajes. Añade mensajes de usuario y AI con wrappers tipados: `UserMessage.from(text)` y `AiMessage.from(text)`. Recupera el historial con `memory.messages()` y envíalo al modelo. El servicio almacena instancias de memoria separadas por ID de conversación, permitiendo que múltiples usuarios chateen simultáneamente.

> **🤖 Prueba con [GitHub Copilot](https://github.com/features/copilot) Chat:** Abre [`ConversationService.java`](../../../01-introduction/src/main/java/com/example/langchain4j/service/ConversationService.java) y pregunta:
> - "¿Cómo decide MessageWindowChatMemory qué mensajes descartar cuando la ventana está llena?"
> - "¿Puedo implementar almacenamiento de memoria personalizado usando una base de datos en lugar de memoria en RAM?"
> - "¿Cómo agregaría resumen para comprimir el historial antiguo de la conversación?"

El endpoint de chat sin estado omite la memoria completamente - solo `chatModel.chat(prompt)` como en el inicio rápido. El endpoint con estado añade mensajes a la memoria, recupera el historial e incluye ese contexto con cada solicitud. Misma configuración de modelo, patrones diferentes.

## Desplegar infraestructura Azure OpenAI

**Bash:**
```bash
cd 01-introduction
azd up  # Seleccione suscripción y ubicación (se recomienda eastus2)
```

**PowerShell:**
```powershell
cd 01-introduction
azd up  # Seleccione suscripción y ubicación (se recomienda eastus2)
```

> **Nota:** Si encuentras un error de timeout (`RequestConflict: Cannot modify resource ... provisioning state is not terminal`), simplemente ejecuta `azd up` nuevamente. Los recursos de Azure pueden estar aún en proceso de aprovisionamiento en segundo plano, y reintentar permite que el despliegue se complete una vez que los recursos alcancen un estado terminal.

Esto hará:
1. Desplegar recurso Azure OpenAI con modelos GPT-5 y text-embedding-3-small
2. Generar automáticamente el archivo `.env` en la raíz del proyecto con credenciales
3. Configurar todas las variables de entorno requeridas

**¿Tienes problemas con el despliegue?** Consulta el [README de Infraestructura](infra/README.md) para solución de problemas detallada incluyendo conflictos de nombres de subdominios, pasos manuales para desplegar en Azure Portal y guía de configuración de modelos.

**Verifica que el despliegue fue exitoso:**

**Bash:**
```bash
cat ../.env  # Debe mostrar AZURE_OPENAI_ENDPOINT, API_KEY, etc.
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Debe mostrar AZURE_OPENAI_ENDPOINT, API_KEY, etc.
```

> **Nota:** El comando `azd up` genera automáticamente el archivo `.env`. Si necesitas actualizarlo después, puedes editar el archivo `.env` manualmente o regenerarlo ejecutando:
>
> **Bash:**
> ```bash
> cd ..
> bash .azd-env.sh
> ```
>
> **PowerShell:**
> ```powershell
> cd ..
> .\.azd-env.ps1
> ```

## Ejecutar la aplicación localmente

**Verifica el despliegue:**

Asegúrate de que el archivo `.env` exista en el directorio raíz con las credenciales de Azure:

**Bash:**
```bash
cat ../.env  # Debe mostrar AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**PowerShell:**
```powershell
Get-Content ..\.env  # Debe mostrar AZURE_OPENAI_ENDPOINT, API_KEY, DEPLOYMENT
```

**Inicia las aplicaciones:**

**Opción 1: Usando Spring Boot Dashboard (Recomendado para usuarios de VS Code)**

El contenedor de desarrollo incluye la extensión Spring Boot Dashboard, que provee una interfaz visual para gestionar todas las aplicaciones Spring Boot. Puedes encontrarla en la Barra de Actividad a la izquierda de VS Code (busca el ícono de Spring Boot).

Desde el Spring Boot Dashboard, puedes:
- Ver todas las aplicaciones Spring Boot disponibles en el espacio de trabajo
- Iniciar/detener aplicaciones con un solo clic
- Ver logs de la aplicación en tiempo real
- Monitorear el estado de la aplicación

Simplemente haz clic en el botón de reproducir junto a "introduction" para iniciar este módulo, o inicia todos los módulos a la vez.

<img src="../../../translated_images/dashboard.69c7479aef09ff6b.es.png" alt="Panel de control Spring Boot" width="400"/>

**Opción 2: Usando scripts de shell**

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
cd 01-introduction
./start.sh
```

**PowerShell:**
```powershell
cd 01-introduction
.\start.ps1
```

Ambos scripts cargan automáticamente las variables de entorno desde el archivo `.env` raíz y construirán los JARs si no existen.

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

Abre http://localhost:8080 en tu navegador.

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

## Usando la aplicación

La aplicación provee una interfaz web con dos implementaciones de chat lado a lado.

<img src="../../../translated_images/home-screen.121a03206ab910c0.es.png" alt="Pantalla principal de la aplicación" width="800"/>

*Panel mostrando opciones de Chat Simple (sin estado) y Chat Conversacional (con estado)*

### Chat sin estado (panel izquierdo)

Prueba esto primero. Pregunta "Mi nombre es John" y luego inmediatamente pregunta "¿Cuál es mi nombre?" El modelo no recordará porque cada mensaje es independiente. Esto demuestra el problema central con la integración básica de modelos de lenguaje: no hay contexto de conversación.

<img src="../../../translated_images/simple-chat-stateless-demo.13aeb3978eab3234.es.png" alt="Demo de chat sin estado" width="800"/>

*La IA no recuerda tu nombre del mensaje anterior*

### Chat con estado (panel derecho)

Ahora prueba la misma secuencia aquí. Pregunta "Mi nombre es John" y luego "¿Cuál es mi nombre?" Esta vez lo recuerda. La diferencia es MessageWindowChatMemory: mantiene el historial de la conversación e incluye ese contexto con cada solicitud. Así funciona la IA conversacional en producción.

<img src="../../../translated_images/conversational-chat-stateful-demo.e5be9822eb23ff59.es.png" alt="Demo de chat con estado" width="800"/>

*La IA recuerda tu nombre de antes en la conversación*

Ambos paneles usan el mismo modelo GPT-5. La única diferencia es la memoria. Esto deja claro qué aporta la memoria a tu aplicación y por qué es esencial para casos de uso reales.

## Próximos pasos

**Siguiente módulo:** [02-prompt-engineering - Ingeniería de prompts con GPT-5](../02-prompt-engineering/README.md)

---

**Navegación:** [← Anterior: Módulo 00 - Inicio rápido](../00-quick-start/README.md) | [Volver al inicio](../README.md) | [Siguiente: Módulo 02 - Ingeniería de prompts →](../02-prompt-engineering/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:  
Este documento ha sido traducido utilizando el servicio de traducción automática [Co-op Translator](https://github.com/Azure/co-op-translator). Aunque nos esforzamos por la precisión, tenga en cuenta que las traducciones automáticas pueden contener errores o inexactitudes. El documento original en su idioma nativo debe considerarse la fuente autorizada. Para información crítica, se recomienda una traducción profesional realizada por humanos. No nos hacemos responsables de malentendidos o interpretaciones erróneas derivadas del uso de esta traducción.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->