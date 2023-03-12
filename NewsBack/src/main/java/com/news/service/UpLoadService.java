package com.news.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface UpLoadService {
	String upload(MultipartFile file,String forder,HttpServletRequest request);
}
