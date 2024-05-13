package com.ssafit.pjt.model.dao;

import java.util.List;

import com.ssafit.pjt.model.dto.Lesson;

public interface LessonDao {
	
	// 해당 가게 ID별 개설된 강의 목록 반환
	public List<Lesson> selectAllLessonById (int storeId);
	

}
