package com.ssafit.pjt.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafit.pjt.model.dto.PictureStorage;
import com.ssafit.pjt.model.dto.SellTicket;
import com.ssafit.pjt.model.dto.Store;
import com.ssafit.pjt.util.StoreSearchCondition;

public interface StoreDao {
	
	// 필터 조건에 따른 전체 가게 목록 반환
	public List<Store> selectStore(StoreSearchCondition searchCondition);
	
	// 리스트에서 선택한 가게의 상세페이지
	public Store selectStoreDetail(Map<String, Object> map);
	
	public List<SellTicket> selectTicketlist(int storeId);
	
	public String selectCategory(int excerciseId);

	public SellTicket selectSellTicketById(int ticketId);
	
	public int insertStore(Store store);
	
	public int insertStorePicture(PictureStorage pictureStorage);
	
	// 가게 상세페이지 내 내용 수정
	public int updateStore(Store store);
	
	public List<String> selectAllimg (int storeId);
	
	public String selectNamebyId (int storeId);

}
