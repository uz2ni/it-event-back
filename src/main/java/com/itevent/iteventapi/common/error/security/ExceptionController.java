package com.itevent.iteventapi.common.error.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public void entrypointException() {
        throw new CAuthenticationEntryPointException();
    }

    @GetMapping("/accessdenied")
    public void accessDeniedException() throws AccessDeniedException {
        throw new AccessDeniedException("보유한 권한으로 접근할 수 없는 리소스입니다.");
    }

}
