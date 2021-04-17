package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.modules.account.AccountFactory;
import com.itevent.iteventapi.modules.account.AccountService;
import com.itevent.iteventapi.modules.account.WithAccount;
import com.itevent.iteventapi.modules.account.dto.AccountResDto;
import com.itevent.iteventapi.modules.event.dto.EventResDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired AccountService accountService;
    @Autowired AccountFactory accountFactory;
    @Autowired EventService eventService;
    @Autowired EnrollmentRepository enrollmentRepository;
    @Autowired EventFactory eventFactory;

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach()");
    }

    @Test
    @WithAccount("guest") // 참가자
    @DisplayName("행사 참가 등록")
    public void enrollment_attend() throws Exception {

        AccountResDto host = accountFactory.createAccount("host"); // 주최자
        EventResDto eventResDto = eventFactory.createEvent(host);
        System.out.println(eventResDto.getAccount().getEmail());

        mockMvc.perform(post("/events/" + eventResDto.getId() + "/attend"))
                .andExpect(status().isOk())
                .andDo(print());

        List<Enrollment> list = enrollmentRepository.findAll();
        for(Enrollment e : list) {
            System.out.println("enrollment id:" + e.getId() + ", event id:" + e.getEvent().getId() + ", guest name:" + e.getAccount().getNickname());
        }
    }

    @Test
    @WithAccount("host") // 주최자
    @DisplayName("행사 참가 수락")
    public void helloTest() throws Exception {

        // 행사 생성
        AccountResDto host = accountFactory.createAccount("host"); // 주최자 생성
        EventResDto eventResDto = eventFactory.createEvent(host); // 행사 생성
        // 행사 참가
        AccountResDto guest = accountFactory.createAccount("guest"); // 참가자 생성

    }

}
