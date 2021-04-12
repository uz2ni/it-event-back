package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.common.CommonField;
import com.itevent.iteventapi.modules.account.Account;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Enrollment extends CommonField {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Event event;

    @ManyToOne
    private Account account;

    private boolean accepted;

    private boolean attended;

    public void checkAccepted() {
        if(this.accepted != true) {
            throw new IllegalArgumentException("참석 대기 상태입니다.");
        }
    }

    public void checkEventDateAndAttend(boolean attended) {
        LocalDateTime now = LocalDateTime.now();
        if(!(this.getEvent().getEventStartDate().isBefore(now) && now.isBefore(this.getEvent().getEventLastDate()))) {
            throw new IllegalArgumentException("유효한 행사 참석 시간이 아닙니다.");
        }
        this.attended = attended;

    }

}