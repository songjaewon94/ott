package com.dopamine.ott.contents.controller;


import com.dopamine.ott.contents.connector.KorContentApiClientContent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/contents")
public class ContentsController {

    private final KorContentApiClientContent koreanContentWebclientFactory;

    @GetMapping("/list")
    public ResponseEntity<?> getContentsAll() {
        String krMobieList = koreanContentWebclientFactory.getMobieList();
        return ResponseEntity.ok(krMobieList);
    }

}
