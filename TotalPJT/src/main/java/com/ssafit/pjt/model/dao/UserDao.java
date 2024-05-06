package com.ssafit.pjt.model.dao;

import com.ssafit.pjt.model.dto.User;

public interface UserDao {
	//회원 가입 기능
	int insert(User user);
	
	//한명의 유저를 아이디로 검색하기
	User selectOne(String id);
	
	//유저 수정하기
	int update(User user);
	
	//유저 삭제하기
	int delete(String id);
	
}
