package com.andreidodu.aidemo.service;

import java.util.List;

public interface PromptBuilderService {
    String buildAnalyzeSentimentPrompt(List<String> trainingExamples, String review);

    String buildGenerateContraryPrompt(String comment);

    List<String> getTrainingExamples();

    String executePrompt(String prompt);
}
