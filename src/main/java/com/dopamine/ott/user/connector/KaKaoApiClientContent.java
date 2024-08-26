package com.dopamine.ott.user.connector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KaKaoApiClientContent implements SnsLoginWebClientFactory{

    @Override
    public String getUserInfo() {
        return null;
    }

    @Override
    public String getAccessToken() {
        return null;
    }
}
