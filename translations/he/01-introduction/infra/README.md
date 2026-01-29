# תשתית Azure עבור LangChain4j התחלה מהירה

## תוכן העניינים

- [דרישות מוקדמות](../../../../01-introduction/infra)
- [ארכיטקטורה](../../../../01-introduction/infra)
- [משאבים שנוצרו](../../../../01-introduction/infra)
- [התחלה מהירה](../../../../01-introduction/infra)
- [הגדרות](../../../../01-introduction/infra)
- [פקודות ניהול](../../../../01-introduction/infra)
- [אופטימיזציה של עלויות](../../../../01-introduction/infra)
- [ניטור](../../../../01-introduction/infra)
- [פתרון בעיות](../../../../01-introduction/infra)
- [עדכון תשתית](../../../../01-introduction/infra)
- [ניקוי](../../../../01-introduction/infra)
- [מבנה קבצים](../../../../01-introduction/infra)
- [המלצות אבטחה](../../../../01-introduction/infra)
- [משאבים נוספים](../../../../01-introduction/infra)

תיקייה זו מכילה את תשתית Azure כקוד (IaC) באמצעות Bicep ו-Azure Developer CLI (azd) לפריסת משאבי Azure OpenAI.

## דרישות מוקדמות

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (גרסה 2.50.0 ומעלה)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (גרסה 1.5.0 ומעלה)
- מנוי Azure עם הרשאות ליצירת משאבים

## ארכיטקטורה

**הגדרת פיתוח מקומי מפושטת** - פרוס רק את Azure OpenAI, הרץ את כל האפליקציות מקומית.

התשתית מפרסת את משאבי Azure הבאים:

### שירותי AI
- **Azure OpenAI**: שירותי קוגניציה עם שתי פריסות מודל:
  - **gpt-5**: מודל שיחה להשלמת טקסט (קיבולת 20K TPM)
  - **text-embedding-3-small**: מודל הטמעה ל-RAG (קיבולת 20K TPM)

### פיתוח מקומי
כל אפליקציות Spring Boot רצות מקומית במחשב שלך:
- 01-introduction (פורט 8080)
- 02-prompt-engineering (פורט 8083)
- 03-rag (פורט 8081)
- 04-tools (פורט 8084)

## משאבים שנוצרו

| סוג משאב | תבנית שם המשאב | מטרה |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | מכיל את כל המשאבים |
| Azure OpenAI | `aoai-{resourceToken}` | אירוח מודל AI |

> **הערה:** `{resourceToken}` הוא מחרוזת ייחודית שנוצרה ממזהה המנוי, שם הסביבה והמיקום

## התחלה מהירה

### 1. פרוס Azure OpenAI

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

בעת בקשה:
- בחר את מנוי Azure שלך
- בחר מיקום (מומלץ: `eastus2` או `swedencentral` לזמינות GPT-5)
- אשר את שם הסביבה (ברירת מחדל: `langchain4j-dev`)

זה ייצור:
- משאב Azure OpenAI עם GPT-5 ו-text-embedding-3-small
- פרטי חיבור בפלט

### 2. קבל פרטי חיבור

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

זה מציג:
- `AZURE_OPENAI_ENDPOINT`: כתובת נקודת הקצה של Azure OpenAI שלך
- `AZURE_OPENAI_KEY`: מפתח API לאימות
- `AZURE_OPENAI_DEPLOYMENT`: שם מודל השיחה (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: שם מודל ההטמעה

### 3. הרץ אפליקציות מקומית

הפקודה `azd up` יוצרת אוטומטית קובץ `.env` בתיקיית השורש עם כל משתני הסביבה הנדרשים.

**מומלץ:** הפעל את כל אפליקציות הווב:

**Bash:**
```bash
# מהספרייה השורשית
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# מהספרייה השורשית
cd ../..
.\start-all.ps1
```

או הפעל מודול יחיד:

**Bash:**
```bash
# דוגמה: התחל רק את מודול ההקדמה
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# דוגמה: התחל רק את מודול ההקדמה
cd ../01-introduction
.\start.ps1
```

שני הסקריפטים טוענים אוטומטית את משתני הסביבה מקובץ `.env` שנוצר על ידי `azd up`.

## הגדרות

### התאמת פריסות מודל

כדי לשנות פריסות מודל, ערוך את `infra/main.bicep` ושנה את הפרמטר `openAiDeployments`:

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

מודלים וגרסאות זמינים: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### שינוי אזורי Azure

כדי לפרוס באזור שונה, ערוך את `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

בדוק זמינות GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

כדי לעדכן את התשתית לאחר שינוי קבצי Bicep:

**Bash:**
```bash
# לבנות מחדש את תבנית ה-ARM
az bicep build --file infra/main.bicep

# תצוגה מקדימה של שינויים
azd provision --preview

# החל שינויים
azd provision
```

**PowerShell:**
```powershell
# לבנות מחדש את תבנית ה-ARM
az bicep build --file infra/main.bicep

# תצוגה מקדימה של שינויים
azd provision --preview

# החל שינויים
azd provision
```

## ניקוי

כדי למחוק את כל המשאבים:

**Bash:**
```bash
# מחק את כל המשאבים
azd down

# מחק הכל כולל הסביבה
azd down --purge
```

**PowerShell:**
```powershell
# מחק את כל המשאבים
azd down

# מחק הכל כולל הסביבה
azd down --purge
```

**אזהרה**: פעולה זו תמחק לצמיתות את כל משאבי Azure.

## מבנה קבצים

## אופטימיזציה של עלויות

### פיתוח/בדיקות
לסביבות פיתוח/בדיקה, ניתן להפחית עלויות:
- השתמש בשכבת Standard (S0) עבור Azure OpenAI
- הגדר קיבולת נמוכה יותר (10K TPM במקום 20K) ב-`infra/core/ai/cognitiveservices.bicep`
- מחק משאבים כשאינם בשימוש: `azd down`

### ייצור
לייצור:
- הגדל קיבולת OpenAI בהתאם לשימוש (50K+ TPM)
- אפשר רדונדנס אזורי לזמינות גבוהה יותר
- יישם ניטור והתראות עלויות מתאימות

### הערכת עלויות
- Azure OpenAI: תשלום לפי טוקן (קלט + פלט)
- GPT-5: כ-3-5$ לכל מיליון טוקנים (בדוק מחירים עדכניים)
- text-embedding-3-small: כ-0.02$ לכל מיליון טוקנים

מחשבון מחירים: https://azure.microsoft.com/pricing/calculator/

## ניטור

### צפייה במדדי Azure OpenAI

גש לפורטל Azure → משאב OpenAI שלך → מדדים:
- שימוש מבוסס טוקנים
- קצב בקשות HTTP
- זמן תגובה
- טוקנים פעילים

## פתרון בעיות

### בעיה: קונפליקט בשם תת-דומיין של Azure OpenAI

**הודעת שגיאה:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**סיבה:**
שם תת-הדומיין שנוצר מהמנוי/הסביבה שלך כבר בשימוש, ייתכן מפריסה קודמת שלא נמחקה במלואה.

**פתרון:**
1. **אפשרות 1 - השתמש בשם סביבה שונה:**
   
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

2. **אפשרות 2 - פריסה ידנית דרך פורטל Azure:**
   - עבור לפורטל Azure → צור משאב → Azure OpenAI
   - בחר שם ייחודי למשאב שלך
   - פרוס את המודלים הבאים:
     - **GPT-5**
     - **text-embedding-3-small** (למודולי RAG)
   - **חשוב:** רשום את שמות הפריסה שלך - הם חייבים להתאים להגדרות `.env`
   - לאחר הפריסה, קבל את נקודת הקצה ומפתח ה-API מ-"Keys and Endpoint"
   - צור קובץ `.env` בתיקיית הפרויקט עם:
     
     **דוגמת קובץ `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**הנחיות לשמות פריסת מודל:**
- השתמש בשמות פשוטים ועקביים: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- שמות הפריסה חייבים להתאים בדיוק למה שהגדרת ב-`.env`
- טעות נפוצה: יצירת מודל בשם אחד אך הפניה לשם שונה בקוד

### בעיה: GPT-5 לא זמין באזור שנבחר

**פתרון:**
- בחר אזור עם גישה ל-GPT-5 (למשל eastus, swedencentral)
- בדוק זמינות: https://learn.microsoft.com/azure/ai-services/openai/concepts/models



### בעיה: קווטה לא מספיקה לפריסה

**פתרון:**
1. בקש הגדלת קווטה בפורטל Azure
2. או השתמש בקיבולת נמוכה יותר ב-`main.bicep` (למשל קיבולת: 10)

### בעיה: "Resource not found" בעת הרצה מקומית

**פתרון:**
1. אמת פריסה: `azd env get-values`
2. בדוק שנקודת הקצה והמפתח נכונים
3. ודא שקבוצת המשאבים קיימת בפורטל Azure

### בעיה: אימות נכשל

**פתרון:**
- אמת ש-`AZURE_OPENAI_API_KEY` מוגדר נכון
- פורמט המפתח צריך להיות מחרוזת הקסדצימלית באורך 32 תווים
- קבל מפתח חדש מפורטל Azure במידת הצורך

### פריסה נכשלה

**בעיה**: `azd provision` נכשל עם שגיאות קווטה או קיבולת

**פתרון**: 
1. נסה אזור שונה - ראה סעיף [שינוי אזורי Azure](../../../../01-introduction/infra) כיצד להגדיר אזורים
2. בדוק שלמנוי שלך יש קווטת Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### האפליקציה לא מתחברת

**בעיה**: אפליקציית Java מציגה שגיאות חיבור

**פתרון**:
1. אמת שמשתני הסביבה מיוצאים:
   
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

2. בדוק שפורמט נקודת הקצה נכון (צריך להיות `https://xxx.openai.azure.com`)
3. אמת שמפתח ה-API הוא המפתח הראשי או המשני מפורטל Azure

**בעיה**: 401 Unauthorized מ-Azure OpenAI

**פתרון**:
1. קבל מפתח API חדש מפורטל Azure → Keys and Endpoint
2. ייצא מחדש את משתנה הסביבה `AZURE_OPENAI_API_KEY`
3. ודא שפריסות המודל הושלמו (בדוק בפורטל Azure)

### בעיות ביצועים

**בעיה**: זמני תגובה איטיים

**פתרון**:
1. בדוק שימוש בטוקנים והגבלות בפורטל Azure במדדים
2. הגדל קיבולת TPM אם אתה מגיע למגבלות
3. שקול להשתמש ברמת מאמץ חשיבה גבוהה יותר (נמוך/בינוני/גבוה)

## עדכון תשתית

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

## המלצות אבטחה

1. **לעולם אל תתחייב מפתחות API** - השתמש במשתני סביבה
2. **השתמש בקבצי .env מקומית** - הוסף `.env` ל-`.gitignore`
3. **סובב מפתחות באופן קבוע** - צור מפתחות חדשים בפורטל Azure
4. **הגבל גישה** - השתמש ב-Azure RBAC לשליטה על מי יכול לגשת למשאבים
5. **נטר שימוש** - הגדר התראות עלויות בפורטל Azure

## משאבים נוספים

- [תיעוד שירות Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [תיעוד מודל GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [תיעוד Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [תיעוד Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [אינטגרציה רשמית של LangChain4j עם OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## תמיכה

לבעיות:
1. בדוק את [סעיף פתרון הבעיות](../../../../01-introduction/infra) למעלה
2. בדוק את מצב שירות Azure OpenAI בפורטל Azure
3. פתח נושא במאגר הקוד

## רישיון

ראה את קובץ [LICENSE](../../../../LICENSE) בשורש לפרטים.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**כתב ויתור**:  
מסמך זה תורגם באמצעות שירות תרגום מבוסס בינה מלאכותית [Co-op Translator](https://github.com/Azure/co-op-translator). למרות שאנו שואפים לדיוק, יש לקחת בחשבון כי תרגומים אוטומטיים עלולים להכיל שגיאות או אי-דיוקים. המסמך המקורי בשפת המקור שלו נחשב למקור הסמכותי. למידע קריטי מומלץ להשתמש בתרגום מקצועי על ידי אדם. אנו לא נושאים באחריות לכל אי-הבנה או פרשנות שגויה הנובעת משימוש בתרגום זה.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->