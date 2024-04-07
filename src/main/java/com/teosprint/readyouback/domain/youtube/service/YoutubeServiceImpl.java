package com.teosprint.readyouback.domain.youtube.service;

import com.google.api.services.youtube.YouTube;
import com.teosprint.readyouback.domain.youtube.dto.response.VideoIframeResponse;
import com.teosprint.readyouback.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    public VideoIframeResponse getVideoIframe(String link) {
        YouTube.Videos.List request = null;
        try {
            request = youTube.videos().list(Collections.singletonList("player"));
        } catch (IOException e) {
            throw new IllegalStateException("유튜브 링크 조회용 request 객체 생성 실패");
        }
        String videoId = extractVideoId(link);
        checkVideoId(videoId);
        request.setKey(apiKey);
        request.setId(Collections.singletonList(videoId));

        List<com.google.api.services.youtube.model.Video> videoList = null;
        try {
            videoList = request.execute().getItems();
        } catch (IOException e) {
            throw new CustomException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    200,
                    "embeded link 조회 실패: " + e.getMessage()
            );
        }

        if (videoList != null && !videoList.isEmpty()) {
            System.out.println(videoList.get(0).getPlayer().getEmbedHtml());
            return new VideoIframeResponse(videoList.get(0).getPlayer().getEmbedHtml());
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, 100, "유효하지 않응 영상 링크입니다. 유튜브 영상 링크를 입력하세요.");
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

    private void checkVideoId(String videoId) {
        if (videoId == null || videoId.isEmpty() || videoId.length() == 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, 100, "유효하지 않응 영상 링크입니다. 유튜브 영상 링크를 입력하세요.");
        }
    }
}
