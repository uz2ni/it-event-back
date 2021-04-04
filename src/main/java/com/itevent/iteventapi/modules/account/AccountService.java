package com.itevent.iteventapi.modules.account;

import com.itevent.iteventapi.modules.account.dto.AccountJoinDto;
import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import com.itevent.iteventapi.modules.account.dto.AccountUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

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


    public AccountResDto getAccount(String name) {
        Account account = getAccountAndExistCheck(name);
        return AccountResDto.of(account);
    }

    public AccountResDto updateNickname(Account account, AccountUpdateDto.Nickname accountUpdateDto) {
        account.setNickname(accountUpdateDto.getNewNickname());
        accountRepository.save(account); // account는 Principal 객체로 받아온 상태라 persist 상태가 아님. save해야 업데이트 된다.
        return AccountResDto.of(account);
    }

    public void updatePassword(Account account, AccountUpdateDto.Password accountUpdateDto) {
        // 현재 패스워드가 일치하는지 확인
        prevPasswordValidCheck(account.getPassword(), accountUpdateDto.getPassword());

        String encodingPassword = getEncodePassword(accountUpdateDto.getNewPassword());
        account.setPassword(encodingPassword);
        accountRepository.save(account);
    }

    private void prevPasswordValidCheck(String prevPassword, String newPassword) {
        if(!passwordEncoder.matches(newPassword, prevPassword)) {
            throw new IllegalArgumentException("현재 패스워드가 일치하지 않습니다.");
        }
    }

    public Account getAccountAndExistCheck(String name) {
        Account account = accountRepository.findByNickname(name);
        if(account == null) {
            account = accountRepository.findByEmail(name);
            if(account == null) throw new IllegalArgumentException("요청한 계정이 없습니다.");
        }

        return account;
    }

    private String getEncodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = getAccountAndExistCheck(email);
        return new UserAccount(account); // Principal 객체 생성
    }
}