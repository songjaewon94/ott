package com.dopamine.ott.user.connector;

import com.dopamine.ott.common.enums.ContentType;
import com.dopamine.ott.common.enums.HttpHeaders;
import com.dopamine.ott.user.config.KakaoApiProperties;
import com.dopamine.ott.user.dto.KakaoUserInfo;
import com.dopamine.ott.user.dto.UserInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class KaKaoApiClientContent implements SnsLoginWebClientFactory{


    private final WebClient webClient;
    private final WebClient webClientKakaoUser;
    private final KakaoApiProperties kakaoApiProperties;

    public KaKaoApiClientContent(WebClient.Builder webClientBuilder, KakaoApiProperties kakaoApiProperties) {
        this.kakaoApiProperties = kakaoApiProperties;
        this.webClient = webClientBuilder.baseUrl(kakaoApiProperties.getDomain()).build();
        this.webClientKakaoUser = webClientBuilder.baseUrl("https://kapi.kakao.com").build();
    }

    @Override
    public String getUserInfo(String code) {
        try {
            return  webClientKakaoUser.post()
                    .uri(kakaoApiProperties.getUris().getUserInfo())
                    .header(HttpHeaders.AUTHORIZATION.getHeaderName(), "Bearer " + getAccessToken(code))
                    .header(HttpHeaders.CONTENT_TYPE.getHeaderName(), ContentType.FORM_URLENCODED.getMediaType())
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

        Map<String, String> formData = new HashMap<>();
        formData.put("grant_type", "authorization_code");
        formData.put("client_id", kakaoApiProperties.getKey());
        formData.put("redirect_uri", kakaoApiProperties.getRedirectUri());
        formData.put("code", code);

        String response = webClient.post()
                .uri(kakaoApiProperties.getUris().getToken())
                .header(HttpHeaders.CONTENT_TYPE.getHeaderName(), MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 블로킹 방식으로 동기 호출
        try {
            JsonNode root = new ObjectMapper().readTree(response);
            accessToken = root.path("access_token").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accessToken;
    }





}
