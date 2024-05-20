package com.ssafit.pjt.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafit.pjt.model.dto.User;
import com.ssafit.pjt.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController // rest API사용, JSON형태로 주고받기 위함
@RequestMapping("/api-user/user")
@Tag(name = "UserRestController", description = "User CRUD")
public class UserController {

	private UserService uService;

	@Autowired
	public void setUserService(UserService uService) {
		this.uService = uService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response)
			throws UnsupportedEncodingException {

		Map<String, Object> result = uService.loginUser(user, response);
		if (result.get("access-token") == null) {
			// 토큰 발급 실패
			return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshAccessToken(HttpServletRequest request) {
		Map<String, Object> result = uService.refreshAccessToken(request);
		if (result.get("access-token") == null) {
			// 토큰 발급 실패
			return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "회원아이디로 회원 조회")
	public ResponseEntity<?> getUser(@PathVariable("id") String id) {
		User user = uService.findUser(id);
		return new ResponseEntity<>(user, user != null ? HttpStatus.OK : HttpStatus.OK);
	}

	@PostMapping("")
	@Operation(summary = "회원가입")
	/*
	 * JSON으로 생각
	 * 
	 */
	public ResponseEntity<?> signin(@RequestPart("user") User user,
            @RequestPart(value = "file", required = false) MultipartFile file) {
		int result = uService.signUser(user, file);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "회원탈퇴")
	public ResponseEntity<?> withdrawal(@PathVariable("id") String id) {
		int result = uService.withdrawalUser(id);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@PutMapping("")
	@Operation(summary = "회원정보수정")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
//		user.setId(id);
		int result = uService.updateUser(user);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
