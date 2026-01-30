# بنية تحتية أزور لـ LangChain4j للبدء

## جدول المحتويات

- [المتطلبات الأساسية](../../../../01-introduction/infra)
- [البنية المعمارية](../../../../01-introduction/infra)
- [الموارد التي تم إنشاؤها](../../../../01-introduction/infra)
- [البدء السريع](../../../../01-introduction/infra)
- [التكوين](../../../../01-introduction/infra)
- [أوامر الإدارة](../../../../01-introduction/infra)
- [تحسين التكلفة](../../../../01-introduction/infra)
- [المراقبة](../../../../01-introduction/infra)
- [استكشاف الأخطاء وإصلاحها](../../../../01-introduction/infra)
- [تحديث البنية التحتية](../../../../01-introduction/infra)
- [التنظيف](../../../../01-introduction/infra)
- [هيكل الملفات](../../../../01-introduction/infra)
- [توصيات الأمان](../../../../01-introduction/infra)
- [الموارد الإضافية](../../../../01-introduction/infra)

يحتوي هذا الدليل على بنية تحتية أزور ككود (IaC) باستخدام Bicep و Azure Developer CLI (azd) لنشر موارد Azure OpenAI.

## المتطلبات الأساسية

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (الإصدار 2.50.0 أو أحدث)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (الإصدار 1.5.0 أو أحدث)
- اشتراك أزور مع أذونات لإنشاء الموارد

## البنية المعمارية

**إعداد تطوير محلي مبسط** - نشر Azure OpenAI فقط، وتشغيل جميع التطبيقات محليًا.

تنشر البنية التحتية الموارد التالية في أزور:

### خدمات الذكاء الاصطناعي
- **Azure OpenAI**: خدمات معرفية مع نشرين للنماذج:
  - **gpt-5**: نموذج إكمال المحادثة (سعة 20 ألف TPM)
  - **text-embedding-3-small**: نموذج التضمين لـ RAG (سعة 20 ألف TPM)

### التطوير المحلي
تشغيل جميع تطبيقات Spring Boot محليًا على جهازك:
- 01-introduction (المنفذ 8080)
- 02-prompt-engineering (المنفذ 8083)
- 03-rag (المنفذ 8081)
- 04-tools (المنفذ 8084)

## الموارد التي تم إنشاؤها

| نوع المورد | نمط اسم المورد | الغرض |
|--------------|----------------------|---------|
| مجموعة الموارد | `rg-{environmentName}` | تحتوي على جميع الموارد |
| Azure OpenAI | `aoai-{resourceToken}` | استضافة نموذج الذكاء الاصطناعي |

> **ملاحظة:** `{resourceToken}` هو سلسلة فريدة تم إنشاؤها من معرف الاشتراك، واسم البيئة، والموقع

## البدء السريع

### 1. نشر Azure OpenAI

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

عند المطالبة:
- اختر اشتراك أزور الخاص بك
- اختر موقعًا (موصى به: `eastus2` أو `swedencentral` لتوفر GPT-5)
- أكد اسم البيئة (الافتراضي: `langchain4j-dev`)

سيتم إنشاء:
- مورد Azure OpenAI مع GPT-5 و text-embedding-3-small
- تفاصيل الاتصال في الإخراج

### 2. الحصول على تفاصيل الاتصال

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

يعرض هذا:
- `AZURE_OPENAI_ENDPOINT`: عنوان نقطة نهاية Azure OpenAI الخاص بك
- `AZURE_OPENAI_KEY`: مفتاح API للمصادقة
- `AZURE_OPENAI_DEPLOYMENT`: اسم نموذج المحادثة (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: اسم نموذج التضمين

### 3. تشغيل التطبيقات محليًا

يقوم أمر `azd up` تلقائيًا بإنشاء ملف `.env` في الدليل الجذري مع جميع متغيرات البيئة اللازمة.

**موصى به:** ابدأ جميع تطبيقات الويب:

**Bash:**
```bash
# من الدليل الجذري
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# من الدليل الجذري
cd ../..
.\start-all.ps1
```

أو ابدأ وحدة واحدة فقط:

**Bash:**
```bash
# مثال: ابدأ فقط وحدة المقدمة
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# مثال: ابدأ فقط بوحدة المقدمة
cd ../01-introduction
.\start.ps1
```

كلا النصين يقومان بتحميل متغيرات البيئة تلقائيًا من ملف `.env` الجذري الذي أنشأه `azd up`.

## التكوين

### تخصيص نشرات النماذج

لتغيير نشرات النماذج، حرر `infra/main.bicep` وعدل معلمة `openAiDeployments`:

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

النماذج والإصدارات المتاحة: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### تغيير مناطق أزور

لنشر في منطقة مختلفة، حرر `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

تحقق من توفر GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

لتحديث البنية التحتية بعد إجراء تغييرات على ملفات Bicep:

**Bash:**
```bash
# إعادة بناء قالب ARM
az bicep build --file infra/main.bicep

# معاينة التغييرات
azd provision --preview

# تطبيق التغييرات
azd provision
```

**PowerShell:**
```powershell
# إعادة بناء قالب ARM
az bicep build --file infra/main.bicep

# معاينة التغييرات
azd provision --preview

# تطبيق التغييرات
azd provision
```

## التنظيف

لحذف جميع الموارد:

**Bash:**
```bash
# حذف كل الموارد
azd down

# حذف كل شيء بما في ذلك البيئة
azd down --purge
```

**PowerShell:**
```powershell
# حذف كل الموارد
azd down

# حذف كل شيء بما في ذلك البيئة
azd down --purge
```

**تحذير**: سيؤدي هذا إلى حذف جميع موارد أزور بشكل دائم.

## هيكل الملفات

## تحسين التكلفة

### التطوير/الاختبار
لبيئات التطوير/الاختبار، يمكنك تقليل التكاليف:
- استخدم الطبقة القياسية (S0) لـ Azure OpenAI
- اضبط السعة على قيمة أقل (10 آلاف TPM بدلاً من 20 ألف) في `infra/core/ai/cognitiveservices.bicep`
- احذف الموارد عند عدم الاستخدام: `azd down`

### الإنتاج
للبيئة الإنتاجية:
- زيادة سعة OpenAI بناءً على الاستخدام (50 ألف TPM أو أكثر)
- تفعيل التكرار عبر المناطق لتوفر أعلى
- تنفيذ مراقبة مناسبة وتنبيهات التكلفة

### تقدير التكلفة
- Azure OpenAI: الدفع حسب عدد الرموز (الإدخال + الإخراج)
- GPT-5: حوالي 3-5 دولارات لكل مليون رمز (تحقق من الأسعار الحالية)
- text-embedding-3-small: حوالي 0.02 دولار لكل مليون رمز

حاسبة الأسعار: https://azure.microsoft.com/pricing/calculator/

## المراقبة

### عرض مقاييس Azure OpenAI

اذهب إلى بوابة أزور → مورد OpenAI الخاص بك → المقاييس:
- الاستخدام بناءً على الرموز
- معدل طلبات HTTP
- زمن الاستجابة
- الرموز النشطة

## استكشاف الأخطاء وإصلاحها

### المشكلة: تعارض اسم النطاق الفرعي لـ Azure OpenAI

**رسالة الخطأ:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**السبب:**
اسم النطاق الفرعي الذي تم إنشاؤه من اشتراكك/بيئتك مستخدم بالفعل، ربما من نشر سابق لم يتم تنظيفه بالكامل.

**الحل:**
1. **الخيار 1 - استخدم اسم بيئة مختلف:**
   
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

2. **الخيار 2 - النشر اليدوي عبر بوابة أزور:**
   - اذهب إلى بوابة أزور → إنشاء مورد → Azure OpenAI
   - اختر اسمًا فريدًا لموردك
   - انشر النماذج التالية:
     - **GPT-5**
     - **text-embedding-3-small** (لوحدات RAG)
   - **مهم:** لاحظ أسماء النشر الخاصة بك - يجب أن تتطابق مع تكوين `.env`
   - بعد النشر، احصل على نقطة النهاية ومفتاح API من "المفاتيح ونقطة النهاية"
   - أنشئ ملف `.env` في جذر المشروع يحتوي على:
     
     **مثال على ملف `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**إرشادات تسمية نشر النماذج:**
- استخدم أسماء بسيطة ومتسقة: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- يجب أن تتطابق أسماء النشر تمامًا مع ما تهيئه في `.env`
- خطأ شائع: إنشاء نموذج باسم واحد ولكن الإشارة إلى اسم مختلف في الكود

### المشكلة: GPT-5 غير متوفر في المنطقة المختارة

**الحل:**
- اختر منطقة بها وصول إلى GPT-5 (مثل eastus، swedencentral)
- تحقق من التوفر: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### المشكلة: حصة غير كافية للنشر

**الحل:**
1. اطلب زيادة الحصة في بوابة أزور
2. أو استخدم سعة أقل في `main.bicep` (مثلاً: capacity: 10)

### المشكلة: "المورد غير موجود" عند التشغيل محليًا

**الحل:**
1. تحقق من النشر: `azd env get-values`
2. تحقق من صحة نقطة النهاية والمفتاح
3. تأكد من وجود مجموعة الموارد في بوابة أزور

### المشكلة: فشل المصادقة

**الحل:**
- تحقق من تعيين `AZURE_OPENAI_API_KEY` بشكل صحيح
- يجب أن يكون المفتاح سلسلة سداسية عشرية مكونة من 32 حرفًا
- احصل على مفتاح جديد من بوابة أزور إذا لزم الأمر

### فشل النشر

**المشكلة**: يفشل `azd provision` بأخطاء الحصة أو السعة

**الحل**: 
1. جرب منطقة مختلفة - راجع قسم [تغيير مناطق أزور](../../../../01-introduction/infra) لكيفية تكوين المناطق
2. تحقق من أن اشتراكك يحتوي على حصة Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### التطبيق لا يتصل

**المشكلة**: يظهر تطبيق جافا أخطاء اتصال

**الحل**:
1. تحقق من تصدير متغيرات البيئة:
   
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

2. تحقق من صحة تنسيق نقطة النهاية (يجب أن تكون `https://xxx.openai.azure.com`)
3. تحقق من أن مفتاح API هو المفتاح الأساسي أو الثانوي من بوابة أزور

**المشكلة**: 401 غير مصرح من Azure OpenAI

**الحل**:
1. احصل على مفتاح API جديد من بوابة أزور → المفاتيح ونقطة النهاية
2. أعد تصدير متغير البيئة `AZURE_OPENAI_API_KEY`
3. تأكد من اكتمال نشر النماذج (تحقق من بوابة أزور)

### مشاكل الأداء

**المشكلة**: بطء أوقات الاستجابة

**الحل**:
1. تحقق من استخدام الرموز والحدود في مقاييس بوابة أزور
2. زد سعة TPM إذا وصلت إلى الحدود
3. فكر في استخدام مستوى جهد استدلال أعلى (منخفض/متوسط/مرتفع)

## تحديث البنية التحتية

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

## توصيات الأمان

1. **لا تقم أبدًا بالتزام مفاتيح API** - استخدم متغيرات البيئة
2. **استخدم ملفات .env محليًا** - أضف `.env` إلى `.gitignore`
3. **دوّر المفاتيح بانتظام** - أنشئ مفاتيح جديدة في بوابة أزور
4. **حدد الوصول** - استخدم Azure RBAC للتحكم بمن يمكنه الوصول إلى الموارد
5. **راقب الاستخدام** - قم بإعداد تنبيهات التكلفة في بوابة أزور

## الموارد الإضافية

- [توثيق خدمة Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [توثيق نموذج GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [توثيق Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [توثيق Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [تكامل LangChain4j OpenAI الرسمي](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## الدعم

للمشاكل:
1. تحقق من [قسم استكشاف الأخطاء وإصلاحها](../../../../01-introduction/infra) أعلاه
2. راجع حالة خدمة Azure OpenAI في بوابة أزور
3. افتح مشكلة في المستودع

## الترخيص

راجع ملف [LICENSE](../../../../LICENSE) في الجذر للتفاصيل.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**إخلاء المسؤولية**:  
تمت ترجمة هذا المستند باستخدام خدمة الترجمة الآلية [Co-op Translator](https://github.com/Azure/co-op-translator). بينما نسعى لتحقيق الدقة، يرجى العلم أن الترجمات الآلية قد تحتوي على أخطاء أو عدم دقة. يجب اعتبار المستند الأصلي بلغته الأصلية المصدر الموثوق به. للمعلومات الهامة، يُنصح بالاعتماد على الترجمة البشرية المهنية. نحن غير مسؤولين عن أي سوء فهم أو تفسير ناتج عن استخدام هذه الترجمة.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->