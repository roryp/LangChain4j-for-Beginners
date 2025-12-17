<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:18:59+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ro"
}
-->
# Glosar LangChain4j

## Cuprins

- [Concepte de bază](../../../docs)
- [Componente LangChain4j](../../../docs)
- [Concepte AI/ML](../../../docs)
- [Ingineria Promptului](../../../docs)
- [RAG (Generare augmentată prin recuperare)](../../../docs)
- [Agenți și Unelte](../../../docs)
- [Protocolul Contextului Modelului (MCP)](../../../docs)
- [Servicii Azure](../../../docs)
- [Testare și Dezvoltare](../../../docs)

Referință rapidă pentru termeni și concepte utilizate pe parcursul cursului.

## Concepte de bază

**Agent AI** - Sistem care folosește AI pentru a raționa și acționa autonom. [Modul 04](../04-tools/README.md)

**Lanț** - Secvență de operații unde ieșirea alimentează pasul următor.

**Fragmentare** - Împărțirea documentelor în bucăți mai mici. Tipic: 300-500 tokeni cu suprapunere. [Modul 03](../03-rag/README.md)

**Fereastra de context** - Numărul maxim de tokeni pe care un model îi poate procesa. GPT-5: 400K tokeni.

**Embedding-uri** - Vectori numerici care reprezintă sensul textului. [Modul 03](../03-rag/README.md)

**Apelarea funcțiilor** - Modelul generează cereri structurate pentru a apela funcții externe. [Modul 04](../04-tools/README.md)

**Halucinație** - Când modelele generează informații incorecte, dar plauzibile.

**Prompt** - Text de intrare pentru un model de limbaj. [Modul 02](../02-prompt-engineering/README.md)

**Căutare semantică** - Căutare după sens folosind embedding-uri, nu cuvinte cheie. [Modul 03](../03-rag/README.md)

**Cu stare vs Fără stare** - Fără stare: fără memorie. Cu stare: păstrează istoricul conversației. [Modul 01](../01-introduction/README.md)

**Tokeni** - Unități de bază de text pe care modelele le procesează. Afectează costurile și limitele. [Modul 01](../01-introduction/README.md)

**Lanț de unelte** - Execuție secvențială a uneltelor unde ieșirea informează următorul apel. [Modul 04](../04-tools/README.md)

## Componente LangChain4j

**AiServices** - Creează interfețe de servicii AI tip-safe.

**OpenAiOfficialChatModel** - Client unificat pentru modelele OpenAI și Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Creează embedding-uri folosind clientul oficial OpenAI (suportă atât OpenAI cât și Azure OpenAI).

**ChatModel** - Interfață de bază pentru modelele de limbaj.

**ChatMemory** - Păstrează istoricul conversației.

**ContentRetriever** - Găsește fragmente relevante de documente pentru RAG.

**DocumentSplitter** - Împarte documentele în fragmente.

**EmbeddingModel** - Convertește textul în vectori numerici.

**EmbeddingStore** - Stochează și recuperează embedding-uri.

**MessageWindowChatMemory** - Păstrează o fereastră glisantă cu mesajele recente.

**PromptTemplate** - Creează prompturi reutilizabile cu locuri rezervate `{{variable}}`.

**TextSegment** - Fragment de text cu metadate. Folosit în RAG.

**ToolExecutionRequest** - Reprezintă o cerere de execuție a unei unelte.

**UserMessage / AiMessage / SystemMessage** - Tipuri de mesaje în conversație.

## Concepte AI/ML

**Învățare Few-Shot** - Furnizarea de exemple în prompturi. [Modul 02](../02-prompt-engineering/README.md)

**Model de limbaj mare (LLM)** - Modele AI antrenate pe volume mari de text.

**Efort de raționare** - Parametru GPT-5 care controlează adâncimea gândirii. [Modul 02](../02-prompt-engineering/README.md)

**Temperatură** - Controlează aleatorietatea ieșirii. Mică=determinist, mare=creativ.

**Bază de date vectorială** - Bază de date specializată pentru embedding-uri. [Modul 03](../03-rag/README.md)

**Învățare Zero-Shot** - Realizarea sarcinilor fără exemple. [Modul 02](../02-prompt-engineering/README.md)

## Ingineria Promptului - [Modul 02](../02-prompt-engineering/README.md)

**Lanț de gândire** - Raționament pas cu pas pentru o acuratețe mai bună.

**Ieșire constrânsă** - Impunerea unui format sau structură specifică.

**Entuziasm ridicat** - Tipar GPT-5 pentru raționament amănunțit.

**Entuziasm scăzut** - Tipar GPT-5 pentru răspunsuri rapide.

**Conversație multi-turn** - Menținerea contextului pe parcursul schimburilor.

**Promptare bazată pe rol** - Setarea personajului modelului prin mesaje de sistem.

**Auto-reflecție** - Modelul evaluează și îmbunătățește ieșirea sa.

**Analiză structurată** - Cadru fix de evaluare.

**Tipar de execuție a sarcinii** - Planifică → Execută → Rezumă.

## RAG (Generare augmentată prin recuperare) - [Modul 03](../03-rag/README.md)

**Flux de procesare a documentelor** - Încarcă → fragmentează → încorporează → stochează.

**Stocare embedding în memorie** - Stocare nepermanentă pentru testare.

**RAG** - Combină recuperarea cu generarea pentru a fundamenta răspunsurile.

**Scor de similaritate** - Măsură (0-1) a similarității semantice.

**Referință sursă** - Metadate despre conținutul recuperat.

## Agenți și Unelte - [Modul 04](../04-tools/README.md)

**Anotare @Tool** - Marchează metode Java ca unelte apelabile de AI.

**Tipar ReAct** - Raționează → Acționează → Observă → Repetă.

**Managementul sesiunii** - Contexte separate pentru utilizatori diferiți.

**Unealtă** - Funcție pe care un agent AI o poate apela.

**Descrierea uneltei** - Documentația scopului și parametrilor uneltei.

## Protocolul Contextului Modelului (MCP) - [Modul 05](../05-mcp/README.md)

**Transport Docker** - Server MCP într-un container Docker.

**MCP** - Standard pentru conectarea aplicațiilor AI la unelte externe.

**Client MCP** - Aplicație care se conectează la servere MCP.

**Server MCP** - Serviciu care expune unelte prin MCP.

**Evenimente trimise de server (SSE)** - Streaming server-către-client prin HTTP.

**Transport Stdio** - Server ca subproces prin stdin/stdout.

**Transport HTTP streamabil** - HTTP cu SSE pentru comunicare în timp real.

**Descoperirea uneltelor** - Clientul interoghează serverul pentru uneltele disponibile.

## Servicii Azure - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Căutare în cloud cu capabilități vectoriale. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Deployează resurse Azure.

**Azure OpenAI** - Serviciul AI enterprise al Microsoft.

**Bicep** - Limbaj Azure pentru infrastructură ca cod. [Ghid infrastructură](../01-introduction/infra/README.md)

**Nume de deployment** - Nume pentru deployment-ul modelului în Azure.

**GPT-5** - Cel mai nou model OpenAI cu control al raționamentului. [Modul 02](../02-prompt-engineering/README.md)

## Testare și Dezvoltare - [Ghid de testare](TESTING.md)

**Dev Container** - Mediu de dezvoltare containerizat. [Configurație](../../../.devcontainer/devcontainer.json)

**Modele GitHub** - Playground gratuit pentru modele AI. [Modul 00](../00-quick-start/README.md)

**Testare în memorie** - Testare cu stocare în memorie.

**Testare de integrare** - Testare cu infrastructură reală.

**Maven** - Unealtă de automatizare build Java.

**Mockito** - Framework Java pentru mocking.

**Spring Boot** - Framework Java pentru aplicații. [Modul 01](../01-introduction/README.md)

**Testcontainers** - Containere Docker în teste.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Declinare de responsabilitate**:  
Acest document a fost tradus folosind serviciul de traducere AI [Co-op Translator](https://github.com/Azure/co-op-translator). Deși ne străduim pentru acuratețe, vă rugăm să rețineți că traducerile automate pot conține erori sau inexactități. Documentul original în limba sa nativă trebuie considerat sursa autorizată. Pentru informații critice, se recomandă traducerea profesională realizată de un specialist uman. Nu ne asumăm răspunderea pentru eventualele neînțelegeri sau interpretări greșite rezultate din utilizarea acestei traduceri.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->