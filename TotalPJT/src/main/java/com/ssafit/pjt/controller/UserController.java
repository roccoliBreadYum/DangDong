package com.ssafit.pjt.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.User;
import com.ssafit.pjt.model.service.UserService;
import com.ssafit.pjt.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/api-user/user")
@Tag(name="UserRestController", description = "User CRUD")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class UserController {
	
	private UserService uService;
	private JwtUtil jwtUtil;
	
	@Value("${jwt.refreshtoken.expiretime}")
	private int refreshTokenExpireTime;
	
	@Autowired
	public void setUserService(UserService uService, JwtUtil jwtUtil) {
		this.uService = uService;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User userDto, HttpServletResponse response) throws UnsupportedEncodingException{
		
		Map<String, Object> result = new HashMap<>();
		
		// DB에서 유저 가져오기
		User dbUser = uService.loginUser(user);
		
		// 일치하는 유저가 없다면 UNAUTHORIZED 반환.
		if(dbUser == null) {
			result.put("message", "일치하는 유저가 없습니다.");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.UNAUTHORIZED);
		}
		
		// 있으면 Token 발급
		// AccessToken, RefreshToken 두 개를 발급해준다.
		String accessToken = jwtUtil.createAccessToken(dbUser.getId());
		String refreshToken = jwtUtil.createRefreshToken(dbUser.getId());
		
		// refreshtoken은 처음 발급할 때 -> DB에 저장.
		// INSERT INTO `refresh-token` (userId, refreshToken)  VALUE (#{userId}, #{refreshToken}) 
		
		
		// Front End와 합의사항
		//1.) accessToken, refreshToken => 본문에 둘다 보내도 되고,
		//   refreshToken: localStorage에 저장, accessToken: sessionStorage 또는 pinia store에만.
		
		//2.) accessToken만 응답 본문에 넣어서 보내고, refreshToken은 쿠키에 넣어서 보냄. => 
		//  refreshToken은 브라우저에 자동 저장
		//  accessToken만 sessionStorage 또는 pinia store에 저장.
		
		Cookie cookie = new Cookie("refreshToken", refreshToken);
		cookie.setMaxAge(refreshTokenExpireTime);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		result.put("accessToken", accessToken);
		result.put("name", dbUser.getName());
		
		
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "회원아이디로 회원 조회")
	public ResponseEntity<?> getUser(@PathVariable("id") String id){
		User user = uService.findUser(id);
		return new ResponseEntity<>(user, user != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("")
	@Operation(summary = "회원가입")
	/*
	 * JSON으로 생각
	 * 
	 * */
	public ResponseEntity<?> signin(@RequestBody User user){
		int result = uService.signUser(user);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "회원탈퇴")
	public ResponseEntity<?> withdrawal(@PathVariable("id") String id){
		int result = uService.withdrawalUser(id);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "회원정보수정")
	public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody User user){
		user.setId(id);
		int result = uService.updateUser(user);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

}
