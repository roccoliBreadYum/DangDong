package com.ssafit.pjt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Lesson;
import com.ssafit.pjt.model.dto.Teacher;
import com.ssafit.pjt.model.service.LessonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/api-lesson")
@Tag(name="LessonController", description = "가게 별 예약가능한 가게 리스트 반환")
public class LessonController {

	private final LessonService lService;
	
	public LessonController(LessonService lService) {
		this.lService = lService;
	}
	
	@GetMapping("/{storeId}")
	@Operation(summary = "레슨 리스트 반환", description = "해당 가게에서 진행하는 전체 레슨 리스트 반환")
	public ResponseEntity<?> getLessonById(@PathVariable int storeId){
		List<Lesson> list = lService.selectAllLessonById(storeId);
		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("lesson/{storeId}/{date}")
	@Operation(summary = "레슨 리스트 반환", description = "해당 가게에서 진행하는 특정 날짜의 전체 레슨 리스트 반환")
	public ResponseEntity<?> getLessonByIdAndDate(@PathVariable int storeId, @PathVariable String date){
		Map<String, Object> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("date", date);
	    List<Lesson> list = lService.selectAllLessonByIdAndDate(map);
	    return new ResponseEntity<>(list, list != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("teacher/{storeId}/{date}")
	@Operation(summary = "강사 리스트 반환", description = "전체 강사 리스트 반환")
	public ResponseEntity<?> getAllTeachers(@PathVariable int storeId, @PathVariable String date) {
		Map<String, Object> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("date", date);
	    List<Teacher> list = lService.SelectAllTeachers(map);
	    return new ResponseEntity<>(list, list != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/lesson/{lessonId}")
	@Operation(summary = "특정 lesson", description = "선택한 lesson정보 반환")
	public ResponseEntity<?> getLesson(@PathVariable("lessonId") int lessonId) {
		Lesson lesson = lService.selectLesson(lessonId);
	    return new ResponseEntity<>(lesson, lesson != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
}
