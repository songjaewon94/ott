package com.dopamine.ott.test;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class KakaoLoginRestApiTest {
    @Value("${kakao.api_key}")
    private String kakaoApiKey;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;



    private WebClient webClient;


    public KakaoLoginRestApiTest() {
        this.webClient = WebClient.create();
    }

    @PostMapping("/login/auth")
    public void redirectToKakao() {
        String url = UriComponentsBuilder.fromHttpUrl("https://kauth.kakao.com/oauth/authorize")
                .queryParam("client_id", kakaoApiKey)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", "code")
                .toUriString();

        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();  // 실제 비동기 환경에서는 block() 대신 subscribe() 사용

        System.out.println(response); // 응답 출력
    }

}
