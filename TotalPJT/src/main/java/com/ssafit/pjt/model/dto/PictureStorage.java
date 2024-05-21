package com.ssafit.pjt.model.dto;

import java.sql.Date;

public class PictureStorage {
	private String pictureId;
	private int storeId;
	private Date createDate;
	
	public PictureStorage() {
		super();
	}


	public PictureStorage(String pictureId, int storeId, Date createDate) {
		super();
		this.pictureId = pictureId;
		this.storeId = storeId;
		this.createDate = createDate;
	}


	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@Override
	public String toString() {
		return "PictureStorage [pictureId=" + pictureId + ", storeId=" + storeId + "]";
	}

	
	
}
