package com.ssafit.pjt.controller;

import java.util.List;

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
import com.ssafit.pjt.model.dto.SearchCondition;
import com.ssafit.pjt.model.dto.Store;
import com.ssafit.pjt.model.service.StoreService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/api-store")
@Tag(name="StoreRestController", description = "Store CRUD")
public class StoreController {
	
	private final StoreService storeService;
	
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@GetMapping("/category")
	public ResponseEntity<?> getCategory(){
		List<Category> list = storeService.categoryList();
		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/stores")
	public ResponseEntity<?> getStores(@ModelAttribute SearchCondition searchCondition){
		List<Store> list = storeService.storeList(searchCondition);
		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/stores/{id}")
	public ResponseEntity<?> searchStoreByCategory(@PathVariable("exercise_id") int id){
		List<Store> list = storeService.getStoreById(id);
		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/stores")
	public ResponseEntity<?> updateStore(@RequestBody Store store){
		int num = storeService.modifyStore(store);
		return new ResponseEntity<>(num, num != 1? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	
}