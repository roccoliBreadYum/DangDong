package com.ssafit.pjt.model.dto;

public class LoginUser {

	private String loginId;
	private String loginPw;
	
	LoginUser(){}

	public LoginUser(String loginId, String loginPw) {
		super();
		this.loginId = loginId;
		this.loginPw = loginPw;
	}

	public String getId() {
		return loginId;
	}

	public void setId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return loginPw;
	}

	public void setPassword(String loginPw) {
		this.loginPw = loginPw;
	}

	@Override
	public String toString() {
		return "LoginUser [loginId=" + loginId + ", loginPw=" + loginPw + "]";
	}
	
	
}
