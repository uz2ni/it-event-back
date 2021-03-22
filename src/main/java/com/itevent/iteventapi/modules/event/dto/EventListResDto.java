package com.itevent.iteventapi.modules.event.dto;

import com.itevent.iteventapi.modules.event.Event;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class EventListResDto {

    List<Event> events;

    public EventListResDto(List<Event> list) {
        events = list;
    }
}