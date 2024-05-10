package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.TicketDao;
import com.ssafit.pjt.model.dto.Ticket;

@Service
public class TicketServiceImpl implements TicketService {
	
	private TicketDao tDao;
	
	@Autowired
	public void setTicketDao(TicketDao tDao) {
		this.tDao = tDao;
	}
	
	@Override
	public int createTicket(Ticket ticket) {
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

}
