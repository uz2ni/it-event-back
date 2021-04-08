package com.itevent.iteventapi.common.error;

/* 컨트롤러 파라미터 누락 */
public class CustomMissingServletRequestParameterException extends CustomException {

    public CustomMissingServletRequestParameterException() {
        super(ErrorCode.BAD_REQUEST);
    }

    public CustomMissingServletRequestParameterException(String message) {
        super(message, ErrorCode.BAD_REQUEST);
    }
}
