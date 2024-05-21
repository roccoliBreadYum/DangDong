package com.ssafit.pjt.model.service;

import java.util.List;
import java.util.Map;

import com.ssafit.pjt.model.dto.Lesson;
import com.ssafit.pjt.model.dto.Teacher;

public interface LessonService {
	
	public List<Lesson> selectAllLessonById(int storeId);
	
	public List<Lesson> selectAllLessonByIdAndDate(Map<String, Object> map);
	
	public List<Teacher> SelectAllTeachers(Map<String, Object> map);
}
