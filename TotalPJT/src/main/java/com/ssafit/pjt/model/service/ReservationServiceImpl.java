package com.ssafit.pjt.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.pjt.model.dao.ReservationDao;
import com.ssafit.pjt.model.dto.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationDao resDao;
	//private final TicketDao tDao;

	
	public ReservationServiceImpl (ReservationDao resDao) {
		this.resDao = resDao;
		//this.tDao = tDao;
	}

	@Override
	@Transactional
	public int addReservation(Reservation reservation) {
		//tDao.decreaseTicket(reservation.getTicketId());
		return resDao.insertReservation(reservation);
	}

	@Override
	public List<Reservation> getReservationByDate(Map<String, Object> map) {
		return resDao.selectReservationByDate(map);
	}

	
	@Override
	@Transactional
	public int removeReservation(Reservation reservation) {
		//tDao.increaseTicket(reservation.getTicketId());
		return resDao.deleteReservation(reservation.getReservationId());
	}
	
}
