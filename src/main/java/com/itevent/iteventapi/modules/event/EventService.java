package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import com.itevent.iteventapi.modules.event.dto.EventResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventResDto createEvent(EventReqDto.createReq eventReqDto) {
        Event event = Event.of(eventReqDto);
        event.setHostId(1L);
        Event newEvent = eventRepository.save(event);

        return EventResDto.of(newEvent);
    }

    public EventResDto updateEvent(EventReqDto.updateReq eventReqDto) {
        Event event = eventRepository.findById(eventReqDto.getId()).orElse(null);
        if(event == null) {
            throw new IllegalArgumentException("요청에 해당하는 행사가 없습니다.");
        }
        Event.of(eventReqDto, event);
        // 영속상태일까???
        System.out.println(event.toString());
//
//        eventRepository.save(event);

        return EventResDto.of(event);
    }
}
