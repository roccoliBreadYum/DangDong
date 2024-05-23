package com.ssafit.pjt.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafit.pjt.model.dto.Lesson;
import com.ssafit.pjt.model.dto.Teacher;

public interface LessonDao {
	
	// 해당 가게 ID별 개설된 강의 목록 반환
	public List<Lesson> selectAllLessonById (int storeId);
	
	public List<Lesson> selectAllLessonByIdAndDate (Map<String, Object> map);
	
	public List<Teacher> SelectAllTeachers(Map<String, Object> map);
	
	public Lesson selectLesson(int lessonId);
	
	public int decreaseLesson(int lessonId);
}
