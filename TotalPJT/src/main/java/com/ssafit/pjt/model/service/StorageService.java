package com.ssafit.pjt.model.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	public String uploadFile(MultipartFile file);
	
	public String updateFile(MultipartFile file);
	
	public byte[] downloadFile(String fileName);
	
	public String deleteFile(String fileName);

}
