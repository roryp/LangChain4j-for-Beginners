# LangChain4j Rječnik

## Sadržaj

- [Temeljni Pojmovi](../../../docs)
- [LangChain4j Komponente](../../../docs)
- [AI/ML Pojmovi](../../../docs)
- [Sigurnosne Mjere](../../../docs)
- [Inženjering Promptova](../../../docs)
- [RAG (Pretraživanjem Obogaćena Generacija)](../../../docs)
- [Agent i Alati](../../../docs)
- [Agentni Modul](../../../docs)
- [Protokol Konteksta Modela (MCP)](../../../docs)
- [Azure Usluge](../../../docs)
- [Testiranje i Razvoj](../../../docs)

Brzi pregled termina i pojmova korištenih tijekom tečaja.

## Temeljni Pojmovi

**AI Agent** - Sustav koji koristi AI za razmišljanje i autonomno djelovanje. [Modul 04](../04-tools/README.md)

**Chain** - Sekvenca operacija gdje izlaz ulazi u sljedeći korak.

**Chunking** - Razbijanje dokumenata na manje dijelove. Tipično: 300-500 tokena s preklapanjem. [Modul 03](../03-rag/README.md)

**Context Window** - Maksimalan broj tokena koje model može obraditi. GPT-5: 400K tokena.

**Embeddings** - Numerički vektori koji predstavljaju značenje teksta. [Modul 03](../03-rag/README.md)

**Function Calling** - Model generira strukturirane zahtjeve za pozivanje vanjskih funkcija. [Modul 04](../04-tools/README.md)

**Hallucination** - Kada modeli generiraju netočne, ali uvjerljive informacije.

**Prompt** - Tekstualni ulaz u jezični model. [Modul 02](../02-prompt-engineering/README.md)

**Semantic Search** - Pretraživanje po značenju koristeći embeddings, a ne ključne riječi. [Modul 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: bez memorije. Stateful: održava povijest razgovora. [Modul 01](../01-introduction/README.md)

**Tokens** - Osnovne tekstualne jedinice koje modeli obrađuju. Utječe na troškove i ograničenja. [Modul 01](../01-introduction/README.md)

**Tool Chaining** - Sekvencijalno izvršavanje alata gdje izlaz informira sljedeći poziv. [Modul 04](../04-tools/README.md)

## LangChain4j Komponente

**AiServices** - Stvara tip-sigurne sučelja za AI servise.

**OpenAiOfficialChatModel** - Jedinstveni klijent za OpenAI i Azure OpenAI modele.

**OpenAiOfficialEmbeddingModel** - Stvara embeddings koristeći OpenAI Official klijent (podržava i OpenAI i Azure OpenAI).

**ChatModel** - Temeljno sučelje za jezične modele.

**ChatMemory** - Održava povijest razgovora.

**ContentRetriever** - Pronalazi relevantne dijelove dokumenata za RAG.

**DocumentSplitter** - Dijeli dokumente na dijelove.

**EmbeddingModel** - Pretvara tekst u numeričke vektore.

**EmbeddingStore** - Pohranjuje i dohvaća embeddings.

**MessageWindowChatMemory** - Održava klizni prozor nedavnih poruka.

**PromptTemplate** - Stvara višekratno upotrebljive promptove s zastavicama `{{variable}}`.

**TextSegment** - Dio teksta s metapodacima. Koristi se u RAG.

**ToolExecutionRequest** - Predstavlja zahtjev za izvršenje alata.

**UserMessage / AiMessage / SystemMessage** - Tipovi poruka u razgovoru.

## AI/ML Pojmovi

**Few-Shot Learning** - Davanje primjera u promptovima. [Modul 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - AI modeli trenirani na ogromnim tekstualnim podatcima.

**Reasoning Effort** - Parametar GPT-5 koji kontrolira dubinu razmišljanja. [Modul 02](../02-prompt-engineering/README.md)

**Temperature** - Kontrolira nasumičnost izlaza. Nisko=deterministički, visoko=kreativno.

**Vector Database** - Specijalizirana baza podataka za embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot Learning** - Izvođenje zadataka bez primjera. [Modul 02](../02-prompt-engineering/README.md)

## Sigurnosne Mjere - [Modul 00](../00-quick-start/README.md)

**Defense in Depth** - Višeslojni sigurnosni pristup koji kombinira aplikacijske zaštite s filtrima sigurnosti pružatelja usluga.

**Hard Block** - Pružatelj usluge vraća HTTP 400 grešku za ozbiljna kršenja sadržaja.

**InputGuardrail** - LangChain4j sučelje za validaciju korisničkog unosa prije nego što dosegne LLM. Štedi troškove i kašnjenje tako što rano blokira štetne promptove.

**InputGuardrailResult** - Povratni tip za validaciju guardraila: `success()` ili `fatal("razlog")`.

**OutputGuardrail** - Sučelje za validaciju AI odgovora prije vraćanja korisnicima.

**Provider Safety Filters** - Ugrađeni filtri sadržaja od AI pružatelja usluga (npr. GitHub Models) koji hvataju kršenja na razini API-ja.

**Soft Refusal** - Model uljudno odbija odgovoriti bez bacanja greške.

## Inženjering Promptova - [Modul 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - Korak-po-korak razmišljanje radi bolje preciznosti.

**Constrained Output** - Nalaženje specifičnog formata ili strukture.

**High Eagerness** - GPT-5 uzorak za temeljito razmišljanje.

**Low Eagerness** - GPT-5 uzorak za brze odgovore.

**Multi-Turn Conversation** - Održavanje konteksta kroz razmjene.

**Role-Based Prompting** - Postavljanje modelne persone putem sistemskih poruka.

**Self-Reflection** - Model sam ocjenjuje i poboljšava svoj izlaz.

**Structured Analysis** - Fiksirani okvir evaluacije.

**Task Execution Pattern** - Plan → Izvrši → Sažmi.

## RAG (Pretraživanjem Obogaćena Generacija) - [Modul 03](../03-rag/README.md)

**Document Processing Pipeline** - Učitaj → podijeli → ugradnja → pohrana.

**In-Memory Embedding Store** - Ne-persistentna pohrana za testiranje.

**RAG** - Kombinira pretraživanje s generacijom da zakloni odgovore.

**Similarity Score** - Mjera (0-1) semantičke sličnosti.

**Source Reference** - Metapodaci o dohvaćenom sadržaju.

## Agent i Alati - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Označava Java metode kao AI-pozive alata.

**ReAct Pattern** - Razmišljaj → Djeluj → Promatraj → Ponovi.

**Session Management** - Odvojeni konteksti za različite korisnike.

**Tool** - Funkcija koju AI agent može pozvati.

**Tool Description** - Dokumentacija svrhe alata i parametara.

## Agentni Modul - [Modul 05](../05-mcp/README.md)

**@Agent Annotation** - Označava sučelja kao AI agente s deklarativnom definicijom ponašanja.

**Agent Listener** - Kuka za praćenje izvršenja agenta putem `beforeAgentInvocation()` i `afterAgentInvocation()`.

**Agentic Scope** - Dijeljena memorija gdje agenti pohranjuju izlaze koristeći `outputKey` za daljnju potrošnju drugih agenata.

**AgenticServices** - Tvornica za stvaranje agenata koristeći `agentBuilder()` i `supervisorBuilder()`.

**Conditional Workflow** - Usmjeravanje na temelju uvjeta različitim specijaliziranim agentima.

**Human-in-the-Loop** - Uzorak tijeka rada koji dodaje ljudske kontrolne točke za odobrenje ili pregled sadržaja.

**langchain4j-agentic** - Maven ovisnost za deklarativnu izgradnju agenata (eksperimentalno).

**Loop Workflow** - Iterira izvršenje agenta dok se ne zadovolji uvjet (npr. ocjena kvalitete ≥ 0.8).

**outputKey** - Parametar anotacije agenta koji specificira gdje se rezultati pohranjuju u Agentic Scope.

**Parallel Workflow** - Istovremeno pokretanje više agenata za neovisne zadatke.

**Response Strategy** - Kako nadzornik formulira konačni odgovor: LAST, SUMMARY, ili SCORED.

**Sequential Workflow** - Izvršavanje agenata redom gdje izlaz ide u sljedeći korak.

**Supervisor Agent Pattern** - Napredni agentni uzorak gdje nadzorna LLM dinamički odlučuje koje pod-agente pozvati.

## Protokol Konteksta Modela (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** - Maven ovisnost za MCP integraciju u LangChain4j.

**MCP** - Model Context Protocol: standard za povezivanje AI aplikacija s vanjskim alatima. Izgradi jednom, koristi svugdje.

**MCP Client** - Aplikacija koja se povezuje na MCP poslužitelje za otkrivanje i korištenje alata.

**MCP Server** - Usluga koja izlaže alate putem MCP s jasnim opisima i shemama parametara.

**McpToolProvider** - LangChain4j komponenta koja omotava MCP alate za korištenje u AI servisima i agentima.

**McpTransport** - Sučelje za MCP komunikaciju. Implementacije uključuju Stdio i HTTP.

**Stdio Transport** - Lokalni transport procesa putem stdin/stdout. Korisno za pristup datotečnom sustavu ili alatima naredbenog retka.

**StdioMcpTransport** - LangChain4j implementacija koja pokreće MCP poslužitelj kao podproces.

**Tool Discovery** - Klijent upitom prema poslužitelju traži dostupne alate s opisima i shemama.

## Azure Usluge - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Cloud pretraživanje s vektorskim mogućnostima. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Pokreće Azure resurse.

**Azure OpenAI** - Microsoftova AI usluga za poduzeća.

**Bicep** - Azure jezik za infrastrukturni kod. [Vodič za infrastrukturu](../01-introduction/infra/README.md)

**Deployment Name** - Ime za model deployment u Azureu.

**GPT-5** - Najnoviji OpenAI model s kontrolom razmišljanja. [Modul 02](../02-prompt-engineering/README.md)

## Testiranje i Razvoj - [Vodič za testiranje](TESTING.md)

**Dev Container** - Kontejnerizirano razvojno okruženje. [Konfiguracija](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Besplatno AI model igralište. [Modul 00](../00-quick-start/README.md)

**In-Memory Testing** - Testiranje s pohranom u memoriji.

**Integration Testing** - Testiranje s pravom infrastrukturom.

**Maven** - Java alat za automatizaciju izgradnje.

**Mockito** - Java framework za mockiranje.

**Spring Boot** - Java aplikacijski okvir. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Odricanje od odgovornosti**:
Ovaj dokument preveden je korištenjem AI usluge za prijevod [Co-op Translator](https://github.com/Azure/co-op-translator). Iako nastojimo biti precizni, imajte na umu da automatski prijevodi mogu sadržavati pogreške ili netočnosti. Izvorni dokument na izvornom jeziku treba smatrati autoritativnim izvorom. Za kritične informacije preporučuje se profesionalni prijevod koji izvodi čovjek. Ne odgovaramo za bilo kakve nesporazume ili pogrešna tumačenja proizašla iz korištenja ovog prijevoda.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->