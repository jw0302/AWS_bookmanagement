package com.toyproject.bookmanagement.repository;

import org.apache.ibatis.annotations.Mapper;

import com.toyproject.bookmanagement.entity.Authority;
import com.toyproject.bookmanagement.entity.User;

@Mapper
public interface UserRepository {

//	public int addAuthorities(List<Authority> authorities);
	// User 이메일 조회
	public User findUserByEmail(String email);
	// 회원가입
	public int saveUser(User user);
	public int saveAuthority(Authority authority);
}
