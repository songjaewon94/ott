package com.dopamine.ott.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;

public class JavaCodeTestMain {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String webClientCall() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://kobis.or.kr/kobisopenapi/webservice/rest/movie")
                .build();

   String result = webClient.get()
                .uri("/searchMovieList.json?key=f5eef3421c602c6cb7ea224104795888")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return result;
    }

    public static void main(String[] args) {
        webClientCall();
    }

}
