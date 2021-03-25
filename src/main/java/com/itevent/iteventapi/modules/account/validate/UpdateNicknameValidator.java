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

		if(isSameNickname(accountUpdateDto)) {
			errors.rejectValue("nickname", "wrong.nickname", "수정될 닉네임을 정확히 입력해주세요.");
		}

		if(!isValidNickname(accountUpdateDto.getPrevNickname())) {
			errors.rejectValue("nickname", "wrong.nickname", "존재하지 않는 계정입니다.");
		}

		if(isValidNickname(accountUpdateDto.getNewNickname())) {
			errors.rejectValue("nickname", "wrong.nickname", "이미 사용중인 닉네임 입니다.");
		}

	}

	private boolean isSameNickname(AccountUpdateDto.Nickname accountUpdateDto) {
		return accountUpdateDto.getNewNickname().equals(accountUpdateDto.getPrevNickname()) ? true : false;
	}

	private boolean isValidNickname(String field) {
		return accountRepository.existsByNickname(field);
	}

}
