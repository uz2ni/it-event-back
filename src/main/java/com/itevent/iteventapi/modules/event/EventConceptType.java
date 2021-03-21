package com.itevent.iteventapi.modules.event;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum EventConceptType {
    CLASS, ACTIVITY, CONFERENCE;

    private static final Map<String, EventConceptType> stringToEnum =
            Stream.of(values()).collect(toMap(Objects::toString, e -> e));

    @JsonCreator
    public static EventConceptType fromString(String symbol) {
        return stringToEnum.get(symbol);
    }
}
