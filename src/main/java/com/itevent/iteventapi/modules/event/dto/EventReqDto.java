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

    @NotNull(message = " 올바른 타입 값을 입력하세요.(INNER / OUTER)")
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

    @NotNull(message = "올바른 타입 값을 입력하세요.(CLASS / ACTIVITY / CONFERENCE)")
    private EventConceptType eventConceptType;

    @NotNull
    private boolean isEmailReserveSending;

    private String emailSendingMessage;

    @NotNull
    private boolean isOnline;

    private String location;

    private String detailLocation;

    private String locationDescription;

    private String image;

    @NotBlank
    private String contents;

    private String onlinePlatformInfo;

    private String onlineEnrollInfo;

}