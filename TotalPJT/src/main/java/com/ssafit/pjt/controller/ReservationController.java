package com.ssafit.pjt.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ssafit.pjt.model.dto.Reservation;
import com.ssafit.pjt.model.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/api-reservation")
@Tag(name="Reservationcontroller", description = "예약리스트 DB와 소통")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class ReservationController {
	
	private final ReservationService resService;
	
	public ReservationController(ReservationService resService) {
		this.resService = resService;
	}
	
	@GetMapping("/{date}/{userId}")
	@Operation(summary = "예약리스트 반환", description = "현재 접속한 아이디 및 날짜 기준 전체 예약목록 반환")
	public ResponseEntity<?> getReservationByDate (@PathVariable("date") Timestamp date, @PathVariable("userId") String userId){
		Map<String, Object> map = new HashMap();
		map.put("date", date);
		map.put("userId", userId);
		List<Reservation> list = resService.getReservationByDate(map);
		return new ResponseEntity<>(list, list != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("")
	@Operation(summary = "예약내역 추가", description = "결제 완료 후 reservation DB에 예약 내용 추가")
	public ResponseEntity<?> addReservation (@RequestBody Reservation reservation){
		int num = resService.addReservation(reservation);
		return new ResponseEntity<>(num, num == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{reservationId}")
	@Operation(summary = "예약 취소", description = "예약 취소 및 이용권 복구")
	public ResponseEntity<?> removeReservation (@PathVariable int reservationId){
		int num = resService.removeReservation(reservationId);
		return new ResponseEntity<>(num, num == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

}
