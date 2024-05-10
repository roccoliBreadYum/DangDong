package com.ssafit.pjt.model.dto;

public class Store {
	private int storeId; // PK @
	private int excersiceId; // 카테고리 id @
	private String storeName; // 이름  @
	private String address; // 주소 @
	private String phoneNumber; // 연락처 @
	private String description; // 설명 @
	private int favoriteCount; // 찜 개수 @
	private String createdAt;  // 생성일자 @
	private String openingHours; // 운영시간 @
	private String logoImage;// 이미지 @
	private int teacherCount; // 강사진 수  -> 즐겨찾기에서 사용하기위해 작성했던 것 같은데 확인 필요 (DB에 없음)
	// 평점, 휴면계정, push알림동의, 휴무일
	
	public Store() {}

	public Store(int storeId, int excersiceId, String storeName, String address, String phoneNumber, String description,
			int favoriteCount, String createdAt, String openingHours, String logoImage) {
		this.storeId = storeId;
		this.excersiceId = excersiceId;
		this.storeName = storeName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.favoriteCount = favoriteCount;
		this.createdAt = createdAt;
		this.openingHours = openingHours;
		this.logoImage = logoImage;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getExcersiceId() {
		return excersiceId;
	}

	public void setExcersiceId(int excersiceId) {
		this.excersiceId = excersiceId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}
	
	
	public int getTeacherCount() {
		return teacherCount;
	}

	public void setTeacherCount(int teacherCount) {
		this.teacherCount = teacherCount;
	}

	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", excersiceId=" + excersiceId + ", storeName=" + storeName + ", address="
				+ address + ", phoneNumber=" + phoneNumber + ", description=" + description + ", favoriteCount="
				+ favoriteCount + ", createdAt=" + createdAt + ", openingHours=" + openingHours + ", logoImage="
				+ logoImage + "]";
	}

}
