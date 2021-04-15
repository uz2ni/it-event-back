package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import com.itevent.iteventapi.modules.event.dto.EnrollReqDto;
import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import com.itevent.iteventapi.modules.event.dto.EventResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class EventFactory {

	private final EventService eventService;
	private final EventRepository eventRepository;

	public EventResDto createEvent(AccountResDto host) {
		LocalDateTime eventStartDate = LocalDateTime.parse("2021-03-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime eventLastDate = LocalDateTime.parse("2021-03-30 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		EventReqDto eventReqDto = EventReqDto.builder().eventCreateType(EventCreateType.INNER)
				.hostEmail("test1@gmail.com")
				.hostPhone("010-1234-5678")
				.title("event title test 1 CONFERENCE")
				.eventStartDate(eventStartDate)
				.eventLastDate(eventLastDate)
				.eventConceptType(EventConceptType.CONFERENCE)
				.isEmailReserveSending(false)
				.emailSendingMessage(null)
				.isOnline(false)
				.location("위치 정보")
				.detailLocation("상세 위치")
				.locationDescription("위치 설명 정보")
				.image(null)
				.contents("행사 내용")
				.onlinePlatformInfo(null)
				.onlineEnrollInfo(null)
				.build();

		return eventService.createEvent(eventReqDto, host.getEmail());
	}

}
