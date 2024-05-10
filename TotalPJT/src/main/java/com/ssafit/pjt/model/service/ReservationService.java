package com.ssafit.pjt.model.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ssafit.pjt.model.dto.Reservation;

public interface ReservationService {

	public int addReservation (Reservation reservation);
	
	public List<Reservation> getReservationByDate(Map<String, Object> map);

	public int removeReservation(Reservation reservation);
	
	
}
