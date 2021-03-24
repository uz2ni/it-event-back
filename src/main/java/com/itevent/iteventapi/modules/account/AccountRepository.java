package com.itevent.iteventapi.modules.account;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Account findByNickname(String nickname);
}
