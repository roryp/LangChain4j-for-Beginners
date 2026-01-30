# Azure Infrastructure for LangChain4j Getting Started

## Table of Contents

- [Prerequisites](../../../../01-introduction/infra)
- [Architecture](../../../../01-introduction/infra)
- [Resources Created](../../../../01-introduction/infra)
- [Quick Start](../../../../01-introduction/infra)
- [Configuration](../../../../01-introduction/infra)
- [Management Commands](../../../../01-introduction/infra)
- [Cost Optimization](../../../../01-introduction/infra)
- [Monitoring](../../../../01-introduction/infra)
- [Troubleshooting](../../../../01-introduction/infra)
- [Updating Infrastructure](../../../../01-introduction/infra)
- [Clean Up](../../../../01-introduction/infra)
- [File Structure](../../../../01-introduction/infra)
- [Security Recommendations](../../../../01-introduction/infra)
- [Additional Resources](../../../../01-introduction/infra)

Цей каталог містить інфраструктуру Azure як код (IaC) з використанням Bicep та Azure Developer CLI (azd) для розгортання ресурсів Azure OpenAI.

## Prerequisites

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (версія 2.50.0 або новіша)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (версія 1.5.0 або новіша)
- Підписка Azure з правами на створення ресурсів

## Architecture

**Спрощене локальне середовище розробки** – розгорнути лише Azure OpenAI, запускати всі додатки локально.

Інфраструктура розгортає такі ресурси Azure:

### AI Services
- **Azure OpenAI**: Cognitive Services з двома розгортаннями моделей:
  - **gpt-5**: модель для чат-комплітів (ємність 20K TPM)
  - **text-embedding-3-small**: модель для вбудовування для RAG (ємність 20K TPM)

### Local Development
Всі додатки Spring Boot запускаються локально на вашому комп’ютері:
- 01-introduction (порт 8080)
- 02-prompt-engineering (порт 8083)
- 03-rag (порт 8081)
- 04-tools (порт 8084)

## Resources Created

| Resource Type | Resource Name Pattern | Purpose |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | Містить усі ресурси |
| Azure OpenAI | `aoai-{resourceToken}` | Хостинг AI моделей |

> **Примітка:** `{resourceToken}` — унікальний рядок, згенерований із ID підписки, назви середовища та розташування

## Quick Start

### 1. Deploy Azure OpenAI

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

Під час запиту:
- Виберіть вашу підписку Azure
- Оберіть регіон (рекомендовано: `eastus2` або `swedencentral` для доступності GPT-5)
- Підтвердіть назву середовища (за замовчуванням: `langchain4j-dev`)

Це створить:
- Ресурс Azure OpenAI з GPT-5 та text-embedding-3-small
- Виведе деталі підключення

### 2. Get Connection Details

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Це відобразить:
- `AZURE_OPENAI_ENDPOINT`: URL вашої кінцевої точки Azure OpenAI
- `AZURE_OPENAI_KEY`: API ключ для автентифікації
- `AZURE_OPENAI_DEPLOYMENT`: Назва чат-моделі (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Назва моделі вбудовування

### 3. Run Applications Locally

Команда `azd up` автоматично створює файл `.env` у кореневому каталозі з усіма необхідними змінними середовища.

**Рекомендовано:** Запустіть усі веб-додатки:

**Bash:**
```bash
# З кореневого каталогу
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# З кореневого каталогу
cd ../..
.\start-all.ps1
```

Або запустіть окремий модуль:

**Bash:**
```bash
# Приклад: Запустіть лише модуль вступу
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Приклад: Запустіть лише модуль введення
cd ../01-introduction
.\start.ps1
```

Обидва скрипти автоматично завантажують змінні середовища з кореневого файлу `.env`, створеного `azd up`.

## Configuration

### Customizing Model Deployments

Щоб змінити розгортання моделей, відредагуйте `infra/main.bicep` і змініть параметр `openAiDeployments`:

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

Доступні моделі та версії: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Changing Azure Regions

Щоб розгорнути в іншому регіоні, відредагуйте `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Перевірте доступність GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Щоб оновити інфраструктуру після внесення змін у файли Bicep:

**Bash:**
```bash
# Перебудувати ARM шаблон
az bicep build --file infra/main.bicep

# Попередній перегляд змін
azd provision --preview

# Застосувати зміни
azd provision
```

**PowerShell:**
```powershell
# Перебудувати ARM шаблон
az bicep build --file infra/main.bicep

# Попередній перегляд змін
azd provision --preview

# Застосувати зміни
azd provision
```

## Clean Up

Щоб видалити всі ресурси:

**Bash:**
```bash
# Видалити всі ресурси
azd down

# Видалити все, включно з оточенням
azd down --purge
```

**PowerShell:**
```powershell
# Видалити всі ресурси
azd down

# Видалити все, включно з оточенням
azd down --purge
```

**Увага**: Це назавжди видалить усі ресурси Azure.

## File Structure

## Cost Optimization

### Development/Testing
Для середовищ розробки/тестування можна знизити витрати:
- Використовуйте стандартний рівень (S0) для Azure OpenAI
- Встановіть нижчу ємність (10K TPM замість 20K) у `infra/core/ai/cognitiveservices.bicep`
- Видаляйте ресурси, коли вони не використовуються: `azd down`

### Production
Для продакшн:
- Збільшуйте ємність OpenAI відповідно до використання (50K+ TPM)
- Увімкніть зональну надлишковість для вищої доступності
- Реалізуйте належний моніторинг і сповіщення про витрати

### Cost Estimation
- Azure OpenAI: оплата за токен (вхідні + вихідні)
- GPT-5: приблизно $3-5 за 1 млн токенів (перевірте актуальні ціни)
- text-embedding-3-small: приблизно $0.02 за 1 млн токенів

Калькулятор цін: https://azure.microsoft.com/pricing/calculator/

## Monitoring

### View Azure OpenAI Metrics

Перейдіть у Azure Portal → Ваш ресурс OpenAI → Метрики:
- Використання за токенами
- Частота HTTP-запитів
- Час відповіді
- Активні токени

## Troubleshooting

### Issue: Azure OpenAI subdomain name conflict

**Error Message:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Cause:**
Ім’я піддомену, згенероване з вашої підписки/середовища, вже використовується, можливо, через попереднє розгортання, яке не було повністю видалене.

**Solution:**
1. **Варіант 1 - Використати іншу назву середовища:**
   
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

2. **Варіант 2 - Ручне розгортання через Azure Portal:**
   - Перейдіть у Azure Portal → Створити ресурс → Azure OpenAI
   - Оберіть унікальну назву для вашого ресурсу
   - Розгорніть такі моделі:
     - **GPT-5**
     - **text-embedding-3-small** (для модулів RAG)
   - **Важливо:** Запам’ятайте назви розгортань — вони мають відповідати конфігурації `.env`
   - Після розгортання отримайте кінцеву точку та API ключ у розділі "Keys and Endpoint"
   - Створіть файл `.env` у корені проекту з таким вмістом:
     
     **Приклад файлу `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Рекомендації щодо іменування розгортань моделей:**
- Використовуйте прості, послідовні назви: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Назви розгортань мають точно відповідати тим, що ви вказуєте у `.env`
- Поширена помилка: створити модель з однією назвою, а в коді посилатися на іншу

### Issue: GPT-5 not available in selected region

**Solution:**
- Оберіть регіон з доступом до GPT-5 (наприклад, eastus, swedencentral)
- Перевірте доступність: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### Issue: Insufficient quota for deployment

**Solution:**
1. Запросіть збільшення квоти в Azure Portal
2. Або використайте нижчу ємність у `main.bicep` (наприклад, capacity: 10)

### Issue: "Resource not found" when running locally

**Solution:**
1. Перевірте розгортання: `azd env get-values`
2. Переконайтеся, що endpoint і ключ правильні
3. Переконайтеся, що група ресурсів існує в Azure Portal

### Issue: Authentication failed

**Solution:**
- Перевірте, що `AZURE_OPENAI_API_KEY` встановлено правильно
- Формат ключа має бути 32-символьним шістнадцятковим рядком
- За потреби отримайте новий ключ у Azure Portal

### Deployment Fails

**Issue**: `azd provision` не вдається через помилки квоти або ємності

**Solution**: 
1. Спробуйте інший регіон — див. розділ [Changing Azure Regions](../../../../01-introduction/infra) для налаштування регіонів
2. Перевірте, чи має ваша підписка квоту Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Application Not Connecting

**Issue**: Java-додаток показує помилки підключення

**Solution**:
1. Переконайтеся, що змінні середовища експортовані:
   
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

2. Перевірте правильність формату endpoint (має бути `https://xxx.openai.azure.com`)
3. Переконайтеся, що API ключ є основним або вторинним ключем з Azure Portal

**Issue**: 401 Unauthorized від Azure OpenAI

**Solution**:
1. Отримайте новий API ключ у Azure Portal → Keys and Endpoint
2. Повторно експортуйте змінну середовища `AZURE_OPENAI_API_KEY`
3. Переконайтеся, що розгортання моделей завершено (перевірте Azure Portal)

### Performance Issues

**Issue**: Повільний час відповіді

**Solution**:
1. Перевірте використання токенів OpenAI та обмеження в метриках Azure Portal
2. Збільшіть ємність TPM, якщо досягаєте лімітів
3. Розгляньте використання вищого рівня зусиль для розуміння (low/medium/high)

## Updating Infrastructure

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

## Security Recommendations

1. **Ніколи не комітьте API ключі** – використовуйте змінні середовища
2. **Використовуйте файли .env локально** – додайте `.env` до `.gitignore`
3. **Регулярно оновлюйте ключі** – генеруйте нові ключі в Azure Portal
4. **Обмежуйте доступ** – використовуйте Azure RBAC для контролю доступу до ресурсів
5. **Моніторьте використання** – налаштуйте сповіщення про витрати в Azure Portal

## Additional Resources

- [Документація Azure OpenAI Service](https://learn.microsoft.com/azure/ai-services/openai/)
- [Документація моделі GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Документація Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Документація Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Офіційна інтеграція LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Support

Для вирішення проблем:
1. Перевірте [розділ усунення несправностей](../../../../01-introduction/infra) вище
2. Перегляньте стан служби Azure OpenAI в Azure Portal
3. Відкрийте issue у репозиторії

## License

Див. файл [LICENSE](../../../../LICENSE) у корені проекту для деталей.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Відмова від відповідальності**:  
Цей документ було перекладено за допомогою сервісу автоматичного перекладу [Co-op Translator](https://github.com/Azure/co-op-translator). Хоча ми прагнемо до точності, будь ласка, майте на увазі, що автоматичні переклади можуть містити помилки або неточності. Оригінальний документ рідною мовою слід вважати авторитетним джерелом. Для критично важливої інформації рекомендується звертатися до професійного людського перекладу. Ми не несемо відповідальності за будь-які непорозуміння або неправильні тлумачення, що виникли внаслідок використання цього перекладу.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->