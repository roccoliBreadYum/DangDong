package com.ssafit.pjt.model.service;

import com.ssafit.pjt.model.dto.User;

public interface UserService {

	User findUser(String id);

	int signUser(User user);

	int withdrawalUser(String id);

	int updateUser(User user);

	User loginUser(User user);
}
