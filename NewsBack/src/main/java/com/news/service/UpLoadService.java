package com.news.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface UpLoadService {
	String upload(MultipartFile file,String forder);
	
	InputStream getInputStream(String func, String date, String fileName);
}
