package com.ssafit.pjt.model.service;

import java.util.Map;

import com.ssafit.pjt.model.dto.User;

public interface UserService {

	User findUser(String id);

	int signUser(User user);

	int withdrawalUser(String id);

	int updateUser(User user);
	
	int addFavorite(Map<String, Object> info);

	int removeFavorite(Map<String, Object> info);

}
