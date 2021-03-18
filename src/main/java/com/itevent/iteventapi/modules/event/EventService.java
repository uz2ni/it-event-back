package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.common.error.CustomNotFoundException;
import com.itevent.iteventapi.modules.event.dto.EventListResDto;
import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import com.itevent.iteventapi.modules.event.dto.EventResDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventResDto getEvent(Long id) {
        Event event = getEventAndExistCheck(id);
        return new EventResDto(event);
    }

    public EventListResDto getEventAll() {
        List<Event> events = eventRepository.findAll();

        return new EventListResDto(events);
    }

    public EventResDto createEvent(EventReqDto eventReqDto) {
        Event event = Event.of(eventReqDto);
        event.setHostId(1L);
        Event newEvent = eventRepository.save(event);

        return new EventResDto(newEvent);
    }

    public EventResDto updateEvent(Long id, EventReqDto eventReqDto) {
        Event event = getEventAndExistCheck(id);
        Event.of(eventReqDto, event);
        System.out.println(event.toString());

        return new EventResDto(event);
    }

    public void deleteEvent(Long id) {
        Event event = getEventAndExistCheck(id);
        eventRepository.deleteById(id);
    }

    private Event getEventAndExistCheck(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if(event == null) {
            throw new IllegalArgumentException("요청한 이벤트가 없습니다. [id : " + id + "]");
        }
        return event;
    }

}