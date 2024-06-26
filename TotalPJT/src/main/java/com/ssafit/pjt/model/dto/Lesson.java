package com.ssafit.pjt.model.dto;

import java.sql.Timestamp;
import java.util.Date;

public class Lesson {
	private int lessonId; // 수업 PK
	private int teacherId; // 강사아이디 (식별)
	private int storeId; // 가게아이디 (식별)
	private Timestamp date; // 해당 수업의 날짜와 시간
	private int userCnt; // 해당 수업을 신청한 유저 수
	private int capacity; // 해당 수업 총 수용량
	private String teacherName;
	private String storeName;
	
	public Lesson() {}

	public Lesson(int lessonId, int teacherId, int storeId, Timestamp date, int userCnt, int capacity,
			String teacherName, String storeName) {
		super();
		this.lessonId = lessonId;
		this.teacherId = teacherId;
		this.storeId = storeId;
		this.date = date;
		this.userCnt = userCnt;
		this.capacity = capacity;
		this.teacherName = teacherName;
		this.storeName = storeName;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Timestamp getDate() {
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Override
	public String toString() {
		return "Lesson [lessonId=" + lessonId + ", teacherId=" + teacherId + ", storeId=" + storeId + ", date=" + date
				+ ", userCnt=" + userCnt + ", capacity=" + capacity + ", teacherName=" + teacherName + ", storeName="
				+ storeName + "]";
	}
	
	
}
