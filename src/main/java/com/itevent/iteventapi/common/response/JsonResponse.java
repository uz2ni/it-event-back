package com.itevent.iteventapi.common.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class JsonResponse<T> {

    private int code;       // HttpStatus Code
    private String message;      // Http Default Message
    private T data;         // response data

    public JsonResponse() {
        this.code = HttpStatus.OK.value();
        this.message = "success";
    }

    public JsonResponse(T data) {
        this.code = HttpStatus.OK.value();
        this.message = "success";
        this.data = data;
    }

    public JsonResponse(HttpStatus status, String errorMessage) {
        this.code = status.value();
        this.message = status.getReasonPhrase();
    }

}
