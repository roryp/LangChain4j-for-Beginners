<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "5d497142c580b4f2bb6f4f314af8ccee",
  "translation_date": "2025-12-13T20:07:58+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "th"
}
-->
# LangChain4j คำศัพท์

## สารบัญ

- [แนวคิดหลัก](../../../docs)
- [ส่วนประกอบของ LangChain4j](../../../docs)
- [แนวคิด AI/ML](../../../docs)
- [การออกแบบ Prompt](../../../docs)
- [RAG (Retrieval-Augmented Generation)](../../../docs)
- [เอเจนต์และเครื่องมือ](../../../docs)
- [โปรโตคอลบริบทของโมเดล (MCP)](../../../docs)
- [บริการ Azure](../../../docs)
- [การทดสอบและการพัฒนา](../../../docs)

อ้างอิงอย่างรวดเร็วสำหรับคำศัพท์และแนวคิดที่ใช้ตลอดหลักสูตร

## แนวคิดหลัก

**AI Agent** - ระบบที่ใช้ AI ในการให้เหตุผลและทำงานโดยอิสระ [Module 04](../04-tools/README.md)

**Chain** - ลำดับของการดำเนินการที่ผลลัพธ์ป้อนเข้าสู่ขั้นตอนถัดไป

**Chunking** - การแบ่งเอกสารออกเป็นชิ้นเล็ก ๆ ปกติ: 300-500 โทเค็นพร้อมการทับซ้อน [Module 03](../03-rag/README.md)

**Context Window** - จำนวนโทเค็นสูงสุดที่โมเดลสามารถประมวลผลได้ GPT-5: 400K โทเค็น

**Embeddings** - เวกเตอร์เชิงตัวเลขที่แทนความหมายของข้อความ [Module 03](../03-rag/README.md)

**Function Calling** - โมเดลสร้างคำขอที่มีโครงสร้างเพื่อเรียกฟังก์ชันภายนอก [Module 04](../04-tools/README.md)

**Hallucination** - เมื่อโมเดลสร้างข้อมูลที่ไม่ถูกต้องแต่ดูสมเหตุสมผล

**Prompt** - ข้อความป้อนเข้าสู่โมเดลภาษา [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - การค้นหาตามความหมายโดยใช้ embeddings แทนคำสำคัญ [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ไม่มีหน่วยความจำ Stateful: รักษาประวัติการสนทนา [Module 01](../01-introduction/README.md)

**Tokens** - หน่วยข้อความพื้นฐานที่โมเดลประมวลผล มีผลต่อค่าใช้จ่ายและข้อจำกัด [Module 01](../01-introduction/README.md)

**Tool Chaining** - การเรียกใช้เครื่องมือแบบลำดับที่ผลลัพธ์แจ้งการเรียกถัดไป [Module 04](../04-tools/README.md)

## ส่วนประกอบของ LangChain4j

**AiServices** - สร้างอินเทอร์เฟซบริการ AI ที่ปลอดภัยตามประเภท

**OpenAiOfficialChatModel** - ลูกค้ารวมสำหรับโมเดล OpenAI และ Azure OpenAI

**OpenAiOfficialEmbeddingModel** - สร้าง embeddings โดยใช้ลูกค้า OpenAI Official (รองรับทั้ง OpenAI และ Azure OpenAI)

**ChatModel** - อินเทอร์เฟซหลักสำหรับโมเดลภาษา

**ChatMemory** - รักษาประวัติการสนทนา

**ContentRetriever** - ค้นหาชิ้นเอกสารที่เกี่ยวข้องสำหรับ RAG

**DocumentSplitter** - แบ่งเอกสารออกเป็นชิ้น

**EmbeddingModel** - แปลงข้อความเป็นเวกเตอร์เชิงตัวเลข

**EmbeddingStore** - จัดเก็บและดึง embeddings

**MessageWindowChatMemory** - รักษาหน้าต่างเลื่อนของข้อความล่าสุด

**PromptTemplate** - สร้าง prompt ที่ใช้ซ้ำได้พร้อมตัวแปร `{{variable}}`

**TextSegment** - ชิ้นข้อความพร้อมเมตาดาต้า ใช้ใน RAG

**ToolExecutionRequest** - แทนคำขอการเรียกใช้เครื่องมือ

**UserMessage / AiMessage / SystemMessage** - ประเภทข้อความในการสนทนา

## แนวคิด AI/ML

**Few-Shot Learning** - การให้ตัวอย่างใน prompt [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - โมเดล AI ที่ฝึกด้วยข้อมูลข้อความจำนวนมาก

**Reasoning Effort** - พารามิเตอร์ GPT-5 ที่ควบคุมความลึกของการคิด [Module 02](../02-prompt-engineering/README.md)

**Temperature** - ควบคุมความสุ่มของผลลัพธ์ ต่ำ=กำหนดได้สูง=สร้างสรรค์

**Vector Database** - ฐานข้อมูลเฉพาะสำหรับ embeddings [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - การทำงานโดยไม่มีตัวอย่าง [Module 02](../02-prompt-engineering/README.md)

## การออกแบบ Prompt - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - การให้เหตุผลทีละขั้นตอนเพื่อความแม่นยำที่ดีขึ้น

**Constrained Output** - บังคับรูปแบบหรือโครงสร้างเฉพาะ

**High Eagerness** - รูปแบบ GPT-5 สำหรับการให้เหตุผลอย่างละเอียด

**Low Eagerness** - รูปแบบ GPT-5 สำหรับคำตอบที่รวดเร็ว

**Multi-Turn Conversation** - รักษาบริบทข้ามการแลกเปลี่ยน

**Role-Based Prompting** - กำหนดบุคลิกโมเดลผ่านข้อความระบบ

**Self-Reflection** - โมเดลประเมินและปรับปรุงผลลัพธ์ของตนเอง

**Structured Analysis** - กรอบการประเมินที่กำหนดไว้ล่วงหน้า

**Task Execution Pattern** - วางแผน → ดำเนินการ → สรุป

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - โหลด → แบ่งชิ้น → สร้าง embeddings → จัดเก็บ

**In-Memory Embedding Store** - ที่เก็บชั่วคราวสำหรับการทดสอบ

**RAG** - ผสมผสานการดึงข้อมูลกับการสร้างเพื่อยึดโยงคำตอบ

**Similarity Score** - มาตรวัด (0-1) ของความคล้ายเชิงความหมาย

**Source Reference** - เมตาดาต้าเกี่ยวกับเนื้อหาที่ดึงมา

## เอเจนต์และเครื่องมือ - [Module 04](../04-tools/README.md)

**@Tool Annotation** - ทำเครื่องหมายเมธอด Java เป็นเครื่องมือที่ AI เรียกใช้ได้

**ReAct Pattern** - ให้เหตุผล → ลงมือ → สังเกต → ทำซ้ำ

**Session Management** - บริบทแยกสำหรับผู้ใช้แต่ละคน

**Tool** - ฟังก์ชันที่เอเจนต์ AI สามารถเรียกใช้

**Tool Description** - เอกสารอธิบายวัตถุประสงค์และพารามิเตอร์ของเครื่องมือ

## โปรโตคอลบริบทของโมเดล (MCP) - [Module 05](../05-mcp/README.md)

**Docker Transport** - เซิร์ฟเวอร์ MCP ในคอนเทนเนอร์ Docker

**MCP** - มาตรฐานสำหรับเชื่อมต่อแอป AI กับเครื่องมือภายนอก

**MCP Client** - แอปพลิเคชันที่เชื่อมต่อกับเซิร์ฟเวอร์ MCP

**MCP Server** - บริการที่เปิดเผยเครื่องมือผ่าน MCP

**Server-Sent Events (SSE)** - การสตรีมจากเซิร์ฟเวอร์ไปยังไคลเอนต์ผ่าน HTTP

**Stdio Transport** - เซิร์ฟเวอร์เป็น subprocess ผ่าน stdin/stdout

**Streamable HTTP Transport** - HTTP พร้อม SSE สำหรับการสื่อสารแบบเรียลไทม์

**Tool Discovery** - ไคลเอนต์สอบถามเซิร์ฟเวอร์เพื่อหาเครื่องมือที่มี

## บริการ Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - การค้นหาในคลาวด์พร้อมความสามารถเวกเตอร์ [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - ใช้ปรับใช้ทรัพยากร Azure

**Azure OpenAI** - บริการ AI สำหรับองค์กรของ Microsoft

**Bicep** - ภาษาโครงสร้างพื้นฐานเป็นโค้ดของ Azure [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - ชื่อสำหรับการปรับใช้โมเดลใน Azure

**GPT-5** - โมเดล OpenAI ล่าสุดที่ควบคุมการให้เหตุผลได้ [Module 02](../02-prompt-engineering/README.md)

## การทดสอบและการพัฒนา - [Testing Guide](TESTING.md)

**Dev Container** - สภาพแวดล้อมพัฒนาที่บรรจุในคอนเทนเนอร์ [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - สนามเด็กเล่นโมเดล AI ฟรี [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - การทดสอบด้วยที่เก็บข้อมูลในหน่วยความจำ

**Integration Testing** - การทดสอบกับโครงสร้างพื้นฐานจริง

**Maven** - เครื่องมืออัตโนมัติสำหรับการสร้าง Java

**Mockito** - เฟรมเวิร์กสำหรับการจำลองใน Java

**Spring Boot** - เฟรมเวิร์กแอปพลิเคชัน Java [Module 01](../01-introduction/README.md)

**Testcontainers** - คอนเทนเนอร์ Docker ในการทดสอบ

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**ข้อจำกัดความรับผิดชอบ**:  
เอกสารนี้ได้รับการแปลโดยใช้บริการแปลภาษาอัตโนมัติ [Co-op Translator](https://github.com/Azure/co-op-translator) แม้เราจะพยายามให้ความถูกต้องสูงสุด แต่โปรดทราบว่าการแปลอัตโนมัติอาจมีข้อผิดพลาดหรือความไม่ถูกต้อง เอกสารต้นฉบับในภาษาต้นทางถือเป็นแหล่งข้อมูลที่เชื่อถือได้ สำหรับข้อมูลที่สำคัญ ขอแนะนำให้ใช้บริการแปลโดยผู้เชี่ยวชาญมนุษย์ เราไม่รับผิดชอบต่อความเข้าใจผิดหรือการตีความผิดใด ๆ ที่เกิดจากการใช้การแปลนี้
<!-- CO-OP TRANSLATOR DISCLAIMER END -->