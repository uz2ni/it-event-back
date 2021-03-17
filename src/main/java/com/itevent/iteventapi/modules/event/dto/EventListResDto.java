package com.itevent.iteventapi.modules.event.dto;

import com.itevent.iteventapi.modules.event.Event;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EventListResDto {
    List<Event> events = new ArrayList<>();

//    public EventListResDto(List<EventResDto> events) {
//        events.forEach(dto -> this.events.add(dto));
//    }
}
