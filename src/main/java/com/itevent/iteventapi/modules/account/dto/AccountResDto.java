package com.itevent.iteventapi.modules.account.dto;

import com.itevent.iteventapi.modules.account.Account;
import lombok.Getter;

@Getter
public class AccountResDto {

    Account account;

    public AccountResDto(Account account) { this.account = account; }
}