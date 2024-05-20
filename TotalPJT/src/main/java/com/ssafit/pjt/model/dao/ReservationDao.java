package com.ssafit.pjt.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafit.pjt.model.dto.Reservation;

public interface ReservationDao {
	
	// 결제 완료 후 reservation DB에 예약 내용 추가
	public int insertReservation(Reservation reservation);
	
	// 특정 인원의 전쳬 예약 리스트
	public List<Reservation> selectAllReservationById (String userId);
	
	// 특정 인원 오늘 이후의 전쳬 예약 리스트
	public List<Reservation> selectReservationById (String userId);
	
	// 해당 날짜에 따른 예약 리스트 반환
	public List<Reservation> selectReservationByDate(Map<String, Object> map);
	
	// 예약 취소
	public int deleteReservation(int reservationId);

}
