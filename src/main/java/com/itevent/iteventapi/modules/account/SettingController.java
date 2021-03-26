package com.itevent.iteventapi.modules.account;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import com.itevent.iteventapi.modules.account.dto.AccountUpdateDto;
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
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingController {

	private final AccountService accountService;
	private final UpdateNicknameValidator updateNicknameValidator;

	@InitBinder("accountUpdateDto.nickname")
	public void updateNicknameInitBinder(WebDataBinder webDataBinder) { webDataBinder.addValidators(updateNicknameValidator); }

	@PatchMapping("/nickname")
	public ResponseEntity<JsonResponse> updateNickname(@Valid @RequestBody AccountUpdateDto.Nickname accountUpdateDto) {
		AccountResDto accountResDto = accountService.updateNickname(accountUpdateDto);
		Map<String, AccountResDto> map = new HashMap<>();
		map.put("account", accountResDto);
		return new ResponseEntity<JsonResponse>(new JsonResponse(map), HttpStatus.OK);
	}

	@PatchMapping("/password")
	public ResponseEntity<JsonResponse> updatePassword(@Valid @RequestBody AccountUpdateDto.Password accountUpdateDto) {
		accountService.updatePassword(accountUpdateDto);
		Map<String, AccountResDto> map = new HashMap<>();
		return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
	}
}
