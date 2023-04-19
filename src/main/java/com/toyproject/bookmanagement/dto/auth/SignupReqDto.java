package com.toyproject.bookmanagement.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.toyproject.bookmanagement.entity.User;

import lombok.Data;

@Data
public class SignupReqDto {

	@Email			// 이메일 형식으로 작성하지 않으면 안되도록 하는 코드					
	private String email;
	
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$",
			message = "비밀번호는 영문자, 숫자, 특수문자를 포함하여 8 ~ 16자로 작성")			// 정규식으로 비밀번호 설정하는 코드
	private String password;
	
	@Pattern(regexp = "^[가-힣]{2,7}$",
			message = "한글이름만 작성 가능합니다.")			// name 정규식 설정 한글, 2자 이상 7자 이하
	private String name;
	
	
	public User toEntity() {
		return User.builder()
				.email(email)
				.password(new BCryptPasswordEncoder().encode(password))
				.name(name)
				.build();
	}
	
}
