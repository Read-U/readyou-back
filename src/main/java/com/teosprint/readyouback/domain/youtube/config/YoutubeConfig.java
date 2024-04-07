package com.teosprint.readyouback.domain.youtube.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class YoutubeConfig {

    @Bean
    public JsonFactory getJsonFactory() {
        return GsonFactory.getDefaultInstance();
    }

    @Bean
    public YouTube getYouTubeService(JsonFactory jsonFactory) {
        try {
            return new YouTube.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    jsonFactory,
                    null
            )
                    .setApplicationName("ReadYou")
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            throw new IllegalStateException("youtuve error");
        }
    }
}
