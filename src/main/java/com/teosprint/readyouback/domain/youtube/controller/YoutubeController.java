package com.teosprint.readyouback.domain.youtube.controller;

import com.teosprint.readyouback.domain.youtube.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/youtube")
@RequiredArgsConstructor
public class YoutubeController {
    private final YoutubeService youtubeService;

    @GetMapping
    public String getVideoIframe(String link) {
        return null;
    }
}
