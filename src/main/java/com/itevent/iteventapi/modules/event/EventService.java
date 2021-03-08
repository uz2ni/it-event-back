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

    public EventResDto createEvent(EventReqDto eventReqDto) {
        Event event = Event.of(eventReqDto);
        event.setHostId(1L);
        Event newEvent = eventRepository.save(event);

        return EventResDto.of(newEvent);
    }

    public EventResDto updateEvent(EventReqDto eventReqDto, Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if(event == null) {
            throw new IllegalArgumentException("요청에 해당하는 행사가 없습니다.");
        }
        event = Event.of(eventReqDto);
        event.setId(id);
        eventRepository.save(event);

        return EventResDto.of(event);
    }
}
