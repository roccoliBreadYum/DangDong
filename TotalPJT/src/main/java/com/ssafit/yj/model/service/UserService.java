package com.ssafit.yj.model.service;

import com.ssafit.yj.model.dto.User;

public interface UserService {

	User findUser(String id);

	int signUser(User user);

	int withdrawalUser(String id);

	int updateUser(User user);
}
