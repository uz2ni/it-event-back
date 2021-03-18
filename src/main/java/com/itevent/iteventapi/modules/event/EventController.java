package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.modules.event.dto.EventListResDto;
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
    public ResponseEntity<JsonResponse> addEvent(@RequestBody EventReqDto eventReqDto) {
        EventResDto eventResDto = eventService.createEvent(eventReqDto);
        return new ResponseEntity<JsonResponse>(new JsonResponse(eventResDto), HttpStatus.OK);
    }

    @PatchMapping("/events/{id}")
    public ResponseEntity<JsonResponse> updateEvent(@PathVariable Long id, @RequestBody EventReqDto eventReqDto) {
        EventResDto eventResDto = eventService.updateEvent(id, eventReqDto);
        return new ResponseEntity<JsonResponse>(new JsonResponse(eventResDto), HttpStatus.OK);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<JsonResponse> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
    }

}
