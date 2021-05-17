package com.itevent.iteventapi;

import com.itevent.iteventapi.crawler.*;
import com.itevent.iteventapi.crawler.shop.mrStreetCrawler;
import com.itevent.iteventapi.modules.account.AccountService;
import com.itevent.iteventapi.modules.account.dto.AccountJoinDto;
import com.itevent.iteventapi.modules.event.EventConceptType;
import com.itevent.iteventapi.modules.event.EventCreateType;
import com.itevent.iteventapi.modules.event.EventService;
import com.itevent.iteventapi.modules.event.dto.EventReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class IteventApiApplication implements CommandLineRunner {

	private final EventService eventService;
	private final AccountService accountService;
	private final ShopRepository shopRepository;
	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(IteventApiApplication.class, args);
		System.out.println("main application running");
	}

	@Override
	public void run(String... args) throws Exception {

//		CreateAccountForTest();
//		CreateEventforTest();

		CrawlerTest();

	}

	private void CrawlerTest() throws IOException {
		// 쇼핑몰 생성
		Shop shop = Shop.builder().shopName("미스터스트리트").siteUrl("https://mr-s.co.kr/").build();
		shopRepository.save(shop);

		// 최신 상품 목록 크롤링
		Crawler crawler = new mrStreetCrawler(shop.getSiteUrl());
		List<Product> products = crawler.run();

		// 연관관계 설정
		for(Product product : products) {
			product = productRepository.save(product);
			shop.addProduct(product);
		}

		// DB 적재
		productRepository.saveAll(products);

	}


	private void CreateEventforTest() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

		LocalDateTime eventStartDate = LocalDateTime.parse("2021-03-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime eventLastDate = LocalDateTime.parse("2021-03-30 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		EventReqDto eventReqDto1 = EventReqDto.builder().eventCreateType(EventCreateType.INNER)
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

		eventStartDate = LocalDateTime.parse("2021-03-20 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		eventLastDate = LocalDateTime.parse("2021-03-25 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		EventReqDto eventReqDto2 = EventReqDto.builder().eventCreateType(EventCreateType.INNER)
				.hostEmail("test2@gmail.com")
				.hostPhone("010-1234-5678")
				.title("event title test 2 CLASS")
				.eventStartDate(eventStartDate)
				.eventLastDate(eventLastDate)
				.eventConceptType(EventConceptType.CLASS)
				.isEmailReserveSending(false)
				.emailSendingMessage(null)
				.isOnline(false)
				.location("위치 정보2")
				.detailLocation("상세 위치2")
				.locationDescription("위치 설명 정보2")
				.image(null)
				.contents("행사 내용2")
				.onlinePlatformInfo(null)
				.onlineEnrollInfo(null)
				.build();

		eventStartDate = LocalDateTime.parse("2021-04-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		eventLastDate = LocalDateTime.parse("2021-04-10 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		EventReqDto eventReqDto3 = EventReqDto.builder().eventCreateType(EventCreateType.INNER)
				.hostEmail("test3@gmail.com")
				.hostPhone("010-1234-5678")
				.title("event title test 3 ACTIVITY")
				.eventStartDate(eventStartDate)
				.eventLastDate(eventLastDate)
				.eventConceptType(EventConceptType.ACTIVITY)
				.isEmailReserveSending(false)
				.emailSendingMessage(null)
				.isOnline(false)
				.location("위치 정보3")
				.detailLocation("상세 위치3")
				.locationDescription("위치 설명 정보3")
				.image(null)
				.contents("행사 내용3")
				.onlinePlatformInfo(null)
				.onlineEnrollInfo(null)
				.build();

		eventService.createEvent(eventReqDto1, "test1@email.com");
		eventService.createEvent(eventReqDto2, "test1@email.com");
		eventService.createEvent(eventReqDto3, "test1@email.com");
	}

	private void CreateAccountForTest() {
		AccountJoinDto accountJoinDto = AccountJoinDto.builder()
			.nickname("test1")
			.email("test1@email.com")
			.password("12345678")
			.build();

		accountService.createAccount(accountJoinDto);
	}

}
