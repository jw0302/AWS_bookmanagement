package com.toyproject.bookmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.toyproject.bookmanagement.security.JwtAuthenticationEntryPoint;
import com.toyproject.bookmanagement.security.JwtAuthenticationFilter;
import com.toyproject.bookmanagement.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 이 메소드에서는 Security 사용할 때 꼭 작성해야 하는 config 기본 설정
		http.cors();
		http.csrf().disable();
		http.httpBasic().disable();
		http.formLogin().disable();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
			.antMatchers("/auth/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint);
	}
}
