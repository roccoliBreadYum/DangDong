package com.ssafit.pjt.model.service;

import java.util.List;
import java.util.Map;

import com.ssafit.pjt.model.dto.Category;
import com.ssafit.pjt.model.dto.Store;
import com.ssafit.pjt.util.StoreSearchCondition;

public interface StoreService {
	
	
	/** 필터 조건에 따른 Store 리스트 반환*/
	public List<Store> storeList(StoreSearchCondition searchCondition);
	
	/** store detail*/
	public Store getStoreDetail(Map<String, Object> map);
	
	
	public String getCategory(int exerciseId);
	
	/** 등록된 Store 수정 (필요할지 의문, 또는 이런식으로 사용되는것이 맞느지 의문)*/
//	public int modifyStore(Store store);

	
}
