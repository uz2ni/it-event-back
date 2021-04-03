package com.itevent.iteventapi.modules.event.dto;

import com.itevent.iteventapi.modules.event.Event;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class EventListResDto {

    private List<EventResDto> events;

    public EventListResDto(List<Event> list) {
        List<EventResDto> events = list.stream()
                .map(event -> EventResDto.of(event))
                .collect(Collectors.toList());

        this.events = events;
    }

}