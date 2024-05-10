package com.ssafit.pjt.model.dto;

import java.sql.Timestamp;
import java.util.Date;

public class Reservation {
	/*
	 * reservationId: 예약 ID(PK)
	 * userId: 유저 ID(FK)
	 * storeId: 가게 ID(FK)
	 * teacherId: 강사 ID(FK)
	 * ticketId : 티켓 ID(FK)
	 * date : 예약 날짜
	 */
	private int reservationId; 
	private String userId;
	private int storeId;
	private int teacherId;
	private int ticketId;
	private Timestamp date;
	
	public Reservation() {	}
	
	public Reservation(int reservationId, String userId, int storeId, int teacherId, int ticketId, Timestamp date) {
		this.reservationId = reservationId;
		this.userId = userId;
		this.storeId = storeId;
		this.teacherId = teacherId;
		this.ticketId = ticketId;
		this.date = date;
	}
	
	
	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStoreId() {
		return storeId;
	}
	
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	public int getTeacherId() {
		return teacherId;
	}
	
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", userId=" + userId + ", storeId=" + storeId
				+ ", teacherId=" + teacherId + ", ticketId=" + ticketId + ", date=" + date + "]";
	}
	
}
