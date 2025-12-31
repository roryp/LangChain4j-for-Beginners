<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "f89f4c106d110e4943c055dd1a2f1dff",
  "translation_date": "2025-12-31T02:29:43+00:00",
  "source_file": "05-mcp/README.md",
  "language_code": "he"
}
-->
# מודול 05: פרוטוקול הקשר של המודל (MCP)

## תוכן העניינים

- [מה תלמדו](../../../05-mcp)
- [מה זה MCP?](../../../05-mcp)
- [איך MCP עובד](../../../05-mcp)
- [המודול האג'נטי](../../../05-mcp)
- [הרצת הדוגמאות](../../../05-mcp)
  - [דרישות מקדימות](../../../05-mcp)
- [התחלה מהירה](../../../05-mcp)
  - [פעולות קבצים (Stdio)](../../../05-mcp)
  - [סוכן המפקח](../../../05-mcp)
    - [הבנת הפלט](../../../05-mcp)
    - [הסבר על תכונות המודול האג'נטי](../../../05-mcp)
- [מושגי מפתח](../../../05-mcp)
- [כל הכבוד!](../../../05-mcp)
  - [מה הלאה?](../../../05-mcp)

## מה תלמדו

בנית כבר מערכת בינה שיחית, למדת הנחיות (prompts), עיגנת תשובות במסמכים ויצרת סוכנים עם כלים. אך כל אותם כלים נבנו בהתאמה אישית לאפליקציה שלך. מה אם תוכל להעניק ל-AI שלך גישה לאקוסיסטם סטנדרטי של כלים שכל אחד יכול ליצור ולשתף? במודול הזה תלמד איך לעשות זאת בעזרת Model Context Protocol (MCP) והמודול האג'נטי של LangChain4j. בתחילה נראה דוגמת קורא קבצים פשוטה מבוססת MCP ואז נראה כיצד היא משתלבת בקלות בעבודות זרימה מתקדמות של סוכנים באמצעות תבנית סוכן המפקח.

<img src="../../../translated_images/mcp-comparison.9129a881ecf10ff5.he.png" alt="השוואת MCP" width="800"/>

*לפני MCP: אינטגרציות מורכבות נקודה-לנקודה. אחרי MCP: פרוטוקול אחד, אפשרויות אין‑סופיות.*

MCP פותר בעיה יסודית בפיתוח מערכות בינה מלאכותית: כל אינטגרציה היא מותאמת אישית. רוצים לגשת ל-GitHub? קוד מותאם. רוצים לקרוא קבצים? קוד מותאם. רוצים לשאול מסד נתונים? קוד מותאם. ואף אחת מהאינטגרציות האלה לא עובדת עם יישומי AI אחרים.

MCP מסטנדרט זאת. שרת MCP חושף כלים עם תיאורים וסכמות פרמטרים ברורות. כל לקוח MCP יכול להתחבר, לגלות את הכלים הזמינים ולהשתמש בהם. בנו פעם, השתמשו בכל מקום.

<img src="../../../translated_images/mcp-architecture.b3156d787a4ceac9.he.png" alt="ארכיטקטורת MCP" width="800"/>

*ארכיטקטורת Model Context Protocol — גילוי וביצוע כלים סטנדרטיים*

## איך MCP עובד

**אדריכלות שרת-לקוח**

MCP משתמש במודל לקוח-שרת. השרתים מספקים כלים — קריאת קבצים, שאילתות לבסיסי נתונים, קריאות ל-API. הלקוחות (אפליקציית ה-AI שלך) מתחברים לשרתים ומשתמשים בכלים שלהם.

כדי להשתמש ב-MCP עם LangChain4j, הוסף את התלות הזו ב-Maven:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-mcp</artifactId>
    <version>${langchain4j.version}</version>
</dependency>
```

**גילוי כלים**

כאשר הלקוח שלך מתחבר לשרת MCP, הוא שואל "אילו כלים יש לכם?" השרת עונה ברשימת כלים זמינים, כל אחד עם תיאור וסכמת פרמטרים. הסוכן ה-AI שלך יכול אז להחליט אילו כלים להשתמש בהתבסס על בקשת המשתמש.

**מנגנוני העברה**

MCP תומך במנגנוני העברה שונים. מודול זה מדגים את ה-transport מסוג Stdio עבור תהליכים מקומיים:

<img src="../../../translated_images/transport-mechanisms.2791ba7ee93cf020.he.png" alt="מנגנוני העברה" width="800"/>

*מנגנוני העברה של MCP: HTTP לשרתים מרוחקים, Stdio עבור תהליכים מקומיים*

**Stdio** - [StdioTransportDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java)

לשימוש בתהליכים מקומיים. האפליקציה שלך משגרת שרת כתהליך משנה ומתקשרת דרך קלט/פלט סטנדרטי. שימושי לגישה למערכת הקבצים או לכלים שורת פקודה.

```java
McpTransport stdioTransport = new StdioMcpTransport.Builder()
    .command(List.of(
        npmCmd, "exec",
        "@modelcontextprotocol/server-filesystem@2025.12.18",
        resourcesDir
    ))
    .logEvents(false)
    .build();
```

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`StdioTransportDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/StdioTransportDemo.java) and ask:
> - "איך מנגנון ה-Stdio עובד ומתי כדאי להשתמש בו במקום HTTP?"
> - "איך LangChain4j מנהל את מחזור החיים של תהליכי שרת MCP שמושלכים?"
> - "מה המשמעויות הביטחוניות של מתן גישה של ה-AI למערכת הקבצים?"

## המודול האג'נטי

בעוד ש-MCP מספק כלים סטנדרטיים, מודול ה-agentic של LangChain4j מספק דרך דקלרטיבית לבניית סוכנים שמאורגנים באמצעות כלים אלה. האנוטציה `@Agent` ו-`AgenticServices` מאפשרות להגדיר התנהגות סוכן דרך ממשקים במקום קוד אימפרטיבי.

במודול זה תחקור את תבנית ה-Supervisor Agent — גישה אג'נטית מתקדמת שבה סוכן "מפקח" מחליט באופן דינמי איזה תת‑סוכנים להפעיל בהתבסס על בקשת המשתמש. נחבר בין שני המושגים על ידי מתן גישה לסוכן משנה אחד לכלי מערכת הקבצים מבוססי MCP.

כדי להשתמש במודול האג'נטי, הוסף את התלות הזו ב-Maven:

```xml
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-agentic</artifactId>
    <version>${langchain4j.mcp.version}</version>
</dependency>
```

> **⚠️ ניסיוני:** המודול `langchain4j-agentic` הוא **ניסיוני** ועשוי להשתנות. הדרך היציבה לבניית עזרי AI נשארת `langchain4j-core` עם כלים מותאמים אישית (מודול 04).

## הרצת הדוגמאות

### דרישות מקדימות

- Java 21+, Maven 3.9+
- Node.js 16+ ו-npm (לשרתי MCP)
- משתני סביבה מוגדרים בקובץ `.env` (מתיקיית השורש):
  - **ל-StdioTransportDemo:** `GITHUB_TOKEN` (Personal Access Token של GitHub)
  - **ל-SupervisorAgentDemo:** `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_DEPLOYMENT` (כפי במודולים 01-04)

> **הערה:** אם עדיין לא קבעת את משתני הסביבה שלך, ראה [Module 00 - Quick Start](../00-quick-start/README.md) לקבלת הוראות, או העתק את `.env.example` ל-`.env` בתיקיית השורש ומלא את הערכים שלך.

## התחלה מהירה

**שימוש ב-VS Code:** פשוט לחץ קליק ימני על כל קובץ דמו ב-Explorer ובחר **"Run Java"**, או השתמש בקונפיגורציות ההשקה בלוח Run and Debug (ודא שהוספת את הטוקן שלך לקובץ `.env` קודם).

**שימוש ב-Maven:** לחלופין, ניתן להריץ מתוך שורת הפקודה עם הדוגמאות למטה.

### פעולות קבצים (Stdio)

זה מדגים כלים מבוססי תהליכים משנה מקומיים.

**✅ אין דרישות מקדימות** - שרת MCP מושלך אוטומטית.

**שימוש ב-VS Code:** לחץ קליק ימני על `StdioTransportDemo.java` ובחר **"Run Java"**.

**שימוש ב-Maven:**

**Bash:**
```bash
export GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

**PowerShell:**
```powershell
$env:GITHUB_TOKEN=your_token_here
cd 05-mcp
mvn --% compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
```

האפליקציה משגרת אוטומטית שרת MCP לגישה למערכת הקבצים וקוראת קובץ מקומי. שים לב כיצד ניהול תהליך המשנה מטופל עבורך.

**פלט צפוי:**
```
Assistant response: The file provides an overview of LangChain4j, an open-source Java library
for integrating Large Language Models (LLMs) into Java applications...
```

### סוכן המפקח

<img src="../../../translated_images/agentic.cf84dcda226374e3.he.png" alt="מודול אג'נטי" width="800"/>


תבנית **סוכן המפקח** היא צורה **גמישה** של בינה אג'נטית. שלא כמו זרימות עבודה דטרמיניסטיות (רציפות, לולאה, מקבילית), מפקח משתמש ב-LLM כדי להחליט באופן אוטונומי אילו סוכנים להפעיל בהתבסס על בקשת המשתמש.

**שילוב המפקח עם MCP:** בדוגמה זו אנו נותנים ל-`FileAgent` גישה לכלי מערכת הקבצים של MCP באמצעות `toolProvider(mcpToolProvider)`. כאשר המשתמש מבקש "לקרוא ולנתח קובץ", המפקח מנתח את הבקשה ומייצר תוכנית ביצוע. אז הוא מנתב את הבקשה ל-`FileAgent`, שמשתמש בכלי `read_file` של MCP כדי לשחזר את התוכן. המפקח מעביר את התוכן הזה ל-`AnalysisAgent` לפרשנות, ואופציונלית מפעיל את `SummaryAgent` כדי לתמצת את התוצאות.

זה מדגים כיצד כלי MCP משתלבים בצורה חלקה בזרימות עבודה אג'נטיות — המפקח לא צריך לדעת *איך* הקבצים נקראים, רק ש-`FileAgent` יכול לעשות זאת. המפקח מתאים את עצמו באופן דינמי לסוגי בקשות שונים ומחזיר או את תגובת הסוכן האחרון או תמצית של כל הפעולות.

**שימוש בתסריטים להפעלה (מומלץ):**

תסריטי ההפעלה טוענים אוטומטית משתני סביבה מקובץ `.env` בתיקיית השורש:

**Bash:**
```bash
cd 05-mcp
chmod +x start.sh
./start.sh
```

**PowerShell:**
```powershell
cd 05-mcp
.\start.ps1
```

**שימוש ב-VS Code:** לחץ קליק ימני על `SupervisorAgentDemo.java` ובחר **"Run Java"** (ודא שקובץ `.env` שלך מוגדר).

**איך המפקח עובד:**

```java
// הגדר סוכנים מרובים עם יכולות ספציפיות
FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
        .chatModel(model)
        .toolProvider(mcpToolProvider)  // בעל כלי MCP עבור פעולות קבצים
        .build();

AnalysisAgent analysisAgent = AgenticServices.agentBuilder(AnalysisAgent.class)
        .chatModel(model)
        .build();

SummaryAgent summaryAgent = AgenticServices.agentBuilder(SummaryAgent.class)
        .chatModel(model)
        .build();

// צור מפקח שמתזמר את הסוכנים הללו
SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
        .chatModel(model)  // המודל "planner"
        .subAgents(fileAgent, analysisAgent, summaryAgent)
        .responseStrategy(SupervisorResponseStrategy.SUMMARY)
        .build();

// המפקח מחליט באופן אוטונומי אילו סוכנים להפעיל
// פשוט העבר בקשת שפה טבעית - ה-LLM מתכנן את הביצוע
String response = supervisor.invoke("Read the file at /path/file.txt and analyze it");
```

ראה [SupervisorAgentDemo.java](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) למימוש המלא.

> **🤖 Try with [GitHub Copilot](https://github.com/features/copilot) Chat:** Open [`SupervisorAgentDemo.java`](../../../05-mcp/src/main/java/com/example/langchain4j/mcp/SupervisorAgentDemo.java) and ask:
> - "איך המפקח מחליט אילו סוכנים להפעיל?"
> - "מה ההבדל בין דפוס Supervisor לדפוס זרימת עבודה רציפה?"
> - "איך אפשר להתאים את התנהגות התכנון של המפקח?"

#### הבנת הפלט

כאשר תריץ את הדמו, תראה סיכום מובנה של אופן בארגונו של המפקח את הסוכנים השונים. הנה מה שפירוש כל מדור:

```
======================================================================
  SUPERVISOR AGENT DEMO
======================================================================

This demo shows how a Supervisor Agent orchestrates multiple specialized agents.
The Supervisor uses an LLM to decide which agent to call based on the task.
```

**הכותרת** מציגה את הדמו ומסבירה את המושג המרכזי: המפקח משתמש ב-LLM (ולא בחוקים מוצפנים בקוד) כדי להחליט אילו סוכנים לקרוא.

```
--- AVAILABLE AGENTS -------------------------------------------------
  [FILE]     FileAgent     - Reads files using MCP filesystem tools
  [ANALYZE]  AnalysisAgent - Analyzes content for structure, tone, and themes
  [SUMMARY]  SummaryAgent  - Creates concise summaries of content
```

**סוכנים זמינים** מציג את שלושת הסוכנים המיוחדים שהמפקח יכול לבחור מהם. לכל סוכן יש יכולת ספציפית:
- **FileAgent** יכול לקרוא קבצים באמצעות כלי MCP (יכולת חיצונית)
- **AnalysisAgent** מנתח תוכן (יכולת מבוססת LLM בלבד)
- **SummaryAgent** יוצר סיכומים (יכולת מבוססת LLM בלבד)

```
--- USER REQUEST -----------------------------------------------------
  "Read the file at .../file.txt and analyze what it's about"
```

**בקשת המשתמש** מציגה מה נדרש. המפקח חייב לפרש זאת ולהחליט אילו סוכנים להפעיל.

```
--- SUPERVISOR ORCHESTRATION -----------------------------------------
  The Supervisor will now decide which agents to invoke and in what order...

  +-- STEP 1: Supervisor chose -> FileAgent (reading file via MCP)
  |
  |   Input: .../file.txt
  |
  |   Result: LangChain4j is an open-source Java library designed to simplify...
  +-- [OK] FileAgent (reading file via MCP) completed

  +-- STEP 2: Supervisor chose -> AnalysisAgent (analyzing content)
  |
  |   Input: LangChain4j is an open-source Java library...
  |
  |   Result: Structure: The content is organized into clear paragraphs that int...
  +-- [OK] AnalysisAgent (analyzing content) completed
```

**אורקסטרציה של המפקח** היא המקום שבו הקסם מתרחש. שים לב כיצד:
1. המפקח **בחר ב-FileAgent ראשון** כי הבקשה הזכירה "read the file"
2. FileAgent השתמש בכלי `read_file` של MCP כדי לשחזר את תוכן הקובץ
3. המפקח אז **בחר ב-AnalysisAgent** והעביר אליו את תוכן הקובץ
4. AnalysisAgent ניתח את המבנה, הטון והנושאים

שים לב שהמפקח קיבל החלטות אלה **באופן אוטונומי** בהתבסס על בקשת המשתמש — ללא זרימת עבודה מוצפנת בקוד!

**תגובה סופית** היא התשובה המסונתזת של המפקח, המשלבת פלטים מכל הסוכנים שהפעיל. הדוגמה מדפיסה את ה-scope האג'נטי ומראה את הסיכום ותוצאות הניתוח שנשמרו על ידי כל סוכן.

```
--- FINAL RESPONSE ---------------------------------------------------
I read the contents of the file and analyzed its structure, tone, and key themes.
The file introduces LangChain4j as an open-source Java library for integrating
large language models...

--- AGENTIC SCOPE (Shared Memory) ------------------------------------
  Agents store their results in a shared scope for other agents to use:
  * summary: LangChain4j is an open-source Java library...
  * analysis: Structure: The content is organized into clear paragraphs that in...
```

### הסבר על תכונות המודול האג'נטי

הדוגמה מראה מספר תכונות מתקדמות של המודול האג'נטי. בואו נבחן מקרוב את Agentic Scope ואת Agent Listeners.

**Agentic Scope** מראה את הזיכרון המשותף שבו הסוכנים שמרו את תוצאותיהם באמצעות `@Agent(outputKey="...")`. זה מאפשר:
- לסוכנים מאוחרים לגשת לפלטים של סוכנים קודמים
- למפקח לסנתז תשובה סופית
- לך לבדוק מה כל סוכן הפיק

```java
ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
AgenticScope scope = result.agenticScope();
String story = scope.readState("story");
List<AgentInvocation> history = scope.agentInvocations("analysisAgent");
```

**Agent Listeners** מאפשרים ניטור וניפוי שגיאות של ביצועי סוכנים. הפלט שלב-אחר-שלב שאתה רואה בדמו מגיע מ-AgentListener שמתחבר לכל קריאת סוכן:
- **beforeAgentInvocation** - נקרא כאשר המפקח בוחר סוכן, ומאפשר לראות איזה סוכן נבחר ומדוע
- **afterAgentInvocation** - נקרא כאשר סוכן מסיים, ומציג את תוצאתו
- **inheritedBySubagents** - כאשר true, המאזין עוקב אחרי כל הסוכנים בהיררכיה

```java
AgentListener monitor = new AgentListener() {
    private int step = 0;
    
    @Override
    public void beforeAgentInvocation(AgentRequest request) {
        step++;
        System.out.println("  +-- STEP " + step + ": " + request.agentName());
    }
    
    @Override
    public void afterAgentInvocation(AgentResponse response) {
        System.out.println("  +-- [OK] " + response.agentName() + " completed");
    }
    
    @Override
    public boolean inheritedBySubagents() {
        return true; // להפיץ לכל תתי-הסוכנים
    }
};
```

מעבר לתבנית המפקח, המודול `langchain4j-agentic` מספק מספר דפוסי זרימת עבודה ותכונות עוצמתיות:

| תבנית | תיאור | מקרה שימוש |
|---------|-------------|----------|
| **רציף (Sequential)** | להריץ סוכנים לפי סדר, כאשר הפלט זורם לסוכן הבא | צינורות: מחקר → ניתוח → דוח |
| **מקביל (Parallel)** | להריץ סוכנים במקביל | משימות בלתי תלויות: מזג אוויר + חדשות + מניות |
| **לולאה (Loop)** | לחזור עד שמתקיים תנאי | ניקוד איכות: לשפר עד שהציון ≥ 0.8 |
| **תנאי (Conditional)** | לנתב על פי תנאים | סיווג → ניתוב לסוכן מומחה |
| **אדם בתווך (Human-in-the-Loop)** | להוסיף נקודות בדיקה אנושיות | זרימות אישור, סקירת תוכן |

## מושגי מפתח

**MCP** אידיאלי כשהרצון שלך הוא לנצל אקוסיסטם של כלים קיים, לבנות כלים שיכולים לשותף על ידי מספר אפליקציות, לשלב שירותים של צד שלישי בפרוטוקולים סטנדרטיים, או להחליף מימושי כלים בלי לשנות קוד.

**המודול האג'נטי** עובד הכי טוב כשאתה רוצה הגדרות סוכנים דקלרטיביות עם אנוטציות `@Agent`, צריך אורקסטרציה של זרימות עבודה (רציף, לולאה, מקביל), מעדיף עיצוב סוכנים מבוסס ממשקים על פני קוד אימפרטיבי, או משלב מספר סוכנים שמשתפים פלטים באמצעות `outputKey`.

**תבנית סוכן המפקח** בולטת כאשר זרימת העבודה אינה צפויה מראש ואתה רוצה שה-LLM יחליט, כשיש לך מספר סוכנים מתמחים שזקוקים לאורקסטרציה דינמית, כשבונים מערכות שיח שמנתבות ליכולות שונות, או כשאתה רוצה את ההתנהגות הגמישה והמתאימה ביותר של סוכן.

## כל הכבוד!

סיימת את הקורס LangChain4j for Beginners. למדת:

- איך לבנות בינה שיחית עם זיכרון (מודול 01)
- דפוסי הנחיה (Prompt engineering) למשימות שונות (מודול 02)
- עיגון תשובות במסמכים עם RAG (מודול 03)
- יצירת סוכני AI בסיסיים (עוזרים) עם כלים מותאמים אישית (מודול 04)
- שילוב כלים סטנדרטיים עם LangChain4j MCP ו-Agentic modules (מודול 05)

### מה הלאה?

לאחר השלמת המודולים, עיין ב-[מדריך הבדיקות](../docs/TESTING.md) כדי לראות את מושגי הבדיקה של LangChain4j בפעולה.

**משאבים רשמיים:**
- [תיעוד LangChain4j](https://docs.langchain4j.dev/) - מדריכים מקיפים ותיעוד ה-API
- [GitHub של LangChain4j](https://github.com/langchain4j/langchain4j) - קוד מקור ודוגמאות
- [מדריכים של LangChain4j](https://docs.langchain4j.dev/tutorials/) - מדריכים שלב אחר שלב למקרי שימוש שונים

תודה שסיימת את הקורס!

---

**ניווט:** [← הקודם: מודול 04 - כלים](../04-tools/README.md) | [חזרה לדף הראשי](../README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
הצהרת אחריות:
מסמך זה תורגם באמצעות שירות תרגום מבוסס בינה מלאכותית [Co-op Translator](https://github.com/Azure/co-op-translator). למרות שאנו שואפים לדיוק, שימו לב שתרגומים אוטומטיים עלולים להכיל שגיאות או אי-דיוקים. יש להתייחס למסמך המקורי בשפתו כמקור הסמכות. למידע קריטי, מומלץ תרגום מקצועי על ידי מתרגם אנושי. איננו אחראים לכל אי-הבנה או לפרשנות שגויה הנובעת משימוש בתרגום זה.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->