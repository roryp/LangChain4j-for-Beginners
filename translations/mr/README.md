<img src="../../translated_images/mr/LangChain4j.90e1d693fcc71b50.webp" alt="LangChain4j" width="800"/>

### 🌐 बहुभाषिक समर्थन

#### GitHub Action द्वारे समर्थित (स्वयंचलित आणि नेहमी अद्ययावत)

<!-- CO-OP TRANSLATOR LANGUAGES TABLE START -->
[Arabic](../ar/README.md) | [Bengali](../bn/README.md) | [Bulgarian](../bg/README.md) | [Burmese (Myanmar)](../my/README.md) | [Chinese (Simplified)](../zh-CN/README.md) | [Chinese (Traditional, Hong Kong)](../zh-HK/README.md) | [Chinese (Traditional, Macau)](../zh-MO/README.md) | [Chinese (Traditional, Taiwan)](../zh-TW/README.md) | [Croatian](../hr/README.md) | [Czech](../cs/README.md) | [Danish](../da/README.md) | [Dutch](../nl/README.md) | [Estonian](../et/README.md) | [Finnish](../fi/README.md) | [French](../fr/README.md) | [German](../de/README.md) | [Greek](../el/README.md) | [Hebrew](../he/README.md) | [Hindi](../hi/README.md) | [Hungarian](../hu/README.md) | [Indonesian](../id/README.md) | [Italian](../it/README.md) | [Japanese](../ja/README.md) | [Kannada](../kn/README.md) | [Korean](../ko/README.md) | [Lithuanian](../lt/README.md) | [Malay](../ms/README.md) | [Malayalam](../ml/README.md) | [Marathi](./README.md) | [Nepali](../ne/README.md) | [Nigerian Pidgin](../pcm/README.md) | [Norwegian](../no/README.md) | [Persian (Farsi)](../fa/README.md) | [Polish](../pl/README.md) | [Portuguese (Brazil)](../pt-BR/README.md) | [Portuguese (Portugal)](../pt-PT/README.md) | [Punjabi (Gurmukhi)](../pa/README.md) | [Romanian](../ro/README.md) | [Russian](../ru/README.md) | [Serbian (Cyrillic)](../sr/README.md) | [Slovak](../sk/README.md) | [Slovenian](../sl/README.md) | [Spanish](../es/README.md) | [Swahili](../sw/README.md) | [Swedish](../sv/README.md) | [Tagalog (Filipino)](../tl/README.md) | [Tamil](../ta/README.md) | [Telugu](../te/README.md) | [Thai](../th/README.md) | [Turkish](../tr/README.md) | [Ukrainian](../uk/README.md) | [Urdu](../ur/README.md) | [Vietnamese](../vi/README.md)

> **स्थानिक स्वरूपात क्लोन करणे प्राधान्य देतात?**

> या रिपॉझिटरीमध्ये ५०+ भाषा अनुवादांचा समावेश आहे ज्यामुळे डाउनलोड आकार लक्षणीयरीत्या वाढतो. अनुवादांशिवाय क्लोन करण्यासाठी sparse checkout वापरा:
> ```bash
> git clone --filter=blob:none --sparse https://github.com/microsoft/LangChain4j-for-Beginners.git
> cd LangChain4j-for-Beginners
> git sparse-checkout set --no-cone '/*' '!translations' '!translated_images'
> ```
> हे तुम्हाला कोर्स पूर्ण करण्यासाठी आवश्यक सर्वकाही वेगाने डाउनलोड करण्यास देते.
<!-- CO-OP TRANSLATOR LANGUAGES TABLE END -->

# LangChain4j नवीन वापरकर्त्यांसाठी

LangChain4j आणि Azure OpenAI GPT-5 सह AI अनुप्रयोग तयार करण्याचा कोर्स, मूलभूत चॅटपासून AI एजंटपर्यंत.

**LangChain4j मध्ये नवीन आहात?** महत्त्वाच्या संज्ञा आणि संकल्पनांसाठी [शब्दसंग्रह](docs/GLOSSARY.md) तपासा.

## अनुक्रमणिका

1. [त्वरित सुरुवात](00-quick-start/README.md) - LangChain4j सह सुरुवात करा
2. [परिचय](01-introduction/README.md) - LangChain4j चे मूलभूत तत्त्व जाणून घ्या
3. [प्रॉम्प्ट अभियांत्रिकी](02-prompt-engineering/README.md) - प्रभावी प्रॉम्प्ट डिझाइन करा
4. [RAG (रिट्रीव्हल-ऑगमेंटेड जनरेशन)](03-rag/README.md) - बुद्धिमान ज्ञान-आधारित प्रणाली तयार करा
5. [साधने](04-tools/README.md) - बाह्य साधने आणि सोपे सहाय्यक एकत्रित करा
6. [MCP (मॉडेल संदर्भ प्रोटोकॉल)](05-mcp/README.md) - मॉडेल संदर्भ प्रोटोकॉल (MCP) आणि एजेन्टिक मॉड्यूलसह कार्य करा
---

## शिक्षण मार्ग

> **त्वरित सुरुवात**

1. हा रिपॉझिटरी तुमच्या GitHub खात्यात Fork करा
2. **Code** → **Codespaces** टॅब → **...** → **New with options...** वर क्लिक करा
3. पूर्वनिर्धारित निवडा – यामुळे या कोर्ससाठी तयार केलेला डेव्हलपमेंट कंटेनर निवडला जाईल
4. **Create codespace** वर क्लिक करा
5. वातावरण तयार होण्यासाठी ५-१० मिनिटे प्रतीक्षा करा
6. लगेच [त्वरित सुरुवात](./00-quick-start/README.md) येथे जा आणि सुरूवात करा!

मॉड्युल्स पूर्ण केल्या केल्यानंतर, LangChain4j टेस्टिंग संकल्पना प्रत्यक्षात पाहण्यासाठी [टेस्टिंग मार्गदर्शक](docs/TESTING.md) तपासा.

> **नोट:** हे प्रशिक्षण GitHub Models आणि Azure OpenAI दोघेही वापरते. [त्वरित सुरुवात](00-quick-start/README.md) मॉड्युल GitHub Models वापरतो (Azure सदस्यत्व आवश्यक नाही), तर १-५ मॉड्युल्स Azure OpenAI वापरतात.


## GitHub Copilot सह शिक्षण

कोडिंग त्वरीत सुरू करण्यासाठी, हा प्रकल्प GitHub Codespace किंवा तुमच्या स्थानिक IDE मध्ये दिलेल्या devcontainer सह उघडा. या कोर्समध्ये वापरला गेलेला devcontainer GitHub Copilot सह पूर्व-स्वरूपित आहे ज्यामुळे AI जोडपे प्रोग्रॅमिंग सुलभ होते.

प्रत्येक कोड उदाहरणात GitHub Copilot कडून विचारू शकणाऱ्या सुचवलेल्या प्रश्नांचा समावेश आहे जेणेकरून तुम्ही समज अधिक सखोल करू शकता. 💡/🤖 प्रॉम्प्टसाठी शोधा:

- **Java फाइल हेडर्स** - प्रत्येक उदाहरणासाठी विशेष प्रश्न
- **मॉड्युल README फायली** - कोड उदाहरणांनंतर एक्सप्लोरेशन प्रॉम्प्ट

**कसे वापरायचे:** कोणतीही कोड फाइल उघडा आणि Copilot ला सुचवलेल्या प्रश्न विचारा. त्याला कोडबेसचा पूर्ण संदर्भ आहे आणि तो समजावून सांगू शकतो, विस्तार करू शकतो, आणि पर्याय सुचवू शकतो.

अधिक जाणून घ्यायचे आहे का? [AI जोडपे प्रोग्रॅमिंगसाठी Copilot](https://aka.ms/GitHubCopilotAI) तपासा.


## अतिरिक्त संसाधने

<!-- CO-OP TRANSLATOR OTHER COURSES START -->
### LangChain
[![LangChain4j for Beginners](https://img.shields.io/badge/LangChain4j%20for%20Beginners-22C55E?style=for-the-badge&&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchain4j-for-beginners)
[![LangChain.js for Beginners](https://img.shields.io/badge/LangChain.js%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=0553D6)](https://aka.ms/langchainjs-for-beginners?WT.mc_id=m365-94501-dwahlin)

---

### Azure / Edge / MCP / Agents
[![AZD for Beginners](https://img.shields.io/badge/AZD%20for%20Beginners-0078D4?style=for-the-badge&labelColor=E5E7EB&color=0078D4)](https://github.com/microsoft/AZD-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Edge AI for Beginners](https://img.shields.io/badge/Edge%20AI%20for%20Beginners-00B8E4?style=for-the-badge&labelColor=E5E7EB&color=00B8E4)](https://github.com/microsoft/edgeai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![MCP for Beginners](https://img.shields.io/badge/MCP%20for%20Beginners-009688?style=for-the-badge&labelColor=E5E7EB&color=009688)](https://github.com/microsoft/mcp-for-beginners?WT.mc_id=academic-105485-koreyst)
[![AI Agents for Beginners](https://img.shields.io/badge/AI%20Agents%20for%20Beginners-00C49A?style=for-the-badge&labelColor=E5E7EB&color=00C49A)](https://github.com/microsoft/ai-agents-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### जनरेटिव AI सिरीज
[![Generative AI for Beginners](https://img.shields.io/badge/Generative%20AI%20for%20Beginners-8B5CF6?style=for-the-badge&labelColor=E5E7EB&color=8B5CF6)](https://github.com/microsoft/generative-ai-for-beginners?WT.mc_id=academic-105485-koreyst)
[![Generative AI (.NET)](https://img.shields.io/badge/Generative%20AI%20(.NET)-9333EA?style=for-the-badge&labelColor=E5E7EB&color=9333EA)](https://github.com/microsoft/Generative-AI-for-beginners-dotnet?WT.mc_id=academic-105485-koreyst)
[![Generative AI (Java)](https://img.shields.io/badge/Generative%20AI%20(Java)-C084FC?style=for-the-badge&labelColor=E5E7EB&color=C084FC)](https://github.com/microsoft/generative-ai-for-beginners-java?WT.mc_id=academic-105485-koreyst)
[![Generative AI (JavaScript)](https://img.shields.io/badge/Generative%20AI%20(JavaScript)-E879F9?style=for-the-badge&labelColor=E5E7EB&color=E879F9)](https://github.com/microsoft/generative-ai-with-javascript?WT.mc_id=academic-105485-koreyst)

---
 
### मुख्य शिक्षण
[![ML for Beginners](https://img.shields.io/badge/ML%20for%20Beginners-22C55E?style=for-the-badge&labelColor=E5E7EB&color=22C55E)](https://aka.ms/ml-beginners?WT.mc_id=academic-105485-koreyst)
[![Data Science for Beginners](https://img.shields.io/badge/Data%20Science%20for%20Beginners-84CC16?style=for-the-badge&labelColor=E5E7EB&color=84CC16)](https://aka.ms/datascience-beginners?WT.mc_id=academic-105485-koreyst)
[![AI for Beginners](https://img.shields.io/badge/AI%20for%20Beginners-A3E635?style=for-the-badge&labelColor=E5E7EB&color=A3E635)](https://aka.ms/ai-beginners?WT.mc_id=academic-105485-koreyst)
[![Cybersecurity for Beginners](https://img.shields.io/badge/Cybersecurity%20for%20Beginners-F97316?style=for-the-badge&labelColor=E5E7EB&color=F97316)](https://github.com/microsoft/Security-101?WT.mc_id=academic-96948-sayoung)
[![Web Dev for Beginners](https://img.shields.io/badge/Web%20Dev%20for%20Beginners-EC4899?style=for-the-badge&labelColor=E5E7EB&color=EC4899)](https://aka.ms/webdev-beginners?WT.mc_id=academic-105485-koreyst)
[![IoT for Beginners](https://img.shields.io/badge/IoT%20for%20Beginners-14B8A6?style=for-the-badge&labelColor=E5E7EB&color=14B8A6)](https://aka.ms/iot-beginners?WT.mc_id=academic-105485-koreyst)
[![XR Development for Beginners](https://img.shields.io/badge/XR%20Development%20for%20Beginners-38BDF8?style=for-the-badge&labelColor=E5E7EB&color=38BDF8)](https://github.com/microsoft/xr-development-for-beginners?WT.mc_id=academic-105485-koreyst)

---
 
### Copilot सिरीज
[![Copilot for AI Paired Programming](https://img.shields.io/badge/Copilot%20for%20AI%20Paired%20Programming-FACC15?style=for-the-badge&labelColor=E5E7EB&color=FACC15)](https://aka.ms/GitHubCopilotAI?WT.mc_id=academic-105485-koreyst)
[![Copilot for C#/.NET](https://img.shields.io/badge/Copilot%20for%20C%23/.NET-FBBF24?style=for-the-badge&labelColor=E5E7EB&color=FBBF24)](https://github.com/microsoft/mastering-github-copilot-for-dotnet-csharp-developers?WT.mc_id=academic-105485-koreyst)
[![Copilot Adventure](https://img.shields.io/badge/Copilot%20Adventure-FDE68A?style=for-the-badge&labelColor=E5E7EB&color=FDE68A)](https://github.com/microsoft/CopilotAdventures?WT.mc_id=academic-105485-koreyst)
<!-- CO-OP TRANSLATOR OTHER COURSES END -->

## मदत मिळवणे

जर तुम्हाला अडचण येत असेल किंवा AI अॅप्स तयार करण्याबद्दल काही प्रश्न असतील, तर सामील व्हा:

[![Azure AI Foundry Discord](https://img.shields.io/badge/Discord-Azure_AI_Foundry_Community_Discord-blue?style=for-the-badge&logo=discord&color=5865f2&logoColor=fff)](https://aka.ms/foundry/discord)

जर उत्पादनाबाबत अभिप्राय किंवा तयार करताना त्रुटी आढळल्यास भेट द्या:

[![Azure AI Foundry Developer Forum](https://img.shields.io/badge/GitHub-Azure_AI_Foundry_Developer_Forum-blue?style=for-the-badge&logo=github&color=000000&logoColor=fff)](https://aka.ms/foundry/forum)

## परवाना

MIT परवाना - तपशीलांसाठी [LICENSE](../../LICENSE) फाईल पहा.

---

<!-- CO-OP TRANSLATOR DISCLAIMER START -->
**कृपायज्ञ**:  
हा दस्तऐवज AI अनुवाद सेवा [Co-op Translator](https://github.com/Azure/co-op-translator) वापरून अनुवादित केला आहे. आम्ही अचूकतेसाठी प्रयत्नशील आहोत, तरी कृपया लक्षात घ्या की स्वयंचलित अनुवादांमध्ये चुका किंवा अचूकतेत फरक असू शकतो. मूळ दस्तऐवज त्याच्या मूळ भाषेत सर्वात अधिकृत स्रोत मानावा. महत्त्वपूर्ण माहितीसाठी व्यावसायिक मानवी अनुवाद घेणे शिफारसीय आहे. या अनुवादाच्या वापरामुळे उद्भवलेल्या कोणत्याही गैरसमजुती किंवा चुकीच्या अर्थ लावण्याबद्दल आम्ही जबाबदार नाही.
<!-- CO-OP TRANSLATOR DISCLAIMER END -->