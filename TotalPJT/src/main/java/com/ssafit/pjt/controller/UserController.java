package com.ssafit.pjt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/api-user/user")
@Tag(name="UserRestController", description = "User CRUD")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class UserController {
	
	private UserService uService;
	
	@Autowired
	public void setUserService(UserService uService) {
		this.uService = uService;
	}
	
//	@GetMapping("/login") //로그인 유저 정보
//	@Operation(summary = "로그인", description = "로그인 요청을 시도한 아이디와 비밀번호를 통해 가능한지의 여부를 반환합니다.")
//	
//	public ResponseEntity<?> doLogin(@RequestBody User user, HttpSession session, Model model) { 
//		 User loginUser = uService.findUser(user.getId());
//		 
//		 if(loginUser==null) {
//			// 이 아이디로 가입된 사용자가 없을 때
//			 model.addAttribute("msg", "아이디(로그인 전용 아이디)를 잘못 입력했습니다.\r\n"+ "입력하신 내용을 다시 확인해주세요.");
//			 return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
//			 
//		 }else if(loginUser.getPassword().equals(user.getPassword())) {
//			 // 로그인 성공
//			 session.setAttribute("loginUser", loginUser);
//			 return new ResponseEntity(HttpStatus.ACCEPTED);
//		 }
//		 
//		 //비밀번호 입력 실패
//		 model.addAttribute("msg", "비밀번호를 잘못 입력했습니다.\r\n" + "입력하신 내용을 다시 확인해주세요.");
//		 return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
//	 }
	
	@GetMapping("/{id}")
	@Operation(summary = "회원아이디로 회원 조회")
	public ResponseEntity<?> getUser(@PathVariable("id") String id){
		User user = uService.findUser(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
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
	
	@PostMapping("/{storeId}/{id}")
	@Operation(summary = "특정 가게 좋아요 등록")
	public ResponseEntity<?> addFavorite(@PathVariable("storeId") int storeId, @PathVariable("id") String id){
		Map<String, Object> info = new HashMap();
		info.put("storeId", storeId);
		info.put("id", id);
		int result = uService.addFavorite(info);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{storeId}/{id}")
	@Operation(summary = "특정 가게 좋아요 삭제")
	public ResponseEntity<?> removeFavorite(@PathVariable("storeId") int storeId, @PathVariable("id") String id){
		Map<String, Object> info = new HashMap();
		info.put("storeId", storeId);
		info.put("id", id);
		int result = uService.removeFavorite(info);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
