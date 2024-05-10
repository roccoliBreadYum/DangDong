package com.ssafit.pjt.model.dao;

import java.util.List;

import com.ssafit.pjt.model.dto.Ticket;

public interface TicketDao {
	
	Ticket selectTicketById(int ticketId);
	
	int insertTicket(Ticket ticket);

	List<Ticket> selectTicketList(String userId);

	int deleteTicket(int ticketId);
	
	int increaseTicket(int ticketId);
	
	int decreaseTicket(int ticketId);
	
}
