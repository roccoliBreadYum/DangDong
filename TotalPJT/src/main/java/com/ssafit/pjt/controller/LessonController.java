package com.ssafit.pjt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Lesson;
import com.ssafit.pjt.model.service.LessonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/lesson")
@Tag(name="LessonController", description = "가게 별 예약가능한 가게 리스트 반환")
public class LessonController {

	private final LessonService lService;
	
	public LessonController(LessonService lService) {
		this.lService = lService;
	}
	
	@GetMapping("")
	@Operation(summary = "레슨 리스트 반환", description = "해당 가게에서 진행하는 전체 레슨 리스트 반환")
	public ResponseEntity<?> getLessonById(@RequestBody int storeId){
		List<Lesson> list = lService.allLessonById(storeId);
		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
}
