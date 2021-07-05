package com.itevent.iteventapi.modules.heart;

import com.itevent.iteventapi.modules.account.Account;
import com.itevent.iteventapi.modules.account.AccountService;
import com.itevent.iteventapi.modules.heart.dto.HeartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HeartService {

    private final AccountService accountService;
    private final HeartRepository heartRepository;

    public void createHeart(HeartDto heartDto, Account account) {
        accountService.getAccountAndExistCheck(account.getEmail());
        Heart heart = Heart.of(heartDto, account);
        heartRepository.save(heart);
    }
}
