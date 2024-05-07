package com.ssafit.pjt.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Category;
import com.ssafit.pjt.model.service.ReservationService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/reservation")
@Tag(name="ReservationController", description = "reservation")
public class ReservationController {

	private final ReservationService resService;
	
	public ReservationController(ReservationService resService) {
		this.resService = resService;
	}
	
//	@GetMapping("/category/{storeId}")
//	public ResponseEntity<?> getCategory(){
//		List<Category> list = resService.findByDate(@PathVariable("storeId")int storeId, Date date))
//		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
	
}
