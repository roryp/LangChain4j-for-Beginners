<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "d61ab6c245562094cc3dddecf08b50d3",
  "translation_date": "2025-12-31T02:26:24+00:00",
  "source_file": "README.md",
  "language_code": "he"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b50.he.png" alt="LangChain4j" width="800"/>

### 🌐 תמיכה ברב־שפות

#### נתמכת באמצעות GitHub Action (מֻמְשֶׁכֶת ותמיד מעודכנת)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[ערבית](../ar/README.md) | [בנגלית](../bn/README.md) | [בולגרית](../bg/README.md) | [בורמזית (מיאנמר)](../my/README.md) | [סינית (מפושטת)](../zh/README.md) | [סינית (מסורתית, הונג קונג)](../hk/README.md) | [סינית (מסורתית, מקאו)](../mo/README.md) | [סינית (מסורתית, טאיוואן)](../tw/README.md) | [קרואטית](../hr/README.md) | [צ'כית](../cs/README.md) | [דנית](../da/README.md) | [הולנדית](../nl/README.md) | [אסטונית](../et/README.md) | [פינית](../fi/README.md) | [צרפתית](../fr/README.md) | [גרמנית](../de/README.md) | [יוונית](../el/README.md) | [עברית](./README.md) | [הינדי](../hi/README.md) | [הונגרית](../hu/README.md) | [אינדונזית](../id/README.md) | [איטלקית](../it/README.md) | [יפנית](../ja/README.md) | [קנאדה](../kn/README.md) | [קוריאנית](../ko/README.md) | [ליטאית](../lt/README.md) | [מלאית](../ms/README.md) | [מלאיאלאם](../ml/README.md) | [מראטי](../mr/README.md) | [נפאלית](../ne/README.md) | [פיג'ין ניגרי](../pcm/README.md) | [נורבגית](../no/README.md) | [פרסית (פארסי)](../fa/README.md) | [פולנית](../pl/README.md) | [פורטוגזית (ברזיל)](../br/README.md) | [פורטוגזית (פורטוגל)](../pt/README.md) | [פנג'אבי (גורמוקי)](../pa/README.md) | [רומנית](../ro/README.md) | [רוסית](../ru/README.md) | [סרבית (קירילית)](../sr/README.md) | [סלובקית](../sk/README.md) | [סלובנית](../sl/README.md) | [ספרדית](../es/README.md) | [סווהילית](../sw/README.md) | [שבדית](../sv/README.md) | [טגלוג (פיליפינית)](../tl/README.md) | [טמילית](../ta/README.md) | [טלוגו](../te/README.md) | [תאית](../th/README.md) | [טורקית](../tr/README.md) | [אוקראינית](../uk/README.md) | [אורדו](../ur/README.md) | [וייטנאמית](../vi/README.md)
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j למתחילים

קורס לבניית יישומי בינה מלאכותית עם LangChain4j ו‑Azure OpenAI GPT-5, משיחה בסיסית ועד סוכני AI.

**חדש ב‑LangChain4j?** עיין ב‑[מילון המונחים](docs/GLOSSARY.md) להגדרות של מונחים ועקרונות מרכזיים.

## Table of Contents

1. [התחלה מהירה](00-quick-start/README.md) - התחל עם LangChain4j
2. [מבוא](01-introduction/README.md) - למד את היסודות של LangChain4j
3. [הנדסת פרומפטים](02-prompt-engineering/README.md) - שלוט בעיצוב פרומפטים יעיל
4. [RAG (Retrieval-Augmented Generation)](03-rag/README.md) - בנה מערכות חכמות מבוססות ידע
5. [כלים](04-tools/README.md) - שלב כלים חיצוניים ועוזרים פשוטים
6. [MCP (Model Context Protocol)](05-mcp/README.md) - עבוד עם פרוטוקול הקשר המודלי (MCP) ומודולים אג'נטיים
---

##  מסלול הלמידה

> **התחלה מהירה**

1. Fork את המאגר הזה אל חשבון ה‑GitHub שלך
2. לחץ על **Code** → לשונית **Codespaces** → **...** → **New with options...**
3. השתמש בהגדרות ברירת המחדל – זה יבחר את מכולת הפיתוח שנוצרה עבור קורס זה
4. לחץ על **Create codespace**
5. המתן 5–10 דקות עד שהסביבה תהיה מוכנה
6. דלג ישירות אל [התחלה מהירה](./00-quick-start/README.md) כדי להתחיל!

> **מעדיפים לשכפל מקומית?**
>
> מאגר זה כולל יותר מ‑50 תרגומי שפות, מה שמגדיל משמעותית את גודל ההורדה. כדי לשכפל ללא התרגומים, השתמש ב‑sparse checkout:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> זה נותן לך את כל מה שאתה צריך כדי להשלים את הקורס עם הורדה מהירה יותר משמעותית.

לאחר שתשלים את המודולים, עיין ב‑[מדריך הבדיקה](docs/TESTING.md) כדי לראות מושגי בדיקה של LangChain4j בפעולה.

> **הערה:** אימון זה משתמש הן ב‑GitHub Models והן ב‑Azure OpenAI. מודולי [התחלה מהירה](00-quick-start/README.md) ו‑[MCP](05-mcp/README.md) משתמשים ב‑GitHub Models (אין צורך במנוי Azure), בעוד שמודולים 1–4 משתמשים ב‑Azure OpenAI GPT-5.


## למידה עם GitHub Copilot

כדי להתחיל לתכנת במהירות, פתח את הפרויקט הזה ב‑GitHub Codespace או ב‑IDE המקומי שלך עם ה‑devcontainer המסופק. ה‑devcontainer שבו משתמשים בקורס זה מגיע מראש עם תצורה של GitHub Copilot לתכנות זוגי בעזרת AI.

כל דוגמת קוד כוללת שאלות מוצעות שאתה יכול לשאול את GitHub Copilot כדי להעמיק את הבנתך. חפש את ההנחיות 💡/🤖 ב:

- **כותרות קבצי Java** - שאלות ספציפיות לכל דוגמה
- **README של המודולים** - הנחיות חקירה לאחר דוגמות קוד

**כיצד להשתמש:** פתח כל קובץ קוד ושאל את Copilot את השאלות המוצעות. יש לו הקשר מלא של בסיס הקוד ויכול להסביר, להרחיב ולהציע חלופות.

רוצה ללמוד עוד? עיין ב‑[Copilot for AI Paired Programming](https://aka.ms/GitHubCopilotAI).


## משאבים נוספים

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j למתחילים](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js למתחילים](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / סוכנים
[![AZD למתחילים](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI למתחילים](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP למתחילים](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Agents למתחילים](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### סדרת בינה גנרטיבית
[![בינה גנרטיבית למתחילים](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![בינה גנרטיבית (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![בינה גנרטיבית (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![בינה גנרטיבית (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### לימוד ליבה
[![ML למתחילים](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![מדע הנתונים למתחילים](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI למתחילים](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![סייברסקיוריטי למתחילים](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![פיתוח ווב למתחילים](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT למתחילים](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![פיתוח XR למתחילים](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### סדרת Copilot
[![Copilot לתכנות זוגי עם AI](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot ל-C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![הרפתקת Copilot](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## קבלת עזרה

אם תיתקלו בבעיה או יהיו לכם שאלות לגבי בניית אפליקציות בינה מלאכותית, הצטרפו אל:

[![Discord של Azure AI Foundry](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

אם יש לכם משוב על המוצר או דיווח על שגיאות במהלך הבנייה, בקרו ב:

[![פורום המפתחים של Azure AI Foundry](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## רישיון

רישיון MIT - ראו את קובץ [LICENSE](../../LICENSE) לפרטים.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
הצהרת אחריות:
מסמך זה תורגם באמצעות שירות תרגום מבוסס בינה מלאכותית Co-op Translator (https://github.com/Azure/co-op-translator). אף שאנו שואפים לדיוק, יש לקחת בחשבון כי תרגומים אוטומטיים עלולים להכיל שגיאות או אי‑דיוקים. יש להסתמך על המסמך המקורי בשפת המקור כמקור הסמכות. למידע קריטי מומלץ להיעזר בתרגום מקצועי על ידי מתרגם אנושי. אנו לא נושאים באחריות לכל אי‑הבנה או פרשנות שגויה הנובעות משימוש בתרגום זה.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->