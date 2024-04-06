package com.teosprint.readyouback.domain.photo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AwsS3Service {
    String uploadImage(MultipartFile multipartFile) throws IOException;
}
