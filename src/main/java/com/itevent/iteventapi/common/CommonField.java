package com.itevent.iteventapi.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@Data
public abstract class CommonField {

    private static final long serialVersionUID = -4808539823377841033L;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";


    @Column( nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = "Asia/Seoul")
    private Timestamp createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = "Asia/Seoul")
    private Timestamp updatedDate;

//
    @PrePersist
    protected void onCreate() {
        createdDate = Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
    }
}
