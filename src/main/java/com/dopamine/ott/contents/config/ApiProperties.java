package com.dopamine.ott.contents.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "contents.api")
public class ApiProperties {
    private Map<String, String> domAPI;
    private Map<String, String> intAPI;
}
