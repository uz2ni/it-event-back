package com.itevent.iteventapi.common.error;

public class CustomNotFoundException extends CustomException {

    private static final long serialVersionUID = -2116671122895194101L;

    public CustomNotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }

    public CustomNotFoundException(Long id) {
        super(id, ErrorCode.NOT_FOUND);
    }
}
