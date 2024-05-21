package com.ssafit.pjt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafit.pjt.model.dto.Category;
import com.ssafit.pjt.model.dto.SellTicket;
import com.ssafit.pjt.model.dto.Store;
import com.ssafit.pjt.model.dto.User;
import com.ssafit.pjt.model.service.StorageService;
import com.ssafit.pjt.model.service.StoreService;
import com.ssafit.pjt.util.StoreSearchCondition;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/api-store")
@Tag(name="StoreRestController", description = "Store CRUD")
public class StoreController {
	
	private final StoreService storeService;
	
	private final StorageService storageService;
	
	public StoreController(StoreService storeService, StorageService storageService) {
		this.storeService = storeService;
		this.storageService = storageService;
	}
	
	@GetMapping("")
	@Operation(summary = "가게 목록", description = "필터 조건에 따른 전체 가게 목록 반환")
	public ResponseEntity<?> getStores(@ModelAttribute StoreSearchCondition searchCondition){
		List<Store> list = storeService.storeList(searchCondition);
		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{storeId}/{userId}")
	@Operation(summary = "가게 페이지", description = "리스트에서 선택한 가게의 상세페이지")
	public ResponseEntity<?> selectStoreDetail(@PathVariable("storeId") int storeId, @PathVariable("userId") String userId){
		Map<String, Object> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("userId", userId);
		Store store = storeService.getStoreDetail(map);
		return new ResponseEntity<>(store, store != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{storeId}/ticket")
	@Operation(summary = "티켓 판매", description = "해당 가게에서 판매중인 티켓 리스트")
	public ResponseEntity<?> getTicketList(@PathVariable("storeId") int storeId){
		List<SellTicket> ticket = storeService.getTicketList(storeId);
		return new ResponseEntity<>(ticket, ticket != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/category/{exerciseId}")
	@Operation(summary = "가게 페이지", description = "리스트에서 선택한 가게의 상세페이지")
	public ResponseEntity<?> selectStoreDetail(@PathVariable("exerciseId") int exerciseId){
		String name = storeService.getCategory(exerciseId);
		return new ResponseEntity<>(name, name != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/regist")
	@Operation(summary = "가게 페이지", description = "리스트에서 선택한 가게의 상세페이지")
	public ResponseEntity<?> registStore(@RequestPart("store") Store store,
            @RequestPart(value = "file", required = false) MultipartFile file){
		if(file != null) {
			String resultFile = storageService.uploadFile(file);
			store.setLogoImage(resultFile);			
		}
		int result = storeService.rigistStore(store);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/update")
	@Operation(summary = "(사업자) 가게 내용 수정", description = "가게 상세페이지 내 내용 수정")
	public ResponseEntity<?> modifyStore(@RequestPart("store") Store store,
            @RequestPart(value = "file", required = false) MultipartFile file){
		if(file != null) {
			String resultFile = storageService.uploadFile(file);
			store.setLogoImage(resultFile);			
		}
		int result = storeService.modifyStore(store);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{storeId}/image")
	@Operation(summary = "이미지 불러오기", description = "현재 선택된 가게의 이미지 최대 2개 불러오기")
	public ResponseEntity<?> getImg(@PathVariable("storeId") int storeId){
		List<String> list = storeService.getAllImg(storeId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
