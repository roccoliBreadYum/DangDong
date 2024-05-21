package com.ssafit.pjt.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.LessonDao;
import com.ssafit.pjt.model.dto.Lesson;
import com.ssafit.pjt.model.dto.Teacher;

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

	@Override
	public List<Lesson> selectAllLessonByIdAndDate(Map<String, Object> map) {
		return lDao.selectAllLessonByIdAndDate(map);
	}

	@Override
	public List<Teacher> SelectAllTeachers(Map<String, Object> map) {
		return lDao.SelectAllTeachers(map);
	}
	

}
