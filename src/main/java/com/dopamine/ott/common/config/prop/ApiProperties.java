package com.dopamine.ott.common.config.prop;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class ApiProperties {
    private String key;
    private String authDomain;
    private String apiDomain;
}
