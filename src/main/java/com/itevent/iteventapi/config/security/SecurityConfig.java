package com.itevent.iteventapi.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // rest api이므로 기본설정 안함. 기본설정은 비인증 시 로그인폼 화면으로 리다이렉트.
                .csrf().disable()       // rest api이므로 csrf 보안 필요 없음.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 토큰으로 인증하므로 세션 필요 없음.
                .and()
                    .authorizeRequests()
                        .antMatchers("/", "/h2-console/**", "/events", "/events/*", "/join", "/account/*", "/settings/*").permitAll()
                        .antMatchers("/login").hasRole("USER")
                        .anyRequest().authenticated()
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣는다.
    }
}