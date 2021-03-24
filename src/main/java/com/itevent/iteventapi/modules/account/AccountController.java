package com.itevent.iteventapi.modules.account;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.modules.account.dto.AccountJoinDto;
import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import com.itevent.iteventapi.modules.account.validate.JoinValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final JoinValidator joinValidator;

    @InitBinder("accountJoinDto")
    public void initBinder(WebDataBinder webDataBinder) { webDataBinder.addValidators(joinValidator); }

    @PostMapping("/join")
    public ResponseEntity<JsonResponse> join(@Valid @RequestBody AccountJoinDto accountJoinDto) {
        AccountResDto accountResDto = accountService.createAccount(accountJoinDto);
        return new ResponseEntity<JsonResponse>(new JsonResponse(accountResDto), HttpStatus.OK);
    }

    @GetMapping("/account/{nickname}")
    public ResponseEntity<JsonResponse> getAccount(@PathVariable String nickname) {
        AccountResDto accountResDto = accountService.getAccount(nickname);
        return new ResponseEntity<JsonResponse>(new JsonResponse(accountResDto), HttpStatus.OK);
    }

}
