package com.dopamine.ott;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {


    /*
    @Bean
    public ReactorResourceFactory resourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false);
        return factory;
    }
    */


    @Bean
    public WebClient webClientCreate(){
            return WebClient.create();

    }



    public String webClientCall(WebClient wb, String uri){
        return wb.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 실제 비동기 환경에서는 block() 대신 subscribe() 사용
    }



}

