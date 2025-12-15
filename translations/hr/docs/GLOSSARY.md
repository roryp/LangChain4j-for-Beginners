<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:21:27+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "hr"
}
-->
# LangChain4j Rječnik

## Sadržaj

- [Osnovni pojmovi](../../../docs)
- [Komponente LangChain4j](../../../docs)
- [AI/ML pojmovi](../../../docs)
- [Inženjering prompta](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [Agenti i alati](../../../docs)
- [Protokol konteksta modela (MCP)](../../../docs)
- [Azure usluge](../../../docs)
- [Testiranje i razvoj](../../../docs)

Brzi pregled pojmova i koncepata korištenih kroz tečaj.

## Osnovni pojmovi

**AI agent** - Sustav koji koristi AI za rezoniranje i autonomno djelovanje. [Modul 04](../04-tools/README.md)

**Lanac** - Niz operacija gdje izlaz služi kao ulaz za sljedeći korak.

**Chunking** - Razbijanje dokumenata na manje dijelove. Tipično: 300-500 tokena s preklapanjem. [Modul 03](../03-rag/README.md)

**Prozor konteksta** - Maksimalan broj tokena koje model može obraditi. GPT-5: 400K tokena.

**Ugrađivanja (Embeddings)** - Numerički vektori koji predstavljaju značenje teksta. [Modul 03](../03-rag/README.md)

**Pozivanje funkcija** - Model generira strukturirane zahtjeve za pozivanje vanjskih funkcija. [Modul 04](../04-tools/README.md)

**Halucinacija** - Kada modeli generiraju netočne, ali uvjerljive informacije.

**Prompt** - Tekstualni ulaz u jezični model. [Modul 02](../02-prompt-engineering/README.md)

**Semantičko pretraživanje** - Pretraživanje po značenju koristeći embeddings, ne ključne riječi. [Modul 03](../03-rag/README.md)

**Stanje s memorijom vs bez memorije** - Bez memorije: nema pamćenja. Sa memorijom: održava povijest razgovora. [Modul 01](../01-introduction/README.md)

**Tokeni** - Osnovne tekstualne jedinice koje modeli obrađuju. Utječu na troškove i ograničenja. [Modul 01](../01-introduction/README.md)

**Lančano korištenje alata** - Sekvencijalno izvršavanje alata gdje izlaz informira sljedeći poziv. [Modul 04](../04-tools/README.md)

## Komponente LangChain4j

**AiServices** - Stvara tip-sigurne AI servisne sučelja.

**OpenAiOfficialChatModel** - Jedinstveni klijent za OpenAI i Azure OpenAI modele.

**OpenAiOfficialEmbeddingModel** - Stvara embeddings koristeći OpenAI Official klijent (podržava OpenAI i Azure OpenAI).

**ChatModel** - Osnovno sučelje za jezične modele.

**ChatMemory** - Održava povijest razgovora.

**ContentRetriever** - Pronalazi relevantne dijelove dokumenata za RAG.

**DocumentSplitter** - Razbija dokumente na dijelove.

**EmbeddingModel** - Pretvara tekst u numeričke vektore.

**EmbeddingStore** - Pohranjuje i dohvaća embeddings.

**MessageWindowChatMemory** - Održava klizni prozor nedavnih poruka.

**PromptTemplate** - Stvara ponovno upotrebljive prompte s `{{variable}}` zamjenskim mjestima.

**TextSegment** - Tekstualni dio s metapodacima. Koristi se u RAG.

**ToolExecutionRequest** - Predstavlja zahtjev za izvršenje alata.

**UserMessage / AiMessage / SystemMessage** - Tipovi poruka u razgovoru.

## AI/ML pojmovi

**Few-Shot učenje** - Davanje primjera u promptima. [Modul 02](../02-prompt-engineering/README.md)

**Veliki jezični model (LLM)** - AI modeli trenirani na ogromnim tekstualnim podacima.

**Napori rezoniranja** - GPT-5 parametar koji kontrolira dubinu razmišljanja. [Modul 02](../02-prompt-engineering/README.md)

**Temperatura** - Kontrolira nasumičnost izlaza. Niska=deterministički, visoka=kreativno.

**Vektorska baza podataka** - Specijalizirana baza za embeddings. [Modul 03](../03-rag/README.md)

**Zero-Shot učenje** - Izvođenje zadataka bez primjera. [Modul 02](../02-prompt-engineering/README.md)

## Inženjering prompta - [Modul 02](../02-prompt-engineering/README.md)

**Lanac razmišljanja** - Korak-po-korak rezoniranje za bolju točnost.

**Ograničeni izlaz** - Nametanje specifičnog formata ili strukture.

**Visoka želja za odgovorom** - GPT-5 obrazac za temeljito rezoniranje.

**Niska želja za odgovorom** - GPT-5 obrazac za brze odgovore.

**Višekratni razgovor** - Održavanje konteksta kroz razmjene.

**Promptiranje temeljeno na ulozi** - Postavljanje modela kao osobe putem sistemskih poruka.

**Samo-refleksija** - Model evaluira i poboljšava svoj izlaz.

**Strukturirana analiza** - Fiksni okvir za evaluaciju.

**Obrazac izvršenja zadatka** - Planiraj → Izvrši → Sažmi.

## RAG (Retrieval-Augmented Generation) - [Modul 03](../03-rag/README.md)

**Procesiranje dokumenata** - Učitaj → razbij → ugradi → pohrani.

**Ugrađivanje u memoriji** - Nepostojana pohrana za testiranje.

**RAG** - Kombinira dohvat i generiranje za utemeljene odgovore.

**Ocjena sličnosti** - Mjera (0-1) semantičke sličnosti.

**Referenca izvora** - Metapodaci o dohvaćenom sadržaju.

## Agenti i alati - [Modul 04](../04-tools/README.md)

**@Tool anotacija** - Označava Java metode kao AI-pozive alata.

**ReAct obrazac** - Razmišljaj → Djeluj → Promatraj → Ponavljaj.

**Upravljanje sesijama** - Odvojeni konteksti za različite korisnike.

**Alat** - Funkcija koju AI agent može pozvati.

**Opis alata** - Dokumentacija svrhe i parametara alata.

## Protokol konteksta modela (MCP) - [Modul 05](../05-mcp/README.md)

**Docker transport** - MCP server u Docker kontejneru.

**MCP** - Standard za povezivanje AI aplikacija s vanjskim alatima.

**MCP klijent** - Aplikacija koja se povezuje na MCP servere.

**MCP server** - Usluga koja izlaže alate putem MCP-a.

**Server-Sent Events (SSE)** - Streaming sa servera na klijenta preko HTTP-a.

**Stdio transport** - Server kao podproces preko stdin/stdout.

**Streamable HTTP transport** - HTTP sa SSE za komunikaciju u stvarnom vremenu.

**Otkrivanje alata** - Klijent upituje server za dostupne alate.

## Azure usluge - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Cloud pretraživanje s vektorskim mogućnostima. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Implementira Azure resurse.

**Azure OpenAI** - Microsoftova enterprise AI usluga.

**Bicep** - Azure jezik za infrastrukturu kao kod. [Vodič za infrastrukturu](../01-introduction/infra/README.md)

**Ime implementacije** - Ime za implementaciju modela u Azureu.

**GPT-5** - Najnoviji OpenAI model s kontrolom rezoniranja. [Modul 02](../02-prompt-engineering/README.md)

## Testiranje i razvoj - [Vodič za testiranje](TESTING.md)

**Dev Container** - Kontejnerizirano razvojno okruženje. [Konfiguracija](../../../.devcontainer/devcontainer.json)

**GitHub modeli** - Besplatno AI model igralište. [Modul 00](../00-quick-start/README.md)

**Testiranje u memoriji** - Testiranje s pohranom u memoriji.

**Integracijsko testiranje** - Testiranje s pravom infrastrukturom.

**Maven** - Alat za automatizaciju izgradnje u Javi.

**Mockito** - Java okvir za izradu lažnih objekata.

**Spring Boot** - Java aplikacijski okvir. [Modul 01](../01-introduction/README.md)

**Testcontainers** - Docker kontejneri u testovima.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Odricanje od odgovornosti**:
Ovaj dokument preveden je pomoću AI usluge za prevođenje [Co-op Translator](https://github.com/Azure/co-op-translator). Iako nastojimo postići točnost, imajte na umu da automatski prijevodi mogu sadržavati pogreške ili netočnosti. Izvorni dokument na izvornom jeziku treba smatrati autoritativnim izvorom. Za kritične informacije preporučuje se profesionalni ljudski prijevod. Ne snosimo odgovornost za bilo kakva nesporazuma ili pogrešna tumačenja koja proizlaze iz korištenja ovog prijevoda.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->