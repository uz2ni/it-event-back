package com.itevent.iteventapi.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonField {

    private static final long serialVersionUID = -4808539823377841033L;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime createdDate;


    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime updatedDate;

//    @Column( nullable = false, updatable = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = "Asia/Seoul")
//    private Timestamp createdDate;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = "Asia/Seoul")
//    private Timestamp updatedDate;

//    @PrePersist
//    protected void onCreate() {
//        createdDate = Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedDate = Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
//    }
}