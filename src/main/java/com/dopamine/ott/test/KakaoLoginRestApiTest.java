package com.dopamine.ott.test;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
public class KakaoLoginRestApiTest {
    @Value("${kakao.api_key}")
    private String kakaoApiKey;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;


    private final ObjectMapper objectMapper;
    private WebClient webClient;
    private WebClient webClientKakaoUser;

    public KakaoLoginRestApiTest(WebClient.Builder webClientBuilder,ObjectMapper objectMapper ) {
        this.objectMapper = objectMapper;
        this.webClient = webClientBuilder.baseUrl("https://kauth.kakao.com").build();
        this.webClientKakaoUser = webClientBuilder.baseUrl("https://kapi.kakao.com").build();
    }
    public String getAccessToken(String code) {
        String accessToken = "";

        String response = webClient.post()
                .uri("/oauth/token")
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .bodyValue("grant_type=authorization_code&client_id=" + kakaoApiKey
                        + "&redirect_uri=" + redirectUri
                        + "&code=" + code)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 블로킹 방식으로 동기 호출
        try {
            JsonNode root = objectMapper.readTree(response);
            accessToken = root.path("access_token").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accessToken;
    }
    public HashMap<String, Object> getUserInfo(String accessToken) {
        HashMap<String, Object> userInfo = new HashMap<>();

        String response = webClientKakaoUser.post()
                .uri("/v2/user/me")
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 블로킹 방식으로 동기 호출

        System.out.println("responseBody = " + response);

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode properties = root.path("properties");
            JsonNode kakaoAccount = root.path("kakao_account");

            String nickname = properties.path("nickname").asText();
            String email = kakaoAccount.path("email").asText();

            userInfo.put("nickname", nickname);
            userInfo.put("email", email);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;
    }
    @GetMapping("/login/auth")
    public String redirecstToKakao(@RequestParam String code) {
        //토큰 얻기
        String accessToken = getAccessToken(code);
        Map<String, Object> userInfo = getUserInfo(accessToken);

        return userInfo.toString();
//        return url;
    }

    @GetMapping("/login/code")
    public String redirectToKakao() {
        String url = UriComponentsBuilder.fromHttpUrl("https://kauth.kakao.com/oauth/authorize")
                .queryParam("client_id", kakaoApiKey)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", "code")
                .toUriString();

//        String response = webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();  // 실제 비동기 환경에서는 block() 대신 subscribe() 사용

      return url;
    }

}
