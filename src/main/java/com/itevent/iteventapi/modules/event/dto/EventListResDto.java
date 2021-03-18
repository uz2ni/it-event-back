package com.itevent.iteventapi.modules.event.dto;

import com.itevent.iteventapi.modules.event.Event;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class EventListResDto {

    List<Map<Long, Event>> events = new ArrayList<>();


    public EventListResDto(List<Event> list) {
        Map<Long, Event> map = new HashMap<Long, Event>();
        list.forEach(event -> map.put(event.getId(), event));

        this.events.add(map);
    }
}
