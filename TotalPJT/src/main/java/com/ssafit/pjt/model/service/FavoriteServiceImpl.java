package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.FavoriteDao;
import com.ssafit.pjt.model.dto.Favorite;
import com.ssafit.pjt.model.dto.Store;


@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	private FavoriteDao fDao;
	
	@Autowired
	public void setFavoriteDao (FavoriteDao fDao) {
		this.fDao = fDao;
	}
	
	@Override
	public int createFavorite(Favorite favorite) {
		return fDao.insert(favorite);
	}

	@Override
	public int removeFavorite(Favorite favorite) {
		return fDao.delete(favorite);
	}

	@Override
	public List<Store> readAll(String userId) {
		return fDao.selectFavoriteInfo(userId);
	}

}
