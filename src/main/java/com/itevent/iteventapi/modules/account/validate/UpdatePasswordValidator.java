package com.itevent.iteventapi.modules.account.validate;

import com.itevent.iteventapi.modules.account.Account;
import com.itevent.iteventapi.modules.account.AccountRepository;
import com.itevent.iteventapi.modules.account.dto.AccountUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UpdatePasswordValidator implements Validator {

	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean supports(Class<?> clazz) {
		return AccountUpdateDto.Password.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		AccountUpdateDto.Password accountUpdateDto = (AccountUpdateDto.Password)target;

		if(!isValidNickname(accountUpdateDto.getNickname())) {
			errors.rejectValue("nickname", "wrong.value", "존재하지 않는 계정입니다.");
		}

		if(!isValidPrevPassword(accountUpdateDto)) {
			errors.rejectValue("password", "wrong.value", "현재 패스워드가 일치하지 않습니다.");
		}

		if(!isMatchPassword(accountUpdateDto)) {
			errors.rejectValue("newPassword", "wrong.value", "입력한 새 패스워드가 일치하지 않습니다.");
		}

	}


	private boolean isValidNickname(String field) { return accountRepository.existsByNickname(field); }

	private boolean isValidPrevPassword(AccountUpdateDto.Password accountUpdateDto) {
		Account account = accountRepository.findByNickname(accountUpdateDto.getNickname());
		return passwordEncoder.matches(accountUpdateDto.getPassword(), account.getPassword());
	}

	private boolean isMatchPassword(AccountUpdateDto.Password accountUpdateDto) {
		return accountUpdateDto.getNewPassword().equals(accountUpdateDto.getConfirmPassword());
	}
}
