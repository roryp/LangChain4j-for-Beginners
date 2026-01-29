# โครงสร้างพื้นฐาน Azure สำหรับ LangChain4j เริ่มต้นใช้งาน

## สารบัญ

- [ข้อกำหนดเบื้องต้น](../../../../01-introduction/infra)
- [สถาปัตยกรรม](../../../../01-introduction/infra)
- [ทรัพยากรที่สร้างขึ้น](../../../../01-introduction/infra)
- [เริ่มต้นอย่างรวดเร็ว](../../../../01-introduction/infra)
- [การกำหนดค่า](../../../../01-introduction/infra)
- [คำสั่งการจัดการ](../../../../01-introduction/infra)
- [การเพิ่มประสิทธิภาพค่าใช้จ่าย](../../../../01-introduction/infra)
- [การตรวจสอบ](../../../../01-introduction/infra)
- [การแก้ไขปัญหา](../../../../01-introduction/infra)
- [การอัปเดตโครงสร้างพื้นฐาน](../../../../01-introduction/infra)
- [การล้างข้อมูล](../../../../01-introduction/infra)
- [โครงสร้างไฟล์](../../../../01-introduction/infra)
- [คำแนะนำด้านความปลอดภัย](../../../../01-introduction/infra)
- [ทรัพยากรเพิ่มเติม](../../../../01-introduction/infra)

ไดเรกทอรีนี้ประกอบด้วยโครงสร้างพื้นฐาน Azure ในรูปแบบโค้ด (IaC) โดยใช้ Bicep และ Azure Developer CLI (azd) สำหรับการปรับใช้ทรัพยากร Azure OpenAI

## ข้อกำหนดเบื้องต้น

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (เวอร์ชัน 2.50.0 หรือใหม่กว่า)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (เวอร์ชัน 1.5.0 หรือใหม่กว่า)
- การสมัครใช้งาน Azure ที่มีสิทธิ์ในการสร้างทรัพยากร

## สถาปัตยกรรม

**การตั้งค่าการพัฒนาท้องถิ่นแบบง่าย** - ปรับใช้ Azure OpenAI เท่านั้น และรันแอปทั้งหมดในเครื่องของคุณ

โครงสร้างพื้นฐานจะปรับใช้ทรัพยากร Azure ดังต่อไปนี้:

### บริการ AI
- **Azure OpenAI**: บริการ Cognitive Services พร้อมการปรับใช้โมเดลสองตัว:
  - **gpt-5**: โมเดลแชทคอมพลีชัน (ความจุ 20K TPM)
  - **text-embedding-3-small**: โมเดลฝังตัวสำหรับ RAG (ความจุ 20K TPM)

### การพัฒนาท้องถิ่น
แอปพลิเคชัน Spring Boot ทั้งหมดรันในเครื่องของคุณ:
- 01-introduction (พอร์ต 8080)
- 02-prompt-engineering (พอร์ต 8083)
- 03-rag (พอร์ต 8081)
- 04-tools (พอร์ต 8084)

## ทรัพยากรที่สร้างขึ้น

| ประเภททรัพยากร | รูปแบบชื่อทรัพยากร | วัตถุประสงค์ |
|--------------|----------------------|---------|
| Resource Group | `rg-{environmentName}` | เก็บทรัพยากรทั้งหมด |
| Azure OpenAI | `aoai-{resourceToken}` | โฮสต์โมเดล AI |

> **หมายเหตุ:** `{resourceToken}` คือสตริงเฉพาะที่สร้างจาก subscription ID, ชื่อสภาพแวดล้อม และตำแหน่งที่ตั้ง

## เริ่มต้นอย่างรวดเร็ว

### 1. ปรับใช้ Azure OpenAI

**Bash:**
```bash
cd 01-introduction
azd up
```

**PowerShell:**
```powershell
cd 01-introduction
azd up
```

เมื่อได้รับแจ้ง:
- เลือกการสมัครใช้งาน Azure ของคุณ
- เลือกตำแหน่งที่ตั้ง (แนะนำ: `eastus2` หรือ `swedencentral` สำหรับการใช้งาน GPT-5)
- ยืนยันชื่อสภาพแวดล้อม (ค่าเริ่มต้น: `langchain4j-dev`)

สิ่งนี้จะสร้าง:
- ทรัพยากร Azure OpenAI พร้อม GPT-5 และ text-embedding-3-small
- แสดงรายละเอียดการเชื่อมต่อ

### 2. รับรายละเอียดการเชื่อมต่อ

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

จะแสดง:
- `AZURE_OPENAI_ENDPOINT`: URL จุดสิ้นสุด Azure OpenAI ของคุณ
- `AZURE_OPENAI_KEY`: คีย์ API สำหรับการตรวจสอบสิทธิ์
- `AZURE_OPENAI_DEPLOYMENT`: ชื่อโมเดลแชท (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: ชื่อโมเดลฝังตัว

### 3. รันแอปพลิเคชันท้องถิ่น

คำสั่ง `azd up` จะสร้างไฟล์ `.env` ในไดเรกทอรีรากโดยอัตโนมัติพร้อมตัวแปรสภาพแวดล้อมที่จำเป็นทั้งหมด

**แนะนำ:** เริ่มแอปเว็บทั้งหมด:

**Bash:**
```bash
# จากไดเรกทอรีราก
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# จากไดเรกทอรีราก
cd ../..
.\start-all.ps1
```

หรือเริ่มโมดูลเดียว:

**Bash:**
```bash
# ตัวอย่าง: เริ่มเพียงโมดูลแนะนำเท่านั้น
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# ตัวอย่าง: เริ่มต้นเพียงโมดูลแนะนำเท่านั้น
cd ../01-introduction
.\start.ps1
```

ทั้งสองสคริปต์จะโหลดตัวแปรสภาพแวดล้อมจากไฟล์ `.env` ที่สร้างโดย `azd up` โดยอัตโนมัติ

## การกำหนดค่า

### การปรับแต่งการปรับใช้โมเดล

หากต้องการเปลี่ยนการปรับใช้โมเดล ให้แก้ไขไฟล์ `infra/main.bicep` และแก้ไขพารามิเตอร์ `openAiDeployments`:

```bicep
param openAiDeployments array = [
  {
    name: 'gpt-5'  // Model deployment name
    model: {
      format: 'OpenAI'
      name: 'gpt-5'
      version: '2025-08-07'  // Model version
    }
    sku: {
      name: 'Standard'
      capacity: 20  // TPM in thousands
    }
  }
  // Add more deployments...
]
```

โมเดลและเวอร์ชันที่มีให้ใช้งาน: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### การเปลี่ยนภูมิภาค Azure

หากต้องการปรับใช้ในภูมิภาคอื่น ให้แก้ไขไฟล์ `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

ตรวจสอบการใช้งาน GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

หากต้องการอัปเดตโครงสร้างพื้นฐานหลังจากแก้ไขไฟล์ Bicep:

**Bash:**
```bash
# สร้างเทมเพลต ARM ใหม่
az bicep build --file infra/main.bicep

# ดูตัวอย่างการเปลี่ยนแปลง
azd provision --preview

# ใช้การเปลี่ยนแปลง
azd provision
```

**PowerShell:**
```powershell
# สร้างเทมเพลต ARM ใหม่
az bicep build --file infra/main.bicep

# ดูตัวอย่างการเปลี่ยนแปลง
azd provision --preview

# ใช้การเปลี่ยนแปลง
azd provision
```

## การล้างข้อมูล

เพื่อลบทรัพยากรทั้งหมด:

**Bash:**
```bash
# ลบทรัพยากรทั้งหมด
azd down

# ลบทุกอย่างรวมถึงสภาพแวดล้อมด้วย
azd down --purge
```

**PowerShell:**
```powershell
# ลบทรัพยากรทั้งหมด
azd down

# ลบทุกอย่างรวมถึงสภาพแวดล้อม
azd down --purge
```

**คำเตือน**: การดำเนินการนี้จะลบทรัพยากร Azure ทั้งหมดอย่างถาวร

## โครงสร้างไฟล์

## การเพิ่มประสิทธิภาพค่าใช้จ่าย

### การพัฒนา/ทดสอบ
สำหรับสภาพแวดล้อม dev/test คุณสามารถลดค่าใช้จ่ายได้:
- ใช้ระดับ Standard (S0) สำหรับ Azure OpenAI
- ตั้งค่าความจุต่ำกว่า (10K TPM แทน 20K) ใน `infra/core/ai/cognitiveservices.bicep`
- ลบทรัพยากรเมื่อไม่ใช้งาน: `azd down`

### การผลิต
สำหรับการผลิต:
- เพิ่มความจุ OpenAI ตามการใช้งาน (50K+ TPM)
- เปิดใช้งานโซนสำรองเพื่อความพร้อมใช้งานสูงขึ้น
- ติดตั้งการตรวจสอบและแจ้งเตือนค่าใช้จ่ายอย่างเหมาะสม

### การประมาณค่าใช้จ่าย
- Azure OpenAI: จ่ายตามจำนวนโทเค็น (อินพุต + เอาต์พุต)
- GPT-5: ประมาณ $3-5 ต่อ 1 ล้านโทเค็น (ตรวจสอบราคาปัจจุบัน)
- text-embedding-3-small: ประมาณ $0.02 ต่อ 1 ล้านโทเค็น

เครื่องมือคำนวณราคา: https://azure.microsoft.com/pricing/calculator/

## การตรวจสอบ

### ดูเมตริก Azure OpenAI

ไปที่ Azure Portal → ทรัพยากร OpenAI ของคุณ → เมตริก:
- การใช้งานตามโทเค็น
- อัตราการร้องขอ HTTP
- เวลาในการตอบสนอง
- โทเค็นที่ใช้งานอยู่

## การแก้ไขปัญหา

### ปัญหา: ชื่อโดเมนย่อย Azure OpenAI ซ้ำกัน

**ข้อความแสดงข้อผิดพลาด:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**สาเหตุ:**
ชื่อโดเมนย่อยที่สร้างจากการสมัครใช้งาน/สภาพแวดล้อมของคุณถูกใช้งานแล้ว อาจมาจากการปรับใช้ก่อนหน้าที่ยังไม่ถูกลบอย่างสมบูรณ์

**วิธีแก้ไข:**
1. **ตัวเลือก 1 - ใช้ชื่อสภาพแวดล้อมที่แตกต่าง:**
   
   **Bash:**
   ```bash
   azd env new my-unique-env-name
   azd up
   ```
   
   **PowerShell:**
   ```powershell
   azd env new my-unique-env-name
   azd up
   ```

2. **ตัวเลือก 2 - ปรับใช้ด้วยตนเองผ่าน Azure Portal:**
   - ไปที่ Azure Portal → สร้างทรัพยากร → Azure OpenAI
   - เลือกชื่อที่ไม่ซ้ำสำหรับทรัพยากรของคุณ
   - ปรับใช้โมเดลดังต่อไปนี้:
     - **GPT-5**
     - **text-embedding-3-small** (สำหรับโมดูล RAG)
   - **สำคัญ:** จดชื่อการปรับใช้ของคุณ - ต้องตรงกับการกำหนดค่า `.env`
   - หลังการปรับใช้ ให้รับ endpoint และคีย์ API จาก "Keys and Endpoint"
   - สร้างไฟล์ `.env` ในโฟลเดอร์โปรเจกต์หลักโดยมีเนื้อหาดังนี้:
     
     **ตัวอย่างไฟล์ `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**แนวทางการตั้งชื่อการปรับใช้โมเดล:**
- ใช้ชื่อเรียบง่ายและสม่ำเสมอ: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- ชื่อการปรับใช้ต้องตรงกับที่กำหนดใน `.env`
- ข้อผิดพลาดทั่วไป: สร้างโมเดลด้วยชื่อหนึ่งแต่เรียกใช้อีกชื่อในโค้ด

### ปัญหา: GPT-5 ไม่พร้อมใช้งานในภูมิภาคที่เลือก

**วิธีแก้ไข:**
- เลือกภูมิภาคที่มีการเข้าถึง GPT-5 (เช่น eastus, swedencentral)
- ตรวจสอบการใช้งาน: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### ปัญหา: โควต้าไม่เพียงพอสำหรับการปรับใช้

**วิธีแก้ไข:**
1. ขอเพิ่มโควต้าใน Azure Portal
2. หรือใช้ความจุต่ำกว่าใน `main.bicep` (เช่น capacity: 10)

### ปัญหา: "ไม่พบทรัพยากร" เมื่อรันในเครื่อง

**วิธีแก้ไข:**
1. ตรวจสอบการปรับใช้: `azd env get-values`
2. ตรวจสอบว่า endpoint และคีย์ถูกต้อง
3. ตรวจสอบว่ากลุ่มทรัพยากรมีอยู่ใน Azure Portal

### ปัญหา: การตรวจสอบสิทธิ์ล้มเหลว

**วิธีแก้ไข:**
- ตรวจสอบว่า `AZURE_OPENAI_API_KEY` ถูกตั้งค่าอย่างถูกต้อง
- รูปแบบคีย์ควรเป็นสตริงเลขฐานสิบหก 32 ตัวอักษร
- รับคีย์ใหม่จาก Azure Portal หากจำเป็น

### การปรับใช้ล้มเหลว

**ปัญหา**: `azd provision` ล้มเหลวด้วยข้อผิดพลาดโควต้า หรือความจุ

**วิธีแก้ไข**: 
1. ลองใช้ภูมิภาคอื่น - ดูส่วน [การเปลี่ยนภูมิภาค Azure](../../../../01-introduction/infra) สำหรับวิธีตั้งค่า
2. ตรวจสอบว่าการสมัครใช้งานของคุณมีโควต้าสำหรับ Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### แอปพลิเคชันไม่เชื่อมต่อ

**ปัญหา**: แอป Java แสดงข้อผิดพลาดการเชื่อมต่อ

**วิธีแก้ไข**:
1. ตรวจสอบว่าตัวแปรสภาพแวดล้อมถูกส่งออกแล้ว:
   
   **Bash:**
   ```bash
   echo $AZURE_OPENAI_ENDPOINT
   echo $AZURE_OPENAI_API_KEY
   ```
   
   **PowerShell:**
   ```powershell
   Write-Host $env:AZURE_OPENAI_ENDPOINT
   Write-Host $env:AZURE_OPENAI_API_KEY
   ```

2. ตรวจสอบรูปแบบ endpoint ว่าถูกต้อง (ควรเป็น `https://xxx.openai.azure.com`)
3. ตรวจสอบว่าคีย์ API เป็นคีย์หลักหรือคีย์รองจาก Azure Portal

**ปัญหา**: 401 Unauthorized จาก Azure OpenAI

**วิธีแก้ไข**:
1. รับคีย์ API ใหม่จาก Azure Portal → Keys and Endpoint
2. ส่งออกตัวแปรสภาพแวดล้อม `AZURE_OPENAI_API_KEY` ใหม่
3. ตรวจสอบให้แน่ใจว่าการปรับใช้โมเดลเสร็จสมบูรณ์ (ตรวจสอบใน Azure Portal)

### ปัญหาด้านประสิทธิภาพ

**ปัญหา**: เวลาตอบสนองช้า

**วิธีแก้ไข**:
1. ตรวจสอบการใช้งานโทเค็นและการจำกัดความเร็วในเมตริก Azure Portal
2. เพิ่มความจุ TPM หากคุณถึงขีดจำกัด
3. พิจารณาใช้ระดับความพยายามในการให้เหตุผลที่สูงขึ้น (ต่ำ/กลาง/สูง)

## การอัปเดตโครงสร้างพื้นฐาน

```
infra/
├── main.bicep                       # Main infrastructure definition
├── main.json                        # Compiled ARM template (auto-generated)
├── main.bicepparam                  # Parameter file
├── README.md                        # This file
└── core/
    └── ai/
        └── cognitiveservices.bicep  # Azure OpenAI module
```

## คำแนะนำด้านความปลอดภัย

1. **อย่าคอมมิตคีย์ API** - ใช้ตัวแปรสภาพแวดล้อมแทน
2. **ใช้ไฟล์ .env ในเครื่อง** - เพิ่ม `.env` ใน `.gitignore`
3. **หมุนเวียนคีย์เป็นประจำ** - สร้างคีย์ใหม่ใน Azure Portal
4. **จำกัดการเข้าถึง** - ใช้ Azure RBAC เพื่อควบคุมผู้เข้าถึงทรัพยากร
5. **ตรวจสอบการใช้งาน** - ตั้งค่าแจ้งเตือนค่าใช้จ่ายใน Azure Portal

## ทรัพยากรเพิ่มเติม

- [เอกสารบริการ Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [เอกสารโมเดล GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [เอกสาร Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [เอกสาร Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [การรวมอย่างเป็นทางการของ LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## การสนับสนุน

สำหรับปัญหา:
1. ตรวจสอบ [ส่วนการแก้ไขปัญหา](../../../../01-introduction/infra) ข้างต้น
2. ตรวจสอบสถานะบริการ Azure OpenAI ใน Azure Portal
3. เปิด issue ในที่เก็บโค้ด

## ใบอนุญาต

ดูไฟล์ [LICENSE](../../../../LICENSE) ที่โฟลเดอร์รากสำหรับรายละเอียดเพิ่มเติม

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**ข้อจำกัดความรับผิดชอบ**:  
เอกสารนี้ได้รับการแปลโดยใช้บริการแปลภาษาอัตโนมัติ [Co-op Translator](https://github.com/Azure/co-op-translator) แม้เราจะพยายามให้ความถูกต้องสูงสุด แต่โปรดทราบว่าการแปลอัตโนมัติอาจมีข้อผิดพลาดหรือความไม่ถูกต้อง เอกสารต้นฉบับในภาษาต้นทางถือเป็นแหล่งข้อมูลที่เชื่อถือได้ สำหรับข้อมูลที่สำคัญ ขอแนะนำให้ใช้บริการแปลโดยผู้เชี่ยวชาญมนุษย์ เราไม่รับผิดชอบต่อความเข้าใจผิดหรือการตีความผิดใด ๆ ที่เกิดจากการใช้การแปลนี้
<!-- CO-OP TRANSLATOR DISCLAIMER END -->