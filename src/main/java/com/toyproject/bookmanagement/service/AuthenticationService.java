package com.toyproject.bookmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.toyproject.bookmanagement.dto.auth.SignupReqDto;
import com.toyproject.bookmanagement.entity.Authority;
import com.toyproject.bookmanagement.entity.User;
import com.toyproject.bookmanagement.exception.CustomException;
import com.toyproject.bookmanagement.exception.ErrorMap;
import com.toyproject.bookmanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
	
	
	// 회원 가입 - 내가 만든 코드 -
//	public void registerUser(SignupReqDto signupReqDto) {
//		User userEntity = signupReqDto.toEntity();
//		userRepository.signupUser(userEntity);
//		
//		List<Authority> authorities = new ArrayList<>();
//		authorities.add(Authority.builder().userId(userEntity.getUserId()).roleId(1).build());
//		
//		userRepository.addAuthorities(authorities);
//	}
	
	
	
	
	// email 중복확인(중복시 메세지 전달)
	public void checkDuplicatedEmail(String email) {
		
		if(userRepository.findUserByEmail(email) != null) {
			throw new CustomException("Duplicated Email", 
					ErrorMap.builder()
					.put("email", "사용중인 이메일입니다.")
					.build());
		}
		
	}
}
