package com.itevent.iteventapi.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.itevent.iteventapi.common.utils.DateFormatUtils;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ErrorResponse {

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatUtils.DEFAULT_DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message; //예외 메시지 저장

    @Builder.Default
    private String code = ""; // 예외를 세분화하기 위한 사용자 지정 코드

    private int status; // HTTP 상태 값 저장 400, 404, 500 등

    @Builder.Default
    private Map<String, String> errors = new HashMap<>(); // 에러 내용 여러 건 나타낼 경우 사용
}
