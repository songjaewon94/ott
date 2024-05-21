package com.dopamine.ott.contents;

import org.springframework.web.reactive.function.client.WebClient;

public interface ApiHandler {

    String apiCall(WebClient webClient, String uri);

}
