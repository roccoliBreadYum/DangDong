package com.ssafit.pjt.model.service;

import java.util.Date;
import java.util.List;

import com.ssafit.pjt.model.dto.Reservation;

public interface ReservationService {
	
	/** 날짜를 기준으로 해당 인원의 년/월 기준 예약현황 및 예약이력 리스트로 반환 */
	public List<Reservation> getByIdAndMonth(Date date, int id);

}
