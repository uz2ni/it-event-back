package com.itevent.iteventapi.modules.event;

import com.itevent.iteventapi.modules.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
