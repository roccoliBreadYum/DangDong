package com.ssafit.pjt.model.service;

import java.util.List;

import com.ssafit.pjt.model.dto.Favorite;
import com.ssafit.pjt.model.dto.Store;

public interface FavoriteService {
	
	int createFavorite(Favorite favorite);
	
	int removeFavorite(Favorite favorite);
	
	List<Store> readAll(String userId);
	
}
