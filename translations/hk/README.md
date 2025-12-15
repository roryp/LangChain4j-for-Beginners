<!--
CO_OP_TRANSLATOR_METADATA:
{
  "original_hash": "69c7e2616c66df6cc296492fbfcad9ec",
  "translation_date": "2025-12-13T12:18:45+00:00",
  "source_file": "README.md",
  "language_code": "hk"
}
-->
<img src="../../translated_images/LangChain4j.90e1d693fcc71b506e9588f734989dd106ebd9f977b7f784941a28b88348f0d6.hk.png" alt="LangChain4j" width="800"/>

# LangChain4j 初學者指南

一個使用 LangChain4j 和 Azure OpenAI GPT-5 建立 AI 應用程式的課程，從基本聊天到 AI 代理人。

**剛接觸 LangChain4j？** 請參閱 [詞彙表](docs/GLOSSARY.md) 了解關鍵術語和概念的定義。

## 目錄

1. [快速入門](00-quick-start/README.md) - 開始使用 LangChain4j
2. [介紹](01-introduction/README.md) - 學習 LangChain4j 的基礎知識
3. [提示工程](02-prompt-engineering/README.md) - 精通有效的提示設計
4. [RAG（檢索增強生成）](03-rag/README.md) - 建立智能知識系統
5. [工具](04-tools/README.md) - 與 AI 代理人整合外部工具和 API
6. [MCP（模型上下文協議）](05-mcp/README.md) - 使用模型上下文協議
---

## 學習路徑

從 [快速入門](00-quick-start/README.md) 模組開始，逐步進行每個模組以建立您的技能。您將嘗試基本範例以理解基礎，然後進入 [介紹](01-introduction/README.md) 模組，深入了解 GPT-5。

<img src="../../translated_images/learning-path.ac2da6720e77c3165960835627cef4c20eb2afb103be73a4f25b6d8fafbd738d.hk.png" alt="Learning Path" width="800"/>

完成模組後，探索 [測試指南](docs/TESTING.md) 以了解 LangChain4j 測試概念的實際應用。

> **注意：** 本訓練同時使用 GitHub 模型和 Azure OpenAI。[快速入門](00-quick-start/README.md) 和 [MCP](05-mcp/README.md) 模組使用 GitHub 模型（無需 Azure 訂閱），而模組 1-4 使用 Azure OpenAI GPT-5。

## 使用 GitHub Copilot 學習

要快速開始編碼，請在 GitHub Codespace 或本地 IDE 中打開此專案，並使用提供的 devcontainer。本課程使用的 devcontainer 預先配置了 GitHub Copilot 以進行 AI 配對編程。

每個程式碼範例都包含您可以問 GitHub Copilot 的建議問題，以加深理解。請留意以下位置的 💡/🤖 提示：

- **Java 檔案標頭** - 針對每個範例的特定問題
- **模組 README** - 程式碼範例後的探索提示

**使用方法：** 打開任何程式碼檔案並向 Copilot 詢問建議問題。它擁有完整的程式碼庫上下文，能解釋、擴展並建議替代方案。

想了解更多？請參閱 [AI 配對編程的 Copilot](https://aka.ms/GitHubCopilotAI)。

## 額外資源

### LangChain
[![LangChain4j for Beginners](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js for Beginners](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / 代理人
[![AZD for Beginners](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI for Beginners](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP for Beginners](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Agents for Beginners](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---

### 生成式 AI 系列
[![Generative AI for Beginners](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generative AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generative AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generative AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---

### 核心學習
[![ML for Beginners](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science for Beginners](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI for Beginners](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Cybersecurity for Beginners](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev for Beginners](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT for Beginners](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development for Beginners](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---

### Copilot 系列
[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)

## 尋求協助

如果遇到困難或有任何關於建立 AI 應用程式的問題，請加入：

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

如果您在開發過程中有產品反饋或錯誤，請訪問：

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## 授權

MIT 授權 - 詳情請參閱 [LICENSE](../../LICENSE) 檔案。

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**免責聲明**：  
本文件由 AI 翻譯服務 [Co-op Translator](https://github.com/Azure/co-op-translator) 進行翻譯。雖然我們致力於確保準確性，但請注意，自動翻譯可能包含錯誤或不準確之處。原始文件的母語版本應被視為權威來源。對於重要資訊，建議採用專業人工翻譯。我們不對因使用本翻譯而引起的任何誤解或誤釋承擔責任。
<!-- CO-OP TRANSLATOR DISCLAIMER END -->