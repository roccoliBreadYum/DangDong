package com.ssafit.pjt.model.dao;

import java.util.List;

import com.ssafit.pjt.model.dto.Category;
import com.ssafit.pjt.model.dto.Store;
import com.ssafit.pjt.util.StoreSearchCondition;

public interface StoreDao {
	
	public List<Category> selectCategory();
	
	public List<Store> selectStore(StoreSearchCondition searchCondition);
	
	public List<Store> selectStoreById(int id);
	
	public int updateStore(Store store);

	
	
	
}
