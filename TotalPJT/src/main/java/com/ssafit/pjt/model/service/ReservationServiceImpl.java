package com.ssafit.pjt.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.CalenderDao;
import com.ssafit.pjt.model.dto.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	private final CalenderDao calDao;
	
	public ReservationServiceImpl(CalenderDao calDao) {
		this.calDao = calDao;
	}

	@Override
	public List<Reservation> getByIdAndMonth(Date date, int id) {
		return calDao.searchByIdAndMonth(date, id);
	}

}
