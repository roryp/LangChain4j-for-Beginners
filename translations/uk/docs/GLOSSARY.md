<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:24:09+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "uk"
}
-->
# LangChain4j Глосарій

## Зміст

- [Основні поняття](../../../docs)
- [Компоненти LangChain4j](../../../docs)
- [Поняття AI/ML](../../../docs)
- [Проєктування підказок](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Агенти та інструменти](../../../docs)
- [Протокол контексту моделі (MCP)](../../../docs)
- [Сервіси Azure](../../../docs)
- [Тестування та розробка](../../../docs)

Швидкий довідник термінів і понять, що використовуються протягом курсу.

## Основні поняття

**AI Agent** - Система, що використовує ШІ для автономного мислення та дій. [Модуль 04](../04-tools/README.md)

**Chain** - Послідовність операцій, де вихід подається на наступний крок.

**Chunking** - Розбиття документів на менші частини. Типово: 300-500 токенів з перекриттям. [Модуль 03](../03-rag/README.md)

**Context Window** - Максимальна кількість токенів, які модель може обробити. GPT-5: 400К токенів.

**Embeddings** - Числові вектори, що представляють значення тексту. [Модуль 03](../03-rag/README.md)

**Function Calling** - Модель генерує структуровані запити для виклику зовнішніх функцій. [Модуль 04](../04-tools/README.md)

**Hallucination** - Коли моделі генерують неправильну, але правдоподібну інформацію.

**Prompt** - Текстовий вхід для мовної моделі. [Модуль 02](../02-prompt-engineering/README.md)

**Semantic Search** - Пошук за значенням із використанням embeddings, а не ключових слів. [Модуль 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: без пам’яті. Stateful: зберігає історію розмови. [Модуль 01](../01-introduction/README.md)

**Tokens** - Базові одиниці тексту, які обробляють моделі. Впливає на вартість і обмеження. [Модуль 01](../01-introduction/README.md)

**Tool Chaining** - Послідовне виконання інструментів, де вихід інформує наступний виклик. [Модуль 04](../04-tools/README.md)

## Компоненти LangChain4j

**AiServices** - Створює типобезпечні інтерфейси AI-сервісів.

**OpenAiOfficialChatModel** - Уніфікований клієнт для моделей OpenAI та Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Створює embeddings за допомогою офіційного клієнта OpenAI (підтримує OpenAI та Azure OpenAI).

**ChatModel** - Основний інтерфейс для мовних моделей.

**ChatMemory** - Зберігає історію розмов.

**ContentRetriever** - Знаходить релевантні частини документів для RAG.

**DocumentSplitter** - Розбиває документи на частини.

**EmbeddingModel** - Перетворює текст у числові вектори.

**EmbeddingStore** - Зберігає та отримує embeddings.

**MessageWindowChatMemory** - Підтримує ковзне вікно останніх повідомлень.

**PromptTemplate** - Створює повторно використовувані підказки з плейсхолдерами `{{variable}}`.

**TextSegment** - Текстовий сегмент з метаданими. Використовується в RAG.

**ToolExecutionRequest** - Представляє запит на виконання інструменту.

**UserMessage / AiMessage / SystemMessage** - Типи повідомлень у розмові.

## Поняття AI/ML

**Few-Shot Learning** - Надання прикладів у підказках. [Модуль 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - ШІ-моделі, навчені на великих обсягах тексту.

**Reasoning Effort** - Параметр GPT-5, що контролює глибину мислення. [Модуль 02](../02-prompt-engineering/README.md)

**Temperature** - Контролює випадковість виходу. Низьке = детерміноване, високе = креативне.

**Vector Database** - Спеціалізована база даних для embeddings. [Модуль 03](../03-rag/README.md)

**Zero-Shot Learning** - Виконання завдань без прикладів. [Модуль 02](../02-prompt-engineering/README.md)

## Проєктування підказок - [Модуль 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Покрокове мислення для кращої точності.

**Constrained Output** - Забезпечення конкретного формату або структури.

**High Eagerness** - Шаблон GPT-5 для ретельного мислення.

**Low Eagerness** - Шаблон GPT-5 для швидких відповідей.

**Multi-Turn Conversation** - Підтримка контексту між обмінами.

**Role-Based Prompting** - Встановлення персонажу моделі через системні повідомлення.

**Self-Reflection** - Модель оцінює та покращує свій вихід.

**Structured Analysis** - Фіксована рамка оцінювання.

**Task Execution Pattern** - План → Виконання → Підсумок.

## RAG (Retrieval-Augmented Generation) - [Модуль 03](../03-rag/README.md)

**Document Processing Pipeline** - Завантаження → розбиття → вбудовування → збереження.

**In-Memory Embedding Store** - Непостійне сховище для тестування.

**RAG** - Поєднує пошук і генерацію для обґрунтування відповідей.

**Similarity Score** - Оцінка (0-1) семантичної схожості.

**Source Reference** - Метадані про отриманий контент.

## Агенти та інструменти - [Модуль 04](../04-tools/README.md)

**@Tool Annotation** - Позначає Java-методи як інструменти, доступні для виклику ШІ.

**ReAct Pattern** - Міркувати → Діяти → Спостерігати → Повторювати.

**Session Management** - Окремі контексти для різних користувачів.

**Tool** - Функція, яку може викликати AI агент.

**Tool Description** - Документація призначення та параметрів інструменту.

## Протокол контексту моделі (MCP) - [Модуль 05](../05-mcp/README.md)

**Docker Transport** - MCP сервер у контейнері Docker.

**MCP** - Стандарт для підключення AI-додатків до зовнішніх інструментів.

**MCP Client** - Додаток, що підключається до MCP серверів.

**MCP Server** - Сервіс, що надає інструменти через MCP.

**Server-Sent Events (SSE)** - Потокова передача від сервера до клієнта через HTTP.

**Stdio Transport** - Сервер як підпроцес через stdin/stdout.

**Streamable HTTP Transport** - HTTP з SSE для комунікації в реальному часі.

**Tool Discovery** - Клієнт запитує сервер про доступні інструменти.

## Сервіси Azure - [Модуль 01](../01-introduction/README.md)

**Azure AI Search** - Хмарний пошук з векторними можливостями. [Модуль 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Розгортання ресурсів Azure.

**Azure OpenAI** - Корпоративний AI-сервіс Microsoft.

**Bicep** - Мова інфраструктури як коду для Azure. [Інфраструктурний посібник](../01-introduction/infra/README.md)

**Deployment Name** - Назва розгортання моделі в Azure.

**GPT-5** - Остання модель OpenAI з контролем мислення. [Модуль 02](../02-prompt-engineering/README.md)

## Тестування та розробка - [Посібник з тестування](TESTING.md)

**Dev Container** - Контейнеризоване середовище розробки. [Конфігурація](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Безкоштовний майданчик для AI-моделей. [Модуль 00](../00-quick-start/README.md)

**In-Memory Testing** - Тестування з використанням пам’яті.

**Integration Testing** - Тестування з реальною інфраструктурою.

**Maven** - Інструмент автоматизації збірки Java.

**Mockito** - Фреймворк для мокінгу в Java.

**Spring Boot** - Фреймворк для Java-додатків. [Модуль 01](../01-introduction/README.md)

**Testcontainers** - Docker-контейнери у тестах.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Відмова від відповідальності**:  
Цей документ було перекладено за допомогою сервісу автоматичного перекладу [Co-op Translator](https://github.com/Azure/co-op-translator). Хоча ми прагнемо до точності, будь ласка, майте на увазі, що автоматичні переклади можуть містити помилки або неточності. Оригінальний документ рідною мовою слід вважати авторитетним джерелом. Для критично важливої інформації рекомендується звертатися до професійного людського перекладу. Ми не несемо відповідальності за будь-які непорозуміння або неправильні тлумачення, що виникли внаслідок використання цього перекладу.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->