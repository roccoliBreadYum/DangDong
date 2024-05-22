package com.ssafit.pjt.model.dto;

public class Store {
	private int storeId; // PK 
	private int exerciseId; // 카테고리 id 
	private String storeName; // 이름  
	private String address; // 주소 
	private String phoneNumber; // 연락처 
	private String description; // 설명
	private int favoriteCount; // 찜 개수 
	private String createdAt;  // 생성일자 
	private String openingHours; // 운영시간 
	private String logoImage;// 이미지 
	private int teacherCount; // 강사진 수  -> 즐겨찾기에서 사용하기위해 작성했던 것 같은데 확인 필요 (DB에 없음)
	private int isFavorite; // 1: 해당유저의 즐겨찾기에 등록되어있음, 0: 미등록
	private String ownerId;
	private int coin = 0;
	// 평점, 휴면계정, push알림동의, 휴무일
	
	public Store() {}
	
	public Store(int storeId, int exerciseId, String storeName, String address, String phoneNumber, String description,
			int coin, int favoriteCount, String createdAt, String openingHours, String logoImage, int teacherCount, String ownerId) {
		this.storeId = storeId;
		this.exerciseId = exerciseId;
		this.storeName = storeName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.favoriteCount = favoriteCount;
		this.createdAt = createdAt;
		this.openingHours = openingHours;
		this.logoImage = logoImage;
		this.teacherCount = teacherCount;
		this.ownerId = ownerId;
		this.coin = coin;
	}
	

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
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

	public int getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(int isFavorite) {
		this.isFavorite = isFavorite;
	}
	

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", exerciseId=" + exerciseId + ", storeName=" + storeName + ", address="
				+ address + ", phoneNumber=" + phoneNumber + ", description=" + description + ", favoriteCount="
				+ favoriteCount + ", createdAt=" + createdAt + ", openingHours=" + openingHours + ", logoImage="
				+ logoImage + ", teacherCount=" + teacherCount + ", isFavorite=" + isFavorite + ", ownerId=" + ownerId
				+ ", coin=" + coin + "]";
	}

	
}
