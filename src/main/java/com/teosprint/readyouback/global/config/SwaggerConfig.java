package com.teosprint.readyouback.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi imageApi() {
        return GroupedOpenApi.builder()
                .group("image")
                .pathsToMatch("/api/img/**")
                .build();
    }

    @Bean
    public GroupedOpenApi youtubeApi() {
        return GroupedOpenApi.builder()
                .group("youtube")
                .pathsToMatch("/api/youtube/**")
                .build();
    }

    @Bean
    public GroupedOpenApi bedgeApi() {
        return GroupedOpenApi.builder()
                .group("bedge")
                .pathsToMatch("/api/bedge/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("API 명세서")
                        .description("ReadYOU API 명세서")
                        .version("v0.0.1"));
    }
}
