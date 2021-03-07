package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import com.itevent.iteventapi.modules.event.dto.EventResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventResDto createEvent(EventReqDto eventReqDto) {
        Event event = Event.of(eventReqDto);
        event.setHostId(1L);
        Event newEvent = eventRepository.save(event);
        System.out.println("event entity:");
        System.out.println(event);
//        System.out.println(event.getCreatedDate());
//        System.out.println(event.getUpdatedDate());
        return EventResDto.of(event);
    }
}
