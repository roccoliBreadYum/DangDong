package com.ssafit.pjt.model.service;

import java.util.List;
import java.util.Map;

import com.ssafit.pjt.model.dto.SellTicket;
import com.ssafit.pjt.model.dto.Ticket;

import jakarta.servlet.http.HttpServletRequest;

public interface TicketService {
	
	// 이용권 생성
	int createTicket(SellTicket sellTicket, HttpServletRequest request);
	
	// 사용자의 전체 이용권 리스트 반환
	List<Ticket> getTicketList(String userId);
	
	// 이용권 삭제
	int removeTicket(int ticketId);

	// 해당 가게 관련 티켓 반환
	List<Ticket> selectTicketByStoreId(Map<String, Object> map);
}
