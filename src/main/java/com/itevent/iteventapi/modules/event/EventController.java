package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.modules.account.Account;
import com.itevent.iteventapi.modules.account.AccountService;
import com.itevent.iteventapi.modules.account.CurrentAccount;
import com.itevent.iteventapi.modules.event.dto.EnrollReqDto;
import com.itevent.iteventapi.modules.event.dto.EventListResDto;
import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import com.itevent.iteventapi.modules.event.dto.EventResDto;
import com.itevent.iteventapi.modules.event.validate.EventValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventValidator eventValidator;
    private final AccountService accountService;

    @InitBinder("eventReqDto")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(eventValidator);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<JsonResponse> getEvent(@PathVariable Long id) {
        EventResDto eventResDto = eventService.getEvent(id);
        return new ResponseEntity<JsonResponse>(new JsonResponse(eventResDto), HttpStatus.OK);
    }

    @GetMapping("/events")
    public ResponseEntity<JsonResponse> getEventAll() {
        EventListResDto eventResDto = eventService.getEventAll();
        return new ResponseEntity<JsonResponse>(new JsonResponse(eventResDto), HttpStatus.OK);
    }

    @PostMapping("/events")
    public ResponseEntity<JsonResponse> addEvent(@Valid @RequestBody EventReqDto eventReqDto, Principal principal) {
        EventResDto eventResDto = eventService.createEvent(eventReqDto, principal.getName());
        return new ResponseEntity<JsonResponse>(new JsonResponse(eventResDto), HttpStatus.OK);
    }

    @PatchMapping("/events/{id}")
    public ResponseEntity<JsonResponse> updateEvent(@PathVariable Long id, @Valid @RequestBody EventReqDto eventReqDto) {
        EventResDto eventResDto = eventService.updateEvent(id, eventReqDto);
        return new ResponseEntity<JsonResponse>(new JsonResponse(eventResDto), HttpStatus.OK);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<JsonResponse> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
    }

    // 참가 신청
    @PostMapping("/events/{id}/attend")
    public ResponseEntity<JsonResponse> addEnrollment(@CurrentAccount Account account, @PathVariable("id") Event event) {
        eventService.newEnrollment(account, event);
        return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
    }

    @DeleteMapping("/events/{id}/cancel")
    public ResponseEntity<JsonResponse> cancelEnrollment(@CurrentAccount Account account, @PathVariable("id") Event event) {
        eventService.cancelEnrollment(account, event);
        return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
    }

    // 참가 허락 (주최자)
    @PostMapping("/events/{id}/accept")
    public ResponseEntity<JsonResponse> acceptEnrollment(@CurrentAccount Account account, @PathVariable("id") Event event, @RequestBody EnrollReqDto.Accept request) {
        Account attendee = accountService.getAccountAndExistCheck(request.getAttendeeName());
        eventService.acceptEnrollment(account, event, request.isAccepted(), attendee);
        return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
    }

    //참석 체크 (참가자)
    @PostMapping("/events/{id}/attend-check")
    public ResponseEntity<JsonResponse> attendEnrollment(@CurrentAccount Account account, @PathVariable("id") Event event, @RequestBody EnrollReqDto.Attend request) {
        eventService.attendEnrollment(account, event, request.isAttended());
        return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
    }


}
