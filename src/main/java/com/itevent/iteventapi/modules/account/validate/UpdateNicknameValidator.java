package com.itevent.iteventapi.modules.account.validate;

import com.itevent.iteventapi.modules.account.AccountRepository;
import com.itevent.iteventapi.modules.account.dto.AccountUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UpdateNicknameValidator implements Validator {

	private final AccountRepository accountRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return AccountUpdateDto.Nickname.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		AccountUpdateDto.Nickname accountUpdateDto = (AccountUpdateDto.Nickname)target;

		if (isValidNickname(accountUpdateDto.getNewNickname())) {
			errors.rejectValue("newNickname", "wrong.newNickname", "이미 사용중인 닉네임 입니다.");
		}

	}

	private boolean isValidNickname(String field) {
		return accountRepository.existsByNickname(field);
	}

}