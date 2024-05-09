package com.ssafit.pjt.model.dao;

import java.util.List;

import com.ssafit.pjt.model.dto.Favorite;
import com.ssafit.pjt.model.dto.Store;

public interface FavoriteDao {
	//생성
	int insert(Favorite favorite);
	
	//삭제
	int delete(Favorite favorite);
	
	//user id로 조회
	List<Store> selectFavoriteInfo(String userId);
}
