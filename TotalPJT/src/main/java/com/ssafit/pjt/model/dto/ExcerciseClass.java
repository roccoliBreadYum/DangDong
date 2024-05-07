package com.ssafit.pjt.model.dto;

import java.util.Date;
import java.sql.Timestamp;

public class ExcerciseClass {
	private int reservationId;
	private int teacherId;
	private Date date;
	private Timestamp startTime;
	private int nowPerson;
	private int TotalPerson;

	
	public ExcerciseClass() {}


	public ExcerciseClass(int reservationId, int teacherId, Date date, Timestamp startTime, int nowPerson,
			int totalPerson) {
		this.reservationId = reservationId;
		this.teacherId = teacherId;
		this.date = date;
		this.startTime = startTime;
		this.nowPerson = nowPerson;
		TotalPerson = totalPerson;
	}


	public int getReservationId() {
		return reservationId;
	}


	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
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


	public void setDate(Date date) {
		this.date = date;
	}


	public Timestamp getStartTime() {
		return startTime;
	}


	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}


	public int getNowPerson() {
		return nowPerson;
	}


	public void setNowPerson(int nowPerson) {
		this.nowPerson = nowPerson;
	}


	public int getTotalPerson() {
		return TotalPerson;
	}


	public void setTotalPerson(int totalPerson) {
		TotalPerson = totalPerson;
	}


	@Override
	public String toString() {
		return "ExcerciseClass [reservationId=" + reservationId + ", teacherId=" + teacherId + ", date=" + date
				+ ", startTime=" + startTime + ", nowPerson=" + nowPerson + ", TotalPerson=" + TotalPerson + "]";
	}
	
	
	
}
