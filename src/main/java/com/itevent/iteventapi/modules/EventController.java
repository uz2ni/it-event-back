package com.itevent.iteventapi.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EventController {

    @GetMapping("/event/{id}")
    public Event getEvent(@PathVariable Long id) {

        Event event = new Event();
        event.setId(1L);
        event.setTitle("이벤트 타이틀 테스트");

        System.out.println("event get");

        return event;
    }

    @PostMapping("/new-event")
    public Event newEvent(@RequestBody Event event) {
        System.out.println("event 생성");
        System.out.println(event.toString());
        return event;
    }

    @PatchMapping("/event/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        System.out.println("event 업데이트");

        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }


}
