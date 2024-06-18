package com.dopamine.ott.common.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class KoreanContentWebclientFactory implements WebClientFactory{
    private final WebClient webClient;
    private final String apiKey;

    public KoreanContentWebclientFactory(@Value("#{${contents.api.domAPI}}") Map<String, String> domAPI) {
        this.webClient = WebClient.builder()
                .baseUrl(domAPI.get("url"))
                .defaultHeader("Accept", "application/json")
                .defaultHeader("User-Agent", "KoreanContentClient")
                .build();
        this.apiKey = domAPI.get("key");
    }

    @Override
    public String getMobieList() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/searchMovieList.json")
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }
}
