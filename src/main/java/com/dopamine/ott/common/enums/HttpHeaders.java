package com.dopamine.ott.common.enums;

public enum HttpHeaders {
    AUTHORIZATION("Authorization"),
    CONTENT_TYPE("Content-type");

    private final String headerName;

    HttpHeaders(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}