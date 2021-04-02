package com.itevent.iteventapi.modules.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itevent.iteventapi.common.utils.DateFormatUtils;
import com.itevent.iteventapi.common.utils.ModelMapperUtils;
import com.itevent.iteventapi.modules.account.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountResDto {

    private Long id;

    private String email;

    private String nickname;

    private boolean emailVerified;

    @JsonIgnore
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatUtils.DEFAULT_DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatUtils.DEFAULT_DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime updatedDate;

    public static AccountResDto of(Account account) {
        return ModelMapperUtils.getModelMapper().map(account, AccountResDto.class);
    }

}