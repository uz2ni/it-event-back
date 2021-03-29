package com.itevent.iteventapi.modules.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itevent.iteventapi.common.CommonField;
import com.itevent.iteventapi.common.utils.ModelMapperUtils;
import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Event extends CommonField {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventCreateType eventCreateType;

    @Column(nullable = false)
    private Long hostId; // Todo: Account 매핑 예정

    @Column
    private String hostEmail;

    @Column
    private String hostPhone;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime eventStartDate;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime eventLastDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventConceptType eventConceptType;

    @Column
    private boolean isEmailReserveSending;

    @Lob
    private String emailSendingMessage;

    @Column
    private boolean isOnline;

    @Column(nullable = false)
    private String location;

    @Column
    private String detailLocation;

    @Column
    private String locationDescription;

    @Lob
    private String image;

    @Lob
    @Column(nullable = false)
    private String contents;

    @Column
    private String onlinePlatformInfo;

    @Column
    private String onlineEnrollInfo;

    public static Event of(EventReqDto eventReqDto) {
        return ModelMapperUtils.getModelMapper().map(eventReqDto, Event.class);
    }

    public static void of(EventReqDto eventReqDto, Event event) {
        ModelMapperUtils.getModelMapper().map(eventReqDto, event);
    }
}