package com.dopamine.ott.user.svc;

import com.dopamine.ott.user.config.KakaoApiProperties;
import com.dopamine.ott.user.connector.KaKaoApiClientContent;
import com.dopamine.ott.user.dto.KakaoUserInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class KakaoLoginSvc {
    private final KaKaoApiClientContent kaKaoApiClientContent;
    private final KakaoApiProperties kakaoApiProperties;
    private final ObjectMapper objectMapper;

    public String getKakaoAuthRedirectUri(){
        return UriComponentsBuilder.fromHttpUrl(kakaoApiProperties.getDomain() + kakaoApiProperties.getUris().getAuthorize())
                .queryParam("client_id", kakaoApiProperties.getKey())
                .queryParam("redirect_uri", kakaoApiProperties.getRedirectUri())
                .queryParam("response_type", "code")
                .toUriString();
    }

    public KakaoUserInfo getUserInfo(String code) throws IOException {

        return parseKakaoUserInfo(kaKaoApiClientContent.getUserInfo(code));
    }


    public KakaoUserInfo getAccessToken(String code) throws IOException {

        return parseKakaoUserInfo(kaKaoApiClientContent.getAccessToken(code));
    }



    private KakaoUserInfo parseKakaoUserInfo(String response) throws IOException {
        JsonNode root = objectMapper.readTree(response);
        JsonNode properties = root.path("properties");

        String nickname = properties.path("nickname").asText(null);
        return KakaoUserInfo.builder()
                .nickname(nickname)
                .build();

    }

}
