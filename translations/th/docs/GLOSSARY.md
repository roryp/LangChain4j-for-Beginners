<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "0c4ed0dd4b9db1aa5d6ac7cfd0c79ca4",
  "translation_date": "2026-01-06T08:10:07+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "th"
}
-->
# พจนานุกรม LangChain4j

## สารบัญ

- [แนวคิดหลัก](../../../docs)
- [ส่วนประกอบของ LangChain4j](../../../docs)
- [แนวคิด AI/ML](../../../docs)
- [เส้นป้องกัน](../../../docs)
- [วิศวกรรมพรอมต์](../../../docs)
- [RAG (การสร้างโดยเสริมการค้นคืน)](../../../docs)
- [เอเจนต์และเครื่องมือ](../../../docs)
- [โมดูล Agentic](../../../docs)
- [โปรโตคอลบริบทของโมเดล (MCP)](../../../docs)
- [บริการ Azure](../../../docs)
- [การทดสอบและการพัฒนา](../../../docs)

อ้างอิงแบบรวดเร็วสำหรับคำและแนวคิดที่ใช้ตลอดคอร์สนี้

## แนวคิดหลัก

**AI Agent** - ระบบที่ใช้ AI ในการเหตุผลและทำงานโดยอัตโนมัติ [Module 04](../04-tools/README.md)

**Chain** - ลำดับของการทำงานที่ผลลัพธ์ถูกส่งต่อไปยังขั้นตอนถัดไป

**Chunking** - การแยกเอกสารเป็นชิ้นส่วนเล็กลง ปกติ: 300-500 โทเค็นพร้อมทับซ้อนกัน [Module 03](../03-rag/README.md)

**Context Window** - จำนวนโทเค็นสูงสุดที่โมเดลสามารถประมวลผลได้ GPT-5: 400K โทเค็น

**Embeddings** - เวกเตอร์ตัวเลขที่แทนความหมายของข้อความ [Module 03](../03-rag/README.md)

**Function Calling** - โมเดลสร้างคำร้องแบบมีโครงสร้างเพื่อเรียกฟังก์ชันภายนอก [Module 04](../04-tools/README.md)

**Hallucination** - เมื่อโมเดลสร้างข้อมูลที่ไม่ถูกต้องแต่ดูสมเหตุสมผล

**Prompt** - ข้อความอินพุตสำหรับโมเดลภาษา [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - การค้นหาด้วยความหมายโดยใช้ embeddings แทนคำค้นหา [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ไม่มีหน่วยความจำ Stateful: รักษาประวัติการสนทนาไว้ [Module 01](../01-introduction/README.md)

**Tokens** - หน่วยพื้นฐานของข้อความที่โมเดลประมวลผล ส่งผลต่อค่าใช้จ่ายและข้อจำกัด [Module 01](../01-introduction/README.md)

**Tool Chaining** - การเรียกใช้เครื่องมือแบบลำดับโดยผลลัพธ์ไปแจ้งเรียกครั้งถัดไป [Module 04](../04-tools/README.md)

## ส่วนประกอบของ LangChain4j

**AiServices** - สร้างอินเทอร์เฟซบริการ AI แบบ type-safe

**OpenAiOfficialChatModel** - ลูกค้ารวมสำหรับโมเดล OpenAI และ Azure OpenAI

**OpenAiOfficialEmbeddingModel** - สร้าง embeddings โดยใช้ลูกค้า OpenAI Official (รองรับทั้ง OpenAI และ Azure OpenAI)

**ChatModel** - อินเทอร์เฟซหลักสำหรับโมเดลภาษา

**ChatMemory** - รักษาประวัติการสนทนา

**ContentRetriever** - ค้นหาชิ้นเอกสารที่เกี่ยวข้องสำหรับ RAG

**DocumentSplitter** - แยกเอกสารเป็นส่วนย่อย

**EmbeddingModel** - แปลงข้อความเป็นเวกเตอร์ตัวเลข

**EmbeddingStore** - เก็บและดึง embeddings

**MessageWindowChatMemory** - รักษาหน้าต่างเลื่อนของข้อความล่าสุด

**PromptTemplate** - สร้างพรอมต์ที่ใช้ซ้ำได้โดยมีตัวแปร `{{variable}}`

**TextSegment** - ชิ้นข้อความที่มีเมตาดาต้า ใช้ใน RAG

**ToolExecutionRequest** - ตัวแทนคำขอเรียกใช้งานเครื่องมือ

**UserMessage / AiMessage / SystemMessage** - ประเภทข้อความในการสนทนา

## แนวคิด AI/ML

**Few-Shot Learning** - การให้ตัวอย่างในพรอมต์ [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - โมเดล AI ที่ถูกฝึกด้วยข้อมูลข้อความจำนวนมาก

**Reasoning Effort** - พารามิเตอร์ GPT-5 ที่ควบคุมความลึกของการคิด [Module 02](../02-prompt-engineering/README.md)

**Temperature** - ควบคุมความสุ่มของผลลัพธ์ ต่ำ=ผลชัดเจนสูง=สร้างสรรค์

**Vector Database** - ฐานข้อมูลเฉพาะสำหรับ embeddings [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - ทำงานโดยไม่ใช้ตัวอย่าง [Module 02](../02-prompt-engineering/README.md)

## เส้นป้องกัน - [Module 00](../00-quick-start/README.md)

**Defense in Depth** - แนวทางรักษาความปลอดภัยหลายชั้นโดยผสมผสานเส้นป้องกันในแอปและตัวกรองความปลอดภัยของผู้ให้บริการ

**Hard Block** - ผู้ให้บริการตอบ HTTP 400 สำหรับเนื้อหาที่ผิดร้ายแรง

**InputGuardrail** - อินเทอร์เฟซ LangChain4j สำหรับตรวจสอบความถูกต้องของข้อมูลผู้ใช้ก่อนถึง LLM ประหยัดต้นทุนและเวลาโดยบล็อกพรอมต์ที่เป็นอันตรายตั้งแต่ต้น

**InputGuardrailResult** - ประเภทผลลัพธ์ของการตรวจสอบเส้นป้องกัน: `success()` หรือ `fatal("เหตุผล")`

**OutputGuardrail** - อินเทอร์เฟซตรวจสอบความถูกต้องของคำตอบ AI ก่อนส่งกลับผู้ใช้

**Provider Safety Filters** - ตัวกรองเนื้อหาที่ติดตั้งโดยผู้ให้บริการ AI (เช่น GitHub Models) ที่จับการละเมิดระดับ API

**Soft Refusal** - โมเดลปฏิเสธตอบอย่างสุภาพโดยไม่ส่งข้อผิดพลาด

## วิศวกรรมพรอมต์ - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - การเหตุผลทีละขั้นตอนเพื่อความแม่นยำมากขึ้น

**Constrained Output** - บังคับรูปแบบหรือโครงสร้างเฉพาะ

**High Eagerness** - รูปแบบ GPT-5 สำหรับการคิดละเอียด

**Low Eagerness** - รูปแบบ GPT-5 สำหรับตอบเร็ว

**Multi-Turn Conversation** - รักษาบริบทข้ามการสนทนา

**Role-Based Prompting** - กำหนดบุคลิกโมเดลผ่านข้อความระบบ

**Self-Reflection** - โมเดลประเมินและปรับปรุงผลลัพธ์ตัวเอง

**Structured Analysis** - กรอบการประเมินที่คงที่

**Task Execution Pattern** - วางแผน → ดำเนินการ → สรุป

## RAG (การสร้างโดยเสริมการค้นคืน) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - โหลด → แยก → ฝัง → เก็บ

**In-Memory Embedding Store** - ที่เก็บแบบไม่ถาวรสำหรับทดสอบ

**RAG** - รวมการค้นคืนกับการสร้างเพื่อตั้งอยู่บนข้อมูลจริง

**Similarity Score** - ค่าวัด (0-1) ความเหมือนทางความหมาย

**Source Reference** - เมตาดาต้าของเนื้อหาที่ค้นคืน

## เอเจนต์และเครื่องมือ - [Module 04](../04-tools/README.md)

**@Tool Annotation** - ทำเครื่องหมายเมธอด Java เป็นเครื่องมือที่ AI เรียกใช้ได้

**ReAct Pattern** - เหตุผล → ลงมือ → สังเกต → ทำซ้ำ

**Session Management** - แยกบริบทสำหรับผู้ใช้แต่ละคน

**Tool** - ฟังก์ชันที่ AI agent สามารถเรียกใช้

**Tool Description** - เอกสารวัตถุประสงค์และพารามิเตอร์เครื่องมือ

## โมดูล Agentic - [Module 05](../05-mcp/README.md)

**@Agent Annotation** - ทำเครื่องหมายอินเทอร์เฟซเป็น AI agent พร้อมนิยามพฤติกรรมแบบประกาศ

**Agent Listener** - ตะขอสำหรับติดตามการทำงาน agent ผ่าน `beforeAgentInvocation()` และ `afterAgentInvocation()`

**Agentic Scope** - หน่วยความจำแชร์ที่เอเจนต์เก็บผลลัพธ์ใช้ `outputKey` สำหรับเอเจนต์ถัดไป

**AgenticServices** - โรงงานสำหรับสร้างเอเจนต์โดยใช้ `agentBuilder()` และ `supervisorBuilder()`

**Conditional Workflow** - เส้นทางตามเงื่อนไขไปยังเอเจนต์เฉพาะทางต่าง ๆ

**Human-in-the-Loop** - รูปแบบเวิร์กโฟลว์เพิ่มจุดตรวจสอบโดยมนุษย์เพื่ออนุมัติหรือทบทวนเนื้อหา

**langchain4j-agentic** - ไลบรารี Maven สำหรับสร้าง agent แบบประกาศ (ทดลอง)

**Loop Workflow** - ทำซ้ำการทำงานเอเจนต์จนกว่าจะตรงเงื่อนไข (เช่น คะแนนคุณภาพ ≥ 0.8)

**outputKey** - พารามิเตอร์คำอธิบายเอเจนต์ที่ระบุที่เก็บผลลัพธ์ใน Agentic Scope

**Parallel Workflow** - รันเอเจนต์หลายตัวพร้อมกันเพื่อทำงานอิสระ

**Response Strategy** - วิธีที่ผู้ควบคุมตอบคำถามสุดท้าย: LAST, SUMMARY หรือ SCORED

**Sequential Workflow** - ดำเนินการเอเจนต์ตามลำดับโดยผลลัพธ์ไหลไปยังขั้นตอนถัดไป

**Supervisor Agent Pattern** - รูปแบบ agentic ขั้นสูงที่ผู้ควบคุม LLM ตัดสินใจเรียกใช้เอเจนต์ย่อยแบบไดนามิก

## โปรโตคอลบริบทของโมเดล (MCP) - [Module 05](../05-mcp/README.md)

**langchain4j-mcp** - ไลบรารี Maven สำหรับผนวกรวม MCP ใน LangChain4j

**MCP** - Model Context Protocol: มาตรฐานเชื่อมต่อแอป AI กับเครื่องมือภายนอก สร้างครั้งเดียว ใช้ได้ทุกที่

**MCP Client** - แอปที่เชื่อมต่อกับ MCP เซิร์ฟเวอร์เพื่อค้นหาและใช้เครื่องมือ

**MCP Server** - บริการเปิดเผยเครื่องมือผ่าน MCP พร้อมคำอธิบายและสคีมา

**McpToolProvider** - ส่วนประกอบ LangChain4j ที่ครอบ MCP เครื่องมือเพื่อใช้ในบริการ AI และเอเจนต์

**McpTransport** - อินเทอร์เฟซสำหรับสื่อสาร MCP มีการใช้งานเช่น Stdio และ HTTP

**Stdio Transport** - การส่งข้อมูลผ่านกระบวนการภายในเครื่องโดย stdin/stdout ใช้สำหรับเข้าถึงไฟล์หรือเครื่องมือบรรทัดคำสั่ง

**StdioMcpTransport** - การใช้งาน LangChain4j ที่สร้าง MCP เซิร์ฟเวอร์เป็น subprocess

**Tool Discovery** - ลูกค้าสอบถามเซิร์ฟเวอร์เพื่อรับเครื่องมือที่มีคำอธิบายและสคีมา

## บริการ Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - บริการค้นหาในคลาวด์ที่รองรับเวกเตอร์ [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - เครื่องมือดีพลอยทรัพยากร Azure

**Azure OpenAI** - บริการ AI สำหรับองค์กรของ Microsoft

**Bicep** - ภาษาเขียนโครงสร้างพื้นฐาน Azure-as-code [คู่มือโครงสร้างพื้นฐาน](../01-introduction/infra/README.md)

**Deployment Name** - ชื่อดีพลอยโมเดลใน Azure

**GPT-5** - โมเดล OpenAI ล่าสุดที่ควบคุมเหตุผลได้ [Module 02](../02-prompt-engineering/README.md)

## การทดสอบและการพัฒนา - [คู่มือการทดสอบ](TESTING.md)

**Dev Container** - สภาพแวดล้อมพัฒนาด้วยคอนเทนเนอร์ [การตั้งค่า](../../../.devcontainer/devcontainer.json)

**GitHub Models** - สนามทดสอบโมเดล AI ฟรี [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - การทดสอบด้วยที่เก็บในหน่วยความจำ

**Integration Testing** - การทดสอบกับโครงสร้างพื้นฐานจริง

**Maven** - เครื่องมืออัตโนมัติสร้าง Java

**Mockito** - เฟรมเวิร์กจำลองใน Java

**Spring Boot** - เฟรมเวิร์กแอปพลิเคชัน Java [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**ข้อจำกัดความรับผิดชอบ**:  
เอกสารฉบับนี้ได้รับการแปลโดยใช้บริการแปลภาษาอัตโนมัติ [Co-op Translator](https://github.com/Azure/co-op-translator) แม้ว่าเราจะพยายามรักษาความถูกต้องให้มากที่สุด แต่โปรดทราบว่าการแปลโดยอัตโนมัติอาจมีข้อผิดพลาดหรือความไม่ถูกต้อง เอกสารต้นฉบับในภาษาต้นทางถือเป็นแหล่งข้อมูลที่เชื่อถือได้ สำหรับข้อมูลที่มีความสำคัญ ทางเราขอแนะนำให้ใช้บริการแปลโดยมนุษย์มืออาชีพ เราไม่รับผิดชอบต่อความเข้าใจผิดหรือการตีความผิดใด ๆ ที่อาจเกิดจากการใช้การแปลฉบับนี้
<!-- CO-OP TRANSLATOR DISCLAIMER END -->