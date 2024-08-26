package com.dopamine.ott.contents.connector;

import com.dopamine.ott.contents.config.KobisApiProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class KorContentApiClientContent implements ContentWebClientFactory {

    private final KobisApiProperties kobisApiProperties;

    private final WebClient webClient;

    public KorContentApiClientContent(KobisApiProperties kobisApiProperties) {
        this.kobisApiProperties = kobisApiProperties;
        this.webClient = WebClient.builder()
                .baseUrl(kobisApiProperties.getDomain())
                .defaultHeader("Accept", "application/json")
                .defaultHeader("User-Agent", "KoreanContentClient")
                .build();
    }

    @Override
    public String getContentList() {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(kobisApiProperties.getUris().getMovieList())
                            .queryParam("key", kobisApiProperties.getKey())
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            // HTTP 응답 오류 처리
            throw new RuntimeException("API 응답 오류: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        } catch (WebClientRequestException e) {
            // 요청 오류 처리
            throw new RuntimeException("API 요청 오류: " + e.getMessage());
        }

    }


    @Override
    public String getContentDetailInfo(String movieCd) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(kobisApiProperties.getUris().getMovieDetailInfo())
                            .queryParam("key", kobisApiProperties.getKey())
                            .queryParam("movieCd",movieCd)
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            // HTTP 응답 오류 처리
            throw new RuntimeException("API 응답 오류: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        } catch (WebClientRequestException e) {
            // 요청 오류 처리
            throw new RuntimeException("API 요청 오류: " + e.getMessage());
        }

    }
}
