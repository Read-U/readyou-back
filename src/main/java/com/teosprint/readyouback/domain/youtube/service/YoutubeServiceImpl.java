package com.teosprint.readyouback.domain.youtube.service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.teosprint.readyouback.domain.youtube.dto.response.VideoIdResponse;
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
public class YoutubeServiceImpl implements YoutubeService {
    @Value("${youtube.api.key}")
    private String apiKey;
    private final YouTube youTube;

    public VideoIdResponse getVideoId(String link) {
        String videoId = extractVideoId(link);
        checkVideoId(videoId);

        YouTube.Videos.List request = getYoutubeRequest();
        request.setKey(apiKey);
        request.setId(Collections.singletonList(videoId));

        List<Video> videoList = getVideoList(request);
        return getVideoIdResponse(videoList);
    }

    private static String extractVideoId(String link) {
        // 유형 1: https://youtu.be/{videoId}
        Pattern pattern = Pattern.compile("youtu.be/(\\w+)");
        Matcher matcher = pattern.matcher(link);
        if (matcher.find()) return matcher.group(1);

        // 유형 2: https://www.youtube.com/live/{videoId}
        pattern = Pattern.compile("youtube.com/live/(\\w+)");
        matcher = pattern.matcher(link);
        if (matcher.find()) return matcher.group(1);

        // 유형 3: https://www.youtube.com/watch?v={videoId}
        String[] parts = link.split("v=");
        return parts[1];
    }

    private void checkVideoId(String videoId) {
        if (videoId == null || videoId.isEmpty() || videoId.length() == 1) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    100,
                    "유효하지 않응 영상 링크입니다. 유튜브 영상 링크를 입력하세요.");
        }
    }

    private YouTube.Videos.List getYoutubeRequest() {
        try {
            return youTube.videos().list(Collections.singletonList("player"));
        } catch (IOException e) {
            throw new IllegalStateException("유튜브 링크 조회용 request 객체 생성 실패");
        }
    }

    private List<Video> getVideoList(YouTube.Videos.List request) {
        try {
            return request.execute().getItems();
        } catch (IOException e) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    100,
                    "유효하지 않응 영상 링크입니다. 유튜브 영상 링크를 입력하세요."
            );
        }
    }

    private VideoIdResponse getVideoIdResponse(List<Video> videoList) {
        if (videoList != null && !videoList.isEmpty()) {
            return new VideoIdResponse(videoList.get(0).getId());
        } else {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    100,
                    "유효하지 않응 영상 링크입니다. 유튜브 영상 링크를 입력하세요."
            );
        }
    }
}
