package com.itevent.iteventapi.modules.account.validate;

import com.itevent.iteventapi.modules.account.AccountRepository;
import com.itevent.iteventapi.modules.account.dto.AccountUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UpdateValidator implements Validator {

	private final AccountRepository accountRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return AccountUpdateDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		AccountUpdateDto accountUpdateDto = (AccountUpdateDto)target;

		if(isDuplicateNickname(accountUpdateDto)) {
			errors.rejectValue("nickname", "wrong.nickname", "이미 사용중인 닉네임 입니다.");
		}

		if(isEmptyFields(accountUpdateDto)) {
			errors.rejectValue("nickname", "worng.emptyAll", "수정할 값을 정확히 입력하세요.");
		}


	}

	private boolean isDuplicateNickname(AccountUpdateDto accountUpdateDto) {
		return accountRepository.existsByNickname(accountUpdateDto.getNickname());
	}

	private boolean isEmptyFields(AccountUpdateDto accountUpdateDto) {
		return (accountUpdateDto.getNickname().isEmpty() && accountUpdateDto.getPassword().isEmpty()) ? true : false;
	}
}
