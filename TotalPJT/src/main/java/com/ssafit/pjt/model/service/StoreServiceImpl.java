package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.StoreDao;
import com.ssafit.pjt.model.dto.Category;
import com.ssafit.pjt.model.dto.Store;
import com.ssafit.pjt.util.StoreSearchCondition;

@Service
public class StoreServiceImpl implements StoreService{
	
	private final StoreDao storeDao;
	
	public StoreServiceImpl (StoreDao storeDao) {
		this.storeDao = storeDao;
	}


	@Override
	public List<Store> storeList(StoreSearchCondition searchCondition) {
		return storeDao.selectStore(searchCondition);
	}
	
	@Override
	public List<Store> getStoreById(int storeId) {
		return storeDao.selectStoreById(storeId);
	}

	@Override
	public int modifyStore(Store store) {
		return storeDao.updateStore(store);
	}

	





}
