package com.toyproject.bookmanagement.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.bookmanagement.aop.annotation.ValidAspect;
import com.toyproject.bookmanagement.dto.auth.LoginReqDto;
import com.toyproject.bookmanagement.dto.auth.SignupReqDto;
import com.toyproject.bookmanagement.service.AuthenticationService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService; 

	
	@ValidAspect
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginReqDto loginReqDto, BindingResult bindingResult) {
		
		return ResponseEntity.ok(authenticationService.signin(loginReqDto));
	}
	
	
	@ValidAspect
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult) {	// 검증 오류가 발생할 경우 오류 내용을 보관하는 스프링 프레임워크에서 제공하는 객체
		// 중복체크를 확인 후에 회원가입 실행되어야 함
		
		// 중복체크
		authenticationService.checkDuplicatedEmail(signupReqDto.getEmail());
		authenticationService.signup(signupReqDto);
		return ResponseEntity.ok().body(true);
	}
	
	
	@GetMapping("/authenticated") // 토큰 유효한지 확인해주는 코드
	public ResponseEntity<?> authenticated(String accessToken) {
		
		return ResponseEntity.ok().body(authenticationService.authenticated(accessToken));
	}
	
	
	@GetMapping("/principal")
	public ResponseEntity<?> principal(String accessToken) {
		return ResponseEntity.ok().body(authenticationService.getPrincipal(accessToken));
	}
	
	
	
}
