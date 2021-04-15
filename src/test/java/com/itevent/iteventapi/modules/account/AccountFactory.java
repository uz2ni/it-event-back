package com.itevent.iteventapi.modules.account;

import com.itevent.iteventapi.modules.account.dto.AccountJoinDto;
import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountFactory {

	private final AccountService accountService;

	public AccountResDto createAccount(String nickname) {
		AccountJoinDto reqDto = AccountJoinDto.builder()
				.email(nickname + "@mail.com")
				.nickname(nickname)
				.password("12345678")
				.build();

		return accountService.createAccount(reqDto);
	}

}