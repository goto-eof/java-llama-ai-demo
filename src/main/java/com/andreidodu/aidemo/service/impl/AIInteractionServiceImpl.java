package com.andreidodu.aidemo.service.impl;

import com.andreidodu.aidemo.service.AIInteractionService;
import com.andreidodu.aidemo.service.PromptBuilderService;
import com.andreidodu.aidemo.util.MockerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("aiInteractionServiceImpl")
@RequiredArgsConstructor
public class AIInteractionServiceImpl implements AIInteractionService {
    private final PromptBuilderService promptBuilderService;
    private final MockerUtil mockerUtil;

    @Override
    public void run() {
        log.info("\n\n\n-------------------------------\n");
        List<String> realReviews = mockerUtil.getRealMockData();
        realReviews.forEach((review -> processReview(review, promptBuilderService)));
        log.info("\n\n\n-------------------------------\n");

    }

    private static void processReview(String review, PromptBuilderService promptBuilderService) {
        log.info("Client: which is the sentiment of: \"{}\"?", review);
        List<String> trainingExamples = promptBuilderService.getTrainingExamples();
        String analyzeSentimentContextPrompt = promptBuilderService.buildAnalyzeSentimentPrompt(trainingExamples, review);
        String result = promptBuilderService.executePrompt(analyzeSentimentContextPrompt);
        log.warn("AI: the sentiment is {}", result);
        manageCaseNegativeReview(review, result, promptBuilderService);
    }

    private static void manageCaseNegativeReview(String review, String result, PromptBuilderService service) {
        if (!result.contains("negative")) {
            return;
        }

        String generateTheContraryPrompt = service.buildGenerateContraryPrompt(review);
        String contrarySentence = service.executePrompt(generateTheContraryPrompt);
        log.warn("AI: because the sentiment is negative, I generated a positive review -> {}", contrarySentence);
    }
}
