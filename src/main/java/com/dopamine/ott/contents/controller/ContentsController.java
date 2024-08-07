package com.dopamine.ott.contents.controller;


import com.dopamine.ott.contents.connector.KorContentApiClientContent;
import com.dopamine.ott.contents.svc.KorContentService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/contents")
public class ContentsController {


    private final KorContentService korContentService;

    @GetMapping("/list")
    public ResponseEntity<?> getContentsAll() {
        try {
            return ResponseEntity.ok(korContentService.getContentServiceList());
        } catch (RuntimeException e) {
            log.error("Error fetching movie list", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/detail/{movieCd}")
    public ResponseEntity<?> getContentsDetail(@PathVariable("movieCd") String movieCd) {
        try {
            return ResponseEntity.ok(korContentService.getContentDetail(movieCd));
        } catch (RuntimeException e) {
            log.error("Error fetching movie list", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
