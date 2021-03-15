package com.itevent.iteventapi.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

//    INVALID_PARAMETER(400, null, "Invalid Request Data"),
//    COUPON_EXPIRATION(410, "C001", "Coupon Was Expired"),
    NOT_FOUND(404, "E001", "Event Not Found");

    private final int status;
    private final String code;
    private final String message;

}
