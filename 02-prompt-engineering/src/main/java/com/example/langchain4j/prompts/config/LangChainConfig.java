package com.example.langchain4j.prompts.config;

import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

/**
 * Configuration for LangChain4j with GPT-5 support.
 * 
 * Note: For GPT-5 (o1/o3 models), reasoning effort is controlled through prompt engineering
 * rather than model configuration parameters. See Gpt5PromptService for examples of how to
 * use prompts like "<reasoning_effort>low</reasoning_effort>" to control model behavior.
 * 
 * The three beans provided here are currently identical and serve as a demonstration of how
 * you might create different model configurations in the future when/if reasoning_effort is
 * exposed as a model parameter in LangChain4j.
 */
@Configuration
public class LangChainConfig {

    @Value("${azure.openai.endpoint}")
    private String azureEndpoint;

    @Value("${azure.openai.api-key}")
    private String azureApiKey;

    @Value("${azure.openai.deployment:gpt-5}")
    private String deploymentName;

    @Value("${azure.openai.max-completion-tokens:2000}")
    private Integer maxCompletionTokens;

    /**
     * Primary chat model for general use.
     */
    @Bean
    @Primary
    public AzureOpenAiChatModel chatLanguageModel() {
        return AzureOpenAiChatModel.builder()
                .endpoint(azureEndpoint)
                .apiKey(azureApiKey)
                .deploymentName(deploymentName)
                .maxCompletionTokens(maxCompletionTokens)
                .timeout(Duration.ofMinutes(5))  // GPT-5 reasoning can take time
                .logRequestsAndResponses(true)
                .build();
    }

    /**
     * Alternative model bean for quick responses.
     * Currently identical to primary model - reasoning is controlled via prompts.
     */
    @Bean("quickModel")
    public AzureOpenAiChatModel quickChatModel() {
        return AzureOpenAiChatModel.builder()
                .endpoint(azureEndpoint)
                .apiKey(azureApiKey)
                .deploymentName(deploymentName)
                .maxCompletionTokens(maxCompletionTokens)
                .timeout(Duration.ofMinutes(5))
                .logRequestsAndResponses(true)
                .build();
    }

    /**
     * Alternative model bean for thorough responses.
     * Currently identical to primary model - reasoning is controlled via prompts.
     */
    @Bean("thoroughModel")
    public AzureOpenAiChatModel thoroughChatModel() {
        return AzureOpenAiChatModel.builder()
                .endpoint(azureEndpoint)
                .apiKey(azureApiKey)
                .deploymentName(deploymentName)
                .maxCompletionTokens(maxCompletionTokens)
                .timeout(Duration.ofMinutes(5))
                .logRequestsAndResponses(true)
                .build();
    }
}
