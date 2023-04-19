package com.toyproject.bookmanagement.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.toyproject.bookmanagement.entity.Authority;
import com.toyproject.bookmanagement.entity.User;

@Mapper
public interface UserRepository {

	// 회원가입
	public int signupUser(User user);
	public int addAuthorities(List<Authority> authorities);
	// User 이메일 조회
	public User findUserByEmail(String email);
}
