package com.itevent.iteventapi.modules.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

	private final AccountService accountService;
	private final  AccountFactory accountFactory;

	@Override
	public SecurityContext createSecurityContext(WithAccount withAccount) {
		String nickname = withAccount.value();

		accountFactory.createAccount(nickname);

		UserDetails principal = accountService.loadUserByUsername(nickname);
		Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		return context;
	}
}
