<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T05:41:53+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "hr"
}
-->
# LangChain4j Rječnik

## Sadržaj

- [Osnovni pojmovi](../../../docs)
- [LangChain4j Components](../../../docs)
- [AI/ML Concepts](../../../docs)
- [Prompt inženjering - [Modul 02]](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenti i alati - [Modul 04]](../../../docs)
- [Protokol konteksta modela (MCP) - [Modul 05]](../../../docs)
- [Azure usluge - [Modul 01]](../../../docs)
- [Testiranje i razvoj - [Vodič za testiranje]](../../../docs)

Kratki pregled pojmova i koncepata korištenih kroz tečaj.

## Osnovni pojmovi

**AI Agent** - Sustav koji koristi AI za zaključivanje i autonomno djelovanje. [Modul 04](../04-tools/README.md)

**Chain** - Niz operacija gdje izlaz daje ulaz za sljedeći korak.

**Chunking** - Razdvajanje dokumenata na manje dijelove. Tipično: 300-500 tokena s preklapanjem. [Modul 03](../03-rag/README.md)

**Context Window** - Maksimalan broj tokena koje model može obraditi. GPT-5: 400K tokena.

**Embeddings** - Numerički vektori koji predstavljaju značenje teksta. [Modul 03](../03-rag/README.md)

**Function Calling** - Model generira strukturirane zahtjeve za pozivanje vanjskih funkcija. [Modul 04](../04-tools/README.md)

**Hallucination** - Kada modeli generiraju netočne, ali izgledom uvjerljive informacije.

**Prompt** - Tekstovni ulaz za jezični model. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Pretraživanje po značenju koristeći embeddings, a ne ključne riječi. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: bez memorije. Stateful: održava povijest razgovora. [Modul 01](../01-introduction/README.md)

**Tokens** - Osnovne jedinice teksta koje modeli obrađuju. Utječu na troškove i ograničenja. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Sekvencijalno izvršavanje alata gdje izlaz informira sljedeći poziv. [Modul 04](../04-tools/README.md)

## LangChain4j Components

**AiServices** - Creates type-safe AI service interfaces.

**OpenAiOfficialChatModel** - Unified client for OpenAI and Azure OpenAI models.

**OpenAiOfficialEmbeddingModel** - Stvara ugradbene vektore koristeći OpenAI Official klijent (podržava i OpenAI i Azure OpenAI).

**ChatModel** - Osnovno sučelje za jezične modele.

**ChatMemory** - Održava povijest razgovora.

**ContentRetriever** - Pronalazi relevantne segmente dokumenata za RAG.

**DocumentSplitter** - Dijeli dokumente na segmente.

**EmbeddingModel** - Pretvara tekst u numeričke vektore.

**EmbeddingStore** - Pohranjuje i dohvaća embeddings.

**MessageWindowChatMemory** - Održava klizni prozor nedavnih poruka.

**PromptTemplate** - Stvara ponovno upotrebljive promptove s `{{variable}}` rezervirnim mjestima.

**TextSegment** - Tekstualni segment s metapodacima. Koristi se u RAG.

**ToolExecutionRequest** - Predstavlja zahtjev za izvršavanje alata.

**UserMessage / AiMessage / SystemMessage** - Vrste poruka u razgovoru.

## AI/ML Concepts

**Few-Shot Learning** - Davanje primjera u promptovima. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI modeli trenirani na velikim skupovima tekstualnih podataka.

**Reasoning Effort** - Parametar GPT-5 koji kontrolira dubinu rezoniranja. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Kontrolira nasumičnost izlaza. Nisko=determinističko, visoko=kreativno.

**Vector Database** - Specijalizirana baza podataka za embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Izvođenje zadataka bez primjera. [Modul 02](../02-prompt-engineering/README.md)

## Prompt inženjering - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Lančano razmišljanje korak-po-korak za bolju točnost.

**Constrained Output** - Nametanje određenog formata ili strukture izlaza.

**High Eagerness** - Uzorak GPT-5 za temeljito rezoniranje.

**Low Eagerness** - Uzorak GPT-5 za brze odgovore.

**Multi-Turn Conversation** - Održavanje konteksta kroz više izmjena poruka.

**Role-Based Prompting** - Postavljanje personae modela putem sistemskih poruka.

**Self-Reflection** - Model evaluira i poboljšava svoj izlaz.

**Structured Analysis** - Fiksni okvir za evaluaciju.

**Task Execution Pattern** - Plan → Izvrši → Sažmi.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Load → chunk → embed → store.

**In-Memory Embedding Store** - Ne-persistentna pohrana za testiranje.

**RAG** - Kombinira dohvaćanje s generiranjem kako bi utemeljio odgovore.

**Similarity Score** - Mjera (0-1) semantičke sličnosti.

**Source Reference** - Metapodaci o dohvaćenom sadržaju.

## Agenti i alati - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Označava Java metode kao alate dostupne AI-u.

**ReAct Pattern** - Razmišljaj → Djeluj → Promatraj → Ponavljaj.

**Session Management** - Odvojeni konteksti za različite korisnike.

**Tool** - Funkcija koju AI agent može pozvati.

**Tool Description** - Dokumentacija o svrsi alata i parametrima.

## Protokol konteksta modela (MCP) - [Modul 05](../05-mcp/README.md)

**MCP** - Standard za povezivanje AI aplikacija s vanjskim alatima.

**MCP Client** - Aplikacija koja se povezuje na MCP servere.

**MCP Server** - Usluga koja izlaže alate putem MCP-a.

**Stdio Transport** - Server kao podproces putem stdin/stdout.

**Tool Discovery** - Klijent upitima otkriva dostupne alate.

## Azure usluge - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Cloud pretraživanje s vektorskim mogućnostima. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Raspoređuje Azure resurse.

**Azure OpenAI** - Microsoftova enterprise AI usluga.

**Bicep** - Azure jezik za infrastrukturu kao kod. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - Naziv za raspoređivanje modela u Azure.

**GPT-5** - Najnoviji OpenAI model s kontrolom rezoniranja. [Modul 02](../02-prompt-engineering/README.md)

## Testiranje i razvoj - [Vodič za testiranje](TESTING.md)

**Dev Container** - Kontejnerizirano razvojno okruženje. [Konfiguracija](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Besplatno okruženje za isprobavanje AI modela. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testiranje s pohranom u memoriji.

**Integration Testing** - Testiranje s pravom infrastrukturom.

**Maven** - Alat za automatizaciju izgradnje Java projekata.

**Mockito** - Java framework za kreiranje mock objekata.

**Spring Boot** - Java aplikacijski framework. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Odricanje odgovornosti**:
Ovaj dokument je preveden pomoću AI usluge automatskog prevođenja [Co-op Translator](https://github.com/Azure/co-op-translator). Iako nastojimo postići točnost, imajte na umu da automatski prijevodi mogu sadržavati pogreške ili netočnosti. Izvorni dokument na izvornom jeziku treba smatrati autoritativnim izvorom. Za kritične informacije preporučuje se profesionalni ljudski prijevod. Ne snosimo odgovornost za bilo kakve nesporazume ili pogrešne interpretacije koje proizlaze iz korištenja ovog prijevoda.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->