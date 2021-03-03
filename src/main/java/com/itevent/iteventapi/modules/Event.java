package com.itevent.iteventapi.modules;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Event {

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
    private LocalDateTime eventStartDate;

    @Column(nullable = false)
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

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

}