package com.itevent.iteventapi.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

//    INVALID_PARAMETER(400, null, "Invalid Request Data"),
//    COUPON_EXPIRATION(410, "C001", "Coupon Was Expired"),
    NOT_FOUND(404, null, "Not Found"),
    FORBIDDEN(403, null, "해당 리소스에 접근 권한이 없습니다.");

    private final int status;
    private final String code;
    private final String message;

}
