package com.itevent.iteventapi.modules.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itevent.iteventapi.common.utils.DateFormatUtils;
import com.itevent.iteventapi.common.utils.ModelMapperUtils;
import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import com.itevent.iteventapi.modules.event.Event;
import com.itevent.iteventapi.modules.event.EventConceptType;
import com.itevent.iteventapi.modules.event.EventCreateType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResDto {

    private Long id;

    private EventCreateType eventCreateType;

    private AccountResDto account;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatUtils.DEFAULT_DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatUtils.DEFAULT_DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime updatedDate;

    public static EventResDto of(Event event) {
        EventResDto eventResDto = ModelMapperUtils.getModelMapper().map(event, EventResDto.class);
        AccountResDto accountResDto = ModelMapperUtils.getModelMapper().map(event.getAccount(), AccountResDto.class);
        eventResDto.setAccount(accountResDto);
        return eventResDto;
    }
}