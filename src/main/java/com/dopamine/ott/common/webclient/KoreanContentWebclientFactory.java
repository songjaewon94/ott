package com.dopamine.ott.common.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KoreanContentWebclientFactory implements WebClientFactory{
//    @Value("${contents.api.domAPI.url}")
//    private String url;

    @Value("${contents.api.kobisAPI.key}")
    private String apiKey;

    @Value("${contents.api.kobisAPI.uri}")
    private String kobisURI;
    private final WebClient webClient;

    public KoreanContentWebclientFactory(@Value("${contents.api.kobisAPI.domain}") String url) {

        this.webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("User-Agent", "KoreanContentClient")
                .build();
    }

    @Override
    public String getMobieList() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(kobisURI)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }
}
