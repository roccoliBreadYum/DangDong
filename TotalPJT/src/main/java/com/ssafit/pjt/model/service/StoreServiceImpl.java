package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.StoreDao;
import com.ssafit.pjt.model.dto.Category;
import com.ssafit.pjt.model.dto.SearchCondition;
import com.ssafit.pjt.model.dto.Store;

@Service
public class StoreServiceImpl implements StoreService{
	
	private final StoreDao storeDao;
	
	public StoreServiceImpl (StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@Override
	public List<Category> categoryList() {
		return storeDao.selectCategory();
	}

	@Override
	public List<Store> storeList(SearchCondition searchCondition) {
		return storeDao.selectStore(searchCondition);
	}
	
	@Override
	public List<Store> getStoreById(int id) {
		return storeDao.selectStoreById(id);
	}

	@Override
	public int modifyStore(Store store) {
		return storeDao.updateStore(store);
	}

	





}
