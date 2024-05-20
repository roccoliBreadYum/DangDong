package com.ssafit.pjt.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.StoreDao;
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
	public Store getStoreDetail(Map<String, Object> map) {
		return storeDao.selectStoreDetail(map);
	}


	@Override
	public String getCategory(int exerciseId) {
		return storeDao.selectCategory(exerciseId);
	}

//	@Override
//	public int modifyStore(Store store) {
//		return storeDao.updateStore(store);
//	}

}
