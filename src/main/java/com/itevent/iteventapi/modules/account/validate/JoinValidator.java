package com.itevent.iteventapi.modules.account.validate;

import com.itevent.iteventapi.modules.account.AccountRepository;
import com.itevent.iteventapi.modules.account.dto.AccountJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountJoinDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        AccountJoinDto accountJoinDto = (AccountJoinDto)target;

        if(isDuplicateEmail(accountJoinDto)) {
            errors.rejectValue("email", "wrong.email", "이미 사용중인 이메일 입니다.");
        }

        if(isDuplicateNickname(accountJoinDto)) {
            errors.rejectValue("nickname", "wrong.nickname", "이미 사용중인 닉네임 입니다.");
        }
    }

    private boolean isDuplicateEmail(AccountJoinDto accountJoinDto) {
        return accountRepository.existsByEmail(accountJoinDto.getEmail());
    }

    private boolean isDuplicateNickname(AccountJoinDto accountJoinDto) {
        return accountRepository.existsByNickname(accountJoinDto.getEmail());
    }


}
