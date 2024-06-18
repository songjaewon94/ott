package com.dopamine.ott.contents.controller;

import com.dopamine.ott.common.webclient.KoreanContentWebclientFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/contents")
public class ContentsController {


    private final KoreanContentWebclientFactory koreanContentWebclientFactory;



    @GetMapping("/list")
    public void getContentsAll() {
        String krMobieList = koreanContentWebclientFactory.getMobieList();
        log.info(krMobieList);

    }

}
