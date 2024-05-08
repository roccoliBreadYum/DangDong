package com.ssafit.pjt.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.ClassDao;
import com.ssafit.pjt.model.dto.ExcerciseClass;

@Service
public class ClassServiceImpl implements ClassService{

private final ClassDao resDao;
	
	public ClassServiceImpl (ClassDao resDao) {
		this.resDao = resDao;
	}
	
	@Override
	public List<ExcerciseClass> findByDate(int storeId, Date date) {
		return resDao.selectByDate(storeId, date);
	}

}
