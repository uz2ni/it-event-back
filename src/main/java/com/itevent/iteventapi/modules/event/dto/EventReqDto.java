package com.itevent.iteventapi.modules.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itevent.iteventapi.modules.event.EventConceptType;
import com.itevent.iteventapi.modules.event.EventCreateType;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventReqDto {

    private EventCreateType eventCreateType;

    @Email
    private String hostEmail;

    private String hostPhone;

    @NotBlank
    private String title;

    @FutureOrPresent
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