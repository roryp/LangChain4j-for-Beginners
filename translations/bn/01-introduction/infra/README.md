# LangChain4j এর জন্য Azure অবকাঠামো শুরু করা

## বিষয়বস্তু সূচি

- [প্রয়োজনীয়তা](../../../../01-introduction/infra)
- [স্থাপত্য](../../../../01-introduction/infra)
- [তৈরি করা সম্পদসমূহ](../../../../01-introduction/infra)
- [দ্রুত শুরু](../../../../01-introduction/infra)
- [কনফিগারেশন](../../../../01-introduction/infra)
- [পরিচালনা কমান্ডসমূহ](../../../../01-introduction/infra)
- [খরচ অপ্টিমাইজেশন](../../../../01-introduction/infra)
- [মনিটরিং](../../../../01-introduction/infra)
- [সমস্যা সমাধান](../../../../01-introduction/infra)
- [অবকাঠামো আপডেট করা](../../../../01-introduction/infra)
- [পরিষ্কার করা](../../../../01-introduction/infra)
- [ফাইল কাঠামো](../../../../01-introduction/infra)
- [নিরাপত্তা সুপারিশসমূহ](../../../../01-introduction/infra)
- [অতিরিক্ত সম্পদসমূহ](../../../../01-introduction/infra)

এই ডিরেক্টরিটি Bicep এবং Azure Developer CLI (azd) ব্যবহার করে Azure OpenAI সম্পদ মোতায়েনের জন্য Azure অবকাঠামো কোড (IaC) ধারণ করে।

## প্রয়োজনীয়তা

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (সংস্করণ 2.50.0 বা পরবর্তী)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (সংস্করণ 1.5.0 বা পরবর্তী)
- সম্পদ তৈরি করার অনুমতি সহ একটি Azure সাবস্ক্রিপশন

## স্থাপত্য

**সরলীকৃত স্থানীয় উন্নয়ন সেটআপ** - শুধুমাত্র Azure OpenAI মোতায়েন করুন, সব অ্যাপ্লিকেশন স্থানীয়ভাবে চালান।

অবকাঠামো নিম্নলিখিত Azure সম্পদ মোতায়েন করে:

### AI পরিষেবাসমূহ
- **Azure OpenAI**: দুটি মডেল মোতায়েন সহ কগনিটিভ সার্ভিসেস:
  - **gpt-5**: চ্যাট সম্পূর্ণতা মডেল (20K TPM ক্ষমতা)
  - **text-embedding-3-small**: RAG এর জন্য এমবেডিং মডেল (20K TPM ক্ষমতা)

### স্থানীয় উন্নয়ন
সব Spring Boot অ্যাপ্লিকেশন আপনার মেশিনে স্থানীয়ভাবে চলে:
- 01-introduction (পোর্ট 8080)
- 02-prompt-engineering (পোর্ট 8083)
- 03-rag (পোর্ট 8081)
- 04-tools (পোর্ট 8084)

## তৈরি করা সম্পদসমূহ

| সম্পদের ধরন | সম্পদের নামের প্যাটার্ন | উদ্দেশ্য |
|--------------|----------------------|---------|
| রিসোর্স গ্রুপ | `rg-{environmentName}` | সব সম্পদ ধারণ করে |
| Azure OpenAI | `aoai-{resourceToken}` | AI মডেল হোস্টিং |

> **নোট:** `{resourceToken}` একটি অনন্য স্ট্রিং যা সাবস্ক্রিপশন আইডি, পরিবেশের নাম এবং অবস্থান থেকে তৈরি হয়

## দ্রুত শুরু

### ১. Azure OpenAI মোতায়েন করুন

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

প্রম্পট পেলে:
- আপনার Azure সাবস্ক্রিপশন নির্বাচন করুন
- একটি অবস্থান নির্বাচন করুন (সুপারিশকৃত: `eastus2` অথবা `swedencentral` GPT-5 উপলব্ধতার জন্য)
- পরিবেশের নাম নিশ্চিত করুন (ডিফল্ট: `langchain4j-dev`)

এটি তৈরি করবে:
- GPT-5 এবং text-embedding-3-small সহ Azure OpenAI সম্পদ
- সংযোগের বিবরণ আউটপুট করবে

### ২. সংযোগের বিবরণ পান

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

এটি প্রদর্শন করবে:
- `AZURE_OPENAI_ENDPOINT`: আপনার Azure OpenAI এন্ডপয়েন্ট URL
- `AZURE_OPENAI_KEY`: প্রমাণীকরণের জন্য API কী
- `AZURE_OPENAI_DEPLOYMENT`: চ্যাট মডেলের নাম (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: এমবেডিং মডেলের নাম

### ৩. অ্যাপ্লিকেশনগুলি স্থানীয়ভাবে চালান

`azd up` কমান্ড স্বয়ংক্রিয়ভাবে রুট ডিরেক্টরিতে একটি `.env` ফাইল তৈরি করে যা সব প্রয়োজনীয় পরিবেশ ভেরিয়েবল ধারণ করে।

**সুপারিশকৃত:** সব ওয়েব অ্যাপ্লিকেশন শুরু করুন:

**Bash:**
```bash
# রুট ডিরেক্টরি থেকে
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# রুট ডিরেক্টরি থেকে
cd ../..
.\start-all.ps1
```

অথবা একটি একক মডিউল শুরু করুন:

**Bash:**
```bash
# উদাহরণ: শুধুমাত্র পরিচিতি মডিউল শুরু করুন
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# উদাহরণ: শুধুমাত্র পরিচিতি মডিউল শুরু করুন
cd ../01-introduction
.\start.ps1
```

উভয় স্ক্রিপ্টই `azd up` দ্বারা তৈরি রুট `.env` ফাইল থেকে পরিবেশ ভেরিয়েবল স্বয়ংক্রিয়ভাবে লোড করে।

## কনফিগারেশন

### মডেল মোতায়েন কাস্টমাইজ করা

মডেল মোতায়েন পরিবর্তন করতে, `infra/main.bicep` সম্পাদনা করুন এবং `openAiDeployments` প্যারামিটার পরিবর্তন করুন:

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

উপলব্ধ মডেল এবং সংস্করণ: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Azure অঞ্চল পরিবর্তন

অন্য অঞ্চলে মোতায়েন করতে, `infra/main.bicep` সম্পাদনা করুন:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

GPT-5 উপলব্ধতা পরীক্ষা করুন: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Bicep ফাইলে পরিবর্তন করার পর অবকাঠামো আপডেট করতে:

**Bash:**
```bash
# ARM টেমপ্লেট পুনর্নির্মাণ করুন
az bicep build --file infra/main.bicep

# পরিবর্তনগুলি পূর্বদর্শন করুন
azd provision --preview

# পরিবর্তনগুলি প্রয়োগ করুন
azd provision
```

**PowerShell:**
```powershell
# ARM টেমপ্লেট পুনর্নির্মাণ করুন
az bicep build --file infra/main.bicep

# পরিবর্তনগুলি পূর্বদর্শন করুন
azd provision --preview

# পরিবর্তনগুলি প্রয়োগ করুন
azd provision
```

## পরিষ্কার করা

সব সম্পদ মুছে ফেলতে:

**Bash:**
```bash
# সমস্ত সম্পদ মুছে ফেলুন
azd down

# পরিবেশ সহ সবকিছু মুছে ফেলুন
azd down --purge
```

**PowerShell:**
```powershell
# সমস্ত সম্পদ মুছে ফেলুন
azd down

# পরিবেশসহ সবকিছু মুছে ফেলুন
azd down --purge
```

**সতর্কতা**: এটি সব Azure সম্পদ স্থায়ীভাবে মুছে ফেলবে।

## ফাইল কাঠামো

## খরচ অপ্টিমাইজেশন

### উন্নয়ন/পরীক্ষা
ডেভ/টেস্ট পরিবেশের জন্য, আপনি খরচ কমাতে পারেন:
- Azure OpenAI এর জন্য Standard tier (S0) ব্যবহার করুন
- `infra/core/ai/cognitiveservices.bicep` এ ক্ষমতা কমিয়ে 10K TPM সেট করুন (20K এর পরিবর্তে)
- ব্যবহার না করার সময় সম্পদ মুছে ফেলুন: `azd down`

### প্রোডাকশন
প্রোডাকশনের জন্য:
- ব্যবহার অনুযায়ী OpenAI ক্ষমতা বাড়ান (50K+ TPM)
- উচ্চ উপলব্ধতার জন্য জোন রিডান্ডেন্সি সক্রিয় করুন
- সঠিক মনিটরিং এবং খরচ সতর্কতা বাস্তবায়ন করুন

### খরচ অনুমান
- Azure OpenAI: টোকেন প্রতি পেমেন্ট (ইনপুট + আউটপুট)
- GPT-5: প্রায় $3-5 প্রতি 1M টোকেন (বর্তমান মূল্য যাচাই করুন)
- text-embedding-3-small: প্রায় $0.02 প্রতি 1M টোকেন

মূল্য নির্ধারণ ক্যালকুলেটর: https://azure.microsoft.com/pricing/calculator/

## মনিটরিং

### Azure OpenAI মেট্রিক্স দেখুন

Azure পোর্টালে যান → আপনার OpenAI সম্পদ → মেট্রিক্স:
- টোকেন-ভিত্তিক ব্যবহার
- HTTP অনুরোধ হার
- প্রতিক্রিয়া সময়
- সক্রিয় টোকেন

## সমস্যা সমাধান

### সমস্যা: Azure OpenAI সাবডোমেইন নাম সংঘর্ষ

**ত্রুটি বার্তা:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**কারণ:**
আপনার সাবস্ক্রিপশন/পরিবেশ থেকে তৈরি সাবডোমেইন নাম ইতিমধ্যেই ব্যবহৃত হচ্ছে, সম্ভবত পূর্বের মোতায়েন থেকে যা সম্পূর্ণরূপে মুছে ফেলা হয়নি।

**সমাধান:**
1. **বিকল্প ১ - ভিন্ন পরিবেশের নাম ব্যবহার করুন:**
   
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

2. **বিকল্প ২ - Azure পোর্টালের মাধ্যমে ম্যানুয়াল মোতায়েন:**
   - Azure পোর্টালে যান → একটি সম্পদ তৈরি করুন → Azure OpenAI
   - আপনার সম্পদের জন্য একটি অনন্য নাম নির্বাচন করুন
   - নিম্নলিখিত মডেল মোতায়েন করুন:
     - **GPT-5**
     - **text-embedding-3-small** (RAG মডিউলগুলির জন্য)
   - **গুরুত্বপূর্ণ:** আপনার মোতায়েন নামগুলি নোট করুন - এগুলো `.env` কনফিগারেশনের সাথে মিলে যেতে হবে
   - মোতায়েনের পরে, "Keys and Endpoint" থেকে আপনার এন্ডপয়েন্ট এবং API কী পান
   - প্রকল্পের রুটে একটি `.env` ফাইল তৈরি করুন যার মধ্যে থাকবে:
     
     **উদাহরণ `.env` ফাইল:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**মডেল মোতায়েন নামকরণের নির্দেশিকা:**
- সহজ, সঙ্গতিপূর্ণ নাম ব্যবহার করুন: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- মোতায়েন নাম অবশ্যই `.env` এ কনফিগার করা নামের সাথে সঠিক মিল থাকতে হবে
- সাধারণ ভুল: একটি নাম দিয়ে মডেল তৈরি করা কিন্তু কোডে ভিন্ন নাম উল্লেখ করা

### সমস্যা: নির্বাচিত অঞ্চলে GPT-5 উপলব্ধ নয়

**সমাধান:**
- GPT-5 অ্যাক্সেস সহ একটি অঞ্চল নির্বাচন করুন (যেমন, eastus, swedencentral)
- উপলব্ধতা পরীক্ষা করুন: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### সমস্যা: মোতায়েনের জন্য পর্যাপ্ত কোটা নেই

**সমাধান:**
1. Azure পোর্টালে কোটা বৃদ্ধি অনুরোধ করুন
2. অথবা `main.bicep` এ কম ক্ষমতা ব্যবহার করুন (যেমন, capacity: 10)

### সমস্যা: স্থানীয়ভাবে চালানোর সময় "Resource not found"

**সমাধান:**
1. মোতায়েন যাচাই করুন: `azd env get-values`
2. এন্ডপয়েন্ট এবং কী সঠিক কিনা পরীক্ষা করুন
3. Azure পোর্টালে রিসোর্স গ্রুপ আছে কিনা নিশ্চিত করুন

### সমস্যা: প্রমাণীকরণ ব্যর্থ

**সমাধান:**
- `AZURE_OPENAI_API_KEY` সঠিকভাবে সেট আছে কিনা যাচাই করুন
- কী ফরম্যাট ৩২-অক্ষরের হেক্সাডেসিমাল স্ট্রিং হওয়া উচিত
- প্রয়োজনে Azure পোর্টাল থেকে নতুন কী নিন

### মোতায়েন ব্যর্থ

**সমস্যা**: `azd provision` কোটা বা ক্ষমতা ত্রুটির কারণে ব্যর্থ

**সমাধান**: 
1. ভিন্ন অঞ্চল চেষ্টা করুন - অঞ্চল কনফিগার করার জন্য [Changing Azure Regions](../../../../01-introduction/infra) অংশ দেখুন
2. নিশ্চিত করুন আপনার সাবস্ক্রিপশনে Azure OpenAI কোটা আছে:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### অ্যাপ্লিকেশন সংযোগ করছে না

**সমস্যা**: জাভা অ্যাপ্লিকেশন সংযোগ ত্রুটি দেখাচ্ছে

**সমাধান**:
1. পরিবেশ ভেরিয়েবল রপ্তানি হয়েছে কিনা যাচাই করুন:
   
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

2. এন্ডপয়েন্ট ফরম্যাট সঠিক কিনা পরীক্ষা করুন (হওয়া উচিত `https://xxx.openai.azure.com`)
3. Azure পোর্টাল থেকে প্রাপ্ত প্রাথমিক বা মাধ্যমিক API কী ব্যবহার করছেন কিনা যাচাই করুন

**সমস্যা**: Azure OpenAI থেকে 401 Unauthorized

**সমাধান**:
1. Azure পোর্টাল → Keys and Endpoint থেকে নতুন API কী নিন
2. `AZURE_OPENAI_API_KEY` পরিবেশ ভেরিয়েবল পুনরায় রপ্তানি করুন
3. মডেল মোতায়েন সম্পূর্ণ হয়েছে কিনা নিশ্চিত করুন (Azure পোর্টাল চেক করুন)

### কর্মক্ষমতা সমস্যা

**সমস্যা**: ধীর প্রতিক্রিয়া সময়

**সমাধান**:
1. Azure পোর্টালের মেট্রিক্সে OpenAI টোকেন ব্যবহার এবং থ্রটলিং পরীক্ষা করুন
2. সীমা ছাড়ালে TPM ক্ষমতা বাড়ান
3. উচ্চতর reasoning-effort স্তর ব্যবহার বিবেচনা করুন (low/medium/high)

## অবকাঠামো আপডেট করা

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

## নিরাপত্তা সুপারিশসমূহ

1. **কখনো API কী কমিট করবেন না** - পরিবেশ ভেরিয়েবল ব্যবহার করুন
2. **স্থানীয়ভাবে .env ফাইল ব্যবহার করুন** - `.env` কে `.gitignore` এ যোগ করুন
3. **নিয়মিত কী রোটেট করুন** - Azure পোর্টালে নতুন কী তৈরি করুন
4. **অ্যাক্সেস সীমাবদ্ধ করুন** - Azure RBAC ব্যবহার করে কে সম্পদ অ্যাক্সেস করতে পারে তা নিয়ন্ত্রণ করুন
5. **ব্যবহার মনিটর করুন** - Azure পোর্টালে খরচ সতর্কতা সেট করুন

## অতিরিক্ত সম্পদসমূহ

- [Azure OpenAI সার্ভিস ডকুমেন্টেশন](https://learn.microsoft.com/azure/ai-services/openai/)
- [GPT-5 মডেল ডকুমেন্টেশন](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Azure Developer CLI ডকুমেন্টেশন](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Bicep ডকুমেন্টেশন](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [LangChain4j OpenAI অফিসিয়াল ইন্টিগ্রেশন](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## সহায়তা

সমস্যার জন্য:
1. উপরের [সমস্যা সমাধান বিভাগ](../../../../01-introduction/infra) দেখুন
2. Azure পোর্টালে Azure OpenAI সার্ভিসের স্বাস্থ্য পর্যালোচনা করুন
3. রিপোজিটরিতে একটি ইস্যু খুলুন

## লাইসেন্স

বিস্তারিত জানতে রুট [LICENSE](../../../../LICENSE) ফাইল দেখুন।

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**অস্বীকৃতি**:  
এই নথিটি AI অনুবাদ সেবা [Co-op Translator](https://github.com/Azure/co-op-translator) ব্যবহার করে অনূদিত হয়েছে। আমরা যথাসাধ্য সঠিকতার চেষ্টা করি, তবে স্বয়ংক্রিয় অনুবাদে ত্রুটি বা অসঙ্গতি থাকতে পারে। মূল নথিটি তার নিজস্ব ভাষায়ই কর্তৃত্বপূর্ণ উৎস হিসেবে বিবেচিত হওয়া উচিত। গুরুত্বপূর্ণ তথ্যের জন্য পেশাদার মানব অনুবাদ গ্রহণ করার পরামর্শ দেওয়া হয়। এই অনুবাদের ব্যবহারে সৃষ্ট কোনো ভুল বোঝাবুঝি বা ভুল ব্যাখ্যার জন্য আমরা দায়ী নই।
<!-- CO-OP TRANSLATOR DISCLAIMER END -->