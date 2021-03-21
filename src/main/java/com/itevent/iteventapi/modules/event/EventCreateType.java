package com.itevent.iteventapi.modules.event;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum EventCreateType {
    INNER, OUTER;

    private static final Map<String, EventCreateType> stringToEnum =
            Stream.of(values()).collect(toMap(Objects::toString, e -> e));

    @JsonCreator
    public static EventCreateType fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

}