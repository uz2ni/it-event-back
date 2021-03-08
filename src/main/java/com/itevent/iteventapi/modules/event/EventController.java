package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import com.itevent.iteventapi.modules.event.dto.EventResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventRepository eventRepository;

    @GetMapping("/events/{id}")
    public ResponseEntity<JsonResponse> getEvent(@PathVariable Long id) {

        Event event = eventRepository.findById(id).orElse(null);

        if(event == null) {
            return new ResponseEntity<JsonResponse>(new JsonResponse(HttpStatus.BAD_REQUEST, "요청 정보에 대한 데이터가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<JsonResponse>(new JsonResponse(event), HttpStatus.OK);
    }

    @PostMapping("/events")
    public ResponseEntity<JsonResponse> addEvent(@RequestBody EventReqDto eventReqDto) {
        EventResDto eventResDto = eventService.createEvent(eventReqDto);
        return new ResponseEntity<JsonResponse>(new JsonResponse(eventResDto), HttpStatus.OK);
    }

    @PatchMapping("/events/{id}")
    public ResponseEntity<JsonResponse> updateEvent(@PathVariable Long id, @RequestBody EventReqDto eventReqDto) {

        EventResDto eventResDto = eventService.updateEvent(eventReqDto, id);

        return new ResponseEntity<JsonResponse>(new JsonResponse(eventResDto), HttpStatus.OK);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<JsonResponse> deleteEvent(@PathVariable Long id) {
        System.out.println("event 삭제");

        return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
    }
}
