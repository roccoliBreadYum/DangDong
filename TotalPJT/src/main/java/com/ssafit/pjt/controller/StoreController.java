package com.ssafit.pjt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Category;
import com.ssafit.pjt.model.dto.Store;
import com.ssafit.pjt.model.service.StoreService;
import com.ssafit.pjt.util.StoreSearchCondition;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/store")
@Tag(name="StoreRestController", description = "Store CRUD")
public class StoreController {
	
	private final StoreService storeService;
	
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@GetMapping("")
	public ResponseEntity<?> getStores(@ModelAttribute StoreSearchCondition searchCondition){
		List<Store> list = storeService.storeList(searchCondition);
		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{storeId}/{userId}")
	public ResponseEntity<?> selectStoreDetail(@PathVariable("storeId") int storeId, @PathVariable("userId") int userId){
		Map<String, Object> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("userId", userId);
		List<Store> list = storeService.getStoreDetail(map);
		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("")
	public ResponseEntity<?> updateStore(@RequestBody Store store){
		int num = storeService.modifyStore(store);
		return new ResponseEntity<>(num, num != 1? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	
}
