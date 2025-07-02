package com.helpdesk.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OpenAiService {

    private final WebClient webClient;

    public OpenAiService(@Value("${openai.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public String askOpenAi(String question) {
        String payload = """
        {
            "model": "gpt-3.5-turbo",
            "messages": [{"role": "system", "content": "You are a helpful support agent for XYZ product."},
                         {"role": "user", "content": "%s"}]
        }
        """.formatted(question);

        return webClient.post()
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
