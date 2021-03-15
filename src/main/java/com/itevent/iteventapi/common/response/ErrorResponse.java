package com.itevent.iteventapi.common.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message; //예외 메시지 저장

    @Builder.Default
    private String code = ""; // 예외를 세분화하기 위한 사용자 지정 코드

    private int status; // HTTP 상태 값 저장 400, 404, 500 등

}
