package com.dopamine.ott.contents.controller;

import com.dopamine.ott.WebClientConfig;
import com.dopamine.ott.contents.config.ApiProperties;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/api/contents")
public class ContentsController {

    private static final Logger log = LogManager.getLogger(ContentsController.class);

    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private ApiProperties apiProperties;

    private String format = ".json";



    @GetMapping("/list")
    public void getContentsAll() {

        //우선 국내만
        Map<String, String> domApiInfo = apiProperties.getDomAPI();
        String apiUrl = domApiInfo.get("url");
        String apiKey = domApiInfo.get("key");

        // http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=f5eef3421c602c6cb7ea224104795888
        String uri = UriComponentsBuilder.fromHttpUrl(apiUrl + "/movie/searchMovieList"+format)
                .queryParam("client_id", apiKey)
                .queryParam("response_type", "code")
                .toUriString();

        try {
            WebClient webc = webClientConfig.webClientCreate();     //서비스로 바꾸기
            String response = webClientConfig.webClientCall(webc, uri);

            System.out.println(response);

        } catch (RestClientException ex) {
            log.debug(" webclient error.{}, uri = {}", ex.toString(), uri);
        }

    }


}
