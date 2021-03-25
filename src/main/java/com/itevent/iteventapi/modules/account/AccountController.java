package com.itevent.iteventapi.modules.account;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.modules.account.dto.AccountJoinDto;
import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import com.itevent.iteventapi.modules.account.dto.AccountUpdateDto;
import com.itevent.iteventapi.modules.account.validate.JoinValidator;
import com.itevent.iteventapi.modules.account.validate.UpdateNicknameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final JoinValidator joinValidator;
    private final UpdateNicknameValidator updateNicknameValidator;

    @InitBinder("accountJoinDto")
    public void joinInitBinder(WebDataBinder webDataBinder) { webDataBinder.addValidators(joinValidator); }

    @InitBinder("accountUpdateDto.nickname")
    public void updateNicknameInitBinder(WebDataBinder webDataBinder) { webDataBinder.addValidators(updateNicknameValidator); }

    @PostMapping("/join")
    public ResponseEntity<JsonResponse> join(@Valid @RequestBody AccountJoinDto accountJoinDto) {
        AccountResDto accountResDto = accountService.createAccount(accountJoinDto);
        return new ResponseEntity<JsonResponse>(new JsonResponse(accountResDto), HttpStatus.OK);
    }

    @GetMapping("/account/{nickname}")
    public ResponseEntity<JsonResponse> getAccount(@PathVariable String nickname) {
        AccountResDto accountResDto = accountService.getAccount(nickname);
        Map<String, AccountResDto> map = new HashMap<>();
        map.put("account", accountResDto);
        return new ResponseEntity<JsonResponse>(new JsonResponse(map), HttpStatus.OK);
    }

    @PatchMapping("/account/profile")
    public ResponseEntity<JsonResponse> updateNickname(@Valid @RequestBody AccountUpdateDto.Nickname accountUpdateDto) {
        AccountResDto accountResDto = accountService.updateNickname(accountUpdateDto);
        Map<String, AccountResDto> map = new HashMap<>();
        map.put("account", accountResDto);
        return new ResponseEntity<JsonResponse>(new JsonResponse(map), HttpStatus.OK);
    }

    @PatchMapping("/account/profile")
    public ResponseEntity<JsonResponse> updateNickname(@Valid @RequestBody AccountUpdateDto.Password accountUpdateDto) {

        Map<String, AccountResDto> map = new HashMap<>();
        return new ResponseEntity<JsonResponse>(new JsonResponse(map), HttpStatus.OK);
    }

}