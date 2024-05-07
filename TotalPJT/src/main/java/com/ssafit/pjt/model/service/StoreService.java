package com.ssafit.pjt.model.service;

import java.util.List;

import com.ssafit.pjt.model.dto.Category;
import com.ssafit.pjt.model.dto.Store;
import com.ssafit.pjt.util.StoreSearchCondition;

public interface StoreService {
	
	/** 메인화면에 카테고리를 불러오는 것이 아닌 원하는 카테고리를 프론트단에서 설정해놓고 파라미터를 넘기는게 맞을 것 같은데 일단은 구현 */
	public List<Category> categoryList();
	
	/** 필터 조건에 따른 Store 리스트 반환*/
	public List<Store> storeList(StoreSearchCondition searchCondition);
	
	/** 메인화면에 노출된 카테고리별 Store리스트 반환*/
	public List<Store> getStoreById(int id);
	
	/** 등록된 Store 수정 (필요할지 의문, 또는 이런식으로 사용되는것이 맞느지 의문)*/
	public int modifyStore(Store store);
	
}
