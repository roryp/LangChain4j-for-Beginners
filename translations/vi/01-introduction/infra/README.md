# Cơ sở hạ tầng Azure cho LangChain4j Bắt đầu

## Mục lục

- [Yêu cầu trước](../../../../01-introduction/infra)
- [Kiến trúc](../../../../01-introduction/infra)
- [Tài nguyên được tạo](../../../../01-introduction/infra)
- [Bắt đầu nhanh](../../../../01-introduction/infra)
- [Cấu hình](../../../../01-introduction/infra)
- [Lệnh quản lý](../../../../01-introduction/infra)
- [Tối ưu chi phí](../../../../01-introduction/infra)
- [Giám sát](../../../../01-introduction/infra)
- [Khắc phục sự cố](../../../../01-introduction/infra)
- [Cập nhật cơ sở hạ tầng](../../../../01-introduction/infra)
- [Dọn dẹp](../../../../01-introduction/infra)
- [Cấu trúc tệp](../../../../01-introduction/infra)
- [Khuyến nghị bảo mật](../../../../01-introduction/infra)
- [Tài nguyên bổ sung](../../../../01-introduction/infra)

Thư mục này chứa cơ sở hạ tầng Azure dưới dạng mã (IaC) sử dụng Bicep và Azure Developer CLI (azd) để triển khai các tài nguyên Azure OpenAI.

## Yêu cầu trước

- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli) (phiên bản 2.50.0 trở lên)
- [Azure Developer CLI (azd)](https://learn.microsoft.com/azure/developer/azure-developer-cli/install-azd) (phiên bản 1.5.0 trở lên)
- Một đăng ký Azure với quyền tạo tài nguyên

## Kiến trúc

**Thiết lập phát triển cục bộ đơn giản** - Chỉ triển khai Azure OpenAI, chạy tất cả ứng dụng trên máy cục bộ.

Cơ sở hạ tầng triển khai các tài nguyên Azure sau:

### Dịch vụ AI
- **Azure OpenAI**: Dịch vụ nhận thức với hai triển khai mô hình:
  - **gpt-5**: Mô hình hoàn thành trò chuyện (20K TPM công suất)
  - **text-embedding-3-small**: Mô hình nhúng cho RAG (20K TPM công suất)

### Phát triển cục bộ
Tất cả ứng dụng Spring Boot chạy trên máy của bạn:
- 01-introduction (cổng 8080)
- 02-prompt-engineering (cổng 8083)
- 03-rag (cổng 8081)
- 04-tools (cổng 8084)

## Tài nguyên được tạo

| Loại tài nguyên | Mẫu tên tài nguyên | Mục đích |
|--------------|----------------------|---------|
| Nhóm tài nguyên | `rg-{environmentName}` | Chứa tất cả tài nguyên |
| Azure OpenAI | `aoai-{resourceToken}` | Lưu trữ mô hình AI |

> **Lưu ý:** `{resourceToken}` là chuỗi duy nhất được tạo từ ID đăng ký, tên môi trường và vị trí

## Bắt đầu nhanh

### 1. Triển khai Azure OpenAI

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

Khi được yêu cầu:
- Chọn đăng ký Azure của bạn
- Chọn vị trí (khuyến nghị: `eastus2` hoặc `swedencentral` để có GPT-5)
- Xác nhận tên môi trường (mặc định: `langchain4j-dev`)

Điều này sẽ tạo:
- Tài nguyên Azure OpenAI với GPT-5 và text-embedding-3-small
- Xuất chi tiết kết nối

### 2. Lấy chi tiết kết nối

**Bash:**
```bash
azd env get-values
```

**PowerShell:**
```powershell
azd env get-values
```

Điều này hiển thị:
- `AZURE_OPENAI_ENDPOINT`: URL điểm cuối Azure OpenAI của bạn
- `AZURE_OPENAI_KEY`: Khóa API để xác thực
- `AZURE_OPENAI_DEPLOYMENT`: Tên mô hình trò chuyện (gpt-5)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Tên mô hình nhúng

### 3. Chạy ứng dụng cục bộ

Lệnh `azd up` tự động tạo tệp `.env` trong thư mục gốc với tất cả biến môi trường cần thiết.

**Khuyến nghị:** Khởi động tất cả ứng dụng web:

**Bash:**
```bash
# Từ thư mục gốc
cd ../..
./start-all.sh
```

**PowerShell:**
```powershell
# Từ thư mục gốc
cd ../..
.\start-all.ps1
```

Hoặc khởi động một module riêng:

**Bash:**
```bash
# Ví dụ: Chỉ bắt đầu mô-đun giới thiệu
cd ../01-introduction
./start.sh
```

**PowerShell:**
```powershell
# Ví dụ: Chỉ bắt đầu mô-đun giới thiệu
cd ../01-introduction
.\start.ps1
```

Cả hai script tự động tải biến môi trường từ tệp `.env` gốc được tạo bởi `azd up`.

## Cấu hình

### Tùy chỉnh triển khai mô hình

Để thay đổi triển khai mô hình, chỉnh sửa `infra/main.bicep` và sửa tham số `openAiDeployments`:

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

Các mô hình và phiên bản có sẵn: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Thay đổi vùng Azure

Để triển khai ở vùng khác, chỉnh sửa `infra/main.bicep`:

```bicep
param openAiLocation string = 'swedencentral'  // or other GPT-5 region
```

Kiểm tra khả năng truy cập GPT-5: https://learn.microsoft.com/azure/ai-services/openai/concepts/models#model-summary-table-and-region-availability

Để cập nhật cơ sở hạ tầng sau khi chỉnh sửa tệp Bicep:

**Bash:**
```bash
# Xây dựng lại mẫu ARM
az bicep build --file infra/main.bicep

# Xem trước các thay đổi
azd provision --preview

# Áp dụng các thay đổi
azd provision
```

**PowerShell:**
```powershell
# Xây dựng lại mẫu ARM
az bicep build --file infra/main.bicep

# Xem trước các thay đổi
azd provision --preview

# Áp dụng các thay đổi
azd provision
```

## Dọn dẹp

Để xóa tất cả tài nguyên:

**Bash:**
```bash
# Xóa tất cả các tài nguyên
azd down

# Xóa mọi thứ bao gồm cả môi trường
azd down --purge
```

**PowerShell:**
```powershell
# Xóa tất cả các tài nguyên
azd down

# Xóa mọi thứ bao gồm cả môi trường
azd down --purge
```

**Cảnh báo**: Điều này sẽ xóa vĩnh viễn tất cả tài nguyên Azure.

## Cấu trúc tệp

## Tối ưu chi phí

### Phát triển/Thử nghiệm
Đối với môi trường dev/test, bạn có thể giảm chi phí:
- Sử dụng cấp Standard (S0) cho Azure OpenAI
- Đặt công suất thấp hơn (10K TPM thay vì 20K) trong `infra/core/ai/cognitiveservices.bicep`
- Xóa tài nguyên khi không sử dụng: `azd down`

### Sản xuất
Đối với sản xuất:
- Tăng công suất OpenAI dựa trên sử dụng (50K+ TPM)
- Bật dự phòng vùng để tăng tính khả dụng
- Triển khai giám sát và cảnh báo chi phí phù hợp

### Ước tính chi phí
- Azure OpenAI: Trả theo token (đầu vào + đầu ra)
- GPT-5: ~3-5 USD cho 1 triệu token (kiểm tra giá hiện tại)
- text-embedding-3-small: ~0.02 USD cho 1 triệu token

Máy tính giá: https://azure.microsoft.com/pricing/calculator/

## Giám sát

### Xem số liệu Azure OpenAI

Đi tới Azure Portal → Tài nguyên OpenAI của bạn → Metrics:
- Sử dụng dựa trên token
- Tỷ lệ yêu cầu HTTP
- Thời gian phản hồi
- Token đang hoạt động

## Khắc phục sự cố

### Vấn đề: Trùng tên miền phụ Azure OpenAI

**Thông báo lỗi:**
```
ERROR CODE: CustomDomainInUse
message: "Please pick a different name. The subdomain name 'aoai-xxxxx' 
is not available as it's already used by a resource."
```

**Nguyên nhân:**
Tên miền phụ được tạo từ đăng ký/môi trường của bạn đã được sử dụng, có thể do triển khai trước đó chưa được xóa hoàn toàn.

**Giải pháp:**
1. **Lựa chọn 1 - Dùng tên môi trường khác:**
   
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

2. **Lựa chọn 2 - Triển khai thủ công qua Azure Portal:**
   - Vào Azure Portal → Tạo tài nguyên → Azure OpenAI
   - Chọn tên duy nhất cho tài nguyên của bạn
   - Triển khai các mô hình sau:
     - **GPT-5**
     - **text-embedding-3-small** (cho các module RAG)
   - **Quan trọng:** Ghi lại tên triển khai - phải khớp với cấu hình `.env`
   - Sau khi triển khai, lấy điểm cuối và khóa API từ "Keys and Endpoint"
   - Tạo tệp `.env` trong thư mục gốc dự án với:
     
     **Ví dụ tệp `.env`:**
     ```bash
     AZURE_OPENAI_ENDPOINT=https://your-resource-name.openai.azure.com
     AZURE_OPENAI_API_KEY=your-api-key-here
     AZURE_OPENAI_DEPLOYMENT=gpt-5
     AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-3-small
     ```

**Hướng dẫn đặt tên triển khai mô hình:**
- Dùng tên đơn giản, nhất quán: `gpt-5`, `gpt-4o`, `text-embedding-3-small`
- Tên triển khai phải khớp chính xác với cấu hình trong `.env`
- Lỗi phổ biến: Tạo mô hình với tên này nhưng tham chiếu tên khác trong code

### Vấn đề: GPT-5 không có sẵn ở vùng đã chọn

**Giải pháp:**
- Chọn vùng có truy cập GPT-5 (ví dụ: eastus, swedencentral)
- Kiểm tra khả năng truy cập: https://learn.microsoft.com/azure/ai-services/openai/concepts/models

### Vấn đề: Hạn mức không đủ cho triển khai

**Giải pháp:**
1. Yêu cầu tăng hạn mức trong Azure Portal
2. Hoặc dùng công suất thấp hơn trong `main.bicep` (ví dụ: capacity: 10)

### Vấn đề: "Resource not found" khi chạy cục bộ

**Giải pháp:**
1. Xác minh triển khai: `azd env get-values`
2. Kiểm tra điểm cuối và khóa đúng
3. Đảm bảo nhóm tài nguyên tồn tại trong Azure Portal

### Vấn đề: Xác thực thất bại

**Giải pháp:**
- Xác nhận `AZURE_OPENAI_API_KEY` được đặt đúng
- Định dạng khóa phải là chuỗi thập lục phân 32 ký tự
- Lấy khóa mới từ Azure Portal nếu cần

### Triển khai thất bại

**Vấn đề**: `azd provision` lỗi hạn mức hoặc công suất

**Giải pháp**: 
1. Thử vùng khác - Xem phần [Thay đổi vùng Azure](../../../../01-introduction/infra) để cấu hình vùng
2. Kiểm tra đăng ký của bạn có hạn mức Azure OpenAI:
   
   **Bash:**
   ```bash
   az cognitiveservices account list-skus --location <your-region>
   ```
   
   **PowerShell:**
   ```powershell
   az cognitiveservices account list-skus --location <your-region>
   ```

### Ứng dụng không kết nối

**Vấn đề**: Ứng dụng Java báo lỗi kết nối

**Giải pháp**:
1. Xác nhận biến môi trường đã được xuất:
   
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

2. Kiểm tra định dạng điểm cuối đúng (phải là `https://xxx.openai.azure.com`)
3. Xác nhận khóa API là khóa chính hoặc phụ từ Azure Portal

**Vấn đề**: 401 Unauthorized từ Azure OpenAI

**Giải pháp**:
1. Lấy khóa API mới từ Azure Portal → Keys and Endpoint
2. Xuất lại biến môi trường `AZURE_OPENAI_API_KEY`
3. Đảm bảo các triển khai mô hình đã hoàn tất (kiểm tra Azure Portal)

### Vấn đề hiệu năng

**Vấn đề**: Thời gian phản hồi chậm

**Giải pháp**:
1. Kiểm tra sử dụng token và giới hạn trong số liệu Azure Portal
2. Tăng công suất TPM nếu bạn bị giới hạn
3. Cân nhắc dùng mức độ nỗ lực suy luận cao hơn (thấp/trung bình/cao)

## Cập nhật cơ sở hạ tầng

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

## Khuyến nghị bảo mật

1. **Không bao giờ commit khóa API** - Dùng biến môi trường
2. **Dùng tệp .env cục bộ** - Thêm `.env` vào `.gitignore`
3. **Thường xuyên thay đổi khóa** - Tạo khóa mới trong Azure Portal
4. **Giới hạn truy cập** - Dùng Azure RBAC để kiểm soát ai có thể truy cập tài nguyên
5. **Giám sát sử dụng** - Thiết lập cảnh báo chi phí trong Azure Portal

## Tài nguyên bổ sung

- [Tài liệu dịch vụ Azure OpenAI](https://learn.microsoft.com/azure/ai-services/openai/)
- [Tài liệu mô hình GPT-5](https://learn.microsoft.com/azure/ai-services/openai/concepts/models#gpt-5)
- [Tài liệu Azure Developer CLI](https://learn.microsoft.com/azure/developer/azure-developer-cli/)
- [Tài liệu Bicep](https://learn.microsoft.com/azure/azure-resource-manager/bicep/)
- [Tích hợp chính thức LangChain4j OpenAI](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## Hỗ trợ

Đối với các vấn đề:
1. Kiểm tra [phần khắc phục sự cố](../../../../01-introduction/infra) ở trên
2. Xem tình trạng dịch vụ Azure OpenAI trong Azure Portal
3. Mở issue trong kho lưu trữ

## Giấy phép

Xem tệp [LICENSE](../../../../LICENSE) gốc để biết chi tiết.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**Tuyên bố từ chối trách nhiệm**:  
Tài liệu này đã được dịch bằng dịch vụ dịch thuật AI [Co-op Translator](https://github.com/Azure/co-op-translator). Mặc dù chúng tôi cố gắng đảm bảo độ chính xác, xin lưu ý rằng bản dịch tự động có thể chứa lỗi hoặc không chính xác. Tài liệu gốc bằng ngôn ngữ gốc của nó nên được coi là nguồn chính xác và đáng tin cậy. Đối với thông tin quan trọng, nên sử dụng dịch vụ dịch thuật chuyên nghiệp do con người thực hiện. Chúng tôi không chịu trách nhiệm về bất kỳ sự hiểu lầm hoặc giải thích sai nào phát sinh từ việc sử dụng bản dịch này.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->