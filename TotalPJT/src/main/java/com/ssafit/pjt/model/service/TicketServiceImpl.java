package com.ssafit.pjt.model.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.TicketDao;
import com.ssafit.pjt.model.dto.SellTicket;
import com.ssafit.pjt.model.dto.Ticket;
import com.ssafit.pjt.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TicketServiceImpl implements TicketService {

	private TicketDao tDao;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	public void setTicketDao(TicketDao tDao) {
		this.tDao = tDao;
	}

	@Override
	public int createTicket(SellTicket sellTicket, HttpServletRequest request) {
		System.out.println("sellTicket : " + sellTicket);
		Ticket ticket = new Ticket();
		ticket.setCategory(sellTicket.getCategory());
		if (ticket.getCategory() == 0) {
			// 다회권
			ticket.setTotalQuantity(sellTicket.getQuantity());
			ticket.setRemainingQuantity(sellTicket.getQuantity());
		} else {
			// 정기권
			// 오늘 날짜 가져오기
	        LocalDate today = LocalDate.now();

	        // 특정 일수를 더하기 
	        int daysToAdd = sellTicket.getExpireDate();
	        LocalDate futureDate = today.plusDays(daysToAdd);

	        // LocalDate를 LocalDateTime으로 변환
	        LocalDateTime futureDateTime = futureDate.atStartOfDay();
	        
	        // LocalDateTime을 Timestamp로 변환
	        Timestamp timestamp = Timestamp.valueOf(futureDateTime);
			ticket.setExpirationDate(timestamp);
		}
		ticket.setStoreId(sellTicket.getStoreId());
		ticket.setBuyTicketId(sellTicket.getTicketId());
		ticket.setName(sellTicket.getName());

		// 요청에서 acessToken가져오기
		String accessToken = request.getHeader("access-token");

		// 토큰의 유저아이디 가져오기
		String userId = jwtUtil.getId(accessToken);
		ticket.setUserId(userId);
		return tDao.insertTicket(ticket);
	}

	@Override
	public List<Ticket> getTicketList(String userId) {
		return tDao.selectTicketList(userId);
	}

	@Override
	public int removeTicket(int ticketId) {
		return tDao.deleteTicket(ticketId);
	}

	@Override
	public List<Ticket> selectTicketByStoreId(Map<String, Object> map) {
		return tDao.selectTicketByStoreId(map);
	}

}
