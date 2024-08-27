package com.dopamine.ott.user.config;

import com.dopamine.ott.common.config.prop.ApiProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = "api.user.kakao")
@Data
public class KakaoApiProperties extends ApiProperties {
    private String redirectUri;
    private Uris uris;
    @Data
    public static class Uris{
        private String userInfo;
        private String authorize;
        private String token;

    }


}
