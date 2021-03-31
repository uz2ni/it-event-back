package com.itevent.iteventapi.modules.account;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.config.security.JwtTokenProvider;
import com.itevent.iteventapi.modules.account.dto.AccountJoinDto;
import com.itevent.iteventapi.modules.account.dto.AccountLoginDto;
import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import com.itevent.iteventapi.modules.account.validate.JoinValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final JoinValidator joinValidator;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;



    @InitBinder("accountJoinDto")
    public void joinInitBinder(WebDataBinder webDataBinder) { webDataBinder.addValidators(joinValidator); }


    @PostMapping("/join")
    public ResponseEntity<JsonResponse> join(@Valid @RequestBody AccountJoinDto accountJoinDto) {
        AccountResDto accountResDto = accountService.createAccount(accountJoinDto);
        return new ResponseEntity<>(new JsonResponse(accountResDto), HttpStatus.OK);
    }

    @GetMapping("/account/{nickname}")
    public ResponseEntity<JsonResponse> getAccount(@PathVariable String nickname) {
        AccountResDto accountResDto = accountService.getAccount(nickname);
        Map<String, AccountResDto> map = new HashMap<>();
        map.put("account", accountResDto);
        return new ResponseEntity<>(new JsonResponse(map), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JsonResponse> login(@RequestBody AccountLoginDto accountLoginDto) {

        AccountResDto accountResDto = accountService.getAccount(accountLoginDto.getEmail());

        // 비밀번호 일치 확인
        if(!passwordEncoder.matches(accountLoginDto.getPassword(), accountResDto.getPassword())) {
            System.out.println("비밀번호 불일치");
        }

        Map<String, String> map = new HashMap<>();
        map.put("token", jwtTokenProvider.createToken(String.valueOf(accountResDto.getEmail()), Arrays.asList("ROLE_USER")));

        return new ResponseEntity<>(new JsonResponse(map), HttpStatus.OK);
    }

}