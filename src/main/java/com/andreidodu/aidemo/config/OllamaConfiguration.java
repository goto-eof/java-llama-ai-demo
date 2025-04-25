package com.andreidodu.aidemo.config;

import dev.langchain4j.model.ollama.OllamaLanguageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OllamaConfiguration {

    @Bean(name = "ollamaLanguageModel")
    public OllamaLanguageModel getOllama(@Value("${com.andreidodu.ai-demo.llama-base-url}") String baseUrl,
                                         @Value("${com.andreidodu.ai-demo.llama-model}") String model,
                                         @Value("${com.andreidodu.ai-demo.llama-timeout-seconds}") Integer timeoutSeconds) {
        return OllamaLanguageModel.builder()
                .baseUrl(baseUrl)
                .modelName(model)
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .build();
    }

}
