package com.ssafit.pjt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	
	
	
	
	
}
