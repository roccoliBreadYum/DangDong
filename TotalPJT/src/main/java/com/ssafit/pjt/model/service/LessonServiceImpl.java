package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.LessonDao;
import com.ssafit.pjt.model.dto.Lesson;

@Service
public class LessonServiceImpl implements LessonService{
	
	private final LessonDao lDao;
	
	public LessonServiceImpl (LessonDao lDao) {
		this.lDao = lDao;
	}

	@Override
	public List<Lesson> selectAllLessonById(int storeId) {
		return lDao.selectAllLessonById(storeId);
	}
	

}
