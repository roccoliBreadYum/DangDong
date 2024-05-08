package com.ssafit.pjt.model.service;

import java.util.Date;
import java.util.List;

import com.ssafit.pjt.model.dto.ExcerciseClass;

public interface ClassService {
	/** 특정 일자를 기준으로 해당 일자에 해당하는 수업 리스트 반환 */
	public List<ExcerciseClass> findByDate(int storeId, Date date);

}
