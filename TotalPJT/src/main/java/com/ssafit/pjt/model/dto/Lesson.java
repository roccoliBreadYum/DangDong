package com.ssafit.pjt.model.dto;

import java.sql.Timestamp;
import java.util.Date;

public class Lesson {
	private int lessonId; // 수업 PK
	private int teacherId; // 유저아이디 (식별)
	private int storeId; // 가게아이디 (식별)
	private Timestamp date; // 해당 수업의 날짜와 시간
	private int userCnt; // 해당 수업을 신청한 유저 수
	private int capacity; // 해당 수업 총 수용량
		
	public Lesson() {}

	public Lesson(int lessonId, int userId, int storeId, Timestamp date, int userCnt, int capacity) {
		this.lessonId = lessonId;
		this.teacherId = userId;
		this.storeId = storeId;
		this.date = date;
		this.userCnt = userCnt;
		this.capacity = capacity;
	}


	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public int getUserId() {
		return teacherId;
	}

	public void setUserId(int userId) {
		this.teacherId = userId;
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

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
	public int getUserCnt() {
		return userCnt;
	}

	public void setUserCnt(int userCnt) {
		this.userCnt = userCnt;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Lesson [lessonId=" + lessonId + ", teacherId=" + teacherId + ", storeId=" + storeId + ", date=" + date
				+ ", userCnt=" + userCnt + ", capacity=" + capacity + "]";
	}

}
