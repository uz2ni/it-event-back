package com.itevent.iteventapi.modules.event.dto;

import com.itevent.iteventapi.modules.event.Event;
import lombok.Data;

@Data
public class EventResDto {

    Event event;

    public EventResDto(Event event) {
        this.event = event;
    }
}
