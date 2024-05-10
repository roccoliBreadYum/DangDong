package com.ssafit.pjt.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafit.pjt.model.dto.Store;
import com.ssafit.pjt.util.StoreSearchCondition;

public interface StoreDao {
	
	public List<Store> selectStore(StoreSearchCondition searchCondition);
	
	public List<Store> selectStoreDetail(Map<String, Object> map);
	
	public int updateStore(Store store);

}
