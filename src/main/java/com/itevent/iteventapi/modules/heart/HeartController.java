package com.itevent.iteventapi.modules.heart;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.modules.account.Account;
import com.itevent.iteventapi.modules.account.CurrentAccount;
import com.itevent.iteventapi.modules.heart.dto.HeartDto;
import com.itevent.iteventapi.modules.heart.validate.HeartValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;
    private final HeartValidator heartValidator;

    @InitBinder("heartDto")
    public void initBinder(WebDataBinder webDataBinder) { webDataBinder.addValidators(heartValidator); }

    @PostMapping("/heart")
    public ResponseEntity<JsonResponse> addHeart(@CurrentAccount Account account, @Valid @RequestBody HeartDto heartDto) {
        heartService.createHeart(heartDto, account);
        return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
    }

    @DeleteMapping("/heart/{id}")
    public ResponseEntity<JsonResponse> deleteHeart(@CurrentAccount Account account, @PathVariable Long id) {
        heartService.deleteHeart(id, account);
        return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
    }


}
