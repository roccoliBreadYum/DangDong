package com.ssafit.pjt.model.service;

import java.util.List;

import com.ssafit.pjt.model.dto.Ticket;

public interface TicketService {
	
	// 이용권 생성
	int createTicket(Ticket ticket);
	
	// 사용자의 전체 이용권 리스트 반환
	List<Ticket> getTicketList(String userId);
	
	// 이용권 삭제
	int removeTicket(int ticketId);

}
