package com.itevent.iteventapi.modules.heart;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.itevent.iteventapi.modules.event.EventConceptType;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum HeartType {
    SHOP, PRODUCT;

    private static final Map<String, HeartType> stringToEnum =
            Stream.of(values()).collect(toMap(Objects::toString, e -> e));

    @JsonCreator
    public static HeartType fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

}
