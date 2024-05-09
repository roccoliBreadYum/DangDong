package com.ssafit.pjt.model.dto;

public class Favorite {
	public String userId;
	public int storeId;
	
	public Favorite(String userId, int storeId) {
		this.userId = userId;
		this.storeId = storeId;
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
		return "Favorite [userId=" + userId + ", storeId=" + storeId + "]";
	}
	
	
	
}
