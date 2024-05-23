package com.ssafit.pjt.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.pjt.model.dao.LessonDao;
import com.ssafit.pjt.model.dao.ReservationDao;
import com.ssafit.pjt.model.dao.TicketDao;
import com.ssafit.pjt.model.dto.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationDao resDao;
	private final TicketDao tDao;
	private final LessonDao lDao;

	
	public ReservationServiceImpl (ReservationDao resDao, TicketDao tDao, LessonDao lDao) {
		this.resDao = resDao;
		this.tDao = tDao;
		this.lDao = lDao;
	}

	@Override
	@Transactional
	public int addReservation(Reservation reservation) {
		if(reservation.getPayment() == 1) {			
			tDao.decreaseTicket(reservation.getTicketId());
		} else if ( reservation.getPayment() == 2) {
			Map<String, Object> map = new HashMap();
			map.put("userId", reservation.getUserId());
			map.put("coin", reservation.getCoin());
			resDao.decreaseCoin(map);
		}
		lDao.decreaseLesson(reservation.getLessonId());
		return resDao.insertReservation(reservation);
	}
	
	@Override
	public List<Reservation> getAllReservationById(String userId) {
		return resDao.selectAllReservationById(userId);
	}
	
	
	@Override
	public List<Reservation> getReservationById(String UserId) {
		return resDao.selectReservationById(UserId);
	}

	@Override
	public List<Reservation> getReservationByDate(Map<String, Object> map) {
		return resDao.selectReservationByDate(map);
	}

	
	@Override
	@Transactional
	public int removeReservation(int reservationId) {
		tDao.increaseTicket(reservationId);
		return resDao.deleteReservation(reservationId);
	}


	
}
