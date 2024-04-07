package com.teosprint.readyouback.domain.youtube.service;

import com.google.api.services.youtube.YouTube;
import com.teosprint.readyouback.domain.youtube.dto.response.VideoIframeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class YoutubeServiceImpl {
    @Value("${youtube.api.key}")
    private String apiKey;
    private final YouTube youTube;

    public VideoIframeResponse getVideoIframe(String link) throws IOException {
        YouTube.Videos.List request = youTube.videos().list(Collections.singletonList("player"));
        String videoId = extractVideoId(link);
        request.setKey(apiKey);
        request.setId(Collections.singletonList(videoId));

        List<com.google.api.services.youtube.model.Video> videoList = request.execute().getItems();

        if (videoList != null && !videoList.isEmpty()) {
            System.out.println(videoList.get(0).getPlayer().getEmbedHtml());
            return new VideoIframeResponse(videoList.get(0).getPlayer().getEmbedHtml());
        } else {
            return new VideoIframeResponse(null);
        }
    }

    private static String extractVideoId(String link) {
        String videoId;

        // 유형 1: https://youtu.be/{videoId}
        Pattern pattern1 = Pattern.compile("youtu.be/(\\w+)");
        Matcher matcher1 = pattern1.matcher(link);
        if (matcher1.find()) {
            videoId = matcher1.group(1);
            return videoId;
        }

        // 유형 2: https://www.youtube.com/live/{videoId}
        Pattern pattern2 = Pattern.compile("youtube.com/live/(\\w+)");
        Matcher matcher2 = pattern2.matcher(link);
        if (matcher2.find()) {
            videoId = matcher2.group(1);
            return videoId;
        }

        // 유형 3: https://www.youtube.com/watch?v={videoId}
        String[] parts = link.split("v=");
        return parts[1];
    }
}
