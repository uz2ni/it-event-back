package com.itevent.iteventapi.common.error.security;

import com.itevent.iteventapi.common.error.CustomException;
import com.itevent.iteventapi.common.error.ErrorCode;

public class CAuthenticationEntryPointException extends CustomException {

    private static final long serialVersionUID = -2116671122895194101L;

    public CAuthenticationEntryPointException() {
        super(ErrorCode.FORBIDDEN);
    }

    public CAuthenticationEntryPointException(String message) {
        super(message, ErrorCode.FORBIDDEN);
    }

}
