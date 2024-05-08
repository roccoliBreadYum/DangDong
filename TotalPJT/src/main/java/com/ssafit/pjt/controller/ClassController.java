package com.ssafit.pjt.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Category;
import com.ssafit.pjt.model.service.ClassService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController //rest API사용, JSON형태로 주고받기 위함 
@RequestMapping("/class")
@Tag(name="classController", description = "등록된 수업과 관련된 컨트롤러")
public class ClassController {

	private final ClassService resService;
	
	public ClassController(ClassService resService) {
		this.resService = resService;
	}
	
//	@GetMapping("/category/{storeId}")
//	public ResponseEntity<?> getCategory(){
//		List<Category> list = resService.findByDate(@PathVariable("storeId")int storeId, Date date))
//		return new ResponseEntity<>(list, list != null? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
	
}
