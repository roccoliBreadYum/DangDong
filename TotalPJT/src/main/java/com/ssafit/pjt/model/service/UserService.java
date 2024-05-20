package com.ssafit.pjt.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafit.pjt.model.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

	User findUser(String id);

//	int signUser(User user);

	int signUser(User user, MultipartFile file);

	int withdrawalUser(String id);

	int updateUser(User user);

	Map<String, Object> loginUser(User user, HttpServletResponse response);

	Map<String, Object> refreshAccessToken(HttpServletRequest request);
}
