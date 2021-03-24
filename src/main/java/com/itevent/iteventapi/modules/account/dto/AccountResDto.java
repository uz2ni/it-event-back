package com.itevent.iteventapi.modules.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itevent.iteventapi.config.ModelMapperUtils;
import com.itevent.iteventapi.modules.account.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountResDto {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private Long id;

    private String email;

    private String nickname;

    private boolean emailVerified;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime updatedDate;

    public static AccountResDto of(Account account) {
        return ModelMapperUtils.getModelMapper().map(account, AccountResDto.class);
    }

}