package com.itevent.iteventapi.modules.account;

import com.itevent.iteventapi.modules.account.dto.AccountJoinDto;
import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import com.itevent.iteventapi.modules.account.dto.AccountUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountResDto createAccount(AccountJoinDto accountJoinDto) {
        Account account = saveNewAccount(accountJoinDto);

        return AccountResDto.of(account);
    }

    private Account saveNewAccount(AccountJoinDto accountJoinDto) {
        accountJoinDto.setPassword(getEncodePassword(accountJoinDto.getPassword()));
        Account account = Account.of(accountJoinDto);
        accountRepository.save(account);

        return account;
    }

    public AccountResDto getAccount(String nickname) {
        Account account = getAccountAndExistCheck(nickname);
        return AccountResDto.of(account);
    }

    public AccountResDto updateNickname(AccountUpdateDto.Nickname accountUpdateDto) {
        Account account = getAccountAndExistCheck(accountUpdateDto.getPrevNickname());
        account.setNickname(accountUpdateDto.getNewNickname());

        return AccountResDto.of(account);
    }

    public void updatePassword(AccountUpdateDto.Password accountUpdateDto) {
        Account account = getAccountAndExistCheck(accountUpdateDto.getNickname());
        String encodingPassword = getEncodePassword(accountUpdateDto.getNewPassword());
        account.setPassword(encodingPassword);
    }

    private Account getAccountAndExistCheck(String nickname) {
        Account account = accountRepository.findByNickname(nickname);
        if(account == null) {
            throw new IllegalArgumentException("요청한 계정이 없습니다. [nickname : " + nickname + "]");
        }
        return account;
    }

    private String getEncodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void deleteAccount(String nickname) {
        Account account = getAccountAndExistCheck(nickname);
        accountRepository.delete(account);
    }
}