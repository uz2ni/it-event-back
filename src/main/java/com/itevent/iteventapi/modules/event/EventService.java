package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.modules.account.Account;
import com.itevent.iteventapi.modules.account.AccountService;
import com.itevent.iteventapi.modules.event.dto.EventListResDto;
import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import com.itevent.iteventapi.modules.event.dto.EventResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AccountService accountService;
    private final EnrollmentRepository enrollmentRepository;

    public EventResDto getEvent(Long id) {
        Event event = getEventAndExistCheck(id);
        return EventResDto.of(event);
    }

    public EventListResDto getEventAll() {
        List<Event> events = eventRepository.findAll();

        return new EventListResDto(events);
    }

    public EventResDto createEvent(EventReqDto eventReqDto, String email) {
        Account account = accountService.getAccountAndExistCheck(email);
        Event event = Event.of(eventReqDto, account);
        event = eventRepository.save(event);

        return EventResDto.of(event);
    }

    public EventResDto updateEvent(Long id, EventReqDto eventReqDto) {
        Event event = getEventAndExistCheck(id);
        Event.of(eventReqDto, event);
        System.out.println(event.toString());

        return EventResDto.of(event);
    }

    public void deleteEvent(Long id) {
        Event event = getEventAndExistCheck(id);
        eventRepository.deleteById(id);
    }

    private Event getEventAndExistCheck(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if(event == null) {
            throw new IllegalArgumentException("요청한 이벤트가 없습니다. [id : " + id + "]");
        }
        return event;
    }

    public void newEnrollment(Account account, Event event) {
        if(enrollmentRepository.existsByEventAndAccount(event, account)) {
            throw new IllegalArgumentException("이미 참가 신청된 계정입니다.");
        }
        Enrollment enrollment = Enrollment.builder().account(account).build();
        enrollmentRepository.save(enrollment);
        event.addEnrollment(enrollment);
    }

}