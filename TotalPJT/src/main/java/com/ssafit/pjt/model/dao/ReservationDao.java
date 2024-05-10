package com.ssafit.pjt.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafit.pjt.model.dto.Reservation;

public interface ReservationDao {
	
	// 결제 완료 후 reservation DB에 예약 내용 추가
	public int insertReservation(Reservation reservation);
	
	// 해당 날짜에 따른 예약 리스트 반환
	public List<Reservation> selectReservationByDate(Map<String, Object> map);
	
	// 예약 취소
	public int deleteReservation(int reservationId);

}
