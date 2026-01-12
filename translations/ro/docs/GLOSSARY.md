<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:31:41+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "ro"
}
-->
# Glosar LangChain4j

## Cuprins

- [Concepte de bază](../../../docs)
- [Componente LangChain4j](../../../docs)
- [Concepte AI/ML](../../../docs)
- [Protecții](../../../docs)
- [Inginerie Prompt](../../../docs)
- [RAG (Generare augmentată prin căutare)](../../../docs)
- [Agenți și unelte](../../../docs)
- [Modul Agentic](../../../docs)
- [Protocol Context Model (MCP)](../../../docs)
- [Servicii Azure](../../../docs)
- [Testare și Dezvoltare](../../../docs)

Referință rapidă pentru termeni și concepte folosite pe parcursul cursului.

## Concepte de bază

**Agent AI** - Sistem care utilizează AI pentru a raționa și a acționa autonom. [Modul 04](../04-tools/README.md)

**Lanț** - Secvență de operațiuni unde rezultatul alimentază pasul următor.

**Fragmentare** - Împărțirea documentelor în bucăți mai mici. Tipic: 300-500 tokeni cu suprapunere. [Modul 03](../03-rag/README.md)

**Fereastra de context** - Numărul maxim de tokeni pe care un model îi poate procesa. GPT-5: 400K tokeni.

**Embeddinguri** - Vectori numerici care reprezintă semnificația textului. [Modul 03](../03-rag/README.md)

**Apelarea Funcțiilor** - Modelul generează cereri structurate pentru a apela funcții externe. [Modul 04](../04-tools/README.md)

**Halucinație** - Când modelele generează informații incorecte dar plauzibile.

**Prompt** - Text introdus într-un model lingvistic. [Modul 02](../02-prompt-engineering/README.md)

**Căutare Semantică** - Căutare după semnificație folosind embeddinguri, nu cuvinte cheie. [Modul 03](../03-rag/README.md)

**Cu stare vs fără stare** - Fără stare: fără memorie. Cu stare: păstrează istoricul conversației. [Modul 01](../01-introduction/README.md)

**Tokeni** - Unitățile de bază de text procesate de modele. Afectează costuri și limite. [Modul 01](../01-introduction/README.md)

**Lanț de unelte** - Execuție secvențială a uneltelor unde rezultatul informează următorul apel. [Modul 04](../04-tools/README.md)

## Componente LangChain4j

**AiServices** - Creează interfețe de servicii AI tip-sigure.

**OpenAiOfficialChatModel** - Client unificat pentru modelele OpenAI și Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - Creează embeddinguri folosind clientul oficial OpenAI (suportă OpenAI și Azure OpenAI).

**ChatModel** - Interfață principală pentru modelele lingvistice.

**ChatMemory** - Păstrează istoricul conversației.

**ContentRetriever** - Găsește fragmente de documente relevante pentru RAG.

**DocumentSplitter** - Sparge documentele în fragmente.

**EmbeddingModel** - Convertește text în vectori numerici.

**EmbeddingStore** - Stochează și recuperează embeddinguri.

**MessageWindowChatMemory** - Păstrează o fereastră mobilă cu mesajele recente.

**PromptTemplate** - Creează prompturi reutilizabile cu locuri pentru `{{variable}}`.

**TextSegment** - Fragment de text cu metadate. Folosit în RAG.

**ToolExecutionRequest** - Reprezintă o cerere de execuție a unei unelte.

**UserMessage / AiMessage / SystemMessage** - Tipuri de mesaje din conversație.

## Concepte AI/ML

**Învățare cu puține exemple** - Furnizarea de exemple în prompturi. [Modul 02](../02-prompt-engineering/README.md)

**Model Lingvistic Mare (LLM)** - Modele AI antrenate pe cantități vaste de text.

**Efort de raționare** - Parametru GPT-5 care controlează profunzimea gândirii. [Modul 02](../02-prompt-engineering/README.md)

**Temperatură** - Controlează aleatorietatea ieșirii. Mică=determinist, mare=creativ.

**Bază de date vectorială** - Bază de date specializată pentru embeddinguri. [Modul 03](../03-rag/README.md)

**Învățare fără exemple** - Executarea sarcinilor fără exemple. [Modul 02](../02-prompt-engineering/README.md)

## Protecții - [Modul 00](../00-quick-start/README.md)

**Apărare în adâncime** - Abordare multi-strat de securitate care combină protecții la nivel de aplicație cu filtre de siguranță ale furnizorilor.

**Blocare dură** - Furnizorul returnează eroare HTTP 400 pentru violări grave de conținut.

**InputGuardrail** - Interfață LangChain4j pentru validarea intrării utilizatorului înainte de a ajunge la LLM. Economisește cost și latență prin blocarea devreme a prompturilor dăunătoare.

**InputGuardrailResult** - Tipul returnat pentru validarea protecțiilor: `success()` sau `fatal("motiv")`.

**OutputGuardrail** - Interfață pentru validarea răspunsurilor AI înainte de a fi returnate utilizatorilor.

**Filtrele de Siguranță ale Furnizorilor** - Filtre de conținut integrate oferite de furnizorii AI (ex. GitHub Models) care detectează încălcări la nivel API.

**Refuz blând** - Modelul refuză politicos să răspundă fără a genera o eroare.

## Inginerie Prompt - [Modul 02](../02-prompt-engineering/README.md)

**Lanț de Gândire** - Raționament pas cu pas pentru o acuratețe mai bună.

**Ieșire Constrânsă** - Impunerea unui format sau structură specifică.

**Dor intensă** - Tipar GPT-5 pentru raționament amănunțit.

**Dor redusă** - Tipar GPT-5 pentru răspunsuri rapide.

**Conversație multi-turn** - Menținerea contextului peste schimburile succesive.

**Prompt bazat pe rol** - Setarea personajului modelului prin mesaje de sistem.

**Auto-reflecție** - Modelul evaluează și îmbunătățește ieșirea sa.

**Analiză structurată** - Cadru fix de evaluare.

**Tipar de execuție a sarcinii** - Planifică → Execută → Rezumă.

## RAG (Generare augmentată prin căutare) - [Modul 03](../03-rag/README.md)

**Flux procesare documente** - Încarcă → fragmentare → embedding → stocare.

**Depozit embedding în memorie** - Stocare nepermanentă pentru testare.

**RAG** - Combină căutarea cu generarea pentru a susține răspunsurile.

**Scor de similitudine** - Măsură (0-1) a similitudinii semantice.

**Referință sursă** - Metadate despre conținutul obținut.

## Agenți și unelte - [Modul 04](../04-tools/README.md)

**@Tool Annotation** - Marchează metode Java ca unelte apelabile de AI.

**Tipar ReAct** - Raționează → Acționează → Observă → Repetă.

**Management sesiuni** - Context separat pentru utilizatori diferiți.

**Unealtă** - Funcție pe care un agent AI o poate apela.

**Descriere unealtă** - Documentația scopului și parametrilor uneltei.

## Modul Agentic - [Modul 05](../05-mcp/README.md)

**@Agent Annotation** - Marchează interfețe ca agenți AI cu definiție declarativă a comportamentului.

**Agent Listener** - Punct de monitorizare a execuției agentului prin `beforeAgentInvocation()` și `afterAgentInvocation()`.

**Domeniu Agentic** - Memorie partajată unde agenții stochează ieșiri folosind `outputKey` pentru a fi consumate de agenți ulteriori.

**AgenticServices** - Fabrică pentru crearea agenților folosind `agentBuilder()` și `supervisorBuilder()`.

**Flux condiționat** - Rutare pe baza condițiilor către agenți specialiști diferiți.

**Om în circuit** - Tipar de flux adăugând puncte de control uman pentru aprobare sau revizuire de conținut.

**langchain4j-agentic** - Dependență Maven pentru construcția declarativă a agenților (experimental).

**Flux ciclic** - Iterează execuția agentului până la îndeplinirea unei condiții (ex. scor calitate ≥ 0.8).

**outputKey** - Parametru de adnotare agent care specifică unde sunt stocate rezultatele în Domeniul Agentic.

**Flux paralel** - Rulează mai mulți agenți simultan pentru sarcini independente.

**Strategie de răspuns** - Cum formulează supraveghetorul răspunsul final: LAST, SUMMARY sau SCORED.

**Flux secvențial** - Execută agenții în ordine unde ieșirea curge către pasul următor.

**Tipar agent supervisor** - Tipar agentic avansat unde un LLM supraveghetor decide dinamic ce sub-agenți să invoce.

## Protocol Context Model (MCP) - [Modul 05](../05-mcp/README.md)

**langchain4j-mcp** - Dependență Maven pentru integrarea MCP în LangChain4j.

**MCP** - Protocol Context Model: standard pentru conectarea aplicațiilor AI la unelte externe. Construiește o dată, folosește oriunde.

**Client MCP** - Aplicație care se conectează la servere MCP pentru a descoperi și folosi unelte.

**Server MCP** - Serviciu care expune unelte prin MCP cu descrieri clare și scheme de parametri.

**McpToolProvider** - Componentă LangChain4j care împachetează uneltele MCP pentru utilizare în servicii AI și agenți.

**McpTransport** - Interfață pentru comunicare MCP. Implementări includ Stdio și HTTP.

**Transport Stdio** - Transport local prin stdin/stdout. Util pentru acces la sistemul de fișiere sau unelte CLI.

**StdioMcpTransport** - Implementare LangChain4j care pornește server MCP ca proces copil.

**Descoperire Unelte** - Clientul interoghează serverul pentru uneltele disponibile cu descrieri și scheme.

## Servicii Azure - [Modul 01](../01-introduction/README.md)

**Azure AI Search** - Căutare cloud cu capabilități vectoriale. [Modul 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - Deploiază resurse Azure.

**Azure OpenAI** - Serviciul AI enterprise al Microsoft.

**Bicep** - Limbaj Azure pentru infrastructură ca cod. [Ghid Infrastructură](../01-introduction/infra/README.md)

**Numele Deployment-ului** - Nume pentru implementarea modelului în Azure.

**GPT-5** - Cel mai nou model OpenAI cu control al raționamentului. [Modul 02](../02-prompt-engineering/README.md)

## Testare și Dezvoltare - [Ghid de testare](TESTING.md)

**Dev Container** - Mediu de dezvoltare containerizat. [Configurație](../../../.devcontainer/devcontainer.json)

**GitHub Models** - Platformă gratuită de modele AI. [Modul 00](../00-quick-start/README.md)

**Testare în memorie** - Testare cu stocare în memorie.

**Testare de integrare** - Testare cu infrastructură reală.

**Maven** - Unealtă de automatizare a construcției Java.

**Mockito** - Framework Java pentru simulări.

**Spring Boot** - Framework Java pentru aplicații. [Modul 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Declinare de responsabilitate**:
Acest document a fost tradus folosind serviciul de traducere AI [Co-op Translator](https://github.com/Azure/co-op-translator). Deși ne străduim pentru acuratețe, vă rugăm să rețineți că traducerile automate pot conține erori sau inexactități. Documentul original în limba sa nativă trebuie considerat sursa oficială. Pentru informații critice, se recomandă traducerea profesională realizată de un specialist uman. Nu ne asumăm responsabilitatea pentru eventualele neînțelegeri sau interpretări greșite rezultate din utilizarea acestei traduceri.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->