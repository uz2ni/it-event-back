package com.itevent.iteventapi.modules.account.dto;

import com.itevent.iteventapi.modules.account.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class AccountResDto {

    Map<String, Object> account = new HashMap<>();

    public AccountResDto(Account account) {
        this.account.put("email", account.getEmail());
        this.account.put("nickname", account.getNickname());
        this.account.put("createdDate", account.getCreatedDate());
        this.account.put("updatedDate", account.getUpdatedDate());
        this.account.put("id", account.getId());
        this.account.put("emailVerified", account.isEmailVerified());
    }
}