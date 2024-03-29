package com.itevent.iteventapi.modules.account;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import com.itevent.iteventapi.modules.account.dto.AccountUpdateDto;
import com.itevent.iteventapi.modules.account.validate.UpdateNicknameValidator;
import com.itevent.iteventapi.modules.account.validate.UpdatePasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingController {

	private final AccountService accountService;
	private final UpdateNicknameValidator updateNicknameValidator;
	private final UpdatePasswordValidator updatePasswordValidator;

	@InitBinder("nickname")
	public void updateNicknameInitBinder(WebDataBinder webDataBinder) { webDataBinder.addValidators(updateNicknameValidator); }

	@InitBinder("password")
	public void updatePasswordInitBinder(WebDataBinder webDataBinder) { webDataBinder.addValidators(updatePasswordValidator); }

	@PatchMapping("/nickname")
	public ResponseEntity<JsonResponse> updateNickname(@CurrentAccount Account account, @Valid @RequestBody AccountUpdateDto.Nickname accountUpdateDto) {
		AccountResDto accountResDto = accountService.updateNickname(account, accountUpdateDto);
		Map<String, AccountResDto> map = new HashMap<>();
		map.put("account", accountResDto);
		return new ResponseEntity<JsonResponse>(new JsonResponse(map), HttpStatus.OK);
	}

	@PatchMapping("/password")
	public ResponseEntity<JsonResponse> updatePassword(@CurrentAccount Account account, @Valid @RequestBody AccountUpdateDto.Password accountUpdateDto) {
		accountService.updatePassword(account, accountUpdateDto);
		return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<JsonResponse> deleteAccount(@CurrentAccount Account account) {
		accountService.deleteAccount(account);
		return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
	}

}