package com.ssafit.yj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssafit.yj.model.dto.User;
import com.ssafit.yj.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/api-user/user")
@Tag(name="UserRestController", description = "사용자 CRUD")

public class UserController {
	
	private UserService uService;
	
	@Autowired
	public void setUserService(UserService uService) {
		this.uService = uService;
	}
	
	@GetMapping("/login") //로그인 유저 정보
	@Operation(summary = "로그인", description = "로그인 요청을 시도한 아이디와 비밀번호를 통해 가능한지의 여부를 반환합니다.")
	/*
	 * User객체에 입력된 정보들로만 이루어지는 생성자 필요한가??
	 * 일단은 JSON으로 넘어온다고 생각했는데 맞나? form??? modleattribute..?
	 * GET요청이 맞나?
	 * 
	 * */
	public ResponseEntity<?> doLogin(@RequestBody User user, HttpSession session, Model model) { 
		 User loginUser = uService.findUser(user.getId());
		 
		 if(loginUser==null) {
			// 이 아이디로 가입된 사용자가 없을 때
			 model.addAttribute("msg", "아이디(로그인 전용 아이디)를 잘못 입력했습니다.\r\n"+ "입력하신 내용을 다시 확인해주세요.");
			 return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
			 
		 }else if(loginUser.getPassword().equals(user.getPassword())) {
			 // 로그인 성공
			 session.setAttribute("loginUser", loginUser);
			 return new ResponseEntity(HttpStatus.ACCEPTED);
		 }
		 
		 //비밀번호 입력 실패
		 model.addAttribute("msg", "비밀번호를 잘못 입력했습니다.\r\n" + "입력하신 내용을 다시 확인해주세요.");
		 return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
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
	@Operation(summary = "회원탈퇴")
	public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody User user){
		user.setId(id);
		int result = uService.updateUser(user);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

}