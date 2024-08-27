package com.dopamine.ott.contents.config;

import com.dopamine.ott.common.config.ApiProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = "api.contents.kobis")
@Data
public class KobisApiProperties extends ApiProperties {
    private Uris uris;
    @Data
    public static class Uris{
        private String movieList;
        private String movieDetailInfo;

    }

}
