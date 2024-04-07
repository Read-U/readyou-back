package com.teosprint.readyouback.global.exception.handler;

import com.teosprint.readyouback.global.exception.CustomException;
import com.teosprint.readyouback.global.exception.ExceptionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CustomException.class)
    private ResponseEntity<ExceptionResponseDto> customException(CustomException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(e.getStatus())
                .body(ExceptionResponseDto.builder()
                        .status(e.getStatus())
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<String> nullPointerException(NullPointerException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("널 포인터 오류 발생");
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> exception(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}