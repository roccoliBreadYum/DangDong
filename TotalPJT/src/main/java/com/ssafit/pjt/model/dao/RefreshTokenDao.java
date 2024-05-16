package com.ssafit.pjt.model.dao;

import java.util.Map;

public interface RefreshTokenDao {

	public void insert(Map<String, Object> map) ;

	public String selectUserIdByRefreshToken(String refreshToken);

}
