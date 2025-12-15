<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "377b3e3e6f8d02965bf0fbbc9ccb45c5",
  "translation_date": "2025-12-13T15:04:49+00:00",
  "source_file": "00-quick-start/README.md",
  "language_code": "he"
}
-->
# מודול 00: התחלה מהירה

## תוכן העניינים

- [הקדמה](../../../00-quick-start)
- [מה זה LangChain4j?](../../../00-quick-start)
- [תלויות LangChain4j](../../../00-quick-start)
- [דרישות מוקדמות](../../../00-quick-start)
- [הגדרה](../../../00-quick-start)
  - [1. קבל את אסימון GitHub שלך](../../../00-quick-start)
  - [2. הגדר את האסימון שלך](../../../00-quick-start)
- [הרץ את הדוגמאות](../../../00-quick-start)
  - [1. שיחה בסיסית](../../../00-quick-start)
  - [2. דפוסי פרומפט](../../../00-quick-start)
  - [3. קריאת פונקציות](../../../00-quick-start)
  - [4. שאלות ותשובות על מסמכים (RAG)](../../../00-quick-start)
- [מה כל דוגמה מראה](../../../00-quick-start)
- [השלבים הבאים](../../../00-quick-start)
- [פתרון בעיות](../../../00-quick-start)

## הקדמה

ההתחלה המהירה הזו מיועדת להפעיל אותך עם LangChain4j במהירות האפשרית. היא מכסה את היסודות המוחלטים של בניית יישומי AI עם LangChain4j ודגמי GitHub. במודולים הבאים תשתמש ב-Azure OpenAI עם LangChain4j כדי לבנות יישומים מתקדמים יותר.

## מה זה LangChain4j?

LangChain4j היא ספריית Java שמפשטת את בניית יישומי AI. במקום להתמודד עם לקוחות HTTP וניתוח JSON, אתה עובד עם ממשקי API נקיים של Java.

המונח "chain" ב-LangChain מתייחס לקישור בין רכיבים מרובים - ייתכן שתקשר פרומפט למודל, למנתח, או תקשר קריאות AI מרובות שבהן פלט אחד מזין את הקלט הבא. התחלה מהירה זו מתמקדת ביסודות לפני חקירת שרשראות מורכבות יותר.

<img src="../../../translated_images/langchain-concept.ad1fe6cf063515e1e961a13c73d45cfa305fd03d81bd11e5d34d0e36ed28a44d.he.png" alt="LangChain4j Chaining Concept" width="800"/>

*שרשור רכיבים ב-LangChain4j - אבני בניין שמתחברות ליצירת זרימות עבודה חזקות של AI*

נשתמש בשלושה רכיבים מרכזיים:

**ChatLanguageModel** - הממשק לאינטראקציות עם מודל AI. קוראים ל-`model.chat("prompt")` ומקבלים מחרוזת תגובה. אנו משתמשים ב-`OpenAiOfficialChatModel` שעובד עם נקודות קצה תואמות OpenAI כמו דגמי GitHub.

**AiServices** - יוצר ממשקי שירות AI בטוחים טיפוסית. מגדירים שיטות, מסמנים אותן עם `@Tool`, ו-LangChain4j מטפל בתזמור. ה-AI קורא אוטומטית לשיטות ה-Java שלך כשצריך.

**MessageWindowChatMemory** - שומר היסטוריית שיחה. בלעדיו, כל בקשה היא עצמאית. איתו, ה-AI זוכר הודעות קודמות ושומר על הקשר לאורך מספר סבבים.

<img src="../../../translated_images/architecture.eedc993a1c57683951f20244f652fc7a9853f571eea835bc2b2828cf0dbf62d0.he.png" alt="LangChain4j Architecture" width="800"/>

*ארכיטקטורת LangChain4j - רכיבים מרכזיים שעובדים יחד כדי להפעיל את יישומי ה-AI שלך*

## תלויות LangChain4j

ההתחלה המהירה הזו משתמשת בשתי תלויות Maven בקובץ [`pom.xml`](../../../00-quick-start/pom.xml):

```xml
<!-- Core LangChain4j library -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>

<!-- OpenAI integration (works with GitHub Models) -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-official</artifactId> <!-- Inherited from BOM in root pom.xml -->
</dependency>
```

מודול `langchain4j-open-ai-official` מספק את המחלקה `OpenAiOfficialChatModel` שמתחברת ל-APIs תואמים OpenAI. דגמי GitHub משתמשים באותו פורמט API, כך שאין צורך במתאם מיוחד - פשוט הפנה את כתובת הבסיס ל-`https://models.github.ai/inference`.

## דרישות מוקדמות

**משתמש במיכל פיתוח?** Java ו-Maven מותקנים כבר. אתה רק צריך אסימון גישה אישי ל-GitHub.

**פיתוח מקומי:**
- Java 21+, Maven 3.9+
- אסימון גישה אישי ל-GitHub (הוראות למטה)

> **הערה:** מודול זה משתמש ב-`gpt-4.1-nano` מדגמי GitHub. אל תשנה את שם המודל בקוד - הוא מוגדר לעבוד עם הדגמים הזמינים של GitHub.

## הגדרה

### 1. קבל את אסימון GitHub שלך

1. עבור ל-[הגדרות GitHub → אסימוני גישה אישיים](https://github.com/settings/personal-access-tokens)
2. לחץ על "Generate new token"
3. הגדר שם תיאורי (למשל, "LangChain4j Demo")
4. הגדר תוקף (מומלץ 7 ימים)
5. תחת "הרשאות חשבון", מצא "Models" והגדר ל-"Read-only"
6. לחץ על "Generate token"
7. העתק ושמור את האסימון שלך - לא תראה אותו שוב

### 2. הגדר את האסימון שלך

**אפשרות 1: שימוש ב-VS Code (מומלץ)**

אם אתה משתמש ב-VS Code, הוסף את האסימון שלך לקובץ `.env` בשורש הפרויקט:

אם קובץ `.env` לא קיים, העתק את `.env.example` ל-`.env` או צור קובץ `.env` חדש בשורש הפרויקט.

**דוגמה לקובץ `.env`:**
```bash
# בקובץ /workspaces/LangChain4j-for-Beginners/.env
GITHUB_TOKEN=your_token_here
```

אז תוכל פשוט ללחוץ קליק ימני על כל קובץ דמו (למשל, `BasicChatDemo.java`) בסייר הקבצים ולבחור **"Run Java"** או להשתמש בקונפיגורציות ההפעלה מפאנל Run and Debug.

**אפשרות 2: שימוש בטרמינל**

הגדר את האסימון כמשתנה סביבה:

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
```

## הרץ את הדוגמאות

**שימוש ב-VS Code:** פשוט לחץ קליק ימני על כל קובץ דמו בסייר הקבצים ובחר **"Run Java"**, או השתמש בקונפיגורציות ההפעלה מפאנל Run and Debug (ודא שהוספת את האסימון שלך לקובץ `.env` קודם).

**שימוש ב-Maven:** לחלופין, תוכל להריץ מהשורת פקודה:

### 1. שיחה בסיסית

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

### 2. דפוסי פרומפט

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.PromptEngineeringDemo
```

מציג פרומפטים מסוג zero-shot, few-shot, chain-of-thought, ופרומפטים מבוססי תפקיד.

### 3. קריאת פונקציות

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.ToolIntegrationDemo
```

ה-AI קורא אוטומטית לשיטות ה-Java שלך כשצריך.

### 4. שאלות ותשובות על מסמכים (RAG)

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.SimpleReaderDemo
```

שאל שאלות על תוכן ב-`document.txt`.

## מה כל דוגמה מראה

**שיחה בסיסית** - [BasicChatDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java)

התחל כאן כדי לראות את LangChain4j בפשטותו. תיצור `OpenAiOfficialChatModel`, תשלח פרומפט עם `.chat()`, ותקבל תגובה. זה מדגים את הבסיס: איך לאתחל מודלים עם נקודות קצה ומפתחות API מותאמים. ברגע שתבין את הדפוס הזה, כל השאר נבנה עליו.

```java
ChatLanguageModel model = OpenAiOfficialChatModel.builder()
    .baseUrl("https://models.github.ai/inference")
    .apiKey(System.getenv("GITHUB_TOKEN"))
    .modelName("gpt-4.1-nano")
    .build();

String response = model.chat("What is LangChain4j?");
System.out.println(response);
```

> **🤖 נסה עם [GitHub Copilot](https://github.com/features/copilot) Chat:** פתח את [`BasicChatDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/BasicChatDemo.java) ושאל:
> - "איך אעבור מדגמי GitHub ל-Azure OpenAI בקוד הזה?"
> - "אילו פרמטרים נוספים אני יכול להגדיר ב-OpenAiOfficialChatModel.builder()?"
> - "איך מוסיפים תגובות בזרם במקום להמתין לתגובה מלאה?"

**הנדסת פרומפטים** - [PromptEngineeringDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java)

עכשיו כשאתה יודע איך לדבר עם מודל, בוא נחקור מה אתה אומר לו. הדמו הזה משתמש באותה הגדרת מודל אך מציג ארבעה דפוסי פרומפט שונים. נסה פרומפטים מסוג zero-shot להוראות ישירות, few-shot שלומדים מדוגמאות, chain-of-thought שמגלים שלבי חשיבה, ופרומפטים מבוססי תפקיד שמגדירים הקשר. תראה איך אותו מודל נותן תוצאות שונות באופן דרמטי בהתאם לאופן שבו אתה מנסח את הבקשה.

```java
PromptTemplate template = PromptTemplate.from(
    "What's the best time to visit {{destination}} for {{activity}}?"
);

Prompt prompt = template.apply(Map.of(
    "destination", "Paris",
    "activity", "sightseeing"
));

String response = model.chat(prompt.text());
```

> **🤖 נסה עם [GitHub Copilot](https://github.com/features/copilot) Chat:** פתח את [`PromptEngineeringDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/PromptEngineeringDemo.java) ושאל:
> - "מה ההבדל בין פרומפט zero-shot ל-few-shot ומתי כדאי להשתמש בכל אחד?"
> - "איך פרמטר הטמפרטורה משפיע על תגובות המודל?"
> - "אילו טכניקות קיימות למניעת התקפות הזרקת פרומפט בפרודקשן?"
> - "איך ליצור אובייקטים של PromptTemplate לשימוש חוזר בדפוסים נפוצים?"

**אינטגרציית כלים** - [ToolIntegrationDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java)

כאן LangChain4j הופך לעוצמתי. תשתמש ב-`AiServices` כדי ליצור עוזר AI שיכול לקרוא לשיטות ה-Java שלך. פשוט סמן שיטות עם `@Tool("description")` ו-LangChain4j מטפל בשאר - ה-AI מחליט אוטומטית מתי להשתמש בכל כלי לפי מה שהמשתמש מבקש. זה מדגים קריאת פונקציות, טכניקה מרכזית לבניית AI שיכול לבצע פעולות, לא רק לענות על שאלות.

```java
@Tool("Performs addition of two numeric values")
public double add(double a, double b) {
    return a + b;
}

MathAssistant assistant = AiServices.create(MathAssistant.class, model);
String response = assistant.chat("What is 25 plus 17?");
```

> **🤖 נסה עם [GitHub Copilot](https://github.com/features/copilot) Chat:** פתח את [`ToolIntegrationDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/ToolIntegrationDemo.java) ושאל:
> - "איך עובד התג @Tool ומה LangChain4j עושה איתו מאחורי הקלעים?"
> - "האם ה-AI יכול לקרוא לכלים מרובים ברצף כדי לפתור בעיות מורכבות?"
> - "מה קורה אם כלי זורק חריגה - איך כדאי לטפל בשגיאות?"
> - "איך אשלב API אמיתי במקום הדוגמה של המחשבון?"

**שאלות ותשובות על מסמכים (RAG)** - [SimpleReaderDemo.java](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java)

כאן תראה את הבסיס של RAG (יצירה משולבת עם אחזור). במקום להסתמך על נתוני האימון של המודל, אתה טוען תוכן מ-[`document.txt`](../../../00-quick-start/document.txt) ומשלב אותו בפרומפט. ה-AI עונה בהתבסס על המסמך שלך, לא על הידע הכללי שלו. זה הצעד הראשון לבניית מערכות שיכולות לעבוד עם הנתונים שלך.

```java
Document document = FileSystemDocumentLoader.loadDocument("document.txt");
String content = document.text();

String prompt = "Based on this document: " + content + 
                "\nQuestion: What is the main topic?";
String response = model.chat(prompt);
```

> **הערה:** גישה פשוטה זו טוענת את כל המסמך לפרומפט. עבור קבצים גדולים (>10KB), תחרוג ממגבלות ההקשר. מודול 03 מכסה חלוקה וחיפוש וקטורי למערכות RAG בפרודקשן.

> **🤖 נסה עם [GitHub Copilot](https://github.com/features/copilot) Chat:** פתח את [`SimpleReaderDemo.java`](../../../00-quick-start/src/main/java/com/example/langchain4j/quickstart/SimpleReaderDemo.java) ושאל:
> - "איך RAG מונע הזיות AI לעומת שימוש בנתוני האימון של המודל?"
> - "מה ההבדל בין הגישה הפשוטה הזו לשימוש בהטמעות וקטוריות לאחזור?"
> - "איך אוכל להרחיב את זה לטיפול במסמכים מרובים או בסיסי ידע גדולים יותר?"
> - "מהן השיטות הטובות ביותר למבנה הפרומפט כדי להבטיח שה-AI ישתמש רק בהקשר שסופק?"

## איתור באגים

הדוגמאות כוללות `.logRequests(true)` ו-`.logResponses(true)` כדי להציג קריאות API בקונסול. זה עוזר לפתור שגיאות אימות, מגבלות קצב, או תגובות בלתי צפויות. הסר דגלים אלה בפרודקשן כדי להפחית רעש בלוג.

## השלבים הבאים

**מודול הבא:** [01-introduction - התחלה עם LangChain4j ו-gpt-5 על Azure](../01-introduction/README.md)

---

**ניווט:** [← חזרה לעמוד הראשי](../README.md) | [הבא: מודול 01 - הקדמה →](../01-introduction/README.md)

---

## פתרון בעיות

### בניית Maven בפעם הראשונה

**בעיה:** `mvn clean compile` או `mvn package` הראשוני לוקח זמן רב (10-15 דקות)

**סיבה:** Maven צריך להוריד את כל התלויות של הפרויקט (Spring Boot, ספריות LangChain4j, SDKs של Azure וכו') בבנייה הראשונה.

**פתרון:** זה התנהגות רגילה. בניות הבאות יהיו מהירות יותר כי התלויות נשמרות במטמון מקומי. זמן ההורדה תלוי במהירות הרשת שלך.

### תחביר פקודות Maven ב-PowerShell

**בעיה:** פקודות Maven נכשלות עם שגיאה `Unknown lifecycle phase ".mainClass=..."`

**סיבה:** PowerShell מפרש את `=` כמפעיל הקצאת משתנה, מה ששובר את תחביר המאפיינים של Maven

**פתרון:** השתמש במפעיל עצירת ניתוח `--%` לפני פקודת Maven:

**PowerShell:**
```powershell
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

**Bash:**
```bash
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.quickstart.BasicChatDemo
```

המפעיל `--%` אומר ל-PowerShell להעביר את כל הפרמטרים הנותרים כפי שהם ל-Maven ללא פירוש.

### הצגת אימוג'ים ב-Windows PowerShell

**בעיה:** תגובות AI מציגות תווים לא קריאים (למשל `????` או `â??`) במקום אימוג'ים ב-PowerShell

**סיבה:** הקידוד ברירת המחדל של PowerShell אינו תומך באימוג'ים UTF-8

**פתרון:** הרץ את הפקודה הזו לפני הפעלת יישומי Java:
```cmd
chcp 65001
```

זה מאלץ קידוד UTF-8 בטרמינל. לחלופין, השתמש ב-Windows Terminal שיש לו תמיכה טובה יותר ב-Unicode.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**כתב ויתור**:  
מסמך זה תורגם באמצעות שירות תרגום מבוסס בינה מלאכותית [Co-op Translator](https://github.com/Azure/co-op-translator). למרות שאנו שואפים לדיוק, יש לקחת בחשבון כי תרגומים אוטומטיים עלולים להכיל שגיאות או אי-דיוקים. המסמך המקורי בשפת המקור שלו נחשב למקור הסמכותי. למידע קריטי מומלץ להשתמש בתרגום מקצועי על ידי אדם. אנו לא נושאים באחריות לכל אי-הבנה או פרשנות שגויה הנובעת משימוש בתרגום זה.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->