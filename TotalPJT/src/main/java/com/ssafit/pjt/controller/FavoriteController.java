package com.ssafit.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Favorite;
import com.ssafit.pjt.model.dto.Store;
import com.ssafit.pjt.model.service.FavoriteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/api-favorite/favorite")
@Tag(name="FavoriteRestController", description = "User CRUD")
public class FavoriteController {
	
	private FavoriteService fService;
	
	@Autowired
	public void setFavoriteService(FavoriteService fService) {
		this.fService = fService;
	}
	
	@PostMapping("")
	@Operation(summary = "즐겨찾기 추가", description = "사용자 아이디와 가게 아이디를 통해서 즐겨찾기 항목 추가")
	public ResponseEntity<?> createFavorite(@RequestBody Favorite favorite){
		System.out.println("favorite get userId: " + favorite.getUserId());
		int res = fService.createFavorite(favorite);
		return new ResponseEntity<>(res, res==1? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{userId}/{storeId}")
	@Operation(summary = "즐겨찾기 삭제", description = "사용자 아이디와 가게 아이디를 통해서 즐겨찾기 항목 삭제")
	public ResponseEntity<?> removeFavorite(@PathVariable("userId") String userId, @PathVariable("storeId") int storeId){
		Favorite favorite = new Favorite(userId, storeId);
		int res = fService.removeFavorite(favorite);
		return new ResponseEntity<>(res, res==1? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{userId}")
	@Operation(summary = "사용자 별 즐겨찾기 항목 조회")
	public ResponseEntity<?> getFavoriteList(@PathVariable("userId") String userId){
		List<Store> list= fService.readAll(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
