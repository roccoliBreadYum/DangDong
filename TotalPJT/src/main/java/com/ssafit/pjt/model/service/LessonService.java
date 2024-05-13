package com.ssafit.pjt.model.service;

import java.util.List;

import com.ssafit.pjt.model.dto.Lesson;

public interface LessonService {
	
	public List<Lesson> selectAllLessonById(int storeId);
}
