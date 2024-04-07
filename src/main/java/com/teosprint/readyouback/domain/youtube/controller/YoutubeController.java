package com.teosprint.readyouback.domain.youtube.controller;

import com.teosprint.readyouback.domain.youtube.dto.response.VideoIframeResponse;
import com.teosprint.readyouback.domain.youtube.service.YoutubeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/youtube")
@RequiredArgsConstructor
public class YoutubeController {
    private final YoutubeServiceImpl youtubeService;

    @GetMapping
    public VideoIframeResponse getVideoIframe(@RequestParam String link) {
        log.info(link);
        return youtubeService.getVideoIframe(link);
    }
}
