package com.ssafit.pjt.model.dto;

import java.sql.Timestamp;
import java.util.Date;

public class Reservation {
	/*
	 * div: 코인결제인지 이용권 결제인지 확인. 1: 이용권, 2: 코인
	 * coin: 코인 결제시 사용한 코인 수
	 * reservationId: 예약 ID(PK)
	 * userId: 유저 ID(FK)
	 * storeId: 가게 ID(FK)
	 * lessonId: 강의 ID(FK)
	 * ticketId : 티켓 ID(FK)
	 * date : 예약 날짜
	 */
	private int payment;
	private int coin;
	private int reservationId; 
	private String userId;
	private int storeId;
	private int lessonId;
	private int ticketId;
	private Timestamp date;
	private String storeName;
	private String exerciseCategory;
	private int exerciseCategoryNum;
	private String time;
	private String ticketCategory;
	
	
	
	public Reservation() {
		super();
	}

	public Reservation(int payment, int coin, int reservationId, String userId, int storeId, int lessonId, int ticketId, Timestamp date,
			String storeName, String exerciseCategory, int exerciseCategoryNum, String time, String ticketCategory) {
		super();
		this.payment = payment;
		this.coin = coin;
		this.reservationId = reservationId;
		this.userId = userId;
		this.storeId = storeId;
		this.lessonId = lessonId;
		this.ticketId = ticketId;
		this.date = date;
		this.storeName = storeName;
		this.exerciseCategory = exerciseCategory;
		this.exerciseCategoryNum = exerciseCategoryNum;
		this.time = time;
		this.ticketCategory = ticketCategory;
	}

	

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
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

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getStoreName() {
		return storeName;
	}
	
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getExerciseCategory() {
		return exerciseCategory;
	}

	public void setExerciseCategory(String exerciseCategory) {
		this.exerciseCategory = exerciseCategory;
	}
	

	public int getExerciseCategoryNum() {
		return exerciseCategoryNum;
	}

	public void setExerciseCategoryNum(int exerciseCategoryNum) {
		this.exerciseCategoryNum = exerciseCategoryNum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public String getTicketCategory() {
		return ticketCategory;
	}


	public void setTicketCategory(String ticketCategory) {
		this.ticketCategory = ticketCategory;
	}

	@Override
	public String toString() {
		return "Reservation [payment=" + payment + ", coin=" + coin + ", reservationId=" + reservationId + ", userId=" + userId
				+ ", storeId=" + storeId + ", lessonId=" + lessonId + ", ticketId=" + ticketId + ", date=" + date
				+ ", storeName=" + storeName + ", exerciseCategory=" + exerciseCategory + ", exerciseCategoryNum="
				+ exerciseCategoryNum + ", time=" + time + ", ticketCategory=" + ticketCategory + "]";
	}





	
}
