<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T02:36:27+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "he"
}
-->
# LangChain4j מילון

## תוכן עניינים

- [מושגי ליבה](../../../docs)
- [רכיבי LangChain4j](../../../docs)
- [מושגי AI/ML](../../../docs)
- [הנדסת פרומפטים](../../../docs)
- [RAG (הפקה משופרת באמצעות שליפה)](../../../docs)
- [סוכנים וכלים](../../../docs)
- [פרוטוקול הקשר למודל (MCP)](../../../docs)
- [שירותי Azure](../../../docs)
- [בדיקות ופיתוח](../../../docs)

התייחסות מהירה למונחים ולמושגים המופיעים בקורס.

## מושגי ליבה

**סוכן בינה מלאכותית** - מערכת המשתמשת בבינה מלאכותית כדי להסק מסקנות ולפעול באופן אוטונומי. [מודול 04](../04-tools/README.md)

**שרשרת (Chain)** - רצף פעולות שבהן הפלט משמש לקלט של השלב הבא.

**Chunking** - פירוק מסמכים לחתיכות קטנות יותר. בדרך כלל: 300-500 טוקנים עם חפיפה. [מודול 03](../03-rag/README.md)

**חלון הקשר** - מספר הטוקנים המקסימלי שמודל יכול לעבד. GPT-5: 400K טוקנים.

**הטמעות (Embeddings)** - וקטורים מספריים המייצגים את משמעות הטקסט. [מודול 03](../03-rag/README.md)

**קריאת פונקציות** - המודל מייצר בקשות מבניות לקריאה לפונקציות חיצוניות. [מודול 04](../04-tools/README.md)

**הלוצינציה** - כשמודלים מייצרים מידע שגוי אך נשמע סביר.

**פרומפט** - קלט טקסטואלי למודל שפה. [מודול 02](../02-prompt-engineering/README.md)

**חיפוש סמנטי** - חיפוש לפי משמעות באמצעות הטמעות (embeddings), לא לפי מילות מפתח. [מודול 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ללא זיכרון. Stateful: שומר היסטוריית שיחה. [מודול 01](../01-introduction/README.md)

**טוקנים** - יחידות טקסט בסיסיות שהמודלים מעבדים. משפיע על עלויות ומגבלות. [מודול 01](../01-introduction/README.md)

**Tool Chaining** - ביצוע כלים ברצף שבו הפלט מניע את הקריאה הבאה. [מודול 04](../04-tools/README.md)

## רכיבי LangChain4j

**AiServices** - יוצר ממשקי שירותי AI בטוחים מבחינת טיפוס.

**OpenAiOfficialChatModel** - לקוח מאוחד עבור דגמי OpenAI ו-Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - יוצר embeddings באמצעות לקוח OpenAI Official (תומך הן ב-OpenAI והן ב-Azure OpenAI).

**ChatModel** - ממשק ליבה עבור מודלי שפה.

**ChatMemory** - שומר היסטוריית שיחה.

**ContentRetriever** - מוצא קטעי מסמכים רלוונטיים עבור RAG.

**DocumentSplitter** - מפצל מסמכים לקטעים.

**EmbeddingModel** - ממיר טקסט לווקטורים מספריים.

**EmbeddingStore** - מאחסן ומשחזר embeddings.

**MessageWindowChatMemory** - שומר חלון זז של ההודעות האחרונות.

**PromptTemplate** - יוצר פרומפטים לשימוש חוזר עם מילויי מקום של `{{variable}}`.

**TextSegment** - קטע טקסט עם מטא-נתונים. משמש ב-RAG.

**ToolExecutionRequest** - מייצג בקשה לביצוע כלי.

**UserMessage / AiMessage / SystemMessage** - סוגי הודעות בשיחה.

## מושגי AI/ML

**Few-Shot Learning** - מתן דוגמאות בפרומפטים. [מודול 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - מודלי AI מאומנים על כמות גדולה של טקסט.

**Reasoning Effort** - פרמטר של GPT-5 שקובע את עומק החשיבה. [מודול 02](../02-prompt-engineering/README.md)

**Temperature** - שולטת ברנדומליות הפלט. נמוכה=דטרמיניסטית, גבוהה=יצירתית.

**Vector Database** - מסד נתונים וקטורי מיוחד עבור embeddings. [מודול 03](../03-rag/README.md)

**Zero-Shot Learning** - ביצוע משימות ללא דוגמאות. [מודול 02](../02-prompt-engineering/README.md)

## הנדסת פרומפטים - [מודול 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - היסק צעד-אחר-צעד לשיפור הדיוק.

**Constrained Output** - כפיית פורמט או מבנה ספציפי.

**High Eagerness** - דפוס של GPT-5 להיסק מעמיק ומקיף.

**Low Eagerness** - דפוס של GPT-5 לתשובות מהירות.

**Multi-Turn Conversation** - שמירת הקשר לאורך חילופי ההודעות.

**Role-Based Prompting** - קביעת אישיות המודל דרך הודעות מערכת.

**Self-Reflection** - המודל מעריך ומשפר את הפלט שלו.

**Structured Analysis** - מסגרת הערכה קבועה.

**Task Execution Pattern** - תכנון → ביצוע → סיכום.

## RAG (הפקה משופרת באמצעות שליפה) - [מודול 03](../03-rag/README.md)

**Document Processing Pipeline** - טען → חלק → הטמע → אחסן.

**In-Memory Embedding Store** - אחסון לא-קבוע למטרות בדיקה.

**RAG** - משלב שליפה עם הפקה כדי לעגן תשובות.

**Similarity Score** - מדד (0-1) של דמיון סמנטי.

**Source Reference** - מטא-נתונים אודות התוכן שנשלף.

## סוכנים וכלים - [מודול 04](../04-tools/README.md)

**@Tool Annotation** - מסמן מתודות Java ככלים הניתנים לקריאה ע"י ה-AI.

**ReAct Pattern** - הסק → פעל → צפה → חזור.

**Session Management** - הקשרים נפרדים עבור משתמשים שונים.

**Tool** - פונקציה שסוכן AI יכול לקרוא לה.

**Tool Description** - תיעוד מטרת הכלי ופרמטריו.

## פרוטוקול הקשר למודל (MCP) - [מודול 05](../05-mcp/README.md)

**MCP** - תקן לחיבור אפליקציות AI לכלים חיצוניים.

**MCP Client** - אפליקציה שמתחברת לשרתי MCP.

**MCP Server** - שירות שמחשף כלים דרך MCP.

**Stdio Transport** - שרת כתהליך משנה דרך stdin/stdout.

**Tool Discovery** - הלקוח שואל את השרת לגבי כלים זמינים.

## שירותי Azure - [מודול 01](../01-introduction/README.md)

**Azure AI Search** - חיפוש בענן עם יכולות וקטוריות. [מודול 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - כלי לפריסת משאבי Azure.

**Azure OpenAI** - שירות ה-AI הארגוני של Microsoft.

**Bicep** - שפת Infrastructure-as-Code של Azure. [מדריך תשתיות](../01-introduction/infra/README.md)

**Deployment Name** - שם לפריסת מודל ב-Azure.

**GPT-5** - המודל העדכני של OpenAI עם בקרת היסק. [מודול 02](../02-prompt-engineering/README.md)

## בדיקות ופיתוח - [מדריך בדיקות](TESTING.md)

**Dev Container** - סביבת פיתוח מבודדת באמצעות קונטיינרים. [תצורה](../../../.devcontainer/devcontainer.json)

**GitHub Models** - סביבת משחק למודלי AI חינמית. [מודול 00](../00-quick-start/README.md)

**In-Memory Testing** - בדיקות עם אחסון בזיכרון.

**Integration Testing** - בדיקות עם תשתית אמיתית.

**Maven** - כלי אוטומציה לבניית פרויקטי Java.

**Mockito** - מסגרת ליצירת mocks ב-Java.

**Spring Boot** - מסגרת לאפליקציות Java. [מודול 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
הצהרת אי-אחריות:
מסמך זה תורגם באמצעות שירות תרגום מבוסס בינה מלאכותית [Co-op Translator](https://github.com/Azure/co-op-translator). למרות שאנו משתדלים לדייק, יש לקחת בחשבון כי תרגומים אוטומטיים עלולים להכיל שגיאות או אי-דיוקים. יש לראות במסמך המקורי בשפתו כמקור הסמכות. עבור מידע קריטי מומלץ להיעזר בתרגום מקצועי על ידי מתרגם אנושי. איננו אחראים לכל אי-הבנות או פרשנויות שגויות הנובעות משימוש בתרגום זה.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->