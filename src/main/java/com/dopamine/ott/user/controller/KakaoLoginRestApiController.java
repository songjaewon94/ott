package com.dopamine.ott.user.controller;


import com.dopamine.ott.common.enums.ContentType;
import com.dopamine.ott.common.enums.HttpHeaders;
import com.dopamine.ott.user.config.KakaoApiProperties;
import com.dopamine.ott.user.connector.KaKaoApiClientContent;
import com.dopamine.ott.user.dto.KakaoUserInfo;
import com.dopamine.ott.user.dto.UserInfo;
import com.dopamine.ott.user.svc.KakaoLoginSvc;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KakaoLoginRestApiController {


    private final KaKaoApiClientContent kaKaoApiClientContent;
    private final KakaoLoginSvc kakaoLoginSvc;


    @GetMapping("/login/auth")
    public UserInfo redirecstToKakao(@RequestParam String code) {
        UserInfo kakaoUserInfo = kaKaoApiClientContent.getUserInfo(code);

        return kakaoUserInfo;
    }

    @GetMapping("/login/code")
    public String redirectToKakao() {
        return kakaoLoginSvc.getKakaoAuthRedirectUri();
    }





}
