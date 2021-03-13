package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import com.itevent.iteventapi.modules.event.dto.EventResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public EventResDto createEvent(EventReqDto eventReqDto) {
        Event event = Event.of(eventReqDto);
        event.setHostId(1L);
        Event newEvent = eventRepository.save(event);

        return EventResDto.of(newEvent);
    }

    public EventResDto updateEvent(Long id, EventReqDto eventReqDto) {
        Event event = getEvent(id);
        ExistEventCheck(event);
        Event.of(eventReqDto, event);
        System.out.println(event.toString());

        return EventResDto.of(event);
    }

    public void deleteEvent(Long id) {
        Event event = getEvent(id);
        ExistEventCheck(event);
        eventRepository.deleteById(id);
    }

    private void ExistEventCheck(Event event) {
        if(event == null) {
            throw new IllegalArgumentException("행사가 존재하지 않습니다.");
        }
    }

}