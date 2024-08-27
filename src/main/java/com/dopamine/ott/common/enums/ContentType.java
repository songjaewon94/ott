package com.dopamine.ott.common.enums;

public enum ContentType {
    FORM_URLENCODED("application/x-www-form-urlencoded;charset=utf-8"),
    JSON("application/json");

    private final String mediaType;

    ContentType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }
}