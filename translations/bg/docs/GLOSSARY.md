<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T05:09:08+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "bg"
}
-->
# Глосар на LangChain4j

## Съдържание

- [Основни концепции](../../../docs)
- [Компоненти на LangChain4j](../../../docs)
- [AI/ML концепции](../../../docs)
- [Проектиране на подсказки](../../../docs)
- [RAG (генериране, подсилено чрез извличане)](../../../docs)
- [Агенти и инструменти](../../../docs)
- [Протокол за контекст на модела (MCP)](../../../docs)
- [Услуги на Azure](../../../docs)
- [Тестване и разработка](../../../docs)

Бързо справочно ръководство за термини и концепции, използвани в целия курс.

## Core Concepts

**AI Agent** - Система, която използва AI за разсъждение и автономни действия. [Модул 04](../04-tools/README.md)

**Chain** - Последователност от операции, където изходът се използва като вход за следващата стъпка.

**Chunking** - Разделяне на документи на по-малки части. Типично: 300-500 токена с припокриване. [Модул 03](../03-rag/README.md)

**Context Window** - Максималният брой токени, които моделът може да обработи. GPT-5: 400K токена.

**Embeddings** - Числови вектори, представящи значението на текста. [Модул 03](../03-rag/README.md)

**Function Calling** - Моделът генерира структурирани заявки за извикване на външни функции. [Модул 04](../04-tools/README.md)

**Hallucination** - Когато моделите генерират неверна, но правдоподобна информация.

**Prompt** - Текстов вход към езиков модел. [Модул 02](../02-prompt-engineering/README.md)

**Semantic Search** - Търсене по значение, използвайки embeddings, а не ключови думи. [Модул 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: без памет. Stateful: поддържа история на разговора. [Модул 01](../01-introduction/README.md)

**Tokens** - Базови текстови единици, които моделите обработват. Влияе върху разходите и ограниченията. [Модул 01](../01-introduction/README.md)

**Tool Chaining** - Последователно изпълнение на инструменти, при което изходът информира следващото извикване. [Модул 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Създава типобезопасни интерфейси за AI услуги.

**OpenAiOfficialChatModel** - Унифициран клиент за модели на OpenAI и Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Създава embeddings, използвайки OpenAI Official клиент (поддържа както OpenAI, така и Azure OpenAI).

**ChatModel** - Основен интерфейс за езикови модели.

**ChatMemory** - Поддържа история на разговора.

**ContentRetriever** - Намира релевантни фрагменти от документи за RAG.

**DocumentSplitter** - Разделя документи на части.

**EmbeddingModel** - Преобразува текст в числови вектори.

**EmbeddingStore** - Съхранява и извлича embeddings.

**MessageWindowChatMemory** - Поддържа плъзгащо се прозорче с последни съобщения.

**PromptTemplate** - Създава многократно използваеми подсказки с плейсхолдери `{{variable}}`.

**TextSegment** - Текстов фрагмент с метаданни. Използва се в RAG.

**ToolExecutionRequest** - Представлява заявка за изпълнение на инструмент.

**UserMessage / AiMessage / SystemMessage** - Типове съобщения в разговор.

## AI/ML Concepts

**Few-Shot Learning** - Даване на примери в подсказките. [Модул 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI модели, обучени върху големи обеми текстови данни.

**Reasoning Effort** - Параметър на GPT-5, контролиращ дълбочината на разсъждение. [Модул 02](../02-prompt-engineering/README.md)

**Temperature** - Контролира случайността на изхода. Ниска=детерминистичен, висока=креативен.

**Vector Database** - Специализирана база данни за embeddings. [Модул 03](../03-rag/README.md)

**Zero-Shot Learning** - Изпълнение на задачи без примери. [Модул 02](../02-prompt-engineering/README.md)

## Prompt Engineering - [Модул 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Стъпково разсъждение за по-добра точност.

**Constrained Output** - Налагане на специфичен формат или структура.

**High Eagerness** - Шаблон на GPT-5 за задълбочено разсъждение.

**Low Eagerness** - Шаблон на GPT-5 за бързи отговори.

**Multi-Turn Conversation** - Поддържане на контекста през няколко обмени.

**Role-Based Prompting** - Настройване на персона на модела чрез системни съобщения.

**Self-Reflection** - Моделът оценява и подобрява своя изход.

**Structured Analysis** - Фиксирана рамка за оценка.

**Task Execution Pattern** - Планиране → Изпълнение → Обобщение.

## RAG (Retrieval-Augmented Generation) - [Модул 03](../03-rag/README.md)

**Document Processing Pipeline** - Зареждане → разделяне на части → embedding → съхранение.

**In-Memory Embedding Store** - Непостоянно хранилище за тестване.

**RAG** - Комбинира извличане с генериране, за да обоснове отговорите.

**Similarity Score** - Мярка (0-1) за семантична прилика.

**Source Reference** - Метаданни за извлеченото съдържание.

## Agents and Tools - [Модул 04](../04-tools/README.md)

**@Tool Annotation** - Маркира Java методи като инструменти, достъпни за AI.

**ReAct Pattern** - Разсъждение → Действие → Наблюдение → Повторение.

**Session Management** - Отделни контексти за различни потребители.

**Tool** - Функция, която AI агент може да извика.

**Tool Description** - Документация за целта на инструмента и параметрите му.

## Model Context Protocol (MCP) - [Модул 05](../05-mcp/README.md)

**MCP** - Стандарт за свързване на AI приложения с външни инструменти.

**MCP Client** - Приложение, което се свързва към MCP сървъри.

**MCP Server** - Услуга, която експонира инструменти чрез MCP.

**Stdio Transport** - Сървър като подпроцес чрез stdin/stdout.

**Tool Discovery** - Клиентът запитва сървъра за налични инструменти.

## Azure Services - [Модул 01](../01-introduction/README.md)

**Azure AI Search** - Облачно търсене с векторни възможности. [Модул 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Деплой на Azure ресурси.

**Azure OpenAI** - Корпоративната AI услуга на Microsoft.

**Bicep** - Език за инфраструктура като код за Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Име за разполагане на модел в Azure.

**GPT-5** - Най-новият модел на OpenAI с контрол върху разсъждението. [Модул 02](../02-prompt-engineering/README.md)

## Testing and Development - [Testing Guide](TESTING.md)

**Dev Container** - Контейнеризирана среда за разработка. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Безплатна AI платформа за експерименти. [Модул 00](../00-quick-start/README.md)

**In-Memory Testing** - Тестване с в паметта съхранение.

**Integration Testing** - Тестване с реална инфраструктура.

**Maven** - Инструмент за автоматизация на билдове за Java.

**Mockito** - Фреймуорк за мокване в Java.

**Spring Boot** - Java рамка за приложения. [Модул 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
Отказ от отговорност:
Този документ е преведен с помощта на AI услуга за превод [Co-op Translator](https://github.com/Azure/co-op-translator). Въпреки че се стремим към точност, имайте предвид, че автоматичните преводи могат да съдържат грешки или неточности. Оригиналният документ на първоначалния му език трябва да се счита за авторитетен източник. За критична информация се препоръчва професионален превод, извършен от човек. Не носим отговорност за каквито и да е недоразумения или погрешни тълкувания, произтичащи от използването на този превод.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->