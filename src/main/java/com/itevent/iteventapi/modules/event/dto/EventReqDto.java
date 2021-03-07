package com.itevent.iteventapi.modules.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itevent.iteventapi.config.ModelMapperUtils;
import com.itevent.iteventapi.modules.event.Event;
import com.itevent.iteventapi.modules.event.EventConceptType;
import com.itevent.iteventapi.modules.event.EventCreateType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventReqDto {

    private EventCreateType eventCreateType;

    private String hostEmail;

    private String hostPhone;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime eventStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime eventLastDate;

    private EventConceptType eventConceptType;

    private boolean isEmailReserveSending;

    private String emailSendingMessage;

    private boolean isOnline;

    private String location;

    private String detailLocation;

    private String locationDescription;

    private String image;

    private String contents;

    private String onlinePlatformInfo;

    private String onlineEnrollInfo;

}