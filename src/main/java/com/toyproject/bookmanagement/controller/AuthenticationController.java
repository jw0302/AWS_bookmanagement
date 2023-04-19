package com.toyproject.bookmanagement.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.bookmanagement.aop.annotation.ValidAspect;
import com.toyproject.bookmanagement.dto.auth.SignupReqDto;
import com.toyproject.bookmanagement.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService; 

	
	@PostMapping("/login")
	public ResponseEntity<?> login() {
		return ResponseEntity.ok(null);
	}
	
	@CrossOrigin			// cros 에러 해결 코드 - cros : post보내는 곳의 port가 서로 다름 이럴땐 서버쪽에서 해결해줘야 함
	@ValidAspect
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult) {	// 검증 오류가 발생할 경우 오류 내용을 보관하는 스프링 프레임워크에서 제공하는 객체
		// 중복체크가 회원가입 전에 실행되어야 함
		authenticationService.checkDuplicatedEmail(signupReqDto.getEmail());
//		authenticationService.registerUser(signupReqDto);
		return ResponseEntity.ok(null);
	}
}
