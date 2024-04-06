package com.teosprint.readyouback.domain.photo.controller;

import com.teosprint.readyouback.domain.photo.dto.response.PhotoResponse;
import com.teosprint.readyouback.domain.photo.service.AwsS3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/img")
@RequiredArgsConstructor
@Tag(name = "Image", description = "이미지 관련 API")
public class PhotoController {
    private final AwsS3Service awsS3Service;

    @Operation(summary = "이미지 첨부", description = "이미지를 첨부한 후 링크를 반환 받는 API")
    @PostMapping
    public PhotoResponse uploadImage(@RequestPart(name = "image") MultipartFile image) throws IOException {
        return awsS3Service.uploadImage(image);
    }
}
