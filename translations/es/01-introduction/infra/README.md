<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T21:42:51+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "es"
}
-->
# Infraestructura de Azure para LangChain4j Introducción

## Tabla de Contenidos

- [Requisitos Previos](../../../../01-introduction/infra)
- [Arquitectura](../../../../01-introduction/infra)
- [Recursos Creados](../../../../01-introduction/infra)
- [Inicio Rápido](../../../../01-introduction/infra)
- [Configuración](../../../../01-introduction/infra)
- [Comandos de Gestión](../../../../01-introduction/infra)
- [Optimización de Costos](../../../../01-introduction/infra)
- [Monitoreo](../../../../01-introduction/infra)
- [Solución de Problemas](../../../../01-introduction/infra)
- [Actualización de Infraestructura](../../../../01-introduction/infra)
- [Limpieza](../../../../01-introduction/infra)
- [Estructura de Archivos](../../../../01-introduction/infra)
- [Recomendaciones de Seguridad](../../../../01-introduction/infra)
- [Recursos Adicionales](../../../../01-introduction/infra)

Este directorio contiene la infraestructura de Azure como código (IaC) usando Bicep y Azure Developer CLI (azd) para desplegar recursos de Azure OpenAI.

## Requisitos Previos

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (versión 2.50.0 o superior)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (versión 1.5.0 o superior)
- Una suscripción de Azure con permisos para crear recursos

## Arquitectura

**Configuración Simplificada para Desarrollo Local** - Despliega solo Azure OpenAI, ejecuta todas las aplicaciones localmente.

La infraestructura despliega los siguientes recursos de Azure:

### Servicios de IA
- **Azure OpenAI**: Servicios Cognitivos con dos despliegues de modelos:
  - **gpt-5**: Modelo de chat para completado (capacidad 20K TPM)
  - **text-embedding-3-small**: Modelo de incrustación para RAG (capacidad 20K TPM)

### Desarrollo Local
Todas las aplicaciones Spring Boot se ejecutan localmente en tu máquina:
- 01-introduction (puerto 8080)
- 02-prompt-engineering (puerto 8083)
- 03-rag (puerto 8081)
- 04-tools (puerto 8084)

## Recursos Creados

| Tipo de Recurso | Patrón de Nombre del Recurso | Propósito |
|--------------|----------------------|---------|
| Grupo de Recursos | `rg-{environmentName}` | Contiene todos los recursos |
| Azure OpenAI | `aoai-{resourceToken}` | Hospedaje del modelo de IA |

> **Nota:** `{resourceToken}` es una cadena única generada a partir del ID de suscripción, nombre del entorno y ubicación

## Inicio Rápido

### 1. Desplegar Azure OpenAI

**Bash:**
```bash
cd 01-introduction
azd up
```

**PowerShell:**
```powershell
cd 01-introduction
azd up
```

Cuando se te solicite:
- Selecciona tu suscripción de Azure
- Elige una ubicación (recomendado: `eastus2` o `swedencentral` para disponibilidad de GPT-5)
- Confirma el nombre del entorno (por defecto: `langchain4j-dev`)

Esto creará:
- Recurso Azure OpenAI con GPT-5 y text-embedding-3-small
- Detalles de conexión de salida

### 2. Obtener Detalles de Conexión

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Esto muestra:
- `AZURE_OPENAI_ENDPOINT`: URL de tu endpoint de Azure OpenAI
- `AZURE_OPENAI_KEY`: Clave API para autenticación
- `AZURE_OPENAI_DEPLOYMENT`: Nombre del modelo de chat (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Nombre del modelo de incrustación

### 3. Ejecutar Aplicaciones Localmente

El comando `azd up` crea automáticamente un archivo `.env` en el directorio raíz con todas las variables de entorno necesarias.

**Recomendado:** Inicia todas las aplicaciones web:

**Bash:**
```bash
# Desde el directorio raíz
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Desde el directorio raíz
cd ../..
.\start-all.ps1
```

O inicia un solo módulo:

**Bash:**
```bash
# Ejemplo: Iniciar solo el módulo de introducción
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Ejemplo: Iniciar solo el módulo de introducción
cd ../01-introduction
.\start.ps1
```

Ambos scripts cargan automáticamente las variables de entorno desde el archivo `.env` raíz creado por `azd up`.

## Configuración

### Personalización de Despliegues de Modelos

Para cambiar los despliegues de modelos, edita `infra/main.bicep` y modifica el parámetro `openAiDeployments`:

```bicep
param openAiDeployments array = [
  {
    name: 'gpt-5'  // Model deployment name
    model: {
      format: 'OpenAI'
      name: 'gpt-5'
      version: '2025-08-07'  // Model version
    }
    sku: {
      name: 'Standard'
      capacity: 20  // TPM in thousands
    }
  }
  // Add more deployments...
]
```

Modelos y versiones disponibles: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Cambio de Regiones de Azure

Para desplegar en una región diferente, edita `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Consulta la disponibilidad de GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Para actualizar la infraestructura después de hacer cambios en los archivos Bicep:

**Bash:**
```bash
# Reconstruir la plantilla ARM
az bicep build --file infra/main.bicep

# Vista previa de los cambios
azd provision --preview

# Aplicar cambios
azd provision
```

**PowerShell:**
```powershell
# Reconstruir la plantilla ARM
az bicep build --file infra/main.bicep

# Vista previa de los cambios
azd provision --preview

# Aplicar cambios
azd provision
```

## Limpieza

Para eliminar todos los recursos:

**Bash:**
```bash
# Eliminar todos los recursos
azd down

# Eliminar todo incluyendo el entorno
azd down --purge
```

**PowerShell:**
```powershell
# Eliminar todos los recursos
azd down

# Eliminar todo incluyendo el entorno
azd down --purge
```

**Advertencia**: Esto eliminará permanentemente todos los recursos de Azure.

## Estructura de Archivos

## Optimización de Costos

### Desarrollo/Pruebas
Para entornos de desarrollo/pruebas, puedes reducir costos:
- Usa el nivel Estándar (S0) para Azure OpenAI
- Establece menor capacidad (10K TPM en lugar de 20K) en `infra/core/ai/cognitiveservices.bicep`
- Elimina recursos cuando no estén en uso: `azd down`

### Producción
Para producción:
- Incrementa la capacidad de OpenAI según uso (50K+ TPM)
- Habilita redundancia por zona para mayor disponibilidad
- Implementa monitoreo adecuado y alertas de costos

### Estimación de Costos
- Azure OpenAI: Pago por token (entrada + salida)
- GPT-5: ~$3-5 por 1M de tokens (verifica precios actuales)
- text-embedding-3-small: ~$0.02 por 1M de tokens

Calculadora de precios: https://azure.microsoft.com/pricing/calculator/

## Monitoreo

### Ver Métricas de Azure OpenAI

Ve al Portal de Azure → Tu recurso OpenAI → Métricas:
- Utilización basada en tokens
- Tasa de solicitudes HTTP
- Tiempo de respuesta
- Tokens activos

## Solución de Problemas

### Problema: Conflicto de nombre de subdominio de Azure OpenAI

**Mensaje de Error:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Causa:**
El nombre de subdominio generado a partir de tu suscripción/entorno ya está en uso, posiblemente por un despliegue previo que no se eliminó completamente.

**Solución:**
1. **Opción 1 - Usa un nombre de entorno diferente:**
   
   **Bash:**
   ```bash
   azd env new my-unique-env-name
   azd up
   ```
   
   **PowerShell:**
   ```powershell
   azd env new my-unique-env-name
   azd up
   ```

2. **Opción 2 - Despliegue manual vía Portal de Azure:**
   - Ve al Portal de Azure → Crear un recurso → Azure OpenAI
   - Elige un nombre único para tu recurso
   - Despliega los siguientes modelos:
     - **GPT-5**
     - **text-embedding-3-small** (para módulos RAG)
   - **Importante:** Anota los nombres de despliegue - deben coincidir con la configuración `.env`
   - Después del despliegue, obtén tu endpoint y clave API desde "Claves y Endpoint"
   - Crea un archivo `.env` en la raíz del proyecto con:
     
     **Ejemplo de archivo `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Guías para nombrar despliegues de modelos:**
- Usa nombres simples y consistentes: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Los nombres de despliegue deben coincidir exactamente con lo configurado en `.env`
- Error común: Crear modelo con un nombre pero referenciar otro diferente en el código

### Problema: GPT-5 no disponible en la región seleccionada

**Solución:**
- Elige una región con acceso a GPT-5 (ej., eastus, swedencentral)
- Verifica disponibilidad: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Problema: Cuota insuficiente para despliegue

**Solución:**
1. Solicita aumento de cuota en el Portal de Azure
2. O usa menor capacidad en `main.bicep` (ej., capacity: 10)

### Problema: "Recurso no encontrado" al ejecutar localmente

**Solución:**
1. Verifica despliegue: `azd env get-values`
2. Revisa que endpoint y clave sean correctos
3. Asegúrate que el grupo de recursos exista en el Portal de Azure

### Problema: Autenticación fallida

**Solución:**
- Verifica que `AZURE_OPENAI_API_KEY` esté configurado correctamente
- La clave debe ser una cadena hexadecimal de 32 caracteres
- Obtén una nueva clave desde el Portal de Azure si es necesario

### Fallo en el Despliegue

**Problema**: `azd provision` falla con errores de cuota o capacidad

**Solución**: 
1. Prueba en una región diferente - Consulta la sección [Cambio de Regiones de Azure](../../../../01-introduction/infra) para configurar regiones
2. Verifica que tu suscripción tenga cuota para Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Aplicación No Conecta

**Problema**: La aplicación Java muestra errores de conexión

**Solución**:
1. Verifica que las variables de entorno estén exportadas:
   
   **Bash:**
   ```bash
   echo $AZURE_OPENAI_ENDPOINT
   echo $AZURE_OPENAI_API_KEY
   ```
   
   **PowerShell:**
   ```powershell
   Write-Host $env:AZURE_OPENAI_ENDPOINT
   Write-Host $env:AZURE_OPENAI_API_KEY
   ```

2. Revisa que el formato del endpoint sea correcto (debe ser `https://xxx.openai.azure.com`)
3. Verifica que la clave API sea la primaria o secundaria del Portal de Azure

**Problema**: 401 No autorizado desde Azure OpenAI

**Solución**:
1. Obtén una clave API nueva desde Portal de Azure → Claves y Endpoint
2. Re-exporta la variable de entorno `AZURE_OPENAI_API_KEY`
3. Asegúrate que los despliegues de modelos estén completos (verifica en Portal de Azure)

### Problemas de Rendimiento

**Problema**: Tiempos de respuesta lentos

**Solución**:
1. Revisa uso de tokens y limitaciones en métricas del Portal de Azure
2. Incrementa capacidad TPM si alcanzas límites
3. Considera usar un nivel de esfuerzo de razonamiento más alto (bajo/medio/alto)

## Actualización de Infraestructura

```
infra/
├── main.bicep                       # Main infrastructure definition
├── main.json                        # Compiled ARM template (auto-generated)
├── main.bicepparam                  # Parameter file
├── README.md                        # This file
└── core/
    └── ai/
        └── cognitiveservices.bicep  # Azure OpenAI module
```

## Recomendaciones de Seguridad

1. **Nunca comitees claves API** - Usa variables de entorno
2. **Usa archivos .env localmente** - Añade `.env` a `.gitignore`
3. **Rota claves regularmente** - Genera nuevas claves en Portal de Azure
4. **Limita acceso** - Usa RBAC de Azure para controlar quién puede acceder a recursos
5. **Monitorea uso** - Configura alertas de costos en Portal de Azure

## Recursos Adicionales

- [Documentación del Servicio Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [Documentación del Modelo GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Documentación Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Documentación Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Integración Oficial LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Soporte

Para problemas:
1. Revisa la [sección de solución de problemas](../../../../01-introduction/infra) arriba
2. Consulta el estado del servicio Azure OpenAI en el Portal de Azure
3. Abre un issue en el repositorio

## Licencia

Consulta el archivo raíz [LICENSE](../../../../LICENSE) para más detalles.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Aviso Legal**:
Este documento ha sido traducido utilizando el servicio de traducción automática [Co-op Translator](https://github.com/Azure/co-op-translator). Aunque nos esforzamos por la precisión, tenga en cuenta que las traducciones automáticas pueden contener errores o inexactitudes. El documento original en su idioma nativo debe considerarse la fuente autorizada. Para información crítica, se recomienda una traducción profesional realizada por humanos. No nos hacemos responsables de malentendidos o interpretaciones erróneas derivadas del uso de esta traducción.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->