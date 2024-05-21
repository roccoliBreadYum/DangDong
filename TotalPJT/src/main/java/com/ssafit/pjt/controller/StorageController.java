package com.ssafit.pjt.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafit.pjt.model.service.StorageServiceImpl;

@RestController
@RequestMapping("/api-file")
public class StorageController {
	
	private StorageServiceImpl service;
	
	public StorageController(StorageServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile (@RequestParam(value="file") MultipartFile file){
		String str = service.uploadFile(file);
		return new ResponseEntity<>(str, str != null? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile (@PathVariable("fileName") String fileName){
		byte[] data = service.downloadFile(fileName);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity
				.ok()
				.contentLength(data.length)
				.header("content-type", "application/octet-stream")
				.header("content-disposition", "attachment; filename=\"" + fileName + "\"")
				.body(resource);
	}
	
	@DeleteMapping("/delete/{fileName}")
	public ResponseEntity<String> deleteFile(@PathVariable("fileName") String fileName){
		String str = service.deleteFile(fileName);
		return new ResponseEntity<>(str, str != null? HttpStatus.OK : HttpStatus.BAD_GATEWAY);
	}
	
	

}
