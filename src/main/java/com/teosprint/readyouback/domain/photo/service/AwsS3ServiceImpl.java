package com.teosprint.readyouback.domain.photo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.teosprint.readyouback.domain.photo.dto.response.PhotoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsS3ServiceImpl implements AwsS3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    @Override
    public PhotoResponse uploadImage(MultipartFile profileImage) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + profileImage.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(profileImage.getInputStream().available());
        objMeta.setContentType(profileImage.getContentType());

        amazonS3.putObject(bucket, s3FileName, profileImage.getInputStream(), objMeta);
        log.info(String.format("사진 업로드 [파일명 : %s]", s3FileName));

        return new PhotoResponse(amazonS3.getUrl(bucket, s3FileName).toString());
    }
}
