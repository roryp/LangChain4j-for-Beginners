<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "bb15d54593663fb75ef1302f3dd1bb1a",
  "translation_date": "2025-12-13T21:46:19+00:00",
  "source_file": "01-introduction/infra/README.md",
  "language_code": "ur"
}
-->
# LangChain4j کے لیے Azure انفراسٹرکچر شروع کرنے کا طریقہ

## فہرست مضامین

- [ضروریات](../../../../01-introduction/infra)
- [معماری](../../../../01-introduction/infra)
- [بنائے گئے وسائل](../../../../01-introduction/infra)
- [جلدی آغاز](../../../../01-introduction/infra)
- [تشکیل](../../../../01-introduction/infra)
- [انتظامی کمانڈز](../../../../01-introduction/infra)
- [لاگت کی بچت](../../../../01-introduction/infra)
- [نگرانی](../../../../01-introduction/infra)
- [مسائل کا حل](../../../../01-introduction/infra)
- [انفراسٹرکچر کی تازہ کاری](../../../../01-introduction/infra)
- [صفائی](../../../../01-introduction/infra)
- [فائل کی ساخت](../../../../01-introduction/infra)
- [سیکیورٹی کی سفارشات](../../../../01-introduction/infra)
- [اضافی وسائل](../../../../01-introduction/infra)

یہ ڈائریکٹری Azure OpenAI وسائل کی تعیناتی کے لیے Bicep اور Azure Developer CLI (azd) استعمال کرتے ہوئے Azure انفراسٹرکچر بطور کوڈ (IaC) پر مشتمل ہے۔

## ضروریات

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (ورژن 2.50.0 یا بعد کا)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (ورژن 1.5.0 یا بعد کا)
- Azure سبسکرپشن جس میں وسائل بنانے کی اجازت ہو

## معماری

**سادہ مقامی ترقی کا سیٹ اپ** - صرف Azure OpenAI تعینات کریں، تمام ایپس مقامی طور پر چلائیں۔

انفراسٹرکچر درج ذیل Azure وسائل تعینات کرتا ہے:

### AI خدمات
- **Azure OpenAI**: دو ماڈل تعیناتیوں کے ساتھ Cognitive Services:
  - **gpt-5**: چیٹ کمپلیشن ماڈل (20K TPM صلاحیت)
  - **text-embedding-3-small**: RAG کے لیے ایمبیڈنگ ماڈل (20K TPM صلاحیت)

### مقامی ترقی
تمام Spring Boot ایپلیکیشنز آپ کے کمپیوٹر پر مقامی طور پر چلتی ہیں:
- 01-introduction (پورٹ 8080)
- 02-prompt-engineering (پورٹ 8083)
- 03-rag (پورٹ 8081)
- 04-tools (پورٹ 8084)

## بنائے گئے وسائل

| وسائل کی قسم | وسائل کا نام پیٹرن | مقصد |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | تمام وسائل کو رکھتا ہے |
| Azure OpenAI | `aoai-{resourceToken}` | AI ماڈل کی میزبانی |

> **نوٹ:** `{resourceToken}` ایک منفرد سٹرنگ ہے جو سبسکرپشن ID، ماحول کے نام، اور مقام سے بنائی جاتی ہے

## جلدی آغاز

### 1. Azure OpenAI تعینات کریں

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

جب پوچھا جائے:
- اپنی Azure سبسکرپشن منتخب کریں
- مقام منتخب کریں (تجویز کردہ: `eastus2` یا `swedencentral` GPT-5 کی دستیابی کے لیے)
- ماحول کا نام تصدیق کریں (ڈیفالٹ: `langchain4j-dev`)

یہ بنائے گا:
- GPT-5 اور text-embedding-3-small کے ساتھ Azure OpenAI وسیلہ
- کنکشن کی تفصیلات آؤٹ پٹ کرے گا

### 2. کنکشن کی تفصیلات حاصل کریں

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

یہ دکھائے گا:
- `AZURE_OPENAI_ENDPOINT`: آپ کا Azure OpenAI اینڈپوائنٹ URL
- `AZURE_OPENAI_KEY`: توثیق کے لیے API کلید
- `AZURE_OPENAI_DEPLOYMENT`: چیٹ ماڈل کا نام (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: ایمبیڈنگ ماڈل کا نام

### 3. ایپلیکیشنز مقامی طور پر چلائیں

`azd up` کمانڈ خود بخود روٹ ڈائریکٹری میں `.env` فائل بناتی ہے جس میں تمام ضروری ماحول کے متغیرات ہوتے ہیں۔

**تجویز کردہ:** تمام ویب ایپلیکیشنز شروع کریں:

**Bash:**
```bash
# جڑ ڈائریکٹری سے
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# جڑ ڈائریکٹری سے
cd ../..
.\start-all.ps1
```

یا ایک ماڈیول شروع کریں:

**Bash:**
```bash
# مثال: صرف تعارفی ماڈیول شروع کریں
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# مثال: صرف تعارفی ماڈیول شروع کریں
cd ../01-introduction
.\start.ps1
```

دونوں اسکرپٹس خود بخود `azd up` سے بنائی گئی روٹ `.env` فائل سے ماحول کے متغیرات لوڈ کرتے ہیں۔

## تشکیل

### ماڈل تعیناتیوں کو حسب ضرورت بنانا

ماڈل تعیناتیوں کو تبدیل کرنے کے لیے `infra/main.bicep` میں `openAiDeployments` پیرامیٹر میں ترمیم کریں:

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

دستیاب ماڈلز اور ورژنز: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure علاقوں کو تبدیل کرنا

کسی مختلف علاقے میں تعیناتی کے لیے `infra/main.bicep` میں ترمیم کریں:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 کی دستیابی چیک کریں: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep فائلوں میں تبدیلی کے بعد انفراسٹرکچر کو اپ ڈیٹ کرنے کے لیے:

**Bash:**
```bash
# اے آر ایم ٹیمپلیٹ کو دوبارہ بنائیں
az bicep build --file infra/main.bicep

# تبدیلیوں کا پیش نظارہ کریں
azd provision --preview

# تبدیلیاں لاگو کریں
azd provision
```

**PowerShell:**
```powershell
# اے آر ایم ٹیمپلیٹ کو دوبارہ بنائیں
az bicep build --file infra/main.bicep

# تبدیلیوں کا پیش نظارہ کریں
azd provision --preview

# تبدیلیاں لاگو کریں
azd provision
```

## صفائی

تمام وسائل کو حذف کرنے کے لیے:

**Bash:**
```bash
# تمام وسائل کو حذف کریں
azd down

# ماحول سمیت سب کچھ حذف کریں
azd down --purge
```

**PowerShell:**
```powershell
# تمام وسائل کو حذف کریں
azd down

# ماحول سمیت سب کچھ حذف کریں
azd down --purge
```

**انتباہ**: یہ تمام Azure وسائل کو مستقل طور پر حذف کر دے گا۔

## فائل کی ساخت

## لاگت کی بچت

### ترقی/ٹیسٹنگ
ڈویلپمنٹ/ٹیسٹنگ ماحول کے لیے، آپ لاگت کم کر سکتے ہیں:
- Azure OpenAI کے لیے Standard tier (S0) استعمال کریں
- `infra/core/ai/cognitiveservices.bicep` میں صلاحیت کم کریں (20K کی بجائے 10K TPM)
- استعمال نہ ہونے پر وسائل حذف کریں: `azd down`

### پیداوار
پیداوار کے لیے:
- استعمال کی بنیاد پر OpenAI صلاحیت بڑھائیں (50K+ TPM)
- زیادہ دستیابی کے لیے زون ریڈنڈنسی فعال کریں
- مناسب نگرانی اور لاگت کی الرٹس نافذ کریں

### لاگت کا تخمینہ
- Azure OpenAI: ٹوکن کے حساب سے ادائیگی (ان پٹ + آؤٹ پٹ)
- GPT-5: تقریباً $3-5 فی 1M ٹوکن (موجودہ قیمت چیک کریں)
- text-embedding-3-small: تقریباً $0.02 فی 1M ٹوکن

قیمت کیلکولیٹر: https://azure.microsoft.com/pricing/calculator/

## نگرانی

### Azure OpenAI میٹرکس دیکھیں

Azure پورٹل → آپ کا OpenAI وسیلہ → میٹرکس:
- ٹوکن کی بنیاد پر استعمال
- HTTP درخواست کی شرح
- جواب کا وقت
- فعال ٹوکنز

## مسائل کا حل

### مسئلہ: Azure OpenAI سب ڈومین نام کا تصادم

**خرابی کا پیغام:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**وجہ:**
آپ کی سبسکرپشن/ماحول سے بننے والا سب ڈومین نام پہلے سے استعمال میں ہے، ممکنہ طور پر پچھلی تعیناتی سے جو مکمل طور پر حذف نہیں ہوئی۔

**حل:**
1. **اختیار 1 - مختلف ماحول کا نام استعمال کریں:**
   
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

2. **اختیار 2 - Azure پورٹل کے ذریعے دستی تعیناتی:**
   - Azure پورٹل جائیں → Create a resource → Azure OpenAI
   - اپنے وسیلہ کے لیے منفرد نام منتخب کریں
   - درج ذیل ماڈلز تعینات کریں:
     - **GPT-5**
     - **text-embedding-3-small** (RAG ماڈیولز کے لیے)
   - **اہم:** اپنے تعیناتی کے نام نوٹ کریں - یہ `.env` کنفیگریشن سے میل کھانے چاہئیں
   - تعیناتی کے بعد "Keys and Endpoint" سے اپنا اینڈپوائنٹ اور API کلید حاصل کریں
   - پروجیکٹ روٹ میں `.env` فائل بنائیں جس میں:

     **مثال `.env` فائل:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**ماڈل تعیناتی کے نام کے رہنما اصول:**
- سادہ، مستقل نام استعمال کریں: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- تعیناتی کے نام بالکل ویسے ہی ہوں جیسے آپ `.env` میں کنفیگر کرتے ہیں
- عام غلطی: ماڈل کو ایک نام سے بنانا لیکن کوڈ میں مختلف نام کا حوالہ دینا

### مسئلہ: منتخب کردہ علاقے میں GPT-5 دستیاب نہیں

**حل:**
- GPT-5 کی رسائی والے علاقے منتخب کریں (مثلاً eastus، swedencentral)
- دستیابی چیک کریں: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### مسئلہ: تعیناتی کے لیے کوٹہ ناکافی ہے

**حل:**
1. Azure پورٹل میں کوٹہ بڑھانے کی درخواست کریں
2. یا `main.bicep` میں کم صلاحیت استعمال کریں (مثلاً capacity: 10)

### مسئلہ: مقامی طور پر چلانے پر "Resource not found"

**حل:**
1. تعیناتی کی تصدیق کریں: `azd env get-values`
2. اینڈپوائنٹ اور کلید درست ہیں چیک کریں
3. Azure پورٹل میں Resource Group موجود ہے یقینی بنائیں

### مسئلہ: توثیق ناکام

**حل:**
- `AZURE_OPENAI_API_KEY` صحیح طریقے سے سیٹ ہے چیک کریں
- کلید کا فارمیٹ 32-حروف کا ہیکساڈیسیمل ہونا چاہیے
- ضرورت ہو تو Azure پورٹل سے نئی کلید حاصل کریں

### تعیناتی ناکام

**مسئلہ**: `azd provision` کوٹہ یا صلاحیت کی غلطیوں کے ساتھ ناکام

**حل**: 
1. مختلف علاقہ آزمائیں - [Azure علاقوں کو تبدیل کرنا](../../../../01-introduction/infra) سیکشن دیکھیں
2. چیک کریں کہ آپ کی سبسکرپشن میں Azure OpenAI کوٹہ موجود ہے:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### ایپلیکیشن کنیکٹ نہیں ہو رہی

**مسئلہ**: جاوا ایپلیکیشن کنکشن کی غلطیاں دکھا رہی ہے

**حل**:
1. ماحول کے متغیرات برآمد ہیں چیک کریں:
   
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

2. اینڈپوائنٹ فارمیٹ درست ہے چیک کریں (یہ ہونا چاہیے `https://xxx.openai.azure.com`)
3. API کلید Azure پورٹل سے پرائمری یا سیکنڈری کلید ہے یقینی بنائیں

**مسئلہ**: Azure OpenAI سے 401 Unauthorized

**حل**:
1. Azure پورٹل → Keys and Endpoint سے نئی API کلید حاصل کریں
2. `AZURE_OPENAI_API_KEY` ماحول کا متغیر دوبارہ برآمد کریں
3. ماڈل تعیناتیاں مکمل ہیں چیک کریں (Azure پورٹل دیکھیں)

### کارکردگی کے مسائل

**مسئلہ**: جواب دینے میں سستی

**حل**:
1. Azure پورٹل میٹرکس میں OpenAI ٹوکن استعمال اور تھروٹلنگ چیک کریں
2. اگر حدیں پہنچ رہی ہیں تو TPM صلاحیت بڑھائیں
3. زیادہ reasoning-effort سطح (کم/درمیانہ/زیادہ) استعمال کرنے پر غور کریں

## انفراسٹرکچر کی تازہ کاری

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

## سیکیورٹی کی سفارشات

1. **API کیز کبھی بھی کمیٹ نہ کریں** - ماحول کے متغیرات استعمال کریں
2. **مقامی طور پر .env فائلز استعمال کریں** - `.env` کو `.gitignore` میں شامل کریں
3. **کلیدیں باقاعدگی سے تبدیل کریں** - Azure پورٹل میں نئی کلیدیں بنائیں
4. **رسائی محدود کریں** - Azure RBAC استعمال کریں کہ کون وسائل تک رسائی حاصل کر سکتا ہے
5. **استعمال کی نگرانی کریں** - Azure پورٹل میں لاگت کی الرٹس سیٹ کریں

## اضافی وسائل

- [Azure OpenAI سروس کی دستاویزات](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 ماڈل کی دستاویزات](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI کی دستاویزات](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep کی دستاویزات](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI سرکاری انٹیگریشن](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## سپورٹ

مسائل کے لیے:
1. اوپر [مسائل کا حل](../../../../01-introduction/infra) سیکشن چیک کریں
2. Azure پورٹل میں Azure OpenAI سروس کی صحت کا جائزہ لیں
3. ریپوزیٹری میں مسئلہ کھولیں

## لائسنس

تفصیلات کے لیے روٹ [LICENSE](../../../../LICENSE) فائل دیکھیں۔

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**دستخطی نوٹ**:  
یہ دستاویز AI ترجمہ سروس [Co-op Translator](https://github.com/Azure/co-op-translator) کے ذریعے ترجمہ کی گئی ہے۔ اگرچہ ہم درستگی کے لیے کوشاں ہیں، براہ کرم اس بات سے آگاہ رہیں کہ خودکار ترجمے میں غلطیاں یا عدم درستیاں ہو سکتی ہیں۔ اصل دستاویز اپنی مادری زبان میں ہی معتبر ماخذ سمجھی جانی چاہیے۔ اہم معلومات کے لیے پیشہ ور انسانی ترجمہ کی سفارش کی جاتی ہے۔ اس ترجمے کے استعمال سے پیدا ہونے والی کسی بھی غلط فہمی یا غلط تشریح کی ذمہ داری ہم پر عائد نہیں ہوتی۔
<!-- CO-OP TRANSLATOR DISCLAIMER END -->