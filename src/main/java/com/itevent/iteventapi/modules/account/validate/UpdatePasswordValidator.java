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

		if (!isMatchPassword(accountUpdateDto)) {
			errors.rejectValue("newPassword", "wrong.newPassword", "입력한 새 패스워드가 일치하지 않습니다.");
		}
	}

	private boolean isMatchPassword(AccountUpdateDto.Password accountUpdateDto) {
		return accountUpdateDto.getNewPassword().equals(accountUpdateDto.getConfirmPassword());
	}
}
