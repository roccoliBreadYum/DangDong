package com.ssafit.pjt.model.dto;

import java.sql.Timestamp;

public class Ticket {
	private int ticketId; //티겟 고유 Id
	private Timestamp regDate; //생성일 (정기권 사용 시 필요)
	private Timestamp expirationDate = null; //만료일 (정기권 사용 시 필요)
	private int totalQuantity = 0; //총 횟수 (다회권 사용 시 필요)
	private int remainingQuantity = 0; //사용가능한 남은횟수 (다회권 사용 시 필요)
	private int category; //다회권(0)인지 정기권(1)인지 분류
	private String userId;
	private int storeId;
	private String name; // 판매되는 티켓 이름
	private int buyTicketId; //-- 판매되는 티켓 고유번호
	
	public Ticket() {}
	

	public int getBuyTicketId() {
		return buyTicketId;
	}


public void setBuyTicketId(int buyTicketId) {
		this.buyTicketId = buyTicketId;
	}


	public Timestamp getExpirationDate() {
		return expirationDate;
	}



	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}



	public String getTicketName() {
		return name;
	}



	public void setTicketName(String name) {
		this.name = name;
	}


	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
		this.setRemainingQuantity(totalQuantity);
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Timestamp getExexpirationDate() {
		return expirationDate;
	}

	public void setExexpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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


	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", regDate=" + regDate + ", expirationDate=" + expirationDate
				+ ", totalQuantity=" + totalQuantity + ", remainingQuantity=" + remainingQuantity + ", category="
				+ category + ", userId=" + userId + ", storeId=" + storeId + ", name=" + name
				+ ", buyTicketId=" + buyTicketId + "]";
	}

	
}
