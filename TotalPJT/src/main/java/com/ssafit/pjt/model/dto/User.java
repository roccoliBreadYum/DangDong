package com.ssafit.pjt.model.dto;

import java.sql.Timestamp;

public class User {
	private String id;
	private String password;
	private String name;
	private String email;
	private String address;
	private int membershipRate; // 회원 등급
	private int gender;
	private String nickName;
	private Timestamp birth;
	private Timestamp regDate; // 생성일
	private Timestamp updateDate; // 수정일
	private int coin; // 보유 코인
	private String category; //이게 뭐야?
	private String comment;
	private String img; // 이미지
	private String orgImg; // 최초 업로드시 파일 이름
	
	
	public User() {
		
	}
	
	
	
	public User(String id, String password, String name, String email, String address, int gender, String nickName,
			Timestamp birth, String category, String comment, String img, String orgImg) {
		//default인 등급, 생성일, 수정일, 보유코인 필드 제외한 생성자. 
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.nickName = nickName;
		this.birth = birth;
		this.category = category;
		this.comment = comment;
		this.img = img;
		this.orgImg = orgImg;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMembershipRate() {
		return membershipRate;
	}
	public void setMembershipRate(int membershipRate) {
		this.membershipRate = membershipRate;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Timestamp getBirth() {
		return birth;
	}
	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getOrgImg() {
		return orgImg;
	}
	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}
	
	
	
}
