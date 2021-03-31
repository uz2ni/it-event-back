package com.itevent.iteventapi.modules.account;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

@Getter
public class UserAccount extends User {

	private Account account;

	public UserAccount(Account account) {
		super(account.getEmail(), account.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		this.account = account;
	}
}