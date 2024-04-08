package com.teosprint.readyouback.domain.youtube.service;

import com.teosprint.readyouback.domain.youtube.dto.response.VideoIdResponse;

public interface YoutubeService {
    VideoIdResponse getVideoId(String link);
}
