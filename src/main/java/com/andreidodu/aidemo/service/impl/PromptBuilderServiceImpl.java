package com.andreidodu.aidemo.service.impl;

import com.andreidodu.aidemo.service.PromptBuilderService;
import dev.langchain4j.model.ollama.OllamaLanguageModel;
import dev.langchain4j.model.output.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.andreidodu.aidemo.constants.MessageKeysConst.*;

@Service("promptBuilderService")
@RequiredArgsConstructor
public class PromptBuilderServiceImpl implements PromptBuilderService {

    private final MessageSource messageSource;
    private final OllamaLanguageModel ollamaLanguageModel;


    @Override
    public String buildAnalyzeSentimentPrompt(List<String> trainingExamples, String review) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append(messageSource.getMessage(MESSAGE_KEY_PROMPT_CONTEXT_SENTIMENT_ANALYSIS, new Object[0], Locale.US));
        for (String trainingExample : trainingExamples) {
            promptBuilder
                    .append(trainingExample)
                    .append("\n");
        }
        promptBuilder.append("Review: \"").append(review);

        return promptBuilder.toString();
    }

    @Override
    public String buildGenerateContraryPrompt(String comment) {
        return messageSource.getMessage(MESSAGE_KEY_PROMPT_CONTEXT_SENTIMENT_CONTRARY, new Object[0], Locale.US) + comment;
    }

    @Override
    public List<String> getTrainingExamples() {
        return Arrays.asList(messageSource.getMessage(MESSAGE_KEY_PROMPT_CONTEXT_TRAINING_EXAMPLES, new Object[0], Locale.US).split("|"));
    }

    @Override
    public String executePrompt(String prompt) {
        Response<String> response = ollamaLanguageModel.generate(prompt);
        return response.content().trim();
    }
}
