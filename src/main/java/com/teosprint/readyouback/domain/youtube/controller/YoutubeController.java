package com.teosprint.readyouback.domain.youtube.controller;

import com.teosprint.readyouback.domain.youtube.dto.response.VideoIdResponse;
import com.teosprint.readyouback.domain.youtube.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/youtube")
@RequiredArgsConstructor
public class YoutubeController {
    private final YoutubeService youtubeService;

    @GetMapping
    public VideoIdResponse getVideoId(@RequestParam String link) {
        log.info(link);
        return youtubeService.getVideoId(link);
    }
}
