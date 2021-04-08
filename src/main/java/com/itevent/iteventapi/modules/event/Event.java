package com.itevent.iteventapi.modules.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itevent.iteventapi.common.CommonField;
import com.itevent.iteventapi.common.utils.ModelMapperUtils;
import com.itevent.iteventapi.modules.account.Account;
import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Event extends CommonField {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventCreateType eventCreateType;

    @ManyToOne(optional = false) // @Column(nallable=false) 와 동일
    private Account account;

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

    @OneToMany(mappedBy = "event")
    @OrderBy("createdDate")
    private List<Enrollment> enrollments = new ArrayList<>();

    public static Event of(EventReqDto eventReqDto) {
        return ModelMapperUtils.getModelMapper().map(eventReqDto, Event.class);
    }

    public static void of(EventReqDto eventReqDto, Event event) {
        ModelMapperUtils.getModelMapper().map(eventReqDto, event);
    }

    public static Event of(EventReqDto eventReqDto, Account account) {
        Event event = ModelMapperUtils.getModelMapper().map(eventReqDto, Event.class);
        event.setAccount(account);
        return event;
    }

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
        enrollment.setEvent(this);
    }

    public void isHostCheck(Account account) {
        if(this.getAccount().getId() != account.getId()) {
            throw new IllegalArgumentException("행사 관계자가 아닙니다.");
        }
    }

}