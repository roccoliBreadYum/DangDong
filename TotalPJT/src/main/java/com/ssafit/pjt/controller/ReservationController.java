package com.ssafit.pjt.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Reservation;
import com.ssafit.pjt.model.service.ReservationService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/reservation")
@Tag(name="ReservationController", description = "현재 예약된 리스트 반환 예정")
public class ReservationController {

	private final ReservationService resService;
	
	public ReservationController (ReservationService resService) {
		this.resService = resService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategory(@PathVariable("id")int id, Date date){
		List<Reservation> list = resService.getByIdAndMonth(date, id);
		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	
	
}
