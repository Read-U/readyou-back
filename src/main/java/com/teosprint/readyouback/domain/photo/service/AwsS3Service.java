package com.teosprint.readyouback.domain.photo.service;

import com.teosprint.readyouback.domain.photo.dto.response.PhotoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AwsS3Service {
    PhotoResponse uploadImage(MultipartFile multipartFile) throws IOException;
}
