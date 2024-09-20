package com.dopamine.ott.common.enums;

public enum AuthConstants {
    ACCESS_TOKEN("access_token"),
    AUTHORIZATION_CODE("authorization_code"),
    BEARER("Bearer ");
    private final String value;

    AuthConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
