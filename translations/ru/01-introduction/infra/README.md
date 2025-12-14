<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T21:44:13+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "ru"
}
-->
# Инфраструктура Azure для LangChain4j Начало работы

## Содержание

- [Требования](../../../../01-introduction/infra)
- [Архитектура](../../../../01-introduction/infra)
- [Созданные ресурсы](../../../../01-introduction/infra)
- [Быстрый старт](../../../../01-introduction/infra)
- [Конфигурация](../../../../01-introduction/infra)
- [Команды управления](../../../../01-introduction/infra)
- [Оптимизация затрат](../../../../01-introduction/infra)
- [Мониторинг](../../../../01-introduction/infra)
- [Устранение неполадок](../../../../01-introduction/infra)
- [Обновление инфраструктуры](../../../../01-introduction/infra)
- [Очистка](../../../../01-introduction/infra)
- [Структура файлов](../../../../01-introduction/infra)
- [Рекомендации по безопасности](../../../../01-introduction/infra)
- [Дополнительные ресурсы](../../../../01-introduction/infra)

В этом каталоге находится инфраструктура Azure как код (IaC) с использованием Bicep и Azure Developer CLI (azd) для развертывания ресурсов Azure OpenAI.

## Требования

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (версия 2.50.0 или новее)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (версия 1.5.0 или новее)
- Подписка Azure с правами на создание ресурсов

## Архитектура

**Упрощённая локальная среда разработки** — развертывается только Azure OpenAI, все приложения запускаются локально.

Инфраструктура развертывает следующие ресурсы Azure:

### AI-сервисы
- **Azure OpenAI**: когнитивные сервисы с двумя развертываниями моделей:
  - **gpt-5**: модель для чат-комплешена (ёмкость 20K TPM)
  - **text-embedding-3-small**: модель для эмбеддингов для RAG (ёмкость 20K TPM)

### Локальная разработка
Все приложения Spring Boot запускаются локально на вашем компьютере:
- 01-introduction (порт 8080)
- 02-prompt-engineering (порт 8083)
- 03-rag (порт 8081)
- 04-tools (порт 8084)

## Созданные ресурсы

| Тип ресурса | Шаблон имени ресурса | Назначение |
|--------------|----------------------|---------|
| Группа ресурсов | `rg-{environmentName}` | Содержит все ресурсы |
| Azure OpenAI | `aoai-{resourceToken}` | Хостинг AI-моделей |

> **Примечание:** `{resourceToken}` — уникальная строка, сгенерированная из ID подписки, имени окружения и региона

## Быстрый старт

### 1. Разверните Azure OpenAI

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

При запросе:
- Выберите вашу подписку Azure
- Выберите регион (рекомендуется: `eastus2` или `swedencentral` для доступности GPT-5)
- Подтвердите имя окружения (по умолчанию: `langchain4j-dev`)

Будут созданы:
- Ресурс Azure OpenAI с GPT-5 и text-embedding-3-small
- Вывод данных для подключения

### 2. Получите данные для подключения

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Отобразится:
- `AZURE_OPENAI_ENDPOINT`: URL конечной точки Azure OpenAI
- `AZURE_OPENAI_KEY`: ключ API для аутентификации
- `AZURE_OPENAI_DEPLOYMENT`: имя модели чата (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: имя модели эмбеддинга

### 3. Запустите приложения локально

Команда `azd up` автоматически создаёт файл `.env` в корневом каталоге со всеми необходимыми переменными окружения.

**Рекомендуется:** Запустить все веб-приложения:

**Bash:**
```bash
# Из корневого каталога
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Из корневого каталога
cd ../..
.\start-all.ps1
```

Или запустить отдельный модуль:

**Bash:**
```bash
# Пример: Запустить только модуль введения
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Пример: Запустить только модуль введения
cd ../01-introduction
.\start.ps1
```

Оба скрипта автоматически загружают переменные окружения из корневого файла `.env`, созданного `azd up`.

## Конфигурация

### Настройка развертываний моделей

Чтобы изменить развертывания моделей, отредактируйте `infra/main.bicep` и измените параметр `openAiDeployments`:

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

Доступные модели и версии: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Изменение регионов Azure

Чтобы развернуть в другом регионе, отредактируйте `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Проверьте доступность GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Чтобы обновить инфраструктуру после изменений в Bicep файлах:

**Bash:**
```bash
# Пересобрать ARM шаблон
az bicep build --file infra/main.bicep

# Предварительный просмотр изменений
azd provision --preview

# Применить изменения
azd provision
```

**PowerShell:**
```powershell
# Пересобрать ARM шаблон
az bicep build --file infra/main.bicep

# Предварительный просмотр изменений
azd provision --preview

# Применить изменения
azd provision
```

## Очистка

Чтобы удалить все ресурсы:

**Bash:**
```bash
# Удалить все ресурсы
azd down

# Удалить всё, включая окружение
azd down --purge
```

**PowerShell:**
```powershell
# Удалить все ресурсы
azd down

# Удалить всё, включая окружение
azd down --purge
```

**Внимание**: Это навсегда удалит все ресурсы Azure.

## Структура файлов

## Оптимизация затрат

### Разработка/Тестирование
Для сред разработки/тестирования можно снизить затраты:
- Используйте стандартный уровень (S0) для Azure OpenAI
- Установите меньшую ёмкость (10K TPM вместо 20K) в `infra/core/ai/cognitiveservices.bicep`
- Удаляйте ресурсы, когда они не используются: `azd down`

### Продакшн
Для продакшн:
- Увеличьте ёмкость OpenAI в зависимости от нагрузки (50K+ TPM)
- Включите зональное резервирование для высокой доступности
- Настройте мониторинг и оповещения о затратах

### Оценка стоимости
- Azure OpenAI: оплата за токен (вход + выход)
- GPT-5: примерно $3-5 за 1 млн токенов (проверьте актуальные цены)
- text-embedding-3-small: примерно $0.02 за 1 млн токенов

Калькулятор цен: https://azure.microsoft.com/pricing/calculator/

## Мониторинг

### Просмотр метрик Azure OpenAI

Перейдите в Azure Portal → Ваш ресурс OpenAI → Метрики:
- Использование по токенам
- Частота HTTP-запросов
- Время отклика
- Активные токены

## Устранение неполадок

### Проблема: конфликт имени поддомена Azure OpenAI

**Сообщение об ошибке:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Причина:**
Имя поддомена, сгенерированное из вашей подписки/окружения, уже используется, возможно, из-за предыдущего развертывания, которое не было полностью удалено.

**Решение:**
1. **Вариант 1 — Использовать другое имя окружения:**
   
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

2. **Вариант 2 — Ручное развертывание через Azure Portal:**
   - Перейдите в Azure Portal → Создать ресурс → Azure OpenAI
   - Выберите уникальное имя для ресурса
   - Разверните следующие модели:
     - **GPT-5**
     - **text-embedding-3-small** (для модулей RAG)
   - **Важно:** Запомните имена развертываний — они должны совпадать с конфигурацией в `.env`
   - После развертывания получите конечную точку и ключ API в разделе "Keys and Endpoint"
   - Создайте файл `.env` в корне проекта с содержимым:
     
     **Пример файла `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Рекомендации по именованию развертываний моделей:**
- Используйте простые, последовательные имена: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Имена развертываний должны точно совпадать с тем, что указано в `.env`
- Частая ошибка: создание модели с одним именем, а в коде ссылка на другое

### Проблема: GPT-5 недоступен в выбранном регионе

**Решение:**
- Выберите регион с доступом к GPT-5 (например, eastus, swedencentral)
- Проверьте доступность: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Проблема: недостаточно квоты для развертывания

**Решение:**
1. Запросите увеличение квоты в Azure Portal
2. Или используйте меньшую ёмкость в `main.bicep` (например, capacity: 10)

### Проблема: "Ресурс не найден" при локальном запуске

**Решение:**
1. Проверьте развертывание: `azd env get-values`
2. Убедитесь, что конечная точка и ключ корректны
3. Проверьте, что группа ресурсов существует в Azure Portal

### Проблема: Ошибка аутентификации

**Решение:**
- Проверьте правильность установки `AZURE_OPENAI_API_KEY`
- Формат ключа должен быть 32-символьной шестнадцатеричной строкой
- При необходимости получите новый ключ в Azure Portal

### Ошибка развертывания

**Проблема**: `azd provision` завершается с ошибками квоты или ёмкости

**Решение**: 
1. Попробуйте другой регион — см. раздел [Изменение регионов Azure](../../../../01-introduction/infra)
2. Проверьте, что у вашей подписки есть квота Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Приложение не подключается

**Проблема**: Java-приложение показывает ошибки подключения

**Решение**:
1. Проверьте экспорт переменных окружения:
   
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

2. Убедитесь, что формат конечной точки правильный (должен быть `https://xxx.openai.azure.com`)
3. Проверьте, что ключ API — основной или вторичный из Azure Portal

**Проблема**: 401 Unauthorized от Azure OpenAI

**Решение**:
1. Получите новый ключ API в Azure Portal → Keys and Endpoint
2. Повторно экспортируйте переменную окружения `AZURE_OPENAI_API_KEY`
3. Убедитесь, что развертывания моделей завершены (проверьте в Azure Portal)

### Проблемы с производительностью

**Проблема**: Медленное время отклика

**Решение**:
1. Проверьте использование токенов и ограничения в метриках Azure Portal
2. Увеличьте ёмкость TPM, если достигаете лимитов
3. Рассмотрите использование более высокого уровня усилий рассуждения (низкий/средний/высокий)

## Обновление инфраструктуры

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

## Рекомендации по безопасности

1. **Никогда не коммитьте ключи API** — используйте переменные окружения
2. **Используйте файлы .env локально** — добавьте `.env` в `.gitignore`
3. **Регулярно меняйте ключи** — генерируйте новые в Azure Portal
4. **Ограничивайте доступ** — используйте Azure RBAC для контроля доступа к ресурсам
5. **Мониторьте использование** — настройте оповещения о затратах в Azure Portal

## Дополнительные ресурсы

- [Документация Azure OpenAI Service](https://learn.microsoft.com/azure/ai-services/openai/)
- [Документация модели GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Документация Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Документация Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Официальная интеграция LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Поддержка

При возникновении проблем:
1. Проверьте [раздел устранения неполадок](../../../../01-introduction/infra) выше
2. Просмотрите состояние сервиса Azure OpenAI в Azure Portal
3. Откройте issue в репозитории

## Лицензия

См. файл [LICENSE](../../../../LICENSE) в корне проекта для подробностей.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Отказ от ответственности**:  
Этот документ был переведен с помощью сервиса автоматического перевода [Co-op Translator](https://github.com/Azure/co-op-translator). Несмотря на наши усилия по обеспечению точности, имейте в виду, что автоматический перевод может содержать ошибки или неточности. Оригинальный документ на его исходном языке следует считать авторитетным источником. Для получения критически важной информации рекомендуется использовать профессиональный перевод, выполненный человеком. Мы не несем ответственности за любые недоразумения или неправильные толкования, возникшие в результате использования данного перевода.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->