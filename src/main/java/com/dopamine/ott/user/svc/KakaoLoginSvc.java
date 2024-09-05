package com.dopamine.ott.user.svc;

import com.dopamine.ott.user.config.KakaoApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class KakaoLoginSvc {
    private final KakaoApiProperties kakaoApiProperties;

    public String getKakaoAuthRedirectUri(){
        return UriComponentsBuilder.fromHttpUrl(kakaoApiProperties.getDomain() + kakaoApiProperties.getUris().getAuthorize())
                .queryParam("client_id", kakaoApiProperties.getKey())
                .queryParam("redirect_uri", kakaoApiProperties.getRedirectUri())
                .queryParam("response_type", "code")
                .toUriString();
    }


}
