<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:22:34+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "sr"
}
-->
# Azure инфраструктура за LangChain4j Почетак рада

## Садржај

- [Претпоставке](../../../../01-introduction/infra)
- [Архитектура](../../../../01-introduction/infra)
- [Креирани ресурси](../../../../01-introduction/infra)
- [Брзи почетак](../../../../01-introduction/infra)
- [Конфигурација](../../../../01-introduction/infra)
- [Команде за управљање](../../../../01-introduction/infra)
- [Оптимизација трошкова](../../../../01-introduction/infra)
- [Надгледање](../../../../01-introduction/infra)
- [Решавање проблема](../../../../01-introduction/infra)
- [Ажурирање инфраструктуре](../../../../01-introduction/infra)
- [Чишћење](../../../../01-introduction/infra)
- [Структура фајлова](../../../../01-introduction/infra)
- [Препоруке за безбедност](../../../../01-introduction/infra)
- [Додатни ресурси](../../../../01-introduction/infra)

Овај директоријум садржи Azure инфраструктуру као код (IaC) користећи Bicep и Azure Developer CLI (azd) за деплојовање Azure OpenAI ресурса.

## Претпоставке

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (верзија 2.50.0 или новија)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (верзија 1.5.0 или новија)
- Azure претплата са дозволама за креирање ресурса

## Архитектура

**Поједностављено локално развојно окружење** - Деплојује само Azure OpenAI, све апликације се покрећу локално.

Инфраструктура деплојује следеће Azure ресурсе:

### AI услуге
- **Azure OpenAI**: Cognitive Services са два модела за деплој:
  - **gpt-5**: Модел за ћаскање (20K TPM капацитет)
  - **text-embedding-3-small**: Модел за уграђивање за RAG (20K TPM капацитет)

### Локални развој
Све Spring Boot апликације се покрећу локално на вашем рачунару:
- 01-introduction (порт 8080)
- 02-prompt-engineering (порт 8083)
- 03-rag (порт 8081)
- 04-tools (порт 8084)

## Креирани ресурси

| Тип ресурса | Образац имена ресурса | Сврха |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | Садржи све ресурсе |
| Azure OpenAI | `aoai-{resourceToken}` | Хостинг AI модела |

> **Напомена:** `{resourceToken}` је јединствени низ генерисан из ID претплате, имена окружења и локације

## Брзи почетак

### 1. Деплојујте Azure OpenAI

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

Када се затражи:
- Изаберите вашу Azure претплату
- Изаберите локацију (препоручено: `eastus2` или `swedencentral` за доступност GPT-5)
- Потврдите име окружења (подразумевано: `langchain4j-dev`)

Ово ће креирати:
- Azure OpenAI ресурс са GPT-5 и text-embedding-3-small
- Излазне детаље везе

### 2. Преузмите детаље везе

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Ово приказује:
- `AZURE_OPENAI_ENDPOINT`: URL вашег Azure OpenAI крајњег тачке
- `AZURE_OPENAI_KEY`: API кључ за аутентификацију
- `AZURE_OPENAI_DEPLOYMENT`: Име ћаскајућег модела (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Име модела за уграђивање

### 3. Покрените апликације локално

Команда `azd up` аутоматски креира `.env` фајл у коренском директоријуму са свим потребним променљивим окружења.

**Препоручено:** Покрените све веб апликације:

**Bash:**
```bash
# Из коренског директоријума
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Из коренског директоријума
cd ../..
.\start-all.ps1
```

Или покрените један модул:

**Bash:**
```bash
# Пример: Покрени само модул увод
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Пример: Покрени само модул увод
cd ../01-introduction
.\start.ps1
```

Оба скрипта аутоматски учитавају променљиве окружења из коренског `.env` фајла креираног од стране `azd up`.

## Конфигурација

### Прилагођавање деплоја модела

Да бисте променили деплоје модела, уредите `infra/main.bicep` и измените параметар `openAiDeployments`:

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

Доступни модели и верзије: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Промена Azure региона

Да бисте деплојовали у другом региону, уредите `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Проверите доступност GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Да ажурирате инфраструктуру након измена у Bicep фајловима:

**Bash:**
```bash
# Преизгради ARM шаблон
az bicep build --file infra/main.bicep

# Прегледај измене
azd provision --preview

# Примени измене
azd provision
```

**PowerShell:**
```powershell
# Преизгради ARM шаблон
az bicep build --file infra/main.bicep

# Прегледај измене
azd provision --preview

# Примени измене
azd provision
```

## Чишћење

Да избришете све ресурсе:

**Bash:**
```bash
# Обриши све ресурсе
azd down

# Обриши све укључујући и окружење
azd down --purge
```

**PowerShell:**
```powershell
# Обриши све ресурсе
azd down

# Обриши све укључујући и окружење
azd down --purge
```

**Упозорење**: Ово ће трајно избрисати све Azure ресурсе.

## Структура фајлова

## Оптимизација трошкова

### Развој/Тестирање
За развојна/тест окружења, можете смањити трошкове:
- Користите Standard tier (S0) за Azure OpenAI
- Поставите мањи капацитет (10K TPM уместо 20K) у `infra/core/ai/cognitiveservices.bicep`
- Обришите ресурсе када нису у употреби: `azd down`

### Продукција
За продукцију:
- Повећајте OpenAI капацитет у складу са коришћењем (50K+ TPM)
- Омогућите зону редунданције за већу доступност
- Имплементирајте адекватно надгледање и упозорења о трошковима

### Процена трошкова
- Azure OpenAI: Плаћање по токену (улаз + излаз)
- GPT-5: око $3-5 по 1M токена (проверите актуелне цене)
- text-embedding-3-small: око $0.02 по 1M токена

Калкулатор цена: https://azure.microsoft.com/pricing/calculator/

## Надгледање

### Приказ метрика Azure OpenAI

Идите у Azure портал → Ваш OpenAI ресурс → Метрике:
- Користење по токену
- Стопа HTTP захтева
- Време одговора
- Активни токени

## Решавање проблема

### Проблем: Конфликт имена поддомена Azure OpenAI

**Порука о грешци:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Узрок:**
Име поддомена генерисано из ваше претплате/окружења је већ у употреби, могуће од претходног деплоја који није у потпуности обрисан.

**Решење:**
1. **Опција 1 - Користите друго име окружења:**
   
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

2. **Опција 2 - Ручни деплој преко Azure портала:**
   - Идите у Azure портал → Креирајте ресурс → Azure OpenAI
   - Изаберите јединствено име за ваш ресурс
   - Деплојујте следеће моделе:
     - **GPT-5**
     - **text-embedding-3-small** (за RAG модуле)
   - **Важно:** Запамтите имена деплоја - морају одговарати конфигурацији у `.env`
   - Након деплоја, преузмите крајњу тачку и API кључ из "Keys and Endpoint"
   - Креирајте `.env` фајл у корену пројекта са:
     
     **Пример `.env` фајла:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Упутства за именовање деплоја модела:**
- Користите једноставна, конзистентна имена: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Имена деплоја морају тачно одговарати ономе што конфигуришете у `.env`
- Честа грешка: Креирање модела са једним именом али позивање другог у коду

### Проблем: GPT-5 није доступан у изабраном региону

**Решење:**
- Изаберите регион са приступом GPT-5 (нпр. eastus, swedencentral)
- Проверите доступност: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Проблем: Недовољна квота за деплој

**Решење:**
1. Затражите повећање квоте у Azure порталу
2. Или користите мањи капацитет у `main.bicep` (нпр. capacity: 10)

### Проблем: "Resource not found" при локалном покретању

**Решење:**
1. Проверите деплој: `azd env get-values`
2. Проверите да ли су крајња тачка и кључ исправни
3. Уверите се да група ресурса постоји у Azure порталу

### Проблем: Аутентификација није успела

**Решење:**
- Проверите да ли је `AZURE_OPENAI_API_KEY` исправно подешен
- Формат кључа треба да буде 32-карактерски хексадецимални низ
- Ако је потребно, преузмите нови кључ из Azure портала

### Деплој неуспешан

**Проблем**: `azd provision` не успева због грешака у квоти или капацитету

**Решење**: 
1. Покушајте други регион - Погледајте одељак [Промена Azure региона](../../../../01-introduction/infra) за конфигурацију региона
2. Проверите да ли ваша претплата има Azure OpenAI квоту:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Апликација се не повезује

**Проблем**: Java апликација показује грешке везе

**Решење**:
1. Проверите да ли су променљиве окружења експортиране:
   
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

2. Проверите да ли је формат крајње тачке исправан (треба да буде `https://xxx.openai.azure.com`)
3. Проверите да ли је API кључ примарни или секундарни кључ из Azure портала

**Проблем**: 401 Unauthorized од Azure OpenAI

**Решење**:
1. Преузмите нови API кључ из Azure портала → Keys and Endpoint
2. Поново експортујте променљиву окружења `AZURE_OPENAI_API_KEY`
3. Уверите се да су деплоји модела комплетни (проверите Azure портал)

### Проблеми са перформансама

**Проблем**: Спори одговори

**Решење**:
1. Проверите коришћење токена и ограничења у метрикама Azure портала
2. Повећајте TPM капацитет ако достижете лимите
3. Размотрите коришћење вишег нивоа напора размишљања (low/medium/high)

## Ажурирање инфраструктуре

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

## Препоруке за безбедност

1. **Никад не комитујте API кључеве** - Користите променљиве окружења
2. **Користите .env фајлове локално** - Додајте `.env` у `.gitignore`
3. **Редовно ротирајте кључеве** - Генеришите нове кључеве у Azure порталу
4. **Ограничите приступ** - Користите Azure RBAC за контролу ко може приступити ресурсима
5. **Надгледајте коришћење** - Поставите упозорења о трошковима у Azure порталу

## Додатни ресурси

- [Документација Azure OpenAI сервиса](https://learn.microsoft.com/azure/ai-services/openai/)
- [Документација GPT-5 модела](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Документација Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Документација Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI званична интеграција](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Подршка

За проблеме:
1. Проверите [одељак за решавање проблема](../../../../01-introduction/infra) изнад
2. Прегледајте здравље Azure OpenAI сервиса у Azure порталу
3. Отворите issue у репозиторијуму

## Лиценца

Погледајте коренски [LICENSE](../../../../LICENSE) фајл за детаље.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Одрицање од одговорности**:
Овај документ је преведен коришћењем AI услуге за превођење [Co-op Translator](https://github.com/Azure/co-op-translator). Иако се трудимо да превод буде тачан, молимо вас да имате у виду да аутоматски преводи могу садржати грешке или нетачности. Оригинални документ на његовом изворном језику треба сматрати ауторитетним извором. За критичне информације препоручује се професионални људски превод. Нисмо одговорни за било каква неспоразума или погрешна тумачења која произилазе из коришћења овог превода.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->