package com.ssafit.pjt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ssafit.pjt.model.dto.SellTicket;
import com.ssafit.pjt.model.dto.Ticket;
import com.ssafit.pjt.model.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api-ticket/ticket")
@Tag(name="TicketRestController", description = "Ticket CRUD")
public class TicketController {
	
	private TicketService tService;
	
	@Autowired
	public void setTicketService(TicketService tService) {
		this.tService = tService; 
	}
	
	@GetMapping("/{userId}")
	@Operation(summary = "사용자가 보유하고있는 전체 이용권 리스트 반환")
	public ResponseEntity<?> getTicketList(@PathVariable("userId") String userId){
		List<Ticket> ticketList = tService.getTicketList(userId);
		return new ResponseEntity<>(ticketList, HttpStatus.OK);
	}
	
	@PostMapping("")
	@Operation(summary = "이용권 생성")
	public ResponseEntity<?> createTicket(@RequestBody SellTicket sellTicket, HttpServletRequest request){
		int res = tService.createTicket(sellTicket, request);
		return new ResponseEntity<>(res, res==1? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{ticketId}")
	@Operation(summary = "이용권 삭제")
	public ResponseEntity<?> deleteTicket(@PathVariable("ticketId") int ticketId){
		int res = tService.removeTicket(ticketId);
		return new ResponseEntity<>(res, res==1? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{storeId}/{userId}")
	@Operation(summary = "사용자가 해당 업체에서 사용할 수 있는 이용권 리스트")
	public ResponseEntity<?> getTicketByStoreId(@PathVariable("storeId") int storeId, @PathVariable("userId") String userId){
		Map<String, Object> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("userId", userId);
		List<Ticket> list = tService.selectTicketByStoreId(map);
		if(list != null) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		return null;
	}
}
