package com.ssafit.pjt.model.dto;

import java.util.Date;

public class Reservation {
	private int reservationId;
	private int userId;
	private int storeId;
	private Date date;
	public Reservation() {
	}
	
	public Reservation(int reservationId, int userId, int storeId, Date date) {
		this.reservationId = reservationId;
		this.userId = userId;
		this.storeId = storeId;
		this.date = date;
	}
	
	public int getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getStoreId() {
		return storeId;
	}
	
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", userId=" + userId + ", storeId=" + storeId + ", date="
				+ date + "]";
	}
	
	

}
