package com.ssafit.pjt.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class StorageServiceImpl implements StorageService{

	@Value("${aws.s3.bucket}")
	private String bucketName;
	
	@Value("${aws.s3.bucket.folder}")
	private String thumbnail;

	@Autowired
	private AmazonS3 s3Client;
	
	
	public String uploadFile(MultipartFile file) {
		File fileObj = convertMultiPartFiletoFile(file);
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		String newFile = thumbnail + "/" + fileName;
		s3Client.putObject(new PutObjectRequest(bucketName, newFile, fileObj));
		fileObj.delete();
		return fileName;
	}
	
	public String updateFile(MultipartFile file) {
		File fileObj = convertMultiPartFiletoFile(file);
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
		fileObj.delete();
		return fileName;
	}
	
	
	public byte[] downloadFile(String fileName) {
		S3Object s3Object = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteFile(String fileName) {
		s3Client.deleteObject(bucketName, fileName);
		return fileName + " remove...";
	}
	
	
	private File convertMultiPartFiletoFile (MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try(FileOutputStream fos = new FileOutputStream(convertedFile)){
			fos.write(file.getBytes());
		} catch(IOException e) {
			System.out.println(e);
		}
		return convertedFile;
	}
	
	
}
