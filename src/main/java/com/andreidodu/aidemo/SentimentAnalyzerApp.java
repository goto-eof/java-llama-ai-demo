package com.andreidodu.aidemo;

import com.andreidodu.aidemo.service.AIInteractionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentimentAnalyzerApp {

    public static void main(String[] args) {
        var context = SpringApplication.run(SentimentAnalyzerApp.class, args);
        ((AIInteractionService) context.getBean("aiInteractionServiceImpl")).run();
    }

}