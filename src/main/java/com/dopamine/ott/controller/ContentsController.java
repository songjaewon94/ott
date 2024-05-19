package com.dopamine.ott.controller;

import com.dopamine.ott.WebClientConfig;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/contents")
public class ContentsController {

    private static final Logger log = LogManager.getLogger(ContentsController.class);

    @Value("${contents.api.domAPI.url}")
    private String domUrl;

    @Value("${contents.api.domAPI.key}")
    private String domKey;

    private WebClientConfig webClientConfig;

    @GetMapping("/test")
    public void getContentsAll() {
        System.out.println(domUrl);
        System.out.println(domKey);

        String uri = UriComponentsBuilder.fromHttpUrl(domUrl+"/movieList")
                .queryParam("client_id", domKey)
                .queryParam("response_type", "code")
                .toUriString();

        try {
            WebClient webc = webClientConfig.webClientCreate();
            String response = webClientConfig.webClientCall(webc, uri);
            System.out.println(webc);

        } catch (RestClientException ex) {
            log.debug(" webclient error.{}, uri = {}", ex.toString(), uri);
        }


    }


}
