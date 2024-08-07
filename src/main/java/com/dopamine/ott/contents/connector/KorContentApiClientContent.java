package com.dopamine.ott.contents.connector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class KorContentApiClientContent implements ContentWebClientFactory {

    @Value("${contents.api.kobisAPI.key}")
    private String apiKey;

    @Value("${contents.api.kobisAPI.uris.movieList}")
    private String kobisMovieListURI;

    @Value("${contents.api.kobisAPI.uris.movieDetailInfo}")
    private String kobisMovieDetailInfoURI;

    private final WebClient webClient;

    public KorContentApiClientContent(@Value("${contents.api.kobisAPI.domain}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("User-Agent", "KoreanContentClient")
                .build();
    }

    @Override
    public String getContentList() {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(kobisMovieListURI)
                            .queryParam("key", apiKey)
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
                            .path(kobisMovieDetailInfoURI)
                            .queryParam("key", apiKey)
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
