<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "52815c169081c357fd1cec7b260f37e4",
  "translation_date": "2025-12-31T01:07:25+00:00",
  "source_file": "docs/GLOSSARY.md",
  "language_code": "th"
}
-->
# LangChain4j คำศัพท์

## สารบัญ

- [แนวคิดหลัก](../../../docs)
- [ส่วนประกอบของ LangChain4j](../../../docs)
- [แนวคิด AI/ML](../../../docs)
- [การออกแบบพรอมต์ --- Module 02](../../../docs)
- [RAG (การสร้างที่เสริมด้วยการดึงข้อมูล) --- Module 03](../../../docs)
- [เอเยนต์และเครื่องมือ --- Module 04](../../../docs)
- [โปรโตคอลบริบทของโมเดล (MCP) --- Module 05](../../../docs)
- [บริการของ Azure --- Module 01](../../../docs)
- [การทดสอบและพัฒนา --- Testing Guide](../../../docs)

อ้างอิงด่วนสำหรับศัพท์และแนวคิดที่ใช้ตลอดหลักสูตร

## แนวคิดหลัก

**AI Agent** - ระบบที่ใช้ AI ในการให้เหตุผลและทำงานอย่างอิสระ. [Module 04](../04-tools/README.md)

**Chain** - ลำดับของการดำเนินการที่ผลลัพธ์จากขั้นตอนหนึ่งถูกป้อนเข้าเป็นอินพุตของขั้นตอนถัดไป.

**Chunking** - การแบ่งเอกสารออกเป็นชิ้นเล็กลง โดยทั่วไป: 300–500 โทเค็นพร้อมการซ้อนทับ. [Module 03](../03-rag/README.md)

**Context Window** - จำนวนโทเค็นสูงสุดที่โมเดลสามารถประมวลผลได้. GPT-5: 400K โทเค็น.

**Embeddings** - เวกเตอร์เชิงตัวเลขที่แทนความหมายของข้อความ. [Module 03](../03-rag/README.md)

**Function Calling** - โมเดลสร้างคำขอแบบมีโครงสร้างเพื่อนำไปเรียกฟังก์ชันภายนอก. [Module 04](../04-tools/README.md)

**Hallucination** - กรณีที่โมเดลสร้างข้อมูลที่ไม่ถูกต้องแต่ฟังดูเป็นไปได้.

**Prompt** - ข้อความอินพุตสำหรับโมเดลภาษา. [Module 02](../02-prompt-engineering/README.md)

**Semantic Search** - การค้นหาตามความหมายโดยใช้ embeddings ไม่ใช่คำค้นหาแบบคำสำคัญ. [Module 03](../03-rag/README.md)

**Stateful vs Stateless** - Stateless: ไม่มีความจำ. Stateful: เก็บประวัติการสนทนา. [Module 01](../01-introduction/README.md)

**Tokens** - หน่วยข้อความพื้นฐานที่โมเดลประมวลผล มีผลต่อค่าใช้จ่ายและข้อจำกัด. [Module 01](../01-introduction/README.md)

**Tool Chaining** - การเรียกใช้งานเครื่องมือแบบต่อเนื่องที่เอาผลลัพธ์จากเครื่องมือก่อนหน้าไปเป็นข้อมูลสำหรับการเรียกครั้งถัดไป. [Module 04](../04-tools/README.md)

## ส่วนประกอบของ LangChain4j

**AiServices** - สร้างอินเทอร์เฟซบริการ AI แบบ type-safe.

**OpenAiOfficialChatModel** - ไคลเอนต์รวมสำหรับโมเดล OpenAI และ Azure OpenAI.

**OpenAiOfficialEmbeddingModel** - สร้าง embeddings โดยใช้ไคลเอนต์ OpenAI Official (รองรับทั้ง OpenAI และ Azure OpenAI).

**ChatModel** - อินเทอร์เฟซหลักสำหรับโมเดลภาษา.

**ChatMemory** - รักษาประวัติการสนทนา.

**ContentRetriever** - ค้นหาชิ้นเอกสารที่เกี่ยวข้องสำหรับ RAG.

**DocumentSplitter** - แบ่งเอกสารเป็นชิ้นย่อย.

**EmbeddingModel** - แปลงข้อความเป็นเวกเตอร์เชิงตัวเลข.

**EmbeddingStore** - เก็บและดึง embeddings.

**MessageWindowChatMemory** - รักษาหน้าต่างเลื่อนของข้อความล่าสุด.

**PromptTemplate** - สร้างพรอมต์ที่นำกลับมาใช้ใหม่ได้พร้อมที่ว่าง `{{variable}}`.

**TextSegment** - ชิ้นข้อความพร้อมเมตาดาต้า ใช้ใน RAG.

**ToolExecutionRequest** - แทนคำขอสำหรับการรันเครื่องมือ.

**UserMessage / AiMessage / SystemMessage** - ประเภทข้อความในการสนทนา.

## แนวคิด AI/ML

**Few-Shot Learning** - การให้ตัวอย่างในพรอมต์. [Module 02](../02-prompt-engineering/README.md)

**Large Language Model (LLM)** - โมเดล AI ที่ฝึกด้วยข้อมูลข้อความจำนวนมาก.

**Reasoning Effort** - พารามิเตอร์ของ GPT-5 ที่ควบคุมความลึกของการให้เหตุผล. [Module 02](../02-prompt-engineering/README.md)

**Temperature** - ควบคุมความสุ่มของผลลัพธ์. ต่ำ=ผลลัพธ์แน่นอน, สูง=สร้างสรรค์.

**Vector Database** - ฐานข้อมูลเฉพาะสำหรับ embeddings. [Module 03](../03-rag/README.md)

**Zero-Shot Learning** - การทำงานโดยไม่มีตัวอย่าง. [Module 02](../02-prompt-engineering/README.md)

## การออกแบบพรอมต์ - [Module 02](../02-prompt-engineering/README.md)

**Chain-of-Thought** - การให้เหตุผลเป็นขั้นตอนเพื่อความแม่นยำที่ดีขึ้น.

**Constrained Output** - การบังคับรูปแบบหรือโครงสร้างเฉพาะของผลลัพธ์.

**High Eagerness** - รูปแบบ GPT-5 สำหรับการให้เหตุผลอย่างละเอียดถี่ถ้วน.

**Low Eagerness** - รูปแบบ GPT-5 สำหรับคำตอบที่รวดเร็ว.

**Multi-Turn Conversation** - การรักษาบริบทข้ามการแลกเปลี่ยนหลายครั้ง.

**Role-Based Prompting** - การกำหนดบุคลิกของโมเดลผ่านข้อความระบบ.

**Self-Reflection** - โมเดลประเมินและปรับปรุงผลลัพธ์ของตัวเอง.

**Structured Analysis** - กรอบการประเมินที่มีโครงสร้างตายตัว.

**Task Execution Pattern** - วางแผน → ดำเนิน → สรุป.

## RAG (Retrieval-Augmented Generation) - [Module 03](../03-rag/README.md)

**Document Processing Pipeline** - โหลด → แบ่งชิ้น → สร้าง embeddings → เก็บ.

**In-Memory Embedding Store** - ที่เก็บ embeddings ชั่วคราวในหน่วยความจำสำหรับการทดสอบ.

**RAG** - ผสมผสานการดึงข้อมูลเข้ากับการสร้างข้อความเพื่อให้คำตอบมีแหล่งอ้างอิง.

**Similarity Score** - มาตรวัดความคล้ายเชิงความหมาย (0-1).

**Source Reference** - เมตาดาต้าของเนื้อหาที่ถูกดึงมา.

## เอเยนต์และเครื่องมือ - [Module 04](../04-tools/README.md)

**@Tool Annotation** - ทำเครื่องหมายเมธอด Java ให้เรียกใช้เป็นเครื่องมือโดย AI ได้.

**ReAct Pattern** - ให้เหตุผล → ลงมือ → สังเกต → ทำซ้ำ.

**Session Management** - แยกบริบทสำหรับผู้ใช้แต่ละคน.

**Tool** - ฟังก์ชันที่เอเยนต์ AI สามารถเรียกใช้ได้.

**Tool Description** - เอกสารอธิบายวัตถุประสงค์และพารามิเตอร์ของเครื่องมือ.

## โปรโตคอลบริบทของโมเดล (MCP) - [Module 05](../05-mcp/README.md)

**MCP** - มาตรฐานสำหรับเชื่อมแอป AI เข้ากับเครื่องมือภายนอก.

**MCP Client** - แอปพลิเคชันที่เชื่อมต่อไปยังเซิร์ฟเวอร์ MCP.

**MCP Server** - บริการที่เผยเครื่องมือผ่าน MCP.

**Stdio Transport** - เซิร์ฟเวอร์ทำงานเป็น subprocess ผ่าน stdin/stdout.

**Tool Discovery** - ไคลเอนต์สืบค้นเซิร์ฟเวอร์เพื่อหาว่าเครื่องมือใดพร้อมใช้งาน.

## บริการของ Azure - [Module 01](../01-introduction/README.md)

**Azure AI Search** - บริการค้นหาในคลาวด์ที่รองรับความสามารถเวกเตอร์. [Module 03](../03-rag/README.md)

**Azure Developer CLI (azd)** - เครื่องมือสำหรับปรับใช้ทรัพยากร Azure.

**Azure OpenAI** - บริการ AI ระดับองค์กรของ Microsoft.

**Bicep** - ภาษา infrastructure-as-code ของ Azure. [Infrastructure Guide](../01-introduction/infra/README.md)

**Deployment Name** - ชื่อสำหรับการปรับใช้โมเดลใน Azure.

**GPT-5** - โมเดล OpenAI ล่าสุดที่มีการควบคุมการให้เหตุผล. [Module 02](../02-prompt-engineering/README.md)

## การทดสอบและพัฒนา - [คู่มือการทดสอบ](TESTING.md)

**Dev Container** - สภาพแวดล้อมการพัฒนาแบบคอนเทนเนอร์. [Configuration](../../../.devcontainer/devcontainer.json)

**GitHub Models** - พื้นที่เล่นโมเดล AI ฟรี. [Module 00](../00-quick-start/README.md)

**In-Memory Testing** - การทดสอบโดยใช้ที่เก็บข้อมูลในหน่วยความจำ.

**Integration Testing** - การทดสอบร่วมกับโครงสร้างพื้นฐานจริง.

**Maven** - เครื่องมืออัตโนมัติสำหรับการสร้างโปรเจกต์ Java.

**Mockito** - เฟรมเวิร์กสำหรับการม็อกใน Java.

**Spring Boot** - เฟรมเวิร์กแอปพลิเคชัน Java. [Module 01](../01-introduction/README.md)

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
ข้อจำกัดความรับผิดชอบ:
เอกสารฉบับนี้ได้รับการแปลโดยใช้บริการแปลด้วย AI [Co-op Translator](https://github.com/Azure/co-op-translator) แม้ว่าเราจะพยายามให้การแปลมีความถูกต้อง แต่โปรดทราบว่าการแปลอัตโนมัติอาจมีข้อผิดพลาดหรือความไม่ถูกต้อง เอกสารต้นฉบับในภาษาต้นฉบับควรถือว่าเป็นแหล่งข้อมูลที่มีอำนาจ หากเป็นข้อมูลที่สำคัญ ขอแนะนำให้ใช้การแปลโดยนักแปลมืออาชีพ เราจะไม่รับผิดชอบต่อความเข้าใจผิดหรือการตีความที่ผิดพลาดใดๆ ที่เกิดจากการใช้การแปลฉบับนี้
<!-- CO-OP TRANSLATOR DISCLAIMER END -->