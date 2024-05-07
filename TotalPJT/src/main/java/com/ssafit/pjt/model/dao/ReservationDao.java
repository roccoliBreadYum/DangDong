package com.ssafit.pjt.model.dao;

import java.util.Date;
import java.util.List;

import com.ssafit.pjt.model.dto.ExcerciseClass;

public interface ReservationDao {
	
	public List<ExcerciseClass> selectByDate(int storeId, Date date);

}
