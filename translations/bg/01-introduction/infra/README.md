<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T22:21:51+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "bg"
}
-->
# Azure инфраструктура за LangChain4j Започване

## Съдържание

- [Изисквания](../../../../01-introduction/infra)
- [Архитектура](../../../../01-introduction/infra)
- [Създадени ресурси](../../../../01-introduction/infra)
- [Бърз старт](../../../../01-introduction/infra)
- [Конфигурация](../../../../01-introduction/infra)
- [Команди за управление](../../../../01-introduction/infra)
- [Оптимизация на разходите](../../../../01-introduction/infra)
- [Мониторинг](../../../../01-introduction/infra)
- [Отстраняване на проблеми](../../../../01-introduction/infra)
- [Актуализиране на инфраструктурата](../../../../01-introduction/infra)
- [Почистване](../../../../01-introduction/infra)
- [Структура на файловете](../../../../01-introduction/infra)
- [Препоръки за сигурност](../../../../01-introduction/infra)
- [Допълнителни ресурси](../../../../01-introduction/infra)

Тази директория съдържа Azure инфраструктура като код (IaC) с използване на Bicep и Azure Developer CLI (azd) за разгръщане на Azure OpenAI ресурси.

## Изисквания

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (версия 2.50.0 или по-нова)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (версия 1.5.0 или по-нова)
- Абонамент в Azure с права за създаване на ресурси

## Архитектура

**Оптимизиран локален режим на разработка** - Разгръща само Azure OpenAI, всички приложения се изпълняват локално.

Инфраструктурата разгръща следните Azure ресурси:

### AI услуги
- **Azure OpenAI**: Когнитивни услуги с две разгръщания на модели:
  - **gpt-5**: Модел за чат (20K TPM капацитет)
  - **text-embedding-3-small**: Модел за вграждане за RAG (20K TPM капацитет)

### Локална разработка
Всички Spring Boot приложения се изпълняват локално на вашия компютър:
- 01-introduction (порт 8080)
- 02-prompt-engineering (порт 8083)
- 03-rag (порт 8081)
- 04-tools (порт 8084)

## Създадени ресурси

| Тип ресурс | Шаблон за име на ресурс | Цел |
|--------------|----------------------|---------|
| Ресурсна група | `rg-{environmentName}` | Съдържа всички ресурси |
| Azure OpenAI | `aoai-{resourceToken}` | Хостинг на AI модел |

> **Забележка:** `{resourceToken}` е уникален низ, генериран от ID на абонамента, име на средата и локация

## Бърз старт

### 1. Разгръщане на Azure OpenAI

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

При подканване:
- Изберете вашия Azure абонамент
- Изберете локация (препоръчително: `eastus2` или `swedencentral` за наличност на GPT-5)
- Потвърдете името на средата (по подразбиране: `langchain4j-dev`)

Това ще създаде:
- Azure OpenAI ресурс с GPT-5 и text-embedding-3-small
- Изходни данни с детайли за връзка

### 2. Вземете детайли за връзка

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Това показва:
- `AZURE_OPENAI_ENDPOINT`: URL на вашия Azure OpenAI крайна точка
- `AZURE_OPENAI_KEY`: API ключ за удостоверяване
- `AZURE_OPENAI_DEPLOYMENT`: Име на чат модела (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Име на модела за вграждане

### 3. Стартирайте приложенията локално

Командата `azd up` автоматично създава `.env` файл в кореновата директория с всички необходими променливи на средата.

**Препоръчително:** Стартирайте всички уеб приложения:

**Bash:**
```bash
# От кореновата директория
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# От кореновата директория
cd ../..
.\start-all.ps1
```

Или стартирайте един модул:

**Bash:**
```bash
# Пример: Стартирайте само модула за въведение
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Пример: Стартирайте само модула за въведение
cd ../01-introduction
.\start.ps1
```

И двата скрипта автоматично зареждат променливите на средата от кореновия `.env` файл, създаден от `azd up`.

## Конфигурация

### Персонализиране на разгръщанията на модели

За да промените разгръщанията на модели, редактирайте `infra/main.bicep` и модифицирайте параметъра `openAiDeployments`:

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

Налични модели и версии: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Смяна на Azure региони

За да разположите в друг регион, редактирайте `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Проверете наличността на GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

За да актуализирате инфраструктурата след промени в Bicep файловете:

**Bash:**
```bash
# Преструктуриране на ARM шаблона
az bicep build --file infra/main.bicep

# Преглед на промените
azd provision --preview

# Прилагане на промените
azd provision
```

**PowerShell:**
```powershell
# Преструктуриране на ARM шаблона
az bicep build --file infra/main.bicep

# Преглед на промените
azd provision --preview

# Прилагане на промените
azd provision
```

## Почистване

За да изтриете всички ресурси:

**Bash:**
```bash
# Изтрийте всички ресурси
azd down

# Изтрийте всичко, включително и средата
azd down --purge
```

**PowerShell:**
```powershell
# Изтрийте всички ресурси
azd down

# Изтрийте всичко, включително и средата
azd down --purge
```

**Внимание**: Това ще изтрие завинаги всички Azure ресурси.

## Структура на файловете

## Оптимизация на разходите

### Разработка/Тестване
За среди за разработка/тест можете да намалите разходите:
- Използвайте стандартен слой (S0) за Azure OpenAI
- Задайте по-нисък капацитет (10K TPM вместо 20K) в `infra/core/ai/cognitiveservices.bicep`
- Изтривайте ресурси, когато не се използват: `azd down`

### Производство
За продукция:
- Увеличете капацитета на OpenAI според използването (50K+ TPM)
- Активирайте зонална излишност за по-висока наличност
- Внедрете подходящ мониторинг и аларми за разходи

### Оценка на разходите
- Azure OpenAI: Плащане на токен (входящ + изходящ)
- GPT-5: около $3-5 на 1M токена (проверете актуалните цени)
- text-embedding-3-small: около $0.02 на 1M токена

Калкулатор на цени: https://azure.microsoft.com/pricing/calculator/

## Мониторинг

### Преглед на метрики за Azure OpenAI

Отидете в Azure Portal → Вашия OpenAI ресурс → Метрики:
- Използване на база токени
- Честота на HTTP заявки
- Време за отговор
- Активни токени

## Отстраняване на проблеми

### Проблем: Конфликт на име на поддомейн в Azure OpenAI

**Съобщение за грешка:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Причина:**
Името на поддомейна, генерирано от вашия абонамент/среда, вече се използва, вероятно от предишно разгръщане, което не е било напълно изчистено.

**Решение:**
1. **Опция 1 - Използвайте различно име на средата:**
   
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

2. **Опция 2 - Ръчно разгръщане през Azure Portal:**
   - Отидете в Azure Portal → Създайте ресурс → Azure OpenAI
   - Изберете уникално име за вашия ресурс
   - Разгърнете следните модели:
     - **GPT-5**
     - **text-embedding-3-small** (за RAG модули)
   - **Важно:** Запишете имената на разгръщанията - те трябва да съвпадат с конфигурацията в `.env`
   - След разгръщането вземете крайна точка и API ключ от "Keys and Endpoint"
   - Създайте `.env` файл в корена на проекта с:
     
     **Примерен `.env` файл:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Насоки за именуване на разгръщания на модели:**
- Използвайте прости, последователни имена: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Имената на разгръщанията трябва да съвпадат точно с тези в `.env`
- Често срещана грешка: Създаване на модел с едно име, но препращане към друго в кода

### Проблем: GPT-5 не е наличен в избрания регион

**Решение:**
- Изберете регион с достъп до GPT-5 (например eastus, swedencentral)
- Проверете наличността: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Проблем: Недостатъчна квота за разгръщане

**Решение:**
1. Заявете увеличение на квотата в Azure Portal
2. Или използвайте по-нисък капацитет в `main.bicep` (например capacity: 10)

### Проблем: "Resource not found" при локално изпълнение

**Решение:**
1. Проверете разгръщането: `azd env get-values`
2. Уверете се, че крайна точка и ключ са правилни
3. Проверете дали ресурсната група съществува в Azure Portal

### Проблем: Неуспешна автентикация

**Решение:**
- Проверете дали `AZURE_OPENAI_API_KEY` е зададен правилно
- Форматът на ключа трябва да е 32-символен шестнадесетичен низ
- Вземете нов ключ от Azure Portal при нужда

### Разгръщането неуспешно

**Проблем**: `azd provision` неуспешно с грешки за квота или капацитет

**Решение**: 
1. Опитайте друг регион - вижте секция [Смяна на Azure региони](../../../../01-introduction/infra) за конфигуриране на региони
2. Проверете дали вашият абонамент има квота за Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Приложението не се свързва

**Проблем**: Java приложението показва грешки при връзка

**Решение**:
1. Проверете дали променливите на средата са експортирани:
   
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

2. Проверете дали форматът на крайна точка е правилен (трябва да е `https://xxx.openai.azure.com`)
3. Уверете се, че API ключът е основният или вторичният ключ от Azure Portal

**Проблем**: 401 Unauthorized от Azure OpenAI

**Решение**:
1. Вземете нов API ключ от Azure Portal → Keys and Endpoint
2. Преекспортирайте променливата на средата `AZURE_OPENAI_API_KEY`
3. Уверете се, че разгръщанията на модели са завършени (проверете в Azure Portal)

### Проблеми с производителността

**Проблем**: Бавни времена за отговор

**Решение**:
1. Проверете използването на токени и ограничаването в метриките на Azure Portal
2. Увеличете TPM капацитета, ако достигате лимити
3. Помислете за използване на по-високо ниво на усилие за разсъждение (ниско/средно/високо)

## Актуализиране на инфраструктурата

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

## Препоръки за сигурност

1. **Никога не комитвайте API ключове** - Използвайте променливи на средата
2. **Използвайте .env файлове локално** - Добавете `.env` в `.gitignore`
3. **Ротация на ключове редовно** - Генерирайте нови ключове в Azure Portal
4. **Ограничете достъпа** - Използвайте Azure RBAC за контрол на достъпа до ресурси
5. **Мониторирайте използването** - Настройте аларми за разходи в Azure Portal

## Допълнителни ресурси

- [Документация за Azure OpenAI Service](https://learn.microsoft.com/azure/ai-services/openai/)
- [Документация за GPT-5 модел](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Документация за Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Документация за Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Официална интеграция LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Поддръжка

За проблеми:
1. Проверете [секцията за отстраняване на проблеми](../../../../01-introduction/infra) по-горе
2. Прегледайте здравния статус на Azure OpenAI услугата в Azure Portal
3. Отворете issue в хранилището

## Лиценз

Вижте основния [LICENSE](../../../../LICENSE) файл за подробности.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Отказ от отговорност**:
Този документ е преведен с помощта на AI преводаческа услуга [Co-op Translator](https://github.com/Azure/co-op-translator). Въпреки че се стремим към точност, моля, имайте предвид, че автоматизираните преводи могат да съдържат грешки или неточности. Оригиналният документ на неговия роден език трябва да се счита за авторитетен източник. За критична информация се препоръчва професионален човешки превод. Ние не носим отговорност за каквито и да е недоразумения или неправилни тълкувания, произтичащи от използването на този превод.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->