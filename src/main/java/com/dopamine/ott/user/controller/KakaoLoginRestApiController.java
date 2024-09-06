package com.dopamine.ott.user.controller;

import com.dopamine.ott.user.connector.KaKaoApiClientContent;
import com.dopamine.ott.user.dto.UserInfo;
import com.dopamine.ott.user.svc.KakaoLoginSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class KakaoLoginRestApiController {

    private final KakaoLoginSvc kakaoLoginSvc;


    @GetMapping("/login/auth")
    public ResponseEntity<UserInfo> auth(@RequestParam String code) {
        try {
            return ResponseEntity.ok(kakaoLoginSvc.getUserInfo(code));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/login/code")
    public ResponseEntity<String> code() {
        return ResponseEntity.ok(kakaoLoginSvc.getKakaoAuthRedirectUri());
    }

}
