package com.itevent.iteventapi.modules.event.validate;

import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EventReqDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventReqDto eventReqDto = (EventReqDto)target;

        if(isNotValidEventLastDate(eventReqDto)) {
            errors.rejectValue("eventLastDate", "wrong.datetime", "행사 종료 일시를 정확히 입력하세요.");
        }
    }

    private boolean isNotValidEventLastDate(EventReqDto eventReqDto) {
        return eventReqDto.getEventLastDate().isBefore(eventReqDto.getEventStartDate());
    }
}
