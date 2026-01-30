# زیرساخت Azure برای LangChain4j شروع به کار

## فهرست مطالب

- [پیش‌نیازها](../../../../01-introduction/infra)
- [معماری](../../../../01-introduction/infra)
- [منابع ایجاد شده](../../../../01-introduction/infra)
- [شروع سریع](../../../../01-introduction/infra)
- [پیکربندی](../../../../01-introduction/infra)
- [دستورات مدیریت](../../../../01-introduction/infra)
- [بهینه‌سازی هزینه](../../../../01-introduction/infra)
- [نظارت](../../../../01-introduction/infra)
- [عیب‌یابی](../../../../01-introduction/infra)
- [به‌روزرسانی زیرساخت](../../../../01-introduction/infra)
- [پاک‌سازی](../../../../01-introduction/infra)
- [ساختار فایل](../../../../01-introduction/infra)
- [توصیه‌های امنیتی](../../../../01-introduction/infra)
- [منابع اضافی](../../../../01-introduction/infra)

این دایرکتوری شامل زیرساخت Azure به صورت کد (IaC) با استفاده از Bicep و Azure Developer CLI (azd) برای استقرار منابع Azure OpenAI است.

## پیش‌نیازها

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (نسخه 2.50.0 یا بالاتر)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (نسخه 1.5.0 یا بالاتر)
- یک اشتراک Azure با مجوزهای ایجاد منابع

## معماری

**تنظیم توسعه محلی ساده‌شده** - فقط Azure OpenAI را مستقر کنید، همه برنامه‌ها را به صورت محلی اجرا کنید.

زیرساخت منابع Azure زیر را مستقر می‌کند:

### خدمات هوش مصنوعی
- **Azure OpenAI**: خدمات شناختی با دو استقرار مدل:
  - **gpt-5**: مدل تکمیل چت (ظرفیت 20K TPM)
  - **text-embedding-3-small**: مدل جاسازی برای RAG (ظرفیت 20K TPM)

### توسعه محلی
تمام برنامه‌های Spring Boot به صورت محلی روی دستگاه شما اجرا می‌شوند:
- 01-introduction (پورت 8080)
- 02-prompt-engineering (پورت 8083)
- 03-rag (پورت 8081)
- 04-tools (پورت 8084)

## منابع ایجاد شده

| نوع منبع | الگوی نام منبع | هدف |
|--------------|----------------------|---------|
| گروه منابع | `rg-{environmentName}` | شامل همه منابع |
| Azure OpenAI | `aoai-{resourceToken}` | میزبانی مدل هوش مصنوعی |

> **توجه:** `{resourceToken}` رشته‌ای یکتا است که از شناسه اشتراک، نام محیط و مکان تولید می‌شود

## شروع سریع

### 1. استقرار Azure OpenAI

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

هنگام درخواست:
- اشتراک Azure خود را انتخاب کنید
- یک مکان انتخاب کنید (توصیه شده: `eastus2` یا `swedencentral` برای دسترسی به GPT-5)
- نام محیط را تأیید کنید (پیش‌فرض: `langchain4j-dev`)

این موارد ایجاد خواهد شد:
- منبع Azure OpenAI با GPT-5 و text-embedding-3-small
- نمایش جزئیات اتصال

### 2. دریافت جزئیات اتصال

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

این موارد را نمایش می‌دهد:
- `AZURE_OPENAI_ENDPOINT`: آدرس نقطه انتهایی Azure OpenAI شما
- `AZURE_OPENAI_KEY`: کلید API برای احراز هویت
- `AZURE_OPENAI_DEPLOYMENT`: نام مدل چت (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: نام مدل جاسازی

### 3. اجرای برنامه‌ها به صورت محلی

دستور `azd up` به طور خودکار یک فایل `.env` در دایرکتوری ریشه با تمام متغیرهای محیطی لازم ایجاد می‌کند.

**توصیه شده:** همه برنامه‌های وب را اجرا کنید:

**Bash:**
```bash
# از دایرکتوری ریشه
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# از دایرکتوری ریشه
cd ../..
.\start-all.ps1
```

یا یک ماژول را به صورت جداگانه اجرا کنید:

**Bash:**
```bash
# مثال: فقط ماژول معرفی را شروع کنید
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# مثال: فقط ماژول معرفی را شروع کنید
cd ../01-introduction
.\start.ps1
```

هر دو اسکریپت به طور خودکار متغیرهای محیطی را از فایل `.env` ریشه که توسط `azd up` ایجاد شده بارگذاری می‌کنند.

## پیکربندی

### سفارشی‌سازی استقرار مدل‌ها

برای تغییر استقرار مدل‌ها، فایل `infra/main.bicep` را ویرایش کرده و پارامتر `openAiDeployments` را تغییر دهید:

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

مدل‌ها و نسخه‌های موجود: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### تغییر مناطق Azure

برای استقرار در منطقه‌ای دیگر، فایل `infra/main.bicep` را ویرایش کنید:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

بررسی دسترسی GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

برای به‌روزرسانی زیرساخت پس از اعمال تغییرات در فایل‌های Bicep:

**Bash:**
```bash
# بازسازی قالب ARM
az bicep build --file infra/main.bicep

# پیش‌نمایش تغییرات
azd provision --preview

# اعمال تغییرات
azd provision
```

**PowerShell:**
```powershell
# بازسازی قالب ARM
az bicep build --file infra/main.bicep

# پیش‌نمایش تغییرات
azd provision --preview

# اعمال تغییرات
azd provision
```

## پاک‌سازی

برای حذف همه منابع:

**Bash:**
```bash
# حذف همه منابع
azd down

# حذف همه چیز از جمله محیط
azd down --purge
```

**PowerShell:**
```powershell
# حذف همه منابع
azd down

# حذف همه چیز از جمله محیط
azd down --purge
```

**هشدار**: این کار همه منابع Azure را به طور دائمی حذف می‌کند.

## ساختار فایل

## بهینه‌سازی هزینه

### توسعه/آزمایش
برای محیط‌های توسعه/آزمایش، می‌توانید هزینه‌ها را کاهش دهید:
- استفاده از سطح استاندارد (S0) برای Azure OpenAI
- تنظیم ظرفیت کمتر (10K TPM به جای 20K) در `infra/core/ai/cognitiveservices.bicep`
- حذف منابع هنگام عدم استفاده: `azd down`

### تولید
برای محیط تولید:
- افزایش ظرفیت OpenAI بر اساس استفاده (50K+ TPM)
- فعال‌سازی افزونگی منطقه‌ای برای دسترسی بالاتر
- پیاده‌سازی نظارت مناسب و هشدارهای هزینه

### برآورد هزینه
- Azure OpenAI: پرداخت به ازای توکن (ورودی + خروجی)
- GPT-5: حدود 3-5 دلار برای هر 1 میلیون توکن (قیمت فعلی را بررسی کنید)
- text-embedding-3-small: حدود 0.02 دلار برای هر 1 میلیون توکن

ماشین حساب قیمت: https://azure.microsoft.com/pricing/calculator/

## نظارت

### مشاهده معیارهای Azure OpenAI

به پرتال Azure → منبع OpenAI خود → Metrics بروید:
- استفاده مبتنی بر توکن
- نرخ درخواست HTTP
- زمان پاسخ
- توکن‌های فعال

## عیب‌یابی

### مشکل: تداخل نام زیردامنه Azure OpenAI

**پیغام خطا:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**علت:**
نام زیردامنه تولید شده از اشتراک/محیط شما قبلاً استفاده شده است، احتمالاً از استقرار قبلی که به طور کامل پاک نشده است.

**راه‌حل:**
1. **گزینه 1 - استفاده از نام محیط متفاوت:**
   
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

2. **گزینه 2 - استقرار دستی از طریق پرتال Azure:**
   - به پرتال Azure → ایجاد منبع → Azure OpenAI بروید
   - نام یکتایی برای منبع خود انتخاب کنید
   - مدل‌های زیر را مستقر کنید:
     - **GPT-5**
     - **text-embedding-3-small** (برای ماژول‌های RAG)
   - **مهم:** نام‌های استقرار خود را یادداشت کنید - باید با پیکربندی `.env` مطابقت داشته باشند
   - پس از استقرار، نقطه انتهایی و کلید API را از "Keys and Endpoint" دریافت کنید
   - یک فایل `.env` در ریشه پروژه ایجاد کنید با:

     **نمونه فایل `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**راهنمای نام‌گذاری استقرار مدل:**
- از نام‌های ساده و یکنواخت استفاده کنید: `gpt-5`، `gpt-4o`، `text-embedding-3-small`
- نام‌های استقرار باید دقیقاً با آنچه در `.env` پیکربندی می‌کنید مطابقت داشته باشند
- اشتباه رایج: ایجاد مدل با یک نام اما ارجاع به نام متفاوت در کد

### مشکل: GPT-5 در منطقه انتخاب شده در دسترس نیست

**راه‌حل:**
- منطقه‌ای با دسترسی به GPT-5 انتخاب کنید (مثلاً eastus، swedencentral)
- دسترسی را بررسی کنید: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### مشکل: سهمیه ناکافی برای استقرار

**راه‌حل:**
1. درخواست افزایش سهمیه در پرتال Azure
2. یا استفاده از ظرفیت کمتر در `main.bicep` (مثلاً ظرفیت: 10)

### مشکل: "منبع یافت نشد" هنگام اجرای محلی

**راه‌حل:**
1. استقرار را بررسی کنید: `azd env get-values`
2. اطمینان از صحت نقطه انتهایی و کلید
3. اطمینان از وجود گروه منابع در پرتال Azure

### مشکل: احراز هویت ناموفق

**راه‌حل:**
- اطمینان از تنظیم صحیح `AZURE_OPENAI_API_KEY`
- فرمت کلید باید رشته هگزادسیمال 32 کاراکتری باشد
- در صورت نیاز کلید جدید از پرتال Azure دریافت کنید

### شکست استقرار

**مشکل**: `azd provision` با خطاهای سهمیه یا ظرفیت مواجه می‌شود

**راه‌حل**: 
1. منطقه دیگری را امتحان کنید - بخش [تغییر مناطق Azure](../../../../01-introduction/infra) را برای نحوه پیکربندی مناطق ببینید
2. بررسی کنید اشتراک شما سهمیه Azure OpenAI دارد:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### برنامه متصل نمی‌شود

**مشکل**: برنامه جاوا خطاهای اتصال نشان می‌دهد

**راه‌حل**:
1. اطمینان از صادر شدن متغیرهای محیطی:
   
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

2. بررسی صحت فرمت نقطه انتهایی (باید `https://xxx.openai.azure.com` باشد)
3. اطمینان از اینکه کلید API کلید اصلی یا ثانویه از پرتال Azure است

**مشکل**: 401 Unauthorized از Azure OpenAI

**راه‌حل**:
1. کلید API جدید از پرتال Azure → Keys and Endpoint دریافت کنید
2. متغیر محیطی `AZURE_OPENAI_API_KEY` را دوباره صادر کنید
3. اطمینان از تکمیل استقرار مدل‌ها (پر تال Azure را بررسی کنید)

### مشکلات عملکرد

**مشکل**: زمان پاسخ کند

**راه‌حل**:
1. استفاده توکن OpenAI و محدودیت‌ها را در معیارهای پرتال Azure بررسی کنید
2. در صورت رسیدن به محدودیت‌ها، ظرفیت TPM را افزایش دهید
3. استفاده از سطح تلاش استدلال بالاتر (کم/متوسط/زیاد) را در نظر بگیرید

## به‌روزرسانی زیرساخت

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

## توصیه‌های امنیتی

1. **هرگز کلیدهای API را کامیت نکنید** - از متغیرهای محیطی استفاده کنید
2. **استفاده از فایل‌های .env به صورت محلی** - فایل `.env` را به `.gitignore` اضافه کنید
3. **کلیدها را به طور منظم بچرخانید** - کلیدهای جدید در پرتال Azure تولید کنید
4. **دسترسی را محدود کنید** - از Azure RBAC برای کنترل دسترسی استفاده کنید
5. **استفاده را نظارت کنید** - هشدارهای هزینه را در پرتال Azure تنظیم کنید

## منابع اضافی

- [مستندات سرویس Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [مستندات مدل GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [مستندات Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [مستندات Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [ادغام رسمی LangChain4j با OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## پشتیبانی

برای مشکلات:
1. بخش [عیب‌یابی](../../../../01-introduction/infra) بالا را بررسی کنید
2. سلامت سرویس Azure OpenAI را در پرتال Azure مرور کنید
3. یک مسئله در مخزن باز کنید

## مجوز

جزئیات را در فایل [LICENSE](../../../../LICENSE) ریشه ببینید.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**سلب مسئولیت**:  
این سند با استفاده از سرویس ترجمه هوش مصنوعی [Co-op Translator](https://github.com/Azure/co-op-translator) ترجمه شده است. در حالی که ما در تلاش برای دقت هستیم، لطفاً توجه داشته باشید که ترجمه‌های خودکار ممکن است حاوی خطاها یا نواقصی باشند. سند اصلی به زبان بومی خود باید به عنوان منبع معتبر در نظر گرفته شود. برای اطلاعات حیاتی، ترجمه حرفه‌ای انسانی توصیه می‌شود. ما مسئول هیچ گونه سوءتفاهم یا تفسیر نادرستی که از استفاده این ترجمه ناشی شود، نیستیم.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->