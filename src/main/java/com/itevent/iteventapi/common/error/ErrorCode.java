package com.itevent.iteventapi.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

//    INVALID_PARAMETER(400, null, "Invalid Request Data"),
//    COUPON_EXPIRATION(410, "C001", "Coupon Was Expired"),
    BAD_REQUEST(400, null, "잘못된 파라미터 요청입니다."),
    FORBIDDEN(403, null, "해당 리소스에 접근 권한이 없습니다."),
    NOT_FOUND(404, null, "Not Found");


    private final int status;
    private final String code;
    private final String message;

}
