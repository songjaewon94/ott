package com.dopamine.ott.user.connector;

import com.dopamine.ott.common.enums.ApiRequestKeys;
import com.dopamine.ott.common.enums.AuthConstants;
import org.springframework.http.HttpHeaders;
import com.dopamine.ott.user.config.KakaoApiProperties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

@Component
public class KaKaoApiClientContent implements SnsLoginWebClientFactory{


    private final WebClient webClient;
    private final WebClient webClientKakaoUser;
    private final KakaoApiProperties kakaoApiProperties;

    public KaKaoApiClientContent(WebClient.Builder webClientBuilder, KakaoApiProperties kakaoApiProperties) {
        this.kakaoApiProperties = kakaoApiProperties;
        this.webClient = webClientBuilder.baseUrl(kakaoApiProperties.getAuthDomain()).build();
        this.webClientKakaoUser = webClientBuilder.baseUrl(kakaoApiProperties.getApiDomain()).build();
    }

    @Override
    public String getUserInfo(String code) {
        try {
            String accessToken = getAccessToken(code);
            String authorizationHeader = String.format("%s%s", AuthConstants.BEARER.getValue(), accessToken);

            return webClientKakaoUser.post()
                    .uri(kakaoApiProperties.getUris().getUserInfo())
                    .headers(headers -> {
                        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);
                        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                    })
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getAccessToken(String code) {
        String accessToken = "";

        String response = webClient.post()
                .uri(kakaoApiProperties.getUris().getToken())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .header(HttpHeaders.ACCEPT_CHARSET,StandardCharsets.UTF_8.name())
                .bodyValue(createFormData(code))
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 블로킹 방식으로 동기 호출
        try {
            JsonNode root = new ObjectMapper().readTree(response);
            accessToken = root.path(AuthConstants.ACCESS_TOKEN.getValue()).asText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accessToken;
    }


    private MultiValueMap<String, String> createFormData(String code) {
        MultiValueMap<String, String> formDataMap = new LinkedMultiValueMap<>();
        formDataMap.setAll(Map.of(
                ApiRequestKeys.GRANT_TYPE, AuthConstants.AUTHORIZATION_CODE.getValue(),
                ApiRequestKeys.CLIENT_ID, kakaoApiProperties.getKey(),
                ApiRequestKeys.REDIRECT_URI, kakaoApiProperties.getRedirectUri(),
                ApiRequestKeys.CODE, code
        ));

        return formDataMap;
    }

}
