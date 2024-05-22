package com.ssafit.pjt.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.pjt.model.dao.StoreDao;
import com.ssafit.pjt.model.dto.PictureStorage;
import com.ssafit.pjt.model.dto.SellTicket;
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
	public List<SellTicket> getTicketList(int storeId) {
		return storeDao.selectTicketlist(storeId);
	}


	@Override
	public String getCategory(int exerciseId) {
		return storeDao.selectCategory(exerciseId);
	}



	@Override
	@Transactional
	public int rigistStore(Store store) {
		storeDao.insertStore(store);
		
		int storeId = store.getStoreId();
		
		if(store.getLogoImage() != null) {
			PictureStorage ps = new PictureStorage();
			ps.setPictureId(store.getLogoImage());
			ps.setStoreId(storeId);
			return storeDao.insertStorePicture(ps);
		}
		return 1;
	}

	@Override
	public int modifyStore(Store store) {
		storeDao.updateStore(store);
		
		int storeId = store.getStoreId();
		
		if(store.getLogoImage() != null) {
			PictureStorage ps = new PictureStorage();
			ps.setPictureId(store.getLogoImage());
			ps.setStoreId(storeId);
			return storeDao.insertStorePicture(ps);
		}
		return 1;
		
	}


	@Override
	public List<String> getAllImg(int storeId) {
		return storeDao.selectAllimg(storeId);
	}


	
	@Override
	public SellTicket getSellTicket(int ticketId) {
		return storeDao.selectSellTicketById(ticketId);
	}


	@Override
	public String getNamebyId(int storeId) {
		return storeDao.selectNamebyId(storeId);
	}

}
