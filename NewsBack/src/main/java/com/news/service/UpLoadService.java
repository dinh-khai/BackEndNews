package com.news.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.news.exception.ErrMessage;

public interface UpLoadService {
	String upload(MultipartFile file,String forder,ErrMessage errMessage,HttpServletRequest request);
}
